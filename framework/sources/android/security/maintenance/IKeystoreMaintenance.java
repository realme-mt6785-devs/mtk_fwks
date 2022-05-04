package android.security.maintenance;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.system.keystore2.KeyDescriptor;
/* loaded from: classes2.dex */
public interface IKeystoreMaintenance extends IInterface {
    public static final String DESCRIPTOR = "android$security$maintenance$IKeystoreMaintenance".replace('$', '.');

    void clearNamespace(int i, long j) throws RemoteException;

    void deleteAllKeys() throws RemoteException;

    void earlyBootEnded() throws RemoteException;

    int getState(int i) throws RemoteException;

    void migrateKeyNamespace(KeyDescriptor keyDescriptor, KeyDescriptor keyDescriptor2) throws RemoteException;

    void onDeviceOffBody() throws RemoteException;

    void onUserAdded(int i) throws RemoteException;

    void onUserPasswordChanged(int i, byte[] bArr) throws RemoteException;

    void onUserRemoved(int i) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IKeystoreMaintenance {
        @Override // android.security.maintenance.IKeystoreMaintenance
        public void onUserAdded(int userId) throws RemoteException {
        }

        @Override // android.security.maintenance.IKeystoreMaintenance
        public void onUserRemoved(int userId) throws RemoteException {
        }

        @Override // android.security.maintenance.IKeystoreMaintenance
        public void onUserPasswordChanged(int userId, byte[] password) throws RemoteException {
        }

        @Override // android.security.maintenance.IKeystoreMaintenance
        public void clearNamespace(int domain, long nspace) throws RemoteException {
        }

        @Override // android.security.maintenance.IKeystoreMaintenance
        public int getState(int userId) throws RemoteException {
            return 0;
        }

        @Override // android.security.maintenance.IKeystoreMaintenance
        public void earlyBootEnded() throws RemoteException {
        }

        @Override // android.security.maintenance.IKeystoreMaintenance
        public void onDeviceOffBody() throws RemoteException {
        }

        @Override // android.security.maintenance.IKeystoreMaintenance
        public void migrateKeyNamespace(KeyDescriptor source, KeyDescriptor destination) throws RemoteException {
        }

        @Override // android.security.maintenance.IKeystoreMaintenance
        public void deleteAllKeys() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IKeystoreMaintenance {
        static final int TRANSACTION_clearNamespace = 4;
        static final int TRANSACTION_deleteAllKeys = 9;
        static final int TRANSACTION_earlyBootEnded = 6;
        static final int TRANSACTION_getState = 5;
        static final int TRANSACTION_migrateKeyNamespace = 8;
        static final int TRANSACTION_onDeviceOffBody = 7;
        static final int TRANSACTION_onUserAdded = 1;
        static final int TRANSACTION_onUserPasswordChanged = 3;
        static final int TRANSACTION_onUserRemoved = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IKeystoreMaintenance asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IKeystoreMaintenance)) {
                return new Proxy(obj);
            }
            return (IKeystoreMaintenance) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            KeyDescriptor _arg0;
            KeyDescriptor _arg1;
            String descriptor = DESCRIPTOR;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(descriptor);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(descriptor);
                            int _arg02 = data.readInt();
                            onUserAdded(_arg02);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(descriptor);
                            int _arg03 = data.readInt();
                            onUserRemoved(_arg03);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(descriptor);
                            int _arg04 = data.readInt();
                            byte[] _arg12 = data.createByteArray();
                            onUserPasswordChanged(_arg04, _arg12);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(descriptor);
                            int _arg05 = data.readInt();
                            long _arg13 = data.readLong();
                            clearNamespace(_arg05, _arg13);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(descriptor);
                            int _arg06 = data.readInt();
                            int _result = getState(_arg06);
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 6:
                            data.enforceInterface(descriptor);
                            earlyBootEnded();
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(descriptor);
                            onDeviceOffBody();
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(descriptor);
                            if (data.readInt() != 0) {
                                _arg0 = KeyDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = KeyDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            migrateKeyNamespace(_arg0, _arg1);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(descriptor);
                            deleteAllKeys();
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IKeystoreMaintenance {
            public static IKeystoreMaintenance sDefaultImpl;
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

            @Override // android.security.maintenance.IKeystoreMaintenance
            public void onUserAdded(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 32);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onUserAdded(userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.maintenance.IKeystoreMaintenance
            public void onUserRemoved(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 32);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onUserRemoved(userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.maintenance.IKeystoreMaintenance
            public void onUserPasswordChanged(int userId, byte[] password) throws RemoteException {
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeByteArray(password);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 32);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onUserPasswordChanged(userId, password);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.maintenance.IKeystoreMaintenance
            public void clearNamespace(int domain, long nspace) throws RemoteException {
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(domain);
                    _data.writeLong(nspace);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 32);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().clearNamespace(domain, nspace);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.maintenance.IKeystoreMaintenance
            public int getState(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 32);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getState(userId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.maintenance.IKeystoreMaintenance
            public void earlyBootEnded() throws RemoteException {
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 32);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().earlyBootEnded();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.maintenance.IKeystoreMaintenance
            public void onDeviceOffBody() throws RemoteException {
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 32);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onDeviceOffBody();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.maintenance.IKeystoreMaintenance
            public void migrateKeyNamespace(KeyDescriptor source, KeyDescriptor destination) throws RemoteException {
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if (source != null) {
                        _data.writeInt(1);
                        source.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (destination != null) {
                        _data.writeInt(1);
                        destination.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(8, _data, _reply, 32);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().migrateKeyNamespace(source, destination);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.security.maintenance.IKeystoreMaintenance
            public void deleteAllKeys() throws RemoteException {
                Parcel _data = Parcel.obtain();
                _data.markSensitive();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 32);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().deleteAllKeys();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IKeystoreMaintenance impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IKeystoreMaintenance getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
