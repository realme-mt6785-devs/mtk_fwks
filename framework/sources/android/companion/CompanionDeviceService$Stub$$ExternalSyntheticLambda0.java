package android.companion;

import java.util.function.BiConsumer;
/* loaded from: classes.dex */
public final /* synthetic */ class CompanionDeviceService$Stub$$ExternalSyntheticLambda0 implements BiConsumer {
    public static final /* synthetic */ CompanionDeviceService$Stub$$ExternalSyntheticLambda0 INSTANCE = new CompanionDeviceService$Stub$$ExternalSyntheticLambda0();

    private /* synthetic */ CompanionDeviceService$Stub$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((CompanionDeviceService) obj).onDeviceAppeared((String) obj2);
    }
}
