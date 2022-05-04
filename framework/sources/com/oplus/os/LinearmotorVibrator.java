package com.oplus.os;

import android.content.Context;
import android.os.Binder;
import android.os.Debug;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.util.Slog;
import java.util.Locale;
/* loaded from: classes4.dex */
public class LinearmotorVibrator {
    private static final int DEPTH_DEBUG_CALLER = 4;
    private static final long DURATION_DELAY_VIBRATE_WITH_INTERVAL = 50;
    private static final long DURATION_MAX_VIBRATE_WITH_INTERVAL = 50;
    public static final String FEATURE_WAVEFORM_VIBRATOR = "oplus.software.vibrator_lmvibrator";
    public static final String LINEARMOTORVIBRATOR_SERVICE = "linearmotor";
    private static final int MSG_LINEARMOTOR_VIBRATOR_BEGIN = 10000;
    private static final int MSG_LINEARMOTOR_VIBRATOR_VIBRATE = 10001;
    public static final String TAG = "LinearmotorVibrator";
    private Context mContext;
    private final String mPackageName;
    private final ILinearmotorVibratorService mService;
    private final Object mLock = new Object();
    private final Binder mToken = new Binder();
    private HandlerThread mHandlerThread = null;
    private VibratorHandler mHandler = null;
    private boolean mLogEnable = SystemProperties.getBoolean("persist.sys.assert.panic", false);
    private long mCurrentVibDuration = -1;
    private long mCurrentVibEndTime = -1;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public final class VibratorHandler extends Handler {
        public VibratorHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 10001:
                    synchronized (LinearmotorVibrator.this.mLock) {
                        LinearmotorVibrator.this.vibrateInnerLocked((WaveformEffect) msg.obj);
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public LinearmotorVibrator(Context context, ILinearmotorVibratorService service) {
        this.mContext = context;
        this.mService = service;
        this.mPackageName = context.getOpPackageName();
        if (service == null) {
            Slog.v(TAG, "ILinearmotorVibratorService was null");
        }
    }

    public void vibrate(WaveformEffect we) {
        Slog.d(TAG, "vibrate: effect=" + we);
        if (this.mLogEnable) {
            Slog.d(TAG, "vibrate: callers=" + Debug.getCallers(4));
        }
        if (this.mService == null || we == null) {
            Slog.d(TAG, "ignore vibrate in favor of invalid params.");
            return;
        }
        synchronized (this.mLock) {
            if (!tryVibrateAsynchronouslyLocked(we, SystemClock.uptimeMillis())) {
                vibrateInnerLocked(we);
            }
        }
    }

    private boolean tryVibrateAsynchronouslyLocked(WaveformEffect we, long now) {
        if (tryVibrateWithIntervalLocked(we, now)) {
            return true;
        }
        if (we == null || !we.getAsynchronous()) {
            return false;
        }
        vibAsyncAndUpdateCurVibLocked(we, 0L, now);
        return true;
    }

    private boolean tryVibrateWithIntervalLocked(WaveformEffect we, long now) {
        long duration = getEffectDuration(we);
        if (duration == -1 || this.mCurrentVibDuration == -1 || this.mCurrentVibEndTime == -1) {
            return false;
        }
        if (we == null || we.getEffectType() == -1) {
            Slog.d(TAG, "tryVibrateWithIntervalLocked: invalid effectType, return.");
            return false;
        }
        if (this.mLogEnable) {
            Slog.d(TAG, String.format(Locale.ENGLISH, "tryVibrateWithInterval: duration=%d, currentVibDuration=%d, now=%d, currentEndTime=%d", Long.valueOf(duration), Long.valueOf(this.mCurrentVibDuration), Long.valueOf(now), Long.valueOf(this.mCurrentVibEndTime)));
        }
        boolean shouldVibWithInterval = duration <= 50 && this.mCurrentVibDuration <= 50 && now <= this.mCurrentVibEndTime;
        if (!shouldVibWithInterval) {
            return false;
        }
        VibratorHandler vibratorHandler = this.mHandler;
        if (vibratorHandler == null || !vibratorHandler.hasMessages(10001)) {
            vibAsyncAndUpdateCurVibLocked(we, (this.mCurrentVibEndTime - now) + 50, now);
            return true;
        }
        Slog.d(TAG, "tryVibrateWithIntervalLocked: Ignoring incoming vibration in favor of asynchronous messages");
        return true;
    }

    private long getEffectDuration(WaveformEffect we) {
        long[] durationArr;
        if (we == null || (durationArr = we.getWaveFormDurationArray()) == null) {
            return -1L;
        }
        return durationArr[0];
    }

    private void vibAsyncAndUpdateCurVibLocked(WaveformEffect we, long delayDuration, long now) {
        vibrateAsynchronouslyLocked(we, delayDuration);
        updateCurrentVibrationLocked(we, now + delayDuration);
    }

    private void vibrateAsynchronouslyLocked(WaveformEffect we, long delayDuration) {
        Slog.d(TAG, "vibrateAsynchronouslyLocked: delayDuration=" + delayDuration);
        initHandlerIfNeeded();
        VibratorHandler vibratorHandler = this.mHandler;
        vibratorHandler.sendMessageDelayed(vibratorHandler.obtainMessage(10001, we), delayDuration);
    }

    private void initHandlerIfNeeded() {
        if (this.mHandlerThread == null) {
            HandlerThread handlerThread = new HandlerThread("LinearmotorVibrator-Thread");
            this.mHandlerThread = handlerThread;
            handlerThread.start();
        }
        if (this.mHandler == null) {
            this.mHandler = new VibratorHandler(this.mHandlerThread.getLooper());
        }
    }

    private void updateCurrentVibrationLocked(WaveformEffect we, long startTime) {
        long effectDuration = getEffectDuration(we);
        this.mCurrentVibDuration = effectDuration;
        this.mCurrentVibEndTime = effectDuration + startTime;
        if (this.mLogEnable) {
            Slog.d(TAG, String.format(Locale.ENGLISH, "updateCurrentVibrationLocked: mCurrentVibDuration=%d, mCurrentEndTime=%d", Long.valueOf(this.mCurrentVibDuration), Long.valueOf(this.mCurrentVibEndTime)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void vibrateInnerLocked(WaveformEffect we) {
        try {
            if (this.mLogEnable) {
                Slog.d(TAG, "vibrateInnerLocked: effect=" + we);
            }
            this.mService.vibrate(Process.myUid(), this.mPackageName, we, this.mToken);
            updateCurrentVibrationLocked(we, SystemClock.uptimeMillis());
        } catch (RemoteException e) {
            Slog.w(TAG, "Remote exception in LinearmotorVibrator: ", e);
        }
    }

    public void cancelVibrate(WaveformEffect we) {
        if (this.mService != null) {
            try {
                Slog.d(TAG, "call linearmotor vibrator service cancelVibrate");
                if (this.mLogEnable) {
                    Slog.d(TAG, "cancelVibrate: callers=" + Debug.getCallers(4));
                }
                this.mService.cancelVibrate(we, this.mToken);
                HandlerThread handlerThread = this.mHandlerThread;
                if (handlerThread != null) {
                    handlerThread.quitSafely();
                    this.mHandlerThread = null;
                }
                if (this.mHandler != null) {
                    this.mHandler = null;
                }
            } catch (RemoteException e) {
                Slog.w(TAG, "Remote exception in LinearmotorVibrator: ", e);
            }
        }
    }

    public int getVibratorStatus() {
        if (this.mService == null) {
            return -1;
        }
        try {
            Slog.d(TAG, "call linearmotor vibrator service getVibratorStatus");
            return this.mService.getVibratorStatus();
        } catch (RemoteException e) {
            Slog.w(TAG, "Remote exception in LinearmotorVibrator: ", e);
            return -1;
        }
    }

    public void setVibratorStrength(int strength) {
        if (this.mService != null) {
            try {
                Slog.d(TAG, "call linearmotor vibrator service setVibratorStrength");
                this.mService.setVibratorStrength(strength);
            } catch (RemoteException e) {
                Slog.w(TAG, "Remote exception in LinearmotorVibrator: ", e);
            }
        }
    }

    public int getSettingsTouchEffectStrength() {
        if (this.mService == null) {
            return 1;
        }
        try {
            Slog.d(TAG, "call linearmotor vibrator service getSettingsTouchEffectStrength");
            return this.mService.getSettingsTouchEffectStrength();
        } catch (RemoteException e) {
            Slog.w(TAG, "Remote exception in LinearmotorVibrator: ", e);
            return 1;
        }
    }

    public int getSettingsRingtoneEffectStrength() {
        if (this.mService == null) {
            return 1;
        }
        try {
            Slog.d(TAG, "call linearmotor vibrator service getSettingsRingtoneEffectStrength");
            return this.mService.getSettingsRingtoneEffectStrength();
        } catch (RemoteException e) {
            Slog.w(TAG, "Remote exception in LinearmotorVibrator: ", e);
            return 1;
        }
    }

    public int getSettingsNotificationEffectStrength() {
        if (this.mService == null) {
            return 1;
        }
        try {
            Slog.d(TAG, "call linearmotor vibrator service getSettingsNotificationEffectStrength");
            return this.mService.getSettingsNotificationEffectStrength();
        } catch (RemoteException e) {
            Slog.w(TAG, "Remote exception in LinearmotorVibrator: ", e);
            return 1;
        }
    }

    public void setVibratorTouchStyle(int style) {
        if (this.mService != null) {
            try {
                Slog.d(TAG, "call linearmotor vibrator service setVibratorTouchStyle");
                this.mService.setVibratorTouchStyle(style);
            } catch (RemoteException e) {
                Slog.w(TAG, "Remote exception in LinearmotorVibrator: ", e);
            }
        }
    }

    public int getVibratorTouchStyle() {
        if (this.mService == null) {
            return -1;
        }
        try {
            Slog.d(TAG, "call linearmotor vibrator service getVibratorTouchStyle");
            return this.mService.getVibratorTouchStyle();
        } catch (RemoteException e) {
            Slog.w(TAG, "Remote exception in LinearmotorVibrator: ", e);
            return -1;
        }
    }

    protected void finalize() throws Throwable {
        try {
            HandlerThread handlerThread = this.mHandlerThread;
            if (handlerThread != null) {
                handlerThread.quitSafely();
                this.mHandlerThread = null;
            }
            if (this.mHandler != null) {
                this.mHandler = null;
            }
        } finally {
            super.finalize();
        }
    }
}
