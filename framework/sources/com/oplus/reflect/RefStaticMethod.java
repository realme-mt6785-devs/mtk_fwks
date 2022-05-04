package com.oplus.reflect;

import android.app.slice.SliceItem;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.function.Function;
/* loaded from: classes4.dex */
public class RefStaticMethod<T> {
    private static final String TAG = "RefStaticMethod";
    private Method method;

    public RefStaticMethod(Class<?> cls, Field field) throws NoSuchMethodException {
        if (field.isAnnotationPresent(MethodParams.class)) {
            Class<?>[] types = ((MethodParams) field.getAnnotation(MethodParams.class)).value();
            for (Class<?> cls2 : types) {
            }
            Method declaredMethod = cls.getDeclaredMethod(field.getName(), types);
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
            boolean arrayset = false;
            String[] typeNames = ((MethodReflectParams) field.getAnnotation(MethodReflectParams.class)).value();
            Class<?>[] types2 = new Class[typeNames.length];
            Class<?>[] types22 = new Class[typeNames.length];
            for (int i2 = 0; i2 < typeNames.length; i2++) {
                Class<?> type = getProtoType(typeNames[i2]);
                if (type == null) {
                    try {
                        type = Class.forName(typeNames[i2]);
                    } catch (ClassNotFoundException e) {
                        Log.e(TAG, e.getMessage(), e);
                    }
                }
                types2[i2] = type;
                if ("java.util.HashSet".equals(typeNames[i2])) {
                    arrayset = true;
                    Class<?> type2 = type;
                    try {
                        type2 = Class.forName("android.util.ArraySet");
                    } catch (ClassNotFoundException e2) {
                        Log.e(TAG, e2.getMessage(), e2);
                    }
                    if (type2 != null) {
                        types22[i2] = type2;
                    } else {
                        types22[i2] = type;
                    }
                } else {
                    types22[i2] = type;
                }
            }
            try {
                this.method = cls.getDeclaredMethod(field.getName(), types2);
            } catch (Exception e3) {
                Log.e(TAG, e3.getMessage(), e3);
                if (arrayset) {
                    this.method = cls.getDeclaredMethod(field.getName(), types22);
                }
            }
            this.method.setAccessible(true);
        }
        if (this.method == null) {
            throw new NoSuchMethodException(field.getName());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Class<?> getProtoType(String typeName) {
        if (typeName.equals(SliceItem.FORMAT_INT)) {
            return Integer.TYPE;
        }
        if (typeName.equals("long")) {
            return Long.TYPE;
        }
        if (typeName.equals("boolean")) {
            return Boolean.TYPE;
        }
        if (typeName.equals("byte")) {
            return Byte.TYPE;
        }
        if (typeName.equals("short")) {
            return Short.TYPE;
        }
        if (typeName.equals("char")) {
            return Character.TYPE;
        }
        if (typeName.equals("float")) {
            return Float.TYPE;
        }
        if (typeName.equals("double")) {
            return Double.TYPE;
        }
        if (typeName.equals("void")) {
            return Void.TYPE;
        }
        return null;
    }

    public T call(Object... params) {
        try {
            T obj = (T) this.method.invoke(null, params);
            return obj;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return null;
        }
    }

    public static <T> T callIfPresent(RefStaticMethod<T> method, T def, final Object... params) {
        return (T) Optional.ofNullable(method).map(new Function() { // from class: com.oplus.reflect.RefStaticMethod$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Object call;
                call = ((RefStaticMethod) obj).call(params);
                return call;
            }
        }).orElse(def);
    }

    public T callWithException(Object... params) throws Throwable {
        try {
            return (T) this.method.invoke(null, params);
        } catch (InvocationTargetException e) {
            if (e.getCause() != null) {
                throw e.getCause();
            }
            throw e;
        }
    }
}
