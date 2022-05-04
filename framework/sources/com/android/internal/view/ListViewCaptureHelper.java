package com.android.internal.view;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import com.android.internal.view.ScrollCaptureViewHelper;
/* loaded from: classes4.dex */
public class ListViewCaptureHelper implements ScrollCaptureViewHelper<ListView> {
    private static final String TAG = "LVCaptureHelper";
    private int mOverScrollMode;
    private boolean mScrollBarWasEnabled;
    private int mScrollDelta;

    public void onPrepareForStart(ListView view, Rect scrollBounds) {
        this.mScrollDelta = 0;
        this.mOverScrollMode = view.getOverScrollMode();
        view.setOverScrollMode(2);
        this.mScrollBarWasEnabled = view.isVerticalScrollBarEnabled();
        view.setVerticalScrollBarEnabled(false);
    }

    public ScrollCaptureViewHelper.ScrollResult onScrollRequested(ListView listView, Rect scrollBounds, Rect requestRect) {
        Log.d(TAG, "-----------------------------------------------------------");
        Log.d(TAG, "onScrollRequested(scrollBounds=" + scrollBounds + ", requestRect=" + requestRect + ")");
        ScrollCaptureViewHelper.ScrollResult result = new ScrollCaptureViewHelper.ScrollResult();
        result.requestedArea = new Rect(requestRect);
        result.scrollDelta = this.mScrollDelta;
        result.availableArea = new Rect();
        if (!listView.isVisibleToUser() || listView.getChildCount() == 0) {
            Log.w(TAG, "listView is empty or not visible, cannot continue");
            return result;
        }
        Rect requestedContainerBounds = ScrollCaptureViewSupport.transformFromRequestToContainer(this.mScrollDelta, scrollBounds, requestRect);
        Rect recyclerLocalVisible = new Rect();
        listView.getLocalVisibleRect(recyclerLocalVisible);
        Rect adjustedContainerBounds = new Rect(requestedContainerBounds);
        int remainingHeight = recyclerLocalVisible.height() - requestedContainerBounds.height();
        if (remainingHeight > 0) {
            adjustedContainerBounds.inset(0, (-remainingHeight) / 2);
        }
        int scrollAmount = ScrollCaptureViewSupport.computeScrollAmount(recyclerLocalVisible, adjustedContainerBounds);
        if (scrollAmount < 0) {
            Log.d(TAG, "About to scroll UP (content moves down within parent)");
        } else if (scrollAmount > 0) {
            Log.d(TAG, "About to scroll DOWN (content moves up within parent)");
        }
        Log.d(TAG, "scrollAmount: " + scrollAmount);
        View refView = ScrollCaptureViewSupport.findScrollingReferenceView(listView, scrollAmount);
        int refTop = refView.getTop();
        listView.scrollListBy(scrollAmount);
        int scrollDistance = refTop - refView.getTop();
        Log.d(TAG, "Parent view has scrolled vertically by " + scrollDistance + " px");
        int i = this.mScrollDelta + scrollDistance;
        this.mScrollDelta = i;
        result.scrollDelta = i;
        if (scrollDistance != 0) {
            Log.d(TAG, "Scroll delta is now " + this.mScrollDelta + " px");
        }
        Rect requestedContainerBounds2 = new Rect(ScrollCaptureViewSupport.transformFromRequestToContainer(this.mScrollDelta, scrollBounds, requestRect));
        listView.getLocalVisibleRect(recyclerLocalVisible);
        if (requestedContainerBounds2.intersect(recyclerLocalVisible)) {
            result.availableArea = ScrollCaptureViewSupport.transformFromContainerToRequest(this.mScrollDelta, scrollBounds, requestedContainerBounds2);
        }
        Log.d(TAG, "-----------------------------------------------------------");
        return result;
    }

    public void onPrepareForEnd(ListView listView) {
        listView.scrollListBy(-this.mScrollDelta);
        listView.setOverScrollMode(this.mOverScrollMode);
        listView.setVerticalScrollBarEnabled(this.mScrollBarWasEnabled);
    }
}
