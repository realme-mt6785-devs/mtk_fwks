package com.oplus.multiapp;

import android.os.UserHandle;
import android.util.Singleton;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
/* loaded from: classes4.dex */
public class OplusMultiAppManager extends BaseOplusMultiAppManager {
    public static final String ACTION_MULTI_APP_ALIAS_CHANGED = "oplus.intent.action.MULTI_APP_RENAME";
    public static final String ACTION_MULTI_APP_CONFIG_CHANGED = "oplus.intent.action.MULTI_APP_CONFIG_CHANGED";
    public static final String ACTION_MULTI_APP_PACKAGE_ADDED = "oplus.intent.action.MULTI_APP_PACKAGE_ADDED";
    public static final String ACTION_MULTI_APP_PACKAGE_REMOVED = "oplus.intent.action.MULTI_APP_PACKAGE_REMOVED";
    public static final int DEFAULT_ACCESS = 0;
    public static final String EXTRA_ALIAS = "name";
    public static final String EXTRA_PACKAGE_NAME = "pkg";
    public static final String EXTRA_STATUS = "extra_status";
    public static final int LIST_TYPE_ACROSS_AUTHORITY = 4;
    public static final int LIST_TYPE_ALLOWED = 1;
    public static final int LIST_TYPE_CREATED = 0;
    public static final int LIST_TYPE_INSTALLED = 3;
    public static final int LIST_TYPE_RELATED = 2;
    public static final int MAIN_APP_ACCESS = 1;
    public static final int MULTI_APP_STATUS_ADD = 1;
    public static final int MULTI_APP_STATUS_REMOVE = -1;
    public static final int RESULT_ERROR_NOT_SUPPORT = -2;
    public static final int RESULT_ERROR_NO_SPACE = -3;
    public static final int RESULT_FAILED = -1;
    public static final int RESULT_SUCCESS = 1;
    public static final String VOLUME_MAIN = "ace-0";
    public static final String VOLUME_MAIN_PATH = "/storage/ace-0";
    public static final String VOLUME_MULTI_APP = "ace-999";
    public static final String VOLUME_MULTI_APP_PATH = "/storage/ace-999";
    public static final List<String> KEEP_CROSS_VOLUME_PKG = Arrays.asList("com.google.android.providers.media.module", "android");
    private static final Singleton<OplusMultiAppManager> sOplusMultiAppManagerSingleton = new Singleton<OplusMultiAppManager>() { // from class: com.oplus.multiapp.OplusMultiAppManager.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.util.Singleton
        public OplusMultiAppManager create() {
            return new OplusMultiAppManager();
        }
    };

    private OplusMultiAppManager() {
    }

    public static OplusMultiAppManager getInstance() {
        return sOplusMultiAppManagerSingleton.get();
    }

    public int getMaxCreateNum() {
        return this.oplusMultiApp.getMaxCreateNum();
    }

    public List<String> getMultiAppList(int type) {
        return this.oplusMultiApp.getMultiAppList(type);
    }

    public boolean setMultiAppConfig(OplusMultiAppConfig config) {
        return this.oplusMultiApp.setMultiAppConfig(config);
    }

    public OplusMultiAppConfig getMultiAppConfig() {
        return this.oplusMultiApp.getMultiAppConfig();
    }

    public int setMultiAppStatus(String pkgName, int status) {
        return this.oplusMultiApp.setMultiAppPackageStatus(pkgName, status);
    }

    public boolean setMultiAppAlias(String pkgName, String alias) {
        return this.oplusMultiApp.setMultiAppAlias(pkgName, alias);
    }

    public String getMultiAppAlias(String pkgName) {
        return this.oplusMultiApp.getMultiAppAlias(pkgName);
    }

    public boolean setMultiAppAccessMode(String pkgName, int accessMode) {
        return this.oplusMultiApp.setMultiAppAccessMode(pkgName, accessMode);
    }

    public int getMultiAppAccessMode(String pkgName) {
        return this.oplusMultiApp.getMultiAppAccessMode(pkgName);
    }

    public Map<String, Integer> getMultiAppAllAccessMode() {
        return this.oplusMultiApp.getMultiAppAllAccessMode();
    }

    public boolean isMultiApp(int userId, String pkgName) {
        return this.oplusMultiApp.isMultiApp(userId, pkgName);
    }

    public boolean isMultiAppSupport() {
        return this.oplusMultiApp.isMultiAppSupport();
    }

    public boolean isMultiAppUserHandle(UserHandle userHandle) {
        return this.oplusMultiApp.isMultiAppUserHandle(userHandle);
    }

    public UserHandle getMultiAppUserHandle() {
        return this.oplusMultiApp.getMultiAppUserHandle();
    }
}
