package android.widget;

import com.oplus.reflect.MethodParams;
import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefConstructor;
/* loaded from: classes3.dex */
public class OplusOverScrollerExtPlugin {
    public static Class<?> TYPE = RefClass.load(OplusOverScrollerExtPlugin.class, "android.widget.OplusOverScrollerExtImpl");
    @MethodParams({OverScroller.class})
    public static RefConstructor<IOplusOverScrollerExt> constructor;
}
