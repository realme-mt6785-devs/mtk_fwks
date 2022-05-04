package com.android.internal.util;

import android.content.ComponentName;
import java.util.function.Predicate;
/* loaded from: classes4.dex */
public final /* synthetic */ class DumpUtils$$ExternalSyntheticLambda6 implements Predicate {
    public static final /* synthetic */ DumpUtils$$ExternalSyntheticLambda6 INSTANCE = new DumpUtils$$ExternalSyntheticLambda6();

    private /* synthetic */ DumpUtils$$ExternalSyntheticLambda6() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return DumpUtils.lambda$filterRecord$0((ComponentName.WithComponentName) obj);
    }
}
