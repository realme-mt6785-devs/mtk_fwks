package android.view;

import android.content.res.CompatibilityInfo;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.view.DisplayCutout;
import java.util.Objects;
/* loaded from: classes3.dex */
public class DisplayAdjustments {
    public static final DisplayAdjustments DEFAULT_DISPLAY_ADJUSTMENTS = new DisplayAdjustments();
    private volatile CompatibilityInfo mCompatInfo;
    private final Configuration mConfiguration;
    private FixedRotationAdjustments mFixedRotationAdjustments;

    public DisplayAdjustments() {
        this.mCompatInfo = CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO;
        this.mConfiguration = new Configuration(Configuration.EMPTY);
    }

    public DisplayAdjustments(Configuration configuration) {
        this.mCompatInfo = CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO;
        Configuration configuration2 = new Configuration(Configuration.EMPTY);
        this.mConfiguration = configuration2;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
    }

    public DisplayAdjustments(DisplayAdjustments daj) {
        this.mCompatInfo = CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO;
        Configuration configuration = new Configuration(Configuration.EMPTY);
        this.mConfiguration = configuration;
        setCompatibilityInfo(daj.mCompatInfo);
        configuration.setTo(daj.getConfiguration());
        this.mFixedRotationAdjustments = daj.mFixedRotationAdjustments;
    }

    public void setCompatibilityInfo(CompatibilityInfo compatInfo) {
        if (this == DEFAULT_DISPLAY_ADJUSTMENTS) {
            throw new IllegalArgumentException("setCompatbilityInfo: Cannot modify DEFAULT_DISPLAY_ADJUSTMENTS");
        } else if (compatInfo == null || (!compatInfo.isScalingRequired() && compatInfo.supportsScreen())) {
            this.mCompatInfo = CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO;
        } else {
            this.mCompatInfo = compatInfo;
        }
    }

    public CompatibilityInfo getCompatibilityInfo() {
        return this.mCompatInfo;
    }

    public void setConfiguration(Configuration configuration) {
        if (this != DEFAULT_DISPLAY_ADJUSTMENTS) {
            this.mConfiguration.setTo(configuration != null ? configuration : Configuration.EMPTY);
            return;
        }
        throw new IllegalArgumentException("setConfiguration: Cannot modify DEFAULT_DISPLAY_ADJUSTMENTS");
    }

    public Configuration getConfiguration() {
        return this.mConfiguration;
    }

    public void setFixedRotationAdjustments(FixedRotationAdjustments fixedRotationAdjustments) {
        this.mFixedRotationAdjustments = fixedRotationAdjustments;
    }

    public FixedRotationAdjustments getFixedRotationAdjustments() {
        return this.mFixedRotationAdjustments;
    }

    private boolean noFlip(int realRotation) {
        FixedRotationAdjustments rotationAdjustments = this.mFixedRotationAdjustments;
        return rotationAdjustments == null || ((realRotation - rotationAdjustments.mRotation) + 4) % 2 == 0;
    }

    public void adjustSize(Point size, int realRotation) {
        if (!noFlip(realRotation)) {
            int w = size.x;
            size.x = size.y;
            size.y = w;
        }
    }

    public void adjustMetrics(DisplayMetrics metrics, int realRotation) {
        if (!noFlip(realRotation)) {
            int w = metrics.widthPixels;
            metrics.widthPixels = metrics.heightPixels;
            metrics.heightPixels = w;
            int w2 = metrics.noncompatWidthPixels;
            metrics.noncompatWidthPixels = metrics.noncompatHeightPixels;
            metrics.noncompatHeightPixels = w2;
        }
    }

    public void adjustGlobalAppMetrics(DisplayMetrics metrics) {
        FixedRotationAdjustments rotationAdjustments = this.mFixedRotationAdjustments;
        if (rotationAdjustments != null) {
            int i = rotationAdjustments.mAppWidth;
            metrics.widthPixels = i;
            metrics.noncompatWidthPixels = i;
            int i2 = rotationAdjustments.mAppHeight;
            metrics.heightPixels = i2;
            metrics.noncompatHeightPixels = i2;
        }
    }

