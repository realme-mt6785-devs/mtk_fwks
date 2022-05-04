package android.media;

import android.graphics.GraphicBuffer;
import android.graphics.ImageFormat;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.hardware.HardwareBuffer;
import android.hardware.camera2.utils.SurfaceUtils;
import android.media.Image;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Size;
import android.view.Surface;
import dalvik.system.VMRuntime;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.NioUtils;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes2.dex */
public class ImageWriter implements AutoCloseable {
    private int mEstimatedNativeAllocBytes;
    private OnImageReleasedListener mListener;
    private ListenerHandler mListenerHandler;
    private final int mMaxImages;
    private long mNativeContext;
    private int mWriterFormat;
    private final Object mListenerLock = new Object();
    private List<Image> mDequeuedImages = new CopyOnWriteArrayList();

    /* loaded from: classes2.dex */
    public interface OnImageReleasedListener {
        void onImageReleased(ImageWriter imageWriter);
    }

    private native synchronized void cancelImage(long j, Image image);

    private native synchronized int nativeAttachAndQueueGraphicBuffer(long j, GraphicBuffer graphicBuffer, int i, long j2, int i2, int i3, int i4, int i5, int i6, int i7);

    private native synchronized int nativeAttachAndQueueImage(long j, long j2, int i, long j3, int i2, int i3, int i4, int i5, int i6, int i7);

    private static native void nativeClassInit();

    private native synchronized void nativeClose(long j);

    private native synchronized void nativeDequeueInputImage(long j, Image image);

    private native synchronized long nativeInit(Object obj, Surface surface, int i, int i2, int i3, int i4);

    private native synchronized void nativeQueueInputImage(long j, Image image, long j2, int i, int i2, int i3, int i4, int i5, int i6);

    public static ImageWriter newInstance(Surface surface, int maxImages) {
        return new ImageWriter(surface, maxImages, 0, -1, -1);
    }

    public static ImageWriter newInstance(Surface surface, int maxImages, int format, int width, int height) {
        if (ImageFormat.isPublicFormat(format) || PixelFormat.isPublicFormat(format)) {
            return new ImageWriter(surface, maxImages, format, width, height);
        }
        throw new IllegalArgumentException("Invalid format is specified: " + format);
    }

    public static ImageWriter newInstance(Surface surface, int maxImages, int format) {
        if (ImageFormat.isPublicFormat(format) || PixelFormat.isPublicFormat(format)) {
            return new ImageWriter(surface, maxImages, format, -1, -1);
        }
        throw new IllegalArgumentException("Invalid format is specified: " + format);
    }

    protected ImageWriter(Surface surface, int maxImages, int format, int width, int height) {
        if (surface == null || maxImages < 1) {
            throw new IllegalArgumentException("Illegal input argument: surface " + surface + ", maxImages: " + maxImages);
        }
        this.mMaxImages = maxImages;
        this.mNativeContext = nativeInit(new WeakReference(this), surface, maxImages, format, width, height);
        format = format == 0 ? SurfaceUtils.getSurfaceFormat(surface) : format;
        if (format == 33) {
            int surfaceDataspace = SurfaceUtils.getSurfaceDataspace(surface);
            switch (surfaceDataspace) {
                case 4096:
                    format = 257;
                    break;
                case 4097:
                default:
                    format = 256;
                    break;
                case 4098:
                    format = ImageFormat.DEPTH_JPEG;
                    break;
                case 4099:
                    format = ImageFormat.HEIC;
                    break;
            }
        }
        Size surfSize = SurfaceUtils.getSurfaceSize(surface);
        this.mEstimatedNativeAllocBytes = ImageUtils.getEstimatedNativeAllocBytes(surfSize.getWidth(), surfSize.getHeight(), format, 1);
        VMRuntime.getRuntime().registerNativeAllocation(this.mEstimatedNativeAllocBytes);
    }

    public int getMaxImages() {
        return this.mMaxImages;
    }

    public Image dequeueInputImage() {
        if (this.mDequeuedImages.size() < this.mMaxImages) {
            WriterSurfaceImage newImage = new WriterSurfaceImage(this);
            nativeDequeueInputImage(this.mNativeContext, newImage);
            this.mDequeuedImages.add(newImage);
            newImage.mIsImageValid = true;
            return newImage;
        }
        throw new IllegalStateException("Already dequeued max number of Images " + this.mMaxImages);
    }

