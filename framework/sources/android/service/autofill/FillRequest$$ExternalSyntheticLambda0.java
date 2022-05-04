package android.service.autofill;

import java.util.function.IntFunction;
/* loaded from: classes3.dex */
public final /* synthetic */ class FillRequest$$ExternalSyntheticLambda0 implements IntFunction {
    public static final /* synthetic */ FillRequest$$ExternalSyntheticLambda0 INSTANCE = new FillRequest$$ExternalSyntheticLambda0();

    private /* synthetic */ FillRequest$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.IntFunction
    public final Object apply(int i) {
        return FillRequest.singleRequestFlagsToString(i);
    }
}
