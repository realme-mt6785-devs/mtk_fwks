package com.android.internal.content.om;

import com.android.internal.content.om.OverlayConfig;
import java.util.function.ToIntFunction;
/* loaded from: classes4.dex */
public final /* synthetic */ class OverlayConfig$$ExternalSyntheticLambda5 implements ToIntFunction {
    public static final /* synthetic */ OverlayConfig$$ExternalSyntheticLambda5 INSTANCE = new OverlayConfig$$ExternalSyntheticLambda5();

    private /* synthetic */ OverlayConfig$$ExternalSyntheticLambda5() {
    }

    @Override // java.util.function.ToIntFunction
    public final int applyAsInt(Object obj) {
        int i;
        i = ((OverlayConfig.Configuration) obj).configIndex;
        return i;
    }
}
