package android.content.pm;
/* loaded from: classes.dex */
public interface IVersionedPackageExt {
    public static final int DELETE_PACKAGE_MULTI_SYSTEM_FLAG = 1;

    default void setCallInfo(int callUid, int callPid) {
    }

    default int getCallUid() {
        return -1;
    }

    default int getCallPid() {
        return -1;
    }

    default void setDeleteFlag(int flag) {
    }

    default int getDeleteFlag() {
        return 0;
    }
}
