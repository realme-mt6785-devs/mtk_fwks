package android.graphics.drawable;

import android.graphics.ImageDecoder;
/* loaded from: classes.dex */
public final /* synthetic */ class BitmapDrawable$$ExternalSyntheticLambda1 implements ImageDecoder.OnHeaderDecodedListener {
    public static final /* synthetic */ BitmapDrawable$$ExternalSyntheticLambda1 INSTANCE = new BitmapDrawable$$ExternalSyntheticLambda1();

    private /* synthetic */ BitmapDrawable$$ExternalSyntheticLambda1() {
    }

    @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
    public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
        imageDecoder.setAllocator(1);
    }
}
