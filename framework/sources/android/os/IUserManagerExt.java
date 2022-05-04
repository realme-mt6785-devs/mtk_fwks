package android.os;
/* loaded from: classes2.dex */
public interface IUserManagerExt {
    default boolean isMultiAppUserId(int userId) {
        return false;
    }
}
