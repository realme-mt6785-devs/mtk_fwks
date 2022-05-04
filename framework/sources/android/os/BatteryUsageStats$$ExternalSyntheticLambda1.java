package android.os;

import java.util.function.ToDoubleFunction;
/* loaded from: classes2.dex */
public final /* synthetic */ class BatteryUsageStats$$ExternalSyntheticLambda1 implements ToDoubleFunction {
    public static final /* synthetic */ BatteryUsageStats$$ExternalSyntheticLambda1 INSTANCE = new BatteryUsageStats$$ExternalSyntheticLambda1();

    private /* synthetic */ BatteryUsageStats$$ExternalSyntheticLambda1() {
    }

    @Override // java.util.function.ToDoubleFunction
    public final double applyAsDouble(Object obj) {
        return ((BatteryConsumer) obj).getConsumedPower();
    }
}
