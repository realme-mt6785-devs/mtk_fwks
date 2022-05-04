package android.app;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.ResourcesImpl;
import android.content.res.ResourcesKey;
import android.os.IBinder;
/* loaded from: classes.dex */
public interface IResourcesManagerExt {
    default void updateResourcesForActivity(String packageName, IBinder activityToken, Configuration overrideConfig, int displayId) {
    }

    default void redirectResourcesToNewImplLocked(Resources r, ResourcesImpl impl, boolean update) {
    }

    default void applyConfigurationToResources(Configuration config, int change) {
    }

    default void updateCompactWindowConfigToApplicationResourcesImpl(Configuration activityThreadConfig, ResourcesImpl impl) {
    }

    default void setCompactWindowDisplayAdjustment(ResourcesImpl impl, Configuration oldOverrideConfig, Configuration newOverrideConfig) {
    }

    default void updateCustomDarkModeForWechat(Configuration targetConfig, Configuration theadConfig, ResourcesImpl targetImpl, String currentProcessName) {
    }

    default boolean canUseOverlayConfiguration(ResourcesKey resourcesKey, Configuration configuration) {
        return true;
    }
}
