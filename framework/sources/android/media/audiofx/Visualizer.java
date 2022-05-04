package android.media.audiofx;

import android.content.AttributionSource;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.util.Log;
import java.lang.ref.WeakReference;
/* loaded from: classes2.dex */
public class Visualizer {
    public static final int ALREADY_EXISTS = -2;
    public static final int ERROR = -1;
    public static final int ERROR_BAD_VALUE = -4;
    public static final int ERROR_DEAD_OBJECT = -7;
    public static final int ERROR_INVALID_OPERATION = -5;
    public static final int ERROR_NO_INIT = -3;
    public static final int ERROR_NO_MEMORY = -6;
    public static final int MEASUREMENT_MODE_NONE = 0;
    public static final int MEASUREMENT_MODE_PEAK_RMS = 1;
    private static final int NATIVE_EVENT_FFT_CAPTURE = 1;
    private static final int NATIVE_EVENT_PCM_CAPTURE = 0;
    private static final int NATIVE_EVENT_SERVER_DIED = 2;
    public static final int SCALING_MODE_AS_PLAYED = 1;
    public static final int SCALING_MODE_NORMALIZED = 0;
    public static final int STATE_ENABLED = 2;
    public static final int STATE_INITIALIZED = 1;
    public static final int STATE_UNINITIALIZED = 0;
    public static final int SUCCESS = 0;
    private static final String TAG = "Visualizer-JAVA";
    private int mId;
    private long mJniData;
    private long mNativeVisualizer;
    private int mState;
    private final Object mStateLock;
    private final Object mListenerLock = new Object();
    private Handler mNativeEventHandler = null;
    private OnDataCaptureListener mCaptureListener = null;
    private OnServerDiedListener mServerDiedListener = null;

    /* loaded from: classes2.dex */
    public static final class MeasurementPeakRms {
        public int mPeak;
        public int mRms;
    }

    /* loaded from: classes2.dex */
    public interface OnDataCaptureListener {
        void onFftDataCapture(Visualizer visualizer, byte[] bArr, int i);

        void onWaveFormDataCapture(Visualizer visualizer, byte[] bArr, int i);
    }

    /* loaded from: classes2.dex */
    public interface OnServerDiedListener {
        void onServerDied();
    }

    public static native int[] getCaptureSizeRange();

    public static native int getMaxCaptureRate();

    private final native void native_finalize();

    private final native int native_getCaptureSize();

    private final native boolean native_getEnabled();

    private final native int native_getFft(byte[] bArr);

    private final native int native_getMeasurementMode();

    private final native int native_getPeakRms(MeasurementPeakRms measurementPeakRms);

    private final native int native_getSamplingRate();

    private final native int native_getScalingMode();

    private final native int native_getWaveForm(byte[] bArr);

    private static final native void native_init();

    private final native void native_release();

    private final native int native_setCaptureSize(int i);

    private final native int native_setEnabled(boolean z);

    private final native int native_setMeasurementMode(int i);

    private final native int native_setPeriodicCapture(int i, boolean z, boolean z2);

    private final native int native_setScalingMode(int i);

    private final native int native_setup(Object obj, int i, int[] iArr, Parcel parcel);

    static {
        System.loadLibrary("audioeffect_jni");
        native_init();
    }

    public Visualizer(int audioSession) throws UnsupportedOperationException, RuntimeException {
        this.mState = 0;
        Object obj = new Object();
        this.mStateLock = obj;
        int[] id = new int[1];
        synchronized (obj) {
            this.mState = 0;
            AttributionSource.ScopedParcelState attributionSourceState = AttributionSource.myAttributionSource().asScopedParcelState();
            int result = native_setup(new WeakReference(this), audioSession, id, attributionSourceState.getParcel());
            if (attributionSourceState != null) {
                attributionSourceState.close();
            }
            if (result == 0 || result == -2) {
                this.mId = id[0];
                if (native_getEnabled()) {
                    this.mState = 2;
                } else {
                    this.mState = 1;
                }
            } else {
                Log.e(TAG, "Error code " + result + " when initializing Visualizer.");
                switch (result) {
                    case -5:
                        throw new UnsupportedOperationException("Effect library not loaded");
                    default:
                        throw new RuntimeException("Cannot initialize Visualizer engine, error: " + result);
                }
            }
        }
    }

