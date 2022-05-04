package com.oplus.reflect;

import android.util.Log;
import java.lang.reflect.Field;
/* loaded from: classes4.dex */
public class RefObject<T> {
    private static final String TAG = "RefObject";
    private Field field;

    public RefObject(Class<?> cls, Field field) throws NoSuchFieldException {
        Field declaredField = cls.getDeclaredField(field.getName());
        this.field = declaredField;
        declaredField.setAccessible(true);
    }

    public T get(Object object) {
        try {
            return (T) this.field.get(object);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return null;
        }
    }

    public void set(Object obj, T value) {
        try {
            this.field.set(obj, value);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }
}
