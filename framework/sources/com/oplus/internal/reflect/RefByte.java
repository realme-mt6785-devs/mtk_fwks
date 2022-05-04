package com.oplus.internal.reflect;

import com.oplus.internal.reflect.internal.BaseField;
import com.oplus.utils.Alog;
import java.lang.reflect.Field;
/* loaded from: classes4.dex */
public final class RefByte extends BaseField {
    public RefByte(Class<?> cls, Field field) {
        super(cls, field);
    }

    @Deprecated
    public byte get(Object target) {
        try {
            return getByte(target);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return Byte.MIN_VALUE;
        }
    }

    @Deprecated
    public void set(Object target, byte value) {
        try {
            setByte(target, value);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
        }
    }

    public byte getWithDefault(Object target, byte defaultValue) {
        try {
            return getByte(target);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return defaultValue;
        }
    }

    public byte getWithException(Object target) throws IllegalAccessException {
        return getByte(target);
    }

    public void setWithException(Object target, byte value) throws IllegalAccessException {
        setByte(target, value);
    }
}
