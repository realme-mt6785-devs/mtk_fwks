package android.hardware.fingerprint;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.ThreadedRenderer;
/* loaded from: classes.dex */
public interface ISidefpsController extends IInterface {
    public static final String DESCRIPTOR = "android.hardware.fingerprint.ISidefpsController";

    void hide() throws RemoteException;

    void show() throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ISidefpsController {
        @Override // android.hardware.fingerprint.ISidefpsController
        public void show() throws RemoteException {
        }

        @Override // android.hardware.fingerprint.ISidefpsController
        public void hide() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ISidefpsController {
        static final int TRANSACTION_hide = 2;
        static final int TRANSACTION_show = 1;

        public Stub() {
            attachInterface(this, ISidefpsController.DESCRIPTOR);
        }

        public static ISidefpsController asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ISidefpsController.DESCRIPTOR);
            if (iin == null || !(iin instanceof ISidefpsController)) {
                return new Proxy(obj);
            }
            return (ISidefpsController) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return ThreadedRenderer.OVERDRAW_PROPERTY_SHOW;
                case 2:
                    return "hide";
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
                    reply.writeString(ISidefpsController.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ISidefpsController.DESCRIPTOR);
                            show();
                            return true;
                        case 2:
                            data.enforceInterface(ISidefpsController.DESCRIPTOR);
                            hide();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ISidefpsController {
            public static ISidefpsController sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISidefpsController.DESCRIPTOR;
            }

            @Override // android.hardware.fingerprint.ISidefpsController
            public void show() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISidefpsController.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().show();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.hardware.fingerprint.ISidefpsController
            public void hide() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ISidefpsController.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().hide();
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISidefpsController impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ISidefpsController getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
