package android.system.keystore2;

import android.hardware.security.keymint.KeyParameter;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IKeystoreSecurityLevel extends IInterface {
    public static final String DESCRIPTOR = "android$system$keystore2$IKeystoreSecurityLevel".replace('$', '.');
    public static final String HASH = "19e8b65277839bad0ab335c781e3c652324920ce";
    public static final int KEY_FLAG_AUTH_BOUND_WITHOUT_CRYPTOGRAPHIC_LSKF_BINDING = 1;
    public static final int VERSION = 1;

    EphemeralStorageKeyResponse convertStorageKeyToEphemeral(KeyDescriptor keyDescriptor) throws RemoteException;

    CreateOperationResponse createOperation(KeyDescriptor keyDescriptor, KeyParameter[] keyParameterArr, boolean z) throws RemoteException;

    void deleteKey(KeyDescriptor keyDescriptor) throws RemoteException;

    KeyMetadata generateKey(KeyDescriptor keyDescriptor, KeyDescriptor keyDescriptor2, KeyParameter[] keyParameterArr, int i, byte[] bArr) throws RemoteException;

    String getInterfaceHash() throws RemoteException;

    int getInterfaceVersion() throws RemoteException;

    KeyMetadata importKey(KeyDescriptor keyDescriptor, KeyDescriptor keyDescriptor2, KeyParameter[] keyParameterArr, int i, byte[] bArr) throws RemoteException;

    KeyMetadata importWrappedKey(KeyDescriptor keyDescriptor, KeyDescriptor keyDescriptor2, byte[] bArr, KeyParameter[] keyParameterArr, AuthenticatorSpec[] authenticatorSpecArr) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IKeystoreSecurityLevel {
        @Override // android.system.keystore2.IKeystoreSecurityLevel
        public CreateOperationResponse createOperation(KeyDescriptor key, KeyParameter[] operationParameters, boolean forced) throws RemoteException {
            return null;
        }

        @Override // android.system.keystore2.IKeystoreSecurityLevel
        public KeyMetadata generateKey(KeyDescriptor key, KeyDescriptor attestationKey, KeyParameter[] params, int flags, byte[] entropy) throws RemoteException {
            return null;
        }

        @Override // android.system.keystore2.IKeystoreSecurityLevel
        public KeyMetadata importKey(KeyDescriptor key, KeyDescriptor attestationKey, KeyParameter[] params, int flags, byte[] keyData) throws RemoteException {
            return null;
        }

        @Override // android.system.keystore2.IKeystoreSecurityLevel
        public KeyMetadata importWrappedKey(KeyDescriptor key, KeyDescriptor wrappingKey, byte[] maskingKey, KeyParameter[] params, AuthenticatorSpec[] authenticators) throws RemoteException {
            return null;
        }

        @Override // android.system.keystore2.IKeystoreSecurityLevel
        public EphemeralStorageKeyResponse convertStorageKeyToEphemeral(KeyDescriptor storageKey) throws RemoteException {
            return null;
        }

        @Override // android.system.keystore2.IKeystoreSecurityLevel
        public void deleteKey(KeyDescriptor key) throws RemoteException {
        }

        @Override // android.system.keystore2.IKeystoreSecurityLevel
        public int getInterfaceVersion() {
            return 0;
        }

        @Override // android.system.keystore2.IKeystoreSecurityLevel
        public String getInterfaceHash() {
            return "";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IKeystoreSecurityLevel {
        static final int TRANSACTION_convertStorageKeyToEphemeral = 5;
        static final int TRANSACTION_createOperation = 1;
        static final int TRANSACTION_deleteKey = 6;
        static final int TRANSACTION_generateKey = 2;
        static final int TRANSACTION_getInterfaceHash = 16777214;
        static final int TRANSACTION_getInterfaceVersion = 16777215;
        static final int TRANSACTION_importKey = 3;
        static final int TRANSACTION_importWrappedKey = 4;

        public Stub() {
            markVintfStability();
            attachInterface(this, DESCRIPTOR);
        }

        public static IKeystoreSecurityLevel asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IKeystoreSecurityLevel)) {
                return new Proxy(obj);
            }
            return (IKeystoreSecurityLevel) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            KeyDescriptor _arg0;
            boolean _arg2;
            KeyDescriptor _arg02;
            KeyDescriptor _arg1;
            KeyDescriptor _arg03;
            KeyDescriptor _arg12;
            KeyDescriptor _arg04;
            KeyDescriptor _arg13;
            KeyDescriptor _arg05;
            KeyDescriptor _arg06;
            String descriptor = DESCRIPTOR;
            switch (code) {
                case 16777214:
                    data.enforceInterface(descriptor);
                    reply.writeNoException();
                    reply.writeString(getInterfaceHash());
                    return true;
                case 16777215:
                    data.enforceInterface(descriptor);
                    reply.writeNoException();
                    reply.writeInt(getInterfaceVersion());
                    return true;
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(descriptor);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(descriptor);
                            if (data.readInt() != 0) {
                                _arg0 = KeyDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            KeyParameter[] _arg14 = (KeyParameter[]) data.createTypedArray(KeyParameter.CREATOR);
                            if (data.readInt() != 0) {
                                _arg2 = true;
                            } else {
                                _arg2 = false;
                            }
                            CreateOperationResponse _result = createOperation(_arg0, _arg14, _arg2);
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 2:
                            data.enforceInterface(descriptor);
                            if (data.readInt() != 0) {
                                _arg02 = KeyDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = KeyDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            KeyParameter[] _arg22 = (KeyParameter[]) data.createTypedArray(KeyParameter.CREATOR);
                            int _arg3 = data.readInt();
                            byte[] _arg4 = data.createByteArray();
                            KeyMetadata _result2 = generateKey(_arg02, _arg1, _arg22, _arg3, _arg4);
                            reply.writeNoException();
                            if (_result2 != null) {
                                reply.writeInt(1);
                                _result2.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 3:
                            data.enforceInterface(descriptor);
                            if (data.readInt() != 0) {
                                _arg03 = KeyDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg12 = KeyDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            KeyParameter[] _arg23 = (KeyParameter[]) data.createTypedArray(KeyParameter.CREATOR);
                            int _arg32 = data.readInt();
                            byte[] _arg42 = data.createByteArray();
                            KeyMetadata _result3 = importKey(_arg03, _arg12, _arg23, _arg32, _arg42);
                            reply.writeNoException();
                            if (_result3 != null) {
                                reply.writeInt(1);
                                _result3.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 4:
                            data.enforceInterface(descriptor);
                            if (data.readInt() != 0) {
                                _arg04 = KeyDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg13 = KeyDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            byte[] _arg24 = data.createByteArray();
                            KeyParameter[] _arg33 = (KeyParameter[]) data.createTypedArray(KeyParameter.CREATOR);
                            AuthenticatorSpec[] _arg43 = (AuthenticatorSpec[]) data.createTypedArray(AuthenticatorSpec.CREATOR);
                            KeyMetadata _result4 = importWrappedKey(_arg04, _arg13, _arg24, _arg33, _arg43);
                            reply.writeNoException();
                            if (_result4 != null) {
                                reply.writeInt(1);
                                _result4.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 5:
                            data.enforceInterface(descriptor);
                            if (data.readInt() != 0) {
                                _arg05 = KeyDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            EphemeralStorageKeyResponse _result5 = convertStorageKeyToEphemeral(_arg05);
                            reply.writeNoException();
                            if (_result5 != null) {
                                reply.writeInt(1);
                                _result5.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 6:
                            data.enforceInterface(descriptor);
                            if (data.readInt() != 0) {
                                _arg06 = KeyDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg06 = null;
                            }
                            deleteKey(_arg06);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IKeystoreSecurityLevel {
            public static IKeystoreSecurityLevel sDefaultImpl;
            private IBinder mRemote;
            private int mCachedVersion = -1;
            private String mCachedHash = "-1";

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

            @Override // android.system.keystore2.IKeystoreSecurityLevel
            public CreateOperationResponse createOperation(KeyDescriptor key, KeyParameter[] operationParameters, boolean forced) throws RemoteException {
                CreateOperationResponse _result;
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    int i = 0;
                    if (key != null) {
                        _data.writeInt(1);
                        key.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeTypedArray(operationParameters, 0);
                    if (forced) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 32);
                    if (_status) {
                        _reply.readException();
                        if (_reply.readInt() != 0) {
                            _result = CreateOperationResponse.CREATOR.createFromParcel(_reply);
                        } else {
                            _result = null;
                        }
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createOperation(key, operationParameters, forced);
                    } else {
                        throw new RemoteException("Method createOperation is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.system.keystore2.IKeystoreSecurityLevel
            public KeyMetadata generateKey(KeyDescriptor key, KeyDescriptor attestationKey, KeyParameter[] params, int flags, byte[] entropy) throws RemoteException {
                KeyMetadata _result;
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if (key != null) {
                        _data.writeInt(1);
                        key.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attestationKey != null) {
                        _data.writeInt(1);
                        attestationKey.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeTypedArray(params, 0);
                    _data.writeInt(flags);
                    _data.writeByteArray(entropy);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 32);
                    if (_status) {
                        _reply.readException();
                        if (_reply.readInt() != 0) {
                            _result = KeyMetadata.CREATOR.createFromParcel(_reply);
                        } else {
                            _result = null;
                        }
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().generateKey(key, attestationKey, params, flags, entropy);
                    } else {
                        throw new RemoteException("Method generateKey is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.system.keystore2.IKeystoreSecurityLevel
            public KeyMetadata importKey(KeyDescriptor key, KeyDescriptor attestationKey, KeyParameter[] params, int flags, byte[] keyData) throws RemoteException {
                KeyMetadata _result;
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if (key != null) {
                        _data.writeInt(1);
                        key.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attestationKey != null) {
                        _data.writeInt(1);
                        attestationKey.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeTypedArray(params, 0);
                    _data.writeInt(flags);
                    _data.writeByteArray(keyData);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 32);
                    if (_status) {
                        _reply.readException();
                        if (_reply.readInt() != 0) {
                            _result = KeyMetadata.CREATOR.createFromParcel(_reply);
                        } else {
                            _result = null;
                        }
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().importKey(key, attestationKey, params, flags, keyData);
                    } else {
                        throw new RemoteException("Method importKey is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.system.keystore2.IKeystoreSecurityLevel
            public KeyMetadata importWrappedKey(KeyDescriptor key, KeyDescriptor wrappingKey, byte[] maskingKey, KeyParameter[] params, AuthenticatorSpec[] authenticators) throws RemoteException {
                KeyMetadata _result;
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if (key != null) {
                        _data.writeInt(1);
                        key.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (wrappingKey != null) {
                        _data.writeInt(1);
                        wrappingKey.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeByteArray(maskingKey);
                    _data.writeTypedArray(params, 0);
                    _data.writeTypedArray(authenticators, 0);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 32);
                    if (_status) {
                        _reply.readException();
                        if (_reply.readInt() != 0) {
                            _result = KeyMetadata.CREATOR.createFromParcel(_reply);
                        } else {
                            _result = null;
                        }
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().importWrappedKey(key, wrappingKey, maskingKey, params, authenticators);
                    } else {
                        throw new RemoteException("Method importWrappedKey is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.system.keystore2.IKeystoreSecurityLevel
            public EphemeralStorageKeyResponse convertStorageKeyToEphemeral(KeyDescriptor storageKey) throws RemoteException {
                EphemeralStorageKeyResponse _result;
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if (storageKey != null) {
                        _data.writeInt(1);
                        storageKey.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, _reply, 32);
                    if (_status) {
                        _reply.readException();
                        if (_reply.readInt() != 0) {
                            _result = EphemeralStorageKeyResponse.CREATOR.createFromParcel(_reply);
                        } else {
                            _result = null;
                        }
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().convertStorageKeyToEphemeral(storageKey);
                    } else {
                        throw new RemoteException("Method convertStorageKeyToEphemeral is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.system.keystore2.IKeystoreSecurityLevel
            public void deleteKey(KeyDescriptor key) throws RemoteException {
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if (key != null) {
                        _data.writeInt(1);
                        key.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, _reply, 32);
                    if (_status) {
                        _reply.readException();
                    } else if (Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteKey(key);
                    } else {
                        throw new RemoteException("Method deleteKey is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.system.keystore2.IKeystoreSecurityLevel
            public int getInterfaceVersion() throws RemoteException {
                if (this.mCachedVersion == -1) {
                    Parcel data = Parcel.obtain();
                    Parcel reply = Parcel.obtain();
                    try {
                        data.writeInterfaceToken(DESCRIPTOR);
                        boolean _status = this.mRemote.transact(16777215, data, reply, 0);
                        if (!_status && Stub.getDefaultImpl() != null) {
                            return Stub.getDefaultImpl().getInterfaceVersion();
                        }
                        reply.readException();
                        this.mCachedVersion = reply.readInt();
                    } finally {
                        reply.recycle();
                        data.recycle();
                    }
                }
                return this.mCachedVersion;
            }

            @Override // android.system.keystore2.IKeystoreSecurityLevel
            public synchronized String getInterfaceHash() throws RemoteException {
                if ("-1".equals(this.mCachedHash)) {
                    Parcel data = Parcel.obtain();
                    Parcel reply = Parcel.obtain();
                    data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(16777214, data, reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        reply.readException();
                        this.mCachedHash = reply.readString();
                        reply.recycle();
                        data.recycle();
                    } else {
                        String interfaceHash = Stub.getDefaultImpl().getInterfaceHash();
                        reply.recycle();
                        data.recycle();
                        return interfaceHash;
                    }
                }
                return this.mCachedHash;
            }
        }

        public static boolean setDefaultImpl(IKeystoreSecurityLevel impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IKeystoreSecurityLevel getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
