package android.telephony;

import com.android.internal.telephony.ISub;
import com.android.internal.util.FunctionalUtils;
/* loaded from: classes3.dex */
public final /* synthetic */ class SubscriptionManager$$ExternalSyntheticLambda12 implements FunctionalUtils.ThrowingFunction {
    public static final /* synthetic */ SubscriptionManager$$ExternalSyntheticLambda12 INSTANCE = new SubscriptionManager$$ExternalSyntheticLambda12();

    private /* synthetic */ SubscriptionManager$$ExternalSyntheticLambda12() {
    }

    @Override // com.android.internal.util.FunctionalUtils.ThrowingFunction
    public final Object applyOrThrow(Object obj) {
        return Integer.valueOf(((ISub) obj).getDefaultSubId());
    }
}
