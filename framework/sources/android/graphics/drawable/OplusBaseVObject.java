package android.graphics.drawable;

import android.content.res.IResourcesExt;
import android.text.TextUtils;
import java.lang.ref.WeakReference;
import oplus.content.res.OplusExtraConfiguration;
/* loaded from: classes.dex */
public abstract class OplusBaseVObject {
    private static final float CHANGE_UNIT = 0.5f;
    private static final int MODE_FLAG = 16711680;
    private static final String WIDTH_SYMBOL = "path_width";
    private WeakReference<IResourcesExt> mBaseResources;
    int mShouldRestoreFillColor = -1;
    int mShouldRestoreStrokeColor = -1;
    boolean mHasOriginColor = false;

    public void hookVFullInflate(IResourcesExt res) {
        this.mBaseResources = new WeakReference<>(res);
    }

    public Float calculateStrokeWidth(String pathName, float defaultWidth) {
        WeakReference<IResourcesExt> weakReference;
        OplusExtraConfiguration extraConfig;
        if (TextUtils.isEmpty(pathName) || !pathName.startsWith(WIDTH_SYMBOL) || (weakReference = this.mBaseResources) == null) {
            return Float.valueOf(defaultWidth);
        }
        IResourcesExt resourcesExt = weakReference.get();
        if (resourcesExt == null || resourcesExt.getConfiguration() == null || (extraConfig = resourcesExt.getConfiguration().getOplusExtraConfiguration()) == null) {
            return Float.valueOf(defaultWidth);
        }
        int fontVariationSettings = extraConfig.mFontVariationSettings;
        float adjustWidth = ((16711680 & fontVariationSettings) >> 16) * 0.5f;
        return Float.valueOf(adjustWidth > 0.0f ? adjustWidth : defaultWidth);
    }

    void setFillColor(int color) {
    }

    void setStrokeColor(int color) {
    }

    int getFillColor() {
        return 0;
    }

    int getStrokeColor() {
        return 0;
    }

    float getStrokeAlpha() {
        return 0.0f;
    }

    float getFillAlpha() {
        return 0.0f;
    }
}
