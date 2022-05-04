package com.android.internal.os;

import android.os.SystemProperties;
import java.util.function.Supplier;
/* loaded from: classes4.dex */
public final /* synthetic */ class RuntimeInit$$ExternalSyntheticLambda1 implements Supplier {
    public static final /* synthetic */ RuntimeInit$$ExternalSyntheticLambda1 INSTANCE = new RuntimeInit$$ExternalSyntheticLambda1();

    private /* synthetic */ RuntimeInit$$ExternalSyntheticLambda1() {
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        String str;
        str = SystemProperties.get("persist.sys.timezone");
        return str;
    }
}
