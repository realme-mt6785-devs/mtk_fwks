package android.media;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.VolumeShaper;
import android.media.audiofx.HapticGenerator;
import android.net.Uri;
import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;
import com.android.internal.R;
import java.io.IOException;
import java.util.ArrayList;
/* loaded from: classes2.dex */
public class Ringtone {
    private static final boolean LOGD = true;
    private static final String MEDIA_SELECTION = "mime_type LIKE 'audio/%' OR mime_type IN ('application/ogg', 'application/x-flac')";
    private static final String TAG = "Ringtone";
    private final boolean mAllowRemote;
    private final AudioManager mAudioManager;
    private final Context mContext;
    private HapticGenerator mHapticGenerator;
    private MediaPlayer mLocalPlayer;
    private final IRingtonePlayer mRemotePlayer;
    private final Binder mRemoteToken;
    private String mTitle;
    private Uri mUri;
    private VolumeShaper mVolumeShaper;
    private VolumeShaper.Configuration mVolumeShaperConfig;
    private static final String[] MEDIA_COLUMNS = {"_id", "title"};
    private static final ArrayList<Ringtone> sActiveRingtones = new ArrayList<>();
    private final MyOnCompletionListener mCompletionListener = new MyOnCompletionListener();
    private AudioAttributes mAudioAttributes = new AudioAttributes.Builder().setUsage(6).setContentType(4).build();
    private boolean mIsLooping = false;
    private float mVolume = 1.0f;
    private boolean mHapticGeneratorEnabled = false;
    private final Object mPlaybackSettingsLock = new Object();

    public Ringtone(Context context, boolean allowRemote) {
        this.mContext = context;
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        this.mAudioManager = audioManager;
        this.mAllowRemote = allowRemote;
        Binder binder = null;
        this.mRemotePlayer = allowRemote ? audioManager.getRingtonePlayer() : null;
        this.mRemoteToken = allowRemote ? new Binder() : binder;
    }

    @Deprecated
    public void setStreamType(int streamType) {
        PlayerBase.deprecateStreamTypeForPlayback(streamType, TAG, "setStreamType()");
        setAudioAttributes(new AudioAttributes.Builder().setInternalLegacyStreamType(streamType).build());
    }

    @Deprecated
    public int getStreamType() {
        return AudioAttributes.toLegacyStreamType(this.mAudioAttributes);
    }

    public void setAudioAttributes(AudioAttributes attributes) throws IllegalArgumentException {
        if (attributes != null) {
            this.mAudioAttributes = attributes;
            setUri(this.mUri, this.mVolumeShaperConfig);
            return;
        }
        throw new IllegalArgumentException("Invalid null AudioAttributes for Ringtone");
    }

    public AudioAttributes getAudioAttributes() {
        return this.mAudioAttributes;
    }

    public void setLooping(boolean looping) {
        synchronized (this.mPlaybackSettingsLock) {
            this.mIsLooping = looping;
            applyPlaybackProperties_sync();
        }
    }

    public boolean isLooping() {
        boolean z;
        synchronized (this.mPlaybackSettingsLock) {
            z = this.mIsLooping;
        }
        return z;
    }

    public void setVolume(float volume) {
        synchronized (this.mPlaybackSettingsLock) {
            if (volume < 0.0f) {
                volume = 0.0f;
            }
            if (volume > 1.0f) {
                volume = 1.0f;
            }
            this.mVolume = volume;
            applyPlaybackProperties_sync();
        }
    }

    public float getVolume() {
        float f;
        synchronized (this.mPlaybackSettingsLock) {
            f = this.mVolume;
        }
        return f;
    }

    public boolean setHapticGeneratorEnabled(boolean enabled) {
        if (!HapticGenerator.isAvailable()) {
            return false;
        }
        synchronized (this.mPlaybackSettingsLock) {
            this.mHapticGeneratorEnabled = enabled;
            applyPlaybackProperties_sync();
        }
        return true;
    }

    public boolean isHapticGeneratorEnabled() {
        boolean z;
        synchronized (this.mPlaybackSettingsLock) {
            z = this.mHapticGeneratorEnabled;
        }
        return z;
    }

