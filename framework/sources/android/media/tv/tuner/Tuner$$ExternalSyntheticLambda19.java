package android.media.tv.tuner;

import android.media.tv.tuner.frontend.FrontendInfo;
import java.util.Objects;
import java.util.function.Predicate;
/* loaded from: classes2.dex */
public final /* synthetic */ class Tuner$$ExternalSyntheticLambda19 implements Predicate {
    public static final /* synthetic */ Tuner$$ExternalSyntheticLambda19 INSTANCE = new Tuner$$ExternalSyntheticLambda19();

    private /* synthetic */ Tuner$$ExternalSyntheticLambda19() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return Objects.nonNull((FrontendInfo) obj);
    }
}
