package android.app;

import android.app.UiModeManager;
import android.util.ArraySet;
import com.android.internal.util.function.TriConsumer;
/* loaded from: classes.dex */
public final /* synthetic */ class UiModeManager$InnerListener$$ExternalSyntheticLambda0 implements TriConsumer {
    public static final /* synthetic */ UiModeManager$InnerListener$$ExternalSyntheticLambda0 INSTANCE = new UiModeManager$InnerListener$$ExternalSyntheticLambda0();

    private /* synthetic */ UiModeManager$InnerListener$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((UiModeManager.OnProjectionStateChangedListener) obj).onProjectionStateChanged(((Integer) obj2).intValue(), (ArraySet) obj3);
    }
}
