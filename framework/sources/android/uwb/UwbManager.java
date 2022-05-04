package android.uwb;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.CancellationSignal;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.uwb.IUwbAdapter;
import android.uwb.RangingSession;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;
@SystemApi
/* loaded from: classes3.dex */
public final class UwbManager {
    private static final String SERVICE_NAME = "uwb";
    private final AdapterStateListener mAdapterStateListener;
    private final Context mContext;
    private final RangingManager mRangingManager;
    private final IUwbAdapter mUwbAdapter;

    /* loaded from: classes3.dex */
    public interface AdapterStateCallback {
        public static final int STATE_CHANGED_REASON_ALL_SESSIONS_CLOSED = 1;
        public static final int STATE_CHANGED_REASON_ERROR_UNKNOWN = 4;
        public static final int STATE_CHANGED_REASON_SESSION_STARTED = 0;
        public static final int STATE_CHANGED_REASON_SYSTEM_BOOT = 3;
        public static final int STATE_CHANGED_REASON_SYSTEM_POLICY = 2;
        public static final int STATE_DISABLED = 0;
        public static final int STATE_ENABLED_ACTIVE = 2;
        public static final int STATE_ENABLED_INACTIVE = 1;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes3.dex */
        public @interface State {
        }

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes3.dex */
        public @interface StateChangedReason {
        }

        void onStateChanged(int i, int i2);
    }

    private UwbManager(Context ctx, IUwbAdapter adapter) {
        this.mContext = ctx;
        this.mUwbAdapter = adapter;
        this.mAdapterStateListener = new AdapterStateListener(adapter);
        this.mRangingManager = new RangingManager(adapter);
    }

    public static UwbManager getInstance(Context ctx) {
        IUwbAdapter adapter;
        IBinder b = ServiceManager.getService("uwb");
        if (b == null || (adapter = IUwbAdapter.Stub.asInterface(b)) == null) {
            return null;
        }
        return new UwbManager(ctx, adapter);
    }

    public void registerAdapterStateCallback(Executor executor, AdapterStateCallback callback) {
        this.mAdapterStateListener.register(executor, callback);
    }

    public void unregisterAdapterStateCallback(AdapterStateCallback callback) {
        this.mAdapterStateListener.unregister(callback);
    }

    public PersistableBundle getSpecificationInfo() {
        try {
            return this.mUwbAdapter.getSpecificationInfo();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public long elapsedRealtimeResolutionNanos() {
        try {
            return this.mUwbAdapter.getTimestampResolutionNanos();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public CancellationSignal openRangingSession(PersistableBundle parameters, Executor executor, RangingSession.Callback callbacks) {
        return this.mRangingManager.openSession(this.mContext.getAttributionSource(), parameters, executor, callbacks);
    }

    public int getAdapterState() {
        return this.mAdapterStateListener.getAdapterState();
    }

    public void setUwbEnabled(boolean enabled) {
        this.mAdapterStateListener.setEnabled(enabled);
    }
}
