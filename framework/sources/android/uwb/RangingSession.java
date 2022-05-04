package android.uwb;

import android.annotation.SystemApi;
import android.os.Binder;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;
@SystemApi
/* loaded from: classes3.dex */
public final class RangingSession implements AutoCloseable {
    private static final String TAG = "Uwb.RangingSession";
    private final IUwbAdapter mAdapter;
    private final Callback mCallback;
    private final Executor mExecutor;
    private final SessionHandle mSessionHandle;
    private State mState;

    /* loaded from: classes3.dex */
    public interface Callback {
        public static final int REASON_BAD_PARAMETERS = 3;
        public static final int REASON_GENERIC_ERROR = 4;
        public static final int REASON_LOCAL_REQUEST = 1;
        public static final int REASON_MAX_SESSIONS_REACHED = 5;
        public static final int REASON_PROTOCOL_SPECIFIC_ERROR = 7;
        public static final int REASON_REMOTE_REQUEST = 2;
        public static final int REASON_SYSTEM_POLICY = 6;
        public static final int REASON_UNKNOWN = 0;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes3.dex */
        public @interface Reason {
        }

        void onClosed(int i, PersistableBundle persistableBundle);

        void onOpenFailed(int i, PersistableBundle persistableBundle);

        void onOpened(RangingSession rangingSession);

        void onReconfigureFailed(int i, PersistableBundle persistableBundle);

        void onReconfigured(PersistableBundle persistableBundle);

        void onReportReceived(RangingReport rangingReport);

        void onStartFailed(int i, PersistableBundle persistableBundle);

        void onStarted(PersistableBundle persistableBundle);

        void onStopFailed(int i, PersistableBundle persistableBundle);

        void onStopped(int i, PersistableBundle persistableBundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public enum State {
        INIT,
        IDLE,
        ACTIVE,
        CLOSED
    }

    public RangingSession(Executor executor, Callback callback, IUwbAdapter adapter, SessionHandle sessionHandle) {
        this.mState = State.INIT;
        this.mState = State.INIT;
        this.mExecutor = executor;
        this.mCallback = callback;
        this.mAdapter = adapter;
        this.mSessionHandle = sessionHandle;
    }

    public boolean isOpen() {
        return this.mState == State.IDLE || this.mState == State.ACTIVE;
    }

    public void start(PersistableBundle params) {
        if (this.mState == State.IDLE) {
            try {
                this.mAdapter.startRanging(this.mSessionHandle, params);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            throw new IllegalStateException();
        }
    }

    public void reconfigure(PersistableBundle params) {
        if (this.mState == State.ACTIVE || this.mState == State.IDLE) {
            try {
                this.mAdapter.reconfigureRanging(this.mSessionHandle, params);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            throw new IllegalStateException();
        }
    }

    public void stop() {
        if (this.mState == State.ACTIVE) {
            try {
                this.mAdapter.stopRanging(this.mSessionHandle);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            throw new IllegalStateException();
        }
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        if (this.mState == State.CLOSED) {
            this.mExecutor.execute(new Runnable() { // from class: android.uwb.RangingSession$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    RangingSession.this.lambda$close$0$RangingSession();
                }
            });
            return;
        }
        try {
            this.mAdapter.closeRanging(this.mSessionHandle);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public /* synthetic */ void lambda$close$0$RangingSession() {
        this.mCallback.onClosed(1, new PersistableBundle());
    }

    public void onRangingOpened() {
        if (this.mState == State.CLOSED) {
            Log.w(TAG, "onRangingOpened invoked for a closed session");
            return;
        }
        this.mState = State.IDLE;
        executeCallback(new Runnable() { // from class: android.uwb.RangingSession$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                RangingSession.this.lambda$onRangingOpened$1$RangingSession();
            }
        });
    }

    public /* synthetic */ void lambda$onRangingOpened$1$RangingSession() {
        this.mCallback.onOpened(this);
    }

    public void onRangingOpenFailed(final int reason, final PersistableBundle params) {
        if (this.mState == State.CLOSED) {
            Log.w(TAG, "onRangingOpenFailed invoked for a closed session");
            return;
        }
        this.mState = State.CLOSED;
        executeCallback(new Runnable() { // from class: android.uwb.RangingSession$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                RangingSession.this.lambda$onRangingOpenFailed$2$RangingSession(reason, params);
            }
        });
    }

    public /* synthetic */ void lambda$onRangingOpenFailed$2$RangingSession(int reason, PersistableBundle params) {
        this.mCallback.onOpenFailed(reason, params);
    }

    public void onRangingStarted(final PersistableBundle parameters) {
        if (this.mState == State.CLOSED) {
            Log.w(TAG, "onRangingStarted invoked for a closed session");
            return;
        }
        this.mState = State.ACTIVE;
        executeCallback(new Runnable() { // from class: android.uwb.RangingSession$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                RangingSession.this.lambda$onRangingStarted$3$RangingSession(parameters);
            }
        });
    }

    public /* synthetic */ void lambda$onRangingStarted$3$RangingSession(PersistableBundle parameters) {
        this.mCallback.onStarted(parameters);
    }

    public void onRangingStartFailed(final int reason, final PersistableBundle params) {
        if (this.mState == State.CLOSED) {
            Log.w(TAG, "onRangingStartFailed invoked for a closed session");
        } else {
            executeCallback(new Runnable() { // from class: android.uwb.RangingSession$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    RangingSession.this.lambda$onRangingStartFailed$4$RangingSession(reason, params);
                }
            });
        }
    }

    public /* synthetic */ void lambda$onRangingStartFailed$4$RangingSession(int reason, PersistableBundle params) {
        this.mCallback.onStartFailed(reason, params);
    }

    public void onRangingReconfigured(final PersistableBundle params) {
        if (this.mState == State.CLOSED) {
            Log.w(TAG, "onRangingReconfigured invoked for a closed session");
        } else {
            executeCallback(new Runnable() { // from class: android.uwb.RangingSession$$ExternalSyntheticLambda8
                @Override // java.lang.Runnable
                public final void run() {
                    RangingSession.this.lambda$onRangingReconfigured$5$RangingSession(params);
                }
            });
        }
    }

    public /* synthetic */ void lambda$onRangingReconfigured$5$RangingSession(PersistableBundle params) {
        this.mCallback.onReconfigured(params);
    }

    public void onRangingReconfigureFailed(final int reason, final PersistableBundle params) {
        if (this.mState == State.CLOSED) {
            Log.w(TAG, "onRangingReconfigureFailed invoked for a closed session");
        } else {
            executeCallback(new Runnable() { // from class: android.uwb.RangingSession$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    RangingSession.this.lambda$onRangingReconfigureFailed$6$RangingSession(reason, params);
                }
            });
        }
    }

