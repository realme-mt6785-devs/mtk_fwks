package android.app;

import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.IBinder;
import android.os.RemoteCallback;
import com.android.internal.util.function.HexConsumer;
/* loaded from: classes.dex */
public final /* synthetic */ class ActivityThread$ApplicationThread$$ExternalSyntheticLambda1 implements HexConsumer {
    public static final /* synthetic */ ActivityThread$ApplicationThread$$ExternalSyntheticLambda1 INSTANCE = new ActivityThread$ApplicationThread$$ExternalSyntheticLambda1();

    private /* synthetic */ ActivityThread$ApplicationThread$$ExternalSyntheticLambda1() {
    }

    @Override // com.android.internal.util.function.HexConsumer
    public final void accept(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        ((ActivityThread) obj).handlePerformDirectAction((IBinder) obj2, (String) obj3, (Bundle) obj4, (CancellationSignal) obj5, (RemoteCallback) obj6);
    }
}
