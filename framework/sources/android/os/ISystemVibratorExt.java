package android.os;

import android.content.Context;
/* loaded from: classes2.dex */
public interface ISystemVibratorExt {
    default void init(Context context) {
    }

    default boolean doVibrate(int uid, String opPkg, VibrationEffect effect) {
        return false;
    }

    default boolean convertToLinearVibration(int uid, String opPkg, VibrationEffect effect, IBinder token, VibrationAttributes attributes) {
        return false;
    }

    default void linearMotorVibrate(int uid, String opPkg, int[] waveformIds, long[] timings, int strength, int repeat, String reason, VibrationAttributes attributes, IBinder token) {
    }

    default int getVibratorStatus() {
        return -1;
    }

    default void setVibratorStrength(int strength) {
    }

    default int getVibratorTouchStyle() {
        return -1;
    }

    default void setVibratorTouchStyle(int style) {
    }
}
