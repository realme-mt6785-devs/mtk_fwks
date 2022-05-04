package android.view;

import android.app.ActivityThread;
import android.content.ComponentName;
import android.content.res.CompatibilityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.ColorSpace;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.display.BrightnessInfo;
import android.hardware.display.DeviceProductInfo;
import android.hardware.display.DisplayManagerGlobal;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.ArraySet;
import android.util.DisplayMetrics;
import com.android.internal.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
/* loaded from: classes3.dex */
public final class Display {
    private static final int CACHED_APP_SIZE_DURATION_MILLIS = 20;
    public static final int COLOR_MODE_ADOBE_RGB = 8;
    public static final int COLOR_MODE_BT601_525 = 3;
    public static final int COLOR_MODE_BT601_525_UNADJUSTED = 4;
    public static final int COLOR_MODE_BT601_625 = 1;
    public static final int COLOR_MODE_BT601_625_UNADJUSTED = 2;
    public static final int COLOR_MODE_BT709 = 5;
    public static final int COLOR_MODE_DCI_P3 = 6;
    public static final int COLOR_MODE_DEFAULT = 0;
    public static final int COLOR_MODE_DISPLAY_P3 = 9;
    public static final int COLOR_MODE_INVALID = -1;
    public static final int COLOR_MODE_SRGB = 7;
    private static final boolean DEBUG = false;
    public static final int DEFAULT_DISPLAY = 0;
    public static final int DEFAULT_DISPLAY_GROUP = 0;
    public static final int DISPLAY_MODE_ID_FOR_FRAME_RATE_OVERRIDE = 255;
    public static final int FLAG_CAN_SHOW_WITH_INSECURE_KEYGUARD = 32;
    public static final int FLAG_OWN_DISPLAY_GROUP = 256;
    public static final int FLAG_PRESENTATION = 8;
    public static final int FLAG_PRIVATE = 4;
    public static final int FLAG_ROUND = 16;
    public static final int FLAG_SCALING_DISABLED = 1073741824;
    public static final int FLAG_SECURE = 2;
    public static final int FLAG_SHOULD_SHOW_SYSTEM_DECORATIONS = 64;
    public static final int FLAG_SUPPORTS_PROTECTED_BUFFERS = 1;
    public static final int FLAG_TRUSTED = 128;
    public static final int INVALID_DISPLAY = -1;
    public static final int INVALID_DISPLAY_GROUP = -1;
    public static final int REMOVE_MODE_DESTROY_CONTENT = 1;
    public static final int REMOVE_MODE_MOVE_CONTENT_TO_PRIMARY = 0;
    public static final int STATE_DOZE = 3;
    public static final int STATE_DOZE_SUSPEND = 4;
    public static final int STATE_OFF = 1;
    public static final int STATE_ON = 2;
    public static final int STATE_ON_SUSPEND = 6;
    public static final int STATE_UNKNOWN = 0;
    public static final int STATE_VR = 5;
    private static final String TAG = "Display";
    public static final int TYPE_EXTERNAL = 2;
    public static final int TYPE_INTERNAL = 1;
    public static final int TYPE_OVERLAY = 4;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_VIRTUAL = 5;
    public static final int TYPE_WIFI = 3;
    private int mCachedAppHeightCompat;
    private int mCachedAppWidthCompat;
    private DisplayAdjustments mDisplayAdjustments;
    IDisplayExt mDisplayExt;
    private final int mDisplayId;
    private DisplayInfo mDisplayInfo;
    private final int mFlags;
    private final DisplayManagerGlobal mGlobal;
    private Optional<Boolean> mIsRecentsComponent;
    private boolean mIsValid;
    private long mLastCachedAppSizeUpdate;
    private final Object mLock;
    private boolean mMayAdjustByFixedRotation;
    private final String mOwnerPackageName;
    private final int mOwnerUid;
    private final Resources mResources;
    private final DisplayMetrics mTempMetrics;
    private final int mType;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface ColorMode {
    }

