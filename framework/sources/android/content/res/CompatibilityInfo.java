package android.content.res;

import android.content.pm.ApplicationInfo;
import android.graphics.Canvas;
import android.graphics.Insets;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.view.InsetsSourceControl;
import android.view.InsetsState;
import android.view.MotionEvent;
import android.view.WindowManager;
/* loaded from: classes.dex */
public class CompatibilityInfo implements Parcelable {
    private static final int ALWAYS_NEEDS_COMPAT = 2;
    public static final int DEFAULT_NORMAL_SHORT_DIMENSION = 320;
    private static final int HAS_OVERRIDE_SCALING = 32;
    public static final float MAXIMUM_ASPECT_RATIO = 1.7791667f;
    private static final int NEEDS_COMPAT_RES = 16;
    private static final int NEEDS_SCREEN_COMPAT = 8;
    private static final int NEVER_NEEDS_COMPAT = 4;
    private static final int SCALING_REQUIRED = 1;
    public final int applicationDensity;
    public final float applicationInvertedScale;
    public final float applicationScale;
    ICompatibilityInfoExt mCompatInfoExt;
    private final int mCompatibilityFlags;
    public static final CompatibilityInfo DEFAULT_COMPATIBILITY_INFO = new CompatibilityInfo() { // from class: android.content.res.CompatibilityInfo.1
    };
    public static final Parcelable.Creator<CompatibilityInfo> CREATOR = new Parcelable.Creator<CompatibilityInfo>() { // from class: android.content.res.CompatibilityInfo.2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CompatibilityInfo createFromParcel(Parcel source) {
            return new CompatibilityInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CompatibilityInfo[] newArray(int size) {
            return new CompatibilityInfo[size];
        }
    };

    @Deprecated
    public CompatibilityInfo(ApplicationInfo appInfo, int screenLayout, int sw, boolean forceCompat) {
        this(appInfo, screenLayout, sw, forceCompat, 1.0f);
    }

    public CompatibilityInfo(ApplicationInfo appInfo, int screenLayout, int sw, boolean forceCompat, float overrideScale) {
        int required;
        this.mCompatInfoExt = CompatibilityInfoExtPlugin.constructor.newInstance();
        int compatFlags = 0;
        compatFlags = appInfo.targetSdkVersion < 26 ? 0 | 16 : compatFlags;
        if (appInfo.requiresSmallestWidthDp == 0 && appInfo.compatibleWidthLimitDp == 0 && appInfo.largestWidthLimitDp == 0) {
            int sizeInfo = 0;
            boolean anyResizeable = false;
            if ((appInfo.flags & 2048) != 0) {
                sizeInfo = 0 | 8;
                anyResizeable = true;
                if (!forceCompat) {
                    sizeInfo |= 34;
                }
            }
            if ((appInfo.flags & 524288) != 0) {
                anyResizeable = true;
                if (!forceCompat) {
                    sizeInfo |= 34;
                }
            }
            if ((appInfo.flags & 4096) != 0) {
                anyResizeable = true;
                sizeInfo |= 2;
            }
            sizeInfo = forceCompat ? sizeInfo & (-3) : sizeInfo;
            compatFlags |= 8;
            switch (screenLayout & 15) {
                case 3:
                    compatFlags = (sizeInfo & 8) != 0 ? compatFlags & (-9) : compatFlags;
                    if ((appInfo.flags & 2048) != 0) {
                        compatFlags |= 4;
                        break;
                    }
                    break;
                case 4:
                    compatFlags = (sizeInfo & 32) != 0 ? compatFlags & (-9) : compatFlags;
                    if ((appInfo.flags & 524288) != 0) {
                        compatFlags |= 4;
                        break;
                    }
                    break;
            }
            if ((screenLayout & 268435456) == 0) {
                compatFlags = (compatFlags & (-9)) | 4;
            } else if ((sizeInfo & 2) != 0) {
                compatFlags &= -9;
            } else if (!anyResizeable) {
                compatFlags |= 2;
            }
            int density = appInfo.getOverrideDensity();
            if (overrideScale != 1.0f && this.mCompatInfoExt != null) {
                this.applicationScale = overrideScale;
                float f = 1.0f / overrideScale;
                this.applicationInvertedScale = f;
                this.applicationDensity = this.mCompatInfoExt.getCompatDensity(appInfo, (int) ((DisplayMetrics.DENSITY_DEVICE_STABLE * f) + 0.5f));
                compatFlags = this.mCompatInfoExt.updateCompatFlagsIfNeed(appInfo, compatFlags) | 32;
            } else if ((appInfo.flags & 8192) == 0) {
                this.applicationDensity = 160;
                float f2 = DisplayMetrics.DENSITY_DEVICE / 160.0f;
                this.applicationScale = f2;
                this.applicationInvertedScale = 1.0f / f2;
                compatFlags |= 1;
            } else if (density != 0) {
                this.applicationScale = overrideScale;
                float f3 = 1.0f / overrideScale;
                this.applicationInvertedScale = f3;
                int appdensity = (int) ((DisplayMetrics.DENSITY_DEVICE_STABLE * f3) + 0.5f);
                ICompatibilityInfoExt iCompatibilityInfoExt = this.mCompatInfoExt;
                this.applicationDensity = iCompatibilityInfoExt != null ? iCompatibilityInfoExt.getCompatDensity(appInfo, appdensity) : appdensity;
                ICompatibilityInfoExt iCompatibilityInfoExt2 = this.mCompatInfoExt;
                compatFlags = (iCompatibilityInfoExt2 != null ? iCompatibilityInfoExt2.updateCompatFlagsIfNeed(appInfo, compatFlags) : compatFlags) | 32;
            } else if ((appInfo.flags & 8192) != 0) {
                this.applicationDensity = DisplayMetrics.DENSITY_DEVICE;
                this.applicationScale = 1.0f;
                this.applicationInvertedScale = 1.0f;
            } else {
                this.applicationDensity = 160;
                float f4 = DisplayMetrics.DENSITY_DEVICE / 160.0f;
                this.applicationScale = f4;
                this.applicationInvertedScale = 1.0f / f4;
                compatFlags |= 1;
            }
        } else {
            int EXPANDABLE = appInfo.requiresSmallestWidthDp;
            if (EXPANDABLE != 0) {
                required = appInfo.requiresSmallestWidthDp;
            } else {
                required = appInfo.compatibleWidthLimitDp;
            }
            required = required == 0 ? appInfo.largestWidthLimitDp : required;
            int compat = appInfo.compatibleWidthLimitDp != 0 ? appInfo.compatibleWidthLimitDp : required;
            compat = compat < required ? required : compat;
            int largest = appInfo.largestWidthLimitDp;
            if (required > 320) {
                compatFlags |= 4;
            } else if (largest != 0 && sw > largest) {
                compatFlags |= 10;
            } else if (compat >= sw) {
                compatFlags |= 4;
            } else if (forceCompat) {
                compatFlags |= 8;
            }
            this.applicationDensity = DisplayMetrics.DENSITY_DEVICE;
            this.applicationScale = 1.0f;
            this.applicationInvertedScale = 1.0f;
        }
        this.mCompatibilityFlags = compatFlags;
    }

