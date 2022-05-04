package android.service.voice;

import android.service.voice.VoiceInteractionSession;
import java.util.function.BiConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class VoiceInteractionSession$1$$ExternalSyntheticLambda0 implements BiConsumer {
    public static final /* synthetic */ VoiceInteractionSession$1$$ExternalSyntheticLambda0 INSTANCE = new VoiceInteractionSession$1$$ExternalSyntheticLambda0();

    private /* synthetic */ VoiceInteractionSession$1$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((VoiceInteractionSession) obj).onDirectActionsInvalidated((VoiceInteractionSession.ActivityId) obj2);
    }
}
