package android.net.wifi.nl80211;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.Objects;
@SystemApi
/* loaded from: classes2.dex */
public final class DeviceWiphyCapabilities implements Parcelable {
    public static final Parcelable.Creator<DeviceWiphyCapabilities> CREATOR = new Parcelable.Creator<DeviceWiphyCapabilities>() { // from class: android.net.wifi.nl80211.DeviceWiphyCapabilities.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DeviceWiphyCapabilities createFromParcel(Parcel in) {
            DeviceWiphyCapabilities capabilities = new DeviceWiphyCapabilities();
            capabilities.m80211nSupported = in.readBoolean();
            capabilities.m80211acSupported = in.readBoolean();
            capabilities.m80211axSupported = in.readBoolean();
            capabilities.mChannelWidth160MhzSupported = in.readBoolean();
            capabilities.mChannelWidth80p80MhzSupported = in.readBoolean();
            capabilities.mMaxNumberTxSpatialStreams = in.readInt();
            capabilities.mMaxNumberRxSpatialStreams = in.readInt();
            return capabilities;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DeviceWiphyCapabilities[] newArray(int size) {
            return new DeviceWiphyCapabilities[size];
        }
    };
    private static final String TAG = "DeviceWiphyCapabilities";
    private boolean m80211nSupported = false;
    private boolean m80211acSupported = false;
    private boolean m80211axSupported = false;
    private boolean mChannelWidth160MhzSupported = false;
    private boolean mChannelWidth80p80MhzSupported = false;
    private int mMaxNumberTxSpatialStreams = 1;
    private int mMaxNumberRxSpatialStreams = 1;

    public boolean isWifiStandardSupported(int standard) {
        switch (standard) {
            case 1:
                return true;
            case 2:
            case 3:
            default:
                Log.e(TAG, "isWifiStandardSupported called with invalid standard: " + standard);
                return false;
            case 4:
                return this.m80211nSupported;
            case 5:
                return this.m80211acSupported;
            case 6:
                return this.m80211axSupported;
        }
    }

    public void setWifiStandardSupport(int standard, boolean support) {
        switch (standard) {
            case 4:
                this.m80211nSupported = support;
                return;
            case 5:
                this.m80211acSupported = support;
                return;
            case 6:
                this.m80211axSupported = support;
                return;
            default:
                Log.e(TAG, "setWifiStandardSupport called with invalid standard: " + standard);
                return;
        }
    }

    public boolean isChannelWidthSupported(int chWidth) {
        switch (chWidth) {
            case 0:
                return true;
            case 1:
                return this.m80211nSupported || this.m80211acSupported || this.m80211axSupported;
            case 2:
                return this.m80211acSupported || this.m80211axSupported;
            case 3:
                return this.mChannelWidth160MhzSupported;
            case 4:
                return this.mChannelWidth80p80MhzSupported;
            default:
                Log.e(TAG, "isChannelWidthSupported called with invalid channel width: " + chWidth);
                return false;
        }
    }

    public void setChannelWidthSupported(int chWidth, boolean support) {
        switch (chWidth) {
            case 3:
                this.mChannelWidth160MhzSupported = support;
                return;
            case 4:
                this.mChannelWidth80p80MhzSupported = support;
                return;
            default:
                Log.e(TAG, "setChannelWidthSupported called with Invalid channel width: " + chWidth);
                return;
        }
    }

    public int getMaxNumberTxSpatialStreams() {
        return this.mMaxNumberTxSpatialStreams;
    }

    public void setMaxNumberTxSpatialStreams(int streams) {
        this.mMaxNumberTxSpatialStreams = streams;
    }

    public int getMaxNumberRxSpatialStreams() {
        return this.mMaxNumberRxSpatialStreams;
    }

    public void setMaxNumberRxSpatialStreams(int streams) {
        this.mMaxNumberRxSpatialStreams = streams;
    }

    public boolean equals(Object rhs) {
        if (this == rhs) {
            return true;
        }
        if (!(rhs instanceof DeviceWiphyCapabilities)) {
            return false;
        }
        DeviceWiphyCapabilities capa = (DeviceWiphyCapabilities) rhs;
        return this.m80211nSupported == capa.m80211nSupported && this.m80211acSupported == capa.m80211acSupported && this.m80211axSupported == capa.m80211axSupported && this.mChannelWidth160MhzSupported == capa.mChannelWidth160MhzSupported && this.mChannelWidth80p80MhzSupported == capa.mChannelWidth80p80MhzSupported && this.mMaxNumberTxSpatialStreams == capa.mMaxNumberTxSpatialStreams && this.mMaxNumberRxSpatialStreams == capa.mMaxNumberRxSpatialStreams;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.m80211nSupported), Boolean.valueOf(this.m80211acSupported), Boolean.valueOf(this.m80211axSupported), Boolean.valueOf(this.mChannelWidth160MhzSupported), Boolean.valueOf(this.mChannelWidth80p80MhzSupported), Integer.valueOf(this.mMaxNumberTxSpatialStreams), Integer.valueOf(this.mMaxNumberRxSpatialStreams));
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeBoolean(this.m80211nSupported);
        out.writeBoolean(this.m80211acSupported);
        out.writeBoolean(this.m80211axSupported);
        out.writeBoolean(this.mChannelWidth160MhzSupported);
        out.writeBoolean(this.mChannelWidth80p80MhzSupported);
        out.writeInt(this.mMaxNumberTxSpatialStreams);
        out.writeInt(this.mMaxNumberRxSpatialStreams);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("m80211nSupported:");
        String str = "Yes";
        sb.append(this.m80211nSupported ? str : "No");
        sb.append("m80211acSupported:");
        sb.append(this.m80211acSupported ? str : "No");
        sb.append("m80211axSupported:");
        sb.append(this.m80211axSupported ? str : "No");
        sb.append("mChannelWidth160MhzSupported: ");
        sb.append(this.mChannelWidth160MhzSupported ? str : "No");
        sb.append("mChannelWidth80p80MhzSupported: ");
        if (!this.mChannelWidth80p80MhzSupported) {
            str = "No";
        }
        sb.append(str);
        sb.append("mMaxNumberTxSpatialStreams: ");
        sb.append(this.mMaxNumberTxSpatialStreams);
        sb.append("mMaxNumberRxSpatialStreams: ");
        sb.append(this.mMaxNumberRxSpatialStreams);
        return sb.toString();
    }
}
