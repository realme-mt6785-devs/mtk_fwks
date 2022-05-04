package com.mediatek.server.pm;

import android.app.AppGlobals;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherActivityInfoInternal;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInfoLite;
import android.content.pm.PackageParser;
import android.content.pm.ResolveInfo;
import android.hidl.base.V1_0.DebugInfo;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Slog;
import com.android.internal.util.ArrayUtils;
import com.android.server.pm.AppsFilter;
import com.android.server.pm.PackageManagerException;
import com.android.server.pm.PackageManagerService;
import com.android.server.pm.PackageSetting;
import com.android.server.pm.UserManagerService;
import com.android.server.pm.parsing.PackageParser2;
import com.android.server.pm.parsing.pkg.AndroidPackage;
import com.android.server.pm.parsing.pkg.ParsedPackage;
import com.android.server.utils.WatchedArrayMap;
import com.mediatek.net.connectivity.MtkPacketMessage;
import com.mediatek.powerhalwrapper.PowerHalWrapper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
/* loaded from: classes.dex */
public class PmsExtImpl extends PmsExt {
    private static final String KEY_WORD1 = "benchmark";
    private static final String PRODUCT_RSC_PATH_CAP = "/product";
    private static final File REMOVABLE_SYS_APP_LIST_BAK;
    private static final File REMOVABLE_SYS_APP_LIST_SYSTEM;
    private static final File REMOVABLE_SYS_APP_LIST_VENDOR;
    private static final String SYS_EXT_RSC_PATH_CAP = "/system_ext";
    private static final String SYS_RSC_PATH_CAP = "/system";
    static final String TAG = "PmsExtImpl";
    private static final String VND_RSC_PATH_CAP = "/vendor";
    private static File mAppLib32InstallDir;
    private static boolean sRemovableSysAppEnabled;
    private static HashSet<String> sSkipScanAppSet;
    private static HashSet<String> sUninstallerAppSet;
    private PackageManagerService mPms;
    private UserManagerService mUms;
    private static boolean sLogEnabled = false;
    private static String sSysRscPath = SystemProperties.get("ro.sys.current_rsc_path", "");
    private static String sVndRscPath = SystemProperties.get("ro.vendor.vnd.current_rsc_path", "");
    private static String sProductRscPath = SystemProperties.get("ro.product.current_rsc_path", "");
    private static String sSysExtRscPath = SystemProperties.get("ro.sys_ext.current_rsc_path", "");
    private static HashSet<String> sRemovableSystemAppSet = new HashSet<>();
    private static HashSet<String> sRemovableSystemAppSetBak = new HashSet<>();
    private ApplicationInfo mMediatekApplication = null;
    private PowerHalWrapper mPowerHalWrapper = null;

    static {
        boolean z = false;
        if (SystemProperties.getInt("persist.vendor.pms_removable", 0) == 1) {
            z = true;
        }
        sRemovableSysAppEnabled = z;
        REMOVABLE_SYS_APP_LIST_SYSTEM = Environment.buildPath(Environment.getRootDirectory(), new String[]{"etc", "permissions", "pms_sysapp_removable_system_list.txt"});
        REMOVABLE_SYS_APP_LIST_VENDOR = Environment.buildPath(Environment.getRootDirectory(), new String[]{"etc", "permissions", "pms_sysapp_removable_vendor_list.txt"});
        REMOVABLE_SYS_APP_LIST_BAK = Environment.buildPath(Environment.getDataDirectory(), new String[]{"system", "pms_sysapp_removable_list_bak.txt"});
        sUninstallerAppSet = new HashSet<>();
        sSkipScanAppSet = new HashSet<>();
    }

    public PmsExtImpl() {
        if (SYS_RSC_PATH_CAP.equals(sSysRscPath)) {
            sSysRscPath = "";
        }
        if (VND_RSC_PATH_CAP.equals(sVndRscPath)) {
            sVndRscPath = "";
        }
        if (PRODUCT_RSC_PATH_CAP.equals(sProductRscPath)) {
            sProductRscPath = "";
        }
        if (SYS_EXT_RSC_PATH_CAP.equals(sSysExtRscPath)) {
            sSysExtRscPath = "";
        }
        File dataDir = Environment.getDataDirectory();
        mAppLib32InstallDir = new File(dataDir, "app-lib");
    }

    public void init(PackageManagerService pms, UserManagerService ums) {
        this.mPms = pms;
        this.mUms = ums;
    }

    public void scanDirLI(int partitionType, boolean isScanOverlay, int parseFlags, int scanFlags, long currentTime, PackageParser2 packageParser, ExecutorService executorService) {
        int index;
        if ((8388608 & scanFlags) == 0) {
            boolean isPrivApp = (131072 & scanFlags) != 0;
            switch (partitionType) {
                case MtkPacketMessage.NF_DROP /* 0 */:
                    if (!isScanOverlay) {
                        index = isPrivApp ? 14 : 15;
                        break;
                    } else {
                        index = 8;
                        break;
                    }
                case 1:
                    if (!isScanOverlay) {
                        index = isPrivApp ? 20 : 21;
                        break;
                    } else {
                        index = 11;
                        break;
                    }
                case DebugInfo.Architecture.IS_32BIT /* 2 */:
                case 3:
                default:
                    index = -1;
                    break;
                case 4:
                    if (!isScanOverlay) {
                        index = isPrivApp ? 18 : 19;
                        break;
                    } else {
                        index = 10;
                        break;
                    }
                case 5:
                    if (!isScanOverlay) {
                        index = isPrivApp ? 16 : 17;
                        break;
                    } else {
                        index = 9;
                        break;
                    }
            }
            if (index > 0) {
                scanDirLI(index, parseFlags, scanFlags, 0L, packageParser, executorService);
            }
        }
    }

