package com.android.server.usage;

import android.app.usage.AppStandbyInfo;
import android.content.Context;
import android.util.IndentingPrintWriter;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;
/* loaded from: classes4.dex */
public interface AppStandbyInternal {
    void addActiveDeviceAdmin(String str, int i);

    void addListener(AppIdleStateChangeListener appIdleStateChangeListener);

    void clearCarrierPrivilegedApps();

    void dumpState(String[] strArr, PrintWriter printWriter);

    void dumpUsers(IndentingPrintWriter indentingPrintWriter, int[] iArr, List<String> list);

    void flushToDisk();

    int getAppId(String str);

    int getAppStandbyBucket(String str, int i, long j, boolean z);

    List<AppStandbyInfo> getAppStandbyBuckets(int i);

    int[] getIdleUidsForUser(int i);

    long getTimeSinceLastJobRun(String str, int i);

    void initializeDefaultsForSystemApps(int i);

    boolean isAppIdleEnabled();

    boolean isAppIdleFiltered(String str, int i, int i2, long j);

    boolean isAppIdleFiltered(String str, int i, long j, boolean z);

    boolean isInParole();

    void onAdminDataAvailable();

    void onBootPhase(int i);

    void onUserRemoved(int i);

    void postCheckIdleStates(int i);

    void postOneTimeCheckIdleStates();

    void postReportContentProviderUsage(String str, String str2, int i);

    void postReportExemptedSyncStart(String str, int i);

    void postReportSyncScheduled(String str, int i, boolean z);

    void removeListener(AppIdleStateChangeListener appIdleStateChangeListener);

    void restrictApp(String str, int i, int i2);

    void restrictApp(String str, int i, int i2, int i3);

    void setActiveAdminApps(Set<String> set, int i);

    void setAppIdleAsync(String str, boolean z, int i);

    void setAppStandbyBucket(String str, int i, int i2, int i3, int i4);

    void setAppStandbyBuckets(List<AppStandbyInfo> list, int i, int i2, int i3);

    void setLastJobRunTime(String str, int i, long j);

    static AppStandbyInternal newAppStandbyController(ClassLoader loader, Context context) {
        try {
            Class<?> clazz = Class.forName("com.android.server.usage.AppStandbyController", true, loader);
            Constructor<?> ctor = clazz.getConstructor(Context.class);
            return (AppStandbyInternal) ctor.newInstance(context);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException("Unable to instantiate AppStandbyController!", e);
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class AppIdleStateChangeListener {
        public abstract void onAppIdleStateChanged(String str, int i, boolean z, int i2, int i3);

        public void onParoleStateChanged(boolean isParoleOn) {
        }

        public void onUserInteractionStarted(String packageName, int userId) {
        }
    }
}
