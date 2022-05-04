package android.graphics.drawable;

import android.graphics.ImageDecoder;
/* loaded from: classes.dex */
public final /* synthetic */ class BitmapDrawable$$ExternalSyntheticLambda2 implements ImageDecoder.OnHeaderDecodedListener {
    public static final /* synthetic */ BitmapDrawable$$ExternalSyntheticLambda2 INSTANCE = new BitmapDrawable$$ExternalSyntheticLambda2();

    private /* synthetic */ BitmapDrawable$$ExternalSyntheticLambda2() {
    }

    @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
    public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
        imageDecoder.setAllocator(1);
    }
}
