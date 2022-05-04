package android.media;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IResourceManagerClient extends IInterface {
    public static final String DESCRIPTOR = "android.media.IResourceManagerClient";

    String getName() throws RemoteException;

    boolean reclaimResource() throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IResourceManagerClient {
        @Override // android.media.IResourceManagerClient
        public boolean reclaimResource() throws RemoteException {
            return false;
        }

        @Override // android.media.IResourceManagerClient
        public String getName() throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IResourceManagerClient {
        static final int TRANSACTION_getName = 2;
        static final int TRANSACTION_reclaimResource = 1;

        public Stub() {
            attachInterface(this, IResourceManagerClient.DESCRIPTOR);
        }

        public static IResourceManagerClient asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IResourceManagerClient.DESCRIPTOR);
            if (iin == null || !(iin instanceof IResourceManagerClient)) {
                return new Proxy(obj);
            }
            return (IResourceManagerClient) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "reclaimResource";
                case 2:
                    return "getName";
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
                    reply.writeString(IResourceManagerClient.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IResourceManagerClient.DESCRIPTOR);
                            boolean reclaimResource = reclaimResource();
                            reply.writeNoException();
                            reply.writeInt(reclaimResource ? 1 : 0);
                            return true;
                        case 2:
                            data.enforceInterface(IResourceManagerClient.DESCRIPTOR);
                            String _result = getName();
                            reply.writeNoException();
                            reply.writeString(_result);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IResourceManagerClient {
            public static IResourceManagerClient sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IResourceManagerClient.DESCRIPTOR;
            }

            @Override // android.media.IResourceManagerClient
            public boolean reclaimResource() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IResourceManagerClient.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reclaimResource();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IResourceManagerClient
            public String getName() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IResourceManagerClient.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getName();
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

        public static boolean setDefaultImpl(IResourceManagerClient impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IResourceManagerClient getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