    public Display(DisplayManagerGlobal global, int displayId, DisplayInfo displayInfo, DisplayAdjustments daj) {
        this(global, displayId, displayInfo, daj, null);
    }

    public Display(DisplayManagerGlobal global, int displayId, DisplayInfo displayInfo, Resources res) {
        this(global, displayId, displayInfo, null, res);
    }

    private Display(DisplayManagerGlobal global, int displayId, DisplayInfo displayInfo, DisplayAdjustments daj, Resources res) {
        DisplayAdjustments displayAdjustments;
        this.mLock = new Object();
        this.mTempMetrics = new DisplayMetrics();
        this.mIsRecentsComponent = Optional.empty();
        this.mDisplayExt = DisplayExtPlugin.constructor.newInstance();
        this.mGlobal = global;
        this.mDisplayId = displayId;
        this.mDisplayInfo = displayInfo;
        this.mResources = res;
        if (res != null) {
            displayAdjustments = new DisplayAdjustments(res.getConfiguration());
        } else {
            displayAdjustments = daj != null ? new DisplayAdjustments(daj) : new DisplayAdjustments();
        }
        this.mDisplayAdjustments = displayAdjustments;
        this.mIsValid = true;
        this.mFlags = displayInfo.flags;
        this.mType = displayInfo.type;
        this.mOwnerUid = displayInfo.ownerUid;
        this.mOwnerPackageName = displayInfo.ownerPackageName;
    }

    public int getDisplayId() {
        return this.mDisplayId;
    }

    public String getUniqueId() {
        return this.mDisplayInfo.uniqueId;
    }

