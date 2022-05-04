package android.telephony.ims.stub;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.telephony.ims.RcsClientConfiguration;
import android.telephony.ims.RcsConfig;
import android.telephony.ims.aidl.IImsConfig;
import android.telephony.ims.aidl.IImsConfigCallback;
import android.telephony.ims.aidl.IRcsConfigCallback;
import android.util.Log;
import com.android.internal.telephony.util.RemoteCallbackListExt;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Consumer;
@SystemApi
/* loaded from: classes3.dex */
public class ImsConfigImplBase {
    public static final int CONFIG_RESULT_FAILED = 1;
    public static final int CONFIG_RESULT_SUCCESS = 0;
    public static final int CONFIG_RESULT_UNKNOWN = -1;
    private static final String TAG = "ImsConfigImplBase";
    private byte[] mRcsConfigData;
    private final RemoteCallbackListExt<IImsConfigCallback> mCallbacks = new RemoteCallbackListExt<>();
    private final RemoteCallbackListExt<IRcsConfigCallback> mRcsCallbacks = new RemoteCallbackListExt<>();
    ImsConfigStub mImsConfigStub = new ImsConfigStub(this);

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface SetConfigResult {
    }

    /* loaded from: classes3.dex */
    public static class ImsConfigStub extends IImsConfig.Stub {
        WeakReference<ImsConfigImplBase> mImsConfigImplBaseWeakReference;
        private HashMap<Integer, Integer> mProvisionedIntValue = new HashMap<>();
        private HashMap<Integer, String> mProvisionedStringValue = new HashMap<>();

        public ImsConfigStub(ImsConfigImplBase imsConfigImplBase) {
            this.mImsConfigImplBaseWeakReference = new WeakReference<>(imsConfigImplBase);
        }

        @Override // android.telephony.ims.aidl.IImsConfig
        public void addImsConfigCallback(IImsConfigCallback c) throws RemoteException {
            getImsConfigImpl().addImsConfigCallback(c);
        }

        @Override // android.telephony.ims.aidl.IImsConfig
        public void removeImsConfigCallback(IImsConfigCallback c) throws RemoteException {
            getImsConfigImpl().removeImsConfigCallback(c);
        }

        @Override // android.telephony.ims.aidl.IImsConfig
        public synchronized int getConfigInt(int item) throws RemoteException {
            if (this.mProvisionedIntValue.containsKey(Integer.valueOf(item))) {
                return this.mProvisionedIntValue.get(Integer.valueOf(item)).intValue();
            }
            int retVal = getImsConfigImpl().getConfigInt(item);
            if (retVal != -1) {
                updateCachedValue(item, retVal, false);
            }
            return retVal;
        }

        @Override // android.telephony.ims.aidl.IImsConfig
        public synchronized String getConfigString(int item) throws RemoteException {
            if (this.mProvisionedStringValue.containsKey(Integer.valueOf(item))) {
                return this.mProvisionedStringValue.get(Integer.valueOf(item));
            }
            String retVal = getImsConfigImpl().getConfigString(item);
            if (retVal != null) {
                updateCachedValue(item, retVal, false);
            }
            return retVal;
        }

        @Override // android.telephony.ims.aidl.IImsConfig
        public synchronized int setConfigInt(int item, int value) throws RemoteException {
            int retVal;
            this.mProvisionedIntValue.remove(Integer.valueOf(item));
            retVal = getImsConfigImpl().setConfig(item, value);
            if (retVal == 0) {
                updateCachedValue(item, value, true);
            } else {
                Log.d(ImsConfigImplBase.TAG, "Set provision value of " + item + " to " + value + " failed with error code " + retVal);
            }
            return retVal;
        }

        @Override // android.telephony.ims.aidl.IImsConfig
        public synchronized int setConfigString(int item, String value) throws RemoteException {
            int retVal;
            this.mProvisionedStringValue.remove(Integer.valueOf(item));
            retVal = getImsConfigImpl().setConfig(item, value);
            if (retVal == 0) {
                updateCachedValue(item, value, true);
            }
            return retVal;
        }

        @Override // android.telephony.ims.aidl.IImsConfig
        public void updateImsCarrierConfigs(PersistableBundle bundle) throws RemoteException {
            getImsConfigImpl().updateImsCarrierConfigs(bundle);
        }

        private ImsConfigImplBase getImsConfigImpl() throws RemoteException {
            ImsConfigImplBase ref = this.mImsConfigImplBaseWeakReference.get();
            if (ref != null) {
                return ref;
            }
            throw new RemoteException("Fail to get ImsConfigImpl");
        }

        @Override // android.telephony.ims.aidl.IImsConfig
        public void notifyRcsAutoConfigurationReceived(byte[] config, boolean isCompressed) throws RemoteException {
            getImsConfigImpl().onNotifyRcsAutoConfigurationReceived(config, isCompressed);
        }

