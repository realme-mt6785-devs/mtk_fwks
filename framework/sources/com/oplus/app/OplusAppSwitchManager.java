package com.oplus.app;

import android.app.OplusActivityTaskManager;
import android.content.Context;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.util.ArrayMap;
import android.util.Log;
import com.oplus.app.IOplusAppSwitchObserver;
import java.util.Map;
/* loaded from: classes4.dex */
public class OplusAppSwitchManager {
    public static int APP_SWITCH_VERSION = 1;
    private static final boolean DEBUG = SystemProperties.getBoolean("persist.sys.assert.panic", false);
    public static final String EXTRA_NOTIFY_TYPE = "extra_notify_type";
    public static final String EXTRA_SWITCH_INFO = "extyra_switch_info";
    public static final String INTENT_OPLUS_APP_SWITCH = "oplus.intent.action.APP_SWITCH";
    public static final int NOTIFY_TYPE_ACTIVITY_ENTER = 3;
    public static final int NOTIFY_TYPE_ACTIVITY_EXIT = 4;
    public static final int NOTIFY_TYPE_APP_ENTER = 1;
    public static final int NOTIFY_TYPE_APP_EXIT = 2;
    public static final String OPLUS_APP_SWITCH_SAFE_PERMISSIONS = "oplus.permission.OPLUS_COMPONENT_SAFE";
    private static final String TAG = "OplusAppSwitchManager";
    private static OplusAppSwitchManager sInstance;
    private final Map<OnAppSwitchObserver, IOplusAppSwitchObserver> mAppSwitchObservers = new ArrayMap();
    private OplusActivityTaskManager mOAms = new OplusActivityTaskManager();

    /* loaded from: classes4.dex */
    public interface OnAppSwitchObserver {
        void onActivityEnter(OplusAppEnterInfo oplusAppEnterInfo);

        void onActivityExit(OplusAppExitInfo oplusAppExitInfo);

        void onAppEnter(OplusAppEnterInfo oplusAppEnterInfo);

        void onAppExit(OplusAppExitInfo oplusAppExitInfo);
    }

    public static OplusAppSwitchManager getInstance() {
        if (sInstance == null) {
            synchronized (OplusAppSwitchManager.class) {
                if (sInstance == null) {
                    sInstance = new OplusAppSwitchManager();
                }
            }
        }
        return sInstance;
    }

    private OplusAppSwitchManager() {
    }

    public boolean registerAppSwitchObserver(Context context, OnAppSwitchObserver observer, OplusAppSwitchConfig config) {
        OplusActivityTaskManager oplusActivityTaskManager;
        if (DEBUG) {
            Log.i(TAG, "registerAppSwitchObserver observer = " + observer + " config = " + config);
        }
        if (observer == null || context == null) {
            return false;
        }
        synchronized (this.mAppSwitchObservers) {
            if (this.mAppSwitchObservers.get(observer) != null) {
                Log.e(TAG, "already register before");
                return false;
            }
            if (config == null) {
                config = new OplusAppSwitchConfig();
            }
            config.observerFingerPrint = observer.hashCode();
            OnAppSwitchObserverDelegate delegate = new OnAppSwitchObserverDelegate(observer);
            try {
                oplusActivityTaskManager = this.mOAms;
            } catch (RemoteException e) {
                Log.e(TAG, "registerAppSwitchObserver remoteException ");
                e.printStackTrace();
            }
            if (oplusActivityTaskManager == null) {
                return false;
            }
            boolean result = oplusActivityTaskManager.registerAppSwitchObserver(context.getPackageName(), delegate, config);
            if (result) {
                this.mAppSwitchObservers.put(observer, delegate);
            }
            return result;
        }
    }

    public boolean unregisterAppSwitchObserver(Context context, OnAppSwitchObserver observer) {
        if (DEBUG) {
            Log.i(TAG, "unRegisterAppSwitchObserver observer = " + observer);
        }
        if (observer == null || context == null) {
            return false;
        }
        synchronized (this.mAppSwitchObservers) {
            IOplusAppSwitchObserver delegate = this.mAppSwitchObservers.get(observer);
            if (delegate != null) {
                try {
                    if (this.mOAms != null) {
                        this.mAppSwitchObservers.remove(observer);
                        OplusAppSwitchConfig config = new OplusAppSwitchConfig();
                        config.observerFingerPrint = observer.hashCode();
                        return this.mOAms.unregisterAppSwitchObserver(context.getPackageName(), config);
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
    private class OnAppSwitchObserverDelegate extends IOplusAppSwitchObserver.Stub {
        private final OnAppSwitchObserver mObserver;

        public OnAppSwitchObserverDelegate(OnAppSwitchObserver observer) {
            this.mObserver = observer;
        }

        @Override // com.oplus.app.IOplusAppSwitchObserver
        public void onActivityEnter(OplusAppEnterInfo info) {
            if (OplusAppSwitchManager.DEBUG) {
                Log.d(OplusAppSwitchManager.TAG, "onActivityEnter info = " + info);
            }
            this.mObserver.onActivityEnter(info);
        }

        @Override // com.oplus.app.IOplusAppSwitchObserver
        public void onActivityExit(OplusAppExitInfo info) {
            if (OplusAppSwitchManager.DEBUG) {
                Log.d(OplusAppSwitchManager.TAG, "onActivityExit info = " + info);
            }
            this.mObserver.onActivityExit(info);
        }

        @Override // com.oplus.app.IOplusAppSwitchObserver
        public void onAppEnter(OplusAppEnterInfo info) {
            if (OplusAppSwitchManager.DEBUG) {
                Log.d(OplusAppSwitchManager.TAG, "onAppEnter info = " + info);
            }
            this.mObserver.onAppEnter(info);
        }

        @Override // com.oplus.app.IOplusAppSwitchObserver
        public void onAppExit(OplusAppExitInfo info) {
            if (OplusAppSwitchManager.DEBUG) {
                Log.d(OplusAppSwitchManager.TAG, "onAppExit info = " + info);
            }
            this.mObserver.onAppExit(info);
        }
    }
}
