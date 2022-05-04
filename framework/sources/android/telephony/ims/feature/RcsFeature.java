package android.telephony.ims.feature;

import android.annotation.SystemApi;
import android.content.Context;
import android.media.MediaRouter2$$ExternalSyntheticLambda8;
import android.net.Uri;
import android.os.RemoteException;
import android.telephony.ims.aidl.CapabilityExchangeAidlWrapper;
import android.telephony.ims.aidl.ICapabilityExchangeEventListener;
import android.telephony.ims.aidl.IImsCapabilityCallback;
import android.telephony.ims.aidl.IImsRcsFeature;
import android.telephony.ims.aidl.IOptionsResponseCallback;
import android.telephony.ims.aidl.IPublishResponseCallback;
import android.telephony.ims.aidl.ISubscribeResponseCallback;
import android.telephony.ims.aidl.RcsOptionsResponseAidlWrapper;
import android.telephony.ims.aidl.RcsPublishResponseAidlWrapper;
import android.telephony.ims.aidl.RcsSubscribeResponseAidlWrapper;
import android.telephony.ims.feature.ImsFeature;
import android.telephony.ims.feature.RcsFeature;
import android.telephony.ims.stub.CapabilityExchangeEventListener;
import android.telephony.ims.stub.RcsCapabilityExchangeImplBase;
import android.util.Log;
import com.android.internal.telephony.util.TelephonyUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.function.Supplier;
@SystemApi
/* loaded from: classes3.dex */
public class RcsFeature extends ImsFeature {
    private static final String LOG_TAG = "RcsFeature";
    private CapabilityExchangeEventListener mCapExchangeEventListener;
    private RcsCapabilityExchangeImplBase mCapabilityExchangeImpl;
    private final Executor mExecutor;
    private final RcsFeatureBinder mImsRcsBinder;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class RcsFeatureBinder extends IImsRcsFeature.Stub {
        private final Executor mExecutor;
        private final RcsFeature mReference;

        RcsFeatureBinder(RcsFeature classRef, Executor executor) {
            this.mReference = classRef;
            this.mExecutor = executor;
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public int queryCapabilityStatus() throws RemoteException {
            return ((Integer) executeMethodAsyncForResult(new Supplier() { // from class: android.telephony.ims.feature.RcsFeature$RcsFeatureBinder$$ExternalSyntheticLambda9
                @Override // java.util.function.Supplier
                public final Object get() {
                    return RcsFeature.RcsFeatureBinder.this.lambda$queryCapabilityStatus$0$RcsFeature$RcsFeatureBinder();
                }
            }, "queryCapabilityStatus")).intValue();
        }

        public /* synthetic */ Integer lambda$queryCapabilityStatus$0$RcsFeature$RcsFeatureBinder() {
            return Integer.valueOf(this.mReference.queryCapabilityStatus().mCapabilities);
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public void addCapabilityCallback(final IImsCapabilityCallback c) throws RemoteException {
            executeMethodAsync(new Runnable() { // from class: android.telephony.ims.feature.RcsFeature$RcsFeatureBinder$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    RcsFeature.RcsFeatureBinder.this.lambda$addCapabilityCallback$1$RcsFeature$RcsFeatureBinder(c);
                }
            }, "addCapabilityCallback");
        }

        public /* synthetic */ void lambda$addCapabilityCallback$1$RcsFeature$RcsFeatureBinder(IImsCapabilityCallback c) {
            this.mReference.addCapabilityCallback(c);
        }

        public /* synthetic */ void lambda$removeCapabilityCallback$2$RcsFeature$RcsFeatureBinder(IImsCapabilityCallback c) {
            this.mReference.removeCapabilityCallback(c);
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public void removeCapabilityCallback(final IImsCapabilityCallback c) throws RemoteException {
            executeMethodAsync(new Runnable() { // from class: android.telephony.ims.feature.RcsFeature$RcsFeatureBinder$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    RcsFeature.RcsFeatureBinder.this.lambda$removeCapabilityCallback$2$RcsFeature$RcsFeatureBinder(c);
                }
            }, "removeCapabilityCallback");
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public void changeCapabilitiesConfiguration(final CapabilityChangeRequest r, final IImsCapabilityCallback c) throws RemoteException {
            executeMethodAsync(new Runnable() { // from class: android.telephony.ims.feature.RcsFeature$RcsFeatureBinder$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    RcsFeature.RcsFeatureBinder.this.lambda$changeCapabilitiesConfiguration$3$RcsFeature$RcsFeatureBinder(r, c);
                }
            }, "changeCapabilitiesConfiguration");
        }

        public /* synthetic */ void lambda$changeCapabilitiesConfiguration$3$RcsFeature$RcsFeatureBinder(CapabilityChangeRequest r, IImsCapabilityCallback c) {
            this.mReference.requestChangeEnabledCapabilities(r, c);
        }

        public /* synthetic */ void lambda$queryCapabilityConfiguration$4$RcsFeature$RcsFeatureBinder(int capability, int radioTech, IImsCapabilityCallback c) {
            this.mReference.queryCapabilityConfigurationInternal(capability, radioTech, c);
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public void queryCapabilityConfiguration(final int capability, final int radioTech, final IImsCapabilityCallback c) throws RemoteException {
            executeMethodAsync(new Runnable() { // from class: android.telephony.ims.feature.RcsFeature$RcsFeatureBinder$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    RcsFeature.RcsFeatureBinder.this.lambda$queryCapabilityConfiguration$4$RcsFeature$RcsFeatureBinder(capability, radioTech, c);
                }
            }, "queryCapabilityConfiguration");
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public int getFeatureState() throws RemoteException {
            final RcsFeature rcsFeature = this.mReference;
            Objects.requireNonNull(rcsFeature);
            return ((Integer) executeMethodAsyncForResult(new Supplier() { // from class: android.telephony.ims.feature.RcsFeature$RcsFeatureBinder$$ExternalSyntheticLambda10
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(RcsFeature.this.getFeatureState());
                }
            }, "getFeatureState")).intValue();
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public void setCapabilityExchangeEventListener(ICapabilityExchangeEventListener listener) throws RemoteException {
            final CapabilityExchangeEventListener listenerWrapper = new CapabilityExchangeAidlWrapper(listener);
            executeMethodAsync(new Runnable() { // from class: android.telephony.ims.feature.RcsFeature$RcsFeatureBinder$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    RcsFeature.RcsFeatureBinder.this.lambda$setCapabilityExchangeEventListener$5$RcsFeature$RcsFeatureBinder(listenerWrapper);
                }
            }, "setCapabilityExchangeEventListener");
        }

        public /* synthetic */ void lambda$setCapabilityExchangeEventListener$5$RcsFeature$RcsFeatureBinder(CapabilityExchangeEventListener listenerWrapper) {
            this.mReference.setCapabilityExchangeEventListener(listenerWrapper);
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public void publishCapabilities(final String pidfXml, IPublishResponseCallback callback) throws RemoteException {
            final RcsCapabilityExchangeImplBase.PublishResponseCallback callbackWrapper = new RcsPublishResponseAidlWrapper(callback);
            executeMethodAsync(new Runnable() { // from class: android.telephony.ims.feature.RcsFeature$RcsFeatureBinder$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    RcsFeature.RcsFeatureBinder.this.lambda$publishCapabilities$6$RcsFeature$RcsFeatureBinder(pidfXml, callbackWrapper);
                }
            }, "publishCapabilities");
        }

        public /* synthetic */ void lambda$publishCapabilities$6$RcsFeature$RcsFeatureBinder(String pidfXml, RcsCapabilityExchangeImplBase.PublishResponseCallback callbackWrapper) {
            this.mReference.getCapabilityExchangeImplBaseInternal().publishCapabilities(pidfXml, callbackWrapper);
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public void subscribeForCapabilities(final List<Uri> uris, ISubscribeResponseCallback callback) throws RemoteException {
            final RcsCapabilityExchangeImplBase.SubscribeResponseCallback wrapper = new RcsSubscribeResponseAidlWrapper(callback);
            executeMethodAsync(new Runnable() { // from class: android.telephony.ims.feature.RcsFeature$RcsFeatureBinder$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    RcsFeature.RcsFeatureBinder.this.lambda$subscribeForCapabilities$7$RcsFeature$RcsFeatureBinder(uris, wrapper);
                }
            }, "subscribeForCapabilities");
        }

        public /* synthetic */ void lambda$subscribeForCapabilities$7$RcsFeature$RcsFeatureBinder(List uris, RcsCapabilityExchangeImplBase.SubscribeResponseCallback wrapper) {
            this.mReference.getCapabilityExchangeImplBaseInternal().subscribeForCapabilities(uris, wrapper);
        }

        @Override // android.telephony.ims.aidl.IImsRcsFeature
        public void sendOptionsCapabilityRequest(final Uri contactUri, final List<String> myCapabilities, IOptionsResponseCallback callback) throws RemoteException {
            final RcsCapabilityExchangeImplBase.OptionsResponseCallback callbackWrapper = new RcsOptionsResponseAidlWrapper(callback);
            executeMethodAsync(new Runnable() { // from class: android.telephony.ims.feature.RcsFeature$RcsFeatureBinder$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    RcsFeature.RcsFeatureBinder.this.lambda$sendOptionsCapabilityRequest$8$RcsFeature$RcsFeatureBinder(contactUri, myCapabilities, callbackWrapper);
                }
            }, "sendOptionsCapabilityRequest");
        }

        public /* synthetic */ void lambda$sendOptionsCapabilityRequest$8$RcsFeature$RcsFeatureBinder(Uri contactUri, List myCapabilities, RcsCapabilityExchangeImplBase.OptionsResponseCallback callbackWrapper) {
            this.mReference.getCapabilityExchangeImplBaseInternal().sendOptionsCapabilityRequest(contactUri, new HashSet(myCapabilities), callbackWrapper);
        }

        private void executeMethodAsync(final Runnable r, String errorLogName) throws RemoteException {
            try {
                CompletableFuture.runAsync(new Runnable() { // from class: android.telephony.ims.feature.RcsFeature$RcsFeatureBinder$$ExternalSyntheticLambda8
                    @Override // java.lang.Runnable
                    public final void run() {
                        TelephonyUtils.runWithCleanCallingIdentity(r);
                    }
                }, this.mExecutor).join();
            } catch (CancellationException | CompletionException e) {
                Log.w(RcsFeature.LOG_TAG, "RcsFeatureBinder - " + errorLogName + " exception: " + e.getMessage());
                throw new RemoteException(e.getMessage());
            }
        }

        private <T> T executeMethodAsyncForResult(final Supplier<T> r, String errorLogName) throws RemoteException {
            CompletableFuture<T> future = CompletableFuture.supplyAsync(new Supplier() { // from class: android.telephony.ims.feature.RcsFeature$RcsFeatureBinder$$ExternalSyntheticLambda11
                @Override // java.util.function.Supplier
                public final Object get() {
                    Object runWithCleanCallingIdentity;
                    runWithCleanCallingIdentity = TelephonyUtils.runWithCleanCallingIdentity(r);
                    return runWithCleanCallingIdentity;
                }
            }, this.mExecutor);
            try {
                return future.get();
            } catch (InterruptedException | ExecutionException e) {
                Log.w(RcsFeature.LOG_TAG, "RcsFeatureBinder - " + errorLogName + " exception: " + e.getMessage());
                throw new RemoteException(e.getMessage());
            }
        }
    }

