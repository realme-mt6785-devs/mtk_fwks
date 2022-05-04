package com.android.server;

import android.content.ComponentName;
import android.content.pm.FeatureInfo;
import android.media.MediaMetrics;
import android.nfc.cardemulation.CardEmulation;
import android.os.Build;
import android.os.CarrierAssociatedAppEntry;
import android.os.Environment;
import android.os.FileUtils;
import android.os.Process;
import android.os.SystemProperties;
import android.permission.PermissionManager;
import android.provider.DeviceConfig;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Slog;
import android.util.SparseArray;
import android.util.TimingsTraceLog;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.XmlUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import libcore.util.EmptyArray;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import system.ext.loader.core.ExtLoader;
/* loaded from: classes4.dex */
public class SystemConfig {
    private static final int ALLOW_ALL = -1;
    private static final int ALLOW_APP_CONFIGS = 8;
    private static final int ALLOW_ASSOCIATIONS = 128;
    private static final int ALLOW_FEATURES = 1;
    private static final int ALLOW_HIDDENAPI_WHITELISTING = 64;
    private static final int ALLOW_IMPLICIT_BROADCASTS = 512;
    private static final int ALLOW_LIBS = 2;
    private static final int ALLOW_OEM_PERMISSIONS = 32;
    private static final int ALLOW_OVERRIDE_APP_RESTRICTIONS = 256;
    private static final int ALLOW_PERMISSIONS = 4;
    private static final int ALLOW_PRIVAPP_PERMISSIONS = 16;
    private static final int ALLOW_VENDOR_APEX = 1024;
    private static final String SKU_PROPERTY = "ro.boot.product.hardware.sku";
    static final String TAG = "SystemConfig";
    private static final String VENDOR_SKU_PROPERTY = "ro.boot.product.vendor.sku";
    static SystemConfig sInstance;
    private String mModulesInstallerPackageName;
    private String mOverlayConfigSignaturePackage;
    int[] mGlobalGids = EmptyArray.INT;
    final SparseArray<ArraySet<String>> mSystemPermissions = new SparseArray<>();
    final ArrayList<PermissionManager.SplitPermissionInfo> mSplitPermissions = new ArrayList<>();
    final ArrayMap<String, SharedLibraryEntry> mSharedLibraries = new ArrayMap<>();
    final ArrayMap<String, FeatureInfo> mAvailableFeatures = new ArrayMap<>();
    final ArraySet<String> mUnavailableFeatures = new ArraySet<>();
    final ArrayMap<String, PermissionEntry> mPermissions = new ArrayMap<>();
    final ArraySet<String> mAllowInPowerSaveExceptIdle = new ArraySet<>();
    final ArraySet<String> mAllowInPowerSave = new ArraySet<>();
    final ArraySet<String> mAllowInDataUsageSave = new ArraySet<>();
    final ArraySet<String> mAllowUnthrottledLocation = new ArraySet<>();
    final ArrayMap<String, ArraySet<String>> mAllowIgnoreLocationSettings = new ArrayMap<>();
    final ArraySet<String> mAllowImplicitBroadcasts = new ArraySet<>();
    final ArraySet<String> mLinkedApps = new ArraySet<>();
    final ArraySet<ComponentName> mDefaultVrComponents = new ArraySet<>();
    final ArraySet<ComponentName> mBackupTransportWhitelist = new ArraySet<>();
    final ArrayMap<String, ArrayMap<String, Boolean>> mPackageComponentEnabledState = new ArrayMap<>();
    final ArraySet<String> mHiddenApiPackageWhitelist = new ArraySet<>();
    final ArraySet<String> mDisabledUntilUsedPreinstalledCarrierApps = new ArraySet<>();
    final ArrayMap<String, List<CarrierAssociatedAppEntry>> mDisabledUntilUsedPreinstalledCarrierAssociatedApps = new ArrayMap<>();
    final ArrayMap<String, ArraySet<String>> mPrivAppPermissions = new ArrayMap<>();
    final ArrayMap<String, ArraySet<String>> mPrivAppDenyPermissions = new ArrayMap<>();
    final ArrayMap<String, ArraySet<String>> mVendorPrivAppPermissions = new ArrayMap<>();
    final ArrayMap<String, ArraySet<String>> mVendorPrivAppDenyPermissions = new ArrayMap<>();
    final ArrayMap<String, ArraySet<String>> mProductPrivAppPermissions = new ArrayMap<>();
    final ArrayMap<String, ArraySet<String>> mProductPrivAppDenyPermissions = new ArrayMap<>();
    final ArrayMap<String, ArraySet<String>> mSystemExtPrivAppPermissions = new ArrayMap<>();
    final ArrayMap<String, ArraySet<String>> mSystemExtPrivAppDenyPermissions = new ArrayMap<>();
    final ArrayMap<String, ArrayMap<String, Boolean>> mOemPermissions = new ArrayMap<>();
    final ArrayMap<String, ArraySet<String>> mAllowedAssociations = new ArrayMap<>();
    private final ArraySet<String> mBugreportWhitelistedPackages = new ArraySet<>();
    private final ArraySet<String> mAppDataIsolationWhitelistedApps = new ArraySet<>();
    private ArrayMap<String, Set<String>> mPackageToUserTypeWhitelist = new ArrayMap<>();
    private ArrayMap<String, Set<String>> mPackageToUserTypeBlacklist = new ArrayMap<>();
    private final ArraySet<String> mRollbackWhitelistedPackages = new ArraySet<>();
    private final ArraySet<String> mWhitelistedStagedInstallers = new ArraySet<>();
    private final ArrayMap<String, String> mAllowedVendorApexes = new ArrayMap<>();
    private Map<String, Map<String, String>> mNamedActors = null;
    ISystemConfigExt mSystemConfigExt = (ISystemConfigExt) ExtLoader.type(ISystemConfigExt.class).base(this).create();

