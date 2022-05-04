package android.app.admin;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.SurfaceControlViewHost;
/* loaded from: classes.dex */
public interface IKeyguardCallback extends IInterface {
    public static final String DESCRIPTOR = "android.app.admin.IKeyguardCallback";

    void onDismiss() throws RemoteException;

    void onRemoteContentReady(SurfaceControlViewHost.SurfacePackage surfacePackage) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IKeyguardCallback {
        @Override // android.app.admin.IKeyguardCallback
        public void onRemoteContentReady(SurfaceControlViewHost.SurfacePackage surfacePackage) throws RemoteException {
        }

        @Override // android.app.admin.IKeyguardCallback
        public void onDismiss() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IKeyguardCallback {
        static final int TRANSACTION_onDismiss = 2;
        static final int TRANSACTION_onRemoteContentReady = 1;

        public Stub() {
            attachInterface(this, IKeyguardCallback.DESCRIPTOR);
        }

        public static IKeyguardCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IKeyguardCallback.DESCRIPTOR);
            if (iin == null || !(iin instanceof IKeyguardCallback)) {
                return new Proxy(obj);
            }
            return (IKeyguardCallback) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onRemoteContentReady";
                case 2:
                    return "onDismiss";
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
                    reply.writeString(IKeyguardCallback.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IKeyguardCallback.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = SurfaceControlViewHost.SurfacePackage.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onRemoteContentReady(_arg0);
                            return true;
                        case 2:
                            data.enforceInterface(IKeyguardCallback.DESCRIPTOR);
                            onDismiss();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IKeyguardCallback {
            public static IKeyguardCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IKeyguardCallback.DESCRIPTOR;
            }

            @Override // android.app.admin.IKeyguardCallback
            public void onRemoteContentReady(SurfaceControlViewHost.SurfacePackage surfacePackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IKeyguardCallback.DESCRIPTOR);
                    if (surfacePackage != null) {
                        _data.writeInt(1);
                        surfacePackage.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRemoteContentReady(surfacePackage);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.admin.IKeyguardCallback
            public void onDismiss() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IKeyguardCallback.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDismiss();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IKeyguardCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IKeyguardCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
