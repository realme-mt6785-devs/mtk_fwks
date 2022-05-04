package com.android.internal.infra;

import java.util.function.Consumer;
/* loaded from: classes4.dex */
public final /* synthetic */ class AbstractRemoteService$$ExternalSyntheticLambda4 implements Consumer {
    public static final /* synthetic */ AbstractRemoteService$$ExternalSyntheticLambda4 INSTANCE = new AbstractRemoteService$$ExternalSyntheticLambda4();

    private /* synthetic */ AbstractRemoteService$$ExternalSyntheticLambda4() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((AbstractRemoteService) obj).handleDestroy();
    }
}
