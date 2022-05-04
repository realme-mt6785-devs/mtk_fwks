package android.net;

import android.net.wifi.WifiInfo;
import android.os.Parcel;
import android.os.Parcelable;
import android.service.autofill.AutofillFieldClassificationService;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.BackupUtils;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
import com.android.net.module.util.NetworkIdentityUtils;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
/* loaded from: classes2.dex */
public class NetworkTemplate implements Parcelable {
    private static final int BACKUP_VERSION = 1;
    public static final int MATCH_BLUETOOTH = 8;
    public static final int MATCH_CARRIER = 10;
    public static final int MATCH_ETHERNET = 5;
    public static final int MATCH_MOBILE = 1;
    public static final int MATCH_MOBILE_WILDCARD = 6;
    public static final int MATCH_PROXY = 9;
    public static final int MATCH_WIFI = 4;
    public static final int MATCH_WIFI_WILDCARD = 7;
    public static final int NETWORK_TYPE_5G_NSA = -2;
    public static final int NETWORK_TYPE_ALL = -1;
    public static final int OEM_MANAGED_ALL = -1;
    public static final int OEM_MANAGED_NO = 0;
    public static final int OEM_MANAGED_YES = -2;
    public static final int SUBSCRIBER_ID_MATCH_RULE_ALL = 1;
    public static final int SUBSCRIBER_ID_MATCH_RULE_EXACT = 0;
    private static final String TAG = "NetworkTemplate";
    private final int mDefaultNetwork;
    private final int mMatchRule;
    private final String[] mMatchSubscriberIds;
    private final int mMetered;
    private final String mNetworkId;
    private final int mOemManaged;
    private final int mRoaming;
    private final int mSubType;
    private final String mSubscriberId;
    private final int mSubscriberIdMatchRule;
    public static final String WIFI_NETWORKID_ALL = null;
    private static boolean sForceAllNetworkTypes = false;
    public static final Parcelable.Creator<NetworkTemplate> CREATOR = new Parcelable.Creator<NetworkTemplate>() { // from class: android.net.NetworkTemplate.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetworkTemplate createFromParcel(Parcel in) {
            return new NetworkTemplate(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetworkTemplate[] newArray(int size) {
            return new NetworkTemplate[size];
        }
    };

    private static boolean isKnownMatchRule(int rule) {
        switch (rule) {
            case 1:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                return true;
            case 2:
            case 3:
            default:
                return false;
        }
    }

    public static void forceAllNetworkTypes() {
        sForceAllNetworkTypes = true;
    }

    public static void resetForceAllNetworkTypes() {
        sForceAllNetworkTypes = false;
    }

    public static NetworkTemplate buildTemplateMobileAll(String subscriberId) {
        return new NetworkTemplate(1, subscriberId, null);
    }

    public static NetworkTemplate buildTemplateMobileWithRatType(String subscriberId, int ratType) {
        if (TextUtils.isEmpty(subscriberId)) {
            return new NetworkTemplate(6, null, null, null, -1, -1, -1, ratType, -1, 0);
        }
        return new NetworkTemplate(1, subscriberId, new String[]{subscriberId}, null, -1, -1, -1, ratType, -1, 0);
    }

    public static NetworkTemplate buildTemplateMobileWildcard() {
        return new NetworkTemplate(6, null, null);
    }

    public static NetworkTemplate buildTemplateWifiWildcard() {
        return new NetworkTemplate(7, null, null);
    }

    @Deprecated
    public static NetworkTemplate buildTemplateWifi() {
        return buildTemplateWifiWildcard();
    }

    public static NetworkTemplate buildTemplateWifi(String networkId) {
        Objects.requireNonNull(networkId);
        return new NetworkTemplate(4, null, new String[]{null}, networkId, -1, -1, -1, -1, -1, 1);
    }

    public static NetworkTemplate buildTemplateWifi(String networkId, String subscriberId) {
        return new NetworkTemplate(4, subscriberId, new String[]{subscriberId}, networkId, -1, -1, -1, -1, -1, 0);
    }

    public static NetworkTemplate buildTemplateEthernet() {
        return new NetworkTemplate(5, null, null);
    }

    public static NetworkTemplate buildTemplateBluetooth() {
        return new NetworkTemplate(8, null, null);
    }

    public static NetworkTemplate buildTemplateProxy() {
        return new NetworkTemplate(9, null, null);
    }

    public static NetworkTemplate buildTemplateCarrierMetered(String subscriberId) {
        Objects.requireNonNull(subscriberId);
        return new NetworkTemplate(10, subscriberId, new String[]{subscriberId}, null, 1, -1, -1, -1, -1, 0);
    }

    private void checkValidSubscriberIdMatchRule() {
        switch (this.mMatchRule) {
            case 1:
            case 10:
                if (this.mSubscriberIdMatchRule == 1) {
                    throw new IllegalArgumentException("Invalid SubscriberIdMatchRuleon match rule: " + getMatchRuleName(this.mMatchRule));
                }
                return;
            default:
                return;
        }
    }

    public NetworkTemplate(int matchRule, String subscriberId, String networkId) {
        this(matchRule, subscriberId, new String[]{subscriberId}, networkId);
    }

    public NetworkTemplate(int matchRule, String subscriberId, String[] matchSubscriberIds, String networkId) {
        this(matchRule, subscriberId, matchSubscriberIds, networkId, -1, -1, -1, -1, -1, 0);
    }

    public NetworkTemplate(int matchRule, String subscriberId, String[] matchSubscriberIds, String networkId, int metered, int roaming, int defaultNetwork, int subType, int oemManaged) {
        this(matchRule, subscriberId, matchSubscriberIds, networkId, metered, roaming, defaultNetwork, subType, oemManaged, 0);
    }

    public NetworkTemplate(int matchRule, String subscriberId, String[] matchSubscriberIds, String networkId, int metered, int roaming, int defaultNetwork, int subType, int oemManaged, int subscriberIdMatchRule) {
        this.mMatchRule = matchRule;
        this.mSubscriberId = subscriberId;
        this.mMatchSubscriberIds = matchSubscriberIds;
        this.mNetworkId = networkId;
        this.mMetered = metered;
        this.mRoaming = roaming;
        this.mDefaultNetwork = defaultNetwork;
        this.mSubType = subType;
        this.mOemManaged = oemManaged;
        this.mSubscriberIdMatchRule = subscriberIdMatchRule;
        checkValidSubscriberIdMatchRule();
        if (!isKnownMatchRule(matchRule)) {
            Log.e(TAG, "Unknown network template rule " + matchRule + " will not match any identity.");
        }
    }

    private NetworkTemplate(Parcel in) {
        this.mMatchRule = in.readInt();
        this.mSubscriberId = in.readString();
        this.mMatchSubscriberIds = in.createStringArray();
        this.mNetworkId = in.readString();
        this.mMetered = in.readInt();
        this.mRoaming = in.readInt();
        this.mDefaultNetwork = in.readInt();
        this.mSubType = in.readInt();
        this.mOemManaged = in.readInt();
        this.mSubscriberIdMatchRule = in.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mMatchRule);
        dest.writeString(this.mSubscriberId);
        dest.writeStringArray(this.mMatchSubscriberIds);
        dest.writeString(this.mNetworkId);
        dest.writeInt(this.mMetered);
        dest.writeInt(this.mRoaming);
        dest.writeInt(this.mDefaultNetwork);
        dest.writeInt(this.mSubType);
        dest.writeInt(this.mOemManaged);
        dest.writeInt(this.mSubscriberIdMatchRule);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("NetworkTemplate: ");
        builder.append("matchRule=");
        builder.append(getMatchRuleName(this.mMatchRule));
        if (this.mSubscriberId != null) {
            builder.append(", subscriberId=");
            builder.append(NetworkIdentityUtils.scrubSubscriberId(this.mSubscriberId));
        }
        if (this.mMatchSubscriberIds != null) {
            builder.append(", matchSubscriberIds=");
            builder.append(Arrays.toString(NetworkIdentityUtils.scrubSubscriberIds(this.mMatchSubscriberIds)));
        }
        if (this.mNetworkId != null) {
            builder.append(", networkId=");
            builder.append(this.mNetworkId);
        }
        if (this.mMetered != -1) {
            builder.append(", metered=");
            builder.append(NetworkStats.meteredToString(this.mMetered));
        }
        if (this.mRoaming != -1) {
            builder.append(", roaming=");
            builder.append(NetworkStats.roamingToString(this.mRoaming));
        }
        if (this.mDefaultNetwork != -1) {
            builder.append(", defaultNetwork=");
            builder.append(NetworkStats.defaultNetworkToString(this.mDefaultNetwork));
        }
        if (this.mSubType != -1) {
            builder.append(", subType=");
            builder.append(this.mSubType);
        }
        if (this.mOemManaged != -1) {
            builder.append(", oemManaged=");
            builder.append(this.mOemManaged);
        }
        builder.append(", subscriberIdMatchRule=");
        builder.append(subscriberIdMatchRuleToString(this.mSubscriberIdMatchRule));
        return builder.toString();
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mMatchRule), this.mSubscriberId, this.mNetworkId, Integer.valueOf(this.mMetered), Integer.valueOf(this.mRoaming), Integer.valueOf(this.mDefaultNetwork), Integer.valueOf(this.mSubType), Integer.valueOf(this.mOemManaged), Integer.valueOf(this.mSubscriberIdMatchRule));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof NetworkTemplate)) {
            return false;
        }
        NetworkTemplate other = (NetworkTemplate) obj;
        return this.mMatchRule == other.mMatchRule && Objects.equals(this.mSubscriberId, other.mSubscriberId) && Objects.equals(this.mNetworkId, other.mNetworkId) && this.mMetered == other.mMetered && this.mRoaming == other.mRoaming && this.mDefaultNetwork == other.mDefaultNetwork && this.mSubType == other.mSubType && this.mOemManaged == other.mOemManaged && this.mSubscriberIdMatchRule == other.mSubscriberIdMatchRule;
    }

    private String subscriberIdMatchRuleToString(int rule) {
        switch (rule) {
            case 0:
                return AutofillFieldClassificationService.REQUIRED_ALGORITHM_EXACT_MATCH;
            case 1:
                return "ALL";
            default:
                return "Unknown rule " + rule;
        }
    }

    public boolean isMatchRuleMobile() {
        switch (this.mMatchRule) {
            case 1:
            case 6:
            case 10:
                return true;
            default:
                return false;
        }
    }

    public boolean isPersistable() {
        switch (this.mMatchRule) {
            case 4:
                return !Objects.equals(this.mNetworkId, WIFI_NETWORKID_ALL) || this.mSubscriberIdMatchRule != 1;
            case 5:
            case 8:
            case 9:
            default:
                return true;
            case 6:
            case 7:
                return false;
            case 10:
                return this.mSubscriberId != null;
        }
    }

    public int getMatchRule() {
        return this.mMatchRule;
    }

    public String getSubscriberId() {
        return this.mSubscriberId;
    }

    public String getNetworkId() {
        return this.mNetworkId;
    }

    public int getSubscriberIdMatchRule() {
        return this.mSubscriberIdMatchRule;
    }

    public int getMeteredness() {
        return this.mMetered;
    }

    public boolean matches(NetworkIdentity ident) {
        if (!matchesMetered(ident) || !matchesRoaming(ident) || !matchesDefaultNetwork(ident) || !matchesOemNetwork(ident)) {
            return false;
        }
        switch (this.mMatchRule) {
            case 1:
                return matchesMobile(ident);
            case 2:
            case 3:
            default:
                return false;
            case 4:
                return matchesWifi(ident);
            case 5:
                return matchesEthernet(ident);
            case 6:
                return matchesMobileWildcard(ident);
            case 7:
                return matchesWifiWildcard(ident);
            case 8:
                return matchesBluetooth(ident);
            case 9:
                return matchesProxy(ident);
            case 10:
                return matchesCarrier(ident);
        }
    }

    private boolean matchesMetered(NetworkIdentity ident) {
        int i = this.mMetered;
        if (i == -1) {
            return true;
        }
        if (i != 1 || !ident.mMetered) {
            return this.mMetered == 0 && !ident.mMetered;
        }
        return true;
    }

    private boolean matchesRoaming(NetworkIdentity ident) {
        int i = this.mRoaming;
        if (i == -1) {
            return true;
        }
        if (i != 1 || !ident.mRoaming) {
            return this.mRoaming == 0 && !ident.mRoaming;
        }
        return true;
    }

    private boolean matchesDefaultNetwork(NetworkIdentity ident) {
        int i = this.mDefaultNetwork;
        if (i == -1) {
            return true;
        }
        if (i != 1 || !ident.mDefaultNetwork) {
            return this.mDefaultNetwork == 0 && !ident.mDefaultNetwork;
        }
        return true;
    }

    private boolean matchesOemNetwork(NetworkIdentity ident) {
        int i = this.mOemManaged;
        return i == -1 || (i == -2 && ident.mOemManaged != 0) || this.mOemManaged == ident.mOemManaged;
    }

    private boolean matchesCollapsedRatType(NetworkIdentity ident) {
        int i = this.mSubType;
        return i == -1 || getCollapsedRatType(i) == getCollapsedRatType(ident.mSubType);
    }

    public boolean matchesSubscriberId(String subscriberId) {
        return this.mSubscriberIdMatchRule == 1 || ArrayUtils.contains(this.mMatchSubscriberIds, subscriberId);
    }

    private boolean matchesWifiNetworkId(String networkId) {
        return Objects.equals(this.mNetworkId, WIFI_NETWORKID_ALL) || Objects.equals(WifiInfo.sanitizeSsid(this.mNetworkId), WifiInfo.sanitizeSsid(networkId));
    }

    private boolean matchesMobile(NetworkIdentity ident) {
        if (ident.mType == 6) {
            return true;
        }
        return (sForceAllNetworkTypes || (ident.mType == 0 && ident.mMetered)) && !ArrayUtils.isEmpty(this.mMatchSubscriberIds) && ArrayUtils.contains(this.mMatchSubscriberIds, ident.mSubscriberId) && matchesCollapsedRatType(ident);
    }

    public static int getCollapsedRatType(int ratType) {
        switch (ratType) {
            case -2:
                return -2;
            case -1:
            case 0:
            case 19:
            default:
                return 0;
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                return 16;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 17:
                return 3;
            case 13:
            case 18:
                return 13;
            case 20:
                return 20;
        }
    }

    public static final int[] getAllCollapsedRatTypes() {
        int[] ratTypes = TelephonyManager.getAllNetworkTypes();
        HashSet<Integer> collapsedRatTypes = new HashSet<>();
        for (int ratType : ratTypes) {
            collapsedRatTypes.add(Integer.valueOf(getCollapsedRatType(ratType)));
        }
        collapsedRatTypes.add(Integer.valueOf(getCollapsedRatType(-2)));
        collapsedRatTypes.add(0);
        return toIntArray(collapsedRatTypes);
    }

    private static int[] toIntArray(Collection<Integer> list) {
        int[] array = new int[list.size()];
        int i = 0;
        for (Integer item : list) {
            array[i] = item.intValue();
            i++;
        }
        return array;
    }

    private boolean matchesWifi(NetworkIdentity ident) {
        switch (ident.mType) {
            case 1:
                return matchesSubscriberId(ident.mSubscriberId) && matchesWifiNetworkId(ident.mNetworkId);
            default:
                return false;
        }
    }

    private boolean matchesEthernet(NetworkIdentity ident) {
        if (ident.mType == 9) {
            return true;
        }
        return false;
    }

    private boolean matchesCarrier(NetworkIdentity ident) {
        return ident.mSubscriberId != null && !ArrayUtils.isEmpty(this.mMatchSubscriberIds) && ArrayUtils.contains(this.mMatchSubscriberIds, ident.mSubscriberId);
    }

    private boolean matchesMobileWildcard(NetworkIdentity ident) {
        if (ident.mType == 6) {
            return true;
        }
        return (sForceAllNetworkTypes || (ident.mType == 0 && ident.mMetered)) && matchesCollapsedRatType(ident);
    }

    private boolean matchesWifiWildcard(NetworkIdentity ident) {
        switch (ident.mType) {
            case 1:
            case 13:
                return true;
            default:
                return false;
        }
    }

    private boolean matchesBluetooth(NetworkIdentity ident) {
        if (ident.mType == 7) {
            return true;
        }
        return false;
    }

    private boolean matchesProxy(NetworkIdentity ident) {
        return ident.mType == 16;
    }

    private static String getMatchRuleName(int matchRule) {
        switch (matchRule) {
            case 1:
                return "MOBILE";
            case 2:
            case 3:
            default:
                return "UNKNOWN(" + matchRule + ")";
            case 4:
                return "WIFI";
            case 5:
                return "ETHERNET";
            case 6:
                return "MOBILE_WILDCARD";
            case 7:
                return "WIFI_WILDCARD";
            case 8:
                return "BLUETOOTH";
            case 9:
                return "PROXY";
            case 10:
                return "CARRIER";
        }
    }

    public static NetworkTemplate normalize(NetworkTemplate template, String[] merged) {
        return normalize(template, Arrays.asList(merged));
    }

    public static NetworkTemplate normalize(NetworkTemplate template, List<String[]> mergedList) {
        if (!template.isMatchRuleMobile()) {
            return template;
        }
        for (String[] merged : mergedList) {
            if (ArrayUtils.contains(merged, template.mSubscriberId)) {
                return new NetworkTemplate(template.mMatchRule, merged[0], merged, template.mNetworkId);
            }
        }
        return template;
    }

    public byte[] getBytesForBackup() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);
        out.writeInt(1);
        out.writeInt(this.mMatchRule);
        BackupUtils.writeString(out, this.mSubscriberId);
        BackupUtils.writeString(out, this.mNetworkId);
        return baos.toByteArray();
    }

    public static NetworkTemplate getNetworkTemplateFromBackup(DataInputStream in) throws IOException, BackupUtils.BadVersionException {
        int version = in.readInt();
        if (version < 1 || version > 1) {
            throw new BackupUtils.BadVersionException("Unknown Backup Serialization Version");
        }
        int matchRule = in.readInt();
        String subscriberId = BackupUtils.readString(in);
        String networkId = BackupUtils.readString(in);
        if (isKnownMatchRule(matchRule)) {
            return new NetworkTemplate(matchRule, subscriberId, networkId);
        }
        throw new BackupUtils.BadVersionException("Restored network template contains unknown match rule " + matchRule);
    }
}
