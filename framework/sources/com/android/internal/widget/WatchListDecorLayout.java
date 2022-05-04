package com.android.internal.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ListView;
import java.util.ArrayList;
/* loaded from: classes4.dex */
public class WatchListDecorLayout extends FrameLayout implements ViewTreeObserver.OnScrollChangedListener {
    private View mBottomPanel;
    private ListView mListView;
    private ViewTreeObserver mObserver;
    private int mPendingScroll;
    private View mTopPanel;
    private int mForegroundPaddingLeft = 0;
    private int mForegroundPaddingTop = 0;
    private int mForegroundPaddingRight = 0;
    private int mForegroundPaddingBottom = 0;
    private final ArrayList<View> mMatchParentChildren = new ArrayList<>(1);

    public WatchListDecorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WatchListDecorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public WatchListDecorLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mPendingScroll = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (!(child instanceof ListView)) {
                int gravity = ((FrameLayout.LayoutParams) child.getLayoutParams()).gravity & 112;
                if (gravity == 48 && this.mTopPanel == null) {
                    this.mTopPanel = child;
                } else if (gravity == 80 && this.mBottomPanel == null) {
                    this.mBottomPanel = child;
                }
            } else if (this.mListView == null) {
                ListView listView = (ListView) child;
                this.mListView = listView;
                listView.setNestedScrollingEnabled(true);
                ViewTreeObserver viewTreeObserver = this.mListView.getViewTreeObserver();
                this.mObserver = viewTreeObserver;
                viewTreeObserver.addOnScrollChangedListener(this);
            } else {
                throw new IllegalArgumentException("only one ListView child allowed");
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        this.mListView = null;
        this.mBottomPanel = null;
        this.mTopPanel = null;
        ViewTreeObserver viewTreeObserver = this.mObserver;
        if (viewTreeObserver != null) {
            if (viewTreeObserver.isAlive()) {
                this.mObserver.removeOnScrollChangedListener(this);
            }
            this.mObserver = null;
        }
    }

