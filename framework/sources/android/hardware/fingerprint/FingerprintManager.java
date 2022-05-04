package android.hardware.fingerprint;

import android.app.ActivityManager;
import android.content.Context;
import android.hardware.biometrics.BiometricAuthenticator;
import android.hardware.biometrics.BiometricFingerprintConstants;
import android.hardware.biometrics.BiometricTestSession;
import android.hardware.biometrics.IBiometricServiceLockoutResetCallback;
import android.hardware.biometrics.ITestSession;
import android.hardware.biometrics.ITestSessionCallback;
import android.hardware.biometrics.SensorProperties;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.fingerprint.IFingerprintServiceReceiver;
import android.os.Binder;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.IBinder;
import android.os.IRemoteCallback;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.security.identity.IdentityCredential;
import android.util.Slog;
import com.android.internal.R;
import com.android.internal.util.FrameworkStatsLog;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.security.Signature;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.Mac;
@Deprecated
/* loaded from: classes.dex */
public class FingerprintManager implements BiometricAuthenticator, BiometricFingerprintConstants {
    private static final boolean DEBUG = true;
    public static final int ENROLL_ENROLL = 2;
    public static final int ENROLL_FIND_SENSOR = 1;
    private static final int MSG_ACQUIRED = 101;
    private static final int MSG_AUTHENTICATION_FAILED = 103;
    private static final int MSG_AUTHENTICATION_SUCCEEDED = 102;
    private static final int MSG_CHALLENGE_GENERATED = 106;
    private static final int MSG_ENROLL_RESULT = 100;
    private static final int MSG_ERROR = 104;
    private static final int MSG_FINGERPRINT_DETECTED = 107;
    private static final int MSG_REMOVED = 105;
    private static final int MSG_UDFPS_POINTER_DOWN = 108;
    private static final int MSG_UDFPS_POINTER_UP = 109;
    public static final int SENSOR_ID_ANY = -1;
    private static final String TAG = "FingerprintManager";
    private AuthenticationCallback mAuthenticationCallback;
    private Context mContext;
    private CryptoObject mCryptoObject;
    private EnrollmentCallback mEnrollmentCallback;
    private FingerprintDetectionCallback mFingerprintDetectionCallback;
    private GenerateChallengeCallback mGenerateChallengeCallback;
    private Handler mHandler;
    private RemovalCallback mRemovalCallback;
    private RemoveTracker mRemoveTracker;
    private IFingerprintService mService;
    private static final boolean DEBUG_FINGER = SystemProperties.getBoolean("persist.sys.assert.panic", false);
    private static IFingerprintManagerExt mIFingerprintManagerExt = null;
    private IBinder mToken = new Binder();
    private IFingerprintServiceReceiver mServiceReceiver = new IFingerprintServiceReceiver.Stub() { // from class: android.hardware.fingerprint.FingerprintManager.2
        @Override // android.hardware.fingerprint.IFingerprintServiceReceiver
        public void onEnrollResult(Fingerprint fp, int remaining) {
            FingerprintManager.this.mHandler.obtainMessage(100, remaining, 0, fp).sendToTarget();
        }

        @Override // android.hardware.fingerprint.IFingerprintServiceReceiver
        public void onAcquired(int acquireInfo, int vendorCode) {
            if (FingerprintManager.DEBUG_FINGER) {
                Slog.d(FingerprintManager.TAG, "onAcquired acquireInfo: " + acquireInfo);
            }
            FingerprintManager.this.mHandler.obtainMessage(101, acquireInfo, vendorCode).sendToTarget();
        }

        @Override // android.hardware.fingerprint.IFingerprintServiceReceiver
        public void onAuthenticationSucceeded(Fingerprint fp, int userId, boolean isStrongBiometric) {
            if (FingerprintManager.DEBUG_FINGER) {
                Slog.d(FingerprintManager.TAG, "onAuthenticationSucceeded userId: " + userId);
            }
            if ("com.android.systemui".equals(FingerprintManager.this.mContext.getOpPackageName())) {
                FingerprintManager.this.sendAuthenticatedSucceeded(fp, userId, isStrongBiometric);
            } else {
                FingerprintManager.this.mHandler.obtainMessage(102, userId, isStrongBiometric ? 1 : 0, fp).sendToTarget();
            }
        }

        @Override // android.hardware.fingerprint.IFingerprintServiceReceiver
        public void onFingerprintDetected(int sensorId, int userId, boolean isStrongBiometric) {
            if (FingerprintManager.DEBUG_FINGER) {
                Slog.d(FingerprintManager.TAG, "onFingerprintDetected userId: " + userId);
            }
            FingerprintManager.this.mHandler.obtainMessage(107, sensorId, userId, Boolean.valueOf(isStrongBiometric)).sendToTarget();
        }

        @Override // android.hardware.fingerprint.IFingerprintServiceReceiver
        public void onAuthenticationFailed() {
            if (FingerprintManager.DEBUG_FINGER) {
                Slog.d(FingerprintManager.TAG, "onAuthenticationFailed");
            }
            FingerprintManager.this.mHandler.obtainMessage(103).sendToTarget();
        }

        @Override // android.hardware.fingerprint.IFingerprintServiceReceiver
        public void onError(int error, int vendorCode) {
            if (FingerprintManager.DEBUG_FINGER) {
                Slog.d(FingerprintManager.TAG, "onError userId: " + error);
            }
            FingerprintManager.this.mHandler.obtainMessage(104, error, vendorCode).sendToTarget();
        }

        @Override // android.hardware.fingerprint.IFingerprintServiceReceiver
        public void onRemoved(Fingerprint fp, int remaining) {
            if (FingerprintManager.DEBUG_FINGER) {
                Slog.d(FingerprintManager.TAG, "onRemoved remaining: " + remaining);
            }
            FingerprintManager.this.mHandler.obtainMessage(105, remaining, 0, fp).sendToTarget();
        }

        @Override // android.hardware.fingerprint.IFingerprintServiceReceiver
        public void onChallengeGenerated(int sensorId, int userId, long challenge) {
            if (FingerprintManager.DEBUG_FINGER) {
                Slog.d(FingerprintManager.TAG, "onChallengeGenerated challenge: " + challenge + " userId:" + userId + " sensorId:" + sensorId);
            }
            FingerprintManager.this.mHandler.obtainMessage(106, sensorId, userId, Long.valueOf(challenge)).sendToTarget();
        }

        @Override // android.hardware.fingerprint.IFingerprintServiceReceiver
        public void onUdfpsPointerDown(int sensorId) {
            if (FingerprintManager.DEBUG_FINGER) {
                Slog.d(FingerprintManager.TAG, "onUdfpsPointerDown sensorId: " + sensorId);
            }
            FingerprintManager.this.mHandler.obtainMessage(108, sensorId, 0).sendToTarget();
        }

        @Override // android.hardware.fingerprint.IFingerprintServiceReceiver
        public void onUdfpsPointerUp(int sensorId) {
            if (FingerprintManager.DEBUG_FINGER) {
                Slog.d(FingerprintManager.TAG, "onUdfpsPointerUp sensorId: " + sensorId);
            }
            FingerprintManager.this.mHandler.obtainMessage(109, sensorId, 0).sendToTarget();
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface EnrollReason {
    }

    /* loaded from: classes.dex */
    public interface FingerprintDetectionCallback {
        void onFingerprintDetected(int i, int i2, boolean z);
    }

    /* loaded from: classes.dex */
    public interface GenerateChallengeCallback {
        void onChallengeGenerated(int i, int i2, long j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class RemoveTracker {
        static final int REMOVE_ALL = 2;
        static final int REMOVE_SINGLE = 1;
        final int mRemoveRequest;
        final Fingerprint mSingleFingerprint;

        /* loaded from: classes.dex */
        @interface RemoveRequest {
        }

        RemoveTracker(int request, Fingerprint fingerprint) {
            this.mRemoveRequest = request;
            this.mSingleFingerprint = fingerprint;
        }
    }

    public List<SensorProperties> getSensorProperties() {
        List<SensorProperties> properties = new ArrayList<>();
        List<FingerprintSensorPropertiesInternal> internalProperties = getSensorPropertiesInternal();
        for (FingerprintSensorPropertiesInternal internalProp : internalProperties) {
            properties.add(FingerprintSensorProperties.from(internalProp));
        }
        return properties;
    }

    public BiometricTestSession createTestSession(int sensorId) {
        try {
            return new BiometricTestSession(this.mContext, sensorId, new BiometricTestSession.TestSessionProvider() { // from class: android.hardware.fingerprint.FingerprintManager$$ExternalSyntheticLambda0
                @Override // android.hardware.biometrics.BiometricTestSession.TestSessionProvider
                public final ITestSession createTestSession(Context context, int i, ITestSessionCallback iTestSessionCallback) {
                    return FingerprintManager.this.lambda$createTestSession$0$FingerprintManager(context, i, iTestSessionCallback);
                }
            });
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public /* synthetic */ ITestSession lambda$createTestSession$0$FingerprintManager(Context context, int sensorId1, ITestSessionCallback callback) throws RemoteException {
        return this.mService.createTestSession(sensorId1, callback, context.getOpPackageName());
    }

    /* loaded from: classes.dex */
    private class OnEnrollCancelListener implements CancellationSignal.OnCancelListener {
        private OnEnrollCancelListener() {
        }

        /* synthetic */ OnEnrollCancelListener(FingerprintManager x0, AnonymousClass1 x1) {
            this();
        }

        @Override // android.os.CancellationSignal.OnCancelListener
        public void onCancel() {
            FingerprintManager.this.cancelEnrollment();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class OnAuthenticationCancelListener implements CancellationSignal.OnCancelListener {
        private android.hardware.biometrics.CryptoObject mCrypto;

        public OnAuthenticationCancelListener(android.hardware.biometrics.CryptoObject crypto) {
            this.mCrypto = crypto;
        }

        @Override // android.os.CancellationSignal.OnCancelListener
        public void onCancel() {
            FingerprintManager.this.cancelAuthentication(this.mCrypto);
        }
    }

    /* loaded from: classes.dex */
    private class OnFingerprintDetectionCancelListener implements CancellationSignal.OnCancelListener {
        private OnFingerprintDetectionCancelListener() {
        }

        /* synthetic */ OnFingerprintDetectionCancelListener(FingerprintManager x0, AnonymousClass1 x1) {
            this();
        }

        @Override // android.os.CancellationSignal.OnCancelListener
        public void onCancel() {
            FingerprintManager.this.cancelFingerprintDetect();
        }
    }

    @Deprecated
    /* loaded from: classes.dex */
    public static final class CryptoObject extends android.hardware.biometrics.CryptoObject {
        public CryptoObject(Signature signature) {
            super(signature);
        }

        public CryptoObject(Cipher cipher) {
            super(cipher);
        }

        public CryptoObject(Mac mac) {
            super(mac);
        }

        @Override // android.hardware.biometrics.CryptoObject
        public Signature getSignature() {
            return super.getSignature();
        }

        @Override // android.hardware.biometrics.CryptoObject
        public Cipher getCipher() {
            return super.getCipher();
        }

        @Override // android.hardware.biometrics.CryptoObject
        public Mac getMac() {
            return super.getMac();
        }

        @Override // android.hardware.biometrics.CryptoObject
        public IdentityCredential getIdentityCredential() {
            return super.getIdentityCredential();
        }
    }

    @Deprecated
    /* loaded from: classes.dex */
    public static class AuthenticationResult {
        private CryptoObject mCryptoObject;
        private Fingerprint mFingerprint;
        private boolean mIsStrongBiometric;
        private int mUserId;

        public AuthenticationResult(CryptoObject crypto, Fingerprint fingerprint, int userId, boolean isStrongBiometric) {
            this.mCryptoObject = crypto;
            this.mFingerprint = fingerprint;
            this.mUserId = userId;
            this.mIsStrongBiometric = isStrongBiometric;
        }

        public CryptoObject getCryptoObject() {
            return this.mCryptoObject;
        }

        public Fingerprint getFingerprint() {
            return this.mFingerprint;
        }

        public int getUserId() {
            return this.mUserId;
        }

        public boolean isStrongBiometric() {
            return this.mIsStrongBiometric;
        }
    }

    @Deprecated
    /* loaded from: classes.dex */
    public static abstract class AuthenticationCallback extends BiometricAuthenticator.AuthenticationCallback {
        @Override // android.hardware.biometrics.BiometricAuthenticator.AuthenticationCallback
        public void onAuthenticationError(int errorCode, CharSequence errString) {
        }

        @Override // android.hardware.biometrics.BiometricAuthenticator.AuthenticationCallback
        public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        }

        public void onAuthenticationSucceeded(AuthenticationResult result) {
        }

        @Override // android.hardware.biometrics.BiometricAuthenticator.AuthenticationCallback
        public void onAuthenticationFailed() {
        }

        @Override // android.hardware.biometrics.BiometricAuthenticator.AuthenticationCallback
        public void onAuthenticationAcquired(int acquireInfo) {
        }

        public void onUdfpsPointerDown(int sensorId) {
        }

        public void onUdfpsPointerUp(int sensorId) {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class EnrollmentCallback {
        public void onEnrollmentError(int errMsgId, CharSequence errString) {
        }

        public void onEnrollmentHelp(int helpMsgId, CharSequence helpString) {
        }

        public void onEnrollmentProgress(int remaining) {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class RemovalCallback {
        public void onRemovalError(Fingerprint fp, int errMsgId, CharSequence errString) {
        }

        public void onRemovalSucceeded(Fingerprint fp, int remaining) {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class LockoutResetCallback {
        public void onLockoutReset(int sensorId) {
        }
    }

    private void useHandler(Handler handler) {
        if (handler != null) {
            this.mHandler = new MyHandler(this, handler.getLooper(), (AnonymousClass1) null);
        } else if (this.mHandler.getLooper() != this.mContext.getMainLooper()) {
            this.mHandler = new MyHandler(this, this.mContext.getMainLooper(), (AnonymousClass1) null);
        }
    }

    @Deprecated
    public void authenticate(CryptoObject crypto, CancellationSignal cancel, int flags, AuthenticationCallback callback, Handler handler) {
        authenticate(crypto, cancel, callback, handler, this.mContext.getUserId());
    }

    public void authenticate(CryptoObject crypto, CancellationSignal cancel, AuthenticationCallback callback, Handler handler, int userId) {
        authenticate(crypto, cancel, callback, handler, -1, userId);
    }

    public void authenticate(CryptoObject crypto, CancellationSignal cancel, AuthenticationCallback callback, Handler handler, int sensorId, int userId) {
        FrameworkStatsLog.write(356, 1, this.mContext.getApplicationInfo().uid, this.mContext.getApplicationInfo().targetSdkVersion);
        if (callback != null) {
            if (cancel != null) {
                if (cancel.isCanceled()) {
                    Slog.w(TAG, "authentication already canceled");
                    return;
                }
                cancel.setOnCancelListener(new OnAuthenticationCancelListener(crypto));
            }
            if (DEBUG_FINGER) {
                Slog.d(TAG, "FingerSensor:" + sensorId + " called authentication in user:" + userId + " by " + this.mContext.getOpPackageName());
            }
            if (this.mService != null) {
                try {
                    useHandler(handler);
                    this.mAuthenticationCallback = callback;
                    this.mCryptoObject = crypto;
                    long operationId = crypto != null ? crypto.getOpId() : 0L;
                    this.mService.authenticate(this.mToken, operationId, sensorId, userId, this.mServiceReceiver, this.mContext.getOpPackageName());
                } catch (RemoteException e) {
                    Slog.w(TAG, "Remote exception while authenticating: ", e);
                    callback.onAuthenticationError(1, getErrorString(this.mContext, 1, 0));
                }
            }
        } else {
            throw new IllegalArgumentException("Must supply an authentication callback");
        }
    }

    public void detectFingerprint(CancellationSignal cancel, FingerprintDetectionCallback callback, int userId) {
        if (this.mService != null) {
            if (cancel.isCanceled()) {
                Slog.w(TAG, "Detection already cancelled");
                return;
            }
            cancel.setOnCancelListener(new OnFingerprintDetectionCancelListener(this, null));
            this.mFingerprintDetectionCallback = callback;
            try {
                if (DEBUG_FINGER) {
                    Slog.d(TAG, "detectFingerprint in user:" + userId + " called by " + this.mContext.getOpPackageName());
                }
                this.mService.detectFingerprint(this.mToken, userId, this.mServiceReceiver, this.mContext.getOpPackageName());
            } catch (RemoteException e) {
                Slog.w(TAG, "Remote exception when requesting finger detect", e);
            }
        }
    }

    public void enroll(byte[] hardwareAuthToken, CancellationSignal cancel, int userId, EnrollmentCallback callback, int enrollReason) {
        if (userId == -2) {
            userId = getCurrentUserId();
        }
        if (callback != null) {
            if (cancel != null) {
                if (cancel.isCanceled()) {
                    Slog.w(TAG, "enrollment already canceled");
                    return;
                }
                cancel.setOnCancelListener(new OnEnrollCancelListener(this, null));
            }
            if (DEBUG_FINGER) {
                Slog.d(TAG, "enroll in user:" + userId + " called by " + this.mContext.getOpPackageName());
            }
            IFingerprintService iFingerprintService = this.mService;
            if (iFingerprintService != null) {
                try {
                    this.mEnrollmentCallback = callback;
                    iFingerprintService.enroll(this.mToken, hardwareAuthToken, userId, this.mServiceReceiver, this.mContext.getOpPackageName(), enrollReason);
                } catch (RemoteException e) {
                    Slog.w(TAG, "Remote exception in enroll: ", e);
                    callback.onEnrollmentError(1, getErrorString(this.mContext, 1, 0));
                }
            }
        } else {
            throw new IllegalArgumentException("Must supply an enrollment callback");
        }
    }

    public void generateChallenge(int sensorId, int userId, GenerateChallengeCallback callback) {
        if (DEBUG_FINGER) {
            Slog.d(TAG, "FingerSensor:" + sensorId + " called generateChallenge in user:" + userId + " by " + this.mContext.getOpPackageName());
        }
        IFingerprintService iFingerprintService = this.mService;
        if (iFingerprintService != null) {
            try {
                this.mGenerateChallengeCallback = callback;
                iFingerprintService.generateChallenge(this.mToken, sensorId, userId, this.mServiceReceiver, this.mContext.getOpPackageName());
            } catch (RemoteException e) {
                Slog.w(TAG, "Remote exception in generateChallenge: ", e);
            }
        }
    }

    public void generateChallenge(int userId, GenerateChallengeCallback callback) {
        FingerprintSensorPropertiesInternal sensorProps = getFirstFingerprintSensor();
        if (sensorProps == null) {
            Slog.e(TAG, "No sensors");
        } else {
            generateChallenge(sensorProps.sensorId, userId, callback);
        }
    }

    public void revokeChallenge(int userId, long challenge) {
        if (this.mService != null) {
            try {
                FingerprintSensorPropertiesInternal sensorProps = getFirstFingerprintSensor();
                if (sensorProps == null) {
                    Slog.e(TAG, "No sensors");
                    return;
                }
                if (DEBUG_FINGER) {
                    Slog.d(TAG, "FingerSensor:" + sensorProps.sensorId + " called revokeChallenge in user:" + userId + " by " + this.mContext.getOpPackageName());
                }
                this.mService.revokeChallenge(this.mToken, sensorProps.sensorId, userId, this.mContext.getOpPackageName(), challenge);
            } catch (RemoteException e) {
                Slog.w(TAG, "Remote exception in revokeChallenge: ", e);
            }
        }
    }

    public void resetLockout(int sensorId, int userId, byte[] hardwareAuthToken) {
        if (DEBUG_FINGER) {
            Slog.d(TAG, "resetLockout in user:" + userId + " called by " + this.mContext.getOpPackageName());
        }
        IFingerprintService iFingerprintService = this.mService;
        if (iFingerprintService != null) {
            try {
                iFingerprintService.resetLockout(this.mToken, sensorId, userId, hardwareAuthToken, this.mContext.getOpPackageName());
            } catch (RemoteException e) {
                Slog.w(TAG, "Remote exception in resetLockout: ", e);
            }
        }
    }

    public void remove(Fingerprint fp, int userId, RemovalCallback callback) {
        if (this.mService != null) {
            try {
                this.mRemovalCallback = callback;
                if (DEBUG_FINGER) {
                    Slog.d(TAG, "remove in user:" + userId + " called by " + this.mContext.getOpPackageName());
                }
                this.mRemoveTracker = new RemoveTracker(1, fp);
                this.mService.remove(this.mToken, fp.getBiometricId(), userId, this.mServiceReceiver, this.mContext.getOpPackageName());
            } catch (RemoteException e) {
                Slog.w(TAG, "Remote exception in remove: ", e);
            }
        }
    }

    public void removeAll(int userId, RemovalCallback callback) {
        if (this.mService != null) {
            try {
                this.mRemovalCallback = callback;
                this.mRemoveTracker = new RemoveTracker(2, null);
                this.mService.removeAll(this.mToken, userId, this.mServiceReceiver, this.mContext.getOpPackageName());
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void rename(int fpId, int userId, String newName) {
        if (DEBUG_FINGER) {
            Slog.d(TAG, "rename fpId:" + fpId + " in user:" + userId + " called by " + this.mContext.getOpPackageName());
        }
        IFingerprintService iFingerprintService = this.mService;
        if (iFingerprintService != null) {
            try {
                iFingerprintService.rename(fpId, userId, newName);
            } catch (RemoteException e) {
                Slog.w(TAG, "Remote exception in rename: ", e);
            }
        } else {
            Slog.w(TAG, "rename(): Service not connected!");
        }
    }

    public List<Fingerprint> getEnrolledFingerprints(int userId) {
        IFingerprintService iFingerprintService = this.mService;
        if (iFingerprintService == null) {
            return null;
        }
        try {
            return iFingerprintService.getEnrolledFingerprints(userId, this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            Slog.w(TAG, "Remote exception in getEnrolledFingerprints: ", e);
            return null;
        }
    }

    public List<Fingerprint> getEnrolledFingerprints() {
        return getEnrolledFingerprints(this.mContext.getUserId());
    }

    public boolean hasEnrolledTemplates() {
        return hasEnrolledFingerprints();
    }

    public boolean hasEnrolledTemplates(int userId) {
        return hasEnrolledFingerprints(userId);
    }

    public boolean hasEnrolledTemplatesForAnySensor(int userId, List<FingerprintSensorPropertiesInternal> sensors) {
        IFingerprintService iFingerprintService = this.mService;
        if (iFingerprintService == null) {
            Slog.w(TAG, "hasEnrolledTemplatesForAnySensor: no fingerprint service");
            return false;
        }
        try {
            return iFingerprintService.hasEnrolledTemplatesForAnySensor(userId, sensors, this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            Slog.w(TAG, "Remote exception in hasEnrolledTemplatesForAnySensor: ", e);
            return false;
        }
    }

    public void setUdfpsOverlayController(IUdfpsOverlayController controller) {
        IFingerprintService iFingerprintService = this.mService;
        if (iFingerprintService == null) {
            Slog.w(TAG, "setUdfpsOverlayController: no fingerprint service");
            return;
        }
        try {
            iFingerprintService.setUdfpsOverlayController(controller);
        } catch (RemoteException e) {
            Slog.w(TAG, "Remote exception in setUdfpsOverlayController: ", e);
        }
    }

    public void setSidefpsController(ISidefpsController controller) {
        IFingerprintService iFingerprintService = this.mService;
        if (iFingerprintService == null) {
            Slog.w(TAG, "setSidefpsController: no fingerprint service");
            return;
        }
        try {
            iFingerprintService.setSidefpsController(controller);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void registerFingerprintStateListener(FingerprintStateListener listener) {
        try {
            this.mService.registerFingerprintStateListener(listener);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void onPointerDown(int sensorId, int x, int y, float minor, float major) {
        IFingerprintService iFingerprintService = this.mService;
        if (iFingerprintService == null) {
            Slog.w(TAG, "onFingerDown: no fingerprint service");
            return;
        }
        try {
            iFingerprintService.onPointerDown(sensorId, x, y, minor, major);
        } catch (RemoteException e) {
            Slog.w(TAG, "Remote exception in onPointerDown: ", e);
        }
    }

    public void onPointerUp(int sensorId) {
        IFingerprintService iFingerprintService = this.mService;
        if (iFingerprintService == null) {
            Slog.w(TAG, "onFingerDown: no fingerprint service");
            return;
        }
        try {
            iFingerprintService.onPointerUp(sensorId);
        } catch (RemoteException e) {
            Slog.w(TAG, "Remote exception in onPointerUp: ", e);
        }
    }

    public void onUiReady(int sensorId) {
        IFingerprintService iFingerprintService = this.mService;
        if (iFingerprintService == null) {
            Slog.w(TAG, "onUiReady: no fingerprint service");
            return;
        }
        try {
            iFingerprintService.onUiReady(sensorId);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public boolean hasEnrolledFingerprints() {
        FrameworkStatsLog.write(356, 2, this.mContext.getApplicationInfo().uid, this.mContext.getApplicationInfo().targetSdkVersion);
        return hasEnrolledFingerprints(UserHandle.myUserId());
    }

    public boolean hasEnrolledFingerprints(int userId) {
        IFingerprintService iFingerprintService = this.mService;
        if (iFingerprintService == null) {
            return false;
        }
        try {
            return iFingerprintService.hasEnrolledFingerprintsDeprecated(userId, this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            Slog.w(TAG, "Remote exception in hasEnrolledFingerprints: ", e);
            return false;
        }
    }

    @Deprecated
    public boolean isHardwareDetected() {
        FrameworkStatsLog.write(356, 3, this.mContext.getApplicationInfo().uid, this.mContext.getApplicationInfo().targetSdkVersion);
        if (DEBUG_FINGER) {
            Slog.d(TAG, "isHardwareDetected called by " + this.mContext.getOpPackageName());
        }
        IFingerprintService iFingerprintService = this.mService;
        if (iFingerprintService != null) {
            try {
                return iFingerprintService.isHardwareDetectedDeprecated(this.mContext.getOpPackageName());
            } catch (RemoteException e) {
                Slog.w(TAG, "Remote exception in isHardwareDetected: ", e);
                return false;
            }
        } else {
            Slog.w(TAG, "isFingerprintHardwareDetected(): Service not connected!");
            return false;
        }
    }

    public List<FingerprintSensorPropertiesInternal> getSensorPropertiesInternal() {
        try {
            IFingerprintService iFingerprintService = this.mService;
            if (iFingerprintService == null) {
                return new ArrayList();
            }
            return iFingerprintService.getSensorPropertiesInternal(this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            Slog.w(TAG, "Remote exception in getSensorPropertiesInternal: ", e);
            return null;
        }
    }

    public boolean isPowerbuttonFps() {
        FingerprintSensorPropertiesInternal sensorProps = getFirstFingerprintSensor();
        return sensorProps.sensorType == 4;
    }

    public void addAuthenticatorsRegisteredCallback(IFingerprintAuthenticatorsRegisteredCallback callback) {
        IFingerprintService iFingerprintService = this.mService;
        if (iFingerprintService != null) {
            try {
                iFingerprintService.addAuthenticatorsRegisteredCallback(callback);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Slog.w(TAG, "addProvidersAvailableCallback(): Service not connected!");
        }
    }

    public void addLockoutResetCallback(LockoutResetCallback callback) {
        if (this.mService != null) {
            try {
                PowerManager powerManager = (PowerManager) this.mContext.getSystemService(PowerManager.class);
                this.mService.addLockoutResetCallback(new AnonymousClass1(powerManager, callback), this.mContext.getOpPackageName());
            } catch (RemoteException e) {
                Slog.w(TAG, "Remote exception in addLockoutResetCallback: ", e);
            }
        } else {
            Slog.w(TAG, "addLockoutResetCallback(): Service not connected!");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: android.hardware.fingerprint.FingerprintManager$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 extends IBiometricServiceLockoutResetCallback.Stub {
        final /* synthetic */ LockoutResetCallback val$callback;
        final /* synthetic */ PowerManager val$powerManager;

        AnonymousClass1(PowerManager powerManager, LockoutResetCallback lockoutResetCallback) {
            this.val$powerManager = powerManager;
            this.val$callback = lockoutResetCallback;
        }

        @Override // android.hardware.biometrics.IBiometricServiceLockoutResetCallback
        public void onLockoutReset(final int sensorId, IRemoteCallback serverCallback) throws RemoteException {
            try {
                final PowerManager.WakeLock wakeLock = this.val$powerManager.newWakeLock(1, "lockoutResetCallback");
                wakeLock.acquire();
                Handler handler = FingerprintManager.this.mHandler;
                final LockoutResetCallback lockoutResetCallback = this.val$callback;
                handler.post(new Runnable() { // from class: android.hardware.fingerprint.FingerprintManager$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        FingerprintManager.AnonymousClass1.lambda$onLockoutReset$0(FingerprintManager.LockoutResetCallback.this, sensorId, wakeLock);
                    }
                });
            } finally {
                serverCallback.sendResult(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onLockoutReset$0(LockoutResetCallback callback, int sensorId, PowerManager.WakeLock wakeLock) {
            try {
                callback.onLockoutReset(sensorId);
            } finally {
                wakeLock.release();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class MyHandler extends Handler {
        /* synthetic */ MyHandler(FingerprintManager x0, Context x1, AnonymousClass1 x2) {
            this(x1);
        }

        /* synthetic */ MyHandler(FingerprintManager x0, Looper x1, AnonymousClass1 x2) {
            this(x1);
        }

        private MyHandler(Context context) {
            super(context.getMainLooper());
        }

        private MyHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100:
                    FingerprintManager.this.sendEnrollResult((Fingerprint) msg.obj, msg.arg1);
                    return;
                case 101:
                    FingerprintManager.this.sendAcquiredResult(msg.arg1, msg.arg2);
                    return;
                case 102:
                    FingerprintManager fingerprintManager = FingerprintManager.this;
                    Fingerprint fingerprint = (Fingerprint) msg.obj;
                    int i = msg.arg1;
                    boolean z = true;
                    if (msg.arg2 != 1) {
                        z = false;
                    }
                    fingerprintManager.sendAuthenticatedSucceeded(fingerprint, i, z);
                    return;
                case 103:
                    FingerprintManager.this.sendAuthenticatedFailed();
                    return;
                case 104:
                    FingerprintManager.this.sendErrorResult(msg.arg1, msg.arg2);
                    return;
                case 105:
                    FingerprintManager.this.sendRemovedResult((Fingerprint) msg.obj, msg.arg1);
                    return;
                case 106:
                    FingerprintManager.this.sendChallengeGenerated(msg.arg1, msg.arg2, ((Long) msg.obj).longValue());
                    return;
                case 107:
                    FingerprintManager.this.sendFingerprintDetected(msg.arg1, msg.arg2, ((Boolean) msg.obj).booleanValue());
                    return;
                case 108:
                    FingerprintManager.this.sendUdfpsPointerDown(msg.arg1);
                    return;
                case 109:
                    FingerprintManager.this.sendUdfpsPointerUp(msg.arg1);
                    return;
                default:
                    Slog.w(FingerprintManager.TAG, "Unknown message: " + msg.what);
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendRemovedResult(Fingerprint fingerprint, int remaining) {
        boolean z = DEBUG_FINGER;
        if (z) {
            Slog.d(TAG, "sendRemovedResult callback start");
        }
        if (this.mRemovalCallback != null) {
            RemoveTracker removeTracker = this.mRemoveTracker;
            if (removeTracker == null) {
                Slog.w(TAG, "Removal tracker is null");
                return;
            }
            if (removeTracker.mRemoveRequest == 1) {
                if (fingerprint == null) {
                    Slog.e(TAG, "Received MSG_REMOVED, but fingerprint is null");
                    return;
                } else if (this.mRemoveTracker.mSingleFingerprint == null) {
                    Slog.e(TAG, "Missing fingerprint");
                    return;
                } else {
                    int fingerId = fingerprint.getBiometricId();
                    int reqFingerId = this.mRemoveTracker.mSingleFingerprint.getBiometricId();
                    if (!(reqFingerId == 0 || fingerId == 0 || fingerId == reqFingerId)) {
                        Slog.w(TAG, "Finger id didn't match: " + fingerId + " != " + reqFingerId);
                        return;
                    }
                }
            }
            this.mRemovalCallback.onRemovalSucceeded(fingerprint, remaining);
            if (z) {
                Slog.d(TAG, "sendRemovedResult finish");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEnrollResult(Fingerprint fp, int remaining) {
        if (this.mEnrollmentCallback != null) {
            if (DEBUG_FINGER) {
                Slog.d(TAG, "sendEnrollResult remaining:" + remaining);
            }
            this.mEnrollmentCallback.onEnrollmentProgress(remaining);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAuthenticatedSucceeded(Fingerprint fp, int userId, boolean isStrongBiometric) {
        boolean z = DEBUG_FINGER;
        if (z) {
            Slog.d(TAG, "sendAuthenticatedSucceeded callback start");
        }
        if (this.mAuthenticationCallback != null) {
            AuthenticationResult result = new AuthenticationResult(this.mCryptoObject, fp, userId, isStrongBiometric);
            this.mAuthenticationCallback.onAuthenticationSucceeded(result);
        }
        if (z) {
            Slog.d(TAG, "sendAuthenticatedSucceeded finish");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAuthenticatedFailed() {
        AuthenticationCallback authenticationCallback = this.mAuthenticationCallback;
        if (authenticationCallback != null) {
            authenticationCallback.onAuthenticationFailed();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAcquiredResult(int acquireInfo, int vendorCode) {
        boolean z = DEBUG_FINGER;
        if (z) {
            Slog.d(TAG, "sendAcquiredResult callback start");
        }
        AuthenticationCallback authenticationCallback = this.mAuthenticationCallback;
        if (authenticationCallback != null) {
            authenticationCallback.onAuthenticationAcquired(acquireInfo);
        }
        String msg = getAcquiredString(this.mContext, acquireInfo, vendorCode);
        if (msg != null) {
            int clientInfo = acquireInfo == 6 ? vendorCode + 1000 : acquireInfo;
            EnrollmentCallback enrollmentCallback = this.mEnrollmentCallback;
            if (enrollmentCallback != null) {
                enrollmentCallback.onEnrollmentHelp(clientInfo, msg);
            } else {
                AuthenticationCallback authenticationCallback2 = this.mAuthenticationCallback;
                if (!(authenticationCallback2 == null || acquireInfo == 7)) {
                    authenticationCallback2.onAuthenticationHelp(clientInfo, msg);
                }
            }
            if (z) {
                Slog.d(TAG, "sendAcquiredResult finish");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendErrorResult(int errMsgId, int vendorCode) {
        boolean z = DEBUG_FINGER;
        if (z) {
            Slog.d(TAG, "sendErrorResult callback start");
        }
        int clientErrMsgId = errMsgId == 8 ? vendorCode + 1000 : errMsgId;
        EnrollmentCallback enrollmentCallback = this.mEnrollmentCallback;
        if (enrollmentCallback != null) {
            enrollmentCallback.onEnrollmentError(clientErrMsgId, getErrorString(this.mContext, errMsgId, vendorCode));
        } else {
            AuthenticationCallback authenticationCallback = this.mAuthenticationCallback;
            if (authenticationCallback != null) {
                authenticationCallback.onAuthenticationError(clientErrMsgId, getErrorString(this.mContext, errMsgId, vendorCode));
            } else if (this.mRemovalCallback != null) {
                RemoveTracker removeTracker = this.mRemoveTracker;
                Fingerprint fp = removeTracker != null ? removeTracker.mSingleFingerprint : null;
                this.mRemovalCallback.onRemovalError(fp, clientErrMsgId, getErrorString(this.mContext, errMsgId, vendorCode));
            }
        }
        if (z) {
            Slog.d(TAG, "sendErrorResult finish");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendChallengeGenerated(int sensorId, int userId, long challenge) {
        GenerateChallengeCallback generateChallengeCallback = this.mGenerateChallengeCallback;
        if (generateChallengeCallback == null) {
            Slog.e(TAG, "sendChallengeGenerated, callback null");
        } else {
            generateChallengeCallback.onChallengeGenerated(sensorId, userId, challenge);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendFingerprintDetected(int sensorId, int userId, boolean isStrongBiometric) {
        FingerprintDetectionCallback fingerprintDetectionCallback = this.mFingerprintDetectionCallback;
        if (fingerprintDetectionCallback == null) {
            Slog.e(TAG, "sendFingerprintDetected, callback null");
        } else {
            fingerprintDetectionCallback.onFingerprintDetected(sensorId, userId, isStrongBiometric);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendUdfpsPointerDown(int sensorId) {
        AuthenticationCallback authenticationCallback = this.mAuthenticationCallback;
        if (authenticationCallback == null) {
            Slog.e(TAG, "sendUdfpsPointerDown, callback null");
        } else {
            authenticationCallback.onUdfpsPointerDown(sensorId);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendUdfpsPointerUp(int sensorId) {
        AuthenticationCallback authenticationCallback = this.mAuthenticationCallback;
        if (authenticationCallback == null) {
            Slog.e(TAG, "sendUdfpsPointerUp, callback null");
        } else {
            authenticationCallback.onUdfpsPointerUp(sensorId);
        }
    }

    public FingerprintManager(Context context, IFingerprintService service) {
        this.mContext = context;
        this.mService = service;
        if (service == null) {
            Slog.v(TAG, "FingerprintService was null");
        }
        this.mHandler = new MyHandler(this, context, (AnonymousClass1) null);
        mIFingerprintManagerExt = OplusFingerprintManagerExtPlugin.constructor.newInstance();
    }

    private int getCurrentUserId() {
        try {
            return ActivityManager.getService().getCurrentUser().id;
        } catch (RemoteException e) {
            Slog.w(TAG, "Remote exception in getCurrentUserId: ", e);
            return 0;
        }
    }

    private FingerprintSensorPropertiesInternal getFirstFingerprintSensor() {
        List<FingerprintSensorPropertiesInternal> allSensors = getSensorPropertiesInternal();
        if (allSensors.isEmpty()) {
            return null;
        }
        return allSensors.get(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelEnrollment() {
        if (DEBUG_FINGER) {
            Slog.d(TAG, "cancelEnrollment called by " + this.mContext.getOpPackageName());
        }
        IFingerprintService iFingerprintService = this.mService;
        if (iFingerprintService != null) {
            try {
                iFingerprintService.cancelEnrollment(this.mToken);
            } catch (RemoteException e) {
                Slog.w(TAG, "Remote exception in cancelEnrollment: ", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelAuthentication(android.hardware.biometrics.CryptoObject cryptoObject) {
        if (DEBUG_FINGER) {
            Slog.d(TAG, "cancelAuthentication called by " + this.mContext.getOpPackageName());
        }
        IFingerprintService iFingerprintService = this.mService;
        if (iFingerprintService != null) {
            try {
                iFingerprintService.cancelAuthentication(this.mToken, this.mContext.getOpPackageName());
            } catch (RemoteException e) {
                Slog.w(TAG, "Remote exception in cancelAuthentication: ", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelFingerprintDetect() {
        if (this.mService != null) {
            if (DEBUG_FINGER) {
                Slog.d(TAG, "cancelFingerprintDetect called by " + this.mContext.getOpPackageName());
            }
            try {
                this.mService.cancelFingerprintDetect(this.mToken, this.mContext.getOpPackageName());
            } catch (RemoteException e) {
                Slog.w(TAG, "Remote exception in cancelFingerprintDetect: ", e);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static String getErrorString(Context context, int errMsg, int vendorCode) {
        switch (errMsg) {
            case 1:
                return context.getString(R.string.fingerprint_error_hw_not_available);
            case 2:
                return context.getString(R.string.fingerprint_error_unable_to_process);
            case 3:
                return context.getString(R.string.fingerprint_error_timeout);
            case 4:
                return context.getString(R.string.fingerprint_error_no_space);
            case 5:
                return context.getString(R.string.fingerprint_error_canceled);
            case 7:
                return context.getString(R.string.fingerprint_error_lockout);
            case 8:
                String[] msgArray = context.getResources().getStringArray(R.array.fingerprint_error_vendor);
                if (vendorCode < msgArray.length) {
                    return msgArray[vendorCode];
                }
                break;
            case 9:
                return context.getString(R.string.fingerprint_error_lockout_permanent);
            case 10:
                return context.getString(R.string.fingerprint_error_user_canceled);
            case 11:
                return context.getString(R.string.fingerprint_error_no_fingerprints);
            case 12:
                return context.getString(R.string.fingerprint_error_hw_not_present);
            case 15:
                return context.getString(R.string.fingerprint_error_security_update_required);
            case 18:
                return context.getString(R.string.fingerprint_error_bad_calibration);
        }
        Slog.w(TAG, "Invalid error message: " + errMsg + ", " + vendorCode);
        return "";
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static String getAcquiredString(Context context, int acquireInfo, int vendorCode) {
        switch (acquireInfo) {
            case 0:
                return null;
            case 1:
                return context.getString(R.string.fingerprint_acquired_partial);
            case 2:
                return context.getString(R.string.fingerprint_acquired_insufficient);
            case 3:
                return context.getString(R.string.fingerprint_acquired_imager_dirty);
            case 4:
                return context.getString(R.string.fingerprint_acquired_too_slow);
            case 5:
                return context.getString(R.string.fingerprint_acquired_too_fast);
            case 6:
                String[] msgArray = context.getResources().getStringArray(R.array.fingerprint_acquired_vendor);
                if (vendorCode < msgArray.length) {
                    return msgArray[vendorCode];
                }
                break;
            case 7:
                return null;
            case 9:
                return context.getString(R.string.fingerprint_acquired_immobile);
            case 10:
                return context.getString(R.string.fingerprint_acquired_too_bright);
        }
        String mAcquireString = mIFingerprintManagerExt.getAcquiredString(acquireInfo, vendorCode);
        if (mAcquireString != null) {
            return mAcquireString;
        }
        Slog.w(TAG, "Invalid acquired message: " + acquireInfo + ", " + vendorCode);
        return null;
    }
}
