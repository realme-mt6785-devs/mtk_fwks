package android.service.voice;

import java.util.function.Consumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class VoiceInteractionService$1$$ExternalSyntheticLambda2 implements Consumer {
    public static final /* synthetic */ VoiceInteractionService$1$$ExternalSyntheticLambda2 INSTANCE = new VoiceInteractionService$1$$ExternalSyntheticLambda2();

    private /* synthetic */ VoiceInteractionService$1$$ExternalSyntheticLambda2() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((VoiceInteractionService) obj).onReady();
    }
}
