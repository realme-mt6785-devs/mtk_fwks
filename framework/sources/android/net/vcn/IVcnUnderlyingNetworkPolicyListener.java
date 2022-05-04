package android.net.vcn;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IVcnUnderlyingNetworkPolicyListener extends IInterface {
    public static final String DESCRIPTOR = "android.net.vcn.IVcnUnderlyingNetworkPolicyListener";

    void onPolicyChanged() throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IVcnUnderlyingNetworkPolicyListener {
        @Override // android.net.vcn.IVcnUnderlyingNetworkPolicyListener
        public void onPolicyChanged() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IVcnUnderlyingNetworkPolicyListener {
        static final int TRANSACTION_onPolicyChanged = 1;

        public Stub() {
            attachInterface(this, IVcnUnderlyingNetworkPolicyListener.DESCRIPTOR);
        }

        public static IVcnUnderlyingNetworkPolicyListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IVcnUnderlyingNetworkPolicyListener.DESCRIPTOR);
            if (iin == null || !(iin instanceof IVcnUnderlyingNetworkPolicyListener)) {
                return new Proxy(obj);
            }
            return (IVcnUnderlyingNetworkPolicyListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onPolicyChanged";
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
                    reply.writeString(IVcnUnderlyingNetworkPolicyListener.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IVcnUnderlyingNetworkPolicyListener.DESCRIPTOR);
                            onPolicyChanged();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IVcnUnderlyingNetworkPolicyListener {
            public static IVcnUnderlyingNetworkPolicyListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IVcnUnderlyingNetworkPolicyListener.DESCRIPTOR;
            }

            @Override // android.net.vcn.IVcnUnderlyingNetworkPolicyListener
            public void onPolicyChanged() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVcnUnderlyingNetworkPolicyListener.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPolicyChanged();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IVcnUnderlyingNetworkPolicyListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IVcnUnderlyingNetworkPolicyListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
