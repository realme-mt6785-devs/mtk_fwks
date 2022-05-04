package android.service.displayhash;

import android.graphics.Rect;
import android.hardware.HardwareBuffer;
import android.service.displayhash.DisplayHashingService;
import com.android.internal.util.function.HexConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class DisplayHashingService$DisplayHashingServiceWrapper$$ExternalSyntheticLambda0 implements HexConsumer {
    public static final /* synthetic */ DisplayHashingService$DisplayHashingServiceWrapper$$ExternalSyntheticLambda0 INSTANCE = new DisplayHashingService$DisplayHashingServiceWrapper$$ExternalSyntheticLambda0();

    private /* synthetic */ DisplayHashingService$DisplayHashingServiceWrapper$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.internal.util.function.HexConsumer
    public final void accept(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        ((DisplayHashingService) obj).onGenerateDisplayHash((byte[]) obj2, (HardwareBuffer) obj3, (Rect) obj4, (String) obj5, (DisplayHashingService.DisplayHashingServiceWrapper.AnonymousClass1) obj6);
    }
}
