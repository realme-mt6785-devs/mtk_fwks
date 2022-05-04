package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IAppTraceRetriever extends IInterface {
    public static final String DESCRIPTOR = "android.app.IAppTraceRetriever";

    ParcelFileDescriptor getTraceFileDescriptor(String str, int i, int i2) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IAppTraceRetriever {
        @Override // android.app.IAppTraceRetriever
        public ParcelFileDescriptor getTraceFileDescriptor(String packageName, int uid, int pid) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IAppTraceRetriever {
        static final int TRANSACTION_getTraceFileDescriptor = 1;

        public Stub() {
            attachInterface(this, IAppTraceRetriever.DESCRIPTOR);
        }

        public static IAppTraceRetriever asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IAppTraceRetriever.DESCRIPTOR);
            if (iin == null || !(iin instanceof IAppTraceRetriever)) {
                return new Proxy(obj);
            }
            return (IAppTraceRetriever) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getTraceFileDescriptor";
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
                    reply.writeString(IAppTraceRetriever.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IAppTraceRetriever.DESCRIPTOR);
                            String _arg0 = data.readString();
                            int _arg1 = data.readInt();
                            int _arg2 = data.readInt();
                            ParcelFileDescriptor _result = getTraceFileDescriptor(_arg0, _arg1, _arg2);
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
        public static class Proxy implements IAppTraceRetriever {
            public static IAppTraceRetriever sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IAppTraceRetriever.DESCRIPTOR;
            }

            @Override // android.app.IAppTraceRetriever
            public ParcelFileDescriptor getTraceFileDescriptor(String packageName, int uid, int pid) throws RemoteException {
                ParcelFileDescriptor _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IAppTraceRetriever.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(uid);
                    _data.writeInt(pid);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTraceFileDescriptor(packageName, uid, pid);
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

        public static boolean setDefaultImpl(IAppTraceRetriever impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IAppTraceRetriever getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
