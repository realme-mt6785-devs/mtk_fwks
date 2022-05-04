package com.android.internal.infra;

import java.util.function.Consumer;
/* loaded from: classes4.dex */
public final /* synthetic */ class AbstractRemoteService$$ExternalSyntheticLambda6 implements Consumer {
    public static final /* synthetic */ AbstractRemoteService$$ExternalSyntheticLambda6 INSTANCE = new AbstractRemoteService$$ExternalSyntheticLambda6();

    private /* synthetic */ AbstractRemoteService$$ExternalSyntheticLambda6() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((AbstractRemoteService) obj).handleBinderDied();
    }
}
