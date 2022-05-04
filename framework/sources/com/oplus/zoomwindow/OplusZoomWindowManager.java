package com.oplus.zoomwindow;

import android.app.OplusActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Slog;
import com.oplus.app.IOplusZoomWindowConfigChangedListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes4.dex */
public class OplusZoomWindowManager {
    public static final int ACTION_DEFAULT_HANDLE = 1;
    public static final int ACTION_DELETE_HANDLE = 16;
    public static final int ACTION_INVISIBLE_HANDLE = 4;
    public static final int ACTION_MASK_FOR_FLOAT_HANDLE = 28;
    public static final int ACTION_MASK_ON_SHOWING_OF_MINI_ZOOM_MODE = -8193;
    public static final int ACTION_MASK_ON_SHOWING_OF_ZOOM_MODE = -2;
    public static final int ACTION_NEW_HANDLE = 8;
    public static final int ACTION_RELAUNCH_HANDLE = 32;
    public static final int ACTION_VISIBLE_HANDLE = 2;
    public static final int DEFAULT_TOP_OFFSET_OF_ZOOM_PORTRAIT = 269;
    public static final String EXTRA_WINDOW_MODE = "extra_window_mode";
    public static final int FLAG_BUBBLE_ZOOM_WINDOW = 2;
    public static final int FLAG_CLICKED_FULL_SCREEN_BUTTON = 5;
    public static final int FLAG_CLICK_OUTSIDE_ZOOM = 3;
    public static final int FLAG_DELETE_MINI_ZOOM_WINDOW = 8;
    public static final int FLAG_DELETE_ON_ACTION_SIMPLE_MODE_BUTTON = 16;
    public static final int FLAG_DELETE_ON_ACTION_USER_SWITCHED = 15;
    public static final int FLAG_DELETE_ON_BLOCKING_PACKAGE = 17;
    public static final int FLAG_DELETE_ON_CLICKED_CLOSED_ZOOM = 14;
    public static final int FLAG_DELETE_ON_ENTER_DRAG_WINDOW = 10;
    public static final int FLAG_DELETE_ON_ENTER_SPEC_MODE = 13;
    public static final int FLAG_DELETE_ZOOM_BY_RECENTS = 12;
    public static final int FLAG_DELETE_ZOOM_BY_SCREEN_OFF = 11;
    public static final int FLAG_DELETE_ZOOM_WINDOW = 1;
    public static final int FLAG_DELETE_ZOOM_WINDOW_BY_GLOBAL_DRAG = 9;
    public static final int FLAG_EXIT_ZOOM_BY_OTHERS = 6;
    public static final int FLAG_HIDE_ZOOM_WINDOW = 2;
    public static final int FLAG_MOVE_TO_HOT_EDGE = 7;
    public static final int FLAG_ON_DEFAULT_ACTION = 0;
    public static final int FLAG_ON_DEFAULT_EVENT = 0;
    public static final int FLAG_ON_DELETE_CONTROL_VIEW = 2;
    public static final int FLAG_ON_DELETE_HANDLE = 8;
    public static final int FLAG_ON_DELETE_MINI_ZOOM_BUTTON_RESTORE = 65536;
    public static final int FLAG_ON_DELETE_MINI_ZOOM_BUTTON_SELECTED = 32768;
    public static final int FLAG_ON_DELETE_MINI_ZOOM_CONTROL_VIEW = 16384;
    public static final int FLAG_ON_DELETE_ZOOM_BUTTON_RESTORE = 2048;
    public static final int FLAG_ON_DELETE_ZOOM_BUTTON_SELECTED = 256;
    public static final int FLAG_ON_DISABLE_CONTROL_VIEW = 64;
    public static final int FLAG_ON_DOUBLE_TAP_TO_CLOSED_ZOOM = 512;
    public static final int FLAG_ON_DRAG_TO_SCALE_LEFT = 32;
    public static final int FLAG_ON_DRAG_TO_SCALE_RIGHT = 64;
    public static final int FLAG_ON_DRAG_TO_SCALE_UP = 128;
    public static final int FLAG_ON_FLING = 2;
    public static final int FLAG_ON_FULL_SCREEN = 256;
    public static final int FLAG_ON_FULL_SCREEN_BUTTON_RESTORE = 1024;
    public static final int FLAG_ON_FULL_SCREEN_BUTTON_SELECTED = 128;
    public static final int FLAG_ON_HIDE_HANDLE = 16;
    public static final int FLAG_ON_HIDE_TIPS_VIEW = 1048576;
    public static final int FLAG_ON_LONG_PRESS = 1;
    public static final int FLAG_ON_MINI_ZOOM_BUTTON_RESTORE = 4096;
    public static final int FLAG_ON_MINI_ZOOM_BUTTON_SELECTED = 512;
    public static final int FLAG_ON_MINI_ZOOM_SHOW_TIPS_VIEW = 262144;
    public static final int FLAG_ON_SHOW_CONTROL_VIEW = 1;
    public static final int FLAG_ON_SHOW_GUIDE = 2097152;
    public static final int FLAG_ON_SHOW_HANDLE = 4;
    public static final int FLAG_ON_SHOW_MINI_ZOOM_CONTROL_VIEW = 8192;
    public static final int FLAG_ON_SINGLE_TAP_TO_CLOSED_ZOOM = 16;
    public static final int FLAG_ON_SINGLE_TAP_TO_SHOW_TIP = 8;
    public static final int FLAG_ON_SING_TAP_MINI_ZOOM = 524288;
    public static final int FLAG_ON_SING_TAP_SHOW_TIPS_VIEW = 131072;
    public static final int FLAG_ON_START_BY_LAUNCHER_GESTURE_FROM_FULL_SCREEN = 1;
    public static final int FLAG_ON_START_BY_LAUNCHER_SWIPE_UP_FROM_ZOOM = 3;
    public static final int FLAG_ON_START_BY_MINI_ZOOM_BUTTON_FROM_ZOOM = 2;
    public static final int FLAG_ON_START_FULL_SCREEN_FROM_RECENTS = 3;
    public static final int FLAG_ON_START_MINI_BY_CHANGE_SCALE = 5;
    public static final int FLAG_ON_START_MINI_BY_FLOAT_HANDLE = 6;
    public static final int FLAG_ON_START_MINI_ZOOM_BY_LAUNCHER_CARD = 4;
    public static final int FLAG_ON_START_MINI_ZOOM_FROM_FULL_SCREEN = 1;
    public static final int FLAG_ON_START_MINI_ZOOM_FROM_RECENTS = 2;
    public static final int FLAG_ON_START_MINI_ZOOM_FROM_ZOOM = 2;
    public static final int FLAG_ON_START_ZOOM_FROM_FLOAT_HANDLE = 4;
    public static final int FLAG_ON_START_ZOOM_FROM_NOTIFICATION = 5;
    public static final int FLAG_ON_START_ZOOM_FROM_NOTIFICATION_NEW_TASK = 6;
    public static final int FLAG_ON_START_ZOOM_FROM_RECENTS = 1;
    public static final int FLAG_ON_UP = 4;
    public static final int FLAG_SHOW_ZOOM_WINDOW = 1;
    public static final int FLAG_TOUCH_OUTSIDE_CONTROL_VIEW = 4194304;
    public static final int FLAG_UNSUPPORT_ZOOM = 4;
    public static final int FLOAT_HANDLE_ON_LEFT = 0;
    public static final int FLOAT_HANDLE_ON_RIGHT = 1;
    public static final String KEY_OF_DELETE_MINI_ZOOM_BUTTON = "delete_mini_zoom_button";
    public static final String KEY_OF_DELETE_ZOOM_BUTTON = "delete_zoom_button";
    public static final String KEY_OF_FLOAT_HANDLE_INFO = "float_handle_info";
    public static final String KEY_OF_FULL_SCREEN_BUTTON = "full_screen_button";
    public static final String KEY_OF_MINI_ZOOM_BUTTON = "mini_zoom_button";
    public static final String KEY_ZOOM_TASK_ID_FROM_RECENTS = "zoom_task_id";
    public static final String KEY_ZOOM_TYPE_FROM_RECENTS = "android:activity.mZoomLaunchFlags";
    public static final int LEFT_RIGHT_LIMIT_EDGE_OF_MOVE_MINI_ZOOM = 16;
    public static final float MINI_ZOOM_SCALE_FOR_LANDSCAPE_DEFAULT = 0.3f;
    public static final float MINI_ZOOM_SCALE_FOR_PORTRAIT_DEFAULT = 0.3f;
    public static final int PAD_LEFT_RIGHT_LIMIT_EDGE_OF_MOVE_MINI_ZOOM = 48;
    public static final float PAD_MINI_ZOOM_SCALE_FOR_LANDSCAPE_DEFAULT = 0.417f;
    public static final float PAD_MINI_ZOOM_SCALE_FOR_PORTRAIT_DEFAULT = 0.417f;
    public static final int PAD_TOP_BOTTOM_LIMIT_EDGE_OF_MOVE_MINI_ZOOM_LANDSCAPE = 48;
    public static final int PAD_TOP_BOTTOM_LIMIT_EDGE_OF_MOVE_MINI_ZOOM_PORTRAIT = 48;
    public static final float PAD_ZOOM_RATIO = 1.6666666f;
    public static final float PAD_ZOOM_SCALE_FOR_LANDSCAPE_DEFAULT = 0.833f;
    public static final float PAD_ZOOM_SCALE_FOR_PORTRAIT_DEFAULT = 0.867f;
    public static final float PAD_ZOOM_SCALE_LAND_FOR_LANDSCAPE_APP_DEFAULT = 0.833f;
    public static final float PAD_ZOOM_SCALE_LAND_FOR_PORTRAIT_APP_DEFAULT = 0.867f;
    private static final String TAG = "OplusZoomWindowManager";
    public static final int TOP_BOTTOM_LIMIT_EDGE_OF_MOVE_MINI_ZOOM_LANDSCAPE = 16;
    public static final int TOP_BOTTOM_LIMIT_EDGE_OF_MOVE_MINI_ZOOM_PORTRAIT = 40;
    public static final int TOP_ZOOM_CLOSE = 1;
    public static final int TOP_ZOOM_TO_FULL = 0;
    public static final int TYPE_FORCED_RELAUNCH_ZOOM_CPN_LIST = 9;
    public static final int TYPE_FORCES_SHOW_TOAST_LIST = 4;
    public static final int TYPE_NOT_SHOW_TOAST_LIST = 5;
    public static final int TYPE_SHOW_COMPATIBILITY_TOAST = 1;
    public static final int TYPE_SHOW_UNSUPPORT_TOAST = 2;
    public static final int TYPE_UNRELAUNCH_ZOOM_CPN_LIST = 8;
    public static final int TYPE_UNREUSED_ZOOM_CPN_LIST = 7;
    public static final int TYPE_UNSUPPORT_ZOOM_CPN_LIST = 6;
    public static final int TYPE_ZOOM_APP_BLACK_LIST = 2;
    public static final int TYPE_ZOOM_APP_REPLY_LIST = 3;
    public static final int TYPE_ZOOM_APP_SUPPORT_LIST = 1;
    public static final int WINDOWING_MODE_FULLSCREEN = 1;
    public static final int WINDOWING_MODE_ZOOM = 100;
    public static final int WINDOWING_MODE_ZOOM_LEGACY = 6;
    public static final int WINDOWING_MODE_ZOOM_TO_FULLSCREEN = 101;
    public static final int WINDOW_TYPE_MINIZOOM = 2;
    public static final int WINDOW_TYPE_UNDEFINE = 0;
    public static final int WINDOW_TYPE_ZOOM = 1;
    public static final int ZOOM_CORNER_RADIUS_PORTRAIT = 22;
    public static final float ZOOM_LANDSCAPE_APP_RATIO = 1.6666666f;
    public static final float ZOOM_LANDSCAPE_RATIO = 1.45f;
    public static final float ZOOM_RATIO = 1.6666666f;
    public static final float ZOOM_SCALE_FOR_LANDSCAPE_APP_DEFAULT = 0.556f;
    public static final float ZOOM_SCALE_FOR_LANDSCAPE_DEFAULT = 0.6f;
    public static final float ZOOM_SCALE_FOR_PORTRAIT_DEFAULT = 0.667f;
    private static volatile OplusZoomWindowManager sInstance;
    private final Map<OnConfigChangedListener, IOplusZoomWindowConfigChangedListener> mConfigListeners = new ArrayMap();
    private OplusActivityManager mOAms = new OplusActivityManager();

