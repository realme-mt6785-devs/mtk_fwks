package android.app;

import android.accessibilityservice.AccessibilityGestureEvent;
import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.accessibilityservice.IAccessibilityServiceClient;
import android.accessibilityservice.IAccessibilityServiceConnection;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.hardware.display.DisplayManagerGlobal;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.UserHandle;
import android.util.ArraySet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewRootImpl;
import android.view.Window;
import android.view.WindowAnimationFrameStats;
import android.view.WindowContentFrameStats;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityInteractionClient;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import com.android.internal.util.function.pooled.PooledLambda;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import libcore.io.IoUtils;
/* loaded from: classes.dex */
public final class UiAutomation {
    private static final int CONNECTION_ID_UNDEFINED = -1;
    private static final long CONNECT_TIMEOUT_MILLIS = 5000;
    private static final boolean DEBUG = false;
    public static final int FLAG_DONT_SUPPRESS_ACCESSIBILITY_SERVICES = 1;
    public static final int FLAG_DONT_USE_ACCESSIBILITY = 2;
    public static final int ROTATION_FREEZE_0 = 0;
    public static final int ROTATION_FREEZE_180 = 2;
    public static final int ROTATION_FREEZE_270 = 3;
    public static final int ROTATION_FREEZE_90 = 1;
    public static final int ROTATION_FREEZE_CURRENT = -1;
    public static final int ROTATION_UNFREEZE = -2;
    private IAccessibilityServiceClient mClient;
    private int mFlags;
    private boolean mIsDestroyed;
    private long mLastEventTimeMillis;
    private final Handler mLocalCallbackHandler;
    private OnAccessibilityEventListener mOnAccessibilityEventListener;
    private HandlerThread mRemoteCallbackThread;
    private final IUiAutomationConnection mUiAutomationConnection;
    private boolean mWaitingForEventDelivery;
    private static final String LOG_TAG = UiAutomation.class.getSimpleName();
    public static final Set<String> ALL_PERMISSIONS = Set.of("_ALL_PERMISSIONS_");
    private final Object mLock = new Object();
    private final ArrayList<AccessibilityEvent> mEventQueue = new ArrayList<>();
    private int mConnectionId = -1;
    private int mConnectionState = 0;
    private int mGenerationId = 0;

    /* loaded from: classes.dex */
    public interface AccessibilityEventFilter {
        boolean accept(AccessibilityEvent accessibilityEvent);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    private @interface ConnectionState {
        public static final int CONNECTED = 2;
        public static final int CONNECTING = 1;
        public static final int DISCONNECTED = 0;
        public static final int FAILED = 3;
    }

    /* loaded from: classes.dex */
    public interface OnAccessibilityEventListener {
        void onAccessibilityEvent(AccessibilityEvent accessibilityEvent);
    }

    public UiAutomation(Looper looper, IUiAutomationConnection connection) {
        if (looper == null) {
            throw new IllegalArgumentException("Looper cannot be null!");
        } else if (connection != null) {
            this.mLocalCallbackHandler = new Handler(looper);
            this.mUiAutomationConnection = connection;
        } else {
            throw new IllegalArgumentException("Connection cannot be null!");
        }
    }

