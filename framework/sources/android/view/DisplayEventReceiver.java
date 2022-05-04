package android.view;

import android.os.Looper;
import android.os.MessageQueue;
import android.util.Log;
import dalvik.annotation.optimization.FastNative;
import java.lang.ref.WeakReference;
/* loaded from: classes3.dex */
public abstract class DisplayEventReceiver {
    public static final int EVENT_REGISTRATION_FRAME_RATE_OVERRIDE_FLAG = 2;
    public static final int EVENT_REGISTRATION_MODE_CHANGED_FLAG = 1;
    private static final String TAG = "DisplayEventReceiver";
    public static final int VSYNC_SOURCE_APP = 0;
    public static final int VSYNC_SOURCE_SURFACE_FLINGER = 1;
    private MessageQueue mMessageQueue;
    private long mReceiverPtr;

    private static native void nativeDispose(long j);

    private static native long nativeInit(WeakReference<DisplayEventReceiver> weakReference, MessageQueue messageQueue, int i, int i2);

    @FastNative
    private static native void nativeScheduleVsync(long j);

    public DisplayEventReceiver(Looper looper) {
        this(looper, 0, 0);
    }

    public DisplayEventReceiver(Looper looper, int vsyncSource, int eventRegistration) {
        if (looper != null) {
            this.mMessageQueue = looper.getQueue();
            this.mReceiverPtr = nativeInit(new WeakReference(this), this.mMessageQueue, vsyncSource, eventRegistration);
            return;
        }
        throw new IllegalArgumentException("looper must not be null");
    }

    protected void finalize() throws Throwable {
        try {
            dispose(true);
        } finally {
            super.finalize();
        }
    }

    public void dispose() {
        dispose(false);
    }

    private void dispose(boolean finalized) {
        long j = this.mReceiverPtr;
        if (j != 0) {
            nativeDispose(j);
            this.mReceiverPtr = 0L;
        }
        this.mMessageQueue = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class VsyncEventData {
        public final long frameDeadline;
        public final long frameInterval;
        public final long id;

        VsyncEventData(long id, long frameDeadline, long frameInterval) {
            this.id = id;
            this.frameDeadline = frameDeadline;
            this.frameInterval = frameInterval;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public VsyncEventData() {
            this.id = -1L;
            this.frameDeadline = Long.MAX_VALUE;
            this.frameInterval = -1L;
        }
    }

    public void onVsync(long timestampNanos, long physicalDisplayId, int frame, VsyncEventData vsyncEventData) {
    }

    public void onHotplug(long timestampNanos, long physicalDisplayId, boolean connected) {
    }

    public void onModeChanged(long timestampNanos, long physicalDisplayId, int modeId) {
    }

    /* loaded from: classes3.dex */
    public static class FrameRateOverride {
        public final float frameRateHz;
        public final int uid;

        public FrameRateOverride(int uid, float frameRateHz) {
            this.uid = uid;
            this.frameRateHz = frameRateHz;
        }

        public String toString() {
            return "{uid=" + this.uid + " frameRateHz=" + this.frameRateHz + "}";
        }
    }

    public void onFrameRateOverridesChanged(long timestampNanos, long physicalDisplayId, FrameRateOverride[] overrides) {
    }

    public void scheduleVsync() {
        long j = this.mReceiverPtr;
        if (j == 0) {
            Log.w(TAG, "Attempted to schedule a vertical sync pulse but the display event receiver has already been disposed.");
        } else {
            nativeScheduleVsync(j);
        }
    }

    private void dispatchVsync(long timestampNanos, long physicalDisplayId, int frame, long frameTimelineVsyncId, long frameDeadline, long frameInterval) {
        onVsync(timestampNanos, physicalDisplayId, frame, new VsyncEventData(frameTimelineVsyncId, frameDeadline, frameInterval));
    }

    private void dispatchHotplug(long timestampNanos, long physicalDisplayId, boolean connected) {
        onHotplug(timestampNanos, physicalDisplayId, connected);
    }

    private void dispatchModeChanged(long timestampNanos, long physicalDisplayId, int modeId) {
        onModeChanged(timestampNanos, physicalDisplayId, modeId);
    }

    private void dispatchFrameRateOverrides(long timestampNanos, long physicalDisplayId, FrameRateOverride[] overrides) {
        onFrameRateOverridesChanged(timestampNanos, physicalDisplayId, overrides);
    }
}
