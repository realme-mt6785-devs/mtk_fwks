package android.view;

import android.graphics.Insets;
import android.graphics.Rect;
/* loaded from: classes3.dex */
public interface IInsetsSourceExt {
    default void setFormInsetsAnimation(boolean isFormInsetsAnimation) {
    }

    default boolean shouldGetIntersection(int type) {
        return false;
    }

    default Insets getNavBarInsets(int type, Rect tmpFrame) {
        return null;
    }
}
