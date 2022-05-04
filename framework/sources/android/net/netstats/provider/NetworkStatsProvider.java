package android.net.netstats.provider;

import android.annotation.SystemApi;
import android.net.NetworkStats;
import android.net.netstats.provider.INetworkStatsProvider;
import android.os.RemoteException;
@SystemApi
/* loaded from: classes2.dex */
public abstract class NetworkStatsProvider {
    public static final int QUOTA_UNLIMITED = -1;
    private final INetworkStatsProvider mProviderBinder = new INetworkStatsProvider.Stub() { // from class: android.net.netstats.provider.NetworkStatsProvider.1
        @Override // android.net.netstats.provider.INetworkStatsProvider
        public void onRequestStatsUpdate(int token) {
            NetworkStatsProvider.this.onRequestStatsUpdate(token);
        }

        @Override // android.net.netstats.provider.INetworkStatsProvider
        public void onSetAlert(long quotaBytes) {
            NetworkStatsProvider.this.onSetAlert(quotaBytes);
        }

        @Override // android.net.netstats.provider.INetworkStatsProvider
        public void onSetWarningAndLimit(String iface, long warningBytes, long limitBytes) {
            NetworkStatsProvider.this.onSetWarningAndLimit(iface, warningBytes, limitBytes);
        }
    };
    private INetworkStatsProviderCallback mProviderCbBinder;

    public abstract void onRequestStatsUpdate(int i);

    public abstract void onSetAlert(long j);

    public abstract void onSetLimit(String str, long j);

    public INetworkStatsProvider getProviderBinder() {
        return this.mProviderBinder;
    }

    public void setProviderCallbackBinder(INetworkStatsProviderCallback binder) {
        if (this.mProviderCbBinder == null) {
            this.mProviderCbBinder = binder;
            return;
        }
        throw new IllegalArgumentException("provider is already registered");
    }

    public INetworkStatsProviderCallback getProviderCallbackBinder() {
        return this.mProviderCbBinder;
    }

    public INetworkStatsProviderCallback getProviderCallbackBinderOrThrow() {
        INetworkStatsProviderCallback iNetworkStatsProviderCallback = this.mProviderCbBinder;
        if (iNetworkStatsProviderCallback != null) {
            return iNetworkStatsProviderCallback;
        }
        throw new IllegalStateException("the provider is not registered");
    }

    public void notifyStatsUpdated(int token, NetworkStats ifaceStats, NetworkStats uidStats) {
        try {
            getProviderCallbackBinderOrThrow().notifyStatsUpdated(token, ifaceStats, uidStats);
        } catch (RemoteException e) {
            e.rethrowAsRuntimeException();
        }
    }

    public void notifyAlertReached() {
        try {
            getProviderCallbackBinderOrThrow().notifyAlertReached();
        } catch (RemoteException e) {
            e.rethrowAsRuntimeException();
        }
    }

    public void notifyWarningReached() {
        try {
            getProviderCallbackBinderOrThrow().notifyWarningOrLimitReached();
        } catch (RemoteException e) {
            e.rethrowAsRuntimeException();
        }
    }

    public void notifyLimitReached() {
        try {
            getProviderCallbackBinderOrThrow().notifyWarningOrLimitReached();
        } catch (RemoteException e) {
            e.rethrowAsRuntimeException();
        }
    }

    public void onSetWarningAndLimit(String iface, long warningBytes, long limitBytes) {
        onSetLimit(iface, limitBytes);
    }
}
