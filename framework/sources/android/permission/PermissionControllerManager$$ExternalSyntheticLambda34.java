package android.permission;

import android.os.IBinder;
import android.permission.IPermissionController;
import java.util.function.Function;
/* loaded from: classes2.dex */
public final /* synthetic */ class PermissionControllerManager$$ExternalSyntheticLambda34 implements Function {
    public static final /* synthetic */ PermissionControllerManager$$ExternalSyntheticLambda34 INSTANCE = new PermissionControllerManager$$ExternalSyntheticLambda34();

    private /* synthetic */ PermissionControllerManager$$ExternalSyntheticLambda34() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return IPermissionController.Stub.asInterface((IBinder) obj);
    }
}
