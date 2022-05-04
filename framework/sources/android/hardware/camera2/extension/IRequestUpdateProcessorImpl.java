package android.hardware.camera2.extension;

import android.hardware.camera2.impl.CameraMetadataNative;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;
/* loaded from: classes.dex */
public interface IRequestUpdateProcessorImpl extends IInterface {
    public static final String DESCRIPTOR = "android.hardware.camera2.extension.IRequestUpdateProcessorImpl";

    void onImageFormatUpdate(int i) throws RemoteException;

    void onOutputSurface(Surface surface, int i) throws RemoteException;

    void onResolutionUpdate(Size size) throws RemoteException;

    CaptureStageImpl process(CameraMetadataNative cameraMetadataNative, int i) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IRequestUpdateProcessorImpl {
        @Override // android.hardware.camera2.extension.IRequestUpdateProcessorImpl
        public void onOutputSurface(Surface surface, int imageFormat) throws RemoteException {
        }

        @Override // android.hardware.camera2.extension.IRequestUpdateProcessorImpl
        public void onResolutionUpdate(Size size) throws RemoteException {
        }

        @Override // android.hardware.camera2.extension.IRequestUpdateProcessorImpl
        public void onImageFormatUpdate(int imageFormat) throws RemoteException {
        }

        @Override // android.hardware.camera2.extension.IRequestUpdateProcessorImpl
        public CaptureStageImpl process(CameraMetadataNative result, int sequenceId) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IRequestUpdateProcessorImpl {
        static final int TRANSACTION_onImageFormatUpdate = 3;
        static final int TRANSACTION_onOutputSurface = 1;
        static final int TRANSACTION_onResolutionUpdate = 2;
        static final int TRANSACTION_process = 4;

        public Stub() {
            attachInterface(this, IRequestUpdateProcessorImpl.DESCRIPTOR);
        }

        public static IRequestUpdateProcessorImpl asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IRequestUpdateProcessorImpl.DESCRIPTOR);
            if (iin == null || !(iin instanceof IRequestUpdateProcessorImpl)) {
                return new Proxy(obj);
            }
            return (IRequestUpdateProcessorImpl) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onOutputSurface";
                case 2:
                    return "onResolutionUpdate";
                case 3:
                    return "onImageFormatUpdate";
                case 4:
                    return "process";
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
            Surface _arg0;
            Size _arg02;
            CameraMetadataNative _arg03;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IRequestUpdateProcessorImpl.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IRequestUpdateProcessorImpl.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = Surface.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            int _arg1 = data.readInt();
                            onOutputSurface(_arg0, _arg1);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(IRequestUpdateProcessorImpl.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = Size.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            onResolutionUpdate(_arg02);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(IRequestUpdateProcessorImpl.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            onImageFormatUpdate(_arg04);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(IRequestUpdateProcessorImpl.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = CameraMetadataNative.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            int _arg12 = data.readInt();
                            CaptureStageImpl _result = process(_arg03, _arg12);
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
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
        public static class Proxy implements IRequestUpdateProcessorImpl {
            public static IRequestUpdateProcessorImpl sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IRequestUpdateProcessorImpl.DESCRIPTOR;
            }

            @Override // android.hardware.camera2.extension.IRequestUpdateProcessorImpl
            public void onOutputSurface(Surface surface, int imageFormat) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRequestUpdateProcessorImpl.DESCRIPTOR);
                    if (surface != null) {
                        _data.writeInt(1);
                        surface.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(imageFormat);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onOutputSurface(surface, imageFormat);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.IRequestUpdateProcessorImpl
            public void onResolutionUpdate(Size size) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRequestUpdateProcessorImpl.DESCRIPTOR);
                    if (size != null) {
                        _data.writeInt(1);
                        size.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onResolutionUpdate(size);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.IRequestUpdateProcessorImpl
            public void onImageFormatUpdate(int imageFormat) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRequestUpdateProcessorImpl.DESCRIPTOR);
                    _data.writeInt(imageFormat);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onImageFormatUpdate(imageFormat);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.IRequestUpdateProcessorImpl
            public CaptureStageImpl process(CameraMetadataNative result, int sequenceId) throws RemoteException {
                CaptureStageImpl _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRequestUpdateProcessorImpl.DESCRIPTOR);
                    if (result != null) {
                        _data.writeInt(1);
                        result.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(sequenceId);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().process(result, sequenceId);
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
        }

        public static boolean setDefaultImpl(IRequestUpdateProcessorImpl impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IRequestUpdateProcessorImpl getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
