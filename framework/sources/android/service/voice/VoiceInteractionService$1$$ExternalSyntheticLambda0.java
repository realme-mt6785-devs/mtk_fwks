package android.service.voice;

import com.android.internal.app.IVoiceActionCheckCallback;
import com.android.internal.util.function.TriConsumer;
import java.util.List;
/* loaded from: classes3.dex */
public final /* synthetic */ class VoiceInteractionService$1$$ExternalSyntheticLambda0 implements TriConsumer {
    public static final /* synthetic */ VoiceInteractionService$1$$ExternalSyntheticLambda0 INSTANCE = new VoiceInteractionService$1$$ExternalSyntheticLambda0();

    private /* synthetic */ VoiceInteractionService$1$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((VoiceInteractionService) obj).onHandleVoiceActionCheck((List) obj2, (IVoiceActionCheckCallback) obj3);
    }
}
