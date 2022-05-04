package android.media;

import android.media.MediaRouter2Manager;
import java.util.function.BiConsumer;
/* loaded from: classes2.dex */
public final /* synthetic */ class MediaRouter2Manager$$ExternalSyntheticLambda10 implements BiConsumer {
    public static final /* synthetic */ MediaRouter2Manager$$ExternalSyntheticLambda10 INSTANCE = new MediaRouter2Manager$$ExternalSyntheticLambda10();

    private /* synthetic */ MediaRouter2Manager$$ExternalSyntheticLambda10() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((MediaRouter2Manager) obj).handleTransferTimeout((MediaRouter2Manager.TransferRequest) obj2);
    }
}
