package android.view;

import com.oplus.internal.reflect.MethodName;
import com.oplus.internal.reflect.RefClass;
import com.oplus.internal.reflect.RefConstructor;
/* loaded from: classes3.dex */
public class ViewExtPlugin {
    public static Class<?> TYPE = RefClass.load(ViewExtPlugin.class, "android.view.ViewExtImpl");
    @MethodName(params = {View.class})
    public static RefConstructor<IViewExt> constructor;
}