    /* loaded from: classes4.dex */
    public static final class SharedLibraryEntry {
        public final String[] dependencies;
        public final String filename;
        public final boolean isNative;
        public final String name;

        SharedLibraryEntry(String name, String filename, String[] dependencies) {
            this(name, filename, dependencies, false);
        }

        SharedLibraryEntry(String name, String filename, String[] dependencies, boolean isNative) {
            this.name = name;
            this.filename = filename;
            this.dependencies = dependencies;
            this.isNative = isNative;
        }
    }

    /* loaded from: classes4.dex */
    public static final class PermissionEntry {
        public int[] gids;
        public final String name;
        public boolean perUser;

        PermissionEntry(String name, boolean perUser) {
            this.name = name;
            this.perUser = perUser;
        }
    }

    public static SystemConfig getInstance() {
        SystemConfig systemConfig;
        if (!isSystemProcess()) {
            Slog.wtf(TAG, "SystemConfig is being accessed by a process other than system_server.");
        }
        synchronized (SystemConfig.class) {
            if (sInstance == null) {
                sInstance = new SystemConfig();
            }
            systemConfig = sInstance;
        }
        return systemConfig;
    }

    public int[] getGlobalGids() {
        return this.mGlobalGids;
    }

    public SparseArray<ArraySet<String>> getSystemPermissions() {
        return this.mSystemPermissions;
    }

    public ArrayList<PermissionManager.SplitPermissionInfo> getSplitPermissions() {
        return this.mSplitPermissions;
    }

    public ArrayMap<String, SharedLibraryEntry> getSharedLibraries() {
        return this.mSharedLibraries;
    }

    public ArrayMap<String, FeatureInfo> getAvailableFeatures() {
        return this.mAvailableFeatures;
    }

    public ArrayMap<String, PermissionEntry> getPermissions() {
        return this.mPermissions;
    }

    public ArraySet<String> getAllowImplicitBroadcasts() {
        return this.mAllowImplicitBroadcasts;
    }

    public ArraySet<String> getAllowInPowerSaveExceptIdle() {
        return this.mAllowInPowerSaveExceptIdle;
    }

    public ArraySet<String> getAllowInPowerSave() {
        return this.mAllowInPowerSave;
    }

    public ArraySet<String> getAllowInDataUsageSave() {
        return this.mAllowInDataUsageSave;
    }

    public ArraySet<String> getAllowUnthrottledLocation() {
        return this.mAllowUnthrottledLocation;
    }

    public ArrayMap<String, ArraySet<String>> getAllowIgnoreLocationSettings() {
        return this.mAllowIgnoreLocationSettings;
    }

    public ArraySet<String> getLinkedApps() {
        return this.mLinkedApps;
    }

    public ArraySet<String> getHiddenApiWhitelistedApps() {
        return this.mHiddenApiPackageWhitelist;
    }

    public ArraySet<ComponentName> getDefaultVrComponents() {
        return this.mDefaultVrComponents;
    }

    public ArraySet<ComponentName> getBackupTransportWhitelist() {
        return this.mBackupTransportWhitelist;
    }

    public ArrayMap<String, Boolean> getComponentsEnabledStates(String packageName) {
        return this.mPackageComponentEnabledState.get(packageName);
    }

    public ArraySet<String> getDisabledUntilUsedPreinstalledCarrierApps() {
        return this.mDisabledUntilUsedPreinstalledCarrierApps;
    }

    public ArrayMap<String, List<CarrierAssociatedAppEntry>> getDisabledUntilUsedPreinstalledCarrierAssociatedApps() {
        return this.mDisabledUntilUsedPreinstalledCarrierAssociatedApps;
    }

    public ArraySet<String> getPrivAppPermissions(String packageName) {
        return this.mPrivAppPermissions.get(packageName);
    }

    public ArraySet<String> getPrivAppDenyPermissions(String packageName) {
        return this.mPrivAppDenyPermissions.get(packageName);
    }

