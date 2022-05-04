package com.oplus.internal.reflect;

import com.oplus.internal.reflect.internal.BaseField;
import com.oplus.utils.Alog;
import java.lang.reflect.Field;
/* loaded from: classes4.dex */
public final class RefChar extends BaseField {
    public RefChar(Class<?> cls, Field field) {
        super(cls, field);
    }

    @Deprecated
    public char get(Object target) {
        try {
            return getChar(target);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return (char) 0;
        }
    }

    @Deprecated
    public void set(Object target, char value) {
        try {
            setChar(target, value);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
        }
    }

    public char getWithDefault(Object target, char defaultValue) {
        try {
            return getChar(target);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return defaultValue;
        }
    }

    public char getWithException(Object target) throws IllegalAccessException {
        return getChar(target);
    }

    public void setWithException(Object target, char value) throws IllegalAccessException {
        setChar(target, value);
    }
}
