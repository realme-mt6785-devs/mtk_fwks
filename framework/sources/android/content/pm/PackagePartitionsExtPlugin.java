package android.content.pm;

import com.oplus.reflect.MethodParams;
import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefStaticMethod;
import java.util.ArrayList;
import java.util.function.Function;
/* loaded from: classes.dex */
public class PackagePartitionsExtPlugin {
    public static Class<?> TYPE = RefClass.load(PackagePartitionsExtPlugin.class, "android.content.pm.PackagePartitionsExtImpl");
    @MethodParams({Function.class, ArrayList.class})
    public static RefStaticMethod<Void> adjustGetOrderedPartitions;
}
