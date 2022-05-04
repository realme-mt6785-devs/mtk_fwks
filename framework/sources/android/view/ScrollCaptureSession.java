package android.view;

import android.graphics.Point;
import android.graphics.Rect;
import java.util.Objects;
/* loaded from: classes3.dex */
public class ScrollCaptureSession {
    private final Point mPositionInWindow;
    private final Rect mScrollBounds;
    private final Surface mSurface;

    public ScrollCaptureSession(Surface surface, Rect scrollBounds, Point positionInWindow) {
        Objects.requireNonNull(surface);
        this.mSurface = surface;
        Objects.requireNonNull(scrollBounds);
        this.mScrollBounds = scrollBounds;
        Objects.requireNonNull(positionInWindow);
        this.mPositionInWindow = positionInWindow;
    }

    public Surface getSurface() {
        return this.mSurface;
    }

    public Rect getScrollBounds() {
        return this.mScrollBounds;
    }

    public Point getPositionInWindow() {
        return this.mPositionInWindow;
    }
}
