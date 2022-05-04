package android.util.apk;

import android.content.pm.PackageManager;
import android.content.pm.PackageParser;
import android.content.pm.Signature;
import android.os.Trace;
import android.util.ArrayMap;
import android.util.Slog;
import android.util.apk.ApkSignatureSchemeV2Verifier;
import android.util.apk.ApkSignatureSchemeV3Verifier;
import android.util.apk.ApkSignatureSchemeV4Verifier;
import android.util.jar.StrictJarFile;
import com.android.internal.util.ArrayUtils;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.ZipEntry;
import libcore.io.IoUtils;
/* loaded from: classes3.dex */
public class ApkSignatureVerifier {
    private static final int NUMBER_OF_CORES;
    private static final String TAG = "ApkSignatureVerifier";
    private static final AtomicReference<byte[]> sBuffer;
    public static boolean sOnScanDataApp;

    static {
        int i = 4;
        if (Runtime.getRuntime().availableProcessors() < 4) {
            i = Runtime.getRuntime().availableProcessors();
        }
        NUMBER_OF_CORES = i;
        sBuffer = new AtomicReference<>();
        sOnScanDataApp = false;
    }

    public static PackageParser.SigningDetails verify(String apkPath, int minSignatureSchemeVersion) throws PackageParser.PackageParserException {
        return verifySignatures(apkPath, minSignatureSchemeVersion, true);
    }

    public static PackageParser.SigningDetails unsafeGetCertsWithoutVerification(String apkPath, int minSignatureSchemeVersion) throws PackageParser.PackageParserException {
        return verifySignatures(apkPath, minSignatureSchemeVersion, false);
    }

    private static PackageParser.SigningDetails verifySignatures(String apkPath, int minSignatureSchemeVersion, boolean verifyFull) throws PackageParser.PackageParserException {
        return verifySignaturesInternal(apkPath, minSignatureSchemeVersion, verifyFull).signingDetails;
    }

    public static SigningDetailsWithDigests verifySignaturesInternal(String apkPath, int minSignatureSchemeVersion, boolean verifyFull) throws PackageParser.PackageParserException {
        if (minSignatureSchemeVersion <= 4) {
            try {
                return verifyV4Signature(apkPath, minSignatureSchemeVersion, verifyFull);
            } catch (SignatureNotFoundException e) {
                if (minSignatureSchemeVersion >= 4) {
                    throw new PackageParser.PackageParserException(-103, "No APK Signature Scheme v4 signature in package " + apkPath, e);
                } else if (minSignatureSchemeVersion <= 3) {
                    return verifyV3AndBelowSignatures(apkPath, minSignatureSchemeVersion, verifyFull);
                } else {
                    throw new PackageParser.PackageParserException(-103, "No signature found in package of version " + minSignatureSchemeVersion + " or newer for package " + apkPath);
                }
            }
        } else {
            throw new PackageParser.PackageParserException(-103, "No signature found in package of version " + minSignatureSchemeVersion + " or newer for package " + apkPath);
        }
    }

    private static SigningDetailsWithDigests verifyV3AndBelowSignatures(String apkPath, int minSignatureSchemeVersion, boolean verifyFull) throws PackageParser.PackageParserException {
        try {
            return verifyV3Signature(apkPath, verifyFull);
        } catch (SignatureNotFoundException e) {
            if (minSignatureSchemeVersion >= 3) {
                throw new PackageParser.PackageParserException(-103, "No APK Signature Scheme v3 signature in package " + apkPath, e);
            } else if (minSignatureSchemeVersion <= 2) {
                try {
                    return verifyV2Signature(apkPath, verifyFull);
                } catch (SignatureNotFoundException e2) {
                    if (minSignatureSchemeVersion >= 2) {
                        throw new PackageParser.PackageParserException(-103, "No APK Signature Scheme v2 signature in package " + apkPath, e2);
                    } else if (minSignatureSchemeVersion <= 1) {
                        return verifyV1Signature(apkPath, verifyFull);
                    } else {
                        throw new PackageParser.PackageParserException(-103, "No signature found in package of version " + minSignatureSchemeVersion + " or newer for package " + apkPath);
                    }
                }
            } else {
                throw new PackageParser.PackageParserException(-103, "No signature found in package of version " + minSignatureSchemeVersion + " or newer for package " + apkPath);
            }
        }
    }

