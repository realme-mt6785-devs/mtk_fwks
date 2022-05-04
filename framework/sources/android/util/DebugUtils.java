package android.util;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;
/* loaded from: classes3.dex */
public class DebugUtils {
    public static boolean isObjectSelected(Object object) {
        Method declaredMethod;
        boolean match = false;
        String s = System.getenv("ANDROID_OBJECT_FILTER");
        if (s != null && s.length() > 0) {
            String[] selectors = s.split("@");
            if (object.getClass().getSimpleName().matches(selectors[0])) {
                for (int i = 1; i < selectors.length; i++) {
                    String[] pair = selectors[i].split("=");
                    Class<?> klass = object.getClass();
                    Class<?> parent = klass;
                    do {
                        try {
                            declaredMethod = parent.getDeclaredMethod("get" + pair[0].substring(0, 1).toUpperCase(Locale.ROOT) + pair[0].substring(1), null);
                            Class<?> superclass = klass.getSuperclass();
                            parent = superclass;
                            if (superclass == null) {
                                break;
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e2) {
                            e2.printStackTrace();
                        } catch (InvocationTargetException e3) {
                            e3.printStackTrace();
                        }
                    } while (declaredMethod == null);
                    if (declaredMethod != null) {
                        Object value = declaredMethod.invoke(object, null);
                        match |= (value != null ? value.toString() : "null").matches(pair[1]);
                    }
                }
            }
        }
        return match;
    }

    public static void buildShortClassTag(Object cls, StringBuilder out) {
        int end;
        if (cls == null) {
            out.append("null");
            return;
        }
        String simpleName = cls.getClass().getSimpleName();
        if ((simpleName == null || simpleName.isEmpty()) && (end = (simpleName = cls.getClass().getName()).lastIndexOf(46)) > 0) {
            simpleName = simpleName.substring(end + 1);
        }
        out.append(simpleName);
        out.append('{');
        out.append(Integer.toHexString(System.identityHashCode(cls)));
    }

    public static void printSizeValue(PrintWriter pw, long number) {
        String value;
        float result = (float) number;
        String suffix = "";
        if (result > 900.0f) {
            suffix = "KB";
            result /= 1024.0f;
        }
        if (result > 900.0f) {
            suffix = "MB";
            result /= 1024.0f;
        }
        if (result > 900.0f) {
            suffix = "GB";
            result /= 1024.0f;
        }
        if (result > 900.0f) {
            suffix = "TB";
            result /= 1024.0f;
        }
        if (result > 900.0f) {
            suffix = "PB";
            result /= 1024.0f;
        }
        if (result < 1.0f) {
            value = String.format("%.2f", Float.valueOf(result));
        } else if (result < 10.0f) {
            value = String.format("%.1f", Float.valueOf(result));
        } else {
            value = result < 100.0f ? String.format("%.0f", Float.valueOf(result)) : String.format("%.0f", Float.valueOf(result));
        }
        pw.print(value);
        pw.print(suffix);
    }

    public static String sizeValueToString(long number, StringBuilder outBuilder) {
        String value;
        if (outBuilder == null) {
            outBuilder = new StringBuilder(32);
        }
        float result = (float) number;
        String suffix = "";
        if (result > 900.0f) {
            suffix = "KB";
            result /= 1024.0f;
        }
        if (result > 900.0f) {
            suffix = "MB";
            result /= 1024.0f;
        }
        if (result > 900.0f) {
            suffix = "GB";
            result /= 1024.0f;
        }
        if (result > 900.0f) {
            suffix = "TB";
            result /= 1024.0f;
        }
        if (result > 900.0f) {
            suffix = "PB";
            result /= 1024.0f;
        }
        if (result < 1.0f) {
            value = String.format("%.2f", Float.valueOf(result));
        } else if (result < 10.0f) {
            value = String.format("%.1f", Float.valueOf(result));
        } else {
            value = result < 100.0f ? String.format("%.0f", Float.valueOf(result)) : String.format("%.0f", Float.valueOf(result));
        }
        outBuilder.append(value);
        outBuilder.append(suffix);
        return outBuilder.toString();
    }

    public static String valueToString(Class<?> clazz, String prefix, int value) {
        Field[] declaredFields = clazz.getDeclaredFields();
        int length = declaredFields.length;
        for (int i = 0; i < length; i++) {
            Field field = declaredFields[i];
            int modifiers = field.getModifiers();
            if (Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers) && field.getType().equals(Integer.TYPE) && field.getName().startsWith(prefix)) {
                try {
                    if (value == field.getInt(null)) {
                        return constNameWithoutPrefix(prefix, field);
                    }
                    continue;
                } catch (IllegalAccessException e) {
                }
            }
        }
        return Integer.toString(value);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v7, types: [int] */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String flagsToString(java.lang.Class<?> r10, java.lang.String r11, int r12) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            r2 = 1
            if (r12 != 0) goto L_0x000b
            r3 = r2
            goto L_0x000c
        L_0x000b:
            r3 = r1
        L_0x000c:
            java.lang.reflect.Field[] r4 = r10.getDeclaredFields()
            int r5 = r4.length
        L_0x0011:
            if (r1 >= r5) goto L_0x0062
            r6 = r4[r1]
            int r7 = r6.getModifiers()
            boolean r8 = java.lang.reflect.Modifier.isStatic(r7)
            if (r8 == 0) goto L_0x005f
            boolean r8 = java.lang.reflect.Modifier.isFinal(r7)
            if (r8 == 0) goto L_0x005f
            java.lang.Class r8 = r6.getType()
            java.lang.Class r9 = java.lang.Integer.TYPE
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x005f
            java.lang.String r8 = r6.getName()
            boolean r8 = r8.startsWith(r11)
            if (r8 == 0) goto L_0x005f
            r8 = 0
            int r8 = r6.getInt(r8)     // Catch: IllegalAccessException -> 0x005e
            if (r8 != 0) goto L_0x0049
            if (r3 == 0) goto L_0x0049
            java.lang.String r1 = constNameWithoutPrefix(r11, r6)     // Catch: IllegalAccessException -> 0x005e
            return r1
        L_0x0049:
            if (r8 == 0) goto L_0x005d
            r9 = r12 & r8
            if (r9 != r8) goto L_0x005d
            int r9 = ~r8     // Catch: IllegalAccessException -> 0x005e
            r12 = r12 & r9
            java.lang.String r9 = constNameWithoutPrefix(r11, r6)     // Catch: IllegalAccessException -> 0x005e
            r0.append(r9)     // Catch: IllegalAccessException -> 0x005e
            r9 = 124(0x7c, float:1.74E-43)
            r0.append(r9)     // Catch: IllegalAccessException -> 0x005e
        L_0x005d:
            goto L_0x005f
        L_0x005e:
            r8 = move-exception
        L_0x005f:
            int r1 = r1 + 1
            goto L_0x0011
        L_0x0062:
            if (r12 != 0) goto L_0x0074
            int r1 = r0.length()
            if (r1 != 0) goto L_0x006b
            goto L_0x0074
        L_0x006b:
            int r1 = r0.length()
            int r1 = r1 - r2
            r0.deleteCharAt(r1)
            goto L_0x007b
        L_0x0074:
            java.lang.String r1 = java.lang.Integer.toHexString(r12)
            r0.append(r1)
        L_0x007b:
            java.lang.String r1 = r0.toString()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.util.DebugUtils.flagsToString(java.lang.Class, java.lang.String, int):java.lang.String");
    }

