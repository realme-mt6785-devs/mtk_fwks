package android.hardware.radio.V1_4;

import android.security.keystore.KeyProperties;
/* loaded from: classes2.dex */
public final class DataCallFailCause {
    public static final int ACCESS_ATTEMPT_ALREADY_IN_PROGRESS = 2219;
    public static final int ACCESS_BLOCK = 2087;
    public static final int ACCESS_BLOCK_ALL = 2088;
    public static final int ACCESS_CLASS_DSAC_REJECTION = 2108;
    public static final int ACCESS_CONTROL_LIST_CHECK_FAILURE = 2128;
    public static final int ACTIVATION_REJECTED_BCM_VIOLATION = 48;
    public static final int ACTIVATION_REJECT_GGSN = 30;
    public static final int ACTIVATION_REJECT_UNSPECIFIED = 31;
    public static final int APN_DISABLED = 2045;
    public static final int APN_DISALLOWED_ON_ROAMING = 2059;
    public static final int APN_MISMATCH = 2054;
    public static final int APN_PARAMETERS_CHANGED = 2060;
    public static final int APN_PENDING_HANDOVER = 2041;
    public static final int APN_TYPE_CONFLICT = 112;
    public static final int AUTH_FAILURE_ON_EMERGENCY_CALL = 122;
    public static final int BEARER_HANDLING_NOT_SUPPORTED = 60;
    public static final int CALL_DISALLOWED_IN_ROAMING = 2068;
    public static final int CALL_PREEMPT_BY_EMERGENCY_APN = 127;
    public static final int CANNOT_ENCODE_OTA_MESSAGE = 2159;
    public static final int CDMA_ALERT_STOP = 2077;
    public static final int CDMA_INCOMING_CALL = 2076;
    public static final int CDMA_INTERCEPT = 2073;
    public static final int CDMA_LOCK = 2072;
    public static final int CDMA_RELEASE_DUE_TO_SO_REJECTION = 2075;
    public static final int CDMA_REORDER = 2074;
    public static final int CDMA_RETRY_ORDER = 2086;
    public static final int CHANNEL_ACQUISITION_FAILURE = 2078;
    public static final int CLOSE_IN_PROGRESS = 2030;
    public static final int COLLISION_WITH_NETWORK_INITIATED_REQUEST = 56;
    public static final int COMPANION_IFACE_IN_USE = 118;
    public static final int CONCURRENT_SERVICES_INCOMPATIBLE = 2083;
    public static final int CONCURRENT_SERVICES_NOT_ALLOWED = 2091;
    public static final int CONCURRENT_SERVICE_NOT_SUPPORTED_BY_BASE_STATION = 2080;
    public static final int CONDITIONAL_IE_ERROR = 100;
    public static final int CONGESTION = 2106;
    public static final int CONNECTION_RELEASED = 2113;
    public static final int CS_DOMAIN_NOT_AVAILABLE = 2181;
    public static final int CS_FALLBACK_CALL_ESTABLISHMENT_NOT_ALLOWED = 2188;
    public static final int DATA_PLAN_EXPIRED = 2198;
    public static final int DATA_REGISTRATION_FAIL = -2;
    public static final int DATA_ROAMING_SETTINGS_DISABLED = 2064;
    public static final int DATA_SETTINGS_DISABLED = 2063;
    public static final int DBM_OR_SMS_IN_PROGRESS = 2211;
    public static final int DDS_SWITCHED = 2065;
    public static final int DDS_SWITCH_IN_PROGRESS = 2067;
    public static final int DRB_RELEASED_BY_RRC = 2112;
    public static final int DS_EXPLICIT_DEACTIVATION = 2125;
    public static final int DUAL_SWITCH = 2227;
    public static final int DUN_CALL_DISALLOWED = 2056;
    public static final int DUPLICATE_BEARER_ID = 2118;
    public static final int EHRPD_TO_HRPD_FALLBACK = 2049;
    public static final int EMBMS_NOT_ENABLED = 2193;
    public static final int EMBMS_REGULAR_DEACTIVATION = 2195;
    public static final int EMERGENCY_IFACE_ONLY = 116;
    public static final int EMERGENCY_MODE = 2221;
    public static final int EMM_ACCESS_BARRED = 115;
    public static final int EMM_ACCESS_BARRED_INFINITE_RETRY = 121;
    public static final int EMM_ATTACH_FAILED = 2115;
    public static final int EMM_ATTACH_STARTED = 2116;
    public static final int EMM_DETACHED = 2114;
    public static final int EMM_T3417_EXPIRED = 2130;
    public static final int EMM_T3417_EXT_EXPIRED = 2131;
    public static final int EPS_SERVICES_AND_NON_EPS_SERVICES_NOT_ALLOWED = 2178;
    public static final int EPS_SERVICES_NOT_ALLOWED_IN_PLMN = 2179;
    public static final int ERROR_UNSPECIFIED = 65535;
    public static final int ESM_BAD_OTA_MESSAGE = 2122;
    public static final int ESM_BEARER_DEACTIVATED_TO_SYNC_WITH_NETWORK = 2120;
    public static final int ESM_COLLISION_SCENARIOS = 2119;
    public static final int ESM_CONTEXT_TRANSFERRED_DUE_TO_IRAT = 2124;
    public static final int ESM_DOWNLOAD_SERVER_REJECTED_THE_CALL = 2123;
    public static final int ESM_FAILURE = 2182;
    public static final int ESM_INFO_NOT_RECEIVED = 53;
    public static final int ESM_LOCAL_CAUSE_NONE = 2126;
    public static final int ESM_NW_ACTIVATED_DED_BEARER_WITH_ID_OF_DEF_BEARER = 2121;
    public static final int ESM_PROCEDURE_TIME_OUT = 2155;
    public static final int ESM_UNKNOWN_EPS_BEARER_CONTEXT = 2111;
    public static final int EVDO_CONNECTION_DENY_BY_BILLING_OR_AUTHENTICATION_FAILURE = 2201;
    public static final int EVDO_CONNECTION_DENY_BY_GENERAL_OR_NETWORK_BUSY = 2200;
    public static final int EVDO_HDR_CHANGED = 2202;
    public static final int EVDO_HDR_CONNECTION_SETUP_TIMEOUT = 2206;
    public static final int EVDO_HDR_EXITED = 2203;
    public static final int EVDO_HDR_NO_SESSION = 2204;
    public static final int EVDO_USING_GPS_FIX_INSTEAD_OF_HDR_CALL = 2205;
    public static final int FADE = 2217;
    public static final int FAILED_TO_ACQUIRE_COLOCATED_HDR = 2207;
    public static final int FEATURE_NOT_SUPP = 40;
    public static final int FILTER_SEMANTIC_ERROR = 44;
    public static final int FILTER_SYTAX_ERROR = 45;
    public static final int FORBIDDEN_APN_NAME = 2066;
    public static final int GPRS_SERVICES_AND_NON_GPRS_SERVICES_NOT_ALLOWED = 2097;
    public static final int GPRS_SERVICES_NOT_ALLOWED = 2098;
    public static final int GPRS_SERVICES_NOT_ALLOWED_IN_THIS_PLMN = 2103;
    public static final int HANDOFF_PREFERENCE_CHANGED = 2251;
    public static final int HDR_ACCESS_FAILURE = 2213;
    public static final int HDR_FADE = 2212;
    public static final int HDR_NO_LOCK_GRANTED = 2210;
    public static final int IFACE_AND_POL_FAMILY_MISMATCH = 120;
    public static final int IFACE_MISMATCH = 117;
    public static final int ILLEGAL_ME = 2096;
    public static final int ILLEGAL_MS = 2095;
    public static final int IMEI_NOT_ACCEPTED = 2177;
    public static final int IMPLICITLY_DETACHED = 2100;
    public static final int IMSI_UNKNOWN_IN_HOME_SUBSCRIBER_SERVER = 2176;
    public static final int INCOMING_CALL_REJECTED = 2092;
    public static final int INSUFFICIENT_RESOURCES = 26;
    public static final int INTERFACE_IN_USE = 2058;
    public static final int INTERNAL_CALL_PREEMPT_BY_HIGH_PRIO_APN = 114;
    public static final int INTERNAL_EPC_NONEPC_TRANSITION = 2057;
    public static final int INVALID_CONNECTION_ID = 2156;
    public static final int INVALID_DNS_ADDR = 123;
    public static final int INVALID_EMM_STATE = 2190;
    public static final int INVALID_MANDATORY_INFO = 96;
    public static final int INVALID_MODE = 2223;
    public static final int INVALID_PCSCF_ADDR = 113;
    public static final int INVALID_PCSCF_OR_DNS_ADDRESS = 124;
    public static final int INVALID_PRIMARY_NSAPI = 2158;
    public static final int INVALID_SIM_STATE = 2224;
    public static final int INVALID_TRANSACTION_ID = 81;
    public static final int IPV6_ADDRESS_TRANSFER_FAILED = 2047;
    public static final int IPV6_PREFIX_UNAVAILABLE = 2250;
    public static final int IP_ADDRESS_MISMATCH = 119;
    public static final int IP_VERSION_MISMATCH = 2055;
    public static final int IRAT_HANDOVER_FAILED = 2194;
    public static final int IS707B_MAX_ACCESS_PROBES = 2089;
    public static final int LIMITED_TO_IPV4 = 2234;
    public static final int LIMITED_TO_IPV6 = 2235;
    public static final int LLC_SNDCP = 25;
    public static final int LOCAL_END = 2215;
    public static final int LOCATION_AREA_NOT_ALLOWED = 2102;
    public static final int LOWER_LAYER_REGISTRATION_FAILURE = 2197;
    public static final int LOW_POWER_MODE_OR_POWERING_DOWN = 2044;
    public static final int LTE_NAS_SERVICE_REQUEST_FAILED = 2117;
    public static final int LTE_THROTTLING_NOT_REQUIRED = 2127;
    public static final int MAC_FAILURE = 2183;
    public static final int MAXIMIUM_NSAPIS_EXCEEDED = 2157;
    public static final int MAXINUM_SIZE_OF_L2_MESSAGE_EXCEEDED = 2166;
    public static final int MAX_ACCESS_PROBE = 2079;
    public static final int MAX_ACTIVE_PDP_CONTEXT_REACHED = 65;
    public static final int MAX_IPV4_CONNECTIONS = 2052;
    public static final int MAX_IPV6_CONNECTIONS = 2053;
    public static final int MAX_PPP_INACTIVITY_TIMER_EXPIRED = 2046;
    public static final int MESSAGE_INCORRECT_SEMANTIC = 95;
    public static final int MESSAGE_TYPE_UNSUPPORTED = 97;
    public static final int MIP_CONFIG_FAILURE = 2050;
    public static final int MIP_FA_ADMIN_PROHIBITED = 2001;
    public static final int MIP_FA_DELIVERY_STYLE_NOT_SUPPORTED = 2012;
    public static final int MIP_FA_ENCAPSULATION_UNAVAILABLE = 2008;
    public static final int MIP_FA_HOME_AGENT_AUTHENTICATION_FAILURE = 2004;
    public static final int MIP_FA_INSUFFICIENT_RESOURCES = 2002;
    public static final int MIP_FA_MALFORMED_REPLY = 2007;
    public static final int MIP_FA_MALFORMED_REQUEST = 2006;
    public static final int MIP_FA_MISSING_CHALLENGE = 2017;
    public static final int MIP_FA_MISSING_HOME_ADDRESS = 2015;
    public static final int MIP_FA_MISSING_HOME_AGENT = 2014;
    public static final int MIP_FA_MISSING_NAI = 2013;
    public static final int MIP_FA_MOBILE_NODE_AUTHENTICATION_FAILURE = 2003;
    public static final int MIP_FA_REASON_UNSPECIFIED = 2000;
    public static final int MIP_FA_REQUESTED_LIFETIME_TOO_LONG = 2005;
    public static final int MIP_FA_REVERSE_TUNNEL_IS_MANDATORY = 2011;
    public static final int MIP_FA_REVERSE_TUNNEL_UNAVAILABLE = 2010;
    public static final int MIP_FA_STALE_CHALLENGE = 2018;
    public static final int MIP_FA_UNKNOWN_CHALLENGE = 2016;
    public static final int MIP_FA_VJ_HEADER_COMPRESSION_UNAVAILABLE = 2009;
    public static final int MIP_HA_ADMIN_PROHIBITED = 2020;
    public static final int MIP_HA_ENCAPSULATION_UNAVAILABLE = 2029;
    public static final int MIP_HA_FOREIGN_AGENT_AUTHENTICATION_FAILURE = 2023;
    public static final int MIP_HA_INSUFFICIENT_RESOURCES = 2021;
    public static final int MIP_HA_MALFORMED_REQUEST = 2025;
    public static final int MIP_HA_MOBILE_NODE_AUTHENTICATION_FAILURE = 2022;
    public static final int MIP_HA_REASON_UNSPECIFIED = 2019;
    public static final int MIP_HA_REGISTRATION_ID_MISMATCH = 2024;
    public static final int MIP_HA_REVERSE_TUNNEL_IS_MANDATORY = 2028;
    public static final int MIP_HA_REVERSE_TUNNEL_UNAVAILABLE = 2027;
    public static final int MIP_HA_UNKNOWN_HOME_AGENT_ADDRESS = 2026;
    public static final int MISSING_UKNOWN_APN = 27;
    public static final int MODEM_APP_PREEMPTED = 2032;
    public static final int MODEM_RESTART = 2037;
    public static final int MSC_TEMPORARILY_NOT_REACHABLE = 2180;
    public static final int MSG_AND_PROTOCOL_STATE_UNCOMPATIBLE = 101;
    public static final int MSG_TYPE_NONCOMPATIBLE_STATE = 98;
    public static final int MS_IDENTITY_CANNOT_BE_DERIVED_BY_THE_NETWORK = 2099;
    public static final int MULTIPLE_PDP_CALL_NOT_ALLOWED = 2192;
    public static final int MULTI_CONN_TO_SAME_PDN_NOT_ALLOWED = 55;
    public static final int NAS_LAYER_FAILURE = 2191;
    public static final int NAS_REQUEST_REJECTED_BY_NETWORK = 2167;
    public static final int NAS_SIGNALLING = 14;
    public static final int NETWORK_FAILURE = 38;
    public static final int NETWORK_INITIATED_DETACH_NO_AUTO_REATTACH = 2154;
    public static final int NETWORK_INITIATED_DETACH_WITH_AUTO_REATTACH = 2153;
    public static final int NETWORK_INITIATED_TERMINATION = 2031;
    public static final int NONE = 0;
    public static final int NON_IP_NOT_SUPPORTED = 2069;
    public static final int NORMAL_RELEASE = 2218;
    public static final int NO_CDMA_SERVICE = 2084;
    public static final int NO_COLLOCATED_HDR = 2225;
    public static final int NO_EPS_BEARER_CONTEXT_ACTIVATED = 2189;
    public static final int NO_GPRS_CONTEXT = 2094;
    public static final int NO_HYBRID_HDR_SERVICE = 2209;
    public static final int NO_PDP_CONTEXT_ACTIVATED = 2107;
    public static final int NO_RESPONSE_FROM_BASE_STATION = 2081;
    public static final int NO_SERVICE = 2216;
    public static final int NO_SERVICE_ON_GATEWAY = 2093;
    public static final int NSAPI_IN_USE = 35;
    public static final int NULL_APN_DISALLOWED = 2061;
    public static final int OEM_DCFAILCAUSE_1 = 4097;
    public static final int OEM_DCFAILCAUSE_10 = 4106;
    public static final int OEM_DCFAILCAUSE_11 = 4107;
    public static final int OEM_DCFAILCAUSE_12 = 4108;
    public static final int OEM_DCFAILCAUSE_13 = 4109;
    public static final int OEM_DCFAILCAUSE_14 = 4110;
    public static final int OEM_DCFAILCAUSE_15 = 4111;
    public static final int OEM_DCFAILCAUSE_2 = 4098;
    public static final int OEM_DCFAILCAUSE_3 = 4099;
    public static final int OEM_DCFAILCAUSE_4 = 4100;
    public static final int OEM_DCFAILCAUSE_5 = 4101;
    public static final int OEM_DCFAILCAUSE_6 = 4102;
    public static final int OEM_DCFAILCAUSE_7 = 4103;
    public static final int OEM_DCFAILCAUSE_8 = 4104;
    public static final int OEM_DCFAILCAUSE_9 = 4105;
    public static final int ONLY_IPV4V6_ALLOWED = 57;
    public static final int ONLY_IPV4_ALLOWED = 50;
    public static final int ONLY_IPV6_ALLOWED = 51;
    public static final int ONLY_NON_IP_ALLOWED = 58;
    public static final int ONLY_SINGLE_BEARER_ALLOWED = 52;
    public static final int OPERATOR_BARRED = 8;
    public static final int OTASP_COMMIT_IN_PROGRESS = 2208;
    public static final int PDN_CONN_DOES_NOT_EXIST = 54;
    public static final int PDN_INACTIVITY_TIMER_EXPIRED = 2051;
    public static final int PDN_IPV4_CALL_DISALLOWED = 2033;
    public static final int PDN_IPV4_CALL_THROTTLED = 2034;
    public static final int PDN_IPV6_CALL_DISALLOWED = 2035;
    public static final int PDN_IPV6_CALL_THROTTLED = 2036;
    public static final int PDN_NON_IP_CALL_DISALLOWED = 2071;
    public static final int PDN_NON_IP_CALL_THROTTLED = 2070;
    public static final int PDP_ACTIVATE_MAX_RETRY_FAILED = 2109;
    public static final int PDP_DUPLICATE = 2104;
    public static final int PDP_ESTABLISH_TIMEOUT_EXPIRED = 2161;
    public static final int PDP_INACTIVE_TIMEOUT_EXPIRED = 2163;
    public static final int PDP_LOWERLAYER_ERROR = 2164;
    public static final int PDP_MODIFY_COLLISION = 2165;
    public static final int PDP_MODIFY_TIMEOUT_EXPIRED = 2162;
    public static final int PDP_PPP_NOT_SUPPORTED = 2038;
    public static final int PDP_WITHOUT_ACTIVE_TFT = 46;
    public static final int PHONE_IN_USE = 2222;
    public static final int PHYSICAL_LINK_CLOSE_IN_PROGRESS = 2040;
    public static final int PLMN_NOT_ALLOWED = 2101;
    public static final int PPP_AUTH_FAILURE = 2229;
    public static final int PPP_CHAP_FAILURE = 2232;
    public static final int PPP_CLOSE_IN_PROGRESS = 2233;
    public static final int PPP_OPTION_MISMATCH = 2230;
    public static final int PPP_PAP_FAILURE = 2231;
    public static final int PPP_TIMEOUT = 2228;
    public static final int PREF_RADIO_TECH_CHANGED = -4;
    public static final int PROFILE_BEARER_INCOMPATIBLE = 2042;
    public static final int PROTOCOL_ERRORS = 111;
    public static final int QOS_NOT_ACCEPTED = 37;
    public static final int RADIO_ACCESS_BEARER_FAILURE = 2110;
    public static final int RADIO_ACCESS_BEARER_SETUP_FAILURE = 2160;
    public static final int RADIO_POWER_OFF = -5;
    public static final int REDIRECTION_OR_HANDOFF_IN_PROGRESS = 2220;
    public static final int REGULAR_DEACTIVATION = 36;
    public static final int REJECTED_BY_BASE_STATION = 2082;
    public static final int RRC_CONNECTION_ABORTED_AFTER_HANDOVER = 2173;
    public static final int RRC_CONNECTION_ABORTED_AFTER_IRAT_CELL_CHANGE = 2174;
    public static final int RRC_CONNECTION_ABORTED_DUE_TO_IRAT_CHANGE = 2171;
    public static final int RRC_CONNECTION_ABORTED_DURING_IRAT_CELL_CHANGE = 2175;
    public static final int RRC_CONNECTION_ABORT_REQUEST = 2151;
    public static final int RRC_CONNECTION_ACCESS_BARRED = 2139;
    public static final int RRC_CONNECTION_ACCESS_STRATUM_FAILURE = 2137;
    public static final int RRC_CONNECTION_ANOTHER_PROCEDURE_IN_PROGRESS = 2138;
    public static final int RRC_CONNECTION_CELL_NOT_CAMPED = 2144;
    public static final int RRC_CONNECTION_CELL_RESELECTION = 2140;
    public static final int RRC_CONNECTION_CONFIG_FAILURE = 2141;
    public static final int RRC_CONNECTION_INVALID_REQUEST = 2168;
    public static final int RRC_CONNECTION_LINK_FAILURE = 2143;
    public static final int RRC_CONNECTION_NORMAL_RELEASE = 2147;
    public static final int RRC_CONNECTION_OUT_OF_SERVICE_DURING_CELL_REGISTER = 2150;
    public static final int RRC_CONNECTION_RADIO_LINK_FAILURE = 2148;
    public static final int RRC_CONNECTION_REESTABLISHMENT_FAILURE = 2149;
    public static final int RRC_CONNECTION_REJECT_BY_NETWORK = 2146;
    public static final int RRC_CONNECTION_RELEASED_SECURITY_NOT_ACTIVE = 2172;
    public static final int RRC_CONNECTION_RF_UNAVAILABLE = 2170;
    public static final int RRC_CONNECTION_SYSTEM_INFORMATION_BLOCK_READ_ERROR = 2152;
    public static final int RRC_CONNECTION_SYSTEM_INTERVAL_FAILURE = 2145;
    public static final int RRC_CONNECTION_TIMER_EXPIRED = 2142;
    public static final int RRC_CONNECTION_TRACKING_AREA_ID_CHANGED = 2169;
    public static final int RRC_UPLINK_CONNECTION_RELEASE = 2134;
    public static final int RRC_UPLINK_DATA_TRANSMISSION_FAILURE = 2132;
    public static final int RRC_UPLINK_DELIVERY_FAILED_DUE_TO_HANDOVER = 2133;
    public static final int RRC_UPLINK_ERROR_REQUEST_FROM_NAS = 2136;
    public static final int RRC_UPLINK_RADIO_LINK_FAILURE = 2135;
    public static final int RUIM_NOT_PRESENT = 2085;
    public static final int SECURITY_MODE_REJECTED = 2186;
    public static final int SERVICE_NOT_ALLOWED_ON_PLMN = 2129;
    public static final int SERVICE_OPTION_NOT_SUBSCRIBED = 33;
    public static final int SERVICE_OPTION_NOT_SUPPORTED = 32;
    public static final int SERVICE_OPTION_OUT_OF_ORDER = 34;
    public static final int SIGNAL_LOST = -3;
    public static final int SIM_CARD_CHANGED = 2043;
    public static final int SYNCHRONIZATION_FAILURE = 2184;
    public static final int TEST_LOOPBACK_REGULAR_DEACTIVATION = 2196;
    public static final int TETHERED_CALL_ACTIVE = -6;
    public static final int TFT_SEMANTIC_ERROR = 41;
    public static final int TFT_SYTAX_ERROR = 42;
    public static final int THERMAL_EMERGENCY = 2090;
    public static final int THERMAL_MITIGATION = 2062;
    public static final int TRAT_SWAP_FAILED = 2048;
    public static final int UE_INITIATED_DETACH_OR_DISCONNECT = 128;
    public static final int UE_IS_ENTERING_POWERSAVE_MODE = 2226;
    public static final int UE_RAT_CHANGE = 2105;
    public static final int UE_SECURITY_CAPABILITIES_MISMATCH = 2185;
    public static final int UMTS_HANDOVER_TO_IWLAN = 2199;
    public static final int UMTS_REACTIVATION_REQ = 39;
    public static final int UNACCEPTABLE_NON_EPS_AUTHENTICATION = 2187;
    public static final int UNKNOWN_INFO_ELEMENT = 99;
    public static final int UNKNOWN_PDP_ADDRESS_TYPE = 28;
    public static final int UNKNOWN_PDP_CONTEXT = 43;
    public static final int UNPREFERRED_RAT = 2039;
    public static final int UNSUPPORTED_1X_PREV = 2214;
    public static final int UNSUPPORTED_APN_IN_CURRENT_PLMN = 66;
    public static final int UNSUPPORTED_QCI_VALUE = 59;
    public static final int USER_AUTHENTICATION = 29;
    public static final int VOICE_REGISTRATION_FAIL = -1;
    public static final int VSNCP_ADMINISTRATIVELY_PROHIBITED = 2245;
    public static final int VSNCP_APN_UNATHORIZED = 2238;
    public static final int VSNCP_GEN_ERROR = 2237;
    public static final int VSNCP_INSUFFICIENT_PARAMETERS = 2243;
    public static final int VSNCP_NO_PDN_GATEWAY_ADDRESS = 2240;
    public static final int VSNCP_PDN_EXISTS_FOR_THIS_APN = 2248;
    public static final int VSNCP_PDN_GATEWAY_REJECT = 2242;
    public static final int VSNCP_PDN_GATEWAY_UNREACHABLE = 2241;
    public static final int VSNCP_PDN_ID_IN_USE = 2246;
    public static final int VSNCP_PDN_LIMIT_EXCEEDED = 2239;
    public static final int VSNCP_RECONNECT_NOT_ALLOWED = 2249;
    public static final int VSNCP_RESOURCE_UNAVAILABLE = 2244;
    public static final int VSNCP_SUBSCRIBER_LIMITATION = 2247;
    public static final int VSNCP_TIMEOUT = 2236;

