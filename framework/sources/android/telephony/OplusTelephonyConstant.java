package android.telephony;
/* loaded from: classes3.dex */
public interface OplusTelephonyConstant {
    public static final String ACTION_DEFAULT_SIM_REMOVED = "android.intent.action.DEFAULT_SIM_REMOVED";
    public static final String ACTION_DOWNLOAD_CALIBRATION_DATA = "android.intent.action.DOWNLOAD_CALIBRATION_DATA";
    public static final String ACTION_EMT_CALL_STATE_CHANGED = "android.intent.action.ACTION_EMT_CALL_STATE_CHANGED";
    public static final String ACTION_NEW_INCOMING_CALL = "android.intent.action.NEW_INCOMING_CALL";
    public static final String ACTION_NEW_SIM_DETECTED = "android.intent.action.NEW_SIM_DETECTED";
    public static final String ACTION_RADIO_OFF = "android.intent.action.RADIO_OFF";
    public static final String ACTION_SIM_INFO_UPDATE = "oplus.intent.action.SIM_INFO_UPDATE";
    public static final String ACTION_SIM_INSERTED_STATUS = "android.intent.action.SIM_INSERTED_STATUS";
    public static final String ACTION_SIM_STATE_CHANGED = "android.intent.action.SIM_STATE_CHANGED";
    public static final String ACTION_WIFI_FAILOVER_GPRS_DIALOG = "oplus.intent.action_WIFI_FAILOVER_GPRS_DIALOG";
    public static final int ADN_FILE_SIZE = 250;
    public static final int APN_REQUEST_FAILED_DUE_TO_RADIO_OFF = 98;
    public static final String APN_TYPE_CMMAIL = "cmmail";
    public static final int APN_TYPE_DISABLE_ONGOING = 100;
    public static final String APN_TYPE_DM = "dm";
    public static final String APN_TYPE_NET = "net";
    public static final int APN_TYPE_NOT_AVAILABLE_DUE_TO_RECORDS_NOT_LOADED = 99;
    public static final String APN_TYPE_RCSE = "rcse";
    public static final String APN_TYPE_TETHERING = "tethering";
    public static final String APN_TYPE_WAP = "wap";
    public static final int BM_ALL_HW_SUPPORT_CHINA = 42;
    public static final int BM_ALL_HW_SUPPORT_FOREIGN = 43;
    public static final int BM_GSM_1800 = 33;
    public static final int BM_GSM_1900 = 34;
    public static final int BM_GSM_850 = 31;
    public static final int BM_GSM_850_AND_1800 = 38;
    public static final int BM_GSM_850_AND_1900 = 40;
    public static final int BM_GSM_900 = 32;
    public static final int BM_GSM_900_AND_1800 = 39;
    public static final int BM_WCDMA_1900 = 36;
    public static final int BM_WCDMA_2100 = 37;
    public static final int BM_WCDMA_850 = 35;
    public static final int BM_WCDMA_850_AND_1900 = 41;
    public static final boolean CTA_ENABLE = false;
    public static final String DISCONNECT_DATA_FLAG = "disconnectPdpFlag";
    public static final String DORMANT_PROBE_INTERVAL = "prop.oplus.data_dormant_interval";
    public static final int EF_CC = 20259;
    public static final int EMT_CALL_STATE_ACTIVE = 2;
    public static final int EMT_CALL_STATE_ALERTING = 5;
    public static final int EMT_CALL_STATE_DIALING = 1;
    public static final int EMT_CALL_STATE_ERROR = -1;
    public static final int EMT_CALL_STATE_HOLDING = 3;
    public static final int EMT_CALL_STATE_IDLE = 0;
    public static final int EMT_CALL_STATE_UNKNOWN = 4;
    public static final int ENCODING_7BIT_LOCKING = 12;
    public static final int ENCODING_7BIT_LOCKING_SINGLE = 13;
    public static final int ENCODING_7BIT_SINGLE = 11;
    public static final int EVENT_ADN_LOAD_ALL_DONE = 95;
    public static final int EVENT_ADN_SIZE_DONE = 990;
    public static final int EVENT_CALL_FORWARD_DELAY = 8888;
    public static final int EVENT_COPY_TEXT_MESSAGE_DONE = 110;
    public static final int EVENT_EM_CALL_STATUS_CHANGED = 115;
    public static final int EVENT_EM_DIAL_DONE = 107;
    public static final int EVENT_EM_GET_CURRENT_CALLS_DONE = 108;
    public static final int EVENT_EM_HANGUP_DONE = 109;
    public static final int EVENT_GET_ADN_FIELD_INFO_DONE = 102;
    public static final int EVENT_GET_ALL_SPACE = 101;
    public static final int EVENT_GET_PBC_SIZE_DONE = 94;
    public static final int EVENT_GET_SMSC = 101;
    public static final int EVENT_GET_USED_SPACE = 100;
    public static final int EVENT_GO_DORMANT_DONE = 7777;
    public static final int EVENT_READ_CC_DONE = 93;
    public static final int EVENT_READ_PBC_DONE = 92;
    public static final int EVENT_SEND_UPLINK_DATA_DONE = 114;
    public static final int EVENT_SET_SMSC = 100;
    public static final int EVENT_UPDATE_ADN_DONE = 90;
    public static final int EVENT_UPDATE_ANR_DONE = 993;
    public static final int EVENT_UPDATE_EMAIL_DONE = 991;
    public static final int EVENT_UPDATE_IAP_DONE = 992;
    public static final int EVENT_UPDATE_PBC_DONE = 91;
    public static final int EVENT_USIM_ANR_LOAD_DONE = 5;
    public static final String EXTRA_CALIBRATION_DATA = "calibrationData";
    public static final String EXTRA_EMT_CALL_NUMBER = "number";
    public static final String EXTRA_EMT_CALL_STATE = "state";
    public static final String EXTRA_INCOMING_NUMBER = "PHONE_NUMBER";
    public static final String EXTRA_PARAMS_VALIDITY_PERIOD = "validity_period";
    public static final String FEATURE_ENABLE_CMMAIL = "enableCMMAIL";
    public static final String FEATURE_ENABLE_DM = "enableDM";
    public static final String FEATURE_ENABLE_NET = "enableNET";
    public static final String FEATURE_ENABLE_WAP = "enableWAP";
    public static final String GEMINI_DEFAULT_SIM_MODE = "persist.radio.default_sim_mode";
    public static final String GEMINI_DEFAULT_SIM_PROP = "persist.radio.default_sim";
    public static final String GEMINI_GPRS_TRANSFER_TYPE = "gemini.gprs.transfer.type";
    public static final int GEMINI_SIM_1 = 0;
    public static final int GEMINI_SIM_2 = 1;
    public static final int GEMINI_SIM_3 = 2;
    public static final int GEMINI_SIM_4 = 3;
    public static final String GEMINI_SIM_ID_KEY = "simId";
    public static final String GEMINI_SIM_NUM_PROP = "persist.gemini.sim_num";
    public static final int GEMINI_SIP_CALL = -1;
    public static final int HEADER_SIZE = 8;
    public static final String INTENT_KEY_ICC_STATE = "state";
    public static final int INT_SIZE = 4;
    public static final String IS_VT_CALL = "isVtCall";
    public static final String LOCK_TAG = "MMDATA_UP";
    public static final int MAXNUM_RETRY_SIMRECORD = 3;
    public static final int MAX_USER_DATA_BYTES_WITH_DATA_SMS_HEADER = 133;
    public static final int MAX_USER_DATA_BYTES_WITH_DATA_SMS_HEADER_AND_CONCATENATED_SMS_HEADER = 128;
    public static final String MULTI_SIM_ID_KEY = "simid";
    public static final int NV_SERVICE_DOMAIN_PREF_I = 850;
    public static final int OPLUS_MAX_PB_EMAIL_LENGTH = 30;
    public static final int OPLUS_MAX_PB_NAME_LENGTH = 14;
    public static final int OPLUS_MAX_PB_NUMBER_LENGTH = 20;
    public static final int OPLUS_SIMPHONEBOOK_STATE_ERROR = -1;
    public static final int OPLUS_SIMPHONEBOOK_STATE_NOT_READY = 0;
    public static final int OPLUS_SIMPHONEBOOK_STATE_READY = 1;
    public static final int PHB_POLL_TIMEOUT = 240000;
    public static final int PHB_START_POLL_TIME1 = 120000;
    public static final int PHB_START_POLL_TIME2 = 240000;
    public static final int POLL_DORMANT_IDLE_MAX_TIMES = 1;
    public static final int POLL_DORMANT_MAX_TIMES = 2;
    public static final int POLL_DORMANT_MILLIS = 6000;
    public static final int POLL_MAX_TIME = 10;
    public static final int PORT_WAP_VCAL_S = 9207;
    public static final int PORT_WAP_VCARD = 9204;
    public static final String PROPERTY_CS_NETWORK_TYPE = "gsm.cs.network.type";
    public static final String PROPERTY_CS_NETWORK_TYPE_2 = "gsm.cs.network.type.2";
    public static final String PROPERTY_DATA_NETWORK_TYPE_2 = "gsm.network.type.2";
    public static final String PROPERTY_GSM_SIM_INSERTED = "gsm.sim.inserted";
    public static final String PROPERTY_ICC_OPERATOR_ALPHA_2 = "gsm.sim.operator.alpha.2";
    public static final String PROPERTY_ICC_OPERATOR_DEFAULT_NAME = "gsm.sim.operator.default-name";
    public static final String PROPERTY_ICC_OPERATOR_DEFAULT_NAME_2 = "gsm.sim.operator.default-name.2";
    public static final String PROPERTY_ICC_OPERATOR_ISO_COUNTRY_2 = "gsm.sim.operator.iso-country.2";
    public static final String PROPERTY_ICC_OPERATOR_NUMERIC_2 = "gsm.sim.operator.numeric.2";
    public static final String PROPERTY_ICC_OPERATOR_PNN_NAME = "gsm.sim.operator.pnn.name";
    public static final String PROPERTY_OPERATOR_ALPHA_2 = "gsm.operator.alpha.2";
    public static final String PROPERTY_OPERATOR_ISMANUAL_2 = "operator.ismanual.2";
    public static final String PROPERTY_OPERATOR_ISO_COUNTRY_2 = "gsm.operator.iso-country.2";
    public static final String PROPERTY_OPERATOR_ISROAMING_2 = "gsm.operator.isroaming.2";
    public static final String PROPERTY_OPERATOR_NUMERIC_2 = "gsm.operator.numeric.2";
    public static final String PROPERTY_ROAMING_INDICATOR_NEEDED = "gsm.roaming.indicator.needed";
    public static final String PROPERTY_ROAMING_INDICATOR_NEEDED_2 = "gsm.roaming.indicator.needed.2";
    public static final String PROPERTY_SIM_INFO_READY = "gsm.siminfo.ready";
    public static final String PROPERTY_SIM_STATE_2 = "gsm.sim.state.2";
    public static final int QCRILHOOK_BASE = 524288;
    public static final int QCRILHOOK_GO_DORMANT = 524291;
    public static final int QCRILHOOK_NV_READ = 524289;
    public static final int QCRILHOOK_NV_WRITE = 524290;
    public static final String REASON_GPRS_ATTACHED_TIMEOUT = "gprsAttachedTimeout";
    public static final String REASON_NO_SUCH_PDP = "noSuchPdp";
    public static final String REASON_ON_RADIO_AVAILABLE = "onRadioAvailable";
    public static final String REASON_ON_RECORDS_LOADED = "onRecordsLoaded";
    public static final String REASON_PDP_NOT_ACTIVE = "pdpNotActive";
    public static final String REASON_POLL_STATE_DONE = "pollStateDone";
    public static final int RESULT_BLOCK = 1;
    public static final int RESULT_ERROR_INVALID_ADDRESS = 8;
    public static final int RESULT_ERROR_SIM_MEM_FULL = 7;
    public static final int RESULT_ERROR_SUCCESS = 0;
    public static final int RESULT_PASS = 0;
    public static final int SIM_INDICATOR_ABSENT = 0;
    public static final int SIM_INDICATOR_CONNECTED = 7;
    public static final int SIM_INDICATOR_INVALID = 3;
    public static final int SIM_INDICATOR_LOCKED = 2;
    public static final int SIM_INDICATOR_NORMAL = 5;
    public static final int SIM_INDICATOR_RADIOOFF = 1;
    public static final int SIM_INDICATOR_ROAMING = 6;
    public static final int SIM_INDICATOR_ROAMINGCONNECTED = 8;
    public static final int SIM_INDICATOR_SEARCHING = 4;
    public static final int SIM_INDICATOR_UNKNOWN = -1;
    public static final String SIM_PLUGIN = "PLUGIN";
    public static final String SIM_PLUGOUT = "PLUGOUT";
    public static final int SIM_STATE_SIMREFRESH = 100;
    public static final int SIM_STATE_SWICTHCARD = 99;
    public static final int SRV_DOMAIN_PREF_SIZE = 4;
    public static final String SUBSCRIPTION_KEY = "subscription";
    public static final int TOTAL_SIM_OPLUS_COUNT = 4;
    public static final int mMatchLen = -1;
    public static final String mOemIdentifier = "QOEMHOOK";
    public static final int mHeaderSize = mOemIdentifier.length() + 8;

