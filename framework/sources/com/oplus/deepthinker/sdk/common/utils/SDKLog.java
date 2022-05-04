package com.oplus.deepthinker.sdk.common.utils;

import android.os.SystemProperties;
import android.provider.SettingsStringUtil;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.IOException;
/* loaded from: classes4.dex */
public class SDKLog {
    private static final boolean DEBUG_DETAILS = false;
    private static final boolean DEBUG_IN_FILE = false;
    public static final boolean IS_QE_LOG_ON;
    private static final boolean IS_QE_LOG_ON_MTK;
    private static final String TAG = "DeepThinkerSDK";
    private static boolean isDebugTagOn;
    private static boolean mDevelopMode;
    private static boolean sSaveLogToFile;
    private static BufferedWriter sWriter;

    static {
        boolean z = false;
        boolean z2 = SystemProperties.getBoolean("persist.sys.assert.panic", false);
        IS_QE_LOG_ON = z2;
        boolean z3 = SystemProperties.getBoolean("persist.sys.assert.enable", false);
        IS_QE_LOG_ON_MTK = z3;
        boolean isLoggable = Log.isLoggable(TAG, 3);
        isDebugTagOn = isLoggable;
        if (z2 || z3 || isLoggable) {
            z = true;
        }
        mDevelopMode = z;
    }

    private SDKLog() {
    }

    public static synchronized void openSaveLogToFile() {
        synchronized (SDKLog.class) {
            sSaveLogToFile = false;
        }
    }

    public static boolean isDevelopModeOn() {
        return mDevelopMode;
    }

    private static void saveLogToFile(String tag, String msg) {
        BufferedWriter bufferedWriter;
        if (sSaveLogToFile && (bufferedWriter = sWriter) != null) {
            try {
                bufferedWriter.write(tag + ": " + msg + "\n");
                sWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void v(String tag, String msg) {
        if (IS_QE_LOG_ON) {
            Log.v(TAG, tag + ": " + msg);
            saveLogToFile(tag, msg);
        }
    }

    public static void v(String msg) {
        if (IS_QE_LOG_ON) {
            Log.v(TAG, msg);
            saveLogToFile(TAG, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (IS_QE_LOG_ON) {
            Log.d(TAG, tag + ": " + msg);
            saveLogToFile(tag, msg);
        }
    }

    public static void d(String msg) {
        if (IS_QE_LOG_ON) {
            Log.d(TAG, msg);
            saveLogToFile(TAG, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (IS_QE_LOG_ON) {
            Log.i(TAG, tag + ": " + msg);
            saveLogToFile(tag, msg);
        }
    }

    public static void i(String msg) {
        if (IS_QE_LOG_ON) {
            Log.i(TAG, msg);
            saveLogToFile(TAG, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (IS_QE_LOG_ON) {
            Log.w(TAG, tag + ": " + msg);
            saveLogToFile(tag, msg);
        }
    }

    public static void w(String msg) {
        if (IS_QE_LOG_ON) {
            Log.w(TAG, msg);
            saveLogToFile(TAG, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (IS_QE_LOG_ON) {
            Log.e(TAG, tag + ": " + msg);
            saveLogToFile(tag, msg);
        }
    }

    public static void e(String msg) {
        if (IS_QE_LOG_ON) {
            Log.e(TAG, msg);
            saveLogToFile(TAG, msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (IS_QE_LOG_ON) {
            Log.e(TAG, tag + SettingsStringUtil.DELIMITER + msg, tr);
            saveLogToFile(tag, msg);
        }
    }
}
