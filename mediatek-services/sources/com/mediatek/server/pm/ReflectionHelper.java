package com.mediatek.server.pm;

import android.util.Slog;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* compiled from: PmsExtImpl.java */
/* loaded from: classes.dex */
class ReflectionHelper {
    ReflectionHelper() {
    }

    public static Class getNonPublicInnerClass(Class targetCls, String innerClsName) {
        Class[] innerClasses = targetCls.getDeclaredClasses();
        for (Class cls : innerClasses) {
            if (cls.toString().contains(innerClsName)) {
                return cls;
            }
        }
        return null;
    }

    public static Field getNonPublicField(Class cls, String fieldName) {
        try {
            Field field = cls.getDeclaredField(fieldName);
            return field;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getObjectValue(Field field, Object targetObject) {
        field.setAccessible(true);
        try {
            Object result = field.get(targetObject);
            return result;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static boolean getBooleanValue(Field field, Object tarObject) {
        field.setAccessible(true);
        try {
            boolean result = field.getBoolean(tarObject);
            return result;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static int getIntValue(Field field, Object tarObject) {
        field.setAccessible(true);
        try {
            int result = field.getInt(tarObject);
            return result;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return 0;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static Object getObjectValue(Class cls, String fieldName, Object targetObject) {
        Field field = getNonPublicField(cls, fieldName);
        return getObjectValue(field, targetObject);
    }

    public static boolean getBooleanValue(Class cls, String fieldName, Object tarObject) {
        Field field = getNonPublicField(cls, fieldName);
        return getBooleanValue(field, tarObject);
    }

    public static int getIntValue(Class cls, String fieldName) {
        Field field = getNonPublicField(cls, fieldName);
        return getIntValue(field, cls);
    }

    public static Method getMethod(Class cls, String methodName, Class... params) {
        Method retMethod = null;
        try {
            retMethod = cls.getDeclaredMethod(methodName, params);
            if (retMethod != null) {
                retMethod.setAccessible(true);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return retMethod;
    }

    public static Object callMethod(Method method, Object object, Object... params) {
        Object ret = null;
        if (method == null) {
            return null;
        }
        try {
            ret = method.invoke(object, params);
            Slog.d("PmsExtImpl", "callMethod:" + method.getName() + " ret=" + ret);
            return ret;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return ret;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return ret;
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
            return ret;
        }
    }

    public static void setFieldValue(Class cls, Object obj, String fieldName, Object value) {
        try {
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (NoSuchFieldException e3) {
            e3.printStackTrace();
        }
    }

    public static void setFieldValue(Object obj, String fieldName, Object value) {
        setFieldValue(obj.getClass(), obj, fieldName, value);
    }
}
