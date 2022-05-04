package com.android.internal.graphics.palette;

import java.util.function.BiFunction;
/* loaded from: classes4.dex */
public final /* synthetic */ class QuantizerMap$$ExternalSyntheticLambda0 implements BiFunction {
    public static final /* synthetic */ QuantizerMap$$ExternalSyntheticLambda0 INSTANCE = new QuantizerMap$$ExternalSyntheticLambda0();

    private /* synthetic */ QuantizerMap$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.BiFunction
    public final Object apply(Object obj, Object obj2) {
        return Integer.valueOf(Integer.sum(((Integer) obj).intValue(), ((Integer) obj2).intValue()));
    }
}
