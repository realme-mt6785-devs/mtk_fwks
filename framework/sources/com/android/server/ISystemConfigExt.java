package com.android.server;

import android.content.pm.FeatureInfo;
import android.util.ArrayMap;
import java.io.File;
/* loaded from: classes4.dex */
public interface ISystemConfigExt {
    default void readConfigInConstructor() {
    }

    default ArrayMap<String, FeatureInfo> loadOplusAvailableFeatures(String name) {
        return new ArrayMap<>();
    }

    default boolean filterFileInReadPermissions(File file) {
        return false;
    }

    default boolean isFilePartitionWithProductFlag(File file) {
        return false;
    }

    default boolean interceptRemoveFeatureInRead(String featureName) {
        return false;
    }

    default void onAddFeatureInRead(String name, int version, File file) {
    }

    default void onAddUnAvailableFeatureInRead(String name, File file) {
    }

    default boolean skipTagExceptionAndReturn(String tagName, File permFile) {
        return false;
    }

    default void addPowerSaveWhitelistForCota(String pkgname) {
    }

    default void addPowerSaveWhitelistExceptIdleForCota(String pkgname) {
    }
}
