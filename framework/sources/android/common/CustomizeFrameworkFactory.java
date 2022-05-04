package android.common;

import android.common.OplusFeatureList;
import android.util.Log;
import java.lang.reflect.Constructor;
/* loaded from: classes.dex */
public class CustomizeFrameworkFactory implements IOplusCommonFactory {
    private static final String CLASSNAME1 = "oplus.android.CustomizeFrameworkFactoryImpl1";
    private static final String CLASSNAME2 = "oplus.android.CustomizeFrameworkFactoryImpl2";
    private static final String CLASSNAME3 = "oplus.android.CustomizeFrameworkFactoryImpl3";
    private static final String TAG = "CustomizeFrameworkFactory";
    private static volatile CustomizeFrameworkFactory sInstance = null;

    public static CustomizeFrameworkFactory getInstance() {
        if (sInstance == null) {
            synchronized (CustomizeFrameworkFactory.class) {
                try {
                    if (sInstance == null) {
                        sInstance = (CustomizeFrameworkFactory) newInstance(CLASSNAME1);
                    }
                    if (sInstance == null) {
                        sInstance = (CustomizeFrameworkFactory) newInstance(CLASSNAME2);
                    }
                    if (sInstance == null) {
                        sInstance = (CustomizeFrameworkFactory) newInstance(CLASSNAME3);
                    }
                } catch (Exception e) {
                    Log.e(TAG, " Reflect exception getInstance: " + e.toString());
                    sInstance = new CustomizeFrameworkFactory();
                }
            }
        }
        return sInstance;
    }

    @Override // android.common.IOplusCommonFactory
    public boolean isValid(int index) {
        boolean validCustomize = index < OplusFeatureList.OplusIndex.EndCustomizeFrameworkFactory.ordinal() && index > OplusFeatureList.OplusIndex.StartCustomizeFrameworkFactory.ordinal();
        Log.i(TAG, "isValid = " + validCustomize + "index = " + index);
        return validCustomize;
    }

    static Object newInstance(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        Constructor constructor = clazz.getConstructor(new Class[0]);
        return constructor.newInstance(new Object[0]);
    }
}
