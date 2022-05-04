package android.hardware.cabc;

import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.util.Log;
import com.android.internal.cabc.ICabcManager;
/* loaded from: classes.dex */
public class CabcManager {
    private static final String CABC_SERVICE = "cabc";
    public static final int LOCK_MODE = 9;
    public static final int OFF_MODE = 0;
    public static final int PIC_MODE = 2;
    private static final String PROP_LOG_CABC = "persist.sys.assert.panic";
    private static final String TAG = "CabcManager";
    public static final int UI_MODE = 1;
    public static final int UNLOCK_MODE = 8;
    public static final int VIDEO_MODE = 3;
    private int mMode = 3;
    private ICabcManager mService;
    private static boolean DEBUG = false;
    private static CabcManager sCabcManagerInstance = null;

    private CabcManager() {
        init();
    }

    private void init() {
        DEBUG = SystemProperties.getBoolean(PROP_LOG_CABC, false);
        if (this.mService == null) {
            IBinder b = ServiceManager.getService(CABC_SERVICE);
            this.mService = ICabcManager.Stub.asInterface(b);
        }
    }

    public static CabcManager getCabcManagerInstance() {
        if (sCabcManagerInstance == null) {
            sCabcManagerInstance = new CabcManager();
        }
        return sCabcManagerInstance;
    }

    public void setMode(int mode) {
        if (DEBUG) {
            Log.d(TAG, "setMode, new mode:" + mode);
        }
        try {
            ICabcManager iCabcManager = this.mService;
            if (iCabcManager != null) {
                iCabcManager.setMode(mode);
            } else {
                Log.e(TAG, "setMode failed: mService is null!");
            }
        } catch (RemoteException e) {
            throw new RuntimeException("system server dead ?", e);
        }
    }

    public int getMode() {
        try {
            ICabcManager iCabcManager = this.mService;
            if (iCabcManager != null) {
                return iCabcManager.getMode();
            }
            Log.e(TAG, "getMode failed: mService is null!");
            return 3;
        } catch (RemoteException e) {
            throw new RuntimeException("system server dead ?", e);
        }
    }

    public void closeCabc() {
        if (DEBUG) {
            Log.d(TAG, "closeCabc.");
        }
        try {
            ICabcManager iCabcManager = this.mService;
            if (iCabcManager != null) {
                iCabcManager.closeCabc();
            } else {
                Log.e(TAG, "closeCabc failed: mService is null!");
            }
        } catch (RemoteException e) {
            throw new RuntimeException("system server dead ?", e);
        }
    }

    public void openCabc() {
        if (DEBUG) {
            Log.d(TAG, "openCabc.");
        }
        try {
            ICabcManager iCabcManager = this.mService;
            if (iCabcManager != null) {
                iCabcManager.openCabc();
            } else {
                Log.e(TAG, "openCabc failed: mService is null!");
            }
        } catch (RemoteException e) {
            throw new RuntimeException("system server dead ?", e);
        }
    }

    public void closeCabcForever(boolean enable) {
        if (DEBUG) {
            Log.d(TAG, "closeCabcForever = " + enable);
        }
        try {
            ICabcManager iCabcManager = this.mService;
            if (iCabcManager != null) {
                iCabcManager.closeCabcForever(enable);
            } else {
                Log.e(TAG, "lockCabcMode failed: mService is null!");
            }
        } catch (RemoteException e) {
            throw new RuntimeException("system server dead ?", e);
        }
    }
}
