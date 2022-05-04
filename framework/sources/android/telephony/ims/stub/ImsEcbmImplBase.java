package android.telephony.ims.stub;

import android.annotation.SystemApi;
import android.os.RemoteException;
import android.util.Log;
import com.android.ims.internal.IImsEcbm;
import com.android.ims.internal.IImsEcbmListener;
import java.util.Objects;
@SystemApi
/* loaded from: classes3.dex */
public class ImsEcbmImplBase {
    private static final String TAG = "ImsEcbmImplBase";
    private IImsEcbmListener mListener;
    private final Object mLock = new Object();
    private final IImsEcbm mImsEcbm = new IImsEcbm.Stub() { // from class: android.telephony.ims.stub.ImsEcbmImplBase.1
        @Override // com.android.ims.internal.IImsEcbm
        public void setListener(IImsEcbmListener listener) {
            synchronized (ImsEcbmImplBase.this.mLock) {
                if (ImsEcbmImplBase.this.mListener != null && !ImsEcbmImplBase.this.mListener.asBinder().isBinderAlive()) {
                    Log.w(ImsEcbmImplBase.TAG, "setListener: discarding dead Binder");
                    ImsEcbmImplBase.this.mListener = null;
                }
                if (ImsEcbmImplBase.this.mListener == null || listener == null || !Objects.equals(ImsEcbmImplBase.this.mListener.asBinder(), listener.asBinder())) {
                    if (listener == null) {
                        ImsEcbmImplBase.this.mListener = null;
                    } else if (listener == null || ImsEcbmImplBase.this.mListener != null) {
                        Log.w(ImsEcbmImplBase.TAG, "setListener is being called when there is already an active listener");
                        ImsEcbmImplBase.this.mListener = listener;
                    } else {
                        ImsEcbmImplBase.this.mListener = listener;
                    }
                }
            }
        }

        @Override // com.android.ims.internal.IImsEcbm
        public void exitEmergencyCallbackMode() {
            ImsEcbmImplBase.this.exitEmergencyCallbackMode();
        }
    };

    public IImsEcbm getImsEcbm() {
        return this.mImsEcbm;
    }

    public void exitEmergencyCallbackMode() {
        Log.d(TAG, "exitEmergencyCallbackMode() not implemented");
    }

    public final void enteredEcbm() {
        IImsEcbmListener listener;
        Log.d(TAG, "Entered ECBM.");
        synchronized (this.mLock) {
            listener = this.mListener;
        }
        if (listener != null) {
            try {
                listener.enteredECBM();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public final void exitedEcbm() {
        IImsEcbmListener listener;
        Log.d(TAG, "Exited ECBM.");
        synchronized (this.mLock) {
            listener = this.mListener;
        }
        if (listener != null) {
            try {
                listener.exitedECBM();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
