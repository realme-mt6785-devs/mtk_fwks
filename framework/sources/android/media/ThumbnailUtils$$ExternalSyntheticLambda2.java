package android.media;

import java.io.File;
import java.util.function.ToIntFunction;
/* loaded from: classes2.dex */
public final /* synthetic */ class ThumbnailUtils$$ExternalSyntheticLambda2 implements ToIntFunction {
    public static final /* synthetic */ ThumbnailUtils$$ExternalSyntheticLambda2 INSTANCE = new ThumbnailUtils$$ExternalSyntheticLambda2();

    private /* synthetic */ ThumbnailUtils$$ExternalSyntheticLambda2() {
    }

    @Override // java.util.function.ToIntFunction
    public final int applyAsInt(Object obj) {
        return ThumbnailUtils.lambda$createAudioThumbnail$1((File) obj);
    }
}
