package android.app.admin;

import java.util.function.Function;
/* loaded from: classes.dex */
public final /* synthetic */ class SystemUpdatePolicy$$ExternalSyntheticLambda0 implements Function {
    public static final /* synthetic */ SystemUpdatePolicy$$ExternalSyntheticLambda0 INSTANCE = new SystemUpdatePolicy$$ExternalSyntheticLambda0();

    private /* synthetic */ SystemUpdatePolicy$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        String freezePeriod;
        freezePeriod = ((FreezePeriod) obj).toString();
        return freezePeriod;
    }
}
