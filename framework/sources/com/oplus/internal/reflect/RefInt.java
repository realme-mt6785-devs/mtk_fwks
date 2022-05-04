package com.oplus.internal.reflect;

import com.oplus.internal.reflect.internal.BaseField;
import com.oplus.utils.Alog;
import java.lang.reflect.Field;
/* loaded from: classes4.dex */
public final class RefInt extends BaseField {
    public RefInt(Class<?> cls, Field field) {
        super(cls, field);
    }

    @Deprecated
    public int get(Object target) {
        try {
            return getInt(target);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return 0;
        }
    }

    @Deprecated
    public void set(Object target, int value) {
        try {
            setInt(target, value);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
        }
    }

    public int getWithDefault(Object target, int defaultValue) {
        try {
            return getInt(target);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return defaultValue;
        }
    }

    public int getWithException(Object target) throws IllegalAccessException {
        return getInt(target);
    }

    public void setWithException(Object target, int value) throws IllegalAccessException {
        setInt(target, value);
    }
}
