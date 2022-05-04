package com.oplus.phoenix;

import android.graphics.FontListParser;
import android.os.ProjectManager;
import android.os.SystemProperties;
import android.provider.DeviceConfig;
import android.text.format.DateFormat;
import android.util.Log;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/* loaded from: classes4.dex */
public class Phoenix {
    public static final String ANDROID_AMS_ENABLE_SCREEN = "ANDROID_AMS_ENABLE_SCREEN";
    public static final String ANDROID_AMS_READY = "ANDROID_AMS_READY";
    public static final String ANDROID_BOOT_COMPLETED = "ANDROID_BOOT_COMPLETED";
    public static final String ANDROID_PMS_DEXOPT_END = "ANDROID_PMS_DEXOPT_END";
    public static final String ANDROID_PMS_DEXOPT_PERSISTPKGS_END = "ANDROID_PMS_DEXOPT_PERSISTPKGS_END";
    public static final String ANDROID_PMS_DEXOPT_PERSISTPKGS_START = "ANDROID_PMS_DEXOPT_PERSISTPKGS_START";
    public static final String ANDROID_PMS_DEXOPT_START = "ANDROID_PMS_DEXOPT_START";
    public static final String ANDROID_PMS_INIT_START = "ANDROID_PMS_INIT_START";
    public static final String ANDROID_PMS_READY = "ANDROID_PMS_READY";
    public static final String ANDROID_PMS_SCAN_END = "ANDROID_PMS_SCAN_END";
    public static final String ANDROID_PMS_SCAN_START = "ANDROID_PMS_SCAN_START";
    public static final String ANDROID_SYSTEMSERVER_INIT_START = "ANDROID_SYSTEMSERVER_INIT_START";
    public static final String ANDROID_SYSTEMSERVER_READY = "ANDROID_SYSTEMSERVER_READY";
    public static final String ANDROID_ZYGOTE_GC_INIT_END = "ANDROID_ZYGOTE_GC_INIT_END";
    public static final String ANDROID_ZYGOTE_GC_INIT_START = "ANDROID_ZYGOTE_GC_INIT_START";
    public static final String ANDROID_ZYGOTE_INIT_END = "ANDROID_ZYGOTE_INIT_END";
    public static final String ANDROID_ZYGOTE_INIT_START = "ANDROID_ZYGOTE_INIT_START";
    public static final String ANDROID_ZYGOTE_PRELOAD_END = "ANDROID_ZYGOTE_PRELOAD_END";
    public static final String ANDROID_ZYGOTE_PRELOAD_START = "ANDROID_ZYGOTE_PRELOAD_START";
    private static final String BOOT_COMPLETED = "1";
    public static final String ERROR_SYSTEM_SERVER_WATCHDOG = "ERROR_SYSTEM_SERVER_WATCHDOG";
    private static final String PHOENIX_TAG = "[PHOENIX]";
    private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static boolean isSwtHappened = false;

    public static native void native_set_booterror(String str);

    public static native void native_set_bootstage(String str);

    static {
        System.loadLibrary("phoenix_jni");
    }

    private static String getFormatLocaltime(String format) {
        return DateFormat.format(format, System.currentTimeMillis()).toString();
    }

    public static boolean isBootCompleted() {
        return "1".equals(SystemProperties.get("sys.oplus.boot_completed"));
    }

    public static void setBooterror(String booterror) {
        Log.i(PHOENIX_TAG, " errno: " + booterror + ", time: " + getFormatLocaltime(TIME_FORMAT));
        native_set_booterror(booterror);
    }

    public static void setBootstage(String bootstage) {
        if (!isBootCompleted()) {
            Log.i(PHOENIX_TAG, " stage: " + bootstage);
            native_set_bootstage(bootstage);
        }
    }

    private static String writeBootFromProc(String fileName, String context) {
        FileWriter fileWriter = null;
        File f = new File(fileName);
        try {
            try {
                try {
                    fileWriter = new FileWriter(f);
                    fileWriter.write(context);
                    fileWriter.flush();
                    fileWriter.close();
                    fileWriter.close();
                } catch (IOException e) {
                    Log.e(PHOENIX_TAG, "writeBootFromProc write failed!!!", e);
                    if (fileWriter != null) {
                        fileWriter.close();
                    }
                }
            } catch (Throwable th) {
                if (fileWriter != null) {
                    try {
                        fileWriter.close();
                    } catch (IOException e1) {
                        Log.e(PHOENIX_TAG, "writeBootFromProc close the writer failed!!!", e1);
                    }
                }
                throw th;
            }
        } catch (IOException e12) {
            Log.e(PHOENIX_TAG, "writeBootFromProc close the writer failed!!!", e12);
        }
        return null;
    }

    public static void updateProcOpbootfrom(boolean isBootFromOTA, boolean isUpgrade, boolean isFirstBoot) {
        try {
            if (isBootFromOTA) {
                ProjectManager.setOpBoot(DeviceConfig.NAMESPACE_OTA);
            } else if (isUpgrade) {
                ProjectManager.setOpBoot("upgrade");
            } else if (isFirstBoot) {
                ProjectManager.setOpBoot("first_boot");
            } else {
                ProjectManager.setOpBoot(FontListParser.STYLE_NORMAL);
            }
        } catch (Exception e) {
            Log.e(PHOENIX_TAG, "Unable to write file" + e.toString());
        }
    }
}
