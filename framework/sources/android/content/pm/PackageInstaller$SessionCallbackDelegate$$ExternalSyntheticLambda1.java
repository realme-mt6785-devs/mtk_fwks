package android.content.pm;

import android.content.pm.PackageInstaller;
import com.android.internal.util.function.TriConsumer;
/* loaded from: classes.dex */
public final /* synthetic */ class PackageInstaller$SessionCallbackDelegate$$ExternalSyntheticLambda1 implements TriConsumer {
    public static final /* synthetic */ PackageInstaller$SessionCallbackDelegate$$ExternalSyntheticLambda1 INSTANCE = new PackageInstaller$SessionCallbackDelegate$$ExternalSyntheticLambda1();

    private /* synthetic */ PackageInstaller$SessionCallbackDelegate$$ExternalSyntheticLambda1() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((PackageInstaller.SessionCallback) obj).onFinished(((Integer) obj2).intValue(), ((Boolean) obj3).booleanValue());
    }
}
