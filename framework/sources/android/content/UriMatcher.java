package android.content;

import java.util.ArrayList;
/* loaded from: classes.dex */
public class UriMatcher {
    private static final int EXACT = 0;
    public static final int NO_MATCH = -1;
    private static final int NUMBER = 1;
    private static final int TEXT = 2;
    private ArrayList<UriMatcher> mChildren;
    private int mCode;
    private final String mText;
    private final int mWhich;

    public UriMatcher(int code) {
        this.mCode = code;
        this.mWhich = -1;
        this.mChildren = new ArrayList<>();
        this.mText = null;
    }

    private UriMatcher(int which, String text) {
        this.mCode = -1;
        this.mWhich = which;
        this.mChildren = new ArrayList<>();
        this.mText = text;
    }

    public void addURI(String authority, String path, int code) {
        if (code >= 0) {
            String[] tokens = null;
            int numTokens = 0;
            if (path != null) {
                String newPath = path;
                if (path.length() > 1 && path.charAt(0) == '/') {
                    newPath = path.substring(1);
                }
                tokens = newPath.split("/");
            }
            if (tokens != null) {
                numTokens = tokens.length;
            }
            UriMatcher node = this;
            int i = -1;
            while (i < numTokens) {
                String token = i < 0 ? authority : tokens[i];
                ArrayList<UriMatcher> children = node.mChildren;
                int numChildren = children.size();
                int j = 0;
                while (true) {
                    if (j >= numChildren) {
                        break;
                    }
                    UriMatcher child = children.get(j);
                    if (token.equals(child.mText)) {
                        node = child;
                        break;
                    }
                    j++;
                }
                if (j == numChildren) {
                    UriMatcher child2 = createChild(token);
                    node.mChildren.add(child2);
                    node = child2;
                }
                i++;
            }
            node.mCode = code;
            return;
        }
        throw new IllegalArgumentException("code " + code + " is invalid: it must be positive");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static UriMatcher createChild(String token) {
        char c;
        switch (token.hashCode()) {
            case 35:
                if (token.equals("#")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 42:
                if (token.equals("*")) {
                    c = 1;
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
                return new UriMatcher(1, "#");
            case 1:
                return new UriMatcher(2, "*");
            default:
                return new UriMatcher(0, token);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x006b A[LOOP:0: B:9:0x0015->B:35:0x006b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0069 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int match(android.net.Uri r14) {
        /*
            r13 = this;
            java.util.List r0 = r14.getPathSegments()
            int r1 = r0.size()
            r2 = r13
            if (r1 != 0) goto L_0x0014
            java.lang.String r3 = r14.getAuthority()
            if (r3 != 0) goto L_0x0014
            int r3 = r13.mCode
            return r3
        L_0x0014:
            r3 = -1
        L_0x0015:
            if (r3 >= r1) goto L_0x006e
            if (r3 >= 0) goto L_0x001e
            java.lang.String r4 = r14.getAuthority()
            goto L_0x0024
        L_0x001e:
            java.lang.Object r4 = r0.get(r3)
            java.lang.String r4 = (java.lang.String) r4
        L_0x0024:
            java.util.ArrayList<android.content.UriMatcher> r5 = r2.mChildren
            if (r5 != 0) goto L_0x0029
            goto L_0x006e
        L_0x0029:
            r2 = 0
            int r6 = r5.size()
            r7 = 0
        L_0x002f:
            if (r7 >= r6) goto L_0x0067
            java.lang.Object r8 = r5.get(r7)
            android.content.UriMatcher r8 = (android.content.UriMatcher) r8
            int r9 = r8.mWhich
            switch(r9) {
                case 0: goto L_0x0058;
                case 1: goto L_0x003f;
                case 2: goto L_0x003d;
                default: goto L_0x003c;
            }
        L_0x003c:
            goto L_0x0061
        L_0x003d:
            r2 = r8
            goto L_0x0061
        L_0x003f:
            int r9 = r4.length()
            r10 = 0
        L_0x0044:
            if (r10 >= r9) goto L_0x0056
            char r11 = r4.charAt(r10)
            r12 = 48
            if (r11 < r12) goto L_0x0061
            r12 = 57
            if (r11 <= r12) goto L_0x0053
            goto L_0x0061
        L_0x0053:
            int r10 = r10 + 1
            goto L_0x0044
        L_0x0056:
            r2 = r8
            goto L_0x0061
        L_0x0058:
            java.lang.String r9 = r8.mText
            boolean r9 = r9.equals(r4)
            if (r9 == 0) goto L_0x0061
            r2 = r8
        L_0x0061:
            if (r2 == 0) goto L_0x0064
            goto L_0x0067
        L_0x0064:
            int r7 = r7 + 1
            goto L_0x002f
        L_0x0067:
            if (r2 != 0) goto L_0x006b
            r7 = -1
            return r7
        L_0x006b:
            int r3 = r3 + 1
            goto L_0x0015
        L_0x006e:
            int r3 = r2.mCode
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.UriMatcher.match(android.net.Uri):int");
    }
}
