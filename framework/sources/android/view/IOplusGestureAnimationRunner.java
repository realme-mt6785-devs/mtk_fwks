package android.view;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.IOplusGestureAnimationController;
/* loaded from: classes3.dex */
public interface IOplusGestureAnimationRunner extends IInterface {
    public static final String DESCRIPTOR = "android.view.IOplusGestureAnimationRunner";

    void onAnimationCanceled() throws RemoteException;

    void onAnimationStart(IOplusGestureAnimationController iOplusGestureAnimationController, RemoteAnimationTarget[] remoteAnimationTargetArr, SurfaceControl[] surfaceControlArr) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IOplusGestureAnimationRunner {
        @Override // android.view.IOplusGestureAnimationRunner
        public void onAnimationCanceled() throws RemoteException {
        }

        @Override // android.view.IOplusGestureAnimationRunner
        public void onAnimationStart(IOplusGestureAnimationController controller, RemoteAnimationTarget[] apps, SurfaceControl[] dims) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IOplusGestureAnimationRunner {
        static final int TRANSACTION_onAnimationCanceled = 2;
        static final int TRANSACTION_onAnimationStart = 3;

        public Stub() {
            attachInterface(this, IOplusGestureAnimationRunner.DESCRIPTOR);
        }

        public static IOplusGestureAnimationRunner asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusGestureAnimationRunner.DESCRIPTOR);
            if (iin == null || !(iin instanceof IOplusGestureAnimationRunner)) {
                return new Proxy(obj);
            }
            return (IOplusGestureAnimationRunner) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 2:
                    return "onAnimationCanceled";
                case 3:
                    return "onAnimationStart";
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
                    reply.writeString(IOplusGestureAnimationRunner.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 2:
                            data.enforceInterface(IOplusGestureAnimationRunner.DESCRIPTOR);
                            onAnimationCanceled();
                            return true;
                        case 3:
                            data.enforceInterface(IOplusGestureAnimationRunner.DESCRIPTOR);
                            IOplusGestureAnimationController _arg0 = IOplusGestureAnimationController.Stub.asInterface(data.readStrongBinder());
                            RemoteAnimationTarget[] _arg1 = (RemoteAnimationTarget[]) data.createTypedArray(RemoteAnimationTarget.CREATOR);
                            SurfaceControl[] _arg2 = (SurfaceControl[]) data.createTypedArray(SurfaceControl.CREATOR);
                            onAnimationStart(_arg0, _arg1, _arg2);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IOplusGestureAnimationRunner {
            public static IOplusGestureAnimationRunner sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusGestureAnimationRunner.DESCRIPTOR;
            }

            @Override // android.view.IOplusGestureAnimationRunner
            public void onAnimationCanceled() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusGestureAnimationRunner.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAnimationCanceled();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IOplusGestureAnimationRunner
            public void onAnimationStart(IOplusGestureAnimationController controller, RemoteAnimationTarget[] apps, SurfaceControl[] dims) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusGestureAnimationRunner.DESCRIPTOR);
                    _data.writeStrongBinder(controller != null ? controller.asBinder() : null);
                    _data.writeTypedArray(apps, 0);
                    _data.writeTypedArray(dims, 0);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAnimationStart(controller, apps, dims);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOplusGestureAnimationRunner impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IOplusGestureAnimationRunner getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
