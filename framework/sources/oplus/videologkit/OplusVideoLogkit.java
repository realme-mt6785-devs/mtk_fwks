package oplus.videologkit;

import android.location.OplusLbsCommonConstant;
import android.os.SystemProperties;
import android.util.Log;
import dalvik.system.PathClassLoader;
import java.lang.reflect.Constructor;
/* loaded from: classes4.dex */
public class OplusVideoLogkit {
    private static final String TAG = "OplusVideoDebug";
    private static final String VIDEO_LOGKITEXT_IMPL_NAME = "com.oplus.videologkitext.OplusVideoLogkitExt";
    private static final String VIDEO_LOGKITEXT_JAR_PATH = "/system_ext/framework/videoLogcatExt.jar";
    private static volatile OplusVideoLogkit sInstance = null;
    private static final boolean isQcomPlatform = SystemProperties.get("ro.boot.hardware", "unknow").toLowerCase().startsWith(OplusLbsCommonConstant.QCOM_PLATFORM);
    private static final boolean isMTKPlatform = SystemProperties.get("ro.boot.hardware", "unknow").toLowerCase().startsWith("mt");

    public OplusVideoLogkit() {
        Log.d(TAG, "context:[OplusVideoLogkit]");
    }

    public static OplusVideoLogkit getInstance() {
        if (sInstance == null) {
            synchronized (OplusVideoLogkit.class) {
                if (sInstance == null) {
                    try {
                        sInstance = (OplusVideoLogkit) newInstance(VIDEO_LOGKITEXT_JAR_PATH, VIDEO_LOGKITEXT_IMPL_NAME);
                        Log.d(TAG, "new Instance");
                    } catch (Exception e) {
                        Log.e(TAG, " Reflect exception getInstance: " + e.toString());
                        sInstance = new OplusVideoLogkit();
                    }
                }
            }
        }
        return sInstance;
    }

    public void setParameter(String key, String value) {
    }

    public void setVideoLogOn() {
        Log.d(TAG, "setVideoLogOn");
        SystemProperties.set("debug.oplus.video.log.switch", "On");
        if (isQcomPlatform) {
            SystemProperties.set("debug.qcom.video.log.switch", "On");
        } else if (isMTKPlatform) {
            SystemProperties.set("debug.mtk.video.log.switch", "On");
        }
    }

    public void setVideoLogOff() {
        Log.d(TAG, "setVideoLogOff");
        SystemProperties.set("debug.oplus.video.log.switch", "Off");
        if (isQcomPlatform) {
            SystemProperties.set("debug.qcom.video.log.switch", "Off");
        } else if (isMTKPlatform) {
            SystemProperties.set("debug.mtk.video.log.switch", "Off");
        }
    }

    private static Object newInstance(String libPath, String className) throws Exception {
        PathClassLoader classLoader = new PathClassLoader(libPath, OplusVideoLogkit.class.getClassLoader());
        Class<?> clazz = Class.forName(className, false, classLoader);
        Constructor constructor = clazz.getConstructor(new Class[0]);
        return constructor.newInstance(new Object[0]);
    }
}
