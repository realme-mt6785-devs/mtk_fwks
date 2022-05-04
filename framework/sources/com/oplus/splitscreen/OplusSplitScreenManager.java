package com.oplus.splitscreen;

import android.app.AppGlobals;
import android.app.OplusActivityTaskManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.Log;
import android.view.SurfaceControl;
import com.android.internal.R;
import com.oplus.app.IOplusSplitScreenObserver;
import com.oplus.app.OplusSplitScreenObserver;
import java.util.HashMap;
import java.util.Map;
import oplus.util.OplusStatistics;
/* loaded from: classes4.dex */
public class OplusSplitScreenManager {
    private static final boolean DEBUG = SystemProperties.getBoolean("persist.sys.assert.panic", false);
    public static final int DIVIDER_INSETS_FOR_FOLDER = 21;
    public static final String EVENT_DISMISS_SPLIT_SCREEN = "dismissSplitScreen";
    public static final String EVENT_ENTER_NORMAL_SPLIT = "enterNormalSplit";
    private static final String EVENT_ID_SPLIT_SCREEN_LAUNCH = "split_screen_launch";
    public static final String EVENT_SPLIT_SCREEN_MINIMIZED_CHANGED = "splitScreenMinimizedChange";
    public static final String EVENT_SPLIT_SCREEN_MODE_CHANGED = "splitScreenModeChange";
    public static final String EVENT_USER_DISMISS_SPLIT = "userDismissSplit";
    private static final String FORBID_SPLITSCREEN_FEATURE = "oplus.customize.splitscreen.disable";
    public static final String KEY_DISMISS_SPLIT_SCREEN_TYPE = "dismissSplitScreenType";
    public static final String KEY_IS_IN_SPLIT_SCREEN_MODE = "isInSplitScreenMode";
    public static final String KEY_IS_MINIMIZED = "isMinimized";
    private static final String KEY_LAUNCH_STYLE = "start_style";
    public static final int LAUNCH_AREA_BOTTOM = 4;
    public static final int LAUNCH_AREA_INVALID = -1;
    public static final int LAUNCH_AREA_LEFT = 1;
    public static final int LAUNCH_AREA_RIGHT = 3;
    public static final int LAUNCH_AREA_TOP = 2;
    private static final String PKG_EXSERVICEUI = "com.oplus.exserviceui";
    private static final String PKG_SYSTEMUI = "com.android.systemui";
    private static final String SETTINGS_FORBID_SPLITSCREEN = "forbid_splitscreen_by_ep";
    private static final String SPLIT_SCREEN_APPID = "20232";
    public static final int SPLIT_SCREEN_FROM_BREENO = 5;
    public static final int SPLIT_SCREEN_FROM_FLOAT_ASSISTANT = 4;
    public static final int SPLIT_SCREEN_FROM_MENU = 2;
    public static final int SPLIT_SCREEN_FROM_NONE = -1;
    public static final int SPLIT_SCREEN_FROM_RECENT = 3;
    public static final int SPLIT_SCREEN_FROM_SERVICE = 1;
    private static final String SPLIT_SCREEN_STATISTIC_ID = "20232001";
    public static final int STATE_APP_NOT_SUPPORT = 1006;
    public static final int STATE_BLACK_LIST = 1004;
    public static final int STATE_CHILDREN_MODE = 1005;
    public static final int STATE_FORBID_SPECIAL_APP = 1008;
    public static final int STATE_FORCE_FULLSCREEN = 1007;
    public static final int STATE_INVALID = 1000;
    public static final int STATE_SINGLE_HAND = 1003;
    public static final int STATE_SNAPSHOT = 1002;
    public static final int STATE_SUPPORT = 1001;
    private static final String TAG = "OplusSplitScreenManager";
    private static volatile OplusSplitScreenManager sInstance;
    private static IOplusSplitScreenSession sSplitScreenSession;
    private OplusActivityTaskManager mOAms;

    private OplusSplitScreenManager() {
        this.mOAms = null;
        this.mOAms = new OplusActivityTaskManager();
    }

    public static OplusSplitScreenManager getInstance() {
        if (sInstance == null) {
            synchronized (OplusSplitScreenManager.class) {
                if (sInstance == null) {
                    sInstance = new OplusSplitScreenManager();
                }
            }
        }
        return sInstance;
    }

