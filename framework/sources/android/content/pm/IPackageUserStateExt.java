package android.content.pm;
/* loaded from: classes.dex */
public interface IPackageUserStateExt {
    public static final String ATTR_PENDING_DATA_MIG = "pendingDataMig";

    default void setPendingDataMig(boolean pendingDataMig) {
    }

    default boolean isPendingDataMig() {
        return false;
    }

    default int getFreezeState() {
        return 0;
    }

    default int getFreezeFlag() {
        return 0;
    }

    default void setFreezeState(int freezeState) {
    }

    default void setFreezeFlag(int freezeFlag) {
    }

    default void setExtraData(IPackageUserStateExt mExt) {
    }

    default boolean ignorePackageDisabledInIsEnabled(int enabled, int flags) {
        return false;
    }
}