    private static SigningDetailsWithDigests verifyV4Signature(String apkPath, int minSignatureSchemeVersion, boolean verifyFull) throws SignatureNotFoundException, PackageParser.PackageParserException {
        Map<Integer, byte[]> nonstreamingDigests;
        Certificate[][] nonstreamingCerts;
        Trace.traceBegin(262144L, verifyFull ? "verifyV4" : "certsOnlyV4");
        try {
            try {
                try {
                    ApkSignatureSchemeV4Verifier.VerifiedSigner vSigner = ApkSignatureSchemeV4Verifier.extractCertificates(apkPath);
                    Certificate[][] signerCerts = {vSigner.certs};
                    Signature[] signerSigs = convertToSignatures(signerCerts);
                    Signature[] pastSignerSigs = null;
                    if (verifyFull) {
                        try {
                            ApkSignatureSchemeV3Verifier.VerifiedSigner v3Signer = ApkSignatureSchemeV3Verifier.unsafeGetCertsWithoutVerification(apkPath);
                            nonstreamingDigests = v3Signer.contentDigests;
                            nonstreamingCerts = new Certificate[][]{v3Signer.certs};
                            if (v3Signer.por != null) {
                                pastSignerSigs = new Signature[v3Signer.por.certs.size()];
                                for (int i = 0; i < pastSignerSigs.length; i++) {
                                    pastSignerSigs[i] = new Signature(v3Signer.por.certs.get(i).getEncoded());
                                    pastSignerSigs[i].setFlags(v3Signer.por.flagsList.get(i).intValue());
                                }
                            }
                        } catch (SignatureNotFoundException e) {
                            try {
                                ApkSignatureSchemeV2Verifier.VerifiedSigner v2Signer = ApkSignatureSchemeV2Verifier.verify(apkPath, false);
                                nonstreamingDigests = v2Signer.contentDigests;
                                Certificate[][] nonstreamingCerts2 = v2Signer.certs;
                                nonstreamingCerts = nonstreamingCerts2;
                            } catch (SignatureNotFoundException ee) {
                                throw new SecurityException("V4 verification failed to collect V2/V3 certificates from : " + apkPath, ee);
                            }
                        }
                        Signature[] nonstreamingSigs = convertToSignatures(nonstreamingCerts);
                        if (nonstreamingSigs.length == signerSigs.length) {
                            int size = signerSigs.length;
                            for (int i2 = 0; i2 < size; i2++) {
                                if (!nonstreamingSigs[i2].equals(signerSigs[i2])) {
                                    throw new SecurityException("V4 signature certificate does not match V2/V3");
                                }
                            }
                            boolean found = false;
                            Iterator<byte[]> it = nonstreamingDigests.values().iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                byte[] nonstreamingDigest = it.next();
                                if (ArrayUtils.equals(vSigner.apkDigest, nonstreamingDigest, vSigner.apkDigest.length)) {
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                throw new SecurityException("APK digest in V4 signature does not match V2/V3");
                            }
                        } else {
                            throw new SecurityException("Invalid number of certificates: " + nonstreamingSigs.length);
                        }
                    }
                    return new SigningDetailsWithDigests(new PackageParser.SigningDetails(signerSigs, 4, pastSignerSigs), vSigner.contentDigests);
                } catch (Exception e2) {
                    throw new PackageParser.PackageParserException(-103, "Failed to collect certificates from " + apkPath + " using APK Signature Scheme v4", e2);
                }
            } catch (SignatureNotFoundException e3) {
                throw e3;
            }
        } finally {
            Trace.traceEnd(262144L);
        }
    }