    public int getSplitScreenState(Intent intent) {
        if (hasForbidScreenScreenFeature()) {
            Log.i(TAG, "getSplitScreenState is disabled for enterprise order");
            return 1000;
        } else if (intent != null) {
            if (DEBUG) {
                Log.i(TAG, "getSplitScreenState");
            }
            try {
                return this.mOAms.getSplitScreenState(intent);
            } catch (RemoteException e) {
                Log.e(TAG, "getSplitScreenState remoteException ");
                e.printStackTrace();
                return 1000;
            }
        } else {
            throw new IllegalArgumentException("getSplitScreenState intent=null");
        }
    }

    public int getTopAppSplitScreenState() {
        if (hasForbidScreenScreenFeature()) {
            Log.i(TAG, "getTopAppSplitScreenState is disabled for enterprise order");
            return 1000;
        }
        try {
            return this.mOAms.getSplitScreenState(null);
        } catch (RemoteException e) {
            Log.e(TAG, "getTopAppSplitScreenState remoteException ");
            e.printStackTrace();
            return 1000;
        }
    }

    @Deprecated
    public void swapDockedFullscreenStack() {
    }

    public boolean splitScreenForTopApp(int type) {
        if (hasForbidScreenScreenFeature()) {
            Log.i(TAG, "splitScreenForTopApp is disabled for enterprise order");
            return false;
        } else if (isEnterpriseDisableSplitScreen()) {
            Log.i(TAG, "splitScreenForTopApp isEnterpriseDisableSplitScreen");
            return false;
        } else if (type != 3) {
            if (DEBUG) {
                Log.i(TAG, "splitScreenForTopApp type:" + type);
            }
            try {
                return this.mOAms.splitScreenForTopApp(type);
            } catch (RemoteException e) {
                Log.e(TAG, "splitScreenForTopApp RemoteException");
                return false;
            }
        } else {
            throw new IllegalArgumentException("splitScreenForTopApp type is abnormal");
        }
    }

    public boolean splitScreenForRecentTasks(int taskId) {
        if (hasForbidScreenScreenFeature()) {
            Log.i(TAG, "splitScreenForRecentTasks is disabled for enterprise order");
            return false;
        } else if (isEnterpriseDisableSplitScreen()) {
            Log.i(TAG, "splitScreenForRecentTasks isEnterpriseDisableSplitScreen");
            return false;
        } else {
            if (DEBUG) {
                Log.i(TAG, "splitScreenForRecentTasks taskId:" + taskId);
            }
            try {
                return this.mOAms.splitScreenForRecentTasks(taskId);
            } catch (RemoteException e) {
                Log.e(TAG, "splitScreenForRecentTasks RemoteException");
                return false;
            }
        }
    }

    public void onSplitScreenLaunched(int type) {
        HashMap<String, String> logMap = new HashMap<>();
        logMap.put(KEY_LAUNCH_STYLE, type + "");
        OplusStatistics.onCommon((Context) AppGlobals.getInitialApplication(), SPLIT_SCREEN_APPID, SPLIT_SCREEN_STATISTIC_ID, EVENT_ID_SPLIT_SCREEN_LAUNCH, (Map<String, String>) logMap, false);
        if (DEBUG) {
            Log.i(TAG, "onSplitScreenLaunched logMap:" + logMap);
        }
    }

    public int splitScreenForEdgePanel(Intent intent, int userId) {
        if (hasForbidScreenScreenFeature()) {
            Log.i(TAG, "splitScreenForEdgePanel is disabled for enterprise order");
            return 0;
        } else if (isEnterpriseDisableSplitScreen()) {
            Log.i(TAG, "splitScreenForEdgePanel isEnterpriseDisableSplitScreen");
            return 0;
        } else if (intent != null) {
            if (DEBUG) {
                Log.i(TAG, "splitWindowForTopApp intent:" + intent);
            }
            try {
                return this.mOAms.splitScreenForEdgePanel(intent, userId);
            } catch (RemoteException e) {
                Log.e(TAG, "splitScreenForEdgePanel failed");
                return 0;
            }
        } else {
            throw new IllegalArgumentException("getSplitScreenState intent=null");
        }
    }

    public int getVersion() {
        return 1;
    }

    public boolean setTaskWindowingModeSplitScreen(int taskId) {
        if (DEBUG) {
            Log.d(TAG, "setTaskWindowingModeSplitScreen, taskId = " + taskId);
        }
        try {
            return this.mOAms.setTaskWindowingModeSplitScreen(taskId);
        } catch (RemoteException e) {
            Log.e(TAG, "setTaskWindowingModeSplitScreen RemoteException");
            return false;
        }
    }

