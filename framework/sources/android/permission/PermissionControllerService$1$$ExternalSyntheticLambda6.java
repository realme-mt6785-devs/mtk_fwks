package android.permission;

import com.android.internal.infra.AndroidFuture;
import java.util.List;
import java.util.function.Consumer;
/* loaded from: classes2.dex */
public final /* synthetic */ class PermissionControllerService$1$$ExternalSyntheticLambda6 implements Consumer {
    public final /* synthetic */ AndroidFuture f$0;

    public /* synthetic */ PermissionControllerService$1$$ExternalSyntheticLambda6(AndroidFuture androidFuture) {
        this.f$0 = androidFuture;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.f$0.complete((List) obj);
    }
}
