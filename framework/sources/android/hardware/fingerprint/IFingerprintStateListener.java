package android.hardware.fingerprint;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IFingerprintStateListener extends IInterface {
    public static final String DESCRIPTOR = "android.hardware.fingerprint.IFingerprintStateListener";

    void onStateChanged(int i) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IFingerprintStateListener {
        @Override // android.hardware.fingerprint.IFingerprintStateListener
        public void onStateChanged(int newState) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IFingerprintStateListener {
        static final int TRANSACTION_onStateChanged = 1;

        public Stub() {
            attachInterface(this, IFingerprintStateListener.DESCRIPTOR);
        }

        public static IFingerprintStateListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IFingerprintStateListener.DESCRIPTOR);
            if (iin == null || !(iin instanceof IFingerprintStateListener)) {
                return new Proxy(obj);
            }
            return (IFingerprintStateListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onStateChanged";
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
                    reply.writeString(IFingerprintStateListener.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IFingerprintStateListener.DESCRIPTOR);
                            int _arg0 = data.readInt();
                            onStateChanged(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IFingerprintStateListener {
            public static IFingerprintStateListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IFingerprintStateListener.DESCRIPTOR;
            }

            @Override // android.hardware.fingerprint.IFingerprintStateListener
            public void onStateChanged(int newState) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IFingerprintStateListener.DESCRIPTOR);
                    _data.writeInt(newState);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onStateChanged(newState);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IFingerprintStateListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IFingerprintStateListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
