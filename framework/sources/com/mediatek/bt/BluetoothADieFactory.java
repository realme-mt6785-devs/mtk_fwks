package com.mediatek.bt;

import android.util.Log;
import dalvik.system.PathClassLoader;
import java.lang.reflect.Constructor;
/* loaded from: classes4.dex */
public class BluetoothADieFactory {
    private static final String TAG = "BluetoothADieFactory";
    private static Object lock = new Object();
    private static BluetoothADieFactory sInstance;

    public static BluetoothADieFactory getInstance() {
        if (sInstance == null) {
            synchronized (lock) {
                if (sInstance == null) {
                    try {
                        PathClassLoader classLoader = new PathClassLoader("/system/framework/mediatek-framework.jar", BluetoothADieFactory.class.getClassLoader());
                        Class<?> clazz = Class.forName("com.mediatek.bt.BluetoothADieFactoryImpl", false, classLoader);
                        Constructor constructor = clazz.getConstructor(new Class[0]);
                        sInstance = (BluetoothADieFactory) constructor.newInstance(new Object[0]);
                    } catch (Exception e) {
                        Log.e(TAG, "getInstance exception");
                        sInstance = null;
                    }
                }
            }
        }
        return sInstance;
    }

    public boolean isAdieFail() {
        Log.d(TAG, "isAdieFail");
        BluetoothADieFactory bluetoothADieFactory = sInstance;
        if (bluetoothADieFactory != null) {
            return bluetoothADieFactory.isAdieFail();
        }
        Log.e(TAG, "isAdieFail sInstance is null");
        return false;
    }
}
