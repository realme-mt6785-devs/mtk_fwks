package android.os;

import com.oplus.reflect.MethodParams;
import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefStaticMethod;
/* loaded from: classes2.dex */
public class OplusJankMonitorExtPlugin {
    public static Class<?> TYPE = RefClass.load(OplusJankMonitorExtPlugin.class, "android.os.OplusJankMonitorExtImpl");
    @MethodParams({String.class, String.class, long.class})
    public static RefStaticMethod<Void> setLaunchStageTime;
    @MethodParams({boolean.class, String.class, int.class, int.class})
    public static RefStaticMethod<Void> startLaunchTrace;
    @MethodParams({String.class, int.class, long.class})
    public static RefStaticMethod<Void> stopLaunchTrace;
}
