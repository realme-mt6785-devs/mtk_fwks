package android.service.voice;

import android.service.voice.HotwordDetector;
import java.util.function.BiConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class SoftwareHotwordDetector$InitializationStateListener$$ExternalSyntheticLambda0 implements BiConsumer {
    public static final /* synthetic */ SoftwareHotwordDetector$InitializationStateListener$$ExternalSyntheticLambda0 INSTANCE = new SoftwareHotwordDetector$InitializationStateListener$$ExternalSyntheticLambda0();

    private /* synthetic */ SoftwareHotwordDetector$InitializationStateListener$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((HotwordDetector.Callback) obj).onHotwordDetectionServiceInitialized(((Integer) obj2).intValue());
    }
}
