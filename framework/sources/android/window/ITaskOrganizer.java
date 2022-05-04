package android.window;

import android.app.ActivityManager;
import android.graphics.Rect;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.SurfaceControl;
/* loaded from: classes3.dex */
public interface ITaskOrganizer extends IInterface {
    public static final String DESCRIPTOR = "android.window.ITaskOrganizer";

    void addStartingWindow(StartingWindowInfo startingWindowInfo, IBinder iBinder) throws RemoteException;

    void copySplashScreenView(int i) throws RemoteException;

    void onAppSplashScreenViewRemoved(int i) throws RemoteException;

    void onBackPressedOnTaskRoot(ActivityManager.RunningTaskInfo runningTaskInfo) throws RemoteException;

    void onTaskAppeared(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl) throws RemoteException;

    void onTaskInfoChanged(ActivityManager.RunningTaskInfo runningTaskInfo) throws RemoteException;

    void onTaskVanished(ActivityManager.RunningTaskInfo runningTaskInfo) throws RemoteException;

    void removeStartingWindow(int i, SurfaceControl surfaceControl, Rect rect, boolean z) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements ITaskOrganizer {
        @Override // android.window.ITaskOrganizer
        public void addStartingWindow(StartingWindowInfo info, IBinder appToken) throws RemoteException {
        }

        @Override // android.window.ITaskOrganizer
        public void removeStartingWindow(int taskId, SurfaceControl leash, Rect frame, boolean playRevealAnimation) throws RemoteException {
        }

        @Override // android.window.ITaskOrganizer
        public void copySplashScreenView(int taskId) throws RemoteException {
        }

        @Override // android.window.ITaskOrganizer
        public void onAppSplashScreenViewRemoved(int taskId) throws RemoteException {
        }

        @Override // android.window.ITaskOrganizer
        public void onTaskAppeared(ActivityManager.RunningTaskInfo taskInfo, SurfaceControl leash) throws RemoteException {
        }

        @Override // android.window.ITaskOrganizer
        public void onTaskVanished(ActivityManager.RunningTaskInfo taskInfo) throws RemoteException {
        }

        @Override // android.window.ITaskOrganizer
        public void onTaskInfoChanged(ActivityManager.RunningTaskInfo taskInfo) throws RemoteException {
        }

        @Override // android.window.ITaskOrganizer
        public void onBackPressedOnTaskRoot(ActivityManager.RunningTaskInfo taskInfo) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements ITaskOrganizer {
        static final int TRANSACTION_addStartingWindow = 1;
        static final int TRANSACTION_copySplashScreenView = 3;
        static final int TRANSACTION_onAppSplashScreenViewRemoved = 4;
        static final int TRANSACTION_onBackPressedOnTaskRoot = 8;
        static final int TRANSACTION_onTaskAppeared = 5;
        static final int TRANSACTION_onTaskInfoChanged = 7;
        static final int TRANSACTION_onTaskVanished = 6;
        static final int TRANSACTION_removeStartingWindow = 2;

        public Stub() {
            attachInterface(this, ITaskOrganizer.DESCRIPTOR);
        }

        public static ITaskOrganizer asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ITaskOrganizer.DESCRIPTOR);
            if (iin == null || !(iin instanceof ITaskOrganizer)) {
                return new Proxy(obj);
            }
            return (ITaskOrganizer) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "addStartingWindow";
                case 2:
                    return "removeStartingWindow";
                case 3:
                    return "copySplashScreenView";
                case 4:
                    return "onAppSplashScreenViewRemoved";
                case 5:
                    return "onTaskAppeared";
                case 6:
                    return "onTaskVanished";
                case 7:
                    return "onTaskInfoChanged";
                case 8:
                    return "onBackPressedOnTaskRoot";
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
            StartingWindowInfo _arg0;
            SurfaceControl _arg1;
            Rect _arg2;
            ActivityManager.RunningTaskInfo _arg02;
            SurfaceControl _arg12;
            ActivityManager.RunningTaskInfo _arg03;
            ActivityManager.RunningTaskInfo _arg04;
            ActivityManager.RunningTaskInfo _arg05;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ITaskOrganizer.DESCRIPTOR);
                    return true;
                default:
                    switch (code) {
                        case 1:
                            data.enforceInterface(ITaskOrganizer.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = StartingWindowInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            IBinder _arg13 = data.readStrongBinder();
                            addStartingWindow(_arg0, _arg13);
                            return true;
                        case 2:
                            data.enforceInterface(ITaskOrganizer.DESCRIPTOR);
                            int _arg06 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg1 = SurfaceControl.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg2 = Rect.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            boolean _arg3 = data.readInt() != 0;
                            removeStartingWindow(_arg06, _arg1, _arg2, _arg3);
                            return true;
                        case 3:
                            data.enforceInterface(ITaskOrganizer.DESCRIPTOR);
                            int _arg07 = data.readInt();
                            copySplashScreenView(_arg07);
                            return true;
                        case 4:
                            data.enforceInterface(ITaskOrganizer.DESCRIPTOR);
                            int _arg08 = data.readInt();
                            onAppSplashScreenViewRemoved(_arg08);
                            return true;
                        case 5:
                            data.enforceInterface(ITaskOrganizer.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg12 = SurfaceControl.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            onTaskAppeared(_arg02, _arg12);
                            return true;
                        case 6:
                            data.enforceInterface(ITaskOrganizer.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            onTaskVanished(_arg03);
                            return true;
                        case 7:
                            data.enforceInterface(ITaskOrganizer.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            onTaskInfoChanged(_arg04);
                            return true;
                        case 8:
                            data.enforceInterface(ITaskOrganizer.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg05 = ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            onBackPressedOnTaskRoot(_arg05);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements ITaskOrganizer {
            public static ITaskOrganizer sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ITaskOrganizer.DESCRIPTOR;
            }

            @Override // android.window.ITaskOrganizer
            public void addStartingWindow(StartingWindowInfo info, IBinder appToken) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITaskOrganizer.DESCRIPTOR);
                    if (info != null) {
                        _data.writeInt(1);
                        info.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(appToken);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addStartingWindow(info, appToken);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.window.ITaskOrganizer
            public void removeStartingWindow(int taskId, SurfaceControl leash, Rect frame, boolean playRevealAnimation) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITaskOrganizer.DESCRIPTOR);
                    _data.writeInt(taskId);
                    int i = 0;
                    if (leash != null) {
                        _data.writeInt(1);
                        leash.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (frame != null) {
                        _data.writeInt(1);
                        frame.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (playRevealAnimation) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeStartingWindow(taskId, leash, frame, playRevealAnimation);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.window.ITaskOrganizer
            public void copySplashScreenView(int taskId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITaskOrganizer.DESCRIPTOR);
                    _data.writeInt(taskId);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().copySplashScreenView(taskId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.window.ITaskOrganizer
            public void onAppSplashScreenViewRemoved(int taskId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITaskOrganizer.DESCRIPTOR);
                    _data.writeInt(taskId);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAppSplashScreenViewRemoved(taskId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.window.ITaskOrganizer
            public void onTaskAppeared(ActivityManager.RunningTaskInfo taskInfo, SurfaceControl leash) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITaskOrganizer.DESCRIPTOR);
                    if (taskInfo != null) {
                        _data.writeInt(1);
                        taskInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (leash != null) {
                        _data.writeInt(1);
                        leash.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTaskAppeared(taskInfo, leash);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.window.ITaskOrganizer
            public void onTaskVanished(ActivityManager.RunningTaskInfo taskInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITaskOrganizer.DESCRIPTOR);
                    if (taskInfo != null) {
                        _data.writeInt(1);
                        taskInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTaskVanished(taskInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.window.ITaskOrganizer
            public void onTaskInfoChanged(ActivityManager.RunningTaskInfo taskInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITaskOrganizer.DESCRIPTOR);
                    if (taskInfo != null) {
                        _data.writeInt(1);
                        taskInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTaskInfoChanged(taskInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.window.ITaskOrganizer
            public void onBackPressedOnTaskRoot(ActivityManager.RunningTaskInfo taskInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITaskOrganizer.DESCRIPTOR);
                    if (taskInfo != null) {
                        _data.writeInt(1);
                        taskInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBackPressedOnTaskRoot(taskInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITaskOrganizer impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ITaskOrganizer getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
