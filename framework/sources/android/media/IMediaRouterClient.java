package android.media;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IMediaRouterClient extends IInterface {
    void onGlobalA2dpChanged(boolean z) throws RemoteException;

    void onGroupRouteSelected(String str) throws RemoteException;

    void onRestoreRoute() throws RemoteException;

    void onStateChanged() throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IMediaRouterClient {
        @Override // android.media.IMediaRouterClient
        public void onStateChanged() throws RemoteException {
        }

        @Override // android.media.IMediaRouterClient
        public void onRestoreRoute() throws RemoteException {
        }

        @Override // android.media.IMediaRouterClient
        public void onGroupRouteSelected(String routeId) throws RemoteException {
        }

        @Override // android.media.IMediaRouterClient
        public void onGlobalA2dpChanged(boolean a2dpOn) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IMediaRouterClient {
        public static final String DESCRIPTOR = "android.media.IMediaRouterClient";
        static final int TRANSACTION_onGlobalA2dpChanged = 4;
        static final int TRANSACTION_onGroupRouteSelected = 3;
        static final int TRANSACTION_onRestoreRoute = 2;
        static final int TRANSACTION_onStateChanged = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaRouterClient asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IMediaRouterClient)) {
                return new Proxy(obj);
            }
            return (IMediaRouterClient) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onStateChanged";
                case 2:
                    return "onRestoreRoute";
                case 3:
                    return "onGroupRouteSelected";
                case 4:
                    return "onGlobalA2dpChanged";
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
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            onStateChanged();
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            onRestoreRoute();
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg0 = data.readString();
                            onGroupRouteSelected(_arg0);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            boolean _arg02 = data.readInt() != 0;
                            onGlobalA2dpChanged(_arg02);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IMediaRouterClient {
            public static IMediaRouterClient sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.media.IMediaRouterClient
            public void onStateChanged() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onStateChanged();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterClient
            public void onRestoreRoute() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRestoreRoute();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterClient
            public void onGroupRouteSelected(String routeId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(routeId);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onGroupRouteSelected(routeId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.media.IMediaRouterClient
            public void onGlobalA2dpChanged(boolean a2dpOn) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(a2dpOn ? 1 : 0);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onGlobalA2dpChanged(a2dpOn);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaRouterClient impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IMediaRouterClient getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
