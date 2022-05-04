package android.telephony;

import com.android.internal.telephony.ISub;
import com.android.internal.util.FunctionalUtils;
/* loaded from: classes3.dex */
public final /* synthetic */ class SubscriptionManager$$ExternalSyntheticLambda9 implements FunctionalUtils.ThrowingFunction {
    public static final /* synthetic */ SubscriptionManager$$ExternalSyntheticLambda9 INSTANCE = new SubscriptionManager$$ExternalSyntheticLambda9();

    private /* synthetic */ SubscriptionManager$$ExternalSyntheticLambda9() {
    }

    @Override // com.android.internal.util.FunctionalUtils.ThrowingFunction
    public final Object applyOrThrow(Object obj) {
        return Integer.valueOf(((ISub) obj).getActiveDataSubscriptionId());
    }
}
