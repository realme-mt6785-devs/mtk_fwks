package com.android.internal.app;

import android.content.Context;
import com.oplus.reflect.MethodParams;
import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefConstructor;
/* loaded from: classes4.dex */
public class MultiProfilePagerAdapterExtPlugin {
    public static Class<?> TYPE = RefClass.load(MultiProfilePagerAdapterExtPlugin.class, "com.android.internal.app.MultiProfilePagerAdapterExtImpl");
    @MethodParams({Context.class, AbstractMultiProfilePagerAdapter.class})
    public static RefConstructor<IMultiProfilePagerAdapterExt> constructor;
}
