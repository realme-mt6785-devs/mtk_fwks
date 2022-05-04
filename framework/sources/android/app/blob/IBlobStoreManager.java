package android.app.blob;

import android.app.blob.IBlobStoreSession;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.text.TextUtils;
import java.util.List;
/* loaded from: classes.dex */
public interface IBlobStoreManager extends IInterface {
    public static final String DESCRIPTOR = "android.app.blob.IBlobStoreManager";

    void abandonSession(long j, String str) throws RemoteException;

    void acquireLease(BlobHandle blobHandle, int i, CharSequence charSequence, long j, String str) throws RemoteException;

    long createSession(BlobHandle blobHandle, String str) throws RemoteException;

    void deleteBlob(long j) throws RemoteException;

    LeaseInfo getLeaseInfo(BlobHandle blobHandle, String str) throws RemoteException;

    List<BlobHandle> getLeasedBlobs(String str) throws RemoteException;

    long getRemainingLeaseQuotaBytes(String str) throws RemoteException;

    ParcelFileDescriptor openBlob(BlobHandle blobHandle, String str) throws RemoteException;

    IBlobStoreSession openSession(long j, String str) throws RemoteException;

    List<BlobInfo> queryBlobsForUser(int i) throws RemoteException;

    void releaseLease(BlobHandle blobHandle, String str) throws RemoteException;

