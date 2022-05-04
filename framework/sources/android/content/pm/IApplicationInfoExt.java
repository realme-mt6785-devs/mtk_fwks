package android.content.pm;

import android.os.Parcel;
/* loaded from: classes.dex */
public interface IApplicationInfoExt {
    default void init(ApplicationInfo refApplicationInfo) {
    }

    default void init(ApplicationInfo refApplicationInfo, ApplicationInfo origin) {
    }

    default void init(ApplicationInfo refApplicationInfo, Parcel source) {
    }

    default String[] getSpecialNativeLibraryDirs() {
        return new String[0];
    }

    default int getPrivateFlags() {
        return 0;
    }

    default void setAppScale(float scale) {
    }

    default float getAppScale() {
        return 0.0f;
    }

    default void setNewAppScale(float scale) {
    }

    default float getNewAppScale() {
        return 0.0f;
    }

    default void setAppInvScale(float scale) {
    }

    default float getAppInvScale() {
        return 0.0f;
    }

    default int getOverrideDensity() {
        return 0;
    }

    default void setOverrideDensity(int newValue) {
    }

    default int getCompatDensity() {
        return 0;
    }

    default void setCompatDensity(int newValue) {
    }

    default void setOplusFreezeState(int state) {
    }

    default int getOplusFreezeState() {
        return 0;
    }

    default boolean firstCheckSupportLowResolution() {
        return false;
    }

    default boolean enableLowResolution() {
        return false;
    }

    default void setEnableLowResolution(boolean enable) {
    }

    default int getLaunchingDisplayId() {
        return 0;
    }

    default void setLaunchingDisplayId(int displayId) {
    }

    default void setCmpactModeFlag(int flag) {
    }

    default int getCompatModeFlag() {
        return 0;
    }

    default void writeToParcel(Parcel dest, int flags) {
    }
}
