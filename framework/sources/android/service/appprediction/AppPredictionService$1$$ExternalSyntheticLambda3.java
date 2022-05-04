package android.service.appprediction;

import android.app.prediction.AppPredictionSessionId;
import android.app.prediction.AppTargetEvent;
import com.android.internal.util.function.TriConsumer;
/* loaded from: classes.dex */
public final /* synthetic */ class AppPredictionService$1$$ExternalSyntheticLambda3 implements TriConsumer {
    public static final /* synthetic */ AppPredictionService$1$$ExternalSyntheticLambda3 INSTANCE = new AppPredictionService$1$$ExternalSyntheticLambda3();

    private /* synthetic */ AppPredictionService$1$$ExternalSyntheticLambda3() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((AppPredictionService) obj).onAppTargetEvent((AppPredictionSessionId) obj2, (AppTargetEvent) obj3);
    }
}
