package android.location.provider;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IProviderRequestListener extends IInterface {
    public static final String DESCRIPTOR = "android.location.provider.IProviderRequestListener";

    void onProviderRequestChanged(String str, ProviderRequest providerRequest) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IProviderRequestListener {
        @Override // android.location.provider.IProviderRequestListener
        public void onProviderRequestChanged(String provider, ProviderRequest request) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IProviderRequestListener {
        static final int TRANSACTION_onProviderRequestChanged = 1;

        public Stub() {
            attachInterface(this, IProviderRequestListener.DESCRIPTOR);
        }

        public static IProviderRequestListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IProviderRequestListener.DESCRIPTOR);
            if (iin == null || !(iin instanceof IProviderRequestListener)) {
                return new Proxy(obj);
            }
            return (IProviderRequestListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onProviderRequestChanged";
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
            ProviderRequest _arg1;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IProviderRequestListener.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IProviderRequestListener.DESCRIPTOR);
                            String _arg0 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = ProviderRequest.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            onProviderRequestChanged(_arg0, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IProviderRequestListener {
            public static IProviderRequestListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IProviderRequestListener.DESCRIPTOR;
            }

            @Override // android.location.provider.IProviderRequestListener
            public void onProviderRequestChanged(String provider, ProviderRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IProviderRequestListener.DESCRIPTOR);
                    _data.writeString(provider);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onProviderRequestChanged(provider, request);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IProviderRequestListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IProviderRequestListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