    public ArraySet<String> getVendorPrivAppPermissions(String packageName) {
        return this.mVendorPrivAppPermissions.get(packageName);
    }

    public ArraySet<String> getVendorPrivAppDenyPermissions(String packageName) {
        return this.mVendorPrivAppDenyPermissions.get(packageName);
    }

    public ArraySet<String> getProductPrivAppPermissions(String packageName) {
        return this.mProductPrivAppPermissions.get(packageName);
    }

    public ArraySet<String> getProductPrivAppDenyPermissions(String packageName) {
        return this.mProductPrivAppDenyPermissions.get(packageName);
    }

    public ArraySet<String> getSystemExtPrivAppPermissions(String packageName) {
        return this.mSystemExtPrivAppPermissions.get(packageName);
    }

    public ArraySet<String> getSystemExtPrivAppDenyPermissions(String packageName) {
        return this.mSystemExtPrivAppDenyPermissions.get(packageName);
    }

    public Map<String, Boolean> getOemPermissions(String packageName) {
        Map<String, Boolean> oemPermissions = this.mOemPermissions.get(packageName);
        if (oemPermissions != null) {
            return oemPermissions;
        }
        return Collections.emptyMap();
    }

    public ArrayMap<String, ArraySet<String>> getAllowedAssociations() {
        return this.mAllowedAssociations;
    }

    public ArraySet<String> getBugreportWhitelistedPackages() {
        return this.mBugreportWhitelistedPackages;
    }

    public Set<String> getRollbackWhitelistedPackages() {
        return this.mRollbackWhitelistedPackages;
    }

    public Set<String> getWhitelistedStagedInstallers() {
        return this.mWhitelistedStagedInstallers;
    }

    public Map<String, String> getAllowedVendorApexes() {
        return this.mAllowedVendorApexes;
    }

    public String getModulesInstallerPackageName() {
        return this.mModulesInstallerPackageName;
    }

    public ArraySet<String> getAppDataIsolationWhitelistedApps() {
        return this.mAppDataIsolationWhitelistedApps;
    }

    public ArrayMap<String, Set<String>> getAndClearPackageToUserTypeWhitelist() {
        ArrayMap<String, Set<String>> r = this.mPackageToUserTypeWhitelist;
        this.mPackageToUserTypeWhitelist = new ArrayMap<>(0);
        return r;
    }

    public ArrayMap<String, Set<String>> getAndClearPackageToUserTypeBlacklist() {
        ArrayMap<String, Set<String>> r = this.mPackageToUserTypeBlacklist;
        this.mPackageToUserTypeBlacklist = new ArrayMap<>(0);
        return r;
    }

    public Map<String, Map<String, String>> getNamedActors() {
        Map<String, Map<String, String>> map = this.mNamedActors;
        return map != null ? map : Collections.emptyMap();
    }

    public String getOverlayConfigSignaturePackage() {
        if (TextUtils.isEmpty(this.mOverlayConfigSignaturePackage)) {
            return null;
        }
        return this.mOverlayConfigSignaturePackage;
    }

    public SystemConfig(boolean readPermissions) {
        if (readPermissions) {
            Slog.w(TAG, "Constructing a test SystemConfig");
            readAllPermissions();
            this.mSystemConfigExt.readConfigInConstructor();
            return;
        }
        Slog.w(TAG, "Constructing an empty test SystemConfig");
    }

    SystemConfig() {
        TimingsTraceLog log = new TimingsTraceLog(TAG, 524288L);
        log.traceBegin("readAllPermissions");
        try {
            readAllPermissions();
            readPublicNativeLibrariesList();
            this.mSystemConfigExt.readConfigInConstructor();
        } finally {
            log.traceEnd();
        }
    }

