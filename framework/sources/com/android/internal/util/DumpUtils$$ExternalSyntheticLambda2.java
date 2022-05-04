package com.android.internal.util;

import android.content.ComponentName;
import java.util.function.Predicate;
/* loaded from: classes4.dex */
public final /* synthetic */ class DumpUtils$$ExternalSyntheticLambda2 implements Predicate {
    public static final /* synthetic */ DumpUtils$$ExternalSyntheticLambda2 INSTANCE = new DumpUtils$$ExternalSyntheticLambda2();

    private /* synthetic */ DumpUtils$$ExternalSyntheticLambda2() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return DumpUtils.isNonPlatformPackage((ComponentName.WithComponentName) obj);
    }
}
