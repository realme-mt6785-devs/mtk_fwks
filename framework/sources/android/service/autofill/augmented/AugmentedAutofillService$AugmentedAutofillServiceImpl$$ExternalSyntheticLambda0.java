package android.service.autofill.augmented;

import android.content.ComponentName;
import android.os.IBinder;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillValue;
import android.view.inputmethod.InlineSuggestionsRequest;
import com.android.internal.util.function.DecConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class AugmentedAutofillService$AugmentedAutofillServiceImpl$$ExternalSyntheticLambda0 implements DecConsumer {
    public static final /* synthetic */ AugmentedAutofillService$AugmentedAutofillServiceImpl$$ExternalSyntheticLambda0 INSTANCE = new AugmentedAutofillService$AugmentedAutofillServiceImpl$$ExternalSyntheticLambda0();

    private /* synthetic */ AugmentedAutofillService$AugmentedAutofillServiceImpl$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.internal.util.function.DecConsumer
    public final void accept(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        ((AugmentedAutofillService) obj).handleOnFillRequest(((Integer) obj2).intValue(), (IBinder) obj3, ((Integer) obj4).intValue(), (ComponentName) obj5, (AutofillId) obj6, (AutofillValue) obj7, ((Long) obj8).longValue(), (InlineSuggestionsRequest) obj9, (IFillCallback) obj10);
    }
}
