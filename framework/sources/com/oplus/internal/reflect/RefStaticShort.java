package com.oplus.internal.reflect;

import com.oplus.internal.reflect.internal.BaseField;
import com.oplus.utils.Alog;
import java.lang.reflect.Field;
/* loaded from: classes4.dex */
public final class RefStaticShort extends BaseField {
    public RefStaticShort(Class<?> cls, Field field) {
        super(cls, field);
    }

    @Deprecated
    public short get() {
        try {
            return getShort(null);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return Short.MIN_VALUE;
        }
    }

    @Deprecated
    public void set(short value) {
        try {
            setShort(null, value);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
        }
    }

    public short getWithDefault(short defaultValue) {
        try {
            return getShort(null);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return defaultValue;
        }
    }

    public short getWithException() throws IllegalAccessException {
        return getShort(null);
    }

    public void setWithException(short value) throws IllegalAccessException {
        setShort(null, value);
    }
}
