package android.security.identity;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.security.identity.IWritableCredential;
/* loaded from: classes2.dex */
public interface ICredential extends IInterface {
    public static final String DESCRIPTOR = "android.security.identity.ICredential";
    public static final int STATUS_NOT_IN_REQUEST_MESSAGE = 3;
    public static final int STATUS_NOT_REQUESTED = 2;
    public static final int STATUS_NO_ACCESS_CONTROL_PROFILES = 6;
    public static final int STATUS_NO_SUCH_ENTRY = 1;
    public static final int STATUS_OK = 0;
    public static final int STATUS_READER_AUTHENTICATION_FAILED = 5;
    public static final int STATUS_USER_AUTHENTICATION_FAILED = 4;

    byte[] createEphemeralKeyPair() throws RemoteException;

    byte[] deleteCredential() throws RemoteException;

    byte[] deleteWithChallenge(byte[] bArr) throws RemoteException;

    AuthKeyParcel[] getAuthKeysNeedingCertification() throws RemoteException;

    int[] getAuthenticationDataUsageCount() throws RemoteException;

    byte[] getCredentialKeyCertificateChain() throws RemoteException;

    GetEntriesResultParcel getEntries(byte[] bArr, RequestNamespaceParcel[] requestNamespaceParcelArr, byte[] bArr2, byte[] bArr3, boolean z, boolean z2) throws RemoteException;

    byte[] proveOwnership(byte[] bArr) throws RemoteException;

    long selectAuthKey(boolean z, boolean z2) throws RemoteException;

    void setAvailableAuthenticationKeys(int i, int i2) throws RemoteException;

    void setReaderEphemeralPublicKey(byte[] bArr) throws RemoteException;

    void storeStaticAuthenticationData(AuthKeyParcel authKeyParcel, byte[] bArr) throws RemoteException;

    void storeStaticAuthenticationDataWithExpiration(AuthKeyParcel authKeyParcel, long j, byte[] bArr) throws RemoteException;

