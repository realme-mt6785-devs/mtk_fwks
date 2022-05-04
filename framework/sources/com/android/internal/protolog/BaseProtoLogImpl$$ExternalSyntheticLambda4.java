package com.android.internal.protolog;

import java.util.function.ToDoubleFunction;
/* loaded from: classes4.dex */
public final /* synthetic */ class BaseProtoLogImpl$$ExternalSyntheticLambda4 implements ToDoubleFunction {
    public static final /* synthetic */ BaseProtoLogImpl$$ExternalSyntheticLambda4 INSTANCE = new BaseProtoLogImpl$$ExternalSyntheticLambda4();

    private /* synthetic */ BaseProtoLogImpl$$ExternalSyntheticLambda4() {
    }

    @Override // java.util.function.ToDoubleFunction
    public final double applyAsDouble(Object obj) {
        double doubleValue;
        doubleValue = ((Double) obj).doubleValue();
        return doubleValue;
    }
}
