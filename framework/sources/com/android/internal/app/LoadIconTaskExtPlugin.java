package com.android.internal.app;

import com.android.internal.app.ResolverListAdapter;
import com.oplus.reflect.MethodParams;
import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefConstructor;
/* loaded from: classes4.dex */
public class LoadIconTaskExtPlugin {
    public static Class<?> TYPE = RefClass.load(LoadIconTaskExtPlugin.class, "com.android.internal.app.LoadIconTaskExtImpl");
    @MethodParams({ResolverListAdapter.LoadIconTask.class})
    public static RefConstructor<ILoadIconTaskExt> constructor;
}
