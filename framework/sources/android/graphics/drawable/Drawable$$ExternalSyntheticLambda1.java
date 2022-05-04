package android.graphics.drawable;

import android.graphics.ImageDecoder;
/* loaded from: classes.dex */
public final /* synthetic */ class Drawable$$ExternalSyntheticLambda1 implements ImageDecoder.OnPartialImageListener {
    public static final /* synthetic */ Drawable$$ExternalSyntheticLambda1 INSTANCE = new Drawable$$ExternalSyntheticLambda1();

    private /* synthetic */ Drawable$$ExternalSyntheticLambda1() {
    }

    @Override // android.graphics.ImageDecoder.OnPartialImageListener
    public final boolean onPartialImage(ImageDecoder.DecodeException decodeException) {
        return Drawable.lambda$getBitmapDrawable$0(decodeException);
    }
}
