package com.oplus.utils;

import android.app.slice.SliceItem;
import java.util.Objects;
/* loaded from: classes4.dex */
public class TypeUtils {
    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T cast(Object obj) {
        return obj;
    }

    public static Class<?> classForName(String className, ClassLoader classLoader) {
        try {
            if (classLoader != null) {
                return Class.forName(className, false, classLoader);
            }
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            Alog.e("Cannot find class " + className, e);
            return null;
        }
    }

    public static Class<?> getPrimitiveClassForName(String name) {
        if (name.equals(SliceItem.FORMAT_INT)) {
            return Integer.TYPE;
        }
        if (name.equals("long")) {
            return Long.TYPE;
        }
        if (name.equals("boolean")) {
            return Boolean.TYPE;
        }
        if (name.equals("byte")) {
            return Byte.TYPE;
        }
        if (name.equals("short")) {
            return Short.TYPE;
        }
        if (name.equals("char")) {
            return Character.TYPE;
        }
        if (name.equals("float")) {
            return Float.TYPE;
        }
        if (name.equals("double")) {
            return Double.TYPE;
        }
        if (name.equals("void")) {
            return Void.TYPE;
        }
        return null;
    }

    public static Class<?> checkMappingClass(Class<?> mappingClass) {
        Objects.requireNonNull(mappingClass, "mappingClass should not be null");
        return mappingClass;
    }
}
