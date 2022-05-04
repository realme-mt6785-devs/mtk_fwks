package android.service.autofill;

import com.android.internal.util.function.TriConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class AutofillService$1$$ExternalSyntheticLambda1 implements TriConsumer {
    public static final /* synthetic */ AutofillService$1$$ExternalSyntheticLambda1 INSTANCE = new AutofillService$1$$ExternalSyntheticLambda1();

    private /* synthetic */ AutofillService$1$$ExternalSyntheticLambda1() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((AutofillService) obj).onSaveRequest((SaveRequest) obj2, (SaveCallback) obj3);
    }
}
