package com.android.internal.widget;

import android.content.Context;
import android.graphics.ImageDecoder;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.util.Size;
import java.io.IOException;
/* loaded from: classes4.dex */
public class LocalImageResolver {
    private static final int MAX_SAFE_ICON_SIZE_PX = 480;
    private static final String TAG = LocalImageResolver.class.getSimpleName();

    public static Drawable resolveImage(Uri uri, Context context) throws IOException {
        ImageDecoder.Source source = ImageDecoder.createSource(context.getContentResolver(), uri);
        Drawable drawable = ImageDecoder.decodeDrawable(source, LocalImageResolver$$ExternalSyntheticLambda1.INSTANCE);
        return drawable;
    }

    public static Drawable resolveImage(Icon icon, Context context) throws IOException {
        Uri uri = getResolvableUri(icon);
        if (uri == null) {
            return icon.loadDrawable(context);
        }
        Drawable result = resolveImage(uri, context);
        if (icon.hasTint()) {
            result.mutate();
            result.setTintList(icon.getTintList());
            result.setTintBlendMode(icon.getTintBlendMode());
        }
        return result;
    }

    public static Drawable resolveImage(Uri uri, Context context, final int maxWidth, final int maxHeight) throws IOException {
        ImageDecoder.Source source = ImageDecoder.createSource(context.getContentResolver(), uri);
        return ImageDecoder.decodeDrawable(source, new ImageDecoder.OnHeaderDecodedListener() { // from class: com.android.internal.widget.LocalImageResolver$$ExternalSyntheticLambda0
            @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
            public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source2) {
                LocalImageResolver.lambda$resolveImage$0(maxWidth, maxHeight, imageDecoder, imageInfo, source2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$resolveImage$0(int maxWidth, int maxHeight, ImageDecoder decoder, ImageDecoder.ImageInfo info, ImageDecoder.Source unused) {
        Size size = info.getSize();
        if (size.getWidth() > size.getHeight()) {
            if (size.getWidth() > maxWidth) {
                int targetHeight = (size.getHeight() * maxWidth) / size.getWidth();
                decoder.setTargetSize(maxWidth, targetHeight);
            }
        } else if (size.getHeight() > maxHeight) {
            int targetWidth = (size.getWidth() * maxHeight) / size.getHeight();
            decoder.setTargetSize(targetWidth, maxHeight);
        }
    }

    private static int getPowerOfTwoForSampleRatio(double ratio) {
        int k = Integer.highestOneBit((int) Math.floor(ratio));
        return Math.max(1, k);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void onHeaderDecoded(ImageDecoder decoder, ImageDecoder.ImageInfo info, ImageDecoder.Source source) {
        double ratio;
        Size size = info.getSize();
        int originalSize = Math.max(size.getHeight(), size.getWidth());
        if (originalSize > 480) {
            ratio = (originalSize * 1.0f) / 480.0f;
        } else {
            ratio = 1.0d;
        }
        decoder.setTargetSampleSize(getPowerOfTwoForSampleRatio(ratio));
    }

    public static Uri getResolvableUri(Icon icon) {
        if (icon == null) {
            return null;
        }
        if (icon.getType() == 4 || icon.getType() == 6) {
            return icon.getUri();
        }
        return null;
    }
}
