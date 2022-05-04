package android.content.pm;

import android.content.pm.PackageParser;
import android.content.pm.parsing.ParsingPackage;
import com.oplus.internal.reflect.MethodName;
import com.oplus.internal.reflect.RefClass;
import com.oplus.internal.reflect.RefMethod;
/* loaded from: classes.dex */
public class PackageParserExtPlugin {
    public static Class<?> TYPE = RefClass.load(PackageParserExtPlugin.class, "android.content.pm.PackageParserExtImpl");
    @MethodName(params = {PackageParser.Package.class})
    public static RefMethod<Void> adjustPermissionInParseBaseApkCommon;
    @MethodName(params = {ParsingPackage.class})
    public static RefMethod<Void> adjustPermissionInParseBaseApkTags;
    @MethodName(params = {IApplicationInfoExt.class, PackageUserState.class})
    public static RefMethod<Void> hookUpdateApplicationInfo;
    @MethodName(params = {String.class})
    public static RefMethod<Boolean> skipValidateName;
}
