package android.os;

import android.Manifest;
import android.annotation.SystemApi;
import android.app.AppGlobals;
import android.app.AppOpsManager;
import android.compat.Compatibility;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes2.dex */
public class Environment extends OplusBaseEnvironment {
    private static final long DEFAULT_SCOPED_STORAGE = 149924527;
    @Deprecated
    public static final String DIRECTORY_ANDROID = "Android";
    public static final String DIR_ANDROID = "Android";
    private static final File DIR_ANDROID_DATA;
    private static final String DIR_ANDROID_DATA_PATH;
    private static final String DIR_CACHE = "cache";
    private static final String DIR_DATA = "data";
    private static final String DIR_FILES = "files";
    private static final String DIR_MEDIA = "media";
    private static final String DIR_OBB = "obb";
    public static final String DIR_USER_CE = "user";
    public static final String DIR_USER_DE = "user_de";
    private static final String ENV_ANDROID_DATA = "ANDROID_DATA";
    private static final String ENV_EXTERNAL_STORAGE = "EXTERNAL_STORAGE";
    private static final long FORCE_ENABLE_SCOPED_STORAGE = 132649864;
    public static final int HAS_ALARMS = 8;
    public static final int HAS_ANDROID = 65536;
    public static final int HAS_AUDIOBOOKS = 1024;
    public static final int HAS_DCIM = 256;
    public static final int HAS_DOCUMENTS = 512;
    public static final int HAS_DOWNLOADS = 128;
    public static final int HAS_MOVIES = 64;
    public static final int HAS_MUSIC = 1;
    public static final int HAS_NOTIFICATIONS = 16;
    public static final int HAS_OTHER = 131072;
    public static final int HAS_PICTURES = 32;
    public static final int HAS_PODCASTS = 2;
    public static final int HAS_RECORDINGS = 2048;
    public static final int HAS_RINGTONES = 4;
    public static final String MEDIA_BAD_REMOVAL = "bad_removal";
    public static final String MEDIA_CHECKING = "checking";
    public static final String MEDIA_EJECTING = "ejecting";
    public static final String MEDIA_MOUNTED = "mounted";
    public static final String MEDIA_MOUNTED_READ_ONLY = "mounted_ro";
    public static final String MEDIA_NOFS = "nofs";
    public static final String MEDIA_REMOVED = "removed";
    public static final String MEDIA_SHARED = "shared";
    public static final String MEDIA_UNKNOWN = "unknown";
    public static final String MEDIA_UNMOUNTABLE = "unmountable";
    public static final String MEDIA_UNMOUNTED = "unmounted";
    private static final String TAG = "Environment";
    private static UserEnvironment sCurrentUser;
    private static boolean sUserRequired;
    private static final String ENV_ANDROID_ROOT = "ANDROID_ROOT";
    private static final File DIR_ANDROID_ROOT = getDirectory(ENV_ANDROID_ROOT, "/system");
    private static final String ENV_ANDROID_EXPAND = "ANDROID_EXPAND";
    private static final File DIR_ANDROID_EXPAND = getDirectory(ENV_ANDROID_EXPAND, "/mnt/expand");
    private static final String ENV_ANDROID_STORAGE = "ANDROID_STORAGE";
    private static final File DIR_ANDROID_STORAGE = getDirectory(ENV_ANDROID_STORAGE, "/storage");
    private static final String ENV_DOWNLOAD_CACHE = "DOWNLOAD_CACHE";
    private static final File DIR_DOWNLOAD_CACHE = getDirectory(ENV_DOWNLOAD_CACHE, "/cache");
    private static final String ENV_OEM_ROOT = "OEM_ROOT";
    private static final File DIR_OEM_ROOT = getDirectory(ENV_OEM_ROOT, "/oem");
    private static final String ENV_ODM_ROOT = "ODM_ROOT";
    private static final File DIR_ODM_ROOT = getDirectory(ENV_ODM_ROOT, "/odm");
    private static final String ENV_VENDOR_ROOT = "VENDOR_ROOT";
    private static final File DIR_VENDOR_ROOT = getDirectory(ENV_VENDOR_ROOT, "/vendor");
    private static final String ENV_PRODUCT_ROOT = "PRODUCT_ROOT";
    private static final File DIR_PRODUCT_ROOT = getDirectory(ENV_PRODUCT_ROOT, "/product");
    private static final String ENV_SYSTEM_EXT_ROOT = "SYSTEM_EXT_ROOT";
    private static final File DIR_SYSTEM_EXT_ROOT = getDirectory(ENV_SYSTEM_EXT_ROOT, "/system_ext");
    private static final String ENV_APEX_ROOT = "APEX_ROOT";
    private static final File DIR_APEX_ROOT = getDirectory(ENV_APEX_ROOT, "/apex");
    public static String DIRECTORY_MUSIC = "Music";
    public static String DIRECTORY_PODCASTS = "Podcasts";
    public static String DIRECTORY_RINGTONES = "Ringtones";
    public static String DIRECTORY_ALARMS = "Alarms";
    public static String DIRECTORY_NOTIFICATIONS = "Notifications";
    public static String DIRECTORY_PICTURES = "Pictures";
    public static String DIRECTORY_MOVIES = "Movies";
    public static String DIRECTORY_DOWNLOADS = "Download";
    public static String DIRECTORY_DCIM = "DCIM";
    public static String DIRECTORY_DOCUMENTS = "Documents";
    public static String DIRECTORY_SCREENSHOTS = "Screenshots";
    public static String DIRECTORY_AUDIOBOOKS = "Audiobooks";
    public static String DIRECTORY_RECORDINGS = "Recordings";
    public static final String[] STANDARD_DIRECTORIES = {"Music", "Podcasts", "Ringtones", "Alarms", "Notifications", "Pictures", "Movies", "Download", "DCIM", "Documents", "Audiobooks", "Recordings"};

