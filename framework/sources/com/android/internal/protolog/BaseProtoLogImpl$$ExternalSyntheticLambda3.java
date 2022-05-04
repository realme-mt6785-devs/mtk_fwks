package com.android.internal.protolog;

import com.android.internal.protolog.common.IProtoLogGroup;
import java.util.function.Predicate;
/* loaded from: classes4.dex */
public final /* synthetic */ class BaseProtoLogImpl$$ExternalSyntheticLambda3 implements Predicate {
    public static final /* synthetic */ BaseProtoLogImpl$$ExternalSyntheticLambda3 INSTANCE = new BaseProtoLogImpl$$ExternalSyntheticLambda3();

    private /* synthetic */ BaseProtoLogImpl$$ExternalSyntheticLambda3() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return BaseProtoLogImpl.lambda$getStatus$4((IProtoLogGroup) obj);
    }
}
