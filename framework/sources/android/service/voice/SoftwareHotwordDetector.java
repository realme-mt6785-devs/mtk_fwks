package android.service.voice;

import android.hardware.soundtrigger.SoundTrigger;
import android.media.AudioFormat;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.os.SharedMemory;
import android.service.voice.AlwaysOnHotwordDetector;
import android.service.voice.HotwordDetector;
import android.service.voice.IMicrophoneHotwordDetectionVoiceInteractionCallback;
import android.util.Slog;
import com.android.internal.app.IHotwordRecognitionStatusCallback;
import com.android.internal.app.IVoiceInteractionManagerService;
import com.android.internal.util.function.pooled.PooledLambda;
import java.io.PrintWriter;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class SoftwareHotwordDetector extends AbstractHotwordDetector {
    private static final boolean DEBUG = false;
    private static final String TAG = SoftwareHotwordDetector.class.getSimpleName();
    private final AudioFormat mAudioFormat;
    private final HotwordDetector.Callback mCallback;
    private final Handler mHandler;
    private final IVoiceInteractionManagerService mManagerService;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SoftwareHotwordDetector(IVoiceInteractionManagerService managerService, AudioFormat audioFormat, PersistableBundle options, SharedMemory sharedMemory, HotwordDetector.Callback callback) {
        super(managerService, callback);
        this.mManagerService = managerService;
        this.mAudioFormat = audioFormat;
        this.mCallback = callback;
        Handler handler = new Handler(Looper.getMainLooper());
        this.mHandler = handler;
        updateStateLocked(options, sharedMemory, new InitializationStateListener(handler, callback));
    }

    @Override // android.service.voice.HotwordDetector
    public boolean startRecognition() {
        maybeCloseExistingSession();
        try {
            this.mManagerService.startListeningFromMic(this.mAudioFormat, new BinderCallback(this.mHandler, this.mCallback));
            return true;
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
            return true;
        } catch (SecurityException e2) {
            String str = TAG;
            Slog.e(str, "startRecognition failed: " + e2);
            return false;
        }
    }

    @Override // android.service.voice.HotwordDetector
    public boolean stopRecognition() {
        try {
            this.mManagerService.stopListeningFromMic();
            return true;
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
            return true;
        }
    }

    private void maybeCloseExistingSession() {
    }

    /* loaded from: classes3.dex */
    private static class BinderCallback extends IMicrophoneHotwordDetectionVoiceInteractionCallback.Stub {
        private final HotwordDetector.Callback mCallback;
        private final Handler mHandler;

        BinderCallback(Handler handler, HotwordDetector.Callback callback) {
            this.mHandler = handler;
            this.mCallback = callback;
        }

        @Override // android.service.voice.IMicrophoneHotwordDetectionVoiceInteractionCallback
        public void onDetected(HotwordDetectedResult hotwordDetectedResult, AudioFormat audioFormat, ParcelFileDescriptor audioStream) {
            this.mHandler.sendMessage(PooledLambda.obtainMessage(AbstractHotwordDetector$BinderCallback$$ExternalSyntheticLambda0.INSTANCE, this.mCallback, new AlwaysOnHotwordDetector.EventPayload(audioFormat, hotwordDetectedResult, audioStream)));
        }
    }

    /* loaded from: classes3.dex */
    private static class InitializationStateListener extends IHotwordRecognitionStatusCallback.Stub {
        private final HotwordDetector.Callback mCallback;
        private final Handler mHandler;

        InitializationStateListener(Handler handler, HotwordDetector.Callback callback) {
            this.mHandler = handler;
            this.mCallback = callback;
        }

        @Override // com.android.internal.app.IHotwordRecognitionStatusCallback
        public void onKeyphraseDetected(SoundTrigger.KeyphraseRecognitionEvent recognitionEvent, HotwordDetectedResult result) {
        }

        @Override // com.android.internal.app.IHotwordRecognitionStatusCallback
        public void onGenericSoundTriggerDetected(SoundTrigger.GenericRecognitionEvent recognitionEvent) throws RemoteException {
        }

        @Override // com.android.internal.app.IHotwordRecognitionStatusCallback
        public void onRejected(HotwordRejectedResult result) throws RemoteException {
        }

        @Override // com.android.internal.app.IHotwordRecognitionStatusCallback
        public void onError(int status) throws RemoteException {
        }

        @Override // com.android.internal.app.IHotwordRecognitionStatusCallback
        public void onRecognitionPaused() throws RemoteException {
        }

        @Override // com.android.internal.app.IHotwordRecognitionStatusCallback
        public void onRecognitionResumed() throws RemoteException {
        }

        @Override // com.android.internal.app.IHotwordRecognitionStatusCallback
        public void onStatusReported(int status) {
            String str = SoftwareHotwordDetector.TAG;
            Slog.v(str, "onStatusReported");
            this.mHandler.sendMessage(PooledLambda.obtainMessage(SoftwareHotwordDetector$InitializationStateListener$$ExternalSyntheticLambda0.INSTANCE, this.mCallback, Integer.valueOf(status)));
        }

        @Override // com.android.internal.app.IHotwordRecognitionStatusCallback
        public void onProcessRestarted() throws RemoteException {
            Slog.v(SoftwareHotwordDetector.TAG, "onProcessRestarted()");
            this.mHandler.sendMessage(PooledLambda.obtainMessage(SoftwareHotwordDetector$InitializationStateListener$$ExternalSyntheticLambda1.INSTANCE, this.mCallback));
        }
    }

    public void dump(String prefix, PrintWriter pw) {
    }
}