    private void applyPlaybackProperties_sync() {
        IRingtonePlayer iRingtonePlayer;
        MediaPlayer mediaPlayer = this.mLocalPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(this.mVolume);
            this.mLocalPlayer.setLooping(this.mIsLooping);
            if (this.mHapticGenerator == null && this.mHapticGeneratorEnabled) {
                this.mHapticGenerator = HapticGenerator.create(this.mLocalPlayer.getAudioSessionId());
            }
            HapticGenerator hapticGenerator = this.mHapticGenerator;
            if (hapticGenerator != null) {
                hapticGenerator.setEnabled(this.mHapticGeneratorEnabled);
            }
        } else if (!this.mAllowRemote || (iRingtonePlayer = this.mRemotePlayer) == null) {
            Log.w(TAG, "Neither local nor remote player available when applying playback properties");
        } else {
            try {
                iRingtonePlayer.setPlaybackProperties(this.mRemoteToken, this.mVolume, this.mIsLooping, this.mHapticGeneratorEnabled);
            } catch (RemoteException e) {
                Log.w(TAG, "Problem setting playback properties: ", e);
            }
        }
    }

    public String getTitle(Context context) {
        String str = this.mTitle;
        if (str != null) {
            return str;
        }
        String title = getTitle(context, this.mUri, true, this.mAllowRemote);
        this.mTitle = title;
        return title;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0069, code lost:
        if (r10 != null) goto L_0x006b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x006b, code lost:
        r10.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0093, code lost:
        if (r10 == null) goto L_0x006e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0096, code lost:
        if (r7 != null) goto L_0x00a4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0098, code lost:
        r7 = r12.getLastPathSegment();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getTitle(android.content.Context r11, android.net.Uri r12, boolean r13, boolean r14) {
        /*
            android.content.ContentResolver r6 = r11.getContentResolver()
            r7 = 0
            if (r12 == 0) goto L_0x009d
            java.lang.String r0 = r12.getAuthority()
            java.lang.String r8 = android.content.ContentProvider.getAuthorityWithoutUserId(r0)
            java.lang.String r0 = "settings"
            boolean r0 = r0.equals(r8)
            r9 = 1
            if (r0 == 0) goto L_0x0036
            if (r13 == 0) goto L_0x009c
            int r0 = android.media.RingtoneManager.getDefaultType(r12)
            android.net.Uri r0 = android.media.RingtoneManager.getActualDefaultRingtoneUri(r11, r0)
            r1 = 0
            java.lang.String r2 = getTitle(r11, r0, r1, r14)
            r3 = 17041338(0x10407ba, float:2.4250115E-38)
            java.lang.Object[] r4 = new java.lang.Object[r9]
            r4[r1] = r2
            java.lang.String r7 = r11.getString(r3, r4)
            goto L_0x009c
        L_0x0036:
            r10 = 0
            java.lang.String r0 = "media"
            boolean r0 = r0.equals(r8)     // Catch: all -> 0x0070, SecurityException -> 0x0072
            if (r0 == 0) goto L_0x0069
            if (r14 == 0) goto L_0x0044
            r0 = 0
            goto L_0x0047
        L_0x0044:
            java.lang.String r0 = "mime_type LIKE 'audio/%' OR mime_type IN ('application/ogg', 'application/x-flac')"
        L_0x0047:
            r3 = r0
            java.lang.String[] r2 = android.media.Ringtone.MEDIA_COLUMNS     // Catch: all -> 0x0070, SecurityException -> 0x0072
            r4 = 0
            r5 = 0
            r0 = r6
            r1 = r12
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5)     // Catch: all -> 0x0070, SecurityException -> 0x0072
            r10 = r0
            if (r10 == 0) goto L_0x0069
            int r0 = r10.getCount()     // Catch: all -> 0x0070, SecurityException -> 0x0072
            if (r0 != r9) goto L_0x0069
            r10.moveToFirst()     // Catch: all -> 0x0070, SecurityException -> 0x0072
            java.lang.String r0 = r10.getString(r9)     // Catch: all -> 0x0070, SecurityException -> 0x0072
            if (r10 == 0) goto L_0x0067
            r10.close()
        L_0x0067:
            r1 = 0
            return r0
        L_0x0069:
            if (r10 == 0) goto L_0x006e
        L_0x006b:
            r10.close()
        L_0x006e:
            r0 = 0
            goto L_0x0096
        L_0x0070:
            r0 = move-exception
            goto L_0x008b
        L_0x0072:
            r0 = move-exception
            r1 = 0
            if (r14 == 0) goto L_0x0083
            java.lang.String r2 = "audio"
            java.lang.Object r2 = r11.getSystemService(r2)     // Catch: all -> 0x0070
            android.media.AudioManager r2 = (android.media.AudioManager) r2     // Catch: all -> 0x0070
            android.media.IRingtonePlayer r3 = r2.getRingtonePlayer()     // Catch: all -> 0x0070
            r1 = r3
        L_0x0083:
            if (r1 == 0) goto L_0x0093
            java.lang.String r2 = r1.getTitle(r12)     // Catch: all -> 0x0070, RemoteException -> 0x0092
            r7 = r2
            goto L_0x0093
        L_0x008b:
            if (r10 == 0) goto L_0x0090
            r10.close()
        L_0x0090:
            r1 = 0
            throw r0
        L_0x0092:
            r2 = move-exception
        L_0x0093:
            if (r10 == 0) goto L_0x006e
            goto L_0x006b
        L_0x0096:
            if (r7 != 0) goto L_0x009c
            java.lang.String r7 = r12.getLastPathSegment()
        L_0x009c:
            goto L_0x00a4
        L_0x009d:
            r0 = 17041342(0x10407be, float:2.4250126E-38)
            java.lang.String r7 = r11.getString(r0)
        L_0x00a4:
            if (r7 != 0) goto L_0x00b1
            r0 = 17041343(0x10407bf, float:2.4250129E-38)
            java.lang.String r7 = r11.getString(r0)
            if (r7 != 0) goto L_0x00b1
            java.lang.String r7 = ""
        L_0x00b1:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: android.media.Ringtone.getTitle(android.content.Context, android.net.Uri, boolean, boolean):java.lang.String");
    }

    public void setUri(Uri uri) {
        setUri(uri, null);
    }

    public void setUri(Uri uri, VolumeShaper.Configuration volumeShaperConfig) {
        this.mVolumeShaperConfig = volumeShaperConfig;
        destroyLocalPlayer();
        this.mUri = uri;
        if (uri != null) {
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.mLocalPlayer = mediaPlayer;
            try {
                mediaPlayer.setDataSource(this.mContext, this.mUri);
                this.mLocalPlayer.setAudioAttributes(this.mAudioAttributes);
                synchronized (this.mPlaybackSettingsLock) {
                    applyPlaybackProperties_sync();
                }
                VolumeShaper.Configuration configuration = this.mVolumeShaperConfig;
                if (configuration != null) {
                    this.mVolumeShaper = this.mLocalPlayer.createVolumeShaper(configuration);
                }
                this.mLocalPlayer.prepare();
            } catch (IOException | SecurityException e) {
                destroyLocalPlayer();
                if (!this.mAllowRemote) {
                    Log.w(TAG, "Remote playback not allowed: " + e);
                }
            }
            if (this.mLocalPlayer != null) {
                Log.d(TAG, "Successfully created local player");
            } else {
                Log.d(TAG, "Problem opening; delegating to remote player");
            }
        }
    }

    public Uri getUri() {
        return this.mUri;
    }

    public void play() {
        Uri uri;
        boolean looping;
        float volume;
        if (this.mLocalPlayer != null) {
            boolean isHapticOnly = AudioManager.hasHapticChannels(this.mContext, this.mUri) && !this.mAudioAttributes.areHapticChannelsMuted() && this.mVolume == 0.0f;
            if (isHapticOnly || this.mAudioManager.getStreamVolume(AudioAttributes.toLegacyStreamType(this.mAudioAttributes)) != 0) {
                startLocalPlayer();
            }
        } else if (this.mAllowRemote && this.mRemotePlayer != null && (uri = this.mUri) != null) {
            Uri canonicalUri = uri.getCanonicalUri();
            synchronized (this.mPlaybackSettingsLock) {
                looping = this.mIsLooping;
                volume = this.mVolume;
            }
            try {
                this.mRemotePlayer.playWithVolumeShaping(this.mRemoteToken, canonicalUri, this.mAudioAttributes, volume, looping, this.mVolumeShaperConfig);
            } catch (RemoteException e) {
                if (!playFallbackRingtone()) {
                    Log.w(TAG, "Problem playing ringtone: " + e);
                }
            }
        } else if (!playFallbackRingtone()) {
            Log.w(TAG, "Neither local nor remote playback available");
        }
    }

    public void stop() {
        IRingtonePlayer iRingtonePlayer;
        if (this.mLocalPlayer != null) {
            destroyLocalPlayer();
        } else if (this.mAllowRemote && (iRingtonePlayer = this.mRemotePlayer) != null) {
            try {
                iRingtonePlayer.stop(this.mRemoteToken);
            } catch (RemoteException e) {
                Log.w(TAG, "Problem stopping ringtone: " + e);
            }
        }
    }

    private void destroyLocalPlayer() {
        if (this.mLocalPlayer != null) {
            HapticGenerator hapticGenerator = this.mHapticGenerator;
            if (hapticGenerator != null) {
                hapticGenerator.release();
                this.mHapticGenerator = null;
            }
            this.mLocalPlayer.setOnCompletionListener(null);
            this.mLocalPlayer.reset();
            this.mLocalPlayer.release();
            this.mLocalPlayer = null;
            this.mVolumeShaper = null;
            ArrayList<Ringtone> arrayList = sActiveRingtones;
            synchronized (arrayList) {
                arrayList.remove(this);
            }
        }
    }

    private void startLocalPlayer() {
        if (this.mLocalPlayer != null) {
            ArrayList<Ringtone> arrayList = sActiveRingtones;
            synchronized (arrayList) {
                arrayList.add(this);
            }
            this.mLocalPlayer.setOnCompletionListener(this.mCompletionListener);
            this.mLocalPlayer.start();
            VolumeShaper volumeShaper = this.mVolumeShaper;
            if (volumeShaper != null) {
                volumeShaper.apply(VolumeShaper.Operation.PLAY);
            }
        }
    }

    public boolean isPlaying() {
        IRingtonePlayer iRingtonePlayer;
        MediaPlayer mediaPlayer = this.mLocalPlayer;
        if (mediaPlayer != null) {
            return mediaPlayer.isPlaying();
        }
        if (!this.mAllowRemote || (iRingtonePlayer = this.mRemotePlayer) == null) {
            Log.w(TAG, "Neither local nor remote playback available");
            return false;
        }
        try {
            return iRingtonePlayer.isPlaying(this.mRemoteToken);
        } catch (RemoteException e) {
            Log.w(TAG, "Problem checking ringtone: " + e);
            return false;
        }
    }

    private boolean playFallbackRingtone() {
        if (this.mAudioManager.getStreamVolume(AudioAttributes.toLegacyStreamType(this.mAudioAttributes)) == 0) {
            return false;
        }
        int ringtoneType = RingtoneManager.getDefaultType(this.mUri);
        if (ringtoneType == -1 || RingtoneManager.getActualDefaultRingtoneUri(this.mContext, ringtoneType) != null) {
            try {
                AssetFileDescriptor afd = this.mContext.getResources().openRawResourceFd(R.raw.fallbackring);
                if (afd != null) {
                    this.mLocalPlayer = new MediaPlayer();
                    if (afd.getDeclaredLength() < 0) {
                        this.mLocalPlayer.setDataSource(afd.getFileDescriptor());
                    } else {
                        this.mLocalPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getDeclaredLength());
                    }
                    this.mLocalPlayer.setAudioAttributes(this.mAudioAttributes);
                    synchronized (this.mPlaybackSettingsLock) {
                        applyPlaybackProperties_sync();
                    }
                    VolumeShaper.Configuration configuration = this.mVolumeShaperConfig;
                    if (configuration != null) {
                        this.mVolumeShaper = this.mLocalPlayer.createVolumeShaper(configuration);
                    }
                    this.mLocalPlayer.prepare();
                    startLocalPlayer();
                    afd.close();
                    return true;
                }
                Log.e(TAG, "Could not load fallback ringtone");
                return false;
            } catch (Resources.NotFoundException e) {
                Log.e(TAG, "Fallback ringtone does not exist");
                return false;
            } catch (IOException e2) {
                destroyLocalPlayer();
                Log.e(TAG, "Failed to open fallback ringtone");
                return false;
            }
        } else {
            Log.w(TAG, "not playing fallback for " + this.mUri);
            return false;
        }
    }

    void setTitle(String title) {
        this.mTitle = title;
    }

    protected void finalize() {
        MediaPlayer mediaPlayer = this.mLocalPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class MyOnCompletionListener implements MediaPlayer.OnCompletionListener {
        MyOnCompletionListener() {
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mp) {
            synchronized (Ringtone.sActiveRingtones) {
                Ringtone.sActiveRingtones.remove(Ringtone.this);
            }
            mp.setOnCompletionListener(null);
        }
    }
}
