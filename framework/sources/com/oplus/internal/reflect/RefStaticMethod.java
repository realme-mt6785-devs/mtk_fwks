package com.oplus.internal.reflect;

import com.oplus.internal.reflect.internal.BaseMethod;
import com.oplus.utils.Alog;
import com.oplus.utils.TypeUtils;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
/* loaded from: classes4.dex */
public class RefStaticMethod<T> extends BaseMethod {
    private final String mappingFieldName;

    public RefStaticMethod(Class<?> targetClass, Field mappingField) {
        super(targetClass, mappingField);
        this.mappingFieldName = mappingField.getName();
    }

    @Deprecated
    public T call(Object... args) {
        try {
            return callWithException(args, new Object[0]);
        } catch (Throwable throwable) {
            Alog.e("RefStaticMethod call failed for mappingFieldName=" + this.mappingFieldName, throwable);
            return null;
        }
    }

    public T callWithDefault(T defaultValue, Object... args) {
        try {
            return callWithException(null, args);
        } catch (Throwable e) {
            Alog.e(e.getMessage());
            return defaultValue;
        }
    }

    public T callWithException(Object object, Object... args) throws Throwable {
        try {
            return (T) TypeUtils.cast(invoke(null, args));
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            if (e.getCause() != null) {
                throw e.getCause();
            }
            throw e;
        }
    }
}
