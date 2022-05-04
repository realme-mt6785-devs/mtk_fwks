package com.oplus.compactwindow;

import android.app.OplusActivityManager;
import android.content.ComponentName;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.Map;
/* loaded from: classes4.dex */
public class OplusCompactWindowManager {
    public static final int DEFAULT_COMPACT_ROTATION = -1;
    public static final int OPLUS_MODE_BLOCK_DISPLAY_0 = 1024;
    public static final int OPLUS_MODE_BLOCK_DISPLAY_180 = 4096;
    public static final int OPLUS_MODE_BLOCK_DISPLAY_270 = 8192;
    public static final int OPLUS_MODE_BLOCK_DISPLAY_90 = 2048;
    public static final int OPLUS_MODE_BLOCK_SENSOR = 32;
    public static final int OPLUS_MODE_COMPACT = 4;
    public static final int OPLUS_MODE_PARALLEL = 2;
    private static final String TAG = "OplusCompactWindow";
    public static final int WINDOWING_MODE_COMPACTWINDOW = 120;
    public static final int WINDOWING_MODE_UNDEFINE = -1;
    private static volatile OplusCompactWindowManager sInstance;
    private final OplusActivityManager mOAms = new OplusActivityManager();

    public static OplusCompactWindowManager getInstance() {
        if (sInstance == null) {
            synchronized (OplusCompactWindowManager.class) {
                if (sInstance == null) {
                    sInstance = new OplusCompactWindowManager();
                }
            }
        }
        return sInstance;
    }

    private OplusCompactWindowManager() {
    }

    public int startCompactWindow() {
        try {
            Log.v(TAG, "startCompactWindow");
            return this.mOAms.startCompactWindow();
        } catch (RemoteException e) {
            Log.e(TAG, "startCompactWindow RemoteException");
            e.printStackTrace();
            return -1;
        }
    }

    public int exitCompactWindow() {
        try {
            Log.v(TAG, "exitCompactWindow");
            return this.mOAms.exitCompactWindow();
        } catch (RemoteException e) {
            Log.e(TAG, "exitCompactWindow RemoteException");
            e.printStackTrace();
            return -1;
        }
    }

    public boolean isCurrentAppSupportCompactMode() {
        try {
            return this.mOAms.isCurrentAppSupportCompactMode();
        } catch (RemoteException e) {
            Log.e(TAG, "isCurrentAppSupportCompactMode RemoteException");
            e.printStackTrace();
            return false;
        }
    }

    public int moveCompactWindowToLeft() {
        try {
            Log.v(TAG, "moveCompactWindowToLeft");
            return this.mOAms.moveCompactWindowToLeft();
        } catch (RemoteException e) {
            Log.e(TAG, "moveCompactWindowToLeft RemoteException");
            e.printStackTrace();
            return -1;
        }
    }

    public int moveCompactWindowToRight() {
        try {
            Log.v(TAG, "moveCompactWindowToRight");
            return this.mOAms.moveCompactWindowToRight();
        } catch (RemoteException e) {
            Log.e(TAG, "moveCompactWindowToRight RemoteException");
            e.printStackTrace();
            return -1;
        }
    }

    public boolean onProtocolUpdated(String content) {
        try {
            Log.v(TAG, "onProtocolUpdated");
            return this.mOAms.onProtocolUpdated(content);
        } catch (RemoteException e) {
            Log.e(TAG, "onProtocolUpdated RemoteException");
            e.printStackTrace();
            return false;
        }
    }

    public boolean registerCompactWindowObserver(IOplusCompactWindowObserver observer) {
        try {
            return this.mOAms.registerCompactWindowObserver(observer);
        } catch (RemoteException e) {
            Log.e(TAG, "registerCompactWindowObserver RemoteException");
            e.printStackTrace();
            return false;
        }
    }

    public boolean unregisterCompactWindowObserver(IOplusCompactWindowObserver observer) {
        try {
            return this.mOAms.unregisterCompactWindowObserver(observer);
        } catch (RemoteException e) {
            Log.e(TAG, "uregisterCompactWindowObserver RemoteException");
            e.printStackTrace();
            return false;
        }
    }

