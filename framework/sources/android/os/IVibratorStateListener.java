package android.os;
/* loaded from: classes2.dex */
public interface IVibratorStateListener extends IInterface {
    public static final String DESCRIPTOR = "android.os.IVibratorStateListener";

    void onVibrating(boolean z) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IVibratorStateListener {
        @Override // android.os.IVibratorStateListener
        public void onVibrating(boolean vibrating) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IVibratorStateListener {
        static final int TRANSACTION_onVibrating = 1;

        public Stub() {
            attachInterface(this, IVibratorStateListener.DESCRIPTOR);
        }

        public static IVibratorStateListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IVibratorStateListener.DESCRIPTOR);
            if (iin == null || !(iin instanceof IVibratorStateListener)) {
                return new Proxy(obj);
            }
            return (IVibratorStateListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onVibrating";
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
                    reply.writeString(IVibratorStateListener.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IVibratorStateListener.DESCRIPTOR);
                            boolean _arg0 = data.readInt() != 0;
                            onVibrating(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IVibratorStateListener {
            public static IVibratorStateListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IVibratorStateListener.DESCRIPTOR;
            }

            @Override // android.os.IVibratorStateListener
            public void onVibrating(boolean vibrating) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IVibratorStateListener.DESCRIPTOR);
                    _data.writeInt(vibrating ? 1 : 0);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onVibrating(vibrating);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IVibratorStateListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IVibratorStateListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
