package android.media;

import android.annotation.SystemApi;
import android.content.IntentFilter;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioRouting;
import android.media.AudioTrack;
import android.media.Utils;
import android.media.VolumeShaper;
import android.media.metrics.LogSessionId;
import android.net.TrafficStats;
import android.opengl.GLES30;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.PersistableBundle;
import android.util.ArrayMap;
import android.util.Log;
import com.mediatek.powerhalmgr.PowerHalMgr;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.NioUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
/* loaded from: classes2.dex */
public class AudioTrack extends PlayerBase implements AudioRouting, VolumeAutomation {
    private static final int AUDIO_OUTPUT_FLAG_DEEP_BUFFER = 8;
    private static final int AUDIO_OUTPUT_FLAG_FAST = 4;
    private static final HashMap<String, Integer> CHANNEL_PAIR_MAP = new HashMap<String, Integer>() { // from class: android.media.AudioTrack.1
        {
            put("front", 12);
            put("back", 192);
            put("front of center", 768);
            put("side", Integer.valueOf((int) GLES30.GL_COLOR));
            put("top front", 81920);
            put("top back", 655360);
            put("top side", Integer.valueOf((int) IntentFilter.MATCH_CATEGORY_HOST));
            put("bottom front", Integer.valueOf((int) PowerHalMgr.PERF_RES_SCHED_BOOST_VALUE_ROOT));
        }
    };
    public static final int DUAL_MONO_MODE_LL = 2;
    public static final int DUAL_MONO_MODE_LR = 1;
    public static final int DUAL_MONO_MODE_OFF = 0;
    public static final int DUAL_MONO_MODE_RR = 3;
    public static final int ENCAPSULATION_METADATA_TYPE_DVB_AD_DESCRIPTOR = 2;
    public static final int ENCAPSULATION_METADATA_TYPE_FRAMEWORK_TUNER = 1;
    public static final int ENCAPSULATION_METADATA_TYPE_NONE = 0;
    public static final int ENCAPSULATION_MODE_ELEMENTARY_STREAM = 1;
    @SystemApi
    public static final int ENCAPSULATION_MODE_HANDLE = 2;
    public static final int ENCAPSULATION_MODE_NONE = 0;
    public static final int ERROR = -1;
    public static final int ERROR_BAD_VALUE = -2;
    public static final int ERROR_DEAD_OBJECT = -6;
    public static final int ERROR_INVALID_OPERATION = -3;
    private static final int ERROR_NATIVESETUP_AUDIOSYSTEM = -16;
    private static final int ERROR_NATIVESETUP_INVALIDCHANNELMASK = -17;
    private static final int ERROR_NATIVESETUP_INVALIDFORMAT = -18;
    private static final int ERROR_NATIVESETUP_INVALIDSTREAMTYPE = -19;
    private static final int ERROR_NATIVESETUP_NATIVEINITFAILED = -20;
    public static final int ERROR_WOULD_BLOCK = -7;
    private static final float GAIN_MAX = 1.0f;
    private static final float GAIN_MIN = 0.0f;
    private static final float HEADER_V2_SIZE_BYTES = 20.0f;
    private static final float MAX_AUDIO_DESCRIPTION_MIX_LEVEL = 48.0f;
    public static final int MODE_STATIC = 0;
    public static final int MODE_STREAM = 1;
    private static final int NATIVE_EVENT_CAN_WRITE_MORE_DATA = 9;
    private static final int NATIVE_EVENT_CODEC_FORMAT_CHANGE = 100;
    private static final int NATIVE_EVENT_MARKER = 3;
    private static final int NATIVE_EVENT_NEW_IAUDIOTRACK = 6;
    private static final int NATIVE_EVENT_NEW_POS = 4;
    private static final int NATIVE_EVENT_STREAM_END = 7;
    public static final int PERFORMANCE_MODE_LOW_LATENCY = 1;
    public static final int PERFORMANCE_MODE_NONE = 0;
    public static final int PERFORMANCE_MODE_POWER_SAVING = 2;
    public static final int PLAYSTATE_PAUSED = 2;
    private static final int PLAYSTATE_PAUSED_STOPPING = 5;
    public static final int PLAYSTATE_PLAYING = 3;
    public static final int PLAYSTATE_STOPPED = 1;
    private static final int PLAYSTATE_STOPPING = 4;
    public static final int STATE_INITIALIZED = 1;
    public static final int STATE_NO_STATIC_DATA = 2;
    public static final int STATE_UNINITIALIZED = 0;
    public static final int SUCCESS = 0;
    private static final int SUPPORTED_OUT_CHANNELS = 67108860;
    private static final String TAG = "android.media.AudioTrack";
    public static final int WRITE_BLOCKING = 0;
    public static final int WRITE_NON_BLOCKING = 1;
    private int mAudioFormat;
    private int mAvSyncBytesRemaining;
    private ByteBuffer mAvSyncHeader;
    private int mChannelConfiguration;
    private int mChannelCount;
    private int mChannelIndexMask;
    private int mChannelMask;
    private final Utils.ListenerList<AudioMetadataReadMap> mCodecFormatChangedListeners;
    private AudioAttributes mConfiguredAudioAttributes;
    private int mDataLoadMode;
    private boolean mEnableSelfRoutingMonitor;
    private NativePositionEventHandlerDelegate mEventHandlerDelegate;
    private final Looper mInitializationLooper;
    private long mJniData;
    private LogSessionId mLogSessionId;
    private int mNativeBufferSizeInBytes;
    private int mNativeBufferSizeInFrames;
    protected long mNativeTrackInJavaObj;
    private int mOffloadDelayFrames;
    private boolean mOffloadEosPending;
    private int mOffloadPaddingFrames;
    private boolean mOffloaded;
    private int mOffset;
    private int mPlayState;
    private final Object mPlayStateLock;
    private AudioDeviceInfo mPreferredDevice;
    private ArrayMap<AudioRouting.OnRoutingChangedListener, NativeRoutingEventHandlerDelegate> mRoutingChangeListeners;
    private int mSampleRate;
    private int mSessionId;
    private int mState;
    private LinkedList<StreamEventCbInfo> mStreamEventCbInfoList;
    private final Object mStreamEventCbLock;
    private volatile StreamEventHandler mStreamEventHandler;
    private HandlerThread mStreamEventHandlerThread;
    private int mStreamType;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface DualMonoMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface EncapsulationMetadataType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface EncapsulationMode {
    }

    /* loaded from: classes2.dex */
    public interface OnCodecFormatChangedListener {
        void onCodecFormatChanged(AudioTrack audioTrack, AudioMetadataReadMap audioMetadataReadMap);
    }

    /* loaded from: classes2.dex */
    public interface OnPlaybackPositionUpdateListener {
        void onMarkerReached(AudioTrack audioTrack);

        void onPeriodicNotification(AudioTrack audioTrack);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface PerformanceMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface TransferMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface WriteMode {
    }

    private native int native_applyVolumeShaper(VolumeShaper.Configuration configuration, VolumeShaper.Operation operation);

    private final native int native_attachAuxEffect(int i);

    private final native void native_disableDeviceCallback();

    private final native void native_enableDeviceCallback();

    private final native void native_finalize();

    private final native void native_flush();

    private native PersistableBundle native_getMetrics();

    private native int native_getPortId();

    private final native int native_getRoutedDeviceId();

    private native int native_getStartThresholdInFrames();

    private native VolumeShaper.State native_getVolumeShaperState(int i);

    private native int native_get_audio_description_mix_level_db(float[] fArr);

    private final native int native_get_buffer_capacity_frames();

    private final native int native_get_buffer_size_frames();

    private native int native_get_dual_mono_mode(int[] iArr);

    private final native int native_get_flags();

    private final native int native_get_latency();

    private final native int native_get_marker_pos();

    private static final native int native_get_min_buff_size(int i, int i2, int i3);

    private static final native int native_get_output_sample_rate(int i);

    private final native PlaybackParams native_get_playback_params();

    private final native int native_get_playback_rate();

    private final native int native_get_pos_update_period();

    private final native int native_get_position();

    private final native int native_get_timestamp(long[] jArr);

    private final native int native_get_underrun_count();

    private static native boolean native_is_direct_output_supported(int i, int i2, int i3, int i4, int i5, int i6, int i7);

    private final native void native_pause();

    private final native int native_reload_static();

    private final native int native_setAuxEffectSendLevel(float f);

    private native void native_setLogSessionId(String str);

    private final native boolean native_setOutputDevice(int i);

    private native void native_setPlayerIId(int i);

    private final native int native_setPresentation(int i, int i2);

