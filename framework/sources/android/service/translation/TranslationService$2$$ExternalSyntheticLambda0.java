package android.service.translation;

import android.os.CancellationSignal;
import android.view.translation.TranslationRequest;
import com.android.internal.util.function.QuintConsumer;
import java.util.function.Consumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class TranslationService$2$$ExternalSyntheticLambda0 implements QuintConsumer {
    public static final /* synthetic */ TranslationService$2$$ExternalSyntheticLambda0 INSTANCE = new TranslationService$2$$ExternalSyntheticLambda0();

    private /* synthetic */ TranslationService$2$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.internal.util.function.QuintConsumer
    public final void accept(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        ((TranslationService) obj).onTranslationRequest((TranslationRequest) obj2, ((Integer) obj3).intValue(), (CancellationSignal) obj4, (Consumer) obj5);
    }
}
