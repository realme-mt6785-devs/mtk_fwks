package com.android.internal.view;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.android.internal.view.ScrollCaptureViewHelper;
/* loaded from: classes4.dex */
public class RecyclerViewCaptureHelper implements ScrollCaptureViewHelper<ViewGroup> {
    private static final String TAG = "RVCaptureHelper";
    private int mOverScrollMode;
    private boolean mScrollBarWasEnabled;
    private int mScrollDelta;

    public void onPrepareForStart(ViewGroup view, Rect scrollBounds) {
        this.mScrollDelta = 0;
        this.mOverScrollMode = view.getOverScrollMode();
        view.setOverScrollMode(2);
        this.mScrollBarWasEnabled = view.isVerticalScrollBarEnabled();
        view.setVerticalScrollBarEnabled(false);
    }

    public ScrollCaptureViewHelper.ScrollResult onScrollRequested(ViewGroup recyclerView, Rect scrollBounds, Rect requestRect) {
        ScrollCaptureViewHelper.ScrollResult result = new ScrollCaptureViewHelper.ScrollResult();
        result.requestedArea = new Rect(requestRect);
        result.scrollDelta = this.mScrollDelta;
        result.availableArea = new Rect();
        if (!recyclerView.isVisibleToUser() || recyclerView.getChildCount() == 0) {
            Log.w(TAG, "recyclerView is empty or not visible, cannot continue");
            return result;
        }
        Rect requestedContainerBounds = new Rect(requestRect);
        requestedContainerBounds.offset(0, -this.mScrollDelta);
        requestedContainerBounds.offset(scrollBounds.left, scrollBounds.top);
        View anchor = findChildNearestTarget(recyclerView, requestedContainerBounds);
        if (anchor == null) {
            Log.w(TAG, "Failed to locate anchor view");
            return result;
        }
        Rect requestedContentBounds = new Rect(requestedContainerBounds);
        recyclerView.offsetRectIntoDescendantCoords(anchor, requestedContentBounds);
        int prevAnchorTop = anchor.getTop();
        Rect input = new Rect(requestedContentBounds);
        int remainingHeight = ((recyclerView.getHeight() - recyclerView.getPaddingTop()) - recyclerView.getPaddingBottom()) - input.height();
        if (remainingHeight > 0) {
            input.inset(0, (-remainingHeight) / 2);
        }
        if (recyclerView.requestChildRectangleOnScreen(anchor, input, true)) {
            int scrolled = prevAnchorTop - anchor.getTop();
            int i = this.mScrollDelta + scrolled;
            this.mScrollDelta = i;
            result.scrollDelta = i;
        }
        requestedContainerBounds.set(requestedContentBounds);
        recyclerView.offsetDescendantRectToMyCoords(anchor, requestedContainerBounds);
        Rect recyclerLocalVisible = new Rect(scrollBounds);
        recyclerView.getLocalVisibleRect(recyclerLocalVisible);
        if (!requestedContainerBounds.intersect(recyclerLocalVisible)) {
            return result;
        }
        Rect available = new Rect(requestedContainerBounds);
        available.offset(-scrollBounds.left, -scrollBounds.top);
        available.offset(0, this.mScrollDelta);
        result.availableArea = available;
        return result;
    }

    static View findChildNearestTarget(ViewGroup parent, Rect targetRect) {
        View selected = null;
        int minCenterDistance = Integer.MAX_VALUE;
        int preferredDistance = (int) (targetRect.height() * 0.25f);
        Rect parentLocalVis = new Rect();
        parent.getLocalVisibleRect(parentLocalVis);
        Rect frame = new Rect();
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            child.getHitRect(frame);
            if (child.getVisibility() == 0) {
                int centerDistance = Math.abs(targetRect.centerY() - frame.centerY());
                if (centerDistance < minCenterDistance) {
                    minCenterDistance = centerDistance;
                    selected = child;
                } else if (frame.intersect(targetRect) && frame.height() > preferredDistance) {
                    selected = child;
                }
            }
        }
        return selected;
    }

    public void onPrepareForEnd(ViewGroup view) {
        view.scrollBy(0, -this.mScrollDelta);
        view.setOverScrollMode(this.mOverScrollMode);
        view.setVerticalScrollBarEnabled(this.mScrollBarWasEnabled);
    }
}
