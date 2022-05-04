package android.telephony.ims;

import android.annotation.SystemApi;
import android.content.Context;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import android.telephony.TelephonyFrameworkInitializer;
import android.telephony.ims.RcsUceAdapter;
import android.telephony.ims.aidl.IImsRcsController;
import android.telephony.ims.aidl.IRcsUceControllerCallback;
import android.telephony.ims.aidl.IRcsUcePublishStateCallback;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
/* loaded from: classes3.dex */
public class RcsUceAdapter {
    public static final int CAPABILITY_TYPE_OPTIONS_UCE = 1;
    @SystemApi
    public static final int CAPABILITY_TYPE_PRESENCE_UCE = 2;
    @SystemApi
    public static final int CAPABILITY_UPDATE_TRIGGER_ETAG_EXPIRED = 1;
    @SystemApi
    public static final int CAPABILITY_UPDATE_TRIGGER_MOVE_TO_2G = 7;
    @SystemApi
    public static final int CAPABILITY_UPDATE_TRIGGER_MOVE_TO_3G = 6;
    @SystemApi
    public static final int CAPABILITY_UPDATE_TRIGGER_MOVE_TO_EHRPD = 4;
    @SystemApi
    public static final int CAPABILITY_UPDATE_TRIGGER_MOVE_TO_HSPAPLUS = 5;
    @SystemApi
    public static final int CAPABILITY_UPDATE_TRIGGER_MOVE_TO_IWLAN = 9;
    @SystemApi
    public static final int CAPABILITY_UPDATE_TRIGGER_MOVE_TO_LTE_VOPS_DISABLED = 2;
    @SystemApi
    public static final int CAPABILITY_UPDATE_TRIGGER_MOVE_TO_LTE_VOPS_ENABLED = 3;
    @SystemApi
    public static final int CAPABILITY_UPDATE_TRIGGER_MOVE_TO_NR5G_VOPS_DISABLED = 10;
    @SystemApi
    public static final int CAPABILITY_UPDATE_TRIGGER_MOVE_TO_NR5G_VOPS_ENABLED = 11;
    @SystemApi
    public static final int CAPABILITY_UPDATE_TRIGGER_MOVE_TO_WLAN = 8;
    @SystemApi
    public static final int CAPABILITY_UPDATE_TRIGGER_UNKNOWN = 0;
    @SystemApi
    public static final int ERROR_FORBIDDEN = 6;
    @SystemApi
    public static final int ERROR_GENERIC_FAILURE = 1;
    @SystemApi
    public static final int ERROR_INSUFFICIENT_MEMORY = 10;
    @SystemApi
    public static final int ERROR_LOST_NETWORK = 11;
    @SystemApi
    public static final int ERROR_NOT_AUTHORIZED = 5;
    @SystemApi
    public static final int ERROR_NOT_AVAILABLE = 3;
    @SystemApi
    public static final int ERROR_NOT_ENABLED = 2;
    @SystemApi
    public static final int ERROR_NOT_FOUND = 7;
    @SystemApi
    public static final int ERROR_NOT_REGISTERED = 4;
    @SystemApi
    public static final int ERROR_REQUEST_TIMEOUT = 9;
    @SystemApi
    public static final int ERROR_REQUEST_TOO_LARGE = 8;
    @SystemApi
    public static final int ERROR_SERVER_UNAVAILABLE = 12;
    @SystemApi
    public static final int PUBLISH_STATE_NOT_PUBLISHED = 2;
    @SystemApi
    public static final int PUBLISH_STATE_OK = 1;
    @SystemApi
    public static final int PUBLISH_STATE_OTHER_ERROR = 6;
    @SystemApi
    public static final int PUBLISH_STATE_RCS_PROVISION_ERROR = 4;
    @SystemApi
    public static final int PUBLISH_STATE_REQUEST_TIMEOUT = 5;
    @SystemApi
    public static final int PUBLISH_STATE_VOICE_PROVISION_ERROR = 3;
    private static final String TAG = "RcsUceAdapter";
    private final Context mContext;
    private final Map<OnPublishStateChangedListener, PublishStateCallbackAdapter> mPublishStateCallbacks = new HashMap();
    private final int mSubId;

    @SystemApi
    /* loaded from: classes3.dex */
    public interface CapabilitiesCallback {
        void onCapabilitiesReceived(List<RcsContactUceCapability> list);

        void onComplete();

        void onError(int i, long j);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface ErrorCode {
    }

