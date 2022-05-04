package android.service.autofill;

import android.os.IBinder;
import com.android.internal.util.function.NonaConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class InlineSuggestionRenderService$2$$ExternalSyntheticLambda0 implements NonaConsumer {
    public static final /* synthetic */ InlineSuggestionRenderService$2$$ExternalSyntheticLambda0 INSTANCE = new InlineSuggestionRenderService$2$$ExternalSyntheticLambda0();

    private /* synthetic */ InlineSuggestionRenderService$2$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.internal.util.function.NonaConsumer
    public final void accept(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        ((InlineSuggestionRenderService) obj).handleRenderSuggestion((IInlineSuggestionUiCallback) obj2, (InlinePresentation) obj3, ((Integer) obj4).intValue(), ((Integer) obj5).intValue(), (IBinder) obj6, ((Integer) obj7).intValue(), ((Integer) obj8).intValue(), ((Integer) obj9).intValue());
    }
}
