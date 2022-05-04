package com.mediatek.server.audio;

import android.util.Log;
import dalvik.system.PathClassLoader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ReflectionHelper {
    private static final String TAG = "AS." + ReflectionHelper.class.getSimpleName();
    private static Result sFailure = new Result(false, null);

    ReflectionHelper() {
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

    public static Method getMethod(Object obj, String methodName, Class... params) {
        boolean success = false;
        Method retMethod = null;
        if (obj == null || methodName == null) {
            String str = TAG;
            Log.e(str, "getMethod, failed  Obj=" + obj + ", methodName=" + methodName);
            return null;
        }
        try {
            retMethod = obj.getClass().getDeclaredMethod(methodName, params);
            if (retMethod != null) {
                retMethod.setAccessible(true);
            }
            success = true;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if (!success) {
            String str2 = TAG;
            Log.e(str2, "getMethod, failed  Obj=" + obj + ", methodName=" + methodName);
        }
        return retMethod;
    }

    public static Field getField(Object obj, String fieldName, Class... params) {
        boolean success = false;
        Field retField = null;
        if (obj == null || fieldName == null) {
            String str = TAG;
            Log.e(str, "getField, failed  obj=" + obj + ", methodName=" + fieldName);
            return null;
        }
        try {
            retField = obj.getClass().getDeclaredField(fieldName);
            if (retField != null) {
                retField.setAccessible(true);
            }
            success = true;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        if (!success) {
            String str2 = TAG;
            Log.e(str2, "getField, failed Obj=" + obj + ", fieldName=" + fieldName);
        }
        return retField;
    }

    public static Object getFieldObject(Object obj, String fieldName, Class... params) {
        Object retFieldObj = null;
        boolean success = false;
        if (obj == null || fieldName == null) {
            String str = TAG;
            Log.e(str, "getField, failed  Obj=" + obj);
            return null;
        }
        try {
            Field retField = obj.getClass().getDeclaredField(fieldName);
            if (retField != null) {
                retField.setAccessible(true);
            }
            retFieldObj = retField.get(obj);
            success = true;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        }
        if (!success) {
            String str2 = TAG;
            Log.e(str2, "getFieldObject, failed Obj=" + obj + "fieldName=" + fieldName);
        }
        return retFieldObj;
    }

    public static void replaceStaticField(String className, String fieldName, Object newInstance) {
        boolean success = false;
        try {
            Class clazz = Class.forName(className);
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(null, newInstance);
            success = true;
        } catch (ClassNotFoundException e) {
        } catch (IllegalAccessException e2) {
        } catch (NoSuchFieldException e3) {
        }
        if (!success) {
            String str = TAG;
            Log.e(str, "Failed to replace static field for className=" + className + "fieldName=" + fieldName + ",newInstance=" + newInstance);
        }
    }

    public static Object callMethod(Method method, Object object, Object... params) {
        Object ret = null;
        boolean success = false;
        if (method == null || object == null) {
            String str = TAG;
            Log.e(str, "getField, failed  method=" + method + "object=" + object);
            return null;
        }
        if (method != null) {
            try {
                ret = method.invoke(object, params);
                success = true;
            } catch (IllegalAccessException e) {
            } catch (IllegalArgumentException e2) {
            } catch (InvocationTargetException e3) {
            }
            if (!success) {
                String str2 = TAG;
                Log.d(str2, "Failed to callMethod:" + method.getName() + " ret=" + ret);
            }
        }
        return ret;
    }

    public static void callSetBoolean(Field fieldName, Object object, Boolean status) {
        boolean success = false;
        if (fieldName == null || object == null) {
            String str = TAG;
            Log.e(str, "callSetBoolean, failed  fieldName=" + fieldName + "object=" + object);
        } else if (fieldName != null) {
            try {
                if (status.booleanValue()) {
                    fieldName.setBoolean(object, new Boolean(true).booleanValue());
                } else {
                    fieldName.setBoolean(object, new Boolean(false).booleanValue());
                }
                success = true;
            } catch (IllegalAccessException e) {
            } catch (IllegalArgumentException e2) {
            }
            if (!success) {
                String str2 = TAG;
                Log.d(str2, "Failed to callSetBoolean:" + fieldName);
            }
        }
    }

    public static Result callMethod(String classPackage, String className, String methodName, Object... params) throws InvocationTargetException {
        Class clazz;
        try {
            if (classPackage != null) {
                PathClassLoader loader = new PathClassLoader(classPackage, ReflectionHelper.class.getClassLoader());
                clazz = Class.forName(className, false, loader);
            } else {
                clazz = Class.forName(className);
            }
            Class[] paramTypes = new Class[params.length];
            for (int i = 0; i < params.length; i++) {
                paramTypes[i] = params[i].getClass();
            }
            Method method = clazz.getDeclaredMethod(methodName, paramTypes);
            method.setAccessible(true);
            Object ret = method.invoke(null, params);
            return new Result(true, ret);
        } catch (ClassNotFoundException e) {
            String str = TAG;
            Log.e(str, "[callMethod]Failed to call className=" + className + ", methodName=" + methodName);
            return sFailure;
        } catch (IllegalAccessException e2) {
            String str2 = TAG;
            Log.e(str2, "[callMethod]Failed to call className=" + className + ", methodName=" + methodName);
            return sFailure;
        } catch (NoSuchMethodException e3) {
            String str22 = TAG;
            Log.e(str22, "[callMethod]Failed to call className=" + className + ", methodName=" + methodName);
            return sFailure;
        } catch (InvocationTargetException e4) {
            String str3 = TAG;
            Log.e(str3, "[callMethod]Exception occurred to the invoke of throw it className=" + className + "methodName" + methodName);
            throw e4;
        }
    }

    public static Result callStaticMethod(String classPackage, String className, String methodName, Object... params) throws InvocationTargetException {
        return callMethod(classPackage, className, methodName, params);
    }

    /* loaded from: classes.dex */
    public static class Result {
        public Object mReturn;
        public boolean mSuccess;

        public Result(boolean success, Object returnValue) {
            this.mSuccess = success;
            this.mReturn = returnValue;
        }
    }

    public static Field getNonPublicField(Class<?> cls, String fieldName) {
        Field field = null;
        try {
            field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return field;
        }
    }

    public static int getDeclaredMethod(Class<?> cls, Object instance, String methodName) {
        try {
            Class<?>[] paraClass = new Class[0];
            Method method = cls.getDeclaredMethod(methodName, paraClass);
            method.setAccessible(true);
            Object[] noObject = new Object[0];
            Object value = method.invoke(instance, noObject);
            return Integer.valueOf(value.toString()).intValue();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return -1;
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return -1;
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
            return -1;
        }
    }
}
