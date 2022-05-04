package android.apphibernation;

import android.annotation.SystemApi;
import android.apphibernation.IAppHibernationService;
import android.content.Context;
import android.os.RemoteException;
import android.os.ServiceManager;
import java.util.List;
@SystemApi
/* loaded from: classes.dex */
public class AppHibernationManager {
    private static final String TAG = "AppHibernationManager";
    private final Context mContext;
    private final IAppHibernationService mIAppHibernationService = IAppHibernationService.Stub.asInterface(ServiceManager.getService("app_hibernation"));

    public AppHibernationManager(Context context) {
        this.mContext = context;
    }

    @SystemApi
    public boolean isHibernatingForUser(String packageName) {
        try {
            return this.mIAppHibernationService.isHibernatingForUser(packageName, this.mContext.getUserId());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void setHibernatingForUser(String packageName, boolean isHibernating) {
        try {
            this.mIAppHibernationService.setHibernatingForUser(packageName, this.mContext.getUserId(), isHibernating);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean isHibernatingGlobally(String packageName) {
        try {
            return this.mIAppHibernationService.isHibernatingGlobally(packageName);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void setHibernatingGlobally(String packageName, boolean isHibernating) {
        try {
            this.mIAppHibernationService.setHibernatingGlobally(packageName, isHibernating);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public List<String> getHibernatingPackagesForUser() {
        try {
            return this.mIAppHibernationService.getHibernatingPackagesForUser(this.mContext.getUserId());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }
}
