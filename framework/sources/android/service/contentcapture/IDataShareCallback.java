package android.service.contentcapture;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.contentcapture.IDataShareReadAdapter;
/* loaded from: classes3.dex */
public interface IDataShareCallback extends IInterface {
    public static final String DESCRIPTOR = "android.service.contentcapture.IDataShareCallback";

    void accept(IDataShareReadAdapter iDataShareReadAdapter) throws RemoteException;

    void reject() throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IDataShareCallback {
        @Override // android.service.contentcapture.IDataShareCallback
        public void accept(IDataShareReadAdapter adapter) throws RemoteException {
        }

        @Override // android.service.contentcapture.IDataShareCallback
        public void reject() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IDataShareCallback {
        static final int TRANSACTION_accept = 1;
        static final int TRANSACTION_reject = 2;

        public Stub() {
            attachInterface(this, IDataShareCallback.DESCRIPTOR);
        }

        public static IDataShareCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IDataShareCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IDataShareCallback)) {
                return new Proxy(obj);
            }
            return (IDataShareCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "accept";
                case 2:
                    return "reject";
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
                    reply.writeString(IDataShareCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IDataShareCallback.DESCRIPTOR);
                            IDataShareReadAdapter _arg0 = IDataShareReadAdapter.Stub.asInterface(data.readStrongBinder());
                            accept(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(IDataShareCallback.DESCRIPTOR);
                            reject();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IDataShareCallback {
            public static IDataShareCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IDataShareCallback.DESCRIPTOR;
            }

            @Override // android.service.contentcapture.IDataShareCallback
            public void accept(IDataShareReadAdapter adapter) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDataShareCallback.DESCRIPTOR);
                    _data.writeStrongBinder(adapter != null ? adapter.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().accept(adapter);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.service.contentcapture.IDataShareCallback
            public void reject() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDataShareCallback.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reject();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDataShareCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IDataShareCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
