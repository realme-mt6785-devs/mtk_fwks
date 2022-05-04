package com.android.internal.view;

import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import com.android.internal.view.ScrollCaptureViewHelper;
/* loaded from: classes4.dex */
public class ScrollViewCaptureHelper implements ScrollCaptureViewHelper<ViewGroup> {
    private int mOverScrollMode;
    private boolean mScrollBarEnabled;
    private int mStartScrollY;

    public void onPrepareForStart(ViewGroup view, Rect scrollBounds) {
        this.mStartScrollY = view.getScrollY();
        int overScrollMode = view.getOverScrollMode();
        this.mOverScrollMode = overScrollMode;
        if (overScrollMode != 2) {
            view.setOverScrollMode(2);
        }
        boolean isVerticalScrollBarEnabled = view.isVerticalScrollBarEnabled();
        this.mScrollBarEnabled = isVerticalScrollBarEnabled;
        if (isVerticalScrollBarEnabled) {
            view.setVerticalScrollBarEnabled(false);
        }
    }

    public ScrollCaptureViewHelper.ScrollResult onScrollRequested(ViewGroup view, Rect scrollBounds, Rect requestRect) {
        int scrollDelta = view.getScrollY() - this.mStartScrollY;
        ScrollCaptureViewHelper.ScrollResult result = new ScrollCaptureViewHelper.ScrollResult();
        result.requestedArea = new Rect(requestRect);
        result.scrollDelta = scrollDelta;
        result.availableArea = new Rect();
        View contentView = view.getChildAt(0);
        if (contentView == null) {
            return result;
        }
        Rect requestedContainerBounds = new Rect(requestRect);
        requestedContainerBounds.offset(0, -scrollDelta);
        requestedContainerBounds.offset(scrollBounds.left, scrollBounds.top);
        Rect requestedContentBounds = new Rect(requestedContainerBounds);
        requestedContentBounds.offset(view.getScrollX() - contentView.getLeft(), view.getScrollY() - contentView.getTop());
        Rect input = new Rect(requestedContentBounds);
        int remainingHeight = ((view.getHeight() - view.getPaddingTop()) - view.getPaddingBottom()) - input.height();
        if (remainingHeight > 0) {
            input.inset(0, (-remainingHeight) / 2);
        }
        contentView.requestRectangleOnScreen(input, true);
        int scrollDelta2 = view.getScrollY() - this.mStartScrollY;
        result.scrollDelta = scrollDelta2;
        Point offset = new Point();
        Rect available = new Rect(requestedContentBounds);
        if (!view.getChildVisibleRect(contentView, available, offset)) {
            available.setEmpty();
            result.availableArea = available;
            return result;
        }
        available.offset(-offset.x, -offset.y);
        available.offset(contentView.getLeft() - view.getScrollX(), contentView.getTop() - view.getScrollY());
        available.offset(-scrollBounds.left, -scrollBounds.top);
        available.offset(0, scrollDelta2);
        result.availableArea = new Rect(available);
        return result;
    }

    public void onPrepareForEnd(ViewGroup view) {
        view.scrollTo(0, this.mStartScrollY);
        int i = this.mOverScrollMode;
        if (i != 2) {
            view.setOverScrollMode(i);
        }
        if (this.mScrollBarEnabled) {
            view.setVerticalScrollBarEnabled(true);
        }
    }
}
