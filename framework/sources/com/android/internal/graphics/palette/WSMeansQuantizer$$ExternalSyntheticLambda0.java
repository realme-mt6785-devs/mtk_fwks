package com.android.internal.graphics.palette;

import com.android.internal.graphics.palette.WSMeansQuantizer;
import java.util.Comparator;
/* loaded from: classes4.dex */
public final /* synthetic */ class WSMeansQuantizer$$ExternalSyntheticLambda0 implements Comparator {
    public static final /* synthetic */ WSMeansQuantizer$$ExternalSyntheticLambda0 INSTANCE = new WSMeansQuantizer$$ExternalSyntheticLambda0();

    private /* synthetic */ WSMeansQuantizer$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compare;
        compare = Float.compare(((WSMeansQuantizer.Distance) obj).getDistance(), ((WSMeansQuantizer.Distance) obj2).getDistance());
        return compare;
    }
}
