package android.uwb;

import android.content.AttributionSource;
import android.os.CancellationSignal;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.util.Log;
import android.uwb.IUwbRangingCallbacks;
import android.uwb.RangingSession;
import java.util.Hashtable;
import java.util.concurrent.Executor;
/* loaded from: classes3.dex */
public class RangingManager extends IUwbRangingCallbacks.Stub {
    private static final String TAG = "Uwb.RangingManager";
    private final IUwbAdapter mAdapter;
    private final Hashtable<SessionHandle, RangingSession> mRangingSessionTable = new Hashtable<>();
    private int mNextSessionId = 1;

    public RangingManager(IUwbAdapter adapter) {
        this.mAdapter = adapter;
    }

    public CancellationSignal openSession(AttributionSource attributionSource, PersistableBundle params, Executor executor, RangingSession.Callback callbacks) {
        CancellationSignal cancellationSignal;
        synchronized (this) {
            int i = this.mNextSessionId;
            this.mNextSessionId = i + 1;
            SessionHandle sessionHandle = new SessionHandle(i);
            final RangingSession session = new RangingSession(executor, callbacks, this.mAdapter, sessionHandle);
            this.mRangingSessionTable.put(sessionHandle, session);
            try {
                this.mAdapter.openRanging(attributionSource, sessionHandle, this, params);
                cancellationSignal = new CancellationSignal();
                cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: android.uwb.RangingManager$$ExternalSyntheticLambda0
                    @Override // android.os.CancellationSignal.OnCancelListener
                    public final void onCancel() {
                        RangingSession.this.close();
                    }
                });
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        return cancellationSignal;
    }

    private boolean hasSession(SessionHandle sessionHandle) {
        return this.mRangingSessionTable.containsKey(sessionHandle);
    }

    @Override // android.uwb.IUwbRangingCallbacks
    public void onRangingOpened(SessionHandle sessionHandle) {
        synchronized (this) {
            if (!hasSession(sessionHandle)) {
                Log.w(TAG, "onRangingOpened - received unexpected SessionHandle: " + sessionHandle);
                return;
            }
            RangingSession session = this.mRangingSessionTable.get(sessionHandle);
            session.onRangingOpened();
        }
    }

    @Override // android.uwb.IUwbRangingCallbacks
    public void onRangingOpenFailed(SessionHandle sessionHandle, int reason, PersistableBundle parameters) {
        synchronized (this) {
            if (!hasSession(sessionHandle)) {
                Log.w(TAG, "onRangingOpenedFailed - received unexpected SessionHandle: " + sessionHandle);
                return;
            }
            RangingSession session = this.mRangingSessionTable.get(sessionHandle);
            session.onRangingOpenFailed(convertToReason(reason), parameters);
            this.mRangingSessionTable.remove(sessionHandle);
        }
    }

    @Override // android.uwb.IUwbRangingCallbacks
    public void onRangingReconfigured(SessionHandle sessionHandle, PersistableBundle parameters) {
        synchronized (this) {
            if (!hasSession(sessionHandle)) {
                Log.w(TAG, "onRangingReconfigured - received unexpected SessionHandle: " + sessionHandle);
                return;
            }
            RangingSession session = this.mRangingSessionTable.get(sessionHandle);
            session.onRangingReconfigured(parameters);
        }
    }

    @Override // android.uwb.IUwbRangingCallbacks
    public void onRangingReconfigureFailed(SessionHandle sessionHandle, int reason, PersistableBundle params) {
        synchronized (this) {
            if (!hasSession(sessionHandle)) {
                Log.w(TAG, "onRangingReconfigureFailed - received unexpected SessionHandle: " + sessionHandle);
                return;
            }
            RangingSession session = this.mRangingSessionTable.get(sessionHandle);
            session.onRangingReconfigureFailed(convertToReason(reason), params);
        }
    }

    @Override // android.uwb.IUwbRangingCallbacks
    public void onRangingStarted(SessionHandle sessionHandle, PersistableBundle parameters) {
        synchronized (this) {
            if (!hasSession(sessionHandle)) {
                Log.w(TAG, "onRangingStarted - received unexpected SessionHandle: " + sessionHandle);
                return;
            }
            RangingSession session = this.mRangingSessionTable.get(sessionHandle);
            session.onRangingStarted(parameters);
        }
    }

    @Override // android.uwb.IUwbRangingCallbacks
    public void onRangingStartFailed(SessionHandle sessionHandle, int reason, PersistableBundle params) {
        synchronized (this) {
            if (!hasSession(sessionHandle)) {
                Log.w(TAG, "onRangingStartFailed - received unexpected SessionHandle: " + sessionHandle);
                return;
            }
            RangingSession session = this.mRangingSessionTable.get(sessionHandle);
            session.onRangingStartFailed(convertToReason(reason), params);
        }
    }

    @Override // android.uwb.IUwbRangingCallbacks
    public void onRangingStopped(SessionHandle sessionHandle, int reason, PersistableBundle params) {
        synchronized (this) {
            if (!hasSession(sessionHandle)) {
                Log.w(TAG, "onRangingStopped - received unexpected SessionHandle: " + sessionHandle);
                return;
            }
            RangingSession session = this.mRangingSessionTable.get(sessionHandle);
            session.onRangingStopped(convertToReason(reason), params);
        }
    }

    @Override // android.uwb.IUwbRangingCallbacks
    public void onRangingStopFailed(SessionHandle sessionHandle, int reason, PersistableBundle parameters) {
        synchronized (this) {
            if (!hasSession(sessionHandle)) {
                Log.w(TAG, "onRangingStopFailed - received unexpected SessionHandle: " + sessionHandle);
                return;
            }
            RangingSession session = this.mRangingSessionTable.get(sessionHandle);
            session.onRangingStopFailed(convertToReason(reason), parameters);
        }
    }

    @Override // android.uwb.IUwbRangingCallbacks
    public void onRangingClosed(SessionHandle sessionHandle, int reason, PersistableBundle params) {
        synchronized (this) {
            if (!hasSession(sessionHandle)) {
                Log.w(TAG, "onRangingClosed - received unexpected SessionHandle: " + sessionHandle);
                return;
            }
            RangingSession session = this.mRangingSessionTable.get(sessionHandle);
            session.onRangingClosed(convertToReason(reason), params);
            this.mRangingSessionTable.remove(sessionHandle);
        }
    }

    @Override // android.uwb.IUwbRangingCallbacks
    public void onRangingResult(SessionHandle sessionHandle, RangingReport result) {
        synchronized (this) {
            if (!hasSession(sessionHandle)) {
                Log.w(TAG, "onRangingResult - received unexpected SessionHandle: " + sessionHandle);
                return;
            }
            RangingSession session = this.mRangingSessionTable.get(sessionHandle);
            session.onRangingResult(result);
        }
    }

    private static int convertToReason(int reason) {
        switch (reason) {
            case 1:
                return 1;
            case 2:
                return 5;
            case 3:
                return 6;
            case 4:
                return 2;
            case 5:
                return 7;
            case 6:
                return 3;
            default:
                return 0;
        }
    }
}
