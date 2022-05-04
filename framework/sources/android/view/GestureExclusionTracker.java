package android.view;

import android.graphics.Rect;
import com.android.internal.util.Preconditions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class GestureExclusionTracker {
    private boolean mGestureExclusionViewsChanged = false;
    private boolean mRootGestureExclusionRectsChanged = false;
    private List<Rect> mRootGestureExclusionRects = Collections.emptyList();
    private List<GestureExclusionViewInfo> mGestureExclusionViewInfos = new ArrayList();
    private List<Rect> mGestureExclusionRects = Collections.emptyList();

    public void updateRectsForView(View view) {
        boolean found = false;
        Iterator<GestureExclusionViewInfo> i = this.mGestureExclusionViewInfos.iterator();
        while (true) {
            if (!i.hasNext()) {
                break;
            }
            GestureExclusionViewInfo info = i.next();
            View v = info.getView();
            if (v == null || !v.isAttachedToWindow() || !v.isAggregatedVisible()) {
                this.mGestureExclusionViewsChanged = true;
                i.remove();
            } else if (v == view) {
                found = true;
                info.mDirty = true;
                break;
            }
        }
        if (!found && view.isAttachedToWindow()) {
            this.mGestureExclusionViewInfos.add(new GestureExclusionViewInfo(view));
            this.mGestureExclusionViewsChanged = true;
        }
    }

    public List<Rect> computeChangedRects() {
        boolean changed = this.mRootGestureExclusionRectsChanged;
        Iterator<GestureExclusionViewInfo> i = this.mGestureExclusionViewInfos.iterator();
        List<Rect> rects = new ArrayList<>(this.mRootGestureExclusionRects);
        while (i.hasNext()) {
            GestureExclusionViewInfo info = i.next();
            switch (info.update()) {
                case 0:
                    changed = true;
                    break;
                case 2:
                    this.mGestureExclusionViewsChanged = true;
                    i.remove();
                    continue;
            }
            rects.addAll(info.mExclusionRects);
        }
        if (!changed && !this.mGestureExclusionViewsChanged) {
            return null;
        }
        this.mGestureExclusionViewsChanged = false;
        this.mRootGestureExclusionRectsChanged = false;
        if (this.mGestureExclusionRects.equals(rects)) {
            return null;
        }
        this.mGestureExclusionRects = rects;
        return rects;
    }

    public void setRootSystemGestureExclusionRects(List<Rect> rects) {
        Preconditions.checkNotNull(rects, "rects must not be null");
        this.mRootGestureExclusionRects = rects;
        this.mRootGestureExclusionRectsChanged = true;
    }

    public List<Rect> getRootSystemGestureExclusionRects() {
        return this.mRootGestureExclusionRects;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class GestureExclusionViewInfo {
        public static final int CHANGED = 0;
        public static final int GONE = 2;
        public static final int UNCHANGED = 1;
        boolean mDirty = true;
        List<Rect> mExclusionRects = Collections.emptyList();
        private final WeakReference<View> mView;

        GestureExclusionViewInfo(View view) {
            this.mView = new WeakReference<>(view);
        }

        public View getView() {
            return this.mView.get();
        }

        public int update() {
            View excludedView = getView();
            if (excludedView == null || !excludedView.isAttachedToWindow() || !excludedView.isAggregatedVisible()) {
                return 2;
            }
            List<Rect> localRects = excludedView.getSystemGestureExclusionRects();
            List<Rect> newRects = new ArrayList<>(localRects.size());
            for (Rect src : localRects) {
                Rect mappedRect = new Rect(src);
                ViewParent p = excludedView.getParent();
                if (p != null && p.getChildVisibleRect(excludedView, mappedRect, null)) {
                    newRects.add(mappedRect);
                }
            }
            if (this.mExclusionRects.equals(localRects)) {
                return 1;
            }
            this.mExclusionRects = newRects;
            return 0;
        }
    }
}
