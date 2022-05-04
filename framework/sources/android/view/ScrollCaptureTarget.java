package android.view;

import android.graphics.Point;
import android.graphics.Rect;
import java.io.PrintWriter;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class ScrollCaptureTarget {
    private final ScrollCaptureCallback mCallback;
    private final View mContainingView;
    private final int mHint;
    private final Rect mLocalVisibleRect;
    private final Point mPositionInWindow;
    private Rect mScrollBounds;
    private final int[] mTmpIntArr = new int[2];

    public ScrollCaptureTarget(View scrollTarget, Rect localVisibleRect, Point positionInWindow, ScrollCaptureCallback callback) {
        Objects.requireNonNull(scrollTarget);
        View view = scrollTarget;
        this.mContainingView = view;
        this.mHint = view.getScrollCaptureHint();
        Objects.requireNonNull(callback);
        this.mCallback = callback;
        Objects.requireNonNull(localVisibleRect);
        this.mLocalVisibleRect = localVisibleRect;
        Objects.requireNonNull(positionInWindow);
        this.mPositionInWindow = positionInWindow;
    }

    public int getHint() {
        return this.mHint;
    }

    public ScrollCaptureCallback getCallback() {
        return this.mCallback;
    }

    public View getContainingView() {
        return this.mContainingView;
    }

    public Rect getLocalVisibleRect() {
        return this.mLocalVisibleRect;
    }

    public Point getPositionInWindow() {
        return this.mPositionInWindow;
    }

    public Rect getScrollBounds() {
        return this.mScrollBounds;
    }

    public void setScrollBounds(Rect scrollBounds) {
        Rect copyOrNull = Rect.copyOrNull(scrollBounds);
        this.mScrollBounds = copyOrNull;
        if (copyOrNull != null && !copyOrNull.intersect(0, 0, this.mContainingView.getWidth(), this.mContainingView.getHeight())) {
            this.mScrollBounds.setEmpty();
        }
    }

    public void updatePositionInWindow() {
        this.mContainingView.getLocationInWindow(this.mTmpIntArr);
        this.mPositionInWindow.x = this.mTmpIntArr[0];
        this.mPositionInWindow.y = this.mTmpIntArr[1];
    }

    public String toString() {
        return "ScrollCaptureTarget{view=" + this.mContainingView + ", callback=" + this.mCallback + ", scrollBounds=" + this.mScrollBounds + ", localVisibleRect=" + this.mLocalVisibleRect + ", positionInWindow=" + this.mPositionInWindow + "}";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dump(PrintWriter writer) {
        String str;
        View view = getContainingView();
        writer.println("view: " + view);
        writer.println("hint: " + this.mHint);
        writer.println("callback: " + this.mCallback);
        StringBuilder sb = new StringBuilder();
        sb.append("scrollBounds: ");
        Rect rect = this.mScrollBounds;
        String str2 = "null";
        sb.append(rect == null ? str2 : rect.toShortString());
        writer.println(sb.toString());
        Point inWindow = getPositionInWindow();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("positionInWindow: ");
        if (inWindow == null) {
            str = str2;
        } else {
            str = "[" + inWindow.x + "," + inWindow.y + "]";
        }
        sb2.append(str);
        writer.println(sb2.toString());
        Rect localVisible = getLocalVisibleRect();
        StringBuilder sb3 = new StringBuilder();
        sb3.append("localVisibleRect: ");
        if (localVisible != null) {
            str2 = localVisible.toShortString();
        }
        sb3.append(str2);
        writer.println(sb3.toString());
    }
}
