package android.service.voice;

import android.annotation.SystemApi;
import android.media.AudioFormat;
import android.os.ParcelFileDescriptor;
import android.os.PersistableBundle;
import android.os.SharedMemory;
import android.service.voice.AlwaysOnHotwordDetector;
@SystemApi
/* loaded from: classes3.dex */
public interface HotwordDetector {

    /* loaded from: classes3.dex */
    public interface Callback {
        void onDetected(AlwaysOnHotwordDetector.EventPayload eventPayload);

        void onError();

        void onHotwordDetectionServiceInitialized(int i);

        void onHotwordDetectionServiceRestarted();

        void onRecognitionPaused();

        void onRecognitionResumed();

        void onRejected(HotwordRejectedResult hotwordRejectedResult);
    }

    boolean startRecognition();

    boolean startRecognition(ParcelFileDescriptor parcelFileDescriptor, AudioFormat audioFormat, PersistableBundle persistableBundle);

    boolean stopRecognition();

    void updateState(PersistableBundle persistableBundle, SharedMemory sharedMemory);
}
