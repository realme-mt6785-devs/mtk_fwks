package com.android.internal.app;

import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import java.util.function.Consumer;
/* loaded from: classes4.dex */
public final /* synthetic */ class ChooserTargetActionsDialogFragment$$ExternalSyntheticLambda0 implements Consumer {
    public static final /* synthetic */ ChooserTargetActionsDialogFragment$$ExternalSyntheticLambda0 INSTANCE = new ChooserTargetActionsDialogFragment$$ExternalSyntheticLambda0();

    private /* synthetic */ ChooserTargetActionsDialogFragment$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((Window) obj).setBackgroundDrawable(new ColorDrawable(0));
    }
}
