package android.net;

import android.annotation.SystemApi;
import android.app.ActivityManager;
import android.content.Context;
import android.net.INetworkPolicyListener;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.os.Process;
import android.os.RemoteException;
import android.security.keystore.KeyProperties;
import android.telephony.SubscriptionPlan;
import android.util.DebugUtils;
import android.util.Pair;
import android.util.Range;
import com.android.internal.util.function.pooled.PooledLambda;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.time.ZonedDateTime;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
@SystemApi(client = SystemApi.Client.MODULE_LIBRARIES)
/* loaded from: classes2.dex */
public class NetworkPolicyManager {
    public static final int ALLOWED_METERED_REASON_FOREGROUND = 262144;
    public static final int ALLOWED_METERED_REASON_MASK = -65536;
    public static final int ALLOWED_METERED_REASON_SYSTEM = 131072;
    public static final int ALLOWED_METERED_REASON_USER_EXEMPTED = 65536;
    public static final int ALLOWED_REASON_FOREGROUND = 2;
    public static final int ALLOWED_REASON_NONE = 0;
    public static final int ALLOWED_REASON_POWER_SAVE_ALLOWLIST = 4;
    public static final int ALLOWED_REASON_POWER_SAVE_EXCEPT_IDLE_ALLOWLIST = 8;
    public static final int ALLOWED_REASON_RESTRICTED_MODE_PERMISSIONS = 16;
    public static final int ALLOWED_REASON_SYSTEM = 1;
    private static final boolean ALLOW_PLATFORM_APP_POLICY = true;
    public static final String EXTRA_NETWORK_TEMPLATE = "android.net.NETWORK_TEMPLATE";
    public static final String FIREWALL_CHAIN_NAME_DOZABLE = "dozable";
    public static final String FIREWALL_CHAIN_NAME_NONE = "none";
    public static final String FIREWALL_CHAIN_NAME_POWERSAVE = "powersave";
    public static final String FIREWALL_CHAIN_NAME_RESTRICTED = "restricted";
    public static final String FIREWALL_CHAIN_NAME_STANDBY = "standby";
    public static final int FIREWALL_RULE_DEFAULT = 0;
    public static final int FOREGROUND_THRESHOLD_STATE = 5;
    public static final int MASK_ALL_NETWORKS = 240;
    public static final int MASK_METERED_NETWORKS = 15;
    public static final int MASK_RESTRICTED_MODE_NETWORKS = 3840;
    public static final int POLICY_ALLOW_METERED_BACKGROUND = 4;
    public static final int POLICY_NONE = 0;
    public static final int POLICY_REJECT_METERED_BACKGROUND = 1;
    public static final int RULE_ALLOW_ALL = 32;
    public static final int RULE_ALLOW_METERED = 1;
    public static final int RULE_NONE = 0;
    public static final int RULE_REJECT_ALL = 64;
    public static final int RULE_REJECT_METERED = 4;
    public static final int RULE_REJECT_RESTRICTED_MODE = 1024;
    public static final int RULE_TEMPORARY_ALLOW_METERED = 2;
    public static final int SUBSCRIPTION_OVERRIDE_CONGESTED = 2;
    public static final int SUBSCRIPTION_OVERRIDE_UNMETERED = 1;
    private final Context mContext;
    private INetworkPolicyManager mService;
    private final Map<SubscriptionCallback, SubscriptionCallbackProxy> mSubscriptionCallbackMap = new ConcurrentHashMap();
    private final Map<NetworkPolicyCallback, NetworkPolicyCallbackProxy> mNetworkPolicyCallbackMap = new ConcurrentHashMap();

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface SubscriptionOverrideMask {
    }

    public NetworkPolicyManager(Context context, INetworkPolicyManager service) {
        if (service != null) {
            this.mContext = context;
            this.mService = service;
            return;
        }
        throw new IllegalArgumentException("missing INetworkPolicyManager");
    }

    public static NetworkPolicyManager from(Context context) {
        return (NetworkPolicyManager) context.getSystemService(Context.NETWORK_POLICY_SERVICE);
    }

