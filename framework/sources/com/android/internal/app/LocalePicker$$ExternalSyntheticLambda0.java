package com.android.internal.app;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
/* loaded from: classes4.dex */
public final /* synthetic */ class LocalePicker$$ExternalSyntheticLambda0 implements Function {
    public static final /* synthetic */ LocalePicker$$ExternalSyntheticLambda0 INSTANCE = new LocalePicker$$ExternalSyntheticLambda0();

    private /* synthetic */ LocalePicker$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        Predicate asPredicate;
        asPredicate = Pattern.compile((String) obj).asPredicate();
        return asPredicate;
    }
}
