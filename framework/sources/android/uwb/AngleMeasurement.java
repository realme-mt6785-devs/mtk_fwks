package android.uwb;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;
@SystemApi
/* loaded from: classes3.dex */
public final class AngleMeasurement implements Parcelable {
    public static final Parcelable.Creator<AngleMeasurement> CREATOR = new Parcelable.Creator<AngleMeasurement>() { // from class: android.uwb.AngleMeasurement.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AngleMeasurement createFromParcel(Parcel in) {
            return new AngleMeasurement(in.readDouble(), in.readDouble(), in.readDouble());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AngleMeasurement[] newArray(int size) {
            return new AngleMeasurement[size];
        }
    };
    private final double mConfidenceLevel;
    private final double mErrorRadians;
    private final double mRadians;

    public AngleMeasurement(double radians, double errorRadians, double confidenceLevel) {
        if (radians < -3.141592653589793d || radians > 3.141592653589793d) {
            throw new IllegalArgumentException("Invalid radians: " + radians);
        }
        this.mRadians = radians;
        if (errorRadians < 0.0d || errorRadians > 3.141592653589793d) {
            throw new IllegalArgumentException("Invalid error radians: " + errorRadians);
        }
        this.mErrorRadians = errorRadians;
        if (confidenceLevel < 0.0d || confidenceLevel > 1.0d) {
            throw new IllegalArgumentException("Invalid confidence level: " + confidenceLevel);
        }
        this.mConfidenceLevel = confidenceLevel;
    }

    public double getRadians() {
        return this.mRadians;
    }

    public double getErrorRadians() {
        return this.mErrorRadians;
    }

    public double getConfidenceLevel() {
        return this.mConfidenceLevel;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AngleMeasurement)) {
            return false;
        }
        AngleMeasurement other = (AngleMeasurement) obj;
        return this.mRadians == other.getRadians() && this.mErrorRadians == other.getErrorRadians() && this.mConfidenceLevel == other.getConfidenceLevel();
    }

    public int hashCode() {
        return Objects.hash(Double.valueOf(this.mRadians), Double.valueOf(this.mErrorRadians), Double.valueOf(this.mConfidenceLevel));
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.mRadians);
        dest.writeDouble(this.mErrorRadians);
        dest.writeDouble(this.mConfidenceLevel);
    }
}
