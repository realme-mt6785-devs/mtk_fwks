package android.view;

import android.content.res.CompatibilityInfo;
import android.content.res.Resources;
import android.graphics.Point;
/* loaded from: classes3.dex */
public interface IDisplayExt {
    default boolean supportDisplayCompat() {
        return false;
    }

    default void updateCompatRealSize(DisplayInfo displayInfo, CompatibilityInfo compat, Point outSize) {
    }

    default int getCompactWindowRotation(Resources resources) {
        return -1;
    }

    default DisplayAdjustments getCompactWindowDisplayAdjustment(Resources resources) {
        return null;
    }

    default DisplayAdjustments getDisplayAdjustmentForCompactWindow(Resources resources, DisplayAdjustments originAdjustments) {
        return originAdjustments;
    }
}
