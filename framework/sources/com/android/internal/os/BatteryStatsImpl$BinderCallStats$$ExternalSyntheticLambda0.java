package com.android.internal.os;

import com.android.internal.os.BatteryStatsImpl;
import java.util.function.Function;
/* loaded from: classes4.dex */
public final /* synthetic */ class BatteryStatsImpl$BinderCallStats$$ExternalSyntheticLambda0 implements Function {
    public static final /* synthetic */ BatteryStatsImpl$BinderCallStats$$ExternalSyntheticLambda0 INSTANCE = new BatteryStatsImpl$BinderCallStats$$ExternalSyntheticLambda0();

    private /* synthetic */ BatteryStatsImpl$BinderCallStats$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((BatteryStatsImpl.BinderCallStats) obj).getClassName();
    }
}
