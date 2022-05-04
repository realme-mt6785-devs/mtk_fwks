package android.service.autofill;

import java.util.function.BiConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class AutofillService$1$$ExternalSyntheticLambda2 implements BiConsumer {
    public static final /* synthetic */ AutofillService$1$$ExternalSyntheticLambda2 INSTANCE = new AutofillService$1$$ExternalSyntheticLambda2();

    private /* synthetic */ AutofillService$1$$ExternalSyntheticLambda2() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((AutofillService) obj).onSavedDatasetsInfoRequest((SavedDatasetsInfoCallbackImpl) obj2);
    }
}
