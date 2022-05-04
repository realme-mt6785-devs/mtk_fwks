package android.media;

import android.annotation.SystemApi;
import android.app.ActivityThread;
import android.content.AttributionSource;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRouting;
import android.media.IAudioService;
import android.media.audiopolicy.AudioMix;
import android.media.audiopolicy.AudioPolicy;
import android.media.metrics.LogSessionId;
import android.media.projection.MediaProjection;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Pair;
import com.android.internal.util.Preconditions;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
/* loaded from: classes2.dex */
public class AudioRecord implements AudioRouting, MicrophoneDirection, AudioRecordingMonitor, AudioRecordingMonitorClient {
    private static final int AUDIORECORD_ERROR_SETUP_INVALIDCHANNELMASK = -17;
    private static final int AUDIORECORD_ERROR_SETUP_INVALIDFORMAT = -18;
    private static final int AUDIORECORD_ERROR_SETUP_INVALIDSOURCE = -19;
    private static final int AUDIORECORD_ERROR_SETUP_NATIVEINITFAILED = -20;
    private static final int AUDIORECORD_ERROR_SETUP_ZEROFRAMECOUNT = -16;
    public static final int ERROR = -1;
    public static final int ERROR_BAD_VALUE = -2;
    public static final int ERROR_DEAD_OBJECT = -6;
    public static final int ERROR_INVALID_OPERATION = -3;
    private static final long MAX_SHARED_AUDIO_HISTORY_MS = 5000;
    private static final int NATIVE_EVENT_MARKER = 2;
    private static final int NATIVE_EVENT_NEW_POS = 3;
    public static final int READ_BLOCKING = 0;
    public static final int READ_NON_BLOCKING = 1;
    public static final int RECORDSTATE_RECORDING = 3;
    public static final int RECORDSTATE_STOPPED = 1;
    public static final int STATE_INITIALIZED = 1;
    public static final int STATE_UNINITIALIZED = 0;
    public static final String SUBMIX_FIXED_VOLUME = "fixedVolume";
    public static final int SUCCESS = 0;
    private static final String TAG = "android.media.AudioRecord";
    private AudioAttributes mAudioAttributes;
    private AudioPolicy mAudioCapturePolicy;
    private int mAudioFormat;
    private final IAudioRecordExt mAudioRecordExt;
    private int mChannelCount;
    private int mChannelIndexMask;
    private int mChannelMask;
    private NativeEventHandler mEventHandler;
    private final IBinder mICallBack;
    private Looper mInitializationLooper;
    private boolean mIsSubmixFullVolume;
    private LogSessionId mLogSessionId;
    private int mNativeBufferSizeInBytes;
    private long mNativeCallbackCookie;
    private long mNativeDeviceCallback;
    private long mNativeRecorderInJavaObj;
    private OnRecordPositionUpdateListener mPositionListener;
    private final Object mPositionListenerLock;
    private AudioDeviceInfo mPreferredDevice;
    private int mRecordSource;
    AudioRecordingMonitorImpl mRecordingInfoImpl;
    private int mRecordingState;
    private final Object mRecordingStateLock;
    private ArrayMap<AudioRouting.OnRoutingChangedListener, NativeRoutingEventHandlerDelegate> mRoutingChangeListeners;
    private int mSampleRate;
    private int mSessionId;
    private int mState;

    /* loaded from: classes2.dex */
    public interface OnRecordPositionUpdateListener {
        void onMarkerReached(AudioRecord audioRecord);

        void onPeriodicNotification(AudioRecord audioRecord);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface ReadMode {
    }

    private final native void native_disableDeviceCallback();

    private final native void native_enableDeviceCallback();

    private native void native_finalize();

    private native PersistableBundle native_getMetrics();

    private native int native_getPortId();

    private final native int native_getRoutedDeviceId();

    private final native int native_get_active_microphones(ArrayList<MicrophoneInfo> arrayList);

    private final native int native_get_buffer_size_in_frames();

    private final native int native_get_marker_pos();

    private static final native int native_get_min_buff_size(int i, int i2, int i3);

    private final native int native_get_pos_update_period();

    private final native int native_get_timestamp(AudioTimestamp audioTimestamp, int i);

    private final native int native_read_in_byte_array(byte[] bArr, int i, int i2, boolean z);

    private final native int native_read_in_direct_buffer(Object obj, int i, boolean z);

    private final native int native_read_in_float_array(float[] fArr, int i, int i2, boolean z);

    private final native int native_read_in_short_array(short[] sArr, int i, int i2, boolean z);

    private final native boolean native_setInputDevice(int i);

    private native void native_setLogSessionId(String str);

    private final native int native_set_marker_pos(int i);

    private final native int native_set_pos_update_period(int i);

    private native int native_set_preferred_microphone_direction(int i);

    private native int native_set_preferred_microphone_field_dimension(float f);

    private native int native_setup(Object obj, Object obj2, int[] iArr, int i, int i2, int i3, int i4, int[] iArr2, Parcel parcel, long j, int i5);

    private native int native_shareAudioHistory(String str, long j);

    private final native int native_start(int i, int i2);

    private final native void native_stop();

    public final native void native_release();

    public AudioRecord(int audioSource, int sampleRateInHz, int channelConfig, int audioFormat, int bufferSizeInBytes) throws IllegalArgumentException {
        this(new AudioAttributes.Builder().setInternalCapturePreset(audioSource).build(), new AudioFormat.Builder().setChannelMask(getChannelMaskFromLegacyConfig(channelConfig, true)).setEncoding(audioFormat).setSampleRate(sampleRateInHz).build(), bufferSizeInBytes, 0);
    }

