package android.view.accessibility;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IRemoteMagnificationAnimationCallback extends IInterface {
    public static final String DESCRIPTOR = "android.view.accessibility.IRemoteMagnificationAnimationCallback";

    void onResult(boolean z) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IRemoteMagnificationAnimationCallback {
        @Override // android.view.accessibility.IRemoteMagnificationAnimationCallback
        public void onResult(boolean success) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IRemoteMagnificationAnimationCallback {
        static final int TRANSACTION_onResult = 1;

        public Stub() {
            attachInterface(this, IRemoteMagnificationAnimationCallback.DESCRIPTOR);
        }

        public static IRemoteMagnificationAnimationCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IRemoteMagnificationAnimationCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IRemoteMagnificationAnimationCallback)) {
                return new Proxy(obj);
            }
            return (IRemoteMagnificationAnimationCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onResult";
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
                    reply.writeString(IRemoteMagnificationAnimationCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IRemoteMagnificationAnimationCallback.DESCRIPTOR);
                            boolean _arg0 = data.readInt() != 0;
                            onResult(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IRemoteMagnificationAnimationCallback {
            public static IRemoteMagnificationAnimationCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IRemoteMagnificationAnimationCallback.DESCRIPTOR;
            }

            @Override // android.view.accessibility.IRemoteMagnificationAnimationCallback
            public void onResult(boolean success) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IRemoteMagnificationAnimationCallback.DESCRIPTOR);
                    _data.writeInt(success ? 1 : 0);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onResult(success);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRemoteMagnificationAnimationCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IRemoteMagnificationAnimationCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
