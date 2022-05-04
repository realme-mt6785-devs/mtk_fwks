package com.oplus.internal.reflect;

import com.oplus.internal.reflect.internal.MethodVisitor;
import com.oplus.utils.Alog;
import com.oplus.utils.TypeUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
/* loaded from: classes4.dex */
public final class RefConstructor<T> {
    private static final String TAG = "RefConstructor";
    private final Constructor<?> mConstructor;

    public RefConstructor(Class<?> targetClass, Field mappingField) {
        Class<?>[] parameterTypes = getParameterTypesFrom(mappingField);
        Constructor<?> constructor = getConstructor(targetClass, parameterTypes);
        this.mConstructor = constructor;
        if (constructor != null) {
            constructor.setAccessible(true);
        }
    }

    @Deprecated
    public T newInstance(Object... params) {
        try {
            return newInstanceWithException(params);
        } catch (ReflectiveOperationException e) {
            Alog.e("Failed newInstance", e);
            return null;
        }
    }

    public T newInstanceWithException(Object... params) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        checkExist();
        return (T) TypeUtils.cast(this.mConstructor.newInstance(params));
    }

    private void checkExist() throws IllegalAccessException {
        if (this.mConstructor == null) {
            throw new IllegalAccessException("Target constructor should not null");
        }
    }

    private Constructor<?> getConstructor(Class<?> targetClass, Class<?>[] parameterTypes) {
        try {
            return targetClass.getDeclaredConstructor(parameterTypes);
        } catch (NoSuchMethodException e) {
            Alog.e("Failed get constructor, targetClass=" + targetClass, e);
            return null;
        }
    }

    private Class<?>[] getParameterTypesFrom(Field mappingField) {
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
}
