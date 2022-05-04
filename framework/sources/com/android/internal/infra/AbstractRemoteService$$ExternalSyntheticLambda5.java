package com.android.internal.infra;

import java.util.function.Consumer;
/* loaded from: classes4.dex */
public final /* synthetic */ class AbstractRemoteService$$ExternalSyntheticLambda5 implements Consumer {
    public static final /* synthetic */ AbstractRemoteService$$ExternalSyntheticLambda5 INSTANCE = new AbstractRemoteService$$ExternalSyntheticLambda5();

    private /* synthetic */ AbstractRemoteService$$ExternalSyntheticLambda5() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((AbstractRemoteService) obj).handleUnbind();
    }
}
