package android.service.autofill.augmented;

import java.util.function.Consumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class AugmentedAutofillService$AugmentedAutofillServiceImpl$$ExternalSyntheticLambda3 implements Consumer {
    public static final /* synthetic */ AugmentedAutofillService$AugmentedAutofillServiceImpl$$ExternalSyntheticLambda3 INSTANCE = new AugmentedAutofillService$AugmentedAutofillServiceImpl$$ExternalSyntheticLambda3();

    private /* synthetic */ AugmentedAutofillService$AugmentedAutofillServiceImpl$$ExternalSyntheticLambda3() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((AugmentedAutofillService) obj).handleOnDisconnected();
    }
}
