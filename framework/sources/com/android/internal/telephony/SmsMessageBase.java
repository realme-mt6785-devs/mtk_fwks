package com.android.internal.telephony;

import android.telephony.Rlog;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.util.Patterns;
import com.android.internal.telephony.GsmAlphabet;
import com.android.internal.telephony.SmsConstants;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes4.dex */
public abstract class SmsMessageBase {
    public static final Pattern NAME_ADDR_EMAIL_PATTERN = Pattern.compile("\\s*(\"[^\"]*\"|[^<>\"]+)\\s*<([^<>]+)>\\s*");
    protected String mEmailBody;
    protected String mEmailFrom;
    protected boolean mIsEmail;
    protected boolean mIsMwi;
    protected String mMessageBody;
    public int mMessageRef;
    protected boolean mMwiDontStore;
    protected boolean mMwiSense;
    public SmsAddress mOriginatingAddress;
    protected byte[] mPdu;
    protected String mPseudoSubject;
    protected SmsAddress mRecipientAddress;
    protected String mScAddress;
    protected long mScTimeMillis;
    protected byte[] mUserData;
    protected SmsHeader mUserDataHeader;
    public int mStatusOnIcc = -1;
    public int mIndexOnIcc = -1;

    public abstract SmsConstants.MessageClass getMessageClass();

    public abstract int getProtocolIdentifier();

    public abstract int getStatus();

    public abstract boolean isCphsMwiMessage();

    public abstract boolean isMWIClearMessage();

    public abstract boolean isMWISetMessage();

    public abstract boolean isMwiDontStore();

    public abstract boolean isReplace();

    public abstract boolean isReplyPathPresent();

    public abstract boolean isStatusReportMessage();

    /* loaded from: classes4.dex */
    public static abstract class SubmitPduBase {
        public byte[] encodedMessage;
        public byte[] encodedScAddress;

        public String toString() {
            return "SubmitPdu: encodedScAddress = " + Arrays.toString(this.encodedScAddress) + ", encodedMessage = " + Arrays.toString(this.encodedMessage);
        }
    }

    public String getServiceCenterAddress() {
        return this.mScAddress;
    }

    public String getOriginatingAddress() {
        SmsAddress smsAddress = this.mOriginatingAddress;
        if (smsAddress == null) {
            return null;
        }
        return smsAddress.getAddressString();
    }

    public String getDisplayOriginatingAddress() {
        if (this.mIsEmail) {
            return this.mEmailFrom;
        }
        return getOriginatingAddress();
    }

    public String getMessageBody() {
        return this.mMessageBody;
    }

    public String getDisplayMessageBody() {
        if (this.mIsEmail) {
            return this.mEmailBody;
        }
        return getMessageBody();
    }

    public String getPseudoSubject() {
        String str = this.mPseudoSubject;
        return str == null ? "" : str;
    }

    public long getTimestampMillis() {
        return this.mScTimeMillis;
    }

    public boolean isEmail() {
        return this.mIsEmail;
    }

    public String getEmailBody() {
        return this.mEmailBody;
    }

    public String getEmailFrom() {
        return this.mEmailFrom;
    }

    public byte[] getUserData() {
        return this.mUserData;
    }

    public SmsHeader getUserDataHeader() {
        return this.mUserDataHeader;
    }

    public byte[] getPdu() {
        return this.mPdu;
    }

    public int getStatusOnIcc() {
        return this.mStatusOnIcc;
    }

