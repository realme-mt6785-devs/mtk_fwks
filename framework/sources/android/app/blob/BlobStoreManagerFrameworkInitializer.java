package android.app.blob;

import android.app.SystemServiceRegistry;
import android.app.blob.IBlobStoreManager;
import android.content.Context;
import android.os.IBinder;
/* loaded from: classes.dex */
public class BlobStoreManagerFrameworkInitializer {
    public static void initialize() {
        SystemServiceRegistry.registerContextAwareService(Context.BLOB_STORE_SERVICE, BlobStoreManager.class, BlobStoreManagerFrameworkInitializer$$ExternalSyntheticLambda0.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BlobStoreManager lambda$initialize$0(Context context, IBinder service) {
        return new BlobStoreManager(context, IBlobStoreManager.Stub.asInterface(service));
    }
}
