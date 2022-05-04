package android.uwb;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IUwbAdapterStateCallbacks extends IInterface {
    public static final String DESCRIPTOR = "android.uwb.IUwbAdapterStateCallbacks";

    void onAdapterStateChanged(int i, int i2) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IUwbAdapterStateCallbacks {
        @Override // android.uwb.IUwbAdapterStateCallbacks
        public void onAdapterStateChanged(int state, int reason) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IUwbAdapterStateCallbacks {
        static final int TRANSACTION_onAdapterStateChanged = 1;

        public Stub() {
            attachInterface(this, IUwbAdapterStateCallbacks.DESCRIPTOR);
        }

        public static IUwbAdapterStateCallbacks asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IUwbAdapterStateCallbacks.DESCRIPTOR);
            if (iin == null || !(iin instanceof IUwbAdapterStateCallbacks)) {
                return new Proxy(obj);
            }
            return (IUwbAdapterStateCallbacks) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onAdapterStateChanged";
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
                    reply.writeString(IUwbAdapterStateCallbacks.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IUwbAdapterStateCallbacks.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            int _arg1 = data.readInt();
                            onAdapterStateChanged(_arg0, _arg1);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IUwbAdapterStateCallbacks {
            public static IUwbAdapterStateCallbacks sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IUwbAdapterStateCallbacks.DESCRIPTOR;
            }

            @Override // android.uwb.IUwbAdapterStateCallbacks
            public void onAdapterStateChanged(int state, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IUwbAdapterStateCallbacks.DESCRIPTOR);
                    _data.writeInt(state);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onAdapterStateChanged(state, reason);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IUwbAdapterStateCallbacks impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IUwbAdapterStateCallbacks getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
