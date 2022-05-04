package android.telephony.ims.stub;

import android.annotation.SystemApi;
import android.net.Uri;
import android.os.RemoteException;
import android.telephony.ims.ImsReasonInfo;
import android.telephony.ims.ImsRegistrationAttributes;
import android.telephony.ims.aidl.IImsRegistration;
import android.telephony.ims.aidl.IImsRegistrationCallback;
import android.util.Log;
import com.android.internal.telephony.util.RemoteCallbackListExt;
import com.android.internal.util.ArrayUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.function.Consumer;
@SystemApi
/* loaded from: classes3.dex */
public class ImsRegistrationImplBase {
    private static final String LOG_TAG = "ImsRegistrationImplBase";
    private static final int REGISTRATION_STATE_UNKNOWN = -1;
    public static final int REGISTRATION_TECH_CROSS_SIM = 2;
    public static final int REGISTRATION_TECH_IWLAN = 1;
    public static final int REGISTRATION_TECH_LTE = 0;
    public static final int REGISTRATION_TECH_NONE = -1;
    public static final int REGISTRATION_TECH_NR = 3;
    private ImsRegistrationAttributes mRegistrationAttributes;
    private final IImsRegistration mBinder = new IImsRegistration.Stub() { // from class: android.telephony.ims.stub.ImsRegistrationImplBase.1
        @Override // android.telephony.ims.aidl.IImsRegistration
        public int getRegistrationTechnology() throws RemoteException {
            int registrationTechnology;
            synchronized (ImsRegistrationImplBase.this.mLock) {
                registrationTechnology = ImsRegistrationImplBase.this.mRegistrationAttributes == null ? -1 : ImsRegistrationImplBase.this.mRegistrationAttributes.getRegistrationTechnology();
            }
            return registrationTechnology;
        }

        @Override // android.telephony.ims.aidl.IImsRegistration
        public void addRegistrationCallback(IImsRegistrationCallback c) throws RemoteException {
            ImsRegistrationImplBase.this.addRegistrationCallback(c);
        }

        @Override // android.telephony.ims.aidl.IImsRegistration
        public void removeRegistrationCallback(IImsRegistrationCallback c) throws RemoteException {
            ImsRegistrationImplBase.this.removeRegistrationCallback(c);
        }

        @Override // android.telephony.ims.aidl.IImsRegistration
        public void triggerFullNetworkRegistration(int sipCode, String sipReason) {
            ImsRegistrationImplBase.this.triggerFullNetworkRegistration(sipCode, sipReason);
        }

        @Override // android.telephony.ims.aidl.IImsRegistration
        public void triggerUpdateSipDelegateRegistration() {
            ImsRegistrationImplBase.this.updateSipDelegateRegistration();
        }

        @Override // android.telephony.ims.aidl.IImsRegistration
        public void triggerSipDelegateDeregistration() {
            ImsRegistrationImplBase.this.triggerSipDelegateDeregistration();
        }
    };
    private final RemoteCallbackListExt<IImsRegistrationCallback> mCallbacks = new RemoteCallbackListExt<>();
    private final Object mLock = new Object();
    private int mRegistrationState = -1;
    private ImsReasonInfo mLastDisconnectCause = new ImsReasonInfo();
    private Uri[] mUris = new Uri[0];
    private boolean mUrisSet = false;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface ImsRegistrationTech {
    }

