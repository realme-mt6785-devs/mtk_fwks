package android.security.authorization;

import android.hardware.security.keymint.HardwareAuthToken;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IKeystoreAuthorization extends IInterface {
    public static final String DESCRIPTOR = "android$security$authorization$IKeystoreAuthorization".replace('$', '.');

    void addAuthToken(HardwareAuthToken hardwareAuthToken) throws RemoteException;

    AuthorizationTokens getAuthTokensForCredStore(long j, long j2, long j3) throws RemoteException;

    HardwareAuthToken getGateKeeperAuthToken() throws RemoteException;

    void onLockScreenEvent(int i, int i2, byte[] bArr, long[] jArr) throws RemoteException;

    void rename_keystore_entry(int i, int i2, String str) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IKeystoreAuthorization {
        @Override // android.security.authorization.IKeystoreAuthorization
        public void addAuthToken(HardwareAuthToken authToken) throws RemoteException {
        }

        @Override // android.security.authorization.IKeystoreAuthorization
        public void onLockScreenEvent(int lockScreenEvent, int userId, byte[] password, long[] unlockingSids) throws RemoteException {
        }

        @Override // android.security.authorization.IKeystoreAuthorization
        public AuthorizationTokens getAuthTokensForCredStore(long challenge, long secureUserId, long authTokenMaxAgeMillis) throws RemoteException {
            return null;
        }

        @Override // android.security.authorization.IKeystoreAuthorization
        public HardwareAuthToken getGateKeeperAuthToken() throws RemoteException {
            return null;
        }

        @Override // android.security.authorization.IKeystoreAuthorization
        public void rename_keystore_entry(int old_uid, int new_uid, String alias) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IKeystoreAuthorization {
        static final int TRANSACTION_addAuthToken = 1;
        static final int TRANSACTION_getAuthTokensForCredStore = 3;
        static final int TRANSACTION_getGateKeeperAuthToken = 4;
        static final int TRANSACTION_onLockScreenEvent = 2;
        static final int TRANSACTION_rename_keystore_entry = 5;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IKeystoreAuthorization asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IKeystoreAuthorization)) {
                return new Proxy(obj);
            }
            return (IKeystoreAuthorization) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            HardwareAuthToken _arg0;
            String descriptor = DESCRIPTOR;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(descriptor);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(descriptor);
                            if (data.readInt() != 0) {
                                _arg0 = HardwareAuthToken.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            addAuthToken(_arg0);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(descriptor);
                            int _arg02 = data.readInt();
                            int _arg1 = data.readInt();
                            byte[] _arg2 = data.createByteArray();
                            long[] _arg3 = data.createLongArray();
                            onLockScreenEvent(_arg02, _arg1, _arg2, _arg3);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(descriptor);
                            long _arg03 = data.readLong();
                            long _arg12 = data.readLong();
                            long _arg22 = data.readLong();
                            AuthorizationTokens _result = getAuthTokensForCredStore(_arg03, _arg12, _arg22);
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 4:
                            data.enforceInterface(descriptor);
                            HardwareAuthToken _result2 = getGateKeeperAuthToken();
                            reply.writeNoException();
                            if (_result2 != null) {
                                reply.writeInt(1);
                                _result2.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 5:
                            data.enforceInterface(descriptor);
                            int _arg04 = data.readInt();
                            int _arg13 = data.readInt();
                            String _arg23 = data.readString();
                            rename_keystore_entry(_arg04, _arg13, _arg23);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IKeystoreAuthorization {
            public static IKeystoreAuthorization sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            @Override // android.security.authorization.IKeystoreAuthorization
            public void addAuthToken(HardwareAuthToken authToken) throws RemoteException {
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if (authToken != null) {
                        _data.writeInt(1);
                        authToken.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, _reply, 32);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addAuthToken(authToken);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.authorization.IKeystoreAuthorization
            public void onLockScreenEvent(int lockScreenEvent, int userId, byte[] password, long[] unlockingSids) throws RemoteException {
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(lockScreenEvent);
                    _data.writeInt(userId);
                    _data.writeByteArray(password);
                    _data.writeLongArray(unlockingSids);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 32);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onLockScreenEvent(lockScreenEvent, userId, password, unlockingSids);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.authorization.IKeystoreAuthorization
            public AuthorizationTokens getAuthTokensForCredStore(long challenge, long secureUserId, long authTokenMaxAgeMillis) throws RemoteException {
                Throwable th;
                AuthorizationTokens _result;
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    try {
                        _data.writeLong(challenge);
                        try {
                            _data.writeLong(secureUserId);
                            try {
                                _data.writeLong(authTokenMaxAgeMillis);
                                boolean _status = this.mRemote.transact(3, _data, _reply, 32);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _reply.readException();
                                    if (_reply.readInt() != 0) {
                                        _result = AuthorizationTokens.CREATOR.createFromParcel(_reply);
                                    } else {
                                        _result = null;
                                    }
                                    _reply.recycle();
                                    _data.recycle();
                                    return _result;
                                }
                                AuthorizationTokens authTokensForCredStore = Stub.getDefaultImpl().getAuthTokensForCredStore(challenge, secureUserId, authTokenMaxAgeMillis);
                                _reply.recycle();
                                _data.recycle();
                                return authTokensForCredStore;
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
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                }
            }

            @Override // android.security.authorization.IKeystoreAuthorization
            public HardwareAuthToken getGateKeeperAuthToken() throws RemoteException {
                HardwareAuthToken _result;
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 32);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGateKeeperAuthToken();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = HardwareAuthToken.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.authorization.IKeystoreAuthorization
            public void rename_keystore_entry(int old_uid, int new_uid, String alias) throws RemoteException {
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(old_uid);
                    _data.writeInt(new_uid);
                    _data.writeString(alias);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 32);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().rename_keystore_entry(old_uid, new_uid, alias);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IKeystoreAuthorization impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IKeystoreAuthorization getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
