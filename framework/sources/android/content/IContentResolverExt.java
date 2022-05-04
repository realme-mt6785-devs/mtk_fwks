package android.content;

import android.net.Uri;
import java.util.ArrayList;
/* loaded from: classes.dex */
public interface IContentResolverExt {
    default boolean hookQuery(Uri uri, int pid, int uid, String access) {
        return true;
    }

    default boolean hookInsert(Uri uri, int pid, int uid, String access) {
        return true;
    }

    default boolean hookDelete(Uri uri, int pid, int uid, String access) {
        return true;
    }

    default boolean hookApplyBatch(ArrayList<ContentProviderOperation> operations, int pid, int uid, String access) {
        return true;
    }
}