    public void release() {
        synchronized (this.mStateLock) {
            native_release();
            this.mState = 0;
        }
    }

    protected void finalize() {
        synchronized (this.mStateLock) {
            native_finalize();
        }
    }

    public int setEnabled(boolean enabled) throws IllegalStateException {
        int status;
        synchronized (this.mStateLock) {
            int i = this.mState;
            if (i != 0) {
                status = 0;
                int i2 = 2;
                if (((enabled && i == 1) || (!enabled && i == 2)) && (status = native_setEnabled(enabled)) == 0) {
                    if (!enabled) {
                        i2 = 1;
                    }
                    this.mState = i2;
                }
            } else {
                throw new IllegalStateException("setEnabled() called in wrong state: " + this.mState);
            }
        }
        return status;
    }

    public boolean getEnabled() {
        boolean native_getEnabled;
        synchronized (this.mStateLock) {
            if (this.mState != 0) {
                native_getEnabled = native_getEnabled();
            } else {
                throw new IllegalStateException("getEnabled() called in wrong state: " + this.mState);
            }
        }
        return native_getEnabled;
    }

    public int setCaptureSize(int size) throws IllegalStateException {
        int native_setCaptureSize;
        synchronized (this.mStateLock) {
            if (this.mState == 1) {
                native_setCaptureSize = native_setCaptureSize(size);
            } else {
                throw new IllegalStateException("setCaptureSize() called in wrong state: " + this.mState);
            }
        }
        return native_setCaptureSize;
    }

    public int getCaptureSize() throws IllegalStateException {
        int native_getCaptureSize;
        synchronized (this.mStateLock) {
            if (this.mState != 0) {
                native_getCaptureSize = native_getCaptureSize();
            } else {
                throw new IllegalStateException("getCaptureSize() called in wrong state: " + this.mState);
            }
        }
        return native_getCaptureSize;
    }

    public int setScalingMode(int mode) throws IllegalStateException {
        int native_setScalingMode;
        synchronized (this.mStateLock) {
            if (this.mState != 0) {
                native_setScalingMode = native_setScalingMode(mode);
            } else {
                throw new IllegalStateException("setScalingMode() called in wrong state: " + this.mState);
            }
        }
        return native_setScalingMode;
    }

    public int getScalingMode() throws IllegalStateException {
        int native_getScalingMode;
        synchronized (this.mStateLock) {
            if (this.mState != 0) {
                native_getScalingMode = native_getScalingMode();
            } else {
                throw new IllegalStateException("getScalingMode() called in wrong state: " + this.mState);
            }
        }
        return native_getScalingMode;
    }

    public int setMeasurementMode(int mode) throws IllegalStateException {
        int native_setMeasurementMode;
        synchronized (this.mStateLock) {
            if (this.mState != 0) {
                native_setMeasurementMode = native_setMeasurementMode(mode);
            } else {
                throw new IllegalStateException("setMeasurementMode() called in wrong state: " + this.mState);
            }
        }
        return native_setMeasurementMode;
    }

    public int getMeasurementMode() throws IllegalStateException {
        int native_getMeasurementMode;
        synchronized (this.mStateLock) {
            if (this.mState != 0) {
                native_getMeasurementMode = native_getMeasurementMode();
            } else {
                throw new IllegalStateException("getMeasurementMode() called in wrong state: " + this.mState);
            }
        }
        return native_getMeasurementMode;
    }

    public int getSamplingRate() throws IllegalStateException {
        int native_getSamplingRate;
        synchronized (this.mStateLock) {
            if (this.mState != 0) {
                native_getSamplingRate = native_getSamplingRate();
            } else {
                throw new IllegalStateException("getSamplingRate() called in wrong state: " + this.mState);
            }
        }
        return native_getSamplingRate;
    }

