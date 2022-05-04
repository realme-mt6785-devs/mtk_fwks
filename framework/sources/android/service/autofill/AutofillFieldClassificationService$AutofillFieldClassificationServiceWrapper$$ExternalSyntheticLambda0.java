package android.service.autofill;

import android.os.Bundle;
import android.os.RemoteCallback;
import com.android.internal.util.function.NonaConsumer;
import java.util.List;
import java.util.Map;
/* loaded from: classes3.dex */
public final /* synthetic */ class AutofillFieldClassificationService$AutofillFieldClassificationServiceWrapper$$ExternalSyntheticLambda0 implements NonaConsumer {
    public static final /* synthetic */ AutofillFieldClassificationService$AutofillFieldClassificationServiceWrapper$$ExternalSyntheticLambda0 INSTANCE = new AutofillFieldClassificationService$AutofillFieldClassificationServiceWrapper$$ExternalSyntheticLambda0();

    private /* synthetic */ AutofillFieldClassificationService$AutofillFieldClassificationServiceWrapper$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.internal.util.function.NonaConsumer
    public final void accept(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        ((AutofillFieldClassificationService) obj).calculateScores((RemoteCallback) obj2, (List) obj3, (String[]) obj4, (String[]) obj5, (String) obj6, (Bundle) obj7, (Map) obj8, (Map) obj9);
    }
}
