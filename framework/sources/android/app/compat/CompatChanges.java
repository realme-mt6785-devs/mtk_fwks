package android.app.compat;

import android.annotation.SystemApi;
import android.compat.Compatibility;
import android.content.Context;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import com.android.internal.compat.CompatibilityOverrideConfig;
import com.android.internal.compat.CompatibilityOverridesToRemoveConfig;
import com.android.internal.compat.IPlatformCompat;
import java.util.Map;
import java.util.Set;
@SystemApi
/* loaded from: classes.dex */
public final class CompatChanges {
    private static final ChangeIdStateCache QUERY_CACHE = new ChangeIdStateCache();

    private CompatChanges() {
    }

    public static boolean isChangeEnabled(long changeId) {
        return Compatibility.isChangeEnabled(changeId);
    }

    public static boolean isChangeEnabled(long changeId, String packageName, UserHandle user) {
        return QUERY_CACHE.query(ChangeIdStateQuery.byPackageName(changeId, packageName, user.getIdentifier())).booleanValue();
    }

    public static boolean isChangeEnabled(long changeId, int uid) {
        return QUERY_CACHE.query(ChangeIdStateQuery.byUid(changeId, uid)).booleanValue();
    }

    public static void putPackageOverrides(String packageName, Map<Long, PackageOverride> overrides) {
        IPlatformCompat platformCompat = IPlatformCompat.Stub.asInterface(ServiceManager.getService(Context.PLATFORM_COMPAT_SERVICE));
        CompatibilityOverrideConfig config = new CompatibilityOverrideConfig(overrides);
        try {
            platformCompat.putOverridesOnReleaseBuilds(config, packageName);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    public static void removePackageOverrides(String packageName, Set<Long> overridesToRemove) {
        IPlatformCompat platformCompat = IPlatformCompat.Stub.asInterface(ServiceManager.getService(Context.PLATFORM_COMPAT_SERVICE));
        CompatibilityOverridesToRemoveConfig config = new CompatibilityOverridesToRemoveConfig(overridesToRemove);
        try {
            platformCompat.removeOverridesOnReleaseBuilds(config, packageName);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }
}
