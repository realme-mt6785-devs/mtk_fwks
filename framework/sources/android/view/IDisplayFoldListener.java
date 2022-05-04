package android.view;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IDisplayFoldListener extends IInterface {
    public static final String DESCRIPTOR = "android.view.IDisplayFoldListener";

    void onDisplayFoldChanged(int i, boolean z) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IDisplayFoldListener {
        @Override // android.view.IDisplayFoldListener
        public void onDisplayFoldChanged(int displayId, boolean folded) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IDisplayFoldListener {
        static final int TRANSACTION_onDisplayFoldChanged = 1;

        public Stub() {
            attachInterface(this, IDisplayFoldListener.DESCRIPTOR);
        }

        public static IDisplayFoldListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IDisplayFoldListener.DESCRIPTOR);
            if (iin == null || !(iin instanceof IDisplayFoldListener)) {
                return new Proxy(obj);
            }
            return (IDisplayFoldListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onDisplayFoldChanged";
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
                    reply.writeString(IDisplayFoldListener.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IDisplayFoldListener.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            boolean _arg1 = data.readInt() != 0;
                            onDisplayFoldChanged(_arg0, _arg1);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IDisplayFoldListener {
            public static IDisplayFoldListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IDisplayFoldListener.DESCRIPTOR;
            }

            @Override // android.view.IDisplayFoldListener
            public void onDisplayFoldChanged(int displayId, boolean folded) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IDisplayFoldListener.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeInt(folded ? 1 : 0);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDisplayFoldChanged(displayId, folded);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDisplayFoldListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IDisplayFoldListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
