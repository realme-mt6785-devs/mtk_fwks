package com.oplus.internal.reflect.internal;

import com.oplus.utils.Alog;
import java.lang.reflect.Field;
/* loaded from: classes4.dex */
public abstract class BaseField {
    private final Field mField;

    public BaseField(Class<?> targetCls, Field field) {
        Field field2 = getField(targetCls, field);
        this.mField = field2;
        if (field2 != null) {
            field2.setAccessible(true);
        }
    }

    private Field getField(Class<?> targetCls, Field targetField) {
        try {
            Field result = targetCls.getDeclaredField(targetField.getName());
            result.setAccessible(true);
            return result;
        } catch (Exception e) {
            Alog.e(e.getMessage(), e);
            return null;
        }
    }

    private void checkExist() throws IllegalAccessException {
        if (this.mField == null) {
            throw new IllegalAccessException("Target field should not null");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean getBoolean(Object target) throws IllegalAccessException {
        checkExist();
        return this.mField.getBoolean(target);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setBoolean(Object target, boolean value) throws IllegalAccessException {
        checkExist();
        this.mField.setBoolean(target, value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte getByte(Object target) throws IllegalAccessException {
        checkExist();
        return this.mField.getByte(target);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setByte(Object target, byte value) throws IllegalAccessException {
        checkExist();
        this.mField.setByte(target, value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public char getChar(Object target) throws IllegalAccessException {
        checkExist();
        return this.mField.getChar(target);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setChar(Object target, char value) throws IllegalAccessException {
        checkExist();
        this.mField.setChar(target, value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public double getDouble(Object target) throws IllegalAccessException {
        checkExist();
        return this.mField.getDouble(target);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setDouble(Object target, double value) throws IllegalAccessException {
        checkExist();
        this.mField.setDouble(target, value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public float getFloat(Object target) throws IllegalAccessException {
        checkExist();
        return this.mField.getFloat(target);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setFloat(Object target, float value) throws IllegalAccessException {
        checkExist();
        this.mField.setFloat(target, value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getInt(Object target) throws IllegalAccessException {
        checkExist();
        return this.mField.getInt(target);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setInt(Object target, int value) throws IllegalAccessException {
        checkExist();
        this.mField.setInt(target, value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long getLong(Object target) throws IllegalAccessException {
        checkExist();
        return this.mField.getLong(target);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setLong(Object target, long value) throws IllegalAccessException {
        checkExist();
        this.mField.setLong(target, value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public short getShort(Object target) throws IllegalAccessException {
        checkExist();
        return this.mField.getShort(target);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setShort(Object target, short value) throws IllegalAccessException {
        checkExist();
        this.mField.setShort(target, value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object getObject(Object target) throws IllegalAccessException {
        checkExist();
        return this.mField.get(target);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setObject(Object target, Object value) throws IllegalAccessException {
        checkExist();
        this.mField.set(target, value);
    }
}
