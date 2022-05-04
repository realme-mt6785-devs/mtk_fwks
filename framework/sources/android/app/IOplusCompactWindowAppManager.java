package android.app;

import android.common.IOplusCommonFeature;
import android.common.OplusFeatureList;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.ResourcesImpl;
import android.util.DisplayMetrics;
import android.view.DisplayAdjustments;
/* loaded from: classes.dex */
public interface IOplusCompactWindowAppManager extends IOplusCommonFeature {
    public static final IOplusCompactWindowAppManager DEFAULT = new IOplusCompactWindowAppManager() { // from class: android.app.IOplusCompactWindowAppManager.1
    };
    public static final int DEFAULT_COMPACT_ROTATION = -1;

    @Override // android.common.IOplusCommonFeature
    default OplusFeatureList.OplusIndex index() {
        return OplusFeatureList.OplusIndex.IOplusCompactWindowAppManager;
    }

    @Override // android.common.IOplusCommonFeature
    default IOplusCommonFeature getDefault() {
        return DEFAULT;
    }

    default boolean blockOrientationSensorEventInCompactWindowMode(Context context, int sensorType, String packageName) {
        return false;
    }

    default DisplayMetrics getCompactWindowMetrics(ResourcesImpl resImpl, DisplayMetrics originMetrics) {
        return null;
    }

    default void updateCompactWindowConfigToApplicationResourcesImpl(Configuration activityThreadConfig, ResourcesImpl impl) {
    }

    default void setCompactWindowDisplayAdjustment(ResourcesImpl impl, Configuration oldOverrideConfig, Configuration newOverrideConfig) {
    }

    default DisplayAdjustments getCompactWindowDisplayAdjustment(Resources resources) {
        return null;
    }

    default int getCompactWindowRotation(Resources resources) {
        return -1;
    }

    default void initCompactApplicationInfo(ApplicationInfo appInfo) {
    }

    default void updateCustomDarkModeForWechat(Configuration targetConfig, Configuration theadConfig, ResourcesImpl targetImpl, String currentProcessName) {
    }
}
