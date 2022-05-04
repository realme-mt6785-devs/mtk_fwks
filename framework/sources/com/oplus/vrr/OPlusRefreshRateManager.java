package com.oplus.vrr;

import android.os.Bundle;
import android.os.IBinder;
import android.os.OplusPropertyList;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Singleton;
import android.util.Slog;
import com.oplus.uifirst.IOplusUIFirstManager;
import com.oplus.vrr.IOPlusRefreshRate;
import com.oplus.vrr.IOPlusRefreshRateManager;
import java.util.List;
/* loaded from: classes4.dex */
public class OPlusRefreshRateManager implements IOPlusRefreshRateManager {
    public static final int ADFR_COVERWIN_WHITELIST = 0;
    public static final int ADFR_GAME_LIST = 1;
    private static final boolean DEBUG = true;
    public static final String OPLUS_VRR_SERVICE = "oplus_vrr_service";
    private static final Singleton<IOPlusRefreshRate> SCREATOR = new Singleton<IOPlusRefreshRate>() { // from class: com.oplus.vrr.OPlusRefreshRateManager.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.util.Singleton
        public IOPlusRefreshRate create() {
            try {
                IBinder b = ServiceManager.getService(OPlusRefreshRateManager.OPLUS_VRR_SERVICE);
                if (b == null) {
                    Slog.d(OPlusRefreshRateManager.TAG, "can't get service binder: IOPlusRefreshRate");
                }
                IOPlusRefreshRate ret = IOPlusRefreshRate.Stub.asInterface(b);
                if (ret == null) {
                    Slog.d(OPlusRefreshRateManager.TAG, "can't get service interface: IOPlusRefreshRate");
                }
                return ret;
            } catch (Exception e) {
                Slog.w(OPlusRefreshRateManager.TAG, "can't get service interface: IOPlusRefreshRate");
                e.printStackTrace();
                return null;
            }
        }
    };
    private static final String TAG = "VRR [OPlusRefreshRateManager]";

    public static boolean hasADFRFeature() {
        boolean ret = !OplusPropertyList.ADFR_ENABLED.equals("0");
        Slog.i(TAG, "hasADFRFeature return " + ret);
        return ret;
    }

    public static String getADFRVersion() {
        return OplusPropertyList.ADFR_ENABLED;
    }

    public static boolean hasVRRFeature() {
        boolean ret = OplusPropertyList.VRR_ENABLED.equals(IOplusUIFirstManager.APP_START_ANIMATION);
        Slog.i(TAG, "hasVRRFeature return " + ret);
        return ret;
    }

    public static IOPlusRefreshRate getDefault() {
        return SCREATOR.get();
    }

