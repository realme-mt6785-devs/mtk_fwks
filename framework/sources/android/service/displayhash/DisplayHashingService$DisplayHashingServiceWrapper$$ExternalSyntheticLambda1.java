package android.service.displayhash;

import android.os.RemoteCallback;
import android.view.displayhash.DisplayHash;
import com.android.internal.util.function.QuadConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class DisplayHashingService$DisplayHashingServiceWrapper$$ExternalSyntheticLambda1 implements QuadConsumer {
    public static final /* synthetic */ DisplayHashingService$DisplayHashingServiceWrapper$$ExternalSyntheticLambda1 INSTANCE = new DisplayHashingService$DisplayHashingServiceWrapper$$ExternalSyntheticLambda1();

    private /* synthetic */ DisplayHashingService$DisplayHashingServiceWrapper$$ExternalSyntheticLambda1() {
    }

    @Override // com.android.internal.util.function.QuadConsumer
    public final void accept(Object obj, Object obj2, Object obj3, Object obj4) {
        ((DisplayHashingService) obj).verifyDisplayHash((byte[]) obj2, (DisplayHash) obj3, (RemoteCallback) obj4);
    }
}
