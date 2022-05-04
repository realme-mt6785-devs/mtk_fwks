package android.os;

import android.util.Log;
/* loaded from: classes2.dex */
public class OplusBasePowerManager {
    private static final int BRIGHTNESS_ELEVEN_BITS = 2047;
    public static int BRIGHTNESS_MULTIBITS_ON = 255;
    private static final int BRIGHTNESS_TEN_BITS = 1023;
    public static final int GO_TO_SLEEP_REASON_FINGERPRINT = 14;
    public static final int GO_TO_SLEEP_REASON_PROXIMITY = 13;
    public static final int GO_TO_SLEEP_REASON_SYSTEM_UI_CLEAN_UP = 15;
    private static final int MAX_STACK_LINE = 5;
    public static final int WAKE_REASON_DOUBLE_HOME = 99;
    public static final int WAKE_REASON_DOUBLE_TAP_SCREEN = 100;
    public static final int WAKE_REASON_LIFT_HAND = 101;
    public static final int WAKE_REASON_PROXIMITY = 97;
    public static final int WAKE_REASON_SYSTEM_UI_CLEAN_UP = 103;
    public static final int WAKE_REASON_WINDOWMANAGER_TURN_SCREENON = 102;
    public static final String WAKE_UP_DUE_TO_ACTIVITYMANAGER_TURN_SCREENON = "android.server.am:TURN_ON:turnScreenOnFlag";
    public static final String WAKE_UP_DUE_TO_DOUBLE_HOME = "android.service.fingerprint:DOUBLE_HOME";
    public static final String WAKE_UP_DUE_TO_DOUBLE_TAP_SCREEN = "oplus.wakeup.gesture:DOUBLE_TAP_SCREEN";
    public static final String WAKE_UP_DUE_TO_FINGERPRINT = "android.service.fingerprint:WAKEUP";
    public static final String WAKE_UP_DUE_TO_LIFT_HAND = "oplus.wakeup.gesture:LIFT_HAND";
    public static final String WAKE_UP_DUE_TO_PROXIMITY = "android.service.power:proximity";
    public static final String WAKE_UP_DUE_TO_SYSTEM_UI_CLEAN_UP = "oplus.wakeup.systemui:clean up";
    public static final String WAKE_UP_DUE_TO_WINDOWMANAGER_TURN_SCREENON = "android.server.wm:SCREEN_ON_FLAG";
    public static final int WAKE_UP_REASON_FINGERPRINT = 98;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void printStackTraceInfo(String methodName) {
        StackTraceElement[] stack = new Throwable().getStackTrace();
        if (stack != null) {
            for (int i = 0; i < 5 && i < stack.length; i++) {
                Log.i("PowerManager", methodName + "    |----" + stack[i].toString());
            }
        }
    }

    public static String wakeReasonToStringExtend(int wakeReason) {
        switch (wakeReason) {
            case 97:
                return WAKE_UP_DUE_TO_PROXIMITY;
            case 98:
                return WAKE_UP_DUE_TO_FINGERPRINT;
            case 99:
                return WAKE_UP_DUE_TO_DOUBLE_HOME;
            case 100:
                return WAKE_UP_DUE_TO_DOUBLE_TAP_SCREEN;
            case 101:
                return WAKE_UP_DUE_TO_LIFT_HAND;
            case 102:
                return WAKE_UP_DUE_TO_WINDOWMANAGER_TURN_SCREENON;
            case 103:
                return WAKE_UP_DUE_TO_SYSTEM_UI_CLEAN_UP;
            default:
                return null;
        }
    }
}