    private void applyMeasureToChild(View child, int widthMeasureSpec, int heightMeasureSpec) {
        int width;
        int childHeightMeasureSpec;
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
        if (lp.width == -1) {
            int width2 = Math.max(0, (((getMeasuredWidth() - getPaddingLeftWithForeground()) - getPaddingRightWithForeground()) - lp.leftMargin) - lp.rightMargin);
            width = View.MeasureSpec.makeMeasureSpec(width2, 1073741824);
        } else {
            width = getChildMeasureSpec(widthMeasureSpec, getPaddingLeftWithForeground() + getPaddingRightWithForeground() + lp.leftMargin + lp.rightMargin, lp.width);
        }
        if (lp.height == -1) {
            int height = Math.max(0, (((getMeasuredHeight() - getPaddingTopWithForeground()) - getPaddingBottomWithForeground()) - lp.topMargin) - lp.bottomMargin);
            childHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(height, 1073741824);
        } else {
            childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, getPaddingTopWithForeground() + getPaddingBottomWithForeground() + lp.topMargin + lp.bottomMargin, lp.height);
        }
        child.measure(width, childHeightMeasureSpec);
    }

    private int measureAndGetHeight(View child, int widthMeasureSpec, int heightMeasureSpec) {
        if (child == null) {
            return 0;
        }
        if (child.getVisibility() != 8) {
            applyMeasureToChild(this.mBottomPanel, widthMeasureSpec, heightMeasureSpec);
            return child.getMeasuredHeight();
        } else if (!getMeasureAllChildren()) {
            return 0;
        } else {
            applyMeasureToChild(this.mBottomPanel, widthMeasureSpec, heightMeasureSpec);
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i;
        int count = getChildCount();
        boolean measureMatchParentChildren = (View.MeasureSpec.getMode(widthMeasureSpec) == 1073741824 && View.MeasureSpec.getMode(heightMeasureSpec) == 1073741824) ? false : true;
        this.mMatchParentChildren.clear();
        int maxHeight = 0;
        int maxWidth = 0;
        int childState = 0;
        int i2 = 0;
        while (i2 < count) {
            View child = getChildAt(i2);
            if (getMeasureAllChildren() || child.getVisibility() != 8) {
                i = i2;
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) child.getLayoutParams();
                int maxWidth2 = Math.max(maxWidth, child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin);
                int maxHeight2 = Math.max(maxHeight, child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin);
                int childState2 = combineMeasuredStates(childState, child.getMeasuredState());
                if (measureMatchParentChildren && (lp.width == -1 || lp.height == -1)) {
                    this.mMatchParentChildren.add(child);
                }
                maxWidth = maxWidth2;
                maxHeight = maxHeight2;
                childState = childState2;
            } else {
                i = i2;
            }
            i2 = i + 1;
        }
        int maxWidth3 = maxWidth + getPaddingLeftWithForeground() + getPaddingRightWithForeground();
        int maxHeight3 = Math.max(maxHeight + getPaddingTopWithForeground() + getPaddingBottomWithForeground(), getSuggestedMinimumHeight());
        int maxWidth4 = Math.max(maxWidth3, getSuggestedMinimumWidth());
        Drawable drawable = getForeground();
        if (drawable != null) {
            maxHeight3 = Math.max(maxHeight3, drawable.getMinimumHeight());
            maxWidth4 = Math.max(maxWidth4, drawable.getMinimumWidth());
        }
        setMeasuredDimension(resolveSizeAndState(maxWidth4, widthMeasureSpec, childState), resolveSizeAndState(maxHeight3, heightMeasureSpec, childState << 16));
        ListView listView = this.mListView;
        if (listView != null) {
            int i3 = this.mPendingScroll;
            if (i3 != 0) {
                listView.scrollListBy(i3);
                this.mPendingScroll = 0;
            }
            int paddingTop = Math.max(this.mListView.getPaddingTop(), measureAndGetHeight(this.mTopPanel, widthMeasureSpec, heightMeasureSpec));
            int paddingBottom = Math.max(this.mListView.getPaddingBottom(), measureAndGetHeight(this.mBottomPanel, widthMeasureSpec, heightMeasureSpec));
            if (!(paddingTop == this.mListView.getPaddingTop() && paddingBottom == this.mListView.getPaddingBottom())) {
                this.mPendingScroll += this.mListView.getPaddingTop() - paddingTop;
                ListView listView2 = this.mListView;
                listView2.setPadding(listView2.getPaddingLeft(), paddingTop, this.mListView.getPaddingRight(), paddingBottom);
            }
        }
        int count2 = this.mMatchParentChildren.size();
        if (count2 > 1) {
            for (int i4 = 0; i4 < count2; i4++) {
                View child2 = this.mMatchParentChildren.get(i4);
                if (this.mListView == null || !(child2 == this.mTopPanel || child2 == this.mBottomPanel)) {
                    applyMeasureToChild(child2, widthMeasureSpec, heightMeasureSpec);
                }
            }
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void setForegroundGravity(int foregroundGravity) {
        if (getForegroundGravity() != foregroundGravity) {
            super.setForegroundGravity(foregroundGravity);
            Drawable foreground = getForeground();
            if (getForegroundGravity() != 119 || foreground == null) {
                this.mForegroundPaddingLeft = 0;
                this.mForegroundPaddingTop = 0;
                this.mForegroundPaddingRight = 0;
                this.mForegroundPaddingBottom = 0;
                return;
            }
            Rect padding = new Rect();
            if (foreground.getPadding(padding)) {
                this.mForegroundPaddingLeft = padding.left;
                this.mForegroundPaddingTop = padding.top;
                this.mForegroundPaddingRight = padding.right;
                this.mForegroundPaddingBottom = padding.bottom;
            }
        }
    }

    private int getPaddingLeftWithForeground() {
        return isForegroundInsidePadding() ? Math.max(this.mPaddingLeft, this.mForegroundPaddingLeft) : this.mPaddingLeft + this.mForegroundPaddingLeft;
    }

    private int getPaddingRightWithForeground() {
        return isForegroundInsidePadding() ? Math.max(this.mPaddingRight, this.mForegroundPaddingRight) : this.mPaddingRight + this.mForegroundPaddingRight;
    }

    private int getPaddingTopWithForeground() {
        return isForegroundInsidePadding() ? Math.max(this.mPaddingTop, this.mForegroundPaddingTop) : this.mPaddingTop + this.mForegroundPaddingTop;
    }

    private int getPaddingBottomWithForeground() {
        return isForegroundInsidePadding() ? Math.max(this.mPaddingBottom, this.mForegroundPaddingBottom) : this.mPaddingBottom + this.mForegroundPaddingBottom;
    }

    @Override // android.view.ViewTreeObserver.OnScrollChangedListener
    public void onScrollChanged() {
        ListView listView = this.mListView;
        if (listView != null) {
            if (this.mTopPanel != null) {
                if (listView.getChildCount() <= 0) {
                    setScrolling(this.mTopPanel, 0.0f);
                } else if (this.mListView.getFirstVisiblePosition() == 0) {
                    View firstChild = this.mListView.getChildAt(0);
                    setScrolling(this.mTopPanel, (firstChild.getY() - this.mTopPanel.getHeight()) - this.mTopPanel.getTop());
                } else {
                    View view = this.mTopPanel;
                    setScrolling(view, -view.getHeight());
                }
            }
            if (this.mBottomPanel == null) {
                return;
            }
            if (this.mListView.getChildCount() <= 0) {
                setScrolling(this.mBottomPanel, 0.0f);
            } else if (this.mListView.getLastVisiblePosition() >= this.mListView.getCount() - 1) {
                ListView listView2 = this.mListView;
                View lastChild = listView2.getChildAt(listView2.getChildCount() - 1);
                setScrolling(this.mBottomPanel, Math.max(0.0f, (lastChild.getY() + lastChild.getHeight()) - this.mBottomPanel.getTop()));
            } else {
                View view2 = this.mBottomPanel;
                setScrolling(view2, view2.getHeight());
            }
        }
    }

    private void setScrolling(View panel, float translationY) {
        if (panel.getTranslationY() != translationY) {
            panel.setTranslationY(translationY);
        }
    }
}
