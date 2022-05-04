package android.uwb;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@SystemApi
/* loaded from: classes3.dex */
public final class RangingReport implements Parcelable {
    public static final Parcelable.Creator<RangingReport> CREATOR = new Parcelable.Creator<RangingReport>() { // from class: android.uwb.RangingReport.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RangingReport createFromParcel(Parcel in) {
            Builder builder = new Builder();
            builder.addMeasurements(in.createTypedArrayList(RangingMeasurement.CREATOR));
            return builder.build();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RangingReport[] newArray(int size) {
            return new RangingReport[size];
        }
    };
    private final List<RangingMeasurement> mRangingMeasurements;

    private RangingReport(List<RangingMeasurement> rangingMeasurements) {
        this.mRangingMeasurements = rangingMeasurements;
    }

    public List<RangingMeasurement> getMeasurements() {
        return this.mRangingMeasurements;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RangingReport)) {
            return false;
        }
        RangingReport other = (RangingReport) obj;
        return this.mRangingMeasurements.equals(other.getMeasurements());
    }

    public int hashCode() {
        return Objects.hash(this.mRangingMeasurements);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.mRangingMeasurements);
    }

    /* loaded from: classes3.dex */
    public static final class Builder {
        List<RangingMeasurement> mMeasurements = new ArrayList();

        public Builder addMeasurement(RangingMeasurement rangingMeasurement) {
            this.mMeasurements.add(rangingMeasurement);
            return this;
        }

        public Builder addMeasurements(List<RangingMeasurement> rangingMeasurements) {
            this.mMeasurements.addAll(rangingMeasurements);
            return this;
        }

        public RangingReport build() {
            RangingMeasurement prevMeasurement = null;
            for (int curIndex = 0; curIndex < this.mMeasurements.size(); curIndex++) {
                RangingMeasurement curMeasurement = this.mMeasurements.get(curIndex);
                if (prevMeasurement == null || prevMeasurement.getElapsedRealtimeNanos() <= curMeasurement.getElapsedRealtimeNanos()) {
                    prevMeasurement = curMeasurement;
                } else {
                    throw new IllegalStateException("Timestamp (" + curMeasurement.getElapsedRealtimeNanos() + ") at index " + curIndex + " is less than previous timestamp (" + prevMeasurement.getElapsedRealtimeNanos() + ")");
                }
            }
            return new RangingReport(this.mMeasurements);
        }
    }
}
