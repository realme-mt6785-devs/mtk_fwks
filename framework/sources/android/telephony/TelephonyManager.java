package android.telephony;

import android.Manifest;
import android.annotation.SystemApi;
import android.app.PendingIntent;
import android.app.role.RoleManager;
import android.common.OplusFeatureCache;
import android.compat.Compatibility;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.OutcomeReceiver;
import android.os.ParcelFileDescriptor;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SystemProperties;
import android.os.WorkSource;
import android.provider.Settings;
import android.provider.Telephony;
import android.service.carrier.CarrierIdentifier;
import android.sysprop.TelephonyProperties;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.IBootstrapAuthenticationCallback;
import android.telephony.ICellInfoCallback;
import android.telephony.TelephonyManager;
import android.telephony.TelephonyScanManager;
import android.telephony.data.ApnSetting;
import android.telephony.data.NetworkSlicingConfig;
import android.telephony.emergency.EmergencyNumber;
import android.telephony.gba.UaSecurityProtocolIdentifier;
import android.telephony.ims.aidl.IImsConfig;
import android.telephony.ims.aidl.IImsRegistration;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.android.internal.R;
import com.android.internal.os.BackgroundThread;
import com.android.internal.telephony.CellNetworkScanResult;
import com.android.internal.telephony.DctConstants;
import com.android.internal.telephony.IBooleanConsumer;
import com.android.internal.telephony.ICallForwardingInfoCallback;
import com.android.internal.telephony.IIntegerConsumer;
import com.android.internal.telephony.INumberVerificationCallback;
import com.android.internal.telephony.IOns;
import com.android.internal.telephony.IPhoneSubInfo;
import com.android.internal.telephony.ISetOpportunisticDataCallback;
import com.android.internal.telephony.ISms;
import com.android.internal.telephony.ISub;
import com.android.internal.telephony.ITelephony;
import com.android.internal.telephony.IUpdateAvailableNetworksCallback;
import com.android.internal.telephony.OperatorInfo;
import com.android.internal.telephony.PhoneConstants;
import com.android.internal.telephony.RILConstants;
import com.android.internal.telephony.SmsApplication;
import com.android.internal.util.FunctionalUtils;
import com.android.internal.util.Preconditions;
import com.android.telephony.Rlog;
import com.oplus.permission.IOplusPermissionCheckInjector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.function.Consumer;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
/* loaded from: classes3.dex */
public class TelephonyManager {
    @SystemApi
    public static final String ACTION_ANOMALY_REPORTED = "android.telephony.action.ANOMALY_REPORTED";
    public static final String ACTION_CALL_DISCONNECT_CAUSE_CHANGED = "android.intent.action.CALL_DISCONNECT_CAUSE";
    public static final String ACTION_CARRIER_MESSAGING_CLIENT_SERVICE = "android.telephony.action.CARRIER_MESSAGING_CLIENT_SERVICE";
    public static final String ACTION_CARRIER_SIGNAL_DEFAULT_NETWORK_AVAILABLE = "android.telephony.action.CARRIER_SIGNAL_DEFAULT_NETWORK_AVAILABLE";
    public static final String ACTION_CARRIER_SIGNAL_PCO_VALUE = "android.telephony.action.CARRIER_SIGNAL_PCO_VALUE";
    public static final String ACTION_CARRIER_SIGNAL_REDIRECTED = "android.telephony.action.CARRIER_SIGNAL_REDIRECTED";
    public static final String ACTION_CARRIER_SIGNAL_REQUEST_NETWORK_FAILED = "android.telephony.action.CARRIER_SIGNAL_REQUEST_NETWORK_FAILED";
    public static final String ACTION_CARRIER_SIGNAL_RESET = "android.telephony.action.CARRIER_SIGNAL_RESET";
    public static final String ACTION_CONFIGURE_VOICEMAIL = "android.telephony.action.CONFIGURE_VOICEMAIL";
    public static final String ACTION_DATA_STALL_DETECTED = "android.intent.action.DATA_STALL_DETECTED";
    @SystemApi
    public static final String ACTION_DEFAULT_DATA_SUBSCRIPTION_CHANGED = "android.intent.action.ACTION_DEFAULT_DATA_SUBSCRIPTION_CHANGED";
    @SystemApi
    public static final String ACTION_DEFAULT_VOICE_SUBSCRIPTION_CHANGED = "android.intent.action.ACTION_DEFAULT_VOICE_SUBSCRIPTION_CHANGED";
    @SystemApi
    public static final String ACTION_EMERGENCY_ASSISTANCE = "android.telephony.action.EMERGENCY_ASSISTANCE";
    @SystemApi
    public static final String ACTION_EMERGENCY_CALLBACK_MODE_CHANGED = "android.intent.action.EMERGENCY_CALLBACK_MODE_CHANGED";
    @SystemApi
    public static final String ACTION_EMERGENCY_CALL_STATE_CHANGED = "android.intent.action.EMERGENCY_CALL_STATE_CHANGED";
    public static final String ACTION_MULTI_SIM_CONFIG_CHANGED = "android.telephony.action.MULTI_SIM_CONFIG_CHANGED";
    public static final String ACTION_NETWORK_COUNTRY_CHANGED = "android.telephony.action.NETWORK_COUNTRY_CHANGED";
    public static final String ACTION_PHONE_STATE_CHANGED = "android.intent.action.PHONE_STATE";
    public static final String ACTION_PRIMARY_SUBSCRIPTION_LIST_CHANGED = "android.telephony.action.PRIMARY_SUBSCRIPTION_LIST_CHANGED";
    @SystemApi
    public static final String ACTION_REQUEST_OMADM_CONFIGURATION_UPDATE = "com.android.omadm.service.CONFIGURATION_UPDATE";
    public static final String ACTION_RESPOND_VIA_MESSAGE = "android.intent.action.RESPOND_VIA_MESSAGE";
    public static final String ACTION_SECRET_CODE = "android.telephony.action.SECRET_CODE";
    public static final String ACTION_SERVICE_PROVIDERS_UPDATED = "android.telephony.action.SERVICE_PROVIDERS_UPDATED";
    @SystemApi
    public static final String ACTION_SHOW_NOTICE_ECM_BLOCK_OTHERS = "android.telephony.action.SHOW_NOTICE_ECM_BLOCK_OTHERS";
    public static final String ACTION_SHOW_VOICEMAIL_NOTIFICATION = "android.telephony.action.SHOW_VOICEMAIL_NOTIFICATION";
    @SystemApi
    public static final String ACTION_SIM_APPLICATION_STATE_CHANGED = "android.telephony.action.SIM_APPLICATION_STATE_CHANGED";
    @SystemApi
    public static final String ACTION_SIM_CARD_STATE_CHANGED = "android.telephony.action.SIM_CARD_STATE_CHANGED";
    @SystemApi
    public static final String ACTION_SIM_SLOT_STATUS_CHANGED = "android.telephony.action.SIM_SLOT_STATUS_CHANGED";
    public static final String ACTION_SUBSCRIPTION_CARRIER_IDENTITY_CHANGED = "android.telephony.action.SUBSCRIPTION_CARRIER_IDENTITY_CHANGED";
    public static final String ACTION_SUBSCRIPTION_SPECIFIC_CARRIER_IDENTITY_CHANGED = "android.telephony.action.SUBSCRIPTION_SPECIFIC_CARRIER_IDENTITY_CHANGED";
    @SystemApi
    public static final int ALLOWED_NETWORK_TYPES_REASON_CARRIER = 2;
    @SystemApi
    public static final int ALLOWED_NETWORK_TYPES_REASON_ENABLE_2G = 3;
    @SystemApi
    public static final int ALLOWED_NETWORK_TYPES_REASON_POWER = 1;
    @SystemApi
    public static final int ALLOWED_NETWORK_TYPES_REASON_USER = 0;
    public static final int APPTYPE_CSIM = 4;
    public static final int APPTYPE_ISIM = 5;
    public static final int APPTYPE_RUIM = 3;
    public static final int APPTYPE_SIM = 1;
    public static final int APPTYPE_UNKNOWN = 0;
    public static final int APPTYPE_USIM = 2;
    public static final int AUTHTYPE_EAP_AKA = 129;
    public static final int AUTHTYPE_EAP_SIM = 128;
    private static final long CALLBACK_ON_MORE_ERROR_CODE_CHANGE = 130595455;
    public static final int CALL_COMPOSER_STATUS_OFF = 0;
    public static final int CALL_COMPOSER_STATUS_ON = 1;
    public static final int CALL_STATE_IDLE = 0;
    public static final int CALL_STATE_OFFHOOK = 2;
    public static final int CALL_STATE_RINGING = 1;
    @SystemApi
    public static final int CALL_WAITING_STATUS_DISABLED = 2;
    @SystemApi
    public static final int CALL_WAITING_STATUS_ENABLED = 1;
    @SystemApi
    public static final int CALL_WAITING_STATUS_NOT_SUPPORTED = 4;
    @SystemApi
    public static final int CALL_WAITING_STATUS_UNKNOWN_ERROR = 3;
    @SystemApi
    public static final String CAPABILITY_NR_DUAL_CONNECTIVITY_CONFIGURATION_AVAILABLE = "CAPABILITY_NR_DUAL_CONNECTIVITY_CONFIGURATION_AVAILABLE";
    public static final String CAPABILITY_PHYSICAL_CHANNEL_CONFIG_1_6_SUPPORTED = "CAPABILITY_PHYSICAL_CHANNEL_CONFIG_1_6_SUPPORTED";
    @SystemApi
    public static final String CAPABILITY_SECONDARY_LINK_BANDWIDTH_VISIBLE = "CAPABILITY_SECONDARY_LINK_BANDWIDTH_VISIBLE";
    public static final String CAPABILITY_SIM_PHONEBOOK_IN_MODEM = "CAPABILITY_SIM_PHONEBOOK_IN_MODEM";
    public static final String CAPABILITY_SLICING_CONFIG_SUPPORTED = "CAPABILITY_SLICING_CONFIG_SUPPORTED";
    @SystemApi
    public static final String CAPABILITY_THERMAL_MITIGATION_DATA_THROTTLING = "CAPABILITY_THERMAL_MITIGATION_DATA_THROTTLING";
    @SystemApi
    public static final String CAPABILITY_USES_ALLOWED_NETWORK_TYPES_BITMASK = "CAPABILITY_USES_ALLOWED_NETWORK_TYPES_BITMASK";
    public static final int CARD_POWER_DOWN = 0;
    public static final int CARD_POWER_UP = 1;
    public static final int CARD_POWER_UP_PASS_THROUGH = 2;
    @SystemApi
    public static final int CARRIER_PRIVILEGE_STATUS_ERROR_LOADING_RULES = -2;
    @SystemApi
    public static final int CARRIER_PRIVILEGE_STATUS_HAS_ACCESS = 1;
    @SystemApi
    public static final int CARRIER_PRIVILEGE_STATUS_NO_ACCESS = 0;
    @SystemApi
    public static final int CARRIER_PRIVILEGE_STATUS_RULES_NOT_LOADED = -1;
    public static final int CDMA_ROAMING_MODE_AFFILIATED = 1;
    public static final int CDMA_ROAMING_MODE_ANY = 2;
    public static final int CDMA_ROAMING_MODE_HOME = 0;
    public static final int CDMA_ROAMING_MODE_RADIO_DEFAULT = -1;
    @SystemApi
    public static final int CDMA_SUBSCRIPTION_NV = 1;
    @SystemApi
    public static final int CDMA_SUBSCRIPTION_RUIM_SIM = 0;
    @SystemApi
    public static final int CDMA_SUBSCRIPTION_UNKNOWN = -1;
    public static final int CHANGE_ICC_LOCK_SUCCESS = Integer.MAX_VALUE;
    public static final int DATA_ACTIVITY_DORMANT = 4;
    public static final int DATA_ACTIVITY_IN = 1;
    public static final int DATA_ACTIVITY_INOUT = 3;
    public static final int DATA_ACTIVITY_NONE = 0;
    public static final int DATA_ACTIVITY_OUT = 2;
    public static final int DATA_CONNECTED = 2;
    public static final int DATA_CONNECTING = 1;
    public static final int DATA_DISCONNECTED = 0;
    public static final int DATA_DISCONNECTING = 4;
    public static final int DATA_ENABLED_REASON_CARRIER = 2;
    public static final int DATA_ENABLED_REASON_POLICY = 1;
    public static final int DATA_ENABLED_REASON_THERMAL = 3;
    public static final int DATA_ENABLED_REASON_USER = 0;
    public static final int DATA_SUSPENDED = 3;
    public static final int DATA_UNKNOWN = -1;
    public static final boolean EMERGENCY_ASSISTANCE_ENABLED = true;
    @SystemApi
    public static final int ENABLE_NR_DUAL_CONNECTIVITY_INVALID_STATE = 4;
    @SystemApi
    public static final int ENABLE_NR_DUAL_CONNECTIVITY_NOT_SUPPORTED = 1;
    @SystemApi
    public static final int ENABLE_NR_DUAL_CONNECTIVITY_RADIO_ERROR = 3;
    @SystemApi
    public static final int ENABLE_NR_DUAL_CONNECTIVITY_RADIO_NOT_AVAILABLE = 2;
    @SystemApi
    public static final int ENABLE_NR_DUAL_CONNECTIVITY_SUCCESS = 0;
    public static final int ERI_FLASH = 2;
    public static final int ERI_ICON_MODE_FLASH = 1;
    public static final int ERI_ICON_MODE_NORMAL = 0;
    public static final int ERI_OFF = 1;
    public static final int ERI_ON = 0;
    public static final String EVENT_CALL_FORWARDED = "android.telephony.event.EVENT_CALL_FORWARDED";
    public static final String EVENT_DOWNGRADE_DATA_DISABLED = "android.telephony.event.EVENT_DOWNGRADE_DATA_DISABLED";
    public static final String EVENT_DOWNGRADE_DATA_LIMIT_REACHED = "android.telephony.event.EVENT_DOWNGRADE_DATA_LIMIT_REACHED";
    public static final String EVENT_HANDOVER_TO_WIFI_FAILED = "android.telephony.event.EVENT_HANDOVER_TO_WIFI_FAILED";
    public static final String EVENT_HANDOVER_VIDEO_FROM_LTE_TO_WIFI = "android.telephony.event.EVENT_HANDOVER_VIDEO_FROM_LTE_TO_WIFI";
    public static final String EVENT_HANDOVER_VIDEO_FROM_WIFI_TO_LTE = "android.telephony.event.EVENT_HANDOVER_VIDEO_FROM_WIFI_TO_LTE";
    public static final String EVENT_NOTIFY_INTERNATIONAL_CALL_ON_WFC = "android.telephony.event.EVENT_NOTIFY_INTERNATIONAL_CALL_ON_WFC";
    public static final String EVENT_SUPPLEMENTARY_SERVICE_NOTIFICATION = "android.telephony.event.EVENT_SUPPLEMENTARY_SERVICE_NOTIFICATION";
    public static final String EXCEPTION_RESULT_KEY = "exception";
    public static final String EXTRA_ACTIVE_SIM_SUPPORTED_COUNT = "android.telephony.extra.ACTIVE_SIM_SUPPORTED_COUNT";
    @SystemApi
    public static final String EXTRA_ANOMALY_DESCRIPTION = "android.telephony.extra.ANOMALY_DESCRIPTION";
    @SystemApi
    public static final String EXTRA_ANOMALY_ID = "android.telephony.extra.ANOMALY_ID";
    public static final String EXTRA_APN_PROTOCOL = "android.telephony.extra.APN_PROTOCOL";
    public static final String EXTRA_APN_TYPE = "android.telephony.extra.APN_TYPE";
    public static final String EXTRA_CALL_VOICEMAIL_INTENT = "android.telephony.extra.CALL_VOICEMAIL_INTENT";
    public static final String EXTRA_CARRIER_ID = "android.telephony.extra.CARRIER_ID";
    public static final String EXTRA_CARRIER_NAME = "android.telephony.extra.CARRIER_NAME";
    public static final String EXTRA_DATA_FAIL_CAUSE = "android.telephony.extra.DATA_FAIL_CAUSE";
    public static final String EXTRA_DATA_SPN = "android.telephony.extra.DATA_SPN";
    public static final String EXTRA_DEFAULT_NETWORK_AVAILABLE = "android.telephony.extra.DEFAULT_NETWORK_AVAILABLE";
    public static final String EXTRA_DEFAULT_SUBSCRIPTION_SELECT_TYPE = "android.telephony.extra.DEFAULT_SUBSCRIPTION_SELECT_TYPE";
    public static final int EXTRA_DEFAULT_SUBSCRIPTION_SELECT_TYPE_ALL = 4;
    public static final int EXTRA_DEFAULT_SUBSCRIPTION_SELECT_TYPE_DATA = 1;
    public static final int EXTRA_DEFAULT_SUBSCRIPTION_SELECT_TYPE_DISMISS = 5;
    public static final int EXTRA_DEFAULT_SUBSCRIPTION_SELECT_TYPE_NONE = 0;
    public static final int EXTRA_DEFAULT_SUBSCRIPTION_SELECT_TYPE_SMS = 3;
    public static final int EXTRA_DEFAULT_SUBSCRIPTION_SELECT_TYPE_VOICE = 2;
    @Deprecated
    public static final String EXTRA_DISCONNECT_CAUSE = "disconnect_cause";
    public static final String EXTRA_HIDE_PUBLIC_SETTINGS = "android.telephony.extra.HIDE_PUBLIC_SETTINGS";
    @Deprecated
    public static final String EXTRA_INCOMING_NUMBER = "incoming_number";
    public static final String EXTRA_IS_REFRESH = "android.telephony.extra.IS_REFRESH";
    public static final String EXTRA_LAST_KNOWN_NETWORK_COUNTRY = "android.telephony.extra.LAST_KNOWN_NETWORK_COUNTRY";
    public static final String EXTRA_LAUNCH_VOICEMAIL_SETTINGS_INTENT = "android.telephony.extra.LAUNCH_VOICEMAIL_SETTINGS_INTENT";
    public static final String EXTRA_NETWORK_COUNTRY = "android.telephony.extra.NETWORK_COUNTRY";
    public static final String EXTRA_NOTIFICATION_CODE = "android.telephony.extra.NOTIFICATION_CODE";
    public static final String EXTRA_NOTIFICATION_COUNT = "android.telephony.extra.NOTIFICATION_COUNT";
    public static final String EXTRA_NOTIFICATION_MESSAGE = "android.telephony.extra.NOTIFICATION_MESSAGE";
    public static final String EXTRA_NOTIFICATION_TYPE = "android.telephony.extra.NOTIFICATION_TYPE";
    public static final String EXTRA_PCO_ID = "android.telephony.extra.PCO_ID";
    public static final String EXTRA_PCO_VALUE = "android.telephony.extra.PCO_VALUE";
    public static final String EXTRA_PHONE_ACCOUNT_HANDLE = "android.telephony.extra.PHONE_ACCOUNT_HANDLE";
    @SystemApi
    public static final String EXTRA_PHONE_IN_ECM_STATE = "android.telephony.extra.PHONE_IN_ECM_STATE";
    @SystemApi
    public static final String EXTRA_PHONE_IN_EMERGENCY_CALL = "android.telephony.extra.PHONE_IN_EMERGENCY_CALL";
    public static final String EXTRA_PLMN = "android.telephony.extra.PLMN";
    public static final String EXTRA_PRECISE_DISCONNECT_CAUSE = "precise_disconnect_cause";
    public static final String EXTRA_RECOVERY_ACTION = "recoveryAction";
    public static final String EXTRA_REDIRECTION_URL = "android.telephony.extra.REDIRECTION_URL";
    public static final String EXTRA_SHOW_PLMN = "android.telephony.extra.SHOW_PLMN";
    public static final String EXTRA_SHOW_SPN = "android.telephony.extra.SHOW_SPN";
    public static final String EXTRA_SIM_COMBINATION_NAMES = "android.telephony.extra.SIM_COMBINATION_NAMES";
    public static final String EXTRA_SIM_COMBINATION_WARNING_TYPE = "android.telephony.extra.SIM_COMBINATION_WARNING_TYPE";
    public static final int EXTRA_SIM_COMBINATION_WARNING_TYPE_DUAL_CDMA = 1;
    public static final int EXTRA_SIM_COMBINATION_WARNING_TYPE_NONE = 0;
    @SystemApi
    public static final String EXTRA_SIM_STATE = "android.telephony.extra.SIM_STATE";
    public static final String EXTRA_SPECIFIC_CARRIER_ID = "android.telephony.extra.SPECIFIC_CARRIER_ID";
    public static final String EXTRA_SPECIFIC_CARRIER_NAME = "android.telephony.extra.SPECIFIC_CARRIER_NAME";
    public static final String EXTRA_SPN = "android.telephony.extra.SPN";
    public static final String EXTRA_STATE = "state";
    public static final String EXTRA_SUBSCRIPTION_ID = "android.telephony.extra.SUBSCRIPTION_ID";
    @SystemApi
    public static final String EXTRA_VISUAL_VOICEMAIL_ENABLED_BY_USER_BOOL = "android.telephony.extra.VISUAL_VOICEMAIL_ENABLED_BY_USER_BOOL";
    public static final String EXTRA_VOICEMAIL_NUMBER = "android.telephony.extra.VOICEMAIL_NUMBER";
    @SystemApi
    public static final String EXTRA_VOICEMAIL_SCRAMBLED_PIN_STRING = "android.telephony.extra.VOICEMAIL_SCRAMBLED_PIN_STRING";
    @SystemApi
    public static final int GBA_FAILURE_REASON_FEATURE_NOT_READY = 2;
    @SystemApi
    public static final int GBA_FAILURE_REASON_FEATURE_NOT_SUPPORTED = 1;
    @SystemApi
    public static final int GBA_FAILURE_REASON_INCORRECT_NAF_ID = 4;
    @SystemApi
    public static final int GBA_FAILURE_REASON_NETWORK_FAILURE = 3;
    @SystemApi
    public static final int GBA_FAILURE_REASON_SECURITY_PROTOCOL_NOT_SUPPORTED = 5;
    @SystemApi
    public static final int GBA_FAILURE_REASON_UNKNOWN = 0;
    private static final long GET_DATA_STATE_R_VERSION = 148534348;
    public static final int INDICATION_FILTER_DATA_CALL_DORMANCY_CHANGED = 4;
    public static final int INDICATION_FILTER_FULL_NETWORK_STATE = 2;
    public static final int INDICATION_FILTER_LINK_CAPACITY_ESTIMATE = 8;
    public static final int INDICATION_FILTER_PHYSICAL_CHANNEL_CONFIG = 16;
    public static final int INDICATION_FILTER_SIGNAL_STRENGTH = 1;
    @SystemApi
    public static final int INVALID_EMERGENCY_NUMBER_DB_VERSION = -1;
    public static final String KEY_CALL_COMPOSER_PICTURE_HANDLE = "call_composer_picture_handle";
    public static final String KEY_SLICING_CONFIG_HANDLE = "slicing_config_handle";
    @SystemApi
    public static final int KEY_TYPE_EPDG = 1;
    @SystemApi
    public static final int KEY_TYPE_WLAN = 2;
    private static final int MAXIMUM_CALL_COMPOSER_PICTURE_SIZE = 80000;
    private static final long MAX_NUMBER_VERIFICATION_TIMEOUT_MILLIS = 60000;
    public static final String METADATA_HIDE_VOICEMAIL_SETTINGS_MENU = "android.telephony.HIDE_VOICEMAIL_SETTINGS_MENU";
    @SystemApi
    public static final int MOBILE_DATA_POLICY_DATA_ON_NON_DEFAULT_DURING_VOICE_CALL = 1;
    @SystemApi
    public static final int MOBILE_DATA_POLICY_MMS_ALWAYS_ALLOWED = 2;
    public static final String MODEM_ACTIVITY_RESULT_KEY = "controller_activity";
    public static final int MULTISIM_ALLOWED = 0;
    public static final int MULTISIM_NOT_SUPPORTED_BY_CARRIER = 2;
    public static final int MULTISIM_NOT_SUPPORTED_BY_HARDWARE = 1;
    public static final long NETWORK_CLASS_BITMASK_2G = 32843;
    public static final long NETWORK_CLASS_BITMASK_3G = 93108;
    public static final long NETWORK_CLASS_BITMASK_4G = 397312;
    public static final long NETWORK_CLASS_BITMASK_5G = 524288;
    public static final int NETWORK_MODE_CDMA_EVDO = 4;
    public static final int NETWORK_MODE_CDMA_NO_EVDO = 5;
    public static final int NETWORK_MODE_EVDO_NO_CDMA = 6;
    public static final int NETWORK_MODE_GLOBAL = 7;
    public static final int NETWORK_MODE_GSM_ONLY = 1;
    public static final int NETWORK_MODE_GSM_UMTS = 3;
    public static final int NETWORK_MODE_LTE_CDMA_EVDO = 8;
    public static final int NETWORK_MODE_LTE_CDMA_EVDO_GSM_WCDMA = 10;
    public static final int NETWORK_MODE_LTE_GSM_WCDMA = 9;
    public static final int NETWORK_MODE_LTE_ONLY = 11;
    public static final int NETWORK_MODE_LTE_TDSCDMA = 15;
    public static final int NETWORK_MODE_LTE_TDSCDMA_CDMA_EVDO_GSM_WCDMA = 22;
    public static final int NETWORK_MODE_LTE_TDSCDMA_GSM = 17;
    public static final int NETWORK_MODE_LTE_TDSCDMA_GSM_WCDMA = 20;
    public static final int NETWORK_MODE_LTE_TDSCDMA_WCDMA = 19;
    public static final int NETWORK_MODE_LTE_WCDMA = 12;
    public static final int NETWORK_MODE_NR_LTE = 24;
    public static final int NETWORK_MODE_NR_LTE_CDMA_EVDO = 25;
    public static final int NETWORK_MODE_NR_LTE_CDMA_EVDO_GSM_WCDMA = 27;
    public static final int NETWORK_MODE_NR_LTE_GSM_WCDMA = 26;
    public static final int NETWORK_MODE_NR_LTE_TDSCDMA = 29;
    public static final int NETWORK_MODE_NR_LTE_TDSCDMA_CDMA_EVDO_GSM_WCDMA = 33;
    public static final int NETWORK_MODE_NR_LTE_TDSCDMA_GSM = 30;
    public static final int NETWORK_MODE_NR_LTE_TDSCDMA_GSM_WCDMA = 32;
    public static final int NETWORK_MODE_NR_LTE_TDSCDMA_WCDMA = 31;
    public static final int NETWORK_MODE_NR_LTE_WCDMA = 28;
    public static final int NETWORK_MODE_NR_ONLY = 23;
    public static final int NETWORK_MODE_TDSCDMA_CDMA_EVDO_GSM_WCDMA = 21;
    public static final int NETWORK_MODE_TDSCDMA_GSM = 16;
    public static final int NETWORK_MODE_TDSCDMA_GSM_WCDMA = 18;
    public static final int NETWORK_MODE_TDSCDMA_ONLY = 13;
    public static final int NETWORK_MODE_TDSCDMA_WCDMA = 14;
    public static final int NETWORK_MODE_WCDMA_ONLY = 2;
    public static final int NETWORK_MODE_WCDMA_PREF = 0;
    public static final int NETWORK_SELECTION_MODE_AUTO = 1;
    public static final int NETWORK_SELECTION_MODE_MANUAL = 2;
    public static final int NETWORK_SELECTION_MODE_UNKNOWN = 0;
    public static final long NETWORK_STANDARDS_FAMILY_BITMASK_3GPP = 906119;
    public static final long NETWORK_STANDARDS_FAMILY_BITMASK_3GPP2 = 10360;
    public static final int NETWORK_TYPE_1xRTT = 7;
    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_1xRTT = 64;
    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_CDMA = 8;
    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_EDGE = 2;
    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_EHRPD = 8192;
    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_EVDO_0 = 16;
    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_EVDO_A = 32;
    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_EVDO_B = 2048;
    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_GPRS = 1;
    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_GSM = 32768;
    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_HSDPA = 128;
    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_HSPA = 512;
    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_HSPAP = 16384;
    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_HSUPA = 256;
    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_IWLAN = 131072;
    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_LTE = 4096;
    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_LTE_CA = 262144;
    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_NR = 524288;
    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_TD_SCDMA = 65536;
    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_UMTS = 4;
    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_UNKNOWN = 0;
    public static final int NETWORK_TYPE_CDMA = 4;
    public static final int NETWORK_TYPE_EDGE = 2;
    public static final int NETWORK_TYPE_EHRPD = 14;
    public static final int NETWORK_TYPE_EVDO_0 = 5;
    public static final int NETWORK_TYPE_EVDO_A = 6;
    public static final int NETWORK_TYPE_EVDO_B = 12;
    public static final int NETWORK_TYPE_GPRS = 1;
    public static final int NETWORK_TYPE_GSM = 16;
    public static final int NETWORK_TYPE_HSDPA = 8;
    public static final int NETWORK_TYPE_HSPA = 10;
    public static final int NETWORK_TYPE_HSPAP = 15;
    public static final int NETWORK_TYPE_HSUPA = 9;
    public static final int NETWORK_TYPE_IDEN = 11;
    public static final int NETWORK_TYPE_IWLAN = 18;
    public static final int NETWORK_TYPE_LTE = 13;
    public static final int NETWORK_TYPE_LTE_CA = 19;
    public static final int NETWORK_TYPE_NR = 20;
    public static final int NETWORK_TYPE_TD_SCDMA = 17;
    public static final int NETWORK_TYPE_UMTS = 3;
    public static final int NETWORK_TYPE_UNKNOWN = 0;
    @SystemApi
    public static final int NR_DUAL_CONNECTIVITY_DISABLE = 2;
    @SystemApi
    public static final int NR_DUAL_CONNECTIVITY_DISABLE_IMMEDIATE = 3;
    @SystemApi
    public static final int NR_DUAL_CONNECTIVITY_ENABLE = 1;
    private static final long NULL_TELEPHONY_THROW_NO_CB = 182185642;
    public static final int OTASP_NEEDED = 2;
    public static final int OTASP_NOT_NEEDED = 3;
    public static final int OTASP_SIM_UNPROVISIONED = 5;
    public static final int OTASP_UNINITIALIZED = 0;
    public static final int OTASP_UNKNOWN = 1;
    public static final String PHONE_PROCESS_NAME = "com.android.phone";
    public static final int PHONE_TYPE_CDMA = 2;
    public static final int PHONE_TYPE_GSM = 1;
    public static final int PHONE_TYPE_IMS = 5;
    public static final int PHONE_TYPE_NONE = 0;
    public static final int PHONE_TYPE_SIP = 3;
    public static final int PHONE_TYPE_THIRD_PARTY = 4;
    @SystemApi
    public static final int PREPARE_UNATTENDED_REBOOT_ERROR = 2;
    @SystemApi
    public static final int PREPARE_UNATTENDED_REBOOT_PIN_REQUIRED = 1;
    @SystemApi
    public static final int PREPARE_UNATTENDED_REBOOT_SUCCESS = 0;
    @SystemApi
    public static final int RADIO_POWER_OFF = 0;
    @SystemApi
    public static final int RADIO_POWER_ON = 1;
    @SystemApi
    public static final int RADIO_POWER_UNAVAILABLE = 2;
    @SystemApi
    public static final int SET_CARRIER_RESTRICTION_ERROR = 2;
    @SystemApi
    public static final int SET_CARRIER_RESTRICTION_NOT_SUPPORTED = 1;
    @SystemApi
    public static final int SET_CARRIER_RESTRICTION_SUCCESS = 0;
    public static final int SET_OPPORTUNISTIC_SUB_INACTIVE_SUBSCRIPTION = 2;
    public static final int SET_OPPORTUNISTIC_SUB_NO_OPPORTUNISTIC_SUB_AVAILABLE = 3;
    public static final int SET_OPPORTUNISTIC_SUB_REMOTE_SERVICE_EXCEPTION = 4;
    public static final int SET_OPPORTUNISTIC_SUB_SUCCESS = 0;
    public static final int SET_OPPORTUNISTIC_SUB_VALIDATION_FAILED = 1;
    @SystemApi
    public static final int SET_SIM_POWER_STATE_ALREADY_IN_STATE = 1;
    @SystemApi
    public static final int SET_SIM_POWER_STATE_MODEM_ERROR = 2;
    @SystemApi
    public static final int SET_SIM_POWER_STATE_NOT_SUPPORTED = 4;
    @SystemApi
    public static final int SET_SIM_POWER_STATE_SIM_ERROR = 3;
    @SystemApi
    public static final int SET_SIM_POWER_STATE_SUCCESS = 0;
    @SystemApi
    public static final int SIM_ACTIVATION_STATE_ACTIVATED = 2;
    @SystemApi
    public static final int SIM_ACTIVATION_STATE_ACTIVATING = 1;
    @SystemApi
    public static final int SIM_ACTIVATION_STATE_DEACTIVATED = 3;
    @SystemApi
    public static final int SIM_ACTIVATION_STATE_RESTRICTED = 4;
    @SystemApi
    public static final int SIM_ACTIVATION_STATE_UNKNOWN = 0;
    public static final int SIM_STATE_ABSENT = 1;
    public static final int SIM_STATE_CARD_IO_ERROR = 8;
    public static final int SIM_STATE_CARD_RESTRICTED = 9;
    @SystemApi
    public static final int SIM_STATE_LOADED = 10;
    public static final int SIM_STATE_NETWORK_LOCKED = 4;
    public static final int SIM_STATE_NOT_READY = 6;
    public static final int SIM_STATE_PERM_DISABLED = 7;
    public static final int SIM_STATE_PIN_REQUIRED = 2;
    @SystemApi
    public static final int SIM_STATE_PRESENT = 11;
    public static final int SIM_STATE_PUK_REQUIRED = 3;
    public static final int SIM_STATE_READY = 5;
    public static final int SIM_STATE_UNKNOWN = 0;
    @SystemApi
    public static final int SRVCC_STATE_HANDOVER_CANCELED = 3;
    @SystemApi
    public static final int SRVCC_STATE_HANDOVER_COMPLETED = 1;
    @SystemApi
    public static final int SRVCC_STATE_HANDOVER_FAILED = 2;
    @SystemApi
    public static final int SRVCC_STATE_HANDOVER_NONE = -1;
    @SystemApi
    public static final int SRVCC_STATE_HANDOVER_STARTED = 0;
    private static final String TAG = "TelephonyManager";
    @SystemApi
    public static final int THERMAL_MITIGATION_RESULT_INVALID_STATE = 3;
    @SystemApi
    public static final int THERMAL_MITIGATION_RESULT_MODEM_ERROR = 1;
    @SystemApi
    public static final int THERMAL_MITIGATION_RESULT_MODEM_NOT_AVAILABLE = 2;
    @SystemApi
    public static final int THERMAL_MITIGATION_RESULT_SUCCESS = 0;
    @SystemApi
    public static final int THERMAL_MITIGATION_RESULT_UNKNOWN_ERROR = 4;
    public static final int UNINITIALIZED_CARD_ID = -2;
    public static final int UNKNOWN_CARRIER_ID = -1;
    public static final int UNKNOWN_CARRIER_ID_LIST_VERSION = -1;
    public static final int UNSUPPORTED_CARD_ID = -1;
    public static final int UPDATE_AVAILABLE_NETWORKS_ABORTED = 2;
    public static final int UPDATE_AVAILABLE_NETWORKS_DISABLE_MODEM_FAIL = 5;
    public static final int UPDATE_AVAILABLE_NETWORKS_ENABLE_MODEM_FAIL = 6;
    public static final int UPDATE_AVAILABLE_NETWORKS_INVALID_ARGUMENTS = 3;
    public static final int UPDATE_AVAILABLE_NETWORKS_MULTIPLE_NETWORKS_NOT_SUPPORTED = 7;
    public static final int UPDATE_AVAILABLE_NETWORKS_NO_CARRIER_PRIVILEGE = 4;
    public static final int UPDATE_AVAILABLE_NETWORKS_NO_OPPORTUNISTIC_SUB_AVAILABLE = 8;
    public static final int UPDATE_AVAILABLE_NETWORKS_REMOTE_SERVICE_EXCEPTION = 9;
    public static final int UPDATE_AVAILABLE_NETWORKS_SERVICE_IS_DISABLED = 10;
    public static final int UPDATE_AVAILABLE_NETWORKS_SUCCESS = 0;
    public static final int UPDATE_AVAILABLE_NETWORKS_UNKNOWN_FAILURE = 1;
    public static final int USSD_ERROR_SERVICE_UNAVAIL = -2;
    public static final String USSD_RESPONSE = "USSD_RESPONSE";
    public static final int USSD_RETURN_FAILURE = -1;
    public static final int USSD_RETURN_SUCCESS = 100;
    public static final String VVM_TYPE_CVVM = "vvm_type_cvvm";
    public static final String VVM_TYPE_OMTP = "vvm_type_omtp";
    private static IPhoneSubInfo sIPhoneSubInfo;
    private static ISms sISms;
    private static ISub sISub;
    private static ITelephony sITelephony;
    private final Context mContext;
    private final int mSubId;
    private SubscriptionManager mSubscriptionManager;
    private TelephonyRegistryManager mTelephonyRegistryMgr;
    private TelephonyScanManager mTelephonyScanManager;
    private static final Object sCacheLock = new Object();
    private static boolean sServiceHandleCacheEnabled = true;
    private static final DeathRecipient sServiceDeath = new DeathRecipient();
    private static TelephonyManager sInstance = new TelephonyManager();
    public static final String EXTRA_STATE_IDLE = PhoneConstants.State.IDLE.toString();
    public static final String EXTRA_STATE_RINGING = PhoneConstants.State.RINGING.toString();
    public static final String EXTRA_STATE_OFFHOOK = PhoneConstants.State.OFFHOOK.toString();
    private static final int[] NETWORK_TYPES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
    public static final int DEFAULT_PREFERRED_NETWORK_MODE = RILConstants.PREFERRED_NETWORK_MODE;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface AllowedNetworkTypesReason {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface AuthenticationFailureReason {
    }

    /* loaded from: classes3.dex */
    public @interface CallComposerStatus {
    }

    @SystemApi
    /* loaded from: classes3.dex */
    public interface CallForwardingInfoCallback {
        public static final int RESULT_ERROR_FDN_CHECK_FAILURE = 2;
        public static final int RESULT_ERROR_NOT_SUPPORTED = 3;
        public static final int RESULT_ERROR_UNKNOWN = 1;
        public static final int RESULT_SUCCESS = 0;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes3.dex */
        public @interface CallForwardingError {
        }

        void onCallForwardingInfoAvailable(CallForwardingInfo callForwardingInfo);

        void onError(int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface CallWaitingStatus {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface CdmaRoamingMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface CdmaSubscription {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface DataEnabledReason {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface DataState {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface DefaultSubscriptionSelectType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface EnableNrDualConnectivityResult {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface EriIconIndex {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface EriIconMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface IsMultiSimSupportedResult {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface KeyType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface MobileDataPolicy {
    }

    /* loaded from: classes3.dex */
    public enum MultiSimVariants {
        DSDS,
        DSDA,
        TSTS,
        UNKNOWN
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface NetworkSelectionMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface NetworkTypeBitMask {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface NrDualConnectivityState {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface PrefNetworkMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface PrepareUnattendedRebootResult {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface RadioInterfaceCapability {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface SetCarrierRestrictionResult {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface SetOpportunisticSubscriptionResult {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface SetSimPowerStateResult {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface SimCombinationWarningType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface SimPowerState {
    }

    /* loaded from: classes3.dex */
    public @interface SimState {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface UpdateAvailableNetworksResult {
    }

    /* loaded from: classes3.dex */
    public interface WifiCallingChoices {
        public static final int ALWAYS_USE = 0;
        public static final int ASK_EVERY_TIME = 1;
        public static final int NEVER_USE = 2;
    }

    public TelephonyManager(Context context) {
        this(context, Integer.MAX_VALUE);
    }

    public TelephonyManager(Context context, int subId) {
        this.mSubId = subId;
        Context appContext = context.getApplicationContext();
        if (appContext == null) {
            this.mContext = context;
        } else if (Objects.equals(context.getAttributionTag(), appContext.getAttributionTag())) {
            this.mContext = appContext;
        } else {
            this.mContext = appContext.createAttributionContext(context.getAttributionTag());
        }
        this.mSubscriptionManager = SubscriptionManager.from(this.mContext);
    }

    private TelephonyManager() {
        this.mContext = null;
        this.mSubId = -1;
    }

    @Deprecated
    public static TelephonyManager getDefault() {
        return sInstance;
    }

    private String getOpPackageName() {
        Context context = this.mContext;
        if (context != null) {
            return context.getOpPackageName();
        }
        ITelephony telephony = getITelephony();
        if (telephony == null) {
            return null;
        }
        try {
            return telephony.getCurrentPackageName();
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    private String getAttributionTag() {
        Context context = this.mContext;
        if (context != null) {
            return context.getAttributionTag();
        }
        return null;
    }

    private static void runOnBackgroundThread(Runnable r) {
        try {
            BackgroundThread.getExecutor().execute(r);
        } catch (RejectedExecutionException e) {
            throw new IllegalStateException("Failed to post a callback from the caller's thread context.", e);
        }
    }

    public MultiSimVariants getMultiSimConfiguration() {
        String mSimConfig = TelephonyProperties.multi_sim_config().orElse("");
        if (mSimConfig.equals("dsds")) {
            return MultiSimVariants.DSDS;
        }
        if (mSimConfig.equals("dsda")) {
            return MultiSimVariants.DSDA;
        }
        if (mSimConfig.equals("tsts")) {
            return MultiSimVariants.TSTS;
        }
        return MultiSimVariants.UNKNOWN;
    }

    @Deprecated
    public int getPhoneCount() {
        return getActiveModemCount();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: android.telephony.TelephonyManager$18  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class AnonymousClass18 {
        static final /* synthetic */ int[] $SwitchMap$android$telephony$TelephonyManager$MultiSimVariants;

        static {
            int[] iArr = new int[MultiSimVariants.values().length];
            $SwitchMap$android$telephony$TelephonyManager$MultiSimVariants = iArr;
            try {
                iArr[MultiSimVariants.UNKNOWN.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$telephony$TelephonyManager$MultiSimVariants[MultiSimVariants.DSDS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$telephony$TelephonyManager$MultiSimVariants[MultiSimVariants.DSDA.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$telephony$TelephonyManager$MultiSimVariants[MultiSimVariants.TSTS.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public int getActiveModemCount() {
        switch (AnonymousClass18.$SwitchMap$android$telephony$TelephonyManager$MultiSimVariants[getMultiSimConfiguration().ordinal()]) {
            case 1:
                if (isVoiceCapable() || isSmsCapable() || isDataCapable()) {
                    return 1;
                }
                return 0;
            case 2:
            case 3:
                return 2;
            case 4:
                return 3;
            default:
                return 1;
        }
    }

    public int getSupportedModemCount() {
        return TelephonyProperties.max_active_modems().orElse(Integer.valueOf(getActiveModemCount())).intValue();
    }

    @SystemApi
    public int getMaxNumberOfSimultaneouslyActiveSims() {
        switch (AnonymousClass18.$SwitchMap$android$telephony$TelephonyManager$MultiSimVariants[getMultiSimConfiguration().ordinal()]) {
            case 1:
            case 2:
            case 4:
                return 1;
            case 3:
                return 2;
            default:
                return 1;
        }
    }

    public static TelephonyManager from(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }

    public TelephonyManager createForSubscriptionId(int subId) {
        return new TelephonyManager(this.mContext, subId);
    }

    public TelephonyManager createForPhoneAccountHandle(PhoneAccountHandle phoneAccountHandle) {
        int subId = getSubscriptionId(phoneAccountHandle);
        if (!SubscriptionManager.isValidSubscriptionId(subId)) {
            return null;
        }
        return new TelephonyManager(this.mContext, subId);
    }

    public boolean isMultiSimEnabled() {
        return getPhoneCount() > 1;
    }

    public static long getMaximumCallComposerPictureSize() {
        return 80000L;
    }

    public String getDeviceSoftwareVersion() {
        return getDeviceSoftwareVersion(getSlotIndex());
    }

    @SystemApi
    public String getDeviceSoftwareVersion(int slotIndex) {
        ITelephony telephony = getITelephony();
        if (telephony == null) {
            return null;
        }
        try {
            return telephony.getDeviceSoftwareVersionForSlot(slotIndex, getOpPackageName(), getAttributionTag());
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    @Deprecated
    public String getDeviceId() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null && ((IOplusPermissionCheckInjector) OplusFeatureCache.getOrCreate(IOplusPermissionCheckInjector.DEFAULT, new Object[0])).checkPermission(Manifest.permission.READ_PHONE_STATE, Binder.getCallingPid(), Binder.getCallingUid(), "getDeviceIdWithFeature")) {
                return telephony.getDeviceIdWithFeature(this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            }
            return null;
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    @Deprecated
    public String getDeviceId(int slotIndex) {
        try {
            IPhoneSubInfo info = getSubscriberInfoService();
            if (info == null) {
                return null;
            }
            return info.getDeviceIdForPhone(slotIndex, this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    public String getImei() {
        return getImei(getSlotIndex());
    }

    public String getImei(int slotIndex) {
        ITelephony telephony = getITelephony();
        if (telephony == null) {
            return null;
        }
        try {
            return telephony.getImeiForSlot(slotIndex, getOpPackageName(), getAttributionTag());
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    public String getTypeAllocationCode() {
        return getTypeAllocationCode(getSlotIndex());
    }

    public String getTypeAllocationCode(int slotIndex) {
        ITelephony telephony = getITelephony();
        if (telephony == null) {
            return null;
        }
        try {
            return telephony.getTypeAllocationCodeForSlot(slotIndex);
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    public String getMeid() {
        return getMeid(getSlotIndex());
    }

    public String getMeid(int slotIndex) {
        ITelephony telephony = getITelephony();
        if (telephony == null) {
            return null;
        }
        try {
            String meid = telephony.getMeidForSlot(slotIndex, getOpPackageName(), getAttributionTag());
            if (!TextUtils.isEmpty(meid)) {
                return meid;
            }
            Log.d(TAG, "getMeid: return null because MEID is not available");
            return null;
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    public String getManufacturerCode() {
        return getManufacturerCode(getSlotIndex());
    }

    public String getManufacturerCode(int slotIndex) {
        ITelephony telephony = getITelephony();
        if (telephony == null) {
            return null;
        }
        try {
            return telephony.getManufacturerCodeForSlot(slotIndex);
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    public String getNai() {
        return getNaiBySubscriberId(getSubId());
    }

    public String getNai(int slotIndex) {
        int[] subId = SubscriptionManager.getSubId(slotIndex);
        if (subId == null) {
            return null;
        }
        return getNaiBySubscriberId(subId[0]);
    }

    private String getNaiBySubscriberId(int subId) {
        try {
            IPhoneSubInfo info = getSubscriberInfoService();
            if (info == null) {
                return null;
            }
            String nai = info.getNaiForSubscriber(subId, this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            if (Log.isLoggable(TAG, 2)) {
                Rlog.v(TAG, "Nai = " + nai);
            }
            return nai;
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    @Deprecated
    public CellLocation getCellLocation() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony == null) {
                Rlog.d(TAG, "getCellLocation returning null because telephony is null");
                return null;
            } else if (!((IOplusPermissionCheckInjector) OplusFeatureCache.getOrCreate(IOplusPermissionCheckInjector.DEFAULT, new Object[0])).checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, Binder.getCallingPid(), Binder.getCallingUid(), "getCellLocation")) {
                return null;
            } else {
                CellIdentity cellIdentity = telephony.getCellLocation(this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
                CellLocation cl = cellIdentity.asCellLocation();
                if (cl != null && !cl.isEmpty()) {
                    return cl;
                }
                Rlog.d(TAG, "getCellLocation returning null because CellLocation is empty or phone type doesn't match CellLocation type");
                return null;
            }
        } catch (RemoteException ex) {
            Rlog.d(TAG, "getCellLocation returning null due to RemoteException " + ex);
            return null;
        }
    }

    @Deprecated
    public List<NeighboringCellInfo> getNeighboringCellInfo() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null && ((IOplusPermissionCheckInjector) OplusFeatureCache.getOrCreate(IOplusPermissionCheckInjector.DEFAULT, new Object[0])).checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, Binder.getCallingPid(), Binder.getCallingUid(), "getNeighboringCellInfo")) {
                return telephony.getNeighboringCellInfo(this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            }
            return null;
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    @SystemApi
    public int getCurrentPhoneType() {
        return getCurrentPhoneType(getSubId());
    }

    @SystemApi
    public int getCurrentPhoneType(int subId) {
        int phoneId;
        if (subId == -1) {
            phoneId = 0;
        } else {
            phoneId = SubscriptionManager.getPhoneId(subId);
        }
        return getCurrentPhoneTypeForSlot(phoneId);
    }

    public int getCurrentPhoneTypeForSlot(int slotIndex) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getActivePhoneTypeForSlot(slotIndex);
            }
            return getPhoneTypeFromProperty(slotIndex);
        } catch (RemoteException e) {
            return getPhoneTypeFromProperty(slotIndex);
        } catch (NullPointerException e2) {
            return getPhoneTypeFromProperty(slotIndex);
        }
    }

    public int getPhoneType() {
        if (!isVoiceCapable()) {
            return 0;
        }
        return getCurrentPhoneType();
    }

    private int getPhoneTypeFromProperty() {
        return getPhoneTypeFromProperty(getPhoneId());
    }

    private int getPhoneTypeFromProperty(int phoneId) {
        Integer type = (Integer) getTelephonyProperty(phoneId, TelephonyProperties.current_active_phone(), (Object) null);
        return type != null ? type.intValue() : getPhoneTypeFromNetworkType(phoneId);
    }

    private int getPhoneTypeFromNetworkType() {
        return getPhoneTypeFromNetworkType(getPhoneId());
    }

    private int getPhoneTypeFromNetworkType(int phoneId) {
        Integer mode = (Integer) getTelephonyProperty(phoneId, TelephonyProperties.default_network(), (Object) null);
        if (mode != null) {
            return getPhoneType(mode.intValue());
        }
        return 0;
    }

    public static int getPhoneType(int networkMode) {
        switch (networkMode) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 9:
            case 10:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 22:
                return 1;
            case 4:
            case 5:
            case 6:
                return 2;
            case 7:
            case 8:
            case 21:
                return 2;
            case 11:
                return TelephonyProperties.lte_on_cdma_device().orElse(0).intValue() == 1 ? 2 : 1;
            default:
                return 1;
        }
    }

    @SystemApi
    public static long getMaxNumberVerificationTimeoutMillis() {
        return 60000L;
    }

    public String getNetworkOperatorName() {
        return getNetworkOperatorName(getSubId());
    }

    public String getNetworkOperatorName(int subId) {
        int phoneId = SubscriptionManager.getPhoneId(subId);
        return (String) getTelephonyProperty(phoneId, TelephonyProperties.operator_alpha(), "");
    }

    public String getNetworkOperator() {
        return getNetworkOperatorForPhone(getPhoneId());
    }

    public String getNetworkOperator(int subId) {
        int phoneId = SubscriptionManager.getPhoneId(subId);
        return getNetworkOperatorForPhone(phoneId);
    }

    public String getNetworkOperatorForPhone(int phoneId) {
        return (String) getTelephonyProperty(phoneId, TelephonyProperties.operator_numeric(), "");
    }

    public String getNetworkSpecifier() {
        return String.valueOf(getSubId());
    }

    public PersistableBundle getCarrierConfig() {
        CarrierConfigManager carrierConfigManager = (CarrierConfigManager) this.mContext.getSystemService(CarrierConfigManager.class);
        return carrierConfigManager.getConfigForSubId(getSubId());
    }

    public boolean isNetworkRoaming() {
        return isNetworkRoaming(getSubId());
    }

    public boolean isNetworkRoaming(int subId) {
        int phoneId = SubscriptionManager.getPhoneId(subId);
        return ((Boolean) getTelephonyProperty(phoneId, (List<boolean>) TelephonyProperties.operator_is_roaming(), false)).booleanValue();
    }

    public String getNetworkCountryIso() {
        return getNetworkCountryIso(getSlotIndex());
    }

    public String getNetworkCountryIso(int slotIndex) {
        if (slotIndex != Integer.MAX_VALUE) {
            try {
                if (!SubscriptionManager.isValidSlotIndex(slotIndex)) {
                    throw new IllegalArgumentException("invalid slot index " + slotIndex);
                }
            } catch (RemoteException e) {
                return "";
            }
        }
        ITelephony telephony = getITelephony();
        return telephony == null ? "" : telephony.getNetworkCountryIsoForPhone(slotIndex);
    }

    @Deprecated
    public String getNetworkCountryIsoForPhone(int phoneId) {
        return getNetworkCountryIso(phoneId);
    }

    @SystemApi(client = SystemApi.Client.MODULE_LIBRARIES)
    public static int[] getAllNetworkTypes() {
        return (int[]) NETWORK_TYPES.clone();
    }

    @Deprecated
    public int getNetworkType() {
        return getNetworkType(getSubId(SubscriptionManager.getActiveDataSubscriptionId()));
    }

    public int getNetworkType(int subId) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getNetworkTypeForSubscriber(subId, getOpPackageName(), getAttributionTag());
            }
            return 0;
        } catch (RemoteException e) {
            return 0;
        } catch (NullPointerException e2) {
            return 0;
        }
    }

    public int getDataNetworkType() {
        return getDataNetworkType(getSubId(SubscriptionManager.getActiveDataSubscriptionId()));
    }

    public int getDataNetworkType(int subId) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getDataNetworkTypeForSubscriber(subId, getOpPackageName(), getAttributionTag());
            }
            return 0;
        } catch (RemoteException e) {
            return 0;
        } catch (NullPointerException e2) {
            return 0;
        }
    }

    public int getVoiceNetworkType() {
        return getVoiceNetworkType(getSubId());
    }

    public int getVoiceNetworkType(int subId) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getVoiceNetworkTypeForSubscriber(subId, getOpPackageName(), getAttributionTag());
            }
            return 0;
        } catch (RemoteException e) {
            return 0;
        } catch (NullPointerException e2) {
            return 0;
        }
    }

    public String getNetworkTypeName() {
        return getNetworkTypeName(getNetworkType());
    }

    public static String getNetworkTypeName(int type) {
        switch (type) {
            case 1:
                return "GPRS";
            case 2:
                return "EDGE";
            case 3:
                return "UMTS";
            case 4:
                return "CDMA";
            case 5:
                return "CDMA - EvDo rev. 0";
            case 6:
                return "CDMA - EvDo rev. A";
            case 7:
                return "CDMA - 1xRTT";
            case 8:
                return "HSDPA";
            case 9:
                return "HSUPA";
            case 10:
                return "HSPA";
            case 11:
                return "iDEN";
            case 12:
                return "CDMA - EvDo rev. B";
            case 13:
                return DctConstants.RAT_NAME_LTE;
            case 14:
                return "CDMA - eHRPD";
            case 15:
                return "HSPA+";
            case 16:
                return "GSM";
            case 17:
                return "TD_SCDMA";
            case 18:
                return "IWLAN";
            case 19:
                return "LTE_CA";
            case 20:
                return "NR";
            default:
                return "UNKNOWN";
        }
    }

    public static long getBitMaskForNetworkType(int networkType) {
        switch (networkType) {
            case 1:
                return 1L;
            case 2:
                return 2L;
            case 3:
                return 4L;
            case 4:
                return 8L;
            case 5:
                return 16L;
            case 6:
                return 32L;
            case 7:
                return 64L;
            case 8:
                return 128L;
            case 9:
                return 256L;
            case 10:
                return 512L;
            case 11:
                return 1024L;
            case 12:
                return 2048L;
            case 13:
                return 4096L;
            case 14:
                return 8192L;
            case 15:
                return 16384L;
            case 16:
                return 32768L;
            case 17:
                return 65536L;
            case 18:
                return 131072L;
            case 19:
                return 262144L;
            case 20:
                return 524288L;
            default:
                return 0L;
        }
    }

    public boolean hasIccCard() {
        return hasIccCard(getSlotIndex());
    }

    public boolean hasIccCard(int slotIndex) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony == null) {
                return false;
            }
            return telephony.hasIccCardUsingSlotIndex(slotIndex);
        } catch (RemoteException e) {
            return false;
        } catch (NullPointerException e2) {
            return false;
        }
    }

    public int getSimState() {
        int simState = getSimStateIncludingLoaded();
        if (simState == 10) {
            return 5;
        }
        return simState;
    }

    private int getSimStateIncludingLoaded() {
        int slotIndex = getSlotIndex();
        if (slotIndex >= 0) {
            return SubscriptionManager.getSimStateForSlotIndex(slotIndex);
        }
        for (int i = 0; i < getPhoneCount(); i++) {
            int simState = getSimState(i);
            if (simState != 1) {
                Rlog.d(TAG, "getSimState: default sim:" + slotIndex + ", sim state for slotIndex=" + i + " is " + simState + ", return state as unknown");
                return 0;
            }
        }
        Rlog.d(TAG, "getSimState: default sim:" + slotIndex + ", all SIMs absent, return state as absent");
        return 1;
    }

    @SystemApi
    public int getSimCardState() {
        int simState = getSimState();
        return getSimCardStateFromSimState(simState);
    }

    @SystemApi
    public int getSimCardState(int physicalSlotIndex) {
        int simState = getSimState(getLogicalSlotIndex(physicalSlotIndex));
        return getSimCardStateFromSimState(simState);
    }

    private int getSimCardStateFromSimState(int simState) {
        switch (simState) {
            case 0:
            case 1:
            case 8:
            case 9:
                return simState;
            default:
                return 11;
        }
    }

    private int getLogicalSlotIndex(int physicalSlotIndex) {
        UiccSlotInfo[] slotInfos = getUiccSlotsInfo();
        if (slotInfos == null || physicalSlotIndex < 0 || physicalSlotIndex >= slotInfos.length || slotInfos[physicalSlotIndex] == null) {
            return -1;
        }
        return slotInfos[physicalSlotIndex].getLogicalSlotIdx();
    }

    @SystemApi
    public int getSimApplicationState() {
        int simState = getSimStateIncludingLoaded();
        return getSimApplicationStateFromSimState(simState);
    }

    @SystemApi
    public int getSimApplicationState(int physicalSlotIndex) {
        int simState = SubscriptionManager.getSimStateForSlotIndex(getLogicalSlotIndex(physicalSlotIndex));
        return getSimApplicationStateFromSimState(simState);
    }

    private int getSimApplicationStateFromSimState(int simState) {
        switch (simState) {
            case 0:
            case 1:
            case 8:
            case 9:
                return 0;
            case 5:
                return 6;
            default:
                return simState;
        }
    }

    @SystemApi
    public boolean isApplicationOnUicc(int appType) {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                return service.isApplicationOnUicc(getSubId(), appType);
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#isApplicationOnUicc", e);
            return false;
        }
    }

    public int getSimState(int slotIndex) {
        int simState = SubscriptionManager.getSimStateForSlotIndex(slotIndex);
        if (simState == 10) {
            return 5;
        }
        return simState;
    }

    public String getSimOperator() {
        return getSimOperatorNumeric();
    }

    public String getSimOperator(int subId) {
        return getSimOperatorNumeric(subId);
    }

    public String getSimOperatorNumeric() {
        int subId = this.mSubId;
        if (!SubscriptionManager.isUsableSubIdValue(subId)) {
            subId = SubscriptionManager.getDefaultDataSubscriptionId();
            if (!SubscriptionManager.isUsableSubIdValue(subId)) {
                subId = SubscriptionManager.getDefaultSmsSubscriptionId();
                if (!SubscriptionManager.isUsableSubIdValue(subId)) {
                    subId = SubscriptionManager.getDefaultVoiceSubscriptionId();
                    if (!SubscriptionManager.isUsableSubIdValue(subId)) {
                        subId = SubscriptionManager.getDefaultSubscriptionId();
                    }
                }
            }
        }
        return getSimOperatorNumeric(subId);
    }

    public String getSimOperatorNumeric(int subId) {
        int phoneId = SubscriptionManager.getPhoneId(subId);
        return getSimOperatorNumericForPhone(phoneId);
    }

    public String getSimOperatorNumericForPhone(int phoneId) {
        return (String) getTelephonyProperty(phoneId, TelephonyProperties.icc_operator_numeric(), "");
    }

    public String getSimOperatorName() {
        return getSimOperatorNameForPhone(getPhoneId());
    }

    public String getSimOperatorName(int subId) {
        int phoneId = SubscriptionManager.getPhoneId(subId);
        return getSimOperatorNameForPhone(phoneId);
    }

    public String getSimOperatorNameForPhone(int phoneId) {
        return (String) getTelephonyProperty(phoneId, TelephonyProperties.icc_operator_alpha(), "");
    }

    public String getSimCountryIso() {
        return getSimCountryIsoForPhone(getPhoneId());
    }

    public static String getSimCountryIso(int subId) {
        int phoneId = SubscriptionManager.getPhoneId(subId);
        return getSimCountryIsoForPhone(phoneId);
    }

    public static String getSimCountryIsoForPhone(int phoneId) {
        return (String) getTelephonyProperty(phoneId, TelephonyProperties.icc_operator_iso_country(), "");
    }

    public String getSimSerialNumber() {
        return getSimSerialNumber(getSubId());
    }

    public String getSimSerialNumber(int subId) {
        try {
            IPhoneSubInfo info = getSubscriberInfoService();
            if (info == null) {
                return null;
            }
            return info.getIccSerialNumberForSubscriber(subId, this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    @SystemApi
    public boolean isLteCdmaEvdoGsmWcdmaEnabled() {
        return getLteOnCdmaMode(getSubId()) == 1;
    }

    public int getLteOnCdmaMode(int subId) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony == null) {
                return -1;
            }
            return telephony.getLteOnCdmaModeForSubscriber(subId, getOpPackageName(), getAttributionTag());
        } catch (RemoteException e) {
            return -1;
        } catch (NullPointerException e2) {
            return -1;
        }
    }

    public int getCardIdForDefaultEuicc() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony == null) {
                return -2;
            }
            return telephony.getCardIdForDefaultEuicc(this.mSubId, this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            return -2;
        }
    }

    public List<UiccCardInfo> getUiccCardsInfo() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getUiccCardsInfo(this.mContext.getOpPackageName());
            }
            Log.e(TAG, "Error in getUiccCardsInfo: unable to connect to Telephony service.");
            return new ArrayList();
        } catch (RemoteException e) {
            Log.e(TAG, "Error in getUiccCardsInfo: " + e);
            return new ArrayList();
        }
    }

    @SystemApi
    public UiccSlotInfo[] getUiccSlotsInfo() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony == null) {
                return null;
            }
            return telephony.getUiccSlotsInfo();
        } catch (RemoteException e) {
            return null;
        }
    }

    public void refreshUiccProfile() {
        try {
            ITelephony telephony = getITelephony();
            telephony.refreshUiccProfile(this.mSubId);
        } catch (RemoteException ex) {
            Rlog.w(TAG, "RemoteException", ex);
        }
    }

    @SystemApi
    public boolean switchSlots(int[] physicalSlots) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony == null) {
                return false;
            }
            return telephony.switchSlots(physicalSlots);
        } catch (RemoteException e) {
            return false;
        }
    }

    @SystemApi
    public Map<Integer, Integer> getLogicalToPhysicalSlotMapping() {
        Map<Integer, Integer> slotMapping = new HashMap<>();
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                int[] slotMappingArray = telephony.getSlotsMapping();
                for (int i = 0; i < slotMappingArray.length; i++) {
                    slotMapping.put(Integer.valueOf(i), Integer.valueOf(slotMappingArray[i]));
                }
            }
        } catch (RemoteException e) {
            Log.e(TAG, "getSlotsMapping RemoteException", e);
        }
        return slotMapping;
    }

    public String getSubscriberId() {
        return getSubscriberId(getSubId());
    }

    public String getSubscriberId(int subId) {
        try {
            IPhoneSubInfo info = getSubscriberInfoService();
            if (info == null) {
                return null;
            }
            return info.getSubscriberIdForSubscriber(subId, this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    @SystemApi
    public ImsiEncryptionInfo getCarrierInfoForImsiEncryption(int keyType) {
        try {
            IPhoneSubInfo info = getSubscriberInfoService();
            if (info == null) {
                Rlog.e(TAG, "IMSI error: Subscriber Info is null");
                return null;
            }
            int subId = getSubId(SubscriptionManager.getDefaultDataSubscriptionId());
            if (!(keyType == 1 || keyType == 2)) {
                throw new IllegalArgumentException("IMSI error: Invalid key type");
            }
            ImsiEncryptionInfo imsiEncryptionInfo = info.getCarrierInfoForImsiEncryption(subId, keyType, this.mContext.getOpPackageName());
            if (imsiEncryptionInfo == null && isImsiEncryptionRequired(subId, keyType)) {
                Rlog.e(TAG, "IMSI error: key is required but not found");
                throw new IllegalArgumentException("IMSI error: key is required but not found");
            }
            return imsiEncryptionInfo;
        } catch (RemoteException ex) {
            Rlog.e(TAG, "getCarrierInfoForImsiEncryption RemoteException" + ex);
            return null;
        } catch (NullPointerException ex2) {
            Rlog.e(TAG, "getCarrierInfoForImsiEncryption NullPointerException" + ex2);
            return null;
        }
    }

    @SystemApi
    public void resetCarrierKeysForImsiEncryption() {
        try {
            IPhoneSubInfo info = getSubscriberInfoService();
            if (info != null) {
                int subId = getSubId(SubscriptionManager.getDefaultDataSubscriptionId());
                info.resetCarrierKeysForImsiEncryption(subId, this.mContext.getOpPackageName());
                return;
            }
            throw new RuntimeException("IMSI error: Subscriber Info is null");
        } catch (RemoteException ex) {
            Rlog.e(TAG, "Telephony#getCarrierInfoForImsiEncryption RemoteException" + ex);
        }
    }

    private static boolean isKeyEnabled(int keyAvailability, int keyType) {
        int returnValue = (keyAvailability >> (keyType - 1)) & 1;
        return returnValue == 1;
    }

    private boolean isImsiEncryptionRequired(int subId, int keyType) {
        PersistableBundle pb;
        CarrierConfigManager configManager = (CarrierConfigManager) this.mContext.getSystemService("carrier_config");
        if (configManager == null || (pb = configManager.getConfigForSubId(subId)) == null) {
            return false;
        }
        int keyAvailability = pb.getInt(CarrierConfigManager.IMSI_KEY_AVAILABILITY_INT);
        return isKeyEnabled(keyAvailability, keyType);
    }

    public void setCarrierInfoForImsiEncryption(ImsiEncryptionInfo imsiEncryptionInfo) {
        try {
            IPhoneSubInfo info = getSubscriberInfoService();
            if (info != null) {
                info.setCarrierInfoForImsiEncryption(this.mSubId, this.mContext.getOpPackageName(), imsiEncryptionInfo);
            }
        } catch (RemoteException ex) {
            Rlog.e(TAG, "setCarrierInfoForImsiEncryption RemoteException", ex);
        } catch (NullPointerException e) {
        }
    }

    /* loaded from: classes3.dex */
    public static class CallComposerException extends Exception {
        public static final int ERROR_AUTHENTICATION_FAILED = 3;
        public static final int ERROR_FILE_TOO_LARGE = 2;
        public static final int ERROR_INPUT_CLOSED = 4;
        public static final int ERROR_IO_EXCEPTION = 5;
        public static final int ERROR_NETWORK_UNAVAILABLE = 6;
        public static final int ERROR_REMOTE_END_CLOSED = 1;
        public static final int ERROR_UNKNOWN = 0;
        public static final int SUCCESS = -1;
        private final int mErrorCode;
        private final IOException mIOException;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes3.dex */
        public @interface CallComposerError {
        }

        public CallComposerException(int errorCode, IOException ioException) {
            this.mErrorCode = errorCode;
            this.mIOException = ioException;
        }

        public int getErrorCode() {
            return this.mErrorCode;
        }

        public IOException getIOException() {
            return this.mIOException;
        }
    }

    public void uploadCallComposerPicture(final Path pictureToUpload, final String contentType, final Executor executor, final OutcomeReceiver<ParcelUuid, CallComposerException> callback) {
        Objects.requireNonNull(pictureToUpload);
        Objects.requireNonNull(executor);
        Objects.requireNonNull(callback);
        RoleManager rm = (RoleManager) this.mContext.getSystemService(RoleManager.class);
        if (rm.isRoleHeld("android.app.role.DIALER")) {
            executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    TelephonyManager.this.lambda$uploadCallComposerPicture$0$TelephonyManager(pictureToUpload, callback, contentType, executor);
                }
            });
            return;
        }
        throw new SecurityException("You must hold RoleManager.ROLE_DIALER to do this");
    }

    public /* synthetic */ void lambda$uploadCallComposerPicture$0$TelephonyManager(Path pictureToUpload, final OutcomeReceiver callback, String contentType, Executor executor) {
        try {
            if (Looper.getMainLooper().isCurrentThread()) {
                Log.w(TAG, "Uploading call composer picture on main thread! hic sunt dracones!");
            }
            long size = Files.size(pictureToUpload);
            if (size > getMaximumCallComposerPictureSize()) {
                callback.onError(new CallComposerException(2, null));
                return;
            }
            final InputStream fileStream = Files.newInputStream(pictureToUpload, new OpenOption[0]);
            try {
                uploadCallComposerPicture(fileStream, contentType, executor, new OutcomeReceiver<ParcelUuid, CallComposerException>() { // from class: android.telephony.TelephonyManager.1
                    public void onResult(ParcelUuid result) {
                        try {
                            fileStream.close();
                        } catch (IOException e) {
                            Log.e(TelephonyManager.TAG, "Error closing file input stream when uploading call composer pic");
                        }
                        callback.onResult(result);
                    }

                    public void onError(CallComposerException error) {
                        try {
                            fileStream.close();
                        } catch (IOException e) {
                            Log.e(TelephonyManager.TAG, "Error closing file input stream when uploading call composer pic");
                        }
                        callback.onError(error);
                    }
                });
            } catch (Exception e) {
                Log.e(TAG, "Got exception calling into stream-version of uploadCallComposerPicture: " + e);
                try {
                    fileStream.close();
                } catch (IOException e2) {
                    Log.e(TAG, "Error closing file input stream when uploading call composer pic");
                }
            }
        } catch (IOException e3) {
            Log.e(TAG, "IOException when uploading call composer pic:" + e3);
            callback.onError(new CallComposerException(5, e3));
        }
    }

    public void uploadCallComposerPicture(final InputStream pictureToUpload, String contentType, Executor executor, final OutcomeReceiver<ParcelUuid, CallComposerException> callback) {
        Objects.requireNonNull(pictureToUpload);
        Objects.requireNonNull(executor);
        Objects.requireNonNull(callback);
        ITelephony telephony = getITelephony();
        if (telephony != null) {
            try {
                ParcelFileDescriptor[] pipe = ParcelFileDescriptor.createReliablePipe();
                final ParcelFileDescriptor writeFd = pipe[1];
                ParcelFileDescriptor readFd = pipe[0];
                final OutputStream output = new ParcelFileDescriptor.AutoCloseOutputStream(writeFd);
                try {
                    telephony.uploadCallComposerPicture(getSubId(), this.mContext.getOpPackageName(), contentType, readFd, new AnonymousClass2(null, executor, callback));
                } catch (RemoteException e) {
                    Log.e(TAG, "Remote exception uploading call composer pic:" + e);
                    e.rethrowAsRuntimeException();
                }
                executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$$ExternalSyntheticLambda8
                    @Override // java.lang.Runnable
                    public final void run() {
                        TelephonyManager.lambda$uploadCallComposerPicture$2(pictureToUpload, callback, writeFd, output);
                    }
                });
            } catch (IOException e2) {
                executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        OutcomeReceiver.this.onError(new TelephonyManager.CallComposerException(5, e2));
                    }
                });
            }
        } else {
            throw new IllegalStateException("Telephony service not available.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: android.telephony.TelephonyManager$2  reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass2 extends ResultReceiver {
        final /* synthetic */ OutcomeReceiver val$callback;
        final /* synthetic */ Executor val$executor;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Handler handler, Executor executor, OutcomeReceiver outcomeReceiver) {
            super(handler);
            this.val$executor = executor;
            this.val$callback = outcomeReceiver;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.ResultReceiver
        public void onReceiveResult(final int resultCode, Bundle result) {
            if (resultCode != -1) {
                Executor executor = this.val$executor;
                final OutcomeReceiver outcomeReceiver = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$2$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        OutcomeReceiver.this.onError(new TelephonyManager.CallComposerException(resultCode, null));
                    }
                });
                return;
            }
            final ParcelUuid resultUuid = (ParcelUuid) result.getParcelable(TelephonyManager.KEY_CALL_COMPOSER_PICTURE_HANDLE);
            if (resultUuid == null) {
                Log.e(TelephonyManager.TAG, "Got null uuid without an error while uploading call composer pic");
                Executor executor2 = this.val$executor;
                final OutcomeReceiver outcomeReceiver2 = this.val$callback;
                executor2.execute(new Runnable() { // from class: android.telephony.TelephonyManager$2$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        OutcomeReceiver.this.onError(new TelephonyManager.CallComposerException(0, null));
                    }
                });
                return;
            }
            Executor executor3 = this.val$executor;
            final OutcomeReceiver outcomeReceiver3 = this.val$callback;
            executor3.execute(new Runnable() { // from class: android.telephony.TelephonyManager$2$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    OutcomeReceiver.this.onResult(resultUuid);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x002b, code lost:
        android.util.Log.e(android.telephony.TelephonyManager.TAG, "Read too many bytes from call composer pic stream: " + r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x003f, code lost:
        r10.onError(new android.telephony.TelephonyManager.CallComposerException(2, null));
        r11.closeWithError("too large");
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0051, code lost:
        r5 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0052, code lost:
        android.util.Log.e(android.telephony.TelephonyManager.TAG, "Error closing fd pipe: " + r5);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ void lambda$uploadCallComposerPicture$2(java.io.InputStream r9, android.os.OutcomeReceiver r10, android.os.ParcelFileDescriptor r11, java.io.OutputStream r12) {
        /*
            java.lang.String r0 = "Error closing fd pipe: "
            android.os.Looper r1 = android.os.Looper.getMainLooper()
            boolean r1 = r1.isCurrentThread()
            java.lang.String r2 = "TelephonyManager"
            if (r1 == 0) goto L_0x0013
            java.lang.String r1 = "Uploading call composer picture on main thread! hic sunt dracones!"
            android.util.Log.w(r2, r1)
        L_0x0013:
            r1 = 0
            r3 = 16384(0x4000, float:2.2959E-41)
            byte[] r3 = new byte[r3]
        L_0x0018:
            int r4 = r9.read(r3)     // Catch: all -> 0x0091, IOException -> 0x0093
            if (r4 >= 0) goto L_0x0021
            goto L_0x00cb
        L_0x0021:
            int r1 = r1 + r4
            long r5 = (long) r1
            long r7 = getMaximumCallComposerPictureSize()     // Catch: all -> 0x0091
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 <= 0) goto L_0x0066
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: all -> 0x0091
            r5.<init>()     // Catch: all -> 0x0091
            java.lang.String r6 = "Read too many bytes from call composer pic stream: "
            r5.append(r6)     // Catch: all -> 0x0091
            r5.append(r1)     // Catch: all -> 0x0091
            java.lang.String r5 = r5.toString()     // Catch: all -> 0x0091
            android.util.Log.e(r2, r5)     // Catch: all -> 0x0091
            android.telephony.TelephonyManager$CallComposerException r5 = new android.telephony.TelephonyManager$CallComposerException     // Catch: IOException -> 0x0051, all -> 0x0091
            r6 = 2
            r7 = 0
            r5.<init>(r6, r7)     // Catch: IOException -> 0x0051, all -> 0x0091
            r10.onError(r5)     // Catch: IOException -> 0x0051, all -> 0x0091
            java.lang.String r5 = "too large"
            r11.closeWithError(r5)     // Catch: IOException -> 0x0051, all -> 0x0091
            goto L_0x00cb
        L_0x0051:
            r5 = move-exception
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: all -> 0x0091
            r6.<init>()     // Catch: all -> 0x0091
            r6.append(r0)     // Catch: all -> 0x0091
            r6.append(r5)     // Catch: all -> 0x0091
            java.lang.String r0 = r6.toString()     // Catch: all -> 0x0091
            android.util.Log.e(r2, r0)     // Catch: all -> 0x0091
            goto L_0x00cb
        L_0x0066:
            r5 = 0
            r12.write(r3, r5, r4)     // Catch: IOException -> 0x006c, all -> 0x0091
            goto L_0x0018
        L_0x006c:
            r5 = move-exception
            android.telephony.TelephonyManager$CallComposerException r6 = new android.telephony.TelephonyManager$CallComposerException     // Catch: all -> 0x0091
            r7 = 1
            r6.<init>(r7, r5)     // Catch: all -> 0x0091
            r10.onError(r6)     // Catch: all -> 0x0091
            java.lang.String r6 = "remote end closed"
            r11.closeWithError(r6)     // Catch: IOException -> 0x007d, all -> 0x0091
            goto L_0x0090
        L_0x007d:
            r6 = move-exception
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: all -> 0x0091
            r7.<init>()     // Catch: all -> 0x0091
            r7.append(r0)     // Catch: all -> 0x0091
            r7.append(r6)     // Catch: all -> 0x0091
            java.lang.String r0 = r7.toString()     // Catch: all -> 0x0091
            android.util.Log.e(r2, r0)     // Catch: all -> 0x0091
        L_0x0090:
            goto L_0x00cb
        L_0x0091:
            r0 = move-exception
            goto L_0x00d2
        L_0x0093:
            r4 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: all -> 0x0091
            r5.<init>()     // Catch: all -> 0x0091
            java.lang.String r6 = "IOException reading from input while uploading pic: "
            r5.append(r6)     // Catch: all -> 0x0091
            r5.append(r4)     // Catch: all -> 0x0091
            java.lang.String r5 = r5.toString()     // Catch: all -> 0x0091
            android.util.Log.e(r2, r5)     // Catch: all -> 0x0091
            android.telephony.TelephonyManager$CallComposerException r5 = new android.telephony.TelephonyManager$CallComposerException     // Catch: all -> 0x0091
            r6 = 4
            r5.<init>(r6, r4)     // Catch: all -> 0x0091
            r10.onError(r5)     // Catch: all -> 0x0091
            java.lang.String r5 = "input closed"
            r11.closeWithError(r5)     // Catch: all -> 0x0091, IOException -> 0x00b7
            goto L_0x00ca
        L_0x00b7:
            r5 = move-exception
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: all -> 0x0091
            r6.<init>()     // Catch: all -> 0x0091
            r6.append(r0)     // Catch: all -> 0x0091
            r6.append(r5)     // Catch: all -> 0x0091
            java.lang.String r0 = r6.toString()     // Catch: all -> 0x0091
            android.util.Log.e(r2, r0)     // Catch: all -> 0x0091
        L_0x00ca:
        L_0x00cb:
            r12.close()     // Catch: IOException -> 0x00cf
            goto L_0x00d1
        L_0x00cf:
            r0 = move-exception
        L_0x00d1:
            return
        L_0x00d2:
            r12.close()     // Catch: IOException -> 0x00d6
            goto L_0x00d7
        L_0x00d6:
            r2 = move-exception
        L_0x00d7:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.telephony.TelephonyManager.lambda$uploadCallComposerPicture$2(java.io.InputStream, android.os.OutcomeReceiver, android.os.ParcelFileDescriptor, java.io.OutputStream):void");
    }

    public String getGroupIdLevel1() {
        try {
            IPhoneSubInfo info = getSubscriberInfoService();
            if (info == null) {
                return null;
            }
            return info.getGroupIdLevel1ForSubscriber(getSubId(), this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    public String getGroupIdLevel1(int subId) {
        try {
            IPhoneSubInfo info = getSubscriberInfoService();
            if (info == null) {
                return null;
            }
            return info.getGroupIdLevel1ForSubscriber(subId, this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    public String getLine1Number() {
        return getLine1Number(getSubId());
    }

    public String getLine1Number(int subId) {
        String number = null;
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                number = telephony.getLine1NumberForDisplay(subId, this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            }
        } catch (RemoteException e) {
        } catch (NullPointerException e2) {
        }
        if (number != null) {
            return number;
        }
        try {
            IPhoneSubInfo info = getSubscriberInfoService();
            if (info == null) {
                return null;
            }
            return info.getLine1NumberForSubscriber(subId, this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
        } catch (RemoteException e3) {
            return null;
        } catch (NullPointerException e4) {
            return null;
        }
    }

    public boolean setLine1NumberForDisplay(String alphaTag, String number) {
        return setLine1NumberForDisplay(getSubId(), alphaTag, number);
    }

    public boolean setLine1NumberForDisplay(int subId, String alphaTag, String number) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.setLine1NumberForDisplayForSubscriber(subId, alphaTag, number);
            }
            return false;
        } catch (RemoteException e) {
            return false;
        } catch (NullPointerException e2) {
            return false;
        }
    }

    public String getLine1AlphaTag() {
        return getLine1AlphaTag(getSubId());
    }

    public String getLine1AlphaTag(int subId) {
        String alphaTag = null;
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                alphaTag = telephony.getLine1AlphaTagForDisplay(subId, getOpPackageName(), getAttributionTag());
            }
        } catch (RemoteException e) {
        } catch (NullPointerException e2) {
        }
        if (alphaTag != null) {
            return alphaTag;
        }
        try {
            IPhoneSubInfo info = getSubscriberInfoService();
            if (info == null) {
                return null;
            }
            return info.getLine1AlphaTagForSubscriber(subId, getOpPackageName(), getAttributionTag());
        } catch (RemoteException e3) {
            return null;
        } catch (NullPointerException e4) {
            return null;
        }
    }

    @Deprecated
    public String[] getMergedSubscriberIds() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getMergedSubscriberIds(getSubId(), getOpPackageName(), getAttributionTag());
            }
            return null;
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    @SystemApi
    public String[] getMergedImsisFromGroup() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getMergedImsisFromGroup(getSubId(), getOpPackageName());
            }
        } catch (RemoteException e) {
        }
        return new String[0];
    }

    public String getMsisdn() {
        return getMsisdn(getSubId());
    }

    public String getMsisdn(int subId) {
        try {
            IPhoneSubInfo info = getSubscriberInfoService();
            if (info == null) {
                return null;
            }
            return info.getMsisdnForSubscriber(subId, getOpPackageName(), getAttributionTag());
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    public String getVoiceMailNumber() {
        return getVoiceMailNumber(getSubId());
    }

    public String getVoiceMailNumber(int subId) {
        try {
            IPhoneSubInfo info = getSubscriberInfoService();
            if (info == null) {
                return null;
            }
            return info.getVoiceMailNumberForSubscriber(subId, getOpPackageName(), getAttributionTag());
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    public boolean setVoiceMailNumber(String alphaTag, String number) {
        return setVoiceMailNumber(getSubId(), alphaTag, number);
    }

    public boolean setVoiceMailNumber(int subId, String alphaTag, String number) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.setVoiceMailNumber(subId, alphaTag, number);
            }
            return false;
        } catch (RemoteException e) {
            return false;
        } catch (NullPointerException e2) {
            return false;
        }
    }

    @SystemApi
    public void setVisualVoicemailEnabled(PhoneAccountHandle phoneAccountHandle, boolean enabled) {
    }

    @SystemApi
    public boolean isVisualVoicemailEnabled(PhoneAccountHandle phoneAccountHandle) {
        return false;
    }

    @SystemApi
    public Bundle getVisualVoicemailSettings() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getVisualVoicemailSettings(this.mContext.getOpPackageName(), this.mSubId);
            }
            return null;
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    public String getVisualVoicemailPackageName() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getVisualVoicemailPackageName(this.mContext.getOpPackageName(), getAttributionTag(), getSubId());
            }
            return null;
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    public void setVisualVoicemailSmsFilterSettings(VisualVoicemailSmsFilterSettings settings) {
        if (settings == null) {
            disableVisualVoicemailSmsFilter(this.mSubId);
        } else {
            enableVisualVoicemailSmsFilter(this.mSubId, settings);
        }
    }

    public void sendVisualVoicemailSms(String number, int port, String text, PendingIntent sentIntent) {
        sendVisualVoicemailSmsForSubscriber(this.mSubId, number, port, text, sentIntent);
    }

    public void enableVisualVoicemailSmsFilter(int subId, VisualVoicemailSmsFilterSettings settings) {
        if (settings != null) {
            try {
                ITelephony telephony = getITelephony();
                if (telephony != null) {
                    telephony.enableVisualVoicemailSmsFilter(this.mContext.getOpPackageName(), subId, settings);
                }
            } catch (RemoteException e) {
            } catch (NullPointerException e2) {
            }
        } else {
            throw new IllegalArgumentException("Settings cannot be null");
        }
    }

    public void disableVisualVoicemailSmsFilter(int subId) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.disableVisualVoicemailSmsFilter(this.mContext.getOpPackageName(), subId);
            }
        } catch (RemoteException e) {
        } catch (NullPointerException e2) {
        }
    }

    public VisualVoicemailSmsFilterSettings getVisualVoicemailSmsFilterSettings(int subId) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getVisualVoicemailSmsFilterSettings(this.mContext.getOpPackageName(), subId);
            }
            return null;
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    public VisualVoicemailSmsFilterSettings getActiveVisualVoicemailSmsFilterSettings(int subId) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getActiveVisualVoicemailSmsFilterSettings(subId);
            }
            return null;
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    public void sendVisualVoicemailSmsForSubscriber(int subId, String number, int port, String text, PendingIntent sentIntent) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.sendVisualVoicemailSmsForSubscriber(this.mContext.getOpPackageName(), this.mContext.getAttributionTag(), subId, number, port, text, sentIntent);
            }
        } catch (RemoteException e) {
        }
    }

    @SystemApi
    public void setVoiceActivationState(int activationState) {
        setVoiceActivationState(getSubId(), activationState);
    }

    public void setVoiceActivationState(int subId, int activationState) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.setVoiceActivationState(subId, activationState);
            }
        } catch (RemoteException e) {
        } catch (NullPointerException e2) {
        }
    }

    @SystemApi
    public void setDataActivationState(int activationState) {
        setDataActivationState(getSubId(), activationState);
    }

    public void setDataActivationState(int subId, int activationState) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.setDataActivationState(subId, activationState);
            }
        } catch (RemoteException e) {
        } catch (NullPointerException e2) {
        }
    }

    @SystemApi
    public int getVoiceActivationState() {
        return getVoiceActivationState(getSubId());
    }

    public int getVoiceActivationState(int subId) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getVoiceActivationState(subId, getOpPackageName());
            }
            return 0;
        } catch (RemoteException e) {
            return 0;
        } catch (NullPointerException e2) {
            return 0;
        }
    }

    @SystemApi
    public int getDataActivationState() {
        return getDataActivationState(getSubId());
    }

    public int getDataActivationState(int subId) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getDataActivationState(subId, getOpPackageName());
            }
            return 0;
        } catch (RemoteException e) {
            return 0;
        } catch (NullPointerException e2) {
            return 0;
        }
    }

    public int getVoiceMessageCount() {
        return getVoiceMessageCount(getSubId());
    }

    public int getVoiceMessageCount(int subId) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony == null) {
                return 0;
            }
            return telephony.getVoiceMessageCountForSubscriber(subId, getOpPackageName(), getAttributionTag());
        } catch (RemoteException e) {
            return 0;
        } catch (NullPointerException e2) {
            return 0;
        }
    }

    public String getVoiceMailAlphaTag() {
        return getVoiceMailAlphaTag(getSubId());
    }

    public String getVoiceMailAlphaTag(int subId) {
        try {
            IPhoneSubInfo info = getSubscriberInfoService();
            if (info == null) {
                return null;
            }
            return info.getVoiceMailAlphaTagForSubscriber(subId, getOpPackageName(), getAttributionTag());
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    public void sendDialerSpecialCode(String inputCode) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.sendDialerSpecialCode(this.mContext.getOpPackageName(), inputCode);
            }
        } catch (RemoteException ex) {
            Rlog.e(TAG, "Telephony#sendDialerSpecialCode RemoteException" + ex);
        }
    }

    public String getIsimImpi() {
        try {
            IPhoneSubInfo info = getSubscriberInfoService();
            if (info == null) {
                return null;
            }
            return info.getIsimImpi(getSubId());
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    @SystemApi
    public String getIsimDomain() {
        try {
            IPhoneSubInfo info = getSubscriberInfoService();
            if (info == null) {
                return null;
            }
            return info.getIsimDomain(getSubId());
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    public String[] getIsimImpu() {
        try {
            IPhoneSubInfo info = getSubscriberInfoService();
            if (info == null) {
                return null;
            }
            return info.getIsimImpu(getSubId());
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    @Deprecated
    public int getCallState() {
        TelecomManager telecomManager;
        Context context = this.mContext;
        if (context == null || (telecomManager = (TelecomManager) context.getSystemService(TelecomManager.class)) == null) {
            return 0;
        }
        return telecomManager.getCallState();
    }

    public int getCallStateForSubscription() {
        return getCallState(getSubId());
    }

    public int getCallState(int subId) {
        ITelephony telephony = getITelephony();
        if (telephony == null) {
            return 0;
        }
        try {
            return telephony.getCallStateForSubscription(subId, this.mContext.getPackageName(), this.mContext.getAttributionTag());
        } catch (RemoteException e) {
            return 0;
        }
    }

    private IPhoneSubInfo getSubscriberInfo() {
        return getSubscriberInfoService();
    }

    public int getCallStateForSlot(int slotIndex) {
        try {
            int[] subId = SubscriptionManager.getSubId(slotIndex);
            ITelephony telephony = getITelephony();
            if (!(telephony == null || subId == null || subId.length == 0)) {
                return telephony.getCallStateForSubscription(subId[0], this.mContext.getPackageName(), this.mContext.getAttributionTag());
            }
            return 0;
        } catch (RemoteException | NullPointerException e) {
            return 0;
        }
    }

    public int getDataActivity() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony == null) {
                return 0;
            }
            return telephony.getDataActivityForSubId(getSubId(SubscriptionManager.getActiveDataSubscriptionId()));
        } catch (RemoteException e) {
            return 0;
        } catch (NullPointerException e2) {
            return 0;
        }
    }

    public int getDataState() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony == null) {
                return 0;
            }
            int state = telephony.getDataStateForSubId(getSubId(SubscriptionManager.getActiveDataSubscriptionId()));
            if (state == 4) {
                if (!Compatibility.isChangeEnabled((long) GET_DATA_STATE_R_VERSION)) {
                    return 2;
                }
            }
            return state;
        } catch (RemoteException e) {
            return 0;
        } catch (NullPointerException e2) {
            return 0;
        }
    }

    private ITelephony getITelephony() {
        if (!sServiceHandleCacheEnabled) {
            return ITelephony.Stub.asInterface(TelephonyFrameworkInitializer.getTelephonyServiceManager().getTelephonyServiceRegisterer().get());
        }
        if (sITelephony == null) {
            ITelephony temp = ITelephony.Stub.asInterface(TelephonyFrameworkInitializer.getTelephonyServiceManager().getTelephonyServiceRegisterer().get());
            synchronized (sCacheLock) {
                if (sITelephony == null && temp != null) {
                    try {
                        sITelephony = temp;
                        temp.asBinder().linkToDeath(sServiceDeath, 0);
                    } catch (Exception e) {
                        sITelephony = null;
                    }
                }
            }
        }
        return sITelephony;
    }

    private IOns getIOns() {
        return IOns.Stub.asInterface(TelephonyFrameworkInitializer.getTelephonyServiceManager().getOpportunisticNetworkServiceRegisterer().get());
    }

    @Deprecated
    public void listen(PhoneStateListener listener, int events) {
        if (this.mContext != null) {
            boolean notifyNow = getITelephony() != null;
            TelephonyRegistryManager telephonyRegistry = (TelephonyRegistryManager) this.mContext.getSystemService(Context.TELEPHONY_REGISTRY_SERVICE);
            if (telephonyRegistry != null) {
                telephonyRegistry.listenFromListener(this.mSubId, getOpPackageName(), getAttributionTag(), listener, events, notifyNow);
            } else {
                Rlog.w(TAG, "telephony registry not ready.");
            }
        }
    }

    @SystemApi
    public int getCdmaEnhancedRoamingIndicatorDisplayNumber() {
        return getCdmaEriIconIndex(getSubId());
    }

    public int getCdmaEriIconIndex(int subId) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony == null) {
                return -1;
            }
            return telephony.getCdmaEriIconIndexForSubscriber(subId, getOpPackageName(), getAttributionTag());
        } catch (RemoteException e) {
            return -1;
        } catch (NullPointerException e2) {
            return -1;
        }
    }

    public int getCdmaEriIconMode(int subId) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony == null) {
                return -1;
            }
            return telephony.getCdmaEriIconModeForSubscriber(subId, getOpPackageName(), getAttributionTag());
        } catch (RemoteException e) {
            return -1;
        } catch (NullPointerException e2) {
            return -1;
        }
    }

    public String getCdmaEriText() {
        return getCdmaEriText(getSubId());
    }

    public String getCdmaEriText(int subId) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony == null) {
                return null;
            }
            return telephony.getCdmaEriTextForSubscriber(subId, getOpPackageName(), getAttributionTag());
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    public boolean isVoiceCapable() {
        Context context = this.mContext;
        if (context == null) {
            return true;
        }
        return context.getResources().getBoolean(R.bool.config_voice_capable);
    }

    public boolean isSmsCapable() {
        Context context = this.mContext;
        if (context == null) {
            return true;
        }
        return context.getResources().getBoolean(R.bool.config_sms_capable);
    }

    public List<CellInfo> getAllCellInfo() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null && ((IOplusPermissionCheckInjector) OplusFeatureCache.getOrCreate(IOplusPermissionCheckInjector.DEFAULT, new Object[0])).checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, Binder.getCallingPid(), Binder.getCallingUid(), "getAllCellInfo")) {
                return telephony.getAllCellInfo(getOpPackageName(), getAttributionTag());
            }
            return null;
        } catch (RemoteException | NullPointerException e) {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class CellInfoCallback {
        public static final int ERROR_MODEM_ERROR = 2;
        public static final int ERROR_TIMEOUT = 1;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes3.dex */
        public @interface CellInfoCallbackError {
        }

        public abstract void onCellInfo(List<CellInfo> list);

        public void onError(int errorCode, Throwable detail) {
            onCellInfo(new ArrayList());
        }
    }

    public void requestCellInfoUpdate(final Executor executor, final CellInfoCallback callback) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.requestCellInfoUpdate(getSubId(), new AnonymousClass3(executor, callback), getOpPackageName(), getAttributionTag());
            } else if (Compatibility.isChangeEnabled((long) NULL_TELEPHONY_THROW_NO_CB)) {
                throw new IllegalStateException("Telephony is null");
            }
        } catch (RemoteException ex) {
            runOnBackgroundThread(new Runnable() { // from class: android.telephony.TelephonyManager$$ExternalSyntheticLambda10
                @Override // java.lang.Runnable
                public final void run() {
                    executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$$ExternalSyntheticLambda5
                        @Override // java.lang.Runnable
                        public final void run() {
                            TelephonyManager.CellInfoCallback.this.onError(2, r2);
                        }
                    });
                }
            });
        }
    }

    /* renamed from: android.telephony.TelephonyManager$3  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass3 extends ICellInfoCallback.Stub {
        final /* synthetic */ CellInfoCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass3(Executor executor, CellInfoCallback cellInfoCallback) {
            this.val$executor = executor;
            this.val$callback = cellInfoCallback;
        }

        @Override // android.telephony.ICellInfoCallback
        public void onCellInfo(final List<CellInfo> cellInfo) {
            long identity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final CellInfoCallback cellInfoCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$3$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        TelephonyManager.CellInfoCallback.this.onCellInfo(cellInfo);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(identity);
            }
        }

        @Override // android.telephony.ICellInfoCallback
        public void onError(final int errorCode, final String exceptionName, final String message) {
            long identity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final CellInfoCallback cellInfoCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$3$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        TelephonyManager.CellInfoCallback.this.onError(errorCode, TelephonyManager.createThrowableByClassName(exceptionName, message));
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(identity);
            }
        }
    }

    @SystemApi
    public void requestCellInfoUpdate(WorkSource workSource, final Executor executor, final CellInfoCallback callback) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.requestCellInfoUpdateWithWorkSource(getSubId(), new AnonymousClass4(executor, callback), getOpPackageName(), getAttributionTag(), workSource);
            } else if (Compatibility.isChangeEnabled((long) NULL_TELEPHONY_THROW_NO_CB)) {
                throw new IllegalStateException("Telephony is null");
            }
        } catch (RemoteException ex) {
            runOnBackgroundThread(new Runnable() { // from class: android.telephony.TelephonyManager$$ExternalSyntheticLambda11
                @Override // java.lang.Runnable
                public final void run() {
                    executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$$ExternalSyntheticLambda6
                        @Override // java.lang.Runnable
                        public final void run() {
                            TelephonyManager.CellInfoCallback.this.onError(2, r2);
                        }
                    });
                }
            });
        }
    }

    /* renamed from: android.telephony.TelephonyManager$4  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass4 extends ICellInfoCallback.Stub {
        final /* synthetic */ CellInfoCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass4(Executor executor, CellInfoCallback cellInfoCallback) {
            this.val$executor = executor;
            this.val$callback = cellInfoCallback;
        }

        @Override // android.telephony.ICellInfoCallback
        public void onCellInfo(final List<CellInfo> cellInfo) {
            long identity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final CellInfoCallback cellInfoCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$4$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        TelephonyManager.CellInfoCallback.this.onCellInfo(cellInfo);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(identity);
            }
        }

        @Override // android.telephony.ICellInfoCallback
        public void onError(final int errorCode, final String exceptionName, final String message) {
            long identity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final CellInfoCallback cellInfoCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$4$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        TelephonyManager.CellInfoCallback.this.onError(errorCode, TelephonyManager.createThrowableByClassName(exceptionName, message));
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(identity);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Throwable createThrowableByClassName(String className, String message) {
        if (className == null) {
            return null;
        }
        try {
            Class<?> c = Class.forName(className);
            return (Throwable) c.getConstructor(String.class).newInstance(message);
        } catch (ClassCastException | ReflectiveOperationException e) {
            return new RuntimeException(className + ": " + message);
        }
    }

    public void setCellInfoListRate(int rateInMillis) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.setCellInfoListRate(rateInMillis);
            }
        } catch (RemoteException e) {
        } catch (NullPointerException e2) {
        }
    }

    public String getMmsUserAgent() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getMmsUserAgent(getSubId());
            }
            return null;
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    public String getMmsUAProfUrl() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getMmsUAProfUrl(getSubId());
            }
            return null;
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    @Deprecated
    public IccOpenLogicalChannelResponse iccOpenLogicalChannel(String AID) {
        return iccOpenLogicalChannel(getSubId(), AID, -1);
    }

    @SystemApi
    public IccOpenLogicalChannelResponse iccOpenLogicalChannelBySlot(int slotIndex, String aid, int p2) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.iccOpenLogicalChannelBySlot(slotIndex, getOpPackageName(), aid, p2);
            }
            return null;
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    @Deprecated
    public IccOpenLogicalChannelResponse iccOpenLogicalChannel(String AID, int p2) {
        return iccOpenLogicalChannel(getSubId(), AID, p2);
    }

    @Deprecated
    public IccOpenLogicalChannelResponse iccOpenLogicalChannel(int subId, String AID, int p2) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.iccOpenLogicalChannel(subId, getOpPackageName(), AID, p2);
            }
            return null;
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    @SystemApi
    @Deprecated
    public boolean iccCloseLogicalChannelBySlot(int slotIndex, int channel) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.iccCloseLogicalChannelBySlot(slotIndex, channel);
            }
            return false;
        } catch (RemoteException e) {
            return false;
        } catch (NullPointerException e2) {
            return false;
        }
    }

    @Deprecated
    public boolean iccCloseLogicalChannel(int channel) {
        return iccCloseLogicalChannel(getSubId(), channel);
    }

    @Deprecated
    public boolean iccCloseLogicalChannel(int subId, int channel) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.iccCloseLogicalChannel(subId, channel);
            }
            return false;
        } catch (RemoteException e) {
            return false;
        } catch (NullPointerException e2) {
            return false;
        }
    }

    @SystemApi
    @Deprecated
    public String iccTransmitApduLogicalChannelBySlot(int slotIndex, int channel, int cla, int instruction, int p1, int p2, int p3, String data) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.iccTransmitApduLogicalChannelBySlot(slotIndex, channel, cla, instruction, p1, p2, p3, data);
            }
            return null;
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    @Deprecated
    public String iccTransmitApduLogicalChannel(int channel, int cla, int instruction, int p1, int p2, int p3, String data) {
        return iccTransmitApduLogicalChannel(getSubId(), channel, cla, instruction, p1, p2, p3, data);
    }

    @Deprecated
    public String iccTransmitApduLogicalChannel(int subId, int channel, int cla, int instruction, int p1, int p2, int p3, String data) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.iccTransmitApduLogicalChannel(subId, channel, cla, instruction, p1, p2, p3, data);
            }
            return "";
        } catch (RemoteException e) {
            return "";
        } catch (NullPointerException e2) {
            return "";
        }
    }

    @SystemApi
    @Deprecated
    public String iccTransmitApduBasicChannelBySlot(int slotIndex, int cla, int instruction, int p1, int p2, int p3, String data) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.iccTransmitApduBasicChannelBySlot(slotIndex, getOpPackageName(), cla, instruction, p1, p2, p3, data);
            }
            return null;
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    @Deprecated
    public String iccTransmitApduBasicChannel(int cla, int instruction, int p1, int p2, int p3, String data) {
        return iccTransmitApduBasicChannel(getSubId(), cla, instruction, p1, p2, p3, data);
    }

    @Deprecated
    public String iccTransmitApduBasicChannel(int subId, int cla, int instruction, int p1, int p2, int p3, String data) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.iccTransmitApduBasicChannel(subId, getOpPackageName(), cla, instruction, p1, p2, p3, data);
            }
            return "";
        } catch (RemoteException e) {
            return "";
        } catch (NullPointerException e2) {
            return "";
        }
    }

    @Deprecated
    public byte[] iccExchangeSimIO(int fileID, int command, int p1, int p2, int p3, String filePath) {
        return iccExchangeSimIO(getSubId(), fileID, command, p1, p2, p3, filePath);
    }

    @Deprecated
    public byte[] iccExchangeSimIO(int subId, int fileID, int command, int p1, int p2, int p3, String filePath) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.iccExchangeSimIO(subId, fileID, command, p1, p2, p3, filePath);
            }
            return null;
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    @Deprecated
    public String sendEnvelopeWithStatus(String content) {
        return sendEnvelopeWithStatus(getSubId(), content);
    }

    @Deprecated
    public String sendEnvelopeWithStatus(int subId, String content) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.sendEnvelopeWithStatus(subId, content);
            }
            return "";
        } catch (RemoteException e) {
            return "";
        } catch (NullPointerException e2) {
            return "";
        }
    }

    public String nvReadItem(int itemID) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.nvReadItem(itemID);
            }
            return "";
        } catch (RemoteException ex) {
            Rlog.e(TAG, "nvReadItem RemoteException", ex);
            return "";
        } catch (NullPointerException ex2) {
            Rlog.e(TAG, "nvReadItem NPE", ex2);
            return "";
        }
    }

    public boolean nvWriteItem(int itemID, String itemValue) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.nvWriteItem(itemID, itemValue);
            }
            return false;
        } catch (RemoteException ex) {
            Rlog.e(TAG, "nvWriteItem RemoteException", ex);
            return false;
        } catch (NullPointerException ex2) {
            Rlog.e(TAG, "nvWriteItem NPE", ex2);
            return false;
        }
    }

    public boolean nvWriteCdmaPrl(byte[] preferredRoamingList) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.nvWriteCdmaPrl(preferredRoamingList);
            }
            return false;
        } catch (RemoteException ex) {
            Rlog.e(TAG, "nvWriteCdmaPrl RemoteException", ex);
            return false;
        } catch (NullPointerException ex2) {
            Rlog.e(TAG, "nvWriteCdmaPrl NPE", ex2);
            return false;
        }
    }

    public boolean nvResetConfig(int resetType) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony == null) {
                return false;
            }
            if (resetType == 1) {
                return telephony.rebootModem(getSlotIndex());
            }
            if (resetType == 3) {
                return telephony.resetModemConfig(getSlotIndex());
            }
            Rlog.e(TAG, "nvResetConfig unsupported reset type");
            return false;
        } catch (RemoteException ex) {
            Rlog.e(TAG, "nvResetConfig RemoteException", ex);
            return false;
        } catch (NullPointerException ex2) {
            Rlog.e(TAG, "nvResetConfig NPE", ex2);
            return false;
        }
    }

    @SystemApi
    public boolean resetRadioConfig() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.resetModemConfig(getSlotIndex());
            }
            return false;
        } catch (RemoteException ex) {
            Rlog.e(TAG, "resetRadioConfig RemoteException", ex);
            return false;
        } catch (NullPointerException ex2) {
            Rlog.e(TAG, "resetRadioConfig NPE", ex2);
            return false;
        }
    }

    @SystemApi
    public boolean rebootRadio() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.rebootModem(getSlotIndex());
            }
            return false;
        } catch (RemoteException ex) {
            Rlog.e(TAG, "rebootRadio RemoteException", ex);
            return false;
        } catch (NullPointerException ex2) {
            Rlog.e(TAG, "rebootRadio NPE", ex2);
            return false;
        }
    }

    public int getSubscriptionId() {
        return getSubId();
    }

    private int getSubId() {
        if (SubscriptionManager.isUsableSubIdValue(this.mSubId)) {
            return this.mSubId;
        }
        return SubscriptionManager.getDefaultSubscriptionId();
    }

    private int getSubId(int preferredSubId) {
        if (SubscriptionManager.isUsableSubIdValue(this.mSubId)) {
            return this.mSubId;
        }
        return preferredSubId;
    }

    private int getPhoneId() {
        return SubscriptionManager.getPhoneId(getSubId());
    }

    private int getPhoneId(int preferredSubId) {
        return SubscriptionManager.getPhoneId(getSubId(preferredSubId));
    }

    public int getSlotIndex() {
        int slotIndex = SubscriptionManager.getSlotIndex(getSubId());
        if (slotIndex == -1) {
            return Integer.MAX_VALUE;
        }
        return slotIndex;
    }

    @SystemApi
    public void requestNumberVerification(PhoneNumberRange range, long timeoutMillis, final Executor executor, final NumberVerificationCallback callback) {
        if (executor == null) {
            throw new NullPointerException("Executor must be non-null");
        } else if (callback != null) {
            INumberVerificationCallback internalCallback = new AnonymousClass5(executor, callback);
            try {
                ITelephony telephony = getITelephony();
                if (telephony != null) {
                    telephony.requestNumberVerification(range, timeoutMillis, internalCallback, getOpPackageName());
                } else if (Compatibility.isChangeEnabled((long) NULL_TELEPHONY_THROW_NO_CB)) {
                    throw new IllegalStateException("Telephony is null");
                }
            } catch (RemoteException ex) {
                Rlog.e(TAG, "requestNumberVerification RemoteException", ex);
                runOnBackgroundThread(new Runnable() { // from class: android.telephony.TelephonyManager$$ExternalSyntheticLambda9
                    @Override // java.lang.Runnable
                    public final void run() {
                        executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$$ExternalSyntheticLambda2
                            @Override // java.lang.Runnable
                            public final void run() {
                                NumberVerificationCallback.this.onVerificationFailed(0);
                            }
                        });
                    }
                });
            }
        } else {
            throw new NullPointerException("Callback must be non-null");
        }
    }

    /* renamed from: android.telephony.TelephonyManager$5  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass5 extends INumberVerificationCallback.Stub {
        final /* synthetic */ NumberVerificationCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass5(Executor executor, NumberVerificationCallback numberVerificationCallback) {
            this.val$executor = executor;
            this.val$callback = numberVerificationCallback;
        }

        @Override // com.android.internal.telephony.INumberVerificationCallback
        public void onCallReceived(final String phoneNumber) {
            long identity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final NumberVerificationCallback numberVerificationCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$5$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        NumberVerificationCallback.this.onCallReceived(phoneNumber);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(identity);
            }
        }

        @Override // com.android.internal.telephony.INumberVerificationCallback
        public void onVerificationFailed(final int reason) {
            long identity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final NumberVerificationCallback numberVerificationCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$5$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        NumberVerificationCallback.this.onVerificationFailed(reason);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(identity);
            }
        }
    }

    private static <T> List<T> updateTelephonyProperty(List<T> prop, int phoneId, T value) {
        List<T> ret = new ArrayList<>(prop);
        while (ret.size() <= phoneId) {
            ret.add(null);
        }
        ret.set(phoneId, value);
        return ret;
    }

    public static int getIntAtIndex(ContentResolver cr, String name, int index) throws Settings.SettingNotFoundException {
        String v = Settings.Global.getString(cr, name);
        if (v != null) {
            String[] valArray = v.split(",");
            if (index >= 0 && index < valArray.length && valArray[index] != null) {
                try {
                    return Integer.parseInt(valArray[index]);
                } catch (NumberFormatException e) {
                }
            }
        }
        throw new Settings.SettingNotFoundException(name);
    }

    public static boolean putIntAtIndex(ContentResolver cr, String name, int index, int value) {
        String data = "";
        String[] valArray = null;
        String v = Settings.Global.getString(cr, name);
        if (index == Integer.MAX_VALUE) {
            throw new IllegalArgumentException("putIntAtIndex index == MAX_VALUE index=" + index);
        } else if (index >= 0) {
            if (v != null) {
                valArray = v.split(",");
            }
            for (int i = 0; i < index; i++) {
                String str = "";
                if (valArray != null && i < valArray.length) {
                    str = valArray[i];
                }
                data = data + str + ",";
            }
            String data2 = data + value;
            if (valArray != null) {
                for (int i2 = index + 1; i2 < valArray.length; i2++) {
                    data2 = data2 + "," + valArray[i2];
                }
            }
            return Settings.Global.putString(cr, name, data2);
        } else {
            throw new IllegalArgumentException("putIntAtIndex index < 0 index=" + index);
        }
    }

    public static String getTelephonyProperty(int phoneId, String property, String defaultVal) {
        String propVal = null;
        String prop = SystemProperties.get(property);
        if (prop != null && prop.length() > 0) {
            String[] values = prop.split(",");
            if (phoneId >= 0 && phoneId < values.length && values[phoneId] != null) {
                propVal = values[phoneId];
            }
        }
        return propVal == null ? defaultVal : propVal;
    }

    private static <T> T getTelephonyProperty(int phoneId, List<T> prop, T defaultValue) {
        T ret = null;
        if (phoneId >= 0 && phoneId < prop.size()) {
            ret = prop.get(phoneId);
        }
        return ret != null ? ret : defaultValue;
    }

    public static String getTelephonyProperty(String property, String defaultVal) {
        String propVal = SystemProperties.get(property);
        return TextUtils.isEmpty(propVal) ? defaultVal : propVal;
    }

    public int getSimCount() {
        return getPhoneCount();
    }

    @SystemApi
    public String getIsimIst() {
        try {
            IPhoneSubInfo info = getSubscriberInfoService();
            if (info == null) {
                return null;
            }
            return info.getIsimIst(getSubId());
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    public String[] getIsimPcscf() {
        try {
            IPhoneSubInfo info = getSubscriberInfoService();
            if (info == null) {
                return null;
            }
            return info.getIsimPcscf(getSubId());
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    public String getIccAuthentication(int appType, int authType, String data) {
        return getIccAuthentication(getSubId(), appType, authType, data);
    }

    public String getIccAuthentication(int subId, int appType, int authType, String data) {
        try {
            IPhoneSubInfo info = getSubscriberInfoService();
            if (info == null) {
                return null;
            }
            return info.getIccSimChallengeResponse(subId, appType, authType, data, getOpPackageName(), getAttributionTag());
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    public String[] getForbiddenPlmns() {
        return getForbiddenPlmns(getSubId(), 2);
    }

    public String[] getForbiddenPlmns(int subId, int appType) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony == null) {
                return null;
            }
            return telephony.getForbiddenPlmns(subId, appType, this.mContext.getOpPackageName(), getAttributionTag());
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    public int setForbiddenPlmns(List<String> fplmns) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony == null) {
                return -1;
            }
            return telephony.setForbiddenPlmns(getSubId(), 2, fplmns, getOpPackageName(), getAttributionTag());
        } catch (RemoteException ex) {
            Rlog.e(TAG, "setForbiddenPlmns RemoteException: " + ex.getMessage());
            return -1;
        } catch (NullPointerException ex2) {
            Rlog.e(TAG, "setForbiddenPlmns NullPointerException: " + ex2.getMessage());
            return -1;
        }
    }

    public String[] getPcscfAddress(String apnType) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony == null) {
                return new String[0];
            }
            return telephony.getPcscfAddress(apnType, getOpPackageName(), getAttributionTag());
        } catch (RemoteException e) {
            return new String[0];
        }
    }

    @SystemApi
    public void resetIms(int slotIndex) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.resetIms(slotIndex);
            }
        } catch (RemoteException e) {
            Rlog.e(TAG, "toggleImsOnOff, RemoteException: " + e.getMessage());
        }
    }

    public void enableIms(int slotId) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.enableIms(slotId);
            }
        } catch (RemoteException e) {
            Rlog.e(TAG, "enableIms, RemoteException: " + e.getMessage());
        }
    }

    public void disableIms(int slotId) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.disableIms(slotId);
            }
        } catch (RemoteException e) {
            Rlog.e(TAG, "disableIms, RemoteException: " + e.getMessage());
        }
    }

    public IImsRegistration getImsRegistration(int slotIndex, int feature) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getImsRegistration(slotIndex, feature);
            }
            return null;
        } catch (RemoteException e) {
            Rlog.e(TAG, "getImsRegistration, RemoteException: " + e.getMessage());
            return null;
        }
    }

    public IImsConfig getImsConfig(int slotIndex, int feature) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getImsConfig(slotIndex, feature);
            }
            return null;
        } catch (RemoteException e) {
            Rlog.e(TAG, "getImsRegistration, RemoteException: " + e.getMessage());
            return null;
        }
    }

    public void setImsRegistrationState(boolean registered) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.setImsRegistrationState(registered);
            }
        } catch (RemoteException e) {
        }
    }

    @Deprecated
    public int getPreferredNetworkType(int subId) {
        return RadioAccessFamily.getNetworkTypeFromRaf((int) getAllowedNetworkTypesBitmask());
    }

    @SystemApi
    @Deprecated
    public long getPreferredNetworkTypeBitmask() {
        return getAllowedNetworkTypesBitmask();
    }

    @SystemApi
    public long getAllowedNetworkTypesBitmask() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getAllowedNetworkTypesBitmask(getSubId());
            }
            return 0L;
        } catch (RemoteException ex) {
            Rlog.e(TAG, "getAllowedNetworkTypesBitmask RemoteException", ex);
            return 0L;
        }
    }

    @SystemApi
    @Deprecated
    public long getAllowedNetworkTypes() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getAllowedNetworkTypesForReason(getSubId(), 2);
            }
            return -1L;
        } catch (RemoteException ex) {
            Rlog.e(TAG, "getAllowedNetworkTypes RemoteException", ex);
            return -1L;
        }
    }

    public void setNetworkSelectionModeAutomatic() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.setNetworkSelectionModeAutomatic(getSubId());
            }
        } catch (RemoteException ex) {
            Rlog.e(TAG, "setNetworkSelectionModeAutomatic RemoteException", ex);
        } catch (NullPointerException ex2) {
            Rlog.e(TAG, "setNetworkSelectionModeAutomatic NPE", ex2);
        }
    }

    public CellNetworkScanResult getAvailableNetworks() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getCellNetworkScanResults(getSubId(), getOpPackageName(), getAttributionTag());
            }
        } catch (RemoteException ex) {
            Rlog.e(TAG, "getAvailableNetworks RemoteException", ex);
        } catch (NullPointerException ex2) {
            Rlog.e(TAG, "getAvailableNetworks NPE", ex2);
        }
        return new CellNetworkScanResult(4, (List<OperatorInfo>) null);
    }

    public NetworkScan requestNetworkScan(NetworkScanRequest request, Executor executor, TelephonyScanManager.NetworkScanCallback callback) {
        synchronized (this) {
            if (this.mTelephonyScanManager == null) {
                this.mTelephonyScanManager = new TelephonyScanManager();
            }
        }
        return this.mTelephonyScanManager.requestNetworkScan(getSubId(), request, executor, callback, getOpPackageName(), getAttributionTag());
    }

    @Deprecated
    public NetworkScan requestNetworkScan(NetworkScanRequest request, TelephonyScanManager.NetworkScanCallback callback) {
        return requestNetworkScan(request, AsyncTask.SERIAL_EXECUTOR, callback);
    }

    public boolean setNetworkSelectionModeManual(String operatorNumeric, boolean persistSelection) {
        return setNetworkSelectionModeManual(new OperatorInfo("", "", operatorNumeric), persistSelection);
    }

    public boolean setNetworkSelectionModeManual(String operatorNumeric, boolean persistSelection, int ran) {
        return setNetworkSelectionModeManual(new OperatorInfo("", "", operatorNumeric, ran), persistSelection);
    }

    public boolean setNetworkSelectionModeManual(OperatorInfo operatorInfo, boolean persistSelection) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.setNetworkSelectionModeManual(getSubId(), operatorInfo, persistSelection);
            }
            return false;
        } catch (RemoteException ex) {
            Rlog.e(TAG, "setNetworkSelectionModeManual RemoteException", ex);
            return false;
        }
    }

    public int getNetworkSelectionMode() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony == null) {
                return 0;
            }
            int mode = telephony.getNetworkSelectionMode(getSubId());
            return mode;
        } catch (RemoteException ex) {
            Rlog.e(TAG, "getNetworkSelectionMode RemoteException", ex);
            return 0;
        }
    }

    public String getManualNetworkSelectionPlmn() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony == null || !isManualNetworkSelectionAllowed()) {
                return null;
            }
            return telephony.getManualNetworkSelectionPlmn(getSubId());
        } catch (RemoteException ex) {
            Rlog.e(TAG, "getManualNetworkSelectionPlmn RemoteException", ex);
            return null;
        }
    }

    @SystemApi
    public boolean isInEmergencySmsMode() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.isInEmergencySmsMode();
            }
            return false;
        } catch (RemoteException ex) {
            Rlog.e(TAG, "isInEmergencySmsMode RemoteException", ex);
            return false;
        }
    }

    @Deprecated
    public boolean setPreferredNetworkType(int subId, int networkType) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.setAllowedNetworkTypesForReason(subId, 0, RadioAccessFamily.getRafFromNetworkType(networkType));
            }
        } catch (RemoteException ex) {
            Rlog.e(TAG, "setPreferredNetworkType RemoteException", ex);
        }
        return false;
    }

    @SystemApi
    @Deprecated
    public boolean setPreferredNetworkTypeBitmask(long networkTypeBitmask) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.setAllowedNetworkTypesForReason(getSubId(), 0, networkTypeBitmask);
            }
        } catch (RemoteException ex) {
            Rlog.e(TAG, "setPreferredNetworkTypeBitmask RemoteException", ex);
        }
        return false;
    }

    @SystemApi
    @Deprecated
    public boolean setAllowedNetworkTypes(long allowedNetworkTypes) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.setAllowedNetworkTypesForReason(getSubId(), 2, allowedNetworkTypes);
            }
            return false;
        } catch (RemoteException ex) {
            Rlog.e(TAG, "setAllowedNetworkTypes RemoteException", ex);
            return false;
        }
    }

    @SystemApi
    public void setAllowedNetworkTypesForReason(int reason, long allowedNetworkTypes) {
        if (isValidAllowedNetworkTypesReason(reason)) {
            try {
                ITelephony telephony = getITelephony();
                if (telephony != null) {
                    telephony.setAllowedNetworkTypesForReason(getSubId(), reason, allowedNetworkTypes);
                    return;
                }
                throw new IllegalStateException("telephony service is null.");
            } catch (RemoteException ex) {
                Rlog.e(TAG, "setAllowedNetworkTypesForReason RemoteException", ex);
                ex.rethrowFromSystemServer();
            }
        } else {
            throw new IllegalArgumentException("invalid AllowedNetworkTypesReason.");
        }
    }

    @SystemApi
    public long getAllowedNetworkTypesForReason(int reason) {
        if (isValidAllowedNetworkTypesReason(reason)) {
            try {
                ITelephony telephony = getITelephony();
                if (telephony != null) {
                    return telephony.getAllowedNetworkTypesForReason(getSubId(), reason);
                }
                throw new IllegalStateException("telephony service is null.");
            } catch (RemoteException ex) {
                Rlog.e(TAG, "getAllowedNetworkTypesForReason RemoteException", ex);
                ex.rethrowFromSystemServer();
                return -1L;
            }
        } else {
            throw new IllegalArgumentException("invalid AllowedNetworkTypesReason.");
        }
    }

    public static boolean isValidAllowedNetworkTypesReason(int reason) {
        switch (reason) {
            case 0:
            case 1:
            case 2:
            case 3:
                return true;
            default:
                return false;
        }
    }

    public static long getAllNetworkTypesBitmask() {
        return 916479L;
    }

    public static String convertNetworkTypeBitmaskToString(final long networkTypeBitmask) {
        String networkTypeName = (String) IntStream.rangeClosed(1, 20).filter(new IntPredicate() { // from class: android.telephony.TelephonyManager$$ExternalSyntheticLambda19
            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                return TelephonyManager.lambda$convertNetworkTypeBitmaskToString$9(networkTypeBitmask, i);
            }
        }).mapToObj(TelephonyManager$$ExternalSyntheticLambda18.INSTANCE).collect(Collectors.joining("|"));
        return TextUtils.isEmpty(networkTypeName) ? "UNKNOWN" : networkTypeName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$convertNetworkTypeBitmaskToString$9(long networkTypeBitmask, int x) {
        return (getBitMaskForNetworkType(x) & networkTypeBitmask) == getBitMaskForNetworkType(x);
    }

    public boolean setPreferredNetworkTypeToGlobal() {
        return setPreferredNetworkTypeToGlobal(getSubId());
    }

    public boolean setPreferredNetworkTypeToGlobal(int subId) {
        return setPreferredNetworkType(subId, 10);
    }

    @SystemApi
    public boolean isTetheringApnRequired() {
        return isTetheringApnRequired(getSubId(SubscriptionManager.getActiveDataSubscriptionId()));
    }

    public boolean isTetheringApnRequired(int subId) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.isTetheringApnRequiredForSubscriber(subId);
            }
            return false;
        } catch (RemoteException ex) {
            Rlog.e(TAG, "hasMatchedTetherApnSetting RemoteException", ex);
            return false;
        } catch (NullPointerException ex2) {
            Rlog.e(TAG, "hasMatchedTetherApnSetting NPE", ex2);
            return false;
        }
    }

    public boolean hasCarrierPrivileges() {
        return hasCarrierPrivileges(getSubId());
    }

    public boolean hasCarrierPrivileges(int subId) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getCarrierPrivilegeStatus(subId) == 1;
            }
        } catch (RemoteException ex) {
            Rlog.e(TAG, "hasCarrierPrivileges RemoteException", ex);
        } catch (NullPointerException ex2) {
            Rlog.e(TAG, "hasCarrierPrivileges NPE", ex2);
        }
        return false;
    }

    public boolean setOperatorBrandOverride(String brand) {
        return setOperatorBrandOverride(getSubId(), brand);
    }

    public boolean setOperatorBrandOverride(int subId, String brand) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.setOperatorBrandOverride(subId, brand);
            }
            return false;
        } catch (RemoteException ex) {
            Rlog.e(TAG, "setOperatorBrandOverride RemoteException", ex);
            return false;
        } catch (NullPointerException ex2) {
            Rlog.e(TAG, "setOperatorBrandOverride NPE", ex2);
            return false;
        }
    }

    public boolean setRoamingOverride(List<String> gsmRoamingList, List<String> gsmNonRoamingList, List<String> cdmaRoamingList, List<String> cdmaNonRoamingList) {
        return setRoamingOverride(getSubId(), gsmRoamingList, gsmNonRoamingList, cdmaRoamingList, cdmaNonRoamingList);
    }

    public boolean setRoamingOverride(int subId, List<String> gsmRoamingList, List<String> gsmNonRoamingList, List<String> cdmaRoamingList, List<String> cdmaNonRoamingList) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.setRoamingOverride(subId, gsmRoamingList, gsmNonRoamingList, cdmaRoamingList, cdmaNonRoamingList);
            }
            return false;
        } catch (RemoteException ex) {
            Rlog.e(TAG, "setRoamingOverride RemoteException", ex);
            return false;
        } catch (NullPointerException ex2) {
            Rlog.e(TAG, "setRoamingOverride NPE", ex2);
            return false;
        }
    }

    @SystemApi
    public String getCdmaMdn() {
        return getCdmaMdn(getSubId());
    }

    @SystemApi
    public String getCdmaMdn(int subId) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony == null) {
                return null;
            }
            return telephony.getCdmaMdn(subId);
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    @SystemApi
    public String getCdmaMin() {
        return getCdmaMin(getSubId());
    }

    @SystemApi
    public String getCdmaMin(int subId) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony == null) {
                return null;
            }
            return telephony.getCdmaMin(subId);
        } catch (RemoteException e) {
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    @SystemApi
    public int checkCarrierPrivilegesForPackage(String pkgName) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.checkCarrierPrivilegesForPackage(getSubId(), pkgName);
            }
            return 0;
        } catch (RemoteException ex) {
            Rlog.e(TAG, "checkCarrierPrivilegesForPackage RemoteException", ex);
            return 0;
        } catch (NullPointerException ex2) {
            Rlog.e(TAG, "checkCarrierPrivilegesForPackage NPE", ex2);
            return 0;
        }
    }

    @SystemApi
    public int checkCarrierPrivilegesForPackageAnyPhone(String pkgName) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.checkCarrierPrivilegesForPackageAnyPhone(pkgName);
            }
            return 0;
        } catch (RemoteException ex) {
            Rlog.e(TAG, "checkCarrierPrivilegesForPackageAnyPhone RemoteException", ex);
            return 0;
        } catch (NullPointerException ex2) {
            Rlog.e(TAG, "checkCarrierPrivilegesForPackageAnyPhone NPE", ex2);
            return 0;
        }
    }

    @SystemApi
    public List<String> getCarrierPackageNamesForIntent(Intent intent) {
        return getCarrierPackageNamesForIntentAndPhone(intent, getPhoneId());
    }

    @SystemApi
    public List<String> getCarrierPackageNamesForIntentAndPhone(Intent intent, int phoneId) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getCarrierPackageNamesForIntentAndPhone(intent, phoneId);
            }
            return null;
        } catch (RemoteException ex) {
            Rlog.e(TAG, "getCarrierPackageNamesForIntentAndPhone RemoteException", ex);
            return null;
        } catch (NullPointerException ex2) {
            Rlog.e(TAG, "getCarrierPackageNamesForIntentAndPhone NPE", ex2);
            return null;
        }
    }

    public List<String> getPackagesWithCarrierPrivileges() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getPackagesWithCarrierPrivileges(getPhoneId());
            }
        } catch (RemoteException ex) {
            Rlog.e(TAG, "getPackagesWithCarrierPrivileges RemoteException", ex);
        } catch (NullPointerException ex2) {
            Rlog.e(TAG, "getPackagesWithCarrierPrivileges NPE", ex2);
        }
        return Collections.EMPTY_LIST;
    }

    @SystemApi
    public List<String> getCarrierPrivilegedPackagesForAllActiveSubscriptions() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getPackagesWithCarrierPrivilegesForAllPhones();
            }
        } catch (RemoteException ex) {
            Rlog.e(TAG, "getCarrierPrivilegedPackagesForAllActiveSubscriptions RemoteException", ex);
        } catch (NullPointerException ex2) {
            Rlog.e(TAG, "getCarrierPrivilegedPackagesForAllActiveSubscriptions NPE", ex2);
        }
        return Collections.EMPTY_LIST;
    }

    public void setCallComposerStatus(int status) {
        if (status > 1 || status < 0) {
            throw new IllegalArgumentException("requested status is invalid");
        }
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.setCallComposerStatus(getSubId(), status);
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "Error calling ITelephony#setCallComposerStatus", ex);
            ex.rethrowFromSystemServer();
        }
    }

    public int getCallComposerStatus() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getCallComposerStatus(getSubId());
            }
            return 0;
        } catch (RemoteException ex) {
            Log.e(TAG, "Error calling ITelephony#getCallComposerStatus", ex);
            ex.rethrowFromSystemServer();
            return 0;
        }
    }

    @SystemApi
    public void dial(String number) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.dial(number);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#dial", e);
        }
    }

    @SystemApi
    @Deprecated
    public void call(String callingPackage, String number) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.call(callingPackage, number);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#call", e);
        }
    }

    @SystemApi
    @Deprecated
    public boolean endCall() {
        return false;
    }

    @SystemApi
    @Deprecated
    public void answerRingingCall() {
    }

    @SystemApi
    @Deprecated
    public void silenceRinger() {
    }

    @SystemApi
    @Deprecated
    public boolean isOffhook() {
        TelecomManager tm = (TelecomManager) this.mContext.getSystemService(Context.TELECOM_SERVICE);
        return tm.isInCall();
    }

    @SystemApi
    @Deprecated
    public boolean isRinging() {
        TelecomManager tm = (TelecomManager) this.mContext.getSystemService(Context.TELECOM_SERVICE);
        return tm.isRinging();
    }

    @SystemApi
    @Deprecated
    public boolean isIdle() {
        TelecomManager tm = (TelecomManager) this.mContext.getSystemService(Context.TELECOM_SERVICE);
        return !tm.isInCall();
    }

    @SystemApi
    @Deprecated
    public boolean isRadioOn() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.isRadioOnWithFeature(getOpPackageName(), getAttributionTag());
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#isRadioOn", e);
            return false;
        }
    }

    @SystemApi
    public boolean supplyPin(String pin) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.supplyPinForSubscriber(getSubId(), pin);
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#supplyPinForSubscriber", e);
            return false;
        }
    }

    @SystemApi
    public boolean supplyPuk(String puk, String pin) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.supplyPukForSubscriber(getSubId(), puk, pin);
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#supplyPukForSubscriber", e);
            return false;
        }
    }

    @SystemApi
    @Deprecated
    public int[] supplyPinReportResult(String pin) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.supplyPinReportResultForSubscriber(getSubId(), pin);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#supplyPinReportResultForSubscriber", e);
        }
        return new int[0];
    }

    @SystemApi
    @Deprecated
    public int[] supplyPukReportResult(String puk, String pin) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.supplyPukReportResultForSubscriber(getSubId(), puk, pin);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#supplyPukReportResultForSubscriber", e);
        }
        return new int[0];
    }

    @SystemApi
    public PinResult supplyIccLockPin(String pin) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                int[] result = telephony.supplyPinReportResultForSubscriber(getSubId(), pin);
                return new PinResult(result[0], result[1]);
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#supplyIccLockPin", e);
            e.rethrowFromSystemServer();
            return PinResult.getDefaultFailedResult();
        }
    }

    @SystemApi
    public PinResult supplyIccLockPuk(String puk, String pin) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                int[] result = telephony.supplyPukReportResultForSubscriber(getSubId(), puk, pin);
                return new PinResult(result[0], result[1]);
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#supplyIccLockPuk", e);
            e.rethrowFromSystemServer();
            return PinResult.getDefaultFailedResult();
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class UssdResponseCallback {
        public void onReceiveUssdResponse(TelephonyManager telephonyManager, String request, CharSequence response) {
        }

        public void onReceiveUssdResponseFailed(TelephonyManager telephonyManager, String request, int failureCode) {
        }
    }

    public void sendUssdRequest(String ussdRequest, final UssdResponseCallback callback, Handler handler) {
        Preconditions.checkNotNull(callback, "UssdResponseCallback cannot be null.");
        ResultReceiver wrappedCallback = new ResultReceiver(handler) { // from class: android.telephony.TelephonyManager.6
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.ResultReceiver
            public void onReceiveResult(int resultCode, Bundle ussdResponse) {
                Rlog.d(TelephonyManager.TAG, "USSD:" + resultCode);
                Preconditions.checkNotNull(ussdResponse, "ussdResponse cannot be null.");
                UssdResponse response = (UssdResponse) ussdResponse.getParcelable(TelephonyManager.USSD_RESPONSE);
                if (resultCode == 100) {
                    callback.onReceiveUssdResponse(telephonyManager, response.getUssdRequest(), response.getReturnMessage());
                } else {
                    callback.onReceiveUssdResponseFailed(telephonyManager, response.getUssdRequest(), resultCode);
                }
            }
        };
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.handleUssdRequest(getSubId(), ussdRequest, wrappedCallback);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#sendUSSDCode", e);
            UssdResponse response = new UssdResponse(ussdRequest, "");
            Bundle returnData = new Bundle();
            returnData.putParcelable(USSD_RESPONSE, response);
            wrappedCallback.send(-2, returnData);
        }
    }

    public boolean isConcurrentVoiceAndDataSupported() {
        boolean z = false;
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                z = telephony.isConcurrentVoiceAndDataAllowed(getSubId());
            }
            return z;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#isConcurrentVoiceAndDataAllowed", e);
            return false;
        }
    }

    @SystemApi
    public boolean handlePinMmi(String dialString) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.handlePinMmi(dialString);
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#handlePinMmi", e);
            return false;
        }
    }

    @SystemApi
    public boolean handlePinMmiForSubscriber(int subId, String dialString) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.handlePinMmiForSubscriber(subId, dialString);
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#handlePinMmi", e);
            return false;
        }
    }

    @SystemApi
    public void toggleRadioOnOff() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.toggleRadioOnOff();
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#toggleRadioOnOff", e);
        }
    }

    @SystemApi
    public boolean setRadio(boolean turnOn) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.setRadio(turnOn);
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#setRadio", e);
            return false;
        }
    }

    @SystemApi
    public boolean setRadioPower(boolean turnOn) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.setRadioPower(turnOn);
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#setRadioPower", e);
            return false;
        }
    }

    @SystemApi
    public void shutdownAllRadios() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.shutdownMobileRadios();
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#shutdownAllRadios", e);
            e.rethrowAsRuntimeException();
        }
    }

    @SystemApi
    public boolean isAnyRadioPoweredOn() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.needMobileRadioShutdown();
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#isAnyRadioPoweredOn", e);
            e.rethrowAsRuntimeException();
            return false;
        }
    }

    @SystemApi
    public int getRadioPowerState() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getRadioPowerState(getSlotIndex(), this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            }
            return 2;
        } catch (RemoteException e) {
            return 2;
        }
    }

    @SystemApi
    public void updateServiceLocation() {
        Log.e(TAG, "Do not call TelephonyManager#updateServiceLocation()");
    }

    @SystemApi
    public boolean enableDataConnectivity() {
        try {
            ITelephony telephony = getITelephony();
            if (((IOplusPermissionCheckInjector) OplusFeatureCache.getOrCreate(IOplusPermissionCheckInjector.DEFAULT, new Object[0])).checkPermission(Manifest.permission.CHANGE_NETWORK_STATE, Binder.getCallingPid(), Binder.getCallingUid(), "enableDataConnectivity") && telephony != null) {
                return telephony.enableDataConnectivity();
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#enableDataConnectivity", e);
        }
        return false;
    }

    @SystemApi
    public boolean disableDataConnectivity() {
        try {
            ITelephony telephony = getITelephony();
            if (((IOplusPermissionCheckInjector) OplusFeatureCache.getOrCreate(IOplusPermissionCheckInjector.DEFAULT, new Object[0])).checkPermission(Manifest.permission.CHANGE_NETWORK_STATE, Binder.getCallingPid(), Binder.getCallingUid(), "disableDataConnectivity") && telephony != null) {
                return telephony.disableDataConnectivity();
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#disableDataConnectivity", e);
        }
        return false;
    }

    @SystemApi
    public boolean isDataConnectivityPossible() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.isDataConnectivityPossible(getSubId(SubscriptionManager.getActiveDataSubscriptionId()));
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#isDataAllowed", e);
            return false;
        }
    }

    @SystemApi
    public boolean needsOtaServiceProvisioning() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.needsOtaServiceProvisioning();
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#needsOtaServiceProvisioning", e);
            return false;
        }
    }

    public String getMobileProvisioningUrl() {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                return service.getMobileProvisioningUrl();
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException ex) {
            Rlog.e(TAG, "Telephony#getMobileProvisioningUrl RemoteException" + ex);
            return null;
        }
    }

    @Deprecated
    public void setDataEnabled(boolean enable) {
        setDataEnabled(getSubId(SubscriptionManager.getDefaultDataSubscriptionId()), enable);
    }

    @SystemApi
    @Deprecated
    public void setDataEnabled(int subId, boolean enable) {
        try {
            setDataEnabledForReason(subId, 0, enable);
        } catch (RuntimeException e) {
            Log.e(TAG, "Error calling setDataEnabledForReason e:" + e);
        }
    }

    @SystemApi
    @Deprecated
    public boolean getDataEnabled() {
        return isDataEnabled();
    }

    public boolean isDataEnabled() {
        try {
            return isDataEnabledForReason(0);
        } catch (IllegalStateException ise) {
            Log.e(TAG, "Error calling #isDataEnabled, returning default (false).", ise);
            return false;
        }
    }

    public boolean isDataRoamingEnabled() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony == null) {
                return false;
            }
            boolean isDataRoamingEnabled = telephony.isDataRoamingEnabled(getSubId(SubscriptionManager.getDefaultDataSubscriptionId()));
            return isDataRoamingEnabled;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#isDataRoamingEnabled", e);
            return false;
        }
    }

    @SystemApi
    public int getCdmaRoamingMode() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                int mode = telephony.getCdmaRoamingMode(getSubId());
                return mode;
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException ex) {
            Log.e(TAG, "Error calling ITelephony#getCdmaRoamingMode", ex);
            ex.rethrowFromSystemServer();
            return -1;
        }
    }

    @SystemApi
    public void setCdmaRoamingMode(int mode) {
        if (getPhoneType() == 2) {
            try {
                ITelephony telephony = getITelephony();
                if (telephony != null) {
                    boolean result = telephony.setCdmaRoamingMode(getSubId(), mode);
                    if (!result) {
                        throw new IllegalStateException("radio is unavailable.");
                    }
                    return;
                }
                throw new IllegalStateException("telephony service is null.");
            } catch (RemoteException ex) {
                Log.e(TAG, "Error calling ITelephony#setCdmaRoamingMode", ex);
                ex.rethrowFromSystemServer();
            }
        } else {
            throw new IllegalStateException("Phone does not support CDMA.");
        }
    }

    @SystemApi
    public int getCdmaSubscriptionMode() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                int mode = telephony.getCdmaSubscriptionMode(getSubId());
                return mode;
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException ex) {
            Log.e(TAG, "Error calling ITelephony#getCdmaSubscriptionMode", ex);
            ex.rethrowFromSystemServer();
            return 0;
        }
    }

    @SystemApi
    public void setCdmaSubscriptionMode(int mode) {
        if (getPhoneType() == 2) {
            try {
                ITelephony telephony = getITelephony();
                if (telephony != null) {
                    boolean result = telephony.setCdmaSubscriptionMode(getSubId(), mode);
                    if (!result) {
                        throw new IllegalStateException("radio is unavailable.");
                    }
                    return;
                }
                throw new IllegalStateException("telephony service is null.");
            } catch (RemoteException ex) {
                Log.e(TAG, "Error calling ITelephony#setCdmaSubscriptionMode", ex);
                ex.rethrowFromSystemServer();
            }
        } else {
            throw new IllegalStateException("Phone does not support CDMA.");
        }
    }

    @SystemApi
    public void setDataRoamingEnabled(boolean isEnabled) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.setDataRoamingEnabled(getSubId(SubscriptionManager.getDefaultDataSubscriptionId()), isEnabled);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#setDataRoamingEnabled", e);
        }
    }

    @SystemApi
    @Deprecated
    public boolean getDataEnabled(int subId) {
        try {
            return isDataEnabledForReason(subId, 0);
        } catch (RuntimeException e) {
            Log.e(TAG, "Error calling isDataEnabledForReason e:" + e);
            return false;
        }
    }

    @Deprecated
    public int invokeOemRilRequestRaw(byte[] oemReq, byte[] oemResp) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.invokeOemRilRequestRaw(oemReq, oemResp);
            }
            return -1;
        } catch (RemoteException e) {
            return -1;
        } catch (NullPointerException e2) {
            return -1;
        }
    }

    @SystemApi
    @Deprecated
    public void enableVideoCalling(boolean enable) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.enableVideoCalling(enable);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#enableVideoCalling", e);
        }
    }

    @SystemApi
    @Deprecated
    public boolean isVideoCallingEnabled() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.isVideoCallingEnabled(getOpPackageName(), getAttributionTag());
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#isVideoCallingEnabled", e);
            return false;
        }
    }

    public boolean canChangeDtmfToneLength() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.canChangeDtmfToneLength(this.mSubId, getOpPackageName(), getAttributionTag());
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#canChangeDtmfToneLength", e);
            return false;
        } catch (SecurityException e2) {
            Log.e(TAG, "Permission error calling ITelephony#canChangeDtmfToneLength", e2);
            return false;
        }
    }

    public boolean isWorldPhone() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.isWorldPhone(this.mSubId, getOpPackageName(), getAttributionTag());
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#isWorldPhone", e);
            return false;
        } catch (SecurityException e2) {
            Log.e(TAG, "Permission error calling ITelephony#isWorldPhone", e2);
            return false;
        }
    }

    @Deprecated
    public boolean isTtyModeSupported() {
        TelecomManager telecomManager = null;
        try {
            Context context = this.mContext;
            if (context != null) {
                telecomManager = (TelecomManager) context.getSystemService(TelecomManager.class);
            }
            if (telecomManager != null) {
                return telecomManager.isTtySupported();
            }
            return false;
        } catch (SecurityException e) {
            Log.e(TAG, "Permission error calling TelecomManager#isTtySupported", e);
            return false;
        }
    }

    public boolean isRttSupported() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.isRttSupported(this.mSubId);
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#isRttSupported", e);
            return false;
        } catch (SecurityException e2) {
            Log.e(TAG, "Permission error calling ITelephony#isWorldPhone", e2);
            return false;
        }
    }

    public boolean isHearingAidCompatibilitySupported() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.isHearingAidCompatibilitySupported();
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#isHearingAidCompatibilitySupported", e);
            return false;
        } catch (SecurityException e2) {
            Log.e(TAG, "Permission error calling ITelephony#isHearingAidCompatibilitySupported", e2);
            return false;
        }
    }

    public boolean isImsRegistered(int subId) {
        try {
            return getITelephony().isImsRegistered(subId);
        } catch (RemoteException | NullPointerException e) {
            return false;
        }
    }

    public boolean isImsRegistered() {
        try {
            return getITelephony().isImsRegistered(getSubId());
        } catch (RemoteException | NullPointerException e) {
            return false;
        }
    }

    public boolean isVolteAvailable() {
        try {
            return getITelephony().isAvailable(getSubId(), 1, 0);
        } catch (RemoteException | NullPointerException e) {
            return false;
        }
    }

    public boolean isVideoTelephonyAvailable() {
        try {
            return getITelephony().isVideoTelephonyAvailable(getSubId());
        } catch (RemoteException | NullPointerException e) {
            return false;
        }
    }

    public boolean isWifiCallingAvailable() {
        try {
            return getITelephony().isWifiCallingAvailable(getSubId());
        } catch (RemoteException | NullPointerException e) {
            return false;
        }
    }

    public int getImsRegTechnologyForMmTel() {
        try {
            return getITelephony().getImsRegTechnologyForMmTel(getSubId());
        } catch (RemoteException e) {
            return -1;
        }
    }

    public void setSimOperatorNumeric(String numeric) {
        int phoneId = getPhoneId();
        setSimOperatorNumericForPhone(phoneId, numeric);
    }

    public void setSimOperatorNumericForPhone(int phoneId, String numeric) {
        if (SubscriptionManager.isValidPhoneId(phoneId)) {
            List<String> newList = updateTelephonyProperty(TelephonyProperties.icc_operator_numeric(), phoneId, numeric);
            TelephonyProperties.icc_operator_numeric(newList);
        }
    }

    public void setSimOperatorName(String name) {
        int phoneId = getPhoneId();
        setSimOperatorNameForPhone(phoneId, name);
    }

    public void setSimOperatorNameForPhone(int phoneId, String name) {
        if (SubscriptionManager.isValidPhoneId(phoneId)) {
            List<String> newList = updateTelephonyProperty(TelephonyProperties.icc_operator_alpha(), phoneId, name);
            TelephonyProperties.icc_operator_alpha(newList);
        }
    }

    public void setSimCountryIso(String iso) {
        int phoneId = getPhoneId();
        setSimCountryIsoForPhone(phoneId, iso);
    }

    public void setSimCountryIsoForPhone(int phoneId, String iso) {
        if (SubscriptionManager.isValidPhoneId(phoneId)) {
            List<String> newList = updateTelephonyProperty(TelephonyProperties.icc_operator_iso_country(), phoneId, iso);
            TelephonyProperties.icc_operator_iso_country(newList);
        }
    }

    public void setSimState(String state) {
        int phoneId = getPhoneId();
        setSimStateForPhone(phoneId, state);
    }

    public void setSimStateForPhone(int phoneId, String state) {
        if (SubscriptionManager.isValidPhoneId(phoneId)) {
            List<String> newList = updateTelephonyProperty(TelephonyProperties.sim_state(), phoneId, state);
            TelephonyProperties.sim_state(newList);
        }
    }

    @SystemApi
    @Deprecated
    public void setSimPowerState(int state) {
        setSimPowerStateForSlot(getSlotIndex(), state);
    }

    @SystemApi
    @Deprecated
    public void setSimPowerStateForSlot(int slotIndex, int state) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.setSimPowerStateForSlot(slotIndex, state);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#setSimPowerStateForSlot", e);
        } catch (SecurityException e2) {
            Log.e(TAG, "Permission error calling ITelephony#setSimPowerStateForSlot", e2);
        }
    }

    @SystemApi
    public void setSimPowerState(int state, Executor executor, Consumer<Integer> callback) {
        setSimPowerStateForSlot(getSlotIndex(), state, executor, callback);
    }

    @SystemApi
    public void setSimPowerStateForSlot(int slotIndex, int state, final Executor executor, final Consumer<Integer> callback) {
        if (state == 0 || state == 1 || state == 2) {
            try {
                ITelephony telephony = getITelephony();
                if (telephony != null) {
                    IIntegerConsumer internalCallback = new AnonymousClass7(executor, callback);
                    if (telephony != null) {
                        telephony.setSimPowerStateForSlotWithCallback(slotIndex, state, internalCallback);
                    } else if (Compatibility.isChangeEnabled((long) NULL_TELEPHONY_THROW_NO_CB)) {
                        throw new IllegalStateException("Telephony is null");
                    }
                } else {
                    throw new IllegalStateException("Telephony is null.");
                }
            } catch (RemoteException e) {
                Log.e(TAG, "Error calling ITelephony#setSimPowerStateForSlot", e);
                runOnBackgroundThread(new Runnable() { // from class: android.telephony.TelephonyManager$$ExternalSyntheticLambda13
                    @Override // java.lang.Runnable
                    public final void run() {
                        executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$$ExternalSyntheticLambda16
                            @Override // java.lang.Runnable
                            public final void run() {
                                r1.accept(2);
                            }
                        });
                    }
                });
            } catch (SecurityException e2) {
                Log.e(TAG, "Permission error calling ITelephony#setSimPowerStateForSlot", e2);
            }
        } else {
            throw new IllegalArgumentException("requested SIM state is invalid");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: android.telephony.TelephonyManager$7  reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass7 extends IIntegerConsumer.Stub {
        final /* synthetic */ Consumer val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass7(Executor executor, Consumer consumer) {
            this.val$executor = executor;
            this.val$callback = consumer;
        }

        @Override // com.android.internal.telephony.IIntegerConsumer
        public void accept(final int result) {
            Executor executor = this.val$executor;
            final Consumer consumer = this.val$callback;
            executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$7$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.TelephonyManager$7$$ExternalSyntheticLambda0
                        @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                        public final void runOrThrow() {
                            r1.accept(Integer.valueOf(r2));
                        }
                    });
                }
            });
        }
    }

    public void setBasebandVersion(String version) {
        int phoneId = getPhoneId();
        setBasebandVersionForPhone(phoneId, version);
    }

    public void setBasebandVersionForPhone(int phoneId, String version) {
        if (SubscriptionManager.isValidPhoneId(phoneId)) {
            List<String> newList = updateTelephonyProperty(TelephonyProperties.baseband_version(), phoneId, "M_V3_P10");
            TelephonyProperties.baseband_version(newList);
        }
    }

    public String getBasebandVersion() {
        int phoneId = getPhoneId();
        return getBasebandVersionForPhone(phoneId);
    }

    public String getBasebandVersionForPhone(int phoneId) {
        return (String) getTelephonyProperty(phoneId, TelephonyProperties.baseband_version(), "");
    }

    public void setPhoneType(int type) {
        int phoneId = getPhoneId();
        setPhoneType(phoneId, type);
    }

    public void setPhoneType(int phoneId, int type) {
        if (SubscriptionManager.isValidPhoneId(phoneId)) {
            List<Integer> newList = updateTelephonyProperty(TelephonyProperties.current_active_phone(), phoneId, Integer.valueOf(type));
            TelephonyProperties.current_active_phone(newList);
        }
    }

    public String getOtaSpNumberSchema(String defaultValue) {
        int phoneId = getPhoneId();
        return getOtaSpNumberSchemaForPhone(phoneId, defaultValue);
    }

    public String getOtaSpNumberSchemaForPhone(int phoneId, String defaultValue) {
        if (SubscriptionManager.isValidPhoneId(phoneId)) {
            return (String) getTelephonyProperty(phoneId, TelephonyProperties.otasp_num_schema(), defaultValue);
        }
        return defaultValue;
    }

    public boolean getSmsReceiveCapable(boolean defaultValue) {
        int phoneId = getPhoneId();
        return getSmsReceiveCapableForPhone(phoneId, defaultValue);
    }

    public boolean getSmsReceiveCapableForPhone(int phoneId, boolean defaultValue) {
        if (SubscriptionManager.isValidPhoneId(phoneId)) {
            return ((Boolean) getTelephonyProperty(phoneId, TelephonyProperties.sms_receive(), Boolean.valueOf(defaultValue))).booleanValue();
        }
        return defaultValue;
    }

    public boolean getSmsSendCapable(boolean defaultValue) {
        int phoneId = getPhoneId();
        return getSmsSendCapableForPhone(phoneId, defaultValue);
    }

    public boolean getSmsSendCapableForPhone(int phoneId, boolean defaultValue) {
        if (SubscriptionManager.isValidPhoneId(phoneId)) {
            return ((Boolean) getTelephonyProperty(phoneId, TelephonyProperties.sms_send(), Boolean.valueOf(defaultValue))).booleanValue();
        }
        return defaultValue;
    }

    @SystemApi
    public ComponentName getAndUpdateDefaultRespondViaMessageApplication() {
        return SmsApplication.getDefaultRespondViaMessageApplication(this.mContext, true);
    }

    @SystemApi
    public ComponentName getDefaultRespondViaMessageApplication() {
        return SmsApplication.getDefaultRespondViaMessageApplication(this.mContext, false);
    }

    public void setNetworkOperatorName(String name) {
        int phoneId = getPhoneId();
        setNetworkOperatorNameForPhone(phoneId, name);
    }

    public void setNetworkOperatorNameForPhone(int phoneId, String name) {
        if (SubscriptionManager.isValidPhoneId(phoneId)) {
            List<String> newList = updateTelephonyProperty(TelephonyProperties.operator_alpha(), phoneId, name);
            TelephonyProperties.operator_alpha(newList);
        }
    }

    public void setNetworkOperatorNumeric(String numeric) {
        int phoneId = getPhoneId();
        setNetworkOperatorNumericForPhone(phoneId, numeric);
    }

    public void setNetworkOperatorNumericForPhone(int phoneId, String numeric) {
        if (SubscriptionManager.isValidPhoneId(phoneId)) {
            List<String> newList = updateTelephonyProperty(TelephonyProperties.operator_numeric(), phoneId, numeric);
            TelephonyProperties.operator_numeric(newList);
        }
    }

    public void setNetworkRoaming(boolean isRoaming) {
        int phoneId = getPhoneId();
        setNetworkRoamingForPhone(phoneId, isRoaming);
    }

    public void setNetworkRoamingForPhone(int phoneId, boolean isRoaming) {
        if (SubscriptionManager.isValidPhoneId(phoneId)) {
            List<Boolean> newList = updateTelephonyProperty(TelephonyProperties.operator_is_roaming(), phoneId, Boolean.valueOf(isRoaming));
            TelephonyProperties.operator_is_roaming(newList);
        }
    }

    public void setDataNetworkType(int type) {
        int phoneId = getPhoneId(SubscriptionManager.getDefaultDataSubscriptionId());
        setDataNetworkTypeForPhone(phoneId, type);
    }

    public void setDataNetworkTypeForPhone(int phoneId, int type) {
        try {
            if (SubscriptionManager.isValidPhoneId(phoneId)) {
                List<String> newList = updateTelephonyProperty(TelephonyProperties.data_network_type(), phoneId, ServiceState.rilRadioTechnologyToString(type));
                TelephonyProperties.data_network_type(newList);
            }
        } catch (RuntimeException e) {
            Log.e(TAG, "Error updateTelephonyProperty data_network_type.", e);
        }
    }

    public int getSubIdForPhoneAccount(PhoneAccount phoneAccount) {
        try {
            ITelephony service = getITelephony();
            if (service == null) {
                return -1;
            }
            int retval = service.getSubIdForPhoneAccount(phoneAccount);
            return retval;
        } catch (RemoteException e) {
            return -1;
        }
    }

    public PhoneAccountHandle getPhoneAccountHandle() {
        return getPhoneAccountHandleForSubscriptionId(getSubId());
    }

    public PhoneAccountHandle getPhoneAccountHandleForSubscriptionId(int subscriptionId) {
        try {
            ITelephony service = getITelephony();
            if (service == null) {
                return null;
            }
            PhoneAccountHandle returnValue = service.getPhoneAccountHandleForSubscriptionId(subscriptionId);
            return returnValue;
        } catch (RemoteException e) {
            return null;
        }
    }

    public int getSubscriptionId(PhoneAccountHandle phoneAccountHandle) {
        try {
            ITelephony service = getITelephony();
            if (service == null) {
                return -1;
            }
            int retval = service.getSubIdForPhoneAccountHandle(phoneAccountHandle, this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            return retval;
        } catch (RemoteException ex) {
            Log.e(TAG, "getSubscriptionId RemoteException", ex);
            ex.rethrowAsRuntimeException();
            return -1;
        }
    }

    public void factoryReset(int subId) {
        try {
            Log.d(TAG, "factoryReset: subId=" + subId);
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.factoryReset(subId);
            }
        } catch (RemoteException e) {
        }
    }

    @SystemApi
    public void resetSettings() {
        try {
            Log.d(TAG, "resetSettings: subId=" + getSubId());
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.factoryReset(getSubId());
            }
        } catch (RemoteException e) {
        }
    }

    @SystemApi
    public Locale getSimLocale() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony == null) {
                return null;
            }
            String languageTag = telephony.getSimLocaleForSubscriber(getSubId());
            if (!TextUtils.isEmpty(languageTag)) {
                return Locale.forLanguageTag(languageTag);
            }
            return null;
        } catch (RemoteException e) {
            return null;
        }
    }

    public String getLocaleFromDefaultSim() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getSimLocaleForSubscriber(getSubId());
            }
            return null;
        } catch (RemoteException e) {
            return null;
        }
    }

    @SystemApi
    /* loaded from: classes3.dex */
    public static class ModemActivityInfoException extends Exception {
        public static final int ERROR_INVALID_INFO_RECEIVED = 2;
        public static final int ERROR_MODEM_RESPONSE_ERROR = 3;
        public static final int ERROR_PHONE_NOT_AVAILABLE = 1;
        public static final int ERROR_UNKNOWN = 0;
        private final int mErrorCode;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes3.dex */
        public @interface ModemActivityInfoError {
        }

        public ModemActivityInfoException(int errorCode) {
            this.mErrorCode = errorCode;
        }

        public int getErrorCode() {
            return this.mErrorCode;
        }

        @Override // java.lang.Throwable
        public String toString() {
            switch (this.mErrorCode) {
                case 0:
                    return "ERROR_UNKNOWN";
                case 1:
                    return "ERROR_PHONE_NOT_AVAILABLE";
                case 2:
                    return "ERROR_INVALID_INFO_RECEIVED";
                case 3:
                    return "ERROR_MODEM_RESPONSE_ERROR";
                default:
                    return "UNDEFINED";
            }
        }
    }

    @SystemApi
    public void requestModemActivityInfo(Executor executor, final OutcomeReceiver<ModemActivityInfo, ModemActivityInfoException> callback) {
        Objects.requireNonNull(executor);
        Objects.requireNonNull(callback);
        ResultReceiver wrapperResultReceiver = new AnonymousClass8(null, executor, callback);
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                service.requestModemActivityInfo(wrapperResultReceiver);
                return;
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#getModemActivityInfo", e);
        }
        executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                OutcomeReceiver.this.onError(new TelephonyManager.ModemActivityInfoException(1));
            }
        });
    }

    /* renamed from: android.telephony.TelephonyManager$8  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass8 extends ResultReceiver {
        final /* synthetic */ OutcomeReceiver val$callback;
        final /* synthetic */ Executor val$executor;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass8(Handler handler, Executor executor, OutcomeReceiver outcomeReceiver) {
            super(handler);
            this.val$executor = executor;
            this.val$callback = outcomeReceiver;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.ResultReceiver
        public void onReceiveResult(int resultCode, Bundle data) {
            if (data == null) {
                Log.w(TelephonyManager.TAG, "requestModemActivityInfo: received null bundle");
                sendErrorToListener(0);
                return;
            }
            data.setDefusable(true);
            if (data.containsKey(TelephonyManager.EXCEPTION_RESULT_KEY)) {
                int receivedErrorCode = data.getInt(TelephonyManager.EXCEPTION_RESULT_KEY);
                sendErrorToListener(receivedErrorCode);
            } else if (!data.containsKey("controller_activity")) {
                Log.w(TelephonyManager.TAG, "requestModemActivityInfo: Bundle did not contain expected key");
                sendErrorToListener(0);
            } else {
                Parcelable receivedResult = data.getParcelable("controller_activity");
                if (!(receivedResult instanceof ModemActivityInfo)) {
                    Log.w(TelephonyManager.TAG, "requestModemActivityInfo: Bundle contained something that wasn't a ModemActivityInfo.");
                    sendErrorToListener(0);
                    return;
                }
                ModemActivityInfo modemActivityInfo = (ModemActivityInfo) receivedResult;
                if (!modemActivityInfo.isValid()) {
                    Log.w(TelephonyManager.TAG, "requestModemActivityInfo: Received an invalid ModemActivityInfo");
                    sendErrorToListener(2);
                    return;
                }
                Log.d(TelephonyManager.TAG, "requestModemActivityInfo: Sending result to app: " + modemActivityInfo);
                sendResultToListener(modemActivityInfo);
            }
        }

        private void sendResultToListener(final ModemActivityInfo info) {
            final Executor executor = this.val$executor;
            final OutcomeReceiver outcomeReceiver = this.val$callback;
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.TelephonyManager$8$$ExternalSyntheticLambda0
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() {
                    executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$8$$ExternalSyntheticLambda2
                        @Override // java.lang.Runnable
                        public final void run() {
                            OutcomeReceiver.this.onResult(r2);
                        }
                    });
                }
            });
        }

        private void sendErrorToListener(int code) {
            final ModemActivityInfoException e = new ModemActivityInfoException(code);
            final Executor executor = this.val$executor;
            final OutcomeReceiver outcomeReceiver = this.val$callback;
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.TelephonyManager$8$$ExternalSyntheticLambda1
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() {
                    executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$8$$ExternalSyntheticLambda3
                        @Override // java.lang.Runnable
                        public final void run() {
                            OutcomeReceiver.this.onError(r2);
                        }
                    });
                }
            });
        }
    }

    public ServiceState getServiceState() {
        return getServiceStateForSubscriber(getSubId());
    }

    public ServiceState getServiceStateForSubscriber(int subId) {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                return service.getServiceStateForSubscriber(subId, getOpPackageName(), getAttributionTag());
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#getServiceStateForSubscriber", e);
            return null;
        } catch (NullPointerException e2) {
            UUID fromString = UUID.fromString("a3ab0b9d-f2aa-4baf-911d-7096c0d4645a");
            AnomalyReporter.reportAnomaly(fromString, "getServiceStateForSubscriber " + subId + " NPE");
            return null;
        }
    }

    public Uri getVoicemailRingtoneUri(PhoneAccountHandle accountHandle) {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                return service.getVoicemailRingtoneUri(accountHandle);
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#getVoicemailRingtoneUri", e);
            return null;
        }
    }

    public void setVoicemailRingtoneUri(PhoneAccountHandle phoneAccountHandle, Uri uri) {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                service.setVoicemailRingtoneUri(getOpPackageName(), phoneAccountHandle, uri);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#setVoicemailRingtoneUri", e);
        }
    }

    public boolean isVoicemailVibrationEnabled(PhoneAccountHandle accountHandle) {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                return service.isVoicemailVibrationEnabled(accountHandle);
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#isVoicemailVibrationEnabled", e);
            return false;
        }
    }

    public void setVoicemailVibrationEnabled(PhoneAccountHandle phoneAccountHandle, boolean enabled) {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                service.setVoicemailVibrationEnabled(getOpPackageName(), phoneAccountHandle, enabled);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#isVoicemailVibrationEnabled", e);
        }
    }

    public int getSimCarrierId() {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                return service.getSubscriptionCarrierId(getSubId());
            }
            return -1;
        } catch (RemoteException e) {
            return -1;
        }
    }

    public CharSequence getSimCarrierIdName() {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                return service.getSubscriptionCarrierName(getSubId());
            }
            return null;
        } catch (RemoteException e) {
            return null;
        }
    }

    public int getSimSpecificCarrierId() {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                return service.getSubscriptionSpecificCarrierId(getSubId());
            }
            return -1;
        } catch (RemoteException e) {
            return -1;
        }
    }

    public CharSequence getSimSpecificCarrierIdName() {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                return service.getSubscriptionSpecificCarrierName(getSubId());
            }
            return null;
        } catch (RemoteException e) {
            return null;
        }
    }

    public int getCarrierIdFromSimMccMnc() {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                return service.getCarrierIdFromMccMnc(getSlotIndex(), getSimOperator(), true);
            }
            return -1;
        } catch (RemoteException e) {
            return -1;
        }
    }

    public int getCarrierIdFromMccMnc(String mccmnc) {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                return service.getCarrierIdFromMccMnc(getSlotIndex(), mccmnc, false);
            }
            return -1;
        } catch (RemoteException e) {
            return -1;
        }
    }

    public List<String> getCertsFromCarrierPrivilegeAccessRules() {
        List<String> certs = null;
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                certs = service.getCertsFromCarrierPrivilegeAccessRules(getSubId());
            }
        } catch (RemoteException e) {
        }
        return certs == null ? Collections.emptyList() : certs;
    }

    @SystemApi
    public String getAidForAppType(int appType) {
        return getAidForAppType(getSubId(), appType);
    }

    public String getAidForAppType(int subId, int appType) {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                return service.getAidForAppType(subId, appType);
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#getAidForAppType", e);
            return null;
        }
    }

    public String getEsn() {
        return getEsn(getSubId());
    }

    public String getEsn(int subId) {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                return service.getEsn(subId);
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#getEsn", e);
            return null;
        }
    }

    @SystemApi
    public String getCdmaPrlVersion() {
        return getCdmaPrlVersion(getSubId());
    }

    public String getCdmaPrlVersion(int subId) {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                return service.getCdmaPrlVersion(subId);
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#getCdmaPrlVersion", e);
            return null;
        }
    }

    @SystemApi
    public List<TelephonyHistogram> getTelephonyHistograms() {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                return service.getTelephonyHistograms();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#getTelephonyHistograms", e);
            return null;
        }
    }

    @SystemApi
    public int setAllowedCarriers(int slotIndex, List<CarrierIdentifier> carriers) {
        if (carriers == null || !SubscriptionManager.isValidPhoneId(slotIndex)) {
            return -1;
        }
        CarrierRestrictionRules carrierRestrictionRules = CarrierRestrictionRules.newBuilder().setAllowedCarriers(carriers).setDefaultCarrierRestriction(0).build();
        int result = setCarrierRestrictionRules(carrierRestrictionRules);
        if (result == 0) {
            return carriers.size();
        }
        return -1;
    }

    @SystemApi
    public int setCarrierRestrictionRules(CarrierRestrictionRules rules) {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                return service.setAllowedCarriers(rules);
            }
            return 2;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#setAllowedCarriers", e);
            return 2;
        } catch (NullPointerException e2) {
            Log.e(TAG, "Error calling ITelephony#setAllowedCarriers", e2);
            return 2;
        }
    }

    @SystemApi
    @Deprecated
    public List<CarrierIdentifier> getAllowedCarriers(int slotIndex) {
        CarrierRestrictionRules carrierRestrictionRule;
        if (!SubscriptionManager.isValidPhoneId(slotIndex) || (carrierRestrictionRule = getCarrierRestrictionRules()) == null) {
            return new ArrayList(0);
        }
        return carrierRestrictionRule.getAllowedCarriers();
    }

    @SystemApi
    public CarrierRestrictionRules getCarrierRestrictionRules() {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                return service.getAllowedCarriers();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#getAllowedCarriers", e);
            return null;
        } catch (NullPointerException e2) {
            Log.e(TAG, "Error calling ITelephony#getAllowedCarriers", e2);
            return null;
        }
    }

    @SystemApi
    @Deprecated
    public void setCarrierDataEnabled(boolean enabled) {
        try {
            setDataEnabledForReason(2, enabled);
        } catch (RuntimeException e) {
            Log.e(TAG, "Error calling setDataEnabledForReason e:" + e);
        }
    }

    @SystemApi
    public void setRadioEnabled(boolean enabled) {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                service.carrierActionSetRadioEnabled(getSubId(SubscriptionManager.getDefaultDataSubscriptionId()), enabled);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#carrierActionSetRadioEnabled", e);
        }
    }

    @SystemApi
    public void reportDefaultNetworkStatus(boolean report) {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                service.carrierActionReportDefaultNetworkStatus(getSubId(SubscriptionManager.getDefaultDataSubscriptionId()), report);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#carrierActionReportDefaultNetworkStatus", e);
        }
    }

    @SystemApi
    public void resetAllCarrierActions() {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                service.carrierActionResetAll(getSubId(SubscriptionManager.getDefaultDataSubscriptionId()));
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#carrierActionResetAll", e);
        }
    }

    @Deprecated
    public void setPolicyDataEnabled(boolean enabled) {
        try {
            setDataEnabledForReason(1, enabled);
        } catch (RuntimeException e) {
            Log.e(TAG, "Error calling setDataEnabledForReason e:" + e);
        }
    }

    public void setDataEnabledForReason(int reason, boolean enabled) {
        setDataEnabledForReason(getSubId(), reason, enabled);
    }

    private void setDataEnabledForReason(int subId, int reason, boolean enabled) {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                service.setDataEnabledForReason(subId, reason, enabled);
                return;
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException ex) {
            Log.e(TAG, "Telephony#setDataEnabledForReason RemoteException", ex);
            ex.rethrowFromSystemServer();
        }
    }

    public boolean isDataEnabledForReason(int reason) {
        return isDataEnabledForReason(getSubId(), reason);
    }

    private boolean isDataEnabledForReason(int subId, int reason) {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                return service.isDataEnabledForReason(subId, reason);
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException ex) {
            Log.e(TAG, "Telephony#isDataEnabledForReason RemoteException", ex);
            ex.rethrowFromSystemServer();
            return false;
        }
    }

    public List<ClientRequestStats> getClientRequestStats(int subId) {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                return service.getClientRequestStats(getOpPackageName(), getAttributionTag(), subId);
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#getClientRequestStats", e);
            return null;
        }
    }

    @SystemApi
    public boolean getEmergencyCallbackMode() {
        return getEmergencyCallbackMode(getSubId());
    }

    public boolean getEmergencyCallbackMode(int subId) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony == null) {
                return false;
            }
            return telephony.getEmergencyCallbackMode(subId);
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#getEmergencyCallbackMode", e);
            return false;
        }
    }

    public boolean isManualNetworkSelectionAllowed() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.isManualNetworkSelectionAllowed(getSubId());
            }
            return true;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#isManualNetworkSelectionAllowed", e);
            return true;
        }
    }

    public void setAlwaysReportSignalStrength(boolean isEnabled) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.setAlwaysReportSignalStrength(getSubId(), isEnabled);
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "setAlwaysReportSignalStrength RemoteException", ex);
            ex.rethrowAsRuntimeException();
        }
    }

    public SignalStrength getSignalStrength() {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                return service.getSignalStrength(getSubId());
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#getSignalStrength", e);
            return null;
        }
    }

    public boolean isDataConnectionAllowed() {
        try {
            int subId = getSubId(SubscriptionManager.getDefaultDataSubscriptionId());
            ITelephony telephony = getITelephony();
            if (telephony == null) {
                return false;
            }
            boolean retVal = telephony.isDataEnabled(subId);
            return retVal;
        } catch (RemoteException e) {
            Log.e(TAG, "Error isDataConnectionAllowed", e);
            return false;
        }
    }

    public boolean isDataCapable() {
        Context context = this.mContext;
        if (context == null) {
            return true;
        }
        return context.getResources().getBoolean(R.bool.config_mobile_data_capable);
    }

    @Deprecated
    public void setCarrierTestOverride(String mccmnc, String imsi, String iccid, String gid1, String gid2, String plmn, String spn) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.setCarrierTestOverride(getSubId(), mccmnc, imsi, iccid, gid1, gid2, plmn, spn, null, null);
            }
        } catch (RemoteException e) {
        }
    }

    public void setCarrierTestOverride(String mccmnc, String imsi, String iccid, String gid1, String gid2, String plmn, String spn, String carrierPriviledgeRules, String apn) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.setCarrierTestOverride(getSubId(), mccmnc, imsi, iccid, gid1, gid2, plmn, spn, carrierPriviledgeRules, apn);
            }
        } catch (RemoteException e) {
        }
    }

    public int getCarrierIdListVersion() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getCarrierIdListVersion(getSubId());
            }
            return -1;
        } catch (RemoteException e) {
            return -1;
        }
    }

    public int getNumberOfModemsWithSimultaneousDataConnections() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getNumberOfModemsWithSimultaneousDataConnections(getSubId(), getOpPackageName(), getAttributionTag());
            }
            return 0;
        } catch (RemoteException e) {
            return 0;
        }
    }

    @SystemApi
    public boolean setOpportunisticNetworkState(boolean enable) {
        Context context = this.mContext;
        String pkgForDebug = context != null ? context.getOpPackageName() : "<unknown>";
        try {
            IOns iOpportunisticNetworkService = getIOns();
            if (iOpportunisticNetworkService == null) {
                return false;
            }
            boolean ret = iOpportunisticNetworkService.setEnable(enable, pkgForDebug);
            return ret;
        } catch (RemoteException ex) {
            Rlog.e(TAG, "enableOpportunisticNetwork RemoteException", ex);
            return false;
        }
    }

    @SystemApi
    public boolean isOpportunisticNetworkEnabled() {
        Context context = this.mContext;
        String pkgForDebug = context != null ? context.getOpPackageName() : "<unknown>";
        try {
            IOns iOpportunisticNetworkService = getIOns();
            if (iOpportunisticNetworkService == null) {
                return false;
            }
            boolean isEnabled = iOpportunisticNetworkService.isEnabled(pkgForDebug);
            return isEnabled;
        } catch (RemoteException ex) {
            Rlog.e(TAG, "enableOpportunisticNetwork RemoteException", ex);
            return false;
        }
    }

    @SystemApi
    public long getSupportedRadioAccessFamily() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getRadioAccessFamily(getSlotIndex(), getOpPackageName());
            }
            return 0L;
        } catch (RemoteException e) {
            return 0L;
        } catch (NullPointerException e2) {
            return 0L;
        }
    }

    @SystemApi
    public void notifyOtaEmergencyNumberDbInstalled() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.notifyOtaEmergencyNumberDbInstalled();
                return;
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException ex) {
            Log.e(TAG, "notifyOtaEmergencyNumberDatabaseInstalled RemoteException", ex);
            ex.rethrowAsRuntimeException();
        }
    }

    @SystemApi
    public void updateOtaEmergencyNumberDbFilePath(ParcelFileDescriptor otaParcelFileDescriptor) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.updateOtaEmergencyNumberDbFilePath(otaParcelFileDescriptor);
                return;
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException ex) {
            Log.e(TAG, "updateOtaEmergencyNumberDbFilePath RemoteException", ex);
            ex.rethrowAsRuntimeException();
        }
    }

    @SystemApi
    public void resetOtaEmergencyNumberDbFilePath() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.resetOtaEmergencyNumberDbFilePath();
                return;
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException ex) {
            Log.e(TAG, "resetOtaEmergencyNumberDbFilePath RemoteException", ex);
            ex.rethrowAsRuntimeException();
        }
    }

    @SystemApi
    public boolean isEmergencyAssistanceEnabled() {
        this.mContext.enforceCallingOrSelfPermission(Manifest.permission.READ_PRIVILEGED_PHONE_STATE, "isEmergencyAssistanceEnabled");
        return true;
    }

    public Map<Integer, List<EmergencyNumber>> getEmergencyNumberList() {
        Map<Integer, List<EmergencyNumber>> emergencyNumberList = new HashMap<>();
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getEmergencyNumberList(this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException ex) {
            Log.e(TAG, "getEmergencyNumberList RemoteException", ex);
            ex.rethrowAsRuntimeException();
            return emergencyNumberList;
        }
    }

    public Map<Integer, List<EmergencyNumber>> getEmergencyNumberList(int categories) {
        Map<Integer, List<EmergencyNumber>> emergencyNumberListForCategories = new HashMap<>();
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                Map<Integer, List<EmergencyNumber>> emergencyNumberList = telephony.getEmergencyNumberList(this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
                Map<Integer, List<EmergencyNumber>> emergencyNumberListForCategories2 = filterEmergencyNumbersByCategories(emergencyNumberList, categories);
                return emergencyNumberListForCategories2;
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException ex) {
            Log.e(TAG, "getEmergencyNumberList with Categories RemoteException", ex);
            ex.rethrowAsRuntimeException();
            return emergencyNumberListForCategories;
        }
    }

    public Map<Integer, List<EmergencyNumber>> filterEmergencyNumbersByCategories(Map<Integer, List<EmergencyNumber>> emergencyNumberList, int categories) {
        Map<Integer, List<EmergencyNumber>> emergencyNumberListForCategories = new HashMap<>();
        if (emergencyNumberList != null) {
            for (Integer subscriptionId : emergencyNumberList.keySet()) {
                List<EmergencyNumber> allNumbersForSub = emergencyNumberList.get(subscriptionId);
                List<EmergencyNumber> numbersForCategoriesPerSub = new ArrayList<>();
                for (EmergencyNumber number : allNumbersForSub) {
                    if (number.isInEmergencyServiceCategories(categories)) {
                        numbersForCategoriesPerSub.add(number);
                    }
                }
                emergencyNumberListForCategories.put(subscriptionId, numbersForCategoriesPerSub);
            }
        }
        return emergencyNumberListForCategories;
    }

    public boolean isEmergencyNumber(String number) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.isEmergencyNumber(number, true);
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException ex) {
            Log.e(TAG, "isEmergencyNumber RemoteException", ex);
            ex.rethrowAsRuntimeException();
            return false;
        }
    }

    @SystemApi
    public boolean isPotentialEmergencyNumber(String number) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.isEmergencyNumber(number, false);
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException ex) {
            Log.e(TAG, "isEmergencyNumber RemoteException", ex);
            ex.rethrowAsRuntimeException();
            return false;
        }
    }

    @SystemApi
    public int getEmergencyNumberDbVersion() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getEmergencyNumberDbVersion(getSubId());
            }
            return -1;
        } catch (RemoteException ex) {
            Log.e(TAG, "getEmergencyNumberDbVersion RemoteException", ex);
            ex.rethrowAsRuntimeException();
            return -1;
        }
    }

    public void setPreferredOpportunisticDataSubscription(int subId, boolean needValidation, final Executor executor, final Consumer<Integer> callback) {
        Context context = this.mContext;
        String pkgForDebug = context != null ? context.getOpPackageName() : "<unknown>";
        try {
            IOns iOpportunisticNetworkService = getIOns();
            if (iOpportunisticNetworkService != null) {
                ISetOpportunisticDataCallback callbackStub = new AnonymousClass9(executor, callback);
                iOpportunisticNetworkService.setPreferredDataSubscriptionId(subId, needValidation, callbackStub, pkgForDebug);
            } else if (Compatibility.isChangeEnabled((long) NULL_TELEPHONY_THROW_NO_CB)) {
                throw new IllegalStateException("Opportunistic Network Service is null");
            } else {
                throw new RemoteException("Null Opportunistic Network Service!");
            }
        } catch (RemoteException ex) {
            Rlog.e(TAG, "setPreferredOpportunisticDataSubscription RemoteException", ex);
            if (executor != null && callback != null) {
                runOnBackgroundThread(new Runnable() { // from class: android.telephony.TelephonyManager$$ExternalSyntheticLambda12
                    @Override // java.lang.Runnable
                    public final void run() {
                        executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$$ExternalSyntheticLambda15
                            @Override // java.lang.Runnable
                            public final void run() {
                                TelephonyManager.lambda$setPreferredOpportunisticDataSubscription$14(r1);
                            }
                        });
                    }
                });
            }
        }
    }

    /* renamed from: android.telephony.TelephonyManager$9  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass9 extends ISetOpportunisticDataCallback.Stub {
        final /* synthetic */ Consumer val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass9(Executor executor, Consumer consumer) {
            this.val$executor = executor;
            this.val$callback = consumer;
        }

        @Override // com.android.internal.telephony.ISetOpportunisticDataCallback
        public void onComplete(final int result) {
            if (this.val$executor != null && this.val$callback != null) {
                long identity = Binder.clearCallingIdentity();
                try {
                    Executor executor = this.val$executor;
                    final Consumer consumer = this.val$callback;
                    executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$9$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            consumer.accept(Integer.valueOf(result));
                        }
                    });
                } finally {
                    Binder.restoreCallingIdentity(identity);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setPreferredOpportunisticDataSubscription$14(Consumer callback) {
        if (Compatibility.isChangeEnabled((long) CALLBACK_ON_MORE_ERROR_CODE_CHANGE)) {
            callback.accept(4);
        } else {
            callback.accept(2);
        }
    }

    public int getPreferredOpportunisticDataSubscription() {
        Context context = this.mContext;
        String packageName = context != null ? context.getOpPackageName() : "<unknown>";
        Context context2 = this.mContext;
        String attributionTag = context2 != null ? context2.getAttributionTag() : null;
        try {
            IOns iOpportunisticNetworkService = getIOns();
            if (iOpportunisticNetworkService == null) {
                return -1;
            }
            int subId = iOpportunisticNetworkService.getPreferredDataSubscriptionId(packageName, attributionTag);
            return subId;
        } catch (RemoteException ex) {
            Rlog.e(TAG, "getPreferredDataSubscriptionId RemoteException", ex);
            return -1;
        }
    }

    public void updateAvailableNetworks(List<AvailableNetworkInfo> availableNetworks, final Executor executor, final Consumer<Integer> callback) {
        Context context = this.mContext;
        String pkgForDebug = context != null ? context.getOpPackageName() : "<unknown>";
        Objects.requireNonNull(availableNetworks, "availableNetworks must not be null.");
        try {
            IOns iOpportunisticNetworkService = getIOns();
            if (iOpportunisticNetworkService != null) {
                IUpdateAvailableNetworksCallback callbackStub = new AnonymousClass10(executor, callback);
                iOpportunisticNetworkService.updateAvailableNetworks(availableNetworks, callbackStub, pkgForDebug);
            } else if (Compatibility.isChangeEnabled((long) NULL_TELEPHONY_THROW_NO_CB)) {
                throw new IllegalStateException("Opportunistic Network Service is null");
            } else {
                throw new RemoteException("Null Opportunistic Network Service!");
            }
        } catch (RemoteException ex) {
            Rlog.e(TAG, "updateAvailableNetworks RemoteException", ex);
            if (executor != null && callback != null) {
                runOnBackgroundThread(new Runnable() { // from class: android.telephony.TelephonyManager$$ExternalSyntheticLambda14
                    @Override // java.lang.Runnable
                    public final void run() {
                        executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$$ExternalSyntheticLambda17
                            @Override // java.lang.Runnable
                            public final void run() {
                                TelephonyManager.lambda$updateAvailableNetworks$16(r1);
                            }
                        });
                    }
                });
            }
        }
    }

    /* renamed from: android.telephony.TelephonyManager$10  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass10 extends IUpdateAvailableNetworksCallback.Stub {
        final /* synthetic */ Consumer val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass10(Executor executor, Consumer consumer) {
            this.val$executor = executor;
            this.val$callback = consumer;
        }

        @Override // com.android.internal.telephony.IUpdateAvailableNetworksCallback
        public void onComplete(final int result) {
            final Consumer consumer;
            final Executor executor = this.val$executor;
            if (executor != null && (consumer = this.val$callback) != null) {
                Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.TelephonyManager$10$$ExternalSyntheticLambda0
                    @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                    public final void runOrThrow() {
                        executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$10$$ExternalSyntheticLambda1
                            @Override // java.lang.Runnable
                            public final void run() {
                                r1.accept(Integer.valueOf(r2));
                            }
                        });
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$updateAvailableNetworks$16(Consumer callback) {
        if (Compatibility.isChangeEnabled((long) CALLBACK_ON_MORE_ERROR_CODE_CHANGE)) {
            callback.accept(9);
        } else {
            callback.accept(1);
        }
    }

    @SystemApi
    public boolean enableModemForSlot(int slotIndex, boolean enable) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony == null) {
                return false;
            }
            boolean ret = telephony.enableModemForSlot(slotIndex, enable);
            return ret;
        } catch (RemoteException ex) {
            Log.e(TAG, "enableModem RemoteException", ex);
            return false;
        }
    }

    public boolean isModemEnabledForSlot(int slotIndex) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.isModemEnabledForSlot(slotIndex, this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            }
            return false;
        } catch (RemoteException ex) {
            Log.e(TAG, "enableModem RemoteException", ex);
            return false;
        }
    }

    @SystemApi
    public void setMultiSimCarrierRestriction(boolean isMultiSimCarrierRestricted) {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                service.setMultiSimCarrierRestriction(isMultiSimCarrierRestricted);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "setMultiSimCarrierRestriction RemoteException", e);
        }
    }

    public int isMultiSimSupported() {
        if (getSupportedModemCount() < 2) {
            return 1;
        }
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                return service.isMultiSimSupported(getOpPackageName(), getAttributionTag());
            }
        } catch (RemoteException e) {
            Log.e(TAG, "isMultiSimSupported RemoteException", e);
        }
        return 1;
    }

    public void switchMultiSimConfig(int numOfSims) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.switchMultiSimConfig(numOfSims);
            }
        } catch (RemoteException ex) {
            Rlog.e(TAG, "switchMultiSimConfig RemoteException", ex);
        }
    }

    public boolean doesSwitchMultiSimConfigTriggerReboot() {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                return service.doesSwitchMultiSimConfigTriggerReboot(getSubId(), getOpPackageName(), getAttributionTag());
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "doesSwitchMultiSimConfigTriggerReboot RemoteException", e);
            return false;
        }
    }

    public Pair<Integer, Integer> getRadioHalVersion() {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                int version = service.getRadioHalVersion();
                if (version == -1) {
                    return new Pair<>(-1, -1);
                }
                return new Pair<>(Integer.valueOf(version / 100), Integer.valueOf(version % 100));
            }
        } catch (RemoteException e) {
            Log.e(TAG, "getRadioHalVersion() RemoteException", e);
        }
        return new Pair<>(-1, -1);
    }

    @SystemApi
    public int getCarrierPrivilegeStatus(int uid) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getCarrierPrivilegeStatusForUid(getSubId(), uid);
            }
            return 0;
        } catch (RemoteException ex) {
            Log.e(TAG, "getCarrierPrivilegeStatus RemoteException", ex);
            return 0;
        }
    }

    public List<ApnSetting> getDevicePolicyOverrideApns(Context context) {
        Cursor cursor = context.getContentResolver().query(Telephony.Carriers.DPC_URI, null, null, null, null);
        try {
            if (cursor == null) {
                List<ApnSetting> emptyList = Collections.emptyList();
                if (cursor != null) {
                    cursor.close();
                }
                return emptyList;
            }
            List<ApnSetting> apnList = new ArrayList<>();
            cursor.moveToPosition(-1);
            while (cursor.moveToNext()) {
                ApnSetting apn = ApnSetting.makeApnSetting(cursor);
                apnList.add(apn);
            }
            if (cursor != null) {
                cursor.close();
            }
            return apnList;
        } catch (Throwable th) {
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public int addDevicePolicyOverrideApn(Context context, ApnSetting apnSetting) {
        Uri resultUri = context.getContentResolver().insert(Telephony.Carriers.DPC_URI, apnSetting.toContentValues());
        if (resultUri == null) {
            return -1;
        }
        try {
            int resultId = Integer.parseInt(resultUri.getLastPathSegment());
            return resultId;
        } catch (NumberFormatException e) {
            Rlog.e(TAG, "Failed to parse inserted override APN id: " + resultUri.getLastPathSegment());
            return -1;
        }
    }

    public boolean modifyDevicePolicyOverrideApn(Context context, int apnId, ApnSetting apnSetting) {
        return context.getContentResolver().update(Uri.withAppendedPath(Telephony.Carriers.DPC_URI, Integer.toString(apnId)), apnSetting.toContentValues(), null, null) > 0;
    }

    @SystemApi
    public boolean isDataEnabledForApn(int apnType) {
        Context context = this.mContext;
        String pkgForDebug = context != null ? context.getOpPackageName() : "<unknown>";
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                return service.isDataEnabledForApn(apnType, getSubId(), pkgForDebug);
            }
            return false;
        } catch (RemoteException ex) {
            Rlog.e(TAG, "Telephony#isDataEnabledForApn RemoteException" + ex);
            return false;
        }
    }

    @SystemApi
    public boolean isApnMetered(int apnType) {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                return service.isApnMetered(apnType, getSubId());
            }
            return true;
        } catch (RemoteException ex) {
            Rlog.e(TAG, "Telephony#isApnMetered RemoteException" + ex);
            return true;
        }
    }

    @SystemApi
    public void setSystemSelectionChannels(List<RadioAccessSpecifier> specifiers, Executor executor, Consumer<Boolean> callback) {
        Objects.requireNonNull(specifiers, "Specifiers must not be null.");
        Objects.requireNonNull(executor, "Executor must not be null.");
        Objects.requireNonNull(callback, "Callback must not be null.");
        setSystemSelectionChannelsInternal(specifiers, executor, callback);
    }

    @SystemApi
    public void setSystemSelectionChannels(List<RadioAccessSpecifier> specifiers) {
        Objects.requireNonNull(specifiers, "Specifiers must not be null.");
        setSystemSelectionChannelsInternal(specifiers, null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: android.telephony.TelephonyManager$11  reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass11 extends IBooleanConsumer.Stub {
        final /* synthetic */ Consumer val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass11(Executor executor, Consumer consumer) {
            this.val$executor = executor;
            this.val$callback = consumer;
        }

        @Override // com.android.internal.telephony.IBooleanConsumer
        public void accept(final boolean result) {
            long identity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final Consumer consumer = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$11$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        consumer.accept(Boolean.valueOf(result));
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(identity);
            }
        }
    }

    private void setSystemSelectionChannelsInternal(List<RadioAccessSpecifier> specifiers, Executor executor, Consumer<Boolean> callback) {
        IBooleanConsumer aidlConsumer = callback == null ? null : new AnonymousClass11(executor, callback);
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                service.setSystemSelectionChannels(specifiers, getSubId(), aidlConsumer);
            }
        } catch (RemoteException ex) {
            Rlog.e(TAG, "Telephony#setSystemSelectionChannels RemoteException" + ex);
        }
    }

    @SystemApi
    public List<RadioAccessSpecifier> getSystemSelectionChannels() {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                return service.getSystemSelectionChannels(getSubId());
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException ex) {
            Rlog.e(TAG, "Telephony#getSystemSelectionChannels RemoteException" + ex);
            return new ArrayList();
        }
    }

    @SystemApi
    public boolean matchesCurrentSimOperator(String mccmnc, int mvnoType, String mvnoMatchData) {
        ITelephony service;
        try {
            if (mccmnc.equals(getSimOperator()) && (service = getITelephony()) != null) {
                return service.isMvnoMatched(getSubId(), mvnoType, mvnoMatchData);
            }
        } catch (RemoteException ex) {
            Rlog.e(TAG, "Telephony#matchesCurrentSimOperator RemoteException" + ex);
        }
        return false;
    }

    @SystemApi
    public void getCallForwarding(int callForwardingReason, Executor executor, CallForwardingInfoCallback callback) {
        if (callForwardingReason < 0 || callForwardingReason > 5) {
            throw new IllegalArgumentException("callForwardingReason is out of range");
        }
        ICallForwardingInfoCallback internalCallback = new AnonymousClass12(executor, callback);
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.getCallForwarding(getSubId(), callForwardingReason, internalCallback);
            }
        } catch (RemoteException ex) {
            Rlog.e(TAG, "getCallForwarding RemoteException", ex);
            ex.rethrowAsRuntimeException();
        }
    }

    /* renamed from: android.telephony.TelephonyManager$12  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass12 extends ICallForwardingInfoCallback.Stub {
        final /* synthetic */ CallForwardingInfoCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass12(Executor executor, CallForwardingInfoCallback callForwardingInfoCallback) {
            this.val$executor = executor;
            this.val$callback = callForwardingInfoCallback;
        }

        @Override // com.android.internal.telephony.ICallForwardingInfoCallback
        public void onCallForwardingInfoAvailable(final CallForwardingInfo info) {
            Executor executor = this.val$executor;
            final CallForwardingInfoCallback callForwardingInfoCallback = this.val$callback;
            executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$12$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.TelephonyManager$12$$ExternalSyntheticLambda1
                        @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                        public final void runOrThrow() {
                            TelephonyManager.CallForwardingInfoCallback.this.onCallForwardingInfoAvailable(r2);
                        }
                    });
                }
            });
        }

        @Override // com.android.internal.telephony.ICallForwardingInfoCallback
        public void onError(final int error) {
            Executor executor = this.val$executor;
            final CallForwardingInfoCallback callForwardingInfoCallback = this.val$callback;
            executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$12$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.TelephonyManager$12$$ExternalSyntheticLambda0
                        @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                        public final void runOrThrow() {
                            TelephonyManager.CallForwardingInfoCallback.this.onError(r2);
                        }
                    });
                }
            });
        }
    }

    @SystemApi
    public void setCallForwarding(CallForwardingInfo callForwardingInfo, Executor executor, Consumer<Integer> resultListener) {
        if (callForwardingInfo != null) {
            int callForwardingReason = callForwardingInfo.getReason();
            if (callForwardingReason < 0 || callForwardingReason > 5) {
                throw new IllegalArgumentException("callForwardingReason is out of range");
            }
            if (callForwardingInfo.isEnabled()) {
                if (callForwardingInfo.getNumber() == null) {
                    throw new IllegalArgumentException("callForwarding number is null");
                } else if (callForwardingInfo.getTimeoutSeconds() <= 0) {
                    throw new IllegalArgumentException("callForwarding timeout isn't positive");
                }
            }
            if (resultListener != null) {
                Objects.requireNonNull(executor);
            }
            IIntegerConsumer internalCallback = new AnonymousClass13(executor, resultListener);
            try {
                ITelephony telephony = getITelephony();
                if (telephony != null) {
                    telephony.setCallForwarding(getSubId(), callForwardingInfo, internalCallback);
                }
            } catch (RemoteException ex) {
                Rlog.e(TAG, "setCallForwarding RemoteException", ex);
                ex.rethrowAsRuntimeException();
            } catch (NullPointerException ex2) {
                Rlog.e(TAG, "setCallForwarding NPE", ex2);
                throw ex2;
            }
        } else {
            throw new IllegalArgumentException("callForwardingInfo is null");
        }
    }

    /* renamed from: android.telephony.TelephonyManager$13  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass13 extends IIntegerConsumer.Stub {
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ Consumer val$resultListener;

        AnonymousClass13(Executor executor, Consumer consumer) {
            this.val$executor = executor;
            this.val$resultListener = consumer;
        }

        @Override // com.android.internal.telephony.IIntegerConsumer
        public void accept(final int result) {
            Executor executor = this.val$executor;
            final Consumer consumer = this.val$resultListener;
            executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$13$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.TelephonyManager$13$$ExternalSyntheticLambda0
                        @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                        public final void runOrThrow() {
                            r1.accept(Integer.valueOf(r2));
                        }
                    });
                }
            });
        }
    }

    @SystemApi
    public void getCallWaitingStatus(Executor executor, Consumer<Integer> resultListener) {
        Objects.requireNonNull(executor);
        Objects.requireNonNull(resultListener);
        IIntegerConsumer internalCallback = new AnonymousClass14(executor, resultListener);
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.getCallWaitingStatus(getSubId(), internalCallback);
            }
        } catch (RemoteException ex) {
            Rlog.e(TAG, "getCallWaitingStatus RemoteException", ex);
            ex.rethrowAsRuntimeException();
        } catch (NullPointerException ex2) {
            Rlog.e(TAG, "getCallWaitingStatus NPE", ex2);
            throw ex2;
        }
    }

    /* renamed from: android.telephony.TelephonyManager$14  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass14 extends IIntegerConsumer.Stub {
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ Consumer val$resultListener;

        AnonymousClass14(Executor executor, Consumer consumer) {
            this.val$executor = executor;
            this.val$resultListener = consumer;
        }

        @Override // com.android.internal.telephony.IIntegerConsumer
        public void accept(final int result) {
            Executor executor = this.val$executor;
            final Consumer consumer = this.val$resultListener;
            executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$14$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.TelephonyManager$14$$ExternalSyntheticLambda0
                        @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                        public final void runOrThrow() {
                            r1.accept(Integer.valueOf(r2));
                        }
                    });
                }
            });
        }
    }

    @SystemApi
    public void setCallWaitingEnabled(boolean enabled, Executor executor, Consumer<Integer> resultListener) {
        if (resultListener != null) {
            Objects.requireNonNull(executor);
        }
        IIntegerConsumer internalCallback = new AnonymousClass15(executor, resultListener);
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.setCallWaitingStatus(getSubId(), enabled, internalCallback);
            }
        } catch (RemoteException ex) {
            Rlog.e(TAG, "setCallWaitingStatus RemoteException", ex);
            ex.rethrowAsRuntimeException();
        } catch (NullPointerException ex2) {
            Rlog.e(TAG, "setCallWaitingStatus NPE", ex2);
            throw ex2;
        }
    }

    /* renamed from: android.telephony.TelephonyManager$15  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass15 extends IIntegerConsumer.Stub {
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ Consumer val$resultListener;

        AnonymousClass15(Executor executor, Consumer consumer) {
            this.val$executor = executor;
            this.val$resultListener = consumer;
        }

        @Override // com.android.internal.telephony.IIntegerConsumer
        public void accept(final int result) {
            Executor executor = this.val$executor;
            final Consumer consumer = this.val$resultListener;
            executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$15$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.telephony.TelephonyManager$15$$ExternalSyntheticLambda0
                        @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                        public final void runOrThrow() {
                            r1.accept(Integer.valueOf(r2));
                        }
                    });
                }
            });
        }
    }

    @SystemApi
    public void setMobileDataPolicyEnabled(int policy, boolean enabled) {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                service.setMobileDataPolicyEnabled(getSubId(), policy, enabled);
            }
        } catch (RemoteException ex) {
            Rlog.e(TAG, "Telephony#setMobileDataPolicyEnabled RemoteException" + ex);
        }
    }

    @SystemApi
    public boolean isMobileDataPolicyEnabled(int policy) {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                return service.isMobileDataPolicyEnabled(getSubId(), policy);
            }
            return false;
        } catch (RemoteException ex) {
            Rlog.e(TAG, "Telephony#isMobileDataPolicyEnabled RemoteException" + ex);
            return false;
        }
    }

    @SystemApi
    public boolean isIccLockEnabled() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.isIccLockEnabled(getSubId());
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException e) {
            Log.e(TAG, "isIccLockEnabled RemoteException", e);
            e.rethrowFromSystemServer();
            return false;
        }
    }

    @SystemApi
    public PinResult setIccLockEnabled(boolean enabled, String pin) {
        Preconditions.checkNotNull(pin, "setIccLockEnabled pin can't be null.");
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                int result = telephony.setIccLockEnabled(getSubId(), enabled, pin);
                if (result == Integer.MAX_VALUE) {
                    return new PinResult(0, 0);
                }
                if (result < 0) {
                    return PinResult.getDefaultFailedResult();
                }
                return new PinResult(1, result);
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException e) {
            Log.e(TAG, "setIccLockEnabled RemoteException", e);
            e.rethrowFromSystemServer();
            return PinResult.getDefaultFailedResult();
        }
    }

    @SystemApi
    public PinResult changeIccLockPin(String oldPin, String newPin) {
        Preconditions.checkNotNull(oldPin, "changeIccLockPin oldPin can't be null.");
        Preconditions.checkNotNull(newPin, "changeIccLockPin newPin can't be null.");
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                int result = telephony.changeIccLockPassword(getSubId(), oldPin, newPin);
                if (result == Integer.MAX_VALUE) {
                    return new PinResult(0, 0);
                }
                if (result < 0) {
                    return PinResult.getDefaultFailedResult();
                }
                return new PinResult(1, result);
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException e) {
            Log.e(TAG, "changeIccLockPin RemoteException", e);
            e.rethrowFromSystemServer();
            return PinResult.getDefaultFailedResult();
        }
    }

    public void notifyUserActivity() {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                service.userActivity();
            }
        } catch (RemoteException e) {
            Log.w(TAG, "notifyUserActivity exception: " + e.getMessage());
        }
    }

    @SystemApi
    public int setNrDualConnectivityState(int nrDualConnectivityState) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.setNrDualConnectivityState(getSubId(), nrDualConnectivityState);
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException ex) {
            Rlog.e(TAG, "setNrDualConnectivityState RemoteException", ex);
            ex.rethrowFromSystemServer();
            return 4;
        }
    }

    @SystemApi
    public boolean isNrDualConnectivityEnabled() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.isNrDualConnectivityEnabled(getSubId());
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException ex) {
            Rlog.e(TAG, "isNRDualConnectivityEnabled RemoteException", ex);
            ex.rethrowFromSystemServer();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class DeathRecipient implements IBinder.DeathRecipient {
        private DeathRecipient() {
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            TelephonyManager.resetServiceCache();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void resetServiceCache() {
        synchronized (sCacheLock) {
            ITelephony iTelephony = sITelephony;
            if (iTelephony != null) {
                iTelephony.asBinder().unlinkToDeath(sServiceDeath, 0);
                sITelephony = null;
            }
            ISub iSub = sISub;
            if (iSub != null) {
                iSub.asBinder().unlinkToDeath(sServiceDeath, 0);
                sISub = null;
                SubscriptionManager.clearCaches();
            }
            ISms iSms = sISms;
            if (iSms != null) {
                iSms.asBinder().unlinkToDeath(sServiceDeath, 0);
                sISms = null;
            }
            IPhoneSubInfo iPhoneSubInfo = sIPhoneSubInfo;
            if (iPhoneSubInfo != null) {
                iPhoneSubInfo.asBinder().unlinkToDeath(sServiceDeath, 0);
                sIPhoneSubInfo = null;
            }
        }
    }

    static IPhoneSubInfo getSubscriberInfoService() {
        if (!sServiceHandleCacheEnabled) {
            return IPhoneSubInfo.Stub.asInterface(TelephonyFrameworkInitializer.getTelephonyServiceManager().getPhoneSubServiceRegisterer().get());
        }
        if (sIPhoneSubInfo == null) {
            IPhoneSubInfo temp = IPhoneSubInfo.Stub.asInterface(TelephonyFrameworkInitializer.getTelephonyServiceManager().getPhoneSubServiceRegisterer().get());
            synchronized (sCacheLock) {
                if (sIPhoneSubInfo == null && temp != null) {
                    try {
                        sIPhoneSubInfo = temp;
                        temp.asBinder().linkToDeath(sServiceDeath, 0);
                    } catch (Exception e) {
                        sIPhoneSubInfo = null;
                    }
                }
            }
        }
        return sIPhoneSubInfo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ISub getSubscriptionService() {
        if (!sServiceHandleCacheEnabled) {
            return ISub.Stub.asInterface(TelephonyFrameworkInitializer.getTelephonyServiceManager().getSubscriptionServiceRegisterer().get());
        }
        if (sISub == null) {
            ISub temp = ISub.Stub.asInterface(TelephonyFrameworkInitializer.getTelephonyServiceManager().getSubscriptionServiceRegisterer().get());
            synchronized (sCacheLock) {
                if (sISub == null && temp != null) {
                    try {
                        sISub = temp;
                        temp.asBinder().linkToDeath(sServiceDeath, 0);
                    } catch (Exception e) {
                        sISub = null;
                    }
                }
            }
        }
        return sISub;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ISms getSmsService() {
        if (!sServiceHandleCacheEnabled) {
            return ISms.Stub.asInterface(TelephonyFrameworkInitializer.getTelephonyServiceManager().getSmsServiceRegisterer().get());
        }
        if (sISms == null) {
            ISms temp = ISms.Stub.asInterface(TelephonyFrameworkInitializer.getTelephonyServiceManager().getSmsServiceRegisterer().get());
            synchronized (sCacheLock) {
                if (sISms == null && temp != null) {
                    try {
                        sISms = temp;
                        temp.asBinder().linkToDeath(sServiceDeath, 0);
                    } catch (Exception e) {
                        sISms = null;
                    }
                }
            }
        }
        return sISms;
    }

    public static void disableServiceHandleCaching() {
        sServiceHandleCacheEnabled = false;
    }

    public static void enableServiceHandleCaching() {
        sServiceHandleCacheEnabled = true;
    }

    public static void setupITelephonyForTest(ITelephony telephony) {
        sITelephony = telephony;
    }

    public boolean canConnectTo5GInDsdsMode() {
        ITelephony telephony = getITelephony();
        if (telephony == null) {
            return true;
        }
        try {
            return telephony.canConnectTo5GInDsdsMode();
        } catch (RemoteException e) {
            return true;
        } catch (NullPointerException e2) {
            return true;
        }
    }

    public List<String> getEquivalentHomePlmns() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getEquivalentHomePlmns(getSubId(), this.mContext.getOpPackageName(), getAttributionTag());
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException ex) {
            Rlog.e(TAG, "Telephony#getEquivalentHomePlmns RemoteException" + ex);
            return Collections.emptyList();
        }
    }

    public boolean isRadioInterfaceCapabilitySupported(String capability) {
        if (capability == null) {
            return false;
        }
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.isRadioInterfaceCapabilitySupported(capability);
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException ex) {
            Rlog.e(TAG, "Telephony#isRadioInterfaceCapabilitySupported RemoteException" + ex);
            return false;
        }
    }

    @SystemApi
    public int sendThermalMitigationRequest(ThermalMitigationRequest thermalMitigationRequest) {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.sendThermalMitigationRequest(getSubId(), thermalMitigationRequest, getOpPackageName());
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException ex) {
            Log.e(TAG, "Telephony#thermalMitigationRequest RemoteException", ex);
            ex.rethrowFromSystemServer();
            return 4;
        }
    }

    public void registerTelephonyCallback(Executor executor, TelephonyCallback callback) {
        Context context = this.mContext;
        if (context == null) {
            throw new IllegalStateException("telephony service is null.");
        } else if (executor == null || callback == null) {
            throw new IllegalArgumentException("TelephonyCallback and executor must be non-null");
        } else {
            TelephonyRegistryManager telephonyRegistryManager = (TelephonyRegistryManager) context.getSystemService(Context.TELEPHONY_REGISTRY_SERVICE);
            this.mTelephonyRegistryMgr = telephonyRegistryManager;
            if (telephonyRegistryManager != null) {
                telephonyRegistryManager.registerTelephonyCallback(executor, this.mSubId, getOpPackageName(), getAttributionTag(), callback, getITelephony() != null);
                return;
            }
            throw new IllegalStateException("telephony service is null.");
        }
    }

    public void unregisterTelephonyCallback(TelephonyCallback callback) {
        if (this.mContext == null) {
            throw new IllegalStateException("telephony service is null.");
        } else if (callback.callback != null) {
            TelephonyRegistryManager telephonyRegistryManager = (TelephonyRegistryManager) this.mContext.getSystemService(TelephonyRegistryManager.class);
            this.mTelephonyRegistryMgr = telephonyRegistryManager;
            if (telephonyRegistryManager != null) {
                telephonyRegistryManager.unregisterTelephonyCallback(this.mSubId, getOpPackageName(), getAttributionTag(), callback, getITelephony() != null);
                return;
            }
            throw new IllegalStateException("telephony service is null.");
        }
    }

    @SystemApi
    /* loaded from: classes3.dex */
    public static class BootstrapAuthenticationCallback {
        public void onKeysAvailable(byte[] gbaKey, String transactionId) {
        }

        public void onAuthenticationFailure(int reason) {
        }
    }

    @SystemApi
    public void bootstrapAuthenticationRequest(int appType, Uri nafId, UaSecurityProtocolIdentifier securityProtocol, boolean forceBootStrapping, Executor e, final BootstrapAuthenticationCallback callback) {
        try {
            ITelephony service = getITelephony();
            if (service == null) {
                e.execute(new Runnable() { // from class: android.telephony.TelephonyManager$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        TelephonyManager.BootstrapAuthenticationCallback.this.onAuthenticationFailure(2);
                    }
                });
            } else {
                service.bootstrapAuthenticationRequest(getSubId(), appType, nafId, securityProtocol, forceBootStrapping, new AnonymousClass16(e, callback));
            }
        } catch (RemoteException exception) {
            Log.e(TAG, "Error calling ITelephony#bootstrapAuthenticationRequest", exception);
            e.execute(new Runnable() { // from class: android.telephony.TelephonyManager$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    TelephonyManager.BootstrapAuthenticationCallback.this.onAuthenticationFailure(2);
                }
            });
        }
    }

    /* renamed from: android.telephony.TelephonyManager$16  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass16 extends IBootstrapAuthenticationCallback.Stub {
        final /* synthetic */ BootstrapAuthenticationCallback val$callback;
        final /* synthetic */ Executor val$e;

        AnonymousClass16(Executor executor, BootstrapAuthenticationCallback bootstrapAuthenticationCallback) {
            this.val$e = executor;
            this.val$callback = bootstrapAuthenticationCallback;
        }

        @Override // android.telephony.IBootstrapAuthenticationCallback
        public void onKeysAvailable(int token, final byte[] gbaKey, final String transactionId) {
            long identity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$e;
                final BootstrapAuthenticationCallback bootstrapAuthenticationCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$16$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        TelephonyManager.BootstrapAuthenticationCallback.this.onKeysAvailable(gbaKey, transactionId);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(identity);
            }
        }

        @Override // android.telephony.IBootstrapAuthenticationCallback
        public void onAuthenticationFailure(int token, final int reason) {
            long identity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$e;
                final BootstrapAuthenticationCallback bootstrapAuthenticationCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$16$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        TelephonyManager.BootstrapAuthenticationCallback.this.onAuthenticationFailure(reason);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(identity);
            }
        }
    }

    public static boolean isNetworkTypeValid(int networkType) {
        return networkType >= 0 && networkType <= 20;
    }

    public void setSignalStrengthUpdateRequest(SignalStrengthUpdateRequest request) {
        Objects.requireNonNull(request, "request must not be null");
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                service.setSignalStrengthUpdateRequest(getSubId(), request, getOpPackageName());
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#setSignalStrengthUpdateRequest", e);
        }
    }

    public void clearSignalStrengthUpdateRequest(SignalStrengthUpdateRequest request) {
        Objects.requireNonNull(request, "request must not be null");
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                service.clearSignalStrengthUpdateRequest(getSubId(), request, getOpPackageName());
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#clearSignalStrengthUpdateRequest", e);
        }
    }

    @SystemApi
    public PhoneCapability getPhoneCapability() {
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                return telephony.getPhoneCapability();
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException ex) {
            ex.rethrowAsRuntimeException();
            if (getActiveModemCount() > 1) {
                return PhoneCapability.DEFAULT_DSDS_CAPABILITY;
            }
            return PhoneCapability.DEFAULT_SSSS_CAPABILITY;
        }
    }

    @SystemApi
    public int prepareForUnattendedReboot() {
        try {
            ITelephony service = getITelephony();
            if (service != null) {
                return service.prepareForUnattendedReboot();
            }
            return 2;
        } catch (RemoteException e) {
            Log.e(TAG, "Telephony#prepareForUnattendedReboot RemoteException", e);
            e.rethrowFromSystemServer();
            return 2;
        }
    }

    /* loaded from: classes3.dex */
    public static class NetworkSlicingException extends Exception {
        public static final int ERROR_MODEM_ERROR = 2;
        public static final int ERROR_TIMEOUT = 1;
        public static final int SUCCESS = 0;
        private final int mErrorCode;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes3.dex */
        public @interface NetworkSlicingError {
        }

        public NetworkSlicingException(int errorCode) {
            this.mErrorCode = errorCode;
        }

        @Override // java.lang.Throwable
        public String toString() {
            switch (this.mErrorCode) {
                case 1:
                    return "ERROR_TIMEOUT";
                case 2:
                    return "ERROR_MODEM_ERROR";
                default:
                    return "UNDEFINED";
            }
        }
    }

    /* loaded from: classes3.dex */
    public class TimeoutException extends NetworkSlicingException {
        public TimeoutException(int errorCode) {
            super(errorCode);
        }
    }

    /* loaded from: classes3.dex */
    public class ModemErrorException extends NetworkSlicingException {
        public ModemErrorException(int errorCode) {
            super(errorCode);
        }
    }

    public void getNetworkSlicingConfiguration(Executor executor, OutcomeReceiver<NetworkSlicingConfig, NetworkSlicingException> callback) {
        Objects.requireNonNull(executor);
        Objects.requireNonNull(callback);
        try {
            ITelephony telephony = getITelephony();
            if (telephony != null) {
                telephony.getSlicingConfig(new AnonymousClass17(null, executor, callback));
                return;
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException ex) {
            ex.rethrowAsRuntimeException();
        }
    }

    /* renamed from: android.telephony.TelephonyManager$17  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass17 extends ResultReceiver {
        final /* synthetic */ OutcomeReceiver val$callback;
        final /* synthetic */ Executor val$executor;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass17(Handler handler, Executor executor, OutcomeReceiver outcomeReceiver) {
            super(handler);
            this.val$executor = executor;
            this.val$callback = outcomeReceiver;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.ResultReceiver
        public void onReceiveResult(final int resultCode, Bundle result) {
            if (resultCode == 1) {
                Executor executor = this.val$executor;
                final OutcomeReceiver outcomeReceiver = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.TelephonyManager$17$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        TelephonyManager.AnonymousClass17.this.lambda$onReceiveResult$0$TelephonyManager$17(outcomeReceiver, resultCode);
                    }
                });
            } else if (resultCode == 2) {
                Executor executor2 = this.val$executor;
                final OutcomeReceiver outcomeReceiver2 = this.val$callback;
                executor2.execute(new Runnable() { // from class: android.telephony.TelephonyManager$17$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        TelephonyManager.AnonymousClass17.this.lambda$onReceiveResult$1$TelephonyManager$17(outcomeReceiver2, resultCode);
                    }
                });
            } else {
                final NetworkSlicingConfig slicingConfig = (NetworkSlicingConfig) result.getParcelable(TelephonyManager.KEY_SLICING_CONFIG_HANDLE);
                Executor executor3 = this.val$executor;
                final OutcomeReceiver outcomeReceiver3 = this.val$callback;
                executor3.execute(new Runnable() { // from class: android.telephony.TelephonyManager$17$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        OutcomeReceiver.this.onResult(slicingConfig);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onReceiveResult$0$TelephonyManager$17(OutcomeReceiver callback, int resultCode) {
            callback.onError(new TimeoutException(resultCode));
        }

        public /* synthetic */ void lambda$onReceiveResult$1$TelephonyManager$17(OutcomeReceiver callback, int resultCode) {
            callback.onError(new ModemErrorException(resultCode));
        }
    }
}
