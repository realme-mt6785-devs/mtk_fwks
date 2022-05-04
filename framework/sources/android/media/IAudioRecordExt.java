package android.media;
/* loaded from: classes2.dex */
public interface IAudioRecordExt {
    default boolean permInterceptInStartRecording() {
        return false;
    }

    default boolean permInterceptInStartRecordingWithEvent() {
        return false;
    }
}
