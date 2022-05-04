package com.android.internal.infra;

import android.os.IInterface;
import com.android.internal.infra.ServiceConnector;
/* loaded from: classes4.dex */
public final /* synthetic */ class ServiceConnector$Impl$$ExternalSyntheticLambda0 implements ServiceConnector.Job {
    public static final /* synthetic */ ServiceConnector$Impl$$ExternalSyntheticLambda0 INSTANCE = new ServiceConnector$Impl$$ExternalSyntheticLambda0();

    private /* synthetic */ ServiceConnector$Impl$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.internal.infra.ServiceConnector.Job
    public final Object run(Object obj) {
        return ServiceConnector.Impl.lambda$connect$0((IInterface) obj);
    }
}
