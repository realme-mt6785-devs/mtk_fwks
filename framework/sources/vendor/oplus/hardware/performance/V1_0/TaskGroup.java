package vendor.oplus.hardware.performance.V1_0;

import java.util.ArrayList;
/* loaded from: classes4.dex */
public final class TaskGroup {
    public static final int DEFAULT = 0;
    public static final int DISPLAY = 3;
    public static final int GAME = 5;
    public static final int INPUT = 4;
    public static final int RENDER = 2;
    public static final int UI = 1;

    public static final String toString(int o) {
        if (o == 0) {
            return "DEFAULT";
        }
        if (o == 1) {
            return "UI";
        }
        if (o == 2) {
            return "RENDER";
        }
        if (o == 3) {
            return "DISPLAY";
        }
        if (o == 4) {
            return "INPUT";
        }
        if (o == 5) {
            return "GAME";
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        list.add("DEFAULT");
        if ((o & 1) == 1) {
            list.add("UI");
            flipped = 0 | 1;
        }
        if ((o & 2) == 2) {
            list.add("RENDER");
            flipped |= 2;
        }
        if ((o & 3) == 3) {
            list.add("DISPLAY");
            flipped |= 3;
        }
        if ((o & 4) == 4) {
            list.add("INPUT");
            flipped |= 4;
        }
        if ((o & 5) == 5) {
            list.add("GAME");
            flipped |= 5;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
