package android.net;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateFormat;
import com.android.net.module.util.NetworkIdentityUtils;
import java.util.Objects;
@SystemApi(client = SystemApi.Client.MODULE_LIBRARIES)
/* loaded from: classes2.dex */
public final class NetworkStateSnapshot implements Parcelable {
    public static final Parcelable.Creator<NetworkStateSnapshot> CREATOR = new Parcelable.Creator<NetworkStateSnapshot>() { // from class: android.net.NetworkStateSnapshot.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetworkStateSnapshot createFromParcel(Parcel in) {
            return new NetworkStateSnapshot(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetworkStateSnapshot[] newArray(int size) {
            return new NetworkStateSnapshot[size];
        }
    };
    private final int mLegacyType;
    private final LinkProperties mLinkProperties;
    private final Network mNetwork;
    private final NetworkCapabilities mNetworkCapabilities;
    private final String mSubscriberId;

    public NetworkStateSnapshot(Network network, NetworkCapabilities networkCapabilities, LinkProperties linkProperties, String subscriberId, int legacyType) {
        Objects.requireNonNull(network);
        this.mNetwork = network;
        Objects.requireNonNull(networkCapabilities);
        this.mNetworkCapabilities = networkCapabilities;
        Objects.requireNonNull(linkProperties);
        this.mLinkProperties = linkProperties;
        this.mSubscriberId = subscriberId;
        this.mLegacyType = legacyType;
    }

    public NetworkStateSnapshot(Parcel in) {
        this.mNetwork = (Network) in.readParcelable(null);
        this.mNetworkCapabilities = (NetworkCapabilities) in.readParcelable(null);
        this.mLinkProperties = (LinkProperties) in.readParcelable(null);
        this.mSubscriberId = in.readString();
        this.mLegacyType = in.readInt();
    }

    public Network getNetwork() {
        return this.mNetwork;
    }

    public NetworkCapabilities getNetworkCapabilities() {
        return this.mNetworkCapabilities;
    }

    public LinkProperties getLinkProperties() {
        return this.mLinkProperties;
    }

    public String getSubscriberId() {
        return this.mSubscriberId;
    }

    public int getLegacyType() {
        return this.mLegacyType;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(this.mNetwork, flags);
        out.writeParcelable(this.mNetworkCapabilities, flags);
        out.writeParcelable(this.mLinkProperties, flags);
        out.writeString(this.mSubscriberId);
        out.writeInt(this.mLegacyType);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NetworkStateSnapshot)) {
            return false;
        }
        NetworkStateSnapshot that = (NetworkStateSnapshot) o;
        return this.mLegacyType == that.mLegacyType && Objects.equals(this.mNetwork, that.mNetwork) && Objects.equals(this.mNetworkCapabilities, that.mNetworkCapabilities) && Objects.equals(this.mLinkProperties, that.mLinkProperties) && Objects.equals(this.mSubscriberId, that.mSubscriberId);
    }

    public int hashCode() {
        return Objects.hash(this.mNetwork, this.mNetworkCapabilities, this.mLinkProperties, this.mSubscriberId, Integer.valueOf(this.mLegacyType));
    }

    public String toString() {
        return "NetworkStateSnapshot{network=" + this.mNetwork + ", networkCapabilities=" + this.mNetworkCapabilities + ", linkProperties=" + this.mLinkProperties + ", subscriberId='" + NetworkIdentityUtils.scrubSubscriberId(this.mSubscriberId) + DateFormat.QUOTE + ", legacyType=" + this.mLegacyType + '}';
    }
}
