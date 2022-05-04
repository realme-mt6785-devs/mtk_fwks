package android.content.pm.parsing;

import android.apex.ApexInfo;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.Attribution;
import android.content.pm.ComponentInfo;
import android.content.pm.ConfigurationInfo;
import android.content.pm.FallbackCategoryProvider;
import android.content.pm.FeatureGroupInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.InstrumentationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageParser;
import android.content.pm.PackageUserState;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.SELinuxUtil;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.content.pm.overlay.OverlayPaths;
import android.content.pm.parsing.component.ComponentParseUtils;
import android.content.pm.parsing.component.ParsedActivity;
import android.content.pm.parsing.component.ParsedAttribution;
import android.content.pm.parsing.component.ParsedComponent;
import android.content.pm.parsing.component.ParsedInstrumentation;
import android.content.pm.parsing.component.ParsedMainComponent;
import android.content.pm.parsing.component.ParsedPermission;
import android.content.pm.parsing.component.ParsedPermissionGroup;
import android.content.pm.parsing.component.ParsedProvider;
import android.content.pm.parsing.component.ParsedService;
import android.content.pm.parsing.component.ParsedUsesPermission;
import android.os.Environment;
import android.os.UserHandle;
import com.android.internal.util.ArrayUtils;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import libcore.util.EmptyArray;
/* loaded from: classes.dex */
public class PackageInfoWithoutStateUtils {
    public static final String SYSTEM_DATA_PATH = Environment.getDataDirectoryPath() + File.separator + "system";

    public static PackageInfo generate(ParsingPackageRead pkg, int[] gids, int flags, long firstInstallTime, long lastUpdateTime, Set<String> grantedPermissions, PackageUserState state, int userId) {
        return generateWithComponents(pkg, gids, flags, firstInstallTime, lastUpdateTime, grantedPermissions, state, userId, null);
    }

    public static PackageInfo generate(ParsingPackageRead pkg, ApexInfo apexInfo, int flags) {
        return generateWithComponents(pkg, EmptyArray.INT, flags, 0L, 0L, Collections.emptySet(), new PackageUserState(), UserHandle.getCallingUserId(), apexInfo);
    }

