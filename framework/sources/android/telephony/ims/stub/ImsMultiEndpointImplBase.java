package android.telephony.ims.stub;

import android.annotation.SystemApi;
import android.os.RemoteException;
import android.telephony.ims.ImsExternalCallState;
import android.util.Log;
import com.android.ims.internal.IImsExternalCallStateListener;
import com.android.ims.internal.IImsMultiEndpoint;
import java.util.List;
import java.util.Objects;
@SystemApi
/* loaded from: classes3.dex */
public class ImsMultiEndpointImplBase {
    private static final String TAG = "MultiEndpointImplBase";
    private IImsExternalCallStateListener mListener;
    private final Object mLock = new Object();
    private final IImsMultiEndpoint mImsMultiEndpoint = new IImsMultiEndpoint.Stub() { // from class: android.telephony.ims.stub.ImsMultiEndpointImplBase.1
        @Override // com.android.ims.internal.IImsMultiEndpoint
        public void setListener(IImsExternalCallStateListener listener) throws RemoteException {
            synchronized (ImsMultiEndpointImplBase.this.mLock) {
                if (ImsMultiEndpointImplBase.this.mListener != null && !ImsMultiEndpointImplBase.this.mListener.asBinder().isBinderAlive()) {
                    Log.w(ImsMultiEndpointImplBase.TAG, "setListener: discarding dead Binder");
                    ImsMultiEndpointImplBase.this.mListener = null;
                }
                if (ImsMultiEndpointImplBase.this.mListener == null || listener == null || !Objects.equals(ImsMultiEndpointImplBase.this.mListener.asBinder(), listener.asBinder())) {
                    if (listener == null) {
                        ImsMultiEndpointImplBase.this.mListener = null;
                    } else if (listener == null || ImsMultiEndpointImplBase.this.mListener != null) {
                        Log.w(ImsMultiEndpointImplBase.TAG, "setListener is being called when there is already an active listener");
                        ImsMultiEndpointImplBase.this.mListener = listener;
                    } else {
                        ImsMultiEndpointImplBase.this.mListener = listener;
                    }
                }
            }
        }

        @Override // com.android.ims.internal.IImsMultiEndpoint
        public void requestImsExternalCallStateInfo() throws RemoteException {
            ImsMultiEndpointImplBase.this.requestImsExternalCallStateInfo();
        }
    };

    public IImsMultiEndpoint getIImsMultiEndpoint() {
        return this.mImsMultiEndpoint;
    }

    public final void onImsExternalCallStateUpdate(List<ImsExternalCallState> externalCallDialogs) {
        IImsExternalCallStateListener listener;
        Log.d(TAG, "ims external call state update triggered.");
        synchronized (this.mLock) {
            listener = this.mListener;
        }
        if (listener != null) {
            try {
                listener.onImsExternalCallStateUpdate(externalCallDialogs);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void requestImsExternalCallStateInfo() {
        Log.d(TAG, "requestImsExternalCallStateInfo() not implemented");
    }
}
