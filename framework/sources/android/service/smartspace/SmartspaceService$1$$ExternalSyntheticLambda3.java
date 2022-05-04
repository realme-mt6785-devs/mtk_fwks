package android.service.smartspace;

import android.app.smartspace.SmartspaceSessionId;
import android.app.smartspace.SmartspaceTargetEvent;
import com.android.internal.util.function.TriConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class SmartspaceService$1$$ExternalSyntheticLambda3 implements TriConsumer {
    public static final /* synthetic */ SmartspaceService$1$$ExternalSyntheticLambda3 INSTANCE = new SmartspaceService$1$$ExternalSyntheticLambda3();

    private /* synthetic */ SmartspaceService$1$$ExternalSyntheticLambda3() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((SmartspaceService) obj).notifySmartspaceEvent((SmartspaceSessionId) obj2, (SmartspaceTargetEvent) obj3);
    }
}
