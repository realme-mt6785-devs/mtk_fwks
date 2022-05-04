package com.oplus.multiapp;

import android.common.IOplusCommonFeature;
import android.common.OplusFeatureList;
import android.os.UserHandle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes4.dex */
public interface IOplusMultiApp extends IOplusCommonFeature {
    public static final IOplusMultiApp DEFAULT = new IOplusMultiApp() { // from class: com.oplus.multiapp.IOplusMultiApp.1
    };

    @Override // android.common.IOplusCommonFeature
    default OplusFeatureList.OplusIndex index() {
        return null;
    }

    @Override // android.common.IOplusCommonFeature
    default IOplusCommonFeature getDefault() {
        return DEFAULT;
    }

    default boolean isMultiAppSupport() {
        return false;
    }

    default int getMaxCreateNum() {
        return 0;
    }

    default List<String> getMultiAppList(int type) {
        return new ArrayList();
    }

    default boolean setMultiAppConfig(OplusMultiAppConfig config) {
        return false;
    }

    default OplusMultiAppConfig getMultiAppConfig() {
        return null;
    }

    default String getMultiAppAlias(String pkgName) {
        return "unkown";
    }

    default boolean setMultiAppAlias(String pkgName, String alias) {
        return false;
    }

    default int getMultiAppAccessMode(String pkgName) {
        return 0;
    }

    default boolean setMultiAppAccessMode(String pkgName, int accessMode) {
        return false;
    }

    default Map<String, Integer> getMultiAppAllAccessMode() {
        return new HashMap();
    }

    default boolean isMultiApp(int userId, String pkgName) {
        return false;
    }

    default boolean isMultiAppUserHandle(UserHandle userHandle) {
        return false;
    }

    default UserHandle getMultiAppUserHandle() {
        return null;
    }

    default int setMultiAppPackageStatus(String pkgName, int status) {
        return -1;
    }

    default boolean isMultiAppUserId(int userId) {
        return false;
    }

    default boolean isCrossUserAuthority(String authority, int userId) {
        return false;
    }

    default boolean isProfileFilterPackage(String pkgName) {
        return false;
    }

    default void scanFileIfNeed(int userId, String path) {
    }
}
