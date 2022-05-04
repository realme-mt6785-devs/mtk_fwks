package android.os;

import java.util.function.ToIntFunction;
/* loaded from: classes2.dex */
public final /* synthetic */ class HidlSupport$$ExternalSyntheticLambda3 implements ToIntFunction {
    public static final /* synthetic */ HidlSupport$$ExternalSyntheticLambda3 INSTANCE = new HidlSupport$$ExternalSyntheticLambda3();

    private /* synthetic */ HidlSupport$$ExternalSyntheticLambda3() {
    }

    @Override // java.util.function.ToIntFunction
    public final int applyAsInt(Object obj) {
        int deepHashCode;
        deepHashCode = HidlSupport.deepHashCode(obj);
        return deepHashCode;
    }
}
