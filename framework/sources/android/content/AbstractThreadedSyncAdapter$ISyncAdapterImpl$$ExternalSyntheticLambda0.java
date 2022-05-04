package android.content;

import java.util.function.BiConsumer;
/* loaded from: classes.dex */
public final /* synthetic */ class AbstractThreadedSyncAdapter$ISyncAdapterImpl$$ExternalSyntheticLambda0 implements BiConsumer {
    public static final /* synthetic */ AbstractThreadedSyncAdapter$ISyncAdapterImpl$$ExternalSyntheticLambda0 INSTANCE = new AbstractThreadedSyncAdapter$ISyncAdapterImpl$$ExternalSyntheticLambda0();

    private /* synthetic */ AbstractThreadedSyncAdapter$ISyncAdapterImpl$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((AbstractThreadedSyncAdapter) obj).handleOnUnsyncableAccount((ISyncAdapterUnsyncableAccountCallback) obj2);
    }
}
