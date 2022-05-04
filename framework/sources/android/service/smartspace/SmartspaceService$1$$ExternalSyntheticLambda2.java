package android.service.smartspace;

import android.app.smartspace.ISmartspaceCallback;
import android.app.smartspace.SmartspaceSessionId;
import com.android.internal.util.function.TriConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class SmartspaceService$1$$ExternalSyntheticLambda2 implements TriConsumer {
    public static final /* synthetic */ SmartspaceService$1$$ExternalSyntheticLambda2 INSTANCE = new SmartspaceService$1$$ExternalSyntheticLambda2();

    private /* synthetic */ SmartspaceService$1$$ExternalSyntheticLambda2() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((SmartspaceService) obj).doUnregisterSmartspaceUpdates((SmartspaceSessionId) obj2, (ISmartspaceCallback) obj3);
    }
}
