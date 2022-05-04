package com.mediatek.server.vow;

import android.app.ActivityThread;
import android.content.Context;
import android.hardware.soundtrigger.IRecognitionStatusCallback;
import android.hardware.soundtrigger.SoundTrigger;
import android.media.permission.Identity;
import android.media.permission.PermissionUtil;
import android.media.permission.SafeCloseable;
import android.os.IBinder;
import android.util.Slog;
import com.android.server.LocalServices;
import com.android.server.SystemService;
import com.android.server.soundtrigger.SoundTriggerInternal;
import com.mediatek.vow.IVoiceWakeupBridge;
/* loaded from: classes.dex */
public class VoiceWakeupBridgeService extends SystemService {
    private static final String BINDER_TAG = "vow_bridge";
    private static final String TAG = "VoiceWakeupBridgeService";
    private IBinder mBinder;
    private final Context mContext;
    private final VoiceWakeupBridgeStub mServiceStub = new VoiceWakeupBridgeStub();
    private SoundTriggerInternal.Session mSoundTriggerInternalSession;

    public VoiceWakeupBridgeService(Context context) {
        super(context);
        this.mContext = context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.mediatek.server.vow.VoiceWakeupBridgeService$VoiceWakeupBridgeStub, android.os.IBinder] */
    public void onStart() {
        Slog.d(TAG, "onStart");
        publishBinderService(BINDER_TAG, this.mServiceStub);
    }

    public void onBootPhase(int phase) {
        Slog.d(TAG, "onBootPhase: " + phase);
        if (500 == phase) {
            Slog.d(TAG, "onBootPhase: mServiceStub:" + this.mServiceStub);
            try {
                SoundTriggerInternal mSoundTriggerInternal = (SoundTriggerInternal) LocalServices.getService(SoundTriggerInternal.class);
                this.mBinder = getBinderService(BINDER_TAG);
                Slog.d(TAG, "onBootPhase: mBinder:" + this.mBinder);
                Identity identity = new Identity();
                identity.packageName = ActivityThread.currentOpPackageName();
                SafeCloseable ignored = PermissionUtil.establishIdentityDirect(identity);
                Slog.d(TAG, "onBootPhase: SafeCloseable ignored:" + ignored);
                this.mSoundTriggerInternalSession = mSoundTriggerInternal.attach(this.mBinder);
                Slog.d(TAG, "onBootPhase: mSoundTriggerInternalSession:" + this.mSoundTriggerInternalSession);
            } catch (Throwable e) {
                Slog.d(TAG, "onBootPhase: Error:" + e.getMessage());
                Slog.e(TAG, "onBootPhaseError", e);
                e.printStackTrace();
            }
        } else if (600 == phase) {
            Slog.d(TAG, "onBootPhase: PHASE_THIRD_PARTY_APPS_CAN_START");
        }
    }

    /* loaded from: classes.dex */
    class VoiceWakeupBridgeStub extends IVoiceWakeupBridge.Stub {
        VoiceWakeupBridgeStub() {
        }

        public int startRecognition(int keyphraseId, SoundTrigger.KeyphraseSoundModel soundModel, IRecognitionStatusCallback listener, SoundTrigger.RecognitionConfig recognitionConfig) {
            Slog.d(VoiceWakeupBridgeService.TAG, "startRecognition");
            if (VoiceWakeupBridgeService.this.mSoundTriggerInternalSession != null) {
                Slog.w(VoiceWakeupBridgeService.TAG, "startRecognition mSoundTriggerInternalSession");
                return VoiceWakeupBridgeService.this.mSoundTriggerInternalSession.startRecognition(keyphraseId, soundModel, listener, recognitionConfig, false);
            }
            Slog.w(VoiceWakeupBridgeService.TAG, "startRecognition mSoundTriggerInternalSession null obj");
            return 0;
        }

        public int stopRecognition(int keyphraseId, IRecognitionStatusCallback listener) {
            Slog.d(VoiceWakeupBridgeService.TAG, "stopRecognition");
            if (VoiceWakeupBridgeService.this.mSoundTriggerInternalSession != null) {
                Slog.w(VoiceWakeupBridgeService.TAG, "stopRecognition mSoundTriggerInternalSession");
                return VoiceWakeupBridgeService.this.mSoundTriggerInternalSession.stopRecognition(keyphraseId, listener);
            }
            Slog.w(VoiceWakeupBridgeService.TAG, "stopRecognition mSoundTriggerInternalSession null obj");
            return 0;
        }

        public int unloadKeyphraseModel(int keyphaseId) {
            Slog.d(VoiceWakeupBridgeService.TAG, "unloadKeyphraseModel");
            if (VoiceWakeupBridgeService.this.mSoundTriggerInternalSession != null) {
                Slog.w(VoiceWakeupBridgeService.TAG, "unloadKeyphraseModel mSoundTriggerInternalSession");
                return VoiceWakeupBridgeService.this.mSoundTriggerInternalSession.unloadKeyphraseModel(keyphaseId);
            }
            Slog.w(VoiceWakeupBridgeService.TAG, "unloadKeyphraseModel mSoundTriggerInternalSession null obj");
            return 0;
        }
    }
}
