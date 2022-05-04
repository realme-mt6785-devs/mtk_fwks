package android.util.apk;

import android.util.ArrayMap;
import android.util.Pair;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.DigestException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
/* loaded from: classes3.dex */
public class ApkSignatureSchemeV2Verifier {
    private static final int APK_SIGNATURE_SCHEME_V2_BLOCK_ID = 1896449818;
    public static final int SF_ATTRIBUTE_ANDROID_APK_SIGNED_ID = 2;
    private static final int STRIPPING_PROTECTION_ATTR_ID = -1091571699;

    public static boolean hasSignature(String apkFile) throws IOException {
        try {
            RandomAccessFile apk = new RandomAccessFile(apkFile, "r");
            findSignature(apk);
            apk.close();
            return true;
        } catch (SignatureNotFoundException e) {
            return false;
        }
    }

    public static X509Certificate[][] verify(String apkFile) throws SignatureNotFoundException, SecurityException, IOException {
        VerifiedSigner vSigner = verify(apkFile, true);
        return vSigner.certs;
    }

    public static X509Certificate[][] unsafeGetCertsWithoutVerification(String apkFile) throws SignatureNotFoundException, SecurityException, IOException {
        VerifiedSigner vSigner = verify(apkFile, false);
        return vSigner.certs;
    }

