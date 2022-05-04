package android.content;

import android.os.Bundle;
import android.os.IBinder;
/* loaded from: classes.dex */
public interface IPendingResultExt {
    default void setHascode(int hasCode) {
    }

    default void setOrder(boolean OrderedHint) {
    }

    default boolean getOrder() {
        return false;
    }

    default void setBroadcastState(int flag, int state) {
    }

    default void finishNotOrderReceiver(IBinder who, int resultCode, String resultData, Bundle resultExtras, boolean resultAbort) {
    }
}
