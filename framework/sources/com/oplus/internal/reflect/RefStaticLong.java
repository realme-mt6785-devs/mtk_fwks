package com.oplus.internal.reflect;

import com.oplus.internal.reflect.internal.BaseField;
import com.oplus.utils.Alog;
import java.lang.reflect.Field;
/* loaded from: classes4.dex */
public final class RefStaticLong extends BaseField {
    public RefStaticLong(Class<?> cls, Field field) {
        super(cls, field);
    }

    @Deprecated
    public long get() {
        try {
            return getLong(null);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return Long.MIN_VALUE;
        }
    }

    @Deprecated
    public void set(long value) {
        try {
            setLong(null, value);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
        }
    }

    public long getWithDefault(long defaultValue) {
        try {
            return getLong(null);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return defaultValue;
        }
    }

    public long getWithException() throws IllegalAccessException {
        return getLong(null);
    }

    public void setWithException(long value) throws IllegalAccessException {
        setLong(null, value);
    }
}