    static {
        String directoryPath = getDirectoryPath(ENV_ANDROID_DATA, "/data");
        DIR_ANDROID_DATA_PATH = directoryPath;
        DIR_ANDROID_DATA = new File(directoryPath);
        initForCurrentUser();
    }

    public static void initForCurrentUser() {
        int userId = UserHandle.myUserId();
        sCurrentUser = new UserEnvironment(userId);
    }

    /* loaded from: classes2.dex */
    public static class UserEnvironment {
        private final int mUserId;

        public UserEnvironment(int userId) {
            this.mUserId = userId;
        }

        public File[] getExternalDirs() {
            StorageVolume[] volumes = StorageManager.getVolumeList(this.mUserId, 256);
            File[] files = new File[volumes.length];
            for (int i = 0; i < volumes.length; i++) {
                files[i] = volumes[i].getPathFile();
            }
            return files;
        }

        @Deprecated
        public File getExternalStorageDirectory() {
            return getExternalDirs()[0];
        }

        @Deprecated
        public File getExternalStoragePublicDirectory(String type) {
            return buildExternalStoragePublicDirs(type)[0];
        }

        public File[] buildExternalStoragePublicDirs(String type) {
            return Environment.buildPaths(getExternalDirs(), type);
        }

        public File[] buildExternalStorageAndroidDataDirs() {
            return Environment.buildPaths(getExternalDirs(), "Android", "data");
        }

        public File[] buildExternalStorageAndroidObbDirs() {
            return Environment.buildPaths(getExternalDirs(), "Android", "obb");
        }

        public File[] buildExternalStorageAppDataDirs(String packageName) {
            return Environment.buildPaths(getExternalDirs(), "Android", "data", packageName);
        }

        public File[] buildExternalStorageAppMediaDirs(String packageName) {
            return Environment.buildPaths(getExternalDirs(), "Android", "media", packageName);
        }

        public File[] buildExternalStorageAppObbDirs(String packageName) {
            return Environment.buildPaths(getExternalDirs(), "Android", "obb", packageName);
        }

        public File[] buildExternalStorageAppFilesDirs(String packageName) {
            return Environment.buildPaths(getExternalDirs(), "Android", "data", packageName, Environment.DIR_FILES);
        }

        public File[] buildExternalStorageAppCacheDirs(String packageName) {
            return Environment.buildPaths(getExternalDirs(), "Android", "data", packageName, Environment.DIR_CACHE);
        }
    }

    public static File getRootDirectory() {
        return DIR_ANDROID_ROOT;
    }

    public static File getStorageDirectory() {
        return DIR_ANDROID_STORAGE;
    }

    @SystemApi
    public static File getOemDirectory() {
        return DIR_OEM_ROOT;
    }

