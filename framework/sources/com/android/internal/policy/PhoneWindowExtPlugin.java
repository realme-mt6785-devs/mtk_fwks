package com.android.internal.policy;

import android.content.Context;
import com.oplus.internal.reflect.MethodName;
import com.oplus.internal.reflect.RefClass;
import com.oplus.internal.reflect.RefConstructor;
/* loaded from: classes4.dex */
public class PhoneWindowExtPlugin {
    public static Class<?> TYPE = RefClass.load(PhoneWindowExtPlugin.class, "com.android.internal.policy.PhoneWindowExtImpl");
    @MethodName(params = {PhoneWindow.class, Context.class})
    public static RefConstructor<IPhoneWindowExt> constructor;
}
