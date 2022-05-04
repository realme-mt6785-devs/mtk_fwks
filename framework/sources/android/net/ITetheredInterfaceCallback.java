package android.net;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface ITetheredInterfaceCallback extends IInterface {
    public static final String DESCRIPTOR = "android.net.ITetheredInterfaceCallback";

    void onAvailable(String str) throws RemoteException;

    void onUnavailable() throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements ITetheredInterfaceCallback {
        @Override // android.net.ITetheredInterfaceCallback
        public void onAvailable(String iface) throws RemoteException {
        }

        @Override // android.net.ITetheredInterfaceCallback
        public void onUnavailable() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ITetheredInterfaceCallback {
        static final int TRANSACTION_onAvailable = 1;
        static final int TRANSACTION_onUnavailable = 2;

        public Stub() {
            attachInterface(this, ITetheredInterfaceCallback.DESCRIPTOR);
        }

        public static ITetheredInterfaceCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ITetheredInterfaceCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof ITetheredInterfaceCallback)) {
                return new Proxy(obj);
            }
            return (ITetheredInterfaceCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onAvailable";
                case 2:
                    return "onUnavailable";
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
                    reply.writeString(ITetheredInterfaceCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ITetheredInterfaceCallback.DESCRIPTOR);
                            String _arg0 = data.readString();
                            onAvailable(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(ITetheredInterfaceCallback.DESCRIPTOR);
                            onUnavailable();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements ITetheredInterfaceCallback {
            public static ITetheredInterfaceCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ITetheredInterfaceCallback.DESCRIPTOR;
            }

            @Override // android.net.ITetheredInterfaceCallback
            public void onAvailable(String iface) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITetheredInterfaceCallback.DESCRIPTOR);
                    _data.writeString(iface);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAvailable(iface);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.net.ITetheredInterfaceCallback
            public void onUnavailable() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITetheredInterfaceCallback.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onUnavailable();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITetheredInterfaceCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ITetheredInterfaceCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
