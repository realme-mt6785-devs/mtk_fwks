package android.content.pm.parsing;

import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.IApplicationInfoExt;
import android.content.pm.IPackageUserStateExt;
import android.content.pm.PackageUserState;
import android.content.pm.parsing.component.ParsedActivity;
import com.oplus.reflect.MethodParams;
import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefStaticMethod;
/* loaded from: classes.dex */
public class PackageInfoWithoutStateUtilsExtPlugin {
    public static Class<?> TYPE = RefClass.load(PackageInfoWithoutStateUtilsExtPlugin.class, "android.content.pm.parsing.PackageInfoWithoutStateUtilsExtImpl");
    @MethodParams({ActivityInfo.class, ParsedActivity.class})
    public static RefStaticMethod<Void> adjustResultInGenerateActivityInfoUnchecked;
    @MethodParams({ApplicationInfo.class, PackageUserState.class, IApplicationInfoExt.class, IPackageUserStateExt.class})
    public static RefStaticMethod<Void> adjustResultInGenerateApplicationInfoUnchecked;
}
