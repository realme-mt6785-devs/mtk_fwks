package android.telephony;

import com.android.internal.telephony.ISub;
import com.android.internal.util.FunctionalUtils;
/* loaded from: classes3.dex */
public final /* synthetic */ class SubscriptionManager$$ExternalSyntheticLambda10 implements FunctionalUtils.ThrowingFunction {
    public static final /* synthetic */ SubscriptionManager$$ExternalSyntheticLambda10 INSTANCE = new SubscriptionManager$$ExternalSyntheticLambda10();

    private /* synthetic */ SubscriptionManager$$ExternalSyntheticLambda10() {
    }

    @Override // com.android.internal.util.FunctionalUtils.ThrowingFunction
    public final Object applyOrThrow(Object obj) {
        return Integer.valueOf(((ISub) obj).getDefaultDataSubId());
    }
}
