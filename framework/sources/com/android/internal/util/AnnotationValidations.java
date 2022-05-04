package com.android.internal.util;

import android.annotation.AppIdInt;
import android.annotation.ColorInt;
import android.annotation.FloatRange;
import android.annotation.IntRange;
import android.annotation.NonNull;
import android.annotation.Size;
import android.annotation.UserIdInt;
import android.app.slice.Slice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import java.lang.annotation.Annotation;
/* loaded from: classes4.dex */
public class AnnotationValidations {
    private AnnotationValidations() {
    }

    public static void validate(Class<UserIdInt> annotation, UserIdInt ignored, int value) {
        if ((value != -10000 && value < -3) || value > 21474) {
            invalid(annotation, Integer.valueOf(value));
        }
    }

    public static void validate(Class<AppIdInt> annotation, AppIdInt ignored, int value) {
        if (value / UserHandle.PER_USER_RANGE != 0 || value < 0) {
            invalid(annotation, Integer.valueOf(value));
        }
    }

    public static void validate(Class<IntRange> annotation, IntRange ignored, int value, String paramName1, long param1, String paramName2, long param2) {
        validate(annotation, ignored, value, paramName1, param1);
        validate(annotation, ignored, value, paramName2, param2);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static void validate(Class<IntRange> annotation, IntRange ignored, int value, String paramName, long param) {
        char c;
        switch (paramName.hashCode()) {
            case 3707:
                if (paramName.equals("to")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 3151786:
                if (paramName.equals("from")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                if (value < param) {
                    invalid(annotation, Integer.valueOf(value), paramName, Long.valueOf(param));
                    return;
                }
                return;
            case 1:
                if (value > param) {
                    invalid(annotation, Integer.valueOf(value), paramName, Long.valueOf(param));
                    return;
                }
                return;
            default:
                return;
        }
    }

    public static void validate(Class<IntRange> annotation, IntRange ignored, long value, String paramName1, long param1, String paramName2, long param2) {
        validate(annotation, ignored, value, paramName1, param1);
        validate(annotation, ignored, value, paramName2, param2);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static void validate(Class<IntRange> annotation, IntRange ignored, long value, String paramName, long param) {
        char c;
        switch (paramName.hashCode()) {
            case 3707:
                if (paramName.equals("to")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 3151786:
                if (paramName.equals("from")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                if (value < param) {
                    invalid(annotation, Long.valueOf(value), paramName, Long.valueOf(param));
                    return;
                }
                return;
            case 1:
                if (value > param) {
                    invalid(annotation, Long.valueOf(value), paramName, Long.valueOf(param));
                    return;
                }
                return;
            default:
                return;
        }
    }

    public static void validate(Class<FloatRange> annotation, FloatRange ignored, float value, String paramName1, float param1, String paramName2, float param2) {
        validate(annotation, ignored, value, paramName1, param1);
        validate(annotation, ignored, value, paramName2, param2);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static void validate(Class<FloatRange> annotation, FloatRange ignored, float value, String paramName, float param) {
        char c;
        switch (paramName.hashCode()) {
            case 3707:
                if (paramName.equals("to")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 3151786:
                if (paramName.equals("from")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                if (value < param) {
                    invalid(annotation, Float.valueOf(value), paramName, Float.valueOf(param));
                    return;
                }
                return;
            case 1:
                if (value > param) {
                    invalid(annotation, Float.valueOf(value), paramName, Float.valueOf(param));
                    return;
                }
                return;
            default:
                return;
        }
    }

    public static void validate(Class<NonNull> annotation, NonNull ignored, Object value) {
        if (value == null) {
            throw new NullPointerException();
        }
    }

    public static void validate(Class<Size> annotation, Size ignored, int value, String paramName1, int param1, String paramName2, int param2) {
        validate(annotation, ignored, value, paramName1, param1);
        validate(annotation, ignored, value, paramName2, param2);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static void validate(Class<Size> annotation, Size ignored, int value, String paramName, int param) {
        char c;
        switch (paramName.hashCode()) {
            case 107876:
                if (paramName.equals(Slice.SUBTYPE_MAX)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 108114:
                if (paramName.equals("min")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 111972721:
                if (paramName.equals("value")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 653829648:
                if (paramName.equals("multiple")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                if (param != -1 && value != param) {
                    invalid(annotation, Integer.valueOf(value), paramName, Integer.valueOf(param));
                    return;
                }
                return;
            case 1:
                if (value < param) {
                    invalid(annotation, Integer.valueOf(value), paramName, Integer.valueOf(param));
                    return;
                }
                return;
            case 2:
                if (value > param) {
                    invalid(annotation, Integer.valueOf(value), paramName, Integer.valueOf(param));
                    return;
                }
                return;
            case 3:
                if (value % param != 0) {
                    invalid(annotation, Integer.valueOf(value), paramName, Integer.valueOf(param));
                    return;
                }
                return;
            default:
                return;
        }
    }

    public static void validate(Class<PackageManager.PermissionResult> annotation, PackageManager.PermissionResult ignored, int value) {
        validateIntEnum(annotation, value, 0);
    }

    public static void validate(Class<PackageManager.PackageInfoFlags> annotation, PackageManager.PackageInfoFlags ignored, int value) {
        validateIntFlags(annotation, value, BitUtils.flagsUpTo(536870912));
    }

    public static void validate(Class<Intent.Flags> annotation, Intent.Flags ignored, int value) {
        validateIntFlags(annotation, value, BitUtils.flagsUpTo(Integer.MIN_VALUE));
    }

    @Deprecated
    public static void validate(Class<? extends Annotation> annotation, Annotation ignored, Object value, Object... params) {
    }

    @Deprecated
    public static void validate(Class<? extends Annotation> annotation, Annotation ignored, Object value) {
    }

    @Deprecated
    public static void validate(Class<? extends Annotation> annotation, Annotation ignored, int value, Object... params) {
    }

    public static void validate(Class<? extends Annotation> annotation, Annotation ignored, int value) {
        if ((("android.annotation".equals(annotation.getPackageName()) && annotation.getSimpleName().endsWith("Res")) || ColorInt.class.equals(annotation)) && value < 0) {
            invalid(annotation, Integer.valueOf(value));
        }
    }

    public static void validate(Class<? extends Annotation> annotation, Annotation ignored, long value) {
        if ("android.annotation".equals(annotation.getPackageName()) && annotation.getSimpleName().endsWith("Long") && value < 0) {
            invalid(annotation, Long.valueOf(value));
        }
    }

    private static void validateIntEnum(Class<? extends Annotation> annotation, int value, int lastValid) {
        if (value > lastValid) {
            invalid(annotation, Integer.valueOf(value));
        }
    }

    private static void validateIntFlags(Class<? extends Annotation> annotation, int value, int validBits) {
        if ((validBits & value) != validBits) {
            invalid(annotation, "0x" + Integer.toHexString(value));
        }
    }

    private static void invalid(Class<? extends Annotation> annotation, Object value) {
        invalid("@" + annotation.getSimpleName(), value);
    }

    private static void invalid(Class<? extends Annotation> annotation, Object value, String paramName, Object param) {
        String paramPrefix;
        if ("value".equals(paramName)) {
            paramPrefix = "";
        } else {
            paramPrefix = paramName + " = ";
        }
        invalid("@" + annotation.getSimpleName() + "(" + paramPrefix + param + ")", value);
    }

    private static void invalid(String valueKind, Object value) {
        throw new IllegalStateException("Invalid " + valueKind + ": " + value);
    }
}
