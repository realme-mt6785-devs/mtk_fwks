package android.bluetooth;

import android.bluetooth.IBluetoothMcsCallbacks;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IBluetoothMcsServer extends IInterface {
    public static final String DESCRIPTOR = "android.bluetooth.IBluetoothMcsServer";

    void registerServiceInstance(String str, IBluetoothMcsCallbacks iBluetoothMcsCallbacks) throws RemoteException;

    void unregisterServiceInstance(String str) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IBluetoothMcsServer {
        @Override // android.bluetooth.IBluetoothMcsServer
        public void registerServiceInstance(String app_token, IBluetoothMcsCallbacks callbacks) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothMcsServer
        public void unregisterServiceInstance(String app_token) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IBluetoothMcsServer {
        static final int TRANSACTION_registerServiceInstance = 1;
        static final int TRANSACTION_unregisterServiceInstance = 2;

        public Stub() {
            attachInterface(this, IBluetoothMcsServer.DESCRIPTOR);
        }

        public static IBluetoothMcsServer asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IBluetoothMcsServer.DESCRIPTOR);
            if (iin == null || !(iin instanceof IBluetoothMcsServer)) {
                return new Proxy(obj);
            }
            return (IBluetoothMcsServer) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "registerServiceInstance";
                case 2:
                    return "unregisterServiceInstance";
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
                    reply.writeString(IBluetoothMcsServer.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IBluetoothMcsServer.DESCRIPTOR);
                            String _arg0 = data.readString();
                            IBluetoothMcsCallbacks _arg1 = IBluetoothMcsCallbacks.Stub.asInterface(data.readStrongBinder());
                            registerServiceInstance(_arg0, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(IBluetoothMcsServer.DESCRIPTOR);
                            String _arg02 = data.readString();
                            unregisterServiceInstance(_arg02);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IBluetoothMcsServer {
            public static IBluetoothMcsServer sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IBluetoothMcsServer.DESCRIPTOR;
            }

            @Override // android.bluetooth.IBluetoothMcsServer
            public void registerServiceInstance(String app_token, IBluetoothMcsCallbacks callbacks) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMcsServer.DESCRIPTOR);
                    _data.writeString(app_token);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerServiceInstance(app_token, callbacks);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothMcsServer
            public void unregisterServiceInstance(String app_token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IBluetoothMcsServer.DESCRIPTOR);
                    _data.writeString(app_token);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterServiceInstance(app_token);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBluetoothMcsServer impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IBluetoothMcsServer getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
