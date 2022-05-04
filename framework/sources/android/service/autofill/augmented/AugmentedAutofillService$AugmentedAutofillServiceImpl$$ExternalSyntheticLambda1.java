package android.service.autofill.augmented;

import com.android.internal.util.function.TriConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class AugmentedAutofillService$AugmentedAutofillServiceImpl$$ExternalSyntheticLambda1 implements TriConsumer {
    public static final /* synthetic */ AugmentedAutofillService$AugmentedAutofillServiceImpl$$ExternalSyntheticLambda1 INSTANCE = new AugmentedAutofillService$AugmentedAutofillServiceImpl$$ExternalSyntheticLambda1();

    private /* synthetic */ AugmentedAutofillService$AugmentedAutofillServiceImpl$$ExternalSyntheticLambda1() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((AugmentedAutofillService) obj).handleOnConnected(((Boolean) obj2).booleanValue(), ((Boolean) obj3).booleanValue());
    }
}
