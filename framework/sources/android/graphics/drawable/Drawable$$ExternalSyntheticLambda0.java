package android.graphics.drawable;

import android.graphics.ImageDecoder;
/* loaded from: classes.dex */
public final /* synthetic */ class Drawable$$ExternalSyntheticLambda0 implements ImageDecoder.OnHeaderDecodedListener {
    public static final /* synthetic */ Drawable$$ExternalSyntheticLambda0 INSTANCE = new Drawable$$ExternalSyntheticLambda0();

    private /* synthetic */ Drawable$$ExternalSyntheticLambda0() {
    }

    @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
    public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
        Drawable.lambda$getBitmapDrawable$1(imageDecoder, imageInfo, source);
    }
}
