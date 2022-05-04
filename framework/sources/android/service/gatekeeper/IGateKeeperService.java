package android.service.gatekeeper;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IGateKeeperService extends IInterface {
    public static final String DESCRIPTOR = "android.service.gatekeeper.IGateKeeperService";

    void clearSecureUserId(int i) throws RemoteException;

    GateKeeperResponse enroll(int i, byte[] bArr, byte[] bArr2, byte[] bArr3) throws RemoteException;

    long getSecureUserId(int i) throws RemoteException;

    void reportDeviceSetupComplete() throws RemoteException;

    GateKeeperResponse verify(int i, byte[] bArr, byte[] bArr2) throws RemoteException;

    GateKeeperResponse verifyChallenge(int i, long j, byte[] bArr, byte[] bArr2) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IGateKeeperService {
        @Override // android.service.gatekeeper.IGateKeeperService
        public GateKeeperResponse enroll(int userId, byte[] currentPasswordHandle, byte[] currentPassword, byte[] desiredPassword) throws RemoteException {
            return null;
        }

        @Override // android.service.gatekeeper.IGateKeeperService
        public GateKeeperResponse verify(int userId, byte[] enrolledPasswordHandle, byte[] providedPassword) throws RemoteException {
            return null;
        }

        @Override // android.service.gatekeeper.IGateKeeperService
        public GateKeeperResponse verifyChallenge(int userId, long challenge, byte[] enrolledPasswordHandle, byte[] providedPassword) throws RemoteException {
            return null;
        }

        @Override // android.service.gatekeeper.IGateKeeperService
        public long getSecureUserId(int userId) throws RemoteException {
            return 0L;
        }

        @Override // android.service.gatekeeper.IGateKeeperService
        public void clearSecureUserId(int userId) throws RemoteException {
        }

        @Override // android.service.gatekeeper.IGateKeeperService
        public void reportDeviceSetupComplete() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IGateKeeperService {
        static final int TRANSACTION_clearSecureUserId = 5;
        static final int TRANSACTION_enroll = 1;
        static final int TRANSACTION_getSecureUserId = 4;
        static final int TRANSACTION_reportDeviceSetupComplete = 6;
        static final int TRANSACTION_verify = 2;
        static final int TRANSACTION_verifyChallenge = 3;

        public Stub() {
            attachInterface(this, "android.service.gatekeeper.IGateKeeperService");
        }

        public static IGateKeeperService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface("android.service.gatekeeper.IGateKeeperService");
            if (iin == null || !(iin instanceof IGateKeeperService)) {
                return new Proxy(obj);
            }
            return (IGateKeeperService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "enroll";
                case 2:
                    return "verify";
                case 3:
                    return "verifyChallenge";
                case 4:
                    return "getSecureUserId";
                case 5:
                    return "clearSecureUserId";
                case 6:
                    return "reportDeviceSetupComplete";
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
                    reply.writeString("android.service.gatekeeper.IGateKeeperService");
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface("android.service.gatekeeper.IGateKeeperService");
                            int _arg0 = data.readInt();
                            byte[] _arg1 = data.createByteArray();
                            byte[] _arg2 = data.createByteArray();
                            byte[] _arg3 = data.createByteArray();
                            GateKeeperResponse _result = enroll(_arg0, _arg1, _arg2, _arg3);
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 2:
                            data.enforceInterface("android.service.gatekeeper.IGateKeeperService");
                            int _arg02 = data.readInt();
                            byte[] _arg12 = data.createByteArray();
                            byte[] _arg22 = data.createByteArray();
                            GateKeeperResponse _result2 = verify(_arg02, _arg12, _arg22);
                            reply.writeNoException();
                            if (_result2 != null) {
                                reply.writeInt(1);
                                _result2.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 3:
                            data.enforceInterface("android.service.gatekeeper.IGateKeeperService");
                            int _arg03 = data.readInt();
                            long _arg13 = data.readLong();
                            byte[] _arg23 = data.createByteArray();
                            byte[] _arg32 = data.createByteArray();
                            GateKeeperResponse _result3 = verifyChallenge(_arg03, _arg13, _arg23, _arg32);
                            reply.writeNoException();
                            if (_result3 != null) {
                                reply.writeInt(1);
                                _result3.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 4:
                            data.enforceInterface("android.service.gatekeeper.IGateKeeperService");
                            int _arg04 = data.readInt();
                            long _result4 = getSecureUserId(_arg04);
                            reply.writeNoException();
                            reply.writeLong(_result4);
                            return true;
                        case 5:
                            data.enforceInterface("android.service.gatekeeper.IGateKeeperService");
                            int _arg05 = data.readInt();
                            clearSecureUserId(_arg05);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface("android.service.gatekeeper.IGateKeeperService");
                            reportDeviceSetupComplete();
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IGateKeeperService {
            public static IGateKeeperService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "android.service.gatekeeper.IGateKeeperService";
            }

            @Override // android.service.gatekeeper.IGateKeeperService
            public GateKeeperResponse enroll(int userId, byte[] currentPasswordHandle, byte[] currentPassword, byte[] desiredPassword) throws RemoteException {
                GateKeeperResponse _result;
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.service.gatekeeper.IGateKeeperService");
                    _data.writeInt(userId);
                    _data.writeByteArray(currentPasswordHandle);
                    _data.writeByteArray(currentPassword);
                    _data.writeByteArray(desiredPassword);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 32);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enroll(userId, currentPasswordHandle, currentPassword, desiredPassword);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = GateKeeperResponse.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.service.gatekeeper.IGateKeeperService
            public GateKeeperResponse verify(int userId, byte[] enrolledPasswordHandle, byte[] providedPassword) throws RemoteException {
                GateKeeperResponse _result;
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.service.gatekeeper.IGateKeeperService");
                    _data.writeInt(userId);
                    _data.writeByteArray(enrolledPasswordHandle);
                    _data.writeByteArray(providedPassword);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 32);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().verify(userId, enrolledPasswordHandle, providedPassword);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = GateKeeperResponse.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.service.gatekeeper.IGateKeeperService
            public GateKeeperResponse verifyChallenge(int userId, long challenge, byte[] enrolledPasswordHandle, byte[] providedPassword) throws RemoteException {
                GateKeeperResponse _result;
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.service.gatekeeper.IGateKeeperService");
                    _data.writeInt(userId);
                    _data.writeLong(challenge);
                    _data.writeByteArray(enrolledPasswordHandle);
                    _data.writeByteArray(providedPassword);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 32);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().verifyChallenge(userId, challenge, enrolledPasswordHandle, providedPassword);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = GateKeeperResponse.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.service.gatekeeper.IGateKeeperService
            public long getSecureUserId(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.service.gatekeeper.IGateKeeperService");
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 32);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSecureUserId(userId);
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.service.gatekeeper.IGateKeeperService
            public void clearSecureUserId(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.service.gatekeeper.IGateKeeperService");
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 32);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().clearSecureUserId(userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.service.gatekeeper.IGateKeeperService
            public void reportDeviceSetupComplete() throws RemoteException {
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.service.gatekeeper.IGateKeeperService");
                    boolean _status = this.mRemote.transact(6, _data, _reply, 32);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().reportDeviceSetupComplete();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IGateKeeperService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IGateKeeperService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
