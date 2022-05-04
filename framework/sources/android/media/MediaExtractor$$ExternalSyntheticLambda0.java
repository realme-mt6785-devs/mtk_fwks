package android.media;

import android.media.DrmInitData;
import java.util.UUID;
import java.util.function.Function;
/* loaded from: classes2.dex */
public final /* synthetic */ class MediaExtractor$$ExternalSyntheticLambda0 implements Function {
    public static final /* synthetic */ MediaExtractor$$ExternalSyntheticLambda0 INSTANCE = new MediaExtractor$$ExternalSyntheticLambda0();

    private /* synthetic */ MediaExtractor$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        UUID uuid;
        uuid = ((DrmInitData.SchemeInitData) obj).uuid;
        return uuid;
    }
}
