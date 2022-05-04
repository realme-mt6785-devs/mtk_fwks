package com.oplus.eventhub.sdk.aidl;
/* loaded from: classes4.dex */
public class EventFountainRegisterCode {
    public static final int BINDER_TRANSACTION_ERROR = 128;
    public static final int EVENT_NOT_AVAILABLE = 2;
    public static final int INVALID_PARAMETERS = 16;
    public static final int NOT_IMPLEMENTED = 0;
    public static final int OPLUS_VERSION_NOT_SUPPORT = 8;
    public static final int PID_REGISTER_LIMITED = 4;
    public static final int REGISTER_SUCCESS = 1;
    public static final int SERVER_INTERVAL_ERROR = 32;
    public static final int UNSUPPORTED_PARAMETER = 64;

    public static String resultCodeToString(int resultCode) {
        switch (resultCode) {
            case 1:
                return "REGISTER_SUCCESS";
            case 2:
                return "EVENT_NOT_AVAILABLE";
            case 4:
                return "PID_REGISTER_LIMITED";
            case 8:
                return "OPLUSOS_VERSION_NOT_SUPPORT";
            case 16:
                return "INVALID_PARAMETERS";
            case 32:
                return "SERVER_INTERVAL_ERROR";
            case 64:
                return "UNSUPPORTED_PARAMETER";
            case 128:
                return "BINDER_TRANSACTION_ERROR";
            default:
                return "UNKNOWN_RESULT_CODE";
        }
    }
}
