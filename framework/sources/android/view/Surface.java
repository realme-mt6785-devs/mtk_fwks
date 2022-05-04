package android.view;

import android.content.res.CompatibilityInfo;
import android.graphics.BLASTBufferQueue;
import android.graphics.Canvas;
import android.graphics.ColorSpace;
import android.graphics.HardwareRenderer;
import android.graphics.Matrix;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.graphics.RenderNode;
import android.graphics.SurfaceTexture;
import android.hardware.HardwareBuffer;
import android.os.Parcel;
import android.os.Parcelable;
import android.system.OsConstants;
import android.util.Log;
import com.mediatek.view.SurfaceExt;
import com.mediatek.view.SurfaceFactory;
import dalvik.system.CloseGuard;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes3.dex */
public class Surface implements Parcelable {
    public static final int CHANGE_FRAME_RATE_ALWAYS = 1;
    public static final int CHANGE_FRAME_RATE_ONLY_IF_SEAMLESS = 0;
    public static final Parcelable.Creator<Surface> CREATOR = new Parcelable.Creator<Surface>() { // from class: android.view.Surface.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Surface createFromParcel(Parcel source) {
            try {
                Surface s = new Surface();
                s.readFromParcel(source);
                return s;
            } catch (Exception e) {
                Log.e(Surface.TAG, "Exception creating surface from parcel", e);
                return null;
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Surface[] newArray(int size) {
            return new Surface[size];
        }
    };
    public static final int FRAME_RATE_COMPATIBILITY_DEFAULT = 0;
    public static final int FRAME_RATE_COMPATIBILITY_EXACT = 100;
    public static final int FRAME_RATE_COMPATIBILITY_FIXED_SOURCE = 1;
    public static final int ROTATION_0 = 0;
    public static final int ROTATION_180 = 2;
    public static final int ROTATION_270 = 3;
    public static final int ROTATION_90 = 1;
    public static final int SCALING_MODE_FREEZE = 0;
    public static final int SCALING_MODE_NO_SCALE_CROP = 3;
    public static final int SCALING_MODE_SCALE_CROP = 2;
    public static final int SCALING_MODE_SCALE_TO_WINDOW = 1;
    private static final String TAG = "Surface";
    private final Canvas mCanvas;
    private final CloseGuard mCloseGuard;
    private Matrix mCompatibleMatrix;
    private int mGenerationId;
    private HwuiContext mHwuiContext;
    private boolean mIsAutoRefreshEnabled;
    private boolean mIsSharedBufferModeEnabled;
    private boolean mIsSingleBuffered;
    final Object mLock;
    private long mLockedObject;
    private String mName;
    long mNativeObject;
    private SurfaceExt mSurfaceExt;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface ChangeFrameRateStrategy {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface FrameRateCompatibility {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface Rotation {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface ScalingMode {
    }

    private static native void nativeAllocateBuffers(long j);

    private static native int nativeAttachAndQueueBufferWithColorSpace(long j, HardwareBuffer hardwareBuffer, int i);

    private static native long nativeCreateFromSurfaceControl(long j);

    private static native long nativeCreateFromSurfaceTexture(SurfaceTexture surfaceTexture) throws OutOfResourcesException;

    private static native int nativeForceScopedDisconnect(long j);

    private static native long nativeGetFromBlastBufferQueue(long j, long j2);

    private static native long nativeGetFromSurfaceControl(long j, long j2);

    private static native int nativeGetHeight(long j);

    private static native long nativeGetNextFrameNumber(long j);

    private static native int nativeGetWidth(long j);

    private static native boolean nativeIsConsumerRunningBehind(long j);

    private static native boolean nativeIsValid(long j);

    private static native long nativeLockCanvas(long j, Canvas canvas, Rect rect) throws OutOfResourcesException;

    private static native long nativeReadFromParcel(long j, Parcel parcel);

    private static native void nativeRelease(long j);

    private static native int nativeSetAutoRefreshEnabled(long j, boolean z);

    private static native int nativeSetFrameRate(long j, float f, int i, int i2);

    private static native int nativeSetScalingMode(long j, int i);

    private static native int nativeSetSharedBufferModeEnabled(long j, boolean z);

    private static native void nativeUnlockCanvasAndPost(long j, Canvas canvas);

    private static native void nativeWriteToParcel(long j, Parcel parcel);

    public Surface() {
        this.mCloseGuard = CloseGuard.get();
        this.mLock = new Object();
        this.mCanvas = new CompatibleCanvas();
        this.mSurfaceExt = SurfaceFactory.getInstance().getSurfaceExt();
    }

    public Surface(SurfaceControl from) {
        this.mCloseGuard = CloseGuard.get();
        this.mLock = new Object();
        this.mCanvas = new CompatibleCanvas();
        this.mSurfaceExt = SurfaceFactory.getInstance().getSurfaceExt();
        copyFrom(from);
    }

    public Surface(SurfaceTexture surfaceTexture) {
        this.mCloseGuard = CloseGuard.get();
        Object obj = new Object();
        this.mLock = obj;
        this.mCanvas = new CompatibleCanvas();
        this.mSurfaceExt = SurfaceFactory.getInstance().getSurfaceExt();
        if (surfaceTexture != null) {
            this.mIsSingleBuffered = surfaceTexture.isSingleBuffered();
            synchronized (obj) {
                this.mName = surfaceTexture.toString();
                setNativeObjectLocked(nativeCreateFromSurfaceTexture(surfaceTexture));
            }
            return;
        }
        throw new IllegalArgumentException("surfaceTexture must not be null");
    }

    private Surface(long nativeObject) {
        this.mCloseGuard = CloseGuard.get();
        Object obj = new Object();
        this.mLock = obj;
        this.mCanvas = new CompatibleCanvas();
        this.mSurfaceExt = SurfaceFactory.getInstance().getSurfaceExt();
        synchronized (obj) {
            setNativeObjectLocked(nativeObject);
        }
    }

    protected void finalize() throws Throwable {
        try {
            CloseGuard closeGuard = this.mCloseGuard;
            if (closeGuard != null) {
                closeGuard.warnIfOpen();
            }
            release();
        } finally {
            super.finalize();
        }
    }

    public void release() {
        synchronized (this.mLock) {
            HwuiContext hwuiContext = this.mHwuiContext;
            if (hwuiContext != null) {
                hwuiContext.destroy();
                this.mHwuiContext = null;
            }
            long j = this.mNativeObject;
            if (j != 0) {
                nativeRelease(j);
                setNativeObjectLocked(0L);
            }
        }
    }

    public void destroy() {
        release();
    }

    public void hwuiDestroy() {
        HwuiContext hwuiContext = this.mHwuiContext;
        if (hwuiContext != null) {
            hwuiContext.destroy();
            this.mHwuiContext = null;
        }
    }

    public boolean isValid() {
        synchronized (this.mLock) {
            long j = this.mNativeObject;
            if (j == 0) {
                return false;
            }
            return nativeIsValid(j);
        }
    }

    public int getGenerationId() {
        int i;
        synchronized (this.mLock) {
            i = this.mGenerationId;
        }
        return i;
    }

    public long getNextFrameNumber() {
        long nativeGetNextFrameNumber;
        synchronized (this.mLock) {
            checkNotReleasedLocked();
            nativeGetNextFrameNumber = nativeGetNextFrameNumber(this.mNativeObject);
        }
        return nativeGetNextFrameNumber;
    }

    public boolean isConsumerRunningBehind() {
        boolean nativeIsConsumerRunningBehind;
        synchronized (this.mLock) {
            checkNotReleasedLocked();
            nativeIsConsumerRunningBehind = nativeIsConsumerRunningBehind(this.mNativeObject);
        }
        return nativeIsConsumerRunningBehind;
    }

    public Canvas lockCanvas(Rect inOutDirty) throws OutOfResourcesException, IllegalArgumentException {
        synchronized (this.mLock) {
            if (this.mSurfaceExt.isInWhiteList()) {
                return lockHardwareCanvas();
            }
            Log.d(TAG, "lockCanvas");
            checkNotReleasedLocked();
            if (this.mLockedObject == 0) {
                this.mLockedObject = nativeLockCanvas(this.mNativeObject, this.mCanvas, inOutDirty);
                return this.mCanvas;
            }
            throw new IllegalArgumentException("Surface was already locked");
        }
    }

    public void unlockCanvasAndPost(Canvas canvas) {
        synchronized (this.mLock) {
            checkNotReleasedLocked();
            HwuiContext hwuiContext = this.mHwuiContext;
            if (hwuiContext != null) {
                hwuiContext.unlockAndPost(canvas);
            } else {
                unlockSwCanvasAndPost(canvas);
            }
        }
    }

    private void unlockSwCanvasAndPost(Canvas canvas) {
        if (canvas == this.mCanvas) {
            if (this.mNativeObject != this.mLockedObject) {
                Log.w(TAG, "WARNING: Surface's mNativeObject (0x" + Long.toHexString(this.mNativeObject) + ") != mLockedObject (0x" + Long.toHexString(this.mLockedObject) + ")");
            }
            long j = this.mLockedObject;
            if (j != 0) {
                try {
                    nativeUnlockCanvasAndPost(j, canvas);
                } finally {
                    nativeRelease(this.mLockedObject);
                    this.mLockedObject = 0L;
                }
            } else {
                throw new IllegalStateException("Surface was not locked");
            }
        } else {
            throw new IllegalArgumentException("canvas object must be the same instance that was previously returned by lockCanvas");
        }
    }

    public Canvas lockHardwareCanvas() {
        Canvas lockCanvas;
        Log.d(TAG, "lockHardwareCanvas");
        synchronized (this.mLock) {
            checkNotReleasedLocked();
            if (this.mHwuiContext == null) {
                this.mHwuiContext = new HwuiContext(false);
            }
            lockCanvas = this.mHwuiContext.lockCanvas(nativeGetWidth(this.mNativeObject), nativeGetHeight(this.mNativeObject));
        }
        return lockCanvas;
    }

    public Canvas lockHardwareWideColorGamutCanvas() {
        Canvas lockCanvas;
        synchronized (this.mLock) {
            checkNotReleasedLocked();
            HwuiContext hwuiContext = this.mHwuiContext;
            if (hwuiContext != null && !hwuiContext.isWideColorGamut()) {
                this.mHwuiContext.destroy();
                this.mHwuiContext = null;
            }
            if (this.mHwuiContext == null) {
                this.mHwuiContext = new HwuiContext(true);
            }
            lockCanvas = this.mHwuiContext.lockCanvas(nativeGetWidth(this.mNativeObject), nativeGetHeight(this.mNativeObject));
        }
        return lockCanvas;
    }

    @Deprecated
    public void unlockCanvas(Canvas canvas) {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCompatibilityTranslator(CompatibilityInfo.Translator translator) {
        if (translator != null) {
            float appScale = translator.applicationScale;
            Matrix matrix = new Matrix();
            this.mCompatibleMatrix = matrix;
            matrix.setScale(appScale, appScale);
        }
    }

    private void updateNativeObject(long newNativeObject) {
        synchronized (this.mLock) {
            long j = this.mNativeObject;
            if (newNativeObject != j) {
                if (j != 0) {
                    nativeRelease(j);
                }
                setNativeObjectLocked(newNativeObject);
            }
        }
    }

    public void copyFrom(SurfaceControl other) {
        if (other != null) {
            long surfaceControlPtr = other.mNativeObject;
            if (surfaceControlPtr != 0) {
                long newNativeObject = nativeGetFromSurfaceControl(this.mNativeObject, surfaceControlPtr);
                updateNativeObject(newNativeObject);
                return;
            }
            throw new NullPointerException("null SurfaceControl native object. Are you using a released SurfaceControl?");
        }
        throw new IllegalArgumentException("other must not be null");
    }

    public void copyFrom(BLASTBufferQueue queue) {
        if (queue != null) {
            long blastBufferQueuePtr = queue.mNativeObject;
            if (blastBufferQueuePtr != 0) {
                long newNativeObject = nativeGetFromBlastBufferQueue(this.mNativeObject, blastBufferQueuePtr);
                updateNativeObject(newNativeObject);
                return;
            }
            throw new NullPointerException("Null BLASTBufferQueue native object");
        }
        throw new IllegalArgumentException("queue must not be null");
    }

    public void createFrom(SurfaceControl other) {
        if (other != null) {
            long surfaceControlPtr = other.mNativeObject;
            if (surfaceControlPtr != 0) {
                long newNativeObject = nativeCreateFromSurfaceControl(surfaceControlPtr);
                synchronized (this.mLock) {
                    long j = this.mNativeObject;
                    if (j != 0) {
                        nativeRelease(j);
                    }
                    setNativeObjectLocked(newNativeObject);
                }
                return;
            }
            throw new NullPointerException("null SurfaceControl native object. Are you using a released SurfaceControl?");
        }
        throw new IllegalArgumentException("other must not be null");
    }

    @Deprecated
    public void transferFrom(Surface other) {
        long newPtr;
        if (other == null) {
            throw new IllegalArgumentException("other must not be null");
        } else if (other != this) {
            synchronized (other.mLock) {
                newPtr = other.mNativeObject;
                other.setNativeObjectLocked(0L);
            }
            synchronized (this.mLock) {
                long j = this.mNativeObject;
                if (j != 0) {
                    nativeRelease(j);
                }
                setNativeObjectLocked(newPtr);
            }
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void readFromParcel(Parcel source) {
        if (source != null) {
            synchronized (this.mLock) {
                this.mName = source.readString();
                this.mIsSingleBuffered = source.readInt() != 0;
                setNativeObjectLocked(nativeReadFromParcel(this.mNativeObject, source));
            }
            return;
        }
        throw new IllegalArgumentException("source must not be null");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        if (dest != null) {
            synchronized (this.mLock) {
                dest.writeString(this.mName);
                dest.writeInt(this.mIsSingleBuffered ? 1 : 0);
                nativeWriteToParcel(this.mNativeObject, dest);
            }
            if ((flags & 1) != 0) {
                release();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("dest must not be null");
    }

    public String toString() {
        String str;
        synchronized (this.mLock) {
            str = "Surface(name=" + this.mName + ")/@0x" + Integer.toHexString(System.identityHashCode(this));
        }
        return str;
    }

    private void setNativeObjectLocked(long ptr) {
        long j = this.mNativeObject;
        if (j != ptr) {
            if (j == 0 && ptr != 0) {
                this.mCloseGuard.open("release");
            } else if (j != 0 && ptr == 0) {
                this.mCloseGuard.close();
            }
            this.mNativeObject = ptr;
            this.mGenerationId++;
            HwuiContext hwuiContext = this.mHwuiContext;
            if (hwuiContext != null) {
                hwuiContext.updateSurface();
            }
        }
    }

    private void checkNotReleasedLocked() {
        if (this.mNativeObject == 0) {
            throw new IllegalStateException("Surface has already been released.");
        }
    }

    public void allocateBuffers() {
        synchronized (this.mLock) {
            checkNotReleasedLocked();
            nativeAllocateBuffers(this.mNativeObject);
        }
    }

    public void setScalingMode(int scalingMode) {
        synchronized (this.mLock) {
            checkNotReleasedLocked();
            int err = nativeSetScalingMode(this.mNativeObject, scalingMode);
            if (err != 0) {
                throw new IllegalArgumentException("Invalid scaling mode: " + scalingMode);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void forceScopedDisconnect() {
        synchronized (this.mLock) {
            checkNotReleasedLocked();
            int err = nativeForceScopedDisconnect(this.mNativeObject);
            if (err != 0) {
                throw new RuntimeException("Failed to disconnect Surface instance (bad object?)");
            }
        }
    }

    public void attachAndQueueBufferWithColorSpace(HardwareBuffer buffer, ColorSpace colorSpace) {
        synchronized (this.mLock) {
            checkNotReleasedLocked();
            if (colorSpace == null) {
                colorSpace = ColorSpace.get(ColorSpace.Named.SRGB);
            }
            int err = nativeAttachAndQueueBufferWithColorSpace(this.mNativeObject, buffer, colorSpace.getId());
            if (err != 0) {
                throw new RuntimeException("Failed to attach and queue buffer to Surface (bad object?), native error: " + err);
            }
        }
    }

    public boolean isSingleBuffered() {
        return this.mIsSingleBuffered;
    }

    public void setSharedBufferModeEnabled(boolean enabled) {
        if (this.mIsSharedBufferModeEnabled != enabled) {
            int error = nativeSetSharedBufferModeEnabled(this.mNativeObject, enabled);
            if (error == 0) {
                this.mIsSharedBufferModeEnabled = enabled;
                return;
            }
            throw new RuntimeException("Failed to set shared buffer mode on Surface (bad object?)");
        }
    }

    public boolean isSharedBufferModeEnabled() {
        return this.mIsSharedBufferModeEnabled;
    }

    public void setAutoRefreshEnabled(boolean enabled) {
        if (this.mIsAutoRefreshEnabled != enabled) {
            int error = nativeSetAutoRefreshEnabled(this.mNativeObject, enabled);
            if (error == 0) {
                this.mIsAutoRefreshEnabled = enabled;
                return;
            }
            throw new RuntimeException("Failed to set auto refresh on Surface (bad object?)");
        }
    }

    public boolean isAutoRefreshEnabled() {
        return this.mIsAutoRefreshEnabled;
    }

    public void setFrameRate(float frameRate, int compatibility, int changeFrameRateStrategy) {
        synchronized (this.mLock) {
            checkNotReleasedLocked();
            int error = nativeSetFrameRate(this.mNativeObject, frameRate, compatibility, changeFrameRateStrategy);
            if (error == (-OsConstants.EINVAL)) {
                throw new IllegalArgumentException("Invalid argument to Surface.setFrameRate()");
            } else if (error != 0) {
                throw new RuntimeException("Failed to set frame rate on Surface");
            }
        }
    }

    public void setFrameRate(float frameRate, int compatibility) {
        setFrameRate(frameRate, compatibility, 0);
    }

    /* loaded from: classes3.dex */
    public static class OutOfResourcesException extends RuntimeException {
        public OutOfResourcesException() {
        }

        public OutOfResourcesException(String name) {
            super(name);
        }
    }

    public static String rotationToString(int rotation) {
        switch (rotation) {
            case 0:
                return "ROTATION_0";
            case 1:
                return "ROTATION_90";
            case 2:
                return "ROTATION_180";
            case 3:
                return "ROTATION_270";
            default:
                return Integer.toString(rotation);
        }
    }

    /* loaded from: classes3.dex */
    private final class CompatibleCanvas extends Canvas {
        private Matrix mOrigMatrix;

        private CompatibleCanvas() {
            this.mOrigMatrix = null;
        }

        @Override // android.graphics.Canvas
        public void setMatrix(Matrix matrix) {
            Matrix matrix2;
            if (Surface.this.mCompatibleMatrix == null || (matrix2 = this.mOrigMatrix) == null || matrix2.equals(matrix)) {
                super.setMatrix(matrix);
                return;
            }
            Matrix m = new Matrix(Surface.this.mCompatibleMatrix);
            m.preConcat(matrix);
            super.setMatrix(m);
        }

        @Override // android.graphics.Canvas
        public void getMatrix(Matrix m) {
            super.getMatrix(m);
            if (this.mOrigMatrix == null) {
                this.mOrigMatrix = new Matrix();
            }
            this.mOrigMatrix.set(m);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public final class HwuiContext {
        private RecordingCanvas mCanvas;
        private HardwareRenderer mHardwareRenderer;
        private final boolean mIsWideColorGamut;
        private final RenderNode mRenderNode;

        HwuiContext(boolean isWideColorGamut) {
            RenderNode create = RenderNode.create("HwuiCanvas", null);
            this.mRenderNode = create;
            int i = 0;
            create.setClipToBounds(false);
            create.setForceDarkAllowed(false);
            this.mIsWideColorGamut = isWideColorGamut;
            HardwareRenderer hardwareRenderer = new HardwareRenderer();
            this.mHardwareRenderer = hardwareRenderer;
            hardwareRenderer.setContentRoot(create);
            this.mHardwareRenderer.setSurface(Surface.this, true);
            this.mHardwareRenderer.setColorMode(isWideColorGamut ? 1 : i);
            this.mHardwareRenderer.setLightSourceAlpha(0.0f, 0.0f);
            this.mHardwareRenderer.setLightSourceGeometry(0.0f, 0.0f, 0.0f, 0.0f);
        }

        Canvas lockCanvas(int width, int height) {
            if (this.mCanvas == null) {
                RecordingCanvas beginRecording = this.mRenderNode.beginRecording(width, height);
                this.mCanvas = beginRecording;
                return beginRecording;
            }
            throw new IllegalStateException("Surface was already locked!");
        }

        void unlockAndPost(Canvas canvas) {
            if (canvas == this.mCanvas) {
                this.mRenderNode.endRecording();
                this.mCanvas = null;
                this.mHardwareRenderer.createRenderRequest().setVsyncTime(System.nanoTime()).syncAndDraw();
                return;
            }
            throw new IllegalArgumentException("canvas object must be the same instance that was previously returned by lockCanvas");
        }

        void updateSurface() {
            this.mHardwareRenderer.setSurface(Surface.this, true);
        }

        void destroy() {
            this.mHardwareRenderer.destroy();
        }

        boolean isWideColorGamut() {
            return this.mIsWideColorGamut;
        }
    }
}
