package android.preference;

import android.media.audiopolicy.AudioProductStrategy;
import java.util.function.Function;
/* loaded from: classes2.dex */
public final /* synthetic */ class SeekBarVolumizer$$ExternalSyntheticLambda0 implements Function {
    public static final /* synthetic */ SeekBarVolumizer$$ExternalSyntheticLambda0 INSTANCE = new SeekBarVolumizer$$ExternalSyntheticLambda0();

    private /* synthetic */ SeekBarVolumizer$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        Integer valueOf;
        valueOf = Integer.valueOf(((AudioProductStrategy) obj).getVolumeGroupIdForAudioAttributes(AudioProductStrategy.sDefaultAttributes));
        return valueOf;
    }
}
