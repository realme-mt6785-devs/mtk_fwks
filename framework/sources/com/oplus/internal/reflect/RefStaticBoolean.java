package com.oplus.internal.reflect;

import com.oplus.internal.reflect.internal.BaseField;
import com.oplus.utils.Alog;
import java.lang.reflect.Field;
/* loaded from: classes4.dex */
public final class RefStaticBoolean extends BaseField {
    public RefStaticBoolean(Class<?> cls, Field field) {
        super(cls, field);
    }

    @Deprecated
    public boolean get() {
        try {
            return getBoolean(null);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return false;
        }
    }

    @Deprecated
    public void set(boolean value) {
        try {
            setBoolean(null, value);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
        }
    }

    public boolean getWithDefault(boolean defaultValue) {
        try {
            return getBoolean(null);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return defaultValue;
        }
    }

    public boolean getWithException() throws IllegalAccessException {
        return getBoolean(null);
    }

    public void setWithException(boolean value) throws IllegalAccessException {
        setBoolean(null, value);
    }
}
