package android.service.voice;

import java.util.function.Consumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class VoiceInteractionService$1$$ExternalSyntheticLambda3 implements Consumer {
    public static final /* synthetic */ VoiceInteractionService$1$$ExternalSyntheticLambda3 INSTANCE = new VoiceInteractionService$1$$ExternalSyntheticLambda3();

    private /* synthetic */ VoiceInteractionService$1$$ExternalSyntheticLambda3() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((VoiceInteractionService) obj).onShutdownInternal();
    }
}
