package com.oplus.internal.reflect;

import com.oplus.internal.reflect.internal.BaseField;
import com.oplus.utils.Alog;
import java.lang.reflect.Field;
/* loaded from: classes4.dex */
public final class RefFloat extends BaseField {
    public RefFloat(Class<?> cls, Field field) {
        super(cls, field);
    }

    @Deprecated
    public float get(Object target) {
        try {
            return getFloat(target);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return Float.NaN;
        }
    }

    @Deprecated
    public void set(Object target, float value) {
        try {
            setFloat(target, value);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
        }
    }

    public float getWithDefault(Object target, float defaultValue) {
        try {
            return getFloat(target);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return defaultValue;
        }
    }

    public float getWithException(Object target) throws IllegalAccessException {
        return getFloat(target);
    }

    public void setWithException(Object target, float value) throws IllegalAccessException {
        setFloat(target, value);
    }
}