    public int getIndexOnIcc() {
        return this.mIndexOnIcc;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void parseMessageBody() {
        SmsAddress smsAddress = this.mOriginatingAddress;
        if (smsAddress != null && smsAddress.couldBeEmailGateway() && !isReplace()) {
            extractEmailAddressFromMessageBody();
        }
    }

    private static String extractAddrSpec(String messageHeader) {
        Matcher match = NAME_ADDR_EMAIL_PATTERN.matcher(messageHeader);
        if (match.matches()) {
            return match.group(2);
        }
        return messageHeader;
    }

    public static boolean isEmailAddress(String messageHeader) {
        if (TextUtils.isEmpty(messageHeader)) {
            return false;
        }
        String s = extractAddrSpec(messageHeader);
        Matcher match = Patterns.EMAIL_ADDRESS.matcher(s);
        return match.matches();
    }

    protected void extractEmailAddressFromMessageBody() {
        String[] parts = this.mMessageBody.split("( /)|( )", 2);
        if (parts.length >= 2) {
            String str = parts[0];
            this.mEmailFrom = str;
            this.mEmailBody = parts[1];
            this.mIsEmail = isEmailAddress(str);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:?, code lost:
        return r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int findNextUnicodePosition(int r4, int r5, java.lang.CharSequence r6) {
        /*
            int r0 = r5 / 2
            int r0 = r0 + r4
            int r1 = r6.length()
            int r0 = java.lang.Math.min(r0, r1)
            int r1 = r6.length()
            if (r0 >= r1) goto L_0x0055
            java.text.BreakIterator r1 = java.text.BreakIterator.getCharacterInstance()
            java.lang.String r2 = r6.toString()
            r1.setText(r2)
            boolean r2 = r1.isBoundary(r0)
            if (r2 != 0) goto L_0x0055
            int r2 = r1.preceding(r0)
        L_0x0026:
            int r3 = r2 + 4
            if (r3 > r0) goto L_0x0043
            int r3 = java.lang.Character.codePointAt(r6, r2)
            boolean r3 = isRegionalIndicatorSymbol(r3)
            if (r3 == 0) goto L_0x0043
            int r3 = r2 + 2
            int r3 = java.lang.Character.codePointAt(r6, r3)
            boolean r3 = isRegionalIndicatorSymbol(r3)
            if (r3 == 0) goto L_0x0043
            int r2 = r2 + 4
            goto L_0x0026
        L_0x0043:
            if (r2 <= r4) goto L_0x0047
            r0 = r2
            goto L_0x0055
        L_0x0047:
            int r3 = r0 + (-1)
            char r3 = r6.charAt(r3)
            boolean r3 = java.lang.Character.isHighSurrogate(r3)
            if (r3 == 0) goto L_0x0055
            int r0 = r0 + (-1)
        L_0x0055:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.telephony.SmsMessageBase.findNextUnicodePosition(int, int, java.lang.CharSequence):int");
    }

    private static boolean isRegionalIndicatorSymbol(int codePoint) {
        return 127462 <= codePoint && codePoint <= 127487;
    }

    public static GsmAlphabet.TextEncodingDetails calcUnicodeEncodingDetails(CharSequence msgBody) {
        GsmAlphabet.TextEncodingDetails ted = new GsmAlphabet.TextEncodingDetails();
        int octets = msgBody.length() * 2;
        ted.codeUnitSize = 3;
        ted.codeUnitCount = msgBody.length();
        if (octets > 140) {
            int maxUserDataBytesWithHeader = 134;
            if (!SmsMessage.hasEmsSupport() && octets <= (134 - 2) * 9) {
                maxUserDataBytesWithHeader = 134 - 2;
            }
            int pos = 0;
            int msgCount = 0;
            while (pos < msgBody.length()) {
                int nextPos = findNextUnicodePosition(pos, maxUserDataBytesWithHeader, msgBody);
                if (nextPos <= pos || nextPos > msgBody.length()) {
                    Rlog.e("SmsMessageBase", "calcUnicodeEncodingDetails failed (" + pos + " >= " + nextPos + " or " + nextPos + " >= " + msgBody.length() + ")");
                    break;
                }
                if (nextPos == msgBody.length()) {
                    ted.codeUnitsRemaining = ((maxUserDataBytesWithHeader / 2) + pos) - msgBody.length();
                }
                pos = nextPos;
                msgCount++;
            }
            ted.msgCount = msgCount;
        } else {
            ted.msgCount = 1;
            ted.codeUnitsRemaining = (140 - octets) / 2;
        }
        return ted;
    }

    public String getRecipientAddress() {
        SmsAddress smsAddress = this.mRecipientAddress;
        if (smsAddress == null) {
            return null;
        }
        return smsAddress.getAddressString();
    }
}
