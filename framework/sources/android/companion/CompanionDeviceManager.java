package android.companion;

import android.annotation.SystemApi;
import android.app.Activity;
import android.app.Application;
import android.app.PendingIntent;
import android.companion.CompanionDeviceManager;
import android.companion.IFindDeviceCallback;
import android.content.ComponentName;
import android.content.Context;
import android.content.IntentSender;
import android.net.MacAddress;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.ExceptionUtils;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
/* loaded from: classes.dex */
public final class CompanionDeviceManager {
    public static final String COMPANION_DEVICE_DISCOVERY_PACKAGE_NAME = "com.android.companiondevicemanager";
    private static final boolean DEBUG = false;
    public static final String EXTRA_DEVICE = "android.companion.extra.DEVICE";
    private static final String LOG_TAG = "CompanionDeviceManager";
    private final Context mContext;
    private final ICompanionDeviceManager mService;

    /* loaded from: classes.dex */
    public static abstract class Callback {
        public abstract void onDeviceFound(IntentSender intentSender);

        public abstract void onFailure(CharSequence charSequence);
    }

    public CompanionDeviceManager(ICompanionDeviceManager service, Context context) {
        this.mService = service;
        this.mContext = context;
    }

    public void associate(AssociationRequest request, Callback callback, Handler handler) {
        if (checkFeaturePresent()) {
            Objects.requireNonNull(request, "Request cannot be null");
            Objects.requireNonNull(callback, "Callback cannot be null");
            try {
                this.mService.associate(request, new CallbackProxy(request, callback, Handler.mainIfNull(handler)), getCallingPackage());
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public List<String> getAssociations() {
        if (!checkFeaturePresent()) {
            return Collections.emptyList();
        }
        try {
            return this.mService.getAssociations(getCallingPackage(), this.mContext.getUserId());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void disassociate(String deviceMacAddress) {
        if (checkFeaturePresent()) {
            try {
                this.mService.disassociate(deviceMacAddress, getCallingPackage());
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void requestNotificationAccess(ComponentName component) {
        if (checkFeaturePresent()) {
            try {
                IntentSender intentSender = this.mService.requestNotificationAccess(component).getIntentSender();
                this.mContext.startIntentSender(intentSender, null, 0, 0, 0);
            } catch (IntentSender.SendIntentException e) {
                throw new RuntimeException(e);
            } catch (RemoteException e2) {
                throw e2.rethrowFromSystemServer();
            }
        }
    }

    public boolean hasNotificationAccess(ComponentName component) {
        if (!checkFeaturePresent()) {
            return false;
        }
        try {
            return this.mService.hasNotificationAccess(component);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean isDeviceAssociatedForWifiConnection(String packageName, MacAddress macAddress, UserHandle user) {
        if (!checkFeaturePresent()) {
            return false;
        }
        Objects.requireNonNull(packageName, "package name cannot be null");
        Objects.requireNonNull(macAddress, "mac address cannot be null");
        Objects.requireNonNull(user, "user cannot be null");
        try {
            return this.mService.isDeviceAssociatedForWifiConnection(packageName, macAddress.toString(), user.getIdentifier());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<Association> getAllAssociations() {
        if (!checkFeaturePresent()) {
            return Collections.emptyList();
        }
        try {
            return this.mService.getAssociationsForUser(this.mContext.getUser().getIdentifier());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean canPairWithoutPrompt(String packageName, String deviceMacAddress, UserHandle user) {
        if (!checkFeaturePresent()) {
            return false;
        }
        Objects.requireNonNull(packageName, "package name cannot be null");
        Objects.requireNonNull(deviceMacAddress, "device mac address cannot be null");
        Objects.requireNonNull(user, "user handle cannot be null");
        try {
            return this.mService.canPairWithoutPrompt(packageName, deviceMacAddress, user.getIdentifier());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void startObservingDevicePresence(String deviceAddress) throws DeviceNotAssociatedException {
        if (checkFeaturePresent()) {
            Objects.requireNonNull(deviceAddress, "address cannot be null");
            try {
                this.mService.registerDevicePresenceListenerService(this.mContext.getPackageName(), deviceAddress);
            } catch (RemoteException e) {
                ExceptionUtils.propagateIfInstanceOf(e.getCause(), DeviceNotAssociatedException.class);
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void stopObservingDevicePresence(String deviceAddress) throws DeviceNotAssociatedException {
        if (checkFeaturePresent()) {
            Objects.requireNonNull(deviceAddress, "address cannot be null");
            try {
                this.mService.unregisterDevicePresenceListenerService(this.mContext.getPackageName(), deviceAddress);
            } catch (RemoteException e) {
                ExceptionUtils.propagateIfInstanceOf(e.getCause(), DeviceNotAssociatedException.class);
            }
        }
    }

    @SystemApi
    public void associate(String packageName, MacAddress macAddress, byte[] certificate) {
        if (checkFeaturePresent()) {
            Objects.requireNonNull(packageName, "package name cannot be null");
            Objects.requireNonNull(macAddress, "mac address cannot be null");
            UserHandle user = Process.myUserHandle();
            try {
                this.mService.createAssociation(packageName, macAddress.toString(), user.getIdentifier(), certificate);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    private boolean checkFeaturePresent() {
        return this.mService != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Activity getActivity() {
        return (Activity) this.mContext;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getCallingPackage() {
        return this.mContext.getPackageName();
    }

    /* loaded from: classes.dex */
    private class CallbackProxy extends IFindDeviceCallback.Stub implements Application.ActivityLifecycleCallbacks {
        private Callback mCallback;
        private Handler mHandler;
        final Object mLock;
        private AssociationRequest mRequest;

        private CallbackProxy(AssociationRequest request, Callback callback, Handler handler) {
            this.mLock = new Object();
            this.mCallback = callback;
            this.mHandler = handler;
            this.mRequest = request;
            CompanionDeviceManager.this.getActivity().getApplication().registerActivityLifecycleCallbacks(this);
        }

        @Override // android.companion.IFindDeviceCallback
        public void onSuccess(PendingIntent launcher) {
            lockAndPost(CompanionDeviceManager$CallbackProxy$$ExternalSyntheticLambda1.INSTANCE, launcher.getIntentSender());
        }

        @Override // android.companion.IFindDeviceCallback
        public void onFailure(CharSequence reason) {
            lockAndPost(CompanionDeviceManager$CallbackProxy$$ExternalSyntheticLambda2.INSTANCE, reason);
        }

        <T> void lockAndPost(final BiConsumer<Callback, T> action, final T payload) {
            synchronized (this.mLock) {
                Handler handler = this.mHandler;
                if (handler != null) {
                    handler.post(new Runnable() { // from class: android.companion.CompanionDeviceManager$CallbackProxy$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            CompanionDeviceManager.CallbackProxy.this.lambda$lockAndPost$0$CompanionDeviceManager$CallbackProxy(action, payload);
                        }
                    });
                }
            }
        }

        public /* synthetic */ void lambda$lockAndPost$0$CompanionDeviceManager$CallbackProxy(BiConsumer action, Object payload) {
            Callback callback;
            synchronized (this.mLock) {
                callback = this.mCallback;
            }
            if (callback != null) {
                action.accept(callback, payload);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            synchronized (this.mLock) {
                if (activity == CompanionDeviceManager.this.getActivity()) {
                    try {
                        CompanionDeviceManager.this.mService.stopScan(this.mRequest, this, CompanionDeviceManager.this.getCallingPackage());
                    } catch (RemoteException e) {
                        e.rethrowFromSystemServer();
                    }
                    CompanionDeviceManager.this.getActivity().getApplication().unregisterActivityLifecycleCallbacks(this);
                    this.mCallback = null;
                    this.mHandler = null;
                    this.mRequest = null;
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }
    }
}
