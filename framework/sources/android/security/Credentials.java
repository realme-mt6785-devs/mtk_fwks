package android.security;

import com.android.internal.org.bouncycastle.util.io.pem.PemObject;
import com.android.internal.org.bouncycastle.util.io.pem.PemWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
/* loaded from: classes2.dex */
public class Credentials {
    public static final String ACTION_MANAGE_CREDENTIALS = "android.security.MANAGE_CREDENTIALS";
    @Deprecated
    public static final String APP_SOURCE_CERTIFICATE = "FSV_";
    @Deprecated
    public static final String CA_CERTIFICATE = "CACERT_";
    public static final String CERTIFICATE_USAGE_APP_SOURCE = "appsrc";
    public static final String CERTIFICATE_USAGE_CA = "ca";
    public static final String CERTIFICATE_USAGE_USER = "user";
    public static final String CERTIFICATE_USAGE_WIFI = "wifi";
    public static final String EXTENSION_CER = ".cer";
    public static final String EXTENSION_CRT = ".crt";
    public static final String EXTENSION_P12 = ".p12";
    public static final String EXTENSION_PFX = ".pfx";
    public static final String EXTRA_CA_CERTIFICATES_DATA = "ca_certificates_data";
    public static final String EXTRA_CERTIFICATE_USAGE = "certificate_install_usage";
    public static final String EXTRA_INSTALL_AS_UID = "install_as_uid";
    public static final String EXTRA_PRIVATE_KEY = "PKEY";
    public static final String EXTRA_PUBLIC_KEY = "KEY";
    public static final String EXTRA_USER_CERTIFICATE_DATA = "user_certificate_data";
    public static final String EXTRA_USER_KEY_ALIAS = "user_key_pair_name";
    public static final String EXTRA_USER_PRIVATE_KEY_DATA = "user_private_key_data";
    public static final String INSTALL_ACTION = "android.credentials.INSTALL";
    public static final String INSTALL_AS_USER_ACTION = "android.credentials.INSTALL_AS_USER";
    public static final String LOCKDOWN_VPN = "LOCKDOWN_VPN";
    private static final String LOGTAG = "Credentials";
    public static final String PLATFORM_VPN = "PLATFORM_VPN_";
    @Deprecated
    public static final String USER_CERTIFICATE = "USRCERT_";
    @Deprecated
    public static final String USER_PRIVATE_KEY = "USRPKEY_";
    @Deprecated
    public static final String USER_SECRET_KEY = "USRSKEY_";
    public static final String VPN = "VPN_";
    public static final String WIFI = "WIFI_";

    public static byte[] convertToPem(Certificate... objects) throws IOException, CertificateEncodingException {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        Writer writer = new OutputStreamWriter(bao, StandardCharsets.US_ASCII);
        PemWriter pw = new PemWriter(writer);
        for (Certificate o : objects) {
            pw.writeObject(new PemObject("CERTIFICATE", o.getEncoded()));
        }
        pw.close();
        return bao.toByteArray();
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x005e, code lost:
        throw new java.lang.IllegalArgumentException("Unknown type " + r5.getType());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.List<java.security.cert.X509Certificate> convertFromPem(byte[] r9) throws java.io.IOException, java.security.cert.CertificateException {
        /*
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream
            r0.<init>(r9)
            java.io.InputStreamReader r1 = new java.io.InputStreamReader
            java.nio.charset.Charset r2 = java.nio.charset.StandardCharsets.US_ASCII
            r1.<init>(r0, r2)
            com.android.internal.org.bouncycastle.util.io.pem.PemReader r2 = new com.android.internal.org.bouncycastle.util.io.pem.PemReader
            r2.<init>(r1)
            java.lang.String r3 = "X509"
            java.security.cert.CertificateFactory r3 = java.security.cert.CertificateFactory.getInstance(r3)     // Catch: all -> 0x0064
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch: all -> 0x0064
            r4.<init>()     // Catch: all -> 0x0064
        L_0x001c:
            com.android.internal.org.bouncycastle.util.io.pem.PemObject r5 = r2.readPemObject()     // Catch: all -> 0x0064
            r6 = r5
            if (r5 == 0) goto L_0x005f
            java.lang.String r5 = r6.getType()     // Catch: all -> 0x0064
            java.lang.String r7 = "CERTIFICATE"
            boolean r5 = r5.equals(r7)     // Catch: all -> 0x0064
            if (r5 == 0) goto L_0x0044
            java.io.ByteArrayInputStream r5 = new java.io.ByteArrayInputStream     // Catch: all -> 0x0064
            byte[] r7 = r6.getContent()     // Catch: all -> 0x0064
            r5.<init>(r7)     // Catch: all -> 0x0064
            java.security.cert.Certificate r5 = r3.generateCertificate(r5)     // Catch: all -> 0x0064
            r7 = r5
            java.security.cert.X509Certificate r7 = (java.security.cert.X509Certificate) r7     // Catch: all -> 0x0064
            r4.add(r7)     // Catch: all -> 0x0064
            goto L_0x001c
        L_0x0044:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException     // Catch: all -> 0x0064
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: all -> 0x0064
            r7.<init>()     // Catch: all -> 0x0064
            java.lang.String r8 = "Unknown type "
            r7.append(r8)     // Catch: all -> 0x0064
            java.lang.String r8 = r6.getType()     // Catch: all -> 0x0064
            r7.append(r8)     // Catch: all -> 0x0064
            java.lang.String r7 = r7.toString()     // Catch: all -> 0x0064
            r5.<init>(r7)     // Catch: all -> 0x0064
            throw r5     // Catch: all -> 0x0064
        L_0x005f:
            r2.close()
            return r4
        L_0x0064:
            r3 = move-exception
            r2.close()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.security.Credentials.convertFromPem(byte[]):java.util.List");
    }
}
