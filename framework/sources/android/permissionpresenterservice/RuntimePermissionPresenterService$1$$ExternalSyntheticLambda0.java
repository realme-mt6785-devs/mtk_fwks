package android.permissionpresenterservice;

import android.os.RemoteCallback;
import com.android.internal.util.function.TriConsumer;
/* loaded from: classes2.dex */
public final /* synthetic */ class RuntimePermissionPresenterService$1$$ExternalSyntheticLambda0 implements TriConsumer {
    public static final /* synthetic */ RuntimePermissionPresenterService$1$$ExternalSyntheticLambda0 INSTANCE = new RuntimePermissionPresenterService$1$$ExternalSyntheticLambda0();

    private /* synthetic */ RuntimePermissionPresenterService$1$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((RuntimePermissionPresenterService) obj).getAppPermissions((String) obj2, (RemoteCallback) obj3);
    }
}
