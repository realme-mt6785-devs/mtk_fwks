package android.service.translation;

import java.util.function.Consumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class TranslationService$1$$ExternalSyntheticLambda3 implements Consumer {
    public static final /* synthetic */ TranslationService$1$$ExternalSyntheticLambda3 INSTANCE = new TranslationService$1$$ExternalSyntheticLambda3();

    private /* synthetic */ TranslationService$1$$ExternalSyntheticLambda3() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((TranslationService) obj).onDisconnected();
    }
}
