package android.content.pm.parsing.component;
/* loaded from: classes.dex */
public class ParsedAttributionUtils {
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00ad, code lost:
        if (r0 != null) goto L_0x00b4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00af, code lost:
        r0 = java.util.Collections.emptyList();
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00b4, code lost:
        ((java.util.ArrayList) r0).trimToSize();
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00c3, code lost:
        return r13.success(new android.content.pm.parsing.component.ParsedAttribution(r4, r5, r0));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.content.pm.parsing.result.ParseResult<android.content.pm.parsing.component.ParsedAttribution> parseAttribution(android.content.res.Resources r11, android.content.res.XmlResourceParser r12, android.content.pm.parsing.result.ParseInput r13) throws java.io.IOException, org.xmlpull.v1.XmlPullParserException {
        /*
            r0 = 0
            int[] r1 = com.android.internal.R.styleable.AndroidManifestAttribution
            android.content.res.TypedArray r1 = r11.obtainAttributes(r12, r1)
            if (r1 != 0) goto L_0x0010
            java.lang.String r2 = "<attribution> could not be parsed"
            android.content.pm.parsing.result.ParseResult r2 = r13.error(r2)
            return r2
        L_0x0010:
            r2 = 1
            r3 = 0
            java.lang.String r4 = r1.getNonConfigurationString(r2, r3)     // Catch: all -> 0x00c4
            if (r4 != 0) goto L_0x0022
            java.lang.String r2 = "<attribution> does not specify android:tag"
            android.content.pm.parsing.result.ParseResult r2 = r13.error(r2)     // Catch: all -> 0x00c4
            r1.recycle()
            return r2
        L_0x0022:
            int r5 = r4.length()     // Catch: all -> 0x00c4
            r6 = 50
            if (r5 <= r6) goto L_0x0034
            java.lang.String r2 = "android:tag is too long. Max length is 50"
            android.content.pm.parsing.result.ParseResult r2 = r13.error(r2)     // Catch: all -> 0x00c4
            r1.recycle()
            return r2
        L_0x0034:
            int r5 = r1.getResourceId(r3, r3)     // Catch: all -> 0x00c4
            if (r5 != 0) goto L_0x0044
            java.lang.String r2 = "<attribution> does not specify android:label"
            android.content.pm.parsing.result.ParseResult r2 = r13.error(r2)     // Catch: all -> 0x00c4
            r1.recycle()
            return r2
        L_0x0044:
            r1.recycle()
            int r6 = r12.getDepth()
        L_0x004c:
            int r7 = r12.next()
            r8 = r7
            if (r7 == r2) goto L_0x00ad
            r7 = 3
            if (r8 != r7) goto L_0x005c
            int r9 = r12.getDepth()
            if (r9 <= r6) goto L_0x00ad
        L_0x005c:
            if (r8 == r7) goto L_0x004c
            r7 = 4
            if (r8 != r7) goto L_0x0062
            goto L_0x004c
        L_0x0062:
            java.lang.String r7 = r12.getName()
            java.lang.String r9 = "inherit-from"
            boolean r9 = r7.equals(r9)
            if (r9 == 0) goto L_0x0097
            int[] r9 = com.android.internal.R.styleable.AndroidManifestAttributionInheritFrom
            android.content.res.TypedArray r1 = r11.obtainAttributes(r12, r9)
            if (r1 != 0) goto L_0x007d
            java.lang.String r2 = "<inherit-from> could not be parsed"
            android.content.pm.parsing.result.ParseResult r2 = r13.error(r2)
            return r2
        L_0x007d:
            java.lang.String r9 = r1.getNonConfigurationString(r3, r3)     // Catch: all -> 0x0092
            if (r0 != 0) goto L_0x0089
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch: all -> 0x0092
            r10.<init>()     // Catch: all -> 0x0092
            r0 = r10
        L_0x0089:
            r0.add(r9)     // Catch: all -> 0x0092
            r1.recycle()
            goto L_0x004c
        L_0x0092:
            r2 = move-exception
            r1.recycle()
            throw r2
        L_0x0097:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Bad element under <attribution>: "
            r2.append(r3)
            r2.append(r7)
            java.lang.String r2 = r2.toString()
            android.content.pm.parsing.result.ParseResult r2 = r13.error(r2)
            return r2
        L_0x00ad:
            if (r0 != 0) goto L_0x00b4
            java.util.List r0 = java.util.Collections.emptyList()
            goto L_0x00ba
        L_0x00b4:
            r2 = r0
            java.util.ArrayList r2 = (java.util.ArrayList) r2
            r2.trimToSize()
        L_0x00ba:
            android.content.pm.parsing.component.ParsedAttribution r2 = new android.content.pm.parsing.component.ParsedAttribution
            r2.<init>(r4, r5, r0)
            android.content.pm.parsing.result.ParseResult r2 = r13.success(r2)
            return r2
        L_0x00c4:
            r2 = move-exception
            r1.recycle()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.pm.parsing.component.ParsedAttributionUtils.parseAttribution(android.content.res.Resources, android.content.res.XmlResourceParser, android.content.pm.parsing.result.ParseInput):android.content.pm.parsing.result.ParseResult");
    }
}
