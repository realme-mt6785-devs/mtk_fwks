package com.oplus.reflect;

import android.util.Log;
import java.lang.reflect.Field;
/* loaded from: classes4.dex */
public class RefLong {
    private static final String TAG = "RefLong";
    private Field field;

    public RefLong(Class cls, Field field) throws NoSuchFieldException {
        Field declaredField = cls.getDeclaredField(field.getName());
        this.field = declaredField;
        declaredField.setAccessible(true);
    }

    public long get(Object object) {
        try {
            return this.field.getLong(object);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return 0L;
        }
    }

    public void set(Object obj, long value) {
        try {
            this.field.setLong(obj, value);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }
}