    /* loaded from: classes4.dex */
    public interface OnConfigChangedListener {
        void onConfigSwitchChanged(boolean z);

        void onConfigTypeChanged(int i);
    }

    public static OplusZoomWindowManager getInstance() {
        if (sInstance == null) {
            synchronized (OplusZoomWindowManager.class) {
                if (sInstance == null) {
                    sInstance = new OplusZoomWindowManager();
                }
            }
        }
        return sInstance;
    }

    private OplusZoomWindowManager() {
    }

    public int startZoomWindow(Intent intent, Bundle bOptions, int userId, String callPkg) {
        try {
            Log.v(TAG, "startZoomWindow: " + intent + " callPkg: " + callPkg);
            return this.mOAms.startZoomWindow(intent, bOptions, userId, callPkg);
        } catch (RemoteException e) {
            Log.e(TAG, "startZoomWindow remoteException ");
            e.printStackTrace();
            return -1;
        }
    }

    public boolean registerZoomWindowObserver(IOplusZoomWindowObserver observer) {
        try {
            return this.mOAms.registerZoomWindowObserver(observer);
        } catch (RemoteException e) {
            Log.e(TAG, "registerZoomWindowObserver remoteException ");
            e.printStackTrace();
            return false;
        }
    }

    public boolean unregisterZoomWindowObserver(IOplusZoomWindowObserver observer) {
        try {
            return this.mOAms.unregisterZoomWindowObserver(observer);
        } catch (RemoteException e) {
            Log.e(TAG, "unregisterZoomWindowObserver remoteException ");
            e.printStackTrace();
            return false;
        }
    }

