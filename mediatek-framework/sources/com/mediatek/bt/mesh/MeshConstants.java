package com.mediatek.bt.mesh;
/* loaded from: classes.dex */
public class MeshConstants {
    public static final boolean DEBUG = true;
    public static final int MESH_ACCESS_MSG_STATUS_CANNOT_BIND = 13;
    public static final int MESH_ACCESS_MSG_STATUS_CANNOT_REMOVE = 12;
    public static final int MESH_ACCESS_MSG_STATUS_CANNOT_SET = 15;
    public static final int MESH_ACCESS_MSG_STATUS_CANNOT_UPDATE = 11;
    public static final int MESH_ACCESS_MSG_STATUS_FEATURE_NOT_SUPPORTED = 10;
    public static final int MESH_ACCESS_MSG_STATUS_INSUFFICIENT_RESOURCES = 5;
    public static final int MESH_ACCESS_MSG_STATUS_INVALID_ADDRESS = 1;
    public static final int MESH_ACCESS_MSG_STATUS_INVALID_APPKEY = 3;
    public static final int MESH_ACCESS_MSG_STATUS_INVALID_BINDING = 17;
    public static final int MESH_ACCESS_MSG_STATUS_INVALID_MODEL = 2;
    public static final int MESH_ACCESS_MSG_STATUS_INVALID_NETKEY = 4;
    public static final int MESH_ACCESS_MSG_STATUS_INVALID_PUBLISH_PARAMS = 7;
    public static final int MESH_ACCESS_MSG_STATUS_KEY_INDEX_ALREADY_STORED = 6;
    public static final int MESH_ACCESS_MSG_STATUS_NOT_A_SUBSCRIBE_MODEL = 8;
    public static final int MESH_ACCESS_MSG_STATUS_STORAGE_FAILURE = 9;
    public static final int MESH_ACCESS_MSG_STATUS_SUCCESS = 0;
    public static final int MESH_ACCESS_MSG_STATUS_TEMPORARILY_UNABLE_TO_CHANGE_STATE = 14;
    public static final int MESH_ACCESS_MSG_STATUS_UNSPECIFIED_ERROR = 16;
    public static final int MESH_ADDRESS_TYPE_GROUP = 3;
    public static final int MESH_ADDRESS_TYPE_UNASSIGNED = 0;
    public static final int MESH_ADDRESS_TYPE_UNICAST = 1;
    public static final int MESH_ADDRESS_TYPE_VIRTUAL = 2;
    public static final int MESH_ADDR_GROUP_FRIENDS_VALUE = 65533;
    public static final int MESH_ADDR_GROUP_NODES_VALUE = 65535;
    public static final int MESH_ADDR_GROUP_PROXIES_VALUE = 65532;
    public static final int MESH_ADDR_GROUP_RELAYS_VALUE = 65534;
    public static final int MESH_BEARER_ADV = 0;
    public static final int MESH_BEARER_GATT = 1;
    public static final int MESH_BEARER_GATT_STATUS_CONNECTED = 0;
    public static final int MESH_BEARER_GATT_STATUS_CONNECT_FAILED = 5;
    public static final int MESH_BEARER_GATT_STATUS_DISCONNECTED = 1;
    public static final int MESH_BEARER_GATT_STATUS_NO_CHARACTERISTIC = 3;
    public static final int MESH_BEARER_GATT_STATUS_NO_SERVICE = 2;
    public static final int MESH_BEARER_GATT_STATUS_WRITE_CCCD_FAILED = 4;
    public static final int MESH_BLE_ADDR_TYPE_PUBLIC = 0;
    public static final int MESH_BLE_ADDR_TYPE_RANDOM_NON_RESOLVABLE = 3;
    public static final int MESH_BLE_ADDR_TYPE_RANDOM_RESOLVABLE = 2;
    public static final int MESH_BLE_ADDR_TYPE_RANDOM_STATIC = 1;
    public static final int MESH_DUMP_TYPE_ADV = 7;
    public static final int MESH_DUMP_TYPE_ALL = 8;
    public static final int MESH_DUMP_TYPE_CONFIG = 3;
    public static final int MESH_DUMP_TYPE_LPN = 5;
    public static final int MESH_DUMP_TYPE_MODEL = 4;
    public static final int MESH_DUMP_TYPE_NETWORK = 1;
    public static final int MESH_DUMP_TYPE_PROXY = 6;
    public static final int MESH_DUMP_TYPE_TRANSPORT = 2;
    public static final int MESH_DUMP_TYPE_UUID = 0;
    public static final int MESH_ERROR_NO_RESOURCE_TO_ADD_REPLAYPROTECTION = 0;
    public static final int MESH_FEATURE_FRIEND = 4;
    public static final int MESH_FEATURE_LPN = 8;
    public static final int MESH_FEATURE_MASK_HEARTBEAT = 1;
    public static final int MESH_FEATURE_MASK_OTA = 2;
    public static final int MESH_FEATURE_NONE = 0;
    public static final int MESH_FEATURE_PROXY = 2;
    public static final int MESH_FEATURE_RELAY = 1;
    public static final int MESH_FLASH_RECORD_ALL = Integer.MAX_VALUE;
    public static final int MESH_FLASH_RECORD_DATA = 1;
    public static final int MESH_FLASH_RECORD_REMOTE_NODE = 4;
    public static final int MESH_FLASH_RECORD_ROLE_PROVISIONEE = 0;
    public static final int MESH_FLASH_RECORD_ROLE_PROVISIONER = Integer.MIN_VALUE;
    public static final int MESH_FLASH_RECORD_SEQ_NUM = 2;
    public static final int MESH_FRIENDSHIP_ESTABLISHED = 1;
    public static final int MESH_FRIENDSHIP_ESTABLISH_FAILED = 2;
    public static final int MESH_FRIENDSHIP_REQUEST_FRIEND_TIMEOUT = 3;
    public static final int MESH_FRIENDSHIP_SELECT_FRIEND_TIMEOUT = 4;
    public static final int MESH_FRIENDSHIP_TERMINATED = 0;
    public static final int MESH_GATT_SERVICE_PROVISION = 1;
    public static final int MESH_GATT_SERVICE_PROXY = 0;
    public static final int MESH_IV_UPDATE_STATE_IN_PROGRESS = 1;
    public static final int MESH_IV_UPDATE_STATE_NORMAL = 0;
    public static final int MESH_KEY_OP_ADD = 0;
    public static final int MESH_KEY_OP_REVOKE_OLD_NETKEY = 3;
    public static final int MESH_KEY_OP_UPDATE = 1;
    public static final int MESH_KEY_OP_USE_NEW_NETKEY = 2;
    public static final int MESH_KEY_REFRESH_STATE_1 = 1;
    public static final int MESH_KEY_REFRESH_STATE_2 = 2;
    public static final int MESH_KEY_REFRESH_STATE_3 = 3;
    public static final int MESH_KEY_REFRESH_STATE_NONE = 0;
    public static final int MESH_KEY_SIZE = 16;
    public static final int MESH_MAX_FILTER_NETWORK_NUMBER = 4;
    public static final int MESH_MODEL_COMPANY_ID_NONE = 65535;
    public static final int MESH_MODEL_DATA_OP_ADD_CONFIGURATION_CLIENT = 4;
    public static final int MESH_MODEL_DATA_OP_ADD_CONFIGURATION_SERVER = 3;
    public static final int MESH_MODEL_DATA_OP_ADD_CTL_CLIENT = 11;
    public static final int MESH_MODEL_DATA_OP_ADD_CTL_SETUP_SERVER = 8;
    public static final int MESH_MODEL_DATA_OP_ADD_ELEMENT = 1;
    public static final int MESH_MODEL_DATA_OP_ADD_GENERIC_LEVEL_CLIENT = 15;
    public static final int MESH_MODEL_DATA_OP_ADD_GENERIC_LEVEL_SERVER = 14;
    public static final int MESH_MODEL_DATA_OP_ADD_GENERIC_ONOFF_CLIENT = 16;
    public static final int MESH_MODEL_DATA_OP_ADD_GENERIC_ONOFF_SERVER = 7;
    public static final int MESH_MODEL_DATA_OP_ADD_GENERIC_POWER_ONOFF_CLIENT = 9;
    public static final int MESH_MODEL_DATA_OP_ADD_HEALTH_CLIENT = 6;
    public static final int MESH_MODEL_DATA_OP_ADD_HEALTH_SERVER = 5;
    public static final int MESH_MODEL_DATA_OP_ADD_HSL_CLIENT = 13;
    public static final int MESH_MODEL_DATA_OP_ADD_HSL_SETUP_SERVER = 12;
    public static final int MESH_MODEL_DATA_OP_ADD_LIGHTNESS_CLIENT = 10;
    public static final int MESH_MODEL_DATA_OP_ADD_MODEL = 500;
    public static final int MESH_MODEL_DATA_OP_SET_COMPOSITION_DATA_HEADER = 0;
    public static final int MESH_MODEL_DATA_OP_SET_ELEMENT_ADDR = 2;
    public static final int MESH_MODEL_ELEMENT_LOCATION_BACK = 257;
    public static final int MESH_MODEL_ELEMENT_LOCATION_BOTTOM = 259;
    public static final int MESH_MODEL_ELEMENT_LOCATION_FIRST = 1;
    public static final int MESH_MODEL_ELEMENT_LOCATION_FRONT = 256;
    public static final int MESH_MODEL_ELEMENT_LOCATION_LOWER = 261;
    public static final int MESH_MODEL_ELEMENT_LOCATION_MAIN = 262;
    public static final int MESH_MODEL_ELEMENT_LOCATION_SECOND = 2;
    public static final int MESH_MODEL_ELEMENT_LOCATION_TOP = 258;
    public static final int MESH_MODEL_ELEMENT_LOCATION_UPPER = 260;
    public static final int MESH_MODEL_SIG_MODEL_ID_CONFIGURATION_CLIENT = 1;
    public static final int MESH_MODEL_SIG_MODEL_ID_CONFIGURATION_SERVER = 0;
    public static final int MESH_MODEL_SIG_MODEL_ID_FIRMWARE_DISTRIBUTION_CLIENT = 65027;
    public static final int MESH_MODEL_SIG_MODEL_ID_FIRMWARE_DISTRIBUTION_SERVER = 65026;
    public static final int MESH_MODEL_SIG_MODEL_ID_FIRMWARE_UPDATE_CLIENT = 65025;
    public static final int MESH_MODEL_SIG_MODEL_ID_FIRMWARE_UPDATE_SERVER = 65024;
    public static final int MESH_MODEL_SIG_MODEL_ID_GENERIC_ADMIN_PROPERTY_SERVER = 4113;
    public static final int MESH_MODEL_SIG_MODEL_ID_GENERIC_BATTERY_CLIENT = 4109;
    public static final int MESH_MODEL_SIG_MODEL_ID_GENERIC_BATTERY_SERVER = 4108;
    public static final int MESH_MODEL_SIG_MODEL_ID_GENERIC_CLIENT_PROPERTY_SERVER = 4116;
    public static final int MESH_MODEL_SIG_MODEL_ID_GENERIC_DEFAULT_TRANSITION_TIME_CLIENT = 4101;
    public static final int MESH_MODEL_SIG_MODEL_ID_GENERIC_DEFAULT_TRANSITION_TIME_SERVER = 4100;
    public static final int MESH_MODEL_SIG_MODEL_ID_GENERIC_LEVEL_CLIENT = 4099;
    public static final int MESH_MODEL_SIG_MODEL_ID_GENERIC_LEVEL_SERVER = 4098;
    public static final int MESH_MODEL_SIG_MODEL_ID_GENERIC_LOCATION_CLIENT = 4112;
    public static final int MESH_MODEL_SIG_MODEL_ID_GENERIC_LOCATION_SERVER = 4110;
    public static final int MESH_MODEL_SIG_MODEL_ID_GENERIC_LOCATION_SETUP_SERVER = 4111;
    public static final int MESH_MODEL_SIG_MODEL_ID_GENERIC_MANUFACTURER_PROPERTY_SERVER = 4114;
    public static final int MESH_MODEL_SIG_MODEL_ID_GENERIC_ONOFF_CLIENT = 4097;
    public static final int MESH_MODEL_SIG_MODEL_ID_GENERIC_ONOFF_SERVER = 4096;
    public static final int MESH_MODEL_SIG_MODEL_ID_GENERIC_POWER_LEVEL_CLIENT = 4107;
    public static final int MESH_MODEL_SIG_MODEL_ID_GENERIC_POWER_LEVEL_SERVER = 4105;
    public static final int MESH_MODEL_SIG_MODEL_ID_GENERIC_POWER_LEVEL_SETUP_SERVER = 4106;
    public static final int MESH_MODEL_SIG_MODEL_ID_GENERIC_POWER_ONOFF_CLIENT = 4104;
    public static final int MESH_MODEL_SIG_MODEL_ID_GENERIC_POWER_ONOFF_SERVER = 4102;
    public static final int MESH_MODEL_SIG_MODEL_ID_GENERIC_POWER_ONOFF_SETUP_SERVER = 4103;
    public static final int MESH_MODEL_SIG_MODEL_ID_GENERIC_PROPERTY_CLIENT = 4117;
    public static final int MESH_MODEL_SIG_MODEL_ID_GENERIC_USER_PROPERTY_SERVER = 4115;
    public static final int MESH_MODEL_SIG_MODEL_ID_HEALTH_CLIENT = 3;
    public static final int MESH_MODEL_SIG_MODEL_ID_HEALTH_SERVER = 2;
    public static final int MESH_MODEL_SIG_MODEL_ID_LIGHT_CTL_CLIENT = 4869;
    public static final int MESH_MODEL_SIG_MODEL_ID_LIGHT_CTL_SERVER = 4867;
    public static final int MESH_MODEL_SIG_MODEL_ID_LIGHT_CTL_SETUP_SERVER = 4868;
    public static final int MESH_MODEL_SIG_MODEL_ID_LIGHT_CTL_TEMPERATURE_SERVER = 4870;
    public static final int MESH_MODEL_SIG_MODEL_ID_LIGHT_HSL_CLIENT = 4873;
    public static final int MESH_MODEL_SIG_MODEL_ID_LIGHT_HSL_HUE_SERVER = 4874;
    public static final int MESH_MODEL_SIG_MODEL_ID_LIGHT_HSL_SATURATION_SERVER = 4875;
    public static final int MESH_MODEL_SIG_MODEL_ID_LIGHT_HSL_SERVER = 4871;
    public static final int MESH_MODEL_SIG_MODEL_ID_LIGHT_HSL_SETUP_SERVER = 4872;
    public static final int MESH_MODEL_SIG_MODEL_ID_LIGHT_LC_CLIENT = 4881;
    public static final int MESH_MODEL_SIG_MODEL_ID_LIGHT_LC_SERVER = 4879;
    public static final int MESH_MODEL_SIG_MODEL_ID_LIGHT_LC_SETUP_SERVER = 4880;
    public static final int MESH_MODEL_SIG_MODEL_ID_LIGHT_LIGHTNESS_CLIENT = 4866;
    public static final int MESH_MODEL_SIG_MODEL_ID_LIGHT_LIGHTNESS_SERVER = 4864;
    public static final int MESH_MODEL_SIG_MODEL_ID_LIGHT_LIGHTNESS_SETUP_SERVER = 4865;
    public static final int MESH_MODEL_SIG_MODEL_ID_LIGHT_XYL_CLIENT = 4878;
    public static final int MESH_MODEL_SIG_MODEL_ID_LIGHT_XYL_SERVER = 4876;
    public static final int MESH_MODEL_SIG_MODEL_ID_LIGHT_XYL_SETUP_SERVER = 4877;
    public static final int MESH_MODEL_SIG_MODEL_ID_OBJECT_TRANSFER_CLIENT = 65281;
    public static final int MESH_MODEL_SIG_MODEL_ID_OBJECT_TRANSFER_SERVER = 65280;
    public static final int MESH_MODEL_SIG_MODEL_ID_SCENE_CLIENT = 4613;
    public static final int MESH_MODEL_SIG_MODEL_ID_SCENE_SERVER = 4611;
    public static final int MESH_MODEL_SIG_MODEL_ID_SCENE_SETUP_SERVER = 4612;
    public static final int MESH_MODEL_SIG_MODEL_ID_SCHEDULER_CLIENT = 4616;
    public static final int MESH_MODEL_SIG_MODEL_ID_SCHEDULER_SERVER = 4614;
    public static final int MESH_MODEL_SIG_MODEL_ID_SCHEDULER_SETUP_SERVER = 4615;
    public static final int MESH_MODEL_SIG_MODEL_ID_SENSOR_CLIENT = 4355;
    public static final int MESH_MODEL_SIG_MODEL_ID_SENSOR_SERVER = 4353;
    public static final int MESH_MODEL_SIG_MODEL_ID_SENSOR_SETUP_SERVER = 4354;
    public static final int MESH_MODEL_SIG_MODEL_ID_TIME_CLIENT = 4610;
    public static final int MESH_MODEL_SIG_MODEL_ID_TIME_SERVER = 4608;
    public static final int MESH_MODEL_SIG_MODEL_ID_TIME_SETUP_SERVER = 4609;
    public static final int MESH_MODE_OFF = 0;
    public static final int MESH_MODE_ON = 1;
    public static final int MESH_MODE_STANDBY = 2;
    public static final int MESH_MSG_CONFIG_APPKEY_ADD = 0;
    public static final int MESH_MSG_CONFIG_APPKEY_DELETE = 32768;
    public static final int MESH_MSG_CONFIG_APPKEY_GET = 32769;
    public static final int MESH_MSG_CONFIG_APPKEY_LIST = 32770;
    public static final int MESH_MSG_CONFIG_APPKEY_STATUS = 32771;
    public static final int MESH_MSG_CONFIG_APPKEY_UPDATE = 1;
    public static final int MESH_MSG_CONFIG_BEACON_GET = 32777;
    public static final int MESH_MSG_CONFIG_BEACON_SET = 32778;
    public static final int MESH_MSG_CONFIG_BEACON_STATUS = 32779;
    public static final int MESH_MSG_CONFIG_COMPOSITION_DATA_GET = 32776;
    public static final int MESH_MSG_CONFIG_COMPOSITION_DATA_STATUS = 2;
    public static final int MESH_MSG_CONFIG_DEFAULT_TTL_GET = 32780;
    public static final int MESH_MSG_CONFIG_DEFAULT_TTL_SET = 32781;
    public static final int MESH_MSG_CONFIG_DEFAULT_TTL_STATUS = 32782;
    public static final int MESH_MSG_CONFIG_FRIEND_GET = 32783;
    public static final int MESH_MSG_CONFIG_FRIEND_SET = 32784;
    public static final int MESH_MSG_CONFIG_FRIEND_STATUS = 32785;
    public static final int MESH_MSG_CONFIG_GATT_PROXY_GET = 32786;
    public static final int MESH_MSG_CONFIG_GATT_PROXY_SET = 32787;
    public static final int MESH_MSG_CONFIG_GATT_PROXY_STATUS = 32788;
    public static final int MESH_MSG_CONFIG_HEARTBEAT_PUBLICATION_GET = 32824;
    public static final int MESH_MSG_CONFIG_HEARTBEAT_PUBLICATION_SET = 32825;
    public static final int MESH_MSG_CONFIG_HEARTBEAT_PUBLICATION_STATUS = 6;
    public static final int MESH_MSG_CONFIG_HEARTBEAT_SUBSCRIPTION_GET = 32826;
    public static final int MESH_MSG_CONFIG_HEARTBEAT_SUBSCRIPTION_SET = 32827;
    public static final int MESH_MSG_CONFIG_HEARTBEAT_SUBSCRIPTION_STATUS = 32828;
    public static final int MESH_MSG_CONFIG_KEY_REFRESH_PHASE_GET = 32789;
    public static final int MESH_MSG_CONFIG_KEY_REFRESH_PHASE_SET = 32790;
    public static final int MESH_MSG_CONFIG_KEY_REFRESH_PHASE_STATUS = 32791;
    public static final int MESH_MSG_CONFIG_LOW_POWER_NODE_POLL_TIMEOUT_GET = 32813;
    public static final int MESH_MSG_CONFIG_LOW_POWER_NODE_POLL_TIMEOUT_STATUS = 32814;
    public static final int MESH_MSG_CONFIG_MODEL_APP_BIND = 32829;
    public static final int MESH_MSG_CONFIG_MODEL_APP_STATUS = 32830;
    public static final int MESH_MSG_CONFIG_MODEL_APP_UNBIND = 32831;
    public static final int MESH_MSG_CONFIG_MODEL_PUBLICATION_GET = 32792;
    public static final int MESH_MSG_CONFIG_MODEL_PUBLICATION_SET = 3;
    public static final int MESH_MSG_CONFIG_MODEL_PUBLICATION_STATUS = 32793;
    public static final int MESH_MSG_CONFIG_MODEL_PUBLICATION_VIRTUAL_ADDRESS_SET = 32794;
    public static final int MESH_MSG_CONFIG_MODEL_SUBSCRIPTION_ADD = 32795;
    public static final int MESH_MSG_CONFIG_MODEL_SUBSCRIPTION_DELETE = 32796;
    public static final int MESH_MSG_CONFIG_MODEL_SUBSCRIPTION_DELETE_ALL = 32797;
    public static final int MESH_MSG_CONFIG_MODEL_SUBSCRIPTION_OVERWRITE = 32798;
    public static final int MESH_MSG_CONFIG_MODEL_SUBSCRIPTION_STATUS = 32799;
    public static final int MESH_MSG_CONFIG_MODEL_SUBSCRIPTION_VIRTUAL_ADDRESS_ADD = 32800;
    public static final int MESH_MSG_CONFIG_MODEL_SUBSCRIPTION_VIRTUAL_ADDRESS_DELETE = 32801;
    public static final int MESH_MSG_CONFIG_MODEL_SUBSCRIPTION_VIRTUAL_ADDRESS_OVERWRITE = 32802;
    public static final int MESH_MSG_CONFIG_NETKEY_ADD = 32832;
    public static final int MESH_MSG_CONFIG_NETKEY_DELETE = 32833;
    public static final int MESH_MSG_CONFIG_NETKEY_GET = 32834;
    public static final int MESH_MSG_CONFIG_NETKEY_LIST = 32835;
    public static final int MESH_MSG_CONFIG_NETKEY_STATUS = 32836;
    public static final int MESH_MSG_CONFIG_NETKEY_UPDATE = 32837;
    public static final int MESH_MSG_CONFIG_NETWORK_TRANSMIT_GET = 32803;
    public static final int MESH_MSG_CONFIG_NETWORK_TRANSMIT_SET = 32804;
    public static final int MESH_MSG_CONFIG_NETWORK_TRANSMIT_STATUS = 32805;
    public static final int MESH_MSG_CONFIG_NODE_IDENTITY_GET = 32838;
    public static final int MESH_MSG_CONFIG_NODE_IDENTITY_SET = 32839;
    public static final int MESH_MSG_CONFIG_NODE_IDENTITY_STATUS = 32840;
    public static final int MESH_MSG_CONFIG_NODE_RESET = 32841;
    public static final int MESH_MSG_CONFIG_NODE_RESET_STATUS = 32842;
    public static final int MESH_MSG_CONFIG_RELAY_GET = 32806;
    public static final int MESH_MSG_CONFIG_RELAY_SET = 32807;
    public static final int MESH_MSG_CONFIG_RELAY_STATUS = 32808;
    public static final int MESH_MSG_CONFIG_SIG_MODEL_APP_GET = 32843;
    public static final int MESH_MSG_CONFIG_SIG_MODEL_APP_LIST = 32844;
    public static final int MESH_MSG_CONFIG_SIG_MODEL_SUBSCRIPTION_GET = 32809;
    public static final int MESH_MSG_CONFIG_SIG_MODEL_SUBSCRIPTION_LIST = 32810;
    public static final int MESH_MSG_CONFIG_VENDOR_MODEL_APP_GET = 32845;
    public static final int MESH_MSG_CONFIG_VENDOR_MODEL_APP_LIST = 32846;
    public static final int MESH_MSG_CONFIG_VENDOR_MODEL_SUBSCRIPTION_GET = 32811;
    public static final int MESH_MSG_CONFIG_VENDOR_MODEL_SUBSCRIPTION_LIST = 32812;
    public static final int MESH_MSG_GENERIC_DELTA_SET = 33289;
    public static final int MESH_MSG_GENERIC_DELTA_SET_UNACKNOWLEDGED = 33290;
    public static final int MESH_MSG_GENERIC_LEVEL_GET = 33285;
    public static final int MESH_MSG_GENERIC_LEVEL_SET = 33286;
    public static final int MESH_MSG_GENERIC_LEVEL_SET_UNACKNOWLEDGED = 33287;
    public static final int MESH_MSG_GENERIC_LEVEL_STATUS = 33288;
    public static final int MESH_MSG_GENERIC_MOVE_SET = 33291;
    public static final int MESH_MSG_GENERIC_MOVE_SET_UNACKNOWLEDGED = 33292;
    public static final int MESH_MSG_GENERIC_ONOFF_GET = 33281;
    public static final int MESH_MSG_GENERIC_ONOFF_SET = 33282;
    public static final int MESH_MSG_GENERIC_ONOFF_SET_UNRELIABLE = 33283;
    public static final int MESH_MSG_GENERIC_ONOFF_STATUS = 33284;
    public static final int MESH_MSG_HEALTH_ATTENTION_GET = 32772;
    public static final int MESH_MSG_HEALTH_ATTENTION_SET = 32773;
    public static final int MESH_MSG_HEALTH_ATTENTION_SET_UNACKNOWLEDGED = 32774;
    public static final int MESH_MSG_HEALTH_ATTENTION_STATUS = 32775;
    public static final int MESH_MSG_HEALTH_CURRENT_STATUS = 4;
    public static final int MESH_MSG_HEALTH_FAULT_CLEAR = 32815;
    public static final int MESH_MSG_HEALTH_FAULT_CLEAR_UNACKNOWLEDGED = 32816;
    public static final int MESH_MSG_HEALTH_FAULT_GET = 32817;
    public static final int MESH_MSG_HEALTH_FAULT_STATUS = 5;
    public static final int MESH_MSG_HEALTH_FAULT_TEST = 32818;
    public static final int MESH_MSG_HEALTH_FAULT_TEST_UNACKNOWLEDGED = 32819;
    public static final int MESH_MSG_HEALTH_PERIOD_GET = 32820;
    public static final int MESH_MSG_HEALTH_PERIOD_SET = 32821;
    public static final int MESH_MSG_HEALTH_PERIOD_SET_UNACKNOWLEDGED = 32822;
    public static final int MESH_MSG_HEALTH_PERIOD_STATUS = 32823;
    public static final int MESH_MSG_LIGHT_CTL_DEFAULT_GET = 33383;
    public static final int MESH_MSG_LIGHT_CTL_DEFAULT_SET = 33385;
    public static final int MESH_MSG_LIGHT_CTL_DEFAULT_SET_UNACKNOWLEDGED = 33386;
    public static final int MESH_MSG_LIGHT_CTL_DEFAULT_STATUS = 33384;
    public static final int MESH_MSG_LIGHT_CTL_GET = 33373;
    public static final int MESH_MSG_LIGHT_CTL_SET = 33374;
    public static final int MESH_MSG_LIGHT_CTL_SET_UNACKNOWLEDGED = 33375;
    public static final int MESH_MSG_LIGHT_CTL_STATUS = 33376;
    public static final int MESH_MSG_LIGHT_CTL_TEMPERATURE_GET = 33377;
    public static final int MESH_MSG_LIGHT_CTL_TEMPERATURE_RANGE_GET = 33378;
    public static final int MESH_MSG_LIGHT_CTL_TEMPERATURE_RANGE_SET = 33387;
    public static final int MESH_MSG_LIGHT_CTL_TEMPERATURE_RANGE_SET_UNACKNOWLEDGED = 33388;
    public static final int MESH_MSG_LIGHT_CTL_TEMPERATURE_RANGE_STATUS = 33379;
    public static final int MESH_MSG_LIGHT_CTL_TEMPERATURE_SET = 33380;
    public static final int MESH_MSG_LIGHT_CTL_TEMPERATURE_SET_UNACKNOWLEDGED = 33381;
    public static final int MESH_MSG_LIGHT_CTL_TEMPERATURE_STATUS = 33382;
    public static final int MESH_MSG_LIGHT_HSL_DEFAULT_GET = 33403;
    public static final int MESH_MSG_LIGHT_HSL_DEFAULT_SET = 33407;
    public static final int MESH_MSG_LIGHT_HSL_DEFAULT_SET_UNACKNOWLEDGED = 33408;
    public static final int MESH_MSG_LIGHT_HSL_DEFAULT_STATUS = 33404;
    public static final int MESH_MSG_LIGHT_HSL_GET = 33389;
    public static final int MESH_MSG_LIGHT_HSL_HUE_GET = 33390;
    public static final int MESH_MSG_LIGHT_HSL_HUE_SET = 33391;
    public static final int MESH_MSG_LIGHT_HSL_HUE_SET_UNACKNOWLEDGED = 33392;
    public static final int MESH_MSG_LIGHT_HSL_HUE_STATUS = 33393;
    public static final int MESH_MSG_LIGHT_HSL_RANGE_GET = 33405;
    public static final int MESH_MSG_LIGHT_HSL_RANGE_SET = 33409;
    public static final int MESH_MSG_LIGHT_HSL_RANGE_SET_UNACKNOWLEDGED = 33410;
    public static final int MESH_MSG_LIGHT_HSL_RANGE_STATUS = 33406;
    public static final int MESH_MSG_LIGHT_HSL_SATURATION_GET = 33394;
    public static final int MESH_MSG_LIGHT_HSL_SATURATION_SET = 33395;
    public static final int MESH_MSG_LIGHT_HSL_SATURATION_SET_UNACKNOWLEDGED = 33396;
    public static final int MESH_MSG_LIGHT_HSL_SATURATION_STATUS = 33397;
    public static final int MESH_MSG_LIGHT_HSL_SET = 33398;
    public static final int MESH_MSG_LIGHT_HSL_SET_UNACKNOWLEDGED = 33399;
    public static final int MESH_MSG_LIGHT_HSL_STATUS = 33400;
    public static final int MESH_MSG_LIGHT_HSL_TARGET_GET = 33401;
    public static final int MESH_MSG_LIGHT_HSL_TARGET_STATUS = 33402;
    public static final int MESH_MSG_LIGHT_LIGHTNESS_DEFAULT_GET = 33365;
    public static final int MESH_MSG_LIGHT_LIGHTNESS_DEFAULT_SET = 33369;
    public static final int MESH_MSG_LIGHT_LIGHTNESS_DEFAULT_SET_UNACKNOWLEDGED = 33370;
    public static final int MESH_MSG_LIGHT_LIGHTNESS_DEFAULT_STATUS = 33366;
    public static final int MESH_MSG_LIGHT_LIGHTNESS_GET = 33355;
    public static final int MESH_MSG_LIGHT_LIGHTNESS_LAST_GET = 33363;
    public static final int MESH_MSG_LIGHT_LIGHTNESS_LAST_STATUS = 33364;
    public static final int MESH_MSG_LIGHT_LIGHTNESS_LINEAR_GET = 33359;
    public static final int MESH_MSG_LIGHT_LIGHTNESS_LINEAR_SET = 33360;
    public static final int MESH_MSG_LIGHT_LIGHTNESS_LINEAR_SET_UNACKNOWLEDGED = 33361;
    public static final int MESH_MSG_LIGHT_LIGHTNESS_LINEAR_STATUS = 33362;
    public static final int MESH_MSG_LIGHT_LIGHTNESS_RANGE_GET = 33367;
    public static final int MESH_MSG_LIGHT_LIGHTNESS_RANGE_SET = 33371;
    public static final int MESH_MSG_LIGHT_LIGHTNESS_RANGE_SET_UNACKNOWLEDGED = 33372;
    public static final int MESH_MSG_LIGHT_LIGHTNESS_RANGE_STATUS = 33368;
    public static final int MESH_MSG_LIGHT_LIGHTNESS_SET = 33356;
    public static final int MESH_MSG_LIGHT_LIGHTNESS_SET_UNACKNOWLEDGED = 33357;
    public static final int MESH_MSG_LIGHT_LIGHTNESS_STATUS = 33358;
    public static final int MESH_OTA_ERROR_CODE_BUSY = 2;
    public static final int MESH_OTA_ERROR_CODE_NO_RESPONSE = 3;
    public static final int MESH_OTA_ERROR_CODE_SUCCESS = 0;
    public static final int MESH_OTA_ERROR_CODE_USER_STOP = 4;
    public static final int MESH_OTA_ERROR_CODE_WRONG_FIRMWARE_ID = 1;
    public static final int MESH_OTA_EVENT_DISTRIBUTION_DFU_READY = 5;
    public static final int MESH_OTA_EVENT_DISTRIBUTION_ONGOING = 2;
    public static final int MESH_OTA_EVENT_DISTRIBUTION_QUEUED = 4;
    public static final int MESH_OTA_EVENT_DISTRIBUTION_STARTED = 1;
    public static final int MESH_OTA_EVENT_DISTRIBUTION_STARTING = 0;
    public static final int MESH_OTA_EVENT_DISTRIBUTION_STOP = 3;
    public static final int MESH_OTA_INITIATOR_OP_FW_INFO_GET = 1;
    public static final int MESH_OTA_INITIATOR_OP_REG_MSG_HANDLER = 0;
    public static final int MESH_OTA_INITIATOR_OP_START_DISTRIBUTION = 2;
    public static final int MESH_OTA_INITIATOR_OP_STOP_DISTRIBUTION = 3;
    public static final int MESH_OTA_NODE_UPDATE_STATUS_CANCELED = 2;
    public static final int MESH_OTA_NODE_UPDATE_STATUS_DFU_READY = 3;
    public static final int MESH_OTA_NODE_UPDATE_STATUS_IN_PROGRESS = 1;
    public static final int MESH_OTA_NODE_UPDATE_STATUS_SUCCESS = 0;
    public static final int MESH_OTA_OTA_INITIATOR_OP_APPLY_DISTRIBUTION = 4;
    public static final int MESH_PRIMARY_NETWORK_KEY_INDEX = 0;
    public static final int MESH_PROV_CAPABILITY_ALGORITHM_FIPS_P256_ELLIPTIC_CURVE = 1;
    public static final int MESH_PROV_CAPABILITY_OOB_PUBLIC_KEY_TYPE_INBAND = 0;
    public static final int MESH_PROV_CAPABILITY_OOB_PUBLIC_KEY_TYPE_OOB = 1;
    public static final int MESH_PROV_CAPABILITY_OOB_STATIC_TYPE_SUPPORTED = 1;
    public static final int MESH_PROV_FACTOR_AUTHEN_RESULT = 7;
    public static final int MESH_PROV_FACTOR_AUTHEN_VALUE = 6;
    public static final int MESH_PROV_FACTOR_CONFIRMATION_DEVICE = 4;
    public static final int MESH_PROV_FACTOR_CONFIRMATION_KEY = 0;
    public static final int MESH_PROV_FACTOR_CONFIRMATION_PROVISIONER = 3;
    public static final int MESH_PROV_FACTOR_PROV_INVITE = 8;
    public static final int MESH_PROV_FACTOR_PUB_KEY = 5;
    public static final int MESH_PROV_FACTOR_RANDOM_DEVICE = 2;
    public static final int MESH_PROV_FACTOR_RANDOM_PROVISIONER = 1;
    public static final int MESH_PROV_OOB_INFO_FIELD_2D_MACHINE_READABLE_CODE = 4;
    public static final int MESH_PROV_OOB_INFO_FIELD_BAR_CODE = 8;
    public static final int MESH_PROV_OOB_INFO_FIELD_ELECTRONIC_URI = 2;
    public static final int MESH_PROV_OOB_INFO_FIELD_INSIDE_BOX = 4096;
    public static final int MESH_PROV_OOB_INFO_FIELD_INSIDE_MANUAL = 16384;
    public static final int MESH_PROV_OOB_INFO_FIELD_NFC = 16;
    public static final int MESH_PROV_OOB_INFO_FIELD_NUMBER = 32;
    public static final int MESH_PROV_OOB_INFO_FIELD_ON_BOX = 2048;
    public static final int MESH_PROV_OOB_INFO_FIELD_ON_DEVICE = 32768;
    public static final int MESH_PROV_OOB_INFO_FIELD_ON_PIECE_OF_PAPER = 8192;
    public static final int MESH_PROV_OOB_INFO_FIELD_OTHER = 1;
    public static final int MESH_PROV_OOB_INFO_FIELD_STRING = 64;
    public static final int MESH_PROV_START_ALGORITHM_FIPS_P256_ELLIPTIC_CURVE = 0;
    public static final int MESH_PROV_START_AUTHEN_METHOD_INPUT_OOB = 3;
    public static final int MESH_PROV_START_AUTHEN_METHOD_NO_OOB = 0;
    public static final int MESH_PROV_START_AUTHEN_METHOD_OUTPUT_OOB = 2;
    public static final int MESH_PROV_START_AUTHEN_METHOD_STATIC_OOB = 1;
    public static final int MESH_PROV_START_PUBLIC_KEY_NO_OOB = 0;
    public static final int MESH_PROV_START_PUBLIC_KEY_OOB = 1;
    public static final int MESH_REPORT_TYPE_DIRECT_IND = 1;
    public static final int MESH_REPORT_TYPE_IND = 0;
    public static final int MESH_REPORT_TYPE_NONCONN_IND = 3;
    public static final int MESH_REPORT_TYPE_SCAN_IND = 2;
    public static final int MESH_REPORT_TYPE_SCAN_RSP = 4;
    public static final int MESH_ROLE_PROVISIONEE = 0;
    public static final int MESH_ROLE_PROVISIONER = 1;
    public static final int MESH_STATUS_ERROR_FAIL = 9;
    public static final int MESH_STATUS_ERROR_INVALID_ADDR = 3;
    public static final int MESH_STATUS_ERROR_INVALID_KEY = 5;
    public static final int MESH_STATUS_ERROR_INVALID_ROLE = 8;
    public static final int MESH_STATUS_ERROR_INVALID_STATE = 7;
    public static final int MESH_STATUS_ERROR_INVALID_TTL = 4;
    public static final int MESH_STATUS_ERROR_NOT_INIT = 6;
    public static final int MESH_STATUS_ERROR_NULL = 2;
    public static final int MESH_STATUS_ERROR_OOM = 1;
    public static final int MESH_STATUS_SUCCESS = 0;
    public static final int MESH_UUID_SIZE = 16;
    public static final boolean VERBOSE = true;

    private MeshConstants() {
    }
}