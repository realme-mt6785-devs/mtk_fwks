package android.provider;

import android.content.ContentResolver;
import android.util.ArraySet;
import com.oplus.reflect.MethodParams;
import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefConstructor;
import com.oplus.reflect.RefStaticMethod;
/* loaded from: classes2.dex */
public class SettingsExtPlugin {
    public static Class<?> TYPE = RefClass.load(SettingsExtPlugin.class, "android.provider.SettingsExtImpl");
    @MethodParams({ArraySet.class})
    public static RefStaticMethod<Void> addKeyToPublicSettings;
    public static RefConstructor<ISettingsExt> constructor;
    @MethodParams({ContentResolver.class, String.class, String.class})
    public static RefStaticMethod<Integer> getAutoBrightnessValueForUser;
    @MethodParams({String.class, String.class, int.class})
    public static RefStaticMethod<Integer> getAutoBrightnessValueForUserWithDef;
    @MethodParams({ContentResolver.class, String.class, String.class, int.class})
    public static RefStaticMethod<Integer> putAutoBrightnessValueForUser;
    @MethodParams({int.class, String.class})
    public static RefStaticMethod<Integer> redirectUserIfNeeded;
}
