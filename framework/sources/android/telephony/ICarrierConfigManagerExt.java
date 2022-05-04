package android.telephony;

import android.os.PersistableBundle;
/* loaded from: classes3.dex */
public interface ICarrierConfigManagerExt {
    public static final String KEY_CALL_PRESENTATION_MAPPING_STRING_ARRAY = "call_presentation_mapping_string_array";
    public static final String KEY_CARRIER_CUSTOMIZED_NRICON_CONFIG = "carrier_customized_nricon_config";
    public static final String KEY_CARRIER_SHOW_IMS_CONFERENCE_LIST_WITHOUT_CEP_CONFIG = "carrier_show_ims_conference_list_without_cep_config";
    public static final String KEY_CARRIER_SUPPORT_BWP = "carrier_support_bwp";
    public static final String KEY_CARRIER_SUPPORT_NRCA = "carrier_support_nrca";
    public static final String KEY_DPM_FD_DELAY_IDLE_TIME = "dpmFdDelayIdleTime";
    public static final String KEY_DPM_FD_SCREEN_OFF_IDLE_TIME = "dpmFdmScreenOffIdleTime";
    public static final String KEY_DPM_FD_SCREEN_ON_IDLE_TIME = "dpmFdScreenOnIdleTime";
    public static final String KEY_DPM_FD_TETHERING_IDLE_TIME = "dpmFdTetheringIdleTime";
    public static final String KEY_ERLVT_MT_OFF = "config_oplus_erlvt_mt_off_bool";
    public static final String KEY_FORCE_BUILD_MMS_OVER_WIFI_APNS = "force_build_mms_over_wifi_apns";
    public static final String KEY_FR1_SIG_INT_ARRAY = "carrier_volte_fr1_int_array";
    public static final String KEY_FR1_SWITCH_BOOL = "carrier_volte_fr1_bool";
    public static final String KEY_SUPPORT_1X_INCALL_MMI = "support_1x_incall_mmi";
    public static final String KEY_WFC_DISABLE_NRSA_CONFIG = "wfc_disable_nrsa_config";
    public static final String OPLUS_DUAL_LTE_AVAILABLE = "config_oplus_dual_lte_available_bool";

    default void putDefault(PersistableBundle defaults) {
    }
}