    public boolean registerZoomAppObserver(IOplusZoomAppObserver observer) {
        Slog.i(TAG, "registerZoomAppObserver start");
        try {
            return this.mOAms.registerZoomAppObserver(observer);
        } catch (RemoteException e) {
            Log.e(TAG, "registerZoomAppObserver remoteException ");
            e.printStackTrace();
            return false;
        }
    }

    public boolean unregisterZoomAppObserver(IOplusZoomAppObserver observer) {
        Slog.i(TAG, "unregisterZoomAppObserver start");
        try {
            return this.mOAms.unregisterZoomAppObserver(observer);
        } catch (RemoteException e) {
            Log.e(TAG, "unregisterZoomAppObserver remoteException ");
            e.printStackTrace();
            return false;
        }
    }

    public boolean isSupportZoomWindowMode() {
        try {
            return this.mOAms.isSupportZoomWindowMode();
        } catch (RemoteException e) {
            Log.e(TAG, "isSupportZoomWindowMode remoteException ");
            e.printStackTrace();
            return true;
        }
    }

    public boolean isSupportZoomMode(String target, int userId, String callPkg, Bundle extension) {
        try {
            return this.mOAms.isSupportZoomMode(target, userId, callPkg, extension);
        } catch (RemoteException e) {
            Log.e(TAG, "isSupportZoomMode remoteException ");
            e.printStackTrace();
            return false;
        }
    }

