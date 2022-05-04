package android.location;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
@SystemApi
/* loaded from: classes2.dex */
public final class SatellitePvt implements Parcelable {
    public static final Parcelable.Creator<SatellitePvt> CREATOR = new Parcelable.Creator<SatellitePvt>() { // from class: android.location.SatellitePvt.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SatellitePvt createFromParcel(Parcel in) {
            int flags = in.readInt();
            ClassLoader classLoader = getClass().getClassLoader();
            PositionEcef positionEcef = (PositionEcef) in.readParcelable(classLoader);
            VelocityEcef velocityEcef = (VelocityEcef) in.readParcelable(classLoader);
            ClockInfo clockInfo = (ClockInfo) in.readParcelable(classLoader);
            double ionoDelayMeters = in.readDouble();
            double tropoDelayMeters = in.readDouble();
            return new SatellitePvt(flags, positionEcef, velocityEcef, clockInfo, ionoDelayMeters, tropoDelayMeters);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SatellitePvt[] newArray(int size) {
            return new SatellitePvt[size];
        }
    };
    private static final int HAS_IONO = 2;
    private static final int HAS_POSITION_VELOCITY_CLOCK_INFO = 1;
    private static final int HAS_TROPO = 4;
    private final ClockInfo mClockInfo;
    private final int mFlags;
    private final double mIonoDelayMeters;
    private final PositionEcef mPositionEcef;
    private final double mTropoDelayMeters;
    private final VelocityEcef mVelocityEcef;

    /* loaded from: classes2.dex */
    public static final class PositionEcef implements Parcelable {
        public static final Parcelable.Creator<PositionEcef> CREATOR = new Parcelable.Creator<PositionEcef>() { // from class: android.location.SatellitePvt.PositionEcef.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public PositionEcef createFromParcel(Parcel in) {
                return new PositionEcef(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public PositionEcef[] newArray(int size) {
                return new PositionEcef[size];
            }
        };
        private final double mUreMeters;
        private final double mXMeters;
        private final double mYMeters;
        private final double mZMeters;

        public PositionEcef(double xMeters, double yMeters, double zMeters, double ureMeters) {
            this.mXMeters = xMeters;
            this.mYMeters = yMeters;
            this.mZMeters = zMeters;
            this.mUreMeters = ureMeters;
        }

        public double getXMeters() {
            return this.mXMeters;
        }

        public double getYMeters() {
            return this.mYMeters;
        }

        public double getZMeters() {
            return this.mZMeters;
        }

        public double getUreMeters() {
            return this.mUreMeters;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeDouble(this.mXMeters);
            dest.writeDouble(this.mYMeters);
            dest.writeDouble(this.mZMeters);
            dest.writeDouble(this.mUreMeters);
        }

        public String toString() {
            return "PositionEcef{xMeters=" + this.mXMeters + ", yMeters=" + this.mYMeters + ", zMeters=" + this.mZMeters + ", ureMeters=" + this.mUreMeters + "}";
        }
    }

    /* loaded from: classes2.dex */
    public static final class VelocityEcef implements Parcelable {
        public static final Parcelable.Creator<VelocityEcef> CREATOR = new Parcelable.Creator<VelocityEcef>() { // from class: android.location.SatellitePvt.VelocityEcef.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public VelocityEcef createFromParcel(Parcel in) {
                return new VelocityEcef(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public VelocityEcef[] newArray(int size) {
                return new VelocityEcef[size];
            }
        };
        private final double mUreRateMetersPerSecond;
        private final double mXMetersPerSecond;
        private final double mYMetersPerSecond;
        private final double mZMetersPerSecond;

        public VelocityEcef(double xMetersPerSecond, double yMetersPerSecond, double zMetersPerSecond, double ureRateMetersPerSecond) {
            this.mXMetersPerSecond = xMetersPerSecond;
            this.mYMetersPerSecond = yMetersPerSecond;
            this.mZMetersPerSecond = zMetersPerSecond;
            this.mUreRateMetersPerSecond = ureRateMetersPerSecond;
        }

        public double getXMetersPerSecond() {
            return this.mXMetersPerSecond;
        }

        public double getYMetersPerSecond() {
            return this.mYMetersPerSecond;
        }

        public double getZMetersPerSecond() {
            return this.mZMetersPerSecond;
        }

        public double getUreRateMetersPerSecond() {
            return this.mUreRateMetersPerSecond;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeDouble(this.mXMetersPerSecond);
            dest.writeDouble(this.mYMetersPerSecond);
            dest.writeDouble(this.mZMetersPerSecond);
            dest.writeDouble(this.mUreRateMetersPerSecond);
        }

        public String toString() {
            return "VelocityEcef{xMetersPerSecond=" + this.mXMetersPerSecond + ", yMetersPerSecond=" + this.mYMetersPerSecond + ", zMetersPerSecond=" + this.mZMetersPerSecond + ", ureRateMetersPerSecond=" + this.mUreRateMetersPerSecond + "}";
        }
    }

