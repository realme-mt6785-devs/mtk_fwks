package android.view;
/* loaded from: classes3.dex */
public abstract class SurfaceControlFpsListener {
    private long mNativeListener = nativeCreate(this);

    private static native long nativeCreate(SurfaceControlFpsListener surfaceControlFpsListener);

    private static native void nativeDestroy(long j);

    private static native void nativeRegister(long j, int i);

    private static native void nativeUnregister(long j);

    public abstract void onFpsReported(float f);

    protected void destroy() {
        if (this.mNativeListener != 0) {
            unregister();
            nativeDestroy(this.mNativeListener);
            this.mNativeListener = 0L;
        }
    }

    protected void finalize() throws Throwable {
        try {
            destroy();
        } finally {
            super.finalize();
        }
    }

    public void register(int taskId) {
        long j = this.mNativeListener;
        if (j != 0) {
            nativeRegister(j, taskId);
        }
    }

    public void unregister() {
        long j = this.mNativeListener;
        if (j != 0) {
            nativeUnregister(j);
        }
    }

    private static void dispatchOnFpsReported(SurfaceControlFpsListener listener, float fps) {
        listener.onFpsReported(fps);
    }
}
