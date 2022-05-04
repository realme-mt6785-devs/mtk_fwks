package com.mediatek.server;

import com.android.server.alarm.Alarm;
import java.util.function.ToLongFunction;
/* loaded from: classes.dex */
public final /* synthetic */ class MtkAlarmManagerService$$ExternalSyntheticLambda0 implements ToLongFunction {
    public static final /* synthetic */ MtkAlarmManagerService$$ExternalSyntheticLambda0 INSTANCE = new MtkAlarmManagerService$$ExternalSyntheticLambda0();

    private /* synthetic */ MtkAlarmManagerService$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.ToLongFunction
    public final long applyAsLong(Object obj) {
        return ((Alarm) obj).getWhenElapsed();
    }
}
