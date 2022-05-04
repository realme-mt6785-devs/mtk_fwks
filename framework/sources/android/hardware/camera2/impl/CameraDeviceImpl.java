package android.hardware.camera2.impl;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraExtensionCharacteristics;
import android.hardware.camera2.CameraOfflineSession;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.ICameraDeviceCallbacks;
import android.hardware.camera2.ICameraDeviceUser;
import android.hardware.camera2.ICameraOfflineSession;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.impl.CameraOfflineSessionImpl;
import android.hardware.camera2.params.ExtensionSessionConfiguration;
import android.hardware.camera2.params.InputConfiguration;
import android.hardware.camera2.params.MultiResolutionStreamConfigurationMap;
import android.hardware.camera2.params.MultiResolutionStreamInfo;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.params.SessionConfiguration;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.hardware.camera2.utils.SubmitInfo;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import android.os.SystemClock;
import android.util.Log;
import android.util.Range;
import android.util.Size;
import android.util.SparseArray;
import android.view.Surface;
import com.android.internal.util.function.pooled.PooledLambda;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public class CameraDeviceImpl extends CameraDevice implements IBinder.DeathRecipient {
    private static final long NANO_PER_SECOND = 1000000000;
    private static final int REQUEST_ID_NONE = -1;
    private final String TAG;
    private final int mAppTargetSdkVersion;
    private ICameraDeviceImplExt mCameraDeviceImplExt;
    private final String mCameraId;
    private final CameraCharacteristics mCharacteristics;
    private final Context mContext;
    private CameraAdvancedExtensionSessionImpl mCurrentAdvancedExtensionSession;
    private CameraExtensionSessionImpl mCurrentExtensionSession;
    private CameraCaptureSessionCore mCurrentSession;
    private final CameraDevice.StateCallback mDeviceCallback;
    private final Executor mDeviceExecutor;
    private CameraOfflineSessionImpl mOfflineSessionImpl;
    private ExecutorService mOfflineSwitchService;
    private final Map<String, CameraCharacteristics> mPhysicalIdsToChars;
    private ICameraDeviceUserWrapper mRemoteDevice;
    private int[] mRepeatingRequestTypes;
    private volatile StateCallbackKK mSessionStateCallback;
    private final int mTotalPartialCount;
    private final boolean DEBUG = false;
    final Object mInterfaceLock = new Object();
    private final CameraDeviceCallbacks mCallbacks = new CameraDeviceCallbacks();
    private final AtomicBoolean mClosing = new AtomicBoolean();
    private boolean mInError = false;
    private boolean mIdle = true;
    private SparseArray<CaptureCallbackHolder> mCaptureCallbackMap = new SparseArray<>();
    private int mRepeatingRequestId = -1;
    private AbstractMap.SimpleEntry<Integer, InputConfiguration> mConfiguredInput = new AbstractMap.SimpleEntry<>(-1, null);
    private final SparseArray<OutputConfiguration> mConfiguredOutputs = new SparseArray<>();
    private final HashSet<Integer> mOfflineSupport = new HashSet<>();
    private final List<RequestLastFrameNumbersHolder> mRequestLastFrameNumbersList = new ArrayList();
    private FrameNumberTracker mFrameNumberTracker = new FrameNumberTracker();
    private int mNextSessionId = 0;
    private final Runnable mCallOnOpened = new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.1
        @Override // java.lang.Runnable
        public void run() {
            synchronized (CameraDeviceImpl.this.mInterfaceLock) {
                if (CameraDeviceImpl.this.mRemoteDevice != null) {
                    StateCallbackKK sessionCallback = CameraDeviceImpl.this.mSessionStateCallback;
                    if (sessionCallback != null) {
                        sessionCallback.onOpened(CameraDeviceImpl.this);
                    }
                    CameraDeviceImpl.this.mDeviceCallback.onOpened(CameraDeviceImpl.this);
                    CameraDeviceImpl.this.mCameraDeviceImplExt.extendsetInfo(CameraDeviceImpl.this.mCameraId, System.currentTimeMillis());
                }
            }
        }
    };
    private final Runnable mCallOnUnconfigured = new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.2
        @Override // java.lang.Runnable
        public void run() {
            synchronized (CameraDeviceImpl.this.mInterfaceLock) {
                if (CameraDeviceImpl.this.mRemoteDevice != null) {
                    StateCallbackKK sessionCallback = CameraDeviceImpl.this.mSessionStateCallback;
                    if (sessionCallback != null) {
                        sessionCallback.onUnconfigured(CameraDeviceImpl.this);
                    }
                }
            }
        }
    };
    private final Runnable mCallOnActive = new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.3
        @Override // java.lang.Runnable
        public void run() {
            synchronized (CameraDeviceImpl.this.mInterfaceLock) {
                if (CameraDeviceImpl.this.mRemoteDevice != null) {
                    StateCallbackKK sessionCallback = CameraDeviceImpl.this.mSessionStateCallback;
                    if (sessionCallback != null) {
                        sessionCallback.onActive(CameraDeviceImpl.this);
                    }
                }
            }
        }
    };
    private final Runnable mCallOnBusy = new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.4
        @Override // java.lang.Runnable
        public void run() {
            synchronized (CameraDeviceImpl.this.mInterfaceLock) {
                if (CameraDeviceImpl.this.mRemoteDevice != null) {
                    StateCallbackKK sessionCallback = CameraDeviceImpl.this.mSessionStateCallback;
                    if (sessionCallback != null) {
                        sessionCallback.onBusy(CameraDeviceImpl.this);
                    }
                }
            }
        }
    };
    private final Runnable mCallOnClosed = new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.5
        private boolean mClosedOnce = false;

        @Override // java.lang.Runnable
        public void run() {
            StateCallbackKK sessionCallback;
            if (!this.mClosedOnce) {
                synchronized (CameraDeviceImpl.this.mInterfaceLock) {
                    sessionCallback = CameraDeviceImpl.this.mSessionStateCallback;
                }
                if (sessionCallback != null) {
                    sessionCallback.onClosed(CameraDeviceImpl.this);
                }
                CameraDeviceImpl.this.mDeviceCallback.onClosed(CameraDeviceImpl.this);
                this.mClosedOnce = true;
                long time = System.currentTimeMillis();
                CameraDeviceImpl.this.mCameraDeviceImplExt.extendsetCloseInfo(CameraDeviceImpl.this.mCameraId, time);
                return;
            }
            throw new AssertionError("Don't post #onClosed more than once");
        }
    };
    private final Runnable mCallOnIdle = new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.6
        @Override // java.lang.Runnable
        public void run() {
            synchronized (CameraDeviceImpl.this.mInterfaceLock) {
                if (CameraDeviceImpl.this.mRemoteDevice != null) {
                    StateCallbackKK sessionCallback = CameraDeviceImpl.this.mSessionStateCallback;
                    if (sessionCallback != null) {
                        sessionCallback.onIdle(CameraDeviceImpl.this);
                    }
                }
            }
        }
    };
    private final Runnable mCallOnDisconnected = new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.7
        @Override // java.lang.Runnable
        public void run() {
            synchronized (CameraDeviceImpl.this.mInterfaceLock) {
                if (CameraDeviceImpl.this.mRemoteDevice != null) {
                    StateCallbackKK sessionCallback = CameraDeviceImpl.this.mSessionStateCallback;
                    if (sessionCallback != null) {
                        sessionCallback.onDisconnected(CameraDeviceImpl.this);
                    }
                    CameraDeviceImpl.this.mDeviceCallback.onDisconnected(CameraDeviceImpl.this);
                }
            }
        }
    };

    public CameraDeviceImpl(String cameraId, CameraDevice.StateCallback callback, Executor executor, CameraCharacteristics characteristics, Map<String, CameraCharacteristics> physicalIdsToChars, int appTargetSdkVersion, Context ctx) {
        if (cameraId == null || callback == null || executor == null || characteristics == null) {
            throw new IllegalArgumentException("Null argument given");
        }
        this.mCameraId = cameraId;
        this.mDeviceCallback = callback;
        this.mDeviceExecutor = executor;
        this.mCharacteristics = characteristics;
        this.mPhysicalIdsToChars = physicalIdsToChars;
        this.mAppTargetSdkVersion = appTargetSdkVersion;
        this.mContext = ctx;
        this.mCameraDeviceImplExt = CameraDeviceImplExtPlugin.getInstance.call(new Object[0]);
        String tag = String.format("CameraDevice-JV-%s", cameraId);
        this.TAG = tag.length() > 23 ? tag.substring(0, 23) : tag;
        Integer partialCount = (Integer) characteristics.get(CameraCharacteristics.REQUEST_PARTIAL_RESULT_COUNT);
        if (partialCount == null) {
            this.mTotalPartialCount = 1;
        } else {
            this.mTotalPartialCount = partialCount.intValue();
        }
    }

    public CameraDeviceCallbacks getCallbacks() {
        return this.mCallbacks;
    }

    public void setRemoteDevice(ICameraDeviceUser remoteDevice) throws CameraAccessException {
        synchronized (this.mInterfaceLock) {
            if (!this.mInError) {
                this.mRemoteDevice = new ICameraDeviceUserWrapper(remoteDevice);
                IBinder remoteDeviceBinder = remoteDevice.asBinder();
                if (remoteDeviceBinder != null) {
                    try {
                        remoteDeviceBinder.linkToDeath(this, 0);
                    } catch (RemoteException e) {
                        this.mDeviceExecutor.execute(this.mCallOnDisconnected);
                        throw new CameraAccessException(2, "The camera device has encountered a serious error");
                    }
                }
                this.mDeviceExecutor.execute(this.mCallOnOpened);
                this.mDeviceExecutor.execute(this.mCallOnUnconfigured);
            }
        }
    }

    public void setRemoteFailure(ServiceSpecificException failure) {
        final int failureCode = 4;
        final boolean failureIsError = true;
        switch (failure.errorCode) {
            case 4:
                failureIsError = false;
                break;
            case 5:
            case 9:
            default:
                String str = this.TAG;
                Log.e(str, "Unexpected failure in opening camera device: " + failure.errorCode + failure.getMessage());
                break;
            case 6:
                failureCode = 3;
                break;
            case 7:
                failureCode = 1;
                break;
            case 8:
                failureCode = 2;
                break;
            case 10:
                failureCode = 4;
                break;
        }
        synchronized (this.mInterfaceLock) {
            this.mInError = true;
            this.mDeviceExecutor.execute(new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.8
                @Override // java.lang.Runnable
                public void run() {
                    if (failureIsError) {
                        CameraDeviceImpl.this.mDeviceCallback.onError(CameraDeviceImpl.this, failureCode);
                    } else {
                        CameraDeviceImpl.this.mDeviceCallback.onDisconnected(CameraDeviceImpl.this);
                    }
                }
            });
        }
    }

    @Override // android.hardware.camera2.CameraDevice
    public String getId() {
        return this.mCameraId;
    }

    public void configureOutputs(List<Surface> outputs) throws CameraAccessException {
        ArrayList<OutputConfiguration> outputConfigs = new ArrayList<>(outputs.size());
        for (Surface s : outputs) {
            outputConfigs.add(new OutputConfiguration(s));
        }
        configureStreamsChecked(null, outputConfigs, 0, null, SystemClock.uptimeMillis());
    }

    public boolean configureStreamsChecked(InputConfiguration inputConfig, List<OutputConfiguration> outputs, int operatingMode, CaptureRequest sessionParams, long createSessionStartTime) throws CameraAccessException {
        List<OutputConfiguration> outputs2;
        int[] offlineStreamIds;
        if (outputs == null) {
            outputs2 = new ArrayList<>();
        } else {
            outputs2 = outputs;
        }
        if (outputs2.size() != 0 || inputConfig == null) {
            checkInputConfiguration(inputConfig);
            synchronized (this.mInterfaceLock) {
                checkIfCameraClosedOrInError();
                HashSet<OutputConfiguration> addSet = new HashSet<>(outputs2);
                List<Integer> deleteList = new ArrayList<>();
                for (int i = 0; i < this.mConfiguredOutputs.size(); i++) {
                    int streamId = this.mConfiguredOutputs.keyAt(i);
                    OutputConfiguration outConfig = this.mConfiguredOutputs.valueAt(i);
                    if (outputs2.contains(outConfig) && !outConfig.isDeferredConfiguration()) {
                        addSet.remove(outConfig);
                    }
                    deleteList.add(Integer.valueOf(streamId));
                }
                this.mDeviceExecutor.execute(this.mCallOnBusy);
                stopRepeating();
                try {
                    waitUntilIdle();
                    this.mRemoteDevice.beginConfigure();
                    InputConfiguration currentInputConfig = this.mConfiguredInput.getValue();
                    if (inputConfig != currentInputConfig && (inputConfig == null || !inputConfig.equals(currentInputConfig))) {
                        if (currentInputConfig != null) {
                            this.mRemoteDevice.deleteStream(this.mConfiguredInput.getKey().intValue());
                            this.mConfiguredInput = new AbstractMap.SimpleEntry<>(-1, null);
                        }
                        if (inputConfig != null) {
                            int streamId2 = this.mRemoteDevice.createInputStream(inputConfig.getWidth(), inputConfig.getHeight(), inputConfig.getFormat(), inputConfig.isMultiResolution());
                            this.mConfiguredInput = new AbstractMap.SimpleEntry<>(Integer.valueOf(streamId2), inputConfig);
                        }
                    }
                    for (Integer streamId3 : deleteList) {
                        this.mRemoteDevice.deleteStream(streamId3.intValue());
                        this.mConfiguredOutputs.delete(streamId3.intValue());
                    }
                    for (OutputConfiguration outConfig2 : outputs2) {
                        if (addSet.contains(outConfig2)) {
                            this.mConfiguredOutputs.put(this.mRemoteDevice.createStream(outConfig2), outConfig2);
                        }
                    }
                    if (sessionParams != null) {
                        offlineStreamIds = this.mRemoteDevice.endConfigure(operatingMode, sessionParams.getNativeCopy(), createSessionStartTime);
                    } else {
                        offlineStreamIds = this.mRemoteDevice.endConfigure(operatingMode, null, createSessionStartTime);
                    }
                    this.mOfflineSupport.clear();
                    if (offlineStreamIds != null && offlineStreamIds.length > 0) {
                        int length = offlineStreamIds.length;
                        int i2 = 0;
                        while (i2 < length) {
                            int offlineStreamId = offlineStreamIds[i2];
                            this.mOfflineSupport.add(Integer.valueOf(offlineStreamId));
                            i2++;
                            currentInputConfig = currentInputConfig;
                        }
                    }
                    if (1 == 0 || outputs2.size() <= 0) {
                        this.mDeviceExecutor.execute(this.mCallOnUnconfigured);
                    } else {
                        this.mDeviceExecutor.execute(this.mCallOnIdle);
                    }
                } catch (CameraAccessException e) {
                    if (e.getReason() == 4) {
                        throw new IllegalStateException("The camera is currently busy. You must wait until the previous operation completes.", e);
                    }
                    throw e;
                } catch (IllegalArgumentException e2) {
                    String str = this.TAG;
                    Log.w(str, "Stream configuration failed due to: " + e2.getMessage());
                    if (0 == 0 || outputs2.size() <= 0) {
                        this.mDeviceExecutor.execute(this.mCallOnUnconfigured);
                    } else {
                        this.mDeviceExecutor.execute(this.mCallOnIdle);
                    }
                    return false;
                }
            }
            return true;
        }
        throw new IllegalArgumentException("cannot configure an input stream without any output streams");
    }

    @Override // android.hardware.camera2.CameraDevice
    public void createCaptureSession(List<Surface> outputs, CameraCaptureSession.StateCallback callback, Handler handler) throws CameraAccessException {
        List<OutputConfiguration> outConfigurations = new ArrayList<>(outputs.size());
        for (Surface surface : outputs) {
            outConfigurations.add(new OutputConfiguration(surface));
        }
        createCaptureSessionInternal(null, outConfigurations, callback, checkAndWrapHandler(handler), 0, null);
    }

    @Override // android.hardware.camera2.CameraDevice
    public void createCaptureSessionByOutputConfigurations(List<OutputConfiguration> outputConfigurations, CameraCaptureSession.StateCallback callback, Handler handler) throws CameraAccessException {
        List<OutputConfiguration> currentOutputs = new ArrayList<>(outputConfigurations);
        createCaptureSessionInternal(null, currentOutputs, callback, checkAndWrapHandler(handler), 0, null);
    }

    @Override // android.hardware.camera2.CameraDevice
    public void createReprocessableCaptureSession(InputConfiguration inputConfig, List<Surface> outputs, CameraCaptureSession.StateCallback callback, Handler handler) throws CameraAccessException {
        if (inputConfig != null) {
            List<OutputConfiguration> outConfigurations = new ArrayList<>(outputs.size());
            for (Surface surface : outputs) {
                outConfigurations.add(new OutputConfiguration(surface));
            }
            createCaptureSessionInternal(inputConfig, outConfigurations, callback, checkAndWrapHandler(handler), 0, null);
            return;
        }
        throw new IllegalArgumentException("inputConfig cannot be null when creating a reprocessable capture session");
    }

    @Override // android.hardware.camera2.CameraDevice
    public void createReprocessableCaptureSessionByConfigurations(InputConfiguration inputConfig, List<OutputConfiguration> outputs, CameraCaptureSession.StateCallback callback, Handler handler) throws CameraAccessException {
        if (inputConfig == null) {
            throw new IllegalArgumentException("inputConfig cannot be null when creating a reprocessable capture session");
        } else if (outputs != null) {
            List<OutputConfiguration> currentOutputs = new ArrayList<>();
            for (OutputConfiguration output : outputs) {
                currentOutputs.add(new OutputConfiguration(output));
            }
            createCaptureSessionInternal(inputConfig, currentOutputs, callback, checkAndWrapHandler(handler), 0, null);
        } else {
            throw new IllegalArgumentException("Output configurations cannot be null when creating a reprocessable capture session");
        }
    }

    @Override // android.hardware.camera2.CameraDevice
    public void createConstrainedHighSpeedCaptureSession(List<Surface> outputs, CameraCaptureSession.StateCallback callback, Handler handler) throws CameraAccessException {
        if (outputs == null || outputs.size() == 0 || outputs.size() > 2) {
            throw new IllegalArgumentException("Output surface list must not be null and the size must be no more than 2");
        }
        List<OutputConfiguration> outConfigurations = new ArrayList<>(outputs.size());
        for (Surface surface : outputs) {
            outConfigurations.add(new OutputConfiguration(surface));
        }
        createCaptureSessionInternal(null, outConfigurations, callback, checkAndWrapHandler(handler), 1, null);
    }

    @Override // android.hardware.camera2.CameraDevice
    public void createCustomCaptureSession(InputConfiguration inputConfig, List<OutputConfiguration> outputs, int operatingMode, CameraCaptureSession.StateCallback callback, Handler handler) throws CameraAccessException {
        List<OutputConfiguration> currentOutputs = new ArrayList<>();
        for (OutputConfiguration output : outputs) {
            currentOutputs.add(new OutputConfiguration(output));
        }
        createCaptureSessionInternal(inputConfig, currentOutputs, callback, checkAndWrapHandler(handler), operatingMode, null);
    }

    @Override // android.hardware.camera2.CameraDevice
    public void createCaptureSession(SessionConfiguration config) throws CameraAccessException {
        if (config != null) {
            List<OutputConfiguration> outputConfigs = config.getOutputConfigurations();
            if (outputConfigs == null) {
                throw new IllegalArgumentException("Invalid output configurations");
            } else if (config.getExecutor() != null) {
                createCaptureSessionInternal(config.getInputConfiguration(), outputConfigs, config.getStateCallback(), config.getExecutor(), config.getSessionType(), config.getSessionParameters());
            } else {
                throw new IllegalArgumentException("Invalid executor");
            }
        } else {
            throw new IllegalArgumentException("Invalid session configuration");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0078 A[Catch: all -> 0x00f8, TRY_ENTER, TryCatch #2 {all -> 0x00f8, blocks: (B:12:0x001b, B:13:0x0022, B:14:0x0023, B:16:0x0027, B:17:0x002a, B:19:0x002f, B:20:0x0034, B:22:0x0038, B:23:0x003d, B:25:0x0055, B:29:0x005e, B:38:0x0078, B:39:0x0086, B:41:0x008c, B:42:0x009b, B:43:0x00cd, B:44:0x00e5, B:46:0x00e9, B:47:0x00ef, B:49:0x00f2, B:52:0x00f6), top: B:59:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00cd A[Catch: all -> 0x00f8, TryCatch #2 {all -> 0x00f8, blocks: (B:12:0x001b, B:13:0x0022, B:14:0x0023, B:16:0x0027, B:17:0x002a, B:19:0x002f, B:20:0x0034, B:22:0x0038, B:23:0x003d, B:25:0x0055, B:29:0x005e, B:38:0x0078, B:39:0x0086, B:41:0x008c, B:42:0x009b, B:43:0x00cd, B:44:0x00e5, B:46:0x00e9, B:47:0x00ef, B:49:0x00f2, B:52:0x00f6), top: B:59:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00e9 A[Catch: all -> 0x00f8, TryCatch #2 {all -> 0x00f8, blocks: (B:12:0x001b, B:13:0x0022, B:14:0x0023, B:16:0x0027, B:17:0x002a, B:19:0x002f, B:20:0x0034, B:22:0x0038, B:23:0x003d, B:25:0x0055, B:29:0x005e, B:38:0x0078, B:39:0x0086, B:41:0x008c, B:42:0x009b, B:43:0x00cd, B:44:0x00e5, B:46:0x00e9, B:47:0x00ef, B:49:0x00f2, B:52:0x00f6), top: B:59:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00f2 A[Catch: all -> 0x00f8, TryCatch #2 {all -> 0x00f8, blocks: (B:12:0x001b, B:13:0x0022, B:14:0x0023, B:16:0x0027, B:17:0x002a, B:19:0x002f, B:20:0x0034, B:22:0x0038, B:23:0x003d, B:25:0x0055, B:29:0x005e, B:38:0x0078, B:39:0x0086, B:41:0x008c, B:42:0x009b, B:43:0x00cd, B:44:0x00e5, B:46:0x00e9, B:47:0x00ef, B:49:0x00f2, B:52:0x00f6), top: B:59:0x0009 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void createCaptureSessionInternal(android.hardware.camera2.params.InputConfiguration r23, java.util.List<android.hardware.camera2.params.OutputConfiguration> r24, android.hardware.camera2.CameraCaptureSession.StateCallback r25, java.util.concurrent.Executor r26, int r27, android.hardware.camera2.CaptureRequest r28) throws android.hardware.camera2.CameraAccessException {
        /*
            Method dump skipped, instructions count: 250
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.camera2.impl.CameraDeviceImpl.createCaptureSessionInternal(android.hardware.camera2.params.InputConfiguration, java.util.List, android.hardware.camera2.CameraCaptureSession$StateCallback, java.util.concurrent.Executor, int, android.hardware.camera2.CaptureRequest):void");
    }

    @Override // android.hardware.camera2.CameraDevice
    public boolean isSessionConfigurationSupported(SessionConfiguration sessionConfig) throws CameraAccessException, UnsupportedOperationException, IllegalArgumentException {
        boolean isSessionConfigurationSupported;
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            isSessionConfigurationSupported = this.mRemoteDevice.isSessionConfigurationSupported(sessionConfig);
        }
        return isSessionConfigurationSupported;
    }

    public void setSessionListener(StateCallbackKK sessionCallback) {
        synchronized (this.mInterfaceLock) {
            this.mSessionStateCallback = sessionCallback;
        }
    }

    private void overrideEnableZsl(CameraMetadataNative request, boolean newValue) {
        Boolean enableZsl = (Boolean) request.get(CaptureRequest.CONTROL_ENABLE_ZSL);
        if (enableZsl != null) {
            request.set((CaptureRequest.Key<CaptureRequest.Key<Boolean>>) CaptureRequest.CONTROL_ENABLE_ZSL, (CaptureRequest.Key<Boolean>) Boolean.valueOf(newValue));
        }
    }

    @Override // android.hardware.camera2.CameraDevice
    public CaptureRequest.Builder createCaptureRequest(int templateType, Set<String> physicalCameraIdSet) throws CameraAccessException {
        CaptureRequest.Builder builder;
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            for (String physicalId : physicalCameraIdSet) {
                if (physicalId == getId()) {
                    throw new IllegalStateException("Physical id matches the logical id!");
                }
            }
            CameraMetadataNative templatedRequest = this.mRemoteDevice.createDefaultRequest(templateType);
            if (this.mAppTargetSdkVersion < 26 || templateType != 2) {
                overrideEnableZsl(templatedRequest, false);
            }
            builder = new CaptureRequest.Builder(templatedRequest, false, -1, getId(), physicalCameraIdSet);
        }
        return builder;
    }

    @Override // android.hardware.camera2.CameraDevice
    public CaptureRequest.Builder createCaptureRequest(int templateType) throws CameraAccessException {
        CaptureRequest.Builder builder;
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            CameraMetadataNative templatedRequest = this.mRemoteDevice.createDefaultRequest(templateType);
            if (this.mAppTargetSdkVersion < 26 || templateType != 2) {
                overrideEnableZsl(templatedRequest, false);
            }
            builder = new CaptureRequest.Builder(templatedRequest, false, -1, getId(), null);
        }
        return builder;
    }

    @Override // android.hardware.camera2.CameraDevice
    public CaptureRequest.Builder createReprocessCaptureRequest(TotalCaptureResult inputResult) throws CameraAccessException {
        CaptureRequest.Builder builder;
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            CameraMetadataNative resultMetadata = new CameraMetadataNative(inputResult.getNativeCopy());
            builder = new CaptureRequest.Builder(resultMetadata, true, inputResult.getSessionId(), getId(), null);
        }
        return builder;
    }

    public void prepare(Surface surface) throws CameraAccessException {
        if (surface != null) {
            synchronized (this.mInterfaceLock) {
                checkIfCameraClosedOrInError();
                int streamId = -1;
                int i = 0;
                while (true) {
                    if (i >= this.mConfiguredOutputs.size()) {
                        break;
                    }
                    List<Surface> surfaces = this.mConfiguredOutputs.valueAt(i).getSurfaces();
                    if (surfaces.contains(surface)) {
                        streamId = this.mConfiguredOutputs.keyAt(i);
                        break;
                    }
                    i++;
                }
                if (streamId != -1) {
                    this.mRemoteDevice.prepare(streamId);
                } else {
                    throw new IllegalArgumentException("Surface is not part of this session");
                }
            }
            return;
        }
        throw new IllegalArgumentException("Surface is null");
    }

    public void prepare(int maxCount, Surface surface) throws CameraAccessException {
        if (surface == null) {
            throw new IllegalArgumentException("Surface is null");
        } else if (maxCount > 0) {
            synchronized (this.mInterfaceLock) {
                checkIfCameraClosedOrInError();
                int streamId = -1;
                int i = 0;
                while (true) {
                    if (i >= this.mConfiguredOutputs.size()) {
                        break;
                    } else if (surface == this.mConfiguredOutputs.valueAt(i).getSurface()) {
                        streamId = this.mConfiguredOutputs.keyAt(i);
                        break;
                    } else {
                        i++;
                    }
                }
                if (streamId != -1) {
                    this.mRemoteDevice.prepare2(maxCount, streamId);
                } else {
                    throw new IllegalArgumentException("Surface is not part of this session");
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid maxCount given: " + maxCount);
        }
    }

    public void updateOutputConfiguration(OutputConfiguration config) throws CameraAccessException {
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            int streamId = -1;
            int i = 0;
            while (true) {
                if (i >= this.mConfiguredOutputs.size()) {
                    break;
                } else if (config.getSurface() == this.mConfiguredOutputs.valueAt(i).getSurface()) {
                    streamId = this.mConfiguredOutputs.keyAt(i);
                    break;
                } else {
                    i++;
                }
            }
            if (streamId != -1) {
                this.mRemoteDevice.updateOutputConfiguration(streamId, config);
                this.mConfiguredOutputs.put(streamId, config);
            } else {
                throw new IllegalArgumentException("Invalid output configuration");
            }
        }
    }

    public CameraOfflineSession switchToOffline(Collection<Surface> offlineOutputs, Executor executor, CameraOfflineSession.CameraOfflineSessionCallback listener) throws CameraAccessException {
        CameraOfflineSessionImpl cameraOfflineSessionImpl;
        if (!offlineOutputs.isEmpty()) {
            final HashSet<Integer> offlineStreamIds = new HashSet<>();
            SparseArray<OutputConfiguration> offlineConfiguredOutputs = new SparseArray<>();
            synchronized (this.mInterfaceLock) {
                checkIfCameraClosedOrInError();
                if (this.mOfflineSessionImpl == null) {
                    for (Surface surface : offlineOutputs) {
                        int streamId = -1;
                        int i = 0;
                        while (true) {
                            if (i >= this.mConfiguredOutputs.size()) {
                                break;
                            } else if (surface == this.mConfiguredOutputs.valueAt(i).getSurface()) {
                                streamId = this.mConfiguredOutputs.keyAt(i);
                                offlineConfiguredOutputs.append(streamId, this.mConfiguredOutputs.valueAt(i));
                                break;
                            } else {
                                i++;
                            }
                        }
                        if (streamId == -1) {
                            throw new IllegalArgumentException("Offline surface is not part of this session");
                        } else if (this.mOfflineSupport.contains(Integer.valueOf(streamId))) {
                            offlineStreamIds.add(Integer.valueOf(streamId));
                        } else {
                            throw new IllegalArgumentException("Surface: " + surface + " does not  support offline mode");
                        }
                    }
                    stopRepeating();
                    cameraOfflineSessionImpl = new CameraOfflineSessionImpl(this.mCameraId, this.mCharacteristics, executor, listener, offlineConfiguredOutputs, this.mConfiguredInput, this.mConfiguredOutputs, this.mFrameNumberTracker, this.mCaptureCallbackMap, this.mRequestLastFrameNumbersList);
                    this.mOfflineSessionImpl = cameraOfflineSessionImpl;
                    this.mOfflineSwitchService = Executors.newSingleThreadExecutor();
                    this.mConfiguredOutputs.clear();
                    this.mConfiguredInput = new AbstractMap.SimpleEntry<>(-1, null);
                    this.mIdle = true;
                    this.mCaptureCallbackMap = new SparseArray<>();
                    this.mFrameNumberTracker = new FrameNumberTracker();
                    this.mCurrentSession.closeWithoutDraining();
                    this.mCurrentSession = null;
                } else {
                    throw new IllegalStateException("Switch to offline mode already in progress");
                }
            }
            this.mOfflineSwitchService.execute(new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.9
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        try {
                            ICameraDeviceUserWrapper iCameraDeviceUserWrapper = CameraDeviceImpl.this.mRemoteDevice;
                            CameraOfflineSessionImpl.CameraDeviceCallbacks callbacks = CameraDeviceImpl.this.mOfflineSessionImpl.getCallbacks();
                            HashSet hashSet = offlineStreamIds;
                            ICameraOfflineSession remoteOfflineSession = iCameraDeviceUserWrapper.switchToOffline(callbacks, Arrays.stream((Integer[]) hashSet.toArray(new Integer[hashSet.size()])).mapToInt(CameraDeviceImpl$9$$ExternalSyntheticLambda0.INSTANCE).toArray());
                            CameraDeviceImpl.this.mOfflineSessionImpl.setRemoteSession(remoteOfflineSession);
                        } catch (CameraAccessException e) {
                            CameraDeviceImpl.this.mOfflineSessionImpl.notifyFailedSwitch();
                        }
                    } finally {
                        CameraDeviceImpl.this.mOfflineSessionImpl = null;
                    }
                }
            });
            return cameraOfflineSessionImpl;
        }
        throw new IllegalArgumentException("Invalid offline surfaces!");
    }

    public boolean supportsOfflineProcessing(Surface surface) {
        boolean contains;
        if (surface != null) {
            synchronized (this.mInterfaceLock) {
                int streamId = -1;
                int i = 0;
                while (true) {
                    if (i >= this.mConfiguredOutputs.size()) {
                        break;
                    } else if (surface == this.mConfiguredOutputs.valueAt(i).getSurface()) {
                        streamId = this.mConfiguredOutputs.keyAt(i);
                        break;
                    } else {
                        i++;
                    }
                }
                if (streamId != -1) {
                    contains = this.mOfflineSupport.contains(Integer.valueOf(streamId));
                } else {
                    throw new IllegalArgumentException("Surface is not part of this session");
                }
            }
            return contains;
        }
        throw new IllegalArgumentException("Surface is null");
    }

    public void tearDown(Surface surface) throws CameraAccessException {
        if (surface != null) {
            synchronized (this.mInterfaceLock) {
                checkIfCameraClosedOrInError();
                int streamId = -1;
                int i = 0;
                while (true) {
                    if (i >= this.mConfiguredOutputs.size()) {
                        break;
                    } else if (surface == this.mConfiguredOutputs.valueAt(i).getSurface()) {
                        streamId = this.mConfiguredOutputs.keyAt(i);
                        break;
                    } else {
                        i++;
                    }
                }
                if (streamId != -1) {
                    this.mRemoteDevice.tearDown(streamId);
                } else {
                    throw new IllegalArgumentException("Surface is not part of this session");
                }
            }
            return;
        }
        throw new IllegalArgumentException("Surface is null");
    }

    public void finalizeOutputConfigs(List<OutputConfiguration> outputConfigs) throws CameraAccessException {
        if (outputConfigs == null || outputConfigs.size() == 0) {
            throw new IllegalArgumentException("deferred config is null or empty");
        }
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            for (OutputConfiguration config : outputConfigs) {
                int streamId = -1;
                int i = 0;
                while (true) {
                    if (i >= this.mConfiguredOutputs.size()) {
                        break;
                    } else if (config.equals(this.mConfiguredOutputs.valueAt(i))) {
                        streamId = this.mConfiguredOutputs.keyAt(i);
                        break;
                    } else {
                        i++;
                    }
                }
                if (streamId == -1) {
                    throw new IllegalArgumentException("Deferred config is not part of this session");
                } else if (config.getSurfaces().size() != 0) {
                    this.mRemoteDevice.finalizeOutputConfigurations(streamId, config);
                    this.mConfiguredOutputs.put(streamId, config);
                } else {
                    throw new IllegalArgumentException("The final config for stream " + streamId + " must have at least 1 surface");
                }
            }
        }
    }

    public int capture(CaptureRequest request, CaptureCallback callback, Executor executor) throws CameraAccessException {
        List<CaptureRequest> requestList = new ArrayList<>();
        requestList.add(request);
        return submitCaptureRequest(requestList, callback, executor, false);
    }

    public int captureBurst(List<CaptureRequest> requests, CaptureCallback callback, Executor executor) throws CameraAccessException {
        if (requests != null && !requests.isEmpty()) {
            return submitCaptureRequest(requests, callback, executor, false);
        }
        throw new IllegalArgumentException("At least one request must be given");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkEarlyTriggerSequenceCompleteLocked(final int requestId, long lastFrameNumber, int[] repeatingRequestTypes) {
        if (lastFrameNumber == -1) {
            int index = this.mCaptureCallbackMap.indexOfKey(requestId);
            final CaptureCallbackHolder holder = index >= 0 ? this.mCaptureCallbackMap.valueAt(index) : null;
            if (holder != null) {
                this.mCaptureCallbackMap.removeAt(index);
            }
            if (holder != null) {
                Runnable resultDispatch = new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.10
                    @Override // java.lang.Runnable
                    public void run() {
                        if (!CameraDeviceImpl.this.isClosed()) {
                            holder.getCallback().onCaptureSequenceAborted(CameraDeviceImpl.this, requestId);
                        }
                    }
                };
                long ident = Binder.clearCallingIdentity();
                try {
                    holder.getExecutor().execute(resultDispatch);
                } finally {
                    Binder.restoreCallingIdentity(ident);
                }
            } else {
                Log.w(this.TAG, String.format("did not register callback to request %d", Integer.valueOf(requestId)));
            }
        } else {
            this.mRequestLastFrameNumbersList.add(new RequestLastFrameNumbersHolder(requestId, lastFrameNumber, repeatingRequestTypes));
            checkAndFireSequenceComplete();
        }
    }

    private int[] getRequestTypes(CaptureRequest[] requestArray) {
        int[] requestTypes = new int[requestArray.length];
        for (int i = 0; i < requestArray.length; i++) {
            requestTypes[i] = requestArray[i].getRequestType();
        }
        return requestTypes;
    }

    private int submitCaptureRequest(List<CaptureRequest> requestList, CaptureCallback callback, Executor executor, boolean repeating) throws CameraAccessException {
        int requestId;
        Executor executor2 = checkExecutor(executor, callback);
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            for (CaptureRequest request : requestList) {
                if (!request.getTargets().isEmpty()) {
                    for (Surface surface : request.getTargets()) {
                        if (surface == null) {
                            throw new IllegalArgumentException("Null Surface targets are not allowed");
                        }
                    }
                } else {
                    throw new IllegalArgumentException("Each request must have at least one Surface target");
                }
            }
            if (repeating) {
                stopRepeating();
            }
            CaptureRequest[] requestArray = (CaptureRequest[]) requestList.toArray(new CaptureRequest[requestList.size()]);
            for (CaptureRequest request2 : requestArray) {
                request2.convertSurfaceToStreamId(this.mConfiguredOutputs);
            }
            SubmitInfo requestInfo = this.mRemoteDevice.submitRequestList(requestArray, repeating);
            for (CaptureRequest request3 : requestArray) {
                request3.recoverStreamIdToSurface();
            }
            if (callback != null) {
                this.mCaptureCallbackMap.put(requestInfo.getRequestId(), new CaptureCallbackHolder(callback, requestList, executor2, repeating, this.mNextSessionId - 1));
            }
            if (repeating) {
                int i = this.mRepeatingRequestId;
                if (i != -1) {
                    checkEarlyTriggerSequenceCompleteLocked(i, requestInfo.getLastFrameNumber(), this.mRepeatingRequestTypes);
                }
                this.mRepeatingRequestId = requestInfo.getRequestId();
                this.mRepeatingRequestTypes = getRequestTypes(requestArray);
            } else {
                this.mRequestLastFrameNumbersList.add(new RequestLastFrameNumbersHolder(requestList, requestInfo));
            }
            if (this.mIdle) {
                this.mDeviceExecutor.execute(this.mCallOnActive);
            }
            this.mIdle = false;
            requestId = requestInfo.getRequestId();
        }
        return requestId;
    }

    public int setRepeatingRequest(CaptureRequest request, CaptureCallback callback, Executor executor) throws CameraAccessException {
        List<CaptureRequest> requestList = new ArrayList<>();
        requestList.add(request);
        return submitCaptureRequest(requestList, callback, executor, true);
    }

    public int setRepeatingBurst(List<CaptureRequest> requests, CaptureCallback callback, Executor executor) throws CameraAccessException {
        if (requests != null && !requests.isEmpty()) {
            return submitCaptureRequest(requests, callback, executor, true);
        }
        throw new IllegalArgumentException("At least one request must be given");
    }

    public void stopRepeating() throws CameraAccessException {
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            int requestId = this.mRepeatingRequestId;
            if (requestId != -1) {
                this.mRepeatingRequestId = -1;
                int[] requestTypes = this.mRepeatingRequestTypes;
                this.mRepeatingRequestTypes = null;
                try {
                    long lastFrameNumber = this.mRemoteDevice.cancelRequest(requestId);
                    checkEarlyTriggerSequenceCompleteLocked(requestId, lastFrameNumber, requestTypes);
                } catch (IllegalArgumentException e) {
                }
            }
        }
    }

    private void waitUntilIdle() throws CameraAccessException {
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            if (this.mRepeatingRequestId == -1) {
                this.mRemoteDevice.waitUntilIdle();
            } else {
                throw new IllegalStateException("Active repeating request ongoing");
            }
        }
    }

    public void flush() throws CameraAccessException {
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            this.mDeviceExecutor.execute(this.mCallOnBusy);
            if (this.mIdle) {
                this.mDeviceExecutor.execute(this.mCallOnIdle);
                return;
            }
            long lastFrameNumber = this.mRemoteDevice.flush();
            int i = this.mRepeatingRequestId;
            if (i != -1) {
                checkEarlyTriggerSequenceCompleteLocked(i, lastFrameNumber, this.mRepeatingRequestTypes);
                this.mRepeatingRequestId = -1;
                this.mRepeatingRequestTypes = null;
            }
        }
    }

    @Override // android.hardware.camera2.CameraDevice, java.lang.AutoCloseable
    public void close() {
        synchronized (this.mInterfaceLock) {
            if (!this.mClosing.getAndSet(true)) {
                this.mCameraDeviceImplExt.ormsSetSceneAction("ORMS_ACTION_CAMERA_CLOSE", 500);
                ExecutorService executorService = this.mOfflineSwitchService;
                if (executorService != null) {
                    executorService.shutdownNow();
                    this.mOfflineSwitchService = null;
                }
                ICameraDeviceUserWrapper iCameraDeviceUserWrapper = this.mRemoteDevice;
                if (iCameraDeviceUserWrapper != null) {
                    iCameraDeviceUserWrapper.disconnect();
                    this.mRemoteDevice.unlinkToDeath(this, 0);
                }
                CameraExtensionSessionImpl cameraExtensionSessionImpl = this.mCurrentExtensionSession;
                if (cameraExtensionSessionImpl != null) {
                    cameraExtensionSessionImpl.release(true);
                    this.mCurrentExtensionSession = null;
                }
                CameraAdvancedExtensionSessionImpl cameraAdvancedExtensionSessionImpl = this.mCurrentAdvancedExtensionSession;
                if (cameraAdvancedExtensionSessionImpl != null) {
                    cameraAdvancedExtensionSessionImpl.release(true);
                    this.mCurrentAdvancedExtensionSession = null;
                }
                if (this.mRemoteDevice != null || this.mInError) {
                    this.mDeviceExecutor.execute(this.mCallOnClosed);
                }
                this.mRemoteDevice = null;
            }
        }
    }

    protected void finalize() throws Throwable {
        try {
            close();
        } finally {
            super.finalize();
        }
    }

    private boolean checkInputConfigurationWithStreamConfigurationsAs(InputConfiguration inputConfig, StreamConfigurationMap configMap) {
        int[] inputFormats = configMap.getInputFormats();
        boolean validFormat = false;
        int inputFormat = inputConfig.getFormat();
        for (int format : inputFormats) {
            if (format == inputFormat) {
                validFormat = true;
            }
        }
        if (!validFormat) {
            return false;
        }
        boolean validSize = false;
        Size[] inputSizes = configMap.getInputSizes(inputFormat);
        for (Size s : inputSizes) {
            if (inputConfig.getWidth() == s.getWidth() && inputConfig.getHeight() == s.getHeight()) {
                validSize = true;
            }
        }
        return validSize;
    }

    private boolean checkInputConfigurationWithStreamConfigurations(InputConfiguration inputConfig, boolean maxResolution) {
        CameraCharacteristics.Key<StreamConfigurationMap> ck = CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP;
        if (maxResolution) {
            ck = CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP_MAXIMUM_RESOLUTION;
        }
        StreamConfigurationMap configMap = (StreamConfigurationMap) this.mCharacteristics.get(ck);
        if (configMap != null && checkInputConfigurationWithStreamConfigurationsAs(inputConfig, configMap)) {
            return true;
        }
        for (Map.Entry<String, CameraCharacteristics> entry : this.mPhysicalIdsToChars.entrySet()) {
            StreamConfigurationMap configMap2 = (StreamConfigurationMap) entry.getValue().get(ck);
            if (configMap2 != null && checkInputConfigurationWithStreamConfigurationsAs(inputConfig, configMap2)) {
                return true;
            }
        }
        return false;
    }

    private void checkInputConfiguration(InputConfiguration inputConfig) {
        if (inputConfig != null) {
            int inputFormat = inputConfig.getFormat();
            if (inputConfig.isMultiResolution()) {
                MultiResolutionStreamConfigurationMap configMap = (MultiResolutionStreamConfigurationMap) this.mCharacteristics.get(CameraCharacteristics.SCALER_MULTI_RESOLUTION_STREAM_CONFIGURATION_MAP);
                int[] inputFormats = configMap.getInputFormats();
                boolean validFormat = false;
                for (int format : inputFormats) {
                    if (format == inputFormat) {
                        validFormat = true;
                    }
                }
                if (validFormat) {
                    boolean validSize = false;
                    Collection<MultiResolutionStreamInfo> inputStreamInfo = configMap.getInputInfo(inputFormat);
                    for (MultiResolutionStreamInfo info : inputStreamInfo) {
                        if (inputConfig.getWidth() == info.getWidth() && inputConfig.getHeight() == info.getHeight()) {
                            validSize = true;
                        }
                    }
                    if (!validSize) {
                        throw new IllegalArgumentException("Multi-resolution input size " + inputConfig.getWidth() + "x" + inputConfig.getHeight() + " is not valid");
                    }
                    return;
                }
                throw new IllegalArgumentException("multi-resolution input format " + inputFormat + " is not valid");
            } else if (!checkInputConfigurationWithStreamConfigurations(inputConfig, false) && !checkInputConfigurationWithStreamConfigurations(inputConfig, true)) {
                throw new IllegalArgumentException("Input config with format " + inputFormat + " and size " + inputConfig.getWidth() + "x" + inputConfig.getHeight() + " not supported by camera id " + this.mCameraId);
            }
        }
    }

    /* loaded from: classes.dex */
    public static abstract class StateCallbackKK extends CameraDevice.StateCallback {
        public void onUnconfigured(CameraDevice camera) {
        }

        public void onActive(CameraDevice camera) {
        }

        public void onBusy(CameraDevice camera) {
        }

        public void onIdle(CameraDevice camera) {
        }

        public void onRequestQueueEmpty() {
        }

        public void onSurfacePrepared(Surface surface) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAndFireSequenceComplete() {
        long completedFrameNumber;
        Throwable th;
        long completedFrameNumber2 = this.mFrameNumberTracker.getCompletedFrameNumber();
        long completedReprocessFrameNumber = this.mFrameNumberTracker.getCompletedReprocessFrameNumber();
        long completedZslStillFrameNumber = this.mFrameNumberTracker.getCompletedZslStillFrameNumber();
        Iterator<RequestLastFrameNumbersHolder> iter = this.mRequestLastFrameNumbersList.iterator();
        while (iter.hasNext()) {
            final RequestLastFrameNumbersHolder requestLastFrameNumbers = iter.next();
            final int requestId = requestLastFrameNumbers.getRequestId();
            if (this.mRemoteDevice == null) {
                Log.w(this.TAG, "Camera closed while checking sequences");
                return;
            }
            if (!requestLastFrameNumbers.isSequenceCompleted()) {
                long lastRegularFrameNumber = requestLastFrameNumbers.getLastRegularFrameNumber();
                long lastReprocessFrameNumber = requestLastFrameNumbers.getLastReprocessFrameNumber();
                long lastZslStillFrameNumber = requestLastFrameNumbers.getLastZslStillFrameNumber();
                if (lastRegularFrameNumber <= completedFrameNumber2 && lastReprocessFrameNumber <= completedReprocessFrameNumber && lastZslStillFrameNumber <= completedZslStillFrameNumber) {
                    requestLastFrameNumbers.markSequenceCompleted();
                }
                completedFrameNumber = completedFrameNumber2;
                int index = this.mCaptureCallbackMap.indexOfKey(requestId);
                final CaptureCallbackHolder holder = index >= 0 ? this.mCaptureCallbackMap.valueAt(index) : null;
                if (holder != null && requestLastFrameNumbers.isSequenceCompleted()) {
                    Runnable resultDispatch = new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.11
                        @Override // java.lang.Runnable
                        public void run() {
                            if (!CameraDeviceImpl.this.isClosed()) {
                                holder.getCallback().onCaptureSequenceCompleted(CameraDeviceImpl.this, requestId, requestLastFrameNumbers.getLastFrameNumber());
                            }
                        }
                    };
                    long ident = Binder.clearCallingIdentity();
                    try {
                        try {
                            holder.getExecutor().execute(resultDispatch);
                            Binder.restoreCallingIdentity(ident);
                        } catch (Throwable th2) {
                            th = th2;
                            Binder.restoreCallingIdentity(ident);
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
            } else {
                completedFrameNumber = completedFrameNumber2;
            }
            if (requestLastFrameNumbers.isSequenceCompleted() && requestLastFrameNumbers.isInflightCompleted()) {
                int index2 = this.mCaptureCallbackMap.indexOfKey(requestId);
                if (index2 >= 0) {
                    this.mCaptureCallbackMap.removeAt(index2);
                }
                iter.remove();
            }
            completedFrameNumber2 = completedFrameNumber;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeCompletedCallbackHolderLocked(long lastCompletedRegularFrameNumber, long lastCompletedReprocessFrameNumber, long lastCompletedZslStillFrameNumber) {
        Iterator<RequestLastFrameNumbersHolder> iter = this.mRequestLastFrameNumbersList.iterator();
        while (iter.hasNext()) {
            RequestLastFrameNumbersHolder requestLastFrameNumbers = iter.next();
            int requestId = requestLastFrameNumbers.getRequestId();
            if (this.mRemoteDevice == null) {
                Log.w(this.TAG, "Camera closed while removing completed callback holders");
                return;
            }
            long lastRegularFrameNumber = requestLastFrameNumbers.getLastRegularFrameNumber();
            long lastReprocessFrameNumber = requestLastFrameNumbers.getLastReprocessFrameNumber();
            long lastZslStillFrameNumber = requestLastFrameNumbers.getLastZslStillFrameNumber();
            if (lastRegularFrameNumber <= lastCompletedRegularFrameNumber && lastReprocessFrameNumber <= lastCompletedReprocessFrameNumber && lastZslStillFrameNumber <= lastCompletedZslStillFrameNumber) {
                if (requestLastFrameNumbers.isSequenceCompleted()) {
                    int index = this.mCaptureCallbackMap.indexOfKey(requestId);
                    if (index >= 0) {
                        this.mCaptureCallbackMap.removeAt(index);
                    }
                    iter.remove();
                } else {
                    requestLastFrameNumbers.markInflightCompleted();
                }
            }
        }
    }

    public void onDeviceError(int errorCode, CaptureResultExtras resultExtras) {
        synchronized (this.mInterfaceLock) {
            if (this.mRemoteDevice != null) {
                CameraOfflineSessionImpl cameraOfflineSessionImpl = this.mOfflineSessionImpl;
                if (cameraOfflineSessionImpl != null) {
                    cameraOfflineSessionImpl.getCallbacks().onDeviceError(errorCode, resultExtras);
                    return;
                }
                switch (errorCode) {
                    case 0:
                        long ident = Binder.clearCallingIdentity();
                        this.mDeviceExecutor.execute(this.mCallOnDisconnected);
                        Binder.restoreCallingIdentity(ident);
                        break;
                    case 1:
                        scheduleNotifyError(4);
                        break;
                    case 2:
                    default:
                        String str = this.TAG;
                        Log.e(str, "Unknown error from camera device: " + errorCode);
                        scheduleNotifyError(5);
                        break;
                    case 3:
                    case 4:
                    case 5:
                        onCaptureErrorLocked(errorCode, resultExtras);
                        break;
                    case 6:
                        scheduleNotifyError(3);
                        break;
                }
            }
        }
    }

    private void scheduleNotifyError(int code) {
        this.mInError = true;
        long ident = Binder.clearCallingIdentity();
        try {
            this.mDeviceExecutor.execute(PooledLambda.obtainRunnable(CameraDeviceImpl$$ExternalSyntheticLambda0.INSTANCE, this, Integer.valueOf(code)).recycleOnUse());
        } finally {
            Binder.restoreCallingIdentity(ident);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyError(int code) {
        if (!isClosed()) {
            this.mDeviceCallback.onError(this, code);
        }
    }

    private void onCaptureErrorLocked(int errorCode, CaptureResultExtras resultExtras) {
        long ident;
        int requestId = resultExtras.getRequestId();
        int subsequenceId = resultExtras.getSubsequenceId();
        final long frameNumber = resultExtras.getFrameNumber();
        String errorPhysicalCameraId = resultExtras.getErrorPhysicalCameraId();
        final CaptureCallbackHolder holder = this.mCaptureCallbackMap.get(requestId);
        int reason = 0;
        if (holder == null) {
            Log.e(this.TAG, String.format("Receive capture error on unknown request ID %d", Integer.valueOf(requestId)));
            return;
        }
        final CaptureRequest request = holder.getRequest(subsequenceId);
        if (errorCode == 5) {
            OutputConfiguration config = this.mConfiguredOutputs.get(resultExtras.getErrorStreamId());
            if (config == null) {
                Log.v(this.TAG, String.format("Stream %d has been removed. Skipping buffer lost callback", Integer.valueOf(resultExtras.getErrorStreamId())));
                return;
            }
            for (final Surface surface : config.getSurfaces()) {
                if (request.containsTarget(surface)) {
                    Runnable failureDispatch = new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.12
                        @Override // java.lang.Runnable
                        public void run() {
                            if (!CameraDeviceImpl.this.isClosed()) {
                                holder.getCallback().onCaptureBufferLost(CameraDeviceImpl.this, request, surface, frameNumber);
                            }
                        }
                    };
                    ident = Binder.clearCallingIdentity();
                    try {
                        holder.getExecutor().execute(failureDispatch);
                    } finally {
                    }
                }
            }
            return;
        }
        boolean mayHaveBuffers = errorCode == 4;
        CameraCaptureSessionCore cameraCaptureSessionCore = this.mCurrentSession;
        if (cameraCaptureSessionCore != null && cameraCaptureSessionCore.isAborting()) {
            reason = 1;
        }
        final CaptureFailure failure = new CaptureFailure(request, reason, mayHaveBuffers, requestId, frameNumber, errorPhysicalCameraId);
        Runnable failureDispatch2 = new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.13
            @Override // java.lang.Runnable
            public void run() {
                if (!CameraDeviceImpl.this.isClosed()) {
                    holder.getCallback().onCaptureFailed(CameraDeviceImpl.this, request, failure);
                }
            }
        };
        this.mFrameNumberTracker.updateTracker(frameNumber, true, request.getRequestType());
        checkAndFireSequenceComplete();
        ident = Binder.clearCallingIdentity();
        try {
            holder.getExecutor().execute(failureDispatch2);
        } finally {
        }
    }

    public void onDeviceIdle() {
        synchronized (this.mInterfaceLock) {
            if (this.mRemoteDevice != null) {
                CameraOfflineSessionImpl cameraOfflineSessionImpl = this.mOfflineSessionImpl;
                if (cameraOfflineSessionImpl != null) {
                    cameraOfflineSessionImpl.getCallbacks().onDeviceIdle();
                    return;
                }
                removeCompletedCallbackHolderLocked(Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE);
                if (!this.mIdle) {
                    long ident = Binder.clearCallingIdentity();
                    this.mDeviceExecutor.execute(this.mCallOnIdle);
                    Binder.restoreCallingIdentity(ident);
                }
                this.mIdle = true;
            }
        }
    }

    /* loaded from: classes.dex */
    public class CameraDeviceCallbacks extends ICameraDeviceCallbacks.Stub {
        public CameraDeviceCallbacks() {
        }

        @Override // android.hardware.camera2.ICameraDeviceCallbacks.Stub, android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.hardware.camera2.ICameraDeviceCallbacks
        public void onDeviceError(int errorCode, CaptureResultExtras resultExtras) {
            CameraDeviceImpl.this.onDeviceError(errorCode, resultExtras);
        }

        @Override // android.hardware.camera2.ICameraDeviceCallbacks
        public void onRepeatingRequestError(long lastFrameNumber, int repeatingRequestId) {
            synchronized (CameraDeviceImpl.this.mInterfaceLock) {
                if (!(CameraDeviceImpl.this.mRemoteDevice == null || CameraDeviceImpl.this.mRepeatingRequestId == -1)) {
                    if (CameraDeviceImpl.this.mOfflineSessionImpl != null) {
                        CameraDeviceImpl.this.mOfflineSessionImpl.getCallbacks().onRepeatingRequestError(lastFrameNumber, repeatingRequestId);
                        return;
                    }
                    CameraDeviceImpl cameraDeviceImpl = CameraDeviceImpl.this;
                    cameraDeviceImpl.checkEarlyTriggerSequenceCompleteLocked(cameraDeviceImpl.mRepeatingRequestId, lastFrameNumber, CameraDeviceImpl.this.mRepeatingRequestTypes);
                    if (CameraDeviceImpl.this.mRepeatingRequestId == repeatingRequestId) {
                        CameraDeviceImpl.this.mRepeatingRequestId = -1;
                        CameraDeviceImpl.this.mRepeatingRequestTypes = null;
                    }
                }
            }
        }

        @Override // android.hardware.camera2.ICameraDeviceCallbacks
        public void onDeviceIdle() {
            CameraDeviceImpl.this.onDeviceIdle();
        }

        @Override // android.hardware.camera2.ICameraDeviceCallbacks
        public void onCaptureStarted(final CaptureResultExtras resultExtras, final long timestamp) {
            Throwable th;
            Throwable th2;
            int requestId = resultExtras.getRequestId();
            final long frameNumber = resultExtras.getFrameNumber();
            long lastCompletedRegularFrameNumber = resultExtras.getLastCompletedRegularFrameNumber();
            long lastCompletedReprocessFrameNumber = resultExtras.getLastCompletedReprocessFrameNumber();
            long lastCompletedZslFrameNumber = resultExtras.getLastCompletedZslFrameNumber();
            synchronized (CameraDeviceImpl.this.mInterfaceLock) {
                try {
                    try {
                        if (CameraDeviceImpl.this.mRemoteDevice != null) {
                            if (CameraDeviceImpl.this.mOfflineSessionImpl != null) {
                                try {
                                    CameraDeviceImpl.this.mOfflineSessionImpl.getCallbacks().onCaptureStarted(resultExtras, timestamp);
                                } catch (Throwable th3) {
                                    th = th3;
                                    throw th;
                                }
                            } else {
                                CameraDeviceImpl.this.removeCompletedCallbackHolderLocked(lastCompletedRegularFrameNumber, lastCompletedReprocessFrameNumber, lastCompletedZslFrameNumber);
                                final CaptureCallbackHolder holder = (CaptureCallbackHolder) CameraDeviceImpl.this.mCaptureCallbackMap.get(requestId);
                                if (holder != null) {
                                    if (!CameraDeviceImpl.this.isClosed()) {
                                        long ident = Binder.clearCallingIdentity();
                                        try {
                                            try {
                                                holder.getExecutor().execute(new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.CameraDeviceCallbacks.1
                                                    @Override // java.lang.Runnable
                                                    public void run() {
                                                        if (!CameraDeviceImpl.this.isClosed()) {
                                                            int subsequenceId = resultExtras.getSubsequenceId();
                                                            CaptureRequest request = holder.getRequest(subsequenceId);
                                                            if (holder.hasBatchedOutputs()) {
                                                                Range<Integer> fpsRange = (Range) request.get(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE);
                                                                for (int i = 0; i < holder.getRequestCount(); i++) {
                                                                    holder.getCallback().onCaptureStarted(CameraDeviceImpl.this, holder.getRequest(i), timestamp - (((subsequenceId - i) * 1000000000) / fpsRange.getUpper().intValue()), frameNumber - (subsequenceId - i));
                                                                }
                                                                return;
                                                            }
                                                            holder.getCallback().onCaptureStarted(CameraDeviceImpl.this, holder.getRequest(resultExtras.getSubsequenceId()), timestamp, frameNumber);
                                                        }
                                                    }
                                                });
                                                Binder.restoreCallingIdentity(ident);
                                            } catch (Throwable th4) {
                                                th2 = th4;
                                                Binder.restoreCallingIdentity(ident);
                                                throw th2;
                                            }
                                        } catch (Throwable th5) {
                                            th2 = th5;
                                        }
                                    }
                                }
                            }
                        }
                    } catch (Throwable th6) {
                        th = th6;
                    }
                } catch (Throwable th7) {
                    th = th7;
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 22, insn: 0x00d5: MOVE  (r16 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r22 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('requestId' int)]), block:B:43:0x00d3 */
        /* JADX WARN: Type inference failed for: r14v0, types: [long] */
        /* JADX WARN: Type inference failed for: r14v1 */
        /* JADX WARN: Type inference failed for: r14v10 */
        /* JADX WARN: Type inference failed for: r14v13 */
        /* JADX WARN: Type inference failed for: r14v15 */
        /* JADX WARN: Type inference failed for: r14v2 */
        /* JADX WARN: Type inference failed for: r14v3 */
        /* JADX WARN: Type inference failed for: r14v4 */
        /* JADX WARN: Type inference failed for: r14v5 */
        /* JADX WARN: Type inference failed for: r14v6 */
        /* JADX WARN: Type inference failed for: r14v9 */
        /* JADX WARN: Type inference failed for: r1v10, types: [android.hardware.camera2.impl.FrameNumberTracker] */
        /* JADX WARN: Type inference failed for: r1v6, types: [android.hardware.camera2.impl.FrameNumberTracker] */
        /* JADX WARN: Type inference failed for: r22v3, types: [android.hardware.camera2.impl.FrameNumberTracker] */
        /* JADX WARN: Unknown variable types count: 1 */
        @Override // android.hardware.camera2.ICameraDeviceCallbacks
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onResultReceived(android.hardware.camera2.impl.CameraMetadataNative r30, final android.hardware.camera2.impl.CaptureResultExtras r31, android.hardware.camera2.impl.PhysicalCaptureResultInfo[] r32) throws android.os.RemoteException {
            /*
                Method dump skipped, instructions count: 430
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: android.hardware.camera2.impl.CameraDeviceImpl.CameraDeviceCallbacks.onResultReceived(android.hardware.camera2.impl.CameraMetadataNative, android.hardware.camera2.impl.CaptureResultExtras, android.hardware.camera2.impl.PhysicalCaptureResultInfo[]):void");
        }

        @Override // android.hardware.camera2.ICameraDeviceCallbacks
        public void onPrepared(int streamId) {
            synchronized (CameraDeviceImpl.this.mInterfaceLock) {
                if (CameraDeviceImpl.this.mOfflineSessionImpl != null) {
                    CameraDeviceImpl.this.mOfflineSessionImpl.getCallbacks().onPrepared(streamId);
                    return;
                }
                OutputConfiguration output = (OutputConfiguration) CameraDeviceImpl.this.mConfiguredOutputs.get(streamId);
                StateCallbackKK sessionCallback = CameraDeviceImpl.this.mSessionStateCallback;
                if (sessionCallback != null) {
                    if (output == null) {
                        Log.w(CameraDeviceImpl.this.TAG, "onPrepared invoked for unknown output Surface");
                        return;
                    }
                    List<Surface> surfaces = output.getSurfaces();
                    for (Surface surface : surfaces) {
                        sessionCallback.onSurfacePrepared(surface);
                    }
                }
            }
        }

        @Override // android.hardware.camera2.ICameraDeviceCallbacks
        public void onRequestQueueEmpty() {
            synchronized (CameraDeviceImpl.this.mInterfaceLock) {
                if (CameraDeviceImpl.this.mOfflineSessionImpl != null) {
                    CameraDeviceImpl.this.mOfflineSessionImpl.getCallbacks().onRequestQueueEmpty();
                    return;
                }
                StateCallbackKK sessionCallback = CameraDeviceImpl.this.mSessionStateCallback;
                if (sessionCallback != null) {
                    sessionCallback.onRequestQueueEmpty();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class CameraHandlerExecutor implements Executor {
        private final Handler mHandler;

        public CameraHandlerExecutor(Handler handler) {
            Objects.requireNonNull(handler);
            this.mHandler = handler;
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable command) {
            this.mHandler.post(command);
        }
    }

    static Executor checkExecutor(Executor executor) {
        return executor == null ? checkAndWrapHandler(null) : executor;
    }

    public static <T> Executor checkExecutor(Executor executor, T callback) {
        return callback != null ? checkExecutor(executor) : executor;
    }

    public static Executor checkAndWrapHandler(Handler handler) {
        return new CameraHandlerExecutor(checkHandler(handler));
    }

    static Handler checkHandler(Handler handler) {
        if (handler != null) {
            return handler;
        }
        Looper looper = Looper.myLooper();
        if (looper != null) {
            return new Handler(looper);
        }
        throw new IllegalArgumentException("No handler given, and current thread has no looper!");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> Handler checkHandler(Handler handler, T callback) {
        if (callback != null) {
            return checkHandler(handler);
        }
        return handler;
    }

    private void checkIfCameraClosedOrInError() throws CameraAccessException {
        if (this.mRemoteDevice == null) {
            throw new IllegalStateException("CameraDevice was already closed");
        } else if (this.mInError) {
            throw new CameraAccessException(3, "The camera device has encountered a serious error");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isClosed() {
        return this.mClosing.get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CameraCharacteristics getCharacteristics() {
        return this.mCharacteristics;
    }

    @Override // android.os.IBinder.DeathRecipient
    public void binderDied() {
        String str = this.TAG;
        Log.w(str, "CameraDevice " + this.mCameraId + " died unexpectedly");
        if (this.mRemoteDevice != null) {
            this.mInError = true;
            Runnable r = new Runnable() { // from class: android.hardware.camera2.impl.CameraDeviceImpl.14
                @Override // java.lang.Runnable
                public void run() {
                    if (!CameraDeviceImpl.this.isClosed()) {
                        CameraDeviceImpl.this.mDeviceCallback.onError(CameraDeviceImpl.this, 5);
                    }
                }
            };
            long ident = Binder.clearCallingIdentity();
            try {
                this.mDeviceExecutor.execute(r);
            } finally {
                Binder.restoreCallingIdentity(ident);
            }
        }
    }

    @Override // android.hardware.camera2.CameraDevice
    public void setCameraAudioRestriction(int mode) throws CameraAccessException {
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            this.mRemoteDevice.setCameraAudioRestriction(mode);
        }
    }

    @Override // android.hardware.camera2.CameraDevice
    public int getCameraAudioRestriction() throws CameraAccessException {
        int globalAudioRestriction;
        synchronized (this.mInterfaceLock) {
            checkIfCameraClosedOrInError();
            globalAudioRestriction = this.mRemoteDevice.getGlobalAudioRestriction();
        }
        return globalAudioRestriction;
    }

    @Override // android.hardware.camera2.CameraDevice
    public void createExtensionSession(ExtensionSessionConfiguration extensionConfiguration) throws CameraAccessException {
        try {
            if (CameraExtensionCharacteristics.areAdvancedExtensionsSupported()) {
                this.mCurrentAdvancedExtensionSession = CameraAdvancedExtensionSessionImpl.createCameraAdvancedExtensionSession(this, this.mContext, extensionConfiguration);
            } else {
                this.mCurrentExtensionSession = CameraExtensionSessionImpl.createCameraExtensionSession(this, this.mContext, extensionConfiguration);
            }
        } catch (RemoteException e) {
            throw new CameraAccessException(3);
        }
    }
}
