package com.oplus.favorite;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes4.dex */
public interface IOplusFavoriteQueryCallback extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.favorite.IOplusFavoriteQueryCallback";

    void onQueryResult(OplusFavoriteQueryResult oplusFavoriteQueryResult) throws RemoteException;

    /* loaded from: classes4.dex */
    public static class Default implements IOplusFavoriteQueryCallback {
        @Override // com.oplus.favorite.IOplusFavoriteQueryCallback
        public void onQueryResult(OplusFavoriteQueryResult result) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class Stub extends Binder implements IOplusFavoriteQueryCallback {
        static final int TRANSACTION_onQueryResult = 1;

        public Stub() {
            attachInterface(this, IOplusFavoriteQueryCallback.DESCRIPTOR);
        }

        public static IOplusFavoriteQueryCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusFavoriteQueryCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusFavoriteQueryCallback)) {
                return new Proxy(obj);
            }
            return (IOplusFavoriteQueryCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onQueryResult";
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
            OplusFavoriteQueryResult _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IOplusFavoriteQueryCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusFavoriteQueryCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = OplusFavoriteQueryResult.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onQueryResult(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes4.dex */
        public static class Proxy implements IOplusFavoriteQueryCallback {
            public static IOplusFavoriteQueryCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusFavoriteQueryCallback.DESCRIPTOR;
            }

            @Override // com.oplus.favorite.IOplusFavoriteQueryCallback
            public void onQueryResult(OplusFavoriteQueryResult result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusFavoriteQueryCallback.DESCRIPTOR);
                    if (result != null) {
                        _data.writeInt(1);
                        result.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onQueryResult(result);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusFavoriteQueryCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusFavoriteQueryCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
