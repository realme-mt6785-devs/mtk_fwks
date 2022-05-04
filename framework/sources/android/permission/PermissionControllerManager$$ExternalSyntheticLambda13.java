package android.permission;

import com.android.internal.infra.ServiceConnector;
/* loaded from: classes2.dex */
public final /* synthetic */ class PermissionControllerManager$$ExternalSyntheticLambda13 implements ServiceConnector.Job {
    public static final /* synthetic */ PermissionControllerManager$$ExternalSyntheticLambda13 INSTANCE = new PermissionControllerManager$$ExternalSyntheticLambda13();

    private /* synthetic */ PermissionControllerManager$$ExternalSyntheticLambda13() {
    }

    @Override // com.android.internal.infra.ServiceConnector.Job
    public final Object run(Object obj) {
        return PermissionControllerManager.lambda$grantOrUpgradeDefaultRuntimePermissions$23((IPermissionController) obj);
    }
}