    public void setUidPolicy(int uid, int policy) {
        try {
            this.mService.setUidPolicy(uid, policy);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void addUidPolicy(int uid, int policy) {
        try {
            this.mService.addUidPolicy(uid, policy);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void removeUidPolicy(int uid, int policy) {
        try {
            this.mService.removeUidPolicy(uid, policy);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int getUidPolicy(int uid) {
        try {
            return this.mService.getUidPolicy(uid);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int[] getUidsWithPolicy(int policy) {
        try {
            return this.mService.getUidsWithPolicy(policy);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void registerListener(INetworkPolicyListener listener) {
        try {
            this.mService.registerListener(listener);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void unregisterListener(INetworkPolicyListener listener) {
        try {
            this.mService.unregisterListener(listener);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void registerSubscriptionCallback(SubscriptionCallback callback) {
        if (callback != null) {
            SubscriptionCallbackProxy callbackProxy = new SubscriptionCallbackProxy(callback);
            if (this.mSubscriptionCallbackMap.putIfAbsent(callback, callbackProxy) == null) {
                registerListener(callbackProxy);
                return;
            }
            throw new IllegalArgumentException("Callback is already registered.");
        }
        throw new NullPointerException("Callback cannot be null.");
    }

    public void unregisterSubscriptionCallback(SubscriptionCallback callback) {
        if (callback != null) {
            SubscriptionCallbackProxy callbackProxy = this.mSubscriptionCallbackMap.remove(callback);
            if (callbackProxy != null) {
                unregisterListener(callbackProxy);
                return;
            }
            return;
        }
        throw new NullPointerException("Callback cannot be null.");
    }

    public void setNetworkPolicies(NetworkPolicy[] policies) {
        try {
            this.mService.setNetworkPolicies(policies);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public NetworkPolicy[] getNetworkPolicies() {
        try {
            return this.mService.getNetworkPolicies(this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setRestrictBackground(boolean restrictBackground) {
        try {
            this.mService.setRestrictBackground(restrictBackground);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean getRestrictBackground() {
        try {
            return this.mService.getRestrictBackground();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi(client = SystemApi.Client.MODULE_LIBRARIES)
    public int getRestrictBackgroundStatus(int uid) {
        try {
            return this.mService.getRestrictBackgroundStatus(uid);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setSubscriptionOverride(int subId, int overrideMask, int overrideValue, int[] networkTypes, long timeoutMillis, String callingPackage) {
        try {
            this.mService.setSubscriptionOverride(subId, overrideMask, overrideValue, networkTypes, timeoutMillis, callingPackage);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setSubscriptionPlans(int subId, SubscriptionPlan[] plans, String callingPackage) {
        try {
            this.mService.setSubscriptionPlans(subId, plans, callingPackage);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public SubscriptionPlan[] getSubscriptionPlans(int subId, String callingPackage) {
        try {
            return this.mService.getSubscriptionPlans(subId, callingPackage);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void factoryReset(String subscriber) {
        try {
            this.mService.factoryReset(subscriber);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isUidNetworkingBlocked(int uid, boolean meteredNetwork) {
        try {
            return this.mService.isUidNetworkingBlocked(uid, meteredNetwork);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isUidRestrictedOnMeteredNetworks(int uid) {
        try {
            return this.mService.isUidRestrictedOnMeteredNetworks(uid);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi(client = SystemApi.Client.MODULE_LIBRARIES)
    public int getMultipathPreference(Network network) {
        try {
            return this.mService.getMultipathPreference(network);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public static Iterator<Pair<ZonedDateTime, ZonedDateTime>> cycleIterator(NetworkPolicy policy) {
        final Iterator<Range<ZonedDateTime>> it = policy.cycleIterator();
        return new Iterator<Pair<ZonedDateTime, ZonedDateTime>>() { // from class: android.net.NetworkPolicyManager.1
            @Override // java.util.Iterator
            public boolean hasNext() {
                return it.hasNext();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public Pair<ZonedDateTime, ZonedDateTime> next() {
                if (!hasNext()) {
                    return Pair.create(null, null);
                }
                Range<ZonedDateTime> r = (Range) it.next();
                return Pair.create(r.getLower(), r.getUpper());
            }
        };
    }

    @Deprecated
    public static boolean isUidValidForPolicy(Context context, int uid) {
        if (!Process.isApplicationUid(uid)) {
            return false;
        }
        return true;
    }

    public static String uidRulesToString(int uidRules) {
        StringBuilder sb = new StringBuilder();
        sb.append(uidRules);
        StringBuilder string = sb.append(" (");
        if (uidRules == 0) {
            string.append(KeyProperties.DIGEST_NONE);
        } else {
            string.append(DebugUtils.flagsToString(NetworkPolicyManager.class, "RULE_", uidRules));
        }
        string.append(")");
        return string.toString();
    }

    public static String uidPoliciesToString(int uidPolicies) {
        StringBuilder sb = new StringBuilder();
        sb.append(uidPolicies);
        StringBuilder string = sb.append(" (");
        if (uidPolicies == 0) {
            string.append(KeyProperties.DIGEST_NONE);
        } else {
            string.append(DebugUtils.flagsToString(NetworkPolicyManager.class, "POLICY_", uidPolicies));
        }
        string.append(")");
        return string.toString();
    }

    public static boolean isProcStateAllowedWhileIdleOrPowerSaveMode(UidState uidState) {
        if (uidState == null) {
            return false;
        }
        return isProcStateAllowedWhileIdleOrPowerSaveMode(uidState.procState, uidState.capability);
    }

    public static boolean isProcStateAllowedWhileIdleOrPowerSaveMode(int procState, int capability) {
        return procState <= 5 || (capability & 8) != 0;
    }

    public static boolean isProcStateAllowedWhileOnRestrictBackground(UidState uidState) {
        if (uidState == null) {
            return false;
        }
        return isProcStateAllowedWhileOnRestrictBackground(uidState.procState);
    }

    public static boolean isProcStateAllowedWhileOnRestrictBackground(int procState) {
        return procState <= 5;
    }

    /* loaded from: classes2.dex */
    public static final class UidState {
        public int capability;
        public int procState;
        public int uid;

        public UidState(int uid, int procState, int capability) {
            this.uid = uid;
            this.procState = procState;
            this.capability = capability;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("{procState=");
            sb.append(ActivityManager.procStateToString(this.procState));
            sb.append(",cap=");
            ActivityManager.printCapabilitiesSummary(sb, this.capability);
            sb.append("}");
            return sb.toString();
        }
    }

    public static String resolveNetworkId(WifiConfiguration config) {
        return WifiInfo.sanitizeSsid(config.isPasspoint() ? config.providerFriendlyName : config.SSID);
    }

    public static String resolveNetworkId(String ssid) {
        return WifiInfo.sanitizeSsid(ssid);
    }

    public static String blockedReasonsToString(int blockedReasons) {
        return DebugUtils.flagsToString(ConnectivityManager.class, "BLOCKED_", blockedReasons);
    }

    public static String allowedReasonsToString(int allowedReasons) {
        return DebugUtils.flagsToString(NetworkPolicyManager.class, "ALLOWED_", allowedReasons);
    }

    @SystemApi(client = SystemApi.Client.MODULE_LIBRARIES)
    public void registerNetworkPolicyCallback(Executor executor, NetworkPolicyCallback callback) {
        if (callback != null) {
            NetworkPolicyCallbackProxy callbackProxy = new NetworkPolicyCallbackProxy(executor, callback);
            registerListener(callbackProxy);
            this.mNetworkPolicyCallbackMap.put(callback, callbackProxy);
            return;
        }
        throw new NullPointerException("Callback cannot be null.");
    }

    @SystemApi(client = SystemApi.Client.MODULE_LIBRARIES)
    public void unregisterNetworkPolicyCallback(NetworkPolicyCallback callback) {
        if (callback != null) {
            NetworkPolicyCallbackProxy callbackProxy = this.mNetworkPolicyCallbackMap.remove(callback);
            if (callbackProxy != null) {
                unregisterListener(callbackProxy);
                return;
            }
            return;
        }
        throw new NullPointerException("Callback cannot be null.");
    }

    @SystemApi(client = SystemApi.Client.MODULE_LIBRARIES)
    /* loaded from: classes2.dex */
    public interface NetworkPolicyCallback {
        @SystemApi(client = SystemApi.Client.MODULE_LIBRARIES)
        default void onUidBlockedReasonChanged(int uid, int blockedReasons) {
        }
    }

    /* loaded from: classes2.dex */
    public static class NetworkPolicyCallbackProxy extends Listener {
        private final NetworkPolicyCallback mCallback;
        private final Executor mExecutor;

        NetworkPolicyCallbackProxy(Executor executor, NetworkPolicyCallback callback) {
            this.mExecutor = executor;
            this.mCallback = callback;
        }

        @Override // android.net.NetworkPolicyManager.Listener, android.net.INetworkPolicyListener
        public void onBlockedReasonChanged(int uid, int oldBlockedReasons, int newBlockedReasons) {
            if (oldBlockedReasons != newBlockedReasons) {
                NetworkPolicyManager.dispatchOnUidBlockedReasonChanged(this.mExecutor, this.mCallback, uid, newBlockedReasons);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dispatchOnUidBlockedReasonChanged(Executor executor, NetworkPolicyCallback callback, int uid, int blockedReasons) {
        if (executor == null) {
            callback.onUidBlockedReasonChanged(uid, blockedReasons);
        } else {
            executor.execute(PooledLambda.obtainRunnable(NetworkPolicyManager$$ExternalSyntheticLambda0.INSTANCE, callback, Integer.valueOf(uid), Integer.valueOf(blockedReasons)).recycleOnUse());
        }
    }

    /* loaded from: classes2.dex */
    public static class SubscriptionCallback {
        public void onSubscriptionOverride(int subId, int overrideMask, int overrideValue, int[] networkTypes) {
        }

        public void onSubscriptionPlansChanged(int subId, SubscriptionPlan[] plans) {
        }
    }

    /* loaded from: classes2.dex */
    public class SubscriptionCallbackProxy extends Listener {
        private final SubscriptionCallback mCallback;

        SubscriptionCallbackProxy(SubscriptionCallback callback) {
            this.mCallback = callback;
        }

        @Override // android.net.NetworkPolicyManager.Listener, android.net.INetworkPolicyListener
        public void onSubscriptionOverride(int subId, int overrideMask, int overrideValue, int[] networkTypes) {
            this.mCallback.onSubscriptionOverride(subId, overrideMask, overrideValue, networkTypes);
        }

        @Override // android.net.NetworkPolicyManager.Listener, android.net.INetworkPolicyListener
        public void onSubscriptionPlansChanged(int subId, SubscriptionPlan[] plans) {
            this.mCallback.onSubscriptionPlansChanged(subId, plans);
        }
    }

    /* loaded from: classes2.dex */
    public static class Listener extends INetworkPolicyListener.Stub {
        @Override // android.net.INetworkPolicyListener
        public void onUidRulesChanged(int uid, int uidRules) {
        }

        @Override // android.net.INetworkPolicyListener
        public void onMeteredIfacesChanged(String[] meteredIfaces) {
        }

        @Override // android.net.INetworkPolicyListener
        public void onRestrictBackgroundChanged(boolean restrictBackground) {
        }

        @Override // android.net.INetworkPolicyListener
        public void onUidPoliciesChanged(int uid, int uidPolicies) {
        }

        @Override // android.net.INetworkPolicyListener
        public void onSubscriptionOverride(int subId, int overrideMask, int overrideValue, int[] networkTypes) {
        }

        @Override // android.net.INetworkPolicyListener
        public void onSubscriptionPlansChanged(int subId, SubscriptionPlan[] plans) {
        }

        @Override // android.net.INetworkPolicyListener
        public void onBlockedReasonChanged(int uid, int oldBlockedReasons, int newBlockedReasons) {
        }
    }
}
