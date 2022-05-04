package android.os.incremental;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IStorageHealthListener extends IInterface {
    public static final String DESCRIPTOR = "android.os.incremental.IStorageHealthListener";
    public static final int HEALTH_STATUS_BLOCKED = 2;
    public static final int HEALTH_STATUS_OK = 0;
    public static final int HEALTH_STATUS_READS_PENDING = 1;
    public static final int HEALTH_STATUS_UNHEALTHY = 3;

    void onHealthStatus(int i, int i2) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IStorageHealthListener {
        @Override // android.os.incremental.IStorageHealthListener
        public void onHealthStatus(int storageId, int status) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IStorageHealthListener {
        static final int TRANSACTION_onHealthStatus = 1;

        public Stub() {
            attachInterface(this, IStorageHealthListener.DESCRIPTOR);
        }

        public static IStorageHealthListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IStorageHealthListener.DESCRIPTOR);
            if (iin == null || !(iin instanceof IStorageHealthListener)) {
                return new Proxy(obj);
            }
            return (IStorageHealthListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onHealthStatus";
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
                    reply.writeString(IStorageHealthListener.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IStorageHealthListener.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            int _arg1 = data.readInt();
                            onHealthStatus(_arg0, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IStorageHealthListener {
            public static IStorageHealthListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IStorageHealthListener.DESCRIPTOR;
            }

            @Override // android.os.incremental.IStorageHealthListener
            public void onHealthStatus(int storageId, int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IStorageHealthListener.DESCRIPTOR);
                    _data.writeInt(storageId);
                    _data.writeInt(status);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onHealthStatus(storageId, status);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IStorageHealthListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IStorageHealthListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
