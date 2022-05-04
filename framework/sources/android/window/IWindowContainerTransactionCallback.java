package android.window;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.SurfaceControl;
/* loaded from: classes3.dex */
public interface IWindowContainerTransactionCallback extends IInterface {
    public static final String DESCRIPTOR = "android.window.IWindowContainerTransactionCallback";

    void onTransactionReady(int i, SurfaceControl.Transaction transaction) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IWindowContainerTransactionCallback {
        @Override // android.window.IWindowContainerTransactionCallback
        public void onTransactionReady(int id, SurfaceControl.Transaction t) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IWindowContainerTransactionCallback {
        static final int TRANSACTION_onTransactionReady = 1;

        public Stub() {
            attachInterface(this, IWindowContainerTransactionCallback.DESCRIPTOR);
        }

        public static IWindowContainerTransactionCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IWindowContainerTransactionCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IWindowContainerTransactionCallback)) {
                return new Proxy(obj);
            }
            return (IWindowContainerTransactionCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onTransactionReady";
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
            SurfaceControl.Transaction _arg1;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IWindowContainerTransactionCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IWindowContainerTransactionCallback.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = SurfaceControl.Transaction.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            onTransactionReady(_arg0, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IWindowContainerTransactionCallback {
            public static IWindowContainerTransactionCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IWindowContainerTransactionCallback.DESCRIPTOR;
            }

            @Override // android.window.IWindowContainerTransactionCallback
            public void onTransactionReady(int id, SurfaceControl.Transaction t) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IWindowContainerTransactionCallback.DESCRIPTOR);
                    _data.writeInt(id);
                    if (t != null) {
                        _data.writeInt(1);
                        t.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTransactionReady(id, t);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWindowContainerTransactionCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IWindowContainerTransactionCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
