package android.media.audiopolicy;

import android.Manifest;
import android.annotation.SystemApi;
import android.app.ActivityManager;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioDeviceInfo;
import android.media.AudioFocusInfo;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.IAudioService;
import android.media.audiopolicy.IAudioPolicyCallback;
import android.media.projection.MediaProjection;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import android.util.Slog;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
@SystemApi
/* loaded from: classes2.dex */
public class AudioPolicy {
    private static final boolean DEBUG = false;
    public static final int FOCUS_POLICY_DUCKING_DEFAULT = 0;
    public static final int FOCUS_POLICY_DUCKING_IN_APP = 0;
    public static final int FOCUS_POLICY_DUCKING_IN_POLICY = 1;
    private static final int MSG_FOCUS_ABANDON = 5;
    private static final int MSG_FOCUS_GRANT = 1;
    private static final int MSG_FOCUS_LOSS = 2;
    private static final int MSG_FOCUS_REQUEST = 4;
    private static final int MSG_MIX_STATE_UPDATE = 3;
    private static final int MSG_POLICY_STATUS_CHANGE = 0;
    private static final int MSG_VOL_ADJUST = 6;
    public static final int POLICY_STATUS_REGISTERED = 2;
    public static final int POLICY_STATUS_UNREGISTERED = 1;
    private static final String TAG = "AudioPolicy";
    private static IAudioService sService;
    private ArrayList<WeakReference<AudioRecord>> mCaptors;
    private AudioPolicyConfig mConfig;
    private Context mContext;
    private final EventHandler mEventHandler;
    private AudioPolicyFocusListener mFocusListener;
    private ArrayList<WeakReference<AudioTrack>> mInjectors;
    private boolean mIsFocusPolicy;
    private boolean mIsTestFocusPolicy;
    private final Object mLock;
    private final IAudioPolicyCallback mPolicyCb;
    private final MediaProjection mProjection;
    private String mRegistrationId;
    private int mStatus;
    private AudioPolicyStatusListener mStatusListener;
    private final AudioPolicyVolumeCallback mVolCb;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface PolicyStatus {
    }

    public AudioPolicyConfig getConfig() {
        return this.mConfig;
    }

    public boolean hasFocusListener() {
        return this.mFocusListener != null;
    }

    public boolean isFocusPolicy() {
        return this.mIsFocusPolicy;
    }

    public boolean isTestFocusPolicy() {
        return this.mIsTestFocusPolicy;
    }

    public boolean isVolumeController() {
        return this.mVolCb != null;
    }

    public MediaProjection getMediaProjection() {
        return this.mProjection;
    }

    private AudioPolicy(AudioPolicyConfig config, Context context, Looper looper, AudioPolicyFocusListener fl, AudioPolicyStatusListener sl, boolean isFocusPolicy, boolean isTestFocusPolicy, AudioPolicyVolumeCallback vc, MediaProjection projection) {
        this.mLock = new Object();
        this.mPolicyCb = new IAudioPolicyCallback.Stub() { // from class: android.media.audiopolicy.AudioPolicy.1
            @Override // android.media.audiopolicy.IAudioPolicyCallback
            public void notifyAudioFocusGrant(AudioFocusInfo afi, int requestResult) {
                AudioPolicy.this.sendMsg(1, afi, requestResult);
            }

            @Override // android.media.audiopolicy.IAudioPolicyCallback
            public void notifyAudioFocusLoss(AudioFocusInfo afi, boolean wasNotified) {
                AudioPolicy.this.sendMsg(2, afi, wasNotified ? 1 : 0);
            }

            @Override // android.media.audiopolicy.IAudioPolicyCallback
            public void notifyAudioFocusRequest(AudioFocusInfo afi, int requestResult) {
                AudioPolicy.this.sendMsg(4, afi, requestResult);
            }

            @Override // android.media.audiopolicy.IAudioPolicyCallback
            public void notifyAudioFocusAbandon(AudioFocusInfo afi) {
                AudioPolicy.this.sendMsg(5, afi, 0);
            }

            @Override // android.media.audiopolicy.IAudioPolicyCallback
            public void notifyMixStateUpdate(String regId, int state) {
                Iterator<AudioMix> it = AudioPolicy.this.mConfig.getMixes().iterator();
                while (it.hasNext()) {
                    AudioMix mix = it.next();
                    if (mix.getRegistration().equals(regId)) {
                        mix.mMixState = state;
                        AudioPolicy.this.sendMsg(3, mix, 0);
                    }
                }
            }

            @Override // android.media.audiopolicy.IAudioPolicyCallback
            public void notifyVolumeAdjust(int adjustment) {
                AudioPolicy.this.sendMsg(6, null, adjustment);
            }

            @Override // android.media.audiopolicy.IAudioPolicyCallback
            public void notifyUnregistration() {
                AudioPolicy.this.setRegistration(null);
            }
        };
        this.mConfig = config;
        this.mStatus = 1;
        this.mContext = context;
        looper = looper == null ? Looper.getMainLooper() : looper;
        if (looper != null) {
            this.mEventHandler = new EventHandler(this, looper);
        } else {
            this.mEventHandler = null;
            Log.e(TAG, "No event handler due to looper without a thread");
        }
        this.mFocusListener = fl;
        this.mStatusListener = sl;
        this.mIsFocusPolicy = isFocusPolicy;
        this.mIsTestFocusPolicy = isTestFocusPolicy;
        this.mVolCb = vc;
        this.mProjection = projection;
    }

