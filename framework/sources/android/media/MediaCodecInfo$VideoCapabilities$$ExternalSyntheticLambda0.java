package android.media;

import android.media.MediaCodecInfo;
import java.util.Comparator;
/* loaded from: classes2.dex */
public final /* synthetic */ class MediaCodecInfo$VideoCapabilities$$ExternalSyntheticLambda0 implements Comparator {
    public static final /* synthetic */ MediaCodecInfo$VideoCapabilities$$ExternalSyntheticLambda0 INSTANCE = new MediaCodecInfo$VideoCapabilities$$ExternalSyntheticLambda0();

    private /* synthetic */ MediaCodecInfo$VideoCapabilities$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return MediaCodecInfo.VideoCapabilities.lambda$getPerformancePoints$0((MediaCodecInfo.VideoCapabilities.PerformancePoint) obj, (MediaCodecInfo.VideoCapabilities.PerformancePoint) obj2);
    }
}
