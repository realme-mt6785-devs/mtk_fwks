package android.content.res;

import android.common.IOplusCommonFeature;
import android.common.OplusFeatureList;
import android.graphics.drawable.Drawable;
import com.oplus.util.OplusTypeCastingHelper;
/* loaded from: classes.dex */
public interface IResourcesExt extends IOplusCommonFeature {
    public static final IResourcesExt DEFAULT = new IResourcesExt() { // from class: android.content.res.IResourcesExt.1
    };

    @Override // android.common.IOplusCommonFeature
    default OplusFeatureList.OplusIndex index() {
        return OplusFeatureList.OplusIndex.IResourcesExt;
    }

    default IResourcesImplExt getResourcesImplExt() {
        return IResourcesImplExt.DEFAULT;
    }

    @Override // android.common.IOplusCommonFeature
    default IResourcesExt getDefault() {
        return DEFAULT;
    }

    default void setIsThemeChanged(boolean changed) {
    }

    default boolean getThemeChanged() {
        return false;
    }

    default Drawable loadIcon(int id) {
        return null;
    }

    default CharSequence getThemeCharSequence(int id) {
        return null;
    }

    default Drawable loadIcon(int id, boolean useWrap) {
        return loadIcon(id, null, useWrap);
    }

    default Drawable loadIcon(int id, String str) {
        return loadIcon(id, str, true);
    }

    default void init(String name) {
    }

    default Drawable loadIcon(int id, String str, boolean useWrap) {
        return null;
    }

    default Resources getResources() {
        return null;
    }

    default OplusBaseConfiguration getConfiguration() {
        return null;
    }

    default OplusBaseConfiguration getSystemConfiguration() {
        Configuration config = Resources.getSystem().getConfiguration();
        if (config != null) {
            return (OplusBaseConfiguration) OplusTypeCastingHelper.typeCasting(OplusBaseConfiguration.class, config);
        }
        return null;
    }
}