    /* loaded from: classes2.dex */
    public static class Builder {
        private Context mContext;
        private AudioPolicyFocusListener mFocusListener;
        private Looper mLooper;
        private MediaProjection mProjection;
        private AudioPolicyStatusListener mStatusListener;
        private AudioPolicyVolumeCallback mVolCb;
        private boolean mIsFocusPolicy = false;
        private boolean mIsTestFocusPolicy = false;
        private ArrayList<AudioMix> mMixes = new ArrayList<>();

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder addMix(AudioMix mix) throws IllegalArgumentException {
            if (mix != null) {
                this.mMixes.add(mix);
                return this;
            }
            throw new IllegalArgumentException("Illegal null AudioMix argument");
        }

        public Builder setLooper(Looper looper) throws IllegalArgumentException {
            if (looper != null) {
                this.mLooper = looper;
                return this;
            }
            throw new IllegalArgumentException("Illegal null Looper argument");
        }

        public void setAudioPolicyFocusListener(AudioPolicyFocusListener l) {
            this.mFocusListener = l;
        }

        public Builder setIsAudioFocusPolicy(boolean isFocusPolicy) {
            this.mIsFocusPolicy = isFocusPolicy;
            return this;
        }

        public Builder setIsTestFocusPolicy(boolean isTestFocusPolicy) {
            this.mIsTestFocusPolicy = isTestFocusPolicy;
            return this;
        }

        public void setAudioPolicyStatusListener(AudioPolicyStatusListener l) {
            this.mStatusListener = l;
        }

        public Builder setAudioPolicyVolumeCallback(AudioPolicyVolumeCallback vc) {
            if (vc != null) {
                this.mVolCb = vc;
                return this;
            }
            throw new IllegalArgumentException("Invalid null volume callback");
        }

        public Builder setMediaProjection(MediaProjection projection) {
            if (projection != null) {
                this.mProjection = projection;
                return this;
            }
            throw new IllegalArgumentException("Invalid null volume callback");
        }

        public AudioPolicy build() {
            if (this.mStatusListener != null) {
                Iterator<AudioMix> it = this.mMixes.iterator();
                while (it.hasNext()) {
                    AudioMix mix = it.next();
                    mix.mCallbackFlags |= 1;
                }
            }
            if (!this.mIsFocusPolicy || this.mFocusListener != null) {
                return new AudioPolicy(new AudioPolicyConfig(this.mMixes), this.mContext, this.mLooper, this.mFocusListener, this.mStatusListener, this.mIsFocusPolicy, this.mIsTestFocusPolicy, this.mVolCb, this.mProjection);
            }
            throw new IllegalStateException("Cannot be a focus policy without an AudioPolicyFocusListener");
        }
    }

    public int attachMixes(List<AudioMix> mixes) {
        int status;
        if (mixes != null) {
            synchronized (this.mLock) {
                if (this.mStatus == 2) {
                    ArrayList<AudioMix> zeMixes = new ArrayList<>(mixes.size());
                    for (AudioMix mix : mixes) {
                        if (mix != null) {
                            zeMixes.add(mix);
                        } else {
                            throw new IllegalArgumentException("Illegal null AudioMix in attachMixes");
                        }
                    }
                    AudioPolicyConfig cfg = new AudioPolicyConfig(zeMixes);
                    IAudioService service = getService();
                    try {
                        status = service.addMixForPolicy(cfg, cb());
                        if (status == 0) {
                            this.mConfig.add(zeMixes);
                        }
                    } catch (RemoteException e) {
                        Log.e(TAG, "Dead object in attachMixes", e);
                        return -1;
                    }
                } else {
                    throw new IllegalStateException("Cannot alter unregistered AudioPolicy");
                }
            }
            return status;
        }
        throw new IllegalArgumentException("Illegal null list of AudioMix");
    }

