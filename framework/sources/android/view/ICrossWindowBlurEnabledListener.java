package android.view;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface ICrossWindowBlurEnabledListener extends IInterface {
    public static final String DESCRIPTOR = "android.view.ICrossWindowBlurEnabledListener";

    void onCrossWindowBlurEnabledChanged(boolean z) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements ICrossWindowBlurEnabledListener {
        @Override // android.view.ICrossWindowBlurEnabledListener
        public void onCrossWindowBlurEnabledChanged(boolean enabled) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements ICrossWindowBlurEnabledListener {
        static final int TRANSACTION_onCrossWindowBlurEnabledChanged = 1;

        public Stub() {
            attachInterface(this, ICrossWindowBlurEnabledListener.DESCRIPTOR);
        }

        public static ICrossWindowBlurEnabledListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ICrossWindowBlurEnabledListener.DESCRIPTOR);
            if (iin == null || !(iin instanceof ICrossWindowBlurEnabledListener)) {
                return new Proxy(obj);
            }
            return (ICrossWindowBlurEnabledListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onCrossWindowBlurEnabledChanged";
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
                    reply.writeString(ICrossWindowBlurEnabledListener.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ICrossWindowBlurEnabledListener.DESCRIPTOR);
                            boolean _arg0 = data.readInt() != 0;
                            onCrossWindowBlurEnabledChanged(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements ICrossWindowBlurEnabledListener {
            public static ICrossWindowBlurEnabledListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ICrossWindowBlurEnabledListener.DESCRIPTOR;
            }

            @Override // android.view.ICrossWindowBlurEnabledListener
            public void onCrossWindowBlurEnabledChanged(boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ICrossWindowBlurEnabledListener.DESCRIPTOR);
                    _data.writeInt(enabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCrossWindowBlurEnabledChanged(enabled);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICrossWindowBlurEnabledListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ICrossWindowBlurEnabledListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
