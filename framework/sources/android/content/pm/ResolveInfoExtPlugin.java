package android.content.pm;

import android.graphics.drawable.Drawable;
import com.oplus.reflect.MethodParams;
import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefStaticMethod;
/* loaded from: classes.dex */
public class ResolveInfoExtPlugin {
    public static Class<?> TYPE = RefClass.load(ResolveInfoExtPlugin.class, "android.content.pm.ResolveInfoExtImpl");
    @MethodParams({PackageManager.class, ResolveInfo.class, String.class, int.class, ApplicationInfo.class})
    public static RefStaticMethod<Drawable> getDrawableByComponentInfoInLoadIcon;
    @MethodParams({PackageManager.class, ResolveInfo.class, String.class, int.class})
    public static RefStaticMethod<Drawable> getDrawableByResolvePackageNameInLoadIcon;
}
