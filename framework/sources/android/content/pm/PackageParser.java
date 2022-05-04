package android.content.pm;

import android.Manifest;
import android.apex.ApexInfo;
import android.app.ActivityTaskManager;
import android.app.ActivityThread;
import android.app.ResourcesManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.overlay.OverlayPaths;
import android.content.pm.split.SplitAssetLoader;
import android.content.res.ApkAssets;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
import android.os.IncidentManager;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PatternMatcher;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.os.Trace;
import android.os.UserHandle;
import android.os.storage.StorageManager;
import android.provider.SettingsStringUtil;
import android.security.keystore.KeyProperties;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.IntArray;
import android.util.PackageUtils;
import android.util.Pair;
import android.util.Slog;
import android.util.SparseArray;
import android.util.TypedValue;
import android.util.apk.ApkSignatureVerifier;
import com.android.internal.R;
import com.android.internal.os.ClassLoaderFactory;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.XmlUtils;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import libcore.io.IoUtils;
import libcore.util.EmptyArray;
import libcore.util.HexEncoding;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import system.ext.loader.core.ExtLoader;
@Deprecated
/* loaded from: classes.dex */
public class PackageParser {
    public static final String ANDROID_MANIFEST_FILENAME = "AndroidManifest.xml";
    public static final String ANDROID_RESOURCES = "http://schemas.android.com/apk/res/android";
    public static final String APEX_FILE_EXTENSION = ".apex";
    public static final String APK_FILE_EXTENSION = ".apk";
    public static final Set<String> CHILD_PACKAGE_TAGS;
    public static final boolean DEBUG_BACKUP = false;
    public static final boolean DEBUG_JAR = false;
    public static final boolean DEBUG_PARSER = false;
    private static final int DEFAULT_MIN_SDK_VERSION = 1;
    public static final float DEFAULT_PRE_O_MAX_ASPECT_RATIO = 1.86f;
    private static final int DEFAULT_TARGET_SDK_VERSION = 0;
    public static final boolean LOG_PARSE_TIMINGS = Build.IS_DEBUGGABLE;
    public static final int LOG_PARSE_TIMINGS_THRESHOLD_MS = 100;
    public static final boolean LOG_UNSAFE_BROADCASTS = false;
    public static final String METADATA_ACTIVITY_WINDOW_LAYOUT_AFFINITY = "android.activity_window_layout_affinity";
    public static final String METADATA_MAX_ASPECT_RATIO = "android.max_aspect";
    public static final String METADATA_SUPPORTS_SIZE_CHANGES = "android.supports_size_changes";
    public static final String MNT_EXPAND = "/mnt/expand/";
    public static final boolean MULTI_PACKAGE_APK_ENABLED;
    public static final NewPermissionInfo[] NEW_PERMISSIONS;
    public static final int PARSE_CHATTY = Integer.MIN_VALUE;
    public static final int PARSE_COLLECT_CERTIFICATES = 32;
    public static final int PARSE_DEFAULT_INSTALL_LOCATION = -1;
    public static final int PARSE_DEFAULT_TARGET_SANDBOX = 1;
    public static final int PARSE_ENFORCE_CODE = 64;
    public static final int PARSE_EXTERNAL_STORAGE = 8;
    public static final int PARSE_IGNORE_PROCESSES = 2;
    public static final int PARSE_IS_SYSTEM_DIR = 16;
    public static final int PARSE_MUST_BE_APK = 1;
    private static final String PROPERTY_CHILD_PACKAGES_ENABLED = "persist.sys.child_packages_enabled";
    private static final int RECREATE_ON_CONFIG_CHANGES_MASK = 3;
    public static final boolean RIGID_PARSER = false;
    public static final Set<String> SAFE_BROADCASTS;
    public static final String[] SDK_CODENAMES;
    public static final int SDK_VERSION;
    private static final String TAG = "PackageParser";
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
    public static boolean sCompatibilityModeEnabled;
    public static final Comparator<String> sSplitNameComparator;
    public static boolean sUseRoundIcon;
    @Deprecated
    public String mArchiveSourcePath;
    private File mCacheDir;
    public Callback mCallback;
    private DisplayMetrics mMetrics;
    private boolean mOnlyCoreApps;
    public IPackageParserExt mPackageParserExt = (IPackageParserExt) ExtLoader.type(IPackageParserExt.class).base(this).create();
    public int mParseError = 1;
    private ParsePackageItemArgs mParseInstrumentationArgs;
    public String[] mSeparateProcesses;

    /* loaded from: classes.dex */
    public interface Callback {
        boolean hasFeature(String str);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ParseFlags {
    }

    static {
        MULTI_PACKAGE_APK_ENABLED = Build.IS_DEBUGGABLE && SystemProperties.getBoolean(PROPERTY_CHILD_PACKAGES_ENABLED, false);
        ArraySet arraySet = new ArraySet();
        CHILD_PACKAGE_TAGS = arraySet;
        arraySet.add("application");
        arraySet.add("compatible-screens");
        arraySet.add("eat-comment");
        arraySet.add("feature-group");
        arraySet.add("instrumentation");
        arraySet.add("supports-screens");
        arraySet.add("supports-input");
        arraySet.add("uses-configuration");
        arraySet.add("uses-feature");
        arraySet.add("uses-gl-texture");
        arraySet.add("uses-permission");
        arraySet.add("uses-permission-sdk-23");
        arraySet.add("uses-permission-sdk-m");
        arraySet.add("uses-sdk");
        ArraySet arraySet2 = new ArraySet();
        SAFE_BROADCASTS = arraySet2;
        arraySet2.add(Intent.ACTION_BOOT_COMPLETED);
        NEW_PERMISSIONS = new NewPermissionInfo[]{new NewPermissionInfo(Manifest.permission.WRITE_EXTERNAL_STORAGE, 4, 0), new NewPermissionInfo(Manifest.permission.READ_PHONE_STATE, 4, 0)};
        SDK_VERSION = Build.VERSION.SDK_INT;
        SDK_CODENAMES = Build.VERSION.ACTIVE_CODENAMES;
        sCompatibilityModeEnabled = true;
        sUseRoundIcon = false;
        sSplitNameComparator = new SplitNameComparator();
    }

    /* loaded from: classes.dex */
    public static class NewPermissionInfo {
        public final int fileVersion;
        public final String name;
        public final int sdkVersion;

