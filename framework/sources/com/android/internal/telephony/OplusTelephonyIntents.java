package com.android.internal.telephony;
/* loaded from: classes4.dex */
public interface OplusTelephonyIntents {
    public static final String ACTION_COMMAND_FORCE_DISABLE_ENDC = "android.intent.force_disable_endc";
    public static final String ACTION_COMMAND_SMART5G_IF_TEST_ENDC_SET = "oplus.intent.action.smart5g_if_test_endc_set";
    public static final String ACTION_COMMAND_SMART5G_IF_TEST_NR_PRIO = "oplus.intent.action.smart5g_if_test_nr_prio";
    public static final String ACTION_DATA_ABNORMAL_DETECTED_INFORM = "android.intent.data_abnormal_detected";
    public static final String ACTION_DEEPTHINKER_STARTUP = "oplus.intent.action.DEEPTHINKER_EVENTFOUNTAIN_STARTUP";
    public static final String ACTION_DMYK_SIM_STATE_CHANGED = "com.dmyk.android.telephony.action.SIM_STATE_CHANGED";
    public static final String ACTION_ELEV_TEST_TRIGGER = "oplus.intent.action.ELEV_TEST_TRIGGER";
    public static final String ACTION_ENTER_GAME_SPACE_AOL = "oplus.intent.action.ENTER_GAME_SPACE_AOL";
    public static final String ACTION_ENTER_GAME_SPACE_OPTIMIZE = "oplus.intent.action.ENTER_GAME_SPACE_OPTIMIZE";
    public static final String ACTION_EXIT_GAME_SPACE_AOL = "oplus.intent.action.EXIT_GAME_SPACE_AOL";
    public static final String ACTION_EXIT_GAME_SPACE_OPTIMIZE = "oplus.intent.action.EXIT_GAME_SPACE_OPTIMIZE";
    public static final String ACTION_GET_RF_FEATURE_DONE = "oplus.intent.action.GET_RF_FEATURE_DONE";
    public static final String ACTION_HOTSWAP_STATE_CHANGE = "oplus.intent.action.SIM_HOTSWAP_STATE_CHANGE";
    public static final String ACTION_IMS_CALL_DROP_WITH_Q850 = "oplus.intent.action.IMS_CALL_DROP_WITH_Q850";
    public static final String ACTION_INTENT_CARRIERCONFIG_IS_FINISHED = "oplus.intent.acton.CarrierConfigIsFinishedInDcTracker";
    public static final String ACTION_LIMIT_STATE_CHANGED = "oplus.intent.action.NW_RATE_LIMIT_STATE";
    public static final String ACTION_MCC_CHANGE = "oplus.intent.action.MCC_CHANGE";
    public static final String ACTION_OPLUS_FENCE_TEST_SET = "oplus.intent.action.fence_test_set";
    public static final String ACTION_OPLUS_OTA_UPDATE_SUCCESSED_OLD = "oplus.intent.action.OPLUS_OTA_UPDATE_SUCCESSED";
    public static final String ACTION_OPLUS_RECOVER_UPDATE_SUCCESSED_OLD = "oplus.intent.action.OPLUS_RECOVER_UPDATE_SUCCESSED";
    public static final String ACTION_PBM_STATE_READY = "android.intent.action.PBM_STATE_READY";
    public static final String ACTION_PCO_STATE_CHANGED = "oplus.intent.action.PCO_STATE_CHANGED";
    public static final String ACTION_SUBINFO_STATE_CHANGE = "oplus.intent.action.SUBINFO_STATE_CHANGE";
    public static final String ACTION_THERMAL_THROTTLING = "oplus.intent.action.THERMAL_THROTTLING_5G";
    public static final String EXTRA_PCO_SLOT_ID = "pcoSlotId";
    public static final String EXTRA_PCO_STATE = "pcoState";
    public static final String EXTRA_RADIO_BUG_TYPE = "radioBugType";
    public static final String EXTRA_SIM_PHONEID = "com.dmyk.android.telephony.extra.SIM_PHONEID";
    public static final String EXTRA_SIM_STATE = "com.dmyk.android.telephony.extra.SIM_STATE";
    public static final String EXTRA_SLOT_ID = "slotId";
    public static final String INTENT_KEY_CARD_TYPE = "typeValue";
    public static final String INTENT_KEY_SIM_STATE = "simstate";
    public static final String INTENT_KEY_SLOT_ID = "slotid";
    public static final String INTENT_KEY_SUB_ID = "subid";
    public static final String INTENT_VALUE_SIM_PLUG_IN = "PLUGIN";
    public static final String INTENT_VALUE_SIM_PLUG_OUT = "PLUGOUT";
    public static final String INTENT_VALUE_SIM_STATE = "CARDTYPE";
    public static final String MDPWR_FORCE_UPDATE_ACTIVITYINFO_WAKEUP_ALARM = "oplus.intent.action.MDPWR_FORCE_UPDATE_ACTIVITYINFO_WAKEUP_ALARM";
    public static final String MDPWR_FORCE_UPDATE_ALL_DATA = "oplus.intent.action.MDPWR_FORCE_UPDATE_ALL_DATA";
    public static final String MDPWR_LOOP_SETTLE_WAKEUP_ALARM = "oplus.intent.action.MDPWR_LOOP_SETTLE_WAKEUP_ALARM";
    public static final String MDPWR_LOOP_UPDATE_ACTIVITYINFO_ALARM = "oplus.intent.action.MDPWR_LOOP_UPDATE_ACTIVITYINFO_ALARM";
    public static final String MDPWR_REPORT_MODEM_ACTIVITY = "oplus.intent.action.MDPWR_REPORT_MODEM_ACTIVITY";
    public static final String OPLUS_ACTION_MODEM_UEVENT = "oplus.intent.action.MODEM_UEVENT_ACTION";
    public static final String OPLUS_ACTION_REPORT_NEC = "oplus.intent.action.TELEPHONY_REPORT_NEC";
    public static final String OPLUS_COMPONENT_SAFE_PERMISSION = "oplus.permission.OPLUS_COMPONENT_SAFE";
    public static final String OPLUS_DEEPTHINKER_SAFE_PERMISSION = "com.oplus.permission.safe.AI_APP";
    public static final String OPLUS_SAFE_SECURITY_PERMISSION = "com.oplus.permission.safe.SECURITY";
}
