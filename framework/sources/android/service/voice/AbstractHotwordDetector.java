package android.service.voice;

import android.app.ActivityThread;
import android.media.AudioFormat;
import android.media.permission.Identity;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.os.SharedMemory;
import android.service.voice.AlwaysOnHotwordDetector;
import android.service.voice.HotwordDetector;
import android.service.voice.IMicrophoneHotwordDetectionVoiceInteractionCallback;
import com.android.internal.app.IHotwordRecognitionStatusCallback;
import com.android.internal.app.IVoiceInteractionManagerService;
import com.android.internal.util.function.pooled.PooledLambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public abstract class AbstractHotwordDetector implements HotwordDetector {
    private static final boolean DEBUG = false;
    private static final String TAG = AbstractHotwordDetector.class.getSimpleName();
    private final HotwordDetector.Callback mCallback;
    private final IVoiceInteractionManagerService mManagerService;
    protected final Object mLock = new Object();
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractHotwordDetector(IVoiceInteractionManagerService managerService, HotwordDetector.Callback callback) {
        this.mManagerService = managerService;
        this.mCallback = callback;
    }

    @Override // android.service.voice.HotwordDetector
    public boolean startRecognition(ParcelFileDescriptor audioStream, AudioFormat audioFormat, PersistableBundle options) {
        try {
            this.mManagerService.startListeningFromExternalSource(audioStream, audioFormat, options, new BinderCallback(this.mHandler, this.mCallback));
            return true;
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
            return true;
        }
    }

    @Override // android.service.voice.HotwordDetector
    public void updateState(PersistableBundle options, SharedMemory sharedMemory) {
        synchronized (this.mLock) {
            updateStateLocked(options, sharedMemory, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateStateLocked(PersistableBundle options, SharedMemory sharedMemory, IHotwordRecognitionStatusCallback callback) {
        Identity identity = new Identity();
        identity.packageName = ActivityThread.currentOpPackageName();
        try {
            this.mManagerService.updateState(identity, options, sharedMemory, callback);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class BinderCallback extends IMicrophoneHotwordDetectionVoiceInteractionCallback.Stub {
        private final HotwordDetector.Callback mCallback;
        private final Handler mHandler;

        BinderCallback(Handler handler, HotwordDetector.Callback callback) {
            this.mHandler = handler;
            this.mCallback = callback;
        }

        @Override // android.service.voice.IMicrophoneHotwordDetectionVoiceInteractionCallback
        public void onDetected(HotwordDetectedResult hotwordDetectedResult, AudioFormat audioFormat, ParcelFileDescriptor audioStreamIgnored) {
            this.mHandler.sendMessage(PooledLambda.obtainMessage(AbstractHotwordDetector$BinderCallback$$ExternalSyntheticLambda0.INSTANCE, this.mCallback, new AlwaysOnHotwordDetector.EventPayload(audioFormat, hotwordDetectedResult)));
        }
    }
}
