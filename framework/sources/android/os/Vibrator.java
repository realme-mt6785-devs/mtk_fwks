package android.os;

import android.annotation.SystemApi;
import android.app.ActivityThread;
import android.content.Context;
import android.media.AudioAttributes;
import android.os.VibrationAttributes;
import android.util.Log;
import android.util.Range;
import com.android.internal.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;
/* loaded from: classes2.dex */
public abstract class Vibrator {
    private static final String TAG = "Vibrator";
    public static final int VIBRATION_EFFECT_SUPPORT_NO = 2;
    public static final int VIBRATION_EFFECT_SUPPORT_UNKNOWN = 0;
    public static final int VIBRATION_EFFECT_SUPPORT_YES = 1;
    public static final int VIBRATION_INTENSITY_HIGH = 3;
    public static final int VIBRATION_INTENSITY_LOW = 1;
    public static final int VIBRATION_INTENSITY_MEDIUM = 2;
    public static final int VIBRATION_INTENSITY_OFF = 0;
    private int mDefaultHapticFeedbackIntensity;
    private int mDefaultNotificationVibrationIntensity;
    private int mDefaultRingVibrationIntensity;
    private float mHapticChannelMaxVibrationAmplitude;
    private final String mPackageName;

    @SystemApi
    /* loaded from: classes2.dex */
    public interface OnVibratorStateChangedListener {
        void onVibratorStateChanged(boolean z);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface VibrationEffectSupport {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface VibrationIntensity {
    }

    public abstract void cancel();

    public abstract void cancel(int i);

    public abstract boolean hasAmplitudeControl();

    public abstract boolean hasVibrator();

    public abstract void vibrate(int i, String str, VibrationEffect vibrationEffect, String str2, VibrationAttributes vibrationAttributes);

    public Vibrator() {
        this.mPackageName = ActivityThread.currentPackageName();
        Context ctx = ActivityThread.currentActivityThread().getSystemContext();
        loadVibrationConfig(ctx);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Vibrator(Context context) {
        this.mPackageName = context.getOpPackageName();
        loadVibrationConfig(context);
    }

    private void loadVibrationConfig(Context context) {
        this.mDefaultHapticFeedbackIntensity = loadDefaultIntensity(context, R.integer.config_defaultHapticFeedbackIntensity);
        this.mDefaultNotificationVibrationIntensity = loadDefaultIntensity(context, R.integer.config_defaultNotificationVibrationIntensity);
        this.mDefaultRingVibrationIntensity = loadDefaultIntensity(context, R.integer.config_defaultRingVibrationIntensity);
        this.mHapticChannelMaxVibrationAmplitude = loadFloat(context, R.dimen.config_hapticChannelMaxVibrationAmplitude, 0.0f);
    }

    private int loadDefaultIntensity(Context ctx, int resId) {
        if (ctx != null) {
            return ctx.getResources().getInteger(resId);
        }
        return 2;
    }

    private float loadFloat(Context ctx, int resId, float defaultValue) {
        return ctx != null ? ctx.getResources().getFloat(resId) : defaultValue;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public VibratorInfo getInfo() {
        return VibratorInfo.EMPTY_VIBRATOR_INFO;
    }

    public int getDefaultHapticFeedbackIntensity() {
        return this.mDefaultHapticFeedbackIntensity;
    }

    public int getDefaultNotificationVibrationIntensity() {
        return this.mDefaultNotificationVibrationIntensity;
    }

    public int getDefaultRingVibrationIntensity() {
        return this.mDefaultRingVibrationIntensity;
    }

    public int getId() {
        return getInfo().getId();
    }

    public boolean hasFrequencyControl() {
        return getInfo().hasCapability(1536L);
    }

    public boolean hasExternalControl() {
        return getInfo().hasCapability(8L);
    }

    public float getResonantFrequency() {
        return getInfo().getResonantFrequency();
    }

    public float getQFactor() {
        return getInfo().getQFactor();
    }

    public Range<Float> getRelativeFrequencyRange() {
        return getInfo().getFrequencyRange();
    }

    public float getMaximumAmplitude(float relativeFrequency) {
        return getInfo().getMaxAmplitude(relativeFrequency);
    }

    public float getHapticChannelMaximumAmplitude() {
        float f = this.mHapticChannelMaxVibrationAmplitude;
        if (f <= 0.0f) {
            return Float.NaN;
        }
        return f;
    }

    public boolean setAlwaysOnEffect(int alwaysOnId, VibrationEffect effect, AudioAttributes attributes) {
        return setAlwaysOnEffect(Process.myUid(), this.mPackageName, alwaysOnId, effect, attributes);
    }

    public boolean setAlwaysOnEffect(int uid, String opPkg, int alwaysOnId, VibrationEffect effect, AudioAttributes attributes) {
        Log.w(TAG, "Always-on effects aren't supported");
        return false;
    }

    @Deprecated
    public void vibrate(long milliseconds) {
        vibrate(milliseconds, (AudioAttributes) null);
    }

    @Deprecated
    public void vibrate(long milliseconds, AudioAttributes attributes) {
        try {
            VibrationEffect effect = VibrationEffect.createOneShot(milliseconds, -1);
            vibrate(effect, attributes);
        } catch (IllegalArgumentException iae) {
            Log.e(TAG, "Failed to create VibrationEffect", iae);
        }
    }

    @Deprecated
    public void vibrate(long[] pattern, int repeat) {
        vibrate(pattern, repeat, null);
    }

    @Deprecated
    public void vibrate(long[] pattern, int repeat, AudioAttributes attributes) {
        if (repeat < -1 || repeat >= pattern.length) {
            Log.e(TAG, "vibrate called with repeat index out of bounds (pattern.length=" + pattern.length + ", index=" + repeat + ")");
            throw new ArrayIndexOutOfBoundsException();
        }
        try {
            vibrate(VibrationEffect.createWaveform(pattern, repeat), attributes);
        } catch (IllegalArgumentException iae) {
            Log.e(TAG, "Failed to create VibrationEffect", iae);
        }
    }

    public void vibrate(VibrationEffect vibe) {
        vibrate(vibe, (AudioAttributes) null);
    }

    public void vibrate(VibrationEffect vibe, AudioAttributes attributes) {
        vibrate(Process.myUid(), this.mPackageName, vibe, (String) null, attributes);
    }

    public final void vibrate(int uid, String opPkg, VibrationEffect vibe, String reason, AudioAttributes attributes) {
        if (attributes == null) {
            attributes = new AudioAttributes.Builder().build();
        }
        VibrationAttributes attr = new VibrationAttributes.Builder(attributes, vibe).build();
        vibrate(uid, opPkg, vibe, reason, attr);
    }

    public int[] areEffectsSupported(int... effectIds) {
        VibratorInfo info = getInfo();
        int[] supported = new int[effectIds.length];
        for (int i = 0; i < effectIds.length; i++) {
            supported[i] = info.isEffectSupported(effectIds[i]);
        }
        return supported;
    }

    public final int areAllEffectsSupported(int... effectIds) {
        int[] areEffectsSupported;
        int support = 1;
        for (int supported : areEffectsSupported(effectIds)) {
            if (supported == 2) {
                return 2;
            }
            if (supported == 0) {
                support = 0;
            }
        }
        return support;
    }

    public boolean[] arePrimitivesSupported(int... primitiveIds) {
        VibratorInfo info = getInfo();
        boolean[] supported = new boolean[primitiveIds.length];
        for (int i = 0; i < primitiveIds.length; i++) {
            supported[i] = info.isPrimitiveSupported(primitiveIds[i]);
        }
        return supported;
    }

    public final boolean areAllPrimitivesSupported(int... primitiveIds) {
        boolean[] arePrimitivesSupported;
        for (boolean supported : arePrimitivesSupported(primitiveIds)) {
            if (!supported) {
                return false;
            }
        }
        return true;
    }

    public int[] getPrimitiveDurations(int... primitiveIds) {
        VibratorInfo info = getInfo();
        int[] durations = new int[primitiveIds.length];
        for (int i = 0; i < primitiveIds.length; i++) {
            durations[i] = info.getPrimitiveDuration(primitiveIds[i]);
        }
        return durations;
    }

    @SystemApi
    public boolean isVibrating() {
        return false;
    }

    @SystemApi
    public void addVibratorStateListener(OnVibratorStateChangedListener listener) {
    }

    @SystemApi
    public void addVibratorStateListener(Executor executor, OnVibratorStateChangedListener listener) {
    }

    @SystemApi
    public void removeVibratorStateListener(OnVibratorStateChangedListener listener) {
    }

    public void linearMotorVibrate(int uid, String opPkg, int[] waveformIds, long[] timings, int strength, int repeat, String reason, AudioAttributes attributes, IBinder token) {
    }

    public int getVibratorStatus() {
        Log.d(TAG, "Vibrator getVibratorStatus()");
        return -1;
    }

    public void setVibratorStrength(int strength) {
        Log.d(TAG, "Vibrator setVibratorStrength()");
    }

    public int getVibratorTouchStyle() {
        Log.d(TAG, "Vibrator getVibratorTouchStyle()");
        return -1;
    }

    public void setVibratorTouchStyle(int style) {
        Log.d(TAG, "Vibrator setVibratorTouchStyle()");
    }
}
