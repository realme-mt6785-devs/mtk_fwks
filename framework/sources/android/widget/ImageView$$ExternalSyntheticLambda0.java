package android.widget;

import android.graphics.ImageDecoder;
/* loaded from: classes3.dex */
public final /* synthetic */ class ImageView$$ExternalSyntheticLambda0 implements ImageDecoder.OnHeaderDecodedListener {
    public static final /* synthetic */ ImageView$$ExternalSyntheticLambda0 INSTANCE = new ImageView$$ExternalSyntheticLambda0();

    private /* synthetic */ ImageView$$ExternalSyntheticLambda0() {
    }

    @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
    public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
        imageDecoder.setAllocator(1);
    }
}
