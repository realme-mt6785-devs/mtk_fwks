package com.android.internal.widget;

import android.view.View;
/* loaded from: classes4.dex */
public interface IOverflowPanelExt {
    default boolean hookOverflowPanel() {
        return false;
    }

    default void hookSetScrollerbar(View overflowPanel) {
    }
}
