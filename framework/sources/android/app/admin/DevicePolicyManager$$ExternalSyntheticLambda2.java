package android.app.admin;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
/* loaded from: classes.dex */
public final /* synthetic */ class DevicePolicyManager$$ExternalSyntheticLambda2 implements BiConsumer {
    public static final /* synthetic */ DevicePolicyManager$$ExternalSyntheticLambda2 INSTANCE = new DevicePolicyManager$$ExternalSyntheticLambda2();

    private /* synthetic */ DevicePolicyManager$$ExternalSyntheticLambda2() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((CompletableFuture) obj).complete((Boolean) obj2);
    }
}
