package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface IPackageLoadingProgressCallback extends IInterface {
    public static final String DESCRIPTOR = "android.content.pm.IPackageLoadingProgressCallback";

    void onPackageLoadingProgressChanged(float f) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IPackageLoadingProgressCallback {
        @Override // android.content.pm.IPackageLoadingProgressCallback
        public void onPackageLoadingProgressChanged(float progress) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IPackageLoadingProgressCallback {
        static final int TRANSACTION_onPackageLoadingProgressChanged = 1;

        public Stub() {
            attachInterface(this, IPackageLoadingProgressCallback.DESCRIPTOR);
        }

        public static IPackageLoadingProgressCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IPackageLoadingProgressCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IPackageLoadingProgressCallback)) {
                return new Proxy(obj);
            }
            return (IPackageLoadingProgressCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onPackageLoadingProgressChanged";
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
                    reply.writeString(IPackageLoadingProgressCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IPackageLoadingProgressCallback.DESCRIPTOR);
                            float _arg0 = data.readFloat();
                            onPackageLoadingProgressChanged(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IPackageLoadingProgressCallback {
            public static IPackageLoadingProgressCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IPackageLoadingProgressCallback.DESCRIPTOR;
            }

            @Override // android.content.pm.IPackageLoadingProgressCallback
            public void onPackageLoadingProgressChanged(float progress) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IPackageLoadingProgressCallback.DESCRIPTOR);
                    _data.writeFloat(progress);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPackageLoadingProgressChanged(progress);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPackageLoadingProgressCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IPackageLoadingProgressCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