        @Override // android.telephony.ims.aidl.IImsConfig
        public void notifyRcsAutoConfigurationRemoved() throws RemoteException {
            getImsConfigImpl().onNotifyRcsAutoConfigurationRemoved();
        }

        private void notifyImsConfigChanged(int item, int value) throws RemoteException {
            getImsConfigImpl().notifyConfigChanged(item, value);
        }

        private void notifyImsConfigChanged(int item, String value) throws RemoteException {
            getImsConfigImpl().notifyConfigChanged(item, value);
        }

        protected synchronized void updateCachedValue(int item, int value, boolean notifyChange) throws RemoteException {
            this.mProvisionedIntValue.put(Integer.valueOf(item), Integer.valueOf(value));
            if (notifyChange) {
                notifyImsConfigChanged(item, value);
            }
        }

        protected synchronized void updateCachedValue(int item, String value, boolean notifyChange) throws RemoteException {
            this.mProvisionedStringValue.put(Integer.valueOf(item), value);
            if (notifyChange) {
                notifyImsConfigChanged(item, value);
            }
        }

        @Override // android.telephony.ims.aidl.IImsConfig
        public void addRcsConfigCallback(IRcsConfigCallback c) throws RemoteException {
            getImsConfigImpl().addRcsConfigCallback(c);
        }

        @Override // android.telephony.ims.aidl.IImsConfig
        public void removeRcsConfigCallback(IRcsConfigCallback c) throws RemoteException {
            getImsConfigImpl().removeRcsConfigCallback(c);
        }

        @Override // android.telephony.ims.aidl.IImsConfig
        public void triggerRcsReconfiguration() throws RemoteException {
            getImsConfigImpl().triggerAutoConfiguration();
        }

        @Override // android.telephony.ims.aidl.IImsConfig
        public void setRcsClientConfiguration(RcsClientConfiguration rcc) throws RemoteException {
            getImsConfigImpl().setRcsClientConfiguration(rcc);
        }

        @Override // android.telephony.ims.aidl.IImsConfig
        public void notifyIntImsConfigChanged(int item, int value) throws RemoteException {
            notifyImsConfigChanged(item, value);
        }

        @Override // android.telephony.ims.aidl.IImsConfig
        public void notifyStringImsConfigChanged(int item, String value) throws RemoteException {
            notifyImsConfigChanged(item, value);
        }
    }

    public ImsConfigImplBase(Context context) {
    }

