package android.os.storage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;
/* loaded from: classes2.dex */
public interface IOplusStorageManagerService extends IInterface {
    public static final String DESCRIPTOR = "android.os.storage.IOplusStorageManagerService";

    void addAuthResultInfo(int i, int i2, int i3, String str) throws RemoteException;

    int clearSDLockPassword() throws RemoteException;

    byte[] decryptDek(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) throws RemoteException;

    Map encryptDek(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, byte[] bArr4) throws RemoteException;

    void eraseSDLock() throws RemoteException;

    String getSDCardId() throws RemoteException;

    int getSDLockState() throws RemoteException;

    int getStorageData() throws RemoteException;

    long getUnlockSdcardDeadline() throws RemoteException;

    Map initAeKek() throws RemoteException;

    Map initBeKek() throws RemoteException;

    int setSDLockPassword(String str) throws RemoteException;

    int unlockSDCard(String str) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IOplusStorageManagerService {
        @Override // android.os.storage.IOplusStorageManagerService
        public int getStorageData() throws RemoteException {
            return 0;
        }

        @Override // android.os.storage.IOplusStorageManagerService
        public int setSDLockPassword(String pw) throws RemoteException {
            return 0;
        }

        @Override // android.os.storage.IOplusStorageManagerService
        public int clearSDLockPassword() throws RemoteException {
            return 0;
        }

        @Override // android.os.storage.IOplusStorageManagerService
        public int unlockSDCard(String pw) throws RemoteException {
            return 0;
        }

        @Override // android.os.storage.IOplusStorageManagerService
        public long getUnlockSdcardDeadline() throws RemoteException {
            return 0L;
        }

        @Override // android.os.storage.IOplusStorageManagerService
        public String getSDCardId() throws RemoteException {
            return null;
        }

        @Override // android.os.storage.IOplusStorageManagerService
        public int getSDLockState() throws RemoteException {
            return 0;
        }

        @Override // android.os.storage.IOplusStorageManagerService
        public void eraseSDLock() throws RemoteException {
        }

        @Override // android.os.storage.IOplusStorageManagerService
        public Map initAeKek() throws RemoteException {
            return null;
        }

        @Override // android.os.storage.IOplusStorageManagerService
        public Map initBeKek() throws RemoteException {
            return null;
        }

        @Override // android.os.storage.IOplusStorageManagerService
        public Map encryptDek(byte[] dek, int protectType, byte[] protectedKek, byte[] deviceNonce, byte[] kekID) throws RemoteException {
            return null;
        }

        @Override // android.os.storage.IOplusStorageManagerService
        public byte[] decryptDek(byte[] protectedDek, int protectType, byte[] protectedKek, byte[] deviceNonce, byte[] kekID, byte[] appNonce) throws RemoteException {
            return null;
        }

        @Override // android.os.storage.IOplusStorageManagerService
        public void addAuthResultInfo(int uid, int pid, int permBits, String packageName) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IOplusStorageManagerService {
        static final int TRANSACTION_addAuthResultInfo = 13;
        static final int TRANSACTION_clearSDLockPassword = 3;
        static final int TRANSACTION_decryptDek = 12;
        static final int TRANSACTION_encryptDek = 11;
        static final int TRANSACTION_eraseSDLock = 8;
        static final int TRANSACTION_getSDCardId = 6;
        static final int TRANSACTION_getSDLockState = 7;
        static final int TRANSACTION_getStorageData = 1;
        static final int TRANSACTION_getUnlockSdcardDeadline = 5;
        static final int TRANSACTION_initAeKek = 9;
        static final int TRANSACTION_initBeKek = 10;
        static final int TRANSACTION_setSDLockPassword = 2;
        static final int TRANSACTION_unlockSDCard = 4;

        public Stub() {
            attachInterface(this, IOplusStorageManagerService.DESCRIPTOR);
        }

        public static IOplusStorageManagerService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusStorageManagerService.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusStorageManagerService)) {
                return new Proxy(obj);
            }
            return (IOplusStorageManagerService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getStorageData";
                case 2:
                    return "setSDLockPassword";
                case 3:
                    return "clearSDLockPassword";
                case 4:
                    return "unlockSDCard";
                case 5:
                    return "getUnlockSdcardDeadline";
                case 6:
                    return "getSDCardId";
                case 7:
                    return "getSDLockState";
                case 8:
                    return "eraseSDLock";
                case 9:
                    return "initAeKek";
                case 10:
                    return "initBeKek";
                case 11:
                    return "encryptDek";
                case 12:
                    return "decryptDek";
                case 13:
                    return "addAuthResultInfo";
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
                    reply.writeString(IOplusStorageManagerService.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusStorageManagerService.DESCRIPTOR);
                            int _result = getStorageData();
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 2:
                            data.enforceInterface(IOplusStorageManagerService.DESCRIPTOR);
                            String _arg0 = data.readString();
                            int _result2 = setSDLockPassword(_arg0);
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        case 3:
                            data.enforceInterface(IOplusStorageManagerService.DESCRIPTOR);
                            int _result3 = clearSDLockPassword();
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            return true;
                        case 4:
                            data.enforceInterface(IOplusStorageManagerService.DESCRIPTOR);
                            String _arg02 = data.readString();
                            int _result4 = unlockSDCard(_arg02);
                            reply.writeNoException();
                            reply.writeInt(_result4);
                            return true;
                        case 5:
                            data.enforceInterface(IOplusStorageManagerService.DESCRIPTOR);
                            long _result5 = getUnlockSdcardDeadline();
                            reply.writeNoException();
                            reply.writeLong(_result5);
                            return true;
                        case 6:
                            data.enforceInterface(IOplusStorageManagerService.DESCRIPTOR);
                            String _result6 = getSDCardId();
                            reply.writeNoException();
                            reply.writeString(_result6);
                            return true;
                        case 7:
                            data.enforceInterface(IOplusStorageManagerService.DESCRIPTOR);
                            int _result7 = getSDLockState();
                            reply.writeNoException();
                            reply.writeInt(_result7);
                            return true;
                        case 8:
                            data.enforceInterface(IOplusStorageManagerService.DESCRIPTOR);
                            eraseSDLock();
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(IOplusStorageManagerService.DESCRIPTOR);
                            Map _result8 = initAeKek();
                            reply.writeNoException();
                            reply.writeMap(_result8);
                            return true;
                        case 10:
                            data.enforceInterface(IOplusStorageManagerService.DESCRIPTOR);
                            Map _result9 = initBeKek();
                            reply.writeNoException();
                            reply.writeMap(_result9);
                            return true;
                        case 11:
                            data.enforceInterface(IOplusStorageManagerService.DESCRIPTOR);
                            byte[] _arg03 = data.createByteArray();
                            int _arg1 = data.readInt();
                            byte[] _arg2 = data.createByteArray();
                            byte[] _arg3 = data.createByteArray();
                            byte[] _arg4 = data.createByteArray();
                            Map _result10 = encryptDek(_arg03, _arg1, _arg2, _arg3, _arg4);
                            reply.writeNoException();
                            reply.writeMap(_result10);
                            return true;
                        case 12:
                            data.enforceInterface(IOplusStorageManagerService.DESCRIPTOR);
                            byte[] _arg04 = data.createByteArray();
                            int _arg12 = data.readInt();
                            byte[] _arg22 = data.createByteArray();
                            byte[] _arg32 = data.createByteArray();
                            byte[] _arg42 = data.createByteArray();
                            byte[] _arg5 = data.createByteArray();
                            byte[] _result11 = decryptDek(_arg04, _arg12, _arg22, _arg32, _arg42, _arg5);
                            reply.writeNoException();
                            reply.writeByteArray(_result11);
                            return true;
                        case 13:
                            data.enforceInterface(IOplusStorageManagerService.DESCRIPTOR);
                            int _arg05 = data.readInt();
                            int _arg13 = data.readInt();
                            int _arg23 = data.readInt();
                            String _arg33 = data.readString();
                            addAuthResultInfo(_arg05, _arg13, _arg23, _arg33);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IOplusStorageManagerService {
            public static IOplusStorageManagerService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusStorageManagerService.DESCRIPTOR;
            }

            @Override // android.os.storage.IOplusStorageManagerService
            public int getStorageData() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusStorageManagerService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getStorageData();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IOplusStorageManagerService
            public int setSDLockPassword(String pw) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusStorageManagerService.DESCRIPTOR);
                    _data.writeString(pw);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setSDLockPassword(pw);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IOplusStorageManagerService
            public int clearSDLockPassword() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusStorageManagerService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().clearSDLockPassword();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IOplusStorageManagerService
            public int unlockSDCard(String pw) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusStorageManagerService.DESCRIPTOR);
                    _data.writeString(pw);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unlockSDCard(pw);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IOplusStorageManagerService
            public long getUnlockSdcardDeadline() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusStorageManagerService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUnlockSdcardDeadline();
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IOplusStorageManagerService
            public String getSDCardId() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusStorageManagerService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSDCardId();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IOplusStorageManagerService
            public int getSDLockState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusStorageManagerService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSDLockState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IOplusStorageManagerService
            public void eraseSDLock() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusStorageManagerService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().eraseSDLock();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IOplusStorageManagerService
            public Map initAeKek() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusStorageManagerService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().initAeKek();
                    }
                    _reply.readException();
                    ClassLoader cl = getClass().getClassLoader();
                    Map _result = _reply.readHashMap(cl);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IOplusStorageManagerService
            public Map initBeKek() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusStorageManagerService.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().initBeKek();
                    }
                    _reply.readException();
                    ClassLoader cl = getClass().getClassLoader();
                    Map _result = _reply.readHashMap(cl);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IOplusStorageManagerService
            public Map encryptDek(byte[] dek, int protectType, byte[] protectedKek, byte[] deviceNonce, byte[] kekID) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusStorageManagerService.DESCRIPTOR);
                    _data.writeByteArray(dek);
                    _data.writeInt(protectType);
                    _data.writeByteArray(protectedKek);
                    _data.writeByteArray(deviceNonce);
                    _data.writeByteArray(kekID);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().encryptDek(dek, protectType, protectedKek, deviceNonce, kekID);
                    }
                    _reply.readException();
                    ClassLoader cl = getClass().getClassLoader();
                    Map _result = _reply.readHashMap(cl);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.storage.IOplusStorageManagerService
            public byte[] decryptDek(byte[] protectedDek, int protectType, byte[] protectedKek, byte[] deviceNonce, byte[] kekID, byte[] appNonce) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusStorageManagerService.DESCRIPTOR);
                    try {
                        _data.writeByteArray(protectedDek);
                        try {
                            _data.writeInt(protectType);
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
                    _data.writeByteArray(protectedKek);
                    try {
                        _data.writeByteArray(deviceNonce);
                        try {
                            _data.writeByteArray(kekID);
                            try {
                                _data.writeByteArray(appNonce);
                                boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _reply.readException();
                                    byte[] _result = _reply.createByteArray();
                                    _reply.recycle();
                                    _data.recycle();
                                    return _result;
                                }
                                byte[] decryptDek = Stub.getDefaultImpl().decryptDek(protectedDek, protectType, protectedKek, deviceNonce, kekID, appNonce);
                                _reply.recycle();
                                _data.recycle();
                                return decryptDek;
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
                } catch (Throwable th8) {
                    th = th8;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.os.storage.IOplusStorageManagerService
            public void addAuthResultInfo(int uid, int pid, int permBits, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusStorageManagerService.DESCRIPTOR);
                    _data.writeInt(uid);
                    _data.writeInt(pid);
                    _data.writeInt(permBits);
                    _data.writeString(packageName);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addAuthResultInfo(uid, pid, permBits, packageName);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusStorageManagerService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusStorageManagerService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
