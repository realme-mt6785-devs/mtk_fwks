package com.oplus.reflect;

import android.util.Log;
import java.lang.reflect.Field;
/* loaded from: classes4.dex */
public class RefBoolean {
    private static final String TAG = "RefBoolean";
    private Field field;

    public RefBoolean(Class<?> cls, Field field) throws NoSuchFieldException {
        Field declaredField = cls.getDeclaredField(field.getName());
        this.field = declaredField;
        declaredField.setAccessible(true);
    }

    public boolean get(Object object) {
        try {
            return this.field.getBoolean(object);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return false;
        }
    }

    public void set(Object obj, boolean value) {
        try {
            this.field.setBoolean(obj, value);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }
}