    @SystemApi
    public static File getOdmDirectory() {
        return DIR_ODM_ROOT;
    }

    @SystemApi
    public static File getVendorDirectory() {
        return DIR_VENDOR_ROOT;
    }

    @SystemApi
    public static File getProductDirectory() {
        return DIR_PRODUCT_ROOT;
    }

    @SystemApi
    @Deprecated
    public static File getProductServicesDirectory() {
        return getDirectory("PRODUCT_SERVICES_ROOT", "/product_services");
    }

    @SystemApi
    public static File getSystemExtDirectory() {
        return DIR_SYSTEM_EXT_ROOT;
    }

    public static File getApexDirectory() {
        return DIR_APEX_ROOT;
    }

    @Deprecated
    public static File getUserSystemDirectory(int userId) {
        return new File(new File(getDataSystemDirectory(), "users"), Integer.toString(userId));
    }

    @Deprecated
    public static File getUserConfigDirectory(int userId) {
        return new File(new File(new File(getDataDirectory(), "misc"), "user"), Integer.toString(userId));
    }

    public static File getDataDirectory() {
        return DIR_ANDROID_DATA;
    }

    public static String getDataDirectoryPath() {
        return DIR_ANDROID_DATA_PATH;
    }

    public static File getDataDirectory(String volumeUuid) {
        if (TextUtils.isEmpty(volumeUuid)) {
            return DIR_ANDROID_DATA;
        }
        return new File("/mnt/expand/" + volumeUuid);
    }

    public static String getDataDirectoryPath(String volumeUuid) {
        if (TextUtils.isEmpty(volumeUuid)) {
            return DIR_ANDROID_DATA_PATH;
        }
        return getExpandDirectory().getAbsolutePath() + File.separator + volumeUuid;
    }

    public static File getExpandDirectory() {
        return DIR_ANDROID_EXPAND;
    }

    public static File getDataSystemDirectory() {
        return new File(getDataDirectory(), "system");
    }

    public static File getDataSystemDeDirectory() {
        return buildPath(getDataDirectory(), "system_de");
    }

    public static File getDataSystemCeDirectory() {
        return buildPath(getDataDirectory(), "system_ce");
    }

    public static File getDataSystemCeDirectory(int userId) {
        return buildPath(getDataDirectory(), "system_ce", String.valueOf(userId));
    }

    public static File getDataSystemDeDirectory(int userId) {
        return buildPath(getDataDirectory(), "system_de", String.valueOf(userId));
    }

    public static File getDataMiscDirectory() {
        return new File(getDataDirectory(), "misc");
    }

    public static File getDataMiscCeDirectory() {
        return buildPath(getDataDirectory(), "misc_ce");
    }

    public static File getDataMiscCeDirectory(int userId) {
        return buildPath(getDataDirectory(), "misc_ce", String.valueOf(userId));
    }

    public static File getDataMiscDeDirectory(int userId) {
        return buildPath(getDataDirectory(), "misc_de", String.valueOf(userId));
    }

    private static File getDataProfilesDeDirectory(int userId) {
        return buildPath(getDataDirectory(), "misc", "profiles", "cur", String.valueOf(userId));
    }

    public static File getDataVendorCeDirectory(int userId) {
        return buildPath(getDataDirectory(), "vendor_ce", String.valueOf(userId));
    }

    public static File getDataVendorDeDirectory(int userId) {
        return buildPath(getDataDirectory(), "vendor_de", String.valueOf(userId));
    }

    public static File getDataRefProfilesDePackageDirectory(String packageName) {
        return buildPath(getDataDirectory(), "misc", "profiles", "ref", packageName);
    }

    public static File getDataProfilesDePackageDirectory(int userId, String packageName) {
        return buildPath(getDataProfilesDeDirectory(userId), packageName);
    }

    public static File getDataAppDirectory(String volumeUuid) {
        return new File(getDataDirectory(volumeUuid), "app");
    }

    public static File getDataStagingDirectory(String volumeUuid) {
        return new File(getDataDirectory(volumeUuid), "app-staging");
    }

    public static File getDataUserCeDirectory(String volumeUuid) {
        return new File(getDataDirectory(volumeUuid), "user");
    }

    public static File getDataUserCeDirectory(String volumeUuid, int userId) {
        return new File(getDataUserCeDirectory(volumeUuid), String.valueOf(userId));
    }