    public void queueInputImage(Image image) {
        if (image != null) {
            boolean ownedByMe = isImageOwnedByMe(image);
            if (ownedByMe && !((WriterSurfaceImage) image).mIsImageValid) {
                throw new IllegalStateException("Image from ImageWriter is invalid");
            } else if (!ownedByMe) {
                if (image.getOwner() instanceof ImageReader) {
                    ImageReader prevOwner = (ImageReader) image.getOwner();
                    prevOwner.detachImage(image);
                } else if (image.getOwner() != null) {
                    throw new IllegalArgumentException("Only images from ImageReader can be queued to ImageWriter, other image source is not supported yet!");
                }
                attachAndQueueInputImage(image);
                image.close();
            } else {
                Rect crop = image.getCropRect();
                nativeQueueInputImage(this.mNativeContext, image, image.getTimestamp(), crop.left, crop.top, crop.right, crop.bottom, image.getTransform(), image.getScalingMode());
                if (ownedByMe) {
                    this.mDequeuedImages.remove(image);
                    WriterSurfaceImage wi = (WriterSurfaceImage) image;
                    wi.clearSurfacePlanes();
                    wi.mIsImageValid = false;
                }
            }
        } else {
            throw new IllegalArgumentException("image shouldn't be null");
        }
    }

    public int getFormat() {
        return this.mWriterFormat;
    }

    public void setOnImageReleasedListener(OnImageReleasedListener listener, Handler handler) {
        synchronized (this.mListenerLock) {
            if (listener != null) {
                Looper looper = handler != null ? handler.getLooper() : Looper.myLooper();
                if (looper != null) {
                    ListenerHandler listenerHandler = this.mListenerHandler;
                    if (listenerHandler == null || listenerHandler.getLooper() != looper) {
                        this.mListenerHandler = new ListenerHandler(looper);
                    }
                    this.mListener = listener;
                } else {
                    throw new IllegalArgumentException("handler is null but the current thread is not a looper");
                }
            } else {
                this.mListener = null;
                this.mListenerHandler = null;
            }
        }
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        setOnImageReleasedListener(null, null);
        for (Image image : this.mDequeuedImages) {
            image.close();
        }
        this.mDequeuedImages.clear();
        nativeClose(this.mNativeContext);
        this.mNativeContext = 0L;
        if (this.mEstimatedNativeAllocBytes > 0) {
            VMRuntime.getRuntime().registerNativeFree(this.mEstimatedNativeAllocBytes);
            this.mEstimatedNativeAllocBytes = 0;
        }
    }

    protected void finalize() throws Throwable {
        try {
            close();
        } finally {
            super.finalize();
        }
    }

