package com.android.internal.os;

import com.android.internal.os.BinderCallsStats;
import java.util.function.ToDoubleFunction;
/* loaded from: classes4.dex */
public final /* synthetic */ class BinderCallsStats$$ExternalSyntheticLambda4 implements ToDoubleFunction {
    public static final /* synthetic */ BinderCallsStats$$ExternalSyntheticLambda4 INSTANCE = new BinderCallsStats$$ExternalSyntheticLambda4();

    private /* synthetic */ BinderCallsStats$$ExternalSyntheticLambda4() {
    }

    @Override // java.util.function.ToDoubleFunction
    public final double applyAsDouble(Object obj) {
        double d;
        BinderCallsStats.UidEntry uidEntry = (BinderCallsStats.UidEntry) obj;
        return d;
    }
}
