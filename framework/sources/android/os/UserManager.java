package android.os;

import android.accounts.AccountManager;
import android.annotation.SystemApi;
import android.app.ActivityManager;
import android.app.PropertyInvalidatedCache;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.AndroidException;
import android.util.ArraySet;
import com.android.internal.R;
import com.android.internal.os.RoSystemProperties;
import com.oplus.uifirst.IOplusUIFirstManager;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import system.ext.loader.core.ExtLoader;
/* loaded from: classes2.dex */
public class UserManager {
    private static final String ACTION_CREATE_USER = "android.os.action.CREATE_USER";
    @SystemApi
    public static final String ACTION_USER_RESTRICTIONS_CHANGED = "android.os.action.USER_RESTRICTIONS_CHANGED";
    public static final String ALLOW_PARENT_PROFILE_APP_LINKING = "allow_parent_profile_app_linking";
    private static final String CACHE_KEY_IS_USER_UNLOCKED_PROPERTY = "cache_key.is_user_unlocked";
    @Deprecated
    public static final String DISALLOW_ADD_MANAGED_PROFILE = "no_add_managed_profile";
    public static final String DISALLOW_ADD_USER = "no_add_user";
    public static final String DISALLOW_ADJUST_VOLUME = "no_adjust_volume";
    public static final String DISALLOW_AIRPLANE_MODE = "no_airplane_mode";
    public static final String DISALLOW_AMBIENT_DISPLAY = "no_ambient_display";
    public static final String DISALLOW_APPS_CONTROL = "no_control_apps";
    public static final String DISALLOW_AUTOFILL = "no_autofill";
    public static final String DISALLOW_BIOMETRIC = "disallow_biometric";
    public static final String DISALLOW_BLUETOOTH = "no_bluetooth";
    public static final String DISALLOW_BLUETOOTH_SHARING = "no_bluetooth_sharing";
    public static final String DISALLOW_CAMERA = "no_camera";
    public static final String DISALLOW_CAMERA_TOGGLE = "disallow_camera_toggle";
    public static final String DISALLOW_CONFIG_BLUETOOTH = "no_config_bluetooth";
    public static final String DISALLOW_CONFIG_BRIGHTNESS = "no_config_brightness";
    public static final String DISALLOW_CONFIG_CELL_BROADCASTS = "no_config_cell_broadcasts";
    public static final String DISALLOW_CONFIG_CREDENTIALS = "no_config_credentials";
    public static final String DISALLOW_CONFIG_DATE_TIME = "no_config_date_time";
    public static final String DISALLOW_CONFIG_LOCALE = "no_config_locale";
    public static final String DISALLOW_CONFIG_LOCATION = "no_config_location";
    public static final String DISALLOW_CONFIG_MOBILE_NETWORKS = "no_config_mobile_networks";
    public static final String DISALLOW_CONFIG_PRIVATE_DNS = "disallow_config_private_dns";
    public static final String DISALLOW_CONFIG_SCREEN_TIMEOUT = "no_config_screen_timeout";
    public static final String DISALLOW_CONFIG_TETHERING = "no_config_tethering";
    public static final String DISALLOW_CONFIG_VPN = "no_config_vpn";
    public static final String DISALLOW_CONFIG_WIFI = "no_config_wifi";
    public static final String DISALLOW_CONTENT_CAPTURE = "no_content_capture";
    public static final String DISALLOW_CONTENT_SUGGESTIONS = "no_content_suggestions";
    public static final String DISALLOW_CREATE_WINDOWS = "no_create_windows";
    public static final String DISALLOW_CROSS_PROFILE_COPY_PASTE = "no_cross_profile_copy_paste";
    public static final String DISALLOW_DATA_ROAMING = "no_data_roaming";
    public static final String DISALLOW_DEBUGGING_FEATURES = "no_debugging_features";
    public static final String DISALLOW_FACTORY_RESET = "no_factory_reset";
    public static final String DISALLOW_FUN = "no_fun";
    public static final String DISALLOW_INSTALL_APPS = "no_install_apps";
    public static final String DISALLOW_INSTALL_UNKNOWN_SOURCES = "no_install_unknown_sources";
    public static final String DISALLOW_INSTALL_UNKNOWN_SOURCES_GLOBALLY = "no_install_unknown_sources_globally";
    public static final String DISALLOW_MICROPHONE_TOGGLE = "disallow_microphone_toggle";
    public static final String DISALLOW_MODIFY_ACCOUNTS = "no_modify_accounts";
    public static final String DISALLOW_MOUNT_PHYSICAL_MEDIA = "no_physical_media";
    public static final String DISALLOW_NETWORK_RESET = "no_network_reset";
    @SystemApi
    @Deprecated
    public static final String DISALLOW_OEM_UNLOCK = "no_oem_unlock";
    public static final String DISALLOW_OUTGOING_BEAM = "no_outgoing_beam";
    public static final String DISALLOW_OUTGOING_CALLS = "no_outgoing_calls";
    public static final String DISALLOW_PRINTING = "no_printing";
    public static final String DISALLOW_RECORD_AUDIO = "no_record_audio";
    @Deprecated
    public static final String DISALLOW_REMOVE_MANAGED_PROFILE = "no_remove_managed_profile";
    public static final String DISALLOW_REMOVE_USER = "no_remove_user";
    @SystemApi
    public static final String DISALLOW_RUN_IN_BACKGROUND = "no_run_in_background";
    public static final String DISALLOW_SAFE_BOOT = "no_safe_boot";
    public static final String DISALLOW_SET_USER_ICON = "no_set_user_icon";
    public static final String DISALLOW_SET_WALLPAPER = "no_set_wallpaper";
    public static final String DISALLOW_SHARE_INTO_MANAGED_PROFILE = "no_sharing_into_profile";
    public static final String DISALLOW_SHARE_LOCATION = "no_share_location";
    public static final String DISALLOW_SMS = "no_sms";
    public static final String DISALLOW_SYSTEM_ERROR_DIALOGS = "no_system_error_dialogs";
    public static final String DISALLOW_UNIFIED_PASSWORD = "no_unified_password";
    public static final String DISALLOW_UNINSTALL_APPS = "no_uninstall_apps";
    public static final String DISALLOW_UNMUTE_DEVICE = "disallow_unmute_device";
    public static final String DISALLOW_UNMUTE_MICROPHONE = "no_unmute_microphone";
    public static final String DISALLOW_USB_FILE_TRANSFER = "no_usb_file_transfer";
    public static final String DISALLOW_USER_SWITCH = "no_user_switch";
    public static final String DISALLOW_WALLPAPER = "no_wallpaper";
    public static final String ENSURE_VERIFY_APPS = "ensure_verify_apps";
    public static final String EXTRA_USER_ACCOUNT_NAME = "android.os.extra.USER_ACCOUNT_NAME";
    public static final String EXTRA_USER_ACCOUNT_OPTIONS = "android.os.extra.USER_ACCOUNT_OPTIONS";
    public static final String EXTRA_USER_ACCOUNT_TYPE = "android.os.extra.USER_ACCOUNT_TYPE";
    public static final String EXTRA_USER_NAME = "android.os.extra.USER_NAME";
    public static final String KEY_RESTRICTIONS_PENDING = "restrictions_pending";
    public static final int PIN_VERIFICATION_FAILED_INCORRECT = -3;
    public static final int PIN_VERIFICATION_FAILED_NOT_SET = -2;
    public static final int PIN_VERIFICATION_SUCCESS = -1;
    public static final int QUIET_MODE_DISABLE_DONT_ASK_CREDENTIAL = 2;
    public static final int QUIET_MODE_DISABLE_ONLY_IF_CREDENTIAL_NOT_REQUIRED = 1;
    public static final int REMOVE_RESULT_ALREADY_BEING_REMOVED = 2;
    public static final int REMOVE_RESULT_ERROR = 3;
    public static final int REMOVE_RESULT_REMOVED = 0;
    public static final int REMOVE_RESULT_SET_EPHEMERAL = 1;
    @SystemApi
    public static final int RESTRICTION_NOT_SET = 0;
    @SystemApi
    public static final int RESTRICTION_SOURCE_DEVICE_OWNER = 2;
    @SystemApi
    public static final int RESTRICTION_SOURCE_PROFILE_OWNER = 4;
    @SystemApi
    public static final int RESTRICTION_SOURCE_SYSTEM = 1;
    @SystemApi
    public static final int SWITCHABILITY_STATUS_OK = 0;
    @SystemApi
    public static final int SWITCHABILITY_STATUS_SYSTEM_USER_LOCKED = 4;
    @SystemApi
    public static final int SWITCHABILITY_STATUS_USER_IN_CALL = 1;
    @SystemApi
    public static final int SWITCHABILITY_STATUS_USER_SWITCH_DISALLOWED = 2;
    private static final String TAG = "UserManager";
    public static final int USER_CREATION_FAILED_NOT_PERMITTED = 1;
    public static final int USER_CREATION_FAILED_NO_MORE_USERS = 2;
    public static final int USER_OPERATION_ERROR_CURRENT_USER = 4;
    public static final int USER_OPERATION_ERROR_LOW_STORAGE = 5;
    public static final int USER_OPERATION_ERROR_MANAGED_PROFILE = 2;
    public static final int USER_OPERATION_ERROR_MAX_RUNNING_USERS = 3;
    public static final int USER_OPERATION_ERROR_MAX_USERS = 6;
    public static final int USER_OPERATION_ERROR_UNKNOWN = 1;
    public static final int USER_OPERATION_SUCCESS = 0;
    public static final String USER_TYPE_FULL_DEMO = "android.os.usertype.full.DEMO";
    public static final String USER_TYPE_FULL_GUEST = "android.os.usertype.full.GUEST";
    public static final String USER_TYPE_FULL_RESTRICTED = "android.os.usertype.full.RESTRICTED";
    @SystemApi
    public static final String USER_TYPE_FULL_SECONDARY = "android.os.usertype.full.SECONDARY";
    @SystemApi
    public static final String USER_TYPE_FULL_SYSTEM = "android.os.usertype.full.SYSTEM";
    @SystemApi
    public static final String USER_TYPE_PROFILE_CLONE = "android.os.usertype.profile.CLONE";
    @SystemApi
    public static final String USER_TYPE_PROFILE_MANAGED = "android.os.usertype.profile.MANAGED";
    public static final String USER_TYPE_PROFILE_TEST = "android.os.usertype.profile.TEST";
    @SystemApi
    public static final String USER_TYPE_SYSTEM_HEADLESS = "android.os.usertype.system.HEADLESS";
    private final Context mContext;
    private Boolean mIsManagedProfileCached;
    private Boolean mIsProfileCached;
    private final IUserManager mService;
    private final int mUserId;
    public IUserManagerExt mUserManagerExt = (IUserManagerExt) ExtLoader.type(IUserManagerExt.class).base(this).create();
    private final PropertyInvalidatedCache<Integer, Boolean> mIsUserUnlockedCache = new PropertyInvalidatedCache<Integer, Boolean>(32, CACHE_KEY_IS_USER_UNLOCKED_PROPERTY) { // from class: android.os.UserManager.1
        /* JADX INFO: Access modifiers changed from: protected */
        public Boolean recompute(Integer query) {
            try {
                return Boolean.valueOf(UserManager.this.mService.isUserUnlocked(query.intValue()));
            } catch (RemoteException re) {
                throw re.rethrowFromSystemServer();
            }
        }
    };
    private final PropertyInvalidatedCache<Integer, Boolean> mIsUserUnlockingOrUnlockedCache = new PropertyInvalidatedCache<Integer, Boolean>(32, CACHE_KEY_IS_USER_UNLOCKED_PROPERTY) { // from class: android.os.UserManager.2
        /* JADX INFO: Access modifiers changed from: protected */
        public Boolean recompute(Integer query) {
            try {
                return Boolean.valueOf(UserManager.this.mService.isUserUnlockingOrUnlocked(query.intValue()));
            } catch (RemoteException re) {
                throw re.rethrowFromSystemServer();
            }
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface QuietModeFlag {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface RemoveResult {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface UserOperationResult {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface UserRestrictionKey {
    }

    @SystemApi
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface UserRestrictionSource {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface UserSwitchabilityResult {
    }

    /* loaded from: classes2.dex */
    public static class UserOperationException extends RuntimeException {
        private final int mUserOperationResult;

        public UserOperationException(String message, int userOperationResult) {
            super(message);
            this.mUserOperationResult = userOperationResult;
        }

        public int getUserOperationResult() {
            return this.mUserOperationResult;
        }

        public static UserOperationException from(ServiceSpecificException exception) {
            return new UserOperationException(exception.getMessage(), exception.errorCode);
        }
    }

    private <T> T returnNullOrThrowUserOperationException(ServiceSpecificException exception, boolean throwInsteadOfNull) throws UserOperationException {
        if (!throwInsteadOfNull) {
            return null;
        }
        throw UserOperationException.from(exception);
    }

    /* loaded from: classes2.dex */
    public static class CheckedUserOperationException extends AndroidException {
        private final int mUserOperationResult;

        public CheckedUserOperationException(String message, int userOperationResult) {
            super(message);
            this.mUserOperationResult = userOperationResult;
        }

        public int getUserOperationResult() {
            return this.mUserOperationResult;
        }

        public ServiceSpecificException toServiceSpecificException() {
            return new ServiceSpecificException(this.mUserOperationResult, getMessage());
        }
    }

    public static UserManager get(Context context) {
        return (UserManager) context.getSystemService("user");
    }

    public UserManager(Context context, IUserManager service) {
        this.mService = service;
        this.mContext = context.getApplicationContext();
        this.mUserId = context.getUserId();
    }

    public static boolean supportsMultipleUsers() {
        return getMaxSupportedUsers() > 1 && SystemProperties.getBoolean("fw.show_multiuserui", Resources.getSystem().getBoolean(R.bool.config_enableMultiUserUI));
    }

    public static boolean isSplitSystemUser() {
        return RoSystemProperties.FW_SYSTEM_USER_SPLIT;
    }

    public static boolean isGuestUserEphemeral() {
        return Resources.getSystem().getBoolean(R.bool.config_guestUserEphemeral);
    }

    public static boolean isHeadlessSystemUserMode() {
        return RoSystemProperties.MULTIUSER_HEADLESS_SYSTEM_USER;
    }

    @Deprecated
    public boolean canSwitchUsers() {
        boolean allowUserSwitchingWhenSystemUserLocked = Settings.Global.getInt(this.mContext.getContentResolver(), Settings.Global.ALLOW_USER_SWITCHING_WHEN_SYSTEM_USER_LOCKED, 0) != 0;
        boolean isSystemUserUnlocked = isUserUnlocked(UserHandle.SYSTEM);
        boolean inCall = false;
        TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService(TelephonyManager.class);
        if (telephonyManager != null) {
            inCall = telephonyManager.getCallState() != 0;
        }
        boolean isUserSwitchDisallowed = hasUserRestriction(DISALLOW_USER_SWITCH);
        return (allowUserSwitchingWhenSystemUserLocked || isSystemUserUnlocked) && !inCall && !isUserSwitchDisallowed;
    }

    @SystemApi
    public int getUserSwitchability() {
        return getUserSwitchability(Process.myUserHandle());
    }

    public int getUserSwitchability(UserHandle userHandle) {
        TelephonyManager tm = (TelephonyManager) this.mContext.getSystemService("phone");
        int flags = 0;
        if (tm.getCallState() != 0) {
            flags = 0 | 1;
        }
        if (hasUserRestriction(DISALLOW_USER_SWITCH, userHandle)) {
            flags |= 2;
        }
        if (isHeadlessSystemUserMode()) {
            return flags;
        }
        boolean allowUserSwitchingWhenSystemUserLocked = false;
        if (Settings.Global.getInt(this.mContext.getContentResolver(), Settings.Global.ALLOW_USER_SWITCHING_WHEN_SYSTEM_USER_LOCKED, 0) != 0) {
            allowUserSwitchingWhenSystemUserLocked = true;
        }
        boolean systemUserUnlocked = isUserUnlocked(UserHandle.SYSTEM);
        if (allowUserSwitchingWhenSystemUserLocked || systemUserUnlocked) {
            return flags;
        }
        return flags | 4;
    }

    public int getUserHandle() {
        return UserHandle.myUserId();
    }

    public String getUserType() {
        UserInfo userInfo = getUserInfo(this.mUserId);
        return userInfo == null ? "" : userInfo.userType;
    }

    public String getUserName() {
        int myUserId = UserHandle.myUserId();
        int i = this.mUserId;
        if (myUserId == i) {
            try {
                return this.mService.getUserName();
            } catch (RemoteException re) {
                throw re.rethrowFromSystemServer();
            }
        } else {
            UserInfo userInfo = getUserInfo(i);
            return userInfo == null ? "" : userInfo.name;
        }
    }

    @SystemApi
    public boolean isUserNameSet() {
        try {
            return this.mService.isUserNameSet(getUserHandle());
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public boolean isUserAGoat() {
        if (this.mContext.getApplicationInfo().targetSdkVersion >= 30) {
            return false;
        }
        return this.mContext.getPackageManager().isPackageAvailable("com.coffeestainstudios.goatsimulator");
    }

    @SystemApi
    public boolean isPrimaryUser() {
        UserInfo user = getUserInfo(UserHandle.myUserId());
        return user != null && user.isPrimary();
    }

    public boolean isSystemUser() {
        return UserHandle.myUserId() == 0;
    }

    @SystemApi
    public boolean isAdminUser() {
        return isUserAdmin(UserHandle.myUserId());
    }

    public boolean isUserAdmin(int userId) {
        UserInfo user = getUserInfo(userId);
        return user != null && user.isAdmin();
    }

    @SystemApi
    public boolean isUserOfType(String userType) {
        try {
            return this.mService.isUserOfType(this.mUserId, userType);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public static boolean isUserTypeManagedProfile(String userType) {
        return USER_TYPE_PROFILE_MANAGED.equals(userType);
    }

    public static boolean isUserTypeGuest(String userType) {
        return USER_TYPE_FULL_GUEST.equals(userType);
    }

    public static boolean isUserTypeRestricted(String userType) {
        return USER_TYPE_FULL_RESTRICTED.equals(userType);
    }

    public static boolean isUserTypeDemo(String userType) {
        return USER_TYPE_FULL_DEMO.equals(userType);
    }

    public static boolean isUserTypeCloneProfile(String userType) {
        return USER_TYPE_PROFILE_CLONE.equals(userType);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int getUserTypeForStatsd(String userType) {
        char c;
        switch (userType.hashCode()) {
            case -1103927049:
                if (userType.equals(USER_TYPE_FULL_GUEST)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -159818852:
                if (userType.equals(USER_TYPE_PROFILE_MANAGED)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 34001850:
                if (userType.equals(USER_TYPE_SYSTEM_HEADLESS)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 485661392:
                if (userType.equals(USER_TYPE_FULL_SYSTEM)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 942013715:
                if (userType.equals(USER_TYPE_FULL_SECONDARY)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1711075452:
                if (userType.equals(USER_TYPE_FULL_RESTRICTED)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1765400260:
                if (userType.equals(USER_TYPE_FULL_DEMO)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1966344346:
                if (userType.equals(USER_TYPE_PROFILE_CLONE)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
            case 6:
                return 7;
            case 7:
                return 8;
            default:
                return 0;
        }
    }

    @Deprecated
    public boolean isLinkedUser() {
        return isRestrictedProfile();
    }

    @SystemApi
    public boolean isRestrictedProfile() {
        try {
            return this.mService.isRestricted();
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean isRestrictedProfile(UserHandle user) {
        try {
            return this.mService.getUserInfo(user.getIdentifier()).isRestricted();
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean canHaveRestrictedProfile() {
        try {
            return this.mService.canHaveRestrictedProfile(this.mUserId);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean hasRestrictedProfiles() {
        try {
            return this.mService.hasRestrictedProfiles();
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public UserHandle getRestrictedProfileParent() {
        int parent;
        UserInfo info = getUserInfo(this.mUserId);
        if (info == null || !info.isRestricted() || (parent = info.restrictedProfileParentId) == -10000) {
            return null;
        }
        return UserHandle.of(parent);
    }

    public boolean isGuestUser(int userId) {
        UserInfo user = getUserInfo(userId);
        return user != null && user.isGuest();
    }

    @SystemApi
    public boolean isGuestUser() {
        UserInfo user = getUserInfo(UserHandle.myUserId());
        return user != null && user.isGuest();
    }

    public boolean isDemoUser() {
        try {
            return this.mService.isDemoUser(UserHandle.myUserId());
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean isProfile() {
        return isProfile(this.mUserId);
    }

    private boolean isProfile(int userId) {
        if (userId == UserHandle.myUserId()) {
            Boolean bool = this.mIsProfileCached;
            if (bool != null) {
                return bool.booleanValue();
            }
            try {
                Boolean valueOf = Boolean.valueOf(this.mService.isProfile(userId));
                this.mIsProfileCached = valueOf;
                return valueOf.booleanValue();
            } catch (RemoteException re) {
                throw re.rethrowFromSystemServer();
            }
        } else {
            try {
                return this.mService.isProfile(userId);
            } catch (RemoteException re2) {
                throw re2.rethrowFromSystemServer();
            }
        }
    }

    public boolean isManagedProfile() {
        Boolean bool = this.mIsManagedProfileCached;
        if (bool != null) {
            return bool.booleanValue();
        }
        try {
            Boolean valueOf = Boolean.valueOf(this.mService.isManagedProfile(UserHandle.myUserId()));
            this.mIsManagedProfileCached = valueOf;
            return valueOf.booleanValue();
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean isManagedProfile(int userId) {
        if (userId == UserHandle.myUserId()) {
            return isManagedProfile();
        }
        try {
            return this.mService.isManagedProfile(userId);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean isCloneProfile() {
        try {
            return this.mService.isCloneProfile(this.mUserId);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public boolean isEphemeralUser() {
        return isUserEphemeral(UserHandle.myUserId());
    }

    public boolean isUserEphemeral(int userId) {
        UserInfo user = getUserInfo(userId);
        return user != null && user.isEphemeral();
    }

    public boolean isUserRunning(UserHandle user) {
        return isUserRunning(user.getIdentifier());
    }

    public boolean isUserRunning(int userId) {
        try {
            return this.mService.isUserRunning(userId);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public boolean isUserRunningOrStopping(UserHandle user) {
        try {
            return ActivityManager.getService().isUserRunning(user.getIdentifier(), 1);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public boolean isUserForeground() {
        try {
            return this.mService.isUserForeground(this.mUserId);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public boolean isUserUnlocked() {
        return isUserUnlocked(Process.myUserHandle());
    }

    public boolean isUserUnlocked(UserHandle user) {
        return isUserUnlocked(user.getIdentifier());
    }

    public boolean isUserUnlocked(int userId) {
        return this.mIsUserUnlockedCache.query(Integer.valueOf(userId)).booleanValue();
    }

    public void disableIsUserUnlockedCache() {
        this.mIsUserUnlockedCache.disableLocal();
        this.mIsUserUnlockingOrUnlockedCache.disableLocal();
    }

    public static final void invalidateIsUserUnlockedCache() {
        PropertyInvalidatedCache.invalidateCache(CACHE_KEY_IS_USER_UNLOCKED_PROPERTY);
    }

    @SystemApi
    public boolean isUserUnlockingOrUnlocked(UserHandle user) {
        return isUserUnlockingOrUnlocked(user.getIdentifier());
    }

    public boolean isUserUnlockingOrUnlocked(int userId) {
        return this.mIsUserUnlockingOrUnlockedCache.query(Integer.valueOf(userId)).booleanValue();
    }

    public long getUserStartRealtime() {
        try {
            return this.mService.getUserStartRealtime();
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public long getUserUnlockRealtime() {
        try {
            return this.mService.getUserUnlockRealtime();
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public UserInfo getUserInfo(int userId) {
        try {
            return this.mService.getUserInfo(userId);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    @SystemApi
    @Deprecated
    public int getUserRestrictionSource(String restrictionKey, UserHandle userHandle) {
        try {
            return this.mService.getUserRestrictionSource(restrictionKey, userHandle.getIdentifier());
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public List<EnforcingUser> getUserRestrictionSources(String restrictionKey, UserHandle userHandle) {
        try {
            return this.mService.getUserRestrictionSources(restrictionKey, userHandle.getIdentifier());
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public Bundle getUserRestrictions() {
        return getUserRestrictions(Process.myUserHandle());
    }

    public Bundle getUserRestrictions(UserHandle userHandle) {
        try {
            return this.mService.getUserRestrictions(userHandle.getIdentifier());
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public boolean hasBaseUserRestriction(String restrictionKey, UserHandle userHandle) {
        try {
            return this.mService.hasBaseUserRestriction(restrictionKey, userHandle.getIdentifier());
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public void setUserRestrictions(Bundle restrictions) {
        throw new UnsupportedOperationException("This method is no longer supported");
    }

    @Deprecated
    public void setUserRestrictions(Bundle restrictions, UserHandle userHandle) {
        throw new UnsupportedOperationException("This method is no longer supported");
    }

    @Deprecated
    public void setUserRestriction(String key, boolean value) {
        setUserRestriction(key, value, Process.myUserHandle());
    }

    @Deprecated
    public void setUserRestriction(String key, boolean value, UserHandle userHandle) {
        try {
            this.mService.setUserRestriction(key, value, userHandle.getIdentifier());
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public boolean hasUserRestriction(String restrictionKey) {
        return hasUserRestrictionForUser(restrictionKey, Process.myUserHandle());
    }

    public boolean hasUserRestriction(String restrictionKey, UserHandle userHandle) {
        return hasUserRestrictionForUser(restrictionKey, userHandle);
    }

    @SystemApi
    public boolean hasUserRestrictionForUser(String restrictionKey, UserHandle userHandle) {
        try {
            return this.mService.hasUserRestriction(restrictionKey, userHandle.getIdentifier());
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public boolean hasUserRestrictionOnAnyUser(String restrictionKey) {
        try {
            return this.mService.hasUserRestrictionOnAnyUser(restrictionKey);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public boolean isSettingRestrictedForUser(String setting, int userId, String value, int callingUid) {
        try {
            return this.mService.isSettingRestrictedForUser(setting, userId, value, callingUid);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public void addUserRestrictionsListener(IUserRestrictionsListener listener) {
        try {
            this.mService.addUserRestrictionsListener(listener);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public long getSerialNumberForUser(UserHandle user) {
        return getUserSerialNumber(user.getIdentifier());
    }

    public UserHandle getUserForSerialNumber(long serialNumber) {
        int ident = getUserHandle((int) serialNumber);
        if (ident >= 0) {
            return new UserHandle(ident);
        }
        return null;
    }

    @Deprecated
    public UserInfo createUser(String name, int flags) {
        return createUser(name, UserInfo.getDefaultUserType(flags), flags);
    }

    public UserInfo createUser(String name, String userType, int flags) {
        try {
            return this.mService.createUserWithThrow(name, userType, flags);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        } catch (ServiceSpecificException e) {
            return null;
        }
    }

    public UserInfo preCreateUser(String userType) throws UserOperationException {
        try {
            return this.mService.preCreateUserWithThrow(userType);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        } catch (ServiceSpecificException e) {
            throw UserOperationException.from(e);
        }
    }

    public UserInfo createGuest(Context context, String name) {
        try {
            UserInfo guest = this.mService.createUserWithThrow(name, USER_TYPE_FULL_GUEST, 0);
            if (guest != null) {
                Settings.Secure.putStringForUser(context.getContentResolver(), Settings.Secure.SKIP_FIRST_USE_HINTS, IOplusUIFirstManager.APP_START_ANIMATION, guest.id);
            }
            return guest;
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        } catch (ServiceSpecificException e) {
            return null;
        }
    }

    public UserInfo findCurrentGuestUser() {
        try {
            return this.mService.findCurrentGuestUser();
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public UserHandle createProfile(String name, String userType, Set<String> disallowedPackages) throws UserOperationException {
        try {
            return this.mService.createProfileForUserWithThrow(name, userType, 0, this.mUserId, (String[]) disallowedPackages.toArray(new String[disallowedPackages.size()])).getUserHandle();
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        } catch (ServiceSpecificException e) {
            return (UserHandle) returnNullOrThrowUserOperationException(e, this.mContext.getApplicationInfo().targetSdkVersion >= 30);
        }
    }

    @Deprecated
    public UserInfo createProfileForUser(String name, int flags, int userId) {
        return createProfileForUser(name, UserInfo.getDefaultUserType(flags), flags, userId, null);
    }

    public UserInfo createProfileForUser(String name, String userType, int flags, int userId) {
        return createProfileForUser(name, userType, flags, userId, null);
    }

    public UserInfo createProfileForUser(String name, String userType, int flags, int userId, String[] disallowedPackages) {
        try {
            return this.mService.createProfileForUserWithThrow(name, userType, flags, userId, disallowedPackages);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        } catch (ServiceSpecificException e) {
            return null;
        }
    }

    public UserInfo createProfileForUserEvenWhenDisallowed(String name, String userType, int flags, int userId, String[] disallowedPackages) {
        try {
            return this.mService.createProfileForUserEvenWhenDisallowedWithThrow(name, userType, flags, userId, disallowedPackages);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        } catch (ServiceSpecificException e) {
            return null;
        }
    }

    public UserInfo createRestrictedProfile(String name) {
        try {
            UserHandle parentUserHandle = Process.myUserHandle();
            UserInfo user = this.mService.createRestrictedProfileWithThrow(name, parentUserHandle.getIdentifier());
            if (user != null) {
                AccountManager.get(this.mContext).addSharedAccountsFromParentUser(parentUserHandle, UserHandle.of(user.id));
            }
            return user;
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        } catch (ServiceSpecificException e) {
            return null;
        }
    }

    public static Intent createUserCreationIntent(String userName, String accountName, String accountType, PersistableBundle accountOptions) {
        Intent intent = new Intent(ACTION_CREATE_USER);
        if (userName != null) {
            intent.putExtra(EXTRA_USER_NAME, userName);
        }
        if (accountName == null || accountType != null) {
            if (accountName != null) {
                intent.putExtra(EXTRA_USER_ACCOUNT_NAME, accountName);
            }
            if (accountType != null) {
                intent.putExtra(EXTRA_USER_ACCOUNT_TYPE, accountType);
            }
            if (accountOptions != null) {
                intent.putExtra(EXTRA_USER_ACCOUNT_OPTIONS, accountOptions);
            }
            return intent;
        }
        throw new IllegalArgumentException("accountType must be specified if accountName is specified");
    }

    public Set<String> getPreInstallableSystemPackages(String userType) {
        try {
            String[] installableSystemPackages = this.mService.getPreInstallableSystemPackages(userType);
            if (installableSystemPackages == null) {
                return null;
            }
            return new ArraySet(installableSystemPackages);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public String getSeedAccountName() {
        try {
            return this.mService.getSeedAccountName();
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public String getSeedAccountType() {
        try {
            return this.mService.getSeedAccountType();
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public PersistableBundle getSeedAccountOptions() {
        try {
            return this.mService.getSeedAccountOptions();
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public void setSeedAccountData(int userId, String accountName, String accountType, PersistableBundle accountOptions) {
        try {
            this.mService.setSeedAccountData(userId, accountName, accountType, accountOptions, true);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void clearSeedAccountData() {
        try {
            this.mService.clearSeedAccountData();
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public boolean markGuestForDeletion(int userId) {
        try {
            return this.mService.markGuestForDeletion(userId);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public void setUserEnabled(int userId) {
        try {
            this.mService.setUserEnabled(userId);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public void setUserAdmin(int userId) {
        try {
            this.mService.setUserAdmin(userId);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public void evictCredentialEncryptionKey(int userId) {
        try {
            this.mService.evictCredentialEncryptionKey(userId);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public int getUserCount() {
        List<UserInfo> users = getUsers();
        if (users != null) {
            return users.size();
        }
        return 1;
    }

    public List<UserInfo> getUsers() {
        return getUsers(true, false, true);
    }

    public List<UserInfo> getAliveUsers() {
        return getUsers(true, true, true);
    }

    @Deprecated
    public List<UserInfo> getUsers(boolean excludeDying) {
        return getUsers(true, excludeDying, true);
    }

    public List<UserInfo> getUsers(boolean excludePartial, boolean excludeDying, boolean excludePreCreated) {
        try {
            return this.mService.getUsers(excludePartial, excludeDying, excludePreCreated);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public List<UserHandle> getUserHandles(boolean excludeDying) {
        List<UserInfo> users = getUsers(true, excludeDying, true);
        List<UserHandle> result = new ArrayList<>(users.size());
        for (UserInfo user : users) {
            result.add(user.getUserHandle());
        }
        return result;
    }

    @SystemApi
    public long[] getSerialNumbersOfUsers(boolean excludeDying) {
        List<UserInfo> users = getUsers(true, excludeDying, true);
        long[] result = new long[users.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = users.get(i).serialNumber;
        }
        return result;
    }

    public String getUserAccount(int userId) {
        try {
            return this.mService.getUserAccount(userId);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public void setUserAccount(int userId, String accountName) {
        try {
            this.mService.setUserAccount(userId, accountName);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public UserInfo getPrimaryUser() {
        try {
            return this.mService.getPrimaryUser();
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public boolean canAddMoreUsers() {
        List<UserInfo> users = getAliveUsers();
        int totalUserCount = users.size();
        int aliveUserCount = 0;
        for (int i = 0; i < totalUserCount; i++) {
            UserInfo user = users.get(i);
            if (!this.mUserManagerExt.isMultiAppUserId(user.id) && !user.isGuest()) {
                aliveUserCount++;
            }
        }
        int i2 = getMaxSupportedUsers();
        return aliveUserCount < i2;
    }

    public boolean canAddMoreManagedProfiles(int userId, boolean allowedToRemoveOne) {
        try {
            return this.mService.canAddMoreManagedProfiles(userId, allowedToRemoveOne);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public boolean canAddMoreProfilesToUser(String userType, int userId) {
        try {
            return this.mService.canAddMoreProfilesToUser(userType, userId, false);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public List<UserInfo> getProfiles(int userId) {
        try {
            return this.mService.getProfiles(userId, false);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean isSameProfileGroup(UserHandle user, UserHandle otherUser) {
        return isSameProfileGroup(user.getIdentifier(), otherUser.getIdentifier());
    }

    public boolean isSameProfileGroup(int userId, int otherUserId) {
        try {
            return this.mService.isSameProfileGroup(userId, otherUserId);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public List<UserInfo> getEnabledProfiles(int userId) {
        try {
            return this.mService.getProfiles(userId, true);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public List<UserHandle> getUserProfiles() {
        int[] userIds = getProfileIds(UserHandle.myUserId(), true);
        List<UserHandle> result = new ArrayList<>(userIds.length);
        for (int userId : userIds) {
            result.add(UserHandle.of(userId));
        }
        return result;
    }

    @SystemApi
    public List<UserHandle> getEnabledProfiles() {
        return getProfiles(true);
    }

    @SystemApi
    public List<UserHandle> getAllProfiles() {
        return getProfiles(false);
    }

    private List<UserHandle> getProfiles(boolean enabledOnly) {
        int[] userIds = getProfileIds(this.mUserId, enabledOnly);
        List<UserHandle> result = new ArrayList<>(userIds.length);
        for (int userId : userIds) {
            result.add(UserHandle.of(userId));
        }
        return result;
    }

    public int[] getProfileIds(int userId, boolean enabledOnly) {
        try {
            return this.mService.getProfileIds(userId, enabledOnly);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public int[] getProfileIdsWithDisabled(int userId) {
        return getProfileIds(userId, false);
    }

    public int[] getEnabledProfileIds(int userId) {
        return getProfileIds(userId, true);
    }

    public int getCredentialOwnerProfile(int userId) {
        try {
            return this.mService.getCredentialOwnerProfile(userId);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public UserInfo getProfileParent(int userId) {
        try {
            return this.mService.getProfileParent(userId);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public UserHandle getProfileParent(UserHandle user) {
        UserInfo info = getProfileParent(user.getIdentifier());
        if (info == null) {
            return null;
        }
        return UserHandle.of(info.id);
    }

    public boolean requestQuietModeEnabled(boolean enableQuietMode, UserHandle userHandle) {
        return requestQuietModeEnabled(enableQuietMode, userHandle, (IntentSender) null);
    }

    public boolean requestQuietModeEnabled(boolean enableQuietMode, UserHandle userHandle, int flags) {
        return requestQuietModeEnabled(enableQuietMode, userHandle, null, flags);
    }

    public boolean requestQuietModeEnabled(boolean enableQuietMode, UserHandle userHandle, IntentSender target) {
        return requestQuietModeEnabled(enableQuietMode, userHandle, target, 0);
    }

    public boolean requestQuietModeEnabled(boolean enableQuietMode, UserHandle userHandle, IntentSender target, int flags) {
        try {
            return this.mService.requestQuietModeEnabled(this.mContext.getPackageName(), enableQuietMode, userHandle.getIdentifier(), target, flags);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public boolean isQuietModeEnabled(UserHandle userHandle) {
        try {
            return this.mService.isQuietModeEnabled(userHandle.getIdentifier());
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public boolean hasBadge(int userId) {
        if (!isProfile(userId)) {
            return false;
        }
        try {
            return this.mService.hasBadge(userId);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public boolean hasBadge() {
        return hasBadge(this.mUserId);
    }

    public int getUserBadgeColor(int userId) {
        try {
            int resourceId = this.mService.getUserBadgeColorResId(userId);
            return Resources.getSystem().getColor(resourceId, null);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public int getUserBadgeDarkColor(int userId) {
        try {
            int resourceId = this.mService.getUserBadgeDarkColorResId(userId);
            return Resources.getSystem().getColor(resourceId, null);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public int getUserIconBadgeResId(int userId) {
        try {
            return this.mService.getUserIconBadgeResId(userId);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public int getUserBadgeResId(int userId) {
        try {
            return this.mService.getUserBadgeResId(userId);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public int getUserBadgeNoBackgroundResId(int userId) {
        try {
            return this.mService.getUserBadgeNoBackgroundResId(userId);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public Drawable getBadgedIconForUser(Drawable icon, UserHandle user) {
        return this.mContext.getPackageManager().getUserBadgedIcon(icon, user);
    }

    public Drawable getBadgedDrawableForUser(Drawable badgedDrawable, UserHandle user, Rect badgeLocation, int badgeDensity) {
        return this.mContext.getPackageManager().getUserBadgedDrawableForDensity(badgedDrawable, user, badgeLocation, badgeDensity);
    }

    public CharSequence getBadgedLabelForUser(CharSequence label, UserHandle user) {
        int userId = user.getIdentifier();
        if (!hasBadge(userId)) {
            return label;
        }
        try {
            int resourceId = this.mService.getUserBadgeLabelResId(userId);
            return Resources.getSystem().getString(resourceId, label);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean isMediaSharedWithParent() {
        try {
            return this.mService.isMediaSharedWithParent(this.mUserId);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public boolean removeUser(int userId) {
        try {
            return this.mService.removeUser(userId);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean removeUser(UserHandle user) {
        if (user != null) {
            return removeUser(user.getIdentifier());
        }
        throw new IllegalArgumentException("user cannot be null");
    }

    public boolean removeUserEvenWhenDisallowed(int userId) {
        try {
            return this.mService.removeUserEvenWhenDisallowed(userId);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public int removeUserOrSetEphemeral(int userId, boolean evenWhenDisallowed) {
        try {
            return this.mService.removeUserOrSetEphemeral(userId, evenWhenDisallowed);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public void setUserName(int userId, String name) {
        try {
            this.mService.setUserName(userId, name);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void setUserName(String name) {
        setUserName(this.mUserId, name);
    }

    public void setUserIcon(int userId, Bitmap icon) {
        try {
            this.mService.setUserIcon(userId, icon);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        } catch (ServiceSpecificException e) {
        }
    }

    @SystemApi
    public void setUserIcon(Bitmap icon) throws UserOperationException {
        setUserIcon(this.mUserId, icon);
    }

    public Bitmap getUserIcon(int userId) {
        try {
            ParcelFileDescriptor fd = this.mService.getUserIcon(userId);
            if (fd == null) {
                return null;
            }
            Bitmap decodeFileDescriptor = BitmapFactory.decodeFileDescriptor(fd.getFileDescriptor());
            try {
                fd.close();
            } catch (IOException e) {
            }
            return decodeFileDescriptor;
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public Bitmap getUserIcon() {
        return getUserIcon(this.mUserId);
    }

    public static int getMaxSupportedUsers() {
        if (Build.ID.startsWith("JVP")) {
            return 1;
        }
        return SystemProperties.getInt("fw.max_users", Resources.getSystem().getInteger(R.integer.config_multiuserMaximumUsers));
    }

    public boolean isUserSwitcherEnabled() {
        return isUserSwitcherEnabled(true);
    }

    public boolean isUserSwitcherEnabled(boolean showEvenIfNotActionable) {
        if (!supportsMultipleUsers() || hasUserRestriction(DISALLOW_USER_SWITCH) || isDeviceInDemoMode(this.mContext)) {
            return false;
        }
        boolean userSwitcherSettingOn = Settings.Global.getInt(this.mContext.getContentResolver(), Settings.Global.USER_SWITCHER_ENABLED, Resources.getSystem().getBoolean(R.bool.config_showUserSwitcherByDefault) ? 1 : 0) != 0;
        if (!userSwitcherSettingOn) {
            return false;
        }
        return showEvenIfNotActionable || areThereUsersToWhichToSwitch() || !hasUserRestriction(DISALLOW_ADD_USER);
    }

    private boolean areThereUsersToWhichToSwitch() {
        List<UserInfo> users = getAliveUsers();
        if (users == null) {
            return false;
        }
        int switchableUserCount = 0;
        for (UserInfo user : users) {
            if (user.supportsSwitchToByUser()) {
                switchableUserCount++;
            }
        }
        if (switchableUserCount > 1) {
            return true;
        }
        return false;
    }

    public static boolean isDeviceInDemoMode(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), Settings.Global.DEVICE_DEMO_MODE, 0) > 0;
    }

    public int getUserSerialNumber(int userId) {
        try {
            return this.mService.getUserSerialNumber(userId);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public int getUserHandle(int userSerialNumber) {
        try {
            return this.mService.getUserHandle(userSerialNumber);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public Bundle getApplicationRestrictions(String packageName) {
        try {
            return this.mService.getApplicationRestrictions(packageName);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public Bundle getApplicationRestrictions(String packageName, UserHandle user) {
        try {
            return this.mService.getApplicationRestrictionsForUser(packageName, user.getIdentifier());
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public void setApplicationRestrictions(String packageName, Bundle restrictions, UserHandle user) {
        try {
            this.mService.setApplicationRestrictions(packageName, restrictions, user.getIdentifier());
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public boolean setRestrictionsChallenge(String newPin) {
        return false;
    }

    public void setDefaultGuestRestrictions(Bundle restrictions) {
        try {
            this.mService.setDefaultGuestRestrictions(restrictions);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public Bundle getDefaultGuestRestrictions() {
        try {
            return this.mService.getDefaultGuestRestrictions();
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public long getUserCreationTime(UserHandle userHandle) {
        try {
            return this.mService.getUserCreationTime(userHandle.getIdentifier());
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    public boolean someUserHasSeedAccount(String accountName, String accountType) {
        try {
            return this.mService.someUserHasSeedAccount(accountName, accountType);
        } catch (RemoteException re) {
            throw re.rethrowFromSystemServer();
        }
    }

    @SystemApi
    /* loaded from: classes2.dex */
    public static final class EnforcingUser implements Parcelable {
        public static final Parcelable.Creator<EnforcingUser> CREATOR = new Parcelable.Creator<EnforcingUser>() { // from class: android.os.UserManager.EnforcingUser.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public EnforcingUser createFromParcel(Parcel in) {
                return new EnforcingUser(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public EnforcingUser[] newArray(int size) {
                return new EnforcingUser[size];
            }
        };
        private final int userId;
        private final int userRestrictionSource;

        public EnforcingUser(int userId, int userRestrictionSource) {
            this.userId = userId;
            this.userRestrictionSource = userRestrictionSource;
        }

        private EnforcingUser(Parcel in) {
            this.userId = in.readInt();
            this.userRestrictionSource = in.readInt();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.userId);
            dest.writeInt(this.userRestrictionSource);
        }

        public UserHandle getUserHandle() {
            return UserHandle.of(this.userId);
        }

        public int getUserRestrictionSource() {
            return this.userRestrictionSource;
        }
    }
}
