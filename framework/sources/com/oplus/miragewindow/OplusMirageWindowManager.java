package com.oplus.miragewindow;

import android.app.OplusActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.util.Slog;
import android.view.Surface;
import com.oplus.miragewindow.IOplusMirageSessionCallback;
import java.util.List;
/* loaded from: classes4.dex */
public class OplusMirageWindowManager {
    public static final int DISPLAY_ID = 2020;
    public static final String DISPLAY_NAME = "Mirage_display";
    public static final int FLAG_MASK = 65535;
    public static final int FLAG_POWER_SAVE = 1;
    public static final int FLAG_PRIVACY_PROTECTION = 16;
    public static final String IS_DEFAULT = "is_default";
    public static final int MODE_MASK = -65536;
    public static final int MODE_MIRROR_CAST = 16777216;
    public static final int MODE_SINGLE_APP_CAST = 33554432;
    public static final float Mirage_CORNER_RADIUS = 30.0f;
    private static final String TAG = "OplusMirageWindowManager";
    private static volatile OplusMirageWindowManager sInstance;
    private OplusActivityManager mAms;
    private IOplusMirageWindowSession mSession;

    public static OplusMirageWindowManager getInstance() {
        if (sInstance == null) {
            synchronized (OplusMirageWindowManager.class) {
                if (sInstance == null) {
                    sInstance = new OplusMirageWindowManager();
                }
            }
        }
        return sInstance;
    }

    private OplusMirageWindowManager() {
        OplusActivityManager oplusActivityManager = new OplusActivityManager();
        this.mAms = oplusActivityManager;
        try {
            this.mSession = oplusActivityManager.createMirageWindowSession(new IOplusMirageSessionCallback.Stub() { // from class: com.oplus.miragewindow.OplusMirageWindowManager.1
            });
        } catch (RemoteException e) {
            Log.e(TAG, "create session remoteException ");
        }
    }

    public void startMirageWindowMode(ComponentName cpnName, int taskId, int mode, Bundle options) {
        try {
            this.mAms.startMirageWindowMode(cpnName, taskId, mode, options);
        } catch (RemoteException e) {
            Log.e(TAG, "startMirageWindowMode remoteException ");
            e.printStackTrace();
        }
    }

    public int startMirageWindowMode(Intent intent, Bundle options) {
        try {
            return this.mAms.startMirageWindowMode(intent, options);
        } catch (RemoteException e) {
            Log.e(TAG, "startMirageWindowMode remoteException ");
            e.printStackTrace();
            return -1;
        }
    }

    public boolean isMirageWindowShow() {
        try {
            return this.mAms.isMirageWindowShow();
        } catch (RemoteException e) {
            Log.e(TAG, "isMirageWindowShow remoteException ");
            e.printStackTrace();
            return false;
        }
    }

    public void stopMirageWindowMode() {
        try {
            this.mAms.stopMirageWindowMode();
        } catch (RemoteException e) {
            Log.e(TAG, "stopMirageWindowMode remoteException ");
            e.printStackTrace();
        }
    }

    public void stopMirageWindowMode(Bundle options) {
        try {
            this.mAms.stopMirageWindowMode(options);
        } catch (RemoteException e) {
            Log.e(TAG, "stopMirageWindowMode remoteException ");
            e.printStackTrace();
        }
    }

    public void setMirageDisplaySurfaceById(int displayId, Surface surface) {
        try {
            this.mAms.setMirageDisplaySurfaceById(displayId, surface);
        } catch (RemoteException e) {
            Log.e(TAG, "setMirageDisplaySurfaceById remoteException ");
            e.printStackTrace();
        }
    }

    public void setMirageDisplaySurfaceByMode(int mode, Surface surface) {
        try {
            this.mAms.setMirageDisplaySurfaceByMode(mode, surface);
        } catch (RemoteException e) {
            Log.e(TAG, "setMirageDisplaySurfaceByMode remoteException ");
            e.printStackTrace();
        }
    }