    public static File getDataUserCePackageDirectory(String volumeUuid, int userId, String packageName) {
        return new File(getDataUserCeDirectory(volumeUuid, userId), packageName);
    }

    public static File getDataUserDeDirectory(String volumeUuid) {
        return new File(getDataDirectory(volumeUuid), DIR_USER_DE);
    }

    public static File getDataUserDeDirectory(String volumeUuid, int userId) {
        return new File(getDataUserDeDirectory(volumeUuid), String.valueOf(userId));
    }

    public static File getDataUserDePackageDirectory(String volumeUuid, int userId, String packageName) {
        return new File(getDataUserDeDirectory(volumeUuid, userId), packageName);
    }

    public static File getDataPreloadsDirectory() {
        return new File(getDataDirectory(), "preloads");
    }

    public static File getDataPreloadsDemoDirectory() {
        return new File(getDataPreloadsDirectory(), "demo");
    }

    public static File getDataPreloadsAppsDirectory() {
        return new File(getDataPreloadsDirectory(), "apps");
    }

    public static File getDataPreloadsMediaDirectory() {
        return new File(getDataPreloadsDirectory(), "media");
    }

    public static File getDataPreloadsFileCacheDirectory(String packageName) {
        return new File(getDataPreloadsFileCacheDirectory(), packageName);
    }

    public static File getDataPreloadsFileCacheDirectory() {
        return new File(getDataPreloadsDirectory(), "file_cache");
    }

    public static File getPackageCacheDirectory() {
        return new File(getDataSystemDirectory(), "package_cache");
    }

    @SystemApi
    public static Collection<File> getInternalMediaDirectories() {
        ArrayList<File> res = new ArrayList<>();
        addCanonicalFile(res, new File(getRootDirectory(), "media"));
        addCanonicalFile(res, new File(getOemDirectory(), "media"));
        addCanonicalFile(res, new File(getProductDirectory(), "media"));
        EnvironmentExtPlugin.adjustListInGetInternalMediaDirectories.call(res);
        return res;
    }

    private static void addCanonicalFile(List<File> list, File file) {
        try {
            list.add(file.getCanonicalFile());
        } catch (IOException e) {
            Log.w(TAG, "Failed to resolve " + file + ": " + e);
            list.add(file);
        }
    }

    @Deprecated
    public static File getExternalStorageDirectory() {
        throwIfUserRequired();
        File[] files = sCurrentUser.getExternalDirs();
        if (files != null && files.length != 0) {
            return files[0];
        }
        Log.e("environment", "getExternalStorageState files.length == 0 ");
        return null;
    }

    public static File getLegacyExternalStorageDirectory() {
        return new File(System.getenv(ENV_EXTERNAL_STORAGE));
    }

    public static File getLegacyExternalStorageObbDirectory() {
        return buildPath(getLegacyExternalStorageDirectory(), "Android", "obb");
    }

    public static boolean isStandardDirectory(String dir) {
        String[] strArr;
        for (String valid : STANDARD_DIRECTORIES) {
            if (valid.equals(dir)) {
                return true;
            }
        }
        return false;
    }

    public static int classifyExternalStorageDirectory(File dir) {
        File[] listFilesOrEmpty;
        int res = 0;
        for (File f : FileUtils.listFilesOrEmpty(dir)) {
            if (f.isFile() && isInterestingFile(f)) {
                res |= 131072;
            } else if (f.isDirectory() && hasInterestingFiles(f)) {
                String name = f.getName();
                if (DIRECTORY_MUSIC.equals(name)) {
                    res |= 1;
                } else if (DIRECTORY_PODCASTS.equals(name)) {
                    res |= 2;
                } else if (DIRECTORY_RINGTONES.equals(name)) {
                    res |= 4;
                } else if (DIRECTORY_ALARMS.equals(name)) {
                    res |= 8;
                } else if (DIRECTORY_NOTIFICATIONS.equals(name)) {
                    res |= 16;
                } else if (DIRECTORY_PICTURES.equals(name)) {
                    res |= 32;
                } else if (DIRECTORY_MOVIES.equals(name)) {
                    res |= 64;
                } else if (DIRECTORY_DOWNLOADS.equals(name)) {
                    res |= 128;
                } else if (DIRECTORY_DCIM.equals(name)) {
                    res |= 256;
                } else if (DIRECTORY_DOCUMENTS.equals(name)) {
                    res |= 512;
                } else if (DIRECTORY_AUDIOBOOKS.equals(name)) {
                    res |= 1024;
                } else if (DIRECTORY_RECORDINGS.equals(name)) {
                    res |= 2048;
                } else {
                    res = "Android".equals(name) ? res | 65536 : res | 131072;
                }
            }
        }
        return res;
    }

