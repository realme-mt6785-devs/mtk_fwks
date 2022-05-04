package com.oplus.internal.reflect;

import com.oplus.internal.reflect.internal.BaseField;
import com.oplus.utils.Alog;
import java.lang.reflect.Field;
/* loaded from: classes4.dex */
public final class RefStaticChar extends BaseField {
    public RefStaticChar(Class<?> cls, Field field) {
        super(cls, field);
    }

    @Deprecated
    public char get() {
        try {
            return getChar(null);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return (char) 0;
        }
    }

    @Deprecated
    public void set(char value) {
        try {
            setChar(null, value);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
        }
    }

    public char getWithDefault(char defaultValue) {
        try {
            return getChar(null);
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return defaultValue;
        }
    }

    public char getWithException() throws IllegalAccessException {
        return getChar(null);
    }

    public void setWithException(char value) throws IllegalAccessException {
        setChar(null, value);
    }
}
