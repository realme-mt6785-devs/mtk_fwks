package android.net;

import android.net.NetworkStats;
import java.util.function.Predicate;
/* loaded from: classes2.dex */
public final /* synthetic */ class NetworkStats$$ExternalSyntheticLambda2 implements Predicate {
    public static final /* synthetic */ NetworkStats$$ExternalSyntheticLambda2 INSTANCE = new NetworkStats$$ExternalSyntheticLambda2();

    private /* synthetic */ NetworkStats$$ExternalSyntheticLambda2() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return NetworkStats.lambda$filterDebugEntries$3((NetworkStats.Entry) obj);
    }
}
