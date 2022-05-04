package com.android.internal.protolog;

import com.android.internal.protolog.common.IProtoLogGroup;
import java.util.function.Function;
/* loaded from: classes4.dex */
public final /* synthetic */ class BaseProtoLogImpl$$ExternalSyntheticLambda1 implements Function {
    public static final /* synthetic */ BaseProtoLogImpl$$ExternalSyntheticLambda1 INSTANCE = new BaseProtoLogImpl$$ExternalSyntheticLambda1();

    private /* synthetic */ BaseProtoLogImpl$$ExternalSyntheticLambda1() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((IProtoLogGroup) obj).name();
    }
}
