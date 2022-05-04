package com.android.internal.os;

import android.util.Pair;
import java.util.Comparator;
/* loaded from: classes4.dex */
public final /* synthetic */ class BinderCallsStats$$ExternalSyntheticLambda0 implements Comparator {
    public static final /* synthetic */ BinderCallsStats$$ExternalSyntheticLambda0 INSTANCE = new BinderCallsStats$$ExternalSyntheticLambda0();

    private /* synthetic */ BinderCallsStats$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compare;
        compare = Integer.compare(((Integer) ((Pair) obj2).second).intValue(), ((Integer) ((Pair) obj).second).intValue());
        return compare;
    }
}
