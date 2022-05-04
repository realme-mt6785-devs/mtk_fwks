package com.android.internal.widget;

import android.view.View;
/* loaded from: classes4.dex */
public interface INotificationActionListLayoutExt {
    default void resetCurrentMaxChildHeight(View child) {
    }

    default void setActionLayoutMeasuredDimension(int widthMeasureSpec, int heightMeasureSpec) {
    }

    default void setMaxChildHeight(int maxChildHeight) {
    }

    default int getMaxChildHeight() {
        return 0;
    }
}