    public int detachMixes(List<AudioMix> mixes) {
        int status;
        if (mixes != null) {
            synchronized (this.mLock) {
                if (this.mStatus == 2) {
                    ArrayList<AudioMix> zeMixes = new ArrayList<>(mixes.size());
                    for (AudioMix mix : mixes) {
                        if (mix != null) {
                            zeMixes.add(mix);
                        } else {
                            throw new IllegalArgumentException("Illegal null AudioMix in detachMixes");
                        }
                    }
                    AudioPolicyConfig cfg = new AudioPolicyConfig(zeMixes);
                    IAudioService service = getService();
                    try {
                        status = service.removeMixForPolicy(cfg, cb());
                        if (status == 0) {
                            this.mConfig.remove(zeMixes);
                        }
                    } catch (RemoteException e) {
                        Log.e(TAG, "Dead object in detachMixes", e);
                        return -1;
                    }
                } else {
                    throw new IllegalStateException("Cannot alter unregistered AudioPolicy");
                }
            }
            return status;
        }
        throw new IllegalArgumentException("Illegal null list of AudioMix");
    }

    @SystemApi
    public boolean setUidDeviceAffinity(int uid, List<AudioDeviceInfo> devices) {
        boolean z;
        if (devices != null) {
            synchronized (this.mLock) {
                if (this.mStatus == 2) {
                    int[] deviceTypes = new int[devices.size()];
                    String[] deviceAdresses = new String[devices.size()];
                    int i = 0;
                    for (AudioDeviceInfo device : devices) {
                        if (device != null) {
                            deviceTypes[i] = AudioDeviceInfo.convertDeviceTypeToInternalDevice(device.getType());
                            deviceAdresses[i] = device.getAddress();
                            i++;
                        } else {
                            throw new IllegalArgumentException("Illegal null AudioDeviceInfo in setUidDeviceAffinity");
                        }
                    }
                    IAudioService service = getService();
                    z = false;
                    try {
                        int status = service.setUidDeviceAffinity(cb(), uid, deviceTypes, deviceAdresses);
                        if (status == 0) {
                            z = true;
                        }
                    } catch (RemoteException e) {
                        Log.e(TAG, "Dead object in setUidDeviceAffinity", e);
                        return false;
                    }
                } else {
                    throw new IllegalStateException("Cannot use unregistered AudioPolicy");
                }
            }
            return z;
        }
        throw new IllegalArgumentException("Illegal null list of audio devices");
    }

    @SystemApi
    public boolean removeUidDeviceAffinity(int uid) {
        boolean z;
        synchronized (this.mLock) {
            if (this.mStatus == 2) {
                IAudioService service = getService();
                z = false;
                try {
                    int status = service.removeUidDeviceAffinity(cb(), uid);
                    if (status == 0) {
                        z = true;
                    }
                } catch (RemoteException e) {
                    Log.e(TAG, "Dead object in removeUidDeviceAffinity", e);
                    return false;
                }
            } else {
                throw new IllegalStateException("Cannot use unregistered AudioPolicy");
            }
        }
        return z;
    }

    @SystemApi
    public boolean removeUserIdDeviceAffinity(int userId) {
        boolean z;
        synchronized (this.mLock) {
            if (this.mStatus == 2) {
                IAudioService service = getService();
                z = false;
                try {
                    int status = service.removeUserIdDeviceAffinity(cb(), userId);
                    if (status == 0) {
                        z = true;
                    }
                } catch (RemoteException e) {
                    Log.e(TAG, "Dead object in removeUserIdDeviceAffinity", e);
                    return false;
                }
            } else {
                throw new IllegalStateException("Cannot use unregistered AudioPolicy");
            }
        }
        return z;
    }

