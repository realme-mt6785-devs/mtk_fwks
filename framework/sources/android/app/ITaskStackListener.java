package android.app;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.window.TaskSnapshot;
/* loaded from: classes.dex */
public interface ITaskStackListener extends IInterface {
    public static final int FORCED_RESIZEABLE_REASON_SECONDARY_DISPLAY = 2;
    public static final int FORCED_RESIZEABLE_REASON_SPLIT_SCREEN = 1;

    void onActivityDismissingDockedTask() throws RemoteException;

    void onActivityForcedResizable(String str, int i, int i2) throws RemoteException;

    void onActivityLaunchOnSecondaryDisplayFailed(ActivityManager.RunningTaskInfo runningTaskInfo, int i) throws RemoteException;

    void onActivityLaunchOnSecondaryDisplayRerouted(ActivityManager.RunningTaskInfo runningTaskInfo, int i) throws RemoteException;

    void onActivityPinned(String str, int i, int i2, int i3) throws RemoteException;

    void onActivityRequestedOrientationChanged(int i, int i2) throws RemoteException;

    void onActivityRestartAttempt(ActivityManager.RunningTaskInfo runningTaskInfo, boolean z, boolean z2, boolean z3) throws RemoteException;

    void onActivityRotation(int i) throws RemoteException;

    void onActivityUnpinned() throws RemoteException;

    void onBackPressedOnTaskRoot(ActivityManager.RunningTaskInfo runningTaskInfo) throws RemoteException;

    void onLockTaskModeChanged(int i) throws RemoteException;

    void onRecentTaskListFrozenChanged(boolean z) throws RemoteException;

    void onRecentTaskListUpdated() throws RemoteException;

    void onTaskCreated(int i, ComponentName componentName) throws RemoteException;

    void onTaskDescriptionChanged(ActivityManager.RunningTaskInfo runningTaskInfo) throws RemoteException;

    void onTaskDisplayChanged(int i, int i2) throws RemoteException;

    void onTaskFocusChanged(int i, boolean z) throws RemoteException;

    void onTaskMovedToBack(ActivityManager.RunningTaskInfo runningTaskInfo) throws RemoteException;

    void onTaskMovedToFront(ActivityManager.RunningTaskInfo runningTaskInfo) throws RemoteException;

    void onTaskProfileLocked(int i, int i2) throws RemoteException;

    void onTaskRemovalStarted(ActivityManager.RunningTaskInfo runningTaskInfo) throws RemoteException;

    void onTaskRemoved(int i) throws RemoteException;

    void onTaskRequestedOrientationChanged(int i, int i2) throws RemoteException;

    void onTaskSnapshotChanged(int i, TaskSnapshot taskSnapshot) throws RemoteException;

