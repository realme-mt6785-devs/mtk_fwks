package com.oplus.permission;

import android.common.IOplusCommonFeature;
import android.common.OplusFeatureList;
import android.content.ContentProviderOperation;
import android.content.Intent;
import android.net.Uri;
import java.util.ArrayList;
/* loaded from: classes4.dex */
public interface IOplusPermissionCheckInjector extends IOplusCommonFeature {
    public static final IOplusPermissionCheckInjector DEFAULT = new IOplusPermissionCheckInjector() { // from class: com.oplus.permission.IOplusPermissionCheckInjector.1
    };

    @Override // android.common.IOplusCommonFeature
    default IOplusPermissionCheckInjector getDefault() {
        return DEFAULT;
    }

    @Override // android.common.IOplusCommonFeature
    default OplusFeatureList.OplusIndex index() {
        return OplusFeatureList.OplusIndex.IOplusPermissionCheckInjector;
    }

    default boolean checkPermission(String permission, int pid, int uid, String access) {
        return true;
    }

    default boolean checkPermission(Intent intent, int pid, int uid, String access) {
        return true;
    }

    default boolean checkUriPermission(Uri uri, int pid, int uid, String access) {
        return true;
    }

    default boolean checkApplyBatchPermission(ArrayList<ContentProviderOperation> operations, int pid, int uid, String access) {
        return true;
    }
}
