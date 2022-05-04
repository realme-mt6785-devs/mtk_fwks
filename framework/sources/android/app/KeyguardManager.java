package android.app;

import android.Manifest;
import android.annotation.SystemApi;
import android.app.INotificationManager;
import android.app.KeyguardManager;
import android.app.admin.DevicePolicyManager;
import android.app.admin.PasswordMetrics;
import android.app.trust.ITrustManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.Settings;
import android.service.persistentdata.IPersistentDataBlockService;
import android.util.Log;
import android.view.IOnKeyguardExitResult;
import android.view.IWindowManager;
import android.view.WindowManagerGlobal;
import com.android.internal.policy.IKeyguardDismissCallback;
import com.android.internal.widget.LockPatternUtils;
import com.android.internal.widget.LockPatternView;
import com.android.internal.widget.LockscreenCredential;
import com.android.internal.widget.VerifyCredentialResponse;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public class KeyguardManager {
    public static final String ACTION_CONFIRM_DEVICE_CREDENTIAL = "android.app.action.CONFIRM_DEVICE_CREDENTIAL";
    public static final String ACTION_CONFIRM_DEVICE_CREDENTIAL_WITH_USER = "android.app.action.CONFIRM_DEVICE_CREDENTIAL_WITH_USER";
    public static final String ACTION_CONFIRM_FRP_CREDENTIAL = "android.app.action.CONFIRM_FRP_CREDENTIAL";
    public static final String EXTRA_ALTERNATE_BUTTON_LABEL = "android.app.extra.ALTERNATE_BUTTON_LABEL";
    public static final String EXTRA_DESCRIPTION = "android.app.extra.DESCRIPTION";
    public static final String EXTRA_DISALLOW_BIOMETRICS_IF_POLICY_EXISTS = "check_dpm";
    public static final String EXTRA_TITLE = "android.app.extra.TITLE";
    @SystemApi
    public static final int PASSWORD = 0;
    @SystemApi
    public static final int PATTERN = 2;
    @SystemApi
    public static final int PIN = 1;
    public static final int RESULT_ALTERNATE = 1;
    private static final String TAG = "KeyguardManager";
    private final Context mContext;
    private final IWindowManager mWM = WindowManagerGlobal.getWindowManagerService();
    private final IActivityManager mAm = ActivityManager.getService();
    private final ITrustManager mTrustManager = ITrustManager.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.TRUST_SERVICE));
    private final INotificationManager mNotificationManager = INotificationManager.Stub.asInterface(ServiceManager.getServiceOrThrow("notification"));

    /* loaded from: classes.dex */
    @interface LockTypes {
    }

    @Deprecated
    /* loaded from: classes.dex */
    public interface OnKeyguardExitResult {
        void onKeyguardExitResult(boolean z);
    }

    @Deprecated
    public Intent createConfirmDeviceCredentialIntent(CharSequence title, CharSequence description) {
        if (!isDeviceSecure()) {
            return null;
        }
        Intent intent = new Intent(ACTION_CONFIRM_DEVICE_CREDENTIAL);
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_DESCRIPTION, description);
        intent.setPackage(getSettingsPackageForIntent(intent));
        return intent;
    }

    public Intent createConfirmDeviceCredentialIntent(CharSequence title, CharSequence description, int userId) {
        if (!isDeviceSecure(userId)) {
            return null;
        }
        Intent intent = new Intent(ACTION_CONFIRM_DEVICE_CREDENTIAL_WITH_USER);
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_DESCRIPTION, description);
        intent.putExtra(Intent.EXTRA_USER_ID, userId);
        intent.setPackage(getSettingsPackageForIntent(intent));
        return intent;
    }

    public Intent createConfirmDeviceCredentialIntent(CharSequence title, CharSequence description, int userId, boolean disallowBiometricsIfPolicyExists) {
        Intent intent = createConfirmDeviceCredentialIntent(title, description, userId);
        intent.putExtra(EXTRA_DISALLOW_BIOMETRICS_IF_POLICY_EXISTS, disallowBiometricsIfPolicyExists);
        return intent;
    }

    @SystemApi
    public Intent createConfirmFactoryResetCredentialIntent(CharSequence title, CharSequence description, CharSequence alternateButtonLabel) {
        if (!LockPatternUtils.frpCredentialEnabled(this.mContext)) {
            Log.w(TAG, "Factory reset credentials not supported.");
            throw new UnsupportedOperationException("not supported on this device");
        } else if (Settings.Global.getInt(this.mContext.getContentResolver(), "device_provisioned", 0) == 0) {
            try {
                IPersistentDataBlockService pdb = IPersistentDataBlockService.Stub.asInterface(ServiceManager.getService(Context.PERSISTENT_DATA_BLOCK_SERVICE));
                if (pdb == null) {
                    Log.e(TAG, "No persistent data block service");
                    throw new UnsupportedOperationException("not supported on this device");
                } else if (!pdb.hasFrpCredentialHandle()) {
                    Log.i(TAG, "The persistent data block does not have a factory reset credential.");
                    return null;
                } else {
                    Intent intent = new Intent(ACTION_CONFIRM_FRP_CREDENTIAL);
                    intent.putExtra(EXTRA_TITLE, title);
                    intent.putExtra(EXTRA_DESCRIPTION, description);
                    intent.putExtra(EXTRA_ALTERNATE_BUTTON_LABEL, alternateButtonLabel);
                    intent.setPackage(getSettingsPackageForIntent(intent));
                    return intent;
                }
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Log.e(TAG, "Factory reset credential cannot be verified after provisioning.");
            throw new IllegalStateException("must not be provisioned yet");
        }
    }

    @SystemApi
    public void setPrivateNotificationsAllowed(boolean allow) {
        try {
            this.mNotificationManager.setPrivateNotificationsAllowed(allow);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean getPrivateNotificationsAllowed() {
        try {
            return this.mNotificationManager.getPrivateNotificationsAllowed();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private String getSettingsPackageForIntent(Intent intent) {
        List<ResolveInfo> resolveInfos = this.mContext.getPackageManager().queryIntentActivities(intent, 1048576);
        if (0 < resolveInfos.size()) {
            return resolveInfos.get(0).activityInfo.packageName;
        }
        return "com.android.settings";
    }

    @Deprecated
    /* loaded from: classes.dex */
    public class KeyguardLock {
        private final String mTag;
        private final IBinder mToken = new Binder();

        KeyguardLock(String tag) {
            this.mTag = tag;
        }

        public void disableKeyguard() {
            try {
                KeyguardManager.this.mWM.disableKeyguard(this.mToken, this.mTag, KeyguardManager.this.mContext.getUserId());
            } catch (RemoteException e) {
            }
        }

        public void reenableKeyguard() {
            try {
                KeyguardManager.this.mWM.reenableKeyguard(this.mToken, KeyguardManager.this.mContext.getUserId());
            } catch (RemoteException e) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static abstract class KeyguardDismissCallback {
        public void onDismissError() {
        }

        public void onDismissSucceeded() {
        }

        public void onDismissCancelled() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public KeyguardManager(Context context) throws ServiceManager.ServiceNotFoundException {
        this.mContext = context;
    }

    @Deprecated
    public KeyguardLock newKeyguardLock(String tag) {
        return new KeyguardLock(tag);
    }

    public boolean isKeyguardLocked() {
        try {
            return this.mWM.isKeyguardLocked();
        } catch (RemoteException e) {
            return false;
        }
    }

    public boolean isKeyguardSecure() {
        try {
            return this.mWM.isKeyguardSecure(this.mContext.getUserId());
        } catch (RemoteException e) {
            return false;
        }
    }

    public boolean inKeyguardRestrictedInputMode() {
        return isKeyguardLocked();
    }

    public boolean isDeviceLocked() {
        return isDeviceLocked(this.mContext.getUserId());
    }

    public boolean isDeviceLocked(int userId) {
        try {
            return this.mTrustManager.isDeviceLocked(userId);
        } catch (RemoteException e) {
            return false;
        }
    }

    public boolean isDeviceSecure() {
        return isDeviceSecure(this.mContext.getUserId());
    }

    public boolean isDeviceSecure(int userId) {
        try {
            return this.mTrustManager.isDeviceSecure(userId);
        } catch (RemoteException e) {
            return false;
        }
    }

    public void requestDismissKeyguard(Activity activity, KeyguardDismissCallback callback) {
        requestDismissKeyguard(activity, null, callback);
    }

    @SystemApi
    public void requestDismissKeyguard(final Activity activity, CharSequence message, final KeyguardDismissCallback callback) {
        ActivityClient.getInstance().dismissKeyguard(activity.getActivityToken(), new IKeyguardDismissCallback.Stub() { // from class: android.app.KeyguardManager.1
            @Override // com.android.internal.policy.IKeyguardDismissCallback
            public void onDismissError() throws RemoteException {
                if (callback != null && !activity.isDestroyed()) {
                    Handler handler = activity.mHandler;
                    final KeyguardDismissCallback keyguardDismissCallback = callback;
                    Objects.requireNonNull(keyguardDismissCallback);
                    handler.post(new Runnable() { // from class: android.app.KeyguardManager$1$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            KeyguardManager.KeyguardDismissCallback.this.onDismissError();
                        }
                    });
                }
            }

            @Override // com.android.internal.policy.IKeyguardDismissCallback
            public void onDismissSucceeded() throws RemoteException {
                if (callback != null && !activity.isDestroyed()) {
                    Handler handler = activity.mHandler;
                    final KeyguardDismissCallback keyguardDismissCallback = callback;
                    Objects.requireNonNull(keyguardDismissCallback);
                    handler.post(new Runnable() { // from class: android.app.KeyguardManager$1$$ExternalSyntheticLambda2
                        @Override // java.lang.Runnable
                        public final void run() {
                            KeyguardManager.KeyguardDismissCallback.this.onDismissSucceeded();
                        }
                    });
                }
            }

            @Override // com.android.internal.policy.IKeyguardDismissCallback
            public void onDismissCancelled() throws RemoteException {
                if (callback != null && !activity.isDestroyed()) {
                    Handler handler = activity.mHandler;
                    final KeyguardDismissCallback keyguardDismissCallback = callback;
                    Objects.requireNonNull(keyguardDismissCallback);
                    handler.post(new Runnable() { // from class: android.app.KeyguardManager$1$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            KeyguardManager.KeyguardDismissCallback.this.onDismissCancelled();
                        }
                    });
                }
            }
        }, message);
    }

    @Deprecated
    public void exitKeyguardSecurely(final OnKeyguardExitResult callback) {
        try {
            this.mWM.exitKeyguardSecurely(new IOnKeyguardExitResult.Stub() { // from class: android.app.KeyguardManager.2
                @Override // android.view.IOnKeyguardExitResult
                public void onKeyguardExitResult(boolean success) throws RemoteException {
                    OnKeyguardExitResult onKeyguardExitResult = callback;
                    if (onKeyguardExitResult != null) {
                        onKeyguardExitResult.onKeyguardExitResult(success);
                    }
                }
            });
        } catch (RemoteException e) {
        }
    }

    private boolean checkInitialLockMethodUsage() {
        if (hasPermission(Manifest.permission.SET_INITIAL_LOCK)) {
            return this.mContext.getPackageManager().hasSystemFeature(PackageManager.FEATURE_AUTOMOTIVE);
        }
        throw new SecurityException("Requires SET_INITIAL_LOCK permission.");
    }

    private boolean hasPermission(String permission) {
        return this.mContext.checkCallingOrSelfPermission(permission) == 0;
    }

    @SystemApi
    public boolean isValidLockPasswordComplexity(int lockType, byte[] password, int complexity) {
        if (!checkInitialLockMethodUsage()) {
            return false;
        }
        int complexity2 = PasswordMetrics.sanitizeComplexityLevel(complexity);
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) this.mContext.getSystemService(Context.DEVICE_POLICY_SERVICE);
        PasswordMetrics adminMetrics = devicePolicyManager.getPasswordMinimumMetrics(this.mContext.getUserId());
        boolean isPinOrPattern = lockType != 0;
        return PasswordMetrics.validatePassword(adminMetrics, complexity2, isPinOrPattern, password).size() == 0;
    }

    @SystemApi
    public int getMinLockLength(boolean isPin, int complexity) {
        if (!checkInitialLockMethodUsage()) {
            return -1;
        }
        int complexity2 = PasswordMetrics.sanitizeComplexityLevel(complexity);
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) this.mContext.getSystemService(Context.DEVICE_POLICY_SERVICE);
        PasswordMetrics adminMetrics = devicePolicyManager.getPasswordMinimumMetrics(this.mContext.getUserId());
        PasswordMetrics minMetrics = PasswordMetrics.applyComplexity(adminMetrics, isPin, complexity2);
        return minMetrics.length;
    }

    @SystemApi
    public boolean setLock(int lockType, byte[] password, int complexity) {
        boolean success;
        if (!checkInitialLockMethodUsage()) {
            return false;
        }
        LockPatternUtils lockPatternUtils = new LockPatternUtils(this.mContext);
        int userId = this.mContext.getUserId();
        if (isDeviceSecure(userId)) {
            Log.e(TAG, "Password already set, rejecting call to setLock");
            return false;
        }
        try {
            if (!isValidLockPasswordComplexity(lockType, password, complexity)) {
                Log.e(TAG, "Password is not valid, rejecting call to setLock");
                return false;
            }
            try {
                LockscreenCredential credential = createLockscreenCredential(lockType, password);
                success = lockPatternUtils.setLockCredential(credential, LockscreenCredential.createNone(), userId);
            } catch (Exception e) {
                Log.e(TAG, "Save lock exception", e);
                success = false;
            }
            return success;
        } finally {
            Arrays.fill(password, (byte) 0);
        }
    }

    public boolean setLock(int newLockType, byte[] newPassword, int currentLockType, byte[] currentPassword) {
        LockPatternUtils lockPatternUtils = new LockPatternUtils(this.mContext);
        int userId = this.mContext.getUserId();
        LockscreenCredential currentCredential = createLockscreenCredential(currentLockType, currentPassword);
        LockscreenCredential newCredential = createLockscreenCredential(newLockType, newPassword);
        return lockPatternUtils.setLockCredential(newCredential, currentCredential, userId);
    }

    public boolean checkLock(int lockType, byte[] password) {
        LockPatternUtils lockPatternUtils = new LockPatternUtils(this.mContext);
        LockscreenCredential credential = createLockscreenCredential(lockType, password);
        VerifyCredentialResponse response = lockPatternUtils.verifyCredential(credential, this.mContext.getUserId(), 0);
        return response != null && response.getResponseCode() == 0;
    }

    private LockscreenCredential createLockscreenCredential(int lockType, byte[] password) {
        if (password == null) {
            return LockscreenCredential.createNone();
        }
        switch (lockType) {
            case 0:
                CharSequence passwordStr = new String(password, Charset.forName("UTF-8"));
                return LockscreenCredential.createPassword(passwordStr);
            case 1:
                CharSequence pinStr = new String(password);
                return LockscreenCredential.createPin(pinStr);
            case 2:
                List<LockPatternView.Cell> pattern = LockPatternUtils.byteArrayToPattern(password);
                return LockscreenCredential.createPattern(pattern);
            default:
                throw new IllegalArgumentException("Unknown lock type " + lockType);
        }
    }
}