    private static boolean hasInterestingFiles(File dir) {
        File[] listFilesOrEmpty;
        LinkedList<File> explore = new LinkedList<>();
        explore.add(dir);
        while (true) {
            if (explore.isEmpty()) {
                return false;
            }
            File dir2 = explore.pop();
            for (File f : FileUtils.listFilesOrEmpty(dir2)) {
                if (isInterestingFile(f)) {
                    return true;
                }
                if (f.isDirectory()) {
                    explore.add(f);
                }
            }
        }
    }

    private static boolean isInterestingFile(File file) {
        if (!file.isFile()) {
            return false;
        }
        String name = file.getName().toLowerCase();
        return !name.endsWith(".exe") && !name.equals("autorun.inf") && !name.equals("launchpad.zip") && !name.equals(".nomedia");
    }

    @Deprecated
    public static File getExternalStoragePublicDirectory(String type) {
        throwIfUserRequired();
        return sCurrentUser.buildExternalStoragePublicDirs(type)[0];
    }

    public static File[] buildExternalStorageAndroidDataDirs() {
        throwIfUserRequired();
        return sCurrentUser.buildExternalStorageAndroidDataDirs();
    }

    public static File[] buildExternalStorageAndroidObbDirs() {
        throwIfUserRequired();
        return sCurrentUser.buildExternalStorageAndroidObbDirs();
    }

    public static File[] buildExternalStorageAppDataDirs(String packageName) {
        throwIfUserRequired();
        return sCurrentUser.buildExternalStorageAppDataDirs(packageName);
    }

    public static File[] buildExternalStorageAppMediaDirs(String packageName) {
        throwIfUserRequired();
        return sCurrentUser.buildExternalStorageAppMediaDirs(packageName);
    }

    public static File[] buildExternalStorageAppObbDirs(String packageName) {
        throwIfUserRequired();
        return sCurrentUser.buildExternalStorageAppObbDirs(packageName);
    }

    public static File[] buildExternalStorageAppFilesDirs(String packageName) {
        throwIfUserRequired();
        return sCurrentUser.buildExternalStorageAppFilesDirs(packageName);
    }

    public static File[] buildExternalStorageAppCacheDirs(String packageName) {
        throwIfUserRequired();
        return sCurrentUser.buildExternalStorageAppCacheDirs(packageName);
    }

    public static File[] buildExternalStoragePublicDirs(String dirType) {
        throwIfUserRequired();
        return sCurrentUser.buildExternalStoragePublicDirs(dirType);
    }

    public static File getDownloadCacheDirectory() {
        return DIR_DOWNLOAD_CACHE;
    }

    public static String getExternalStorageState() {
        File[] files = sCurrentUser.getExternalDirs();
        if (files == null || files.length == 0) {
            Log.e("environment", "getExternalStorageState files.length == 0 ");
            return "unknown";
        }
        File externalDir = files[0];
        return getExternalStorageState(externalDir);
    }

    @Deprecated
    public static String getStorageState(File path) {
        return getExternalStorageState(path);
    }

    public static String getExternalStorageState(File path) {
        StorageVolume volume = StorageManager.getStorageVolume(path, UserHandle.myUserId());
        if (volume != null) {
            return volume.getState();
        }
        return "unknown";
    }

    public static boolean isExternalStorageRemovable() {
        File externalDir = sCurrentUser.getExternalDirs()[0];
        return isExternalStorageRemovable(externalDir);
    }

    public static boolean isExternalStorageRemovable(File path) {
        StorageVolume volume = StorageManager.getStorageVolume(path, UserHandle.myUserId());
        if (volume != null) {
            return volume.isRemovable();
        }
        throw new IllegalArgumentException("Failed to find storage device at " + path);
    }

    public static boolean isExternalStorageEmulated() {
        File externalDir = sCurrentUser.getExternalDirs()[0];
        return isExternalStorageEmulated(externalDir);
    }

