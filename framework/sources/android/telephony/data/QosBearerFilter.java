package android.telephony.data;

import android.hardware.radio.V1_6.QosFilter;
import android.net.InetAddresses;
import android.net.LinkAddress;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class QosBearerFilter implements Parcelable {
    public static final Parcelable.Creator<QosBearerFilter> CREATOR = new Parcelable.Creator<QosBearerFilter>() { // from class: android.telephony.data.QosBearerFilter.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public QosBearerFilter createFromParcel(Parcel source) {
            return new QosBearerFilter(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public QosBearerFilter[] newArray(int size) {
            return new QosBearerFilter[size];
        }
    };
    public static final int QOS_FILTER_DIRECTION_BIDIRECTIONAL = 2;
    public static final int QOS_FILTER_DIRECTION_DOWNLINK = 0;
    public static final int QOS_FILTER_DIRECTION_UPLINK = 1;
    public static final int QOS_MAX_PORT = 65535;
    public static final int QOS_MIN_PORT = 20;
    public static final int QOS_PROTOCOL_AH = 51;
    public static final int QOS_PROTOCOL_ESP = 50;
    public static final int QOS_PROTOCOL_TCP = 6;
    public static final int QOS_PROTOCOL_UDP = 17;
    public static final int QOS_PROTOCOL_UNSPECIFIED = -1;
    private int filterDirection;
    private long flowLabel;
    private List<LinkAddress> localAddresses;
    private PortRange localPort;
    private int precedence;
    private int protocol;
    private List<LinkAddress> remoteAddresses;
    private PortRange remotePort;
    private long securityParameterIndex;
    private int typeOfServiceMask;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface QosBearerFilterDirection {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface QosProtocol {
    }

    QosBearerFilter() {
        this.localAddresses = new ArrayList();
        this.remoteAddresses = new ArrayList();
        this.localPort = new PortRange();
        this.remotePort = new PortRange();
        this.protocol = -1;
        this.filterDirection = 2;
    }

    public QosBearerFilter(List<LinkAddress> localAddresses, List<LinkAddress> remoteAddresses, PortRange localPort, PortRange remotePort, int protocol, int tos, long flowLabel, long spi, int direction, int precedence) {
        this.localAddresses = localAddresses;
        this.remoteAddresses = remoteAddresses;
        this.localPort = localPort;
        this.remotePort = remotePort;
        this.protocol = protocol;
        this.typeOfServiceMask = tos;
        this.flowLabel = flowLabel;
        this.securityParameterIndex = spi;
        this.filterDirection = direction;
        this.precedence = precedence;
    }

    public List<LinkAddress> getLocalAddresses() {
        return this.localAddresses;
    }

    public List<LinkAddress> getRemoteAddresses() {
        return this.remoteAddresses;
    }

    public PortRange getLocalPortRange() {
        return this.localPort;
    }

    public PortRange getRemotePortRange() {
        return this.remotePort;
    }

    public int getPrecedence() {
        return this.precedence;
    }

    public static QosBearerFilter create(QosFilter qosFilter) {
        QosBearerFilter ret = new QosBearerFilter();
        String[] localAddresses = (String[]) qosFilter.localAddresses.stream().toArray(QosBearerFilter$$ExternalSyntheticLambda0.INSTANCE);
        if (localAddresses != null) {
            for (String address : localAddresses) {
                ret.localAddresses.add(createLinkAddressFromString(address));
            }
        }
        String[] remoteAddresses = (String[]) qosFilter.remoteAddresses.stream().toArray(QosBearerFilter$$ExternalSyntheticLambda1.INSTANCE);
        if (remoteAddresses != null) {
            for (String address2 : remoteAddresses) {
                ret.remoteAddresses.add(createLinkAddressFromString(address2));
            }
        }
        if (qosFilter.localPort != null && qosFilter.localPort.getDiscriminator() == 1) {
            android.hardware.radio.V1_6.PortRange portRange = qosFilter.localPort.range();
            ret.localPort.start = portRange.start;
            ret.localPort.end = portRange.end;
        }
        if (qosFilter.remotePort != null && qosFilter.remotePort.getDiscriminator() == 1) {
            android.hardware.radio.V1_6.PortRange portRange2 = qosFilter.remotePort.range();
            ret.remotePort.start = portRange2.start;
            ret.remotePort.end = portRange2.end;
        }
        ret.protocol = qosFilter.protocol;
        if (qosFilter.tos != null && qosFilter.tos.getDiscriminator() == 1) {
            ret.typeOfServiceMask = qosFilter.tos.value();
        }
        if (qosFilter.flowLabel != null && qosFilter.flowLabel.getDiscriminator() == 1) {
            ret.flowLabel = qosFilter.flowLabel.value();
        }
        if (qosFilter.spi != null && qosFilter.spi.getDiscriminator() == 1) {
            ret.securityParameterIndex = qosFilter.spi.value();
        }
        ret.filterDirection = qosFilter.direction;
        ret.precedence = qosFilter.precedence;
        return ret;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$create$0(int x$0) {
        return new String[x$0];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$create$1(int x$0) {
        return new String[x$0];
    }

    /* loaded from: classes3.dex */
    public static class PortRange implements Parcelable {
        public static final Parcelable.Creator<PortRange> CREATOR = new Parcelable.Creator<PortRange>() { // from class: android.telephony.data.QosBearerFilter.PortRange.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public PortRange createFromParcel(Parcel source) {
                return new PortRange(source);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public PortRange[] newArray(int size) {
                return new PortRange[size];
            }
        };
        int end;
        int start;

        PortRange() {
            this.start = -1;
            this.end = -1;
        }

        private PortRange(Parcel source) {
            this.start = source.readInt();
            this.end = source.readInt();
        }

        public PortRange(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return this.start;
        }

        public int getEnd() {
            return this.end;
        }

        public boolean isValid() {
            int i;
            int i2 = this.start;
            return i2 >= 20 && i2 <= 65535 && (i = this.end) >= 20 && i <= 65535 && i2 <= i;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.start);
            dest.writeInt(this.end);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "PortRange { start=" + this.start + " end=" + this.end + "}";
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || !(o instanceof PortRange)) {
                return false;
            }
            PortRange other = (PortRange) o;
            if (this.start == other.start && this.end == other.end) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.start), Integer.valueOf(this.end));
        }
    }

    public String toString() {
        return "QosBearerFilter { localAddresses=" + this.localAddresses + " remoteAddresses=" + this.remoteAddresses + " localPort=" + this.localPort + " remotePort=" + this.remotePort + " protocol=" + this.protocol + " typeOfServiceMask=" + this.typeOfServiceMask + " flowLabel=" + this.flowLabel + " securityParameterIndex=" + this.securityParameterIndex + " filterDirection=" + this.filterDirection + " precedence=" + this.precedence + "}";
    }

    public int hashCode() {
        return Objects.hash(this.localAddresses, this.remoteAddresses, this.localPort, this.remotePort, Integer.valueOf(this.protocol), Integer.valueOf(this.typeOfServiceMask), Long.valueOf(this.flowLabel), Long.valueOf(this.securityParameterIndex), Integer.valueOf(this.filterDirection), Integer.valueOf(this.precedence));
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof QosBearerFilter)) {
            return false;
        }
        QosBearerFilter other = (QosBearerFilter) o;
        if (this.localAddresses.size() == other.localAddresses.size() && this.localAddresses.containsAll(other.localAddresses) && this.remoteAddresses.size() == other.remoteAddresses.size() && this.remoteAddresses.containsAll(other.remoteAddresses) && this.localPort.equals(other.localPort) && this.remotePort.equals(other.remotePort) && this.protocol == other.protocol && this.typeOfServiceMask == other.typeOfServiceMask && this.flowLabel == other.flowLabel && this.securityParameterIndex == other.securityParameterIndex && this.filterDirection == other.filterDirection && this.precedence == other.precedence) {
            return true;
        }
        return false;
    }

    private static LinkAddress createLinkAddressFromString(String addressString) {
        String addressString2 = addressString.trim();
        InetAddress address = null;
        int prefixLength = -1;
        try {
            String[] pieces = addressString2.split("/", 2);
            address = InetAddresses.parseNumericAddress(pieces[0]);
            if (pieces.length == 1) {
                prefixLength = address instanceof Inet4Address ? 32 : 128;
            } else if (pieces.length == 2) {
                prefixLength = Integer.parseInt(pieces[1]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (NumberFormatException e2) {
        } catch (IllegalArgumentException e3) {
        } catch (NullPointerException e4) {
        }
        if (address != null && prefixLength != -1) {
            return new LinkAddress(address, prefixLength, 0, 0, -1L, -1L);
        }
        throw new IllegalArgumentException("Invalid link address " + addressString2);
    }

    private QosBearerFilter(Parcel source) {
        ArrayList arrayList = new ArrayList();
        this.localAddresses = arrayList;
        source.readList(arrayList, LinkAddress.class.getClassLoader());
        ArrayList arrayList2 = new ArrayList();
        this.remoteAddresses = arrayList2;
        source.readList(arrayList2, LinkAddress.class.getClassLoader());
        this.localPort = (PortRange) source.readParcelable(PortRange.class.getClassLoader());
        this.remotePort = (PortRange) source.readParcelable(PortRange.class.getClassLoader());
        this.protocol = source.readInt();
        this.typeOfServiceMask = source.readInt();
        this.flowLabel = source.readLong();
        this.securityParameterIndex = source.readLong();
        this.filterDirection = source.readInt();
        this.precedence = source.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.localAddresses);
        dest.writeList(this.remoteAddresses);
        dest.writeParcelable(this.localPort, flags);
        dest.writeParcelable(this.remotePort, flags);
        dest.writeInt(this.protocol);
        dest.writeInt(this.typeOfServiceMask);
        dest.writeLong(this.flowLabel);
        dest.writeLong(this.securityParameterIndex);
        dest.writeInt(this.filterDirection);
        dest.writeInt(this.precedence);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}
