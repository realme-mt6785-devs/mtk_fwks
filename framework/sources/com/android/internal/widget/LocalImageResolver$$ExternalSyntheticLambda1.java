package com.android.internal.widget;

import android.graphics.ImageDecoder;
/* loaded from: classes4.dex */
public final /* synthetic */ class LocalImageResolver$$ExternalSyntheticLambda1 implements ImageDecoder.OnHeaderDecodedListener {
    public static final /* synthetic */ LocalImageResolver$$ExternalSyntheticLambda1 INSTANCE = new LocalImageResolver$$ExternalSyntheticLambda1();

    private /* synthetic */ LocalImageResolver$$ExternalSyntheticLambda1() {
    }

    @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
    public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
        LocalImageResolver.onHeaderDecoded(imageDecoder, imageInfo, source);
    }
}
