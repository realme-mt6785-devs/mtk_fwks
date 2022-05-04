package android.app;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageManager;
import android.content.res.CompatibilityInfo;
import android.content.res.Configuration;
import android.os.Parcel;
import java.util.List;
/* loaded from: classes.dex */
public interface IActivityThreadExt {
    default void shouldInterceptConfigRelaunch(int diff, Configuration configuration) {
    }

    default boolean shouldReportExtraConfig(ActivityInfo activityInfo, IPackageManager pm, Configuration config, int diff, int realChanges, boolean shouldReportChange) {
        return shouldReportChange;
    }

    default void changeToSpecialModel(String packageName) {
    }

    default boolean checkOplusExSystemService(boolean systemThread, String className) {
        return false;
    }

    default boolean checkOplusExSystemService(boolean systemThread, Intent intent) {
        return false;
    }

    default boolean getShouldCallActivityConfigChangeState(boolean shouldReportChange) {
        return shouldReportChange;
    }

    default boolean shouldConfigChangeByMultiSystem(boolean change, int realConfigChange, int diff, String pkgName) {
        return false;
    }

    default void enableDoFrameOpt(boolean enable) {
    }

    default boolean isWindowModeChanged(int currentMode, int newMode) {
        return false;
    }

    default void handleLaunchActivity() {
    }

    default void reportBindApplicationFinishedInActivityThread(Application app) {
    }

    default void setDynamicalLogEnable(boolean enable) {
    }

    default void enableDynamicalLogIfNeed() {
    }

    default void setDynamicalLogConfig(List<String> configs) {
    }

    default void initDisplayCompat(ApplicationInfo ai, CompatibilityInfo compat) {
    }

    default void onDisplayChanged(int displayId) {
    }

    default boolean isTopApp() {
        return false;
    }

    default void setTopApp(boolean topApp) {
    }

    default boolean onTransact(int code, Parcel data, Parcel reply, int flags) {
        return false;
    }

    default boolean hasImportMessage() {
        return false;
    }

    default void setImportMessage(boolean isFirstFrame) {
    }

    default void updateCurrentActivity(String activityName) {
    }

    default void resetOptState() {
    }

    default void initCompactApplicationInfo(ApplicationInfo ai) {
    }
}