    public static boolean isExternalStorageEmulated(File path) {
        StorageVolume volume = StorageManager.getStorageVolume(path, UserHandle.myUserId());
        if (volume != null) {
            return volume.isEmulated();
        }
        throw new IllegalArgumentException("Failed to find storage device at " + path);
    }

    public static boolean isExternalStorageLegacy() {
        File externalDir = sCurrentUser.getExternalDirs()[0];
        return isExternalStorageLegacy(externalDir);
    }

    public static boolean isExternalStorageLegacy(File path) {
        Context context = AppGlobals.getInitialApplication();
        int uid = context.getApplicationInfo().uid;
        if (Process.isIsolated(uid)) {
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager.isInstantApp()) {
            return false;
        }
        boolean defaultScopedStorage = Compatibility.isChangeEnabled((long) DEFAULT_SCOPED_STORAGE);
        boolean forceEnableScopedStorage = Compatibility.isChangeEnabled((long) FORCE_ENABLE_SCOPED_STORAGE);
        if (isScopedStorageEnforced(defaultScopedStorage, forceEnableScopedStorage)) {
            return false;
        }
        if (isScopedStorageDisabled(defaultScopedStorage, forceEnableScopedStorage)) {
            return true;
        }
        AppOpsManager appOps = (AppOpsManager) context.getSystemService(AppOpsManager.class);
        String opPackageName = context.getOpPackageName();
        return appOps.noteOpNoThrow(87, uid, opPackageName) == 0 || appOps.noteOpNoThrow(99, uid, opPackageName) == 0;
    }

    private static boolean isScopedStorageEnforced(boolean defaultScopedStorage, boolean forceEnableScopedStorage) {
        return defaultScopedStorage && forceEnableScopedStorage;
    }

    private static boolean isScopedStorageDisabled(boolean defaultScopedStorage, boolean forceEnableScopedStorage) {
        return !defaultScopedStorage && !forceEnableScopedStorage;
    }

    public static boolean isExternalStorageManager() {
        File externalDir = sCurrentUser.getExternalDirs()[0];
        return isExternalStorageManager(externalDir);
    }

    public static boolean isExternalStorageManager(File path) {
        Context initialApplication = AppGlobals.getInitialApplication();
        Objects.requireNonNull(initialApplication);
        Context context = initialApplication;
        String packageName = context.getPackageName();
        Objects.requireNonNull(packageName);
        String packageName2 = packageName;
        int uid = context.getApplicationInfo().uid;
        AppOpsManager appOps = (AppOpsManager) context.getSystemService(AppOpsManager.class);
        int opMode = appOps.checkOpNoThrow(92, uid, packageName2);
        switch (opMode) {
            case 0:
                return true;
            case 1:
            case 2:
                return false;
            case 3:
                return context.checkPermission(Manifest.permission.MANAGE_EXTERNAL_STORAGE, Process.myPid(), uid) == 0;
            default:
                throw new IllegalStateException("Unknown AppOpsManager mode " + opMode);
        }
    }

    static File getDirectory(String variableName, String defaultPath) {
        String path = System.getenv(variableName);
        return path == null ? new File(defaultPath) : new File(path);
    }

    static String getDirectoryPath(String variableName, String defaultPath) {
        String path = System.getenv(variableName);
        return path == null ? defaultPath : path;
    }

    public static void setUserRequired(boolean userRequired) {
        sUserRequired = userRequired;
    }

    private static void throwIfUserRequired() {
        if (sUserRequired) {
            Log.e(TAG, "Path requests must specify a user by using UserEnvironment", new Throwable());
        }
    }

    public static File[] buildPaths(File[] base, String... segments) {
        File[] result = new File[base.length];
        for (int i = 0; i < base.length; i++) {
            result[i] = buildPath(base[i], segments);
        }
        return result;
    }

    public static File buildPath(File base, String... segments) {
        File cur = base;
        for (String segment : segments) {
            if (cur == null) {
                cur = new File(segment);
            } else {
                cur = new File(cur, segment);
            }
        }
        return cur;
    }

    @Deprecated
    public static File maybeTranslateEmulatedPathToInternal(File path) {
        if (EnvironmentExtPlugin.interceptMaybeTranslateEmulatedPathToInternal.call(new Object[0]).booleanValue()) {
            return path;
        }
        return StorageManager.maybeTranslateEmulatedPathToInternal(path);
    }
}
