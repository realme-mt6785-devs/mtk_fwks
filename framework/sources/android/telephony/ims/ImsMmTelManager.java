package android.telephony.ims;

import android.annotation.SystemApi;
import android.os.Binder;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import android.telephony.BinderCacheManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyFrameworkInitializer;
import android.telephony.ims.ImsMmTelManager;
import android.telephony.ims.RegistrationManager;
import android.telephony.ims.aidl.IImsCapabilityCallback;
import android.telephony.ims.feature.MmTelFeature;
import android.util.Log;
import com.android.internal.telephony.IIntegerConsumer;
import com.android.internal.telephony.ITelephony;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
/* loaded from: classes3.dex */
public class ImsMmTelManager implements RegistrationManager {
    private static final String TAG = "ImsMmTelManager";
    public static final int WIFI_MODE_CELLULAR_PREFERRED = 1;
    public static final int WIFI_MODE_WIFI_ONLY = 0;
    public static final int WIFI_MODE_WIFI_PREFERRED = 2;
    private final BinderCacheManager<ITelephony> mBinderCache;
    private final int mSubId;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface WiFiCallingMode {
    }

    @SystemApi
    @Deprecated
    /* loaded from: classes3.dex */
    public static class RegistrationCallback extends RegistrationManager.RegistrationCallback {
        @Override // android.telephony.ims.RegistrationManager.RegistrationCallback
        public void onRegistered(int imsTransportType) {
        }

        @Override // android.telephony.ims.RegistrationManager.RegistrationCallback
        public void onRegistering(int imsTransportType) {
        }

        @Override // android.telephony.ims.RegistrationManager.RegistrationCallback
        public void onUnregistered(ImsReasonInfo info) {
        }

        @Override // android.telephony.ims.RegistrationManager.RegistrationCallback
        public void onTechnologyChangeFailed(int imsTransportType, ImsReasonInfo info) {
        }
    }

    /* loaded from: classes3.dex */
    public static class CapabilityCallback {
        private final CapabilityBinder mBinder = new CapabilityBinder(this);

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class CapabilityBinder extends IImsCapabilityCallback.Stub {
            private Executor mExecutor;
            private final CapabilityCallback mLocalCallback;

            CapabilityBinder(CapabilityCallback c) {
                this.mLocalCallback = c;
            }

            @Override // android.telephony.ims.aidl.IImsCapabilityCallback
            public void onCapabilitiesStatusChanged(final int config) {
                if (this.mLocalCallback != null) {
                    long callingIdentity = Binder.clearCallingIdentity();
                    try {
                        this.mExecutor.execute(new Runnable() { // from class: android.telephony.ims.ImsMmTelManager$CapabilityCallback$CapabilityBinder$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                ImsMmTelManager.CapabilityCallback.CapabilityBinder.this.lambda$onCapabilitiesStatusChanged$0$ImsMmTelManager$CapabilityCallback$CapabilityBinder(config);
                            }
                        });
                    } finally {
                        restoreCallingIdentity(callingIdentity);
                    }
                }
            }

            public /* synthetic */ void lambda$onCapabilitiesStatusChanged$0$ImsMmTelManager$CapabilityCallback$CapabilityBinder(int config) {
                this.mLocalCallback.onCapabilitiesStatusChanged(new MmTelFeature.MmTelCapabilities(config));
            }

            @Override // android.telephony.ims.aidl.IImsCapabilityCallback
            public void onQueryCapabilityConfiguration(int capability, int radioTech, boolean isEnabled) {
            }

            @Override // android.telephony.ims.aidl.IImsCapabilityCallback
            public void onChangeCapabilityConfigurationError(int capability, int radioTech, int reason) {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setExecutor(Executor executor) {
                this.mExecutor = executor;
            }
        }

        public void onCapabilitiesStatusChanged(MmTelFeature.MmTelCapabilities capabilities) {
        }

        public final IImsCapabilityCallback getBinder() {
            return this.mBinder;
        }

        public final void setExecutor(Executor executor) {
            this.mBinder.setExecutor(executor);
        }
    }

    @SystemApi
    @Deprecated
    public static ImsMmTelManager createForSubscriptionId(int subId) {
        if (SubscriptionManager.isValidSubscriptionId(subId)) {
            return new ImsMmTelManager(subId, new BinderCacheManager(ImsMmTelManager$$ExternalSyntheticLambda0.INSTANCE));
        }
        throw new IllegalArgumentException("Invalid subscription ID");
    }

