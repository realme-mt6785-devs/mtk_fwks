package android.common;

import android.common.OplusFeatureList;
import android.util.Log;
import com.oplus.multiapp.IOplusMultiApp;
import java.lang.reflect.Constructor;
/* loaded from: classes.dex */
public class OplusFrameworkFactory implements IOplusCommonFactory {
    public static final String OPLUS_FRAMEWORK_FACTORY_IMPL_NAME = "oplus.android.OplusFrameworkFactoryImpl";
    private static final String TAG = "OplusFrameworkFactory";
    private static volatile OplusFrameworkFactory sInstance = null;

    public static OplusFrameworkFactory getInstance() {
        if (sInstance == null) {
            synchronized (OplusFrameworkFactory.class) {
                if (sInstance == null) {
                    try {
                        sInstance = (OplusFrameworkFactory) newInstance(OPLUS_FRAMEWORK_FACTORY_IMPL_NAME);
                    } catch (Exception e) {
                        Log.e(TAG, " Reflect exception getInstance: " + e.toString());
                        sInstance = new OplusFrameworkFactory();
                    }
                }
            }
        }
        return sInstance;
    }

    @Override // android.common.IOplusCommonFactory
    public boolean isValid(int index) {
        boolean validOplus = index < OplusFeatureList.OplusIndex.EndOplusFrameworkFactory.ordinal() && index > OplusFeatureList.OplusIndex.StartOplusFrameworkFactory.ordinal();
        boolean vaildOplusOs = index < OplusFeatureList.OplusIndex.EndOplusOsFrameworkFactory.ordinal() && index > OplusFeatureList.OplusIndex.StartOplusOsFrameworkFactory.ordinal();
        return vaildOplusOs || validOplus;
    }

    static Object newInstance(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        Constructor constructor = clazz.getConstructor(new Class[0]);
        return constructor.newInstance(new Object[0]);
    }

    public IOplusMultiApp getOplusMultiApp() {
        return IOplusMultiApp.DEFAULT;
    }
}
