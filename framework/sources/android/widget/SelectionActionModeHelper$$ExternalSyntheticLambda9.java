package android.widget;

import android.widget.SelectionActionModeHelper;
import java.util.function.Supplier;
/* loaded from: classes3.dex */
public final /* synthetic */ class SelectionActionModeHelper$$ExternalSyntheticLambda9 implements Supplier {
    public final /* synthetic */ SelectionActionModeHelper.TextClassificationHelper f$0;

    public /* synthetic */ SelectionActionModeHelper$$ExternalSyntheticLambda9(SelectionActionModeHelper.TextClassificationHelper textClassificationHelper) {
        this.f$0 = textClassificationHelper;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        return this.f$0.classifyText();
    }
}