    public boolean handleShowCompatibilityToast(String target, int userId, String callPkg, Bundle extension, int type) {
        try {
            return this.mOAms.handleShowCompatibilityToast(target, userId, callPkg, extension, type);
        } catch (RemoteException e) {
            Log.e(TAG, "handleShowCompatibilityToast remoteException ");
            e.printStackTrace();
            return false;
        }
    }

    public void startMiniZoomFromZoom(int startWay) {
        try {
            this.mOAms.startMiniZoomFromZoom(startWay);
        } catch (RemoteException e) {
            Log.e(TAG, "startMiniZoomFromZoom remoteException ");
            e.printStackTrace();
        }
    }

    public OplusZoomWindowInfo getCurrentZoomWindowState() {
        try {
            return this.mOAms.getCurrentZoomWindowState();
        } catch (RemoteException e) {
            Log.e(TAG, "getCurrentZoomWindowState remoteException ");
            e.printStackTrace();
            return null;
        }
    }

    public void hideZoomWindow(int flag) {
        try {
            this.mOAms.hideZoomWindow(flag);
        } catch (RemoteException e) {
            Log.e(TAG, "hideZoomWindow remoteException ");
            e.printStackTrace();
        }
    }

    public List<String> getZoomAppConfigList(int type) {
        try {
            return this.mOAms.getZoomAppConfigList(type);
        } catch (RemoteException e) {
            Log.e(TAG, "hideZoomWindow remoteException ");
            e.printStackTrace();
            return new ArrayList(0);
        }
    }

    public OplusZoomWindowRUSConfig getZoomWindowConfig() {
        Log.i(TAG, "getZoomWindowConfig start");
        try {
            return this.mOAms.getZoomWindowConfig();
        } catch (RemoteException e) {
            Log.e(TAG, "getZoomWindowConfig remoteException ");
            e.printStackTrace();
            return new OplusZoomWindowRUSConfig();
        }
    }

