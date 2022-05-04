package android.media.tv.tuner;

import android.annotation.SystemApi;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;
@SystemApi
/* loaded from: classes2.dex */
public class Lnb implements AutoCloseable {
    public static final int EVENT_TYPE_DISEQC_RX_OVERFLOW = 0;
    public static final int EVENT_TYPE_DISEQC_RX_PARITY_ERROR = 2;
    public static final int EVENT_TYPE_DISEQC_RX_TIMEOUT = 1;
    public static final int EVENT_TYPE_LNB_OVERLOAD = 3;
    public static final int POSITION_A = 1;
    public static final int POSITION_B = 2;
    public static final int POSITION_UNDEFINED = 0;
    private static final String TAG = "Lnb";
    public static final int TONE_CONTINUOUS = 1;
    public static final int TONE_NONE = 0;
    public static final int VOLTAGE_11V = 2;
    public static final int VOLTAGE_12V = 3;
    public static final int VOLTAGE_13V = 4;
    public static final int VOLTAGE_14V = 5;
    public static final int VOLTAGE_15V = 6;
    public static final int VOLTAGE_18V = 7;
    public static final int VOLTAGE_19V = 8;
    public static final int VOLTAGE_5V = 1;
    public static final int VOLTAGE_NONE = 0;
    LnbCallback mCallback;
    Executor mExecutor;
    private long mNativeContext;
    Tuner mTuner;
    private final Object mCallbackLock = new Object();
    private Boolean mIsClosed = false;
    private final Object mLock = new Object();

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface EventType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Position {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Tone {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Voltage {
    }

    private native int nativeClose();

    private native int nativeSendDiseqcMessage(byte[] bArr);

    private native int nativeSetSatellitePosition(int i);

    private native int nativeSetTone(int i);

    private native int nativeSetVoltage(int i);

    private Lnb() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCallback(Executor executor, LnbCallback callback, Tuner tuner) {
        synchronized (this.mCallbackLock) {
            this.mCallback = callback;
            this.mExecutor = executor;
            this.mTuner = tuner;
        }
    }

    private void onEvent(final int eventType) {
        synchronized (this.mCallbackLock) {
            Executor executor = this.mExecutor;
            if (!(executor == null || this.mCallback == null)) {
                executor.execute(new Runnable() { // from class: android.media.tv.tuner.Lnb$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        Lnb.this.lambda$onEvent$0$Lnb(eventType);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$onEvent$0$Lnb(int eventType) {
        this.mCallback.onEvent(eventType);
    }

    private void onDiseqcMessage(final byte[] diseqcMessage) {
        synchronized (this.mCallbackLock) {
            Executor executor = this.mExecutor;
            if (!(executor == null || this.mCallback == null)) {
                executor.execute(new Runnable() { // from class: android.media.tv.tuner.Lnb$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        Lnb.this.lambda$onDiseqcMessage$1$Lnb(diseqcMessage);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$onDiseqcMessage$1$Lnb(byte[] diseqcMessage) {
        this.mCallback.onDiseqcMessage(diseqcMessage);
    }

    boolean isClosed() {
        boolean booleanValue;
        synchronized (this.mLock) {
            booleanValue = this.mIsClosed.booleanValue();
        }
        return booleanValue;
    }

    public int setVoltage(int voltage) {
        int nativeSetVoltage;
        synchronized (this.mLock) {
            TunerUtils.checkResourceState(TAG, this.mIsClosed.booleanValue());
            nativeSetVoltage = nativeSetVoltage(voltage);
        }
        return nativeSetVoltage;
    }

    public int setTone(int tone) {
        int nativeSetTone;
        synchronized (this.mLock) {
            TunerUtils.checkResourceState(TAG, this.mIsClosed.booleanValue());
            nativeSetTone = nativeSetTone(tone);
        }
        return nativeSetTone;
    }

    public int setSatellitePosition(int position) {
        int nativeSetSatellitePosition;
        synchronized (this.mLock) {
            TunerUtils.checkResourceState(TAG, this.mIsClosed.booleanValue());
            nativeSetSatellitePosition = nativeSetSatellitePosition(position);
        }
        return nativeSetSatellitePosition;
    }

    public int sendDiseqcMessage(byte[] message) {
        int nativeSendDiseqcMessage;
        synchronized (this.mLock) {
            TunerUtils.checkResourceState(TAG, this.mIsClosed.booleanValue());
            nativeSendDiseqcMessage = nativeSendDiseqcMessage(message);
        }
        return nativeSendDiseqcMessage;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        synchronized (this.mLock) {
            if (!this.mIsClosed.booleanValue()) {
                int res = nativeClose();
                if (res != 0) {
                    TunerUtils.throwExceptionForResult(res, "Failed to close LNB");
                } else {
                    this.mIsClosed = true;
                    this.mTuner.releaseLnb();
                }
            }
        }
    }
}
