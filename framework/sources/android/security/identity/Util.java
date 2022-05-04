package android.security.identity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.PublicKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;
import java.util.Collection;
/* loaded from: classes2.dex */
public class Util {
    private static final String TAG = "Util";

    static int[] integerCollectionToArray(Collection<Integer> collection) {
        int[] result = new int[collection.size()];
        int n = 0;
        for (Integer num : collection) {
            int item = num.intValue();
            result[n] = item;
            n++;
        }
        return result;
    }

    static byte[] stripLeadingZeroes(byte[] value) {
        int n = 0;
        while (n < value.length && value[n] == 0) {
            n++;
        }
        int newLen = value.length - n;
        byte[] ret = new byte[newLen];
        int m = 0;
        while (n < value.length) {
            ret[m] = value[n];
            m++;
            n++;
        }
        return ret;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] publicKeyEncodeUncompressedForm(PublicKey publicKey) {
        ECPoint w = ((ECPublicKey) publicKey).getW();
        byte[] x = stripLeadingZeroes(w.getAffineX().toByteArray());
        byte[] y = stripLeadingZeroes(w.getAffineY().toByteArray());
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(4);
            baos.write(x);
            baos.write(y);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Unexpected IOException", e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0052 A[Catch: InvalidKeyException -> 0x0062, LOOP:0: B:13:0x003f->B:15:0x0052, LOOP_END, TryCatch #1 {InvalidKeyException -> 0x0062, blocks: (B:7:0x0011, B:10:0x0015, B:11:0x001e, B:12:0x002c, B:13:0x003f, B:15:0x0052, B:16:0x005b), top: B:28:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x005b A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] computeHkdf(java.lang.String r8, byte[] r9, byte[] r10, byte[] r11, int r12) {
        /*
            r0 = 0
            javax.crypto.Mac r1 = javax.crypto.Mac.getInstance(r8)     // Catch: NoSuchAlgorithmException -> 0x0074
            r0 = r1
            int r1 = r0.getMacLength()
            int r1 = r1 * 255
            if (r12 > r1) goto L_0x006b
            if (r10 == 0) goto L_0x001e
            int r1 = r10.length     // Catch: InvalidKeyException -> 0x0062
            if (r1 != 0) goto L_0x0015
            goto L_0x001e
        L_0x0015:
            javax.crypto.spec.SecretKeySpec r1 = new javax.crypto.spec.SecretKeySpec     // Catch: InvalidKeyException -> 0x0062
            r1.<init>(r10, r8)     // Catch: InvalidKeyException -> 0x0062
            r0.init(r1)     // Catch: InvalidKeyException -> 0x0062
            goto L_0x002c
        L_0x001e:
            javax.crypto.spec.SecretKeySpec r1 = new javax.crypto.spec.SecretKeySpec     // Catch: InvalidKeyException -> 0x0062
            int r2 = r0.getMacLength()     // Catch: InvalidKeyException -> 0x0062
            byte[] r2 = new byte[r2]     // Catch: InvalidKeyException -> 0x0062
            r1.<init>(r2, r8)     // Catch: InvalidKeyException -> 0x0062
            r0.init(r1)     // Catch: InvalidKeyException -> 0x0062
        L_0x002c:
            byte[] r1 = r0.doFinal(r9)     // Catch: InvalidKeyException -> 0x0062
            byte[] r2 = new byte[r12]     // Catch: InvalidKeyException -> 0x0062
            r3 = 1
            r4 = 0
            javax.crypto.spec.SecretKeySpec r5 = new javax.crypto.spec.SecretKeySpec     // Catch: InvalidKeyException -> 0x0062
            r5.<init>(r1, r8)     // Catch: InvalidKeyException -> 0x0062
            r0.init(r5)     // Catch: InvalidKeyException -> 0x0062
            r5 = 0
            byte[] r6 = new byte[r5]     // Catch: InvalidKeyException -> 0x0062
        L_0x003f:
            r0.update(r6)     // Catch: InvalidKeyException -> 0x0062
            r0.update(r11)     // Catch: InvalidKeyException -> 0x0062
            byte r7 = (byte) r3     // Catch: InvalidKeyException -> 0x0062
            r0.update(r7)     // Catch: InvalidKeyException -> 0x0062
            byte[] r7 = r0.doFinal()     // Catch: InvalidKeyException -> 0x0062
            r6 = r7
            int r7 = r6.length     // Catch: InvalidKeyException -> 0x0062
            int r7 = r7 + r4
            if (r7 >= r12) goto L_0x005b
            int r7 = r6.length     // Catch: InvalidKeyException -> 0x0062
            java.lang.System.arraycopy(r6, r5, r2, r4, r7)     // Catch: InvalidKeyException -> 0x0062
            int r7 = r6.length     // Catch: InvalidKeyException -> 0x0062
            int r4 = r4 + r7
            int r3 = r3 + 1
            goto L_0x003f
        L_0x005b:
            int r7 = r12 - r4
            java.lang.System.arraycopy(r6, r5, r2, r4, r7)     // Catch: InvalidKeyException -> 0x0062
            return r2
        L_0x0062:
            r1 = move-exception
            java.lang.RuntimeException r2 = new java.lang.RuntimeException
            java.lang.String r3 = "Error MACing"
            r2.<init>(r3, r1)
            throw r2
        L_0x006b:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "size too large"
            r1.<init>(r2)
            throw r1
        L_0x0074:
            r1 = move-exception
            java.lang.RuntimeException r2 = new java.lang.RuntimeException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "No such algorithm: "
            r3.append(r4)
            r3.append(r8)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3, r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.security.identity.Util.computeHkdf(java.lang.String, byte[], byte[], byte[], int):byte[]");
    }

    private Util() {
    }
}
