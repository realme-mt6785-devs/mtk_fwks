package android.hardware.security.keymint;

import android.hardware.security.secureclock.TimeStampToken;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IKeyMintDevice extends IInterface {
    public static final int AUTH_TOKEN_MAC_LENGTH = 32;
    public static final String DESCRIPTOR = "android$hardware$security$keymint$IKeyMintDevice".replace('$', '.');
    public static final String HASH = "976674616001f714f4a4df49ee45f548de828524";
    public static final int VERSION = 1;

    void addRngEntropy(byte[] bArr) throws RemoteException;

    BeginResult begin(int i, byte[] bArr, KeyParameter[] keyParameterArr, HardwareAuthToken hardwareAuthToken) throws RemoteException;

    byte[] convertStorageKeyToEphemeral(byte[] bArr) throws RemoteException;

    void deleteAllKeys() throws RemoteException;

    void deleteKey(byte[] bArr) throws RemoteException;

    void destroyAttestationIds() throws RemoteException;

    void deviceLocked(boolean z, TimeStampToken timeStampToken) throws RemoteException;

    void earlyBootEnded() throws RemoteException;

    KeyCreationResult generateKey(KeyParameter[] keyParameterArr, AttestationKey attestationKey) throws RemoteException;

    KeyMintHardwareInfo getHardwareInfo() throws RemoteException;

    String getInterfaceHash() throws RemoteException;

    int getInterfaceVersion() throws RemoteException;

    KeyCharacteristics[] getKeyCharacteristics(byte[] bArr, byte[] bArr2, byte[] bArr3) throws RemoteException;

    KeyCreationResult importKey(KeyParameter[] keyParameterArr, int i, byte[] bArr, AttestationKey attestationKey) throws RemoteException;

    KeyCreationResult importWrappedKey(byte[] bArr, byte[] bArr2, byte[] bArr3, KeyParameter[] keyParameterArr, long j, long j2) throws RemoteException;

    byte[] upgradeKey(byte[] bArr, KeyParameter[] keyParameterArr) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IKeyMintDevice {
        @Override // android.hardware.security.keymint.IKeyMintDevice
        public KeyMintHardwareInfo getHardwareInfo() throws RemoteException {
            return null;
        }

        @Override // android.hardware.security.keymint.IKeyMintDevice
        public void addRngEntropy(byte[] data) throws RemoteException {
        }

        @Override // android.hardware.security.keymint.IKeyMintDevice
        public KeyCreationResult generateKey(KeyParameter[] keyParams, AttestationKey attestationKey) throws RemoteException {
            return null;
        }

        @Override // android.hardware.security.keymint.IKeyMintDevice
        public KeyCreationResult importKey(KeyParameter[] keyParams, int keyFormat, byte[] keyData, AttestationKey attestationKey) throws RemoteException {
            return null;
        }

        @Override // android.hardware.security.keymint.IKeyMintDevice
        public KeyCreationResult importWrappedKey(byte[] wrappedKeyData, byte[] wrappingKeyBlob, byte[] maskingKey, KeyParameter[] unwrappingParams, long passwordSid, long biometricSid) throws RemoteException {
            return null;
        }

        @Override // android.hardware.security.keymint.IKeyMintDevice
        public byte[] upgradeKey(byte[] keyBlobToUpgrade, KeyParameter[] upgradeParams) throws RemoteException {
            return null;
        }

        @Override // android.hardware.security.keymint.IKeyMintDevice
        public void deleteKey(byte[] keyBlob) throws RemoteException {
        }

        @Override // android.hardware.security.keymint.IKeyMintDevice
        public void deleteAllKeys() throws RemoteException {
        }

        @Override // android.hardware.security.keymint.IKeyMintDevice
        public void destroyAttestationIds() throws RemoteException {
        }

        @Override // android.hardware.security.keymint.IKeyMintDevice
        public BeginResult begin(int purpose, byte[] keyBlob, KeyParameter[] params, HardwareAuthToken authToken) throws RemoteException {
            return null;
        }

        @Override // android.hardware.security.keymint.IKeyMintDevice
        public void deviceLocked(boolean passwordOnly, TimeStampToken timestampToken) throws RemoteException {
        }

        @Override // android.hardware.security.keymint.IKeyMintDevice
        public void earlyBootEnded() throws RemoteException {
        }

        @Override // android.hardware.security.keymint.IKeyMintDevice
        public byte[] convertStorageKeyToEphemeral(byte[] storageKeyBlob) throws RemoteException {
            return null;
        }

        @Override // android.hardware.security.keymint.IKeyMintDevice
        public KeyCharacteristics[] getKeyCharacteristics(byte[] keyBlob, byte[] appId, byte[] appData) throws RemoteException {
            return null;
        }

        @Override // android.hardware.security.keymint.IKeyMintDevice
        public int getInterfaceVersion() {
            return 0;
        }

        @Override // android.hardware.security.keymint.IKeyMintDevice
        public String getInterfaceHash() {
            return "";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IKeyMintDevice {
        static final int TRANSACTION_addRngEntropy = 2;
        static final int TRANSACTION_begin = 10;
        static final int TRANSACTION_convertStorageKeyToEphemeral = 13;
        static final int TRANSACTION_deleteAllKeys = 8;
        static final int TRANSACTION_deleteKey = 7;
        static final int TRANSACTION_destroyAttestationIds = 9;
        static final int TRANSACTION_deviceLocked = 11;
        static final int TRANSACTION_earlyBootEnded = 12;
        static final int TRANSACTION_generateKey = 3;
        static final int TRANSACTION_getHardwareInfo = 1;
        static final int TRANSACTION_getInterfaceHash = 16777214;
        static final int TRANSACTION_getInterfaceVersion = 16777215;
        static final int TRANSACTION_getKeyCharacteristics = 14;
        static final int TRANSACTION_importKey = 4;
        static final int TRANSACTION_importWrappedKey = 5;
        static final int TRANSACTION_upgradeKey = 6;

        public Stub() {
            markVintfStability();
            attachInterface(this, DESCRIPTOR);
        }

        public static IKeyMintDevice asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IKeyMintDevice)) {
                return new Proxy(obj);
            }
            return (IKeyMintDevice) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            AttestationKey _arg1;
            AttestationKey _arg3;
            HardwareAuthToken _arg32;
            TimeStampToken _arg12;
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
                    boolean _arg0 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(descriptor);
                            KeyMintHardwareInfo _result = getHardwareInfo();
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
                            byte[] _arg02 = data.createByteArray();
                            addRngEntropy(_arg02);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(descriptor);
                            KeyParameter[] _arg03 = (KeyParameter[]) data.createTypedArray(KeyParameter.CREATOR);
                            if (data.readInt() != 0) {
                                _arg1 = AttestationKey.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            KeyCreationResult _result2 = generateKey(_arg03, _arg1);
                            reply.writeNoException();
                            if (_result2 != null) {
                                reply.writeInt(1);
                                _result2.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 4:
                            data.enforceInterface(descriptor);
                            KeyParameter[] _arg04 = (KeyParameter[]) data.createTypedArray(KeyParameter.CREATOR);
                            int _arg13 = data.readInt();
                            byte[] _arg2 = data.createByteArray();
                            if (data.readInt() != 0) {
                                _arg3 = AttestationKey.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            KeyCreationResult _result3 = importKey(_arg04, _arg13, _arg2, _arg3);
                            reply.writeNoException();
                            if (_result3 != null) {
                                reply.writeInt(1);
                                _result3.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 5:
                            data.enforceInterface(descriptor);
                            byte[] _arg05 = data.createByteArray();
                            byte[] _arg14 = data.createByteArray();
                            byte[] _arg22 = data.createByteArray();
                            KeyParameter[] _arg33 = (KeyParameter[]) data.createTypedArray(KeyParameter.CREATOR);
                            long _arg4 = data.readLong();
                            long _arg5 = data.readLong();
                            KeyCreationResult _result4 = importWrappedKey(_arg05, _arg14, _arg22, _arg33, _arg4, _arg5);
                            reply.writeNoException();
                            if (_result4 != null) {
                                reply.writeInt(1);
                                _result4.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 6:
                            data.enforceInterface(descriptor);
                            byte[] _arg06 = data.createByteArray();
                            KeyParameter[] _arg15 = (KeyParameter[]) data.createTypedArray(KeyParameter.CREATOR);
                            byte[] _result5 = upgradeKey(_arg06, _arg15);
                            reply.writeNoException();
                            reply.writeByteArray(_result5);
                            return true;
                        case 7:
                            data.enforceInterface(descriptor);
                            byte[] _arg07 = data.createByteArray();
                            deleteKey(_arg07);
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(descriptor);
                            deleteAllKeys();
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(descriptor);
                            destroyAttestationIds();
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(descriptor);
                            int _arg08 = data.readInt();
                            byte[] _arg16 = data.createByteArray();
                            KeyParameter[] _arg23 = (KeyParameter[]) data.createTypedArray(KeyParameter.CREATOR);
                            if (data.readInt() != 0) {
                                _arg32 = HardwareAuthToken.CREATOR.createFromParcel(data);
                            } else {
                                _arg32 = null;
                            }
                            BeginResult _result6 = begin(_arg08, _arg16, _arg23, _arg32);
                            reply.writeNoException();
                            if (_result6 != null) {
                                reply.writeInt(1);
                                _result6.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 11:
                            data.enforceInterface(descriptor);
                            if (data.readInt() != 0) {
                                _arg0 = true;
                            }
                            if (data.readInt() != 0) {
                                _arg12 = TimeStampToken.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            deviceLocked(_arg0, _arg12);
                            reply.writeNoException();
                            return true;
                        case 12:
                            data.enforceInterface(descriptor);
                            earlyBootEnded();
                            reply.writeNoException();
                            return true;
                        case 13:
                            data.enforceInterface(descriptor);
                            byte[] _arg09 = data.createByteArray();
                            byte[] _result7 = convertStorageKeyToEphemeral(_arg09);
                            reply.writeNoException();
                            reply.writeByteArray(_result7);
                            return true;
                        case 14:
                            data.enforceInterface(descriptor);
                            byte[] _arg010 = data.createByteArray();
                            byte[] _arg17 = data.createByteArray();
                            byte[] _arg24 = data.createByteArray();
                            KeyCharacteristics[] _result8 = getKeyCharacteristics(_arg010, _arg17, _arg24);
                            reply.writeNoException();
                            reply.writeTypedArray(_result8, 1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IKeyMintDevice {
            public static IKeyMintDevice sDefaultImpl;
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

            @Override // android.hardware.security.keymint.IKeyMintDevice
            public KeyMintHardwareInfo getHardwareInfo() throws RemoteException {
                KeyMintHardwareInfo _result;
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 32);
                    if (_status) {
                        _reply.readException();
                        if (_reply.readInt() != 0) {
                            _result = KeyMintHardwareInfo.CREATOR.createFromParcel(_reply);
                        } else {
                            _result = null;
                        }
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHardwareInfo();
                    } else {
                        throw new RemoteException("Method getHardwareInfo is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.security.keymint.IKeyMintDevice
            public void addRngEntropy(byte[] data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeByteArray(data);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 32);
                    if (_status) {
                        _reply.readException();
                    } else if (Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addRngEntropy(data);
                    } else {
                        throw new RemoteException("Method addRngEntropy is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.security.keymint.IKeyMintDevice
            public KeyCreationResult generateKey(KeyParameter[] keyParams, AttestationKey attestationKey) throws RemoteException {
                KeyCreationResult _result;
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeTypedArray(keyParams, 0);
                    if (attestationKey != null) {
                        _data.writeInt(1);
                        attestationKey.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, _reply, 32);
                    if (_status) {
                        _reply.readException();
                        if (_reply.readInt() != 0) {
                            _result = KeyCreationResult.CREATOR.createFromParcel(_reply);
                        } else {
                            _result = null;
                        }
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().generateKey(keyParams, attestationKey);
                    } else {
                        throw new RemoteException("Method generateKey is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.security.keymint.IKeyMintDevice
            public KeyCreationResult importKey(KeyParameter[] keyParams, int keyFormat, byte[] keyData, AttestationKey attestationKey) throws RemoteException {
                KeyCreationResult _result;
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeTypedArray(keyParams, 0);
                    _data.writeInt(keyFormat);
                    _data.writeByteArray(keyData);
                    if (attestationKey != null) {
                        _data.writeInt(1);
                        attestationKey.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, _reply, 32);
                    if (_status) {
                        _reply.readException();
                        if (_reply.readInt() != 0) {
                            _result = KeyCreationResult.CREATOR.createFromParcel(_reply);
                        } else {
                            _result = null;
                        }
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().importKey(keyParams, keyFormat, keyData, attestationKey);
                    } else {
                        throw new RemoteException("Method importKey is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.security.keymint.IKeyMintDevice
            public KeyCreationResult importWrappedKey(byte[] wrappedKeyData, byte[] wrappingKeyBlob, byte[] maskingKey, KeyParameter[] unwrappingParams, long passwordSid, long biometricSid) throws RemoteException {
                Throwable th;
                KeyCreationResult _result;
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    try {
                        _data.writeByteArray(wrappedKeyData);
                        try {
                            _data.writeByteArray(wrappingKeyBlob);
                            try {
                                _data.writeByteArray(maskingKey);
                                try {
                                    _data.writeTypedArray(unwrappingParams, 0);
                                    _data.writeLong(passwordSid);
                                    _data.writeLong(biometricSid);
                                    boolean _status = this.mRemote.transact(5, _data, _reply, 32);
                                    if (_status) {
                                        _reply.readException();
                                        if (_reply.readInt() != 0) {
                                            _result = KeyCreationResult.CREATOR.createFromParcel(_reply);
                                        } else {
                                            _result = null;
                                        }
                                        _reply.recycle();
                                        _data.recycle();
                                        return _result;
                                    } else if (Stub.getDefaultImpl() != null) {
                                        KeyCreationResult importWrappedKey = Stub.getDefaultImpl().importWrappedKey(wrappedKeyData, wrappingKeyBlob, maskingKey, unwrappingParams, passwordSid, biometricSid);
                                        _reply.recycle();
                                        _data.recycle();
                                        return importWrappedKey;
                                    } else {
                                        throw new RemoteException("Method importWrappedKey is unimplemented.");
                                    }
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
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                }
            }

            @Override // android.hardware.security.keymint.IKeyMintDevice
            public byte[] upgradeKey(byte[] keyBlobToUpgrade, KeyParameter[] upgradeParams) throws RemoteException {
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeByteArray(keyBlobToUpgrade);
                    _data.writeTypedArray(upgradeParams, 0);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 32);
                    if (_status) {
                        _reply.readException();
                        byte[] _result = _reply.createByteArray();
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().upgradeKey(keyBlobToUpgrade, upgradeParams);
                    } else {
                        throw new RemoteException("Method upgradeKey is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.security.keymint.IKeyMintDevice
            public void deleteKey(byte[] keyBlob) throws RemoteException {
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeByteArray(keyBlob);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 32);
                    if (_status) {
                        _reply.readException();
                    } else if (Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteKey(keyBlob);
                    } else {
                        throw new RemoteException("Method deleteKey is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.security.keymint.IKeyMintDevice
            public void deleteAllKeys() throws RemoteException {
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 32);
                    if (_status) {
                        _reply.readException();
                    } else if (Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteAllKeys();
                    } else {
                        throw new RemoteException("Method deleteAllKeys is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.security.keymint.IKeyMintDevice
            public void destroyAttestationIds() throws RemoteException {
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 32);
                    if (_status) {
                        _reply.readException();
                    } else if (Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().destroyAttestationIds();
                    } else {
                        throw new RemoteException("Method destroyAttestationIds is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.security.keymint.IKeyMintDevice
            public BeginResult begin(int purpose, byte[] keyBlob, KeyParameter[] params, HardwareAuthToken authToken) throws RemoteException {
                BeginResult _result;
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(purpose);
                    _data.writeByteArray(keyBlob);
                    _data.writeTypedArray(params, 0);
                    if (authToken != null) {
                        _data.writeInt(1);
                        authToken.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(10, _data, _reply, 32);
                    if (_status) {
                        _reply.readException();
                        if (_reply.readInt() != 0) {
                            _result = BeginResult.CREATOR.createFromParcel(_reply);
                        } else {
                            _result = null;
                        }
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().begin(purpose, keyBlob, params, authToken);
                    } else {
                        throw new RemoteException("Method begin is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.security.keymint.IKeyMintDevice
            public void deviceLocked(boolean passwordOnly, TimeStampToken timestampToken) throws RemoteException {
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(passwordOnly ? 1 : 0);
                    if (timestampToken != null) {
                        _data.writeInt(1);
                        timestampToken.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(11, _data, _reply, 32);
                    if (_status) {
                        _reply.readException();
                    } else if (Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deviceLocked(passwordOnly, timestampToken);
                    } else {
                        throw new RemoteException("Method deviceLocked is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.security.keymint.IKeyMintDevice
            public void earlyBootEnded() throws RemoteException {
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 32);
                    if (_status) {
                        _reply.readException();
                    } else if (Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().earlyBootEnded();
                    } else {
                        throw new RemoteException("Method earlyBootEnded is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.security.keymint.IKeyMintDevice
            public byte[] convertStorageKeyToEphemeral(byte[] storageKeyBlob) throws RemoteException {
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeByteArray(storageKeyBlob);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 32);
                    if (_status) {
                        _reply.readException();
                        byte[] _result = _reply.createByteArray();
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().convertStorageKeyToEphemeral(storageKeyBlob);
                    } else {
                        throw new RemoteException("Method convertStorageKeyToEphemeral is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.security.keymint.IKeyMintDevice
            public KeyCharacteristics[] getKeyCharacteristics(byte[] keyBlob, byte[] appId, byte[] appData) throws RemoteException {
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeByteArray(keyBlob);
                    _data.writeByteArray(appId);
                    _data.writeByteArray(appData);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 32);
                    if (_status) {
                        _reply.readException();
                        KeyCharacteristics[] _result = (KeyCharacteristics[]) _reply.createTypedArray(KeyCharacteristics.CREATOR);
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getKeyCharacteristics(keyBlob, appId, appData);
                    } else {
                        throw new RemoteException("Method getKeyCharacteristics is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.security.keymint.IKeyMintDevice
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

            @Override // android.hardware.security.keymint.IKeyMintDevice
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

        public static boolean setDefaultImpl(IKeyMintDevice impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IKeyMintDevice getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
