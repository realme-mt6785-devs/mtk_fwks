package com.android.net.module.util;

import android.net.NetworkCapabilities;
/* loaded from: classes4.dex */
public final class NetworkCapabilitiesUtils {
    private static final int[] DISPLAY_TRANSPORT_PRIORITIES = {4, 0, 5, 2, 1, 3, 8};
    private static final long FORCE_RESTRICTED_CAPABILITIES = 608174080;
    public static final int NET_CAPABILITY_BIP = 31;
    public static final int NET_CAPABILITY_ENTERPRISE = 29;
    public static final int NET_CAPABILITY_NOT_VCN_MANAGED = 28;
    public static final int NET_CAPABILITY_OEM_PRIVATE = 26;
    public static final int NET_CAPABILITY_VEHICLE_INTERNAL = 27;
    public static final int NET_CAPABILITY_VSIM = 30;
    static final long RESTRICTED_CAPABILITIES = -394262596;
    public static final int TRANSPORT_USB = 8;
    static final long UNRESTRICTED_CAPABILITIES = 4163;

    public static int getDisplayTransport(int[] transports) {
        int[] iArr;
        for (int transport : DISPLAY_TRANSPORT_PRIORITIES) {
            if (CollectionUtils.contains(transports, transport)) {
                return transport;
            }
        }
        if (transports.length >= 1) {
            return transports[0];
        }
        throw new IllegalArgumentException("No transport in the provided array");
    }

    public static boolean inferRestrictedCapability(NetworkCapabilities nc) {
        int[] unpackBits;
        int[] unpackBits2;
        int[] unpackBits3;
        for (int capability : unpackBits(FORCE_RESTRICTED_CAPABILITIES)) {
            if (nc.hasCapability(capability)) {
                return true;
            }
        }
        for (int capability2 : unpackBits(UNRESTRICTED_CAPABILITIES)) {
            if (nc.hasCapability(capability2)) {
                return false;
            }
        }
        for (int capability3 : unpackBits(RESTRICTED_CAPABILITIES)) {
            if (nc.hasCapability(capability3)) {
                return true;
            }
        }
        return false;
    }

    public static int[] unpackBits(long val) {
        int size = Long.bitCount(val);
        int[] result = new int[size];
        int index = 0;
        int bitPos = 0;
        while (val != 0) {
            if ((val & 1) == 1) {
                result[index] = bitPos;
                index++;
            }
            val >>>= 1;
            bitPos++;
        }
        return result;
    }

    public static long packBits(int[] bits) {
        long packed = 0;
        for (int b : bits) {
            packed |= 1 << b;
        }
        return packed;
    }
}
