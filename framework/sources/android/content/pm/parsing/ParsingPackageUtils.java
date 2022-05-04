package android.content.pm.parsing;

import android.app.ActivityThread;
import android.app.ResourcesManager;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.ConfigurationInfo;
import android.content.pm.FeatureGroupInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageParser;
import android.content.pm.Signature;
import android.content.pm.parsing.component.ComponentParseUtils;
import android.content.pm.parsing.component.ParsedActivity;
import android.content.pm.parsing.component.ParsedAttribution;
import android.content.pm.parsing.component.ParsedAttributionUtils;
import android.content.pm.parsing.component.ParsedComponent;
import android.content.pm.parsing.component.ParsedInstrumentation;
import android.content.pm.parsing.component.ParsedInstrumentationUtils;
import android.content.pm.parsing.component.ParsedIntentInfo;
import android.content.pm.parsing.component.ParsedPermission;
import android.content.pm.parsing.component.ParsedPermissionGroup;
import android.content.pm.parsing.component.ParsedPermissionUtils;
import android.content.pm.parsing.component.ParsedProcess;
import android.content.pm.parsing.component.ParsedProcessUtils;
import android.content.pm.parsing.component.ParsedUsesPermission;
import android.content.pm.parsing.result.ParseInput;
import android.content.pm.parsing.result.ParseResult;
import android.content.pm.parsing.result.ParseTypeImpl;
import android.content.pm.split.DefaultSplitAssetLoader;
import android.content.pm.split.SplitAssetDependencyLoader;
import android.content.pm.split.SplitAssetLoader;
import android.content.pm.split.SplitDependencyLoader;
import android.content.res.ApkAssets;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.Trace;
import android.os.UserHandle;
import android.os.ext.SdkExtensions;
import android.permission.PermissionManager;
import android.provider.SettingsStringUtil;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.util.Slog;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.util.apk.ApkSignatureVerifier;
import com.android.internal.R;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.XmlUtils;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import libcore.io.IoUtils;
import libcore.util.EmptyArray;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public class ParsingPackageUtils {
    public static final String ANDROID_MANIFEST_FILENAME = "AndroidManifest.xml";
    public static final boolean DEBUG_BACKUP = false;
    public static final boolean DEBUG_JAR = false;
    public static final float DEFAULT_PRE_O_MAX_ASPECT_RATIO = 1.86f;
    private static final int MAX_FILE_NAME_SIZE = 223;
    public static final String METADATA_ACTIVITY_LAUNCH_MODE = "android.activity.launch_mode";
    public static final String METADATA_ACTIVITY_WINDOW_LAYOUT_AFFINITY = "android.activity_window_layout_affinity";
    public static final String METADATA_MAX_ASPECT_RATIO = "android.max_aspect";
    public static final String METADATA_SUPPORTS_SIZE_CHANGES = "android.supports_size_changes";
    public static final String MNT_EXPAND = "/mnt/expand/";
    public static final int PARSE_CHATTY = Integer.MIN_VALUE;
    public static final int PARSE_COLLECT_CERTIFICATES = 32;
    public static final int PARSE_DEFAULT_INSTALL_LOCATION = -1;
    public static final int PARSE_DEFAULT_TARGET_SANDBOX = 1;
    public static final int PARSE_ENFORCE_CODE = 64;
    public static final int PARSE_EXTERNAL_STORAGE = 8;
    public static final int PARSE_IGNORE_PROCESSES = 2;
    public static final int PARSE_IS_SYSTEM_DIR = 16;
    public static final int PARSE_MUST_BE_APK = 1;
    public static final boolean RIGID_PARSER = false;
    private static final String TAG = "PackageParsing";
    public static final String TAG_ADOPT_PERMISSIONS = "adopt-permissions";
    public static final String TAG_APPLICATION = "application";
    public static final String TAG_ATTRIBUTION = "attribution";
    public static final String TAG_COMPATIBLE_SCREENS = "compatible-screens";
    public static final String TAG_EAT_COMMENT = "eat-comment";
    public static final String TAG_FEATURE_GROUP = "feature-group";
    public static final String TAG_INSTRUMENTATION = "instrumentation";
    public static final String TAG_KEY_SETS = "key-sets";
    public static final String TAG_MANIFEST = "manifest";
    public static final String TAG_ORIGINAL_PACKAGE = "original-package";
    public static final String TAG_OVERLAY = "overlay";
    public static final String TAG_PACKAGE = "package";
    public static final String TAG_PACKAGE_VERIFIER = "package-verifier";
    public static final String TAG_PERMISSION = "permission";
    public static final String TAG_PERMISSION_GROUP = "permission-group";
    public static final String TAG_PERMISSION_TREE = "permission-tree";
    public static final String TAG_PROFILEABLE = "profileable";
    public static final String TAG_PROTECTED_BROADCAST = "protected-broadcast";
    public static final String TAG_QUERIES = "queries";
    public static final String TAG_RESTRICT_UPDATE = "restrict-update";
    public static final String TAG_SUPPORTS_INPUT = "supports-input";
    public static final String TAG_SUPPORT_SCREENS = "supports-screens";
    public static final String TAG_USES_CONFIGURATION = "uses-configuration";
    public static final String TAG_USES_FEATURE = "uses-feature";
    public static final String TAG_USES_GL_TEXTURE = "uses-gl-texture";
    public static final String TAG_USES_PERMISSION = "uses-permission";
    public static final String TAG_USES_PERMISSION_SDK_23 = "uses-permission-sdk-23";
    public static final String TAG_USES_PERMISSION_SDK_M = "uses-permission-sdk-m";
    public static final String TAG_USES_SDK = "uses-sdk";
    public static final String TAG_USES_SPLIT = "uses-split";
    private Callback mCallback;
    private DisplayMetrics mDisplayMetrics;
    private boolean mOnlyCoreApps;
    private String[] mSeparateProcesses;
    private List<PermissionManager.SplitPermissionInfo> mSplitPermissionInfos;
    public static final int SDK_VERSION = Build.VERSION.SDK_INT;
    public static final String[] SDK_CODENAMES = Build.VERSION.ACTIVE_CODENAMES;
    public static boolean sCompatibilityModeEnabled = true;
    public static boolean sUseRoundIcon = false;

    /* loaded from: classes.dex */
    public interface Callback {
        boolean hasFeature(String str);

        ParsingPackage startParsingPackage(String str, String str2, String str3, TypedArray typedArray, boolean z);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ParseFlags {
    }

    public static ParseResult<ParsingPackage> parseDefaultOneTime(File file, int parseFlags, List<PermissionManager.SplitPermissionInfo> splitPermissions, boolean collectCertificates) {
        ParseInput input = ParseTypeImpl.forDefaultParsing().reset();
        return parseDefault(input, file, parseFlags, splitPermissions, collectCertificates);
    }

    public static ParseResult<ParsingPackage> parseDefault(ParseInput input, File file, int parseFlags, List<PermissionManager.SplitPermissionInfo> splitPermissions, boolean collectCertificates) {
        ParsingPackageUtils parser = new ParsingPackageUtils(false, null, null, splitPermissions, new Callback() { // from class: android.content.pm.parsing.ParsingPackageUtils.1
            @Override // android.content.pm.parsing.ParsingPackageUtils.Callback
            public boolean hasFeature(String feature) {
                return false;
            }

            @Override // android.content.pm.parsing.ParsingPackageUtils.Callback
            public ParsingPackage startParsingPackage(String packageName, String baseApkPath, String path, TypedArray manifestArray, boolean isCoreApp) {
                return new ParsingPackageImpl(packageName, baseApkPath, path, manifestArray);
            }
        });
        try {
            ParseResult<ParsingPackage> result = parser.parsePackage(input, file, parseFlags);
            if (result.isError()) {
                return result;
            }
            try {
                ParsingPackage pkg = result.getResult();
                if (collectCertificates) {
                    pkg.setSigningDetails(getSigningDetails(pkg, false));
                }
                pkg.hideAsParsed();
                return input.success(pkg);
            } catch (PackageParser.PackageParserException e) {
                return input.error(-102, "Error collecting package certificates", e);
            }
        } catch (PackageParser.PackageParserException e2) {
            return input.error(-102, "Error parsing package", e2);
        }
    }

    public ParsingPackageUtils(boolean onlyCoreApps, String[] separateProcesses, DisplayMetrics displayMetrics, List<PermissionManager.SplitPermissionInfo> splitPermissions, Callback callback) {
        this.mOnlyCoreApps = onlyCoreApps;
        this.mSeparateProcesses = separateProcesses;
        this.mDisplayMetrics = displayMetrics;
        this.mSplitPermissionInfos = splitPermissions;
        this.mCallback = callback;
    }

    public ParseResult<ParsingPackage> parsePackage(ParseInput input, File packageFile, int flags) throws PackageParser.PackageParserException {
        if (packageFile.isDirectory()) {
            return parseClusterPackage(input, packageFile, flags);
        }
        return parseMonolithicPackage(input, packageFile, flags);
    }

    private ParseResult<ParsingPackage> parseClusterPackage(ParseInput input, File packageDir, int flags) {
        SplitAssetLoader assetLoader;
        SparseArray<int[]> splitDependencies;
        SplitAssetLoader assetLoader2;
        PackageParser.PackageParserException e;
        PackageParser.PackageParserException e2;
        ParseResult<PackageLite> liteResult = ApkLiteParseUtils.parseClusterPackageLite(input, packageDir, 0);
        if (liteResult.isError()) {
            return input.error(liteResult);
        }
        PackageLite lite = liteResult.getResult();
        if (!this.mOnlyCoreApps || lite.isCoreApp()) {
            if (!lite.isIsolatedSplits() || ArrayUtils.isEmpty(lite.getSplitNames())) {
                assetLoader = new DefaultSplitAssetLoader(lite, flags);
                splitDependencies = null;
            } else {
                try {
                    SparseArray<int[]> splitDependencies2 = SplitAssetDependencyLoader.createDependenciesFromPackage(lite);
                    assetLoader = new SplitAssetDependencyLoader(lite, splitDependencies2, flags);
                    splitDependencies = splitDependencies2;
                } catch (SplitDependencyLoader.IllegalDependencyException e3) {
                    return input.error(-101, e3.getMessage());
                }
            }
            try {
                File baseApk = new File(lite.getBaseApkPath());
                ParseResult<ParsingPackage> result = parseBaseApk(input, baseApk, lite.getPath(), assetLoader, flags);
                if (result.isError()) {
                    try {
                        ParseResult<ParsingPackage> error = input.error(result);
                        IoUtils.closeQuietly(assetLoader);
                        return error;
                    } catch (PackageParser.PackageParserException e4) {
                        e2 = e4;
                        assetLoader2 = assetLoader;
                    } catch (Throwable th) {
                        e = th;
                        assetLoader2 = assetLoader;
                        IoUtils.closeQuietly(assetLoader2);
                        throw e;
                    }
                } else {
                    ParsingPackage pkg = result.getResult();
                    if (!ArrayUtils.isEmpty(lite.getSplitNames())) {
                        pkg.asSplit(lite.getSplitNames(), lite.getSplitApkPaths(), lite.getSplitRevisionCodes(), splitDependencies);
                        int num = lite.getSplitNames().length;
                        int i = 0;
                        while (i < num) {
                            AssetManager splitAssets = assetLoader.getSplitAssetManager(i);
                            assetLoader2 = assetLoader;
                            try {
                                try {
                                    parseSplitApk(input, pkg, i, splitAssets, flags);
                                    i++;
                                    splitDependencies = splitDependencies;
                                    assetLoader = assetLoader2;
                                } catch (PackageParser.PackageParserException e5) {
                                    e2 = e5;
                                }
                            } catch (Throwable th2) {
                                e = th2;
                                IoUtils.closeQuietly(assetLoader2);
                                throw e;
                            }
                        }
                        assetLoader2 = assetLoader;
                    } else {
                        assetLoader2 = assetLoader;
                    }
                    pkg.setUse32BitAbi(lite.isUse32bitAbi());
                    ParseResult<ParsingPackage> success = input.success(pkg);
                    IoUtils.closeQuietly(assetLoader2);
                    return success;
                }
            } catch (PackageParser.PackageParserException e6) {
                e2 = e6;
                assetLoader2 = assetLoader;
            } catch (Throwable th3) {
                e = th3;
                assetLoader2 = assetLoader;
            }
            ParseResult<ParsingPackage> error2 = input.error(-102, "Failed to load assets: " + lite.getBaseApkPath(), e2);
            IoUtils.closeQuietly(assetLoader2);
            return error2;
        }
        return input.error(PackageManager.INSTALL_PARSE_FAILED_ONLY_COREAPP_ALLOWED, "Not a coreApp: " + packageDir);
    }

    private ParseResult<ParsingPackage> parseMonolithicPackage(ParseInput input, File apkFile, int flags) throws PackageParser.PackageParserException {
        ParseResult<PackageLite> liteResult = ApkLiteParseUtils.parseMonolithicPackageLite(input, apkFile, flags);
        if (liteResult.isError()) {
            return input.error(liteResult);
        }
        PackageLite lite = liteResult.getResult();
        if (!this.mOnlyCoreApps || lite.isCoreApp()) {
            SplitAssetLoader assetLoader = new DefaultSplitAssetLoader(lite, flags);
            try {
                ParseResult<ParsingPackage> result = parseBaseApk(input, apkFile, apkFile.getCanonicalPath(), assetLoader, flags);
                if (result.isError()) {
                    return input.error(result);
                }
                return input.success(result.getResult().setUse32BitAbi(lite.isUse32bitAbi()));
            } catch (IOException e) {
                return input.error(-102, "Failed to get path: " + apkFile, e);
            } finally {
                IoUtils.closeQuietly(assetLoader);
            }
        } else {
            return input.error(PackageManager.INSTALL_PARSE_FAILED_ONLY_COREAPP_ALLOWED, "Not a coreApp: " + apkFile);
        }
    }

    private ParseResult<ParsingPackage> parseBaseApk(ParseInput input, File apkFile, String codePath, SplitAssetLoader assetLoader, int flags) throws PackageParser.PackageParserException {
        String volumeUuid;
        Exception e;
        XmlResourceParser parser;
        Throwable th;
        ParseResult<ParsingPackage> result;
        String apkPath = apkFile.getAbsolutePath();
        if (apkPath.startsWith("/mnt/expand/")) {
            int end = apkPath.indexOf(47, "/mnt/expand/".length());
            String volumeUuid2 = apkPath.substring("/mnt/expand/".length(), end);
            volumeUuid = volumeUuid2;
        } else {
            volumeUuid = null;
        }
        AssetManager assets = assetLoader.getBaseAssetManager();
        int cookie = assets.findCookieForPath(apkPath);
        if (cookie == 0) {
            return input.error(-101, "Failed adding asset path: " + apkPath);
        }
        try {
            parser = assets.openXmlResourceParser(cookie, "AndroidManifest.xml");
        } catch (Exception e2) {
            e = e2;
        }
        try {
            try {
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                Resources res = new Resources(assets, this.mDisplayMetrics, null);
                ParseResult<ParsingPackage> result2 = parseBaseApk(input, apkPath, codePath, res, parser, flags);
                if (result2.isError()) {
                    ParseResult<ParsingPackage> error = input.error(result2.getErrorCode(), apkPath + " (at " + parser.getPositionDescription() + "): " + result2.getErrorMessage());
                    if (parser != null) {
                        parser.close();
                    }
                    return error;
                }
                ParsingPackage pkg = result2.getResult();
                if (assets.containsAllocatedTable()) {
                    ParseResult<?> deferResult = input.deferError("Targeting R+ (version 30 and above) requires the resources.arsc of installed APKs to be stored uncompressed and aligned on a 4-byte boundary", ParseInput.DeferredError.RESOURCES_ARSC_COMPRESSED);
                    if (deferResult.isError()) {
                        ParseResult<ParsingPackage> error2 = input.error(PackageManager.INSTALL_PARSE_FAILED_RESOURCES_ARSC_COMPRESSED, deferResult.getErrorMessage());
                        if (parser != null) {
                            parser.close();
                        }
                        return error2;
                    }
                }
                ApkAssets apkAssets = assetLoader.getBaseApkAssets();
                boolean definesOverlayable = false;
                try {
                    definesOverlayable = apkAssets.definesOverlayable();
                } catch (IOException e3) {
                }
                if (definesOverlayable) {
                    SparseArray<String> packageNames = assets.getAssignedPackageIdentifiers();
                    int size = packageNames.size();
                    int index = 0;
                    while (index < size) {
                        String packageName = packageNames.valueAt(index);
                        Map<String, String> overlayableToActor = assets.getOverlayableMap(packageName);
                        if (overlayableToActor == null || overlayableToActor.isEmpty()) {
                            result = result2;
                        } else {
                            for (String overlayable : overlayableToActor.keySet()) {
                                pkg.addOverlayable(overlayable, overlayableToActor.get(overlayable));
                                result2 = result2;
                                overlayableToActor = overlayableToActor;
                            }
                            result = result2;
                        }
                        index++;
                        packageNames = packageNames;
                        result2 = result;
                    }
                }
                pkg.setVolumeUuid(volumeUuid);
                if ((flags & 32) != 0) {
                    pkg.setSigningDetails(getSigningDetails(pkg, false));
                } else {
                    pkg.setSigningDetails(PackageParser.SigningDetails.UNKNOWN);
                }
                ParseResult<ParsingPackage> success = input.success(pkg);
                if (parser != null) {
                    parser.close();
                }
                return success;
            } catch (Throwable th3) {
                th = th3;
                if (parser != null) {
                    parser.close();
                }
                throw th;
            }
        } catch (Exception e4) {
            e = e4;
            return input.error(-102, "Failed to read manifest from " + apkPath, e);
        }
    }

    private ParseResult<ParsingPackage> parseSplitApk(ParseInput input, ParsingPackage pkg, int splitIndex, AssetManager assets, int flags) {
        String apkPath = pkg.getSplitCodePaths()[splitIndex];
        int cookie = assets.findCookieForPath(apkPath);
        if (cookie == 0) {
            return input.error(-101, "Failed adding asset path: " + apkPath);
        }
        try {
            XmlResourceParser parser = assets.openXmlResourceParser(cookie, "AndroidManifest.xml");
            Resources res = new Resources(assets, this.mDisplayMetrics, null);
            ParseResult<ParsingPackage> parseResult = parseSplitApk(input, pkg, res, parser, flags, splitIndex);
            if (parseResult.isError()) {
                int errorCode = parseResult.getErrorCode();
                ParseResult<ParsingPackage> error = input.error(errorCode, apkPath + " (at " + parser.getPositionDescription() + "): " + parseResult.getErrorMessage());
                if (parser != null) {
                    parser.close();
                }
                return error;
            }
            if (parser != null) {
                parser.close();
            }
            return parseResult;
        } catch (Exception e) {
            return input.error(-102, "Failed to read manifest from " + apkPath, e);
        }
    }

    private ParseResult<ParsingPackage> parseBaseApk(ParseInput input, String apkPath, String codePath, Resources res, XmlResourceParser parser, int flags) throws XmlPullParserException, IOException {
        Throwable th;
        ParseResult<Pair<String, String>> packageSplitResult = ApkLiteParseUtils.parsePackageSplitNames(input, parser);
        if (packageSplitResult.isError()) {
            return input.error(packageSplitResult);
        }
        Pair<String, String> packageSplit = packageSplitResult.getResult();
        String pkgName = packageSplit.first;
        String splitName = packageSplit.second;
        if (!TextUtils.isEmpty(splitName)) {
            return input.error(PackageManager.INSTALL_PARSE_FAILED_BAD_PACKAGE_NAME, "Expected base APK, but found split " + splitName);
        }
        TypedArray manifestArray = res.obtainAttributes(parser, R.styleable.AndroidManifest);
        try {
            boolean isCoreApp = parser.getAttributeBooleanValue(null, "coreApp", false);
            ParsingPackage pkg = this.mCallback.startParsingPackage(pkgName, apkPath, codePath, manifestArray, isCoreApp);
            try {
                ParseResult<ParsingPackage> result = parseBaseApkTags(input, pkg, manifestArray, res, parser, flags);
                if (result.isError()) {
                    manifestArray.recycle();
                    return result;
                }
                ParseResult<ParsingPackage> success = input.success(pkg);
                manifestArray.recycle();
                return success;
            } catch (Throwable th2) {
                th = th2;
                manifestArray.recycle();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    private ParseResult<ParsingPackage> parseSplitApk(ParseInput input, ParsingPackage pkg, Resources res, XmlResourceParser parser, int flags, int splitIndex) throws XmlPullParserException, IOException, PackageParser.PackageParserException {
        ParseResult result;
        PackageParser.parsePackageSplitNames(parser, parser);
        boolean foundApp = false;
        int outerDepth = parser.getDepth();
        while (true) {
            int type = parser.next();
            if (type == 1) {
                if (!foundApp) {
                    ParseResult<?> deferResult = input.deferError("<manifest> does not contain an <application>", ParseInput.DeferredError.MISSING_APP_TAG);
                    if (deferResult.isError()) {
                        return input.error(deferResult);
                    }
                }
                return input.success(pkg);
            } else if (outerDepth + 1 >= parser.getDepth() && type == 2) {
                String tagName = parser.getName();
                if (!"application".equals(tagName)) {
                    result = ParsingUtils.unknownTag("<manifest>", pkg, parser, input);
                } else if (foundApp) {
                    Slog.w("PackageParsing", "<manifest> has more than one <application>");
                    result = input.success(null);
                } else {
                    foundApp = true;
                    result = parseSplitApplication(input, pkg, res, parser, flags, splitIndex);
                }
                if (result.isError()) {
                    return input.error(result);
                }
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00a4, code lost:
        if (r13.equals("provider") != false) goto L_0x00b2;
     */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0174  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.content.pm.parsing.result.ParseResult<android.content.pm.parsing.ParsingPackage> parseSplitApplication(android.content.pm.parsing.result.ParseInput r21, android.content.pm.parsing.ParsingPackage r22, android.content.res.Resources r23, android.content.res.XmlResourceParser r24, int r25, int r26) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 486
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.parsing.ParsingPackageUtils.parseSplitApplication(android.content.pm.parsing.result.ParseInput, android.content.pm.parsing.ParsingPackage, android.content.res.Resources, android.content.res.XmlResourceParser, int, int):android.content.pm.parsing.result.ParseResult");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private ParseResult parseSplitBaseAppChildTags(ParseInput input, String tag, ParsingPackage pkg, Resources res, XmlResourceParser parser) throws IOException, XmlPullParserException {
        char c;
        switch (tag.hashCode()) {
            case -1608941274:
                if (tag.equals("uses-native-library")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -1356765254:
                if (tag.equals("uses-library")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1115949454:
                if (tag.equals("meta-data")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -993141291:
                if (tag.equals("property")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 8960125:
                if (tag.equals("uses-static-library")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1964930885:
                if (tag.equals("uses-package")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                ParseResult<PackageManager.Property> metaDataResult = parseMetaData(pkg, null, res, parser, "<meta-data>", input);
                if (metaDataResult.isSuccess() && metaDataResult.getResult() != null) {
                    pkg.setMetaData(metaDataResult.getResult().toBundle(pkg.getMetaData()));
                }
                return metaDataResult;
            case 1:
                ParseResult<PackageManager.Property> propertyResult = parseMetaData(pkg, null, res, parser, "<property>", input);
                if (propertyResult.isSuccess()) {
                    pkg.addProperty(propertyResult.getResult());
                }
                return propertyResult;
            case 2:
                return parseUsesStaticLibrary(input, pkg, res, parser);
            case 3:
                return parseUsesLibrary(input, pkg, res, parser);
            case 4:
                return parseUsesNativeLibrary(input, pkg, res, parser);
            case 5:
                return input.success(null);
            default:
                return ParsingUtils.unknownTag("<application>", pkg, parser, input);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x00a7, code lost:
        if (r15 != false) goto L_0x00c7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00b1, code lost:
        if (com.android.internal.util.ArrayUtils.size(r20.getInstrumentations()) != 0) goto L_0x00c7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00b3, code lost:
        r0 = r19.deferError("<manifest> does not contain an <application> or <instrumentation>", android.content.pm.parsing.result.ParseInput.DeferredError.MISSING_APP_TAG);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00c0, code lost:
        if (r0.isError() == false) goto L_0x00c7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00c6, code lost:
        return r19.error(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00cf, code lost:
        if (android.content.pm.parsing.component.ParsedAttribution.isCombinationValid(r20.getAttributions()) != false) goto L_0x00da;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00d9, code lost:
        return r19.error(-101, "Combination <attribution> tags are not valid");
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00da, code lost:
        convertNewPermissions(r20);
        convertSplitPermissions(r20);
        android.content.pm.PackageParserExtPlugin.adjustPermissionInParseBaseApkTags.call(null, r20);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00ef, code lost:
        if (r20.getTargetSdkVersion() < 4) goto L_0x0115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00f5, code lost:
        if (r20.isSupportsSmallScreens() != false) goto L_0x0118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00fb, code lost:
        if (r20.isSupportsNormalScreens() != false) goto L_0x0118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0101, code lost:
        if (r20.isSupportsLargeScreens() != false) goto L_0x0118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0107, code lost:
        if (r20.isSupportsExtraLargeScreens() != false) goto L_0x0118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x010d, code lost:
        if (r20.isResizeable() != false) goto L_0x0118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0113, code lost:
        if (r20.isAnyDensity() != false) goto L_0x0118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0115, code lost:
        adjustPackageToBeUnresizeableAndUnpipable(r20);
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0118, code lost:
        com.mediatek.cta.CtaManagerFactory.getInstance().makeCtaManager().linkCtaPermissions(r20);
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0127, code lost:
        return r19.success(r20);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.content.pm.parsing.result.ParseResult<android.content.pm.parsing.ParsingPackage> parseBaseApkTags(android.content.pm.parsing.result.ParseInput r19, android.content.pm.parsing.ParsingPackage r20, android.content.res.TypedArray r21, android.content.res.Resources r22, android.content.res.XmlResourceParser r23, int r24) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 296
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.parsing.ParsingPackageUtils.parseBaseApkTags(android.content.pm.parsing.result.ParseInput, android.content.pm.parsing.ParsingPackage, android.content.res.TypedArray, android.content.res.Resources, android.content.res.XmlResourceParser, int):android.content.pm.parsing.result.ParseResult");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private ParseResult parseBaseApkTag(String tag, ParseInput input, ParsingPackage pkg, Resources res, XmlResourceParser parser, int flags) throws IOException, XmlPullParserException {
        char c;
        switch (tag.hashCode()) {
            case -1773650763:
                if (tag.equals("uses-configuration")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -1667688228:
                if (tag.equals("permission-tree")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -1108197302:
                if (tag.equals("original-package")) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case -1091287984:
                if (tag.equals("overlay")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -998269702:
                if (tag.equals("restrict-update")) {
                    c = 23;
                    break;
                }
                c = 65535;
                break;
            case -979207434:
                if (tag.equals("feature")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -517618225:
                if (tag.equals("permission")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -309882753:
                if (tag.equals("attribution")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -266709319:
                if (tag.equals("uses-sdk")) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case -170723071:
                if (tag.equals("permission-group")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -129269526:
                if (tag.equals("eat-comment")) {
                    c = 22;
                    break;
                }
                c = 65535;
                break;
            case 119109844:
                if (tag.equals("uses-gl-texture")) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case 349565761:
                if (tag.equals("supports-input")) {
                    c = 21;
                    break;
                }
                c = 65535;
                break;
            case 454915839:
                if (tag.equals("key-sets")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 544550766:
                if (tag.equals("instrumentation")) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case 599862896:
                if (tag.equals("uses-permission")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 632228327:
                if (tag.equals("adopt-permissions")) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case 655087462:
                if (tag.equals("queries")) {
                    c = 24;
                    break;
                }
                c = 65535;
                break;
            case 896788286:
                if (tag.equals("supports-screens")) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case 1343942321:
                if (tag.equals("uses-permission-sdk-23")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 1439495522:
                if (tag.equals("protected-broadcast")) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 1682371816:
                if (tag.equals("feature-group")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 1705921021:
                if (tag.equals("uses-permission-sdk-m")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1792785909:
                if (tag.equals("uses-feature")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 1818228622:
                if (tag.equals("compatible-screens")) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return parseOverlay(input, pkg, res, parser);
            case 1:
                return parseKeySets(input, pkg, res, parser);
            case 2:
            case 3:
                return parseAttribution(input, pkg, res, parser);
            case 4:
                return parsePermissionGroup(input, pkg, res, parser);
            case 5:
                return parsePermission(input, pkg, res, parser);
            case 6:
                return parsePermissionTree(input, pkg, res, parser);
            case 7:
            case '\b':
            case '\t':
                return parseUsesPermission(input, pkg, res, parser);
            case '\n':
                return parseUsesConfiguration(input, pkg, res, parser);
            case 11:
                return parseUsesFeature(input, pkg, res, parser);
            case '\f':
                return parseFeatureGroup(input, pkg, res, parser);
            case '\r':
                return parseUsesSdk(input, pkg, res, parser);
            case 14:
                return parseSupportScreens(input, pkg, res, parser);
            case 15:
                return parseProtectedBroadcast(input, pkg, res, parser);
            case 16:
                return parseInstrumentation(input, pkg, res, parser);
            case 17:
                return parseOriginalPackage(input, pkg, res, parser);
            case 18:
                return parseAdoptPermissions(input, pkg, res, parser);
            case 19:
            case 20:
            case 21:
            case 22:
                XmlUtils.skipCurrentTag(parser);
                return input.success(pkg);
            case 23:
                return parseRestrictUpdateHash(flags, input, pkg, res, parser);
            case 24:
                return parseQueries(input, pkg, res, parser);
            default:
                return ParsingUtils.unknownTag("<manifest>", pkg, parser, input);
        }
    }

    private static ParseResult<ParsingPackage> parseSharedUser(ParseInput input, ParsingPackage pkg, TypedArray sa) {
        String str = nonConfigString(0, 0, sa);
        if (TextUtils.isEmpty(str)) {
            return input.success(pkg);
        }
        if (!"android".equals(pkg.getPackageName())) {
            ParseResult<?> nameResult = validateName(input, str, true, true);
            if (nameResult.isError()) {
                return input.error(PackageManager.INSTALL_PARSE_FAILED_BAD_SHARED_USER_ID, "<manifest> specifies bad sharedUserId name \"" + str + "\": " + nameResult.getErrorMessage());
            }
        }
        return input.success(pkg.setSharedUserId(str.intern()).setSharedUserLabel(resId(3, sa)));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0222, code lost:
        r0 = r22.getPackageName();
        r5 = r7.keySet();
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0234, code lost:
        if (r5.removeAll(r9.keySet()) == false) goto L_0x024f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x024e, code lost:
        return r21.error("Package" + r0 + " AndroidManifest.xml 'key-set' and 'public-key' names must be distinct.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x024f, code lost:
        r6 = r9.entrySet().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x025b, code lost:
        if (r6.hasNext() == false) goto L_0x02ed;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x025d, code lost:
        r14 = r6.next();
        r15 = r14.getKey();
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x0275, code lost:
        if (r14.getValue().size() != 0) goto L_0x0299;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x0277, code lost:
        android.util.Slog.w("PackageParsing", "Package" + r0 + " AndroidManifest.xml 'key-set' " + r15 + " has no valid associated 'public-key'. Not including in package's defined key-sets.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x029d, code lost:
        if (r10.contains(r15) == false) goto L_0x02c1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x029f, code lost:
        android.util.Slog.w("PackageParsing", "Package" + r0 + " AndroidManifest.xml 'key-set' " + r15 + " contained improper 'public-key' tags. Not including in package's defined key-sets.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x02c1, code lost:
        r3 = r14.getValue().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x02cf, code lost:
        if (r3.hasNext() == false) goto L_0x02e7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x02d1, code lost:
        r4 = r3.next();
        r22.addKeySet(r15, r7.get(r4));
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x02f9, code lost:
        if (r22.getKeySetMapping().keySet().containsAll(r8) == false) goto L_0x0303;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x02fb, code lost:
        r22.setUpgradeKeySets(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x0302, code lost:
        return r21.success(r22);
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x031b, code lost:
        return r21.error("Package" + r0 + " AndroidManifest.xml does not define all 'upgrade-key-set's .");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.content.pm.parsing.result.ParseResult<android.content.pm.parsing.ParsingPackage> parseKeySets(android.content.pm.parsing.result.ParseInput r21, android.content.pm.parsing.ParsingPackage r22, android.content.res.Resources r23, android.content.res.XmlResourceParser r24) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 820
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.parsing.ParsingPackageUtils.parseKeySets(android.content.pm.parsing.result.ParseInput, android.content.pm.parsing.ParsingPackage, android.content.res.Resources, android.content.res.XmlResourceParser):android.content.pm.parsing.result.ParseResult");
    }

    private static ParseResult<ParsingPackage> parseAttribution(ParseInput input, ParsingPackage pkg, Resources res, XmlResourceParser parser) throws IOException, XmlPullParserException {
        ParseResult<ParsedAttribution> result = ParsedAttributionUtils.parseAttribution(res, parser, input);
        if (result.isError()) {
            return input.error(result);
        }
        return input.success(pkg.addAttribution(result.getResult()));
    }

    private static ParseResult<ParsingPackage> parsePermissionGroup(ParseInput input, ParsingPackage pkg, Resources res, XmlResourceParser parser) throws XmlPullParserException, IOException {
        ParseResult<ParsedPermissionGroup> result = ParsedPermissionUtils.parsePermissionGroup(pkg, res, parser, sUseRoundIcon, input);
        if (result.isError()) {
            return input.error(result);
        }
        return input.success(pkg.addPermissionGroup(result.getResult()));
    }

    private static ParseResult<ParsingPackage> parsePermission(ParseInput input, ParsingPackage pkg, Resources res, XmlResourceParser parser) throws XmlPullParserException, IOException {
        ParseResult<ParsedPermission> result = ParsedPermissionUtils.parsePermission(pkg, res, parser, sUseRoundIcon, input);
        if (result.isError()) {
            return input.error(result);
        }
        return input.success(pkg.addPermission(result.getResult()));
    }

    private static ParseResult<ParsingPackage> parsePermissionTree(ParseInput input, ParsingPackage pkg, Resources res, XmlResourceParser parser) throws XmlPullParserException, IOException {
        ParseResult<ParsedPermission> result = ParsedPermissionUtils.parsePermissionTree(pkg, res, parser, sUseRoundIcon, input);
        if (result.isError()) {
            return input.error(result);
        }
        return input.success(pkg.addPermission(result.getResult()));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private ParseResult<ParsingPackage> parseUsesPermission(ParseInput input, ParsingPackage pkg, Resources res, XmlResourceParser parser) throws IOException, XmlPullParserException {
        int type;
        int maxSdkVersion;
        int i;
        int outerDepth;
        char c;
        ParseResult<?> result;
        ParseInput parseInput = input;
        TypedArray sa = res.obtainAttributes(parser, R.styleable.AndroidManifestUsesPermission);
        try {
            String name = sa.getNonResourceString(0);
            int maxSdkVersion2 = 0;
            int i2 = 1;
            TypedValue val = sa.peekValue(1);
            if (val != null && val.type >= 16 && val.type <= 31) {
                maxSdkVersion2 = val.data;
            }
            ArraySet<String> requiredFeatures = new ArraySet<>();
            String feature = sa.getNonConfigurationString(2, 0);
            if (feature != null) {
                requiredFeatures.add(feature);
            }
            ArraySet<String> requiredNotFeatures = new ArraySet<>();
            int i3 = 3;
            String feature2 = sa.getNonConfigurationString(3, 0);
            if (feature2 != null) {
                requiredNotFeatures.add(feature2);
            }
            int usesPermissionFlags = sa.getInt(4, 0);
            int outerDepth2 = parser.getDepth();
            while (true) {
                int type2 = parser.next();
                if (type2 != i2) {
                    type = type2;
                    if (type == i3) {
                        outerDepth = outerDepth2;
                        if (parser.getDepth() > outerDepth) {
                        }
                    } else {
                        outerDepth = outerDepth2;
                    }
                    if (type == i3) {
                        outerDepth2 = outerDepth;
                        i2 = 1;
                        i3 = 3;
                    } else if (type == 4) {
                        outerDepth2 = outerDepth;
                        i2 = 1;
                    } else {
                        String name2 = parser.getName();
                        switch (name2.hashCode()) {
                            case 874138830:
                                if (name2.equals("required-not-feature")) {
                                    c = 1;
                                    break;
                                }
                                c = 65535;
                                break;
                            case 1693350600:
                                if (name2.equals("required-feature")) {
                                    c = 0;
                                    break;
                                }
                                c = 65535;
                                break;
                            default:
                                c = 65535;
                                break;
                        }
                        switch (c) {
                            case 0:
                                result = parseRequiredFeature(parseInput, res, parser);
                                if (result.isSuccess()) {
                                    requiredFeatures.add(result.getResult());
                                    break;
                                }
                                break;
                            case 1:
                                result = parseRequiredNotFeature(parseInput, res, parser);
                                if (result.isSuccess()) {
                                    requiredNotFeatures.add(result.getResult());
                                    break;
                                }
                                break;
                            default:
                                result = ParsingUtils.unknownTag("<uses-permission>", pkg, parser, parseInput);
                                break;
                        }
                        if (result.isError()) {
                            return parseInput.error(result);
                        }
                        outerDepth2 = outerDepth;
                        i2 = 1;
                        i3 = 3;
                    }
                } else {
                    type = type2;
                }
            }
            ParseResult<ParsingPackage> success = input.success(pkg);
            if (name == null) {
                return success;
            }
            if (maxSdkVersion2 != 0 && maxSdkVersion2 < Build.VERSION.RESOURCES_SDK_INT) {
                return success;
            }
            if (this.mCallback != null) {
                int i4 = requiredFeatures.size() - 1;
                while (i4 >= 0) {
                    if (!this.mCallback.hasFeature(requiredFeatures.valueAt(i4))) {
                        return success;
                    }
                    i4--;
                    type = type;
                }
                for (int i5 = requiredNotFeatures.size() - 1; i5 >= 0; i5--) {
                    if (this.mCallback.hasFeature(requiredNotFeatures.valueAt(i5))) {
                        return success;
                    }
                }
            }
            List<ParsedUsesPermission> usesPermissions = pkg.getUsesPermissions();
            int size = usesPermissions.size();
            int i6 = 0;
            while (true) {
                if (i6 < size) {
                    ParsedUsesPermission usesPermission = usesPermissions.get(i6);
                    if (Objects.equals(usesPermission.name, name)) {
                        maxSdkVersion = usesPermissionFlags;
                        if (usesPermission.usesPermissionFlags != maxSdkVersion) {
                            return parseInput.error("Conflicting uses-permissions flags: " + name + " in package: " + pkg.getPackageName() + " at: " + parser.getPositionDescription());
                        }
                        Slog.w("PackageParsing", "Ignoring duplicate uses-permissions/uses-permissions-sdk-m: " + name + " in package: " + pkg.getPackageName() + " at: " + parser.getPositionDescription());
                        i = 1;
                    } else {
                        i6++;
                        parseInput = input;
                        usesPermissions = usesPermissions;
                        usesPermissionFlags = usesPermissionFlags;
                        maxSdkVersion2 = maxSdkVersion2;
                    }
                } else {
                    maxSdkVersion = usesPermissionFlags;
                    i = 0;
                }
            }
            if (i == 0) {
                pkg.addUsesPermission(new ParsedUsesPermission(name, maxSdkVersion));
            }
            return success;
        } finally {
            sa.recycle();
        }
    }

    private ParseResult<String> parseRequiredFeature(ParseInput input, Resources res, AttributeSet attrs) {
        ParseResult<String> parseResult;
        TypedArray sa = res.obtainAttributes(attrs, R.styleable.AndroidManifestRequiredFeature);
        try {
            String featureName = sa.getString(0);
            if (TextUtils.isEmpty(featureName)) {
                parseResult = input.error("Feature name is missing from <required-feature> tag.");
            } else {
                parseResult = input.success(featureName);
            }
            return parseResult;
        } finally {
            sa.recycle();
        }
    }

    private ParseResult<String> parseRequiredNotFeature(ParseInput input, Resources res, AttributeSet attrs) {
        ParseResult<String> parseResult;
        TypedArray sa = res.obtainAttributes(attrs, R.styleable.AndroidManifestRequiredNotFeature);
        try {
            String featureName = sa.getString(0);
            if (TextUtils.isEmpty(featureName)) {
                parseResult = input.error("Feature name is missing from <required-not-feature> tag.");
            } else {
                parseResult = input.success(featureName);
            }
            return parseResult;
        } finally {
            sa.recycle();
        }
    }

    private static ParseResult<ParsingPackage> parseUsesConfiguration(ParseInput input, ParsingPackage pkg, Resources res, XmlResourceParser parser) {
        ConfigurationInfo cPref = new ConfigurationInfo();
        TypedArray sa = res.obtainAttributes(parser, R.styleable.AndroidManifestUsesConfiguration);
        try {
            cPref.reqTouchScreen = sa.getInt(0, 0);
            cPref.reqKeyboardType = sa.getInt(1, 0);
            if (sa.getBoolean(2, false)) {
                cPref.reqInputFeatures = 1 | cPref.reqInputFeatures;
            }
            cPref.reqNavigation = sa.getInt(3, 0);
            if (sa.getBoolean(4, false)) {
                cPref.reqInputFeatures |= 2;
            }
            pkg.addConfigPreference(cPref);
            return input.success(pkg);
        } finally {
            sa.recycle();
        }
    }

    private static ParseResult<ParsingPackage> parseUsesFeature(ParseInput input, ParsingPackage pkg, Resources res, XmlResourceParser parser) {
        FeatureInfo fi = parseFeatureInfo(res, parser);
        pkg.addReqFeature(fi);
        if (fi.name == null) {
            ConfigurationInfo cPref = new ConfigurationInfo();
            cPref.reqGlEsVersion = fi.reqGlEsVersion;
            pkg.addConfigPreference(cPref);
        }
        return input.success(pkg);
    }

    private static FeatureInfo parseFeatureInfo(Resources res, AttributeSet attrs) {
        FeatureInfo fi = new FeatureInfo();
        TypedArray sa = res.obtainAttributes(attrs, R.styleable.AndroidManifestUsesFeature);
        try {
            fi.name = sa.getNonResourceString(0);
            fi.version = sa.getInt(3, 0);
            if (fi.name == null) {
                fi.reqGlEsVersion = sa.getInt(1, 0);
            }
            if (sa.getBoolean(2, true)) {
                fi.flags |= 1;
            }
            return fi;
        } finally {
            sa.recycle();
        }
    }

    private static ParseResult<ParsingPackage> parseFeatureGroup(ParseInput input, ParsingPackage pkg, Resources res, XmlResourceParser parser) throws IOException, XmlPullParserException {
        FeatureGroupInfo group = new FeatureGroupInfo();
        ArrayList<FeatureInfo> features = null;
        int depth = parser.getDepth();
        while (true) {
            int type = parser.next();
            if (type == 1 || (type == 3 && parser.getDepth() <= depth)) {
                break;
            } else if (type == 2) {
                String innerTagName = parser.getName();
                if (innerTagName.equals("uses-feature")) {
                    FeatureInfo featureInfo = parseFeatureInfo(res, parser);
                    featureInfo.flags = 1 | featureInfo.flags;
                    features = ArrayUtils.add(features, featureInfo);
                } else {
                    Slog.w("PackageParsing", "Unknown element under <feature-group>: " + innerTagName + " at " + pkg.getBaseApkPath() + " " + parser.getPositionDescription());
                }
            }
        }
        if (features != null) {
            group.features = new FeatureInfo[features.size()];
            group.features = (FeatureInfo[]) features.toArray(group.features);
        }
        pkg.addFeatureGroup(group);
        return input.success(pkg);
    }

    private static ParseResult<ParsingPackage> parseUsesSdk(ParseInput input, ParsingPackage pkg, Resources res, XmlResourceParser parser) throws IOException, XmlPullParserException {
        ParseResult result;
        SparseIntArray minExtensionVersions;
        int i = SDK_VERSION;
        if (i > 0) {
            TypedArray sa = res.obtainAttributes(parser, R.styleable.AndroidManifestUsesSdk);
            int minVers = 1;
            String minCode = null;
            int targetVers = 0;
            String targetCode = null;
            try {
                TypedValue val = sa.peekValue(0);
                if (val != null) {
                    if (val.type != 3 || val.string == null) {
                        minVers = val.data;
                    } else {
                        minCode = val.string.toString();
                    }
                }
                TypedValue val2 = sa.peekValue(1);
                if (val2 == null) {
                    targetVers = minVers;
                    targetCode = minCode;
                } else if (val2.type != 3 || val2.string == null) {
                    targetVers = val2.data;
                } else {
                    targetCode = val2.string.toString();
                    if (minCode == null) {
                        minCode = targetCode;
                    }
                }
                String[] strArr = SDK_CODENAMES;
                ParseResult<Integer> targetSdkVersionResult = computeTargetSdkVersion(targetVers, targetCode, strArr, input);
                if (targetSdkVersionResult.isError()) {
                    return input.error(targetSdkVersionResult);
                }
                int targetSdkVersion = targetSdkVersionResult.getResult().intValue();
                ParseResult<?> deferResult = input.enableDeferredError(pkg.getPackageName(), targetSdkVersion);
                if (deferResult.isError()) {
                    return input.error(deferResult);
                }
                ParseResult<Integer> minSdkVersionResult = computeMinSdkVersion(minVers, minCode, i, strArr, input);
                if (minSdkVersionResult.isError()) {
                    return input.error(minSdkVersionResult);
                }
                int minSdkVersion = minSdkVersionResult.getResult().intValue();
                pkg.setMinSdkVersion(minSdkVersion).setTargetSdkVersion(targetSdkVersion);
                int innerDepth = parser.getDepth();
                SparseIntArray minExtensionVersions2 = null;
                while (true) {
                    int type = parser.next();
                    if (type == 1) {
                        break;
                    }
                    if (type == 3 && parser.getDepth() <= innerDepth) {
                        break;
                    }
                    if (!(type == 3 || type == 4)) {
                        if (parser.getName().equals("extension-sdk")) {
                            if (minExtensionVersions2 == null) {
                                minExtensionVersions = new SparseIntArray();
                            } else {
                                minExtensionVersions = minExtensionVersions2;
                            }
                            result = parseExtensionSdk(input, res, parser, minExtensionVersions);
                            XmlUtils.skipCurrentTag(parser);
                            minExtensionVersions2 = minExtensionVersions;
                        } else {
                            result = ParsingUtils.unknownTag("<uses-sdk>", pkg, parser, input);
                        }
                        if (result.isError()) {
                            return input.error(result);
                        }
                        minSdkVersionResult = minSdkVersionResult;
                        minVers = minVers;
                    }
                    minSdkVersionResult = minSdkVersionResult;
                    minVers = minVers;
                }
                pkg.setMinExtensionVersions(exactSizedCopyOfSparseArray(minExtensionVersions2));
            } finally {
                sa.recycle();
            }
        }
        return input.success(pkg);
    }

    private static SparseIntArray exactSizedCopyOfSparseArray(SparseIntArray input) {
        if (input == null) {
            return null;
        }
        SparseIntArray output = new SparseIntArray(input.size());
        for (int i = 0; i < input.size(); i++) {
            output.put(input.keyAt(i), input.valueAt(i));
        }
        return output;
    }

    /* JADX WARN: Finally extract failed */
    private static ParseResult<SparseIntArray> parseExtensionSdk(ParseInput input, Resources res, XmlResourceParser parser, SparseIntArray minExtensionVersions) {
        TypedArray sa = res.obtainAttributes(parser, R.styleable.AndroidManifestExtensionSdk);
        try {
            int sdkVersion = sa.getInt(0, -1);
            int minVersion = sa.getInt(1, -1);
            sa.recycle();
            if (sdkVersion < 0) {
                return input.error(PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED, "<extension-sdk> must specify an sdkVersion >= 0");
            }
            if (minVersion < 0) {
                return input.error(PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED, "<extension-sdk> must specify minExtensionVersion >= 0");
            }
            try {
                int version = SdkExtensions.getExtensionVersion(sdkVersion);
                if (version < minVersion) {
                    return input.error(-12, "Package requires " + sdkVersion + " extension version " + minVersion + " which exceeds device version " + version);
                }
                minExtensionVersions.put(sdkVersion, minVersion);
                return input.success(minExtensionVersions);
            } catch (RuntimeException e) {
                return input.error(PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED, "Specified sdkVersion " + sdkVersion + " is not valid");
            }
        } catch (Throwable th) {
            sa.recycle();
            throw th;
        }
    }

    public static ParseResult<Integer> computeMinSdkVersion(int minVers, String minCode, int platformSdkVersion, String[] platformSdkCodenames, ParseInput input) {
        if (minCode == null) {
            if (minVers <= platformSdkVersion) {
                return input.success(Integer.valueOf(minVers));
            }
            return input.error(-12, "Requires newer sdk version #" + minVers + " (current version is #" + platformSdkVersion + ")");
        } else if (matchTargetCode(platformSdkCodenames, minCode)) {
            return input.success(10000);
        } else {
            if (platformSdkCodenames.length > 0) {
                return input.error(-12, "Requires development platform " + minCode + " (current platform is any of " + Arrays.toString(platformSdkCodenames) + ")");
            }
            return input.error(-12, "Requires development platform " + minCode + " but this is a release platform.");
        }
    }

    public static ParseResult<Integer> computeTargetSdkVersion(int targetVers, String targetCode, String[] platformSdkCodenames, ParseInput input) {
        if (targetCode == null) {
            return input.success(Integer.valueOf(targetVers));
        }
        if (matchTargetCode(platformSdkCodenames, targetCode)) {
            return input.success(10000);
        }
        if (platformSdkCodenames.length > 0) {
            return input.error(-12, "Requires development platform " + targetCode + " (current platform is any of " + Arrays.toString(platformSdkCodenames) + ")");
        }
        return input.error(-12, "Requires development platform " + targetCode + " but this is a release platform.");
    }

    private static boolean matchTargetCode(String[] codeNames, String targetCode) {
        String targetCodeName;
        int targetCodeIdx = targetCode.indexOf(46);
        if (targetCodeIdx == -1) {
            targetCodeName = targetCode;
        } else {
            targetCodeName = targetCode.substring(0, targetCodeIdx);
        }
        return ArrayUtils.contains(codeNames, targetCodeName);
    }

    private static ParseResult<ParsingPackage> parseRestrictUpdateHash(int flags, ParseInput input, ParsingPackage pkg, Resources res, XmlResourceParser parser) {
        if ((flags & 16) != 0) {
            TypedArray sa = res.obtainAttributes(parser, R.styleable.AndroidManifestRestrictUpdate);
            try {
                String hash = sa.getNonConfigurationString(0, 0);
                if (hash != null) {
                    int hashLength = hash.length();
                    byte[] hashBytes = new byte[hashLength / 2];
                    for (int i = 0; i < hashLength; i += 2) {
                        hashBytes[i / 2] = (byte) ((Character.digit(hash.charAt(i), 16) << 4) + Character.digit(hash.charAt(i + 1), 16));
                    }
                    pkg.setRestrictUpdateHash(hashBytes);
                } else {
                    pkg.setRestrictUpdateHash(null);
                }
            } finally {
                sa.recycle();
            }
        }
        return input.success(pkg);
    }

    /* JADX WARN: Code restructure failed: missing block: B:83:0x01c4, code lost:
        return r20.success(r21);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.content.pm.parsing.result.ParseResult<android.content.pm.parsing.ParsingPackage> parseQueries(android.content.pm.parsing.result.ParseInput r20, android.content.pm.parsing.ParsingPackage r21, android.content.res.Resources r22, android.content.res.XmlResourceParser r23) throws java.io.IOException, org.xmlpull.v1.XmlPullParserException {
        /*
            Method dump skipped, instructions count: 453
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.parsing.ParsingPackageUtils.parseQueries(android.content.pm.parsing.result.ParseInput, android.content.pm.parsing.ParsingPackage, android.content.res.Resources, android.content.res.XmlResourceParser):android.content.pm.parsing.result.ParseResult");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:221:0x0462, code lost:
        if (android.text.TextUtils.isEmpty(r29.getStaticSharedLibName()) == false) goto L_0x047c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:222:0x0464, code lost:
        r1 = generateAppDetailsHiddenActivity(r28, r29);
     */
    /* JADX WARN: Code restructure failed: missing block: B:223:0x046c, code lost:
        if (r1.isError() == false) goto L_0x0473;
     */
    /* JADX WARN: Code restructure failed: missing block: B:225:0x0472, code lost:
        return r28.error(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:226:0x0473, code lost:
        r29.addActivity(r1.getResult());
     */
    /* JADX WARN: Code restructure failed: missing block: B:227:0x047c, code lost:
        if (r0 == 0) goto L_0x0481;
     */
    /* JADX WARN: Code restructure failed: missing block: B:228:0x047e, code lost:
        r29.sortActivities();
     */
    /* JADX WARN: Code restructure failed: missing block: B:229:0x0481, code lost:
        if (r17 == 0) goto L_0x0486;
     */
    /* JADX WARN: Code restructure failed: missing block: B:230:0x0483, code lost:
        r29.sortReceivers();
     */
    /* JADX WARN: Code restructure failed: missing block: B:231:0x0486, code lost:
        if (r18 == 0) goto L_0x048b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:232:0x0488, code lost:
        r29.sortServices();
     */
    /* JADX WARN: Code restructure failed: missing block: B:233:0x048b, code lost:
        setMaxAspectRatio(r29);
        setMinAspectRatio(r29);
        setSupportsSizeChanges(r29);
        r29.setHasDomainUrls(hasDomainURLs(r29));
     */
    /* JADX WARN: Code restructure failed: missing block: B:234:0x049f, code lost:
        return r28.success(r29);
     */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0415  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.content.pm.parsing.result.ParseResult<android.content.pm.parsing.ParsingPackage> parseBaseApplication(android.content.pm.parsing.result.ParseInput r28, android.content.pm.parsing.ParsingPackage r29, android.content.res.Resources r30, android.content.res.XmlResourceParser r31, int r32) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 1228
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.parsing.ParsingPackageUtils.parseBaseApplication(android.content.pm.parsing.result.ParseInput, android.content.pm.parsing.ParsingPackage, android.content.res.Resources, android.content.res.XmlResourceParser, int):android.content.pm.parsing.result.ParseResult");
    }

    private void parseBaseAppBasicFlags(ParsingPackage pkg, TypedArray sa) {
        boolean z;
        boolean z2;
        boolean z3;
        int targetSdk = pkg.getTargetSdkVersion();
        boolean z4 = true;
        ParsingPackage attributionsAreUserVisible = pkg.setAllowBackup(bool(true, 17, sa)).setAllowClearUserData(bool(true, 5, sa)).setAllowClearUserDataOnFailedRestore(bool(true, 54, sa)).setAllowNativeHeapPointerTagging(bool(true, 59, sa)).setEnabled(bool(true, 9, sa)).setExtractNativeLibs(bool(true, 34, sa)).setHasCode(bool(true, 7, sa)).setAllowTaskReparenting(bool(false, 14, sa)).setCantSaveState(bool(false, 47, sa)).setCrossProfile(bool(false, 58, sa)).setDebuggable(bool(false, 10, sa)).setDefaultToDeviceProtectedStorage(bool(false, 38, sa)).setDirectBootAware(bool(false, 39, sa)).setForceQueryable(bool(false, 57, sa)).setGame(bool(false, 31, sa)).setHasFragileUserData(bool(false, 50, sa)).setLargeHeap(bool(false, 24, sa)).setMultiArch(bool(false, 33, sa)).setPreserveLegacyExternalStorage(bool(false, 61, sa)).setRequiredForAllUsers(bool(false, 27, sa)).setSupportsRtl(bool(false, 26, sa)).setTestOnly(bool(false, 15, sa)).setUseEmbeddedDex(bool(false, 53, sa)).setUsesNonSdkApi(bool(false, 49, sa)).setVmSafeMode(bool(false, 20, sa)).setAutoRevokePermissions(anInt(60, sa)).setAttributionsAreUserVisible(bool(false, 69, sa));
        if (targetSdk >= 29) {
            z = true;
        } else {
            z = false;
        }
        ParsingPackage allowAudioPlaybackCapture = attributionsAreUserVisible.setAllowAudioPlaybackCapture(bool(z, 55, sa));
        if (targetSdk >= 14) {
            z2 = true;
        } else {
            z2 = false;
        }
        ParsingPackage baseHardwareAccelerated = allowAudioPlaybackCapture.setBaseHardwareAccelerated(bool(z2, 23, sa));
        if (targetSdk < 29) {
            z3 = true;
        } else {
            z3 = false;
        }
        ParsingPackage requestLegacyExternalStorage = baseHardwareAccelerated.setRequestLegacyExternalStorage(bool(z3, 56, sa));
        if (targetSdk >= 28) {
            z4 = false;
        }
        requestLegacyExternalStorage.setUsesCleartextTraffic(bool(z4, 36, sa)).setUiOptions(anInt(25, sa)).setCategory(anInt(-1, 43, sa)).setMaxAspectRatio(aFloat(44, sa)).setMinAspectRatio(aFloat(51, sa)).setBanner(resId(30, sa)).setDescriptionRes(resId(13, sa)).setIconRes(resId(2, sa)).setLogo(resId(22, sa)).setNetworkSecurityConfigRes(resId(41, sa)).setRoundIconRes(resId(42, sa)).setTheme(resId(0, sa)).setDataExtractionRules(resId(66, sa)).setClassLoaderName(string(46, sa)).setRequiredAccountType(string(29, sa)).setRestrictedAccountType(string(28, sa)).setZygotePreloadName(string(52, sa)).setPermission(nonConfigString(0, 6, sa));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private ParseResult parseBaseAppChildTag(ParseInput input, String tag, ParsingPackage pkg, Resources res, XmlResourceParser parser, int flags) throws IOException, XmlPullParserException {
        char c;
        switch (tag.hashCode()) {
            case -1608941274:
                if (tag.equals("uses-native-library")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -1356765254:
                if (tag.equals("uses-library")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1115949454:
                if (tag.equals("meta-data")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1094759587:
                if (tag.equals("processes")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -1056667556:
                if (tag.equals("static-library")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -993141291:
                if (tag.equals("property")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 8960125:
                if (tag.equals("uses-static-library")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 166208699:
                if (tag.equals("library")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 178070147:
                if (tag.equals("profileable")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 1964930885:
                if (tag.equals("uses-package")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                ParseResult<PackageManager.Property> metaDataResult = parseMetaData(pkg, null, res, parser, "<meta-data>", input);
                if (metaDataResult.isSuccess() && metaDataResult.getResult() != null) {
                    pkg.setMetaData(metaDataResult.getResult().toBundle(pkg.getMetaData()));
                }
                return metaDataResult;
            case 1:
                ParseResult<PackageManager.Property> propertyResult = parseMetaData(pkg, null, res, parser, "<property>", input);
                if (propertyResult.isSuccess()) {
                    pkg.addProperty(propertyResult.getResult());
                }
                return propertyResult;
            case 2:
                return parseStaticLibrary(pkg, res, parser, input);
            case 3:
                return parseLibrary(pkg, res, parser, input);
            case 4:
                return parseUsesStaticLibrary(input, pkg, res, parser);
            case 5:
                return parseUsesLibrary(input, pkg, res, parser);
            case 6:
                return parseUsesNativeLibrary(input, pkg, res, parser);
            case 7:
                return parseProcesses(input, pkg, res, parser, this.mSeparateProcesses, flags);
            case '\b':
                return input.success(null);
            case '\t':
                return parseProfileable(input, pkg, res, parser);
            default:
                return ParsingUtils.unknownTag("<application>", pkg, parser, input);
        }
    }

    private static ParseResult<ParsingPackage> parseStaticLibrary(ParsingPackage pkg, Resources res, XmlResourceParser parser, ParseInput input) {
        TypedArray sa = res.obtainAttributes(parser, R.styleable.AndroidManifestStaticLibrary);
        try {
            String lname = sa.getNonResourceString(0);
            int version = sa.getInt(1, -1);
            int versionMajor = sa.getInt(2, 0);
            if (lname != null && version >= 0) {
                if (pkg.getSharedUserId() != null) {
                    return input.error(PackageManager.INSTALL_PARSE_FAILED_BAD_SHARED_USER_ID, "sharedUserId not allowed in static shared library");
                }
                if (pkg.getStaticSharedLibName() == null) {
                    return input.success(pkg.setStaticSharedLibName(lname.intern()).setStaticSharedLibVersion(PackageInfo.composeLongVersionCode(versionMajor, version)).setStaticSharedLibrary(true));
                }
                return input.error("Multiple static-shared libs for package " + pkg.getPackageName());
            }
            return input.error("Bad static-library declaration name: " + lname + " version: " + version);
        } finally {
            sa.recycle();
        }
    }

    private static ParseResult<ParsingPackage> parseLibrary(ParsingPackage pkg, Resources res, XmlResourceParser parser, ParseInput input) {
        TypedArray sa = res.obtainAttributes(parser, R.styleable.AndroidManifestLibrary);
        try {
            String lname = sa.getNonResourceString(0);
            if (lname != null) {
                String lname2 = lname.intern();
                if (!ArrayUtils.contains(pkg.getLibraryNames(), lname2)) {
                    pkg.addLibraryName(lname2);
                }
            }
            return input.success(pkg);
        } finally {
            sa.recycle();
        }
    }

    private static ParseResult<ParsingPackage> parseUsesStaticLibrary(ParseInput input, ParsingPackage pkg, Resources res, XmlResourceParser parser) throws XmlPullParserException, IOException {
        TypedArray sa = res.obtainAttributes(parser, R.styleable.AndroidManifestUsesStaticLibrary);
        try {
            String lname = sa.getNonResourceString(0);
            int version = sa.getInt(1, -1);
            String certSha256Digest = sa.getNonResourceString(2);
            if (!(lname == null || version < 0 || certSha256Digest == null)) {
                List<String> usesStaticLibraries = pkg.getUsesStaticLibraries();
                if (usesStaticLibraries.contains(lname)) {
                    return input.error("Depending on multiple versions of static library " + lname);
                }
                String lname2 = lname.intern();
                String certSha256Digest2 = certSha256Digest.replace(SettingsStringUtil.DELIMITER, "").toLowerCase();
                String[] additionalCertSha256Digests = EmptyArray.STRING;
                if (pkg.getTargetSdkVersion() >= 27) {
                    ParseResult<String[]> certResult = parseAdditionalCertificates(input, res, parser);
                    if (certResult.isError()) {
                        return input.error((ParseResult<?>) certResult);
                    }
                    additionalCertSha256Digests = certResult.getResult();
                }
                String[] certSha256Digests = new String[additionalCertSha256Digests.length + 1];
                certSha256Digests[0] = certSha256Digest2;
                System.arraycopy(additionalCertSha256Digests, 0, certSha256Digests, 1, additionalCertSha256Digests.length);
                return input.success(pkg.addUsesStaticLibrary(lname2).addUsesStaticLibraryVersion(version).addUsesStaticLibraryCertDigests(certSha256Digests));
            }
            return input.error("Bad uses-static-library declaration name: " + lname + " version: " + version + " certDigest" + certSha256Digest);
        } finally {
            sa.recycle();
        }
    }

    private static ParseResult<ParsingPackage> parseUsesLibrary(ParseInput input, ParsingPackage pkg, Resources res, XmlResourceParser parser) {
        TypedArray sa = res.obtainAttributes(parser, R.styleable.AndroidManifestUsesLibrary);
        try {
            String lname = sa.getNonResourceString(0);
            boolean req = sa.getBoolean(1, true);
            if (lname != null) {
                String lname2 = lname.intern();
                if (req) {
                    pkg.addUsesLibrary(lname2).removeUsesOptionalLibrary(lname2);
                } else if (!ArrayUtils.contains(pkg.getUsesLibraries(), lname2)) {
                    pkg.addUsesOptionalLibrary(lname2);
                }
            }
            return input.success(pkg);
        } finally {
            sa.recycle();
        }
    }

    private static ParseResult<ParsingPackage> parseUsesNativeLibrary(ParseInput input, ParsingPackage pkg, Resources res, XmlResourceParser parser) {
        TypedArray sa = res.obtainAttributes(parser, R.styleable.AndroidManifestUsesNativeLibrary);
        try {
            String lname = sa.getNonResourceString(0);
            boolean req = sa.getBoolean(1, true);
            if (lname != null) {
                if (req) {
                    pkg.addUsesNativeLibrary(lname).removeUsesOptionalNativeLibrary(lname);
                } else if (!ArrayUtils.contains(pkg.getUsesNativeLibraries(), lname)) {
                    pkg.addUsesOptionalNativeLibrary(lname);
                }
            }
            return input.success(pkg);
        } finally {
            sa.recycle();
        }
    }

    private static ParseResult<ParsingPackage> parseProcesses(ParseInput input, ParsingPackage pkg, Resources res, XmlResourceParser parser, String[] separateProcesses, int flags) throws IOException, XmlPullParserException {
        ParseResult<ArrayMap<String, ParsedProcess>> result = ParsedProcessUtils.parseProcesses(separateProcesses, pkg, res, parser, flags, input);
        if (result.isError()) {
            return input.error(result);
        }
        return input.success(pkg.setProcesses(result.getResult()));
    }

    private static ParseResult<ParsingPackage> parseProfileable(ParseInput input, ParsingPackage pkg, Resources res, XmlResourceParser parser) {
        boolean z;
        ParsingPackage newPkg;
        TypedArray sa = res.obtainAttributes(parser, R.styleable.AndroidManifestProfileable);
        try {
            boolean z2 = false;
            if (!pkg.isProfileableByShell() && !bool(false, 1, sa)) {
                z = false;
                newPkg = pkg.setProfileableByShell(z);
                if (newPkg.isProfileable() && bool(true, 0, sa)) {
                    z2 = true;
                }
                return input.success(newPkg.setProfileable(z2));
            }
            z = true;
            newPkg = pkg.setProfileableByShell(z);
            if (newPkg.isProfileable()) {
                z2 = true;
            }
            return input.success(newPkg.setProfileable(z2));
        } finally {
            sa.recycle();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0075, code lost:
        return r8.success(r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.content.pm.parsing.result.ParseResult<java.lang.String[]> parseAdditionalCertificates(android.content.pm.parsing.result.ParseInput r8, android.content.res.Resources r9, android.content.res.XmlResourceParser r10) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            java.lang.String[] r0 = libcore.util.EmptyArray.STRING
            int r1 = r10.getDepth()
        L_0x0006:
            int r2 = r10.next()
            r3 = r2
            r4 = 1
            if (r2 == r4) goto L_0x0071
            r2 = 3
            if (r3 != r2) goto L_0x0017
            int r2 = r10.getDepth()
            if (r2 <= r1) goto L_0x0071
        L_0x0017:
            r2 = 2
            if (r3 == r2) goto L_0x001b
            goto L_0x0006
        L_0x001b:
            java.lang.String r2 = r10.getName()
            java.lang.String r4 = "additional-certificate"
            boolean r4 = r2.equals(r4)
            if (r4 == 0) goto L_0x0070
            int[] r4 = com.android.internal.R.styleable.AndroidManifestAdditionalCertificate
            android.content.res.TypedArray r4 = r9.obtainAttributes(r10, r4)
            r5 = 0
            java.lang.String r5 = r4.getNonResourceString(r5)     // Catch: all -> 0x006b
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch: all -> 0x006b
            if (r6 == 0) goto L_0x0051
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: all -> 0x006b
            r6.<init>()     // Catch: all -> 0x006b
            java.lang.String r7 = "Bad additional-certificate declaration with empty certDigest:"
            r6.append(r7)     // Catch: all -> 0x006b
            r6.append(r5)     // Catch: all -> 0x006b
            java.lang.String r6 = r6.toString()     // Catch: all -> 0x006b
            android.content.pm.parsing.result.ParseResult r6 = r8.error(r6)     // Catch: all -> 0x006b
            r4.recycle()
            return r6
        L_0x0051:
            java.lang.String r6 = ":"
            java.lang.String r7 = ""
            java.lang.String r6 = r5.replace(r6, r7)     // Catch: all -> 0x006b
            java.lang.String r6 = r6.toLowerCase()     // Catch: all -> 0x006b
            r5 = r6
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            java.lang.Object[] r6 = com.android.internal.util.ArrayUtils.appendElement(r6, r0, r5)     // Catch: all -> 0x006b
            java.lang.String[] r6 = (java.lang.String[]) r6     // Catch: all -> 0x006b
            r0 = r6
            r4.recycle()
            goto L_0x0070
        L_0x006b:
            r5 = move-exception
            r4.recycle()
            throw r5
        L_0x0070:
            goto L_0x0006
        L_0x0071:
            android.content.pm.parsing.result.ParseResult r2 = r8.success(r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.parsing.ParsingPackageUtils.parseAdditionalCertificates(android.content.pm.parsing.result.ParseInput, android.content.res.Resources, android.content.res.XmlResourceParser):android.content.pm.parsing.result.ParseResult");
    }

    private static ParseResult<ParsedActivity> generateAppDetailsHiddenActivity(ParseInput input, ParsingPackage pkg) {
        String packageName = pkg.getPackageName();
        ParseResult<String> result = ComponentParseUtils.buildTaskAffinityName(packageName, packageName, ":app_details", input);
        if (result.isError()) {
            return input.error(result);
        }
        String taskAffinity = result.getResult();
        return input.success(ParsedActivity.makeAppDetailsActivity(packageName, pkg.getProcessName(), pkg.getUiOptions(), taskAffinity, pkg.isBaseHardwareAccelerated()));
    }

    private static boolean hasDomainURLs(ParsingPackage pkg) {
        List<ParsedActivity> activities = pkg.getActivities();
        int activitiesSize = activities.size();
        for (int index = 0; index < activitiesSize; index++) {
            ParsedActivity activity = activities.get(index);
            List<ParsedIntentInfo> filters = activity.getIntents();
            int filtersSize = filters.size();
            for (int filtersIndex = 0; filtersIndex < filtersSize; filtersIndex++) {
                ParsedIntentInfo aii = filters.get(filtersIndex);
                if (aii.hasAction("android.intent.action.VIEW") && aii.hasAction("android.intent.action.VIEW") && (aii.hasDataScheme(IntentFilter.SCHEME_HTTP) || aii.hasDataScheme(IntentFilter.SCHEME_HTTPS))) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void setMaxAspectRatio(ParsingPackage pkg) {
        float activityAspectRatio;
        float maxAspectRatio = pkg.getTargetSdkVersion() < 26 ? 1.86f : 0.0f;
        float packageMaxAspectRatio = pkg.getMaxAspectRatio();
        if (packageMaxAspectRatio != 0.0f) {
            maxAspectRatio = packageMaxAspectRatio;
        } else {
            Bundle appMetaData = pkg.getMetaData();
            if (appMetaData != null && appMetaData.containsKey("android.max_aspect")) {
                maxAspectRatio = appMetaData.getFloat("android.max_aspect", maxAspectRatio);
            }
        }
        List<ParsedActivity> activities = pkg.getActivities();
        int activitiesSize = activities.size();
        for (int index = 0; index < activitiesSize; index++) {
            ParsedActivity activity = activities.get(index);
            if (activity.getMaxAspectRatio() == null) {
                if (activity.getMetaData() != null) {
                    activityAspectRatio = activity.getMetaData().getFloat("android.max_aspect", maxAspectRatio);
                } else {
                    activityAspectRatio = maxAspectRatio;
                }
                activity.setMaxAspectRatio(activity.getResizeMode(), activityAspectRatio);
            }
        }
    }

    private void setMinAspectRatio(ParsingPackage pkg) {
        float minAspectRatio = pkg.getMinAspectRatio();
        List<ParsedActivity> activities = pkg.getActivities();
        int activitiesSize = activities.size();
        for (int index = 0; index < activitiesSize; index++) {
            ParsedActivity activity = activities.get(index);
            if (activity.getMinAspectRatio() == null) {
                activity.setMinAspectRatio(activity.getResizeMode(), minAspectRatio);
            }
        }
    }

    private void setSupportsSizeChanges(ParsingPackage pkg) {
        Bundle appMetaData = pkg.getMetaData();
        boolean supportsSizeChanges = appMetaData != null && appMetaData.getBoolean("android.supports_size_changes", false);
        List<ParsedActivity> activities = pkg.getActivities();
        int activitiesSize = activities.size();
        for (int index = 0; index < activitiesSize; index++) {
            ParsedActivity activity = activities.get(index);
            if (supportsSizeChanges || (activity.getMetaData() != null && activity.getMetaData().getBoolean("android.supports_size_changes", false))) {
                activity.setSupportsSizeChanges(true);
            }
        }
    }

    private static ParseResult<ParsingPackage> parseOverlay(ParseInput input, ParsingPackage pkg, Resources res, XmlResourceParser parser) {
        TypedArray sa = res.obtainAttributes(parser, R.styleable.AndroidManifestResourceOverlay);
        try {
            String target = sa.getString(1);
            int priority = anInt(0, 0, sa);
            if (target == null) {
                return input.error("<overlay> does not specify a target package");
            }
            if (priority < 0 || priority > 9999) {
                return input.error("<overlay> priority must be between 0 and 9999");
            }
            String propName = sa.getString(5);
            String propValue = sa.getString(6);
            if (PackageParser.checkRequiredSystemProperties(propName, propValue)) {
                return input.success(pkg.setOverlay(true).setOverlayTarget(target).setOverlayPriority(priority).setOverlayTargetName(sa.getString(3)).setOverlayCategory(sa.getString(2)).setOverlayIsStatic(bool(false, 4, sa)));
            }
            String message = "Skipping target and overlay pair " + target + " and " + pkg.getBaseApkPath() + ": overlay ignored due to required system property: " + propName + " with value: " + propValue;
            Slog.i("PackageParsing", message);
            return input.skip(message);
        } finally {
            sa.recycle();
        }
    }

    private static ParseResult<ParsingPackage> parseProtectedBroadcast(ParseInput input, ParsingPackage pkg, Resources res, XmlResourceParser parser) {
        TypedArray sa = res.obtainAttributes(parser, R.styleable.AndroidManifestProtectedBroadcast);
        try {
            String name = nonResString(0, sa);
            if (name != null) {
                pkg.addProtectedBroadcast(name);
            }
            return input.success(pkg);
        } finally {
            sa.recycle();
        }
    }

    private static ParseResult<ParsingPackage> parseSupportScreens(ParseInput input, ParsingPackage pkg, Resources res, XmlResourceParser parser) {
        TypedArray sa = res.obtainAttributes(parser, R.styleable.AndroidManifestSupportsScreens);
        try {
            int requiresSmallestWidthDp = anInt(0, 6, sa);
            int compatibleWidthLimitDp = anInt(0, 7, sa);
            int largestWidthLimitDp = anInt(0, 8, sa);
            return input.success(pkg.setSupportsSmallScreens(anInt(1, 1, sa)).setSupportsNormalScreens(anInt(1, 2, sa)).setSupportsLargeScreens(anInt(1, 3, sa)).setSupportsExtraLargeScreens(anInt(1, 5, sa)).setResizeable(anInt(1, 4, sa)).setAnyDensity(anInt(1, 0, sa)).setRequiresSmallestWidthDp(requiresSmallestWidthDp).setCompatibleWidthLimitDp(compatibleWidthLimitDp).setLargestWidthLimitDp(largestWidthLimitDp));
        } finally {
            sa.recycle();
        }
    }

    private static ParseResult<ParsingPackage> parseInstrumentation(ParseInput input, ParsingPackage pkg, Resources res, XmlResourceParser parser) throws XmlPullParserException, IOException {
        ParseResult<ParsedInstrumentation> result = ParsedInstrumentationUtils.parseInstrumentation(pkg, res, parser, sUseRoundIcon, input);
        if (result.isError()) {
            return input.error(result);
        }
        return input.success(pkg.addInstrumentation(result.getResult()));
    }

    private static ParseResult<ParsingPackage> parseOriginalPackage(ParseInput input, ParsingPackage pkg, Resources res, XmlResourceParser parser) {
        TypedArray sa = res.obtainAttributes(parser, R.styleable.AndroidManifestOriginalPackage);
        try {
            String orig = sa.getNonConfigurationString(0, 0);
            if (!pkg.getPackageName().equals(orig)) {
                if (pkg.getOriginalPackages().isEmpty()) {
                    pkg.setRealPackage(pkg.getPackageName());
                }
                pkg.addOriginalPackage(orig);
            }
            return input.success(pkg);
        } finally {
            sa.recycle();
        }
    }

    private static ParseResult<ParsingPackage> parseAdoptPermissions(ParseInput input, ParsingPackage pkg, Resources res, XmlResourceParser parser) {
        TypedArray sa = res.obtainAttributes(parser, R.styleable.AndroidManifestOriginalPackage);
        try {
            String name = nonConfigString(0, 0, sa);
            if (name != null) {
                pkg.addAdoptPermission(name);
            }
            return input.success(pkg);
        } finally {
            sa.recycle();
        }
    }

    private static void convertNewPermissions(ParsingPackage pkg) {
        int NP = PackageParser.NEW_PERMISSIONS.length;
        StringBuilder newPermsMsg = null;
        for (int ip = 0; ip < NP; ip++) {
            PackageParser.NewPermissionInfo npi = PackageParser.NEW_PERMISSIONS[ip];
            if (pkg.getTargetSdkVersion() >= npi.sdkVersion) {
                break;
            }
            if (!pkg.getRequestedPermissions().contains(npi.name)) {
                if (newPermsMsg == null) {
                    newPermsMsg = new StringBuilder(128);
                    newPermsMsg.append(pkg.getPackageName());
                    newPermsMsg.append(": compat added ");
                } else {
                    newPermsMsg.append(' ');
                }
                newPermsMsg.append(npi.name);
                pkg.addUsesPermission(new ParsedUsesPermission(npi.name, 0)).addImplicitPermission(npi.name);
            }
        }
        if (newPermsMsg != null) {
            Slog.i("PackageParsing", newPermsMsg.toString());
        }
    }

    private void convertSplitPermissions(ParsingPackage pkg) {
        int listSize = this.mSplitPermissionInfos.size();
        for (int is = 0; is < listSize; is++) {
            PermissionManager.SplitPermissionInfo spi = this.mSplitPermissionInfos.get(is);
            List<String> requestedPermissions = pkg.getRequestedPermissions();
            if (pkg.getTargetSdkVersion() < spi.getTargetSdk() && requestedPermissions.contains(spi.getSplitPermission())) {
                List<String> newPerms = spi.getNewPermissions();
                for (int in = 0; in < newPerms.size(); in++) {
                    String perm = newPerms.get(in);
                    if (!requestedPermissions.contains(perm)) {
                        pkg.addUsesPermission(new ParsedUsesPermission(perm, 0)).addImplicitPermission(perm);
                    }
                }
            }
        }
    }

    private static void adjustPackageToBeUnresizeableAndUnpipable(ParsingPackage pkg) {
        List<ParsedActivity> activities = pkg.getActivities();
        int activitiesSize = activities.size();
        for (int index = 0; index < activitiesSize; index++) {
            ParsedActivity activity = activities.get(index);
            activity.setResizeMode(0).setFlags(activity.getFlags() & (-4194305));
        }
    }

    public static String validateName(String name, boolean requireSeparator, boolean requireFilename) {
        int N = name.length();
        boolean hasSep = false;
        boolean front = true;
        for (int i = 0; i < N; i++) {
            char c = name.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                front = false;
            } else if (front || ((c < '0' || c > '9') && c != '_')) {
                if (c == '.') {
                    hasSep = true;
                    front = true;
                } else {
                    return "bad character '" + c + "'";
                }
            }
        }
        if (requireFilename) {
            if (!FileUtils.isValidExtFilename(name)) {
                return "Invalid filename";
            }
            if (N > 223) {
                return "the length of the name is greater than 223";
            }
        }
        if (hasSep || !requireSeparator) {
            return null;
        }
        return "must have at least one '.' separator";
    }

    public static ParseResult validateName(ParseInput input, String name, boolean requireSeparator, boolean requireFilename) {
        String errorMessage = validateName(name, requireSeparator, requireFilename);
        if (errorMessage != null) {
            return input.error(errorMessage);
        }
        return input.success(null);
    }

    public static ParseResult<PackageManager.Property> parseMetaData(ParsingPackage pkg, ParsedComponent component, Resources res, XmlResourceParser parser, String tagName, ParseInput input) {
        PackageManager.Property property;
        TypedArray sa = res.obtainAttributes(parser, R.styleable.AndroidManifestMetaData);
        boolean z = false;
        try {
            String name = TextUtils.safeIntern(nonConfigString(0, 0, sa));
            if (name == null) {
                return input.error(tagName + " requires an android:name attribute");
            }
            String packageName = pkg.getPackageName();
            String stringValue = null;
            String className = component != null ? component.getName() : null;
            TypedValue v = sa.peekValue(2);
            if (v == null || v.resourceId == 0) {
                TypedValue v2 = sa.peekValue(1);
                if (v2 == null) {
                    return input.error(tagName + " requires an android:value or android:resource attribute");
                } else if (v2.type == 3) {
                    CharSequence cs = v2.coerceToString();
                    if (cs != null) {
                        stringValue = cs.toString();
                    }
                    property = new PackageManager.Property(name, stringValue, packageName, className);
                } else if (v2.type == 18) {
                    if (v2.data != 0) {
                        z = true;
                    }
                    property = new PackageManager.Property(name, z, packageName, className);
                } else if (v2.type >= 16 && v2.type <= 31) {
                    property = new PackageManager.Property(name, v2.data, false, packageName, className);
                } else if (v2.type == 4) {
                    property = new PackageManager.Property(name, v2.getFloat(), packageName, className);
                } else {
                    Slog.w("PackageParsing", tagName + " only supports string, integer, float, color, boolean, and resource reference types: " + parser.getName() + " at " + pkg.getBaseApkPath() + " " + parser.getPositionDescription());
                    property = null;
                }
            } else {
                property = new PackageManager.Property(name, v.resourceId, true, packageName, className);
            }
            return input.success(property);
        } finally {
            sa.recycle();
        }
    }

    public static PackageParser.SigningDetails getSigningDetails(ParsingPackageRead pkg, boolean skipVerify) throws PackageParser.PackageParserException {
        Throwable th;
        PackageParser.SigningDetails signingDetails = PackageParser.SigningDetails.UNKNOWN;
        ParseInput input = ParseTypeImpl.forDefaultParsing().reset();
        Trace.traceBegin(262144L, "collectCertificates");
        try {
            ParseResult<PackageParser.SigningDetails> result = getSigningDetails(input, pkg.getBaseApkPath(), skipVerify, pkg.isStaticSharedLibrary(), signingDetails, pkg.getTargetSdkVersion());
            if (!result.isError()) {
                PackageParser.SigningDetails signingDetails2 = result.getResult();
                String[] splitCodePaths = pkg.getSplitCodePaths();
                if (!ArrayUtils.isEmpty(splitCodePaths)) {
                    PackageParser.SigningDetails signingDetails3 = signingDetails2;
                    for (String str : splitCodePaths) {
                        try {
                            ParseResult<PackageParser.SigningDetails> result2 = getSigningDetails(input, str, skipVerify, pkg.isStaticSharedLibrary(), signingDetails3, pkg.getTargetSdkVersion());
                            if (!result2.isError()) {
                                signingDetails3 = result2.getResult();
                            } else {
                                throw new PackageParser.PackageParserException(result2.getErrorCode(), result2.getErrorMessage(), result2.getException());
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            Trace.traceEnd(262144L);
                            throw th;
                        }
                    }
                    signingDetails2 = signingDetails3;
                }
                Trace.traceEnd(262144L);
                return signingDetails2;
            }
            throw new PackageParser.PackageParserException(result.getErrorCode(), result.getErrorMessage(), result.getException());
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public static ParseResult<PackageParser.SigningDetails> getSigningDetails(ParseInput input, String baseCodePath, boolean skipVerify, boolean isStaticSharedLibrary, PackageParser.SigningDetails existingSigningDetails, int targetSdk) {
        PackageParser.SigningDetails verified;
        int minSignatureScheme = ApkSignatureVerifier.getMinimumSignatureSchemeVersionForTargetSdk(targetSdk);
        if (isStaticSharedLibrary) {
            minSignatureScheme = 2;
        }
        try {
            if (skipVerify) {
                verified = ApkSignatureVerifier.unsafeGetCertsWithoutVerification(baseCodePath, 1);
            } else {
                verified = ApkSignatureVerifier.verify(baseCodePath, minSignatureScheme);
            }
            if (existingSigningDetails == PackageParser.SigningDetails.UNKNOWN) {
                return input.success(verified);
            }
            if (Signature.areExactMatch(existingSigningDetails.signatures, verified.signatures)) {
                return input.success(existingSigningDetails);
            }
            return input.error(PackageManager.INSTALL_PARSE_FAILED_INCONSISTENT_CERTIFICATES, baseCodePath + " has mismatched certificates");
        } catch (PackageParser.PackageParserException e) {
            return input.error(-103, "Failed collecting certificates for " + baseCodePath, e);
        }
    }

    public static void readConfigUseRoundIcon(Resources r) {
        if (r != null) {
            sUseRoundIcon = r.getBoolean(R.bool.config_useRoundIcon);
            return;
        }
        try {
            ApplicationInfo androidAppInfo = ActivityThread.getPackageManager().getApplicationInfo("android", 0, UserHandle.myUserId());
            Resources systemResources = Resources.getSystem();
            Resources overlayableRes = ResourcesManager.getInstance().getResources(null, null, null, androidAppInfo.resourceDirs, androidAppInfo.overlayPaths, androidAppInfo.sharedLibraryFiles, null, null, systemResources.getCompatibilityInfo(), systemResources.getClassLoader(), null);
            sUseRoundIcon = overlayableRes.getBoolean(R.bool.config_useRoundIcon);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private static boolean bool(boolean defaultValue, int attribute, TypedArray sa) {
        return sa.getBoolean(attribute, defaultValue);
    }

    private static float aFloat(float defaultValue, int attribute, TypedArray sa) {
        return sa.getFloat(attribute, defaultValue);
    }

    private static float aFloat(int attribute, TypedArray sa) {
        return sa.getFloat(attribute, 0.0f);
    }

    private static int anInt(int defaultValue, int attribute, TypedArray sa) {
        return sa.getInt(attribute, defaultValue);
    }

    private static int anInteger(int defaultValue, int attribute, TypedArray sa) {
        return sa.getInteger(attribute, defaultValue);
    }

    private static int anInt(int attribute, TypedArray sa) {
        return sa.getInt(attribute, 0);
    }

    private static int resId(int attribute, TypedArray sa) {
        return sa.getResourceId(attribute, 0);
    }

    private static String string(int attribute, TypedArray sa) {
        return sa.getString(attribute);
    }

    private static String nonConfigString(int allowedChangingConfigs, int attribute, TypedArray sa) {
        return sa.getNonConfigurationString(attribute, allowedChangingConfigs);
    }

    private static String nonResString(int index, TypedArray sa) {
        return sa.getNonResourceString(index);
    }

    public static void writeKeySetMapping(Parcel dest, Map<String, ArraySet<PublicKey>> keySetMapping) {
        if (keySetMapping == null) {
            dest.writeInt(-1);
            return;
        }
        int N = keySetMapping.size();
        dest.writeInt(N);
        for (String key : keySetMapping.keySet()) {
            dest.writeString(key);
            ArraySet<PublicKey> keys = keySetMapping.get(key);
            if (keys == null) {
                dest.writeInt(-1);
            } else {
                int M = keys.size();
                dest.writeInt(M);
                for (int j = 0; j < M; j++) {
                    dest.writeSerializable(keys.valueAt(j));
                }
            }
        }
    }

    public static ArrayMap<String, ArraySet<PublicKey>> readKeySetMapping(Parcel in) {
        int N = in.readInt();
        if (N == -1) {
            return null;
        }
        ArrayMap<String, ArraySet<PublicKey>> keySetMapping = new ArrayMap<>();
        for (int i = 0; i < N; i++) {
            String key = in.readString();
            int M = in.readInt();
            if (M == -1) {
                keySetMapping.put(key, null);
            } else {
                ArraySet<PublicKey> keys = new ArraySet<>(M);
                for (int j = 0; j < M; j++) {
                    PublicKey pk = (PublicKey) in.readSerializable();
                    keys.add(pk);
                }
                keySetMapping.put(key, keys);
            }
        }
        return keySetMapping;
    }
}
