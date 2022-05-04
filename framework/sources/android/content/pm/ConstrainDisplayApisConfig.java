package android.content.pm;

import android.provider.DeviceConfig;
import android.provider.SettingsStringUtil;
import android.util.Slog;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes.dex */
public final class ConstrainDisplayApisConfig {
    private static final String FLAG_ALWAYS_CONSTRAIN_DISPLAY_APIS = "always_constrain_display_apis";
    private static final String FLAG_NEVER_CONSTRAIN_DISPLAY_APIS = "never_constrain_display_apis";
    private static final String FLAG_NEVER_CONSTRAIN_DISPLAY_APIS_ALL_PACKAGES = "never_constrain_display_apis_all_packages";
    private static final String TAG = ConstrainDisplayApisConfig.class.getSimpleName();

    public static boolean neverConstrainDisplayApis(ApplicationInfo applicationInfo) {
        if (DeviceConfig.getBoolean(DeviceConfig.NAMESPACE_CONSTRAIN_DISPLAY_APIS, FLAG_NEVER_CONSTRAIN_DISPLAY_APIS_ALL_PACKAGES, false)) {
            return true;
        }
        return flagHasMatchingPackageEntry(FLAG_NEVER_CONSTRAIN_DISPLAY_APIS, applicationInfo);
    }

    public static boolean alwaysConstrainDisplayApis(ApplicationInfo applicationInfo) {
        return flagHasMatchingPackageEntry(FLAG_ALWAYS_CONSTRAIN_DISPLAY_APIS, applicationInfo);
    }

    private static boolean flagHasMatchingPackageEntry(String flagName, ApplicationInfo applicationInfo) {
        String[] split;
        String configStr = DeviceConfig.getString(DeviceConfig.NAMESPACE_CONSTRAIN_DISPLAY_APIS, flagName, "");
        if (configStr.isEmpty()) {
            return false;
        }
        for (String packageEntryString : configStr.split(",")) {
            if (matchesApplicationInfo(packageEntryString, applicationInfo)) {
                return true;
            }
        }
        return false;
    }

    private static boolean matchesApplicationInfo(String packageEntryStr, ApplicationInfo applicationInfo) {
        List<String> packageAndVersions = Arrays.asList(packageEntryStr.split(SettingsStringUtil.DELIMITER, 3));
        if (packageAndVersions.size() != 3) {
            String str = TAG;
            Slog.w(str, "Invalid package entry in flag 'never_constrain_display_apis': " + packageEntryStr);
            return false;
        }
        String packageName = packageAndVersions.get(0);
        String minVersionCodeStr = packageAndVersions.get(1);
        String maxVersionCodeStr = packageAndVersions.get(2);
        if (!packageName.equals(applicationInfo.packageName)) {
            return false;
        }
        long version = applicationInfo.longVersionCode;
        try {
            if (!minVersionCodeStr.isEmpty() && version < Long.parseLong(minVersionCodeStr)) {
                return false;
            }
            if (!maxVersionCodeStr.isEmpty()) {
                if (version > Long.parseLong(maxVersionCodeStr)) {
                    return false;
                }
            }
            return true;
        } catch (NumberFormatException e) {
            String str2 = TAG;
            Slog.w(str2, "Invalid APK version code in package entry: " + packageEntryStr);
            return false;
        }
    }
}
