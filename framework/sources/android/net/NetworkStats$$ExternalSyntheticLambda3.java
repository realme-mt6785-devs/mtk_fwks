package android.net;

import android.net.NetworkStats;
import java.util.function.Predicate;
/* loaded from: classes2.dex */
public final /* synthetic */ class NetworkStats$$ExternalSyntheticLambda3 implements Predicate {
    public static final /* synthetic */ NetworkStats$$ExternalSyntheticLambda3 INSTANCE = new NetworkStats$$ExternalSyntheticLambda3();

    private /* synthetic */ NetworkStats$$ExternalSyntheticLambda3() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return NetworkStats.lambda$removeEmptyEntries$1((NetworkStats.Entry) obj);
    }
}
