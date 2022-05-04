package com.oplus.content;

import com.oplus.annotation.OplusFeature;
import com.oplus.annotation.OplusFeaturePermission;
import oplus.Manifest;
/* loaded from: classes4.dex */
public interface IOplusFeatureConfigList {
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String DISABLE_FEATURE_TORCH_HELPER = "oplus.software.powerkey_disbale_turnoff_torch";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_1X_BSR_DDS_SWITCH = "oplus.software.radio.1x_bsr_dds_swtich";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_1X_INCALL_MMI_CODE_DISABLED = "oplus.software.radio.1X_incall_mmi_code_disabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_5G_BAND_CFG = "oplus.software.radio.5g_band_cfg";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_5G_ICON_JP_REQUIREMENT = "oplus.software.5g_icon_jp_requirement";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_5G_SUPPORT = "oplus.software.radio.support_5g";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_ACCELERATE_THREAD = "oplus.software.radio.accelerate";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_ADD_MORE_MULTIAPP = "oplus.software.multiapp.add_more_multiapps";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_ADFR_SUPPORT = "oplus.software.display.adfr";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_AIR_INTERFACE_DETECT_SUPPORT = "oplus.software.radio.air_interface_detect_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_ALERT_SLIDER = "oplus.software.audio.alert_slider";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_ALIGN_ALARM_SUPPORT = "oplus.software.power.align_alarm";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_ANTIROOT_DISABLED = "oplus.software.antiroot_disabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_AOD_PREVENT_BURN_NEW_MECH = "oplus.software.display.aod_prevent_burn_new_mech";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_AOD_PREVENT_BURN_SUPPORT = "oplus.software.display.aod_prevent_burn_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_AOD_RAMLESS_SUPPORT = "oplus.software.display.aod_ramless_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_AOD_SUPPORT = "oplus.software.display.aod_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_AOD_TRANSPARENT = "oplus.software.display.aod_transparent";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_AOL_SCENE_TO_MODEM = "oplus.software.radio.aol_scene_to_modem";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_AON_ANSWERPHONE_SUPPORT = "oplus.software.aon_phone_answerphone";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_AON_ANT_PEEP_DISABLE_SUPPORT = "oplus.software.aon_ant_peep_disable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_AON_AUTO_NONE_SCREEN_OFF_DISABLE_SUPPORT = "oplus.software.aon_auto_none_screen_off_disable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_AON_GESTUREUI_SUPPORT = "oplus.software.aon_gestureui_enable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_AON_GESTURE_PRESS_SUPPORT = "oplus.software.aon_gesture_press";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_AON_HARDWARE_SUPPORT = "oplus.hardware.aon_enable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_AON_LUMINANCE_CONTROL = "oplus.software.aon_luminance_control";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_AON_PHONE_CAMERA_GESTURE_RECOGNITION_SUPPORT = "oplus.software.aon_phone_camera_gesture_recognition";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_AON_PHONE_MUTE_SUPPORT = "oplus.software.aon_phone_mute";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_AON_PHONE_SUPPORT = "oplus.software.aon_phone_enable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_AON_SOFTWARE_SUPPORT = "oplus.software.aon_enable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_AON_SUB_CAM_SUPPORT = "oplus.software.aon_sub_cam_enable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_APN_RUS_UPDATE = "oplus.software.radio.apn_rus_update_enabled";
    @OplusFeature(OplusFeature.OplusFeatureType.PERSIST_FEATURE)
    @OplusFeaturePermission(Manifest.permission.OPLUS_FEATURE)
    public static final String FEATURE_APP_ACCESS_NFC = "oplus.software.nfc.app_access_nfc";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_APP_REDUCE_BRIGHTNESS_DISABLE = "oplus.software.display.disable_app_reduce_brightness_for_engineering";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_APP_RESOLUTION_DEFAULT = "oplus.software.app_resolution_auto";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_APP_RESOLUTION_DEFAULT_CONFIG = "oplus.software.resolution_default_config";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_APP_RESOLUTION_SWITCH = "oplus.software.app_resolution_switch";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_APTG_FORCE_ENABLE_ROAMING = "oplus.software.radio.aptg_force_enable_roaming";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_AUDIOEFFECT_SUPPORT = "oplus.software.audio.audioeffect_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_AUTOBRIGHTCTL_ANIMATION_SUPPORT = "oplus.software.display.autobrightctl_animation_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_AUTOLIGHT_PSENSOR_NOTSUPPORT = "oplus.software.display.autolight_psensor_notsupport";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_AUTOTIMEUPDATE_FORCE = "oplus.software.country.autotimeupdate_force";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_AUTO_DISABLE_SMART_WFC_SA_CONTROLLER = "oplus.software.radio.auto_disable_smart_wfc_sa_controller";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_AUTO_ENABLE_SMART_WFC_SA_CONTROLLER = "oplus.software.radio.auto_enable_smart_wfc_sa_controller";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BACKGROUND_STREAM_SERVICE_ENABLED = "oplus.software.background_stream_tileservice_enabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BACK_TOUCH_FINGERPRINT = "oplus.software.fingeprint_back_touch";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BENCHMARK_SUPPORT = "oplus.software.benchmark.support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BIND_SMALL_CORE = "oplus.software.bind_small_core";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BRIGHTNESS_LOWLEVEL_SCREEN = "oplus.software.display.brightness_lowlevel_screen";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BRIGHTNESS_MODE_AUTOMATIC = "oplus.software.display.brightness_mode_automatic";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BROWSER_HARMONYNET = "oplus.software.browser.harmonynet";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BT_ABNORMAL_DETECT_DISABLE = "oplus.software.bt.abnormal_detect_disable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BT_ABSVOLUME_SUPPORT = "oplus.software.bt.absvolume_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BT_ABSVOLUME_SUPPORT_DISABLE = "oplus.software.bt.absvolume_support_disable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BT_APCF_THRESHOLD = "oplus.software.bt.apcf_threshold";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BT_API_CUSTOMIZE_DISABLE = "oplus.software.bt.api_customize_disable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BT_AUDIO_GUARD_DISABLE = "oplus.software.bt.audio_guard_disable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BT_BINAURAL_RECORD = "oplus.software.bt.binaural_record";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BT_BLE_SCAN_STRATEGYMODE = "oplus.software.bt.ble_scan_strategymode";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BT_CONNECT_HELP = "oplus.software.bt.connect.helper";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BT_DYNAMIC_BLACKLIST_DWM_DISABLE = "oplus.software.bt.dynamic_blacklist_dwm_disable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BT_HCI_LOG_DISABLE = "oplus.software.bt.hcilog_disable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BT_IDENTIFICATION_DISABLE = "oplus.software.bt.identification_disable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BT_IGNORE_LOCATIONMODE_DISABLE = "oplus.software.bt.ignore_locationmode_disable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BT_IOT_ENABLELOGGING_DISABLE = "oplus.software.bt.iot_enablelogging_disable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BT_OIDT_DISABLE = "oplus.software.bt.oidt_disable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BT_ONLY_SUPPORT_CN = "oplus.software.bt.help.region_CN";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BT_PROFILE_TRAKCER_DISABLE = "oplus.software.bt.profile_tracker_disable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BT_RMTDEVICE_REPORT_DISABLE = "oplus.software.bt.rmtdevice_report_disable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BT_RSSI_RANGE_SUPPORT = "oplus.software.bt.rssi_range_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BT_RSSI_RANGE_SUPPORT_DISABLE = "oplus.software.bt.rssi_range_support_disable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BT_RUS_DISABLE = "oplus.software.bt.rus_disable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BT_SAR = "oplus.software.bt.sar_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BT_SSR_DUMP_DISABLE = "oplus.software.bt.ssrdump_disable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BT_SWITCH_LOG_DISABLE = "oplus.software.bt.switchlog_disable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BT_TETHERING_DENIED = "oplus.software.connectivity.tethering_denied";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BT_VENDOR_AT_COMMAND_DISABLE = "oplus.software.bt.vendor_at_command_disable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_BUSINESS_FLASHBACK = "oplus.business.flashback";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_C303_DEVICE = "oplus.software.carrier.c303_device";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_C427_DEVICE = "oplus.software.carrier.c427_device";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_CALL_ASSISTANT_SUPPORT = "oplus.software.audio.call_assistant_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_CALL_FORWARDING_NUM_EXT_DISABLED = "oplus.software.radio.call_forwarding_num_ext_disalbed";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_CALL_TRANSLATOR_SUPPORT = "oplus.software.audio.call_translator_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_CAMERA_DROP_DETECTION = "oplus.software.camera.dropdetection_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_CAMERA_HEIC_SUPPORT = "oplus.software.camera.heic_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_CDMACW_FILTER_DISABLED = "oplus.software.radio.cdmacw_filter_disabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_CHANGE_PACKAGES_STATE = "oplus.software.change_packages_state";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_CHECK_COTA_MOUNT_WHILE_SETUPWIZARD = "oplus.software.cota.check_mount_setupwizard";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_CINEMA_MODE_SUPPORT = "oplus.software.display.cinema_mode_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_CLIENT_HIDE_STORAGE = "oplus.all.client_hide_storage";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_CLIPBOARD_NOTIFY_CONTROL_ENABLE = "oplus.software.clipboard_notify_enabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_CLIR_DEFAULT_SUPPRESSION = "oplus.software.radio.clir_default_suppression";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_CLOSEEYE_FACE = "oplus.software.face_closeeye_detect";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_CMCC_DM = "oplus.software.radio.cmcc_dm";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_COLORFUL_MODE_SUPPORT = "oplus.software.display.colorful_mode_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_COLORMODE_CALIBRATE_P3_D65_SUPPORT = "oplus.software.display.colormode_calibrate_p3_65_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_COLORMODE_CALIBRATE_SUPPORT = "oplus.software.display.colormode_calibrate_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_COLORMODE_OLED_SUPPORT = "oplus.software.display.colormode_oled_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_COLORX_SUPPORT = "oplus.software.colorx.support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_COMM_SCENE_SWITCH_ENABLE = "oplus.software.radio.comm_scene_switch_enable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_CONNECTIVITY_REGION_CN = "oplus.software.connectivity.region_CN";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_CONNECTIVITY_REGION_JP = "oplus.software.connectivity.region_JP";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_CONTINUE_TO_USE_NEW_PACKAGE = "oplus.software.continue_to_use_new_package";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_COOLEFECT_COOLEX_SUPPORT = "oplus.software.coolex.support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_CTA_SUPPORT = "oplus.software.cta.support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_CUSTOM_DWB_CURVE_SUPPORT = "oplus.software.display.custom_dwb_curve";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_CUSTOM_ECCLIST_WRITABLE = "oplus.software.radio.custom_ecclist_writable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_CUSTOM_PLMN_FOR_SPECIAL_OPERATOR = "oplus.software.radio.custom_plmn_for_special_operator";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DATA_AVOID_DATA_ICON_DISABLED = "oplus.software.radio.data_avoid_data_icon_disabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DATA_BLOCK_NONDDS_IMS_PAKG = "oplus.software.radio.block_nondds_pck";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DATA_DEFAULT_AS_DUN_APN_DISABLED = "oplus.software.radio.data_default_as_dun_disabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DATA_DNS_SPEED_UP = "oplus.software.radio.dns_speed_up";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DATA_DYNAMIC_ENABLE = "oplus.software.radio.data_dynamic_enable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DCBACKLIGHT_SUPPORT = "oplus.software.display.dcbacklight_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DDS_SWITCH = "oplus.software.radio.dds_switch";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DEFAULTAPP_OPLUS_POLICY_ENABLED = "oplus.software.defaultapp.color_policy_enabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DEFAULTAPP_REMOVE_FORCE_LAUNCHER = "oplus.software.defaultapp.remove_force_launcher";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DEFAULTAPP_SET_CHROME_BROWSER = "oplus.software.defaultapp.set_chrome_browser";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DEFAULT_TOP_DISPLAY = "oplus.software.display.default_top_display";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DELAY_AML_SMS = "oplus.software.radio.delay_aml_sms";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DELAY_SMS_WHEN_BROADCASTING = "oplus.software.radio.delay_sms_when_broadcasting";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DEVICEIDLE_NETWORK_OPT = "oplus.software.deviceidle_network_optimization";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DIABLE_DIRAC_FOR_ENGINEERING = "oplus.software.audio.disable_dirac_for_engineering";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DIABLE_DOLBY_FOR_ENGINEERING = "oplus.software.audio.disable_dolby_for_engineering";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DIAGNOSE_PARSE_NR_CELL_SUPPORT = "oplus.software.radio.diagnose_parse_nr_cell_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DIRAC_A2DP_SUPPORT = "oplus.software.audio.dirac.a2dp.support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DIRAC_BLACKLIST_SUPPORT = "oplus.software.audio.dirac.blacklist.support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DIRAC_SUPPORT = "oplus.software.audio.dirac_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DIRAC_V2_SUPPORT = "oplus.software.audio.dirac_v2_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DISABLE_ALL_SA_BACKOFF = "oplus.software.radio.disable_all_sa_backoff";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DISABLE_AUTO_NETWORK_SELECT = "oplus.software.radio.disable_auto_network_select";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DISABLE_CLEAN_EU = "oplus.software.phonemanager_disable_clean_eu";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DISABLE_CLEAN_JP = "oplus.software.phonemanager_disable_clean_jp";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DISABLE_CN_GMS = "oplus.software.disable_cn_gms";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DISABLE_FIRST_FEATURE_LOAD = "oplus.software.radio.disable_first_feature_load";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DISABLE_KEYLOG_PRINT = "oplus.software.radio.disable_keylog_print";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DISABLE_NR5G_CAUSE_5G_DUMP = "oplus.software.radio.disable_5g_nr_5gdump";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DISABLE_NW_LIMIT_NOTIFY = "oplus.software.radio.disable_nw_limit_notify";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DISABLE_POWERSAVE = "oplus.software.power.disable_power_save";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DISABLE_SMALLWINDOW_LEATHER = "oplus.software.disable_smallwindow_leather";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DISPLAY_PLMN_SPN = "oplus.software.radio.display_plmn_spn";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DITO_CT_ATUO_IMS_REG = "oplus.software.radio.dito_ct_auto_ims_reg";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DOLBY_SUPPORT = "oplus.software.audio.dolby_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DRM_SUPPORT = "oplus.software.video.drm_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DUALHEADPHONE_SUPPORT = "oplus.software.audio.dualheadphone";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DUALHEADPHONE_SUPPORT_LITE = "oplus.software.audio.dualheadphone.lite";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DUAL_LIGHTSENSOR_SUPPORT = "oplus.software.display.dual_lightsensor_support";
    @OplusFeature(OplusFeature.OplusFeatureType.PERSIST_FEATURE)
    public static final String FEATURE_DUAL_NR_ENABLED = "oplus.software.radio.dual_nr_enabled";
    @OplusFeature(OplusFeature.OplusFeatureType.PERSIST_FEATURE)
    public static final String FEATURE_DUAL_NR_TOGGLE_ENABLED = "oplus.software.radio.dual_nr_toggle_enabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DUAL_SIMS_SMART5G_ENABLED = "oplus.software.radio.dual_sims_smart5g_enabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DUAL_SPEAKER_SUPPORT = "oplus.software.audio.dualspk_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DUPLICATE_PERMISSION_INSTALL = "oplus.software.duplicate_permission_install";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_DYNAMIC_FPS_SWITCH = "oplus.software.display.dynamic_fps_switch";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_ECC_CSFB_UPDATE_RAT = "oplus.software.radio.ecc_csfb_update_rat";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_ECC_SOLUTION_DISABLED = "oplus.software.radio.ecc_solution_disabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_ELEV_AUTOMATION = "oplus.software.radio.elev_automation";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_ELEV_GW_NDDS = "oplus.software.radio.elev_ndds";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_ENABLE_DIALING_NULBER_CHECK = "oplus.software.radio.enable_cf_dialing_nulber_check";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_ENABLE_OEM_LOG = "oplus.software.radio.enable_oem_log";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_ENABLE_SA_WEAK_SIGNAL_BACKOFF = "oplus.software.radio.enable_weak_signal_backoff";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_ENABLE_TELCEL_COSTOMIZE_NAME = "oplus.software.radio.enable_telcel_costomize_name";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_ENDC_NR_SIGNAL_OPTIMIZATION = "oplus.software.radio.nsa_signal_optim";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_ENVELOPE_ASSISTANT_ENABLE = "oplus.software.notification.envelope_assistant_enable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_EXIT_INFO_RECORD = "oplus.software.application_exit_info_detect";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_EXP_NOTSHOW_STK_BOOTWIZARD = "oplus.software.radio.EXP_NOTSHOW_STK_BOOTWIZARD";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_EXP_OPERATOR_SWITCH_ENABLE = "oplus.software.radio.exp_operator_switch";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_FAST_DORMANCY = "oplus.software.radio.fast_dormancy";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_FBE_SDP = "oplus.software.fbe.sdp";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_FEMTOCELL_UI = "oplus.software.radio.usv_femtocell_ui";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_FETCH_NUMBER_COUNTRY_NAME = "oplus.software.radio.fetch_number_country_name";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_FINGERPRINT_DISABLE_DIMLAYER = "oplus.software.display.fingerprint_disable_dimlayer";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_FINGERPRINT_SHUTTER = "oplus.software.fingerprint.shutter";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_FIVE_SIGNAL_BAR = "oplus.software.five_signal_bar";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_FOLD = "oplus.hardware.type.fold";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_FONT_OP_SANS = "oplus.software.font.op_sans";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_FORCE_FCC_SAR_REGION = "oplus.software.radio.force_fcc_sar";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_FORWARDLY_FREEZE = "oplus.software.forwardly_freeze";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_FRONT_PRESS_FINGERPRINT = "oplus.software.fingeprint_front_press";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_FRONT_TOUCH_FINGERPRINT = "oplus.software.fingeprint_front_touch";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_FRONT_TPPROTECT_FINGERPRINT = "oplus.software.fingeprint_front_tpprotect";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_GAMESPACE_MT_OPTIMIZATION = "oplus.software.radio.mt_optimization";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_GAME_ANTI_MISTOUCH_SUPPORT = "oplus.intelligent.anti.touch.feature";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_GAME_BACKOFF_SA = "oplus.software.radio.game_backoff_sa";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_GAME_ENGINE_VIBRATE_V1_SUPPORT = "oplus.software.game_engine_vibrator_v1.support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_GAME_HAPTIC_VIBRATE_V1_SUPPORT = "oplus.software.haptic_vibrator_v1.support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_GAME_HAPTIC_VIBRATE_V2_SUPPORT = "oplus.software.haptic_vibrator_v2.support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_GAME_IMS_REG = "oplus.software.radio.game_ims_reg";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_GAME_JOYSTICK_SUPPORT = "oplus.software.joystick.game_joystick_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_GAME_OPLUS_PLUS_V2_SUPPORT = "oplus.software.display.game_color_plus_v2_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_GAME_SPACE_TOOL_BOX = "oplus.software.game_space_tool_box";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_GAME_TOUCH_ADJUSTER_SUPPORT = "oplus.software.game.touch_adjuster_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_GDVIBRATOR_SUPPORT = "oplus.software.vibrator_gdvibrator";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_GEME_VOLUMEMUTE_SUPPORT = "oplus.software.audio.game_volumemute_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_GEOIPTIME = "oplus.software.radio.geoiptime_enabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_GESTUREUI_FOR_SCREEN_OFF_SHORTHAND = "oplus.software.screen_off_shorthand";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_GESTURE_PROXIMITYSENSOR = "oplus.software.gesture_proximitysensor";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_GOOGLE_MESSAGE_CONFIG_ENABLE = "oplus.software.radio.google_message_config_enable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_GPS_UST_E911 = "oplus.software.gps.ust_e911";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_GT_MODE_SUPPORT_OPTIMIZE = "oplus.software.support.gt.mode";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_GUARDELF_SHOW_DELUSIVE_MEMORY = "oplus.software.battery.show_confused_hardwareinfo";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_GUARDELF_SUPPORT = "oplus.software.power.guardelf";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_HANS_RESTRICTION = "oplus.software.hans_restriction";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_HARDWARE_RC_SUPPORT = "oplus.software.display.enable_hardware_roundcorner";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_HDR_ENHANCE_BRIGHTNESS = "oplus.software.display.hdr_enhance_brightness_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_HEARING_HEALTH_SUPPORT = "oplus.software.audio.hearing_health_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_HEYCAST_INTERNAL_RECORD_SUPPORT = "oplus.software.audio.heycast_internal_record_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_HIDDEN_API_COLLECT = "oplus.software.enable_hidden_api_collect";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_HIDE_ALERT_WINDOW_NOTIFICATION = "oplus.software.floatwindow_hide_alertwindow_notification";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_HQV_FRC_SUPPORT = "oplus.software.video.hqv_frc_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_HQV_SUPPORT = "oplus.software.video.hqv_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_HVOLTE_DISABLED = "oplus.software.radio.hvolte_disabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_HW_MANUFACTURER_MTK = "oplus.software.hw.manufacturer.mtk";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_HW_MANUFACTURER_QUALCOMM = "oplus.software.hw.manufacturer.qualcomm";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_HW_MIPI_FAIL_SUPPORT = "oplus.software.radio.hw.mipi.fail.support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_IGNORE_RETRICTED_ENABLED = "oplus.software.radio.ignore_dcnr_retricted_enable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_IGNORE_SIM_VM_NUMBER = "oplus.software.radio.ignore_sim_vm_number_disabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_IMS_GUARD = "oplus.software.radio.ims_guard";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_INEXACT_ALARM_SUPPORT = "oplus.software.power.inexact_alarm";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_INIT_TMOBILE_PROPERTY = "oplus.software.radio.init_tmobile_property";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_INPUTMETHOD_BAIDU_DEFAULT = "oplus.software.inputmethod.baidu.default";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_INPUTMETHOD_CN = "oplus.software.inputmethod.cn";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_INPUTMETHOD_EMOJI_DEFAULT = "oplus.software.inputmethod.emoji.default";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_INPUTMETHOD_GBOARD_DEFAULT = "oplus.software.inputmethod.gboard.default";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_INPUTMETHOD_SOGOU_DEFAULT = "oplus.software.inputmethod.sogou.default";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_INTELLIGENT_OPLUS_TEMPERATURE_SUPPORT = "oplus.software.display.intelligent_color_temperature_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_INTERNAL_RECORD_SUPPORT = "oplus.software.audio.internal_record_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_JOB_CUSTOMIZEFUNC_SUPPORT = "oplus.software.power.jobscheduler";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_JP_SIGNAL_STRENGTH = "oplus.software.radio.jp_signal_strength";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_KARAOKE_V2_PITCHCHANGE = "oplus.software.audio.karaoke_v2.pitchchange";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_KARAOKE_V2_SUPPORT = "oplus.software.audio.karaoke_v2.support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_KEEPALIVE = "oplus.software.keep_alive";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_KEEPALIVE_EXT = "oplus.software.keep_alive_ext";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_KEYBOARD_RAISE = "oplus.software.country.keyboard_raise";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_LIBDIAG_VENDOR_SUPPORT = "oplus.software.radio.libdiag_vendor_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_LIGHT_SMART5G_ENABLED = "oplus.software.radio.light_smart5g_enabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_LISTEN_STATE_FROM_PLAYSTORE = "oplus.software.listen_state_from_playstore";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_LIST_OPTIMIZE = "oplus.software.list_optimize";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_LMVIBRATOR_SUPPORT = "oplus.software.vibrator_lmvibrator";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_LOCAL_RELEASE = "oplus.software.radio.local_release";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_LOC_CONTROL_SWITCH = "oplus.software.radio.loc_control_switch";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_LOGKIT_HIDE_DESKTOP_ICON = "oplus.software.logkit.hide_desktop_icon";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_LOG_GAME_ERR = "oplus.software.radio.game_err";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_LOG_GAME_PAGING = "oplus.software.radio.game_paging";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_LTE_INSTEND_OF_CA = "oplus.software.radio.lte_instend_of_ca";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_MAX_DEFAULT_VOLUME_SUPPORT = "oplus.software.audio.max_default_volume";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_MAX_THREE_PDNS = "oplus.software.radio.max_three_pdns";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_MEETING_OPITMIZ = "oplus.software.radio.online_meeting";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_MEMCGAME_INCREASE_FPS_LIMIT_SUPPORT = "oplus.software.display.game.memc_increase_fps_limit_mode";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_MEMCGAME_INCREASE_FPS_SUPPORT = "oplus.software.display.game.memc_increase_fps_mode";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_MEMCGAME_OPTIMISE_POWER_SUPPORT = "oplus.software.display.game.memc_optimise_power_mode";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_MEMCGAME_SUPPORT = "oplus.software.display.game.memc_enable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_MEMC_SUPPORT = "oplus.software.display.memc_enable";
    @OplusFeature(OplusFeature.OplusFeatureType.PERSIST_FEATURE)
    @OplusFeaturePermission("com.oplus.permission.safe.SECURITY")
    public static final String FEATURE_MIDAS_DISABLE = "oplus.software.midas.disabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_MIX_SIM_NAME_LEN_DEFAULT_TEN = "oplus.software.radio.mix_sim_name_len_default_ten";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_MMS_NOT_ALLOW_INTERNET = "oplus.software.radio.mms_not_allow_internet";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_MOTOR_BACKCAMERA = "oplus.software.motor.backcamera";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_MOTOR_BACKFLASH = "oplus.software.motor.backflash";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_MOTOR_BREATHLED = "oplus.software.motor.breathled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_MOTOR_DAT_ENABLED = "oplus.software.radio.motor_dat_enabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_MOTOR_SUPPORT = "oplus.software.motor_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_MTKOPLUSMODE_SUPPORT = "oplus.software.display.mtkcolormode_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_MULTIBITS_DIMMING_SUPPORT = "oplus.software.display.multibits_dimming_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_MULTI_APP_DISABLED = "oplus.software.multi_app_disabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_MULTI_APP_SUPPORT_LESS = "oplus.software.multiapp_support_less";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_MULTI_APP_SUPPORT_RLM_EXT = "oplus.software.multiapp_support_rlm_ext";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_MULTI_DEVICE_DECREASE_REFRESH_RATE_SUPPORT = "oplus.software.display.multi_device_decrease_refresh_rate";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_MULTI_USER_ENTRY_DISABLED = "oplus.software.multiuser_entry_disabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_NETWORK_FENCE = "oplus.software.radio.enable_network_fence";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_NEW_FEATURES_DISPLAYING = "oplus.software.new_features_displaying";
    @OplusFeature(OplusFeature.OplusFeatureType.PERSIST_FEATURE)
    @OplusFeaturePermission(Manifest.permission.OPLUS_FEATURE)
    public static final String FEATURE_NFC_AID_OVERFLOW = "oplus.software.nfc.over_flow";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_NFC_FELICA_SUPPORT = "oplus.software.nfc.felica_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_NONE_DEVICE = "oplus.software.carrier.none_device";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_NON_STS_SIM_LOCK = "oplus.software.radio.non_sts_sim_lock";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_NOPROWERBLOCK_FACE = "oplus.software.face_nopowerblock";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_NOTIFICATION_CUSTOM_VERSION = "oplus.software.notification_custom_version";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_NOTIFY_DATA_CONNECTION = "oplus.software.radio.notify_data_connection";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_NOT_GRANT_CONTACTS = "oplus.software.disable_grant_calendar_contacts_permissions";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_NOT_UNINSTALL_COMPANY_PACKAGES = "oplus.software.not_uninstall_company_package";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_NO_BAND_SELECTIONS = "oplus.software.radio.no_band_selections";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_NR_ALWAYS_SA_PRE = "oplus.software.radio.nr_always_sa_pre";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_NR_BWP_RUS_CONTROL = "oplus.software.radio.nr_bwp_rus_control";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_NR_ICON_OPTIMIZED_ENABLED = "oplus.software.radio.nr_icon_optimized_enabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_NR_NRCA_RUS_CONTROL = "oplus.software.radio.nr_nrca_rus_control";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_NSA_CALL_DISABLE_ENDC = "oplus.software.radio.nsa_call_disable_endc";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_NTP_PREFERRED = "oplus.software.radio.ntp_preferred";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_NWPOWER_APP_NETWORK_MONITOR_SWITCH_ENABLE = "oplus.software.radio.nwpower_app_network_monitor_switch_enable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_NWPOWER_BG_APP_NET_CONTROL_SWITCH_ENABLE = "oplus.software.radio.nwpower_bg_app_net_control_switch_enable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_NWPOWER_POWER_RECOVERY_SWITCH_ENABLE = "oplus.software.radio.nwpower_power_recovery_switch_enable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_NWPOWER_SCREENOFF_APP_NET_CONTROL_SWITCH_ENABLE = "oplus.software.radio.nwpower_screenoff_app_net_control_switch_enable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_NWPOWER_SYS_NET_CONTROL_SWITCH_ENABLE = "oplus.software.radio.nwpower_sys_net_control_switch_enable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_NW_DATA_EVAL = "oplus.software.radio.data_eval";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_NW_DATA_FAST_DORMANCY_DISABLED = "oplus.software.radio.data_fast_dormancy_disabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_NW_DATA_FAST_RECOVERY = "oplus.software.radio.data_fast_recovery";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_NW_DATA_LIMIT = "oplus.software.radio.data_limit";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_NW_LOW_LATENCY = "oplus.software.radio.low_latency";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_OFFHOTSPOT_SIMREFRESH = "oplus.software.radio.offhotspot_simrefresh";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_OHA_SUPPORT = "oplus.software.display.oha_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_ONLY_GET_SIM_MSISDN = "oplus.software.radio.only_get_sim_msisdn";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_OPENID_SUPPORT_OLD = "oplus.software.openid.support_old_project";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_OPERATOR_SERVICE_COTA_COTA = "oplus.software.operator_service.cota_cota";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_OPERATOR_SERVICE_SFR_FR = "oplus.software.operator_service.sfr_fr";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_OPERATOR_SERVICE_THREAD_DISABLE = "oplus.software.operator.disable_domestic";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_OPERATOR_SERVICE_TM_DE = "oplus.software.operator_service.tmobile_de";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_OPERATOR_SERVICE_TM_NL = "oplus.software.operator_service.tmobile_nl";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_OPERATOR_SERVICE_TM_PL = "oplus.software.operator_service.tmobile_pl";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_OPERATOR_SERVICE_TM_RO = "oplus.software.operator_service.tmobile_ro";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_OPERATOR_SERVICE_VF_DE = "oplus.software.operator_service.vodafone_de";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_OPERATOR_SERVICE_VF_ES = "oplus.software.operator_service.vodafone_es";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_OPERATOR_SERVICE_VF_GB = "oplus.software.operator_service.vodafone_gb";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_OPERATOR_SERVICE_VF_IT = "oplus.software.operator_service.vodafone_it";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_OPERATOR_SERVICE_VF_PT = "oplus.software.operator_service.vodafone_pt";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_OPLUSMODE_SUPPORT = "oplus.software.display.colormode_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_OPLUSPOWERSAVINGMODE_SUPPORT = "oplus.software.display.colormode_powersaving_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_OPLUS_PASSPPOINT_OFF = "oplus.software.wlan.opluspasspoint.off";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_OPTICAL_FINGERPRINT = "oplus.software.fingeprint_optical_enabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_OP_SHARE_SHEET = "oplus.software.op_share_sheet";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_OP_UXICON_EXP = "oplus.software.op_uxicon_exp";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_ORIGINAL_SHUTDOWN_ANIMATION = "oplus.software.original.shutdown_animation";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_ORIGIN_ROUNDCORNER_SUPPORT = "oplus.software.display.origin_roundcorner_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_OSIE_AIPQ_SUPPORT = "oplus.software.display.osie_aipq_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_OSIE_SUPPORT = "oplus.software.video.osie_support";
    @OplusFeature(OplusFeature.OplusFeatureType.MEMORY_FEATURE)
    @OplusFeaturePermission(Manifest.permission.UPDATE)
    public static final String FEATURE_OTA_UPDATE_RESULT = "oplus.software.deal_update_result";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_PADCONNECT_SUPPORT = "oplus.software.padconnect.support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_PA_AGING_FOR_LTE_B41_110M = "oplus.software.radio.pa_aging_for_lte_b41_110m";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_PA_AGING_FOR_LTE_B41_120M = "oplus.software.radio.pa_aging_for_lte_b41_120m";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_PA_AGING_FOR_LTE_B41_160M = "oplus.software.radio.pa_aging_for_lte_b41_160m";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_PA_AGING_FOR_LTE_B41_LEGACY = "oplus.software.radio.pa_aging_for_lte_b41_legacy";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_PA_AGING_FOR_N41_CHANNEL = "oplus.software.radio.pa_aging_for_n41_channel";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_PA_AGING_FOR_NR_N41_160M = "oplus.software.radio.pa_aging_for_nr_n41_160m";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_PCCONNECT_SUPPORT = "oplus.software.pcconnect.support";
    public static final String FEATURE_PERMISSION_ACCOUNT_DIALOG_DISABLED = "oplus.software.permission_account_dialog_disabled";
    public static final String FEATURE_PERMISSION_BACKGROUND_REJECT_DISABLED = "oplus.software.permission_background_reject_disabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_PERMISSION_INTERCEPT = "oplus.software.permission_intercept_enabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_PERMISSION_INTERCEPT_LOCKSCREEN = "oplus.software.intercept_lockscreen_service";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_PERMISSION_PARSE = "oplus.software.permission_product_xml_parse";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_PERSONAL_ASSIST_ENABLE = "oplus.software.personal_assist_enable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_PIXELWORKS_SUPPORT = "oplus.software.display.pixelworks_enable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_PMS_ADB_SESSION_INSTALLER_INTERCEPT = "oplus.software.pms_adb_session_installer_intercept";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_PMS_APP_FROZEN = "oplus.software.pms_app_frozen";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_PMS_APP_LIST_INTERCEPT = "oplus.software.pms_app_list_intercept";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_PMS_DEVICE_OWNER_UNINSTALL_POLICY = "oplus.software.pms_device_owner_uninstall_policy";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_PMS_GP_VERIFICATION_DISABLED = "oplus.software.pms_gp_verification_disabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_PMS_HIDE_LAUNCHER_ENABLE = "oplus.software.pms_hide_launcher_enable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_PMS_INSTALL_PERMISSION_AUTOALLOWED = "oplus.software.install_permission_autoallowed";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_PMS_INSTALL_STATISTICS_NO_SILENT = "oplus.software.pms_install_statistics_no_silent";
    @OplusFeature(OplusFeature.OplusFeatureType.PERSIST_FEATURE)
    public static final String FEATURE_PMS_SELLMODE = "oplus.software.pms_sellmode";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_POLICY_SALEMODE_DISABLED = "oplus.software.radio.policy_salemode_disabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_POWER_BUTTON_REDEFINE = "oplus.software.power_button_redefine";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_PRELOAD_SPLAHS = "oplus.software.preload_splash";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_PROCESS_REGION_CHANGE = "oplus.software.radio.process_change_region";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_PULL_WIFI_CELL = "oplus.software.radio.pull_wifi_cell";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_PUT_CARD_TYPE_TO_DB = "oplus.software.radio.put_card_type_to_db";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_QCOM_LMVIBRATOR_SUPPORT = "oplus.software.vibrator_qcom_lmvibrator";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_QC_GSM_FBRX_NOT_CAL = "oplus.software.radio.gsm_fbrx_not_cal";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_QC_NEW_PA_AGING_SUPPORT = "oplus.software.radio.new_pa_aging_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_QC_PA_AGING_FOR_LTE_B28B = "oplus.software.radio.pa_aging_for_lte_b28b";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_QC_PA_AGING_FOR_LTE_B41B = "oplus.software.radio.pa_aging_for_lte_b41b";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_QC_PA_AGING_FOR_NR_B28B = "oplus.software.radio.pa_aging_for_nr_b28b";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_QC_PA_AGING_SKIP_TX_TEST = "oplus.software.radio.pa_aging_skip_tx_test";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_QC_PA_AGING_USE_FTM_NS = "oplus.software.radio.pa_aging_use_ftm_ns";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_RADIO_APMDMLOGPB_SUPPORT = "oplus.software.radio.apmdm_log_pb";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_RADIO_ENGNW_STS_INTERFACE_SUPPORT = "oplus.software.radio.engnw_sts_interface_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_RADIO_HEALTH_SERVICE_SUPPORT = "oplus.software.radio.health_service_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_RADIO_HOOK = "oplus.software.radio.ril_ext_api_hook";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_RADIO_LCMHOPPING_URC = "oplus.software.radio.lcm_hopping_urc";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_RADIO_SAR_VIA_STS_SUPPORT = "oplus.software.radio.sar_via_sts_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_RADIO_VENDOR_DIAG_SUPPORT = "oplus.software.radio.vendor_diag";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_RAT_BAND_SELECTIONS = "oplus.software.radio.rat_band_selections";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_RECORD_CHANNEL_SUPPORT = "oplus.software.audio.record_channel_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_RECOVERY_NRMODE = "oplus.software.radio.recovery_nrmode";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_REDUCE_BRIGHTNESS_MANUAL = "oplus.software.display.reduce_brightness_manual";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_REDUCE_BRIGHTNESS_RM = "oplus.software.display.reduce_brightness_rm";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_REDUCE_BRIGHTNESS_RM_MANUAL = "oplus.software.display.reduce_brightness_rm_manual";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_REFRESHRATE_DEFAULT_SMART = "oplus.software.display.refreshrate_default_smart";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_REMOVE_ABNORMAL_SYNCJOB = "oplus.software.remove_abnormal_syncjob";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_RESOLUTION_SWITCH = "oplus.software.display.resolution_switch_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_RESOLUTION_SWITCH_DISABLEAUTO = "oplus.software.display.resolution_switch_disableauto_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_RESOLVER_SHARE_EMAIL = "oplus.software.resolver_share_email";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_RETRY_OPEN_CHANNEL_FOR_TW = "oplus.software.radio.retry_open_channel";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_REVISE_SQUARE_DISPLAY_ORIENTATION = "oplus.software.revise_square_display_orientation";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_RF_BAR_CELL = "oplus.software.radio.bar_cell";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_RICHTAP_SUPPORT = "oplus.software.vibrator_richctap";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_RINGER_TOGGLE_CHORD_DISABLE = "oplus.software.ringer_toggle_chord_disable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_RINGTONE_HAPTIC_CHANNEL = "oplus.software.audio.haptic_channel_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_RM_BATTERY_CURVE = "oplus.software.rm_battery_curve";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_ROAMING_DYNAMIC_DUN_APN = "oplus.software.radio.roaming_dynamic_dun_apn";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SALEPATEST_SUPPORT = "oplus.software.radio.eng_salepatest_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SAR_AIRINTERFACE_DETECT_NOTSUPPORT = "oplus.software.radio.sar.airinterface.detect.notsupport";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SAR_BACKOFF_DISABLED = "oplus.software.radio.sar_backoff_disabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SAR_BT_DETECT_SUPPORT = "oplus.software.radio.sar.bluetooth.detect.support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SAR_EARPIECE_DETECT_SUPPORT = "oplus.software.radio.sar.earpiece.detect.support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SAR_ENABLE = "oplus.software.radio.sar.sar_sensor_enable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SAR_FCC_DETECT_SUPPORT = "oplus.software.radio.sar.fcc.detect.support";
    @OplusFeature(OplusFeature.OplusFeatureType.MEMORY_FEATURE)
    @OplusFeaturePermission(Manifest.permission.SAU)
    public static final String FEATURE_SAU_UPDATE_RESULT = "oplus.software.deal_sau_update_result";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SA_APM_CONTROL = "oplus.software.radio.sa_apm_control";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SA_BACKOFF_SA_CAUSE_NETWORK_REJECT = "oplus.software.radio.backoff_sa_cause_network_reject";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SA_CALL_BACK0FF_CONTROL = "oplus.software.radio.sa_call_backoff_control";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SA_CALL_CONTROL_DISABLED = "oplus.software.radio.sa_call_control_disabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SA_DATA_CALL_CONTROL_DISABLED = "oplus.software.radio.data_call_control_disabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SA_DATA_RECOVERY = "oplus.software.radio.sa_data_recovery_enabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SA_IMS_CONTROL_DISABLED = "oplus.software.radio.sa_ims_control_disabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SA_MODE_ROAMING_CONTROL = "oplus.software.radio.sa_mode_roaming_control";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SA_PINGPONG_CONTROL_DISABLED = "oplus.software.radio.sa_pingpong_control_disabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SA_RAT_CONTROL = "oplus.software.radio.sa_rat_control";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SA_REG_TIMEOUT_CONTROL_ENABLED = "oplus.software.radio.sa_reg_timeout_enabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SA_SCREEN_OFF_DISABLE_SA_ENABLED = "oplus.software.radio.screen_off_disable_sa_enabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SA_TAC_CONTROL = "oplus.software.radio.sa_tac_control";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SA_TIME_CONTROL_DISABLED = "oplus.software.radio.sa_time_control_disabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SCENE_MODE = "oplus.software.radio.scene_mode";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SCREEN_DEFAULT_SMART_MODE_SUPPORT = "oplus.software.display.screen_defaultsmartmode";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SCREEN_GLOABLEHBM_SUPPORT = "oplus.software.display.screen_gloablehbm_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SCREEN_HETEROMORPHISM = "oplus.software.display.screen_heteromorphism";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SCREEN_NO_HBM_IN_MANUAL_MODE_SUPPORT = "oplus.software.display.no_hbm_in_manual_mode_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SCREEN_PARTHBM_SUPPORT = "oplus.software.display.screen_parthbm_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SECURITYANALYSIS_SENDBROADCAST = "oplus.software.securityanalysis.sendbroadcast";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SENSOR_FUSIONLIGHT_SUPPORT = "oplus.sensor.fusionlight.support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SET_DEFAUT_MONTH_FOR_FIRST_DATE = "oplus.software.time.set_default_month";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SET_POL_ENTRY_COMPLETE_WITH_LAST_ITEM = "oplus.software.radio.set_pol_entry_complete_with_last_item";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SET_RAT_NO_DELAY = "oplus.software.radio.set_pref_rat_no_delay";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SET_WIFI_SAR_ENABLED = "oplus.software.radio.set_wifi_sar_enabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SHOW_CONFUSED_HARDWARE_INFO = "oplus.software.show_confused_hardwareinfo";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SHOW_ESIM_ENGINEERMODE_ENABLED = "oplus.software.radio.esim_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SHOW_HD_PLUS = "oplus.software.radio.show_hd_plus";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SHOW_NOTIFICATION_TOAST = "oplus.software.notification.show_toast";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SHOW_NR5G_MODE = "oplus.software.radio.show_5g_nr_mode";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SHUTDOWN_ANIMATION = "oplus.software.shutdown_animation";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SIDE_FINGERPRINT = "oplus.software.side_fingerprint";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SIGNAL_RECOVERY = "oplus.software.radio.signal_recovery_enabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SIGNAL_SMOOTH_DISABLED = "oplus.software.radio.signal_smooth_disabled";
    @OplusFeature(OplusFeature.OplusFeatureType.PERSIST_FEATURE)
    public static final String FEATURE_SILENT_REDIAL_ENABLED = "oplus.software.radio.silent_redial_enabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SILENT_REDIAL_SUPPORTED = "oplus.software.radio.silent_redial_supported";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SIM2_DETECT_NOT_SUPPORT = "oplus.software.radio.sim2_detect_not_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SIM_CONTACTS_SUPPORT = "oplus.software.radio.sim_contacts_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SIM_DETECT_STATUS_CHECK_SUPPORT = "oplus.software.radio.sim_detect_check_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SIM_NAME_LENGTH_DEFAULT_TEN = "oplus.software.radio.sim_name_length_default_ten";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SIM_SWITCH_CURRENT_REMOVE_PRE_APK = "oplus.software.carrier.sim_switch_current_remove_prev_apk";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SIM_SWITCH_FIRST_EVERYTIME = "oplus.software.carrier.sim_switch_first_everytime";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SIM_SWITCH_FIRST_ONETIME = "oplus.software.carrier.sim_switch_first_onetime";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SIM_SWITCH_FIRST_ONLY_FIRST = "oplus.software.carrier.sim_switch_first_only_first";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SIM_SWITCH_FIRST_REMOVE_PRE_APK = "oplus.software.carrier.sim_switch_first_remove_prev_apk";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SIM_SWITCH_FORCE_REBOOT = "oplus.software.carrier.sim_switch_force_reboot";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SIM_SWITCH_NOTIFY_REBOOT = "oplus.software.carrier.sim_switch_notify_reboot";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SIM_SWITCH_WAIT_DATA_SLOT = "oplus.software.carrier.sim_switch_first_wait_data_slot";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SIM_SWITCH_WAIT_MAIN_SLOT = "oplus.software.carrier.sim_switch_first_wait_main_slot";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SIM_SWITCH_WAIT_PHYSIAL_SLOT = "oplus.software.carrier.sim_switch_first_wait_physial_slot";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SINGLEHAND_MODE = "oplus.software.singlehand_mode";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SIP491_RESUME_FAILURE_DISABLED = "oplus.software.radio.sip491_resume_failure_disalbed";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SKIP_CHECK_LOCKASSISTANT = "oplus.software.skip_check_lockassistant";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SMART5G_EXP_CFG = "oplus.software.radio.smart5g_exp_cfg";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SMART5G_FENCE_SA_DATA_SLOW = "oplus.software.radio.smart5g_fence_sa_data_slow_opt";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SMART5G_GAME_LATENCY_CHECK = "oplus.software.radio.smart5g_game_latency_check";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SMART5G_GAME_LTE_PREFER = "oplus.software.radio.smart5g_game_lte_prefer";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SMART5G_GMAE_LIST_DEPRIO_SA = "oplus.software.radio.smart5g_game_list_deprio_sa";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SMART5G_HOTSPOT_ENABLE = "oplus.software.radio.smart5g_hotspot_enable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SMART5G_NR_RTT_CHECK = "oplus.software.radio.smart5g_nr_rtt_check";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SMART5G_NR_SIGNAL_CHECK = "oplus.software.radio.smart5g_nr_signal_check";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SMART5G_NR_S_DROP_CTRL = "oplus.software.radio.smart5g_nrscell_drop_ctrl";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SMART5G_RTT_CHECK = "oplus.software.radio.smart5g_rtt_check";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SMART5G_RTT_CHECK_SA = "oplus.software.radio.smart5g_rtt_check_sa";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SMART5G_SA_ENABLED = "oplus.software.radio.smart5g_sa_enabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SMART5G_SA_ENVIRONMENT_GAME_LTE_PREFER = "oplus.software.radio.smart5g_sa_environment_game_lte_prefer";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SMART5G_SPEEDBASED_NR_S_DROP = "oplus.software.radio.smart5g_speedbased_nrscell_drop";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SMART5G_SP_REGION_DISABLE = "oplus.software.radio.smart5g_sp_region_disable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SMART5G_THERMAL_NR_DEPRIO = "oplus.software.radio.smart5g_thermal_nr_deprio";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SMART5G_THERMAL_NR_S_DROP = "oplus.software.radio.smart5g_thermal_nrscell_drop";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SMS_7BIT16BIT = "oplus.software.radio.sms_7bit_16bit";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SMS_FIND_PHONE = "oplus.software.radio.find_phone";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SOS_SPECIAL_FUNCTION = "oplus.software.sos_special_function";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SPEECH_ASSIST_FOR_BREENO = "oplus.software.speech_assist_for_breeno";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SPEECH_ASSIST_FOR_GOOGLE = "oplus.software.speech_assist_for_google";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SPEED_CHARGE_MODE = "oplus.software.speed_charge_mode";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SPLICE_NAVIGATION_GESTURE = "oplus.software.splice.navigation_gesture";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SPL_LIMIT_SUPPORT = "oplus.software.audio.spl_limit_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SPN_AS_DISPLAYNAME = "oplus.software.radio.spn.as.displayname";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SR_SUPPORT = "oplus.software.video.sr_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_STARTUP_STATISTICS_UPLOAD = "oplus.software.startup_statistics_upload";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_STARTUP_STRATEGY_RESTRICT = "oplus.software.startup_strategy_restrict";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_STS_AUTO_ANSWER_SUPPORT = "oplus.software.radio.sts_auto_answer_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SUBINFO_MCCMNC_ADVANCE = "oplus.software.radio.subinfo_mccmnc_advance";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SUPER_SLEEP = "oplus.software.super_sleep";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SUPER_VOLUME = "oplus.software.audio.super_volume";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SUPPORT_ABNORMAL = "oplus.software.support_abnormal";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SUPPORT_DUAL_NR = "oplus.software.radio.support_dual_nr";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SUPPORT_FCC_SAR_REGION = "oplus.software.radio.support_fcc_sar";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SUPPORT_SIM_DISPLAY_NAME = "oplus.software.radio.support_sim_display_name";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SUPPORT_SIM_LOCK_STATE = "oplus.software.radio.support_sim_lock_state";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SUPPORT_SNC = "oplus.sncontent.support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SURROUND_EFFECT_SUPPORT = "oplus.software.effect.surround_effect_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SURROUND_RECORD_SUPPORT = "oplus.software.video.surround_record_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_SWITCH_ECT = "oplus.software.radio.switch_ect";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_TABLET = "oplus.hardware.type.tablet";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_TELEPHONY_DISABLED_KEY_LOG = "oplus.software.radio.disabled_key_log";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_TELEPHONY_DISABLED_RUS = "oplus.software.radio.disabled_rus";
    @OplusFeature(OplusFeature.OplusFeatureType.MEMORY_FEATURE)
    @OplusFeaturePermission(Manifest.permission.OPLUS_FEATURE)
    public static final String FEATURE_TEST_MEMORY = "oplus.software.memory_test";
    @OplusFeature(OplusFeature.OplusFeatureType.MEMORY_FEATURE)
    @OplusFeaturePermission("com.oplus.permission.safe.OPLUS_FEATURE")
    public static final String FEATURE_TEST_MEMORY_1 = "oplus.software.memory_test1";
    @OplusFeature(OplusFeature.OplusFeatureType.MEMORY_NATIVE_FEATURE)
    public static final String FEATURE_TEST_NATIVE_MEMORY = "vendor.feature.test_native";
    @OplusFeature(OplusFeature.OplusFeatureType.PERSIST_NATIVE_FEATURE)
    public static final String FEATURE_TEST_NATIVE_PERSIST = "persist.vendor.feature.test_native";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_NATIVE_FEATURE)
    public static final String FEATURE_TEST_NATIVE_READONLY = "ro.vendor.feature.test_native";
    @OplusFeature(OplusFeature.OplusFeatureType.PERSIST_FEATURE)
    @OplusFeaturePermission(Manifest.permission.OPLUS_FEATURE)
    public static final String FEATURE_TEST_PERSIST = "oplus.software.persist_test";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_TEST_READONLY_A = "oplus.software.readonly_test_a";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_TEST_READONLY_B = "oplus.software.readonly_test_b";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_TEST_READONLY_C = "oplus.software.readonly_test_c";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_TP_EDGE_MISTOUCH_PREVENTION = "oplus.software.tp.edge_mistouch_prevention";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_TVCONNECT_SUPPORT = "oplus.software.tvconnect.support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_TV_VIDEOCALL_SUPPORT = "oplus.software.tv_videocall.support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_TYPEC_OTG_SUPPORT = "oplus.typecotg.connection.menu.support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_UID_PUR_STATS = "oplus.software.radio.uid_pur_stats";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_ULTIMATE_FIVE_G_SUPPORT = "oplus.software.radio.ultimate_five_g_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_UNDERLIGHTSENSOR_SUPPORT = "oplus.software.display.underlightsensor_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_UNKNOWN_SOURCE_APP_INSTALL = "oplus.software.unknown_source_app_install";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_USB_SCENES_GAMES_OP_CONFIG = "oplus.software.usb.scenes.optimize_config";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_USS_DATA_ERROR_MESSAGE = "oplus.software.radio.uss_data_error_message";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_USS_DEVICE = "oplus.software.carrier.uss_device";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_USS_ROAMING_ALERT = "oplus.software.radio.uss_roaming_alert";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_USS_ROAMING_REDUCTION = "oplus.software.radio.uss_roaming_reduction";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_USS_ROAMING_SUPPORT = "oplus.software.radio.uss_roaming_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_UST_5G_UC = "oplus.software.carrier.ust_5g_uc";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_UST_APP_STATE_BROADCAST = "oplus.software.carrier.ust_app_state_broadcast";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_UST_CARRIER_CONFIG = "oplus.software.radio.ust_carrier_config";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_UST_DATA_ALWAYS_ON = "oplus.software.radio.ust_data_always_on";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_UST_DATA_DEVICE_REPORTING = "oplus.software.radio.ust_data_device_reporting";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_UST_DATA_ROAMING_POPUP = "oplus.software.radio.ust_data_roaming_popup";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_UST_DEVICE = "oplus.software.carrier.ust_device";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_UST_ECHOLOCATE = "oplus.software.radio.ust_echolocate";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_UST_NONE_SIM_ECC = "oplus.software.radio.ust_none_sim_ecc";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_UST_SIMLOCK = "oplus.software.radio.ust_simlock";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_UST_SMART_WIFICALLING = "oplus.software.radio.ust_smart_wificalling";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_UST_STIR = "oplus.software.radio.ust_stir";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_UST_TOAST = "oplus.software.radio.ust_toast";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_UST_UNIFY_DEVICE = "oplus.software.carrier.ust_unify_device";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_UST_USKU_DEVICE = "oplus.software.carrier.ust_usku_device";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_USV_EMERGENCY_CALL = "oplus.software.radio.usv_emergency_call";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_USV_OMA_APN = "oplus.software.radio.usv_ota_apn";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_USV_PCO = "oplus.software.radio.usv_pco";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_USV_UA = "oplus.software.radio.usv_user_agent";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_USV_UWICON = "oplus.software.radio.usv_uw_icon";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_UXICON_EXP = "oplus.software.uxicon_exp";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_VERIFY_CODE_ENABLE = "oplus.software.inputmethod.verify_code_enable";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_VIBRATION_INTENSITY_IME = "oplus.software.vibration_intensity_ime";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_VIBRATION_KEYBOARD_CONVERT = "oplus.software.vibration_keyboard_convert";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_VIBRATOR_STYLESWITCH_SUPPORT = "oplus.hardware.vibrator_style_switch";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_VIBRATOR_XLINEAR_SUPPORT = "oplus.hardware.vibrator_xlinear_type";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_VICE_CARD_GAME_MODE = "oplus.software.radio.vice_card_game_mode";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_VIDEO_RM_MEMC_SUPPORT = "oplus.software.video.rm_memc";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_VOICE_DTMF = "oplus.software.radio.voice_dtmf";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_VOICE_WAKEUP_3WORDS_SUPPORT = "oplus.software.audio.voice_wakeup_3words_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_VOICE_WAKEUP_SUPPORT = "oplus.software.audio.voice_wakeup_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_VONR_CAPABILITY_RECOVERY = "oplus.software.radio.vonr_capability_recovery";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_VONR_CITY_CONTROL = "oplus.software.radio.vonr_city_control";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_VONR_SWITCH_ENABLED = "oplus.software.radio.vonr_switch_enabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WAKE_UP_AROUSE_SUPPORT = "oplus.software.wake_up_arouse_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WALLET_QUICK_LAUNCH = "oplus.software.wallet_quick_launch";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WALLPAPER_CARRIER_CLARO = "oplus.software.wallpaper_carrier_claro";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WALLPAPER_CARRIER_CUSTOM = "oplus.software.wallpaper_carrier_custom";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WALLPAPER_CARRIER_TELCEL = "oplus.software.wallpaper_carrier_telcel";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WALLPAPER_CARRIER_TIM = "oplus.software.wallpaper_carrier_tim";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WFC_DISABLE_NRSA = "oplus.software.radio.wfc_disable_nrsa";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_5G160M_SAP = "oplus.software.wlan.5g_160M_softap";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_5G160M_STATION_FOR_PHONE_CLONE = "oplus.software.wlan.5g_160M_station_for_phone_clone";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_ANT_SWAP = "oplus.software.wlan.ant_swap";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_AUTORECONNECT = "oplus.software.wlan.auto_reconnect";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_BAR = "oplus.software.wlan.bar";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_DATA_CONTROL = "oplus.software.radio.wifi_data_control";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_DBS = "oplus.software.wlan.dbs";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_DISABLE_SAP_WiFi6 = "oplus.software.wlan.disable_sap_wifi6";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_DUALSTA = "oplus.software.wlan.dual_sta";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_FDD = "oplus.software.wlan.fdd_mode";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_HYBRID = "oplus.software.wlan.hybrid_mode";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_IPV6OPTMIZE = "oplus.software.wlan.ipv6_optimize";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_LOG_EUEX = "oplus.software.wlan.log_euex";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_LOW_LATENCY = "oplus.software.wlan.low_latency";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_NULL_DATA_TO_CTS = "oplus.software.wlan.null_data_to_cts";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_PASSPOINT_ATT = "oplus.software.wlan.passpoint_attmex";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_PASSPOINT_JIO = "oplus.software.wlan.passpoint_jio";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_PASSPOINT_VERIZON = "oplus.software.wlan.passpoint_verizon";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_PKAT = "oplus.software.wlan.pkat";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_PRESET_BOUYGUE = "oplus.software.wlan.operator_preset_bouygue";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_PRESET_KDDI = "oplus.software.wlan.operator_preset_kddi";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_PRESET_O2 = "oplus.software.wlan.o2_preset";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_PRESET_RAKUTEN = "oplus.software.wlan.operator_preset_rakuten";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_PRESET_SINGTEL = "oplus.software.wlan.operator_preset_singtel";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_PRESET_SWISSCOM = "oplus.software.wlan.operator_preset_swisscom";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_PRESET_THAILAND = "oplus.software.wlan.operator_preset_thailand";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_PRESET_TMOBILE = "oplus.software.wlan.operator_preset_tmobile";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_PRESET_TURKCELL = "oplus.software.wlan.operator_preset_turkcell";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_PRESET_VERIZON = "oplus.software.wlan.verizon_preset";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_PRESET_VODAFONE = "oplus.software.wlan.vodafone_preset";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_ROUTERBOOST = "oplus.software.wlan.routerboost";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_SAP_RANDOM_SSID = "oplus.software.wlan.softap_random_ssid";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_SMARTGEAR = "oplus.software.wlan.smart_gear";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_SMART_ANTENNA = "oplus.software.wlan.smart_antenna";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_SNIFFER_CAPTURE_WITH_UDP = "oplus.software.wlan.sniffer_capture_with_udp";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_SOFTAP_NSS1X1 = "oplus.software.wlan.softap_nss1X1";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_USS_PASSPOINT = "oplus.software.wlan.uss_passpoint";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_UST_HOTSPOT_SHOW = "oplus.software.wlan.ust_hotspot_show";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_UST_PASSPOINT = "oplus.software.wlan.ust_passpoint";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WIFI_WIFI6 = "oplus.software.wlan.wifi6";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WINDOW_ANIM_LIGHT = "oplus.software.windowanimaiton.light";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WINDOW_BRACKET = "oplus.software.windowmode.bracket";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WINDOW_COMPACT = "oplus.software.windowmode.compact";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WLAN_ASSISTANT = "oplus.software.wlan.assistant";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WLAN_IOTCONNECT_OFF = "oplus.software.wlan.iotconnect.off";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WLAN_SLA = "oplus.software.wlan.sla";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WLAN_TRAFFIC_LIMIT = "oplus.software.wlan.traffic_limit";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WLB = "oplus.software.wlb";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_WNR_SUPPORT = "oplus.software.video.wnr_support";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String FEATURE_Z_AXIS_LMVIBRATOR_SUPPORT = "oplus.software.vibrator_z_axis";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String OPLUS_FEATURE_CALL_RECOVERY_DISABLED = "oplus.software.radio.call_recovery_disabled";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String OPLUS_FEATURE_CLEAR_USER_WALLPAPER = "oplus.software.wallpaper.clear_user_wallpaper";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String OPLUS_FEATURE_COLOR_SPLIT_FEATURE = "oplus.software.color_split_feature";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String OPLUS_FEATURE_CRAZY_QUERY_CHECKER = "oplus.software.radio.crazy_query_checker";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String OPLUS_FEATURE_DARK_MODE_STYLE_MODIFY = "oplus.software.dark_mode_style";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String OPLUS_FEATURE_INCREASE_TCP_SYNC_RETRIES = "oplus.software.radio.increase_tcp_sync_retries";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String OPLUS_FEATURE_JANK_TRACKER = "oplus.software.jank_tracker";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String OPLUS_FEATURE_KEEP_SWIPEUP_GESTURES = "com.android.systemui.keep_swipup_gestures";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String OPLUS_FEATURE_NEWSOUNDRECORD_OS12_1_FUNCTION = "oplus.software.newsoundrecord.os12_1_function";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String OPLUS_FEATURE_POWERKEY_SHUTDOWN = "oplus.software.long_press_powerkey_shutdown";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String OPLUS_FEATURE_SCROLL_ANIMATION_OPT = "oplus.software.scroll.animation_opt";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String OPLUS_FEATURE_UST_ADB_SET_SVN = "oplus.software.carrier.ust_adb_set_svn";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String OPLUS_FEATURE_UST_UA = "oplus.software.radio.ust_user_agent";
    @OplusFeature(OplusFeature.OplusFeatureType.READONLY_FEATURE)
    public static final String OPLUS_FEATURE_WEATHER_EFFECT_OGG = "oplus.software.weather.effect_egg";
}