    private native int native_setStartThresholdInFrames(int i);

    private final native void native_setVolume(float f, float f2);

    private native int native_set_audio_description_mix_level_db(float f);

    private final native int native_set_buffer_size_frames(int i);

    private native void native_set_delay_padding(int i, int i2);

    private native int native_set_dual_mono_mode(int i);

    private final native int native_set_loop(int i, int i2, int i3);

    private final native int native_set_marker_pos(int i);

    private final native void native_set_playback_params(PlaybackParams playbackParams);

    private final native int native_set_playback_rate(int i);

    private final native int native_set_pos_update_period(int i);

    private final native int native_set_position(int i);

    private final native int native_setup(Object obj, Object obj2, int[] iArr, int i, int i2, int i3, int i4, int i5, int[] iArr2, long j, boolean z, int i6, Object obj3, String str);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void native_start();

    private final native void native_stop();

    private final native int native_write_byte(byte[] bArr, int i, int i2, int i3, boolean z);

    private final native int native_write_float(float[] fArr, int i, int i2, int i3, boolean z);

    private final native int native_write_native_bytes(ByteBuffer byteBuffer, int i, int i2, int i3, boolean z);

    private final native int native_write_short(short[] sArr, int i, int i2, int i3, boolean z);

    public final native void native_release();

    public AudioTrack(int streamType, int sampleRateInHz, int channelConfig, int audioFormat, int bufferSizeInBytes, int mode) throws IllegalArgumentException {
        this(streamType, sampleRateInHz, channelConfig, audioFormat, bufferSizeInBytes, mode, 0);
    }

    public AudioTrack(int streamType, int sampleRateInHz, int channelConfig, int audioFormat, int bufferSizeInBytes, int mode, int sessionId) throws IllegalArgumentException {
        this(new AudioAttributes.Builder().setLegacyStreamType(streamType).build(), new AudioFormat.Builder().setChannelMask(channelConfig).setEncoding(audioFormat).setSampleRate(sampleRateInHz).build(), bufferSizeInBytes, mode, sessionId);
        deprecateStreamTypeForPlayback(streamType, "AudioTrack", "AudioTrack()");
    }

    public AudioTrack(AudioAttributes attributes, AudioFormat format, int bufferSizeInBytes, int mode, int sessionId) throws IllegalArgumentException {
        this(attributes, format, bufferSizeInBytes, mode, sessionId, false, 0, null);
    }

