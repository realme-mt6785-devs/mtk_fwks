package android.service.displayhash;

import android.os.RemoteCallback;
import java.util.function.BiConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class DisplayHashingService$DisplayHashingServiceWrapper$$ExternalSyntheticLambda2 implements BiConsumer {
    public static final /* synthetic */ DisplayHashingService$DisplayHashingServiceWrapper$$ExternalSyntheticLambda2 INSTANCE = new DisplayHashingService$DisplayHashingServiceWrapper$$ExternalSyntheticLambda2();

    private /* synthetic */ DisplayHashingService$DisplayHashingServiceWrapper$$ExternalSyntheticLambda2() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((DisplayHashingService) obj).getDisplayHashAlgorithms((RemoteCallback) obj2);
    }
}
