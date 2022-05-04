package android.uwb;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;
@SystemApi
/* loaded from: classes3.dex */
public final class DistanceMeasurement implements Parcelable {
    public static final Parcelable.Creator<DistanceMeasurement> CREATOR = new Parcelable.Creator<DistanceMeasurement>() { // from class: android.uwb.DistanceMeasurement.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DistanceMeasurement createFromParcel(Parcel in) {
            Builder builder = new Builder();
            builder.setMeters(in.readDouble());
            builder.setErrorMeters(in.readDouble());
            builder.setConfidenceLevel(in.readDouble());
            return builder.build();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DistanceMeasurement[] newArray(int size) {
            return new DistanceMeasurement[size];
        }
    };
    private final double mConfidenceLevel;
    private final double mErrorMeters;
    private final double mMeters;

    private DistanceMeasurement(double meters, double errorMeters, double confidenceLevel) {
        this.mMeters = meters;
        this.mErrorMeters = errorMeters;
        this.mConfidenceLevel = confidenceLevel;
    }

    public double getMeters() {
        return this.mMeters;
    }

    public double getErrorMeters() {
        return this.mErrorMeters;
    }

    public double getConfidenceLevel() {
        return this.mConfidenceLevel;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DistanceMeasurement)) {
            return false;
        }
        DistanceMeasurement other = (DistanceMeasurement) obj;
        return this.mMeters == other.getMeters() && this.mErrorMeters == other.getErrorMeters() && this.mConfidenceLevel == other.getConfidenceLevel();
    }

    public int hashCode() {
        return Objects.hash(Double.valueOf(this.mMeters), Double.valueOf(this.mErrorMeters), Double.valueOf(this.mConfidenceLevel));
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.mMeters);
        dest.writeDouble(this.mErrorMeters);
        dest.writeDouble(this.mConfidenceLevel);
    }

    /* loaded from: classes3.dex */
    public static final class Builder {
        private double mMeters = Double.NaN;
        private double mErrorMeters = Double.NaN;
        private double mConfidenceLevel = Double.NaN;

        public Builder setMeters(double meters) {
            if (!Double.isNaN(meters)) {
                this.mMeters = meters;
                return this;
            }
            throw new IllegalArgumentException("meters cannot be NaN");
        }

        public Builder setErrorMeters(double errorMeters) {
            if (Double.isNaN(errorMeters) || errorMeters < 0.0d) {
                throw new IllegalArgumentException("errorMeters must be >= 0.0 and not NaN: " + errorMeters);
            }
            this.mErrorMeters = errorMeters;
            return this;
        }

        public Builder setConfidenceLevel(double confidenceLevel) {
            if (confidenceLevel < 0.0d || confidenceLevel > 1.0d) {
                throw new IllegalArgumentException("confidenceLevel must be in the range [0.0, 1.0]: " + confidenceLevel);
            }
            this.mConfidenceLevel = confidenceLevel;
            return this;
        }

        public DistanceMeasurement build() {
            if (Double.isNaN(this.mMeters)) {
                throw new IllegalStateException("Meters cannot be NaN");
            } else if (Double.isNaN(this.mErrorMeters)) {
                throw new IllegalStateException("Error meters cannot be NaN");
            } else if (!Double.isNaN(this.mConfidenceLevel)) {
                return new DistanceMeasurement(this.mMeters, this.mErrorMeters, this.mConfidenceLevel);
            } else {
                throw new IllegalStateException("Confidence level cannot be NaN");
            }
        }
    }
}
