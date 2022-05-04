package com.android.internal.net;

import android.net.Ikev2VpnProfile;
import android.net.ProxyInfo;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.net.module.util.ProxyUtils;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
/* loaded from: classes4.dex */
public final class VpnProfile implements Cloneable, Parcelable {
    private static final String ENCODED_NULL_PROXY_INFO = "\u0000\u0000\u0000\u0000";
    static final String LIST_DELIMITER = ",";
    public static final int PROXY_MANUAL = 1;
    public static final int PROXY_NONE = 0;
    private static final String TAG = "VpnProfile";
    public static final int TYPE_IKEV2_IPSEC_PSK = 7;
    public static final int TYPE_IKEV2_IPSEC_RSA = 8;
    public static final int TYPE_IKEV2_IPSEC_USER_PASS = 6;
    public static final int TYPE_IPSEC_HYBRID_RSA = 5;
    public static final int TYPE_IPSEC_XAUTH_PSK = 3;
    public static final int TYPE_IPSEC_XAUTH_RSA = 4;
    public static final int TYPE_L2TP_IPSEC_PSK = 1;
    public static final int TYPE_L2TP_IPSEC_RSA = 2;
    public static final int TYPE_MAX = 8;
    public static final int TYPE_PPTP = 0;
    static final String VALUE_DELIMITER = "\u0000";
    public boolean areAuthParamsInline;
    public String dnsServers;
    public String ipsecCaCert;
    public String ipsecIdentifier;
    public String ipsecSecret;
    public String ipsecServerCert;
    public String ipsecUserCert;
    public boolean isBypassable;
    public boolean isMetered;
    public final boolean isRestrictedToTestNetworks;
    public final String key;
    public String l2tpSecret;
    private List<String> mAllowedAlgorithms;
    public int maxMtu;
    public boolean mppe;
    public String name;
    public String password;
    public ProxyInfo proxy;
    public String routes;
    public transient boolean saveLogin;
    public String searchDomains;
    public String server;
    public int type;
    public String username;
    private static final String DEFAULT_ENCODING = StandardCharsets.UTF_8.name();
    public static final Parcelable.Creator<VpnProfile> CREATOR = new Parcelable.Creator<VpnProfile>() { // from class: com.android.internal.net.VpnProfile.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VpnProfile createFromParcel(Parcel in) {
            return new VpnProfile(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VpnProfile[] newArray(int size) {
            return new VpnProfile[size];
        }
    };

    public VpnProfile(String key) {
        this(key, false);
    }

    public VpnProfile(String key, boolean isRestrictedToTestNetworks) {
        this.name = "";
        this.type = 0;
        this.server = "";
        this.username = "";
        this.password = "";
        this.dnsServers = "";
        this.searchDomains = "";
        this.routes = "";
        this.mppe = true;
        this.l2tpSecret = "";
        this.ipsecIdentifier = "";
        this.ipsecSecret = "";
        this.ipsecUserCert = "";
        this.ipsecCaCert = "";
        this.ipsecServerCert = "";
        this.proxy = null;
        this.mAllowedAlgorithms = new ArrayList();
        this.isBypassable = false;
        this.isMetered = false;
        this.maxMtu = 1360;
        this.areAuthParamsInline = false;
        this.saveLogin = false;
        this.key = key;
        this.isRestrictedToTestNetworks = isRestrictedToTestNetworks;
    }

    public VpnProfile(Parcel in) {
        boolean z;
        this.name = "";
        boolean z2 = false;
        this.type = 0;
        this.server = "";
        this.username = "";
        this.password = "";
        this.dnsServers = "";
        this.searchDomains = "";
        this.routes = "";
        this.mppe = true;
        this.l2tpSecret = "";
        this.ipsecIdentifier = "";
        this.ipsecSecret = "";
        this.ipsecUserCert = "";
        this.ipsecCaCert = "";
        this.ipsecServerCert = "";
        this.proxy = null;
        this.mAllowedAlgorithms = new ArrayList();
        this.isBypassable = false;
        this.isMetered = false;
        this.maxMtu = 1360;
        this.areAuthParamsInline = false;
        this.saveLogin = false;
        this.key = in.readString();
        this.name = in.readString();
        this.type = in.readInt();
        this.server = in.readString();
        this.username = in.readString();
        this.password = in.readString();
        this.dnsServers = in.readString();
        this.searchDomains = in.readString();
        this.routes = in.readString();
        if (in.readInt() != 0) {
            z = true;
        } else {
            z = false;
        }
        this.mppe = z;
        this.l2tpSecret = in.readString();
        this.ipsecIdentifier = in.readString();
        this.ipsecSecret = in.readString();
        this.ipsecUserCert = in.readString();
        this.ipsecCaCert = in.readString();
        this.ipsecServerCert = in.readString();
        this.saveLogin = in.readInt() != 0 ? true : z2;
        this.proxy = (ProxyInfo) in.readParcelable(null);
        ArrayList arrayList = new ArrayList();
        this.mAllowedAlgorithms = arrayList;
        in.readList(arrayList, null);
        this.isBypassable = in.readBoolean();
        this.isMetered = in.readBoolean();
        this.maxMtu = in.readInt();
        this.areAuthParamsInline = in.readBoolean();
        this.isRestrictedToTestNetworks = in.readBoolean();
    }

    public List<String> getAllowedAlgorithms() {
        return Collections.unmodifiableList(this.mAllowedAlgorithms);
    }

    public void setAllowedAlgorithms(List<String> allowedAlgorithms) {
        this.mAllowedAlgorithms = allowedAlgorithms;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.key);
        out.writeString(this.name);
        out.writeInt(this.type);
        out.writeString(this.server);
        out.writeString(this.username);
        out.writeString(this.password);
        out.writeString(this.dnsServers);
        out.writeString(this.searchDomains);
        out.writeString(this.routes);
        out.writeInt(this.mppe ? 1 : 0);
        out.writeString(this.l2tpSecret);
        out.writeString(this.ipsecIdentifier);
        out.writeString(this.ipsecSecret);
        out.writeString(this.ipsecUserCert);
        out.writeString(this.ipsecCaCert);
        out.writeString(this.ipsecServerCert);
        out.writeInt(this.saveLogin ? 1 : 0);
        out.writeParcelable(this.proxy, flags);
        out.writeList(this.mAllowedAlgorithms);
        out.writeBoolean(this.isBypassable);
        out.writeBoolean(this.isMetered);
        out.writeInt(this.maxMtu);
        out.writeBoolean(this.areAuthParamsInline);
        out.writeBoolean(this.isRestrictedToTestNetworks);
    }

