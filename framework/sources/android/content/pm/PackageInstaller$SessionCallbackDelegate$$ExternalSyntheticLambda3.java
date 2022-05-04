package android.content.pm;

import android.content.pm.PackageInstaller;
import java.util.function.BiConsumer;
/* loaded from: classes.dex */
public final /* synthetic */ class PackageInstaller$SessionCallbackDelegate$$ExternalSyntheticLambda3 implements BiConsumer {
    public static final /* synthetic */ PackageInstaller$SessionCallbackDelegate$$ExternalSyntheticLambda3 INSTANCE = new PackageInstaller$SessionCallbackDelegate$$ExternalSyntheticLambda3();

    private /* synthetic */ PackageInstaller$SessionCallbackDelegate$$ExternalSyntheticLambda3() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((PackageInstaller.SessionCallback) obj).onBadgingChanged(((Integer) obj2).intValue());
    }
}
