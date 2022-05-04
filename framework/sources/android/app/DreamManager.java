package android.app;

import android.content.ComponentName;
import android.content.Context;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.service.dreams.DreamService;
import android.service.dreams.IDreamManager;
/* loaded from: classes.dex */
public class DreamManager {
    private final Context mContext;
    private final IDreamManager mService = IDreamManager.Stub.asInterface(ServiceManager.getServiceOrThrow(DreamService.DREAM_SERVICE));

    public DreamManager(Context context) throws ServiceManager.ServiceNotFoundException {
        this.mContext = context;
    }

    public void startDream(ComponentName name) {
        try {
            this.mService.dream();
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    public void stopDream() {
        try {
            this.mService.awaken();
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    public void setActiveDream(ComponentName dreamComponent) {
        ComponentName[] dreams = {dreamComponent};
        try {
            this.mService.setDreamComponentsForUser(this.mContext.getUserId(), dreams);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    public boolean isDreaming() {
        try {
            return this.mService.isDreaming();
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
            return false;
        }
    }
}
