package android.telephony.data;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;
/* loaded from: classes3.dex */
public abstract class Qos {
    static final int QOS_TYPE_EPS = 1;
    static final int QOS_TYPE_NR = 2;
    final QosBandwidth downlink;
    final int type;
    final QosBandwidth uplink;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface QosType {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Qos(int type, android.hardware.radio.V1_6.QosBandwidth downlink, android.hardware.radio.V1_6.QosBandwidth uplink) {
        this.type = type;
        this.downlink = new QosBandwidth(downlink.maxBitrateKbps, downlink.guaranteedBitrateKbps);
        this.uplink = new QosBandwidth(uplink.maxBitrateKbps, uplink.guaranteedBitrateKbps);
    }

    public QosBandwidth getDownlinkBandwidth() {
        return this.downlink;
    }

    public QosBandwidth getUplinkBandwidth() {
        return this.uplink;
    }

    /* loaded from: classes3.dex */
    public static class QosBandwidth implements Parcelable {
        public static final Parcelable.Creator<QosBandwidth> CREATOR = new Parcelable.Creator<QosBandwidth>() { // from class: android.telephony.data.Qos.QosBandwidth.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public QosBandwidth createFromParcel(Parcel source) {
                return new QosBandwidth(source);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public QosBandwidth[] newArray(int size) {
                return new QosBandwidth[size];
            }
        };
        int guaranteedBitrateKbps;
        int maxBitrateKbps;

        QosBandwidth() {
        }

        QosBandwidth(int maxBitrateKbps, int guaranteedBitrateKbps) {
            this.maxBitrateKbps = maxBitrateKbps;
            this.guaranteedBitrateKbps = guaranteedBitrateKbps;
        }

        private QosBandwidth(Parcel source) {
            this.maxBitrateKbps = source.readInt();
            this.guaranteedBitrateKbps = source.readInt();
        }

        public int getMaxBitrateKbps() {
            return this.maxBitrateKbps;
        }

        public int getGuaranteedBitrateKbps() {
            return this.guaranteedBitrateKbps;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.maxBitrateKbps);
            dest.writeInt(this.guaranteedBitrateKbps);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.maxBitrateKbps), Integer.valueOf(this.guaranteedBitrateKbps));
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || !(o instanceof QosBandwidth)) {
                return false;
            }
            QosBandwidth other = (QosBandwidth) o;
            if (this.maxBitrateKbps == other.maxBitrateKbps && this.guaranteedBitrateKbps == other.guaranteedBitrateKbps) {
                return true;
            }
            return false;
        }

        public String toString() {
            return "Bandwidth { maxBitrateKbps=" + this.maxBitrateKbps + " guaranteedBitrateKbps=" + this.guaranteedBitrateKbps + "}";
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Qos(Parcel source) {
        this.type = source.readInt();
        this.downlink = (QosBandwidth) source.readParcelable(QosBandwidth.class.getClassLoader());
        this.uplink = (QosBandwidth) source.readParcelable(QosBandwidth.class.getClassLoader());
    }

    public void writeToParcel(int type, Parcel dest, int flags) {
        dest.writeInt(type);
        dest.writeParcelable(this.downlink, flags);
        dest.writeParcelable(this.uplink, flags);
    }

    public static Qos create(android.hardware.radio.V1_6.Qos qos) {
        switch (qos.getDiscriminator()) {
            case 1:
                return new EpsQos(qos.eps());
            case 2:
                return new NrQos(qos.nr());
            default:
                return null;
        }
    }

    public int getType() {
        return this.type;
    }

    public int hashCode() {
        return Objects.hash(this.downlink, this.uplink);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        Qos other = (Qos) o;
        return this.type == other.type && this.downlink.equals(other.downlink) && this.uplink.equals(other.uplink);
    }
}
