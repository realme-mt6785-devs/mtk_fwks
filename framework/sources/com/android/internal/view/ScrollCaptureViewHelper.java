package com.android.internal.view;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
/* loaded from: classes4.dex */
public interface ScrollCaptureViewHelper<V extends View> {
    public static final int DOWN = 1;
    public static final int UP = -1;

    void onPrepareForEnd(V v);

    void onPrepareForStart(V v, Rect rect);

    ScrollResult onScrollRequested(V v, Rect rect, Rect rect2);

    /* loaded from: classes4.dex */
    public static class ScrollResult {
        public Rect availableArea;
        public Rect requestedArea;
        public int scrollDelta;

        public String toString() {
            return "ScrollResult{requestedArea=" + this.requestedArea + ", availableArea=" + this.availableArea + ", scrollDelta=" + this.scrollDelta + '}';
        }
    }

    default boolean onAcceptSession(V view) {
        return view.isVisibleToUser() && (view.canScrollVertically(-1) || view.canScrollVertically(1));
    }

    default Rect onComputeScrollBounds(V view) {
        Rect bounds = new Rect(0, 0, view.getWidth(), view.getHeight());
        if ((view instanceof ViewGroup) && ((ViewGroup) view).getClipToPadding()) {
            bounds.inset(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
        }
        return bounds;
    }
}
