package com.oplus.reflect;

import android.util.Log;
import java.lang.reflect.Field;
/* loaded from: classes4.dex */
public class RefStaticObject<T> {
    private static final String TAG = "RefStaticObject";
    private Field field;

    public RefStaticObject(Class<?> cls, Field field) throws NoSuchFieldException {
        Field declaredField = cls.getDeclaredField(field.getName());
        this.field = declaredField;
        declaredField.setAccessible(true);
    }

    public Class<?> type() {
        return this.field.getType();
    }

    public T get() {
        try {
            T obj = (T) this.field.get(null);
            return obj;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return null;
        }
    }

    public void set(T obj) {
        try {
            this.field.set(null, obj);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }
}
