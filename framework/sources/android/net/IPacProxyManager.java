package android.net;

import android.net.IPacProxyInstalledListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IPacProxyManager extends IInterface {
    public static final String DESCRIPTOR = "android.net.IPacProxyManager";

    void addListener(IPacProxyInstalledListener iPacProxyInstalledListener) throws RemoteException;

    void removeListener(IPacProxyInstalledListener iPacProxyInstalledListener) throws RemoteException;

    void setCurrentProxyScriptUrl(ProxyInfo proxyInfo) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IPacProxyManager {
        @Override // android.net.IPacProxyManager
        public void addListener(IPacProxyInstalledListener listener) throws RemoteException {
        }

        @Override // android.net.IPacProxyManager
        public void removeListener(IPacProxyInstalledListener listener) throws RemoteException {
        }

        @Override // android.net.IPacProxyManager
        public void setCurrentProxyScriptUrl(ProxyInfo proxyInfo) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IPacProxyManager {
        static final int TRANSACTION_addListener = 1;
        static final int TRANSACTION_removeListener = 2;
        static final int TRANSACTION_setCurrentProxyScriptUrl = 3;

        public Stub() {
            attachInterface(this, IPacProxyManager.DESCRIPTOR);
        }

        public static IPacProxyManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IPacProxyManager.DESCRIPTOR);
            if (iin == null || !(iin instanceof IPacProxyManager)) {
                return new Proxy(obj);
            }
            return (IPacProxyManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "addListener";
                case 2:
                    return "removeListener";
                case 3:
                    return "setCurrentProxyScriptUrl";
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
            ProxyInfo _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IPacProxyManager.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IPacProxyManager.DESCRIPTOR);
                            IPacProxyInstalledListener _arg02 = IPacProxyInstalledListener.Stub.asInterface(data.readStrongBinder());
                            addListener(_arg02);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(IPacProxyManager.DESCRIPTOR);
                            IPacProxyInstalledListener _arg03 = IPacProxyInstalledListener.Stub.asInterface(data.readStrongBinder());
                            removeListener(_arg03);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(IPacProxyManager.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = (ProxyInfo) ProxyInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            setCurrentProxyScriptUrl(_arg0);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IPacProxyManager {
            public static IPacProxyManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IPacProxyManager.DESCRIPTOR;
            }

            @Override // android.net.IPacProxyManager
            public void addListener(IPacProxyInstalledListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPacProxyManager.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addListener(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.IPacProxyManager
            public void removeListener(IPacProxyInstalledListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPacProxyManager.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeListener(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.IPacProxyManager
            public void setCurrentProxyScriptUrl(ProxyInfo proxyInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPacProxyManager.DESCRIPTOR);
                    if (proxyInfo != null) {
                        _data.writeInt(1);
                        proxyInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setCurrentProxyScriptUrl(proxyInfo);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPacProxyManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IPacProxyManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
