package android.net.wifi.owm;

import android.common.IOplusCommonFeature;
import android.common.OplusFeatureList;
import android.net.LinkProperties;
import android.net.wifi.WifiInfo;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Map;
/* loaded from: classes2.dex */
public interface IOplusOwmMonitorCenter extends IOplusCommonFeature {
    public static final IOplusOwmMonitorCenter DEFAULT = new IOplusOwmMonitorCenter() { // from class: android.net.wifi.owm.IOplusOwmMonitorCenter.1
    };
    public static final String DUMP_ARG = "OwmStat";
    public static final String NAME = "IOplusOwmMonitorCenter";
    public static final int OPLUS_OWM_FEATURE_BASE = 0;
    public static final int OPLUS_WIFI_DATALIMIT = 12;
    public static final int OPLUS_WIFI_DIS_OPT = 10;
    public static final int OPLUS_WIFI_DUAL_STA = 1;
    public static final int OPLUS_WIFI_HT40 = 9;
    public static final int OPLUS_WIFI_MTU_PROB = 11;
    public static final int OPLUS_WIFI_PRIVATE_DNS = 5;
    public static final int OPLUS_WIFI_QPOWER = 8;
    public static final int OPLUS_WIFI_SELFCURE = 14;
    public static final int OPLUS_WIFI_SLA = 2;
    public static final int OPLUS_WIFI_SMARTGEAR = 7;
    public static final int OPLUS_WIFI_STATIC_ARP = 6;
    public static final int OPLUS_WIFI_STATIC_IP = 4;
    public static final int OPLUS_WIFI_VPN_STATUS = 13;
    public static final int OPLUS_WIFI_WN_S = 3;

    @Override // android.common.IOplusCommonFeature
    default OplusFeatureList.OplusIndex index() {
        return OplusFeatureList.OplusIndex.IOplusOwmMonitorCenter;
    }

    @Override // android.common.IOplusCommonFeature
    default IOplusCommonFeature getDefault() {
        return DEFAULT;
    }

    default boolean getOwmNetMonitorEnabled() {
        return false;
    }

    default String getPrimaryClientIfname() {
        return null;
    }

    default void addPrimaryClientIfname(String ifname) {
    }

    default void addConnWifiInfoRecord(String ifname, WifiInfo curWifiInfo, long txSuccess, long txRetries, long rxSuccess) {
    }

    default void addConnL2StatsRecord(String ifname, int radioOnTimeMs, int ccaBusyTimeMs, int txPer, int dataStallType) {
    }

    default void addRoamingRecord(String ifname, boolean isSucced, String lastBssid) {
    }

    default void addDhcpSuccRecord(String ifname, String ipAddr, String gateway) {
    }

    default void addDhcpFailRecord(String ifname) {
    }

    default void addIpConflictRecord(String ifname, String conflictIp) {
    }

    default void addArpDetectResultRecord(String ifname, Map<String, Long> arpResult) {
    }

    default void addNetScoreRecord(String ifname, int score) {
    }

    default void addNetValidRecord(String ifname, boolean valid) {
    }

    default void addNudFailRecord(String ifname, String nudMsg) {
    }

    default void addUidNetPolicyRecord(int uid) {
    }

    default void addIpVersionRecord(String ifname, LinkProperties lp) {
    }

    default void addIpv6RtoRecord(String ifname, String srcAddr, String destAddr, int uid) {
    }

    default void addIpv4RtoRecord(String ifname, String srcAddr, String destAddr, int uid) {
    }

    default void addGameFrontInState(boolean isFrontIn, String pkgName) {
    }

    default void addGameLatencyRecord(String gamePkgName, int rtt) {
    }

    default void addGamePlayingState(boolean isPlaying) {
    }

    default void addDnsRecord(int errCode, int latencyMs, int netId, String hostname, String[] ipAddresses) {
    }

    default void addTcpSyncRecord(int netId, int uid, int errCode, int latency, String ipAddr) {
    }

    default void addTcpStatRecord(int netId, long newSendPkg, long newRecvPkg, long newRetryPkg, long newLossPkg) {
    }

    default void addTcpRttRecord(long[] data) {
    }

    default void addTcpRttRecord(int rttMs) {
    }

    default void addRouterInfoRecord(String ifname, String connApName, boolean isConnSoftAp, boolean isConn1x1IotAp) {
    }

    default void addDataStallReportedFromFirmwareRecord(String ifname, int errCode) {
    }

    default void addWifiScanEventRecord() {
    }

    default void addOplusFeatureActivedRecord(String ifname, int featureId, boolean isActived) {
    }

    default void addOplusWifiSelfCureOngoingRecord(boolean isOngoing) {
    }

    default void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
    }
}
