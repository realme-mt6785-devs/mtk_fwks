package android.service.appprediction;

import android.app.prediction.AppPredictionSessionId;
import android.app.prediction.IPredictionCallback;
import com.android.internal.util.function.TriConsumer;
/* loaded from: classes.dex */
public final /* synthetic */ class AppPredictionService$1$$ExternalSyntheticLambda5 implements TriConsumer {
    public static final /* synthetic */ AppPredictionService$1$$ExternalSyntheticLambda5 INSTANCE = new AppPredictionService$1$$ExternalSyntheticLambda5();

    private /* synthetic */ AppPredictionService$1$$ExternalSyntheticLambda5() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((AppPredictionService) obj).doUnregisterPredictionUpdates((AppPredictionSessionId) obj2, (IPredictionCallback) obj3);
    }
}
