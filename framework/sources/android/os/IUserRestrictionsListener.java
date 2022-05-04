package android.os;
/* loaded from: classes2.dex */
public interface IUserRestrictionsListener extends IInterface {
    public static final String DESCRIPTOR = "android.os.IUserRestrictionsListener";

    void onUserRestrictionsChanged(int i, Bundle bundle, Bundle bundle2) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IUserRestrictionsListener {
        @Override // android.os.IUserRestrictionsListener
        public void onUserRestrictionsChanged(int userId, Bundle newRestrictions, Bundle prevRestrictions) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IUserRestrictionsListener {
        static final int TRANSACTION_onUserRestrictionsChanged = 1;

        public Stub() {
            attachInterface(this, IUserRestrictionsListener.DESCRIPTOR);
        }

        public static IUserRestrictionsListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IUserRestrictionsListener.DESCRIPTOR);
            if (iin == null || !(iin instanceof IUserRestrictionsListener)) {
                return new Proxy(obj);
            }
            return (IUserRestrictionsListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onUserRestrictionsChanged";
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
            Bundle _arg1;
            Bundle _arg2;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IUserRestrictionsListener.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IUserRestrictionsListener.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg2 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            onUserRestrictionsChanged(_arg0, _arg1, _arg2);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IUserRestrictionsListener {
            public static IUserRestrictionsListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IUserRestrictionsListener.DESCRIPTOR;
            }

            @Override // android.os.IUserRestrictionsListener
            public void onUserRestrictionsChanged(int userId, Bundle newRestrictions, Bundle prevRestrictions) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUserRestrictionsListener.DESCRIPTOR);
                    _data.writeInt(userId);
                    if (newRestrictions != null) {
                        _data.writeInt(1);
                        newRestrictions.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (prevRestrictions != null) {
                        _data.writeInt(1);
                        prevRestrictions.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onUserRestrictionsChanged(userId, newRestrictions, prevRestrictions);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IUserRestrictionsListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IUserRestrictionsListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
