package android.net;

import android.net.StaticIpConfiguration;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.android.net.module.util.InetAddressUtils;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes2.dex */
public final class DhcpResults implements Parcelable {
    public static final Parcelable.Creator<DhcpResults> CREATOR = new Parcelable.Creator<DhcpResults>() { // from class: android.net.DhcpResults.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DhcpResults createFromParcel(Parcel in) {
            return DhcpResults.readFromParcel(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DhcpResults[] newArray(int size) {
            return new DhcpResults[size];
        }
    };
    private static final String TAG = "DhcpResults";
    public String captivePortalApiUrl;
    public final ArrayList<InetAddress> dnsServers;
    public String domains;
    public InetAddress gateway;
    public LinkAddress ipAddress;
    public int leaseDuration;
    public int mtu;
    public Inet4Address serverAddress;
    public String serverHostName;
    public String vendorInfo;

    public DhcpResults() {
        this.dnsServers = new ArrayList<>();
    }

    public StaticIpConfiguration toStaticIpConfiguration() {
        return new StaticIpConfiguration.Builder().setIpAddress(this.ipAddress).setGateway(this.gateway).setDnsServers(this.dnsServers).setDomains(this.domains).build();
    }

    public DhcpResults(StaticIpConfiguration source) {
        ArrayList<InetAddress> arrayList = new ArrayList<>();
        this.dnsServers = arrayList;
        if (source != null) {
            this.ipAddress = source.getIpAddress();
            this.gateway = source.getGateway();
            arrayList.addAll(source.getDnsServers());
            this.domains = source.getDomains();
        }
    }

    public DhcpResults(DhcpResults source) {
        this(source == null ? null : source.toStaticIpConfiguration());
        if (source != null) {
            this.serverAddress = source.serverAddress;
            this.vendorInfo = source.vendorInfo;
            this.leaseDuration = source.leaseDuration;
            this.mtu = source.mtu;
            this.serverHostName = source.serverHostName;
            this.captivePortalApiUrl = source.captivePortalApiUrl;
        }
    }

    public List<RouteInfo> getRoutes(String iface) {
        return toStaticIpConfiguration().getRoutes(iface);
    }

    public boolean hasMeteredHint() {
        String str = this.vendorInfo;
        if (str != null) {
            return str.contains("ANDROID_METERED");
        }
        return false;
    }

    public void clear() {
        this.ipAddress = null;
        this.gateway = null;
        this.dnsServers.clear();
        this.domains = null;
        this.serverAddress = null;
        this.vendorInfo = null;
        this.leaseDuration = 0;
        this.mtu = 0;
        this.serverHostName = null;
        this.captivePortalApiUrl = null;
    }

    public String toString() {
        StringBuffer str = new StringBuffer(super.toString());
        str.append(" DHCP server ");
        str.append(this.serverAddress);
        str.append(" Vendor info ");
        str.append(this.vendorInfo);
        str.append(" lease ");
        str.append(this.leaseDuration);
        str.append(" seconds");
        if (this.mtu != 0) {
            str.append(" MTU ");
            str.append(this.mtu);
        }
        str.append(" Servername ");
        str.append(this.serverHostName);
        if (this.captivePortalApiUrl != null) {
            str.append(" CaptivePortalApiUrl ");
            str.append(this.captivePortalApiUrl);
        }
        return str.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DhcpResults)) {
            return false;
        }
        DhcpResults target = (DhcpResults) obj;
        return toStaticIpConfiguration().equals(target.toStaticIpConfiguration()) && Objects.equals(this.serverAddress, target.serverAddress) && Objects.equals(this.vendorInfo, target.vendorInfo) && Objects.equals(this.serverHostName, target.serverHostName) && this.leaseDuration == target.leaseDuration && this.mtu == target.mtu && Objects.equals(this.captivePortalApiUrl, target.captivePortalApiUrl);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        toStaticIpConfiguration().writeToParcel(dest, flags);
        dest.writeInt(this.leaseDuration);
        dest.writeInt(this.mtu);
        InetAddressUtils.parcelInetAddress(dest, this.serverAddress, flags);
        dest.writeString(this.vendorInfo);
        dest.writeString(this.serverHostName);
        dest.writeString(this.captivePortalApiUrl);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static DhcpResults readFromParcel(Parcel in) {
        StaticIpConfiguration s = (StaticIpConfiguration) StaticIpConfiguration.CREATOR.createFromParcel(in);
        DhcpResults dhcpResults = new DhcpResults(s);
        dhcpResults.leaseDuration = in.readInt();
        dhcpResults.mtu = in.readInt();
        dhcpResults.serverAddress = (Inet4Address) InetAddressUtils.unparcelInetAddress(in);
        dhcpResults.vendorInfo = in.readString();
        dhcpResults.serverHostName = in.readString();
        dhcpResults.captivePortalApiUrl = in.readString();
        return dhcpResults;
    }

    public boolean setIpAddress(String addrString, int prefixLength) {
        try {
            Inet4Address addr = (Inet4Address) InetAddresses.parseNumericAddress(addrString);
            this.ipAddress = new LinkAddress(addr, prefixLength);
            return false;
        } catch (ClassCastException | IllegalArgumentException e) {
            Log.e(TAG, "setIpAddress failed with addrString " + addrString + "/" + prefixLength);
            return true;
        }
    }

    public boolean setGateway(String addrString) {
        try {
            this.gateway = InetAddresses.parseNumericAddress(addrString);
            return false;
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "setGateway failed with addrString " + addrString);
            return true;
        }
    }

    public boolean addDns(String addrString) {
        if (TextUtils.isEmpty(addrString)) {
            return false;
        }
        try {
            this.dnsServers.add(InetAddresses.parseNumericAddress(addrString));
            return false;
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "addDns failed with addrString " + addrString);
            return true;
        }
    }

    public LinkAddress getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(LinkAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    public InetAddress getGateway() {
        return this.gateway;
    }

    public void setGateway(InetAddress gateway) {
        this.gateway = gateway;
    }

    public List<InetAddress> getDnsServers() {
        return this.dnsServers;
    }

    public void addDnsServer(InetAddress server) {
        this.dnsServers.add(server);
    }

    public String getDomains() {
        return this.domains;
    }

    public void setDomains(String domains) {
        this.domains = domains;
    }

    public Inet4Address getServerAddress() {
        return this.serverAddress;
    }

    public void setServerAddress(Inet4Address addr) {
        this.serverAddress = addr;
    }

    public int getLeaseDuration() {
        return this.leaseDuration;
    }

    public void setLeaseDuration(int duration) {
        this.leaseDuration = duration;
    }

    public String getVendorInfo() {
        return this.vendorInfo;
    }

    public void setVendorInfo(String info) {
        this.vendorInfo = info;
    }

    public int getMtu() {
        return this.mtu;
    }

    public void setMtu(int mtu) {
        this.mtu = mtu;
    }

    public String getCaptivePortalApiUrl() {
        return this.captivePortalApiUrl;
    }

    public void setCaptivePortalApiUrl(String url) {
        this.captivePortalApiUrl = url;
    }
}
