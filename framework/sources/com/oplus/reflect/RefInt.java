package com.oplus.reflect;

import android.util.Log;
import java.lang.reflect.Field;
/* loaded from: classes4.dex */
public class RefInt {
    private static final String TAG = "RefInt";
    private Field field;

    public RefInt(Class cls, Field field) throws NoSuchFieldException {
        Field declaredField = cls.getDeclaredField(field.getName());
        this.field = declaredField;
        declaredField.setAccessible(true);
    }

    public int get(Object object) {
        try {
            return this.field.getInt(object);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return 0;
        }
    }

    public void set(Object obj, int intValue) {
        try {
            this.field.setInt(obj, intValue);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }
}
