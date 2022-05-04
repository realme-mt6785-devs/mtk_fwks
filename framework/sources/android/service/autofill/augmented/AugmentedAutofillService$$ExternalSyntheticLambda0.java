package android.service.autofill.augmented;

import java.util.function.Consumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class AugmentedAutofillService$$ExternalSyntheticLambda0 implements Consumer {
    public static final /* synthetic */ AugmentedAutofillService$$ExternalSyntheticLambda0 INSTANCE = new AugmentedAutofillService$$ExternalSyntheticLambda0();

    private /* synthetic */ AugmentedAutofillService$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((AugmentedAutofillService) obj).handleOnUnbind();
    }
}