    /* loaded from: classes3.dex */
    public static class RcsImsCapabilities extends ImsFeature.Capabilities {
        public static final int CAPABILITY_TYPE_NONE = 0;
        public static final int CAPABILITY_TYPE_OPTIONS_UCE = 1;
        public static final int CAPABILITY_TYPE_PRESENCE_UCE = 2;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes3.dex */
        public @interface RcsImsCapabilityFlag {
        }

        public RcsImsCapabilities(int capabilities) {
            super(capabilities);
        }

        private RcsImsCapabilities(ImsFeature.Capabilities capabilities) {
            super(capabilities.getMask());
        }

        @Override // android.telephony.ims.feature.ImsFeature.Capabilities
        public void addCapabilities(int capabilities) {
            super.addCapabilities(capabilities);
        }

        @Override // android.telephony.ims.feature.ImsFeature.Capabilities
        public void removeCapabilities(int capabilities) {
            super.removeCapabilities(capabilities);
        }

        @Override // android.telephony.ims.feature.ImsFeature.Capabilities
        public boolean isCapable(int capabilities) {
            return super.isCapable(capabilities);
        }
    }

    @Deprecated
    public RcsFeature() {
        MediaRouter2$$ExternalSyntheticLambda8 mediaRouter2$$ExternalSyntheticLambda8 = MediaRouter2$$ExternalSyntheticLambda8.INSTANCE;
        this.mExecutor = mediaRouter2$$ExternalSyntheticLambda8;
        this.mImsRcsBinder = new RcsFeatureBinder(this, mediaRouter2$$ExternalSyntheticLambda8);
    }

