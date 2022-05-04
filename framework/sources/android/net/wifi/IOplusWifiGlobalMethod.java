package android.net.wifi;

import android.common.IOplusCommonFeature;
import android.common.OplusFeatureList;
/* loaded from: classes2.dex */
public interface IOplusWifiGlobalMethod extends IOplusCommonFeature {
    public static final IOplusWifiGlobalMethod DEFAULT = new IOplusWifiGlobalMethod() { // from class: android.net.wifi.IOplusWifiGlobalMethod.1
    };
    public static final String NAME = "IOplusWifiGlobalMethod";

    @Override // android.common.IOplusCommonFeature
    default OplusFeatureList.OplusIndex index() {
        return OplusFeatureList.OplusIndex.IOplusWifiGlobalMethod;
    }

    @Override // android.common.IOplusCommonFeature
    default IOplusCommonFeature getDefault() {
        return DEFAULT;
    }

    default boolean isNotChineseOperator() {
        return false;
    }
}
