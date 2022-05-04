package android.telephony;

import android.compat.Compatibility;
import android.content.Context;
import android.os.Binder;
import android.os.Build;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyCallback;
import android.telephony.TelephonyRegistryManager;
import android.telephony.emergency.EmergencyNumber;
import android.telephony.ims.ImsReasonInfo;
import android.util.ArraySet;
import android.util.Log;
import com.android.internal.telephony.IOnSubscriptionsChangedListener;
import com.android.internal.telephony.ITelephonyRegistry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
/* loaded from: classes3.dex */
public class TelephonyRegistryManager {
    private static final boolean IS_DEBUG_BUILD;
    private static final long LISTEN_CODE_CHANGE = 147600208;
    public static final int SIM_ACTIVATION_TYPE_DATA = 1;
    public static final int SIM_ACTIVATION_TYPE_VOICE = 0;
    private static final String TAG = "TelephonyRegistryManager";
    private static ITelephonyRegistry sRegistry;
    private final Context mContext;
    private final Map<SubscriptionManager.OnSubscriptionsChangedListener, IOnSubscriptionsChangedListener> mSubscriptionChangedListenerMap = new HashMap();
    private final Map<SubscriptionManager.OnOpportunisticSubscriptionsChangedListener, IOnSubscriptionsChangedListener> mOpportunisticSubscriptionChangedListenerMap = new HashMap();

    static {
        IS_DEBUG_BUILD = Build.TYPE.equals("eng") || Build.TYPE.equals("userdebug");
    }

    public TelephonyRegistryManager(Context context) {
        this.mContext = context;
        if (sRegistry == null) {
            sRegistry = ITelephonyRegistry.Stub.asInterface(ServiceManager.getService("telephony.registry"));
        }
    }

