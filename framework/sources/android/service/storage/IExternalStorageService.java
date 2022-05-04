package android.service.storage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.os.storage.StorageVolume;
/* loaded from: classes3.dex */
public interface IExternalStorageService extends IInterface {
    public static final String DESCRIPTOR = "android.service.storage.IExternalStorageService";

    void endSession(String str, RemoteCallback remoteCallback) throws RemoteException;

    void freeCache(String str, String str2, long j, RemoteCallback remoteCallback) throws RemoteException;

    void notifyAnrDelayStarted(String str, int i, int i2, int i3) throws RemoteException;

    void notifyVolumeStateChanged(String str, StorageVolume storageVolume, RemoteCallback remoteCallback) throws RemoteException;

    void startSession(String str, int i, ParcelFileDescriptor parcelFileDescriptor, String str2, String str3, RemoteCallback remoteCallback) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IExternalStorageService {
        @Override // android.service.storage.IExternalStorageService
        public void startSession(String sessionId, int type, ParcelFileDescriptor deviceFd, String upperPath, String lowerPath, RemoteCallback callback) throws RemoteException {
        }

        @Override // android.service.storage.IExternalStorageService
        public void endSession(String sessionId, RemoteCallback callback) throws RemoteException {
        }

        @Override // android.service.storage.IExternalStorageService
        public void notifyVolumeStateChanged(String sessionId, StorageVolume vol, RemoteCallback callback) throws RemoteException {
        }

        @Override // android.service.storage.IExternalStorageService
        public void freeCache(String sessionId, String volumeUuid, long bytes, RemoteCallback callback) throws RemoteException {
        }

        @Override // android.service.storage.IExternalStorageService
        public void notifyAnrDelayStarted(String packageName, int uid, int tid, int reason) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IExternalStorageService {
        static final int TRANSACTION_endSession = 2;
        static final int TRANSACTION_freeCache = 4;
        static final int TRANSACTION_notifyAnrDelayStarted = 5;
        static final int TRANSACTION_notifyVolumeStateChanged = 3;
        static final int TRANSACTION_startSession = 1;

        public Stub() {
            attachInterface(this, IExternalStorageService.DESCRIPTOR);
        }

        public static IExternalStorageService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IExternalStorageService.DESCRIPTOR);
            if (iin == null || !(iin instanceof IExternalStorageService)) {
                return new Proxy(obj);
            }
            return (IExternalStorageService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "startSession";
                case 2:
                    return "endSession";
                case 3:
                    return "notifyVolumeStateChanged";
                case 4:
                    return "freeCache";
                case 5:
                    return "notifyAnrDelayStarted";
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
            ParcelFileDescriptor _arg2;
            RemoteCallback _arg5;
            RemoteCallback _arg1;
            StorageVolume _arg12;
            RemoteCallback _arg22;
            RemoteCallback _arg3;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IExternalStorageService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IExternalStorageService.DESCRIPTOR);
                            String _arg0 = data.readString();
                            int _arg13 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg2 = ParcelFileDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            String _arg32 = data.readString();
                            String _arg4 = data.readString();
                            if (data.readInt() != 0) {
                                _arg5 = RemoteCallback.CREATOR.createFromParcel(data);
                            } else {
                                _arg5 = null;
                            }
                            startSession(_arg0, _arg13, _arg2, _arg32, _arg4, _arg5);
                            return true;
                        case 2:
                            data.enforceInterface(IExternalStorageService.DESCRIPTOR);
                            String _arg02 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = RemoteCallback.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            endSession(_arg02, _arg1);
                            return true;
                        case 3:
                            data.enforceInterface(IExternalStorageService.DESCRIPTOR);
                            String _arg03 = data.readString();
                            if (data.readInt() != 0) {
                                _arg12 = StorageVolume.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg22 = RemoteCallback.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            notifyVolumeStateChanged(_arg03, _arg12, _arg22);
                            return true;
                        case 4:
                            data.enforceInterface(IExternalStorageService.DESCRIPTOR);
                            String _arg04 = data.readString();
                            String _arg14 = data.readString();
                            long _arg23 = data.readLong();
                            if (data.readInt() != 0) {
                                _arg3 = RemoteCallback.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            freeCache(_arg04, _arg14, _arg23, _arg3);
                            return true;
                        case 5:
                            data.enforceInterface(IExternalStorageService.DESCRIPTOR);
                            String _arg05 = data.readString();
                            int _arg15 = data.readInt();
                            int _arg24 = data.readInt();
                            int _arg33 = data.readInt();
                            notifyAnrDelayStarted(_arg05, _arg15, _arg24, _arg33);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IExternalStorageService {
            public static IExternalStorageService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IExternalStorageService.DESCRIPTOR;
            }

            @Override // android.service.storage.IExternalStorageService
            public void startSession(String sessionId, int type, ParcelFileDescriptor deviceFd, String upperPath, String lowerPath, RemoteCallback callback) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IExternalStorageService.DESCRIPTOR);
                    try {
                        _data.writeString(sessionId);
                        try {
                            _data.writeInt(type);
                            if (deviceFd != null) {
                                _data.writeInt(1);
                                deviceFd.writeToParcel(_data, 0);
                            } else {
                                _data.writeInt(0);
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
                try {
                    _data.writeString(upperPath);
                    try {
                        _data.writeString(lowerPath);
                        if (callback != null) {
                            _data.writeInt(1);
                            callback.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        try {
                            boolean _status = this.mRemote.transact(1, _data, null, 1);
                            if (_status || Stub.getDefaultImpl() == null) {
                                _data.recycle();
                                return;
                            }
                            Stub.getDefaultImpl().startSession(sessionId, type, deviceFd, upperPath, lowerPath, callback);
                            _data.recycle();
                        } catch (Throwable th5) {
                            th = th5;
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.service.storage.IExternalStorageService
            public void endSession(String sessionId, RemoteCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IExternalStorageService.DESCRIPTOR);
                    _data.writeString(sessionId);
                    if (callback != null) {
                        _data.writeInt(1);
                        callback.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().endSession(sessionId, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.storage.IExternalStorageService
            public void notifyVolumeStateChanged(String sessionId, StorageVolume vol, RemoteCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IExternalStorageService.DESCRIPTOR);
                    _data.writeString(sessionId);
                    if (vol != null) {
                        _data.writeInt(1);
                        vol.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (callback != null) {
                        _data.writeInt(1);
                        callback.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyVolumeStateChanged(sessionId, vol, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.storage.IExternalStorageService
            public void freeCache(String sessionId, String volumeUuid, long bytes, RemoteCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IExternalStorageService.DESCRIPTOR);
                    _data.writeString(sessionId);
                    _data.writeString(volumeUuid);
                    _data.writeLong(bytes);
                    if (callback != null) {
                        _data.writeInt(1);
                        callback.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().freeCache(sessionId, volumeUuid, bytes, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.storage.IExternalStorageService
            public void notifyAnrDelayStarted(String packageName, int uid, int tid, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IExternalStorageService.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(uid);
                    _data.writeInt(tid);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyAnrDelayStarted(packageName, uid, tid, reason);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IExternalStorageService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IExternalStorageService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
