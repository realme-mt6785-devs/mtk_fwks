package android.hardware.camera2.extension;

import android.hardware.camera2.extension.ICaptureCallback;
import android.hardware.camera2.extension.IRequestProcessorImpl;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface ISessionProcessorImpl extends IInterface {
    public static final String DESCRIPTOR = "android.hardware.camera2.extension.ISessionProcessorImpl";

    void deInitSession() throws RemoteException;

    CameraSessionConfig initSession(String str, OutputSurface outputSurface, OutputSurface outputSurface2) throws RemoteException;

    void onCaptureSessionEnd() throws RemoteException;

    void onCaptureSessionStart(IRequestProcessorImpl iRequestProcessorImpl) throws RemoteException;

    int startCapture(ICaptureCallback iCaptureCallback, int i, int i2) throws RemoteException;

    int startRepeating(ICaptureCallback iCaptureCallback) throws RemoteException;

    void stopRepeating() throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ISessionProcessorImpl {
        @Override // android.hardware.camera2.extension.ISessionProcessorImpl
        public CameraSessionConfig initSession(String cameraId, OutputSurface previewSurface, OutputSurface imageCaptureSurface) throws RemoteException {
            return null;
        }

        @Override // android.hardware.camera2.extension.ISessionProcessorImpl
        public void deInitSession() throws RemoteException {
        }

        @Override // android.hardware.camera2.extension.ISessionProcessorImpl
        public void onCaptureSessionStart(IRequestProcessorImpl requestProcessor) throws RemoteException {
        }

        @Override // android.hardware.camera2.extension.ISessionProcessorImpl
        public void onCaptureSessionEnd() throws RemoteException {
        }

        @Override // android.hardware.camera2.extension.ISessionProcessorImpl
        public int startRepeating(ICaptureCallback callback) throws RemoteException {
            return 0;
        }

        @Override // android.hardware.camera2.extension.ISessionProcessorImpl
        public void stopRepeating() throws RemoteException {
        }

        @Override // android.hardware.camera2.extension.ISessionProcessorImpl
        public int startCapture(ICaptureCallback callback, int jpegRotation, int jpegQuality) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ISessionProcessorImpl {
        static final int TRANSACTION_deInitSession = 2;
        static final int TRANSACTION_initSession = 1;
        static final int TRANSACTION_onCaptureSessionEnd = 4;
        static final int TRANSACTION_onCaptureSessionStart = 3;
        static final int TRANSACTION_startCapture = 7;
        static final int TRANSACTION_startRepeating = 5;
        static final int TRANSACTION_stopRepeating = 6;

        public Stub() {
            attachInterface(this, ISessionProcessorImpl.DESCRIPTOR);
        }

        public static ISessionProcessorImpl asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ISessionProcessorImpl.DESCRIPTOR);
            if (iin == null || !(iin instanceof ISessionProcessorImpl)) {
                return new Proxy(obj);
            }
            return (ISessionProcessorImpl) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "initSession";
                case 2:
                    return "deInitSession";
                case 3:
                    return "onCaptureSessionStart";
                case 4:
                    return "onCaptureSessionEnd";
                case 5:
                    return "startRepeating";
                case 6:
                    return "stopRepeating";
                case 7:
                    return "startCapture";
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
            OutputSurface _arg1;
            OutputSurface _arg2;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ISessionProcessorImpl.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ISessionProcessorImpl.DESCRIPTOR);
                            String _arg0 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = OutputSurface.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg2 = OutputSurface.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            CameraSessionConfig _result = initSession(_arg0, _arg1, _arg2);
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 2:
                            data.enforceInterface(ISessionProcessorImpl.DESCRIPTOR);
                            deInitSession();
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(ISessionProcessorImpl.DESCRIPTOR);
                            IRequestProcessorImpl _arg02 = IRequestProcessorImpl.Stub.asInterface(data.readStrongBinder());
                            onCaptureSessionStart(_arg02);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(ISessionProcessorImpl.DESCRIPTOR);
                            onCaptureSessionEnd();
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(ISessionProcessorImpl.DESCRIPTOR);
                            ICaptureCallback _arg03 = ICaptureCallback.Stub.asInterface(data.readStrongBinder());
                            int _result2 = startRepeating(_arg03);
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        case 6:
                            data.enforceInterface(ISessionProcessorImpl.DESCRIPTOR);
                            stopRepeating();
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(ISessionProcessorImpl.DESCRIPTOR);
                            ICaptureCallback _arg04 = ICaptureCallback.Stub.asInterface(data.readStrongBinder());
                            int _arg12 = data.readInt();
                            int _arg22 = data.readInt();
                            int _result3 = startCapture(_arg04, _arg12, _arg22);
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ISessionProcessorImpl {
            public static ISessionProcessorImpl sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISessionProcessorImpl.DESCRIPTOR;
            }

            @Override // android.hardware.camera2.extension.ISessionProcessorImpl
            public CameraSessionConfig initSession(String cameraId, OutputSurface previewSurface, OutputSurface imageCaptureSurface) throws RemoteException {
                CameraSessionConfig _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISessionProcessorImpl.DESCRIPTOR);
                    _data.writeString(cameraId);
                    if (previewSurface != null) {
                        _data.writeInt(1);
                        previewSurface.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (imageCaptureSurface != null) {
                        _data.writeInt(1);
                        imageCaptureSurface.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().initSession(cameraId, previewSurface, imageCaptureSurface);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = CameraSessionConfig.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.ISessionProcessorImpl
            public void deInitSession() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISessionProcessorImpl.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().deInitSession();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.ISessionProcessorImpl
            public void onCaptureSessionStart(IRequestProcessorImpl requestProcessor) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISessionProcessorImpl.DESCRIPTOR);
                    _data.writeStrongBinder(requestProcessor != null ? requestProcessor.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onCaptureSessionStart(requestProcessor);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.ISessionProcessorImpl
            public void onCaptureSessionEnd() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISessionProcessorImpl.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onCaptureSessionEnd();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.ISessionProcessorImpl
            public int startRepeating(ICaptureCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISessionProcessorImpl.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startRepeating(callback);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.ISessionProcessorImpl
            public void stopRepeating() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISessionProcessorImpl.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().stopRepeating();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.ISessionProcessorImpl
            public int startCapture(ICaptureCallback callback, int jpegRotation, int jpegQuality) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISessionProcessorImpl.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeInt(jpegRotation);
                    _data.writeInt(jpegQuality);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startCapture(callback, jpegRotation, jpegQuality);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISessionProcessorImpl impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ISessionProcessorImpl getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
