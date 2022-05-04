package android.media;

import android.media.audiopolicy.AudioMixingRule;
import java.util.function.ToIntFunction;
/* loaded from: classes2.dex */
public final /* synthetic */ class AudioPlaybackCaptureConfiguration$$ExternalSyntheticLambda4 implements ToIntFunction {
    public static final /* synthetic */ AudioPlaybackCaptureConfiguration$$ExternalSyntheticLambda4 INSTANCE = new AudioPlaybackCaptureConfiguration$$ExternalSyntheticLambda4();

    private /* synthetic */ AudioPlaybackCaptureConfiguration$$ExternalSyntheticLambda4() {
    }

    @Override // java.util.function.ToIntFunction
    public final int applyAsInt(Object obj) {
        int usage;
        usage = ((AudioMixingRule.AudioMixMatchCriterion) obj).getAudioAttributes().getUsage();
        return usage;
    }
}
