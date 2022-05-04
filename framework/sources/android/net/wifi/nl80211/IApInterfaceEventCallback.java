package android.net.wifi.nl80211;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IApInterfaceEventCallback extends IInterface {
    public static final int BANDWIDTH_160 = 6;
    public static final int BANDWIDTH_20 = 2;
    public static final int BANDWIDTH_20_NOHT = 1;
    public static final int BANDWIDTH_40 = 3;
    public static final int BANDWIDTH_80 = 4;
    public static final int BANDWIDTH_80P80 = 5;
    public static final int BANDWIDTH_INVALID = 0;
    public static final String DESCRIPTOR = "android.net.wifi.nl80211.IApInterfaceEventCallback";

    void onConnectedClientsChanged(NativeWifiClient nativeWifiClient, boolean z) throws RemoteException;

    void onSoftApChannelSwitched(int i, int i2) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IApInterfaceEventCallback {
        @Override // android.net.wifi.nl80211.IApInterfaceEventCallback
        public void onConnectedClientsChanged(NativeWifiClient client, boolean isConnected) throws RemoteException {
        }

        @Override // android.net.wifi.nl80211.IApInterfaceEventCallback
        public void onSoftApChannelSwitched(int frequency, int bandwidth) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IApInterfaceEventCallback {
        static final int TRANSACTION_onConnectedClientsChanged = 1;
        static final int TRANSACTION_onSoftApChannelSwitched = 2;

        public Stub() {
            attachInterface(this, IApInterfaceEventCallback.DESCRIPTOR);
        }

        public static IApInterfaceEventCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IApInterfaceEventCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IApInterfaceEventCallback)) {
                return new Proxy(obj);
            }
            return (IApInterfaceEventCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onConnectedClientsChanged";
                case 2:
                    return "onSoftApChannelSwitched";
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
            NativeWifiClient _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IApInterfaceEventCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IApInterfaceEventCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = NativeWifiClient.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            boolean _arg1 = data.readInt() != 0;
                            onConnectedClientsChanged(_arg0, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(IApInterfaceEventCallback.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            int _arg12 = data.readInt();
                            onSoftApChannelSwitched(_arg02, _arg12);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IApInterfaceEventCallback {
            public static IApInterfaceEventCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IApInterfaceEventCallback.DESCRIPTOR;
            }

            @Override // android.net.wifi.nl80211.IApInterfaceEventCallback
            public void onConnectedClientsChanged(NativeWifiClient client, boolean isConnected) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IApInterfaceEventCallback.DESCRIPTOR);
                    int i = 0;
                    if (client != null) {
                        _data.writeInt(1);
                        client.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (isConnected) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onConnectedClientsChanged(client, isConnected);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IApInterfaceEventCallback
            public void onSoftApChannelSwitched(int frequency, int bandwidth) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IApInterfaceEventCallback.DESCRIPTOR);
                    _data.writeInt(frequency);
                    _data.writeInt(bandwidth);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSoftApChannelSwitched(frequency, bandwidth);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IApInterfaceEventCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IApInterfaceEventCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
