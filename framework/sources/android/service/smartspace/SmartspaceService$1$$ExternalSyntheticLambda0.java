package android.service.smartspace;

import android.app.smartspace.SmartspaceConfig;
import android.app.smartspace.SmartspaceSessionId;
import com.android.internal.util.function.TriConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class SmartspaceService$1$$ExternalSyntheticLambda0 implements TriConsumer {
    public static final /* synthetic */ SmartspaceService$1$$ExternalSyntheticLambda0 INSTANCE = new SmartspaceService$1$$ExternalSyntheticLambda0();

    private /* synthetic */ SmartspaceService$1$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((SmartspaceService) obj).doCreateSmartspaceSession((SmartspaceConfig) obj2, (SmartspaceSessionId) obj3);
    }
}
