package android.text;

import com.oplus.reflect.MethodParams;
import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefConstructor;
/* loaded from: classes3.dex */
public class TextLineExtPlugin {
    public static Class<?> TYPE = RefClass.load(TextLineExtPlugin.class, "android.text.TextLineExtImpl");
    @MethodParams({TextLine.class})
    public static RefConstructor<ITextLineExt> constructor;
}