    public void scanDirLI(int index, int parseFlags, int scanFlags, long currentTime, PackageParser2 packageParser, ExecutorService executorService) {
        File targetFile;
        switch (index) {
            case 1:
                targetFile = new File("/custom/framework");
                break;
            case DebugInfo.Architecture.IS_32BIT /* 2 */:
                targetFile = new File(Environment.getVendorDirectory(), "framework");
                break;
            case 3:
                targetFile = new File(Environment.getVendorDirectory(), "/operator/app");
                break;
            case 4:
                targetFile = new File(Environment.getRootDirectory(), "plugin");
                break;
            case 5:
                targetFile = new File(Environment.getVendorDirectory(), "plugin");
                break;
            case 6:
                targetFile = new File("/custom/app");
                break;
            case 7:
                targetFile = new File("/custom/plugin");
                break;
            case 8:
                if (!sSysRscPath.isEmpty()) {
                    targetFile = new File(sSysRscPath, "overlay");
                    break;
                }
                targetFile = null;
                break;
            case 9:
                if (!sSysExtRscPath.isEmpty()) {
                    targetFile = new File(sSysExtRscPath, "overlay");
                    break;
                }
                targetFile = null;
                break;
            case 10:
                if (!sProductRscPath.isEmpty()) {
                    targetFile = new File(sProductRscPath, "overlay");
                    break;
                }
                targetFile = null;
                break;
            case 11:
                if (!sVndRscPath.isEmpty()) {
                    targetFile = new File(sVndRscPath, "overlay");
                    break;
                }
                targetFile = null;
                break;
            case 12:
                if (!sSysRscPath.isEmpty()) {
                    targetFile = new File(sSysRscPath, "framework");
                    break;
                }
                targetFile = null;
                break;
            case 13:
                if (!sVndRscPath.isEmpty()) {
                    targetFile = new File(sVndRscPath, "framework");
                    break;
                }
                targetFile = null;
                break;
            case 14:
                if (!sSysRscPath.isEmpty()) {
                    targetFile = new File(sSysRscPath, "priv-app");
                    break;
                }
                targetFile = null;
                break;
            case 15:
                if (!sSysRscPath.isEmpty()) {
                    targetFile = new File(sSysRscPath, "app");
                    break;
                }
                targetFile = null;
                break;
            case 16:
                if (!sSysExtRscPath.isEmpty()) {
                    targetFile = new File(sSysExtRscPath, "priv-app");
                    break;
                }
                targetFile = null;
                break;
            case 17:
                if (!sSysExtRscPath.isEmpty()) {
                    targetFile = new File(sSysExtRscPath, "app");
                    break;
                }
                targetFile = null;
                break;
            case 18:
                if (!sProductRscPath.isEmpty()) {
                    targetFile = new File(sProductRscPath, "priv-app");
                    break;
                }
                targetFile = null;
                break;
            case 19:
                if (!sProductRscPath.isEmpty()) {
                    targetFile = new File(sProductRscPath, "app");
                    break;
                }
                targetFile = null;
                break;
            case 20:
                if (!sVndRscPath.isEmpty()) {
                    targetFile = new File(sVndRscPath, "priv-app");
                    break;
                }
                targetFile = null;
                break;
            case 21:
                if (!sVndRscPath.isEmpty()) {
                    targetFile = new File(sVndRscPath, "app");
                    break;
                }
                targetFile = null;
                break;
            case 22:
                if (!sSysRscPath.isEmpty()) {
                    targetFile = new File(sSysRscPath, "plugin");
                    break;
                }
                targetFile = null;
                break;
            case 23:
                if (!sVndRscPath.isEmpty()) {
                    targetFile = new File(sVndRscPath, "plugin");
                    break;
                }
                targetFile = null;
                break;
            default:
                Slog.d(TAG, "Unknown index for ext:" + index);
                targetFile = null;
                break;
        }
        if (targetFile != null) {
            try {
                targetFile = targetFile.getCanonicalFile();
            } catch (IOException e) {
            }
            this.mPms.scanDirTracedLI(targetFile, parseFlags, scanFlags, currentTime, packageParser, executorService);
        }
    }

    public void scanMoreDirLi(int defParseFlags, int defScanFlags, PackageParser2 packageParser, ExecutorService executorService) {
        scanDirLI(22, defParseFlags | 16, defScanFlags | 65536, 0L, packageParser, executorService);
        scanDirLI(4, defParseFlags | 16, defScanFlags | 65536, 0L, packageParser, executorService);
        scanDirLI(23, defParseFlags | 16, defScanFlags | 65536 | 524288, 0L, packageParser, executorService);
        scanDirLI(5, defParseFlags | 16, defScanFlags | 65536 | 524288, 0L, packageParser, executorService);
        scanDirLI(3, defParseFlags | 16, defScanFlags | 65536 | 524288, 0L, packageParser, executorService);
        scanDirLI(1, defParseFlags | 16, defScanFlags | 65536 | 1, 0L, packageParser, executorService);
        carrierExpressInstall(defParseFlags, defScanFlags, 0L, packageParser, executorService);
    }