    private CompatibilityInfo(int compFlags, int dens, float scale, float invertedScale) {
        this.mCompatInfoExt = CompatibilityInfoExtPlugin.constructor.newInstance();
        this.mCompatibilityFlags = compFlags;
        this.applicationDensity = dens;
        this.applicationScale = scale;
        this.applicationInvertedScale = invertedScale;
    }

    private CompatibilityInfo() {
        this(4, DisplayMetrics.DENSITY_DEVICE, 1.0f, 1.0f);
    }

    public boolean isScalingRequired() {
        return (this.mCompatibilityFlags & 33) != 0;
    }

    public boolean supportsScreen() {
        ICompatibilityInfoExt iCompatibilityInfoExt = this.mCompatInfoExt;
        return (iCompatibilityInfoExt == null || !iCompatibilityInfoExt.hasOverrideScaling(this.mCompatibilityFlags)) && (this.mCompatibilityFlags & 8) == 0;
    }

    public boolean neverSupportsScreen() {
        return (this.mCompatibilityFlags & 2) != 0;
    }

    public boolean alwaysSupportsScreen() {
        return (this.mCompatibilityFlags & 4) != 0;
    }

    public boolean needsCompatResources() {
        return (this.mCompatibilityFlags & 16) != 0;
    }

    public Translator getTranslator() {
        if ((this.mCompatibilityFlags & 1) != 0) {
            return new Translator(this);
        }
        return null;
    }

    /* loaded from: classes.dex */
    public class Translator {
        public final float applicationInvertedScale;
        public final float applicationScale;
        private Rect mContentInsetsBuffer;
        private Region mTouchableAreaBuffer;
        private Rect mVisibleInsetsBuffer;

        Translator(float applicationScale, float applicationInvertedScale) {
            this.mContentInsetsBuffer = null;
            this.mVisibleInsetsBuffer = null;
            this.mTouchableAreaBuffer = null;
            this.applicationScale = applicationScale;
            this.applicationInvertedScale = applicationInvertedScale;
        }

        Translator(CompatibilityInfo this$0) {
            this(this$0.applicationScale, this$0.applicationInvertedScale);
        }

