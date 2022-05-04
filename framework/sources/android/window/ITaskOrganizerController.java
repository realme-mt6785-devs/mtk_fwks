package android.window;

import android.app.ActivityManager;
import android.content.pm.ParceledListSlice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.window.ITaskOrganizer;
import java.util.List;
/* loaded from: classes3.dex */
public interface ITaskOrganizerController extends IInterface {
    public static final String DESCRIPTOR = "android.window.ITaskOrganizerController";

    void createRootTask(int i, int i2, IBinder iBinder) throws RemoteException;

    boolean deleteRootTask(WindowContainerToken windowContainerToken) throws RemoteException;

    List<ActivityManager.RunningTaskInfo> getChildTasks(WindowContainerToken windowContainerToken, int[] iArr) throws RemoteException;

    WindowContainerToken getImeTarget(int i) throws RemoteException;

    List<ActivityManager.RunningTaskInfo> getRootTasks(int i, int[] iArr) throws RemoteException;

    ParceledListSlice<TaskAppearedInfo> registerTaskOrganizer(ITaskOrganizer iTaskOrganizer) throws RemoteException;

    void restartTaskTopActivityProcessIfVisible(WindowContainerToken windowContainerToken) throws RemoteException;

    void setInterceptBackPressedOnTaskRoot(WindowContainerToken windowContainerToken, boolean z) throws RemoteException;

