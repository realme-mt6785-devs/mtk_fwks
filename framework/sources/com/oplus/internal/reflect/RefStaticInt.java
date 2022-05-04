package com.oplus.internal.reflect;

import com.oplus.internal.reflect.internal.BaseField;
import com.oplus.utils.Alog;
import java.lang.reflect.Field;
/* loaded from: classes4.dex */
public final class RefStaticInt extends BaseField {
    public RefStaticInt(Class<?> cls, Field field) {
        super(cls, field);
    }

    @Deprecated
    public int get() {
        try {
            return getInt(null);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return 0;
        }
    }

    @Deprecated
    public void set(int value) {
        try {
            setInt(null, value);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
        }
    }

    public int getWithDefault(int defaultValue) {
        try {
            return getInt(null);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return defaultValue;
        }
    }

    public int getWithException() throws IllegalAccessException {
        return getInt(null);
    }

    public void setWithException(int value) throws IllegalAccessException {
        setInt(null, value);
    }
}