    public final IImsRegistration getBinder() {
        return this.mBinder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addRegistrationCallback(IImsRegistrationCallback c) throws RemoteException {
        this.mCallbacks.register(c);
        updateNewCallbackWithState(c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeRegistrationCallback(IImsRegistrationCallback c) {
        this.mCallbacks.unregister(c);
    }

    public void updateSipDelegateRegistration() {
    }

    public void triggerSipDelegateDeregistration() {
    }

    public void triggerFullNetworkRegistration(int sipCode, String sipReason) {
    }

    public final void onRegistered(int imsRadioTech) {
        onRegistered(new ImsRegistrationAttributes.Builder(imsRadioTech).build());
    }

    public final void onRegistered(final ImsRegistrationAttributes attributes) {
        updateToState(attributes, 2);
        this.mCallbacks.broadcastAction(new Consumer() { // from class: android.telephony.ims.stub.ImsRegistrationImplBase$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ImsRegistrationImplBase.lambda$onRegistered$0(ImsRegistrationAttributes.this, (IImsRegistrationCallback) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onRegistered$0(ImsRegistrationAttributes attributes, IImsRegistrationCallback c) {
        try {
            c.onRegistered(attributes);
        } catch (RemoteException e) {
            Log.w(LOG_TAG, e + "onRegistered(int, Set) - Skipping callback.");
        }
    }

    public final void onRegistering(int imsRadioTech) {
        onRegistering(new ImsRegistrationAttributes.Builder(imsRadioTech).build());
    }

    public final void onRegistering(final ImsRegistrationAttributes attributes) {
        updateToState(attributes, 1);
        this.mCallbacks.broadcastAction(new Consumer() { // from class: android.telephony.ims.stub.ImsRegistrationImplBase$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ImsRegistrationImplBase.lambda$onRegistering$1(ImsRegistrationAttributes.this, (IImsRegistrationCallback) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onRegistering$1(ImsRegistrationAttributes attributes, IImsRegistrationCallback c) {
        try {
            c.onRegistering(attributes);
        } catch (RemoteException e) {
            Log.w(LOG_TAG, e + "onRegistering(int, Set) - Skipping callback.");
        }
    }

    public final void onDeregistered(ImsReasonInfo info) {
        updateToDisconnectedState(info);
        final ImsReasonInfo reasonInfo = info != null ? info : new ImsReasonInfo();
        this.mCallbacks.broadcastAction(new Consumer() { // from class: android.telephony.ims.stub.ImsRegistrationImplBase$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ImsRegistrationImplBase.lambda$onDeregistered$2(ImsReasonInfo.this, (IImsRegistrationCallback) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onDeregistered$2(ImsReasonInfo reasonInfo, IImsRegistrationCallback c) {
        try {
            c.onDeregistered(reasonInfo);
        } catch (RemoteException e) {
            Log.w(LOG_TAG, e + "onDeregistered() - Skipping callback.");
        }
    }

    public final void onTechnologyChangeFailed(final int imsRadioTech, ImsReasonInfo info) {
        final ImsReasonInfo reasonInfo = info != null ? info : new ImsReasonInfo();
        this.mCallbacks.broadcastAction(new Consumer() { // from class: android.telephony.ims.stub.ImsRegistrationImplBase$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ImsRegistrationImplBase.lambda$onTechnologyChangeFailed$3(imsRadioTech, reasonInfo, (IImsRegistrationCallback) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onTechnologyChangeFailed$3(int imsRadioTech, ImsReasonInfo reasonInfo, IImsRegistrationCallback c) {
        try {
            c.onTechnologyChangeFailed(imsRadioTech, reasonInfo);
        } catch (RemoteException e) {
            Log.w(LOG_TAG, e + "onTechnologyChangeFailed() - Skipping callback.");
        }
    }

    public final void onSubscriberAssociatedUriChanged(final Uri[] uris) {
        synchronized (this.mLock) {
            this.mUris = (Uri[]) ArrayUtils.cloneOrNull(uris);
            this.mUrisSet = true;
        }
        this.mCallbacks.broadcastAction(new Consumer() { // from class: android.telephony.ims.stub.ImsRegistrationImplBase$$ExternalSyntheticLambda4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ImsRegistrationImplBase.this.lambda$onSubscriberAssociatedUriChanged$4$ImsRegistrationImplBase(uris, (IImsRegistrationCallback) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onSubscriberAssociatedUriChanged */
    public void lambda$onSubscriberAssociatedUriChanged$4$ImsRegistrationImplBase(IImsRegistrationCallback callback, Uri[] uris) {
        try {
            callback.onSubscriberAssociatedUriChanged(uris);
        } catch (RemoteException e) {
            Log.w(LOG_TAG, e + "onSubscriberAssociatedUriChanged() - Skipping callback.");
        }
    }

    private void updateToState(ImsRegistrationAttributes attributes, int newState) {
        synchronized (this.mLock) {
            this.mRegistrationAttributes = attributes;
            this.mRegistrationState = newState;
            this.mLastDisconnectCause = null;
        }
    }

    private void updateToDisconnectedState(ImsReasonInfo info) {
        synchronized (this.mLock) {
            this.mUrisSet = false;
            this.mUris = null;
            updateToState(new ImsRegistrationAttributes.Builder(-1).build(), 0);
            if (info != null) {
                this.mLastDisconnectCause = info;
            } else {
                Log.w(LOG_TAG, "updateToDisconnectedState: no ImsReasonInfo provided.");
                this.mLastDisconnectCause = new ImsReasonInfo();
            }
        }
    }

    private void updateNewCallbackWithState(IImsRegistrationCallback c) throws RemoteException {
        int state;
        ImsRegistrationAttributes attributes;
        ImsReasonInfo disconnectInfo;
        boolean urisSet;
        Uri[] uris;
        synchronized (this.mLock) {
            state = this.mRegistrationState;
            attributes = this.mRegistrationAttributes;
            disconnectInfo = this.mLastDisconnectCause;
            urisSet = this.mUrisSet;
            uris = this.mUris;
        }
        switch (state) {
            case 0:
                c.onDeregistered(disconnectInfo);
                break;
            case 1:
                c.onRegistering(attributes);
                break;
            case 2:
                c.onRegistered(attributes);
                break;
        }
        if (urisSet) {
            lambda$onSubscriberAssociatedUriChanged$4$ImsRegistrationImplBase(c, uris);
        }
    }
}
