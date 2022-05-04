package android.media;

import java.io.File;
import java.io.FilenameFilter;
/* loaded from: classes2.dex */
public final /* synthetic */ class ThumbnailUtils$$ExternalSyntheticLambda0 implements FilenameFilter {
    public static final /* synthetic */ ThumbnailUtils$$ExternalSyntheticLambda0 INSTANCE = new ThumbnailUtils$$ExternalSyntheticLambda0();

    private /* synthetic */ ThumbnailUtils$$ExternalSyntheticLambda0() {
    }

    @Override // java.io.FilenameFilter
    public final boolean accept(File file, String str) {
        return str.toLowerCase();
    }
}
