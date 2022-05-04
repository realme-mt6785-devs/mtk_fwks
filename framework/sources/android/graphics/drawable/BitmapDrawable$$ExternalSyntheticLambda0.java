package android.graphics.drawable;

import android.graphics.ImageDecoder;
/* loaded from: classes.dex */
public final /* synthetic */ class BitmapDrawable$$ExternalSyntheticLambda0 implements ImageDecoder.OnHeaderDecodedListener {
    public static final /* synthetic */ BitmapDrawable$$ExternalSyntheticLambda0 INSTANCE = new BitmapDrawable$$ExternalSyntheticLambda0();

    private /* synthetic */ BitmapDrawable$$ExternalSyntheticLambda0() {
    }

    @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
    public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
        imageDecoder.setAllocator(1);
    }
}