    public RcsFeature(Executor executor) {
        if (executor != null) {
            this.mExecutor = executor;
            this.mImsRcsBinder = new RcsFeatureBinder(this, executor);
            return;
        }
        throw new IllegalArgumentException("executor can not be null.");
    }

    @Override // android.telephony.ims.feature.ImsFeature
    public void initialize(Context context, int slotId) {
        super.initialize(context, slotId);
        this.mExecutor.execute(new Runnable() { // from class: android.telephony.ims.feature.RcsFeature$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                RcsFeature.this.lambda$initialize$0$RcsFeature();
            }
        });
    }

    @Override // android.telephony.ims.feature.ImsFeature
    public final RcsImsCapabilities queryCapabilityStatus() {
        return new RcsImsCapabilities(super.queryCapabilityStatus());
    }

    public final void notifyCapabilitiesStatusChanged(RcsImsCapabilities capabilities) {
        if (capabilities != null) {
            super.notifyCapabilitiesStatusChanged((ImsFeature.Capabilities) capabilities);
            return;
        }
        throw new IllegalArgumentException("RcsImsCapabilities must be non-null!");
    }

    @Override // android.telephony.ims.feature.ImsFeature
    public boolean queryCapabilityConfiguration(int capability, int radioTech) {
        return false;
    }

    @Override // android.telephony.ims.feature.ImsFeature
    public void changeEnabledCapabilities(CapabilityChangeRequest request, ImsFeature.CapabilityCallbackProxy callback) {
    }

    public RcsCapabilityExchangeImplBase createCapabilityExchangeImpl(CapabilityExchangeEventListener listener) {
        return new RcsCapabilityExchangeImplBase();
    }

    public void destroyCapabilityExchangeImpl(RcsCapabilityExchangeImplBase capExchangeImpl) {
    }

    @Override // android.telephony.ims.feature.ImsFeature
    public void onFeatureRemoved() {
    }

    @Override // android.telephony.ims.feature.ImsFeature
    /* renamed from: onFeatureReady */
    public void lambda$initialize$0$RcsFeature() {
    }

    @Override // android.telephony.ims.feature.ImsFeature
    public final IImsRcsFeature getBinder() {
        return this.mImsRcsBinder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCapabilityExchangeEventListener(CapabilityExchangeEventListener listener) {
        synchronized (this.mLock) {
            this.mCapExchangeEventListener = listener;
            if (listener != null) {
                initRcsCapabilityExchangeImplBase(listener);
            } else {
                RcsCapabilityExchangeImplBase rcsCapabilityExchangeImplBase = this.mCapabilityExchangeImpl;
                if (rcsCapabilityExchangeImplBase != null) {
                    destroyCapabilityExchangeImpl(rcsCapabilityExchangeImplBase);
                }
                this.mCapabilityExchangeImpl = null;
            }
        }
    }

    private void initRcsCapabilityExchangeImplBase(CapabilityExchangeEventListener listener) {
        synchronized (this.mLock) {
            RcsCapabilityExchangeImplBase rcsCapabilityExchangeImplBase = this.mCapabilityExchangeImpl;
            if (rcsCapabilityExchangeImplBase != null) {
                destroyCapabilityExchangeImpl(rcsCapabilityExchangeImplBase);
            }
            this.mCapabilityExchangeImpl = createCapabilityExchangeImpl(listener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RcsCapabilityExchangeImplBase getCapabilityExchangeImplBaseInternal() {
        RcsCapabilityExchangeImplBase rcsCapabilityExchangeImplBase;
        synchronized (this.mLock) {
            rcsCapabilityExchangeImplBase = this.mCapabilityExchangeImpl;
            if (rcsCapabilityExchangeImplBase == null) {
                throw new IllegalStateException("Session is not available.");
            }
        }
        return rcsCapabilityExchangeImplBase;
    }
}
