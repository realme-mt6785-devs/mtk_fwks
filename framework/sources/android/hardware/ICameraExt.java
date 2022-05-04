package android.hardware;

import android.hardware.Camera;
/* loaded from: classes.dex */
public interface ICameraExt {
    void extendCamera(int i, long j);

    void extendRelease(long j);

    void extendhandleMessage(int i);

    void extendstopPreview(Camera.Parameters parameters);

    void extendtakePicture(Camera.Parameters parameters);

    String getComponentName();

    int getNumPhysicalCameras(String str);

    default boolean interceptTakePicture() {
        return false;
    }
}
