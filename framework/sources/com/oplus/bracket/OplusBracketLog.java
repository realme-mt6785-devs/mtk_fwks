package com.oplus.bracket;

import android.os.SystemProperties;
import android.util.Log;
/* loaded from: classes4.dex */
public class OplusBracketLog {
    public static final boolean DEBUG_PARSER = false;
    private static final String TAG = "OplusBracketLog";
    public static boolean DEBUG = SystemProperties.getBoolean("persist.sys.assert.panic", false);
    public static boolean DEBUG_QUERY = false;

    public static void d(String content) {
        if (DEBUG) {
            Log.d(TAG, content);
        }
    }

    public static void i(String content) {
        if (DEBUG && DEBUG_QUERY) {
            Log.i(TAG, content);
        }
    }

    public static void e(String content) {
        if (DEBUG) {
            Log.e(TAG, content);
        }
    }

    public static void d(String tag, String content) {
        if (DEBUG) {
            Log.d(TAG, "[" + tag + "] " + content);
        }
    }

    public static void i(String tag, String content) {
        if (DEBUG && DEBUG_QUERY) {
            Log.i(TAG, "[" + tag + "] " + content);
        }
    }

    public static void e(String tag, String content) {
        Log.e(TAG, "[" + tag + "] " + content);
    }

    public static void e(String tag, String content, Throwable throwable) {
        Log.e(TAG, "[" + tag + "] " + content, throwable);
    }

    public static void openLogSwitch(boolean on) {
        Log.d(TAG, "openBracketLogSwitch: " + on);
        DEBUG = on;
        DEBUG_QUERY = on;
    }
}
