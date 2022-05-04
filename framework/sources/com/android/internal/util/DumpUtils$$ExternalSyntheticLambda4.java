package com.android.internal.util;

import android.content.ComponentName;
import java.util.function.Predicate;
/* loaded from: classes4.dex */
public final /* synthetic */ class DumpUtils$$ExternalSyntheticLambda4 implements Predicate {
    public static final /* synthetic */ DumpUtils$$ExternalSyntheticLambda4 INSTANCE = new DumpUtils$$ExternalSyntheticLambda4();

    private /* synthetic */ DumpUtils$$ExternalSyntheticLambda4() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return DumpUtils.isPlatformNonCriticalPackage((ComponentName.WithComponentName) obj);
    }
}
