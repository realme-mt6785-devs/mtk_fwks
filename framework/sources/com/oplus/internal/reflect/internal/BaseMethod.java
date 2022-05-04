package com.oplus.internal.reflect.internal;

import com.oplus.internal.reflect.MethodName;
import com.oplus.internal.reflect.MethodParams;
import com.oplus.internal.reflect.MethodReflectParams;
import com.oplus.internal.reflect.MethodSignature;
import com.oplus.utils.Alog;
import com.oplus.utils.TypeUtils;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
/* loaded from: classes4.dex */
public abstract class BaseMethod {
    private final Method mMethod;

    public BaseMethod(Class<?> targetClass, Field mappingField) {
        Method findTargetMethod = findTargetMethod(targetClass, getMethodNameFrom(mappingField), getMethodParameterTypesFrom(mappingField));
        this.mMethod = findTargetMethod;
        if (findTargetMethod != null) {
            findTargetMethod.setAccessible(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object invoke(Object obj, Object... args) throws InvocationTargetException, IllegalAccessException {
        checkExist();
        return this.mMethod.invoke(obj, args);
    }

    private void checkExist() throws IllegalAccessException {
        if (this.mMethod == null) {
            throw new IllegalAccessException("Target method should not null");
        }
    }

    private String getMethodNameFrom(Field mappingField) {
        String methodName = "";
        if (mappingField.isAnnotationPresent(MethodName.class)) {
            methodName = ((MethodName) mappingField.getAnnotation(MethodName.class)).name();
        } else if (mappingField.isAnnotationPresent(MethodSignature.class)) {
            methodName = ((MethodSignature) mappingField.getAnnotation(MethodSignature.class)).name();
        }
        if ("".equals(methodName)) {
            return mappingField.getName();
        }
        return methodName;
    }

    private Class<?>[] getMethodParameterTypesFrom(Field mappingField) {
        if (mappingField.isAnnotationPresent(MethodName.class)) {
            return ((MethodName) mappingField.getAnnotation(MethodName.class)).params();
        }
        if (mappingField.isAnnotationPresent(MethodSignature.class)) {
            return MethodVisitor.visitParameterTypes((MethodSignature) mappingField.getAnnotation(MethodSignature.class));
        }
        if (mappingField.isAnnotationPresent(MethodParams.class)) {
            return ((MethodParams) mappingField.getAnnotation(MethodParams.class)).value();
        }
        if (mappingField.isAnnotationPresent(MethodReflectParams.class)) {
            return MethodVisitor.visitParameterTypes((MethodReflectParams) mappingField.getAnnotation(MethodReflectParams.class));
        }
        return null;
    }

    private Class<?> getMethodReturnTypeFrom(Field mappingField) {
        Type genericType = mappingField.getGenericType();
        if (!(genericType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
        if (actualTypeArguments.length > 0) {
            return (Class) actualTypeArguments[0];
        }
        return Object.class;
    }

    private Method findTargetMethod(Class<?> targetClass, String name, Class<?>[] parameterTypes) {
        if (parameterTypes == null) {
            return findMethodForName(targetClass, name);
        }
        return getDeclaredMethod(targetClass, name, parameterTypes);
    }

    private Method findMethodForName(Class<?> targetClass, String name) {
        Method[] declaredMethods;
        for (Method method : targetClass.getDeclaredMethods()) {
            if (method.getName().equals(name)) {
                return method;
            }
        }
        return null;
    }

    private Method getDeclaredMethod(Class<?> targetClass, String name, Class<?>[] parameterTypes) {
        try {
            return targetClass.getDeclaredMethod(name, parameterTypes);
        } catch (Exception e) {
            Alog.e("Failed getDeclaredMethod", e);
            try {
                return targetClass.getDeclaredMethod(name, fixParameterTypes(parameterTypes));
            } catch (Exception e2) {
                Alog.e("Failed getDeclaredMethod with fix list types", e2);
                return null;
            }
        }
    }

    private Class<?>[] fixParameterTypes(Class<?>[] parameterTypes) {
        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i] == HashSet.class) {
                parameterTypes[i] = TypeUtils.classForName("android.util.ArraySet", null);
            }
        }
        return parameterTypes;
    }
}
