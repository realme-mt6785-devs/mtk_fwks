package com.oplus.multiapp;

import android.app.AppGlobals;
import android.common.OplusFrameworkFactory;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.os.storage.VolumeInfo;
import android.provider.DeviceConfig;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes4.dex */
public abstract class BaseOplusMultiAppManager {
    public static final String ACTION_MULTI_APP_USER_UNLOCKED = "oplus.intent.action.MULTI_APP_USER_UNLOCKED";
    public static final String EXTERNAL_PATH = "/storage/emulated/";
    public static final String EXTERNAL_PRIMARY_MAIN_PATH = "/storage/emulated/0";
    public static final String EXTERNAL_PRIMARY_MULTIAPP_PATH = "/storage/emulated/999";
    public static final String MEDIA_PROVIDER_PACKAGE_NAME = "com.android.providers.media";
    public static final int MULTI_APP_VERSION = 2;
    private static final String TAG = "OplusMultiAppManager";
    public static final int USER_FLAG_MULTI_APP = 67108864;
    public static final int USER_ID_MULTI_APP = 999;
    public static final int USER_ID_ORIGINAL = 0;
    IOplusMultiApp oplusMultiApp = OplusFrameworkFactory.getInstance().getOplusMultiApp();
    public static final boolean DEBUG_ALL = SystemProperties.getBoolean("persist.sys.assert.panic", false);
    private static Map<Integer, StorageVolume> sStorageVolumes = new ConcurrentHashMap();

    public static File getMultiAppVolumePath(String volumeName) throws FileNotFoundException {
        if ("external_primary".equals(volumeName)) {
            StorageManager sm = (StorageManager) AppGlobals.getInitialApplication().getSystemService(StorageManager.class);
            for (VolumeInfo volume : sm.getVolumes()) {
                File path = volume.getPathForUser(999);
                if (path != null && path.toString().startsWith(EXTERNAL_PRIMARY_MULTIAPP_PATH)) {
                    return path;
                }
            }
        }
        throw new FileNotFoundException("Failed to find path for " + volumeName);
    }

    public boolean isMultiAppUserId(int userId) {
        return this.oplusMultiApp.isMultiAppUserId(userId);
    }

    public boolean isCrossUserAuthority(String authority, int userId) {
        return this.oplusMultiApp.isCrossUserAuthority(authority, userId);
    }

    public boolean isProfileFilterPackage(String pkgName) {
        return this.oplusMultiApp.isProfileFilterPackage(pkgName);
    }

    public void scanFileIfNeed(int userId, String path) {
        if (!TextUtils.isEmpty(path)) {
            if (999 == userId || userId == 0) {
                this.oplusMultiApp.scanFileIfNeed(userId, path);
            }
        }
    }

    public static String redirectPath(int userId, String path) {
        if (TextUtils.isEmpty(path) || userId != 999) {
            return null;
        }
        if (path.startsWith(EXTERNAL_PRIMARY_MULTIAPP_PATH)) {
            String rPath = path.replace(EXTERNAL_PRIMARY_MULTIAPP_PATH, OplusMultiAppManager.VOLUME_MULTI_APP_PATH);
            return rPath;
        } else if (!path.startsWith(OplusMultiAppManager.VOLUME_MAIN_PATH)) {
            return null;
        } else {
            String rPath2 = path.replace(OplusMultiAppManager.VOLUME_MAIN_PATH, EXTERNAL_PRIMARY_MAIN_PATH);
            return rPath2;
        }
    }

    public static String getSharedParalledPathIfNeeded(String action, String path) {
        int userId = UserHandle.myUserId();
        return getSharedParalledPathIfNeeded(action, path, userId);
    }

    private static String getSharedParalledPathIfNeeded(String action, String path, int userId) {
        boolean isPathUpdated = false;
        if (path == null || !path.startsWith(EXTERNAL_PATH)) {
            if (path != null && path.startsWith(OplusMultiAppManager.VOLUME_MULTI_APP_PATH) && userId == 999) {
                isPathUpdated = true;
                path = path.replace(OplusMultiAppManager.VOLUME_MULTI_APP_PATH, EXTERNAL_PRIMARY_MULTIAPP_PATH);
            }
        } else if (path.startsWith(EXTERNAL_PRIMARY_MAIN_PATH) && userId == 999) {
            path = path.replace(EXTERNAL_PRIMARY_MAIN_PATH, OplusMultiAppManager.VOLUME_MAIN_PATH);
            isPathUpdated = true;
        } else if (path.startsWith(EXTERNAL_PRIMARY_MULTIAPP_PATH) && userId == 0) {
            path = path.replace(EXTERNAL_PRIMARY_MULTIAPP_PATH, OplusMultiAppManager.VOLUME_MULTI_APP_PATH);
            isPathUpdated = true;
        }
        if (isPathUpdated && DEBUG_ALL) {
            Log.d(TAG, "getSharedParalledPathIfNeeded: path updated [" + action + "] - " + path + " user = " + userId, new Throwable());
        }
        return path;
    }

    public static boolean shouldRedirectToMainUser(Uri uri, Context context) {
        if (uri == null || context == null) {
            return false;
        }
        int contextUserId = context.getUserId();
        String scheme = uri.getScheme();
        String path = uri.getPath();
        String authority = ContentProvider.getAuthorityWithoutUserId(uri.getAuthority());
        if (999 == contextUserId && "content".equals(scheme) && DeviceConfig.NAMESPACE_MEDIA.equals(authority)) {
            if (path.startsWith(File.separator + "internal")) {
                return true;
            }
        }
        return false;
    }

    public static StorageVolume getCoressUserStorageVolume(int userId) {
        int crosserUserId;
        if (sStorageVolumes.containsKey(Integer.valueOf(userId))) {
            return sStorageVolumes.get(Integer.valueOf(userId));
        }
        if (userId == 999) {
            crosserUserId = 0;
        } else if (userId != 0) {
            return null;
        } else {
            crosserUserId = 999;
        }
        String id = "ace-" + crosserUserId;
        new File("/storage/" + id);
        new UserHandle(userId);
        Resources.getSystem().getString(17039374);
        return null;
    }

    public static Uri changeCrossUserVolume(Uri uri, ContentValues values) {
        int userId = UserHandle.myUserId();
        if (uri == null || userId != 999) {
            return uri;
        }
        String authWithoutUserId = ContentProvider.getAuthorityWithoutUserId(uri.getAuthority());
        List<String> pathSegments = uri.getPathSegments();
        if (!DeviceConfig.NAMESPACE_MEDIA.equals(authWithoutUserId) || pathSegments == null || pathSegments.isEmpty()) {
            return uri;
        }
        String volumeName = pathSegments.get(0);
        if (!"external".equals(volumeName) && !"external_primary".equals(volumeName)) {
            return uri;
        }
        if (values != null && values.containsKey("_data")) {
            String dataPath = values.getAsString("_data");
            if (dataPath == null || !dataPath.startsWith(OplusMultiAppManager.VOLUME_MAIN_PATH)) {
                values.put("_data", getSharedParalledPathIfNeeded("insert", values.getAsString("_data"), 0));
            } else {
                values.put("_data", dataPath.replace(OplusMultiAppManager.VOLUME_MAIN_PATH, EXTERNAL_PRIMARY_MAIN_PATH));
                return uri;
            }
        }
        return Uri.parse(uri.toString().replace(volumeName, OplusMultiAppManager.VOLUME_MULTI_APP));
    }
}