    public static VerifiedSigner verify(String apkFile, boolean verifyIntegrity) throws SignatureNotFoundException, SecurityException, IOException {
        RandomAccessFile apk = new RandomAccessFile(apkFile, "r");
        try {
            VerifiedSigner verify = verify(apk, verifyIntegrity);
            apk.close();
            return verify;
        } catch (Throwable th) {
            try {
                apk.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private static VerifiedSigner verify(RandomAccessFile apk, boolean verifyIntegrity) throws SignatureNotFoundException, SecurityException, IOException {
        SignatureInfo signatureInfo = findSignature(apk);
        return verify(apk, signatureInfo, verifyIntegrity);
    }

    public static SignatureInfo findSignature(RandomAccessFile apk) throws IOException, SignatureNotFoundException {
        return ApkSigningBlockUtils.findSignature(apk, APK_SIGNATURE_SCHEME_V2_BLOCK_ID);
    }

    private static VerifiedSigner verify(RandomAccessFile apk, SignatureInfo signatureInfo, boolean doVerifyIntegrity) throws SecurityException, IOException {
        int signerCount = 0;
        Map<Integer, byte[]> contentDigests = new ArrayMap<>();
        List<X509Certificate[]> signerCerts = new ArrayList<>();
        try {
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            try {
                ByteBuffer signers = ApkSigningBlockUtils.getLengthPrefixedSlice(signatureInfo.signatureBlock);
                while (signers.hasRemaining()) {
                    signerCount++;
                    try {
                        ByteBuffer signer = ApkSigningBlockUtils.getLengthPrefixedSlice(signers);
                        X509Certificate[] certs = verifySigner(signer, contentDigests, certFactory);
                        signerCerts.add(certs);
                    } catch (IOException | SecurityException | BufferUnderflowException e) {
                        throw new SecurityException("Failed to parse/verify signer #" + signerCount + " block", e);
                    }
                }
                if (signerCount < 1) {
                    throw new SecurityException("No signers found");
                } else if (!contentDigests.isEmpty()) {
                    if (doVerifyIntegrity) {
                        ApkSigningBlockUtils.verifyIntegrity(contentDigests, apk, signatureInfo);
                    }
                    byte[] verityRootHash = null;
                    if (contentDigests.containsKey(3)) {
                        byte[] verityDigest = contentDigests.get(3);
                        verityRootHash = ApkSigningBlockUtils.parseVerityDigestAndVerifySourceLength(verityDigest, apk.length(), signatureInfo);
                    }
                    return new VerifiedSigner((X509Certificate[][]) signerCerts.toArray(new X509Certificate[signerCerts.size()]), verityRootHash, contentDigests);
                } else {
                    throw new SecurityException("No content digests found");
                }
            } catch (IOException e2) {
                throw new SecurityException("Failed to read list of signers", e2);
            }
        } catch (CertificateException e3) {
            throw new RuntimeException("Failed to obtain X.509 CertificateFactory", e3);
        }
    }

    private static X509Certificate[] verifySigner(ByteBuffer signerBlock, Map<Integer, byte[]> contentDigests, CertificateFactory certFactory) throws SecurityException, IOException {
        GeneralSecurityException e;
        CertificateException e2;
        Exception e3;
        ByteBuffer signedData = ApkSigningBlockUtils.getLengthPrefixedSlice(signerBlock);
        ByteBuffer signatures = ApkSigningBlockUtils.getLengthPrefixedSlice(signerBlock);
        byte[] publicKeyBytes = ApkSigningBlockUtils.readLengthPrefixedByteArray(signerBlock);
        List<Integer> signaturesSigAlgorithms = new ArrayList<>();
        byte[] bestSigAlgorithmSignatureBytes = null;
        int bestSigAlgorithm = -1;
        int signatureCount = 0;
        while (signatures.hasRemaining()) {
            signatureCount++;
            try {
                ByteBuffer signature = ApkSigningBlockUtils.getLengthPrefixedSlice(signatures);
                if (signature.remaining() >= 8) {
                    int sigAlgorithm = signature.getInt();
                    signaturesSigAlgorithms.add(Integer.valueOf(sigAlgorithm));
                    if (ApkSigningBlockUtils.isSupportedSignatureAlgorithm(sigAlgorithm)) {
                        if (bestSigAlgorithm == -1 || ApkSigningBlockUtils.compareSignatureAlgorithm(sigAlgorithm, bestSigAlgorithm) > 0) {
                            bestSigAlgorithm = sigAlgorithm;
                            bestSigAlgorithmSignatureBytes = ApkSigningBlockUtils.readLengthPrefixedByteArray(signature);
                        }
                    }
                } else {
                    throw new SecurityException("Signature record too short");
                }
            } catch (IOException | BufferUnderflowException e4) {
                throw new SecurityException("Failed to parse signature record #" + signatureCount, e4);
            }
        }
        if (bestSigAlgorithm != -1) {
            String keyAlgorithm = ApkSigningBlockUtils.getSignatureAlgorithmJcaKeyAlgorithm(bestSigAlgorithm);
            Pair<String, ? extends AlgorithmParameterSpec> signatureAlgorithmParams = ApkSigningBlockUtils.getSignatureAlgorithmJcaSignatureAlgorithm(bestSigAlgorithm);
            String jcaSignatureAlgorithm = signatureAlgorithmParams.first;
            AlgorithmParameterSpec jcaSignatureAlgorithmParams = (AlgorithmParameterSpec) signatureAlgorithmParams.second;
            try {
                PublicKey publicKey = KeyFactory.getInstance(keyAlgorithm).generatePublic(new X509EncodedKeySpec(publicKeyBytes));
                Signature sig = Signature.getInstance(jcaSignatureAlgorithm);
                sig.initVerify(publicKey);
                if (jcaSignatureAlgorithmParams != null) {
                    try {
                        sig.setParameter(jcaSignatureAlgorithmParams);
                    } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | SignatureException | InvalidKeySpecException e5) {
                        e = e5;
                        throw new SecurityException("Failed to verify " + jcaSignatureAlgorithm + " signature", e);
                    }
                }
                sig.update(signedData);
                boolean sigVerified = sig.verify(bestSigAlgorithmSignatureBytes);
                if (sigVerified) {
                    signedData.clear();
                    ByteBuffer digests = ApkSigningBlockUtils.getLengthPrefixedSlice(signedData);
                    List<Integer> digestsSigAlgorithms = new ArrayList<>();
                    int digestCount = 0;
                    byte[] contentDigest = null;
                    while (digests.hasRemaining()) {
                        int digestCount2 = digestCount + 1;
                        try {
                            ByteBuffer digest = ApkSigningBlockUtils.getLengthPrefixedSlice(digests);
                            if (digest.remaining() >= 8) {
                                try {
                                    int sigAlgorithm2 = digest.getInt();
                                    digestsSigAlgorithms.add(Integer.valueOf(sigAlgorithm2));
                                    if (sigAlgorithm2 == bestSigAlgorithm) {
                                        contentDigest = ApkSigningBlockUtils.readLengthPrefixedByteArray(digest);
                                    }
                                    digestCount = digestCount2;
                                    signatures = signatures;
                                } catch (IOException | BufferUnderflowException e6) {
                                    e3 = e6;
                                }
                            } else {
                                throw new IOException("Record too short");
                            }
                            e3 = e6;
                        } catch (IOException | BufferUnderflowException e7) {
                            e3 = e7;
                        }
                        throw new IOException("Failed to parse digest record #" + digestCount2, e3);
                    }
                    if (signaturesSigAlgorithms.equals(digestsSigAlgorithms)) {
                        int certificateCount = ApkSigningBlockUtils.getSignatureAlgorithmContentDigestAlgorithm(bestSigAlgorithm);
                        byte[] previousSignerDigest = contentDigests.put(Integer.valueOf(certificateCount), contentDigest);
                        if (previousSignerDigest != null && !MessageDigest.isEqual(previousSignerDigest, contentDigest)) {
                            throw new SecurityException(ApkSigningBlockUtils.getContentDigestAlgorithmJcaDigestAlgorithm(certificateCount) + " contents digest does not match the digest specified by a preceding signer");
                        }
                        List<X509Certificate> certs = new ArrayList<>();
                        int certificateCount2 = 0;
                        for (ByteBuffer certificates = ApkSigningBlockUtils.getLengthPrefixedSlice(signedData); certificates.hasRemaining(); certificates = certificates) {
                            int digestAlgorithm = certificateCount2 + 1;
                            byte[] encodedCert = ApkSigningBlockUtils.readLengthPrefixedByteArray(certificates);
                            try {
                            } catch (CertificateException e8) {
                                e2 = e8;
                            }
                            try {
                                X509Certificate certificate = (X509Certificate) certFactory.generateCertificate(new ByteArrayInputStream(encodedCert));
                                certs.add(new VerbatimX509Certificate(certificate, encodedCert));
                                certificateCount2 = digestAlgorithm;
                                certificateCount = certificateCount;
                                signaturesSigAlgorithms = signaturesSigAlgorithms;
                            } catch (CertificateException e9) {
                                e2 = e9;
                                throw new SecurityException("Failed to decode certificate #" + digestAlgorithm, e2);
                            }
                        }
                        if (!certs.isEmpty()) {
                            X509Certificate mainCertificate = certs.get(0);
                            byte[] certificatePublicKeyBytes = mainCertificate.getPublicKey().getEncoded();
                            if (Arrays.equals(publicKeyBytes, certificatePublicKeyBytes)) {
                                ByteBuffer additionalAttrs = ApkSigningBlockUtils.getLengthPrefixedSlice(signedData);
                                verifyAdditionalAttributes(additionalAttrs);
                                return (X509Certificate[]) certs.toArray(new X509Certificate[certs.size()]);
                            }
                            throw new SecurityException("Public key mismatch between certificate and signature record");
                        }
                        throw new SecurityException("No certificates listed");
                    }
                    throw new SecurityException("Signature algorithms don't match between digests and signatures records");
                }
                throw new SecurityException(jcaSignatureAlgorithm + " signature did not verify");
            } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | SignatureException | InvalidKeySpecException e10) {
                e = e10;
            }
        } else if (signatureCount == 0) {
            throw new SecurityException("No signatures found");
        } else {
            throw new SecurityException("No supported signatures found");
        }
    }

    private static void verifyAdditionalAttributes(ByteBuffer attrs) throws SecurityException, IOException {
        while (attrs.hasRemaining()) {
            ByteBuffer attr = ApkSigningBlockUtils.getLengthPrefixedSlice(attrs);
            if (attr.remaining() >= 4) {
                int id = attr.getInt();
                switch (id) {
                    case STRIPPING_PROTECTION_ATTR_ID /* -1091571699 */:
                        if (attr.remaining() >= 4) {
                            int vers = attr.getInt();
                            if (vers != 3) {
                                break;
                            } else {
                                throw new SecurityException("V2 signature indicates APK is signed using APK Signature Scheme v3, but none was found. Signature stripped?");
                            }
                        } else {
                            throw new IOException("V2 Signature Scheme Stripping Protection Attribute  value too small.  Expected 4 bytes, but found " + attr.remaining());
                        }
                }
            } else {
                throw new IOException("Remaining buffer too short to contain additional attribute ID. Remaining: " + attr.remaining());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] getVerityRootHash(String apkPath) throws IOException, SignatureNotFoundException, SecurityException {
        RandomAccessFile apk = new RandomAccessFile(apkPath, "r");
        try {
            findSignature(apk);
            VerifiedSigner vSigner = verify(apk, false);
            byte[] bArr = vSigner.verityRootHash;
            apk.close();
            return bArr;
        } catch (Throwable th) {
            try {
                apk.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] generateApkVerity(String apkPath, ByteBufferFactory bufferFactory) throws IOException, SignatureNotFoundException, SecurityException, DigestException, NoSuchAlgorithmException {
        RandomAccessFile apk = new RandomAccessFile(apkPath, "r");
        try {
            SignatureInfo signatureInfo = findSignature(apk);
            byte[] generateApkVerity = VerityBuilder.generateApkVerity(apkPath, bufferFactory, signatureInfo);
            apk.close();
            return generateApkVerity;
        } catch (Throwable th) {
            try {
                apk.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] generateApkVerityRootHash(String apkPath) throws IOException, SignatureNotFoundException, DigestException, NoSuchAlgorithmException {
        RandomAccessFile apk = new RandomAccessFile(apkPath, "r");
        try {
            SignatureInfo signatureInfo = findSignature(apk);
            VerifiedSigner vSigner = verify(apk, false);
            if (vSigner.verityRootHash == null) {
                apk.close();
                return null;
            }
            byte[] generateApkVerityRootHash = VerityBuilder.generateApkVerityRootHash(apk, ByteBuffer.wrap(vSigner.verityRootHash), signatureInfo);
            apk.close();
            return generateApkVerityRootHash;
        } catch (Throwable th) {
            try {
                apk.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    /* loaded from: classes3.dex */
    public static class VerifiedSigner {
        public final X509Certificate[][] certs;
        public final Map<Integer, byte[]> contentDigests;
        public final byte[] verityRootHash;

        public VerifiedSigner(X509Certificate[][] certs, byte[] verityRootHash, Map<Integer, byte[]> contentDigests) {
            this.certs = certs;
            this.verityRootHash = verityRootHash;
            this.contentDigests = contentDigests;
        }
    }
}