    public static final String toString(int o) {
        if (o == 0) {
            return KeyProperties.DIGEST_NONE;
        }
        if (o == 8) {
            return "OPERATOR_BARRED";
        }
        if (o == 14) {
            return "NAS_SIGNALLING";
        }
        if (o == 26) {
            return "INSUFFICIENT_RESOURCES";
        }
        if (o == 27) {
            return "MISSING_UKNOWN_APN";
        }
        if (o == 28) {
            return "UNKNOWN_PDP_ADDRESS_TYPE";
        }
        if (o == 29) {
            return "USER_AUTHENTICATION";
        }
        if (o == 30) {
            return "ACTIVATION_REJECT_GGSN";
        }
        if (o == 31) {
            return "ACTIVATION_REJECT_UNSPECIFIED";
        }
        if (o == 32) {
            return "SERVICE_OPTION_NOT_SUPPORTED";
        }
        if (o == 33) {
            return "SERVICE_OPTION_NOT_SUBSCRIBED";
        }
        if (o == 34) {
            return "SERVICE_OPTION_OUT_OF_ORDER";
        }
        if (o == 35) {
            return "NSAPI_IN_USE";
        }
        if (o == 36) {
            return "REGULAR_DEACTIVATION";
        }
        if (o == 37) {
            return "QOS_NOT_ACCEPTED";
        }
        if (o == 38) {
            return "NETWORK_FAILURE";
        }
        if (o == 39) {
            return "UMTS_REACTIVATION_REQ";
        }
        if (o == 40) {
            return "FEATURE_NOT_SUPP";
        }
        if (o == 41) {
            return "TFT_SEMANTIC_ERROR";
        }
        if (o == 42) {
            return "TFT_SYTAX_ERROR";
        }
        if (o == 43) {
            return "UNKNOWN_PDP_CONTEXT";
        }
        if (o == 44) {
            return "FILTER_SEMANTIC_ERROR";
        }
        if (o == 45) {
            return "FILTER_SYTAX_ERROR";
        }
        if (o == 46) {
            return "PDP_WITHOUT_ACTIVE_TFT";
        }
        if (o == 50) {
            return "ONLY_IPV4_ALLOWED";
        }
        if (o == 51) {
            return "ONLY_IPV6_ALLOWED";
        }
        if (o == 52) {
            return "ONLY_SINGLE_BEARER_ALLOWED";
        }
        if (o == 53) {
            return "ESM_INFO_NOT_RECEIVED";
        }
        if (o == 54) {
            return "PDN_CONN_DOES_NOT_EXIST";
        }
        if (o == 55) {
            return "MULTI_CONN_TO_SAME_PDN_NOT_ALLOWED";
        }
        if (o == 65) {
            return "MAX_ACTIVE_PDP_CONTEXT_REACHED";
        }
        if (o == 66) {
            return "UNSUPPORTED_APN_IN_CURRENT_PLMN";
        }
        if (o == 81) {
            return "INVALID_TRANSACTION_ID";
        }
        if (o == 95) {
            return "MESSAGE_INCORRECT_SEMANTIC";
        }
        if (o == 96) {
            return "INVALID_MANDATORY_INFO";
        }
        if (o == 97) {
            return "MESSAGE_TYPE_UNSUPPORTED";
        }
        if (o == 98) {
            return "MSG_TYPE_NONCOMPATIBLE_STATE";
        }
        if (o == 99) {
            return "UNKNOWN_INFO_ELEMENT";
        }
        if (o == 100) {
            return "CONDITIONAL_IE_ERROR";
        }
        if (o == 101) {
            return "MSG_AND_PROTOCOL_STATE_UNCOMPATIBLE";
        }
        if (o == 111) {
            return "PROTOCOL_ERRORS";
        }
        if (o == 112) {
            return "APN_TYPE_CONFLICT";
        }
        if (o == 113) {
            return "INVALID_PCSCF_ADDR";
        }
        if (o == 114) {
            return "INTERNAL_CALL_PREEMPT_BY_HIGH_PRIO_APN";
        }
        if (o == 115) {
            return "EMM_ACCESS_BARRED";
        }
        if (o == 116) {
            return "EMERGENCY_IFACE_ONLY";
        }
        if (o == 117) {
            return "IFACE_MISMATCH";
        }
        if (o == 118) {
            return "COMPANION_IFACE_IN_USE";
        }
        if (o == 119) {
            return "IP_ADDRESS_MISMATCH";
        }
        if (o == 120) {
            return "IFACE_AND_POL_FAMILY_MISMATCH";
        }
        if (o == 121) {
            return "EMM_ACCESS_BARRED_INFINITE_RETRY";
        }
        if (o == 122) {
            return "AUTH_FAILURE_ON_EMERGENCY_CALL";
        }
        if (o == 4097) {
            return "OEM_DCFAILCAUSE_1";
        }
        if (o == 4098) {
            return "OEM_DCFAILCAUSE_2";
        }
        if (o == 4099) {
            return "OEM_DCFAILCAUSE_3";
        }
        if (o == 4100) {
            return "OEM_DCFAILCAUSE_4";
        }
        if (o == 4101) {
            return "OEM_DCFAILCAUSE_5";
        }
        if (o == 4102) {
            return "OEM_DCFAILCAUSE_6";
        }
        if (o == 4103) {
            return "OEM_DCFAILCAUSE_7";
        }
        if (o == 4104) {
            return "OEM_DCFAILCAUSE_8";
        }
        if (o == 4105) {
            return "OEM_DCFAILCAUSE_9";
        }
        if (o == 4106) {
            return "OEM_DCFAILCAUSE_10";
        }
        if (o == 4107) {
            return "OEM_DCFAILCAUSE_11";
        }
        if (o == 4108) {
            return "OEM_DCFAILCAUSE_12";
        }
        if (o == 4109) {
            return "OEM_DCFAILCAUSE_13";
        }
        if (o == 4110) {
            return "OEM_DCFAILCAUSE_14";
        }
        if (o == 4111) {
            return "OEM_DCFAILCAUSE_15";
        }
        if (o == -1) {
            return "VOICE_REGISTRATION_FAIL";
        }
        if (o == -2) {
            return "DATA_REGISTRATION_FAIL";
        }
        if (o == -3) {
            return "SIGNAL_LOST";
        }
        if (o == -4) {
            return "PREF_RADIO_TECH_CHANGED";
        }
        if (o == -5) {
            return "RADIO_POWER_OFF";
        }
        if (o == -6) {
            return "TETHERED_CALL_ACTIVE";
        }
        if (o == 65535) {
            return "ERROR_UNSPECIFIED";
        }
        if (o == 25) {
            return "LLC_SNDCP";
        }
        if (o == 48) {
            return "ACTIVATION_REJECTED_BCM_VIOLATION";
        }
        if (o == 56) {
            return "COLLISION_WITH_NETWORK_INITIATED_REQUEST";
        }
        if (o == 57) {
            return "ONLY_IPV4V6_ALLOWED";
        }
        if (o == 58) {
            return "ONLY_NON_IP_ALLOWED";
        }
        if (o == 59) {
            return "UNSUPPORTED_QCI_VALUE";
        }
        if (o == 60) {
            return "BEARER_HANDLING_NOT_SUPPORTED";
        }
        if (o == 123) {
            return "INVALID_DNS_ADDR";
        }
        if (o == 124) {
            return "INVALID_PCSCF_OR_DNS_ADDRESS";
        }
        if (o == 127) {
            return "CALL_PREEMPT_BY_EMERGENCY_APN";
        }
        if (o == 128) {
            return "UE_INITIATED_DETACH_OR_DISCONNECT";
        }
        if (o == 2000) {
            return "MIP_FA_REASON_UNSPECIFIED";
        }
        if (o == 2001) {
            return "MIP_FA_ADMIN_PROHIBITED";
        }
        if (o == 2002) {
            return "MIP_FA_INSUFFICIENT_RESOURCES";
        }
        if (o == 2003) {
            return "MIP_FA_MOBILE_NODE_AUTHENTICATION_FAILURE";
        }
        if (o == 2004) {
            return "MIP_FA_HOME_AGENT_AUTHENTICATION_FAILURE";
        }
        if (o == 2005) {
            return "MIP_FA_REQUESTED_LIFETIME_TOO_LONG";
        }
        if (o == 2006) {
            return "MIP_FA_MALFORMED_REQUEST";
        }
        if (o == 2007) {
            return "MIP_FA_MALFORMED_REPLY";
        }
        if (o == 2008) {
            return "MIP_FA_ENCAPSULATION_UNAVAILABLE";
        }
        if (o == 2009) {
            return "MIP_FA_VJ_HEADER_COMPRESSION_UNAVAILABLE";
        }
        if (o == 2010) {
            return "MIP_FA_REVERSE_TUNNEL_UNAVAILABLE";
        }
        if (o == 2011) {
            return "MIP_FA_REVERSE_TUNNEL_IS_MANDATORY";
        }
        if (o == 2012) {
            return "MIP_FA_DELIVERY_STYLE_NOT_SUPPORTED";
        }
        if (o == 2013) {
            return "MIP_FA_MISSING_NAI";
        }
        if (o == 2014) {
            return "MIP_FA_MISSING_HOME_AGENT";
        }
        if (o == 2015) {
            return "MIP_FA_MISSING_HOME_ADDRESS";
        }
        if (o == 2016) {
            return "MIP_FA_UNKNOWN_CHALLENGE";
        }
        if (o == 2017) {
            return "MIP_FA_MISSING_CHALLENGE";
        }
        if (o == 2018) {
            return "MIP_FA_STALE_CHALLENGE";
        }
        if (o == 2019) {
            return "MIP_HA_REASON_UNSPECIFIED";
        }
        if (o == 2020) {
            return "MIP_HA_ADMIN_PROHIBITED";
        }
        if (o == 2021) {
            return "MIP_HA_INSUFFICIENT_RESOURCES";
        }
        if (o == 2022) {
            return "MIP_HA_MOBILE_NODE_AUTHENTICATION_FAILURE";
        }
        if (o == 2023) {
            return "MIP_HA_FOREIGN_AGENT_AUTHENTICATION_FAILURE";
        }
        if (o == 2024) {
            return "MIP_HA_REGISTRATION_ID_MISMATCH";
        }
        if (o == 2025) {
            return "MIP_HA_MALFORMED_REQUEST";
        }
        if (o == 2026) {
            return "MIP_HA_UNKNOWN_HOME_AGENT_ADDRESS";
        }
        if (o == 2027) {
            return "MIP_HA_REVERSE_TUNNEL_UNAVAILABLE";
        }
        if (o == 2028) {
            return "MIP_HA_REVERSE_TUNNEL_IS_MANDATORY";
        }
        if (o == 2029) {
            return "MIP_HA_ENCAPSULATION_UNAVAILABLE";
        }
        if (o == 2030) {
            return "CLOSE_IN_PROGRESS";
        }
        if (o == 2031) {
            return "NETWORK_INITIATED_TERMINATION";
        }
        if (o == 2032) {
            return "MODEM_APP_PREEMPTED";
        }
        if (o == 2033) {
            return "PDN_IPV4_CALL_DISALLOWED";
        }
        if (o == 2034) {
            return "PDN_IPV4_CALL_THROTTLED";
        }
        if (o == 2035) {
            return "PDN_IPV6_CALL_DISALLOWED";
        }
        if (o == 2036) {
            return "PDN_IPV6_CALL_THROTTLED";
        }
        if (o == 2037) {
            return "MODEM_RESTART";
        }
        if (o == 2038) {
            return "PDP_PPP_NOT_SUPPORTED";
        }
        if (o == 2039) {
            return "UNPREFERRED_RAT";
        }
        if (o == 2040) {
            return "PHYSICAL_LINK_CLOSE_IN_PROGRESS";
        }
        if (o == 2041) {
            return "APN_PENDING_HANDOVER";
        }
        if (o == 2042) {
            return "PROFILE_BEARER_INCOMPATIBLE";
        }
        if (o == 2043) {
            return "SIM_CARD_CHANGED";
        }
        if (o == 2044) {
            return "LOW_POWER_MODE_OR_POWERING_DOWN";
        }
        if (o == 2045) {
            return "APN_DISABLED";
        }
        if (o == 2046) {
            return "MAX_PPP_INACTIVITY_TIMER_EXPIRED";
        }
        if (o == 2047) {
            return "IPV6_ADDRESS_TRANSFER_FAILED";
        }
        if (o == 2048) {
            return "TRAT_SWAP_FAILED";
        }
        if (o == 2049) {
            return "EHRPD_TO_HRPD_FALLBACK";
        }
        if (o == 2050) {
            return "MIP_CONFIG_FAILURE";
        }
        if (o == 2051) {
            return "PDN_INACTIVITY_TIMER_EXPIRED";
        }
        if (o == 2052) {
            return "MAX_IPV4_CONNECTIONS";
        }
        if (o == 2053) {
            return "MAX_IPV6_CONNECTIONS";
        }
        if (o == 2054) {
            return "APN_MISMATCH";
        }
        if (o == 2055) {
            return "IP_VERSION_MISMATCH";
        }
        if (o == 2056) {
            return "DUN_CALL_DISALLOWED";
        }
        if (o == 2057) {
            return "INTERNAL_EPC_NONEPC_TRANSITION";
        }
        if (o == 2058) {
            return "INTERFACE_IN_USE";
        }
        if (o == 2059) {
            return "APN_DISALLOWED_ON_ROAMING";
        }
        if (o == 2060) {
            return "APN_PARAMETERS_CHANGED";
        }
        if (o == 2061) {
            return "NULL_APN_DISALLOWED";
        }
        if (o == 2062) {
            return "THERMAL_MITIGATION";
        }
        if (o == 2063) {
            return "DATA_SETTINGS_DISABLED";
        }
        if (o == 2064) {
            return "DATA_ROAMING_SETTINGS_DISABLED";
        }
        if (o == 2065) {
            return "DDS_SWITCHED";
        }
        if (o == 2066) {
            return "FORBIDDEN_APN_NAME";
        }
        if (o == 2067) {
            return "DDS_SWITCH_IN_PROGRESS";
        }
        if (o == 2068) {
            return "CALL_DISALLOWED_IN_ROAMING";
        }
        if (o == 2069) {
            return "NON_IP_NOT_SUPPORTED";
        }
        if (o == 2070) {
            return "PDN_NON_IP_CALL_THROTTLED";
        }
        if (o == 2071) {
            return "PDN_NON_IP_CALL_DISALLOWED";
        }
        if (o == 2072) {
            return "CDMA_LOCK";
        }
        if (o == 2073) {
            return "CDMA_INTERCEPT";
        }
        if (o == 2074) {
            return "CDMA_REORDER";
        }
        if (o == 2075) {
            return "CDMA_RELEASE_DUE_TO_SO_REJECTION";
        }
        if (o == 2076) {
            return "CDMA_INCOMING_CALL";
        }
        if (o == 2077) {
            return "CDMA_ALERT_STOP";
        }
        if (o == 2078) {
            return "CHANNEL_ACQUISITION_FAILURE";
        }
        if (o == 2079) {
            return "MAX_ACCESS_PROBE";
        }
        if (o == 2080) {
            return "CONCURRENT_SERVICE_NOT_SUPPORTED_BY_BASE_STATION";
        }
        if (o == 2081) {
            return "NO_RESPONSE_FROM_BASE_STATION";
        }
        if (o == 2082) {
            return "REJECTED_BY_BASE_STATION";
        }
        if (o == 2083) {
            return "CONCURRENT_SERVICES_INCOMPATIBLE";
        }
        if (o == 2084) {
            return "NO_CDMA_SERVICE";
        }
        if (o == 2085) {
            return "RUIM_NOT_PRESENT";
        }
        if (o == 2086) {
            return "CDMA_RETRY_ORDER";
        }
        if (o == 2087) {
            return "ACCESS_BLOCK";
        }
        if (o == 2088) {
            return "ACCESS_BLOCK_ALL";
        }
        if (o == 2089) {
            return "IS707B_MAX_ACCESS_PROBES";
        }
        if (o == 2090) {
            return "THERMAL_EMERGENCY";
        }
        if (o == 2091) {
            return "CONCURRENT_SERVICES_NOT_ALLOWED";
        }
        if (o == 2092) {
            return "INCOMING_CALL_REJECTED";
        }
        if (o == 2093) {
            return "NO_SERVICE_ON_GATEWAY";
        }
        if (o == 2094) {
            return "NO_GPRS_CONTEXT";
        }
        if (o == 2095) {
            return "ILLEGAL_MS";
        }
        if (o == 2096) {
            return "ILLEGAL_ME";
        }
        if (o == 2097) {
            return "GPRS_SERVICES_AND_NON_GPRS_SERVICES_NOT_ALLOWED";
        }
        if (o == 2098) {
            return "GPRS_SERVICES_NOT_ALLOWED";
        }
        if (o == 2099) {
            return "MS_IDENTITY_CANNOT_BE_DERIVED_BY_THE_NETWORK";
        }
        if (o == 2100) {
            return "IMPLICITLY_DETACHED";
        }
        if (o == 2101) {
            return "PLMN_NOT_ALLOWED";
        }
        if (o == 2102) {
            return "LOCATION_AREA_NOT_ALLOWED";
        }
        if (o == 2103) {
            return "GPRS_SERVICES_NOT_ALLOWED_IN_THIS_PLMN";
        }
        if (o == 2104) {
            return "PDP_DUPLICATE";
        }
        if (o == 2105) {
            return "UE_RAT_CHANGE";
        }
        if (o == 2106) {
            return "CONGESTION";
        }
        if (o == 2107) {
            return "NO_PDP_CONTEXT_ACTIVATED";
        }
        if (o == 2108) {
            return "ACCESS_CLASS_DSAC_REJECTION";
        }
        if (o == 2109) {
            return "PDP_ACTIVATE_MAX_RETRY_FAILED";
        }
        if (o == 2110) {
            return "RADIO_ACCESS_BEARER_FAILURE";
        }
        if (o == 2111) {
            return "ESM_UNKNOWN_EPS_BEARER_CONTEXT";
        }
        if (o == 2112) {
            return "DRB_RELEASED_BY_RRC";
        }
        if (o == 2113) {
            return "CONNECTION_RELEASED";
        }
        if (o == 2114) {
            return "EMM_DETACHED";
        }
        if (o == 2115) {
            return "EMM_ATTACH_FAILED";
        }
        if (o == 2116) {
            return "EMM_ATTACH_STARTED";
        }
        if (o == 2117) {
            return "LTE_NAS_SERVICE_REQUEST_FAILED";
        }
        if (o == 2118) {
            return "DUPLICATE_BEARER_ID";
        }
        if (o == 2119) {
            return "ESM_COLLISION_SCENARIOS";
        }
        if (o == 2120) {
            return "ESM_BEARER_DEACTIVATED_TO_SYNC_WITH_NETWORK";
        }
        if (o == 2121) {
            return "ESM_NW_ACTIVATED_DED_BEARER_WITH_ID_OF_DEF_BEARER";
        }
        if (o == 2122) {
            return "ESM_BAD_OTA_MESSAGE";
        }
        if (o == 2123) {
            return "ESM_DOWNLOAD_SERVER_REJECTED_THE_CALL";
        }
        if (o == 2124) {
            return "ESM_CONTEXT_TRANSFERRED_DUE_TO_IRAT";
        }
        if (o == 2125) {
            return "DS_EXPLICIT_DEACTIVATION";
        }
        if (o == 2126) {
            return "ESM_LOCAL_CAUSE_NONE";
        }
        if (o == 2127) {
            return "LTE_THROTTLING_NOT_REQUIRED";
        }
        if (o == 2128) {
            return "ACCESS_CONTROL_LIST_CHECK_FAILURE";
        }
        if (o == 2129) {
            return "SERVICE_NOT_ALLOWED_ON_PLMN";
        }
        if (o == 2130) {
            return "EMM_T3417_EXPIRED";
        }
        if (o == 2131) {
            return "EMM_T3417_EXT_EXPIRED";
        }
        if (o == 2132) {
            return "RRC_UPLINK_DATA_TRANSMISSION_FAILURE";
        }
        if (o == 2133) {
            return "RRC_UPLINK_DELIVERY_FAILED_DUE_TO_HANDOVER";
        }
        if (o == 2134) {
            return "RRC_UPLINK_CONNECTION_RELEASE";
        }
        if (o == 2135) {
            return "RRC_UPLINK_RADIO_LINK_FAILURE";
        }
        if (o == 2136) {
            return "RRC_UPLINK_ERROR_REQUEST_FROM_NAS";
        }
        if (o == 2137) {
            return "RRC_CONNECTION_ACCESS_STRATUM_FAILURE";
        }
        if (o == 2138) {
            return "RRC_CONNECTION_ANOTHER_PROCEDURE_IN_PROGRESS";
        }
        if (o == 2139) {
            return "RRC_CONNECTION_ACCESS_BARRED";
        }
        if (o == 2140) {
            return "RRC_CONNECTION_CELL_RESELECTION";
        }
        if (o == 2141) {
            return "RRC_CONNECTION_CONFIG_FAILURE";
        }
        if (o == 2142) {
            return "RRC_CONNECTION_TIMER_EXPIRED";
        }
        if (o == 2143) {
            return "RRC_CONNECTION_LINK_FAILURE";
        }
        if (o == 2144) {
            return "RRC_CONNECTION_CELL_NOT_CAMPED";
        }
        if (o == 2145) {
            return "RRC_CONNECTION_SYSTEM_INTERVAL_FAILURE";
        }
        if (o == 2146) {
            return "RRC_CONNECTION_REJECT_BY_NETWORK";
        }
        if (o == 2147) {
            return "RRC_CONNECTION_NORMAL_RELEASE";
        }
        if (o == 2148) {
            return "RRC_CONNECTION_RADIO_LINK_FAILURE";
        }
        if (o == 2149) {
            return "RRC_CONNECTION_REESTABLISHMENT_FAILURE";
        }
        if (o == 2150) {
            return "RRC_CONNECTION_OUT_OF_SERVICE_DURING_CELL_REGISTER";
        }
        if (o == 2151) {
            return "RRC_CONNECTION_ABORT_REQUEST";
        }
        if (o == 2152) {
            return "RRC_CONNECTION_SYSTEM_INFORMATION_BLOCK_READ_ERROR";
        }
        if (o == 2153) {
            return "NETWORK_INITIATED_DETACH_WITH_AUTO_REATTACH";
        }
        if (o == 2154) {
            return "NETWORK_INITIATED_DETACH_NO_AUTO_REATTACH";
        }
        if (o == 2155) {
            return "ESM_PROCEDURE_TIME_OUT";
        }
        if (o == 2156) {
            return "INVALID_CONNECTION_ID";
        }
        if (o == 2157) {
            return "MAXIMIUM_NSAPIS_EXCEEDED";
        }
        if (o == 2158) {
            return "INVALID_PRIMARY_NSAPI";
        }
        if (o == 2159) {
            return "CANNOT_ENCODE_OTA_MESSAGE";
        }
        if (o == 2160) {
            return "RADIO_ACCESS_BEARER_SETUP_FAILURE";
        }
        if (o == 2161) {
            return "PDP_ESTABLISH_TIMEOUT_EXPIRED";
        }
        if (o == 2162) {
            return "PDP_MODIFY_TIMEOUT_EXPIRED";
        }
        if (o == 2163) {
            return "PDP_INACTIVE_TIMEOUT_EXPIRED";
        }
        if (o == 2164) {
            return "PDP_LOWERLAYER_ERROR";
        }
        if (o == 2165) {
            return "PDP_MODIFY_COLLISION";
        }
        if (o == 2166) {
            return "MAXINUM_SIZE_OF_L2_MESSAGE_EXCEEDED";
        }
        if (o == 2167) {
            return "NAS_REQUEST_REJECTED_BY_NETWORK";
        }
        if (o == 2168) {
            return "RRC_CONNECTION_INVALID_REQUEST";
        }
        if (o == 2169) {
            return "RRC_CONNECTION_TRACKING_AREA_ID_CHANGED";
        }
        if (o == 2170) {
            return "RRC_CONNECTION_RF_UNAVAILABLE";
        }
        if (o == 2171) {
            return "RRC_CONNECTION_ABORTED_DUE_TO_IRAT_CHANGE";
        }
        if (o == 2172) {
            return "RRC_CONNECTION_RELEASED_SECURITY_NOT_ACTIVE";
        }
        if (o == 2173) {
            return "RRC_CONNECTION_ABORTED_AFTER_HANDOVER";
        }
        if (o == 2174) {
            return "RRC_CONNECTION_ABORTED_AFTER_IRAT_CELL_CHANGE";
        }
        if (o == 2175) {
            return "RRC_CONNECTION_ABORTED_DURING_IRAT_CELL_CHANGE";
        }
        if (o == 2176) {
            return "IMSI_UNKNOWN_IN_HOME_SUBSCRIBER_SERVER";
        }
        if (o == 2177) {
            return "IMEI_NOT_ACCEPTED";
        }
        if (o == 2178) {
            return "EPS_SERVICES_AND_NON_EPS_SERVICES_NOT_ALLOWED";
        }
        if (o == 2179) {
            return "EPS_SERVICES_NOT_ALLOWED_IN_PLMN";
        }
        if (o == 2180) {
            return "MSC_TEMPORARILY_NOT_REACHABLE";
        }
        if (o == 2181) {
            return "CS_DOMAIN_NOT_AVAILABLE";
        }
        if (o == 2182) {
            return "ESM_FAILURE";
        }
        if (o == 2183) {
            return "MAC_FAILURE";
        }
        if (o == 2184) {
            return "SYNCHRONIZATION_FAILURE";
        }
        if (o == 2185) {
            return "UE_SECURITY_CAPABILITIES_MISMATCH";
        }
        if (o == 2186) {
            return "SECURITY_MODE_REJECTED";
        }
        if (o == 2187) {
            return "UNACCEPTABLE_NON_EPS_AUTHENTICATION";
        }
        if (o == 2188) {
            return "CS_FALLBACK_CALL_ESTABLISHMENT_NOT_ALLOWED";
        }
        if (o == 2189) {
            return "NO_EPS_BEARER_CONTEXT_ACTIVATED";
        }
        if (o == 2190) {
            return "INVALID_EMM_STATE";
        }
        if (o == 2191) {
            return "NAS_LAYER_FAILURE";
        }
        if (o == 2192) {
            return "MULTIPLE_PDP_CALL_NOT_ALLOWED";
        }
        if (o == 2193) {
            return "EMBMS_NOT_ENABLED";
        }
        if (o == 2194) {
            return "IRAT_HANDOVER_FAILED";
        }
        if (o == 2195) {
            return "EMBMS_REGULAR_DEACTIVATION";
        }
        if (o == 2196) {
            return "TEST_LOOPBACK_REGULAR_DEACTIVATION";
        }
        if (o == 2197) {
            return "LOWER_LAYER_REGISTRATION_FAILURE";
        }
        if (o == 2198) {
            return "DATA_PLAN_EXPIRED";
        }
        if (o == 2199) {
            return "UMTS_HANDOVER_TO_IWLAN";
        }
        if (o == 2200) {
            return "EVDO_CONNECTION_DENY_BY_GENERAL_OR_NETWORK_BUSY";
        }
        if (o == 2201) {
            return "EVDO_CONNECTION_DENY_BY_BILLING_OR_AUTHENTICATION_FAILURE";
        }
        if (o == 2202) {
            return "EVDO_HDR_CHANGED";
        }
        if (o == 2203) {
            return "EVDO_HDR_EXITED";
        }
        if (o == 2204) {
            return "EVDO_HDR_NO_SESSION";
        }
        if (o == 2205) {
            return "EVDO_USING_GPS_FIX_INSTEAD_OF_HDR_CALL";
        }
        if (o == 2206) {
            return "EVDO_HDR_CONNECTION_SETUP_TIMEOUT";
        }
        if (o == 2207) {
            return "FAILED_TO_ACQUIRE_COLOCATED_HDR";
        }
        if (o == 2208) {
            return "OTASP_COMMIT_IN_PROGRESS";
        }
        if (o == 2209) {
            return "NO_HYBRID_HDR_SERVICE";
        }
        if (o == 2210) {
            return "HDR_NO_LOCK_GRANTED";
        }
        if (o == 2211) {
            return "DBM_OR_SMS_IN_PROGRESS";
        }
        if (o == 2212) {
            return "HDR_FADE";
        }
        if (o == 2213) {
            return "HDR_ACCESS_FAILURE";
        }
        if (o == 2214) {
            return "UNSUPPORTED_1X_PREV";
        }
        if (o == 2215) {
            return "LOCAL_END";
        }
        if (o == 2216) {
            return "NO_SERVICE";
        }
        if (o == 2217) {
            return "FADE";
        }
        if (o == 2218) {
            return "NORMAL_RELEASE";
        }
        if (o == 2219) {
            return "ACCESS_ATTEMPT_ALREADY_IN_PROGRESS";
        }
        if (o == 2220) {
            return "REDIRECTION_OR_HANDOFF_IN_PROGRESS";
        }
        if (o == 2221) {
            return "EMERGENCY_MODE";
        }
        if (o == 2222) {
            return "PHONE_IN_USE";
        }
        if (o == 2223) {
            return "INVALID_MODE";
        }
        if (o == 2224) {
            return "INVALID_SIM_STATE";
        }
        if (o == 2225) {
            return "NO_COLLOCATED_HDR";
        }
        if (o == 2226) {
            return "UE_IS_ENTERING_POWERSAVE_MODE";
        }
        if (o == 2227) {
            return "DUAL_SWITCH";
        }
        if (o == 2228) {
            return "PPP_TIMEOUT";
        }
        if (o == 2229) {
            return "PPP_AUTH_FAILURE";
        }
        if (o == 2230) {
            return "PPP_OPTION_MISMATCH";
        }
        if (o == 2231) {
            return "PPP_PAP_FAILURE";
        }
        if (o == 2232) {
            return "PPP_CHAP_FAILURE";
        }
        if (o == 2233) {
            return "PPP_CLOSE_IN_PROGRESS";
        }
        if (o == 2234) {
            return "LIMITED_TO_IPV4";
        }
        if (o == 2235) {
            return "LIMITED_TO_IPV6";
        }
        if (o == 2236) {
            return "VSNCP_TIMEOUT";
        }
        if (o == 2237) {
            return "VSNCP_GEN_ERROR";
        }
        if (o == 2238) {
            return "VSNCP_APN_UNATHORIZED";
        }
        if (o == 2239) {
            return "VSNCP_PDN_LIMIT_EXCEEDED";
        }
        if (o == 2240) {
            return "VSNCP_NO_PDN_GATEWAY_ADDRESS";
        }
        if (o == 2241) {
            return "VSNCP_PDN_GATEWAY_UNREACHABLE";
        }
        if (o == 2242) {
            return "VSNCP_PDN_GATEWAY_REJECT";
        }
        if (o == 2243) {
            return "VSNCP_INSUFFICIENT_PARAMETERS";
        }
        if (o == 2244) {
            return "VSNCP_RESOURCE_UNAVAILABLE";
        }
        if (o == 2245) {
            return "VSNCP_ADMINISTRATIVELY_PROHIBITED";
        }
        if (o == 2246) {
            return "VSNCP_PDN_ID_IN_USE";
        }
        if (o == 2247) {
            return "VSNCP_SUBSCRIBER_LIMITATION";
        }
        if (o == 2248) {
            return "VSNCP_PDN_EXISTS_FOR_THIS_APN";
        }
        if (o == 2249) {
            return "VSNCP_RECONNECT_NOT_ALLOWED";
        }
        if (o == 2250) {
            return "IPV6_PREFIX_UNAVAILABLE";
        }
        if (o == 2251) {
            return "HANDOFF_PREFERENCE_CHANGED";
        }
        return "0x" + Integer.toHexString(o);
    }

    /*  JADX ERROR: Type inference failed with exception
        jadx.core.utils.exceptions.JadxOverflowException: Type update terminated with stack overflow, arg: (r1v105 ??)
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:112)
        */
    public static final java.lang.String dumpBitfield(int r4) {
        /*
            Method dump skipped, instructions count: 4413
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.radio.V1_4.DataCallFailCause.dumpBitfield(int):java.lang.String");
    }
}
