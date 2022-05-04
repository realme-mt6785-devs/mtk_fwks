package com.android.internal.accessibility;

import android.speech.tts.TextToSpeech;
import java.util.function.Consumer;
/* loaded from: classes4.dex */
public final /* synthetic */ class AccessibilityShortcutController$TtsPrompt$$ExternalSyntheticLambda0 implements Consumer {
    public static final /* synthetic */ AccessibilityShortcutController$TtsPrompt$$ExternalSyntheticLambda0 INSTANCE = new AccessibilityShortcutController$TtsPrompt$$ExternalSyntheticLambda0();

    private /* synthetic */ AccessibilityShortcutController$TtsPrompt$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((TextToSpeech) obj).shutdown();
    }
}
