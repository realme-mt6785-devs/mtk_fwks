package android.service.appprediction;

import android.app.prediction.AppPredictionSessionId;
import java.util.function.BiConsumer;
/* loaded from: classes.dex */
public final /* synthetic */ class AppPredictionService$1$$ExternalSyntheticLambda7 implements BiConsumer {
    public static final /* synthetic */ AppPredictionService$1$$ExternalSyntheticLambda7 INSTANCE = new AppPredictionService$1$$ExternalSyntheticLambda7();

    private /* synthetic */ AppPredictionService$1$$ExternalSyntheticLambda7() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((AppPredictionService) obj).doRequestPredictionUpdate((AppPredictionSessionId) obj2);
    }
}
