package android.telephony.data;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class TrafficDescriptor implements Parcelable {
    public static final Parcelable.Creator<TrafficDescriptor> CREATOR = new Parcelable.Creator<TrafficDescriptor>() { // from class: android.telephony.data.TrafficDescriptor.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TrafficDescriptor createFromParcel(Parcel source) {
            return new TrafficDescriptor(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TrafficDescriptor[] newArray(int size) {
            return new TrafficDescriptor[size];
        }
    };
    private final String mDnn;
    private final byte[] mOsAppId;

    private TrafficDescriptor(Parcel in) {
        this.mDnn = in.readString();
        this.mOsAppId = in.createByteArray();
    }

    public TrafficDescriptor(String dnn, byte[] osAppId) {
        this.mDnn = dnn;
        this.mOsAppId = osAppId;
    }

    public String getDataNetworkName() {
        return this.mDnn;
    }

    public byte[] getOsAppId() {
        return this.mOsAppId;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "TrafficDescriptor={mDnn=" + this.mDnn + ", mOsAppId=" + this.mOsAppId + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mDnn);
        dest.writeByteArray(this.mOsAppId);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TrafficDescriptor that = (TrafficDescriptor) o;
        if (!Objects.equals(this.mDnn, that.mDnn) || !Arrays.equals(this.mOsAppId, that.mOsAppId)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(this.mDnn, this.mOsAppId);
    }

    /* loaded from: classes3.dex */
    public static final class Builder {
        private String mDnn = null;
        private byte[] mOsAppId = null;

        public Builder setDataNetworkName(String dnn) {
            this.mDnn = dnn;
            return this;
        }

        public Builder setOsAppId(byte[] osAppId) {
            this.mOsAppId = osAppId;
            return this;
        }

        public TrafficDescriptor build() {
            if (this.mDnn != null || this.mOsAppId != null) {
                return new TrafficDescriptor(this.mDnn, this.mOsAppId);
            }
            throw new IllegalArgumentException("DNN and OS App ID are null");
        }
    }
}
