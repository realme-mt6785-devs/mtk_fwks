package com.oplus.reflect;

import android.util.Log;
import java.lang.reflect.Field;
/* loaded from: classes4.dex */
public class RefFloat {
    private static final String TAG = "RefFloat";
    private Field field;

    public RefFloat(Class cls, Field field) throws NoSuchFieldException {
        Field declaredField = cls.getDeclaredField(field.getName());
        this.field = declaredField;
        declaredField.setAccessible(true);
    }

    public float get(Object object) {
        try {
            return this.field.getFloat(object);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return 0.0f;
        }
    }

    public void set(Object obj, float value) {
        try {
            this.field.setFloat(obj, value);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }
}
