package com.android.internal.util;

import android.content.ComponentName;
import java.util.Objects;
import java.util.function.Predicate;
/* loaded from: classes4.dex */
public final /* synthetic */ class DumpUtils$$ExternalSyntheticLambda7 implements Predicate {
    public static final /* synthetic */ DumpUtils$$ExternalSyntheticLambda7 INSTANCE = new DumpUtils$$ExternalSyntheticLambda7();

    private /* synthetic */ DumpUtils$$ExternalSyntheticLambda7() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return Objects.nonNull((ComponentName.WithComponentName) obj);
    }
}
