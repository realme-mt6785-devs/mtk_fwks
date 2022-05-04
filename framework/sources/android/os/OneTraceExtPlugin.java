package android.os;

import com.oplus.reflect.MethodParams;
import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefStaticMethod;
/* loaded from: classes2.dex */
public class OneTraceExtPlugin {
    public static final long TRACE_TAG_BINDER = 8589934592L;
    public static final long TRACE_TAG_LOOPER = 4294967296L;
    public static Class<?> TYPE = RefClass.load(OneTraceExtPlugin.class, "android.os.OneTraceExtImpl");
    @MethodParams({long.class})
    public static RefStaticMethod<Boolean> isOneTraceEnable;
    @MethodParams({String.class})
    public static RefStaticMethod<String> matchDescriptor;
    @MethodParams({long.class, String.class})
    public static RefStaticMethod<Void> oneTraceBegin;
    @MethodParams({long.class, String.class, int.class})
    public static RefStaticMethod<Void> oneTraceBeginAsync;
    @MethodParams({long.class})
    public static RefStaticMethod<Void> oneTraceEnd;
    @MethodParams({long.class, String.class, int.class})
    public static RefStaticMethod<Void> oneTraceEndAsync;
    @MethodParams({boolean.class})
    public static RefStaticMethod<Void> setActiveState;
}