        public void translateRegionInWindowToScreen(Region transparentRegion) {
            transparentRegion.scale(this.applicationScale);
        }

        public void translateCanvas(Canvas canvas) {
            if (this.applicationScale == 1.5f) {
                canvas.translate(0.0026143792f, 0.0026143792f);
            }
            float tinyOffset = this.applicationScale;
            canvas.scale(tinyOffset, tinyOffset);
        }

        public void translateEventInScreenToAppWindow(MotionEvent event) {
            event.scale(this.applicationInvertedScale);
        }

        public void translateWindowLayout(WindowManager.LayoutParams params) {
            params.scale(this.applicationScale);
        }

        public float translateLengthInAppWindowToScreen(float length) {
            return this.applicationScale * length;
        }

        public void translateRectInAppWindowToScreen(Rect rect) {
            rect.scale(this.applicationScale);
        }

        public void translateRectInScreenToAppWindow(Rect rect) {
            rect.scale(this.applicationInvertedScale);
        }

        public void translateInsetsStateInScreenToAppWindow(InsetsState state) {
            state.scale(this.applicationInvertedScale);
        }

        public void translateSourceControlsInScreenToAppWindow(InsetsSourceControl[] controls) {
            if (controls != null) {
                float scale = this.applicationInvertedScale;
                if (scale != 1.0f) {
                    for (InsetsSourceControl control : controls) {
                        if (control != null) {
                            Insets hint = control.getInsetsHint();
                            control.setInsetsHint((int) (hint.left * scale), (int) (hint.top * scale), (int) (hint.right * scale), (int) (hint.bottom * scale));
                        }
                    }
                }
            }
        }

        public void translatePointInScreenToAppWindow(PointF point) {
            float scale = this.applicationInvertedScale;
            if (scale != 1.0f) {
                point.x *= scale;
                point.y *= scale;
            }
        }

        public void translateLayoutParamsInAppWindowToScreen(WindowManager.LayoutParams params) {
            params.scale(this.applicationScale);
        }

        public Rect getTranslatedContentInsets(Rect contentInsets) {
            if (this.mContentInsetsBuffer == null) {
                this.mContentInsetsBuffer = new Rect();
            }
            this.mContentInsetsBuffer.set(contentInsets);
            translateRectInAppWindowToScreen(this.mContentInsetsBuffer);
            return this.mContentInsetsBuffer;
        }

        public Rect getTranslatedVisibleInsets(Rect visibleInsets) {
            if (this.mVisibleInsetsBuffer == null) {
                this.mVisibleInsetsBuffer = new Rect();
            }
            this.mVisibleInsetsBuffer.set(visibleInsets);
            translateRectInAppWindowToScreen(this.mVisibleInsetsBuffer);
            return this.mVisibleInsetsBuffer;
        }

        public Region getTranslatedTouchableArea(Region touchableArea) {
            if (this.mTouchableAreaBuffer == null) {
                this.mTouchableAreaBuffer = new Region();
            }
            this.mTouchableAreaBuffer.set(touchableArea);
            this.mTouchableAreaBuffer.scale(this.applicationScale);
            return this.mTouchableAreaBuffer;
        }
    }

    public void applyToDisplayMetrics(DisplayMetrics inoutDm) {
        ICompatibilityInfoExt iCompatibilityInfoExt;
        if (supportsScreen() || ((iCompatibilityInfoExt = this.mCompatInfoExt) != null && iCompatibilityInfoExt.hasOverrideScaling(this.mCompatibilityFlags))) {
            inoutDm.widthPixels = inoutDm.noncompatWidthPixels;
            inoutDm.heightPixels = inoutDm.noncompatHeightPixels;
        } else {
            computeCompatibleScaling(inoutDm, inoutDm);
        }
        if (isScalingRequired()) {
            float invertedRatio = this.applicationInvertedScale;
            inoutDm.density = inoutDm.noncompatDensity * invertedRatio;
            inoutDm.densityDpi = (int) ((inoutDm.noncompatDensityDpi * invertedRatio) + 0.5f);
            inoutDm.scaledDensity = inoutDm.noncompatScaledDensity * invertedRatio;
            inoutDm.xdpi = inoutDm.noncompatXdpi * invertedRatio;
            inoutDm.ydpi = inoutDm.noncompatYdpi * invertedRatio;
            inoutDm.widthPixels = (int) ((inoutDm.widthPixels * invertedRatio) + 0.5f);
            inoutDm.heightPixels = (int) ((inoutDm.heightPixels * invertedRatio) + 0.5f);
            ICompatibilityInfoExt iCompatibilityInfoExt2 = this.mCompatInfoExt;
            if (iCompatibilityInfoExt2 != null && iCompatibilityInfoExt2.hasOverrideScaling(this.mCompatibilityFlags)) {
                this.mCompatInfoExt.overrideDisplayMetricsIfNeed(inoutDm, this, this.mCompatibilityFlags);
            }
        }
    }

