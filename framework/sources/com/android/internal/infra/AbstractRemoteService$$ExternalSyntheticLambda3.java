package com.android.internal.infra;

import java.util.function.Consumer;
/* loaded from: classes4.dex */
public final /* synthetic */ class AbstractRemoteService$$ExternalSyntheticLambda3 implements Consumer {
    public static final /* synthetic */ AbstractRemoteService$$ExternalSyntheticLambda3 INSTANCE = new AbstractRemoteService$$ExternalSyntheticLambda3();

    private /* synthetic */ AbstractRemoteService$$ExternalSyntheticLambda3() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((AbstractRemoteService) obj).handleEnsureBound();
    }
}
