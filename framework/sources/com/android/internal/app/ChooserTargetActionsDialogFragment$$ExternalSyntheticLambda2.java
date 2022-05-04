package com.android.internal.app;

import android.app.Dialog;
import java.util.function.Function;
/* loaded from: classes4.dex */
public final /* synthetic */ class ChooserTargetActionsDialogFragment$$ExternalSyntheticLambda2 implements Function {
    public static final /* synthetic */ ChooserTargetActionsDialogFragment$$ExternalSyntheticLambda2 INSTANCE = new ChooserTargetActionsDialogFragment$$ExternalSyntheticLambda2();

    private /* synthetic */ ChooserTargetActionsDialogFragment$$ExternalSyntheticLambda2() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((Dialog) obj).getWindow();
    }
}
