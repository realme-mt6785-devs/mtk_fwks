package android.os;

import com.oplus.annotation.OplusProperty;
import com.oplus.uifirst.IOplusUIFirstManager;
/* loaded from: classes2.dex */
public class OplusPropertyList {
    public static final String OPLUS_VERSION = "CN";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PERSIST_PROP_MONKEY_RUNNING = "persist.sys.oplus.autotest.monkeyRunning";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PERSIST_SYS_OPEN_CAMERA = "persist.sys.oplus.camera.open.camera";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PERSIST_SYS_OPEN_TORCH = "persist.sys.oplus.camera.open.torch";
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_AUDIO_EFFECT_TYPE = "ro.oplus.audio.effect.type";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_BEAM_ALWAYS_DISABLE = "persist.sys.oplus.nfc.beam_disable";
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_BTN_APOLLO = "persist.brightness.apollo";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_CARRIER_DATE_UTC_BACKUP = "persist.sys.oplus.my_carrier.data_utc_backup";
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_CONNECTIVITY_OVERSEA = "ro.oplus.connectivity.oversea";
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_CONNECTIVITY_PREVISION = "ro.oplus.connectivity.prevision_build";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_CUSTOMIZE_MARKET_APP_ALLOW_INSTALL = "persist.sys.custom.market.app.list.allow.install";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_CUSTOMIZE_MARKET_APP_UNINSTALL = "persist.sys.custom.market.app.list.uninstall";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_CUSTOMIZE_OPLUS_APP_ALLOW_INSTALL = "persist.sys.custom.oplus.app.list.allow.install";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_CUSTOMIZE_OPLUS_APP_UNINSTALL = "persist.sys.custom.oplus.app.list.uninstall";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_CUSTOMIZE_SEND_POWER_BRAODCAST_ENABLED = "persist.sys.custom.enable.powerkeybroadcast";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_CUSTOMIZE_SEND_SOS_BRAODCAST_ENABLED = "persist.sys.custom.enable.soskeybroadcast";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_CUSTOMIZE_SEND_VOLUMEDOWN_BRAODCAST_ENABLED = "persist.sys.custom.enable.volumedownkeybroadcast";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_CUSTOMIZE_SEND_VOLUMEUP_BRAODCAST_ENABLED = "persist.sys.custom.enable.volumeupkeybroadcast";
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_CUSTOMIZE_UNINSTALL_MINUS_SCREEN = "ro.custom.disable.minus_screen";
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_CUSTOMIZE_UNINSTALL_OPLUS_BROWSER = "ro.custom.disable.oplus.browser";
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_CUSTOMIZE_UNINSTALL_WEBVIEW = "ro.custom.disable.webview";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_FEATURE_OCA_ON = "persist.sys.oplus.feature_oca_on";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_GPS_NFW_ENABLE = "persist.sys.oplus.nfw.enable";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_GPS_NLP_NAME = "persist.sys.oplus.gps.nlp_name";
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_HW_PHONE_OPLUS = "ro.hw.phone.color";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_INPUTMETHOD_CUSTOMIZE_PACKAGE = "persist.sys.oplus.inputmethod.customize.package";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_INPUTMETHOD_CUSTOMIZE_PACKAGE_BACKUP = "persist.sys.oplus.inputmethod.customize.package.backup";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_LOAD_SYSTEM_CONFIG_COTA = "persist.sys.load.system.config.cota";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_MULTI_BRIGHTNESS = "sys.oplus.multibrightness";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_NEURON_SYSTEM = "persist.sys.oplus.neuron_system";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_NFC_ALWAYS_DISABLE = "persist.sys.oplus.nfc.always_disable";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_NFC_ALWAYS_ENABLE = "persist.sys.oplus.nfc.always_enable";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_NFC_ANTENNA_AREA = "persist.sys.oplus.nfc.antenna_area";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_NFC_DEFAULT_ENABLE = "persist.sys.oplus.nfc.defnfc";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_OPERATOR_SERVICE_OPTA = "persist.sys.oplus.operator.opta";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_OPERATOR_SERVICE_OPTB = "persist.sys.oplus.operator.optb";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_OPINSTALLED = "persist.sys.oplus.opinstalled";
    @OplusProperty(OplusProperty.OplusPropertyType.SYS_PROPERTY)
    public static final String PROPERTY_OPLUS_CROSS_UPGRADE = "sys.oplus.cross.upgrade";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_OPLUS_FACTORY_CODE = "persist.sys.oplus.factory.no";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_OPLUS_SERIAL_NUMBER = "persist.sys.oplus.serialno";
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_OPLUS_SUPPORT_FREQUENCY = "ro.oplus.nfc.frequency.support";
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_OPLUS_SUPPORT_GPFELICA = "ro.oplus.nfc.gpfelica.support";
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_OPLUS_VERSION = "";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_OSENSE_VERSION = "persist.sys.oplus.osense.version";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_SIMSWITCH_CURRENT_BACKUP = "persist.sys.simswitch.current.backup";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_SIMSWITCH_FIRST_BACKUP = "persist.sys.simswitch.first.backup";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_SIMSWITCH_SSV_BACKUP = "persist.sys.simswitch.ssv.backup";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_SIM_SWITCH_CURRENT_OPINSTALLED = "persist.sys.sim.switch.current.opinstalled";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_SIM_SWITCH_FIRST_OPINSTALLED = "persist.sys.sim.switch.first.opinstalled";
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_SUPPORT_JAPAN_FELICA = "ro.oplus.nfc.support_japan_felica";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_SYS_CUSTOM_VERSION_BACKUP = "persist.sys.oplus.company.data_backup";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_WIPEMEIDIA = "persist.sys.wipemedia";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPETY_BIOMETRICS_DEBUG = "persist.sys.biometrics.debug";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPETY_BRIGHTNESS_MODE = "persist.sys.oplus.display.brightness.mode";
    @OplusProperty(OplusProperty.OplusPropertyType.SYS_PROPERTY)
    public static final String PROPETY_COTA_LOAD_NOTREBOOT = "sys.cota.load.notreboot";
    @OplusProperty(OplusProperty.OplusPropertyType.SYS_PROPERTY)
    public static final String PROPETY_COTA_MOUNTED_STATE = "sys.cotaimg.verify";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPETY_CUSTOMIZE_MEDIA = "persist.sys.customize.media";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPETY_DC_FEATURE = "persist.sys.oplus.dc.feature";
    @OplusProperty(OplusProperty.OplusPropertyType.SYS_PROPERTY)
    public static final String PROPETY_DEEPSLEEP_DISABLE_NETWORK = "sys.deepsleep.disable.network";
    @OplusProperty(OplusProperty.OplusPropertyType.SYS_PROPERTY)
    public static final String PROPETY_DEEPSLEEP_RESTORE_NETWORK = "sys.deepsleep.restore.network";
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPETY_DEFAULT_ALARM_NAME = "ro.sys.custommedia.default_alarm";
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPETY_DEFAULT_MAIN_PHYSICAL_SIM_SLOT = "ro.sys.sim.switch.physical.slot";
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPETY_DEFAULT_NOTIFICATION_NAME = "ro.sys.custommedia.default_notification";
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPETY_DEFAULT_RINGTONE_NAME = "ro.sys.custommedia.default_ringtone";
    @OplusProperty(OplusProperty.OplusPropertyType.SYS_PROPERTY)
    public static final String PROPETY_DISPLAY_RATE = "sys.oplus.display.rate";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPETY_ENALBE_TORCH = "persist.sys.torch_available";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPETY_FACE_DATA_DEBUG = "persist.face.disable_debug_data";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPETY_FACE_POWER_BLOCK_DEBUG = "persist.sys.android.face.power.block";
    @OplusProperty(OplusProperty.OplusPropertyType.SYS_PROPERTY)
    public static final String PROPETY_HIGH_PREFORMANCE_MODE = "sys.oplus.high.performance";
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPETY_LIST_OPTIMIZE_ENABLE = "persist.sys.flingopts.enable";
    @OplusProperty(OplusProperty.OplusPropertyType.SYS_PROPERTY)
    public static final String PROPETY_MOUNT_MEDIA_PATH_RESULT = "sys.mount.mediapath.success";
    @OplusProperty(OplusProperty.OplusPropertyType.SYS_PROPERTY)
    public static final String PROPETY_RES_PRELOAD_VERSION = "sys.oplus.respreload.version";
    @OplusProperty(OplusProperty.OplusPropertyType.SYS_PROPERTY)
    public static final String PROPETY_RES_PRELOAD_VIP_SDK_ENABLED = "sys.oplus.respreload.vipsdk.enabled";
    @OplusProperty(OplusProperty.OplusPropertyType.SYS_PROPERTY)
    public static final String PROPETY_SIM_SWITCH_APK_SCAN_SYSTEM = "ro.sys.sim.switch.apk.scan.system";
    @OplusProperty(OplusProperty.OplusPropertyType.SYS_PROPERTY)
    public static final String PROPETY_SIM_SWITCH_CURRENT_SLOT = "sys.sim.switch.current.slot";
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPETY_SIM_SWITCH_DELAY = "ro.sys.sim.switch.delay";
    @OplusProperty(OplusProperty.OplusPropertyType.SYS_PROPERTY)
    public static final String PROPETY_SIM_SWITCH_FIRST_SLOT = "sys.sim.switch.first.slot";
    @OplusProperty(OplusProperty.OplusPropertyType.SYS_PROPERTY)
    public static final String PROPETY_SIM_SWITCH_OVERRIDE_PROPERTIES = "sys.override.simswitch.property";
    private static final String TAG = "OplusPropertyList";
    public static final String UNKNOWN = "unknown";
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_OPLUS_REGIONMARK = "ro.vendor.oplus.regionmark";
    public static final String OPLUS_REGIONMARK = SystemProperties.get(PROPERTY_OPLUS_REGIONMARK, "");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_OPLUS_OPERATOR = "ro.vendor.oplus.operator";
    public static final String OPLUS_OPERATOR = SystemProperties.get(PROPERTY_OPLUS_OPERATOR, "");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_OPLUS_EUEX_COUNTRY = "ro.vendor.oplus.euex.country";
    public static final String OPLUS_EUEX_COUNTRY = SystemProperties.get(PROPERTY_OPLUS_EUEX_COUNTRY, "");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_OPLUS_RSA_SUPPORT = "ro.oplus.rsa3.support";
    public static final String OPLUS_RSA_SUPPORT = SystemProperties.get(PROPERTY_OPLUS_RSA_SUPPORT, "false");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_OPLUS_EXP_VERSION = "ro.vendor.oplus.exp.version";
    public static final String OPLUS_EXP_VERSION = SystemProperties.get(PROPERTY_OPLUS_EXP_VERSION, "");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_SEPARATE_SOFT = "ro.separate.soft";
    public static final String SEPARATE_SOFT = SystemProperties.get(PROPERTY_SEPARATE_SOFT, "");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_OPLUS_VENDOR_MARKET_NAME = "ro.vendor.oplus.market.name";
    public static final String OPLUS_VENDOR_MARKET_NAME = SystemProperties.get(PROPERTY_OPLUS_VENDOR_MARKET_NAME, "");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_OPLUS_VENDOR_MARKET_ENNAME = "ro.vendor.oplus.market.enname";
    public static final String OPLUS_VENDOR_MARKET_ENNAME = SystemProperties.get(PROPERTY_OPLUS_VENDOR_MARKET_ENNAME, "");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_PRODUCT_OPLUS_MODEL = "ro.product.oplus_model";
    public static final String PRODUCT_OPLUS_MODEL = SystemProperties.get(PROPERTY_PRODUCT_OPLUS_MODEL, "");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_PRODUCT_LITTLETAIL_MODEL = "ro.product.littletail_model";
    public static final String PRODUCT_LITTLETAIL_MODEL = SystemProperties.get(PROPERTY_PRODUCT_LITTLETAIL_MODEL, "");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_ODM_PREV_PRODUCT_NAME = "ro.odm.prev.product.name";
    public static final String ODM_PREV_PRODUCT_NAME = SystemProperties.get(PROPERTY_ODM_PREV_PRODUCT_NAME, "");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_VERSION_CONFIDENTIAL = "ro.version.confidential";
    public static final String VERSION_CONFIDENTIAL = SystemProperties.get(PROPERTY_VERSION_CONFIDENTIAL, "");
    @OplusProperty(OplusProperty.OplusPropertyType.SYS_PROPERTY)
    public static final String PROPETY_OTEST_MONKEY_ENABLE = "sys.oplus.otest.monkey.enable";
    public static final String OTEST_MONKEY_ENABLE = SystemProperties.get(PROPETY_OTEST_MONKEY_ENABLE, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.SYS_PROPERTY)
    public static final String PROPETY_OTEST_CAMERA_TEST_ENABLE = "sys.oplus.otest.cameratest.enable";
    public static final String OTEST_CAMERA_TEST_ENABLE = SystemProperties.get(PROPETY_OTEST_CAMERA_TEST_ENABLE, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_REGION = "persist.sys.oplus.region";
    public static String REGION = getString(PROPERTY_REGION);
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_GNSS_POWER_SAVE_SWITCH = "ro.oplus.gnss.power_save.switch";
    public static final String GNSS_POWER_SAVE_SWITCH = SystemProperties.get(PROPERTY_GNSS_POWER_SAVE_SWITCH, "-1");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_GNSS_DEL_AIDING_DATA_SWITCH = "ro.oplus.gnss.del_aiding_data.switch";
    public static final String GNSS_DEL_AIDING_DATA_SWITCH = SystemProperties.get(PROPERTY_GNSS_DEL_AIDING_DATA_SWITCH, "-1");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_GNSS_NMEA_FILTER_SWITCH = "ro.oplus.gnss.nmea_filter.switch";
    public static final String GNSS_NMEA_FILTER_SWITCH = SystemProperties.get(PROPERTY_GNSS_NMEA_FILTER_SWITCH, "-1");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_GNSS_NMEA_THREAD_SWITCH = "ro.oplus.gnss.nmea_thread.switch";
    public static final String GNSS_NMEA_THREAD_SWITCH = SystemProperties.get(PROPERTY_GNSS_NMEA_THREAD_SWITCH, "-1");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_GPS_LAB_SWITCH = "ro.oplus.gnss.lab.switch";
    public static final String GNSS_LAB_SWITCH = SystemProperties.get(PROPERTY_GPS_LAB_SWITCH, "-1");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_CAMERA_SOUND_FORCED = "ro.oplus.audio.camerasound.forced";
    public static final String CAMERA_SOUND_FORCED = SystemProperties.get(PROPERTY_CAMERA_SOUND_FORCED, "false");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_AUDIO_NOTSUPPORT_AUDIOSCENCE = "ro.oplus.audio.notsupport.audioscence";
    public static final String NOTSUPPORT_AUDIOSCENCE_STATUS = SystemProperties.get(PROPERTY_AUDIO_NOTSUPPORT_AUDIOSCENCE, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_AUDIO_SUPPORT_FOLDINGMODE = "ro.oplus.audio.support.foldingmode";
    public static final String AUDIO_SUPPORT_FOLDINGMODE_STATUS = SystemProperties.get(PROPERTY_AUDIO_SUPPORT_FOLDINGMODE, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_OPLUS_CUSTOM_MEDIA_DEFAULT_VOLUME = "ro.oplus.audio.custom.media_vol_default";
    public static final String CUSTOM_MEDIA_DEFAULT_VOLUME = SystemProperties.get(PROPERTY_OPLUS_CUSTOM_MEDIA_DEFAULT_VOLUME, "false");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_SCREEN_UNDERLIGHTSENSOR_REGION = "ro.oplus.lcd.display.screen.underlightsensor.region";
    public static final String SCREEN_UNDERLIGHTSENSOR_REGION = SystemProperties.get(PROPERTY_SCREEN_UNDERLIGHTSENSOR_REGION, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_BRIGHTNESS_XS = "ro.oplus.display.brightness.xs";
    public static final String BRIGHTNESS_XS = SystemProperties.get(PROPERTY_BRIGHTNESS_XS, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_BRIGHTNESS_YS = "ro.oplus.display.brightness.ys";
    public static final String BRIGHTNESS_YS = SystemProperties.get(PROPERTY_BRIGHTNESS_YS, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_BRIGHTNESS_HBM_XS = "ro.display.brightness.hbm_xs";
    public static final String BRIGHTNESS_HBM_XS = SystemProperties.get(PROPERTY_BRIGHTNESS_HBM_XS, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_BRIGHTNESS_HBM_XS_MIN = "ro.display.brightness.hbm_xs_min";
    public static final String BRIGHTNESS_HBM_XS_MIN = SystemProperties.get(PROPERTY_BRIGHTNESS_HBM_XS_MIN, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_BRIGHTNESS_HBM_XS_MAX = "ro.display.brightness.hbm_xs_max";
    public static final String BRIGHTNESS_HBM_XS_MAX = SystemProperties.get(PROPERTY_BRIGHTNESS_HBM_XS_MAX, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_BRIGHTNESS_HBM_YS = "ro.oplus.display.brightness.hbm_ys";
    public static final String BRIGHTNESS_HBM_YS = SystemProperties.get(PROPERTY_BRIGHTNESS_HBM_YS, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_BRIGHTNESS_DEFAULT_BRIGHTNESS = "ro.oplus.display.brightness.default_brightness";
    public static final String BRIGHTNESS_DEFAULT_BRIGHTNESS = SystemProperties.get(PROPERTY_BRIGHTNESS_DEFAULT_BRIGHTNESS, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_BRIGHTNESS_NORMAL_MAX_BRIGHTNESS = "ro.oplus.display.brightness.normal_max_brightness";
    public static final String BRIGHTNESS_NORMAL_MAX_BRIGHTNESS = SystemProperties.get(PROPERTY_BRIGHTNESS_NORMAL_MAX_BRIGHTNESS, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_BRIGHTNESS_NORMAL_MIN_BRIGHTNESS = "ro.oplus.display.brightness.normal_min_brightness";
    public static final String BRIGHTNESS_NORMAL_MIN_BRIGHTNESS = SystemProperties.get(PROPERTY_BRIGHTNESS_NORMAL_MIN_BRIGHTNESS, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_BRIGHTNESS_MIN_LIGHT_IN_DNM = "ro.oplus.display.brightness.min_light_in_dnm";
    public static final String BRIGHTNESS_MIN_LIGHT_IN_DNM = SystemProperties.get(PROPERTY_BRIGHTNESS_MIN_LIGHT_IN_DNM, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_BRIGHTNESS_PANEL_NUM = "ro.oplus.display.brightness.panel_num";
    public static final String BRIGHTNESS_PANEL_NUM = SystemProperties.get(PROPERTY_BRIGHTNESS_PANEL_NUM, IOplusUIFirstManager.APP_START_ANIMATION);
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_DENSITY_SCREENZOOM_FDH = "ro.oplus.display.density.screenzoom.fdh";
    public static final String DENSITY_SCREENZOOM_FDH = SystemProperties.get(PROPERTY_DENSITY_SCREENZOOM_FDH, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_DENSITY_SCREENZOOM_QDH = "ro.oplus.display.density.screenzoom.qdh";
    public static final String DENSITY_SCREENZOOM_QDH = SystemProperties.get(PROPERTY_DENSITY_SCREENZOOM_QDH, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_SCREEN_HETEROMORPHISM = "ro.oplus.display.screen.heteromorphism";
    public static final String SCREEN_HETEROMORPHISM = SystemProperties.get(PROPERTY_SCREEN_HETEROMORPHISM, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPETY_RO_BRIGHTNESS_MODE = "ro.display.brightness.brightness.mode";
    public static final String RO_BRIGHTNESS_MODE = SystemProperties.get(PROPETY_RO_BRIGHTNESS_MODE, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPETY_RO_BRIGHTNESS_MODE_EXP_PER_20 = "ro.display.brightness.mode.exp.per_20";
    public static final String RO_BRIGHTNESS_MODE_EXP_PER_20 = SystemProperties.get(PROPETY_RO_BRIGHTNESS_MODE_EXP_PER_20, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_SENSOR_TYPE = "ro.oplus.display.sensortype";
    public static final String SENSOR_TYPE = SystemProperties.get(PROPERTY_SENSOR_TYPE, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_SCREENHOLE_POSITION = "ro.oplus.display.screenhole.positon";
    public static final String SCREENHOLE_POSITION = SystemProperties.get(PROPERTY_SCREENHOLE_POSITION, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_RC_SIZE = "ro.oplus.display.rc.size";
    public static final String RC_SIZE = SystemProperties.get(PROPERTY_RC_SIZE, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_BRIGHTNESS_DELTA_UP_SCALE = "ro.oplus.display.brightness.delta_up_scale";
    public static final String BRIGHTNESS_DELTA_UP_SCALE = SystemProperties.get(PROPERTY_BRIGHTNESS_DELTA_UP_SCALE, "0.5");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_BRIGHTNESS_DELTA_DOWN_SCALE = "ro.oplus.display.brightness.delta_down_scale";
    public static final String BRIGHTNESS_DELTA_DOWN_SCALE = SystemProperties.get(PROPERTY_BRIGHTNESS_DELTA_DOWN_SCALE, "0.15");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_COLORMODE_VIVID = "ro.oplus.display.colormode.vivid";
    public static final String COLORMODE_VIVID = SystemProperties.get(PROPERTY_COLORMODE_VIVID, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_COLORMODE_SOFT = "ro.oplus.display.colormode.soft";
    public static final String COLORMODE_SOFT = SystemProperties.get(PROPERTY_COLORMODE_SOFT, IOplusUIFirstManager.APP_START_ANIMATION);
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_COLORMODE_CINEMA = "ro.oplus.display.colormode.cinema";
    public static final String COLORMODE_CINEMA = SystemProperties.get(PROPERTY_COLORMODE_CINEMA, IOplusUIFirstManager.APP_START_ANIMATION);
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_COLORMODE_COLORFUL = "ro.oplus.display.colormode.colorful";
    public static final String COLORMODE_COLORFUL = SystemProperties.get(PROPERTY_COLORMODE_COLORFUL, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_COLORMODE_VIVID_RENDERINTENT = "ro.oplus.display.colormode.vivid.renderintent";
    public static final String COLORMODE_VIVID_RENDERINTENT = SystemProperties.get(PROPERTY_COLORMODE_VIVID_RENDERINTENT, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_COLORMODE_SOFT_RENDERINTENT = "ro.oplus.display.colormode.soft.renderintent";
    public static final String COLORMODE_SOFT_RENDERINTENT = SystemProperties.get(PROPERTY_COLORMODE_SOFT_RENDERINTENT, "256");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_ENGINEERING_FEATURE_LOW_VOLUME = "ro.oplus.audio.low_volume_for_engineering";
    public static final String ENGINEERING_FEATURE_LOW_VOLUME = SystemProperties.get(PROPERTY_ENGINEERING_FEATURE_LOW_VOLUME, "false");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_MUITIPLE_COLORMODE = "ro.oplus.multiple.colormode";
    public static final String MUITIPLE_COLORMODE = SystemProperties.get(PROPERTY_MUITIPLE_COLORMODE, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_VRR = "persist.oplus.display.vrr";
    public static final String VRR_ENABLED = SystemProperties.get(PROPERTY_VRR, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.PERSIST_PROPERTY)
    public static final String PROPERTY_ADFR = "persist.oplus.display.vrr.adfr";
    public static final String ADFR_ENABLED = SystemProperties.get(PROPERTY_ADFR, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.SYS_PROPERTY)
    public static final String PROPETY_SIM_SWITCH_FIRST = "sys.sim.switch.first";
    public static final String SIM_SWITCH_FIRST_NAME = SystemProperties.get(PROPETY_SIM_SWITCH_FIRST, "");
    @OplusProperty(OplusProperty.OplusPropertyType.SYS_PROPERTY)
    public static final String PROPETY_SIM_SWITCH_CURRENT = "sys.sim.switch.current";
    public static final String SIM_SWITCH_CURRENT_NAME = SystemProperties.get(PROPETY_SIM_SWITCH_CURRENT, "");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPETY_NONE_CLEAR_SWITCH_FIRST = "ro.none.clear.switch.first";
    public static final String NONE_CLEAR_SWITCH_FIRST = SystemProperties.get(PROPETY_NONE_CLEAR_SWITCH_FIRST, "");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_CUSTOM_HAPTIC_LRA_VALUE_SET = "ro.oplus.haptic.lra_value_set";
    public static final String CUSTOM_HAPTIC_LRA_VALUE_SET = SystemProperties.get(PROPERTY_CUSTOM_HAPTIC_LRA_VALUE_SET, "false");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_CUSTOM_HAPTIC_LRA_RANGE_MIN = "ro.oplus.haptic.lra_range_min";
    public static final String CUSTOM_HAPTIC_LRA_RANGE_MIN = SystemProperties.get(PROPERTY_CUSTOM_HAPTIC_LRA_RANGE_MIN, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_CUSTOM_HAPTIC_LRA_RANGE_GAP1 = "ro.oplus.haptic.lra_range_gap1";
    public static final String CUSTOM_HAPTIC_LRA_RANGE_GAP1 = SystemProperties.get(PROPERTY_CUSTOM_HAPTIC_LRA_RANGE_GAP1, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_CUSTOM_HAPTIC_LRA_RANGE_GAP2 = "ro.oplus.haptic.lra_range_gap2";
    public static final String CUSTOM_HAPTIC_LRA_RANGE_GAP2 = SystemProperties.get(PROPERTY_CUSTOM_HAPTIC_LRA_RANGE_GAP2, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_CUSTOM_HAPTIC_LRA_RANGE_GAP3 = "ro.oplus.haptic.lra_range_gap3";
    public static final String CUSTOM_HAPTIC_LRA_RANGE_GAP3 = SystemProperties.get(PROPERTY_CUSTOM_HAPTIC_LRA_RANGE_GAP3, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_CUSTOM_HAPTIC_LRA_RANGE_GAP4 = "ro.oplus.haptic.lra_range_gap4";
    public static final String CUSTOM_HAPTIC_LRA_RANGE_GAP4 = SystemProperties.get(PROPERTY_CUSTOM_HAPTIC_LRA_RANGE_GAP4, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_CUSTOM_HAPTIC_LRA_RANGE_MAX = "ro.oplus.haptic.lra_range_max";
    public static final String CUSTOM_HAPTIC_LRA_RANGE_MAX = SystemProperties.get(PROPERTY_CUSTOM_HAPTIC_LRA_RANGE_MAX, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_CUSTOM_CUSTOM_HAPTIC_LRA_LEVEL_LOW = "ro.oplus.haptic.lra_level_low";
    public static final String CUSTOM_HAPTIC_LRA_LEVEL_LOW = SystemProperties.get(PROPERTY_CUSTOM_CUSTOM_HAPTIC_LRA_LEVEL_LOW, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_CUSTOM_HAPTIC_LRA_LEVEL_MIDDLE = "ro.oplus.haptic.lra_level_middle";
    public static final String CUSTOM_HAPTIC_LRA_LEVEL_MIDDLE = SystemProperties.get(PROPERTY_CUSTOM_HAPTIC_LRA_LEVEL_MIDDLE, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_CUSTOM_HAPTIC_LRA_LEVEL_MIDDLE1 = "ro.oplus.haptic.lra_level_middle1";
    public static final String CUSTOM_HAPTIC_LRA_LEVEL_MIDDLE1 = SystemProperties.get(PROPERTY_CUSTOM_HAPTIC_LRA_LEVEL_MIDDLE1, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_CUSTOM_HAPTIC_LRA_LEVEL_MIDDLE2 = "ro.oplus.haptic.lra_level_middle2";
    public static final String CUSTOM_HAPTIC_LRA_LEVEL_MIDDLE2 = SystemProperties.get(PROPERTY_CUSTOM_HAPTIC_LRA_LEVEL_MIDDLE2, "0");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_CUSTOM_HAPTIC_LRA_LEVEL_HIGH = "ro.oplus.haptic.lra_level_high";
    public static final String CUSTOM_HAPTIC_LRA_LEVEL_HIGH = SystemProperties.get(PROPERTY_CUSTOM_HAPTIC_LRA_LEVEL_HIGH, "0");
    public static final String PROPERTY_CONTROL_SHUTDOWN_DELAYTIME = "ro.oplus.control.shutdown.delaytime";
    public static final int CONTROL_SHUTDOWN_DELAYTIME = SystemProperties.getInt(PROPERTY_CONTROL_SHUTDOWN_DELAYTIME, 0);
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPETY_OPLUS_OPCARRIER_ID = "ro.boot.opcarrier";
    public static final String OPLUS_OPCARRIER_ID = SystemProperties.get(PROPETY_OPLUS_OPCARRIER_ID, "");
    @OplusProperty(OplusProperty.OplusPropertyType.RO_PROPERTY)
    public static final String PROPERTY_MUITIPLE_CLOSEVFX = "ro.oplus.multiple.closeVFX";
    public static final String MUITIPLE_CLOSEVFX = SystemProperties.get(PROPERTY_MUITIPLE_CLOSEVFX, "0");

    private static String getString(String property) {
        return SystemProperties.get(property, "unknown");
    }
}
