package com.android.internal.org.bouncycastle.jcajce.provider.asymmetric.x509;

import com.android.internal.org.bouncycastle.asn1.ASN1Sequence;
import com.android.internal.org.bouncycastle.util.encoders.Base64;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes4.dex */
class PEMUtil {
    private final Boundaries[] _supportedBoundaries;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class Boundaries {
        private final String _footer;
        private final String _header;

        private Boundaries(String type) {
            this._header = "-----BEGIN " + type + "-----";
            this._footer = "-----END " + type + "-----";
        }

        public boolean isTheExpectedHeader(String line) {
            return line.startsWith(this._header);
        }

        public boolean isTheExpectedFooter(String line) {
            return line.startsWith(this._footer);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PEMUtil(String type) {
        this._supportedBoundaries = new Boundaries[]{new Boundaries(type), new Boundaries("X509 " + type), new Boundaries("PKCS7")};
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001f, code lost:
        if (r0.length() == 0) goto L_0x0005;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String readLine(java.io.InputStream r6) throws java.io.IOException {
        /*
            r5 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
        L_0x0005:
            int r1 = r6.read()
            r2 = r1
            r3 = 10
            r4 = 13
            if (r1 == r4) goto L_0x0019
            if (r2 == r3) goto L_0x0019
            if (r2 < 0) goto L_0x0019
            char r1 = (char) r2
            r0.append(r1)
            goto L_0x0005
        L_0x0019:
            if (r2 < 0) goto L_0x0021
            int r1 = r0.length()
            if (r1 == 0) goto L_0x0005
        L_0x0021:
            if (r2 >= 0) goto L_0x0030
            int r1 = r0.length()
            if (r1 != 0) goto L_0x002b
            r1 = 0
            return r1
        L_0x002b:
            java.lang.String r1 = r0.toString()
            return r1
        L_0x0030:
            if (r2 != r4) goto L_0x0045
            r1 = 1
            r6.mark(r1)
            int r4 = r6.read()
            r2 = r4
            if (r4 != r3) goto L_0x0040
            r6.mark(r1)
        L_0x0040:
            if (r2 <= 0) goto L_0x0045
            r6.reset()
        L_0x0045:
            java.lang.String r1 = r0.toString()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.org.bouncycastle.jcajce.provider.asymmetric.x509.PEMUtil.readLine(java.io.InputStream):java.lang.String");
    }

    private Boundaries getBoundaries(String line) {
        Boundaries boundary;
        int i = 0;
        while (true) {
            Boundaries[] boundariesArr = this._supportedBoundaries;
            if (i == boundariesArr.length) {
                return null;
            }
            boundary = boundariesArr[i];
            if (boundary.isTheExpectedHeader(line) || boundary.isTheExpectedFooter(line)) {
                break;
            }
            i++;
        }
        return boundary;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Sequence readPEMObject(InputStream in) throws IOException {
        StringBuffer pemBuf = new StringBuffer();
        Boundaries header = null;
        while (header == null) {
            String line = readLine(in);
            if (line == null) {
                break;
            }
            header = getBoundaries(line);
            if (header != null && !header.isTheExpectedHeader(line)) {
                throw new IOException("malformed PEM data: found footer where header was expected");
            }
        }
        if (header != null) {
            Boundaries footer = null;
            while (footer == null) {
                String line2 = readLine(in);
                if (line2 == null) {
                    break;
                }
                footer = getBoundaries(line2);
                if (footer == null) {
                    pemBuf.append(line2);
                } else if (!header.isTheExpectedFooter(line2)) {
                    throw new IOException("malformed PEM data: header/footer mismatch");
                }
            }
            if (footer == null) {
                throw new IOException("malformed PEM data: no footer found");
            } else if (pemBuf.length() == 0) {
                return null;
            } else {
                try {
                    return ASN1Sequence.getInstance(Base64.decode(pemBuf.toString()));
                } catch (Exception e) {
                    throw new IOException("malformed PEM data encountered");
                }
            }
        } else {
            throw new IOException("malformed PEM data: no header found");
        }
    }
}
