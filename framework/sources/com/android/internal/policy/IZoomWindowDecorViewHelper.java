package com.android.internal.policy;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.view.IWindow;
/* loaded from: classes4.dex */
public interface IZoomWindowDecorViewHelper {
    public static final IZoomWindowDecorViewHelper DEFAULT = new IZoomWindowDecorViewHelper() { // from class: com.android.internal.policy.IZoomWindowDecorViewHelper.1
    };

    default void relayoutZoomView(PhoneWindow mWindow) {
    }

    default void relayoutZoomView() {
    }

    default void draw(Canvas canvas, int width, int height) {
    }

    default void setWindow(IWindow window) {
    }

    default void onConfigurationChanged(Configuration newConfig, Context context) {
    }

    default void removeZoomView() {
    }

    default boolean remeasureZoomView(int widthMeasureSpec, int heightMeasureSpec) {
        return false;
    }
}
