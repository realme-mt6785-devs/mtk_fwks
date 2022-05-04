package android.service.appprediction;

import android.app.prediction.AppPredictionSessionId;
import android.os.CancellationSignal;
import android.service.appprediction.AppPredictionService;
import com.android.internal.util.function.QuintConsumer;
import java.util.List;
/* loaded from: classes.dex */
public final /* synthetic */ class AppPredictionService$1$$ExternalSyntheticLambda1 implements QuintConsumer {
    public static final /* synthetic */ AppPredictionService$1$$ExternalSyntheticLambda1 INSTANCE = new AppPredictionService$1$$ExternalSyntheticLambda1();

    private /* synthetic */ AppPredictionService$1$$ExternalSyntheticLambda1() {
    }

    @Override // com.android.internal.util.function.QuintConsumer
    public final void accept(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        ((AppPredictionService) obj).onSortAppTargets((AppPredictionSessionId) obj2, (List) obj3, (CancellationSignal) obj4, (AppPredictionService.CallbackWrapper) obj5);
    }
}
