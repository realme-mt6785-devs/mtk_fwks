package android.media.audiopolicy;

import java.util.function.Predicate;
/* loaded from: classes2.dex */
public final /* synthetic */ class AudioPolicy$$ExternalSyntheticLambda0 implements Predicate {
    public static final /* synthetic */ AudioPolicy$$ExternalSyntheticLambda0 INSTANCE = new AudioPolicy$$ExternalSyntheticLambda0();

    private /* synthetic */ AudioPolicy$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return AudioPolicy.lambda$isLoopbackRenderPolicy$0((AudioMix) obj);
    }
}
