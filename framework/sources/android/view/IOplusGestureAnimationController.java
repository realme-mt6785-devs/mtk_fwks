package android.view;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes3.dex */
public interface IOplusGestureAnimationController extends IInterface {
    public static final String DESCRIPTOR = "android.view.IOplusGestureAnimationController";

    void finishGestureAnimation(Bundle bundle) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IOplusGestureAnimationController {
        @Override // android.view.IOplusGestureAnimationController
        public void finishGestureAnimation(Bundle bOptions) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IOplusGestureAnimationController {
        static final int TRANSACTION_finishGestureAnimation = 1;

        public Stub() {
            attachInterface(this, IOplusGestureAnimationController.DESCRIPTOR);
        }

        public static IOplusGestureAnimationController asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusGestureAnimationController.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusGestureAnimationController)) {
                return new Proxy(obj);
            }
            return (IOplusGestureAnimationController) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "finishGestureAnimation";
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
            Bundle _arg0;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(IOplusGestureAnimationController.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(IOplusGestureAnimationController.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            finishGestureAnimation(_arg0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IOplusGestureAnimationController {
            public static IOplusGestureAnimationController sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusGestureAnimationController.DESCRIPTOR;
            }

            @Override // android.view.IOplusGestureAnimationController
            public void finishGestureAnimation(Bundle bOptions) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusGestureAnimationController.DESCRIPTOR);
                    if (bOptions != null) {
                        _data.writeInt(1);
                        bOptions.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().finishGestureAnimation(bOptions);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusGestureAnimationController impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusGestureAnimationController getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