    /* loaded from: classes2.dex */
    public static final class ClockInfo implements Parcelable {
        public static final Parcelable.Creator<ClockInfo> CREATOR = new Parcelable.Creator<ClockInfo>() { // from class: android.location.SatellitePvt.ClockInfo.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ClockInfo createFromParcel(Parcel in) {
                return new ClockInfo(in.readDouble(), in.readDouble(), in.readDouble());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ClockInfo[] newArray(int size) {
                return new ClockInfo[size];
            }
        };
        private final double mClockDriftMetersPerSecond;
        private final double mHardwareCodeBiasMeters;
        private final double mTimeCorrectionMeters;

        public ClockInfo(double hardwareCodeBiasMeters, double timeCorrectionMeters, double clockDriftMetersPerSecond) {
            this.mHardwareCodeBiasMeters = hardwareCodeBiasMeters;
            this.mTimeCorrectionMeters = timeCorrectionMeters;
            this.mClockDriftMetersPerSecond = clockDriftMetersPerSecond;
        }

        public double getHardwareCodeBiasMeters() {
            return this.mHardwareCodeBiasMeters;
        }

        public double getTimeCorrectionMeters() {
            return this.mTimeCorrectionMeters;
        }

        public double getClockDriftMetersPerSecond() {
            return this.mClockDriftMetersPerSecond;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeDouble(this.mHardwareCodeBiasMeters);
            dest.writeDouble(this.mTimeCorrectionMeters);
            dest.writeDouble(this.mClockDriftMetersPerSecond);
        }

        public String toString() {
            return "ClockInfo{hardwareCodeBiasMeters=" + this.mHardwareCodeBiasMeters + ", timeCorrectionMeters=" + this.mTimeCorrectionMeters + ", clockDriftMetersPerSecond=" + this.mClockDriftMetersPerSecond + "}";
        }
    }

    private SatellitePvt(int flags, PositionEcef positionEcef, VelocityEcef velocityEcef, ClockInfo clockInfo, double ionoDelayMeters, double tropoDelayMeters) {
        this.mFlags = flags;
        this.mPositionEcef = positionEcef;
        this.mVelocityEcef = velocityEcef;
        this.mClockInfo = clockInfo;
        this.mIonoDelayMeters = ionoDelayMeters;
        this.mTropoDelayMeters = tropoDelayMeters;
    }

    public PositionEcef getPositionEcef() {
        return this.mPositionEcef;
    }

    public VelocityEcef getVelocityEcef() {
        return this.mVelocityEcef;
    }

    public ClockInfo getClockInfo() {
        return this.mClockInfo;
    }

    public double getIonoDelayMeters() {
        return this.mIonoDelayMeters;
    }

    public double getTropoDelayMeters() {
        return this.mTropoDelayMeters;
    }

    public boolean hasPositionVelocityClockInfo() {
        return (this.mFlags & 1) != 0;
    }

    public boolean hasIono() {
        return (this.mFlags & 2) != 0;
    }

    public boolean hasTropo() {
        return (this.mFlags & 4) != 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this.mFlags);
        parcel.writeParcelable(this.mPositionEcef, flags);
        parcel.writeParcelable(this.mVelocityEcef, flags);
        parcel.writeParcelable(this.mClockInfo, flags);
        parcel.writeDouble(this.mIonoDelayMeters);
        parcel.writeDouble(this.mTropoDelayMeters);
    }

    public String toString() {
        return "SatellitePvt{Flags=" + this.mFlags + ", PositionEcef=" + this.mPositionEcef + ", VelocityEcef=" + this.mVelocityEcef + ", ClockInfo=" + this.mClockInfo + ", IonoDelayMeters=" + this.mIonoDelayMeters + ", TropoDelayMeters=" + this.mTropoDelayMeters + "}";
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        private ClockInfo mClockInfo;
        private int mFlags;
        private double mIonoDelayMeters;
        private PositionEcef mPositionEcef;
        private double mTropoDelayMeters;
        private VelocityEcef mVelocityEcef;

        public Builder setPositionEcef(PositionEcef positionEcef) {
            this.mPositionEcef = positionEcef;
            updateFlags();
            return this;
        }

        public Builder setVelocityEcef(VelocityEcef velocityEcef) {
            this.mVelocityEcef = velocityEcef;
            updateFlags();
            return this;
        }

        public Builder setClockInfo(ClockInfo clockInfo) {
            this.mClockInfo = clockInfo;
            updateFlags();
            return this;
        }

        private void updateFlags() {
            if (this.mPositionEcef != null && this.mVelocityEcef != null && this.mClockInfo != null) {
                this.mFlags = (byte) (this.mFlags | 1);
            }
        }

        public Builder setIonoDelayMeters(double ionoDelayMeters) {
            this.mIonoDelayMeters = ionoDelayMeters;
            this.mFlags = (byte) (this.mFlags | 2);
            return this;
        }

        public Builder setTropoDelayMeters(double tropoDelayMeters) {
            this.mTropoDelayMeters = tropoDelayMeters;
            this.mFlags = (byte) (this.mFlags | 4);
            return this;
        }

        public SatellitePvt build() {
            return new SatellitePvt(this.mFlags, this.mPositionEcef, this.mVelocityEcef, this.mClockInfo, this.mIonoDelayMeters, this.mTropoDelayMeters);
        }
    }
}
