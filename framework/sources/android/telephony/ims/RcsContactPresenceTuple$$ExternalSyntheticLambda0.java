package android.telephony.ims;

import java.time.Instant;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;
/* loaded from: classes3.dex */
public final /* synthetic */ class RcsContactPresenceTuple$$ExternalSyntheticLambda0 implements TemporalQuery {
    public static final /* synthetic */ RcsContactPresenceTuple$$ExternalSyntheticLambda0 INSTANCE = new RcsContactPresenceTuple$$ExternalSyntheticLambda0();

    private /* synthetic */ RcsContactPresenceTuple$$ExternalSyntheticLambda0() {
    }

    @Override // java.time.temporal.TemporalQuery
    public final Object queryFrom(TemporalAccessor temporalAccessor) {
        return Instant.from(temporalAccessor);
    }
}
