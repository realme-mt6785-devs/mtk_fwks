package com.android.internal.infra;

import com.android.internal.infra.AbstractRemoteService;
import java.util.function.BiConsumer;
/* loaded from: classes4.dex */
public final /* synthetic */ class AbstractRemoteService$$ExternalSyntheticLambda1 implements BiConsumer {
    public static final /* synthetic */ AbstractRemoteService$$ExternalSyntheticLambda1 INSTANCE = new AbstractRemoteService$$ExternalSyntheticLambda1();

    private /* synthetic */ AbstractRemoteService$$ExternalSyntheticLambda1() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((AbstractRemoteService) obj).handleFinishRequest((AbstractRemoteService.BasePendingRequest) obj2);
    }
}
