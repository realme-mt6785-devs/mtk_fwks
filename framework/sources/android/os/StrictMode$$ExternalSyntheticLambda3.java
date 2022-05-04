package android.os;

import android.os.strictmode.NonSdkApiUsedViolation;
import java.util.function.Consumer;
/* loaded from: classes2.dex */
public final /* synthetic */ class StrictMode$$ExternalSyntheticLambda3 implements Consumer {
    public static final /* synthetic */ StrictMode$$ExternalSyntheticLambda3 INSTANCE = new StrictMode$$ExternalSyntheticLambda3();

    private /* synthetic */ StrictMode$$ExternalSyntheticLambda3() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        StrictMode.onVmPolicyViolation(new NonSdkApiUsedViolation((String) obj));
    }
}
