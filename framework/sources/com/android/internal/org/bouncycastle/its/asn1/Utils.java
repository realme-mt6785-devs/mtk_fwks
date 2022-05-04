package com.android.internal.org.bouncycastle.its.asn1;

import com.android.internal.org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
class Utils {
    Utils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] octetStringFixed(byte[] octets, int n) {
        if (octets.length == n) {
            return octets;
        }
        throw new IllegalArgumentException("octet string out of range");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] octetStringFixed(byte[] octets) {
        if (octets.length >= 1 && octets.length <= 32) {
            return Arrays.clone(octets);
        }
        throw new IllegalArgumentException("octet string out of range");
    }
}