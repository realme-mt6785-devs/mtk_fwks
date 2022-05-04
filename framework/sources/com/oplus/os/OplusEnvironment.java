package com.oplus.os;

import android.content.Context;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import java.io.File;
/* loaded from: classes4.dex */
public class OplusEnvironment {
    public static final File PARENT_STORAGE_DIRECTORY = getDirectory("EXTERNAL_STORAGE", "/storage/sdcard0");
    public static final File SUB_STORAGE_DIRECTORY = getDirectory("INTERNAL_STORAGE", "/storage/sdcard0/external_sd");
    private static final String TAG = "OplusEnvironment";
    private static String externalSdDir;
    private static String internalSdDir;
    private static StorageManager mStorageManager;

    private static void update(Context context) {
        StorageVolume[] volumes;
        StorageManager storageManager = (StorageManager) context.getSystemService("storage");
        mStorageManager = storageManager;
        if (!(storageManager == null || (volumes = storageManager.getVolumeList()) == null)) {
            for (int i = 0; i < volumes.length; i++) {
                if (volumes[i].isRemovable()) {
                    externalSdDir = volumes[i].getPath();
                } else {
                    internalSdDir = volumes[i].getPath();
                }
            }
        }
    }

    public static File getInternalSdDirectory(Context context) {
        update(context);
        if (internalSdDir == null) {
            return null;
        }
        return new File(internalSdDir);
    }

    public static File getExternalSdDirectory(Context context) {
        update(context);
        if (externalSdDir == null) {
            return null;
        }
        return new File(externalSdDir);
    }

    public static String getInternalSdState(Context context) {
        update(context);
        String str = internalSdDir;
        if (str == null) {
            return null;
        }
        return mStorageManager.getVolumeState(str);
    }

    public static String getExternalSdState(Context context) {
        update(context);
        String str = externalSdDir;
        if (str == null) {
            return null;
        }
        return mStorageManager.getVolumeState(str);
    }

    public static boolean isExternalSDRemoved(Context context) {
        update(context);
        String str = externalSdDir;
        if (str == null) {
            return true;
        }
        return Environment.MEDIA_REMOVED.equals(mStorageManager.getVolumeState(str));
    }

    static File getDirectory(String variableName, String defaultPath) {
        String path = System.getenv(variableName);
        return path == null ? new File(defaultPath) : new File(path);
    }
}
