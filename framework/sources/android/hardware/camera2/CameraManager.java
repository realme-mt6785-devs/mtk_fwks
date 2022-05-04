package android.hardware.camera2;

import android.Manifest;
import android.annotation.SystemApi;
import android.app.ActivityThread;
import android.common.OplusFeatureCache;
import android.content.Context;
import android.hardware.CameraStatus;
import android.hardware.ICameraService;
import android.hardware.ICameraServiceListener;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraInjectionSession;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.impl.CameraDeviceImpl;
import android.hardware.camera2.impl.CameraInjectionSessionImpl;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.params.SessionConfiguration;
import android.hardware.camera2.params.StreamConfiguration;
import android.hardware.camera2.utils.CameraIdAndSessionConfiguration;
import android.hardware.camera2.utils.ConcurrentCameraIdCombination;
import android.hardware.display.DisplayManager;
import android.os.Binder;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.ServiceSpecificException;
import android.os.SystemProperties;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import android.util.Size;
import android.view.Display;
import com.oplus.permission.IOplusPermissionCheckInjector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public final class CameraManager {
    private static final int API_VERSION_1 = 1;
    private static final int API_VERSION_2 = 2;
    private static final int CAMERA_TYPE_ALL = 1;
    private static final int CAMERA_TYPE_BACKWARD_COMPATIBLE = 0;
    private static final String TAG = "CameraManager";
    private static final int USE_CALLING_UID = -1;
    private final boolean DEBUG = false;
    private ICameraManagerExt mCameraManagerExt;
    private final Context mContext;
    private ArrayList<String> mDeviceIdList;
    private final Object mLock;

    public CameraManager(Context context) {
        Object obj = new Object();
        this.mLock = obj;
        synchronized (obj) {
            this.mContext = context;
            ICameraManagerExt call = CameraManagerExtPlugin.getInstance.call(new Object[0]);
            this.mCameraManagerExt = call;
            call.extendCameraManager(context.getOpPackageName());
        }
    }

    public String[] getCameraIdList() throws CameraAccessException {
        return CameraManagerGlobal.get().getCameraIdList();
    }

    public String[] getCameraIdListNoLazy() throws CameraAccessException {
        return CameraManagerGlobal.get().getCameraIdListNoLazy();
    }

    public Set<Set<String>> getConcurrentCameraIds() throws CameraAccessException {
        return CameraManagerGlobal.get().getConcurrentCameraIds();
    }

    public boolean isConcurrentSessionConfigurationSupported(Map<String, SessionConfiguration> cameraIdAndSessionConfig) throws CameraAccessException {
        return CameraManagerGlobal.get().isConcurrentSessionConfigurationSupported(cameraIdAndSessionConfig, this.mContext.getApplicationInfo().targetSdkVersion);
    }

    public void registerAvailabilityCallback(AvailabilityCallback callback, Handler handler) {
        CameraManagerGlobal.get().registerAvailabilityCallback(callback, CameraDeviceImpl.checkAndWrapHandler(handler));
    }

    public void registerAvailabilityCallback(Executor executor, AvailabilityCallback callback) {
        if (executor != null) {
            CameraManagerGlobal.get().registerAvailabilityCallback(callback, executor);
            return;
        }
        throw new IllegalArgumentException("executor was null");
    }

    public void unregisterAvailabilityCallback(AvailabilityCallback callback) {
        CameraManagerGlobal.get().unregisterAvailabilityCallback(callback);
    }

    public void registerTorchCallback(TorchCallback callback, Handler handler) {
        CameraManagerGlobal.get().registerTorchCallback(callback, CameraDeviceImpl.checkAndWrapHandler(handler));
    }

    public void registerTorchCallback(Executor executor, TorchCallback callback) {
        if (executor != null) {
            CameraManagerGlobal.get().registerTorchCallback(callback, executor);
            return;
        }
        throw new IllegalArgumentException("executor was null");
    }

    public void unregisterTorchCallback(TorchCallback callback) {
        CameraManagerGlobal.get().unregisterTorchCallback(callback);
    }

    private Size getDisplaySize() {
        Size ret = new Size(0, 0);
        try {
            DisplayManager displayManager = (DisplayManager) this.mContext.getSystemService(Context.DISPLAY_SERVICE);
            Display display = displayManager.getDisplay(0);
            if (display != null) {
                int width = display.getWidth();
                int height = display.getHeight();
                if (height > width) {
                    height = width;
                    width = display.getHeight();
                }
                return new Size(width, height);
            }
            Log.e(TAG, "Invalid default display!");
            return ret;
        } catch (Exception e) {
            Log.e(TAG, "getDisplaySize Failed. " + e.toString());
            return ret;
        }
    }

    private Map<String, StreamConfiguration[]> getPhysicalCameraMultiResolutionConfigs(String cameraId, CameraMetadataNative info, ICameraService cameraService) throws CameraAccessException {
        HashMap<String, StreamConfiguration[]> multiResolutionStreamConfigurations = new HashMap<>();
        Boolean multiResolutionStreamSupported = (Boolean) info.get(CameraCharacteristics.SCALER_MULTI_RESOLUTION_STREAM_SUPPORTED);
        if (multiResolutionStreamSupported == null || !multiResolutionStreamSupported.booleanValue()) {
            return multiResolutionStreamConfigurations;
        }
        Set<String> physicalCameraIds = info.getPhysicalCameraIds();
        if (physicalCameraIds.size() != 0 || !info.isUltraHighResolutionSensor()) {
            try {
                for (String physicalCameraId : physicalCameraIds) {
                    CameraMetadataNative physicalCameraInfo = cameraService.getCameraCharacteristics(physicalCameraId, this.mContext.getApplicationInfo().targetSdkVersion);
                    StreamConfiguration[] configs = (StreamConfiguration[]) physicalCameraInfo.get(CameraCharacteristics.SCALER_PHYSICAL_CAMERA_MULTI_RESOLUTION_STREAM_CONFIGURATIONS);
                    if (configs != null) {
                        multiResolutionStreamConfigurations.put(physicalCameraId, configs);
                    }
                }
            } catch (RemoteException e) {
                ServiceSpecificException sse = new ServiceSpecificException(4, "Camera service is currently unavailable");
                throwAsPublicException(sse);
            }
            return multiResolutionStreamConfigurations;
        }
        StreamConfiguration[] configs2 = (StreamConfiguration[]) info.get(CameraCharacteristics.SCALER_PHYSICAL_CAMERA_MULTI_RESOLUTION_STREAM_CONFIGURATIONS);
        if (configs2 != null) {
            multiResolutionStreamConfigurations.put(cameraId, configs2);
        }
        return multiResolutionStreamConfigurations;
    }

    public CameraCharacteristics getCameraCharacteristics(String cameraId) throws CameraAccessException {
        CameraCharacteristics characteristics = null;
        if (!CameraManagerGlobal.sCameraServiceDisabled) {
            synchronized (this.mLock) {
                ICameraService cameraService = CameraManagerGlobal.get().getCameraService();
                if (cameraService != null) {
                    try {
                        try {
                            Size displaySize = getDisplaySize();
                            CameraMetadataNative info = cameraService.getCameraCharacteristics(cameraId, this.mContext.getApplicationInfo().targetSdkVersion);
                            try {
                                info.setCameraId(Integer.parseInt(cameraId));
                            } catch (NumberFormatException e) {
                                Log.v(TAG, "Failed to parse camera Id " + cameraId + " to integer");
                            }
                            boolean hasConcurrentStreams = CameraManagerGlobal.get().cameraIdHasConcurrentStreamsLocked(cameraId);
                            info.setHasMandatoryConcurrentStreams(hasConcurrentStreams);
                            info.setDisplaySize(displaySize);
                            Map<String, StreamConfiguration[]> multiResolutionSizeMap = getPhysicalCameraMultiResolutionConfigs(cameraId, info, cameraService);
                            if (multiResolutionSizeMap.size() > 0) {
                                info.setMultiResolutionStreamConfigurationMap(multiResolutionSizeMap);
                            }
                            characteristics = new CameraCharacteristics(info);
                        } catch (ServiceSpecificException e2) {
                            throwAsPublicException(e2);
                        }
                    } catch (RemoteException e3) {
                        throw new CameraAccessException(2, "Camera service is currently unavailable", e3);
                    }
                } else {
                    throw new CameraAccessException(2, "Camera service is currently unavailable");
                }
            }
            return characteristics;
        }
        throw new IllegalArgumentException("No cameras available on device");
    }

    public CameraExtensionCharacteristics getCameraExtensionCharacteristics(String cameraId) throws CameraAccessException {
        CameraCharacteristics chars = getCameraCharacteristics(cameraId);
        return new CameraExtensionCharacteristics(this.mContext, cameraId, chars);
    }

    private Map<String, CameraCharacteristics> getPhysicalIdToCharsMap(CameraCharacteristics chars) throws CameraAccessException {
        HashMap<String, CameraCharacteristics> physicalIdsToChars = new HashMap<>();
        Set<String> physicalCameraIds = chars.getPhysicalCameraIds();
        for (String physicalCameraId : physicalCameraIds) {
            CameraCharacteristics physicalChars = getCameraCharacteristics(physicalCameraId);
            physicalIdsToChars.put(physicalCameraId, physicalChars);
        }
        return physicalIdsToChars;
    }

    private CameraDevice openCameraDeviceUserAsync(String cameraId, CameraDevice.StateCallback callback, Executor executor, int uid, int oomScoreOffset) throws CameraAccessException {
        CameraDeviceImpl deviceImpl;
        ICameraService cameraService;
        CameraCharacteristics characteristics = getCameraCharacteristics(cameraId);
        Map<String, CameraCharacteristics> physicalIdsToChars = getPhysicalIdToCharsMap(characteristics);
        synchronized (this.mLock) {
            ICameraDeviceUser cameraUser = null;
            deviceImpl = new CameraDeviceImpl(cameraId, callback, executor, characteristics, physicalIdsToChars, this.mContext.getApplicationInfo().targetSdkVersion, this.mContext);
            ICameraDeviceCallbacks callbacks = deviceImpl.getCallbacks();
            try {
                cameraService = CameraManagerGlobal.get().getCameraService();
            } catch (RemoteException e) {
                ServiceSpecificException sse = new ServiceSpecificException(4, "Camera service is currently unavailable");
                deviceImpl.setRemoteFailure(sse);
                throwAsPublicException(sse);
            } catch (ServiceSpecificException e2) {
                if (e2.errorCode != 9) {
                    if (!(e2.errorCode == 7 || e2.errorCode == 8 || e2.errorCode == 6 || e2.errorCode == 4 || e2.errorCode == 10)) {
                        throwAsPublicException(e2);
                    }
                    deviceImpl.setRemoteFailure(e2);
                    if (e2.errorCode == 6 || e2.errorCode == 4 || e2.errorCode == 7) {
                        throwAsPublicException(e2);
                    }
                } else {
                    throw new AssertionError("Should've gone down the shim path");
                }
            }
            if (cameraService != null) {
                cameraUser = cameraService.connectDevice(callbacks, cameraId, this.mCameraManagerExt.extendGetComponentName(), this.mContext.getAttributionTag(), uid, oomScoreOffset, this.mContext.getApplicationInfo().targetSdkVersion);
                deviceImpl.setRemoteDevice(cameraUser);
            } else {
                throw new ServiceSpecificException(4, "Camera service is currently unavailable");
            }
        }
        return deviceImpl;
    }

    public void openCamera(String cameraId, CameraDevice.StateCallback callback, Handler handler) throws CameraAccessException {
        openCameraForUid(cameraId, callback, CameraDeviceImpl.checkAndWrapHandler(handler), -1);
    }

    public void openCamera(String cameraId, Executor executor, CameraDevice.StateCallback callback) throws CameraAccessException {
        if (executor != null) {
            openCameraForUid(cameraId, callback, executor, -1);
            return;
        }
        throw new IllegalArgumentException("executor was null");
    }

    @SystemApi
    public void openCamera(String cameraId, int oomScoreOffset, Executor executor, CameraDevice.StateCallback callback) throws CameraAccessException {
        if (executor == null) {
            throw new IllegalArgumentException("executor was null");
        } else if (oomScoreOffset >= 0) {
            openCameraForUid(cameraId, callback, executor, -1, oomScoreOffset);
        } else {
            throw new IllegalArgumentException("oomScoreOffset < 0, cannot increase priority of camera client");
        }
    }

    public void openCameraForUid(String cameraId, CameraDevice.StateCallback callback, Executor executor, int clientUid, int oomScoreOffset) throws CameraAccessException {
        if (cameraId == null) {
            throw new IllegalArgumentException("cameraId was null");
        } else if (callback == null) {
            throw new IllegalArgumentException("callback was null");
        } else if (CameraManagerGlobal.sCameraServiceDisabled) {
            throw new IllegalArgumentException("No cameras available on device");
        } else if (!this.mCameraManagerExt.interceptOpenCameraForUid()) {
            openCameraDeviceUserAsync(cameraId, callback, executor, clientUid, oomScoreOffset);
        }
    }

    public void openCameraForUid(String cameraId, CameraDevice.StateCallback callback, Executor executor, int clientUid) throws CameraAccessException {
        if (((IOplusPermissionCheckInjector) OplusFeatureCache.getOrCreate(IOplusPermissionCheckInjector.DEFAULT, new Object[0])).checkPermission(Manifest.permission.CAMERA, Binder.getCallingPid(), Binder.getCallingUid(), "openCamera")) {
            openCameraForUid(cameraId, callback, executor, clientUid, 0);
        }
    }

    public void setTorchMode(String cameraId, boolean enabled) throws CameraAccessException {
        if (!CameraManagerGlobal.sCameraServiceDisabled) {
            CameraManagerGlobal.get().setTorchMode(cameraId, enabled);
            return;
        }
        throw new IllegalArgumentException("No cameras available on device");
    }

    /* loaded from: classes.dex */
    public static abstract class AvailabilityCallback {
        public void onCameraAvailable(String cameraId) {
        }

        public void onCameraUnavailable(String cameraId) {
        }

        public void onCameraAccessPrioritiesChanged() {
        }

        public void onPhysicalCameraAvailable(String cameraId, String physicalCameraId) {
        }

        public void onPhysicalCameraUnavailable(String cameraId, String physicalCameraId) {
        }

        @SystemApi
        public void onCameraOpened(String cameraId, String packageId) {
        }

        @SystemApi
        public void onCameraClosed(String cameraId) {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class TorchCallback {
        public void onTorchModeUnavailable(String cameraId) {
        }

        public void onTorchModeChanged(String cameraId, boolean enabled) {
        }
    }

    public static void throwAsPublicException(Throwable t) throws CameraAccessException {
        int reason;
        if (t instanceof ServiceSpecificException) {
            ServiceSpecificException e = (ServiceSpecificException) t;
            switch (e.errorCode) {
                case 1:
                    throw new SecurityException(e.getMessage(), e);
                case 2:
                case 3:
                    throw new IllegalArgumentException(e.getMessage(), e);
                case 4:
                    reason = 2;
                    break;
                case 5:
                default:
                    reason = 3;
                    break;
                case 6:
                    reason = 1;
                    break;
                case 7:
                    reason = 4;
                    break;
                case 8:
                    reason = 5;
                    break;
                case 9:
                    reason = 1000;
                    break;
            }
            throw new CameraAccessException(reason, e.getMessage(), e);
        } else if (t instanceof DeadObjectException) {
            throw new CameraAccessException(2, "Camera service has died unexpectedly", t);
        } else if (t instanceof RemoteException) {
            throw new UnsupportedOperationException("An unknown RemoteException was thrown which should never happen.", t);
        } else if (t instanceof RuntimeException) {
            throw ((RuntimeException) t);
        }
    }

    public static boolean isHiddenPhysicalCamera(String cameraId) {
        try {
            ICameraService cameraService = CameraManagerGlobal.get().getCameraService();
            if (cameraService == null) {
                return false;
            }
            return cameraService.isHiddenPhysicalCamera(cameraId);
        } catch (RemoteException e) {
            return false;
        }
    }

    public void injectCamera(String packageName, String internalCamId, String externalCamId, Executor executor, CameraInjectionSession.InjectionStatusCallback callback) throws CameraAccessException, SecurityException, IllegalArgumentException {
        if (!CameraManagerGlobal.sCameraServiceDisabled) {
            ICameraService cameraService = CameraManagerGlobal.get().getCameraService();
            if (cameraService != null) {
                synchronized (this.mLock) {
                    try {
                        try {
                            CameraInjectionSessionImpl injectionSessionImpl = new CameraInjectionSessionImpl(callback, executor);
                            ICameraInjectionCallback cameraInjectionCallback = injectionSessionImpl.getCallback();
                            ICameraInjectionSession injectionSession = cameraService.injectCamera(packageName, internalCamId, externalCamId, cameraInjectionCallback);
                            injectionSessionImpl.setRemoteInjectionSession(injectionSession);
                        } catch (ServiceSpecificException e) {
                            throwAsPublicException(e);
                        }
                    } catch (RemoteException e2) {
                        ServiceSpecificException sse = new ServiceSpecificException(4, "Camera service is currently unavailable");
                        throwAsPublicException(sse);
                    }
                }
                return;
            }
            throw new CameraAccessException(2, "Camera service is currently unavailable");
        }
        throw new IllegalArgumentException("No cameras available on device");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class CameraManagerGlobal extends ICameraServiceListener.Stub implements IBinder.DeathRecipient {
        private static final String CAMERA_SERVICE_BINDER_NAME = "media.camera";
        private static final String TAG = "CameraManagerGlobal";
        private static final CameraManagerGlobal gCameraManager = new CameraManagerGlobal();
        public static final boolean sCameraServiceDisabled = SystemProperties.getBoolean("config.disable_cameraservice", false);
        private ICameraService mCameraService;
        private final boolean DEBUG = false;
        private final int CAMERA_SERVICE_RECONNECT_DELAY_MS = 1000;
        private final ScheduledExecutorService mScheduler = Executors.newScheduledThreadPool(1);
        private final ArrayMap<String, Integer> mDeviceStatus = new ArrayMap<>();
        private final ArrayMap<String, ArrayList<String>> mUnavailablePhysicalDevices = new ArrayMap<>();
        private final Set<Set<String>> mConcurrentCameraIdCombinations = new ArraySet();
        private final ArrayMap<AvailabilityCallback, Executor> mCallbackMap = new ArrayMap<>();
        private Binder mTorchClientBinder = new Binder();
        private final ArrayMap<String, Integer> mTorchStatus = new ArrayMap<>();
        private final ArrayMap<TorchCallback, Executor> mTorchCallbackMap = new ArrayMap<>();
        private final Object mLock = new Object();

        private CameraManagerGlobal() {
        }

        public static CameraManagerGlobal get() {
            return gCameraManager;
        }

        @Override // android.hardware.ICameraServiceListener.Stub, android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public ICameraService getCameraService() {
            ICameraService iCameraService;
            synchronized (this.mLock) {
                connectCameraServiceLocked();
                if (this.mCameraService == null && !sCameraServiceDisabled) {
                    Log.e(TAG, "Camera service is unavailable");
                }
                iCameraService = this.mCameraService;
            }
            return iCameraService;
        }

        private void connectCameraServiceLocked() {
            String[] strArr;
            CameraManagerExtPlugin.getInstance.call(new Object[0]).extendSetPackageName();
            if (this.mCameraService == null && !sCameraServiceDisabled) {
                Log.i(TAG, "Connecting to camera service");
                IBinder cameraServiceBinder = ServiceManager.getService(CAMERA_SERVICE_BINDER_NAME);
                if (cameraServiceBinder != null) {
                    try {
                        cameraServiceBinder.linkToDeath(this, 0);
                        ICameraService cameraService = ICameraService.Stub.asInterface(cameraServiceBinder);
                        try {
                            CameraMetadataNative.setupGlobalVendorTagDescriptor();
                        } catch (ServiceSpecificException e) {
                            handleRecoverableSetupErrors(e);
                        }
                        try {
                            CameraStatus[] cameraStatuses = cameraService.addListener(this);
                            for (CameraStatus c : cameraStatuses) {
                                onStatusChangedLocked(c.status, c.cameraId);
                                if (c.unavailablePhysicalCameras != null) {
                                    for (String unavailPhysicalCamera : c.unavailablePhysicalCameras) {
                                        onPhysicalCameraStatusChangedLocked(0, c.cameraId, unavailPhysicalCamera);
                                    }
                                }
                            }
                            this.mCameraService = cameraService;
                        } catch (RemoteException e2) {
                        } catch (ServiceSpecificException e3) {
                            throw new IllegalStateException("Failed to register a camera service listener", e3);
                        }
                        try {
                            ConcurrentCameraIdCombination[] cameraIdCombinations = cameraService.getConcurrentCameraIds();
                            for (ConcurrentCameraIdCombination comb : cameraIdCombinations) {
                                this.mConcurrentCameraIdCombinations.add(comb.getConcurrentCameraIdCombination());
                            }
                        } catch (RemoteException e4) {
                        } catch (ServiceSpecificException e5) {
                            throw new IllegalStateException("Failed to get concurrent camera id combinations", e5);
                        }
                    } catch (RemoteException e6) {
                    }
                }
            }
        }

        private String[] extractCameraIdListLocked() {
            int idCount = 0;
            for (int i = 0; i < this.mDeviceStatus.size(); i++) {
                int status = this.mDeviceStatus.valueAt(i).intValue();
                if (!(status == 0 || status == 2)) {
                    idCount++;
                }
            }
            String[] cameraIds = new String[idCount];
            int idCount2 = 0;
            for (int i2 = 0; i2 < this.mDeviceStatus.size(); i2++) {
                int status2 = this.mDeviceStatus.valueAt(i2).intValue();
                if (!(status2 == 0 || status2 == 2)) {
                    cameraIds[idCount2] = this.mDeviceStatus.keyAt(i2);
                    idCount2++;
                }
            }
            return cameraIds;
        }

        private Set<Set<String>> extractConcurrentCameraIdListLocked() {
            Set<Set<String>> concurrentCameraIds = new ArraySet<>();
            for (Set<String> cameraIds : this.mConcurrentCameraIdCombinations) {
                Set<String> extractedCameraIds = new ArraySet<>();
                for (String cameraId : cameraIds) {
                    Integer status = this.mDeviceStatus.get(cameraId);
                    if (!(status == null || status.intValue() == 2 || status.intValue() == 0)) {
                        extractedCameraIds.add(cameraId);
                    }
                }
                concurrentCameraIds.add(extractedCameraIds);
            }
            return concurrentCameraIds;
        }

        private static void sortCameraIds(String[] cameraIds) {
            Arrays.sort(cameraIds, new Comparator<String>() { // from class: android.hardware.camera2.CameraManager.CameraManagerGlobal.1
                public int compare(String s1, String s2) {
                    int s1Int;
                    int s2Int;
                    try {
                        s1Int = Integer.parseInt(s1);
                    } catch (NumberFormatException e) {
                        s1Int = -1;
                    }
                    try {
                        s2Int = Integer.parseInt(s2);
                    } catch (NumberFormatException e2) {
                        s2Int = -1;
                    }
                    if (s1Int >= 0 && s2Int >= 0) {
                        return s1Int - s2Int;
                    }
                    if (s1Int >= 0) {
                        return -1;
                    }
                    if (s2Int >= 0) {
                        return 1;
                    }
                    return s1.compareTo(s2);
                }
            });
        }

        public static boolean cameraStatusesContains(CameraStatus[] cameraStatuses, String id) {
            for (CameraStatus c : cameraStatuses) {
                if (c.cameraId.equals(id)) {
                    return true;
                }
            }
            return false;
        }

        public String[] getCameraIdListNoLazy() {
            String[] cameraIds;
            if (sCameraServiceDisabled) {
                return new String[0];
            }
            ICameraServiceListener.Stub testListener = new ICameraServiceListener.Stub() { // from class: android.hardware.camera2.CameraManager.CameraManagerGlobal.2
                @Override // android.hardware.ICameraServiceListener
                public void onStatusChanged(int status, String id) throws RemoteException {
                }

                @Override // android.hardware.ICameraServiceListener
                public void onPhysicalCameraStatusChanged(int status, String id, String physicalId) throws RemoteException {
                }

                @Override // android.hardware.ICameraServiceListener
                public void onTorchStatusChanged(int status, String id) throws RemoteException {
                }

                @Override // android.hardware.ICameraServiceListener
                public void onCameraAccessPrioritiesChanged() {
                }

                @Override // android.hardware.ICameraServiceListener
                public void onCameraOpened(String id, String clientPackageId) {
                }

                @Override // android.hardware.ICameraServiceListener
                public void onCameraClosed(String id) {
                }
            };
            synchronized (this.mLock) {
                connectCameraServiceLocked();
                try {
                    CameraStatus[] cameraStatuses = this.mCameraService.addListener(testListener);
                    this.mCameraService.removeListener(testListener);
                    for (CameraStatus c : cameraStatuses) {
                        onStatusChangedLocked(c.status, c.cameraId);
                    }
                    Set<String> deviceCameraIds = this.mDeviceStatus.keySet();
                    ArrayList<String> deviceIdsToRemove = new ArrayList<>();
                    for (String deviceCameraId : deviceCameraIds) {
                        if (!cameraStatusesContains(cameraStatuses, deviceCameraId)) {
                            deviceIdsToRemove.add(deviceCameraId);
                        }
                    }
                    Iterator<String> it = deviceIdsToRemove.iterator();
                    while (it.hasNext()) {
                        String id = it.next();
                        onStatusChangedLocked(0, id);
                        this.mTorchStatus.remove(id);
                    }
                } catch (RemoteException e) {
                } catch (ServiceSpecificException e2) {
                    throw new IllegalStateException("Failed to register a camera service listener", e2);
                }
                cameraIds = extractCameraIdListLocked();
            }
            sortCameraIds(cameraIds);
            return cameraIds;
        }

        public String[] getCameraIdList() {
            String[] cameraIds;
            synchronized (this.mLock) {
                connectCameraServiceLocked();
                cameraIds = extractCameraIdListLocked();
            }
            sortCameraIds(cameraIds);
            return cameraIds;
        }

        public Set<Set<String>> getConcurrentCameraIds() {
            Set<Set<String>> concurrentStreamingCameraIds;
            synchronized (this.mLock) {
                connectCameraServiceLocked();
                concurrentStreamingCameraIds = extractConcurrentCameraIdListLocked();
            }
            return concurrentStreamingCameraIds;
        }

        public boolean isConcurrentSessionConfigurationSupported(Map<String, SessionConfiguration> cameraIdsAndSessionConfigurations, int targetSdkVersion) throws CameraAccessException {
            if (cameraIdsAndSessionConfigurations != null) {
                int size = cameraIdsAndSessionConfigurations.size();
                if (size != 0) {
                    synchronized (this.mLock) {
                        boolean subsetFound = false;
                        for (Set<String> combination : this.mConcurrentCameraIdCombinations) {
                            if (combination.containsAll(cameraIdsAndSessionConfigurations.keySet())) {
                                subsetFound = true;
                            }
                        }
                        if (!subsetFound) {
                            Log.v(TAG, "isConcurrentSessionConfigurationSupported called with a subset ofcamera ids not returned by getConcurrentCameraIds");
                            return false;
                        }
                        CameraIdAndSessionConfiguration[] cameraIdsAndConfigs = new CameraIdAndSessionConfiguration[size];
                        int i = 0;
                        for (Map.Entry<String, SessionConfiguration> pair : cameraIdsAndSessionConfigurations.entrySet()) {
                            cameraIdsAndConfigs[i] = new CameraIdAndSessionConfiguration(pair.getKey(), pair.getValue());
                            i++;
                        }
                        try {
                            return this.mCameraService.isConcurrentSessionConfigurationSupported(cameraIdsAndConfigs, targetSdkVersion);
                        } catch (RemoteException e) {
                            throw new CameraAccessException(2, "Camera service is currently unavailable", e);
                        } catch (ServiceSpecificException e2) {
                            CameraManager.throwAsPublicException(e2);
                            return false;
                        }
                    }
                } else {
                    throw new IllegalArgumentException("camera id and session combination is empty");
                }
            } else {
                throw new IllegalArgumentException("cameraIdsAndSessionConfigurations was null");
            }
        }

        public boolean cameraIdHasConcurrentStreamsLocked(String cameraId) {
            if (!this.mDeviceStatus.containsKey(cameraId)) {
                return false;
            }
            for (Set<String> comb : this.mConcurrentCameraIdCombinations) {
                if (comb.contains(cameraId)) {
                    return true;
                }
            }
            return false;
        }

        public void setTorchMode(String cameraId, boolean enabled) throws CameraAccessException {
            synchronized (this.mLock) {
                try {
                    if (cameraId != null) {
                        ICameraService cameraService = getCameraService();
                        if (cameraService != null) {
                            try {
                                cameraService.setTorchMode(cameraId, enabled, this.mTorchClientBinder);
                            } catch (RemoteException e) {
                                throw new CameraAccessException(2, "Camera service is currently unavailable");
                            } catch (ServiceSpecificException e2) {
                                CameraManager.throwAsPublicException(e2);
                            }
                        } else {
                            throw new CameraAccessException(2, "Camera service is currently unavailable");
                        }
                    } else {
                        throw new IllegalArgumentException("cameraId was null");
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        private void handleRecoverableSetupErrors(ServiceSpecificException e) {
            switch (e.errorCode) {
                case 4:
                    Log.w(TAG, e.getMessage());
                    return;
                default:
                    throw new IllegalStateException(e);
            }
        }

        private boolean isAvailable(int status) {
            switch (status) {
                case 1:
                    return true;
                default:
                    return false;
            }
        }

        private boolean validStatus(int status) {
            switch (status) {
                case -2:
                case 0:
                case 1:
                case 2:
                    return true;
                case -1:
                default:
                    return false;
            }
        }

        private boolean validTorchStatus(int status) {
            switch (status) {
                case 0:
                case 1:
                case 2:
                    return true;
                default:
                    return false;
            }
        }

        private void postSingleAccessPriorityChangeUpdate(final AvailabilityCallback callback, Executor executor) {
            long ident = Binder.clearCallingIdentity();
            try {
                executor.execute(new Runnable() { // from class: android.hardware.camera2.CameraManager.CameraManagerGlobal.3
                    @Override // java.lang.Runnable
                    public void run() {
                        callback.onCameraAccessPrioritiesChanged();
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(ident);
            }
        }

        private void postSingleCameraOpenedUpdate(final AvailabilityCallback callback, Executor executor, final String id, final String packageId) {
            long ident = Binder.clearCallingIdentity();
            try {
                executor.execute(new Runnable() { // from class: android.hardware.camera2.CameraManager.CameraManagerGlobal.4
                    @Override // java.lang.Runnable
                    public void run() {
                        callback.onCameraOpened(id, packageId);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(ident);
            }
        }

        private void postSingleCameraClosedUpdate(final AvailabilityCallback callback, Executor executor, final String id) {
            long ident = Binder.clearCallingIdentity();
            try {
                executor.execute(new Runnable() { // from class: android.hardware.camera2.CameraManager.CameraManagerGlobal.5
                    @Override // java.lang.Runnable
                    public void run() {
                        callback.onCameraClosed(id);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(ident);
            }
        }

        private void postSingleUpdate(final AvailabilityCallback callback, Executor executor, final String id, final String physicalId, int status) {
            long ident;
            if (isAvailable(status)) {
                ident = Binder.clearCallingIdentity();
                try {
                    executor.execute(new Runnable() { // from class: android.hardware.camera2.CameraManager.CameraManagerGlobal.6
                        @Override // java.lang.Runnable
                        public void run() {
                            String str = physicalId;
                            if (str == null) {
                                callback.onCameraAvailable(id);
                            } else {
                                callback.onPhysicalCameraAvailable(id, str);
                            }
                        }
                    });
                } finally {
                }
            } else {
                ident = Binder.clearCallingIdentity();
                try {
                    executor.execute(new Runnable() { // from class: android.hardware.camera2.CameraManager.CameraManagerGlobal.7
                        @Override // java.lang.Runnable
                        public void run() {
                            String str = physicalId;
                            if (str == null) {
                                callback.onCameraUnavailable(id);
                            } else {
                                callback.onPhysicalCameraUnavailable(id, str);
                            }
                        }
                    });
                } finally {
                }
            }
        }

        private void postSingleTorchUpdate(final TorchCallback callback, Executor executor, final String id, final int status) {
            long ident;
            Log.i(TAG, "postSingleTorchUpdate  cameraId=" + id);
            String packageName = ActivityThread.currentOpPackageName();
            if (Integer.valueOf(id).intValue() <= 1 || !packageName.equals("android.camera.cts")) {
                switch (status) {
                    case 1:
                    case 2:
                        ident = Binder.clearCallingIdentity();
                        try {
                            executor.execute(new Runnable() { // from class: android.hardware.camera2.CameraManager$CameraManagerGlobal$$ExternalSyntheticLambda2
                                @Override // java.lang.Runnable
                                public final void run() {
                                    CameraManager.TorchCallback torchCallback = CameraManager.TorchCallback.this;
                                    String str = id;
                                    int i = status;
                                    torchCallback.onTorchModeChanged(str, status == 2);
                                }
                            });
                            return;
                        } finally {
                        }
                    default:
                        ident = Binder.clearCallingIdentity();
                        try {
                            executor.execute(new Runnable() { // from class: android.hardware.camera2.CameraManager$CameraManagerGlobal$$ExternalSyntheticLambda1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    CameraManager.TorchCallback.this.onTorchModeUnavailable(id);
                                }
                            });
                            return;
                        } finally {
                        }
                }
            }
        }

        private void updateCallbackLocked(AvailabilityCallback callback, Executor executor) {
            for (int i = 0; i < this.mDeviceStatus.size(); i++) {
                String id = this.mDeviceStatus.keyAt(i);
                Integer status = this.mDeviceStatus.valueAt(i);
                postSingleUpdate(callback, executor, id, null, status.intValue());
                if (isAvailable(status.intValue()) && this.mUnavailablePhysicalDevices.containsKey(id)) {
                    ArrayList<String> unavailableIds = this.mUnavailablePhysicalDevices.get(id);
                    Iterator<String> it = unavailableIds.iterator();
                    while (it.hasNext()) {
                        String unavailableId = it.next();
                        postSingleUpdate(callback, executor, id, unavailableId, 0);
                    }
                }
            }
        }

        private void onStatusChangedLocked(int status, String id) {
            Integer oldStatus;
            if (!validStatus(status)) {
                Log.e(TAG, String.format("Ignoring invalid device %s status 0x%x", id, Integer.valueOf(status)));
                return;
            }
            if (status == 0) {
                oldStatus = this.mDeviceStatus.remove(id);
                this.mUnavailablePhysicalDevices.remove(id);
            } else {
                oldStatus = this.mDeviceStatus.put(id, Integer.valueOf(status));
                if (oldStatus == null) {
                    this.mUnavailablePhysicalDevices.put(id, new ArrayList<>());
                }
            }
            if (oldStatus != null && oldStatus.intValue() == status) {
                return;
            }
            if (oldStatus == null || isAvailable(status) != isAvailable(oldStatus.intValue())) {
                int callbackCount = this.mCallbackMap.size();
                for (int i = 0; i < callbackCount; i++) {
                    Executor executor = this.mCallbackMap.valueAt(i);
                    AvailabilityCallback callback = this.mCallbackMap.keyAt(i);
                    postSingleUpdate(callback, executor, id, null, status);
                }
            }
        }

        private void onPhysicalCameraStatusChangedLocked(int status, String id, String physicalId) {
            if (!validStatus(status)) {
                Log.e(TAG, String.format("Ignoring invalid device %s physical device %s status 0x%x", id, physicalId, Integer.valueOf(status)));
            } else if (!this.mDeviceStatus.containsKey(id) || !isAvailable(this.mDeviceStatus.get(id).intValue()) || !this.mUnavailablePhysicalDevices.containsKey(id)) {
                Log.e(TAG, String.format("Camera %s is not available. Ignore physical camera status change", id));
            } else {
                ArrayList<String> unavailablePhysicalDevices = this.mUnavailablePhysicalDevices.get(id);
                if (!isAvailable(status) && !unavailablePhysicalDevices.contains(physicalId)) {
                    unavailablePhysicalDevices.add(physicalId);
                } else if (isAvailable(status) && unavailablePhysicalDevices.contains(physicalId)) {
                    unavailablePhysicalDevices.remove(physicalId);
                } else {
                    return;
                }
                int callbackCount = this.mCallbackMap.size();
                for (int i = 0; i < callbackCount; i++) {
                    Executor executor = this.mCallbackMap.valueAt(i);
                    AvailabilityCallback callback = this.mCallbackMap.keyAt(i);
                    postSingleUpdate(callback, executor, id, physicalId, status);
                }
            }
        }

        private void updateTorchCallbackLocked(TorchCallback callback, Executor executor) {
            for (int i = 0; i < this.mTorchStatus.size(); i++) {
                String id = this.mTorchStatus.keyAt(i);
                Integer status = this.mTorchStatus.valueAt(i);
                postSingleTorchUpdate(callback, executor, id, status.intValue());
            }
        }

        private void onTorchStatusChangedLocked(int status, String id) {
            if (!validTorchStatus(status)) {
                Log.e(TAG, String.format("Ignoring invalid device %s torch status 0x%x", id, Integer.valueOf(status)));
                return;
            }
            Integer oldStatus = this.mTorchStatus.put(id, Integer.valueOf(status));
            if (oldStatus == null || oldStatus.intValue() != status) {
                int callbackCount = this.mTorchCallbackMap.size();
                for (int i = 0; i < callbackCount; i++) {
                    Executor executor = this.mTorchCallbackMap.valueAt(i);
                    TorchCallback callback = this.mTorchCallbackMap.keyAt(i);
                    postSingleTorchUpdate(callback, executor, id, status);
                }
            }
        }

        public void registerAvailabilityCallback(AvailabilityCallback callback, Executor executor) {
            synchronized (this.mLock) {
                connectCameraServiceLocked();
                Executor oldExecutor = this.mCallbackMap.put(callback, executor);
                if (oldExecutor == null) {
                    updateCallbackLocked(callback, executor);
                }
                if (this.mCameraService == null) {
                    scheduleCameraServiceReconnectionLocked();
                }
            }
        }

        public void unregisterAvailabilityCallback(AvailabilityCallback callback) {
            synchronized (this.mLock) {
                this.mCallbackMap.remove(callback);
            }
        }

        public void registerTorchCallback(TorchCallback callback, Executor executor) {
            synchronized (this.mLock) {
                connectCameraServiceLocked();
                Executor oldExecutor = this.mTorchCallbackMap.put(callback, executor);
                if (oldExecutor == null) {
                    updateTorchCallbackLocked(callback, executor);
                }
                if (this.mCameraService == null) {
                    scheduleCameraServiceReconnectionLocked();
                }
            }
        }

        public void unregisterTorchCallback(TorchCallback callback) {
            synchronized (this.mLock) {
                this.mTorchCallbackMap.remove(callback);
            }
        }

        @Override // android.hardware.ICameraServiceListener
        public void onStatusChanged(int status, String cameraId) throws RemoteException {
            synchronized (this.mLock) {
                onStatusChangedLocked(status, cameraId);
            }
        }

        @Override // android.hardware.ICameraServiceListener
        public void onPhysicalCameraStatusChanged(int status, String cameraId, String physicalCameraId) throws RemoteException {
            synchronized (this.mLock) {
                onPhysicalCameraStatusChangedLocked(status, cameraId, physicalCameraId);
            }
        }

        @Override // android.hardware.ICameraServiceListener
        public void onTorchStatusChanged(int status, String cameraId) throws RemoteException {
            synchronized (this.mLock) {
                onTorchStatusChangedLocked(status, cameraId);
            }
        }

        @Override // android.hardware.ICameraServiceListener
        public void onCameraAccessPrioritiesChanged() {
            synchronized (this.mLock) {
                int callbackCount = this.mCallbackMap.size();
                for (int i = 0; i < callbackCount; i++) {
                    Executor executor = this.mCallbackMap.valueAt(i);
                    AvailabilityCallback callback = this.mCallbackMap.keyAt(i);
                    postSingleAccessPriorityChangeUpdate(callback, executor);
                }
            }
        }

        @Override // android.hardware.ICameraServiceListener
        public void onCameraOpened(String cameraId, String clientPackageId) {
            synchronized (this.mLock) {
                int callbackCount = this.mCallbackMap.size();
                for (int i = 0; i < callbackCount; i++) {
                    Executor executor = this.mCallbackMap.valueAt(i);
                    AvailabilityCallback callback = this.mCallbackMap.keyAt(i);
                    postSingleCameraOpenedUpdate(callback, executor, cameraId, clientPackageId);
                }
            }
        }

        @Override // android.hardware.ICameraServiceListener
        public void onCameraClosed(String cameraId) {
            synchronized (this.mLock) {
                int callbackCount = this.mCallbackMap.size();
                for (int i = 0; i < callbackCount; i++) {
                    Executor executor = this.mCallbackMap.valueAt(i);
                    AvailabilityCallback callback = this.mCallbackMap.keyAt(i);
                    postSingleCameraClosedUpdate(callback, executor, cameraId);
                }
            }
        }

        private void scheduleCameraServiceReconnectionLocked() {
            if (!this.mCallbackMap.isEmpty() || !this.mTorchCallbackMap.isEmpty()) {
                try {
                    this.mScheduler.schedule(new Runnable() { // from class: android.hardware.camera2.CameraManager$CameraManagerGlobal$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            CameraManager.CameraManagerGlobal.this.lambda$scheduleCameraServiceReconnectionLocked$2$CameraManager$CameraManagerGlobal();
                        }
                    }, 1000L, TimeUnit.MILLISECONDS);
                } catch (RejectedExecutionException e) {
                    Log.e(TAG, "Failed to schedule camera service re-connect: " + e);
                }
            }
        }

        public /* synthetic */ void lambda$scheduleCameraServiceReconnectionLocked$2$CameraManager$CameraManagerGlobal() {
            ICameraService cameraService = getCameraService();
            if (cameraService == null) {
                synchronized (this.mLock) {
                    scheduleCameraServiceReconnectionLocked();
                }
            }
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            synchronized (this.mLock) {
                if (this.mCameraService != null) {
                    this.mCameraService = null;
                    for (int i = this.mDeviceStatus.size() - 1; i >= 0; i--) {
                        String cameraId = this.mDeviceStatus.keyAt(i);
                        onStatusChangedLocked(0, cameraId);
                    }
                    for (int i2 = 0; i2 < this.mTorchStatus.size(); i2++) {
                        String cameraId2 = this.mTorchStatus.keyAt(i2);
                        onTorchStatusChangedLocked(0, cameraId2);
                    }
                    this.mConcurrentCameraIdCombinations.clear();
                    scheduleCameraServiceReconnectionLocked();
                }
            }
        }
    }
}
