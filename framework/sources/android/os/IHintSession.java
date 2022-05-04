package android.os;
/* loaded from: classes2.dex */
public interface IHintSession extends IInterface {
    public static final String DESCRIPTOR = "android.os.IHintSession";

    void close() throws RemoteException;

    void reportActualWorkDuration(long[] jArr, long[] jArr2) throws RemoteException;

    void updateTargetWorkDuration(long j) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IHintSession {
        @Override // android.os.IHintSession
        public void updateTargetWorkDuration(long targetDurationNanos) throws RemoteException {
        }

        @Override // android.os.IHintSession
        public void reportActualWorkDuration(long[] actualDurationNanos, long[] timeStampNanos) throws RemoteException {
        }

        @Override // android.os.IHintSession
        public void close() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IHintSession {
        static final int TRANSACTION_close = 3;
        static final int TRANSACTION_reportActualWorkDuration = 2;
        static final int TRANSACTION_updateTargetWorkDuration = 1;

        public Stub() {
            attachInterface(this, IHintSession.DESCRIPTOR);
        }

        public static IHintSession asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IHintSession.DESCRIPTOR);
            if (iin == null || !(iin instanceof IHintSession)) {
                return new Proxy(obj);
            }
            return (IHintSession) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "updateTargetWorkDuration";
                case 2:
                    return "reportActualWorkDuration";
                case 3:
                    return "close";
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
                    reply.writeString(IHintSession.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IHintSession.DESCRIPTOR);
                            long _arg0 = data.readLong();
                            updateTargetWorkDuration(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(IHintSession.DESCRIPTOR);
                            long[] _arg02 = data.createLongArray();
                            long[] _arg1 = data.createLongArray();
                            reportActualWorkDuration(_arg02, _arg1);
                            return true;
                        case 3:
                            data.enforceInterface(IHintSession.DESCRIPTOR);
                            close();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IHintSession {
            public static IHintSession sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IHintSession.DESCRIPTOR;
            }

            @Override // android.os.IHintSession
            public void updateTargetWorkDuration(long targetDurationNanos) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IHintSession.DESCRIPTOR);
                    _data.writeLong(targetDurationNanos);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateTargetWorkDuration(targetDurationNanos);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.os.IHintSession
            public void reportActualWorkDuration(long[] actualDurationNanos, long[] timeStampNanos) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IHintSession.DESCRIPTOR);
                    _data.writeLongArray(actualDurationNanos);
                    _data.writeLongArray(timeStampNanos);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportActualWorkDuration(actualDurationNanos, timeStampNanos);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.os.IHintSession
            public void close() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IHintSession.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().close();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IHintSession impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IHintSession getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
