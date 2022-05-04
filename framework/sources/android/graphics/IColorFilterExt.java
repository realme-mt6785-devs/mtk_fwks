package android.graphics;

import android.common.IOplusCommonFeature;
import android.common.OplusFeatureList;
/* loaded from: classes.dex */
public interface IColorFilterExt extends IOplusCommonFeature {
    public static final IColorFilterExt DEFAULT = new IColorFilterExt() { // from class: android.graphics.IColorFilterExt.1
    };

    @Override // android.common.IOplusCommonFeature
    default OplusFeatureList.OplusIndex index() {
        return OplusFeatureList.OplusIndex.IColorFilterExt;
    }

    @Override // android.common.IOplusCommonFeature
    default IColorFilterExt getDefault() {
        return DEFAULT;
    }

    default void setColor(ColorFilter colorFilter, int color) {
    }
}