    public ImsMmTelManager(int subId, BinderCacheManager<ITelephony> binderCache) {
        this.mSubId = subId;
        this.mBinderCache = binderCache;
    }

    @SystemApi
    @Deprecated
    public void registerImsRegistrationCallback(Executor executor, RegistrationCallback c) throws ImsException {
        if (c == null) {
            throw new IllegalArgumentException("Must include a non-null RegistrationCallback.");
        } else if (executor != null) {
            c.setExecutor(executor);
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                try {
                    iTelephony.registerImsRegistrationCallback(this.mSubId, c.getBinder());
                } catch (RemoteException | IllegalStateException e) {
                    throw new ImsException(e.getMessage(), 1);
                } catch (ServiceSpecificException e2) {
                    if (e2.errorCode == 3) {
                        throw new IllegalArgumentException(e2.getMessage());
                    }
                    throw new ImsException(e2.getMessage(), e2.errorCode);
                }
            } else {
                throw new ImsException("Could not find Telephony Service.", 1);
            }
        } else {
            throw new IllegalArgumentException("Must include a non-null Executor.");
        }
    }

    @Override // android.telephony.ims.RegistrationManager
    public void registerImsRegistrationCallback(Executor executor, RegistrationManager.RegistrationCallback c) throws ImsException {
        if (c == null) {
            throw new IllegalArgumentException("Must include a non-null RegistrationCallback.");
        } else if (executor != null) {
            c.setExecutor(executor);
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                try {
                    iTelephony.registerImsRegistrationCallback(this.mSubId, c.getBinder());
                } catch (RemoteException | IllegalStateException e) {
                    throw new ImsException(e.getMessage(), 1);
                } catch (ServiceSpecificException e2) {
                    throw new ImsException(e2.getMessage(), e2.errorCode);
                }
            } else {
                throw new ImsException("Could not find Telephony Service.", 1);
            }
        } else {
            throw new IllegalArgumentException("Must include a non-null Executor.");
        }
    }

    @SystemApi
    @Deprecated
    public void unregisterImsRegistrationCallback(RegistrationCallback c) {
        if (c != null) {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                try {
                    iTelephony.unregisterImsRegistrationCallback(this.mSubId, c.getBinder());
                } catch (RemoteException e) {
                    throw e.rethrowAsRuntimeException();
                }
            } else {
                throw new RuntimeException("Could not find Telephony Service.");
            }
        } else {
            throw new IllegalArgumentException("Must include a non-null RegistrationCallback.");
        }
    }

    @Override // android.telephony.ims.RegistrationManager
    public void unregisterImsRegistrationCallback(RegistrationManager.RegistrationCallback c) {
        if (c != null) {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                try {
                    iTelephony.unregisterImsRegistrationCallback(this.mSubId, c.getBinder());
                } catch (RemoteException e) {
                    throw e.rethrowAsRuntimeException();
                }
            } else {
                throw new RuntimeException("Could not find Telephony Service.");
            }
        } else {
            throw new IllegalArgumentException("Must include a non-null RegistrationCallback.");
        }
    }

    @Override // android.telephony.ims.RegistrationManager
    @SystemApi
    public void getRegistrationState(Executor executor, final Consumer<Integer> stateCallback) {
        if (stateCallback == null) {
            throw new IllegalArgumentException("Must include a non-null callback.");
        } else if (executor != null) {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                try {
                    iTelephony.getImsMmTelRegistrationState(this.mSubId, new AnonymousClass1(executor, stateCallback));
                } catch (RemoteException | ServiceSpecificException e) {
                    Log.w(TAG, "Error getting registration state: " + e);
                    executor.execute(new Runnable() { // from class: android.telephony.ims.ImsMmTelManager$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            stateCallback.accept(0);
                        }
                    });
                }
            } else {
                throw new RuntimeException("Could not find Telephony Service.");
            }
        } else {
            throw new IllegalArgumentException("Must include a non-null Executor.");
        }
    }

    /* renamed from: android.telephony.ims.ImsMmTelManager$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass1 extends IIntegerConsumer.Stub {
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ Consumer val$stateCallback;

        AnonymousClass1(Executor executor, Consumer consumer) {
            this.val$executor = executor;
            this.val$stateCallback = consumer;
        }

        @Override // com.android.internal.telephony.IIntegerConsumer
        public void accept(final int result) {
            long identity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final Consumer consumer = this.val$stateCallback;
                executor.execute(new Runnable() { // from class: android.telephony.ims.ImsMmTelManager$1$$ExternalSyntheticLambda0
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

    @Override // android.telephony.ims.RegistrationManager
    public void getRegistrationTransportType(Executor executor, final Consumer<Integer> transportTypeCallback) {
        if (transportTypeCallback == null) {
            throw new IllegalArgumentException("Must include a non-null callback.");
        } else if (executor != null) {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                try {
                    iTelephony.getImsMmTelRegistrationTransportType(this.mSubId, new AnonymousClass2(executor, transportTypeCallback));
                } catch (RemoteException | ServiceSpecificException e) {
                    Log.w(TAG, "Error getting transport type: " + e);
                    executor.execute(new Runnable() { // from class: android.telephony.ims.ImsMmTelManager$$ExternalSyntheticLambda2
                        @Override // java.lang.Runnable
                        public final void run() {
                            transportTypeCallback.accept(-1);
                        }
                    });
                }
            } else {
                throw new RuntimeException("Could not find Telephony Service.");
            }
        } else {
            throw new IllegalArgumentException("Must include a non-null Executor.");
        }
    }

    /* renamed from: android.telephony.ims.ImsMmTelManager$2  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass2 extends IIntegerConsumer.Stub {
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ Consumer val$transportTypeCallback;

        AnonymousClass2(Executor executor, Consumer consumer) {
            this.val$executor = executor;
            this.val$transportTypeCallback = consumer;
        }

        @Override // com.android.internal.telephony.IIntegerConsumer
        public void accept(final int result) {
            long identity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final Consumer consumer = this.val$transportTypeCallback;
                executor.execute(new Runnable() { // from class: android.telephony.ims.ImsMmTelManager$2$$ExternalSyntheticLambda0
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

    public void registerMmTelCapabilityCallback(Executor executor, CapabilityCallback c) throws ImsException {
        if (c == null) {
            throw new IllegalArgumentException("Must include a non-null RegistrationCallback.");
        } else if (executor != null) {
            c.setExecutor(executor);
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                try {
                    iTelephony.registerMmTelCapabilityCallback(this.mSubId, c.getBinder());
                } catch (RemoteException e) {
                    throw e.rethrowAsRuntimeException();
                } catch (ServiceSpecificException e2) {
                    throw new ImsException(e2.getMessage(), e2.errorCode);
                } catch (IllegalStateException e3) {
                    throw new ImsException(e3.getMessage(), 1);
                }
            } else {
                throw new ImsException("Could not find Telephony Service.", 1);
            }
        } else {
            throw new IllegalArgumentException("Must include a non-null Executor.");
        }
    }

    public void unregisterMmTelCapabilityCallback(CapabilityCallback c) {
        if (c != null) {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                try {
                    iTelephony.unregisterMmTelCapabilityCallback(this.mSubId, c.getBinder());
                } catch (RemoteException e) {
                    throw e.rethrowAsRuntimeException();
                }
            } else {
                throw new RuntimeException("Could not find Telephony Service.");
            }
        } else {
            throw new IllegalArgumentException("Must include a non-null RegistrationCallback.");
        }
    }

    public boolean isAdvancedCallingSettingEnabled() {
        ITelephony iTelephony = getITelephony();
        if (iTelephony != null) {
            try {
                return iTelephony.isAdvancedCallingSettingEnabled(this.mSubId);
            } catch (RemoteException e) {
                throw e.rethrowAsRuntimeException();
            } catch (ServiceSpecificException e2) {
                if (e2.errorCode == 3) {
                    throw new IllegalArgumentException(e2.getMessage());
                }
                throw new RuntimeException(e2.getMessage());
            }
        } else {
            throw new RuntimeException("Could not find Telephony Service.");
        }
    }

    @SystemApi
    public void setAdvancedCallingSettingEnabled(boolean isEnabled) {
        ITelephony iTelephony = getITelephony();
        if (iTelephony != null) {
            try {
                iTelephony.setAdvancedCallingSettingEnabled(this.mSubId, isEnabled);
            } catch (RemoteException e) {
                throw e.rethrowAsRuntimeException();
            } catch (ServiceSpecificException e2) {
                if (e2.errorCode == 3) {
                    throw new IllegalArgumentException(e2.getMessage());
                }
                throw new RuntimeException(e2.getMessage());
            }
        } else {
            throw new RuntimeException("Could not find Telephony Service.");
        }
    }

    @SystemApi
    public boolean isCapable(int capability, int imsRegTech) {
        ITelephony iTelephony = getITelephony();
        if (iTelephony != null) {
            try {
                return iTelephony.isCapable(this.mSubId, capability, imsRegTech);
            } catch (RemoteException e) {
                throw e.rethrowAsRuntimeException();
            }
        } else {
            throw new RuntimeException("Could not find Telephony Service.");
        }
    }

    @SystemApi
    public boolean isAvailable(int capability, int imsRegTech) {
        ITelephony iTelephony = getITelephony();
        if (iTelephony != null) {
            try {
                return iTelephony.isAvailable(this.mSubId, capability, imsRegTech);
            } catch (RemoteException e) {
                throw e.rethrowAsRuntimeException();
            }
        } else {
            throw new RuntimeException("Could not find Telephony Service.");
        }
    }

    @SystemApi
    public void isSupported(int capability, int transportType, Executor executor, Consumer<Boolean> callback) throws ImsException {
        if (callback == null) {
            throw new IllegalArgumentException("Must include a non-null Consumer.");
        } else if (executor != null) {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                try {
                    iTelephony.isMmTelCapabilitySupported(this.mSubId, new AnonymousClass3(executor, callback), capability, transportType);
                } catch (RemoteException e) {
                    e.rethrowAsRuntimeException();
                } catch (ServiceSpecificException sse) {
                    throw new ImsException(sse.getMessage(), sse.errorCode);
                }
            } else {
                throw new ImsException("Could not find Telephony Service.", 1);
            }
        } else {
            throw new IllegalArgumentException("Must include a non-null Executor.");
        }
    }

    /* renamed from: android.telephony.ims.ImsMmTelManager$3  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass3 extends IIntegerConsumer.Stub {
        final /* synthetic */ Consumer val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass3(Executor executor, Consumer consumer) {
            this.val$executor = executor;
            this.val$callback = consumer;
        }

        @Override // com.android.internal.telephony.IIntegerConsumer
        public void accept(final int result) {
            long identity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final Consumer consumer = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.ims.ImsMmTelManager$3$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        ImsMmTelManager.AnonymousClass3.lambda$accept$0(consumer, result);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(identity);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$accept$0(Consumer callback, int result) {
            boolean z = true;
            if (result != 1) {
                z = false;
            }
            callback.accept(Boolean.valueOf(z));
        }
    }

    public boolean isVtSettingEnabled() {
        ITelephony iTelephony = getITelephony();
        if (iTelephony != null) {
            try {
                return iTelephony.isVtSettingEnabled(this.mSubId);
            } catch (RemoteException e) {
                throw e.rethrowAsRuntimeException();
            } catch (ServiceSpecificException e2) {
                if (e2.errorCode == 3) {
                    throw new IllegalArgumentException(e2.getMessage());
                }
                throw new RuntimeException(e2.getMessage());
            }
        } else {
            throw new RuntimeException("Could not find Telephony Service.");
        }
    }

    @SystemApi
    public void setVtSettingEnabled(boolean isEnabled) {
        ITelephony iTelephony = getITelephony();
        if (iTelephony != null) {
            try {
                iTelephony.setVtSettingEnabled(this.mSubId, isEnabled);
            } catch (RemoteException e) {
                throw e.rethrowAsRuntimeException();
            } catch (ServiceSpecificException e2) {
                if (e2.errorCode == 3) {
                    throw new IllegalArgumentException(e2.getMessage());
                }
                throw new RuntimeException(e2.getMessage());
            }
        } else {
            throw new RuntimeException("Could not find Telephony Service.");
        }
    }

    public boolean isVoWiFiSettingEnabled() {
        ITelephony iTelephony = getITelephony();
        if (iTelephony != null) {
            try {
                return iTelephony.isVoWiFiSettingEnabled(this.mSubId);
            } catch (RemoteException e) {
                throw e.rethrowAsRuntimeException();
            } catch (ServiceSpecificException e2) {
                if (e2.errorCode == 3) {
                    throw new IllegalArgumentException(e2.getMessage());
                }
                throw new RuntimeException(e2.getMessage());
            }
        } else {
            throw new RuntimeException("Could not find Telephony Service.");
        }
    }

    @SystemApi
    public void setVoWiFiSettingEnabled(boolean isEnabled) {
        ITelephony iTelephony = getITelephony();
        if (iTelephony != null) {
            try {
                iTelephony.setVoWiFiSettingEnabled(this.mSubId, isEnabled);
            } catch (RemoteException e) {
                throw e.rethrowAsRuntimeException();
            } catch (ServiceSpecificException e2) {
                if (e2.errorCode == 3) {
                    throw new IllegalArgumentException(e2.getMessage());
                }
                throw new RuntimeException(e2.getMessage());
            }
        } else {
            throw new RuntimeException("Could not find Telephony Service.");
        }
    }

    public boolean isCrossSimCallingEnabled() throws ImsException {
        ITelephony iTelephony = getITelephony();
        if (iTelephony != null) {
            try {
                return iTelephony.isCrossSimCallingEnabledByUser(this.mSubId);
            } catch (RemoteException e) {
                e.rethrowAsRuntimeException();
                return false;
            } catch (ServiceSpecificException sse) {
                throw new ImsException(sse.getMessage(), sse.errorCode);
            }
        } else {
            throw new ImsException("Could not find Telephony Service.", 1);
        }
    }

    @SystemApi
    public void setCrossSimCallingEnabled(boolean isEnabled) throws ImsException {
        ITelephony iTelephony = getITelephony();
        if (iTelephony != null) {
            try {
                iTelephony.setCrossSimCallingEnabled(this.mSubId, isEnabled);
            } catch (RemoteException e) {
                e.rethrowAsRuntimeException();
            } catch (ServiceSpecificException sse) {
                throw new ImsException(sse.getMessage(), sse.errorCode);
            }
        } else {
            throw new ImsException("Could not find Telephony Service.", 1);
        }
    }

    public boolean isVoWiFiRoamingSettingEnabled() {
        ITelephony iTelephony = getITelephony();
        if (iTelephony != null) {
            try {
                return iTelephony.isVoWiFiRoamingSettingEnabled(this.mSubId);
            } catch (RemoteException e) {
                throw e.rethrowAsRuntimeException();
            } catch (ServiceSpecificException e2) {
                if (e2.errorCode == 3) {
                    throw new IllegalArgumentException(e2.getMessage());
                }
                throw new RuntimeException(e2.getMessage());
            }
        } else {
            throw new RuntimeException("Could not find Telephony Service.");
        }
    }

    @SystemApi
    public void setVoWiFiRoamingSettingEnabled(boolean isEnabled) {
        ITelephony iTelephony = getITelephony();
        if (iTelephony != null) {
            try {
                iTelephony.setVoWiFiRoamingSettingEnabled(this.mSubId, isEnabled);
            } catch (RemoteException e) {
                throw e.rethrowAsRuntimeException();
            } catch (ServiceSpecificException e2) {
                if (e2.errorCode == 3) {
                    throw new IllegalArgumentException(e2.getMessage());
                }
                throw new RuntimeException(e2.getMessage());
            }
        } else {
            throw new RuntimeException("Could not find Telephony Service.");
        }
    }

    @SystemApi
    public void setVoWiFiNonPersistent(boolean isCapable, int mode) {
        ITelephony iTelephony = getITelephony();
        if (iTelephony != null) {
            try {
                iTelephony.setVoWiFiNonPersistent(this.mSubId, isCapable, mode);
            } catch (RemoteException e) {
                throw e.rethrowAsRuntimeException();
            } catch (ServiceSpecificException e2) {
                if (e2.errorCode == 3) {
                    throw new IllegalArgumentException(e2.getMessage());
                }
                throw new RuntimeException(e2.getMessage());
            }
        } else {
            throw new RuntimeException("Could not find Telephony Service.");
        }
    }

    public int getVoWiFiModeSetting() {
        ITelephony iTelephony = getITelephony();
        if (iTelephony != null) {
            try {
                return iTelephony.getVoWiFiModeSetting(this.mSubId);
            } catch (RemoteException e) {
                throw e.rethrowAsRuntimeException();
            } catch (ServiceSpecificException e2) {
                if (e2.errorCode == 3) {
                    throw new IllegalArgumentException(e2.getMessage());
                }
                throw new RuntimeException(e2.getMessage());
            }
        } else {
            throw new RuntimeException("Could not find Telephony Service.");
        }
    }

    @SystemApi
    public void setVoWiFiModeSetting(int mode) {
        ITelephony iTelephony = getITelephony();
        if (iTelephony != null) {
            try {
                iTelephony.setVoWiFiModeSetting(this.mSubId, mode);
            } catch (RemoteException e) {
                throw e.rethrowAsRuntimeException();
            } catch (ServiceSpecificException e2) {
                if (e2.errorCode == 3) {
                    throw new IllegalArgumentException(e2.getMessage());
                }
                throw new RuntimeException(e2.getMessage());
            }
        } else {
            throw new RuntimeException("Could not find Telephony Service.");
        }
    }

    @SystemApi
    public int getVoWiFiRoamingModeSetting() {
        ITelephony iTelephony = getITelephony();
        if (iTelephony != null) {
            try {
                return iTelephony.getVoWiFiRoamingModeSetting(this.mSubId);
            } catch (RemoteException e) {
                throw e.rethrowAsRuntimeException();
            } catch (ServiceSpecificException e2) {
                if (e2.errorCode == 3) {
                    throw new IllegalArgumentException(e2.getMessage());
                }
                throw new RuntimeException(e2.getMessage());
            }
        } else {
            throw new RuntimeException("Could not find Telephony Service.");
        }
    }

    @SystemApi
    public void setVoWiFiRoamingModeSetting(int mode) {
        ITelephony iTelephony = getITelephony();
        if (iTelephony != null) {
            try {
                iTelephony.setVoWiFiRoamingModeSetting(this.mSubId, mode);
            } catch (RemoteException e) {
                throw e.rethrowAsRuntimeException();
            } catch (ServiceSpecificException e2) {
                if (e2.errorCode == 3) {
                    throw new IllegalArgumentException(e2.getMessage());
                }
                throw new RuntimeException(e2.getMessage());
            }
        } else {
            throw new RuntimeException("Could not find Telephony Service.");
        }
    }

    @SystemApi
    public void setRttCapabilitySetting(boolean isEnabled) {
        ITelephony iTelephony = getITelephony();
        if (iTelephony != null) {
            try {
                iTelephony.setRttCapabilitySetting(this.mSubId, isEnabled);
            } catch (RemoteException e) {
                throw e.rethrowAsRuntimeException();
            } catch (ServiceSpecificException e2) {
                if (e2.errorCode == 3) {
                    throw new IllegalArgumentException(e2.getMessage());
                }
                throw new RuntimeException(e2.getMessage());
            }
        } else {
            throw new RuntimeException("Could not find Telephony Service.");
        }
    }

    public boolean isTtyOverVolteEnabled() {
        ITelephony iTelephony = getITelephony();
        if (iTelephony != null) {
            try {
                return iTelephony.isTtyOverVolteEnabled(this.mSubId);
            } catch (RemoteException e) {
                throw e.rethrowAsRuntimeException();
            } catch (ServiceSpecificException e2) {
                if (e2.errorCode == 3) {
                    throw new IllegalArgumentException(e2.getMessage());
                }
                throw new RuntimeException(e2.getMessage());
            }
        } else {
            throw new RuntimeException("Could not find Telephony Service.");
        }
    }

    @SystemApi
    public void getFeatureState(Executor executor, Consumer<Integer> callback) throws ImsException {
        if (executor == null) {
            throw new IllegalArgumentException("Must include a non-null Executor.");
        } else if (callback != null) {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                try {
                    iTelephony.getImsMmTelFeatureState(this.mSubId, new AnonymousClass4(executor, callback));
                } catch (RemoteException e) {
                    e.rethrowAsRuntimeException();
                } catch (ServiceSpecificException sse) {
                    throw new ImsException(sse.getMessage(), sse.errorCode);
                }
            } else {
                throw new ImsException("Could not find Telephony Service.", 1);
            }
        } else {
            throw new IllegalArgumentException("Must include a non-null Consumer.");
        }
    }

    /* renamed from: android.telephony.ims.ImsMmTelManager$4  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass4 extends IIntegerConsumer.Stub {
        final /* synthetic */ Consumer val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass4(Executor executor, Consumer consumer) {
            this.val$executor = executor;
            this.val$callback = consumer;
        }

        @Override // com.android.internal.telephony.IIntegerConsumer
        public void accept(final int result) {
            long identity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final Consumer consumer = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.ims.ImsMmTelManager$4$$ExternalSyntheticLambda0
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

    private ITelephony getITelephony() {
        return this.mBinderCache.getBinder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ITelephony getITelephonyInterface() {
        ITelephony binder = ITelephony.Stub.asInterface(TelephonyFrameworkInitializer.getTelephonyServiceManager().getTelephonyServiceRegisterer().get());
        return binder;
    }
}