    public boolean setSplitScreenObserver(IOplusSplitScreenObserver observer) {
        if (observer == null) {
            Log.e(TAG, "SystemUi setSplitScreenObserver error, observer is null");
            return false;
        }
        if (DEBUG) {
            Log.d(TAG, "SystemUi setSplitScreenObserver");
        }
        try {
            return this.mOAms.setSplitScreenObserver(observer);
        } catch (RemoteException e) {
            Log.e(TAG, "SystemUi setSplitScreenObserver RemoteException");
            return false;
        }
    }

    public void notifySplitScreenStateChanged(String event, Bundle bundle, boolean broadcast) {
        if (DEBUG) {
            Log.d(TAG, "SystemUi notifyStateChanged");
        }
        try {
            this.mOAms.notifySplitScreenStateChanged(event, bundle, broadcast);
        } catch (RemoteException e) {
            Log.e(TAG, "notifyStateChanged RemoteException");
        }
    }

    public boolean isInSplitScreenMode() {
        try {
            return this.mOAms.isInSplitScreenMode();
        } catch (RemoteException e) {
            Log.e(TAG, "isInSplitScreenMode RemoteException");
            return false;
        }
    }

    public boolean dismissSplitScreenMode(int type) {
        if (DEBUG) {
            Log.d(TAG, "dismissSplitScreenMode type = " + type);
        }
        try {
            return this.mOAms.dismissSplitScreenMode(type);
        } catch (RemoteException e) {
            Log.e(TAG, "dismissSplitScreenMode RemoteException");
            return false;
        }
    }

    public boolean registerSplitScreenObserver(IOplusSplitScreenObserver observer) {
        if (observer == null) {
            Log.e(TAG, "registerSplitScreenObserver error, observer is null");
            return false;
        }
        try {
            return this.mOAms.registerSplitScreenObserver(observer.hashCode(), observer);
        } catch (RemoteException e) {
            Log.e(TAG, "registerSplitScreenObserver failed");
            return false;
        }
    }

    public boolean unregisterSplitScreenObserver(IOplusSplitScreenObserver observer) {
        if (observer == null) {
            Log.e(TAG, "unregisterSplitScreenObserver error, observer is null");
            return false;
        }
        try {
            return this.mOAms.unregisterSplitScreenObserver(observer.hashCode(), observer);
        } catch (RemoteException e) {
            Log.e(TAG, "unregisterSplitScreenObserver failed");
            return false;
        }
    }

    public Bundle getSplitScreenStatus(String event) {
        if (DEBUG) {
            Log.d(TAG, "getSplitScreenStatus event = " + event);
        }
        try {
            return this.mOAms.getSplitScreenStatus(event);
        } catch (RemoteException e) {
            Log.e(TAG, "getSplitScreenCurrentState failed");
            return null;
        }
    }

    private boolean hasForbidScreenScreenFeature() {
        try {
            return AppGlobals.getPackageManager().hasSystemFeature(FORBID_SPLITSCREEN_FEATURE, 0);
        } catch (RemoteException e) {
            Log.e(TAG, "hasForbidScreenScreenFeature RemoteException");
            return false;
        }
    }

    private boolean isEnterpriseDisableSplitScreen() {
        try {
            return Settings.Secure.getInt(AppGlobals.getInitialApplication().getContentResolver(), SETTINGS_FORBID_SPLITSCREEN, 0) == 1;
        } catch (Exception e) {
            Log.e(TAG, "isEnterpriseDisableSplitScreen error");
            return false;
        }
    }

    public boolean splitScreenForEdgePanel(Intent intent, boolean launchToPrimary, int launchArea) {
        if (hasForbidScreenScreenFeature()) {
            Log.i(TAG, "splitScreenForEdgePanel is disabled for enterprise order");
            return false;
        } else if (isEnterpriseDisableSplitScreen()) {
            Log.i(TAG, "splitScreenForEdgePanel isEnterpriseDisableSplitScreen");
            return false;
        } else if (intent == null) {
            Log.e(TAG, "splitScreenForEdgePanel error, intent is null");
            return false;
        } else {
            if (DEBUG) {
                Log.i(TAG, "splitScreenForEdgePanel intent:" + intent + ", launchToPrimary = " + launchToPrimary);
            }
            try {
                return this.mOAms.splitScreenForEdgePanel(intent, launchToPrimary, launchArea);
            } catch (Exception e) {
                Log.e(TAG, "splitScreenForEdgePanel failed");
                return false;
            }
        }
    }

