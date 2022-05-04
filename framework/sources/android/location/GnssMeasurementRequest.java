package android.location;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;
/* loaded from: classes2.dex */
public final class GnssMeasurementRequest implements Parcelable {
    public static final Parcelable.Creator<GnssMeasurementRequest> CREATOR = new Parcelable.Creator<GnssMeasurementRequest>() { // from class: android.location.GnssMeasurementRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GnssMeasurementRequest createFromParcel(Parcel parcel) {
            return new GnssMeasurementRequest(parcel.readBoolean(), parcel.readBoolean());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GnssMeasurementRequest[] newArray(int i) {
            return new GnssMeasurementRequest[i];
        }
    };
    private final boolean mCorrelationVectorOutputsEnabled;
    private final boolean mFullTracking;

    private GnssMeasurementRequest(boolean fullTracking, boolean correlationVectorOutputsEnabled) {
        this.mFullTracking = fullTracking;
        this.mCorrelationVectorOutputsEnabled = correlationVectorOutputsEnabled;
    }

    @SystemApi
    public boolean isCorrelationVectorOutputsEnabled() {
        return this.mCorrelationVectorOutputsEnabled;
    }

    public boolean isFullTracking() {
        return this.mFullTracking;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeBoolean(this.mFullTracking);
        parcel.writeBoolean(this.mCorrelationVectorOutputsEnabled);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("GnssMeasurementRequest[");
        if (this.mFullTracking) {
            s.append("FullTracking");
        }
        if (this.mCorrelationVectorOutputsEnabled) {
            s.append(", CorrelationVectorOutPuts");
        }
        s.append(']');
        return s.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GnssMeasurementRequest)) {
            return false;
        }
        GnssMeasurementRequest other = (GnssMeasurementRequest) obj;
        if (this.mFullTracking == other.mFullTracking && this.mCorrelationVectorOutputsEnabled == other.mCorrelationVectorOutputsEnabled) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.mFullTracking), Boolean.valueOf(this.mCorrelationVectorOutputsEnabled));
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        private boolean mCorrelationVectorOutputsEnabled;
        private boolean mFullTracking;

        public Builder() {
        }

        public Builder(GnssMeasurementRequest request) {
            this.mCorrelationVectorOutputsEnabled = request.isCorrelationVectorOutputsEnabled();
            this.mFullTracking = request.isFullTracking();
        }

        @SystemApi
        public Builder setCorrelationVectorOutputsEnabled(boolean value) {
            this.mCorrelationVectorOutputsEnabled = value;
            return this;
        }

        public Builder setFullTracking(boolean value) {
            this.mFullTracking = value;
            return this;
        }

        public GnssMeasurementRequest build() {
            return new GnssMeasurementRequest(this.mFullTracking, this.mCorrelationVectorOutputsEnabled);
        }
    }
}
