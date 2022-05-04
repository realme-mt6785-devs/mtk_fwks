package android.system.keystore2;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.system.keystore2.IKeystoreSecurityLevel;
/* loaded from: classes3.dex */
public interface IKeystoreService extends IInterface {
    public static final String DESCRIPTOR = "android$system$keystore2$IKeystoreService".replace('$', '.');
    public static final String HASH = "19e8b65277839bad0ab335c781e3c652324920ce";
    public static final int VERSION = 1;

    void deleteKey(KeyDescriptor keyDescriptor) throws RemoteException;

    String getInterfaceHash() throws RemoteException;

    int getInterfaceVersion() throws RemoteException;

    KeyEntryResponse getKeyEntry(KeyDescriptor keyDescriptor) throws RemoteException;

    IKeystoreSecurityLevel getSecurityLevel(int i) throws RemoteException;

    KeyDescriptor grant(KeyDescriptor keyDescriptor, int i, int i2) throws RemoteException;

    KeyDescriptor[] listEntries(int i, long j) throws RemoteException;

    void ungrant(KeyDescriptor keyDescriptor, int i) throws RemoteException;

    void updateSubcomponent(KeyDescriptor keyDescriptor, byte[] bArr, byte[] bArr2) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IKeystoreService {
        @Override // android.system.keystore2.IKeystoreService
        public IKeystoreSecurityLevel getSecurityLevel(int securityLevel) throws RemoteException {
            return null;
        }

        @Override // android.system.keystore2.IKeystoreService
        public KeyEntryResponse getKeyEntry(KeyDescriptor key) throws RemoteException {
            return null;
        }

        @Override // android.system.keystore2.IKeystoreService
        public void updateSubcomponent(KeyDescriptor key, byte[] publicCert, byte[] certificateChain) throws RemoteException {
        }

        @Override // android.system.keystore2.IKeystoreService
        public KeyDescriptor[] listEntries(int domain, long nspace) throws RemoteException {
            return null;
        }

        @Override // android.system.keystore2.IKeystoreService
        public void deleteKey(KeyDescriptor key) throws RemoteException {
        }

        @Override // android.system.keystore2.IKeystoreService
        public KeyDescriptor grant(KeyDescriptor key, int granteeUid, int accessVector) throws RemoteException {
            return null;
        }

        @Override // android.system.keystore2.IKeystoreService
        public void ungrant(KeyDescriptor key, int granteeUid) throws RemoteException {
        }

        @Override // android.system.keystore2.IKeystoreService
        public int getInterfaceVersion() {
            return 0;
        }

        @Override // android.system.keystore2.IKeystoreService
        public String getInterfaceHash() {
            return "";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IKeystoreService {
        static final int TRANSACTION_deleteKey = 5;
        static final int TRANSACTION_getInterfaceHash = 16777214;
        static final int TRANSACTION_getInterfaceVersion = 16777215;
        static final int TRANSACTION_getKeyEntry = 2;
        static final int TRANSACTION_getSecurityLevel = 1;
        static final int TRANSACTION_grant = 6;
        static final int TRANSACTION_listEntries = 4;
        static final int TRANSACTION_ungrant = 7;
        static final int TRANSACTION_updateSubcomponent = 3;

        public Stub() {
            markVintfStability();
            attachInterface(this, DESCRIPTOR);
        }

        public static IKeystoreService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IKeystoreService)) {
                return new Proxy(obj);
            }
            return (IKeystoreService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            KeyDescriptor _arg0;
            KeyDescriptor _arg02;
            KeyDescriptor _arg03;
            KeyDescriptor _arg04;
            KeyDescriptor _arg05;
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
                            int _arg06 = data.readInt();
                            IKeystoreSecurityLevel _result = getSecurityLevel(_arg06);
                            reply.writeNoException();
                            reply.writeStrongBinder(_result != null ? _result.asBinder() : null);
                            return true;
                        case 2:
                            data.enforceInterface(descriptor);
                            if (data.readInt() != 0) {
                                _arg0 = KeyDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            KeyEntryResponse _result2 = getKeyEntry(_arg0);
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
                                _arg02 = KeyDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            byte[] _arg1 = data.createByteArray();
                            byte[] _arg2 = data.createByteArray();
                            updateSubcomponent(_arg02, _arg1, _arg2);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(descriptor);
                            int _arg07 = data.readInt();
                            long _arg12 = data.readLong();
                            KeyDescriptor[] _result3 = listEntries(_arg07, _arg12);
                            reply.writeNoException();
                            reply.writeTypedArray(_result3, 1);
                            return true;
                        case 5:
                            data.enforceInterface(descriptor);
                            if (data.readInt() != 0) {
                                _arg03 = KeyDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            deleteKey(_arg03);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(descriptor);
                            if (data.readInt() != 0) {
                                _arg04 = KeyDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            int _arg13 = data.readInt();
                            int _arg22 = data.readInt();
                            KeyDescriptor _result4 = grant(_arg04, _arg13, _arg22);
                            reply.writeNoException();
                            if (_result4 != null) {
                                reply.writeInt(1);
                                _result4.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 7:
                            data.enforceInterface(descriptor);
                            if (data.readInt() != 0) {
                                _arg05 = KeyDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            int _arg14 = data.readInt();
                            ungrant(_arg05, _arg14);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IKeystoreService {
            public static IKeystoreService sDefaultImpl;
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

            @Override // android.system.keystore2.IKeystoreService
            public IKeystoreSecurityLevel getSecurityLevel(int securityLevel) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(securityLevel);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                        IKeystoreSecurityLevel _result = IKeystoreSecurityLevel.Stub.asInterface(_reply.readStrongBinder());
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSecurityLevel(securityLevel);
                    } else {
                        throw new RemoteException("Method getSecurityLevel is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.system.keystore2.IKeystoreService
            public KeyEntryResponse getKeyEntry(KeyDescriptor key) throws RemoteException {
                KeyEntryResponse _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if (key != null) {
                        _data.writeInt(1);
                        key.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                        if (_reply.readInt() != 0) {
                            _result = KeyEntryResponse.CREATOR.createFromParcel(_reply);
                        } else {
                            _result = null;
                        }
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getKeyEntry(key);
                    } else {
                        throw new RemoteException("Method getKeyEntry is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.system.keystore2.IKeystoreService
            public void updateSubcomponent(KeyDescriptor key, byte[] publicCert, byte[] certificateChain) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if (key != null) {
                        _data.writeInt(1);
                        key.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeByteArray(publicCert);
                    _data.writeByteArray(certificateChain);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                    } else if (Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateSubcomponent(key, publicCert, certificateChain);
                    } else {
                        throw new RemoteException("Method updateSubcomponent is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.system.keystore2.IKeystoreService
            public KeyDescriptor[] listEntries(int domain, long nspace) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(domain);
                    _data.writeLong(nspace);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                        KeyDescriptor[] _result = (KeyDescriptor[]) _reply.createTypedArray(KeyDescriptor.CREATOR);
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().listEntries(domain, nspace);
                    } else {
                        throw new RemoteException("Method listEntries is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.system.keystore2.IKeystoreService
            public void deleteKey(KeyDescriptor key) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if (key != null) {
                        _data.writeInt(1);
                        key.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
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

            @Override // android.system.keystore2.IKeystoreService
            public KeyDescriptor grant(KeyDescriptor key, int granteeUid, int accessVector) throws RemoteException {
                KeyDescriptor _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if (key != null) {
                        _data.writeInt(1);
                        key.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(granteeUid);
                    _data.writeInt(accessVector);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                        if (_reply.readInt() != 0) {
                            _result = KeyDescriptor.CREATOR.createFromParcel(_reply);
                        } else {
                            _result = null;
                        }
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().grant(key, granteeUid, accessVector);
                    } else {
                        throw new RemoteException("Method grant is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.system.keystore2.IKeystoreService
            public void ungrant(KeyDescriptor key, int granteeUid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if (key != null) {
                        _data.writeInt(1);
                        key.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(granteeUid);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status) {
                        _reply.readException();
                    } else if (Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().ungrant(key, granteeUid);
                    } else {
                        throw new RemoteException("Method ungrant is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.system.keystore2.IKeystoreService
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

            @Override // android.system.keystore2.IKeystoreService
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

        public static boolean setDefaultImpl(IKeystoreService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IKeystoreService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
