package android.service.autofill;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.SurfaceControlViewHost;
/* loaded from: classes3.dex */
public interface ISurfacePackageResultCallback extends IInterface {
    public static final String DESCRIPTOR = "android.service.autofill.ISurfacePackageResultCallback";

    void onResult(SurfaceControlViewHost.SurfacePackage surfacePackage) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements ISurfacePackageResultCallback {
        @Override // android.service.autofill.ISurfacePackageResultCallback
        public void onResult(SurfaceControlViewHost.SurfacePackage result) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements ISurfacePackageResultCallback {
        static final int TRANSACTION_onResult = 1;

        public Stub() {
            attachInterface(this, ISurfacePackageResultCallback.DESCRIPTOR);
        }

        public static ISurfacePackageResultCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ISurfacePackageResultCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof ISurfacePackageResultCallback)) {
                return new Proxy(obj);
            }
            return (ISurfacePackageResultCallback) iin;
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
            SurfaceControlViewHost.SurfacePackage _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ISurfacePackageResultCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ISurfacePackageResultCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = SurfaceControlViewHost.SurfacePackage.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onResult(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements ISurfacePackageResultCallback {
            public static ISurfacePackageResultCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISurfacePackageResultCallback.DESCRIPTOR;
            }

            @Override // android.service.autofill.ISurfacePackageResultCallback
            public void onResult(SurfaceControlViewHost.SurfacePackage result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISurfacePackageResultCallback.DESCRIPTOR);
                    if (result != null) {
                        _data.writeInt(1);
                        result.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onResult(result);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISurfacePackageResultCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ISurfacePackageResultCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
