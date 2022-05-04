package android.view.accessibility;

import java.util.function.IntFunction;
/* loaded from: classes3.dex */
public final /* synthetic */ class AccessibilityEvent$$ExternalSyntheticLambda1 implements IntFunction {
    public static final /* synthetic */ AccessibilityEvent$$ExternalSyntheticLambda1 INSTANCE = new AccessibilityEvent$$ExternalSyntheticLambda1();

    private /* synthetic */ AccessibilityEvent$$ExternalSyntheticLambda1() {
    }

    @Override // java.util.function.IntFunction
    public final Object apply(int i) {
        String singleWindowChangeTypeToString;
        singleWindowChangeTypeToString = AccessibilityEvent.singleWindowChangeTypeToString(i);
        return singleWindowChangeTypeToString;
    }
}
