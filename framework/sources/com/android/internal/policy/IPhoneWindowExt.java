package com.android.internal.policy;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.ViewGroup;
/* loaded from: classes4.dex */
public interface IPhoneWindowExt {
    default void setSystemBarColor(int color) {
    }

    default ViewGroup getContentParent() {
        return null;
    }

    default CharSequence getWindowTitle() {
        return "";
    }

    default boolean isUseDefaultNavigationBarColor() {
        return true;
    }

    default void hookGenerateLayout(PhoneWindow phoneWindow, TypedArray typedArray, Context context) {
        phoneWindow.mNavigationBarColor = typedArray.getColor(35, -16777216);
    }
}
