package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IParcelFileDescriptorRetriever extends IInterface {
    public static final String DESCRIPTOR = "android.app.IParcelFileDescriptorRetriever";

    ParcelFileDescriptor getPfd() throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IParcelFileDescriptorRetriever {
        @Override // android.app.IParcelFileDescriptorRetriever
        public ParcelFileDescriptor getPfd() throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IParcelFileDescriptorRetriever {
        static final int TRANSACTION_getPfd = 1;

        public Stub() {
            attachInterface(this, IParcelFileDescriptorRetriever.DESCRIPTOR);
        }

        public static IParcelFileDescriptorRetriever asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IParcelFileDescriptorRetriever.DESCRIPTOR);
            if (iin == null || !(iin instanceof IParcelFileDescriptorRetriever)) {
                return new Proxy(obj);
            }
            return (IParcelFileDescriptorRetriever) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getPfd";
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
                    reply.writeString(IParcelFileDescriptorRetriever.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IParcelFileDescriptorRetriever.DESCRIPTOR);
                            ParcelFileDescriptor _result = getPfd();
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IParcelFileDescriptorRetriever {
            public static IParcelFileDescriptorRetriever sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IParcelFileDescriptorRetriever.DESCRIPTOR;
            }

            @Override // android.app.IParcelFileDescriptorRetriever
            public ParcelFileDescriptor getPfd() throws RemoteException {
                ParcelFileDescriptor _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IParcelFileDescriptorRetriever.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPfd();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParcelFileDescriptor.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IParcelFileDescriptorRetriever impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IParcelFileDescriptorRetriever getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
