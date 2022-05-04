package android.net;

import android.net.NetworkPolicyManager;
import com.android.internal.util.function.TriConsumer;
/* loaded from: classes2.dex */
public final /* synthetic */ class NetworkPolicyManager$$ExternalSyntheticLambda0 implements TriConsumer {
    public static final /* synthetic */ NetworkPolicyManager$$ExternalSyntheticLambda0 INSTANCE = new NetworkPolicyManager$$ExternalSyntheticLambda0();

    private /* synthetic */ NetworkPolicyManager$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((NetworkPolicyManager.NetworkPolicyCallback) obj).onUidBlockedReasonChanged(((Integer) obj2).intValue(), ((Integer) obj3).intValue());
    }
}
