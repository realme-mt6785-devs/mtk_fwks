package com.android.net.module.util;

import android.Manifest;
import android.content.Context;
import android.media.MediaMetrics;
import android.net.NetworkStack;
import java.util.ArrayList;
import java.util.Arrays;
/* loaded from: classes4.dex */
public final class PermissionUtils {
    public static boolean checkAnyPermissionOf(Context context, String... permissions) {
        for (String permission : permissions) {
            if (context.checkCallingOrSelfPermission(permission) == 0) {
                return true;
            }
        }
        return false;
    }

    public static void enforceAnyPermissionOf(Context context, String... permissions) {
        if (!checkAnyPermissionOf(context, permissions)) {
            throw new SecurityException("Requires one of the following permissions: " + String.join(", ", permissions) + MediaMetrics.SEPARATOR);
        }
    }

    public static void enforceNetworkStackPermission(Context context) {
        enforceNetworkStackPermissionOr(context, new String[0]);
    }

    public static void enforceNetworkStackPermissionOr(Context context, String... otherPermissions) {
        ArrayList<String> permissions = new ArrayList<>(Arrays.asList(otherPermissions));
        permissions.add(Manifest.permission.NETWORK_STACK);
        permissions.add(NetworkStack.PERMISSION_MAINLINE_NETWORK_STACK);
        enforceAnyPermissionOf(context, (String[]) permissions.toArray(new String[0]));
    }
}
