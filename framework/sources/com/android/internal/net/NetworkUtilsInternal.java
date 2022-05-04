package com.android.internal.net;

import android.system.Os;
import android.system.OsConstants;
import java.io.FileDescriptor;
/* loaded from: classes4.dex */
public class NetworkUtilsInternal {
    private static final int[] ADDRESS_FAMILIES = {OsConstants.AF_INET, OsConstants.AF_INET6};

    public static native boolean protectFromVpn(int i);

    public static native boolean protectFromVpn(FileDescriptor fileDescriptor);

    public static native void setAllowNetworkingForProcess(boolean z);

    public static boolean isWeaklyValidatedHostname(String hostname) {
        int[] iArr;
        if (!hostname.matches("^[a-zA-Z0-9_.-]+$")) {
            return false;
        }
        for (int address_family : ADDRESS_FAMILIES) {
            if (Os.inet_pton(address_family, hostname) != null) {
                return false;
            }
        }
        return true;
    }

    public static long multiplySafeByRational(long value, long num, long den) {
        if (den != 0) {
            long r = value * num;
            long ax = Math.abs(value);
            long ay = Math.abs(num);
            if (((ax | ay) >>> 31) != 0) {
                if ((num != 0 && r / num != value) || (value == Long.MIN_VALUE && num == -1)) {
                    return (long) ((num / den) * value);
                }
            }
            long x = r / den;
            return x;
        }
        throw new ArithmeticException("Invalid Denominator");
    }
}
