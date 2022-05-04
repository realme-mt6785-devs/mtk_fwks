package com.mediatek.boostfwk.utils;

import android.app.AppGlobals;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageManager;
import android.hardware.display.DisplayManagerGlobal;
import android.os.Looper;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Slog;
import android.view.DisplayInfo;
import android.view.WindowManager;
import com.mediatek.bt.BluetoothMcsService;
import java.io.File;
/* loaded from: classes.dex */
public final class Util {
    private static final String TAG = "SBE-Util";
    private static final String[] sGameLibs = {"libGame.so", "libhegame.so"};

    public static boolean isGameApp(String pkgName) {
        ApplicationInfo appInfo;
        String[] strArr;
        IPackageManager pm = AppGlobals.getPackageManager();
        try {
            appInfo = pm.getApplicationInfo(pkgName, 0, UserHandle.getCallingUserId());
        } catch (RemoteException e) {
            Slog.e(TAG, "isGameApp exception :" + e);
        }
        if (appInfo == null) {
            return true;
        }
        if (appInfo != null && (appInfo.flags & 33554432) == 33554432) {
            return true;
        }
        for (String gameLibName : sGameLibs) {
            File file = new File(appInfo.nativeLibraryDir, gameLibName);
            if (file.exists()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSystemApp(String pkgName) {
        IPackageManager pm = AppGlobals.getPackageManager();
        try {
            ApplicationInfo appInfo = pm.getApplicationInfo(pkgName, 0, UserHandle.getCallingUserId());
            if (!(appInfo == null || appInfo == null)) {
                if ((appInfo.flags & 1) == 0) {
                    if ((appInfo.flags & 128) != 0) {
                    }
                }
                return true;
            }
        } catch (RemoteException e) {
            Slog.e(TAG, "isSystemApp exception :" + e);
        }
        return false;
    }

    public static boolean IsFullScreen(WindowManager.LayoutParams attrs) {
        if (attrs == null || (attrs.flags & BluetoothMcsService.ObjectIds.CURRENT_TRACK_OBJ_ID) != 1024) {
            return false;
        }
        return true;
    }

    public static float getRefreshRate() {
        DisplayInfo di = DisplayManagerGlobal.getInstance().getDisplayInfo(0);
        return di.getMode().getRefreshRate();
    }

    public static boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}
