package android.service.voice;

import java.util.function.Consumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class VoiceInteractionService$1$$ExternalSyntheticLambda1 implements Consumer {
    public static final /* synthetic */ VoiceInteractionService$1$$ExternalSyntheticLambda1 INSTANCE = new VoiceInteractionService$1$$ExternalSyntheticLambda1();

    private /* synthetic */ VoiceInteractionService$1$$ExternalSyntheticLambda1() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((VoiceInteractionService) obj).onLaunchVoiceAssistFromKeyguard();
    }
}
