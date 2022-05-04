package android.view;

import android.graphics.Region;
import android.os.IBinder;
import java.lang.ref.WeakReference;
/* loaded from: classes3.dex */
public final class InputWindowHandle {
    public long dispatchingTimeoutMillis;
    public int displayId;
    public boolean focusable;
    public int frameBottom;
    public int frameLeft;
    public int frameRight;
    public int frameTop;
    public boolean hasWallpaper;
    public InputApplicationHandle inputApplicationHandle;
    public int inputFeatures;
    public int layoutParamsFlags;
    public int layoutParamsType;
    public String name;
    public int ownerPid;
    public int ownerUid;
    public String packageName;
    public boolean paused;
    private long ptr;
    public boolean replaceTouchableRegionWithCrop;
    public float scaleFactor;
    public int surfaceInset;
    public IBinder token;
    public boolean trustedOverlay;
    public boolean visible;
    public final Region touchableRegion = new Region();
    public int touchOcclusionMode = 0;
    public int portalToDisplayId = -1;
    public WeakReference<SurfaceControl> touchableRegionSurfaceControl = new WeakReference<>(null);

    private native void nativeDispose();

    public InputWindowHandle(InputApplicationHandle inputApplicationHandle, int displayId) {
        this.inputApplicationHandle = inputApplicationHandle;
        this.displayId = displayId;
    }

    public String toString() {
        String str = this.name;
        if (str == null) {
            str = "";
        }
        return str + ", frame=[" + this.frameLeft + "," + this.frameTop + "," + this.frameRight + "," + this.frameBottom + "], touchableRegion=" + this.touchableRegion + ", visible=" + this.visible;
    }

    protected void finalize() throws Throwable {
        try {
            nativeDispose();
        } finally {
            super.finalize();
        }
    }

    public void replaceTouchableRegionWithCrop(SurfaceControl bounds) {
        setTouchableRegionCrop(bounds);
        this.replaceTouchableRegionWithCrop = true;
    }

    public void setTouchableRegionCrop(SurfaceControl bounds) {
        this.touchableRegionSurfaceControl = new WeakReference<>(bounds);
    }
}
