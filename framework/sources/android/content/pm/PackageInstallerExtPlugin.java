package android.content.pm;

import com.oplus.reflect.MethodParams;
import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefStaticMethod;
/* loaded from: classes.dex */
public class PackageInstallerExtPlugin {
    public static Class<?> TYPE = RefClass.load(PackageInstallerExtPlugin.class, "android.content.pm.PackageInstallerExtImpl");
    @MethodParams({int.class})
    public static RefStaticMethod<String> interceptCreateSession;
}
