package android.uwb;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;
@SystemApi
/* loaded from: classes3.dex */
public final class RangingMeasurement implements Parcelable {
    public static final Parcelable.Creator<RangingMeasurement> CREATOR = new Parcelable.Creator<RangingMeasurement>() { // from class: android.uwb.RangingMeasurement.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RangingMeasurement createFromParcel(Parcel in) {
            Builder builder = new Builder();
            builder.setRemoteDeviceAddress((UwbAddress) in.readParcelable(UwbAddress.class.getClassLoader()));
            builder.setStatus(in.readInt());
            builder.setElapsedRealtimeNanos(in.readLong());
            builder.setDistanceMeasurement((DistanceMeasurement) in.readParcelable(DistanceMeasurement.class.getClassLoader()));
            builder.setAngleOfArrivalMeasurement((AngleOfArrivalMeasurement) in.readParcelable(AngleOfArrivalMeasurement.class.getClassLoader()));
            return builder.build();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RangingMeasurement[] newArray(int size) {
            return new RangingMeasurement[size];
        }
    };
    public static final int RANGING_STATUS_FAILURE_OUT_OF_RANGE = 1;
    public static final int RANGING_STATUS_FAILURE_UNKNOWN_ERROR = -1;
    public static final int RANGING_STATUS_SUCCESS = 0;
    private final AngleOfArrivalMeasurement mAngleOfArrivalMeasurement;
    private final DistanceMeasurement mDistanceMeasurement;
    private final long mElapsedRealtimeNanos;
    private final UwbAddress mRemoteDeviceAddress;
    private final int mStatus;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface Status {
    }

    private RangingMeasurement(UwbAddress remoteDeviceAddress, int status, long elapsedRealtimeNanos, DistanceMeasurement distanceMeasurement, AngleOfArrivalMeasurement angleOfArrivalMeasurement) {
        this.mRemoteDeviceAddress = remoteDeviceAddress;
        this.mStatus = status;
        this.mElapsedRealtimeNanos = elapsedRealtimeNanos;
        this.mDistanceMeasurement = distanceMeasurement;
        this.mAngleOfArrivalMeasurement = angleOfArrivalMeasurement;
    }

    public UwbAddress getRemoteDeviceAddress() {
        return this.mRemoteDeviceAddress;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public long getElapsedRealtimeNanos() {
        return this.mElapsedRealtimeNanos;
    }

    public DistanceMeasurement getDistanceMeasurement() {
        return this.mDistanceMeasurement;
    }

    public AngleOfArrivalMeasurement getAngleOfArrivalMeasurement() {
        return this.mAngleOfArrivalMeasurement;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RangingMeasurement)) {
            return false;
        }
        RangingMeasurement other = (RangingMeasurement) obj;
        return this.mRemoteDeviceAddress.equals(other.getRemoteDeviceAddress()) && this.mStatus == other.getStatus() && this.mElapsedRealtimeNanos == other.getElapsedRealtimeNanos() && this.mDistanceMeasurement.equals(other.getDistanceMeasurement()) && this.mAngleOfArrivalMeasurement.equals(other.getAngleOfArrivalMeasurement());
    }

    public int hashCode() {
        return Objects.hash(this.mRemoteDeviceAddress, Integer.valueOf(this.mStatus), Long.valueOf(this.mElapsedRealtimeNanos), this.mDistanceMeasurement, this.mAngleOfArrivalMeasurement);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mRemoteDeviceAddress, flags);
        dest.writeInt(this.mStatus);
        dest.writeLong(this.mElapsedRealtimeNanos);
        dest.writeParcelable(this.mDistanceMeasurement, flags);
        dest.writeParcelable(this.mAngleOfArrivalMeasurement, flags);
    }

    /* loaded from: classes3.dex */
    public static final class Builder {
        private UwbAddress mRemoteDeviceAddress = null;
        private int mStatus = -1;
        private long mElapsedRealtimeNanos = -1;
        private DistanceMeasurement mDistanceMeasurement = null;
        private AngleOfArrivalMeasurement mAngleOfArrivalMeasurement = null;

        public Builder setRemoteDeviceAddress(UwbAddress remoteDeviceAddress) {
            this.mRemoteDeviceAddress = remoteDeviceAddress;
            return this;
        }

        public Builder setStatus(int status) {
            this.mStatus = status;
            return this;
        }

        public Builder setElapsedRealtimeNanos(long elapsedRealtimeNanos) {
            if (elapsedRealtimeNanos >= 0) {
                this.mElapsedRealtimeNanos = elapsedRealtimeNanos;
                return this;
            }
            throw new IllegalArgumentException("elapsedRealtimeNanos must be >= 0");
        }

        public Builder setDistanceMeasurement(DistanceMeasurement distanceMeasurement) {
            this.mDistanceMeasurement = distanceMeasurement;
            return this;
        }

        public Builder setAngleOfArrivalMeasurement(AngleOfArrivalMeasurement angleOfArrivalMeasurement) {
            this.mAngleOfArrivalMeasurement = angleOfArrivalMeasurement;
            return this;
        }

        public RangingMeasurement build() {
            if (this.mStatus != 0) {
                if (this.mDistanceMeasurement != null) {
                    throw new IllegalStateException("Distance Measurement must be null if ranging is not successful");
                } else if (this.mAngleOfArrivalMeasurement != null) {
                    throw new IllegalStateException("Angle of Arrival must be null if ranging is not successful");
                }
            }
            if (this.mRemoteDeviceAddress == null) {
                throw new IllegalStateException("No remote device address was set");
            } else if (this.mElapsedRealtimeNanos >= 0) {
                return new RangingMeasurement(this.mRemoteDeviceAddress, this.mStatus, this.mElapsedRealtimeNanos, this.mDistanceMeasurement, this.mAngleOfArrivalMeasurement);
            } else {
                throw new IllegalStateException("elapsedRealtimeNanos must be >=0: " + this.mElapsedRealtimeNanos);
            }
        }
    }
}
