package com.android.internal.os;

import com.android.internal.os.BinderCallsStats;
import java.util.Comparator;
/* loaded from: classes4.dex */
public final /* synthetic */ class BinderCallsStats$$ExternalSyntheticLambda2 implements Comparator {
    public static final /* synthetic */ BinderCallsStats$$ExternalSyntheticLambda2 INSTANCE = new BinderCallsStats$$ExternalSyntheticLambda2();

    private /* synthetic */ BinderCallsStats$$ExternalSyntheticLambda2() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compareByCpuDesc;
        compareByCpuDesc = BinderCallsStats.compareByCpuDesc((BinderCallsStats.ExportedCallStat) obj, (BinderCallsStats.ExportedCallStat) obj2);
        return compareByCpuDesc;
    }
}