    private static PackageInfo generateWithComponents(ParsingPackageRead pkg, int[] gids, int flags, long firstInstallTime, long lastUpdateTime, Set<String> grantedPermissions, PackageUserState state, int userId, ApexInfo apexInfo) {
        PackageInfo info;
        int N;
        int size;
        int size2;
        int size3;
        int N2;
        ApplicationInfo applicationInfo = generateApplicationInfo(pkg, flags, state, userId);
        if (applicationInfo == null || (info = generateWithoutComponents(pkg, gids, flags, firstInstallTime, lastUpdateTime, grantedPermissions, state, userId, apexInfo, applicationInfo)) == null) {
            return null;
        }
        if ((flags & 1) != 0 && (N2 = pkg.getActivities().size()) > 0) {
            ActivityInfo[] res = new ActivityInfo[N2];
            int num = 0;
            for (int i = 0; i < N2; i++) {
                ParsedActivity a = pkg.getActivities().get(i);
                if (ComponentParseUtils.isMatch(state, false, pkg.isEnabled(), a, flags) && !PackageManager.APP_DETAILS_ACTIVITY_CLASS_NAME.equals(a.getName())) {
                    res[num] = generateActivityInfo(pkg, a, flags, state, applicationInfo, userId);
                    num++;
                }
            }
            info.activities = (ActivityInfo[]) ArrayUtils.trimToSize(res, num);
        }
        if ((flags & 2) != 0 && (size3 = pkg.getReceivers().size()) > 0) {
            ActivityInfo[] res2 = new ActivityInfo[size3];
            int num2 = 0;
            for (int i2 = 0; i2 < size3; i2++) {
                ParsedActivity a2 = pkg.getReceivers().get(i2);
                if (ComponentParseUtils.isMatch(state, false, pkg.isEnabled(), a2, flags)) {
                    res2[num2] = generateActivityInfo(pkg, a2, flags, state, applicationInfo, userId);
                    num2++;
                }
            }
            info.receivers = (ActivityInfo[]) ArrayUtils.trimToSize(res2, num2);
        }
        if ((flags & 4) != 0 && (size2 = pkg.getServices().size()) > 0) {
            ServiceInfo[] res3 = new ServiceInfo[size2];
            int num3 = 0;
            for (int i3 = 0; i3 < size2; i3++) {
                ParsedService s = pkg.getServices().get(i3);
                if (ComponentParseUtils.isMatch(state, false, pkg.isEnabled(), s, flags)) {
                    res3[num3] = generateServiceInfo(pkg, s, flags, state, applicationInfo, userId);
                    num3++;
                }
            }
            info.services = (ServiceInfo[]) ArrayUtils.trimToSize(res3, num3);
        }
        if ((flags & 8) != 0 && (size = pkg.getProviders().size()) > 0) {
            ProviderInfo[] res4 = new ProviderInfo[size];
            int num4 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                ParsedProvider pr = pkg.getProviders().get(i4);
                if (ComponentParseUtils.isMatch(state, false, pkg.isEnabled(), pr, flags)) {
                    res4[num4] = generateProviderInfo(pkg, pr, flags, state, applicationInfo, userId);
                    num4++;
                }
            }
            info.providers = (ProviderInfo[]) ArrayUtils.trimToSize(res4, num4);
        }
        if ((flags & 16) != 0 && (N = pkg.getInstrumentations().size()) > 0) {
            info.instrumentation = new InstrumentationInfo[N];
            for (int i5 = 0; i5 < N; i5++) {
                info.instrumentation[i5] = generateInstrumentationInfo(pkg.getInstrumentations().get(i5), pkg, flags, userId, true);
            }
        }
        return info;
    }

    public static PackageInfo generateWithoutComponents(ParsingPackageRead pkg, int[] gids, int flags, long firstInstallTime, long lastUpdateTime, Set<String> grantedPermissions, PackageUserState state, int userId, ApexInfo apexInfo, ApplicationInfo applicationInfo) {
        if (!checkUseInstalled(pkg, state, flags)) {
            return null;
        }
        return generateWithoutComponentsUnchecked(pkg, gids, flags, firstInstallTime, lastUpdateTime, grantedPermissions, state, userId, apexInfo, applicationInfo);
    }

    public static PackageInfo generateWithoutComponentsUnchecked(ParsingPackageRead pkg, int[] gids, int flags, long firstInstallTime, long lastUpdateTime, Set<String> grantedPermissions, PackageUserState state, int userId, ApexInfo apexInfo, ApplicationInfo applicationInfo) {
        PackageInfo pi = new PackageInfo();
        pi.packageName = pkg.getPackageName();
        pi.splitNames = pkg.getSplitNames();
        pi.versionCode = pkg.getVersionCode();
        pi.versionCodeMajor = pkg.getVersionCodeMajor();
        pi.baseRevisionCode = pkg.getBaseRevisionCode();
        pi.splitRevisionCodes = pkg.getSplitRevisionCodes();
        pi.versionName = pkg.getVersionName();
        pi.sharedUserId = pkg.getSharedUserId();
        pi.sharedUserLabel = pkg.getSharedUserLabel();
        pi.applicationInfo = applicationInfo;
        pi.installLocation = pkg.getInstallLocation();
        if (!((pi.applicationInfo.flags & 1) == 0 && (pi.applicationInfo.flags & 128) == 0)) {
            pi.requiredForAllUsers = pkg.isRequiredForAllUsers();
        }
        pi.restrictedAccountType = pkg.getRestrictedAccountType();
        pi.requiredAccountType = pkg.getRequiredAccountType();
        pi.overlayTarget = pkg.getOverlayTarget();
        pi.targetOverlayableName = pkg.getOverlayTargetName();
        pi.overlayCategory = pkg.getOverlayCategory();
        pi.overlayPriority = pkg.getOverlayPriority();
        pi.mOverlayIsStatic = pkg.isOverlayIsStatic();
        pi.compileSdkVersion = pkg.getCompileSdkVersion();
        pi.compileSdkVersionCodename = pkg.getCompileSdkVersionCodeName();
        pi.firstInstallTime = firstInstallTime;
        pi.lastUpdateTime = lastUpdateTime;
        if ((flags & 256) != 0) {
            pi.gids = gids;
        }
        if ((flags & 16384) != 0) {
            int size = pkg.getConfigPreferences().size();
            if (size > 0) {
                pi.configPreferences = new ConfigurationInfo[size];
                pkg.getConfigPreferences().toArray(pi.configPreferences);
            }
            int size2 = pkg.getReqFeatures().size();
            if (size2 > 0) {
                pi.reqFeatures = new FeatureInfo[size2];
                pkg.getReqFeatures().toArray(pi.reqFeatures);
            }
            int size3 = pkg.getFeatureGroups().size();
            if (size3 > 0) {
                pi.featureGroups = new FeatureGroupInfo[size3];
                pkg.getFeatureGroups().toArray(pi.featureGroups);
            }
        }
        if ((flags & 4096) != 0) {
            int size4 = ArrayUtils.size(pkg.getPermissions());
            if (size4 > 0) {
                pi.permissions = new PermissionInfo[size4];
                for (int i = 0; i < size4; i++) {
                    pi.permissions[i] = generatePermissionInfo(pkg.getPermissions().get(i), flags);
                }
            }
            List<ParsedUsesPermission> usesPermissions = pkg.getUsesPermissions();
            int size5 = usesPermissions.size();
            if (size5 > 0) {
                pi.requestedPermissions = new String[size5];
                pi.requestedPermissionsFlags = new int[size5];
                for (int i2 = 0; i2 < size5; i2++) {
                    ParsedUsesPermission usesPermission = usesPermissions.get(i2);
                    pi.requestedPermissions[i2] = usesPermission.name;
                    int[] iArr = pi.requestedPermissionsFlags;
                    iArr[i2] = iArr[i2] | 1;
                    if (grantedPermissions != null && grantedPermissions.contains(usesPermission.name)) {
                        int[] iArr2 = pi.requestedPermissionsFlags;
                        iArr2[i2] = iArr2[i2] | 2;
                    }
                    if ((usesPermission.usesPermissionFlags & 65536) != 0) {
                        int[] iArr3 = pi.requestedPermissionsFlags;
                        iArr3[i2] = iArr3[i2] | 65536;
                    }
                }
            }
        }
        if ((Integer.MIN_VALUE & flags) != 0) {
            int size6 = ArrayUtils.size(pkg.getAttributions());
            if (size6 > 0) {
                pi.attributions = new Attribution[size6];
                for (int i3 = 0; i3 < size6; i3++) {
                    pi.attributions[i3] = generateAttribution(pkg.getAttributions().get(i3));
                }
            }
            if (pkg.areAttributionsUserVisible()) {
                pi.applicationInfo.privateFlagsExt |= 4;
            } else {
                pi.applicationInfo.privateFlagsExt &= -5;
            }
        } else {
            pi.applicationInfo.privateFlagsExt &= -5;
        }
        if (apexInfo != null) {
            File apexFile = new File(apexInfo.modulePath);
            pi.applicationInfo.sourceDir = apexFile.getPath();
            pi.applicationInfo.publicSourceDir = apexFile.getPath();
            if (apexInfo.isFactory) {
                pi.applicationInfo.flags |= 1;
                pi.applicationInfo.flags &= -129;
            } else {
                pi.applicationInfo.flags &= -2;
                pi.applicationInfo.flags |= 128;
            }
            if (apexInfo.isActive) {
                pi.applicationInfo.flags |= 8388608;
            } else {
                pi.applicationInfo.flags &= -8388609;
            }
            pi.isApex = true;
        }
        PackageParser.SigningDetails signingDetails = pkg.getSigningDetails();
        if ((flags & 64) != 0) {
            if (signingDetails.hasPastSigningCertificates()) {
                pi.signatures = new Signature[1];
                pi.signatures[0] = signingDetails.pastSigningCertificates[0];
            } else if (signingDetails.hasSignatures()) {
                int numberOfSigs = signingDetails.signatures.length;
                pi.signatures = new Signature[numberOfSigs];
                System.arraycopy(signingDetails.signatures, 0, pi.signatures, 0, numberOfSigs);
            }
        }
        if ((134217728 & flags) != 0) {
            if (signingDetails != PackageParser.SigningDetails.UNKNOWN) {
                pi.signingInfo = new SigningInfo(signingDetails);
            } else {
                pi.signingInfo = null;
            }
        }
        return pi;
    }

    public static ApplicationInfo generateApplicationInfo(ParsingPackageRead pkg, int flags, PackageUserState state, int userId) {
        if (pkg != null && checkUseInstalled(pkg, state, flags)) {
            return generateApplicationInfoUnchecked(pkg, flags, state, userId, true);
        }
        return null;
    }

    public static ApplicationInfo generateApplicationInfoUnchecked(ParsingPackageRead pkg, int flags, PackageUserState state, int userId, boolean assignUserFields) {
        ApplicationInfo ai = pkg.toAppInfoWithoutState();
        if (assignUserFields) {
            assignUserFields(pkg, ai, userId);
        }
        if ((flags & 128) == 0) {
            ai.metaData = null;
        }
        if ((flags & 1024) == 0) {
            ai.sharedLibraryFiles = null;
            ai.sharedLibraryInfos = null;
        }
        if (!PackageParser.sCompatibilityModeEnabled) {
            ai.disableCompatibilityMode();
        }
        ai.flags |= flag(state.stopped, 2097152) | flag(state.installed, 8388608) | flag(state.suspended, 1073741824);
        ai.privateFlags |= flag(state.instantApp, 128) | flag(state.virtualPreload, 65536) | flag(state.hidden, 1);
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
        PackageInfoWithoutStateUtilsExtPlugin.adjustResultInGenerateApplicationInfoUnchecked.call(ai, state, ai.mApplicationInfoExt, state.mPackageUserStateExt);
        return ai;
    }

    public static ActivityInfo generateActivityInfo(ParsingPackageRead pkg, ParsedActivity a, int flags, PackageUserState state, ApplicationInfo applicationInfo, int userId) {
        if (a == null || !checkUseInstalled(pkg, state, flags)) {
            return null;
        }
        if (applicationInfo == null) {
            applicationInfo = generateApplicationInfo(pkg, flags, state, userId);
        }
        if (applicationInfo == null) {
            return null;
        }
        return generateActivityInfoUnchecked(a, flags, applicationInfo);
    }

    public static ActivityInfo generateActivityInfoUnchecked(ParsedActivity a, int flags, ApplicationInfo applicationInfo) {
        ActivityInfo ai = new ActivityInfo();
        assignSharedFieldsForComponentInfo(ai, a);
        ai.targetActivity = a.getTargetActivity();
        ai.processName = a.getProcessName();
        ai.exported = a.isExported();
        ai.theme = a.getTheme();
        ai.uiOptions = a.getUiOptions();
        ai.parentActivityName = a.getParentActivityName();
        ai.permission = a.getPermission();
        ai.taskAffinity = a.getTaskAffinity();
        ai.flags = a.getFlags();
        ai.privateFlags = a.getPrivateFlags();
        ai.launchMode = a.getLaunchMode();
        ai.documentLaunchMode = a.getDocumentLaunchMode();
        ai.maxRecents = a.getMaxRecents();
        ai.configChanges = a.getConfigChanges();
        ai.softInputMode = a.getSoftInputMode();
        ai.persistableMode = a.getPersistableMode();
        ai.lockTaskLaunchMode = a.getLockTaskLaunchMode();
        ai.screenOrientation = a.getScreenOrientation();
        ai.resizeMode = a.getResizeMode();
        Float maxAspectRatio = a.getMaxAspectRatio();
        float f = 0.0f;
        ai.setMaxAspectRatio(maxAspectRatio != null ? maxAspectRatio.floatValue() : 0.0f);
        Float minAspectRatio = a.getMinAspectRatio();
        if (minAspectRatio != null) {
            f = minAspectRatio.floatValue();
        }
        ai.setMinAspectRatio(f);
        ai.supportsSizeChanges = a.getSupportsSizeChanges();
        ai.requestedVrComponent = a.getRequestedVrComponent();
        ai.rotationAnimation = a.getRotationAnimation();
        ai.colorMode = a.getColorMode();
        ai.windowLayout = a.getWindowLayout();
        ai.attributionTags = a.getAttributionTags();
        if ((flags & 128) != 0) {
            ai.metaData = a.getMetaData();
        }
        ai.applicationInfo = applicationInfo;
        PackageInfoWithoutStateUtilsExtPlugin.adjustResultInGenerateActivityInfoUnchecked.call(ai, a);
        return ai;
    }

    public static ActivityInfo generateActivityInfo(ParsingPackageRead pkg, ParsedActivity a, int flags, PackageUserState state, int userId) {
        return generateActivityInfo(pkg, a, flags, state, null, userId);
    }

    public static ServiceInfo generateServiceInfo(ParsingPackageRead pkg, ParsedService s, int flags, PackageUserState state, ApplicationInfo applicationInfo, int userId) {
        if (s == null || !checkUseInstalled(pkg, state, flags)) {
            return null;
        }
        if (applicationInfo == null) {
            applicationInfo = generateApplicationInfo(pkg, flags, state, userId);
        }
        if (applicationInfo == null) {
            return null;
        }
        return generateServiceInfoUnchecked(s, flags, applicationInfo);
    }

    public static ServiceInfo generateServiceInfoUnchecked(ParsedService s, int flags, ApplicationInfo applicationInfo) {
        ServiceInfo si = new ServiceInfo();
        assignSharedFieldsForComponentInfo(si, s);
        si.exported = s.isExported();
        si.flags = s.getFlags();
        si.permission = s.getPermission();
        si.processName = s.getProcessName();
        si.mForegroundServiceType = s.getForegroundServiceType();
        si.applicationInfo = applicationInfo;
        if ((flags & 128) != 0) {
            si.metaData = s.getMetaData();
        }
        return si;
    }

    public static ServiceInfo generateServiceInfo(ParsingPackageRead pkg, ParsedService s, int flags, PackageUserState state, int userId) {
        return generateServiceInfo(pkg, s, flags, state, null, userId);
    }

    public static ProviderInfo generateProviderInfo(ParsingPackageRead pkg, ParsedProvider p, int flags, PackageUserState state, ApplicationInfo applicationInfo, int userId) {
        if (p == null || !checkUseInstalled(pkg, state, flags)) {
            return null;
        }
        if (applicationInfo == null) {
            applicationInfo = generateApplicationInfo(pkg, flags, state, userId);
        }
        if (applicationInfo == null) {
            return null;
        }
        return generateProviderInfoUnchecked(p, flags, applicationInfo);
    }

    public static ProviderInfo generateProviderInfoUnchecked(ParsedProvider p, int flags, ApplicationInfo applicationInfo) {
        ProviderInfo pi = new ProviderInfo();
        assignSharedFieldsForComponentInfo(pi, p);
        pi.exported = p.isExported();
        pi.flags = p.getFlags();
        pi.processName = p.getProcessName();
        pi.authority = p.getAuthority();
        pi.isSyncable = p.isSyncable();
        pi.readPermission = p.getReadPermission();
        pi.writePermission = p.getWritePermission();
        pi.grantUriPermissions = p.isGrantUriPermissions();
        pi.forceUriPermissions = p.isForceUriPermissions();
        pi.multiprocess = p.isMultiProcess();
        pi.initOrder = p.getInitOrder();
        pi.uriPermissionPatterns = p.getUriPermissionPatterns();
        pi.pathPermissions = p.getPathPermissions();
        if ((flags & 2048) == 0) {
            pi.uriPermissionPatterns = null;
        }
        if ((flags & 128) != 0) {
            pi.metaData = p.getMetaData();
        }
        pi.applicationInfo = applicationInfo;
        return pi;
    }

    public static ProviderInfo generateProviderInfo(ParsingPackageRead pkg, ParsedProvider p, int flags, PackageUserState state, int userId) {
        return generateProviderInfo(pkg, p, flags, state, null, userId);
    }

    public static InstrumentationInfo generateInstrumentationInfo(ParsedInstrumentation i, ParsingPackageRead pkg, int flags, int userId, boolean assignUserFields) {
        if (i == null) {
            return null;
        }
        InstrumentationInfo ii = new InstrumentationInfo();
        assignSharedFieldsForPackageItemInfo(ii, i);
        ii.targetPackage = i.getTargetPackage();
        ii.targetProcesses = i.getTargetProcesses();
        ii.handleProfiling = i.isHandleProfiling();
        ii.functionalTest = i.isFunctionalTest();
        ii.sourceDir = pkg.getBaseApkPath();
        ii.publicSourceDir = pkg.getBaseApkPath();
        ii.splitNames = pkg.getSplitNames();
        ii.splitSourceDirs = pkg.getSplitCodePaths();
        ii.splitPublicSourceDirs = pkg.getSplitCodePaths();
        ii.splitDependencies = pkg.getSplitDependencies();
        if (assignUserFields) {
            assignUserFields(pkg, ii, userId);
        }
        if ((flags & 128) == 0) {
            return ii;
        }
        ii.metaData = i.getMetaData();
        return ii;
    }

    public static PermissionInfo generatePermissionInfo(ParsedPermission p, int flags) {
        if (p == null) {
            return null;
        }
        PermissionInfo pi = new PermissionInfo(p.getBackgroundPermission());
        assignSharedFieldsForPackageItemInfo(pi, p);
        pi.group = p.getGroup();
        pi.requestRes = p.getRequestRes();
        pi.protectionLevel = p.getProtectionLevel();
        pi.descriptionRes = p.getDescriptionRes();
        pi.flags = p.getFlags();
        pi.knownCerts = p.getKnownCerts();
        if ((flags & 128) == 0) {
            return pi;
        }
        pi.metaData = p.getMetaData();
        return pi;
    }

    public static PermissionGroupInfo generatePermissionGroupInfo(ParsedPermissionGroup pg, int flags) {
        if (pg == null) {
            return null;
        }
        PermissionGroupInfo pgi = new PermissionGroupInfo(pg.getRequestDetailResourceId(), pg.getBackgroundRequestResourceId(), pg.getBackgroundRequestDetailResourceId());
        assignSharedFieldsForPackageItemInfo(pgi, pg);
        pgi.descriptionRes = pg.getDescriptionRes();
        pgi.priority = pg.getPriority();
        pgi.requestRes = pg.getRequestRes();
        pgi.flags = pg.getFlags();
        if ((flags & 128) == 0) {
            return pgi;
        }
        pgi.metaData = pg.getMetaData();
        return pgi;
    }

    public static Attribution generateAttribution(ParsedAttribution pa) {
        if (pa == null) {
            return null;
        }
        return new Attribution(pa.tag, pa.label);
    }

    private static void assignSharedFieldsForComponentInfo(ComponentInfo componentInfo, ParsedMainComponent mainComponent) {
        assignSharedFieldsForPackageItemInfo(componentInfo, mainComponent);
        componentInfo.descriptionRes = mainComponent.getDescriptionRes();
        componentInfo.directBootAware = mainComponent.isDirectBootAware();
        componentInfo.enabled = mainComponent.isEnabled();
        componentInfo.splitName = mainComponent.getSplitName();
        componentInfo.attributionTags = mainComponent.getAttributionTags();
    }

    private static void assignSharedFieldsForPackageItemInfo(PackageItemInfo packageItemInfo, ParsedComponent component) {
        packageItemInfo.nonLocalizedLabel = ComponentParseUtils.getNonLocalizedLabel(component);
        packageItemInfo.icon = ComponentParseUtils.getIcon(component);
        packageItemInfo.banner = component.getBanner();
        packageItemInfo.labelRes = component.getLabelRes();
        packageItemInfo.logo = component.getLogo();
        packageItemInfo.name = component.getName();
        packageItemInfo.packageName = component.getPackageName();
    }

    private static int flag(boolean hasFlag, int flag) {
        if (hasFlag) {
            return flag;
        }
        return 0;
    }

    public static int appInfoFlags(ParsingPackageRead pkg) {
        return flag(pkg.isExternalStorage(), 262144) | flag(pkg.isBaseHardwareAccelerated(), 536870912) | flag(pkg.isAllowBackup(), 32768) | flag(pkg.isKillAfterRestore(), 65536) | flag(pkg.isRestoreAnyVersion(), 131072) | flag(pkg.isFullBackupOnly(), 67108864) | flag(pkg.isPersistent(), 8) | flag(pkg.isDebuggable(), 2) | flag(pkg.isVmSafeMode(), 16384) | flag(pkg.isHasCode(), 4) | flag(pkg.isAllowTaskReparenting(), 32) | flag(pkg.isAllowClearUserData(), 64) | flag(pkg.isLargeHeap(), 1048576) | flag(pkg.isUsesCleartextTraffic(), 134217728) | flag(pkg.isSupportsRtl(), 4194304) | flag(pkg.isTestOnly(), 256) | flag(pkg.isMultiArch(), Integer.MIN_VALUE) | flag(pkg.isExtractNativeLibs(), 268435456) | flag(pkg.isGame(), 33554432) | flag(pkg.isSupportsSmallScreens(), 512) | flag(pkg.isSupportsNormalScreens(), 1024) | flag(pkg.isSupportsLargeScreens(), 2048) | flag(pkg.isSupportsExtraLargeScreens(), 524288) | flag(pkg.isResizeable(), 4096) | flag(pkg.isAnyDensity(), 8192);
    }

    public static int appInfoPrivateFlags(ParsingPackageRead pkg) {
        int privateFlags = flag(pkg.isStaticSharedLibrary(), 16384) | flag(pkg.isOverlay(), 268435456) | flag(pkg.isIsolatedSplitLoading(), 32768) | flag(pkg.isHasDomainUrls(), 16) | flag(pkg.isProfileableByShell(), 8388608) | flag(pkg.isBackupInForeground(), 8192) | flag(pkg.isUseEmbeddedDex(), 33554432) | flag(pkg.isDefaultToDeviceProtectedStorage(), 32) | flag(pkg.isDirectBootAware(), 64) | flag(pkg.isPartiallyDirectBootAware(), 256) | flag(pkg.isAllowClearUserDataOnFailedRestore(), 67108864) | flag(pkg.isAllowAudioPlaybackCapture(), 134217728) | flag(pkg.isRequestLegacyExternalStorage(), 536870912) | flag(pkg.isUsesNonSdkApi(), 4194304) | flag(pkg.isHasFragileUserData(), 16777216) | flag(pkg.isCantSaveState(), 2) | flag(pkg.isResizeableActivityViaSdkVersion(), 4096) | flag(pkg.isAllowNativeHeapPointerTagging(), Integer.MIN_VALUE);
        Boolean resizeableActivity = pkg.getResizeableActivity();
        if (resizeableActivity == null) {
            return privateFlags;
        }
        if (resizeableActivity.booleanValue()) {
            return privateFlags | 1024;
        }
        return privateFlags | 2048;
    }

    public static int appInfoPrivateFlagsExt(ParsingPackageRead pkg) {
        int privateFlagsExt = flag(pkg.isProfileable(), 1) | flag(pkg.hasRequestForegroundServiceExemption(), 2) | flag(pkg.areAttributionsUserVisible(), 4);
        return privateFlagsExt;
    }

    private static boolean checkUseInstalled(ParsingPackageRead pkg, PackageUserState state, int flags) {
        return state.isAvailable(flags);
    }

    public static File getDataDir(ParsingPackageRead pkg, int userId) {
        if ("android".equals(pkg.getPackageName())) {
            return Environment.getDataSystemDirectory();
        }
        if (pkg.isDefaultToDeviceProtectedStorage()) {
            return getDeviceProtectedDataDir(pkg, userId);
        }
        return getCredentialProtectedDataDir(pkg, userId);
    }

    public static File getDeviceProtectedDataDir(ParsingPackageRead pkg, int userId) {
        return Environment.getDataUserDePackageDirectory(pkg.getVolumeUuid(), userId, pkg.getPackageName());
    }

    public static File getCredentialProtectedDataDir(ParsingPackageRead pkg, int userId) {
        return Environment.getDataUserCePackageDirectory(pkg.getVolumeUuid(), userId, pkg.getPackageName());
    }

    private static void assignUserFields(ParsingPackageRead pkg, ApplicationInfo info, int userId) {
        info.uid = UserHandle.getUid(userId, UserHandle.getAppId(info.uid));
        String pkgName = pkg.getPackageName();
        if ("android".equals(pkgName)) {
            info.dataDir = SYSTEM_DATA_PATH;
            return;
        }
        String baseDataDirPrefix = Environment.getDataDirectoryPath(pkg.getVolumeUuid()) + File.separator;
        String userIdPkgSuffix = File.separator + userId + File.separator + pkgName;
        info.credentialProtectedDataDir = baseDataDirPrefix + "user" + userIdPkgSuffix;
        info.deviceProtectedDataDir = baseDataDirPrefix + Environment.DIR_USER_DE + userIdPkgSuffix;
        if (pkg.isDefaultToDeviceProtectedStorage()) {
            info.dataDir = info.deviceProtectedDataDir;
        } else {
            info.dataDir = info.credentialProtectedDataDir;
        }
    }

    private static void assignUserFields(ParsingPackageRead pkg, InstrumentationInfo info, int userId) {
        String pkgName = pkg.getPackageName();
        if ("android".equals(pkgName)) {
            info.dataDir = SYSTEM_DATA_PATH;
            return;
        }
        String baseDataDirPrefix = Environment.getDataDirectoryPath(pkg.getVolumeUuid()) + File.separator;
        String userIdPkgSuffix = File.separator + userId + File.separator + pkgName;
        info.credentialProtectedDataDir = baseDataDirPrefix + "user" + userIdPkgSuffix;
        info.deviceProtectedDataDir = baseDataDirPrefix + Environment.DIR_USER_DE + userIdPkgSuffix;
        if (pkg.isDefaultToDeviceProtectedStorage()) {
            info.dataDir = info.deviceProtectedDataDir;
        } else {
            info.dataDir = info.credentialProtectedDataDir;
        }
    }
}
