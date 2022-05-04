package android.telephony.ims.stub;

import android.annotation.SystemApi;
import android.os.Bundle;
import android.os.RemoteException;
import android.telephony.ims.ImsUtListener;
import android.util.Log;
import com.android.ims.internal.IImsUt;
import com.android.ims.internal.IImsUtListener;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;
@SystemApi
/* loaded from: classes3.dex */
public class ImsUtImplBase {
    public static final int CALL_BARRING_ALL = 7;
    public static final int CALL_BARRING_ALL_INCOMING = 1;
    public static final int CALL_BARRING_ALL_OUTGOING = 2;
    public static final int CALL_BARRING_ANONYMOUS_INCOMING = 6;
    public static final int CALL_BARRING_INCOMING_ALL_SERVICES = 9;
    public static final int CALL_BARRING_OUTGOING_ALL_SERVICES = 8;
    public static final int CALL_BARRING_OUTGOING_INTL = 3;
    public static final int CALL_BARRING_OUTGOING_INTL_EXCL_HOME = 4;
    public static final int CALL_BARRING_SPECIFIC_INCOMING_CALLS = 10;
    public static final int CALL_BLOCKING_INCOMING_WHEN_ROAMING = 5;
    public static final int INVALID_RESULT = -1;
    private static final String TAG = "ImsUtImplBase";
    private final IImsUt.Stub mServiceImpl = new IImsUt.Stub() { // from class: android.telephony.ims.stub.ImsUtImplBase.1
        private final Object mLock = new Object();
        private ImsUtListener mUtListener;

        @Override // com.android.ims.internal.IImsUt
        public void close() throws RemoteException {
            ImsUtImplBase.this.close();
        }

        @Override // com.android.ims.internal.IImsUt
        public int queryCallBarring(int cbType) throws RemoteException {
            return ImsUtImplBase.this.queryCallBarring(cbType);
        }

        @Override // com.android.ims.internal.IImsUt
        public int queryCallForward(int condition, String number) throws RemoteException {
            return ImsUtImplBase.this.queryCallForward(condition, number);
        }

        @Override // com.android.ims.internal.IImsUt
        public int queryCallWaiting() throws RemoteException {
            return ImsUtImplBase.this.queryCallWaiting();
        }

        @Override // com.android.ims.internal.IImsUt
        public int queryCLIR() throws RemoteException {
            return ImsUtImplBase.this.queryCLIR();
        }

        @Override // com.android.ims.internal.IImsUt
        public int queryCLIP() throws RemoteException {
            return ImsUtImplBase.this.queryCLIP();
        }

        @Override // com.android.ims.internal.IImsUt
        public int queryCOLR() throws RemoteException {
            return ImsUtImplBase.this.queryCOLR();
        }

        @Override // com.android.ims.internal.IImsUt
        public int queryCOLP() throws RemoteException {
            return ImsUtImplBase.this.queryCOLP();
        }

        @Override // com.android.ims.internal.IImsUt
        public int transact(Bundle ssInfo) throws RemoteException {
            return ImsUtImplBase.this.transact(ssInfo);
        }

        @Override // com.android.ims.internal.IImsUt
        public int updateCallBarring(int cbType, int action, String[] barrList) throws RemoteException {
            return ImsUtImplBase.this.updateCallBarring(cbType, action, barrList);
        }

        @Override // com.android.ims.internal.IImsUt
        public int updateCallForward(int action, int condition, String number, int serviceClass, int timeSeconds) throws RemoteException {
            return ImsUtImplBase.this.updateCallForward(action, condition, number, serviceClass, timeSeconds);
        }

        @Override // com.android.ims.internal.IImsUt
        public int updateCallWaiting(boolean enable, int serviceClass) throws RemoteException {
            return ImsUtImplBase.this.updateCallWaiting(enable, serviceClass);
        }

        @Override // com.android.ims.internal.IImsUt
        public int updateCLIR(int clirMode) throws RemoteException {
            return ImsUtImplBase.this.updateCLIR(clirMode);
        }

        @Override // com.android.ims.internal.IImsUt
        public int updateCLIP(boolean enable) throws RemoteException {
            return ImsUtImplBase.this.updateCLIP(enable);
        }

        @Override // com.android.ims.internal.IImsUt
        public int updateCOLR(int presentation) throws RemoteException {
            return ImsUtImplBase.this.updateCOLR(presentation);
        }

        @Override // com.android.ims.internal.IImsUt
        public int updateCOLP(boolean enable) throws RemoteException {
            return ImsUtImplBase.this.updateCOLP(enable);
        }

        @Override // com.android.ims.internal.IImsUt
        public void setListener(IImsUtListener listener) throws RemoteException {
            synchronized (this.mLock) {
                ImsUtListener imsUtListener = this.mUtListener;
                if (imsUtListener != null && !imsUtListener.getListenerInterface().asBinder().isBinderAlive()) {
                    Log.w(ImsUtImplBase.TAG, "setListener: discarding dead Binder");
                    this.mUtListener = null;
                }
                ImsUtListener imsUtListener2 = this.mUtListener;
                if (imsUtListener2 == null || listener == null || !Objects.equals(imsUtListener2.getListenerInterface().asBinder(), listener.asBinder())) {
                    if (listener == null) {
                        this.mUtListener = null;
                    } else if (listener == null || this.mUtListener != null) {
                        Log.w(ImsUtImplBase.TAG, "setListener is being called when there is already an active listener");
                        this.mUtListener = new ImsUtListener(listener);
                    } else {
                        this.mUtListener = new ImsUtListener(listener);
                    }
                    ImsUtImplBase.this.setListener(this.mUtListener);
                }
            }
        }

        @Override // com.android.ims.internal.IImsUt
        public int queryCallBarringForServiceClass(int cbType, int serviceClass) throws RemoteException {
            return ImsUtImplBase.this.queryCallBarringForServiceClass(cbType, serviceClass);
        }

        @Override // com.android.ims.internal.IImsUt
        public int updateCallBarringForServiceClass(int cbType, int action, String[] barrList, int serviceClass) throws RemoteException {
            return ImsUtImplBase.this.updateCallBarringForServiceClass(cbType, action, barrList, serviceClass);
        }

        @Override // com.android.ims.internal.IImsUt
        public int updateCallBarringWithPassword(int cbType, int action, String[] barrList, int serviceClass, String password) throws RemoteException {
            return ImsUtImplBase.this.updateCallBarringWithPassword(cbType, action, barrList, serviceClass, password);
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface CallBarringMode {
    }

    public void close() {
    }

    public int queryCallBarring(int cbType) {
        return -1;
    }

    public int queryCallBarringForServiceClass(int cbType, int serviceClass) {
        return -1;
    }

    public int queryCallForward(int condition, String number) {
        return -1;
    }

    public int queryCallWaiting() {
        return -1;
    }

    public int queryCLIR() {
        return queryClir();
    }

    public int queryCLIP() {
        return queryClip();
    }

    public int queryCOLR() {
        return queryColr();
    }

    public int queryCOLP() {
        return queryColp();
    }

    public int queryClir() {
        return -1;
    }

    public int queryClip() {
        return -1;
    }

    public int queryColr() {
        return -1;
    }

    public int queryColp() {
        return -1;
    }

    public int transact(Bundle ssInfo) {
        return -1;
    }

    public int updateCallBarring(int cbType, int action, String[] barrList) {
        return -1;
    }

    public int updateCallBarringForServiceClass(int cbType, int action, String[] barrList, int serviceClass) {
        return -1;
    }

    public int updateCallBarringWithPassword(int cbType, int action, String[] barrList, int serviceClass, String password) {
        return -1;
    }

    public int updateCallForward(int action, int condition, String number, int serviceClass, int timeSeconds) {
        return 0;
    }

    public int updateCallWaiting(boolean enable, int serviceClass) {
        return -1;
    }

    public int updateCLIR(int clirMode) {
        return updateClir(clirMode);
    }

    public int updateCLIP(boolean enable) {
        return updateClip(enable);
    }

    public int updateCOLR(int presentation) {
        return updateColr(presentation);
    }

    public int updateCOLP(boolean enable) {
        return updateColp(enable);
    }

    public int updateClir(int clirMode) {
        return -1;
    }

    public int updateClip(boolean enable) {
        return -1;
    }

    public int updateColr(int presentation) {
        return -1;
    }

    public int updateColp(boolean enable) {
        return -1;
    }

    public void setListener(ImsUtListener listener) {
    }

    public IImsUt getInterface() {
        return this.mServiceImpl;
    }
}
