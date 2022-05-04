package com.android.internal.os;

import android.content.res.Resources;
import com.oplus.reflect.MethodParams;
import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefStaticMethod;
/* loaded from: classes4.dex */
public class ZygoteInitExtPlugin {
    public static Class<?> TYPE = RefClass.load(ZygoteInitExtPlugin.class, "com.android.internal.os.ZygoteInitExtImpl");
    @MethodParams({boolean.class})
    public static RefStaticMethod<Void> beginHookGcAndFinalize;
    public static RefStaticMethod<Void> beginHookPreload;
    @MethodParams({boolean.class})
    public static RefStaticMethod<Void> endHookGcAndFinalize;
    public static RefStaticMethod<Void> endHookPreload;
    @MethodParams({Resources.class, String.class})
    public static RefStaticMethod<Void> hookPreloadResources;
}
