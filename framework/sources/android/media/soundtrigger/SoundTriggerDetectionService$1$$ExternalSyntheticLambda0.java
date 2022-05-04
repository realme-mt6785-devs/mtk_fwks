package android.media.soundtrigger;

import android.os.Bundle;
import com.android.internal.util.function.QuadConsumer;
import java.util.UUID;
/* loaded from: classes2.dex */
public final /* synthetic */ class SoundTriggerDetectionService$1$$ExternalSyntheticLambda0 implements QuadConsumer {
    public static final /* synthetic */ SoundTriggerDetectionService$1$$ExternalSyntheticLambda0 INSTANCE = new SoundTriggerDetectionService$1$$ExternalSyntheticLambda0();

    private /* synthetic */ SoundTriggerDetectionService$1$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.internal.util.function.QuadConsumer
    public final void accept(Object obj, Object obj2, Object obj3, Object obj4) {
        ((SoundTriggerDetectionService) obj).setClient((UUID) obj2, (Bundle) obj3, (ISoundTriggerDetectionServiceClient) obj4);
    }
}
