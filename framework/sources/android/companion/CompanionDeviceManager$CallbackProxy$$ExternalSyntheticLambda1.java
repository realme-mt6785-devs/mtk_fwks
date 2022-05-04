package android.companion;

import android.companion.CompanionDeviceManager;
import android.content.IntentSender;
import java.util.function.BiConsumer;
/* loaded from: classes.dex */
public final /* synthetic */ class CompanionDeviceManager$CallbackProxy$$ExternalSyntheticLambda1 implements BiConsumer {
    public static final /* synthetic */ CompanionDeviceManager$CallbackProxy$$ExternalSyntheticLambda1 INSTANCE = new CompanionDeviceManager$CallbackProxy$$ExternalSyntheticLambda1();

    private /* synthetic */ CompanionDeviceManager$CallbackProxy$$ExternalSyntheticLambda1() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((CompanionDeviceManager.Callback) obj).onDeviceFound((IntentSender) obj2);
    }
}