    private static SigningDetailsWithDigests verifyV3Signature(String apkPath, boolean verifyFull) throws SignatureNotFoundException, PackageParser.PackageParserException {
        Trace.traceBegin(262144L, verifyFull ? "verifyV3" : "certsOnlyV3");
        try {
            try {
                ApkSignatureSchemeV3Verifier.VerifiedSigner vSigner = verifyFull ? ApkSignatureSchemeV3Verifier.verify(apkPath) : ApkSignatureSchemeV3Verifier.unsafeGetCertsWithoutVerification(apkPath);
                Certificate[][] signerCerts = {vSigner.certs};
                Signature[] signerSigs = convertToSignatures(signerCerts);
                Signature[] pastSignerSigs = null;
                if (vSigner.por != null) {
                    pastSignerSigs = new Signature[vSigner.por.certs.size()];
                    for (int i = 0; i < pastSignerSigs.length; i++) {
                        pastSignerSigs[i] = new Signature(vSigner.por.certs.get(i).getEncoded());
                        pastSignerSigs[i].setFlags(vSigner.por.flagsList.get(i).intValue());
                    }
                }
                return new SigningDetailsWithDigests(new PackageParser.SigningDetails(signerSigs, 3, pastSignerSigs), vSigner.contentDigests);
            } catch (SignatureNotFoundException e) {
                throw e;
            } catch (Exception e2) {
                throw new PackageParser.PackageParserException(-103, "Failed to collect certificates from " + apkPath + " using APK Signature Scheme v3", e2);
            }
        } finally {
            Trace.traceEnd(262144L);
        }
    }

    private static SigningDetailsWithDigests verifyV2Signature(String apkPath, boolean verifyFull) throws SignatureNotFoundException, PackageParser.PackageParserException {
        Trace.traceBegin(262144L, verifyFull ? "verifyV2" : "certsOnlyV2");
        try {
            try {
                ApkSignatureSchemeV2Verifier.VerifiedSigner vSigner = ApkSignatureSchemeV2Verifier.verify(apkPath, verifyFull);
                Certificate[][] signerCerts = vSigner.certs;
                Signature[] signerSigs = convertToSignatures(signerCerts);
                return new SigningDetailsWithDigests(new PackageParser.SigningDetails(signerSigs, 2), vSigner.contentDigests);
            } catch (SignatureNotFoundException e) {
                throw e;
            } catch (Exception e2) {
                throw new PackageParser.PackageParserException(-103, "Failed to collect certificates from " + apkPath + " using APK Signature Scheme v2", e2);
            }
        } finally {
            Trace.traceEnd(262144L);
        }
    }

