package android.telephony;

import com.android.internal.telephony.ISub;
import com.android.internal.util.FunctionalUtils;
/* loaded from: classes3.dex */
public final /* synthetic */ class SubscriptionManager$$ExternalSyntheticLambda11 implements FunctionalUtils.ThrowingFunction {
    public static final /* synthetic */ SubscriptionManager$$ExternalSyntheticLambda11 INSTANCE = new SubscriptionManager$$ExternalSyntheticLambda11();

    private /* synthetic */ SubscriptionManager$$ExternalSyntheticLambda11() {
    }

    @Override // com.android.internal.util.FunctionalUtils.ThrowingFunction
    public final Object applyOrThrow(Object obj) {
        return Integer.valueOf(((ISub) obj).getDefaultSmsSubId());
    }
}
