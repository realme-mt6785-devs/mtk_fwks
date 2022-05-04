package android.net;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IPacProxyInstalledListener extends IInterface {
    public static final String DESCRIPTOR = "android.net.IPacProxyInstalledListener";

    void onPacProxyInstalled(Network network, ProxyInfo proxyInfo) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IPacProxyInstalledListener {
        @Override // android.net.IPacProxyInstalledListener
        public void onPacProxyInstalled(Network network, ProxyInfo proxy) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IPacProxyInstalledListener {
        static final int TRANSACTION_onPacProxyInstalled = 1;

        public Stub() {
            attachInterface(this, IPacProxyInstalledListener.DESCRIPTOR);
        }

        public static IPacProxyInstalledListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IPacProxyInstalledListener.DESCRIPTOR);
            if (iin == null || !(iin instanceof IPacProxyInstalledListener)) {
                return new Proxy(obj);
            }
            return (IPacProxyInstalledListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onPacProxyInstalled";
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
            Network _arg0;
            ProxyInfo _arg1;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IPacProxyInstalledListener.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IPacProxyInstalledListener.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = (Network) Network.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = (ProxyInfo) ProxyInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            onPacProxyInstalled(_arg0, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IPacProxyInstalledListener {
            public static IPacProxyInstalledListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IPacProxyInstalledListener.DESCRIPTOR;
            }

            @Override // android.net.IPacProxyInstalledListener
            public void onPacProxyInstalled(Network network, ProxyInfo proxy) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPacProxyInstalledListener.DESCRIPTOR);
                    if (network != null) {
                        _data.writeInt(1);
                        network.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (proxy != null) {
                        _data.writeInt(1);
                        proxy.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPacProxyInstalled(network, proxy);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPacProxyInstalledListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IPacProxyInstalledListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