    /* loaded from: classes3.dex */
    public enum ApCmd2ModemType {
        AP_2_MODEM_NV_BACKUP(1),
        AP_2_MODEM_NV_RESTORE(2),
        AP_2_MODEM_NV_CHECK(3),
        AP_2_MODEM_SET_ANTENNA(4),
        AP_2_MODEM_GET_ANTENNA_POS(5),
        AP_2_MODEM_DISABLE_TUNER_ACL(6),
        AP_2_MODEM_SAR_SENSOR_OPERATE(7),
        AP_2_MODEM_SAR_FUC_OPERATOR(8),
        AP_2_MODEM_OEM_SET_MCC(9),
        AP_2_MODEM_OEM_GET_MCC(10),
        AP_2_MODEM_OEM_SET_DSI(11),
        AP_2_MODEM_OEM_GET_DSI(12),
        AP_2_MODEM_OEM_SET_SSR(13),
        AP_2_MODEM_OEM_GET_NR_BAND_LOCK(14),
        AP_2_MODEM_OEM_SET_NR_BAND_LOCK(15),
        AP_2_MODEM_OEM_SET_BAR_CELL_INFO(16),
        AP_2_MODEM_OEM_SET_NR_STATE(17),
        AP_2_MODEM_OEM_SET_SAR_STATE(18),
        AP_2_MODEM_OEM_GET_SAR_STATE(19),
        AP_2_MODEM_NV_MISC_INFO(20),
        AP_2_MODEM_NV_WRITE_ADRC_CONFIG(21),
        AP_2_MODEM_NV_READ_ADRC_CONFIG(22),
        AP_2_MODEM_OEM_GET_SIMLOCK_STATUS(23),
        AP_2_MODEM_OEM_GET_SIMLOCK_CATEGORY_DATA(24),
        AP_2_MODEM_OEM_GET_SIMLOCK_FUSE_STATUS(25),
        AP_2_MODEM_OEM_GET_SIMLOCK_RSU_MODE(26),
        AP_2_MODEM_MAX_CMD(Integer.MAX_VALUE);
        
        private int value;

        ApCmd2ModemType(int value) {
            this.value = 0;
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    /* loaded from: classes3.dex */
    public enum ApCmd2QcrilType {
        QCRILHOOK_E_NONE(0),
        QCRILHOOK_SET_NR_5G_CDRX_CMD(1),
        QCRILHOOK_GET_NR_INFO(2),
        QCRILHOOK_SET_5G_BAND_PREF(3),
        QCRILHOOK_SET_MODEM_ENDC_FEATURE(4),
        QCRILHOOL_RESET_IMS_REG_TIMER(22),
        QCRILHOOK_MAX_CMD(Integer.MAX_VALUE);
        
        private int value;

        ApCmd2QcrilType(int value) {
            this.value = 0;
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }
}