    private void readAllPermissions() {
        File[] listFilesOrEmpty;
        readPermissions(Environment.buildPath(Environment.getRootDirectory(), "etc", "sysconfig"), -1);
        readPermissions(Environment.buildPath(Environment.getRootDirectory(), "etc", DeviceConfig.NAMESPACE_PERMISSIONS), -1);
        int vendorPermissionFlag = 1171;
        if (Build.VERSION.DEVICE_INITIAL_SDK_INT <= 27) {
            vendorPermissionFlag = 1171 | 12;
        }
        readPermissions(Environment.buildPath(Environment.getVendorDirectory(), "etc", "sysconfig"), vendorPermissionFlag);
        readPermissions(Environment.buildPath(Environment.getVendorDirectory(), "etc", DeviceConfig.NAMESPACE_PERMISSIONS), vendorPermissionFlag);
        String vendorSkuProperty = SystemProperties.get(VENDOR_SKU_PROPERTY, "");
        if (!vendorSkuProperty.isEmpty()) {
            String vendorSkuDir = "sku_" + vendorSkuProperty;
            readPermissions(Environment.buildPath(Environment.getVendorDirectory(), "etc", "sysconfig", vendorSkuDir), vendorPermissionFlag);
            readPermissions(Environment.buildPath(Environment.getVendorDirectory(), "etc", DeviceConfig.NAMESPACE_PERMISSIONS, vendorSkuDir), vendorPermissionFlag);
        }
        readPermissions(Environment.buildPath(Environment.getOdmDirectory(), "etc", "sysconfig"), vendorPermissionFlag);
        readPermissions(Environment.buildPath(Environment.getOdmDirectory(), "etc", DeviceConfig.NAMESPACE_PERMISSIONS), vendorPermissionFlag);
        String skuProperty = SystemProperties.get(SKU_PROPERTY, "");
        if (!skuProperty.isEmpty()) {
            String skuDir = "sku_" + skuProperty;
            readPermissions(Environment.buildPath(Environment.getOdmDirectory(), "etc", "sysconfig", skuDir), vendorPermissionFlag);
            readPermissions(Environment.buildPath(Environment.getOdmDirectory(), "etc", DeviceConfig.NAMESPACE_PERMISSIONS, skuDir), vendorPermissionFlag);
        }
        readPermissions(Environment.buildPath(Environment.getOemDirectory(), "etc", "sysconfig"), 1185);
        readPermissions(Environment.buildPath(Environment.getOemDirectory(), "etc", DeviceConfig.NAMESPACE_PERMISSIONS), 1185);
        int productPermissionFlag = 2015;
        if (Build.VERSION.DEVICE_INITIAL_SDK_INT <= 30) {
            productPermissionFlag = -1;
        }
        readPermissions(Environment.buildPath(Environment.getProductDirectory(), "etc", "sysconfig"), productPermissionFlag);
        readPermissions(Environment.buildPath(Environment.getProductDirectory(), "etc", DeviceConfig.NAMESPACE_PERMISSIONS), productPermissionFlag);
        readPermissions(Environment.buildPath(Environment.getSystemExtDirectory(), "etc", "sysconfig"), -1);
        readPermissions(Environment.buildPath(Environment.getSystemExtDirectory(), "etc", DeviceConfig.NAMESPACE_PERMISSIONS), -1);
        if (isSystemProcess()) {
            for (File f : FileUtils.listFilesOrEmpty(Environment.getApexDirectory())) {
                if (!f.isFile() && !f.getPath().contains("@")) {
                    readPermissions(Environment.buildPath(f, "etc", DeviceConfig.NAMESPACE_PERMISSIONS), 2);
                }
            }
        }
    }

    public void readPermissions(File libraryDir, int permissionFlag) {
        File[] listFiles;
        if (!libraryDir.exists() || !libraryDir.isDirectory()) {
            if (permissionFlag == -1) {
                Slog.w(TAG, "No directory " + libraryDir + ", skipping");
            }
        } else if (!libraryDir.canRead()) {
            Slog.w(TAG, "Directory " + libraryDir + " cannot be read");
        } else {
            File platformFile = null;
            for (File f : libraryDir.listFiles()) {
                if (f.isFile()) {
                    if (f.getPath().endsWith("etc/permissions/platform.xml")) {
                        platformFile = f;
                    } else if (!f.getPath().endsWith(".xml")) {
                        Slog.i(TAG, "Non-xml file " + f + " in " + libraryDir + " directory, ignoring");
                    } else if (!f.canRead()) {
                        Slog.w(TAG, "Permissions library file " + f + " cannot be read");
                    } else if (!this.mSystemConfigExt.filterFileInReadPermissions(f)) {
                        readPermissionsFromXml(f, permissionFlag);
                    }
                }
            }
            if (platformFile != null) {
                readPermissionsFromXml(platformFile, permissionFlag);
            }
        }
    }