    public boolean isValid() {
        boolean z;
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            z = this.mIsValid;
        }
        return z;
    }

    public boolean getDisplayInfo(DisplayInfo outDisplayInfo) {
        boolean z;
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            outDisplayInfo.copyFrom(this.mDisplayInfo);
            z = this.mIsValid;
        }
        return z;
    }

    public int getLayerStack() {
        int i;
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            i = this.mDisplayInfo.layerStack;
        }
        return i;
    }

    public int getFlags() {
        return this.mFlags;
    }

    public int getType() {
        return this.mType;
    }

    public DisplayAddress getAddress() {
        DisplayAddress displayAddress;
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            displayAddress = this.mDisplayInfo.address;
        }
        return displayAddress;
    }

    public int getOwnerUid() {
        return this.mOwnerUid;
    }

    public String getOwnerPackageName() {
        return this.mOwnerPackageName;
    }

    public DisplayAdjustments getDisplayAdjustments() {
        Resources resources = this.mResources;
        if (resources != null) {
            DisplayAdjustments currentAdjustments = resources.getDisplayAdjustments();
            if (!this.mDisplayAdjustments.equals(currentAdjustments)) {
                this.mDisplayAdjustments = new DisplayAdjustments(currentAdjustments);
            }
        }
        return this.mDisplayAdjustments;
    }

    public String getName() {
        String str;
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            str = this.mDisplayInfo.name;
        }
        return str;
    }

    public float getBrightnessDefault() {
        float f;
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            f = this.mDisplayInfo.brightnessDefault;
        }
        return f;
    }

    public BrightnessInfo getBrightnessInfo() {
        return this.mGlobal.getBrightnessInfo(this.mDisplayId);
    }

    @Deprecated
    public void getSize(Point outSize) {
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            this.mDisplayInfo.getAppMetrics(this.mTempMetrics, this.mDisplayExt.getDisplayAdjustmentForCompactWindow(this.mResources, getDisplayAdjustments()));
            outSize.x = this.mTempMetrics.widthPixels;
            outSize.y = this.mTempMetrics.heightPixels;
        }
    }

    @Deprecated
    public void getRectSize(Rect outSize) {
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            this.mDisplayInfo.getAppMetrics(this.mTempMetrics, this.mDisplayExt.getDisplayAdjustmentForCompactWindow(this.mResources, getDisplayAdjustments()));
            outSize.set(0, 0, this.mTempMetrics.widthPixels, this.mTempMetrics.heightPixels);
        }
    }

    public void getCurrentSizeRange(Point outSmallestSize, Point outLargestSize) {
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            outSmallestSize.x = this.mDisplayInfo.smallestNominalAppWidth;
            outSmallestSize.y = this.mDisplayInfo.smallestNominalAppHeight;
            outLargestSize.x = this.mDisplayInfo.largestNominalAppWidth;
            outLargestSize.y = this.mDisplayInfo.largestNominalAppHeight;
        }
    }

    public int getMaximumSizeDimension() {
        int max;
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            max = Math.max(this.mDisplayInfo.logicalWidth, this.mDisplayInfo.logicalHeight);
        }
        return max;
    }

    @Deprecated
    public int getWidth() {
        int i;
        synchronized (this.mLock) {
            updateCachedAppSizeIfNeededLocked();
            i = this.mCachedAppWidthCompat;
        }
        return i;
    }

    @Deprecated
    public int getHeight() {
        int i;
        synchronized (this.mLock) {
            updateCachedAppSizeIfNeededLocked();
            i = this.mCachedAppHeightCompat;
        }
        return i;
    }

    public int getRotation() {
        int i;
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            int rotation = this.mDisplayExt.getCompactWindowRotation(this.mResources);
            if (rotation != -1) {
                return rotation;
            }
            if (this.mMayAdjustByFixedRotation) {
                i = getDisplayAdjustments().getRotation(this.mDisplayInfo.rotation);
            } else {
                i = this.mDisplayInfo.rotation;
            }
            return i;
        }
    }

    @Deprecated
    public int getOrientation() {
        return getRotation();
    }

    public DisplayCutout getCutout() {
        DisplayCutout displayCutout;
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            if (this.mMayAdjustByFixedRotation) {
                displayCutout = getDisplayAdjustments().getDisplayCutout(this.mDisplayInfo.displayCutout);
            } else {
                displayCutout = this.mDisplayInfo.displayCutout;
            }
        }
        return displayCutout;
    }

    public RoundedCorner getRoundedCorner(int position) {
        RoundedCorners roundedCorners;
        RoundedCorner roundedCorner;
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            if (this.mMayAdjustByFixedRotation) {
                roundedCorners = getDisplayAdjustments().adjustRoundedCorner(this.mDisplayInfo.roundedCorners, this.mDisplayInfo.rotation, this.mDisplayInfo.logicalWidth, this.mDisplayInfo.logicalHeight);
            } else {
                roundedCorners = this.mDisplayInfo.roundedCorners;
            }
            roundedCorner = roundedCorners == null ? null : roundedCorners.getRoundedCorner(position);
        }
        return roundedCorner;
    }

    @Deprecated
    public int getPixelFormat() {
        return 1;
    }

    public float getRefreshRate() {
        float refreshRate;
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            refreshRate = this.mDisplayInfo.getRefreshRate();
        }
        return refreshRate;
    }

    @Deprecated
    public float[] getSupportedRefreshRates() {
        float[] defaultRefreshRates;
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            defaultRefreshRates = this.mDisplayInfo.getDefaultRefreshRates();
        }
        return defaultRefreshRates;
    }

    public Mode getMode() {
        Mode mode;
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            mode = this.mDisplayInfo.getMode();
        }
        return mode;
    }

    public Mode[] getSupportedModes() {
        Mode[] modeArr;
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            Mode[] modes = this.mDisplayInfo.supportedModes;
            modeArr = (Mode[]) Arrays.copyOf(modes, modes.length);
        }
        return modeArr;
    }

    public boolean isMinimalPostProcessingSupported() {
        boolean z;
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            z = this.mDisplayInfo.minimalPostProcessingSupported;
        }
        return z;
    }

    public void requestColorMode(int colorMode) {
        this.mGlobal.requestColorMode(this.mDisplayId, colorMode);
    }

    public int getColorMode() {
        int i;
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            i = this.mDisplayInfo.colorMode;
        }
        return i;
    }

    public int getRemoveMode() {
        return this.mDisplayInfo.removeMode;
    }

    public HdrCapabilities getHdrCapabilities() {
        int[] supportedHdrTypes;
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            if (this.mDisplayInfo.userDisabledHdrTypes.length == 0) {
                return this.mDisplayInfo.hdrCapabilities;
            } else if (this.mDisplayInfo.hdrCapabilities == null) {
                return null;
            } else {
                ArraySet<Integer> enabledTypesSet = new ArraySet<>();
                for (int supportedType : this.mDisplayInfo.hdrCapabilities.getSupportedHdrTypes()) {
                    boolean typeDisabled = false;
                    int[] iArr = this.mDisplayInfo.userDisabledHdrTypes;
                    int length = iArr.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            break;
                        }
                        int userDisabledType = iArr[i];
                        if (supportedType == userDisabledType) {
                            typeDisabled = true;
                            break;
                        }
                        i++;
                    }
                    if (!typeDisabled) {
                        enabledTypesSet.add(Integer.valueOf(supportedType));
                    }
                }
                int[] enabledTypes = new int[enabledTypesSet.size()];
                int index = 0;
                Iterator<Integer> it = enabledTypesSet.iterator();
                while (it.hasNext()) {
                    int enabledType = it.next().intValue();
                    enabledTypes[index] = enabledType;
                    index++;
                }
                return new HdrCapabilities(enabledTypes, this.mDisplayInfo.hdrCapabilities.mMaxLuminance, this.mDisplayInfo.hdrCapabilities.mMaxAverageLuminance, this.mDisplayInfo.hdrCapabilities.mMinLuminance);
            }
        }
    }

    public int[] getReportedHdrTypes() {
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            if (this.mDisplayInfo.hdrCapabilities == null) {
                return new int[0];
            }
            return this.mDisplayInfo.hdrCapabilities.getSupportedHdrTypes();
        }
    }

    public boolean isHdr() {
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            HdrCapabilities hdrCapabilities = getHdrCapabilities();
            boolean z = false;
            if (hdrCapabilities == null) {
                return false;
            }
            if (hdrCapabilities.getSupportedHdrTypes().length != 0) {
                z = true;
            }
            return z;
        }
    }

    public boolean isWideColorGamut() {
        boolean isWideColorGamut;
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            isWideColorGamut = this.mDisplayInfo.isWideColorGamut();
        }
        return isWideColorGamut;
    }

    public ColorSpace getPreferredWideGamutColorSpace() {
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            if (!this.mDisplayInfo.isWideColorGamut()) {
                return null;
            }
            return this.mGlobal.getPreferredWideGamutColorSpace();
        }
    }

    public int[] getSupportedColorModes() {
        int[] copyOf;
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            int[] colorModes = this.mDisplayInfo.supportedColorModes;
            copyOf = Arrays.copyOf(colorModes, colorModes.length);
        }
        return copyOf;
    }

    public ColorSpace[] getSupportedWideColorGamut() {
        synchronized (this.mLock) {
            ColorSpace[] defaultColorSpaces = new ColorSpace[0];
            updateDisplayInfoLocked();
            if (!isWideColorGamut()) {
                return defaultColorSpaces;
            }
            int[] colorModes = getSupportedColorModes();
            List<ColorSpace> colorSpaces = new ArrayList<>();
            for (int colorMode : colorModes) {
                switch (colorMode) {
                    case 6:
                        colorSpaces.add(ColorSpace.get(ColorSpace.Named.DCI_P3));
                        break;
                    case 9:
                        colorSpaces.add(ColorSpace.get(ColorSpace.Named.DISPLAY_P3));
                        break;
                }
            }
            return (ColorSpace[]) colorSpaces.toArray(defaultColorSpaces);
        }
    }

    public long getAppVsyncOffsetNanos() {
        long j;
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            j = this.mDisplayInfo.appVsyncOffsetNanos;
        }
        return j;
    }

    public long getPresentationDeadlineNanos() {
        long j;
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            j = this.mDisplayInfo.presentationDeadlineNanos;
        }
        return j;
    }

    public DeviceProductInfo getDeviceProductInfo() {
        DeviceProductInfo deviceProductInfo;
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            deviceProductInfo = this.mDisplayInfo.deviceProductInfo;
        }
        return deviceProductInfo;
    }

    @Deprecated
    public void getMetrics(DisplayMetrics outMetrics) {
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            this.mDisplayInfo.getAppMetrics(outMetrics, this.mDisplayExt.getDisplayAdjustmentForCompactWindow(this.mResources, getDisplayAdjustments()));
        }
    }

    @Deprecated
    public void getRealSize(Point outSize) {
        synchronized (this.mLock) {
            DisplayAdjustments compactWindowAdjustment = this.mDisplayExt.getCompactWindowDisplayAdjustment(this.mResources);
            if (compactWindowAdjustment == null || compactWindowAdjustment.getConfiguration().windowConfiguration.getAppBounds() == null) {
                updateDisplayInfoLocked();
                if (shouldReportMaxBounds()) {
                    Rect bounds = this.mResources.getConfiguration().windowConfiguration.getMaxBounds();
                    outSize.x = bounds.width();
                    outSize.y = bounds.height();
                    return;
                }
                outSize.x = this.mDisplayInfo.logicalWidth;
                outSize.y = this.mDisplayInfo.logicalHeight;
                IDisplayExt iDisplayExt = this.mDisplayExt;
                if (iDisplayExt != null) {
                    iDisplayExt.updateCompatRealSize(this.mDisplayInfo, getDisplayAdjustments().getCompatibilityInfo(), outSize);
                }
                if (this.mMayAdjustByFixedRotation) {
                    getDisplayAdjustments().adjustSize(outSize, this.mDisplayInfo.rotation);
                }
                return;
            }
            outSize.x = compactWindowAdjustment.getConfiguration().windowConfiguration.getAppBounds().width();
            outSize.y = compactWindowAdjustment.getConfiguration().windowConfiguration.getAppBounds().height();
            if (this.mMayAdjustByFixedRotation) {
                getDisplayAdjustments().adjustSize(outSize, this.mDisplayInfo.rotation);
            }
        }
    }

    @Deprecated
    public void getRealMetrics(DisplayMetrics outMetrics) {
        synchronized (this.mLock) {
            DisplayAdjustments compactWindowAdjustment = this.mDisplayExt.getCompactWindowDisplayAdjustment(this.mResources);
            if (compactWindowAdjustment == null || compactWindowAdjustment.getConfiguration().windowConfiguration.getAppBounds() == null) {
                updateDisplayInfoLocked();
                if (shouldReportMaxBounds()) {
                    this.mDisplayInfo.getMaxBoundsMetrics(outMetrics, CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO, this.mResources.getConfiguration());
                    return;
                }
                this.mDisplayInfo.getLogicalMetrics(outMetrics, CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO, null);
                if (this.mMayAdjustByFixedRotation) {
                    getDisplayAdjustments().adjustMetrics(outMetrics, this.mDisplayInfo.rotation);
                }
                return;
            }
            getMetrics(outMetrics);
        }
    }

    private boolean shouldReportMaxBounds() {
        Configuration config;
        Resources resources = this.mResources;
        return resources != null && (config = resources.getConfiguration()) != null && !config.windowConfiguration.getMaxBounds().isEmpty() && !isRecentsComponent();
    }

    boolean isRecentsComponent() {
        if (this.mIsRecentsComponent.isPresent()) {
            return this.mIsRecentsComponent.get().booleanValue();
        }
        Resources resources = this.mResources;
        if (resources == null) {
            return false;
        }
        try {
            String recentsComponent = resources.getString(R.string.config_recentsComponentName);
            if (recentsComponent == null) {
                return false;
            }
            String recentsPackage = ComponentName.unflattenFromString(recentsComponent).getPackageName();
            Optional<Boolean> of = Optional.of(Boolean.valueOf(recentsPackage != null && recentsPackage.equals(ActivityThread.currentPackageName())));
            this.mIsRecentsComponent = of;
            return of.get().booleanValue();
        } catch (Resources.NotFoundException e) {
            return false;
        }
    }

    public int getState() {
        int i;
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            i = this.mIsValid ? this.mDisplayInfo.state : 0;
        }
        return i;
    }

    public boolean hasAccess(int uid) {
        return hasAccess(uid, this.mFlags, this.mOwnerUid, this.mDisplayId);
    }

    public static boolean hasAccess(int uid, int flags, int ownerUid, int displayId) {
        return (flags & 4) == 0 || uid == ownerUid || uid == 1000 || uid == 0 || DisplayManagerGlobal.getInstance().isUidPresentOnDisplay(uid, displayId);
    }

    public boolean isPublicPresentation() {
        return (this.mFlags & 12) == 8;
    }

    public boolean isTrusted() {
        return (this.mFlags & 128) == 128;
    }

    private void updateDisplayInfoLocked() {
        DisplayInfo newInfo = this.mGlobal.getDisplayInfo(this.mDisplayId);
        boolean z = false;
        if (newInfo != null) {
            this.mDisplayInfo = newInfo;
            if (!this.mIsValid) {
                this.mIsValid = true;
            }
        } else if (this.mIsValid) {
            this.mIsValid = false;
        }
        Resources resources = this.mResources;
        if (resources != null && resources.hasOverrideDisplayAdjustments()) {
            z = true;
        }
        this.mMayAdjustByFixedRotation = z;
    }

    private void updateCachedAppSizeIfNeededLocked() {
        long now = SystemClock.uptimeMillis();
        if (now > this.mLastCachedAppSizeUpdate + 20) {
            updateDisplayInfoLocked();
            this.mDisplayInfo.getAppMetrics(this.mTempMetrics, this.mDisplayExt.getDisplayAdjustmentForCompactWindow(this.mResources, getDisplayAdjustments()));
            this.mDisplayInfo.getAppMetrics(this.mTempMetrics, getDisplayAdjustments());
            this.mCachedAppWidthCompat = this.mTempMetrics.widthPixels;
            this.mCachedAppHeightCompat = this.mTempMetrics.heightPixels;
            this.mLastCachedAppSizeUpdate = now;
        }
    }

    public String toString() {
        String str;
        String sb;
        synchronized (this.mLock) {
            updateDisplayInfoLocked();
            DisplayAdjustments adjustments = getDisplayAdjustments();
            this.mDisplayInfo.getAppMetrics(this.mTempMetrics, adjustments);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Display id ");
            sb2.append(this.mDisplayId);
            sb2.append(": ");
            sb2.append(this.mDisplayInfo);
            if (this.mMayAdjustByFixedRotation) {
                str = ", " + adjustments.getFixedRotationAdjustments() + ", ";
            } else {
                str = ", ";
            }
            sb2.append(str);
            sb2.append(this.mTempMetrics);
            sb2.append(", isValid=");
            sb2.append(this.mIsValid);
            sb = sb2.toString();
        }
        return sb;
    }

    public static String typeToString(int type) {
        switch (type) {
            case 0:
                return "UNKNOWN";
            case 1:
                return "INTERNAL";
            case 2:
                return "EXTERNAL";
            case 3:
                return "WIFI";
            case 4:
                return "OVERLAY";
            case 5:
                return "VIRTUAL";
            default:
                return Integer.toString(type);
        }
    }

    public static String stateToString(int state) {
        switch (state) {
            case 0:
                return "UNKNOWN";
            case 1:
                return "OFF";
            case 2:
                return "ON";
            case 3:
                return "DOZE";
            case 4:
                return "DOZE_SUSPEND";
            case 5:
                return "VR";
            case 6:
                return "ON_SUSPEND";
            default:
                return Integer.toString(state);
        }
    }

    public static boolean isSuspendedState(int state) {
        return state == 1 || state == 4 || state == 6;
    }

    public static boolean isDozeState(int state) {
        return state == 3 || state == 4;
    }

    public static boolean isActiveState(int state) {
        return state == 2 || state == 5;
    }

    public static boolean isOffState(int state) {
        return state == 1;
    }

    public static boolean isOnState(int state) {
        return state == 2 || state == 5 || state == 6;
    }

    /* loaded from: classes3.dex */
    public static final class Mode implements Parcelable {
        private final float[] mAlternativeRefreshRates;
        private final int mHeight;
        private final int mModeId;
        private final float mRefreshRate;
        private final int mWidth;
        public static final Mode[] EMPTY_ARRAY = new Mode[0];
        public static final Parcelable.Creator<Mode> CREATOR = new Parcelable.Creator<Mode>() { // from class: android.view.Display.Mode.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Mode createFromParcel(Parcel in) {
                return new Mode(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Mode[] newArray(int size) {
                return new Mode[size];
            }
        };

        public Mode(int modeId, int width, int height, float refreshRate) {
            this(modeId, width, height, refreshRate, new float[0]);
        }

        public Mode(int modeId, int width, int height, float refreshRate, float[] alternativeRefreshRates) {
            this.mModeId = modeId;
            this.mWidth = width;
            this.mHeight = height;
            this.mRefreshRate = refreshRate;
            float[] copyOf = Arrays.copyOf(alternativeRefreshRates, alternativeRefreshRates.length);
            this.mAlternativeRefreshRates = copyOf;
            Arrays.sort(copyOf);
        }

        public int getModeId() {
            return this.mModeId;
        }

        public int getPhysicalWidth() {
            return this.mWidth;
        }

        public int getPhysicalHeight() {
            return this.mHeight;
        }

        public float getRefreshRate() {
            return this.mRefreshRate;
        }

        public float[] getAlternativeRefreshRates() {
            return this.mAlternativeRefreshRates;
        }

        public boolean matches(int width, int height, float refreshRate) {
            return this.mWidth == width && this.mHeight == height && Float.floatToIntBits(this.mRefreshRate) == Float.floatToIntBits(refreshRate);
        }

        public boolean equalsExceptRefreshRate(Mode other) {
            return this.mWidth == other.mWidth && this.mHeight == other.mHeight;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Mode)) {
                return false;
            }
            Mode that = (Mode) other;
            return this.mModeId == that.mModeId && matches(that.mWidth, that.mHeight, that.mRefreshRate) && Arrays.equals(this.mAlternativeRefreshRates, that.mAlternativeRefreshRates);
        }

        public int hashCode() {
            int hash = (1 * 17) + this.mModeId;
            return (((((((hash * 17) + this.mWidth) * 17) + this.mHeight) * 17) + Float.floatToIntBits(this.mRefreshRate)) * 17) + Arrays.hashCode(this.mAlternativeRefreshRates);
        }

        public String toString() {
            return "{id=" + this.mModeId + ", width=" + this.mWidth + ", height=" + this.mHeight + ", fps=" + this.mRefreshRate + ", alternativeRefreshRates=" + Arrays.toString(this.mAlternativeRefreshRates) + "}";
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        private Mode(Parcel in) {
            this(in.readInt(), in.readInt(), in.readInt(), in.readFloat(), in.createFloatArray());
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel out, int parcelableFlags) {
            out.writeInt(this.mModeId);
            out.writeInt(this.mWidth);
            out.writeInt(this.mHeight);
            out.writeFloat(this.mRefreshRate);
            out.writeFloatArray(this.mAlternativeRefreshRates);
        }
    }

    /* loaded from: classes3.dex */
    public static final class HdrCapabilities implements Parcelable {
        public static final int HDR_TYPE_DOLBY_VISION = 1;
        public static final int HDR_TYPE_HDR10 = 2;
        public static final int HDR_TYPE_HDR10_PLUS = 4;
        public static final int HDR_TYPE_HLG = 3;
        public static final float INVALID_LUMINANCE = -1.0f;
        private float mMaxAverageLuminance;
        private float mMaxLuminance;
        private float mMinLuminance;
        private int[] mSupportedHdrTypes;
        public static final int[] HDR_TYPES = {1, 2, 3, 4};
        public static final Parcelable.Creator<HdrCapabilities> CREATOR = new Parcelable.Creator<HdrCapabilities>() { // from class: android.view.Display.HdrCapabilities.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public HdrCapabilities createFromParcel(Parcel source) {
                return new HdrCapabilities(source);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public HdrCapabilities[] newArray(int size) {
                return new HdrCapabilities[size];
            }
        };

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes3.dex */
        public @interface HdrType {
        }

        public HdrCapabilities() {
            this.mSupportedHdrTypes = new int[0];
            this.mMaxLuminance = -1.0f;
            this.mMaxAverageLuminance = -1.0f;
            this.mMinLuminance = -1.0f;
        }

        public HdrCapabilities(int[] supportedHdrTypes, float maxLuminance, float maxAverageLuminance, float minLuminance) {
            this.mSupportedHdrTypes = new int[0];
            this.mMaxLuminance = -1.0f;
            this.mMaxAverageLuminance = -1.0f;
            this.mMinLuminance = -1.0f;
            this.mSupportedHdrTypes = supportedHdrTypes;
            Arrays.sort(supportedHdrTypes);
            this.mMaxLuminance = maxLuminance;
            this.mMaxAverageLuminance = maxAverageLuminance;
            this.mMinLuminance = minLuminance;
        }

        public int[] getSupportedHdrTypes() {
            return this.mSupportedHdrTypes;
        }

        public float getDesiredMaxLuminance() {
            return this.mMaxLuminance;
        }

        public float getDesiredMaxAverageLuminance() {
            return this.mMaxAverageLuminance;
        }

        public float getDesiredMinLuminance() {
            return this.mMinLuminance;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof HdrCapabilities)) {
                return false;
            }
            HdrCapabilities that = (HdrCapabilities) other;
            return Arrays.equals(this.mSupportedHdrTypes, that.mSupportedHdrTypes) && this.mMaxLuminance == that.mMaxLuminance && this.mMaxAverageLuminance == that.mMaxAverageLuminance && this.mMinLuminance == that.mMinLuminance;
        }

        public int hashCode() {
            int hash = (23 * 17) + Arrays.hashCode(this.mSupportedHdrTypes);
            return (((((hash * 17) + Float.floatToIntBits(this.mMaxLuminance)) * 17) + Float.floatToIntBits(this.mMaxAverageLuminance)) * 17) + Float.floatToIntBits(this.mMinLuminance);
        }

        private HdrCapabilities(Parcel source) {
            this.mSupportedHdrTypes = new int[0];
            this.mMaxLuminance = -1.0f;
            this.mMaxAverageLuminance = -1.0f;
            this.mMinLuminance = -1.0f;
            readFromParcel(source);
        }

        public void readFromParcel(Parcel source) {
            int types = source.readInt();
            this.mSupportedHdrTypes = new int[types];
            for (int i = 0; i < types; i++) {
                this.mSupportedHdrTypes[i] = source.readInt();
            }
            this.mMaxLuminance = source.readFloat();
            this.mMaxAverageLuminance = source.readFloat();
            this.mMinLuminance = source.readFloat();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.mSupportedHdrTypes.length);
            int i = 0;
            while (true) {
                int[] iArr = this.mSupportedHdrTypes;
                if (i < iArr.length) {
                    dest.writeInt(iArr[i]);
                    i++;
                } else {
                    dest.writeFloat(this.mMaxLuminance);
                    dest.writeFloat(this.mMaxAverageLuminance);
                    dest.writeFloat(this.mMinLuminance);
                    return;
                }
            }
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "HdrCapabilities{mSupportedHdrTypes=" + Arrays.toString(this.mSupportedHdrTypes) + ", mMaxLuminance=" + this.mMaxLuminance + ", mMaxAverageLuminance=" + this.mMaxAverageLuminance + ", mMinLuminance=" + this.mMinLuminance + '}';
        }
    }
}
