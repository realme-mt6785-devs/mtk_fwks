package com.android.internal.app;

import com.oplus.reflect.MethodParams;
import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefConstructor;
/* loaded from: classes4.dex */
public class ResolverListAdapterExtPlugin {
    public static Class<?> TYPE = RefClass.load(ResolverListAdapterExtPlugin.class, "com.android.internal.app.ResolverListAdapterExtImpl");
    @MethodParams({ResolverListAdapter.class})
    public static RefConstructor<IResolverListAdapterExt> constructor;
}
