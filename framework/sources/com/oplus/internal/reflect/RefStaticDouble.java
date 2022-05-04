package com.oplus.internal.reflect;

import com.oplus.internal.reflect.internal.BaseField;
import com.oplus.utils.Alog;
import java.lang.reflect.Field;
/* loaded from: classes4.dex */
public final class RefStaticDouble extends BaseField {
    public RefStaticDouble(Class<?> cls, Field field) {
        super(cls, field);
    }

    @Deprecated
    public double get() {
        try {
            return getDouble(null);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return Double.NaN;
        }
    }

    @Deprecated
    public void set(double value) {
        try {
            setDouble(null, value);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
        }
    }

    public double getWithDefault(double defaultValue) {
        try {
            return getDouble(null);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return defaultValue;
        }
    }

    public double getWithException() throws IllegalAccessException {
        return getDouble(null);
    }

    public void setWithException(double value) throws IllegalAccessException {
        setDouble(null, value);
    }
}
