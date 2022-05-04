package android.app;

import android.app.AppOpsManager;
import android.util.LongSparseLongArray;
import java.util.function.Supplier;
/* loaded from: classes.dex */
public final /* synthetic */ class AppOpsManager$HistoricalOp$$ExternalSyntheticLambda2 implements Supplier {
    public final /* synthetic */ AppOpsManager.HistoricalOp f$0;

    public /* synthetic */ AppOpsManager$HistoricalOp$$ExternalSyntheticLambda2(AppOpsManager.HistoricalOp historicalOp) {
        this.f$0 = historicalOp;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        LongSparseLongArray orCreateRejectCount;
        orCreateRejectCount = this.f$0.getOrCreateRejectCount();
        return orCreateRejectCount;
    }
}
