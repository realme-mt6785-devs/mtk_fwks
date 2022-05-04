package android.widget;

import java.util.function.Supplier;
/* loaded from: classes3.dex */
public final /* synthetic */ class SelectionActionModeHelper$$ExternalSyntheticLambda12 implements Supplier {
    public final /* synthetic */ TextView f$0;

    public /* synthetic */ SelectionActionModeHelper$$ExternalSyntheticLambda12(TextView textView) {
        this.f$0 = textView;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        return this.f$0.getTextClassificationSession();
    }
}
