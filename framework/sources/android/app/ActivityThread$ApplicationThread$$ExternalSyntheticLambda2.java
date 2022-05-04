package android.app;

import android.os.CancellationSignal;
import android.os.IBinder;
import android.os.RemoteCallback;
import com.android.internal.app.IVoiceInteractor;
import com.android.internal.util.function.QuintConsumer;
/* loaded from: classes.dex */
public final /* synthetic */ class ActivityThread$ApplicationThread$$ExternalSyntheticLambda2 implements QuintConsumer {
    public static final /* synthetic */ ActivityThread$ApplicationThread$$ExternalSyntheticLambda2 INSTANCE = new ActivityThread$ApplicationThread$$ExternalSyntheticLambda2();

    private /* synthetic */ ActivityThread$ApplicationThread$$ExternalSyntheticLambda2() {
    }

    @Override // com.android.internal.util.function.QuintConsumer
    public final void accept(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        ((ActivityThread) obj).handleRequestDirectActions((IBinder) obj2, (IVoiceInteractor) obj3, (CancellationSignal) obj4, (RemoteCallback) obj5);
    }
}
