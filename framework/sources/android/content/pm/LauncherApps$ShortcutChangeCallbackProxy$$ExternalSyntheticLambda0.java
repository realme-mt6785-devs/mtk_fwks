package android.content.pm;

import android.content.pm.LauncherApps;
import android.os.UserHandle;
import com.android.internal.util.function.QuadConsumer;
import java.util.List;
/* loaded from: classes.dex */
public final /* synthetic */ class LauncherApps$ShortcutChangeCallbackProxy$$ExternalSyntheticLambda0 implements QuadConsumer {
    public static final /* synthetic */ LauncherApps$ShortcutChangeCallbackProxy$$ExternalSyntheticLambda0 INSTANCE = new LauncherApps$ShortcutChangeCallbackProxy$$ExternalSyntheticLambda0();

    private /* synthetic */ LauncherApps$ShortcutChangeCallbackProxy$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.internal.util.function.QuadConsumer
    public final void accept(Object obj, Object obj2, Object obj3, Object obj4) {
        ((LauncherApps.ShortcutChangeCallback) obj).onShortcutsAddedOrUpdated((String) obj2, (List) obj3, (UserHandle) obj4);
    }
}