    public void checkMtkResPkg(AndroidPackage pkg) throws PackageManagerException {
        if (!pkg.getPackageName().equals("com.mediatek")) {
            return;
        }
        if (this.mMediatekApplication == null) {
            this.mMediatekApplication = pkg.toAppInfoWithoutState();
        } else {
            Slog.w(TAG, "Core mediatek package being redefined. Skipping.");
            throw new PackageManagerException(-5, "Core android package being redefined. Skipping.");
        }
    }

    public boolean needSkipScanning(ParsedPackage pkg, PackageSetting updatedPkg, PackageSetting ps) {
        if (sSkipScanAppSet.contains(pkg.getPackageName())) {
            Slog.d(TAG, "Skip scan package:" + pkg.getPackageName());
            return true;
        } else if (this.mPms.isFirstBoot() || !isRemovableSysApp(pkg.getPackageName()) || ps != null || updatedPkg != null) {
            if (ps == null && updatedPkg != null) {
                Slog.d(TAG, "Skip scanning uninstalled package: " + pkg.getPackageName());
                return true;
            } else if (this.mPms.isFirstBoot() || pkg == null || ps == null || !isRemovableSysApp(pkg.getPackageName()) || !pkg.getPath().startsWith("/system/") || !ps.getPathString().startsWith("/data/")) {
                return false;
            } else {
                Slog.d(TAG, "Skip scanning the sys package which uninstalled before, \n but then installed later: " + pkg.getPackageName());
                return true;
            }
        } else if (!this.mPms.isDeviceUpgrading() || sRemovableSystemAppSetBak.contains(pkg.getPackageName())) {
            Slog.d(TAG, "Skip scanning uninstalled sys package " + pkg.getPackageName());
            return true;
        } else {
            Slog.d(TAG, "New added removable sys app by OTA:" + pkg.getPackageName());
            return false;
        }
    }

    public boolean needSkipAppInfo(ApplicationInfo ai) {
        if (!sRemovableSysAppEnabled || ai == null || (ai.flags & 8388608) != 0) {
            return false;
        }
        boolean ret = isRemovableSysApp(ai.packageName);
        return ret;
    }

    public void onPackageAdded(String packageName, PackageSetting pkgSetting, int userId) {
        updateUninstallerAppSetWithPkg(packageName, userId);
    }

    public void initBeforeScan() {
        if (sLogEnabled) {
            Slog.d(TAG, "initBeforeScan start");
        }
        if (sRemovableSysAppEnabled) {
            buildRemovableSystemAppSet();
        }
        buildSkipScanAppSet();
        if (sLogEnabled) {
            Slog.d(TAG, "initBeforeScan end");
        }
    }

    public void initAfterScan(WatchedArrayMap<String, PackageSetting> settingsPackages) {
        if (sRemovableSysAppEnabled) {
            if (sLogEnabled) {
                Slog.d(TAG, "initAfterScan start");
            }
            buildUninstallerAppSet();
            if (this.mPms.isFirstBoot() || this.mPms.isDeviceUpgrading()) {
                if (sRemovableSystemAppSetBak.isEmpty()) {
                    sWriteRemovableSystemAppToFile(sRemovableSystemAppSet, REMOVABLE_SYS_APP_LIST_BAK);
                } else if (onUpgradeRemovableSystemAppList(sRemovableSystemAppSetBak, sRemovableSystemAppSet, settingsPackages)) {
                    sWriteRemovableSystemAppToFile(sRemovableSystemAppSet, REMOVABLE_SYS_APP_LIST_BAK);
                }
            }
            if (sLogEnabled) {
                Slog.d(TAG, "initAfterScan end");
            }
        }
    }

    public int customizeInstallPkgFlags(int installFlags, PackageInfoLite pkgLite, WatchedArrayMap<String, PackageSetting> settingsPackages, UserHandle user) {
        PackageSetting ps = (PackageSetting) settingsPackages.get(pkgLite.packageName);
        if (ps == null || !isRemovableSysApp(pkgLite.packageName)) {
            return installFlags;
        }
        int[] installedUsers = ps.queryInstalledUsers(this.mUms.getUserIds(), true);
        if (sLogEnabled) {
            Slog.d(TAG, "getUser()=" + user + " installedUsers=" + Arrays.toString(installedUsers));
        }
        if ((user != UserHandle.ALL && ArrayUtils.contains(installedUsers, user.getIdentifier())) || installedUsers == null || installedUsers.length == this.mUms.getUserIds().length) {
            return installFlags;
        }
        Slog.d(TAG, "built in app, set replace and allow downgrade");
        int ret = installFlags | 1048576;
        return ret | 2;
    }

    public void updatePackageSettings(int userId, String pkgName, AndroidPackage newPackage, PackageSetting ps, int[] allUsers, String installerPackageName) {
        if (userId == -1 && isRemovableSysApp(pkgName) && newPackage.isSystem()) {
            for (int currentUserId : allUsers) {
                ps.setInstalled(true, currentUserId);
                ps.setEnabled(0, currentUserId, installerPackageName);
            }
        }
    }

