package android.content.type;

import java.io.InputStream;
import java.util.function.Function;
import libcore.content.type.MimeMap;
/* loaded from: classes.dex */
public class DefaultMimeMapFactory {
    private DefaultMimeMapFactory() {
    }

    public static MimeMap create() {
        return create(new Function() { // from class: android.content.type.DefaultMimeMapFactory$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                InputStream resourceAsStream;
                resourceAsStream = r1.getResourceAsStream("/res/" + ((String) obj));
                return resourceAsStream;
            }
        });
    }

    public static MimeMap create(Function<String, InputStream> resourceSupplier) {
        MimeMap.Builder builder = MimeMap.builder();
        parseTypes(builder, resourceSupplier, "debian.mime.types");
        parseTypes(builder, resourceSupplier, "android.mime.types");
        parseTypes(builder, resourceSupplier, "vendor.mime.types");
        return builder.build();
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0074, code lost:
        throw new java.lang.IllegalArgumentException("Malformed line: " + r3);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void parseTypes(libcore.content.type.MimeMap.Builder r10, java.util.function.Function<java.lang.String, java.io.InputStream> r11, java.lang.String r12) {
        /*
            java.lang.Object r0 = r11.apply(r12)     // Catch: IOException | RuntimeException -> 0x0095
            java.io.InputStream r0 = (java.io.InputStream) r0     // Catch: IOException | RuntimeException -> 0x0095
            java.util.Objects.requireNonNull(r0)     // Catch: IOException | RuntimeException -> 0x0095
            java.io.InputStream r0 = (java.io.InputStream) r0     // Catch: IOException | RuntimeException -> 0x0095
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: all -> 0x0089
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: all -> 0x0089
            r2.<init>(r0)     // Catch: all -> 0x0089
            r1.<init>(r2)     // Catch: all -> 0x0089
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch: all -> 0x007f
            r3 = 10
            r2.<init>(r3)     // Catch: all -> 0x007f
        L_0x001c:
            java.lang.String r3 = r1.readLine()     // Catch: all -> 0x007f
            r4 = r3
            if (r3 == 0) goto L_0x0075
            r2.clear()     // Catch: all -> 0x007f
            r3 = 0
        L_0x0027:
            r5 = 32
            int r5 = r4.indexOf(r5, r3)     // Catch: all -> 0x007f
            if (r5 >= 0) goto L_0x0034
            int r6 = r4.length()     // Catch: all -> 0x007f
            r5 = r6
        L_0x0034:
            java.lang.String r6 = r4.substring(r3, r5)     // Catch: all -> 0x007f
            boolean r7 = r6.isEmpty()     // Catch: all -> 0x007f
            if (r7 != 0) goto L_0x005e
            r2.add(r6)     // Catch: all -> 0x007f
            int r3 = r5 + 1
            int r5 = r4.length()     // Catch: all -> 0x007f
            if (r3 < r5) goto L_0x0027
            r5 = 0
            java.lang.Object r5 = r2.get(r5)     // Catch: all -> 0x007f
            java.lang.String r5 = (java.lang.String) r5     // Catch: all -> 0x007f
            int r6 = r2.size()     // Catch: all -> 0x007f
            r7 = 1
            java.util.List r6 = r2.subList(r7, r6)     // Catch: all -> 0x007f
            r10.addMimeMapping(r5, r6)     // Catch: all -> 0x007f
            goto L_0x001c
        L_0x005e:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException     // Catch: all -> 0x007f
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: all -> 0x007f
            r8.<init>()     // Catch: all -> 0x007f
            java.lang.String r9 = "Malformed line: "
            r8.append(r9)     // Catch: all -> 0x007f
            r8.append(r4)     // Catch: all -> 0x007f
            java.lang.String r8 = r8.toString()     // Catch: all -> 0x007f
            r7.<init>(r8)     // Catch: all -> 0x007f
            throw r7     // Catch: all -> 0x007f
        L_0x0075:
            r1.close()     // Catch: all -> 0x0089
            if (r0 == 0) goto L_0x007d
            r0.close()     // Catch: IOException | RuntimeException -> 0x0095
        L_0x007d:
            return
        L_0x007f:
            r2 = move-exception
            r1.close()     // Catch: all -> 0x0084
            goto L_0x0088
        L_0x0084:
            r3 = move-exception
            r2.addSuppressed(r3)     // Catch: all -> 0x0089
        L_0x0088:
            throw r2     // Catch: all -> 0x0089
        L_0x0089:
            r1 = move-exception
            if (r0 == 0) goto L_0x0094
            r0.close()     // Catch: all -> 0x0090
            goto L_0x0094
        L_0x0090:
            r2 = move-exception
            r1.addSuppressed(r2)     // Catch: IOException | RuntimeException -> 0x0095
        L_0x0094:
            throw r1     // Catch: IOException | RuntimeException -> 0x0095
        L_0x0095:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Failed to parse "
            r2.append(r3)
            r2.append(r12)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.type.DefaultMimeMapFactory.parseTypes(libcore.content.type.MimeMap$Builder, java.util.function.Function, java.lang.String):void");
    }
}
