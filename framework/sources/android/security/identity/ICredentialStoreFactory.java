package android.security.identity;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.security.identity.ICredentialStore;
/* loaded from: classes2.dex */
public interface ICredentialStoreFactory extends IInterface {
    public static final int CREDENTIAL_STORE_TYPE_DEFAULT = 0;
    public static final int CREDENTIAL_STORE_TYPE_DIRECT_ACCESS = 1;
    public static final String DESCRIPTOR = "android.security.identity.ICredentialStoreFactory";

    ICredentialStore getCredentialStore(int i) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements ICredentialStoreFactory {
        @Override // android.security.identity.ICredentialStoreFactory
        public ICredentialStore getCredentialStore(int credentialStoreType) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements ICredentialStoreFactory {
        static final int TRANSACTION_getCredentialStore = 1;

        public Stub() {
            attachInterface(this, ICredentialStoreFactory.DESCRIPTOR);
        }

        public static ICredentialStoreFactory asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ICredentialStoreFactory.DESCRIPTOR);
            if (iin == null || !(iin instanceof ICredentialStoreFactory)) {
                return new Proxy(obj);
            }
            return (ICredentialStoreFactory) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getCredentialStore";
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
                    reply.writeString(ICredentialStoreFactory.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ICredentialStoreFactory.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            ICredentialStore _result = getCredentialStore(_arg0);
                            reply.writeNoException();
                            reply.writeStrongBinder(_result != null ? _result.asBinder() : null);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements ICredentialStoreFactory {
            public static ICredentialStoreFactory sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ICredentialStoreFactory.DESCRIPTOR;
            }

            @Override // android.security.identity.ICredentialStoreFactory
            public ICredentialStore getCredentialStore(int credentialStoreType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICredentialStoreFactory.DESCRIPTOR);
                    _data.writeInt(credentialStoreType);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCredentialStore(credentialStoreType);
                    }
                    _reply.readException();
                    ICredentialStore _result = ICredentialStore.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICredentialStoreFactory impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ICredentialStoreFactory getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