    void onTaskStackChanged() throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ITaskStackListener {
        @Override // android.app.ITaskStackListener
        public void onTaskStackChanged() throws RemoteException {
        }

        @Override // android.app.ITaskStackListener
        public void onActivityPinned(String packageName, int userId, int taskId, int stackId) throws RemoteException {
        }

        @Override // android.app.ITaskStackListener
        public void onActivityUnpinned() throws RemoteException {
        }

        @Override // android.app.ITaskStackListener
        public void onActivityRestartAttempt(ActivityManager.RunningTaskInfo task, boolean homeTaskVisible, boolean clearedTask, boolean wasVisible) throws RemoteException {
        }

        @Override // android.app.ITaskStackListener
        public void onActivityForcedResizable(String packageName, int taskId, int reason) throws RemoteException {
        }

        @Override // android.app.ITaskStackListener
        public void onActivityDismissingDockedTask() throws RemoteException {
        }

        @Override // android.app.ITaskStackListener
        public void onActivityLaunchOnSecondaryDisplayFailed(ActivityManager.RunningTaskInfo taskInfo, int requestedDisplayId) throws RemoteException {
        }

        @Override // android.app.ITaskStackListener
        public void onActivityLaunchOnSecondaryDisplayRerouted(ActivityManager.RunningTaskInfo taskInfo, int requestedDisplayId) throws RemoteException {
        }

        @Override // android.app.ITaskStackListener
        public void onTaskCreated(int taskId, ComponentName componentName) throws RemoteException {
        }

        @Override // android.app.ITaskStackListener
        public void onTaskRemoved(int taskId) throws RemoteException {
        }

        @Override // android.app.ITaskStackListener
        public void onTaskMovedToFront(ActivityManager.RunningTaskInfo taskInfo) throws RemoteException {
        }

        @Override // android.app.ITaskStackListener
        public void onTaskDescriptionChanged(ActivityManager.RunningTaskInfo taskInfo) throws RemoteException {
        }

        @Override // android.app.ITaskStackListener
        public void onActivityRequestedOrientationChanged(int taskId, int requestedOrientation) throws RemoteException {
        }

        @Override // android.app.ITaskStackListener
        public void onTaskRemovalStarted(ActivityManager.RunningTaskInfo taskInfo) throws RemoteException {
        }

        @Override // android.app.ITaskStackListener
        public void onTaskProfileLocked(int taskId, int userId) throws RemoteException {
        }

        @Override // android.app.ITaskStackListener
        public void onTaskSnapshotChanged(int taskId, TaskSnapshot snapshot) throws RemoteException {
        }

        @Override // android.app.ITaskStackListener
        public void onBackPressedOnTaskRoot(ActivityManager.RunningTaskInfo taskInfo) throws RemoteException {
        }

        @Override // android.app.ITaskStackListener
        public void onTaskDisplayChanged(int taskId, int newDisplayId) throws RemoteException {
        }

        @Override // android.app.ITaskStackListener
        public void onRecentTaskListUpdated() throws RemoteException {
        }

        @Override // android.app.ITaskStackListener
        public void onRecentTaskListFrozenChanged(boolean frozen) throws RemoteException {
        }

        @Override // android.app.ITaskStackListener
        public void onTaskFocusChanged(int taskId, boolean focused) throws RemoteException {
        }

        @Override // android.app.ITaskStackListener
        public void onTaskRequestedOrientationChanged(int taskId, int requestedOrientation) throws RemoteException {
        }

        @Override // android.app.ITaskStackListener
        public void onActivityRotation(int displayId) throws RemoteException {
        }

        @Override // android.app.ITaskStackListener
        public void onTaskMovedToBack(ActivityManager.RunningTaskInfo taskInfo) throws RemoteException {
        }

        @Override // android.app.ITaskStackListener
        public void onLockTaskModeChanged(int mode) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ITaskStackListener {
        public static final String DESCRIPTOR = "android.app.ITaskStackListener";
        static final int TRANSACTION_onActivityDismissingDockedTask = 6;
        static final int TRANSACTION_onActivityForcedResizable = 5;
        static final int TRANSACTION_onActivityLaunchOnSecondaryDisplayFailed = 7;
        static final int TRANSACTION_onActivityLaunchOnSecondaryDisplayRerouted = 8;
        static final int TRANSACTION_onActivityPinned = 2;
        static final int TRANSACTION_onActivityRequestedOrientationChanged = 13;
        static final int TRANSACTION_onActivityRestartAttempt = 4;
        static final int TRANSACTION_onActivityRotation = 23;
        static final int TRANSACTION_onActivityUnpinned = 3;
        static final int TRANSACTION_onBackPressedOnTaskRoot = 17;
        static final int TRANSACTION_onLockTaskModeChanged = 25;
        static final int TRANSACTION_onRecentTaskListFrozenChanged = 20;
        static final int TRANSACTION_onRecentTaskListUpdated = 19;
        static final int TRANSACTION_onTaskCreated = 9;
        static final int TRANSACTION_onTaskDescriptionChanged = 12;
        static final int TRANSACTION_onTaskDisplayChanged = 18;
        static final int TRANSACTION_onTaskFocusChanged = 21;
        static final int TRANSACTION_onTaskMovedToBack = 24;
        static final int TRANSACTION_onTaskMovedToFront = 11;
        static final int TRANSACTION_onTaskProfileLocked = 15;
        static final int TRANSACTION_onTaskRemovalStarted = 14;
        static final int TRANSACTION_onTaskRemoved = 10;
        static final int TRANSACTION_onTaskRequestedOrientationChanged = 22;
        static final int TRANSACTION_onTaskSnapshotChanged = 16;
        static final int TRANSACTION_onTaskStackChanged = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITaskStackListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ITaskStackListener)) {
                return new Proxy(obj);
            }
            return (ITaskStackListener) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "onTaskStackChanged";
                case 2:
                    return "onActivityPinned";
                case 3:
                    return "onActivityUnpinned";
                case 4:
                    return "onActivityRestartAttempt";
                case 5:
                    return "onActivityForcedResizable";
                case 6:
                    return "onActivityDismissingDockedTask";
                case 7:
                    return "onActivityLaunchOnSecondaryDisplayFailed";
                case 8:
                    return "onActivityLaunchOnSecondaryDisplayRerouted";
                case 9:
                    return "onTaskCreated";
                case 10:
                    return "onTaskRemoved";
                case 11:
                    return "onTaskMovedToFront";
                case 12:
                    return "onTaskDescriptionChanged";
                case 13:
                    return "onActivityRequestedOrientationChanged";
                case 14:
                    return "onTaskRemovalStarted";
                case 15:
                    return "onTaskProfileLocked";
                case 16:
                    return "onTaskSnapshotChanged";
                case 17:
                    return "onBackPressedOnTaskRoot";
                case 18:
                    return "onTaskDisplayChanged";
                case 19:
                    return "onRecentTaskListUpdated";
                case 20:
                    return "onRecentTaskListFrozenChanged";
                case 21:
                    return "onTaskFocusChanged";
                case 22:
                    return "onTaskRequestedOrientationChanged";
                case 23:
                    return "onActivityRotation";
                case 24:
                    return "onTaskMovedToBack";
                case 25:
                    return "onLockTaskModeChanged";
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
            ActivityManager.RunningTaskInfo _arg0;
            boolean _arg1;
            boolean _arg2;
            ActivityManager.RunningTaskInfo _arg02;
            ActivityManager.RunningTaskInfo _arg03;
            ComponentName _arg12;
            ActivityManager.RunningTaskInfo _arg04;
            ActivityManager.RunningTaskInfo _arg05;
            ActivityManager.RunningTaskInfo _arg06;
            TaskSnapshot _arg13;
            ActivityManager.RunningTaskInfo _arg07;
            ActivityManager.RunningTaskInfo _arg08;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    boolean _arg14 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            onTaskStackChanged();
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg09 = data.readString();
                            int _arg15 = data.readInt();
                            int _arg22 = data.readInt();
                            int _arg3 = data.readInt();
                            onActivityPinned(_arg09, _arg15, _arg22, _arg3);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            onActivityUnpinned();
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = true;
                            } else {
                                _arg1 = false;
                            }
                            if (data.readInt() != 0) {
                                _arg2 = true;
                            } else {
                                _arg2 = false;
                            }
                            if (data.readInt() != 0) {
                                _arg14 = true;
                            }
                            onActivityRestartAttempt(_arg0, _arg1, _arg2, _arg14);
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg010 = data.readString();
                            int _arg16 = data.readInt();
                            int _arg23 = data.readInt();
                            onActivityForcedResizable(_arg010, _arg16, _arg23);
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            onActivityDismissingDockedTask();
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            onActivityLaunchOnSecondaryDisplayFailed(_arg02, data.readInt());
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            onActivityLaunchOnSecondaryDisplayRerouted(_arg03, data.readInt());
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg011 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg12 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            onTaskCreated(_arg011, _arg12);
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg012 = data.readInt();
                            onTaskRemoved(_arg012);
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            onTaskMovedToFront(_arg04);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg05 = ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            onTaskDescriptionChanged(_arg05);
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg013 = data.readInt();
                            onActivityRequestedOrientationChanged(_arg013, data.readInt());
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg06 = ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg06 = null;
                            }
                            onTaskRemovalStarted(_arg06);
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg014 = data.readInt();
                            onTaskProfileLocked(_arg014, data.readInt());
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg015 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg13 = TaskSnapshot.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            onTaskSnapshotChanged(_arg015, _arg13);
                            return true;
                        case 17:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg07 = ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg07 = null;
                            }
                            onBackPressedOnTaskRoot(_arg07);
                            return true;
                        case 18:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg016 = data.readInt();
                            onTaskDisplayChanged(_arg016, data.readInt());
                            return true;
                        case 19:
                            data.enforceInterface(DESCRIPTOR);
                            onRecentTaskListUpdated();
                            return true;
                        case 20:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg14 = true;
                            }
                            onRecentTaskListFrozenChanged(_arg14);
                            return true;
                        case 21:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg017 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg14 = true;
                            }
                            onTaskFocusChanged(_arg017, _arg14);
                            return true;
                        case 22:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg018 = data.readInt();
                            onTaskRequestedOrientationChanged(_arg018, data.readInt());
                            return true;
                        case 23:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg019 = data.readInt();
                            onActivityRotation(_arg019);
                            return true;
                        case 24:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg08 = ActivityManager.RunningTaskInfo.CREATOR.createFromParcel(data);
                            } else {
                                _arg08 = null;
                            }
                            onTaskMovedToBack(_arg08);
                            return true;
                        case 25:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg020 = data.readInt();
                            onLockTaskModeChanged(_arg020);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ITaskStackListener {
            public static ITaskStackListener sDefaultImpl;
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

            @Override // android.app.ITaskStackListener
            public void onTaskStackChanged() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTaskStackChanged();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.ITaskStackListener
            public void onActivityPinned(String packageName, int userId, int taskId, int stackId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    _data.writeInt(taskId);
                    _data.writeInt(stackId);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onActivityPinned(packageName, userId, taskId, stackId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.ITaskStackListener
            public void onActivityUnpinned() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onActivityUnpinned();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.ITaskStackListener
            public void onActivityRestartAttempt(ActivityManager.RunningTaskInfo task, boolean homeTaskVisible, boolean clearedTask, boolean wasVisible) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 0;
                    if (task != null) {
                        _data.writeInt(1);
                        task.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(homeTaskVisible ? 1 : 0);
                    _data.writeInt(clearedTask ? 1 : 0);
                    if (wasVisible) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onActivityRestartAttempt(task, homeTaskVisible, clearedTask, wasVisible);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.ITaskStackListener
            public void onActivityForcedResizable(String packageName, int taskId, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(taskId);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onActivityForcedResizable(packageName, taskId, reason);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.ITaskStackListener
            public void onActivityDismissingDockedTask() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onActivityDismissingDockedTask();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.ITaskStackListener
            public void onActivityLaunchOnSecondaryDisplayFailed(ActivityManager.RunningTaskInfo taskInfo, int requestedDisplayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (taskInfo != null) {
                        _data.writeInt(1);
                        taskInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(requestedDisplayId);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onActivityLaunchOnSecondaryDisplayFailed(taskInfo, requestedDisplayId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.ITaskStackListener
            public void onActivityLaunchOnSecondaryDisplayRerouted(ActivityManager.RunningTaskInfo taskInfo, int requestedDisplayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (taskInfo != null) {
                        _data.writeInt(1);
                        taskInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(requestedDisplayId);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onActivityLaunchOnSecondaryDisplayRerouted(taskInfo, requestedDisplayId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.ITaskStackListener
            public void onTaskCreated(int taskId, ComponentName componentName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(taskId);
                    if (componentName != null) {
                        _data.writeInt(1);
                        componentName.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTaskCreated(taskId, componentName);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.ITaskStackListener
            public void onTaskRemoved(int taskId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(taskId);
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTaskRemoved(taskId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.ITaskStackListener
            public void onTaskMovedToFront(ActivityManager.RunningTaskInfo taskInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (taskInfo != null) {
                        _data.writeInt(1);
                        taskInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTaskMovedToFront(taskInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.ITaskStackListener
            public void onTaskDescriptionChanged(ActivityManager.RunningTaskInfo taskInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (taskInfo != null) {
                        _data.writeInt(1);
                        taskInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTaskDescriptionChanged(taskInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.ITaskStackListener
            public void onActivityRequestedOrientationChanged(int taskId, int requestedOrientation) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(taskId);
                    _data.writeInt(requestedOrientation);
                    boolean _status = this.mRemote.transact(13, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onActivityRequestedOrientationChanged(taskId, requestedOrientation);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.ITaskStackListener
            public void onTaskRemovalStarted(ActivityManager.RunningTaskInfo taskInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (taskInfo != null) {
                        _data.writeInt(1);
                        taskInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(14, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTaskRemovalStarted(taskInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.ITaskStackListener
            public void onTaskProfileLocked(int taskId, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(taskId);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(15, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTaskProfileLocked(taskId, userId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.ITaskStackListener
            public void onTaskSnapshotChanged(int taskId, TaskSnapshot snapshot) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(taskId);
                    if (snapshot != null) {
                        _data.writeInt(1);
                        snapshot.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(16, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTaskSnapshotChanged(taskId, snapshot);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.ITaskStackListener
            public void onBackPressedOnTaskRoot(ActivityManager.RunningTaskInfo taskInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (taskInfo != null) {
                        _data.writeInt(1);
                        taskInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(17, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBackPressedOnTaskRoot(taskInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.ITaskStackListener
            public void onTaskDisplayChanged(int taskId, int newDisplayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(taskId);
                    _data.writeInt(newDisplayId);
                    boolean _status = this.mRemote.transact(18, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTaskDisplayChanged(taskId, newDisplayId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.ITaskStackListener
            public void onRecentTaskListUpdated() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(19, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRecentTaskListUpdated();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.ITaskStackListener
            public void onRecentTaskListFrozenChanged(boolean frozen) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(frozen ? 1 : 0);
                    boolean _status = this.mRemote.transact(20, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRecentTaskListFrozenChanged(frozen);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.ITaskStackListener
            public void onTaskFocusChanged(int taskId, boolean focused) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(taskId);
                    _data.writeInt(focused ? 1 : 0);
                    boolean _status = this.mRemote.transact(21, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTaskFocusChanged(taskId, focused);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.ITaskStackListener
            public void onTaskRequestedOrientationChanged(int taskId, int requestedOrientation) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(taskId);
                    _data.writeInt(requestedOrientation);
                    boolean _status = this.mRemote.transact(22, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTaskRequestedOrientationChanged(taskId, requestedOrientation);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.ITaskStackListener
            public void onActivityRotation(int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(23, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onActivityRotation(displayId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.ITaskStackListener
            public void onTaskMovedToBack(ActivityManager.RunningTaskInfo taskInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (taskInfo != null) {
                        _data.writeInt(1);
                        taskInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(24, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTaskMovedToBack(taskInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.ITaskStackListener
            public void onLockTaskModeChanged(int mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mode);
                    boolean _status = this.mRemote.transact(25, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onLockTaskModeChanged(mode);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITaskStackListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static ITaskStackListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
