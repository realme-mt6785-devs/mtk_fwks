package android.app.compat;

import android.app.PropertyInvalidatedCache;
import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import android.os.ServiceManager;
import com.android.internal.compat.IPlatformCompat;
/* loaded from: classes.dex */
public final class ChangeIdStateCache extends PropertyInvalidatedCache<ChangeIdStateQuery, Boolean> {
    private static final String CACHE_KEY = "cache_key.is_compat_change_enabled";
    private static final int MAX_ENTRIES = 20;
    private static boolean sDisabled = false;
    private static IPlatformCompat platformCompat = null;

    public ChangeIdStateCache() {
        super(20, CACHE_KEY);
    }

    public static void disable() {
        sDisabled = true;
    }

    public static void invalidate() {
        if (!sDisabled) {
            PropertyInvalidatedCache.invalidateCache(CACHE_KEY);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Finally extract failed */
    public Boolean recompute(ChangeIdStateQuery query) {
        if (platformCompat == null) {
            platformCompat = IPlatformCompat.Stub.asInterface(ServiceManager.getService(Context.PLATFORM_COMPAT_SERVICE));
        }
        long token = Binder.clearCallingIdentity();
        try {
            try {
                if (query.type == 0) {
                    Boolean valueOf = Boolean.valueOf(platformCompat.isChangeEnabledByPackageName(query.changeId, query.packageName, query.userId));
                    Binder.restoreCallingIdentity(token);
                    return valueOf;
                } else if (query.type == 1) {
                    Boolean valueOf2 = Boolean.valueOf(platformCompat.isChangeEnabledByUid(query.changeId, query.uid));
                    Binder.restoreCallingIdentity(token);
                    return valueOf2;
                } else {
                    throw new IllegalArgumentException("Invalid query type: " + query.type);
                }
            } catch (RemoteException e) {
                e.rethrowFromSystemServer();
                Binder.restoreCallingIdentity(token);
                throw new IllegalStateException("Could not recompute value!");
            }
        } catch (Throwable th) {
            Binder.restoreCallingIdentity(token);
            throw th;
        }
    }
}
