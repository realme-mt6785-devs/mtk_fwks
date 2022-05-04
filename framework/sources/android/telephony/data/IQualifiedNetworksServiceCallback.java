package android.telephony.data;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IQualifiedNetworksServiceCallback extends IInterface {
    public static final String DESCRIPTOR = "android.telephony.data.IQualifiedNetworksServiceCallback";

    void onQualifiedNetworkTypesChanged(int i, int[] iArr) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IQualifiedNetworksServiceCallback {
        @Override // android.telephony.data.IQualifiedNetworksServiceCallback
        public void onQualifiedNetworkTypesChanged(int apnTypes, int[] qualifiedNetworkTypes) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IQualifiedNetworksServiceCallback {
        static final int TRANSACTION_onQualifiedNetworkTypesChanged = 1;

        public Stub() {
            attachInterface(this, IQualifiedNetworksServiceCallback.DESCRIPTOR);
        }

        public static IQualifiedNetworksServiceCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IQualifiedNetworksServiceCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IQualifiedNetworksServiceCallback)) {
                return new Proxy(obj);
            }
            return (IQualifiedNetworksServiceCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onQualifiedNetworkTypesChanged";
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
                    reply.writeString(IQualifiedNetworksServiceCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IQualifiedNetworksServiceCallback.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            int[] _arg1 = data.createIntArray();
                            onQualifiedNetworkTypesChanged(_arg0, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IQualifiedNetworksServiceCallback {
            public static IQualifiedNetworksServiceCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IQualifiedNetworksServiceCallback.DESCRIPTOR;
            }

            @Override // android.telephony.data.IQualifiedNetworksServiceCallback
            public void onQualifiedNetworkTypesChanged(int apnTypes, int[] qualifiedNetworkTypes) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IQualifiedNetworksServiceCallback.DESCRIPTOR);
                    _data.writeInt(apnTypes);
                    _data.writeIntArray(qualifiedNetworkTypes);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onQualifiedNetworkTypesChanged(apnTypes, qualifiedNetworkTypes);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IQualifiedNetworksServiceCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IQualifiedNetworksServiceCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