    public boolean hasFolderFeature() {
        try {
            return this.mOAms.hasFolderFeature();
        } catch (RemoteException e) {
            Log.e(TAG, "hasFolderFeature error");
            return false;
        }
    }

    public boolean isFolderInnerScreen() {
        try {
            return this.mOAms.isFolderInnerScreen();
        } catch (RemoteException e) {
            Log.e(TAG, "isFolderInnerScreen error");
            return false;
        }
    }

    public Rect getMinimizedBounds(int dockedSide) {
        try {
            return this.mOAms.getMinimizedBounds(dockedSide);
        } catch (RemoteException e) {
            Log.e(TAG, "getMinimizedBounds error");
            return new Rect();
        }
    }

    public void setHandleRegion(int windowingMode, Rect handleRegion) {
        try {
            this.mOAms.setHandleRegion(windowingMode, handleRegion);
        } catch (RemoteException e) {
            Log.e(TAG, "setHandleRegion error");
        }
    }

    public boolean hasNewDragSplitFeature() {
        try {
            return this.mOAms.hasNewDragSplitFeature();
        } catch (Exception e) {
            Log.e(TAG, "setHandleRegion error");
            return false;
        }
    }

    public int getDividerInsets(Resources res) {
        if (hasNewDragSplitFeature()) {
            return (int) (res.getDisplayMetrics().density * 21.0f);
        }
        return res.getDimensionPixelSize(R.dimen.docked_stack_divider_insets);
    }

    private IOplusSplitScreenSession getSplitScreenSession() {
        IOplusSplitScreenSession iOplusSplitScreenSession;
        synchronized (OplusSplitScreenManager.class) {
            if (sSplitScreenSession == null) {
                try {
                    sSplitScreenSession = this.mOAms.getSplitScreenSession();
                } catch (RemoteException e) {
                    Log.e(TAG, "getSplitScreenSession error");
                }
            }
            iOplusSplitScreenSession = sSplitScreenSession;
        }
        return iOplusSplitScreenSession;
    }

    public void notifyFoldUpdatingComplete() {
        try {
            IOplusSplitScreenSession session = getSplitScreenSession();
            if (session != null) {
                session.notifyFoldUpdatingComplete();
            }
        } catch (Exception e) {
            Log.e(TAG, "notifyFoldUpdatingComplete error");
        }
    }

    public void registerStackDivider(Context context, IOplusStackDividerConnection conn) {
        if (checkCaller(context, PKG_SYSTEMUI)) {
            try {
                IOplusSplitScreenSession session = getSplitScreenSession();
                if (session != null) {
                    session.registerStackDivider(conn);
                }
            } catch (Exception e) {
                Log.e(TAG, "registerStackDivider error");
            }
        }
    }

    public void waitWindowDrawnAfterMinimized(OplusSplitScreenObserver observer) {
        if (observer != null) {
            try {
                IOplusSplitScreenSession session = getSplitScreenSession();
                if (session != null) {
                    session.waitWindowDrawnAfterMinimized(observer);
                }
            } catch (Exception e) {
                Log.e(TAG, "waitWindowDrawnAfterMinimized error");
            }
        }
    }

    public void startDividerRemoteAnimation(Context context, SurfaceControl out) {
        if (checkCaller(context, PKG_EXSERVICEUI)) {
            try {
                IOplusSplitScreenSession session = getSplitScreenSession();
                if (session != null) {
                    session.startDividerRemoteAnimation(out);
                }
            } catch (Exception e) {
                Log.e(TAG, "startDividerRemoteAnimation error");
            }
        }
    }

    public void finishDividerRemoteAnimation(Context context) {
        if (checkCaller(context, PKG_EXSERVICEUI)) {
            try {
                IOplusSplitScreenSession session = getSplitScreenSession();
                if (session != null) {
                    session.finishDividerRemoteAnimation();
                }
            } catch (Exception e) {
                Log.e(TAG, "finishDividerRemoteAnimation error");
            }
        }
    }

    private boolean checkCaller(Context context, String pkg) {
        if (context == null || !pkg.equals(context.getPackageName())) {
            return false;
        }
        return true;
    }
}
