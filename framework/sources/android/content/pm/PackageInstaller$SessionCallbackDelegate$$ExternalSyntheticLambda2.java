package android.content.pm;

import android.content.pm.PackageInstaller;
import com.android.internal.util.function.TriConsumer;
/* loaded from: classes.dex */
public final /* synthetic */ class PackageInstaller$SessionCallbackDelegate$$ExternalSyntheticLambda2 implements TriConsumer {
    public static final /* synthetic */ PackageInstaller$SessionCallbackDelegate$$ExternalSyntheticLambda2 INSTANCE = new PackageInstaller$SessionCallbackDelegate$$ExternalSyntheticLambda2();

    private /* synthetic */ PackageInstaller$SessionCallbackDelegate$$ExternalSyntheticLambda2() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((PackageInstaller.SessionCallback) obj).onProgressChanged(((Integer) obj2).intValue(), ((Float) obj3).floatValue());
    }
}
