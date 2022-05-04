package android.telephony.data;

import android.annotation.SystemApi;
import android.os.RemoteException;
import com.android.telephony.Rlog;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
@SystemApi
/* loaded from: classes3.dex */
public class DataServiceCallback {
    private static final boolean DBG = true;
    public static final int RESULT_ERROR_BUSY = 3;
    public static final int RESULT_ERROR_ILLEGAL_STATE = 4;
    public static final int RESULT_ERROR_INVALID_ARG = 2;
    public static final int RESULT_ERROR_TEMPORARILY_UNAVAILABLE = 5;
    public static final int RESULT_ERROR_UNSUPPORTED = 1;
    public static final int RESULT_SUCCESS = 0;
    private static final String TAG = DataServiceCallback.class.getSimpleName();
    private final IDataServiceCallback mCallback;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface ResultCode {
    }

    public DataServiceCallback(IDataServiceCallback callback) {
        this.mCallback = callback;
    }

    public void onSetupDataCallComplete(int result, DataCallResponse response) {
        if (this.mCallback != null) {
            try {
                Rlog.d(TAG, "onSetupDataCallComplete");
                this.mCallback.onSetupDataCallComplete(result, response);
            } catch (RemoteException e) {
                Rlog.e(TAG, "Failed to onSetupDataCallComplete on the remote");
            }
        } else {
            Rlog.e(TAG, "onSetupDataCallComplete: callback is null!");
        }
    }

    public void onDeactivateDataCallComplete(int result) {
        if (this.mCallback != null) {
            try {
                Rlog.d(TAG, "onDeactivateDataCallComplete");
                this.mCallback.onDeactivateDataCallComplete(result);
            } catch (RemoteException e) {
                Rlog.e(TAG, "Failed to onDeactivateDataCallComplete on the remote");
            }
        } else {
            Rlog.e(TAG, "onDeactivateDataCallComplete: callback is null!");
        }
    }

    public void onSetInitialAttachApnComplete(int result) {
        IDataServiceCallback iDataServiceCallback = this.mCallback;
        if (iDataServiceCallback != null) {
            try {
                iDataServiceCallback.onSetInitialAttachApnComplete(result);
            } catch (RemoteException e) {
                Rlog.e(TAG, "Failed to onSetInitialAttachApnComplete on the remote");
            }
        } else {
            Rlog.e(TAG, "onSetInitialAttachApnComplete: callback is null!");
        }
    }

    public void onSetDataProfileComplete(int result) {
        IDataServiceCallback iDataServiceCallback = this.mCallback;
        if (iDataServiceCallback != null) {
            try {
                iDataServiceCallback.onSetDataProfileComplete(result);
            } catch (RemoteException e) {
                Rlog.e(TAG, "Failed to onSetDataProfileComplete on the remote");
            }
        } else {
            Rlog.e(TAG, "onSetDataProfileComplete: callback is null!");
        }
    }

    public void onRequestDataCallListComplete(int result, List<DataCallResponse> dataCallList) {
        IDataServiceCallback iDataServiceCallback = this.mCallback;
        if (iDataServiceCallback != null) {
            try {
                iDataServiceCallback.onRequestDataCallListComplete(result, dataCallList);
            } catch (RemoteException e) {
                Rlog.e(TAG, "Failed to onRequestDataCallListComplete on the remote");
            }
        } else {
            Rlog.e(TAG, "onRequestDataCallListComplete: callback is null!");
        }
    }

    public void onDataCallListChanged(List<DataCallResponse> dataCallList) {
        if (this.mCallback != null) {
            try {
                Rlog.d(TAG, "onDataCallListChanged");
                this.mCallback.onDataCallListChanged(dataCallList);
            } catch (RemoteException e) {
                Rlog.e(TAG, "Failed to onDataCallListChanged on the remote");
            }
        } else {
            Rlog.e(TAG, "onDataCallListChanged: callback is null!");
        }
    }

    public void onHandoverStarted(int result) {
        if (this.mCallback != null) {
            try {
                Rlog.d(TAG, "onHandoverStarted");
                this.mCallback.onHandoverStarted(result);
            } catch (RemoteException e) {
                Rlog.e(TAG, "Failed to onHandoverStarted on the remote");
            }
        } else {
            Rlog.e(TAG, "onHandoverStarted: callback is null!");
        }
    }

    public void onHandoverCancelled(int result) {
        if (this.mCallback != null) {
            try {
                Rlog.d(TAG, "onHandoverCancelled");
                this.mCallback.onHandoverCancelled(result);
            } catch (RemoteException e) {
                Rlog.e(TAG, "Failed to onHandoverCancelled on the remote");
            }
        } else {
            Rlog.e(TAG, "onHandoverCancelled: callback is null!");
        }
    }

    public static String resultCodeToString(int resultCode) {
        switch (resultCode) {
            case 0:
                return "RESULT_SUCCESS";
            case 1:
                return "RESULT_ERROR_UNSUPPORTED";
            case 2:
                return "RESULT_ERROR_INVALID_ARG";
            case 3:
                return "RESULT_ERROR_BUSY";
            case 4:
                return "RESULT_ERROR_ILLEGAL_STATE";
            default:
                return "Missing case for result code=" + resultCode;
        }
    }

    public void onApnUnthrottled(String apn) {
        if (this.mCallback != null) {
            try {
                Rlog.d(TAG, "onApnUnthrottled");
                this.mCallback.onApnUnthrottled(apn);
            } catch (RemoteException e) {
                Rlog.e(TAG, "onApnUnthrottled: remote exception", e);
            }
        } else {
            Rlog.e(TAG, "onApnUnthrottled: callback is null!");
        }
    }
}
