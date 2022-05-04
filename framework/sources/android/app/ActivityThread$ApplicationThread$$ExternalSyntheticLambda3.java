package android.app;

import java.util.function.BiConsumer;
/* loaded from: classes.dex */
public final /* synthetic */ class ActivityThread$ApplicationThread$$ExternalSyntheticLambda3 implements BiConsumer {
    public static final /* synthetic */ ActivityThread$ApplicationThread$$ExternalSyntheticLambda3 INSTANCE = new ActivityThread$ApplicationThread$$ExternalSyntheticLambda3();

    private /* synthetic */ ActivityThread$ApplicationThread$$ExternalSyntheticLambda3() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((ActivityThread) obj).handleTrimMemory(((Integer) obj2).intValue());
    }
}
