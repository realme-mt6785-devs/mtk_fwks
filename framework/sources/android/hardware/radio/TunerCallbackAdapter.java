package android.hardware.radio;

import android.hardware.radio.ITunerCallback;
import android.hardware.radio.ProgramList;
import android.hardware.radio.RadioManager;
import android.hardware.radio.RadioTuner;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class TunerCallbackAdapter extends ITunerCallback.Stub {
    private static final String TAG = "BroadcastRadio.TunerCallbackAdapter";
    private final RadioTuner.Callback mCallback;
    RadioManager.ProgramInfo mCurrentProgramInfo;
    private final Handler mHandler;
    List<RadioManager.ProgramInfo> mLastCompleteList;
    ProgramList mProgramList;
    private final Object mLock = new Object();
    boolean mIsAntennaConnected = true;
    private boolean mDelayedCompleteCallback = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TunerCallbackAdapter(RadioTuner.Callback callback, Handler handler) {
        this.mCallback = callback;
        if (handler == null) {
            this.mHandler = new Handler(Looper.getMainLooper());
        } else {
            this.mHandler = handler;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void close() {
        synchronized (this.mLock) {
            ProgramList programList = this.mProgramList;
            if (programList != null) {
                programList.close();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setProgramListObserver(final ProgramList programList, final ProgramList.OnCloseListener closeListener) {
        Objects.requireNonNull(closeListener);
        synchronized (this.mLock) {
            if (this.mProgramList != null) {
                Log.w(TAG, "Previous program list observer wasn't properly closed, closing it...");
                this.mProgramList.close();
            }
            this.mProgramList = programList;
            if (programList != null) {
                programList.setOnCloseListener(new ProgramList.OnCloseListener() { // from class: android.hardware.radio.TunerCallbackAdapter$$ExternalSyntheticLambda0
                    @Override // android.hardware.radio.ProgramList.OnCloseListener
                    public final void onClose() {
                        TunerCallbackAdapter.this.lambda$setProgramListObserver$0$TunerCallbackAdapter(programList, closeListener);
                    }
                });
                programList.addOnCompleteListener(new ProgramList.OnCompleteListener() { // from class: android.hardware.radio.TunerCallbackAdapter$$ExternalSyntheticLambda1
                    @Override // android.hardware.radio.ProgramList.OnCompleteListener
                    public final void onComplete() {
                        TunerCallbackAdapter.this.lambda$setProgramListObserver$1$TunerCallbackAdapter(programList);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$setProgramListObserver$0$TunerCallbackAdapter(ProgramList programList, ProgramList.OnCloseListener closeListener) {
        synchronized (this.mLock) {
            if (this.mProgramList == programList) {
                this.mProgramList = null;
                this.mLastCompleteList = null;
                closeListener.onClose();
            }
        }
    }

    public /* synthetic */ void lambda$setProgramListObserver$1$TunerCallbackAdapter(ProgramList programList) {
        synchronized (this.mLock) {
            if (this.mProgramList == programList) {
                this.mLastCompleteList = programList.toList();
                if (this.mDelayedCompleteCallback) {
                    Log.d(TAG, "Sending delayed onBackgroundScanComplete callback");
                    sendBackgroundScanCompleteLocked();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<RadioManager.ProgramInfo> getLastCompleteList() {
        List<RadioManager.ProgramInfo> list;
        synchronized (this.mLock) {
            list = this.mLastCompleteList;
        }
        return list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearLastCompleteList() {
        synchronized (this.mLock) {
            this.mLastCompleteList = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RadioManager.ProgramInfo getCurrentProgramInformation() {
        RadioManager.ProgramInfo programInfo;
        synchronized (this.mLock) {
            programInfo = this.mCurrentProgramInfo;
        }
        return programInfo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isAntennaConnected() {
        return this.mIsAntennaConnected;
    }

    public /* synthetic */ void lambda$onError$2$TunerCallbackAdapter(int status) {
        this.mCallback.onError(status);
    }

    @Override // android.hardware.radio.ITunerCallback
    public void onError(final int status) {
        this.mHandler.post(new Runnable() { // from class: android.hardware.radio.TunerCallbackAdapter$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                TunerCallbackAdapter.this.lambda$onError$2$TunerCallbackAdapter(status);
            }
        });
    }

    public /* synthetic */ void lambda$onTuneFailed$3$TunerCallbackAdapter(int status, ProgramSelector selector) {
        this.mCallback.onTuneFailed(status, selector);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.hardware.radio.ITunerCallback
    public void onTuneFailed(final int status, final ProgramSelector selector) {
        final int errorCode;
        this.mHandler.post(new Runnable() { // from class: android.hardware.radio.TunerCallbackAdapter$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                TunerCallbackAdapter.this.lambda$onTuneFailed$3$TunerCallbackAdapter(status, selector);
            }
        });
        switch (status) {
            case Integer.MIN_VALUE:
            case -38:
            case -22:
            case -19:
                Log.i(TAG, "Got an error with no mapping to the legacy API (" + status + "), doing a best-effort conversion to ERROR_SCAN_TIMEOUT");
                errorCode = 3;
                break;
            case -32:
            case -1:
                errorCode = 1;
                break;
            default:
                errorCode = 3;
                break;
        }
        this.mHandler.post(new Runnable() { // from class: android.hardware.radio.TunerCallbackAdapter$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                TunerCallbackAdapter.this.lambda$onTuneFailed$4$TunerCallbackAdapter(errorCode);
            }
        });
    }

    public /* synthetic */ void lambda$onTuneFailed$4$TunerCallbackAdapter(int errorCode) {
        this.mCallback.onError(errorCode);
    }

    public /* synthetic */ void lambda$onConfigurationChanged$5$TunerCallbackAdapter(RadioManager.BandConfig config) {
        this.mCallback.onConfigurationChanged(config);
    }

    @Override // android.hardware.radio.ITunerCallback
    public void onConfigurationChanged(final RadioManager.BandConfig config) {
        this.mHandler.post(new Runnable() { // from class: android.hardware.radio.TunerCallbackAdapter$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                TunerCallbackAdapter.this.lambda$onConfigurationChanged$5$TunerCallbackAdapter(config);
            }
        });
    }

    @Override // android.hardware.radio.ITunerCallback
    public void onCurrentProgramInfoChanged(final RadioManager.ProgramInfo info) {
        if (info == null) {
            Log.e(TAG, "ProgramInfo must not be null");
            return;
        }
        synchronized (this.mLock) {
            this.mCurrentProgramInfo = info;
        }
        this.mHandler.post(new Runnable() { // from class: android.hardware.radio.TunerCallbackAdapter$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                TunerCallbackAdapter.this.lambda$onCurrentProgramInfoChanged$6$TunerCallbackAdapter(info);
            }
        });
    }

    public /* synthetic */ void lambda$onCurrentProgramInfoChanged$6$TunerCallbackAdapter(RadioManager.ProgramInfo info) {
        this.mCallback.onProgramInfoChanged(info);
        RadioMetadata metadata = info.getMetadata();
        if (metadata != null) {
            this.mCallback.onMetadataChanged(metadata);
        }
    }

    public /* synthetic */ void lambda$onTrafficAnnouncement$7$TunerCallbackAdapter(boolean active) {
        this.mCallback.onTrafficAnnouncement(active);
    }

    @Override // android.hardware.radio.ITunerCallback
    public void onTrafficAnnouncement(final boolean active) {
        this.mHandler.post(new Runnable() { // from class: android.hardware.radio.TunerCallbackAdapter$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                TunerCallbackAdapter.this.lambda$onTrafficAnnouncement$7$TunerCallbackAdapter(active);
            }
        });
    }

    public /* synthetic */ void lambda$onEmergencyAnnouncement$8$TunerCallbackAdapter(boolean active) {
        this.mCallback.onEmergencyAnnouncement(active);
    }

    @Override // android.hardware.radio.ITunerCallback
    public void onEmergencyAnnouncement(final boolean active) {
        this.mHandler.post(new Runnable() { // from class: android.hardware.radio.TunerCallbackAdapter$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                TunerCallbackAdapter.this.lambda$onEmergencyAnnouncement$8$TunerCallbackAdapter(active);
            }
        });
    }

    @Override // android.hardware.radio.ITunerCallback
    public void onAntennaState(final boolean connected) {
        this.mIsAntennaConnected = connected;
        this.mHandler.post(new Runnable() { // from class: android.hardware.radio.TunerCallbackAdapter$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                TunerCallbackAdapter.this.lambda$onAntennaState$9$TunerCallbackAdapter(connected);
            }
        });
    }

    public /* synthetic */ void lambda$onAntennaState$9$TunerCallbackAdapter(boolean connected) {
        this.mCallback.onAntennaState(connected);
    }

    public /* synthetic */ void lambda$onBackgroundScanAvailabilityChange$10$TunerCallbackAdapter(boolean isAvailable) {
        this.mCallback.onBackgroundScanAvailabilityChange(isAvailable);
    }

    @Override // android.hardware.radio.ITunerCallback
    public void onBackgroundScanAvailabilityChange(final boolean isAvailable) {
        this.mHandler.post(new Runnable() { // from class: android.hardware.radio.TunerCallbackAdapter$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                TunerCallbackAdapter.this.lambda$onBackgroundScanAvailabilityChange$10$TunerCallbackAdapter(isAvailable);
            }
        });
    }

    private void sendBackgroundScanCompleteLocked() {
        this.mDelayedCompleteCallback = false;
        this.mHandler.post(new Runnable() { // from class: android.hardware.radio.TunerCallbackAdapter$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                TunerCallbackAdapter.this.lambda$sendBackgroundScanCompleteLocked$11$TunerCallbackAdapter();
            }
        });
    }

    public /* synthetic */ void lambda$sendBackgroundScanCompleteLocked$11$TunerCallbackAdapter() {
        this.mCallback.onBackgroundScanComplete();
    }

    @Override // android.hardware.radio.ITunerCallback
    public void onBackgroundScanComplete() {
        synchronized (this.mLock) {
            if (this.mLastCompleteList == null) {
                Log.i(TAG, "Got onBackgroundScanComplete callback, but the program list didn't get through yet. Delaying it...");
                this.mDelayedCompleteCallback = true;
                return;
            }
            sendBackgroundScanCompleteLocked();
        }
    }

    public /* synthetic */ void lambda$onProgramListChanged$12$TunerCallbackAdapter() {
        this.mCallback.onProgramListChanged();
    }

    @Override // android.hardware.radio.ITunerCallback
    public void onProgramListChanged() {
        this.mHandler.post(new Runnable() { // from class: android.hardware.radio.TunerCallbackAdapter$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                TunerCallbackAdapter.this.lambda$onProgramListChanged$12$TunerCallbackAdapter();
            }
        });
    }

    @Override // android.hardware.radio.ITunerCallback
    public void onProgramListUpdated(final ProgramList.Chunk chunk) {
        this.mHandler.post(new Runnable() { // from class: android.hardware.radio.TunerCallbackAdapter$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                TunerCallbackAdapter.this.lambda$onProgramListUpdated$13$TunerCallbackAdapter(chunk);
            }
        });
    }

    public /* synthetic */ void lambda$onProgramListUpdated$13$TunerCallbackAdapter(ProgramList.Chunk chunk) {
        synchronized (this.mLock) {
            ProgramList programList = this.mProgramList;
            if (programList != null) {
                Objects.requireNonNull(chunk);
                programList.apply(chunk);
            }
        }
    }

    public /* synthetic */ void lambda$onParametersUpdated$14$TunerCallbackAdapter(Map parameters) {
        this.mCallback.onParametersUpdated(parameters);
    }

    @Override // android.hardware.radio.ITunerCallback
    public void onParametersUpdated(final Map parameters) {
        this.mHandler.post(new Runnable() { // from class: android.hardware.radio.TunerCallbackAdapter$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                TunerCallbackAdapter.this.lambda$onParametersUpdated$14$TunerCallbackAdapter(parameters);
            }
        });
    }
}