    IWritableCredential update() throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements ICredential {
        @Override // android.security.identity.ICredential
        public byte[] createEphemeralKeyPair() throws RemoteException {
            return null;
        }

        @Override // android.security.identity.ICredential
        public void setReaderEphemeralPublicKey(byte[] publicKey) throws RemoteException {
        }

        @Override // android.security.identity.ICredential
        public byte[] deleteCredential() throws RemoteException {
            return null;
        }

        @Override // android.security.identity.ICredential
        public byte[] deleteWithChallenge(byte[] challenge) throws RemoteException {
            return null;
        }

        @Override // android.security.identity.ICredential
        public byte[] proveOwnership(byte[] challenge) throws RemoteException {
            return null;
        }

        @Override // android.security.identity.ICredential
        public byte[] getCredentialKeyCertificateChain() throws RemoteException {
            return null;
        }

        @Override // android.security.identity.ICredential
        public long selectAuthKey(boolean allowUsingExhaustedKeys, boolean allowUsingExpiredKeys) throws RemoteException {
            return 0L;
        }

        @Override // android.security.identity.ICredential
        public GetEntriesResultParcel getEntries(byte[] requestMessage, RequestNamespaceParcel[] requestNamespaces, byte[] sessionTranscript, byte[] readerSignature, boolean allowUsingExhaustedKeys, boolean allowUsingExpiredKeys) throws RemoteException {
            return null;
        }

        @Override // android.security.identity.ICredential
        public void setAvailableAuthenticationKeys(int keyCount, int maxUsesPerKey) throws RemoteException {
        }

        @Override // android.security.identity.ICredential
        public AuthKeyParcel[] getAuthKeysNeedingCertification() throws RemoteException {
            return null;
        }

        @Override // android.security.identity.ICredential
        public void storeStaticAuthenticationData(AuthKeyParcel authenticationKey, byte[] staticAuthData) throws RemoteException {
        }

        @Override // android.security.identity.ICredential
        public void storeStaticAuthenticationDataWithExpiration(AuthKeyParcel authenticationKey, long expirationDateMillisSinceEpoch, byte[] staticAuthData) throws RemoteException {
        }

        @Override // android.security.identity.ICredential
        public int[] getAuthenticationDataUsageCount() throws RemoteException {
            return null;
        }

        @Override // android.security.identity.ICredential
        public IWritableCredential update() throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ICredential {
        static final int TRANSACTION_createEphemeralKeyPair = 1;
        static final int TRANSACTION_deleteCredential = 3;
        static final int TRANSACTION_deleteWithChallenge = 4;
        static final int TRANSACTION_getAuthKeysNeedingCertification = 10;
        static final int TRANSACTION_getAuthenticationDataUsageCount = 13;
        static final int TRANSACTION_getCredentialKeyCertificateChain = 6;
        static final int TRANSACTION_getEntries = 8;
        static final int TRANSACTION_proveOwnership = 5;
        static final int TRANSACTION_selectAuthKey = 7;
        static final int TRANSACTION_setAvailableAuthenticationKeys = 9;
        static final int TRANSACTION_setReaderEphemeralPublicKey = 2;
        static final int TRANSACTION_storeStaticAuthenticationData = 11;
        static final int TRANSACTION_storeStaticAuthenticationDataWithExpiration = 12;
        static final int TRANSACTION_update = 14;

        public Stub() {
            attachInterface(this, ICredential.DESCRIPTOR);
        }

        public static ICredential asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ICredential.DESCRIPTOR);
            if (iin == null || !(iin instanceof ICredential)) {
                return new Proxy(obj);
            }
            return (ICredential) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "createEphemeralKeyPair";
                case 2:
                    return "setReaderEphemeralPublicKey";
                case 3:
                    return "deleteCredential";
                case 4:
                    return "deleteWithChallenge";
                case 5:
                    return "proveOwnership";
                case 6:
                    return "getCredentialKeyCertificateChain";
                case 7:
                    return "selectAuthKey";
                case 8:
                    return "getEntries";
                case 9:
                    return "setAvailableAuthenticationKeys";
                case 10:
                    return "getAuthKeysNeedingCertification";
                case 11:
                    return "storeStaticAuthenticationData";
                case 12:
                    return "storeStaticAuthenticationDataWithExpiration";
                case 13:
                    return "getAuthenticationDataUsageCount";
                case 14:
                    return "update";
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
            boolean _arg0;
            boolean _arg4;
            boolean _arg5;
            AuthKeyParcel _arg02;
            AuthKeyParcel _arg03;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ICredential.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg1 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(ICredential.DESCRIPTOR);
                            byte[] _result = createEphemeralKeyPair();
                            reply.writeNoException();
                            reply.writeByteArray(_result);
                            return true;
                        case 2:
                            data.enforceInterface(ICredential.DESCRIPTOR);
                            byte[] _arg04 = data.createByteArray();
                            setReaderEphemeralPublicKey(_arg04);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(ICredential.DESCRIPTOR);
                            byte[] _result2 = deleteCredential();
                            reply.writeNoException();
                            reply.writeByteArray(_result2);
                            return true;
                        case 4:
                            data.enforceInterface(ICredential.DESCRIPTOR);
                            byte[] _arg05 = data.createByteArray();
                            byte[] _result3 = deleteWithChallenge(_arg05);
                            reply.writeNoException();
                            reply.writeByteArray(_result3);
                            return true;
                        case 5:
                            data.enforceInterface(ICredential.DESCRIPTOR);
                            byte[] _arg06 = data.createByteArray();
                            byte[] _result4 = proveOwnership(_arg06);
                            reply.writeNoException();
                            reply.writeByteArray(_result4);
                            return true;
                        case 6:
                            data.enforceInterface(ICredential.DESCRIPTOR);
                            byte[] _result5 = getCredentialKeyCertificateChain();
                            reply.writeNoException();
                            reply.writeByteArray(_result5);
                            return true;
                        case 7:
                            data.enforceInterface(ICredential.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = true;
                            } else {
                                _arg0 = false;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = true;
                            }
                            long _result6 = selectAuthKey(_arg0, _arg1);
                            reply.writeNoException();
                            reply.writeLong(_result6);
                            return true;
                        case 8:
                            data.enforceInterface(ICredential.DESCRIPTOR);
                            byte[] _arg07 = data.createByteArray();
                            RequestNamespaceParcel[] _arg12 = (RequestNamespaceParcel[]) data.createTypedArray(RequestNamespaceParcel.CREATOR);
                            byte[] _arg2 = data.createByteArray();
                            byte[] _arg3 = data.createByteArray();
                            if (data.readInt() != 0) {
                                _arg4 = true;
                            } else {
                                _arg4 = false;
                            }
                            if (data.readInt() != 0) {
                                _arg5 = true;
                            } else {
                                _arg5 = false;
                            }
                            GetEntriesResultParcel _result7 = getEntries(_arg07, _arg12, _arg2, _arg3, _arg4, _arg5);
                            reply.writeNoException();
                            if (_result7 != null) {
                                reply.writeInt(1);
                                _result7.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 9:
                            data.enforceInterface(ICredential.DESCRIPTOR);
                            int _arg08 = data.readInt();
                            int _arg13 = data.readInt();
                            setAvailableAuthenticationKeys(_arg08, _arg13);
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(ICredential.DESCRIPTOR);
                            AuthKeyParcel[] _result8 = getAuthKeysNeedingCertification();
                            reply.writeNoException();
                            reply.writeTypedArray(_result8, 1);
                            return true;
                        case 11:
                            data.enforceInterface(ICredential.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = AuthKeyParcel.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            byte[] _arg14 = data.createByteArray();
                            storeStaticAuthenticationData(_arg02, _arg14);
                            reply.writeNoException();
                            return true;
                        case 12:
                            data.enforceInterface(ICredential.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = AuthKeyParcel.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            long _arg15 = data.readLong();
                            byte[] _arg22 = data.createByteArray();
                            storeStaticAuthenticationDataWithExpiration(_arg03, _arg15, _arg22);
                            reply.writeNoException();
                            return true;
                        case 13:
                            data.enforceInterface(ICredential.DESCRIPTOR);
                            int[] _result9 = getAuthenticationDataUsageCount();
                            reply.writeNoException();
                            reply.writeIntArray(_result9);
                            return true;
                        case 14:
                            data.enforceInterface(ICredential.DESCRIPTOR);
                            IWritableCredential _result10 = update();
                            reply.writeNoException();
                            reply.writeStrongBinder(_result10 != null ? _result10.asBinder() : null);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements ICredential {
            public static ICredential sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ICredential.DESCRIPTOR;
            }

            @Override // android.security.identity.ICredential
            public byte[] createEphemeralKeyPair() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICredential.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createEphemeralKeyPair();
                    }
                    _reply.readException();
                    byte[] _result = _reply.createByteArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.identity.ICredential
            public void setReaderEphemeralPublicKey(byte[] publicKey) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICredential.DESCRIPTOR);
                    _data.writeByteArray(publicKey);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setReaderEphemeralPublicKey(publicKey);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.identity.ICredential
            public byte[] deleteCredential() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICredential.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deleteCredential();
                    }
                    _reply.readException();
                    byte[] _result = _reply.createByteArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.identity.ICredential
            public byte[] deleteWithChallenge(byte[] challenge) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICredential.DESCRIPTOR);
                    _data.writeByteArray(challenge);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deleteWithChallenge(challenge);
                    }
                    _reply.readException();
                    byte[] _result = _reply.createByteArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.identity.ICredential
            public byte[] proveOwnership(byte[] challenge) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICredential.DESCRIPTOR);
                    _data.writeByteArray(challenge);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().proveOwnership(challenge);
                    }
                    _reply.readException();
                    byte[] _result = _reply.createByteArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.identity.ICredential
            public byte[] getCredentialKeyCertificateChain() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICredential.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCredentialKeyCertificateChain();
                    }
                    _reply.readException();
                    byte[] _result = _reply.createByteArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.identity.ICredential
            public long selectAuthKey(boolean allowUsingExhaustedKeys, boolean allowUsingExpiredKeys) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICredential.DESCRIPTOR);
                    int i = 1;
                    _data.writeInt(allowUsingExhaustedKeys ? 1 : 0);
                    if (!allowUsingExpiredKeys) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().selectAuthKey(allowUsingExhaustedKeys, allowUsingExpiredKeys);
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.identity.ICredential
            public GetEntriesResultParcel getEntries(byte[] requestMessage, RequestNamespaceParcel[] requestNamespaces, byte[] sessionTranscript, byte[] readerSignature, boolean allowUsingExhaustedKeys, boolean allowUsingExpiredKeys) throws RemoteException {
                Throwable th;
                int i;
                GetEntriesResultParcel _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICredential.DESCRIPTOR);
                    try {
                        _data.writeByteArray(requestMessage);
                        try {
                            _data.writeTypedArray(requestNamespaces, 0);
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
                    _data.writeByteArray(sessionTranscript);
                    try {
                        _data.writeByteArray(readerSignature);
                        int i2 = 1;
                        if (allowUsingExhaustedKeys) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        _data.writeInt(i);
                        if (!allowUsingExpiredKeys) {
                            i2 = 0;
                        }
                        _data.writeInt(i2);
                        try {
                            boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                            if (_status || Stub.getDefaultImpl() == null) {
                                _reply.readException();
                                if (_reply.readInt() != 0) {
                                    _result = GetEntriesResultParcel.CREATOR.createFromParcel(_reply);
                                } else {
                                    _result = null;
                                }
                                _reply.recycle();
                                _data.recycle();
                                return _result;
                            }
                            GetEntriesResultParcel entries = Stub.getDefaultImpl().getEntries(requestMessage, requestNamespaces, sessionTranscript, readerSignature, allowUsingExhaustedKeys, allowUsingExpiredKeys);
                            _reply.recycle();
                            _data.recycle();
                            return entries;
                        } catch (Throwable th5) {
                            th = th5;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.security.identity.ICredential
            public void setAvailableAuthenticationKeys(int keyCount, int maxUsesPerKey) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICredential.DESCRIPTOR);
                    _data.writeInt(keyCount);
                    _data.writeInt(maxUsesPerKey);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setAvailableAuthenticationKeys(keyCount, maxUsesPerKey);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.identity.ICredential
            public AuthKeyParcel[] getAuthKeysNeedingCertification() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICredential.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAuthKeysNeedingCertification();
                    }
                    _reply.readException();
                    AuthKeyParcel[] _result = (AuthKeyParcel[]) _reply.createTypedArray(AuthKeyParcel.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.identity.ICredential
            public void storeStaticAuthenticationData(AuthKeyParcel authenticationKey, byte[] staticAuthData) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICredential.DESCRIPTOR);
                    if (authenticationKey != null) {
                        _data.writeInt(1);
                        authenticationKey.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeByteArray(staticAuthData);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().storeStaticAuthenticationData(authenticationKey, staticAuthData);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.identity.ICredential
            public void storeStaticAuthenticationDataWithExpiration(AuthKeyParcel authenticationKey, long expirationDateMillisSinceEpoch, byte[] staticAuthData) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICredential.DESCRIPTOR);
                    if (authenticationKey != null) {
                        _data.writeInt(1);
                        authenticationKey.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeLong(expirationDateMillisSinceEpoch);
                    _data.writeByteArray(staticAuthData);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().storeStaticAuthenticationDataWithExpiration(authenticationKey, expirationDateMillisSinceEpoch, staticAuthData);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.identity.ICredential
            public int[] getAuthenticationDataUsageCount() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICredential.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAuthenticationDataUsageCount();
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.identity.ICredential
            public IWritableCredential update() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICredential.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().update();
                    }
                    _reply.readException();
                    IWritableCredential _result = IWritableCredential.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICredential impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ICredential getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
