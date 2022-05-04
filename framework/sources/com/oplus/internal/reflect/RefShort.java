package com.oplus.internal.reflect;

import com.oplus.internal.reflect.internal.BaseField;
import com.oplus.utils.Alog;
import java.lang.reflect.Field;
/* loaded from: classes4.dex */
public final class RefShort extends BaseField {
    public RefShort(Class<?> cls, Field field) {
        super(cls, field);
    }

    @Deprecated
    public short get(Object target) {
        try {
            return getShort(target);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return Short.MIN_VALUE;
        }
    }

    @Deprecated
    public void set(Object target, short value) {
        try {
            setShort(target, value);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
        }
    }

    public short getWithDefault(Object target, short defaultValue) {
        try {
            return getShort(target);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return defaultValue;
        }
    }

    public short getWithException(Object target) throws IllegalAccessException {
        return getShort(target);
    }

    public void setWithException(Object target, short value) throws IllegalAccessException {
        setShort(target, value);
    }
}
