package android.hardware.security.keymint;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IRemotelyProvisionedComponent extends IInterface {
    public static final String DESCRIPTOR = "android$hardware$security$keymint$IRemotelyProvisionedComponent".replace('$', '.');
    public static final String HASH = "976674616001f714f4a4df49ee45f548de828524";
    public static final int STATUS_FAILED = 1;
    public static final int STATUS_INVALID_EEK = 5;
    public static final int STATUS_INVALID_MAC = 2;
    public static final int STATUS_PRODUCTION_KEY_IN_TEST_REQUEST = 3;
    public static final int STATUS_TEST_KEY_IN_PRODUCTION_REQUEST = 4;
    public static final int VERSION = 1;

    byte[] generateCertificateRequest(boolean z, MacedPublicKey[] macedPublicKeyArr, byte[] bArr, byte[] bArr2, DeviceInfo deviceInfo, ProtectedData protectedData) throws RemoteException;

    byte[] generateEcdsaP256KeyPair(boolean z, MacedPublicKey macedPublicKey) throws RemoteException;

    RpcHardwareInfo getHardwareInfo() throws RemoteException;

    String getInterfaceHash() throws RemoteException;

    int getInterfaceVersion() throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IRemotelyProvisionedComponent {
        @Override // android.hardware.security.keymint.IRemotelyProvisionedComponent
        public RpcHardwareInfo getHardwareInfo() throws RemoteException {
            return null;
        }

        @Override // android.hardware.security.keymint.IRemotelyProvisionedComponent
        public byte[] generateEcdsaP256KeyPair(boolean testMode, MacedPublicKey macedPublicKey) throws RemoteException {
            return null;
        }

        @Override // android.hardware.security.keymint.IRemotelyProvisionedComponent
        public byte[] generateCertificateRequest(boolean testMode, MacedPublicKey[] keysToSign, byte[] endpointEncryptionCertChain, byte[] challenge, DeviceInfo deviceInfo, ProtectedData protectedData) throws RemoteException {
            return null;
        }

        @Override // android.hardware.security.keymint.IRemotelyProvisionedComponent
        public int getInterfaceVersion() {
            return 0;
        }

        @Override // android.hardware.security.keymint.IRemotelyProvisionedComponent
        public String getInterfaceHash() {
            return "";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IRemotelyProvisionedComponent {
        static final int TRANSACTION_generateCertificateRequest = 3;
        static final int TRANSACTION_generateEcdsaP256KeyPair = 2;
        static final int TRANSACTION_getHardwareInfo = 1;
        static final int TRANSACTION_getInterfaceHash = 16777214;
        static final int TRANSACTION_getInterfaceVersion = 16777215;

        public Stub() {
            markVintfStability();
            attachInterface(this, DESCRIPTOR);
        }

        public static IRemotelyProvisionedComponent asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IRemotelyProvisionedComponent)) {
                return new Proxy(obj);
            }
            return (IRemotelyProvisionedComponent) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            boolean _arg0;
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
                    boolean _arg02 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(descriptor);
                            RpcHardwareInfo _result = getHardwareInfo();
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
                                _arg02 = true;
                            }
                            MacedPublicKey _arg1 = new MacedPublicKey();
                            byte[] _result2 = generateEcdsaP256KeyPair(_arg02, _arg1);
                            reply.writeNoException();
                            reply.writeByteArray(_result2);
                            reply.writeInt(1);
                            _arg1.writeToParcel(reply, 1);
                            return true;
                        case 3:
                            data.enforceInterface(descriptor);
                            if (data.readInt() != 0) {
                                _arg0 = true;
                            } else {
                                _arg0 = false;
                            }
                            byte[] _arg2 = data.createByteArray();
                            byte[] _arg3 = data.createByteArray();
                            DeviceInfo _arg4 = new DeviceInfo();
                            ProtectedData _arg5 = new ProtectedData();
                            byte[] _result3 = generateCertificateRequest(_arg0, (MacedPublicKey[]) data.createTypedArray(MacedPublicKey.CREATOR), _arg2, _arg3, _arg4, _arg5);
                            reply.writeNoException();
                            reply.writeByteArray(_result3);
                            reply.writeInt(1);
                            _arg4.writeToParcel(reply, 1);
                            reply.writeInt(1);
                            _arg5.writeToParcel(reply, 1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IRemotelyProvisionedComponent {
            public static IRemotelyProvisionedComponent sDefaultImpl;
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

            @Override // android.hardware.security.keymint.IRemotelyProvisionedComponent
            public RpcHardwareInfo getHardwareInfo() throws RemoteException {
                RpcHardwareInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                        if (_reply.readInt() != 0) {
                            _result = RpcHardwareInfo.CREATOR.createFromParcel(_reply);
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

            @Override // android.hardware.security.keymint.IRemotelyProvisionedComponent
            public byte[] generateEcdsaP256KeyPair(boolean testMode, MacedPublicKey macedPublicKey) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(testMode ? 1 : 0);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                        byte[] _result = _reply.createByteArray();
                        if (_reply.readInt() != 0) {
                            macedPublicKey.readFromParcel(_reply);
                        }
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().generateEcdsaP256KeyPair(testMode, macedPublicKey);
                    } else {
                        throw new RemoteException("Method generateEcdsaP256KeyPair is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.hardware.security.keymint.IRemotelyProvisionedComponent
            public byte[] generateCertificateRequest(boolean testMode, MacedPublicKey[] keysToSign, byte[] endpointEncryptionCertChain, byte[] challenge, DeviceInfo deviceInfo, ProtectedData protectedData) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(testMode ? 1 : 0);
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    _data.writeTypedArray(keysToSign, 0);
                    try {
                        _data.writeByteArray(endpointEncryptionCertChain);
                        try {
                            _data.writeByteArray(challenge);
                            try {
                                boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                                if (_status) {
                                    _reply.readException();
                                    byte[] _result = _reply.createByteArray();
                                    if (_reply.readInt() != 0) {
                                        try {
                                            deviceInfo.readFromParcel(_reply);
                                        } catch (Throwable th3) {
                                            th = th3;
                                            _reply.recycle();
                                            _data.recycle();
                                            throw th;
                                        }
                                    }
                                    if (_reply.readInt() != 0) {
                                        try {
                                            protectedData.readFromParcel(_reply);
                                        } catch (Throwable th4) {
                                            th = th4;
                                            _reply.recycle();
                                            _data.recycle();
                                            throw th;
                                        }
                                    }
                                    _reply.recycle();
                                    _data.recycle();
                                    return _result;
                                } else if (Stub.getDefaultImpl() != null) {
                                    byte[] generateCertificateRequest = Stub.getDefaultImpl().generateCertificateRequest(testMode, keysToSign, endpointEncryptionCertChain, challenge, deviceInfo, protectedData);
                                    _reply.recycle();
                                    _data.recycle();
                                    return generateCertificateRequest;
                                } else {
                                    throw new RemoteException("Method generateCertificateRequest is unimplemented.");
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

            @Override // android.hardware.security.keymint.IRemotelyProvisionedComponent
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

            @Override // android.hardware.security.keymint.IRemotelyProvisionedComponent
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

        public static boolean setDefaultImpl(IRemotelyProvisionedComponent impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IRemotelyProvisionedComponent getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