    public int getWaveForm(byte[] waveform) throws IllegalStateException {
        int native_getWaveForm;
        synchronized (this.mStateLock) {
            if (this.mState == 2) {
                native_getWaveForm = native_getWaveForm(waveform);
            } else {
                throw new IllegalStateException("getWaveForm() called in wrong state: " + this.mState);
            }
        }
        return native_getWaveForm;
    }

    public int getFft(byte[] fft) throws IllegalStateException {
        int native_getFft;
        synchronized (this.mStateLock) {
            if (this.mState == 2) {
                native_getFft = native_getFft(fft);
            } else {
                throw new IllegalStateException("getFft() called in wrong state: " + this.mState);
            }
        }
        return native_getFft;
    }

    public int getMeasurementPeakRms(MeasurementPeakRms measurement) {
        int native_getPeakRms;
        if (measurement == null) {
            Log.e(TAG, "Cannot store measurements in a null object");
            return -4;
        }
        synchronized (this.mStateLock) {
            if (this.mState == 2) {
                native_getPeakRms = native_getPeakRms(measurement);
            } else {
                throw new IllegalStateException("getMeasurementPeakRms() called in wrong state: " + this.mState);
            }
        }
        return native_getPeakRms;
    }

    public int setDataCaptureListener(OnDataCaptureListener listener, int rate, boolean waveform, boolean fft) {
        int status;
        if (listener == null) {
            waveform = false;
            fft = false;
        }
        synchronized (this.mStateLock) {
            status = native_setPeriodicCapture(rate, waveform, fft);
        }
        if (status == 0) {
            synchronized (this.mListenerLock) {
                this.mCaptureListener = listener;
                if (listener != null && this.mNativeEventHandler == null) {
                    Looper looper = Looper.myLooper();
                    if (looper != null) {
                        this.mNativeEventHandler = new Handler(looper);
                    } else {
                        Looper looper2 = Looper.getMainLooper();
                        if (looper2 != null) {
                            this.mNativeEventHandler = new Handler(looper2);
                        } else {
                            this.mNativeEventHandler = null;
                            status = -3;
                        }
                    }
                }
            }
        }
        return status;
    }

    public int setServerDiedListener(OnServerDiedListener listener) {
        synchronized (this.mListenerLock) {
            this.mServerDiedListener = listener;
        }
        return 0;
    }

    private static void postEventFromNative(Object effect_ref, final int what, final int samplingRate, final byte[] data) {
        Handler handler;
        final Visualizer visualizer = (Visualizer) ((WeakReference) effect_ref).get();
        if (visualizer != null) {
            synchronized (visualizer.mListenerLock) {
                handler = visualizer.mNativeEventHandler;
            }
            if (handler != null) {
                switch (what) {
                    case 0:
                    case 1:
                        handler.post(new Runnable() { // from class: android.media.audiofx.Visualizer$$ExternalSyntheticLambda1
                            @Override // java.lang.Runnable
                            public final void run() {
                                Visualizer.lambda$postEventFromNative$0(Visualizer.this, what, data, samplingRate);
                            }
                        });
                        return;
                    case 2:
                        handler.post(new Runnable() { // from class: android.media.audiofx.Visualizer$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                Visualizer.lambda$postEventFromNative$1(Visualizer.this);
                            }
                        });
                        return;
                    default:
                        Log.e(TAG, "Unknown native event in postEventFromNative: " + what);
                        return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$postEventFromNative$0(Visualizer visualizer, int what, byte[] data, int samplingRate) {
        OnDataCaptureListener l;
        synchronized (visualizer.mListenerLock) {
            l = visualizer.mCaptureListener;
        }
        if (l == null) {
            return;
        }
        if (what == 0) {
            l.onWaveFormDataCapture(visualizer, data, samplingRate);
        } else {
            l.onFftDataCapture(visualizer, data, samplingRate);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$postEventFromNative$1(Visualizer visualizer) {
        OnServerDiedListener l;
        synchronized (visualizer.mListenerLock) {
            l = visualizer.mServerDiedListener;
        }
        if (l != null) {
            l.onServerDied();
        }
    }
}
