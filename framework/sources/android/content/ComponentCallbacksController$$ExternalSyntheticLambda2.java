package android.content;

import java.util.function.Consumer;
/* loaded from: classes.dex */
public final /* synthetic */ class ComponentCallbacksController$$ExternalSyntheticLambda2 implements Consumer {
    public static final /* synthetic */ ComponentCallbacksController$$ExternalSyntheticLambda2 INSTANCE = new ComponentCallbacksController$$ExternalSyntheticLambda2();

    private /* synthetic */ ComponentCallbacksController$$ExternalSyntheticLambda2() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((ComponentCallbacks) obj).onLowMemory();
    }
}