    public static VpnProfile decode(String key, byte[] value) {
        boolean isRestrictedToTestNetworks;
        if (key == null) {
            return null;
        }
        try {
            String[] values = new String(value, StandardCharsets.UTF_8).split(VALUE_DELIMITER, -1);
            if (!((values.length >= 14 && values.length <= 19) || values.length == 24 || values.length == 25)) {
                return null;
            }
            if (values.length >= 25) {
                isRestrictedToTestNetworks = Boolean.parseBoolean(values[24]);
            } else {
                isRestrictedToTestNetworks = false;
            }
            VpnProfile profile = new VpnProfile(key, isRestrictedToTestNetworks);
            boolean z = false;
            profile.name = values[0];
            int parseInt = Integer.parseInt(values[1]);
            profile.type = parseInt;
            if (parseInt >= 0 && parseInt <= 8) {
                profile.server = values[2];
                profile.username = values[3];
                profile.password = values[4];
                profile.dnsServers = values[5];
                profile.searchDomains = values[6];
                profile.routes = values[7];
                profile.mppe = Boolean.parseBoolean(values[8]);
                profile.l2tpSecret = values[9];
                profile.ipsecIdentifier = values[10];
                profile.ipsecSecret = values[11];
                profile.ipsecUserCert = values[12];
                profile.ipsecCaCert = values[13];
                String pacFileUrl = "";
                profile.ipsecServerCert = values.length > 14 ? values[14] : pacFileUrl;
                if (values.length > 15) {
                    String host = values.length > 15 ? values[15] : pacFileUrl;
                    String port = values.length > 16 ? values[16] : pacFileUrl;
                    String exclList = values.length > 17 ? values[17] : pacFileUrl;
                    if (values.length > 18) {
                        pacFileUrl = values[18];
                    }
                    if (host.isEmpty() && port.isEmpty() && exclList.isEmpty()) {
                        if (!pacFileUrl.isEmpty()) {
                            profile.proxy = ProxyInfo.buildPacProxy(Uri.parse(pacFileUrl));
                        }
                    }
                    profile.proxy = ProxyInfo.buildDirectProxy(host, port.isEmpty() ? 0 : Integer.parseInt(port), ProxyUtils.exclusionStringAsList(exclList));
                }
                if (values.length >= 24) {
                    profile.mAllowedAlgorithms = new ArrayList();
                    for (String algo : Arrays.asList(values[19].split(","))) {
                        profile.mAllowedAlgorithms.add(URLDecoder.decode(algo, DEFAULT_ENCODING));
                    }
                    profile.isBypassable = Boolean.parseBoolean(values[20]);
                    profile.isMetered = Boolean.parseBoolean(values[21]);
                    profile.maxMtu = Integer.parseInt(values[22]);
                    profile.areAuthParamsInline = Boolean.parseBoolean(values[23]);
                }
                if (!profile.username.isEmpty() || !profile.password.isEmpty()) {
                    z = true;
                }
                profile.saveLogin = z;
                return profile;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public byte[] encode() {
        StringBuilder builder = new StringBuilder(this.name);
        builder.append(VALUE_DELIMITER);
        builder.append(this.type);
        builder.append(VALUE_DELIMITER);
        builder.append(this.server);
        builder.append(VALUE_DELIMITER);
        String str = "";
        builder.append(this.saveLogin ? this.username : str);
        builder.append(VALUE_DELIMITER);
        builder.append(this.saveLogin ? this.password : str);
        builder.append(VALUE_DELIMITER);
        builder.append(this.dnsServers);
        builder.append(VALUE_DELIMITER);
        builder.append(this.searchDomains);
        builder.append(VALUE_DELIMITER);
        builder.append(this.routes);
        builder.append(VALUE_DELIMITER);
        builder.append(this.mppe);
        builder.append(VALUE_DELIMITER);
        builder.append(this.l2tpSecret);
        builder.append(VALUE_DELIMITER);
        builder.append(this.ipsecIdentifier);
        builder.append(VALUE_DELIMITER);
        builder.append(this.ipsecSecret);
        builder.append(VALUE_DELIMITER);
        builder.append(this.ipsecUserCert);
        builder.append(VALUE_DELIMITER);
        builder.append(this.ipsecCaCert);
        builder.append(VALUE_DELIMITER);
        builder.append(this.ipsecServerCert);
        if (this.proxy != null) {
            builder.append(VALUE_DELIMITER);
            builder.append(this.proxy.getHost() != null ? this.proxy.getHost() : str);
            builder.append(VALUE_DELIMITER);
            builder.append(this.proxy.getPort());
            builder.append(VALUE_DELIMITER);
            if (ProxyUtils.exclusionListAsString(this.proxy.getExclusionList()) != null) {
                str = ProxyUtils.exclusionListAsString(this.proxy.getExclusionList());
            }
            builder.append(str);
            builder.append(VALUE_DELIMITER);
            builder.append(this.proxy.getPacFileUrl().toString());
        } else {
            builder.append(ENCODED_NULL_PROXY_INFO);
        }
        List<String> encodedAlgoNames = new ArrayList<>();
        try {
            for (String algo : this.mAllowedAlgorithms) {
                encodedAlgoNames.add(URLEncoder.encode(algo, DEFAULT_ENCODING));
            }
            builder.append(VALUE_DELIMITER);
            builder.append(String.join(",", encodedAlgoNames));
            builder.append(VALUE_DELIMITER);
            builder.append(this.isBypassable);
            builder.append(VALUE_DELIMITER);
            builder.append(this.isMetered);
            builder.append(VALUE_DELIMITER);
            builder.append(this.maxMtu);
            builder.append(VALUE_DELIMITER);
            builder.append(this.areAuthParamsInline);
            builder.append(VALUE_DELIMITER);
            builder.append(this.isRestrictedToTestNetworks);
            return builder.toString().getBytes(StandardCharsets.UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Failed to encode algorithms.", e);
        }
    }

    public static boolean isLegacyType(int type) {
        switch (type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return true;
            default:
                return false;
        }
    }

    private boolean isValidLockdownLegacyVpnProfile() {
        return isLegacyType(this.type) && isServerAddressNumeric() && hasDns() && areDnsAddressesNumeric();
    }

    private boolean isValidLockdownPlatformVpnProfile() {
        return Ikev2VpnProfile.isValidVpnProfile(this);
    }

    public boolean isValidLockdownProfile() {
        return isTypeValidForLockdown() && (isValidLockdownLegacyVpnProfile() || isValidLockdownPlatformVpnProfile());
    }

    public boolean isTypeValidForLockdown() {
        return this.type != 0;
    }

    public boolean isServerAddressNumeric() {
        try {
            InetAddress.parseNumericAddress(this.server);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean hasDns() {
        return !TextUtils.isEmpty(this.dnsServers);
    }

    public boolean areDnsAddressesNumeric() {
        String[] split;
        try {
            for (String dnsServer : this.dnsServers.split(" +")) {
                InetAddress.parseNumericAddress(dnsServer);
            }
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(this.key, Integer.valueOf(this.type), this.server, this.username, this.password, this.dnsServers, this.searchDomains, this.routes, Boolean.valueOf(this.mppe), this.l2tpSecret, this.ipsecIdentifier, this.ipsecSecret, this.ipsecUserCert, this.ipsecCaCert, this.ipsecServerCert, this.proxy, this.mAllowedAlgorithms, Boolean.valueOf(this.isBypassable), Boolean.valueOf(this.isMetered), Integer.valueOf(this.maxMtu), Boolean.valueOf(this.areAuthParamsInline), Boolean.valueOf(this.isRestrictedToTestNetworks));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof VpnProfile)) {
            return false;
        }
        VpnProfile other = (VpnProfile) obj;
        return Objects.equals(this.key, other.key) && Objects.equals(this.name, other.name) && this.type == other.type && Objects.equals(this.server, other.server) && Objects.equals(this.username, other.username) && Objects.equals(this.password, other.password) && Objects.equals(this.dnsServers, other.dnsServers) && Objects.equals(this.searchDomains, other.searchDomains) && Objects.equals(this.routes, other.routes) && this.mppe == other.mppe && Objects.equals(this.l2tpSecret, other.l2tpSecret) && Objects.equals(this.ipsecIdentifier, other.ipsecIdentifier) && Objects.equals(this.ipsecSecret, other.ipsecSecret) && Objects.equals(this.ipsecUserCert, other.ipsecUserCert) && Objects.equals(this.ipsecCaCert, other.ipsecCaCert) && Objects.equals(this.ipsecServerCert, other.ipsecServerCert) && Objects.equals(this.proxy, other.proxy) && Objects.equals(this.mAllowedAlgorithms, other.mAllowedAlgorithms) && this.isBypassable == other.isBypassable && this.isMetered == other.isMetered && this.maxMtu == other.maxMtu && this.areAuthParamsInline == other.areAuthParamsInline && this.isRestrictedToTestNetworks == other.isRestrictedToTestNetworks;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}
