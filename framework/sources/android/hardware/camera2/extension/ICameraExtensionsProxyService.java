package android.hardware.camera2.extension;

import android.hardware.camera2.extension.IAdvancedExtenderImpl;
import android.hardware.camera2.extension.IImageCaptureExtenderImpl;
import android.hardware.camera2.extension.IInitializeSessionCallback;
import android.hardware.camera2.extension.IPreviewExtenderImpl;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface ICameraExtensionsProxyService extends IInterface {
    public static final String DESCRIPTOR = "android.hardware.camera2.extension.ICameraExtensionsProxyService";

    boolean advancedExtensionsSupported() throws RemoteException;

    IAdvancedExtenderImpl initializeAdvancedExtension(int i) throws RemoteException;

    IImageCaptureExtenderImpl initializeImageExtension(int i) throws RemoteException;

    IPreviewExtenderImpl initializePreviewExtension(int i) throws RemoteException;

    void initializeSession(IInitializeSessionCallback iInitializeSessionCallback) throws RemoteException;

    long registerClient() throws RemoteException;

    void releaseSession() throws RemoteException;

    void unregisterClient(long j) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ICameraExtensionsProxyService {
        @Override // android.hardware.camera2.extension.ICameraExtensionsProxyService
        public long registerClient() throws RemoteException {
            return 0L;
        }

        @Override // android.hardware.camera2.extension.ICameraExtensionsProxyService
        public void unregisterClient(long clientId) throws RemoteException {
        }

        @Override // android.hardware.camera2.extension.ICameraExtensionsProxyService
        public boolean advancedExtensionsSupported() throws RemoteException {
            return false;
        }

        @Override // android.hardware.camera2.extension.ICameraExtensionsProxyService
        public void initializeSession(IInitializeSessionCallback cb) throws RemoteException {
        }

        @Override // android.hardware.camera2.extension.ICameraExtensionsProxyService
        public void releaseSession() throws RemoteException {
        }

        @Override // android.hardware.camera2.extension.ICameraExtensionsProxyService
        public IPreviewExtenderImpl initializePreviewExtension(int extensionType) throws RemoteException {
            return null;
        }

        @Override // android.hardware.camera2.extension.ICameraExtensionsProxyService
        public IImageCaptureExtenderImpl initializeImageExtension(int extensionType) throws RemoteException {
            return null;
        }

        @Override // android.hardware.camera2.extension.ICameraExtensionsProxyService
        public IAdvancedExtenderImpl initializeAdvancedExtension(int extensionType) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ICameraExtensionsProxyService {
        static final int TRANSACTION_advancedExtensionsSupported = 3;
        static final int TRANSACTION_initializeAdvancedExtension = 8;
        static final int TRANSACTION_initializeImageExtension = 7;
        static final int TRANSACTION_initializePreviewExtension = 6;
        static final int TRANSACTION_initializeSession = 4;
        static final int TRANSACTION_registerClient = 1;
        static final int TRANSACTION_releaseSession = 5;
        static final int TRANSACTION_unregisterClient = 2;

        public Stub() {
            attachInterface(this, ICameraExtensionsProxyService.DESCRIPTOR);
        }

        public static ICameraExtensionsProxyService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ICameraExtensionsProxyService.DESCRIPTOR);
            if (iin == null || !(iin instanceof ICameraExtensionsProxyService)) {
                return new Proxy(obj);
            }
            return (ICameraExtensionsProxyService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "registerClient";
                case 2:
                    return "unregisterClient";
                case 3:
                    return "advancedExtensionsSupported";
                case 4:
                    return "initializeSession";
                case 5:
                    return "releaseSession";
                case 6:
                    return "initializePreviewExtension";
                case 7:
                    return "initializeImageExtension";
                case 8:
                    return "initializeAdvancedExtension";
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
                    reply.writeString(ICameraExtensionsProxyService.DESCRIPTOR);
                    return true;
                default:
                    IBinder iBinder = null;
                    switch (code) {
                        case 1:
                            data.enforceInterface(ICameraExtensionsProxyService.DESCRIPTOR);
                            long _result = registerClient();
                            reply.writeNoException();
                            reply.writeLong(_result);
                            return true;
                        case 2:
                            data.enforceInterface(ICameraExtensionsProxyService.DESCRIPTOR);
                            long _arg0 = data.readLong();
                            unregisterClient(_arg0);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(ICameraExtensionsProxyService.DESCRIPTOR);
                            boolean advancedExtensionsSupported = advancedExtensionsSupported();
                            reply.writeNoException();
                            reply.writeInt(advancedExtensionsSupported ? 1 : 0);
                            return true;
                        case 4:
                            data.enforceInterface(ICameraExtensionsProxyService.DESCRIPTOR);
                            IInitializeSessionCallback _arg02 = IInitializeSessionCallback.Stub.asInterface(data.readStrongBinder());
                            initializeSession(_arg02);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(ICameraExtensionsProxyService.DESCRIPTOR);
                            releaseSession();
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(ICameraExtensionsProxyService.DESCRIPTOR);
                            int _arg03 = data.readInt();
                            IPreviewExtenderImpl _result2 = initializePreviewExtension(_arg03);
                            reply.writeNoException();
                            if (_result2 != null) {
                                iBinder = _result2.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 7:
                            data.enforceInterface(ICameraExtensionsProxyService.DESCRIPTOR);
                            int _arg04 = data.readInt();
                            IImageCaptureExtenderImpl _result3 = initializeImageExtension(_arg04);
                            reply.writeNoException();
                            if (_result3 != null) {
                                iBinder = _result3.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        case 8:
                            data.enforceInterface(ICameraExtensionsProxyService.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            IAdvancedExtenderImpl _result4 = initializeAdvancedExtension(_arg05);
                            reply.writeNoException();
                            if (_result4 != null) {
                                iBinder = _result4.asBinder();
                            }
                            reply.writeStrongBinder(iBinder);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ICameraExtensionsProxyService {
            public static ICameraExtensionsProxyService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ICameraExtensionsProxyService.DESCRIPTOR;
            }

            @Override // android.hardware.camera2.extension.ICameraExtensionsProxyService
            public long registerClient() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICameraExtensionsProxyService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerClient();
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.ICameraExtensionsProxyService
            public void unregisterClient(long clientId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICameraExtensionsProxyService.DESCRIPTOR);
                    _data.writeLong(clientId);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterClient(clientId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.ICameraExtensionsProxyService
            public boolean advancedExtensionsSupported() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICameraExtensionsProxyService.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().advancedExtensionsSupported();
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

            @Override // android.hardware.camera2.extension.ICameraExtensionsProxyService
            public void initializeSession(IInitializeSessionCallback cb) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICameraExtensionsProxyService.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().initializeSession(cb);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.ICameraExtensionsProxyService
            public void releaseSession() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICameraExtensionsProxyService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().releaseSession();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.ICameraExtensionsProxyService
            public IPreviewExtenderImpl initializePreviewExtension(int extensionType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICameraExtensionsProxyService.DESCRIPTOR);
                    _data.writeInt(extensionType);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().initializePreviewExtension(extensionType);
                    }
                    _reply.readException();
                    IPreviewExtenderImpl _result = IPreviewExtenderImpl.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.ICameraExtensionsProxyService
            public IImageCaptureExtenderImpl initializeImageExtension(int extensionType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICameraExtensionsProxyService.DESCRIPTOR);
                    _data.writeInt(extensionType);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().initializeImageExtension(extensionType);
                    }
                    _reply.readException();
                    IImageCaptureExtenderImpl _result = IImageCaptureExtenderImpl.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.camera2.extension.ICameraExtensionsProxyService
            public IAdvancedExtenderImpl initializeAdvancedExtension(int extensionType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICameraExtensionsProxyService.DESCRIPTOR);
                    _data.writeInt(extensionType);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().initializeAdvancedExtension(extensionType);
                    }
                    _reply.readException();
                    IAdvancedExtenderImpl _result = IAdvancedExtenderImpl.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICameraExtensionsProxyService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ICameraExtensionsProxyService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
