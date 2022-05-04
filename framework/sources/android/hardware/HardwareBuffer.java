package android.hardware;

import android.graphics.GraphicBuffer;
import android.os.Parcel;
import android.os.Parcelable;
import dalvik.annotation.optimization.CriticalNative;
import dalvik.annotation.optimization.FastNative;
import dalvik.system.CloseGuard;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import libcore.util.NativeAllocationRegistry;
/* loaded from: classes.dex */
public final class HardwareBuffer implements Parcelable, AutoCloseable {
    public static final int BLOB = 33;
    public static final Parcelable.Creator<HardwareBuffer> CREATOR = new Parcelable.Creator<HardwareBuffer>() { // from class: android.hardware.HardwareBuffer.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HardwareBuffer createFromParcel(Parcel in) {
            long nativeObject = HardwareBuffer.nReadHardwareBufferFromParcel(in);
            if (nativeObject != 0) {
                return new HardwareBuffer(nativeObject);
            }
            return null;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HardwareBuffer[] newArray(int size) {
            return new HardwareBuffer[size];
        }
    };
    public static final int DS_24UI8 = 50;
    public static final int DS_FP32UI8 = 52;
    public static final int D_16 = 48;
    public static final int D_24 = 49;
    public static final int D_FP32 = 51;
    public static final int RGBA_1010102 = 43;
    public static final int RGBA_8888 = 1;
    public static final int RGBA_FP16 = 22;
    public static final int RGBX_8888 = 2;
    public static final int RGB_565 = 4;
    public static final int RGB_888 = 3;
    public static final int S_UI8 = 53;
    public static final long USAGE_CPU_READ_OFTEN = 3;
    public static final long USAGE_CPU_READ_RARELY = 2;
    public static final long USAGE_CPU_WRITE_OFTEN = 48;
    public static final long USAGE_CPU_WRITE_RARELY = 32;
    public static final long USAGE_GPU_COLOR_OUTPUT = 512;
    public static final long USAGE_GPU_CUBE_MAP = 33554432;
    public static final long USAGE_GPU_DATA_BUFFER = 16777216;
    public static final long USAGE_GPU_MIPMAP_COMPLETE = 67108864;
    public static final long USAGE_GPU_SAMPLED_IMAGE = 256;
    public static final long USAGE_PROTECTED_CONTENT = 16384;
    public static final long USAGE_SENSOR_DIRECT_DATA = 8388608;
    public static final long USAGE_VIDEO_ENCODE = 65536;
    public static final int YCBCR_420_888 = 35;
    private Runnable mCleaner;
    private final CloseGuard mCloseGuard;
    private long mNativeObject;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Format {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Usage {
    }

    private static native long nCreateFromGraphicBuffer(GraphicBuffer graphicBuffer);

    private static native long nCreateHardwareBuffer(int i, int i2, int i3, int i4, long j);

    @CriticalNative
    private static native long nEstimateSize(long j);

    @FastNative
    private static native int nGetFormat(long j);

    @FastNative
    private static native int nGetHeight(long j);

    @FastNative
    private static native int nGetLayers(long j);

    private static native long nGetNativeFinalizer();

    @FastNative
    private static native long nGetUsage(long j);

    @FastNative
    private static native int nGetWidth(long j);

    private static native boolean nIsSupported(int i, int i2, int i3, int i4, long j);

    /* JADX INFO: Access modifiers changed from: private */
    public static native long nReadHardwareBufferFromParcel(Parcel parcel);

    private static native void nWriteHardwareBufferToParcel(long j, Parcel parcel);

    public static HardwareBuffer create(int width, int height, int format, int layers, long usage) {
        if (!isSupportedFormat(format)) {
            throw new IllegalArgumentException("Invalid pixel format " + format);
        } else if (width <= 0) {
            throw new IllegalArgumentException("Invalid width " + width);
        } else if (height <= 0) {
            throw new IllegalArgumentException("Invalid height " + height);
        } else if (layers <= 0) {
            throw new IllegalArgumentException("Invalid layer count " + layers);
        } else if (format != 33 || height == 1) {
            long nativeObject = nCreateHardwareBuffer(width, height, format, layers, usage);
            if (nativeObject != 0) {
                return new HardwareBuffer(nativeObject);
            }
            throw new IllegalArgumentException("Unable to create a HardwareBuffer, either the dimensions passed were too large, too many image layers were requested, or an invalid set of usage flags or invalid format was passed");
        } else {
            throw new IllegalArgumentException("Height must be 1 when using the BLOB format");
        }
    }

    public static boolean isSupported(int width, int height, int format, int layers, long usage) {
        if (!isSupportedFormat(format)) {
            throw new IllegalArgumentException("Invalid pixel format " + format);
        } else if (width <= 0) {
            throw new IllegalArgumentException("Invalid width " + width);
        } else if (height <= 0) {
            throw new IllegalArgumentException("Invalid height " + height);
        } else if (layers <= 0) {
            throw new IllegalArgumentException("Invalid layer count " + layers);
        } else if (format != 33 || height == 1) {
            return nIsSupported(width, height, format, layers, usage);
        } else {
            throw new IllegalArgumentException("Height must be 1 when using the BLOB format");
        }
    }

    public static HardwareBuffer createFromGraphicBuffer(GraphicBuffer graphicBuffer) {
        long nativeObject = nCreateFromGraphicBuffer(graphicBuffer);
        return new HardwareBuffer(nativeObject);
    }

    private HardwareBuffer(long nativeObject) {
        CloseGuard closeGuard = CloseGuard.get();
        this.mCloseGuard = closeGuard;
        this.mNativeObject = nativeObject;
        nEstimateSize(nativeObject);
        ClassLoader loader = HardwareBuffer.class.getClassLoader();
        NativeAllocationRegistry registry = new NativeAllocationRegistry(loader, nGetNativeFinalizer(), 232L);
        this.mCleaner = registry.registerNativeAllocation(this, this.mNativeObject);
        closeGuard.open("close");
    }

    protected void finalize() throws Throwable {
        try {
            this.mCloseGuard.warnIfOpen();
            close();
        } finally {
            super.finalize();
        }
    }

    public int getWidth() {
        if (!isClosed()) {
            return nGetWidth(this.mNativeObject);
        }
        throw new IllegalStateException("This HardwareBuffer has been closed and its width cannot be obtained.");
    }

    public int getHeight() {
        if (!isClosed()) {
            return nGetHeight(this.mNativeObject);
        }
        throw new IllegalStateException("This HardwareBuffer has been closed and its height cannot be obtained.");
    }

    public int getFormat() {
        if (!isClosed()) {
            return nGetFormat(this.mNativeObject);
        }
        throw new IllegalStateException("This HardwareBuffer has been closed and its format cannot be obtained.");
    }

    public int getLayers() {
        if (!isClosed()) {
            return nGetLayers(this.mNativeObject);
        }
        throw new IllegalStateException("This HardwareBuffer has been closed and its layer count cannot be obtained.");
    }

    public long getUsage() {
        if (!isClosed()) {
            return nGetUsage(this.mNativeObject);
        }
        throw new IllegalStateException("This HardwareBuffer has been closed and its usage cannot be obtained.");
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        if (!isClosed()) {
            this.mCloseGuard.close();
            this.mNativeObject = 0L;
            this.mCleaner.run();
            this.mCleaner = null;
        }
    }

    public boolean isClosed() {
        return this.mNativeObject == 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 1;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        if (!isClosed()) {
            nWriteHardwareBufferToParcel(this.mNativeObject, dest);
            return;
        }
        throw new IllegalStateException("This HardwareBuffer has been closed and cannot be written to a parcel.");
    }

    private static boolean isSupportedFormat(int format) {
        switch (format) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 22:
            case 33:
            case 35:
            case 43:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
                return true;
            default:
                return false;
        }
    }
}
