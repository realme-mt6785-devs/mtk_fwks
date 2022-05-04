package com.android.internal.app;

import com.oplus.reflect.MethodParams;
import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefConstructor;
/* loaded from: classes4.dex */
public class ResolverActivityExtPlugin {
    public static Class<?> TYPE = RefClass.load(ResolverActivityExtPlugin.class, "com.android.internal.app.ResolverActivityExtImpl");
    @MethodParams({ResolverActivity.class})
    public static RefConstructor<IResolverActivityExt> constructor;
}
