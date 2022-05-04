package android.view.accessibility;

import java.util.function.IntFunction;
/* loaded from: classes3.dex */
public final /* synthetic */ class AccessibilityEvent$$ExternalSyntheticLambda0 implements IntFunction {
    public static final /* synthetic */ AccessibilityEvent$$ExternalSyntheticLambda0 INSTANCE = new AccessibilityEvent$$ExternalSyntheticLambda0();

    private /* synthetic */ AccessibilityEvent$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.IntFunction
    public final Object apply(int i) {
        String singleContentChangeTypeToString;
        singleContentChangeTypeToString = AccessibilityEvent.singleContentChangeTypeToString(i);
        return singleContentChangeTypeToString;
    }
}
