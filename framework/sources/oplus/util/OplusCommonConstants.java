package oplus.util;

import android.util.Log;
/* loaded from: classes4.dex */
public final class OplusCommonConstants {
    private static final int COLOR_CALL_TRANSACTION_INDEX = 10000;
    private static final int COLOR_MESSAGE_INDEX = 1000;
    public static final int OPLUS_FIRST_CALL_TRANSACTION = 10001;
    public static final int OPLUS_FIRST_MESSAGE = 1001;
    public static final int OPLUS_GROUP = 1;
    public static final int OPLUS_LAST_CALL_TRANSACTION = 20000;
    public static final int OPLUS_LAST_MESSAGE = 2000;
    public static final int OPLUS_LAYOUT_IN_DISPLAY_CUTOUT_MODE_FORCE = 5;
    private static final int OPPO_CALL_TRANSACTION_INDEX = 10000;
    private static final int OPPO_FIRST_CALL_TRANSACTION = 1;
    private static final int OPPO_FIRST_MESSAGE = 1;
    private static final int OPPO_MESSAGE_INDEX = 1000;
    private static final int PSW_CALL_TRANSACTION_INDEX = 20000;
    public static final int PSW_FIRST_CALL_TRANSACTION = 20001;
    public static final int PSW_FIRST_MESSAGE = 2001;
    public static final int PSW_GROUP = 2;
    public static final int PSW_LAST_CALL_TRANSACTION = 30000;
    public static final int PSW_LAST_MESSAGE = 3000;
    private static final int PSW_MESSAGE_INDEX = 2000;
    private static final int SCREEN_CAST_CALL_TRANSACTION_INDEX = 30000;
    public static final int SCREEN_CAST_FIRST_CALL_TRANSACTION = 30001;
    public static final int SCREEN_CAST_LAST_CALL_TRANSACTION = 40000;
    private static final String TAG = "OplusCommonConstants";
    public static final int TYPE_BINDER = 1;
    public static final int TYPE_MESSAGE = 2;

    public static boolean checkCodeValid(int code, int type, int group) {
        switch (type) {
            case 1:
                return checkBinderCodeValid(code, group);
            case 2:
                return checkMessageCodeValie(code, group);
            default:
                Log.i(TAG, "UNKNOW type = " + type);
                return false;
        }
    }

    private static boolean checkBinderCodeValid(int code, int group) {
        switch (group) {
            case 1:
                return inside(code, 10001, 20000);
            case 2:
                return inside(code, 20001, 30000);
            default:
                Log.i(TAG, "UNKNOW group = " + group);
                return false;
        }
    }

    private static boolean checkMessageCodeValie(int code, int group) {
        switch (group) {
            case 1:
                return inside(code, 1001, 2000);
            case 2:
                return inside(code, 2001, 3000);
            default:
                Log.i(TAG, "Uknow group = " + group);
                return false;
        }
    }

    private static boolean inside(int code, int first, int last) {
        return code >= first && code <= last;
    }
}