    @SystemApi
    public AudioRecord(AudioAttributes attributes, AudioFormat format, int bufferSizeInBytes, int sessionId) throws IllegalArgumentException {
        this(attributes, format, bufferSizeInBytes, sessionId, ActivityThread.currentApplication(), 0);
    }

    private AudioRecord(AudioAttributes attributes, AudioFormat format, int bufferSizeInBytes, int sessionId, Context context, int maxSharedAudioHistoryMs) throws IllegalArgumentException {
        int rate;
        int encoding;
        Throwable th;
        this.mState = 0;
        this.mRecordingState = 1;
        this.mRecordingStateLock = new Object();
        this.mPositionListener = null;
        this.mPositionListenerLock = new Object();
        this.mEventHandler = null;
        this.mInitializationLooper = null;
        this.mNativeBufferSizeInBytes = 0;
        this.mSessionId = 0;
        this.mIsSubmixFullVolume = false;
        this.mLogSessionId = LogSessionId.LOG_SESSION_ID_NONE;
        this.mAudioRecordExt = AudioRecordExtPlugin.constructor.newInstance();
        this.mICallBack = new Binder();
        this.mRoutingChangeListeners = new ArrayMap<>();
        this.mPreferredDevice = null;
        this.mRecordingInfoImpl = new AudioRecordingMonitorImpl(this);
        this.mRecordingState = 1;
        if (attributes == null) {
            throw new IllegalArgumentException("Illegal null AudioAttributes");
        } else if (format != null) {
            Looper myLooper = Looper.myLooper();
            this.mInitializationLooper = myLooper;
            if (myLooper == null) {
                this.mInitializationLooper = Looper.getMainLooper();
            }
            if (attributes.getCapturePreset() == 8) {
                AudioAttributes.Builder filteredAttr = new AudioAttributes.Builder();
                for (String tag : attributes.getTags()) {
                    if (tag.equalsIgnoreCase(SUBMIX_FIXED_VOLUME)) {
                        this.mIsSubmixFullVolume = true;
                        Log.v(TAG, "Will record from REMOTE_SUBMIX at full fixed volume");
                    } else {
                        filteredAttr.addTag(tag);
                    }
                }
                filteredAttr.setInternalCapturePreset(attributes.getCapturePreset());
                this.mAudioAttributes = filteredAttr.build();
            } else {
                this.mAudioAttributes = attributes;
            }
            int rate2 = format.getSampleRate();
            if (rate2 == 0) {
                rate = 0;
            } else {
                rate = rate2;
            }
            if ((format.getPropertySetMask() & 1) != 0) {
                int encoding2 = format.getEncoding();
                encoding = encoding2;
            } else {
                encoding = 1;
            }
            int encoding3 = attributes.getCapturePreset();
            audioParamCheck(encoding3, rate, encoding);
            if ((format.getPropertySetMask() & 8) != 0) {
                this.mChannelIndexMask = format.getChannelIndexMask();
                this.mChannelCount = format.getChannelCount();
            }
            if ((format.getPropertySetMask() & 4) != 0) {
                this.mChannelMask = getChannelMaskFromLegacyConfig(format.getChannelMask(), false);
                this.mChannelCount = format.getChannelCount();
            } else if (this.mChannelIndexMask == 0) {
                int channelMaskFromLegacyConfig = getChannelMaskFromLegacyConfig(1, false);
                this.mChannelMask = channelMaskFromLegacyConfig;
                this.mChannelCount = AudioFormat.channelCountFromInChannelMask(channelMaskFromLegacyConfig);
            }
            audioBuffSizeCheck(bufferSizeInBytes);
            AttributionSource attributionSource = context != null ? context.getAttributionSource() : AttributionSource.myAttributionSource();
            AttributionSource attributionSource2 = attributionSource.getPackageName() == null ? attributionSource.withPackageName("uid:" + Binder.getCallingUid()) : attributionSource;
            int[] sampleRate = {this.mSampleRate};
            int[] session = {sessionId};
            AttributionSource.ScopedParcelState attributionSourceState = attributionSource2.asScopedParcelState();
            try {
                try {
                    int initResult = native_setup(new WeakReference(this), this.mAudioAttributes, sampleRate, this.mChannelMask, this.mChannelIndexMask, this.mAudioFormat, this.mNativeBufferSizeInBytes, session, attributionSourceState.getParcel(), 0L, maxSharedAudioHistoryMs);
                    if (initResult != 0) {
                        loge("Error code " + initResult + " when initializing native AudioRecord object.");
                        if (attributionSourceState != null) {
                            attributionSourceState.close();
                            return;
                        }
                        return;
                    }
                    if (attributionSourceState != null) {
                        attributionSourceState.close();
                    }
                    this.mSampleRate = sampleRate[0];
                    this.mSessionId = session[0];
                    this.mState = 1;
                } catch (Throwable th2) {
                    th = th2;
                    if (attributionSourceState != null) {
                        try {
                            attributionSourceState.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } else {
            throw new IllegalArgumentException("Illegal null AudioFormat");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AudioRecord(long nativeRecordInJavaObj) {
        this.mState = 0;
        this.mRecordingState = 1;
        this.mRecordingStateLock = new Object();
        this.mPositionListener = null;
        this.mPositionListenerLock = new Object();
        this.mEventHandler = null;
        this.mInitializationLooper = null;
        this.mNativeBufferSizeInBytes = 0;
        this.mSessionId = 0;
        this.mIsSubmixFullVolume = false;
        this.mLogSessionId = LogSessionId.LOG_SESSION_ID_NONE;
        this.mAudioRecordExt = AudioRecordExtPlugin.constructor.newInstance();
        this.mICallBack = new Binder();
        this.mRoutingChangeListeners = new ArrayMap<>();
        this.mPreferredDevice = null;
        this.mRecordingInfoImpl = new AudioRecordingMonitorImpl(this);
        this.mNativeRecorderInJavaObj = 0L;
        this.mNativeCallbackCookie = 0L;
        this.mNativeDeviceCallback = 0L;
        if (nativeRecordInJavaObj != 0) {
            deferred_connect(nativeRecordInJavaObj);
        } else {
            this.mState = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unregisterAudioPolicyOnRelease(AudioPolicy audioPolicy) {
        this.mAudioCapturePolicy = audioPolicy;
    }

    void deferred_connect(long nativeRecordInJavaObj) {
        if (this.mState != 1) {
            int[] session = {0};
            int[] rates = {0};
            AttributionSource.ScopedParcelState attributionSourceState = AttributionSource.myAttributionSource().asScopedParcelState();
            try {
                int initResult = native_setup(new WeakReference(this), null, rates, 0, 0, 0, 0, session, attributionSourceState.getParcel(), nativeRecordInJavaObj, 0);
                if (attributionSourceState != null) {
                    attributionSourceState.close();
                }
                if (initResult != 0) {
                    loge("Error code " + initResult + " when initializing native AudioRecord object.");
                    return;
                }
                this.mSessionId = session[0];
                this.mState = 1;
            } catch (Throwable th) {
                if (attributionSourceState != null) {
                    try {
                        attributionSourceState.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
    }

    public AudioAttributes getAudioAttributes() {
        return this.mAudioAttributes;
    }

    /* loaded from: classes2.dex */
    public static class Builder {
        private static final String ERROR_MESSAGE_SOURCE_MISMATCH = "Cannot both set audio source and set playback capture config";
        private static final int PRIVACY_SENSITIVE_DEFAULT = -1;
        private static final int PRIVACY_SENSITIVE_DISABLED = 0;
        private static final int PRIVACY_SENSITIVE_ENABLED = 1;
        private AudioAttributes mAttributes;
        private AudioPlaybackCaptureConfiguration mAudioPlaybackCaptureConfiguration;
        private int mBufferSizeInBytes;
        private Context mContext;
        private AudioFormat mFormat;
        private int mSessionId = 0;
        private int mPrivacySensitive = -1;
        private int mMaxSharedAudioHistoryMs = 0;

        public Builder setAudioSource(int source) throws IllegalArgumentException {
            Preconditions.checkState(this.mAudioPlaybackCaptureConfiguration == null, ERROR_MESSAGE_SOURCE_MISMATCH);
            if (source < 0 || source > MediaRecorder.getAudioSourceMax()) {
                throw new IllegalArgumentException("Invalid audio source " + source);
            }
            this.mAttributes = new AudioAttributes.Builder().setInternalCapturePreset(source).build();
            return this;
        }

        public Builder setContext(Context context) {
            Objects.requireNonNull(context);
            this.mContext = context;
            return this;
        }

        @SystemApi
        public Builder setAudioAttributes(AudioAttributes attributes) throws IllegalArgumentException {
            if (attributes == null) {
                throw new IllegalArgumentException("Illegal null AudioAttributes argument");
            } else if (attributes.getCapturePreset() != -1) {
                this.mAttributes = attributes;
                return this;
            } else {
                throw new IllegalArgumentException("No valid capture preset in AudioAttributes argument");
            }
        }

        public Builder setAudioFormat(AudioFormat format) throws IllegalArgumentException {
            if (format != null) {
                this.mFormat = format;
                return this;
            }
            throw new IllegalArgumentException("Illegal null AudioFormat argument");
        }

        public Builder setBufferSizeInBytes(int bufferSizeInBytes) throws IllegalArgumentException {
            if (bufferSizeInBytes > 0) {
                this.mBufferSizeInBytes = bufferSizeInBytes;
                return this;
            }
            throw new IllegalArgumentException("Invalid buffer size " + bufferSizeInBytes);
        }

        public Builder setAudioPlaybackCaptureConfig(AudioPlaybackCaptureConfiguration config) {
            Preconditions.checkNotNull(config, "Illegal null AudioPlaybackCaptureConfiguration argument");
            Preconditions.checkState(this.mAttributes == null, ERROR_MESSAGE_SOURCE_MISMATCH);
            this.mAudioPlaybackCaptureConfiguration = config;
            return this;
        }

        public Builder setPrivacySensitive(boolean privacySensitive) {
            this.mPrivacySensitive = privacySensitive ? 1 : 0;
            return this;
        }

        @SystemApi
        public Builder setSessionId(int sessionId) throws IllegalArgumentException {
            if (sessionId >= 0) {
                if (this.mSessionId == 0) {
                    this.mSessionId = sessionId;
                } else {
                    Log.e(AudioRecord.TAG, "setSessionId() called twice or after setSharedAudioEvent()");
                }
                return this;
            }
            throw new IllegalArgumentException("Invalid session ID " + sessionId);
        }

        private AudioRecord buildAudioPlaybackCaptureRecord() {
            AudioMix audioMix = this.mAudioPlaybackCaptureConfiguration.createAudioMix(this.mFormat);
            MediaProjection projection = this.mAudioPlaybackCaptureConfiguration.getMediaProjection();
            AudioPolicy audioPolicy = new AudioPolicy.Builder(null).setMediaProjection(projection).addMix(audioMix).build();
            int error = AudioManager.registerAudioPolicyStatic(audioPolicy);
            if (error == 0) {
                AudioRecord record = audioPolicy.createAudioRecordSink(audioMix);
                if (record != null) {
                    record.unregisterAudioPolicyOnRelease(audioPolicy);
                    return record;
                }
                throw new UnsupportedOperationException("Cannot create AudioRecord");
            }
            throw new UnsupportedOperationException("Error: could not register audio policy");
        }

        @SystemApi
        public Builder setMaxSharedAudioHistoryMillis(long maxSharedAudioHistoryMillis) throws IllegalArgumentException {
            if (maxSharedAudioHistoryMillis <= 0 || maxSharedAudioHistoryMillis > 5000) {
                throw new IllegalArgumentException("Illegal maxSharedAudioHistoryMillis argument");
            }
            this.mMaxSharedAudioHistoryMs = (int) maxSharedAudioHistoryMillis;
            return this;
        }

        @SystemApi
        public Builder setSharedAudioEvent(MediaSyncEvent event) throws IllegalArgumentException {
            Objects.requireNonNull(event);
            if (event.getType() != 100) {
                throw new IllegalArgumentException("Invalid event type " + event.getType());
            } else if (event.getAudioSessionId() != 0) {
                this.mSessionId = event.getAudioSessionId();
                return this;
            } else {
                throw new IllegalArgumentException("Invalid session ID " + event.getAudioSessionId());
            }
        }

        public AudioRecord build() throws UnsupportedOperationException {
            if (this.mAudioPlaybackCaptureConfiguration != null) {
                return buildAudioPlaybackCaptureRecord();
            }
            AudioFormat audioFormat = this.mFormat;
            if (audioFormat == null) {
                this.mFormat = new AudioFormat.Builder().setEncoding(2).setChannelMask(16).build();
            } else {
                if (audioFormat.getEncoding() == 0) {
                    this.mFormat = new AudioFormat.Builder(this.mFormat).setEncoding(2).build();
                }
                if (this.mFormat.getChannelMask() == 0 && this.mFormat.getChannelIndexMask() == 0) {
                    this.mFormat = new AudioFormat.Builder(this.mFormat).setChannelMask(16).build();
                }
            }
            boolean z = false;
            if (this.mAttributes == null) {
                this.mAttributes = new AudioAttributes.Builder().setInternalCapturePreset(0).build();
            }
            if (this.mPrivacySensitive != -1) {
                int source = this.mAttributes.getCapturePreset();
                if (source == 8 || source == 1998 || source == 3 || source == 2 || source == 4 || source == 1997) {
                    throw new UnsupportedOperationException("Cannot request private capture with source: " + source);
                }
                AudioAttributes.Builder internalCapturePreset = new AudioAttributes.Builder(this.mAttributes).setInternalCapturePreset(source);
                if (this.mPrivacySensitive == 1) {
                    z = true;
                }
                this.mAttributes = internalCapturePreset.setPrivacySensitive(z).build();
            }
            try {
                if (this.mBufferSizeInBytes == 0) {
                    this.mBufferSizeInBytes = this.mFormat.getChannelCount() * AudioFormat.getBytesPerSample(this.mFormat.getEncoding());
                }
                AudioRecord record = new AudioRecord(this.mAttributes, this.mFormat, this.mBufferSizeInBytes, this.mSessionId, this.mContext, this.mMaxSharedAudioHistoryMs);
                if (record.getState() != 0) {
                    return record;
                }
                throw new UnsupportedOperationException("Cannot create AudioRecord");
            } catch (IllegalArgumentException e) {
                throw new UnsupportedOperationException(e.getMessage());
            }
        }
    }

    private static int getChannelMaskFromLegacyConfig(int inChannelConfig, boolean allowLegacyConfig) {
        int mask;
        switch (inChannelConfig) {
            case 1:
            case 2:
            case 16:
                mask = 16;
                break;
            case 3:
            case 12:
                mask = 12;
                break;
            case 48:
                mask = inChannelConfig;
                break;
            case AudioFormat.CHANNEL_IN_2POINT0POINT2 /* 6291468 */:
                mask = inChannelConfig;
                break;
            default:
                throw new IllegalArgumentException("Unsupported channel configuration.");
        }
        if (allowLegacyConfig || (inChannelConfig != 2 && inChannelConfig != 3)) {
            return mask;
        }
        throw new IllegalArgumentException("Unsupported deprecated configuration.");
    }

    private void audioParamCheck(int audioSource, int sampleRateInHz, int audioFormat) throws IllegalArgumentException {
        if (audioSource < 0 || !(audioSource <= MediaRecorder.getAudioSourceMax() || audioSource == 1998 || audioSource == 1997 || audioSource == 1999)) {
            throw new IllegalArgumentException("Invalid audio source " + audioSource);
        }
        this.mRecordSource = audioSource;
        if ((sampleRateInHz < AudioFormat.SAMPLE_RATE_HZ_MIN || sampleRateInHz > AudioFormat.SAMPLE_RATE_HZ_MAX) && sampleRateInHz != 0) {
            throw new IllegalArgumentException(sampleRateInHz + "Hz is not a supported sample rate.");
        }
        this.mSampleRate = sampleRateInHz;
        switch (audioFormat) {
            case 1:
                this.mAudioFormat = 2;
                return;
            case 2:
            case 3:
            case 4:
            case 21:
            case 22:
                this.mAudioFormat = audioFormat;
                return;
            default:
                throw new IllegalArgumentException("Unsupported sample encoding " + audioFormat + ". Should be ENCODING_PCM_8BIT, ENCODING_PCM_16BIT, ENCODING_PCM_24BIT_PACKED, ENCODING_PCM_32BIT, or ENCODING_PCM_FLOAT.");
        }
    }

    private void audioBuffSizeCheck(int audioBufferSize) throws IllegalArgumentException {
        int frameSizeInBytes = this.mChannelCount * AudioFormat.getBytesPerSample(this.mAudioFormat);
        if (audioBufferSize % frameSizeInBytes != 0 || audioBufferSize < 1) {
            throw new IllegalArgumentException("Invalid audio buffer size " + audioBufferSize + " (frame size " + frameSizeInBytes + ")");
        }
        this.mNativeBufferSizeInBytes = audioBufferSize;
    }

    public void release() {
        try {
            stop();
        } catch (IllegalStateException e) {
        }
        AudioPolicy audioPolicy = this.mAudioCapturePolicy;
        if (audioPolicy != null) {
            AudioManager.unregisterAudioPolicyAsyncStatic(audioPolicy);
            this.mAudioCapturePolicy = null;
        }
        native_release();
        this.mState = 0;
    }

    protected void finalize() {
        release();
    }

    public int getSampleRate() {
        return this.mSampleRate;
    }

    public int getAudioSource() {
        return this.mRecordSource;
    }

    public int getAudioFormat() {
        return this.mAudioFormat;
    }

    public int getChannelConfiguration() {
        return this.mChannelMask;
    }

    public AudioFormat getFormat() {
        AudioFormat.Builder builder = new AudioFormat.Builder().setSampleRate(this.mSampleRate).setEncoding(this.mAudioFormat);
        int i = this.mChannelMask;
        if (i != 0) {
            builder.setChannelMask(i);
        }
        int i2 = this.mChannelIndexMask;
        if (i2 != 0) {
            builder.setChannelIndexMask(i2);
        }
        return builder.build();
    }

    public int getChannelCount() {
        return this.mChannelCount;
    }

    public int getState() {
        return this.mState;
    }

    public int getRecordingState() {
        int i;
        synchronized (this.mRecordingStateLock) {
            i = this.mRecordingState;
        }
        return i;
    }

    public int getBufferSizeInFrames() {
        return native_get_buffer_size_in_frames();
    }

    public int getNotificationMarkerPosition() {
        return native_get_marker_pos();
    }

    public int getPositionNotificationPeriod() {
        return native_get_pos_update_period();
    }

    public int getTimestamp(AudioTimestamp outTimestamp, int timebase) {
        if (outTimestamp != null && (timebase == 1 || timebase == 0)) {
            return native_get_timestamp(outTimestamp, timebase);
        }
        throw new IllegalArgumentException();
    }

    public static int getMinBufferSize(int sampleRateInHz, int channelConfig, int audioFormat) {
        int channelCount;
        switch (channelConfig) {
            case 1:
            case 2:
            case 16:
                channelCount = 1;
                break;
            case 3:
            case 12:
            case 48:
                channelCount = 2;
                break;
            case AudioFormat.CHANNEL_IN_2POINT0POINT2 /* 6291468 */:
                channelCount = 4;
                break;
            default:
                loge("getMinBufferSize(): Invalid channel configuration.");
                return -2;
        }
        int size = native_get_min_buff_size(sampleRateInHz, channelCount, audioFormat);
        if (size == 0) {
            return -2;
        }
        if (size == -1) {
            return -1;
        }
        return size;
    }

    public int getAudioSessionId() {
        return this.mSessionId;
    }

    public boolean isPrivacySensitive() {
        return (this.mAudioAttributes.getAllFlags() & 8192) != 0;
    }

    public void startRecording() throws IllegalStateException {
        if (this.mAudioRecordExt.permInterceptInStartRecording()) {
            throw new IllegalStateException("permission denied");
        } else if (this.mState == 1) {
            synchronized (this.mRecordingStateLock) {
                if (native_start(0, 0) == 0) {
                    handleFullVolumeRec(true);
                    this.mRecordingState = 3;
                }
            }
        } else {
            throw new IllegalStateException("startRecording() called on an uninitialized AudioRecord.");
        }
    }

    public void startRecording(MediaSyncEvent syncEvent) throws IllegalStateException {
        if (this.mState != 1) {
            throw new IllegalStateException("startRecording() called on an uninitialized AudioRecord.");
        } else if (!this.mAudioRecordExt.permInterceptInStartRecordingWithEvent()) {
            synchronized (this.mRecordingStateLock) {
                if (native_start(syncEvent.getType(), syncEvent.getAudioSessionId()) == 0) {
                    handleFullVolumeRec(true);
                    this.mRecordingState = 3;
                }
            }
        } else {
            throw new IllegalStateException("permission denied");
        }
    }

    public void stop() throws IllegalStateException {
        if (this.mState == 1) {
            synchronized (this.mRecordingStateLock) {
                handleFullVolumeRec(false);
                native_stop();
                this.mRecordingState = 1;
            }
            return;
        }
        throw new IllegalStateException("stop() called on an uninitialized AudioRecord.");
    }

    private void handleFullVolumeRec(boolean starting) {
        if (this.mIsSubmixFullVolume) {
            IBinder b = ServiceManager.getService("audio");
            IAudioService ias = IAudioService.Stub.asInterface(b);
            try {
                ias.forceRemoteSubmixFullVolume(starting, this.mICallBack);
            } catch (RemoteException e) {
                Log.e(TAG, "Error talking to AudioService when handling full submix volume", e);
            }
        }
    }

    public int read(byte[] audioData, int offsetInBytes, int sizeInBytes) {
        return read(audioData, offsetInBytes, sizeInBytes, 0);
    }

    public int read(byte[] audioData, int offsetInBytes, int sizeInBytes, int readMode) {
        boolean z = true;
        if (this.mState != 1 || this.mAudioFormat == 4) {
            return -3;
        }
        if (readMode != 0 && readMode != 1) {
            Log.e(TAG, "AudioRecord.read() called with invalid blocking mode");
            return -2;
        } else if (audioData == null || offsetInBytes < 0 || sizeInBytes < 0 || offsetInBytes + sizeInBytes < 0 || offsetInBytes + sizeInBytes > audioData.length) {
            return -2;
        } else {
            if (readMode != 0) {
                z = false;
            }
            return native_read_in_byte_array(audioData, offsetInBytes, sizeInBytes, z);
        }
    }

    public int read(short[] audioData, int offsetInShorts, int sizeInShorts) {
        return read(audioData, offsetInShorts, sizeInShorts, 0);
    }

    public int read(short[] audioData, int offsetInShorts, int sizeInShorts, int readMode) {
        int i;
        boolean z = true;
        if (this.mState != 1 || (i = this.mAudioFormat) == 4 || i > 20) {
            return -3;
        }
        if (readMode != 0 && readMode != 1) {
            Log.e(TAG, "AudioRecord.read() called with invalid blocking mode");
            return -2;
        } else if (audioData == null || offsetInShorts < 0 || sizeInShorts < 0 || offsetInShorts + sizeInShorts < 0 || offsetInShorts + sizeInShorts > audioData.length) {
            return -2;
        } else {
            if (readMode != 0) {
                z = false;
            }
            return native_read_in_short_array(audioData, offsetInShorts, sizeInShorts, z);
        }
    }

    public int read(float[] audioData, int offsetInFloats, int sizeInFloats, int readMode) {
        if (this.mState == 0) {
            Log.e(TAG, "AudioRecord.read() called in invalid state STATE_UNINITIALIZED");
            return -3;
        } else if (this.mAudioFormat != 4) {
            Log.e(TAG, "AudioRecord.read(float[] ...) requires format ENCODING_PCM_FLOAT");
            return -3;
        } else {
            boolean z = true;
            if (readMode != 0 && readMode != 1) {
                Log.e(TAG, "AudioRecord.read() called with invalid blocking mode");
                return -2;
            } else if (audioData == null || offsetInFloats < 0 || sizeInFloats < 0 || offsetInFloats + sizeInFloats < 0 || offsetInFloats + sizeInFloats > audioData.length) {
                return -2;
            } else {
                if (readMode != 0) {
                    z = false;
                }
                return native_read_in_float_array(audioData, offsetInFloats, sizeInFloats, z);
            }
        }
    }

    public int read(ByteBuffer audioBuffer, int sizeInBytes) {
        return read(audioBuffer, sizeInBytes, 0);
    }

    public int read(ByteBuffer audioBuffer, int sizeInBytes, int readMode) {
        boolean z = true;
        if (this.mState != 1) {
            return -3;
        }
        if (readMode != 0 && readMode != 1) {
            Log.e(TAG, "AudioRecord.read() called with invalid blocking mode");
            return -2;
        } else if (audioBuffer == null || sizeInBytes < 0) {
            return -2;
        } else {
            if (readMode != 0) {
                z = false;
            }
            return native_read_in_direct_buffer(audioBuffer, sizeInBytes, z);
        }
    }

    public PersistableBundle getMetrics() {
        PersistableBundle bundle = native_getMetrics();
        return bundle;
    }

    public void setRecordPositionUpdateListener(OnRecordPositionUpdateListener listener) {
        setRecordPositionUpdateListener(listener, null);
    }

    public void setRecordPositionUpdateListener(OnRecordPositionUpdateListener listener, Handler handler) {
        synchronized (this.mPositionListenerLock) {
            this.mPositionListener = listener;
            if (listener == null) {
                this.mEventHandler = null;
            } else if (handler != null) {
                this.mEventHandler = new NativeEventHandler(this, handler.getLooper());
            } else {
                this.mEventHandler = new NativeEventHandler(this, this.mInitializationLooper);
            }
        }
    }

    public int setNotificationMarkerPosition(int markerInFrames) {
        if (this.mState == 0) {
            return -3;
        }
        return native_set_marker_pos(markerInFrames);
    }

    @Override // android.media.AudioRouting
    public AudioDeviceInfo getRoutedDevice() {
        int deviceId = native_getRoutedDeviceId();
        if (deviceId == 0) {
            return null;
        }
        return AudioManager.getDeviceForPortId(deviceId, 1);
    }

    @SystemApi
    public static long getMaxSharedAudioHistoryMillis() {
        return 5000L;
    }

    @SystemApi
    public MediaSyncEvent shareAudioHistory(String sharedPackage, long startFromMillis) {
        Objects.requireNonNull(sharedPackage);
        if (startFromMillis >= 0) {
            int status = native_shareAudioHistory(sharedPackage, startFromMillis);
            if (status == -2) {
                throw new IllegalArgumentException("Illegal sharedAudioHistoryMs argument");
            } else if (status != -4) {
                MediaSyncEvent event = MediaSyncEvent.createEvent(100);
                event.setAudioSessionId(this.mSessionId);
                return event;
            } else {
                throw new SecurityException("permission CAPTURE_AUDIO_HOTWORD required");
            }
        } else {
            throw new IllegalArgumentException("Illegal negative sharedAudioHistoryMs argument");
        }
    }

    private void testEnableNativeRoutingCallbacksLocked() {
        if (this.mRoutingChangeListeners.size() == 0) {
            native_enableDeviceCallback();
        }
    }

    private void testDisableNativeRoutingCallbacksLocked() {
        if (this.mRoutingChangeListeners.size() == 0) {
            native_disableDeviceCallback();
        }
    }

    @Override // android.media.AudioRouting
    public void addOnRoutingChangedListener(AudioRouting.OnRoutingChangedListener listener, Handler handler) {
        synchronized (this.mRoutingChangeListeners) {
            if (listener != null) {
                if (!this.mRoutingChangeListeners.containsKey(listener)) {
                    testEnableNativeRoutingCallbacksLocked();
                    this.mRoutingChangeListeners.put(listener, new NativeRoutingEventHandlerDelegate(this, listener, handler != null ? handler : new Handler(this.mInitializationLooper)));
                }
            }
        }
    }

    @Override // android.media.AudioRouting
    public void removeOnRoutingChangedListener(AudioRouting.OnRoutingChangedListener listener) {
        synchronized (this.mRoutingChangeListeners) {
            if (this.mRoutingChangeListeners.containsKey(listener)) {
                this.mRoutingChangeListeners.remove(listener);
                testDisableNativeRoutingCallbacksLocked();
            }
        }
    }

    @Deprecated
    /* loaded from: classes2.dex */
    public interface OnRoutingChangedListener extends AudioRouting.OnRoutingChangedListener {
        void onRoutingChanged(AudioRecord audioRecord);

        @Override // android.media.AudioRouting.OnRoutingChangedListener
        default void onRoutingChanged(AudioRouting router) {
            if (router instanceof AudioRecord) {
                onRoutingChanged((AudioRecord) router);
            }
        }
    }

    @Deprecated
    public void addOnRoutingChangedListener(OnRoutingChangedListener listener, Handler handler) {
        addOnRoutingChangedListener((AudioRouting.OnRoutingChangedListener) listener, handler);
    }

    @Deprecated
    public void removeOnRoutingChangedListener(OnRoutingChangedListener listener) {
        removeOnRoutingChangedListener((AudioRouting.OnRoutingChangedListener) listener);
    }

    private void broadcastRoutingChange() {
        AudioManager.resetAudioPortGeneration();
        synchronized (this.mRoutingChangeListeners) {
            for (NativeRoutingEventHandlerDelegate delegate : this.mRoutingChangeListeners.values()) {
                delegate.notifyClient();
            }
        }
    }

    public int setPositionNotificationPeriod(int periodInFrames) {
        if (this.mState == 0) {
            return -3;
        }
        return native_set_pos_update_period(periodInFrames);
    }

    @Override // android.media.AudioRouting
    public boolean setPreferredDevice(AudioDeviceInfo deviceInfo) {
        int preferredDeviceId = 0;
        if (deviceInfo != null && !deviceInfo.isSource()) {
            return false;
        }
        if (deviceInfo != null) {
            preferredDeviceId = deviceInfo.getId();
        }
        boolean status = native_setInputDevice(preferredDeviceId);
        if (status) {
            synchronized (this) {
                this.mPreferredDevice = deviceInfo;
            }
        }
        return status;
    }

    @Override // android.media.AudioRouting
    public AudioDeviceInfo getPreferredDevice() {
        AudioDeviceInfo audioDeviceInfo;
        synchronized (this) {
            audioDeviceInfo = this.mPreferredDevice;
        }
        return audioDeviceInfo;
    }

    public List<MicrophoneInfo> getActiveMicrophones() throws IOException {
        AudioDeviceInfo device;
        ArrayList<MicrophoneInfo> activeMicrophones = new ArrayList<>();
        int status = native_get_active_microphones(activeMicrophones);
        if (status != 0) {
            if (status != -3) {
                Log.e(TAG, "getActiveMicrophones failed:" + status);
            }
            Log.i(TAG, "getActiveMicrophones failed, fallback on routed device info");
        }
        AudioManager.setPortIdForMicrophones(activeMicrophones);
        if (activeMicrophones.size() == 0 && (device = getRoutedDevice()) != null) {
            MicrophoneInfo microphone = AudioManager.microphoneInfoFromAudioDeviceInfo(device);
            ArrayList<Pair<Integer, Integer>> channelMapping = new ArrayList<>();
            for (int i = 0; i < this.mChannelCount; i++) {
                channelMapping.add(new Pair<>(Integer.valueOf(i), 1));
            }
            microphone.setChannelMapping(channelMapping);
            activeMicrophones.add(microphone);
        }
        return activeMicrophones;
    }

    @Override // android.media.AudioRecordingMonitor
    public void registerAudioRecordingCallback(Executor executor, AudioManager.AudioRecordingCallback cb) {
        this.mRecordingInfoImpl.registerAudioRecordingCallback(executor, cb);
    }

    @Override // android.media.AudioRecordingMonitor
    public void unregisterAudioRecordingCallback(AudioManager.AudioRecordingCallback cb) {
        this.mRecordingInfoImpl.unregisterAudioRecordingCallback(cb);
    }

    @Override // android.media.AudioRecordingMonitor
    public AudioRecordingConfiguration getActiveRecordingConfiguration() {
        return this.mRecordingInfoImpl.getActiveRecordingConfiguration();
    }

    @Override // android.media.AudioRecordingMonitorClient
    public int getPortId() {
        if (this.mNativeRecorderInJavaObj == 0) {
            return 0;
        }
        try {
            return native_getPortId();
        } catch (IllegalStateException e) {
            return 0;
        }
    }

    @Override // android.media.MicrophoneDirection
    public boolean setPreferredMicrophoneDirection(int direction) {
        return native_set_preferred_microphone_direction(direction) == 0;
    }

    @Override // android.media.MicrophoneDirection
    public boolean setPreferredMicrophoneFieldDimension(float zoom) {
        Preconditions.checkArgument(zoom >= -1.0f && zoom <= 1.0f, "Argument must fall between -1 & 1 (inclusive)");
        return native_set_preferred_microphone_field_dimension(zoom) == 0;
    }

    public void setLogSessionId(LogSessionId logSessionId) {
        Objects.requireNonNull(logSessionId);
        if (this.mState != 0) {
            String stringId = logSessionId.getStringId();
            native_setLogSessionId(stringId);
            this.mLogSessionId = logSessionId;
            return;
        }
        throw new IllegalStateException("AudioRecord not initialized");
    }

    public LogSessionId getLogSessionId() {
        return this.mLogSessionId;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class NativeEventHandler extends Handler {
        private final AudioRecord mAudioRecord;

        NativeEventHandler(AudioRecord recorder, Looper looper) {
            super(looper);
            this.mAudioRecord = recorder;
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            OnRecordPositionUpdateListener listener;
            synchronized (AudioRecord.this.mPositionListenerLock) {
                listener = this.mAudioRecord.mPositionListener;
            }
            switch (msg.what) {
                case 2:
                    if (listener != null) {
                        listener.onMarkerReached(this.mAudioRecord);
                        return;
                    }
                    return;
                case 3:
                    if (listener != null) {
                        listener.onPeriodicNotification(this.mAudioRecord);
                        return;
                    }
                    return;
                default:
                    AudioRecord.loge("Unknown native event type: " + msg.what);
                    return;
            }
        }
    }

    private static void postEventFromNative(Object audiorecord_ref, int what, int arg1, int arg2, Object obj) {
        AudioRecord recorder = (AudioRecord) ((WeakReference) audiorecord_ref).get();
        if (recorder != null) {
            if (what == 1000) {
                recorder.broadcastRoutingChange();
                return;
            }
            NativeEventHandler nativeEventHandler = recorder.mEventHandler;
            if (nativeEventHandler != null) {
                Message m = nativeEventHandler.obtainMessage(what, arg1, arg2, obj);
                recorder.mEventHandler.sendMessage(m);
            }
        }
    }

    @Deprecated
    private int native_setup(Object audiorecordThis, Object attributes, int[] sampleRate, int channelMask, int channelIndexMask, int audioFormat, int buffSizeInBytes, int[] sessionId, String opPackageName, long nativeRecordInJavaObj) {
        AttributionSource attributionSource = AttributionSource.myAttributionSource().withPackageName(opPackageName);
        AttributionSource.ScopedParcelState attributionSourceState = attributionSource.asScopedParcelState();
        try {
            int native_setup = native_setup(audiorecordThis, attributes, sampleRate, channelMask, channelIndexMask, audioFormat, buffSizeInBytes, sessionId, attributionSourceState.getParcel(), nativeRecordInJavaObj, 0);
            if (attributionSourceState != null) {
                attributionSourceState.close();
            }
            return native_setup;
        } catch (Throwable th) {
            if (attributionSourceState != null) {
                try {
                    attributionSourceState.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private static void logd(String msg) {
        Log.d(TAG, msg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void loge(String msg) {
        Log.e(TAG, msg);
    }

    /* loaded from: classes2.dex */
    public static final class MetricsConstants {
        public static final String ATTRIBUTES = "android.media.audiorecord.attributes";
        public static final String CHANNELS = "android.media.audiorecord.channels";
        public static final String CHANNEL_MASK = "android.media.audiorecord.channelMask";
        public static final String DURATION_MS = "android.media.audiorecord.durationMs";
        public static final String ENCODING = "android.media.audiorecord.encoding";
        public static final String FRAME_COUNT = "android.media.audiorecord.frameCount";
        @Deprecated
        public static final String LATENCY = "android.media.audiorecord.latency";
        private static final String MM_PREFIX = "android.media.audiorecord.";
        public static final String PORT_ID = "android.media.audiorecord.portId";
        public static final String SAMPLERATE = "android.media.audiorecord.samplerate";
        public static final String SOURCE = "android.media.audiorecord.source";
        public static final String START_COUNT = "android.media.audiorecord.startCount";

        private MetricsConstants() {
        }
    }
}
