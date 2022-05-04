package com.oplus.internal.reflect;

import com.oplus.internal.reflect.internal.BaseField;
import com.oplus.utils.Alog;
import java.lang.reflect.Field;
/* loaded from: classes4.dex */
public final class RefStaticFloat extends BaseField {
    public RefStaticFloat(Class<?> cls, Field field) {
        super(cls, field);
    }

    @Deprecated
    public float get() {
        try {
            return getFloat(null);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return Float.NaN;
        }
    }

    @Deprecated
    public void set(float value) {
        try {
            setFloat(null, value);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
        }
    }

    public float getWithDefault(float defaultValue) {
        try {
            return getFloat(null);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return defaultValue;
        }
    }

    public float getWithException() throws IllegalAccessException {
        return getFloat(null);
    }

    public void setWithException(float value) throws IllegalAccessException {
        setFloat(null, value);
    }
}
