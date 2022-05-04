package android.media.soundtrigger;

import android.os.Bundle;
import com.android.internal.util.function.TriConsumer;
import java.util.UUID;
/* loaded from: classes2.dex */
public final /* synthetic */ class SoundTriggerDetectionService$1$$ExternalSyntheticLambda4 implements TriConsumer {
    public static final /* synthetic */ SoundTriggerDetectionService$1$$ExternalSyntheticLambda4 INSTANCE = new SoundTriggerDetectionService$1$$ExternalSyntheticLambda4();

    private /* synthetic */ SoundTriggerDetectionService$1$$ExternalSyntheticLambda4() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((SoundTriggerDetectionService) obj).removeClient((UUID) obj2, (Bundle) obj3);
    }
}
