package android.util;

import java.util.function.Function;
/* loaded from: classes3.dex */
public final /* synthetic */ class DebugUtils$$ExternalSyntheticLambda0 implements Function {
    public static final /* synthetic */ DebugUtils$$ExternalSyntheticLambda0 INSTANCE = new DebugUtils$$ExternalSyntheticLambda0();

    private /* synthetic */ DebugUtils$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((StackTraceElement) obj).getMethodName();
    }
}
