package android.net;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface INetworkManagementEventObserver extends IInterface {
    void addressRemoved(String str, LinkAddress linkAddress) throws RemoteException;

    void addressUpdated(String str, LinkAddress linkAddress) throws RemoteException;

    void interfaceAdded(String str) throws RemoteException;

    void interfaceClassDataActivityChanged(int i, boolean z, long j, int i2) throws RemoteException;

    void interfaceDnsServerInfo(String str, long j, String[] strArr) throws RemoteException;

    void interfaceLinkStateChanged(String str, boolean z) throws RemoteException;

    void interfaceRemoved(String str) throws RemoteException;

    void interfaceStatusChanged(String str, boolean z) throws RemoteException;

    void limitReached(String str, String str2) throws RemoteException;

    void routeRemoved(RouteInfo routeInfo) throws RemoteException;

    void routeUpdated(RouteInfo routeInfo) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements INetworkManagementEventObserver {
        @Override // android.net.INetworkManagementEventObserver
        public void interfaceStatusChanged(String iface, boolean up) throws RemoteException {
        }

        @Override // android.net.INetworkManagementEventObserver
        public void interfaceLinkStateChanged(String iface, boolean up) throws RemoteException {
        }

        @Override // android.net.INetworkManagementEventObserver
        public void interfaceAdded(String iface) throws RemoteException {
        }

        @Override // android.net.INetworkManagementEventObserver
        public void interfaceRemoved(String iface) throws RemoteException {
        }

        @Override // android.net.INetworkManagementEventObserver
        public void addressUpdated(String iface, LinkAddress address) throws RemoteException {
        }

        @Override // android.net.INetworkManagementEventObserver
        public void addressRemoved(String iface, LinkAddress address) throws RemoteException {
        }

        @Override // android.net.INetworkManagementEventObserver
        public void limitReached(String limitName, String iface) throws RemoteException {
        }

        @Override // android.net.INetworkManagementEventObserver
        public void interfaceClassDataActivityChanged(int transportType, boolean active, long tsNanos, int uid) throws RemoteException {
        }

        @Override // android.net.INetworkManagementEventObserver
        public void interfaceDnsServerInfo(String iface, long lifetime, String[] servers) throws RemoteException {
        }

        @Override // android.net.INetworkManagementEventObserver
        public void routeUpdated(RouteInfo route) throws RemoteException {
        }

        @Override // android.net.INetworkManagementEventObserver
        public void routeRemoved(RouteInfo route) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements INetworkManagementEventObserver {
        public static final String DESCRIPTOR = "android.net.INetworkManagementEventObserver";
        static final int TRANSACTION_addressRemoved = 6;
        static final int TRANSACTION_addressUpdated = 5;
        static final int TRANSACTION_interfaceAdded = 3;
        static final int TRANSACTION_interfaceClassDataActivityChanged = 8;
        static final int TRANSACTION_interfaceDnsServerInfo = 9;
        static final int TRANSACTION_interfaceLinkStateChanged = 2;
        static final int TRANSACTION_interfaceRemoved = 4;
        static final int TRANSACTION_interfaceStatusChanged = 1;
        static final int TRANSACTION_limitReached = 7;
        static final int TRANSACTION_routeRemoved = 11;
        static final int TRANSACTION_routeUpdated = 10;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INetworkManagementEventObserver asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof INetworkManagementEventObserver)) {
                return new Proxy(obj);
            }
            return (INetworkManagementEventObserver) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "interfaceStatusChanged";
                case 2:
                    return "interfaceLinkStateChanged";
                case 3:
                    return "interfaceAdded";
                case 4:
                    return "interfaceRemoved";
                case 5:
                    return "addressUpdated";
                case 6:
                    return "addressRemoved";
                case 7:
                    return "limitReached";
                case 8:
                    return "interfaceClassDataActivityChanged";
                case 9:
                    return "interfaceDnsServerInfo";
                case 10:
                    return "routeUpdated";
                case 11:
                    return "routeRemoved";
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
            LinkAddress _arg1;
            LinkAddress _arg12;
            RouteInfo _arg0;
            RouteInfo _arg02;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    boolean _arg13 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg03 = data.readString();
                            if (data.readInt() != 0) {
                                _arg13 = true;
                            }
                            interfaceStatusChanged(_arg03, _arg13);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg04 = data.readString();
                            if (data.readInt() != 0) {
                                _arg13 = true;
                            }
                            interfaceLinkStateChanged(_arg04, _arg13);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg05 = data.readString();
                            interfaceAdded(_arg05);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg06 = data.readString();
                            interfaceRemoved(_arg06);
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg07 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = (LinkAddress) LinkAddress.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            addressUpdated(_arg07, _arg1);
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg08 = data.readString();
                            if (data.readInt() != 0) {
                                _arg12 = (LinkAddress) LinkAddress.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            addressRemoved(_arg08, _arg12);
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg09 = data.readString();
                            limitReached(_arg09, data.readString());
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg010 = data.readInt();
                            boolean _arg14 = data.readInt() != 0;
                            long _arg2 = data.readLong();
                            int _arg3 = data.readInt();
                            interfaceClassDataActivityChanged(_arg010, _arg14, _arg2, _arg3);
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg011 = data.readString();
                            long _arg15 = data.readLong();
                            String[] _arg22 = data.createStringArray();
                            interfaceDnsServerInfo(_arg011, _arg15, _arg22);
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = (RouteInfo) RouteInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            routeUpdated(_arg0);
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = (RouteInfo) RouteInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            routeRemoved(_arg02);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements INetworkManagementEventObserver {
            public static INetworkManagementEventObserver sDefaultImpl;
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

            @Override // android.net.INetworkManagementEventObserver
            public void interfaceStatusChanged(String iface, boolean up) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(iface);
                    _data.writeInt(up ? 1 : 0);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().interfaceStatusChanged(iface, up);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.INetworkManagementEventObserver
            public void interfaceLinkStateChanged(String iface, boolean up) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(iface);
                    _data.writeInt(up ? 1 : 0);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().interfaceLinkStateChanged(iface, up);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.INetworkManagementEventObserver
            public void interfaceAdded(String iface) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(iface);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().interfaceAdded(iface);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.INetworkManagementEventObserver
            public void interfaceRemoved(String iface) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(iface);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().interfaceRemoved(iface);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.INetworkManagementEventObserver
            public void addressUpdated(String iface, LinkAddress address) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(iface);
                    if (address != null) {
                        _data.writeInt(1);
                        address.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addressUpdated(iface, address);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.INetworkManagementEventObserver
            public void addressRemoved(String iface, LinkAddress address) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(iface);
                    if (address != null) {
                        _data.writeInt(1);
                        address.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addressRemoved(iface, address);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.INetworkManagementEventObserver
            public void limitReached(String limitName, String iface) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(limitName);
                    _data.writeString(iface);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().limitReached(limitName, iface);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.INetworkManagementEventObserver
            public void interfaceClassDataActivityChanged(int transportType, boolean active, long tsNanos, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(transportType);
                    _data.writeInt(active ? 1 : 0);
                    _data.writeLong(tsNanos);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().interfaceClassDataActivityChanged(transportType, active, tsNanos, uid);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.INetworkManagementEventObserver
            public void interfaceDnsServerInfo(String iface, long lifetime, String[] servers) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(iface);
                    _data.writeLong(lifetime);
                    _data.writeStringArray(servers);
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().interfaceDnsServerInfo(iface, lifetime, servers);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.INetworkManagementEventObserver
            public void routeUpdated(RouteInfo route) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (route != null) {
                        _data.writeInt(1);
                        route.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().routeUpdated(route);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.INetworkManagementEventObserver
            public void routeRemoved(RouteInfo route) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (route != null) {
                        _data.writeInt(1);
                        route.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().routeRemoved(route);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INetworkManagementEventObserver impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static INetworkManagementEventObserver getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
