package android.companion;

import java.util.function.BiConsumer;
/* loaded from: classes.dex */
public final /* synthetic */ class CompanionDeviceService$Stub$$ExternalSyntheticLambda1 implements BiConsumer {
    public static final /* synthetic */ CompanionDeviceService$Stub$$ExternalSyntheticLambda1 INSTANCE = new CompanionDeviceService$Stub$$ExternalSyntheticLambda1();

    private /* synthetic */ CompanionDeviceService$Stub$$ExternalSyntheticLambda1() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((CompanionDeviceService) obj).onDeviceDisappeared((String) obj2);
    }
}
