package android.window;

import android.app.ActivityManager;
import android.graphics.Rect;
import android.media.MediaRouter2$$ExternalSyntheticLambda8;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.util.Log;
import android.view.SurfaceControl;
import android.window.ITaskOrganizer;
import android.window.TaskOrganizer;
import java.util.List;
import java.util.concurrent.Executor;
/* loaded from: classes3.dex */
public class TaskOrganizer extends WindowOrganizer {
    private static boolean DEBUG_PANIC;
    private final Executor mExecutor;
    private final ITaskOrganizer mInterface;
    private final ITaskOrganizerController mTaskOrganizerController;

    static {
        boolean z = false;
        if (SystemProperties.getBoolean("persist.sys.assert.panic", false) && "0".equals(SystemProperties.get("persist.sys.agingtest", "0"))) {
            z = true;
        }
        DEBUG_PANIC = z;
    }

    public TaskOrganizer() {
        this(null, null);
    }

    public TaskOrganizer(ITaskOrganizerController taskOrganizerController, Executor executor) {
        this.mInterface = new AnonymousClass1();
        this.mExecutor = executor != null ? executor : MediaRouter2$$ExternalSyntheticLambda8.INSTANCE;
        this.mTaskOrganizerController = taskOrganizerController != null ? taskOrganizerController : getController();
    }

