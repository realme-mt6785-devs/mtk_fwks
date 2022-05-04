package com.android.internal.os;

import com.oplus.reflect.MethodParams;
import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefConstructor;
import com.oplus.reflect.RefStaticMethod;
/* loaded from: classes4.dex */
public class RuntimeInitExtPlugin {
    public static Class<?> TYPE = RefClass.load(RuntimeInitExtPlugin.class, "com.android.internal.os.RuntimeInitExtImpl");
    public static RefConstructor<IRuntimeInitExt> constructor;
    @MethodParams({ClassLoader.class})
    public static RefStaticMethod<Void> preload;
}
