package android.content.pm.parsing;

import android.content.pm.PackageManager;
import android.content.pm.PackageParser;
import android.content.pm.VerifierInfo;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.res.ApkAssets;
import android.content.res.XmlResourceParser;
import android.os.Trace;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Pair;
import android.util.Slog;
import com.android.internal.util.ArrayUtils;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import libcore.io.IoUtils;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public class ApkLiteParseUtils {
    public static final String APK_FILE_EXTENSION = ".apk";
    private static final int PARSE_DEFAULT_INSTALL_LOCATION = -1;
    private static final String TAG = "PackageParsing";
    private static final Comparator<String> sSplitNameComparator = new SplitNameComparator();

    public static ParseResult<PackageLite> parsePackageLite(ParseInput input, File packageFile, int flags) {
        if (packageFile.isDirectory()) {
            return parseClusterPackageLite(input, packageFile, flags);
        }
        return parseMonolithicPackageLite(input, packageFile, flags);
    }

    public static ParseResult<PackageLite> parseMonolithicPackageLite(ParseInput input, File packageFile, int flags) {
        Trace.traceBegin(262144L, "parseApkLite");
        try {
            ParseResult<ApkLite> result = parseApkLite(input, packageFile, flags);
            if (result.isError()) {
                return input.error(result);
            }
            ApkLite baseApk = result.getResult();
            String packagePath = packageFile.getAbsolutePath();
            return input.success(new PackageLite(packagePath, baseApk.getPath(), baseApk, null, null, null, null, null, null, baseApk.getTargetSdkVersion()));
        } finally {
            Trace.traceEnd(262144L);
        }
    }

    public static ParseResult<PackageLite> parseClusterPackageLite(ParseInput input, File packageDir, int flags) {
        File[] files = packageDir.listFiles();
        if (ArrayUtils.isEmpty(files)) {
            return input.error(-100, "No packages found in split");
        }
        int i = 0;
        if (files.length == 1 && files[0].isDirectory()) {
            return parseClusterPackageLite(input, files[0], flags);
        }
        String packageName = null;
        int versionCode = 0;
        ArrayMap<String, ApkLite> apks = new ArrayMap<>();
        long j = 262144;
        Trace.traceBegin(262144L, "parseApkLite");
        try {
            int length = files.length;
            while (i < length) {
                File file = files[i];
                if (isApkFile(file)) {
                    ParseResult<ApkLite> result = parseApkLite(input, file, flags);
                    if (result.isError()) {
                        ParseResult<PackageLite> error = input.error(result);
                        Trace.traceEnd(j);
                        return error;
                    }
                    ApkLite lite = result.getResult();
                    if (packageName == null) {
                        packageName = lite.getPackageName();
                        versionCode = lite.getVersionCode();
                    } else if (!packageName.equals(lite.getPackageName())) {
                        return input.error(-101, "Inconsistent package " + lite.getPackageName() + " in " + file + "; expected " + packageName);
                    } else if (versionCode != lite.getVersionCode()) {
                        return input.error(-101, "Inconsistent version " + lite.getVersionCode() + " in " + file + "; expected " + versionCode);
                    }
                    if (apks.put(lite.getSplitName(), lite) != null) {
                        return input.error(-101, "Split name " + lite.getSplitName() + " defined more than once; most recent was " + file);
                    }
                }
                i++;
                j = 262144;
            }
            Trace.traceEnd(262144L);
            ApkLite baseApk = apks.remove(null);
            return composePackageLiteFromApks(input, packageDir, baseApk, apks);
        } finally {
            Trace.traceEnd(262144L);
        }
    }

    public static ParseResult<PackageLite> composePackageLiteFromApks(ParseInput input, File packageDir, ApkLite baseApk, ArrayMap<String, ApkLite> splitApks) {
        return composePackageLiteFromApks(input, packageDir, baseApk, splitApks, false);
    }

    public static ParseResult<PackageLite> composePackageLiteFromApks(ParseInput input, File packageDir, ApkLite baseApk, ArrayMap<String, ApkLite> splitApks, boolean apkRenamed) {
        int[] splitRevisionCodes;
        String[] splitCodePaths;
        String[] configForSplits;
        String[] usesSplitNames;
        boolean[] isFeatureSplits;
        String[] splitNames;
        if (baseApk == null) {
            return input.error(-101, "Missing base APK in " + packageDir);
        }
        int size = ArrayUtils.size(splitApks);
        if (size > 0) {
            String[] splitNames2 = new String[size];
            boolean[] isFeatureSplits2 = new boolean[size];
            String[] usesSplitNames2 = new String[size];
            String[] configForSplits2 = new String[size];
            String[] splitCodePaths2 = new String[size];
            int[] splitRevisionCodes2 = new int[size];
            String[] splitNames3 = (String[]) splitApks.keySet().toArray(splitNames2);
            Arrays.sort(splitNames3, sSplitNameComparator);
            for (int i = 0; i < size; i++) {
                ApkLite apk = splitApks.get(splitNames3[i]);
                usesSplitNames2[i] = apk.getUsesSplitName();
                isFeatureSplits2[i] = apk.isFeatureSplit();
                configForSplits2[i] = apk.getConfigForSplit();
                splitCodePaths2[i] = apkRenamed ? new File(packageDir, splitNameToFileName(apk)).getAbsolutePath() : apk.getPath();
                splitRevisionCodes2[i] = apk.getRevisionCode();
            }
            splitNames = splitNames3;
            isFeatureSplits = isFeatureSplits2;
            usesSplitNames = usesSplitNames2;
            configForSplits = configForSplits2;
            splitCodePaths = splitCodePaths2;
            splitRevisionCodes = splitRevisionCodes2;
        } else {
            splitNames = null;
            isFeatureSplits = null;
            usesSplitNames = null;
            configForSplits = null;
            splitCodePaths = null;
            splitRevisionCodes = null;
        }
        String codePath = packageDir.getAbsolutePath();
        String baseCodePath = apkRenamed ? new File(packageDir, splitNameToFileName(baseApk)).getAbsolutePath() : baseApk.getPath();
        return input.success(new PackageLite(codePath, baseCodePath, baseApk, splitNames, isFeatureSplits, usesSplitNames, configForSplits, splitCodePaths, splitRevisionCodes, baseApk.getTargetSdkVersion()));
    }

    public static String splitNameToFileName(ApkLite apk) {
        String fileName;
        Objects.requireNonNull(apk);
        if (apk.getSplitName() == null) {
            fileName = "base";
        } else {
            fileName = "split_" + apk.getSplitName();
        }
        return fileName + ".apk";
    }

    public static ParseResult<ApkLite> parseApkLite(ParseInput input, File apkFile, int flags) {
        return parseApkLiteInner(input, apkFile, null, null, flags);
    }

    public static ParseResult<ApkLite> parseApkLite(ParseInput input, FileDescriptor fd, String debugPathName, int flags) {
        return parseApkLiteInner(input, null, fd, debugPathName, flags);
    }

    private static ParseResult<ApkLite> parseApkLiteInner(ParseInput input, File apkFile, FileDescriptor fd, String debugPathName, int flags) {
        Exception e;
        Exception e2;
        ApkAssets apkAssets;
        XmlResourceParser parser;
        PackageParser.SigningDetails signingDetails;
        String apkPath = fd != null ? debugPathName : apkFile.getAbsolutePath();
        XmlResourceParser parser2 = null;
        ApkAssets apkAssets2 = null;
        boolean skipVerify = false;
        try {
            try {
                try {
                    apkAssets = fd != null ? ApkAssets.loadFromFd(fd, debugPathName, 0, null) : ApkAssets.loadFromPath(apkPath);
                    try {
                        parser = apkAssets.openXml("AndroidManifest.xml");
                    } catch (IOException | RuntimeException | XmlPullParserException e3) {
                        e2 = e3;
                        apkAssets2 = apkAssets;
                    } catch (Throwable th) {
                        e = th;
                        apkAssets2 = apkAssets;
                    }
                } catch (IOException | RuntimeException | XmlPullParserException e4) {
                    e2 = e4;
                }
            } catch (IOException e5) {
                ParseResult<ApkLite> error = input.error(-100, "Failed to parse " + apkPath, e5);
                IoUtils.closeQuietly((AutoCloseable) null);
                if (0 != 0) {
                    try {
                        apkAssets2.close();
                    } catch (Throwable th2) {
                    }
                }
                return error;
            }
        } catch (Throwable th3) {
            e = th3;
        }
        try {
            if ((flags & 32) != 0) {
                if ((flags & 16) != 0) {
                    skipVerify = true;
                }
                Trace.traceBegin(262144L, "collectCertificates");
                try {
                    ParseResult<PackageParser.SigningDetails> result = ParsingPackageUtils.getSigningDetails(input, apkFile.getAbsolutePath(), skipVerify, false, PackageParser.SigningDetails.UNKNOWN, 0);
                    if (result.isError()) {
                        ParseResult<ApkLite> error2 = input.error(result);
                        IoUtils.closeQuietly(parser);
                        if (apkAssets != null) {
                            try {
                                apkAssets.close();
                            } catch (Throwable th4) {
                            }
                        }
                        return error2;
                    }
                    PackageParser.SigningDetails signingDetails2 = result.getResult();
                    Trace.traceEnd(262144L);
                    signingDetails = signingDetails2;
                } finally {
                    Trace.traceEnd(262144L);
                }
            } else {
                signingDetails = PackageParser.SigningDetails.UNKNOWN;
            }
            ParseResult<ApkLite> parseApkLite = parseApkLite(input, apkPath, parser, signingDetails);
            IoUtils.closeQuietly(parser);
            if (apkAssets != null) {
                try {
                    apkAssets.close();
                } catch (Throwable th5) {
                }
            }
            return parseApkLite;
        } catch (IOException | RuntimeException | XmlPullParserException e6) {
            e2 = e6;
            apkAssets2 = apkAssets;
            parser2 = parser;
            Slog.w("PackageParsing", "Failed to parse " + apkPath, e2);
            ParseResult<ApkLite> error3 = input.error(-102, "Failed to parse " + apkPath, e2);
            IoUtils.closeQuietly(parser2);
            if (apkAssets2 != null) {
                try {
                    apkAssets2.close();
                } catch (Throwable th6) {
                }
            }
            return error3;
        } catch (Throwable th7) {
            e = th7;
            apkAssets2 = apkAssets;
            parser2 = parser;
            IoUtils.closeQuietly(parser2);
            if (apkAssets2 != null) {
                try {
                    apkAssets2.close();
                } catch (Throwable th8) {
                }
            }
            throw e;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:90:0x0286, code lost:
        if (android.content.pm.PackageParser.checkRequiredSystemProperties(r15, r14) != false) goto L_0x02c1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0288, code lost:
        android.util.Slog.i("PackageParsing", "Skipping target and overlay pair " + r8 + " and " + r60 + ": overlay ignored due to required system property: " + r15 + " with value: " + r14);
        r0 = null;
        r3 = false;
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x02c1, code lost:
        r0 = r8;
        r3 = r17;
        r5 = r18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0317, code lost:
        return r59.success(new android.content.pm.parsing.ApkLite(r60, r4.first, r4.second, r40, r42, r53, r41, r35, r36, r37, r6, r12, r62, r38, r47, r48, r49, r50, r52, r51, r39, r0, r3, r5, r46, r45, r54));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.content.pm.parsing.result.ParseResult<android.content.pm.parsing.ApkLite> parseApkLite(android.content.pm.parsing.result.ParseInput r59, java.lang.String r60, android.content.res.XmlResourceParser r61, android.content.pm.PackageParser.SigningDetails r62) throws java.io.IOException, org.xmlpull.v1.XmlPullParserException {
        /*
            Method dump skipped, instructions count: 792
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.parsing.ApkLiteParseUtils.parseApkLite(android.content.pm.parsing.result.ParseInput, java.lang.String, android.content.res.XmlResourceParser, android.content.pm.PackageParser$SigningDetails):android.content.pm.parsing.result.ParseResult");
    }

    public static ParseResult<Pair<String, String>> parsePackageSplitNames(ParseInput input, XmlResourceParser parser) throws IOException, XmlPullParserException {
        int type;
        do {
            type = parser.next();
            if (type == 2) {
                break;
            }
        } while (type != 1);
        if (type != 2) {
            return input.error(PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED, "No start tag found");
        }
        if (!parser.getName().equals("manifest")) {
            return input.error(PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED, "No <manifest> tag");
        }
        String packageName = parser.getAttributeValue(null, "package");
        if (!"android".equals(packageName) && !"oplus".equals(packageName)) {
            ParseResult<?> nameResult = ParsingPackageUtils.validateName(input, packageName, true, true);
            if (nameResult.isError()) {
                return input.error(PackageManager.INSTALL_PARSE_FAILED_BAD_PACKAGE_NAME, "Invalid manifest package: " + nameResult.getErrorMessage());
            }
        }
        String splitName = parser.getAttributeValue(null, "split");
        if (splitName != null) {
            if (splitName.length() == 0) {
                splitName = null;
            } else {
                ParseResult<?> nameResult2 = ParsingPackageUtils.validateName(input, splitName, false, false);
                if (nameResult2.isError()) {
                    return input.error(PackageManager.INSTALL_PARSE_FAILED_BAD_PACKAGE_NAME, "Invalid manifest split: " + nameResult2.getErrorMessage());
                }
            }
        }
        return input.success(Pair.create(packageName.intern(), splitName != null ? splitName.intern() : splitName));
    }

    public static VerifierInfo parseVerifier(AttributeSet attrs) {
        String packageName = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "name");
        String encodedPublicKey = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "publicKey");
        if (packageName == null || packageName.length() == 0) {
            Slog.i("PackageParsing", "verifier package name was null; skipping");
            return null;
        }
        PublicKey publicKey = PackageParser.parsePublicKey(encodedPublicKey);
        if (publicKey != null) {
            return new VerifierInfo(packageName, publicKey);
        }
        Slog.i("PackageParsing", "Unable to parse verifier public key for " + packageName);
        return null;
    }

    /* loaded from: classes.dex */
    private static class SplitNameComparator implements Comparator<String> {
        private SplitNameComparator() {
        }

        public int compare(String lhs, String rhs) {
            if (lhs == null) {
                return -1;
            }
            if (rhs == null) {
                return 1;
            }
            return lhs.compareTo(rhs);
        }
    }

    public static boolean isApkFile(File file) {
        return isApkPath(file.getName());
    }

    public static boolean isApkPath(String path) {
        return path.endsWith(".apk");
    }
}
