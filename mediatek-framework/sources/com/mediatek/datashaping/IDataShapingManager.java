package com.mediatek.datashaping;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IDataShapingManager extends IInterface {
    public static final String DESCRIPTOR = "com.mediatek.datashaping.IDataShapingManager";

    void disableDataShaping() throws RemoteException;

    void enableDataShaping() throws RemoteException;

    boolean isDataShapingWhitelistApp(String str) throws RemoteException;

    boolean openLteDataUpLinkGate(boolean z) throws RemoteException;

    void setDeviceIdleMode(boolean z) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IDataShapingManager {
        @Override // com.mediatek.datashaping.IDataShapingManager
        public void enableDataShaping() throws RemoteException {
        }

        @Override // com.mediatek.datashaping.IDataShapingManager
        public void disableDataShaping() throws RemoteException {
        }

        @Override // com.mediatek.datashaping.IDataShapingManager
        public boolean openLteDataUpLinkGate(boolean isForce) throws RemoteException {
            return false;
        }

        @Override // com.mediatek.datashaping.IDataShapingManager
        public void setDeviceIdleMode(boolean enabled) throws RemoteException {
        }

        @Override // com.mediatek.datashaping.IDataShapingManager
        public boolean isDataShapingWhitelistApp(String packageName) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IDataShapingManager {
        static final int TRANSACTION_disableDataShaping = 2;
        static final int TRANSACTION_enableDataShaping = 1;
        static final int TRANSACTION_isDataShapingWhitelistApp = 5;
        static final int TRANSACTION_openLteDataUpLinkGate = 3;
        static final int TRANSACTION_setDeviceIdleMode = 4;

        public Stub() {
            attachInterface(this, IDataShapingManager.DESCRIPTOR);
        }

        public static IDataShapingManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IDataShapingManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof IDataShapingManager)) {
                return new Proxy(obj);
            }
            return (IDataShapingManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1598968902:
                    reply.writeString(IDataShapingManager.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg0 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(IDataShapingManager.DESCRIPTOR);
                            enableDataShaping();
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(IDataShapingManager.DESCRIPTOR);
                            disableDataShaping();
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(IDataShapingManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = true;
                            }
                            boolean openLteDataUpLinkGate = openLteDataUpLinkGate(_arg0);
                            reply.writeNoException();
                            reply.writeInt(openLteDataUpLinkGate ? 1 : 0);
                            return true;
                        case 4:
                            data.enforceInterface(IDataShapingManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = true;
                            }
                            setDeviceIdleMode(_arg0);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(IDataShapingManager.DESCRIPTOR);
                            boolean isDataShapingWhitelistApp = isDataShapingWhitelistApp(data.readString());
                            reply.writeNoException();
                            reply.writeInt(isDataShapingWhitelistApp ? 1 : 0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IDataShapingManager {
            public static IDataShapingManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IDataShapingManager.DESCRIPTOR;
            }

            @Override // com.mediatek.datashaping.IDataShapingManager
            public void enableDataShaping() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDataShapingManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().enableDataShaping();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.datashaping.IDataShapingManager
            public void disableDataShaping() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDataShapingManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().disableDataShaping();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.datashaping.IDataShapingManager
            public boolean openLteDataUpLinkGate(boolean isForce) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDataShapingManager.DESCRIPTOR);
                    boolean _result = true;
                    _data.writeInt(isForce ? 1 : 0);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openLteDataUpLinkGate(isForce);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.datashaping.IDataShapingManager
            public void setDeviceIdleMode(boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDataShapingManager.DESCRIPTOR);
                    _data.writeInt(enabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setDeviceIdleMode(enabled);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.mediatek.datashaping.IDataShapingManager
            public boolean isDataShapingWhitelistApp(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDataShapingManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDataShapingWhitelistApp(packageName);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDataShapingManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IDataShapingManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
