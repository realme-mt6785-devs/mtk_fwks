package android.uwb;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;
@SystemApi
/* loaded from: classes3.dex */
public final class AngleOfArrivalMeasurement implements Parcelable {
    public static final Parcelable.Creator<AngleOfArrivalMeasurement> CREATOR = new Parcelable.Creator<AngleOfArrivalMeasurement>() { // from class: android.uwb.AngleOfArrivalMeasurement.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AngleOfArrivalMeasurement createFromParcel(Parcel in) {
            Builder builder = new Builder((AngleMeasurement) in.readParcelable(AngleMeasurement.class.getClassLoader()));
            builder.setAltitude((AngleMeasurement) in.readParcelable(AngleMeasurement.class.getClassLoader()));
            return builder.build();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AngleOfArrivalMeasurement[] newArray(int size) {
            return new AngleOfArrivalMeasurement[size];
        }
    };
    private final AngleMeasurement mAltitudeAngleMeasurement;
    private final AngleMeasurement mAzimuthAngleMeasurement;

    private AngleOfArrivalMeasurement(AngleMeasurement azimuthAngleMeasurement, AngleMeasurement altitudeAngleMeasurement) {
        this.mAzimuthAngleMeasurement = azimuthAngleMeasurement;
        this.mAltitudeAngleMeasurement = altitudeAngleMeasurement;
    }

    public AngleMeasurement getAzimuth() {
        return this.mAzimuthAngleMeasurement;
    }

    public AngleMeasurement getAltitude() {
        return this.mAltitudeAngleMeasurement;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AngleOfArrivalMeasurement)) {
            return false;
        }
        AngleOfArrivalMeasurement other = (AngleOfArrivalMeasurement) obj;
        return this.mAzimuthAngleMeasurement.equals(other.getAzimuth()) && this.mAltitudeAngleMeasurement.equals(other.getAltitude());
    }

    public int hashCode() {
        return Objects.hash(this.mAzimuthAngleMeasurement, this.mAltitudeAngleMeasurement);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mAzimuthAngleMeasurement, flags);
        dest.writeParcelable(this.mAltitudeAngleMeasurement, flags);
    }

    /* loaded from: classes3.dex */
    public static final class Builder {
        private AngleMeasurement mAltitudeAngleMeasurement = null;
        private final AngleMeasurement mAzimuthAngleMeasurement;

        public Builder(AngleMeasurement azimuthAngle) {
            this.mAzimuthAngleMeasurement = azimuthAngle;
        }

        public Builder setAltitude(AngleMeasurement altitudeAngle) {
            this.mAltitudeAngleMeasurement = altitudeAngle;
            return this;
        }

        public AngleOfArrivalMeasurement build() {
            return new AngleOfArrivalMeasurement(this.mAzimuthAngleMeasurement, this.mAltitudeAngleMeasurement);
        }
    }
}
