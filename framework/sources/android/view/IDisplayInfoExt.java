package android.view;

import android.util.DisplayMetrics;
/* loaded from: classes3.dex */
public interface IDisplayInfoExt {
    default boolean supportDisplayCompat() {
        return false;
    }

    default void overrideDisplayMetricsIfNeed(DisplayMetrics outMetrics) {
    }
}
