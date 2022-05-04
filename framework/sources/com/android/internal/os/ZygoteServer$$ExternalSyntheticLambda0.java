package com.android.internal.os;

import java.io.FileDescriptor;
import java.util.function.ToIntFunction;
/* loaded from: classes4.dex */
public final /* synthetic */ class ZygoteServer$$ExternalSyntheticLambda0 implements ToIntFunction {
    public static final /* synthetic */ ZygoteServer$$ExternalSyntheticLambda0 INSTANCE = new ZygoteServer$$ExternalSyntheticLambda0();

    private /* synthetic */ ZygoteServer$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.ToIntFunction
    public final int applyAsInt(Object obj) {
        return ((FileDescriptor) obj).getInt$();
    }
}
