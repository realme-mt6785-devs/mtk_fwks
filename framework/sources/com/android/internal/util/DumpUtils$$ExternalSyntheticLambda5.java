package com.android.internal.util;

import android.content.ComponentName;
import java.util.function.Predicate;
/* loaded from: classes4.dex */
public final /* synthetic */ class DumpUtils$$ExternalSyntheticLambda5 implements Predicate {
    public static final /* synthetic */ DumpUtils$$ExternalSyntheticLambda5 INSTANCE = new DumpUtils$$ExternalSyntheticLambda5();

    private /* synthetic */ DumpUtils$$ExternalSyntheticLambda5() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return DumpUtils.isPlatformPackage((ComponentName.WithComponentName) obj);
    }
}