    public void setZoomWindowConfig(OplusZoomWindowRUSConfig config) {
        Log.i(TAG, "setZoomWindowConfig start");
        try {
            this.mOAms.setZoomWindowConfig(config);
        } catch (RemoteException e) {
            Log.e(TAG, "setZoomWindowConfig remoteException ");
            e.printStackTrace();
        }
    }

    public boolean addOnConfigChangedListener(OnConfigChangedListener listener) {
        Log.i(TAG, "addOnConfigChangedListener listener = " + listener);
        synchronized (this.mConfigListeners) {
            if (this.mConfigListeners.get(listener) != null) {
                Log.i(TAG, "addOnConfigChangedListener already added before");
                return false;
            }
            OnConfigChangeListenerDelegate delegate = new OnConfigChangeListenerDelegate(listener, Looper.getMainLooper());
            try {
            } catch (RemoteException e) {
                Log.e(TAG, "addOnConfigChangedListener remoteException ");
                e.printStackTrace();
            }
            if (this.mOAms == null) {
                return false;
            }
            this.mConfigListeners.put(listener, delegate);
            return this.mOAms.addZoomWindowConfigChangedListener(delegate);
        }
    }

    public boolean removeOnConfigChangedListener(OnConfigChangedListener listener) {
        Log.i(TAG, "removeOnConfigChangedListener listener = " + listener);
        synchronized (this.mConfigListeners) {
            IOplusZoomWindowConfigChangedListener delegate = this.mConfigListeners.get(listener);
            if (delegate != null) {
                try {
                    if (this.mOAms != null) {
                        this.mConfigListeners.remove(listener);
                        return this.mOAms.removeZoomWindowConfigChangedListener(delegate);
                    }
                } catch (RemoteException e) {
                    Log.e(TAG, "removeOnConfigChangedListener remoteException ");
                    e.printStackTrace();
                }
            }
            return false;
        }
    }

    /* loaded from: classes4.dex */
    private class OnConfigChangeListenerDelegate extends IOplusZoomWindowConfigChangedListener.Stub implements Handler.Callback {
        private static final int MSG_CONFIG_SWITCH_CHANGED = 2;
        private static final int MSG_CONFIG_TYPE_CHANGED = 1;
        private final Handler mHandler;
        private final OnConfigChangedListener mListener;

        public OnConfigChangeListenerDelegate(OnConfigChangedListener listener, Looper looper) {
            this.mListener = listener;
            this.mHandler = new Handler(looper, this);
        }

        @Override // com.oplus.app.IOplusZoomWindowConfigChangedListener
        public void onConfigTypeChanged(int type) {
            this.mHandler.obtainMessage(1, type, 0).sendToTarget();
        }

        @Override // com.oplus.app.IOplusZoomWindowConfigChangedListener
        public void onConfigSwitchChanged(boolean enable) {
            this.mHandler.obtainMessage(2, enable ? 1 : 0, 0).sendToTarget();
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message msg) {
            boolean enable = false;
            switch (msg.what) {
                case 1:
                    int type = msg.arg1;
                    this.mListener.onConfigTypeChanged(type);
                    return true;
                case 2:
                    if (msg.arg1 != 0) {
                        enable = true;
                    }
                    this.mListener.onConfigSwitchChanged(enable);
                    return true;
                default:
                    return false;
            }
        }
    }

    public void onInputEvent(OplusZoomInputEventInfo inputEventInfo) {
        try {
            this.mOAms.onInputEvent(inputEventInfo);
        } catch (RemoteException e) {
            Log.e(TAG, "onInputEvent remoteException ");
            e.printStackTrace();
        }
    }

    public void onControlViewChanged(OplusZoomControlViewInfo cvInfo) {
        try {
            this.mOAms.onControlViewChanged(cvInfo);
        } catch (RemoteException e) {
            Log.e(TAG, "onControlViewChanged remoteException ");
            e.printStackTrace();
        }
    }

    public void setBubbleMode(boolean inBubbleMode) {
    }

    public void onFloatHandleViewChanged(OplusZoomFloatHandleViewInfo floatHandleInfo) {
        try {
            this.mOAms.onFloatHandleViewChanged(floatHandleInfo);
        } catch (RemoteException e) {
            Log.e(TAG, "onFloatHandleViewChanged remoteException ");
            e.printStackTrace();
        }
    }

    public boolean isZoomSimpleModeEnable() {
        try {
            return this.mOAms.isZoomSimpleModeEnable();
        } catch (RemoteException e) {
            Log.e(TAG, "isZoomSimpleModeEnable remoteException ");
            e.printStackTrace();
            return false;
        }
    }
}
