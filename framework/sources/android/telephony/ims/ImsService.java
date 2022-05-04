package android.telephony.ims;

import android.annotation.SystemApi;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.telephony.ims.aidl.IImsConfig;
import android.telephony.ims.aidl.IImsMmTelFeature;
import android.telephony.ims.aidl.IImsRcsFeature;
import android.telephony.ims.aidl.IImsRegistration;
import android.telephony.ims.aidl.IImsServiceController;
import android.telephony.ims.aidl.IImsServiceControllerListener;
import android.telephony.ims.aidl.ISipTransport;
import android.telephony.ims.feature.ImsFeature;
import android.telephony.ims.feature.MmTelFeature;
import android.telephony.ims.feature.RcsFeature;
import android.telephony.ims.stub.ImsConfigImplBase;
import android.telephony.ims.stub.ImsFeatureConfiguration;
import android.telephony.ims.stub.ImsRegistrationImplBase;
import android.telephony.ims.stub.SipTransportImplBase;
import android.util.Log;
import android.util.SparseArray;
import com.android.ims.internal.IImsFeatureStatusCallback;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;
@SystemApi
/* loaded from: classes3.dex */
public class ImsService extends Service {
    public static final long CAPABILITY_EMERGENCY_OVER_MMTEL = 1;
    public static final long CAPABILITY_SIP_DELEGATE_CREATION = 2;
    private static final String LOG_TAG = "ImsService";
    public static final String SERVICE_INTERFACE = "android.telephony.ims.ImsService";
    private final SparseArray<SparseArray<ImsFeature>> mFeaturesBySlot = new SparseArray<>();
    protected final IBinder mImsServiceController = new IImsServiceController.Stub() { // from class: android.telephony.ims.ImsService.2
        @Override // android.telephony.ims.aidl.IImsServiceController
        public void setListener(IImsServiceControllerListener l) {
            ImsService.this.mListener = l;
        }

        @Override // android.telephony.ims.aidl.IImsServiceController
        public IImsMmTelFeature createMmTelFeature(int slotId) {
            return ImsService.this.createMmTelFeatureInternal(slotId);
        }

        @Override // android.telephony.ims.aidl.IImsServiceController
        public IImsRcsFeature createRcsFeature(int slotId) {
            return ImsService.this.createRcsFeatureInternal(slotId);
        }

        @Override // android.telephony.ims.aidl.IImsServiceController
        public void addFeatureStatusCallback(int slotId, int featureType, IImsFeatureStatusCallback c) {
            ImsService.this.addImsFeatureStatusCallback(slotId, featureType, c);
        }

        @Override // android.telephony.ims.aidl.IImsServiceController
        public void removeFeatureStatusCallback(int slotId, int featureType, IImsFeatureStatusCallback c) {
            ImsService.this.removeImsFeatureStatusCallback(slotId, featureType, c);
        }

        @Override // android.telephony.ims.aidl.IImsServiceController
        public void removeImsFeature(int slotId, int featureType) {
            ImsService.this.removeImsFeature(slotId, featureType);
        }

        @Override // android.telephony.ims.aidl.IImsServiceController
        public ImsFeatureConfiguration querySupportedImsFeatures() {
            return ImsService.this.querySupportedImsFeatures();
        }

        @Override // android.telephony.ims.aidl.IImsServiceController
        public long getImsServiceCapabilities() {
            long caps = ImsService.this.getImsServiceCapabilities();
            long sanitizedCaps = ImsService.sanitizeCapabilities(caps);
            if (caps != sanitizedCaps) {
                Log.w(ImsService.LOG_TAG, "removing invalid bits from field: 0x" + Long.toHexString(caps ^ sanitizedCaps));
            }
            return sanitizedCaps;
        }

        @Override // android.telephony.ims.aidl.IImsServiceController
        public void notifyImsServiceReadyForFeatureCreation() {
            ImsService.this.readyForFeatureCreation();
        }

        @Override // android.telephony.ims.aidl.IImsServiceController
        public IImsConfig getConfig(int slotId) {
            ImsConfigImplBase c = ImsService.this.getConfig(slotId);
            if (c != null) {
                return c.getIImsConfig();
            }
            return null;
        }

        @Override // android.telephony.ims.aidl.IImsServiceController
        public IImsRegistration getRegistration(int slotId) {
            ImsRegistrationImplBase r = ImsService.this.getRegistration(slotId);
            if (r != null) {
                return r.getBinder();
            }
            return null;
        }

        @Override // android.telephony.ims.aidl.IImsServiceController
        public ISipTransport getSipTransport(int slotId) {
            SipTransportImplBase s = ImsService.this.getSipTransport(slotId);
            if (s != null) {
                return s.getBinder();
            }
            return null;
        }

        @Override // android.telephony.ims.aidl.IImsServiceController
        public void enableIms(int slotId) {
            ImsService.this.enableIms(slotId);
        }

        @Override // android.telephony.ims.aidl.IImsServiceController
        public void disableIms(int slotId) {
            ImsService.this.disableIms(slotId);
        }
    };
    private IImsServiceControllerListener mListener;
    public static final long CAPABILITY_MAX_INDEX = Long.numberOfTrailingZeros(2);
    private static final Map<Long, String> CAPABILITIES_LOG_MAP = new HashMap<Long, String>() { // from class: android.telephony.ims.ImsService.1
        {
            put(1L, "EMERGENCY_OVER_MMTEL");
            put(2L, "SIP_DELEGATE_CREATION");
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface ImsServiceCapability {
    }

    /* loaded from: classes3.dex */
    public static class Listener extends IImsServiceControllerListener.Stub {
        @Override // android.telephony.ims.aidl.IImsServiceControllerListener
        public void onUpdateSupportedImsFeatures(ImsFeatureConfiguration c) {
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (!SERVICE_INTERFACE.equals(intent.getAction())) {
            return null;
        }
        Log.i(LOG_TAG, "ImsService Bound.");
        return this.mImsServiceController;
    }

    public SparseArray<ImsFeature> getFeatures(int slotId) {
        return this.mFeaturesBySlot.get(slotId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IImsMmTelFeature createMmTelFeatureInternal(int slotId) {
        MmTelFeature f = createMmTelFeature(slotId);
        if (f != null) {
            setupFeature(f, slotId, 1);
            return f.getBinder();
        }
        Log.e(LOG_TAG, "createMmTelFeatureInternal: null feature returned.");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IImsRcsFeature createRcsFeatureInternal(int slotId) {
        RcsFeature f = createRcsFeature(slotId);
        if (f != null) {
            setupFeature(f, slotId, 2);
            return f.getBinder();
        }
        Log.e(LOG_TAG, "createRcsFeatureInternal: null feature returned.");
        return null;
    }

    private void setupFeature(ImsFeature f, int slotId, int featureType) {
        f.initialize(this, slotId);
        addImsFeature(slotId, featureType, f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addImsFeatureStatusCallback(int slotId, int featureType, IImsFeatureStatusCallback c) {
        synchronized (this.mFeaturesBySlot) {
            SparseArray<ImsFeature> features = this.mFeaturesBySlot.get(slotId);
            if (features == null) {
                Log.w(LOG_TAG, "Can not add ImsFeatureStatusCallback - no features on slot " + slotId);
                return;
            }
            ImsFeature f = features.get(featureType);
            if (f != null) {
                f.addImsFeatureStatusCallback(c);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeImsFeatureStatusCallback(int slotId, int featureType, IImsFeatureStatusCallback c) {
        synchronized (this.mFeaturesBySlot) {
            SparseArray<ImsFeature> features = this.mFeaturesBySlot.get(slotId);
            if (features == null) {
                Log.w(LOG_TAG, "Can not remove ImsFeatureStatusCallback - no features on slot " + slotId);
                return;
            }
            ImsFeature f = features.get(featureType);
            if (f != null) {
                f.removeImsFeatureStatusCallback(c);
            }
        }
    }

    private void addImsFeature(int slotId, int featureType, ImsFeature f) {
        synchronized (this.mFeaturesBySlot) {
            SparseArray<ImsFeature> features = this.mFeaturesBySlot.get(slotId);
            if (features == null) {
                features = new SparseArray<>();
                this.mFeaturesBySlot.put(slotId, features);
            }
            features.put(featureType, f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeImsFeature(int slotId, int featureType) {
        synchronized (this.mFeaturesBySlot) {
            SparseArray<ImsFeature> features = this.mFeaturesBySlot.get(slotId);
            if (features == null) {
                Log.w(LOG_TAG, "Can not remove ImsFeature. No ImsFeatures exist on slot " + slotId);
                return;
            }
            ImsFeature f = features.get(featureType);
            if (f == null) {
                Log.w(LOG_TAG, "Can not remove ImsFeature. No feature with type " + featureType + " exists on slot " + slotId);
                return;
            }
            f.onFeatureRemoved();
            features.remove(featureType);
        }
    }

    public ImsFeatureConfiguration querySupportedImsFeatures() {
        return new ImsFeatureConfiguration();
    }

    public final void onUpdateSupportedImsFeatures(ImsFeatureConfiguration c) throws RemoteException {
        IImsServiceControllerListener iImsServiceControllerListener = this.mListener;
        if (iImsServiceControllerListener != null) {
            iImsServiceControllerListener.onUpdateSupportedImsFeatures(c);
            return;
        }
        throw new IllegalStateException("Framework is not ready");
    }

    public long getImsServiceCapabilities() {
        return 0L;
    }

    public void readyForFeatureCreation() {
    }

    public void enableIms(int slotId) {
    }

    public void disableIms(int slotId) {
    }

    public MmTelFeature createMmTelFeature(int slotId) {
        return null;
    }

    public RcsFeature createRcsFeature(int slotId) {
        return null;
    }

    public ImsConfigImplBase getConfig(int slotId) {
        return new ImsConfigImplBase();
    }

    public ImsRegistrationImplBase getRegistration(int slotId) {
        return new ImsRegistrationImplBase();
    }

    public SipTransportImplBase getSipTransport(int slotId) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long sanitizeCapabilities(long caps) {
        long filter = (-1) << ((int) (CAPABILITY_MAX_INDEX + 1));
        return caps & (~filter) & (-2);
    }

    public static String getCapabilitiesString(long caps) {
        StringBuffer result = new StringBuffer();
        result.append("capabilities={ ");
        long filter = -1;
        for (long i = 0; (caps & filter) != 0 && i <= 63; i++) {
            long bitToCheck = 1 << ((int) i);
            if ((caps & bitToCheck) != 0) {
                Map<Long, String> map = CAPABILITIES_LOG_MAP;
                Long valueOf = Long.valueOf(bitToCheck);
                result.append(map.getOrDefault(valueOf, bitToCheck + "?"));
                result.append(" ");
            }
            filter <<= 1;
        }
        result.append("}");
        return result.toString();
    }
}