    private static SigningDetailsWithDigests verifyV1Signature(String apkPath, boolean verifyFull) throws PackageParser.PackageParserException {
        if (verifyFull && NUMBER_OF_CORES > 1) {
            return oplusVerifyV1Signature(apkPath, verifyFull);
        }
        StrictJarFile jarFile = null;
        try {
            try {
                try {
                    Trace.traceBegin(262144L, "strictJarFileCtor");
                    jarFile = new StrictJarFile(apkPath, true, verifyFull);
                    List<ZipEntry> toVerify = new ArrayList<>();
                    ZipEntry manifestEntry = jarFile.findEntry("AndroidManifest.xml");
                    if (manifestEntry != null) {
                        Certificate[][] lastCerts = loadCertificates(jarFile, manifestEntry);
                        if (!ArrayUtils.isEmpty(lastCerts)) {
                            Signature[] lastSigs = convertToSignatures(lastCerts);
                            if (verifyFull) {
                                Iterator<ZipEntry> i = jarFile.iterator();
                                while (i.hasNext()) {
                                    ZipEntry entry = i.next();
                                    if (!entry.isDirectory()) {
                                        String entryName = entry.getName();
                                        if (!entryName.startsWith("META-INF/") && !entryName.equals("AndroidManifest.xml")) {
                                            toVerify.add(entry);
                                        }
                                    }
                                }
                                for (ZipEntry entry2 : toVerify) {
                                    Certificate[][] entryCerts = loadCertificates(jarFile, entry2);
                                    if (!ArrayUtils.isEmpty(entryCerts)) {
                                        Signature[] entrySigs = convertToSignatures(entryCerts);
                                        if (!Signature.areExactMatch(lastSigs, entrySigs)) {
                                            throw new PackageParser.PackageParserException(PackageManager.INSTALL_PARSE_FAILED_INCONSISTENT_CERTIFICATES, "Package " + apkPath + " has mismatched certificates at entry " + entry2.getName());
                                        }
                                    } else {
                                        throw new PackageParser.PackageParserException(-103, "Package " + apkPath + " has no certificates at entry " + entry2.getName());
                                    }
                                }
                            }
                            return new SigningDetailsWithDigests(new PackageParser.SigningDetails(lastSigs, 1), null);
                        }
                        throw new PackageParser.PackageParserException(-103, "Package " + apkPath + " has no certificates at entry AndroidManifest.xml");
                    }
                    throw new PackageParser.PackageParserException(-101, "Package " + apkPath + " has no manifest");
                } catch (IOException | RuntimeException e) {
                    throw new PackageParser.PackageParserException(-103, "Failed to collect certificates from " + apkPath, e);
                }
            } catch (GeneralSecurityException e2) {
                throw new PackageParser.PackageParserException(PackageManager.INSTALL_PARSE_FAILED_CERTIFICATE_ENCODING, "Failed to collect certificates from " + apkPath, e2);
            }
        } finally {
            Trace.traceEnd(262144L);
            closeQuietly(jarFile);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Certificate[][] loadCertificates(StrictJarFile jarFile, ZipEntry entry) throws PackageParser.PackageParserException {
        InputStream is = null;
        try {
            try {
                is = jarFile.getInputStream(entry);
                readFullyIgnoringContents(is);
                return jarFile.getCertificateChains(entry);
            } catch (IOException | RuntimeException e) {
                throw new PackageParser.PackageParserException(-102, "Failed reading " + entry.getName() + " in " + jarFile, e);
            }
        } finally {
            IoUtils.closeQuietly(is);
        }
    }

    private static void readFullyIgnoringContents(InputStream in) throws IOException {
        byte[] buffer = sBuffer.getAndSet(null);
        if (buffer == null) {
            buffer = new byte[4096];
        }
        int count = 0;
        while (true) {
            int n = in.read(buffer, 0, buffer.length);
            if (n != -1) {
                count += n;
            } else {
                sBuffer.set(buffer);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Signature[] convertToSignatures(Certificate[][] certs) throws CertificateEncodingException {
        Signature[] res = new Signature[certs.length];
        for (int i = 0; i < certs.length; i++) {
            res[i] = new Signature(certs[i]);
        }
        return res;
    }

    private static void closeQuietly(StrictJarFile jarFile) {
        if (jarFile != null) {
            try {
                jarFile.close();
            } catch (Exception e) {
            }
        }
    }

    public static int getMinimumSignatureSchemeVersionForTargetSdk(int targetSdk) {
        if (targetSdk < 30 || sOnScanDataApp) {
            return 1;
        }
        return 2;
    }

    /* loaded from: classes3.dex */
    public static class Result {
        public final Certificate[][] certs;
        public final int signatureSchemeVersion;
        public final Signature[] sigs;

        public Result(Certificate[][] certs, Signature[] sigs, int signingVersion) {
            this.certs = certs;
            this.sigs = sigs;
            this.signatureSchemeVersion = signingVersion;
        }
    }

    public static byte[] getVerityRootHash(String apkPath) throws IOException, SecurityException {
        try {
            return ApkSignatureSchemeV3Verifier.getVerityRootHash(apkPath);
        } catch (SignatureNotFoundException e) {
            try {
                return ApkSignatureSchemeV2Verifier.getVerityRootHash(apkPath);
            } catch (SignatureNotFoundException e2) {
                return null;
            }
        }
    }

    public static byte[] generateApkVerity(String apkPath, ByteBufferFactory bufferFactory) throws IOException, SignatureNotFoundException, SecurityException, DigestException, NoSuchAlgorithmException {
        try {
            return ApkSignatureSchemeV3Verifier.generateApkVerity(apkPath, bufferFactory);
        } catch (SignatureNotFoundException e) {
            return ApkSignatureSchemeV2Verifier.generateApkVerity(apkPath, bufferFactory);
        }
    }

    public static byte[] generateApkVerityRootHash(String apkPath) throws NoSuchAlgorithmException, DigestException, IOException {
        try {
            return ApkSignatureSchemeV3Verifier.generateApkVerityRootHash(apkPath);
        } catch (SignatureNotFoundException e) {
            try {
                return ApkSignatureSchemeV2Verifier.generateApkVerityRootHash(apkPath);
            } catch (SignatureNotFoundException e2) {
                return null;
            }
        }
    }

    /* loaded from: classes3.dex */
    public static class SigningDetailsWithDigests {
        public final Map<Integer, byte[]> contentDigests;
        public final PackageParser.SigningDetails signingDetails;

        SigningDetailsWithDigests(PackageParser.SigningDetails signingDetails, Map<Integer, byte[]> contentDigests) {
            this.signingDetails = signingDetails;
            this.contentDigests = contentDigests;
        }
    }

    private static SigningDetailsWithDigests oplusVerifyV1Signature(final String apkPath, boolean verifyFull) throws PackageParser.PackageParserException {
        Signature[] lastSigs;
        int objectNumber = verifyFull ? NUMBER_OF_CORES : 1;
        final StrictJarFile[] jarFile = new StrictJarFile[objectNumber];
        final ArrayMap<String, StrictJarFile> strictJarFiles = new ArrayMap<>();
        try {
            try {
                try {
                    Trace.traceBegin(262144L, "strictJarFileCtor");
                    for (int i = 0; i < objectNumber; i++) {
                        jarFile[i] = new StrictJarFile(apkPath, true, verifyFull);
                    }
                    List<ZipEntry> toVerify = new ArrayList<>();
                    ZipEntry manifestEntry = jarFile[0].findEntry("AndroidManifest.xml");
                    if (manifestEntry != null) {
                        Certificate[][] lastCerts = loadCertificates(jarFile[0], manifestEntry);
                        if (!ArrayUtils.isEmpty(lastCerts)) {
                            final Signature[] lastSigs2 = convertToSignatures(lastCerts);
                            if (verifyFull) {
                                Iterator<ZipEntry> i2 = jarFile[0].iterator();
                                while (i2.hasNext()) {
                                    ZipEntry entry = i2.next();
                                    if (!entry.isDirectory()) {
                                        String entryName = entry.getName();
                                        if (!entryName.startsWith("META-INF/") && !entryName.equals("AndroidManifest.xml")) {
                                            toVerify.add(entry);
                                        }
                                    }
                                }
                                final C1VerificationData vData = new C1VerificationData();
                                vData.objWaitAll = new Object();
                                int i3 = NUMBER_OF_CORES;
                                ThreadPoolExecutor verificationExecutor = new ThreadPoolExecutor(i3, i3, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue());
                                for (final ZipEntry entry2 : toVerify) {
                                    Runnable verifyTask = new Runnable() { // from class: android.util.apk.ApkSignatureVerifier.1
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            StrictJarFile tempJarFile;
                                            try {
                                                if (C1VerificationData.this.exceptionFlag != 0) {
                                                    Slog.w(ApkSignatureVerifier.TAG, "VerifyV1 exit with exception " + C1VerificationData.this.exceptionFlag);
                                                    return;
                                                }
                                                String tid = Long.toString(Thread.currentThread().getId());
                                                synchronized (strictJarFiles) {
                                                    tempJarFile = (StrictJarFile) strictJarFiles.get(tid);
                                                    if (tempJarFile == null) {
                                                        if (C1VerificationData.this.index >= ApkSignatureVerifier.NUMBER_OF_CORES) {
                                                            C1VerificationData.this.index = 0;
                                                        }
                                                        StrictJarFile[] strictJarFileArr = jarFile;
                                                        C1VerificationData r4 = C1VerificationData.this;
                                                        int i4 = r4.index;
                                                        r4.index = i4 + 1;
                                                        tempJarFile = strictJarFileArr[i4];
                                                        strictJarFiles.put(tid, tempJarFile);
                                                    }
                                                }
                                                Certificate[][] entryCerts = ApkSignatureVerifier.loadCertificates(tempJarFile, entry2);
                                                if (!ArrayUtils.isEmpty(entryCerts)) {
                                                    Signature[] entrySigs = ApkSignatureVerifier.convertToSignatures(entryCerts);
                                                    if (!Signature.areExactMatch(lastSigs2, entrySigs)) {
                                                        throw new PackageParser.PackageParserException(PackageManager.INSTALL_PARSE_FAILED_INCONSISTENT_CERTIFICATES, "Package " + apkPath + " has mismatched certificates at entry " + entry2.getName());
                                                    }
                                                    return;
                                                }
                                                throw new PackageParser.PackageParserException(-103, "Package " + apkPath + " has no certificates at entry " + entry2.getName());
                                            } catch (PackageParser.PackageParserException e) {
                                                synchronized (C1VerificationData.this.objWaitAll) {
                                                    C1VerificationData.this.exceptionFlag = -102;
                                                    C1VerificationData.this.exception = e;
                                                }
                                            } catch (GeneralSecurityException e2) {
                                                synchronized (C1VerificationData.this.objWaitAll) {
                                                    C1VerificationData.this.exceptionFlag = PackageManager.INSTALL_PARSE_FAILED_CERTIFICATE_ENCODING;
                                                    C1VerificationData.this.exception = e2;
                                                }
                                            }
                                        }
                                    };
                                    vData = vData;
                                    synchronized (vData.objWaitAll) {
                                        if (vData.exceptionFlag == 0) {
                                            verificationExecutor.execute(verifyTask);
                                        }
                                    }
                                    verificationExecutor = verificationExecutor;
                                    lastSigs2 = lastSigs2;
                                    manifestEntry = manifestEntry;
                                    toVerify = toVerify;
                                }
                                lastSigs = lastSigs2;
                                vData.wait = true;
                                verificationExecutor.shutdown();
                                while (vData.wait) {
                                    try {
                                        if (vData.exceptionFlag != 0 && !vData.shutDown) {
                                            Slog.w(TAG, "verifyV1 Exception " + vData.exceptionFlag);
                                            verificationExecutor.shutdownNow();
                                            vData.shutDown = true;
                                        }
                                        vData.wait = !verificationExecutor.awaitTermination(50L, TimeUnit.MILLISECONDS);
                                    } catch (InterruptedException e) {
                                        Slog.w(TAG, "VerifyV1 interrupted while awaiting all threads done...");
                                    }
                                }
                                if (vData.exceptionFlag != 0) {
                                    int i4 = vData.exceptionFlag;
                                    throw new PackageParser.PackageParserException(i4, "Failed to collect certificates from " + apkPath, vData.exception);
                                }
                            } else {
                                lastSigs = lastSigs2;
                            }
                            return new SigningDetailsWithDigests(new PackageParser.SigningDetails(lastSigs, 1), null);
                        }
                        throw new PackageParser.PackageParserException(-103, "Package " + apkPath + " has no certificates at entry AndroidManifest.xml");
                    }
                    throw new PackageParser.PackageParserException(-101, "Package " + apkPath + " has no manifest");
                } catch (GeneralSecurityException e2) {
                    throw new PackageParser.PackageParserException(PackageManager.INSTALL_PARSE_FAILED_CERTIFICATE_ENCODING, "Failed to collect certificates from " + apkPath, e2);
                }
            } catch (IOException | RuntimeException e3) {
                throw new PackageParser.PackageParserException(-103, "Failed to collect certificates from " + apkPath, e3);
            }
        } finally {
            strictJarFiles.clear();
            Trace.traceEnd(262144L);
            for (int i5 = 0; i5 < objectNumber; i5++) {
                closeQuietly(jarFile[i5]);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: android.util.apk.ApkSignatureVerifier$1VerificationData  reason: invalid class name */
    /* loaded from: classes3.dex */
    public class C1VerificationData {
        public Exception exception;
        public int exceptionFlag;
        public int index;
        public Object objWaitAll;
        public boolean shutDown;
        public boolean wait;

        C1VerificationData() {
        }
    }
}