    public void applyToConfiguration(int displayDensity, Configuration inoutConfig) {
        ICompatibilityInfoExt iCompatibilityInfoExt;
        if (!supportsScreen() && ((iCompatibilityInfoExt = this.mCompatInfoExt) == null || !iCompatibilityInfoExt.hasOverrideScaling(this.mCompatibilityFlags))) {
            inoutConfig.screenLayout = (inoutConfig.screenLayout & (-16)) | 2;
            inoutConfig.screenWidthDp = inoutConfig.compatScreenWidthDp;
            inoutConfig.screenHeightDp = inoutConfig.compatScreenHeightDp;
            inoutConfig.smallestScreenWidthDp = inoutConfig.compatSmallestScreenWidthDp;
        }
        inoutConfig.densityDpi = displayDensity;
        if (isScalingRequired()) {
            float invertedRatio = this.applicationInvertedScale;
            inoutConfig.densityDpi = (int) ((inoutConfig.densityDpi * invertedRatio) + 0.5f);
            inoutConfig.windowConfiguration.getMaxBounds().scale(invertedRatio);
            inoutConfig.windowConfiguration.getBounds().scale(invertedRatio);
            Rect appBounds = inoutConfig.windowConfiguration.getAppBounds();
            if (appBounds != null) {
                appBounds.scale(invertedRatio);
            }
            ICompatibilityInfoExt iCompatibilityInfoExt2 = this.mCompatInfoExt;
            if (iCompatibilityInfoExt2 != null && iCompatibilityInfoExt2.hasOverrideScaling(this.mCompatibilityFlags)) {
                this.mCompatInfoExt.applyToConfiguration(inoutConfig, this, this.mCompatibilityFlags);
            }
        }
    }

    public static float computeCompatibleScaling(DisplayMetrics dm, DisplayMetrics outDm) {
        int longSize;
        int shortSize;
        int newHeight;
        int newWidth;
        int width = dm.noncompatWidthPixels;
        int height = dm.noncompatHeightPixels;
        if (width < height) {
            shortSize = width;
            longSize = height;
        } else {
            shortSize = height;
            longSize = width;
        }
        int newShortSize = (int) ((dm.density * 320.0f) + 0.5f);
        float aspect = longSize / shortSize;
        if (aspect > 1.7791667f) {
            aspect = 1.7791667f;
        }
        int newLongSize = (int) ((newShortSize * aspect) + 0.5f);
        if (width < height) {
            newWidth = newShortSize;
            newHeight = newLongSize;
        } else {
            newWidth = newLongSize;
            newHeight = newShortSize;
        }
        float sw = width / newWidth;
        float sh = height / newHeight;
        float scale = sw < sh ? sw : sh;
        if (scale < 1.0f) {
            scale = 1.0f;
        }
        if (outDm != null) {
            outDm.widthPixels = newWidth;
            outDm.heightPixels = newHeight;
        }
        return scale;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        try {
            CompatibilityInfo oc = (CompatibilityInfo) o;
            if (this.mCompatibilityFlags != oc.mCompatibilityFlags || this.applicationDensity != oc.applicationDensity || this.applicationScale != oc.applicationScale) {
                return false;
            }
            if (this.applicationInvertedScale != oc.applicationInvertedScale) {
                return false;
            }
            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("{");
        sb.append(this.applicationDensity);
        sb.append("dpi");
        if (isScalingRequired()) {
            sb.append(" ");
            sb.append(this.applicationScale);
            sb.append("x");
        }
        if (!supportsScreen()) {
            sb.append(" resizing");
        }
        if (neverSupportsScreen()) {
            sb.append(" never-compat");
        }
        if (alwaysSupportsScreen()) {
            sb.append(" always-compat");
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int result = (17 * 31) + this.mCompatibilityFlags;
        return (((((result * 31) + this.applicationDensity) * 31) + Float.floatToIntBits(this.applicationScale)) * 31) + Float.floatToIntBits(this.applicationInvertedScale);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mCompatibilityFlags);
        dest.writeInt(this.applicationDensity);
        dest.writeFloat(this.applicationScale);
        dest.writeFloat(this.applicationInvertedScale);
    }

    private CompatibilityInfo(Parcel source) {
        this.mCompatInfoExt = CompatibilityInfoExtPlugin.constructor.newInstance();
        this.mCompatibilityFlags = source.readInt();
        this.applicationDensity = source.readInt();
        this.applicationScale = source.readFloat();
        this.applicationInvertedScale = source.readFloat();
    }
}
