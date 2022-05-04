package com.android.internal.app;

import com.android.internal.app.chooser.ChooserTargetInfo;
import java.util.function.Predicate;
/* loaded from: classes4.dex */
public final /* synthetic */ class ChooserListAdapter$$ExternalSyntheticLambda0 implements Predicate {
    public static final /* synthetic */ ChooserListAdapter$$ExternalSyntheticLambda0 INSTANCE = new ChooserListAdapter$$ExternalSyntheticLambda0();

    private /* synthetic */ ChooserListAdapter$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return ChooserListAdapter.lambda$completeServiceTargetLoading$0((ChooserTargetInfo) obj);
    }
}