    public void addOnSubscriptionsChangedListener(SubscriptionManager.OnSubscriptionsChangedListener listener, Executor executor) {
        if (this.mSubscriptionChangedListenerMap.get(listener) != null) {
            Log.d(TAG, "addOnSubscriptionsChangedListener listener already present");
            return;
        }
        IOnSubscriptionsChangedListener callback = new AnonymousClass1(executor, listener);
        this.mSubscriptionChangedListenerMap.put(listener, callback);
        try {
            sRegistry.addOnSubscriptionsChangedListener(this.mContext.getOpPackageName(), this.mContext.getAttributionTag(), callback);
        } catch (RemoteException e) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: android.telephony.TelephonyRegistryManager$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass1 extends IOnSubscriptionsChangedListener.Stub {
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ SubscriptionManager.OnSubscriptionsChangedListener val$listener;

        AnonymousClass1(Executor executor, SubscriptionManager.OnSubscriptionsChangedListener onSubscriptionsChangedListener) {
            this.val$executor = executor;
            this.val$listener = onSubscriptionsChangedListener;
        }

        @Override // com.android.internal.telephony.IOnSubscriptionsChangedListener
        public void onSubscriptionsChanged() {
            long identity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final SubscriptionManager.OnSubscriptionsChangedListener onSubscriptionsChangedListener = this.val$listener;
                executor.execute(new Runnable() { // from class: android.telephony.TelephonyRegistryManager$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        TelephonyRegistryManager.AnonymousClass1.lambda$onSubscriptionsChanged$0(SubscriptionManager.OnSubscriptionsChangedListener.this);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(identity);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onSubscriptionsChanged$0(SubscriptionManager.OnSubscriptionsChangedListener listener) {
            long durationUptime = 0;
            if (TelephonyRegistryManager.IS_DEBUG_BUILD) {
                durationUptime = SystemClock.uptimeMillis();
            }
            listener.onSubscriptionsChanged();
            if (TelephonyRegistryManager.IS_DEBUG_BUILD) {
                long durationUptime2 = SystemClock.uptimeMillis() - durationUptime;
                if (durationUptime2 > 200) {
                    Log.d(TelephonyRegistryManager.TAG, "Cost " + durationUptime2 + "ms in listener = " + listener);
                }
            }
        }
    }

    public void removeOnSubscriptionsChangedListener(SubscriptionManager.OnSubscriptionsChangedListener listener) {
        if (this.mSubscriptionChangedListenerMap.get(listener) != null) {
            try {
                sRegistry.removeOnSubscriptionsChangedListener(this.mContext.getOpPackageName(), this.mSubscriptionChangedListenerMap.get(listener));
                this.mSubscriptionChangedListenerMap.remove(listener);
            } catch (RemoteException e) {
            }
        }
    }

    public void addOnOpportunisticSubscriptionsChangedListener(SubscriptionManager.OnOpportunisticSubscriptionsChangedListener listener, Executor executor) {
        if (this.mOpportunisticSubscriptionChangedListenerMap.get(listener) != null) {
            Log.d(TAG, "addOnOpportunisticSubscriptionsChangedListener listener already present");
            return;
        }
        IOnSubscriptionsChangedListener callback = new AnonymousClass2(executor, listener);
        this.mOpportunisticSubscriptionChangedListenerMap.put(listener, callback);
        try {
            sRegistry.addOnOpportunisticSubscriptionsChangedListener(this.mContext.getOpPackageName(), this.mContext.getAttributionTag(), callback);
        } catch (RemoteException e) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: android.telephony.TelephonyRegistryManager$2  reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass2 extends IOnSubscriptionsChangedListener.Stub {
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ SubscriptionManager.OnOpportunisticSubscriptionsChangedListener val$listener;

        AnonymousClass2(Executor executor, SubscriptionManager.OnOpportunisticSubscriptionsChangedListener onOpportunisticSubscriptionsChangedListener) {
            this.val$executor = executor;
            this.val$listener = onOpportunisticSubscriptionsChangedListener;
        }

        @Override // com.android.internal.telephony.IOnSubscriptionsChangedListener
        public void onSubscriptionsChanged() {
            long identity = Binder.clearCallingIdentity();
            try {
                Log.d(TelephonyRegistryManager.TAG, "onOpportunisticSubscriptionsChanged callback received.");
                Executor executor = this.val$executor;
                final SubscriptionManager.OnOpportunisticSubscriptionsChangedListener onOpportunisticSubscriptionsChangedListener = this.val$listener;
                executor.execute(new Runnable() { // from class: android.telephony.TelephonyRegistryManager$2$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        SubscriptionManager.OnOpportunisticSubscriptionsChangedListener.this.onOpportunisticSubscriptionsChanged();
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(identity);
            }
        }
    }

    public void removeOnOpportunisticSubscriptionsChangedListener(SubscriptionManager.OnOpportunisticSubscriptionsChangedListener listener) {
        if (this.mOpportunisticSubscriptionChangedListenerMap.get(listener) != null) {
            try {
                sRegistry.removeOnSubscriptionsChangedListener(this.mContext.getOpPackageName(), this.mOpportunisticSubscriptionChangedListenerMap.get(listener));
                this.mOpportunisticSubscriptionChangedListenerMap.remove(listener);
            } catch (RemoteException e) {
            }
        }
    }

    public void listenFromListener(int subId, String pkg, String featureId, PhoneStateListener listener, int events, boolean notifyNow) {
        if (listener != null) {
            try {
                int[] eventsList = getEventsFromBitmask(events).stream().mapToInt(TelephonyRegistryManager$$ExternalSyntheticLambda0.INSTANCE).toArray();
                if (Compatibility.isChangeEnabled((long) LISTEN_CODE_CHANGE)) {
                    listener.mSubId = Integer.valueOf(eventsList.length == 0 ? -1 : subId);
                } else if (listener.mSubId != null) {
                    subId = listener.mSubId.intValue();
                }
                sRegistry.listenWithEventList(subId, pkg, featureId, listener.callback, eventsList, notifyNow);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            throw new IllegalStateException("telephony service is null.");
        }
    }

    private void listenFromCallback(int subId, String pkg, String featureId, TelephonyCallback telephonyCallback, int[] events, boolean notifyNow) {
        try {
            sRegistry.listenWithEventList(subId, pkg, featureId, telephonyCallback.callback, events, notifyNow);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void notifyCarrierNetworkChange(boolean active) {
        try {
            sRegistry.notifyCarrierNetworkChange(active);
        } catch (RemoteException e) {
        }
    }

    public void notifyCallStateChanged(int slotIndex, int subId, int state, String incomingNumber) {
        try {
            sRegistry.notifyCallState(slotIndex, subId, state, incomingNumber);
        } catch (RemoteException e) {
        }
    }

    public void notifyCallStateChangedForAllSubscriptions(int state, String incomingNumber) {
        try {
            sRegistry.notifyCallStateForAllSubs(state, incomingNumber);
        } catch (RemoteException e) {
        }
    }

    public void notifySubscriptionInfoChanged() {
        try {
            sRegistry.notifySubscriptionInfoChanged();
        } catch (RemoteException e) {
        }
    }

    public void notifyOpportunisticSubscriptionInfoChanged() {
        try {
            sRegistry.notifyOpportunisticSubscriptionInfoChanged();
        } catch (RemoteException e) {
        }
    }

    public void notifyServiceStateChanged(int slotIndex, int subId, ServiceState state) {
        try {
            sRegistry.notifyServiceStateForPhoneId(slotIndex, subId, state);
        } catch (RemoteException e) {
        }
    }

    public void notifySignalStrengthChanged(int slotIndex, int subId, SignalStrength signalStrength) {
        try {
            sRegistry.notifySignalStrengthForPhoneId(slotIndex, subId, signalStrength);
        } catch (RemoteException e) {
        }
    }

    public void notifyMessageWaitingChanged(int slotIndex, int subId, boolean msgWaitingInd) {
        try {
            sRegistry.notifyMessageWaitingChangedForPhoneId(slotIndex, subId, msgWaitingInd);
        } catch (RemoteException e) {
        }
    }

    public void notifyCallForwardingChanged(int subId, boolean callForwardInd) {
        try {
            sRegistry.notifyCallForwardingChangedForSubscriber(subId, callForwardInd);
        } catch (RemoteException e) {
        }
    }

    public void notifyDataActivityChanged(int subId, int dataActivityType) {
        try {
            sRegistry.notifyDataActivityForSubscriber(subId, dataActivityType);
        } catch (RemoteException e) {
        }
    }

    public void notifyDataConnectionForSubscriber(int slotIndex, int subId, PreciseDataConnectionState preciseState) {
        try {
            sRegistry.notifyDataConnectionForSubscriber(slotIndex, subId, preciseState);
        } catch (RemoteException e) {
        }
    }

    public void notifyCallQualityChanged(int slotIndex, int subId, CallQuality callQuality, int networkType) {
        try {
            sRegistry.notifyCallQualityChanged(callQuality, slotIndex, subId, networkType);
        } catch (RemoteException e) {
        }
    }

    public void notifyEmergencyNumberList(int slotIndex, int subId) {
        try {
            sRegistry.notifyEmergencyNumberList(slotIndex, subId);
        } catch (RemoteException e) {
        }
    }

    public void notifyOutgoingEmergencyCall(int phoneId, int subId, EmergencyNumber emergencyNumber) {
        try {
            sRegistry.notifyOutgoingEmergencyCall(phoneId, subId, emergencyNumber);
        } catch (RemoteException e) {
        }
    }

    public void notifyOutgoingEmergencySms(int phoneId, int subId, EmergencyNumber emergencyNumber) {
        try {
            sRegistry.notifyOutgoingEmergencySms(phoneId, subId, emergencyNumber);
        } catch (RemoteException e) {
        }
    }

    public void notifyRadioPowerStateChanged(int slotIndex, int subId, int radioPowerState) {
        try {
            sRegistry.notifyRadioPowerStateChanged(slotIndex, subId, radioPowerState);
        } catch (RemoteException e) {
        }
    }

    public void notifyPhoneCapabilityChanged(PhoneCapability phoneCapability) {
        try {
            sRegistry.notifyPhoneCapabilityChanged(phoneCapability);
        } catch (RemoteException e) {
        }
    }

    public void notifyDataActivationStateChanged(int slotIndex, int subId, int activationState) {
        try {
            sRegistry.notifySimActivationStateChangedForPhoneId(slotIndex, subId, 1, activationState);
        } catch (RemoteException e) {
        }
    }

    public void notifyVoiceActivationStateChanged(int slotIndex, int subId, int activationState) {
        try {
            sRegistry.notifySimActivationStateChangedForPhoneId(slotIndex, subId, 0, activationState);
        } catch (RemoteException e) {
        }
    }

    public void notifyUserMobileDataStateChanged(int slotIndex, int subId, boolean state) {
        try {
            sRegistry.notifyUserMobileDataStateChangedForPhoneId(slotIndex, subId, state);
        } catch (RemoteException e) {
        }
    }

    public void notifyDisplayInfoChanged(int slotIndex, int subscriptionId, TelephonyDisplayInfo telephonyDisplayInfo) {
        try {
            sRegistry.notifyDisplayInfoChanged(slotIndex, subscriptionId, telephonyDisplayInfo);
        } catch (RemoteException e) {
        }
    }

    public void notifyImsDisconnectCause(int subId, ImsReasonInfo imsReasonInfo) {
        try {
            sRegistry.notifyImsDisconnectCause(subId, imsReasonInfo);
        } catch (RemoteException e) {
        }
    }

    public void notifySrvccStateChanged(int subId, int state) {
        try {
            sRegistry.notifySrvccStateChanged(subId, state);
        } catch (RemoteException e) {
        }
    }

    public void notifyPreciseCallState(int slotIndex, int subId, int ringCallPreciseState, int foregroundCallPreciseState, int backgroundCallPreciseState) {
        try {
            sRegistry.notifyPreciseCallState(slotIndex, subId, ringCallPreciseState, foregroundCallPreciseState, backgroundCallPreciseState);
        } catch (RemoteException e) {
        }
    }

    public void notifyDisconnectCause(int slotIndex, int subId, int cause, int preciseCause) {
        try {
            sRegistry.notifyDisconnectCause(slotIndex, subId, cause, preciseCause);
        } catch (RemoteException e) {
        }
    }

    public void notifyCellLocation(int subId, CellIdentity cellLocation) {
        try {
            sRegistry.notifyCellLocationForSubscriber(subId, cellLocation);
        } catch (RemoteException e) {
        }
    }

    public void notifyCellInfoChanged(int subId, List<CellInfo> cellInfo) {
        try {
            sRegistry.notifyCellInfoForSubscriber(subId, cellInfo);
        } catch (RemoteException e) {
        }
    }

    public void notifyActiveDataSubIdChanged(int activeDataSubId) {
        try {
            sRegistry.notifyActiveDataSubIdChanged(activeDataSubId);
        } catch (RemoteException e) {
        }
    }

    public void notifyRegistrationFailed(int slotIndex, int subId, CellIdentity cellIdentity, String chosenPlmn, int domain, int causeCode, int additionalCauseCode) {
        try {
            sRegistry.notifyRegistrationFailed(slotIndex, subId, cellIdentity, chosenPlmn, domain, causeCode, additionalCauseCode);
        } catch (RemoteException e) {
        }
    }

    public void notifyBarringInfoChanged(int slotIndex, int subId, BarringInfo barringInfo) {
        try {
            sRegistry.notifyBarringInfoChanged(slotIndex, subId, barringInfo);
        } catch (RemoteException e) {
        }
    }

    public void notifyPhysicalChannelConfigForSubscriber(int slotIndex, int subId, List<PhysicalChannelConfig> configs) {
        try {
            sRegistry.notifyPhysicalChannelConfigForSubscriber(slotIndex, subId, configs);
        } catch (RemoteException e) {
        }
    }

    public void notifyDataEnabled(int slotIndex, int subId, boolean enabled, int reason) {
        try {
            sRegistry.notifyDataEnabled(slotIndex, subId, enabled, reason);
        } catch (RemoteException e) {
        }
    }

    public void notifyAllowedNetworkTypesChanged(int slotIndex, int subId, int reason, long allowedNetworkType) {
        try {
            sRegistry.notifyAllowedNetworkTypesChanged(slotIndex, subId, reason, allowedNetworkType);
        } catch (RemoteException e) {
        }
    }

    public void notifyLinkCapacityEstimateChanged(int slotIndex, int subId, List<LinkCapacityEstimate> linkCapacityEstimateList) {
        try {
            sRegistry.notifyLinkCapacityEstimateChanged(slotIndex, subId, linkCapacityEstimateList);
        } catch (RemoteException e) {
        }
    }

    public Set<Integer> getEventsFromCallback(TelephonyCallback telephonyCallback) {
        Set<Integer> eventList = new ArraySet<>();
        if (telephonyCallback instanceof TelephonyCallback.ServiceStateListener) {
            eventList.add(1);
        }
        if (telephonyCallback instanceof TelephonyCallback.MessageWaitingIndicatorListener) {
            eventList.add(3);
        }
        if (telephonyCallback instanceof TelephonyCallback.CallForwardingIndicatorListener) {
            eventList.add(4);
        }
        if (telephonyCallback instanceof TelephonyCallback.CellLocationListener) {
            eventList.add(5);
        }
        if (telephonyCallback instanceof TelephonyCallback.CallStateListener) {
            eventList.add(6);
        }
        if (telephonyCallback instanceof TelephonyCallback.DataConnectionStateListener) {
            eventList.add(7);
        }
        if (telephonyCallback instanceof TelephonyCallback.DataActivityListener) {
            eventList.add(8);
        }
        if (telephonyCallback instanceof TelephonyCallback.SignalStrengthsListener) {
            eventList.add(9);
        }
        if (telephonyCallback instanceof TelephonyCallback.CellInfoListener) {
            eventList.add(11);
        }
        if (telephonyCallback instanceof TelephonyCallback.PreciseCallStateListener) {
            eventList.add(12);
        }
        if (telephonyCallback instanceof TelephonyCallback.CallDisconnectCauseListener) {
            eventList.add(26);
        }
        if (telephonyCallback instanceof TelephonyCallback.ImsCallDisconnectCauseListener) {
            eventList.add(28);
        }
        if (telephonyCallback instanceof TelephonyCallback.PreciseDataConnectionStateListener) {
            eventList.add(13);
        }
        if (telephonyCallback instanceof TelephonyCallback.SrvccStateListener) {
            eventList.add(16);
        }
        if (telephonyCallback instanceof TelephonyCallback.VoiceActivationStateListener) {
            eventList.add(18);
        }
        if (telephonyCallback instanceof TelephonyCallback.DataActivationStateListener) {
            eventList.add(19);
        }
        if (telephonyCallback instanceof TelephonyCallback.UserMobileDataStateListener) {
            eventList.add(20);
        }
        if (telephonyCallback instanceof TelephonyCallback.DisplayInfoListener) {
            eventList.add(21);
        }
        if (telephonyCallback instanceof TelephonyCallback.EmergencyNumberListListener) {
            eventList.add(25);
        }
        if (telephonyCallback instanceof TelephonyCallback.OutgoingEmergencyCallListener) {
            eventList.add(29);
        }
        if (telephonyCallback instanceof TelephonyCallback.OutgoingEmergencySmsListener) {
            eventList.add(30);
        }
        if (telephonyCallback instanceof TelephonyCallback.PhoneCapabilityListener) {
            eventList.add(22);
        }
        if (telephonyCallback instanceof TelephonyCallback.ActiveDataSubscriptionIdListener) {
            eventList.add(23);
        }
        if (telephonyCallback instanceof TelephonyCallback.RadioPowerStateListener) {
            eventList.add(24);
        }
        if (telephonyCallback instanceof TelephonyCallback.CarrierNetworkListener) {
            eventList.add(17);
        }
        if (telephonyCallback instanceof TelephonyCallback.RegistrationFailedListener) {
            eventList.add(31);
        }
        if (telephonyCallback instanceof TelephonyCallback.CallAttributesListener) {
            eventList.add(27);
        }
        if (telephonyCallback instanceof TelephonyCallback.BarringInfoListener) {
            eventList.add(32);
        }
        if (telephonyCallback instanceof TelephonyCallback.PhysicalChannelConfigListener) {
            eventList.add(33);
        }
        if (telephonyCallback instanceof TelephonyCallback.DataEnabledListener) {
            eventList.add(34);
        }
        if (telephonyCallback instanceof TelephonyCallback.AllowedNetworkTypesListener) {
            eventList.add(35);
        }
        if (telephonyCallback instanceof TelephonyCallback.LinkCapacityEstimateChangedListener) {
            eventList.add(37);
        }
        return eventList;
    }

    private Set<Integer> getEventsFromBitmask(int eventMask) {
        Set<Integer> eventList = new ArraySet<>();
        if ((eventMask & 1) != 0) {
            eventList.add(1);
        }
        if ((eventMask & 2) != 0) {
            eventList.add(2);
        }
        if ((eventMask & 4) != 0) {
            eventList.add(3);
        }
        if ((eventMask & 8) != 0) {
            eventList.add(4);
        }
        if ((eventMask & 16) != 0) {
            eventList.add(5);
        }
        if ((eventMask & 32) != 0) {
            eventList.add(36);
        }
        if ((eventMask & 64) != 0) {
            eventList.add(7);
        }
        if ((eventMask & 128) != 0) {
            eventList.add(8);
        }
        if ((eventMask & 256) != 0) {
            eventList.add(9);
        }
        if ((eventMask & 512) != 0) {
            eventList.add(10);
        }
        if ((eventMask & 1024) != 0) {
            eventList.add(11);
        }
        if ((eventMask & 2048) != 0) {
            eventList.add(12);
        }
        if ((eventMask & 4096) != 0) {
            eventList.add(13);
        }
        if ((eventMask & 8192) != 0) {
            eventList.add(14);
        }
        if ((32768 & eventMask) != 0) {
            eventList.add(15);
        }
        if ((eventMask & 16384) != 0) {
            eventList.add(16);
        }
        if ((65536 & eventMask) != 0) {
            eventList.add(17);
        }
        if ((131072 & eventMask) != 0) {
            eventList.add(18);
        }
        if ((262144 & eventMask) != 0) {
            eventList.add(19);
        }
        if ((524288 & eventMask) != 0) {
            eventList.add(20);
        }
        if ((1048576 & eventMask) != 0) {
            eventList.add(21);
        }
        if ((2097152 & eventMask) != 0) {
            eventList.add(22);
        }
        if ((4194304 & eventMask) != 0) {
            eventList.add(23);
        }
        if ((8388608 & eventMask) != 0) {
            eventList.add(24);
        }
        if ((16777216 & eventMask) != 0) {
            eventList.add(25);
        }
        if ((33554432 & eventMask) != 0) {
            eventList.add(26);
        }
        if ((67108864 & eventMask) != 0) {
            eventList.add(27);
        }
        if ((134217728 & eventMask) != 0) {
            eventList.add(28);
        }
        if ((268435456 & eventMask) != 0) {
            eventList.add(29);
        }
        if ((536870912 & eventMask) != 0) {
            eventList.add(30);
        }
        if ((1073741824 & eventMask) != 0) {
            eventList.add(31);
        }
        if ((Integer.MIN_VALUE & eventMask) != 0) {
            eventList.add(32);
        }
        return eventList;
    }

    public void registerTelephonyCallback(Executor executor, int subId, String pkgName, String attributionTag, TelephonyCallback callback, boolean notifyNow) {
        if (callback != null) {
            callback.init(executor);
            listenFromCallback(subId, pkgName, attributionTag, callback, getEventsFromCallback(callback).stream().mapToInt(TelephonyRegistryManager$$ExternalSyntheticLambda1.INSTANCE).toArray(), notifyNow);
            return;
        }
        throw new IllegalStateException("telephony service is null.");
    }

    public void unregisterTelephonyCallback(int subId, String pkgName, String attributionTag, TelephonyCallback callback, boolean notifyNow) {
        listenFromCallback(subId, pkgName, attributionTag, callback, new int[0], notifyNow);
    }
}
