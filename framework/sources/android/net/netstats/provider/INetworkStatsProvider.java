package android.net.netstats.provider;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface INetworkStatsProvider extends IInterface {
    public static final String DESCRIPTOR = "android.net.netstats.provider.INetworkStatsProvider";

    void onRequestStatsUpdate(int i) throws RemoteException;

    void onSetAlert(long j) throws RemoteException;

    void onSetWarningAndLimit(String str, long j, long j2) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements INetworkStatsProvider {
        @Override // android.net.netstats.provider.INetworkStatsProvider
        public void onRequestStatsUpdate(int token) throws RemoteException {
        }

        @Override // android.net.netstats.provider.INetworkStatsProvider
        public void onSetAlert(long quotaBytes) throws RemoteException {
        }

        @Override // android.net.netstats.provider.INetworkStatsProvider
        public void onSetWarningAndLimit(String iface, long warningBytes, long limitBytes) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements INetworkStatsProvider {
        static final int TRANSACTION_onRequestStatsUpdate = 1;
        static final int TRANSACTION_onSetAlert = 2;
        static final int TRANSACTION_onSetWarningAndLimit = 3;

        public Stub() {
            attachInterface(this, INetworkStatsProvider.DESCRIPTOR);
        }

        public static INetworkStatsProvider asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(INetworkStatsProvider.DESCRIPTOR);
            if (iin == null || !(iin instanceof INetworkStatsProvider)) {
                return new Proxy(obj);
            }
            return (INetworkStatsProvider) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onRequestStatsUpdate";
                case 2:
                    return "onSetAlert";
                case 3:
                    return "onSetWarningAndLimit";
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
                    reply.writeString(INetworkStatsProvider.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(INetworkStatsProvider.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            onRequestStatsUpdate(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(INetworkStatsProvider.DESCRIPTOR);
                            long _arg02 = data.readLong();
                            onSetAlert(_arg02);
                            return true;
                        case 3:
                            data.enforceInterface(INetworkStatsProvider.DESCRIPTOR);
                            String _arg03 = data.readString();
                            long _arg1 = data.readLong();
                            long _arg2 = data.readLong();
                            onSetWarningAndLimit(_arg03, _arg1, _arg2);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements INetworkStatsProvider {
            public static INetworkStatsProvider sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return INetworkStatsProvider.DESCRIPTOR;
            }

            @Override // android.net.netstats.provider.INetworkStatsProvider
            public void onRequestStatsUpdate(int token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(INetworkStatsProvider.DESCRIPTOR);
                    _data.writeInt(token);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRequestStatsUpdate(token);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.netstats.provider.INetworkStatsProvider
            public void onSetAlert(long quotaBytes) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(INetworkStatsProvider.DESCRIPTOR);
                    _data.writeLong(quotaBytes);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSetAlert(quotaBytes);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.netstats.provider.INetworkStatsProvider
            public void onSetWarningAndLimit(String iface, long warningBytes, long limitBytes) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(INetworkStatsProvider.DESCRIPTOR);
                    _data.writeString(iface);
                    _data.writeLong(warningBytes);
                    _data.writeLong(limitBytes);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSetWarningAndLimit(iface, warningBytes, limitBytes);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INetworkStatsProvider impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static INetworkStatsProvider getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
