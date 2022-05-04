package android.net.wifi;

import android.common.IOplusCommonFeature;
import android.common.OplusFeatureList;
import java.util.List;
/* loaded from: classes2.dex */
public interface IOplusWifiNetworkConfig extends IOplusCommonFeature {
    public static final IOplusWifiNetworkConfig DEFAULT = new IOplusWifiNetworkConfig() { // from class: android.net.wifi.IOplusWifiNetworkConfig.1
    };
    public static final String NAME = "IOplusWifiNetworkConfig";

    @Override // android.common.IOplusCommonFeature
    default OplusFeatureList.OplusIndex index() {
        return OplusFeatureList.OplusIndex.IOplusWifiNetworkConfig;
    }

    @Override // android.common.IOplusCommonFeature
    default IOplusCommonFeature getDefault() {
        return DEFAULT;
    }

    default boolean inSpecialUrlList(String url) {
        return false;
    }

    default List<String> getInternalServers() {
        return null;
    }

    default List<String> getPublicHttpsServers() {
        return null;
    }

    default List<String> getFallbackServers() {
        return null;
    }

    default List<String> getIpv6Servers() {
        return null;
    }

    default String getExpHttpUrl() {
        return null;
    }

    default String getExpHttpsUrl() {
        return null;
    }

    default String getDefaultDns(boolean isExp) {
        return null;
    }

    default String[] getBackupDnsServer(boolean isExp) {
        return null;
    }

    default boolean isHomeAp(String url) {
        return false;
    }
}