    public void connect() {
        try {
            connectWithTimeout(0, 5000L);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    public void connect(int flags) {
        try {
            connectWithTimeout(flags, 5000L);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    public void connectWithTimeout(int flags, long timeoutMillis) throws TimeoutException {
        synchronized (this.mLock) {
            throwIfConnectedLocked();
            if (this.mConnectionState != 1) {
                this.mConnectionState = 1;
                HandlerThread handlerThread = new HandlerThread("UiAutomation");
                this.mRemoteCallbackThread = handlerThread;
                handlerThread.start();
                Looper looper = this.mRemoteCallbackThread.getLooper();
                int i = this.mGenerationId + 1;
                this.mGenerationId = i;
                IAccessibilityServiceClientImpl iAccessibilityServiceClientImpl = new IAccessibilityServiceClientImpl(looper, i);
                this.mClient = iAccessibilityServiceClientImpl;
                try {
                    this.mUiAutomationConnection.connect(iAccessibilityServiceClientImpl, flags);
                    this.mFlags = flags;
                    if (!useAccessibility()) {
                        this.mConnectionState = 0;
                        return;
                    }
                    synchronized (this.mLock) {
                        long startTimeMillis = SystemClock.uptimeMillis();
                        while (this.mConnectionState != 2) {
                            long elapsedTimeMillis = SystemClock.uptimeMillis() - startTimeMillis;
                            long remainingTimeMillis = timeoutMillis - elapsedTimeMillis;
                            if (remainingTimeMillis > 0) {
                                try {
                                    this.mLock.wait(remainingTimeMillis);
                                } catch (InterruptedException e) {
                                }
                            } else {
                                this.mConnectionState = 3;
                                throw new TimeoutException("Timeout while connecting " + this);
                            }
                        }
                    }
                } catch (RemoteException re) {
                    throw new RuntimeException("Error while connecting " + this, re);
                }
            }
        }
    }

    public int getFlags() {
        return this.mFlags;
    }

    public void disconnect() {
        synchronized (this.mLock) {
            if (this.mConnectionState == 1) {
                throw new IllegalStateException("Cannot call disconnect() while connecting " + this);
            } else if (!useAccessibility() || this.mConnectionState != 0) {
                this.mConnectionState = 0;
                this.mConnectionId = -1;
                this.mGenerationId++;
                try {
                    try {
                        this.mUiAutomationConnection.disconnect();
                    } catch (RemoteException re) {
                        throw new RuntimeException("Error while disconnecting " + this, re);
                    }
                } finally {
                    HandlerThread handlerThread = this.mRemoteCallbackThread;
                    if (handlerThread != null) {
                        handlerThread.quit();
                        this.mRemoteCallbackThread = null;
                    }
                }
            }
        }
    }

    public int getConnectionId() {
        int i;
        synchronized (this.mLock) {
            throwIfNotConnectedLocked();
            i = this.mConnectionId;
        }
        return i;
    }

    public boolean isDestroyed() {
        return this.mIsDestroyed;
    }

    public void setOnAccessibilityEventListener(OnAccessibilityEventListener listener) {
        synchronized (this.mLock) {
            throwIfNotConnectedLocked();
            this.mOnAccessibilityEventListener = listener;
        }
    }

    public void destroy() {
        disconnect();
        this.mIsDestroyed = true;
    }

    public void adoptShellPermissionIdentity() {
        try {
            this.mUiAutomationConnection.adoptShellPermissionIdentity(Process.myUid(), null);
        } catch (RemoteException re) {
            Log.e(LOG_TAG, "Error executing adopting shell permission identity!", re);
        }
    }

    public void adoptShellPermissionIdentity(String... permissions) {
        try {
            this.mUiAutomationConnection.adoptShellPermissionIdentity(Process.myUid(), permissions);
        } catch (RemoteException re) {
            Log.e(LOG_TAG, "Error executing adopting shell permission identity!", re);
        }
    }

    public void dropShellPermissionIdentity() {
        try {
            this.mUiAutomationConnection.dropShellPermissionIdentity();
        } catch (RemoteException re) {
            Log.e(LOG_TAG, "Error executing dropping shell permission identity!", re);
        }
    }

    public Set<String> getAdoptedShellPermissions() {
        try {
            List<String> permissions = this.mUiAutomationConnection.getAdoptedShellPermissions();
            return permissions == null ? ALL_PERMISSIONS : new ArraySet(permissions);
        } catch (RemoteException re) {
            Log.e(LOG_TAG, "Error getting adopted shell permissions", re);
            return Collections.emptySet();
        }
    }

    public final boolean performGlobalAction(int action) {
        IAccessibilityServiceConnection connection;
        synchronized (this.mLock) {
            throwIfNotConnectedLocked();
            AccessibilityInteractionClient.getInstance();
            connection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
        }
        if (connection == null) {
            return false;
        }
        try {
            return connection.performGlobalAction(action);
        } catch (RemoteException re) {
            Log.w(LOG_TAG, "Error while calling performGlobalAction", re);
            return false;
        }
    }

    public AccessibilityNodeInfo findFocus(int focus) {
        synchronized (this.mLock) {
            throwIfNotConnectedLocked();
        }
        return AccessibilityInteractionClient.getInstance().findFocus(this.mConnectionId, -2, AccessibilityNodeInfo.ROOT_NODE_ID, focus);
    }

    public final AccessibilityServiceInfo getServiceInfo() {
        IAccessibilityServiceConnection connection;
        synchronized (this.mLock) {
            throwIfNotConnectedLocked();
            AccessibilityInteractionClient.getInstance();
            connection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
        }
        if (connection == null) {
            return null;
        }
        try {
            return connection.getServiceInfo();
        } catch (RemoteException re) {
            Log.w(LOG_TAG, "Error while getting AccessibilityServiceInfo", re);
            return null;
        }
    }

    public final void setServiceInfo(AccessibilityServiceInfo info) {
        IAccessibilityServiceConnection connection;
        synchronized (this.mLock) {
            throwIfNotConnectedLocked();
            AccessibilityInteractionClient.getInstance().clearCache();
            AccessibilityInteractionClient.getInstance();
            connection = AccessibilityInteractionClient.getConnection(this.mConnectionId);
        }
        if (connection != null) {
            try {
                connection.setServiceInfo(info);
            } catch (RemoteException re) {
                Log.w(LOG_TAG, "Error while setting AccessibilityServiceInfo", re);
            }
        }
    }

    public List<AccessibilityWindowInfo> getWindows() {
        int connectionId;
        synchronized (this.mLock) {
            throwIfNotConnectedLocked();
            connectionId = this.mConnectionId;
        }
        return AccessibilityInteractionClient.getInstance().getWindows(connectionId);
    }

    public SparseArray<List<AccessibilityWindowInfo>> getWindowsOnAllDisplays() {
        int connectionId;
        synchronized (this.mLock) {
            throwIfNotConnectedLocked();
            connectionId = this.mConnectionId;
        }
        return AccessibilityInteractionClient.getInstance().getWindowsOnAllDisplays(connectionId);
    }

    public AccessibilityNodeInfo getRootInActiveWindow() {
        int connectionId;
        synchronized (this.mLock) {
            throwIfNotConnectedLocked();
            connectionId = this.mConnectionId;
        }
        return AccessibilityInteractionClient.getInstance().getRootInActiveWindow(connectionId);
    }

    public boolean injectInputEvent(InputEvent event, boolean sync) {
        return injectInputEvent(event, sync, true);
    }

    public boolean injectInputEvent(InputEvent event, boolean sync, boolean waitForAnimations) {
        try {
            return this.mUiAutomationConnection.injectInputEvent(event, sync, waitForAnimations);
        } catch (RemoteException re) {
            Log.e(LOG_TAG, "Error while injecting input event!", re);
            return false;
        }
    }

    public void syncInputTransactions() {
        try {
            this.mUiAutomationConnection.syncInputTransactions(true);
        } catch (RemoteException re) {
            Log.e(LOG_TAG, "Error while syncing input transactions!", re);
        }
    }

    public void syncInputTransactions(boolean waitForAnimations) {
        try {
            this.mUiAutomationConnection.syncInputTransactions(waitForAnimations);
        } catch (RemoteException re) {
            Log.e(LOG_TAG, "Error while syncing input transactions!", re);
        }
    }

    public boolean setRotation(int rotation) {
        switch (rotation) {
            case -2:
            case -1:
            case 0:
            case 1:
            case 2:
            case 3:
                try {
                    this.mUiAutomationConnection.setRotation(rotation);
                    return true;
                } catch (RemoteException re) {
                    Log.e(LOG_TAG, "Error while setting rotation!", re);
                    return false;
                }
            default:
                throw new IllegalArgumentException("Invalid rotation.");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x00d3, code lost:
        throw new java.util.concurrent.TimeoutException("Expected event not received within: " + r22 + " ms among: " + r0);
     */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00eb A[LOOP:3: B:56:0x00e9->B:57:0x00eb, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00fa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.accessibility.AccessibilityEvent executeAndWaitForEvent(java.lang.Runnable r20, android.app.UiAutomation.AccessibilityEventFilter r21, long r22) throws java.util.concurrent.TimeoutException {
        /*
            Method dump skipped, instructions count: 275
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.app.UiAutomation.executeAndWaitForEvent(java.lang.Runnable, android.app.UiAutomation$AccessibilityEventFilter, long):android.view.accessibility.AccessibilityEvent");
    }

    public void waitForIdle(long idleTimeoutMillis, long globalTimeoutMillis) throws TimeoutException {
        synchronized (this.mLock) {
            throwIfNotConnectedLocked();
            long startTimeMillis = SystemClock.uptimeMillis();
            long elapsedIdleTimeMillis = 0;
            if (this.mLastEventTimeMillis <= 0) {
                this.mLastEventTimeMillis = startTimeMillis;
            }
            while (true) {
                long currentTimeMillis = SystemClock.uptimeMillis();
                long elapsedGlobalTimeMillis = currentTimeMillis - startTimeMillis;
                long remainingGlobalTimeMillis = globalTimeoutMillis - elapsedGlobalTimeMillis;
                if (remainingGlobalTimeMillis > elapsedIdleTimeMillis) {
                    long elapsedIdleTimeMillis2 = currentTimeMillis - this.mLastEventTimeMillis;
                    long startTimeMillis2 = idleTimeoutMillis - elapsedIdleTimeMillis2;
                    if (startTimeMillis2 > 0) {
                        try {
                            this.mLock.wait(startTimeMillis2);
                        } catch (InterruptedException e) {
                        }
                        elapsedIdleTimeMillis = 0;
                        startTimeMillis = startTimeMillis;
                    }
                } else {
                    throw new TimeoutException("No idle state with idle timeout: " + idleTimeoutMillis + " within global timeout: " + globalTimeoutMillis);
                }
            }
        }
    }

    public Bitmap takeScreenshot() {
        Display display = DisplayManagerGlobal.getInstance().getRealDisplay(0);
        Point displaySize = new Point();
        display.getRealSize(displaySize);
        display.getRotation();
        try {
            Bitmap screenShot = this.mUiAutomationConnection.takeScreenshot(new Rect(0, 0, displaySize.x, displaySize.y));
            if (screenShot == null) {
                return null;
            }
            screenShot.setHasAlpha(false);
            return screenShot;
        } catch (RemoteException re) {
            Log.e(LOG_TAG, "Error while taking screenshot!", re);
            return null;
        }
    }

    public Bitmap takeScreenshot(Window window) {
        View decorView;
        ViewRootImpl viewRoot;
        if (window == null || (decorView = window.peekDecorView()) == null || (viewRoot = decorView.getViewRootImpl()) == null) {
            return null;
        }
        SurfaceControl sc = viewRoot.getSurfaceControl();
        if (!sc.isValid()) {
            return null;
        }
        new SurfaceControl.Transaction().apply(true);
        try {
            return this.mUiAutomationConnection.takeSurfaceControlScreenshot(sc);
        } catch (RemoteException re) {
            Log.e(LOG_TAG, "Error while taking screenshot!", re);
            return null;
        }
    }

    public void setRunAsMonkey(boolean enable) {
        try {
            ActivityManager.getService().setUserIsMonkey(enable);
        } catch (RemoteException re) {
            Log.e(LOG_TAG, "Error while setting run as monkey!", re);
        }
    }

    public boolean clearWindowContentFrameStats(int windowId) {
        synchronized (this.mLock) {
            throwIfNotConnectedLocked();
        }
        try {
            return this.mUiAutomationConnection.clearWindowContentFrameStats(windowId);
        } catch (RemoteException re) {
            Log.e(LOG_TAG, "Error clearing window content frame stats!", re);
            return false;
        }
    }

    public WindowContentFrameStats getWindowContentFrameStats(int windowId) {
        synchronized (this.mLock) {
            throwIfNotConnectedLocked();
        }
        try {
            return this.mUiAutomationConnection.getWindowContentFrameStats(windowId);
        } catch (RemoteException re) {
            Log.e(LOG_TAG, "Error getting window content frame stats!", re);
            return null;
        }
    }

    public void clearWindowAnimationFrameStats() {
        try {
            this.mUiAutomationConnection.clearWindowAnimationFrameStats();
        } catch (RemoteException re) {
            Log.e(LOG_TAG, "Error clearing window animation frame stats!", re);
        }
    }

    public WindowAnimationFrameStats getWindowAnimationFrameStats() {
        try {
            return this.mUiAutomationConnection.getWindowAnimationFrameStats();
        } catch (RemoteException re) {
            Log.e(LOG_TAG, "Error getting window animation frame stats!", re);
            return null;
        }
    }

    public void grantRuntimePermission(String packageName, String permission) {
        grantRuntimePermissionAsUser(packageName, permission, Process.myUserHandle());
    }

    @Deprecated
    public boolean grantRuntimePermission(String packageName, String permission, UserHandle userHandle) {
        grantRuntimePermissionAsUser(packageName, permission, userHandle);
        return true;
    }

    public void grantRuntimePermissionAsUser(String packageName, String permission, UserHandle userHandle) {
        try {
            this.mUiAutomationConnection.grantRuntimePermission(packageName, permission, userHandle.getIdentifier());
        } catch (Exception e) {
            throw new SecurityException("Error granting runtime permission", e);
        }
    }

    public void revokeRuntimePermission(String packageName, String permission) {
        revokeRuntimePermissionAsUser(packageName, permission, Process.myUserHandle());
    }

    @Deprecated
    public boolean revokeRuntimePermission(String packageName, String permission, UserHandle userHandle) {
        revokeRuntimePermissionAsUser(packageName, permission, userHandle);
        return true;
    }

    public void revokeRuntimePermissionAsUser(String packageName, String permission, UserHandle userHandle) {
        try {
            this.mUiAutomationConnection.revokeRuntimePermission(packageName, permission, userHandle.getIdentifier());
        } catch (Exception e) {
            throw new SecurityException("Error granting runtime permission", e);
        }
    }

    public ParcelFileDescriptor executeShellCommand(String command) {
        warnIfBetterCommand(command);
        ParcelFileDescriptor source = null;
        ParcelFileDescriptor sink = null;
        try {
            try {
                ParcelFileDescriptor[] pipe = ParcelFileDescriptor.createPipe();
                source = pipe[0];
                sink = pipe[1];
                this.mUiAutomationConnection.executeShellCommand(command, sink, null);
            } catch (RemoteException re) {
                Log.e(LOG_TAG, "Error executing shell command!", re);
            } catch (IOException ioe) {
                Log.e(LOG_TAG, "Error executing shell command!", ioe);
            }
            return source;
        } finally {
            IoUtils.closeQuietly(sink);
        }
    }

    public ParcelFileDescriptor[] executeShellCommandRw(String command) {
        return executeShellCommandInternal(command, false);
    }

    public ParcelFileDescriptor[] executeShellCommandRwe(String command) {
        return executeShellCommandInternal(command, true);
    }

    /* JADX WARN: Finally extract failed */
    private ParcelFileDescriptor[] executeShellCommandInternal(String command, boolean includeStderr) {
        warnIfBetterCommand(command);
        ParcelFileDescriptor source_read = null;
        ParcelFileDescriptor sink_read = null;
        ParcelFileDescriptor source_write = null;
        ParcelFileDescriptor sink_write = null;
        ParcelFileDescriptor stderr_source_read = null;
        ParcelFileDescriptor stderr_sink_read = null;
        try {
            try {
                try {
                    ParcelFileDescriptor[] pipe_read = ParcelFileDescriptor.createPipe();
                    source_read = pipe_read[0];
                    sink_read = pipe_read[1];
                    ParcelFileDescriptor[] pipe_write = ParcelFileDescriptor.createPipe();
                    source_write = pipe_write[0];
                    sink_write = pipe_write[1];
                    if (includeStderr) {
                        ParcelFileDescriptor[] stderr_read = ParcelFileDescriptor.createPipe();
                        stderr_source_read = stderr_read[0];
                        stderr_sink_read = stderr_read[1];
                    }
                    this.mUiAutomationConnection.executeShellCommandWithStderr(command, sink_read, source_write, stderr_sink_read);
                } catch (IOException ioe) {
                    Log.e(LOG_TAG, "Error executing shell command!", ioe);
                }
            } catch (RemoteException re) {
                Log.e(LOG_TAG, "Error executing shell command!", re);
            }
            IoUtils.closeQuietly(sink_read);
            IoUtils.closeQuietly(source_write);
            IoUtils.closeQuietly(stderr_sink_read);
            ParcelFileDescriptor[] result = new ParcelFileDescriptor[includeStderr ? 3 : 2];
            result[0] = source_read;
            result[1] = sink_write;
            if (includeStderr) {
                result[2] = stderr_source_read;
            }
            return result;
        } catch (Throwable th) {
            IoUtils.closeQuietly(sink_read);
            IoUtils.closeQuietly(source_write);
            IoUtils.closeQuietly(stderr_sink_read);
            throw th;
        }
    }

    public String toString() {
        return "UiAutomation@" + Integer.toHexString(hashCode()) + "[id=" + this.mConnectionId + ", flags=" + this.mFlags + "]";
    }

    private void throwIfConnectedLocked() {
        if (this.mConnectionState == 2) {
            throw new IllegalStateException("UiAutomation connected, " + this);
        }
    }

    private void throwIfNotConnectedLocked() {
        String msg;
        if (this.mConnectionState != 2) {
            if (useAccessibility()) {
                msg = "UiAutomation not connected, ";
            } else {
                msg = "UiAutomation not connected: Accessibility-dependent method called with FLAG_DONT_USE_ACCESSIBILITY set, ";
            }
            throw new IllegalStateException(msg + this);
        }
    }

    private void warnIfBetterCommand(String cmd) {
        if (cmd.startsWith("pm grant ")) {
            Log.w(LOG_TAG, "UiAutomation.grantRuntimePermission() is more robust and should be used instead of 'pm grant'");
        } else if (cmd.startsWith("pm revoke ")) {
            Log.w(LOG_TAG, "UiAutomation.revokeRuntimePermission() is more robust and should be used instead of 'pm revoke'");
        }
    }

    private boolean useAccessibility() {
        return (this.mFlags & 2) == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class IAccessibilityServiceClientImpl extends AccessibilityService.IAccessibilityServiceClientWrapper {
        public IAccessibilityServiceClientImpl(Looper looper, int generationId) {
            super(null, looper, new AccessibilityService.Callbacks(generationId) { // from class: android.app.UiAutomation.IAccessibilityServiceClientImpl.1
                private final int mGenerationId;
                final /* synthetic */ int val$generationId;

                {
                    this.val$generationId = generationId;
                    this.mGenerationId = generationId;
                }

                private boolean isGenerationChangedLocked() {
                    return this.mGenerationId != UiAutomation.this.mGenerationId;
                }

                @Override // android.accessibilityservice.AccessibilityService.Callbacks
                public void init(int connectionId, IBinder windowToken) {
                    synchronized (UiAutomation.this.mLock) {
                        if (!isGenerationChangedLocked()) {
                            UiAutomation.this.mConnectionState = 2;
                            UiAutomation.this.mConnectionId = connectionId;
                            UiAutomation.this.mLock.notifyAll();
                            if (Build.IS_DEBUGGABLE) {
                                String str = UiAutomation.LOG_TAG;
                                Log.v(str, "Init " + UiAutomation.this);
                            }
                        }
                    }
                }

                @Override // android.accessibilityservice.AccessibilityService.Callbacks
                public void onServiceConnected() {
                }

                @Override // android.accessibilityservice.AccessibilityService.Callbacks
                public void onInterrupt() {
                }

                @Override // android.accessibilityservice.AccessibilityService.Callbacks
                public void onSystemActionsChanged() {
                }

                @Override // android.accessibilityservice.AccessibilityService.Callbacks
                public boolean onGesture(AccessibilityGestureEvent gestureEvent) {
                    return false;
                }

                @Override // android.accessibilityservice.AccessibilityService.Callbacks
                public void onAccessibilityEvent(AccessibilityEvent event) {
                    synchronized (UiAutomation.this.mLock) {
                        if (!isGenerationChangedLocked()) {
                            UiAutomation.this.mLastEventTimeMillis = event.getEventTime();
                            if (UiAutomation.this.mWaitingForEventDelivery) {
                                UiAutomation.this.mEventQueue.add(AccessibilityEvent.obtain(event));
                            }
                            UiAutomation.this.mLock.notifyAll();
                            OnAccessibilityEventListener listener = UiAutomation.this.mOnAccessibilityEventListener;
                            if (listener != null) {
                                UiAutomation.this.mLocalCallbackHandler.sendMessage(PooledLambda.obtainMessage(UiAutomation$IAccessibilityServiceClientImpl$1$$ExternalSyntheticLambda0.INSTANCE, listener, AccessibilityEvent.obtain(event)));
                            }
                        }
                    }
                }

                @Override // android.accessibilityservice.AccessibilityService.Callbacks
                public boolean onKeyEvent(KeyEvent event) {
                    return false;
                }

                @Override // android.accessibilityservice.AccessibilityService.Callbacks
                public void onMagnificationChanged(int displayId, Region region, float scale, float centerX, float centerY) {
                }

                @Override // android.accessibilityservice.AccessibilityService.Callbacks
                public void onSoftKeyboardShowModeChanged(int showMode) {
                }

                @Override // android.accessibilityservice.AccessibilityService.Callbacks
                public void onPerformGestureResult(int sequence, boolean completedSuccessfully) {
                }

                @Override // android.accessibilityservice.AccessibilityService.Callbacks
                public void onFingerprintCapturingGesturesChanged(boolean active) {
                }

                @Override // android.accessibilityservice.AccessibilityService.Callbacks
                public void onFingerprintGesture(int gesture) {
                }

                @Override // android.accessibilityservice.AccessibilityService.Callbacks
                public void onAccessibilityButtonClicked(int displayId) {
                }

                @Override // android.accessibilityservice.AccessibilityService.Callbacks
                public void onAccessibilityButtonAvailabilityChanged(boolean available) {
                }
            });
        }
    }
}