    public Map<String, ArrayList<String>> getPWAppInfo() {
        try {
            return this.mOAms.getPWAppInfo();
        } catch (RemoteException e) {
            Log.e(TAG, "getPWAppInfo RemoteException");
            e.printStackTrace();
            return null;
        }
    }

    public int getFocusMode() {
        try {
            return this.mOAms.getFocusMode();
        } catch (RemoteException e) {
            Log.e(TAG, "getFocusMode RemoteException");
            e.printStackTrace();
            return -1;
        }
    }

    public Rect getFocusBounds(boolean isPrimary) {
        try {
            Rect bounds = this.mOAms.getFocusBounds(isPrimary);
            return bounds;
        } catch (RemoteException e) {
            Log.e(TAG, "getFocusBounds RemoteException isPrimary:" + isPrimary);
            e.printStackTrace();
            return null;
        }
    }

    public ComponentName getFocusComponent(boolean isPrimary) {
        try {
            ComponentName cmn = this.mOAms.getFocusComponent(isPrimary);
            return cmn;
        } catch (RemoteException e) {
            Log.e(TAG, "getFocusComponent RemoteException isPrimary:" + isPrimary);
            e.printStackTrace();
            return null;
        }
    }

    public Point getRealSize() {
        try {
            Point point = this.mOAms.getRealSize();
            return point;
        } catch (RemoteException e) {
            Log.e(TAG, "getRealSize RemoteException");
            e.printStackTrace();
            return null;
        }
    }

    public Bundle callMethod(String method, String packageName, int param1, boolean param2, String param3, Bundle object) {
        try {
            Bundle bundle = this.mOAms.callMethod(method, packageName, param1, param2, param3, object);
            return bundle;
        } catch (RemoteException e) {
            Log.e(TAG, "callMethod RemoteException, method:" + method + " packageName:" + packageName);
            e.printStackTrace();
            return null;
        }
    }

    public Bundle invokeSync(String packageName, String method, String params, Bundle object) {
        try {
            Bundle bundle = this.mOAms.invokeSync(packageName, method, params, object);
            return bundle;
        } catch (RemoteException e) {
            Log.e(TAG, "invokeSync RemoteException, method:" + method + " packageName:" + packageName);
            e.printStackTrace();
            return null;
        }
    }

    public static int setBlockDisplayRotation(int flag, int rotation) {
        switch (rotation) {
            case 0:
                return flag | 1024;
            case 1:
                return flag | 2048;
            case 2:
                return flag | 4096;
            case 3:
                return flag | 8192;
            default:
                return flag;
        }
    }

    public static int getBlockDisplayRotation(int flag) {
        int i;
        if ((flag & 1024) != 0) {
            i = 0;
        } else if ((flag & 2048) != 0) {
            i = 1;
        } else if ((flag & 4096) != 0) {
            i = 2;
        } else if ((flag & 8192) != 0) {
            i = 3;
        } else {
            i = -1;
        }
        return i;
    }

    public static boolean isModeParallel(int flag) {
        return (flag & 2) != 0;
    }

    public static boolean isModeCompact(int flag) {
        return (flag & 4) != 0;
    }

    public static boolean isBlockSensor(int flag) {
        return (flag & 32) != 0;
    }

    public static String flagToString(int flag) {
        StringBuilder stringBuilder = new StringBuilder();
        if ((flag & 2) != 0) {
            stringBuilder.append(" MODE_PARALLEL ");
            stringBuilder.append(" ");
        }
        if ((flag & 4) != 0) {
            stringBuilder.append(" MODE_COMPACT ");
            stringBuilder.append(" ");
        }
        if ((flag & 32) != 0) {
            stringBuilder.append(" BLOCK_SENSOR ");
            stringBuilder.append(" ");
        }
        if ((flag & 1024) != 0) {
            stringBuilder.append(" DISPLAY_0 ");
            stringBuilder.append(" ");
        }
        if ((flag & 2048) != 0) {
            stringBuilder.append(" DISPLAY_90 ");
            stringBuilder.append(" ");
        }
        if ((flag & 4096) != 0) {
            stringBuilder.append(" DISPLAY_180 ");
            stringBuilder.append(" ");
        }
        if ((flag & 8192) != 0) {
            stringBuilder.append(" ISPLAY_270 ");
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }
}
