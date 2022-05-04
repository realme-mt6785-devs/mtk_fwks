package com.oplus.content;

import android.os.RemoteException;
import java.util.List;
/* loaded from: classes4.dex */
public interface IOplusFeatureConfigManager {
    public static final String DESCRIPTOR = "android.app.IPackageManager";
    public static final int DISABLE_FEATURE_CONFIG = 20004;
    public static final int ENABLE_FEATURE_CONFIG = 20003;
    public static final int HAS_FEATURE_CONFIG = 20002;
    public static final int NOTIFY_FEATURE_CONFIG_UPDATE = 20005;
    public static final int REGISTER_FEATURE_OBSERVER = 20006;
    public static final int UNREGISTER_FEATURE_OBSERVER = 20007;

    default boolean hasFeature(String featureName) throws RemoteException {
        return false;
    }

    default boolean enableFeature(String featureName) throws RemoteException {
        return false;
    }

    default boolean disableFeature(String featureName) throws RemoteException {
        return false;
    }

    default void notifyFeaturesUpdate(String action, String actionValue) throws RemoteException {
    }

    default boolean registerFeatureObserver(List<String> features, IOplusFeatureObserver observer) {
        return false;
    }

    default boolean unregisterFeatureObserver(IOplusFeatureObserver observer) {
        return false;
    }
}