    @SystemApi
    public boolean setUserIdDeviceAffinity(int userId, List<AudioDeviceInfo> devices) {
        boolean z;
        Objects.requireNonNull(devices, "Illegal null list of audio devices");
        synchronized (this.mLock) {
            if (this.mStatus == 2) {
                int[] deviceTypes = new int[devices.size()];
                String[] deviceAddresses = new String[devices.size()];
                int i = 0;
                for (AudioDeviceInfo device : devices) {
                    if (device != null) {
                        deviceTypes[i] = AudioDeviceInfo.convertDeviceTypeToInternalDevice(device.getType());
                        deviceAddresses[i] = device.getAddress();
                        i++;
                    } else {
                        throw new IllegalArgumentException("Illegal null AudioDeviceInfo in setUserIdDeviceAffinity");
                    }
                }
                IAudioService service = getService();
                z = false;
                try {
                    int status = service.setUserIdDeviceAffinity(cb(), userId, deviceTypes, deviceAddresses);
                    if (status == 0) {
                        z = true;
                    }
                } catch (RemoteException e) {
                    Log.e(TAG, "Dead object in setUserIdDeviceAffinity", e);
                    return false;
                }
            } else {
                throw new IllegalStateException("Cannot use unregistered AudioPolicy");
            }
        }
        return z;
    }

    public void reset() {
        setRegistration(null);
        this.mConfig.reset();
    }

    public void setRegistration(String regId) {
        synchronized (this.mLock) {
            this.mRegistrationId = regId;
            this.mConfig.setRegistration(regId);
            if (regId != null) {
                this.mStatus = 2;
            } else {
                this.mStatus = 1;
            }
        }
        sendMsg(0);
    }

    public String getRegistration() {
        return this.mRegistrationId;
    }

    private boolean policyReadyToUse() {
        boolean canProjectAudio;
        synchronized (this.mLock) {
            if (this.mStatus != 2) {
                Log.e(TAG, "Cannot use unregistered AudioPolicy");
                return false;
            } else if (this.mRegistrationId == null) {
                Log.e(TAG, "Cannot use unregistered AudioPolicy");
                return false;
            } else {
                boolean canModifyAudioRouting = checkCallingOrSelfPermission(Manifest.permission.MODIFY_AUDIO_ROUTING) == 0;
                try {
                    MediaProjection mediaProjection = this.mProjection;
                    if (mediaProjection != null) {
                        if (mediaProjection.getProjection().canProjectAudio()) {
                            canProjectAudio = true;
                            if ((!isLoopbackRenderPolicy() && canProjectAudio) || canModifyAudioRouting) {
                                return true;
                            }
                            Slog.w(TAG, "Cannot use AudioPolicy for pid " + Binder.getCallingPid() + " / uid " + Binder.getCallingUid() + ", needs MODIFY_AUDIO_ROUTING or MediaProjection that can project audio.");
                            return false;
                        }
                    }
                    canProjectAudio = false;
                    if (!isLoopbackRenderPolicy()) {
                    }
                    Slog.w(TAG, "Cannot use AudioPolicy for pid " + Binder.getCallingPid() + " / uid " + Binder.getCallingUid() + ", needs MODIFY_AUDIO_ROUTING or MediaProjection that can project audio.");
                    return false;
                } catch (RemoteException e) {
                    Log.e(TAG, "Failed to check if MediaProjection#canProjectAudio");
                    throw e.rethrowFromSystemServer();
                }
            }
        }
    }

