package com.android.internal.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.oplus.reflect.MethodParams;
import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefConstructor;
import com.oplus.reflect.RefStaticMethod;
/* loaded from: classes4.dex */
public class FloatingToolbarExtPlugin {
    public static Class<?> TYPE = RefClass.load(FloatingToolbarExtPlugin.class, "com.android.internal.widget.FloatingToolbarExtImpl");
    public static RefConstructor<IFloatingToolbarExt> constructor;
    @MethodParams({Context.class})
    public static RefStaticMethod<ViewGroup> hookCreateContentContainer;
    @MethodParams({Context.class})
    public static RefStaticMethod<View> hookCreateMenuItemButton;
    @MethodParams({View.class})
    public static RefStaticMethod<ImageView> hookUpdateMenuItemButtonForIcon;
    @MethodParams({View.class})
    public static RefStaticMethod<TextView> hookUpdateMenuItemButtonForText;
}
