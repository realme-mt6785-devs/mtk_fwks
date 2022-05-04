package android.service.voice;

import java.util.function.Consumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class VoiceInteractionService$$ExternalSyntheticLambda1 implements Consumer {
    public static final /* synthetic */ VoiceInteractionService$$ExternalSyntheticLambda1 INSTANCE = new VoiceInteractionService$$ExternalSyntheticLambda1();

    private /* synthetic */ VoiceInteractionService$$ExternalSyntheticLambda1() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((VoiceInteractionService) obj).onShutdownInternal();
    }
}
