package android.service.translation;

import android.view.translation.TranslationContext;
import com.android.internal.os.IResultReceiver;
import com.android.internal.util.function.QuadConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class TranslationService$1$$ExternalSyntheticLambda0 implements QuadConsumer {
    public static final /* synthetic */ TranslationService$1$$ExternalSyntheticLambda0 INSTANCE = new TranslationService$1$$ExternalSyntheticLambda0();

    private /* synthetic */ TranslationService$1$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.internal.util.function.QuadConsumer
    public final void accept(Object obj, Object obj2, Object obj3, Object obj4) {
        ((TranslationService) obj).handleOnCreateTranslationSession((TranslationContext) obj2, ((Integer) obj3).intValue(), (IResultReceiver) obj4);
    }
}
