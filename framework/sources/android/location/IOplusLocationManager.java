package android.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
/* loaded from: classes2.dex */
public interface IOplusLocationManager extends IInterface {
    public static final String DESCRIPTOR = "android.location.IOplusLocationManager";

    List<String> getInUsePackagesList() throws RemoteException;

    void setDebugDump() throws RemoteException;

    void setDebugOff() throws RemoteException;

    void setDebugOn() throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IOplusLocationManager {
        @Override // android.location.IOplusLocationManager
        public List<String> getInUsePackagesList() throws RemoteException {
            return null;
        }

        @Override // android.location.IOplusLocationManager
        public void setDebugOn() throws RemoteException {
        }

        @Override // android.location.IOplusLocationManager
        public void setDebugOff() throws RemoteException {
        }

        @Override // android.location.IOplusLocationManager
        public void setDebugDump() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IOplusLocationManager {
        static final int TRANSACTION_getInUsePackagesList = 1;
        static final int TRANSACTION_setDebugDump = 4;
        static final int TRANSACTION_setDebugOff = 3;
        static final int TRANSACTION_setDebugOn = 2;

        public Stub() {
            attachInterface(this, IOplusLocationManager.DESCRIPTOR);
        }

        public static IOplusLocationManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusLocationManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusLocationManager)) {
                return new Proxy(obj);
            }
            return (IOplusLocationManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getInUsePackagesList";
                case 2:
                    return "setDebugOn";
                case 3:
                    return "setDebugOff";
                case 4:
                    return "setDebugDump";
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
                    reply.writeString(IOplusLocationManager.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusLocationManager.DESCRIPTOR);
                            List<String> _result = getInUsePackagesList();
                            reply.writeNoException();
                            reply.writeStringList(_result);
                            return true;
                        case 2:
                            data.enforceInterface(IOplusLocationManager.DESCRIPTOR);
                            setDebugOn();
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(IOplusLocationManager.DESCRIPTOR);
                            setDebugOff();
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(IOplusLocationManager.DESCRIPTOR);
                            setDebugDump();
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IOplusLocationManager {
            public static IOplusLocationManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusLocationManager.DESCRIPTOR;
            }

            @Override // android.location.IOplusLocationManager
            public List<String> getInUsePackagesList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusLocationManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInUsePackagesList();
                    }
                    _reply.readException();
                    List<String> _result = _reply.createStringArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.IOplusLocationManager
            public void setDebugOn() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusLocationManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setDebugOn();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.IOplusLocationManager
            public void setDebugOff() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusLocationManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setDebugOff();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.location.IOplusLocationManager
            public void setDebugDump() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusLocationManager.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setDebugDump();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusLocationManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusLocationManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
