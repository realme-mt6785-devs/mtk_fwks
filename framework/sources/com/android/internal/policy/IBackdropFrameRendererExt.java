package com.android.internal.policy;

import android.graphics.RecordingCanvas;
import android.graphics.RenderNode;
import android.graphics.drawable.Drawable;
/* loaded from: classes4.dex */
public interface IBackdropFrameRendererExt {
    default void drawDarkModeBackground(DecorView decorView, Drawable drawable, int lastCaptionHeight, int top, int left, int height, int width, RecordingCanvas canvas, RenderNode renderNode) {
    }

    default void checkSystemBarForceDark(DecorView decorView, RenderNode systemBarBackgroundNode) {
    }
}
