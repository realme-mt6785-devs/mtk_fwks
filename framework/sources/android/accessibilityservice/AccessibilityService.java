package android.accessibilityservice;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.accessibilityservice.IAccessibilityServiceClient;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ParceledListSlice;
import android.graphics.ColorSpace;
import android.graphics.ParcelableColorSpace;
import android.graphics.Region;
import android.hardware.HardwareBuffer;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Slog;
import android.util.SparseArray;
import android.view.Display;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.WindowManagerImpl;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityInteractionClient;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import com.android.internal.os.HandlerCaller;
import com.android.internal.os.SomeArgs;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public abstract class AccessibilityService extends Service {
    public static final int ACCESSIBILITY_TAKE_SCREENSHOT_REQUEST_INTERVAL_TIMES_MS = 333;
    public static final int ERROR_TAKE_SCREENSHOT_INTERNAL_ERROR = 1;
    public static final int ERROR_TAKE_SCREENSHOT_INTERVAL_TIME_SHORT = 3;
    public static final int ERROR_TAKE_SCREENSHOT_INVALID_DISPLAY = 4;
    public static final int ERROR_TAKE_SCREENSHOT_NO_ACCESSIBILITY_ACCESS = 2;
    public static final int GESTURE_2_FINGER_DOUBLE_TAP = 20;
    public static final int GESTURE_2_FINGER_DOUBLE_TAP_AND_HOLD = 40;
    public static final int GESTURE_2_FINGER_SINGLE_TAP = 19;
    public static final int GESTURE_2_FINGER_SWIPE_DOWN = 26;
    public static final int GESTURE_2_FINGER_SWIPE_LEFT = 27;
    public static final int GESTURE_2_FINGER_SWIPE_RIGHT = 28;
    public static final int GESTURE_2_FINGER_SWIPE_UP = 25;
    public static final int GESTURE_2_FINGER_TRIPLE_TAP = 21;
    public static final int GESTURE_2_FINGER_TRIPLE_TAP_AND_HOLD = 43;
    public static final int GESTURE_3_FINGER_DOUBLE_TAP = 23;
    public static final int GESTURE_3_FINGER_DOUBLE_TAP_AND_HOLD = 41;
    public static final int GESTURE_3_FINGER_SINGLE_TAP = 22;
    public static final int GESTURE_3_FINGER_SINGLE_TAP_AND_HOLD = 44;
    public static final int GESTURE_3_FINGER_SWIPE_DOWN = 30;
    public static final int GESTURE_3_FINGER_SWIPE_LEFT = 31;
    public static final int GESTURE_3_FINGER_SWIPE_RIGHT = 32;
    public static final int GESTURE_3_FINGER_SWIPE_UP = 29;
    public static final int GESTURE_3_FINGER_TRIPLE_TAP = 24;
    public static final int GESTURE_3_FINGER_TRIPLE_TAP_AND_HOLD = 45;
    public static final int GESTURE_4_FINGER_DOUBLE_TAP = 38;
    public static final int GESTURE_4_FINGER_DOUBLE_TAP_AND_HOLD = 42;
    public static final int GESTURE_4_FINGER_SINGLE_TAP = 37;
    public static final int GESTURE_4_FINGER_SWIPE_DOWN = 34;
    public static final int GESTURE_4_FINGER_SWIPE_LEFT = 35;
    public static final int GESTURE_4_FINGER_SWIPE_RIGHT = 36;
    public static final int GESTURE_4_FINGER_SWIPE_UP = 33;
    public static final int GESTURE_4_FINGER_TRIPLE_TAP = 39;
    public static final int GESTURE_DOUBLE_TAP = 17;
    public static final int GESTURE_DOUBLE_TAP_AND_HOLD = 18;
    public static final int GESTURE_PASSTHROUGH = -1;
    public static final int GESTURE_SWIPE_DOWN = 2;
    public static final int GESTURE_SWIPE_DOWN_AND_LEFT = 15;
    public static final int GESTURE_SWIPE_DOWN_AND_RIGHT = 16;
    public static final int GESTURE_SWIPE_DOWN_AND_UP = 8;
    public static final int GESTURE_SWIPE_LEFT = 3;
    public static final int GESTURE_SWIPE_LEFT_AND_DOWN = 10;
    public static final int GESTURE_SWIPE_LEFT_AND_RIGHT = 5;
    public static final int GESTURE_SWIPE_LEFT_AND_UP = 9;
    public static final int GESTURE_SWIPE_RIGHT = 4;
    public static final int GESTURE_SWIPE_RIGHT_AND_DOWN = 12;
    public static final int GESTURE_SWIPE_RIGHT_AND_LEFT = 6;
    public static final int GESTURE_SWIPE_RIGHT_AND_UP = 11;
    public static final int GESTURE_SWIPE_UP = 1;
    public static final int GESTURE_SWIPE_UP_AND_DOWN = 7;
    public static final int GESTURE_SWIPE_UP_AND_LEFT = 13;
    public static final int GESTURE_SWIPE_UP_AND_RIGHT = 14;
    public static final int GESTURE_TOUCH_EXPLORATION = -2;
    public static final int GESTURE_UNKNOWN = 0;
    public static final int GLOBAL_ACTION_ACCESSIBILITY_ALL_APPS = 14;
    public static final int GLOBAL_ACTION_ACCESSIBILITY_BUTTON = 11;
    public static final int GLOBAL_ACTION_ACCESSIBILITY_BUTTON_CHOOSER = 12;
    public static final int GLOBAL_ACTION_ACCESSIBILITY_SHORTCUT = 13;
    public static final int GLOBAL_ACTION_BACK = 1;
    public static final int GLOBAL_ACTION_DISMISS_NOTIFICATION_SHADE = 15;
    public static final int GLOBAL_ACTION_HOME = 2;
    public static final int GLOBAL_ACTION_KEYCODE_HEADSETHOOK = 10;
    public static final int GLOBAL_ACTION_LOCK_SCREEN = 8;
    public static final int GLOBAL_ACTION_NOTIFICATIONS = 4;
    public static final int GLOBAL_ACTION_POWER_DIALOG = 6;
    public static final int GLOBAL_ACTION_QUICK_SETTINGS = 5;
    public static final int GLOBAL_ACTION_RECENTS = 3;
    public static final int GLOBAL_ACTION_TAKE_SCREENSHOT = 9;
    public static final int GLOBAL_ACTION_TOGGLE_SPLIT_SCREEN = 7;
    public static final String KEY_ACCESSIBILITY_SCREENSHOT_COLORSPACE = "screenshot_colorSpace";
    public static final String KEY_ACCESSIBILITY_SCREENSHOT_HARDWAREBUFFER = "screenshot_hardwareBuffer";
    public static final String KEY_ACCESSIBILITY_SCREENSHOT_STATUS = "screenshot_status";
    public static final String KEY_ACCESSIBILITY_SCREENSHOT_TIMESTAMP = "screenshot_timestamp";
    private static final String LOG_TAG = "AccessibilityService";
    public static final String SERVICE_INTERFACE = "android.accessibilityservice.AccessibilityService";
    public static final String SERVICE_META_DATA = "android.accessibilityservice";
    public static final int SHOW_MODE_AUTO = 0;
    public static final int SHOW_MODE_HARD_KEYBOARD_ORIGINAL_VALUE = 536870912;
    public static final int SHOW_MODE_HARD_KEYBOARD_OVERRIDDEN = 1073741824;
    public static final int SHOW_MODE_HIDDEN = 1;
    public static final int SHOW_MODE_IGNORE_HARD_KEYBOARD = 2;
    public static final int SHOW_MODE_MASK = 3;
    public static final int TAKE_SCREENSHOT_SUCCESS = 0;
    private FingerprintGestureController mFingerprintGestureController;
    private SparseArray<GestureResultCallbackInfo> mGestureStatusCallbackInfos;
    private int mGestureStatusCallbackSequence;
    private AccessibilityServiceInfo mInfo;
    private SoftKeyboardController mSoftKeyboardController;
    private WindowManager mWindowManager;
    private IBinder mWindowToken;
    private int mConnectionId = -1;
    private final SparseArray<MagnificationController> mMagnificationControllers = new SparseArray<>(0);
    private final SparseArray<AccessibilityButtonController> mAccessibilityButtonControllers = new SparseArray<>(0);
    private final Object mLock = new Object();

    /* loaded from: classes.dex */
    public interface Callbacks {
        void init(int i, IBinder iBinder);

        void onAccessibilityButtonAvailabilityChanged(boolean z);

        void onAccessibilityButtonClicked(int i);

        void onAccessibilityEvent(AccessibilityEvent accessibilityEvent);

        void onFingerprintCapturingGesturesChanged(boolean z);

        void onFingerprintGesture(int i);

        boolean onGesture(AccessibilityGestureEvent accessibilityGestureEvent);

        void onInterrupt();

        boolean onKeyEvent(KeyEvent keyEvent);

        void onMagnificationChanged(int i, Region region, float f, float f2, float f3);

        void onPerformGestureResult(int i, boolean z);

        void onServiceConnected();

        void onSoftKeyboardShowModeChanged(int i);

        void onSystemActionsChanged();
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ScreenshotErrorCode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SoftKeyboardShowMode {
    }

    /* loaded from: classes.dex */
    public interface TakeScreenshotCallback {
        void onFailure(int i);

        void onSuccess(ScreenshotResult screenshotResult);
    }

    public abstract void onAccessibilityEvent(AccessibilityEvent accessibilityEvent);

    public abstract void onInterrupt();

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchServiceConnected() {
        synchronized (this.mLock) {
            for (int i = 0; i < this.mMagnificationControllers.size(); i++) {
                this.mMagnificationControllers.valueAt(i).onServiceConnectedLocked();
            }
        }
        SoftKeyboardController softKeyboardController = this.mSoftKeyboardController;
        if (softKeyboardController != null) {
            softKeyboardController.onServiceConnected();
        }
        onServiceConnected();
    }

    protected void onServiceConnected() {
    }

    @Deprecated
    protected boolean onGesture(int gestureId) {
        return false;
    }

    public boolean onGesture(AccessibilityGestureEvent gestureEvent) {
        if (gestureEvent.getDisplayId() != 0) {
            return false;
        }
        onGesture(gestureEvent.getGestureId());
        return false;
    }

    protected boolean onKeyEvent(KeyEvent event) {
        return false;
    }

    public List<AccessibilityWindowInfo> getWindows() {
        return AccessibilityInteractionClient.getInstance(this).getWindows(this.mConnectionId);
    }

    public final SparseArray<List<AccessibilityWindowInfo>> getWindowsOnAllDisplays() {
        return AccessibilityInteractionClient.getInstance(this).getWindowsOnAllDisplays(this.mConnectionId);
    }

    public AccessibilityNodeInfo getRootInActiveWindow() {
        return AccessibilityInteractionClient.getInstance(this).getRootInActiveWindow(this.mConnectionId);
    }

    public final void disableSelf() {
        AccessibilityInteractionClient.getInstance(this);
        IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
        if (connection != null) {
            try {
                connection.disableSelf();
            } catch (RemoteException re) {
                throw new RuntimeException(re);
            }
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Context createDisplayContext(Display display) {
        Context context = super.createDisplayContext(display);
        int displayId = display.getDisplayId();
        setDefaultTokenInternal(context, displayId);
        return context;
    }

    private void setDefaultTokenInternal(Context context, int displayId) {
        WindowManagerImpl wm = (WindowManagerImpl) context.getSystemService(Context.WINDOW_SERVICE);
        AccessibilityInteractionClient.getInstance(this);
        IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
        IBinder token = null;
        if (connection != null) {
            synchronized (this.mLock) {
                try {
                    token = connection.getOverlayWindowToken(displayId);
                } catch (RemoteException re) {
                    Log.w(LOG_TAG, "Failed to get window token", re);
                    re.rethrowFromSystemServer();
                }
            }
            wm.setDefaultToken(token);
        }
    }

    public final MagnificationController getMagnificationController() {
        return getMagnificationController(0);
    }

    public final MagnificationController getMagnificationController(int displayId) {
        MagnificationController controller;
        synchronized (this.mLock) {
            controller = this.mMagnificationControllers.get(displayId);
            if (controller == null) {
                controller = new MagnificationController(this, this.mLock, displayId);
                this.mMagnificationControllers.put(displayId, controller);
            }
        }
        return controller;
    }

    public final FingerprintGestureController getFingerprintGestureController() {
        if (this.mFingerprintGestureController == null) {
            AccessibilityInteractionClient.getInstance(this);
            this.mFingerprintGestureController = new FingerprintGestureController(AccessibilityInteractionClient.getConnection(this.mConnectionId));
        }
        return this.mFingerprintGestureController;
    }

    public final boolean dispatchGesture(GestureDescription gesture, GestureResultCallback callback, Handler handler) {
        AccessibilityInteractionClient.getInstance(this);
        IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
        if (connection == null) {
            return false;
        }
        int sampleTimeMs = calculateGestureSampleTimeMs(gesture.getDisplayId());
        List<GestureDescription.GestureStep> steps = GestureDescription.MotionEventGenerator.getGestureStepsFromGestureDescription(gesture, sampleTimeMs);
        try {
            synchronized (this.mLock) {
                this.mGestureStatusCallbackSequence++;
                if (callback != null) {
                    if (this.mGestureStatusCallbackInfos == null) {
                        this.mGestureStatusCallbackInfos = new SparseArray<>();
                    }
                    GestureResultCallbackInfo callbackInfo = new GestureResultCallbackInfo(gesture, callback, handler);
                    this.mGestureStatusCallbackInfos.put(this.mGestureStatusCallbackSequence, callbackInfo);
                }
                connection.dispatchGesture(this.mGestureStatusCallbackSequence, new ParceledListSlice(steps), gesture.getDisplayId());
            }
            return true;
        } catch (RemoteException re) {
            throw new RuntimeException(re);
        }
    }

    private int calculateGestureSampleTimeMs(int displayId) {
        Display display;
        int sampleTimeMs;
        if (getApplicationInfo().targetSdkVersion > 29 && (display = ((DisplayManager) getSystemService(DisplayManager.class)).getDisplay(displayId)) != null && (sampleTimeMs = (int) (1000 / display.getRefreshRate())) >= 1) {
            return sampleTimeMs;
        }
        return 100;
    }

    void onPerformGestureResult(int sequence, final boolean completedSuccessfully) {
        final GestureResultCallbackInfo callbackInfo;
        if (this.mGestureStatusCallbackInfos != null) {
            synchronized (this.mLock) {
                callbackInfo = this.mGestureStatusCallbackInfos.get(sequence);
                this.mGestureStatusCallbackInfos.remove(sequence);
            }
            if (callbackInfo != null && callbackInfo.gestureDescription != null && callbackInfo.callback != null) {
                if (callbackInfo.handler != null) {
                    callbackInfo.handler.post(new Runnable() { // from class: android.accessibilityservice.AccessibilityService.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (completedSuccessfully) {
                                callbackInfo.callback.onCompleted(callbackInfo.gestureDescription);
                            } else {
                                callbackInfo.callback.onCancelled(callbackInfo.gestureDescription);
                            }
                        }
                    });
                } else if (completedSuccessfully) {
                    callbackInfo.callback.onCompleted(callbackInfo.gestureDescription);
                } else {
                    callbackInfo.callback.onCancelled(callbackInfo.gestureDescription);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMagnificationChanged(int displayId, Region region, float scale, float centerX, float centerY) {
        MagnificationController controller;
        synchronized (this.mLock) {
            controller = this.mMagnificationControllers.get(displayId);
        }
        if (controller != null) {
            controller.dispatchMagnificationChanged(region, scale, centerX, centerY);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFingerprintCapturingGesturesChanged(boolean active) {
        getFingerprintGestureController().onGestureDetectionActiveChanged(active);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFingerprintGesture(int gesture) {
        getFingerprintGestureController().onGesture(gesture);
    }

    /* loaded from: classes.dex */
    public static final class MagnificationController {
        private final int mDisplayId;
        private ArrayMap<OnMagnificationChangedListener, Handler> mListeners;
        private final Object mLock;
        private final AccessibilityService mService;

        /* loaded from: classes.dex */
        public interface OnMagnificationChangedListener {
            void onMagnificationChanged(MagnificationController magnificationController, Region region, float f, float f2, float f3);
        }

        MagnificationController(AccessibilityService service, Object lock, int displayId) {
            this.mService = service;
            this.mLock = lock;
            this.mDisplayId = displayId;
        }

        void onServiceConnectedLocked() {
            ArrayMap<OnMagnificationChangedListener, Handler> arrayMap = this.mListeners;
            if (arrayMap != null && !arrayMap.isEmpty()) {
                setMagnificationCallbackEnabled(true);
            }
        }

        public void addListener(OnMagnificationChangedListener listener) {
            addListener(listener, null);
        }

        public void addListener(OnMagnificationChangedListener listener, Handler handler) {
            synchronized (this.mLock) {
                if (this.mListeners == null) {
                    this.mListeners = new ArrayMap<>();
                }
                boolean shouldEnableCallback = this.mListeners.isEmpty();
                this.mListeners.put(listener, handler);
                if (shouldEnableCallback) {
                    setMagnificationCallbackEnabled(true);
                }
            }
        }

        public boolean removeListener(OnMagnificationChangedListener listener) {
            boolean hasKey;
            if (this.mListeners == null) {
                return false;
            }
            synchronized (this.mLock) {
                int keyIndex = this.mListeners.indexOfKey(listener);
                hasKey = keyIndex >= 0;
                if (hasKey) {
                    this.mListeners.removeAt(keyIndex);
                }
                if (hasKey && this.mListeners.isEmpty()) {
                    setMagnificationCallbackEnabled(false);
                }
            }
            return hasKey;
        }

        private void setMagnificationCallbackEnabled(boolean enabled) {
            AccessibilityInteractionClient.getInstance(this.mService);
            IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
            if (connection != null) {
                try {
                    connection.setMagnificationCallbackEnabled(this.mDisplayId, enabled);
                } catch (RemoteException re) {
                    throw new RuntimeException(re);
                }
            }
        }

        void dispatchMagnificationChanged(final Region region, final float scale, final float centerX, final float centerY) {
            synchronized (this.mLock) {
                ArrayMap<OnMagnificationChangedListener, Handler> arrayMap = this.mListeners;
                if (arrayMap != null && !arrayMap.isEmpty()) {
                    ArrayMap<OnMagnificationChangedListener, Handler> entries = new ArrayMap<>(this.mListeners);
                    int count = entries.size();
                    for (int i = 0; i < count; i++) {
                        final OnMagnificationChangedListener listener = entries.keyAt(i);
                        Handler handler = entries.valueAt(i);
                        if (handler != null) {
                            handler.post(new Runnable() { // from class: android.accessibilityservice.AccessibilityService.MagnificationController.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    listener.onMagnificationChanged(MagnificationController.this, region, scale, centerX, centerY);
                                }
                            });
                        } else {
                            listener.onMagnificationChanged(this, region, scale, centerX, centerY);
                        }
                    }
                    return;
                }
                Slog.d(AccessibilityService.LOG_TAG, "Received magnification changed callback with no listeners registered!");
                setMagnificationCallbackEnabled(false);
            }
        }

        public float getScale() {
            AccessibilityInteractionClient.getInstance(this.mService);
            IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
            if (connection == null) {
                return 1.0f;
            }
            try {
                return connection.getMagnificationScale(this.mDisplayId);
            } catch (RemoteException re) {
                Log.w(AccessibilityService.LOG_TAG, "Failed to obtain scale", re);
                re.rethrowFromSystemServer();
                return 1.0f;
            }
        }

        public float getCenterX() {
            AccessibilityInteractionClient.getInstance(this.mService);
            IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
            if (connection == null) {
                return 0.0f;
            }
            try {
                return connection.getMagnificationCenterX(this.mDisplayId);
            } catch (RemoteException re) {
                Log.w(AccessibilityService.LOG_TAG, "Failed to obtain center X", re);
                re.rethrowFromSystemServer();
                return 0.0f;
            }
        }

        public float getCenterY() {
            AccessibilityInteractionClient.getInstance(this.mService);
            IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
            if (connection == null) {
                return 0.0f;
            }
            try {
                return connection.getMagnificationCenterY(this.mDisplayId);
            } catch (RemoteException re) {
                Log.w(AccessibilityService.LOG_TAG, "Failed to obtain center Y", re);
                re.rethrowFromSystemServer();
                return 0.0f;
            }
        }

        public Region getMagnificationRegion() {
            AccessibilityInteractionClient.getInstance(this.mService);
            IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
            if (connection != null) {
                try {
                    return connection.getMagnificationRegion(this.mDisplayId);
                } catch (RemoteException re) {
                    Log.w(AccessibilityService.LOG_TAG, "Failed to obtain magnified region", re);
                    re.rethrowFromSystemServer();
                }
            }
            return Region.obtain();
        }

        public boolean reset(boolean animate) {
            AccessibilityInteractionClient.getInstance(this.mService);
            IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
            if (connection == null) {
                return false;
            }
            try {
                return connection.resetMagnification(this.mDisplayId, animate);
            } catch (RemoteException re) {
                Log.w(AccessibilityService.LOG_TAG, "Failed to reset", re);
                re.rethrowFromSystemServer();
                return false;
            }
        }

        public boolean setScale(float scale, boolean animate) {
            AccessibilityInteractionClient.getInstance(this.mService);
            IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
            if (connection == null) {
                return false;
            }
            try {
                return connection.setMagnificationScaleAndCenter(this.mDisplayId, scale, Float.NaN, Float.NaN, animate);
            } catch (RemoteException re) {
                Log.w(AccessibilityService.LOG_TAG, "Failed to set scale", re);
                re.rethrowFromSystemServer();
                return false;
            }
        }

        public boolean setCenter(float centerX, float centerY, boolean animate) {
            AccessibilityInteractionClient.getInstance(this.mService);
            IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
            if (connection == null) {
                return false;
            }
            try {
                return connection.setMagnificationScaleAndCenter(this.mDisplayId, Float.NaN, centerX, centerY, animate);
            } catch (RemoteException re) {
                Log.w(AccessibilityService.LOG_TAG, "Failed to set center", re);
                re.rethrowFromSystemServer();
                return false;
            }
        }
    }

    public final SoftKeyboardController getSoftKeyboardController() {
        SoftKeyboardController softKeyboardController;
        synchronized (this.mLock) {
            if (this.mSoftKeyboardController == null) {
                this.mSoftKeyboardController = new SoftKeyboardController(this, this.mLock);
            }
            softKeyboardController = this.mSoftKeyboardController;
        }
        return softKeyboardController;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSoftKeyboardShowModeChanged(int showMode) {
        SoftKeyboardController softKeyboardController = this.mSoftKeyboardController;
        if (softKeyboardController != null) {
            softKeyboardController.dispatchSoftKeyboardShowModeChanged(showMode);
        }
    }

    /* loaded from: classes.dex */
    public static final class SoftKeyboardController {
        private ArrayMap<OnShowModeChangedListener, Handler> mListeners;
        private final Object mLock;
        private final AccessibilityService mService;

        /* loaded from: classes.dex */
        public interface OnShowModeChangedListener {
            void onShowModeChanged(SoftKeyboardController softKeyboardController, int i);
        }

        SoftKeyboardController(AccessibilityService service, Object lock) {
            this.mService = service;
            this.mLock = lock;
        }

        void onServiceConnected() {
            synchronized (this.mLock) {
                ArrayMap<OnShowModeChangedListener, Handler> arrayMap = this.mListeners;
                if (arrayMap != null && !arrayMap.isEmpty()) {
                    setSoftKeyboardCallbackEnabled(true);
                }
            }
        }

        public void addOnShowModeChangedListener(OnShowModeChangedListener listener) {
            addOnShowModeChangedListener(listener, null);
        }

        public void addOnShowModeChangedListener(OnShowModeChangedListener listener, Handler handler) {
            synchronized (this.mLock) {
                if (this.mListeners == null) {
                    this.mListeners = new ArrayMap<>();
                }
                boolean shouldEnableCallback = this.mListeners.isEmpty();
                this.mListeners.put(listener, handler);
                if (shouldEnableCallback) {
                    setSoftKeyboardCallbackEnabled(true);
                }
            }
        }

        public boolean removeOnShowModeChangedListener(OnShowModeChangedListener listener) {
            boolean hasKey;
            if (this.mListeners == null) {
                return false;
            }
            synchronized (this.mLock) {
                int keyIndex = this.mListeners.indexOfKey(listener);
                hasKey = keyIndex >= 0;
                if (hasKey) {
                    this.mListeners.removeAt(keyIndex);
                }
                if (hasKey && this.mListeners.isEmpty()) {
                    setSoftKeyboardCallbackEnabled(false);
                }
            }
            return hasKey;
        }

        private void setSoftKeyboardCallbackEnabled(boolean enabled) {
            AccessibilityInteractionClient.getInstance(this.mService);
            IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
            if (connection != null) {
                try {
                    connection.setSoftKeyboardCallbackEnabled(enabled);
                } catch (RemoteException re) {
                    throw new RuntimeException(re);
                }
            }
        }

        void dispatchSoftKeyboardShowModeChanged(final int showMode) {
            synchronized (this.mLock) {
                ArrayMap<OnShowModeChangedListener, Handler> arrayMap = this.mListeners;
                if (arrayMap != null && !arrayMap.isEmpty()) {
                    ArrayMap<OnShowModeChangedListener, Handler> entries = new ArrayMap<>(this.mListeners);
                    int count = entries.size();
                    for (int i = 0; i < count; i++) {
                        final OnShowModeChangedListener listener = entries.keyAt(i);
                        Handler handler = entries.valueAt(i);
                        if (handler != null) {
                            handler.post(new Runnable() { // from class: android.accessibilityservice.AccessibilityService.SoftKeyboardController.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    listener.onShowModeChanged(SoftKeyboardController.this, showMode);
                                }
                            });
                        } else {
                            listener.onShowModeChanged(this, showMode);
                        }
                    }
                    return;
                }
                Slog.w(AccessibilityService.LOG_TAG, "Received soft keyboard show mode changed callback with no listeners registered!");
                setSoftKeyboardCallbackEnabled(false);
            }
        }

        public int getShowMode() {
            AccessibilityInteractionClient.getInstance(this.mService);
            IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
            if (connection == null) {
                return 0;
            }
            try {
                return connection.getSoftKeyboardShowMode();
            } catch (RemoteException re) {
                Log.w(AccessibilityService.LOG_TAG, "Failed to set soft keyboard behavior", re);
                re.rethrowFromSystemServer();
                return 0;
            }
        }

        public boolean setShowMode(int showMode) {
            AccessibilityInteractionClient.getInstance(this.mService);
            IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
            if (connection == null) {
                return false;
            }
            try {
                return connection.setSoftKeyboardShowMode(showMode);
            } catch (RemoteException re) {
                Log.w(AccessibilityService.LOG_TAG, "Failed to set soft keyboard behavior", re);
                re.rethrowFromSystemServer();
                return false;
            }
        }

        public boolean switchToInputMethod(String imeId) {
            AccessibilityInteractionClient.getInstance(this.mService);
            IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mService.mConnectionId);
            if (connection == null) {
                return false;
            }
            try {
                return connection.switchToInputMethod(imeId);
            } catch (RemoteException re) {
                throw new RuntimeException(re);
            }
        }
    }

    public final AccessibilityButtonController getAccessibilityButtonController() {
        return getAccessibilityButtonController(0);
    }

    public final AccessibilityButtonController getAccessibilityButtonController(int displayId) {
        AccessibilityButtonController controller;
        synchronized (this.mLock) {
            controller = this.mAccessibilityButtonControllers.get(displayId);
            if (controller == null) {
                AccessibilityInteractionClient.getInstance(this);
                controller = new AccessibilityButtonController(AccessibilityInteractionClient.getConnection(this.mConnectionId));
                this.mAccessibilityButtonControllers.put(displayId, controller);
            }
        }
        return controller;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAccessibilityButtonClicked(int displayId) {
        getAccessibilityButtonController(displayId).dispatchAccessibilityButtonClicked();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAccessibilityButtonAvailabilityChanged(boolean available) {
        getAccessibilityButtonController().dispatchAccessibilityButtonAvailabilityChanged(available);
    }

    public void onSystemActionsChanged() {
    }

    public final List<AccessibilityNodeInfo.AccessibilityAction> getSystemActions() {
        AccessibilityInteractionClient.getInstance(this);
        IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
        if (connection != null) {
            try {
                return connection.getSystemActions();
            } catch (RemoteException re) {
                Log.w(LOG_TAG, "Error while calling getSystemActions", re);
                re.rethrowFromSystemServer();
            }
        }
        return Collections.emptyList();
    }

    public final boolean performGlobalAction(int action) {
        AccessibilityInteractionClient.getInstance(this);
        IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
        if (connection == null) {
            return false;
        }
        try {
            return connection.performGlobalAction(action);
        } catch (RemoteException re) {
            Log.w(LOG_TAG, "Error while calling performGlobalAction", re);
            re.rethrowFromSystemServer();
            return false;
        }
    }

    public AccessibilityNodeInfo findFocus(int focus) {
        return AccessibilityInteractionClient.getInstance(this).findFocus(this.mConnectionId, -2, AccessibilityNodeInfo.ROOT_NODE_ID, focus);
    }

    public final AccessibilityServiceInfo getServiceInfo() {
        AccessibilityInteractionClient.getInstance(this);
        IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
        if (connection == null) {
            return null;
        }
        try {
            return connection.getServiceInfo();
        } catch (RemoteException re) {
            Log.w(LOG_TAG, "Error while getting AccessibilityServiceInfo", re);
            re.rethrowFromSystemServer();
            return null;
        }
    }

    public final void setServiceInfo(AccessibilityServiceInfo info) {
        this.mInfo = info;
        sendServiceInfo();
    }

    private void sendServiceInfo() {
        AccessibilityInteractionClient.getInstance(this);
        IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
        AccessibilityServiceInfo accessibilityServiceInfo = this.mInfo;
        if (accessibilityServiceInfo != null && connection != null) {
            try {
                connection.setServiceInfo(accessibilityServiceInfo);
                this.mInfo = null;
                AccessibilityInteractionClient.getInstance(this).clearCache();
            } catch (RemoteException re) {
                Log.w(LOG_TAG, "Error while setting AccessibilityServiceInfo", re);
                re.rethrowFromSystemServer();
            }
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String name) {
        if (getBaseContext() == null) {
            throw new IllegalStateException("System services not available to Activities before onCreate()");
        } else if (!Context.WINDOW_SERVICE.equals(name)) {
            return super.getSystemService(name);
        } else {
            if (this.mWindowManager == null) {
                this.mWindowManager = (WindowManager) getBaseContext().getSystemService(name);
            }
            return this.mWindowManager;
        }
    }

    public void takeScreenshot(int displayId, final Executor executor, final TakeScreenshotCallback callback) {
        Preconditions.checkNotNull(executor, "executor cannot be null");
        Preconditions.checkNotNull(callback, "callback cannot be null");
        AccessibilityInteractionClient.getInstance(this);
        IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
        if (connection == null) {
            sendScreenshotFailure(1, executor, callback);
            return;
        }
        try {
            connection.takeScreenshot(displayId, new RemoteCallback(new RemoteCallback.OnResultListener() { // from class: android.accessibilityservice.AccessibilityService$$ExternalSyntheticLambda0
                @Override // android.os.RemoteCallback.OnResultListener
                public final void onResult(Bundle bundle) {
                    AccessibilityService.this.lambda$takeScreenshot$0$AccessibilityService(executor, callback, bundle);
                }
            }));
        } catch (RemoteException re) {
            throw new RuntimeException(re);
        }
    }

    public /* synthetic */ void lambda$takeScreenshot$0$AccessibilityService(Executor executor, TakeScreenshotCallback callback, Bundle result) {
        int status = result.getInt(KEY_ACCESSIBILITY_SCREENSHOT_STATUS);
        if (status != 0) {
            sendScreenshotFailure(status, executor, callback);
            return;
        }
        HardwareBuffer hardwareBuffer = (HardwareBuffer) result.getParcelable(KEY_ACCESSIBILITY_SCREENSHOT_HARDWAREBUFFER);
        ParcelableColorSpace colorSpace = (ParcelableColorSpace) result.getParcelable(KEY_ACCESSIBILITY_SCREENSHOT_COLORSPACE);
        ScreenshotResult screenshot = new ScreenshotResult(hardwareBuffer, colorSpace.getColorSpace(), result.getLong(KEY_ACCESSIBILITY_SCREENSHOT_TIMESTAMP));
        sendScreenshotSuccess(screenshot, executor, callback);
    }

    public void setAccessibilityFocusAppearance(int strokeWidth, int color) {
        AccessibilityInteractionClient.getInstance(this);
        IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
        if (connection != null) {
            try {
                connection.setFocusAppearance(strokeWidth, color);
            } catch (RemoteException re) {
                Log.w(LOG_TAG, "Error while setting the strokeWidth and color of the accessibility focus rectangle", re);
                re.rethrowFromSystemServer();
            }
        }
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return new IAccessibilityServiceClientWrapper(this, getMainLooper(), new Callbacks() { // from class: android.accessibilityservice.AccessibilityService.2
            @Override // android.accessibilityservice.AccessibilityService.Callbacks
            public void onServiceConnected() {
                AccessibilityService.this.dispatchServiceConnected();
            }

            @Override // android.accessibilityservice.AccessibilityService.Callbacks
            public void onInterrupt() {
                AccessibilityService.this.onInterrupt();
            }

            @Override // android.accessibilityservice.AccessibilityService.Callbacks
            public void onAccessibilityEvent(AccessibilityEvent event) {
                AccessibilityService.this.onAccessibilityEvent(event);
            }

            @Override // android.accessibilityservice.AccessibilityService.Callbacks
            public void init(int connectionId, IBinder windowToken) {
                AccessibilityService.this.mConnectionId = connectionId;
                AccessibilityService.this.mWindowToken = windowToken;
                WindowManagerImpl wm = (WindowManagerImpl) AccessibilityService.this.getSystemService(Context.WINDOW_SERVICE);
                wm.setDefaultToken(windowToken);
            }

            @Override // android.accessibilityservice.AccessibilityService.Callbacks
            public boolean onGesture(AccessibilityGestureEvent gestureEvent) {
                return AccessibilityService.this.onGesture(gestureEvent);
            }

            @Override // android.accessibilityservice.AccessibilityService.Callbacks
            public boolean onKeyEvent(KeyEvent event) {
                return AccessibilityService.this.onKeyEvent(event);
            }

            @Override // android.accessibilityservice.AccessibilityService.Callbacks
            public void onMagnificationChanged(int displayId, Region region, float scale, float centerX, float centerY) {
                AccessibilityService.this.onMagnificationChanged(displayId, region, scale, centerX, centerY);
            }

            @Override // android.accessibilityservice.AccessibilityService.Callbacks
            public void onSoftKeyboardShowModeChanged(int showMode) {
                AccessibilityService.this.onSoftKeyboardShowModeChanged(showMode);
            }

            @Override // android.accessibilityservice.AccessibilityService.Callbacks
            public void onPerformGestureResult(int sequence, boolean completedSuccessfully) {
                AccessibilityService.this.onPerformGestureResult(sequence, completedSuccessfully);
            }

            @Override // android.accessibilityservice.AccessibilityService.Callbacks
            public void onFingerprintCapturingGesturesChanged(boolean active) {
                AccessibilityService.this.onFingerprintCapturingGesturesChanged(active);
            }

            @Override // android.accessibilityservice.AccessibilityService.Callbacks
            public void onFingerprintGesture(int gesture) {
                AccessibilityService.this.onFingerprintGesture(gesture);
            }

            @Override // android.accessibilityservice.AccessibilityService.Callbacks
            public void onAccessibilityButtonClicked(int displayId) {
                AccessibilityService.this.onAccessibilityButtonClicked(displayId);
            }

            @Override // android.accessibilityservice.AccessibilityService.Callbacks
            public void onAccessibilityButtonAvailabilityChanged(boolean available) {
                AccessibilityService.this.onAccessibilityButtonAvailabilityChanged(available);
            }

            @Override // android.accessibilityservice.AccessibilityService.Callbacks
            public void onSystemActionsChanged() {
                AccessibilityService.this.onSystemActionsChanged();
            }
        });
    }

    /* loaded from: classes.dex */
    public static class IAccessibilityServiceClientWrapper extends IAccessibilityServiceClient.Stub implements HandlerCaller.Callback {
        private static final int DO_ACCESSIBILITY_BUTTON_AVAILABILITY_CHANGED = 13;
        private static final int DO_ACCESSIBILITY_BUTTON_CLICKED = 12;
        private static final int DO_CLEAR_ACCESSIBILITY_CACHE = 5;
        private static final int DO_GESTURE_COMPLETE = 9;
        private static final int DO_INIT = 1;
        private static final int DO_ON_ACCESSIBILITY_EVENT = 3;
        private static final int DO_ON_FINGERPRINT_ACTIVE_CHANGED = 10;
        private static final int DO_ON_FINGERPRINT_GESTURE = 11;
        private static final int DO_ON_GESTURE = 4;
        private static final int DO_ON_INTERRUPT = 2;
        private static final int DO_ON_KEY_EVENT = 6;
        private static final int DO_ON_MAGNIFICATION_CHANGED = 7;
        private static final int DO_ON_SOFT_KEYBOARD_SHOW_MODE_CHANGED = 8;
        private static final int DO_ON_SYSTEM_ACTIONS_CHANGED = 14;
        private final Callbacks mCallback;
        private final HandlerCaller mCaller;
        private int mConnectionId = -1;
        private final Context mContext;

        public IAccessibilityServiceClientWrapper(Context context, Looper looper, Callbacks callback) {
            this.mCallback = callback;
            this.mContext = context;
            this.mCaller = new HandlerCaller(context, looper, this, true);
        }

        @Override // android.accessibilityservice.IAccessibilityServiceClient
        public void init(IAccessibilityServiceConnection connection, int connectionId, IBinder windowToken) {
            Message message = this.mCaller.obtainMessageIOO(1, connectionId, connection, windowToken);
            this.mCaller.sendMessage(message);
        }

        @Override // android.accessibilityservice.IAccessibilityServiceClient
        public void onInterrupt() {
            Message message = this.mCaller.obtainMessage(2);
            this.mCaller.sendMessage(message);
        }

        @Override // android.accessibilityservice.IAccessibilityServiceClient
        public void onAccessibilityEvent(AccessibilityEvent event, boolean serviceWantsEvent) {
            Message message = this.mCaller.obtainMessageBO(3, serviceWantsEvent, event);
            this.mCaller.sendMessage(message);
        }

        @Override // android.accessibilityservice.IAccessibilityServiceClient
        public void onGesture(AccessibilityGestureEvent gestureInfo) {
            Message message = this.mCaller.obtainMessageO(4, gestureInfo);
            this.mCaller.sendMessage(message);
        }

        @Override // android.accessibilityservice.IAccessibilityServiceClient
        public void clearAccessibilityCache() {
            Message message = this.mCaller.obtainMessage(5);
            this.mCaller.sendMessage(message);
        }

        @Override // android.accessibilityservice.IAccessibilityServiceClient
        public void onKeyEvent(KeyEvent event, int sequence) {
            Message message = this.mCaller.obtainMessageIO(6, sequence, event);
            this.mCaller.sendMessage(message);
        }

        @Override // android.accessibilityservice.IAccessibilityServiceClient
        public void onMagnificationChanged(int displayId, Region region, float scale, float centerX, float centerY) {
            SomeArgs args = SomeArgs.obtain();
            args.arg1 = region;
            args.arg2 = Float.valueOf(scale);
            args.arg3 = Float.valueOf(centerX);
            args.arg4 = Float.valueOf(centerY);
            args.argi1 = displayId;
            Message message = this.mCaller.obtainMessageO(7, args);
            this.mCaller.sendMessage(message);
        }

        @Override // android.accessibilityservice.IAccessibilityServiceClient
        public void onSoftKeyboardShowModeChanged(int showMode) {
            Message message = this.mCaller.obtainMessageI(8, showMode);
            this.mCaller.sendMessage(message);
        }

        @Override // android.accessibilityservice.IAccessibilityServiceClient
        public void onPerformGestureResult(int sequence, boolean successfully) {
            Message message = this.mCaller.obtainMessageII(9, sequence, successfully ? 1 : 0);
            this.mCaller.sendMessage(message);
        }

        @Override // android.accessibilityservice.IAccessibilityServiceClient
        public void onFingerprintCapturingGesturesChanged(boolean active) {
            HandlerCaller handlerCaller = this.mCaller;
            handlerCaller.sendMessage(handlerCaller.obtainMessageI(10, active ? 1 : 0));
        }

        @Override // android.accessibilityservice.IAccessibilityServiceClient
        public void onFingerprintGesture(int gesture) {
            HandlerCaller handlerCaller = this.mCaller;
            handlerCaller.sendMessage(handlerCaller.obtainMessageI(11, gesture));
        }

        @Override // android.accessibilityservice.IAccessibilityServiceClient
        public void onAccessibilityButtonClicked(int displayId) {
            Message message = this.mCaller.obtainMessageI(12, displayId);
            this.mCaller.sendMessage(message);
        }

        @Override // android.accessibilityservice.IAccessibilityServiceClient
        public void onAccessibilityButtonAvailabilityChanged(boolean available) {
            Message message = this.mCaller.obtainMessageI(13, available ? 1 : 0);
            this.mCaller.sendMessage(message);
        }

        @Override // android.accessibilityservice.IAccessibilityServiceClient
        public void onSystemActionsChanged() {
            HandlerCaller handlerCaller = this.mCaller;
            handlerCaller.sendMessage(handlerCaller.obtainMessage(14));
        }

        @Override // com.android.internal.os.HandlerCaller.Callback
        public void executeMessage(Message message) {
            boolean available = false;
            switch (message.what) {
                case 1:
                    this.mConnectionId = message.arg1;
                    SomeArgs args = (SomeArgs) message.obj;
                    IAccessibilityServiceConnection connection = (IAccessibilityServiceConnection) args.arg1;
                    IBinder windowToken = (IBinder) args.arg2;
                    args.recycle();
                    if (connection != null) {
                        AccessibilityInteractionClient.getInstance(this.mContext);
                        AccessibilityInteractionClient.addConnection(this.mConnectionId, connection);
                        this.mCallback.init(this.mConnectionId, windowToken);
                        this.mCallback.onServiceConnected();
                        return;
                    }
                    AccessibilityInteractionClient.getInstance(this.mContext);
                    AccessibilityInteractionClient.removeConnection(this.mConnectionId);
                    this.mConnectionId = -1;
                    AccessibilityInteractionClient.getInstance(this.mContext).clearCache();
                    this.mCallback.init(-1, null);
                    return;
                case 2:
                    if (this.mConnectionId != -1) {
                        this.mCallback.onInterrupt();
                        return;
                    }
                    return;
                case 3:
                    AccessibilityEvent event = (AccessibilityEvent) message.obj;
                    if (message.arg1 != 0) {
                        available = true;
                    }
                    if (event != null) {
                        AccessibilityInteractionClient.getInstance(this.mContext).onAccessibilityEvent(event);
                        if (available && this.mConnectionId != -1) {
                            this.mCallback.onAccessibilityEvent(event);
                        }
                        try {
                            event.recycle();
                            return;
                        } catch (IllegalStateException e) {
                            return;
                        }
                    } else {
                        return;
                    }
                case 4:
                    if (this.mConnectionId != -1) {
                        this.mCallback.onGesture((AccessibilityGestureEvent) message.obj);
                        return;
                    }
                    return;
                case 5:
                    AccessibilityInteractionClient.getInstance(this.mContext).clearCache();
                    return;
                case 6:
                    KeyEvent event2 = (KeyEvent) message.obj;
                    try {
                        AccessibilityInteractionClient.getInstance(this.mContext);
                        IAccessibilityServiceConnection connection2 = AccessibilityInteractionClient.getConnection(this.mConnectionId);
                        if (connection2 != null) {
                            boolean result = this.mCallback.onKeyEvent(event2);
                            int sequence = message.arg1;
                            try {
                                connection2.setOnKeyEventResult(result, sequence);
                            } catch (RemoteException e2) {
                            }
                        }
                        try {
                            event2.recycle();
                            return;
                        } catch (IllegalStateException e3) {
                            return;
                        }
                    } catch (Throwable th) {
                        try {
                            event2.recycle();
                        } catch (IllegalStateException e4) {
                        }
                        throw th;
                    }
                case 7:
                    if (this.mConnectionId != -1) {
                        SomeArgs args2 = (SomeArgs) message.obj;
                        Region region = (Region) args2.arg1;
                        float scale = ((Float) args2.arg2).floatValue();
                        float centerX = ((Float) args2.arg3).floatValue();
                        float centerY = ((Float) args2.arg4).floatValue();
                        int displayId = args2.argi1;
                        args2.recycle();
                        this.mCallback.onMagnificationChanged(displayId, region, scale, centerX, centerY);
                        return;
                    }
                    return;
                case 8:
                    if (this.mConnectionId != -1) {
                        int showMode = message.arg1;
                        this.mCallback.onSoftKeyboardShowModeChanged(showMode);
                        return;
                    }
                    return;
                case 9:
                    if (this.mConnectionId != -1) {
                        if (message.arg2 == 1) {
                            available = true;
                        }
                        this.mCallback.onPerformGestureResult(message.arg1, available);
                        return;
                    }
                    return;
                case 10:
                    if (this.mConnectionId != -1) {
                        Callbacks callbacks = this.mCallback;
                        if (message.arg1 == 1) {
                            available = true;
                        }
                        callbacks.onFingerprintCapturingGesturesChanged(available);
                        return;
                    }
                    return;
                case 11:
                    if (this.mConnectionId != -1) {
                        this.mCallback.onFingerprintGesture(message.arg1);
                        return;
                    }
                    return;
                case 12:
                    if (this.mConnectionId != -1) {
                        this.mCallback.onAccessibilityButtonClicked(message.arg1);
                        return;
                    }
                    return;
                case 13:
                    if (this.mConnectionId != -1) {
                        if (message.arg1 != 0) {
                            available = true;
                        }
                        this.mCallback.onAccessibilityButtonAvailabilityChanged(available);
                        return;
                    }
                    return;
                case 14:
                    if (this.mConnectionId != -1) {
                        this.mCallback.onSystemActionsChanged();
                        return;
                    }
                    return;
                default:
                    Log.w(AccessibilityService.LOG_TAG, "Unknown message type " + message.what);
                    return;
            }
        }
    }

    /* loaded from: classes.dex */
    public static abstract class GestureResultCallback {
        public void onCompleted(GestureDescription gestureDescription) {
        }

        public void onCancelled(GestureDescription gestureDescription) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class GestureResultCallbackInfo {
        GestureResultCallback callback;
        GestureDescription gestureDescription;
        Handler handler;

        GestureResultCallbackInfo(GestureDescription gestureDescription, GestureResultCallback callback, Handler handler) {
            this.gestureDescription = gestureDescription;
            this.callback = callback;
            this.handler = handler;
        }
    }

    private void sendScreenshotSuccess(final ScreenshotResult screenshot, Executor executor, final TakeScreenshotCallback callback) {
        executor.execute(new Runnable() { // from class: android.accessibilityservice.AccessibilityService$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                AccessibilityService.TakeScreenshotCallback.this.onSuccess(screenshot);
            }
        });
    }

    private void sendScreenshotFailure(final int errorCode, Executor executor, final TakeScreenshotCallback callback) {
        executor.execute(new Runnable() { // from class: android.accessibilityservice.AccessibilityService$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                AccessibilityService.TakeScreenshotCallback.this.onFailure(errorCode);
            }
        });
    }

    /* loaded from: classes.dex */
    public static final class ScreenshotResult {
        private final ColorSpace mColorSpace;
        private final HardwareBuffer mHardwareBuffer;
        private final long mTimestamp;

        private ScreenshotResult(HardwareBuffer hardwareBuffer, ColorSpace colorSpace, long timestamp) {
            Preconditions.checkNotNull(hardwareBuffer, "hardwareBuffer cannot be null");
            Preconditions.checkNotNull(colorSpace, "colorSpace cannot be null");
            this.mHardwareBuffer = hardwareBuffer;
            this.mColorSpace = colorSpace;
            this.mTimestamp = timestamp;
        }

        public ColorSpace getColorSpace() {
            return this.mColorSpace;
        }

        public HardwareBuffer getHardwareBuffer() {
            return this.mHardwareBuffer;
        }

        public long getTimestamp() {
            return this.mTimestamp;
        }
    }

    public void setGestureDetectionPassthroughRegion(int displayId, Region region) {
        Preconditions.checkNotNull(region, "region cannot be null");
        AccessibilityInteractionClient.getInstance(this);
        IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
        if (connection != null) {
            try {
                connection.setGestureDetectionPassthroughRegion(displayId, region);
            } catch (RemoteException re) {
                throw new RuntimeException(re);
            }
        }
    }

    public void setTouchExplorationPassthroughRegion(int displayId, Region region) {
        Preconditions.checkNotNull(region, "region cannot be null");
        AccessibilityInteractionClient.getInstance(this);
        IAccessibilityServiceConnection connection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
        if (connection != null) {
            try {
                connection.setTouchExplorationPassthroughRegion(displayId, region);
            } catch (RemoteException re) {
                throw new RuntimeException(re);
            }
        }
    }
}
