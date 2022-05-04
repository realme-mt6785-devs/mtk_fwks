package com.android.internal.os;

import com.android.internal.os.BinderCallsStats;
import java.util.Comparator;
/* loaded from: classes4.dex */
public final /* synthetic */ class BinderCallsStats$$ExternalSyntheticLambda1 implements Comparator {
    public static final /* synthetic */ BinderCallsStats$$ExternalSyntheticLambda1 INSTANCE = new BinderCallsStats$$ExternalSyntheticLambda1();

    private /* synthetic */ BinderCallsStats$$ExternalSyntheticLambda1() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compareByBinderClassAndCode;
        compareByBinderClassAndCode = BinderCallsStats.compareByBinderClassAndCode((BinderCallsStats.ExportedCallStat) obj, (BinderCallsStats.ExportedCallStat) obj2);
        return compareByBinderClassAndCode;
    }
}