    public DisplayCutout getDisplayCutout(DisplayCutout realCutout) {
        FixedRotationAdjustments rotationAdjustments = this.mFixedRotationAdjustments;
        if (rotationAdjustments == null || rotationAdjustments.mRotatedDisplayCutout == null) {
            return realCutout;
        }
        return rotationAdjustments.mRotatedDisplayCutout;
    }

    public RoundedCorners adjustRoundedCorner(RoundedCorners realRoundedCorners, int realRotation, int displayWidth, int displayHeight) {
        FixedRotationAdjustments rotationAdjustments = this.mFixedRotationAdjustments;
        if (realRoundedCorners == null || rotationAdjustments == null || rotationAdjustments.mRotation == realRotation) {
            return realRoundedCorners;
        }
        return realRoundedCorners.rotate(rotationAdjustments.mRotation, displayWidth, displayHeight);
    }

    public int getRotation(int realRotation) {
        FixedRotationAdjustments rotationAdjustments = this.mFixedRotationAdjustments;
        return rotationAdjustments != null ? rotationAdjustments.mRotation : realRotation;
    }

    public int hashCode() {
        int hash = (17 * 31) + Objects.hashCode(this.mCompatInfo);
        return (((hash * 31) + Objects.hashCode(this.mConfiguration)) * 31) + Objects.hashCode(this.mFixedRotationAdjustments);
    }

    public boolean equals(Object o) {
        if (!(o instanceof DisplayAdjustments)) {
            return false;
        }
        DisplayAdjustments daj = (DisplayAdjustments) o;
        return Objects.equals(daj.mCompatInfo, this.mCompatInfo) && Objects.equals(daj.mConfiguration, this.mConfiguration) && Objects.equals(daj.mFixedRotationAdjustments, this.mFixedRotationAdjustments);
    }

    /* loaded from: classes3.dex */
    public static class FixedRotationAdjustments implements Parcelable {
        public static final Parcelable.Creator<FixedRotationAdjustments> CREATOR = new Parcelable.Creator<FixedRotationAdjustments>() { // from class: android.view.DisplayAdjustments.FixedRotationAdjustments.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public FixedRotationAdjustments createFromParcel(Parcel in) {
                return new FixedRotationAdjustments(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public FixedRotationAdjustments[] newArray(int size) {
                return new FixedRotationAdjustments[size];
            }
        };
        final int mAppHeight;
        final int mAppWidth;
        final DisplayCutout mRotatedDisplayCutout;
        final int mRotation;

        public FixedRotationAdjustments(int rotation, int appWidth, int appHeight, DisplayCutout cutout) {
            this.mRotation = rotation;
            this.mAppWidth = appWidth;
            this.mAppHeight = appHeight;
            this.mRotatedDisplayCutout = cutout;
        }

        public int hashCode() {
            int hash = (17 * 31) + this.mRotation;
            return (((((hash * 31) + this.mAppWidth) * 31) + this.mAppHeight) * 31) + Objects.hashCode(this.mRotatedDisplayCutout);
        }

        public boolean equals(Object o) {
            if (!(o instanceof FixedRotationAdjustments)) {
                return false;
            }
            FixedRotationAdjustments other = (FixedRotationAdjustments) o;
            return this.mRotation == other.mRotation && this.mAppWidth == other.mAppWidth && this.mAppHeight == other.mAppHeight && Objects.equals(this.mRotatedDisplayCutout, other.mRotatedDisplayCutout);
        }

        public String toString() {
            return "FixedRotationAdjustments{rotation=" + Surface.rotationToString(this.mRotation) + " appWidth=" + this.mAppWidth + " appHeight=" + this.mAppHeight + " cutout=" + this.mRotatedDisplayCutout + "}";
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.mRotation);
            dest.writeInt(this.mAppWidth);
            dest.writeInt(this.mAppHeight);
            dest.writeTypedObject(new DisplayCutout.ParcelableWrapper(this.mRotatedDisplayCutout), flags);
        }

        private FixedRotationAdjustments(Parcel in) {
            this.mRotation = in.readInt();
            this.mAppWidth = in.readInt();
            this.mAppHeight = in.readInt();
            DisplayCutout.ParcelableWrapper cutoutWrapper = (DisplayCutout.ParcelableWrapper) in.readTypedObject(DisplayCutout.ParcelableWrapper.CREATOR);
            this.mRotatedDisplayCutout = cutoutWrapper != null ? cutoutWrapper.get() : null;
        }
    }
}