    public /* synthetic */ void lambda$onRangingReconfigureFailed$6$RangingSession(int reason, PersistableBundle params) {
        this.mCallback.onReconfigureFailed(reason, params);
    }

    public void onRangingStopped(final int reason, final PersistableBundle params) {
        if (this.mState == State.CLOSED) {
            Log.w(TAG, "onRangingStopped invoked for a closed session");
            return;
        }
        this.mState = State.IDLE;
        executeCallback(new Runnable() { // from class: android.uwb.RangingSession$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                RangingSession.this.lambda$onRangingStopped$7$RangingSession(reason, params);
            }
        });
    }

    public /* synthetic */ void lambda$onRangingStopped$7$RangingSession(int reason, PersistableBundle params) {
        this.mCallback.onStopped(reason, params);
    }

    public void onRangingStopFailed(final int reason, final PersistableBundle params) {
        if (this.mState == State.CLOSED) {
            Log.w(TAG, "onRangingStopFailed invoked for a closed session");
        } else {
            executeCallback(new Runnable() { // from class: android.uwb.RangingSession$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    RangingSession.this.lambda$onRangingStopFailed$8$RangingSession(reason, params);
                }
            });
        }
    }

    public /* synthetic */ void lambda$onRangingStopFailed$8$RangingSession(int reason, PersistableBundle params) {
        this.mCallback.onStopFailed(reason, params);
    }

    public void onRangingClosed(final int reason, final PersistableBundle parameters) {
        this.mState = State.CLOSED;
        executeCallback(new Runnable() { // from class: android.uwb.RangingSession$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                RangingSession.this.lambda$onRangingClosed$9$RangingSession(reason, parameters);
            }
        });
    }

    public /* synthetic */ void lambda$onRangingClosed$9$RangingSession(int reason, PersistableBundle parameters) {
        this.mCallback.onClosed(reason, parameters);
    }

    public void onRangingResult(final RangingReport report) {
        if (!isOpen()) {
            Log.w(TAG, "onRangingResult invoked for non-open session");
        } else {
            executeCallback(new Runnable() { // from class: android.uwb.RangingSession$$ExternalSyntheticLambda10
                @Override // java.lang.Runnable
                public final void run() {
                    RangingSession.this.lambda$onRangingResult$10$RangingSession(report);
                }
            });
        }
    }

    public /* synthetic */ void lambda$onRangingResult$10$RangingSession(RangingReport report) {
        this.mCallback.onReportReceived(report);
    }

    private void executeCallback(Runnable runnable) {
        long identity = Binder.clearCallingIdentity();
        try {
            this.mExecutor.execute(runnable);
        } finally {
            Binder.restoreCallingIdentity(identity);
        }
    }
}
