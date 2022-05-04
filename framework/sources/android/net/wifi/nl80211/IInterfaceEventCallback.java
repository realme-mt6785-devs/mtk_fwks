package android.net.wifi.nl80211;

import android.net.wifi.nl80211.IApInterface;
import android.net.wifi.nl80211.IClientInterface;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IInterfaceEventCallback extends IInterface {
    public static final String DESCRIPTOR = "android.net.wifi.nl80211.IInterfaceEventCallback";

    void OnApInterfaceReady(IApInterface iApInterface) throws RemoteException;

    void OnApTorndownEvent(IApInterface iApInterface) throws RemoteException;

    void OnClientInterfaceReady(IClientInterface iClientInterface) throws RemoteException;

    void OnClientTorndownEvent(IClientInterface iClientInterface) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IInterfaceEventCallback {
        @Override // android.net.wifi.nl80211.IInterfaceEventCallback
        public void OnClientInterfaceReady(IClientInterface network_interface) throws RemoteException {
        }

        @Override // android.net.wifi.nl80211.IInterfaceEventCallback
        public void OnApInterfaceReady(IApInterface network_interface) throws RemoteException {
        }

        @Override // android.net.wifi.nl80211.IInterfaceEventCallback
        public void OnClientTorndownEvent(IClientInterface network_interface) throws RemoteException {
        }

        @Override // android.net.wifi.nl80211.IInterfaceEventCallback
        public void OnApTorndownEvent(IApInterface network_interface) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IInterfaceEventCallback {
        static final int TRANSACTION_OnApInterfaceReady = 2;
        static final int TRANSACTION_OnApTorndownEvent = 4;
        static final int TRANSACTION_OnClientInterfaceReady = 1;
        static final int TRANSACTION_OnClientTorndownEvent = 3;

        public Stub() {
            attachInterface(this, IInterfaceEventCallback.DESCRIPTOR);
        }

        public static IInterfaceEventCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IInterfaceEventCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IInterfaceEventCallback)) {
                return new Proxy(obj);
            }
            return (IInterfaceEventCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "OnClientInterfaceReady";
                case 2:
                    return "OnApInterfaceReady";
                case 3:
                    return "OnClientTorndownEvent";
                case 4:
                    return "OnApTorndownEvent";
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
                    reply.writeString(IInterfaceEventCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IInterfaceEventCallback.DESCRIPTOR);
                            IClientInterface _arg0 = IClientInterface.Stub.asInterface(data.readStrongBinder());
                            OnClientInterfaceReady(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(IInterfaceEventCallback.DESCRIPTOR);
                            IApInterface _arg02 = IApInterface.Stub.asInterface(data.readStrongBinder());
                            OnApInterfaceReady(_arg02);
                            return true;
                        case 3:
                            data.enforceInterface(IInterfaceEventCallback.DESCRIPTOR);
                            IClientInterface _arg03 = IClientInterface.Stub.asInterface(data.readStrongBinder());
                            OnClientTorndownEvent(_arg03);
                            return true;
                        case 4:
                            data.enforceInterface(IInterfaceEventCallback.DESCRIPTOR);
                            IApInterface _arg04 = IApInterface.Stub.asInterface(data.readStrongBinder());
                            OnApTorndownEvent(_arg04);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IInterfaceEventCallback {
            public static IInterfaceEventCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IInterfaceEventCallback.DESCRIPTOR;
            }

            @Override // android.net.wifi.nl80211.IInterfaceEventCallback
            public void OnClientInterfaceReady(IClientInterface network_interface) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInterfaceEventCallback.DESCRIPTOR);
                    _data.writeStrongBinder(network_interface != null ? network_interface.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().OnClientInterfaceReady(network_interface);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IInterfaceEventCallback
            public void OnApInterfaceReady(IApInterface network_interface) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInterfaceEventCallback.DESCRIPTOR);
                    _data.writeStrongBinder(network_interface != null ? network_interface.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().OnApInterfaceReady(network_interface);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IInterfaceEventCallback
            public void OnClientTorndownEvent(IClientInterface network_interface) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInterfaceEventCallback.DESCRIPTOR);
                    _data.writeStrongBinder(network_interface != null ? network_interface.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().OnClientTorndownEvent(network_interface);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IInterfaceEventCallback
            public void OnApTorndownEvent(IApInterface network_interface) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IInterfaceEventCallback.DESCRIPTOR);
                    _data.writeStrongBinder(network_interface != null ? network_interface.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().OnApTorndownEvent(network_interface);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInterfaceEventCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IInterfaceEventCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
