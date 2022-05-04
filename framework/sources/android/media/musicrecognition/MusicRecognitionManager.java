package android.media.musicrecognition;

import android.annotation.SystemApi;
import android.media.MediaMetadata;
import android.media.musicrecognition.IMusicRecognitionManagerCallback;
import android.media.musicrecognition.MusicRecognitionManager;
import android.os.Bundle;
import android.os.RemoteException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;
import java.util.concurrent.Executor;
@SystemApi
/* loaded from: classes2.dex */
public class MusicRecognitionManager {
    public static final int RECOGNITION_FAILED_AUDIO_UNAVAILABLE = 7;
    public static final int RECOGNITION_FAILED_NOT_FOUND = 1;
    public static final int RECOGNITION_FAILED_NO_CONNECTIVITY = 2;
    public static final int RECOGNITION_FAILED_SERVICE_KILLED = 5;
    public static final int RECOGNITION_FAILED_SERVICE_UNAVAILABLE = 3;
    public static final int RECOGNITION_FAILED_TIMEOUT = 6;
    public static final int RECOGNITION_FAILED_UNKNOWN = -1;
    private final IMusicRecognitionManager mService;

    /* loaded from: classes2.dex */
    public interface RecognitionCallback {
        void onAudioStreamClosed();

        void onRecognitionFailed(RecognitionRequest recognitionRequest, int i);

        void onRecognitionSucceeded(RecognitionRequest recognitionRequest, MediaMetadata mediaMetadata, Bundle bundle);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface RecognitionFailureCode {
    }

    public MusicRecognitionManager(IMusicRecognitionManager service) {
        this.mService = service;
    }

    @SystemApi
    public void beginStreamingSearch(RecognitionRequest recognitionRequest, Executor callbackExecutor, RecognitionCallback callback) {
        try {
            IMusicRecognitionManager iMusicRecognitionManager = this.mService;
            Objects.requireNonNull(recognitionRequest);
            Objects.requireNonNull(recognitionRequest);
            Objects.requireNonNull(callback);
            Objects.requireNonNull(callbackExecutor);
            iMusicRecognitionManager.beginRecognition(recognitionRequest, new MusicRecognitionCallbackWrapper(recognitionRequest, callback, callbackExecutor));
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    /* loaded from: classes2.dex */
    private final class MusicRecognitionCallbackWrapper extends IMusicRecognitionManagerCallback.Stub {
        private final RecognitionCallback mCallback;
        private final Executor mCallbackExecutor;
        private final RecognitionRequest mRecognitionRequest;

        MusicRecognitionCallbackWrapper(RecognitionRequest recognitionRequest, RecognitionCallback callback, Executor callbackExecutor) {
            this.mRecognitionRequest = recognitionRequest;
            this.mCallback = callback;
            this.mCallbackExecutor = callbackExecutor;
        }

        @Override // android.media.musicrecognition.IMusicRecognitionManagerCallback
        public void onRecognitionSucceeded(final MediaMetadata result, final Bundle extras) {
            this.mCallbackExecutor.execute(new Runnable() { // from class: android.media.musicrecognition.MusicRecognitionManager$MusicRecognitionCallbackWrapper$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    MusicRecognitionManager.MusicRecognitionCallbackWrapper.this.lambda$onRecognitionSucceeded$0$MusicRecognitionManager$MusicRecognitionCallbackWrapper(result, extras);
                }
            });
        }

        public /* synthetic */ void lambda$onRecognitionSucceeded$0$MusicRecognitionManager$MusicRecognitionCallbackWrapper(MediaMetadata result, Bundle extras) {
            this.mCallback.onRecognitionSucceeded(this.mRecognitionRequest, result, extras);
        }

        @Override // android.media.musicrecognition.IMusicRecognitionManagerCallback
        public void onRecognitionFailed(final int failureCode) {
            this.mCallbackExecutor.execute(new Runnable() { // from class: android.media.musicrecognition.MusicRecognitionManager$MusicRecognitionCallbackWrapper$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    MusicRecognitionManager.MusicRecognitionCallbackWrapper.this.lambda$onRecognitionFailed$1$MusicRecognitionManager$MusicRecognitionCallbackWrapper(failureCode);
                }
            });
        }

        public /* synthetic */ void lambda$onRecognitionFailed$1$MusicRecognitionManager$MusicRecognitionCallbackWrapper(int failureCode) {
            this.mCallback.onRecognitionFailed(this.mRecognitionRequest, failureCode);
        }

        @Override // android.media.musicrecognition.IMusicRecognitionManagerCallback
        public void onAudioStreamClosed() {
            Executor executor = this.mCallbackExecutor;
            final RecognitionCallback recognitionCallback = this.mCallback;
            Objects.requireNonNull(recognitionCallback);
            executor.execute(new Runnable() { // from class: android.media.musicrecognition.MusicRecognitionManager$MusicRecognitionCallbackWrapper$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    MusicRecognitionManager.RecognitionCallback.this.onAudioStreamClosed();
                }
            });
        }
    }
}
