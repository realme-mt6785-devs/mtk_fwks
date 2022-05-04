package android.inputmethodservice;

import com.oplus.reflect.MethodParams;
import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefConstructor;
/* loaded from: classes2.dex */
public class InputMethodServiceExtPlugin {
    public static Class<?> TYPE = RefClass.load(InputMethodServiceExtPlugin.class, "android.inputmethodservice.InputMethodServiceExtImpl");
    @MethodParams({InputMethodService.class})
    public static RefConstructor<IInputMethodServiceExt> constructor;
}
