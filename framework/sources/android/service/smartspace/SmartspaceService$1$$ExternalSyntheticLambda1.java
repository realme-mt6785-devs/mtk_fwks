package android.service.smartspace;

import android.app.smartspace.ISmartspaceCallback;
import android.app.smartspace.SmartspaceSessionId;
import com.android.internal.util.function.TriConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class SmartspaceService$1$$ExternalSyntheticLambda1 implements TriConsumer {
    public static final /* synthetic */ SmartspaceService$1$$ExternalSyntheticLambda1 INSTANCE = new SmartspaceService$1$$ExternalSyntheticLambda1();

    private /* synthetic */ SmartspaceService$1$$ExternalSyntheticLambda1() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((SmartspaceService) obj).doRegisterSmartspaceUpdates((SmartspaceSessionId) obj2, (ISmartspaceCallback) obj3);
    }
}
