package com.android.internal.app;

import android.service.chooser.ChooserTarget;
import java.util.Comparator;
/* loaded from: classes4.dex */
public final /* synthetic */ class ChooserActivity$$ExternalSyntheticLambda8 implements Comparator {
    public static final /* synthetic */ ChooserActivity$$ExternalSyntheticLambda8 INSTANCE = new ChooserActivity$$ExternalSyntheticLambda8();

    private /* synthetic */ ChooserActivity$$ExternalSyntheticLambda8() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return ChooserActivity.lambda$convertToChooserTarget$4((ChooserTarget) obj, (ChooserTarget) obj2);
    }
}
