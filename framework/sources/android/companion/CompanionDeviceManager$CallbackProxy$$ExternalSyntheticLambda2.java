package android.companion;

import android.companion.CompanionDeviceManager;
import java.util.function.BiConsumer;
/* loaded from: classes.dex */
public final /* synthetic */ class CompanionDeviceManager$CallbackProxy$$ExternalSyntheticLambda2 implements BiConsumer {
    public static final /* synthetic */ CompanionDeviceManager$CallbackProxy$$ExternalSyntheticLambda2 INSTANCE = new CompanionDeviceManager$CallbackProxy$$ExternalSyntheticLambda2();

    private /* synthetic */ CompanionDeviceManager$CallbackProxy$$ExternalSyntheticLambda2() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((CompanionDeviceManager.Callback) obj).onFailure((CharSequence) obj2);
    }
}
