package com.android.internal.infra;

import com.android.internal.util.FunctionalUtils;
import java.io.InputStream;
/* loaded from: classes4.dex */
public final /* synthetic */ class RemoteStream$$ExternalSyntheticLambda2 implements FunctionalUtils.ThrowingFunction {
    public static final /* synthetic */ RemoteStream$$ExternalSyntheticLambda2 INSTANCE = new RemoteStream$$ExternalSyntheticLambda2();

    private /* synthetic */ RemoteStream$$ExternalSyntheticLambda2() {
    }

    @Override // com.android.internal.util.FunctionalUtils.ThrowingFunction
    public final Object applyOrThrow(Object obj) {
        return RemoteStream.readAll((InputStream) obj);
    }
}