    void waitForIdle(RemoteCallback remoteCallback) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IBlobStoreManager {
        @Override // android.app.blob.IBlobStoreManager
        public long createSession(BlobHandle handle, String packageName) throws RemoteException {
            return 0L;
        }

        @Override // android.app.blob.IBlobStoreManager
        public IBlobStoreSession openSession(long sessionId, String packageName) throws RemoteException {
            return null;
        }

        @Override // android.app.blob.IBlobStoreManager
        public ParcelFileDescriptor openBlob(BlobHandle handle, String packageName) throws RemoteException {
            return null;
        }

        @Override // android.app.blob.IBlobStoreManager
        public void abandonSession(long sessionId, String packageName) throws RemoteException {
        }

        @Override // android.app.blob.IBlobStoreManager
        public void acquireLease(BlobHandle handle, int descriptionResId, CharSequence description, long leaseTimeoutMillis, String packageName) throws RemoteException {
        }

        @Override // android.app.blob.IBlobStoreManager
        public void releaseLease(BlobHandle handle, String packageName) throws RemoteException {
        }

        @Override // android.app.blob.IBlobStoreManager
        public long getRemainingLeaseQuotaBytes(String packageName) throws RemoteException {
            return 0L;
        }

        @Override // android.app.blob.IBlobStoreManager
        public void waitForIdle(RemoteCallback callback) throws RemoteException {
        }

        @Override // android.app.blob.IBlobStoreManager
        public List<BlobInfo> queryBlobsForUser(int userId) throws RemoteException {
            return null;
        }

        @Override // android.app.blob.IBlobStoreManager
        public void deleteBlob(long blobId) throws RemoteException {
        }

        @Override // android.app.blob.IBlobStoreManager
        public List<BlobHandle> getLeasedBlobs(String packageName) throws RemoteException {
            return null;
        }

        @Override // android.app.blob.IBlobStoreManager
        public LeaseInfo getLeaseInfo(BlobHandle blobHandle, String packageName) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IBlobStoreManager {
        static final int TRANSACTION_abandonSession = 4;
        static final int TRANSACTION_acquireLease = 5;
        static final int TRANSACTION_createSession = 1;
        static final int TRANSACTION_deleteBlob = 10;
        static final int TRANSACTION_getLeaseInfo = 12;
        static final int TRANSACTION_getLeasedBlobs = 11;
        static final int TRANSACTION_getRemainingLeaseQuotaBytes = 7;
        static final int TRANSACTION_openBlob = 3;
        static final int TRANSACTION_openSession = 2;
        static final int TRANSACTION_queryBlobsForUser = 9;
        static final int TRANSACTION_releaseLease = 6;
        static final int TRANSACTION_waitForIdle = 8;

        public Stub() {
            attachInterface(this, IBlobStoreManager.DESCRIPTOR);
        }

        public static IBlobStoreManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IBlobStoreManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof IBlobStoreManager)) {
                return new Proxy(obj);
            }
            return (IBlobStoreManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "createSession";
                case 2:
                    return "openSession";
                case 3:
                    return "openBlob";
                case 4:
                    return "abandonSession";
                case 5:
                    return "acquireLease";
                case 6:
                    return "releaseLease";
                case 7:
                    return "getRemainingLeaseQuotaBytes";
                case 8:
                    return "waitForIdle";
                case 9:
                    return "queryBlobsForUser";
                case 10:
                    return "deleteBlob";
                case 11:
                    return "getLeasedBlobs";
                case 12:
                    return "getLeaseInfo";
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
            BlobHandle _arg0;
            BlobHandle _arg02;
            BlobHandle _arg03;
            CharSequence _arg2;
            BlobHandle _arg04;
            RemoteCallback _arg05;
            BlobHandle _arg06;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IBlobStoreManager.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IBlobStoreManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = BlobHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            String _arg1 = data.readString();
                            long _result = createSession(_arg0, _arg1);
                            reply.writeNoException();
                            reply.writeLong(_result);
                            return true;
                        case 2:
                            data.enforceInterface(IBlobStoreManager.DESCRIPTOR);
                            long _arg07 = data.readLong();
                            String _arg12 = data.readString();
                            IBlobStoreSession _result2 = openSession(_arg07, _arg12);
                            reply.writeNoException();
                            reply.writeStrongBinder(_result2 != null ? _result2.asBinder() : null);
                            return true;
                        case 3:
                            data.enforceInterface(IBlobStoreManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = BlobHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            String _arg13 = data.readString();
                            ParcelFileDescriptor _result3 = openBlob(_arg02, _arg13);
                            reply.writeNoException();
                            if (_result3 != null) {
                                reply.writeInt(1);
                                _result3.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 4:
                            data.enforceInterface(IBlobStoreManager.DESCRIPTOR);
                            long _arg08 = data.readLong();
                            String _arg14 = data.readString();
                            abandonSession(_arg08, _arg14);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(IBlobStoreManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = BlobHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            int _arg15 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg2 = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            long _arg3 = data.readLong();
                            String _arg4 = data.readString();
                            acquireLease(_arg03, _arg15, _arg2, _arg3, _arg4);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(IBlobStoreManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = BlobHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            String _arg16 = data.readString();
                            releaseLease(_arg04, _arg16);
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(IBlobStoreManager.DESCRIPTOR);
                            String _arg09 = data.readString();
                            long _result4 = getRemainingLeaseQuotaBytes(_arg09);
                            reply.writeNoException();
                            reply.writeLong(_result4);
                            return true;
                        case 8:
                            data.enforceInterface(IBlobStoreManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg05 = RemoteCallback.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            waitForIdle(_arg05);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(IBlobStoreManager.DESCRIPTOR);
                            int _arg010 = data.readInt();
                            List<BlobInfo> _result5 = queryBlobsForUser(_arg010);
                            reply.writeNoException();
                            reply.writeTypedList(_result5);
                            return true;
                        case 10:
                            data.enforceInterface(IBlobStoreManager.DESCRIPTOR);
                            long _arg011 = data.readLong();
                            deleteBlob(_arg011);
                            reply.writeNoException();
                            return true;
                        case 11:
                            data.enforceInterface(IBlobStoreManager.DESCRIPTOR);
                            String _arg012 = data.readString();
                            List<BlobHandle> _result6 = getLeasedBlobs(_arg012);
                            reply.writeNoException();
                            reply.writeTypedList(_result6);
                            return true;
                        case 12:
                            data.enforceInterface(IBlobStoreManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg06 = BlobHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg06 = null;
                            }
                            String _arg17 = data.readString();
                            LeaseInfo _result7 = getLeaseInfo(_arg06, _arg17);
                            reply.writeNoException();
                            if (_result7 != null) {
                                reply.writeInt(1);
                                _result7.writeToParcel(reply, 1);
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
        public static class Proxy implements IBlobStoreManager {
            public static IBlobStoreManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBlobStoreManager.DESCRIPTOR;
            }

            @Override // android.app.blob.IBlobStoreManager
            public long createSession(BlobHandle handle, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBlobStoreManager.DESCRIPTOR);
                    if (handle != null) {
                        _data.writeInt(1);
                        handle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createSession(handle, packageName);
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreManager
            public IBlobStoreSession openSession(long sessionId, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBlobStoreManager.DESCRIPTOR);
                    _data.writeLong(sessionId);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openSession(sessionId, packageName);
                    }
                    _reply.readException();
                    IBlobStoreSession _result = IBlobStoreSession.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreManager
            public ParcelFileDescriptor openBlob(BlobHandle handle, String packageName) throws RemoteException {
                ParcelFileDescriptor _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBlobStoreManager.DESCRIPTOR);
                    if (handle != null) {
                        _data.writeInt(1);
                        handle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openBlob(handle, packageName);
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

            @Override // android.app.blob.IBlobStoreManager
            public void abandonSession(long sessionId, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBlobStoreManager.DESCRIPTOR);
                    _data.writeLong(sessionId);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().abandonSession(sessionId, packageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreManager
            public void acquireLease(BlobHandle handle, int descriptionResId, CharSequence description, long leaseTimeoutMillis, String packageName) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBlobStoreManager.DESCRIPTOR);
                    if (handle != null) {
                        _data.writeInt(1);
                        handle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    try {
                        _data.writeInt(descriptionResId);
                        if (description != null) {
                            _data.writeInt(1);
                            TextUtils.writeToParcel(description, _data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        try {
                            _data.writeLong(leaseTimeoutMillis);
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
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().acquireLease(handle, descriptionResId, description, leaseTimeoutMillis, packageName);
                    _reply.recycle();
                    _data.recycle();
                } catch (Throwable th5) {
                    th = th5;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.app.blob.IBlobStoreManager
            public void releaseLease(BlobHandle handle, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBlobStoreManager.DESCRIPTOR);
                    if (handle != null) {
                        _data.writeInt(1);
                        handle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().releaseLease(handle, packageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreManager
            public long getRemainingLeaseQuotaBytes(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBlobStoreManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRemainingLeaseQuotaBytes(packageName);
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreManager
            public void waitForIdle(RemoteCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBlobStoreManager.DESCRIPTOR);
                    if (callback != null) {
                        _data.writeInt(1);
                        callback.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().waitForIdle(callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreManager
            public List<BlobInfo> queryBlobsForUser(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBlobStoreManager.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().queryBlobsForUser(userId);
                    }
                    _reply.readException();
                    List<BlobInfo> _result = _reply.createTypedArrayList(BlobInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreManager
            public void deleteBlob(long blobId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBlobStoreManager.DESCRIPTOR);
                    _data.writeLong(blobId);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().deleteBlob(blobId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreManager
            public List<BlobHandle> getLeasedBlobs(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBlobStoreManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLeasedBlobs(packageName);
                    }
                    _reply.readException();
                    List<BlobHandle> _result = _reply.createTypedArrayList(BlobHandle.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreManager
            public LeaseInfo getLeaseInfo(BlobHandle blobHandle, String packageName) throws RemoteException {
                LeaseInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBlobStoreManager.DESCRIPTOR);
                    if (blobHandle != null) {
                        _data.writeInt(1);
                        blobHandle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLeaseInfo(blobHandle, packageName);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = LeaseInfo.CREATOR.createFromParcel(_reply);
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

        public static boolean setDefaultImpl(IBlobStoreManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IBlobStoreManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