    public static String constantToString(Class<?> clazz, String prefix, int value) {
        Field[] declaredFields = clazz.getDeclaredFields();
        int length = declaredFields.length;
        for (int i = 0; i < length; i++) {
            Field field = declaredFields[i];
            int modifiers = field.getModifiers();
            try {
                if (Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers) && field.getType().equals(Integer.TYPE) && field.getName().startsWith(prefix) && field.getInt(null) == value) {
                    return constNameWithoutPrefix(prefix, field);
                }
            } catch (IllegalAccessException e) {
            }
        }
        return prefix + Integer.toString(value);
    }

    private static String constNameWithoutPrefix(String prefix, Field field) {
        return field.getName().substring(prefix.length());
    }

    public static List<String> callersWithin(final Class<?> cls, int offset) {
        List<String> result = (List) Arrays.stream(Thread.currentThread().getStackTrace()).skip(offset + 3).filter(new Predicate() { // from class: android.util.DebugUtils$$ExternalSyntheticLambda1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean startsWith;
                startsWith = ((StackTraceElement) obj).getClassName().startsWith(cls.getName());
                return startsWith;
            }
        }).map(DebugUtils$$ExternalSyntheticLambda0.INSTANCE).collect(Collectors.toList());
        Collections.reverse(result);
        return result;
    }
}
