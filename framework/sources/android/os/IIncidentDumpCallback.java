package android.os;
/* loaded from: classes2.dex */
public interface IIncidentDumpCallback extends IInterface {
    public static final String DESCRIPTOR = "android.os.IIncidentDumpCallback";

    void onDumpSection(ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IIncidentDumpCallback {
        @Override // android.os.IIncidentDumpCallback
        public void onDumpSection(ParcelFileDescriptor fd) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IIncidentDumpCallback {
        static final int TRANSACTION_onDumpSection = 1;

        public Stub() {
            attachInterface(this, IIncidentDumpCallback.DESCRIPTOR);
        }

        public static IIncidentDumpCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IIncidentDumpCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IIncidentDumpCallback)) {
                return new Proxy(obj);
            }
            return (IIncidentDumpCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onDumpSection";
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
            ParcelFileDescriptor _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IIncidentDumpCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IIncidentDumpCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = ParcelFileDescriptor.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onDumpSection(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IIncidentDumpCallback {
            public static IIncidentDumpCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IIncidentDumpCallback.DESCRIPTOR;
            }

            @Override // android.os.IIncidentDumpCallback
            public void onDumpSection(ParcelFileDescriptor fd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IIncidentDumpCallback.DESCRIPTOR);
                    if (fd != null) {
                        _data.writeInt(1);
                        fd.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDumpSection(fd);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IIncidentDumpCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IIncidentDumpCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
