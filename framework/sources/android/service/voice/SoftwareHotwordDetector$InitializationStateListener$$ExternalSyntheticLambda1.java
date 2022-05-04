package android.service.voice;

import android.service.voice.HotwordDetector;
import java.util.function.Consumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class SoftwareHotwordDetector$InitializationStateListener$$ExternalSyntheticLambda1 implements Consumer {
    public static final /* synthetic */ SoftwareHotwordDetector$InitializationStateListener$$ExternalSyntheticLambda1 INSTANCE = new SoftwareHotwordDetector$InitializationStateListener$$ExternalSyntheticLambda1();

    private /* synthetic */ SoftwareHotwordDetector$InitializationStateListener$$ExternalSyntheticLambda1() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((HotwordDetector.Callback) obj).onHotwordDetectionServiceRestarted();
    }
}
