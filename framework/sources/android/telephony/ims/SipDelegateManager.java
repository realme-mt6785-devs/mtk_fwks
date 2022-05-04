package android.telephony.ims;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import android.telephony.BinderCacheManager;
import android.telephony.ims.aidl.IImsRcsController;
import android.telephony.ims.aidl.SipDelegateConnectionAidlWrapper;
import android.telephony.ims.stub.DelegateConnectionMessageCallback;
import android.telephony.ims.stub.DelegateConnectionStateCallback;
import android.util.ArrayMap;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;
import java.util.concurrent.Executor;
@SystemApi
/* loaded from: classes3.dex */
public class SipDelegateManager {
    public static final int DENIED_REASON_INVALID = 4;
    public static final int DENIED_REASON_IN_USE_BY_ANOTHER_DELEGATE = 1;
    public static final int DENIED_REASON_NOT_ALLOWED = 2;
    public static final int DENIED_REASON_SINGLE_REGISTRATION_NOT_ALLOWED = 3;
    public static final int DENIED_REASON_UNKNOWN = 0;
    public static final int MESSAGE_FAILURE_REASON_DELEGATE_CLOSED = 2;
    public static final int MESSAGE_FAILURE_REASON_DELEGATE_DEAD = 1;
    public static final int MESSAGE_FAILURE_REASON_INTERNAL_DELEGATE_STATE_TRANSITION = 11;
    public static final int MESSAGE_FAILURE_REASON_INVALID_BODY_CONTENT = 5;
    public static final int MESSAGE_FAILURE_REASON_INVALID_FEATURE_TAG = 6;
    public static final int MESSAGE_FAILURE_REASON_INVALID_HEADER_FIELDS = 4;
    public static final int MESSAGE_FAILURE_REASON_INVALID_START_LINE = 3;
    public static final int MESSAGE_FAILURE_REASON_NETWORK_NOT_AVAILABLE = 8;
    public static final int MESSAGE_FAILURE_REASON_NOT_REGISTERED = 9;
    public static final int MESSAGE_FAILURE_REASON_STALE_IMS_CONFIGURATION = 10;
    public static final ArrayMap<Integer, String> MESSAGE_FAILURE_REASON_STRING_MAP;
    public static final int MESSAGE_FAILURE_REASON_TAG_NOT_ENABLED_FOR_DELEGATE = 7;
    public static final int MESSAGE_FAILURE_REASON_UNKNOWN = 0;
    public static final int SIP_DELEGATE_DESTROY_REASON_REQUESTED_BY_APP = 2;
    public static final int SIP_DELEGATE_DESTROY_REASON_SERVICE_DEAD = 1;
    public static final int SIP_DELEGATE_DESTROY_REASON_SUBSCRIPTION_TORN_DOWN = 4;
    public static final int SIP_DELEGATE_DESTROY_REASON_UNKNOWN = 0;
    public static final int SIP_DELEGATE_DESTROY_REASON_USER_DISABLED_RCS = 3;
    private final BinderCacheManager<IImsRcsController> mBinderCache;
    private final Context mContext;
    private final int mSubId;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface DeniedReason {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface MessageFailureReason {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface SipDelegateDestroyReason {
    }

    static {
        ArrayMap<Integer, String> arrayMap = new ArrayMap<>(11);
        MESSAGE_FAILURE_REASON_STRING_MAP = arrayMap;
        arrayMap.append(0, "MESSAGE_FAILURE_REASON_UNKNOWN");
        arrayMap.append(1, "MESSAGE_FAILURE_REASON_DELEGATE_DEAD");
        arrayMap.append(2, "MESSAGE_FAILURE_REASON_DELEGATE_CLOSED");
        arrayMap.append(4, "MESSAGE_FAILURE_REASON_INVALID_HEADER_FIELDS");
        arrayMap.append(5, "MESSAGE_FAILURE_REASON_INVALID_BODY_CONTENT");
        arrayMap.append(6, "MESSAGE_FAILURE_REASON_INVALID_FEATURE_TAG");
        arrayMap.append(7, "MESSAGE_FAILURE_REASON_TAG_NOT_ENABLED_FOR_DELEGATE");
        arrayMap.append(8, "MESSAGE_FAILURE_REASON_NETWORK_NOT_AVAILABLE");
        arrayMap.append(9, "MESSAGE_FAILURE_REASON_NOT_REGISTERED");
        arrayMap.append(10, "MESSAGE_FAILURE_REASON_STALE_IMS_CONFIGURATION");
        arrayMap.append(11, "MESSAGE_FAILURE_REASON_INTERNAL_DELEGATE_STATE_TRANSITION");
    }

    public SipDelegateManager(Context context, int subId, BinderCacheManager<IImsRcsController> binderCache) {
        this.mContext = context;
        this.mSubId = subId;
        this.mBinderCache = binderCache;
    }

    public boolean isSupported() throws ImsException {
        try {
            IImsRcsController controller = this.mBinderCache.getBinder();
            if (controller != null) {
                return controller.isSipDelegateSupported(this.mSubId);
            }
            throw new ImsException("Telephony server is down", 1);
        } catch (RemoteException e) {
            throw new ImsException(e.getMessage(), 1);
        } catch (ServiceSpecificException e2) {
            throw new ImsException(e2.getMessage(), e2.errorCode);
        }
    }

    public void createSipDelegate(DelegateRequest request, Executor executor, DelegateConnectionStateCallback dc, DelegateConnectionMessageCallback mc) throws ImsException {
        Objects.requireNonNull(request, "The DelegateRequest must not be null.");
        Objects.requireNonNull(executor, "The Executor must not be null.");
        Objects.requireNonNull(dc, "The DelegateConnectionStateCallback must not be null.");
        Objects.requireNonNull(mc, "The DelegateConnectionMessageCallback must not be null.");
        try {
            final SipDelegateConnectionAidlWrapper wrapper = new SipDelegateConnectionAidlWrapper(executor, dc, mc);
            BinderCacheManager<IImsRcsController> binderCacheManager = this.mBinderCache;
            Objects.requireNonNull(wrapper);
            IImsRcsController controller = binderCacheManager.listenOnBinder(wrapper, new Runnable() { // from class: android.telephony.ims.SipDelegateManager$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    SipDelegateConnectionAidlWrapper.this.binderDied();
                }
            });
            if (controller != null) {
                controller.createSipDelegate(this.mSubId, request, this.mContext.getOpPackageName(), wrapper.getStateCallbackBinder(), wrapper.getMessageCallbackBinder());
                return;
            }
            throw new ImsException("Telephony server is down", 1);
        } catch (RemoteException e) {
            throw new ImsException(e.getMessage(), 1);
        } catch (ServiceSpecificException e2) {
            throw new ImsException(e2.getMessage(), e2.errorCode);
        }
    }

    public void destroySipDelegate(SipDelegateConnection delegateConnection, int reason) {
        Objects.requireNonNull(delegateConnection, "SipDelegateConnection can not be null.");
        if (delegateConnection instanceof SipDelegateConnectionAidlWrapper) {
            SipDelegateConnectionAidlWrapper w = (SipDelegateConnectionAidlWrapper) delegateConnection;
            try {
                IImsRcsController c = this.mBinderCache.removeRunnable(w);
                c.destroySipDelegate(this.mSubId, w.getSipDelegateBinder(), reason);
            } catch (RemoteException e) {
                try {
                    w.getStateCallbackBinder().onDestroyed(2);
                } catch (RemoteException e2) {
                }
            }
        } else {
            throw new IllegalArgumentException("Unknown SipDelegateConnection implementation passed into this method");
        }
    }

    public void triggerFullNetworkRegistration(SipDelegateConnection connection, int sipCode, String sipReason) {
        Objects.requireNonNull(connection, "SipDelegateConnection can not be null.");
        if (connection instanceof SipDelegateConnectionAidlWrapper) {
            SipDelegateConnectionAidlWrapper w = (SipDelegateConnectionAidlWrapper) connection;
            try {
                IImsRcsController controller = this.mBinderCache.getBinder();
                controller.triggerNetworkRegistration(this.mSubId, w.getSipDelegateBinder(), sipCode, sipReason);
            } catch (RemoteException e) {
            }
        } else {
            throw new IllegalArgumentException("Unknown SipDelegateConnection implementation passed into this method");
        }
    }
}
