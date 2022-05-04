package vendor.oplus.hardware.rpmh.V2_0;

import java.util.ArrayList;
/* loaded from: classes4.dex */
public final class Result {
    public static final int BAD_VALUE = -3;
    public static final int INVALID_OPERATION = -4;
    public static final int OK = 0;
    public static final int PERMISSION_DENIED = -2;
    public static final int UN_SUPPORTED = -1;

    public static final String toString(int o) {
        if (o == 0) {
            return "OK";
        }
        if (o == -1) {
            return "UN_SUPPORTED";
        }
        if (o == -2) {
            return "PERMISSION_DENIED";
        }
        if (o == -3) {
            return "BAD_VALUE";
        }
        if (o == -4) {
            return "INVALID_OPERATION";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("OK");
        if ((o & (-1)) == -1) {
            list.add("UN_SUPPORTED");
            flipped = 0 | (-1);
        }
        if ((o & (-2)) == -2) {
            list.add("PERMISSION_DENIED");
            flipped |= -2;
        }
        if ((o & (-3)) == -3) {
            list.add("BAD_VALUE");
            flipped |= -3;
        }
        if ((o & (-4)) == -4) {
            list.add("INVALID_OPERATION");
            flipped |= -4;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
