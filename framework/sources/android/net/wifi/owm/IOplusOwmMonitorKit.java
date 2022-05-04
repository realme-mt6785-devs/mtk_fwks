package android.net.wifi.owm;

import android.common.IOplusCommonFeature;
import android.common.OplusFeatureList;
import android.os.Handler;
import java.util.Map;
/* loaded from: classes2.dex */
public interface IOplusOwmMonitorKit extends IOplusCommonFeature {
    public static final int BTC_EVT = 251658266;
    public static final IOplusOwmMonitorKit DEFAULT = new IOplusOwmMonitorKit() { // from class: android.net.wifi.owm.IOplusOwmMonitorKit.1
    };
    public static final int DHCP_FAIL_EVT = 251658244;
    public static final int DHCP_NAK_EVT = 251658263;
    public static final int DHCP_OFFER_EVT = 251658245;
    public static final int DNS_DUR_FAIL_EVT = 251658256;
    public static final int DNS_HIJACK_EVT = 251658254;
    public static final int DUAL_STA_ACT_EVT = 251658253;
    public static final int DUP_DHCP_SERV_EVT = 251658259;
    public static final int EVENT_BASE = 251658240;
    public static final int GAME_HIGH_LATENCY_EVT = 251658262;
    public static final int GATEWAY_HIGH_LATENCY_EVT = 251658261;
    public static final int GATEWAY_UNREACH_EVT = 251658260;
    public static final int ICMP_ERR_CODE_DST_HOST_UNKNOWN = 7;
    public static final int ICMP_ERR_CODE_DST_NET_UNKNOWN = 6;
    public static final int ICMP_ERR_CODE_FRAGMENT_NEED = 4;
    public static final int ICMP_ERR_CODE_HOST_UNREACHABLE = 1;
    public static final int ICMP_ERR_CODE_NET_UNREACHABLE = 0;
    public static final int ICMP_ERR_CODE_OTHERS = 8;
    public static final int ICMP_ERR_CODE_PORT_UNREACHABLE = 3;
    public static final int ICMP_ERR_CODE_PROTO_UNREACHABLE = 2;
    public static final int ICMP_ERR_CODE_SRC_ROUTINT_FAIL = 5;
    public static final int ICMP_ERR_EVT = 251658248;
    public static final int ICMP_UNREACHABLE_BURST_EVT = 251658264;
    public static final int IPV4_RTO_EVT = 251658258;
    public static final int IPV6_RTO_EVT = 251658249;
    public static final int IP_SUCC_EVT = 251658251;
    public static final int L2_STUCK_EVT = 251658250;
    public static final int L2_STUCK_TYPE_RX = 2;
    public static final int L2_STUCK_TYPE_TX = 1;
    public static final int L2_STUCK_TYPE_TX_RX = 3;
    public static final int L2_STUCK_TYPE_UNKNOWN = 0;
    public static final int MTU_PROB_NO_ENABLE_EVT = 251658247;
    public static final int MULTI_GATEWAY_EVT = 251658265;
    public static final String NAME = "IOplusOwmMonitorKit";
    public static final int NET_DUR_SLOW_EVT = 251658255;
    public static final int NET_INVALID_EVT = 251658242;
    public static final int NET_VALID_EVT = 251658243;
    public static final int NUD_FAIL_EVT = 251658241;
    public static final int ROAMING_EVT = 251658246;
    public static final int SLA_ACT_EVT = 251658252;
    public static final int TCP_DUR_RTO_EVT = 251658256;

    @Override // android.common.IOplusCommonFeature
    default OplusFeatureList.OplusIndex index() {
        return OplusFeatureList.OplusIndex.IOplusOwmMonitorKit;
    }

    @Override // android.common.IOplusCommonFeature
    default IOplusCommonFeature getDefault() {
        return DEFAULT;
    }

    default void registerEventHandler(int what, Handler handler) {
    }

    default void deregisterEventHandler(int what, Handler handler) {
    }

    default long getTxPackages(String ifname) {
        return 0L;
    }

    default long getRxPackages(String ifname) {
        return 0L;
    }

    default long getTcpTxPackages(String ifname) {
        return 0L;
    }

    default long getTcpRxPackages(String ifname) {
        return 0L;
    }

    default long getTcpRetryPackages(String ifname) {
        return 0L;
    }

    default long getTcpLossPackages(String ifname) {
        return 0L;
    }

    default int getDnsFailCnt(String ifname) {
        return 0;
    }

    default int getDnsRefuseCnt(String ifname) {
        return 0;
    }

    default boolean getGatewayConflictState(String ifname, boolean forceNewDetect) {
        return false;
    }

    default boolean getDupDhcpServerState(String ifname) {
        return false;
    }

    default String getWifiNetStateInfo() {
        return "";
    }

    default Map<String, String> getWifiGameLatencyInfo() {
        return null;
    }
}
