package android.service.appprediction;

import android.app.prediction.AppPredictionSessionId;
import java.util.function.BiConsumer;
/* loaded from: classes.dex */
public final /* synthetic */ class AppPredictionService$1$$ExternalSyntheticLambda6 implements BiConsumer {
    public static final /* synthetic */ AppPredictionService$1$$ExternalSyntheticLambda6 INSTANCE = new AppPredictionService$1$$ExternalSyntheticLambda6();

    private /* synthetic */ AppPredictionService$1$$ExternalSyntheticLambda6() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((AppPredictionService) obj).doDestroyPredictionSession((AppPredictionSessionId) obj2);
    }
}
