package android.service.translation;

import java.util.function.BiConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class TranslationService$2$$ExternalSyntheticLambda1 implements BiConsumer {
    public static final /* synthetic */ TranslationService$2$$ExternalSyntheticLambda1 INSTANCE = new TranslationService$2$$ExternalSyntheticLambda1();

    private /* synthetic */ TranslationService$2$$ExternalSyntheticLambda1() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((TranslationService) obj).onFinishTranslationSession(((Integer) obj2).intValue());
    }
}