    private void logNotAllowedInPartition(String name, File permFile, XmlPullParser parser) {
        Slog.w(TAG, "<" + name + "> not allowed in partition of " + permFile + " at " + parser.getPositionDescription());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x003d, code lost:
        if (r12 != 2) goto L_0x0db5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x004a, code lost:
        if (r10.getName().equals(android.provider.DeviceConfig.NAMESPACE_PERMISSIONS) != false) goto L_0x00aa;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0056, code lost:
        if (r10.getName().equals("config") != false) goto L_0x00aa;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0062, code lost:
        if (r32.mSystemConfigExt.skipTagExceptionAndReturn(r10.getName(), r33) == false) goto L_0x0068;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0064, code lost:
        libcore.io.IoUtils.closeQuietly(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0067, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x008f, code lost:
        throw new org.xmlpull.v1.XmlPullParserException("Unexpected start tag in " + r33 + ": found " + r10.getName() + ", expected 'permissions' or 'config'");
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0090, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0091, code lost:
        r3 = r0;
        r24 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0098, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0099, code lost:
        r3 = r0;
        r25 = r4;
        r24 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00a2, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00a3, code lost:
        r3 = r0;
        r24 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00ab, code lost:
        if (r34 != (-1)) goto L_0x00af;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00ad, code lost:
        r15 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00af, code lost:
        r15 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00b2, code lost:
        if ((r34 & 2) == 0) goto L_0x00b7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00b4, code lost:
        r16 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00b7, code lost:
        r16 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00bb, code lost:
        if ((r34 & 1) == 0) goto L_0x00c0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00bd, code lost:
        r17 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00c0, code lost:
        r17 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00c4, code lost:
        if ((r34 & 4) == 0) goto L_0x00c9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00c6, code lost:
        r18 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00c9, code lost:
        r18 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00cd, code lost:
        if ((r34 & 8) == 0) goto L_0x00d2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00cf, code lost:
        r19 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00d2, code lost:
        r19 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00d6, code lost:
        if ((r34 & 16) == 0) goto L_0x00db;
     */
    /* JADX WARN: Code restructure failed: missing block: B:497:0x0dc4, code lost:
        throw new org.xmlpull.v1.XmlPullParserException("No start tag found");
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00d8, code lost:
        r20 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:507:0x0ddf, code lost:
        android.util.Slog.w(com.android.server.SystemConfig.TAG, r25, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00db, code lost:
        r20 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:510:0x0dea, code lost:
        android.util.Slog.w(com.android.server.SystemConfig.TAG, r4, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:511:0x0dee, code lost:
        libcore.io.IoUtils.closeQuietly(r24);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00df, code lost:
        if ((r34 & 32) == 0) goto L_0x00e4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:538:0x0e5f, code lost:
        libcore.io.IoUtils.closeQuietly(r24);
     */
    /* JADX WARN: Code restructure failed: missing block: B:539:0x0e62, code lost:
        throw r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00e1, code lost:
        r21 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00e4, code lost:
        r21 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00e8, code lost:
        if ((r34 & 64) == 0) goto L_0x00ed;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00ea, code lost:
        r22 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00ed, code lost:
        r22 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00f1, code lost:
        if ((r34 & 128) == 0) goto L_0x00f5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00f3, code lost:
        r11 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00f5, code lost:
        r11 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00f8, code lost:
        if ((r34 & 256) == 0) goto L_0x00fc;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00fa, code lost:
        r13 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00fc, code lost:
        r13 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00ff, code lost:
        if ((r34 & 512) == 0) goto L_0x0103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0101, code lost:
        r8 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0103, code lost:
        r8 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0106, code lost:
        if ((r34 & 1024) == 0) goto L_0x010a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0108, code lost:
        r9 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x010a, code lost:
        r9 = false;
     */
    /* JADX WARN: Not initialized variable reg: 25, insn: 0x0dca: MOVE  (r4 I:??[OBJECT, ARRAY]) = (r25 I:??[OBJECT, ARRAY]), block:B:501:0x0dc9 */
    /* JADX WARN: Removed duplicated region for block: B:296:0x0742 A[Catch: IOException -> 0x0dc5, XmlPullParserException -> 0x0dc8, all -> 0x0e5d, TryCatch #8 {all -> 0x0e5d, blocks: (B:183:0x02cb, B:185:0x02d7, B:187:0x02fb, B:190:0x0323, B:192:0x0329, B:193:0x032c, B:195:0x0334, B:197:0x0341, B:198:0x0364, B:200:0x036b, B:202:0x036f, B:203:0x0372, B:204:0x0379, B:206:0x037b, B:207:0x037e, B:208:0x0384, B:210:0x038a, B:211:0x03ad, B:212:0x03b2, B:214:0x03ba, B:216:0x03c0, B:217:0x03e3, B:219:0x03eb, B:221:0x03f2, B:222:0x0410, B:223:0x0411, B:224:0x0414, B:225:0x041a, B:227:0x0437, B:228:0x045d, B:230:0x0463, B:231:0x0488, B:233:0x048e, B:234:0x04b3, B:236:0x04bb, B:238:0x04bf, B:239:0x04c6, B:241:0x04d0, B:242:0x04dc, B:244:0x04e2, B:245:0x04e5, B:246:0x04eb, B:247:0x051f, B:248:0x0520, B:249:0x0543, B:250:0x0544, B:251:0x054e, B:253:0x0554, B:254:0x0577, B:255:0x057c, B:256:0x0582, B:258:0x0588, B:259:0x05ab, B:260:0x05b0, B:262:0x05b8, B:264:0x05c1, B:265:0x05eb, B:267:0x05f3, B:268:0x061d, B:270:0x0631, B:271:0x063c, B:272:0x065d, B:273:0x0660, B:275:0x0668, B:277:0x066e, B:278:0x0691, B:280:0x0697, B:281:0x069a, B:283:0x06a2, B:284:0x06a8, B:287:0x06b5, B:289:0x06d6, B:294:0x06fb, B:296:0x0742, B:298:0x074c, B:300:0x0756, B:301:0x075e, B:303:0x0768, B:305:0x0773, B:307:0x0779, B:308:0x079c, B:310:0x07a2, B:311:0x07a5, B:313:0x07ad, B:317:0x07bc, B:319:0x07c9, B:323:0x07d1, B:324:0x07fb, B:326:0x0805, B:327:0x0810, B:328:0x0819, B:330:0x083e, B:331:0x0841, B:333:0x0849, B:335:0x0852, B:336:0x0877, B:338:0x087d, B:339:0x08aa, B:341:0x08b0, B:342:0x08b3, B:343:0x08b9, B:345:0x08c1, B:347:0x08cd, B:349:0x08f2, B:350:0x0917, B:352:0x0922, B:353:0x0925, B:355:0x092d, B:357:0x0933, B:358:0x0956, B:360:0x095c, B:361:0x095f, B:363:0x0967, B:365:0x096f, B:366:0x0994, B:368:0x099a, B:369:0x099d, B:371:0x09a5, B:373:0x09b1, B:374:0x09d4, B:376:0x09de, B:379:0x09e6, B:380:0x09f2, B:382:0x09fa, B:385:0x0a03, B:387:0x0a07, B:388:0x0a0a, B:390:0x0a12, B:392:0x0a18, B:393:0x0a3b, B:395:0x0a41, B:396:0x0a44, B:398:0x0a4c, B:400:0x0a52, B:401:0x0a75, B:403:0x0a7b, B:404:0x0a7e, B:406:0x0a86, B:408:0x0a8c, B:409:0x0aaf, B:411:0x0aba, B:412:0x0abd, B:414:0x0ac5, B:416:0x0acb, B:417:0x0aee, B:419:0x0af9, B:420:0x0afc, B:422:0x0b04, B:424:0x0b0a, B:425:0x0b2f, B:427:0x0b3a, B:428:0x0b3d, B:431:0x0b47, B:434:0x0b58, B:436:0x0b6a, B:438:0x0b8f, B:440:0x0b98, B:441:0x0b9c, B:444:0x0ba6, B:446:0x0bb8, B:448:0x0bdd, B:449:0x0c02, B:451:0x0c06, B:452:0x0c0a, B:453:0x0c10, B:455:0x0c19, B:456:0x0c1c, B:459:0x0c24, B:460:0x0c29, B:463:0x0c36, B:465:0x0c3c, B:466:0x0c63, B:468:0x0c6c, B:469:0x0c95, B:471:0x0c9b, B:472:0x0ccc, B:474:0x0cdb, B:475:0x0ce6, B:476:0x0ceb, B:477:0x0cee, B:480:0x0cf8, B:482:0x0cfe, B:483:0x0d25, B:484:0x0d2f, B:487:0x0d39, B:489:0x0d41, B:490:0x0d4e, B:492:0x0d73, B:493:0x0d76, B:494:0x0d7a, B:496:0x0db5, B:497:0x0dc4, B:507:0x0ddf, B:510:0x0dea), top: B:547:0x002a }] */
    /* JADX WARN: Removed duplicated region for block: B:297:0x074a  */
    /* JADX WARN: Removed duplicated region for block: B:514:0x0df8  */
    /* JADX WARN: Removed duplicated region for block: B:515:0x0e04  */
    /* JADX WARN: Removed duplicated region for block: B:518:0x0e0b  */
    /* JADX WARN: Removed duplicated region for block: B:521:0x0e16  */
    /* JADX WARN: Removed duplicated region for block: B:522:0x0e1c  */
    /* JADX WARN: Removed duplicated region for block: B:525:0x0e27  */
    /* JADX WARN: Removed duplicated region for block: B:528:0x0e38  */
    /* JADX WARN: Removed duplicated region for block: B:532:0x0e49  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void readPermissionsFromXml(java.io.File r33, int r34) {
        /*
            Method dump skipped, instructions count: 3898
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.server.SystemConfig.readPermissionsFromXml(java.io.File, int):void");
    }

    private void addFeature(String name, int version) {
        FeatureInfo fi = this.mAvailableFeatures.get(name);
        if (fi == null) {
            FeatureInfo fi2 = new FeatureInfo();
            fi2.name = name;
            fi2.version = version;
            this.mAvailableFeatures.put(name, fi2);
            return;
        }
        fi.version = Math.max(fi.version, version);
    }

    private void removeFeature(String name) {
        if (this.mAvailableFeatures.remove(name) != null) {
            Slog.d(TAG, "Removed unavailable feature " + name);
        }
    }

    void readPermission(XmlPullParser parser, String name) throws IOException, XmlPullParserException {
        if (!this.mPermissions.containsKey(name)) {
            boolean perUser = XmlUtils.readBooleanAttribute(parser, "perUser", false);
            PermissionEntry perm = new PermissionEntry(name, perUser);
            this.mPermissions.put(name, perm);
            int outerDepth = parser.getDepth();
            while (true) {
                int type = parser.next();
                if (type == 1) {
                    return;
                }
                if (type == 3 && parser.getDepth() <= outerDepth) {
                    return;
                }
                if (!(type == 3 || type == 4)) {
                    String tagName = parser.getName();
                    if ("group".equals(tagName)) {
                        String gidStr = parser.getAttributeValue(null, "gid");
                        if (gidStr != null) {
                            int gid = Process.getGidForName(gidStr);
                            perm.gids = ArrayUtils.appendInt(perm.gids, gid);
                        } else {
                            Slog.w(TAG, "<group> without gid at " + parser.getPositionDescription());
                        }
                    }
                    XmlUtils.skipCurrentTag(parser);
                }
            }
        } else {
            throw new IllegalStateException("Duplicate permission definition for " + name);
        }
    }

    private void readPrivAppPermissions(XmlPullParser parser, ArrayMap<String, ArraySet<String>> grantMap, ArrayMap<String, ArraySet<String>> denyMap) throws IOException, XmlPullParserException {
        String packageName = parser.getAttributeValue(null, "package");
        if (TextUtils.isEmpty(packageName)) {
            Slog.w(TAG, "package is required for <privapp-permissions> in " + parser.getPositionDescription());
            return;
        }
        ArraySet<String> permissions = grantMap.get(packageName);
        if (permissions == null) {
            permissions = new ArraySet<>();
        }
        ArraySet<String> denyPermissions = denyMap.get(packageName);
        int depth = parser.getDepth();
        while (XmlUtils.nextElementWithin(parser, depth)) {
            String name = parser.getName();
            if ("permission".equals(name)) {
                String permName = parser.getAttributeValue(null, "name");
                if (TextUtils.isEmpty(permName)) {
                    Slog.w(TAG, "name is required for <permission> in " + parser.getPositionDescription());
                } else {
                    permissions.add(permName);
                }
            } else if ("deny-permission".equals(name)) {
                String permName2 = parser.getAttributeValue(null, "name");
                if (TextUtils.isEmpty(permName2)) {
                    Slog.w(TAG, "name is required for <deny-permission> in " + parser.getPositionDescription());
                } else {
                    if (denyPermissions == null) {
                        denyPermissions = new ArraySet<>();
                    }
                    denyPermissions.add(permName2);
                }
            }
        }
        grantMap.put(packageName, permissions);
        if (denyPermissions != null) {
            denyMap.put(packageName, denyPermissions);
        }
    }

    private void readInstallInUserType(XmlPullParser parser, Map<String, Set<String>> doInstallMap, Map<String, Set<String>> nonInstallMap) throws IOException, XmlPullParserException {
        String packageName = parser.getAttributeValue(null, "package");
        if (TextUtils.isEmpty(packageName)) {
            Slog.w(TAG, "package is required for <install-in-user-type> in " + parser.getPositionDescription());
            return;
        }
        Set<String> userTypesYes = doInstallMap.get(packageName);
        Set<String> userTypesNo = nonInstallMap.get(packageName);
        int depth = parser.getDepth();
        while (XmlUtils.nextElementWithin(parser, depth)) {
            String name = parser.getName();
            if ("install-in".equals(name)) {
                String userType = parser.getAttributeValue(null, "user-type");
                if (TextUtils.isEmpty(userType)) {
                    Slog.w(TAG, "user-type is required for <install-in-user-type> in " + parser.getPositionDescription());
                } else {
                    if (userTypesYes == null) {
                        userTypesYes = new ArraySet();
                        doInstallMap.put(packageName, userTypesYes);
                    }
                    userTypesYes.add(userType);
                }
            } else if ("do-not-install-in".equals(name)) {
                String userType2 = parser.getAttributeValue(null, "user-type");
                if (TextUtils.isEmpty(userType2)) {
                    Slog.w(TAG, "user-type is required for <install-in-user-type> in " + parser.getPositionDescription());
                } else {
                    if (userTypesNo == null) {
                        userTypesNo = new ArraySet();
                        nonInstallMap.put(packageName, userTypesNo);
                    }
                    userTypesNo.add(userType2);
                }
            } else {
                Slog.w(TAG, "unrecognized tag in <install-in-user-type> in " + parser.getPositionDescription());
            }
        }
    }

    void readOemPermissions(XmlPullParser parser) throws IOException, XmlPullParserException {
        String packageName = parser.getAttributeValue(null, "package");
        if (TextUtils.isEmpty(packageName)) {
            Slog.w(TAG, "package is required for <oem-permissions> in " + parser.getPositionDescription());
            return;
        }
        ArrayMap<String, Boolean> permissions = this.mOemPermissions.get(packageName);
        if (permissions == null) {
            permissions = new ArrayMap<>();
        }
        int depth = parser.getDepth();
        while (XmlUtils.nextElementWithin(parser, depth)) {
            String name = parser.getName();
            if ("permission".equals(name)) {
                String permName = parser.getAttributeValue(null, "name");
                if (TextUtils.isEmpty(permName)) {
                    Slog.w(TAG, "name is required for <permission> in " + parser.getPositionDescription());
                } else {
                    permissions.put(permName, Boolean.TRUE);
                }
            } else if ("deny-permission".equals(name)) {
                String permName2 = parser.getAttributeValue(null, "name");
                if (TextUtils.isEmpty(permName2)) {
                    Slog.w(TAG, "name is required for <deny-permission> in " + parser.getPositionDescription());
                } else {
                    permissions.put(permName2, Boolean.FALSE);
                }
            }
        }
        this.mOemPermissions.put(packageName, permissions);
    }

    private void readSplitPermission(XmlPullParser parser, File permFile) throws IOException, XmlPullParserException {
        String splitPerm = parser.getAttributeValue(null, "name");
        if (splitPerm == null) {
            Slog.w(TAG, "<split-permission> without name in " + permFile + " at " + parser.getPositionDescription());
            XmlUtils.skipCurrentTag(parser);
            return;
        }
        String targetSdkStr = parser.getAttributeValue(null, "targetSdk");
        int targetSdk = 10001;
        if (!TextUtils.isEmpty(targetSdkStr)) {
            try {
                targetSdk = Integer.parseInt(targetSdkStr);
            } catch (NumberFormatException e) {
                Slog.w(TAG, "<split-permission> targetSdk not an integer in " + permFile + " at " + parser.getPositionDescription());
                XmlUtils.skipCurrentTag(parser);
                return;
            }
        }
        int depth = parser.getDepth();
        List<String> newPermissions = new ArrayList<>();
        while (XmlUtils.nextElementWithin(parser, depth)) {
            String name = parser.getName();
            if ("new-permission".equals(name)) {
                String newName = parser.getAttributeValue(null, "name");
                if (TextUtils.isEmpty(newName)) {
                    Slog.w(TAG, "name is required for <new-permission> in " + parser.getPositionDescription());
                } else {
                    newPermissions.add(newName);
                }
            } else {
                XmlUtils.skipCurrentTag(parser);
            }
        }
        if (!newPermissions.isEmpty()) {
            this.mSplitPermissions.add(new PermissionManager.SplitPermissionInfo(splitPerm, newPermissions, targetSdk));
        }
    }

    private void readComponentOverrides(XmlPullParser parser, File permFile) throws IOException, XmlPullParserException {
        String pkgname = parser.getAttributeValue(null, "package");
        if (pkgname == null) {
            Slog.w(TAG, "<component-override> without package in " + permFile + " at " + parser.getPositionDescription());
            return;
        }
        String pkgname2 = pkgname.intern();
        int depth = parser.getDepth();
        while (XmlUtils.nextElementWithin(parser, depth)) {
            if (CardEmulation.EXTRA_SERVICE_COMPONENT.equals(parser.getName())) {
                String clsname = parser.getAttributeValue(null, "class");
                String enabled = parser.getAttributeValue(null, "enabled");
                if (clsname == null) {
                    Slog.w(TAG, "<component> without class in " + permFile + " at " + parser.getPositionDescription());
                    return;
                } else if (enabled == null) {
                    Slog.w(TAG, "<component> without enabled in " + permFile + " at " + parser.getPositionDescription());
                    return;
                } else {
                    if (clsname.startsWith(MediaMetrics.SEPARATOR)) {
                        clsname = pkgname2 + clsname;
                    }
                    String clsname2 = clsname.intern();
                    ArrayMap<String, Boolean> componentEnabledStates = this.mPackageComponentEnabledState.get(pkgname2);
                    if (componentEnabledStates == null) {
                        componentEnabledStates = new ArrayMap<>();
                        this.mPackageComponentEnabledState.put(pkgname2, componentEnabledStates);
                    }
                    componentEnabledStates.put(clsname2, Boolean.valueOf(!"false".equals(enabled)));
                }
            }
        }
    }

    private void readPublicNativeLibrariesList() {
        readPublicLibrariesListFile(new File("/vendor/etc/public.libraries.txt"));
        String[] dirs = {"/system/etc", "/system_ext/etc", "/product/etc"};
        for (String dir : dirs) {
            File[] files = new File(dir).listFiles();
            if (files == null) {
                Slog.w(TAG, "Public libraries file folder missing: " + dir);
            } else {
                for (File f : files) {
                    String name = f.getName();
                    if (name.startsWith("public.libraries-") && name.endsWith(".txt")) {
                        readPublicLibrariesListFile(f);
                    }
                }
            }
        }
    }

    private void readPublicLibrariesListFile(File listFile) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(listFile));
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    br.close();
                    return;
                } else if (!line.isEmpty() && !line.startsWith("#")) {
                    String soname = line.trim().split(" ")[0];
                    SharedLibraryEntry entry = new SharedLibraryEntry(soname, soname, new String[0], true);
                    this.mSharedLibraries.put(entry.name, entry);
                }
            }
        } catch (IOException e) {
            Slog.w(TAG, "Failed to read public libraries file " + listFile, e);
        }
    }

    private static boolean isSystemProcess() {
        return Process.myUid() == 1000;
    }
}
