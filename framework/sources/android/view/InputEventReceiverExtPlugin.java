package android.view;

import com.oplus.reflect.MethodParams;
import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefConstructor;
/* loaded from: classes3.dex */
public class InputEventReceiverExtPlugin {
    public static Class<?> TYPE = RefClass.load(InputEventReceiverExtPlugin.class, "android.view.InputEventReceiverExtImpl");
    @MethodParams({InputEventReceiver.class})
    public static RefConstructor<IInputEventReceiverExt> constructor;
}
