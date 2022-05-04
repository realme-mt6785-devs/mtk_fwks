package android.telephony.data;

import android.hardware.radio.V1_6.SliceInfo;
import android.hardware.radio.V1_6.SlicingConfig;
import android.hardware.radio.V1_6.UrspRule;
import android.os.Parcel;
import android.os.Parcelable;
import android.telephony.data.NetworkSliceInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class NetworkSlicingConfig implements Parcelable {
    public static final Parcelable.Creator<NetworkSlicingConfig> CREATOR = new Parcelable.Creator<NetworkSlicingConfig>() { // from class: android.telephony.data.NetworkSlicingConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetworkSlicingConfig createFromParcel(Parcel source) {
            return new NetworkSlicingConfig(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetworkSlicingConfig[] newArray(int size) {
            return new NetworkSlicingConfig[size];
        }
    };
    private final List<NetworkSliceInfo> mSliceInfo;
    private final List<UrspRule> mUrspRules;

    public NetworkSlicingConfig() {
        this.mUrspRules = new ArrayList();
        this.mSliceInfo = new ArrayList();
    }

    public NetworkSlicingConfig(SlicingConfig sc) {
        this(sc.urspRules, sc.sliceInfo);
    }

    public NetworkSlicingConfig(List<UrspRule> urspRules, List<SliceInfo> sliceInfo) {
        this.mUrspRules = new ArrayList();
        for (UrspRule ur : urspRules) {
            this.mUrspRules.add(new UrspRule(ur.precedence, ur.trafficDescriptors, ur.routeSelectionDescriptor));
        }
        this.mSliceInfo = new ArrayList();
        for (SliceInfo si : sliceInfo) {
            this.mSliceInfo.add(sliceInfoBuilder(si));
        }
    }

    private NetworkSliceInfo sliceInfoBuilder(SliceInfo si) {
        NetworkSliceInfo.Builder builder = new NetworkSliceInfo.Builder().setSliceServiceType(si.sst).setMappedHplmnSliceServiceType(si.mappedHplmnSst);
        if (si.sliceDifferentiator != -1) {
            builder.setSliceDifferentiator(si.sliceDifferentiator).setMappedHplmnSliceDifferentiator(si.mappedHplmnSD);
        }
        return builder.build();
    }

    public NetworkSlicingConfig(Parcel p) {
        this.mUrspRules = p.createTypedArrayList(UrspRule.CREATOR);
        this.mSliceInfo = p.createTypedArrayList(NetworkSliceInfo.CREATOR);
    }

    public List<UrspRule> getUrspRules() {
        return this.mUrspRules;
    }

    public List<NetworkSliceInfo> getSliceInfo() {
        return this.mSliceInfo;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.mUrspRules, flags);
        dest.writeTypedList(this.mSliceInfo, flags);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NetworkSlicingConfig that = (NetworkSlicingConfig) o;
        if (this.mUrspRules.size() != that.mUrspRules.size() || !this.mUrspRules.containsAll(that.mUrspRules) || this.mSliceInfo.size() != that.mSliceInfo.size() || !this.mSliceInfo.containsAll(that.mSliceInfo)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(this.mUrspRules, this.mSliceInfo);
    }

    public String toString() {
        return "{.urspRules = " + this.mUrspRules + ", .sliceInfo = " + this.mSliceInfo + "}";
    }
}
