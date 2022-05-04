package android.app;

import android.app.ResourcesManager;
import java.lang.ref.WeakReference;
import java.util.function.Function;
/* loaded from: classes.dex */
public final /* synthetic */ class ResourcesManager$$ExternalSyntheticLambda0 implements Function {
    public static final /* synthetic */ ResourcesManager$$ExternalSyntheticLambda0 INSTANCE = new ResourcesManager$$ExternalSyntheticLambda0();

    private /* synthetic */ ResourcesManager$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        WeakReference weakReference;
        weakReference = ((ResourcesManager.ActivityResource) obj).resources;
        return weakReference;
    }
}
