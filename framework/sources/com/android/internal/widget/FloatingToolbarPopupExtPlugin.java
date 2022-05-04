package com.android.internal.widget;

import com.oplus.internal.reflect.RefClass;
import com.oplus.internal.reflect.RefConstructor;
import com.oplus.internal.reflect.RefMethod;
/* loaded from: classes4.dex */
public class FloatingToolbarPopupExtPlugin {
    public static Class<?> TYPE = RefClass.load(FloatingToolbarPopupExtPlugin.class, "com.android.internal.widget.FloatingToolbarPopupExtImpl");
    public static RefConstructor<IFloatingToolbarPopupExt> constructor;
    public static RefMethod<Integer> hookMaxOverflowSize;
    public static RefMethod<Integer> hookMinOverflowSize;
}
