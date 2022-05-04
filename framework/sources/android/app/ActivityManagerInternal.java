package android.app;

import android.content.ComponentName;
import android.content.IIntentReceiver;
import android.content.IIntentSender;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ActivityPresentationInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.UserInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.TransactionTooLargeException;
import android.os.WorkSource;
import android.util.ArraySet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public abstract class ActivityManagerInternal {
    public static final int ALLOW_ALL_PROFILE_PERMISSIONS_IN_PROFILE = 3;
    public static final int ALLOW_FULL_ONLY = 2;
    public static final int ALLOW_NON_FULL = 0;
    public static final int ALLOW_NON_FULL_IN_PROFILE = 1;

    /* loaded from: classes.dex */
    public enum ServiceNotificationPolicy {
        NOT_FOREGROUND_SERVICE,
        SHOW_IMMEDIATELY,
        UPDATE_ONLY
    }

    public abstract void addPendingTopUid(int i, int i2);

    public abstract ServiceNotificationPolicy applyForegroundServiceNotification(Notification notification, String str, int i, String str2, int i2);

    public abstract void broadcastCloseSystemDialogs(String str);

    public abstract void broadcastGlobalConfigurationChanged(int i, boolean z);

    public abstract int broadcastIntent(Intent intent, IIntentReceiver iIntentReceiver, String[] strArr, boolean z, int i, int[] iArr, Bundle bundle);

    public abstract int broadcastIntentInPackage(String str, String str2, int i, int i2, int i3, Intent intent, String str3, IIntentReceiver iIntentReceiver, int i4, String str4, Bundle bundle, String str5, Bundle bundle2, boolean z, boolean z2, int i5, boolean z3, IBinder iBinder);

    public abstract boolean canStartMoreUsers();

    public abstract String checkContentProviderAccess(String str, int i);

    public abstract int checkContentProviderUriPermission(Uri uri, int i, int i2, int i3);

    public abstract void cleanUpServices(int i, ComponentName componentName, Intent intent);

    public abstract void clearPendingBackup(int i);

    public abstract void clearPendingIntentAllowBgActivityStarts(IIntentSender iIntentSender, IBinder iBinder);

    public abstract void deletePendingTopUid(int i);

    public abstract void disconnectActivityFromServices(Object obj);

    public abstract void enforceCallingPermission(String str, String str2);

    public abstract void ensureBootCompleted();

    public abstract void ensureNotSpecialUser(int i);

    public abstract void finishBooting();

    public abstract void finishUserSwitch(Object obj);

    public abstract ActivityInfo getActivityInfoForUser(ActivityInfo activityInfo, int i);

    public abstract ActivityPresentationInfo getActivityPresentationInfo(IBinder iBinder);

    public abstract long getBootTimeTempAllowListDuration();

    public abstract int[] getCurrentProfileIds();

    public abstract UserInfo getCurrentUser();

    public abstract int getCurrentUserId();

    public abstract Intent getIntentForIntentSender(IIntentSender iIntentSender);

    public abstract List<Integer> getIsolatedProcesses(int i);

    public abstract int getMaxRunningUsers();

    public abstract List<ProcessMemoryState> getMemoryStateForProcesses();

    public abstract PendingIntent getPendingIntentActivityAsApp(int i, Intent intent, int i2, Bundle bundle, String str, int i3);

    public abstract PendingIntent getPendingIntentActivityAsApp(int i, Intent[] intentArr, int i2, Bundle bundle, String str, int i3);

    public abstract int getPendingIntentFlags(IIntentSender iIntentSender);

    public abstract Map<Integer, String> getProcessesWithPendingBindMounts(int i);

    public abstract int getPushMessagingOverQuotaBehavior();

    public abstract int[] getStartedUserIds();

    public abstract int getStorageMountMode(int i, int i2);

    public abstract int getTaskIdForActivity(IBinder iBinder, boolean z);

    public abstract int getUidCapability(int i);

    public abstract int getUidProcessState(int i);

    public abstract int handleIncomingUser(int i, int i2, int i3, boolean z, int i4, String str, String str2);

    public abstract boolean hasForegroundServiceNotification(String str, int i, String str2);

    public abstract boolean hasRunningActivity(int i, String str);

    public abstract boolean hasRunningForegroundService(int i, int i2);

    public abstract boolean hasStartedUserState(int i);

    public abstract void inputDispatchingResumed(int i);

    public abstract long inputDispatchingTimedOut(int i, boolean z, String str);

    public abstract boolean inputDispatchingTimedOut(Object obj, String str, ApplicationInfo applicationInfo, String str2, Object obj2, boolean z, String str3);

    public abstract boolean isActivityStartsLoggingEnabled();

    public abstract boolean isAppBad(String str, int i);

    public abstract boolean isAppForeground(int i);

    public abstract boolean isAppStartModeDisabled(int i, String str);

    public abstract boolean isAssociatedCompanionApp(int i, int i2);

    public abstract boolean isBackgroundActivityStartsEnabled();

    public abstract boolean isBooted();

    public abstract boolean isBooting();

    public abstract boolean isCurrentProfile(int i);

    public abstract boolean isDeviceOwner(int i);

    public abstract boolean isPendingTopUid(int i);

    public abstract boolean isProfileOwner(int i);

    public abstract boolean isRuntimeRestarted();

    public abstract boolean isSystemReady();

    public abstract boolean isTempAllowlistedForFgsWhileInUse(int i);

    public abstract boolean isUidActive(int i);

    public abstract boolean isUidCurrentlyInstrumented(int i);

    public abstract boolean isUserRunning(int i, int i2);

    public abstract void killAllBackgroundProcessesExcept(int i, int i2);

    public abstract void killForegroundAppsForUser(int i);

    public abstract void killProcess(String str, int i, String str2);

    public abstract void killProcessesForRemovedTask(ArrayList<Object> arrayList);

    public abstract void monitor();

    public abstract void noteAlarmFinish(PendingIntent pendingIntent, WorkSource workSource, int i, String str);

    public abstract void noteAlarmStart(PendingIntent pendingIntent, WorkSource workSource, int i, String str);

    public abstract void noteWakeupAlarm(PendingIntent pendingIntent, WorkSource workSource, int i, String str, String str2);

    public abstract void notifyNetworkPolicyRulesUpdated(int i, long j);

    public abstract void onForegroundServiceNotificationUpdate(boolean z, Notification notification, int i, String str, int i2);

    public abstract void onUserRemoved(int i);

    public abstract void onWakefulnessChanged(int i);

    public abstract void prepareForPossibleShutdown();

    public abstract void registerAnrController(AnrController anrController);

    public abstract void registerProcessObserver(IProcessObserver iProcessObserver);

    public abstract void reportCurKeyguardUsageEvent(boolean z);

    public abstract void scheduleAppGcs();

    public abstract void sendForegroundProfileChanged(int i);

    public abstract void setBooted(boolean z);

    public abstract void setBooting(boolean z);

    public abstract void setCompanionAppUids(int i, Set<Integer> set);

    public abstract void setDebugFlagsForStartingActivity(ActivityInfo activityInfo, int i, ProfilerInfo profilerInfo, Object obj);

    public abstract void setDeviceIdleAllowlist(int[] iArr, int[] iArr2);

    public abstract void setDeviceOwnerUid(int i);

    public abstract void setHasOverlayUi(int i, boolean z);

    public abstract void setPendingIntentAllowBgActivityStarts(IIntentSender iIntentSender, IBinder iBinder, int i);

    public abstract void setPendingIntentAllowlistDuration(IIntentSender iIntentSender, IBinder iBinder, long j, int i, int i2, String str);

    public abstract void setProfileOwnerUid(ArraySet<Integer> arraySet);

    public abstract void setSwitchingFromSystemUserMessage(String str);

    public abstract void setSwitchingToSystemUserMessage(String str);

    public abstract boolean shouldConfirmCredentials(int i);

    public abstract boolean startIsolatedProcess(String str, String[] strArr, String str2, String str3, int i, Runnable runnable);

    public abstract void startProcess(String str, ApplicationInfo applicationInfo, boolean z, boolean z2, String str2, ComponentName componentName);

    public abstract ComponentName startServiceInPackage(int i, Intent intent, String str, boolean z, String str2, String str3, int i2, boolean z2, IBinder iBinder) throws TransactionTooLargeException;

    public abstract void stopForegroundServicesForChannel(String str, int i, String str2);

    public abstract void tempAllowlistForPendingIntent(int i, int i2, int i3, long j, int i4, int i5, String str);

    public abstract void trimApplications();

    public abstract void unregisterAnrController(AnrController anrController);

    public abstract void unregisterProcessObserver(IProcessObserver iProcessObserver);

    public abstract void updateActivityUsageStats(ComponentName componentName, int i, int i2, IBinder iBinder, ComponentName componentName2);

    public abstract void updateBatteryStats(ComponentName componentName, int i, int i2, boolean z);

    public abstract void updateCpuStats();

    public abstract void updateDeviceIdleTempAllowlist(int[] iArr, int i, boolean z, long j, int i2, int i3, String str, int i4);

    public abstract void updateForegroundTimeIfOnBattery(String str, int i, long j);

    public abstract void updateOomAdj();

    public abstract void updateOomLevelsForDisplay(int i);
}
