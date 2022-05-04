package android.view;

import android.graphics.Rect;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.window.PictureInPictureSurfaceTransaction;
import android.window.TaskSnapshot;
/* loaded from: classes3.dex */
public interface IRecentsAnimationController extends IInterface {
    void animateNavigationBarToApp(long j) throws RemoteException;

    void cleanupScreenshot() throws RemoteException;

    void detachNavigationBarFromApp(boolean z) throws RemoteException;

    void finish(boolean z, boolean z2) throws RemoteException;

    void finishZoom(boolean z, boolean z2, int i, int i2, Rect rect, int i3, Bundle bundle) throws RemoteException;

    void hideCurrentInputMethod() throws RemoteException;

    boolean removeTask(int i) throws RemoteException;

    TaskSnapshot screenshotTask(int i) throws RemoteException;

    void setAnimationTargetsBehindSystemBars(boolean z) throws RemoteException;

    void setDeferCancelUntilNextTransition(boolean z, boolean z2) throws RemoteException;

    void setFinishTaskTransaction(int i, PictureInPictureSurfaceTransaction pictureInPictureSurfaceTransaction, SurfaceControl surfaceControl) throws RemoteException;

    void setInputConsumerEnabled(boolean z) throws RemoteException;

    void setWillFinishToHome(boolean z) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IRecentsAnimationController {
        @Override // android.view.IRecentsAnimationController
        public TaskSnapshot screenshotTask(int taskId) throws RemoteException {
            return null;
        }

        @Override // android.view.IRecentsAnimationController
        public void setFinishTaskTransaction(int taskId, PictureInPictureSurfaceTransaction finishTransaction, SurfaceControl overlay) throws RemoteException {
        }

        @Override // android.view.IRecentsAnimationController
        public void finish(boolean moveHomeToTop, boolean sendUserLeaveHint) throws RemoteException {
        }

        @Override // android.view.IRecentsAnimationController
        public void setInputConsumerEnabled(boolean enabled) throws RemoteException {
        }

        @Override // android.view.IRecentsAnimationController
        public void setAnimationTargetsBehindSystemBars(boolean behindSystemBars) throws RemoteException {
        }

        @Override // android.view.IRecentsAnimationController
        public void hideCurrentInputMethod() throws RemoteException {
        }

        @Override // android.view.IRecentsAnimationController
        public void cleanupScreenshot() throws RemoteException {
        }

        @Override // android.view.IRecentsAnimationController
        public void setDeferCancelUntilNextTransition(boolean defer, boolean screenshot) throws RemoteException {
        }

        @Override // android.view.IRecentsAnimationController
        public void setWillFinishToHome(boolean willFinishToHome) throws RemoteException {
        }

        @Override // android.view.IRecentsAnimationController
        public boolean removeTask(int taskId) throws RemoteException {
            return false;
        }

        @Override // android.view.IRecentsAnimationController
        public void detachNavigationBarFromApp(boolean moveHomeToTop) throws RemoteException {
        }

        @Override // android.view.IRecentsAnimationController
        public void animateNavigationBarToApp(long duration) throws RemoteException {
        }

        @Override // android.view.IRecentsAnimationController
        public void finishZoom(boolean moveHomeToTop, boolean sendUserLeaveHint, int taskId, int type, Rect rect, int orientation, Bundle bOptions) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IRecentsAnimationController {
        public static final String DESCRIPTOR = "android.view.IRecentsAnimationController";
        static final int TRANSACTION_animateNavigationBarToApp = 12;
        static final int TRANSACTION_cleanupScreenshot = 7;
        static final int TRANSACTION_detachNavigationBarFromApp = 11;
        static final int TRANSACTION_finish = 3;
        static final int TRANSACTION_finishZoom = 13;
        static final int TRANSACTION_hideCurrentInputMethod = 6;
        static final int TRANSACTION_removeTask = 10;
        static final int TRANSACTION_screenshotTask = 1;
        static final int TRANSACTION_setAnimationTargetsBehindSystemBars = 5;
        static final int TRANSACTION_setDeferCancelUntilNextTransition = 8;
        static final int TRANSACTION_setFinishTaskTransaction = 2;
        static final int TRANSACTION_setInputConsumerEnabled = 4;
        static final int TRANSACTION_setWillFinishToHome = 9;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRecentsAnimationController asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IRecentsAnimationController)) {
                return new Proxy(obj);
            }
            return (IRecentsAnimationController) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "screenshotTask";
                case 2:
                    return "setFinishTaskTransaction";
                case 3:
                    return "finish";
                case 4:
                    return "setInputConsumerEnabled";
                case 5:
                    return "setAnimationTargetsBehindSystemBars";
                case 6:
                    return "hideCurrentInputMethod";
                case 7:
                    return "cleanupScreenshot";
                case 8:
                    return "setDeferCancelUntilNextTransition";
                case 9:
                    return "setWillFinishToHome";
                case 10:
                    return "removeTask";
                case 11:
                    return "detachNavigationBarFromApp";
                case 12:
                    return "animateNavigationBarToApp";
                case 13:
                    return "finishZoom";
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
            PictureInPictureSurfaceTransaction _arg1;
            SurfaceControl _arg2;
            boolean _arg0;
            boolean _arg02;
            boolean _arg03;
            boolean _arg12;
            Rect _arg4;
            Bundle _arg6;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    boolean _arg04 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            TaskSnapshot _result = screenshotTask(data.readInt());
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg05 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = PictureInPictureSurfaceTransaction.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg2 = SurfaceControl.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            setFinishTaskTransaction(_arg05, _arg1, _arg2);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = true;
                            } else {
                                _arg0 = false;
                            }
                            if (data.readInt() != 0) {
                                _arg04 = true;
                            }
                            finish(_arg0, _arg04);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = true;
                            }
                            setInputConsumerEnabled(_arg04);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = true;
                            }
                            setAnimationTargetsBehindSystemBars(_arg04);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            hideCurrentInputMethod();
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            cleanupScreenshot();
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = true;
                            } else {
                                _arg02 = false;
                            }
                            if (data.readInt() != 0) {
                                _arg04 = true;
                            }
                            setDeferCancelUntilNextTransition(_arg02, _arg04);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = true;
                            }
                            setWillFinishToHome(_arg04);
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            boolean removeTask = removeTask(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(removeTask ? 1 : 0);
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = true;
                            }
                            detachNavigationBarFromApp(_arg04);
                            reply.writeNoException();
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            animateNavigationBarToApp(data.readLong());
                            reply.writeNoException();
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = true;
                            } else {
                                _arg03 = false;
                            }
                            if (data.readInt() != 0) {
                                _arg12 = true;
                            } else {
                                _arg12 = false;
                            }
                            int _arg22 = data.readInt();
                            int _arg3 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg4 = Rect.CREATOR.createFromParcel(data);
                            } else {
                                _arg4 = null;
                            }
                            int _arg5 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg6 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg6 = null;
                            }
                            finishZoom(_arg03, _arg12, _arg22, _arg3, _arg4, _arg5, _arg6);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IRecentsAnimationController {
            public static IRecentsAnimationController sDefaultImpl;
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

            @Override // android.view.IRecentsAnimationController
            public TaskSnapshot screenshotTask(int taskId) throws RemoteException {
                TaskSnapshot _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(taskId);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().screenshotTask(taskId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = TaskSnapshot.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IRecentsAnimationController
            public void setFinishTaskTransaction(int taskId, PictureInPictureSurfaceTransaction finishTransaction, SurfaceControl overlay) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(taskId);
                    if (finishTransaction != null) {
                        _data.writeInt(1);
                        finishTransaction.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (overlay != null) {
                        _data.writeInt(1);
                        overlay.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setFinishTaskTransaction(taskId, finishTransaction, overlay);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IRecentsAnimationController
            public void finish(boolean moveHomeToTop, boolean sendUserLeaveHint) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    _data.writeInt(moveHomeToTop ? 1 : 0);
                    if (!sendUserLeaveHint) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().finish(moveHomeToTop, sendUserLeaveHint);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IRecentsAnimationController
            public void setInputConsumerEnabled(boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setInputConsumerEnabled(enabled);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IRecentsAnimationController
            public void setAnimationTargetsBehindSystemBars(boolean behindSystemBars) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(behindSystemBars ? 1 : 0);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setAnimationTargetsBehindSystemBars(behindSystemBars);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IRecentsAnimationController
            public void hideCurrentInputMethod() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().hideCurrentInputMethod();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IRecentsAnimationController
            public void cleanupScreenshot() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().cleanupScreenshot();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IRecentsAnimationController
            public void setDeferCancelUntilNextTransition(boolean defer, boolean screenshot) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    _data.writeInt(defer ? 1 : 0);
                    if (!screenshot) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setDeferCancelUntilNextTransition(defer, screenshot);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IRecentsAnimationController
            public void setWillFinishToHome(boolean willFinishToHome) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(willFinishToHome ? 1 : 0);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setWillFinishToHome(willFinishToHome);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IRecentsAnimationController
            public boolean removeTask(int taskId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(taskId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeTask(taskId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IRecentsAnimationController
            public void detachNavigationBarFromApp(boolean moveHomeToTop) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(moveHomeToTop ? 1 : 0);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().detachNavigationBarFromApp(moveHomeToTop);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IRecentsAnimationController
            public void animateNavigationBarToApp(long duration) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(duration);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().animateNavigationBarToApp(duration);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IRecentsAnimationController
            public void finishZoom(boolean moveHomeToTop, boolean sendUserLeaveHint, int taskId, int type, Rect rect, int orientation, Bundle bOptions) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(moveHomeToTop ? 1 : 0);
                    _data.writeInt(sendUserLeaveHint ? 1 : 0);
                    try {
                        _data.writeInt(taskId);
                        try {
                            _data.writeInt(type);
                            if (rect != null) {
                                _data.writeInt(1);
                                rect.writeToParcel(_data, 0);
                            } else {
                                _data.writeInt(0);
                            }
                            try {
                                _data.writeInt(orientation);
                                if (bOptions != null) {
                                    _data.writeInt(1);
                                    bOptions.writeToParcel(_data, 0);
                                } else {
                                    _data.writeInt(0);
                                }
                                boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _reply.readException();
                                    _reply.recycle();
                                    _data.recycle();
                                    return;
                                }
                                Stub.getDefaultImpl().finishZoom(moveHomeToTop, sendUserLeaveHint, taskId, type, rect, orientation, bOptions);
                                _reply.recycle();
                                _data.recycle();
                            } catch (Throwable th2) {
                                th = th2;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                }
            }
        }

        public static boolean setDefaultImpl(IRecentsAnimationController impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IRecentsAnimationController getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
