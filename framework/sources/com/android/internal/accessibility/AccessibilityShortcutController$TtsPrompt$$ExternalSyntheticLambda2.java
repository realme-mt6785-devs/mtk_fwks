package com.android.internal.accessibility;

import com.android.internal.accessibility.AccessibilityShortcutController;
import java.util.function.Consumer;
/* loaded from: classes4.dex */
public final /* synthetic */ class AccessibilityShortcutController$TtsPrompt$$ExternalSyntheticLambda2 implements Consumer {
    public static final /* synthetic */ AccessibilityShortcutController$TtsPrompt$$ExternalSyntheticLambda2 INSTANCE = new AccessibilityShortcutController$TtsPrompt$$ExternalSyntheticLambda2();

    private /* synthetic */ AccessibilityShortcutController$TtsPrompt$$ExternalSyntheticLambda2() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((AccessibilityShortcutController.TtsPrompt) obj).waitForTtsReady();
    }
}
