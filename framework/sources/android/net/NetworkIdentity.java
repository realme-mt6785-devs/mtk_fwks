package android.net;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.proto.ProtoOutputStream;
import com.android.net.module.util.NetworkIdentityUtils;
import java.util.Objects;
/* loaded from: classes2.dex */
public class NetworkIdentity implements Comparable<NetworkIdentity> {
    public static final int OEM_NONE = 0;
    public static final int OEM_PAID = 1;
    public static final int OEM_PRIVATE = 2;
    public static final int SUBTYPE_COMBINED = -1;
    private static final String TAG = "NetworkIdentity";
    final boolean mDefaultNetwork;
    final boolean mMetered;
    final String mNetworkId;
    final int mOemManaged;
    final boolean mRoaming;
    final int mSubType;
    final String mSubscriberId;
    final int mType;

    public NetworkIdentity(int type, int subType, String subscriberId, String networkId, boolean roaming, boolean metered, boolean defaultNetwork, int oemManaged) {
        this.mType = type;
        this.mSubType = subType;
        this.mSubscriberId = subscriberId;
        this.mNetworkId = networkId;
        this.mRoaming = roaming;
        this.mMetered = metered;
        this.mDefaultNetwork = defaultNetwork;
        this.mOemManaged = oemManaged;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mType), Integer.valueOf(this.mSubType), this.mSubscriberId, this.mNetworkId, Boolean.valueOf(this.mRoaming), Boolean.valueOf(this.mMetered), Boolean.valueOf(this.mDefaultNetwork), Integer.valueOf(this.mOemManaged));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof NetworkIdentity)) {
            return false;
        }
        NetworkIdentity ident = (NetworkIdentity) obj;
        return this.mType == ident.mType && this.mSubType == ident.mSubType && this.mRoaming == ident.mRoaming && Objects.equals(this.mSubscriberId, ident.mSubscriberId) && Objects.equals(this.mNetworkId, ident.mNetworkId) && this.mMetered == ident.mMetered && this.mDefaultNetwork == ident.mDefaultNetwork && this.mOemManaged == ident.mOemManaged;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        builder.append("type=");
        builder.append(this.mType);
        builder.append(", subType=");
        int i = this.mSubType;
        if (i == -1) {
            builder.append("COMBINED");
        } else {
            builder.append(i);
        }
        if (this.mSubscriberId != null) {
            builder.append(", subscriberId=");
            builder.append(NetworkIdentityUtils.scrubSubscriberId(this.mSubscriberId));
        }
        if (this.mNetworkId != null) {
            builder.append(", networkId=");
            builder.append(this.mNetworkId);
        }
        if (this.mRoaming) {
            builder.append(", ROAMING");
        }
        builder.append(", metered=");
        builder.append(this.mMetered);
        builder.append(", defaultNetwork=");
        builder.append(this.mDefaultNetwork);
        builder.append(", oemManaged=");
        builder.append(this.mOemManaged);
        builder.append("}");
        return builder.toString();
    }

    public void dumpDebug(ProtoOutputStream proto, long tag) {
        long start = proto.start(tag);
        proto.write(1120986464257L, this.mType);
        String str = this.mSubscriberId;
        if (str != null) {
            proto.write(1138166333442L, NetworkIdentityUtils.scrubSubscriberId(str));
        }
        proto.write(1138166333443L, this.mNetworkId);
        proto.write(1133871366148L, this.mRoaming);
        proto.write(1133871366149L, this.mMetered);
        proto.write(1133871366150L, this.mDefaultNetwork);
        proto.write(1120986464263L, this.mOemManaged);
        proto.end(start);
    }

    public int getType() {
        return this.mType;
    }

    public int getSubType() {
        return this.mSubType;
    }

    public String getSubscriberId() {
        return this.mSubscriberId;
    }

    public String getNetworkId() {
        return this.mNetworkId;
    }

    public boolean getRoaming() {
        return this.mRoaming;
    }

    public boolean getMetered() {
        return this.mMetered;
    }

    public boolean getDefaultNetwork() {
        return this.mDefaultNetwork;
    }

    public int getOemManaged() {
        return this.mOemManaged;
    }

    public static NetworkIdentity buildNetworkIdentity(Context context, NetworkStateSnapshot snapshot, boolean defaultNetwork, int subType) {
        String networkId;
        int legacyType = snapshot.getLegacyType();
        String subscriberId = snapshot.getSubscriberId();
        boolean roaming = !snapshot.getNetworkCapabilities().hasCapability(18);
        boolean metered = !snapshot.getNetworkCapabilities().hasCapability(11);
        int oemManaged = getOemBitfield(snapshot.getNetworkCapabilities());
        if (legacyType == 1) {
            String networkId2 = snapshot.getNetworkCapabilities().getSsid();
            if (networkId2 == null) {
                WifiManager wifi = (WifiManager) context.getSystemService(WifiManager.class);
                WifiInfo info = wifi.getConnectionInfo();
                String networkId3 = info != null ? info.getSSID() : null;
                networkId = networkId3;
            } else {
                networkId = networkId2;
            }
        } else {
            networkId = null;
        }
        return new NetworkIdentity(legacyType, subType, subscriberId, networkId, roaming, metered, defaultNetwork, oemManaged);
    }

    public static int getOemBitfield(NetworkCapabilities nc) {
        int oemManaged = 0;
        if (nc.hasCapability(22)) {
            oemManaged = 0 | 1;
        }
        if (nc.hasCapability(26)) {
            return oemManaged | 2;
        }
        return oemManaged;
    }

    public int compareTo(NetworkIdentity another) {
        String str;
        String str2;
        String str3;
        String str4;
        int res = Integer.compare(this.mType, another.mType);
        if (res == 0) {
            res = Integer.compare(this.mSubType, another.mSubType);
        }
        if (!(res != 0 || (str3 = this.mSubscriberId) == null || (str4 = another.mSubscriberId) == null)) {
            res = str3.compareTo(str4);
        }
        if (!(res != 0 || (str = this.mNetworkId) == null || (str2 = another.mNetworkId) == null)) {
            res = str.compareTo(str2);
        }
        if (res == 0) {
            res = Boolean.compare(this.mRoaming, another.mRoaming);
        }
        if (res == 0) {
            res = Boolean.compare(this.mMetered, another.mMetered);
        }
        if (res == 0) {
            res = Boolean.compare(this.mDefaultNetwork, another.mDefaultNetwork);
        }
        if (res == 0) {
            return Integer.compare(this.mOemManaged, another.mOemManaged);
        }
        return res;
    }
}
