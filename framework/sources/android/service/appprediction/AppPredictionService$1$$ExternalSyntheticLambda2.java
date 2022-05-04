package android.service.appprediction;

import android.app.prediction.AppPredictionContext;
import android.app.prediction.AppPredictionSessionId;
import com.android.internal.util.function.TriConsumer;
/* loaded from: classes.dex */
public final /* synthetic */ class AppPredictionService$1$$ExternalSyntheticLambda2 implements TriConsumer {
    public static final /* synthetic */ AppPredictionService$1$$ExternalSyntheticLambda2 INSTANCE = new AppPredictionService$1$$ExternalSyntheticLambda2();

    private /* synthetic */ AppPredictionService$1$$ExternalSyntheticLambda2() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((AppPredictionService) obj).doCreatePredictionSession((AppPredictionContext) obj2, (AppPredictionSessionId) obj3);
    }
}
