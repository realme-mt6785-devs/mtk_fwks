package android.content.res;

import android.common.IOplusCommonFeature;
import android.common.OplusFeatureList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.DisplayAdjustments;
import com.oplus.util.OplusTypeCastingHelper;
import java.io.InputStream;
/* loaded from: classes.dex */
public interface IResourcesImplExt extends IOplusCommonFeature {
    public static final IResourcesImplExt DEFAULT = new IResourcesImplExt() { // from class: android.content.res.IResourcesImplExt.1
    };

    @Override // android.common.IOplusCommonFeature
    default OplusFeatureList.OplusIndex index() {
        return OplusFeatureList.OplusIndex.IResourcesImplExt;
    }

    @Override // android.common.IOplusCommonFeature
    default IResourcesImplExt getDefault() {
        return DEFAULT;
    }

    default void init(String name) {
    }

    default String getPackageName() {
        return null;
    }

    default void checkUpdate(int changes, boolean languageChaged) {
    }

    default void getExValue(int id, TypedValue outValue, boolean resolveRefs) {
    }

    default SparseArray<Boolean> getLoadArrary() {
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

    default void initThemeResource(String name) {
    }

    default boolean isHasDrawables() {
        return false;
    }

    default Drawable loadIcon(Resources wrapper, int id, String str, boolean useWrap) {
        return null;
    }

    default Drawable loadOverlayDrawable(Resources wrapper, TypedValue value, int id) {
        return null;
    }

    default InputStream openThemeRawResource(int id, TypedValue outValue) throws Resources.NotFoundException {
        return null;
    }

    default TypedArray replaceTypedArray(TypedArray typedarray) {
        return null;
    }

    default void setIsThemeChanged(boolean changed) {
    }

    default CharSequence getThemeCharSequence(int id) {
        return null;
    }

    default boolean getThemeChanged() {
        return false;
    }

    default int updateExConfiguration(ResourcesImpl resources, Configuration config) {
        return -1;
    }

    default void clearCache() {
    }

    default ResourcesImpl getResourcesImpl() {
        return null;
    }

    default DisplayAdjustments getCompactWindowAdjustments() {
        return null;
    }

    default void updateCompactWindowAdjustments(Configuration oldOverrideConfig, Configuration newOverrideConfig) {
    }

    default DisplayMetrics getCompactWindowMetrics(ResourcesImpl resources, DisplayMetrics oldMetrics) {
        return null;
    }
}
