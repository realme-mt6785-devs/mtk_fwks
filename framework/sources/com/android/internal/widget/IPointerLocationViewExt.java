package com.android.internal.widget;
/* loaded from: classes4.dex */
public interface IPointerLocationViewExt {
    default boolean enableInputLogV() {
        return false;
    }

    default boolean inputLogd(String tag, String msg) {
        return false;
    }
}
