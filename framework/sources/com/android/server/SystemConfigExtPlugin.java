package com.android.server;

import com.oplus.reflect.MethodParams;
import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefConstructor;
/* loaded from: classes4.dex */
public class SystemConfigExtPlugin {
    public static Class<?> TYPE = RefClass.load(SystemConfigExtPlugin.class, "com.android.server.SystemConfigExtImpl");
    @MethodParams({SystemConfig.class})
    public static RefConstructor<ISystemConfigExt> constructor;
}
