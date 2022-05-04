package android.app;

import android.app.UiAutomation;
import android.view.accessibility.AccessibilityEvent;
import java.util.function.BiConsumer;
/* loaded from: classes.dex */
public final /* synthetic */ class UiAutomation$IAccessibilityServiceClientImpl$1$$ExternalSyntheticLambda0 implements BiConsumer {
    public static final /* synthetic */ UiAutomation$IAccessibilityServiceClientImpl$1$$ExternalSyntheticLambda0 INSTANCE = new UiAutomation$IAccessibilityServiceClientImpl$1$$ExternalSyntheticLambda0();

    private /* synthetic */ UiAutomation$IAccessibilityServiceClientImpl$1$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((UiAutomation.OnAccessibilityEventListener) obj).onAccessibilityEvent((AccessibilityEvent) obj2);
    }
}
