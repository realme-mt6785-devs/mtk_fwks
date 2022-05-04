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
import android.hardware.camera2.extension.CameraOutputConfig;
import android.hardware.camera2.extension.CameraSessionConfig;
import android.hardware.camera2.extension.IAdvancedExtenderImpl;
import android.hardware.camera2.extension.ICaptureCallback;
import android.hardware.camera2.extension.IImageProcessorImpl;
import android.hardware.camera2.extension.IInitializeSessionCallback;
import android.hardware.camera2.extension.IRequestCallback;
import android.hardware.camera2.extension.IRequestProcessorImpl;
import android.hardware.camera2.extension.ISessionProcessorImpl;
import android.hardware.camera2.extension.OutputConfigId;
import android.hardware.camera2.extension.OutputSurface;
import android.hardware.camera2.extension.ParcelCaptureResult;
import android.hardware.camera2.extension.ParcelImage;
import android.hardware.camera2.extension.ParcelTotalCaptureResult;
import android.hardware.camera2.extension.Request;
import android.hardware.camera2.impl.CameraAdvancedExtensionSessionImpl;
import android.hardware.camera2.impl.CameraExtensionUtils;
import android.hardware.camera2.params.ExtensionSessionConfiguration;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.params.SessionConfiguration;
import android.hardware.camera2.utils.SurfaceUtils;
import android.media.Image;
import android.media.ImageReader;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public final class CameraAdvancedExtensionSessionImpl extends CameraExtensionSession {
    private static final String TAG = "CameraAdvancedExtensionSessionImpl";
    private final IAdvancedExtenderImpl mAdvancedExtender;
    private final CameraExtensionSession.StateCallback mCallbacks;
    private final CameraDevice mCameraDevice;
    private Surface mClientCaptureSurface;
    private Surface mClientRepeatingRequestSurface;
    private final Executor mExecutor;
    private final long mExtensionClientId;
    private final Handler mHandler;
    private final HandlerThread mHandlerThread;
    private final HashMap<Surface, CameraOutputConfig> mCameraConfigMap = new HashMap<>();
    private final HashMap<Integer, ImageReader> mReaderMap = new HashMap<>();
    private final RequestProcessor mRequestProcessor = new RequestProcessor();
    private CameraCaptureSession mCaptureSession = null;
    private ISessionProcessorImpl mSessionProcessor = null;
    final Object mInterfaceLock = new Object();
    private boolean mInitialized = false;
    private final InitializeSessionHandler mInitializeHandler = new InitializeSessionHandler();

    public static CameraAdvancedExtensionSessionImpl createCameraAdvancedExtensionSession(CameraDevice cameraDevice, Context ctx, ExtensionSessionConfiguration config) throws CameraAccessException, RemoteException {
        int[] iArr;
        int suitableSurfaceCount;
        long clientId = CameraExtensionCharacteristics.registerClient(ctx);
        if (clientId >= 0) {
            String cameraId = cameraDevice.getId();
            CameraManager manager = (CameraManager) ctx.getSystemService(CameraManager.class);
            CameraCharacteristics chars = manager.getCameraCharacteristics(cameraId);
            CameraExtensionCharacteristics extensionChars = new CameraExtensionCharacteristics(ctx, cameraId, chars);
            if (!CameraExtensionCharacteristics.isExtensionSupported(cameraDevice.getId(), config.getExtension(), chars)) {
                throw new UnsupportedOperationException("Unsupported extension type: " + config.getExtension());
            } else if (config.getOutputConfigurations().isEmpty() || config.getOutputConfigurations().size() > 2) {
                throw new IllegalArgumentException("Unexpected amount of output surfaces, received: " + config.getOutputConfigurations().size() + " expected <= 2");
            } else {
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
                    IAdvancedExtenderImpl extender = CameraExtensionCharacteristics.initializeAdvancedExtension(config.getExtension());
                    extender.init(cameraId);
                    CameraAdvancedExtensionSessionImpl ret = new CameraAdvancedExtensionSessionImpl(clientId, extender, cameraDevice, repeatingRequestSurface, burstCaptureSurface, config.getStateCallback(), config.getExecutor());
                    ret.initialize();
                    return ret;
                }
                throw new IllegalArgumentException("One or more unsupported output surfaces found!");
            }
        } else {
            throw new UnsupportedOperationException("Unsupported extension!");
        }
    }

    private CameraAdvancedExtensionSessionImpl(long extensionClientId, IAdvancedExtenderImpl extender, CameraDevice cameraDevice, Surface repeatingRequestSurface, Surface burstCaptureSurface, CameraExtensionSession.StateCallback callback, Executor executor) {
        this.mExtensionClientId = extensionClientId;
        this.mAdvancedExtender = extender;
        this.mCameraDevice = cameraDevice;
        this.mCallbacks = callback;
        this.mExecutor = executor;
        this.mClientRepeatingRequestSurface = repeatingRequestSurface;
        this.mClientCaptureSurface = burstCaptureSurface;
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper());
    }

    public synchronized void initialize() throws CameraAccessException, RemoteException {
        OutputConfiguration cameraOutput;
        if (this.mInitialized) {
            Log.d(TAG, "Session already initialized");
            return;
        }
        OutputSurface previewSurface = initializeParcelable(this.mClientRepeatingRequestSurface);
        OutputSurface captureSurface = initializeParcelable(this.mClientCaptureSurface);
        ISessionProcessorImpl sessionProcessor = this.mAdvancedExtender.getSessionProcessor();
        this.mSessionProcessor = sessionProcessor;
        CameraSessionConfig sessionConfig = sessionProcessor.initSession(this.mCameraDevice.getId(), previewSurface, captureSurface);
        List<CameraOutputConfig> outputConfigs = sessionConfig.outputConfigs;
        HashMap<Integer, OutputConfiguration> cameraOutputs = new HashMap<>();
        for (CameraOutputConfig output : outputConfigs) {
            switch (output.type) {
                case 0:
                    if (output.surface != null) {
                        cameraOutput = new OutputConfiguration(output.surfaceGroupId, output.surface);
                        break;
                    } else {
                        Log.w(TAG, "Unsupported client output id: " + output.outputId.id + ", skipping!");
                        continue;
                    }
                case 1:
                    if (output.imageFormat != 0 && output.size.width > 0 && output.size.height > 0) {
                        ImageReader reader = ImageReader.newInstance(output.size.width, output.size.height, output.imageFormat, output.capacity);
                        this.mReaderMap.put(Integer.valueOf(output.outputId.id), reader);
                        cameraOutput = new OutputConfiguration(output.surfaceGroupId, reader.getSurface());
                        break;
                    }
                    Log.w(TAG, "Unsupported client output id: " + output.outputId.id + ", skipping!");
                    continue;
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported output config type: " + output.type);
            }
            cameraOutput.setPhysicalCameraId(output.physicalCameraId);
            cameraOutputs.put(Integer.valueOf(output.outputId.id), cameraOutput);
        }
        ArrayList<OutputConfiguration> outputList = new ArrayList<>();
        for (CameraOutputConfig output2 : outputConfigs) {
            if (cameraOutputs.containsKey(Integer.valueOf(output2.outputId.id))) {
                OutputConfiguration outConfig = cameraOutputs.get(Integer.valueOf(output2.outputId.id));
                if (output2.surfaceSharingOutputConfigs != null && !output2.surfaceSharingOutputConfigs.isEmpty()) {
                    outConfig.enableSurfaceSharing();
                    for (OutputConfigId outputId : output2.surfaceSharingOutputConfigs) {
                        outConfig.addSurface(cameraOutputs.get(Integer.valueOf(outputId.id)).getSurface());
                        cameraOutputs.remove(Integer.valueOf(outputId.id));
                    }
                }
                outputList.add(outConfig);
                this.mCameraConfigMap.put(outConfig.getSurface(), output2);
            }
        }
        SessionConfiguration sessionConfiguration = new SessionConfiguration(0, outputList, new CameraExtensionUtils.HandlerExecutor(this.mHandler), new SessionStateHandler());
        if (sessionConfig.sessionParameter != null && !sessionConfig.sessionParameter.isEmpty()) {
            CaptureRequest.Builder requestBuilder = this.mCameraDevice.createCaptureRequest(sessionConfig.sessionTemplateId);
            CaptureRequest sessionRequest = requestBuilder.build();
            CameraMetadataNative.update(sessionRequest.getNativeMetadata(), sessionConfig.sessionParameter);
            sessionConfiguration.setSessionParameters(sessionRequest);
        }
        this.mCameraDevice.createCaptureSession(sessionConfiguration);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ParcelCaptureResult initializeParcelable(CaptureResult result) {
        ParcelCaptureResult ret = new ParcelCaptureResult();
        ret.cameraId = result.getCameraId();
        ret.results = result.getNativeMetadata();
        ret.parent = result.getRequest();
        ret.sequenceId = result.getSequenceId();
        ret.frameNumber = result.getFrameNumber();
        return ret;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ParcelTotalCaptureResult initializeParcelable(TotalCaptureResult totalResult) {
        ParcelTotalCaptureResult ret = new ParcelTotalCaptureResult();
        ret.logicalCameraId = totalResult.getCameraId();
        ret.results = totalResult.getNativeMetadata();
        ret.parent = totalResult.getRequest();
        ret.sequenceId = totalResult.getSequenceId();
        ret.frameNumber = totalResult.getFrameNumber();
        ret.sessionId = totalResult.getSessionId();
        ret.partials = new ArrayList(totalResult.getPartialResults().size());
        for (CaptureResult partial : totalResult.getPartialResults()) {
            ret.partials.add(initializeParcelable(partial));
        }
        Map<String, TotalCaptureResult> physicalResults = totalResult.getPhysicalCameraTotalResults();
        ret.physicalResult = new ArrayList(physicalResults.size());
        for (TotalCaptureResult physicalResult : physicalResults.values()) {
            ret.physicalResult.add(new PhysicalCaptureResultInfo(physicalResult.getCameraId(), physicalResult.getNativeMetadata()));
        }
        return ret;
    }

    private static OutputSurface initializeParcelable(Surface s) {
        OutputSurface ret = new OutputSurface();
        if (s != null) {
            ret.surface = s;
            ret.size = new android.hardware.camera2.extension.Size();
            Size surfaceSize = SurfaceUtils.getSurfaceSize(s);
            ret.size.width = surfaceSize.getWidth();
            ret.size.height = surfaceSize.getHeight();
            ret.imageFormat = SurfaceUtils.getSurfaceFormat(s);
        } else {
            ret.surface = null;
            ret.size = new android.hardware.camera2.extension.Size();
            ret.size.width = -1;
            ret.size.height = -1;
            ret.imageFormat = 0;
        }
        return ret;
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
        int seqId;
        synchronized (this.mInterfaceLock) {
            if (this.mInitialized) {
                Surface surface = this.mClientRepeatingRequestSurface;
                if (surface == null) {
                    throw new IllegalArgumentException("No registered preview surface");
                } else if (!request.containsTarget(surface) || request.getTargets().size() != 1) {
                    throw new IllegalArgumentException("Invalid repeating request output target!");
                } else {
                    try {
                        seqId = this.mSessionProcessor.startRepeating(new RequestCallbackHandler(request, executor, listener));
                    } catch (RemoteException e) {
                        throw new CameraAccessException(3, "Failed to enable repeating request, extension service failed to respond!");
                    }
                }
            } else {
                throw new IllegalStateException("Uninitialized component");
            }
        }
        return seqId;
    }

    @Override // android.hardware.camera2.CameraExtensionSession
    public int capture(CaptureRequest request, Executor executor, CameraExtensionSession.ExtensionCaptureCallback listener) throws CameraAccessException {
        int seqId;
        synchronized (this.mInterfaceLock) {
            if (this.mInitialized) {
                Surface surface = this.mClientCaptureSurface;
                if (surface == null) {
                    throw new IllegalArgumentException("No output surface registered for single requests!");
                } else if (!request.containsTarget(surface) || request.getTargets().size() != 1) {
                    throw new IllegalArgumentException("Invalid single capture output target!");
                } else {
                    try {
                        Integer jpegRotation = (Integer) request.get(CaptureRequest.JPEG_ORIENTATION);
                        if (jpegRotation == null) {
                            jpegRotation = 0;
                        }
                        Byte jpegQuality = (Byte) request.get(CaptureRequest.JPEG_QUALITY);
                        if (jpegQuality == null) {
                            jpegQuality = (byte) 100;
                        }
                        seqId = this.mSessionProcessor.startCapture(new RequestCallbackHandler(request, executor, listener), jpegRotation.intValue(), jpegQuality.byteValue());
                    } catch (RemoteException e) {
                        throw new CameraAccessException(3, "Failed to submit capture request, extension service failed to respond!");
                    }
                }
            } else {
                throw new IllegalStateException("Uninitialized component");
            }
        }
        return seqId;
    }

    @Override // android.hardware.camera2.CameraExtensionSession
    public void stopRepeating() throws CameraAccessException {
        synchronized (this.mInterfaceLock) {
            if (this.mInitialized) {
                this.mCaptureSession.stopRepeating();
                try {
                    this.mSessionProcessor.stopRepeating();
                } catch (RemoteException e) {
                    throw new CameraAccessException(3, "Failed to notify about the end of repeating request, extension service failed to respond!");
                }
            } else {
                throw new IllegalStateException("Uninitialized component");
            }
        }
    }

    @Override // android.hardware.camera2.CameraExtensionSession, java.lang.AutoCloseable
    public void close() throws CameraAccessException {
        synchronized (this.mInterfaceLock) {
            if (this.mInitialized) {
                try {
                    this.mCaptureSession.stopRepeating();
                    this.mSessionProcessor.stopRepeating();
                    this.mSessionProcessor.onCaptureSessionEnd();
                } catch (RemoteException e) {
                    Log.e(TAG, "Failed to stop the repeating request or end the session, , extension service does not respond!");
                }
                this.mCaptureSession.close();
            }
        }
    }

    public void release(boolean skipCloseNotification) {
        boolean notifyClose = false;
        synchronized (this.mInterfaceLock) {
            this.mHandlerThread.quitSafely();
            ISessionProcessorImpl iSessionProcessorImpl = this.mSessionProcessor;
            if (iSessionProcessorImpl != null) {
                try {
                    iSessionProcessorImpl.deInitSession();
                } catch (RemoteException e) {
                    Log.e(TAG, "Failed to de-initialize session processor, extension service does not respond!");
                }
                this.mSessionProcessor = null;
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
            for (ImageReader reader : this.mReaderMap.values()) {
                reader.close();
            }
            this.mReaderMap.clear();
            this.mClientRepeatingRequestSurface = null;
            this.mClientCaptureSurface = null;
        }
        if (notifyClose && !skipCloseNotification) {
            long ident = Binder.clearCallingIdentity();
            try {
                this.mExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.CameraAdvancedExtensionSessionImpl$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        CameraAdvancedExtensionSessionImpl.this.lambda$release$0$CameraAdvancedExtensionSessionImpl();
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(ident);
            }
        }
    }

    public /* synthetic */ void lambda$release$0$CameraAdvancedExtensionSessionImpl() {
        this.mCallbacks.onClosed(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyConfigurationFailure() {
        synchronized (this.mInterfaceLock) {
            if (!this.mInitialized) {
                release(true);
                long ident = Binder.clearCallingIdentity();
                try {
                    this.mExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.CameraAdvancedExtensionSessionImpl$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            CameraAdvancedExtensionSessionImpl.this.lambda$notifyConfigurationFailure$1$CameraAdvancedExtensionSessionImpl();
                        }
                    });
                } finally {
                    Binder.restoreCallingIdentity(ident);
                }
            }
        }
    }

    public /* synthetic */ void lambda$notifyConfigurationFailure$1$CameraAdvancedExtensionSessionImpl() {
        this.mCallbacks.onConfigureFailed(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class SessionStateHandler extends CameraCaptureSession.StateCallback {
        private SessionStateHandler() {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onClosed(CameraCaptureSession session) {
            CameraAdvancedExtensionSessionImpl.this.release(false);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigureFailed(CameraCaptureSession session) {
            CameraAdvancedExtensionSessionImpl.this.notifyConfigurationFailure();
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigured(CameraCaptureSession session) {
            synchronized (CameraAdvancedExtensionSessionImpl.this.mInterfaceLock) {
                CameraAdvancedExtensionSessionImpl.this.mCaptureSession = session;
                try {
                    CameraExtensionCharacteristics.initializeSession(CameraAdvancedExtensionSessionImpl.this.mInitializeHandler);
                } catch (RemoteException e) {
                    Log.e(CameraAdvancedExtensionSessionImpl.TAG, "Failed to initialize session! Extension service does not respond!");
                    CameraAdvancedExtensionSessionImpl.this.notifyConfigurationFailure();
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
            synchronized (CameraAdvancedExtensionSessionImpl.this.mInterfaceLock) {
                try {
                    CameraAdvancedExtensionSessionImpl.this.mSessionProcessor.onCaptureSessionStart(CameraAdvancedExtensionSessionImpl.this.mRequestProcessor);
                    CameraAdvancedExtensionSessionImpl.this.mInitialized = true;
                } catch (RemoteException e) {
                    Log.e(CameraAdvancedExtensionSessionImpl.TAG, "Failed to start capture session, extension service does not respond!");
                    status = false;
                    CameraAdvancedExtensionSessionImpl.this.mCaptureSession.close();
                }
            }
            if (status) {
                long ident = Binder.clearCallingIdentity();
                try {
                    CameraAdvancedExtensionSessionImpl.this.mExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.CameraAdvancedExtensionSessionImpl$InitializeSessionHandler$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            CameraAdvancedExtensionSessionImpl.InitializeSessionHandler.this.lambda$onSuccess$0$CameraAdvancedExtensionSessionImpl$InitializeSessionHandler();
                        }
                    });
                } finally {
                    Binder.restoreCallingIdentity(ident);
                }
            } else {
                CameraAdvancedExtensionSessionImpl.this.notifyConfigurationFailure();
            }
        }

        public /* synthetic */ void lambda$onSuccess$0$CameraAdvancedExtensionSessionImpl$InitializeSessionHandler() {
            CameraAdvancedExtensionSessionImpl.this.mCallbacks.onConfigured(CameraAdvancedExtensionSessionImpl.this);
        }

        @Override // android.hardware.camera2.extension.IInitializeSessionCallback
        public void onFailure() {
            CameraAdvancedExtensionSessionImpl.this.mCaptureSession.close();
            Log.e(CameraAdvancedExtensionSessionImpl.TAG, "Failed to initialize proxy service session! This can happen when trying to configure multiple concurrent extension sessions!");
            CameraAdvancedExtensionSessionImpl.this.notifyConfigurationFailure();
        }
    }

    /* loaded from: classes.dex */
    private final class RequestCallbackHandler extends ICaptureCallback.Stub {
        private final CameraExtensionSession.ExtensionCaptureCallback mClientCallbacks;
        private final Executor mClientExecutor;
        private final CaptureRequest mClientRequest;

        private RequestCallbackHandler(CaptureRequest clientRequest, Executor clientExecutor, CameraExtensionSession.ExtensionCaptureCallback clientCallbacks) {
            this.mClientRequest = clientRequest;
            this.mClientExecutor = clientExecutor;
            this.mClientCallbacks = clientCallbacks;
        }

        @Override // android.hardware.camera2.extension.ICaptureCallback
        public void onCaptureStarted(int captureSequenceId, final long timestamp) {
            long ident = Binder.clearCallingIdentity();
            try {
                this.mClientExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.CameraAdvancedExtensionSessionImpl$RequestCallbackHandler$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        CameraAdvancedExtensionSessionImpl.RequestCallbackHandler.this.lambda$onCaptureStarted$0$CameraAdvancedExtensionSessionImpl$RequestCallbackHandler(timestamp);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(ident);
            }
        }

        public /* synthetic */ void lambda$onCaptureStarted$0$CameraAdvancedExtensionSessionImpl$RequestCallbackHandler(long timestamp) {
            this.mClientCallbacks.onCaptureStarted(CameraAdvancedExtensionSessionImpl.this, this.mClientRequest, timestamp);
        }

        @Override // android.hardware.camera2.extension.ICaptureCallback
        public void onCaptureProcessStarted(int captureSequenceId) {
            long ident = Binder.clearCallingIdentity();
            try {
                this.mClientExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.CameraAdvancedExtensionSessionImpl$RequestCallbackHandler$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        CameraAdvancedExtensionSessionImpl.RequestCallbackHandler.this.lambda$onCaptureProcessStarted$1$CameraAdvancedExtensionSessionImpl$RequestCallbackHandler();
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(ident);
            }
        }

        public /* synthetic */ void lambda$onCaptureProcessStarted$1$CameraAdvancedExtensionSessionImpl$RequestCallbackHandler() {
            this.mClientCallbacks.onCaptureProcessStarted(CameraAdvancedExtensionSessionImpl.this, this.mClientRequest);
        }

        @Override // android.hardware.camera2.extension.ICaptureCallback
        public void onCaptureFailed(int captureSequenceId) {
            long ident = Binder.clearCallingIdentity();
            try {
                this.mClientExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.CameraAdvancedExtensionSessionImpl$RequestCallbackHandler$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        CameraAdvancedExtensionSessionImpl.RequestCallbackHandler.this.lambda$onCaptureFailed$2$CameraAdvancedExtensionSessionImpl$RequestCallbackHandler();
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(ident);
            }
        }

        public /* synthetic */ void lambda$onCaptureFailed$2$CameraAdvancedExtensionSessionImpl$RequestCallbackHandler() {
            this.mClientCallbacks.onCaptureFailed(CameraAdvancedExtensionSessionImpl.this, this.mClientRequest);
        }

        @Override // android.hardware.camera2.extension.ICaptureCallback
        public void onCaptureSequenceCompleted(final int captureSequenceId) {
            long ident = Binder.clearCallingIdentity();
            try {
                this.mClientExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.CameraAdvancedExtensionSessionImpl$RequestCallbackHandler$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        CameraAdvancedExtensionSessionImpl.RequestCallbackHandler.this.lambda$onCaptureSequenceCompleted$3$CameraAdvancedExtensionSessionImpl$RequestCallbackHandler(captureSequenceId);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(ident);
            }
        }

        public /* synthetic */ void lambda$onCaptureSequenceCompleted$3$CameraAdvancedExtensionSessionImpl$RequestCallbackHandler(int captureSequenceId) {
            this.mClientCallbacks.onCaptureSequenceCompleted(CameraAdvancedExtensionSessionImpl.this, captureSequenceId);
        }

        @Override // android.hardware.camera2.extension.ICaptureCallback
        public void onCaptureSequenceAborted(final int captureSequenceId) {
            long ident = Binder.clearCallingIdentity();
            try {
                this.mClientExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.CameraAdvancedExtensionSessionImpl$RequestCallbackHandler$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        CameraAdvancedExtensionSessionImpl.RequestCallbackHandler.this.lambda$onCaptureSequenceAborted$4$CameraAdvancedExtensionSessionImpl$RequestCallbackHandler(captureSequenceId);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(ident);
            }
        }

        public /* synthetic */ void lambda$onCaptureSequenceAborted$4$CameraAdvancedExtensionSessionImpl$RequestCallbackHandler(int captureSequenceId) {
            this.mClientCallbacks.onCaptureSequenceAborted(CameraAdvancedExtensionSessionImpl.this, captureSequenceId);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class CaptureCallbackHandler extends CameraCaptureSession.CaptureCallback {
        private final IRequestCallback mCallback;

        public CaptureCallbackHandler(IRequestCallback callback) {
            this.mCallback = callback;
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureBufferLost(CameraCaptureSession session, CaptureRequest request, Surface target, long frameNumber) {
            try {
                if (request.getTag() instanceof Integer) {
                    Integer requestId = (Integer) request.getTag();
                    this.mCallback.onCaptureBufferLost(requestId.intValue(), frameNumber, ((CameraOutputConfig) CameraAdvancedExtensionSessionImpl.this.mCameraConfigMap.get(target)).outputId.id);
                } else {
                    Log.e(CameraAdvancedExtensionSessionImpl.TAG, "Invalid capture request tag!");
                }
            } catch (RemoteException e) {
                Log.e(CameraAdvancedExtensionSessionImpl.TAG, "Failed to notify lost capture buffer, extension service doesn't respond!");
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
            try {
                if (request.getTag() instanceof Integer) {
                    Integer requestId = (Integer) request.getTag();
                    this.mCallback.onCaptureCompleted(requestId.intValue(), CameraAdvancedExtensionSessionImpl.initializeParcelable(result));
                } else {
                    Log.e(CameraAdvancedExtensionSessionImpl.TAG, "Invalid capture request tag!");
                }
            } catch (RemoteException e) {
                Log.e(CameraAdvancedExtensionSessionImpl.TAG, "Failed to notify capture result, extension service doesn't respond!");
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureFailed(CameraCaptureSession session, CaptureRequest request, CaptureFailure failure) {
            try {
                if (request.getTag() instanceof Integer) {
                    Integer requestId = (Integer) request.getTag();
                    android.hardware.camera2.extension.CaptureFailure captureFailure = new android.hardware.camera2.extension.CaptureFailure();
                    captureFailure.request = request;
                    captureFailure.reason = failure.getReason();
                    captureFailure.errorPhysicalCameraId = failure.getPhysicalCameraId();
                    captureFailure.frameNumber = failure.getFrameNumber();
                    captureFailure.sequenceId = failure.getSequenceId();
                    captureFailure.dropped = !failure.wasImageCaptured();
                    this.mCallback.onCaptureFailed(requestId.intValue(), captureFailure);
                } else {
                    Log.e(CameraAdvancedExtensionSessionImpl.TAG, "Invalid capture request tag!");
                }
            } catch (RemoteException e) {
                Log.e(CameraAdvancedExtensionSessionImpl.TAG, "Failed to notify capture failure, extension service doesn't respond!");
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureProgressed(CameraCaptureSession session, CaptureRequest request, CaptureResult partialResult) {
            try {
                if (request.getTag() instanceof Integer) {
                    Integer requestId = (Integer) request.getTag();
                    this.mCallback.onCaptureProgressed(requestId.intValue(), CameraAdvancedExtensionSessionImpl.initializeParcelable(partialResult));
                } else {
                    Log.e(CameraAdvancedExtensionSessionImpl.TAG, "Invalid capture request tag!");
                }
            } catch (RemoteException e) {
                Log.e(CameraAdvancedExtensionSessionImpl.TAG, "Failed to notify capture partial result, extension service doesn't respond!");
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureSequenceAborted(CameraCaptureSession session, int sequenceId) {
            try {
                this.mCallback.onCaptureSequenceAborted(sequenceId);
            } catch (RemoteException e) {
                Log.e(CameraAdvancedExtensionSessionImpl.TAG, "Failed to notify aborted sequence, extension service doesn't respond!");
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureSequenceCompleted(CameraCaptureSession session, int sequenceId, long frameNumber) {
            try {
                this.mCallback.onCaptureSequenceCompleted(sequenceId, frameNumber);
            } catch (RemoteException e) {
                Log.e(CameraAdvancedExtensionSessionImpl.TAG, "Failed to notify sequence complete, extension service doesn't respond!");
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureStarted(CameraCaptureSession session, CaptureRequest request, long timestamp, long frameNumber) {
            try {
                if (request.getTag() instanceof Integer) {
                    Integer requestId = (Integer) request.getTag();
                    this.mCallback.onCaptureStarted(requestId.intValue(), frameNumber, timestamp);
                } else {
                    Log.e(CameraAdvancedExtensionSessionImpl.TAG, "Invalid capture request tag!");
                }
            } catch (RemoteException e) {
                Log.e(CameraAdvancedExtensionSessionImpl.TAG, "Failed to notify capture started, extension service doesn't respond!");
            }
        }
    }

    /* loaded from: classes.dex */
    private static final class ImageReaderHandler implements ImageReader.OnImageAvailableListener {
        private final IImageProcessorImpl mIImageProcessor;
        private final OutputConfigId mOutputConfigId;
        private final String mPhysicalCameraId;

        private ImageReaderHandler(int outputConfigId, IImageProcessorImpl iImageProcessor, String physicalCameraId) {
            OutputConfigId outputConfigId2 = new OutputConfigId();
            this.mOutputConfigId = outputConfigId2;
            outputConfigId2.id = outputConfigId;
            this.mIImageProcessor = iImageProcessor;
            this.mPhysicalCameraId = physicalCameraId;
        }

        @Override // android.media.ImageReader.OnImageAvailableListener
        public void onImageAvailable(ImageReader reader) {
            if (this.mIImageProcessor != null) {
                try {
                    Image img = reader.acquireNextImage();
                    if (img == null) {
                        Log.e(CameraAdvancedExtensionSessionImpl.TAG, "Invalid image!");
                        return;
                    }
                    try {
                        reader.detachImage(img);
                        ParcelImage parcelImage = new ParcelImage();
                        parcelImage.buffer = img.getHardwareBuffer();
                        if (img.getFenceFd() >= 0) {
                            try {
                                parcelImage.fence = ParcelFileDescriptor.fromFd(img.getFenceFd());
                            } catch (IOException e) {
                                Log.e(CameraAdvancedExtensionSessionImpl.TAG, "Failed to parcel buffer fence!");
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
                        try {
                            try {
                                this.mIImageProcessor.onNextImageAvailable(this.mOutputConfigId, parcelImage, this.mPhysicalCameraId);
                            } catch (RemoteException e2) {
                                Log.e(CameraAdvancedExtensionSessionImpl.TAG, "Failed to propagate image buffer on output surface id: " + this.mOutputConfigId + " extension service does not respond!");
                            }
                        } finally {
                            parcelImage.buffer.close();
                            img.close();
                        }
                    } catch (Exception e3) {
                        Log.e(CameraAdvancedExtensionSessionImpl.TAG, "Failed to detach image");
                        img.close();
                    }
                } catch (IllegalStateException e4) {
                    Log.e(CameraAdvancedExtensionSessionImpl.TAG, "Failed to acquire image, too many images pending!");
                }
            }
        }
    }

    /* loaded from: classes.dex */
    private final class RequestProcessor extends IRequestProcessorImpl.Stub {
        private RequestProcessor() {
        }

        @Override // android.hardware.camera2.extension.IRequestProcessorImpl
        public void setImageProcessor(OutputConfigId outputConfigId, IImageProcessorImpl imageProcessor) {
            synchronized (CameraAdvancedExtensionSessionImpl.this.mInterfaceLock) {
                if (CameraAdvancedExtensionSessionImpl.this.mReaderMap.containsKey(Integer.valueOf(outputConfigId.id))) {
                    ImageReader reader = (ImageReader) CameraAdvancedExtensionSessionImpl.this.mReaderMap.get(Integer.valueOf(outputConfigId.id));
                    if (CameraAdvancedExtensionSessionImpl.this.mCameraConfigMap.containsKey(reader.getSurface())) {
                        String physicalCameraId = ((CameraOutputConfig) CameraAdvancedExtensionSessionImpl.this.mCameraConfigMap.get(reader.getSurface())).physicalCameraId;
                        reader.setOnImageAvailableListener(new ImageReaderHandler(outputConfigId.id, imageProcessor, physicalCameraId), CameraAdvancedExtensionSessionImpl.this.mHandler);
                    } else {
                        Log.e(CameraAdvancedExtensionSessionImpl.TAG, "Camera output configuration for ImageReader with  config Id " + outputConfigId.id + " not found!");
                    }
                } else {
                    Log.e(CameraAdvancedExtensionSessionImpl.TAG, "ImageReader with output config id: " + outputConfigId.id + " not found!");
                }
            }
        }

        @Override // android.hardware.camera2.extension.IRequestProcessorImpl
        public int submit(Request request, IRequestCallback callback) {
            ArrayList<Request> captureList = new ArrayList<>();
            captureList.add(request);
            return submitBurst(captureList, callback);
        }

        @Override // android.hardware.camera2.extension.IRequestProcessorImpl
        public int submitBurst(List<Request> requests, IRequestCallback callback) {
            int seqId = -1;
            synchronized (CameraAdvancedExtensionSessionImpl.this.mInterfaceLock) {
                try {
                    CaptureCallbackHandler captureCallback = new CaptureCallbackHandler(callback);
                    ArrayList<CaptureRequest> captureRequests = new ArrayList<>();
                    for (Request request : requests) {
                        captureRequests.add(CameraAdvancedExtensionSessionImpl.initializeCaptureRequest(CameraAdvancedExtensionSessionImpl.this.mCameraDevice, request, CameraAdvancedExtensionSessionImpl.this.mCameraConfigMap));
                    }
                    seqId = CameraAdvancedExtensionSessionImpl.this.mCaptureSession.captureBurstRequests(captureRequests, new CameraExtensionUtils.HandlerExecutor(CameraAdvancedExtensionSessionImpl.this.mHandler), captureCallback);
                } catch (CameraAccessException e) {
                    Log.e(CameraAdvancedExtensionSessionImpl.TAG, "Failed to submit capture requests!");
                } catch (IllegalStateException e2) {
                    Log.e(CameraAdvancedExtensionSessionImpl.TAG, "Capture session closed!");
                }
            }
            return seqId;
        }

        @Override // android.hardware.camera2.extension.IRequestProcessorImpl
        public int setRepeating(Request request, IRequestCallback callback) {
            int seqId = -1;
            synchronized (CameraAdvancedExtensionSessionImpl.this.mInterfaceLock) {
                try {
                    try {
                        CaptureRequest repeatingRequest = CameraAdvancedExtensionSessionImpl.initializeCaptureRequest(CameraAdvancedExtensionSessionImpl.this.mCameraDevice, request, CameraAdvancedExtensionSessionImpl.this.mCameraConfigMap);
                        CaptureCallbackHandler captureCallback = new CaptureCallbackHandler(callback);
                        seqId = CameraAdvancedExtensionSessionImpl.this.mCaptureSession.setSingleRepeatingRequest(repeatingRequest, new CameraExtensionUtils.HandlerExecutor(CameraAdvancedExtensionSessionImpl.this.mHandler), captureCallback);
                    } catch (CameraAccessException e) {
                        Log.e(CameraAdvancedExtensionSessionImpl.TAG, "Failed to enable repeating request!");
                    }
                } catch (IllegalStateException e2) {
                    Log.e(CameraAdvancedExtensionSessionImpl.TAG, "Capture session closed!");
                }
            }
            return seqId;
        }

        @Override // android.hardware.camera2.extension.IRequestProcessorImpl
        public void abortCaptures() {
            synchronized (CameraAdvancedExtensionSessionImpl.this.mInterfaceLock) {
                try {
                    CameraAdvancedExtensionSessionImpl.this.mCaptureSession.abortCaptures();
                } catch (CameraAccessException e) {
                    Log.e(CameraAdvancedExtensionSessionImpl.TAG, "Failed during capture abort!");
                } catch (IllegalStateException e2) {
                    Log.e(CameraAdvancedExtensionSessionImpl.TAG, "Capture session closed!");
                }
            }
        }

        @Override // android.hardware.camera2.extension.IRequestProcessorImpl
        public void stopRepeating() {
            synchronized (CameraAdvancedExtensionSessionImpl.this.mInterfaceLock) {
                try {
                    CameraAdvancedExtensionSessionImpl.this.mCaptureSession.stopRepeating();
                } catch (CameraAccessException e) {
                    Log.e(CameraAdvancedExtensionSessionImpl.TAG, "Failed during repeating capture stop!");
                } catch (IllegalStateException e2) {
                    Log.e(CameraAdvancedExtensionSessionImpl.TAG, "Capture session closed!");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CaptureRequest initializeCaptureRequest(CameraDevice cameraDevice, Request request, HashMap<Surface, CameraOutputConfig> surfaceIdMap) throws CameraAccessException {
        CaptureRequest.Builder builder = cameraDevice.createCaptureRequest(request.templateId);
        for (OutputConfigId configId : request.targetOutputConfigIds) {
            boolean found = false;
            Iterator<Map.Entry<Surface, CameraOutputConfig>> it = surfaceIdMap.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<Surface, CameraOutputConfig> entry = it.next();
                if (entry.getValue().outputId.id == configId.id) {
                    builder.addTarget(entry.getKey());
                    found = true;
                    break;
                }
            }
            if (!found) {
                Log.e(TAG, "Surface with output id: " + configId.id + " not found among registered camera outputs!");
            }
        }
        builder.setTag(Integer.valueOf(request.requestId));
        CaptureRequest ret = builder.build();
        CameraMetadataNative.update(ret.getNativeMetadata(), request.parameters);
        return ret;
    }
}
