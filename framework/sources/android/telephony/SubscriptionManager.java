package android.telephony;

import android.annotation.SystemApi;
import android.app.PendingIntent;
import android.app.PropertyInvalidatedCache;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.NetworkPolicyManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelUuid;
import android.os.Process;
import android.os.RemoteException;
import android.provider.Telephony;
import android.telephony.SubscriptionManager;
import android.telephony.euicc.EuiccManager;
import android.util.Base64;
import android.util.Log;
import android.util.Pair;
import com.android.internal.telephony.ISetOpportunisticDataCallback;
import com.android.internal.telephony.ISub;
import com.android.internal.telephony.util.HandlerExecutor;
import com.android.internal.util.FunctionalUtils;
import com.android.internal.util.Preconditions;
import com.android.telephony.Rlog;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
/* loaded from: classes3.dex */
public class SubscriptionManager {
    public static final String ACCESS_RULES = "access_rules";
    public static final String ACCESS_RULES_FROM_CARRIER_CONFIGS = "access_rules_from_carrier_configs";
    public static final String ACTION_DEFAULT_SMS_SUBSCRIPTION_CHANGED = "android.telephony.action.DEFAULT_SMS_SUBSCRIPTION_CHANGED";
    public static final String ACTION_DEFAULT_SUBSCRIPTION_CHANGED = "android.telephony.action.DEFAULT_SUBSCRIPTION_CHANGED";
    public static final String ACTION_MANAGE_SUBSCRIPTION_PLANS = "android.telephony.action.MANAGE_SUBSCRIPTION_PLANS";
    public static final String ACTION_REFRESH_SUBSCRIPTION_PLANS = "android.telephony.action.REFRESH_SUBSCRIPTION_PLANS";
    @SystemApi
    public static final String ACTION_SUBSCRIPTION_PLANS_CHANGED = "android.telephony.action.SUBSCRIPTION_PLANS_CHANGED";
    @SystemApi
    public static final Uri ADVANCED_CALLING_ENABLED_CONTENT_URI;
    public static final String ALLOWED_NETWORK_TYPES = "allowed_network_types_for_reasons";
    public static final String CACHE_KEY_ACTIVE_DATA_SUB_ID_PROPERTY = "cache_key.telephony.get_active_data_sub_id";
    public static final String CACHE_KEY_DEFAULT_DATA_SUB_ID_PROPERTY = "cache_key.telephony.get_default_data_sub_id";
    public static final String CACHE_KEY_DEFAULT_SMS_SUB_ID_PROPERTY = "cache_key.telephony.get_default_sms_sub_id";
    public static final String CACHE_KEY_DEFAULT_SUB_ID_PROPERTY = "cache_key.telephony.get_default_sub_id";
    public static final String CACHE_KEY_SLOT_INDEX_PROPERTY = "cache_key.telephony.get_slot_index";
    public static final String CARD_ID = "card_id";
    public static final String CARRIER_ID = "carrier_id";
    public static final String CARRIER_NAME = "carrier_name";
    public static final String CB_ALERT_REMINDER_INTERVAL = "alert_reminder_interval";
    public static final String CB_ALERT_SOUND_DURATION = "alert_sound_duration";
    public static final String CB_ALERT_SPEECH = "enable_alert_speech";
    public static final String CB_ALERT_VIBRATE = "enable_alert_vibrate";
    public static final String CB_AMBER_ALERT = "enable_cmas_amber_alerts";
    public static final String CB_CHANNEL_50_ALERT = "enable_channel_50_alerts";
    public static final String CB_CMAS_TEST_ALERT = "enable_cmas_test_alerts";
    public static final String CB_EMERGENCY_ALERT = "enable_emergency_alerts";
    public static final String CB_ETWS_TEST_ALERT = "enable_etws_test_alerts";
    public static final String CB_EXTREME_THREAT_ALERT = "enable_cmas_extreme_threat_alerts";
    public static final String CB_OPT_OUT_DIALOG = "show_cmas_opt_out_dialog";
    public static final String CB_SEVERE_THREAT_ALERT = "enable_cmas_severe_threat_alerts";
    public static final Uri CONTENT_URI;
    public static final String CROSS_SIM_CALLING_ENABLED = "cross_sim_calling_enabled";
    @SystemApi
    public static final Uri CROSS_SIM_ENABLED_CONTENT_URI;
    public static final int D2D_SHARING_ALL = 3;
    public static final int D2D_SHARING_ALL_CONTACTS = 1;
    public static final int D2D_SHARING_DISABLED = 0;
    public static final int D2D_SHARING_SELECTED_CONTACTS = 2;
    public static final String D2D_STATUS_SHARING = "d2d_sharing_status";
    public static final String D2D_STATUS_SHARING_SELECTED_CONTACTS = "d2d_sharing_contacts";
    public static final String DATA_ENABLED_OVERRIDE_RULES = "data_enabled_override_rules";
    public static final String DATA_ROAMING = "data_roaming";
    public static final int DATA_ROAMING_DISABLE = 0;
    public static final int DATA_ROAMING_ENABLE = 1;
    private static final boolean DBG = false;
    public static final int DEFAULT_NAME_RES = 17039374;
    public static final int DEFAULT_PHONE_INDEX = Integer.MAX_VALUE;
    public static final int DEFAULT_SIM_SLOT_INDEX = Integer.MAX_VALUE;
    public static final int DEFAULT_SUBSCRIPTION_ID = Integer.MAX_VALUE;
    public static final String DISPLAY_NAME = "display_name";
    public static final String EHPLMNS = "ehplmns";
    public static final String ENHANCED_4G_MODE_ENABLED = "volte_vt_enabled";
    public static final String EXTRA_SLOT_INDEX = "android.telephony.extra.SLOT_INDEX";
    public static final String EXTRA_SUBSCRIPTION_INDEX = "android.telephony.extra.SUBSCRIPTION_INDEX";
    public static final String GET_SIM_SPECIFIC_SETTINGS_METHOD_NAME = "getSimSpecificSettings";
    public static final String GROUP_OWNER = "group_owner";
    public static final String GROUP_UUID = "group_uuid";
    public static final String HPLMNS = "hplmns";
    public static final String HUE = "color";
    public static final String ICC_ID = "icc_id";
    public static final String IMSI = "imsi";
    public static final String IMS_RCS_UCE_ENABLED = "ims_rcs_uce_enabled";
    public static final int INVALID_PHONE_INDEX = -1;
    public static final int INVALID_SIM_SLOT_INDEX = -1;
    public static final int INVALID_SUBSCRIPTION_ID = -1;
    public static final String ISO_COUNTRY_CODE = "iso_country_code";
    private static final boolean IS_DEBUG_BUILD;
    public static final String IS_EMBEDDED = "is_embedded";
    public static final String IS_OPPORTUNISTIC = "is_opportunistic";
    public static final String IS_REMOVABLE = "is_removable";
    public static final String KEY_SIM_SPECIFIC_SETTINGS_DATA = "KEY_SIM_SPECIFIC_SETTINGS_DATA";
    private static final String LOG_TAG = "SubscriptionManager";
    private static final int MAX_CACHE_SIZE = 4;
    public static final int MAX_SUBSCRIPTION_ID_VALUE = 2147483646;
    public static final String MCC = "mcc";
    public static final String MCC_STRING = "mcc_string";
    public static final int MIN_SUBSCRIPTION_ID_VALUE = 0;
    public static final String MNC = "mnc";
    public static final String MNC_STRING = "mnc_string";
    public static final String NAME_SOURCE = "name_source";
    public static final int NAME_SOURCE_CARRIER = 3;
    public static final int NAME_SOURCE_CARRIER_ID = 0;
    public static final int NAME_SOURCE_SIM_PNN = 4;
    public static final int NAME_SOURCE_SIM_SPN = 1;
    public static final int NAME_SOURCE_USER_INPUT = 2;
    public static final String NUMBER = "number";
    public static final int PLACEHOLDER_SUBSCRIPTION_ID_BASE = -2;
    public static final String PROFILE_CLASS = "profile_class";
    @SystemApi
    @Deprecated
    public static final int PROFILE_CLASS_DEFAULT = -1;
    @SystemApi
    public static final int PROFILE_CLASS_OPERATIONAL = 2;
    @SystemApi
    public static final int PROFILE_CLASS_PROVISIONING = 1;
    @SystemApi
    public static final int PROFILE_CLASS_TESTING = 0;
    @SystemApi
    public static final int PROFILE_CLASS_UNSET = -1;
    public static final String RESTORE_SIM_SPECIFIC_SETTINGS_METHOD_NAME = "restoreSimSpecificSettings";
    public static final Uri SIM_INFO_BACKUP_AND_RESTORE_CONTENT_URI;
    public static final Uri SIM_INFO_SUW_RESTORE_CONTENT_URI;
    public static final int SIM_NOT_INSERTED = -1;
    public static final String SIM_SLOT_INDEX = "sim_id";
    public static final int SLOT_INDEX_FOR_REMOTE_SIM_SUB = -1;
    public static final String SUBSCRIPTION_TYPE = "subscription_type";
    public static final int SUBSCRIPTION_TYPE_LOCAL_SIM = 0;
    public static final int SUBSCRIPTION_TYPE_REMOTE_SIM = 1;
    public static final String SUB_DEFAULT_CHANGED_ACTION = "android.intent.action.SUB_DEFAULT_CHANGED";
    public static final String UICC_APPLICATIONS_ENABLED = "uicc_applications_enabled";
    public static final String UNIQUE_KEY_SUBSCRIPTION_ID = "_id";
    private static final boolean VDBG = false;
    public static final String VOIMS_OPT_IN_STATUS = "voims_opt_in_status";
    @SystemApi
    public static final Uri VT_ENABLED_CONTENT_URI;
    public static final String VT_IMS_ENABLED = "vt_ims_enabled";
    @SystemApi
    public static final Uri WFC_ENABLED_CONTENT_URI;
    public static final String WFC_IMS_ENABLED = "wfc_ims_enabled";
    public static final String WFC_IMS_MODE = "wfc_ims_mode";
    public static final String WFC_IMS_ROAMING_ENABLED = "wfc_ims_roaming_enabled";
    public static final String WFC_IMS_ROAMING_MODE = "wfc_ims_roaming_mode";
    @SystemApi
    public static final Uri WFC_MODE_CONTENT_URI;
    @SystemApi
    public static final Uri WFC_ROAMING_ENABLED_CONTENT_URI;
    @SystemApi
    public static final Uri WFC_ROAMING_MODE_CONTENT_URI;
    private static VoidPropertyInvalidatedCache<Integer> sActiveDataSubIdCache;
    private static VoidPropertyInvalidatedCache<Integer> sDefaultDataSubIdCache;
    private static VoidPropertyInvalidatedCache<Integer> sDefaultSmsSubIdCache;
    private static VoidPropertyInvalidatedCache<Integer> sDefaultSubIdCache;
    private static IntegerPropertyInvalidatedCache<Integer> sPhoneIdCache;
    private static final Map<Pair<Context, Integer>, Resources> sResourcesCache;
    private static IntegerPropertyInvalidatedCache<Integer> sSlotIndexCache;
    private final Context mContext;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public interface CallISubMethodHelper {
        int callMethod(ISub iSub) throws RemoteException;
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface DeviceToDeviceStatusSharingPreference {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface ProfileClass {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface SimDisplayNameSource {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface SubscriptionType {
    }

    static {
        IS_DEBUG_BUILD = Build.TYPE.equals("eng") || Build.TYPE.equals("userdebug");
        Uri uri = Telephony.SimInfo.CONTENT_URI;
        CONTENT_URI = uri;
        sDefaultSubIdCache = new VoidPropertyInvalidatedCache<>(SubscriptionManager$$ExternalSyntheticLambda12.INSTANCE, CACHE_KEY_DEFAULT_SUB_ID_PROPERTY, -1);
        sDefaultDataSubIdCache = new VoidPropertyInvalidatedCache<>(SubscriptionManager$$ExternalSyntheticLambda10.INSTANCE, CACHE_KEY_DEFAULT_DATA_SUB_ID_PROPERTY, -1);
        sDefaultSmsSubIdCache = new VoidPropertyInvalidatedCache<>(SubscriptionManager$$ExternalSyntheticLambda11.INSTANCE, CACHE_KEY_DEFAULT_SMS_SUB_ID_PROPERTY, -1);
        sActiveDataSubIdCache = new VoidPropertyInvalidatedCache<>(SubscriptionManager$$ExternalSyntheticLambda9.INSTANCE, CACHE_KEY_ACTIVE_DATA_SUB_ID_PROPERTY, -1);
        sSlotIndexCache = new IntegerPropertyInvalidatedCache<>(SubscriptionManager$$ExternalSyntheticLambda8.INSTANCE, CACHE_KEY_SLOT_INDEX_PROPERTY, -1);
        sPhoneIdCache = new IntegerPropertyInvalidatedCache<>(SubscriptionManager$$ExternalSyntheticLambda7.INSTANCE, CACHE_KEY_DEFAULT_SUB_ID_PROPERTY, -1);
        WFC_ENABLED_CONTENT_URI = Uri.withAppendedPath(uri, "wfc");
        ADVANCED_CALLING_ENABLED_CONTENT_URI = Uri.withAppendedPath(uri, "advanced_calling");
        WFC_MODE_CONTENT_URI = Uri.withAppendedPath(uri, "wfc_mode");
        WFC_ROAMING_MODE_CONTENT_URI = Uri.withAppendedPath(uri, "wfc_roaming_mode");
        VT_ENABLED_CONTENT_URI = Uri.withAppendedPath(uri, "vt_enabled");
        WFC_ROAMING_ENABLED_CONTENT_URI = Uri.withAppendedPath(uri, "wfc_roaming_enabled");
        Uri withAppendedPath = Uri.withAppendedPath(uri, "backup_and_restore");
        SIM_INFO_BACKUP_AND_RESTORE_CONTENT_URI = withAppendedPath;
        SIM_INFO_SUW_RESTORE_CONTENT_URI = Uri.withAppendedPath(withAppendedPath, "suw_restore");
        CROSS_SIM_ENABLED_CONTENT_URI = Uri.withAppendedPath(uri, "cross_sim_calling_enabled");
        sResourcesCache = new ConcurrentHashMap();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class VoidPropertyInvalidatedCache<T> extends PropertyInvalidatedCache<Void, T> {
        private final String mCacheKeyProperty;
        private final T mDefaultValue;
        private final FunctionalUtils.ThrowingFunction<ISub, T> mInterfaceMethod;

        VoidPropertyInvalidatedCache(FunctionalUtils.ThrowingFunction<ISub, T> subscriptionInterfaceMethod, String cacheKeyProperty, T defaultValue) {
            super(4, cacheKeyProperty);
            this.mInterfaceMethod = subscriptionInterfaceMethod;
            this.mCacheKeyProperty = cacheKeyProperty;
            this.mDefaultValue = defaultValue;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public T recompute(Void aVoid) {
            T result = this.mDefaultValue;
            try {
                ISub iSub = TelephonyManager.getSubscriptionService();
                if (iSub != null) {
                    return this.mInterfaceMethod.applyOrThrow(iSub);
                }
                return result;
            } catch (Exception e) {
                Rlog.w(SubscriptionManager.LOG_TAG, "Failed to recompute cache key for " + this.mCacheKeyProperty);
                return result;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class IntegerPropertyInvalidatedCache<T> extends PropertyInvalidatedCache<Integer, T> {
        private final String mCacheKeyProperty;
        private final T mDefaultValue;
        private final FunctionalUtils.ThrowingBiFunction<ISub, Integer, T> mInterfaceMethod;

        IntegerPropertyInvalidatedCache(FunctionalUtils.ThrowingBiFunction<ISub, Integer, T> subscriptionInterfaceMethod, String cacheKeyProperty, T defaultValue) {
            super(4, cacheKeyProperty);
            this.mInterfaceMethod = subscriptionInterfaceMethod;
            this.mCacheKeyProperty = cacheKeyProperty;
            this.mDefaultValue = defaultValue;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public T recompute(Integer query) {
            T result = this.mDefaultValue;
            try {
                ISub iSub = TelephonyManager.getSubscriptionService();
                if (iSub != null) {
                    return this.mInterfaceMethod.applyOrThrow(iSub, query);
                }
                return result;
            } catch (Exception e) {
                Rlog.w(SubscriptionManager.LOG_TAG, "Failed to recompute cache key for " + this.mCacheKeyProperty);
                return result;
            }
        }
    }

    public static Uri getUriForSubscriptionId(int subscriptionId) {
        return Uri.withAppendedPath(CONTENT_URI, String.valueOf(subscriptionId));
    }

    /* loaded from: classes3.dex */
    public static class OnSubscriptionsChangedListener {
        private final HandlerExecutor mExecutor;

        /* loaded from: classes3.dex */
        private class OnSubscriptionsChangedListenerHandler extends Handler {
            OnSubscriptionsChangedListenerHandler() {
            }

            OnSubscriptionsChangedListenerHandler(Looper looper) {
                super(looper);
            }
        }

        public HandlerExecutor getHandlerExecutor() {
            return this.mExecutor;
        }

        public OnSubscriptionsChangedListener() {
            this.mExecutor = new HandlerExecutor(new OnSubscriptionsChangedListenerHandler());
        }

        public OnSubscriptionsChangedListener(Looper looper) {
            this.mExecutor = new HandlerExecutor(new OnSubscriptionsChangedListenerHandler(looper));
        }

        public void onSubscriptionsChanged() {
        }

        public void onAddListenerFailed() {
            Rlog.w(SubscriptionManager.LOG_TAG, "onAddListenerFailed not overridden");
        }

        private void log(String s) {
            Rlog.d(SubscriptionManager.LOG_TAG, s);
        }
    }

    public SubscriptionManager(Context context) {
        this.mContext = context;
    }

    private NetworkPolicyManager getNetworkPolicyManager() {
        return (NetworkPolicyManager) this.mContext.getSystemService(Context.NETWORK_POLICY_SERVICE);
    }

    @Deprecated
    public static SubscriptionManager from(Context context) {
        return (SubscriptionManager) context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);
    }

    @Deprecated
    public void addOnSubscriptionsChangedListener(OnSubscriptionsChangedListener listener) {
        if (listener != null) {
            addOnSubscriptionsChangedListener(listener.mExecutor, listener);
        }
    }

    public void addOnSubscriptionsChangedListener(Executor executor, final OnSubscriptionsChangedListener listener) {
        Context context = this.mContext;
        String pkgName = context != null ? context.getOpPackageName() : "<unknown>";
        if (IS_DEBUG_BUILD) {
            logd("register OnSubscriptionsChangedListener pkgName=" + pkgName + " listener=" + listener);
        }
        TelephonyRegistryManager telephonyRegistryManager = (TelephonyRegistryManager) this.mContext.getSystemService(Context.TELEPHONY_REGISTRY_SERVICE);
        if (telephonyRegistryManager != null) {
            telephonyRegistryManager.addOnSubscriptionsChangedListener(listener, executor);
            return;
        }
        loge("addOnSubscriptionsChangedListener: pkgname=" + pkgName + " failed to be added  due to TELEPHONY_REGISTRY_SERVICE being unavailable.");
        executor.execute(new Runnable() { // from class: android.telephony.SubscriptionManager$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                SubscriptionManager.OnSubscriptionsChangedListener.this.onAddListenerFailed();
            }
        });
    }

    public void removeOnSubscriptionsChangedListener(OnSubscriptionsChangedListener listener) {
        if (listener != null) {
            Context context = this.mContext;
            if (context != null) {
                context.getOpPackageName();
            }
            TelephonyRegistryManager telephonyRegistryManager = (TelephonyRegistryManager) this.mContext.getSystemService(Context.TELEPHONY_REGISTRY_SERVICE);
            if (telephonyRegistryManager != null) {
                telephonyRegistryManager.removeOnSubscriptionsChangedListener(listener);
            }
        }
    }

    /* loaded from: classes3.dex */
    public static class OnOpportunisticSubscriptionsChangedListener {
        public void onOpportunisticSubscriptionsChanged() {
        }

        private void log(String s) {
            Rlog.d(SubscriptionManager.LOG_TAG, s);
        }
    }

    public void addOnOpportunisticSubscriptionsChangedListener(Executor executor, OnOpportunisticSubscriptionsChangedListener listener) {
        if (executor != null && listener != null) {
            Context context = this.mContext;
            if (context != null) {
                context.getOpPackageName();
            }
            TelephonyRegistryManager telephonyRegistryManager = (TelephonyRegistryManager) this.mContext.getSystemService(Context.TELEPHONY_REGISTRY_SERVICE);
            if (telephonyRegistryManager != null) {
                telephonyRegistryManager.addOnOpportunisticSubscriptionsChangedListener(listener, executor);
            }
        }
    }

    public void removeOnOpportunisticSubscriptionsChangedListener(OnOpportunisticSubscriptionsChangedListener listener) {
        Preconditions.checkNotNull(listener, "listener cannot be null");
        Context context = this.mContext;
        if (context != null) {
            context.getOpPackageName();
        }
        TelephonyRegistryManager telephonyRegistryManager = (TelephonyRegistryManager) this.mContext.getSystemService(Context.TELEPHONY_REGISTRY_SERVICE);
        if (telephonyRegistryManager != null) {
            telephonyRegistryManager.removeOnOpportunisticSubscriptionsChangedListener(listener);
        }
    }

    public SubscriptionInfo getActiveSubscriptionInfo(int subId) {
        if (!isValidSubscriptionId(subId)) {
            return null;
        }
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub == null) {
                return null;
            }
            SubscriptionInfo subInfo = iSub.getActiveSubscriptionInfo(subId, this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            return subInfo;
        } catch (RemoteException e) {
            return null;
        }
    }

    @SystemApi
    public SubscriptionInfo getActiveSubscriptionInfoForIcc(String iccId) {
        if (iccId == null) {
            logd("[getActiveSubscriptionInfoForIccIndex]- null iccid");
            return null;
        }
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub == null) {
                return null;
            }
            SubscriptionInfo result = iSub.getActiveSubscriptionInfoForIccId(iccId, this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            return result;
        } catch (RemoteException e) {
            return null;
        }
    }

    public SubscriptionInfo getActiveSubscriptionInfoForSimSlotIndex(int slotIndex) {
        if (!isValidSlotIndex(slotIndex)) {
            logd("[getActiveSubscriptionInfoForSimSlotIndex]- invalid slotIndex");
            return null;
        }
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub == null) {
                return null;
            }
            SubscriptionInfo result = iSub.getActiveSubscriptionInfoForSimSlotIndex(slotIndex, this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            return result;
        } catch (RemoteException e) {
            return null;
        }
    }

    public List<SubscriptionInfo> getAllSubscriptionInfoList() {
        List<SubscriptionInfo> result = null;
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub != null) {
                result = iSub.getAllSubInfoList(this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            }
        } catch (RemoteException e) {
        }
        if (result == null) {
            return Collections.emptyList();
        }
        return result;
    }

    public List<SubscriptionInfo> getActiveSubscriptionInfoList() {
        return getActiveSubscriptionInfoList(true);
    }

    public List<SubscriptionInfo> getCompleteActiveSubscriptionInfoList() {
        List<SubscriptionInfo> completeList = getActiveSubscriptionInfoList(false);
        if (completeList == null) {
            return new ArrayList<>();
        }
        return completeList;
    }

    public List<SubscriptionInfo> getActiveSubscriptionInfoList(boolean userVisibleOnly) {
        List<SubscriptionInfo> activeList = null;
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub != null) {
                activeList = iSub.getActiveSubscriptionInfoList(this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            }
        } catch (RemoteException e) {
        }
        if (!userVisibleOnly || activeList == null) {
            return activeList;
        }
        return (List) activeList.stream().filter(new Predicate() { // from class: android.telephony.SubscriptionManager$$ExternalSyntheticLambda14
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return SubscriptionManager.this.lambda$getActiveSubscriptionInfoList$1$SubscriptionManager((SubscriptionInfo) obj);
            }
        }).collect(Collectors.toList());
    }

    @SystemApi
    public List<SubscriptionInfo> getAvailableSubscriptionInfoList() {
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub == null) {
                return null;
            }
            List<SubscriptionInfo> result = iSub.getAvailableSubscriptionInfoList(this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            return result;
        } catch (RemoteException e) {
            return null;
        }
    }

    public List<SubscriptionInfo> getAccessibleSubscriptionInfoList() {
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub == null) {
                return null;
            }
            List<SubscriptionInfo> result = iSub.getAccessibleSubscriptionInfoList(this.mContext.getOpPackageName());
            return result;
        } catch (RemoteException e) {
            return null;
        }
    }

    @SystemApi
    public void requestEmbeddedSubscriptionInfoListRefresh() {
        int cardId = TelephonyManager.from(this.mContext).getCardIdForDefaultEuicc();
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub != null) {
                iSub.requestEmbeddedSubscriptionInfoListRefresh(cardId);
            }
        } catch (RemoteException e) {
            logd("requestEmbeddedSubscriptionInfoListFresh for card = " + cardId + " failed.");
        }
    }

    @SystemApi
    public void requestEmbeddedSubscriptionInfoListRefresh(int cardId) {
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub != null) {
                iSub.requestEmbeddedSubscriptionInfoListRefresh(cardId);
            }
        } catch (RemoteException e) {
            logd("requestEmbeddedSubscriptionInfoListFresh for card = " + cardId + " failed.");
        }
    }

    public int getAllSubscriptionInfoCount() {
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub == null) {
                return 0;
            }
            int result = iSub.getAllSubInfoCount(this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            return result;
        } catch (RemoteException e) {
            return 0;
        }
    }

    public int getActiveSubscriptionInfoCount() {
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub == null) {
                return 0;
            }
            int result = iSub.getActiveSubInfoCount(this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            return result;
        } catch (RemoteException e) {
            return 0;
        }
    }

    public int getActiveSubscriptionInfoCountMax() {
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub == null) {
                return 0;
            }
            int result = iSub.getActiveSubInfoCountMax();
            return result;
        } catch (RemoteException e) {
            return 0;
        }
    }

    public Uri addSubscriptionInfoRecord(String iccId, int slotIndex) {
        if (iccId == null) {
            logd("[addSubscriptionInfoRecord]- null iccId");
        }
        if (!isValidSlotIndex(slotIndex)) {
            logd("[addSubscriptionInfoRecord]- invalid slotIndex");
        }
        addSubscriptionInfoRecord(iccId, null, slotIndex, 0);
        return null;
    }

    public void addSubscriptionInfoRecord(String uniqueId, String displayName, int slotIndex, int subscriptionType) {
        if (uniqueId == null) {
            Log.e(LOG_TAG, "[addSubscriptionInfoRecord]- uniqueId is null");
            return;
        }
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub == null) {
                Log.e(LOG_TAG, "[addSubscriptionInfoRecord]- ISub service is null");
                return;
            }
            int result = iSub.addSubInfo(uniqueId, displayName, slotIndex, subscriptionType);
            if (result < 0) {
                Log.e(LOG_TAG, "Adding of subscription didn't succeed: error = " + result);
                return;
            }
            logd("successfully added new subscription");
        } catch (RemoteException e) {
        }
    }

    public void removeSubscriptionInfoRecord(String uniqueId, int subscriptionType) {
        if (uniqueId == null) {
            Log.e(LOG_TAG, "[addSubscriptionInfoRecord]- uniqueId is null");
            return;
        }
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub == null) {
                Log.e(LOG_TAG, "[removeSubscriptionInfoRecord]- ISub service is null");
                return;
            }
            int result = iSub.removeSubInfo(uniqueId, subscriptionType);
            if (result < 0) {
                Log.e(LOG_TAG, "Removal of subscription didn't succeed: error = " + result);
                return;
            }
            logd("successfully removed subscription");
        } catch (RemoteException e) {
        }
    }

    public int setIconTint(final int tint, final int subId) {
        return setSubscriptionPropertyHelper(subId, "setIconTint", new CallISubMethodHelper() { // from class: android.telephony.SubscriptionManager$$ExternalSyntheticLambda2
            @Override // android.telephony.SubscriptionManager.CallISubMethodHelper
            public final int callMethod(ISub iSub) {
                int iconTint;
                iconTint = iSub.setIconTint(tint, subId);
                return iconTint;
            }
        });
    }

    public int setDisplayName(final String displayName, final int subId, final int nameSource) {
        return setSubscriptionPropertyHelper(subId, "setDisplayName", new CallISubMethodHelper() { // from class: android.telephony.SubscriptionManager$$ExternalSyntheticLambda5
            @Override // android.telephony.SubscriptionManager.CallISubMethodHelper
            public final int callMethod(ISub iSub) {
                int displayNameUsingSrc;
                displayNameUsingSrc = iSub.setDisplayNameUsingSrc(displayName, subId, nameSource);
                return displayNameUsingSrc;
            }
        });
    }

    public int setDisplayNumber(final String number, final int subId) {
        if (number != null) {
            return setSubscriptionPropertyHelper(subId, "setDisplayNumber", new CallISubMethodHelper() { // from class: android.telephony.SubscriptionManager$$ExternalSyntheticLambda4
                @Override // android.telephony.SubscriptionManager.CallISubMethodHelper
                public final int callMethod(ISub iSub) {
                    int displayNumber;
                    displayNumber = iSub.setDisplayNumber(number, subId);
                    return displayNumber;
                }
            });
        }
        logd("[setDisplayNumber]- fail");
        return -1;
    }

    public int setDataRoaming(final int roaming, final int subId) {
        return setSubscriptionPropertyHelper(subId, "setDataRoaming", new CallISubMethodHelper() { // from class: android.telephony.SubscriptionManager$$ExternalSyntheticLambda0
            @Override // android.telephony.SubscriptionManager.CallISubMethodHelper
            public final int callMethod(ISub iSub) {
                int dataRoaming;
                dataRoaming = iSub.setDataRoaming(roaming, subId);
                return dataRoaming;
            }
        });
    }

    public static int getSlotIndex(int subscriptionId) {
        return sSlotIndexCache.query(Integer.valueOf(subscriptionId)).intValue();
    }

    public int[] getSubscriptionIds(int slotIndex) {
        return getSubId(slotIndex);
    }

    public static int[] getSubId(int slotIndex) {
        if (!isValidSlotIndex(slotIndex)) {
            logd("[getSubId]- fail");
            return null;
        }
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub == null) {
                return null;
            }
            int[] subId = iSub.getSubId(slotIndex);
            return subId;
        } catch (RemoteException e) {
            return null;
        }
    }

    public static int getPhoneId(int subId) {
        return sPhoneIdCache.query(Integer.valueOf(subId)).intValue();
    }

    private static void logd(String msg) {
        Rlog.d(LOG_TAG, msg);
    }

    private static void loge(String msg) {
        Rlog.e(LOG_TAG, msg);
    }

    public static int getDefaultSubscriptionId() {
        return sDefaultSubIdCache.query(null).intValue();
    }

    public static int getDefaultVoiceSubscriptionId() {
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub == null) {
                return -1;
            }
            int subId = iSub.getDefaultVoiceSubId();
            return subId;
        } catch (RemoteException e) {
            return -1;
        }
    }

    @SystemApi
    public void setDefaultVoiceSubscriptionId(int subscriptionId) {
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub != null) {
                iSub.setDefaultVoiceSubId(subscriptionId);
            }
        } catch (RemoteException e) {
        }
    }

    public void setDefaultVoiceSubId(int subId) {
        setDefaultVoiceSubscriptionId(subId);
    }

    public SubscriptionInfo getDefaultVoiceSubscriptionInfo() {
        return getActiveSubscriptionInfo(getDefaultVoiceSubscriptionId());
    }

    public static int getDefaultVoicePhoneId() {
        return getPhoneId(getDefaultVoiceSubscriptionId());
    }

    public static int getDefaultSmsSubscriptionId() {
        return sDefaultSmsSubIdCache.query(null).intValue();
    }

    @SystemApi
    public void setDefaultSmsSubId(int subscriptionId) {
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub != null) {
                iSub.setDefaultSmsSubId(subscriptionId);
            }
        } catch (RemoteException ex) {
            ex.rethrowFromSystemServer();
        }
    }

    public SubscriptionInfo getDefaultSmsSubscriptionInfo() {
        return getActiveSubscriptionInfo(getDefaultSmsSubscriptionId());
    }

    public int getDefaultSmsPhoneId() {
        return getPhoneId(getDefaultSmsSubscriptionId());
    }

    public static int getDefaultDataSubscriptionId() {
        return sDefaultDataSubIdCache.query(null).intValue();
    }

    @SystemApi
    public void setDefaultDataSubId(int subscriptionId) {
        try {
            if (!Build.IS_USER) {
                Thread.dumpStack();
            }
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub != null) {
                iSub.setDefaultDataSubId(subscriptionId);
            }
        } catch (RemoteException e) {
        }
    }

    public SubscriptionInfo getDefaultDataSubscriptionInfo() {
        return getActiveSubscriptionInfo(getDefaultDataSubscriptionId());
    }

    public int getDefaultDataPhoneId() {
        return getPhoneId(getDefaultDataSubscriptionId());
    }

    public void clearSubscriptionInfo() {
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub != null) {
                iSub.clearSubInfo();
            }
        } catch (RemoteException e) {
        }
    }

    public boolean allDefaultsSelected() {
        return isValidSubscriptionId(getDefaultDataSubscriptionId()) && isValidSubscriptionId(getDefaultSmsSubscriptionId()) && isValidSubscriptionId(getDefaultVoiceSubscriptionId());
    }

    public static boolean isValidSubscriptionId(int subscriptionId) {
        return subscriptionId > -1;
    }

    public static boolean isUsableSubscriptionId(int subscriptionId) {
        return isUsableSubIdValue(subscriptionId);
    }

    public static boolean isUsableSubIdValue(int subId) {
        return subId >= 0 && subId <= 2147483646;
    }

    public static boolean isValidSlotIndex(int slotIndex) {
        return slotIndex >= 0 && slotIndex < TelephonyManager.getDefault().getActiveModemCount();
    }

    public static boolean isValidPhoneId(int phoneId) {
        return phoneId >= 0 && phoneId < TelephonyManager.getDefault().getActiveModemCount();
    }

    public static void putPhoneIdAndSubIdExtra(Intent intent, int phoneId) {
        int[] subIds = getSubId(phoneId);
        if (subIds == null || subIds.length <= 0) {
            logd("putPhoneIdAndSubIdExtra: no valid subs");
            intent.putExtra("phone", phoneId);
            intent.putExtra("android.telephony.extra.SLOT_INDEX", phoneId);
            return;
        }
        putPhoneIdAndSubIdExtra(intent, phoneId, subIds[0]);
    }

    public static void putPhoneIdAndSubIdExtra(Intent intent, int phoneId, int subId) {
        intent.putExtra("android.telephony.extra.SLOT_INDEX", phoneId);
        intent.putExtra("phone", phoneId);
        putSubscriptionIdExtra(intent, subId);
    }

    @SystemApi
    public int[] getActiveSubscriptionIdList() {
        return getActiveSubscriptionIdList(true);
    }

    @SystemApi
    public int[] getCompleteActiveSubscriptionIdList() {
        return getActiveSubscriptionIdList(false);
    }

    public int[] getActiveSubscriptionIdList(boolean visibleOnly) {
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub != null) {
                int[] subId = iSub.getActiveSubIdList(visibleOnly);
                if (subId != null) {
                    return subId;
                }
            }
        } catch (RemoteException e) {
        }
        return new int[0];
    }

    public boolean isNetworkRoaming(int subId) {
        int phoneId = getPhoneId(subId);
        if (phoneId < 0) {
            return false;
        }
        return TelephonyManager.getDefault().isNetworkRoaming(subId);
    }

    public static int getSimStateForSlotIndex(int slotIndex) {
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub == null) {
                return 0;
            }
            int simState = iSub.getSimStateForSlotIndex(slotIndex);
            return simState;
        } catch (RemoteException e) {
            return 0;
        }
    }

    public static void setSubscriptionProperty(int subId, String propKey, String propValue) {
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub != null) {
                iSub.setSubscriptionProperty(subId, propKey, propValue);
            }
        } catch (RemoteException e) {
        }
    }

    public static String serializeUriLists(List<Uri> uris) {
        List<String> contacts = new ArrayList<>();
        for (Uri uri : uris) {
            contacts.add(uri.toString());
        }
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(contacts);
            oos.flush();
            return Base64.encodeToString(bos.toByteArray(), 0);
        } catch (IOException e) {
            logd("serializeUriLists IO exception");
            return "";
        }
    }

    private static List<Uri> getContactsFromSubscriptionProperty(int subId, String propKey, Context context) {
        String result = getSubscriptionProperty(subId, propKey, context);
        if (result != null) {
            try {
                byte[] b = Base64.decode(result, 0);
                ByteArrayInputStream bis = new ByteArrayInputStream(b);
                ObjectInputStream ois = new ObjectInputStream(bis);
                List<String> contacts = (List) ArrayList.class.cast(ois.readObject());
                List<Uri> uris = new ArrayList<>();
                for (String contact : contacts) {
                    uris.add(Uri.parse(contact));
                }
                return uris;
            } catch (IOException e) {
                logd("getContactsFromSubscriptionProperty IO exception");
            } catch (ClassNotFoundException e2) {
                logd("getContactsFromSubscriptionProperty ClassNotFound exception");
            }
        }
        return new ArrayList();
    }

    private static String getSubscriptionProperty(int subId, String propKey, Context context) {
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub == null) {
                return null;
            }
            String resultValue = iSub.getSubscriptionProperty(subId, propKey, context.getOpPackageName(), context.getAttributionTag());
            return resultValue;
        } catch (RemoteException e) {
            return null;
        }
    }

    public static boolean getBooleanSubscriptionProperty(int subId, String propKey, boolean defValue, Context context) {
        String result = getSubscriptionProperty(subId, propKey, context);
        if (result != null) {
            try {
                return Integer.parseInt(result) == 1;
            } catch (NumberFormatException e) {
                logd("getBooleanSubscriptionProperty NumberFormat exception");
            }
        }
        return defValue;
    }

    public static int getIntegerSubscriptionProperty(int subId, String propKey, int defValue, Context context) {
        String result = getSubscriptionProperty(subId, propKey, context);
        if (result != null) {
            try {
                return Integer.parseInt(result);
            } catch (NumberFormatException e) {
                logd("getIntegerSubscriptionProperty NumberFormat exception");
            }
        }
        return defValue;
    }

    public static long getLongSubscriptionProperty(int subId, String propKey, long defValue, Context context) {
        String result = getSubscriptionProperty(subId, propKey, context);
        if (result != null) {
            try {
                return Long.parseLong(result);
            } catch (NumberFormatException e) {
                logd("getLongSubscriptionProperty NumberFormat exception");
            }
        }
        return defValue;
    }

    @SystemApi
    public static Resources getResourcesForSubId(Context context, int subId) {
        return getResourcesForSubId(context, subId, false);
    }

    public static Resources getResourcesForSubId(Context context, int subId, boolean useRootLocale) {
        Pair<Context, Integer> cacheKey = null;
        if (isValidSubscriptionId(subId) && !useRootLocale) {
            cacheKey = Pair.create(context, Integer.valueOf(subId));
            Map<Pair<Context, Integer>, Resources> map = sResourcesCache;
            if (map.containsKey(cacheKey)) {
                return map.get(cacheKey);
            }
        }
        SubscriptionInfo subInfo = from(context).getActiveSubscriptionInfo(subId);
        Configuration overrideConfig = new Configuration();
        if (subInfo != null) {
            overrideConfig.mcc = subInfo.getMcc();
            overrideConfig.mnc = subInfo.getMnc();
            if (overrideConfig.mnc == 0) {
                overrideConfig.mnc = 65535;
                cacheKey = null;
            }
        }
        if (useRootLocale) {
            overrideConfig.setLocale(Locale.ROOT);
        }
        Context newContext = context.createConfigurationContext(overrideConfig);
        Resources res = newContext.getResources();
        if (cacheKey != null) {
            sResourcesCache.put(cacheKey, res);
        }
        return res;
    }

    public boolean isActiveSubscriptionId(int subscriptionId) {
        return isActiveSubId(subscriptionId);
    }

    public boolean isActiveSubId(int subId) {
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub != null) {
                return iSub.isActiveSubId(subId, this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            }
            return false;
        } catch (RemoteException e) {
            return false;
        }
    }

    public List<SubscriptionPlan> getSubscriptionPlans(int subId) {
        SubscriptionPlan[] subscriptionPlans = getNetworkPolicyManager().getSubscriptionPlans(subId, this.mContext.getOpPackageName());
        return subscriptionPlans == null ? Collections.emptyList() : Arrays.asList(subscriptionPlans);
    }

    public void setSubscriptionPlans(int subId, List<SubscriptionPlan> plans) {
        getNetworkPolicyManager().setSubscriptionPlans(subId, (SubscriptionPlan[]) plans.toArray(new SubscriptionPlan[plans.size()]), this.mContext.getOpPackageName());
    }

    public void setSubscriptionOverrideUnmetered(int subId, boolean overrideUnmetered, long timeoutMillis) {
        setSubscriptionOverrideUnmetered(subId, overrideUnmetered, TelephonyManager.getAllNetworkTypes(), timeoutMillis);
    }

    public void setSubscriptionOverrideUnmetered(int subId, boolean overrideUnmetered, int[] networkTypes, long timeoutMillis) {
        getNetworkPolicyManager().setSubscriptionOverride(subId, 1, overrideUnmetered ? 1 : 0, networkTypes, timeoutMillis, this.mContext.getOpPackageName());
    }

    public void setSubscriptionOverrideCongested(int subId, boolean overrideCongested, long timeoutMillis) {
        setSubscriptionOverrideCongested(subId, overrideCongested, TelephonyManager.getAllNetworkTypes(), timeoutMillis);
    }

    public void setSubscriptionOverrideCongested(int subId, boolean overrideCongested, int[] networkTypes, long timeoutMillis) {
        int overrideValue = overrideCongested ? 2 : 0;
        getNetworkPolicyManager().setSubscriptionOverride(subId, 2, overrideValue, networkTypes, timeoutMillis, this.mContext.getOpPackageName());
    }

    public boolean canManageSubscription(SubscriptionInfo info) {
        return canManageSubscription(info, this.mContext.getPackageName());
    }

    @SystemApi
    public boolean canManageSubscription(SubscriptionInfo info, String packageName) {
        if (info == null || info.getAllAccessRules() == null || packageName == null) {
            return false;
        }
        PackageManager packageManager = this.mContext.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 134217728);
            for (UiccAccessRule rule : info.getAllAccessRules()) {
                if (rule.getCarrierPrivilegeStatus(packageInfo) == 1) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            logd("Unknown package: " + packageName);
            return false;
        }
    }

    @SystemApi
    public void setPreferredDataSubscriptionId(int subId, boolean needValidation, Executor executor, Consumer<Integer> callback) {
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub != null) {
                ISetOpportunisticDataCallback callbackStub = new AnonymousClass1(executor, callback);
                iSub.setPreferredDataSubscriptionId(subId, needValidation, callbackStub);
            }
        } catch (RemoteException e) {
        }
    }

    /* renamed from: android.telephony.SubscriptionManager$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass1 extends ISetOpportunisticDataCallback.Stub {
        final /* synthetic */ Consumer val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass1(Executor executor, Consumer consumer) {
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
                    executor.execute(new Runnable() { // from class: android.telephony.SubscriptionManager$1$$ExternalSyntheticLambda0
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

    public int getPreferredDataSubscriptionId() {
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub == null) {
                return Integer.MAX_VALUE;
            }
            int preferredSubId = iSub.getPreferredDataSubscriptionId();
            return preferredSubId;
        } catch (RemoteException e) {
            return Integer.MAX_VALUE;
        }
    }

    public List<SubscriptionInfo> getOpportunisticSubscriptions() {
        Context context = this.mContext;
        String contextPkg = context != null ? context.getOpPackageName() : "<unknown>";
        Context context2 = this.mContext;
        String contextAttributionTag = context2 != null ? context2.getAttributionTag() : null;
        List<SubscriptionInfo> subInfoList = null;
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub != null) {
                subInfoList = iSub.getOpportunisticSubscriptions(contextPkg, contextAttributionTag);
            }
        } catch (RemoteException e) {
        }
        if (subInfoList == null) {
            return new ArrayList<>();
        }
        return subInfoList;
    }

    public void switchToSubscription(int subId, PendingIntent callbackIntent) {
        Preconditions.checkNotNull(callbackIntent, "callbackIntent cannot be null");
        EuiccManager euiccManager = new EuiccManager(this.mContext);
        euiccManager.switchToSubscription(subId, callbackIntent);
    }

    public boolean setOpportunistic(final boolean opportunistic, final int subId) {
        return setSubscriptionPropertyHelper(subId, "setOpportunistic", new CallISubMethodHelper() { // from class: android.telephony.SubscriptionManager$$ExternalSyntheticLambda3
            @Override // android.telephony.SubscriptionManager.CallISubMethodHelper
            public final int callMethod(ISub iSub) {
                return SubscriptionManager.this.lambda$setOpportunistic$6$SubscriptionManager(opportunistic, subId, iSub);
            }
        }) == 1;
    }

    public /* synthetic */ int lambda$setOpportunistic$6$SubscriptionManager(boolean opportunistic, int subId, ISub iSub) throws RemoteException {
        return iSub.setOpportunistic(opportunistic, subId, this.mContext.getOpPackageName());
    }

    public ParcelUuid createSubscriptionGroup(List<Integer> subIdList) {
        Preconditions.checkNotNull(subIdList, "can't create group for null subId list");
        Context context = this.mContext;
        String pkgForDebug = context != null ? context.getOpPackageName() : "<unknown>";
        ParcelUuid groupUuid = null;
        int[] subIdArray = subIdList.stream().mapToInt(SubscriptionManager$$ExternalSyntheticLambda16.INSTANCE).toArray();
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub != null) {
                groupUuid = iSub.createSubscriptionGroup(subIdArray, pkgForDebug);
            } else if (!isSystemProcess()) {
                throw new IllegalStateException("telephony service is null.");
            }
        } catch (RemoteException ex) {
            loge("createSubscriptionGroup RemoteException " + ex);
            if (!isSystemProcess()) {
                ex.rethrowAsRuntimeException();
            }
        }
        return groupUuid;
    }

    public void addSubscriptionsIntoGroup(List<Integer> subIdList, ParcelUuid groupUuid) {
        Preconditions.checkNotNull(subIdList, "subIdList can't be null.");
        Preconditions.checkNotNull(groupUuid, "groupUuid can't be null.");
        Context context = this.mContext;
        String pkgForDebug = context != null ? context.getOpPackageName() : "<unknown>";
        int[] subIdArray = subIdList.stream().mapToInt(SubscriptionManager$$ExternalSyntheticLambda15.INSTANCE).toArray();
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub != null) {
                iSub.addSubscriptionsIntoGroup(subIdArray, groupUuid, pkgForDebug);
            } else if (!isSystemProcess()) {
                throw new IllegalStateException("telephony service is null.");
            }
        } catch (RemoteException ex) {
            loge("addSubscriptionsIntoGroup RemoteException " + ex);
            if (!isSystemProcess()) {
                ex.rethrowAsRuntimeException();
            }
        }
    }

    private boolean isSystemProcess() {
        return Process.myUid() == 1000;
    }

    public void removeSubscriptionsFromGroup(List<Integer> subIdList, ParcelUuid groupUuid) {
        Preconditions.checkNotNull(subIdList, "subIdList can't be null.");
        Preconditions.checkNotNull(groupUuid, "groupUuid can't be null.");
        Context context = this.mContext;
        String pkgForDebug = context != null ? context.getOpPackageName() : "<unknown>";
        int[] subIdArray = subIdList.stream().mapToInt(SubscriptionManager$$ExternalSyntheticLambda17.INSTANCE).toArray();
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub != null) {
                iSub.removeSubscriptionsFromGroup(subIdArray, groupUuid, pkgForDebug);
            } else if (!isSystemProcess()) {
                throw new IllegalStateException("telephony service is null.");
            }
        } catch (RemoteException ex) {
            loge("removeSubscriptionsFromGroup RemoteException " + ex);
            if (!isSystemProcess()) {
                ex.rethrowAsRuntimeException();
            }
        }
    }

    public List<SubscriptionInfo> getSubscriptionsInGroup(ParcelUuid groupUuid) {
        Preconditions.checkNotNull(groupUuid, "groupUuid can't be null");
        Context context = this.mContext;
        String contextPkg = context != null ? context.getOpPackageName() : "<unknown>";
        Context context2 = this.mContext;
        String contextAttributionTag = context2 != null ? context2.getAttributionTag() : null;
        List<SubscriptionInfo> result = null;
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub != null) {
                result = iSub.getSubscriptionsInGroup(groupUuid, contextPkg, contextAttributionTag);
            } else if (!isSystemProcess()) {
                throw new IllegalStateException("telephony service is null.");
            }
        } catch (RemoteException ex) {
            loge("removeSubscriptionsFromGroup RemoteException " + ex);
            if (!isSystemProcess()) {
                ex.rethrowAsRuntimeException();
            }
        }
        return result;
    }

    /* renamed from: isSubscriptionVisible */
    public boolean lambda$getActiveSubscriptionInfoList$1$SubscriptionManager(SubscriptionInfo info) {
        if (info == null) {
            return false;
        }
        if (info.getGroupUuid() == null || !info.isOpportunistic()) {
            return true;
        }
        return TelephonyManager.from(this.mContext).hasCarrierPrivileges(info.getSubscriptionId()) || canManageSubscription(info);
    }

    public List<SubscriptionInfo> getSelectableSubscriptionInfoList() {
        List<SubscriptionInfo> availableList = getAvailableSubscriptionInfoList();
        if (availableList == null) {
            return null;
        }
        List<SubscriptionInfo> selectableList = new ArrayList<>();
        Map<ParcelUuid, SubscriptionInfo> groupMap = new HashMap<>();
        for (SubscriptionInfo info : availableList) {
            if (lambda$getActiveSubscriptionInfoList$1$SubscriptionManager(info)) {
                ParcelUuid groupUuid = info.getGroupUuid();
                if (groupUuid == null) {
                    selectableList.add(info);
                } else if (!groupMap.containsKey(groupUuid) || (groupMap.get(groupUuid).getSimSlotIndex() == -1 && info.getSimSlotIndex() != -1)) {
                    selectableList.remove(groupMap.get(groupUuid));
                    selectableList.add(info);
                    groupMap.put(groupUuid, info);
                }
            }
        }
        return selectableList;
    }

    @SystemApi
    public boolean setSubscriptionEnabled(int subscriptionId, boolean enable) {
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub != null) {
                return iSub.setSubscriptionEnabled(enable, subscriptionId);
            }
            return false;
        } catch (RemoteException e) {
            return false;
        }
    }

    @SystemApi
    public void setUiccApplicationsEnabled(int subscriptionId, boolean enabled) {
        try {
            ISub iSub = ISub.Stub.asInterface(TelephonyFrameworkInitializer.getTelephonyServiceManager().getSubscriptionServiceRegisterer().get());
            if (iSub != null) {
                iSub.setUiccApplicationsEnabled(enabled, subscriptionId);
            }
        } catch (RemoteException e) {
        }
    }

    @SystemApi
    public boolean canDisablePhysicalSubscription() {
        try {
            ISub iSub = ISub.Stub.asInterface(TelephonyFrameworkInitializer.getTelephonyServiceManager().getSubscriptionServiceRegisterer().get());
            if (iSub != null) {
                return iSub.canDisablePhysicalSubscription();
            }
            return false;
        } catch (RemoteException e) {
            return false;
        }
    }

    @SystemApi
    public boolean isSubscriptionEnabled(int subscriptionId) {
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub != null) {
                return iSub.isSubscriptionEnabled(subscriptionId);
            }
            return false;
        } catch (RemoteException e) {
            return false;
        }
    }

    public void setDeviceToDeviceStatusSharingPreference(final int subscriptionId, final int sharing) {
        setSubscriptionPropertyHelper(subscriptionId, "setDeviceToDeviceSharingStatus", new CallISubMethodHelper() { // from class: android.telephony.SubscriptionManager$$ExternalSyntheticLambda1
            @Override // android.telephony.SubscriptionManager.CallISubMethodHelper
            public final int callMethod(ISub iSub) {
                int deviceToDeviceStatusSharing;
                deviceToDeviceStatusSharing = iSub.setDeviceToDeviceStatusSharing(sharing, subscriptionId);
                return deviceToDeviceStatusSharing;
            }
        });
    }

    public int getDeviceToDeviceStatusSharingPreference(int subscriptionId) {
        return getIntegerSubscriptionProperty(subscriptionId, "d2d_sharing_status", 0, this.mContext);
    }

    public void setDeviceToDeviceStatusSharingContacts(final int subscriptionId, final List<Uri> contacts) {
        serializeUriLists(contacts);
        setSubscriptionPropertyHelper(subscriptionId, "setDeviceToDeviceSharingStatus", new CallISubMethodHelper() { // from class: android.telephony.SubscriptionManager$$ExternalSyntheticLambda6
            @Override // android.telephony.SubscriptionManager.CallISubMethodHelper
            public final int callMethod(ISub iSub) {
                int deviceToDeviceStatusSharingContacts;
                deviceToDeviceStatusSharingContacts = iSub.setDeviceToDeviceStatusSharingContacts(SubscriptionManager.serializeUriLists(contacts), subscriptionId);
                return deviceToDeviceStatusSharingContacts;
            }
        });
    }

    public List<Uri> getDeviceToDeviceStatusSharingContacts(int subscriptionId) {
        return getContactsFromSubscriptionProperty(subscriptionId, "d2d_sharing_contacts", this.mContext);
    }

    @SystemApi
    public int getEnabledSubscriptionId(int slotIndex) {
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub == null) {
                return -1;
            }
            int subId = iSub.getEnabledSubscriptionId(slotIndex);
            return subId;
        } catch (RemoteException e) {
            return -1;
        }
    }

    private int setSubscriptionPropertyHelper(int subId, String methodName, CallISubMethodHelper helper) {
        if (!isValidSubscriptionId(subId)) {
            logd("[" + methodName + "]- fail");
            return -1;
        }
        try {
            ISub iSub = TelephonyManager.getSubscriptionService();
            if (iSub == null) {
                return 0;
            }
            int result = helper.callMethod(iSub);
            return result;
        } catch (RemoteException e) {
            return 0;
        }
    }

    public static int getActiveDataSubscriptionId() {
        return sActiveDataSubIdCache.query(null).intValue();
    }

    public static void putSubscriptionIdExtra(Intent intent, int subId) {
        intent.putExtra("android.telephony.extra.SUBSCRIPTION_INDEX", subId);
        intent.putExtra("subscription", subId);
    }

    public static void invalidateDefaultSubIdCaches() {
        PropertyInvalidatedCache.invalidateCache(CACHE_KEY_DEFAULT_SUB_ID_PROPERTY);
    }

    public static void invalidateDefaultDataSubIdCaches() {
        PropertyInvalidatedCache.invalidateCache(CACHE_KEY_DEFAULT_DATA_SUB_ID_PROPERTY);
    }

    public static void invalidateDefaultSmsSubIdCaches() {
        PropertyInvalidatedCache.invalidateCache(CACHE_KEY_DEFAULT_SMS_SUB_ID_PROPERTY);
    }

    public static void invalidateActiveDataSubIdCaches() {
        PropertyInvalidatedCache.invalidateCache(CACHE_KEY_ACTIVE_DATA_SUB_ID_PROPERTY);
    }

    public static void invalidateSlotIndexCaches() {
        PropertyInvalidatedCache.invalidateCache(CACHE_KEY_SLOT_INDEX_PROPERTY);
    }

    public static void disableCaching() {
        sDefaultSubIdCache.disableLocal();
        sDefaultDataSubIdCache.disableLocal();
        sActiveDataSubIdCache.disableLocal();
        sDefaultSmsSubIdCache.disableLocal();
        sSlotIndexCache.disableLocal();
        sPhoneIdCache.disableLocal();
    }

    public static void clearCaches() {
        sDefaultSubIdCache.clear();
        sDefaultDataSubIdCache.clear();
        sActiveDataSubIdCache.clear();
        sDefaultSmsSubIdCache.clear();
        sSlotIndexCache.clear();
        sPhoneIdCache.clear();
    }

    @SystemApi
    public byte[] getAllSimSpecificSettingsForBackup() {
        Bundle bundle = this.mContext.getContentResolver().call(SIM_INFO_BACKUP_AND_RESTORE_CONTENT_URI, GET_SIM_SPECIFIC_SETTINGS_METHOD_NAME, (String) null, (Bundle) null);
        return bundle.getByteArray(KEY_SIM_SPECIFIC_SETTINGS_DATA);
    }

    public void restoreSimSpecificSettingsForIccIdFromBackup(String iccId) {
        this.mContext.getContentResolver().call(SIM_INFO_BACKUP_AND_RESTORE_CONTENT_URI, RESTORE_SIM_SPECIFIC_SETTINGS_METHOD_NAME, iccId, (Bundle) null);
    }

    @SystemApi
    public void restoreAllSimSpecificSettingsFromBackup(byte[] data) {
        Bundle bundle = new Bundle();
        bundle.putByteArray(KEY_SIM_SPECIFIC_SETTINGS_DATA, data);
        this.mContext.getContentResolver().call(SIM_INFO_BACKUP_AND_RESTORE_CONTENT_URI, RESTORE_SIM_SPECIFIC_SETTINGS_METHOD_NAME, (String) null, bundle);
    }
}