    private void attachAndQueueInputImage(Image image) {
        if (image == null) {
            throw new IllegalArgumentException("image shouldn't be null");
        } else if (isImageOwnedByMe(image)) {
            throw new IllegalArgumentException("Can not attach an image that is owned ImageWriter already");
        } else if (image.isAttachable()) {
            Rect crop = image.getCropRect();
            if (image.getNativeContext() != 0) {
                nativeAttachAndQueueImage(this.mNativeContext, image.getNativeContext(), image.getFormat(), image.getTimestamp(), crop.left, crop.top, crop.right, crop.bottom, image.getTransform(), image.getScalingMode());
                return;
            }
            GraphicBuffer gb = GraphicBuffer.createFromHardwareBuffer(image.getHardwareBuffer());
            nativeAttachAndQueueGraphicBuffer(this.mNativeContext, gb, image.getFormat(), image.getTimestamp(), crop.left, crop.top, crop.right, crop.bottom, image.getTransform(), image.getScalingMode());
            gb.destroy();
            image.close();
        } else {
            throw new IllegalStateException("Image was not detached from last owner, or image  is not detachable");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public final class ListenerHandler extends Handler {
        public ListenerHandler(Looper looper) {
            super(looper, null, true);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            OnImageReleasedListener listener;
            synchronized (ImageWriter.this.mListenerLock) {
                listener = ImageWriter.this.mListener;
            }
            if (listener != null) {
                listener.onImageReleased(ImageWriter.this);
            }
        }
    }

    private static void postEventFromNative(Object selfRef) {
        Handler handler;
        WeakReference<ImageWriter> weakSelf = (WeakReference) selfRef;
        ImageWriter iw = weakSelf.get();
        if (iw != null) {
            synchronized (iw.mListenerLock) {
                handler = iw.mListenerHandler;
            }
            if (handler != null) {
                handler.sendEmptyMessage(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void abortImage(Image image) {
        if (image == null) {
            throw new IllegalArgumentException("image shouldn't be null");
        } else if (this.mDequeuedImages.contains(image)) {
            WriterSurfaceImage wi = (WriterSurfaceImage) image;
            if (wi.mIsImageValid) {
                cancelImage(this.mNativeContext, image);
                this.mDequeuedImages.remove(image);
                wi.clearSurfacePlanes();
                wi.mIsImageValid = false;
            }
        } else {
            throw new IllegalStateException("It is illegal to abort some image that is not dequeued yet");
        }
    }

    private boolean isImageOwnedByMe(Image image) {
        if (!(image instanceof WriterSurfaceImage)) {
            return false;
        }
        WriterSurfaceImage wi = (WriterSurfaceImage) image;
        return wi.getOwner() == this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class WriterSurfaceImage extends Image {
        private long mNativeBuffer;
        private ImageWriter mOwner;
        private SurfacePlane[] mPlanes;
        private int mNativeFenceFd = -1;
        private int mHeight = -1;
        private int mWidth = -1;
        private int mFormat = -1;
        private final long DEFAULT_TIMESTAMP = Long.MIN_VALUE;
        private long mTimestamp = Long.MIN_VALUE;
        private int mTransform = 0;
        private int mScalingMode = 0;

        private native synchronized SurfacePlane[] nativeCreatePlanes(int i, int i2);

        private native synchronized int nativeGetFormat();

        private native synchronized HardwareBuffer nativeGetHardwareBuffer();

        private native synchronized int nativeGetHeight();

        private native synchronized int nativeGetWidth();

        public WriterSurfaceImage(ImageWriter writer) {
            this.mOwner = writer;
        }

        @Override // android.media.Image
        public int getFormat() {
            throwISEIfImageIsInvalid();
            if (this.mFormat == -1) {
                this.mFormat = nativeGetFormat();
            }
            return this.mFormat;
        }

        @Override // android.media.Image
        public int getWidth() {
            throwISEIfImageIsInvalid();
            if (this.mWidth == -1) {
                this.mWidth = nativeGetWidth();
            }
            return this.mWidth;
        }

        @Override // android.media.Image
        public int getHeight() {
            throwISEIfImageIsInvalid();
            if (this.mHeight == -1) {
                this.mHeight = nativeGetHeight();
            }
            return this.mHeight;
        }

        @Override // android.media.Image
        public int getTransform() {
            throwISEIfImageIsInvalid();
            return this.mTransform;
        }

        @Override // android.media.Image
        public int getScalingMode() {
            throwISEIfImageIsInvalid();
            return this.mScalingMode;
        }

        @Override // android.media.Image
        public long getTimestamp() {
            throwISEIfImageIsInvalid();
            return this.mTimestamp;
        }

        @Override // android.media.Image
        public void setTimestamp(long timestamp) {
            throwISEIfImageIsInvalid();
            this.mTimestamp = timestamp;
        }

        @Override // android.media.Image
        public HardwareBuffer getHardwareBuffer() {
            throwISEIfImageIsInvalid();
            return nativeGetHardwareBuffer();
        }

        @Override // android.media.Image
        public Image.Plane[] getPlanes() {
            throwISEIfImageIsInvalid();
            if (this.mPlanes == null) {
                int numPlanes = ImageUtils.getNumPlanesForFormat(getFormat());
                this.mPlanes = nativeCreatePlanes(numPlanes, getOwner().getFormat());
            }
            return (Image.Plane[]) this.mPlanes.clone();
        }

        @Override // android.media.Image
        public boolean isAttachable() {
            throwISEIfImageIsInvalid();
            return false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // android.media.Image
        public ImageWriter getOwner() {
            throwISEIfImageIsInvalid();
            return this.mOwner;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // android.media.Image
        public long getNativeContext() {
            throwISEIfImageIsInvalid();
            return this.mNativeBuffer;
        }

        @Override // android.media.Image, java.lang.AutoCloseable
        public void close() {
            if (this.mIsImageValid) {
                getOwner().abortImage(this);
            }
        }

        protected final void finalize() throws Throwable {
            try {
                close();
            } finally {
                super.finalize();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSurfacePlanes() {
            if (this.mIsImageValid && this.mPlanes != null) {
                int i = 0;
                while (true) {
                    SurfacePlane[] surfacePlaneArr = this.mPlanes;
                    if (i < surfacePlaneArr.length) {
                        if (surfacePlaneArr[i] != null) {
                            surfacePlaneArr[i].clearBuffer();
                            this.mPlanes[i] = null;
                        }
                        i++;
                    } else {
                        return;
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public class SurfacePlane extends Image.Plane {
            private ByteBuffer mBuffer;
            private final int mPixelStride;
            private final int mRowStride;

            private SurfacePlane(int rowStride, int pixelStride, ByteBuffer buffer) {
                this.mRowStride = rowStride;
                this.mPixelStride = pixelStride;
                this.mBuffer = buffer;
                buffer.order(ByteOrder.nativeOrder());
            }

            @Override // android.media.Image.Plane
            public int getRowStride() {
                WriterSurfaceImage.this.throwISEIfImageIsInvalid();
                return this.mRowStride;
            }

            @Override // android.media.Image.Plane
            public int getPixelStride() {
                WriterSurfaceImage.this.throwISEIfImageIsInvalid();
                return this.mPixelStride;
            }

            @Override // android.media.Image.Plane
            public ByteBuffer getBuffer() {
                WriterSurfaceImage.this.throwISEIfImageIsInvalid();
                return this.mBuffer;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearBuffer() {
                ByteBuffer byteBuffer = this.mBuffer;
                if (byteBuffer != null) {
                    if (byteBuffer.isDirect()) {
                        NioUtils.freeDirectBuffer(this.mBuffer);
                    }
                    this.mBuffer = null;
                }
            }
        }
    }

    static {
        System.loadLibrary("media_jni");
        nativeClassInit();
    }
}
