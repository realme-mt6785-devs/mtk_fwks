package android.os;
/* loaded from: classes2.dex */
public interface IDeviceIdentifiersPolicyService extends IInterface {
    String getSerial() throws RemoteException;

    String getSerialForPackage(String str, String str2) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IDeviceIdentifiersPolicyService {
        @Override // android.os.IDeviceIdentifiersPolicyService
        public String getSerial() throws RemoteException {
            return null;
        }

        @Override // android.os.IDeviceIdentifiersPolicyService
        public String getSerialForPackage(String callingPackage, String callingFeatureId) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IDeviceIdentifiersPolicyService {
        public static final String DESCRIPTOR = "android.os.IDeviceIdentifiersPolicyService";
        static final int TRANSACTION_getSerial = 1;
        static final int TRANSACTION_getSerialForPackage = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDeviceIdentifiersPolicyService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IDeviceIdentifiersPolicyService)) {
                return new Proxy(obj);
            }
            return (IDeviceIdentifiersPolicyService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getSerial";
                case 2:
                    return "getSerialForPackage";
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
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            String _result = getSerial();
                            reply.writeNoException();
                            reply.writeString(_result);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg0 = data.readString();
                            String _arg1 = data.readString();
                            String _result2 = getSerialForPackage(_arg0, _arg1);
                            reply.writeNoException();
                            reply.writeString(_result2);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IDeviceIdentifiersPolicyService {
            public static IDeviceIdentifiersPolicyService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.os.IDeviceIdentifiersPolicyService
            public String getSerial() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSerial();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.os.IDeviceIdentifiersPolicyService
            public String getSerialForPackage(String callingPackage, String callingFeatureId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackage);
                    _data.writeString(callingFeatureId);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSerialForPackage(callingPackage, callingFeatureId);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDeviceIdentifiersPolicyService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IDeviceIdentifiersPolicyService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