    private boolean isLoopbackRenderPolicy() {
        boolean allMatch;
        synchronized (this.mLock) {
            allMatch = this.mConfig.mMixes.stream().allMatch(AudioPolicy$$ExternalSyntheticLambda0.INSTANCE);
        }
        return allMatch;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$isLoopbackRenderPolicy$0(AudioMix mix) {
        return mix.getRouteFlags() == 3;
    }

    private int checkCallingOrSelfPermission(String permission) {
        Context context = this.mContext;
        if (context != null) {
            return context.checkCallingOrSelfPermission(permission);
        }
        Slog.v(TAG, "Null context, checking permission via ActivityManager");
        int pid = Binder.getCallingPid();
        int uid = Binder.getCallingUid();
        try {
            return ActivityManager.getService().checkPermission(permission, pid, uid);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private void checkMixReadyToUse(AudioMix mix, boolean forTrack) throws IllegalArgumentException {
        if (mix == null) {
            String msg = forTrack ? "Invalid null AudioMix for AudioTrack creation" : "Invalid null AudioMix for AudioRecord creation";
            throw new IllegalArgumentException(msg);
        } else if (!this.mConfig.mMixes.contains(mix)) {
            throw new IllegalArgumentException("Invalid mix: not part of this policy");
        } else if ((mix.getRouteFlags() & 2) != 2) {
            throw new IllegalArgumentException("Invalid AudioMix: not defined for loop back");
        } else if (forTrack && mix.getMixType() != 1) {
            throw new IllegalArgumentException("Invalid AudioMix: not defined for being a recording source");
        } else if (!forTrack && mix.getMixType() != 0) {
            throw new IllegalArgumentException("Invalid AudioMix: not defined for capturing playback");
        }
    }

    public int getFocusDuckingBehavior() {
        return this.mConfig.mDuckingPolicy;
    }

    public int setFocusDuckingBehavior(int behavior) throws IllegalArgumentException, IllegalStateException {
        int status;
        if (behavior == 0 || behavior == 1) {
            synchronized (this.mLock) {
                if (this.mStatus == 2) {
                    if (behavior == 1 && this.mFocusListener == null) {
                        throw new IllegalStateException("Cannot handle ducking without an audio focus listener");
                    }
                    IAudioService service = getService();
                    try {
                        status = service.setFocusPropertiesForPolicy(behavior, cb());
                        if (status == 0) {
                            this.mConfig.mDuckingPolicy = behavior;
                        }
                    } catch (RemoteException e) {
                        Log.e(TAG, "Dead object in setFocusPropertiesForPolicy for behavior", e);
                        return -1;
                    }
                } else {
                    throw new IllegalStateException("Cannot change ducking behavior for unregistered policy");
                }
            }
            return status;
        }
        throw new IllegalArgumentException("Invalid ducking behavior " + behavior);
    }

    public AudioRecord createAudioRecordSink(AudioMix mix) throws IllegalArgumentException {
        if (!policyReadyToUse()) {
            Log.e(TAG, "Cannot create AudioRecord sink for AudioMix");
            return null;
        }
        checkMixReadyToUse(mix, false);
        AudioFormat mixFormat = new AudioFormat.Builder(mix.getFormat()).setChannelMask(AudioFormat.inChannelMaskFromOutChannelMask(mix.getFormat().getChannelMask())).build();
        AudioRecord ar = new AudioRecord(new AudioAttributes.Builder().setInternalCapturePreset(8).addTag(addressForTag(mix)).addTag(AudioRecord.SUBMIX_FIXED_VOLUME).build(), mixFormat, AudioRecord.getMinBufferSize(mix.getFormat().getSampleRate(), 12, mix.getFormat().getEncoding()), 0);
        synchronized (this.mLock) {
            if (this.mCaptors == null) {
                this.mCaptors = new ArrayList<>(1);
            }
            this.mCaptors.add(new WeakReference<>(ar));
        }
        return ar;
    }

    public AudioTrack createAudioTrackSource(AudioMix mix) throws IllegalArgumentException {
        if (!policyReadyToUse()) {
            Log.e(TAG, "Cannot create AudioTrack source for AudioMix");
            return null;
        }
        checkMixReadyToUse(mix, true);
        AudioTrack at = new AudioTrack(new AudioAttributes.Builder().setUsage(15).addTag(addressForTag(mix)).build(), mix.getFormat(), AudioTrack.getMinBufferSize(mix.getFormat().getSampleRate(), mix.getFormat().getChannelMask(), mix.getFormat().getEncoding()), 1, 0);
        synchronized (this.mLock) {
            if (this.mInjectors == null) {
                this.mInjectors = new ArrayList<>(1);
            }
            this.mInjectors.add(new WeakReference<>(at));
        }
        return at;
    }

    public void invalidateCaptorsAndInjectors() {
        if (policyReadyToUse()) {
            synchronized (this.mLock) {
                ArrayList<WeakReference<AudioTrack>> arrayList = this.mInjectors;
                if (arrayList != null) {
                    Iterator<WeakReference<AudioTrack>> it = arrayList.iterator();
                    while (it.hasNext()) {
                        WeakReference<AudioTrack> weakTrack = it.next();
                        AudioTrack track = weakTrack.get();
                        if (track == null) {
                            break;
                        }
                        try {
                            track.stop();
                            track.flush();
                        } catch (IllegalStateException e) {
                        }
                    }
                }
                ArrayList<WeakReference<AudioRecord>> arrayList2 = this.mCaptors;
                if (arrayList2 != null) {
                    Iterator<WeakReference<AudioRecord>> it2 = arrayList2.iterator();
                    while (it2.hasNext()) {
                        WeakReference<AudioRecord> weakRecord = it2.next();
                        AudioRecord record = weakRecord.get();
                        if (record == null) {
                            break;
                        }
                        try {
                            record.stop();
                        } catch (IllegalStateException e2) {
                        }
                    }
                }
            }
        }
    }

    public int getStatus() {
        return this.mStatus;
    }

    /* loaded from: classes2.dex */
    public static abstract class AudioPolicyStatusListener {
        public void onStatusChange() {
        }

        public void onMixStateUpdate(AudioMix mix) {
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class AudioPolicyFocusListener {
        public void onAudioFocusGrant(AudioFocusInfo afi, int requestResult) {
        }

        public void onAudioFocusLoss(AudioFocusInfo afi, boolean wasNotified) {
        }

        public void onAudioFocusRequest(AudioFocusInfo afi, int requestResult) {
        }

        public void onAudioFocusAbandon(AudioFocusInfo afi) {
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class AudioPolicyVolumeCallback {
        public void onVolumeAdjustment(int adjustment) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPolicyStatusChange() {
        synchronized (this.mLock) {
            AudioPolicyStatusListener l = this.mStatusListener;
            if (l != null) {
                l.onStatusChange();
            }
        }
    }

    public IAudioPolicyCallback cb() {
        return this.mPolicyCb;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class EventHandler extends Handler {
        public EventHandler(AudioPolicy ap, Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    AudioPolicy.this.onPolicyStatusChange();
                    return;
                case 1:
                    if (AudioPolicy.this.mFocusListener != null) {
                        AudioPolicy.this.mFocusListener.onAudioFocusGrant((AudioFocusInfo) msg.obj, msg.arg1);
                        return;
                    }
                    return;
                case 2:
                    if (AudioPolicy.this.mFocusListener != null) {
                        AudioPolicy.this.mFocusListener.onAudioFocusLoss((AudioFocusInfo) msg.obj, msg.arg1 != 0);
                        return;
                    }
                    return;
                case 3:
                    if (AudioPolicy.this.mStatusListener != null) {
                        AudioPolicy.this.mStatusListener.onMixStateUpdate((AudioMix) msg.obj);
                        return;
                    }
                    return;
                case 4:
                    if (AudioPolicy.this.mFocusListener != null) {
                        AudioPolicy.this.mFocusListener.onAudioFocusRequest((AudioFocusInfo) msg.obj, msg.arg1);
                        return;
                    } else {
                        Log.e(AudioPolicy.TAG, "Invalid null focus listener for focus request event");
                        return;
                    }
                case 5:
                    if (AudioPolicy.this.mFocusListener != null) {
                        AudioPolicy.this.mFocusListener.onAudioFocusAbandon((AudioFocusInfo) msg.obj);
                        return;
                    } else {
                        Log.e(AudioPolicy.TAG, "Invalid null focus listener for focus abandon event");
                        return;
                    }
                case 6:
                    if (AudioPolicy.this.mVolCb != null) {
                        AudioPolicy.this.mVolCb.onVolumeAdjustment(msg.arg1);
                        return;
                    } else {
                        Log.e(AudioPolicy.TAG, "Invalid null volume event");
                        return;
                    }
                default:
                    Log.e(AudioPolicy.TAG, "Unknown event " + msg.what);
                    return;
            }
        }
    }

    private static String addressForTag(AudioMix mix) {
        return "addr=" + mix.getRegistration();
    }

    private void sendMsg(int msg) {
        EventHandler eventHandler = this.mEventHandler;
        if (eventHandler != null) {
            eventHandler.sendEmptyMessage(msg);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMsg(int msg, Object obj, int i) {
        EventHandler eventHandler = this.mEventHandler;
        if (eventHandler != null) {
            eventHandler.sendMessage(eventHandler.obtainMessage(msg, i, 0, obj));
        }
    }

    private static IAudioService getService() {
        IAudioService iAudioService = sService;
        if (iAudioService != null) {
            return iAudioService;
        }
        IBinder b = ServiceManager.getService("audio");
        IAudioService asInterface = IAudioService.Stub.asInterface(b);
        sService = asInterface;
        return asInterface;
    }

    public String toLogFriendlyString() {
        String textDump = new String("android.media.audiopolicy.AudioPolicy:\n");
        return textDump + "config=" + this.mConfig.toLogFriendlyString();
    }
}