        public NewPermissionInfo(String name, int sdkVersion, int fileVersion) {
            this.name = name;
            this.sdkVersion = sdkVersion;
            this.fileVersion = fileVersion;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class ParsePackageItemArgs {
        final int bannerRes;
        final int iconRes;
        final int labelRes;
        final int logoRes;
        final int nameRes;
        final String[] outError;
        final Package owner;
        final int roundIconRes;
        TypedArray sa;
        String tag;

        ParsePackageItemArgs(Package _owner, String[] _outError, int _nameRes, int _labelRes, int _iconRes, int _roundIconRes, int _logoRes, int _bannerRes) {
            this.owner = _owner;
            this.outError = _outError;
            this.nameRes = _nameRes;
            this.labelRes = _labelRes;
            this.iconRes = _iconRes;
            this.logoRes = _logoRes;
            this.bannerRes = _bannerRes;
            this.roundIconRes = _roundIconRes;
        }
    }

    /* loaded from: classes.dex */
    public static class ParseComponentArgs extends ParsePackageItemArgs {
        final int descriptionRes;
        final int enabledRes;
        int flags;
        final int processRes;
        final String[] sepProcesses;

        public ParseComponentArgs(Package _owner, String[] _outError, int _nameRes, int _labelRes, int _iconRes, int _roundIconRes, int _logoRes, int _bannerRes, String[] _sepProcesses, int _processRes, int _descriptionRes, int _enabledRes) {
            super(_owner, _outError, _nameRes, _labelRes, _iconRes, _roundIconRes, _logoRes, _bannerRes);
            this.sepProcesses = _sepProcesses;
            this.processRes = _processRes;
            this.descriptionRes = _descriptionRes;
            this.enabledRes = _enabledRes;
        }
    }

    /* loaded from: classes.dex */
    public static class PackageLite {
        public final String baseCodePath;
        public final int baseRevisionCode;
        public final String codePath;
        public final String[] configForSplit;
        public final boolean coreApp;
        public final boolean debuggable;
        public final boolean extractNativeLibs;
        public final int installLocation;
        public final boolean[] isFeatureSplits;
        public final boolean isSplitRequired;
        public final boolean isolatedSplits;
        public final boolean multiArch;
        public final String packageName;
        public final boolean profilableByShell;
        public final String[] splitCodePaths;
        public final String[] splitNames;
        public final int[] splitRevisionCodes;
        public final boolean use32bitAbi;
        public final boolean useEmbeddedDex;
        public final String[] usesSplitNames;
        public final VerifierInfo[] verifiers;
        public final int versionCode;
        public final int versionCodeMajor;

        public PackageLite(String codePath, String baseCodePath, ApkLite baseApk, String[] splitNames, boolean[] isFeatureSplits, String[] usesSplitNames, String[] configForSplit, String[] splitCodePaths, int[] splitRevisionCodes) {
            this.packageName = baseApk.packageName;
            this.versionCode = baseApk.versionCode;
            this.versionCodeMajor = baseApk.versionCodeMajor;
            this.installLocation = baseApk.installLocation;
            this.verifiers = baseApk.verifiers;
            this.splitNames = splitNames;
            this.isFeatureSplits = isFeatureSplits;
            this.usesSplitNames = usesSplitNames;
            this.configForSplit = configForSplit;
            this.codePath = codePath;
            this.baseCodePath = baseCodePath;
            this.splitCodePaths = splitCodePaths;
            this.baseRevisionCode = baseApk.revisionCode;
            this.splitRevisionCodes = splitRevisionCodes;
            this.coreApp = baseApk.coreApp;
            this.debuggable = baseApk.debuggable;
            this.multiArch = baseApk.multiArch;
            this.use32bitAbi = baseApk.use32bitAbi;
            this.extractNativeLibs = baseApk.extractNativeLibs;
            this.isolatedSplits = baseApk.isolatedSplits;
            this.useEmbeddedDex = baseApk.useEmbeddedDex;
            this.isSplitRequired = baseApk.isSplitRequired;
            this.profilableByShell = baseApk.profilableByShell;
        }

        public List<String> getAllCodePaths() {
            ArrayList<String> paths = new ArrayList<>();
            paths.add(this.baseCodePath);
            if (!ArrayUtils.isEmpty(this.splitCodePaths)) {
                Collections.addAll(paths, this.splitCodePaths);
            }
            return paths;
        }

        public long getLongVersionCode() {
            return PackageInfo.composeLongVersionCode(this.versionCodeMajor, this.versionCode);
        }
    }

    /* loaded from: classes.dex */
    public static class ApkLite {
        public final String codePath;
        public final String configForSplit;
        public final boolean coreApp;
        public final boolean debuggable;
        public final boolean extractNativeLibs;
        public final int installLocation;
        public boolean isFeatureSplit;
        public final boolean isSplitRequired;
        public final boolean isolatedSplits;
        public final int minSdkVersion;
        public final boolean multiArch;
        public final boolean overlayIsStatic;
        public final int overlayPriority;
        public final String packageName;
        public final boolean profilableByShell;
        public final int revisionCode;
        public final int rollbackDataPolicy;
        public final SigningDetails signingDetails;
        public final String splitName;
        public final String targetPackageName;
        public final int targetSdkVersion;
        public final boolean use32bitAbi;
        public final boolean useEmbeddedDex;
        public final String usesSplitName;
        public final VerifierInfo[] verifiers;
        public final int versionCode;
        public final int versionCodeMajor;

        public ApkLite(String codePath, String packageName, String splitName, boolean isFeatureSplit, String configForSplit, String usesSplitName, boolean isSplitRequired, int versionCode, int versionCodeMajor, int revisionCode, int installLocation, List<VerifierInfo> verifiers, SigningDetails signingDetails, boolean coreApp, boolean debuggable, boolean profilableByShell, boolean multiArch, boolean use32bitAbi, boolean useEmbeddedDex, boolean extractNativeLibs, boolean isolatedSplits, String targetPackageName, boolean overlayIsStatic, int overlayPriority, int minSdkVersion, int targetSdkVersion, int rollbackDataPolicy) {
            this.codePath = codePath;
            this.packageName = packageName;
            this.splitName = splitName;
            this.isFeatureSplit = isFeatureSplit;
            this.configForSplit = configForSplit;
            this.usesSplitName = usesSplitName;
            this.versionCode = versionCode;
            this.versionCodeMajor = versionCodeMajor;
            this.revisionCode = revisionCode;
            this.installLocation = installLocation;
            this.signingDetails = signingDetails;
            this.verifiers = (VerifierInfo[]) verifiers.toArray(new VerifierInfo[verifiers.size()]);
            this.coreApp = coreApp;
            this.debuggable = debuggable;
            this.profilableByShell = profilableByShell;
            this.multiArch = multiArch;
            this.use32bitAbi = use32bitAbi;
            this.useEmbeddedDex = useEmbeddedDex;
            this.extractNativeLibs = extractNativeLibs;
            this.isolatedSplits = isolatedSplits;
            this.isSplitRequired = isSplitRequired;
            this.targetPackageName = targetPackageName;
            this.overlayIsStatic = overlayIsStatic;
            this.overlayPriority = overlayPriority;
            this.minSdkVersion = minSdkVersion;
            this.targetSdkVersion = targetSdkVersion;
            this.rollbackDataPolicy = rollbackDataPolicy;
        }

        public long getLongVersionCode() {
            return PackageInfo.composeLongVersionCode(this.versionCodeMajor, this.versionCode);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class CachedComponentArgs {
        ParseComponentArgs mActivityAliasArgs;
        ParseComponentArgs mActivityArgs;
        ParseComponentArgs mProviderArgs;
        ParseComponentArgs mServiceArgs;

        private CachedComponentArgs() {
        }
    }

    public PackageParser() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.mMetrics = displayMetrics;
        displayMetrics.setToDefaults();
    }

    public void setSeparateProcesses(String[] procs) {
        this.mSeparateProcesses = procs;
    }

    public void setOnlyCoreApps(boolean onlyCoreApps) {
        this.mOnlyCoreApps = onlyCoreApps;
    }

    public void setDisplayMetrics(DisplayMetrics metrics) {
        this.mMetrics = metrics;
    }

    public void setCacheDir(File cacheDir) {
        this.mCacheDir = cacheDir;
    }

    /* loaded from: classes.dex */
    public static final class CallbackImpl implements Callback {
        private final PackageManager mPm;

        public CallbackImpl(PackageManager pm) {
            this.mPm = pm;
        }

        @Override // android.content.pm.PackageParser.Callback
        public boolean hasFeature(String feature) {
            return this.mPm.hasSystemFeature(feature);
        }
    }

    public void setCallback(Callback cb) {
        this.mCallback = cb;
    }

    public static final boolean isApkFile(File file) {
        return isApkPath(file.getName());
    }

    public static boolean isApkPath(String path) {
        return path.endsWith(".apk");
    }

    private static boolean checkUseInstalledOrHidden(int flags, PackageUserState state, ApplicationInfo appInfo) {
        if ((flags & 536870912) == 0 && !state.installed && appInfo != null && appInfo.hiddenUntilInstalled) {
            return false;
        }
        if (!state.isAvailable(flags)) {
            if (appInfo == null || !appInfo.isSystemApp()) {
                return false;
            }
            if ((4202496 & flags) == 0 && (536870912 & flags) == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAvailable(PackageUserState state) {
        return checkUseInstalledOrHidden(0, state, null);
    }

    public static PackageInfo generatePackageInfo(Package p, int[] gids, int flags, long firstInstallTime, long lastUpdateTime, Set<String> grantedPermissions, PackageUserState state) {
        return generatePackageInfo(p, gids, flags, firstInstallTime, lastUpdateTime, grantedPermissions, state, UserHandle.getCallingUserId());
    }

    public static PackageInfo generatePackageInfo(Package p, int[] gids, int flags, long firstInstallTime, long lastUpdateTime, Set<String> grantedPermissions, PackageUserState state, int userId) {
        return generatePackageInfo(p, null, gids, flags, firstInstallTime, lastUpdateTime, grantedPermissions, state, userId);
    }

    public static PackageInfo generatePackageInfo(Package pkg, ApexInfo apexInfo, int flags) {
        return generatePackageInfo(pkg, apexInfo, EmptyArray.INT, flags, 0L, 0L, Collections.emptySet(), new PackageUserState(), UserHandle.getCallingUserId());
    }

    private static PackageInfo generatePackageInfo(Package p, ApexInfo apexInfo, int[] gids, int flags, long firstInstallTime, long lastUpdateTime, Set<String> grantedPermissions, PackageUserState state, int userId) {
        int N;
        int N2;
        int N3;
        int N4;
        int N5;
        if (checkUseInstalledOrHidden(flags, state, p.applicationInfo) && p.isMatch(flags)) {
            PackageInfo pi = new PackageInfo();
            pi.packageName = p.packageName;
            pi.splitNames = p.splitNames;
            pi.versionCode = p.mVersionCode;
            pi.versionCodeMajor = p.mVersionCodeMajor;
            pi.baseRevisionCode = p.baseRevisionCode;
            pi.splitRevisionCodes = p.splitRevisionCodes;
            pi.versionName = p.mVersionName;
            pi.sharedUserId = p.mSharedUserId;
            pi.sharedUserLabel = p.mSharedUserLabel;
            pi.applicationInfo = generateApplicationInfo(p, flags, state, userId);
            pi.installLocation = p.installLocation;
            pi.isStub = p.isStub;
            pi.coreApp = p.coreApp;
            if (!((pi.applicationInfo.flags & 1) == 0 && (pi.applicationInfo.flags & 128) == 0)) {
                pi.requiredForAllUsers = p.mRequiredForAllUsers;
            }
            pi.restrictedAccountType = p.mRestrictedAccountType;
            pi.requiredAccountType = p.mRequiredAccountType;
            pi.overlayTarget = p.mOverlayTarget;
            pi.targetOverlayableName = p.mOverlayTargetName;
            pi.overlayCategory = p.mOverlayCategory;
            pi.overlayPriority = p.mOverlayPriority;
            pi.mOverlayIsStatic = p.mOverlayIsStatic;
            pi.compileSdkVersion = p.mCompileSdkVersion;
            pi.compileSdkVersionCodename = p.mCompileSdkVersionCodename;
            pi.firstInstallTime = firstInstallTime;
            pi.lastUpdateTime = lastUpdateTime;
            if ((flags & 256) != 0) {
                pi.gids = gids;
            }
            if ((flags & 16384) != 0) {
                int N6 = p.configPreferences != null ? p.configPreferences.size() : 0;
                if (N6 > 0) {
                    pi.configPreferences = new ConfigurationInfo[N6];
                    p.configPreferences.toArray(pi.configPreferences);
                }
                int N7 = p.reqFeatures != null ? p.reqFeatures.size() : 0;
                if (N7 > 0) {
                    pi.reqFeatures = new FeatureInfo[N7];
                    p.reqFeatures.toArray(pi.reqFeatures);
                }
                int N8 = p.featureGroups != null ? p.featureGroups.size() : 0;
                if (N8 > 0) {
                    pi.featureGroups = new FeatureGroupInfo[N8];
                    p.featureGroups.toArray(pi.featureGroups);
                }
            }
            int N9 = flags & 1;
            if (N9 != 0 && (N5 = p.activities.size()) > 0) {
                int num = 0;
                ActivityInfo[] res = new ActivityInfo[N5];
                int i = 0;
                while (i < N5) {
                    Activity a = p.activities.get(i);
                    if (state.isMatch(a.info, flags) && !PackageManager.APP_DETAILS_ACTIVITY_CLASS_NAME.equals(a.className)) {
                        res[num] = generateActivityInfo(a, flags, state, userId);
                        num++;
                    }
                    i++;
                    N5 = N5;
                }
                pi.activities = (ActivityInfo[]) ArrayUtils.trimToSize(res, num);
            }
            int N10 = flags & 2;
            if (N10 != 0 && (N4 = p.receivers.size()) > 0) {
                int num2 = 0;
                ActivityInfo[] res2 = new ActivityInfo[N4];
                for (int i2 = 0; i2 < N4; i2++) {
                    Activity a2 = p.receivers.get(i2);
                    if (state.isMatch(a2.info, flags)) {
                        res2[num2] = generateActivityInfo(a2, flags, state, userId);
                        num2++;
                    }
                }
                pi.receivers = (ActivityInfo[]) ArrayUtils.trimToSize(res2, num2);
            }
            int N11 = flags & 4;
            if (N11 != 0 && (N3 = p.services.size()) > 0) {
                int num3 = 0;
                ServiceInfo[] res3 = new ServiceInfo[N3];
                for (int i3 = 0; i3 < N3; i3++) {
                    Service s = p.services.get(i3);
                    if (state.isMatch(s.info, flags)) {
                        res3[num3] = generateServiceInfo(s, flags, state, userId);
                        num3++;
                    }
                }
                pi.services = (ServiceInfo[]) ArrayUtils.trimToSize(res3, num3);
            }
            int N12 = flags & 8;
            if (N12 != 0 && (N2 = p.providers.size()) > 0) {
                int num4 = 0;
                ProviderInfo[] res4 = new ProviderInfo[N2];
                for (int i4 = 0; i4 < N2; i4++) {
                    Provider pr = p.providers.get(i4);
                    if (state.isMatch(pr.info, flags)) {
                        res4[num4] = generateProviderInfo(pr, flags, state, userId);
                        num4++;
                    }
                }
                pi.providers = (ProviderInfo[]) ArrayUtils.trimToSize(res4, num4);
            }
            int N13 = flags & 16;
            if (N13 != 0 && (N = p.instrumentation.size()) > 0) {
                pi.instrumentation = new InstrumentationInfo[N];
                for (int i5 = 0; i5 < N; i5++) {
                    pi.instrumentation[i5] = generateInstrumentationInfo(p.instrumentation.get(i5), flags);
                }
            }
            int N14 = flags & 4096;
            if (N14 != 0) {
                int N15 = p.permissions.size();
                if (N15 > 0) {
                    pi.permissions = new PermissionInfo[N15];
                    for (int i6 = 0; i6 < N15; i6++) {
                        pi.permissions[i6] = generatePermissionInfo(p.permissions.get(i6), flags);
                    }
                }
                int N16 = p.requestedPermissions.size();
                if (N16 > 0) {
                    pi.requestedPermissions = new String[N16];
                    pi.requestedPermissionsFlags = new int[N16];
                    for (int i7 = 0; i7 < N16; i7++) {
                        String perm = p.requestedPermissions.get(i7);
                        pi.requestedPermissions[i7] = perm;
                        int[] iArr = pi.requestedPermissionsFlags;
                        iArr[i7] = iArr[i7] | 1;
                        if (grantedPermissions != null && grantedPermissions.contains(perm)) {
                            int[] iArr2 = pi.requestedPermissionsFlags;
                            iArr2[i7] = iArr2[i7] | 2;
                        }
                    }
                }
            }
            if (apexInfo != null) {
                File apexFile = new File(apexInfo.modulePath);
                pi.applicationInfo.sourceDir = apexFile.getPath();
                pi.applicationInfo.publicSourceDir = apexFile.getPath();
                if (apexInfo.isFactory) {
                    pi.applicationInfo.flags |= 1;
                } else {
                    pi.applicationInfo.flags &= -2;
                }
                if (apexInfo.isActive) {
                    pi.applicationInfo.flags |= 8388608;
                } else {
                    pi.applicationInfo.flags &= -8388609;
                }
                pi.isApex = true;
            }
            if ((flags & 64) != 0) {
                if (p.mSigningDetails.hasPastSigningCertificates()) {
                    pi.signatures = new Signature[1];
                    pi.signatures[0] = p.mSigningDetails.pastSigningCertificates[0];
                } else if (p.mSigningDetails.hasSignatures()) {
                    int numberOfSigs = p.mSigningDetails.signatures.length;
                    pi.signatures = new Signature[numberOfSigs];
                    System.arraycopy(p.mSigningDetails.signatures, 0, pi.signatures, 0, numberOfSigs);
                }
            }
            if ((134217728 & flags) != 0) {
                if (p.mSigningDetails != SigningDetails.UNKNOWN) {
                    pi.signingInfo = new SigningInfo(p.mSigningDetails);
                } else {
                    pi.signingInfo = null;
                }
            }
            return pi;
        }
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

    public static PackageLite parsePackageLite(File packageFile, int flags) throws PackageParserException {
        if (packageFile.isDirectory()) {
            return parseClusterPackageLite(packageFile, flags);
        }
        return parseMonolithicPackageLite(packageFile, flags);
    }

    private static PackageLite parseMonolithicPackageLite(File packageFile, int flags) throws PackageParserException {
        Trace.traceBegin(262144L, "parseApkLite");
        ApkLite baseApk = parseApkLite(packageFile, flags);
        String packagePath = packageFile.getAbsolutePath();
        Trace.traceEnd(262144L);
        return new PackageLite(packagePath, baseApk.codePath, baseApk, null, null, null, null, null, null);
    }

    static PackageLite parseClusterPackageLite(File packageDir, int flags) throws PackageParserException {
        int[] splitRevisionCodes;
        String[] splitCodePaths;
        String[] configForSplits;
        File[] files = packageDir.listFiles();
        if (!ArrayUtils.isEmpty(files)) {
            if (files.length == 1 && files[0].isDirectory()) {
                return parseClusterPackageLite(files[0], flags);
            }
            String packageName = null;
            int versionCode = 0;
            Trace.traceBegin(262144L, "parseApkLite");
            ArrayMap<String, ApkLite> apks = new ArrayMap<>();
            for (File file : files) {
                if (isApkFile(file)) {
                    ApkLite lite = parseApkLite(file, flags);
                    if (packageName == null) {
                        packageName = lite.packageName;
                        versionCode = lite.versionCode;
                    } else if (!packageName.equals(lite.packageName)) {
                        throw new PackageParserException(-101, "Inconsistent package " + lite.packageName + " in " + file + "; expected " + packageName);
                    } else if (versionCode != lite.versionCode) {
                        throw new PackageParserException(-101, "Inconsistent version " + lite.versionCode + " in " + file + "; expected " + versionCode);
                    }
                    if (apks.put(lite.splitName, lite) != null) {
                        throw new PackageParserException(-101, "Split name " + lite.splitName + " defined more than once; most recent was " + file);
                    }
                }
            }
            Trace.traceEnd(262144L);
            ApkLite baseApk = apks.remove(null);
            if (baseApk != null) {
                int size = apks.size();
                String[] splitNames = null;
                boolean[] isFeatureSplits = null;
                String[] usesSplitNames = null;
                if (size > 0) {
                    String[] splitNames2 = new String[size];
                    isFeatureSplits = new boolean[size];
                    usesSplitNames = new String[size];
                    String[] configForSplits2 = new String[size];
                    String[] splitCodePaths2 = new String[size];
                    int[] splitRevisionCodes2 = new int[size];
                    splitNames = (String[]) apks.keySet().toArray(splitNames2);
                    Arrays.sort(splitNames, sSplitNameComparator);
                    for (int i = 0; i < size; i++) {
                        ApkLite apk = apks.get(splitNames[i]);
                        usesSplitNames[i] = apk.usesSplitName;
                        isFeatureSplits[i] = apk.isFeatureSplit;
                        configForSplits2[i] = apk.configForSplit;
                        splitCodePaths2[i] = apk.codePath;
                        splitRevisionCodes2[i] = apk.revisionCode;
                    }
                    configForSplits = configForSplits2;
                    splitCodePaths = splitCodePaths2;
                    splitRevisionCodes = splitRevisionCodes2;
                } else {
                    configForSplits = null;
                    splitCodePaths = null;
                    splitRevisionCodes = null;
                }
                String codePath = packageDir.getAbsolutePath();
                return new PackageLite(codePath, baseApk.codePath, baseApk, splitNames, isFeatureSplits, usesSplitNames, configForSplits, splitCodePaths, splitRevisionCodes);
            }
            throw new PackageParserException(-101, "Missing base APK in " + packageDir);
        }
        throw new PackageParserException(-100, "No packages found in split");
    }

    public Package parsePackage(File packageFile, int flags, boolean useCaches) throws PackageParserException {
        if (packageFile.isDirectory()) {
            return parseClusterPackage(packageFile, flags);
        }
        return parseMonolithicPackage(packageFile, flags);
    }

    public Package parsePackage(File packageFile, int flags) throws PackageParserException {
        return parsePackage(packageFile, flags, false);
    }

    private Package parseClusterPackage(File packageDir, int flags) throws PackageParserException {
        SplitAssetLoader assetLoader;
        PackageLite lite = parseClusterPackageLite(packageDir, 0);
        if (!this.mOnlyCoreApps || lite.coreApp) {
            SparseArray<int[]> splitDependencies = null;
            if (!lite.isolatedSplits || ArrayUtils.isEmpty(lite.splitNames)) {
                assetLoader = new DefaultSplitAssetLoader(lite, flags);
            } else {
                try {
                    splitDependencies = SplitAssetDependencyLoader.createDependenciesFromPackage(lite);
                    assetLoader = new SplitAssetDependencyLoader(lite, splitDependencies, flags);
                } catch (SplitDependencyLoader.IllegalDependencyException e) {
                    throw new PackageParserException(-101, e.getMessage());
                }
            }
            try {
                AssetManager assets = assetLoader.getBaseAssetManager();
                File baseApk = new File(lite.baseCodePath);
                Package pkg = parseBaseApk(baseApk, assets, flags);
                if (pkg != null) {
                    if (!ArrayUtils.isEmpty(lite.splitNames)) {
                        int num = lite.splitNames.length;
                        pkg.splitNames = lite.splitNames;
                        pkg.splitCodePaths = lite.splitCodePaths;
                        pkg.splitRevisionCodes = lite.splitRevisionCodes;
                        pkg.splitFlags = new int[num];
                        pkg.splitPrivateFlags = new int[num];
                        pkg.applicationInfo.splitNames = pkg.splitNames;
                        pkg.applicationInfo.splitDependencies = splitDependencies;
                        pkg.applicationInfo.splitClassLoaderNames = new String[num];
                        for (int i = 0; i < num; i++) {
                            AssetManager splitAssets = assetLoader.getSplitAssetManager(i);
                            parseSplitApk(pkg, i, splitAssets, flags);
                        }
                    }
                    pkg.setCodePath(lite.codePath);
                    pkg.setUse32bitAbi(lite.use32bitAbi);
                    return pkg;
                }
                throw new PackageParserException(-100, "Failed to parse base APK: " + baseApk);
            } finally {
                IoUtils.closeQuietly(assetLoader);
            }
        } else {
            throw new PackageParserException(PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED, "Not a coreApp: " + packageDir);
        }
    }

    public Package parseMonolithicPackage(File apkFile, int flags) throws PackageParserException {
        PackageLite lite = parseMonolithicPackageLite(apkFile, flags);
        if (!this.mOnlyCoreApps || lite.coreApp) {
            SplitAssetLoader assetLoader = new DefaultSplitAssetLoader(lite, flags);
            try {
                try {
                    Package pkg = parseBaseApk(apkFile, assetLoader.getBaseAssetManager(), flags);
                    pkg.setCodePath(apkFile.getCanonicalPath());
                    pkg.setUse32bitAbi(lite.use32bitAbi);
                    return pkg;
                } catch (IOException e) {
                    throw new PackageParserException(-102, "Failed to get path: " + apkFile, e);
                }
            } finally {
                IoUtils.closeQuietly(assetLoader);
            }
        } else {
            throw new PackageParserException(PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED, "Not a coreApp: " + apkFile);
        }
    }

    private Package parseBaseApk(File apkFile, AssetManager assets, int flags) throws PackageParserException {
        String volumeUuid;
        PackageParserException e;
        Exception e2;
        String apkPath = apkFile.getAbsolutePath();
        if (apkPath.startsWith("/mnt/expand/")) {
            int end = apkPath.indexOf(47, "/mnt/expand/".length());
            volumeUuid = apkPath.substring("/mnt/expand/".length(), end);
        } else {
            volumeUuid = null;
        }
        this.mParseError = 1;
        this.mArchiveSourcePath = apkFile.getAbsolutePath();
        XmlResourceParser parser = null;
        try {
            try {
                int cookie = assets.findCookieForPath(apkPath);
                if (cookie != 0) {
                    XmlResourceParser parser2 = assets.openXmlResourceParser(cookie, "AndroidManifest.xml");
                    try {
                        Resources res = new Resources(assets, this.mMetrics, null);
                        String[] outError = new String[1];
                        Package pkg = parseBaseApk(apkPath, res, parser2, flags, outError);
                        if (pkg != null) {
                            pkg.setVolumeUuid(volumeUuid);
                            pkg.setApplicationVolumeUuid(volumeUuid);
                            pkg.setBaseCodePath(apkPath);
                            pkg.setSigningDetails(SigningDetails.UNKNOWN);
                            IoUtils.closeQuietly(parser2);
                            return pkg;
                        }
                        throw new PackageParserException(this.mParseError, apkPath + " (at " + parser2.getPositionDescription() + "): " + outError[0]);
                    } catch (PackageParserException e3) {
                        throw e3;
                    } catch (Exception e4) {
                        e2 = e4;
                        throw new PackageParserException(-102, "Failed to read manifest from " + apkPath, e2);
                    } catch (Throwable th) {
                        e = th;
                        parser = parser2;
                        IoUtils.closeQuietly(parser);
                        throw e;
                    }
                } else {
                    throw new PackageParserException(-101, "Failed adding asset path: " + apkPath);
                }
            } catch (Throwable th2) {
                e = th2;
            }
        } catch (PackageParserException e5) {
            throw e5;
        } catch (Exception e6) {
            e2 = e6;
        }
    }

    private void parseSplitApk(Package pkg, int splitIndex, AssetManager assets, int flags) throws PackageParserException {
        PackageParserException e;
        Exception e2;
        String apkPath = pkg.splitCodePaths[splitIndex];
        this.mParseError = 1;
        this.mArchiveSourcePath = apkPath;
        XmlResourceParser parser = null;
        try {
            try {
                int cookie = assets.findCookieForPath(apkPath);
                if (cookie != 0) {
                    XmlResourceParser parser2 = assets.openXmlResourceParser(cookie, "AndroidManifest.xml");
                    try {
                        Resources res = new Resources(assets, this.mMetrics, null);
                        String[] outError = new String[1];
                        if (parseSplitApk(pkg, res, parser2, flags, splitIndex, outError) != null) {
                            IoUtils.closeQuietly(parser2);
                            return;
                        }
                        try {
                            int i = this.mParseError;
                            throw new PackageParserException(i, apkPath + " (at " + parser2.getPositionDescription() + "): " + outError[0]);
                        } catch (PackageParserException e3) {
                        } catch (Exception e4) {
                            e2 = e4;
                            throw new PackageParserException(-102, "Failed to read manifest from " + apkPath, e2);
                        } catch (Throwable th) {
                            e = th;
                            parser = parser2;
                            IoUtils.closeQuietly(parser);
                            throw e;
                        }
                    } catch (PackageParserException e5) {
                    } catch (Exception e6) {
                        e2 = e6;
                    } catch (Throwable th2) {
                        e = th2;
                        parser = parser2;
                    }
                } else {
                    throw new PackageParserException(-101, "Failed adding asset path: " + apkPath);
                }
            } catch (Throwable th3) {
                e = th3;
            }
        } catch (PackageParserException e7) {
            throw e7;
        } catch (Exception e8) {
            e2 = e8;
        } catch (Throwable th4) {
            e = th4;
        }
    }

    private Package parseSplitApk(Package pkg, Resources res, XmlResourceParser parser, int flags, int splitIndex, String[] outError) throws XmlPullParserException, IOException, PackageParserException {
        parsePackageSplitNames(parser, parser);
        this.mParseInstrumentationArgs = null;
        boolean foundApp = false;
        int outerDepth = parser.getDepth();
        while (true) {
            int type = parser.next();
            if (type == 1 || (type == 3 && parser.getDepth() <= outerDepth)) {
                break;
            } else if (!(type == 3 || type == 4)) {
                String tagName = parser.getName();
                if (!tagName.equals("application")) {
                    Slog.w(TAG, "Unknown element under <manifest>: " + parser.getName() + " at " + this.mArchiveSourcePath + " " + parser.getPositionDescription());
                    XmlUtils.skipCurrentTag(parser);
                } else if (foundApp) {
                    Slog.w(TAG, "<manifest> has more than one <application>");
                    XmlUtils.skipCurrentTag(parser);
                } else {
                    foundApp = true;
                    if (!parseSplitApplication(pkg, res, parser, flags, splitIndex, outError)) {
                        return null;
                    }
                }
            }
        }
        if (!foundApp) {
            outError[0] = "<manifest> does not contain an <application>";
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_EMPTY;
        }
        return pkg;
    }

    public static ArraySet<PublicKey> toSigningKeys(Signature[] signatures) throws CertificateException {
        ArraySet<PublicKey> keys = new ArraySet<>(signatures.length);
        for (Signature signature : signatures) {
            keys.add(signature.getPublicKey());
        }
        return keys;
    }

    public static void collectCertificates(Package pkg, boolean skipVerify) throws PackageParserException {
        collectCertificatesInternal(pkg, skipVerify);
        int childCount = pkg.childPackages != null ? pkg.childPackages.size() : 0;
        for (int i = 0; i < childCount; i++) {
            Package childPkg = pkg.childPackages.get(i);
            childPkg.mSigningDetails = pkg.mSigningDetails;
        }
    }

    private static void collectCertificatesInternal(Package pkg, boolean skipVerify) throws PackageParserException {
        pkg.mSigningDetails = SigningDetails.UNKNOWN;
        Trace.traceBegin(262144L, "collectCertificates");
        try {
            collectCertificates(pkg, new File(pkg.baseCodePath), skipVerify);
            if (!ArrayUtils.isEmpty(pkg.splitCodePaths)) {
                for (int i = 0; i < pkg.splitCodePaths.length; i++) {
                    collectCertificates(pkg, new File(pkg.splitCodePaths[i]), skipVerify);
                }
            }
        } finally {
            Trace.traceEnd(262144L);
        }
    }

    private static void collectCertificates(Package pkg, File apkFile, boolean skipVerify) throws PackageParserException {
        SigningDetails verified;
        String apkPath = apkFile.getAbsolutePath();
        int minSignatureScheme = ApkSignatureVerifier.getMinimumSignatureSchemeVersionForTargetSdk(pkg.applicationInfo.targetSdkVersion);
        if (pkg.applicationInfo.isStaticSharedLibrary()) {
            minSignatureScheme = 2;
        }
        if (skipVerify) {
            verified = ApkSignatureVerifier.unsafeGetCertsWithoutVerification(apkPath, 1);
        } else {
            verified = ApkSignatureVerifier.verify(apkPath, minSignatureScheme);
        }
        if (pkg.mSigningDetails == SigningDetails.UNKNOWN) {
            pkg.mSigningDetails = verified;
        } else if (!Signature.areExactMatch(pkg.mSigningDetails.signatures, verified.signatures)) {
            throw new PackageParserException(PackageManager.INSTALL_PARSE_FAILED_INCONSISTENT_CERTIFICATES, apkPath + " has mismatched certificates");
        }
    }

    private static AssetManager newConfiguredAssetManager() {
        AssetManager assetManager = new AssetManager();
        assetManager.setConfiguration(0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Build.VERSION.RESOURCES_SDK_INT);
        return assetManager;
    }

    public static ApkLite parseApkLite(File apkFile, int flags) throws PackageParserException {
        return parseApkLiteInner(apkFile, null, null, flags);
    }

    public static ApkLite parseApkLite(FileDescriptor fd, String debugPathName, int flags) throws PackageParserException {
        return parseApkLiteInner(null, fd, debugPathName, flags);
    }

    /* JADX WARN: Finally extract failed */
    private static ApkLite parseApkLiteInner(File apkFile, FileDescriptor fd, String debugPathName, int flags) throws PackageParserException {
        SigningDetails signingDetails;
        ApkAssets apkAssets;
        String apkPath = fd != null ? debugPathName : apkFile.getAbsolutePath();
        XmlResourceParser parser = null;
        ApkAssets apkAssets2 = null;
        boolean skipVerify = false;
        try {
            try {
                try {
                    if (fd != null) {
                        apkAssets = ApkAssets.loadFromFd(fd, debugPathName, 0, null);
                    } else {
                        apkAssets = ApkAssets.loadFromPath(apkPath);
                    }
                    parser = apkAssets2.openXml("AndroidManifest.xml");
                    if ((flags & 32) != 0) {
                        Package tempPkg = new Package((String) null);
                        if ((flags & 16) != 0) {
                            skipVerify = true;
                        }
                        Trace.traceBegin(262144L, "collectCertificates");
                        try {
                            collectCertificates(tempPkg, apkFile, skipVerify);
                            Trace.traceEnd(262144L);
                            signingDetails = tempPkg.mSigningDetails;
                        } catch (Throwable th) {
                            Trace.traceEnd(262144L);
                            throw th;
                        }
                    } else {
                        signingDetails = SigningDetails.UNKNOWN;
                    }
                    return parseApkLite(apkPath, parser, parser, signingDetails);
                } catch (IOException | RuntimeException | XmlPullParserException e) {
                    Slog.w(TAG, "Failed to parse " + apkPath, e);
                    throw new PackageParserException(-102, "Failed to parse " + apkPath, e);
                }
            } catch (IOException e2) {
                throw new PackageParserException(-100, "Failed to parse " + apkPath);
            }
        } finally {
            IoUtils.closeQuietly(parser);
            if (apkAssets2 != null) {
                try {
                    apkAssets2.close();
                } catch (Throwable th2) {
                }
            }
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
        if (requireFilename && !FileUtils.isValidExtFilename(name)) {
            return "Invalid filename";
        }
        if (hasSep || !requireSeparator) {
            return null;
        }
        return "must have at least one '.' separator";
    }

    @Deprecated
    public static Pair<String, String> parsePackageSplitNames(XmlPullParser parser, AttributeSet attrs) throws IOException, XmlPullParserException, PackageParserException {
        int type;
        String error;
        do {
            type = parser.next();
            if (type == 2) {
                break;
            }
        } while (type != 1);
        if (type != 2) {
            throw new PackageParserException(PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED, "No start tag found");
        } else if (parser.getName().equals("manifest")) {
            String packageName = attrs.getAttributeValue(null, "package");
            if ("android".equals(packageName) || PackageParserExtPlugin.skipValidateName.call(null, packageName).booleanValue() || (error = validateName(packageName, true, true)) == null) {
                String splitName = attrs.getAttributeValue(null, "split");
                if (splitName != null) {
                    if (splitName.length() == 0) {
                        splitName = null;
                    } else {
                        String error2 = validateName(splitName, false, false);
                        if (error2 != null) {
                            throw new PackageParserException(PackageManager.INSTALL_PARSE_FAILED_BAD_PACKAGE_NAME, "Invalid manifest split: " + error2);
                        }
                    }
                }
                return Pair.create(packageName.intern(), splitName != null ? splitName.intern() : splitName);
            }
            throw new PackageParserException(PackageManager.INSTALL_PARSE_FAILED_BAD_PACKAGE_NAME, "Invalid manifest package: " + error);
        } else {
            throw new PackageParserException(PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED, "No <manifest> tag");
        }
    }

    private static ApkLite parseApkLite(String codePath, XmlPullParser parser, AttributeSet attrs, SigningDetails signingDetails) throws IOException, XmlPullParserException, PackageParserException {
        int targetSdkVersion;
        int type;
        int overlayPriority;
        boolean overlayIsStatic;
        String targetPackage;
        int minSdkVersion;
        Pair<String, String> packageSplit = parsePackageSplitNames(parser, attrs);
        int installLocation = -1;
        int versionCode = 0;
        int versionCodeMajor = 0;
        int i = 0;
        int minSdkVersion2 = 1;
        int revisionCode = 0;
        boolean coreApp = false;
        boolean debuggable = false;
        boolean overlayIsStatic2 = false;
        int overlayPriority2 = 0;
        boolean isolatedSplits = false;
        boolean isFeatureSplit = false;
        boolean isSplitRequired = false;
        String configForSplit = null;
        for (int i2 = 0; i2 < attrs.getAttributeCount(); i2++) {
            String attr = attrs.getAttributeName(i2);
            if (attr.equals("installLocation")) {
                installLocation = attrs.getAttributeIntValue(i2, -1);
            } else if (attr.equals("versionCode")) {
                versionCode = attrs.getAttributeIntValue(i2, 0);
            } else if (attr.equals("versionCodeMajor")) {
                versionCodeMajor = attrs.getAttributeIntValue(i2, 0);
            } else if (attr.equals("revisionCode")) {
                revisionCode = attrs.getAttributeIntValue(i2, 0);
            } else if (attr.equals("coreApp")) {
                coreApp = attrs.getAttributeBooleanValue(i2, false);
            } else if (attr.equals("isolatedSplits")) {
                isolatedSplits = attrs.getAttributeBooleanValue(i2, false);
            } else if (attr.equals("configForSplit")) {
                configForSplit = attrs.getAttributeValue(i2);
            } else if (attr.equals("isFeatureSplit")) {
                isFeatureSplit = attrs.getAttributeBooleanValue(i2, false);
            } else if (attr.equals("isSplitRequired")) {
                isSplitRequired = attrs.getAttributeBooleanValue(i2, false);
            }
        }
        int i3 = parser.getDepth();
        int searchDepth = i3 + 1;
        List<VerifierInfo> verifiers = new ArrayList<>();
        boolean multiArch = false;
        boolean use32bitAbi = false;
        boolean extractNativeLibs = true;
        boolean useEmbeddedDex = false;
        String usesSplitName = null;
        String targetPackage2 = null;
        int rollbackDataPolicy = 0;
        String requiredSystemPropertyName = null;
        String requiredSystemPropertyValue = null;
        while (true) {
            int type2 = parser.next();
            targetSdkVersion = i;
            if (type2 == 1) {
                type = type2;
                break;
            }
            if (type2 == 3 && parser.getDepth() < searchDepth) {
                type = type2;
                break;
            }
            if (!(type2 == 3 || type2 == 4 || parser.getDepth() != searchDepth)) {
                if ("package-verifier".equals(parser.getName())) {
                    VerifierInfo verifier = parseVerifier(attrs);
                    if (verifier != null) {
                        verifiers.add(verifier);
                    }
                } else if ("application".equals(parser.getName())) {
                    for (int i4 = 0; i4 < attrs.getAttributeCount(); i4++) {
                        String attr2 = attrs.getAttributeName(i4);
                        if ("debuggable".equals(attr2)) {
                            debuggable = attrs.getAttributeBooleanValue(i4, false);
                        }
                        if ("multiArch".equals(attr2)) {
                            multiArch = attrs.getAttributeBooleanValue(i4, false);
                        }
                        if ("use32bitAbi".equals(attr2)) {
                            use32bitAbi = attrs.getAttributeBooleanValue(i4, false);
                        }
                        if ("extractNativeLibs".equals(attr2)) {
                            extractNativeLibs = attrs.getAttributeBooleanValue(i4, true);
                        }
                        if ("useEmbeddedDex".equals(attr2)) {
                            useEmbeddedDex = attrs.getAttributeBooleanValue(i4, false);
                        }
                        if (attr2.equals("rollbackDataPolicy")) {
                            rollbackDataPolicy = attrs.getAttributeIntValue(i4, 0);
                        }
                    }
                    i = targetSdkVersion;
                    minSdkVersion2 = minSdkVersion2;
                } else if ("overlay".equals(parser.getName())) {
                    for (int i5 = 0; i5 < attrs.getAttributeCount(); i5++) {
                        String attr3 = attrs.getAttributeName(i5);
                        if ("requiredSystemPropertyName".equals(attr3)) {
                            requiredSystemPropertyName = attrs.getAttributeValue(i5);
                        } else if ("requiredSystemPropertyValue".equals(attr3)) {
                            requiredSystemPropertyValue = attrs.getAttributeValue(i5);
                        } else if ("targetPackage".equals(attr3)) {
                            targetPackage2 = attrs.getAttributeValue(i5);
                        } else if ("isStatic".equals(attr3)) {
                            overlayIsStatic2 = attrs.getAttributeBooleanValue(i5, false);
                        } else if ("priority".equals(attr3)) {
                            overlayPriority2 = attrs.getAttributeIntValue(i5, 0);
                        }
                    }
                    i = targetSdkVersion;
                    minSdkVersion2 = minSdkVersion2;
                } else if ("uses-split".equals(parser.getName())) {
                    if (usesSplitName != null) {
                        Slog.w(TAG, "Only one <uses-split> permitted. Ignoring others.");
                    } else {
                        usesSplitName = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "name");
                        if (usesSplitName != null) {
                            i = targetSdkVersion;
                            minSdkVersion2 = minSdkVersion2;
                        } else {
                            throw new PackageParserException(PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED, "<uses-split> tag requires 'android:name' attribute");
                        }
                    }
                } else if ("uses-sdk".equals(parser.getName())) {
                    int i6 = 0;
                    int minSdkVersion3 = minSdkVersion2;
                    while (i6 < attrs.getAttributeCount()) {
                        String attr4 = attrs.getAttributeName(i6);
                        if ("targetSdkVersion".equals(attr4)) {
                            targetSdkVersion = attrs.getAttributeIntValue(i6, 0);
                        }
                        if ("minSdkVersion".equals(attr4)) {
                            minSdkVersion = attrs.getAttributeIntValue(i6, 1);
                        } else {
                            minSdkVersion = minSdkVersion3;
                        }
                        i6++;
                        minSdkVersion3 = minSdkVersion;
                    }
                    minSdkVersion2 = minSdkVersion3;
                    i = targetSdkVersion;
                }
            }
            i = targetSdkVersion;
            minSdkVersion2 = minSdkVersion2;
        }
        if (!checkRequiredSystemProperties(requiredSystemPropertyName, requiredSystemPropertyValue)) {
            Slog.i(TAG, "Skipping target and overlay pair " + targetPackage2 + " and " + codePath + ": overlay ignored due to required system property: " + requiredSystemPropertyName + " with value: " + requiredSystemPropertyValue);
            targetPackage = null;
            overlayIsStatic = false;
            overlayPriority = 0;
        } else {
            targetPackage = targetPackage2;
            overlayIsStatic = overlayIsStatic2;
            overlayPriority = overlayPriority2;
        }
        String requiredSystemPropertyName2 = packageSplit.first;
        return new ApkLite(codePath, requiredSystemPropertyName2, packageSplit.second, isFeatureSplit, configForSplit, usesSplitName, isSplitRequired, versionCode, versionCodeMajor, revisionCode, installLocation, verifiers, signingDetails, coreApp, debuggable, false, multiArch, use32bitAbi, useEmbeddedDex, extractNativeLibs, isolatedSplits, targetPackage, overlayIsStatic, overlayPriority, minSdkVersion2, targetSdkVersion, rollbackDataPolicy);
    }

    private boolean parseBaseApkChild(Package parentPkg, Resources res, XmlResourceParser parser, int flags, String[] outError) throws XmlPullParserException, IOException {
        String childPackageName = parser.getAttributeValue(null, "package");
        if (validateName(childPackageName, true, false) != null) {
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_BAD_PACKAGE_NAME;
            return false;
        } else if (childPackageName.equals(parentPkg.packageName)) {
            String message = "Child package name cannot be equal to parent package name: " + parentPkg.packageName;
            Slog.w(TAG, message);
            outError[0] = message;
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
            return false;
        } else if (parentPkg.hasChildPackage(childPackageName)) {
            String message2 = "Duplicate child package:" + childPackageName;
            Slog.w(TAG, message2);
            outError[0] = message2;
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
            return false;
        } else {
            Package childPkg = new Package(childPackageName);
            childPkg.mVersionCode = parentPkg.mVersionCode;
            childPkg.baseRevisionCode = parentPkg.baseRevisionCode;
            childPkg.mVersionName = parentPkg.mVersionName;
            childPkg.applicationInfo.targetSdkVersion = parentPkg.applicationInfo.targetSdkVersion;
            childPkg.applicationInfo.minSdkVersion = parentPkg.applicationInfo.minSdkVersion;
            Package childPkg2 = parseBaseApkCommon(childPkg, CHILD_PACKAGE_TAGS, res, parser, flags, outError);
            if (childPkg2 == null) {
                return false;
            }
            if (parentPkg.childPackages == null) {
                parentPkg.childPackages = new ArrayList<>();
            }
            parentPkg.childPackages.add(childPkg2);
            childPkg2.parentPackage = parentPkg;
            return true;
        }
    }

    private Package parseBaseApk(String apkPath, Resources res, XmlResourceParser parser, int flags, String[] outError) throws XmlPullParserException, IOException {
        try {
            Pair<String, String> packageSplit = parsePackageSplitNames(parser, parser);
            String pkgName = packageSplit.first;
            String splitName = packageSplit.second;
            if (!TextUtils.isEmpty(splitName)) {
                outError[0] = "Expected base APK, but found split " + splitName;
                this.mParseError = PackageManager.INSTALL_PARSE_FAILED_BAD_PACKAGE_NAME;
                return null;
            }
            Package pkg = new Package(pkgName);
            TypedArray sa = res.obtainAttributes(parser, R.styleable.AndroidManifest);
            pkg.mVersionCode = sa.getInteger(1, 0);
            pkg.mVersionCodeMajor = sa.getInteger(11, 0);
            pkg.applicationInfo.setVersionCode(pkg.getLongVersionCode());
            pkg.baseRevisionCode = sa.getInteger(5, 0);
            pkg.mVersionName = sa.getNonConfigurationString(2, 0);
            if (pkg.mVersionName != null) {
                pkg.mVersionName = pkg.mVersionName.intern();
            }
            pkg.coreApp = parser.getAttributeBooleanValue(null, "coreApp", false);
            boolean isolatedSplits = sa.getBoolean(6, false);
            if (isolatedSplits) {
                pkg.applicationInfo.privateFlags |= 32768;
            }
            pkg.mCompileSdkVersion = sa.getInteger(9, 0);
            pkg.applicationInfo.compileSdkVersion = pkg.mCompileSdkVersion;
            pkg.mCompileSdkVersionCodename = sa.getNonConfigurationString(10, 0);
            if (pkg.mCompileSdkVersionCodename != null) {
                pkg.mCompileSdkVersionCodename = pkg.mCompileSdkVersionCodename.intern();
            }
            pkg.applicationInfo.compileSdkVersionCodename = pkg.mCompileSdkVersionCodename;
            sa.recycle();
            return parseBaseApkCommon(pkg, null, res, parser, flags, outError);
        } catch (PackageParserException e) {
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_BAD_PACKAGE_NAME;
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:248:0x0770, code lost:
        if (r18 != false) goto L_0x0783;
     */
    /* JADX WARN: Code restructure failed: missing block: B:250:0x0778, code lost:
        if (r36.instrumentation.size() != 0) goto L_0x0783;
     */
    /* JADX WARN: Code restructure failed: missing block: B:251:0x077a, code lost:
        r41[0] = "<manifest> does not contain an <application> or <instrumentation>";
        r35.mParseError = android.content.pm.PackageManager.INSTALL_PARSE_FAILED_MANIFEST_EMPTY;
     */
    /* JADX WARN: Code restructure failed: missing block: B:252:0x0783, code lost:
        r0 = android.content.pm.PackageParser.NEW_PERMISSIONS.length;
        r1 = null;
        r2 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:253:0x0788, code lost:
        if (r2 >= r0) goto L_0x07d1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:254:0x078a, code lost:
        r3 = android.content.pm.PackageParser.NEW_PERMISSIONS[r2];
     */
    /* JADX WARN: Code restructure failed: missing block: B:255:0x0794, code lost:
        if (r36.applicationInfo.targetSdkVersion < r3.sdkVersion) goto L_0x0797;
     */
    /* JADX WARN: Code restructure failed: missing block: B:257:0x079f, code lost:
        if (r36.requestedPermissions.contains(r3.name) != false) goto L_0x07ce;
     */
    /* JADX WARN: Code restructure failed: missing block: B:258:0x07a1, code lost:
        if (r1 != null) goto L_0x07b6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:259:0x07a3, code lost:
        r1 = new java.lang.StringBuilder(128);
        r1.append(r36.packageName);
        r1.append(": compat added ");
     */
    /* JADX WARN: Code restructure failed: missing block: B:260:0x07b6, code lost:
        r1.append(' ');
     */
    /* JADX WARN: Code restructure failed: missing block: B:261:0x07bb, code lost:
        r1.append(r3.name);
        r36.requestedPermissions.add(r3.name);
        r36.implicitPermissions.add(r3.name);
     */
    /* JADX WARN: Code restructure failed: missing block: B:262:0x07ce, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:263:0x07d1, code lost:
        if (r1 == null) goto L_0x07da;
     */
    /* JADX WARN: Code restructure failed: missing block: B:264:0x07d3, code lost:
        android.util.Slog.i(android.content.pm.PackageParser.TAG, r1.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:265:0x07da, code lost:
        r2 = ((android.permission.PermissionManager) android.app.ActivityThread.currentApplication().getSystemService(android.permission.PermissionManager.class)).getSplitPermissions();
        r3 = r2.size();
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:266:0x07ef, code lost:
        if (r4 >= r3) goto L_0x084e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:267:0x07f1, code lost:
        r5 = r2.get(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:268:0x07ff, code lost:
        if (r36.applicationInfo.targetSdkVersion >= r5.getTargetSdk()) goto L_0x0843;
     */
    /* JADX WARN: Code restructure failed: missing block: B:270:0x080b, code lost:
        if (r36.requestedPermissions.contains(r5.getSplitPermission()) != false) goto L_0x0812;
     */
    /* JADX WARN: Code restructure failed: missing block: B:271:0x080d, code lost:
        r25 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:272:0x0812, code lost:
        r8 = r5.getNewPermissions();
        r15 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:273:0x0817, code lost:
        r25 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:274:0x081d, code lost:
        if (r15 >= r8.size()) goto L_0x0847;
     */
    /* JADX WARN: Code restructure failed: missing block: B:275:0x081f, code lost:
        r0 = r8.get(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:276:0x082d, code lost:
        if (r36.requestedPermissions.contains(r0) != false) goto L_0x0839;
     */
    /* JADX WARN: Code restructure failed: missing block: B:277:0x082f, code lost:
        r36.requestedPermissions.add(r0);
        r36.implicitPermissions.add(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:278:0x0839, code lost:
        r15 = r15 + 1;
        r0 = r25;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:279:0x0843, code lost:
        r25 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:280:0x0847, code lost:
        r4 = r4 + 1;
        r0 = r25;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:281:0x084e, code lost:
        android.content.pm.PackageParserExtPlugin.adjustPermissionInParseBaseApkCommon.call(null, r36);
     */
    /* JADX WARN: Code restructure failed: missing block: B:282:0x085e, code lost:
        if (r13 < 0) goto L_0x0869;
     */
    /* JADX WARN: Code restructure failed: missing block: B:283:0x0860, code lost:
        if (r13 <= 0) goto L_0x0871;
     */
    /* JADX WARN: Code restructure failed: missing block: B:285:0x0867, code lost:
        if (r36.applicationInfo.targetSdkVersion < 4) goto L_0x0871;
     */
    /* JADX WARN: Code restructure failed: missing block: B:286:0x0869, code lost:
        r36.applicationInfo.flags |= 512;
     */
    /* JADX WARN: Code restructure failed: missing block: B:287:0x0871, code lost:
        if (r12 == 0) goto L_0x087b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:288:0x0873, code lost:
        r36.applicationInfo.flags |= 1024;
     */
    /* JADX WARN: Code restructure failed: missing block: B:289:0x087b, code lost:
        if (r14 < 0) goto L_0x0886;
     */
    /* JADX WARN: Code restructure failed: missing block: B:290:0x087d, code lost:
        if (r14 <= 0) goto L_0x088e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:292:0x0884, code lost:
        if (r36.applicationInfo.targetSdkVersion < 4) goto L_0x088e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:293:0x0886, code lost:
        r36.applicationInfo.flags |= 2048;
     */
    /* JADX WARN: Code restructure failed: missing block: B:294:0x088e, code lost:
        if (r16 < 0) goto L_0x089a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:295:0x0890, code lost:
        if (r16 <= 0) goto L_0x08a3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:297:0x0898, code lost:
        if (r36.applicationInfo.targetSdkVersion < 9) goto L_0x08a3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:298:0x089a, code lost:
        r36.applicationInfo.flags |= 524288;
     */
    /* JADX WARN: Code restructure failed: missing block: B:299:0x08a3, code lost:
        if (r22 < 0) goto L_0x08ae;
     */
    /* JADX WARN: Code restructure failed: missing block: B:300:0x08a5, code lost:
        if (r22 <= 0) goto L_0x08b6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:302:0x08ac, code lost:
        if (r36.applicationInfo.targetSdkVersion < 4) goto L_0x08b6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:303:0x08ae, code lost:
        r36.applicationInfo.flags |= 4096;
     */
    /* JADX WARN: Code restructure failed: missing block: B:304:0x08b6, code lost:
        if (r23 < 0) goto L_0x08c1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:305:0x08b8, code lost:
        if (r23 <= 0) goto L_0x08c9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:307:0x08bf, code lost:
        if (r36.applicationInfo.targetSdkVersion < 4) goto L_0x08c9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:308:0x08c1, code lost:
        r36.applicationInfo.flags |= 8192;
     */
    /* JADX WARN: Code restructure failed: missing block: B:310:0x08cf, code lost:
        if (r36.applicationInfo.usesCompatibilityMode() == false) goto L_0x08d4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:311:0x08d1, code lost:
        adjustPackageToBeUnresizeableAndUnpipable(r36);
     */
    /* JADX WARN: Code restructure failed: missing block: B:312:0x08d4, code lost:
        return r36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0260, code lost:
        r41[0] = "<overlay> priority must be between 0 and 9999";
        r35.mParseError = android.content.pm.PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0268, code lost:
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.content.pm.PackageParser.Package parseBaseApkCommon(android.content.pm.PackageParser.Package r36, java.util.Set<java.lang.String> r37, android.content.res.Resources r38, android.content.res.XmlResourceParser r39, int r40, java.lang.String[] r41) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 2261
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.PackageParser.parseBaseApkCommon(android.content.pm.PackageParser$Package, java.util.Set, android.content.res.Resources, android.content.res.XmlResourceParser, int, java.lang.String[]):android.content.pm.PackageParser$Package");
    }

    public static boolean checkRequiredSystemProperties(String rawPropNames, String rawPropValues) {
        if (!TextUtils.isEmpty(rawPropNames) && !TextUtils.isEmpty(rawPropValues)) {
            String[] propNames = rawPropNames.split(",");
            String[] propValues = rawPropValues.split(",");
            if (propNames.length != propValues.length) {
                Slog.w(TAG, "Disabling overlay - property :'" + rawPropNames + "=" + rawPropValues + "' - require both requiredSystemPropertyName AND requiredSystemPropertyValue lists to have the same size.");
                return false;
            }
            for (int i = 0; i < propNames.length; i++) {
                String currValue = SystemProperties.get(propNames[i]);
                if (!TextUtils.equals(currValue, propValues[i])) {
                    return false;
                }
            }
            return true;
        } else if (TextUtils.isEmpty(rawPropNames) && TextUtils.isEmpty(rawPropValues)) {
            return true;
        } else {
            Slog.w(TAG, "Disabling overlay - incomplete property :'" + rawPropNames + "=" + rawPropValues + "' - require both requiredSystemPropertyName AND requiredSystemPropertyValue to be specified.");
            return false;
        }
    }

    private void adjustPackageToBeUnresizeableAndUnpipable(Package pkg) {
        Iterator<Activity> it = pkg.activities.iterator();
        while (it.hasNext()) {
            Activity a = it.next();
            a.info.resizeMode = 0;
            a.info.flags &= -4194305;
        }
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

    public static int computeTargetSdkVersion(int targetVers, String targetCode, String[] platformSdkCodenames, String[] outError) {
        if (targetCode == null) {
            return targetVers;
        }
        if (matchTargetCode(platformSdkCodenames, targetCode)) {
            return 10000;
        }
        if (platformSdkCodenames.length > 0) {
            outError[0] = "Requires development platform " + targetCode + " (current platform is any of " + Arrays.toString(platformSdkCodenames) + ")";
            return -1;
        }
        outError[0] = "Requires development platform " + targetCode + " but this is a release platform.";
        return -1;
    }

    public static int computeMinSdkVersion(int minVers, String minCode, int platformSdkVersion, String[] platformSdkCodenames, String[] outError) {
        if (minCode == null) {
            if (minVers <= platformSdkVersion) {
                return minVers;
            }
            outError[0] = "Requires newer sdk version #" + minVers + " (current version is #" + platformSdkVersion + ")";
            return -1;
        } else if (matchTargetCode(platformSdkCodenames, minCode)) {
            return 10000;
        } else {
            if (platformSdkCodenames.length > 0) {
                outError[0] = "Requires development platform " + minCode + " (current platform is any of " + Arrays.toString(platformSdkCodenames) + ")";
            } else {
                outError[0] = "Requires development platform " + minCode + " but this is a release platform.";
            }
            return -1;
        }
    }

    private FeatureInfo parseUsesFeature(Resources res, AttributeSet attrs) {
        FeatureInfo fi = new FeatureInfo();
        TypedArray sa = res.obtainAttributes(attrs, R.styleable.AndroidManifestUsesFeature);
        fi.name = sa.getNonResourceString(0);
        fi.version = sa.getInt(3, 0);
        if (fi.name == null) {
            fi.reqGlEsVersion = sa.getInt(1, 0);
        }
        if (sa.getBoolean(2, true)) {
            fi.flags |= 1;
        }
        sa.recycle();
        return fi;
    }

    private boolean parseUsesStaticLibrary(Package pkg, Resources res, XmlResourceParser parser, String[] outError) throws XmlPullParserException, IOException {
        TypedArray sa = res.obtainAttributes(parser, R.styleable.AndroidManifestUsesStaticLibrary);
        String lname = sa.getNonResourceString(0);
        int version = sa.getInt(1, -1);
        String certSha256Digest = sa.getNonResourceString(2);
        sa.recycle();
        if (lname == null || version < 0 || certSha256Digest == null) {
            outError[0] = "Bad uses-static-library declaration name: " + lname + " version: " + version + " certDigest" + certSha256Digest;
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
            XmlUtils.skipCurrentTag(parser);
            return false;
        } else if (pkg.usesStaticLibraries == null || !pkg.usesStaticLibraries.contains(lname)) {
            String lname2 = lname.intern();
            String certSha256Digest2 = certSha256Digest.replace(SettingsStringUtil.DELIMITER, "").toLowerCase();
            String[] additionalCertSha256Digests = EmptyArray.STRING;
            if (pkg.applicationInfo.targetSdkVersion >= 27) {
                additionalCertSha256Digests = parseAdditionalCertificates(res, parser, outError);
                if (additionalCertSha256Digests == null) {
                    return false;
                }
            } else {
                XmlUtils.skipCurrentTag(parser);
            }
            String[] certSha256Digests = new String[additionalCertSha256Digests.length + 1];
            certSha256Digests[0] = certSha256Digest2;
            System.arraycopy(additionalCertSha256Digests, 0, certSha256Digests, 1, additionalCertSha256Digests.length);
            pkg.usesStaticLibraries = ArrayUtils.add(pkg.usesStaticLibraries, lname2);
            pkg.usesStaticLibrariesVersions = ArrayUtils.appendLong(pkg.usesStaticLibrariesVersions, version, true);
            pkg.usesStaticLibrariesCertDigests = (String[][]) ArrayUtils.appendElement(String[].class, pkg.usesStaticLibrariesCertDigests, certSha256Digests, true);
            return true;
        } else {
            outError[0] = "Depending on multiple versions of static library " + lname;
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
            XmlUtils.skipCurrentTag(parser);
            return false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0076, code lost:
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String[] parseAdditionalCertificates(android.content.res.Resources r10, android.content.res.XmlResourceParser r11, java.lang.String[] r12) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r9 = this;
            java.lang.String[] r0 = libcore.util.EmptyArray.STRING
            int r1 = r11.getDepth()
        L_0x0006:
            int r2 = r11.next()
            r3 = r2
            r4 = 1
            if (r2 == r4) goto L_0x0076
            r2 = 3
            if (r3 != r2) goto L_0x0017
            int r4 = r11.getDepth()
            if (r4 <= r1) goto L_0x0076
        L_0x0017:
            if (r3 == r2) goto L_0x0006
            r2 = 4
            if (r3 != r2) goto L_0x001d
            goto L_0x0006
        L_0x001d:
            java.lang.String r2 = r11.getName()
            java.lang.String r4 = "additional-certificate"
            boolean r4 = r2.equals(r4)
            if (r4 == 0) goto L_0x0072
            int[] r4 = com.android.internal.R.styleable.AndroidManifestAdditionalCertificate
            android.content.res.TypedArray r4 = r10.obtainAttributes(r11, r4)
            r5 = 0
            java.lang.String r6 = r4.getNonResourceString(r5)
            r4.recycle()
            boolean r7 = android.text.TextUtils.isEmpty(r6)
            if (r7 == 0) goto L_0x005c
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "Bad additional-certificate declaration with empty certDigest:"
            r7.append(r8)
            r7.append(r6)
            java.lang.String r7 = r7.toString()
            r12[r5] = r7
            r5 = -108(0xffffffffffffff94, float:NaN)
            r9.mParseError = r5
            com.android.internal.util.XmlUtils.skipCurrentTag(r11)
            r4.recycle()
            r5 = 0
            return r5
        L_0x005c:
            java.lang.String r5 = ":"
            java.lang.String r7 = ""
            java.lang.String r5 = r6.replace(r5, r7)
            java.lang.String r5 = r5.toLowerCase()
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            java.lang.Object[] r6 = com.android.internal.util.ArrayUtils.appendElement(r6, r0, r5)
            r0 = r6
            java.lang.String[] r0 = (java.lang.String[]) r0
            goto L_0x0075
        L_0x0072:
            com.android.internal.util.XmlUtils.skipCurrentTag(r11)
        L_0x0075:
            goto L_0x0006
        L_0x0076:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.PackageParser.parseAdditionalCertificates(android.content.res.Resources, android.content.res.XmlResourceParser, java.lang.String[]):java.lang.String[]");
    }

    private boolean parseUsesPermission(Package pkg, Resources res, XmlResourceParser parser) throws XmlPullParserException, IOException {
        Callback callback;
        Callback callback2;
        TypedArray sa = res.obtainAttributes(parser, R.styleable.AndroidManifestUsesPermission);
        String name = sa.getNonResourceString(0);
        int maxSdkVersion = 0;
        TypedValue val = sa.peekValue(1);
        if (val != null && val.type >= 16 && val.type <= 31) {
            maxSdkVersion = val.data;
        }
        String requiredFeature = sa.getNonConfigurationString(2, 0);
        String requiredNotfeature = sa.getNonConfigurationString(3, 0);
        sa.recycle();
        XmlUtils.skipCurrentTag(parser);
        if (name == null) {
            return true;
        }
        if (maxSdkVersion != 0 && maxSdkVersion < Build.VERSION.RESOURCES_SDK_INT) {
            return true;
        }
        if (requiredFeature != null && (callback2 = this.mCallback) != null && !callback2.hasFeature(requiredFeature)) {
            return true;
        }
        if (requiredNotfeature != null && (callback = this.mCallback) != null && callback.hasFeature(requiredNotfeature)) {
            return true;
        }
        int index = pkg.requestedPermissions.indexOf(name);
        if (index == -1) {
            pkg.requestedPermissions.add(name.intern());
        } else {
            Slog.w(TAG, "Ignoring duplicate uses-permissions/uses-permissions-sdk-m: " + name + " in package: " + pkg.packageName + " at: " + parser.getPositionDescription());
        }
        return true;
    }

    public static String buildClassName(String pkg, CharSequence clsSeq, String[] outError) {
        if (clsSeq == null || clsSeq.length() <= 0) {
            outError[0] = "Empty class name in package " + pkg;
            return null;
        }
        String cls = clsSeq.toString();
        char c = cls.charAt(0);
        if (c == '.') {
            return pkg + cls;
        } else if (cls.indexOf(46) >= 0) {
            return cls;
        } else {
            return pkg + '.' + cls;
        }
    }

    private static String buildCompoundName(String pkg, CharSequence procSeq, String type, String[] outError) {
        String proc = procSeq.toString();
        char c = proc.charAt(0);
        if (pkg == null || c != ':') {
            String nameError = validateName(proc, true, false);
            if (nameError == null || "system".equals(proc)) {
                return proc;
            }
            outError[0] = "Invalid " + type + " name " + proc + " in package " + pkg + ": " + nameError;
            return null;
        } else if (proc.length() < 2) {
            outError[0] = "Bad " + type + " name " + proc + " in package " + pkg + ": must be at least two characters";
            return null;
        } else {
            String subName = proc.substring(1);
            String nameError2 = validateName(subName, false, false);
            if (nameError2 != null) {
                outError[0] = "Invalid " + type + " name " + proc + " in package " + pkg + ": " + nameError2;
                return null;
            }
            return pkg + proc;
        }
    }

    public static String buildProcessName(String pkg, String defProc, CharSequence procSeq, int flags, String[] separateProcesses, String[] outError) {
        if (!((flags & 2) == 0 || "system".equals(procSeq))) {
            return defProc != null ? defProc : pkg;
        }
        if (separateProcesses != null) {
            for (int i = separateProcesses.length - 1; i >= 0; i--) {
                String sp = separateProcesses[i];
                if (sp.equals(pkg) || sp.equals(defProc) || sp.equals(procSeq)) {
                    return pkg;
                }
            }
        }
        if (procSeq == null || procSeq.length() <= 0) {
            return defProc;
        }
        return TextUtils.safeIntern(buildCompoundName(pkg, procSeq, "process", outError));
    }

    public static String buildTaskAffinityName(String pkg, String defProc, CharSequence procSeq, String[] outError) {
        if (procSeq == null) {
            return defProc;
        }
        if (procSeq.length() <= 0) {
            return null;
        }
        return buildCompoundName(pkg, procSeq, "taskAffinity", outError);
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x0202, code lost:
        r4 = r7.keySet();
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0210, code lost:
        if (r4.removeAll(r9.keySet()) == false) goto L_0x0230;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0212, code lost:
        r26[0] = "Package" + r23.packageName + " AndroidManifext.xml 'key-set' and 'public-key' names must be distinct.";
        r22.mParseError = android.content.pm.PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x022f, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0230, code lost:
        r23.mKeySetMapping = new android.util.ArrayMap<>();
        r5 = r9.entrySet().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0243, code lost:
        if (r5.hasNext() == false) goto L_0x02eb;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0245, code lost:
        r12 = r5.next();
        r13 = r12.getKey();
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x025d, code lost:
        if (r12.getValue().size() != 0) goto L_0x0283;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x025f, code lost:
        android.util.Slog.w(android.content.pm.PackageParser.TAG, "Package" + r23.packageName + " AndroidManifext.xml 'key-set' " + r13 + " has no valid associated 'public-key'. Not including in package's defined key-sets.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0287, code lost:
        if (r10.contains(r13) == false) goto L_0x02ad;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0289, code lost:
        android.util.Slog.w(android.content.pm.PackageParser.TAG, "Package" + r23.packageName + " AndroidManifext.xml 'key-set' " + r13 + " contained improper 'public-key' tags. Not including in package's defined key-sets.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x02ad, code lost:
        r23.mKeySetMapping.put(r13, new android.util.ArraySet<>());
        r2 = r12.getValue().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x02c5, code lost:
        if (r2.hasNext() == false) goto L_0x02e5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x02c7, code lost:
        r3 = r2.next();
        r23.mKeySetMapping.get(r13).add(r7.get(r3));
        r2 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x02f5, code lost:
        if (r23.mKeySetMapping.keySet().containsAll(r8) == false) goto L_0x02fb;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x02f7, code lost:
        r23.mUpgradeKeySets = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x02fa, code lost:
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x02fb, code lost:
        r26[0] = "Package" + r23.packageName + " AndroidManifext.xml does not define all 'upgrade-key-set's .";
        r22.mParseError = android.content.pm.PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0318, code lost:
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean parseKeySets(android.content.pm.PackageParser.Package r23, android.content.res.Resources r24, android.content.res.XmlResourceParser r25, java.lang.String[] r26) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 793
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.PackageParser.parseKeySets(android.content.pm.PackageParser$Package, android.content.res.Resources, android.content.res.XmlResourceParser, java.lang.String[]):boolean");
    }

    private boolean parsePermissionGroup(Package owner, int flags, Resources res, XmlResourceParser parser, String[] outError) throws XmlPullParserException, IOException {
        TypedArray sa = res.obtainAttributes(parser, R.styleable.AndroidManifestPermissionGroup);
        int requestDetailResourceId = sa.getResourceId(12, 0);
        int backgroundRequestResourceId = sa.getResourceId(9, 0);
        int backgroundRequestDetailResourceId = sa.getResourceId(10, 0);
        PermissionGroup perm = new PermissionGroup(owner, requestDetailResourceId, backgroundRequestResourceId, backgroundRequestDetailResourceId);
        if (!parsePackageItemInfo(owner, perm.info, outError, "<permission-group>", sa, true, 2, 0, 1, 8, 5, 7)) {
            sa.recycle();
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
            return false;
        }
        perm.info.descriptionRes = sa.getResourceId(4, 0);
        perm.info.requestRes = sa.getResourceId(11, 0);
        perm.info.flags = sa.getInt(6, 0);
        perm.info.priority = sa.getInt(3, 0);
        sa.recycle();
        if (!parseAllMetaData(res, parser, "<permission-group>", perm, outError)) {
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
            return false;
        }
        owner.permissionGroups.add(perm);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0072  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean parsePermission(android.content.pm.PackageParser.Package r22, android.content.res.Resources r23, android.content.res.XmlResourceParser r24, java.lang.String[] r25) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 359
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.PackageParser.parsePermission(android.content.pm.PackageParser$Package, android.content.res.Resources, android.content.res.XmlResourceParser, java.lang.String[]):boolean");
    }

    private boolean parsePermissionTree(Package owner, Resources res, XmlResourceParser parser, String[] outError) throws XmlPullParserException, IOException {
        int index;
        Permission perm = new Permission(owner, (String) null);
        TypedArray sa = res.obtainAttributes(parser, R.styleable.AndroidManifestPermissionTree);
        if (!parsePackageItemInfo(owner, perm.info, outError, "<permission-tree>", sa, true, 2, 0, 1, 5, 3, 4)) {
            sa.recycle();
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
            return false;
        }
        sa.recycle();
        int index2 = perm.info.name.indexOf(46);
        if (index2 > 0) {
            index = perm.info.name.indexOf(46, index2 + 1);
        } else {
            index = index2;
        }
        if (index < 0) {
            outError[0] = "<permission-tree> name has less than three segments: " + perm.info.name;
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
            return false;
        }
        perm.info.descriptionRes = 0;
        perm.info.requestRes = 0;
        perm.info.protectionLevel = 0;
        perm.tree = true;
        if (!parseAllMetaData(res, parser, "<permission-tree>", perm, outError)) {
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
            return false;
        }
        owner.permissions.add(perm);
        return true;
    }

    private Instrumentation parseInstrumentation(Package owner, Resources res, XmlResourceParser parser, String[] outError) throws XmlPullParserException, IOException {
        TypedArray sa = res.obtainAttributes(parser, R.styleable.AndroidManifestInstrumentation);
        if (this.mParseInstrumentationArgs == null) {
            ParsePackageItemArgs parsePackageItemArgs = new ParsePackageItemArgs(owner, outError, 2, 0, 1, 8, 6, 7);
            this.mParseInstrumentationArgs = parsePackageItemArgs;
            parsePackageItemArgs.tag = "<instrumentation>";
        }
        this.mParseInstrumentationArgs.sa = sa;
        Instrumentation a = new Instrumentation(this.mParseInstrumentationArgs, new InstrumentationInfo());
        if (outError[0] != null) {
            sa.recycle();
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
            return null;
        }
        String str = sa.getNonResourceString(3);
        a.info.targetPackage = str != null ? str.intern() : null;
        String str2 = sa.getNonResourceString(9);
        a.info.targetProcesses = str2 != null ? str2.intern() : null;
        a.info.handleProfiling = sa.getBoolean(4, false);
        a.info.functionalTest = sa.getBoolean(5, false);
        sa.recycle();
        if (a.info.targetPackage == null) {
            outError[0] = "<instrumentation> does not specify targetPackage";
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
            return null;
        } else if (!parseAllMetaData(res, parser, "<instrumentation>", a, outError)) {
            this.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
            return null;
        } else {
            owner.instrumentation.add(a);
            return a;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:280:0x066d, code lost:
        r9[0] = "Bad static-library declaration name: " + r10 + " version: " + r13;
        r0.mParseError = android.content.pm.PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
        com.android.internal.util.XmlUtils.skipCurrentTag(r37);
     */
    /* JADX WARN: Code restructure failed: missing block: B:281:0x068f, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:319:0x07bc, code lost:
        if (android.text.TextUtils.isEmpty(r14.staticSharedLibName) == false) goto L_0x07cc;
     */
    /* JADX WARN: Code restructure failed: missing block: B:320:0x07be, code lost:
        r14.activities.add(r0.generateAppDetailsHiddenActivity(r14, r38, r9, r14.baseHardwareAccelerated));
     */
    /* JADX WARN: Code restructure failed: missing block: B:322:0x07ce, code lost:
        if (r21 == false) goto L_0x07d7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:323:0x07d0, code lost:
        java.util.Collections.sort(r14.activities, android.content.pm.PackageParser$$ExternalSyntheticLambda0.INSTANCE);
     */
    /* JADX WARN: Code restructure failed: missing block: B:324:0x07d7, code lost:
        if (r23 == false) goto L_0x07e0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:325:0x07d9, code lost:
        java.util.Collections.sort(r14.receivers, android.content.pm.PackageParser$$ExternalSyntheticLambda1.INSTANCE);
     */
    /* JADX WARN: Code restructure failed: missing block: B:326:0x07e0, code lost:
        if (r24 == false) goto L_0x07e9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:327:0x07e2, code lost:
        java.util.Collections.sort(r14.services, android.content.pm.PackageParser$$ExternalSyntheticLambda2.INSTANCE);
     */
    /* JADX WARN: Code restructure failed: missing block: B:328:0x07e9, code lost:
        setMaxAspectRatio(r35);
        setMinAspectRatio(r35);
        setSupportsSizeChanges(r35);
     */
    /* JADX WARN: Code restructure failed: missing block: B:329:0x07f6, code lost:
        if (hasDomainURLs(r35) == false) goto L_0x0801;
     */
    /* JADX WARN: Code restructure failed: missing block: B:330:0x07f8, code lost:
        r14.applicationInfo.privateFlags |= 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:331:0x0801, code lost:
        r14.applicationInfo.privateFlags &= -17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:332:0x0809, code lost:
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:364:?, code lost:
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean parseBaseApplication(android.content.pm.PackageParser.Package r35, android.content.res.Resources r36, android.content.res.XmlResourceParser r37, int r38, java.lang.String[] r39) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 2059
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.PackageParser.parseBaseApplication(android.content.pm.PackageParser$Package, android.content.res.Resources, android.content.res.XmlResourceParser, int, java.lang.String[]):boolean");
    }

    private static boolean hasDomainURLs(Package pkg) {
        if (pkg == null || pkg.activities == null) {
            return false;
        }
        ArrayList<Activity> activities = pkg.activities;
        int countActivities = activities.size();
        for (int n = 0; n < countActivities; n++) {
            Activity activity = activities.get(n);
            ArrayList<II> arrayList = activity.intents;
            if (arrayList != 0) {
                int countFilters = arrayList.size();
                for (int m = 0; m < countFilters; m++) {
                    ActivityIntentInfo aii = (ActivityIntentInfo) arrayList.get(m);
                    if (aii.hasAction("android.intent.action.VIEW") && aii.hasAction("android.intent.action.VIEW") && (aii.hasDataScheme(IntentFilter.SCHEME_HTTP) || aii.hasDataScheme(IntentFilter.SCHEME_HTTPS))) {
                        return true;
                    }
                }
                continue;
            }
        }
        return false;
    }

    private boolean parseSplitApplication(Package owner, Resources res, XmlResourceParser parser, int flags, int splitIndex, String[] outError) throws XmlPullParserException, IOException {
        int innerDepth;
        String classLoaderName;
        XmlResourceParser xmlResourceParser;
        ComponentInfo parsedComponent;
        PackageParser packageParser = this;
        Package r14 = owner;
        Resources resources = res;
        XmlResourceParser xmlResourceParser2 = parser;
        String[] strArr = outError;
        TypedArray sa = resources.obtainAttributes(xmlResourceParser2, R.styleable.AndroidManifestApplication);
        int i = 1;
        int i2 = 4;
        if (sa.getBoolean(7, true)) {
            int[] iArr = r14.splitFlags;
            iArr[splitIndex] = iArr[splitIndex] | 4;
        }
        String classLoaderName2 = sa.getString(46);
        int i3 = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
        boolean z = false;
        if (classLoaderName2 == null || ClassLoaderFactory.isValidClassLoaderName(classLoaderName2)) {
            r14.applicationInfo.splitClassLoaderNames[splitIndex] = classLoaderName2;
            int innerDepth2 = parser.getDepth();
            while (true) {
                int type = parser.next();
                if (type == i) {
                    return true;
                }
                if (type == 3 && parser.getDepth() <= innerDepth2) {
                    return true;
                }
                if (type == 3) {
                    innerDepth = innerDepth2;
                    classLoaderName = classLoaderName2;
                    xmlResourceParser = xmlResourceParser2;
                    r14 = r14;
                    packageParser = packageParser;
                    boolean z2 = z ? 1 : 0;
                    Object[] objArr = z ? 1 : 0;
                    Object[] objArr2 = z ? 1 : 0;
                    Object[] objArr3 = z ? 1 : 0;
                    Object[] objArr4 = z ? 1 : 0;
                    Object[] objArr5 = z ? 1 : 0;
                    Object[] objArr6 = z ? 1 : 0;
                    z = z2;
                    i3 = i3;
                    strArr = strArr;
                    resources = resources;
                } else if (type == i2) {
                    innerDepth = innerDepth2;
                    classLoaderName = classLoaderName2;
                    xmlResourceParser = xmlResourceParser2;
                    r14 = r14;
                    packageParser = packageParser;
                    z = z;
                    i3 = i3;
                    strArr = strArr;
                    resources = resources;
                } else {
                    CachedComponentArgs cachedArgs = new CachedComponentArgs();
                    String tagName = parser.getName();
                    if (tagName.equals("activity")) {
                        innerDepth = innerDepth2;
                        classLoaderName = classLoaderName2;
                        Activity a = parseActivity(owner, res, parser, flags, outError, cachedArgs, false, r14.baseHardwareAccelerated);
                        if (a == null) {
                            packageParser.mParseError = i3;
                            return false;
                        }
                        r14.activities.add(a);
                        ComponentInfo parsedComponent2 = a.info;
                        resources = res;
                        parsedComponent = parsedComponent2;
                        strArr = strArr;
                        xmlResourceParser = xmlResourceParser2;
                        packageParser = packageParser;
                        z = false;
                        r14 = r14;
                        i3 = i3;
                    } else {
                        innerDepth = innerDepth2;
                        classLoaderName = classLoaderName2;
                        if (tagName.equals(IncidentManager.URI_PARAM_RECEIVER_CLASS)) {
                            xmlResourceParser = xmlResourceParser2;
                            resources = res;
                            r14 = r14;
                            packageParser = packageParser;
                            Activity a2 = parseActivity(owner, res, parser, flags, outError, cachedArgs, true, false);
                            if (a2 == null) {
                                packageParser.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
                                return false;
                            }
                            i3 = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
                            z = false;
                            r14.receivers.add(a2);
                            parsedComponent = a2.info;
                            strArr = outError;
                        } else {
                            resources = res;
                            xmlResourceParser = xmlResourceParser2;
                            packageParser = packageParser;
                            z = z;
                            r14 = r14;
                            i3 = i3;
                            if (tagName.equals("service")) {
                                Service s = parseService(owner, res, parser, flags, outError, cachedArgs);
                                if (s == null) {
                                    packageParser.mParseError = i3;
                                    return z;
                                }
                                r14.services.add(s);
                                parsedComponent = s.info;
                                strArr = outError;
                            } else if (tagName.equals("provider")) {
                                Provider p = parseProvider(owner, res, parser, flags, outError, cachedArgs);
                                if (p == null) {
                                    packageParser.mParseError = i3;
                                    return z;
                                }
                                r14.providers.add(p);
                                parsedComponent = p.info;
                                strArr = outError;
                            } else if (tagName.equals("activity-alias")) {
                                Activity a3 = parseActivityAlias(owner, res, parser, flags, outError, cachedArgs);
                                if (a3 == null) {
                                    packageParser.mParseError = i3;
                                    return z;
                                }
                                r14.activities.add(a3);
                                parsedComponent = a3.info;
                                strArr = outError;
                            } else {
                                if (parser.getName().equals("meta-data")) {
                                    strArr = outError;
                                    Bundle parseMetaData = packageParser.parseMetaData(resources, xmlResourceParser, r14.mAppMetaData, strArr);
                                    r14.mAppMetaData = parseMetaData;
                                    if (parseMetaData == null) {
                                        packageParser.mParseError = i3;
                                        return z;
                                    }
                                } else {
                                    strArr = outError;
                                    if (tagName.equals("uses-static-library")) {
                                        if (!packageParser.parseUsesStaticLibrary(r14, resources, xmlResourceParser, strArr)) {
                                            return z;
                                        }
                                    } else if (tagName.equals("uses-library")) {
                                        TypedArray sa2 = resources.obtainAttributes(xmlResourceParser, R.styleable.AndroidManifestUsesLibrary);
                                        int i4 = z ? 1 : 0;
                                        int i5 = z ? 1 : 0;
                                        int i6 = z ? 1 : 0;
                                        int i7 = z ? 1 : 0;
                                        int i8 = z ? 1 : 0;
                                        String lname = sa2.getNonResourceString(i4);
                                        boolean req = sa2.getBoolean(1, true);
                                        sa2.recycle();
                                        if (lname != null) {
                                            String lname2 = lname.intern();
                                            if (req) {
                                                r14.usesLibraries = ArrayUtils.add(r14.usesLibraries, lname2);
                                                r14.usesOptionalLibraries = ArrayUtils.remove(r14.usesOptionalLibraries, lname2);
                                            } else if (!ArrayUtils.contains(r14.usesLibraries, lname2)) {
                                                r14.usesOptionalLibraries = ArrayUtils.add(r14.usesOptionalLibraries, lname2);
                                            }
                                        }
                                        XmlUtils.skipCurrentTag(parser);
                                        parsedComponent = null;
                                    } else if (tagName.equals("uses-package")) {
                                        XmlUtils.skipCurrentTag(parser);
                                    } else {
                                        Slog.w(TAG, "Unknown element under <application>: " + tagName + " at " + packageParser.mArchiveSourcePath + " " + parser.getPositionDescription());
                                        XmlUtils.skipCurrentTag(parser);
                                    }
                                }
                                parsedComponent = null;
                            }
                        }
                    }
                    if (parsedComponent != null && parsedComponent.splitName == null) {
                        parsedComponent.splitName = r14.splitNames[splitIndex];
                    }
                    xmlResourceParser2 = xmlResourceParser;
                    classLoaderName2 = classLoaderName;
                    innerDepth2 = innerDepth;
                    i2 = 4;
                    i = 1;
                    r14 = r14;
                }
                xmlResourceParser2 = xmlResourceParser;
                classLoaderName2 = classLoaderName;
                innerDepth2 = innerDepth;
                i2 = 4;
                i = 1;
            }
        } else {
            strArr[0] = "Invalid class loader name: " + classLoaderName2;
            packageParser.mParseError = PackageManager.INSTALL_PARSE_FAILED_MANIFEST_MALFORMED;
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean parsePackageItemInfo(Package owner, PackageItemInfo outInfo, String[] outError, String tag, TypedArray sa, boolean nameRequired, int nameRes, int labelRes, int iconRes, int roundIconRes, int logoRes, int bannerRes) {
        if (sa == null) {
            outError[0] = tag + " does not contain any attributes";
            return false;
        }
        String name = sa.getNonConfigurationString(nameRes, 0);
        if (name != null) {
            String outInfoName = buildClassName(owner.applicationInfo.packageName, name, outError);
            if (PackageManager.APP_DETAILS_ACTIVITY_CLASS_NAME.equals(outInfoName)) {
                outError[0] = tag + " invalid android:name";
                return false;
            }
            outInfo.name = outInfoName;
            if (outInfoName == null) {
                return false;
            }
        } else if (nameRequired) {
            outError[0] = tag + " does not specify android:name";
            return false;
        }
        int roundIconVal = sUseRoundIcon ? sa.getResourceId(roundIconRes, 0) : 0;
        if (roundIconVal != 0) {
            outInfo.icon = roundIconVal;
            outInfo.nonLocalizedLabel = null;
        } else {
            int iconVal = sa.getResourceId(iconRes, 0);
            if (iconVal != 0) {
                outInfo.icon = iconVal;
                outInfo.nonLocalizedLabel = null;
            }
        }
        int logoVal = sa.getResourceId(logoRes, 0);
        if (logoVal != 0) {
            outInfo.logo = logoVal;
        }
        int bannerVal = sa.getResourceId(bannerRes, 0);
        if (bannerVal != 0) {
            outInfo.banner = bannerVal;
        }
        TypedValue v = sa.peekValue(labelRes);
        if (v != null) {
            int i = v.resourceId;
            outInfo.labelRes = i;
            if (i == 0) {
                outInfo.nonLocalizedLabel = v.coerceToString();
            }
        }
        outInfo.packageName = owner.packageName;
        return true;
    }

    private Activity generateAppDetailsHiddenActivity(Package owner, int flags, String[] outError, boolean hardwareAccelerated) {
        Activity a = new Activity(owner, PackageManager.APP_DETAILS_ACTIVITY_CLASS_NAME, new ActivityInfo());
        a.owner = owner;
        a.setPackageName(owner.packageName);
        a.info.theme = 16973909;
        a.info.exported = true;
        a.info.name = PackageManager.APP_DETAILS_ACTIVITY_CLASS_NAME;
        a.info.processName = owner.applicationInfo.processName;
        a.info.uiOptions = a.info.applicationInfo.uiOptions;
        a.info.taskAffinity = buildTaskAffinityName(owner.packageName, owner.packageName, ":app_details", outError);
        a.info.enabled = true;
        a.info.launchMode = 0;
        a.info.documentLaunchMode = 0;
        a.info.maxRecents = ActivityTaskManager.getDefaultAppRecentsLimitStatic();
        a.info.configChanges = getActivityConfigChanges(0, 0);
        a.info.softInputMode = 0;
        a.info.persistableMode = 1;
        a.info.screenOrientation = -1;
        a.info.resizeMode = 4;
        a.info.lockTaskLaunchMode = 0;
        a.info.directBootAware = false;
        a.info.rotationAnimation = -1;
        a.info.colorMode = 0;
        if (hardwareAccelerated) {
            a.info.flags |= 512;
        }
        return a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:138:0x03ef A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:139:0x03f1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.content.pm.PackageParser.Activity parseActivity(android.content.pm.PackageParser.Package r29, android.content.res.Resources r30, android.content.res.XmlResourceParser r31, int r32, java.lang.String[] r33, android.content.pm.PackageParser.CachedComponentArgs r34, boolean r35, boolean r36) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 1721
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.PackageParser.parseActivity(android.content.pm.PackageParser$Package, android.content.res.Resources, android.content.res.XmlResourceParser, int, java.lang.String[], android.content.pm.PackageParser$CachedComponentArgs, boolean, boolean):android.content.pm.PackageParser$Activity");
    }

    private void setActivityResizeMode(ActivityInfo aInfo, TypedArray sa, Package owner) {
        boolean appResizeable = true;
        boolean appExplicitDefault = (owner.applicationInfo.privateFlags & 3072) != 0;
        if (sa.hasValue(40) || appExplicitDefault) {
            if ((owner.applicationInfo.privateFlags & 1024) == 0) {
                appResizeable = false;
            }
            if (sa.getBoolean(40, appResizeable)) {
                aInfo.resizeMode = 2;
            } else {
                aInfo.resizeMode = 0;
            }
        } else if ((owner.applicationInfo.privateFlags & 4096) != 0) {
            aInfo.resizeMode = 1;
        } else if (aInfo.isFixedOrientationPortrait()) {
            aInfo.resizeMode = 6;
        } else if (aInfo.isFixedOrientationLandscape()) {
            aInfo.resizeMode = 5;
        } else if (aInfo.isFixedOrientation()) {
            aInfo.resizeMode = 7;
        } else {
            aInfo.resizeMode = 4;
        }
    }

    private void setMaxAspectRatio(Package owner) {
        float activityAspectRatio;
        float maxAspectRatio = owner.applicationInfo.targetSdkVersion < 26 ? 1.86f : 0.0f;
        if (owner.applicationInfo.maxAspectRatio != 0.0f) {
            maxAspectRatio = owner.applicationInfo.maxAspectRatio;
        } else if (owner.mAppMetaData != null && owner.mAppMetaData.containsKey("android.max_aspect")) {
            maxAspectRatio = owner.mAppMetaData.getFloat("android.max_aspect", maxAspectRatio);
        }
        this.mPackageParserExt.hookSetMaxAspectRatio(owner, maxAspectRatio);
        Iterator<Activity> it = owner.activities.iterator();
        while (it.hasNext()) {
            Activity activity = it.next();
            if (!activity.hasMaxAspectRatio()) {
                if (activity.metaData != null) {
                    activityAspectRatio = activity.metaData.getFloat("android.max_aspect", maxAspectRatio);
                } else {
                    activityAspectRatio = maxAspectRatio;
                }
                activity.setMaxAspectRatio(activityAspectRatio);
            }
        }
    }

    private void setMinAspectRatio(Package owner) {
        float minAspectRatio = owner.applicationInfo.minAspectRatio;
        Iterator<Activity> it = owner.activities.iterator();
        while (it.hasNext()) {
            Activity activity = it.next();
            if (!activity.hasMinAspectRatio()) {
                activity.setMinAspectRatio(minAspectRatio);
            }
        }
    }

    private void setSupportsSizeChanges(Package owner) {
        boolean supportsSizeChanges = owner.mAppMetaData != null && owner.mAppMetaData.getBoolean("android.supports_size_changes", false);
        Iterator<Activity> it = owner.activities.iterator();
        while (it.hasNext()) {
            Activity activity = it.next();
            if (supportsSizeChanges || (activity.metaData != null && activity.metaData.getBoolean("android.supports_size_changes", false))) {
                activity.info.supportsSizeChanges = true;
            }
        }
    }

    public static int getActivityConfigChanges(int configChanges, int recreateOnConfigChanges) {
        return ((~recreateOnConfigChanges) & 3) | configChanges;
    }

    private void parseLayout(Resources res, AttributeSet attrs, Activity a) {
        TypedArray sw = res.obtainAttributes(attrs, R.styleable.AndroidManifestLayout);
        int width = -1;
        float widthFraction = -1.0f;
        int height = -1;
        float heightFraction = -1.0f;
        int widthType = sw.getType(3);
        if (widthType == 6) {
            widthFraction = sw.getFraction(3, 1, 1, -1.0f);
        } else if (widthType == 5) {
            width = sw.getDimensionPixelSize(3, -1);
        }
        int heightType = sw.getType(4);
        if (heightType == 6) {
            heightFraction = sw.getFraction(4, 1, 1, -1.0f);
        } else if (heightType == 5) {
            height = sw.getDimensionPixelSize(4, -1);
        }
        int gravity = sw.getInt(0, 17);
        int minWidth = sw.getDimensionPixelSize(1, -1);
        int minHeight = sw.getDimensionPixelSize(2, -1);
        sw.recycle();
        a.info.windowLayout = new ActivityInfo.WindowLayout(width, widthFraction, height, heightFraction, gravity, minWidth, minHeight);
    }

    private void resolveWindowLayout(Activity activity) {
        if (activity.metaData != null && activity.metaData.containsKey("android.activity_window_layout_affinity")) {
            ActivityInfo aInfo = activity.info;
            if (aInfo.windowLayout == null || aInfo.windowLayout.windowLayoutAffinity == null) {
                String windowLayoutAffinity = activity.metaData.getString("android.activity_window_layout_affinity");
                if (aInfo.windowLayout == null) {
                    aInfo.windowLayout = new ActivityInfo.WindowLayout(-1, -1.0f, -1, -1.0f, 0, -1, -1);
                }
                aInfo.windowLayout.windowLayoutAffinity = windowLayoutAffinity;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x03bb, code lost:
        r1 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x03bd, code lost:
        r0.exported = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x03bf, code lost:
        return r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x03ac, code lost:
        if (r15 != false) goto L_0x03bf;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x03ae, code lost:
        r0 = r13.info;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x03b6, code lost:
        if (r13.intents.size() <= 0) goto L_0x03bb;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.content.pm.PackageParser.Activity parseActivityAlias(android.content.pm.PackageParser.Package r31, android.content.res.Resources r32, android.content.res.XmlResourceParser r33, int r34, java.lang.String[] r35, android.content.pm.PackageParser.CachedComponentArgs r36) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 960
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.PackageParser.parseActivityAlias(android.content.pm.PackageParser$Package, android.content.res.Resources, android.content.res.XmlResourceParser, int, java.lang.String[], android.content.pm.PackageParser$CachedComponentArgs):android.content.pm.PackageParser$Activity");
    }

    private Provider parseProvider(Package owner, Resources res, XmlResourceParser parser, int flags, String[] outError, CachedComponentArgs cachedArgs) throws XmlPullParserException, IOException {
        TypedArray sa;
        boolean providerExportedDefault;
        String str;
        TypedArray sa2 = res.obtainAttributes(parser, R.styleable.AndroidManifestProvider);
        if (cachedArgs.mProviderArgs == null) {
            sa = sa2;
            cachedArgs.mProviderArgs = new ParseComponentArgs(owner, outError, 2, 0, 1, 19, 15, 17, this.mSeparateProcesses, 8, 14, 6);
            cachedArgs.mProviderArgs.tag = "<provider>";
        } else {
            sa = sa2;
        }
        cachedArgs.mProviderArgs.sa = sa;
        cachedArgs.mProviderArgs.flags = flags;
        Provider p = new Provider(cachedArgs.mProviderArgs, new ProviderInfo());
        if (outError[0] != null) {
            sa.recycle();
            return null;
        }
        if (owner.applicationInfo.targetSdkVersion < 17) {
            providerExportedDefault = true;
        } else {
            providerExportedDefault = false;
        }
        p.info.exported = sa.getBoolean(7, providerExportedDefault);
        String cpname = sa.getNonConfigurationString(10, 0);
        p.info.isSyncable = sa.getBoolean(11, false);
        String permission = sa.getNonConfigurationString(3, 0);
        String str2 = sa.getNonConfigurationString(4, 0);
        if (str2 == null) {
            str2 = permission;
        }
        if (str2 == null) {
            p.info.readPermission = owner.applicationInfo.permission;
        } else {
            p.info.readPermission = str2.length() > 0 ? str2.toString().intern() : null;
        }
        String str3 = sa.getNonConfigurationString(5, 0);
        if (str3 == null) {
            str = permission;
        } else {
            str = str3;
        }
        if (str == null) {
            p.info.writePermission = owner.applicationInfo.permission;
        } else {
            p.info.writePermission = str.length() > 0 ? str.toString().intern() : null;
        }
        p.info.grantUriPermissions = sa.getBoolean(13, false);
        p.info.forceUriPermissions = sa.getBoolean(22, false);
        p.info.multiprocess = sa.getBoolean(9, false);
        p.info.initOrder = sa.getInt(12, 0);
        p.info.splitName = sa.getNonConfigurationString(21, 0);
        p.info.flags = 0;
        if (sa.getBoolean(16, false)) {
            p.info.flags |= 1073741824;
        }
        p.info.directBootAware = sa.getBoolean(18, false);
        if (p.info.directBootAware) {
            owner.applicationInfo.privateFlags |= 256;
        }
        boolean visibleToEphemeral = sa.getBoolean(20, false);
        if (visibleToEphemeral) {
            p.info.flags |= 1048576;
            owner.visibleToInstantApps = true;
        }
        sa.recycle();
        if ((owner.applicationInfo.privateFlags & 2) != 0 && p.info.processName == owner.packageName) {
            outError[0] = "Heavy-weight applications can not have providers in main process";
            return null;
        } else if (cpname == null) {
            outError[0] = "<provider> does not include authorities attribute";
            return null;
        } else if (cpname.length() <= 0) {
            outError[0] = "<provider> has empty authorities attribute";
            return null;
        } else {
            p.info.authority = cpname.intern();
            if (!parseProviderTags(res, parser, visibleToEphemeral, p, outError)) {
                return null;
            }
            return p;
        }
    }

    private boolean parseProviderTags(Resources res, XmlResourceParser parser, boolean visibleToEphemeral, Provider outInfo, String[] outError) throws XmlPullParserException, IOException {
        String readPermission;
        String readPermission2;
        String writePermission;
        PathPermission pa;
        PathPermission pa2;
        PathPermission pa3;
        int outerDepth = parser.getDepth();
        while (true) {
            int type = parser.next();
            if (type == 1) {
                return true;
            }
            if (type == 3 && parser.getDepth() <= outerDepth) {
                return true;
            }
            if (!(type == 3 || type == 4)) {
                if (parser.getName().equals("intent-filter")) {
                    ProviderIntentInfo intent = new ProviderIntentInfo(outInfo);
                    if (!parseIntent(res, parser, true, false, intent, outError)) {
                        return false;
                    }
                    if (visibleToEphemeral) {
                        intent.setVisibilityToInstantApp(1);
                        outInfo.info.flags |= 1048576;
                    }
                    outInfo.order = Math.max(intent.getOrder(), outInfo.order);
                    outInfo.intents.add(intent);
                } else if (parser.getName().equals("meta-data")) {
                    Bundle parseMetaData = parseMetaData(res, parser, outInfo.metaData, outError);
                    outInfo.metaData = parseMetaData;
                    if (parseMetaData == null) {
                        return false;
                    }
                } else if (parser.getName().equals("grant-uri-permission")) {
                    TypedArray sa = res.obtainAttributes(parser, R.styleable.AndroidManifestGrantUriPermission);
                    PatternMatcher pa4 = null;
                    String str = sa.getNonConfigurationString(0, 0);
                    if (str != null) {
                        pa4 = new PatternMatcher(str, 0);
                    }
                    String str2 = sa.getNonConfigurationString(1, 0);
                    if (str2 != null) {
                        pa4 = new PatternMatcher(str2, 1);
                    }
                    String str3 = sa.getNonConfigurationString(2, 0);
                    if (str3 != null) {
                        pa4 = new PatternMatcher(str3, 2);
                    }
                    sa.recycle();
                    if (pa4 != null) {
                        if (outInfo.info.uriPermissionPatterns == null) {
                            outInfo.info.uriPermissionPatterns = new PatternMatcher[1];
                            outInfo.info.uriPermissionPatterns[0] = pa4;
                        } else {
                            int N = outInfo.info.uriPermissionPatterns.length;
                            PatternMatcher[] newp = new PatternMatcher[N + 1];
                            System.arraycopy(outInfo.info.uriPermissionPatterns, 0, newp, 0, N);
                            newp[N] = pa4;
                            outInfo.info.uriPermissionPatterns = newp;
                        }
                        outInfo.info.grantUriPermissions = true;
                        XmlUtils.skipCurrentTag(parser);
                    } else {
                        Slog.w(TAG, "Unknown element under <path-permission>: " + parser.getName() + " at " + this.mArchiveSourcePath + " " + parser.getPositionDescription());
                        XmlUtils.skipCurrentTag(parser);
                    }
                } else if (parser.getName().equals("path-permission")) {
                    TypedArray sa2 = res.obtainAttributes(parser, R.styleable.AndroidManifestPathPermission);
                    String permission = sa2.getNonConfigurationString(0, 0);
                    String readPermission3 = sa2.getNonConfigurationString(1, 0);
                    if (readPermission3 == null) {
                        readPermission = permission;
                    } else {
                        readPermission = readPermission3;
                    }
                    String writePermission2 = sa2.getNonConfigurationString(2, 0);
                    if (writePermission2 == null) {
                        writePermission2 = permission;
                    }
                    boolean havePerm = false;
                    if (readPermission != null) {
                        havePerm = true;
                        readPermission2 = readPermission.intern();
                    } else {
                        readPermission2 = readPermission;
                    }
                    if (writePermission2 != null) {
                        havePerm = true;
                        writePermission = writePermission2.intern();
                    } else {
                        writePermission = writePermission2;
                    }
                    if (!havePerm) {
                        Slog.w(TAG, "No readPermission or writePermssion for <path-permission>: " + parser.getName() + " at " + this.mArchiveSourcePath + " " + parser.getPositionDescription());
                        XmlUtils.skipCurrentTag(parser);
                    } else {
                        String path = sa2.getNonConfigurationString(3, 0);
                        if (path != null) {
                            pa = new PathPermission(path, 0, readPermission2, writePermission);
                        } else {
                            pa = null;
                        }
                        String path2 = sa2.getNonConfigurationString(4, 0);
                        if (path2 != null) {
                            pa2 = new PathPermission(path2, 1, readPermission2, writePermission);
                        } else {
                            pa2 = pa;
                        }
                        String path3 = sa2.getNonConfigurationString(5, 0);
                        if (path3 != null) {
                            pa2 = new PathPermission(path3, 2, readPermission2, writePermission);
                        }
                        String path4 = sa2.getNonConfigurationString(7, 0);
                        if (path4 != null) {
                            pa3 = new PathPermission(path4, 3, readPermission2, writePermission);
                        } else {
                            pa3 = pa2;
                        }
                        sa2.recycle();
                        if (pa3 != null) {
                            if (outInfo.info.pathPermissions == null) {
                                outInfo.info.pathPermissions = new PathPermission[1];
                                outInfo.info.pathPermissions[0] = pa3;
                            } else {
                                int N2 = outInfo.info.pathPermissions.length;
                                PathPermission[] newp2 = new PathPermission[N2 + 1];
                                System.arraycopy(outInfo.info.pathPermissions, 0, newp2, 0, N2);
                                newp2[N2] = pa3;
                                outInfo.info.pathPermissions = newp2;
                            }
                            XmlUtils.skipCurrentTag(parser);
                        } else {
                            Slog.w(TAG, "No path, pathPrefix, or pathPattern for <path-permission>: " + parser.getName() + " at " + this.mArchiveSourcePath + " " + parser.getPositionDescription());
                            XmlUtils.skipCurrentTag(parser);
                        }
                    }
                } else {
                    Slog.w(TAG, "Unknown element under <provider>: " + parser.getName() + " at " + this.mArchiveSourcePath + " " + parser.getPositionDescription());
                    XmlUtils.skipCurrentTag(parser);
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:77:0x025a, code lost:
        if (r12 != false) goto L_0x026b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x025c, code lost:
        r1 = r0.info;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0264, code lost:
        if (r0.intents.size() <= 0) goto L_0x0268;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0268, code lost:
        r10 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0269, code lost:
        r1.exported = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x026b, code lost:
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.content.pm.PackageParser.Service parseService(android.content.pm.PackageParser.Package r25, android.content.res.Resources r26, android.content.res.XmlResourceParser r27, int r28, java.lang.String[] r29, android.content.pm.PackageParser.CachedComponentArgs r30) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 620
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.PackageParser.parseService(android.content.pm.PackageParser$Package, android.content.res.Resources, android.content.res.XmlResourceParser, int, java.lang.String[], android.content.pm.PackageParser$CachedComponentArgs):android.content.pm.PackageParser$Service");
    }

    private boolean isImplicitlyExposedIntent(IntentInfo intent) {
        return intent.hasCategory(Intent.CATEGORY_BROWSABLE) || intent.hasAction(Intent.ACTION_SEND) || intent.hasAction(Intent.ACTION_SENDTO) || intent.hasAction(Intent.ACTION_SEND_MULTIPLE);
    }

    private boolean parseAllMetaData(Resources res, XmlResourceParser parser, String tag, Component<?> outInfo, String[] outError) throws XmlPullParserException, IOException {
        int outerDepth = parser.getDepth();
        while (true) {
            int type = parser.next();
            if (type == 1 || (type == 3 && parser.getDepth() <= outerDepth)) {
                break;
            } else if (!(type == 3 || type == 4)) {
                if (parser.getName().equals("meta-data")) {
                    Bundle parseMetaData = parseMetaData(res, parser, outInfo.metaData, outError);
                    outInfo.metaData = parseMetaData;
                    if (parseMetaData == null) {
                        return false;
                    }
                } else {
                    Slog.w(TAG, "Unknown element under " + tag + ": " + parser.getName() + " at " + this.mArchiveSourcePath + " " + parser.getPositionDescription());
                    XmlUtils.skipCurrentTag(parser);
                }
            }
        }
        return true;
    }

    private Bundle parseMetaData(Resources res, XmlResourceParser parser, Bundle data, String[] outError) throws XmlPullParserException, IOException {
        TypedArray sa = res.obtainAttributes(parser, R.styleable.AndroidManifestMetaData);
        if (data == null) {
            data = new Bundle();
        }
        boolean z = false;
        String name = sa.getNonConfigurationString(0, 0);
        String str = null;
        if (name == null) {
            outError[0] = "<meta-data> requires an android:name attribute";
            sa.recycle();
            return null;
        }
        String name2 = name.intern();
        TypedValue v = sa.peekValue(2);
        if (v == null || v.resourceId == 0) {
            TypedValue v2 = sa.peekValue(1);
            if (v2 == null) {
                outError[0] = "<meta-data> requires an android:value or android:resource attribute";
                data = null;
            } else if (v2.type == 3) {
                CharSequence cs = v2.coerceToString();
                if (cs != null) {
                    str = cs.toString();
                }
                data.putString(name2, str);
            } else if (v2.type == 18) {
                if (v2.data != 0) {
                    z = true;
                }
                data.putBoolean(name2, z);
            } else if (v2.type >= 16 && v2.type <= 31) {
                data.putInt(name2, v2.data);
            } else if (v2.type == 4) {
                data.putFloat(name2, v2.getFloat());
            } else {
                Slog.w(TAG, "<meta-data> only supports string, integer, float, color, boolean, and resource reference types: " + parser.getName() + " at " + this.mArchiveSourcePath + " " + parser.getPositionDescription());
            }
        } else {
            data.putInt(name2, v.resourceId);
        }
        sa.recycle();
        XmlUtils.skipCurrentTag(parser);
        return data;
    }

    private static VerifierInfo parseVerifier(AttributeSet attrs) {
        String packageName = null;
        String encodedPublicKey = null;
        int attrCount = attrs.getAttributeCount();
        for (int i = 0; i < attrCount; i++) {
            int attrResId = attrs.getAttributeNameResource(i);
            switch (attrResId) {
                case 16842755:
                    packageName = attrs.getAttributeValue(i);
                    break;
                case 16843686:
                    encodedPublicKey = attrs.getAttributeValue(i);
                    break;
            }
        }
        if (packageName == null || packageName.length() == 0) {
            Slog.i(TAG, "verifier package name was null; skipping");
            return null;
        }
        PublicKey publicKey = parsePublicKey(encodedPublicKey);
        if (publicKey != null) {
            return new VerifierInfo(packageName, publicKey);
        }
        Slog.i(TAG, "Unable to parse verifier public key for " + packageName);
        return null;
    }

    public static final PublicKey parsePublicKey(String encodedPublicKey) {
        if (encodedPublicKey == null) {
            Slog.w(TAG, "Could not parse null public key");
            return null;
        }
        try {
            return parsePublicKey(Base64.decode(encodedPublicKey, 0));
        } catch (IllegalArgumentException e) {
            Slog.w(TAG, "Could not parse verifier public key; invalid Base64");
            return null;
        }
    }

    public static final PublicKey parsePublicKey(byte[] publicKey) {
        if (publicKey == null) {
            Slog.w(TAG, "Could not parse null public key");
            return null;
        }
        try {
            EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
            try {
                KeyFactory keyFactory = KeyFactory.getInstance(KeyProperties.KEY_ALGORITHM_RSA);
                return keyFactory.generatePublic(keySpec);
            } catch (NoSuchAlgorithmException e) {
                Slog.wtf(TAG, "Could not parse public key: RSA KeyFactory not included in build");
                try {
                    KeyFactory keyFactory2 = KeyFactory.getInstance(KeyProperties.KEY_ALGORITHM_EC);
                    return keyFactory2.generatePublic(keySpec);
                } catch (NoSuchAlgorithmException e2) {
                    Slog.wtf(TAG, "Could not parse public key: EC KeyFactory not included in build");
                    try {
                        KeyFactory keyFactory3 = KeyFactory.getInstance("DSA");
                        return keyFactory3.generatePublic(keySpec);
                    } catch (NoSuchAlgorithmException e3) {
                        Slog.wtf(TAG, "Could not parse public key: DSA KeyFactory not included in build");
                        return null;
                    } catch (InvalidKeySpecException e4) {
                        return null;
                    }
                } catch (InvalidKeySpecException e5) {
                    KeyFactory keyFactory32 = KeyFactory.getInstance("DSA");
                    return keyFactory32.generatePublic(keySpec);
                }
            } catch (InvalidKeySpecException e6) {
                KeyFactory keyFactory22 = KeyFactory.getInstance(KeyProperties.KEY_ALGORITHM_EC);
                return keyFactory22.generatePublic(keySpec);
            }
        } catch (IllegalArgumentException e7) {
            Slog.w(TAG, "Could not parse verifier public key; invalid Base64");
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ae, code lost:
        r24[0] = "No value supplied for <android:name>";
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00b0, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00cd, code lost:
        r24[0] = "No value supplied for <android:name>";
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00cf, code lost:
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean parseIntent(android.content.res.Resources r19, android.content.res.XmlResourceParser r20, boolean r21, boolean r22, android.content.pm.PackageParser.IntentInfo r23, java.lang.String[] r24) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 474
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.PackageParser.parseIntent(android.content.res.Resources, android.content.res.XmlResourceParser, boolean, boolean, android.content.pm.PackageParser$IntentInfo, java.lang.String[]):boolean");
    }

    /* loaded from: classes.dex */
    public static final class SigningDetails implements Parcelable {
        private static final int PAST_CERT_EXISTS = 0;
        public final Signature[] pastSigningCertificates;
        public final ArraySet<PublicKey> publicKeys;
        public final int signatureSchemeVersion;
        public final Signature[] signatures;
        public static final SigningDetails UNKNOWN = new SigningDetails(null, 0, null, null);
        public static final Parcelable.Creator<SigningDetails> CREATOR = new Parcelable.Creator<SigningDetails>() { // from class: android.content.pm.PackageParser.SigningDetails.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SigningDetails createFromParcel(Parcel source) {
                if (source.readBoolean()) {
                    return SigningDetails.UNKNOWN;
                }
                return new SigningDetails(source);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SigningDetails[] newArray(int size) {
                return new SigningDetails[size];
            }
        };

        /* loaded from: classes.dex */
        public @interface CertCapabilities {
            public static final int AUTH = 16;
            public static final int INSTALLED_DATA = 1;
            public static final int PERMISSION = 4;
            public static final int ROLLBACK = 8;
            public static final int SHARED_USER_ID = 2;
        }

        /* loaded from: classes.dex */
        public @interface SignatureSchemeVersion {
            public static final int JAR = 1;
            public static final int SIGNING_BLOCK_V2 = 2;
            public static final int SIGNING_BLOCK_V3 = 3;
            public static final int SIGNING_BLOCK_V4 = 4;
            public static final int UNKNOWN = 0;
        }

        public SigningDetails(Signature[] signatures, int signatureSchemeVersion, ArraySet<PublicKey> keys, Signature[] pastSigningCertificates) {
            this.signatures = signatures;
            this.signatureSchemeVersion = signatureSchemeVersion;
            this.publicKeys = keys;
            this.pastSigningCertificates = pastSigningCertificates;
        }

        public SigningDetails(Signature[] signatures, int signatureSchemeVersion, Signature[] pastSigningCertificates) throws CertificateException {
            this(signatures, signatureSchemeVersion, PackageParser.toSigningKeys(signatures), pastSigningCertificates);
        }

        public SigningDetails(Signature[] signatures, int signatureSchemeVersion) throws CertificateException {
            this(signatures, signatureSchemeVersion, null);
        }

        public SigningDetails(SigningDetails orig) {
            if (orig != null) {
                Signature[] signatureArr = orig.signatures;
                if (signatureArr != null) {
                    this.signatures = (Signature[]) signatureArr.clone();
                } else {
                    this.signatures = null;
                }
                this.signatureSchemeVersion = orig.signatureSchemeVersion;
                this.publicKeys = new ArraySet<>(orig.publicKeys);
                Signature[] signatureArr2 = orig.pastSigningCertificates;
                if (signatureArr2 != null) {
                    this.pastSigningCertificates = (Signature[]) signatureArr2.clone();
                } else {
                    this.pastSigningCertificates = null;
                }
            } else {
                this.signatures = null;
                this.signatureSchemeVersion = 0;
                this.publicKeys = null;
                this.pastSigningCertificates = null;
            }
        }

        public SigningDetails mergeLineageWith(SigningDetails otherSigningDetails) {
            if (!hasPastSigningCertificates()) {
                return (!otherSigningDetails.hasPastSigningCertificates() || !otherSigningDetails.hasAncestorOrSelf(this)) ? this : otherSigningDetails;
            }
            if (!otherSigningDetails.hasPastSigningCertificates()) {
                return this;
            }
            SigningDetails descendantSigningDetails = getDescendantOrSelf(otherSigningDetails);
            if (descendantSigningDetails == null) {
                return this;
            }
            return descendantSigningDetails == this ? mergeLineageWithAncestorOrSelf(otherSigningDetails) : otherSigningDetails.mergeLineageWithAncestorOrSelf(this);
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x0077, code lost:
            if (r7 < 0) goto L_0x007a;
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x0079, code lost:
            return r10;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private android.content.pm.PackageParser.SigningDetails mergeLineageWithAncestorOrSelf(android.content.pm.PackageParser.SigningDetails r11) {
            /*
                Method dump skipped, instructions count: 213
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: android.content.pm.PackageParser.SigningDetails.mergeLineageWithAncestorOrSelf(android.content.pm.PackageParser$SigningDetails):android.content.pm.PackageParser$SigningDetails");
        }

        public boolean hasCommonAncestor(SigningDetails otherSigningDetails) {
            if (!hasPastSigningCertificates()) {
                return otherSigningDetails.hasAncestorOrSelf(this);
            }
            if (!otherSigningDetails.hasPastSigningCertificates()) {
                return hasAncestorOrSelf(otherSigningDetails);
            }
            return getDescendantOrSelf(otherSigningDetails) != null;
        }

        public boolean hasAncestorOrSelfWithDigest(Set<String> certDigests) {
            if (!(this == UNKNOWN || certDigests == null || certDigests.size() == 0)) {
                Signature[] signatureArr = this.signatures;
                if (signatureArr.length != 0) {
                    if (signatureArr.length > 1) {
                        int size = certDigests.size();
                        Signature[] signatureArr2 = this.signatures;
                        if (size < signatureArr2.length) {
                            return false;
                        }
                        for (Signature signature : signatureArr2) {
                            String signatureDigest = PackageUtils.computeSha256Digest(signature.toByteArray());
                            if (!certDigests.contains(signatureDigest)) {
                                return false;
                            }
                        }
                        return true;
                    }
                    String signatureDigest2 = PackageUtils.computeSha256Digest(signatureArr[0].toByteArray());
                    if (certDigests.contains(signatureDigest2)) {
                        return true;
                    }
                    if (hasPastSigningCertificates()) {
                        int i = 0;
                        while (true) {
                            Signature[] signatureArr3 = this.pastSigningCertificates;
                            if (i >= signatureArr3.length - 1) {
                                break;
                            }
                            String signatureDigest3 = PackageUtils.computeSha256Digest(signatureArr3[i].toByteArray());
                            if (certDigests.contains(signatureDigest3)) {
                                return true;
                            }
                            i++;
                        }
                    }
                    return false;
                }
            }
            return false;
        }

        private SigningDetails getDescendantOrSelf(SigningDetails otherSigningDetails) {
            SigningDetails ancestorSigningDetails;
            SigningDetails descendantSigningDetails;
            if (hasAncestorOrSelf(otherSigningDetails)) {
                descendantSigningDetails = this;
                ancestorSigningDetails = otherSigningDetails;
            } else if (!otherSigningDetails.hasAncestor(this)) {
                return null;
            } else {
                descendantSigningDetails = otherSigningDetails;
                ancestorSigningDetails = this;
            }
            int descendantIndex = descendantSigningDetails.pastSigningCertificates.length - 1;
            int ancestorIndex = ancestorSigningDetails.pastSigningCertificates.length - 1;
            while (descendantIndex >= 0 && !descendantSigningDetails.pastSigningCertificates[descendantIndex].equals(ancestorSigningDetails.pastSigningCertificates[ancestorIndex])) {
                descendantIndex--;
            }
            if (descendantIndex < 0) {
                return null;
            }
            do {
                descendantIndex--;
                ancestorIndex--;
                if (descendantIndex < 0 || ancestorIndex < 0) {
                    break;
                }
            } while (descendantSigningDetails.pastSigningCertificates[descendantIndex].equals(ancestorSigningDetails.pastSigningCertificates[ancestorIndex]));
            if (descendantIndex < 0 || ancestorIndex < 0) {
                return descendantSigningDetails;
            }
            return null;
        }

        public boolean hasSignatures() {
            Signature[] signatureArr = this.signatures;
            return signatureArr != null && signatureArr.length > 0;
        }

        public boolean hasPastSigningCertificates() {
            Signature[] signatureArr = this.pastSigningCertificates;
            return signatureArr != null && signatureArr.length > 0;
        }

        public boolean hasAncestorOrSelf(SigningDetails oldDetails) {
            SigningDetails signingDetails = UNKNOWN;
            if (!(this == signingDetails || oldDetails == signingDetails)) {
                Signature[] signatureArr = oldDetails.signatures;
                if (signatureArr.length != 0) {
                    if (signatureArr.length > 1) {
                        return signaturesMatchExactly(oldDetails);
                    }
                    return hasCertificate(signatureArr[0]);
                }
            }
            return false;
        }

        public boolean hasAncestor(SigningDetails oldDetails) {
            SigningDetails signingDetails = UNKNOWN;
            if (this != signingDetails && oldDetails != signingDetails && oldDetails.signatures.length != 0 && hasPastSigningCertificates() && oldDetails.signatures.length == 1) {
                int i = 0;
                while (true) {
                    Signature[] signatureArr = this.pastSigningCertificates;
                    if (i >= signatureArr.length - 1) {
                        break;
                    } else if (signatureArr[i].equals(oldDetails.signatures[0])) {
                        return true;
                    } else {
                        i++;
                    }
                }
            }
            return false;
        }

        public boolean hasCommonSignerWithCapability(SigningDetails otherDetails, int flags) {
            SigningDetails signingDetails = UNKNOWN;
            if (!(this == signingDetails || otherDetails == signingDetails)) {
                Signature[] signatureArr = this.signatures;
                if (signatureArr.length != 0) {
                    Signature[] signatureArr2 = otherDetails.signatures;
                    if (signatureArr2.length != 0) {
                        if (signatureArr.length > 1 || signatureArr2.length > 1) {
                            return signaturesMatchExactly(otherDetails);
                        }
                        Set<Signature> otherSignatures = new ArraySet<>();
                        if (otherDetails.hasPastSigningCertificates()) {
                            otherSignatures.addAll(Arrays.asList(otherDetails.pastSigningCertificates));
                        } else {
                            otherSignatures.addAll(Arrays.asList(otherDetails.signatures));
                        }
                        if (otherSignatures.contains(this.signatures[0])) {
                            return true;
                        }
                        if (hasPastSigningCertificates()) {
                            int i = 0;
                            while (true) {
                                Signature[] signatureArr3 = this.pastSigningCertificates;
                                if (i >= signatureArr3.length - 1) {
                                    break;
                                } else if (otherSignatures.contains(signatureArr3[i]) && (this.pastSigningCertificates[i].getFlags() & flags) == flags) {
                                    return true;
                                } else {
                                    i++;
                                }
                            }
                        }
                        return false;
                    }
                }
            }
            return false;
        }

        public boolean checkCapability(SigningDetails oldDetails, int flags) {
            SigningDetails signingDetails = UNKNOWN;
            if (!(this == signingDetails || oldDetails == signingDetails)) {
                Signature[] signatureArr = oldDetails.signatures;
                if (signatureArr.length != 0) {
                    if (signatureArr.length > 1) {
                        return signaturesMatchExactly(oldDetails);
                    }
                    return hasCertificate(signatureArr[0], flags);
                }
            }
            return false;
        }

        public boolean checkCapabilityRecover(SigningDetails oldDetails, int flags) throws CertificateException {
            SigningDetails signingDetails = UNKNOWN;
            if (oldDetails == signingDetails || this == signingDetails) {
                return false;
            }
            if (!hasPastSigningCertificates() || oldDetails.signatures.length != 1) {
                return Signature.areEffectiveMatch(oldDetails.signatures, this.signatures);
            }
            int i = 0;
            while (true) {
                Signature[] signatureArr = this.pastSigningCertificates;
                if (i >= signatureArr.length) {
                    return false;
                }
                if (Signature.areEffectiveMatch(oldDetails.signatures[0], signatureArr[i]) && this.pastSigningCertificates[i].getFlags() == flags) {
                    return true;
                }
                i++;
            }
        }

        public boolean hasCertificate(Signature signature) {
            return hasCertificateInternal(signature, 0);
        }

        public boolean hasCertificate(Signature signature, int flags) {
            return hasCertificateInternal(signature, flags);
        }

        public boolean hasCertificate(byte[] certificate) {
            Signature signature = new Signature(certificate);
            return hasCertificate(signature);
        }

        private boolean hasCertificateInternal(Signature signature, int flags) {
            if (this == UNKNOWN) {
                return false;
            }
            if (hasPastSigningCertificates()) {
                int i = 0;
                while (true) {
                    Signature[] signatureArr = this.pastSigningCertificates;
                    if (i >= signatureArr.length - 1) {
                        break;
                    } else if (!signatureArr[i].equals(signature) || !(flags == 0 || (this.pastSigningCertificates[i].getFlags() & flags) == flags)) {
                        i++;
                    }
                }
                return true;
            }
            Signature[] signatureArr2 = this.signatures;
            return signatureArr2.length == 1 && signatureArr2[0].equals(signature);
        }

        public boolean checkCapability(String sha256String, int flags) {
            if (this == UNKNOWN) {
                return false;
            }
            byte[] sha256Bytes = sha256String == null ? null : HexEncoding.decode(sha256String, false);
            if (hasSha256Certificate(sha256Bytes, flags)) {
                return true;
            }
            String[] mSignaturesSha256Digests = PackageUtils.computeSignaturesSha256Digests(this.signatures);
            String mSignaturesSha256Digest = PackageUtils.computeSignaturesSha256Digest(mSignaturesSha256Digests);
            return mSignaturesSha256Digest.equals(sha256String);
        }

        public boolean hasSha256Certificate(byte[] sha256Certificate) {
            return hasSha256CertificateInternal(sha256Certificate, 0);
        }

        public boolean hasSha256Certificate(byte[] sha256Certificate, int flags) {
            return hasSha256CertificateInternal(sha256Certificate, flags);
        }

        private boolean hasSha256CertificateInternal(byte[] sha256Certificate, int flags) {
            if (this == UNKNOWN) {
                return false;
            }
            if (hasPastSigningCertificates()) {
                int i = 0;
                while (true) {
                    Signature[] signatureArr = this.pastSigningCertificates;
                    if (i >= signatureArr.length - 1) {
                        break;
                    }
                    byte[] digest = PackageUtils.computeSha256DigestBytes(signatureArr[i].toByteArray());
                    if (!Arrays.equals(sha256Certificate, digest) || !(flags == 0 || (this.pastSigningCertificates[i].getFlags() & flags) == flags)) {
                        i++;
                    }
                }
                return true;
            }
            Signature[] signatureArr2 = this.signatures;
            if (signatureArr2.length != 1) {
                return false;
            }
            byte[] digest2 = PackageUtils.computeSha256DigestBytes(signatureArr2[0].toByteArray());
            return Arrays.equals(sha256Certificate, digest2);
        }

        public boolean signaturesMatchExactly(SigningDetails other) {
            return Signature.areExactMatch(this.signatures, other.signatures);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            boolean isUnknown = UNKNOWN == this;
            dest.writeBoolean(isUnknown);
            if (!isUnknown) {
                dest.writeTypedArray(this.signatures, flags);
                dest.writeInt(this.signatureSchemeVersion);
                dest.writeArraySet(this.publicKeys);
                dest.writeTypedArray(this.pastSigningCertificates, flags);
            }
        }

        protected SigningDetails(Parcel in) {
            ClassLoader boot = Object.class.getClassLoader();
            this.signatures = (Signature[]) in.createTypedArray(Signature.CREATOR);
            this.signatureSchemeVersion = in.readInt();
            this.publicKeys = in.readArraySet(boot);
            this.pastSigningCertificates = (Signature[]) in.createTypedArray(Signature.CREATOR);
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof SigningDetails)) {
                return false;
            }
            SigningDetails that = (SigningDetails) o;
            if (this.signatureSchemeVersion != that.signatureSchemeVersion || !Signature.areExactMatch(this.signatures, that.signatures)) {
                return false;
            }
            ArraySet<PublicKey> arraySet = this.publicKeys;
            if (arraySet != null) {
                if (!arraySet.equals(that.publicKeys)) {
                    return false;
                }
            } else if (that.publicKeys != null) {
                return false;
            }
            if (!Arrays.equals(this.pastSigningCertificates, that.pastSigningCertificates)) {
                return false;
            }
            int i = 0;
            while (true) {
                Signature[] signatureArr = this.pastSigningCertificates;
                if (i >= signatureArr.length) {
                    return true;
                }
                if (signatureArr[i].getFlags() != that.pastSigningCertificates[i].getFlags()) {
                    return false;
                }
                i++;
            }
        }

        public int hashCode() {
            int result = Arrays.hashCode(this.signatures);
            int result2 = ((result * 31) + this.signatureSchemeVersion) * 31;
            ArraySet<PublicKey> arraySet = this.publicKeys;
            return ((result2 + (arraySet != null ? arraySet.hashCode() : 0)) * 31) + Arrays.hashCode(this.pastSigningCertificates);
        }

        /* loaded from: classes.dex */
        public static class Builder {
            private Signature[] mPastSigningCertificates;
            private int mSignatureSchemeVersion = 0;
            private Signature[] mSignatures;

            public Builder setSignatures(Signature[] signatures) {
                this.mSignatures = signatures;
                return this;
            }

            public Builder setSignatureSchemeVersion(int signatureSchemeVersion) {
                this.mSignatureSchemeVersion = signatureSchemeVersion;
                return this;
            }

            public Builder setPastSigningCertificates(Signature[] pastSigningCertificates) {
                this.mPastSigningCertificates = pastSigningCertificates;
                return this;
            }

            private void checkInvariants() {
                if (this.mSignatures == null) {
                    throw new IllegalStateException("SigningDetails requires the current signing certificates.");
                }
            }

            public SigningDetails build() throws CertificateException {
                checkInvariants();
                return new SigningDetails(this.mSignatures, this.mSignatureSchemeVersion, this.mPastSigningCertificates);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class Package implements Parcelable {
        public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Package>() { // from class: android.content.pm.PackageParser.Package.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Package createFromParcel(Parcel in) {
                return new Package(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Package[] newArray(int size) {
                return new Package[size];
            }
        };
        public final ArrayList<Activity> activities;
        public ApplicationInfo applicationInfo;
        public String baseCodePath;
        public boolean baseHardwareAccelerated;
        public int baseRevisionCode;
        public ArrayList<Package> childPackages;
        public String codePath;
        public ArrayList<ConfigurationInfo> configPreferences;
        public boolean coreApp;
        public String cpuAbiOverride;
        public ArrayList<FeatureGroupInfo> featureGroups;
        public final ArrayList<String> implicitPermissions;
        public int installLocation;
        public final ArrayList<Instrumentation> instrumentation;
        public boolean isStub;
        public ArrayList<String> libraryNames;
        public ArrayList<String> mAdoptPermissions;
        public Bundle mAppMetaData;
        public int mCompileSdkVersion;
        public String mCompileSdkVersionCodename;
        public Object mExtras;
        public ArrayMap<String, ArraySet<PublicKey>> mKeySetMapping;
        public long[] mLastPackageUsageTimeInMills;
        public ArrayList<String> mOriginalPackages;
        public String mOverlayCategory;
        public boolean mOverlayIsStatic;
        public int mOverlayPriority;
        public String mOverlayTarget;
        public String mOverlayTargetName;
        public int mPreferredOrder;
        public String mRealPackage;
        public String mRequiredAccountType;
        public boolean mRequiredForAllUsers;
        public String mRestrictedAccountType;
        public String mSharedUserId;
        public int mSharedUserLabel;
        public SigningDetails mSigningDetails;
        public ArraySet<String> mUpgradeKeySets;
        public int mVersionCode;
        public int mVersionCodeMajor;
        public String mVersionName;
        public String manifestPackageName;
        public String packageName;
        public Package parentPackage;
        public final ArrayList<PermissionGroup> permissionGroups;
        public final ArrayList<Permission> permissions;
        public ArrayList<ActivityIntentInfo> preferredActivityFilters;
        public ArrayList<String> protectedBroadcasts;
        public final ArrayList<Provider> providers;
        public final ArrayList<Activity> receivers;
        public ArrayList<FeatureInfo> reqFeatures;
        public final ArrayList<String> requestedPermissions;
        public byte[] restrictUpdateHash;
        public final ArrayList<Service> services;
        public String[] splitCodePaths;
        public int[] splitFlags;
        public String[] splitNames;
        public int[] splitPrivateFlags;
        public int[] splitRevisionCodes;
        public String staticSharedLibName;
        public long staticSharedLibVersion;
        public boolean use32bitAbi;
        public ArrayList<String> usesLibraries;
        public String[] usesLibraryFiles;
        public ArrayList<SharedLibraryInfo> usesLibraryInfos;
        public ArrayList<String> usesOptionalLibraries;
        public ArrayList<String> usesStaticLibraries;
        public String[][] usesStaticLibrariesCertDigests;
        public long[] usesStaticLibrariesVersions;
        public boolean visibleToInstantApps;
        public String volumeUuid;

        public long getLongVersionCode() {
            return PackageInfo.composeLongVersionCode(this.mVersionCodeMajor, this.mVersionCode);
        }

        public Package(String packageName) {
            this.applicationInfo = new ApplicationInfo();
            this.permissions = new ArrayList<>(0);
            this.permissionGroups = new ArrayList<>(0);
            this.activities = new ArrayList<>(0);
            this.receivers = new ArrayList<>(0);
            this.providers = new ArrayList<>(0);
            this.services = new ArrayList<>(0);
            this.instrumentation = new ArrayList<>(0);
            this.requestedPermissions = new ArrayList<>();
            this.implicitPermissions = new ArrayList<>();
            this.staticSharedLibName = null;
            this.staticSharedLibVersion = 0L;
            this.libraryNames = null;
            this.usesLibraries = null;
            this.usesStaticLibraries = null;
            this.usesStaticLibrariesVersions = null;
            this.usesStaticLibrariesCertDigests = null;
            this.usesOptionalLibraries = null;
            this.usesLibraryFiles = null;
            this.usesLibraryInfos = null;
            this.preferredActivityFilters = null;
            this.mOriginalPackages = null;
            this.mRealPackage = null;
            this.mAdoptPermissions = null;
            this.mAppMetaData = null;
            this.mSigningDetails = SigningDetails.UNKNOWN;
            this.mPreferredOrder = 0;
            this.mLastPackageUsageTimeInMills = new long[8];
            this.configPreferences = null;
            this.reqFeatures = null;
            this.featureGroups = null;
            this.packageName = packageName;
            this.manifestPackageName = packageName;
            this.applicationInfo.packageName = packageName;
            this.applicationInfo.uid = -1;
        }

        public void setApplicationVolumeUuid(String volumeUuid) {
            UUID storageUuid = StorageManager.convert(volumeUuid);
            this.applicationInfo.volumeUuid = volumeUuid;
            this.applicationInfo.storageUuid = storageUuid;
            ArrayList<Package> arrayList = this.childPackages;
            if (arrayList != null) {
                int packageCount = arrayList.size();
                for (int i = 0; i < packageCount; i++) {
                    this.childPackages.get(i).applicationInfo.volumeUuid = volumeUuid;
                    this.childPackages.get(i).applicationInfo.storageUuid = storageUuid;
                }
            }
        }

        public void setApplicationInfoCodePath(String codePath) {
            this.applicationInfo.setCodePath(codePath);
            ArrayList<Package> arrayList = this.childPackages;
            if (arrayList != null) {
                int packageCount = arrayList.size();
                for (int i = 0; i < packageCount; i++) {
                    this.childPackages.get(i).applicationInfo.setCodePath(codePath);
                }
            }
        }

        @Deprecated
        public void setApplicationInfoResourcePath(String resourcePath) {
            this.applicationInfo.setResourcePath(resourcePath);
            ArrayList<Package> arrayList = this.childPackages;
            if (arrayList != null) {
                int packageCount = arrayList.size();
                for (int i = 0; i < packageCount; i++) {
                    this.childPackages.get(i).applicationInfo.setResourcePath(resourcePath);
                }
            }
        }

        @Deprecated
        public void setApplicationInfoBaseResourcePath(String resourcePath) {
            this.applicationInfo.setBaseResourcePath(resourcePath);
            ArrayList<Package> arrayList = this.childPackages;
            if (arrayList != null) {
                int packageCount = arrayList.size();
                for (int i = 0; i < packageCount; i++) {
                    this.childPackages.get(i).applicationInfo.setBaseResourcePath(resourcePath);
                }
            }
        }

        public void setApplicationInfoBaseCodePath(String baseCodePath) {
            this.applicationInfo.setBaseCodePath(baseCodePath);
            ArrayList<Package> arrayList = this.childPackages;
            if (arrayList != null) {
                int packageCount = arrayList.size();
                for (int i = 0; i < packageCount; i++) {
                    this.childPackages.get(i).applicationInfo.setBaseCodePath(baseCodePath);
                }
            }
        }

        public List<String> getChildPackageNames() {
            ArrayList<Package> arrayList = this.childPackages;
            if (arrayList == null) {
                return null;
            }
            int childCount = arrayList.size();
            List<String> childPackageNames = new ArrayList<>(childCount);
            for (int i = 0; i < childCount; i++) {
                String childPackageName = this.childPackages.get(i).packageName;
                childPackageNames.add(childPackageName);
            }
            return childPackageNames;
        }

        public boolean hasChildPackage(String packageName) {
            ArrayList<Package> arrayList = this.childPackages;
            int childCount = arrayList != null ? arrayList.size() : 0;
            for (int i = 0; i < childCount; i++) {
                if (this.childPackages.get(i).packageName.equals(packageName)) {
                    return true;
                }
            }
            return false;
        }

        public void setApplicationInfoSplitCodePaths(String[] splitCodePaths) {
            this.applicationInfo.setSplitCodePaths(splitCodePaths);
        }

        @Deprecated
        public void setApplicationInfoSplitResourcePaths(String[] resroucePaths) {
            this.applicationInfo.setSplitResourcePaths(resroucePaths);
        }

        public void setSplitCodePaths(String[] codePaths) {
            this.splitCodePaths = codePaths;
        }

        public void setCodePath(String codePath) {
            this.codePath = codePath;
            ArrayList<Package> arrayList = this.childPackages;
            if (arrayList != null) {
                int packageCount = arrayList.size();
                for (int i = 0; i < packageCount; i++) {
                    this.childPackages.get(i).codePath = codePath;
                }
            }
        }

        public void setBaseCodePath(String baseCodePath) {
            this.baseCodePath = baseCodePath;
            ArrayList<Package> arrayList = this.childPackages;
            if (arrayList != null) {
                int packageCount = arrayList.size();
                for (int i = 0; i < packageCount; i++) {
                    this.childPackages.get(i).baseCodePath = baseCodePath;
                }
            }
        }

        public void setSigningDetails(SigningDetails signingDetails) {
            this.mSigningDetails = signingDetails;
            ArrayList<Package> arrayList = this.childPackages;
            if (arrayList != null) {
                int packageCount = arrayList.size();
                for (int i = 0; i < packageCount; i++) {
                    this.childPackages.get(i).mSigningDetails = signingDetails;
                }
            }
        }

        public void setVolumeUuid(String volumeUuid) {
            this.volumeUuid = volumeUuid;
            ArrayList<Package> arrayList = this.childPackages;
            if (arrayList != null) {
                int packageCount = arrayList.size();
                for (int i = 0; i < packageCount; i++) {
                    this.childPackages.get(i).volumeUuid = volumeUuid;
                }
            }
        }

        public void setApplicationInfoFlags(int mask, int flags) {
            ApplicationInfo applicationInfo = this.applicationInfo;
            applicationInfo.flags = (applicationInfo.flags & (~mask)) | (mask & flags);
            ArrayList<Package> arrayList = this.childPackages;
            if (arrayList != null) {
                int packageCount = arrayList.size();
                for (int i = 0; i < packageCount; i++) {
                    this.childPackages.get(i).applicationInfo.flags = (this.applicationInfo.flags & (~mask)) | (mask & flags);
                }
            }
        }

        public void setUse32bitAbi(boolean use32bitAbi) {
            this.use32bitAbi = use32bitAbi;
            ArrayList<Package> arrayList = this.childPackages;
            if (arrayList != null) {
                int packageCount = arrayList.size();
                for (int i = 0; i < packageCount; i++) {
                    this.childPackages.get(i).use32bitAbi = use32bitAbi;
                }
            }
        }

        public boolean isLibrary() {
            return this.staticSharedLibName != null || !ArrayUtils.isEmpty(this.libraryNames);
        }

        public List<String> getAllCodePaths() {
            ArrayList<String> paths = new ArrayList<>();
            paths.add(this.baseCodePath);
            if (!ArrayUtils.isEmpty(this.splitCodePaths)) {
                Collections.addAll(paths, this.splitCodePaths);
            }
            return paths;
        }

        public List<String> getAllCodePathsExcludingResourceOnly() {
            ArrayList<String> paths = new ArrayList<>();
            if ((this.applicationInfo.flags & 4) != 0) {
                paths.add(this.baseCodePath);
            }
            if (!ArrayUtils.isEmpty(this.splitCodePaths)) {
                int i = 0;
                while (true) {
                    String[] strArr = this.splitCodePaths;
                    if (i >= strArr.length) {
                        break;
                    }
                    if ((this.splitFlags[i] & 4) != 0) {
                        paths.add(strArr[i]);
                    }
                    i++;
                }
            }
            return paths;
        }

        public void setPackageName(String newName) {
            this.packageName = newName;
            this.applicationInfo.packageName = newName;
            for (int i = this.permissions.size() - 1; i >= 0; i--) {
                this.permissions.get(i).setPackageName(newName);
            }
            for (int i2 = this.permissionGroups.size() - 1; i2 >= 0; i2--) {
                this.permissionGroups.get(i2).setPackageName(newName);
            }
            for (int i3 = this.activities.size() - 1; i3 >= 0; i3--) {
                this.activities.get(i3).setPackageName(newName);
            }
            for (int i4 = this.receivers.size() - 1; i4 >= 0; i4--) {
                this.receivers.get(i4).setPackageName(newName);
            }
            for (int i5 = this.providers.size() - 1; i5 >= 0; i5--) {
                this.providers.get(i5).setPackageName(newName);
            }
            for (int i6 = this.services.size() - 1; i6 >= 0; i6--) {
                this.services.get(i6).setPackageName(newName);
            }
            for (int i7 = this.instrumentation.size() - 1; i7 >= 0; i7--) {
                this.instrumentation.get(i7).setPackageName(newName);
            }
        }

        public boolean hasComponentClassName(String name) {
            for (int i = this.activities.size() - 1; i >= 0; i--) {
                if (name.equals(this.activities.get(i).className)) {
                    return true;
                }
            }
            for (int i2 = this.receivers.size() - 1; i2 >= 0; i2--) {
                if (name.equals(this.receivers.get(i2).className)) {
                    return true;
                }
            }
            for (int i3 = this.providers.size() - 1; i3 >= 0; i3--) {
                if (name.equals(this.providers.get(i3).className)) {
                    return true;
                }
            }
            for (int i4 = this.services.size() - 1; i4 >= 0; i4--) {
                if (name.equals(this.services.get(i4).className)) {
                    return true;
                }
            }
            for (int i5 = this.instrumentation.size() - 1; i5 >= 0; i5--) {
                if (name.equals(this.instrumentation.get(i5).className)) {
                    return true;
                }
            }
            return false;
        }

        public boolean isExternal() {
            return this.applicationInfo.isExternal();
        }

        public boolean isForwardLocked() {
            return false;
        }

        public boolean isOem() {
            return this.applicationInfo.isOem();
        }

        public boolean isVendor() {
            return this.applicationInfo.isVendor();
        }

        public boolean isProduct() {
            return this.applicationInfo.isProduct();
        }

        public boolean isSystemExt() {
            return this.applicationInfo.isSystemExt();
        }

        public boolean isOdm() {
            return this.applicationInfo.isOdm();
        }

        public boolean isPrivileged() {
            return this.applicationInfo.isPrivilegedApp();
        }

        public boolean isSystem() {
            return this.applicationInfo.isSystemApp();
        }

        public boolean isUpdatedSystemApp() {
            return this.applicationInfo.isUpdatedSystemApp();
        }

        public boolean canHaveOatDir() {
            return true;
        }

        public boolean isMatch(int flags) {
            if ((1048576 & flags) != 0) {
                return isSystem();
            }
            return true;
        }

        public long getLatestPackageUseTimeInMills() {
            long[] jArr;
            long latestUse = 0;
            for (long use : this.mLastPackageUsageTimeInMills) {
                latestUse = Math.max(latestUse, use);
            }
            return latestUse;
        }

        public long getLatestForegroundPackageUseTimeInMills() {
            int[] foregroundReasons = {0, 2};
            long latestUse = 0;
            for (int reason : foregroundReasons) {
                latestUse = Math.max(latestUse, this.mLastPackageUsageTimeInMills[reason]);
            }
            return latestUse;
        }

        public String toString() {
            return "Package{" + Integer.toHexString(System.identityHashCode(this)) + " " + this.packageName + "}";
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public Package(Parcel dest) {
            this.applicationInfo = new ApplicationInfo();
            ArrayList<Permission> arrayList = new ArrayList<>(0);
            this.permissions = arrayList;
            ArrayList<PermissionGroup> arrayList2 = new ArrayList<>(0);
            this.permissionGroups = arrayList2;
            ArrayList<Activity> arrayList3 = new ArrayList<>(0);
            this.activities = arrayList3;
            ArrayList<Activity> arrayList4 = new ArrayList<>(0);
            this.receivers = arrayList4;
            ArrayList<Provider> arrayList5 = new ArrayList<>(0);
            this.providers = arrayList5;
            ArrayList<Service> arrayList6 = new ArrayList<>(0);
            this.services = arrayList6;
            ArrayList<Instrumentation> arrayList7 = new ArrayList<>(0);
            this.instrumentation = arrayList7;
            ArrayList<String> arrayList8 = new ArrayList<>();
            this.requestedPermissions = arrayList8;
            ArrayList<String> arrayList9 = new ArrayList<>();
            this.implicitPermissions = arrayList9;
            this.staticSharedLibName = null;
            this.staticSharedLibVersion = 0L;
            this.libraryNames = null;
            this.usesLibraries = null;
            this.usesStaticLibraries = null;
            this.usesStaticLibrariesVersions = null;
            this.usesStaticLibrariesCertDigests = null;
            this.usesOptionalLibraries = null;
            this.usesLibraryFiles = null;
            this.usesLibraryInfos = null;
            this.preferredActivityFilters = null;
            this.mOriginalPackages = null;
            this.mRealPackage = null;
            this.mAdoptPermissions = null;
            this.mAppMetaData = null;
            this.mSigningDetails = SigningDetails.UNKNOWN;
            this.mPreferredOrder = 0;
            this.mLastPackageUsageTimeInMills = new long[8];
            this.configPreferences = null;
            this.reqFeatures = null;
            this.featureGroups = null;
            ClassLoader boot = Object.class.getClassLoader();
            this.packageName = dest.readString().intern();
            this.manifestPackageName = dest.readString();
            this.splitNames = dest.readStringArray();
            this.volumeUuid = dest.readString();
            this.codePath = dest.readString();
            this.baseCodePath = dest.readString();
            this.splitCodePaths = dest.readStringArray();
            this.baseRevisionCode = dest.readInt();
            this.splitRevisionCodes = dest.createIntArray();
            this.splitFlags = dest.createIntArray();
            this.splitPrivateFlags = dest.createIntArray();
            this.baseHardwareAccelerated = dest.readInt() == 1;
            ApplicationInfo applicationInfo = (ApplicationInfo) dest.readParcelable(boot);
            this.applicationInfo = applicationInfo;
            if (applicationInfo.permission != null) {
                ApplicationInfo applicationInfo2 = this.applicationInfo;
                applicationInfo2.permission = applicationInfo2.permission.intern();
            }
            dest.readParcelableList(arrayList, boot);
            fixupOwner(arrayList);
            dest.readParcelableList(arrayList2, boot);
            fixupOwner(arrayList2);
            dest.readParcelableList(arrayList3, boot);
            fixupOwner(arrayList3);
            dest.readParcelableList(arrayList4, boot);
            fixupOwner(arrayList4);
            dest.readParcelableList(arrayList5, boot);
            fixupOwner(arrayList5);
            dest.readParcelableList(arrayList6, boot);
            fixupOwner(arrayList6);
            dest.readParcelableList(arrayList7, boot);
            fixupOwner(arrayList7);
            dest.readStringList(arrayList8);
            internStringArrayList(arrayList8);
            dest.readStringList(arrayList9);
            internStringArrayList(arrayList9);
            ArrayList<String> createStringArrayList = dest.createStringArrayList();
            this.protectedBroadcasts = createStringArrayList;
            internStringArrayList(createStringArrayList);
            this.parentPackage = (Package) dest.readParcelable(boot);
            ArrayList<Package> arrayList10 = new ArrayList<>();
            this.childPackages = arrayList10;
            dest.readParcelableList(arrayList10, boot);
            if (this.childPackages.size() == 0) {
                this.childPackages = null;
            }
            String readString = dest.readString();
            this.staticSharedLibName = readString;
            if (readString != null) {
                this.staticSharedLibName = readString.intern();
            }
            this.staticSharedLibVersion = dest.readLong();
            ArrayList<String> createStringArrayList2 = dest.createStringArrayList();
            this.libraryNames = createStringArrayList2;
            internStringArrayList(createStringArrayList2);
            ArrayList<String> createStringArrayList3 = dest.createStringArrayList();
            this.usesLibraries = createStringArrayList3;
            internStringArrayList(createStringArrayList3);
            ArrayList<String> createStringArrayList4 = dest.createStringArrayList();
            this.usesOptionalLibraries = createStringArrayList4;
            internStringArrayList(createStringArrayList4);
            this.usesLibraryFiles = dest.readStringArray();
            this.usesLibraryInfos = dest.createTypedArrayList(SharedLibraryInfo.CREATOR);
            int libCount = dest.readInt();
            if (libCount > 0) {
                ArrayList<String> arrayList11 = new ArrayList<>(libCount);
                this.usesStaticLibraries = arrayList11;
                dest.readStringList(arrayList11);
                internStringArrayList(this.usesStaticLibraries);
                long[] jArr = new long[libCount];
                this.usesStaticLibrariesVersions = jArr;
                dest.readLongArray(jArr);
                this.usesStaticLibrariesCertDigests = new String[libCount];
                for (int i = 0; i < libCount; i++) {
                    this.usesStaticLibrariesCertDigests[i] = dest.createStringArray();
                }
            }
            ArrayList<ActivityIntentInfo> arrayList12 = new ArrayList<>();
            this.preferredActivityFilters = arrayList12;
            dest.readParcelableList(arrayList12, boot);
            if (this.preferredActivityFilters.size() == 0) {
                this.preferredActivityFilters = null;
            }
            this.mOriginalPackages = dest.createStringArrayList();
            this.mRealPackage = dest.readString();
            this.mAdoptPermissions = dest.createStringArrayList();
            this.mAppMetaData = dest.readBundle();
            this.mVersionCode = dest.readInt();
            this.mVersionCodeMajor = dest.readInt();
            String readString2 = dest.readString();
            this.mVersionName = readString2;
            if (readString2 != null) {
                this.mVersionName = readString2.intern();
            }
            String readString3 = dest.readString();
            this.mSharedUserId = readString3;
            if (readString3 != null) {
                this.mSharedUserId = readString3.intern();
            }
            this.mSharedUserLabel = dest.readInt();
            this.mSigningDetails = (SigningDetails) dest.readParcelable(boot);
            this.mPreferredOrder = dest.readInt();
            ArrayList<ConfigurationInfo> arrayList13 = new ArrayList<>();
            this.configPreferences = arrayList13;
            dest.readParcelableList(arrayList13, boot);
            if (this.configPreferences.size() == 0) {
                this.configPreferences = null;
            }
            ArrayList<FeatureInfo> arrayList14 = new ArrayList<>();
            this.reqFeatures = arrayList14;
            dest.readParcelableList(arrayList14, boot);
            if (this.reqFeatures.size() == 0) {
                this.reqFeatures = null;
            }
            ArrayList<FeatureGroupInfo> arrayList15 = new ArrayList<>();
            this.featureGroups = arrayList15;
            dest.readParcelableList(arrayList15, boot);
            if (this.featureGroups.size() == 0) {
                this.featureGroups = null;
            }
            this.installLocation = dest.readInt();
            this.coreApp = dest.readInt() == 1;
            this.mRequiredForAllUsers = dest.readInt() == 1;
            this.mRestrictedAccountType = dest.readString();
            this.mRequiredAccountType = dest.readString();
            this.mOverlayTarget = dest.readString();
            this.mOverlayTargetName = dest.readString();
            this.mOverlayCategory = dest.readString();
            this.mOverlayPriority = dest.readInt();
            this.mOverlayIsStatic = dest.readInt() == 1;
            this.mCompileSdkVersion = dest.readInt();
            this.mCompileSdkVersionCodename = dest.readString();
            this.mUpgradeKeySets = dest.readArraySet(boot);
            this.mKeySetMapping = readKeySetMapping(dest);
            this.cpuAbiOverride = dest.readString();
            this.use32bitAbi = dest.readInt() == 1;
            this.restrictUpdateHash = dest.createByteArray();
            this.visibleToInstantApps = dest.readInt() == 1;
        }

        private static void internStringArrayList(List<String> list) {
            if (list != null) {
                int N = list.size();
                for (int i = 0; i < N; i++) {
                    list.set(i, list.get(i).intern());
                }
            }
        }

        public void fixupOwner(List<? extends Component<?>> list) {
            if (list != null) {
                for (Component<?> c : list) {
                    c.owner = this;
                    if (c instanceof Activity) {
                        ((Activity) c).info.applicationInfo = this.applicationInfo;
                    } else if (c instanceof Service) {
                        ((Service) c).info.applicationInfo = this.applicationInfo;
                    } else if (c instanceof Provider) {
                        ((Provider) c).info.applicationInfo = this.applicationInfo;
                    }
                }
            }
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            String[][] strArr;
            dest.writeString(this.packageName);
            dest.writeString(this.manifestPackageName);
            dest.writeStringArray(this.splitNames);
            dest.writeString(this.volumeUuid);
            dest.writeString(this.codePath);
            dest.writeString(this.baseCodePath);
            dest.writeStringArray(this.splitCodePaths);
            dest.writeInt(this.baseRevisionCode);
            dest.writeIntArray(this.splitRevisionCodes);
            dest.writeIntArray(this.splitFlags);
            dest.writeIntArray(this.splitPrivateFlags);
            dest.writeInt(this.baseHardwareAccelerated ? 1 : 0);
            dest.writeParcelable(this.applicationInfo, flags);
            dest.writeParcelableList(this.permissions, flags);
            dest.writeParcelableList(this.permissionGroups, flags);
            dest.writeParcelableList(this.activities, flags);
            dest.writeParcelableList(this.receivers, flags);
            dest.writeParcelableList(this.providers, flags);
            dest.writeParcelableList(this.services, flags);
            dest.writeParcelableList(this.instrumentation, flags);
            dest.writeStringList(this.requestedPermissions);
            dest.writeStringList(this.implicitPermissions);
            dest.writeStringList(this.protectedBroadcasts);
            dest.writeParcelable(this.parentPackage, flags);
            dest.writeParcelableList(this.childPackages, flags);
            dest.writeString(this.staticSharedLibName);
            dest.writeLong(this.staticSharedLibVersion);
            dest.writeStringList(this.libraryNames);
            dest.writeStringList(this.usesLibraries);
            dest.writeStringList(this.usesOptionalLibraries);
            dest.writeStringArray(this.usesLibraryFiles);
            dest.writeTypedList(this.usesLibraryInfos);
            if (ArrayUtils.isEmpty(this.usesStaticLibraries)) {
                dest.writeInt(-1);
            } else {
                dest.writeInt(this.usesStaticLibraries.size());
                dest.writeStringList(this.usesStaticLibraries);
                dest.writeLongArray(this.usesStaticLibrariesVersions);
                for (String[] usesStaticLibrariesCertDigest : this.usesStaticLibrariesCertDigests) {
                    dest.writeStringArray(usesStaticLibrariesCertDigest);
                }
            }
            dest.writeParcelableList(this.preferredActivityFilters, flags);
            dest.writeStringList(this.mOriginalPackages);
            dest.writeString(this.mRealPackage);
            dest.writeStringList(this.mAdoptPermissions);
            dest.writeBundle(this.mAppMetaData);
            dest.writeInt(this.mVersionCode);
            dest.writeInt(this.mVersionCodeMajor);
            dest.writeString(this.mVersionName);
            dest.writeString(this.mSharedUserId);
            dest.writeInt(this.mSharedUserLabel);
            dest.writeParcelable(this.mSigningDetails, flags);
            dest.writeInt(this.mPreferredOrder);
            dest.writeParcelableList(this.configPreferences, flags);
            dest.writeParcelableList(this.reqFeatures, flags);
            dest.writeParcelableList(this.featureGroups, flags);
            dest.writeInt(this.installLocation);
            dest.writeInt(this.coreApp ? 1 : 0);
            dest.writeInt(this.mRequiredForAllUsers ? 1 : 0);
            dest.writeString(this.mRestrictedAccountType);
            dest.writeString(this.mRequiredAccountType);
            dest.writeString(this.mOverlayTarget);
            dest.writeString(this.mOverlayTargetName);
            dest.writeString(this.mOverlayCategory);
            dest.writeInt(this.mOverlayPriority);
            dest.writeInt(this.mOverlayIsStatic ? 1 : 0);
            dest.writeInt(this.mCompileSdkVersion);
            dest.writeString(this.mCompileSdkVersionCodename);
            dest.writeArraySet(this.mUpgradeKeySets);
            writeKeySetMapping(dest, this.mKeySetMapping);
            dest.writeString(this.cpuAbiOverride);
            dest.writeInt(this.use32bitAbi ? 1 : 0);
            dest.writeByteArray(this.restrictUpdateHash);
            dest.writeInt(this.visibleToInstantApps ? 1 : 0);
        }

        private static void writeKeySetMapping(Parcel dest, ArrayMap<String, ArraySet<PublicKey>> keySetMapping) {
            if (keySetMapping == null) {
                dest.writeInt(-1);
                return;
            }
            int N = keySetMapping.size();
            dest.writeInt(N);
            for (int i = 0; i < N; i++) {
                dest.writeString(keySetMapping.keyAt(i));
                ArraySet<PublicKey> keys = keySetMapping.valueAt(i);
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

        private static ArrayMap<String, ArraySet<PublicKey>> readKeySetMapping(Parcel in) {
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

    /* loaded from: classes.dex */
    public static abstract class Component<II extends IntentInfo> {
        public final String className;
        ComponentName componentName;
        String componentShortName;
        public final ArrayList<II> intents;
        public Bundle metaData;
        public int order;
        public Package owner;

        public Component(Package owner, ArrayList<II> intents, String className) {
            this.owner = owner;
            this.intents = intents;
            this.className = className;
        }

        public Component(Package owner) {
            this.owner = owner;
            this.intents = null;
            this.className = null;
        }

        public Component(ParsePackageItemArgs args, PackageItemInfo outInfo) {
            this.owner = args.owner;
            this.intents = new ArrayList<>(0);
            if (PackageParser.parsePackageItemInfo(args.owner, outInfo, args.outError, args.tag, args.sa, true, args.nameRes, args.labelRes, args.iconRes, args.roundIconRes, args.logoRes, args.bannerRes)) {
                this.className = outInfo.name;
            } else {
                this.className = null;
            }
        }

        public Component(ParseComponentArgs args, ComponentInfo outInfo) {
            this((ParsePackageItemArgs) args, (PackageItemInfo) outInfo);
            CharSequence pname;
            if (args.outError[0] == null) {
                if (args.processRes != 0) {
                    if (this.owner.applicationInfo.targetSdkVersion >= 8) {
                        pname = args.sa.getNonConfigurationString(args.processRes, 1024);
                    } else {
                        pname = args.sa.getNonResourceString(args.processRes);
                    }
                    outInfo.processName = PackageParser.buildProcessName(this.owner.applicationInfo.packageName, this.owner.applicationInfo.processName, pname, args.flags, args.sepProcesses, args.outError);
                }
                if (args.descriptionRes != 0) {
                    outInfo.descriptionRes = args.sa.getResourceId(args.descriptionRes, 0);
                }
                outInfo.enabled = args.sa.getBoolean(args.enabledRes, true);
            }
        }

        public Component(Component<II> clone) {
            this.owner = clone.owner;
            this.intents = clone.intents;
            this.className = clone.className;
            this.componentName = clone.componentName;
            this.componentShortName = clone.componentShortName;
        }

        public ComponentName getComponentName() {
            ComponentName componentName = this.componentName;
            if (componentName != null) {
                return componentName;
            }
            if (this.className != null) {
                this.componentName = new ComponentName(this.owner.applicationInfo.packageName, this.className);
            }
            return this.componentName;
        }

        protected Component(Parcel in) {
            this.className = in.readString();
            this.metaData = in.readBundle();
            this.intents = createIntentsList(in);
            this.owner = null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.className);
            dest.writeBundle(this.metaData);
            writeIntentsList(this.intents, dest, flags);
        }

        private static void writeIntentsList(ArrayList<? extends IntentInfo> list, Parcel out, int flags) {
            if (list == null) {
                out.writeInt(-1);
                return;
            }
            int N = list.size();
            out.writeInt(N);
            if (N > 0) {
                IntentInfo info = (IntentInfo) list.get(0);
                out.writeString(info.getClass().getName());
                for (int i = 0; i < N; i++) {
                    ((IntentInfo) list.get(i)).writeIntentInfoToParcel(out, flags);
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        private static <T extends IntentInfo> ArrayList<T> createIntentsList(Parcel in) {
            int N = in.readInt();
            if (N == -1) {
                return null;
            }
            if (N == 0) {
                return new ArrayList<>(0);
            }
            String componentName = in.readString();
            try {
                Constructor<?> constructor = Class.forName(componentName).getConstructor(Parcel.class);
                ArrayList<T> intentsList = (ArrayList<T>) new ArrayList(N);
                for (int i = 0; i < N; i++) {
                    intentsList.add((IntentInfo) constructor.newInstance(in));
                }
                return intentsList;
            } catch (ReflectiveOperationException e) {
                throw new AssertionError("Unable to construct intent list for: " + componentName);
            }
        }

        public void appendComponentShortName(StringBuilder sb) {
            ComponentName.appendShortString(sb, this.owner.applicationInfo.packageName, this.className);
        }

        public void printComponentShortName(PrintWriter pw) {
            ComponentName.printShortString(pw, this.owner.applicationInfo.packageName, this.className);
        }

        public void setPackageName(String packageName) {
            this.componentName = null;
            this.componentShortName = null;
        }
    }

    /* loaded from: classes.dex */
    public static final class Permission extends Component<IntentInfo> implements Parcelable {
        public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Permission>() { // from class: android.content.pm.PackageParser.Permission.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Permission createFromParcel(Parcel in) {
                return new Permission(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Permission[] newArray(int size) {
                return new Permission[size];
            }
        };
        public PermissionGroup group;
        public final PermissionInfo info;
        public boolean tree;

        public Permission(Package owner, String backgroundPermission) {
            super(owner);
            this.info = new PermissionInfo(backgroundPermission);
        }

        public Permission(Package _owner, PermissionInfo _info) {
            super(_owner);
            this.info = _info;
        }

        @Override // android.content.pm.PackageParser.Component
        public void setPackageName(String packageName) {
            super.setPackageName(packageName);
            this.info.packageName = packageName;
        }

        public String toString() {
            return "Permission{" + Integer.toHexString(System.identityHashCode(this)) + " " + this.info.name + "}";
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.content.pm.PackageParser.Component, android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeParcelable(this.info, flags);
            dest.writeInt(this.tree ? 1 : 0);
            dest.writeParcelable(this.group, flags);
        }

        public boolean isAppOp() {
            return this.info.isAppOp();
        }

        private Permission(Parcel in) {
            super(in);
            ClassLoader boot = Object.class.getClassLoader();
            PermissionInfo permissionInfo = (PermissionInfo) in.readParcelable(boot);
            this.info = permissionInfo;
            if (permissionInfo.group != null) {
                permissionInfo.group = permissionInfo.group.intern();
            }
            this.tree = in.readInt() != 1 ? false : true;
            this.group = (PermissionGroup) in.readParcelable(boot);
        }
    }

    /* loaded from: classes.dex */
    public static final class PermissionGroup extends Component<IntentInfo> implements Parcelable {
        public static final Parcelable.Creator CREATOR = new Parcelable.Creator<PermissionGroup>() { // from class: android.content.pm.PackageParser.PermissionGroup.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public PermissionGroup createFromParcel(Parcel in) {
                return new PermissionGroup(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public PermissionGroup[] newArray(int size) {
                return new PermissionGroup[size];
            }
        };
        public final PermissionGroupInfo info;

        public PermissionGroup(Package owner, int requestDetailResourceId, int backgroundRequestResourceId, int backgroundRequestDetailResourceId) {
            super(owner);
            this.info = new PermissionGroupInfo(requestDetailResourceId, backgroundRequestResourceId, backgroundRequestDetailResourceId);
        }

        public PermissionGroup(Package _owner, PermissionGroupInfo _info) {
            super(_owner);
            this.info = _info;
        }

        @Override // android.content.pm.PackageParser.Component
        public void setPackageName(String packageName) {
            super.setPackageName(packageName);
            this.info.packageName = packageName;
        }

        public String toString() {
            return "PermissionGroup{" + Integer.toHexString(System.identityHashCode(this)) + " " + this.info.name + "}";
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.content.pm.PackageParser.Component, android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeParcelable(this.info, flags);
        }

        private PermissionGroup(Parcel in) {
            super(in);
            this.info = (PermissionGroupInfo) in.readParcelable(Object.class.getClassLoader());
        }
    }

    private static boolean copyNeeded(int flags, Package p, PackageUserState state, Bundle metaData, int userId) {
        if (userId != 0) {
            return true;
        }
        if (state.enabled != 0) {
            boolean enabled = state.enabled == 1;
            if (p.applicationInfo.enabled != enabled) {
                return true;
            }
        }
        boolean suspended = (p.applicationInfo.flags & 1073741824) != 0;
        if (state.suspended != suspended || !state.installed || state.hidden || state.stopped || state.instantApp != p.applicationInfo.isInstantApp()) {
            return true;
        }
        if ((flags & 128) != 0 && (metaData != null || p.mAppMetaData != null)) {
            return true;
        }
        if ((flags & 1024) == 0 || p.usesLibraryFiles == null) {
            return (((flags & 1024) == 0 || p.usesLibraryInfos == null) && p.staticSharedLibName == null) ? false : true;
        }
        return true;
    }

    public static ApplicationInfo generateApplicationInfo(Package p, int flags, PackageUserState state) {
        return generateApplicationInfo(p, flags, state, UserHandle.getCallingUserId());
    }

    private static void updateApplicationInfo(ApplicationInfo ai, int flags, PackageUserState state) {
        if (!sCompatibilityModeEnabled) {
            ai.disableCompatibilityMode();
        }
        if (state.installed) {
            ai.flags |= 8388608;
        } else {
            ai.flags &= -8388609;
        }
        if (state.suspended) {
            ai.flags |= 1073741824;
        } else {
            ai.flags &= -1073741825;
        }
        if (state.instantApp) {
            ai.privateFlags |= 128;
        } else {
            ai.privateFlags &= -129;
        }
        if (state.virtualPreload) {
            ai.privateFlags |= 65536;
        } else {
            ai.privateFlags &= -65537;
        }
        if (state.hidden) {
            ai.privateFlags |= 1;
        } else {
            ai.privateFlags &= -2;
        }
        if (state.enabled == 1) {
            ai.enabled = true;
        } else if (state.enabled == 4) {
            ai.enabled = (32768 & flags) != 0;
        } else if (state.enabled == 2 || state.enabled == 3) {
            ai.enabled = false;
        }
        ai.enabledSetting = state.enabled;
        if (ai.category == -1) {
            ai.category = state.categoryHint;
        }
        if (ai.category == -1) {
            ai.category = FallbackCategoryProvider.getFallbackCategory(ai.packageName);
        }
        ai.seInfoUser = SELinuxUtil.assignSeinfoUser(state);
        OverlayPaths overlayPaths = state.getAllOverlayPaths();
        if (overlayPaths != null) {
            ai.resourceDirs = (String[]) overlayPaths.getResourceDirs().toArray(new String[0]);
            ai.overlayPaths = (String[]) overlayPaths.getOverlayPaths().toArray(new String[0]);
        }
        ai.icon = (!sUseRoundIcon || ai.roundIconRes == 0) ? ai.iconRes : ai.roundIconRes;
        PackageParserExtPlugin.hookUpdateApplicationInfo.call(null, ai.mApplicationInfoExt, state);
    }

    public static ApplicationInfo generateApplicationInfo(Package p, int flags, PackageUserState state, int userId) {
        if (p == null || !checkUseInstalledOrHidden(flags, state, p.applicationInfo) || !p.isMatch(flags)) {
            return null;
        }
        if (copyNeeded(flags, p, state, null, userId) || ((32768 & flags) != 0 && state.enabled == 4)) {
            ApplicationInfo ai = new ApplicationInfo(p.applicationInfo);
            ai.initForUser(userId);
            if ((flags & 128) != 0) {
                ai.metaData = p.mAppMetaData;
            }
            if ((flags & 1024) != 0) {
                ai.sharedLibraryFiles = p.usesLibraryFiles;
                ai.sharedLibraryInfos = p.usesLibraryInfos;
            }
            if (state.stopped) {
                ai.flags |= 2097152;
            } else {
                ai.flags &= -2097153;
            }
            updateApplicationInfo(ai, flags, state);
            return ai;
        }
        updateApplicationInfo(p.applicationInfo, flags, state);
        return p.applicationInfo;
    }

    public static ApplicationInfo generateApplicationInfo(ApplicationInfo ai, int flags, PackageUserState state, int userId) {
        if (ai == null || !checkUseInstalledOrHidden(flags, state, ai)) {
            return null;
        }
        ApplicationInfo ai2 = new ApplicationInfo(ai);
        ai2.initForUser(userId);
        if (state.stopped) {
            ai2.flags |= 2097152;
        } else {
            ai2.flags &= -2097153;
        }
        updateApplicationInfo(ai2, flags, state);
        return ai2;
    }

    public static final PermissionInfo generatePermissionInfo(Permission p, int flags) {
        if (p == null) {
            return null;
        }
        if ((flags & 128) == 0) {
            return p.info;
        }
        PermissionInfo pi = new PermissionInfo(p.info);
        pi.metaData = p.metaData;
        return pi;
    }

    public static final PermissionGroupInfo generatePermissionGroupInfo(PermissionGroup pg, int flags) {
        if (pg == null) {
            return null;
        }
        if ((flags & 128) == 0) {
            return pg.info;
        }
        PermissionGroupInfo pgi = new PermissionGroupInfo(pg.info);
        pgi.metaData = pg.metaData;
        return pgi;
    }

    /* loaded from: classes.dex */
    public static final class Activity extends Component<ActivityIntentInfo> implements Parcelable {
        public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Activity>() { // from class: android.content.pm.PackageParser.Activity.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Activity createFromParcel(Parcel in) {
                return new Activity(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Activity[] newArray(int size) {
                return new Activity[size];
            }
        };
        public final ActivityInfo info;
        private boolean mHasMaxAspectRatio;
        private boolean mHasMinAspectRatio;

        /* JADX INFO: Access modifiers changed from: private */
        public boolean hasMaxAspectRatio() {
            return this.mHasMaxAspectRatio;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean hasMinAspectRatio() {
            return this.mHasMinAspectRatio;
        }

        Activity(Package owner, String className, ActivityInfo info) {
            super(owner, new ArrayList(0), className);
            this.info = info;
            info.applicationInfo = owner.applicationInfo;
        }

        public Activity(ParseComponentArgs args, ActivityInfo _info) {
            super(args, (ComponentInfo) _info);
            this.info = _info;
            _info.applicationInfo = args.owner.applicationInfo;
        }

        @Override // android.content.pm.PackageParser.Component
        public void setPackageName(String packageName) {
            super.setPackageName(packageName);
            this.info.packageName = packageName;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMaxAspectRatio(float maxAspectRatio) {
            if (this.info.resizeMode != 2 && this.info.resizeMode != 1) {
                if (maxAspectRatio >= 1.0f || maxAspectRatio == 0.0f) {
                    this.info.setMaxAspectRatio(maxAspectRatio);
                    this.mHasMaxAspectRatio = true;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMinAspectRatio(float minAspectRatio) {
            if (this.info.resizeMode != 2 && this.info.resizeMode != 1) {
                if (minAspectRatio >= 1.0f || minAspectRatio == 0.0f) {
                    this.info.setMinAspectRatio(minAspectRatio);
                    this.mHasMinAspectRatio = true;
                }
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("Activity{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(' ');
            appendComponentShortName(sb);
            sb.append('}');
            return sb.toString();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.content.pm.PackageParser.Component, android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeParcelable(this.info, flags | 2);
            dest.writeBoolean(this.mHasMaxAspectRatio);
            dest.writeBoolean(this.mHasMinAspectRatio);
        }

        private Activity(Parcel in) {
            super(in);
            this.info = (ActivityInfo) in.readParcelable(Object.class.getClassLoader());
            this.mHasMaxAspectRatio = in.readBoolean();
            this.mHasMinAspectRatio = in.readBoolean();
            Iterator it = this.intents.iterator();
            while (it.hasNext()) {
                ActivityIntentInfo aii = (ActivityIntentInfo) it.next();
                aii.activity = this;
                this.order = Math.max(aii.getOrder(), this.order);
            }
            if (this.info.permission != null) {
                ActivityInfo activityInfo = this.info;
                activityInfo.permission = activityInfo.permission.intern();
            }
        }
    }

    public static final ActivityInfo generateActivityInfo(Activity a, int flags, PackageUserState state, int userId) {
        if (a == null || !checkUseInstalledOrHidden(flags, state, a.owner.applicationInfo)) {
            return null;
        }
        if (!copyNeeded(flags, a.owner, state, a.metaData, userId)) {
            updateApplicationInfo(a.info.applicationInfo, flags, state);
            return a.info;
        }
        ActivityInfo ai = new ActivityInfo(a.info);
        ai.metaData = a.metaData;
        ai.applicationInfo = generateApplicationInfo(a.owner, flags, state, userId);
        return ai;
    }

    public static final ActivityInfo generateActivityInfo(ActivityInfo ai, int flags, PackageUserState state, int userId) {
        if (ai == null || !checkUseInstalledOrHidden(flags, state, ai.applicationInfo)) {
            return null;
        }
        ActivityInfo ai2 = new ActivityInfo(ai);
        ai2.applicationInfo = generateApplicationInfo(ai2.applicationInfo, flags, state, userId);
        return ai2;
    }

    /* loaded from: classes.dex */
    public static final class Service extends Component<ServiceIntentInfo> implements Parcelable {
        public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Service>() { // from class: android.content.pm.PackageParser.Service.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Service createFromParcel(Parcel in) {
                return new Service(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Service[] newArray(int size) {
                return new Service[size];
            }
        };
        public final ServiceInfo info;

        public Service(ParseComponentArgs args, ServiceInfo _info) {
            super(args, (ComponentInfo) _info);
            this.info = _info;
            _info.applicationInfo = args.owner.applicationInfo;
        }

        @Override // android.content.pm.PackageParser.Component
        public void setPackageName(String packageName) {
            super.setPackageName(packageName);
            this.info.packageName = packageName;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("Service{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(' ');
            appendComponentShortName(sb);
            sb.append('}');
            return sb.toString();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.content.pm.PackageParser.Component, android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeParcelable(this.info, flags | 2);
        }

        private Service(Parcel in) {
            super(in);
            this.info = (ServiceInfo) in.readParcelable(Object.class.getClassLoader());
            Iterator it = this.intents.iterator();
            while (it.hasNext()) {
                ServiceIntentInfo aii = (ServiceIntentInfo) it.next();
                aii.service = this;
                this.order = Math.max(aii.getOrder(), this.order);
            }
            if (this.info.permission != null) {
                ServiceInfo serviceInfo = this.info;
                serviceInfo.permission = serviceInfo.permission.intern();
            }
        }
    }

    public static final ServiceInfo generateServiceInfo(Service s, int flags, PackageUserState state, int userId) {
        if (s == null || !checkUseInstalledOrHidden(flags, state, s.owner.applicationInfo)) {
            return null;
        }
        if (!copyNeeded(flags, s.owner, state, s.metaData, userId)) {
            updateApplicationInfo(s.info.applicationInfo, flags, state);
            return s.info;
        }
        ServiceInfo si = new ServiceInfo(s.info);
        si.metaData = s.metaData;
        si.applicationInfo = generateApplicationInfo(s.owner, flags, state, userId);
        return si;
    }

    /* loaded from: classes.dex */
    public static final class Provider extends Component<ProviderIntentInfo> implements Parcelable {
        public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Provider>() { // from class: android.content.pm.PackageParser.Provider.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Provider createFromParcel(Parcel in) {
                return new Provider(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Provider[] newArray(int size) {
                return new Provider[size];
            }
        };
        public final ProviderInfo info;
        public boolean syncable;

        public Provider(ParseComponentArgs args, ProviderInfo _info) {
            super(args, (ComponentInfo) _info);
            this.info = _info;
            _info.applicationInfo = args.owner.applicationInfo;
            this.syncable = false;
        }

        public Provider(Provider existingProvider) {
            super(existingProvider);
            this.info = existingProvider.info;
            this.syncable = existingProvider.syncable;
        }

        @Override // android.content.pm.PackageParser.Component
        public void setPackageName(String packageName) {
            super.setPackageName(packageName);
            this.info.packageName = packageName;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("Provider{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(' ');
            appendComponentShortName(sb);
            sb.append('}');
            return sb.toString();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.content.pm.PackageParser.Component, android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeParcelable(this.info, flags | 2);
            dest.writeInt(this.syncable ? 1 : 0);
        }

        private Provider(Parcel in) {
            super(in);
            this.info = (ProviderInfo) in.readParcelable(Object.class.getClassLoader());
            this.syncable = in.readInt() != 1 ? false : true;
            Iterator it = this.intents.iterator();
            while (it.hasNext()) {
                ProviderIntentInfo aii = (ProviderIntentInfo) it.next();
                aii.provider = this;
            }
            if (this.info.readPermission != null) {
                ProviderInfo providerInfo = this.info;
                providerInfo.readPermission = providerInfo.readPermission.intern();
            }
            if (this.info.writePermission != null) {
                ProviderInfo providerInfo2 = this.info;
                providerInfo2.writePermission = providerInfo2.writePermission.intern();
            }
            if (this.info.authority != null) {
                ProviderInfo providerInfo3 = this.info;
                providerInfo3.authority = providerInfo3.authority.intern();
            }
        }
    }

    public static final ProviderInfo generateProviderInfo(Provider p, int flags, PackageUserState state, int userId) {
        if (p == null || !checkUseInstalledOrHidden(flags, state, p.owner.applicationInfo)) {
            return null;
        }
        if (copyNeeded(flags, p.owner, state, p.metaData, userId) || ((flags & 2048) == 0 && p.info.uriPermissionPatterns != null)) {
            ProviderInfo pi = new ProviderInfo(p.info);
            pi.metaData = p.metaData;
            if ((flags & 2048) == 0) {
                pi.uriPermissionPatterns = null;
            }
            pi.applicationInfo = generateApplicationInfo(p.owner, flags, state, userId);
            return pi;
        }
        updateApplicationInfo(p.info.applicationInfo, flags, state);
        return p.info;
    }

    /* loaded from: classes.dex */
    public static final class Instrumentation extends Component<IntentInfo> implements Parcelable {
        public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Instrumentation>() { // from class: android.content.pm.PackageParser.Instrumentation.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Instrumentation createFromParcel(Parcel in) {
                return new Instrumentation(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Instrumentation[] newArray(int size) {
                return new Instrumentation[size];
            }
        };
        public final InstrumentationInfo info;

        public Instrumentation(ParsePackageItemArgs args, InstrumentationInfo _info) {
            super(args, _info);
            this.info = _info;
        }

        @Override // android.content.pm.PackageParser.Component
        public void setPackageName(String packageName) {
            super.setPackageName(packageName);
            this.info.packageName = packageName;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("Instrumentation{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(' ');
            appendComponentShortName(sb);
            sb.append('}');
            return sb.toString();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.content.pm.PackageParser.Component, android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeParcelable(this.info, flags);
        }

        private Instrumentation(Parcel in) {
            super(in);
            InstrumentationInfo instrumentationInfo = (InstrumentationInfo) in.readParcelable(Object.class.getClassLoader());
            this.info = instrumentationInfo;
            if (instrumentationInfo.targetPackage != null) {
                instrumentationInfo.targetPackage = instrumentationInfo.targetPackage.intern();
            }
            if (instrumentationInfo.targetProcesses != null) {
                instrumentationInfo.targetProcesses = instrumentationInfo.targetProcesses.intern();
            }
        }
    }

    public static final InstrumentationInfo generateInstrumentationInfo(Instrumentation i, int flags) {
        if (i == null) {
            return null;
        }
        if ((flags & 128) == 0) {
            return i.info;
        }
        InstrumentationInfo ii = new InstrumentationInfo(i.info);
        ii.metaData = i.metaData;
        return ii;
    }

    /* loaded from: classes.dex */
    public static abstract class IntentInfo extends IntentFilter {
        public int banner;
        public boolean hasDefault;
        public int icon;
        public int labelRes;
        public int logo;
        public CharSequence nonLocalizedLabel;
        public int preferred;

        protected IntentInfo() {
        }

        protected IntentInfo(Parcel dest) {
            super(dest);
            this.hasDefault = dest.readInt() != 1 ? false : true;
            this.labelRes = dest.readInt();
            this.nonLocalizedLabel = dest.readCharSequence();
            this.icon = dest.readInt();
            this.logo = dest.readInt();
            this.banner = dest.readInt();
            this.preferred = dest.readInt();
        }

        public void writeIntentInfoToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(this.hasDefault ? 1 : 0);
            dest.writeInt(this.labelRes);
            dest.writeCharSequence(this.nonLocalizedLabel);
            dest.writeInt(this.icon);
            dest.writeInt(this.logo);
            dest.writeInt(this.banner);
            dest.writeInt(this.preferred);
        }
    }

    /* loaded from: classes.dex */
    public static final class ActivityIntentInfo extends IntentInfo {
        public Activity activity;

        public ActivityIntentInfo(Activity _activity) {
            this.activity = _activity;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("ActivityIntentInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(' ');
            this.activity.appendComponentShortName(sb);
            sb.append('}');
            return sb.toString();
        }

        public ActivityIntentInfo(Parcel in) {
            super(in);
        }
    }

    /* loaded from: classes.dex */
    public static final class ServiceIntentInfo extends IntentInfo {
        public Service service;

        public ServiceIntentInfo(Service _service) {
            this.service = _service;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("ServiceIntentInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(' ');
            this.service.appendComponentShortName(sb);
            sb.append('}');
            return sb.toString();
        }

        public ServiceIntentInfo(Parcel in) {
            super(in);
        }
    }

    /* loaded from: classes.dex */
    public static final class ProviderIntentInfo extends IntentInfo {
        public Provider provider;

        public ProviderIntentInfo(Provider provider) {
            this.provider = provider;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("ProviderIntentInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(' ');
            this.provider.appendComponentShortName(sb);
            sb.append('}');
            return sb.toString();
        }

        public ProviderIntentInfo(Parcel in) {
            super(in);
        }
    }

    public static void setCompatibilityModeEnabled(boolean compatibilityModeEnabled) {
        sCompatibilityModeEnabled = compatibilityModeEnabled;
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

    /* loaded from: classes.dex */
    public static class PackageParserException extends Exception {
        public final int error;

        public PackageParserException(int error, String detailMessage) {
            super(detailMessage);
            this.error = error;
        }

        public PackageParserException(int error, String detailMessage, Throwable throwable) {
            super(detailMessage, throwable);
            this.error = error;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Deprecated
    /* loaded from: classes.dex */
    public static abstract class SplitDependencyLoader<E extends Exception> {
        private final SparseArray<int[]> mDependencies;

        protected abstract void constructSplit(int i, int[] iArr, int i2) throws Exception;

        protected abstract boolean isSplitCached(int i);

        protected SplitDependencyLoader(SparseArray<int[]> dependencies) {
            this.mDependencies = dependencies;
        }

        protected void loadDependenciesForSplit(int splitIdx) throws Exception {
            if (!isSplitCached(splitIdx)) {
                if (splitIdx == 0) {
                    int[] configSplitIndices = collectConfigSplitIndices(0);
                    constructSplit(0, configSplitIndices, -1);
                    return;
                }
                IntArray linearDependencies = new IntArray();
                linearDependencies.add(splitIdx);
                while (true) {
                    int[] deps = this.mDependencies.get(splitIdx);
                    if (deps == null || deps.length <= 0) {
                        splitIdx = -1;
                    } else {
                        splitIdx = deps[0];
                    }
                    if (splitIdx < 0 || isSplitCached(splitIdx)) {
                        break;
                    }
                    linearDependencies.add(splitIdx);
                }
                int parentIdx = splitIdx;
                for (int i = linearDependencies.size() - 1; i >= 0; i--) {
                    int idx = linearDependencies.get(i);
                    int[] configSplitIndices2 = collectConfigSplitIndices(idx);
                    constructSplit(idx, configSplitIndices2, parentIdx);
                    parentIdx = idx;
                }
            }
        }

        private int[] collectConfigSplitIndices(int splitIdx) {
            int[] deps = this.mDependencies.get(splitIdx);
            if (deps == null || deps.length <= 1) {
                return EmptyArray.INT;
            }
            return Arrays.copyOfRange(deps, 1, deps.length);
        }

        /* loaded from: classes.dex */
        public static class IllegalDependencyException extends Exception {
            private IllegalDependencyException(String message) {
                super(message);
            }
        }

        private static int[] append(int[] src, int elem) {
            if (src == null) {
                return new int[]{elem};
            }
            int[] dst = Arrays.copyOf(src, src.length + 1);
            dst[src.length] = elem;
            return dst;
        }

        public static SparseArray<int[]> createDependenciesFromPackage(PackageLite pkg) throws IllegalDependencyException {
            int depIdx;
            int depIdx2;
            SparseArray<int[]> splitDependencies = new SparseArray<>();
            splitDependencies.put(0, new int[]{-1});
            for (int splitIdx = 0; splitIdx < pkg.splitNames.length; splitIdx++) {
                if (pkg.isFeatureSplits[splitIdx]) {
                    String splitDependency = pkg.usesSplitNames[splitIdx];
                    if (splitDependency != null) {
                        int depIdx3 = Arrays.binarySearch(pkg.splitNames, splitDependency);
                        if (depIdx3 >= 0) {
                            depIdx2 = depIdx3 + 1;
                        } else {
                            throw new IllegalDependencyException("Split '" + pkg.splitNames[splitIdx] + "' requires split '" + splitDependency + "', which is missing.");
                        }
                    } else {
                        depIdx2 = 0;
                    }
                    splitDependencies.put(splitIdx + 1, new int[]{depIdx2});
                }
            }
            int size = pkg.splitNames.length;
            for (int splitIdx2 = 0; splitIdx2 < size; splitIdx2++) {
                if (!pkg.isFeatureSplits[splitIdx2]) {
                    String configForSplit = pkg.configForSplit[splitIdx2];
                    if (configForSplit != null) {
                        int depIdx4 = Arrays.binarySearch(pkg.splitNames, configForSplit);
                        if (depIdx4 < 0) {
                            throw new IllegalDependencyException("Split '" + pkg.splitNames[splitIdx2] + "' targets split '" + configForSplit + "', which is missing.");
                        } else if (pkg.isFeatureSplits[depIdx4]) {
                            depIdx = depIdx4 + 1;
                        } else {
                            throw new IllegalDependencyException("Split '" + pkg.splitNames[splitIdx2] + "' declares itself as configuration split for a non-feature split '" + pkg.splitNames[depIdx4] + "'");
                        }
                    } else {
                        depIdx = 0;
                    }
                    splitDependencies.put(depIdx, append(splitDependencies.get(depIdx), splitIdx2 + 1));
                }
            }
            BitSet bitset = new BitSet();
            int size2 = splitDependencies.size();
            for (int i = 0; i < size2; i++) {
                int splitIdx3 = splitDependencies.keyAt(i);
                bitset.clear();
                while (splitIdx3 != -1) {
                    if (!bitset.get(splitIdx3)) {
                        bitset.set(splitIdx3);
                        int[] deps = splitDependencies.get(splitIdx3);
                        splitIdx3 = deps != null ? deps[0] : -1;
                    } else {
                        throw new IllegalDependencyException("Cycle detected in split dependencies.");
                    }
                }
            }
            return splitDependencies;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Deprecated
    /* loaded from: classes.dex */
    public static class DefaultSplitAssetLoader implements SplitAssetLoader {
        private ApkAssets mBaseApkAssets;
        private final String mBaseCodePath;
        private AssetManager mCachedAssetManager;
        private final int mFlags;
        private final String[] mSplitCodePaths;

        DefaultSplitAssetLoader(PackageLite pkg, int flags) {
            this.mBaseCodePath = pkg.baseCodePath;
            this.mSplitCodePaths = pkg.splitCodePaths;
            this.mFlags = flags;
        }

        private static ApkAssets loadApkAssets(String path, int flags) throws PackageParserException {
            if ((flags & 1) == 0 || PackageParser.isApkPath(path)) {
                try {
                    return ApkAssets.loadFromPath(path);
                } catch (IOException e) {
                    throw new PackageParserException(-2, "Failed to load APK at path " + path, e);
                }
            } else {
                throw new PackageParserException(-100, "Invalid package file: " + path);
            }
        }

        @Override // android.content.pm.split.SplitAssetLoader
        public AssetManager getBaseAssetManager() throws PackageParserException {
            AssetManager assetManager = this.mCachedAssetManager;
            if (assetManager != null) {
                return assetManager;
            }
            String[] strArr = this.mSplitCodePaths;
            ApkAssets[] apkAssets = new ApkAssets[(strArr != null ? strArr.length : 0) + 1];
            ApkAssets loadApkAssets = loadApkAssets(this.mBaseCodePath, this.mFlags);
            this.mBaseApkAssets = loadApkAssets;
            int splitIdx = 0 + 1;
            apkAssets[0] = loadApkAssets;
            if (!ArrayUtils.isEmpty(this.mSplitCodePaths)) {
                String[] strArr2 = this.mSplitCodePaths;
                int length = strArr2.length;
                int i = 0;
                while (i < length) {
                    String apkPath = strArr2[i];
                    apkAssets[splitIdx] = loadApkAssets(apkPath, this.mFlags);
                    i++;
                    splitIdx++;
                }
            }
            AssetManager assets = new AssetManager();
            assets.setConfiguration(0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Build.VERSION.RESOURCES_SDK_INT);
            assets.setApkAssets(apkAssets, false);
            this.mCachedAssetManager = assets;
            return assets;
        }

        @Override // android.content.pm.split.SplitAssetLoader
        public AssetManager getSplitAssetManager(int splitIdx) throws PackageParserException {
            return getBaseAssetManager();
        }

        @Override // java.lang.AutoCloseable
        public void close() throws Exception {
            IoUtils.closeQuietly(this.mCachedAssetManager);
        }

        @Override // android.content.pm.split.SplitAssetLoader
        public ApkAssets getBaseApkAssets() {
            return this.mBaseApkAssets;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Deprecated
    /* loaded from: classes.dex */
    public static class SplitAssetDependencyLoader extends SplitDependencyLoader<PackageParserException> implements SplitAssetLoader {
        private final AssetManager[] mCachedAssetManagers;
        private final ApkAssets[][] mCachedSplitApks;
        private final int mFlags;
        private final String[] mSplitPaths;

        SplitAssetDependencyLoader(PackageLite pkg, SparseArray<int[]> dependencies, int flags) {
            super(dependencies);
            String[] strArr = new String[pkg.splitCodePaths.length + 1];
            this.mSplitPaths = strArr;
            strArr[0] = pkg.baseCodePath;
            System.arraycopy(pkg.splitCodePaths, 0, strArr, 1, pkg.splitCodePaths.length);
            this.mFlags = flags;
            this.mCachedSplitApks = new ApkAssets[strArr.length];
            this.mCachedAssetManagers = new AssetManager[strArr.length];
        }

        @Override // android.content.pm.PackageParser.SplitDependencyLoader
        protected boolean isSplitCached(int splitIdx) {
            return this.mCachedAssetManagers[splitIdx] != null;
        }

        private static ApkAssets loadApkAssets(String path, int flags) throws PackageParserException {
            if ((flags & 1) == 0 || PackageParser.isApkPath(path)) {
                try {
                    return ApkAssets.loadFromPath(path);
                } catch (IOException e) {
                    throw new PackageParserException(-2, "Failed to load APK at path " + path, e);
                }
            } else {
                throw new PackageParserException(-100, "Invalid package file: " + path);
            }
        }

        private static AssetManager createAssetManagerWithAssets(ApkAssets[] apkAssets) {
            AssetManager assets = new AssetManager();
            assets.setConfiguration(0, 0, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Build.VERSION.RESOURCES_SDK_INT);
            assets.setApkAssets(apkAssets, false);
            return assets;
        }

        @Override // android.content.pm.PackageParser.SplitDependencyLoader
        protected void constructSplit(int splitIdx, int[] configSplitIndices, int parentSplitIdx) throws PackageParserException {
            ArrayList<ApkAssets> assets = new ArrayList<>();
            if (parentSplitIdx >= 0) {
                Collections.addAll(assets, this.mCachedSplitApks[parentSplitIdx]);
            }
            assets.add(loadApkAssets(this.mSplitPaths[splitIdx], this.mFlags));
            for (int configSplitIdx : configSplitIndices) {
                assets.add(loadApkAssets(this.mSplitPaths[configSplitIdx], this.mFlags));
            }
            this.mCachedSplitApks[splitIdx] = (ApkAssets[]) assets.toArray(new ApkAssets[assets.size()]);
            this.mCachedAssetManagers[splitIdx] = createAssetManagerWithAssets(this.mCachedSplitApks[splitIdx]);
        }

        @Override // android.content.pm.split.SplitAssetLoader
        public AssetManager getBaseAssetManager() throws PackageParserException {
            loadDependenciesForSplit(0);
            return this.mCachedAssetManagers[0];
        }

        @Override // android.content.pm.split.SplitAssetLoader
        public AssetManager getSplitAssetManager(int idx) throws PackageParserException {
            loadDependenciesForSplit(idx + 1);
            return this.mCachedAssetManagers[idx + 1];
        }

        @Override // java.lang.AutoCloseable
        public void close() throws Exception {
            AssetManager[] assetManagerArr;
            for (AssetManager assets : this.mCachedAssetManagers) {
                IoUtils.closeQuietly(assets);
            }
        }

        @Override // android.content.pm.split.SplitAssetLoader
        public ApkAssets getBaseApkAssets() {
            return this.mCachedSplitApks[0][0];
        }
    }
}
