package android.app;

import com.oplus.reflect.MethodParams;
import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefConstructor;
import com.oplus.reflect.RefStaticMethod;
/* loaded from: classes.dex */
public class WindowConfigurationExtPlugin {
    public static Class<?> TYPE = RefClass.load(WindowConfigurationExtPlugin.class, "android.app.WindowConfigurationExtImpl");
    public static RefConstructor<IWindowConfigurationExt> constructor;
    @MethodParams({int.class})
    public static RefStaticMethod<Boolean> isWindowingZoomMode;
    public static RefStaticMethod<Integer> sGetWindowingComactMode;
    @MethodParams({int.class})
    public static RefStaticMethod<Boolean> siSWindowingComactMode;
}