    private AudioTrack(AudioAttributes attributes, AudioFormat format, int bufferSizeInBytes, int mode, int sessionId, boolean offload, int encapsulationMode, TunerConfiguration tunerConfiguration) throws IllegalArgumentException {
        super(attributes, 1);
        Looper looper;
        int rate;
        int channelIndexMask;
        int channelMask;
        int encoding;
        int frameSizeInBytes;
        this.mState = 0;
        this.mPlayState = 1;
        this.mOffloadEosPending = false;
        this.mPlayStateLock = new Object();
        this.mNativeBufferSizeInBytes = 0;
        this.mNativeBufferSizeInFrames = 0;
        this.mChannelCount = 1;
        this.mChannelMask = 4;
        this.mStreamType = 3;
        this.mDataLoadMode = 1;
        this.mChannelConfiguration = 4;
        this.mChannelIndexMask = 0;
        this.mSessionId = 0;
        this.mAvSyncHeader = null;
        this.mAvSyncBytesRemaining = 0;
        this.mOffset = 0;
        this.mOffloaded = false;
        this.mOffloadDelayFrames = 0;
        this.mOffloadPaddingFrames = 0;
        this.mLogSessionId = LogSessionId.LOG_SESSION_ID_NONE;
        this.mPreferredDevice = null;
        this.mRoutingChangeListeners = new ArrayMap<>();
        this.mCodecFormatChangedListeners = new Utils.ListenerList<>();
        this.mStreamEventCbLock = new Object();
        this.mStreamEventCbInfoList = new LinkedList<>();
        this.mConfiguredAudioAttributes = attributes;
        if (format != null) {
            if (shouldEnablePowerSaving(this.mAttributes, format, bufferSizeInBytes, mode)) {
                this.mAttributes = new AudioAttributes.Builder(this.mAttributes).replaceFlags((this.mAttributes.getAllFlags() | 512) & TrafficStats.TAG_NETWORK_STACK_RANGE_END).build();
            }
            Looper looper2 = Looper.myLooper();
            if (looper2 == null) {
                looper = Looper.getMainLooper();
            } else {
                looper = looper2;
            }
            int rate2 = format.getSampleRate();
            if (rate2 == 0) {
                rate = 0;
            } else {
                rate = rate2;
            }
            if ((format.getPropertySetMask() & 8) != 0) {
                channelIndexMask = format.getChannelIndexMask();
            } else {
                channelIndexMask = 0;
            }
            if ((4 & format.getPropertySetMask()) != 0) {
                int channelMask2 = format.getChannelMask();
                channelMask = channelMask2;
            } else if (channelIndexMask == 0) {
                channelMask = 12;
            } else {
                channelMask = 0;
            }
            if ((format.getPropertySetMask() & 1) != 0) {
                int encoding2 = format.getEncoding();
                encoding = encoding2;
            } else {
                encoding = 1;
            }
            audioParamCheck(rate, channelMask, channelIndexMask, encoding, mode);
            this.mOffloaded = offload;
            this.mStreamType = -1;
            audioBuffSizeCheck(bufferSizeInBytes);
            this.mInitializationLooper = looper;
            if (sessionId >= 0) {
                int[] sampleRate = {this.mSampleRate};
                int[] session = {sessionId};
                int initResult = native_setup(new WeakReference(this), this.mAttributes, sampleRate, this.mChannelMask, this.mChannelIndexMask, this.mAudioFormat, this.mNativeBufferSizeInBytes, this.mDataLoadMode, session, 0L, offload, encapsulationMode, tunerConfiguration, getCurrentOpPackageName());
                if (initResult != 0) {
                    loge("Error code " + initResult + " when initializing AudioTrack.");
                    return;
                }
                this.mSampleRate = sampleRate[0];
                this.mSessionId = session[0];
                if ((this.mAttributes.getFlags() & 16) != 0) {
                    if (AudioFormat.isEncodingLinearFrames(this.mAudioFormat)) {
                        frameSizeInBytes = this.mChannelCount * AudioFormat.getBytesPerSample(this.mAudioFormat);
                    } else {
                        frameSizeInBytes = 1;
                    }
                    this.mOffset = ((int) Math.ceil(HEADER_V2_SIZE_BYTES / frameSizeInBytes)) * frameSizeInBytes;
                }
                if (this.mDataLoadMode == 0) {
                    this.mState = 2;
                } else {
                    this.mState = 1;
                }
                baseRegisterPlayer(this.mSessionId);
                native_setPlayerIId(this.mPlayerIId);
                return;
            }
            throw new IllegalArgumentException("Invalid audio session ID: " + sessionId);
        }
        throw new IllegalArgumentException("Illegal null AudioFormat");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AudioTrack(long nativeTrackInJavaObj) {
        super(new AudioAttributes.Builder().build(), 1);
        this.mState = 0;
        this.mPlayState = 1;
        this.mOffloadEosPending = false;
        this.mPlayStateLock = new Object();
        this.mNativeBufferSizeInBytes = 0;
        this.mNativeBufferSizeInFrames = 0;
        this.mChannelCount = 1;
        this.mChannelMask = 4;
        this.mStreamType = 3;
        this.mDataLoadMode = 1;
        this.mChannelConfiguration = 4;
        this.mChannelIndexMask = 0;
        this.mSessionId = 0;
        this.mAvSyncHeader = null;
        this.mAvSyncBytesRemaining = 0;
        this.mOffset = 0;
        this.mOffloaded = false;
        this.mOffloadDelayFrames = 0;
        this.mOffloadPaddingFrames = 0;
        this.mLogSessionId = LogSessionId.LOG_SESSION_ID_NONE;
        this.mPreferredDevice = null;
        this.mRoutingChangeListeners = new ArrayMap<>();
        this.mCodecFormatChangedListeners = new Utils.ListenerList<>();
        this.mStreamEventCbLock = new Object();
        this.mStreamEventCbInfoList = new LinkedList<>();
        this.mNativeTrackInJavaObj = 0L;
        this.mJniData = 0L;
        Looper myLooper = Looper.myLooper();
        Looper looper = myLooper;
        this.mInitializationLooper = myLooper == null ? Looper.getMainLooper() : looper;
        if (nativeTrackInJavaObj != 0) {
            baseRegisterPlayer(0);
            deferred_connect(nativeTrackInJavaObj);
            return;
        }
        this.mState = 0;
    }

    void deferred_connect(long nativeTrackInJavaObj) {
        if (this.mState != 1) {
            int[] session = {0};
            int[] rates = {0};
            int initResult = native_setup(new WeakReference(this), null, rates, 0, 0, 0, 0, 0, session, nativeTrackInJavaObj, false, 0, null, "");
            if (initResult != 0) {
                loge("Error code " + initResult + " when initializing AudioTrack.");
                return;
            }
            this.mSessionId = session[0];
            this.mState = 1;
        }
    }

    @SystemApi
    /* loaded from: classes2.dex */
    public static class TunerConfiguration {
        public static final int CONTENT_ID_NONE = 0;
        private final int mContentId;
        private final int mSyncId;

        public TunerConfiguration(int contentId, int syncId) {
            if (contentId < 0) {
                throw new IllegalArgumentException("contentId " + contentId + " must be positive or CONTENT_ID_NONE");
            } else if (syncId >= 1) {
                this.mContentId = contentId;
                this.mSyncId = syncId;
            } else {
                throw new IllegalArgumentException("syncId " + syncId + " must be positive");
            }
        }

        public int getContentId() {
            return this.mContentId;
        }

        public int getSyncId() {
            return this.mSyncId;
        }
    }

    /* loaded from: classes2.dex */
    public static class Builder {
        private AudioAttributes mAttributes;
        private int mBufferSizeInBytes;
        private AudioFormat mFormat;
        private TunerConfiguration mTunerConfiguration;
        private int mEncapsulationMode = 0;
        private int mSessionId = 0;
        private int mMode = 1;
        private int mPerformanceMode = 0;
        private boolean mOffload = false;

        public Builder setAudioAttributes(AudioAttributes attributes) throws IllegalArgumentException {
            if (attributes != null) {
                this.mAttributes = attributes;
                return this;
            }
            throw new IllegalArgumentException("Illegal null AudioAttributes argument");
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

        public Builder setEncapsulationMode(int encapsulationMode) {
            switch (encapsulationMode) {
                case 0:
                case 1:
                case 2:
                    this.mEncapsulationMode = encapsulationMode;
                    return this;
                default:
                    throw new IllegalArgumentException("Invalid encapsulation mode " + encapsulationMode);
            }
        }

        public Builder setTransferMode(int mode) throws IllegalArgumentException {
            switch (mode) {
                case 0:
                case 1:
                    this.mMode = mode;
                    return this;
                default:
                    throw new IllegalArgumentException("Invalid transfer mode " + mode);
            }
        }

        public Builder setSessionId(int sessionId) throws IllegalArgumentException {
            if (sessionId == 0 || sessionId >= 1) {
                this.mSessionId = sessionId;
                return this;
            }
            throw new IllegalArgumentException("Invalid audio session ID " + sessionId);
        }

        public Builder setPerformanceMode(int performanceMode) {
            switch (performanceMode) {
                case 0:
                case 1:
                case 2:
                    this.mPerformanceMode = performanceMode;
                    return this;
                default:
                    throw new IllegalArgumentException("Invalid performance mode " + performanceMode);
            }
        }

        public Builder setOffloadedPlayback(boolean offload) {
            this.mOffload = offload;
            return this;
        }

        @SystemApi
        public Builder setTunerConfiguration(TunerConfiguration tunerConfiguration) {
            if (tunerConfiguration != null) {
                this.mTunerConfiguration = tunerConfiguration;
                return this;
            }
            throw new IllegalArgumentException("tunerConfiguration is null");
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code restructure failed: missing block: B:9:0x0042, code lost:
            if (android.media.AudioTrack.shouldEnablePowerSaving(r12.mAttributes, r12.mFormat, r12.mBufferSizeInBytes, r12.mMode) == false) goto L_0x0060;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public android.media.AudioTrack build() throws java.lang.UnsupportedOperationException {
            /*
                Method dump skipped, instructions count: 258
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: android.media.AudioTrack.Builder.build():android.media.AudioTrack");
        }
    }

    public void setOffloadDelayPadding(int delayInFrames, int paddingInFrames) {
        if (paddingInFrames < 0) {
            throw new IllegalArgumentException("Illegal negative padding");
        } else if (delayInFrames < 0) {
            throw new IllegalArgumentException("Illegal negative delay");
        } else if (!this.mOffloaded) {
            throw new IllegalStateException("Illegal use of delay/padding on non-offloaded track");
        } else if (this.mState != 0) {
            this.mOffloadDelayFrames = delayInFrames;
            this.mOffloadPaddingFrames = paddingInFrames;
            native_set_delay_padding(delayInFrames, paddingInFrames);
        } else {
            throw new IllegalStateException("Uninitialized track");
        }
    }

    public int getOffloadDelay() {
        if (!this.mOffloaded) {
            throw new IllegalStateException("Illegal query of delay on non-offloaded track");
        } else if (this.mState != 0) {
            return this.mOffloadDelayFrames;
        } else {
            throw new IllegalStateException("Illegal query of delay on uninitialized track");
        }
    }

    public int getOffloadPadding() {
        if (!this.mOffloaded) {
            throw new IllegalStateException("Illegal query of padding on non-offloaded track");
        } else if (this.mState != 0) {
            return this.mOffloadPaddingFrames;
        } else {
            throw new IllegalStateException("Illegal query of padding on uninitialized track");
        }
    }

    public void setOffloadEndOfStream() {
        if (!this.mOffloaded) {
            throw new IllegalStateException("EOS not supported on non-offloaded track");
        } else if (this.mState == 0) {
            throw new IllegalStateException("Uninitialized track");
        } else if (this.mPlayState == 3) {
            synchronized (this.mStreamEventCbLock) {
                if (this.mStreamEventCbInfoList.size() == 0) {
                    throw new IllegalStateException("EOS not supported without StreamEventCallback");
                }
            }
            synchronized (this.mPlayStateLock) {
                native_stop();
                this.mOffloadEosPending = true;
                this.mPlayState = 4;
            }
        } else {
            throw new IllegalStateException("EOS not supported if not playing");
        }
    }

    public boolean isOffloadedPlayback() {
        return this.mOffloaded;
    }

    public static boolean isDirectPlaybackSupported(AudioFormat format, AudioAttributes attributes) {
        if (format == null) {
            throw new IllegalArgumentException("Illegal null AudioFormat argument");
        } else if (attributes != null) {
            return native_is_direct_output_supported(format.getEncoding(), format.getSampleRate(), format.getChannelMask(), format.getChannelIndexMask(), attributes.getContentType(), attributes.getUsage(), attributes.getFlags());
        } else {
            throw new IllegalArgumentException("Illegal null AudioAttributes argument");
        }
    }

    private static boolean isValidAudioDescriptionMixLevel(float level) {
        return !Float.isNaN(level) && level <= MAX_AUDIO_DESCRIPTION_MIX_LEVEL;
    }

    public boolean setAudioDescriptionMixLeveldB(float level) {
        if (isValidAudioDescriptionMixLevel(level)) {
            return native_set_audio_description_mix_level_db(level) == 0;
        }
        throw new IllegalArgumentException("level is out of range" + level);
    }

    public float getAudioDescriptionMixLeveldB() {
        float[] level = {Float.NEGATIVE_INFINITY};
        try {
            int status = native_get_audio_description_mix_level_db(level);
            if (status == 0) {
                if (!Float.isNaN(level[0])) {
                    return level[0];
                }
            }
            return Float.NEGATIVE_INFINITY;
        } catch (Exception e) {
            return Float.NEGATIVE_INFINITY;
        }
    }

    private static boolean isValidDualMonoMode(int dualMonoMode) {
        switch (dualMonoMode) {
            case 0:
            case 1:
            case 2:
            case 3:
                return true;
            default:
                return false;
        }
    }

    public boolean setDualMonoMode(int dualMonoMode) {
        if (isValidDualMonoMode(dualMonoMode)) {
            return native_set_dual_mono_mode(dualMonoMode) == 0;
        }
        throw new IllegalArgumentException("Invalid Dual Mono mode " + dualMonoMode);
    }

    public int getDualMonoMode() {
        int[] dualMonoMode = {0};
        try {
            int status = native_get_dual_mono_mode(dualMonoMode);
            if (status == 0) {
                if (isValidDualMonoMode(dualMonoMode[0])) {
                    return dualMonoMode[0];
                }
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean shouldEnablePowerSaving(AudioAttributes attributes, AudioFormat format, int bufferSizeInBytes, int mode) {
        int flags = attributes.getAllFlags() & 792;
        if ((attributes != null && (flags != 0 || attributes.getUsage() != 1 || (attributes.getContentType() != 0 && attributes.getContentType() != 2 && attributes.getContentType() != 3))) || format == null || format.getSampleRate() == 0 || !AudioFormat.isEncodingLinearPcm(format.getEncoding()) || !AudioFormat.isValidEncoding(format.getEncoding()) || format.getChannelCount() < 1 || mode != 1) {
            return false;
        }
        if (bufferSizeInBytes != 0) {
            long bufferTargetSize = (((format.getChannelCount() * 100) * AudioFormat.getBytesPerSample(format.getEncoding())) * format.getSampleRate()) / 1000;
            if (bufferSizeInBytes < bufferTargetSize) {
                return false;
            }
        }
        return true;
    }

    private void audioParamCheck(int sampleRateInHz, int channelConfig, int channelIndexMask, int audioFormat, int mode) {
        if ((sampleRateInHz < AudioFormat.SAMPLE_RATE_HZ_MIN || sampleRateInHz > AudioFormat.SAMPLE_RATE_HZ_MAX) && sampleRateInHz != 0) {
            throw new IllegalArgumentException(sampleRateInHz + "Hz is not a supported sample rate.");
        }
        this.mSampleRate = sampleRateInHz;
        if (!(audioFormat != 13 || channelConfig == 12 || AudioFormat.channelCountFromOutChannelMask(channelConfig) == 8)) {
            Log.w(TAG, "ENCODING_IEC61937 is configured with channel mask as " + channelConfig + ", which is not 2 or 8 channels");
        }
        this.mChannelConfiguration = channelConfig;
        boolean accepted = false;
        switch (channelConfig) {
            case 1:
            case 2:
            case 4:
                this.mChannelCount = 1;
                this.mChannelMask = 4;
                break;
            case 3:
            case 12:
                this.mChannelCount = 2;
                this.mChannelMask = 12;
                break;
            default:
                if (channelConfig == 0 && channelIndexMask != 0) {
                    this.mChannelCount = 0;
                    break;
                } else if (isMultichannelConfigSupported(channelConfig, audioFormat)) {
                    this.mChannelMask = channelConfig;
                    this.mChannelCount = AudioFormat.channelCountFromOutChannelMask(channelConfig);
                    break;
                } else {
                    throw new IllegalArgumentException("Unsupported channel mask configuration " + channelConfig + " for encoding " + audioFormat);
                }
        }
        this.mChannelIndexMask = channelIndexMask;
        if (channelIndexMask != 0) {
            int channelIndexCount = Integer.bitCount(channelIndexMask);
            if (((-16777216) & channelIndexMask) == 0 && (!AudioFormat.isEncodingLinearFrames(audioFormat) || channelIndexCount <= AudioSystem.OUT_CHANNEL_COUNT_MAX)) {
                accepted = true;
            }
            if (accepted) {
                int i = this.mChannelCount;
                if (i == 0) {
                    this.mChannelCount = channelIndexCount;
                } else if (i != channelIndexCount) {
                    throw new IllegalArgumentException("Channel count must match");
                }
            } else {
                throw new IllegalArgumentException("Unsupported channel index mask configuration " + channelIndexMask + " for encoding " + audioFormat);
            }
        }
        if (audioFormat == 1) {
            audioFormat = 2;
        }
        if (AudioFormat.isPublicEncoding(audioFormat)) {
            this.mAudioFormat = audioFormat;
            if ((mode == 1 || mode == 0) && (mode == 1 || AudioFormat.isEncodingLinearPcm(audioFormat))) {
                this.mDataLoadMode = mode;
                return;
            }
            throw new IllegalArgumentException("Invalid mode.");
        }
        throw new IllegalArgumentException("Unsupported audio encoding.");
    }

    private static boolean isMultichannelConfigSupported(int channelConfig, int encoding) {
        int channelCountLimit;
        if ((67108860 & channelConfig) != channelConfig) {
            loge("Channel configuration features unsupported channels");
            return false;
        }
        int channelCount = AudioFormat.channelCountFromOutChannelMask(channelConfig);
        if (AudioFormat.isEncodingLinearFrames(encoding)) {
            channelCountLimit = AudioSystem.OUT_CHANNEL_COUNT_MAX;
        } else {
            channelCountLimit = 24;
        }
        if (channelCount > channelCountLimit) {
            loge("Channel configuration contains too many channels for encoding " + encoding + "(" + channelCount + " > " + channelCountLimit + ")");
            return false;
        } else if ((channelConfig & 12) != 12) {
            loge("Front channels must be present in multichannel configurations");
            return false;
        } else {
            for (Map.Entry<String, Integer> e : CHANNEL_PAIR_MAP.entrySet()) {
                int positionPair = e.getValue().intValue();
                if (!((channelConfig & positionPair) == 0 || (channelConfig & positionPair) == positionPair)) {
                    loge("Channel pair (" + e.getKey() + ") cannot be used independently");
                    return false;
                }
            }
            return true;
        }
    }

    private void audioBuffSizeCheck(int audioBufferSize) {
        int frameSizeInBytes;
        if (AudioFormat.isEncodingLinearFrames(this.mAudioFormat)) {
            frameSizeInBytes = this.mChannelCount * AudioFormat.getBytesPerSample(this.mAudioFormat);
        } else {
            frameSizeInBytes = 1;
        }
        if (audioBufferSize % frameSizeInBytes != 0 || audioBufferSize < 1) {
            throw new IllegalArgumentException("Invalid audio buffer size.");
        }
        this.mNativeBufferSizeInBytes = audioBufferSize;
        this.mNativeBufferSizeInFrames = audioBufferSize / frameSizeInBytes;
    }

    public void release() {
        synchronized (this.mStreamEventCbLock) {
            endStreamEventHandling();
        }
        try {
            stop();
        } catch (IllegalStateException e) {
        }
        baseRelease();
        native_release();
        synchronized (this.mPlayStateLock) {
            this.mState = 0;
            this.mPlayState = 1;
            this.mPlayStateLock.notify();
        }
    }

    protected void finalize() {
        tryToDisableNativeRoutingCallback();
        baseRelease();
        native_finalize();
    }

    public static float getMinVolume() {
        return 0.0f;
    }

    public static float getMaxVolume() {
        return 1.0f;
    }

    public int getSampleRate() {
        return this.mSampleRate;
    }

    public int getPlaybackRate() {
        return native_get_playback_rate();
    }

    public PlaybackParams getPlaybackParams() {
        return native_get_playback_params();
    }

    public AudioAttributes getAudioAttributes() {
        AudioAttributes audioAttributes;
        if (this.mState != 0 && (audioAttributes = this.mConfiguredAudioAttributes) != null) {
            return audioAttributes;
        }
        throw new IllegalStateException("track not initialized");
    }

    public int getAudioFormat() {
        return this.mAudioFormat;
    }

    public int getStreamType() {
        return this.mStreamType;
    }

    public int getChannelConfiguration() {
        return this.mChannelConfiguration;
    }

    public AudioFormat getFormat() {
        AudioFormat.Builder builder = new AudioFormat.Builder().setSampleRate(this.mSampleRate).setEncoding(this.mAudioFormat);
        int i = this.mChannelConfiguration;
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

    public int getPlayState() {
        synchronized (this.mPlayStateLock) {
            int i = this.mPlayState;
            switch (i) {
                case 4:
                    return 3;
                case 5:
                    return 2;
                default:
                    return i;
            }
        }
    }

    public int getBufferSizeInFrames() {
        return native_get_buffer_size_frames();
    }

    public int setBufferSizeInFrames(int bufferSizeInFrames) {
        if (this.mDataLoadMode == 0 || this.mState == 0) {
            return -3;
        }
        if (bufferSizeInFrames < 0) {
            return -2;
        }
        return native_set_buffer_size_frames(bufferSizeInFrames);
    }

    public int getBufferCapacityInFrames() {
        return native_get_buffer_capacity_frames();
    }

    public int setStartThresholdInFrames(int startThresholdInFrames) {
        if (this.mState != 1) {
            throw new IllegalStateException("AudioTrack is not initialized");
        } else if (this.mDataLoadMode != 1) {
            throw new IllegalStateException("AudioTrack must be a streaming track");
        } else if (startThresholdInFrames >= 1) {
            return native_setStartThresholdInFrames(startThresholdInFrames);
        } else {
            throw new IllegalArgumentException("startThresholdInFrames " + startThresholdInFrames + " must be positive");
        }
    }

    public int getStartThresholdInFrames() {
        if (this.mState != 1) {
            throw new IllegalStateException("AudioTrack is not initialized");
        } else if (this.mDataLoadMode == 1) {
            return native_getStartThresholdInFrames();
        } else {
            throw new IllegalStateException("AudioTrack must be a streaming track");
        }
    }

    @Deprecated
    protected int getNativeFrameCount() {
        return native_get_buffer_capacity_frames();
    }

    public int getNotificationMarkerPosition() {
        return native_get_marker_pos();
    }

    public int getPositionNotificationPeriod() {
        return native_get_pos_update_period();
    }

    public int getPlaybackHeadPosition() {
        return native_get_position();
    }

    public int getLatency() {
        return native_get_latency();
    }

    public int getUnderrunCount() {
        return native_get_underrun_count();
    }

    public int getPerformanceMode() {
        int flags = native_get_flags();
        if ((flags & 4) != 0) {
            return 1;
        }
        if ((flags & 8) != 0) {
            return 2;
        }
        return 0;
    }

    public static int getNativeOutputSampleRate(int streamType) {
        return native_get_output_sample_rate(streamType);
    }

    public static int getMinBufferSize(int sampleRateInHz, int channelConfig, int audioFormat) {
        int channelCount;
        switch (channelConfig) {
            case 2:
            case 4:
                channelCount = 1;
                break;
            case 3:
            case 12:
                channelCount = 2;
                break;
            default:
                if (isMultichannelConfigSupported(channelConfig, audioFormat)) {
                    channelCount = AudioFormat.channelCountFromOutChannelMask(channelConfig);
                    break;
                } else {
                    loge("getMinBufferSize(): Invalid channel configuration.");
                    return -2;
                }
        }
        if (!AudioFormat.isPublicEncoding(audioFormat)) {
            loge("getMinBufferSize(): Invalid audio format.");
            return -2;
        } else if (sampleRateInHz < AudioFormat.SAMPLE_RATE_HZ_MIN || sampleRateInHz > AudioFormat.SAMPLE_RATE_HZ_MAX) {
            loge("getMinBufferSize(): " + sampleRateInHz + " Hz is not a supported sample rate.");
            return -2;
        } else {
            int size = native_get_min_buff_size(sampleRateInHz, channelCount, audioFormat);
            if (size > 0) {
                return size;
            }
            loge("getMinBufferSize(): error querying hardware");
            return -1;
        }
    }

    public int getAudioSessionId() {
        return this.mSessionId;
    }

    public boolean getTimestamp(AudioTimestamp timestamp) {
        if (timestamp != null) {
            long[] longArray = new long[2];
            int ret = native_get_timestamp(longArray);
            if (ret != 0) {
                return false;
            }
            timestamp.framePosition = longArray[0];
            timestamp.nanoTime = longArray[1];
            return true;
        }
        throw new IllegalArgumentException();
    }

    public int getTimestampWithStatus(AudioTimestamp timestamp) {
        if (timestamp != null) {
            long[] longArray = new long[2];
            int ret = native_get_timestamp(longArray);
            timestamp.framePosition = longArray[0];
            timestamp.nanoTime = longArray[1];
            return ret;
        }
        throw new IllegalArgumentException();
    }

    public PersistableBundle getMetrics() {
        PersistableBundle bundle = native_getMetrics();
        return bundle;
    }

    public void setPlaybackPositionUpdateListener(OnPlaybackPositionUpdateListener listener) {
        setPlaybackPositionUpdateListener(listener, null);
    }

    public void setPlaybackPositionUpdateListener(OnPlaybackPositionUpdateListener listener, Handler handler) {
        if (listener != null) {
            this.mEventHandlerDelegate = new NativePositionEventHandlerDelegate(this, listener, handler);
        } else {
            this.mEventHandlerDelegate = null;
        }
    }

    private static float clampGainOrLevel(float gainOrLevel) {
        if (Float.isNaN(gainOrLevel)) {
            throw new IllegalArgumentException();
        } else if (gainOrLevel < 0.0f) {
            return 0.0f;
        } else {
            if (gainOrLevel > 1.0f) {
                return 1.0f;
            }
            return gainOrLevel;
        }
    }

    @Deprecated
    public int setStereoVolume(float leftGain, float rightGain) {
        if (this.mState == 0) {
            return -3;
        }
        baseSetVolume(leftGain, rightGain);
        return 0;
    }

    @Override // android.media.PlayerBase
    void playerSetVolume(boolean muting, float leftVolume, float rightVolume) {
        float f = 0.0f;
        float leftVolume2 = clampGainOrLevel(muting ? 0.0f : leftVolume);
        if (!muting) {
            f = rightVolume;
        }
        float rightVolume2 = clampGainOrLevel(f);
        native_setVolume(leftVolume2, rightVolume2);
    }

    public int setVolume(float gain) {
        return setStereoVolume(gain, gain);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // android.media.PlayerBase
    public int playerApplyVolumeShaper(VolumeShaper.Configuration configuration, VolumeShaper.Operation operation) {
        return native_applyVolumeShaper(configuration, operation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // android.media.PlayerBase
    public VolumeShaper.State playerGetVolumeShaperState(int id) {
        return native_getVolumeShaperState(id);
    }

    @Override // android.media.VolumeAutomation
    public VolumeShaper createVolumeShaper(VolumeShaper.Configuration configuration) {
        return new VolumeShaper(configuration, this);
    }

    public int setPlaybackRate(int sampleRateInHz) {
        if (this.mState != 1) {
            return -3;
        }
        if (sampleRateInHz <= 0) {
            return -2;
        }
        return native_set_playback_rate(sampleRateInHz);
    }

    public void setPlaybackParams(PlaybackParams params) {
        if (params != null) {
            native_set_playback_params(params);
            return;
        }
        throw new IllegalArgumentException("params is null");
    }

    public int setNotificationMarkerPosition(int markerInFrames) {
        if (this.mState == 0) {
            return -3;
        }
        return native_set_marker_pos(markerInFrames);
    }

    public int setPositionNotificationPeriod(int periodInFrames) {
        if (this.mState == 0) {
            return -3;
        }
        return native_set_pos_update_period(periodInFrames);
    }

    public int setPlaybackHeadPosition(int positionInFrames) {
        if (this.mDataLoadMode == 1 || this.mState == 0 || getPlayState() == 3) {
            return -3;
        }
        if (positionInFrames < 0 || positionInFrames > this.mNativeBufferSizeInFrames) {
            return -2;
        }
        return native_set_position(positionInFrames);
    }

    public int setLoopPoints(int startInFrames, int endInFrames, int loopCount) {
        int i;
        if (this.mDataLoadMode == 1 || this.mState == 0 || getPlayState() == 3) {
            return -3;
        }
        if (loopCount != 0 && (startInFrames < 0 || startInFrames >= (i = this.mNativeBufferSizeInFrames) || startInFrames >= endInFrames || endInFrames > i)) {
            return -2;
        }
        return native_set_loop(startInFrames, endInFrames, loopCount);
    }

    public int setPresentation(AudioPresentation presentation) {
        if (presentation != null) {
            return native_setPresentation(presentation.getPresentationId(), presentation.getProgramId());
        }
        throw new IllegalArgumentException("audio presentation is null");
    }

    @Deprecated
    protected void setState(int state) {
        this.mState = state;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [android.media.AudioTrack$2] */
    public void play() throws IllegalStateException {
        if (this.mState == 1) {
            final int delay = getStartDelayMs();
            if (delay == 0) {
                startImpl();
            } else {
                new Thread() { // from class: android.media.AudioTrack.2
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        try {
                            Thread.sleep(delay);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        AudioTrack.this.baseSetStartDelayMs(0);
                        try {
                            AudioTrack.this.startImpl();
                        } catch (IllegalStateException e2) {
                        }
                    }
                }.start();
            }
        } else {
            throw new IllegalStateException("play() called on uninitialized AudioTrack.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startImpl() {
        synchronized (this.mRoutingChangeListeners) {
            if (!this.mEnableSelfRoutingMonitor) {
                this.mEnableSelfRoutingMonitor = testEnableNativeRoutingCallbacksLocked();
            }
        }
        synchronized (this.mPlayStateLock) {
            baseStart(0);
            native_start();
            if (this.mPlayState == 5) {
                this.mPlayState = 4;
            } else {
                this.mPlayState = 3;
                this.mOffloadEosPending = false;
            }
        }
    }

    public void stop() throws IllegalStateException {
        if (this.mState == 1) {
            synchronized (this.mPlayStateLock) {
                native_stop();
                baseStop();
                if (!this.mOffloaded || this.mPlayState == 5) {
                    this.mPlayState = 1;
                    this.mOffloadEosPending = false;
                    this.mAvSyncHeader = null;
                    this.mAvSyncBytesRemaining = 0;
                    this.mPlayStateLock.notify();
                } else {
                    this.mPlayState = 4;
                }
            }
            tryToDisableNativeRoutingCallback();
            return;
        }
        throw new IllegalStateException("stop() called on uninitialized AudioTrack.");
    }

    public void pause() throws IllegalStateException {
        if (this.mState == 1) {
            synchronized (this.mPlayStateLock) {
                native_pause();
                basePause();
                if (this.mPlayState == 4) {
                    this.mPlayState = 5;
                } else {
                    this.mPlayState = 2;
                }
            }
            return;
        }
        throw new IllegalStateException("pause() called on uninitialized AudioTrack.");
    }

    public void flush() {
        if (this.mState == 1) {
            native_flush();
            this.mAvSyncHeader = null;
            this.mAvSyncBytesRemaining = 0;
        }
    }

    public int write(byte[] audioData, int offsetInBytes, int sizeInBytes) {
        return write(audioData, offsetInBytes, sizeInBytes, 0);
    }

    public int write(byte[] audioData, int offsetInBytes, int sizeInBytes, int writeMode) {
        if (this.mState == 0 || this.mAudioFormat == 4) {
            return -3;
        }
        if (writeMode != 0 && writeMode != 1) {
            Log.e(TAG, "AudioTrack.write() called with invalid blocking mode");
            return -2;
        } else if (audioData == null || offsetInBytes < 0 || sizeInBytes < 0 || offsetInBytes + sizeInBytes < 0 || offsetInBytes + sizeInBytes > audioData.length) {
            return -2;
        } else {
            if (!blockUntilOffloadDrain(writeMode)) {
                return 0;
            }
            int ret = native_write_byte(audioData, offsetInBytes, sizeInBytes, this.mAudioFormat, writeMode == 0);
            if (this.mDataLoadMode == 0 && this.mState == 2 && ret > 0) {
                this.mState = 1;
            }
            return ret;
        }
    }

    public int write(short[] audioData, int offsetInShorts, int sizeInShorts) {
        return write(audioData, offsetInShorts, sizeInShorts, 0);
    }

    public int write(short[] audioData, int offsetInShorts, int sizeInShorts, int writeMode) {
        int i;
        if (this.mState == 0 || (i = this.mAudioFormat) == 4 || i > 20) {
            return -3;
        }
        if (writeMode != 0 && writeMode != 1) {
            Log.e(TAG, "AudioTrack.write() called with invalid blocking mode");
            return -2;
        } else if (audioData == null || offsetInShorts < 0 || sizeInShorts < 0 || offsetInShorts + sizeInShorts < 0 || offsetInShorts + sizeInShorts > audioData.length) {
            return -2;
        } else {
            if (!blockUntilOffloadDrain(writeMode)) {
                return 0;
            }
            int ret = native_write_short(audioData, offsetInShorts, sizeInShorts, this.mAudioFormat, writeMode == 0);
            if (this.mDataLoadMode == 0 && this.mState == 2 && ret > 0) {
                this.mState = 1;
            }
            return ret;
        }
    }

    public int write(float[] audioData, int offsetInFloats, int sizeInFloats, int writeMode) {
        if (this.mState == 0) {
            Log.e(TAG, "AudioTrack.write() called in invalid state STATE_UNINITIALIZED");
            return -3;
        } else if (this.mAudioFormat != 4) {
            Log.e(TAG, "AudioTrack.write(float[] ...) requires format ENCODING_PCM_FLOAT");
            return -3;
        } else if (writeMode != 0 && writeMode != 1) {
            Log.e(TAG, "AudioTrack.write() called with invalid blocking mode");
            return -2;
        } else if (audioData == null || offsetInFloats < 0 || sizeInFloats < 0 || offsetInFloats + sizeInFloats < 0 || offsetInFloats + sizeInFloats > audioData.length) {
            Log.e(TAG, "AudioTrack.write() called with invalid array, offset, or size");
            return -2;
        } else if (!blockUntilOffloadDrain(writeMode)) {
            return 0;
        } else {
            int ret = native_write_float(audioData, offsetInFloats, sizeInFloats, this.mAudioFormat, writeMode == 0);
            if (this.mDataLoadMode == 0 && this.mState == 2 && ret > 0) {
                this.mState = 1;
            }
            return ret;
        }
    }

    public int write(ByteBuffer audioData, int sizeInBytes, int writeMode) {
        int ret;
        if (this.mState == 0) {
            Log.e(TAG, "AudioTrack.write() called in invalid state STATE_UNINITIALIZED");
            return -3;
        } else if (writeMode != 0 && writeMode != 1) {
            Log.e(TAG, "AudioTrack.write() called with invalid blocking mode");
            return -2;
        } else if (audioData == null || sizeInBytes < 0 || sizeInBytes > audioData.remaining()) {
            Log.e(TAG, "AudioTrack.write() called with invalid size (" + sizeInBytes + ") value");
            return -2;
        } else if (!blockUntilOffloadDrain(writeMode)) {
            return 0;
        } else {
            if (audioData.isDirect()) {
                ret = native_write_native_bytes(audioData, audioData.position(), sizeInBytes, this.mAudioFormat, writeMode == 0);
            } else {
                ret = native_write_byte(NioUtils.unsafeArray(audioData), audioData.position() + NioUtils.unsafeArrayOffset(audioData), sizeInBytes, this.mAudioFormat, writeMode == 0);
            }
            if (this.mDataLoadMode == 0 && this.mState == 2 && ret > 0) {
                this.mState = 1;
            }
            if (ret > 0) {
                audioData.position(audioData.position() + ret);
            }
            return ret;
        }
    }

    public int write(ByteBuffer audioData, int sizeInBytes, int writeMode, long timestamp) {
        if (this.mState == 0) {
            Log.e(TAG, "AudioTrack.write() called in invalid state STATE_UNINITIALIZED");
            return -3;
        } else if (writeMode != 0 && writeMode != 1) {
            Log.e(TAG, "AudioTrack.write() called with invalid blocking mode");
            return -2;
        } else if (this.mDataLoadMode != 1) {
            Log.e(TAG, "AudioTrack.write() with timestamp called for non-streaming mode track");
            return -3;
        } else if ((this.mAttributes.getFlags() & 16) == 0) {
            Log.d(TAG, "AudioTrack.write() called on a regular AudioTrack. Ignoring pts...");
            return write(audioData, sizeInBytes, writeMode);
        } else if (audioData == null || sizeInBytes < 0 || sizeInBytes > audioData.remaining()) {
            Log.e(TAG, "AudioTrack.write() called with invalid size (" + sizeInBytes + ") value");
            return -2;
        } else if (!blockUntilOffloadDrain(writeMode)) {
            return 0;
        } else {
            if (this.mAvSyncHeader == null) {
                ByteBuffer allocate = ByteBuffer.allocate(this.mOffset);
                this.mAvSyncHeader = allocate;
                allocate.order(ByteOrder.BIG_ENDIAN);
                this.mAvSyncHeader.putInt(1431633922);
            }
            if (this.mAvSyncBytesRemaining == 0) {
                this.mAvSyncHeader.putInt(4, sizeInBytes);
                this.mAvSyncHeader.putLong(8, timestamp);
                this.mAvSyncHeader.putInt(16, this.mOffset);
                this.mAvSyncHeader.position(0);
                this.mAvSyncBytesRemaining = sizeInBytes;
            }
            if (this.mAvSyncHeader.remaining() != 0) {
                ByteBuffer byteBuffer = this.mAvSyncHeader;
                int ret = write(byteBuffer, byteBuffer.remaining(), writeMode);
                if (ret < 0) {
                    Log.e(TAG, "AudioTrack.write() could not write timestamp header!");
                    this.mAvSyncHeader = null;
                    this.mAvSyncBytesRemaining = 0;
                    return ret;
                } else if (this.mAvSyncHeader.remaining() > 0) {
                    Log.v(TAG, "AudioTrack.write() partial timestamp header written.");
                    return 0;
                }
            }
            int sizeToWrite = Math.min(this.mAvSyncBytesRemaining, sizeInBytes);
            int ret2 = write(audioData, sizeToWrite, writeMode);
            if (ret2 < 0) {
                Log.e(TAG, "AudioTrack.write() could not write audio data!");
                this.mAvSyncHeader = null;
                this.mAvSyncBytesRemaining = 0;
                return ret2;
            }
            this.mAvSyncBytesRemaining -= ret2;
            return ret2;
        }
    }

    public int reloadStaticData() {
        if (this.mDataLoadMode == 1 || this.mState != 1) {
            return -3;
        }
        return native_reload_static();
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0013, code lost:
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean blockUntilOffloadDrain(int r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mPlayStateLock
            monitor-enter(r0)
        L_0x0003:
            int r1 = r4.mPlayState     // Catch: all -> 0x001c
            r2 = 4
            r3 = 1
            if (r1 == r2) goto L_0x000f
            r2 = 5
            if (r1 != r2) goto L_0x000d
            goto L_0x000f
        L_0x000d:
            monitor-exit(r0)     // Catch: all -> 0x001c
            return r3
        L_0x000f:
            if (r5 != r3) goto L_0x0014
            r1 = 0
            monitor-exit(r0)     // Catch: all -> 0x001c
            return r1
        L_0x0014:
            java.lang.Object r1 = r4.mPlayStateLock     // Catch: InterruptedException -> 0x001a, all -> 0x001c
            r1.wait()     // Catch: InterruptedException -> 0x001a, all -> 0x001c
            goto L_0x001b
        L_0x001a:
            r1 = move-exception
        L_0x001b:
            goto L_0x0003
        L_0x001c:
            r1 = move-exception
            monitor-exit(r0)     // Catch: all -> 0x001c
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.media.AudioTrack.blockUntilOffloadDrain(int):boolean");
    }

    public int attachAuxEffect(int effectId) {
        if (this.mState == 0) {
            return -3;
        }
        return native_attachAuxEffect(effectId);
    }

    public int setAuxEffectSendLevel(float level) {
        if (this.mState == 0) {
            return -3;
        }
        return baseSetAuxEffectSendLevel(level);
    }

    @Override // android.media.PlayerBase
    int playerSetAuxEffectSendLevel(boolean muting, float level) {
        int err = native_setAuxEffectSendLevel(clampGainOrLevel(muting ? 0.0f : level));
        return err == 0 ? 0 : -1;
    }

    @Override // android.media.AudioRouting
    public boolean setPreferredDevice(AudioDeviceInfo deviceInfo) {
        int preferredDeviceId = 0;
        if (deviceInfo != null && !deviceInfo.isSink()) {
            return false;
        }
        if (deviceInfo != null) {
            preferredDeviceId = deviceInfo.getId();
        }
        boolean status = native_setOutputDevice(preferredDeviceId);
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

    @Override // android.media.AudioRouting
    public AudioDeviceInfo getRoutedDevice() {
        int deviceId = native_getRoutedDeviceId();
        if (deviceId == 0) {
            return null;
        }
        return AudioManager.getDeviceForPortId(deviceId, 2);
    }

    private void tryToDisableNativeRoutingCallback() {
        synchronized (this.mRoutingChangeListeners) {
            if (this.mEnableSelfRoutingMonitor) {
                this.mEnableSelfRoutingMonitor = false;
                testDisableNativeRoutingCallbacksLocked();
            }
        }
    }

    private boolean testEnableNativeRoutingCallbacksLocked() {
        if (this.mRoutingChangeListeners.size() != 0 || this.mEnableSelfRoutingMonitor) {
            return false;
        }
        try {
            native_enableDeviceCallback();
            return true;
        } catch (IllegalStateException e) {
            if (!Log.isLoggable(TAG, 3)) {
                return false;
            }
            Log.d(TAG, "testEnableNativeRoutingCallbacks failed", e);
            return false;
        }
    }

    private void testDisableNativeRoutingCallbacksLocked() {
        if (this.mRoutingChangeListeners.size() == 0 && !this.mEnableSelfRoutingMonitor) {
            try {
                native_disableDeviceCallback();
            } catch (IllegalStateException e) {
            }
        }
    }

    @Override // android.media.AudioRouting
    public void addOnRoutingChangedListener(AudioRouting.OnRoutingChangedListener listener, Handler handler) {
        synchronized (this.mRoutingChangeListeners) {
            if (listener != null) {
                if (!this.mRoutingChangeListeners.containsKey(listener)) {
                    this.mEnableSelfRoutingMonitor = testEnableNativeRoutingCallbacksLocked();
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
            }
            testDisableNativeRoutingCallbacksLocked();
        }
    }

    @Deprecated
    /* loaded from: classes2.dex */
    public interface OnRoutingChangedListener extends AudioRouting.OnRoutingChangedListener {
        void onRoutingChanged(AudioTrack audioTrack);

        @Override // android.media.AudioRouting.OnRoutingChangedListener
        default void onRoutingChanged(AudioRouting router) {
            if (router instanceof AudioTrack) {
                onRoutingChanged((AudioTrack) router);
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
        baseUpdateDeviceId(getRoutedDevice());
        synchronized (this.mRoutingChangeListeners) {
            for (NativeRoutingEventHandlerDelegate delegate : this.mRoutingChangeListeners.values()) {
                delegate.notifyClient();
            }
        }
    }

    public void addOnCodecFormatChangedListener(Executor executor, final OnCodecFormatChangedListener listener) {
        this.mCodecFormatChangedListeners.add(listener, executor, new Utils.ListenerList.Listener() { // from class: android.media.AudioTrack$$ExternalSyntheticLambda0
            @Override // android.media.Utils.ListenerList.Listener
            public final void onEvent(int i, Object obj) {
                AudioTrack.this.lambda$addOnCodecFormatChangedListener$0$AudioTrack(listener, i, (AudioMetadataReadMap) obj);
            }
        });
    }

    public /* synthetic */ void lambda$addOnCodecFormatChangedListener$0$AudioTrack(OnCodecFormatChangedListener listener, int eventCode, AudioMetadataReadMap readMap) {
        listener.onCodecFormatChanged(this, readMap);
    }

    public void removeOnCodecFormatChangedListener(OnCodecFormatChangedListener listener) {
        this.mCodecFormatChangedListeners.remove(listener);
    }

    /* loaded from: classes2.dex */
    public static abstract class StreamEventCallback {
        public void onTearDown(AudioTrack track) {
        }

        public void onPresentationEnded(AudioTrack track) {
        }

        public void onDataRequest(AudioTrack track, int sizeInFrames) {
        }
    }

    public void registerStreamEventCallback(Executor executor, StreamEventCallback eventCallback) {
        if (eventCallback == null) {
            throw new IllegalArgumentException("Illegal null StreamEventCallback");
        } else if (!this.mOffloaded) {
            throw new IllegalStateException("Cannot register StreamEventCallback on non-offloaded AudioTrack");
        } else if (executor != null) {
            synchronized (this.mStreamEventCbLock) {
                Iterator<StreamEventCbInfo> it = this.mStreamEventCbInfoList.iterator();
                while (it.hasNext()) {
                    StreamEventCbInfo seci = it.next();
                    if (seci.mStreamEventCb == eventCallback) {
                        throw new IllegalArgumentException("StreamEventCallback already registered");
                    }
                }
                beginStreamEventHandling();
                this.mStreamEventCbInfoList.add(new StreamEventCbInfo(executor, eventCallback));
            }
        } else {
            throw new IllegalArgumentException("Illegal null Executor for the StreamEventCallback");
        }
    }

    public void unregisterStreamEventCallback(StreamEventCallback eventCallback) {
        if (eventCallback == null) {
            throw new IllegalArgumentException("Illegal null StreamEventCallback");
        } else if (this.mOffloaded) {
            synchronized (this.mStreamEventCbLock) {
                Iterator<StreamEventCbInfo> it = this.mStreamEventCbInfoList.iterator();
                while (it.hasNext()) {
                    StreamEventCbInfo seci = it.next();
                    if (seci.mStreamEventCb == eventCallback) {
                        this.mStreamEventCbInfoList.remove(seci);
                        if (this.mStreamEventCbInfoList.size() == 0) {
                            endStreamEventHandling();
                        }
                    }
                }
                throw new IllegalArgumentException("StreamEventCallback was not registered");
            }
        } else {
            throw new IllegalStateException("No StreamEventCallback on non-offloaded AudioTrack");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class StreamEventCbInfo {
        final StreamEventCallback mStreamEventCb;
        final Executor mStreamEventExec;

        StreamEventCbInfo(Executor e, StreamEventCallback cb) {
            this.mStreamEventExec = e;
            this.mStreamEventCb = cb;
        }
    }

    void handleStreamEventFromNative(int what, int arg) {
        if (this.mStreamEventHandler != null) {
            switch (what) {
                case 6:
                    this.mStreamEventHandler.sendMessage(this.mStreamEventHandler.obtainMessage(6));
                    return;
                case 7:
                    this.mStreamEventHandler.sendMessage(this.mStreamEventHandler.obtainMessage(7));
                    return;
                case 8:
                default:
                    return;
                case 9:
                    this.mStreamEventHandler.removeMessages(9);
                    this.mStreamEventHandler.sendMessage(this.mStreamEventHandler.obtainMessage(9, arg, 0));
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class StreamEventHandler extends Handler {
        StreamEventHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(final Message msg) {
            synchronized (AudioTrack.this.mStreamEventCbLock) {
                if (msg.what == 7) {
                    synchronized (AudioTrack.this.mPlayStateLock) {
                        if (AudioTrack.this.mPlayState == 4) {
                            if (AudioTrack.this.mOffloadEosPending) {
                                AudioTrack.this.native_start();
                                AudioTrack.this.mPlayState = 3;
                            } else {
                                AudioTrack.this.mAvSyncHeader = null;
                                AudioTrack.this.mAvSyncBytesRemaining = 0;
                                AudioTrack.this.mPlayState = 1;
                            }
                            AudioTrack.this.mOffloadEosPending = false;
                            AudioTrack.this.mPlayStateLock.notify();
                        }
                    }
                }
                if (AudioTrack.this.mStreamEventCbInfoList.size() != 0) {
                    LinkedList<StreamEventCbInfo> cbInfoList = new LinkedList<>(AudioTrack.this.mStreamEventCbInfoList);
                    long identity = Binder.clearCallingIdentity();
                    try {
                        Iterator<StreamEventCbInfo> it = cbInfoList.iterator();
                        while (it.hasNext()) {
                            final StreamEventCbInfo cbi = it.next();
                            switch (msg.what) {
                                case 6:
                                    cbi.mStreamEventExec.execute(new Runnable() { // from class: android.media.AudioTrack$StreamEventHandler$$ExternalSyntheticLambda0
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            AudioTrack.StreamEventHandler.this.lambda$handleMessage$1$AudioTrack$StreamEventHandler(cbi);
                                        }
                                    });
                                    break;
                                case 7:
                                    cbi.mStreamEventExec.execute(new Runnable() { // from class: android.media.AudioTrack$StreamEventHandler$$ExternalSyntheticLambda1
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            AudioTrack.StreamEventHandler.this.lambda$handleMessage$2$AudioTrack$StreamEventHandler(cbi);
                                        }
                                    });
                                    break;
                                case 9:
                                    cbi.mStreamEventExec.execute(new Runnable() { // from class: android.media.AudioTrack$StreamEventHandler$$ExternalSyntheticLambda2
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            AudioTrack.StreamEventHandler.this.lambda$handleMessage$0$AudioTrack$StreamEventHandler(cbi, msg);
                                        }
                                    });
                                    break;
                            }
                        }
                    } finally {
                        Binder.restoreCallingIdentity(identity);
                    }
                }
            }
        }

        public /* synthetic */ void lambda$handleMessage$0$AudioTrack$StreamEventHandler(StreamEventCbInfo cbi, Message msg) {
            cbi.mStreamEventCb.onDataRequest(AudioTrack.this, msg.arg1);
        }

        public /* synthetic */ void lambda$handleMessage$1$AudioTrack$StreamEventHandler(StreamEventCbInfo cbi) {
            cbi.mStreamEventCb.onTearDown(AudioTrack.this);
        }

        public /* synthetic */ void lambda$handleMessage$2$AudioTrack$StreamEventHandler(StreamEventCbInfo cbi) {
            cbi.mStreamEventCb.onPresentationEnded(AudioTrack.this);
        }
    }

    private void beginStreamEventHandling() {
        if (this.mStreamEventHandlerThread == null) {
            HandlerThread handlerThread = new HandlerThread("android.media.AudioTrack.StreamEvent");
            this.mStreamEventHandlerThread = handlerThread;
            handlerThread.start();
            Looper looper = this.mStreamEventHandlerThread.getLooper();
            if (looper != null) {
                this.mStreamEventHandler = new StreamEventHandler(looper);
            }
        }
    }

    private void endStreamEventHandling() {
        HandlerThread handlerThread = this.mStreamEventHandlerThread;
        if (handlerThread != null) {
            handlerThread.quit();
            this.mStreamEventHandlerThread = null;
        }
    }

    public void setLogSessionId(LogSessionId logSessionId) {
        Objects.requireNonNull(logSessionId);
        if (this.mState != 0) {
            String stringId = logSessionId.getStringId();
            native_setLogSessionId(stringId);
            this.mLogSessionId = logSessionId;
            return;
        }
        throw new IllegalStateException("track not initialized");
    }

    public LogSessionId getLogSessionId() {
        return this.mLogSessionId;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class NativePositionEventHandlerDelegate {
        private final Handler mHandler;

        NativePositionEventHandlerDelegate(final AudioTrack track, final OnPlaybackPositionUpdateListener listener, Handler handler) {
            Looper looper;
            if (handler != null) {
                looper = handler.getLooper();
            } else {
                looper = AudioTrack.this.mInitializationLooper;
            }
            if (looper != null) {
                this.mHandler = new Handler(looper) { // from class: android.media.AudioTrack.NativePositionEventHandlerDelegate.1
                    @Override // android.os.Handler
                    public void handleMessage(Message msg) {
                        if (track != null) {
                            switch (msg.what) {
                                case 3:
                                    OnPlaybackPositionUpdateListener onPlaybackPositionUpdateListener = listener;
                                    if (onPlaybackPositionUpdateListener != null) {
                                        onPlaybackPositionUpdateListener.onMarkerReached(track);
                                        return;
                                    }
                                    return;
                                case 4:
                                    OnPlaybackPositionUpdateListener onPlaybackPositionUpdateListener2 = listener;
                                    if (onPlaybackPositionUpdateListener2 != null) {
                                        onPlaybackPositionUpdateListener2.onPeriodicNotification(track);
                                        return;
                                    }
                                    return;
                                default:
                                    AudioTrack.loge("Unknown native event type: " + msg.what);
                                    return;
                            }
                        }
                    }
                };
            } else {
                this.mHandler = null;
            }
        }

        Handler getHandler() {
            return this.mHandler;
        }
    }

    @Override // android.media.PlayerBase
    void playerStart() {
        play();
    }

    @Override // android.media.PlayerBase
    void playerPause() {
        pause();
    }

    @Override // android.media.PlayerBase
    void playerStop() {
        stop();
    }

    private static void postEventFromNative(Object audiotrack_ref, int what, int arg1, int arg2, Object obj) {
        Handler handler;
        AudioTrack track = (AudioTrack) ((WeakReference) audiotrack_ref).get();
        if (track != null) {
            if (what == 1000) {
                track.broadcastRoutingChange();
            } else if (what == 100) {
                ByteBuffer buffer = (ByteBuffer) obj;
                buffer.order(ByteOrder.nativeOrder());
                buffer.rewind();
                AudioMetadataReadMap audioMetaData = AudioMetadata.fromByteBuffer(buffer);
                if (audioMetaData == null) {
                    Log.e(TAG, "Unable to get audio metadata from byte buffer");
                } else {
                    track.mCodecFormatChangedListeners.notify(0, audioMetaData);
                }
            } else if (what == 9 || what == 6 || what == 7) {
                track.handleStreamEventFromNative(what, arg1);
            } else {
                NativePositionEventHandlerDelegate delegate = track.mEventHandlerDelegate;
                if (delegate != null && (handler = delegate.getHandler()) != null) {
                    Message m = handler.obtainMessage(what, arg1, arg2, obj);
                    handler.sendMessage(m);
                }
            }
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
        public static final String ATTRIBUTES = "android.media.audiotrack.attributes";
        @Deprecated
        public static final String CHANNELMASK = "android.media.audiorecord.channelmask";
        public static final String CHANNEL_MASK = "android.media.audiotrack.channelMask";
        public static final String CONTENTTYPE = "android.media.audiotrack.type";
        public static final String ENCODING = "android.media.audiotrack.encoding";
        public static final String FRAME_COUNT = "android.media.audiotrack.frameCount";
        private static final String MM_PREFIX = "android.media.audiotrack.";
        public static final String PORT_ID = "android.media.audiotrack.portId";
        @Deprecated
        public static final String SAMPLERATE = "android.media.audiorecord.samplerate";
        public static final String SAMPLE_RATE = "android.media.audiotrack.sampleRate";
        public static final String STREAMTYPE = "android.media.audiotrack.streamtype";
        public static final String USAGE = "android.media.audiotrack.usage";

        private MetricsConstants() {
        }
    }
}
