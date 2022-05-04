package android.net.vcn;

import android.annotation.SystemApi;
import android.content.Context;
import android.net.LinkProperties;
import android.net.NetworkCapabilities;
import android.net.vcn.IVcnStatusCallback;
import android.net.vcn.IVcnUnderlyingNetworkPolicyListener;
import android.net.vcn.VcnManager;
import android.os.Binder;
import android.os.ParcelUuid;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import com.android.internal.util.FunctionalUtils;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
/* loaded from: classes2.dex */
public class VcnManager {
    public static final int VCN_ERROR_CODE_CONFIG_ERROR = 1;
    public static final int VCN_ERROR_CODE_INTERNAL_ERROR = 0;
    public static final int VCN_ERROR_CODE_NETWORK_ERROR = 2;
    public static final String VCN_NETWORK_SELECTION_WIFI_ENTRY_RSSI_THRESHOLD_KEY = "vcn_network_selection_wifi_entry_rssi_threshold";
    public static final String VCN_NETWORK_SELECTION_WIFI_EXIT_RSSI_THRESHOLD_KEY = "vcn_network_selection_wifi_exit_rssi_threshold";
    public static final int VCN_STATUS_CODE_ACTIVE = 2;
    public static final int VCN_STATUS_CODE_INACTIVE = 1;
    public static final int VCN_STATUS_CODE_NOT_CONFIGURED = 0;
    public static final int VCN_STATUS_CODE_SAFE_MODE = 3;
    private final Context mContext;
    private final IVcnManagementService mService;
    private static final String TAG = VcnManager.class.getSimpleName();
    private static final Map<VcnNetworkPolicyChangeListener, VcnUnderlyingNetworkPolicyListenerBinder> REGISTERED_POLICY_LISTENERS = new ConcurrentHashMap();

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface VcnErrorCode {
    }

    @SystemApi
    /* loaded from: classes2.dex */
    public interface VcnNetworkPolicyChangeListener {
        void onPolicyChanged();
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface VcnStatusCode {
    }

    /* loaded from: classes2.dex */
    public interface VcnUnderlyingNetworkPolicyListener extends VcnNetworkPolicyChangeListener {
    }

    public VcnManager(Context ctx, IVcnManagementService service) {
        Objects.requireNonNull(ctx, "missing context");
        this.mContext = ctx;
        Objects.requireNonNull(service, "missing service");
        this.mService = service;
    }

    public static Map<VcnNetworkPolicyChangeListener, VcnUnderlyingNetworkPolicyListenerBinder> getAllPolicyListeners() {
        return Collections.unmodifiableMap(REGISTERED_POLICY_LISTENERS);
    }

