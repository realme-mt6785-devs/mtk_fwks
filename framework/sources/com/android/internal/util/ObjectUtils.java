package com.android.internal.util;

import java.util.Objects;
/* loaded from: classes4.dex */
public class ObjectUtils {
    private ObjectUtils() {
    }

    public static <T> T firstNotNull(T a, T b) {
        if (a != null) {
            return a;
        }
        Objects.requireNonNull(b);
        return b;
    }

    public static <T extends Comparable> int compare(T a, T b) {
        if (a == null) {
            return b != null ? -1 : 0;
        }
        if (b != null) {
            return a.compareTo(b);
        }
        return 1;
    }

    public static <T> T getOrElse(T object, T otherwise) {
        return object != null ? object : otherwise;
    }
}
