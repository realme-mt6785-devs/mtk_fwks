package android.service.smartspace;

import android.app.smartspace.SmartspaceSessionId;
import java.util.function.BiConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class SmartspaceService$1$$ExternalSyntheticLambda4 implements BiConsumer {
    public static final /* synthetic */ SmartspaceService$1$$ExternalSyntheticLambda4 INSTANCE = new SmartspaceService$1$$ExternalSyntheticLambda4();

    private /* synthetic */ SmartspaceService$1$$ExternalSyntheticLambda4() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((SmartspaceService) obj).doDestroy((SmartspaceSessionId) obj2);
    }
}