    @SystemApi
    /* loaded from: classes3.dex */
    public interface OnPublishStateChangedListener {
        void onPublishStateChange(int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface PublishState {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface RcsImsCapabilityFlag {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface StackPublishTriggerType {
    }

    /* loaded from: classes3.dex */
    public static class PublishStateCallbackAdapter {
        private final PublishStateBinder mBinder;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class PublishStateBinder extends IRcsUcePublishStateCallback.Stub {
            private final Executor mExecutor;
            private final OnPublishStateChangedListener mPublishStateChangeListener;

            PublishStateBinder(Executor executor, OnPublishStateChangedListener listener) {
                this.mExecutor = executor;
                this.mPublishStateChangeListener = listener;
            }

            @Override // android.telephony.ims.aidl.IRcsUcePublishStateCallback
            public void onPublishStateChanged(final int publishState) {
                if (this.mPublishStateChangeListener != null) {
                    long callingIdentity = Binder.clearCallingIdentity();
                    try {
                        this.mExecutor.execute(new Runnable() { // from class: android.telephony.ims.RcsUceAdapter$PublishStateCallbackAdapter$PublishStateBinder$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                RcsUceAdapter.PublishStateCallbackAdapter.PublishStateBinder.this.lambda$onPublishStateChanged$0$RcsUceAdapter$PublishStateCallbackAdapter$PublishStateBinder(publishState);
                            }
                        });
                    } finally {
                        restoreCallingIdentity(callingIdentity);
                    }
                }
            }

            public /* synthetic */ void lambda$onPublishStateChanged$0$RcsUceAdapter$PublishStateCallbackAdapter$PublishStateBinder(int publishState) {
                this.mPublishStateChangeListener.onPublishStateChange(publishState);
            }
        }

        public PublishStateCallbackAdapter(Executor executor, OnPublishStateChangedListener listener) {
            this.mBinder = new PublishStateBinder(executor, listener);
        }

        public final IRcsUcePublishStateCallback getBinder() {
            return this.mBinder;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RcsUceAdapter(Context context, int subId) {
        this.mContext = context;
        this.mSubId = subId;
    }

    @SystemApi
    public void requestCapabilities(Collection<Uri> contactNumbers, Executor executor, CapabilitiesCallback c) throws ImsException {
        if (c == null) {
            throw new IllegalArgumentException("Must include a non-null CapabilitiesCallback.");
        } else if (executor == null) {
            throw new IllegalArgumentException("Must include a non-null Executor.");
        } else if (contactNumbers != null) {
            IImsRcsController imsRcsController = getIImsRcsController();
            if (imsRcsController != null) {
                IRcsUceControllerCallback internalCallback = new AnonymousClass1(executor, c);
                try {
                    imsRcsController.requestCapabilities(this.mSubId, this.mContext.getOpPackageName(), this.mContext.getAttributionTag(), new ArrayList(contactNumbers), internalCallback);
                } catch (RemoteException e) {
                    Log.e(TAG, "Error calling IImsRcsController#requestCapabilities", e);
                    throw new ImsException("Remote IMS Service is not available", 1);
                } catch (ServiceSpecificException e2) {
                    throw new ImsException(e2.toString(), e2.errorCode);
                }
            } else {
                Log.e(TAG, "requestCapabilities: IImsRcsController is null");
                throw new ImsException("Can not find remote IMS service", 1);
            }
        } else {
            throw new IllegalArgumentException("Must include non-null contact number list.");
        }
    }

    /* renamed from: android.telephony.ims.RcsUceAdapter$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass1 extends IRcsUceControllerCallback.Stub {
        final /* synthetic */ CapabilitiesCallback val$c;
        final /* synthetic */ Executor val$executor;

        AnonymousClass1(Executor executor, CapabilitiesCallback capabilitiesCallback) {
            this.val$executor = executor;
            this.val$c = capabilitiesCallback;
        }

        @Override // android.telephony.ims.aidl.IRcsUceControllerCallback
        public void onCapabilitiesReceived(final List<RcsContactUceCapability> contactCapabilities) {
            long callingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final CapabilitiesCallback capabilitiesCallback = this.val$c;
                executor.execute(new Runnable() { // from class: android.telephony.ims.RcsUceAdapter$1$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        RcsUceAdapter.CapabilitiesCallback.this.onCapabilitiesReceived(contactCapabilities);
                    }
                });
            } finally {
                restoreCallingIdentity(callingIdentity);
            }
        }

        @Override // android.telephony.ims.aidl.IRcsUceControllerCallback
        public void onComplete() {
            long callingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final CapabilitiesCallback capabilitiesCallback = this.val$c;
                executor.execute(new Runnable() { // from class: android.telephony.ims.RcsUceAdapter$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        RcsUceAdapter.CapabilitiesCallback.this.onComplete();
                    }
                });
            } finally {
                restoreCallingIdentity(callingIdentity);
            }
        }

