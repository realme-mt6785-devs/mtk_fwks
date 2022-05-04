package android.os;

import android.content.Context;
import java.io.PrintWriter;
/* loaded from: classes2.dex */
public interface IBatteryStatsExt {
    default Context getContext() {
        return null;
    }

    default void setContext(Context context) {
    }

    default void registerRomUpdate() {
    }

    default boolean haveNetworkMode(PrintWriter pw, boolean haveModes, long initMode, long modMode) {
        return haveModes;
    }

    default void reportDailyProto() {
    }

    default int[] noteConnectivityChangedLocked(int type, String extra, int phoneDataConnectionType, int modStepMode, int curStepMode) {
        return new int[]{modStepMode, curStepMode};
    }

    default int[] notePhoneDataConnectionStateLocked(int dataType, boolean hasData, int bin, int historyCurStates, int modStepMode, int curStepMode) {
        return new int[]{modStepMode, curStepMode};
    }

    default void setInBatteryStatsImplInstance(Object impl) {
    }
}
