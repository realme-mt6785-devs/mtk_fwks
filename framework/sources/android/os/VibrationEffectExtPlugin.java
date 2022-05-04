package android.os;

import com.oplus.reflect.MethodParams;
import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefStaticMethod;
/* loaded from: classes2.dex */
public class VibrationEffectExtPlugin {
    public static Class<?> TYPE = RefClass.load(VibrationEffectExtPlugin.class, "android.os.VibrationEffectExtImpl");
    @MethodParams({Parcel.class})
    public static RefStaticMethod<VibrationEffect> createExtendedEffect;
}
