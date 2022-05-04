package android.hardware.camera2.extension;

import android.hardware.camera2.extension.ICaptureProcessorImpl;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes.dex */
public interface IImageCaptureExtenderImpl extends IInterface {
    public static final String DESCRIPTOR = "android.hardware.camera2.extension.IImageCaptureExtenderImpl";

    ICaptureProcessorImpl getCaptureProcessor() throws RemoteException;

    List<CaptureStageImpl> getCaptureStages() throws RemoteException;

    LatencyRange getEstimatedCaptureLatencyRange(Size size) throws RemoteException;

    int getMaxCaptureStage() throws RemoteException;

    List<SizeList> getSupportedResolutions() throws RemoteException;

    void init(String str, CameraMetadataNative cameraMetadataNative) throws RemoteException;

    boolean isExtensionAvailable(String str, CameraMetadataNative cameraMetadataNative) throws RemoteException;

    void onDeInit() throws RemoteException;

    CaptureStageImpl onDisableSession() throws RemoteException;

    CaptureStageImpl onEnableSession() throws RemoteException;

    void onInit(String str, CameraMetadataNative cameraMetadataNative) throws RemoteException;

    CaptureStageImpl onPresetSession() throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IImageCaptureExtenderImpl {
        @Override // android.hardware.camera2.extension.IImageCaptureExtenderImpl
        public void onInit(String cameraId, CameraMetadataNative cameraCharacteristics) throws RemoteException {
        }

        @Override // android.hardware.camera2.extension.IImageCaptureExtenderImpl
        public void onDeInit() throws RemoteException {
        }

        @Override // android.hardware.camera2.extension.IImageCaptureExtenderImpl
        public CaptureStageImpl onPresetSession() throws RemoteException {
            return null;
        }

        @Override // android.hardware.camera2.extension.IImageCaptureExtenderImpl
        public CaptureStageImpl onEnableSession() throws RemoteException {
            return null;
        }

        @Override // android.hardware.camera2.extension.IImageCaptureExtenderImpl
        public CaptureStageImpl onDisableSession() throws RemoteException {
            return null;
        }

        @Override // android.hardware.camera2.extension.IImageCaptureExtenderImpl
        public boolean isExtensionAvailable(String cameraId, CameraMetadataNative chars) throws RemoteException {
            return false;
        }

        @Override // android.hardware.camera2.extension.IImageCaptureExtenderImpl
        public void init(String cameraId, CameraMetadataNative chars) throws RemoteException {
        }

        @Override // android.hardware.camera2.extension.IImageCaptureExtenderImpl
        public ICaptureProcessorImpl getCaptureProcessor() throws RemoteException {
            return null;
        }

        @Override // android.hardware.camera2.extension.IImageCaptureExtenderImpl
        public List<CaptureStageImpl> getCaptureStages() throws RemoteException {
            return null;
        }

        @Override // android.hardware.camera2.extension.IImageCaptureExtenderImpl
        public int getMaxCaptureStage() throws RemoteException {
            return 0;
        }

        @Override // android.hardware.camera2.extension.IImageCaptureExtenderImpl
        public List<SizeList> getSupportedResolutions() throws RemoteException {
            return null;
        }

        @Override // android.hardware.camera2.extension.IImageCaptureExtenderImpl
        public LatencyRange getEstimatedCaptureLatencyRange(Size outputSize) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IImageCaptureExtenderImpl {
        static final int TRANSACTION_getCaptureProcessor = 8;
        static final int TRANSACTION_getCaptureStages = 9;
        static final int TRANSACTION_getEstimatedCaptureLatencyRange = 12;
        static final int TRANSACTION_getMaxCaptureStage = 10;
        static final int TRANSACTION_getSupportedResolutions = 11;
        static final int TRANSACTION_init = 7;
        static final int TRANSACTION_isExtensionAvailable = 6;
        static final int TRANSACTION_onDeInit = 2;
        static final int TRANSACTION_onDisableSession = 5;
        static final int TRANSACTION_onEnableSession = 4;
        static final int TRANSACTION_onInit = 1;
        static final int TRANSACTION_onPresetSession = 3;

        public Stub() {
            attachInterface(this, IImageCaptureExtenderImpl.DESCRIPTOR);
        }

        public static IImageCaptureExtenderImpl asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IImageCaptureExtenderImpl.DESCRIPTOR);
            if (iin == null || !(iin instanceof IImageCaptureExtenderImpl)) {
                return new Proxy(obj);
            }
            return (IImageCaptureExtenderImpl) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onInit";
                case 2:
                    return "onDeInit";
                case 3:
                    return "onPresetSession";
                case 4:
                    return "onEnableSession";
                case 5:
                    return "onDisableSession";
                case 6:
                    return "isExtensionAvailable";
                case 7:
                    return "init";
                case 8:
                    return "getCaptureProcessor";
                case 9:
                    return "getCaptureStages";
                case 10:
                    return "getMaxCaptureStage";
                case 11:
                    return "getSupportedResolutions";
                case 12:
                    return "getEstimatedCaptureLatencyRange";
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
            CameraMetadataNative _arg1;
            CameraMetadataNative _arg12;
            CameraMetadataNative _arg13;
            Size _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IImageCaptureExtenderImpl.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IImageCaptureExtenderImpl.DESCRIPTOR);
                            String _arg02 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = CameraMetadataNative.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            onInit(_arg02, _arg1);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(IImageCaptureExtenderImpl.DESCRIPTOR);
                            onDeInit();
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(IImageCaptureExtenderImpl.DESCRIPTOR);
                            CaptureStageImpl _result = onPresetSession();
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 4:
                            data.enforceInterface(IImageCaptureExtenderImpl.DESCRIPTOR);
                            CaptureStageImpl _result2 = onEnableSession();
                            reply.writeNoException();
                            if (_result2 != null) {
                                reply.writeInt(1);
                                _result2.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 5:
                            data.enforceInterface(IImageCaptureExtenderImpl.DESCRIPTOR);
                            CaptureStageImpl _result3 = onDisableSession();
                            reply.writeNoException();
                            if (_result3 != null) {
                                reply.writeInt(1);
                                _result3.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 6:
                            data.enforceInterface(IImageCaptureExtenderImpl.DESCRIPTOR);
                            String _arg03 = data.readString();
                            if (data.readInt() != 0) {
                                _arg12 = CameraMetadataNative.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            boolean isExtensionAvailable = isExtensionAvailable(_arg03, _arg12);
                            reply.writeNoException();
                            reply.writeInt(isExtensionAvailable ? 1 : 0);
                            return true;
                        case 7:
                            data.enforceInterface(IImageCaptureExtenderImpl.DESCRIPTOR);
                            String _arg04 = data.readString();
                            if (data.readInt() != 0) {
                                _arg13 = CameraMetadataNative.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            init(_arg04, _arg13);
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(IImageCaptureExtenderImpl.DESCRIPTOR);
                            ICaptureProcessorImpl _result4 = getCaptureProcessor();
                            reply.writeNoException();
                            reply.writeStrongBinder(_result4 != null ? _result4.asBinder() : null);
                            return true;
                        case 9:
                            data.enforceInterface(IImageCaptureExtenderImpl.DESCRIPTOR);
                            List<CaptureStageImpl> _result5 = getCaptureStages();
                            reply.writeNoException();
                            reply.writeTypedList(_result5);
                            return true;
                        case 10:
                            data.enforceInterface(IImageCaptureExtenderImpl.DESCRIPTOR);
                            int _result6 = getMaxCaptureStage();
                            reply.writeNoException();
                            reply.writeInt(_result6);
                            return true;
                        case 11:
                            data.enforceInterface(IImageCaptureExtenderImpl.DESCRIPTOR);
                            List<SizeList> _result7 = getSupportedResolutions();
                            reply.writeNoException();
                            reply.writeTypedList(_result7);
                            return true;
                        case 12:
                            data.enforceInterface(IImageCaptureExtenderImpl.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = Size.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            LatencyRange _result8 = getEstimatedCaptureLatencyRange(_arg0);
                            reply.writeNoException();
                            if (_result8 != null) {
                                reply.writeInt(1);
                                _result8.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IImageCaptureExtenderImpl {
            public static IImageCaptureExtenderImpl sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IImageCaptureExtenderImpl.DESCRIPTOR;
            }

            @Override // android.hardware.camera2.extension.IImageCaptureExtenderImpl
            public void onInit(String cameraId, CameraMetadataNative cameraCharacteristics) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImageCaptureExtenderImpl.DESCRIPTOR);
                    _data.writeString(cameraId);
                    if (cameraCharacteristics != null) {
                        _data.writeInt(1);
                        cameraCharacteristics.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onInit(cameraId, cameraCharacteristics);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.IImageCaptureExtenderImpl
            public void onDeInit() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImageCaptureExtenderImpl.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onDeInit();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.IImageCaptureExtenderImpl
            public CaptureStageImpl onPresetSession() throws RemoteException {
                CaptureStageImpl _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImageCaptureExtenderImpl.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onPresetSession();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = CaptureStageImpl.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.IImageCaptureExtenderImpl
            public CaptureStageImpl onEnableSession() throws RemoteException {
                CaptureStageImpl _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImageCaptureExtenderImpl.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onEnableSession();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = CaptureStageImpl.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.IImageCaptureExtenderImpl
            public CaptureStageImpl onDisableSession() throws RemoteException {
                CaptureStageImpl _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImageCaptureExtenderImpl.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onDisableSession();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = CaptureStageImpl.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.IImageCaptureExtenderImpl
            public boolean isExtensionAvailable(String cameraId, CameraMetadataNative chars) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImageCaptureExtenderImpl.DESCRIPTOR);
                    _data.writeString(cameraId);
                    boolean _result = true;
                    if (chars != null) {
                        _data.writeInt(1);
                        chars.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isExtensionAvailable(cameraId, chars);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.IImageCaptureExtenderImpl
            public void init(String cameraId, CameraMetadataNative chars) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImageCaptureExtenderImpl.DESCRIPTOR);
                    _data.writeString(cameraId);
                    if (chars != null) {
                        _data.writeInt(1);
                        chars.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().init(cameraId, chars);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.IImageCaptureExtenderImpl
            public ICaptureProcessorImpl getCaptureProcessor() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImageCaptureExtenderImpl.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCaptureProcessor();
                    }
                    _reply.readException();
                    ICaptureProcessorImpl _result = ICaptureProcessorImpl.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.IImageCaptureExtenderImpl
            public List<CaptureStageImpl> getCaptureStages() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImageCaptureExtenderImpl.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCaptureStages();
                    }
                    _reply.readException();
                    List<CaptureStageImpl> _result = _reply.createTypedArrayList(CaptureStageImpl.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.IImageCaptureExtenderImpl
            public int getMaxCaptureStage() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImageCaptureExtenderImpl.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMaxCaptureStage();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.IImageCaptureExtenderImpl
            public List<SizeList> getSupportedResolutions() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImageCaptureExtenderImpl.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSupportedResolutions();
                    }
                    _reply.readException();
                    List<SizeList> _result = _reply.createTypedArrayList(SizeList.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.IImageCaptureExtenderImpl
            public LatencyRange getEstimatedCaptureLatencyRange(Size outputSize) throws RemoteException {
                LatencyRange _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IImageCaptureExtenderImpl.DESCRIPTOR);
                    if (outputSize != null) {
                        _data.writeInt(1);
                        outputSize.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEstimatedCaptureLatencyRange(outputSize);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = LatencyRange.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IImageCaptureExtenderImpl impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IImageCaptureExtenderImpl getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
