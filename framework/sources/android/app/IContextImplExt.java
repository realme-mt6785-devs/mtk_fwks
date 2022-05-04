package android.app;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.storage.StorageManager;
import java.io.File;
/* loaded from: classes.dex */
public interface IContextImplExt {
    default boolean hookStartServiceCommon(Context context, Intent service) {
        return false;
    }

    default void hookSetResources(Resources mResources, String mPackageName) {
    }

    default void checkExternalStorageStateMounted(String packageName) {
    }

    default void fixupDirIfNeed(File dir, StorageManager sm) {
    }
}
