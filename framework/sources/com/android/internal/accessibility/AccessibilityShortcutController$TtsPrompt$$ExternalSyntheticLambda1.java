package com.android.internal.accessibility;

import com.android.internal.accessibility.AccessibilityShortcutController;
import java.util.function.Consumer;
/* loaded from: classes4.dex */
public final /* synthetic */ class AccessibilityShortcutController$TtsPrompt$$ExternalSyntheticLambda1 implements Consumer {
    public static final /* synthetic */ AccessibilityShortcutController$TtsPrompt$$ExternalSyntheticLambda1 INSTANCE = new AccessibilityShortcutController$TtsPrompt$$ExternalSyntheticLambda1();

    private /* synthetic */ AccessibilityShortcutController$TtsPrompt$$ExternalSyntheticLambda1() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((AccessibilityShortcutController.TtsPrompt) obj).play();
    }
}
