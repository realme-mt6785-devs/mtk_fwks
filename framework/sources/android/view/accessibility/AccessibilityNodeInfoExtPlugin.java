package android.view.accessibility;

import com.oplus.reflect.MethodParams;
import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefStaticMethod;
/* loaded from: classes3.dex */
public class AccessibilityNodeInfoExtPlugin {
    public static Class<?> TYPE = RefClass.load(AccessibilityNodeInfoExtPlugin.class, "android.view.accessibility.AccessibilityNodeInfoExtImpl");
    @MethodParams({AccessibilityNodeInfo.class})
    public static RefStaticMethod<CharSequence> getRealClassName;
    @MethodParams({CharSequence.class, AccessibilityNodeInfo.class})
    public static RefStaticMethod<Void> setRealClassName;
}
