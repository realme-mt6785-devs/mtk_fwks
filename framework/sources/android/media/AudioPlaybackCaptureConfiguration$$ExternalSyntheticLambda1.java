package android.media;

import android.media.audiopolicy.AudioMixingRule;
import java.util.function.ToIntFunction;
/* loaded from: classes2.dex */
public final /* synthetic */ class AudioPlaybackCaptureConfiguration$$ExternalSyntheticLambda1 implements ToIntFunction {
    public static final /* synthetic */ AudioPlaybackCaptureConfiguration$$ExternalSyntheticLambda1 INSTANCE = new AudioPlaybackCaptureConfiguration$$ExternalSyntheticLambda1();

    private /* synthetic */ AudioPlaybackCaptureConfiguration$$ExternalSyntheticLambda1() {
    }

    @Override // java.util.function.ToIntFunction
    public final int applyAsInt(Object obj) {
        int intProp;
        intProp = ((AudioMixingRule.AudioMixMatchCriterion) obj).getIntProp();
        return intProp;
    }
}
