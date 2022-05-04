package android.content.pm;

import android.content.Intent;
import java.util.function.Function;
/* loaded from: classes.dex */
public final /* synthetic */ class AppSearchShortcutInfo$Builder$$ExternalSyntheticLambda0 implements Function {
    public static final /* synthetic */ AppSearchShortcutInfo$Builder$$ExternalSyntheticLambda0 INSTANCE = new AppSearchShortcutInfo$Builder$$ExternalSyntheticLambda0();

    private /* synthetic */ AppSearchShortcutInfo$Builder$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        String uri;
        uri = ((Intent) obj).toUri(0);
        return uri;
    }
}
