package com.oplus.util;
/* loaded from: classes4.dex */
public final class OplusTypeCastingHelper {
    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T typeCasting(Class<T> type, Object object) {
        if (object == 0 || !type.isInstance(object)) {
            return null;
        }
        return object;
    }
}
