package android.os;

import java.util.function.ToIntFunction;
/* loaded from: classes2.dex */
public final /* synthetic */ class HidlSupport$$ExternalSyntheticLambda2 implements ToIntFunction {
    public static final /* synthetic */ HidlSupport$$ExternalSyntheticLambda2 INSTANCE = new HidlSupport$$ExternalSyntheticLambda2();

    private /* synthetic */ HidlSupport$$ExternalSyntheticLambda2() {
    }

    @Override // java.util.function.ToIntFunction
    public final int applyAsInt(Object obj) {
        int deepHashCode;
        deepHashCode = HidlSupport.deepHashCode(obj);
        return deepHashCode;
    }
}
