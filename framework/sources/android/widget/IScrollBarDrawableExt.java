package android.widget;

import android.graphics.Rect;
import android.view.IViewExt;
/* loaded from: classes3.dex */
public interface IScrollBarDrawableExt {
    default void setScrollBarEffect(IViewExt effect) {
    }

    default void getDrawRect(Rect r) {
    }

    default int getThumbLength(int size, int thickness, int extent, int range) {
        return -1;
    }
}
