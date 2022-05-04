package android.os;

import android.os.BatteryStats;
/* loaded from: classes2.dex */
public final /* synthetic */ class BatteryStats$$ExternalSyntheticLambda0 implements BatteryStats.IntToString {
    public static final /* synthetic */ BatteryStats$$ExternalSyntheticLambda0 INSTANCE = new BatteryStats$$ExternalSyntheticLambda0();

    private /* synthetic */ BatteryStats$$ExternalSyntheticLambda0() {
    }

    @Override // android.os.BatteryStats.IntToString
    public final String applyAsString(int i) {
        return UserHandle.formatUid(i);
    }
}
