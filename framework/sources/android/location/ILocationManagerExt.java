package android.location;
/* loaded from: classes2.dex */
public interface ILocationManagerExt {
    default boolean checkPermission(int pid, int uid, String access) {
        return true;
    }
}
