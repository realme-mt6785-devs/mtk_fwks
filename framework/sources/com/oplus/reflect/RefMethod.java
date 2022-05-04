package com.oplus.reflect;

import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes4.dex */
public class RefMethod<T> {
    private static final String TAG = "RefMethod";
    private Method method;

    public RefMethod(Class<?> cls, Field field) throws NoSuchMethodException {
        if (field.isAnnotationPresent(MethodParams.class)) {
            Method declaredMethod = cls.getDeclaredMethod(field.getName(), ((MethodParams) field.getAnnotation(MethodParams.class)).value());
            this.method = declaredMethod;
            declaredMethod.setAccessible(true);
        } else if (!field.isAnnotationPresent(MethodReflectParams.class)) {
            Method[] declaredMethods = cls.getDeclaredMethods();
            int length = declaredMethods.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Method method = declaredMethods[i];
                if (method.getName().equals(field.getName())) {
                    this.method = method;
                    method.setAccessible(true);
                    break;
                }
                i++;
            }
        } else {
            String[] typeNames = ((MethodReflectParams) field.getAnnotation(MethodReflectParams.class)).value();
            Class<?>[] types = new Class[typeNames.length];
            for (int i2 = 0; i2 < typeNames.length; i2++) {
                Class<?> type = RefStaticMethod.getProtoType(typeNames[i2]);
                if (type == null) {
                    try {
                        type = Class.forName(typeNames[i2]);
                    } catch (ClassNotFoundException e) {
                        Log.e(TAG, e.getMessage(), e);
                    }
                }
                types[i2] = type;
            }
            Method declaredMethod2 = cls.getDeclaredMethod(field.getName(), types);
            this.method = declaredMethod2;
            declaredMethod2.setAccessible(true);
        }
        if (this.method == null) {
            throw new NoSuchMethodException(field.getName());
        }
    }

    public T call(Object receiver, Object... args) {
        try {
            return (T) this.method.invoke(receiver, args);
        } catch (InvocationTargetException e) {
            if (e.getCause() != null) {
                Log.e(TAG, e.getCause().getMessage(), e);
                return null;
            }
            Log.e(TAG, e.getMessage(), e);
            return null;
        } catch (Throwable e2) {
            Log.e(TAG, e2.getMessage(), e2);
            return null;
        }
    }

    public T callWithException(Object receiver, Object... args) throws Throwable {
        try {
            return (T) this.method.invoke(receiver, args);
        } catch (InvocationTargetException e) {
            if (e.getCause() != null) {
                throw e.getCause();
            }
            throw e;
        }
    }

    public Class<?>[] paramList() {
        return this.method.getParameterTypes();
    }
}