    public int getMirageDisplayCastMode(int displayId) {
        try {
            return this.mAms.getMirageDisplayCastMode(displayId);
        } catch (RemoteException e) {
            Log.e(TAG, "getMirageDisplayCastMode remoteException ");
            e.printStackTrace();
            return -1;
        }
    }

    public void expandToFullScreen() {
        try {
            this.mAms.expandToFullScreen();
        } catch (RemoteException e) {
            Log.e(TAG, "expandTofullscreen remoteException ");
            e.printStackTrace();
        }
    }

    public void setMirageWindowSilent(String pkgName) {
        try {
            this.mAms.setMirageWindowSilent(pkgName);
        } catch (RemoteException e) {
            Log.e(TAG, "setMirageWindowSilent remoteException ");
            e.printStackTrace();
        }
    }

    public int createMirageDisplay(Surface surface) {
        try {
            return this.mAms.createMirageDisplay(surface);
        } catch (RemoteException e) {
            Slog.e(TAG, "Failed to createMirageDisplay: remoteException");
            e.printStackTrace();
            return -1;
        }
    }

    public boolean isSupportMirageWindowMode() {
        try {
            return this.mAms.isSupportMirageWindowMode();
        } catch (RemoteException e) {
            Log.e(TAG, "isSupportMirageWindowMode remoteException ");
            e.printStackTrace();
            return false;
        }
    }

    public OplusMirageWindowInfo getMirageWindowInfo() {
        try {
            return this.mAms.getMirageWindowInfo();
        } catch (RemoteException e) {
            Log.e(TAG, "getMirageWindowInfo remoteException ");
            e.printStackTrace();
            return new OplusMirageWindowInfo();
        }
    }

    public boolean registerMirageWindowObserver(IOplusMirageWindowObserver observer) {
        try {
            return this.mAms.registerMirageWindowObserver(observer);
        } catch (RemoteException e) {
            Slog.e(TAG, "Failed to registerMirageWindowObserver: remoteException");
            e.printStackTrace();
            return false;
        }
    }

    public boolean unregisterMirageWindowObserver(IOplusMirageWindowObserver observer) {
        try {
            return this.mAms.unregisterMirageWindowObserver(observer);
        } catch (RemoteException e) {
            Slog.e(TAG, "Failed to unregisterMirageWindowObserver: remoteException");
            e.printStackTrace();
            return false;
        }
    }

    public boolean registerMirageDisplayObserver(IOplusMirageDisplayObserver observer) {
        try {
            return this.mAms.registerMirageDisplayObserver(observer);
        } catch (RemoteException e) {
            Slog.e(TAG, "Failed to registerMirageDisplayObserver: remoteException");
            e.printStackTrace();
            return false;
        }
    }

    public boolean unregisterMirageDisplayObserver(IOplusMirageDisplayObserver observer) {
        try {
            return this.mAms.unregisterMirageDisplayObserver(observer);
        } catch (RemoteException e) {
            Slog.e(TAG, "Failed to unregisterMirageDisplayObserver: remoteException");
            e.printStackTrace();
            return false;
        }
    }

    public void feedbackUserSelection(int eventId, int selection, Bundle extension) {
        try {
            this.mAms.feedbackUserSelection(eventId, selection, extension);
        } catch (RemoteException e) {
            Log.e(TAG, "feedbackUserSelection remoteException ");
            e.printStackTrace();
        }
    }

    public boolean updateMirageWindowCastFlag(int castFlag) {
        return updateMirageWindowCastFlag(castFlag, null);
    }

