package android.service.autofill;

import android.os.CancellationSignal;
import com.android.internal.util.function.QuadConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class AutofillService$1$$ExternalSyntheticLambda0 implements QuadConsumer {
    public static final /* synthetic */ AutofillService$1$$ExternalSyntheticLambda0 INSTANCE = new AutofillService$1$$ExternalSyntheticLambda0();

    private /* synthetic */ AutofillService$1$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.internal.util.function.QuadConsumer
    public final void accept(Object obj, Object obj2, Object obj3, Object obj4) {
        ((AutofillService) obj).onFillRequest((FillRequest) obj2, (CancellationSignal) obj3, (FillCallback) obj4);
    }
}
