package android.service.translation;

import android.os.IBinder;
import java.util.function.BiConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class TranslationService$1$$ExternalSyntheticLambda2 implements BiConsumer {
    public static final /* synthetic */ TranslationService$1$$ExternalSyntheticLambda2 INSTANCE = new TranslationService$1$$ExternalSyntheticLambda2();

    private /* synthetic */ TranslationService$1$$ExternalSyntheticLambda2() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((TranslationService) obj).handleOnConnected((IBinder) obj2);
    }
}
