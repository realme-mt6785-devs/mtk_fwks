package android.uwb;

import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;
import android.uwb.IUwbAdapterStateCallbacks;
import android.uwb.UwbManager;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
/* loaded from: classes3.dex */
public class AdapterStateListener extends IUwbAdapterStateCallbacks.Stub {
    private static final String TAG = "Uwb.StateListener";
    private final IUwbAdapter mAdapter;
    private boolean mIsRegistered = false;
    private final Map<UwbManager.AdapterStateCallback, Executor> mCallbackMap = new HashMap();
    private int mAdapterStateChangeReason = 4;
    private int mAdapterState = 0;

    public AdapterStateListener(IUwbAdapter adapter) {
        this.mAdapter = adapter;
    }

    public void register(Executor executor, UwbManager.AdapterStateCallback callback) {
        synchronized (this) {
            if (!this.mCallbackMap.containsKey(callback)) {
                this.mCallbackMap.put(callback, executor);
                if (!this.mIsRegistered) {
                    try {
                        this.mAdapter.registerAdapterStateCallbacks(this);
                        this.mIsRegistered = true;
                    } catch (RemoteException e) {
                        Log.w(TAG, "Failed to register adapter state callback");
                        throw e.rethrowFromSystemServer();
                    }
                } else {
                    sendCurrentState(callback);
                }
            }
        }
    }

    public void unregister(UwbManager.AdapterStateCallback callback) {
        synchronized (this) {
            if (this.mCallbackMap.containsKey(callback)) {
                this.mCallbackMap.remove(callback);
                if (this.mCallbackMap.isEmpty() && this.mIsRegistered) {
                    try {
                        this.mAdapter.unregisterAdapterStateCallbacks(this);
                        this.mIsRegistered = false;
                    } catch (RemoteException e) {
                        Log.w(TAG, "Failed to unregister AdapterStateCallback with service");
                        throw e.rethrowFromSystemServer();
                    }
                }
            }
        }
    }

    public void setEnabled(boolean isEnabled) {
        synchronized (this) {
            try {
                try {
                    this.mAdapter.setEnabled(isEnabled);
                } catch (RemoteException e) {
                    Log.w(TAG, "Failed to set adapter state");
                    throw e.rethrowFromSystemServer();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public int getAdapterState() {
        int adapterState;
        synchronized (this) {
            try {
                try {
                    adapterState = this.mAdapter.getAdapterState();
                } catch (RemoteException e) {
                    Log.w(TAG, "Failed to get adapter state");
                    throw e.rethrowFromSystemServer();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return adapterState;
    }

    private void sendCurrentState(final UwbManager.AdapterStateCallback callback) {
        synchronized (this) {
            Executor executor = this.mCallbackMap.get(callback);
            long identity = Binder.clearCallingIdentity();
            executor.execute(new Runnable() { // from class: android.uwb.AdapterStateListener$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AdapterStateListener.this.lambda$sendCurrentState$0$AdapterStateListener(callback);
                }
            });
            Binder.restoreCallingIdentity(identity);
        }
    }

    public /* synthetic */ void lambda$sendCurrentState$0$AdapterStateListener(UwbManager.AdapterStateCallback callback) {
        callback.onStateChanged(this.mAdapterState, this.mAdapterStateChangeReason);
    }

    @Override // android.uwb.IUwbAdapterStateCallbacks
    public void onAdapterStateChanged(int state, int reason) {
        synchronized (this) {
            int localReason = convertToStateChangedReason(reason);
            int localState = convertToState(state);
            this.mAdapterStateChangeReason = localReason;
            this.mAdapterState = localState;
            for (UwbManager.AdapterStateCallback cb : this.mCallbackMap.keySet()) {
                sendCurrentState(cb);
            }
        }
    }

    private static int convertToStateChangedReason(int reason) {
        switch (reason) {
            case 1:
                return 0;
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            default:
                return 4;
        }
    }

    private static int convertToState(int state) {
        switch (state) {
            case 1:
                return 1;
            case 2:
                return 2;
            default:
                return 0;
        }
    }
}
