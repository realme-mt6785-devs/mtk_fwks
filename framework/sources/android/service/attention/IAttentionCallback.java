package android.service.attention;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IAttentionCallback extends IInterface {
    public static final String DESCRIPTOR = "android.service.attention.IAttentionCallback";

    void onFailure(int i) throws RemoteException;

    void onSuccess(int i, long j) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IAttentionCallback {
        @Override // android.service.attention.IAttentionCallback
        public void onSuccess(int result, long timestamp) throws RemoteException {
        }

        @Override // android.service.attention.IAttentionCallback
        public void onFailure(int error) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IAttentionCallback {
        static final int TRANSACTION_onFailure = 2;
        static final int TRANSACTION_onSuccess = 1;

        public Stub() {
            attachInterface(this, IAttentionCallback.DESCRIPTOR);
        }

        public static IAttentionCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IAttentionCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IAttentionCallback)) {
                return new Proxy(obj);
            }
            return (IAttentionCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onSuccess";
                case 2:
                    return "onFailure";
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
                    reply.writeString(IAttentionCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IAttentionCallback.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            long _arg1 = data.readLong();
                            onSuccess(_arg0, _arg1);
                            return true;
                        case 2:
                            data.enforceInterface(IAttentionCallback.DESCRIPTOR);
                            int _arg02 = data.readInt();
                            onFailure(_arg02);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IAttentionCallback {
            public static IAttentionCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IAttentionCallback.DESCRIPTOR;
            }

            @Override // android.service.attention.IAttentionCallback
            public void onSuccess(int result, long timestamp) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAttentionCallback.DESCRIPTOR);
                    _data.writeInt(result);
                    _data.writeLong(timestamp);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSuccess(result, timestamp);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.attention.IAttentionCallback
            public void onFailure(int error) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAttentionCallback.DESCRIPTOR);
                    _data.writeInt(error);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onFailure(error);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAttentionCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IAttentionCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
