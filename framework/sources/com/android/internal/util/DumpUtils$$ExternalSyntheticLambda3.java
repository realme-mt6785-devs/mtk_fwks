package com.android.internal.util;

import android.content.ComponentName;
import java.util.function.Predicate;
/* loaded from: classes4.dex */
public final /* synthetic */ class DumpUtils$$ExternalSyntheticLambda3 implements Predicate {
    public static final /* synthetic */ DumpUtils$$ExternalSyntheticLambda3 INSTANCE = new DumpUtils$$ExternalSyntheticLambda3();

    private /* synthetic */ DumpUtils$$ExternalSyntheticLambda3() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return DumpUtils.isPlatformCriticalPackage((ComponentName.WithComponentName) obj);
    }
}
