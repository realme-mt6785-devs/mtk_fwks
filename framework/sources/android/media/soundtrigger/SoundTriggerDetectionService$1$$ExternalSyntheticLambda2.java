package android.media.soundtrigger;

import android.hardware.soundtrigger.SoundTrigger;
import android.os.Bundle;
import com.android.internal.util.function.QuintConsumer;
import java.util.UUID;
/* loaded from: classes2.dex */
public final /* synthetic */ class SoundTriggerDetectionService$1$$ExternalSyntheticLambda2 implements QuintConsumer {
    public static final /* synthetic */ SoundTriggerDetectionService$1$$ExternalSyntheticLambda2 INSTANCE = new SoundTriggerDetectionService$1$$ExternalSyntheticLambda2();

    private /* synthetic */ SoundTriggerDetectionService$1$$ExternalSyntheticLambda2() {
    }

    @Override // com.android.internal.util.function.QuintConsumer
    public final void accept(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        ((SoundTriggerDetectionService) obj).onGenericRecognitionEvent((UUID) obj2, (Bundle) obj3, ((Integer) obj4).intValue(), (SoundTrigger.GenericRecognitionEvent) obj5);
    }
}
