package com.oplus.confinemode;

import android.app.OplusActivityManager;
import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.util.ArrayMap;
import android.util.Log;
import com.oplus.confinemode.IOplusConfineModeObserver;
import java.util.List;
import java.util.Map;
/* loaded from: classes4.dex */
public class OplusConfineModeManager {
    private static final boolean DEBUG = SystemProperties.getBoolean("persist.sys.assert.panic", false);
    public static final int OPLUS_CONFINE_MODE_CHILDREN = 2;
    public static final int OPLUS_CONFINE_MODE_DRIVE = 1;
    public static final int OPLUS_CONFINE_MODE_FOCUS = 4;
    public static final int OPLUS_CONFINE_MODE_GAME_FOCUS = 8;
    public static final int OPLUS_CONFINE_MODE_LEARN = 16;
    public static final int OPLUS_CONFINE_MODE_NORMAL = 0;
    public static final int OPLUS_PERMIT_TYPE_APPEND = 2;
    public static final int OPLUS_PERMIT_TYPE_CLEAR = 0;
    public static final int OPLUS_PERMIT_TYPE_CPN = 8;
    public static final int OPLUS_PERMIT_TYPE_PKG = 4;
    public static final int OPLUS_PERMIT_TYPE_REPLACE = 1;
    private static final String TAG = "OplusConfineModeManager";
    private static volatile OplusConfineModeManager sInstance;
    private final Map<ConfineModeObserver, IOplusConfineModeObserver> mObservers = new ArrayMap();
    private OplusActivityManager mOAms = new OplusActivityManager();

    /* loaded from: classes4.dex */
    public interface ConfineModeObserver {
        void onChange(int i, int i2, int i3);
    }

    private OplusConfineModeManager() {
    }

    public static OplusConfineModeManager getInstance() {
        if (sInstance == null) {
            synchronized (OplusConfineModeManager.class) {
                if (sInstance == null) {
                    sInstance = new OplusConfineModeManager();
                }
            }
        }
        return sInstance;
    }

    public void setConfineMode(int mode, boolean on) {
        try {
            if (DEBUG) {
                Log.v(TAG, "setConfineMode: mode=" + mode + ", on=" + on);
            }
            this.mOAms.setConfineMode(mode, on);
        } catch (RemoteException e) {
            Log.e(TAG, "setConfineMode remoteException:" + e);
        }
    }

    public int getConfineMode() {
        try {
            if (DEBUG) {
                Log.v(TAG, "getConfineMode, caller:" + Binder.getCallingUid());
            }
            return this.mOAms.getConfineMode();
        } catch (RemoteException e) {
            Log.e(TAG, "getConfineMode remoteException:" + e);
            return 0;
        }
    }

    public void setPermitList(int mode, int type, List<String> permits, boolean isMultiApp) {
        try {
            if (DEBUG) {
                Log.v(TAG, "setPermitList: mode=" + mode + ", type=" + type + ", isMultiApp=" + isMultiApp);
            }
            this.mOAms.setPermitList(mode, type, permits, isMultiApp);
        } catch (RemoteException e) {
            Log.e(TAG, "setPermitList remoteException:" + e);
        }
    }

    public boolean registerConfineModeObserver(Context context, ConfineModeObserver observer) {
        OplusActivityManager oplusActivityManager;
        if (DEBUG) {
            Log.d(TAG, "registerConfineModeObserver, context: " + context + ", observer: " + observer);
        }
        if (context == null || observer == null) {
            return false;
        }
        synchronized (this.mObservers) {
            if (this.mObservers.get(observer) != null) {
                Log.e(TAG, "already register before");
                return false;
            }
            ConfineModeObserverDelegate delegate = new ConfineModeObserverDelegate(observer);
            try {
                oplusActivityManager = this.mOAms;
            } catch (RemoteException e) {
                Log.e(TAG, "registerConfineModeObserver failed, err: " + e);
            }
            if (oplusActivityManager == null) {
                return false;
            }
            boolean result = oplusActivityManager.registerConfineModeObserver(delegate);
            if (result) {
                this.mObservers.put(observer, delegate);
            }
            return result;
        }
    }

    public boolean unregisterConfineModeObserver(Context context, ConfineModeObserver observer) {
        if (DEBUG) {
            Log.d(TAG, "unregisterConfineModeObserver, context: " + context + ", observer: " + observer);
        }
        if (context == null || observer == null) {
            return false;
        }
        synchronized (this.mObservers) {
            IOplusConfineModeObserver delegate = this.mObservers.get(observer);
            if (delegate != null) {
                try {
                    OplusActivityManager oplusActivityManager = this.mOAms;
                    if (oplusActivityManager != null) {
                        boolean result = oplusActivityManager.unregisterConfineModeObserver(delegate);
                        if (result) {
                            this.mObservers.remove(observer);
                        }
                        return result;
                    }
                } catch (RemoteException e) {
                    Log.e(TAG, "unregisterConfineModeObserver failed, err: " + e);
                }
            }
            return false;
        }
    }

    /* loaded from: classes4.dex */
    private class ConfineModeObserverDelegate extends IOplusConfineModeObserver.Stub {
        private final ConfineModeObserver mObserver;

        public ConfineModeObserverDelegate(ConfineModeObserver observer) {
            this.mObserver = observer;
        }

        @Override // com.oplus.confinemode.IOplusConfineModeObserver
        public void onChange(int oldMode, int newMode, int userId) {
            if (OplusConfineModeManager.DEBUG) {
                Log.d(OplusConfineModeManager.TAG, "confine mode changed, oldMode: " + oldMode + ", newMode: " + newMode + ", userId: " + userId);
            }
            this.mObserver.onChange(oldMode, newMode, userId);
        }
    }
}
