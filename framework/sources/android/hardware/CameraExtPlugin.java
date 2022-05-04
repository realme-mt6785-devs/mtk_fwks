package android.hardware;

import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefStaticMethod;
/* loaded from: classes.dex */
public class CameraExtPlugin {
    public static Class<?> TYPE = RefClass.load(CameraExtPlugin.class, "android.hardware.CameraExtImpl");
    public static RefStaticMethod<ICameraExt> getInstance;
    public static RefStaticMethod<Boolean> interceptOpen;
    public static RefStaticMethod<Boolean> interceptOpenLegacy;
    public static RefStaticMethod<Boolean> interceptOpenWithCameraId;
}
