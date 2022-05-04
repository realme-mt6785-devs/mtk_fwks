package com.android.internal.content.om;

import android.content.pm.PackagePartitions;
import com.android.internal.content.om.OverlayConfigParser;
import java.util.function.Function;
/* loaded from: classes4.dex */
public final /* synthetic */ class OverlayConfig$$ExternalSyntheticLambda3 implements Function {
    public static final /* synthetic */ OverlayConfig$$ExternalSyntheticLambda3 INSTANCE = new OverlayConfig$$ExternalSyntheticLambda3();

    private /* synthetic */ OverlayConfig$$ExternalSyntheticLambda3() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return new OverlayConfigParser.OverlayPartition((PackagePartitions.SystemPartition) obj);
    }
}
