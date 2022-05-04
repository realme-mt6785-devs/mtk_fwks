package android.graphics;

import android.graphics.HardwareRenderer;
import java.util.function.Function;
/* loaded from: classes.dex */
public final /* synthetic */ class HardwareRenderer$ProcessInitializer$$ExternalSyntheticLambda0 implements Function {
    public static final /* synthetic */ HardwareRenderer$ProcessInitializer$$ExternalSyntheticLambda0 INSTANCE = new HardwareRenderer$ProcessInitializer$$ExternalSyntheticLambda0();

    private /* synthetic */ HardwareRenderer$ProcessInitializer$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return HardwareRenderer.ProcessInitializer.Dataspace.find((ColorSpace) obj);
    }
}
