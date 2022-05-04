package android.widget;

import android.content.Context;
/* loaded from: classes3.dex */
public interface IOplusScrollViewExt {
    default OverScroller createSpringOverScrollerInstance(Context context) {
        return null;
    }

    default void onOverScrolled(OverScroller scroller, int scrollRange, int x, int y) {
    }

    default boolean canFling(boolean canFling) {
        return false;
    }

    default boolean shouldDisplayEdgeEffects(boolean shouldDisplay) {
        return false;
    }

    default void hookInitScrollView(Context context) {
    }
}
