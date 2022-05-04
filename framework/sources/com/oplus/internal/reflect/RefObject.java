package com.oplus.internal.reflect;

import com.oplus.internal.reflect.internal.BaseField;
import com.oplus.utils.Alog;
import com.oplus.utils.TypeUtils;
import java.lang.reflect.Field;
/* loaded from: classes4.dex */
public final class RefObject<T> extends BaseField {
    public RefObject(Class<?> cls, Field field) {
        super(cls, field);
    }

    @Deprecated
    public T get(Object target) {
        try {
            return (T) TypeUtils.cast(getObject(target));
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return null;
        }
    }

    @Deprecated
    public void set(Object target, T value) {
        try {
            setObject(target, value);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
        }
    }

    public T getWithDefault(Object target, T defaultValue) {
        try {
            return (T) TypeUtils.cast(getObject(target));
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return defaultValue;
        }
    }

    public T getWithException(Object target) throws IllegalAccessException {
        return (T) TypeUtils.cast(getObject(target));
    }

    public void setWithException(Object target, T value) throws IllegalAccessException {
        setObject(target, value);
    }
}