    public void setVcnConfig(ParcelUuid subscriptionGroup, VcnConfig config) throws IOException {
        Objects.requireNonNull(subscriptionGroup, "subscriptionGroup was null");
        Objects.requireNonNull(config, "config was null");
        try {
            this.mService.setVcnConfig(subscriptionGroup, config, this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (ServiceSpecificException e2) {
            throw new IOException(e2);
        }
    }

    public void clearVcnConfig(ParcelUuid subscriptionGroup) throws IOException {
        Objects.requireNonNull(subscriptionGroup, "subscriptionGroup was null");
        try {
            this.mService.clearVcnConfig(subscriptionGroup, this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (ServiceSpecificException e2) {
            throw new IOException(e2);
        }
    }

    public List<ParcelUuid> getConfiguredSubscriptionGroups() {
        try {
            return this.mService.getConfiguredSubscriptionGroups(this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void addVcnUnderlyingNetworkPolicyListener(Executor executor, VcnUnderlyingNetworkPolicyListener listener) {
        addVcnNetworkPolicyChangeListener(executor, listener);
    }

    public void removeVcnUnderlyingNetworkPolicyListener(VcnUnderlyingNetworkPolicyListener listener) {
        removeVcnNetworkPolicyChangeListener(listener);
    }

    public VcnUnderlyingNetworkPolicy getUnderlyingNetworkPolicy(NetworkCapabilities networkCapabilities, LinkProperties linkProperties) {
        Objects.requireNonNull(networkCapabilities, "networkCapabilities must not be null");
        Objects.requireNonNull(linkProperties, "linkProperties must not be null");
        try {
            return this.mService.getUnderlyingNetworkPolicy(networkCapabilities, linkProperties);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void addVcnNetworkPolicyChangeListener(Executor executor, VcnNetworkPolicyChangeListener listener) {
        Objects.requireNonNull(executor, "executor must not be null");
        Objects.requireNonNull(listener, "listener must not be null");
        VcnUnderlyingNetworkPolicyListenerBinder binder = new VcnUnderlyingNetworkPolicyListenerBinder(executor, listener);
        if (REGISTERED_POLICY_LISTENERS.putIfAbsent(listener, binder) == null) {
            try {
                this.mService.addVcnUnderlyingNetworkPolicyListener(binder);
            } catch (RemoteException e) {
                REGISTERED_POLICY_LISTENERS.remove(listener);
                throw e.rethrowFromSystemServer();
            }
        } else {
            throw new IllegalStateException("listener is already registered with VcnManager");
        }
    }

    @SystemApi
    public void removeVcnNetworkPolicyChangeListener(VcnNetworkPolicyChangeListener listener) {
        Objects.requireNonNull(listener, "listener must not be null");
        VcnUnderlyingNetworkPolicyListenerBinder binder = REGISTERED_POLICY_LISTENERS.remove(listener);
        if (binder != null) {
            try {
                this.mService.removeVcnUnderlyingNetworkPolicyListener(binder);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    @SystemApi
    public VcnNetworkPolicyResult applyVcnNetworkPolicy(NetworkCapabilities networkCapabilities, LinkProperties linkProperties) {
        Objects.requireNonNull(networkCapabilities, "networkCapabilities must not be null");
        Objects.requireNonNull(linkProperties, "linkProperties must not be null");
        VcnUnderlyingNetworkPolicy policy = getUnderlyingNetworkPolicy(networkCapabilities, linkProperties);
        return new VcnNetworkPolicyResult(policy.isTeardownRequested(), policy.getMergedNetworkCapabilities());
    }

    /* loaded from: classes2.dex */
    public static abstract class VcnStatusCallback {
        private VcnStatusCallbackBinder mCbBinder;

        public abstract void onGatewayConnectionError(String str, int i, Throwable th);

        public abstract void onStatusChanged(int i);
    }

    public void registerVcnStatusCallback(ParcelUuid subscriptionGroup, Executor executor, VcnStatusCallback callback) {
        Objects.requireNonNull(subscriptionGroup, "subscriptionGroup must not be null");
        Objects.requireNonNull(executor, "executor must not be null");
        Objects.requireNonNull(callback, "callback must not be null");
        synchronized (callback) {
            if (callback.mCbBinder == null) {
                callback.mCbBinder = new VcnStatusCallbackBinder(executor, callback);
                try {
                    this.mService.registerVcnStatusCallback(subscriptionGroup, callback.mCbBinder, this.mContext.getOpPackageName());
                } catch (RemoteException e) {
                    callback.mCbBinder = null;
                    throw e.rethrowFromSystemServer();
                }
            } else {
                throw new IllegalStateException("callback is already registered with VcnManager");
            }
        }
    }

    public void unregisterVcnStatusCallback(VcnStatusCallback callback) {
        Objects.requireNonNull(callback, "callback must not be null");
        synchronized (callback) {
            if (callback.mCbBinder != null) {
                try {
                    this.mService.unregisterVcnStatusCallback(callback.mCbBinder);
                    callback.mCbBinder = null;
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class VcnUnderlyingNetworkPolicyListenerBinder extends IVcnUnderlyingNetworkPolicyListener.Stub {
        private final Executor mExecutor;
        private final VcnNetworkPolicyChangeListener mListener;

        private VcnUnderlyingNetworkPolicyListenerBinder(Executor executor, VcnNetworkPolicyChangeListener listener) {
            this.mExecutor = executor;
            this.mListener = listener;
        }

        @Override // android.net.vcn.IVcnUnderlyingNetworkPolicyListener
        public void onPolicyChanged() {
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.net.vcn.VcnManager$VcnUnderlyingNetworkPolicyListenerBinder$$ExternalSyntheticLambda0
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() {
                    VcnManager.VcnUnderlyingNetworkPolicyListenerBinder.this.lambda$onPolicyChanged$1$VcnManager$VcnUnderlyingNetworkPolicyListenerBinder();
                }
            });
        }

        public /* synthetic */ void lambda$onPolicyChanged$0$VcnManager$VcnUnderlyingNetworkPolicyListenerBinder() {
            this.mListener.onPolicyChanged();
        }

        public /* synthetic */ void lambda$onPolicyChanged$1$VcnManager$VcnUnderlyingNetworkPolicyListenerBinder() throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.net.vcn.VcnManager$VcnUnderlyingNetworkPolicyListenerBinder$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    VcnManager.VcnUnderlyingNetworkPolicyListenerBinder.this.lambda$onPolicyChanged$0$VcnManager$VcnUnderlyingNetworkPolicyListenerBinder();
                }
            });
        }
    }

    /* loaded from: classes2.dex */
    public static class VcnStatusCallbackBinder extends IVcnStatusCallback.Stub {
        private final VcnStatusCallback mCallback;
        private final Executor mExecutor;

        public VcnStatusCallbackBinder(Executor executor, VcnStatusCallback callback) {
            this.mExecutor = executor;
            this.mCallback = callback;
        }

        @Override // android.net.vcn.IVcnStatusCallback
        public void onVcnStatusChanged(final int statusCode) {
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.net.vcn.VcnManager$VcnStatusCallbackBinder$$ExternalSyntheticLambda0
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() {
                    VcnManager.VcnStatusCallbackBinder.this.lambda$onVcnStatusChanged$1$VcnManager$VcnStatusCallbackBinder(statusCode);
                }
            });
        }

        public /* synthetic */ void lambda$onVcnStatusChanged$0$VcnManager$VcnStatusCallbackBinder(int statusCode) {
            this.mCallback.onStatusChanged(statusCode);
        }

        public /* synthetic */ void lambda$onVcnStatusChanged$1$VcnManager$VcnStatusCallbackBinder(final int statusCode) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.net.vcn.VcnManager$VcnStatusCallbackBinder$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    VcnManager.VcnStatusCallbackBinder.this.lambda$onVcnStatusChanged$0$VcnManager$VcnStatusCallbackBinder(statusCode);
                }
            });
        }

        @Override // android.net.vcn.IVcnStatusCallback
        public void onGatewayConnectionError(final String gatewayConnectionName, final int errorCode, String exceptionClass, String exceptionMessage) {
            final Throwable cause = createThrowableByClassName(exceptionClass, exceptionMessage);
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.net.vcn.VcnManager$VcnStatusCallbackBinder$$ExternalSyntheticLambda1
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() {
                    VcnManager.VcnStatusCallbackBinder.this.lambda$onGatewayConnectionError$3$VcnManager$VcnStatusCallbackBinder(gatewayConnectionName, errorCode, cause);
                }
            });
        }

        public /* synthetic */ void lambda$onGatewayConnectionError$3$VcnManager$VcnStatusCallbackBinder(final String gatewayConnectionName, final int errorCode, final Throwable cause) throws Exception {
            this.mExecutor.execute(new Runnable() { // from class: android.net.vcn.VcnManager$VcnStatusCallbackBinder$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    VcnManager.VcnStatusCallbackBinder.this.lambda$onGatewayConnectionError$2$VcnManager$VcnStatusCallbackBinder(gatewayConnectionName, errorCode, cause);
                }
            });
        }

        public /* synthetic */ void lambda$onGatewayConnectionError$2$VcnManager$VcnStatusCallbackBinder(String gatewayConnectionName, int errorCode, Throwable cause) {
            this.mCallback.onGatewayConnectionError(gatewayConnectionName, errorCode, cause);
        }

        private static Throwable createThrowableByClassName(String className, String message) {
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
    }
}
