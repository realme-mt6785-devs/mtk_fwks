package android.util.apk;

import android.os.incremental.IncrementalManager;
import android.os.incremental.V4Signature;
import android.util.ArrayMap;
import android.util.Pair;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Map;
/* loaded from: classes3.dex */
public class ApkSignatureSchemeV4Verifier {
    public static VerifiedSigner extractCertificates(String apkFile) throws SignatureNotFoundException, SecurityException {
        File apk = new File(apkFile);
        byte[] signatureBytes = IncrementalManager.unsafeGetFileSignature(apk.getAbsolutePath());
        if (signatureBytes == null || signatureBytes.length == 0) {
            throw new SignatureNotFoundException("Failed to obtain signature bytes from IncFS.");
        }
        try {
            V4Signature signature = V4Signature.readFrom(signatureBytes);
            if (signature.isVersionSupported()) {
                V4Signature.HashingInfo hashingInfo = V4Signature.HashingInfo.fromByteArray(signature.hashingInfo);
                V4Signature.SigningInfo signingInfo = V4Signature.SigningInfo.fromByteArray(signature.signingInfo);
                byte[] signedData = V4Signature.getSignedData(apk.length(), hashingInfo, signingInfo);
                Pair<Certificate, byte[]> result = verifySigner(signingInfo, signedData);
                Map<Integer, byte[]> contentDigests = new ArrayMap<>();
                contentDigests.put(Integer.valueOf(convertToContentDigestType(hashingInfo.hashAlgorithm)), hashingInfo.rawRootHash);
                return new VerifiedSigner(new Certificate[]{result.first}, result.second, contentDigests);
            }
            throw new SecurityException("v4 signature version " + signature.version + " is not supported");
        } catch (IOException e) {
            throw new SignatureNotFoundException("Failed to read V4 signature.", e);
        }
    }

    private static Pair<Certificate, byte[]> verifySigner(V4Signature.SigningInfo signingInfo, byte[] signedData) throws SecurityException {
        if (ApkSigningBlockUtils.isSupportedSignatureAlgorithm(signingInfo.signatureAlgorithmId)) {
            int signatureAlgorithmId = signingInfo.signatureAlgorithmId;
            byte[] signatureBytes = signingInfo.signature;
            byte[] publicKeyBytes = signingInfo.publicKey;
            byte[] encodedCert = signingInfo.certificate;
            String keyAlgorithm = ApkSigningBlockUtils.getSignatureAlgorithmJcaKeyAlgorithm(signatureAlgorithmId);
            Pair<String, ? extends AlgorithmParameterSpec> signatureAlgorithmParams = ApkSigningBlockUtils.getSignatureAlgorithmJcaSignatureAlgorithm(signatureAlgorithmId);
            String jcaSignatureAlgorithm = signatureAlgorithmParams.first;
            AlgorithmParameterSpec jcaSignatureAlgorithmParams = (AlgorithmParameterSpec) signatureAlgorithmParams.second;
            try {
                PublicKey publicKey = KeyFactory.getInstance(keyAlgorithm).generatePublic(new X509EncodedKeySpec(publicKeyBytes));
                Signature sig = Signature.getInstance(jcaSignatureAlgorithm);
                sig.initVerify(publicKey);
                if (jcaSignatureAlgorithmParams != null) {
                    sig.setParameter(jcaSignatureAlgorithmParams);
                }
                sig.update(signedData);
                boolean sigVerified = sig.verify(signatureBytes);
                if (sigVerified) {
                    try {
                        CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
                        try {
                            X509Certificate certificate = new VerbatimX509Certificate((X509Certificate) certFactory.generateCertificate(new ByteArrayInputStream(encodedCert)), encodedCert);
                            byte[] certificatePublicKeyBytes = certificate.getPublicKey().getEncoded();
                            if (Arrays.equals(publicKeyBytes, certificatePublicKeyBytes)) {
                                return Pair.create(certificate, signingInfo.apkDigest);
                            }
                            throw new SecurityException("Public key mismatch between certificate and signature record");
                        } catch (CertificateException e) {
                            throw new SecurityException("Failed to decode certificate", e);
                        }
                    } catch (CertificateException e2) {
                        throw new RuntimeException("Failed to obtain X.509 CertificateFactory", e2);
                    }
                } else {
                    throw new SecurityException(jcaSignatureAlgorithm + " signature did not verify");
                }
            } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | SignatureException | InvalidKeySpecException e3) {
                throw new SecurityException("Failed to verify " + jcaSignatureAlgorithm + " signature", e3);
            }
        } else {
            throw new SecurityException("No supported signatures found");
        }
    }

    private static int convertToContentDigestType(int hashAlgorithm) throws SecurityException {
        if (hashAlgorithm == 1) {
            return 3;
        }
        throw new SecurityException("Unsupported hashAlgorithm: " + hashAlgorithm);
    }

    /* loaded from: classes3.dex */
    public static class VerifiedSigner {
        public final byte[] apkDigest;
        public final Certificate[] certs;
        public final Map<Integer, byte[]> contentDigests;

        public VerifiedSigner(Certificate[] certs, byte[] apkDigest, Map<Integer, byte[]> contentDigests) {
            this.certs = certs;
            this.apkDigest = apkDigest;
            this.contentDigests = contentDigests;
        }
    }
}
