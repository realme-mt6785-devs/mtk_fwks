package android.widget;

import android.widget.OverScroller;
import com.oplus.reflect.MethodParams;
import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefConstructor;
/* loaded from: classes3.dex */
public class SplineOverScrollerExtPlugin {
    public static Class<?> TYPE = RefClass.load(SplineOverScrollerExtPlugin.class, "android.widget.SplineOverScrollerExtImpl");
    @MethodParams({OverScroller.SplineOverScroller.class})
    public static RefConstructor<ISplineOverScrollerExt> constructor;
}
