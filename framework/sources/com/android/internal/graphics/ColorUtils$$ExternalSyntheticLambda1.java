package com.android.internal.graphics;

import com.android.internal.graphics.ColorUtils;
/* loaded from: classes4.dex */
public final /* synthetic */ class ColorUtils$$ExternalSyntheticLambda1 implements ColorUtils.ContrastCalculator {
    public static final /* synthetic */ ColorUtils$$ExternalSyntheticLambda1 INSTANCE = new ColorUtils$$ExternalSyntheticLambda1();

    private /* synthetic */ ColorUtils$$ExternalSyntheticLambda1() {
    }

    @Override // com.android.internal.graphics.ColorUtils.ContrastCalculator
    public final double calculateContrast(int i, int i2, int i3) {
        return ColorUtils.setAlphaComponent(i, i3);
    }
}