    void unregisterTaskOrganizer(ITaskOrganizer iTaskOrganizer) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements ITaskOrganizerController {
        @Override // android.window.ITaskOrganizerController
        public ParceledListSlice<TaskAppearedInfo> registerTaskOrganizer(ITaskOrganizer organizer) throws RemoteException {
            return null;
        }

        @Override // android.window.ITaskOrganizerController
        public void unregisterTaskOrganizer(ITaskOrganizer organizer) throws RemoteException {
        }

        @Override // android.window.ITaskOrganizerController
        public void createRootTask(int displayId, int windowingMode, IBinder launchCookie) throws RemoteException {
        }

        @Override // android.window.ITaskOrganizerController
        public boolean deleteRootTask(WindowContainerToken task) throws RemoteException {
            return false;
        }

        @Override // android.window.ITaskOrganizerController
        public List<ActivityManager.RunningTaskInfo> getChildTasks(WindowContainerToken parent, int[] activityTypes) throws RemoteException {
            return null;
        }

        @Override // android.window.ITaskOrganizerController
        public List<ActivityManager.RunningTaskInfo> getRootTasks(int displayId, int[] activityTypes) throws RemoteException {
            return null;
        }

        @Override // android.window.ITaskOrganizerController
        public WindowContainerToken getImeTarget(int display) throws RemoteException {
            return null;
        }

        @Override // android.window.ITaskOrganizerController
        public void setInterceptBackPressedOnTaskRoot(WindowContainerToken task, boolean interceptBackPressed) throws RemoteException {
        }

        @Override // android.window.ITaskOrganizerController
        public void restartTaskTopActivityProcessIfVisible(WindowContainerToken task) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements ITaskOrganizerController {
        static final int TRANSACTION_createRootTask = 3;
        static final int TRANSACTION_deleteRootTask = 4;
        static final int TRANSACTION_getChildTasks = 5;
        static final int TRANSACTION_getImeTarget = 7;
        static final int TRANSACTION_getRootTasks = 6;
        static final int TRANSACTION_registerTaskOrganizer = 1;
        static final int TRANSACTION_restartTaskTopActivityProcessIfVisible = 9;
        static final int TRANSACTION_setInterceptBackPressedOnTaskRoot = 8;
        static final int TRANSACTION_unregisterTaskOrganizer = 2;

        public Stub() {
            attachInterface(this, ITaskOrganizerController.DESCRIPTOR);
        }

        public static ITaskOrganizerController asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(ITaskOrganizerController.DESCRIPTOR);
            if (iin == null || !(iin instanceof ITaskOrganizerController)) {
                return new Proxy(obj);
            }
            return (ITaskOrganizerController) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "registerTaskOrganizer";
                case 2:
                    return "unregisterTaskOrganizer";
                case 3:
                    return "createRootTask";
                case 4:
                    return "deleteRootTask";
                case 5:
                    return "getChildTasks";
                case 6:
                    return "getRootTasks";
                case 7:
                    return "getImeTarget";
                case 8:
                    return "setInterceptBackPressedOnTaskRoot";
                case 9:
                    return "restartTaskTopActivityProcessIfVisible";
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
            WindowContainerToken _arg0;
            WindowContainerToken _arg02;
            WindowContainerToken _arg03;
            WindowContainerToken _arg04;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(ITaskOrganizerController.DESCRIPTOR);
                    return true;
                default:
                    boolean _arg1 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(ITaskOrganizerController.DESCRIPTOR);
                            ITaskOrganizer _arg05 = ITaskOrganizer.Stub.asInterface(data.readStrongBinder());
                            ParceledListSlice<TaskAppearedInfo> _result = registerTaskOrganizer(_arg05);
                            reply.writeNoException();
                            if (_result != null) {
                                reply.writeInt(1);
                                _result.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 2:
                            data.enforceInterface(ITaskOrganizerController.DESCRIPTOR);
                            ITaskOrganizer _arg06 = ITaskOrganizer.Stub.asInterface(data.readStrongBinder());
                            unregisterTaskOrganizer(_arg06);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(ITaskOrganizerController.DESCRIPTOR);
                            int _arg07 = data.readInt();
                            int _arg12 = data.readInt();
                            IBinder _arg2 = data.readStrongBinder();
                            createRootTask(_arg07, _arg12, _arg2);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(ITaskOrganizerController.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = WindowContainerToken.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            boolean deleteRootTask = deleteRootTask(_arg0);
                            reply.writeNoException();
                            reply.writeInt(deleteRootTask ? 1 : 0);
                            return true;
                        case 5:
                            data.enforceInterface(ITaskOrganizerController.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = WindowContainerToken.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            int[] _arg13 = data.createIntArray();
                            List<ActivityManager.RunningTaskInfo> _result2 = getChildTasks(_arg02, _arg13);
                            reply.writeNoException();
                            reply.writeTypedList(_result2);
                            return true;
                        case 6:
                            data.enforceInterface(ITaskOrganizerController.DESCRIPTOR);
                            int _arg08 = data.readInt();
                            int[] _arg14 = data.createIntArray();
                            List<ActivityManager.RunningTaskInfo> _result3 = getRootTasks(_arg08, _arg14);
                            reply.writeNoException();
                            reply.writeTypedList(_result3);
                            return true;
                        case 7:
                            data.enforceInterface(ITaskOrganizerController.DESCRIPTOR);
                            int _arg09 = data.readInt();
                            WindowContainerToken _result4 = getImeTarget(_arg09);
                            reply.writeNoException();
                            if (_result4 != null) {
                                reply.writeInt(1);
                                _result4.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 8:
                            data.enforceInterface(ITaskOrganizerController.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = WindowContainerToken.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = true;
                            }
                            setInterceptBackPressedOnTaskRoot(_arg03, _arg1);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(ITaskOrganizerController.DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = WindowContainerToken.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            restartTaskTopActivityProcessIfVisible(_arg04);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements ITaskOrganizerController {
            public static ITaskOrganizerController sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ITaskOrganizerController.DESCRIPTOR;
            }

            @Override // android.window.ITaskOrganizerController
            public ParceledListSlice<TaskAppearedInfo> registerTaskOrganizer(ITaskOrganizer organizer) throws RemoteException {
                ParceledListSlice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITaskOrganizerController.DESCRIPTOR);
                    _data.writeStrongBinder(organizer != null ? organizer.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerTaskOrganizer(organizer);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParceledListSlice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.window.ITaskOrganizerController
            public void unregisterTaskOrganizer(ITaskOrganizer organizer) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITaskOrganizerController.DESCRIPTOR);
                    _data.writeStrongBinder(organizer != null ? organizer.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterTaskOrganizer(organizer);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.window.ITaskOrganizerController
            public void createRootTask(int displayId, int windowingMode, IBinder launchCookie) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITaskOrganizerController.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeInt(windowingMode);
                    _data.writeStrongBinder(launchCookie);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().createRootTask(displayId, windowingMode, launchCookie);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.window.ITaskOrganizerController
            public boolean deleteRootTask(WindowContainerToken task) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITaskOrganizerController.DESCRIPTOR);
                    boolean _result = true;
                    if (task != null) {
                        _data.writeInt(1);
                        task.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deleteRootTask(task);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.window.ITaskOrganizerController
            public List<ActivityManager.RunningTaskInfo> getChildTasks(WindowContainerToken parent, int[] activityTypes) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITaskOrganizerController.DESCRIPTOR);
                    if (parent != null) {
                        _data.writeInt(1);
                        parent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeIntArray(activityTypes);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getChildTasks(parent, activityTypes);
                    }
                    _reply.readException();
                    List<ActivityManager.RunningTaskInfo> _result = _reply.createTypedArrayList(ActivityManager.RunningTaskInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.window.ITaskOrganizerController
            public List<ActivityManager.RunningTaskInfo> getRootTasks(int displayId, int[] activityTypes) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITaskOrganizerController.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeIntArray(activityTypes);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRootTasks(displayId, activityTypes);
                    }
                    _reply.readException();
                    List<ActivityManager.RunningTaskInfo> _result = _reply.createTypedArrayList(ActivityManager.RunningTaskInfo.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.window.ITaskOrganizerController
            public WindowContainerToken getImeTarget(int display) throws RemoteException {
                WindowContainerToken _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITaskOrganizerController.DESCRIPTOR);
                    _data.writeInt(display);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getImeTarget(display);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = WindowContainerToken.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.window.ITaskOrganizerController
            public void setInterceptBackPressedOnTaskRoot(WindowContainerToken task, boolean interceptBackPressed) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITaskOrganizerController.DESCRIPTOR);
                    int i = 1;
                    if (task != null) {
                        _data.writeInt(1);
                        task.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!interceptBackPressed) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setInterceptBackPressedOnTaskRoot(task, interceptBackPressed);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.window.ITaskOrganizerController
            public void restartTaskTopActivityProcessIfVisible(WindowContainerToken task) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(ITaskOrganizerController.DESCRIPTOR);
                    if (task != null) {
                        _data.writeInt(1);
                        task.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().restartTaskTopActivityProcessIfVisible(task);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITaskOrganizerController impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ITaskOrganizerController getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
