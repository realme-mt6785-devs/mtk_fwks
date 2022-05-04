package com.mediatek.boostfwk.utils;

import android.content.Context;
import android.os.Process;
import android.os.StrictMode;
import android.util.Slog;
import com.mediatek.bt.BluetoothMcsService;
import java.io.File;
/* loaded from: classes.dex */
public final class TasksUtil {
    private static final int STRICT_MODE_DETECT_THREAD_DISK_READ = 2;
    private static final String TAG = "TasksUtil";
    private static final String sAppBrandUI = "appbrand";
    private static final String sFlutterEngineName = "flutter_engine";
    private static final String sRenderThreadName = "RenderThread";
    private static final int[] CMDLINE_OUT = {4096};
    private static final String[] sSpecialTaskList = {"Chrome_InProcGp", "Chrome_IOThread", "hippy.js"};
    private static final String[] sGameTaskList = {"UnityMain", "MainThread-UE4"};

    private TasksUtil() {
    }

    public static int findRenderTheadTid(int pid) {
        int rdTid = -1;
        String filePath = "/proc/" + pid + "/task";
        int[] pids = Process.getPids(filePath, new int[BluetoothMcsService.ObjectIds.CURRENT_TRACK_OBJ_ID]);
        int length = pids.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            int tmpPid = pids[i];
            if (tmpPid < 0) {
                break;
            }
            String filePath2 = "/proc/" + pid + "/task/" + tmpPid + "/comm";
            String taskName = readCmdlineFromProcfs(filePath2);
            if (taskName == null || taskName.equals("") || !taskName.trim().equals(sRenderThreadName)) {
                i++;
            } else {
                if (Config.isBoostFwkLogEnable()) {
                    Slog.d(TAG, "renderthread tid = " + tmpPid);
                }
                rdTid = tmpPid;
            }
        }
        if (Config.isBoostFwkLogEnable()) {
            Slog.d("ScrollIdentify", "pid = " + pid + "render thread id = " + rdTid);
        }
        return rdTid;
    }

    private static String readCmdlineFromProcfs(String filePath) {
        String[] cmdline = new String[1];
        if (!Process.readProcFile(filePath, CMDLINE_OUT, cmdline, null, null)) {
            return "";
        }
        return cmdline[0];
    }

    public static boolean isAppBrand() {
        int pid = Process.myPid();
        String filePath = "/proc/" + pid + "/comm";
        String commName = readCmdlineFromProcfs(filePath);
        if (commName == null || !commName.contains(sAppBrandUI)) {
            return false;
        }
        if (Config.isBoostFwkLogEnable()) {
            Slog.d(TAG, "This is app brand.");
        }
        return true;
    }

    public static boolean hasCrossPlatformTask() {
        int pid = Process.myPid();
        int crossPlatformCount = 0;
        String filePath = "/proc/" + pid + "/task";
        int[] pids = Process.getPids(filePath, new int[BluetoothMcsService.ObjectIds.CURRENT_TRACK_OBJ_ID]);
        for (int tmpPid : pids) {
            if (tmpPid < 0) {
                break;
            }
            String filePath2 = "/proc/" + pid + "/task/" + tmpPid + "/comm";
            String taskName = readCmdlineFromProcfs(filePath2);
            if (taskName != null && !taskName.equals("")) {
                String[] strArr = sSpecialTaskList;
                int length = strArr.length;
                int i = 0;
                while (true) {
                    if (i < length) {
                        String spTaskName = strArr[i];
                        if (taskName.trim().equals(spTaskName)) {
                            crossPlatformCount++;
                            break;
                        }
                        i++;
                    }
                }
            }
        }
        return crossPlatformCount != 0;
    }

    public static boolean hasGameTask(int pid) {
        int gameTaskCount = 0;
        String filePath = "/proc/" + pid + "/task";
        int[] pids = Process.getPids(filePath, new int[BluetoothMcsService.ObjectIds.CURRENT_TRACK_OBJ_ID]);
        for (int tmpPid : pids) {
            if (tmpPid < 0) {
                break;
            }
            String filePath2 = "/proc/" + pid + "/task/" + tmpPid + "/comm";
            String taskName = readCmdlineFromProcfs(filePath2);
            if (taskName != null && !taskName.equals("")) {
                String[] strArr = sGameTaskList;
                int length = strArr.length;
                int i = 0;
                while (true) {
                    if (i < length) {
                        String spTaskName = strArr[i];
                        if (taskName.trim().equals(spTaskName)) {
                            gameTaskCount++;
                            break;
                        }
                        i++;
                    }
                }
            }
        }
        return gameTaskCount != 0;
    }

    public static boolean isGameAPP(Context context) {
        int pid = Process.myPid();
        boolean isGameAPP = Util.isGameApp(context.getPackageName()) || hasGameTask(pid);
        return isGameAPP;
    }

    public static boolean isFlutterApp(Context context) {
        Process.myPid();
        boolean isFlutter = false;
        File codeCacheDir = context.getCodeCacheDir();
        File[] fs = codeCacheDir.listFiles();
        if (!(fs == null || fs.length == 0)) {
            for (File f : fs) {
                if (sFlutterEngineName.equals(f.getName())) {
                    if (Config.isBoostFwkLogEnable()) {
                        Slog.d(TAG, "This is flutter.");
                    }
                    isFlutter = true;
                }
            }
        }
        return isFlutter;
    }

    public static boolean isSpeicalAPP(Context context) {
        Process.myPid();
        String packageName = context.getPackageName();
        if (packageName != null && "android".equals(packageName)) {
            return false;
        }
        boolean isFlutter = isFlutterApp(context);
        boolean isAppBrand = isAppBrand();
        boolean hasCrossTask = hasCrossPlatformTask();
        if (!isFlutter && !isAppBrand && !hasCrossTask) {
            return false;
        }
        if (Config.isBoostFwkLogEnable()) {
            Slog.d(TAG, "This is speical app.");
        }
        return true;
    }

    public static boolean isSpeicalAPPWOWebView(Context context) {
        Process.myPid();
        String packageName = context.getPackageName();
        if (packageName != null && "android".equals(packageName)) {
            return false;
        }
        boolean isFlutter = isFlutterApp(context);
        boolean isAppBrand = isAppBrand();
        if (!isFlutter && !isAppBrand) {
            return false;
        }
        if (Config.isBoostFwkLogEnable()) {
            Slog.d(TAG, "This is speical app.");
        }
        return true;
    }

    public static boolean isAPPInStrictMode(String packageName) {
        StrictMode.ThreadPolicy policy;
        String mask;
        boolean result = false;
        if (packageName == null || !packageName.contains("webview_shell") || (policy = StrictMode.getThreadPolicy()) == null || (mask = policy.toString().replace("[StrictMode.ThreadPolicy; mask=", "").replace("]", "")) == null || mask == "") {
            return false;
        }
        if ((Integer.parseInt(mask) & 2) != 0) {
            result = true;
        }
        if (Config.isBoostFwkLogEnable() && result) {
            Slog.d(TAG, "This is app in strictmode -> " + packageName + " mask:" + mask);
        }
        return result;
    }
}
