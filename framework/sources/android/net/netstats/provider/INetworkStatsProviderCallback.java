package android.net.netstats.provider;

import android.net.NetworkStats;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface INetworkStatsProviderCallback extends IInterface {
    public static final String DESCRIPTOR = "android.net.netstats.provider.INetworkStatsProviderCallback";

    void notifyAlertReached() throws RemoteException;

    void notifyStatsUpdated(int i, NetworkStats networkStats, NetworkStats networkStats2) throws RemoteException;

    void notifyWarningOrLimitReached() throws RemoteException;

    void unregister() throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements INetworkStatsProviderCallback {
        @Override // android.net.netstats.provider.INetworkStatsProviderCallback
        public void notifyStatsUpdated(int token, NetworkStats ifaceStats, NetworkStats uidStats) throws RemoteException {
        }

        @Override // android.net.netstats.provider.INetworkStatsProviderCallback
        public void notifyAlertReached() throws RemoteException {
        }

        @Override // android.net.netstats.provider.INetworkStatsProviderCallback
        public void notifyWarningOrLimitReached() throws RemoteException {
        }

        @Override // android.net.netstats.provider.INetworkStatsProviderCallback
        public void unregister() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements INetworkStatsProviderCallback {
        static final int TRANSACTION_notifyAlertReached = 2;
        static final int TRANSACTION_notifyStatsUpdated = 1;
        static final int TRANSACTION_notifyWarningOrLimitReached = 3;
        static final int TRANSACTION_unregister = 4;

        public Stub() {
            attachInterface(this, INetworkStatsProviderCallback.DESCRIPTOR);
        }

        public static INetworkStatsProviderCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(INetworkStatsProviderCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof INetworkStatsProviderCallback)) {
                return new Proxy(obj);
            }
            return (INetworkStatsProviderCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "notifyStatsUpdated";
                case 2:
                    return "notifyAlertReached";
                case 3:
                    return "notifyWarningOrLimitReached";
                case 4:
                    return "unregister";
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
            NetworkStats _arg1;
            NetworkStats _arg2;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(INetworkStatsProviderCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(INetworkStatsProviderCallback.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = NetworkStats.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg2 = NetworkStats.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            notifyStatsUpdated(_arg0, _arg1, _arg2);
                            return true;
                        case 2:
                            data.enforceInterface(INetworkStatsProviderCallback.DESCRIPTOR);
                            notifyAlertReached();
                            return true;
                        case 3:
                            data.enforceInterface(INetworkStatsProviderCallback.DESCRIPTOR);
                            notifyWarningOrLimitReached();
                            return true;
                        case 4:
                            data.enforceInterface(INetworkStatsProviderCallback.DESCRIPTOR);
                            unregister();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements INetworkStatsProviderCallback {
            public static INetworkStatsProviderCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return INetworkStatsProviderCallback.DESCRIPTOR;
            }

            @Override // android.net.netstats.provider.INetworkStatsProviderCallback
            public void notifyStatsUpdated(int token, NetworkStats ifaceStats, NetworkStats uidStats) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(INetworkStatsProviderCallback.DESCRIPTOR);
                    _data.writeInt(token);
                    if (ifaceStats != null) {
                        _data.writeInt(1);
                        ifaceStats.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (uidStats != null) {
                        _data.writeInt(1);
                        uidStats.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyStatsUpdated(token, ifaceStats, uidStats);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.netstats.provider.INetworkStatsProviderCallback
            public void notifyAlertReached() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(INetworkStatsProviderCallback.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyAlertReached();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.netstats.provider.INetworkStatsProviderCallback
            public void notifyWarningOrLimitReached() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(INetworkStatsProviderCallback.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyWarningOrLimitReached();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.netstats.provider.INetworkStatsProviderCallback
            public void unregister() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(INetworkStatsProviderCallback.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregister();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INetworkStatsProviderCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static INetworkStatsProviderCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