    public int customizeDeletePkgFlags(int deleteFlags, String packageName) {
        if (!isRemovableSysApp(packageName)) {
            return deleteFlags;
        }
        int newDelFlags = deleteFlags | 4;
        return newDelFlags;
    }

    public int customizeDeletePkg(int[] users, String packageName, int versionCode, int delFlags, boolean removedBySystem) {
        int returnCode = 1;
        int userFlags = delFlags & (-3);
        for (int userId : users) {
            returnCode = this.mPms.deletePackageX(packageName, versionCode, userId, userFlags, removedBySystem);
            if (returnCode != 1) {
                Slog.w(TAG, "Package delete failed for user " + userId + ", returnCode " + returnCode);
            }
        }
        return returnCode;
    }

    public boolean dumpCmdHandle(String cmd, PrintWriter pw, String[] args, int opti) {
        if ("log".equals(cmd)) {
            configLogTag(pw, args, opti);
            return true;
        } else if ("removable".equals(cmd)) {
            dumpRemovableSysApps(pw, args, opti);
            return true;
        } else {
            boolean ret = PmsExtImpl.super.dumpCmdHandle(cmd, pw, args, opti);
            return ret;
        }
    }

    public ApplicationInfo updateApplicationInfoForRemovable(ApplicationInfo oldAppInfo) {
        if (Binder.getCallingPid() == Process.myPid() || oldAppInfo == null || !isRemovableSysApp(oldAppInfo.packageName)) {
            return oldAppInfo;
        }
        return updateApplicationInfoForRemovable(this.mPms.getNameForUid(Binder.getCallingUid()), oldAppInfo);
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0060, code lost:
        if (com.mediatek.server.pm.PmsExtImpl.sLogEnabled == false) goto L_0x0085;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0062, code lost:
        android.util.Slog.d(com.mediatek.server.pm.PmsExtImpl.TAG, "shared uid=" + r4[1] + " pkg=" + r10);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.content.pm.ApplicationInfo updateApplicationInfoForRemovable(java.lang.String r13, android.content.pm.ApplicationInfo r14) {
        /*
            r12 = this;
            boolean r0 = com.mediatek.server.pm.PmsExtImpl.sRemovableSysAppEnabled
            if (r0 == 0) goto L_0x00bf
            if (r14 != 0) goto L_0x0008
            goto L_0x00bf
        L_0x0008:
            r0 = 0
            r1 = 0
            java.lang.String r2 = r14.packageName
            int r3 = android.os.Binder.getCallingPid()
            int r4 = android.os.Process.myPid()
            if (r3 == r4) goto L_0x00ad
            boolean r3 = r12.isRemovableSysApp(r2)
            if (r3 == 0) goto L_0x00ad
            r3 = r13
            if (r3 == 0) goto L_0x00ad
            java.lang.String r4 = ":"
            java.lang.String[] r4 = r3.split(r4)
            int r5 = r4.length
            java.lang.String r6 = "PmsExtImpl"
            r7 = 0
            r8 = 1
            if (r5 != r8) goto L_0x0033
            r5 = r4[r7]
            boolean r0 = isUninstallerApp(r5)
            goto L_0x0085
        L_0x0033:
            int r5 = r4.length
            if (r5 <= r8) goto L_0x0085
            r5 = r4[r8]
            java.lang.String r9 = "1000"
            boolean r0 = r5.equals(r9)
            if (r0 != 0) goto L_0x0085
            android.content.pm.IPackageManager r5 = android.app.AppGlobals.getPackageManager()     // Catch: RemoteException -> 0x0084
            r9 = r4[r8]     // Catch: RemoteException -> 0x0084
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch: RemoteException -> 0x0084
            int r9 = r9.intValue()     // Catch: RemoteException -> 0x0084
            java.lang.String[] r5 = r5.getPackagesForUid(r9)     // Catch: RemoteException -> 0x0084
            int r9 = r5.length     // Catch: RemoteException -> 0x0084
        L_0x0053:
            if (r7 >= r9) goto L_0x0085
            r10 = r5[r7]     // Catch: RemoteException -> 0x0084
            boolean r11 = isUninstallerApp(r10)     // Catch: RemoteException -> 0x0084
            r0 = r11
            if (r0 == 0) goto L_0x0081
            boolean r7 = com.mediatek.server.pm.PmsExtImpl.sLogEnabled     // Catch: RemoteException -> 0x0084
            if (r7 == 0) goto L_0x0085
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: RemoteException -> 0x0084
            r7.<init>()     // Catch: RemoteException -> 0x0084
            java.lang.String r9 = "shared uid="
            r7.append(r9)     // Catch: RemoteException -> 0x0084
            r8 = r4[r8]     // Catch: RemoteException -> 0x0084
            r7.append(r8)     // Catch: RemoteException -> 0x0084
            java.lang.String r8 = " pkg="
            r7.append(r8)     // Catch: RemoteException -> 0x0084
            r7.append(r10)     // Catch: RemoteException -> 0x0084
            java.lang.String r7 = r7.toString()     // Catch: RemoteException -> 0x0084
            android.util.Slog.d(r6, r7)     // Catch: RemoteException -> 0x0084
            goto L_0x0085
        L_0x0081:
            int r7 = r7 + 1
            goto L_0x0053
        L_0x0084:
            r5 = move-exception
        L_0x0085:
            boolean r5 = com.mediatek.server.pm.PmsExtImpl.sLogEnabled
            if (r5 == 0) goto L_0x00ad
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r7 = "judge for "
            r5.append(r7)
            r5.append(r2)
            java.lang.String r7 = " name="
            r5.append(r7)
            r5.append(r3)
            java.lang.String r7 = " clear ? "
            r5.append(r7)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            android.util.Slog.d(r6, r5)
        L_0x00ad:
            if (r0 == 0) goto L_0x00be
            if (r14 == 0) goto L_0x00be
            android.content.pm.ApplicationInfo r3 = new android.content.pm.ApplicationInfo
            r3.<init>(r14)
            r1 = r3
            int r3 = r1.flags
            r3 = r3 & (-130(0xffffffffffffff7e, float:NaN))
            r1.flags = r3
            return r1
        L_0x00be:
            return r14
        L_0x00bf:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mediatek.server.pm.PmsExtImpl.updateApplicationInfoForRemovable(java.lang.String, android.content.pm.ApplicationInfo):android.content.pm.ApplicationInfo");
    }

    public ActivityInfo updateActivityInfoForRemovable(ActivityInfo info) throws RemoteException {
        if (info != null) {
            info.applicationInfo = updateApplicationInfoForRemovable(AppGlobals.getPackageManager().getNameForUid(Binder.getCallingUid()), info.applicationInfo);
        }
        return info;
    }

    public List<LauncherActivityInfoInternal> updateResolveInfoListForRemovable(List<LauncherActivityInfoInternal> apps) throws RemoteException {
        if (apps != null) {
            for (LauncherActivityInfoInternal info : apps) {
                info.getActivityInfo().applicationInfo = updateApplicationInfoForRemovable(AppGlobals.getPackageManager().getNameForUid(Binder.getCallingUid()), info.getActivityInfo().applicationInfo);
            }
        }
        return apps;
    }

    public PackageInfo updatePackageInfoForRemovable(PackageInfo oldPkgInfo) {
        if (!sRemovableSysAppEnabled || oldPkgInfo == null) {
            return oldPkgInfo;
        }
        oldPkgInfo.applicationInfo = updateApplicationInfoForRemovable(oldPkgInfo.applicationInfo);
        return oldPkgInfo;
    }

    public boolean isRemovableSysApp(String pkgName) {
        if (!sRemovableSysAppEnabled) {
            return false;
        }
        boolean ret = sRemovableSystemAppSet.contains(pkgName);
        return ret;
    }

    public boolean updateNativeLibDir(ApplicationInfo info, String codePath) {
        if (codePath == null || !codePath.contains("vendor/operator/app")) {
            return false;
        }
        String apkName = PackageManagerService.deriveCodePathName(codePath);
        info.nativeLibraryRootDir = new File(mAppLib32InstallDir, apkName).getAbsolutePath();
        info.nativeLibraryRootRequiresIsa = false;
        info.nativeLibraryDir = info.nativeLibraryRootDir;
        return true;
    }

    private void configLogTag(PrintWriter pw, String[] args, int opti) {
        if (opti + 1 >= args.length) {
            pw.println("  Invalid argument!");
            return;
        }
        String tag = args[opti];
        boolean on = "on".equals(args[opti + 1]);
        if ("a".equals(tag)) {
            PackageManagerService.DEBUG_SETTINGS = on;
            PackageManagerService.DEBUG_PREFERRED = on;
            PackageManagerService.DEBUG_UPGRADE = on;
            PackageManagerService.DEBUG_DOMAIN_VERIFICATION = on;
            PackageManagerService.DEBUG_BACKUP = on;
            PackageManagerService.DEBUG_INSTALL = on;
            PackageManagerService.DEBUG_REMOVE = on;
            PackageManagerService.DEBUG_BROADCASTS = on;
            PackageManagerService.DEBUG_PACKAGE_INFO = on;
            PackageManagerService.DEBUG_INTENT_MATCHING = on;
            PackageManagerService.DEBUG_PACKAGE_SCANNING = on;
            PackageManagerService.DEBUG_VERIFY = on;
            PackageManagerService.DEBUG_PERMISSIONS = on;
            PackageManagerService.DEBUG_SHARED_LIBRARIES = on;
            PackageManagerService.DEBUG_DEXOPT = on;
            PackageManagerService.DEBUG_ABI_SELECTION = on;
            PackageManagerService.DEBUG_INSTANT = on;
            PackageManagerService.DEBUG_APP_DATA = on;
            AppsFilter.DEBUG_LOGGING = on;
        } else if ("DEBUG_LOGGING".equals(tag)) {
            AppsFilter.DEBUG_LOGGING = on;
        } else if ("DEBUG_TRACING".equals(tag)) {
            AppsFilter.DEBUG_TRACING = on;
        } else {
            try {
                Class c = Class.forName("com.android.server.pm.PackageManagerService");
                Field tagField = c.getField(tag);
                if (tagField != null) {
                    tagField.setBoolean(null, on);
                }
            } catch (Exception e) {
                pw.println("set Tag fail!");
            }
        }
    }

    private void carrierExpressInstall(int defParseFlags, int defScanFlags, long currentTime, PackageParser2 packageParser, ExecutorService executorService) {
        boolean isCarrierExpressInstallEnabled = "1".equals(SystemProperties.get("ro.vendor.mtk_carrierexpress_inst_sup"));
        if (!isCarrierExpressInstallEnabled) {
            scanDirLI(6, defParseFlags | 16, defScanFlags | 65536, currentTime, packageParser, executorService);
            scanDirLI(7, defParseFlags | 16, defScanFlags | 65536, currentTime, packageParser, executorService);
            return;
        }
        scanOperatorDirLI(defScanFlags);
    }

    private void scanOperatorDirLI(int scanFlags) {
        String opStr = SystemProperties.get("persist.vendor.operator.optr");
        if (opStr == null || opStr.length() <= 0) {
            Slog.d(TAG, "No operater defined.");
            return;
        }
        String opFileName = "usp-apks-path-" + opStr + ".txt";
        File customUniDir = new File("/custom/usp");
        if (customUniDir.exists()) {
            scanCxpApp(customUniDir, opFileName, scanFlags);
            return;
        }
        File systemUniDir = new File("/system/usp");
        if (systemUniDir.exists()) {
            scanCxpApp(systemUniDir, opFileName, scanFlags);
        } else {
            Slog.d(TAG, "No Carrier Express Pack directory.");
        }
    }

    private void scanCxpApp(File uniPath, String opFileName, int scanFlags) {
        String str;
        File file;
        String str2;
        String str3;
        File file2;
        PackageManagerException e;
        PmsExtImpl pmsExtImpl = this;
        File opFilePath = new File(uniPath, opFileName);
        List<String> appPathList = pmsExtImpl.readPathsFromFile(opFilePath);
        int i = 0;
        while (i < appPathList.size()) {
            String path = appPathList.get(i);
            File file3 = new File(path);
            long startScanTime = SystemClock.uptimeMillis();
            Slog.d(TAG, "scan package: " + file3.toString() + " , start at: " + startScanTime + "ms.");
            try {
                PackageManagerService packageManagerService = pmsExtImpl.mPms;
                str = TAG;
                str3 = "ms.";
                str2 = "scan package: ";
                file2 = file3;
                try {
                    packageManagerService.scanPackageTracedLI(file3, 17, scanFlags, 0L, (UserHandle) null);
                    str = str;
                    file = file2;
                } catch (PackageManagerException e2) {
                    e = e2;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to parse ");
                    file = file2;
                    sb.append(file);
                    sb.append(": ");
                    sb.append(e.getMessage());
                    Slog.w(str, sb.toString());
                    long endScanTime = SystemClock.uptimeMillis();
                    Slog.d(str, str2 + file.toString() + " , end at: " + endScanTime + "ms. elapsed time = " + (endScanTime - startScanTime) + str3);
                    i++;
                    pmsExtImpl = this;
                }
            } catch (PackageManagerException e3) {
                e = e3;
                str = TAG;
                str3 = "ms.";
                str2 = "scan package: ";
                file2 = file3;
            }
            long endScanTime2 = SystemClock.uptimeMillis();
            Slog.d(str, str2 + file.toString() + " , end at: " + endScanTime2 + "ms. elapsed time = " + (endScanTime2 - startScanTime) + str3);
            i++;
            pmsExtImpl = this;
        }
    }

    private List<String> readPathsFromFile(File packagePathsFile) {
        int length = (int) packagePathsFile.length();
        byte[] bArr = new byte[length];
        List<String> fileContents = new ArrayList<>();
        try {
            FileInputStream inputStream = new FileInputStream(packagePathsFile);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while (true) {
                String receiveString = bufferedReader.readLine();
                if (receiveString == null) {
                    break;
                }
                fileContents.add(receiveString);
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            Slog.d(TAG, "File not found: " + e.toString());
        } catch (IOException e2) {
            Slog.d(TAG, "Can not read file: " + e2.toString());
        }
        return fileContents;
    }

    private void dumpRemovableSysApps(PrintWriter pw, String[] args, int opti) {
        pw.println(" sRemovableSysAppEnabled: " + sRemovableSysAppEnabled);
        Iterator<String> it = sRemovableSystemAppSet.iterator();
        pw.println(" sRemovableSystemAppSet:");
        while (it.hasNext()) {
            pw.println("  " + it.next());
        }
        Iterator<String> it2 = sUninstallerAppSet.iterator();
        pw.println(" sUninstallerAppSet:");
        while (it2.hasNext()) {
            pw.println("  " + it2.next());
        }
    }

    private void buildRemovableSystemAppSet() {
        if (sRemovableSysAppEnabled) {
            if (sLogEnabled) {
                Slog.d(TAG, "BuildRemovableSystemAppSet start");
            }
            sGetRemovableSystemAppFromFile(sRemovableSystemAppSet, REMOVABLE_SYS_APP_LIST_SYSTEM);
            sGetRemovableSystemAppFromFile(sRemovableSystemAppSet, REMOVABLE_SYS_APP_LIST_VENDOR);
            sGetRemovableSystemAppFromFile(sRemovableSystemAppSetBak, REMOVABLE_SYS_APP_LIST_BAK);
            if (sLogEnabled) {
                Slog.d(TAG, "BuildRemovableSystemAppSet end");
            }
        }
    }

    private void buildUninstallerAppSet() {
        if (sRemovableSysAppEnabled) {
            if (sLogEnabled) {
                Slog.d(TAG, "buildUninstallerAppSet start");
            }
            int[] allUserIds = this.mUms.getUserIds();
            for (int i = 0; i < allUserIds.length; i++) {
                Intent settingIntent = new Intent("android.settings.SETTINGS");
                settingIntent.addCategory("android.intent.category.DEFAULT");
                getAppSetByIntent(sUninstallerAppSet, settingIntent, allUserIds[i]);
                Intent launcherIntent = new Intent("android.intent.action.MAIN");
                launcherIntent.addCategory("android.intent.category.HOME");
                launcherIntent.addCategory("android.intent.category.DEFAULT");
                getAppSetByIntent(sUninstallerAppSet, launcherIntent, allUserIds[i]);
                Intent storeIntent = new Intent("android.intent.action.MAIN");
                storeIntent.addCategory("android.intent.category.APP_MARKET");
                storeIntent.addCategory("android.intent.category.DEFAULT");
                getAppSetByIntent(sUninstallerAppSet, storeIntent, allUserIds[i]);
                Intent installIntent = new Intent("android.intent.action.INSTALL_PACKAGE");
                installIntent.addCategory("android.intent.category.DEFAULT");
                installIntent.setData(Uri.fromParts("package", "foo.bar", null));
                Intent uninstallIntent = new Intent("android.intent.action.UNINSTALL_PACKAGE");
                uninstallIntent.addCategory("android.intent.category.DEFAULT");
                uninstallIntent.setData(Uri.fromParts("package", "foo.bar", null));
                getAppSetByIntent(sUninstallerAppSet, installIntent, allUserIds[i]);
                getAppSetByIntent(sUninstallerAppSet, uninstallIntent, allUserIds[i]);
                if (sLogEnabled) {
                    Slog.d(TAG, "buildUninstallerAppSet end");
                }
            }
        }
    }

    private void updateUninstallerAppSetWithPkg(String pkgName, int userId) {
        if (sRemovableSysAppEnabled && pkgName != null) {
            if (sUninstallerAppSet.contains(pkgName)) {
                Slog.d(TAG, "already in set:" + pkgName);
                return;
            }
            if (sLogEnabled) {
                Slog.d(TAG, "updateUninstallerAppSetWithPkg for:" + pkgName + " with:" + userId);
            }
            Intent launcherIntent = new Intent("android.intent.action.MAIN");
            launcherIntent.addCategory("android.intent.category.HOME");
            launcherIntent.addCategory("android.intent.category.DEFAULT");
            launcherIntent.setPackage(pkgName);
            getAppSetByIntent(sUninstallerAppSet, launcherIntent, userId);
            Intent storeIntent = new Intent("android.intent.action.MAIN");
            storeIntent.addCategory("android.intent.category.APP_MARKET");
            storeIntent.setPackage(pkgName);
            getAppSetByIntent(sUninstallerAppSet, storeIntent, userId);
            if (sLogEnabled) {
                Slog.d(TAG, "updateUninstallerAppSetWithPkg end");
            }
        }
    }

    private static void sGetRemovableSystemAppFromFile(HashSet<String> resultSet, File file) {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            try {
                try {
                    if (file.exists()) {
                        FileReader fr2 = new FileReader(file);
                        BufferedReader br2 = new BufferedReader(fr2);
                        while (true) {
                            String line = br2.readLine();
                            if (line != null) {
                                String line2 = line.trim();
                                if (!TextUtils.isEmpty(line2)) {
                                    if (sLogEnabled) {
                                        Slog.d(TAG, "read line " + line2);
                                    }
                                    resultSet.add(line2);
                                }
                            } else {
                                br2.close();
                                fr2.close();
                                return;
                            }
                        }
                    } else {
                        Slog.d(TAG, "file in " + file + " does not exist!");
                        if (0 != 0) {
                            try {
                                br.close();
                            } catch (IOException io) {
                                Slog.d(TAG, io.getMessage());
                                return;
                            }
                        }
                        if (0 != 0) {
                            fr.close();
                        }
                    }
                } catch (IOException io2) {
                    Slog.d(TAG, io2.getMessage());
                    if (0 != 0) {
                        br.close();
                    }
                    if (0 != 0) {
                        fr.close();
                    }
                }
            } catch (IOException io3) {
                Slog.d(TAG, io3.getMessage());
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    br.close();
                } catch (IOException io4) {
                    Slog.d(TAG, io4.getMessage());
                    throw th;
                }
            }
            if (0 != 0) {
                fr.close();
            }
            throw th;
        }
    }

    private static void sWriteRemovableSystemAppToFile(HashSet<String> resultSet, File file) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            try {
                try {
                    if (file.exists()) {
                        file.delete();
                    }
                    FileWriter fw2 = new FileWriter(file, false);
                    BufferedWriter bw2 = new BufferedWriter(fw2);
                    if (resultSet != null && !resultSet.isEmpty()) {
                        Iterator<String> it = resultSet.iterator();
                        while (it.hasNext()) {
                            String next = it.next();
                            bw2.write(next);
                            bw2.newLine();
                        }
                        bw2.flush();
                        bw2.close();
                        fw2.close();
                        return;
                    }
                    bw2.write("");
                    bw2.flush();
                    try {
                        bw2.close();
                        fw2.close();
                    } catch (IOException io) {
                        Slog.d(TAG, io.getMessage());
                    }
                } catch (IOException io2) {
                    Slog.d(TAG, io2.getMessage());
                }
            } catch (IOException io3) {
                Slog.d(TAG, io3.getMessage());
                if (0 != 0) {
                    bw.close();
                }
                if (0 != 0) {
                    fw.close();
                }
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    bw.close();
                } catch (IOException io4) {
                    Slog.d(TAG, io4.getMessage());
                    throw th;
                }
            }
            if (0 != 0) {
                fw.close();
            }
            throw th;
        }
    }

    private static boolean isUninstallerApp(String pkgName) {
        if (!sRemovableSysAppEnabled) {
            return false;
        }
        boolean ret = sUninstallerAppSet.contains(pkgName);
        return ret;
    }

    private void getAppSetByIntent(HashSet<String> resultSet, Intent targetIntent, int userId) {
        List<ResolveInfo> matches = this.mPms.queryIntentActivitiesInternal(targetIntent, (String) null, 786944, userId);
        int size = matches.size();
        if (sLogEnabled) {
            Slog.d(TAG, "getAppSetByIntent:" + targetIntent + " size=" + size);
        }
        if (size >= 1) {
            for (int i = 0; i < size; i++) {
                resultSet.add(matches.get(i).getComponentInfo().packageName);
            }
        }
    }

    private boolean onUpgradeRemovableSystemAppList(HashSet<String> oldSet, HashSet<String> newSet, WatchedArrayMap<String, PackageSetting> settingsPackages) {
        HashSet<String> added = new HashSet<>();
        HashSet<String> removed = new HashSet<>();
        added.addAll(newSet);
        added.removeAll(oldSet);
        removed.addAll(oldSet);
        removed.removeAll(newSet);
        if (sLogEnabled) {
            Slog.d(TAG, "onUpgradeRemovableSystemAppList: add=" + added.size() + " removed=" + removed.size());
        }
        int[] allUserIds = this.mUms.getUserIds();
        Iterator<String> it = removed.iterator();
        boolean updated = false;
        while (it.hasNext()) {
            String removedPkg = it.next();
            PackageSetting ps = (PackageSetting) settingsPackages.get(removedPkg);
            if (ps != null) {
                int[] uninstalledUsers = ps.queryInstalledUsers(allUserIds, false);
                if (uninstalledUsers.length > 0) {
                    for (int i : uninstalledUsers) {
                        ps.setEnabled(0, i, "android");
                        updated = true;
                    }
                }
            }
        }
        if (updated) {
            this.mPms.scheduleWriteSettingsLocked();
        }
        return removed.size() > 0 || added.size() > 0;
    }

    public void checkBenchmark(PackageParser.Package pkg) {
        boolean isNeedAdd = false;
        String pkgName = pkg.packageName;
        if (pkgName.contains(KEY_WORD1)) {
            Slog.d(TAG, "care package name is " + pkg.packageName);
            isNeedAdd = true;
        }
        if (!isNeedAdd) {
            Iterator it = pkg.requestedPermissions.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String requestedPermission = (String) it.next();
                if (requestedPermission.contains(KEY_WORD1)) {
                    Slog.d(TAG, pkgName + " requestedPermission = " + requestedPermission);
                    isNeedAdd = true;
                    break;
                }
            }
        }
        if (!isNeedAdd) {
            Iterator it2 = pkg.activities.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                PackageParser.Activity activity = (PackageParser.Activity) it2.next();
                String className = activity.className;
                if (className.contains(KEY_WORD1)) {
                    Slog.d(TAG, pkgName + " ActivityClassName = " + className);
                    isNeedAdd = true;
                    break;
                }
            }
        }
        if (!isNeedAdd) {
            Iterator it3 = pkg.receivers.iterator();
            while (it3.hasNext()) {
                PackageParser.Activity receiver = (PackageParser.Activity) it3.next();
                Iterator it4 = receiver.intents.iterator();
                while (it4.hasNext()) {
                    PackageParser.ActivityIntentInfo intent = (PackageParser.ActivityIntentInfo) it4.next();
                    int count = intent.countCategories();
                    int i = 0;
                    while (true) {
                        if (i < count) {
                            String category = intent.getCategory(i);
                            if (category.contains(KEY_WORD1)) {
                                Slog.d(TAG, "care package name is " + pkgName + " category =" + category);
                                isNeedAdd = true;
                                break;
                            }
                            i++;
                        }
                    }
                }
            }
        }
        if (isNeedAdd && this.mPowerHalWrapper != null) {
            Slog.d(TAG, "setSportsApk " + pkgName);
        }
    }

    private void buildSkipScanAppSet() {
        String fileNameStr = SystemProperties.get("ro.vendor.mtk_skip_pkg_file");
        if (sLogEnabled) {
            Slog.d(TAG, "BuildSkipScanAppSet start");
        }
        File targetFile = new File(sSysRscPath, fileNameStr);
        if (targetFile.exists()) {
            sGetRemovableSystemAppFromFile(sSkipScanAppSet, targetFile);
        }
        if (sLogEnabled) {
            Slog.d(TAG, "BuildSkipScanAppSet end");
        }
    }
}
