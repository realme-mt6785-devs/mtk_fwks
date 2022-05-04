package android.app;

import com.oplus.reflect.MethodParams;
import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefStaticMethod;
/* loaded from: classes.dex */
public class SystemServiceRegistryExtPlugin {
    public static Class<?> TYPE = RefClass.load(SystemServiceRegistryExtPlugin.class, "android.app.SystemServiceRegistryExtImpl");
    @MethodParams({int.class})
    public static RefStaticMethod<Integer> checkAppPackageName;
    @MethodParams({})
    public static RefStaticMethod<Void> registerService;
}
