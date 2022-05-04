package com.android.internal.telephony;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageManager;
/* loaded from: classes4.dex */
public interface IOplusSmsApplication {
    public static final int MAIN_USER_ID = 0;

    /* loaded from: classes4.dex */
    public interface Action {
        void doAction(String str);
    }

    default boolean shouldWriteMessageForPackage(String packageName) {
        return true;
    }

    default void oemAssignExclusiveSmsPermissionsToSystemApp(Context context, PackageManager packageManager, AppOpsManager appOps, Action action) {
    }

    default void setUserId(int userId) {
    }

    default int getUserId() {
        return 0;
    }

    default boolean isUserIdEqualMainUserId() {
        return false;
    }

    default String[] oemPackageName() {
        return null;
    }
}
