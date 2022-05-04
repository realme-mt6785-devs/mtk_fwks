package android.hardware;

import android.hardware.ICamera;
import android.hardware.ICameraClient;
import android.hardware.ICameraServiceListener;
import android.hardware.camera2.ICameraDeviceCallbacks;
import android.hardware.camera2.ICameraDeviceUser;
import android.hardware.camera2.ICameraInjectionCallback;
import android.hardware.camera2.ICameraInjectionSession;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.params.VendorTagDescriptor;
import android.hardware.camera2.params.VendorTagDescriptorCache;
import android.hardware.camera2.utils.CameraIdAndSessionConfiguration;
import android.hardware.camera2.utils.ConcurrentCameraIdCombination;
import android.media.MediaMetrics;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface ICameraService extends IInterface {
    public static final int API_VERSION_1 = 1;
    public static final int API_VERSION_2 = 2;
    public static final int CAMERA_TYPE_ALL = 1;
    public static final int CAMERA_TYPE_BACKWARD_COMPATIBLE = 0;
    public static final int DEVICE_STATE_BACK_COVERED = 1;
    public static final int DEVICE_STATE_FOLDED = 4;
    public static final int DEVICE_STATE_FRONT_COVERED = 2;
    public static final int DEVICE_STATE_LAST_FRAMEWORK_BIT = Integer.MIN_VALUE;
    public static final int DEVICE_STATE_NORMAL = 0;
    public static final int ERROR_ALREADY_EXISTS = 2;
    public static final int ERROR_CAMERA_IN_USE = 7;
    public static final int ERROR_DEPRECATED_HAL = 9;
    public static final int ERROR_DISABLED = 6;
    public static final int ERROR_DISCONNECTED = 4;
    public static final int ERROR_ILLEGAL_ARGUMENT = 3;
    public static final int ERROR_INVALID_OPERATION = 10;
    public static final int ERROR_MAX_CAMERAS_IN_USE = 8;
    public static final int ERROR_PERMISSION_DENIED = 1;
    public static final int ERROR_TIMED_OUT = 5;
    public static final int EVENT_NONE = 0;
    public static final int EVENT_USER_SWITCHED = 1;
    public static final int USE_CALLING_PID = -1;
    public static final int USE_CALLING_UID = -1;

    CameraStatus[] addListener(ICameraServiceListener iCameraServiceListener) throws RemoteException;

    ICamera connect(ICameraClient iCameraClient, int i, String str, int i2, int i3, int i4) throws RemoteException;

    ICameraDeviceUser connectDevice(ICameraDeviceCallbacks iCameraDeviceCallbacks, String str, String str2, String str3, int i, int i2, int i3) throws RemoteException;

    CameraMetadataNative getCameraCharacteristics(String str, int i) throws RemoteException;

    CameraInfo getCameraInfo(int i) throws RemoteException;

    VendorTagDescriptorCache getCameraVendorTagCache() throws RemoteException;

    VendorTagDescriptor getCameraVendorTagDescriptor() throws RemoteException;

    ConcurrentCameraIdCombination[] getConcurrentCameraIds() throws RemoteException;

    String getLegacyParameters(int i) throws RemoteException;

    int getNumberOfCameras(int i) throws RemoteException;

    ICameraInjectionSession injectCamera(String str, String str2, String str3, ICameraInjectionCallback iCameraInjectionCallback) throws RemoteException;

    boolean isConcurrentSessionConfigurationSupported(CameraIdAndSessionConfiguration[] cameraIdAndSessionConfigurationArr, int i) throws RemoteException;

    boolean isHiddenPhysicalCamera(String str) throws RemoteException;

    void notifyDeviceStateChange(long j) throws RemoteException;

    void notifyDisplayConfigurationChange() throws RemoteException;

    void notifySystemEvent(int i, int[] iArr) throws RemoteException;

    void removeListener(ICameraServiceListener iCameraServiceListener) throws RemoteException;

    void setTorchMode(String str, boolean z, IBinder iBinder) throws RemoteException;

    boolean supportsCameraApi(String str, int i) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ICameraService {
        @Override // android.hardware.ICameraService
        public int getNumberOfCameras(int type) throws RemoteException {
            return 0;
        }

        @Override // android.hardware.ICameraService
        public CameraInfo getCameraInfo(int cameraId) throws RemoteException {
            return null;
        }

        @Override // android.hardware.ICameraService
        public ICamera connect(ICameraClient client, int cameraId, String opPackageName, int clientUid, int clientPid, int targetSdkVersion) throws RemoteException {
            return null;
        }

        @Override // android.hardware.ICameraService
        public ICameraDeviceUser connectDevice(ICameraDeviceCallbacks callbacks, String cameraId, String opPackageName, String featureId, int clientUid, int oomScoreOffset, int targetSdkVersion) throws RemoteException {
            return null;
        }

        @Override // android.hardware.ICameraService
        public CameraStatus[] addListener(ICameraServiceListener listener) throws RemoteException {
            return null;
        }

        @Override // android.hardware.ICameraService
        public ConcurrentCameraIdCombination[] getConcurrentCameraIds() throws RemoteException {
            return null;
        }

        @Override // android.hardware.ICameraService
        public boolean isConcurrentSessionConfigurationSupported(CameraIdAndSessionConfiguration[] sessions, int targetSdkVersion) throws RemoteException {
            return false;
        }

        @Override // android.hardware.ICameraService
        public void removeListener(ICameraServiceListener listener) throws RemoteException {
        }

        @Override // android.hardware.ICameraService
        public CameraMetadataNative getCameraCharacteristics(String cameraId, int targetSdkVersion) throws RemoteException {
            return null;
        }

        @Override // android.hardware.ICameraService
        public VendorTagDescriptor getCameraVendorTagDescriptor() throws RemoteException {
            return null;
        }

        @Override // android.hardware.ICameraService
        public VendorTagDescriptorCache getCameraVendorTagCache() throws RemoteException {
            return null;
        }

        @Override // android.hardware.ICameraService
        public String getLegacyParameters(int cameraId) throws RemoteException {
            return null;
        }

        @Override // android.hardware.ICameraService
        public boolean supportsCameraApi(String cameraId, int apiVersion) throws RemoteException {
            return false;
        }

        @Override // android.hardware.ICameraService
        public boolean isHiddenPhysicalCamera(String cameraId) throws RemoteException {
            return false;
        }

        @Override // android.hardware.ICameraService
        public ICameraInjectionSession injectCamera(String packageName, String internalCamId, String externalCamId, ICameraInjectionCallback CameraInjectionCallback) throws RemoteException {
            return null;
        }

        @Override // android.hardware.ICameraService
        public void setTorchMode(String cameraId, boolean enabled, IBinder clientBinder) throws RemoteException {
        }

        @Override // android.hardware.ICameraService
        public void notifySystemEvent(int eventId, int[] args) throws RemoteException {
        }

        @Override // android.hardware.ICameraService
        public void notifyDisplayConfigurationChange() throws RemoteException {
        }

        @Override // android.hardware.ICameraService
        public void notifyDeviceStateChange(long newState) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICameraService {
        public static final String DESCRIPTOR = "android.hardware.ICameraService";
        static final int TRANSACTION_addListener = 5;
        static final int TRANSACTION_connect = 3;
        static final int TRANSACTION_connectDevice = 4;
        static final int TRANSACTION_getCameraCharacteristics = 9;
        static final int TRANSACTION_getCameraInfo = 2;
        static final int TRANSACTION_getCameraVendorTagCache = 11;
        static final int TRANSACTION_getCameraVendorTagDescriptor = 10;
        static final int TRANSACTION_getConcurrentCameraIds = 6;
        static final int TRANSACTION_getLegacyParameters = 12;
        static final int TRANSACTION_getNumberOfCameras = 1;
        static final int TRANSACTION_injectCamera = 15;
        static final int TRANSACTION_isConcurrentSessionConfigurationSupported = 7;
        static final int TRANSACTION_isHiddenPhysicalCamera = 14;
        static final int TRANSACTION_notifyDeviceStateChange = 19;
        static final int TRANSACTION_notifyDisplayConfigurationChange = 18;
        static final int TRANSACTION_notifySystemEvent = 17;
        static final int TRANSACTION_removeListener = 8;
        static final int TRANSACTION_setTorchMode = 16;
        static final int TRANSACTION_supportsCameraApi = 13;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICameraService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ICameraService)) {
                return new Proxy(obj);
            }
            return (ICameraService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getNumberOfCameras";
                case 2:
                    return "getCameraInfo";
                case 3:
                    return MediaMetrics.Value.CONNECT;
                case 4:
                    return "connectDevice";
                case 5:
                    return "addListener";
                case 6:
                    return "getConcurrentCameraIds";
                case 7:
                    return "isConcurrentSessionConfigurationSupported";
                case 8:
                    return "removeListener";
                case 9:
                    return "getCameraCharacteristics";
                case 10:
                    return "getCameraVendorTagDescriptor";
                case 11:
                    return "getCameraVendorTagCache";
                case 12:
                    return "getLegacyParameters";
                case 13:
                    return "supportsCameraApi";
                case 14:
                    return "isHiddenPhysicalCamera";
                case 15:
                    return "injectCamera";
                case 16:
                    return "setTorchMode";
                case 17:
                    return "notifySystemEvent";
                case 18:
                    return "notifyDisplayConfigurationChange";
                case 19:
                    return "notifyDeviceStateChange";
                default:
                    return null;
            }
        }

        @Override // android.os.Binder
        public String getTransactionName(int transactionCode) {
            return getDefaultTransactionName(transactionCode);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    IBinder iBinder = null;
                    boolean _arg1 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg0 = data.readInt();
                            int _result = getNumberOfCameras(_arg0);
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg02 = data.readInt();
                            CameraInfo _result2 = getCameraInfo(_arg02);
                            reply.writeNoException();
                            if (_result2 != null) {
                                reply.writeInt(1);
                                _result2.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            ICameraClient _arg03 = ICameraClient.Stub.asInterface(data.readStrongBinder());
                            int _arg12 = data.readInt();
                            String _arg2 = data.readString();
                            int _arg3 = data.readInt();
                            int _arg4 = data.readInt();
                            int _arg5 = data.readInt();
                            ICamera _result3 = connect(_arg03, _arg12, _arg2, _arg3, _arg4, _arg5);
                            reply.writeNoException();
                            if (_result3 != null) {
                                iBinder = _result3.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            ICameraDeviceCallbacks _arg04 = ICameraDeviceCallbacks.Stub.asInterface(data.readStrongBinder());
                            String _arg13 = data.readString();
                            String _arg22 = data.readString();
                            String _arg32 = data.readString();
                            int _arg42 = data.readInt();
                            int _arg52 = data.readInt();
                            int _arg6 = data.readInt();
                            ICameraDeviceUser _result4 = connectDevice(_arg04, _arg13, _arg22, _arg32, _arg42, _arg52, _arg6);
                            reply.writeNoException();
                            if (_result4 != null) {
                                iBinder = _result4.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            ICameraServiceListener _arg05 = ICameraServiceListener.Stub.asInterface(data.readStrongBinder());
                            CameraStatus[] _result5 = addListener(_arg05);
                            reply.writeNoException();
                            reply.writeTypedArray(_result5, 1);
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            ConcurrentCameraIdCombination[] _result6 = getConcurrentCameraIds();
                            reply.writeNoException();
                            reply.writeTypedArray(_result6, 1);
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            CameraIdAndSessionConfiguration[] _arg06 = (CameraIdAndSessionConfiguration[]) data.createTypedArray(CameraIdAndSessionConfiguration.CREATOR);
                            int _arg14 = data.readInt();
                            boolean isConcurrentSessionConfigurationSupported = isConcurrentSessionConfigurationSupported(_arg06, _arg14);
                            reply.writeNoException();
                            reply.writeInt(isConcurrentSessionConfigurationSupported ? 1 : 0);
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            ICameraServiceListener _arg07 = ICameraServiceListener.Stub.asInterface(data.readStrongBinder());
                            removeListener(_arg07);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg08 = data.readString();
                            int _arg15 = data.readInt();
                            CameraMetadataNative _result7 = getCameraCharacteristics(_arg08, _arg15);
                            reply.writeNoException();
                            if (_result7 != null) {
                                reply.writeInt(1);
                                _result7.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            VendorTagDescriptor _result8 = getCameraVendorTagDescriptor();
                            reply.writeNoException();
                            if (_result8 != null) {
                                reply.writeInt(1);
                                _result8.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            VendorTagDescriptorCache _result9 = getCameraVendorTagCache();
                            reply.writeNoException();
                            if (_result9 != null) {
                                reply.writeInt(1);
                                _result9.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg09 = data.readInt();
                            String _result10 = getLegacyParameters(_arg09);
                            reply.writeNoException();
                            reply.writeString(_result10);
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg010 = data.readString();
                            int _arg16 = data.readInt();
                            boolean supportsCameraApi = supportsCameraApi(_arg010, _arg16);
                            reply.writeNoException();
                            reply.writeInt(supportsCameraApi ? 1 : 0);
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg011 = data.readString();
                            boolean isHiddenPhysicalCamera = isHiddenPhysicalCamera(_arg011);
                            reply.writeNoException();
                            reply.writeInt(isHiddenPhysicalCamera ? 1 : 0);
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg012 = data.readString();
                            String _arg17 = data.readString();
                            String _arg23 = data.readString();
                            ICameraInjectionCallback _arg33 = ICameraInjectionCallback.Stub.asInterface(data.readStrongBinder());
                            ICameraInjectionSession _result11 = injectCamera(_arg012, _arg17, _arg23, _arg33);
                            reply.writeNoException();
                            if (_result11 != null) {
                                iBinder = _result11.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg013 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = true;
                            }
                            IBinder _arg24 = data.readStrongBinder();
                            setTorchMode(_arg013, _arg1, _arg24);
                            reply.writeNoException();
                            return true;
                        case 17:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg014 = data.readInt();
                            int[] _arg18 = data.createIntArray();
                            notifySystemEvent(_arg014, _arg18);
                            return true;
                        case 18:
                            data.enforceInterface(DESCRIPTOR);
                            notifyDisplayConfigurationChange();
                            return true;
                        case 19:
                            data.enforceInterface(DESCRIPTOR);
                            long _arg015 = data.readLong();
                            notifyDeviceStateChange(_arg015);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ICameraService {
            public static ICameraService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.hardware.ICameraService
            public int getNumberOfCameras(int type) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(type);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNumberOfCameras(type);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public CameraInfo getCameraInfo(int cameraId) throws RemoteException {
                CameraInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(cameraId);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCameraInfo(cameraId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = CameraInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public ICamera connect(ICameraClient client, int cameraId, String opPackageName, int clientUid, int clientPid, int targetSdkVersion) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(client != null ? client.asBinder() : null);
                    try {
                        _data.writeInt(cameraId);
                        try {
                            _data.writeString(opPackageName);
                        } catch (Throwable th2) {
                            th = th2;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
                try {
                    _data.writeInt(clientUid);
                    try {
                        _data.writeInt(clientPid);
                        try {
                            _data.writeInt(targetSdkVersion);
                            try {
                                boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _reply.readException();
                                    ICamera _result = ICamera.Stub.asInterface(_reply.readStrongBinder());
                                    _reply.recycle();
                                    _data.recycle();
                                    return _result;
                                }
                                ICamera connect = Stub.getDefaultImpl().connect(client, cameraId, opPackageName, clientUid, clientPid, targetSdkVersion);
                                _reply.recycle();
                                _data.recycle();
                                return connect;
                            } catch (Throwable th5) {
                                th = th5;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th6) {
                            th = th6;
                        }
                    } catch (Throwable th7) {
                        th = th7;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th8) {
                    th = th8;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.hardware.ICameraService
            public ICameraDeviceUser connectDevice(ICameraDeviceCallbacks callbacks, String cameraId, String opPackageName, String featureId, int clientUid, int oomScoreOffset, int targetSdkVersion) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    try {
                        _data.writeString(cameraId);
                    } catch (Throwable th2) {
                        th = th2;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
                try {
                    _data.writeString(opPackageName);
                    try {
                        _data.writeString(featureId);
                        try {
                            _data.writeInt(clientUid);
                            try {
                                _data.writeInt(oomScoreOffset);
                                _data.writeInt(targetSdkVersion);
                                boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _reply.readException();
                                    ICameraDeviceUser _result = ICameraDeviceUser.Stub.asInterface(_reply.readStrongBinder());
                                    _reply.recycle();
                                    _data.recycle();
                                    return _result;
                                }
                                ICameraDeviceUser connectDevice = Stub.getDefaultImpl().connectDevice(callbacks, cameraId, opPackageName, featureId, clientUid, oomScoreOffset, targetSdkVersion);
                                _reply.recycle();
                                _data.recycle();
                                return connectDevice;
                            } catch (Throwable th4) {
                                th = th4;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.hardware.ICameraService
            public CameraStatus[] addListener(ICameraServiceListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addListener(listener);
                    }
                    _reply.readException();
                    CameraStatus[] _result = (CameraStatus[]) _reply.createTypedArray(CameraStatus.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public ConcurrentCameraIdCombination[] getConcurrentCameraIds() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConcurrentCameraIds();
                    }
                    _reply.readException();
                    ConcurrentCameraIdCombination[] _result = (ConcurrentCameraIdCombination[]) _reply.createTypedArray(ConcurrentCameraIdCombination.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public boolean isConcurrentSessionConfigurationSupported(CameraIdAndSessionConfiguration[] sessions, int targetSdkVersion) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    _data.writeTypedArray(sessions, 0);
                    _data.writeInt(targetSdkVersion);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isConcurrentSessionConfigurationSupported(sessions, targetSdkVersion);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public void removeListener(ICameraServiceListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeListener(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public CameraMetadataNative getCameraCharacteristics(String cameraId, int targetSdkVersion) throws RemoteException {
                CameraMetadataNative _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(cameraId);
                    _data.writeInt(targetSdkVersion);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCameraCharacteristics(cameraId, targetSdkVersion);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = CameraMetadataNative.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public VendorTagDescriptor getCameraVendorTagDescriptor() throws RemoteException {
                VendorTagDescriptor _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCameraVendorTagDescriptor();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = VendorTagDescriptor.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public VendorTagDescriptorCache getCameraVendorTagCache() throws RemoteException {
                VendorTagDescriptorCache _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCameraVendorTagCache();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = VendorTagDescriptorCache.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public String getLegacyParameters(int cameraId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(cameraId);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLegacyParameters(cameraId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public boolean supportsCameraApi(String cameraId, int apiVersion) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(cameraId);
                    _data.writeInt(apiVersion);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().supportsCameraApi(cameraId, apiVersion);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public boolean isHiddenPhysicalCamera(String cameraId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(cameraId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isHiddenPhysicalCamera(cameraId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public ICameraInjectionSession injectCamera(String packageName, String internalCamId, String externalCamId, ICameraInjectionCallback CameraInjectionCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(internalCamId);
                    _data.writeString(externalCamId);
                    _data.writeStrongBinder(CameraInjectionCallback != null ? CameraInjectionCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().injectCamera(packageName, internalCamId, externalCamId, CameraInjectionCallback);
                    }
                    _reply.readException();
                    ICameraInjectionSession _result = ICameraInjectionSession.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public void setTorchMode(String cameraId, boolean enabled, IBinder clientBinder) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(cameraId);
                    _data.writeInt(enabled ? 1 : 0);
                    _data.writeStrongBinder(clientBinder);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setTorchMode(cameraId, enabled, clientBinder);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public void notifySystemEvent(int eventId, int[] args) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(eventId);
                    _data.writeIntArray(args);
                    boolean _status = this.mRemote.transact(17, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifySystemEvent(eventId, args);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public void notifyDisplayConfigurationChange() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(18, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyDisplayConfigurationChange();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public void notifyDeviceStateChange(long newState) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(newState);
                    boolean _status = this.mRemote.transact(19, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyDeviceStateChange(newState);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICameraService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ICameraService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
