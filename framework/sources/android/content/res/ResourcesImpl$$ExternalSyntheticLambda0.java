package android.content.res;

import android.graphics.ImageDecoder;
/* loaded from: classes.dex */
public final /* synthetic */ class ResourcesImpl$$ExternalSyntheticLambda0 implements ImageDecoder.OnHeaderDecodedListener {
    public static final /* synthetic */ ResourcesImpl$$ExternalSyntheticLambda0 INSTANCE = new ResourcesImpl$$ExternalSyntheticLambda0();

    private /* synthetic */ ResourcesImpl$$ExternalSyntheticLambda0() {
    }

    @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
    public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
        imageDecoder.setAllocator(1);
    }
}
