package android.os;

import android.util.Log;
import vendor.oplus.hardware.lmvibrator.V1_0.ILinearMotorVibrator;
/* loaded from: classes2.dex */
public class LinearMotorVibratorManager {
    private static final String TAG = "LinearMotorVibratorManager";
    private static ILinearMotorVibrator sLinearMotorVibrateService = null;

    private static ILinearMotorVibrator getLinearMotorVibrateService() {
        if (sLinearMotorVibrateService == null) {
            try {
                sLinearMotorVibrateService = ILinearMotorVibrator.getService();
            } catch (Exception e) {
                Log.e(TAG, "Failed to get linear motor vibrator interface", e);
            }
        }
        return sLinearMotorVibrateService;
    }

    public static void turnOffLinearMotorVibrator() {
        try {
            ILinearMotorVibrator service = getLinearMotorVibrateService();
            if (service != null) {
                service.linearmotorVibratorOff();
            }
        } catch (RemoteException e) {
            Log.e(TAG, "turnOffLinearMotorVibrator failed.", e);
        }
    }

    public static void turnOnLinearmotorVibrator(int waveformId, short amplitude, boolean isRtpMode) {
        try {
            ILinearMotorVibrator service = getLinearMotorVibrateService();
            if (service != null) {
                service.linearmotorVibratorOn(waveformId, amplitude, isRtpMode);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "turnOnLinearmotorVibrator failed.", e);
        }
    }

    public static int getVibratorStatus() {
        try {
            ILinearMotorVibrator service = getLinearMotorVibrateService();
            if (service == null) {
                return -1;
            }
            int vibrationStatus = service.getStatus();
            Log.e(TAG, "getVibratorStatus service.getStatus() = " + vibrationStatus);
            return vibrationStatus;
        } catch (RemoteException e) {
            Log.e(TAG, "getVibratorStatus failed.", e);
            return -1;
        }
    }

    public static void setVibratorStrength(int strength) {
        try {
            ILinearMotorVibrator service = getLinearMotorVibrateService();
            if (service != null) {
                service.setVmax(strength);
                Log.e(TAG, "setVibratorStrength service.setVmax() = " + strength);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "setVibratorStrength failed.", e);
        }
    }

    public static int getVibratorTouchStyle() {
        try {
            ILinearMotorVibrator service = getLinearMotorVibrateService();
            if (service == null) {
                return -1;
            }
            int vibrationStyle = service.getVibratorTouchStyle();
            Log.e(TAG, "getVibratorStatus service.getVibratorTouchStyle() = " + vibrationStyle);
            return vibrationStyle;
        } catch (RemoteException e) {
            Log.e(TAG, "getVibratorTouchStyle failed.", e);
            return -1;
        }
    }

    public static void setVibratorTouchStyle(int style) {
        try {
            ILinearMotorVibrator service = getLinearMotorVibrateService();
            if (service != null) {
                service.setVibratorTouchStyle(style);
                Log.e(TAG, "setVibratorTouchStyle service.setVibratorTouchStyle() = " + style);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "setVibratorTouchStyle failed.", e);
        }
    }
}
