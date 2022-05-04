package com.android.internal.policy;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.view.IWindow;
import android.view.MotionEvent;
import android.view.WindowInsets;
/* loaded from: classes4.dex */
public interface IDecorViewExt {
    default boolean hookDecorView(Context context, boolean defaultForceWindowDrawsBarBackgrounds) {
        return defaultForceWindowDrawsBarBackgrounds;
    }

    default void hookSetHandledPrimaryActionMode(ObjectAnimator fadeAnim) {
    }

    default void hookOnDestroyActionMode(ObjectAnimator fadeAnim) {
    }

    default boolean isDebugSystemBar() {
        return false;
    }

    default int getBottomInset(boolean isImeWindow, int normalBottomInset, WindowInsets insets) {
        return normalBottomInset;
    }

    default int calculateNavigationBarColor(boolean isImeWindow, int defaultColor) {
        return defaultColor;
    }

    default void updateColorNavigationGuardColor(int color) {
    }

    default void onConfigurationChanged(Configuration newConfig) {
    }

    default void updatePhoneWindow(PhoneWindow window) {
    }

    default void inputLog(String level, String tag, String msg, MotionEvent event) {
    }

    default int getLegacyNavBarBackgroundColor() {
        return -16777216;
    }

    default void relayoutZoomView() {
    }

    default void draw(Canvas canvas, int width, int height) {
    }

    default void setWindow(IWindow window) {
    }

    default void onConfigurationChanged(Configuration newConfig, Context context) {
    }

    default boolean remeasureZoomView(int widthMeasureSpec, int heightMeasureSpec) {
        return false;
    }

    default boolean adjustForSplitWindow(boolean isStatusColorViewState) {
        return false;
    }

    default void setSplitWindow(boolean splitWindow) {
    }

    default void initDarkModeBackgroundColor() {
    }

    default void requestLayoutForDarkModeBackgroundView() {
    }
}
