package com.android.internal.inputmethod;

import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;
/* loaded from: classes4.dex */
public final /* synthetic */ class CancellationGroup$$ExternalSyntheticLambda0 implements Consumer {
    public static final /* synthetic */ CancellationGroup$$ExternalSyntheticLambda0 INSTANCE = new CancellationGroup$$ExternalSyntheticLambda0();

    private /* synthetic */ CancellationGroup$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((CountDownLatch) obj).countDown();
    }
}