        @Override // android.telephony.ims.aidl.IRcsUceControllerCallback
        public void onError(final int errorCode, final long retryAfterMilliseconds) {
            long callingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final CapabilitiesCallback capabilitiesCallback = this.val$c;
                executor.execute(new Runnable() { // from class: android.telephony.ims.RcsUceAdapter$1$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        RcsUceAdapter.CapabilitiesCallback.this.onError(errorCode, retryAfterMilliseconds);
                    }
                });
            } finally {
                restoreCallingIdentity(callingIdentity);
            }
        }
    }

    @SystemApi
    public void requestAvailability(Uri contactNumber, Executor executor, CapabilitiesCallback c) throws ImsException {
        if (executor == null) {
            throw new IllegalArgumentException("Must include a non-null Executor.");
        } else if (contactNumber == null) {
            throw new IllegalArgumentException("Must include non-null contact number.");
        } else if (c != null) {
            IImsRcsController imsRcsController = getIImsRcsController();
            if (imsRcsController != null) {
                IRcsUceControllerCallback internalCallback = new AnonymousClass2(executor, c);
                try {
                    imsRcsController.requestAvailability(this.mSubId, this.mContext.getOpPackageName(), this.mContext.getAttributionTag(), contactNumber, internalCallback);
                } catch (RemoteException e) {
                    Log.e(TAG, "Error calling IImsRcsController#requestAvailability", e);
                    throw new ImsException("Remote IMS Service is not available", 1);
                } catch (ServiceSpecificException e2) {
                    throw new ImsException(e2.toString(), e2.errorCode);
                }
            } else {
                Log.e(TAG, "requestAvailability: IImsRcsController is null");
                throw new ImsException("Cannot find remote IMS service", 1);
            }
        } else {
            throw new IllegalArgumentException("Must include a non-null CapabilitiesCallback.");
        }
    }

    /* renamed from: android.telephony.ims.RcsUceAdapter$2  reason: invalid class name */
    /* loaded from: classes3.dex */
    class AnonymousClass2 extends IRcsUceControllerCallback.Stub {
        final /* synthetic */ CapabilitiesCallback val$c;
        final /* synthetic */ Executor val$executor;

        AnonymousClass2(Executor executor, CapabilitiesCallback capabilitiesCallback) {
            this.val$executor = executor;
            this.val$c = capabilitiesCallback;
        }

        @Override // android.telephony.ims.aidl.IRcsUceControllerCallback
        public void onCapabilitiesReceived(final List<RcsContactUceCapability> contactCapabilities) {
            long callingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final CapabilitiesCallback capabilitiesCallback = this.val$c;
                executor.execute(new Runnable() { // from class: android.telephony.ims.RcsUceAdapter$2$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        RcsUceAdapter.CapabilitiesCallback.this.onCapabilitiesReceived(contactCapabilities);
                    }
                });
            } finally {
                restoreCallingIdentity(callingIdentity);
            }
        }

        @Override // android.telephony.ims.aidl.IRcsUceControllerCallback
        public void onComplete() {
            long callingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final CapabilitiesCallback capabilitiesCallback = this.val$c;
                executor.execute(new Runnable() { // from class: android.telephony.ims.RcsUceAdapter$2$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        RcsUceAdapter.CapabilitiesCallback.this.onComplete();
                    }
                });
            } finally {
                restoreCallingIdentity(callingIdentity);
            }
        }

        @Override // android.telephony.ims.aidl.IRcsUceControllerCallback
        public void onError(final int errorCode, final long retryAfterMilliseconds) {
            long callingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final CapabilitiesCallback capabilitiesCallback = this.val$c;
                executor.execute(new Runnable() { // from class: android.telephony.ims.RcsUceAdapter$2$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        RcsUceAdapter.CapabilitiesCallback.this.onError(errorCode, retryAfterMilliseconds);
                    }
                });
            } finally {
                restoreCallingIdentity(callingIdentity);
            }
        }
    }

    @SystemApi
    public int getUcePublishState() throws ImsException {
        IImsRcsController imsRcsController = getIImsRcsController();
        if (imsRcsController != null) {
            try {
                return imsRcsController.getUcePublishState(this.mSubId);
            } catch (RemoteException e) {
                Log.e(TAG, "Error calling IImsRcsController#getUcePublishState", e);
                throw new ImsException("Remote IMS Service is not available", 1);
            } catch (ServiceSpecificException e2) {
                throw new ImsException(e2.getMessage(), e2.errorCode);
            }
        } else {
            Log.e(TAG, "getUcePublishState: IImsRcsController is null");
            throw new ImsException("Can not find remote IMS service", 1);
        }
    }

    @SystemApi
    public void addOnPublishStateChangedListener(Executor executor, OnPublishStateChangedListener listener) throws ImsException {
        if (executor == null) {
            throw new IllegalArgumentException("Must include a non-null Executor.");
        } else if (listener != null) {
            IImsRcsController imsRcsController = getIImsRcsController();
            if (imsRcsController != null) {
                PublishStateCallbackAdapter stateCallback = addPublishStateCallback(executor, listener);
                try {
                    imsRcsController.registerUcePublishStateCallback(this.mSubId, stateCallback.getBinder());
                } catch (RemoteException e) {
                    Log.e(TAG, "Error calling IImsRcsController#registerUcePublishStateCallback", e);
                    throw new ImsException("Remote IMS Service is not available", 1);
                } catch (ServiceSpecificException e2) {
                    throw new ImsException(e2.getMessage(), e2.errorCode);
                }
            } else {
                Log.e(TAG, "addOnPublishStateChangedListener : IImsRcsController is null");
                throw new ImsException("Cannot find remote IMS service", 1);
            }
        } else {
            throw new IllegalArgumentException("Must include a non-null OnPublishStateChangedListener.");
        }
    }

    @SystemApi
    public void removeOnPublishStateChangedListener(OnPublishStateChangedListener listener) throws ImsException {
        if (listener != null) {
            IImsRcsController imsRcsController = getIImsRcsController();
            if (imsRcsController != null) {
                PublishStateCallbackAdapter callback = removePublishStateCallback(listener);
                if (callback != null) {
                    try {
                        imsRcsController.unregisterUcePublishStateCallback(this.mSubId, callback.getBinder());
                    } catch (RemoteException e) {
                        Log.e(TAG, "Error calling IImsRcsController#unregisterUcePublishStateCallback", e);
                        throw new ImsException("Remote IMS Service is not available", 1);
                    } catch (ServiceSpecificException e2) {
                        throw new ImsException(e2.getMessage(), e2.errorCode);
                    }
                }
            } else {
                Log.e(TAG, "removeOnPublishStateChangedListener: IImsRcsController is null");
                throw new ImsException("Cannot find remote IMS service", 1);
            }
        } else {
            throw new IllegalArgumentException("Must include a non-null OnPublishStateChangedListener.");
        }
    }

    public boolean isUceSettingEnabled() throws ImsException {
        IImsRcsController imsRcsController = getIImsRcsController();
        if (imsRcsController != null) {
            try {
                return imsRcsController.isUceSettingEnabled(this.mSubId, this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            } catch (RemoteException e) {
                Log.e(TAG, "Error calling IImsRcsController#isUceSettingEnabled", e);
                throw new ImsException("Remote IMS Service is not available", 1);
            }
        } else {
            Log.e(TAG, "isUceSettingEnabled: IImsRcsController is null");
            throw new ImsException("Can not find remote IMS service", 1);
        }
    }

    @SystemApi
    public void setUceSettingEnabled(boolean isEnabled) throws ImsException {
        IImsRcsController imsRcsController = getIImsRcsController();
        if (imsRcsController != null) {
            try {
                imsRcsController.setUceSettingEnabled(this.mSubId, isEnabled);
            } catch (RemoteException e) {
                Log.e(TAG, "Error calling IImsRcsController#setUceSettingEnabled", e);
                throw new ImsException("Remote IMS Service is not available", 1);
            }
        } else {
            Log.e(TAG, "setUceSettingEnabled: IImsRcsController is null");
            throw new ImsException("Can not find remote IMS service", 1);
        }
    }

    private PublishStateCallbackAdapter addPublishStateCallback(Executor executor, OnPublishStateChangedListener listener) {
        PublishStateCallbackAdapter adapter = new PublishStateCallbackAdapter(executor, listener);
        synchronized (this.mPublishStateCallbacks) {
            this.mPublishStateCallbacks.put(listener, adapter);
        }
        return adapter;
    }

    private PublishStateCallbackAdapter removePublishStateCallback(OnPublishStateChangedListener listener) {
        PublishStateCallbackAdapter remove;
        synchronized (this.mPublishStateCallbacks) {
            remove = this.mPublishStateCallbacks.remove(listener);
        }
        return remove;
    }

    private IImsRcsController getIImsRcsController() {
        IBinder binder = TelephonyFrameworkInitializer.getTelephonyServiceManager().getTelephonyImsServiceRegisterer().get();
        return IImsRcsController.Stub.asInterface(binder);
    }
}
