package android.graphics;

import android.view.Surface;
import android.view.SurfaceControl;
/* loaded from: classes.dex */
public final class BLASTBufferQueue {
    public long mNativeObject;

    /* loaded from: classes.dex */
    public interface TransactionCompleteCallback {
        void onTransactionComplete(long j);
    }

    private static native long nativeCreate(String str, long j, long j2, long j3, int i);

    private static native void nativeDestroy(long j);

    private static native void nativeFlushShadowQueue(long j);

    private static native long nativeGetLastAcquiredFrameNum(long j);

    private static native Surface nativeGetSurface(long j, boolean z);

    private static native void nativeMergeWithNextTransaction(long j, long j2, long j3);

    private static native void nativeSetNextTransaction(long j, long j2);

    private static native void nativeSetTransactionCompleteCallback(long j, long j2, TransactionCompleteCallback transactionCompleteCallback);

    private static native void nativeUpdate(long j, long j2, long j3, long j4, int i, long j5);

    public BLASTBufferQueue(String name, SurfaceControl sc, int width, int height, int format) {
        this.mNativeObject = nativeCreate(name, sc.mNativeObject, width, height, format);
    }

    public void destroy() {
        nativeDestroy(this.mNativeObject);
        this.mNativeObject = 0L;
    }

    public Surface createSurface() {
        return nativeGetSurface(this.mNativeObject, false);
    }

    public Surface createSurfaceWithHandle() {
        return nativeGetSurface(this.mNativeObject, true);
    }

    public void setNextTransaction(SurfaceControl.Transaction t) {
        nativeSetNextTransaction(this.mNativeObject, t == null ? 0L : t.mNativeObject);
    }

    public void update(SurfaceControl sc, int width, int height, int format, SurfaceControl.Transaction t) {
        nativeUpdate(this.mNativeObject, sc.mNativeObject, width, height, format, t.mNativeObject);
    }

    public void update(SurfaceControl sc, int width, int height, int format) {
        nativeUpdate(this.mNativeObject, sc.mNativeObject, width, height, format, 0L);
    }

    public void setTransactionCompleteCallback(long frameNumber, TransactionCompleteCallback completeCallback) {
        nativeSetTransactionCompleteCallback(this.mNativeObject, frameNumber, completeCallback);
    }

    protected void finalize() throws Throwable {
        try {
            long j = this.mNativeObject;
            if (j != 0) {
                nativeDestroy(j);
            }
        } finally {
            super.finalize();
        }
    }

    public void flushShadowQueue() {
        nativeFlushShadowQueue(this.mNativeObject);
    }

    public void mergeWithNextTransaction(SurfaceControl.Transaction t, long frameNumber) {
        nativeMergeWithNextTransaction(this.mNativeObject, t.mNativeObject, frameNumber);
    }

    public void mergeWithNextTransaction(long nativeTransaction, long frameNumber) {
        nativeMergeWithNextTransaction(this.mNativeObject, nativeTransaction, frameNumber);
    }

    public long getLastAcquiredFrameNum() {
        return nativeGetLastAcquiredFrameNum(this.mNativeObject);
    }
}
