package com.oplus.internal.reflect;

import com.oplus.internal.reflect.internal.BaseField;
import com.oplus.utils.Alog;
import java.lang.reflect.Field;
/* loaded from: classes4.dex */
public final class RefLong extends BaseField {
    public RefLong(Class<?> cls, Field field) {
        super(cls, field);
    }

    @Deprecated
    public long get(Object target) {
        try {
            return getLong(target);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return Long.MIN_VALUE;
        }
    }

    @Deprecated
    public void set(Object target, long value) {
        try {
            setLong(target, value);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
        }
    }

    public long getWithDefault(Object target, long defaultValue) {
        try {
            return getLong(target);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return defaultValue;
        }
    }

    public long getWithException(Object target) throws IllegalAccessException {
        return getLong(target);
    }

    public void setWithException(Object target, long value) throws IllegalAccessException {
        setLong(target, value);
    }
}
