package android.common;

import android.common.OplusFeatureList;
import com.oplus.reflect.RefClass;
import com.oplus.reflect.RefConstructor;
/* loaded from: classes.dex */
public class OplusExtPluginFactory implements IOplusCommonFactory {
    public static RefConstructor<OplusExtPluginFactory> constructor;
    private static volatile OplusExtPluginFactory sInstance = null;
    public static Class<?> TYPE = RefClass.load(OplusExtPluginFactory.class, "oplus.android.OplusExtPluginFactoryImp");

    public static synchronized OplusExtPluginFactory getInstance() {
        OplusExtPluginFactory oplusExtPluginFactory;
        RefConstructor<OplusExtPluginFactory> refConstructor;
        synchronized (OplusExtPluginFactory.class) {
            if (sInstance == null && (refConstructor = constructor) != null) {
                sInstance = refConstructor.newInstance();
                if (sInstance == null) {
                    sInstance = new OplusExtPluginFactory();
                }
            }
            oplusExtPluginFactory = sInstance;
        }
        return oplusExtPluginFactory;
    }

    @Override // android.common.IOplusCommonFactory
    public boolean isValid(int index) {
        return index < OplusFeatureList.OplusIndex.EndAOSPExtensionPluginFactory.ordinal() && index > OplusFeatureList.OplusIndex.StartAOSPExtensionPluginFactory.ordinal();
    }
}
