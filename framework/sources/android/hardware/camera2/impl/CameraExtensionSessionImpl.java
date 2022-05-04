package android.hardware.camera2.impl;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraExtensionCharacteristics;
import android.hardware.camera2.CameraExtensionSession;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.extension.CaptureBundle;
import android.hardware.camera2.extension.CaptureStageImpl;
import android.hardware.camera2.extension.ICaptureProcessorImpl;
import android.hardware.camera2.extension.IImageCaptureExtenderImpl;
import android.hardware.camera2.extension.IInitializeSessionCallback;
import android.hardware.camera2.extension.IPreviewExtenderImpl;
import android.hardware.camera2.extension.IRequestUpdateProcessorImpl;
import android.hardware.camera2.extension.ParcelImage;
import android.hardware.camera2.impl.CameraExtensionSessionImpl;
import android.hardware.camera2.impl.CameraExtensionUtils;
import android.hardware.camera2.params.ExtensionSessionConfiguration;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.params.SessionConfiguration;
import android.hardware.camera2.utils.SurfaceUtils;
import android.media.Image;
import android.media.ImageReader;
import android.media.ImageWriter;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.Pair;
import android.util.Size;
import android.view.Surface;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public final class CameraExtensionSessionImpl extends CameraExtensionSession {
    private static final int PREVIEW_QUEUE_SIZE = 3;
    private static final String TAG = "CameraExtensionSessionImpl";
    private final CameraExtensionSession.StateCallback mCallbacks;
    private Surface mCameraBurstSurface;
    private final CameraDevice mCameraDevice;
    private Surface mCameraRepeatingSurface;
    private Surface mClientCaptureSurface;
    private Surface mClientRepeatingRequestSurface;
    private final Executor mExecutor;
    private final long mExtensionClientId;
    private final Handler mHandler;
    private final HandlerThread mHandlerThread;
    private final IImageCaptureExtenderImpl mImageExtender;
    private final IPreviewExtenderImpl mPreviewExtender;
    private final List<Size> mSupportedPreviewSizes;
    private CameraCaptureSession mCaptureSession = null;
    private ImageReader mRepeatingRequestImageReader = null;
    private ImageReader mBurstCaptureImageReader = null;
    private ImageReader mStubCaptureImageReader = null;
    private ImageWriter mRepeatingRequestImageWriter = null;
    private CameraOutputImageCallback mRepeatingRequestImageCallback = null;
    private CameraOutputImageCallback mBurstCaptureImageCallback = null;
    private CameraExtensionJpegProcessor mImageJpegProcessor = null;
    private ICaptureProcessorImpl mImageProcessor = null;
    private CameraExtensionForwardProcessor mPreviewImageProcessor = null;
    private IRequestUpdateProcessorImpl mPreviewRequestUpdateProcessor = null;
    private int mPreviewProcessorType = 2;
    private boolean mInternalRepeatingRequestEnabled = true;
    final Object mInterfaceLock = new Object();
    private boolean mInitialized = false;
    private final InitializeSessionHandler mInitializeHandler = new InitializeSessionHandler();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface OnImageAvailableListener {
        void onImageAvailable(ImageReader imageReader, Image image);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int nativeGetSurfaceFormat(Surface surface) {
        return SurfaceUtils.getSurfaceFormat(surface);
    }

    public static CameraExtensionSessionImpl createCameraExtensionSession(CameraDevice cameraDevice, Context ctx, ExtensionSessionConfiguration config) throws CameraAccessException, RemoteException {
        int[] iArr;
        int suitableSurfaceCount;
        long clientId = CameraExtensionCharacteristics.registerClient(ctx);
        if (clientId >= 0) {
            String cameraId = cameraDevice.getId();
            CameraManager manager = (CameraManager) ctx.getSystemService(CameraManager.class);
            CameraCharacteristics chars = manager.getCameraCharacteristics(cameraId);
            CameraExtensionCharacteristics extensionChars = new CameraExtensionCharacteristics(ctx, cameraId, chars);
            if (CameraExtensionCharacteristics.isExtensionSupported(cameraDevice.getId(), config.getExtension(), chars)) {
                if (!config.getOutputConfigurations().isEmpty() && config.getOutputConfigurations().size() <= 2) {
                    Pair<IPreviewExtenderImpl, IImageCaptureExtenderImpl> extenders = CameraExtensionCharacteristics.initializeExtension(config.getExtension());
                    int suitableSurfaceCount2 = 0;
                    List<Size> supportedPreviewSizes = extensionChars.getExtensionSupportedSizes(config.getExtension(), SurfaceTexture.class);
                    Surface repeatingRequestSurface = CameraExtensionUtils.getRepeatingRequestSurface(config.getOutputConfigurations(), supportedPreviewSizes);
                    if (repeatingRequestSurface != null) {
                        suitableSurfaceCount2 = 0 + 1;
                    }
                    HashMap<Integer, List<Size>> supportedCaptureSizes = new HashMap<>();
                    for (int format : CameraExtensionUtils.SUPPORTED_CAPTURE_OUTPUT_FORMATS) {
                        List<Size> supportedSizes = extensionChars.getExtensionSupportedSizes(config.getExtension(), format);
                        if (supportedSizes != null) {
                            supportedCaptureSizes.put(Integer.valueOf(format), supportedSizes);
                        }
                    }
                    Surface burstCaptureSurface = CameraExtensionUtils.getBurstCaptureSurface(config.getOutputConfigurations(), supportedCaptureSizes);
                    if (burstCaptureSurface != null) {
                        suitableSurfaceCount = suitableSurfaceCount2 + 1;
                    } else {
                        suitableSurfaceCount = suitableSurfaceCount2;
                    }
                    if (suitableSurfaceCount == config.getOutputConfigurations().size()) {
                        extenders.first.init(cameraId, chars.getNativeMetadata());
                        extenders.first.onInit(cameraId, chars.getNativeMetadata());
                        extenders.second.init(cameraId, chars.getNativeMetadata());
                        extenders.second.onInit(cameraId, chars.getNativeMetadata());
                        CameraExtensionSessionImpl session = new CameraExtensionSessionImpl(extenders.second, extenders.first, supportedPreviewSizes, clientId, cameraDevice, repeatingRequestSurface, burstCaptureSurface, config.getStateCallback(), config.getExecutor());
                        session.initialize();
                        return session;
                    }
                    throw new IllegalArgumentException("One or more unsupported output surfaces found!");
                }
                throw new IllegalArgumentException("Unexpected amount of output surfaces, received: " + config.getOutputConfigurations().size() + " expected <= 2");
            }
            throw new UnsupportedOperationException("Unsupported extension type: " + config.getExtension());
        }
        throw new UnsupportedOperationException("Unsupported extension!");
    }

    public CameraExtensionSessionImpl(IImageCaptureExtenderImpl imageExtender, IPreviewExtenderImpl previewExtender, List<Size> previewSizes, long extensionClientId, CameraDevice cameraDevice, Surface repeatingRequestSurface, Surface burstCaptureSurface, CameraExtensionSession.StateCallback callback, Executor executor) {
        this.mExtensionClientId = extensionClientId;
        this.mImageExtender = imageExtender;
        this.mPreviewExtender = previewExtender;
        this.mCameraDevice = cameraDevice;
        this.mCallbacks = callback;
        this.mExecutor = executor;
        this.mClientRepeatingRequestSurface = repeatingRequestSurface;
        this.mClientCaptureSurface = burstCaptureSurface;
        this.mSupportedPreviewSizes = previewSizes;
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper());
    }

    private void initializeRepeatingRequestPipeline() throws RemoteException {
        CameraExtensionUtils.SurfaceInfo repeatingSurfaceInfo = new CameraExtensionUtils.SurfaceInfo();
        this.mPreviewProcessorType = this.mPreviewExtender.getProcessorType();
        Surface surface = this.mClientRepeatingRequestSurface;
        if (surface != null) {
            repeatingSurfaceInfo = CameraExtensionUtils.querySurface(surface);
        } else {
            CameraExtensionUtils.SurfaceInfo captureSurfaceInfo = CameraExtensionUtils.querySurface(this.mClientCaptureSurface);
            Size captureSize = new Size(captureSurfaceInfo.mWidth, captureSurfaceInfo.mHeight);
            Size previewSize = findSmallestAspectMatchedSize(this.mSupportedPreviewSizes, captureSize);
            repeatingSurfaceInfo.mWidth = previewSize.getWidth();
            repeatingSurfaceInfo.mHeight = previewSize.getHeight();
            repeatingSurfaceInfo.mUsage = 256L;
        }
        int i = this.mPreviewProcessorType;
        if (i == 1) {
            try {
                CameraExtensionForwardProcessor cameraExtensionForwardProcessor = new CameraExtensionForwardProcessor(this.mPreviewExtender.getPreviewImageProcessor(), repeatingSurfaceInfo.mFormat, repeatingSurfaceInfo.mUsage, this.mHandler);
                this.mPreviewImageProcessor = cameraExtensionForwardProcessor;
                cameraExtensionForwardProcessor.onImageFormatUpdate(35);
                this.mPreviewImageProcessor.onResolutionUpdate(new Size(repeatingSurfaceInfo.mWidth, repeatingSurfaceInfo.mHeight));
                this.mPreviewImageProcessor.onOutputSurface(null, -1);
                ImageReader newInstance = ImageReader.newInstance(repeatingSurfaceInfo.mWidth, repeatingSurfaceInfo.mHeight, 35, 3, repeatingSurfaceInfo.mUsage);
                this.mRepeatingRequestImageReader = newInstance;
                this.mCameraRepeatingSurface = newInstance.getSurface();
            } catch (ClassCastException e) {
                throw new UnsupportedOperationException("Failed casting preview processor!");
            }
        } else if (i == 0) {
            try {
                this.mPreviewRequestUpdateProcessor = this.mPreviewExtender.getRequestUpdateProcessor();
                ImageReader newInstance2 = ImageReader.newInstance(repeatingSurfaceInfo.mWidth, repeatingSurfaceInfo.mHeight, 34, 3, repeatingSurfaceInfo.mUsage);
                this.mRepeatingRequestImageReader = newInstance2;
                this.mCameraRepeatingSurface = newInstance2.getSurface();
                android.hardware.camera2.extension.Size sz = new android.hardware.camera2.extension.Size();
                sz.width = repeatingSurfaceInfo.mWidth;
                sz.height = repeatingSurfaceInfo.mHeight;
                this.mPreviewRequestUpdateProcessor.onResolutionUpdate(sz);
                this.mPreviewRequestUpdateProcessor.onImageFormatUpdate(34);
            } catch (ClassCastException e2) {
                throw new UnsupportedOperationException("Failed casting preview processor!");
            }
        } else {
            ImageReader newInstance3 = ImageReader.newInstance(repeatingSurfaceInfo.mWidth, repeatingSurfaceInfo.mHeight, 34, 3, repeatingSurfaceInfo.mUsage);
            this.mRepeatingRequestImageReader = newInstance3;
            this.mCameraRepeatingSurface = newInstance3.getSurface();
        }
        CameraOutputImageCallback cameraOutputImageCallback = new CameraOutputImageCallback(this.mRepeatingRequestImageReader);
        this.mRepeatingRequestImageCallback = cameraOutputImageCallback;
        this.mRepeatingRequestImageReader.setOnImageAvailableListener(cameraOutputImageCallback, this.mHandler);
    }

    private void initializeBurstCapturePipeline() throws RemoteException {
        ICaptureProcessorImpl captureProcessor = this.mImageExtender.getCaptureProcessor();
        this.mImageProcessor = captureProcessor;
        if (captureProcessor == null && this.mImageExtender.getMaxCaptureStage() != 1) {
            throw new UnsupportedOperationException("Multiple stages expected without a valid capture processor!");
        } else if (this.mImageProcessor != null) {
            Surface surface = this.mClientCaptureSurface;
            if (surface != null) {
                CameraExtensionUtils.SurfaceInfo surfaceInfo = CameraExtensionUtils.querySurface(surface);
                if (surfaceInfo.mFormat == 256) {
                    CameraExtensionJpegProcessor cameraExtensionJpegProcessor = new CameraExtensionJpegProcessor(this.mImageProcessor);
                    this.mImageJpegProcessor = cameraExtensionJpegProcessor;
                    this.mImageProcessor = cameraExtensionJpegProcessor;
                }
                this.mBurstCaptureImageReader = ImageReader.newInstance(surfaceInfo.mWidth, surfaceInfo.mHeight, 35, this.mImageExtender.getMaxCaptureStage());
            } else {
                this.mBurstCaptureImageReader = ImageReader.newInstance(this.mRepeatingRequestImageReader.getWidth(), this.mRepeatingRequestImageReader.getHeight(), 35, 1);
                ImageReader newInstance = ImageReader.newInstance(this.mRepeatingRequestImageReader.getWidth(), this.mRepeatingRequestImageReader.getHeight(), 35, 1);
                this.mStubCaptureImageReader = newInstance;
                this.mImageProcessor.onOutputSurface(newInstance.getSurface(), 35);
            }
            CameraOutputImageCallback cameraOutputImageCallback = new CameraOutputImageCallback(this.mBurstCaptureImageReader);
            this.mBurstCaptureImageCallback = cameraOutputImageCallback;
            this.mBurstCaptureImageReader.setOnImageAvailableListener(cameraOutputImageCallback, this.mHandler);
            this.mCameraBurstSurface = this.mBurstCaptureImageReader.getSurface();
            android.hardware.camera2.extension.Size sz = new android.hardware.camera2.extension.Size();
            sz.width = this.mBurstCaptureImageReader.getWidth();
            sz.height = this.mBurstCaptureImageReader.getHeight();
            this.mImageProcessor.onResolutionUpdate(sz);
            this.mImageProcessor.onImageFormatUpdate(this.mBurstCaptureImageReader.getImageFormat());
        } else {
            Surface surface2 = this.mClientCaptureSurface;
            if (surface2 != null) {
                this.mCameraBurstSurface = surface2;
                return;
            }
            ImageReader newInstance2 = ImageReader.newInstance(this.mRepeatingRequestImageReader.getWidth(), this.mRepeatingRequestImageReader.getHeight(), 256, 1);
            this.mBurstCaptureImageReader = newInstance2;
            this.mCameraBurstSurface = newInstance2.getSurface();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishPipelineInitialization() throws RemoteException {
        Surface surface;
        Surface surface2 = this.mClientRepeatingRequestSurface;
        if (surface2 != null) {
            int i = this.mPreviewProcessorType;
            if (i == 0) {
                this.mPreviewRequestUpdateProcessor.onOutputSurface(surface2, nativeGetSurfaceFormat(surface2));
                this.mRepeatingRequestImageWriter = ImageWriter.newInstance(this.mClientRepeatingRequestSurface, 3, 34);
            } else if (i == 2) {
                this.mRepeatingRequestImageWriter = ImageWriter.newInstance(surface2, 3, 34);
            }
        }
        if (this.mImageProcessor != null && (surface = this.mClientCaptureSurface) != null) {
            CameraExtensionUtils.SurfaceInfo surfaceInfo = CameraExtensionUtils.querySurface(surface);
            this.mImageProcessor.onOutputSurface(this.mClientCaptureSurface, surfaceInfo.mFormat);
        }
    }

    public synchronized void initialize() throws CameraAccessException, RemoteException {
        if (this.mInitialized) {
            Log.d(TAG, "Session already initialized");
            return;
        }
        ArrayList<CaptureStageImpl> sessionParamsList = new ArrayList<>();
        ArrayList<OutputConfiguration> outputList = new ArrayList<>();
        initializeRepeatingRequestPipeline();
        outputList.add(new OutputConfiguration(this.mCameraRepeatingSurface));
        CaptureStageImpl previewSessionParams = this.mPreviewExtender.onPresetSession();
        if (previewSessionParams != null) {
            sessionParamsList.add(previewSessionParams);
        }
        initializeBurstCapturePipeline();
        outputList.add(new OutputConfiguration(this.mCameraBurstSurface));
        CaptureStageImpl stillCaptureSessionParams = this.mImageExtender.onPresetSession();
        if (stillCaptureSessionParams != null) {
            sessionParamsList.add(stillCaptureSessionParams);
        }
        SessionConfiguration sessionConfig = new SessionConfiguration(0, outputList, new CameraExtensionUtils.HandlerExecutor(this.mHandler), new SessionStateHandler());
        if (!sessionParamsList.isEmpty()) {
            CaptureRequest sessionParamRequest = createRequest(this.mCameraDevice, sessionParamsList, null, 1);
            sessionConfig.setSessionParameters(sessionParamRequest);
        }
        this.mCameraDevice.createCaptureSession(sessionConfig);
    }

    @Override // android.hardware.camera2.CameraExtensionSession
    public CameraDevice getDevice() {
        CameraDevice cameraDevice;
        synchronized (this.mInterfaceLock) {
            cameraDevice = this.mCameraDevice;
        }
        return cameraDevice;
    }

    @Override // android.hardware.camera2.CameraExtensionSession
    public int setRepeatingRequest(CaptureRequest request, Executor executor, CameraExtensionSession.ExtensionCaptureCallback listener) throws CameraAccessException {
        int repeatingRequest;
        synchronized (this.mInterfaceLock) {
            if (this.mInitialized) {
                Surface surface = this.mClientRepeatingRequestSurface;
                if (surface == null) {
                    throw new IllegalArgumentException("No registered preview surface");
                } else if (!request.containsTarget(surface) || request.getTargets().size() != 1) {
                    throw new IllegalArgumentException("Invalid repeating request output target!");
                } else {
                    this.mInternalRepeatingRequestEnabled = false;
                    try {
                        repeatingRequest = setRepeatingRequest(this.mPreviewExtender.getCaptureStage(), new RepeatingRequestHandler(request, executor, listener, this.mRepeatingRequestImageCallback));
                    } catch (RemoteException e) {
                        Log.e(TAG, "Failed to set repeating request! Extension service does not respond");
                        throw new CameraAccessException(3);
                    }
                }
            } else {
                throw new IllegalStateException("Uninitialized component");
            }
        }
        return repeatingRequest;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<CaptureStageImpl> compileInitialRequestList() {
        ArrayList<CaptureStageImpl> captureStageList = new ArrayList<>();
        try {
            CaptureStageImpl initialPreviewParams = this.mPreviewExtender.onEnableSession();
            if (initialPreviewParams != null) {
                captureStageList.add(initialPreviewParams);
            }
            CaptureStageImpl initialStillCaptureParams = this.mImageExtender.onEnableSession();
            if (initialStillCaptureParams != null) {
                captureStageList.add(initialStillCaptureParams);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to initialize session parameters! Extension service does not respond!");
        }
        return captureStageList;
    }

    private static List<CaptureRequest> createBurstRequest(CameraDevice cameraDevice, List<CaptureStageImpl> captureStageList, CaptureRequest clientRequest, Surface target, int captureTemplate, Map<CaptureRequest, Integer> captureMap) {
        ArrayList<CaptureRequest> ret = new ArrayList<>();
        for (CaptureStageImpl captureStage : captureStageList) {
            try {
                CaptureRequest.Builder requestBuilder = cameraDevice.createCaptureRequest(captureTemplate);
                Integer jpegRotation = (Integer) clientRequest.get(CaptureRequest.JPEG_ORIENTATION);
                if (jpegRotation != null) {
                    captureStage.parameters.set((CaptureRequest.Key<CaptureRequest.Key<Integer>>) CaptureRequest.JPEG_ORIENTATION, (CaptureRequest.Key<Integer>) jpegRotation);
                }
                Byte jpegQuality = (Byte) clientRequest.get(CaptureRequest.JPEG_QUALITY);
                if (jpegQuality != null) {
                    captureStage.parameters.set((CaptureRequest.Key<CaptureRequest.Key<Byte>>) CaptureRequest.JPEG_QUALITY, (CaptureRequest.Key<Byte>) jpegQuality);
                }
                requestBuilder.addTarget(target);
                CaptureRequest request = requestBuilder.build();
                CameraMetadataNative.update(request.getNativeMetadata(), captureStage.parameters);
                ret.add(request);
                captureMap.put(request, Integer.valueOf(captureStage.id));
            } catch (CameraAccessException e) {
                return null;
            }
        }
        return ret;
    }

    private static CaptureRequest createRequest(CameraDevice cameraDevice, List<CaptureStageImpl> captureStageList, Surface target, int captureTemplate) throws CameraAccessException {
        CaptureRequest.Builder requestBuilder = cameraDevice.createCaptureRequest(captureTemplate);
        if (target != null) {
            requestBuilder.addTarget(target);
        }
        CaptureRequest ret = requestBuilder.build();
        for (CaptureStageImpl captureStage : captureStageList) {
            if (captureStage != null) {
                CameraMetadataNative.update(ret.getNativeMetadata(), captureStage.parameters);
            }
        }
        return ret;
    }

    @Override // android.hardware.camera2.CameraExtensionSession
    public int capture(CaptureRequest request, Executor executor, CameraExtensionSession.ExtensionCaptureCallback listener) throws CameraAccessException {
        if (this.mInitialized) {
            Surface surface = this.mClientCaptureSurface;
            if (surface == null) {
                throw new IllegalArgumentException("No output surface registered for single requests!");
            } else if (!request.containsTarget(surface) || request.getTargets().size() != 1) {
                throw new IllegalArgumentException("Invalid single capture output target!");
            } else {
                HashMap<CaptureRequest, Integer> requestMap = new HashMap<>();
                try {
                    List<CaptureRequest> burstRequest = createBurstRequest(this.mCameraDevice, this.mImageExtender.getCaptureStages(), request, this.mCameraBurstSurface, 2, requestMap);
                    if (burstRequest != null) {
                        return this.mCaptureSession.captureBurstRequests(burstRequest, new CameraExtensionUtils.HandlerExecutor(this.mHandler), new BurstRequestHandler(request, executor, listener, requestMap, this.mBurstCaptureImageCallback));
                    }
                    throw new UnsupportedOperationException("Failed to create still capture burst request");
                } catch (RemoteException e) {
                    Log.e(TAG, "Failed to initialize internal burst request! Extension service does not respond!");
                    throw new CameraAccessException(3);
                }
            }
        } else {
            throw new IllegalStateException("Uninitialized component");
        }
    }

    @Override // android.hardware.camera2.CameraExtensionSession
    public void stopRepeating() throws CameraAccessException {
        synchronized (this.mInterfaceLock) {
            if (this.mInitialized) {
                this.mInternalRepeatingRequestEnabled = true;
                this.mCaptureSession.stopRepeating();
            } else {
                throw new IllegalStateException("Uninitialized component");
            }
        }
    }

    @Override // android.hardware.camera2.CameraExtensionSession, java.lang.AutoCloseable
    public void close() throws CameraAccessException {
        synchronized (this.mInterfaceLock) {
            if (this.mInitialized) {
                this.mInternalRepeatingRequestEnabled = false;
                this.mCaptureSession.stopRepeating();
                ArrayList<CaptureStageImpl> captureStageList = new ArrayList<>();
                try {
                    CaptureStageImpl disableParams = this.mPreviewExtender.onDisableSession();
                    if (disableParams != null) {
                        captureStageList.add(disableParams);
                    }
                    CaptureStageImpl disableStillCaptureParams = this.mImageExtender.onDisableSession();
                    if (disableStillCaptureParams != null) {
                        captureStageList.add(disableStillCaptureParams);
                    }
                } catch (RemoteException e) {
                    Log.e(TAG, "Failed to disable extension! Extension service does not respond!");
                }
                if (!captureStageList.isEmpty()) {
                    CaptureRequest disableRequest = createRequest(this.mCameraDevice, captureStageList, this.mCameraRepeatingSurface, 1);
                    this.mCaptureSession.capture(disableRequest, new CloseRequestHandler(this.mRepeatingRequestImageCallback), this.mHandler);
                }
                this.mCaptureSession.close();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setInitialCaptureRequest(List<CaptureStageImpl> captureStageList, InitialRequestHandler requestHandler) throws CameraAccessException {
        CaptureRequest initialRequest = createRequest(this.mCameraDevice, captureStageList, this.mCameraRepeatingSurface, 1);
        this.mCaptureSession.capture(initialRequest, requestHandler, this.mHandler);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int setRepeatingRequest(CaptureStageImpl captureStage, CameraCaptureSession.CaptureCallback requestHandler) throws CameraAccessException {
        ArrayList<CaptureStageImpl> captureStageList = new ArrayList<>();
        captureStageList.add(captureStage);
        CaptureRequest repeatingRequest = createRequest(this.mCameraDevice, captureStageList, this.mCameraRepeatingSurface, 1);
        return this.mCaptureSession.setSingleRepeatingRequest(repeatingRequest, new CameraExtensionUtils.HandlerExecutor(this.mHandler), requestHandler);
    }

    public void release(boolean skipCloseNotification) {
        boolean notifyClose = false;
        synchronized (this.mInterfaceLock) {
            this.mInternalRepeatingRequestEnabled = false;
            this.mHandlerThread.quitSafely();
            try {
                this.mPreviewExtender.onDeInit();
                this.mImageExtender.onDeInit();
            } catch (RemoteException e) {
                Log.e(TAG, "Failed to release extensions! Extension service does not respond!");
            }
            long j = this.mExtensionClientId;
            if (j >= 0) {
                CameraExtensionCharacteristics.unregisterClient(j);
                if (this.mInitialized) {
                    notifyClose = true;
                    CameraExtensionCharacteristics.releaseSession();
                }
            }
            this.mInitialized = false;
            CameraOutputImageCallback cameraOutputImageCallback = this.mRepeatingRequestImageCallback;
            if (cameraOutputImageCallback != null) {
                cameraOutputImageCallback.close();
                this.mRepeatingRequestImageCallback = null;
            }
            ImageReader imageReader = this.mRepeatingRequestImageReader;
            if (imageReader != null) {
                imageReader.close();
                this.mRepeatingRequestImageReader = null;
            }
            CameraOutputImageCallback cameraOutputImageCallback2 = this.mBurstCaptureImageCallback;
            if (cameraOutputImageCallback2 != null) {
                cameraOutputImageCallback2.close();
                this.mBurstCaptureImageCallback = null;
            }
            ImageReader imageReader2 = this.mBurstCaptureImageReader;
            if (imageReader2 != null) {
                imageReader2.close();
                this.mBurstCaptureImageReader = null;
            }
            ImageReader imageReader3 = this.mStubCaptureImageReader;
            if (imageReader3 != null) {
                imageReader3.close();
                this.mStubCaptureImageReader = null;
            }
            ImageWriter imageWriter = this.mRepeatingRequestImageWriter;
            if (imageWriter != null) {
                imageWriter.close();
                this.mRepeatingRequestImageWriter = null;
            }
            CameraExtensionForwardProcessor cameraExtensionForwardProcessor = this.mPreviewImageProcessor;
            if (cameraExtensionForwardProcessor != null) {
                cameraExtensionForwardProcessor.close();
                this.mPreviewImageProcessor = null;
            }
            CameraExtensionJpegProcessor cameraExtensionJpegProcessor = this.mImageJpegProcessor;
            if (cameraExtensionJpegProcessor != null) {
                cameraExtensionJpegProcessor.close();
                this.mImageJpegProcessor = null;
            }
            this.mCaptureSession = null;
            this.mImageProcessor = null;
            this.mClientRepeatingRequestSurface = null;
            this.mCameraRepeatingSurface = null;
            this.mClientCaptureSurface = null;
            this.mCameraBurstSurface = null;
        }
        if (notifyClose && !skipCloseNotification) {
            long ident = Binder.clearCallingIdentity();
            try {
                this.mExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.CameraExtensionSessionImpl$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        CameraExtensionSessionImpl.this.lambda$release$0$CameraExtensionSessionImpl();
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(ident);
            }
        }
    }

    public /* synthetic */ void lambda$release$0$CameraExtensionSessionImpl() {
        this.mCallbacks.onClosed(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyConfigurationFailure() {
        synchronized (this.mInterfaceLock) {
            if (!this.mInitialized) {
                release(true);
                long ident = Binder.clearCallingIdentity();
                try {
                    this.mExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.CameraExtensionSessionImpl$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            CameraExtensionSessionImpl.this.lambda$notifyConfigurationFailure$1$CameraExtensionSessionImpl();
                        }
                    });
                } finally {
                    Binder.restoreCallingIdentity(ident);
                }
            }
        }
    }

    public /* synthetic */ void lambda$notifyConfigurationFailure$1$CameraExtensionSessionImpl() {
        this.mCallbacks.onConfigureFailed(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyConfigurationSuccess() {
        synchronized (this.mInterfaceLock) {
            if (!this.mInitialized) {
                this.mInitialized = true;
                long ident = Binder.clearCallingIdentity();
                try {
                    this.mExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.CameraExtensionSessionImpl$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            CameraExtensionSessionImpl.this.lambda$notifyConfigurationSuccess$2$CameraExtensionSessionImpl();
                        }
                    });
                } finally {
                    Binder.restoreCallingIdentity(ident);
                }
            }
        }
    }

    public /* synthetic */ void lambda$notifyConfigurationSuccess$2$CameraExtensionSessionImpl() {
        this.mCallbacks.onConfigured(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class SessionStateHandler extends CameraCaptureSession.StateCallback {
        private SessionStateHandler() {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onClosed(CameraCaptureSession session) {
            CameraExtensionSessionImpl.this.release(false);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigureFailed(CameraCaptureSession session) {
            CameraExtensionSessionImpl.this.notifyConfigurationFailure();
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigured(CameraCaptureSession session) {
            synchronized (CameraExtensionSessionImpl.this.mInterfaceLock) {
                CameraExtensionSessionImpl.this.mCaptureSession = session;
                try {
                    CameraExtensionSessionImpl.this.finishPipelineInitialization();
                    CameraExtensionCharacteristics.initializeSession(CameraExtensionSessionImpl.this.mInitializeHandler);
                } catch (RemoteException e) {
                    Log.e(CameraExtensionSessionImpl.TAG, "Failed to initialize session! Extension service does not respond!");
                    CameraExtensionSessionImpl.this.notifyConfigurationFailure();
                }
            }
        }
    }

    /* loaded from: classes.dex */
    private class InitializeSessionHandler extends IInitializeSessionCallback.Stub {
        private InitializeSessionHandler() {
        }

        @Override // android.hardware.camera2.extension.IInitializeSessionCallback
        public void onSuccess() {
            boolean status = true;
            ArrayList<CaptureStageImpl> initialRequestList = CameraExtensionSessionImpl.this.compileInitialRequestList();
            if (!initialRequestList.isEmpty()) {
                try {
                    CameraExtensionSessionImpl cameraExtensionSessionImpl = CameraExtensionSessionImpl.this;
                    cameraExtensionSessionImpl.setInitialCaptureRequest(initialRequestList, new InitialRequestHandler(cameraExtensionSessionImpl.mRepeatingRequestImageCallback));
                } catch (CameraAccessException e) {
                    Log.e(CameraExtensionSessionImpl.TAG, "Failed to initialize the initial capture request!");
                    status = false;
                }
            } else {
                try {
                    CameraExtensionSessionImpl cameraExtensionSessionImpl2 = CameraExtensionSessionImpl.this;
                    CaptureStageImpl captureStage = cameraExtensionSessionImpl2.mPreviewExtender.getCaptureStage();
                    CameraExtensionSessionImpl cameraExtensionSessionImpl3 = CameraExtensionSessionImpl.this;
                    cameraExtensionSessionImpl2.setRepeatingRequest(captureStage, new RepeatingRequestHandler(null, null, null, cameraExtensionSessionImpl3.mRepeatingRequestImageCallback));
                } catch (CameraAccessException | RemoteException e2) {
                    Log.e(CameraExtensionSessionImpl.TAG, "Failed to initialize internal repeating request!");
                    status = false;
                }
            }
            if (!status) {
                CameraExtensionSessionImpl.this.notifyConfigurationFailure();
            }
        }

        @Override // android.hardware.camera2.extension.IInitializeSessionCallback
        public void onFailure() {
            CameraExtensionSessionImpl.this.mCaptureSession.close();
            Log.e(CameraExtensionSessionImpl.TAG, "Failed to initialize proxy service session! This can happen when trying to configure multiple concurrent extension sessions!");
            CameraExtensionSessionImpl.this.notifyConfigurationFailure();
        }
    }

    /* loaded from: classes.dex */
    private class BurstRequestHandler extends CameraCaptureSession.CaptureCallback {
        private final CameraOutputImageCallback mBurstImageCallback;
        private final CameraExtensionSession.ExtensionCaptureCallback mCallbacks;
        private final HashMap<CaptureRequest, Integer> mCaptureRequestMap;
        private final CaptureRequest mClientRequest;
        private final Executor mExecutor;
        private HashMap<Integer, Pair<Image, TotalCaptureResult>> mCaptureStageMap = new HashMap<>();
        private LongSparseArray<Pair<Image, Integer>> mCapturePendingMap = new LongSparseArray<>();
        private ImageCallback mImageCallback = null;
        private boolean mCaptureFailed = false;

        public BurstRequestHandler(CaptureRequest request, Executor executor, CameraExtensionSession.ExtensionCaptureCallback callbacks, HashMap<CaptureRequest, Integer> requestMap, CameraOutputImageCallback imageCallback) {
            this.mClientRequest = request;
            this.mExecutor = executor;
            this.mCallbacks = callbacks;
            this.mCaptureRequestMap = requestMap;
            this.mBurstImageCallback = imageCallback;
        }

        /* JADX WARN: Finally extract failed */
        private void notifyCaptureFailed() {
            if (!this.mCaptureFailed) {
                this.mCaptureFailed = true;
                long ident = Binder.clearCallingIdentity();
                try {
                    this.mExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.CameraExtensionSessionImpl$BurstRequestHandler$$ExternalSyntheticLambda2
                        @Override // java.lang.Runnable
                        public final void run() {
                            CameraExtensionSessionImpl.BurstRequestHandler.this.lambda$notifyCaptureFailed$0$CameraExtensionSessionImpl$BurstRequestHandler();
                        }
                    });
                    Binder.restoreCallingIdentity(ident);
                    for (Pair<Image, TotalCaptureResult> captureStage : this.mCaptureStageMap.values()) {
                        captureStage.first.close();
                    }
                    this.mCaptureStageMap.clear();
                } catch (Throwable th) {
                    Binder.restoreCallingIdentity(ident);
                    throw th;
                }
            }
        }

        public /* synthetic */ void lambda$notifyCaptureFailed$0$CameraExtensionSessionImpl$BurstRequestHandler() {
            this.mCallbacks.onCaptureFailed(CameraExtensionSessionImpl.this, this.mClientRequest);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureStarted(CameraCaptureSession session, CaptureRequest request, final long timestamp, long frameNumber) {
            boolean initialCallback = false;
            synchronized (CameraExtensionSessionImpl.this.mInterfaceLock) {
                if (CameraExtensionSessionImpl.this.mImageProcessor != null && this.mImageCallback == null) {
                    this.mImageCallback = new ImageCallback();
                    initialCallback = true;
                } else if (CameraExtensionSessionImpl.this.mImageProcessor == null) {
                    initialCallback = true;
                }
            }
            if (initialCallback) {
                long ident = Binder.clearCallingIdentity();
                try {
                    this.mExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.CameraExtensionSessionImpl$BurstRequestHandler$$ExternalSyntheticLambda6
                        @Override // java.lang.Runnable
                        public final void run() {
                            CameraExtensionSessionImpl.BurstRequestHandler.this.lambda$onCaptureStarted$1$CameraExtensionSessionImpl$BurstRequestHandler(timestamp);
                        }
                    });
                } finally {
                    Binder.restoreCallingIdentity(ident);
                }
            }
            CameraOutputImageCallback cameraOutputImageCallback = this.mBurstImageCallback;
            if (cameraOutputImageCallback != null && this.mImageCallback != null) {
                cameraOutputImageCallback.registerListener(Long.valueOf(timestamp), this.mImageCallback);
            }
        }

        public /* synthetic */ void lambda$onCaptureStarted$1$CameraExtensionSessionImpl$BurstRequestHandler(long timestamp) {
            this.mCallbacks.onCaptureStarted(CameraExtensionSessionImpl.this, this.mClientRequest, timestamp);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureBufferLost(CameraCaptureSession session, CaptureRequest request, Surface target, long frameNumber) {
            notifyCaptureFailed();
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureFailed(CameraCaptureSession session, CaptureRequest request, CaptureFailure failure) {
            notifyCaptureFailed();
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureSequenceAborted(CameraCaptureSession session, final int sequenceId) {
            long ident = Binder.clearCallingIdentity();
            try {
                this.mExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.CameraExtensionSessionImpl$BurstRequestHandler$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        CameraExtensionSessionImpl.BurstRequestHandler.this.lambda$onCaptureSequenceAborted$2$CameraExtensionSessionImpl$BurstRequestHandler(sequenceId);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(ident);
            }
        }

        public /* synthetic */ void lambda$onCaptureSequenceAborted$2$CameraExtensionSessionImpl$BurstRequestHandler(int sequenceId) {
            this.mCallbacks.onCaptureSequenceAborted(CameraExtensionSessionImpl.this, sequenceId);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureSequenceCompleted(CameraCaptureSession session, final int sequenceId, long frameNumber) {
            long ident = Binder.clearCallingIdentity();
            try {
                this.mExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.CameraExtensionSessionImpl$BurstRequestHandler$$ExternalSyntheticLambda5
                    @Override // java.lang.Runnable
                    public final void run() {
                        CameraExtensionSessionImpl.BurstRequestHandler.this.lambda$onCaptureSequenceCompleted$3$CameraExtensionSessionImpl$BurstRequestHandler(sequenceId);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(ident);
            }
        }

        public /* synthetic */ void lambda$onCaptureSequenceCompleted$3$CameraExtensionSessionImpl$BurstRequestHandler(int sequenceId) {
            this.mCallbacks.onCaptureSequenceCompleted(CameraExtensionSessionImpl.this, sequenceId);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
            if (!this.mCaptureRequestMap.containsKey(request)) {
                Log.e(CameraExtensionSessionImpl.TAG, "Unexpected still capture request received!");
                return;
            }
            Integer stageId = this.mCaptureRequestMap.get(request);
            Long timestamp = (Long) result.get(CaptureResult.SENSOR_TIMESTAMP);
            if (timestamp == null) {
                Log.e(CameraExtensionSessionImpl.TAG, "Capture result without valid sensor timestamp!");
            } else if (CameraExtensionSessionImpl.this.mImageProcessor == null) {
                this.mCaptureRequestMap.clear();
                long ident = Binder.clearCallingIdentity();
                try {
                    this.mExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.CameraExtensionSessionImpl$BurstRequestHandler$$ExternalSyntheticLambda3
                        @Override // java.lang.Runnable
                        public final void run() {
                            CameraExtensionSessionImpl.BurstRequestHandler.this.lambda$onCaptureCompleted$4$CameraExtensionSessionImpl$BurstRequestHandler();
                        }
                    });
                } finally {
                    Binder.restoreCallingIdentity(ident);
                }
            } else if (this.mCapturePendingMap.indexOfKey(timestamp.longValue()) >= 0) {
                Image img = this.mCapturePendingMap.get(timestamp.longValue()).first;
                this.mCaptureStageMap.put(stageId, new Pair<>(img, result));
                checkAndFireBurstProcessing();
            } else {
                this.mCapturePendingMap.put(timestamp.longValue(), new Pair<>(null, stageId));
                this.mCaptureStageMap.put(stageId, new Pair<>(null, result));
            }
        }

        public /* synthetic */ void lambda$onCaptureCompleted$4$CameraExtensionSessionImpl$BurstRequestHandler() {
            this.mCallbacks.onCaptureProcessStarted(CameraExtensionSessionImpl.this, this.mClientRequest);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:7:0x001e  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void checkAndFireBurstProcessing() {
            /*
                r8 = this;
                java.util.HashMap<android.hardware.camera2.CaptureRequest, java.lang.Integer> r0 = r8.mCaptureRequestMap
                int r0 = r0.size()
                java.util.HashMap<java.lang.Integer, android.util.Pair<android.media.Image, android.hardware.camera2.TotalCaptureResult>> r1 = r8.mCaptureStageMap
                int r1 = r1.size()
                if (r0 != r1) goto L_0x00c9
                java.util.HashMap<java.lang.Integer, android.util.Pair<android.media.Image, android.hardware.camera2.TotalCaptureResult>> r0 = r8.mCaptureStageMap
                java.util.Collection r0 = r0.values()
                java.util.Iterator r0 = r0.iterator()
            L_0x0018:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L_0x002f
                java.lang.Object r1 = r0.next()
                android.util.Pair r1 = (android.util.Pair) r1
                F r2 = r1.first
                if (r2 == 0) goto L_0x002e
                S r2 = r1.second
                if (r2 != 0) goto L_0x002d
                goto L_0x002e
            L_0x002d:
                goto L_0x0018
            L_0x002e:
                return
            L_0x002f:
                java.util.HashMap<android.hardware.camera2.CaptureRequest, java.lang.Integer> r0 = r8.mCaptureRequestMap
                r0.clear()
                android.util.LongSparseArray<android.util.Pair<android.media.Image, java.lang.Integer>> r0 = r8.mCapturePendingMap
                r0.clear()
                r0 = 1
                android.hardware.camera2.CaptureRequest r1 = r8.mClientRequest
                android.hardware.camera2.CaptureRequest$Key<java.lang.Byte> r2 = android.hardware.camera2.CaptureRequest.JPEG_QUALITY
                java.lang.Object r1 = r1.get(r2)
                java.lang.Byte r1 = (java.lang.Byte) r1
                android.hardware.camera2.CaptureRequest r2 = r8.mClientRequest
                android.hardware.camera2.CaptureRequest$Key<java.lang.Integer> r3 = android.hardware.camera2.CaptureRequest.JPEG_ORIENTATION
                java.lang.Object r2 = r2.get(r3)
                java.lang.Integer r2 = (java.lang.Integer) r2
                java.util.HashMap<java.lang.Integer, android.util.Pair<android.media.Image, android.hardware.camera2.TotalCaptureResult>> r3 = r8.mCaptureStageMap
                java.util.List r3 = android.hardware.camera2.impl.CameraExtensionSessionImpl.access$1300(r3, r2, r1)
                android.hardware.camera2.impl.CameraExtensionSessionImpl r4 = android.hardware.camera2.impl.CameraExtensionSessionImpl.this     // Catch: RemoteException -> 0x005e
                android.hardware.camera2.extension.ICaptureProcessorImpl r4 = android.hardware.camera2.impl.CameraExtensionSessionImpl.access$1100(r4)     // Catch: RemoteException -> 0x005e
                r4.process(r3)     // Catch: RemoteException -> 0x005e
                goto L_0x0067
            L_0x005e:
                r4 = move-exception
                java.lang.String r5 = "CameraExtensionSessionImpl"
                java.lang.String r6 = "Failed to process multi-frame request! Extension service does not respond!"
                android.util.Log.e(r5, r6)
                r0 = 0
            L_0x0067:
                java.util.Iterator r4 = r3.iterator()
            L_0x006b:
                boolean r5 = r4.hasNext()
                if (r5 == 0) goto L_0x007f
                java.lang.Object r5 = r4.next()
                android.hardware.camera2.extension.CaptureBundle r5 = (android.hardware.camera2.extension.CaptureBundle) r5
                android.hardware.camera2.extension.ParcelImage r6 = r5.captureImage
                android.hardware.HardwareBuffer r6 = r6.buffer
                r6.close()
                goto L_0x006b
            L_0x007f:
                r3.clear()
                java.util.HashMap<java.lang.Integer, android.util.Pair<android.media.Image, android.hardware.camera2.TotalCaptureResult>> r4 = r8.mCaptureStageMap
                java.util.Collection r4 = r4.values()
                java.util.Iterator r4 = r4.iterator()
            L_0x008c:
                boolean r5 = r4.hasNext()
                if (r5 == 0) goto L_0x00a0
                java.lang.Object r5 = r4.next()
                android.util.Pair r5 = (android.util.Pair) r5
                F r6 = r5.first
                android.media.Image r6 = (android.media.Image) r6
                r6.close()
                goto L_0x008c
            L_0x00a0:
                java.util.HashMap<java.lang.Integer, android.util.Pair<android.media.Image, android.hardware.camera2.TotalCaptureResult>> r4 = r8.mCaptureStageMap
                r4.clear()
                long r4 = android.os.Binder.clearCallingIdentity()
                if (r0 == 0) goto L_0x00b6
                java.util.concurrent.Executor r6 = r8.mExecutor     // Catch: all -> 0x00c4
                android.hardware.camera2.impl.CameraExtensionSessionImpl$BurstRequestHandler$$ExternalSyntheticLambda0 r7 = new android.hardware.camera2.impl.CameraExtensionSessionImpl$BurstRequestHandler$$ExternalSyntheticLambda0     // Catch: all -> 0x00c4
                r7.<init>()     // Catch: all -> 0x00c4
                r6.execute(r7)     // Catch: all -> 0x00c4
                goto L_0x00c0
            L_0x00b6:
                java.util.concurrent.Executor r6 = r8.mExecutor     // Catch: all -> 0x00c4
                android.hardware.camera2.impl.CameraExtensionSessionImpl$BurstRequestHandler$$ExternalSyntheticLambda1 r7 = new android.hardware.camera2.impl.CameraExtensionSessionImpl$BurstRequestHandler$$ExternalSyntheticLambda1     // Catch: all -> 0x00c4
                r7.<init>()     // Catch: all -> 0x00c4
                r6.execute(r7)     // Catch: all -> 0x00c4
            L_0x00c0:
                android.os.Binder.restoreCallingIdentity(r4)
                goto L_0x00c9
            L_0x00c4:
                r6 = move-exception
                android.os.Binder.restoreCallingIdentity(r4)
                throw r6
            L_0x00c9:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.hardware.camera2.impl.CameraExtensionSessionImpl.BurstRequestHandler.checkAndFireBurstProcessing():void");
        }

        public /* synthetic */ void lambda$checkAndFireBurstProcessing$5$CameraExtensionSessionImpl$BurstRequestHandler() {
            this.mCallbacks.onCaptureProcessStarted(CameraExtensionSessionImpl.this, this.mClientRequest);
        }

        public /* synthetic */ void lambda$checkAndFireBurstProcessing$6$CameraExtensionSessionImpl$BurstRequestHandler() {
            this.mCallbacks.onCaptureFailed(CameraExtensionSessionImpl.this, this.mClientRequest);
        }

        /* loaded from: classes.dex */
        private class ImageCallback implements OnImageAvailableListener {
            private ImageCallback() {
            }

            @Override // android.hardware.camera2.impl.CameraExtensionSessionImpl.OnImageAvailableListener
            public void onImageAvailable(ImageReader reader, Image img) {
                if (BurstRequestHandler.this.mCaptureFailed) {
                    img.close();
                }
                long timestamp = img.getTimestamp();
                reader.detachImage(img);
                if (BurstRequestHandler.this.mCapturePendingMap.indexOfKey(timestamp) >= 0) {
                    Integer stageId = (Integer) ((Pair) BurstRequestHandler.this.mCapturePendingMap.get(timestamp)).second;
                    Pair<Image, TotalCaptureResult> captureStage = (Pair) BurstRequestHandler.this.mCaptureStageMap.get(stageId);
                    if (captureStage != null) {
                        BurstRequestHandler.this.mCaptureStageMap.put(stageId, new Pair(img, (TotalCaptureResult) captureStage.second));
                        BurstRequestHandler.this.checkAndFireBurstProcessing();
                        return;
                    }
                    Log.e(CameraExtensionSessionImpl.TAG, "Capture stage: " + ((Pair) BurstRequestHandler.this.mCapturePendingMap.get(timestamp)).second + " is absent!");
                    return;
                }
                BurstRequestHandler.this.mCapturePendingMap.put(timestamp, new Pair(img, -1));
            }
        }
    }

    /* loaded from: classes.dex */
    private class ImageLoopbackCallback implements OnImageAvailableListener {
        private ImageLoopbackCallback() {
        }

        @Override // android.hardware.camera2.impl.CameraExtensionSessionImpl.OnImageAvailableListener
        public void onImageAvailable(ImageReader reader, Image img) {
            img.close();
        }
    }

    /* loaded from: classes.dex */
    private class InitialRequestHandler extends CameraCaptureSession.CaptureCallback {
        private final CameraOutputImageCallback mImageCallback;

        public InitialRequestHandler(CameraOutputImageCallback imageCallback) {
            this.mImageCallback = imageCallback;
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureStarted(CameraCaptureSession session, CaptureRequest request, long timestamp, long frameNumber) {
            this.mImageCallback.registerListener(Long.valueOf(timestamp), new ImageLoopbackCallback());
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureSequenceAborted(CameraCaptureSession session, int sequenceId) {
            Log.e(CameraExtensionSessionImpl.TAG, "Initial capture request aborted!");
            CameraExtensionSessionImpl.this.notifyConfigurationFailure();
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureFailed(CameraCaptureSession session, CaptureRequest request, CaptureFailure failure) {
            Log.e(CameraExtensionSessionImpl.TAG, "Initial capture request failed!");
            CameraExtensionSessionImpl.this.notifyConfigurationFailure();
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureSequenceCompleted(CameraCaptureSession session, int sequenceId, long frameNumber) {
            boolean status = true;
            synchronized (CameraExtensionSessionImpl.this.mInterfaceLock) {
                try {
                    CameraExtensionSessionImpl cameraExtensionSessionImpl = CameraExtensionSessionImpl.this;
                    cameraExtensionSessionImpl.setRepeatingRequest(cameraExtensionSessionImpl.mPreviewExtender.getCaptureStage(), new RepeatingRequestHandler(null, null, null, this.mImageCallback));
                } catch (CameraAccessException | RemoteException e) {
                    Log.e(CameraExtensionSessionImpl.TAG, "Failed to start the internal repeating request!");
                    status = false;
                }
            }
            if (!status) {
                CameraExtensionSessionImpl.this.notifyConfigurationFailure();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class CameraOutputImageCallback implements ImageReader.OnImageAvailableListener, Closeable {
        private final ImageReader mImageReader;
        private HashMap<Long, Pair<Image, OnImageAvailableListener>> mImageListenerMap = new HashMap<>();
        private boolean mOutOfBuffers = false;

        CameraOutputImageCallback(ImageReader imageReader) {
            this.mImageReader = imageReader;
        }

        @Override // android.media.ImageReader.OnImageAvailableListener
        public void onImageAvailable(ImageReader reader) {
            try {
                Image img = reader.acquireNextImage();
                if (img == null) {
                    Log.e(CameraExtensionSessionImpl.TAG, "Invalid image!");
                    return;
                }
                Long timestamp = Long.valueOf(img.getTimestamp());
                if (this.mImageListenerMap.containsKey(timestamp)) {
                    Pair<Image, OnImageAvailableListener> entry = this.mImageListenerMap.remove(timestamp);
                    if (entry.second != null) {
                        entry.second.onImageAvailable(reader, img);
                        return;
                    }
                    Log.w(CameraExtensionSessionImpl.TAG, "Invalid image listener, dropping frame!");
                    img.close();
                    return;
                }
                this.mImageListenerMap.put(Long.valueOf(img.getTimestamp()), new Pair<>(img, null));
            } catch (IllegalStateException e) {
                Log.e(CameraExtensionSessionImpl.TAG, "Failed to acquire image, too many images pending!");
                this.mOutOfBuffers = true;
            }
        }

        public void registerListener(Long timestamp, OnImageAvailableListener listener) {
            if (this.mImageListenerMap.containsKey(timestamp)) {
                Pair<Image, OnImageAvailableListener> entry = this.mImageListenerMap.remove(timestamp);
                if (entry.first != null) {
                    listener.onImageAvailable(this.mImageReader, entry.first);
                    if (this.mOutOfBuffers) {
                        this.mOutOfBuffers = false;
                        Log.w(CameraExtensionSessionImpl.TAG, "Out of buffers, retry!");
                        onImageAvailable(this.mImageReader);
                        return;
                    }
                    return;
                }
                Log.w(CameraExtensionSessionImpl.TAG, "No valid image for listener with ts: " + timestamp.longValue());
                return;
            }
            this.mImageListenerMap.put(timestamp, new Pair<>(null, listener));
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            for (Pair<Image, OnImageAvailableListener> entry : this.mImageListenerMap.values()) {
                if (entry.first != null) {
                    entry.first.close();
                }
            }
            this.mImageListenerMap.clear();
        }
    }

    /* loaded from: classes.dex */
    private class CloseRequestHandler extends CameraCaptureSession.CaptureCallback {
        private final CameraOutputImageCallback mImageCallback;

        public CloseRequestHandler(CameraOutputImageCallback imageCallback) {
            this.mImageCallback = imageCallback;
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureStarted(CameraCaptureSession session, CaptureRequest request, long timestamp, long frameNumber) {
            this.mImageCallback.registerListener(Long.valueOf(timestamp), new ImageLoopbackCallback());
        }
    }

    /* loaded from: classes.dex */
    private class RepeatingRequestHandler extends CameraCaptureSession.CaptureCallback {
        private final CameraExtensionSession.ExtensionCaptureCallback mCallbacks;
        private final boolean mClientNotificationsEnabled;
        private final CaptureRequest mClientRequest;
        private final Executor mExecutor;
        private final CameraOutputImageCallback mRepeatingImageCallback;
        private OnImageAvailableListener mImageCallback = null;
        private LongSparseArray<Pair<Image, TotalCaptureResult>> mPendingResultMap = new LongSparseArray<>();
        private boolean mRequestUpdatedNeeded = false;

        public RepeatingRequestHandler(CaptureRequest clientRequest, Executor executor, CameraExtensionSession.ExtensionCaptureCallback listener, CameraOutputImageCallback imageCallback) {
            boolean z = false;
            this.mClientRequest = clientRequest;
            this.mExecutor = executor;
            this.mCallbacks = listener;
            if (!(clientRequest == null || executor == null || listener == null)) {
                z = true;
            }
            this.mClientNotificationsEnabled = z;
            this.mRepeatingImageCallback = imageCallback;
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureStarted(CameraCaptureSession session, CaptureRequest request, final long timestamp, long frameNumber) {
            OnImageAvailableListener onImageAvailableListener;
            synchronized (CameraExtensionSessionImpl.this.mInterfaceLock) {
                if (this.mImageCallback == null) {
                    if (CameraExtensionSessionImpl.this.mPreviewProcessorType == 1) {
                        if (this.mClientNotificationsEnabled) {
                            CameraExtensionSessionImpl.this.mPreviewImageProcessor.onOutputSurface(CameraExtensionSessionImpl.this.mClientRepeatingRequestSurface, CameraExtensionSessionImpl.nativeGetSurfaceFormat(CameraExtensionSessionImpl.this.mClientRepeatingRequestSurface));
                        } else {
                            CameraExtensionSessionImpl.this.mPreviewImageProcessor.onOutputSurface(null, -1);
                        }
                        this.mImageCallback = new ImageProcessCallback();
                    } else {
                        if (this.mClientNotificationsEnabled) {
                            onImageAvailableListener = new ImageForwardCallback(CameraExtensionSessionImpl.this.mRepeatingRequestImageWriter);
                        } else {
                            onImageAvailableListener = new ImageLoopbackCallback();
                        }
                        this.mImageCallback = onImageAvailableListener;
                    }
                }
            }
            if (this.mClientNotificationsEnabled) {
                long ident = Binder.clearCallingIdentity();
                try {
                    this.mExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.CameraExtensionSessionImpl$RepeatingRequestHandler$$ExternalSyntheticLambda6
                        @Override // java.lang.Runnable
                        public final void run() {
                            CameraExtensionSessionImpl.RepeatingRequestHandler.this.lambda$onCaptureStarted$0$CameraExtensionSessionImpl$RepeatingRequestHandler(timestamp);
                        }
                    });
                } finally {
                    Binder.restoreCallingIdentity(ident);
                }
            }
            this.mRepeatingImageCallback.registerListener(Long.valueOf(timestamp), this.mImageCallback);
        }

        public /* synthetic */ void lambda$onCaptureStarted$0$CameraExtensionSessionImpl$RepeatingRequestHandler(long timestamp) {
            this.mCallbacks.onCaptureStarted(CameraExtensionSessionImpl.this, this.mClientRequest, timestamp);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureSequenceAborted(CameraCaptureSession session, final int sequenceId) {
            synchronized (CameraExtensionSessionImpl.this.mInterfaceLock) {
                if (CameraExtensionSessionImpl.this.mInternalRepeatingRequestEnabled) {
                    resumeInternalRepeatingRequest(true);
                }
            }
            if (this.mClientNotificationsEnabled) {
                long ident = Binder.clearCallingIdentity();
                try {
                    this.mExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.CameraExtensionSessionImpl$RepeatingRequestHandler$$ExternalSyntheticLambda4
                        @Override // java.lang.Runnable
                        public final void run() {
                            CameraExtensionSessionImpl.RepeatingRequestHandler.this.lambda$onCaptureSequenceAborted$1$CameraExtensionSessionImpl$RepeatingRequestHandler(sequenceId);
                        }
                    });
                } finally {
                    Binder.restoreCallingIdentity(ident);
                }
            } else {
                CameraExtensionSessionImpl.this.notifyConfigurationFailure();
            }
        }

        public /* synthetic */ void lambda$onCaptureSequenceAborted$1$CameraExtensionSessionImpl$RepeatingRequestHandler(int sequenceId) {
            this.mCallbacks.onCaptureSequenceAborted(CameraExtensionSessionImpl.this, sequenceId);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureSequenceCompleted(CameraCaptureSession session, final int sequenceId, long frameNumber) {
            synchronized (CameraExtensionSessionImpl.this.mInterfaceLock) {
                if (this.mRequestUpdatedNeeded) {
                    this.mRequestUpdatedNeeded = false;
                    resumeInternalRepeatingRequest(false);
                } else if (CameraExtensionSessionImpl.this.mInternalRepeatingRequestEnabled) {
                    resumeInternalRepeatingRequest(true);
                }
            }
            if (this.mClientNotificationsEnabled) {
                long ident = Binder.clearCallingIdentity();
                try {
                    this.mExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.CameraExtensionSessionImpl$RepeatingRequestHandler$$ExternalSyntheticLambda5
                        @Override // java.lang.Runnable
                        public final void run() {
                            CameraExtensionSessionImpl.RepeatingRequestHandler.this.lambda$onCaptureSequenceCompleted$2$CameraExtensionSessionImpl$RepeatingRequestHandler(sequenceId);
                        }
                    });
                } finally {
                    Binder.restoreCallingIdentity(ident);
                }
            }
        }

        public /* synthetic */ void lambda$onCaptureSequenceCompleted$2$CameraExtensionSessionImpl$RepeatingRequestHandler(int sequenceId) {
            this.mCallbacks.onCaptureSequenceCompleted(CameraExtensionSessionImpl.this, sequenceId);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureFailed(CameraCaptureSession session, CaptureRequest request, CaptureFailure failure) {
            if (this.mClientNotificationsEnabled) {
                long ident = Binder.clearCallingIdentity();
                try {
                    this.mExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.CameraExtensionSessionImpl$RepeatingRequestHandler$$ExternalSyntheticLambda3
                        @Override // java.lang.Runnable
                        public final void run() {
                            CameraExtensionSessionImpl.RepeatingRequestHandler.this.lambda$onCaptureFailed$3$CameraExtensionSessionImpl$RepeatingRequestHandler();
                        }
                    });
                } finally {
                    Binder.restoreCallingIdentity(ident);
                }
            }
        }

        public /* synthetic */ void lambda$onCaptureFailed$3$CameraExtensionSessionImpl$RepeatingRequestHandler() {
            this.mCallbacks.onCaptureFailed(CameraExtensionSessionImpl.this, this.mClientRequest);
        }

        /* JADX WARN: Removed duplicated region for block: B:40:0x0113 A[Catch: all -> 0x0149, TRY_LEAVE, TryCatch #5 {, blocks: (B:4:0x0008, B:6:0x0012, B:10:0x001d, B:13:0x0032, B:15:0x003b, B:17:0x0044, B:20:0x0051, B:21:0x0055, B:23:0x005d, B:25:0x0069, B:27:0x0086, B:28:0x009b, B:32:0x00aa, B:36:0x00c9, B:37:0x00df, B:38:0x00ff, B:40:0x0113, B:44:0x012e, B:45:0x0139, B:46:0x0140, B:42:0x0119, B:43:0x0124, B:26:0x007d, B:31:0x00a3, B:35:0x00c2), top: B:59:0x0008 }] */
        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onCaptureCompleted(android.hardware.camera2.CameraCaptureSession r11, android.hardware.camera2.CaptureRequest r12, android.hardware.camera2.TotalCaptureResult r13) {
            /*
                Method dump skipped, instructions count: 332
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: android.hardware.camera2.impl.CameraExtensionSessionImpl.RepeatingRequestHandler.onCaptureCompleted(android.hardware.camera2.CameraCaptureSession, android.hardware.camera2.CaptureRequest, android.hardware.camera2.TotalCaptureResult):void");
        }

        public /* synthetic */ void lambda$onCaptureCompleted$4$CameraExtensionSessionImpl$RepeatingRequestHandler() {
            this.mCallbacks.onCaptureProcessStarted(CameraExtensionSessionImpl.this, this.mClientRequest);
        }

        public /* synthetic */ void lambda$onCaptureCompleted$5$CameraExtensionSessionImpl$RepeatingRequestHandler() {
            this.mCallbacks.onCaptureFailed(CameraExtensionSessionImpl.this, this.mClientRequest);
        }

        private void resumeInternalRepeatingRequest(boolean internal) {
            try {
                if (internal) {
                    CameraExtensionSessionImpl cameraExtensionSessionImpl = CameraExtensionSessionImpl.this;
                    cameraExtensionSessionImpl.setRepeatingRequest(cameraExtensionSessionImpl.mPreviewExtender.getCaptureStage(), new RepeatingRequestHandler(null, null, null, this.mRepeatingImageCallback));
                } else {
                    CameraExtensionSessionImpl cameraExtensionSessionImpl2 = CameraExtensionSessionImpl.this;
                    cameraExtensionSessionImpl2.setRepeatingRequest(cameraExtensionSessionImpl2.mPreviewExtender.getCaptureStage(), this);
                }
            } catch (CameraAccessException e) {
                Log.e(CameraExtensionSessionImpl.TAG, "Failed to resume internal repeating request!");
            } catch (RemoteException e2) {
                Log.e(CameraExtensionSessionImpl.TAG, "Failed to resume internal repeating request, extension service fails to respond!");
            } catch (IllegalStateException e3) {
                Log.w(CameraExtensionSessionImpl.TAG, "Failed to resume internal repeating request!");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Long calculatePruneThreshold(LongSparseArray<Pair<Image, TotalCaptureResult>> previewMap) {
            long oldestTimestamp = Long.MAX_VALUE;
            for (int idx = 0; idx < previewMap.size(); idx++) {
                Pair<Image, TotalCaptureResult> entry = previewMap.valueAt(idx);
                long timestamp = previewMap.keyAt(idx);
                if (entry.first != null && timestamp < oldestTimestamp) {
                    oldestTimestamp = timestamp;
                }
            }
            return Long.valueOf(oldestTimestamp == Long.MAX_VALUE ? 0L : oldestTimestamp);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void discardPendingRepeatingResults(int idx, LongSparseArray<Pair<Image, TotalCaptureResult>> previewMap, boolean notifyCurrentIndex) {
            if (idx >= 0) {
                for (int i = idx; i >= 0; i--) {
                    if (previewMap.valueAt(i).first != null) {
                        previewMap.valueAt(i).first.close();
                    } else if (this.mClientNotificationsEnabled && (i != idx || notifyCurrentIndex)) {
                        Log.w(CameraExtensionSessionImpl.TAG, "Preview frame drop with timestamp: " + previewMap.keyAt(i));
                        long ident = Binder.clearCallingIdentity();
                        try {
                            this.mExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.CameraExtensionSessionImpl$RepeatingRequestHandler$$ExternalSyntheticLambda0
                                @Override // java.lang.Runnable
                                public final void run() {
                                    CameraExtensionSessionImpl.RepeatingRequestHandler.this.lambda$discardPendingRepeatingResults$6$CameraExtensionSessionImpl$RepeatingRequestHandler();
                                }
                            });
                        } finally {
                            Binder.restoreCallingIdentity(ident);
                        }
                    }
                    previewMap.removeAt(i);
                }
            }
        }

        public /* synthetic */ void lambda$discardPendingRepeatingResults$6$CameraExtensionSessionImpl$RepeatingRequestHandler() {
            this.mCallbacks.onCaptureFailed(CameraExtensionSessionImpl.this, this.mClientRequest);
        }

        /* loaded from: classes.dex */
        private class ImageForwardCallback implements OnImageAvailableListener {
            private final ImageWriter mOutputWriter;

            public ImageForwardCallback(ImageWriter imageWriter) {
                this.mOutputWriter = imageWriter;
            }

            @Override // android.hardware.camera2.impl.CameraExtensionSessionImpl.OnImageAvailableListener
            public void onImageAvailable(ImageReader reader, Image img) {
                if (img == null) {
                    Log.e(CameraExtensionSessionImpl.TAG, "Invalid image!");
                    return;
                }
                try {
                    this.mOutputWriter.queueInputImage(img);
                } catch (IllegalStateException e) {
                    Log.w(CameraExtensionSessionImpl.TAG, "Output surface likely abandoned, dropping buffer!");
                    img.close();
                }
            }
        }

        /* loaded from: classes.dex */
        private class ImageProcessCallback implements OnImageAvailableListener {
            private ImageProcessCallback() {
            }

            /* JADX WARN: Finally extract failed */
            @Override // android.hardware.camera2.impl.CameraExtensionSessionImpl.OnImageAvailableListener
            public void onImageAvailable(ImageReader reader, Image img) {
                if (RepeatingRequestHandler.this.mPendingResultMap.size() + 1 >= 3) {
                    RepeatingRequestHandler repeatingRequestHandler = RepeatingRequestHandler.this;
                    LongSparseArray longSparseArray = repeatingRequestHandler.mPendingResultMap;
                    RepeatingRequestHandler repeatingRequestHandler2 = RepeatingRequestHandler.this;
                    repeatingRequestHandler.discardPendingRepeatingResults(longSparseArray.indexOfKey(repeatingRequestHandler2.calculatePruneThreshold(repeatingRequestHandler2.mPendingResultMap).longValue()), RepeatingRequestHandler.this.mPendingResultMap, true);
                }
                if (img == null) {
                    Log.e(CameraExtensionSessionImpl.TAG, "Invalid preview buffer!");
                    return;
                }
                try {
                    reader.detachImage(img);
                    long timestamp = img.getTimestamp();
                    int idx = RepeatingRequestHandler.this.mPendingResultMap.indexOfKey(timestamp);
                    if (idx >= 0) {
                        boolean processStatus = true;
                        ParcelImage parcelImage = CameraExtensionSessionImpl.initializeParcelImage(img);
                        try {
                            try {
                                CameraExtensionSessionImpl.this.mPreviewImageProcessor.process(parcelImage, (TotalCaptureResult) ((Pair) RepeatingRequestHandler.this.mPendingResultMap.get(timestamp)).second);
                            } catch (RemoteException e) {
                                processStatus = false;
                                Log.e(CameraExtensionSessionImpl.TAG, "Extension service does not respond during processing, dropping frame!");
                            }
                            parcelImage.buffer.close();
                            img.close();
                            RepeatingRequestHandler repeatingRequestHandler3 = RepeatingRequestHandler.this;
                            repeatingRequestHandler3.discardPendingRepeatingResults(idx, repeatingRequestHandler3.mPendingResultMap, false);
                            if (RepeatingRequestHandler.this.mClientNotificationsEnabled) {
                                long ident = Binder.clearCallingIdentity();
                                try {
                                    if (processStatus) {
                                        RepeatingRequestHandler.this.mExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.CameraExtensionSessionImpl$RepeatingRequestHandler$ImageProcessCallback$$ExternalSyntheticLambda0
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                CameraExtensionSessionImpl.RepeatingRequestHandler.ImageProcessCallback.this.lambda$onImageAvailable$0$CameraExtensionSessionImpl$RepeatingRequestHandler$ImageProcessCallback();
                                            }
                                        });
                                    } else {
                                        RepeatingRequestHandler.this.mExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.CameraExtensionSessionImpl$RepeatingRequestHandler$ImageProcessCallback$$ExternalSyntheticLambda1
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                CameraExtensionSessionImpl.RepeatingRequestHandler.ImageProcessCallback.this.lambda$onImageAvailable$1$CameraExtensionSessionImpl$RepeatingRequestHandler$ImageProcessCallback();
                                            }
                                        });
                                    }
                                } finally {
                                    Binder.restoreCallingIdentity(ident);
                                }
                            }
                        } catch (Throwable th) {
                            parcelImage.buffer.close();
                            img.close();
                            throw th;
                        }
                    } else {
                        RepeatingRequestHandler.this.mPendingResultMap.put(timestamp, new Pair(img, null));
                    }
                } catch (Exception e2) {
                    Log.e(CameraExtensionSessionImpl.TAG, "Failed to detach image!");
                    img.close();
                }
            }

            public /* synthetic */ void lambda$onImageAvailable$0$CameraExtensionSessionImpl$RepeatingRequestHandler$ImageProcessCallback() {
                RepeatingRequestHandler.this.mCallbacks.onCaptureProcessStarted(CameraExtensionSessionImpl.this, RepeatingRequestHandler.this.mClientRequest);
            }

            public /* synthetic */ void lambda$onImageAvailable$1$CameraExtensionSessionImpl$RepeatingRequestHandler$ImageProcessCallback() {
                RepeatingRequestHandler.this.mCallbacks.onCaptureFailed(CameraExtensionSessionImpl.this, RepeatingRequestHandler.this.mClientRequest);
            }
        }
    }

    private static Size findSmallestAspectMatchedSize(List<Size> sizes, Size arSize) {
        if (arSize.getHeight() != 0) {
            float targetAR = arSize.getWidth() / arSize.getHeight();
            Size ret = null;
            Size fallbackSize = null;
            for (Size sz : sizes) {
                if (fallbackSize == null) {
                    fallbackSize = sz;
                }
                if (sz.getHeight() > 0 && (ret == null || ret.getWidth() * ret.getHeight() < sz.getWidth() * sz.getHeight())) {
                    float currentAR = sz.getWidth() / sz.getHeight();
                    if (Math.abs(currentAR - targetAR) <= 0.01f) {
                        ret = sz;
                    }
                }
            }
            if (ret != null) {
                return ret;
            }
            Log.e(TAG, "AR matched size not found returning first size in list");
            return fallbackSize;
        }
        throw new IllegalArgumentException("Invalid input aspect ratio");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ParcelImage initializeParcelImage(Image img) {
        ParcelImage parcelImage = new ParcelImage();
        parcelImage.buffer = img.getHardwareBuffer();
        if (img.getFenceFd() >= 0) {
            try {
                parcelImage.fence = ParcelFileDescriptor.fromFd(img.getFenceFd());
            } catch (IOException e) {
                Log.e(TAG, "Failed to parcel buffer fence!");
            }
        }
        parcelImage.width = img.getWidth();
        parcelImage.height = img.getHeight();
        parcelImage.format = img.getFormat();
        parcelImage.timestamp = img.getTimestamp();
        parcelImage.transform = img.getTransform();
        parcelImage.scalingMode = img.getScalingMode();
        parcelImage.planeCount = img.getPlaneCount();
        parcelImage.crop = img.getCropRect();
        return parcelImage;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<CaptureBundle> initializeParcelable(HashMap<Integer, Pair<Image, TotalCaptureResult>> captureMap, Integer jpegOrientation, Byte jpegQuality) {
        ArrayList<CaptureBundle> ret = new ArrayList<>();
        for (Integer stagetId : captureMap.keySet()) {
            Pair<Image, TotalCaptureResult> entry = captureMap.get(stagetId);
            CaptureBundle bundle = new CaptureBundle();
            bundle.stage = stagetId.intValue();
            bundle.captureImage = initializeParcelImage(entry.first);
            bundle.sequenceId = entry.second.getSequenceId();
            bundle.captureResult = entry.second.getNativeMetadata();
            if (jpegOrientation != null) {
                bundle.captureResult.set((CaptureResult.Key<CaptureResult.Key<Integer>>) CaptureResult.JPEG_ORIENTATION, (CaptureResult.Key<Integer>) jpegOrientation);
            }
            if (jpegQuality != null) {
                bundle.captureResult.set((CaptureResult.Key<CaptureResult.Key<Byte>>) CaptureResult.JPEG_QUALITY, (CaptureResult.Key<Byte>) jpegQuality);
            }
            ret.add(bundle);
        }
        return ret;
    }
}
