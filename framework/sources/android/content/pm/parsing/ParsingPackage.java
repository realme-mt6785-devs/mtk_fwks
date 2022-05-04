package android.content.pm.parsing;

import android.content.Intent;
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
import android.util.SparseArray;
import android.util.SparseIntArray;
import java.security.PublicKey;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public interface ParsingPackage extends ParsingPackageRead {
    ParsingPackage addActivity(ParsedActivity parsedActivity);

    ParsingPackage addAdoptPermission(String str);

    ParsingPackage addAttribution(ParsedAttribution parsedAttribution);

    ParsingPackage addConfigPreference(ConfigurationInfo configurationInfo);

    ParsingPackage addFeatureGroup(FeatureGroupInfo featureGroupInfo);

    ParsingPackage addImplicitPermission(String str);

    ParsingPackage addInstrumentation(ParsedInstrumentation parsedInstrumentation);

    ParsingPackage addKeySet(String str, PublicKey publicKey);

    ParsingPackage addLibraryName(String str);

    ParsingPackage addOriginalPackage(String str);

    ParsingPackage addOverlayable(String str, String str2);

    ParsingPackage addPermission(ParsedPermission parsedPermission);

    ParsingPackage addPermissionGroup(ParsedPermissionGroup parsedPermissionGroup);

    ParsingPackage addPreferredActivityFilter(String str, ParsedIntentInfo parsedIntentInfo);

    ParsingPackage addProperty(PackageManager.Property property);

    ParsingPackage addProtectedBroadcast(String str);

    ParsingPackage addProvider(ParsedProvider parsedProvider);

    ParsingPackage addQueriesIntent(Intent intent);

    ParsingPackage addQueriesPackage(String str);

    ParsingPackage addQueriesProvider(String str);

    ParsingPackage addReceiver(ParsedActivity parsedActivity);

    ParsingPackage addReqFeature(FeatureInfo featureInfo);

    ParsingPackage addService(ParsedService parsedService);

    ParsingPackage addUsesLibrary(String str);

    ParsingPackage addUsesNativeLibrary(String str);

    ParsingPackage addUsesOptionalLibrary(String str);

    ParsingPackage addUsesOptionalNativeLibrary(String str);

    ParsingPackage addUsesPermission(ParsedUsesPermission parsedUsesPermission);

    ParsingPackage addUsesStaticLibrary(String str);

    ParsingPackage addUsesStaticLibraryCertDigests(String[] strArr);

    ParsingPackage addUsesStaticLibraryVersion(long j);

    ParsingPackage asSplit(String[] strArr, String[] strArr2, int[] iArr, SparseArray<int[]> sparseArray);

    Object hideAsParsed();

    ParsingPackage removeUsesOptionalLibrary(String str);

    ParsingPackage removeUsesOptionalNativeLibrary(String str);

    ParsingPackage setAllowAudioPlaybackCapture(boolean z);

    ParsingPackage setAllowBackup(boolean z);

    ParsingPackage setAllowClearUserData(boolean z);

    ParsingPackage setAllowClearUserDataOnFailedRestore(boolean z);

    ParsingPackage setAllowNativeHeapPointerTagging(boolean z);

    ParsingPackage setAllowTaskReparenting(boolean z);

    ParsingPackage setAnyDensity(int i);

    ParsingPackage setAppComponentFactory(String str);

    ParsingPackage setAttributionsAreUserVisible(boolean z);

    ParsingPackage setAutoRevokePermissions(int i);

    ParsingPackage setBackupAgentName(String str);

    ParsingPackage setBackupInForeground(boolean z);

    ParsingPackage setBanner(int i);

    ParsingPackage setBaseHardwareAccelerated(boolean z);

    ParsingPackage setBaseRevisionCode(int i);

    ParsingPackage setCantSaveState(boolean z);

    ParsingPackage setCategory(int i);

    ParsingPackage setClassLoaderName(String str);

    ParsingPackage setClassName(String str);

    ParsingPackage setCompatibleWidthLimitDp(int i);

    ParsingPackage setCompileSdkVersion(int i);

    ParsingPackage setCompileSdkVersionCodename(String str);

    ParsingPackage setCrossProfile(boolean z);

    ParsingPackage setDataExtractionRules(int i);

    ParsingPackage setDebuggable(boolean z);

    ParsingPackage setDefaultToDeviceProtectedStorage(boolean z);

    ParsingPackage setDescriptionRes(int i);

    ParsingPackage setDirectBootAware(boolean z);

    ParsingPackage setEnabled(boolean z);

    ParsingPackage setExternalStorage(boolean z);

    ParsingPackage setExtractNativeLibs(boolean z);

    ParsingPackage setForceQueryable(boolean z);

    ParsingPackage setFullBackupContent(int i);

    ParsingPackage setFullBackupOnly(boolean z);

    ParsingPackage setGame(boolean z);

    ParsingPackage setGwpAsanMode(int i);

    ParsingPackage setHasCode(boolean z);

    ParsingPackage setHasDomainUrls(boolean z);

    ParsingPackage setHasFragileUserData(boolean z);

    ParsingPackage setIconRes(int i);

    ParsingPackage setInstallLocation(int i);

    ParsingPackage setIsolatedSplitLoading(boolean z);

    ParsingPackage setKillAfterRestore(boolean z);

    ParsingPackage setLabelRes(int i);

    ParsingPackage setLargeHeap(boolean z);

    ParsingPackage setLargestWidthLimitDp(int i);

    ParsingPackage setLogo(int i);

    ParsingPackage setManageSpaceActivityName(String str);

    ParsingPackage setMaxAspectRatio(float f);

    ParsingPackage setMemtagMode(int i);

    ParsingPackage setMetaData(Bundle bundle);

    ParsingPackage setMinAspectRatio(float f);

    ParsingPackage setMinExtensionVersions(SparseIntArray sparseIntArray);

    ParsingPackage setMinSdkVersion(int i);

    ParsingPackage setMultiArch(boolean z);

    ParsingPackage setNativeHeapZeroInitialized(int i);

    ParsingPackage setNetworkSecurityConfigRes(int i);

    ParsingPackage setNonLocalizedLabel(CharSequence charSequence);

    ParsingPackage setOverlay(boolean z);

    ParsingPackage setOverlayCategory(String str);

    ParsingPackage setOverlayIsStatic(boolean z);

    ParsingPackage setOverlayPriority(int i);

    ParsingPackage setOverlayTarget(String str);

    ParsingPackage setOverlayTargetName(String str);

    ParsingPackage setPartiallyDirectBootAware(boolean z);

    ParsingPackage setPermission(String str);

    ParsingPackage setPersistent(boolean z);

    ParsingPackage setPreserveLegacyExternalStorage(boolean z);

    ParsingPackage setProcessName(String str);

    ParsingPackage setProcesses(Map<String, ParsedProcess> map);

    ParsingPackage setProfileable(boolean z);

    ParsingPackage setProfileableByShell(boolean z);

    ParsingPackage setRealPackage(String str);

    ParsingPackage setRequestForegroundServiceExemption(boolean z);

    ParsingPackage setRequestLegacyExternalStorage(boolean z);

    ParsingPackage setRequestRawExternalStorageAccess(Boolean bool);

    ParsingPackage setRequiredAccountType(String str);

    ParsingPackage setRequiredForAllUsers(boolean z);

    ParsingPackage setRequiresSmallestWidthDp(int i);

    ParsingPackage setResizeable(int i);

    ParsingPackage setResizeableActivity(Boolean bool);

    ParsingPackage setResizeableActivityViaSdkVersion(boolean z);

    ParsingPackage setRestoreAnyVersion(boolean z);

    ParsingPackage setRestrictUpdateHash(byte[] bArr);

    ParsingPackage setRestrictedAccountType(String str);

    ParsingPackage setRoundIconRes(int i);

    ParsingPackage setSharedUserId(String str);

    ParsingPackage setSharedUserLabel(int i);

    ParsingPackage setSigningDetails(PackageParser.SigningDetails signingDetails);

    ParsingPackage setSplitClassLoaderName(int i, String str);

    ParsingPackage setSplitHasCode(int i, boolean z);

    ParsingPackage setStaticSharedLibName(String str);

    ParsingPackage setStaticSharedLibVersion(long j);

    ParsingPackage setStaticSharedLibrary(boolean z);

    ParsingPackage setSupportsExtraLargeScreens(int i);

    ParsingPackage setSupportsLargeScreens(int i);

    ParsingPackage setSupportsNormalScreens(int i);

    ParsingPackage setSupportsRtl(boolean z);

    ParsingPackage setSupportsSmallScreens(int i);

    ParsingPackage setTargetSandboxVersion(int i);

    ParsingPackage setTargetSdkVersion(int i);

    ParsingPackage setTaskAffinity(String str);

    ParsingPackage setTestOnly(boolean z);

    ParsingPackage setTheme(int i);

    ParsingPackage setUiOptions(int i);

    ParsingPackage setUpgradeKeySets(Set<String> set);

    ParsingPackage setUse32BitAbi(boolean z);

    ParsingPackage setUseEmbeddedDex(boolean z);

    ParsingPackage setUsesCleartextTraffic(boolean z);

    ParsingPackage setUsesNonSdkApi(boolean z);

    ParsingPackage setVersionName(String str);

    ParsingPackage setVisibleToInstantApps(boolean z);

    ParsingPackage setVmSafeMode(boolean z);

    ParsingPackage setVolumeUuid(String str);

    ParsingPackage setZygotePreloadName(String str);

    ParsingPackage sortActivities();

    ParsingPackage sortReceivers();

    ParsingPackage sortServices();
}
