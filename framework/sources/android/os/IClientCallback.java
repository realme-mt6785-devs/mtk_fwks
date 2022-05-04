package android.os;
/* loaded from: classes2.dex */
public interface IClientCallback extends IInterface {
    public static final String DESCRIPTOR = "android.os.IClientCallback";

    void onClients(IBinder iBinder, boolean z) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IClientCallback {
        @Override // android.os.IClientCallback
        public void onClients(IBinder registered, boolean hasClients) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IClientCallback {
        static final int TRANSACTION_onClients = 1;

        public Stub() {
            attachInterface(this, IClientCallback.DESCRIPTOR);
        }

        public static IClientCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IClientCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IClientCallback)) {
                return new Proxy(obj);
            }
            return (IClientCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onClients";
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
                    reply.writeString(IClientCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IClientCallback.DESCRIPTOR);
                            IBinder _arg0 = data.readStrongBinder();
                            boolean _arg1 = data.readInt() != 0;
                            onClients(_arg0, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IClientCallback {
            public static IClientCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IClientCallback.DESCRIPTOR;
            }

            @Override // android.os.IClientCallback
            public void onClients(IBinder registered, boolean hasClients) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IClientCallback.DESCRIPTOR);
                    _data.writeStrongBinder(registered);
                    _data.writeInt(hasClients ? 1 : 0);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onClients(registered, hasClients);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IClientCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IClientCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
