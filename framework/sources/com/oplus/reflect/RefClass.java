package com.oplus.reflect;

import android.util.Log;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
/* loaded from: classes4.dex */
public final class RefClass {
    private static final HashMap<Class<?>, Constructor<?>> REF_TYPES;
    private static final String TAG = "RefClass";

    static {
        HashMap<Class<?>, Constructor<?>> hashMap = new HashMap<>();
        REF_TYPES = hashMap;
        try {
            hashMap.put(RefObject.class, RefObject.class.getConstructor(Class.class, Field.class));
            hashMap.put(RefMethod.class, RefMethod.class.getConstructor(Class.class, Field.class));
            hashMap.put(RefInt.class, RefInt.class.getConstructor(Class.class, Field.class));
            hashMap.put(RefLong.class, RefLong.class.getConstructor(Class.class, Field.class));
            hashMap.put(RefFloat.class, RefFloat.class.getConstructor(Class.class, Field.class));
            hashMap.put(RefDouble.class, RefDouble.class.getConstructor(Class.class, Field.class));
            hashMap.put(RefBoolean.class, RefBoolean.class.getConstructor(Class.class, Field.class));
            hashMap.put(RefStaticObject.class, RefStaticObject.class.getConstructor(Class.class, Field.class));
            hashMap.put(RefStaticInt.class, RefStaticInt.class.getConstructor(Class.class, Field.class));
            hashMap.put(RefStaticMethod.class, RefStaticMethod.class.getConstructor(Class.class, Field.class));
            hashMap.put(RefConstructor.class, RefConstructor.class.getConstructor(Class.class, Field.class));
        } catch (Exception e) {
            Log.e(TAG, e.toString(), e);
        }
    }

    public static Class<?> load(Class<?> mappingClass, String className) {
        try {
            ClassLoader classLoader = mappingClass.getClassLoader();
            return load(mappingClass, Class.forName(className, false, classLoader));
        } catch (Exception e) {
            Log.e(TAG, mappingClass.getName() + ".load", e);
            return null;
        }
    }

    public static Class<?> load(Class<?> mappingClass, Class<?> realClass) {
        Constructor<?> constructor;
        Field[] fields = mappingClass.getDeclaredFields();
        for (Field field : fields) {
            try {
                if (Modifier.isStatic(field.getModifiers()) && (constructor = REF_TYPES.get(field.getType())) != null) {
                    field.setAccessible(true);
                    field.set(null, constructor.newInstance(realClass, field));
                }
            } catch (Exception e) {
                Log.e(TAG, mappingClass.getName() + ".load", e);
            }
        }
        return realClass;
    }
}