    public ImsConfigImplBase() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addImsConfigCallback(IImsConfigCallback c) {
        this.mCallbacks.register(c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeImsConfigCallback(IImsConfigCallback c) {
        this.mCallbacks.unregister(c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyConfigChanged(final int item, final int value) {
        RemoteCallbackListExt<IImsConfigCallback> remoteCallbackListExt = this.mCallbacks;
        if (remoteCallbackListExt != null) {
            remoteCallbackListExt.broadcastAction(new Consumer() { // from class: android.telephony.ims.stub.ImsConfigImplBase$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ImsConfigImplBase.lambda$notifyConfigChanged$0(item, value, (IImsConfigCallback) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$notifyConfigChanged$0(int item, int value, IImsConfigCallback c) {
        try {
            c.onIntConfigChanged(item, value);
        } catch (RemoteException e) {
            Log.w(TAG, "notifyConfigChanged(int): dead binder in notify, skipping.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyConfigChanged(final int item, final String value) {
        RemoteCallbackListExt<IImsConfigCallback> remoteCallbackListExt = this.mCallbacks;
        if (remoteCallbackListExt != null) {
            remoteCallbackListExt.broadcastAction(new Consumer() { // from class: android.telephony.ims.stub.ImsConfigImplBase$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ImsConfigImplBase.lambda$notifyConfigChanged$1(item, value, (IImsConfigCallback) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$notifyConfigChanged$1(int item, String value, IImsConfigCallback c) {
        try {
            c.onStringConfigChanged(item, value);
        } catch (RemoteException e) {
            Log.w(TAG, "notifyConfigChanged(string): dead binder in notify, skipping.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addRcsConfigCallback(IRcsConfigCallback c) {
        this.mRcsCallbacks.register(c);
        byte[] bArr = this.mRcsConfigData;
        if (bArr != null) {
            try {
                c.onConfigurationChanged(bArr);
            } catch (RemoteException e) {
                Log.w(TAG, "dead binder to call onConfigurationChanged, skipping.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeRcsConfigCallback(IRcsConfigCallback c) {
        this.mRcsCallbacks.unregister(c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onNotifyRcsAutoConfigurationReceived(byte[] config, boolean isCompressed) {
        byte[] config2 = isCompressed ? RcsConfig.decompressGzip(config) : config;
        if (!Arrays.equals(this.mRcsConfigData, config2)) {
            this.mRcsConfigData = config2;
            RemoteCallbackListExt<IRcsConfigCallback> remoteCallbackListExt = this.mRcsCallbacks;
            if (remoteCallbackListExt != null) {
                remoteCallbackListExt.broadcastAction(new Consumer() { // from class: android.telephony.ims.stub.ImsConfigImplBase$$ExternalSyntheticLambda3
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ImsConfigImplBase.this.lambda$onNotifyRcsAutoConfigurationReceived$2$ImsConfigImplBase((IRcsConfigCallback) obj);
                    }
                });
            }
            notifyRcsAutoConfigurationReceived(config2, isCompressed);
        }
    }

    public /* synthetic */ void lambda$onNotifyRcsAutoConfigurationReceived$2$ImsConfigImplBase(IRcsConfigCallback c) {
        try {
            c.onConfigurationChanged(this.mRcsConfigData);
        } catch (RemoteException e) {
            Log.w(TAG, "dead binder in notifyRcsAutoConfigurationReceived, skipping.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onNotifyRcsAutoConfigurationRemoved() {
        this.mRcsConfigData = null;
        RemoteCallbackListExt<IRcsConfigCallback> remoteCallbackListExt = this.mRcsCallbacks;
        if (remoteCallbackListExt != null) {
            remoteCallbackListExt.broadcastAction(ImsConfigImplBase$$ExternalSyntheticLambda5.INSTANCE);
        }
        notifyRcsAutoConfigurationRemoved();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onNotifyRcsAutoConfigurationRemoved$3(IRcsConfigCallback c) {
        try {
            c.onConfigurationReset();
        } catch (RemoteException e) {
            Log.w(TAG, "dead binder in notifyRcsAutoConfigurationRemoved, skipping.");
        }
    }

    public IImsConfig getIImsConfig() {
        return this.mImsConfigStub;
    }

    public final void notifyProvisionedValueChanged(int item, int value) {
        try {
            this.mImsConfigStub.updateCachedValue(item, value, true);
        } catch (RemoteException e) {
            Log.w(TAG, "notifyProvisionedValueChanged(int): Framework connection is dead.");
        }
    }

    public final void notifyProvisionedValueChanged(int item, String value) {
        try {
            this.mImsConfigStub.updateCachedValue(item, value, true);
        } catch (RemoteException e) {
            Log.w(TAG, "notifyProvisionedValueChanged(string): Framework connection is dead.");
        }
    }

    public void notifyRcsAutoConfigurationReceived(byte[] config, boolean isCompressed) {
    }

    public void notifyRcsAutoConfigurationRemoved() {
    }

    public int setConfig(int item, int value) {
        return 1;
    }

    public int setConfig(int item, String value) {
        return 1;
    }

    public int getConfigInt(int item) {
        return -1;
    }

    public String getConfigString(int item) {
        return null;
    }

    public void updateImsCarrierConfigs(PersistableBundle bundle) {
    }

    public void setRcsClientConfiguration(RcsClientConfiguration rcc) {
    }

    public void triggerAutoConfiguration() {
    }

    public final void notifyAutoConfigurationErrorReceived(final int errorCode, final String errorString) {
        RemoteCallbackListExt<IRcsConfigCallback> remoteCallbackListExt = this.mRcsCallbacks;
        if (remoteCallbackListExt != null) {
            remoteCallbackListExt.broadcastAction(new Consumer() { // from class: android.telephony.ims.stub.ImsConfigImplBase$$ExternalSyntheticLambda2
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ImsConfigImplBase.lambda$notifyAutoConfigurationErrorReceived$4(errorCode, errorString, (IRcsConfigCallback) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$notifyAutoConfigurationErrorReceived$4(int errorCode, String errorString, IRcsConfigCallback c) {
        try {
            c.onAutoConfigurationErrorReceived(errorCode, errorString);
        } catch (RemoteException e) {
            Log.w(TAG, "dead binder in notifyAutoConfigurationErrorReceived, skipping.");
        }
    }

    public final void notifyPreProvisioningReceived(final byte[] configXml) {
        RemoteCallbackListExt<IRcsConfigCallback> remoteCallbackListExt = this.mRcsCallbacks;
        if (remoteCallbackListExt != null) {
            remoteCallbackListExt.broadcastAction(new Consumer() { // from class: android.telephony.ims.stub.ImsConfigImplBase$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ImsConfigImplBase.lambda$notifyPreProvisioningReceived$5(configXml, (IRcsConfigCallback) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$notifyPreProvisioningReceived$5(byte[] configXml, IRcsConfigCallback c) {
        try {
            c.onPreProvisioningReceived(configXml);
        } catch (RemoteException e) {
            Log.w(TAG, "dead binder in notifyPreProvisioningReceived, skipping.");
        }
    }
}
