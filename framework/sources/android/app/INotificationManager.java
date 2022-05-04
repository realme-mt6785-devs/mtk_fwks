package android.app;

import android.app.ITransientNotification;
import android.app.ITransientNotificationCallback;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.pm.ParceledListSlice;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.UserHandle;
import android.service.notification.Adjustment;
import android.service.notification.Condition;
import android.service.notification.IConditionProvider;
import android.service.notification.INotificationListener;
import android.service.notification.NotificationListenerFilter;
import android.service.notification.StatusBarNotification;
import android.service.notification.ZenModeConfig;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public interface INotificationManager extends IInterface {
    String addAutomaticZenRule(AutomaticZenRule automaticZenRule, String str) throws RemoteException;

    void allowAssistantAdjustment(String str) throws RemoteException;

    void applyAdjustmentFromAssistant(INotificationListener iNotificationListener, Adjustment adjustment) throws RemoteException;

    void applyAdjustmentsFromAssistant(INotificationListener iNotificationListener, List<Adjustment> list) throws RemoteException;

    void applyEnqueuedAdjustmentFromAssistant(INotificationListener iNotificationListener, Adjustment adjustment) throws RemoteException;

    void applyRestore(byte[] bArr, int i) throws RemoteException;

    boolean areBubblesAllowed(String str) throws RemoteException;

    boolean areBubblesEnabled(UserHandle userHandle) throws RemoteException;

    boolean areChannelsBypassingDnd() throws RemoteException;

    boolean areNotificationsEnabled(String str) throws RemoteException;

    boolean areNotificationsEnabledForPackage(String str, int i) throws RemoteException;

    boolean canNotifyAsPackage(String str, String str2, int i) throws RemoteException;

    boolean canShowBadge(String str, int i) throws RemoteException;

    void cancelAllNotifications(String str, int i) throws RemoteException;

    void cancelNotificationFromListener(INotificationListener iNotificationListener, String str, String str2, int i) throws RemoteException;

    void cancelNotificationWithTag(String str, String str2, String str3, int i, int i2) throws RemoteException;

    void cancelNotificationsFromListener(INotificationListener iNotificationListener, String[] strArr) throws RemoteException;

    void cancelToast(String str, IBinder iBinder) throws RemoteException;

    void clearData(String str, int i, boolean z) throws RemoteException;

    void clearRequestedListenerHints(INotificationListener iNotificationListener) throws RemoteException;

    void createConversationNotificationChannelForPackage(String str, int i, NotificationChannel notificationChannel, String str2) throws RemoteException;

    void createNotificationChannelGroups(String str, ParceledListSlice parceledListSlice) throws RemoteException;

    void createNotificationChannels(String str, ParceledListSlice parceledListSlice) throws RemoteException;

    void createNotificationChannelsForPackage(String str, int i, ParceledListSlice parceledListSlice) throws RemoteException;

    void deleteNotificationChannel(String str, String str2) throws RemoteException;

    void deleteNotificationChannelGroup(String str, String str2) throws RemoteException;

    void deleteNotificationHistoryItem(String str, int i, long j) throws RemoteException;

    void disallowAssistantAdjustment(String str) throws RemoteException;

    void enqueueNotificationWithTag(String str, String str2, String str3, int i, Notification notification, int i2) throws RemoteException;

    void enqueueTextToast(String str, IBinder iBinder, CharSequence charSequence, int i, int i2, ITransientNotificationCallback iTransientNotificationCallback) throws RemoteException;

    void enqueueToast(String str, IBinder iBinder, ITransientNotification iTransientNotification, int i, int i2) throws RemoteException;

    void finishToken(String str, IBinder iBinder) throws RemoteException;

    StatusBarNotification[] getActiveNotifications(String str) throws RemoteException;

    ParceledListSlice getActiveNotificationsFromListener(INotificationListener iNotificationListener, String[] strArr, int i) throws RemoteException;

    StatusBarNotification[] getActiveNotificationsWithAttribution(String str, String str2) throws RemoteException;

    List<String> getAllowedAssistantAdjustments(String str) throws RemoteException;

    ComponentName getAllowedNotificationAssistant() throws RemoteException;

    ComponentName getAllowedNotificationAssistantForUser(int i) throws RemoteException;

    ParceledListSlice getAppActiveNotifications(String str, int i) throws RemoteException;

    int getAppsBypassingDndCount(int i) throws RemoteException;

    AutomaticZenRule getAutomaticZenRule(String str) throws RemoteException;

    byte[] getBackupPayload(int i) throws RemoteException;

    int getBlockedAppCount(int i) throws RemoteException;

    int getBlockedChannelCount(String str, int i) throws RemoteException;

    int getBubblePreferenceForPackage(String str, int i) throws RemoteException;

    NotificationManager.Policy getConsolidatedNotificationPolicy() throws RemoteException;

    NotificationChannel getConversationNotificationChannel(String str, int i, String str2, String str3, boolean z, String str4) throws RemoteException;

    ParceledListSlice getConversations(boolean z) throws RemoteException;

    ParceledListSlice getConversationsForPackage(String str, int i) throws RemoteException;

    ComponentName getDefaultNotificationAssistant() throws RemoteException;

    int getDeletedChannelCount(String str, int i) throws RemoteException;

    ComponentName getEffectsSuppressor() throws RemoteException;

    List<String> getEnabledNotificationListenerPackages() throws RemoteException;

    List<ComponentName> getEnabledNotificationListeners(int i) throws RemoteException;

    int getHintsFromListener(INotificationListener iNotificationListener) throws RemoteException;

    StatusBarNotification[] getHistoricalNotifications(String str, int i, boolean z) throws RemoteException;

    StatusBarNotification[] getHistoricalNotificationsWithAttribution(String str, String str2, int i, boolean z) throws RemoteException;

    int getInterruptionFilterFromListener(INotificationListener iNotificationListener) throws RemoteException;

    NotificationListenerFilter getListenerFilter(ComponentName componentName, int i) throws RemoteException;

    NotificationChannel getNotificationChannel(String str, int i, String str2, String str3) throws RemoteException;

    NotificationChannel getNotificationChannelForPackage(String str, int i, String str2, String str3, boolean z) throws RemoteException;

    NotificationChannelGroup getNotificationChannelGroup(String str, String str2) throws RemoteException;

    NotificationChannelGroup getNotificationChannelGroupForPackage(String str, String str2, int i) throws RemoteException;

    ParceledListSlice getNotificationChannelGroups(String str) throws RemoteException;

    ParceledListSlice getNotificationChannelGroupsForPackage(String str, int i, boolean z) throws RemoteException;

    ParceledListSlice getNotificationChannelGroupsFromPrivilegedListener(INotificationListener iNotificationListener, String str, UserHandle userHandle) throws RemoteException;

    ParceledListSlice getNotificationChannels(String str, String str2, int i) throws RemoteException;

    ParceledListSlice getNotificationChannelsBypassingDnd(String str, int i) throws RemoteException;

    ParceledListSlice getNotificationChannelsForPackage(String str, int i, boolean z) throws RemoteException;

    ParceledListSlice getNotificationChannelsFromPrivilegedListener(INotificationListener iNotificationListener, String str, UserHandle userHandle) throws RemoteException;

    String getNotificationDelegate(String str) throws RemoteException;

    NotificationHistory getNotificationHistory(String str, String str2) throws RemoteException;

    NotificationManager.Policy getNotificationPolicy(String str) throws RemoteException;

    int getNumNotificationChannelsForPackage(String str, int i, boolean z) throws RemoteException;

    int getPackageImportance(String str) throws RemoteException;

    NotificationChannelGroup getPopulatedNotificationChannelGroupForPackage(String str, int i, String str2, boolean z) throws RemoteException;

    boolean getPrivateNotificationsAllowed() throws RemoteException;

    int getRuleInstanceCount(ComponentName componentName) throws RemoteException;

    ParceledListSlice getSnoozedNotificationsFromListener(INotificationListener iNotificationListener, int i) throws RemoteException;

    int getZenMode() throws RemoteException;

    ZenModeConfig getZenModeConfig() throws RemoteException;

    List<ZenModeConfig.ZenRule> getZenRules() throws RemoteException;

    boolean hasEnabledNotificationListener(String str, int i) throws RemoteException;

    boolean hasSentValidMsg(String str, int i) throws RemoteException;

    boolean hasUserDemotedInvalidMsgApp(String str, int i) throws RemoteException;

    boolean isInInvalidMsgState(String str, int i) throws RemoteException;

    boolean isNotificationAssistantAccessGranted(ComponentName componentName) throws RemoteException;

    boolean isNotificationListenerAccessGranted(ComponentName componentName) throws RemoteException;

    boolean isNotificationListenerAccessGrantedForUser(ComponentName componentName, int i) throws RemoteException;

    boolean isNotificationPolicyAccessGranted(String str) throws RemoteException;

    boolean isNotificationPolicyAccessGrantedForPackage(String str) throws RemoteException;

    boolean isPackagePaused(String str) throws RemoteException;

    boolean isSystemConditionProviderEnabled(String str) throws RemoteException;

    boolean matchesCallFilter(Bundle bundle) throws RemoteException;

    void migrateNotificationFilter(INotificationListener iNotificationListener, int i, List<String> list) throws RemoteException;

    void notifyConditions(String str, IConditionProvider iConditionProvider, Condition[] conditionArr) throws RemoteException;

    boolean onlyHasDefaultChannel(String str, int i) throws RemoteException;

    long pullStats(long j, int i, boolean z, List<ParcelFileDescriptor> list) throws RemoteException;

    void registerListener(INotificationListener iNotificationListener, ComponentName componentName, int i) throws RemoteException;

    boolean removeAutomaticZenRule(String str) throws RemoteException;

    boolean removeAutomaticZenRules(String str) throws RemoteException;

    void requestBindListener(ComponentName componentName) throws RemoteException;

    void requestBindProvider(ComponentName componentName) throws RemoteException;

    void requestHintsFromListener(INotificationListener iNotificationListener, int i) throws RemoteException;

    void requestInterruptionFilterFromListener(INotificationListener iNotificationListener, int i) throws RemoteException;

    void requestUnbindListener(INotificationListener iNotificationListener) throws RemoteException;

    void requestUnbindProvider(IConditionProvider iConditionProvider) throws RemoteException;

    void setAutomaticZenRuleState(String str, Condition condition) throws RemoteException;

    void setBubblesAllowed(String str, int i, int i2) throws RemoteException;

    void setHideSilentStatusIcons(boolean z) throws RemoteException;

    void setInterruptionFilter(String str, int i) throws RemoteException;

    void setInvalidMsgAppDemoted(String str, int i, boolean z) throws RemoteException;

    void setListenerFilter(ComponentName componentName, int i, NotificationListenerFilter notificationListenerFilter) throws RemoteException;

    void setNASMigrationDoneAndResetDefault(int i, boolean z) throws RemoteException;

    void setNotificationAssistantAccessGranted(ComponentName componentName, boolean z) throws RemoteException;

    void setNotificationAssistantAccessGrantedForUser(ComponentName componentName, int i, boolean z) throws RemoteException;

    void setNotificationDelegate(String str, String str2) throws RemoteException;

    void setNotificationListenerAccessGranted(ComponentName componentName, boolean z, boolean z2) throws RemoteException;

    void setNotificationListenerAccessGrantedForUser(ComponentName componentName, int i, boolean z, boolean z2) throws RemoteException;

    void setNotificationPolicy(String str, NotificationManager.Policy policy) throws RemoteException;

    void setNotificationPolicyAccessGranted(String str, boolean z) throws RemoteException;

    void setNotificationPolicyAccessGrantedForUser(String str, int i, boolean z) throws RemoteException;

    void setNotificationsEnabledForPackage(String str, int i, boolean z) throws RemoteException;

    void setNotificationsEnabledWithImportanceLockForPackage(String str, int i, boolean z) throws RemoteException;

    void setNotificationsShownFromListener(INotificationListener iNotificationListener, String[] strArr) throws RemoteException;

    void setOnNotificationPostedTrimFromListener(INotificationListener iNotificationListener, int i) throws RemoteException;

    void setPrivateNotificationsAllowed(boolean z) throws RemoteException;

    void setShowBadge(String str, int i, boolean z) throws RemoteException;

    void setToastRateLimitingEnabled(boolean z) throws RemoteException;

    void setZenMode(int i, Uri uri, String str) throws RemoteException;

    boolean shouldHideSilentStatusIcons(String str) throws RemoteException;

    void silenceNotificationSound() throws RemoteException;

    void snoozeNotificationUntilContextFromListener(INotificationListener iNotificationListener, String str, String str2) throws RemoteException;

    void snoozeNotificationUntilFromListener(INotificationListener iNotificationListener, String str, long j) throws RemoteException;

    void unlockAllNotificationChannels() throws RemoteException;

    void unlockNotificationChannel(String str, int i, String str2) throws RemoteException;

    void unregisterListener(INotificationListener iNotificationListener, int i) throws RemoteException;

    void unsnoozeNotificationFromAssistant(INotificationListener iNotificationListener, String str) throws RemoteException;

    void unsnoozeNotificationFromSystemListener(INotificationListener iNotificationListener, String str) throws RemoteException;

    boolean updateAutomaticZenRule(String str, AutomaticZenRule automaticZenRule) throws RemoteException;

    void updateNotificationChannelForPackage(String str, int i, NotificationChannel notificationChannel) throws RemoteException;

    void updateNotificationChannelFromPrivilegedListener(INotificationListener iNotificationListener, String str, UserHandle userHandle, NotificationChannel notificationChannel) throws RemoteException;

    void updateNotificationChannelGroupForPackage(String str, int i, NotificationChannelGroup notificationChannelGroup) throws RemoteException;

    void updateNotificationChannelGroupFromPrivilegedListener(INotificationListener iNotificationListener, String str, UserHandle userHandle, NotificationChannelGroup notificationChannelGroup) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements INotificationManager {
        @Override // android.app.INotificationManager
        public void cancelAllNotifications(String pkg, int userId) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void clearData(String pkg, int uid, boolean fromApp) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void enqueueTextToast(String pkg, IBinder token, CharSequence text, int duration, int displayId, ITransientNotificationCallback callback) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void enqueueToast(String pkg, IBinder token, ITransientNotification callback, int duration, int displayId) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void cancelToast(String pkg, IBinder token) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void finishToken(String pkg, IBinder token) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void enqueueNotificationWithTag(String pkg, String opPkg, String tag, int id, Notification notification, int userId) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void cancelNotificationWithTag(String pkg, String opPkg, String tag, int id, int userId) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setShowBadge(String pkg, int uid, boolean showBadge) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public boolean canShowBadge(String pkg, int uid) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public boolean hasSentValidMsg(String pkg, int uid) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public boolean isInInvalidMsgState(String pkg, int uid) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public boolean hasUserDemotedInvalidMsgApp(String pkg, int uid) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public void setInvalidMsgAppDemoted(String pkg, int uid, boolean isDemoted) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setNotificationsEnabledForPackage(String pkg, int uid, boolean enabled) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setNotificationsEnabledWithImportanceLockForPackage(String pkg, int uid, boolean enabled) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public boolean areNotificationsEnabledForPackage(String pkg, int uid) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public boolean areNotificationsEnabled(String pkg) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public int getPackageImportance(String pkg) throws RemoteException {
            return 0;
        }

        @Override // android.app.INotificationManager
        public List<String> getAllowedAssistantAdjustments(String pkg) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public void allowAssistantAdjustment(String adjustmentType) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void disallowAssistantAdjustment(String adjustmentType) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public boolean shouldHideSilentStatusIcons(String callingPkg) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public void setHideSilentStatusIcons(boolean hide) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setBubblesAllowed(String pkg, int uid, int bubblePreference) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public boolean areBubblesAllowed(String pkg) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public boolean areBubblesEnabled(UserHandle user) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public int getBubblePreferenceForPackage(String pkg, int uid) throws RemoteException {
            return 0;
        }

        @Override // android.app.INotificationManager
        public void createNotificationChannelGroups(String pkg, ParceledListSlice channelGroupList) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void createNotificationChannels(String pkg, ParceledListSlice channelsList) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void createNotificationChannelsForPackage(String pkg, int uid, ParceledListSlice channelsList) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public ParceledListSlice getConversations(boolean onlyImportant) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public ParceledListSlice getConversationsForPackage(String pkg, int uid) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public ParceledListSlice getNotificationChannelGroupsForPackage(String pkg, int uid, boolean includeDeleted) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public NotificationChannelGroup getNotificationChannelGroupForPackage(String groupId, String pkg, int uid) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public NotificationChannelGroup getPopulatedNotificationChannelGroupForPackage(String pkg, int uid, String groupId, boolean includeDeleted) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public void updateNotificationChannelGroupForPackage(String pkg, int uid, NotificationChannelGroup group) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void updateNotificationChannelForPackage(String pkg, int uid, NotificationChannel channel) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void unlockNotificationChannel(String pkg, int uid, String channelId) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void unlockAllNotificationChannels() throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public NotificationChannel getNotificationChannel(String callingPkg, int userId, String pkg, String channelId) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public NotificationChannel getConversationNotificationChannel(String callingPkg, int userId, String pkg, String channelId, boolean returnParentIfNoConversationChannel, String conversationId) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public void createConversationNotificationChannelForPackage(String pkg, int uid, NotificationChannel parentChannel, String conversationId) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public NotificationChannel getNotificationChannelForPackage(String pkg, int uid, String channelId, String conversationId, boolean includeDeleted) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public void deleteNotificationChannel(String pkg, String channelId) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public ParceledListSlice getNotificationChannels(String callingPkg, String targetPkg, int userId) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public ParceledListSlice getNotificationChannelsForPackage(String pkg, int uid, boolean includeDeleted) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public int getNumNotificationChannelsForPackage(String pkg, int uid, boolean includeDeleted) throws RemoteException {
            return 0;
        }

        @Override // android.app.INotificationManager
        public int getDeletedChannelCount(String pkg, int uid) throws RemoteException {
            return 0;
        }

        @Override // android.app.INotificationManager
        public int getBlockedChannelCount(String pkg, int uid) throws RemoteException {
            return 0;
        }

        @Override // android.app.INotificationManager
        public void deleteNotificationChannelGroup(String pkg, String channelGroupId) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public NotificationChannelGroup getNotificationChannelGroup(String pkg, String channelGroupId) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public ParceledListSlice getNotificationChannelGroups(String pkg) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public boolean onlyHasDefaultChannel(String pkg, int uid) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public int getBlockedAppCount(int userId) throws RemoteException {
            return 0;
        }

        @Override // android.app.INotificationManager
        public boolean areChannelsBypassingDnd() throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public int getAppsBypassingDndCount(int uid) throws RemoteException {
            return 0;
        }

        @Override // android.app.INotificationManager
        public ParceledListSlice getNotificationChannelsBypassingDnd(String pkg, int userId) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public boolean isPackagePaused(String pkg) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public void deleteNotificationHistoryItem(String pkg, int uid, long postedTime) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void silenceNotificationSound() throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public StatusBarNotification[] getActiveNotifications(String callingPkg) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public StatusBarNotification[] getActiveNotificationsWithAttribution(String callingPkg, String callingAttributionTag) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public StatusBarNotification[] getHistoricalNotifications(String callingPkg, int count, boolean includeSnoozed) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public StatusBarNotification[] getHistoricalNotificationsWithAttribution(String callingPkg, String callingAttributionTag, int count, boolean includeSnoozed) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public NotificationHistory getNotificationHistory(String callingPkg, String callingAttributionTag) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public void registerListener(INotificationListener listener, ComponentName component, int userid) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void unregisterListener(INotificationListener listener, int userid) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void cancelNotificationFromListener(INotificationListener token, String pkg, String tag, int id) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void cancelNotificationsFromListener(INotificationListener token, String[] keys) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void snoozeNotificationUntilContextFromListener(INotificationListener token, String key, String snoozeCriterionId) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void snoozeNotificationUntilFromListener(INotificationListener token, String key, long until) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void requestBindListener(ComponentName component) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void requestUnbindListener(INotificationListener token) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void requestBindProvider(ComponentName component) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void requestUnbindProvider(IConditionProvider token) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setNotificationsShownFromListener(INotificationListener token, String[] keys) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public ParceledListSlice getActiveNotificationsFromListener(INotificationListener token, String[] keys, int trim) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public ParceledListSlice getSnoozedNotificationsFromListener(INotificationListener token, int trim) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public void clearRequestedListenerHints(INotificationListener token) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void requestHintsFromListener(INotificationListener token, int hints) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public int getHintsFromListener(INotificationListener token) throws RemoteException {
            return 0;
        }

        @Override // android.app.INotificationManager
        public void requestInterruptionFilterFromListener(INotificationListener token, int interruptionFilter) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public int getInterruptionFilterFromListener(INotificationListener token) throws RemoteException {
            return 0;
        }

        @Override // android.app.INotificationManager
        public void setOnNotificationPostedTrimFromListener(INotificationListener token, int trim) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setInterruptionFilter(String pkg, int interruptionFilter) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void updateNotificationChannelGroupFromPrivilegedListener(INotificationListener token, String pkg, UserHandle user, NotificationChannelGroup group) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void updateNotificationChannelFromPrivilegedListener(INotificationListener token, String pkg, UserHandle user, NotificationChannel channel) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public ParceledListSlice getNotificationChannelsFromPrivilegedListener(INotificationListener token, String pkg, UserHandle user) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public ParceledListSlice getNotificationChannelGroupsFromPrivilegedListener(INotificationListener token, String pkg, UserHandle user) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public void applyEnqueuedAdjustmentFromAssistant(INotificationListener token, Adjustment adjustment) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void applyAdjustmentFromAssistant(INotificationListener token, Adjustment adjustment) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void applyAdjustmentsFromAssistant(INotificationListener token, List<Adjustment> adjustments) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void unsnoozeNotificationFromAssistant(INotificationListener token, String key) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void unsnoozeNotificationFromSystemListener(INotificationListener token, String key) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public ComponentName getEffectsSuppressor() throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public boolean matchesCallFilter(Bundle extras) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public boolean isSystemConditionProviderEnabled(String path) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public boolean isNotificationListenerAccessGranted(ComponentName listener) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public boolean isNotificationListenerAccessGrantedForUser(ComponentName listener, int userId) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public boolean isNotificationAssistantAccessGranted(ComponentName assistant) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public void setNotificationListenerAccessGranted(ComponentName listener, boolean enabled, boolean userSet) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setNotificationAssistantAccessGranted(ComponentName assistant, boolean enabled) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setNotificationListenerAccessGrantedForUser(ComponentName listener, int userId, boolean enabled, boolean userSet) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setNotificationAssistantAccessGrantedForUser(ComponentName assistant, int userId, boolean enabled) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public List<String> getEnabledNotificationListenerPackages() throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public List<ComponentName> getEnabledNotificationListeners(int userId) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public ComponentName getAllowedNotificationAssistantForUser(int userId) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public ComponentName getAllowedNotificationAssistant() throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public ComponentName getDefaultNotificationAssistant() throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public void setNASMigrationDoneAndResetDefault(int userId, boolean loadFromConfig) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public boolean hasEnabledNotificationListener(String packageName, int userId) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public int getZenMode() throws RemoteException {
            return 0;
        }

        @Override // android.app.INotificationManager
        public ZenModeConfig getZenModeConfig() throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public NotificationManager.Policy getConsolidatedNotificationPolicy() throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public void setZenMode(int mode, Uri conditionId, String reason) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void notifyConditions(String pkg, IConditionProvider provider, Condition[] conditions) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public boolean isNotificationPolicyAccessGranted(String pkg) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public NotificationManager.Policy getNotificationPolicy(String pkg) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public void setNotificationPolicy(String pkg, NotificationManager.Policy policy) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public boolean isNotificationPolicyAccessGrantedForPackage(String pkg) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public void setNotificationPolicyAccessGranted(String pkg, boolean granted) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setNotificationPolicyAccessGrantedForUser(String pkg, int userId, boolean granted) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public AutomaticZenRule getAutomaticZenRule(String id) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public List<ZenModeConfig.ZenRule> getZenRules() throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public String addAutomaticZenRule(AutomaticZenRule automaticZenRule, String pkg) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public boolean updateAutomaticZenRule(String id, AutomaticZenRule automaticZenRule) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public boolean removeAutomaticZenRule(String id) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public boolean removeAutomaticZenRules(String packageName) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public int getRuleInstanceCount(ComponentName owner) throws RemoteException {
            return 0;
        }

        @Override // android.app.INotificationManager
        public void setAutomaticZenRuleState(String id, Condition condition) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public byte[] getBackupPayload(int user) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public void applyRestore(byte[] payload, int user) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public ParceledListSlice getAppActiveNotifications(String callingPkg, int userId) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public void setNotificationDelegate(String callingPkg, String delegate) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public String getNotificationDelegate(String callingPkg) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public boolean canNotifyAsPackage(String callingPkg, String targetPkg, int userId) throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public void setPrivateNotificationsAllowed(boolean allow) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public boolean getPrivateNotificationsAllowed() throws RemoteException {
            return false;
        }

        @Override // android.app.INotificationManager
        public long pullStats(long startNs, int report, boolean doAgg, List<ParcelFileDescriptor> stats) throws RemoteException {
            return 0L;
        }

        @Override // android.app.INotificationManager
        public NotificationListenerFilter getListenerFilter(ComponentName cn, int userId) throws RemoteException {
            return null;
        }

        @Override // android.app.INotificationManager
        public void setListenerFilter(ComponentName cn, int userId, NotificationListenerFilter nlf) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void migrateNotificationFilter(INotificationListener token, int defaultTypes, List<String> disallowedPkgs) throws RemoteException {
        }

        @Override // android.app.INotificationManager
        public void setToastRateLimitingEnabled(boolean enable) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements INotificationManager {
        public static final String DESCRIPTOR = "android.app.INotificationManager";
        static final int TRANSACTION_addAutomaticZenRule = 126;
        static final int TRANSACTION_allowAssistantAdjustment = 21;
        static final int TRANSACTION_applyAdjustmentFromAssistant = 92;
        static final int TRANSACTION_applyAdjustmentsFromAssistant = 93;
        static final int TRANSACTION_applyEnqueuedAdjustmentFromAssistant = 91;
        static final int TRANSACTION_applyRestore = 133;
        static final int TRANSACTION_areBubblesAllowed = 26;
        static final int TRANSACTION_areBubblesEnabled = 27;
        static final int TRANSACTION_areChannelsBypassingDnd = 56;
        static final int TRANSACTION_areNotificationsEnabled = 18;
        static final int TRANSACTION_areNotificationsEnabledForPackage = 17;
        static final int TRANSACTION_canNotifyAsPackage = 137;
        static final int TRANSACTION_canShowBadge = 10;
        static final int TRANSACTION_cancelAllNotifications = 1;
        static final int TRANSACTION_cancelNotificationFromListener = 69;
        static final int TRANSACTION_cancelNotificationWithTag = 8;
        static final int TRANSACTION_cancelNotificationsFromListener = 70;
        static final int TRANSACTION_cancelToast = 5;
        static final int TRANSACTION_clearData = 2;
        static final int TRANSACTION_clearRequestedListenerHints = 80;
        static final int TRANSACTION_createConversationNotificationChannelForPackage = 43;
        static final int TRANSACTION_createNotificationChannelGroups = 29;
        static final int TRANSACTION_createNotificationChannels = 30;
        static final int TRANSACTION_createNotificationChannelsForPackage = 31;
        static final int TRANSACTION_deleteNotificationChannel = 45;
        static final int TRANSACTION_deleteNotificationChannelGroup = 51;
        static final int TRANSACTION_deleteNotificationHistoryItem = 60;
        static final int TRANSACTION_disallowAssistantAdjustment = 22;
        static final int TRANSACTION_enqueueNotificationWithTag = 7;
        static final int TRANSACTION_enqueueTextToast = 3;
        static final int TRANSACTION_enqueueToast = 4;
        static final int TRANSACTION_finishToken = 6;
        static final int TRANSACTION_getActiveNotifications = 62;
        static final int TRANSACTION_getActiveNotificationsFromListener = 78;
        static final int TRANSACTION_getActiveNotificationsWithAttribution = 63;
        static final int TRANSACTION_getAllowedAssistantAdjustments = 20;
        static final int TRANSACTION_getAllowedNotificationAssistant = 109;
        static final int TRANSACTION_getAllowedNotificationAssistantForUser = 108;
        static final int TRANSACTION_getAppActiveNotifications = 134;
        static final int TRANSACTION_getAppsBypassingDndCount = 57;
        static final int TRANSACTION_getAutomaticZenRule = 124;
        static final int TRANSACTION_getBackupPayload = 132;
        static final int TRANSACTION_getBlockedAppCount = 55;
        static final int TRANSACTION_getBlockedChannelCount = 50;
        static final int TRANSACTION_getBubblePreferenceForPackage = 28;
        static final int TRANSACTION_getConsolidatedNotificationPolicy = 115;
        static final int TRANSACTION_getConversationNotificationChannel = 42;
        static final int TRANSACTION_getConversations = 32;
        static final int TRANSACTION_getConversationsForPackage = 33;
        static final int TRANSACTION_getDefaultNotificationAssistant = 110;
        static final int TRANSACTION_getDeletedChannelCount = 49;
        static final int TRANSACTION_getEffectsSuppressor = 96;
        static final int TRANSACTION_getEnabledNotificationListenerPackages = 106;
        static final int TRANSACTION_getEnabledNotificationListeners = 107;
        static final int TRANSACTION_getHintsFromListener = 82;
        static final int TRANSACTION_getHistoricalNotifications = 64;
        static final int TRANSACTION_getHistoricalNotificationsWithAttribution = 65;
        static final int TRANSACTION_getInterruptionFilterFromListener = 84;
        static final int TRANSACTION_getListenerFilter = 141;
        static final int TRANSACTION_getNotificationChannel = 41;
        static final int TRANSACTION_getNotificationChannelForPackage = 44;
        static final int TRANSACTION_getNotificationChannelGroup = 52;
        static final int TRANSACTION_getNotificationChannelGroupForPackage = 35;
        static final int TRANSACTION_getNotificationChannelGroups = 53;
        static final int TRANSACTION_getNotificationChannelGroupsForPackage = 34;
        static final int TRANSACTION_getNotificationChannelGroupsFromPrivilegedListener = 90;
        static final int TRANSACTION_getNotificationChannels = 46;
        static final int TRANSACTION_getNotificationChannelsBypassingDnd = 58;
        static final int TRANSACTION_getNotificationChannelsForPackage = 47;
        static final int TRANSACTION_getNotificationChannelsFromPrivilegedListener = 89;
        static final int TRANSACTION_getNotificationDelegate = 136;
        static final int TRANSACTION_getNotificationHistory = 66;
        static final int TRANSACTION_getNotificationPolicy = 119;
        static final int TRANSACTION_getNumNotificationChannelsForPackage = 48;
        static final int TRANSACTION_getPackageImportance = 19;
        static final int TRANSACTION_getPopulatedNotificationChannelGroupForPackage = 36;
        static final int TRANSACTION_getPrivateNotificationsAllowed = 139;
        static final int TRANSACTION_getRuleInstanceCount = 130;
        static final int TRANSACTION_getSnoozedNotificationsFromListener = 79;
        static final int TRANSACTION_getZenMode = 113;
        static final int TRANSACTION_getZenModeConfig = 114;
        static final int TRANSACTION_getZenRules = 125;
        static final int TRANSACTION_hasEnabledNotificationListener = 112;
        static final int TRANSACTION_hasSentValidMsg = 11;
        static final int TRANSACTION_hasUserDemotedInvalidMsgApp = 13;
        static final int TRANSACTION_isInInvalidMsgState = 12;
        static final int TRANSACTION_isNotificationAssistantAccessGranted = 101;
        static final int TRANSACTION_isNotificationListenerAccessGranted = 99;
        static final int TRANSACTION_isNotificationListenerAccessGrantedForUser = 100;
        static final int TRANSACTION_isNotificationPolicyAccessGranted = 118;
        static final int TRANSACTION_isNotificationPolicyAccessGrantedForPackage = 121;
        static final int TRANSACTION_isPackagePaused = 59;
        static final int TRANSACTION_isSystemConditionProviderEnabled = 98;
        static final int TRANSACTION_matchesCallFilter = 97;
        static final int TRANSACTION_migrateNotificationFilter = 143;
        static final int TRANSACTION_notifyConditions = 117;
        static final int TRANSACTION_onlyHasDefaultChannel = 54;
        static final int TRANSACTION_pullStats = 140;
        static final int TRANSACTION_registerListener = 67;
        static final int TRANSACTION_removeAutomaticZenRule = 128;
        static final int TRANSACTION_removeAutomaticZenRules = 129;
        static final int TRANSACTION_requestBindListener = 73;
        static final int TRANSACTION_requestBindProvider = 75;
        static final int TRANSACTION_requestHintsFromListener = 81;
        static final int TRANSACTION_requestInterruptionFilterFromListener = 83;
        static final int TRANSACTION_requestUnbindListener = 74;
        static final int TRANSACTION_requestUnbindProvider = 76;
        static final int TRANSACTION_setAutomaticZenRuleState = 131;
        static final int TRANSACTION_setBubblesAllowed = 25;
        static final int TRANSACTION_setHideSilentStatusIcons = 24;
        static final int TRANSACTION_setInterruptionFilter = 86;
        static final int TRANSACTION_setInvalidMsgAppDemoted = 14;
        static final int TRANSACTION_setListenerFilter = 142;
        static final int TRANSACTION_setNASMigrationDoneAndResetDefault = 111;
        static final int TRANSACTION_setNotificationAssistantAccessGranted = 103;
        static final int TRANSACTION_setNotificationAssistantAccessGrantedForUser = 105;
        static final int TRANSACTION_setNotificationDelegate = 135;
        static final int TRANSACTION_setNotificationListenerAccessGranted = 102;
        static final int TRANSACTION_setNotificationListenerAccessGrantedForUser = 104;
        static final int TRANSACTION_setNotificationPolicy = 120;
        static final int TRANSACTION_setNotificationPolicyAccessGranted = 122;
        static final int TRANSACTION_setNotificationPolicyAccessGrantedForUser = 123;
        static final int TRANSACTION_setNotificationsEnabledForPackage = 15;
        static final int TRANSACTION_setNotificationsEnabledWithImportanceLockForPackage = 16;
        static final int TRANSACTION_setNotificationsShownFromListener = 77;
        static final int TRANSACTION_setOnNotificationPostedTrimFromListener = 85;
        static final int TRANSACTION_setPrivateNotificationsAllowed = 138;
        static final int TRANSACTION_setShowBadge = 9;
        static final int TRANSACTION_setToastRateLimitingEnabled = 144;
        static final int TRANSACTION_setZenMode = 116;
        static final int TRANSACTION_shouldHideSilentStatusIcons = 23;
        static final int TRANSACTION_silenceNotificationSound = 61;
        static final int TRANSACTION_snoozeNotificationUntilContextFromListener = 71;
        static final int TRANSACTION_snoozeNotificationUntilFromListener = 72;
        static final int TRANSACTION_unlockAllNotificationChannels = 40;
        static final int TRANSACTION_unlockNotificationChannel = 39;
        static final int TRANSACTION_unregisterListener = 68;
        static final int TRANSACTION_unsnoozeNotificationFromAssistant = 94;
        static final int TRANSACTION_unsnoozeNotificationFromSystemListener = 95;
        static final int TRANSACTION_updateAutomaticZenRule = 127;
        static final int TRANSACTION_updateNotificationChannelForPackage = 38;
        static final int TRANSACTION_updateNotificationChannelFromPrivilegedListener = 88;
        static final int TRANSACTION_updateNotificationChannelGroupForPackage = 37;
        static final int TRANSACTION_updateNotificationChannelGroupFromPrivilegedListener = 87;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INotificationManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof INotificationManager)) {
                return new Proxy(obj);
            }
            return (INotificationManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "cancelAllNotifications";
                case 2:
                    return "clearData";
                case 3:
                    return "enqueueTextToast";
                case 4:
                    return "enqueueToast";
                case 5:
                    return "cancelToast";
                case 6:
                    return "finishToken";
                case 7:
                    return "enqueueNotificationWithTag";
                case 8:
                    return "cancelNotificationWithTag";
                case 9:
                    return "setShowBadge";
                case 10:
                    return "canShowBadge";
                case 11:
                    return "hasSentValidMsg";
                case 12:
                    return "isInInvalidMsgState";
                case 13:
                    return "hasUserDemotedInvalidMsgApp";
                case 14:
                    return "setInvalidMsgAppDemoted";
                case 15:
                    return "setNotificationsEnabledForPackage";
                case 16:
                    return "setNotificationsEnabledWithImportanceLockForPackage";
                case 17:
                    return "areNotificationsEnabledForPackage";
                case 18:
                    return "areNotificationsEnabled";
                case 19:
                    return "getPackageImportance";
                case 20:
                    return "getAllowedAssistantAdjustments";
                case 21:
                    return "allowAssistantAdjustment";
                case 22:
                    return "disallowAssistantAdjustment";
                case 23:
                    return "shouldHideSilentStatusIcons";
                case 24:
                    return "setHideSilentStatusIcons";
                case 25:
                    return "setBubblesAllowed";
                case 26:
                    return "areBubblesAllowed";
                case 27:
                    return "areBubblesEnabled";
                case 28:
                    return "getBubblePreferenceForPackage";
                case 29:
                    return "createNotificationChannelGroups";
                case 30:
                    return "createNotificationChannels";
                case 31:
                    return "createNotificationChannelsForPackage";
                case 32:
                    return "getConversations";
                case 33:
                    return "getConversationsForPackage";
                case 34:
                    return "getNotificationChannelGroupsForPackage";
                case 35:
                    return "getNotificationChannelGroupForPackage";
                case 36:
                    return "getPopulatedNotificationChannelGroupForPackage";
                case 37:
                    return "updateNotificationChannelGroupForPackage";
                case 38:
                    return "updateNotificationChannelForPackage";
                case 39:
                    return "unlockNotificationChannel";
                case 40:
                    return "unlockAllNotificationChannels";
                case 41:
                    return "getNotificationChannel";
                case 42:
                    return "getConversationNotificationChannel";
                case 43:
                    return "createConversationNotificationChannelForPackage";
                case 44:
                    return "getNotificationChannelForPackage";
                case 45:
                    return "deleteNotificationChannel";
                case 46:
                    return "getNotificationChannels";
                case 47:
                    return "getNotificationChannelsForPackage";
                case 48:
                    return "getNumNotificationChannelsForPackage";
                case 49:
                    return "getDeletedChannelCount";
                case 50:
                    return "getBlockedChannelCount";
                case 51:
                    return "deleteNotificationChannelGroup";
                case 52:
                    return "getNotificationChannelGroup";
                case 53:
                    return "getNotificationChannelGroups";
                case 54:
                    return "onlyHasDefaultChannel";
                case 55:
                    return "getBlockedAppCount";
                case 56:
                    return "areChannelsBypassingDnd";
                case 57:
                    return "getAppsBypassingDndCount";
                case 58:
                    return "getNotificationChannelsBypassingDnd";
                case 59:
                    return "isPackagePaused";
                case 60:
                    return "deleteNotificationHistoryItem";
                case 61:
                    return "silenceNotificationSound";
                case 62:
                    return "getActiveNotifications";
                case 63:
                    return "getActiveNotificationsWithAttribution";
                case 64:
                    return "getHistoricalNotifications";
                case 65:
                    return "getHistoricalNotificationsWithAttribution";
                case 66:
                    return "getNotificationHistory";
                case 67:
                    return "registerListener";
                case 68:
                    return "unregisterListener";
                case 69:
                    return "cancelNotificationFromListener";
                case 70:
                    return "cancelNotificationsFromListener";
                case 71:
                    return "snoozeNotificationUntilContextFromListener";
                case 72:
                    return "snoozeNotificationUntilFromListener";
                case 73:
                    return "requestBindListener";
                case 74:
                    return "requestUnbindListener";
                case 75:
                    return "requestBindProvider";
                case 76:
                    return "requestUnbindProvider";
                case 77:
                    return "setNotificationsShownFromListener";
                case 78:
                    return "getActiveNotificationsFromListener";
                case 79:
                    return "getSnoozedNotificationsFromListener";
                case 80:
                    return "clearRequestedListenerHints";
                case 81:
                    return "requestHintsFromListener";
                case 82:
                    return "getHintsFromListener";
                case 83:
                    return "requestInterruptionFilterFromListener";
                case 84:
                    return "getInterruptionFilterFromListener";
                case 85:
                    return "setOnNotificationPostedTrimFromListener";
                case 86:
                    return "setInterruptionFilter";
                case 87:
                    return "updateNotificationChannelGroupFromPrivilegedListener";
                case 88:
                    return "updateNotificationChannelFromPrivilegedListener";
                case 89:
                    return "getNotificationChannelsFromPrivilegedListener";
                case 90:
                    return "getNotificationChannelGroupsFromPrivilegedListener";
                case 91:
                    return "applyEnqueuedAdjustmentFromAssistant";
                case 92:
                    return "applyAdjustmentFromAssistant";
                case 93:
                    return "applyAdjustmentsFromAssistant";
                case 94:
                    return "unsnoozeNotificationFromAssistant";
                case 95:
                    return "unsnoozeNotificationFromSystemListener";
                case 96:
                    return "getEffectsSuppressor";
                case 97:
                    return "matchesCallFilter";
                case 98:
                    return "isSystemConditionProviderEnabled";
                case 99:
                    return "isNotificationListenerAccessGranted";
                case 100:
                    return "isNotificationListenerAccessGrantedForUser";
                case 101:
                    return "isNotificationAssistantAccessGranted";
                case 102:
                    return "setNotificationListenerAccessGranted";
                case 103:
                    return "setNotificationAssistantAccessGranted";
                case 104:
                    return "setNotificationListenerAccessGrantedForUser";
                case 105:
                    return "setNotificationAssistantAccessGrantedForUser";
                case 106:
                    return "getEnabledNotificationListenerPackages";
                case 107:
                    return "getEnabledNotificationListeners";
                case 108:
                    return "getAllowedNotificationAssistantForUser";
                case 109:
                    return "getAllowedNotificationAssistant";
                case 110:
                    return "getDefaultNotificationAssistant";
                case 111:
                    return "setNASMigrationDoneAndResetDefault";
                case 112:
                    return "hasEnabledNotificationListener";
                case 113:
                    return "getZenMode";
                case 114:
                    return "getZenModeConfig";
                case 115:
                    return "getConsolidatedNotificationPolicy";
                case 116:
                    return "setZenMode";
                case 117:
                    return "notifyConditions";
                case 118:
                    return "isNotificationPolicyAccessGranted";
                case 119:
                    return "getNotificationPolicy";
                case 120:
                    return "setNotificationPolicy";
                case 121:
                    return "isNotificationPolicyAccessGrantedForPackage";
                case 122:
                    return "setNotificationPolicyAccessGranted";
                case 123:
                    return "setNotificationPolicyAccessGrantedForUser";
                case 124:
                    return "getAutomaticZenRule";
                case 125:
                    return "getZenRules";
                case 126:
                    return "addAutomaticZenRule";
                case 127:
                    return "updateAutomaticZenRule";
                case 128:
                    return "removeAutomaticZenRule";
                case 129:
                    return "removeAutomaticZenRules";
                case 130:
                    return "getRuleInstanceCount";
                case 131:
                    return "setAutomaticZenRuleState";
                case 132:
                    return "getBackupPayload";
                case 133:
                    return "applyRestore";
                case 134:
                    return "getAppActiveNotifications";
                case 135:
                    return "setNotificationDelegate";
                case 136:
                    return "getNotificationDelegate";
                case 137:
                    return "canNotifyAsPackage";
                case 138:
                    return "setPrivateNotificationsAllowed";
                case 139:
                    return "getPrivateNotificationsAllowed";
                case 140:
                    return "pullStats";
                case 141:
                    return "getListenerFilter";
                case 142:
                    return "setListenerFilter";
                case 143:
                    return "migrateNotificationFilter";
                case 144:
                    return "setToastRateLimitingEnabled";
                default:
                    return null;
            }
        }

        @Override // android.os.Binder
        public String getTransactionName(int transactionCode) {
            return getDefaultTransactionName(transactionCode);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            CharSequence _arg2;
            Notification _arg4;
            UserHandle _arg0;
            ParceledListSlice _arg1;
            ParceledListSlice _arg12;
            ParceledListSlice _arg22;
            boolean _arg02;
            boolean _arg23;
            boolean _arg3;
            NotificationChannelGroup _arg24;
            NotificationChannel _arg25;
            boolean _arg42;
            NotificationChannel _arg26;
            boolean _arg43;
            boolean _arg27;
            ComponentName _arg13;
            ComponentName _arg03;
            ComponentName _arg04;
            UserHandle _arg28;
            NotificationChannelGroup _arg32;
            UserHandle _arg29;
            NotificationChannel _arg33;
            UserHandle _arg210;
            UserHandle _arg211;
            Adjustment _arg14;
            Adjustment _arg15;
            Bundle _arg05;
            ComponentName _arg06;
            ComponentName _arg07;
            ComponentName _arg08;
            ComponentName _arg09;
            boolean _arg16;
            ComponentName _arg010;
            ComponentName _arg011;
            boolean _arg212;
            ComponentName _arg012;
            Uri _arg17;
            NotificationManager.Policy _arg18;
            AutomaticZenRule _arg013;
            AutomaticZenRule _arg19;
            ComponentName _arg014;
            Condition _arg110;
            boolean _arg213;
            ComponentName _arg015;
            ComponentName _arg016;
            NotificationListenerFilter _arg214;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    boolean _arg017 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg018 = data.readString();
                            int _arg111 = data.readInt();
                            cancelAllNotifications(_arg018, _arg111);
                            reply.writeNoException();
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg019 = data.readString();
                            int _arg112 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg017 = true;
                            }
                            clearData(_arg019, _arg112, _arg017);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg020 = data.readString();
                            IBinder _arg113 = data.readStrongBinder();
                            if (data.readInt() != 0) {
                                _arg2 = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            int _arg34 = data.readInt();
                            int _arg44 = data.readInt();
                            ITransientNotificationCallback _arg5 = ITransientNotificationCallback.Stub.asInterface(data.readStrongBinder());
                            enqueueTextToast(_arg020, _arg113, _arg2, _arg34, _arg44, _arg5);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg021 = data.readString();
                            IBinder _arg114 = data.readStrongBinder();
                            ITransientNotification _arg215 = ITransientNotification.Stub.asInterface(data.readStrongBinder());
                            int _arg35 = data.readInt();
                            int _arg45 = data.readInt();
                            enqueueToast(_arg021, _arg114, _arg215, _arg35, _arg45);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg022 = data.readString();
                            IBinder _arg115 = data.readStrongBinder();
                            cancelToast(_arg022, _arg115);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg023 = data.readString();
                            IBinder _arg116 = data.readStrongBinder();
                            finishToken(_arg023, _arg116);
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg024 = data.readString();
                            String _arg117 = data.readString();
                            String _arg216 = data.readString();
                            int _arg36 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg4 = Notification.CREATOR.createFromParcel(data);
                            } else {
                                _arg4 = null;
                            }
                            int _arg52 = data.readInt();
                            enqueueNotificationWithTag(_arg024, _arg117, _arg216, _arg36, _arg4, _arg52);
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg025 = data.readString();
                            String _arg118 = data.readString();
                            String _arg217 = data.readString();
                            int _arg37 = data.readInt();
                            int _arg46 = data.readInt();
                            cancelNotificationWithTag(_arg025, _arg118, _arg217, _arg37, _arg46);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg026 = data.readString();
                            int _arg119 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg017 = true;
                            }
                            setShowBadge(_arg026, _arg119, _arg017);
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg027 = data.readString();
                            int _arg120 = data.readInt();
                            boolean canShowBadge = canShowBadge(_arg027, _arg120);
                            reply.writeNoException();
                            reply.writeInt(canShowBadge ? 1 : 0);
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg028 = data.readString();
                            int _arg121 = data.readInt();
                            boolean hasSentValidMsg = hasSentValidMsg(_arg028, _arg121);
                            reply.writeNoException();
                            reply.writeInt(hasSentValidMsg ? 1 : 0);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg029 = data.readString();
                            int _arg122 = data.readInt();
                            boolean isInInvalidMsgState = isInInvalidMsgState(_arg029, _arg122);
                            reply.writeNoException();
                            reply.writeInt(isInInvalidMsgState ? 1 : 0);
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg030 = data.readString();
                            int _arg123 = data.readInt();
                            boolean hasUserDemotedInvalidMsgApp = hasUserDemotedInvalidMsgApp(_arg030, _arg123);
                            reply.writeNoException();
                            reply.writeInt(hasUserDemotedInvalidMsgApp ? 1 : 0);
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg031 = data.readString();
                            int _arg124 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg017 = true;
                            }
                            setInvalidMsgAppDemoted(_arg031, _arg124, _arg017);
                            reply.writeNoException();
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg032 = data.readString();
                            int _arg125 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg017 = true;
                            }
                            setNotificationsEnabledForPackage(_arg032, _arg125, _arg017);
                            reply.writeNoException();
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg033 = data.readString();
                            int _arg126 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg017 = true;
                            }
                            setNotificationsEnabledWithImportanceLockForPackage(_arg033, _arg126, _arg017);
                            reply.writeNoException();
                            return true;
                        case 17:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg034 = data.readString();
                            int _arg127 = data.readInt();
                            boolean areNotificationsEnabledForPackage = areNotificationsEnabledForPackage(_arg034, _arg127);
                            reply.writeNoException();
                            reply.writeInt(areNotificationsEnabledForPackage ? 1 : 0);
                            return true;
                        case 18:
                            data.enforceInterface(DESCRIPTOR);
                            boolean areNotificationsEnabled = areNotificationsEnabled(data.readString());
                            reply.writeNoException();
                            reply.writeInt(areNotificationsEnabled ? 1 : 0);
                            return true;
                        case 19:
                            data.enforceInterface(DESCRIPTOR);
                            int _result = getPackageImportance(data.readString());
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 20:
                            data.enforceInterface(DESCRIPTOR);
                            List<String> _result2 = getAllowedAssistantAdjustments(data.readString());
                            reply.writeNoException();
                            reply.writeStringList(_result2);
                            return true;
                        case 21:
                            data.enforceInterface(DESCRIPTOR);
                            allowAssistantAdjustment(data.readString());
                            reply.writeNoException();
                            return true;
                        case 22:
                            data.enforceInterface(DESCRIPTOR);
                            disallowAssistantAdjustment(data.readString());
                            reply.writeNoException();
                            return true;
                        case 23:
                            data.enforceInterface(DESCRIPTOR);
                            boolean shouldHideSilentStatusIcons = shouldHideSilentStatusIcons(data.readString());
                            reply.writeNoException();
                            reply.writeInt(shouldHideSilentStatusIcons ? 1 : 0);
                            return true;
                        case 24:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg017 = true;
                            }
                            setHideSilentStatusIcons(_arg017);
                            reply.writeNoException();
                            return true;
                        case 25:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg035 = data.readString();
                            int _arg128 = data.readInt();
                            int _arg218 = data.readInt();
                            setBubblesAllowed(_arg035, _arg128, _arg218);
                            reply.writeNoException();
                            return true;
                        case 26:
                            data.enforceInterface(DESCRIPTOR);
                            boolean areBubblesAllowed = areBubblesAllowed(data.readString());
                            reply.writeNoException();
                            reply.writeInt(areBubblesAllowed ? 1 : 0);
                            return true;
                        case 27:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            boolean areBubblesEnabled = areBubblesEnabled(_arg0);
                            reply.writeNoException();
                            reply.writeInt(areBubblesEnabled ? 1 : 0);
                            return true;
                        case 28:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg036 = data.readString();
                            int _arg129 = data.readInt();
                            int _result3 = getBubblePreferenceForPackage(_arg036, _arg129);
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            return true;
                        case 29:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg037 = data.readString();
                            if (data.readInt() != 0) {
                                _arg1 = ParceledListSlice.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            createNotificationChannelGroups(_arg037, _arg1);
                            reply.writeNoException();
                            return true;
                        case 30:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg038 = data.readString();
                            if (data.readInt() != 0) {
                                _arg12 = ParceledListSlice.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            createNotificationChannels(_arg038, _arg12);
                            reply.writeNoException();
                            return true;
                        case 31:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg039 = data.readString();
                            int _arg130 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg22 = ParceledListSlice.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            createNotificationChannelsForPackage(_arg039, _arg130, _arg22);
                            reply.writeNoException();
                            return true;
                        case 32:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = true;
                            } else {
                                _arg02 = false;
                            }
                            ParceledListSlice _result4 = getConversations(_arg02);
                            reply.writeNoException();
                            if (_result4 != null) {
                                reply.writeInt(1);
                                _result4.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 33:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg040 = data.readString();
                            int _arg131 = data.readInt();
                            ParceledListSlice _result5 = getConversationsForPackage(_arg040, _arg131);
                            reply.writeNoException();
                            if (_result5 != null) {
                                reply.writeInt(1);
                                _result5.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 34:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg041 = data.readString();
                            int _arg132 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg23 = true;
                            } else {
                                _arg23 = false;
                            }
                            ParceledListSlice _result6 = getNotificationChannelGroupsForPackage(_arg041, _arg132, _arg23);
                            reply.writeNoException();
                            if (_result6 != null) {
                                reply.writeInt(1);
                                _result6.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 35:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg042 = data.readString();
                            String _arg133 = data.readString();
                            int _arg219 = data.readInt();
                            NotificationChannelGroup _result7 = getNotificationChannelGroupForPackage(_arg042, _arg133, _arg219);
                            reply.writeNoException();
                            if (_result7 != null) {
                                reply.writeInt(1);
                                _result7.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 36:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg043 = data.readString();
                            int _arg134 = data.readInt();
                            String _arg220 = data.readString();
                            if (data.readInt() != 0) {
                                _arg3 = true;
                            } else {
                                _arg3 = false;
                            }
                            NotificationChannelGroup _result8 = getPopulatedNotificationChannelGroupForPackage(_arg043, _arg134, _arg220, _arg3);
                            reply.writeNoException();
                            if (_result8 != null) {
                                reply.writeInt(1);
                                _result8.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 37:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg044 = data.readString();
                            int _arg135 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg24 = NotificationChannelGroup.CREATOR.createFromParcel(data);
                            } else {
                                _arg24 = null;
                            }
                            updateNotificationChannelGroupForPackage(_arg044, _arg135, _arg24);
                            reply.writeNoException();
                            return true;
                        case 38:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg045 = data.readString();
                            int _arg136 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg25 = NotificationChannel.CREATOR.createFromParcel(data);
                            } else {
                                _arg25 = null;
                            }
                            updateNotificationChannelForPackage(_arg045, _arg136, _arg25);
                            reply.writeNoException();
                            return true;
                        case 39:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg046 = data.readString();
                            int _arg137 = data.readInt();
                            String _arg221 = data.readString();
                            unlockNotificationChannel(_arg046, _arg137, _arg221);
                            reply.writeNoException();
                            return true;
                        case 40:
                            data.enforceInterface(DESCRIPTOR);
                            unlockAllNotificationChannels();
                            reply.writeNoException();
                            return true;
                        case 41:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg047 = data.readString();
                            int _arg138 = data.readInt();
                            String _arg222 = data.readString();
                            String _arg38 = data.readString();
                            NotificationChannel _result9 = getNotificationChannel(_arg047, _arg138, _arg222, _arg38);
                            reply.writeNoException();
                            if (_result9 != null) {
                                reply.writeInt(1);
                                _result9.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 42:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg048 = data.readString();
                            int _arg139 = data.readInt();
                            String _arg223 = data.readString();
                            String _arg39 = data.readString();
                            if (data.readInt() != 0) {
                                _arg42 = true;
                            } else {
                                _arg42 = false;
                            }
                            String _arg53 = data.readString();
                            NotificationChannel _result10 = getConversationNotificationChannel(_arg048, _arg139, _arg223, _arg39, _arg42, _arg53);
                            reply.writeNoException();
                            if (_result10 != null) {
                                reply.writeInt(1);
                                _result10.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 43:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg049 = data.readString();
                            int _arg140 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg26 = NotificationChannel.CREATOR.createFromParcel(data);
                            } else {
                                _arg26 = null;
                            }
                            String _arg310 = data.readString();
                            createConversationNotificationChannelForPackage(_arg049, _arg140, _arg26, _arg310);
                            reply.writeNoException();
                            return true;
                        case 44:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg050 = data.readString();
                            int _arg141 = data.readInt();
                            String _arg224 = data.readString();
                            String _arg311 = data.readString();
                            if (data.readInt() != 0) {
                                _arg43 = true;
                            } else {
                                _arg43 = false;
                            }
                            NotificationChannel _result11 = getNotificationChannelForPackage(_arg050, _arg141, _arg224, _arg311, _arg43);
                            reply.writeNoException();
                            if (_result11 != null) {
                                reply.writeInt(1);
                                _result11.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 45:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg051 = data.readString();
                            String _arg142 = data.readString();
                            deleteNotificationChannel(_arg051, _arg142);
                            reply.writeNoException();
                            return true;
                        case 46:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg052 = data.readString();
                            String _arg143 = data.readString();
                            int _arg225 = data.readInt();
                            ParceledListSlice _result12 = getNotificationChannels(_arg052, _arg143, _arg225);
                            reply.writeNoException();
                            if (_result12 != null) {
                                reply.writeInt(1);
                                _result12.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 47:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg053 = data.readString();
                            int _arg144 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg27 = true;
                            } else {
                                _arg27 = false;
                            }
                            ParceledListSlice _result13 = getNotificationChannelsForPackage(_arg053, _arg144, _arg27);
                            reply.writeNoException();
                            if (_result13 != null) {
                                reply.writeInt(1);
                                _result13.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 48:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg054 = data.readString();
                            int _arg145 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg017 = true;
                            }
                            int _result14 = getNumNotificationChannelsForPackage(_arg054, _arg145, _arg017);
                            reply.writeNoException();
                            reply.writeInt(_result14);
                            return true;
                        case 49:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg055 = data.readString();
                            int _arg146 = data.readInt();
                            int _result15 = getDeletedChannelCount(_arg055, _arg146);
                            reply.writeNoException();
                            reply.writeInt(_result15);
                            return true;
                        case 50:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg056 = data.readString();
                            int _arg147 = data.readInt();
                            int _result16 = getBlockedChannelCount(_arg056, _arg147);
                            reply.writeNoException();
                            reply.writeInt(_result16);
                            return true;
                        case 51:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg057 = data.readString();
                            String _arg148 = data.readString();
                            deleteNotificationChannelGroup(_arg057, _arg148);
                            reply.writeNoException();
                            return true;
                        case 52:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg058 = data.readString();
                            String _arg149 = data.readString();
                            NotificationChannelGroup _result17 = getNotificationChannelGroup(_arg058, _arg149);
                            reply.writeNoException();
                            if (_result17 != null) {
                                reply.writeInt(1);
                                _result17.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 53:
                            data.enforceInterface(DESCRIPTOR);
                            ParceledListSlice _result18 = getNotificationChannelGroups(data.readString());
                            reply.writeNoException();
                            if (_result18 != null) {
                                reply.writeInt(1);
                                _result18.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 54:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg059 = data.readString();
                            int _arg150 = data.readInt();
                            boolean onlyHasDefaultChannel = onlyHasDefaultChannel(_arg059, _arg150);
                            reply.writeNoException();
                            reply.writeInt(onlyHasDefaultChannel ? 1 : 0);
                            return true;
                        case 55:
                            data.enforceInterface(DESCRIPTOR);
                            int _result19 = getBlockedAppCount(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result19);
                            return true;
                        case 56:
                            data.enforceInterface(DESCRIPTOR);
                            boolean areChannelsBypassingDnd = areChannelsBypassingDnd();
                            reply.writeNoException();
                            reply.writeInt(areChannelsBypassingDnd ? 1 : 0);
                            return true;
                        case 57:
                            data.enforceInterface(DESCRIPTOR);
                            int _result20 = getAppsBypassingDndCount(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result20);
                            return true;
                        case 58:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg060 = data.readString();
                            int _arg151 = data.readInt();
                            ParceledListSlice _result21 = getNotificationChannelsBypassingDnd(_arg060, _arg151);
                            reply.writeNoException();
                            if (_result21 != null) {
                                reply.writeInt(1);
                                _result21.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 59:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isPackagePaused = isPackagePaused(data.readString());
                            reply.writeNoException();
                            reply.writeInt(isPackagePaused ? 1 : 0);
                            return true;
                        case 60:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg061 = data.readString();
                            int _arg152 = data.readInt();
                            long _arg226 = data.readLong();
                            deleteNotificationHistoryItem(_arg061, _arg152, _arg226);
                            reply.writeNoException();
                            return true;
                        case 61:
                            data.enforceInterface(DESCRIPTOR);
                            silenceNotificationSound();
                            reply.writeNoException();
                            return true;
                        case 62:
                            data.enforceInterface(DESCRIPTOR);
                            StatusBarNotification[] _result22 = getActiveNotifications(data.readString());
                            reply.writeNoException();
                            reply.writeTypedArray(_result22, 1);
                            return true;
                        case 63:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg062 = data.readString();
                            String _arg153 = data.readString();
                            StatusBarNotification[] _result23 = getActiveNotificationsWithAttribution(_arg062, _arg153);
                            reply.writeNoException();
                            reply.writeTypedArray(_result23, 1);
                            return true;
                        case 64:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg063 = data.readString();
                            int _arg154 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg017 = true;
                            }
                            StatusBarNotification[] _result24 = getHistoricalNotifications(_arg063, _arg154, _arg017);
                            reply.writeNoException();
                            reply.writeTypedArray(_result24, 1);
                            return true;
                        case 65:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg064 = data.readString();
                            String _arg155 = data.readString();
                            int _arg227 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg017 = true;
                            }
                            StatusBarNotification[] _result25 = getHistoricalNotificationsWithAttribution(_arg064, _arg155, _arg227, _arg017);
                            reply.writeNoException();
                            reply.writeTypedArray(_result25, 1);
                            return true;
                        case 66:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg065 = data.readString();
                            String _arg156 = data.readString();
                            NotificationHistory _result26 = getNotificationHistory(_arg065, _arg156);
                            reply.writeNoException();
                            if (_result26 != null) {
                                reply.writeInt(1);
                                _result26.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 67:
                            data.enforceInterface(DESCRIPTOR);
                            INotificationListener _arg066 = INotificationListener.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg13 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            int _arg228 = data.readInt();
                            registerListener(_arg066, _arg13, _arg228);
                            reply.writeNoException();
                            return true;
                        case 68:
                            data.enforceInterface(DESCRIPTOR);
                            INotificationListener _arg067 = INotificationListener.Stub.asInterface(data.readStrongBinder());
                            int _arg157 = data.readInt();
                            unregisterListener(_arg067, _arg157);
                            reply.writeNoException();
                            return true;
                        case 69:
                            data.enforceInterface(DESCRIPTOR);
                            INotificationListener _arg068 = INotificationListener.Stub.asInterface(data.readStrongBinder());
                            String _arg158 = data.readString();
                            String _arg229 = data.readString();
                            int _arg312 = data.readInt();
                            cancelNotificationFromListener(_arg068, _arg158, _arg229, _arg312);
                            reply.writeNoException();
                            return true;
                        case 70:
                            data.enforceInterface(DESCRIPTOR);
                            INotificationListener _arg069 = INotificationListener.Stub.asInterface(data.readStrongBinder());
                            String[] _arg159 = data.createStringArray();
                            cancelNotificationsFromListener(_arg069, _arg159);
                            reply.writeNoException();
                            return true;
                        case 71:
                            data.enforceInterface(DESCRIPTOR);
                            INotificationListener _arg070 = INotificationListener.Stub.asInterface(data.readStrongBinder());
                            String _arg160 = data.readString();
                            String _arg230 = data.readString();
                            snoozeNotificationUntilContextFromListener(_arg070, _arg160, _arg230);
                            reply.writeNoException();
                            return true;
                        case 72:
                            data.enforceInterface(DESCRIPTOR);
                            INotificationListener _arg071 = INotificationListener.Stub.asInterface(data.readStrongBinder());
                            String _arg161 = data.readString();
                            long _arg231 = data.readLong();
                            snoozeNotificationUntilFromListener(_arg071, _arg161, _arg231);
                            reply.writeNoException();
                            return true;
                        case 73:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            requestBindListener(_arg03);
                            reply.writeNoException();
                            return true;
                        case 74:
                            data.enforceInterface(DESCRIPTOR);
                            requestUnbindListener(INotificationListener.Stub.asInterface(data.readStrongBinder()));
                            reply.writeNoException();
                            return true;
                        case 75:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            requestBindProvider(_arg04);
                            reply.writeNoException();
                            return true;
                        case 76:
                            data.enforceInterface(DESCRIPTOR);
                            requestUnbindProvider(IConditionProvider.Stub.asInterface(data.readStrongBinder()));
                            reply.writeNoException();
                            return true;
                        case 77:
                            data.enforceInterface(DESCRIPTOR);
                            INotificationListener _arg072 = INotificationListener.Stub.asInterface(data.readStrongBinder());
                            String[] _arg162 = data.createStringArray();
                            setNotificationsShownFromListener(_arg072, _arg162);
                            reply.writeNoException();
                            return true;
                        case 78:
                            data.enforceInterface(DESCRIPTOR);
                            INotificationListener _arg073 = INotificationListener.Stub.asInterface(data.readStrongBinder());
                            String[] _arg163 = data.createStringArray();
                            int _arg232 = data.readInt();
                            ParceledListSlice _result27 = getActiveNotificationsFromListener(_arg073, _arg163, _arg232);
                            reply.writeNoException();
                            if (_result27 != null) {
                                reply.writeInt(1);
                                _result27.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 79:
                            data.enforceInterface(DESCRIPTOR);
                            INotificationListener _arg074 = INotificationListener.Stub.asInterface(data.readStrongBinder());
                            int _arg164 = data.readInt();
                            ParceledListSlice _result28 = getSnoozedNotificationsFromListener(_arg074, _arg164);
                            reply.writeNoException();
                            if (_result28 != null) {
                                reply.writeInt(1);
                                _result28.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 80:
                            data.enforceInterface(DESCRIPTOR);
                            clearRequestedListenerHints(INotificationListener.Stub.asInterface(data.readStrongBinder()));
                            reply.writeNoException();
                            return true;
                        case 81:
                            data.enforceInterface(DESCRIPTOR);
                            INotificationListener _arg075 = INotificationListener.Stub.asInterface(data.readStrongBinder());
                            int _arg165 = data.readInt();
                            requestHintsFromListener(_arg075, _arg165);
                            reply.writeNoException();
                            return true;
                        case 82:
                            data.enforceInterface(DESCRIPTOR);
                            int _result29 = getHintsFromListener(INotificationListener.Stub.asInterface(data.readStrongBinder()));
                            reply.writeNoException();
                            reply.writeInt(_result29);
                            return true;
                        case 83:
                            data.enforceInterface(DESCRIPTOR);
                            INotificationListener _arg076 = INotificationListener.Stub.asInterface(data.readStrongBinder());
                            int _arg166 = data.readInt();
                            requestInterruptionFilterFromListener(_arg076, _arg166);
                            reply.writeNoException();
                            return true;
                        case 84:
                            data.enforceInterface(DESCRIPTOR);
                            int _result30 = getInterruptionFilterFromListener(INotificationListener.Stub.asInterface(data.readStrongBinder()));
                            reply.writeNoException();
                            reply.writeInt(_result30);
                            return true;
                        case 85:
                            data.enforceInterface(DESCRIPTOR);
                            INotificationListener _arg077 = INotificationListener.Stub.asInterface(data.readStrongBinder());
                            int _arg167 = data.readInt();
                            setOnNotificationPostedTrimFromListener(_arg077, _arg167);
                            reply.writeNoException();
                            return true;
                        case 86:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg078 = data.readString();
                            int _arg168 = data.readInt();
                            setInterruptionFilter(_arg078, _arg168);
                            reply.writeNoException();
                            return true;
                        case 87:
                            data.enforceInterface(DESCRIPTOR);
                            INotificationListener _arg079 = INotificationListener.Stub.asInterface(data.readStrongBinder());
                            String _arg169 = data.readString();
                            if (data.readInt() != 0) {
                                _arg28 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg28 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg32 = NotificationChannelGroup.CREATOR.createFromParcel(data);
                            } else {
                                _arg32 = null;
                            }
                            updateNotificationChannelGroupFromPrivilegedListener(_arg079, _arg169, _arg28, _arg32);
                            reply.writeNoException();
                            return true;
                        case 88:
                            data.enforceInterface(DESCRIPTOR);
                            INotificationListener _arg080 = INotificationListener.Stub.asInterface(data.readStrongBinder());
                            String _arg170 = data.readString();
                            if (data.readInt() != 0) {
                                _arg29 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg29 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg33 = NotificationChannel.CREATOR.createFromParcel(data);
                            } else {
                                _arg33 = null;
                            }
                            updateNotificationChannelFromPrivilegedListener(_arg080, _arg170, _arg29, _arg33);
                            reply.writeNoException();
                            return true;
                        case 89:
                            data.enforceInterface(DESCRIPTOR);
                            INotificationListener _arg081 = INotificationListener.Stub.asInterface(data.readStrongBinder());
                            String _arg171 = data.readString();
                            if (data.readInt() != 0) {
                                _arg210 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg210 = null;
                            }
                            ParceledListSlice _result31 = getNotificationChannelsFromPrivilegedListener(_arg081, _arg171, _arg210);
                            reply.writeNoException();
                            if (_result31 != null) {
                                reply.writeInt(1);
                                _result31.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 90:
                            data.enforceInterface(DESCRIPTOR);
                            INotificationListener _arg082 = INotificationListener.Stub.asInterface(data.readStrongBinder());
                            String _arg172 = data.readString();
                            if (data.readInt() != 0) {
                                _arg211 = UserHandle.CREATOR.createFromParcel(data);
                            } else {
                                _arg211 = null;
                            }
                            ParceledListSlice _result32 = getNotificationChannelGroupsFromPrivilegedListener(_arg082, _arg172, _arg211);
                            reply.writeNoException();
                            if (_result32 != null) {
                                reply.writeInt(1);
                                _result32.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 91:
                            data.enforceInterface(DESCRIPTOR);
                            INotificationListener _arg083 = INotificationListener.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg14 = Adjustment.CREATOR.createFromParcel(data);
                            } else {
                                _arg14 = null;
                            }
                            applyEnqueuedAdjustmentFromAssistant(_arg083, _arg14);
                            reply.writeNoException();
                            return true;
                        case 92:
                            data.enforceInterface(DESCRIPTOR);
                            INotificationListener _arg084 = INotificationListener.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg15 = Adjustment.CREATOR.createFromParcel(data);
                            } else {
                                _arg15 = null;
                            }
                            applyAdjustmentFromAssistant(_arg084, _arg15);
                            reply.writeNoException();
                            return true;
                        case 93:
                            data.enforceInterface(DESCRIPTOR);
                            INotificationListener _arg085 = INotificationListener.Stub.asInterface(data.readStrongBinder());
                            List<Adjustment> _arg173 = data.createTypedArrayList(Adjustment.CREATOR);
                            applyAdjustmentsFromAssistant(_arg085, _arg173);
                            reply.writeNoException();
                            return true;
                        case 94:
                            data.enforceInterface(DESCRIPTOR);
                            INotificationListener _arg086 = INotificationListener.Stub.asInterface(data.readStrongBinder());
                            String _arg174 = data.readString();
                            unsnoozeNotificationFromAssistant(_arg086, _arg174);
                            reply.writeNoException();
                            return true;
                        case 95:
                            data.enforceInterface(DESCRIPTOR);
                            INotificationListener _arg087 = INotificationListener.Stub.asInterface(data.readStrongBinder());
                            String _arg175 = data.readString();
                            unsnoozeNotificationFromSystemListener(_arg087, _arg175);
                            reply.writeNoException();
                            return true;
                        case 96:
                            data.enforceInterface(DESCRIPTOR);
                            ComponentName _result33 = getEffectsSuppressor();
                            reply.writeNoException();
                            if (_result33 != null) {
                                reply.writeInt(1);
                                _result33.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 97:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg05 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            boolean matchesCallFilter = matchesCallFilter(_arg05);
                            reply.writeNoException();
                            reply.writeInt(matchesCallFilter ? 1 : 0);
                            return true;
                        case 98:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isSystemConditionProviderEnabled = isSystemConditionProviderEnabled(data.readString());
                            reply.writeNoException();
                            reply.writeInt(isSystemConditionProviderEnabled ? 1 : 0);
                            return true;
                        case 99:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg06 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg06 = null;
                            }
                            boolean isNotificationListenerAccessGranted = isNotificationListenerAccessGranted(_arg06);
                            reply.writeNoException();
                            reply.writeInt(isNotificationListenerAccessGranted ? 1 : 0);
                            return true;
                        case 100:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg07 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg07 = null;
                            }
                            int _arg176 = data.readInt();
                            boolean isNotificationListenerAccessGrantedForUser = isNotificationListenerAccessGrantedForUser(_arg07, _arg176);
                            reply.writeNoException();
                            reply.writeInt(isNotificationListenerAccessGrantedForUser ? 1 : 0);
                            return true;
                        case 101:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg08 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg08 = null;
                            }
                            boolean isNotificationAssistantAccessGranted = isNotificationAssistantAccessGranted(_arg08);
                            reply.writeNoException();
                            reply.writeInt(isNotificationAssistantAccessGranted ? 1 : 0);
                            return true;
                        case 102:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg09 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg09 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg16 = true;
                            } else {
                                _arg16 = false;
                            }
                            if (data.readInt() != 0) {
                                _arg017 = true;
                            }
                            setNotificationListenerAccessGranted(_arg09, _arg16, _arg017);
                            reply.writeNoException();
                            return true;
                        case 103:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg010 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg010 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg017 = true;
                            }
                            setNotificationAssistantAccessGranted(_arg010, _arg017);
                            reply.writeNoException();
                            return true;
                        case 104:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg011 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg011 = null;
                            }
                            int _arg177 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg212 = true;
                            } else {
                                _arg212 = false;
                            }
                            if (data.readInt() != 0) {
                                _arg017 = true;
                            }
                            setNotificationListenerAccessGrantedForUser(_arg011, _arg177, _arg212, _arg017);
                            reply.writeNoException();
                            return true;
                        case 105:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg012 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg012 = null;
                            }
                            int _arg178 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg017 = true;
                            }
                            setNotificationAssistantAccessGrantedForUser(_arg012, _arg178, _arg017);
                            reply.writeNoException();
                            return true;
                        case 106:
                            data.enforceInterface(DESCRIPTOR);
                            List<String> _result34 = getEnabledNotificationListenerPackages();
                            reply.writeNoException();
                            reply.writeStringList(_result34);
                            return true;
                        case 107:
                            data.enforceInterface(DESCRIPTOR);
                            List<ComponentName> _result35 = getEnabledNotificationListeners(data.readInt());
                            reply.writeNoException();
                            reply.writeTypedList(_result35);
                            return true;
                        case 108:
                            data.enforceInterface(DESCRIPTOR);
                            ComponentName _result36 = getAllowedNotificationAssistantForUser(data.readInt());
                            reply.writeNoException();
                            if (_result36 != null) {
                                reply.writeInt(1);
                                _result36.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 109:
                            data.enforceInterface(DESCRIPTOR);
                            ComponentName _result37 = getAllowedNotificationAssistant();
                            reply.writeNoException();
                            if (_result37 != null) {
                                reply.writeInt(1);
                                _result37.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 110:
                            data.enforceInterface(DESCRIPTOR);
                            ComponentName _result38 = getDefaultNotificationAssistant();
                            reply.writeNoException();
                            if (_result38 != null) {
                                reply.writeInt(1);
                                _result38.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 111:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg088 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg017 = true;
                            }
                            setNASMigrationDoneAndResetDefault(_arg088, _arg017);
                            reply.writeNoException();
                            return true;
                        case 112:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg089 = data.readString();
                            int _arg179 = data.readInt();
                            boolean hasEnabledNotificationListener = hasEnabledNotificationListener(_arg089, _arg179);
                            reply.writeNoException();
                            reply.writeInt(hasEnabledNotificationListener ? 1 : 0);
                            return true;
                        case 113:
                            data.enforceInterface(DESCRIPTOR);
                            int _result39 = getZenMode();
                            reply.writeNoException();
                            reply.writeInt(_result39);
                            return true;
                        case 114:
                            data.enforceInterface(DESCRIPTOR);
                            ZenModeConfig _result40 = getZenModeConfig();
                            reply.writeNoException();
                            if (_result40 != null) {
                                reply.writeInt(1);
                                _result40.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 115:
                            data.enforceInterface(DESCRIPTOR);
                            NotificationManager.Policy _result41 = getConsolidatedNotificationPolicy();
                            reply.writeNoException();
                            if (_result41 != null) {
                                reply.writeInt(1);
                                _result41.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 116:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg090 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg17 = Uri.CREATOR.createFromParcel(data);
                            } else {
                                _arg17 = null;
                            }
                            String _arg233 = data.readString();
                            setZenMode(_arg090, _arg17, _arg233);
                            return true;
                        case 117:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg091 = data.readString();
                            IConditionProvider _arg180 = IConditionProvider.Stub.asInterface(data.readStrongBinder());
                            Condition[] _arg234 = (Condition[]) data.createTypedArray(Condition.CREATOR);
                            notifyConditions(_arg091, _arg180, _arg234);
                            return true;
                        case 118:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isNotificationPolicyAccessGranted = isNotificationPolicyAccessGranted(data.readString());
                            reply.writeNoException();
                            reply.writeInt(isNotificationPolicyAccessGranted ? 1 : 0);
                            return true;
                        case 119:
                            data.enforceInterface(DESCRIPTOR);
                            NotificationManager.Policy _result42 = getNotificationPolicy(data.readString());
                            reply.writeNoException();
                            if (_result42 != null) {
                                reply.writeInt(1);
                                _result42.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 120:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg092 = data.readString();
                            if (data.readInt() != 0) {
                                _arg18 = NotificationManager.Policy.CREATOR.createFromParcel(data);
                            } else {
                                _arg18 = null;
                            }
                            setNotificationPolicy(_arg092, _arg18);
                            reply.writeNoException();
                            return true;
                        case 121:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isNotificationPolicyAccessGrantedForPackage = isNotificationPolicyAccessGrantedForPackage(data.readString());
                            reply.writeNoException();
                            reply.writeInt(isNotificationPolicyAccessGrantedForPackage ? 1 : 0);
                            return true;
                        case 122:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg093 = data.readString();
                            if (data.readInt() != 0) {
                                _arg017 = true;
                            }
                            setNotificationPolicyAccessGranted(_arg093, _arg017);
                            reply.writeNoException();
                            return true;
                        case 123:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg094 = data.readString();
                            int _arg181 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg017 = true;
                            }
                            setNotificationPolicyAccessGrantedForUser(_arg094, _arg181, _arg017);
                            reply.writeNoException();
                            return true;
                        case 124:
                            data.enforceInterface(DESCRIPTOR);
                            AutomaticZenRule _result43 = getAutomaticZenRule(data.readString());
                            reply.writeNoException();
                            if (_result43 != null) {
                                reply.writeInt(1);
                                _result43.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 125:
                            data.enforceInterface(DESCRIPTOR);
                            List<ZenModeConfig.ZenRule> _result44 = getZenRules();
                            reply.writeNoException();
                            reply.writeTypedList(_result44);
                            return true;
                        case 126:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg013 = AutomaticZenRule.CREATOR.createFromParcel(data);
                            } else {
                                _arg013 = null;
                            }
                            String _arg182 = data.readString();
                            String _result45 = addAutomaticZenRule(_arg013, _arg182);
                            reply.writeNoException();
                            reply.writeString(_result45);
                            return true;
                        case 127:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg095 = data.readString();
                            if (data.readInt() != 0) {
                                _arg19 = AutomaticZenRule.CREATOR.createFromParcel(data);
                            } else {
                                _arg19 = null;
                            }
                            boolean updateAutomaticZenRule = updateAutomaticZenRule(_arg095, _arg19);
                            reply.writeNoException();
                            reply.writeInt(updateAutomaticZenRule ? 1 : 0);
                            return true;
                        case 128:
                            data.enforceInterface(DESCRIPTOR);
                            boolean removeAutomaticZenRule = removeAutomaticZenRule(data.readString());
                            reply.writeNoException();
                            reply.writeInt(removeAutomaticZenRule ? 1 : 0);
                            return true;
                        case 129:
                            data.enforceInterface(DESCRIPTOR);
                            boolean removeAutomaticZenRules = removeAutomaticZenRules(data.readString());
                            reply.writeNoException();
                            reply.writeInt(removeAutomaticZenRules ? 1 : 0);
                            return true;
                        case 130:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg014 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg014 = null;
                            }
                            int _result46 = getRuleInstanceCount(_arg014);
                            reply.writeNoException();
                            reply.writeInt(_result46);
                            return true;
                        case 131:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg096 = data.readString();
                            if (data.readInt() != 0) {
                                _arg110 = Condition.CREATOR.createFromParcel(data);
                            } else {
                                _arg110 = null;
                            }
                            setAutomaticZenRuleState(_arg096, _arg110);
                            reply.writeNoException();
                            return true;
                        case 132:
                            data.enforceInterface(DESCRIPTOR);
                            byte[] _result47 = getBackupPayload(data.readInt());
                            reply.writeNoException();
                            reply.writeByteArray(_result47);
                            return true;
                        case 133:
                            data.enforceInterface(DESCRIPTOR);
                            byte[] _arg097 = data.createByteArray();
                            int _arg183 = data.readInt();
                            applyRestore(_arg097, _arg183);
                            reply.writeNoException();
                            return true;
                        case 134:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg098 = data.readString();
                            int _arg184 = data.readInt();
                            ParceledListSlice _result48 = getAppActiveNotifications(_arg098, _arg184);
                            reply.writeNoException();
                            if (_result48 != null) {
                                reply.writeInt(1);
                                _result48.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 135:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg099 = data.readString();
                            String _arg185 = data.readString();
                            setNotificationDelegate(_arg099, _arg185);
                            reply.writeNoException();
                            return true;
                        case 136:
                            data.enforceInterface(DESCRIPTOR);
                            String _result49 = getNotificationDelegate(data.readString());
                            reply.writeNoException();
                            reply.writeString(_result49);
                            return true;
                        case 137:
                            data.enforceInterface(DESCRIPTOR);
                            String _arg0100 = data.readString();
                            String _arg186 = data.readString();
                            int _arg235 = data.readInt();
                            boolean canNotifyAsPackage = canNotifyAsPackage(_arg0100, _arg186, _arg235);
                            reply.writeNoException();
                            reply.writeInt(canNotifyAsPackage ? 1 : 0);
                            return true;
                        case 138:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg017 = true;
                            }
                            setPrivateNotificationsAllowed(_arg017);
                            reply.writeNoException();
                            return true;
                        case 139:
                            data.enforceInterface(DESCRIPTOR);
                            boolean privateNotificationsAllowed = getPrivateNotificationsAllowed();
                            reply.writeNoException();
                            reply.writeInt(privateNotificationsAllowed ? 1 : 0);
                            return true;
                        case 140:
                            data.enforceInterface(DESCRIPTOR);
                            long _arg0101 = data.readLong();
                            int _arg187 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg213 = true;
                            } else {
                                _arg213 = false;
                            }
                            ArrayList arrayList = new ArrayList();
                            long _result50 = pullStats(_arg0101, _arg187, _arg213, arrayList);
                            reply.writeNoException();
                            reply.writeLong(_result50);
                            reply.writeTypedList(arrayList);
                            return true;
                        case 141:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg015 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg015 = null;
                            }
                            int _arg188 = data.readInt();
                            NotificationListenerFilter _result51 = getListenerFilter(_arg015, _arg188);
                            reply.writeNoException();
                            if (_result51 != null) {
                                reply.writeInt(1);
                                _result51.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 142:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg016 = ComponentName.CREATOR.createFromParcel(data);
                            } else {
                                _arg016 = null;
                            }
                            int _arg189 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg214 = NotificationListenerFilter.CREATOR.createFromParcel(data);
                            } else {
                                _arg214 = null;
                            }
                            setListenerFilter(_arg016, _arg189, _arg214);
                            reply.writeNoException();
                            return true;
                        case 143:
                            data.enforceInterface(DESCRIPTOR);
                            INotificationListener _arg0102 = INotificationListener.Stub.asInterface(data.readStrongBinder());
                            int _arg190 = data.readInt();
                            List<String> _arg236 = data.createStringArrayList();
                            migrateNotificationFilter(_arg0102, _arg190, _arg236);
                            reply.writeNoException();
                            return true;
                        case 144:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg017 = true;
                            }
                            setToastRateLimitingEnabled(_arg017);
                            reply.writeNoException();
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements INotificationManager {
            public static INotificationManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.app.INotificationManager
            public void cancelAllNotifications(String pkg, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().cancelAllNotifications(pkg, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void clearData(String pkg, int uid, boolean fromApp) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(uid);
                    _data.writeInt(fromApp ? 1 : 0);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().clearData(pkg, uid, fromApp);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void enqueueTextToast(String pkg, IBinder token, CharSequence text, int duration, int displayId, ITransientNotificationCallback callback) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(pkg);
                    } catch (Throwable th2) {
                        th = th2;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
                try {
                    _data.writeStrongBinder(token);
                    if (text != null) {
                        _data.writeInt(1);
                        TextUtils.writeToParcel(text, _data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    try {
                        _data.writeInt(duration);
                        try {
                            _data.writeInt(displayId);
                            _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                            try {
                                boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _reply.readException();
                                    _reply.recycle();
                                    _data.recycle();
                                    return;
                                }
                                Stub.getDefaultImpl().enqueueTextToast(pkg, token, text, duration, displayId, callback);
                                _reply.recycle();
                                _data.recycle();
                            } catch (Throwable th4) {
                                th = th4;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.app.INotificationManager
            public void enqueueToast(String pkg, IBinder token, ITransientNotification callback, int duration, int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeStrongBinder(token);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeInt(duration);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().enqueueToast(pkg, token, callback, duration, displayId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void cancelToast(String pkg, IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().cancelToast(pkg, token);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void finishToken(String pkg, IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().finishToken(pkg, token);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void enqueueNotificationWithTag(String pkg, String opPkg, String tag, int id, Notification notification, int userId) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(pkg);
                        try {
                            _data.writeString(opPkg);
                            try {
                                _data.writeString(tag);
                            } catch (Throwable th2) {
                                th = th2;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                }
                try {
                    _data.writeInt(id);
                    if (notification != null) {
                        _data.writeInt(1);
                        notification.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    try {
                        _data.writeInt(userId);
                        boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                        if (_status || Stub.getDefaultImpl() == null) {
                            _reply.readException();
                            _reply.recycle();
                            _data.recycle();
                            return;
                        }
                        Stub.getDefaultImpl().enqueueNotificationWithTag(pkg, opPkg, tag, id, notification, userId);
                        _reply.recycle();
                        _data.recycle();
                    } catch (Throwable th6) {
                        th = th6;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.app.INotificationManager
            public void cancelNotificationWithTag(String pkg, String opPkg, String tag, int id, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeString(opPkg);
                    _data.writeString(tag);
                    _data.writeInt(id);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().cancelNotificationWithTag(pkg, opPkg, tag, id, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setShowBadge(String pkg, int uid, boolean showBadge) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(uid);
                    _data.writeInt(showBadge ? 1 : 0);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setShowBadge(pkg, uid, showBadge);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean canShowBadge(String pkg, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(uid);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canShowBadge(pkg, uid);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean hasSentValidMsg(String pkg, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(uid);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasSentValidMsg(pkg, uid);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean isInInvalidMsgState(String pkg, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(uid);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isInInvalidMsgState(pkg, uid);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean hasUserDemotedInvalidMsgApp(String pkg, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(uid);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasUserDemotedInvalidMsgApp(pkg, uid);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setInvalidMsgAppDemoted(String pkg, int uid, boolean isDemoted) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(uid);
                    _data.writeInt(isDemoted ? 1 : 0);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setInvalidMsgAppDemoted(pkg, uid, isDemoted);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setNotificationsEnabledForPackage(String pkg, int uid, boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(uid);
                    _data.writeInt(enabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setNotificationsEnabledForPackage(pkg, uid, enabled);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setNotificationsEnabledWithImportanceLockForPackage(String pkg, int uid, boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(uid);
                    _data.writeInt(enabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setNotificationsEnabledWithImportanceLockForPackage(pkg, uid, enabled);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean areNotificationsEnabledForPackage(String pkg, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(uid);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().areNotificationsEnabledForPackage(pkg, uid);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean areNotificationsEnabled(String pkg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().areNotificationsEnabled(pkg);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public int getPackageImportance(String pkg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPackageImportance(pkg);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public List<String> getAllowedAssistantAdjustments(String pkg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllowedAssistantAdjustments(pkg);
                    }
                    _reply.readException();
                    List<String> _result = _reply.createStringArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void allowAssistantAdjustment(String adjustmentType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(adjustmentType);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().allowAssistantAdjustment(adjustmentType);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void disallowAssistantAdjustment(String adjustmentType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(adjustmentType);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().disallowAssistantAdjustment(adjustmentType);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean shouldHideSilentStatusIcons(String callingPkg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPkg);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().shouldHideSilentStatusIcons(callingPkg);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setHideSilentStatusIcons(boolean hide) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(hide ? 1 : 0);
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setHideSilentStatusIcons(hide);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setBubblesAllowed(String pkg, int uid, int bubblePreference) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(uid);
                    _data.writeInt(bubblePreference);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setBubblesAllowed(pkg, uid, bubblePreference);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean areBubblesAllowed(String pkg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().areBubblesAllowed(pkg);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean areBubblesEnabled(UserHandle user) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = true;
                    if (user != null) {
                        _data.writeInt(1);
                        user.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().areBubblesEnabled(user);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public int getBubblePreferenceForPackage(String pkg, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(28, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBubblePreferenceForPackage(pkg, uid);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void createNotificationChannelGroups(String pkg, ParceledListSlice channelGroupList) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    if (channelGroupList != null) {
                        _data.writeInt(1);
                        channelGroupList.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(29, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().createNotificationChannelGroups(pkg, channelGroupList);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void createNotificationChannels(String pkg, ParceledListSlice channelsList) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    if (channelsList != null) {
                        _data.writeInt(1);
                        channelsList.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(30, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().createNotificationChannels(pkg, channelsList);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void createNotificationChannelsForPackage(String pkg, int uid, ParceledListSlice channelsList) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(uid);
                    if (channelsList != null) {
                        _data.writeInt(1);
                        channelsList.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(31, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().createNotificationChannelsForPackage(pkg, uid, channelsList);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ParceledListSlice getConversations(boolean onlyImportant) throws RemoteException {
                ParceledListSlice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(onlyImportant ? 1 : 0);
                    boolean _status = this.mRemote.transact(32, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConversations(onlyImportant);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParceledListSlice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ParceledListSlice getConversationsForPackage(String pkg, int uid) throws RemoteException {
                ParceledListSlice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(33, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConversationsForPackage(pkg, uid);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParceledListSlice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ParceledListSlice getNotificationChannelGroupsForPackage(String pkg, int uid, boolean includeDeleted) throws RemoteException {
                ParceledListSlice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(uid);
                    _data.writeInt(includeDeleted ? 1 : 0);
                    boolean _status = this.mRemote.transact(34, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotificationChannelGroupsForPackage(pkg, uid, includeDeleted);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParceledListSlice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public NotificationChannelGroup getNotificationChannelGroupForPackage(String groupId, String pkg, int uid) throws RemoteException {
                NotificationChannelGroup _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(groupId);
                    _data.writeString(pkg);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(35, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotificationChannelGroupForPackage(groupId, pkg, uid);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = NotificationChannelGroup.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public NotificationChannelGroup getPopulatedNotificationChannelGroupForPackage(String pkg, int uid, String groupId, boolean includeDeleted) throws RemoteException {
                NotificationChannelGroup _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(uid);
                    _data.writeString(groupId);
                    _data.writeInt(includeDeleted ? 1 : 0);
                    boolean _status = this.mRemote.transact(36, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPopulatedNotificationChannelGroupForPackage(pkg, uid, groupId, includeDeleted);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = NotificationChannelGroup.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void updateNotificationChannelGroupForPackage(String pkg, int uid, NotificationChannelGroup group) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(uid);
                    if (group != null) {
                        _data.writeInt(1);
                        group.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(37, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().updateNotificationChannelGroupForPackage(pkg, uid, group);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void updateNotificationChannelForPackage(String pkg, int uid, NotificationChannel channel) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(uid);
                    if (channel != null) {
                        _data.writeInt(1);
                        channel.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(38, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().updateNotificationChannelForPackage(pkg, uid, channel);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void unlockNotificationChannel(String pkg, int uid, String channelId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(uid);
                    _data.writeString(channelId);
                    boolean _status = this.mRemote.transact(39, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unlockNotificationChannel(pkg, uid, channelId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void unlockAllNotificationChannels() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(40, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unlockAllNotificationChannels();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public NotificationChannel getNotificationChannel(String callingPkg, int userId, String pkg, String channelId) throws RemoteException {
                NotificationChannel _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPkg);
                    _data.writeInt(userId);
                    _data.writeString(pkg);
                    _data.writeString(channelId);
                    boolean _status = this.mRemote.transact(41, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotificationChannel(callingPkg, userId, pkg, channelId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = NotificationChannel.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public NotificationChannel getConversationNotificationChannel(String callingPkg, int userId, String pkg, String channelId, boolean returnParentIfNoConversationChannel, String conversationId) throws RemoteException {
                Throwable th;
                NotificationChannel _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(callingPkg);
                        try {
                            _data.writeInt(userId);
                            try {
                                _data.writeString(pkg);
                            } catch (Throwable th2) {
                                th = th2;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeString(channelId);
                        _data.writeInt(returnParentIfNoConversationChannel ? 1 : 0);
                        try {
                            _data.writeString(conversationId);
                            try {
                                boolean _status = this.mRemote.transact(42, _data, _reply, 0);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _reply.readException();
                                    if (_reply.readInt() != 0) {
                                        _result = NotificationChannel.CREATOR.createFromParcel(_reply);
                                    } else {
                                        _result = null;
                                    }
                                    _reply.recycle();
                                    _data.recycle();
                                    return _result;
                                }
                                NotificationChannel conversationNotificationChannel = Stub.getDefaultImpl().getConversationNotificationChannel(callingPkg, userId, pkg, channelId, returnParentIfNoConversationChannel, conversationId);
                                _reply.recycle();
                                _data.recycle();
                                return conversationNotificationChannel;
                            } catch (Throwable th5) {
                                th = th5;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th6) {
                            th = th6;
                        }
                    } catch (Throwable th7) {
                        th = th7;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th8) {
                    th = th8;
                }
            }

            @Override // android.app.INotificationManager
            public void createConversationNotificationChannelForPackage(String pkg, int uid, NotificationChannel parentChannel, String conversationId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(uid);
                    if (parentChannel != null) {
                        _data.writeInt(1);
                        parentChannel.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(conversationId);
                    boolean _status = this.mRemote.transact(43, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().createConversationNotificationChannelForPackage(pkg, uid, parentChannel, conversationId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public NotificationChannel getNotificationChannelForPackage(String pkg, int uid, String channelId, String conversationId, boolean includeDeleted) throws RemoteException {
                NotificationChannel _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(uid);
                    _data.writeString(channelId);
                    _data.writeString(conversationId);
                    _data.writeInt(includeDeleted ? 1 : 0);
                    boolean _status = this.mRemote.transact(44, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotificationChannelForPackage(pkg, uid, channelId, conversationId, includeDeleted);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = NotificationChannel.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void deleteNotificationChannel(String pkg, String channelId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeString(channelId);
                    boolean _status = this.mRemote.transact(45, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().deleteNotificationChannel(pkg, channelId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ParceledListSlice getNotificationChannels(String callingPkg, String targetPkg, int userId) throws RemoteException {
                ParceledListSlice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPkg);
                    _data.writeString(targetPkg);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(46, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotificationChannels(callingPkg, targetPkg, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParceledListSlice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ParceledListSlice getNotificationChannelsForPackage(String pkg, int uid, boolean includeDeleted) throws RemoteException {
                ParceledListSlice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(uid);
                    _data.writeInt(includeDeleted ? 1 : 0);
                    boolean _status = this.mRemote.transact(47, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotificationChannelsForPackage(pkg, uid, includeDeleted);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParceledListSlice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public int getNumNotificationChannelsForPackage(String pkg, int uid, boolean includeDeleted) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(uid);
                    _data.writeInt(includeDeleted ? 1 : 0);
                    boolean _status = this.mRemote.transact(48, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNumNotificationChannelsForPackage(pkg, uid, includeDeleted);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public int getDeletedChannelCount(String pkg, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(49, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeletedChannelCount(pkg, uid);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public int getBlockedChannelCount(String pkg, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(50, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBlockedChannelCount(pkg, uid);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void deleteNotificationChannelGroup(String pkg, String channelGroupId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeString(channelGroupId);
                    boolean _status = this.mRemote.transact(51, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().deleteNotificationChannelGroup(pkg, channelGroupId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public NotificationChannelGroup getNotificationChannelGroup(String pkg, String channelGroupId) throws RemoteException {
                NotificationChannelGroup _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeString(channelGroupId);
                    boolean _status = this.mRemote.transact(52, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotificationChannelGroup(pkg, channelGroupId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = NotificationChannelGroup.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ParceledListSlice getNotificationChannelGroups(String pkg) throws RemoteException {
                ParceledListSlice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    boolean _status = this.mRemote.transact(53, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotificationChannelGroups(pkg);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParceledListSlice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean onlyHasDefaultChannel(String pkg, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(uid);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(54, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onlyHasDefaultChannel(pkg, uid);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public int getBlockedAppCount(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(55, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBlockedAppCount(userId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean areChannelsBypassingDnd() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(56, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().areChannelsBypassingDnd();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public int getAppsBypassingDndCount(int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(57, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppsBypassingDndCount(uid);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ParceledListSlice getNotificationChannelsBypassingDnd(String pkg, int userId) throws RemoteException {
                ParceledListSlice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(58, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotificationChannelsBypassingDnd(pkg, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParceledListSlice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean isPackagePaused(String pkg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(59, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPackagePaused(pkg);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void deleteNotificationHistoryItem(String pkg, int uid, long postedTime) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(uid);
                    _data.writeLong(postedTime);
                    boolean _status = this.mRemote.transact(60, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().deleteNotificationHistoryItem(pkg, uid, postedTime);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void silenceNotificationSound() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(61, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().silenceNotificationSound();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public StatusBarNotification[] getActiveNotifications(String callingPkg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPkg);
                    boolean _status = this.mRemote.transact(62, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveNotifications(callingPkg);
                    }
                    _reply.readException();
                    StatusBarNotification[] _result = (StatusBarNotification[]) _reply.createTypedArray(StatusBarNotification.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public StatusBarNotification[] getActiveNotificationsWithAttribution(String callingPkg, String callingAttributionTag) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPkg);
                    _data.writeString(callingAttributionTag);
                    boolean _status = this.mRemote.transact(63, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveNotificationsWithAttribution(callingPkg, callingAttributionTag);
                    }
                    _reply.readException();
                    StatusBarNotification[] _result = (StatusBarNotification[]) _reply.createTypedArray(StatusBarNotification.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public StatusBarNotification[] getHistoricalNotifications(String callingPkg, int count, boolean includeSnoozed) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPkg);
                    _data.writeInt(count);
                    _data.writeInt(includeSnoozed ? 1 : 0);
                    boolean _status = this.mRemote.transact(64, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHistoricalNotifications(callingPkg, count, includeSnoozed);
                    }
                    _reply.readException();
                    StatusBarNotification[] _result = (StatusBarNotification[]) _reply.createTypedArray(StatusBarNotification.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public StatusBarNotification[] getHistoricalNotificationsWithAttribution(String callingPkg, String callingAttributionTag, int count, boolean includeSnoozed) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPkg);
                    _data.writeString(callingAttributionTag);
                    _data.writeInt(count);
                    _data.writeInt(includeSnoozed ? 1 : 0);
                    boolean _status = this.mRemote.transact(65, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHistoricalNotificationsWithAttribution(callingPkg, callingAttributionTag, count, includeSnoozed);
                    }
                    _reply.readException();
                    StatusBarNotification[] _result = (StatusBarNotification[]) _reply.createTypedArray(StatusBarNotification.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public NotificationHistory getNotificationHistory(String callingPkg, String callingAttributionTag) throws RemoteException {
                NotificationHistory _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPkg);
                    _data.writeString(callingAttributionTag);
                    boolean _status = this.mRemote.transact(66, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotificationHistory(callingPkg, callingAttributionTag);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = NotificationHistory.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void registerListener(INotificationListener listener, ComponentName component, int userid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    if (component != null) {
                        _data.writeInt(1);
                        component.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(userid);
                    boolean _status = this.mRemote.transact(67, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerListener(listener, component, userid);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void unregisterListener(INotificationListener listener, int userid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    _data.writeInt(userid);
                    boolean _status = this.mRemote.transact(68, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterListener(listener, userid);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void cancelNotificationFromListener(INotificationListener token, String pkg, String tag, int id) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token != null ? token.asBinder() : null);
                    _data.writeString(pkg);
                    _data.writeString(tag);
                    _data.writeInt(id);
                    boolean _status = this.mRemote.transact(69, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().cancelNotificationFromListener(token, pkg, tag, id);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void cancelNotificationsFromListener(INotificationListener token, String[] keys) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token != null ? token.asBinder() : null);
                    _data.writeStringArray(keys);
                    boolean _status = this.mRemote.transact(70, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().cancelNotificationsFromListener(token, keys);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void snoozeNotificationUntilContextFromListener(INotificationListener token, String key, String snoozeCriterionId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token != null ? token.asBinder() : null);
                    _data.writeString(key);
                    _data.writeString(snoozeCriterionId);
                    boolean _status = this.mRemote.transact(71, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().snoozeNotificationUntilContextFromListener(token, key, snoozeCriterionId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void snoozeNotificationUntilFromListener(INotificationListener token, String key, long until) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token != null ? token.asBinder() : null);
                    _data.writeString(key);
                    _data.writeLong(until);
                    boolean _status = this.mRemote.transact(72, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().snoozeNotificationUntilFromListener(token, key, until);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void requestBindListener(ComponentName component) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (component != null) {
                        _data.writeInt(1);
                        component.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(73, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().requestBindListener(component);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void requestUnbindListener(INotificationListener token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token != null ? token.asBinder() : null);
                    boolean _status = this.mRemote.transact(74, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().requestUnbindListener(token);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void requestBindProvider(ComponentName component) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (component != null) {
                        _data.writeInt(1);
                        component.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(75, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().requestBindProvider(component);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void requestUnbindProvider(IConditionProvider token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token != null ? token.asBinder() : null);
                    boolean _status = this.mRemote.transact(76, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().requestUnbindProvider(token);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setNotificationsShownFromListener(INotificationListener token, String[] keys) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token != null ? token.asBinder() : null);
                    _data.writeStringArray(keys);
                    boolean _status = this.mRemote.transact(77, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setNotificationsShownFromListener(token, keys);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ParceledListSlice getActiveNotificationsFromListener(INotificationListener token, String[] keys, int trim) throws RemoteException {
                ParceledListSlice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token != null ? token.asBinder() : null);
                    _data.writeStringArray(keys);
                    _data.writeInt(trim);
                    boolean _status = this.mRemote.transact(78, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveNotificationsFromListener(token, keys, trim);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParceledListSlice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ParceledListSlice getSnoozedNotificationsFromListener(INotificationListener token, int trim) throws RemoteException {
                ParceledListSlice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token != null ? token.asBinder() : null);
                    _data.writeInt(trim);
                    boolean _status = this.mRemote.transact(79, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSnoozedNotificationsFromListener(token, trim);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParceledListSlice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void clearRequestedListenerHints(INotificationListener token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token != null ? token.asBinder() : null);
                    boolean _status = this.mRemote.transact(80, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().clearRequestedListenerHints(token);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void requestHintsFromListener(INotificationListener token, int hints) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token != null ? token.asBinder() : null);
                    _data.writeInt(hints);
                    boolean _status = this.mRemote.transact(81, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().requestHintsFromListener(token, hints);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public int getHintsFromListener(INotificationListener token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token != null ? token.asBinder() : null);
                    boolean _status = this.mRemote.transact(82, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHintsFromListener(token);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void requestInterruptionFilterFromListener(INotificationListener token, int interruptionFilter) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token != null ? token.asBinder() : null);
                    _data.writeInt(interruptionFilter);
                    boolean _status = this.mRemote.transact(83, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().requestInterruptionFilterFromListener(token, interruptionFilter);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public int getInterruptionFilterFromListener(INotificationListener token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token != null ? token.asBinder() : null);
                    boolean _status = this.mRemote.transact(84, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInterruptionFilterFromListener(token);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setOnNotificationPostedTrimFromListener(INotificationListener token, int trim) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token != null ? token.asBinder() : null);
                    _data.writeInt(trim);
                    boolean _status = this.mRemote.transact(85, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setOnNotificationPostedTrimFromListener(token, trim);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setInterruptionFilter(String pkg, int interruptionFilter) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(interruptionFilter);
                    boolean _status = this.mRemote.transact(86, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setInterruptionFilter(pkg, interruptionFilter);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void updateNotificationChannelGroupFromPrivilegedListener(INotificationListener token, String pkg, UserHandle user, NotificationChannelGroup group) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token != null ? token.asBinder() : null);
                    _data.writeString(pkg);
                    if (user != null) {
                        _data.writeInt(1);
                        user.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (group != null) {
                        _data.writeInt(1);
                        group.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(87, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().updateNotificationChannelGroupFromPrivilegedListener(token, pkg, user, group);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void updateNotificationChannelFromPrivilegedListener(INotificationListener token, String pkg, UserHandle user, NotificationChannel channel) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token != null ? token.asBinder() : null);
                    _data.writeString(pkg);
                    if (user != null) {
                        _data.writeInt(1);
                        user.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (channel != null) {
                        _data.writeInt(1);
                        channel.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(88, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().updateNotificationChannelFromPrivilegedListener(token, pkg, user, channel);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ParceledListSlice getNotificationChannelsFromPrivilegedListener(INotificationListener token, String pkg, UserHandle user) throws RemoteException {
                ParceledListSlice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token != null ? token.asBinder() : null);
                    _data.writeString(pkg);
                    if (user != null) {
                        _data.writeInt(1);
                        user.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(89, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotificationChannelsFromPrivilegedListener(token, pkg, user);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParceledListSlice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ParceledListSlice getNotificationChannelGroupsFromPrivilegedListener(INotificationListener token, String pkg, UserHandle user) throws RemoteException {
                ParceledListSlice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token != null ? token.asBinder() : null);
                    _data.writeString(pkg);
                    if (user != null) {
                        _data.writeInt(1);
                        user.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(90, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotificationChannelGroupsFromPrivilegedListener(token, pkg, user);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParceledListSlice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void applyEnqueuedAdjustmentFromAssistant(INotificationListener token, Adjustment adjustment) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token != null ? token.asBinder() : null);
                    if (adjustment != null) {
                        _data.writeInt(1);
                        adjustment.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(91, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().applyEnqueuedAdjustmentFromAssistant(token, adjustment);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void applyAdjustmentFromAssistant(INotificationListener token, Adjustment adjustment) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token != null ? token.asBinder() : null);
                    if (adjustment != null) {
                        _data.writeInt(1);
                        adjustment.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(92, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().applyAdjustmentFromAssistant(token, adjustment);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void applyAdjustmentsFromAssistant(INotificationListener token, List<Adjustment> adjustments) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token != null ? token.asBinder() : null);
                    _data.writeTypedList(adjustments);
                    boolean _status = this.mRemote.transact(93, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().applyAdjustmentsFromAssistant(token, adjustments);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void unsnoozeNotificationFromAssistant(INotificationListener token, String key) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token != null ? token.asBinder() : null);
                    _data.writeString(key);
                    boolean _status = this.mRemote.transact(94, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unsnoozeNotificationFromAssistant(token, key);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void unsnoozeNotificationFromSystemListener(INotificationListener token, String key) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token != null ? token.asBinder() : null);
                    _data.writeString(key);
                    boolean _status = this.mRemote.transact(95, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unsnoozeNotificationFromSystemListener(token, key);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ComponentName getEffectsSuppressor() throws RemoteException {
                ComponentName _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(96, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEffectsSuppressor();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ComponentName.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean matchesCallFilter(Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = true;
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(97, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().matchesCallFilter(extras);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean isSystemConditionProviderEnabled(String path) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(path);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(98, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSystemConditionProviderEnabled(path);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean isNotificationListenerAccessGranted(ComponentName listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = true;
                    if (listener != null) {
                        _data.writeInt(1);
                        listener.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(99, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isNotificationListenerAccessGranted(listener);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean isNotificationListenerAccessGrantedForUser(ComponentName listener, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = true;
                    if (listener != null) {
                        _data.writeInt(1);
                        listener.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(100, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isNotificationListenerAccessGrantedForUser(listener, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean isNotificationAssistantAccessGranted(ComponentName assistant) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = true;
                    if (assistant != null) {
                        _data.writeInt(1);
                        assistant.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(101, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isNotificationAssistantAccessGranted(assistant);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setNotificationListenerAccessGranted(ComponentName listener, boolean enabled, boolean userSet) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    if (listener != null) {
                        _data.writeInt(1);
                        listener.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(enabled ? 1 : 0);
                    if (!userSet) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(102, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setNotificationListenerAccessGranted(listener, enabled, userSet);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setNotificationAssistantAccessGranted(ComponentName assistant, boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    if (assistant != null) {
                        _data.writeInt(1);
                        assistant.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!enabled) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(103, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setNotificationAssistantAccessGranted(assistant, enabled);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setNotificationListenerAccessGrantedForUser(ComponentName listener, int userId, boolean enabled, boolean userSet) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    if (listener != null) {
                        _data.writeInt(1);
                        listener.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(userId);
                    _data.writeInt(enabled ? 1 : 0);
                    if (!userSet) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(104, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setNotificationListenerAccessGrantedForUser(listener, userId, enabled, userSet);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setNotificationAssistantAccessGrantedForUser(ComponentName assistant, int userId, boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    if (assistant != null) {
                        _data.writeInt(1);
                        assistant.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(userId);
                    if (!enabled) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(105, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setNotificationAssistantAccessGrantedForUser(assistant, userId, enabled);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public List<String> getEnabledNotificationListenerPackages() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(106, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEnabledNotificationListenerPackages();
                    }
                    _reply.readException();
                    List<String> _result = _reply.createStringArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public List<ComponentName> getEnabledNotificationListeners(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(107, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEnabledNotificationListeners(userId);
                    }
                    _reply.readException();
                    List<ComponentName> _result = _reply.createTypedArrayList(ComponentName.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ComponentName getAllowedNotificationAssistantForUser(int userId) throws RemoteException {
                ComponentName _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(108, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllowedNotificationAssistantForUser(userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ComponentName.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ComponentName getAllowedNotificationAssistant() throws RemoteException {
                ComponentName _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(109, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllowedNotificationAssistant();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ComponentName.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ComponentName getDefaultNotificationAssistant() throws RemoteException {
                ComponentName _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(110, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDefaultNotificationAssistant();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ComponentName.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setNASMigrationDoneAndResetDefault(int userId, boolean loadFromConfig) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    _data.writeInt(loadFromConfig ? 1 : 0);
                    boolean _status = this.mRemote.transact(111, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setNASMigrationDoneAndResetDefault(userId, loadFromConfig);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean hasEnabledNotificationListener(String packageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(112, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasEnabledNotificationListener(packageName, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public int getZenMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(113, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getZenMode();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ZenModeConfig getZenModeConfig() throws RemoteException {
                ZenModeConfig _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(114, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getZenModeConfig();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ZenModeConfig.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public NotificationManager.Policy getConsolidatedNotificationPolicy() throws RemoteException {
                NotificationManager.Policy _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(115, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConsolidatedNotificationPolicy();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = NotificationManager.Policy.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setZenMode(int mode, Uri conditionId, String reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mode);
                    if (conditionId != null) {
                        _data.writeInt(1);
                        conditionId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(reason);
                    boolean _status = this.mRemote.transact(116, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setZenMode(mode, conditionId, reason);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void notifyConditions(String pkg, IConditionProvider provider, Condition[] conditions) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeStrongBinder(provider != null ? provider.asBinder() : null);
                    _data.writeTypedArray(conditions, 0);
                    boolean _status = this.mRemote.transact(117, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyConditions(pkg, provider, conditions);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean isNotificationPolicyAccessGranted(String pkg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(118, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isNotificationPolicyAccessGranted(pkg);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public NotificationManager.Policy getNotificationPolicy(String pkg) throws RemoteException {
                NotificationManager.Policy _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    boolean _status = this.mRemote.transact(119, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotificationPolicy(pkg);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = NotificationManager.Policy.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setNotificationPolicy(String pkg, NotificationManager.Policy policy) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    if (policy != null) {
                        _data.writeInt(1);
                        policy.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(120, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setNotificationPolicy(pkg, policy);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean isNotificationPolicyAccessGrantedForPackage(String pkg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(121, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isNotificationPolicyAccessGrantedForPackage(pkg);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setNotificationPolicyAccessGranted(String pkg, boolean granted) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(granted ? 1 : 0);
                    boolean _status = this.mRemote.transact(122, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setNotificationPolicyAccessGranted(pkg, granted);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setNotificationPolicyAccessGrantedForUser(String pkg, int userId, boolean granted) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeInt(userId);
                    _data.writeInt(granted ? 1 : 0);
                    boolean _status = this.mRemote.transact(123, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setNotificationPolicyAccessGrantedForUser(pkg, userId, granted);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public AutomaticZenRule getAutomaticZenRule(String id) throws RemoteException {
                AutomaticZenRule _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(id);
                    boolean _status = this.mRemote.transact(124, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAutomaticZenRule(id);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = AutomaticZenRule.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public List<ZenModeConfig.ZenRule> getZenRules() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(125, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getZenRules();
                    }
                    _reply.readException();
                    List<ZenModeConfig.ZenRule> _result = _reply.createTypedArrayList(ZenModeConfig.ZenRule.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public String addAutomaticZenRule(AutomaticZenRule automaticZenRule, String pkg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (automaticZenRule != null) {
                        _data.writeInt(1);
                        automaticZenRule.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(pkg);
                    boolean _status = this.mRemote.transact(126, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addAutomaticZenRule(automaticZenRule, pkg);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean updateAutomaticZenRule(String id, AutomaticZenRule automaticZenRule) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(id);
                    boolean _result = true;
                    if (automaticZenRule != null) {
                        _data.writeInt(1);
                        automaticZenRule.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(127, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateAutomaticZenRule(id, automaticZenRule);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean removeAutomaticZenRule(String id) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(id);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(128, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeAutomaticZenRule(id);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean removeAutomaticZenRules(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(129, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeAutomaticZenRules(packageName);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public int getRuleInstanceCount(ComponentName owner) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (owner != null) {
                        _data.writeInt(1);
                        owner.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(130, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRuleInstanceCount(owner);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setAutomaticZenRuleState(String id, Condition condition) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(id);
                    if (condition != null) {
                        _data.writeInt(1);
                        condition.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(131, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setAutomaticZenRuleState(id, condition);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public byte[] getBackupPayload(int user) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(user);
                    boolean _status = this.mRemote.transact(132, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBackupPayload(user);
                    }
                    _reply.readException();
                    byte[] _result = _reply.createByteArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void applyRestore(byte[] payload, int user) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(payload);
                    _data.writeInt(user);
                    boolean _status = this.mRemote.transact(133, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().applyRestore(payload, user);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public ParceledListSlice getAppActiveNotifications(String callingPkg, int userId) throws RemoteException {
                ParceledListSlice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPkg);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(134, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppActiveNotifications(callingPkg, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParceledListSlice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setNotificationDelegate(String callingPkg, String delegate) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPkg);
                    _data.writeString(delegate);
                    boolean _status = this.mRemote.transact(135, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setNotificationDelegate(callingPkg, delegate);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public String getNotificationDelegate(String callingPkg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPkg);
                    boolean _status = this.mRemote.transact(136, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotificationDelegate(callingPkg);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean canNotifyAsPackage(String callingPkg, String targetPkg, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPkg);
                    _data.writeString(targetPkg);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(137, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canNotifyAsPackage(callingPkg, targetPkg, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setPrivateNotificationsAllowed(boolean allow) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(allow ? 1 : 0);
                    boolean _status = this.mRemote.transact(138, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setPrivateNotificationsAllowed(allow);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public boolean getPrivateNotificationsAllowed() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(139, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPrivateNotificationsAllowed();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public long pullStats(long startNs, int report, boolean doAgg, List<ParcelFileDescriptor> stats) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(startNs);
                    _data.writeInt(report);
                    _data.writeInt(doAgg ? 1 : 0);
                    boolean _status = this.mRemote.transact(140, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().pullStats(startNs, report, doAgg, stats);
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    _reply.readTypedList(stats, ParcelFileDescriptor.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public NotificationListenerFilter getListenerFilter(ComponentName cn, int userId) throws RemoteException {
                NotificationListenerFilter _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (cn != null) {
                        _data.writeInt(1);
                        cn.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(141, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getListenerFilter(cn, userId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = NotificationListenerFilter.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setListenerFilter(ComponentName cn, int userId, NotificationListenerFilter nlf) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (cn != null) {
                        _data.writeInt(1);
                        cn.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(userId);
                    if (nlf != null) {
                        _data.writeInt(1);
                        nlf.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(142, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setListenerFilter(cn, userId, nlf);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void migrateNotificationFilter(INotificationListener token, int defaultTypes, List<String> disallowedPkgs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token != null ? token.asBinder() : null);
                    _data.writeInt(defaultTypes);
                    _data.writeStringList(disallowedPkgs);
                    boolean _status = this.mRemote.transact(143, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().migrateNotificationFilter(token, defaultTypes, disallowedPkgs);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.app.INotificationManager
            public void setToastRateLimitingEnabled(boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(144, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setToastRateLimitingEnabled(enable);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INotificationManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static INotificationManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
