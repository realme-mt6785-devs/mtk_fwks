package android.system.keystore2;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IKeystoreOperation extends IInterface {
    public static final String DESCRIPTOR = "android$system$keystore2$IKeystoreOperation".replace('$', '.');
    public static final String HASH = "19e8b65277839bad0ab335c781e3c652324920ce";
    public static final int VERSION = 1;

    void abort() throws RemoteException;

    byte[] finish(byte[] bArr, byte[] bArr2) throws RemoteException;

    String getInterfaceHash() throws RemoteException;

    int getInterfaceVersion() throws RemoteException;

    byte[] update(byte[] bArr) throws RemoteException;

    void updateAad(byte[] bArr) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IKeystoreOperation {
        @Override // android.system.keystore2.IKeystoreOperation
        public void updateAad(byte[] aadInput) throws RemoteException {
        }

        @Override // android.system.keystore2.IKeystoreOperation
        public byte[] update(byte[] input) throws RemoteException {
            return null;
        }

        @Override // android.system.keystore2.IKeystoreOperation
        public byte[] finish(byte[] input, byte[] signature) throws RemoteException {
            return null;
        }

        @Override // android.system.keystore2.IKeystoreOperation
        public void abort() throws RemoteException {
        }

        @Override // android.system.keystore2.IKeystoreOperation
        public int getInterfaceVersion() {
            return 0;
        }

        @Override // android.system.keystore2.IKeystoreOperation
        public String getInterfaceHash() {
            return "";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IKeystoreOperation {
        static final int TRANSACTION_abort = 4;
        static final int TRANSACTION_finish = 3;
        static final int TRANSACTION_getInterfaceHash = 16777214;
        static final int TRANSACTION_getInterfaceVersion = 16777215;
        static final int TRANSACTION_update = 2;
        static final int TRANSACTION_updateAad = 1;

        public Stub() {
            markVintfStability();
            attachInterface(this, DESCRIPTOR);
        }

        public static IKeystoreOperation asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IKeystoreOperation)) {
                return new Proxy(obj);
            }
            return (IKeystoreOperation) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
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
                            byte[] _arg0 = data.createByteArray();
                            updateAad(_arg0);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(descriptor);
                            byte[] _arg02 = data.createByteArray();
                            byte[] _result = update(_arg02);
                            reply.writeNoException();
                            reply.writeByteArray(_result);
                            return true;
                        case 3:
                            data.enforceInterface(descriptor);
                            byte[] _arg03 = data.createByteArray();
                            byte[] _arg1 = data.createByteArray();
                            byte[] _result2 = finish(_arg03, _arg1);
                            reply.writeNoException();
                            reply.writeByteArray(_result2);
                            return true;
                        case 4:
                            data.enforceInterface(descriptor);
                            abort();
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IKeystoreOperation {
            public static IKeystoreOperation sDefaultImpl;
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

            @Override // android.system.keystore2.IKeystoreOperation
            public void updateAad(byte[] aadInput) throws RemoteException {
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeByteArray(aadInput);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 32);
                    if (_status) {
                        _reply.readException();
                    } else if (Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateAad(aadInput);
                    } else {
                        throw new RemoteException("Method updateAad is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.system.keystore2.IKeystoreOperation
            public byte[] update(byte[] input) throws RemoteException {
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeByteArray(input);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 32);
                    if (_status) {
                        _reply.readException();
                        byte[] _result = _reply.createByteArray();
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().update(input);
                    } else {
                        throw new RemoteException("Method update is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.system.keystore2.IKeystoreOperation
            public byte[] finish(byte[] input, byte[] signature) throws RemoteException {
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeByteArray(input);
                    _data.writeByteArray(signature);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 32);
                    if (_status) {
                        _reply.readException();
                        byte[] _result = _reply.createByteArray();
                        return _result;
                    } else if (Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().finish(input, signature);
                    } else {
                        throw new RemoteException("Method finish is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.system.keystore2.IKeystoreOperation
            public void abort() throws RemoteException {
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 32);
                    if (_status) {
                        _reply.readException();
                    } else if (Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().abort();
                    } else {
                        throw new RemoteException("Method abort is unimplemented.");
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.system.keystore2.IKeystoreOperation
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

            @Override // android.system.keystore2.IKeystoreOperation
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

        public static boolean setDefaultImpl(IKeystoreOperation impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IKeystoreOperation getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
