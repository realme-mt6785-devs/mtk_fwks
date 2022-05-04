package android.view;

import android.graphics.Rect;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.IRecentsAnimationController;
import android.window.TaskSnapshot;
/* loaded from: classes3.dex */
public interface IRecentsAnimationRunner extends IInterface {
    void onAnimationCanceled(TaskSnapshot taskSnapshot) throws RemoteException;

    void onAnimationStart(IRecentsAnimationController iRecentsAnimationController, RemoteAnimationTarget[] remoteAnimationTargetArr, RemoteAnimationTarget[] remoteAnimationTargetArr2, Rect rect, Rect rect2) throws RemoteException;

    void onTaskAppeared(RemoteAnimationTarget remoteAnimationTarget) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IRecentsAnimationRunner {
        @Override // android.view.IRecentsAnimationRunner
        public void onAnimationCanceled(TaskSnapshot taskSnapshot) throws RemoteException {
        }

        @Override // android.view.IRecentsAnimationRunner
        public void onAnimationStart(IRecentsAnimationController controller, RemoteAnimationTarget[] apps, RemoteAnimationTarget[] wallpapers, Rect homeContentInsets, Rect minimizedHomeBounds) throws RemoteException {
        }

        @Override // android.view.IRecentsAnimationRunner
        public void onTaskAppeared(RemoteAnimationTarget app) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IRecentsAnimationRunner {
        public static final String DESCRIPTOR = "android.view.IRecentsAnimationRunner";
        static final int TRANSACTION_onAnimationCanceled = 2;
        static final int TRANSACTION_onAnimationStart = 3;
        static final int TRANSACTION_onTaskAppeared = 4;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRecentsAnimationRunner asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IRecentsAnimationRunner)) {
                return new Proxy(obj);
            }
            return (IRecentsAnimationRunner) iin;
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
                case 4:
                    return "onTaskAppeared";
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
            TaskSnapshot _arg0;
            Rect _arg3;
            Rect _arg4;
            RemoteAnimationTarget _arg02;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = TaskSnapshot.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            onAnimationCanceled(_arg0);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            IRecentsAnimationController _arg03 = IRecentsAnimationController.Stub.asInterface(data.readStrongBinder());
                            RemoteAnimationTarget[] _arg1 = (RemoteAnimationTarget[]) data.createTypedArray(RemoteAnimationTarget.CREATOR);
                            RemoteAnimationTarget[] _arg2 = (RemoteAnimationTarget[]) data.createTypedArray(RemoteAnimationTarget.CREATOR);
                            if (data.readInt() != 0) {
                                _arg3 = Rect.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg4 = Rect.CREATOR.createFromParcel(data);
                            } else {
                                _arg4 = null;
                            }
                            onAnimationStart(_arg03, _arg1, _arg2, _arg3, _arg4);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = RemoteAnimationTarget.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            onTaskAppeared(_arg02);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IRecentsAnimationRunner {
            public static IRecentsAnimationRunner sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.view.IRecentsAnimationRunner
            public void onAnimationCanceled(TaskSnapshot taskSnapshot) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (taskSnapshot != null) {
                        _data.writeInt(1);
                        taskSnapshot.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAnimationCanceled(taskSnapshot);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IRecentsAnimationRunner
            public void onAnimationStart(IRecentsAnimationController controller, RemoteAnimationTarget[] apps, RemoteAnimationTarget[] wallpapers, Rect homeContentInsets, Rect minimizedHomeBounds) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(controller != null ? controller.asBinder() : null);
                    _data.writeTypedArray(apps, 0);
                    _data.writeTypedArray(wallpapers, 0);
                    if (homeContentInsets != null) {
                        _data.writeInt(1);
                        homeContentInsets.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (minimizedHomeBounds != null) {
                        _data.writeInt(1);
                        minimizedHomeBounds.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAnimationStart(controller, apps, wallpapers, homeContentInsets, minimizedHomeBounds);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IRecentsAnimationRunner
            public void onTaskAppeared(RemoteAnimationTarget app) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (app != null) {
                        _data.writeInt(1);
                        app.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTaskAppeared(app);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRecentsAnimationRunner impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IRecentsAnimationRunner getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
