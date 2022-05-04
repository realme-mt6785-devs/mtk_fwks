package android.service.smartspace;

import android.app.smartspace.SmartspaceSessionId;
import java.util.function.BiConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class SmartspaceService$1$$ExternalSyntheticLambda5 implements BiConsumer {
    public static final /* synthetic */ SmartspaceService$1$$ExternalSyntheticLambda5 INSTANCE = new SmartspaceService$1$$ExternalSyntheticLambda5();

    private /* synthetic */ SmartspaceService$1$$ExternalSyntheticLambda5() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((SmartspaceService) obj).doRequestPredictionUpdate((SmartspaceSessionId) obj2);
    }
}
