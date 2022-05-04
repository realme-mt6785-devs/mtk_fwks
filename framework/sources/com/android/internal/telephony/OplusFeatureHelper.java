package com.android.internal.telephony;

import android.os.RemoteException;
import com.oplus.content.OplusFeatureConfigManager;
import java.util.List;
/* loaded from: classes4.dex */
public class OplusFeatureHelper {
    private static OplusFeatureHelper sInstance = null;
    OplusFeatureConfigManager mManager;

    public static OplusFeatureHelper getInstance() {
        OplusFeatureHelper oplusFeatureHelper;
        synchronized (OplusFeatureHelper.class) {
            if (sInstance == null) {
                sInstance = new OplusFeatureHelper();
            }
            oplusFeatureHelper = sInstance;
        }
        return oplusFeatureHelper;
    }

    private OplusFeatureHelper() {
        this.mManager = null;
        this.mManager = OplusFeatureConfigManager.getInstance();
    }

    public boolean hasFeature(String featureName) {
        return this.mManager.hasFeature(featureName);
    }

    public boolean enableFeature(String featureName) {
        try {
            return this.mManager.enableFeature(featureName);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean disableFeature(String featureName) {
        try {
            return this.mManager.disableFeature(featureName);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void notifyFeaturesUpdate(String action, String actionValue) {
        try {
            this.mManager.notifyFeaturesUpdate(action, actionValue);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean registerFeatureObserver(List<String> features, FeatureObserver observer) {
        return this.mManager.registerFeatureObserver(features, observer);
    }

    public boolean unregisterFeatureObserver(FeatureObserver observer) {
        return this.mManager.unregisterFeatureObserver(observer);
    }

    /* loaded from: classes4.dex */
    public class FeatureObserver implements OplusFeatureConfigManager.OnFeatureObserver {
        public FeatureObserver() {
        }

        @Override // com.oplus.content.OplusFeatureConfigManager.OnFeatureObserver
        public void onFeatureUpdate(List<String> list) {
        }
    }
}
