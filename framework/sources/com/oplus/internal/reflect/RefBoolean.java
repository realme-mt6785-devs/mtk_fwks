package com.oplus.internal.reflect;

import com.oplus.internal.reflect.internal.BaseField;
import com.oplus.utils.Alog;
import java.lang.reflect.Field;
/* loaded from: classes4.dex */
public final class RefBoolean extends BaseField {
    public RefBoolean(Class<?> cls, Field field) {
        super(cls, field);
    }

    @Deprecated
    public boolean get(Object target) {
        try {
            return getBoolean(target);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return false;
        }
    }

    @Deprecated
    public void set(Object target, boolean value) {
        try {
            setBoolean(target, value);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
        }
    }

    public boolean getWithDefault(Object target, boolean defaultValue) {
        try {
            return getBoolean(target);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return defaultValue;
        }
    }

    public boolean getWithException(Object target) throws IllegalAccessException {
        return getBoolean(target);
    }

    public void setWithException(Object target, boolean value) throws IllegalAccessException {
        setBoolean(target, value);
    }
}
