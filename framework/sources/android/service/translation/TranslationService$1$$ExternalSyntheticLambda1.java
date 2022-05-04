package android.service.translation;

import android.os.ResultReceiver;
import com.android.internal.util.function.QuadConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class TranslationService$1$$ExternalSyntheticLambda1 implements QuadConsumer {
    public static final /* synthetic */ TranslationService$1$$ExternalSyntheticLambda1 INSTANCE = new TranslationService$1$$ExternalSyntheticLambda1();

    private /* synthetic */ TranslationService$1$$ExternalSyntheticLambda1() {
    }

    @Override // com.android.internal.util.function.QuadConsumer
    public final void accept(Object obj, Object obj2, Object obj3, Object obj4) {
        ((TranslationService) obj).handleOnTranslationCapabilitiesRequest(((Integer) obj2).intValue(), ((Integer) obj3).intValue(), (ResultReceiver) obj4);
    }
}
