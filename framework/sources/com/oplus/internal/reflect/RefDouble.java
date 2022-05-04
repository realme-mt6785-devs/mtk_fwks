package com.oplus.internal.reflect;

import com.oplus.internal.reflect.internal.BaseField;
import com.oplus.utils.Alog;
import java.lang.reflect.Field;
/* loaded from: classes4.dex */
public final class RefDouble extends BaseField {
    public RefDouble(Class<?> cls, Field field) {
        super(cls, field);
    }

    @Deprecated
    public double get(Object target) {
        try {
            return getDouble(target);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return Double.NaN;
        }
    }

    @Deprecated
    public void set(Object target, double value) {
        try {
            setDouble(target, value);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
        }
    }

    public double getWithDefault(Object target, double defaultValue) {
        try {
            return getDouble(target);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return defaultValue;
        }
    }

    public double getWithException(Object target) throws IllegalAccessException {
        return getDouble(target);
    }

    public void setWithException(Object target, double value) throws IllegalAccessException {
        setDouble(target, value);
    }
}
