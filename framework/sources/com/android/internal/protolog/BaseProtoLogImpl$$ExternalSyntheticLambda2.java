package com.android.internal.protolog;

import com.android.internal.protolog.common.IProtoLogGroup;
import java.util.function.Predicate;
/* loaded from: classes4.dex */
public final /* synthetic */ class BaseProtoLogImpl$$ExternalSyntheticLambda2 implements Predicate {
    public static final /* synthetic */ BaseProtoLogImpl$$ExternalSyntheticLambda2 INSTANCE = new BaseProtoLogImpl$$ExternalSyntheticLambda2();

    private /* synthetic */ BaseProtoLogImpl$$ExternalSyntheticLambda2() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return BaseProtoLogImpl.lambda$getStatus$3((IProtoLogGroup) obj);
    }
}
