package android.telecom.Logging;

import android.telecom.Logging.EventManager;
import android.util.Pair;
import java.util.function.ToLongFunction;
/* loaded from: classes3.dex */
public final /* synthetic */ class EventManager$$ExternalSyntheticLambda1 implements ToLongFunction {
    public static final /* synthetic */ EventManager$$ExternalSyntheticLambda1 INSTANCE = new EventManager$$ExternalSyntheticLambda1();

    private /* synthetic */ EventManager$$ExternalSyntheticLambda1() {
    }

    @Override // java.util.function.ToLongFunction
    public final long applyAsLong(Object obj) {
        long j;
        j = ((EventManager.Event) ((Pair) obj).second).time;
        return j;
    }
}
