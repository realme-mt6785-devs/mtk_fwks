package android.service.autofill;

import java.util.function.Consumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class AutofillService$1$$ExternalSyntheticLambda4 implements Consumer {
    public static final /* synthetic */ AutofillService$1$$ExternalSyntheticLambda4 INSTANCE = new AutofillService$1$$ExternalSyntheticLambda4();

    private /* synthetic */ AutofillService$1$$ExternalSyntheticLambda4() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((AutofillService) obj).onDisconnected();
    }
}
