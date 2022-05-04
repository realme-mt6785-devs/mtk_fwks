package android.hardware.camera2;
/* loaded from: classes.dex */
public interface ICameraManagerExt {
    void extendCameraManager(String str);

    String extendGetComponentName();

    void extendSetPackageName();

    int getNumPhysicalCameras(String str);

    void ormsSetSceneAction(String str, int i);

    default boolean interceptOpenCameraForUid() {
        return false;
    }
}
