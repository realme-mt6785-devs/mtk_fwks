package android.graphics.drawable;

import android.graphics.ImageDecoder;
/* loaded from: classes.dex */
public final /* synthetic */ class AnimatedImageDrawable$$ExternalSyntheticLambda0 implements ImageDecoder.OnHeaderDecodedListener {
    public static final /* synthetic */ AnimatedImageDrawable$$ExternalSyntheticLambda0 INSTANCE = new AnimatedImageDrawable$$ExternalSyntheticLambda0();

    private /* synthetic */ AnimatedImageDrawable$$ExternalSyntheticLambda0() {
    }

    @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
    public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
        AnimatedImageDrawable.lambda$updateStateFromTypedArray$0(imageDecoder, imageInfo, source);
    }
}
