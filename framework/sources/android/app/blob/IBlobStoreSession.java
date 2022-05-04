package android.app.blob;

import android.app.blob.IBlobCommitCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IBlobStoreSession extends IInterface {
    public static final String DESCRIPTOR = "android.app.blob.IBlobStoreSession";

    void abandon() throws RemoteException;

    void allowPackageAccess(String str, byte[] bArr) throws RemoteException;

    void allowPublicAccess() throws RemoteException;

    void allowSameSignatureAccess() throws RemoteException;

    void close() throws RemoteException;

    void commit(IBlobCommitCallback iBlobCommitCallback) throws RemoteException;

    long getSize() throws RemoteException;

    boolean isPackageAccessAllowed(String str, byte[] bArr) throws RemoteException;

    boolean isPublicAccessAllowed() throws RemoteException;

    boolean isSameSignatureAccessAllowed() throws RemoteException;

    ParcelFileDescriptor openRead() throws RemoteException;

    ParcelFileDescriptor openWrite(long j, long j2) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IBlobStoreSession {
        @Override // android.app.blob.IBlobStoreSession
        public ParcelFileDescriptor openWrite(long offsetBytes, long lengthBytes) throws RemoteException {
            return null;
        }

        @Override // android.app.blob.IBlobStoreSession
        public ParcelFileDescriptor openRead() throws RemoteException {
            return null;
        }

        @Override // android.app.blob.IBlobStoreSession
        public void allowPackageAccess(String packageName, byte[] certificate) throws RemoteException {
        }

        @Override // android.app.blob.IBlobStoreSession
        public void allowSameSignatureAccess() throws RemoteException {
        }

        @Override // android.app.blob.IBlobStoreSession
        public void allowPublicAccess() throws RemoteException {
        }

        @Override // android.app.blob.IBlobStoreSession
        public boolean isPackageAccessAllowed(String packageName, byte[] certificate) throws RemoteException {
            return false;
        }

        @Override // android.app.blob.IBlobStoreSession
        public boolean isSameSignatureAccessAllowed() throws RemoteException {
            return false;
        }

        @Override // android.app.blob.IBlobStoreSession
        public boolean isPublicAccessAllowed() throws RemoteException {
            return false;
        }

        @Override // android.app.blob.IBlobStoreSession
        public long getSize() throws RemoteException {
            return 0L;
        }

        @Override // android.app.blob.IBlobStoreSession
        public void close() throws RemoteException {
        }

        @Override // android.app.blob.IBlobStoreSession
        public void abandon() throws RemoteException {
        }

        @Override // android.app.blob.IBlobStoreSession
        public void commit(IBlobCommitCallback callback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IBlobStoreSession {
        static final int TRANSACTION_abandon = 11;
        static final int TRANSACTION_allowPackageAccess = 3;
        static final int TRANSACTION_allowPublicAccess = 5;
        static final int TRANSACTION_allowSameSignatureAccess = 4;
        static final int TRANSACTION_close = 10;
        static final int TRANSACTION_commit = 12;
        static final int TRANSACTION_getSize = 9;
        static final int TRANSACTION_isPackageAccessAllowed = 6;
        static final int TRANSACTION_isPublicAccessAllowed = 8;
        static final int TRANSACTION_isSameSignatureAccessAllowed = 7;
        static final int TRANSACTION_openRead = 2;
        static final int TRANSACTION_openWrite = 1;

        public Stub() {
            attachInterface(this, IBlobStoreSession.DESCRIPTOR);
        }

        public static IBlobStoreSession asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IBlobStoreSession.DESCRIPTOR);
            if (iin == null || !(iin instanceof IBlobStoreSession)) {
                return new Proxy(obj);
            }
            return (IBlobStoreSession) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "openWrite";
                case 2:
                    return "openRead";
                case 3:
                    return "allowPackageAccess";
                case 4:
                    return "allowSameSignatureAccess";
                case 5:
                    return "allowPublicAccess";
                case 6:
                    return "isPackageAccessAllowed";
                case 7:
                    return "isSameSignatureAccessAllowed";
                case 8:
                    return "isPublicAccessAllowed";
                case 9:
                    return "getSize";
                case 10:
                    return "close";
                case 11:
                    return "abandon";
                case 12:
                    return "commit";
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
                    reply.writeString(IBlobStoreSession.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IBlobStoreSession.DESCRIPTOR);
                            long _arg0 = data.readLong();
                            long _arg1 = data.readLong();
                            ParcelFileDescriptor _result = openWrite(_arg0, _arg1);
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 2:
                            data.enforceInterface(IBlobStoreSession.DESCRIPTOR);
                            ParcelFileDescriptor _result2 = openRead();
                            reply.writeNoException();
                            if (_result2 != null) {
                                reply.writeInt(1);
                                _result2.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 3:
                            data.enforceInterface(IBlobStoreSession.DESCRIPTOR);
                            String _arg02 = data.readString();
                            byte[] _arg12 = data.createByteArray();
                            allowPackageAccess(_arg02, _arg12);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(IBlobStoreSession.DESCRIPTOR);
                            allowSameSignatureAccess();
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(IBlobStoreSession.DESCRIPTOR);
                            allowPublicAccess();
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(IBlobStoreSession.DESCRIPTOR);
                            String _arg03 = data.readString();
                            byte[] _arg13 = data.createByteArray();
                            boolean isPackageAccessAllowed = isPackageAccessAllowed(_arg03, _arg13);
                            reply.writeNoException();
                            reply.writeInt(isPackageAccessAllowed ? 1 : 0);
                            return true;
                        case 7:
                            data.enforceInterface(IBlobStoreSession.DESCRIPTOR);
                            boolean isSameSignatureAccessAllowed = isSameSignatureAccessAllowed();
                            reply.writeNoException();
                            reply.writeInt(isSameSignatureAccessAllowed ? 1 : 0);
                            return true;
                        case 8:
                            data.enforceInterface(IBlobStoreSession.DESCRIPTOR);
                            boolean isPublicAccessAllowed = isPublicAccessAllowed();
                            reply.writeNoException();
                            reply.writeInt(isPublicAccessAllowed ? 1 : 0);
                            return true;
                        case 9:
                            data.enforceInterface(IBlobStoreSession.DESCRIPTOR);
                            long _result3 = getSize();
                            reply.writeNoException();
                            reply.writeLong(_result3);
                            return true;
                        case 10:
                            data.enforceInterface(IBlobStoreSession.DESCRIPTOR);
                            close();
                            reply.writeNoException();
                            return true;
                        case 11:
                            data.enforceInterface(IBlobStoreSession.DESCRIPTOR);
                            abandon();
                            reply.writeNoException();
                            return true;
                        case 12:
                            data.enforceInterface(IBlobStoreSession.DESCRIPTOR);
                            IBlobCommitCallback _arg04 = IBlobCommitCallback.Stub.asInterface(data.readStrongBinder());
                            commit(_arg04);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IBlobStoreSession {
            public static IBlobStoreSession sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBlobStoreSession.DESCRIPTOR;
            }

            @Override // android.app.blob.IBlobStoreSession
            public ParcelFileDescriptor openWrite(long offsetBytes, long lengthBytes) throws RemoteException {
                ParcelFileDescriptor _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBlobStoreSession.DESCRIPTOR);
                    _data.writeLong(offsetBytes);
                    _data.writeLong(lengthBytes);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openWrite(offsetBytes, lengthBytes);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParcelFileDescriptor.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreSession
            public ParcelFileDescriptor openRead() throws RemoteException {
                ParcelFileDescriptor _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBlobStoreSession.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openRead();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParcelFileDescriptor.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreSession
            public void allowPackageAccess(String packageName, byte[] certificate) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBlobStoreSession.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeByteArray(certificate);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().allowPackageAccess(packageName, certificate);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreSession
            public void allowSameSignatureAccess() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBlobStoreSession.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().allowSameSignatureAccess();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreSession
            public void allowPublicAccess() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBlobStoreSession.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().allowPublicAccess();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreSession
            public boolean isPackageAccessAllowed(String packageName, byte[] certificate) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBlobStoreSession.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeByteArray(certificate);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPackageAccessAllowed(packageName, certificate);
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

            @Override // android.app.blob.IBlobStoreSession
            public boolean isSameSignatureAccessAllowed() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBlobStoreSession.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSameSignatureAccessAllowed();
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

            @Override // android.app.blob.IBlobStoreSession
            public boolean isPublicAccessAllowed() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBlobStoreSession.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPublicAccessAllowed();
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

            @Override // android.app.blob.IBlobStoreSession
            public long getSize() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBlobStoreSession.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSize();
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreSession
            public void close() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBlobStoreSession.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().close();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreSession
            public void abandon() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBlobStoreSession.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().abandon();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreSession
            public void commit(IBlobCommitCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBlobStoreSession.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().commit(callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBlobStoreSession impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IBlobStoreSession getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
