package android.app;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Pair;
import android.util.Slog;
import com.android.internal.statusbar.IStatusBarService;
import com.android.internal.statusbar.NotificationVisibility;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes.dex */
public class StatusBarManager {
    public static final int CAMERA_LAUNCH_SOURCE_LIFT_TRIGGER = 2;
    public static final int CAMERA_LAUNCH_SOURCE_POWER_DOUBLE_TAP = 1;
    public static final int CAMERA_LAUNCH_SOURCE_WIGGLE = 0;
    public static final int DEFAULT_SETUP_DISABLE2_FLAGS = 16;
    public static final int DEFAULT_SETUP_DISABLE_FLAGS = 61145088;
    private static final int DEFAULT_SIM_LOCKED_DISABLED_FLAGS = 65536;
    public static final int DISABLE2_GLOBAL_ACTIONS = 8;
    public static final int DISABLE2_MASK = 31;
    public static final int DISABLE2_NONE = 0;
    public static final int DISABLE2_NOTIFICATION_SHADE = 4;
    public static final int DISABLE2_QUICK_SETTINGS = 1;
    public static final int DISABLE2_ROTATE_SUGGESTIONS = 16;
    public static final int DISABLE2_SYSTEM_ICONS = 2;
    public static final int DISABLE_BACK = 4194304;
    public static final int DISABLE_CLOCK = 8388608;
    public static final int DISABLE_EXPAND = 65536;
    public static final int DISABLE_HOME = 2097152;
    public static final int DISABLE_MASK = 134152192;
    @Deprecated
    public static final int DISABLE_NAVIGATION = 18874368;
    public static final int DISABLE_NONE = 0;
    public static final int DISABLE_NOTIFICATION_ALERTS = 262144;
    public static final int DISABLE_NOTIFICATION_ICONS = 131072;
    @Deprecated
    public static final int DISABLE_NOTIFICATION_TICKER = 524288;
    public static final int DISABLE_ONGOING_CALL_CHIP = 67108864;
    public static final int DISABLE_RECENT = 16777216;
    public static final int DISABLE_SEARCH = 33554432;
    public static final int DISABLE_SYSTEM_INFO = 1048576;
    public static final int NAVIGATION_HINT_BACK_ALT = 1;
    public static final int NAVIGATION_HINT_IME_SHOWN = 2;
    public static final int WINDOW_NAVIGATION_BAR = 2;
    public static final int WINDOW_STATE_HIDDEN = 2;
    public static final int WINDOW_STATE_HIDING = 1;
    public static final int WINDOW_STATE_SHOWING = 0;
    public static final int WINDOW_STATUS_BAR = 1;
    private Context mContext;
    private IStatusBarService mService;
    private IBinder mToken = new Binder();

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Disable2Flags {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface DisableFlags {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface WindowType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface WindowVisibleState {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public StatusBarManager(Context context) {
        this.mContext = context;
    }

    private synchronized IStatusBarService getService() {
        if (this.mService == null) {
            IStatusBarService asInterface = IStatusBarService.Stub.asInterface(ServiceManager.getService(Context.STATUS_BAR_SERVICE));
            this.mService = asInterface;
            if (asInterface == null) {
                Slog.w("StatusBarManager", "warning: no STATUS_BAR_SERVICE");
            }
        }
        return this.mService;
    }

    public void disable(int what) {
        try {
            int userId = Binder.getCallingUserHandle().getIdentifier();
            IStatusBarService svc = getService();
            if (svc != null) {
                svc.disableForUser(what, this.mToken, this.mContext.getPackageName(), userId);
            }
        } catch (RemoteException ex) {
            throw ex.rethrowFromSystemServer();
        }
    }

    public void disable2(int what) {
        try {
            int userId = Binder.getCallingUserHandle().getIdentifier();
            IStatusBarService svc = getService();
            if (svc != null) {
                svc.disable2ForUser(what, this.mToken, this.mContext.getPackageName(), userId);
            }
        } catch (RemoteException ex) {
            throw ex.rethrowFromSystemServer();
        }
    }

    public void clickNotification(String key, int rank, int count, boolean visible) {
        clickNotificationInternal(key, rank, count, visible);
    }

    private void clickNotificationInternal(String key, int rank, int count, boolean visible) {
        try {
            IStatusBarService svc = getService();
            if (svc != null) {
                svc.onNotificationClick(key, NotificationVisibility.obtain(key, rank, count, visible));
            }
        } catch (RemoteException ex) {
            throw ex.rethrowFromSystemServer();
        }
    }

    public void sendNotificationFeedback(String key, Bundle feedback) {
        try {
            IStatusBarService svc = getService();
            if (svc != null) {
                svc.onNotificationFeedbackReceived(key, feedback);
            }
        } catch (RemoteException ex) {
            throw ex.rethrowFromSystemServer();
        }
    }

    public void expandNotificationsPanel() {
        try {
            IStatusBarService svc = getService();
            if (svc != null) {
                svc.expandNotificationsPanel();
            }
        } catch (RemoteException ex) {
            throw ex.rethrowFromSystemServer();
        }
    }

    public void collapsePanels() {
        try {
            IStatusBarService svc = getService();
            if (svc != null) {
                svc.collapsePanels();
            }
        } catch (RemoteException ex) {
            throw ex.rethrowFromSystemServer();
        }
    }

    public void togglePanel() {
        try {
            IStatusBarService svc = getService();
            if (svc != null) {
                svc.togglePanel();
            }
        } catch (RemoteException ex) {
            throw ex.rethrowFromSystemServer();
        }
    }

    public void handleSystemKey(int key) {
        try {
            IStatusBarService svc = getService();
            if (svc != null) {
                svc.handleSystemKey(key);
            }
        } catch (RemoteException ex) {
            throw ex.rethrowFromSystemServer();
        }
    }

    public void expandSettingsPanel() {
        expandSettingsPanel(null);
    }

    public void expandSettingsPanel(String subPanel) {
        try {
            IStatusBarService svc = getService();
            if (svc != null) {
                svc.expandSettingsPanel(subPanel);
            }
        } catch (RemoteException ex) {
            throw ex.rethrowFromSystemServer();
        }
    }

    public void setIcon(String slot, int iconId, int iconLevel, String contentDescription) {
        try {
            IStatusBarService svc = getService();
            if (svc != null) {
                svc.setIcon(slot, this.mContext.getPackageName(), iconId, iconLevel, contentDescription);
            }
        } catch (RemoteException ex) {
            throw ex.rethrowFromSystemServer();
        }
    }

    public void removeIcon(String slot) {
        try {
            IStatusBarService svc = getService();
            if (svc != null) {
                svc.removeIcon(slot);
            }
        } catch (RemoteException ex) {
            throw ex.rethrowFromSystemServer();
        }
    }

    public void setIconVisibility(String slot, boolean visible) {
        try {
            IStatusBarService svc = getService();
            if (svc != null) {
                svc.setIconVisibility(slot, visible);
            }
        } catch (RemoteException ex) {
            throw ex.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void setDisabledForSetup(boolean disabled) {
        try {
            int userId = Binder.getCallingUserHandle().getIdentifier();
            IStatusBarService svc = getService();
            if (svc != null) {
                int i = 0;
                svc.disableForUser(disabled ? DEFAULT_SETUP_DISABLE_FLAGS : 0, this.mToken, this.mContext.getPackageName(), userId);
                if (disabled) {
                    i = 16;
                }
                svc.disable2ForUser(i, this.mToken, this.mContext.getPackageName(), userId);
            }
        } catch (RemoteException ex) {
            throw ex.rethrowFromSystemServer();
        }
    }

    @SystemApi(client = SystemApi.Client.MODULE_LIBRARIES)
    public void setExpansionDisabledForSimNetworkLock(boolean disabled) {
        try {
            int userId = Binder.getCallingUserHandle().getIdentifier();
            IStatusBarService svc = getService();
            if (svc != null) {
                svc.disableForUser(disabled ? 65536 : 0, this.mToken, this.mContext.getPackageName(), userId);
            }
        } catch (RemoteException ex) {
            throw ex.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public DisableInfo getDisableInfo() {
        try {
            int userId = Binder.getCallingUserHandle().getIdentifier();
            IStatusBarService svc = getService();
            int[] flags = {0, 0};
            if (svc != null) {
                flags = svc.getDisableFlags(this.mToken, userId);
            }
            return new DisableInfo(flags[0], flags[1]);
        } catch (RemoteException ex) {
            throw ex.rethrowFromSystemServer();
        }
    }

    public static String windowStateToString(int state) {
        return state == 1 ? "WINDOW_STATE_HIDING" : state == 2 ? "WINDOW_STATE_HIDDEN" : state == 0 ? "WINDOW_STATE_SHOWING" : "WINDOW_STATE_UNKNOWN";
    }

    @SystemApi
    /* loaded from: classes.dex */
    public static final class DisableInfo {
        private boolean mClock;
        private boolean mNavigateHome;
        private boolean mNotificationIcons;
        private boolean mNotificationPeeking;
        private boolean mRecents;
        private boolean mSearch;
        private boolean mStatusBarExpansion;
        private boolean mSystemIcons;

        public DisableInfo(int flags1, int flags2) {
            boolean z = true;
            this.mStatusBarExpansion = (65536 & flags1) != 0;
            this.mNavigateHome = (2097152 & flags1) != 0;
            this.mNotificationPeeking = (262144 & flags1) != 0;
            this.mRecents = (16777216 & flags1) != 0;
            this.mSearch = (33554432 & flags1) != 0;
            this.mSystemIcons = (1048576 & flags1) != 0;
            this.mClock = (8388608 & flags1) != 0;
            this.mNotificationIcons = (131072 & flags1) == 0 ? false : z;
        }

        public DisableInfo() {
        }

        @SystemApi
        public boolean isStatusBarExpansionDisabled() {
            return this.mStatusBarExpansion;
        }

        public void setStatusBarExpansionDisabled(boolean disabled) {
            this.mStatusBarExpansion = disabled;
        }

        @SystemApi
        public boolean isNavigateToHomeDisabled() {
            return this.mNavigateHome;
        }

        public void setNagivationHomeDisabled(boolean disabled) {
            this.mNavigateHome = disabled;
        }

        @SystemApi
        public boolean isNotificationPeekingDisabled() {
            return this.mNotificationPeeking;
        }

        public void setNotificationPeekingDisabled(boolean disabled) {
            this.mNotificationPeeking = disabled;
        }

        @SystemApi
        public boolean isRecentsDisabled() {
            return this.mRecents;
        }

        public void setRecentsDisabled(boolean disabled) {
            this.mRecents = disabled;
        }

        @SystemApi
        public boolean isSearchDisabled() {
            return this.mSearch;
        }

        public void setSearchDisabled(boolean disabled) {
            this.mSearch = disabled;
        }

        public boolean areSystemIconsDisabled() {
            return this.mSystemIcons;
        }

        public void setSystemIconsDisabled(boolean disabled) {
            this.mSystemIcons = disabled;
        }

        public boolean isClockDisabled() {
            return this.mClock;
        }

        public void setClockDisabled(boolean disabled) {
            this.mClock = disabled;
        }

        public boolean areNotificationIconsDisabled() {
            return this.mNotificationIcons;
        }

        public void setNotificationIconsDisabled(boolean disabled) {
            this.mNotificationIcons = disabled;
        }

        @SystemApi
        public boolean areAllComponentsEnabled() {
            return !this.mStatusBarExpansion && !this.mNavigateHome && !this.mNotificationPeeking && !this.mRecents && !this.mSearch && !this.mSystemIcons && !this.mClock && !this.mNotificationIcons;
        }

        public void setEnableAll() {
            this.mStatusBarExpansion = false;
            this.mNavigateHome = false;
            this.mNotificationPeeking = false;
            this.mRecents = false;
            this.mSearch = false;
            this.mSystemIcons = false;
            this.mClock = false;
            this.mNotificationIcons = false;
        }

        public boolean areAllComponentsDisabled() {
            return this.mStatusBarExpansion && this.mNavigateHome && this.mNotificationPeeking && this.mRecents && this.mSearch && this.mSystemIcons && this.mClock && this.mNotificationIcons;
        }

        public void setDisableAll() {
            this.mStatusBarExpansion = true;
            this.mNavigateHome = true;
            this.mNotificationPeeking = true;
            this.mRecents = true;
            this.mSearch = true;
            this.mSystemIcons = true;
            this.mClock = true;
            this.mNotificationIcons = true;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("DisableInfo: ");
            sb.append(" mStatusBarExpansion=");
            String str = "disabled";
            sb.append(this.mStatusBarExpansion ? str : "enabled");
            sb.append(" mNavigateHome=");
            sb.append(this.mNavigateHome ? str : "enabled");
            sb.append(" mNotificationPeeking=");
            sb.append(this.mNotificationPeeking ? str : "enabled");
            sb.append(" mRecents=");
            sb.append(this.mRecents ? str : "enabled");
            sb.append(" mSearch=");
            sb.append(this.mSearch ? str : "enabled");
            sb.append(" mSystemIcons=");
            sb.append(this.mSystemIcons ? str : "enabled");
            sb.append(" mClock=");
            sb.append(this.mClock ? str : "enabled");
            sb.append(" mNotificationIcons=");
            if (!this.mNotificationIcons) {
                str = "enabled";
            }
            sb.append(str);
            return sb.toString();
        }

        public Pair<Integer, Integer> toFlags() {
            int disable1 = 0;
            if (this.mStatusBarExpansion) {
                disable1 = 0 | 65536;
            }
            if (this.mNavigateHome) {
                disable1 |= 2097152;
            }
            if (this.mNotificationPeeking) {
                disable1 |= 262144;
            }
            if (this.mRecents) {
                disable1 |= 16777216;
            }
            if (this.mSearch) {
                disable1 |= 33554432;
            }
            if (this.mSystemIcons) {
                disable1 |= 1048576;
            }
            if (this.mClock) {
                disable1 |= 8388608;
            }
            if (this.mNotificationIcons) {
                disable1 |= 131072;
            }
            return new Pair<>(Integer.valueOf(disable1), 0);
        }
    }
}
