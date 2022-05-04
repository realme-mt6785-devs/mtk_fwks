package com.android.internal.widget;

import android.app.admin.DevicePolicyManager;
import android.app.admin.PasswordMetrics;
import android.app.trust.IStrongAuthTracker;
import android.app.trust.TrustManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.UserInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.UserManager;
import android.os.storage.IStorageManager;
import android.os.storage.StorageManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import com.android.internal.R;
import com.android.internal.widget.ICheckCredentialProgressCallback;
import com.android.internal.widget.ILockSettings;
import com.android.internal.widget.LockPatternUtils;
import com.android.internal.widget.LockPatternView;
import com.android.server.LocalServices;
import com.google.android.collect.Lists;
import com.oplus.uifirst.IOplusUIFirstManager;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import system.ext.loader.core.ExtLoader;
/* loaded from: classes4.dex */
public class LockPatternUtils {
    @Deprecated
    public static final String BIOMETRIC_WEAK_EVER_CHOSEN_KEY = "lockscreen.biometricweakeverchosen";
    public static final int CREDENTIAL_TYPE_NONE = -1;
    public static final int CREDENTIAL_TYPE_PASSWORD = 4;
    public static final int CREDENTIAL_TYPE_PASSWORD_OR_PIN = 2;
    public static final int CREDENTIAL_TYPE_PATTERN = 1;
    public static final int CREDENTIAL_TYPE_PIN = 3;
    private static final boolean DEBUG = SystemProperties.getBoolean("persist.sys.assert.panic", false);
    public static final String DISABLE_LOCKSCREEN_KEY = "lockscreen.disabled";
    private static final String ENABLED_TRUST_AGENTS = "lockscreen.enabledtrustagents";
    public static final int FAILED_ATTEMPTS_BEFORE_WIPE_GRACE = 5;
    public static final long FAILED_ATTEMPT_COUNTDOWN_INTERVAL_MS = 1000;
    private static final boolean FRP_CREDENTIAL_ENABLED = true;
    private static final String IS_TRUST_USUALLY_MANAGED = "lockscreen.istrustusuallymanaged";
    public static final String LEGACY_LOCK_PATTERN_ENABLED = "legacy_lock_pattern_enabled";
    @Deprecated
    public static final String LOCKOUT_PERMANENT_KEY = "lockscreen.lockedoutpermanently";
    @Deprecated
    public static final String LOCKSCREEN_BIOMETRIC_WEAK_FALLBACK = "lockscreen.biometric_weak_fallback";
    public static final String LOCKSCREEN_OPTIONS = "lockscreen.options";
    public static final String LOCKSCREEN_POWER_BUTTON_INSTANTLY_LOCKS = "lockscreen.power_button_instantly_locks";
    @Deprecated
    public static final String LOCKSCREEN_WIDGETS_ENABLED = "lockscreen.widgets_enabled";
    public static final String LOCK_PASSWORD_SALT_KEY = "lockscreen.password_salt";
    private static final String LOCK_SCREEN_DEVICE_OWNER_INFO = "lockscreen.device_owner_info";
    private static final String LOCK_SCREEN_OWNER_INFO = "lock_screen_owner_info";
    private static final String LOCK_SCREEN_OWNER_INFO_ENABLED = "lock_screen_owner_info_enabled";
    public static final int MIN_LOCK_PASSWORD_SIZE = 4;
    public static final int MIN_LOCK_PATTERN_SIZE = 4;
    public static final int MIN_PATTERN_REGISTER_FAIL = 4;
    public static final String PASSWORD_HISTORY_DELIMITER = ",";
    public static final String PASSWORD_HISTORY_KEY = "lockscreen.passwordhistory";
    @Deprecated
    public static final String PASSWORD_TYPE_ALTERNATE_KEY = "lockscreen.password_type_alternate";
    public static final String PASSWORD_TYPE_KEY = "lockscreen.password_type";
    public static final String PATTERN_EVER_CHOSEN_KEY = "lockscreen.patterneverchosen";
    public static final String PROFILE_KEY_NAME_DECRYPT = "profile_key_name_decrypt_";
    public static final String PROFILE_KEY_NAME_ENCRYPT = "profile_key_name_encrypt_";
    public static final int SYNTHETIC_PASSWORD_ENABLED_BY_DEFAULT = 1;
    public static final String SYNTHETIC_PASSWORD_ENABLED_KEY = "enable-sp";
    public static final String SYNTHETIC_PASSWORD_HANDLE_KEY = "sp-handle";
    public static final String SYNTHETIC_PASSWORD_KEY_PREFIX = "synthetic_password_";
    private static final String TAG = "LockPatternUtils";
    public static final int USER_FRP = -9999;
    public static final int VERIFY_FLAG_REQUEST_GK_PW_HANDLE = 1;
    private final ContentResolver mContentResolver;
    private final Context mContext;
    private DevicePolicyManager mDevicePolicyManager;
    private final Handler mHandler;
    private Boolean mHasSecureLockScreen;
    private ILockSettings mLockSettingsService;
    private UserManager mUserManager;
    public ILockPatternUtilsExt mLockPatternUtilsExt = (ILockPatternUtilsExt) ExtLoader.type(ILockPatternUtilsExt.class).base(this).create();
    private final SparseLongArray mLockoutDeadlines = new SparseLongArray();

    /* loaded from: classes4.dex */
    public interface CheckCredentialProgressCallback {
        void onEarlyMatched();
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    public @interface CredentialType {
    }

