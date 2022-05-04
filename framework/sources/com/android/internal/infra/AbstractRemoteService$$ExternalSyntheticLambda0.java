package com.android.internal.infra;

import com.android.internal.infra.AbstractRemoteService;
import java.util.function.BiConsumer;
/* loaded from: classes4.dex */
public final /* synthetic */ class AbstractRemoteService$$ExternalSyntheticLambda0 implements BiConsumer {
    public static final /* synthetic */ AbstractRemoteService$$ExternalSyntheticLambda0 INSTANCE = new AbstractRemoteService$$ExternalSyntheticLambda0();

    private /* synthetic */ AbstractRemoteService$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((AbstractRemoteService) obj).handlePendingRequest((AbstractRemoteService.BasePendingRequest) obj2);
    }
}
