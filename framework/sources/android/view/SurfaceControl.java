package android.view;

import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.GraphicBuffer;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.hardware.HardwareBuffer;
import android.hardware.display.DeviceProductInfo;
import android.hardware.display.DisplayedContentSample;
import android.hardware.display.DisplayedContentSamplingAttributes;
import android.os.Debug;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemProperties;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.proto.ProtoOutputStream;
import android.view.Display;
import android.view.Surface;
import com.android.internal.util.VirtualRefBasePtr;
import dalvik.system.CloseGuard;
import java.io.Closeable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import libcore.util.NativeAllocationRegistry;
/* loaded from: classes3.dex */
public final class SurfaceControl implements Parcelable {
    public static final int AOD_FLAGS = 512;
    public static final int CAST_FLAGS = 32;
    public static final Parcelable.Creator<SurfaceControl> CREATOR;
    public static final int CURSOR_WINDOW = 8192;
    private static final boolean DEBUG_ALL;
    private static final int DEBUG_DEPTH;
    private static boolean DEBUG_SFC = false;
    public static final int ENABLE_BACKPRESSURE = 256;
    public static final int FX_SURFACE_BLAST = 262144;
    public static final int FX_SURFACE_CONTAINER = 524288;
    public static final int FX_SURFACE_EFFECT = 131072;
    public static final int FX_SURFACE_MASK = 983040;
    public static final int FX_SURFACE_NORMAL = 0;
    public static final int HIDDEN = 4;
    private static final int INTERNAL_DATASPACE_DISPLAY_P3 = 143261696;
    private static final int INTERNAL_DATASPACE_SCRGB = 411107328;
    private static final int INTERNAL_DATASPACE_SRGB = 142671872;
    private static final boolean LOG_SURFACE_CONTROL;
    public static final int METADATA_ACCESSIBILITY_ID = 5;
    public static final int METADATA_GAME_MODE = 8;
    public static final int METADATA_MOUSE_CURSOR = 4;
    public static final int METADATA_OWNER_PID = 6;
    public static final int METADATA_OWNER_UID = 1;
    public static final int METADATA_TASK_ID = 3;
    public static final int METADATA_WINDOW_TYPE = 2;
    public static final int NON_PREMULTIPLIED = 256;
    public static final int NO_COLOR_FILL = 16384;
    public static final int OPAQUE = 1024;
    public static final int POWER_MODE_DOZE = 1;
    public static final int POWER_MODE_DOZE_SUSPEND = 3;
    public static final int POWER_MODE_NORMAL = 2;
    public static final int POWER_MODE_OFF = 0;
    public static final int POWER_MODE_ON_SUSPEND = 4;
    public static final int PROTECTED_APP = 2048;
    public static final int SECURE = 128;
    public static final int SKIP_SCREENSHOT = 64;
    private static final int SURFACE_HIDDEN = 1;
    private static final int SURFACE_OPAQUE = 2;
    private static final String TAG = "SurfaceControl";
    static GlobalTransactionWrapper sGlobalTransaction;
    static long sTransactionNestCount;
    private final CloseGuard mCloseGuard;
    private int mHeight;
    private WeakReference<View> mLocalOwnerView;
    private final Object mLock;
    private String mName;
    private long mNativeHandle;
    public long mNativeObject;
    private ArrayList<OnReparentListener> mReparentListeners;
    private int mTransformHint;
    private int mWidth;

    /* loaded from: classes3.dex */
    public static final class CieXyz {
        public float X;
        public float Y;
        public float Z;
    }

    /* loaded from: classes3.dex */
    public static final class DisplayPrimaries {
        public CieXyz blue;
        public CieXyz green;
        public CieXyz red;
        public CieXyz white;
    }

    /* loaded from: classes3.dex */
    public interface OnReparentListener {
        void onReparent(Transaction transaction, SurfaceControl surfaceControl);
    }

    /* loaded from: classes3.dex */
    public interface ScreenCaptureListener {
        void onScreenCaptureComplete(ScreenshotHardwareBuffer screenshotHardwareBuffer);
    }

    private static native long nativeAcquireFrameRateFlexibilityToken();