    /* loaded from: classes4.dex */
    public interface EscrowTokenStateChangeCallback {
        void onEscrowTokenActivated(long j, int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    public @interface VerifyFlag {
    }

    public boolean isTrustUsuallyManaged(int userId) {
        if (this.mLockSettingsService instanceof ILockSettings.Stub) {
            try {
                return getLockSettings().getBoolean(IS_TRUST_USUALLY_MANAGED, false, userId);
            } catch (RemoteException e) {
                return false;
            }
        } else {
            throw new IllegalStateException("May only be called by TrustManagerService. Use TrustManager.isTrustUsuallyManaged()");
        }
    }

    public void setTrustUsuallyManaged(boolean managed, int userId) {
        try {
            getLockSettings().setBoolean(IS_TRUST_USUALLY_MANAGED, managed, userId);
        } catch (RemoteException e) {
        }
    }

    public void userPresent(int userId) {
        try {
            getLockSettings().userPresent(userId);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    /* loaded from: classes4.dex */
    public static final class RequestThrottledException extends Exception {
        private int mTimeoutMs;

        public RequestThrottledException(int timeoutMs) {
            this.mTimeoutMs = timeoutMs;
        }

        public int getTimeoutMs() {
            return this.mTimeoutMs;
        }
    }

    public DevicePolicyManager getDevicePolicyManager() {
        if (this.mDevicePolicyManager == null) {
            DevicePolicyManager devicePolicyManager = (DevicePolicyManager) this.mContext.getSystemService(Context.DEVICE_POLICY_SERVICE);
            this.mDevicePolicyManager = devicePolicyManager;
            if (devicePolicyManager == null) {
                Log.e(TAG, "Can't get DevicePolicyManagerService: is it running?", new IllegalStateException("Stack trace:"));
            }
        }
        return this.mDevicePolicyManager;
    }

    private UserManager getUserManager() {
        if (this.mUserManager == null) {
            this.mUserManager = UserManager.get(this.mContext);
        }
        return this.mUserManager;
    }

    private TrustManager getTrustManager() {
        TrustManager trust = (TrustManager) this.mContext.getSystemService(Context.TRUST_SERVICE);
        if (trust == null) {
            Log.e(TAG, "Can't get TrustManagerService: is it running?", new IllegalStateException("Stack trace:"));
        }
        return trust;
    }

    public LockPatternUtils(Context context) {
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        Looper looper = Looper.myLooper();
        this.mHandler = looper != null ? new Handler(looper) : null;
    }

    public ILockSettings getLockSettings() {
        if (this.mLockSettingsService == null) {
            ILockSettings service = ILockSettings.Stub.asInterface(ServiceManager.getService("lock_settings"));
            this.mLockSettingsService = service;
        }
        ILockSettings service2 = this.mLockSettingsService;
        return service2;
    }

    public int getRequestedMinimumPasswordLength(int userId) {
        return getDevicePolicyManager().getPasswordMinimumLength(null, userId);
    }

    public int getMaximumPasswordLength(int quality) {
        return getDevicePolicyManager().getPasswordMaximumLength(quality);
    }

    public PasswordMetrics getRequestedPasswordMetrics(int userId) {
        return getRequestedPasswordMetrics(userId, false);
    }

    public PasswordMetrics getRequestedPasswordMetrics(int userId, boolean deviceWideOnly) {
        return getDevicePolicyManager().getPasswordMinimumMetrics(userId, deviceWideOnly);
    }

    private int getRequestedPasswordHistoryLength(int userId) {
        return getDevicePolicyManager().getPasswordHistoryLength(null, userId);
    }

    public int getRequestedPasswordComplexity(int userId) {
        return getRequestedPasswordComplexity(userId, false);
    }

    public int getRequestedPasswordComplexity(int userId, boolean deviceWideOnly) {
        return getDevicePolicyManager().getAggregatedPasswordComplexityForUser(userId, deviceWideOnly);
    }

    public void reportFailedPasswordAttempt(int userId) {
        if (userId != -9999 || !frpCredentialEnabled(this.mContext)) {
            getDevicePolicyManager().reportFailedPasswordAttempt(userId);
            getTrustManager().reportUnlockAttempt(false, userId);
        }
    }

    public void reportSuccessfulPasswordAttempt(int userId) {
        if (userId != -9999 || !frpCredentialEnabled(this.mContext)) {
            getDevicePolicyManager().reportSuccessfulPasswordAttempt(userId);
            getTrustManager().reportUnlockAttempt(true, userId);
        }
    }

    public void reportPasswordLockout(int timeoutMs, int userId) {
        if (userId != -9999 || !frpCredentialEnabled(this.mContext)) {
            getTrustManager().reportUnlockLockout(timeoutMs, userId);
        }
    }

    public int getCurrentFailedPasswordAttempts(int userId) {
        if (userId != -9999 || !frpCredentialEnabled(this.mContext)) {
            return getDevicePolicyManager().getCurrentFailedPasswordAttempts(userId);
        }
        return 0;
    }

    public int getMaximumFailedPasswordsForWipe(int userId) {
        if (userId != -9999 || !frpCredentialEnabled(this.mContext)) {
            return getDevicePolicyManager().getMaximumFailedPasswordsForWipe(null, userId);
        }
        return 0;
    }

    public VerifyCredentialResponse verifyCredential(LockscreenCredential credential, int userId, int flags) {
        throwIfCalledOnMainThread();
        try {
            VerifyCredentialResponse response = getLockSettings().verifyCredential(credential, userId, flags);
            if (response == null) {
                return VerifyCredentialResponse.ERROR;
            }
            return response;
        } catch (RemoteException re) {
            Log.e(TAG, "failed to verify credential", re);
            return VerifyCredentialResponse.ERROR;
        }
    }

    public VerifyCredentialResponse verifyGatekeeperPasswordHandle(long gatekeeperPasswordHandle, long challenge, int userId) {
        try {
            VerifyCredentialResponse response = getLockSettings().verifyGatekeeperPasswordHandle(gatekeeperPasswordHandle, challenge, userId);
            if (response == null) {
                return VerifyCredentialResponse.ERROR;
            }
            return response;
        } catch (RemoteException e) {
            Log.e(TAG, "failed to verify gatekeeper password", e);
            return VerifyCredentialResponse.ERROR;
        }
    }

    public void removeGatekeeperPasswordHandle(long gatekeeperPasswordHandle) {
        try {
            getLockSettings().removeGatekeeperPasswordHandle(gatekeeperPasswordHandle);
        } catch (RemoteException e) {
            Log.e(TAG, "failed to remove gatekeeper password handle", e);
        }
    }

    public boolean checkCredential(LockscreenCredential credential, int userId, CheckCredentialProgressCallback progressCallback) throws RequestThrottledException {
        throwIfCalledOnMainThread();
        try {
            VerifyCredentialResponse response = getLockSettings().checkCredential(credential, userId, wrapCallback(progressCallback));
            if (response == null) {
                return false;
            }
            if (response.getResponseCode() == 0) {
                return true;
            }
            if (response.getResponseCode() != 1) {
                return false;
            }
            throw new RequestThrottledException(response.getTimeout());
        } catch (RemoteException re) {
            Log.e(TAG, "failed to check credential", re);
            return false;
        }
    }

    public VerifyCredentialResponse verifyTiedProfileChallenge(LockscreenCredential credential, int userId, int flags) {
        throwIfCalledOnMainThread();
        try {
            VerifyCredentialResponse response = getLockSettings().verifyTiedProfileChallenge(credential, userId, flags);
            if (response == null) {
                return VerifyCredentialResponse.ERROR;
            }
            return response;
        } catch (RemoteException re) {
            Log.e(TAG, "failed to verify tied profile credential", re);
            return VerifyCredentialResponse.ERROR;
        }
    }

    public boolean checkVoldPassword(int userId) {
        try {
            return getLockSettings().checkVoldPassword(userId);
        } catch (RemoteException re) {
            Log.e(TAG, "failed to check vold password", re);
            return false;
        }
    }

    public byte[] getPasswordHistoryHashFactor(LockscreenCredential currentPassword, int userId) {
        try {
            return getLockSettings().getHashFactor(currentPassword, userId);
        } catch (RemoteException e) {
            Log.e(TAG, "failed to get hash factor", e);
            return null;
        }
    }

    public boolean checkPasswordHistory(byte[] passwordToCheck, byte[] hashFactor, int userId) {
        int passwordHistoryLength;
        if (passwordToCheck == null || passwordToCheck.length == 0) {
            Log.e(TAG, "checkPasswordHistory: empty password");
            return false;
        }
        String passwordHistory = getString(PASSWORD_HISTORY_KEY, userId);
        if (TextUtils.isEmpty(passwordHistory) || (passwordHistoryLength = getRequestedPasswordHistoryLength(userId)) == 0) {
            return false;
        }
        byte[] salt = getSalt(userId).getBytes();
        String legacyHash = LockscreenCredential.legacyPasswordToHash(passwordToCheck, salt);
        String passwordHash = LockscreenCredential.passwordToHistoryHash(passwordToCheck, salt, hashFactor);
        String[] history = passwordHistory.split(",");
        for (int i = 0; i < Math.min(passwordHistoryLength, history.length); i++) {
            if (history[i].equals(legacyHash) || history[i].equals(passwordHash)) {
                return true;
            }
        }
        return false;
    }

    public boolean isPatternEverChosen(int userId) {
        return getBoolean(PATTERN_EVER_CHOSEN_KEY, false, userId);
    }

    public void reportPatternWasChosen(int userId) {
        setBoolean(PATTERN_EVER_CHOSEN_KEY, true, userId);
    }

    public int getActivePasswordQuality(int userId) {
        return getKeyguardStoredPasswordQuality(userId);
    }

    public void resetKeyStore(int userId) {
        try {
            getLockSettings().resetKeyStore(userId);
        } catch (RemoteException e) {
            Log.e(TAG, "Couldn't reset keystore " + e);
        }
    }

    public void setLockScreenDisabled(boolean disable, int userId) {
        setBoolean("lockscreen.disabled", disable, userId);
    }

    public boolean isLockScreenDisabled(int userId) {
        if (isSecure(userId)) {
            return false;
        }
        boolean disabledByDefault = this.mContext.getResources().getBoolean(R.bool.config_disableLockscreenByDefault);
        boolean isSystemUser = UserManager.isSplitSystemUser() && userId == 0;
        UserInfo userInfo = getUserManager().getUserInfo(userId);
        boolean isDemoUser = UserManager.isDeviceInDemoMode(this.mContext) && userInfo != null && userInfo.isDemo();
        return getBoolean("lockscreen.disabled", false, userId) || (disabledByDefault && !isSystemUser) || isDemoUser;
    }

    public static boolean isQualityAlphabeticPassword(int quality) {
        return quality >= 262144;
    }

    public static boolean isQualityNumericPin(int quality) {
        return quality == 131072 || quality == 196608;
    }

    public static int credentialTypeToPasswordQuality(int credentialType) {
        switch (credentialType) {
            case -1:
                return 0;
            case 0:
            case 2:
            default:
                throw new IllegalStateException("Unknown type: " + credentialType);
            case 1:
                return 65536;
            case 3:
                return 131072;
            case 4:
                return 262144;
        }
    }

    public boolean setLockCredential(LockscreenCredential newCredential, LockscreenCredential savedCredential, int userHandle) {
        if (hasSecureLockScreen() || newCredential.getType() == -1) {
            newCredential.checkLength();
            try {
                if (!getLockSettings().setLockCredential(newCredential, savedCredential, userHandle)) {
                    return false;
                }
                this.mLockPatternUtilsExt.savePasswordLenght(this.mContext.getContentResolver(), "PASSWORD_LENGTH", newCredential.size(), userHandle);
                return true;
            } catch (RemoteException e) {
                throw new RuntimeException("Unable to save lock password", e);
            }
        } else {
            throw new UnsupportedOperationException("This operation requires the lock screen feature.");
        }
    }

    private void updateCryptoUserInfo(int userId) {
        if (userId == 0) {
            String ownerInfo = isOwnerInfoEnabled(userId) ? getOwnerInfo(userId) : "";
            IBinder service = ServiceManager.getService("mount");
            if (service == null) {
                Log.e(TAG, "Could not find the mount service to update the user info");
                return;
            }
            IStorageManager storageManager = IStorageManager.Stub.asInterface(service);
            try {
                Log.d(TAG, "Setting owner info");
                storageManager.setField(StorageManager.OWNER_INFO_KEY, ownerInfo);
            } catch (RemoteException e) {
                Log.e(TAG, "Error changing user info", e);
            }
        }
    }

    public void setOwnerInfo(String info, int userId) {
        setString("lock_screen_owner_info", info, userId);
        updateCryptoUserInfo(userId);
    }

    public void setOwnerInfoEnabled(boolean enabled, int userId) {
        setBoolean("lock_screen_owner_info_enabled", enabled, userId);
        updateCryptoUserInfo(userId);
    }

    public String getOwnerInfo(int userId) {
        return getString("lock_screen_owner_info", userId);
    }

    public boolean isOwnerInfoEnabled(int userId) {
        return getBoolean("lock_screen_owner_info_enabled", false, userId);
    }

    public void setDeviceOwnerInfo(String info) {
        if (info != null && info.isEmpty()) {
            info = null;
        }
        setString(LOCK_SCREEN_DEVICE_OWNER_INFO, info, 0);
    }

    public String getDeviceOwnerInfo() {
        return getString(LOCK_SCREEN_DEVICE_OWNER_INFO, 0);
    }

    public boolean isDeviceOwnerInfoEnabled() {
        return getDeviceOwnerInfo() != null;
    }

    public static boolean isDeviceEncryptionEnabled() {
        return StorageManager.isEncrypted();
    }

    public static boolean isFileEncryptionEnabled() {
        return StorageManager.isFileEncryptedNativeOrEmulated();
    }

    public void clearEncryptionPassword() {
        try {
            getLockSettings().updateEncryptionPassword(1, null);
        } catch (RemoteException e) {
            Log.e(TAG, "Couldn't clear encryption password");
        }
    }

    @Deprecated
    public int getKeyguardStoredPasswordQuality(int userHandle) {
        return credentialTypeToPasswordQuality(getCredentialTypeForUser(userHandle));
    }

    public void setSeparateProfileChallengeEnabled(int userHandle, boolean enabled, LockscreenCredential profilePassword) {
        if (isManagedProfile(userHandle)) {
            try {
                getLockSettings().setSeparateProfileChallengeEnabled(userHandle, enabled, profilePassword);
                reportEnabledTrustAgentsChanged(userHandle);
            } catch (RemoteException e) {
                Log.e(TAG, "Couldn't update work profile challenge enabled");
            }
        }
    }

    public boolean isSeparateProfileChallengeEnabled(int userHandle) {
        return isManagedProfile(userHandle) && hasSeparateChallenge(userHandle);
    }

    public boolean isManagedProfileWithUnifiedChallenge(int userHandle) {
        return isManagedProfile(userHandle) && !hasSeparateChallenge(userHandle);
    }

    public boolean isSeparateProfileChallengeAllowed(int userHandle) {
        return isManagedProfile(userHandle) && getDevicePolicyManager().isSeparateProfileChallengeAllowed(userHandle);
    }

    private boolean hasSeparateChallenge(int userHandle) {
        try {
            return getLockSettings().getSeparateProfileChallengeEnabled(userHandle);
        } catch (RemoteException e) {
            Log.e(TAG, "Couldn't get separate profile challenge enabled");
            return false;
        }
    }

    private boolean isManagedProfile(int userHandle) {
        UserInfo info = getUserManager().getUserInfo(userHandle);
        return info != null && info.isManagedProfile();
    }

    public static List<LockPatternView.Cell> byteArrayToPattern(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        List<LockPatternView.Cell> result = Lists.newArrayList();
        for (byte b : bytes) {
            byte b2 = (byte) (b - 49);
            result.add(LockPatternView.Cell.of(b2 / 3, b2 % 3));
        }
        return result;
    }

    public static byte[] patternToByteArray(List<LockPatternView.Cell> pattern) {
        if (pattern == null) {
            return new byte[0];
        }
        int patternSize = pattern.size();
        byte[] res = new byte[patternSize];
        for (int i = 0; i < patternSize; i++) {
            LockPatternView.Cell cell = pattern.get(i);
            res[i] = (byte) ((cell.getRow() * 3) + cell.getColumn() + 49);
        }
        return res;
    }

    private String getSalt(int userId) {
        long salt = getLong(LOCK_PASSWORD_SALT_KEY, 0L, userId);
        if (salt == 0) {
            try {
                salt = SecureRandom.getInstance("SHA1PRNG").nextLong();
                setLong(LOCK_PASSWORD_SALT_KEY, salt, userId);
                Log.v(TAG, "Initialized lock password salt for user: " + userId);
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalStateException("Couldn't get SecureRandom number", e);
            }
        }
        return Long.toHexString(salt);
    }

    public int getCredentialTypeForUser(int userHandle) {
        try {
            return getLockSettings().getCredentialType(userHandle);
        } catch (RemoteException re) {
            Log.e(TAG, "failed to get credential type", re);
            return -1;
        }
    }

    public boolean isSecure(int userId) {
        int type = getCredentialTypeForUser(userId);
        return type != -1;
    }

    public boolean isLockPasswordEnabled(int userId) {
        int type = getCredentialTypeForUser(userId);
        return type == 4 || type == 3;
    }

    public boolean isLockPatternEnabled(int userId) {
        int type = getCredentialTypeForUser(userId);
        return type == 1;
    }

    @Deprecated
    public boolean isLegacyLockPatternEnabled(int userId) {
        return getBoolean(LEGACY_LOCK_PATTERN_ENABLED, true, userId);
    }

    @Deprecated
    public void setLegacyLockPatternEnabled(int userId) {
        setBoolean("lock_pattern_autolock", true, userId);
    }

    public boolean isVisiblePatternEnabled(int userId) {
        return getBoolean("lock_pattern_visible_pattern", false, userId);
    }

    public void setVisiblePatternEnabled(boolean enabled, int userId) {
        setBoolean("lock_pattern_visible_pattern", enabled, userId);
        if (userId == 0) {
            IBinder service = ServiceManager.getService("mount");
            if (service == null) {
                Log.e(TAG, "Could not find the mount service to update the user info");
                return;
            }
            IStorageManager storageManager = IStorageManager.Stub.asInterface(service);
            try {
                storageManager.setField(StorageManager.PATTERN_VISIBLE_KEY, enabled ? IOplusUIFirstManager.APP_START_ANIMATION : "0");
            } catch (RemoteException e) {
                Log.e(TAG, "Error changing pattern visible state", e);
            }
        }
    }

    public boolean isVisiblePatternEverChosen(int userId) {
        return getString("lock_pattern_visible_pattern", userId) != null;
    }

    public void setVisiblePasswordEnabled(boolean enabled, int userId) {
        if (userId == 0) {
            IBinder service = ServiceManager.getService("mount");
            if (service == null) {
                Log.e(TAG, "Could not find the mount service to update the user info");
                return;
            }
            IStorageManager storageManager = IStorageManager.Stub.asInterface(service);
            try {
                storageManager.setField(StorageManager.PASSWORD_VISIBLE_KEY, enabled ? IOplusUIFirstManager.APP_START_ANIMATION : "0");
            } catch (RemoteException e) {
                Log.e(TAG, "Error changing password visible state", e);
            }
        }
    }

    public boolean isTactileFeedbackEnabled() {
        return Settings.System.getIntForUser(this.mContentResolver, Settings.System.HAPTIC_FEEDBACK_ENABLED, 1, -2) != 0;
    }

    public long setLockoutAttemptDeadline(int userId, int timeoutMs) {
        long deadline = SystemClock.elapsedRealtime() + timeoutMs;
        if (userId == -9999) {
            return deadline;
        }
        this.mLockPatternUtilsExt.setLockoutAttemptDeadline(userId, deadline, timeoutMs, this);
        return deadline;
    }

    public long getLockoutAttemptDeadline(int userId) {
        return this.mLockPatternUtilsExt.getLockoutAttemptDeadline(userId, this);
    }

    private boolean getBoolean(String secureSettingKey, boolean defaultValue, int userId) {
        try {
            return getLockSettings().getBoolean(secureSettingKey, defaultValue, userId);
        } catch (RemoteException e) {
            return defaultValue;
        }
    }

    private void setBoolean(String secureSettingKey, boolean enabled, int userId) {
        try {
            getLockSettings().setBoolean(secureSettingKey, enabled, userId);
        } catch (RemoteException re) {
            Log.e(TAG, "Couldn't write boolean " + secureSettingKey + re);
        }
    }

    private long getLong(String secureSettingKey, long defaultValue, int userHandle) {
        try {
            return getLockSettings().getLong(secureSettingKey, defaultValue, userHandle);
        } catch (RemoteException e) {
            return defaultValue;
        }
    }

    private void setLong(String secureSettingKey, long value, int userHandle) {
        try {
            getLockSettings().setLong(secureSettingKey, value, userHandle);
        } catch (RemoteException re) {
            Log.e(TAG, "Couldn't write long " + secureSettingKey + re);
        }
    }

    private String getString(String secureSettingKey, int userHandle) {
        try {
            return getLockSettings().getString(secureSettingKey, null, userHandle);
        } catch (RemoteException e) {
            return null;
        }
    }

    private void setString(String secureSettingKey, String value, int userHandle) {
        try {
            getLockSettings().setString(secureSettingKey, value, userHandle);
        } catch (RemoteException re) {
            Log.e(TAG, "Couldn't write string " + secureSettingKey + re);
        }
    }

    public void setPowerButtonInstantlyLocks(boolean enabled, int userId) {
        setBoolean(LOCKSCREEN_POWER_BUTTON_INSTANTLY_LOCKS, enabled, userId);
    }

    public boolean getPowerButtonInstantlyLocks(int userId) {
        return getBoolean(LOCKSCREEN_POWER_BUTTON_INSTANTLY_LOCKS, true, userId);
    }

    public boolean isPowerButtonInstantlyLocksEverChosen(int userId) {
        return getString(LOCKSCREEN_POWER_BUTTON_INSTANTLY_LOCKS, userId) != null;
    }

    public void setEnabledTrustAgents(Collection<ComponentName> activeTrustAgents, int userId) {
        StringBuilder sb = new StringBuilder();
        for (ComponentName cn : activeTrustAgents) {
            if (sb.length() > 0) {
                sb.append(',');
            }
            sb.append(cn.flattenToShortString());
        }
        setString(ENABLED_TRUST_AGENTS, sb.toString(), userId);
        getTrustManager().reportEnabledTrustAgentsChanged(userId);
    }

    public List<ComponentName> getEnabledTrustAgents(int userId) {
        String serialized = getString(ENABLED_TRUST_AGENTS, userId);
        if (TextUtils.isEmpty(serialized)) {
            return null;
        }
        String[] split = serialized.split(",");
        ArrayList<ComponentName> activeTrustAgents = new ArrayList<>(split.length);
        for (String s : split) {
            if (!TextUtils.isEmpty(s)) {
                activeTrustAgents.add(ComponentName.unflattenFromString(s));
            }
        }
        return activeTrustAgents;
    }

    public void requireCredentialEntry(int userId) {
        requireStrongAuth(4, userId);
    }

    public void requireStrongAuth(int strongAuthReason, int userId) {
        try {
            getLockSettings().requireStrongAuth(strongAuthReason, userId);
        } catch (RemoteException e) {
            Log.e(TAG, "Error while requesting strong auth: " + e);
        }
    }

    private void reportEnabledTrustAgentsChanged(int userHandle) {
        getTrustManager().reportEnabledTrustAgentsChanged(userHandle);
    }

    public boolean isCredentialRequiredToDecrypt(boolean defaultValue) {
        int value = Settings.Global.getInt(this.mContentResolver, Settings.Global.REQUIRE_PASSWORD_TO_DECRYPT, -1);
        return value == -1 ? defaultValue : value != 0;
    }

    public void setCredentialRequiredToDecrypt(boolean required) {
        if (!getUserManager().isSystemUser() && !getUserManager().isPrimaryUser()) {
            throw new IllegalStateException("Only the system or primary user may call setCredentialRequiredForDecrypt()");
        } else if (isDeviceEncryptionEnabled()) {
            Settings.Global.putInt(this.mContext.getContentResolver(), Settings.Global.REQUIRE_PASSWORD_TO_DECRYPT, required ? 1 : 0);
        }
    }

    private void throwIfCalledOnMainThread() {
        if (Looper.getMainLooper().isCurrentThread()) {
            throw new IllegalStateException("should not be called from the main thread.");
        }
    }

    public void registerStrongAuthTracker(StrongAuthTracker strongAuthTracker) {
        try {
            getLockSettings().registerStrongAuthTracker(strongAuthTracker.getStub());
        } catch (RemoteException e) {
            throw new RuntimeException("Could not register StrongAuthTracker");
        }
    }

    public void unregisterStrongAuthTracker(StrongAuthTracker strongAuthTracker) {
        try {
            getLockSettings().unregisterStrongAuthTracker(strongAuthTracker.getStub());
        } catch (RemoteException e) {
            Log.e(TAG, "Could not unregister StrongAuthTracker", e);
        }
    }

    public void reportSuccessfulBiometricUnlock(boolean isStrongBiometric, int userId) {
        try {
            getLockSettings().reportSuccessfulBiometricUnlock(isStrongBiometric, userId);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not report successful biometric unlock", e);
        }
    }

    public void scheduleNonStrongBiometricIdleTimeout(int userId) {
        try {
            getLockSettings().scheduleNonStrongBiometricIdleTimeout(userId);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not schedule non-strong biometric idle timeout", e);
        }
    }

    public int getStrongAuthForUser(int userId) {
        try {
            return getLockSettings().getStrongAuthForUser(userId);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not get StrongAuth", e);
            return StrongAuthTracker.getDefaultFlags(this.mContext);
        }
    }

    public boolean isTrustAllowedForUser(int userId) {
        return getStrongAuthForUser(userId) == 0;
    }

    public boolean isBiometricAllowedForUser(int userId) {
        StringBuilder sb = new StringBuilder();
        sb.append("isBiometricAllowedForUser allowed:");
        sb.append((getStrongAuthForUser(userId) & (-5)) == 0);
        sb.append(" userId:");
        sb.append(userId);
        Log.d(TAG, sb.toString());
        return (getStrongAuthForUser(userId) & (-5)) == 0;
    }

    public boolean isUserInLockdown(int userId) {
        return getStrongAuthForUser(userId) == 32;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class WrappedCallback extends ICheckCredentialProgressCallback.Stub {
        private CheckCredentialProgressCallback mCallback;
        private Handler mHandler;

        WrappedCallback(Handler handler, CheckCredentialProgressCallback callback) {
            this.mHandler = handler;
            this.mCallback = callback;
        }

        @Override // com.android.internal.widget.ICheckCredentialProgressCallback
        public void onCredentialVerified() throws RemoteException {
            if (this.mHandler == null) {
                Log.e(LockPatternUtils.TAG, "Handler is null during callback");
            }
            this.mHandler.post(new Runnable() { // from class: com.android.internal.widget.LockPatternUtils$WrappedCallback$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    LockPatternUtils.WrappedCallback.this.lambda$onCredentialVerified$0$LockPatternUtils$WrappedCallback();
                }
            });
            this.mHandler = null;
        }

        public /* synthetic */ void lambda$onCredentialVerified$0$LockPatternUtils$WrappedCallback() {
            this.mCallback.onEarlyMatched();
            this.mCallback = null;
        }
    }

    private ICheckCredentialProgressCallback wrapCallback(CheckCredentialProgressCallback callback) {
        if (callback == null) {
            return null;
        }
        if (this.mHandler != null) {
            return new WrappedCallback(this.mHandler, callback);
        }
        throw new IllegalStateException("Must construct LockPatternUtils on a looper thread to use progress callbacks.");
    }

    private LockSettingsInternal getLockSettingsInternal() {
        LockSettingsInternal service = (LockSettingsInternal) LocalServices.getService(LockSettingsInternal.class);
        if (service != null) {
            return service;
        }
        throw new SecurityException("Only available to system server itself");
    }

    public long addEscrowToken(byte[] token, int userId, EscrowTokenStateChangeCallback callback) {
        return getLockSettingsInternal().addEscrowToken(token, userId, callback);
    }

    public boolean removeEscrowToken(long handle, int userId) {
        return getLockSettingsInternal().removeEscrowToken(handle, userId);
    }

    public boolean isEscrowTokenActive(long handle, int userId) {
        return getLockSettingsInternal().isEscrowTokenActive(handle, userId);
    }

    public boolean setLockCredentialWithToken(LockscreenCredential credential, long tokenHandle, byte[] token, int userHandle) {
        if (hasSecureLockScreen() || credential.getType() == -1) {
            credential.checkLength();
            LockSettingsInternal localService = getLockSettingsInternal();
            return localService.setLockCredentialWithToken(credential, tokenHandle, token, userHandle);
        }
        throw new UnsupportedOperationException("This operation requires the lock screen feature.");
    }

    public boolean unlockUserWithToken(long tokenHandle, byte[] token, int userId) {
        return getLockSettingsInternal().unlockUserWithToken(tokenHandle, token, userId);
    }

    /* loaded from: classes4.dex */
    public static class StrongAuthTracker {
        private static final int ALLOWING_BIOMETRIC = 4;
        public static final int SOME_AUTH_REQUIRED_AFTER_USER_REQUEST = 4;
        public static final int STRONG_AUTH_NOT_REQUIRED = 0;
        public static final int STRONG_AUTH_REQUIRED_AFTER_BOOT = 1;
        public static final int STRONG_AUTH_REQUIRED_AFTER_DPM_LOCK_NOW = 2;
        public static final int STRONG_AUTH_REQUIRED_AFTER_LOCKOUT = 8;
        public static final int STRONG_AUTH_REQUIRED_AFTER_NON_STRONG_BIOMETRICS_TIMEOUT = 128;
        public static final int STRONG_AUTH_REQUIRED_AFTER_TIMEOUT = 16;
        public static final int STRONG_AUTH_REQUIRED_AFTER_USER_LOCKDOWN = 32;
        public static final int STRONG_AUTH_REQUIRED_FOR_UNATTENDED_UPDATE = 64;
        private final boolean mDefaultIsNonStrongBiometricAllowed;
        private final int mDefaultStrongAuthFlags;
        private final H mHandler;
        private final SparseBooleanArray mIsNonStrongBiometricAllowedForUser;
        private final SparseIntArray mStrongAuthRequiredForUser;
        private final IStrongAuthTracker.Stub mStub;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes4.dex */
        public @interface StrongAuthFlags {
        }

        public StrongAuthTracker(Context context) {
            this(context, Looper.myLooper());
        }

        public StrongAuthTracker(Context context, Looper looper) {
            this.mStrongAuthRequiredForUser = new SparseIntArray();
            this.mIsNonStrongBiometricAllowedForUser = new SparseBooleanArray();
            this.mDefaultIsNonStrongBiometricAllowed = true;
            this.mStub = new IStrongAuthTracker.Stub() { // from class: com.android.internal.widget.LockPatternUtils.StrongAuthTracker.1
                @Override // android.app.trust.IStrongAuthTracker
                public void onStrongAuthRequiredChanged(int strongAuthFlags, int userId) {
                    StrongAuthTracker.this.mHandler.obtainMessage(1, strongAuthFlags, userId).sendToTarget();
                }

                @Override // android.app.trust.IStrongAuthTracker
                public void onIsNonStrongBiometricAllowedChanged(boolean allowed, int userId) {
                    StrongAuthTracker.this.mHandler.obtainMessage(2, allowed ? 1 : 0, userId).sendToTarget();
                }
            };
            this.mHandler = new H(looper);
            this.mDefaultStrongAuthFlags = getDefaultFlags(context);
        }

        public static int getDefaultFlags(Context context) {
            return context.getResources().getBoolean(R.bool.config_strongAuthRequiredOnBoot) ? 1 : 0;
        }

        public int getStrongAuthForUser(int userId) {
            return this.mStrongAuthRequiredForUser.get(userId, this.mDefaultStrongAuthFlags);
        }

        public boolean isTrustAllowedForUser(int userId) {
            return getStrongAuthForUser(userId) == 0;
        }

        public boolean isBiometricAllowedForUser(boolean isStrongBiometric, int userId) {
            boolean allowed = (getStrongAuthForUser(userId) & (-5)) == 0;
            if (!isStrongBiometric) {
                allowed &= isNonStrongBiometricAllowedAfterIdleTimeout(userId);
            }
            Log.d(LockPatternUtils.TAG, "isBiometricAllowedForUser allowed:" + allowed + " isStrongBiometric:" + isStrongBiometric + " userId:" + userId);
            return allowed;
        }

        public boolean isNonStrongBiometricAllowedAfterIdleTimeout(int userId) {
            return this.mIsNonStrongBiometricAllowedForUser.get(userId, true);
        }

        public void onStrongAuthRequiredChanged(int userId) {
        }

        public void onIsNonStrongBiometricAllowedChanged(int userId) {
        }

        protected void handleStrongAuthRequiredChanged(int strongAuthFlags, int userId) {
            int oldValue = getStrongAuthForUser(userId);
            if (strongAuthFlags != oldValue) {
                if (strongAuthFlags == this.mDefaultStrongAuthFlags) {
                    this.mStrongAuthRequiredForUser.delete(userId);
                } else {
                    this.mStrongAuthRequiredForUser.put(userId, strongAuthFlags);
                }
                onStrongAuthRequiredChanged(userId);
            }
        }

        protected void handleIsNonStrongBiometricAllowedChanged(boolean allowed, int userId) {
            boolean oldValue = isNonStrongBiometricAllowedAfterIdleTimeout(userId);
            if (allowed != oldValue) {
                if (allowed) {
                    this.mIsNonStrongBiometricAllowedForUser.delete(userId);
                } else {
                    this.mIsNonStrongBiometricAllowedForUser.put(userId, allowed);
                }
                onIsNonStrongBiometricAllowedChanged(userId);
            }
        }

        public IStrongAuthTracker.Stub getStub() {
            return this.mStub;
        }

        /* loaded from: classes4.dex */
        private class H extends Handler {
            static final int MSG_ON_IS_NON_STRONG_BIOMETRIC_ALLOWED_CHANGED = 2;
            static final int MSG_ON_STRONG_AUTH_REQUIRED_CHANGED = 1;

            public H(Looper looper) {
                super(looper);
            }

            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        StrongAuthTracker.this.handleStrongAuthRequiredChanged(msg.arg1, msg.arg2);
                        return;
                    case 2:
                        StrongAuthTracker strongAuthTracker = StrongAuthTracker.this;
                        boolean z = true;
                        if (msg.arg1 != 1) {
                            z = false;
                        }
                        strongAuthTracker.handleIsNonStrongBiometricAllowedChanged(z, msg.arg2);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public void enableSyntheticPassword() {
        setLong(SYNTHETIC_PASSWORD_ENABLED_KEY, 1L, 0);
    }

    public void disableSyntheticPassword() {
        setLong(SYNTHETIC_PASSWORD_ENABLED_KEY, 0L, 0);
    }

    public boolean isSyntheticPasswordEnabled() {
        return getLong(SYNTHETIC_PASSWORD_ENABLED_KEY, 1L, 0) != 0;
    }

    public boolean hasPendingEscrowToken(int userId) {
        try {
            return getLockSettings().hasPendingEscrowToken(userId);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
            return false;
        }
    }

    public boolean hasSecureLockScreen() {
        if (this.mHasSecureLockScreen == null) {
            try {
                this.mHasSecureLockScreen = Boolean.valueOf(getLockSettings().hasSecureLockScreen());
            } catch (RemoteException e) {
                e.rethrowFromSystemServer();
            }
        }
        return this.mHasSecureLockScreen.booleanValue();
    }

    public static boolean userOwnsFrpCredential(Context context, UserInfo info) {
        return info != null && info.isPrimary() && info.isAdmin() && frpCredentialEnabled(context);
    }

    public static boolean frpCredentialEnabled(Context context) {
        return context.getResources().getBoolean(R.bool.config_enableCredentialFactoryResetProtection);
    }

    public boolean tryUnlockWithCachedUnifiedChallenge(int userId) {
        try {
            return getLockSettings().tryUnlockWithCachedUnifiedChallenge(userId);
        } catch (RemoteException e) {
            return false;
        }
    }

    public void removeCachedUnifiedChallenge(int userId) {
        try {
            getLockSettings().removeCachedUnifiedChallenge(userId);
        } catch (RemoteException re) {
            re.rethrowFromSystemServer();
        }
    }
}