    public static IOPlusRefreshRate getOPlusRefreshRateService() {
        try {
            IBinder b = ServiceManager.getService(OPLUS_VRR_SERVICE);
            if (b == null) {
                Slog.d(TAG, "can't get service binder: IOPlusRefreshRate");
            }
            IOPlusRefreshRate ret = IOPlusRefreshRate.Stub.asInterface(b);
            if (ret == null) {
                Slog.d(TAG, "can't get service interface: IOPlusRefreshRate");
            }
            return ret;
        } catch (Exception e) {
            Slog.w(TAG, "can't get service interface: IOPlusRefreshRate");
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.oplus.vrr.IOPlusRefreshRateManager
    public void setExternalRefreshRateStatus(int status) {
        IOPlusRefreshRate service = getDefault();
        if (service != null) {
            try {
                service.setExternalRefreshRateStatus(status);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Slog.w(TAG, "OPlusRefreshRate disconnected");
        }
    }

    @Override // com.oplus.vrr.IOPlusRefreshRateManager
    public void updateDisplayModes(long physicalDisplayId) {
        IOPlusRefreshRate service = getDefault();
        if (service != null) {
            try {
                service.updateDisplayModes(physicalDisplayId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Slog.w(TAG, "OPlusRefreshRate disconnected");
        }
    }

    @Override // com.oplus.vrr.IOPlusRefreshRateManager
    public int findDisplayModeIdByPolicy(int policy, int displayId, int baseModeId) {
        IOPlusRefreshRate service = getDefault();
        if (service != null) {
            try {
                int policyMode = service.findDisplayModeIdByPolicy(policy, displayId, baseModeId);
                return policyMode;
            } catch (RemoteException e) {
                e.printStackTrace();
                return baseModeId;
            }
        } else {
            Slog.w(TAG, "OPlusRefreshRate disconnected");
            return baseModeId;
        }
    }

    @Override // com.oplus.vrr.IOPlusRefreshRateManager
    public void screenStateChange(int state) {
        IOPlusRefreshRate service = getDefault();
        if (service != null) {
            try {
                service.screenStateChange(state);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Slog.w(TAG, "OPlusRefreshRate disconnected");
        }
    }

    @Override // com.oplus.vrr.IOPlusRefreshRateManager
    public void notifyNitsChange(float nits) {
        IOPlusRefreshRate service = getDefault();
        if (service != null) {
            try {
                service.notifyNitsChange(nits);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Slog.w(TAG, "OPlusRefreshRate disconnected");
        }
    }

    @Override // com.oplus.vrr.IOPlusRefreshRateManager
    public void notifyBrightnessChange(float brightness) {
        IOPlusRefreshRate service = getDefault();
        if (service != null) {
            try {
                service.notifyBrightnessChange(brightness);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Slog.w(TAG, "OPlusRefreshRate disconnected");
        }
    }

    @Override // com.oplus.vrr.IOPlusRefreshRateManager
    public void setRefreshRatePolicy(int displayId, float rate, int policy, boolean statusOn) {
        IOPlusRefreshRate service = getDefault();
        if (service != null) {
            try {
                service.setRefreshRatePolicy(displayId, rate, policy, statusOn);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Slog.w(TAG, "OPlusRefreshRate disconnected");
        }
    }

    @Override // com.oplus.vrr.IOPlusRefreshRateManager
    public int getRefreshRatePolicy(float rate) {
        IOPlusRefreshRate service = getOPlusRefreshRateService();
        if (service != null) {
            try {
                int policy = service.getRefreshRatePolicy(rate);
                return policy;
            } catch (RemoteException e) {
                e.printStackTrace();
                return 0;
            }
        } else {
            Slog.w(TAG, "OPlusRefreshRate disconnected");
            return 0;
        }
    }

    @Override // com.oplus.vrr.IOPlusRefreshRateManager
    public boolean isOAMode() {
        IOPlusRefreshRate service = getDefault();
        if (service != null) {
            try {
                boolean isOAMode = service.isOAMode();
                return isOAMode;
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            Slog.w(TAG, "OPlusRefreshRate disconnected");
            return false;
        }
    }

    @Override // com.oplus.vrr.IOPlusRefreshRateManager
    public int getModeType(int modeId) {
        IOPlusRefreshRate service = getDefault();
        if (service != null) {
            try {
                int modeType = service.getModeType(modeId);
                return modeType;
            } catch (RemoteException e) {
                e.printStackTrace();
                return 0;
            }
        } else {
            Slog.w(TAG, "OPlusRefreshRate disconnected");
            return 0;
        }
    }

    @Override // com.oplus.vrr.IOPlusRefreshRateManager
    public void setLowFreqVideo(boolean enable) {
        IOPlusRefreshRate service = getDefault();
        if (service != null) {
            try {
                service.setLowFreqVideo(enable);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Slog.w(TAG, "OPlusRefreshRate disconnected");
        }
    }

    @Override // com.oplus.vrr.IOPlusRefreshRateManager
    public float getPreferredFrameRate(String pkgName, String winName) {
        IOPlusRefreshRate service = getDefault();
        if (service != null) {
            try {
                float frameRate = service.getPreferredFrameRate(pkgName, winName);
                return frameRate;
            } catch (RemoteException e) {
                e.printStackTrace();
                return -1.0f;
            }
        } else {
            Slog.w(TAG, "OPlusRefreshRate disconnected");
            return -1.0f;
        }
    }

    @Override // com.oplus.vrr.IOPlusRefreshRateManager
    public boolean hasFlickerRisk() {
        IOPlusRefreshRate service = getDefault();
        if (service != null) {
            try {
                boolean hasFlickerRisk = service.hasFlickerRisk();
                return hasFlickerRisk;
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            Slog.w(TAG, "OPlusRefreshRate disconnected");
            return false;
        }
    }

    @Override // com.oplus.vrr.IOPlusRefreshRateManager
    public void updateAccelerationPkgName(String pkgName, int targetFps, int currentFps) {
        IOPlusRefreshRate service = getDefault();
        if (service != null) {
            try {
                service.updateAccelerationPkgName(pkgName, targetFps, currentFps);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Slog.w(TAG, "OPlusRefreshRate disconnected");
        }
    }

    @Override // com.oplus.vrr.IOPlusRefreshRateManager
    public void setTgpaGameData(Bundle bundle) {
        IOPlusRefreshRate service = getDefault();
        if (service != null) {
            try {
                service.setTgpaGameData(bundle);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Slog.w(TAG, "OPlusRefreshRate disconnected");
        }
    }

    @Override // com.oplus.vrr.IOPlusRefreshRateManager
    public boolean isGameAccelerationScene() {
        IOPlusRefreshRate service = getDefault();
        if (service != null) {
            try {
                boolean isGameAccelerationScene = service.isGameAccelerationScene();
                return isGameAccelerationScene;
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            Slog.w(TAG, "OPlusRefreshRate disconnected");
            return false;
        }
    }

    @Override // com.oplus.vrr.IOPlusRefreshRateManager
    public boolean isWhiteListGame(String pkgName) {
        IOPlusRefreshRate service = getDefault();
        if (service != null) {
            try {
                boolean isWhiteListGame = service.isWhiteListGame(pkgName);
                return isWhiteListGame;
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            Slog.w(TAG, "OPlusRefreshRate disconnected");
            return false;
        }
    }

    @Override // com.oplus.vrr.IOPlusRefreshRateManager
    public List<String> getList(int listType) {
        IOPlusRefreshRate service = getDefault();
        if (service != null) {
            try {
                List<String> list = service.getList(listType);
                return list;
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            Slog.w(TAG, "OPlusRefreshRate disconnected");
            return null;
        }
    }

    @Override // com.oplus.vrr.IOPlusRefreshRateManager
    public boolean setFrameRate(float frameRate, String windowTile, String pkgName, int pid) {
        IOPlusRefreshRate service = getDefault();
        if (service != null) {
            try {
                boolean success = service.setFrameRate(frameRate, windowTile, pkgName, pid);
                return success;
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            Slog.w(TAG, "OPlusRefreshRate disconnected");
            return false;
        }
    }

    @Override // com.oplus.vrr.IOPlusRefreshRateManager
    public void requestRefreshRate(IOPlusRefreshRateManager.DisplayRefreshRateRequest request) {
        Slog.d(TAG, "VRR requestRefreshRate: " + request.toString());
        setRefreshRatePolicy(request.displayId, request.requestRefreshRate, request.policy, request.statusOn);
    }
}
