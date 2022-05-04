package android.graphics;

import android.os.Handler;
import com.android.internal.util.VirtualRefBasePtr;
/* loaded from: classes.dex */
public class HardwareRendererObserver {
    private final long[] mFrameMetrics;
    private final Handler mHandler;
    private final OnFrameMetricsAvailableListener mListener;
    private VirtualRefBasePtr mNativePtr;

    /* loaded from: classes.dex */
    public interface OnFrameMetricsAvailableListener {
        void onFrameMetricsAvailable(int i);
    }

    private native long nCreateObserver(boolean z);

    private static native int nGetNextBuffer(long j, long[] jArr);

    public HardwareRendererObserver(OnFrameMetricsAvailableListener listener, long[] frameMetrics, Handler handler, boolean waitForPresentTime) {
        if (handler == null || handler.getLooper() == null) {
            throw new NullPointerException("handler and its looper cannot be null");
        } else if (handler.getLooper().getQueue() != null) {
            this.mFrameMetrics = frameMetrics;
            this.mHandler = handler;
            this.mListener = listener;
            this.mNativePtr = new VirtualRefBasePtr(nCreateObserver(waitForPresentTime));
        } else {
            throw new IllegalStateException("invalid looper, null message queue\n");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getNativeInstance() {
        return this.mNativePtr.get();
    }

    private void notifyDataAvailable() {
        this.mHandler.post(new Runnable() { // from class: android.graphics.HardwareRendererObserver$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                HardwareRendererObserver.this.lambda$notifyDataAvailable$0$HardwareRendererObserver();
            }
        });
    }

    public /* synthetic */ void lambda$notifyDataAvailable$0$HardwareRendererObserver() {
        boolean hasMoreData = true;
        while (hasMoreData) {
            int dropCount = nGetNextBuffer(this.mNativePtr.get(), this.mFrameMetrics);
            if (dropCount >= 0) {
                this.mListener.onFrameMetricsAvailable(dropCount);
            } else {
                hasMoreData = false;
            }
        }
    }
}
