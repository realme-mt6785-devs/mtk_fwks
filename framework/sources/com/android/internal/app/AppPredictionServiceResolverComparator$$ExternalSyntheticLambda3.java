package com.android.internal.app;

import android.content.ComponentName;
import java.util.Map;
import java.util.function.Function;
/* loaded from: classes4.dex */
public final /* synthetic */ class AppPredictionServiceResolverComparator$$ExternalSyntheticLambda3 implements Function {
    public static final /* synthetic */ AppPredictionServiceResolverComparator$$ExternalSyntheticLambda3 INSTANCE = new AppPredictionServiceResolverComparator$$ExternalSyntheticLambda3();

    private /* synthetic */ AppPredictionServiceResolverComparator$$ExternalSyntheticLambda3() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return (ComponentName) ((Map.Entry) obj).getKey();
    }
}