    public boolean updateMirageWindowCastFlag(int castFlag, Bundle options) {
        try {
            return this.mAms.updateMirageWindowCastFlag(castFlag, options);
        } catch (RemoteException e) {
            Log.e(TAG, "updateMirageWindowCastFlag remoteException ");
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePrivacyProtectionList(List<String> name, boolean append) {
        try {
            return this.mAms.updatePrivacyProtectionList(name, append);
        } catch (RemoteException e) {
            Log.e(TAG, "updatePrivacyProtectionList remoteException ");
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePrivacyProtectionList(List<String> name, boolean append, boolean isDefault, Bundle options) {
        try {
            return this.mAms.updatePrivacyProtectionList(name, append, isDefault, options);
        } catch (RemoteException e) {
            Log.e(TAG, "updatePrivacyProtectionList default remoteException ");
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCarModeMultiLaunchWhiteList(String list) {
        try {
            return this.mAms.updateCarModeMultiLaunchWhiteList(list);
        } catch (RemoteException e) {
            Log.e(TAG, "updateCarModeMultiLaunchWhiteList default remoteException ");
            e.printStackTrace();
            return false;
        }
    }

    public void addCastScreenState(OplusCastScreenState state) {
        IOplusMirageWindowSession iOplusMirageWindowSession;
        synchronized (OplusMirageWindowManager.class) {
            try {
                try {
                    iOplusMirageWindowSession = this.mSession;
                } catch (RemoteException e) {
                    Log.e(TAG, "addCastScreenState remoteException ");
                }
                if (!(iOplusMirageWindowSession == null || state == null)) {
                    iOplusMirageWindowSession.addCastScreenState(state);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void removeCastScreenState() {
        IOplusMirageWindowSession iOplusMirageWindowSession;
        synchronized (OplusMirageWindowManager.class) {
            try {
                iOplusMirageWindowSession = this.mSession;
            } catch (RemoteException e) {
                Log.e(TAG, "removeCastScreenState remoteException ");
            }
            if (iOplusMirageWindowSession != null) {
                iOplusMirageWindowSession.removeCastScreenState();
            }
        }
    }

    public List<OplusCastScreenState> getCastScreenStateList() {
        synchronized (OplusMirageWindowManager.class) {
            try {
                try {
                    IOplusMirageWindowSession iOplusMirageWindowSession = this.mSession;
                    if (iOplusMirageWindowSession == null) {
                        return null;
                    }
                    return iOplusMirageWindowSession.getCastScreenStateList();
                } catch (RemoteException e) {
                    Log.e(TAG, "getCastScreenStateList remoteException ");
                    return null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean registerCastScreenStateObserver(IOplusCastScreenStateObserver observer) {
        try {
            IOplusMirageWindowSession iOplusMirageWindowSession = this.mSession;
            if (!(iOplusMirageWindowSession == null || observer == null)) {
                return iOplusMirageWindowSession.registerCastScreenStateObserver(observer);
            }
            return false;
        } catch (RemoteException e) {
            Slog.e(TAG, "Failed to registerCastScreenStateObserver: remoteException");
            e.printStackTrace();
            return false;
        }
    }

    public boolean unregisterCastScreenStateObserver(IOplusCastScreenStateObserver observer) {
        try {
            IOplusMirageWindowSession iOplusMirageWindowSession = this.mSession;
            if (!(iOplusMirageWindowSession == null || observer == null)) {
                return iOplusMirageWindowSession.unregisterCastScreenStateObserver(observer);
            }
            return false;
        } catch (RemoteException e) {
            Slog.e(TAG, "Failed to unregisterCastScreenStateObserver: remoteException");
            e.printStackTrace();
            return false;
        }
    }

    public void setMirageDisplayId(int id) {
        try {
            if (this.mSession != null) {
                Slog.v(TAG, "setMirageDisplayId: " + id);
                this.mSession.setMirageDisplayId(id);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "setMirageDisplayId remoteException ");
        }
    }

    public int getDisplayIdForPackageName(String packageName) {
        try {
            return this.mAms.getGetDisplayIdForPackageName(packageName);
        } catch (RemoteException e) {
            Slog.d(TAG, "Failed to getDisplayIdForPackageName: remoteException");
            e.printStackTrace();
            return 0;
        }
    }

    public boolean rebindDisplayIfNeeded(int castDisplayId, int mirageDisplayId) {
        try {
            return this.mAms.rebindDisplayIfNeeded(castDisplayId, mirageDisplayId);
        } catch (RemoteException e) {
            Slog.d(TAG, "Failed to rebindDisplayIfNeeded: remoteException");
            e.printStackTrace();
            return false;
        }
    }
}
