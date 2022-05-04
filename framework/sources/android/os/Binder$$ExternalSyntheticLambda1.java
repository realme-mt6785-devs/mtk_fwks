package android.os;

import java.util.function.Supplier;
/* loaded from: classes2.dex */
public final /* synthetic */ class Binder$$ExternalSyntheticLambda1 implements Supplier {
    public static final /* synthetic */ Binder$$ExternalSyntheticLambda1 INSTANCE = new Binder$$ExternalSyntheticLambda1();

    private /* synthetic */ Binder$$ExternalSyntheticLambda1() {
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        Boolean valueOf;
        valueOf = Boolean.valueOf(Binder.sWarnOnBlocking);
        return valueOf;
    }
}
