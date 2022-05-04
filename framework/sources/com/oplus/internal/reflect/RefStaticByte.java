package com.oplus.internal.reflect;

import com.oplus.internal.reflect.internal.BaseField;
import com.oplus.utils.Alog;
import java.lang.reflect.Field;
/* loaded from: classes4.dex */
public final class RefStaticByte extends BaseField {
    public RefStaticByte(Class<?> cls, Field field) {
        super(cls, field);
    }

    @Deprecated
    public byte get() {
        try {
            return getByte(null);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return Byte.MIN_VALUE;
        }
    }

    @Deprecated
    public void set(byte value) {
        try {
            setByte(null, value);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
        }
    }

    public byte getWithDefault(byte defaultValue) {
        try {
            return getByte(null);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return defaultValue;
        }
    }

    public byte getWithException() throws IllegalAccessException {
        return getByte(null);
    }

    public void setWithException(byte value) throws IllegalAccessException {
        setByte(null, value);
    }
}
