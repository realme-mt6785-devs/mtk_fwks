package com.android.internal.telephony.gsm;

import com.android.internal.telephony.gsm.SmsMessage;
/* loaded from: classes4.dex */
public interface IOplusSmsMessageGsmExt {
    default SmsMessage.SubmitPdu oemGetSubmitPdu(String scAddress, String destinationAddress, int destinationPort, byte[] data, boolean statusReportRequested) {
        return null;
    }

    default String getUserDataOem8bit(byte[] mUserData, byte[] mPdu, int mCur, int byteCount) {
        return null;
    }

    default boolean isEnable8BitMtSms() {
        return false;
    }

    default Boolean isAmlDataMessage(byte[] data) {
        return false;
    }
}
