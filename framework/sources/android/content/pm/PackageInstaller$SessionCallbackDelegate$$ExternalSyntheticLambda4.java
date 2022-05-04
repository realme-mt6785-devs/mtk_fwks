package android.content.pm;

import android.content.pm.PackageInstaller;
import java.util.function.BiConsumer;
/* loaded from: classes.dex */
public final /* synthetic */ class PackageInstaller$SessionCallbackDelegate$$ExternalSyntheticLambda4 implements BiConsumer {
    public static final /* synthetic */ PackageInstaller$SessionCallbackDelegate$$ExternalSyntheticLambda4 INSTANCE = new PackageInstaller$SessionCallbackDelegate$$ExternalSyntheticLambda4();

    private /* synthetic */ PackageInstaller$SessionCallbackDelegate$$ExternalSyntheticLambda4() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((PackageInstaller.SessionCallback) obj).onCreated(((Integer) obj2).intValue());
    }
}
