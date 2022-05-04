package com.android.internal.os;

import java.util.Comparator;
/* loaded from: classes4.dex */
public final /* synthetic */ class BatteryStatsHelper$$ExternalSyntheticLambda0 implements Comparator {
    public static final /* synthetic */ BatteryStatsHelper$$ExternalSyntheticLambda0 INSTANCE = new BatteryStatsHelper$$ExternalSyntheticLambda0();

    private /* synthetic */ BatteryStatsHelper$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compare;
        compare = Double.compare(((BatterySipper) obj2).mobilemspp, ((BatterySipper) obj).mobilemspp);
        return compare;
    }
}