    public List<TaskAppearedInfo> registerOrganizer() {
        try {
            return this.mTaskOrganizerController.registerTaskOrganizer(this.mInterface).getList();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void unregisterOrganizer() {
        try {
            this.mTaskOrganizerController.unregisterTaskOrganizer(this.mInterface);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void addStartingWindow(StartingWindowInfo info, IBinder appToken) {
    }

    public void removeStartingWindow(int taskId, SurfaceControl leash, Rect frame, boolean playRevealAnimation) {
    }

    public void copySplashScreenView(int taskId) {
    }

    public void onAppSplashScreenViewRemoved(int taskId) {
    }

    public void onTaskAppeared(ActivityManager.RunningTaskInfo taskInfo, SurfaceControl leash) {
    }

    public void onTaskVanished(ActivityManager.RunningTaskInfo taskInfo) {
    }

    public void onTaskInfoChanged(ActivityManager.RunningTaskInfo taskInfo) {
    }

    public void onBackPressedOnTaskRoot(ActivityManager.RunningTaskInfo taskInfo) {
    }

    public void createRootTask(int displayId, int windowingMode, IBinder launchCookie) {
        try {
            this.mTaskOrganizerController.createRootTask(displayId, windowingMode, launchCookie);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean deleteRootTask(WindowContainerToken task) {
        try {
            return this.mTaskOrganizerController.deleteRootTask(task);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<ActivityManager.RunningTaskInfo> getChildTasks(WindowContainerToken parent, int[] activityTypes) {
        try {
            return this.mTaskOrganizerController.getChildTasks(parent, activityTypes);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<ActivityManager.RunningTaskInfo> getRootTasks(int displayId, int[] activityTypes) {
        try {
            return this.mTaskOrganizerController.getRootTasks(displayId, activityTypes);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public WindowContainerToken getImeTarget(int display) {
        try {
            return this.mTaskOrganizerController.getImeTarget(display);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setInterceptBackPressedOnTaskRoot(WindowContainerToken task, boolean interceptBackPressed) {
        try {
            this.mTaskOrganizerController.setInterceptBackPressedOnTaskRoot(task, interceptBackPressed);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void restartTaskTopActivityProcessIfVisible(WindowContainerToken task) {
        try {
            this.mTaskOrganizerController.restartTaskTopActivityProcessIfVisible(task);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public Executor getExecutor() {
        return this.mExecutor;
    }

    /* renamed from: android.window.TaskOrganizer$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass1 extends ITaskOrganizer.Stub {
        AnonymousClass1() {
        }

        @Override // android.window.ITaskOrganizer
        public void addStartingWindow(final StartingWindowInfo windowInfo, final IBinder appToken) {
            TaskOrganizer.this.mExecutor.execute(new Runnable() { // from class: android.window.TaskOrganizer$1$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    TaskOrganizer.AnonymousClass1.this.lambda$addStartingWindow$0$TaskOrganizer$1(windowInfo, appToken);
                }
            });
        }

        public /* synthetic */ void lambda$addStartingWindow$0$TaskOrganizer$1(StartingWindowInfo windowInfo, IBinder appToken) {
            TaskOrganizer.this.addStartingWindow(windowInfo, appToken);
        }

        public /* synthetic */ void lambda$removeStartingWindow$1$TaskOrganizer$1(int taskId, SurfaceControl leash, Rect frame, boolean playRevealAnimation) {
            TaskOrganizer.this.removeStartingWindow(taskId, leash, frame, playRevealAnimation);
        }

        @Override // android.window.ITaskOrganizer
        public void removeStartingWindow(final int taskId, final SurfaceControl leash, final Rect frame, final boolean playRevealAnimation) {
            TaskOrganizer.this.mExecutor.execute(new Runnable() { // from class: android.window.TaskOrganizer$1$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    TaskOrganizer.AnonymousClass1.this.lambda$removeStartingWindow$1$TaskOrganizer$1(taskId, leash, frame, playRevealAnimation);
                }
            });
        }

        @Override // android.window.ITaskOrganizer
        public void copySplashScreenView(final int taskId) {
            TaskOrganizer.this.mExecutor.execute(new Runnable() { // from class: android.window.TaskOrganizer$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    TaskOrganizer.AnonymousClass1.this.lambda$copySplashScreenView$2$TaskOrganizer$1(taskId);
                }
            });
        }

        public /* synthetic */ void lambda$copySplashScreenView$2$TaskOrganizer$1(int taskId) {
            TaskOrganizer.this.copySplashScreenView(taskId);
        }

        public /* synthetic */ void lambda$onAppSplashScreenViewRemoved$3$TaskOrganizer$1(int taskId) {
            TaskOrganizer.this.onAppSplashScreenViewRemoved(taskId);
        }

        @Override // android.window.ITaskOrganizer
        public void onAppSplashScreenViewRemoved(final int taskId) {
            TaskOrganizer.this.mExecutor.execute(new Runnable() { // from class: android.window.TaskOrganizer$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    TaskOrganizer.AnonymousClass1.this.lambda$onAppSplashScreenViewRemoved$3$TaskOrganizer$1(taskId);
                }
            });
        }

        @Override // android.window.ITaskOrganizer
        public void onTaskAppeared(final ActivityManager.RunningTaskInfo taskInfo, final SurfaceControl leash) {
            if (TaskOrganizer.DEBUG_PANIC) {
                Log.d("TaskOrganizer", "onTaskAppeared: taskInfo=" + taskInfo + ", leash=" + leash);
            }
            TaskOrganizer.this.mExecutor.execute(new Runnable() { // from class: android.window.TaskOrganizer$1$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    TaskOrganizer.AnonymousClass1.this.lambda$onTaskAppeared$4$TaskOrganizer$1(taskInfo, leash);
                }
            });
        }

        public /* synthetic */ void lambda$onTaskAppeared$4$TaskOrganizer$1(ActivityManager.RunningTaskInfo taskInfo, SurfaceControl leash) {
            TaskOrganizer.this.onTaskAppeared(taskInfo, leash);
        }

        public /* synthetic */ void lambda$onTaskVanished$5$TaskOrganizer$1(ActivityManager.RunningTaskInfo taskInfo) {
            TaskOrganizer.this.onTaskVanished(taskInfo);
        }

        @Override // android.window.ITaskOrganizer
        public void onTaskVanished(final ActivityManager.RunningTaskInfo taskInfo) {
            TaskOrganizer.this.mExecutor.execute(new Runnable() { // from class: android.window.TaskOrganizer$1$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    TaskOrganizer.AnonymousClass1.this.lambda$onTaskVanished$5$TaskOrganizer$1(taskInfo);
                }
            });
        }

        public /* synthetic */ void lambda$onTaskInfoChanged$6$TaskOrganizer$1(ActivityManager.RunningTaskInfo info) {
            TaskOrganizer.this.onTaskInfoChanged(info);
        }

        @Override // android.window.ITaskOrganizer
        public void onTaskInfoChanged(final ActivityManager.RunningTaskInfo info) {
            TaskOrganizer.this.mExecutor.execute(new Runnable() { // from class: android.window.TaskOrganizer$1$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    TaskOrganizer.AnonymousClass1.this.lambda$onTaskInfoChanged$6$TaskOrganizer$1(info);
                }
            });
        }

        public /* synthetic */ void lambda$onBackPressedOnTaskRoot$7$TaskOrganizer$1(ActivityManager.RunningTaskInfo info) {
            TaskOrganizer.this.onBackPressedOnTaskRoot(info);
        }

        @Override // android.window.ITaskOrganizer
        public void onBackPressedOnTaskRoot(final ActivityManager.RunningTaskInfo info) {
            TaskOrganizer.this.mExecutor.execute(new Runnable() { // from class: android.window.TaskOrganizer$1$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    TaskOrganizer.AnonymousClass1.this.lambda$onBackPressedOnTaskRoot$7$TaskOrganizer$1(info);
                }
            });
        }
    }

    private ITaskOrganizerController getController() {
        try {
            return getWindowOrganizerController().getTaskOrganizerController();
        } catch (RemoteException e) {
            return null;
        }
    }
}
