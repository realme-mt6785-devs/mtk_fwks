package android.hardware.camera2.impl;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
/* loaded from: classes.dex */
public interface ICameraDeviceImplExt {
    void extendParseSessionParameters(CaptureRequest captureRequest);

    boolean extendPrivilegedAppList(String str);

    boolean extendSession();

    void extendaddCaptureInfo(String str, CameraCharacteristics cameraCharacteristics, TotalCaptureResult totalCaptureResult);

    void extendsetCloseInfo(String str, long j);

    void extendsetInfo(String str, long j);

    void ormsSetSceneAction(String str, int i);
}