    private static native void nativeAddJankDataListener(long j, long j2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeApplyTransaction(long j, boolean z);

    private static native int nativeCaptureDisplay(DisplayCaptureArgs displayCaptureArgs, ScreenCaptureListener screenCaptureListener);

    private static native int nativeCaptureLayers(LayerCaptureArgs layerCaptureArgs, ScreenCaptureListener screenCaptureListener);

    private static native boolean nativeClearAnimationFrameStats();

    private static native boolean nativeClearContentFrameStats(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeClearTransaction(long j);

    private static native long nativeCopyFromSurfaceControl(long j);

    private static native long nativeCreate(SurfaceSession surfaceSession, String str, int i, int i2, int i3, int i4, long j, Parcel parcel) throws Surface.OutOfResourcesException;

    private static native IBinder nativeCreateDisplay(String str, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public static native long nativeCreateJankDataListenerWrapper(OnJankDataListener onJankDataListener);

    /* JADX INFO: Access modifiers changed from: private */
    public static native long nativeCreateTransaction();

    private static native void nativeDestroyDisplay(IBinder iBinder);

    private static native void nativeDisconnect(long j);

    private static native boolean nativeGetAnimationFrameStats(WindowAnimationFrameStats windowAnimationFrameStats);

    private static native int[] nativeGetCompositionDataspaces();

    private static native boolean nativeGetContentFrameStats(long j, WindowContentFrameStats windowContentFrameStats);

    private static native DesiredDisplayModeSpecs nativeGetDesiredDisplayModeSpecs(IBinder iBinder);

    private static native boolean nativeGetDisplayBrightnessSupport(IBinder iBinder);

    private static native DisplayPrimaries nativeGetDisplayNativePrimaries(IBinder iBinder);

    private static native DisplayedContentSample nativeGetDisplayedContentSample(IBinder iBinder, long j, long j2);

    private static native DisplayedContentSamplingAttributes nativeGetDisplayedContentSamplingAttributes(IBinder iBinder);

    private static native DynamicDisplayInfo nativeGetDynamicDisplayInfo(IBinder iBinder);

    private static native int nativeGetGPUContextPriority();

    private static native long nativeGetHandle(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public static native long nativeGetNativeTransactionFinalizer();

    private static native long[] nativeGetPhysicalDisplayIds();

    private static native IBinder nativeGetPhysicalDisplayToken(long j);

    private static native boolean nativeGetProtectedContentSupport();

    private static native StaticDisplayInfo nativeGetStaticDisplayInfo(IBinder iBinder);

    private static native int nativeGetTransformHint(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeMergeTransaction(long j, long j2);

    private static native long nativeMirrorSurface(long j);

    private static native void nativeOverrideHdrTypes(IBinder iBinder, int[] iArr);

    private static native long nativeReadFromParcel(Parcel parcel);

    /* JADX INFO: Access modifiers changed from: private */
    public static native long nativeReadTransactionFromParcel(Parcel parcel);

    private static native void nativeRelease(long j);

    private static native void nativeReleaseFrameRateFlexibilityToken(long j);

    private static native void nativeRemoveJankDataListener(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeReparent(long j, long j2, long j3);

    private static native boolean nativeSetActiveColorMode(IBinder iBinder, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetAlpha(long j, long j2, float f);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetAnimationTransaction(long j);

    private static native void nativeSetAutoLowLatencyMode(IBinder iBinder, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetBackgroundBlurRadius(long j, long j2, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetBlurRegions(long j, long j2, float[][] fArr, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetBuffer(long j, long j2, GraphicBuffer graphicBuffer);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetColor(long j, long j2, float[] fArr);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetColorSpace(long j, long j2, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetColorSpaceAgnostic(long j, long j2, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetColorTransform(long j, long j2, float[] fArr, float[] fArr2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetCornerRadius(long j, long j2, float f);

    private static native boolean nativeSetDesiredDisplayModeSpecs(IBinder iBinder, DesiredDisplayModeSpecs desiredDisplayModeSpecs);

    private static native boolean nativeSetDisplayBrightness(IBinder iBinder, float f, float f2, float f3, float f4);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetDisplayLayerStack(long j, IBinder iBinder, int i);

    private static native void nativeSetDisplayPowerMode(IBinder iBinder, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetDisplayProjection(long j, IBinder iBinder, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetDisplaySize(long j, IBinder iBinder, int i, int i2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetDisplaySurface(long j, IBinder iBinder, long j2);

    private static native boolean nativeSetDisplayedContentSamplingEnabled(IBinder iBinder, boolean z, int i, int i2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetEarlyWakeupEnd(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetEarlyWakeupStart(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetFixedTransformHint(long j, long j2, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetFlags(long j, long j2, int i, int i2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetFocusedWindow(long j, IBinder iBinder, String str, IBinder iBinder2, String str2, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetFrameRate(long j, long j2, float f, int i, int i2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetFrameRateSelectionPriority(long j, long j2, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetFrameTimelineVsync(long j, long j2);

    private static native void nativeSetGameContentType(IBinder iBinder, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetGeometry(long j, long j2, Rect rect, Rect rect2, long j3);

    private static native void nativeSetGlobalShadowSettings(float[] fArr, float[] fArr2, float f, float f2, float f3);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetInputWindowInfo(long j, long j2, InputWindowHandle inputWindowHandle);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetLayer(long j, long j2, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetLayerStack(long j, long j2, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetMatrix(long j, long j2, float f, float f2, float f3, float f4);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetMetadata(long j, long j2, int i, Parcel parcel);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetPosition(long j, long j2, float f, float f2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetRelativeLayer(long j, long j2, long j3, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetShadowRadius(long j, long j2, float f);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetSize(long j, long j2, int i, int i2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetStretchEffect(long j, long j2, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10);

    private static native void nativeSetTransformHint(long j, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetTransparentRegionHint(long j, long j2, Region region);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetTrustedOverlay(long j, long j2, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetWindowCrop(long j, long j2, int i, int i2, int i3, int i4);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSyncInputWindows(long j);

    private static native void nativeUpdateDefaultBufferSize(long j, int i, int i2);

    private static native void nativeWriteToParcel(long j, Parcel parcel);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeWriteTransactionToParcel(long j, Parcel parcel);

    static {
        DEBUG_SFC = SystemProperties.getBoolean("persist.sys.assert.panic", false) && "0".equals(SystemProperties.get("persist.sys.agingtest", "0"));
        DEBUG_ALL = SystemProperties.getBoolean("debug.surfacectrl", false);
        DEBUG_DEPTH = SystemProperties.getInt("debug.surfacectrl.depth", 5);
        LOG_SURFACE_CONTROL = SystemProperties.getBoolean("debug.surfacecontrol.log", false);
        sTransactionNestCount = 0L;
        CREATOR = new Parcelable.Creator<SurfaceControl>() { // from class: android.view.SurfaceControl.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SurfaceControl createFromParcel(Parcel in) {
                return new SurfaceControl(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SurfaceControl[] newArray(int size) {
                return new SurfaceControl[size];
            }
        };
    }

    /* loaded from: classes3.dex */
    public static class JankData {
        public static final int BUFFER_STUFFING = 64;
        public static final int DISPLAY_HAL = 1;
        public static final int JANK_APP_DEADLINE_MISSED = 8;
        public static final int JANK_NONE = 0;
        public static final int JANK_SURFACEFLINGER_DEADLINE_MISSED = 2;
        public static final int JANK_SURFACEFLINGER_GPU_DEADLINE_MISSED = 4;
        public static final int PREDICTION_ERROR = 16;
        public static final int SURFACE_FLINGER_SCHEDULING = 32;
        public static final int UNKNOWN = 128;
        public final long frameVsyncId;
        public final int jankType;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes3.dex */
        public @interface JankType {
        }

        public JankData(long frameVsyncId, int jankType) {
            this.frameVsyncId = frameVsyncId;
            this.jankType = jankType;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class OnJankDataListener {
        private final VirtualRefBasePtr mNativePtr = new VirtualRefBasePtr(SurfaceControl.nativeCreateJankDataListenerWrapper(this));

        public abstract void onJankDataAvailable(JankData[] jankDataArr);
    }

    public boolean addOnReparentListener(OnReparentListener listener) {
        boolean add;
        synchronized (this.mLock) {
            if (this.mReparentListeners == null) {
                this.mReparentListeners = new ArrayList<>(1);
            }
            add = this.mReparentListeners.add(listener);
        }
        return add;
    }

    public boolean removeOnReparentListener(OnReparentListener listener) {
        boolean removed;
        synchronized (this.mLock) {
            removed = this.mReparentListeners.remove(listener);
            if (this.mReparentListeners.isEmpty()) {
                this.mReparentListeners = null;
            }
        }
        return removed;
    }

    private void assignNativeObject(long nativeObject, String callsite) {
        long j = 0;
        if (this.mNativeObject != 0) {
            release();
        }
        if (nativeObject != 0) {
            this.mCloseGuard.openWithCallSite("release", callsite);
        }
        this.mNativeObject = nativeObject;
        if (nativeObject != 0) {
            j = nativeGetHandle(nativeObject);
        }
        this.mNativeHandle = j;
    }

    public void copyFrom(SurfaceControl other, String callsite) {
        this.mName = other.mName;
        this.mWidth = other.mWidth;
        this.mHeight = other.mHeight;
        this.mLocalOwnerView = other.mLocalOwnerView;
        assignNativeObject(nativeCopyFromSurfaceControl(other.mNativeObject), callsite);
    }

    /* loaded from: classes3.dex */
    public static class ScreenshotHardwareBuffer {
        private final ColorSpace mColorSpace;
        private final boolean mContainsSecureLayers;
        private final HardwareBuffer mHardwareBuffer;

        public ScreenshotHardwareBuffer(HardwareBuffer hardwareBuffer, ColorSpace colorSpace, boolean containsSecureLayers) {
            this.mHardwareBuffer = hardwareBuffer;
            this.mColorSpace = colorSpace;
            this.mContainsSecureLayers = containsSecureLayers;
        }

        private static ScreenshotHardwareBuffer createFromNative(HardwareBuffer hardwareBuffer, int namedColorSpace, boolean containsSecureLayers) {
            ColorSpace colorSpace = ColorSpace.get(ColorSpace.Named.values()[namedColorSpace]);
            return new ScreenshotHardwareBuffer(hardwareBuffer, colorSpace, containsSecureLayers);
        }

        public ColorSpace getColorSpace() {
            return this.mColorSpace;
        }

        public HardwareBuffer getHardwareBuffer() {
            return this.mHardwareBuffer;
        }

        public boolean containsSecureLayers() {
            return this.mContainsSecureLayers;
        }

        public Bitmap asBitmap() {
            HardwareBuffer hardwareBuffer = this.mHardwareBuffer;
            if (hardwareBuffer != null) {
                return Bitmap.wrapHardwareBuffer(hardwareBuffer, this.mColorSpace);
            }
            Log.w(SurfaceControl.TAG, "Failed to take screenshot. Null screenshot object");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class SyncScreenCaptureListener implements ScreenCaptureListener {
        private static final int SCREENSHOT_WAIT_TIME_S = 1;
        private final CountDownLatch mCountDownLatch;
        private ScreenshotHardwareBuffer mScreenshotHardwareBuffer;

        private SyncScreenCaptureListener() {
            this.mCountDownLatch = new CountDownLatch(1);
        }

        @Override // android.view.SurfaceControl.ScreenCaptureListener
        public void onScreenCaptureComplete(ScreenshotHardwareBuffer hardwareBuffer) {
            this.mScreenshotHardwareBuffer = hardwareBuffer;
            this.mCountDownLatch.countDown();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ScreenshotHardwareBuffer waitForScreenshot() {
            try {
                this.mCountDownLatch.await(1L, TimeUnit.SECONDS);
            } catch (Exception e) {
                Log.e(SurfaceControl.TAG, "Failed to wait for screen capture result", e);
            }
            return this.mScreenshotHardwareBuffer;
        }
    }

    /* loaded from: classes3.dex */
    private static abstract class CaptureArgs {
        private final boolean mAllowProtected;
        private final boolean mCaptureSecureLayers;
        private final float mFrameScaleX;
        private final float mFrameScaleY;
        private final boolean mGrayscale;
        private final int mPixelFormat;
        private final Rect mSourceCrop;
        private final long mUid;

        private CaptureArgs(Builder<? extends Builder<?>> builder) {
            Rect rect = new Rect();
            this.mSourceCrop = rect;
            this.mPixelFormat = ((Builder) builder).mPixelFormat;
            rect.set(((Builder) builder).mSourceCrop);
            this.mFrameScaleX = ((Builder) builder).mFrameScaleX;
            this.mFrameScaleY = ((Builder) builder).mFrameScaleY;
            this.mCaptureSecureLayers = ((Builder) builder).mCaptureSecureLayers;
            this.mAllowProtected = ((Builder) builder).mAllowProtected;
            this.mUid = ((Builder) builder).mUid;
            this.mGrayscale = ((Builder) builder).mGrayscale;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes3.dex */
        public static abstract class Builder<T extends Builder<T>> {
            private boolean mAllowProtected;
            private boolean mCaptureSecureLayers;
            private boolean mGrayscale;
            private int mPixelFormat = 1;
            private final Rect mSourceCrop = new Rect();
            private float mFrameScaleX = 1.0f;
            private float mFrameScaleY = 1.0f;
            private long mUid = -1;

            abstract T getThis();

            Builder() {
            }

            public T setPixelFormat(int pixelFormat) {
                this.mPixelFormat = pixelFormat;
                return getThis();
            }

            public T setSourceCrop(Rect sourceCrop) {
                this.mSourceCrop.set(sourceCrop);
                return getThis();
            }

            public T setFrameScale(float frameScale) {
                this.mFrameScaleX = frameScale;
                this.mFrameScaleY = frameScale;
                return getThis();
            }

            public T setFrameScale(float frameScaleX, float frameScaleY) {
                this.mFrameScaleX = frameScaleX;
                this.mFrameScaleY = frameScaleY;
                return getThis();
            }

            public T setCaptureSecureLayers(boolean captureSecureLayers) {
                this.mCaptureSecureLayers = captureSecureLayers;
                return getThis();
            }

            public T setAllowProtected(boolean allowProtected) {
                this.mAllowProtected = allowProtected;
                return getThis();
            }

            public T setUid(long uid) {
                this.mUid = uid;
                return getThis();
            }

            public T setGrayscale(boolean grayscale) {
                this.mGrayscale = grayscale;
                return getThis();
            }
        }
    }

    /* loaded from: classes3.dex */
    public static class DisplayCaptureArgs extends CaptureArgs {
        private final IBinder mDisplayToken;
        private final int mHeight;
        private final boolean mUseIdentityTransform;
        private final int mWidth;

        private DisplayCaptureArgs(Builder builder) {
            super(builder);
            this.mDisplayToken = builder.mDisplayToken;
            this.mWidth = builder.mWidth;
            this.mHeight = builder.mHeight;
            this.mUseIdentityTransform = builder.mUseIdentityTransform;
        }

        /* loaded from: classes3.dex */
        public static class Builder extends CaptureArgs.Builder<Builder> {
            private IBinder mDisplayToken;
            private int mHeight;
            private boolean mUseIdentityTransform;
            private int mWidth;

            /* JADX WARN: Type inference failed for: r1v1, types: [android.view.SurfaceControl$CaptureArgs$Builder, android.view.SurfaceControl$DisplayCaptureArgs$Builder] */
            @Override // android.view.SurfaceControl.CaptureArgs.Builder
            public /* bridge */ /* synthetic */ Builder setAllowProtected(boolean z) {
                return super.setAllowProtected(z);
            }

            /* JADX WARN: Type inference failed for: r1v1, types: [android.view.SurfaceControl$CaptureArgs$Builder, android.view.SurfaceControl$DisplayCaptureArgs$Builder] */
            @Override // android.view.SurfaceControl.CaptureArgs.Builder
            public /* bridge */ /* synthetic */ Builder setCaptureSecureLayers(boolean z) {
                return super.setCaptureSecureLayers(z);
            }

            /* JADX WARN: Type inference failed for: r1v1, types: [android.view.SurfaceControl$CaptureArgs$Builder, android.view.SurfaceControl$DisplayCaptureArgs$Builder] */
            @Override // android.view.SurfaceControl.CaptureArgs.Builder
            public /* bridge */ /* synthetic */ Builder setFrameScale(float f) {
                return super.setFrameScale(f);
            }

            /* JADX WARN: Type inference failed for: r1v1, types: [android.view.SurfaceControl$CaptureArgs$Builder, android.view.SurfaceControl$DisplayCaptureArgs$Builder] */
            @Override // android.view.SurfaceControl.CaptureArgs.Builder
            public /* bridge */ /* synthetic */ Builder setFrameScale(float f, float f2) {
                return super.setFrameScale(f, f2);
            }

            /* JADX WARN: Type inference failed for: r1v1, types: [android.view.SurfaceControl$CaptureArgs$Builder, android.view.SurfaceControl$DisplayCaptureArgs$Builder] */
            @Override // android.view.SurfaceControl.CaptureArgs.Builder
            public /* bridge */ /* synthetic */ Builder setGrayscale(boolean z) {
                return super.setGrayscale(z);
            }

            /* JADX WARN: Type inference failed for: r1v1, types: [android.view.SurfaceControl$CaptureArgs$Builder, android.view.SurfaceControl$DisplayCaptureArgs$Builder] */
            @Override // android.view.SurfaceControl.CaptureArgs.Builder
            public /* bridge */ /* synthetic */ Builder setPixelFormat(int i) {
                return super.setPixelFormat(i);
            }

            /* JADX WARN: Type inference failed for: r1v1, types: [android.view.SurfaceControl$CaptureArgs$Builder, android.view.SurfaceControl$DisplayCaptureArgs$Builder] */
            @Override // android.view.SurfaceControl.CaptureArgs.Builder
            public /* bridge */ /* synthetic */ Builder setSourceCrop(Rect rect) {
                return super.setSourceCrop(rect);
            }

            /* JADX WARN: Type inference failed for: r1v1, types: [android.view.SurfaceControl$CaptureArgs$Builder, android.view.SurfaceControl$DisplayCaptureArgs$Builder] */
            @Override // android.view.SurfaceControl.CaptureArgs.Builder
            public /* bridge */ /* synthetic */ Builder setUid(long j) {
                return super.setUid(j);
            }

            public DisplayCaptureArgs build() {
                if (this.mDisplayToken != null) {
                    return new DisplayCaptureArgs(this);
                }
                throw new IllegalStateException("Can't take screenshot with null display token");
            }

            public Builder(IBinder displayToken) {
                setDisplayToken(displayToken);
            }

            public Builder setDisplayToken(IBinder displayToken) {
                this.mDisplayToken = displayToken;
                return this;
            }

            public Builder setSize(int width, int height) {
                this.mWidth = width;
                this.mHeight = height;
                return this;
            }

            public Builder setUseIdentityTransform(boolean useIdentityTransform) {
                this.mUseIdentityTransform = useIdentityTransform;
                return this;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // android.view.SurfaceControl.CaptureArgs.Builder
            public Builder getThis() {
                return this;
            }
        }
    }

    /* loaded from: classes3.dex */
    public static class LayerCaptureArgs extends CaptureArgs {
        private final boolean mChildrenOnly;
        private final long[] mNativeExcludeLayers;
        private final long mNativeLayer;

        private LayerCaptureArgs(Builder builder) {
            super(builder);
            this.mChildrenOnly = builder.mChildrenOnly;
            this.mNativeLayer = builder.mLayer.mNativeObject;
            if (builder.mExcludeLayers != null) {
                this.mNativeExcludeLayers = new long[builder.mExcludeLayers.length];
                for (int i = 0; i < builder.mExcludeLayers.length; i++) {
                    this.mNativeExcludeLayers[i] = builder.mExcludeLayers[i].mNativeObject;
                }
                return;
            }
            this.mNativeExcludeLayers = null;
        }

        /* loaded from: classes3.dex */
        public static class Builder extends CaptureArgs.Builder<Builder> {
            private boolean mChildrenOnly = true;
            private SurfaceControl[] mExcludeLayers;
            private SurfaceControl mLayer;

            /* JADX WARN: Type inference failed for: r1v1, types: [android.view.SurfaceControl$CaptureArgs$Builder, android.view.SurfaceControl$LayerCaptureArgs$Builder] */
            @Override // android.view.SurfaceControl.CaptureArgs.Builder
            public /* bridge */ /* synthetic */ Builder setAllowProtected(boolean z) {
                return super.setAllowProtected(z);
            }

            /* JADX WARN: Type inference failed for: r1v1, types: [android.view.SurfaceControl$CaptureArgs$Builder, android.view.SurfaceControl$LayerCaptureArgs$Builder] */
            @Override // android.view.SurfaceControl.CaptureArgs.Builder
            public /* bridge */ /* synthetic */ Builder setCaptureSecureLayers(boolean z) {
                return super.setCaptureSecureLayers(z);
            }

            /* JADX WARN: Type inference failed for: r1v1, types: [android.view.SurfaceControl$CaptureArgs$Builder, android.view.SurfaceControl$LayerCaptureArgs$Builder] */
            @Override // android.view.SurfaceControl.CaptureArgs.Builder
            public /* bridge */ /* synthetic */ Builder setFrameScale(float f) {
                return super.setFrameScale(f);
            }

            /* JADX WARN: Type inference failed for: r1v1, types: [android.view.SurfaceControl$CaptureArgs$Builder, android.view.SurfaceControl$LayerCaptureArgs$Builder] */
            @Override // android.view.SurfaceControl.CaptureArgs.Builder
            public /* bridge */ /* synthetic */ Builder setFrameScale(float f, float f2) {
                return super.setFrameScale(f, f2);
            }

            /* JADX WARN: Type inference failed for: r1v1, types: [android.view.SurfaceControl$CaptureArgs$Builder, android.view.SurfaceControl$LayerCaptureArgs$Builder] */
            @Override // android.view.SurfaceControl.CaptureArgs.Builder
            public /* bridge */ /* synthetic */ Builder setGrayscale(boolean z) {
                return super.setGrayscale(z);
            }

            /* JADX WARN: Type inference failed for: r1v1, types: [android.view.SurfaceControl$CaptureArgs$Builder, android.view.SurfaceControl$LayerCaptureArgs$Builder] */
            @Override // android.view.SurfaceControl.CaptureArgs.Builder
            public /* bridge */ /* synthetic */ Builder setPixelFormat(int i) {
                return super.setPixelFormat(i);
            }

            /* JADX WARN: Type inference failed for: r1v1, types: [android.view.SurfaceControl$CaptureArgs$Builder, android.view.SurfaceControl$LayerCaptureArgs$Builder] */
            @Override // android.view.SurfaceControl.CaptureArgs.Builder
            public /* bridge */ /* synthetic */ Builder setSourceCrop(Rect rect) {
                return super.setSourceCrop(rect);
            }

            /* JADX WARN: Type inference failed for: r1v1, types: [android.view.SurfaceControl$CaptureArgs$Builder, android.view.SurfaceControl$LayerCaptureArgs$Builder] */
            @Override // android.view.SurfaceControl.CaptureArgs.Builder
            public /* bridge */ /* synthetic */ Builder setUid(long j) {
                return super.setUid(j);
            }

            public LayerCaptureArgs build() {
                if (this.mLayer != null) {
                    return new LayerCaptureArgs(this);
                }
                throw new IllegalStateException("Can't take screenshot with null layer");
            }

            public Builder(SurfaceControl layer) {
                setLayer(layer);
            }

            public Builder setLayer(SurfaceControl layer) {
                this.mLayer = layer;
                return this;
            }

            public Builder setExcludeLayers(SurfaceControl[] excludeLayers) {
                this.mExcludeLayers = excludeLayers;
                return this;
            }

            public Builder setChildrenOnly(boolean childrenOnly) {
                this.mChildrenOnly = childrenOnly;
                return this;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // android.view.SurfaceControl.CaptureArgs.Builder
            public Builder getThis() {
                return this;
            }
        }
    }

    /* loaded from: classes3.dex */
    public static class Builder {
        private int mHeight;
        private WeakReference<View> mLocalOwnerView;
        private SparseIntArray mMetadata;
        private String mName;
        private SurfaceControl mParent;
        private SurfaceSession mSession;
        private int mWidth;
        private int mFlags = 4;
        private int mFormat = -1;
        private String mCallsite = "SurfaceControl.Builder";

        public Builder(SurfaceSession session) {
            this.mSession = session;
        }

        public Builder() {
        }

        public SurfaceControl build() {
            int i;
            int i2 = this.mWidth;
            if (i2 < 0 || (i = this.mHeight) < 0) {
                throw new IllegalStateException("width and height must be positive or unset");
            } else if ((i2 > 0 || i > 0) && (isEffectLayer() || isContainerLayer())) {
                throw new IllegalStateException("Only buffer layers can set a valid buffer size.");
            } else {
                if ((this.mFlags & SurfaceControl.FX_SURFACE_MASK) == 0) {
                    setBLASTLayer();
                }
                return new SurfaceControl(this.mSession, this.mName, this.mWidth, this.mHeight, this.mFormat, this.mFlags, this.mParent, this.mMetadata, this.mLocalOwnerView, this.mCallsite);
            }
        }

        public Builder setName(String name) {
            this.mName = name;
            return this;
        }

        public Builder setLocalOwnerView(View view) {
            this.mLocalOwnerView = new WeakReference<>(view);
            return this;
        }

        public Builder setBufferSize(int width, int height) {
            if (width < 0 || height < 0) {
                throw new IllegalArgumentException("width and height must be positive");
            }
            this.mWidth = width;
            this.mHeight = height;
            return setFlags(0, SurfaceControl.FX_SURFACE_MASK);
        }

        private void unsetBufferSize() {
            this.mWidth = 0;
            this.mHeight = 0;
        }

        public Builder setFormat(int format) {
            this.mFormat = format;
            return this;
        }

        public Builder setProtected(boolean protectedContent) {
            if (protectedContent) {
                this.mFlags |= 2048;
            } else {
                this.mFlags &= -2049;
            }
            return this;
        }

        public Builder setSecure(boolean secure) {
            if (secure) {
                this.mFlags |= 128;
            } else {
                this.mFlags &= -129;
            }
            return this;
        }

        public Builder setOpaque(boolean opaque) {
            if (opaque) {
                this.mFlags |= 1024;
            } else {
                this.mFlags &= -1025;
            }
            return this;
        }

        public Builder setHidden(boolean hidden) {
            if (hidden) {
                this.mFlags |= 4;
            } else {
                this.mFlags &= -5;
            }
            return this;
        }

        public Builder setParent(SurfaceControl parent) {
            String str;
            if ((SurfaceControl.DEBUG_SFC || SurfaceControl.DEBUG_ALL) && (str = this.mName) != null && str.toString().contains("WindowToken") && !this.mName.toString().contains("WallpaperWindowToken")) {
                Log.e(SurfaceControl.TAG, "setParent " + this.mName + " parent " + parent + " callers " + Debug.getCallers(5));
            }
            this.mParent = parent;
            return this;
        }

        public Builder setMetadata(int key, int data) {
            if (this.mMetadata == null) {
                this.mMetadata = new SparseIntArray();
            }
            this.mMetadata.put(key, data);
            return this;
        }

        public Builder setEffectLayer() {
            this.mFlags |= 16384;
            unsetBufferSize();
            return setFlags(131072, SurfaceControl.FX_SURFACE_MASK);
        }

        public Builder setColorLayer() {
            unsetBufferSize();
            return setFlags(131072, SurfaceControl.FX_SURFACE_MASK);
        }

        private boolean isEffectLayer() {
            return (this.mFlags & 131072) == 131072;
        }

        public Builder setBLASTLayer() {
            return setFlags(262144, SurfaceControl.FX_SURFACE_MASK);
        }

        public Builder setContainerLayer() {
            unsetBufferSize();
            return setFlags(524288, SurfaceControl.FX_SURFACE_MASK);
        }

        private boolean isContainerLayer() {
            return (this.mFlags & 524288) == 524288;
        }

        public Builder setFlags(int flags) {
            this.mFlags = flags;
            return this;
        }

        public Builder setCallsite(String callsite) {
            this.mCallsite = callsite;
            return this;
        }

        private Builder setFlags(int flags, int mask) {
            this.mFlags = (this.mFlags & (~mask)) | flags;
            return this;
        }
    }

    private SurfaceControl(SurfaceSession session, String name, int w, int h, int format, int flags, SurfaceControl parent, SparseIntArray metadata, WeakReference<View> localOwnerView, String callsite) throws Surface.OutOfResourcesException, IllegalArgumentException {
        Parcel metaParcel;
        Throwable th;
        this.mCloseGuard = CloseGuard.get();
        this.mLock = new Object();
        if (name != null) {
            this.mName = name;
            this.mWidth = w;
            this.mHeight = h;
            this.mLocalOwnerView = localOwnerView;
            Parcel metaParcel2 = Parcel.obtain();
            if (metadata != null) {
                try {
                    if (metadata.size() > 0) {
                        metaParcel2.writeInt(metadata.size());
                        for (int i = 0; i < metadata.size(); i++) {
                            metaParcel2.writeInt(metadata.keyAt(i));
                            metaParcel2.writeByteArray(ByteBuffer.allocate(4).order(ByteOrder.nativeOrder()).putInt(metadata.valueAt(i)).array());
                        }
                        metaParcel2.setDataPosition(0);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    metaParcel = metaParcel2;
                    metaParcel.recycle();
                    throw th;
                }
            }
            metaParcel = metaParcel2;
            try {
                this.mNativeObject = nativeCreate(session, name, w, h, format, flags, parent != null ? parent.mNativeObject : 0L, metaParcel);
                metaParcel.recycle();
                long j = this.mNativeObject;
                if (j != 0) {
                    this.mNativeHandle = nativeGetHandle(j);
                    this.mCloseGuard.openWithCallSite("release", callsite);
                    return;
                }
                throw new Surface.OutOfResourcesException("Couldn't allocate SurfaceControl native object");
            } catch (Throwable th3) {
                th = th3;
                metaParcel.recycle();
                throw th;
            }
        } else {
            throw new IllegalArgumentException("name must not be null");
        }
    }

    public SurfaceControl(SurfaceControl other, String callsite) {
        this.mCloseGuard = CloseGuard.get();
        this.mLock = new Object();
        copyFrom(other, callsite);
    }

    private SurfaceControl(Parcel in) {
        this.mCloseGuard = CloseGuard.get();
        this.mLock = new Object();
        readFromParcel(in);
    }

    public SurfaceControl() {
        this.mCloseGuard = CloseGuard.get();
        this.mLock = new Object();
    }

    public void readFromParcel(Parcel in) {
        if (in != null) {
            this.mName = in.readString8();
            this.mWidth = in.readInt();
            this.mHeight = in.readInt();
            long object = 0;
            if (in.readInt() != 0) {
                object = nativeReadFromParcel(in);
            }
            assignNativeObject(object, "readFromParcel");
            return;
        }
        throw new IllegalArgumentException("source must not be null");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString8(this.mName);
        dest.writeInt(this.mWidth);
        dest.writeInt(this.mHeight);
        if (this.mNativeObject == 0) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
        }
        nativeWriteToParcel(this.mNativeObject, dest);
        if ((flags & 1) != 0) {
            release();
        }
    }

    public boolean isSameSurface(SurfaceControl other) {
        return other.mNativeHandle == this.mNativeHandle;
    }

    public void dumpDebug(ProtoOutputStream proto, long fieldId) {
        long token = proto.start(fieldId);
        proto.write(1120986464257L, System.identityHashCode(this));
        proto.write(1138166333442L, this.mName);
        proto.end(token);
    }

    protected void finalize() throws Throwable {
        if (LOG_SURFACE_CONTROL) {
            Log.d(TAG, "finalize, mNativeObject:" + this.mNativeObject, new Throwable());
        }
        try {
            CloseGuard closeGuard = this.mCloseGuard;
            if (closeGuard != null) {
                closeGuard.warnIfOpen();
            }
            if (this.mNativeObject != 0) {
                if (DEBUG_SFC) {
                    Log.v(TAG, "SurfaceControl finalize:  this " + this + " caller=" + Debug.getCallers(5));
                }
                nativeRelease(this.mNativeObject);
                this.mNativeObject = 0L;
                this.mNativeHandle = 0L;
            }
        } finally {
            super.finalize();
        }
    }

    public void release() {
        if (LOG_SURFACE_CONTROL) {
            Log.d(TAG, "release, mNativeObject:" + this.mNativeObject, new Throwable());
        }
        if (this.mNativeObject != 0) {
            if (DEBUG_SFC) {
                Log.v(TAG, "SurfaceControl release:  this " + this + " caller=" + Debug.getCallers(5));
            }
            nativeRelease(this.mNativeObject);
            this.mNativeObject = 0L;
            this.mNativeHandle = 0L;
            this.mCloseGuard.close();
        }
    }

    public void disconnect() {
        if (LOG_SURFACE_CONTROL) {
            Log.d(TAG, "disconnect, mNativeObject:" + this.mNativeObject);
        }
        long j = this.mNativeObject;
        if (j != 0) {
            nativeDisconnect(j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkNotReleased() {
        if (this.mNativeObject == 0) {
            throw new NullPointerException("Invalid " + this + ", mNativeObject is null. Have you called release() already?");
        }
    }

    public boolean isValid() {
        return this.mNativeObject != 0;
    }

    public static void openTransaction() {
        synchronized (SurfaceControl.class) {
            if (sGlobalTransaction == null) {
                sGlobalTransaction = new GlobalTransactionWrapper();
            }
            synchronized (SurfaceControl.class) {
                sTransactionNestCount++;
            }
        }
    }

    @Deprecated
    public static void mergeToGlobalTransaction(Transaction t) {
        synchronized (SurfaceControl.class) {
            sGlobalTransaction.merge(t);
        }
    }

    public static void closeTransaction() {
        synchronized (SurfaceControl.class) {
            long j = sTransactionNestCount;
            if (j == 0) {
                Log.e(TAG, "Call to SurfaceControl.closeTransaction without matching openTransaction");
            } else {
                long j2 = j - 1;
                sTransactionNestCount = j2;
                if (j2 > 0) {
                    return;
                }
            }
            sGlobalTransaction.applyGlobalTransaction(false);
        }
    }

    public boolean clearContentFrameStats() {
        checkNotReleased();
        return nativeClearContentFrameStats(this.mNativeObject);
    }

    public boolean getContentFrameStats(WindowContentFrameStats outStats) {
        checkNotReleased();
        return nativeGetContentFrameStats(this.mNativeObject, outStats);
    }

    public static boolean clearAnimationFrameStats() {
        return nativeClearAnimationFrameStats();
    }

    public static boolean getAnimationFrameStats(WindowAnimationFrameStats outStats) {
        return nativeGetAnimationFrameStats(outStats);
    }

    public int getWidth() {
        int i;
        synchronized (this.mLock) {
            i = this.mWidth;
        }
        return i;
    }

    public int getHeight() {
        int i;
        synchronized (this.mLock) {
            i = this.mHeight;
        }
        return i;
    }

    public View getLocalOwnerView() {
        WeakReference<View> weakReference = this.mLocalOwnerView;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public String toString() {
        return "Surface(name=" + this.mName + ")/@0x" + Integer.toHexString(System.identityHashCode(this));
    }

    /* loaded from: classes3.dex */
    public static final class StaticDisplayInfo {
        public float density;
        public DeviceProductInfo deviceProductInfo;
        public boolean isInternal;
        public boolean secure;

        public String toString() {
            return "StaticDisplayInfo{isInternal=" + this.isInternal + ", density=" + this.density + ", secure=" + this.secure + ", deviceProductInfo=" + this.deviceProductInfo + "}";
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            StaticDisplayInfo that = (StaticDisplayInfo) o;
            if (this.isInternal == that.isInternal && this.density == that.density && this.secure == that.secure && Objects.equals(this.deviceProductInfo, that.deviceProductInfo)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Boolean.valueOf(this.isInternal), Float.valueOf(this.density), Boolean.valueOf(this.secure), this.deviceProductInfo);
        }
    }

    /* loaded from: classes3.dex */
    public static final class DynamicDisplayInfo {
        public int activeColorMode;
        public int activeDisplayModeId;
        public boolean autoLowLatencyModeSupported;
        public boolean gameContentTypeSupported;
        public Display.HdrCapabilities hdrCapabilities;
        public int[] supportedColorModes;
        public DisplayMode[] supportedDisplayModes;

        public String toString() {
            return "DynamicDisplayInfo{supportedDisplayModes=" + Arrays.toString(this.supportedDisplayModes) + ", activeDisplayModeId=" + this.activeDisplayModeId + ", supportedColorModes=" + Arrays.toString(this.supportedColorModes) + ", activeColorMode=" + this.activeColorMode + ", hdrCapabilities=" + this.hdrCapabilities + ", autoLowLatencyModeSupported=" + this.autoLowLatencyModeSupported + ", gameContentTypeSupported" + this.gameContentTypeSupported + "}";
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            DynamicDisplayInfo that = (DynamicDisplayInfo) o;
            if (!Arrays.equals(this.supportedDisplayModes, that.supportedDisplayModes) || this.activeDisplayModeId != that.activeDisplayModeId || !Arrays.equals(this.supportedColorModes, that.supportedColorModes) || this.activeColorMode != that.activeColorMode || !Objects.equals(this.hdrCapabilities, that.hdrCapabilities)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return Objects.hash(this.supportedDisplayModes, Integer.valueOf(this.activeDisplayModeId), Integer.valueOf(this.activeDisplayModeId), Integer.valueOf(this.activeColorMode), this.hdrCapabilities);
        }
    }

    /* loaded from: classes3.dex */
    public static final class DisplayMode {
        public static final int INVALID_DISPLAY_MODE_ID = -1;
        public long appVsyncOffsetNanos;
        public int group;
        public int height;
        public int id;
        public long presentationDeadlineNanos;
        public float refreshRate;
        public int width;
        public float xDpi;
        public float yDpi;

        public String toString() {
            return "DisplayMode{id=" + this.id + ", width=" + this.width + ", height=" + this.height + ", xDpi=" + this.xDpi + ", yDpi=" + this.yDpi + ", refreshRate=" + this.refreshRate + ", appVsyncOffsetNanos=" + this.appVsyncOffsetNanos + ", presentationDeadlineNanos=" + this.presentationDeadlineNanos + ", group=" + this.group + "}";
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            DisplayMode that = (DisplayMode) o;
            if (this.id == that.id && this.width == that.width && this.height == that.height && Float.compare(that.xDpi, this.xDpi) == 0 && Float.compare(that.yDpi, this.yDpi) == 0 && Float.compare(that.refreshRate, this.refreshRate) == 0 && this.appVsyncOffsetNanos == that.appVsyncOffsetNanos && this.presentationDeadlineNanos == that.presentationDeadlineNanos && this.group == that.group) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.id), Integer.valueOf(this.width), Integer.valueOf(this.height), Float.valueOf(this.xDpi), Float.valueOf(this.yDpi), Float.valueOf(this.refreshRate), Long.valueOf(this.appVsyncOffsetNanos), Long.valueOf(this.presentationDeadlineNanos), Integer.valueOf(this.group));
        }
    }

    public static void setDisplayPowerMode(IBinder displayToken, int mode) {
        if (displayToken != null) {
            nativeSetDisplayPowerMode(displayToken, mode);
            return;
        }
        throw new IllegalArgumentException("displayToken must not be null");
    }

    public static StaticDisplayInfo getStaticDisplayInfo(IBinder displayToken) {
        if (displayToken != null) {
            return nativeGetStaticDisplayInfo(displayToken);
        }
        throw new IllegalArgumentException("displayToken must not be null");
    }

    public static DynamicDisplayInfo getDynamicDisplayInfo(IBinder displayToken) {
        if (displayToken != null) {
            return nativeGetDynamicDisplayInfo(displayToken);
        }
        throw new IllegalArgumentException("displayToken must not be null");
    }

    public static DisplayedContentSamplingAttributes getDisplayedContentSamplingAttributes(IBinder displayToken) {
        if (displayToken != null) {
            return nativeGetDisplayedContentSamplingAttributes(displayToken);
        }
        throw new IllegalArgumentException("displayToken must not be null");
    }

    public static boolean setDisplayedContentSamplingEnabled(IBinder displayToken, boolean enable, int componentMask, int maxFrames) {
        if (displayToken == null) {
            throw new IllegalArgumentException("displayToken must not be null");
        } else if ((componentMask >> 4) == 0) {
            return nativeSetDisplayedContentSamplingEnabled(displayToken, enable, componentMask, maxFrames);
        } else {
            throw new IllegalArgumentException("invalid componentMask when enabling sampling");
        }
    }

    public static DisplayedContentSample getDisplayedContentSample(IBinder displayToken, long maxFrames, long timestamp) {
        if (displayToken != null) {
            return nativeGetDisplayedContentSample(displayToken, maxFrames, timestamp);
        }
        throw new IllegalArgumentException("displayToken must not be null");
    }

    /* loaded from: classes3.dex */
    public static final class DesiredDisplayModeSpecs {
        public boolean allowGroupSwitching;
        public float appRequestRefreshRateMax;
        public float appRequestRefreshRateMin;
        public int defaultMode;
        public float primaryRefreshRateMax;
        public float primaryRefreshRateMin;

        public DesiredDisplayModeSpecs() {
        }

        public DesiredDisplayModeSpecs(DesiredDisplayModeSpecs other) {
            copyFrom(other);
        }

        public DesiredDisplayModeSpecs(int defaultMode, boolean allowGroupSwitching, float primaryRefreshRateMin, float primaryRefreshRateMax, float appRequestRefreshRateMin, float appRequestRefreshRateMax) {
            this.defaultMode = defaultMode;
            this.allowGroupSwitching = allowGroupSwitching;
            this.primaryRefreshRateMin = primaryRefreshRateMin;
            this.primaryRefreshRateMax = primaryRefreshRateMax;
            this.appRequestRefreshRateMin = appRequestRefreshRateMin;
            this.appRequestRefreshRateMax = appRequestRefreshRateMax;
        }

        public boolean equals(Object o) {
            return (o instanceof DesiredDisplayModeSpecs) && equals((DesiredDisplayModeSpecs) o);
        }

        public boolean equals(DesiredDisplayModeSpecs other) {
            return other != null && this.defaultMode == other.defaultMode && this.primaryRefreshRateMin == other.primaryRefreshRateMin && this.primaryRefreshRateMax == other.primaryRefreshRateMax && this.appRequestRefreshRateMin == other.appRequestRefreshRateMin && this.appRequestRefreshRateMax == other.appRequestRefreshRateMax;
        }

        public int hashCode() {
            return 0;
        }

        public void copyFrom(DesiredDisplayModeSpecs other) {
            this.defaultMode = other.defaultMode;
            this.primaryRefreshRateMin = other.primaryRefreshRateMin;
            this.primaryRefreshRateMax = other.primaryRefreshRateMax;
            this.appRequestRefreshRateMin = other.appRequestRefreshRateMin;
            this.appRequestRefreshRateMax = other.appRequestRefreshRateMax;
        }

        public String toString() {
            return String.format("defaultConfig=%d primaryRefreshRateRange=[%.0f %.0f] appRequestRefreshRateRange=[%.0f %.0f]", Integer.valueOf(this.defaultMode), Float.valueOf(this.primaryRefreshRateMin), Float.valueOf(this.primaryRefreshRateMax), Float.valueOf(this.appRequestRefreshRateMin), Float.valueOf(this.appRequestRefreshRateMax));
        }
    }

    public static boolean setDesiredDisplayModeSpecs(IBinder displayToken, DesiredDisplayModeSpecs desiredDisplayModeSpecs) {
        if (displayToken == null) {
            throw new IllegalArgumentException("displayToken must not be null");
        } else if (desiredDisplayModeSpecs == null) {
            throw new IllegalArgumentException("desiredDisplayModeSpecs must not be null");
        } else if (desiredDisplayModeSpecs.defaultMode >= 0) {
            return nativeSetDesiredDisplayModeSpecs(displayToken, desiredDisplayModeSpecs);
        } else {
            throw new IllegalArgumentException("defaultMode must be non-negative");
        }
    }

    public static DesiredDisplayModeSpecs getDesiredDisplayModeSpecs(IBinder displayToken) {
        if (displayToken != null) {
            return nativeGetDesiredDisplayModeSpecs(displayToken);
        }
        throw new IllegalArgumentException("displayToken must not be null");
    }

    public static DisplayPrimaries getDisplayNativePrimaries(IBinder displayToken) {
        if (displayToken != null) {
            return nativeGetDisplayNativePrimaries(displayToken);
        }
        throw new IllegalArgumentException("displayToken must not be null");
    }

    public static boolean setActiveColorMode(IBinder displayToken, int colorMode) {
        if (displayToken != null) {
            return nativeSetActiveColorMode(displayToken, colorMode);
        }
        throw new IllegalArgumentException("displayToken must not be null");
    }

    public static ColorSpace[] getCompositionColorSpaces() {
        int[] dataspaces = nativeGetCompositionDataspaces();
        ColorSpace srgb = ColorSpace.get(ColorSpace.Named.SRGB);
        ColorSpace[] colorSpaces = new ColorSpace[2];
        colorSpaces[0] = srgb;
        colorSpaces[1] = srgb;
        if (dataspaces.length == 2) {
            for (int i = 0; i < 2; i++) {
                switch (dataspaces[i]) {
                    case INTERNAL_DATASPACE_DISPLAY_P3 /* 143261696 */:
                        colorSpaces[i] = ColorSpace.get(ColorSpace.Named.DISPLAY_P3);
                        break;
                    case INTERNAL_DATASPACE_SCRGB /* 411107328 */:
                        colorSpaces[i] = ColorSpace.get(ColorSpace.Named.EXTENDED_SRGB);
                        break;
                }
            }
        }
        return colorSpaces;
    }

    public static void setAutoLowLatencyMode(IBinder displayToken, boolean on) {
        if (displayToken != null) {
            nativeSetAutoLowLatencyMode(displayToken, on);
            return;
        }
        throw new IllegalArgumentException("displayToken must not be null");
    }

    public static void setGameContentType(IBinder displayToken, boolean on) {
        if (displayToken != null) {
            nativeSetGameContentType(displayToken, on);
            return;
        }
        throw new IllegalArgumentException("displayToken must not be null");
    }

    public static void setDisplayProjection(IBinder displayToken, int orientation, Rect layerStackRect, Rect displayRect) {
        synchronized (SurfaceControl.class) {
            sGlobalTransaction.setDisplayProjection(displayToken, orientation, layerStackRect, displayRect);
        }
    }

    public static void setDisplayLayerStack(IBinder displayToken, int layerStack) {
        synchronized (SurfaceControl.class) {
            sGlobalTransaction.setDisplayLayerStack(displayToken, layerStack);
        }
    }

    public static void setDisplaySurface(IBinder displayToken, Surface surface) {
        synchronized (SurfaceControl.class) {
            sGlobalTransaction.setDisplaySurface(displayToken, surface);
        }
    }

    public static void setDisplaySize(IBinder displayToken, int width, int height) {
        synchronized (SurfaceControl.class) {
            sGlobalTransaction.setDisplaySize(displayToken, width, height);
        }
    }

    public static void overrideHdrTypes(IBinder displayToken, int[] modes) {
        nativeOverrideHdrTypes(displayToken, modes);
    }

    public static IBinder createDisplay(String name, boolean secure) {
        if (name != null) {
            return nativeCreateDisplay(name, secure);
        }
        throw new IllegalArgumentException("name must not be null");
    }

    public static void destroyDisplay(IBinder displayToken) {
        if (displayToken != null) {
            nativeDestroyDisplay(displayToken);
            return;
        }
        throw new IllegalArgumentException("displayToken must not be null");
    }

    public static long[] getPhysicalDisplayIds() {
        return nativeGetPhysicalDisplayIds();
    }

    public static IBinder getPhysicalDisplayToken(long physicalDisplayId) {
        return nativeGetPhysicalDisplayToken(physicalDisplayId);
    }

    public static IBinder getInternalDisplayToken() {
        long[] physicalDisplayIds = getPhysicalDisplayIds();
        if (physicalDisplayIds.length == 0) {
            return null;
        }
        return getPhysicalDisplayToken(physicalDisplayIds[0]);
    }

    public static int captureDisplay(DisplayCaptureArgs captureArgs, ScreenCaptureListener captureListener) {
        return nativeCaptureDisplay(captureArgs, captureListener);
    }

    public static ScreenshotHardwareBuffer captureDisplay(DisplayCaptureArgs captureArgs) {
        SyncScreenCaptureListener screenCaptureListener = new SyncScreenCaptureListener();
        int status = captureDisplay(captureArgs, screenCaptureListener);
        if (status != 0) {
            return null;
        }
        return screenCaptureListener.waitForScreenshot();
    }

    public static ScreenshotHardwareBuffer captureLayers(SurfaceControl layer, Rect sourceCrop, float frameScale) {
        return captureLayers(layer, sourceCrop, frameScale, 1);
    }

    public static ScreenshotHardwareBuffer captureLayers(SurfaceControl layer, Rect sourceCrop, float frameScale, int format) {
        LayerCaptureArgs captureArgs = ((LayerCaptureArgs.Builder) ((LayerCaptureArgs.Builder) ((LayerCaptureArgs.Builder) new LayerCaptureArgs.Builder(layer).setSourceCrop(sourceCrop)).setFrameScale(frameScale)).setPixelFormat(format)).build();
        return captureLayers(captureArgs);
    }

    public static ScreenshotHardwareBuffer captureLayers(LayerCaptureArgs captureArgs) {
        SyncScreenCaptureListener screenCaptureListener = new SyncScreenCaptureListener();
        int status = captureLayers(captureArgs, screenCaptureListener);
        if (status != 0) {
            return null;
        }
        return screenCaptureListener.waitForScreenshot();
    }

    public static ScreenshotHardwareBuffer captureLayersExcluding(SurfaceControl layer, Rect sourceCrop, float frameScale, int format, SurfaceControl[] exclude) {
        LayerCaptureArgs captureArgs = ((LayerCaptureArgs.Builder) ((LayerCaptureArgs.Builder) ((LayerCaptureArgs.Builder) new LayerCaptureArgs.Builder(layer).setSourceCrop(sourceCrop)).setFrameScale(frameScale)).setPixelFormat(format)).setExcludeLayers(exclude).build();
        return captureLayers(captureArgs);
    }

    public static int captureLayers(LayerCaptureArgs captureArgs, ScreenCaptureListener captureListener) {
        return nativeCaptureLayers(captureArgs, captureListener);
    }

    public static boolean getProtectedContentSupport() {
        return nativeGetProtectedContentSupport();
    }

    public static boolean getDisplayBrightnessSupport(IBinder displayToken) {
        return nativeGetDisplayBrightnessSupport(displayToken);
    }

    public static boolean setDisplayBrightness(IBinder displayToken, float brightness) {
        return setDisplayBrightness(displayToken, brightness, -1.0f, brightness, -1.0f);
    }

    public static boolean setDisplayBrightness(IBinder displayToken, float sdrBrightness, float sdrBrightnessNits, float displayBrightness, float displayBrightnessNits) {
        Objects.requireNonNull(displayToken);
        if (Float.isNaN(displayBrightness) || displayBrightness > 1.0f || (displayBrightness < 0.0f && displayBrightness != -1.0f)) {
            throw new IllegalArgumentException("displayBrightness must be a number between 0.0f  and 1.0f, or -1 to turn the backlight off: " + displayBrightness);
        } else if (!Float.isNaN(sdrBrightness) && sdrBrightness <= 1.0f && (sdrBrightness >= 0.0f || sdrBrightness == -1.0f)) {
            return nativeSetDisplayBrightness(displayToken, sdrBrightness, sdrBrightnessNits, displayBrightness, displayBrightnessNits);
        } else {
            throw new IllegalArgumentException("sdrBrightness must be a number between 0.0f and 1.0f, or -1 to turn the backlight off: " + sdrBrightness);
        }
    }

    public static SurfaceControl mirrorSurface(SurfaceControl mirrorOf) {
        long nativeObj = nativeMirrorSurface(mirrorOf.mNativeObject);
        SurfaceControl sc = new SurfaceControl();
        sc.assignNativeObject(nativeObj, "mirrorSurface");
        return sc;
    }

    private static void validateColorArg(float[] color) {
        if (color.length == 4) {
            for (float c : color) {
                if (c < 0.0f || c > 1.0f) {
                    throw new IllegalArgumentException("Color must be specified as a float array with four values to represent r, g, b, a in range [0..1]");
                }
            }
            return;
        }
        throw new IllegalArgumentException("Color must be specified as a float array with four values to represent r, g, b, a in range [0..1]");
    }

    public static void setGlobalShadowSettings(float[] ambientColor, float[] spotColor, float lightPosY, float lightPosZ, float lightRadius) {
        validateColorArg(ambientColor);
        validateColorArg(spotColor);
        nativeSetGlobalShadowSettings(ambientColor, spotColor, lightPosY, lightPosZ, lightRadius);
    }

    public static void addJankDataListener(OnJankDataListener listener, SurfaceControl surface) {
        nativeAddJankDataListener(listener.mNativePtr.get(), surface.mNativeObject);
    }

    public static void removeJankDataListener(OnJankDataListener listener) {
        nativeRemoveJankDataListener(listener.mNativePtr.get());
    }

    public static int getGPUContextPriority() {
        return nativeGetGPUContextPriority();
    }

    /* loaded from: classes3.dex */
    public static class Transaction implements Closeable, Parcelable {
        Runnable mFreeNativeResources;
        public long mNativeObject;
        private final ArrayMap<SurfaceControl, SurfaceControl> mReparentedSurfaces;
        private final ArrayMap<SurfaceControl, Point> mResizedSurfaces;
        public static final NativeAllocationRegistry sRegistry = new NativeAllocationRegistry(Transaction.class.getClassLoader(), SurfaceControl.nativeGetNativeTransactionFinalizer(), 512);
        private static final float[] INVALID_COLOR = {-1.0f, -1.0f, -1.0f};
        public static final Parcelable.Creator<Transaction> CREATOR = new Parcelable.Creator<Transaction>() { // from class: android.view.SurfaceControl.Transaction.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Transaction createFromParcel(Parcel in) {
                return new Transaction(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Transaction[] newArray(int size) {
                return new Transaction[size];
            }
        };

        protected void checkPreconditions(SurfaceControl sc) {
            sc.checkNotReleased();
        }

        public Transaction() {
            this.mResizedSurfaces = new ArrayMap<>();
            this.mReparentedSurfaces = new ArrayMap<>();
            long nativeCreateTransaction = SurfaceControl.nativeCreateTransaction();
            this.mNativeObject = nativeCreateTransaction;
            this.mFreeNativeResources = sRegistry.registerNativeAllocation(this, nativeCreateTransaction);
        }

        private Transaction(Parcel in) {
            this.mResizedSurfaces = new ArrayMap<>();
            this.mReparentedSurfaces = new ArrayMap<>();
            readFromParcel(in);
        }

        public void apply() {
            apply(false);
        }

        public void clear() {
            this.mResizedSurfaces.clear();
            this.mReparentedSurfaces.clear();
            long j = this.mNativeObject;
            if (j != 0) {
                SurfaceControl.nativeClearTransaction(j);
            }
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (SurfaceControl.DEBUG_SFC || SurfaceControl.DEBUG_ALL) {
                Log.v(SurfaceControl.TAG, "Transaction close sc " + this + " caller=" + Debug.getCallers(SurfaceControl.DEBUG_DEPTH));
            }
            this.mResizedSurfaces.clear();
            this.mReparentedSurfaces.clear();
            this.mFreeNativeResources.run();
            this.mNativeObject = 0L;
        }

        public void apply(boolean sync) {
            applyResizedSurfaces();
            notifyReparentedSurfaces();
            SurfaceControl.nativeApplyTransaction(this.mNativeObject, sync);
        }

        protected void applyResizedSurfaces() {
            for (int i = this.mResizedSurfaces.size() - 1; i >= 0; i--) {
                Point size = this.mResizedSurfaces.valueAt(i);
                SurfaceControl surfaceControl = this.mResizedSurfaces.keyAt(i);
                synchronized (surfaceControl.mLock) {
                    surfaceControl.resize(size.x, size.y);
                }
            }
            this.mResizedSurfaces.clear();
        }

        protected void notifyReparentedSurfaces() {
            int reparentCount = this.mReparentedSurfaces.size();
            for (int i = reparentCount - 1; i >= 0; i--) {
                SurfaceControl child = this.mReparentedSurfaces.keyAt(i);
                synchronized (child.mLock) {
                    int listenerCount = child.mReparentListeners != null ? child.mReparentListeners.size() : 0;
                    for (int j = 0; j < listenerCount; j++) {
                        OnReparentListener listener = (OnReparentListener) child.mReparentListeners.get(j);
                        listener.onReparent(this, this.mReparentedSurfaces.valueAt(i));
                    }
                    this.mReparentedSurfaces.removeAt(i);
                }
            }
        }

        public Transaction setVisibility(SurfaceControl sc, boolean visible) {
            checkPreconditions(sc);
            if (visible) {
                return show(sc);
            }
            return hide(sc);
        }

        public Transaction setFrameRateSelectionPriority(SurfaceControl sc, int priority) {
            sc.checkNotReleased();
            SurfaceControl.nativeSetFrameRateSelectionPriority(this.mNativeObject, sc.mNativeObject, priority);
            return this;
        }

        public Transaction show(SurfaceControl sc) {
            if (SurfaceControl.DEBUG_SFC || SurfaceControl.DEBUG_ALL) {
                Log.v(SurfaceControl.TAG, "SurfaceControl show : sc " + sc);
            }
            checkPreconditions(sc);
            SurfaceControl.nativeSetFlags(this.mNativeObject, sc.mNativeObject, 0, 1);
            return this;
        }

        public Transaction hide(SurfaceControl sc) {
            if (SurfaceControl.DEBUG_SFC || SurfaceControl.DEBUG_ALL) {
                Log.v(SurfaceControl.TAG, "SurfaceControl hide: sc " + sc);
            }
            checkPreconditions(sc);
            SurfaceControl.nativeSetFlags(this.mNativeObject, sc.mNativeObject, 1, 1);
            return this;
        }

        public Transaction setPosition(SurfaceControl sc, float x, float y) {
            if (SurfaceControl.DEBUG_SFC || SurfaceControl.DEBUG_ALL) {
                Log.v(SurfaceControl.TAG, "SurfaceControl setPosition:  sc " + sc + " x=" + x + " y=" + y + " caller=" + Debug.getCallers(SurfaceControl.DEBUG_DEPTH));
            }
            checkPreconditions(sc);
            SurfaceControl.nativeSetPosition(this.mNativeObject, sc.mNativeObject, x, y);
            return this;
        }

        public Transaction setCastFlags(SurfaceControl sc, int flags) {
            checkPreconditions(sc);
            SurfaceControl.nativeSetFlags(this.mNativeObject, sc.mNativeObject, flags, 32);
            return this;
        }

        public Transaction setAodFlags(SurfaceControl sc, int flags) {
            checkPreconditions(sc);
            SurfaceControl.nativeSetFlags(this.mNativeObject, sc.mNativeObject, flags == 1 ? 512 : 0, 512);
            return this;
        }

        public Transaction setBufferSize(SurfaceControl sc, int w, int h) {
            if (SurfaceControl.DEBUG_SFC) {
                Log.v(SurfaceControl.TAG, "SurfaceControl setBufferSize: w " + w + " h " + h + " sc " + sc);
            }
            checkPreconditions(sc);
            this.mResizedSurfaces.put(sc, new Point(w, h));
            SurfaceControl.nativeSetSize(this.mNativeObject, sc.mNativeObject, w, h);
            return this;
        }

        public Transaction setFixedTransformHint(SurfaceControl sc, int transformHint) {
            checkPreconditions(sc);
            SurfaceControl.nativeSetFixedTransformHint(this.mNativeObject, sc.mNativeObject, transformHint);
            return this;
        }

        public Transaction unsetFixedTransformHint(SurfaceControl sc) {
            checkPreconditions(sc);
            SurfaceControl.nativeSetFixedTransformHint(this.mNativeObject, sc.mNativeObject, -1);
            return this;
        }

        public Transaction setLayer(SurfaceControl sc, int z) {
            if (SurfaceControl.DEBUG_SFC) {
                Log.v(SurfaceControl.TAG, "setLayer z " + z + " sc " + sc);
            }
            checkPreconditions(sc);
            SurfaceControl.nativeSetLayer(this.mNativeObject, sc.mNativeObject, z);
            return this;
        }

        public Transaction setRelativeLayer(SurfaceControl sc, SurfaceControl relativeTo, int z) {
            checkPreconditions(sc);
            SurfaceControl.nativeSetRelativeLayer(this.mNativeObject, sc.mNativeObject, relativeTo.mNativeObject, z);
            return this;
        }

        public Transaction setTransparentRegionHint(SurfaceControl sc, Region transparentRegion) {
            checkPreconditions(sc);
            SurfaceControl.nativeSetTransparentRegionHint(this.mNativeObject, sc.mNativeObject, transparentRegion);
            return this;
        }

        public Transaction setAlpha(SurfaceControl sc, float alpha) {
            checkPreconditions(sc);
            SurfaceControl.nativeSetAlpha(this.mNativeObject, sc.mNativeObject, alpha);
            return this;
        }

        public Transaction setInputWindowInfo(SurfaceControl sc, InputWindowHandle handle) {
            checkPreconditions(sc);
            SurfaceControl.nativeSetInputWindowInfo(this.mNativeObject, sc.mNativeObject, handle);
            return this;
        }

        public Transaction syncInputWindows() {
            SurfaceControl.nativeSyncInputWindows(this.mNativeObject);
            return this;
        }

        public Transaction setGeometry(SurfaceControl sc, Rect sourceCrop, Rect destFrame, int orientation) {
            checkPreconditions(sc);
            SurfaceControl.nativeSetGeometry(this.mNativeObject, sc.mNativeObject, sourceCrop, destFrame, orientation);
            return this;
        }

        public Transaction setMatrix(SurfaceControl sc, float dsdx, float dtdx, float dtdy, float dsdy) {
            checkPreconditions(sc);
            SurfaceControl.nativeSetMatrix(this.mNativeObject, sc.mNativeObject, dsdx, dtdx, dtdy, dsdy);
            return this;
        }

        public Transaction setMatrix(SurfaceControl sc, Matrix matrix, float[] float9) {
            matrix.getValues(float9);
            setMatrix(sc, float9[0], float9[3], float9[1], float9[4]);
            setPosition(sc, float9[2], float9[5]);
            return this;
        }

        public Transaction setColorTransform(SurfaceControl sc, float[] matrix, float[] translation) {
            checkPreconditions(sc);
            SurfaceControl.nativeSetColorTransform(this.mNativeObject, sc.mNativeObject, matrix, translation);
            return this;
        }

        public Transaction setColorSpaceAgnostic(SurfaceControl sc, boolean agnostic) {
            checkPreconditions(sc);
            SurfaceControl.nativeSetColorSpaceAgnostic(this.mNativeObject, sc.mNativeObject, agnostic);
            return this;
        }

        public Transaction setWindowCrop(SurfaceControl sc, Rect crop) {
            checkPreconditions(sc);
            if (crop != null) {
                SurfaceControl.nativeSetWindowCrop(this.mNativeObject, sc.mNativeObject, crop.left, crop.top, crop.right, crop.bottom);
            } else {
                SurfaceControl.nativeSetWindowCrop(this.mNativeObject, sc.mNativeObject, 0, 0, 0, 0);
            }
            return this;
        }

        public Transaction setWindowCrop(SurfaceControl sc, int width, int height) {
            checkPreconditions(sc);
            SurfaceControl.nativeSetWindowCrop(this.mNativeObject, sc.mNativeObject, 0, 0, width, height);
            return this;
        }

        public Transaction setCornerRadius(SurfaceControl sc, float cornerRadius) {
            checkPreconditions(sc);
            SurfaceControl.nativeSetCornerRadius(this.mNativeObject, sc.mNativeObject, cornerRadius);
            return this;
        }

        public Transaction setBackgroundBlurRadius(SurfaceControl sc, int radius) {
            checkPreconditions(sc);
            SurfaceControl.nativeSetBackgroundBlurRadius(this.mNativeObject, sc.mNativeObject, radius);
            return this;
        }

        public Transaction setBackgroundBlurRadiusForOplus(SurfaceControl sc, int radius) {
            checkPreconditions(sc);
            SurfaceControl.nativeSetBackgroundBlurRadius(this.mNativeObject, sc.mNativeObject, radius);
            return this;
        }

        public Transaction setBlurRegions(SurfaceControl sc, float[][] regions) {
            checkPreconditions(sc);
            SurfaceControl.nativeSetBlurRegions(this.mNativeObject, sc.mNativeObject, regions, regions.length);
            return this;
        }

        public Transaction setStretchEffect(SurfaceControl sc, float width, float height, float vecX, float vecY, float maxStretchAmountX, float maxStretchAmountY, float childRelativeLeft, float childRelativeTop, float childRelativeRight, float childRelativeBottom) {
            checkPreconditions(sc);
            SurfaceControl.nativeSetStretchEffect(this.mNativeObject, sc.mNativeObject, width, height, vecX, vecY, maxStretchAmountX, maxStretchAmountY, childRelativeLeft, childRelativeTop, childRelativeRight, childRelativeBottom);
            return this;
        }

        public Transaction setLayerStack(SurfaceControl sc, int layerStack) {
            checkPreconditions(sc);
            SurfaceControl.nativeSetLayerStack(this.mNativeObject, sc.mNativeObject, layerStack);
            return this;
        }

        public Transaction reparent(SurfaceControl sc, SurfaceControl newParent) {
            checkPreconditions(sc);
            long otherObject = 0;
            if (newParent != null) {
                newParent.checkNotReleased();
                otherObject = newParent.mNativeObject;
            }
            if (SurfaceControl.DEBUG_SFC || SurfaceControl.DEBUG_ALL) {
                Log.v(SurfaceControl.TAG, "Transaction reparent sc " + sc + ",parent=" + newParent + " caller=" + Debug.getCallers(SurfaceControl.DEBUG_DEPTH));
            }
            SurfaceControl.nativeReparent(this.mNativeObject, sc.mNativeObject, otherObject);
            this.mReparentedSurfaces.put(sc, newParent);
            return this;
        }

        public Transaction setColor(SurfaceControl sc, float[] color) {
            checkPreconditions(sc);
            SurfaceControl.nativeSetColor(this.mNativeObject, sc.mNativeObject, color);
            return this;
        }

        public Transaction unsetColor(SurfaceControl sc) {
            checkPreconditions(sc);
            SurfaceControl.nativeSetColor(this.mNativeObject, sc.mNativeObject, INVALID_COLOR);
            return this;
        }

        public Transaction setSecure(SurfaceControl sc, boolean isSecure) {
            checkPreconditions(sc);
            if (isSecure) {
                SurfaceControl.nativeSetFlags(this.mNativeObject, sc.mNativeObject, 128, 128);
            } else {
                SurfaceControl.nativeSetFlags(this.mNativeObject, sc.mNativeObject, 0, 128);
            }
            return this;
        }

        public Transaction setOpaque(SurfaceControl sc, boolean isOpaque) {
            checkPreconditions(sc);
            if (isOpaque) {
                SurfaceControl.nativeSetFlags(this.mNativeObject, sc.mNativeObject, 2, 2);
            } else {
                SurfaceControl.nativeSetFlags(this.mNativeObject, sc.mNativeObject, 0, 2);
            }
            return this;
        }

        public Transaction setDisplaySurface(IBinder displayToken, Surface surface) {
            if (displayToken != null) {
                if (surface != null) {
                    synchronized (surface.mLock) {
                        SurfaceControl.nativeSetDisplaySurface(this.mNativeObject, displayToken, surface.mNativeObject);
                    }
                } else {
                    SurfaceControl.nativeSetDisplaySurface(this.mNativeObject, displayToken, 0L);
                }
                return this;
            }
            throw new IllegalArgumentException("displayToken must not be null");
        }

        public Transaction setDisplayLayerStack(IBinder displayToken, int layerStack) {
            if (displayToken != null) {
                SurfaceControl.nativeSetDisplayLayerStack(this.mNativeObject, displayToken, layerStack);
                return this;
            }
            throw new IllegalArgumentException("displayToken must not be null");
        }

        public Transaction setDisplayProjection(IBinder displayToken, int orientation, Rect layerStackRect, Rect displayRect) {
            if (displayToken == null) {
                throw new IllegalArgumentException("displayToken must not be null");
            } else if (layerStackRect == null) {
                throw new IllegalArgumentException("layerStackRect must not be null");
            } else if (displayRect != null) {
                SurfaceControl.nativeSetDisplayProjection(this.mNativeObject, displayToken, orientation, layerStackRect.left, layerStackRect.top, layerStackRect.right, layerStackRect.bottom, displayRect.left, displayRect.top, displayRect.right, displayRect.bottom);
                return this;
            } else {
                throw new IllegalArgumentException("displayRect must not be null");
            }
        }

        public Transaction setDisplaySize(IBinder displayToken, int width, int height) {
            if (displayToken == null) {
                throw new IllegalArgumentException("displayToken must not be null");
            } else if (width <= 0 || height <= 0) {
                throw new IllegalArgumentException("width and height must be positive");
            } else {
                SurfaceControl.nativeSetDisplaySize(this.mNativeObject, displayToken, width, height);
                return this;
            }
        }

        public Transaction setAnimationTransaction() {
            SurfaceControl.nativeSetAnimationTransaction(this.mNativeObject);
            return this;
        }

        public Transaction setEarlyWakeupStart() {
            SurfaceControl.nativeSetEarlyWakeupStart(this.mNativeObject);
            return this;
        }

        public Transaction setEarlyWakeupEnd() {
            SurfaceControl.nativeSetEarlyWakeupEnd(this.mNativeObject);
            return this;
        }

        public Transaction setMetadata(SurfaceControl sc, int key, int data) {
            Parcel parcel = Parcel.obtain();
            parcel.writeInt(data);
            try {
                setMetadata(sc, key, parcel);
                return this;
            } finally {
                parcel.recycle();
            }
        }

        public Transaction setMetadata(SurfaceControl sc, int key, Parcel data) {
            checkPreconditions(sc);
            SurfaceControl.nativeSetMetadata(this.mNativeObject, sc.mNativeObject, key, data);
            return this;
        }

        public Transaction setShadowRadius(SurfaceControl sc, float shadowRadius) {
            checkPreconditions(sc);
            SurfaceControl.nativeSetShadowRadius(this.mNativeObject, sc.mNativeObject, shadowRadius);
            return this;
        }

        public Transaction setFrameRate(SurfaceControl sc, float frameRate, int compatibility) {
            return setFrameRate(sc, frameRate, compatibility, 0);
        }

        public Transaction setFrameRate(SurfaceControl sc, float frameRate, int compatibility, int changeFrameRateStrategy) {
            checkPreconditions(sc);
            SurfaceControl.nativeSetFrameRate(this.mNativeObject, sc.mNativeObject, frameRate, compatibility, changeFrameRateStrategy);
            return this;
        }

        public Transaction setFocusedWindow(IBinder token, String windowName, int displayId) {
            SurfaceControl.nativeSetFocusedWindow(this.mNativeObject, token, windowName, null, null, displayId);
            return this;
        }

        public Transaction requestFocusTransfer(IBinder token, String windowName, IBinder focusedToken, String focusedWindowName, int displayId) {
            SurfaceControl.nativeSetFocusedWindow(this.mNativeObject, token, windowName, focusedToken, focusedWindowName, displayId);
            return this;
        }

        public Transaction setSkipScreenshot(SurfaceControl sc, boolean skipScrenshot) {
            checkPreconditions(sc);
            if (skipScrenshot) {
                SurfaceControl.nativeSetFlags(this.mNativeObject, sc.mNativeObject, 64, 64);
            } else {
                SurfaceControl.nativeSetFlags(this.mNativeObject, sc.mNativeObject, 0, 64);
            }
            return this;
        }

        public Transaction setBuffer(SurfaceControl sc, GraphicBuffer buffer) {
            checkPreconditions(sc);
            SurfaceControl.nativeSetBuffer(this.mNativeObject, sc.mNativeObject, buffer);
            return this;
        }

        public Transaction setColorSpace(SurfaceControl sc, ColorSpace colorSpace) {
            checkPreconditions(sc);
            SurfaceControl.nativeSetColorSpace(this.mNativeObject, sc.mNativeObject, colorSpace.getId());
            return this;
        }

        public Transaction setTrustedOverlay(SurfaceControl sc, boolean isTrustedOverlay) {
            checkPreconditions(sc);
            SurfaceControl.nativeSetTrustedOverlay(this.mNativeObject, sc.mNativeObject, isTrustedOverlay);
            return this;
        }

        public Transaction merge(Transaction other) {
            if (this == other) {
                return this;
            }
            this.mResizedSurfaces.putAll((ArrayMap<? extends SurfaceControl, ? extends Point>) other.mResizedSurfaces);
            other.mResizedSurfaces.clear();
            this.mReparentedSurfaces.putAll((ArrayMap<? extends SurfaceControl, ? extends SurfaceControl>) other.mReparentedSurfaces);
            other.mReparentedSurfaces.clear();
            SurfaceControl.nativeMergeTransaction(this.mNativeObject, other.mNativeObject);
            return this;
        }

        public Transaction remove(SurfaceControl sc) {
            if (SurfaceControl.DEBUG_SFC || SurfaceControl.DEBUG_ALL) {
                Log.v(SurfaceControl.TAG, "SurfaceControl remove in transaction: sc " + sc + " caller=" + Debug.getCallers(SurfaceControl.DEBUG_DEPTH));
            }
            reparent(sc, null);
            sc.release();
            return this;
        }

        public Transaction setFrameTimelineVsync(long frameTimelineVsyncId) {
            SurfaceControl.nativeSetFrameTimelineVsync(this.mNativeObject, frameTimelineVsyncId);
            return this;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            if (this.mNativeObject == 0) {
                dest.writeInt(0);
                return;
            }
            dest.writeInt(1);
            SurfaceControl.nativeWriteTransactionToParcel(this.mNativeObject, dest);
            if ((flags & 1) != 0) {
                SurfaceControl.nativeClearTransaction(this.mNativeObject);
            }
        }

        private void readFromParcel(Parcel in) {
            this.mNativeObject = 0L;
            if (in.readInt() != 0) {
                long nativeReadTransactionFromParcel = SurfaceControl.nativeReadTransactionFromParcel(in);
                this.mNativeObject = nativeReadTransactionFromParcel;
                this.mFreeNativeResources = sRegistry.registerNativeAllocation(this, nativeReadTransactionFromParcel);
            }
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }
    }

    /* loaded from: classes3.dex */
    public static class LockDebuggingTransaction extends Transaction {
        Object mMonitor;

        public LockDebuggingTransaction(Object o) {
            this.mMonitor = o;
        }

        @Override // android.view.SurfaceControl.Transaction
        protected void checkPreconditions(SurfaceControl sc) {
            super.checkPreconditions(sc);
            if (!Thread.holdsLock(this.mMonitor)) {
                throw new RuntimeException("Unlocked access to synchronized SurfaceControl.Transaction");
            }
        }
    }

    /* loaded from: classes3.dex */
    private static class GlobalTransactionWrapper extends Transaction {
        private GlobalTransactionWrapper() {
        }

        void applyGlobalTransaction(boolean sync) {
            applyResizedSurfaces();
            notifyReparentedSurfaces();
            SurfaceControl.nativeApplyTransaction(this.mNativeObject, sync);
        }

        @Override // android.view.SurfaceControl.Transaction
        public void apply(boolean sync) {
            throw new RuntimeException("Global transaction must be applied from closeTransaction");
        }
    }

    public static long acquireFrameRateFlexibilityToken() {
        return nativeAcquireFrameRateFlexibilityToken();
    }

    public static void releaseFrameRateFlexibilityToken(long token) {
        nativeReleaseFrameRateFlexibilityToken(token);
    }

    public static Transaction getGlobalTransaction() {
        return sGlobalTransaction;
    }

    public void resize(int w, int h) {
        this.mWidth = w;
        this.mHeight = h;
        nativeUpdateDefaultBufferSize(this.mNativeObject, w, h);
    }

    public int getTransformHint() {
        checkNotReleased();
        return nativeGetTransformHint(this.mNativeObject);
    }

    public void setTransformHint(int transformHint) {
        nativeSetTransformHint(this.mNativeObject, transformHint);
    }

    /* loaded from: classes3.dex */
    public class ScreenshotGraphicBuffer {
        public ScreenshotGraphicBuffer() {
        }
    }
}
