package android.mtp;

import android.content.Context;
import com.oplus.reflect.MethodParams;
import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefStaticMethod;
/* loaded from: classes2.dex */
public class MtpDatabaseExtPlugin {
    public static Class<?> TYPE = RefClass.load(MtpDatabaseExtPlugin.class, "android.mtp.MtpDatabaseExtImpl");
    @MethodParams({Context.class})
    public static RefStaticMethod<IMtpDatabaseExt> getInstance;
}
