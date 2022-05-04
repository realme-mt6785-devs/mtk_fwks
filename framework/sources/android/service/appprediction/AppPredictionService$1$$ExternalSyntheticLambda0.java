package android.service.appprediction;

import android.app.prediction.AppPredictionSessionId;
import com.android.internal.util.function.QuadConsumer;
import java.util.List;
/* loaded from: classes.dex */
public final /* synthetic */ class AppPredictionService$1$$ExternalSyntheticLambda0 implements QuadConsumer {
    public static final /* synthetic */ AppPredictionService$1$$ExternalSyntheticLambda0 INSTANCE = new AppPredictionService$1$$ExternalSyntheticLambda0();

    private /* synthetic */ AppPredictionService$1$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.internal.util.function.QuadConsumer
    public final void accept(Object obj, Object obj2, Object obj3, Object obj4) {
        ((AppPredictionService) obj).onLaunchLocationShown((AppPredictionSessionId) obj2, (String) obj3, (List) obj4);
    }
}
