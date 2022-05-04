package android.telephony;

import com.android.internal.telephony.ISub;
import com.android.internal.util.FunctionalUtils;
/* loaded from: classes3.dex */
public final /* synthetic */ class SubscriptionManager$$ExternalSyntheticLambda8 implements FunctionalUtils.ThrowingBiFunction {
    public static final /* synthetic */ SubscriptionManager$$ExternalSyntheticLambda8 INSTANCE = new SubscriptionManager$$ExternalSyntheticLambda8();

    private /* synthetic */ SubscriptionManager$$ExternalSyntheticLambda8() {
    }

    @Override // com.android.internal.util.FunctionalUtils.ThrowingBiFunction
    public final Object applyOrThrow(Object obj, Object obj2) {
        return Integer.valueOf(((ISub) obj).getSlotIndex(((Integer) obj2).intValue()));
    }
}
