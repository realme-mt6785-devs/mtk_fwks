package android.content.pm.parsing;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ConfigurationInfo;
import android.content.pm.FeatureGroupInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageParser;
import android.content.pm.parsing.component.ParsedActivity;
import android.content.pm.parsing.component.ParsedAttribution;
import android.content.pm.parsing.component.ParsedInstrumentation;
import android.content.pm.parsing.component.ParsedIntentInfo;
import android.content.pm.parsing.component.ParsedPermission;
import android.content.pm.parsing.component.ParsedPermissionGroup;
import android.content.pm.parsing.component.ParsedProcess;
import android.content.pm.parsing.component.ParsedProvider;
import android.content.pm.parsing.component.ParsedService;
import android.content.pm.parsing.component.ParsedUsesPermission;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.ArraySet;
import android.util.Pair;
import android.util.SparseArray;
import android.util.SparseIntArray;
import java.security.PublicKey;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public interface ParsingPackageRead extends Parcelable {
    boolean areAttributionsUserVisible();

    List<ParsedActivity> getActivities();

    List<String> getAdoptPermissions();

    String getAppComponentFactory();

    List<ParsedAttribution> getAttributions();

    int getAutoRevokePermissions();

    String getBackupAgentName();

    int getBanner();

    String getBaseApkPath();

    int getBaseRevisionCode();

    int getCategory();

    String getClassLoaderName();

    String getClassName();

    int getCompatibleWidthLimitDp();

    int getCompileSdkVersion();

    String getCompileSdkVersionCodeName();

    List<ConfigurationInfo> getConfigPreferences();

    int getDataExtractionRules();

    int getDescriptionRes();

    List<FeatureGroupInfo> getFeatureGroups();

    int getFullBackupContent();

    int getGwpAsanMode();

    int getIconRes();

    List<String> getImplicitPermissions();

    int getInstallLocation();

    List<ParsedInstrumentation> getInstrumentations();

    Map<String, ArraySet<PublicKey>> getKeySetMapping();

    int getLabelRes();

    int getLargestWidthLimitDp();

    List<String> getLibraryNames();

    int getLogo();

    String getManageSpaceActivityName();

    float getMaxAspectRatio();

    int getMemtagMode();

    Bundle getMetaData();

    Set<String> getMimeGroups();

    float getMinAspectRatio();

    SparseIntArray getMinExtensionVersions();

    int getMinSdkVersion();

    int getNativeHeapZeroInitialized();

    int getNetworkSecurityConfigRes();

    CharSequence getNonLocalizedLabel();

    List<String> getOriginalPackages();

    String getOverlayCategory();

    int getOverlayPriority();

    String getOverlayTarget();

    String getOverlayTargetName();

    Map<String, String> getOverlayables();

    String getPackageName();

    String getPath();

    String getPermission();

    List<ParsedPermissionGroup> getPermissionGroups();

    List<ParsedPermission> getPermissions();

    List<Pair<String, ParsedIntentInfo>> getPreferredActivityFilters();

    String getProcessName();

    Map<String, ParsedProcess> getProcesses();

    Map<String, PackageManager.Property> getProperties();

    List<String> getProtectedBroadcasts();

    List<ParsedProvider> getProviders();

    List<Intent> getQueriesIntents();

    List<String> getQueriesPackages();

    Set<String> getQueriesProviders();

    String getRealPackage();

    List<ParsedActivity> getReceivers();

    List<FeatureInfo> getReqFeatures();

    @Deprecated
    List<String> getRequestedPermissions();

    String getRequiredAccountType();

    int getRequiresSmallestWidthDp();

    Boolean getResizeableActivity();

    byte[] getRestrictUpdateHash();

    String getRestrictedAccountType();

    int getRoundIconRes();

    List<ParsedService> getServices();

    @Deprecated
    String getSharedUserId();

    @Deprecated
    int getSharedUserLabel();

    PackageParser.SigningDetails getSigningDetails();

    String[] getSplitClassLoaderNames();

    String[] getSplitCodePaths();

    SparseArray<int[]> getSplitDependencies();

    int[] getSplitFlags();

    String[] getSplitNames();

    int[] getSplitRevisionCodes();

    String getStaticSharedLibName();

    long getStaticSharedLibVersion();

    @Deprecated
    int getTargetSandboxVersion();

    int getTargetSdkVersion();

    String getTaskAffinity();

    int getTheme();

    int getUiOptions();

    Set<String> getUpgradeKeySets();

    List<String> getUsesLibraries();

    List<String> getUsesNativeLibraries();

    List<String> getUsesOptionalLibraries();

    List<String> getUsesOptionalNativeLibraries();

    List<ParsedUsesPermission> getUsesPermissions();

    List<String> getUsesStaticLibraries();

    String[][] getUsesStaticLibrariesCertDigests();

    long[] getUsesStaticLibrariesVersions();

    int getVersionCode();

    int getVersionCodeMajor();

    String getVersionName();

    String getVolumeUuid();

    String getZygotePreloadName();

    boolean hasPreserveLegacyExternalStorage();

    boolean hasRequestForegroundServiceExemption();

    Boolean hasRequestRawExternalStorageAccess();

    boolean isAllowAudioPlaybackCapture();

    boolean isAllowBackup();

    boolean isAllowClearUserData();

    boolean isAllowClearUserDataOnFailedRestore();

    boolean isAllowNativeHeapPointerTagging();

    boolean isAllowTaskReparenting();

    boolean isAnyDensity();

    boolean isBackupInForeground();

    boolean isBaseHardwareAccelerated();

    boolean isCantSaveState();

    boolean isCrossProfile();

    boolean isDebuggable();

    boolean isDefaultToDeviceProtectedStorage();

    boolean isDirectBootAware();

    boolean isEnabled();

    boolean isExternalStorage();

    boolean isExtractNativeLibs();

    boolean isForceQueryable();

    boolean isFullBackupOnly();

    @Deprecated
    boolean isGame();

    boolean isHasCode();

    boolean isHasDomainUrls();

    boolean isHasFragileUserData();

    boolean isIsolatedSplitLoading();

    boolean isKillAfterRestore();

    boolean isLargeHeap();

    boolean isMultiArch();

    boolean isOverlay();

    boolean isOverlayIsStatic();

    boolean isPartiallyDirectBootAware();

    boolean isPersistent();

    boolean isProfileable();

    boolean isProfileableByShell();

    boolean isRequestLegacyExternalStorage();

    boolean isRequiredForAllUsers();

    boolean isResizeable();

    boolean isResizeableActivityViaSdkVersion();

    boolean isRestoreAnyVersion();

    boolean isStaticSharedLibrary();

    boolean isSupportsExtraLargeScreens();

    boolean isSupportsLargeScreens();

    boolean isSupportsNormalScreens();

    boolean isSupportsRtl();

    boolean isSupportsSmallScreens();

    boolean isTestOnly();

    boolean isUse32BitAbi();

    boolean isUseEmbeddedDex();

    boolean isUsesCleartextTraffic();

    boolean isUsesNonSdkApi();

    boolean isVisibleToInstantApps();

    boolean isVmSafeMode();

    ApplicationInfo toAppInfoWithoutState();

    ApplicationInfo toAppInfoWithoutStateWithoutFlags();
}
