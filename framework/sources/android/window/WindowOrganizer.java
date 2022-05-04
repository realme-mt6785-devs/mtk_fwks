package android.window;

import android.app.ActivityTaskManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Singleton;
/* loaded from: classes3.dex */
public class WindowOrganizer {
    private static final Singleton<IWindowOrganizerController> IWindowOrganizerControllerSingleton = new Singleton<IWindowOrganizerController>() { // from class: android.window.WindowOrganizer.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.util.Singleton
        public IWindowOrganizerController create() {
            try {
                return ActivityTaskManager.getService().getWindowOrganizerController();
            } catch (RemoteException e) {
                return null;
            }
        }
    };

    public void applyTransaction(WindowContainerTransaction t) {
        try {
            if (!t.isEmpty()) {
                getWindowOrganizerController().applyTransaction(t);
            }
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int applySyncTransaction(WindowContainerTransaction t, WindowContainerTransactionCallback callback) {
        try {
            return getWindowOrganizerController().applySyncTransaction(t, callback.mInterface);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void abortSyncTransaction(int syncId) {
        try {
            getWindowOrganizerController().abortSync(syncId);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public IBinder startTransition(int type, IBinder transitionToken, WindowContainerTransaction t) {
        try {
            return getWindowOrganizerController().startTransition(type, transitionToken, t);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int finishTransition(IBinder transitionToken, WindowContainerTransaction t, WindowContainerTransactionCallback callback) {
        try {
            return getWindowOrganizerController().finishTransition(transitionToken, t, callback != null ? callback.mInterface : null);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void registerTransitionPlayer(ITransitionPlayer player) {
        try {
            getWindowOrganizerController().registerTransitionPlayer(player);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IWindowOrganizerController getWindowOrganizerController() {
        return IWindowOrganizerControllerSingleton.get();
    }
}
