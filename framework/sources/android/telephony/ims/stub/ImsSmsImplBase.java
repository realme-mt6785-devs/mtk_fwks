package android.telephony.ims.stub;

import android.annotation.SystemApi;
import android.os.RemoteException;
import android.telephony.SmsMessage;
import android.telephony.ims.aidl.IImsSmsListener;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@SystemApi
/* loaded from: classes3.dex */
public class ImsSmsImplBase {
    public static final int DELIVER_STATUS_ERROR_GENERIC = 2;
    public static final int DELIVER_STATUS_ERROR_NO_MEMORY = 3;
    public static final int DELIVER_STATUS_ERROR_REQUEST_NOT_SUPPORTED = 4;
    public static final int DELIVER_STATUS_OK = 1;
    private static final String LOG_TAG = "SmsImplBase";
    public static final int RESULT_NO_NETWORK_ERROR = -1;
    public static final int SEND_STATUS_ERROR = 2;
    public static final int SEND_STATUS_ERROR_FALLBACK = 4;
    public static final int SEND_STATUS_ERROR_RETRY = 3;
    public static final int SEND_STATUS_OK = 1;
    public static final int STATUS_REPORT_STATUS_ERROR = 2;
    public static final int STATUS_REPORT_STATUS_OK = 1;
    private IImsSmsListener mListener;
    private final Object mLock = new Object();

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface DeliverStatusResult {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface SendStatusResult {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface StatusReportResult {
    }

    public final void registerSmsListener(IImsSmsListener listener) {
        synchronized (this.mLock) {
            this.mListener = listener;
        }
    }

    public void sendSms(int token, int messageRef, String format, String smsc, boolean isRetry, byte[] pdu) {
        try {
            onSendSmsResult(token, messageRef, 2, 1);
        } catch (RuntimeException e) {
            Log.e(LOG_TAG, "Can not send sms: " + e.getMessage());
        }
    }

    public void acknowledgeSms(int token, int messageRef, int result) {
        Log.e(LOG_TAG, "acknowledgeSms() not implemented.");
    }

    public void acknowledgeSmsReport(int token, int messageRef, int result) {
        Log.e(LOG_TAG, "acknowledgeSmsReport() not implemented.");
    }

    public final void onSmsReceived(int token, String format, byte[] pdu) throws RuntimeException {
        synchronized (this.mLock) {
            IImsSmsListener iImsSmsListener = this.mListener;
            if (iImsSmsListener != null) {
                try {
                    iImsSmsListener.onSmsReceived(token, format, pdu);
                } catch (RemoteException e) {
                    Log.e(LOG_TAG, "Can not deliver sms: " + e.getMessage());
                    SmsMessage message = SmsMessage.createFromPdu(pdu, format);
                    if (message == null || message.mWrappedSmsMessage == null) {
                        Log.w(LOG_TAG, "onSmsReceived: Invalid pdu entered.");
                        acknowledgeSms(token, 0, 2);
                    } else {
                        acknowledgeSms(token, message.mWrappedSmsMessage.mMessageRef, 2);
                    }
                }
            } else {
                throw new RuntimeException("Feature not ready.");
            }
        }
    }

    public final void onSendSmsResultSuccess(int token, int messageRef) throws RuntimeException {
        synchronized (this.mLock) {
            IImsSmsListener iImsSmsListener = this.mListener;
            if (iImsSmsListener != null) {
                try {
                    iImsSmsListener.onSendSmsResult(token, messageRef, 1, 0, -1);
                } catch (RemoteException e) {
                    e.rethrowFromSystemServer();
                }
            } else {
                throw new RuntimeException("Feature not ready.");
            }
        }
    }

    @Deprecated
    public final void onSendSmsResult(int token, int messageRef, int status, int reason) throws RuntimeException {
        synchronized (this.mLock) {
            IImsSmsListener iImsSmsListener = this.mListener;
            if (iImsSmsListener != null) {
                try {
                    iImsSmsListener.onSendSmsResult(token, messageRef, status, reason, -1);
                } catch (RemoteException e) {
                    e.rethrowFromSystemServer();
                }
            } else {
                throw new RuntimeException("Feature not ready.");
            }
        }
    }

    public final void onSendSmsResultError(int token, int messageRef, int status, int reason, int networkErrorCode) throws RuntimeException {
        synchronized (this.mLock) {
            IImsSmsListener iImsSmsListener = this.mListener;
            if (iImsSmsListener != null) {
                try {
                    iImsSmsListener.onSendSmsResult(token, messageRef, status, reason, networkErrorCode);
                } catch (RemoteException e) {
                    e.rethrowFromSystemServer();
                }
            } else {
                throw new RuntimeException("Feature not ready.");
            }
        }
    }

    @Deprecated
    public final void onSmsStatusReportReceived(int token, int messageRef, String format, byte[] pdu) throws RuntimeException {
        synchronized (this.mLock) {
            IImsSmsListener iImsSmsListener = this.mListener;
            if (iImsSmsListener != null) {
                try {
                    iImsSmsListener.onSmsStatusReportReceived(token, format, pdu);
                } catch (RemoteException e) {
                    Log.e(LOG_TAG, "Can not process sms status report: " + e.getMessage());
                    acknowledgeSmsReport(token, messageRef, 2);
                }
            } else {
                throw new RuntimeException("Feature not ready.");
            }
        }
    }

    public final void onSmsStatusReportReceived(int token, String format, byte[] pdu) throws RuntimeException {
        synchronized (this.mLock) {
            IImsSmsListener iImsSmsListener = this.mListener;
            if (iImsSmsListener != null) {
                try {
                    iImsSmsListener.onSmsStatusReportReceived(token, format, pdu);
                } catch (RemoteException e) {
                    Log.e(LOG_TAG, "Can not process sms status report: " + e.getMessage());
                    SmsMessage message = SmsMessage.createFromPdu(pdu, format);
                    if (message == null || message.mWrappedSmsMessage == null) {
                        Log.w(LOG_TAG, "onSmsStatusReportReceived: Invalid pdu entered.");
                        acknowledgeSmsReport(token, 0, 2);
                    } else {
                        acknowledgeSmsReport(token, message.mWrappedSmsMessage.mMessageRef, 2);
                    }
                }
            } else {
                throw new RuntimeException("Feature not ready.");
            }
        }
    }

    public String getSmsFormat() {
        return "3gpp";
    }

    public void onReady() {
    }
}