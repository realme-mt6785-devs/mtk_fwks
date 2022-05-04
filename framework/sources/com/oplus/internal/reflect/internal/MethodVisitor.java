package com.oplus.internal.reflect.internal;

import com.oplus.internal.reflect.MethodReflectParams;
import com.oplus.internal.reflect.MethodSignature;
import com.oplus.utils.Alog;
import com.oplus.utils.TypeUtils;
/* loaded from: classes4.dex */
public class MethodVisitor {
    public static Class<?>[] visitParameterTypes(MethodReflectParams annotation) {
        return visitParameterTypesInner(annotation.value());
    }

    public static Class<?>[] visitParameterTypes(MethodSignature annotation) {
        return visitParameterTypesInner(annotation.params());
    }

    private static Class<?>[] visitParameterTypesInner(String[] parameterTypeNames) {
        Class<?>[] parameterTypes = new Class[parameterTypeNames.length];
        for (int i = 0; i < parameterTypeNames.length; i++) {
            String parameterTypeName = parameterTypeNames[i];
            parameterTypes[i] = TypeUtils.getPrimitiveClassForName(parameterTypeName);
            if (parameterTypes[i] == null) {
                parameterTypes[i] = TypeUtils.classForName(parameterTypeName, null);
            }
            if (parameterTypes[i] == null) {
                Alog.d("parameter type should not null");
                return null;
            }
        }
        return parameterTypes;
    }
}
