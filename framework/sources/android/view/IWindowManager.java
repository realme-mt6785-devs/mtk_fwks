package android.view;

import android.app.IAssistDataReceiver;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Insets;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.IRemoteCallback;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.IAppTransitionAnimationSpecsFuture;
import android.view.ICrossWindowBlurEnabledListener;
import android.view.IDisplayFoldListener;
import android.view.IDisplayWindowInsetsController;
import android.view.IDisplayWindowListener;
import android.view.IDisplayWindowRotationController;
import android.view.IOnKeyguardExitResult;
import android.view.IPinnedTaskListener;
import android.view.IRotationWatcher;
import android.view.IScrollCaptureResponseListener;
import android.view.ISystemGestureExclusionListener;
import android.view.IWallpaperVisibilityListener;
import android.view.IWindow;
import android.view.IWindowSession;
import android.view.IWindowSessionCallback;
import android.view.WindowManager;
import android.view.displayhash.DisplayHash;
import android.view.displayhash.VerifiedDisplayHash;
import com.android.internal.os.IResultReceiver;
import com.android.internal.policy.IKeyguardDismissCallback;
import com.android.internal.policy.IShortcutService;
/* loaded from: classes3.dex */
public interface IWindowManager extends IInterface {
    public static final int FIXED_TO_USER_ROTATION_DEFAULT = 0;
    public static final int FIXED_TO_USER_ROTATION_DISABLED = 1;
    public static final int FIXED_TO_USER_ROTATION_ENABLED = 2;

    SurfaceControl addShellRoot(int i, IWindow iWindow, int i2) throws RemoteException;

    void addWindowToken(IBinder iBinder, int i, int i2, Bundle bundle) throws RemoteException;

    Configuration attachWindowContextToDisplayArea(IBinder iBinder, int i, int i2, Bundle bundle) throws RemoteException;

    void attachWindowContextToWindowToken(IBinder iBinder, IBinder iBinder2) throws RemoteException;

    void clearForcedDisplayDensityForUser(int i, int i2) throws RemoteException;

    void clearForcedDisplaySize(int i) throws RemoteException;

    boolean clearWindowContentFrameStats(IBinder iBinder) throws RemoteException;

    void closeSystemDialogs(String str) throws RemoteException;

    void createInputConsumer(IBinder iBinder, String str, int i, InputChannel inputChannel) throws RemoteException;

    boolean destroyInputConsumer(String str, int i) throws RemoteException;

    void detachWindowContextFromWindowContainer(IBinder iBinder) throws RemoteException;

    @Deprecated
    void disableKeyguard(IBinder iBinder, String str, int i) throws RemoteException;

    void dismissKeyguard(IKeyguardDismissCallback iKeyguardDismissCallback, CharSequence charSequence) throws RemoteException;

    void dontOverrideDisplayInfo(int i) throws RemoteException;

    void enableScreenIfNeeded() throws RemoteException;

    @Deprecated
    void endProlongedAnimations() throws RemoteException;

    void exitKeyguardSecurely(IOnKeyguardExitResult iOnKeyguardExitResult) throws RemoteException;

    void freezeDisplayRotation(int i, int i2) throws RemoteException;

    void freezeRotation(int i) throws RemoteException;

    float getAnimationScale(int i) throws RemoteException;

    float[] getAnimationScales() throws RemoteException;

    int getBaseDisplayDensity(int i) throws RemoteException;

    void getBaseDisplaySize(int i, Point point) throws RemoteException;

    float getCurrentAnimatorScale() throws RemoteException;

    Region getCurrentImeTouchRegion() throws RemoteException;

    int getDefaultDisplayRotation() throws RemoteException;

    int getDisplayImePolicy(int i) throws RemoteException;

    int getDockedStackSide() throws RemoteException;

    int getInitialDisplayDensity(int i) throws RemoteException;

    void getInitialDisplaySize(int i, Point point) throws RemoteException;

    int getNavBarPosition(int i) throws RemoteException;

    int getPreferredOptionsPanelGravity(int i) throws RemoteException;

    int getRemoveContentMode(int i) throws RemoteException;

    void getStableInsets(int i, Rect rect) throws RemoteException;

    String[] getSupportedDisplayHashAlgorithms() throws RemoteException;

    WindowContentFrameStats getWindowContentFrameStats(IBinder iBinder) throws RemoteException;

    boolean getWindowInsets(WindowManager.LayoutParams layoutParams, int i, InsetsState insetsState) throws RemoteException;

    int getWindowingMode(int i) throws RemoteException;

    boolean hasNavigationBar(int i) throws RemoteException;

    void hideTransientBars(int i) throws RemoteException;

    void holdLock(IBinder iBinder, int i) throws RemoteException;

    boolean injectInputAfterTransactionsApplied(InputEvent inputEvent, int i, boolean z) throws RemoteException;

    boolean isDisplayRotationFrozen(int i) throws RemoteException;

    boolean isKeyguardLocked() throws RemoteException;

    boolean isKeyguardSecure(int i) throws RemoteException;

    boolean isLayerTracing() throws RemoteException;

    boolean isRotationFrozen() throws RemoteException;

    boolean isSafeModeEnabled() throws RemoteException;

    boolean isTaskSnapshotSupported() throws RemoteException;

    boolean isViewServerRunning() throws RemoteException;

    boolean isWindowToken(IBinder iBinder) throws RemoteException;

    boolean isWindowTraceEnabled() throws RemoteException;

    void lockNow(Bundle bundle) throws RemoteException;

    boolean mirrorDisplay(int i, SurfaceControl surfaceControl) throws RemoteException;

    void modifyDisplayWindowInsets(int i, InsetsState insetsState) throws RemoteException;

    IWindowSession openSession(IWindowSessionCallback iWindowSessionCallback) throws RemoteException;

    void overridePendingAppTransitionMultiThumbFuture(IAppTransitionAnimationSpecsFuture iAppTransitionAnimationSpecsFuture, IRemoteCallback iRemoteCallback, boolean z, int i) throws RemoteException;

    void overridePendingAppTransitionRemote(RemoteAnimationAdapter remoteAnimationAdapter, int i) throws RemoteException;

    @Deprecated
    void reenableKeyguard(IBinder iBinder, int i) throws RemoteException;

    void refreshScreenCaptureDisabled(int i) throws RemoteException;

    boolean registerCrossWindowBlurEnabledListener(ICrossWindowBlurEnabledListener iCrossWindowBlurEnabledListener) throws RemoteException;

    void registerDisplayFoldListener(IDisplayFoldListener iDisplayFoldListener) throws RemoteException;

    void registerDisplayWindowListener(IDisplayWindowListener iDisplayWindowListener) throws RemoteException;

    void registerPinnedTaskListener(int i, IPinnedTaskListener iPinnedTaskListener) throws RemoteException;

    void registerShortcutKey(long j, IShortcutService iShortcutService) throws RemoteException;

    void registerSystemGestureExclusionListener(ISystemGestureExclusionListener iSystemGestureExclusionListener, int i) throws RemoteException;

    boolean registerWallpaperVisibilityListener(IWallpaperVisibilityListener iWallpaperVisibilityListener, int i) throws RemoteException;

    void removeRotationWatcher(IRotationWatcher iRotationWatcher) throws RemoteException;

    void removeWindowToken(IBinder iBinder, int i) throws RemoteException;

    void requestAppKeyboardShortcuts(IResultReceiver iResultReceiver, int i) throws RemoteException;

    boolean requestAssistScreenshot(IAssistDataReceiver iAssistDataReceiver) throws RemoteException;

    void requestScrollCapture(int i, IBinder iBinder, int i2, IScrollCaptureResponseListener iScrollCaptureResponseListener) throws RemoteException;

    Bitmap screenshotWallpaper() throws RemoteException;

    void setAnimationScale(int i, float f) throws RemoteException;

    void setAnimationScales(float[] fArr) throws RemoteException;

    void setDisplayHashThrottlingEnabled(boolean z) throws RemoteException;

    void setDisplayImePolicy(int i, int i2) throws RemoteException;

    void setDisplayWindowInsetsController(int i, IDisplayWindowInsetsController iDisplayWindowInsetsController) throws RemoteException;

    void setDisplayWindowRotationController(IDisplayWindowRotationController iDisplayWindowRotationController) throws RemoteException;

    void setDockedTaskDividerTouchRegion(Rect rect) throws RemoteException;

    void setEventDispatching(boolean z) throws RemoteException;

    void setFixedToUserRotation(int i, int i2) throws RemoteException;

    void setForcedDisplayDensityForUser(int i, int i2, int i3) throws RemoteException;

    void setForcedDisplayScalingMode(int i, int i2) throws RemoteException;

    void setForcedDisplaySize(int i, int i2, int i3) throws RemoteException;

    void setForwardedInsets(int i, Insets insets) throws RemoteException;

    void setIgnoreOrientationRequest(int i, boolean z) throws RemoteException;

    void setInTouchMode(boolean z) throws RemoteException;

    void setLayerTracing(boolean z) throws RemoteException;

    void setLayerTracingFlags(int i) throws RemoteException;

    void setNavBarVirtualKeyHapticFeedbackEnabled(boolean z) throws RemoteException;

    void setRecentsVisibility(boolean z) throws RemoteException;

    void setRemoveContentMode(int i, int i2) throws RemoteException;

    void setShellRootAccessibilityWindow(int i, int i2, IWindow iWindow) throws RemoteException;

    void setShouldShowSystemDecors(int i, boolean z) throws RemoteException;

    void setShouldShowWithInsecureKeyguard(int i, boolean z) throws RemoteException;

    void setStrictModeVisualIndicatorPreference(String str) throws RemoteException;

    void setSwitchingUser(boolean z) throws RemoteException;

    void setWindowingMode(int i, int i2) throws RemoteException;

    boolean shouldShowSystemDecors(int i) throws RemoteException;

    boolean shouldShowWithInsecureKeyguard(int i) throws RemoteException;

    void showGlobalActions() throws RemoteException;

    void showStrictModeViolation(boolean z) throws RemoteException;

    void startFreezingScreen(int i, int i2) throws RemoteException;

    boolean startViewServer(int i) throws RemoteException;

    void startWindowTrace() throws RemoteException;

    void stopFreezingScreen() throws RemoteException;

    boolean stopViewServer() throws RemoteException;

    void stopWindowTrace() throws RemoteException;

    void syncInputTransactions(boolean z) throws RemoteException;

    void thawDisplayRotation(int i) throws RemoteException;

    void thawRotation() throws RemoteException;

    void unregisterCrossWindowBlurEnabledListener(ICrossWindowBlurEnabledListener iCrossWindowBlurEnabledListener) throws RemoteException;

    void unregisterDisplayFoldListener(IDisplayFoldListener iDisplayFoldListener) throws RemoteException;

    void unregisterDisplayWindowListener(IDisplayWindowListener iDisplayWindowListener) throws RemoteException;

    void unregisterSystemGestureExclusionListener(ISystemGestureExclusionListener iSystemGestureExclusionListener, int i) throws RemoteException;

    void unregisterWallpaperVisibilityListener(IWallpaperVisibilityListener iWallpaperVisibilityListener, int i) throws RemoteException;

    void updateRotation(boolean z, boolean z2) throws RemoteException;

    void updateStaticPrivacyIndicatorBounds(int i, Rect[] rectArr) throws RemoteException;

    boolean useBLAST() throws RemoteException;

    VerifiedDisplayHash verifyDisplayHash(DisplayHash displayHash) throws RemoteException;

    int watchRotation(IRotationWatcher iRotationWatcher, int i) throws RemoteException;

    /* loaded from: classes3.dex */
    public static class Default implements IWindowManager {
        @Override // android.view.IWindowManager
        public boolean startViewServer(int port) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public boolean stopViewServer() throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public boolean isViewServerRunning() throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public IWindowSession openSession(IWindowSessionCallback callback) throws RemoteException {
            return null;
        }

        @Override // android.view.IWindowManager
        public boolean useBLAST() throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public void getInitialDisplaySize(int displayId, Point size) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void getBaseDisplaySize(int displayId, Point size) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setForcedDisplaySize(int displayId, int width, int height) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void clearForcedDisplaySize(int displayId) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public int getInitialDisplayDensity(int displayId) throws RemoteException {
            return 0;
        }

        @Override // android.view.IWindowManager
        public int getBaseDisplayDensity(int displayId) throws RemoteException {
            return 0;
        }

        @Override // android.view.IWindowManager
        public void setForcedDisplayDensityForUser(int displayId, int density, int userId) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void clearForcedDisplayDensityForUser(int displayId, int userId) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setForcedDisplayScalingMode(int displayId, int mode) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setEventDispatching(boolean enabled) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public boolean isWindowToken(IBinder binder) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public void addWindowToken(IBinder token, int type, int displayId, Bundle options) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void removeWindowToken(IBinder token, int displayId) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setDisplayWindowRotationController(IDisplayWindowRotationController controller) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public SurfaceControl addShellRoot(int displayId, IWindow client, int shellRootLayer) throws RemoteException {
            return null;
        }

        @Override // android.view.IWindowManager
        public void setShellRootAccessibilityWindow(int displayId, int shellRootLayer, IWindow target) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void overridePendingAppTransitionMultiThumbFuture(IAppTransitionAnimationSpecsFuture specsFuture, IRemoteCallback startedCallback, boolean scaleUp, int displayId) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void overridePendingAppTransitionRemote(RemoteAnimationAdapter remoteAnimationAdapter, int displayId) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void endProlongedAnimations() throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void startFreezingScreen(int exitAnim, int enterAnim) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void stopFreezingScreen() throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void disableKeyguard(IBinder token, String tag, int userId) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void reenableKeyguard(IBinder token, int userId) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void exitKeyguardSecurely(IOnKeyguardExitResult callback) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public boolean isKeyguardLocked() throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public boolean isKeyguardSecure(int userId) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public void dismissKeyguard(IKeyguardDismissCallback callback, CharSequence message) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setSwitchingUser(boolean switching) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void closeSystemDialogs(String reason) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public float getAnimationScale(int which) throws RemoteException {
            return 0.0f;
        }

        @Override // android.view.IWindowManager
        public float[] getAnimationScales() throws RemoteException {
            return null;
        }

        @Override // android.view.IWindowManager
        public void setAnimationScale(int which, float scale) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setAnimationScales(float[] scales) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public float getCurrentAnimatorScale() throws RemoteException {
            return 0.0f;
        }

        @Override // android.view.IWindowManager
        public void setInTouchMode(boolean showFocus) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void showStrictModeViolation(boolean on) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setStrictModeVisualIndicatorPreference(String enabled) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void refreshScreenCaptureDisabled(int userId) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void updateRotation(boolean alwaysSendConfiguration, boolean forceRelayout) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public int getDefaultDisplayRotation() throws RemoteException {
            return 0;
        }

        @Override // android.view.IWindowManager
        public int watchRotation(IRotationWatcher watcher, int displayId) throws RemoteException {
            return 0;
        }

        @Override // android.view.IWindowManager
        public void removeRotationWatcher(IRotationWatcher watcher) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public int getPreferredOptionsPanelGravity(int displayId) throws RemoteException {
            return 0;
        }

        @Override // android.view.IWindowManager
        public void freezeRotation(int rotation) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void thawRotation() throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public boolean isRotationFrozen() throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public void freezeDisplayRotation(int displayId, int rotation) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void thawDisplayRotation(int displayId) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public boolean isDisplayRotationFrozen(int displayId) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public void setFixedToUserRotation(int displayId, int fixedToUserRotation) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setIgnoreOrientationRequest(int displayId, boolean ignoreOrientationRequest) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public Bitmap screenshotWallpaper() throws RemoteException {
            return null;
        }

        @Override // android.view.IWindowManager
        public boolean registerWallpaperVisibilityListener(IWallpaperVisibilityListener listener, int displayId) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public void unregisterWallpaperVisibilityListener(IWallpaperVisibilityListener listener, int displayId) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void registerSystemGestureExclusionListener(ISystemGestureExclusionListener listener, int displayId) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void unregisterSystemGestureExclusionListener(ISystemGestureExclusionListener listener, int displayId) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public boolean requestAssistScreenshot(IAssistDataReceiver receiver) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public void hideTransientBars(int displayId) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setRecentsVisibility(boolean visible) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void updateStaticPrivacyIndicatorBounds(int displayId, Rect[] staticBounds) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setNavBarVirtualKeyHapticFeedbackEnabled(boolean enabled) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public boolean hasNavigationBar(int displayId) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public int getNavBarPosition(int displayId) throws RemoteException {
            return 0;
        }

        @Override // android.view.IWindowManager
        public void lockNow(Bundle options) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public boolean isSafeModeEnabled() throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public void enableScreenIfNeeded() throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public boolean clearWindowContentFrameStats(IBinder token) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public WindowContentFrameStats getWindowContentFrameStats(IBinder token) throws RemoteException {
            return null;
        }

        @Override // android.view.IWindowManager
        public int getDockedStackSide() throws RemoteException {
            return 0;
        }

        @Override // android.view.IWindowManager
        public void setDockedTaskDividerTouchRegion(Rect touchableRegion) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void registerPinnedTaskListener(int displayId, IPinnedTaskListener listener) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void requestAppKeyboardShortcuts(IResultReceiver receiver, int deviceId) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void getStableInsets(int displayId, Rect outInsets) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setForwardedInsets(int displayId, Insets insets) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void registerShortcutKey(long shortcutCode, IShortcutService keySubscriber) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void createInputConsumer(IBinder token, String name, int displayId, InputChannel inputChannel) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public boolean destroyInputConsumer(String name, int displayId) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public Region getCurrentImeTouchRegion() throws RemoteException {
            return null;
        }

        @Override // android.view.IWindowManager
        public void registerDisplayFoldListener(IDisplayFoldListener listener) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void unregisterDisplayFoldListener(IDisplayFoldListener listener) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void registerDisplayWindowListener(IDisplayWindowListener listener) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void unregisterDisplayWindowListener(IDisplayWindowListener listener) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void startWindowTrace() throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void stopWindowTrace() throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public boolean isWindowTraceEnabled() throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public void dontOverrideDisplayInfo(int displayId) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public int getWindowingMode(int displayId) throws RemoteException {
            return 0;
        }

        @Override // android.view.IWindowManager
        public void setWindowingMode(int displayId, int mode) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public int getRemoveContentMode(int displayId) throws RemoteException {
            return 0;
        }

        @Override // android.view.IWindowManager
        public void setRemoveContentMode(int displayId, int mode) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public boolean shouldShowWithInsecureKeyguard(int displayId) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public void setShouldShowWithInsecureKeyguard(int displayId, boolean shouldShow) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public boolean shouldShowSystemDecors(int displayId) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public void setShouldShowSystemDecors(int displayId, boolean shouldShow) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public int getDisplayImePolicy(int displayId) throws RemoteException {
            return 0;
        }

        @Override // android.view.IWindowManager
        public void setDisplayImePolicy(int displayId, int imePolicy) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public boolean injectInputAfterTransactionsApplied(InputEvent ev, int mode, boolean waitForAnimations) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public void syncInputTransactions(boolean waitForAnimations) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public boolean isLayerTracing() throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public void setLayerTracing(boolean enabled) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public boolean mirrorDisplay(int displayId, SurfaceControl outSurfaceControl) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public void setDisplayWindowInsetsController(int displayId, IDisplayWindowInsetsController displayWindowInsetsController) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void modifyDisplayWindowInsets(int displayId, InsetsState state) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public boolean getWindowInsets(WindowManager.LayoutParams attrs, int displayId, InsetsState outInsetsState) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public void showGlobalActions() throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setLayerTracingFlags(int flags) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void requestScrollCapture(int displayId, IBinder behindClient, int taskId, IScrollCaptureResponseListener listener) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void holdLock(IBinder token, int durationMs) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public String[] getSupportedDisplayHashAlgorithms() throws RemoteException {
            return null;
        }

        @Override // android.view.IWindowManager
        public VerifiedDisplayHash verifyDisplayHash(DisplayHash displayHash) throws RemoteException {
            return null;
        }

        @Override // android.view.IWindowManager
        public void setDisplayHashThrottlingEnabled(boolean enable) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public Configuration attachWindowContextToDisplayArea(IBinder clientToken, int type, int displayId, Bundle options) throws RemoteException {
            return null;
        }

        @Override // android.view.IWindowManager
        public void attachWindowContextToWindowToken(IBinder clientToken, IBinder token) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void detachWindowContextFromWindowContainer(IBinder clientToken) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public boolean registerCrossWindowBlurEnabledListener(ICrossWindowBlurEnabledListener listener) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public void unregisterCrossWindowBlurEnabledListener(ICrossWindowBlurEnabledListener listener) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public boolean isTaskSnapshotSupported() throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class Stub extends Binder implements IWindowManager {
        public static final String DESCRIPTOR = "android.view.IWindowManager";
        static final int TRANSACTION_addShellRoot = 20;
        static final int TRANSACTION_addWindowToken = 17;
        static final int TRANSACTION_attachWindowContextToDisplayArea = 117;
        static final int TRANSACTION_attachWindowContextToWindowToken = 118;
        static final int TRANSACTION_clearForcedDisplayDensityForUser = 13;
        static final int TRANSACTION_clearForcedDisplaySize = 9;
        static final int TRANSACTION_clearWindowContentFrameStats = 72;
        static final int TRANSACTION_closeSystemDialogs = 34;
        static final int TRANSACTION_createInputConsumer = 81;
        static final int TRANSACTION_destroyInputConsumer = 82;
        static final int TRANSACTION_detachWindowContextFromWindowContainer = 119;
        static final int TRANSACTION_disableKeyguard = 27;
        static final int TRANSACTION_dismissKeyguard = 32;
        static final int TRANSACTION_dontOverrideDisplayInfo = 91;
        static final int TRANSACTION_enableScreenIfNeeded = 71;
        static final int TRANSACTION_endProlongedAnimations = 24;
        static final int TRANSACTION_exitKeyguardSecurely = 29;
        static final int TRANSACTION_freezeDisplayRotation = 52;
        static final int TRANSACTION_freezeRotation = 49;
        static final int TRANSACTION_getAnimationScale = 35;
        static final int TRANSACTION_getAnimationScales = 36;
        static final int TRANSACTION_getBaseDisplayDensity = 11;
        static final int TRANSACTION_getBaseDisplaySize = 7;
        static final int TRANSACTION_getCurrentAnimatorScale = 39;
        static final int TRANSACTION_getCurrentImeTouchRegion = 83;
        static final int TRANSACTION_getDefaultDisplayRotation = 45;
        static final int TRANSACTION_getDisplayImePolicy = 100;
        static final int TRANSACTION_getDockedStackSide = 74;
        static final int TRANSACTION_getInitialDisplayDensity = 10;
        static final int TRANSACTION_getInitialDisplaySize = 6;
        static final int TRANSACTION_getNavBarPosition = 68;
        static final int TRANSACTION_getPreferredOptionsPanelGravity = 48;
        static final int TRANSACTION_getRemoveContentMode = 94;
        static final int TRANSACTION_getStableInsets = 78;
        static final int TRANSACTION_getSupportedDisplayHashAlgorithms = 114;
        static final int TRANSACTION_getWindowContentFrameStats = 73;
        static final int TRANSACTION_getWindowInsets = 109;
        static final int TRANSACTION_getWindowingMode = 92;
        static final int TRANSACTION_hasNavigationBar = 67;
        static final int TRANSACTION_hideTransientBars = 63;
        static final int TRANSACTION_holdLock = 113;
        static final int TRANSACTION_injectInputAfterTransactionsApplied = 102;
        static final int TRANSACTION_isDisplayRotationFrozen = 54;
        static final int TRANSACTION_isKeyguardLocked = 30;
        static final int TRANSACTION_isKeyguardSecure = 31;
        static final int TRANSACTION_isLayerTracing = 104;
        static final int TRANSACTION_isRotationFrozen = 51;
        static final int TRANSACTION_isSafeModeEnabled = 70;
        static final int TRANSACTION_isTaskSnapshotSupported = 122;
        static final int TRANSACTION_isViewServerRunning = 3;
        static final int TRANSACTION_isWindowToken = 16;
        static final int TRANSACTION_isWindowTraceEnabled = 90;
        static final int TRANSACTION_lockNow = 69;
        static final int TRANSACTION_mirrorDisplay = 106;
        static final int TRANSACTION_modifyDisplayWindowInsets = 108;
        static final int TRANSACTION_openSession = 4;
        static final int TRANSACTION_overridePendingAppTransitionMultiThumbFuture = 22;
        static final int TRANSACTION_overridePendingAppTransitionRemote = 23;
        static final int TRANSACTION_reenableKeyguard = 28;
        static final int TRANSACTION_refreshScreenCaptureDisabled = 43;
        static final int TRANSACTION_registerCrossWindowBlurEnabledListener = 120;
        static final int TRANSACTION_registerDisplayFoldListener = 84;
        static final int TRANSACTION_registerDisplayWindowListener = 86;
        static final int TRANSACTION_registerPinnedTaskListener = 76;
        static final int TRANSACTION_registerShortcutKey = 80;
        static final int TRANSACTION_registerSystemGestureExclusionListener = 60;
        static final int TRANSACTION_registerWallpaperVisibilityListener = 58;
        static final int TRANSACTION_removeRotationWatcher = 47;
        static final int TRANSACTION_removeWindowToken = 18;
        static final int TRANSACTION_requestAppKeyboardShortcuts = 77;
        static final int TRANSACTION_requestAssistScreenshot = 62;
        static final int TRANSACTION_requestScrollCapture = 112;
        static final int TRANSACTION_screenshotWallpaper = 57;
        static final int TRANSACTION_setAnimationScale = 37;
        static final int TRANSACTION_setAnimationScales = 38;
        static final int TRANSACTION_setDisplayHashThrottlingEnabled = 116;
        static final int TRANSACTION_setDisplayImePolicy = 101;
        static final int TRANSACTION_setDisplayWindowInsetsController = 107;
        static final int TRANSACTION_setDisplayWindowRotationController = 19;
        static final int TRANSACTION_setDockedTaskDividerTouchRegion = 75;
        static final int TRANSACTION_setEventDispatching = 15;
        static final int TRANSACTION_setFixedToUserRotation = 55;
        static final int TRANSACTION_setForcedDisplayDensityForUser = 12;
        static final int TRANSACTION_setForcedDisplayScalingMode = 14;
        static final int TRANSACTION_setForcedDisplaySize = 8;
        static final int TRANSACTION_setForwardedInsets = 79;
        static final int TRANSACTION_setIgnoreOrientationRequest = 56;
        static final int TRANSACTION_setInTouchMode = 40;
        static final int TRANSACTION_setLayerTracing = 105;
        static final int TRANSACTION_setLayerTracingFlags = 111;
        static final int TRANSACTION_setNavBarVirtualKeyHapticFeedbackEnabled = 66;
        static final int TRANSACTION_setRecentsVisibility = 64;
        static final int TRANSACTION_setRemoveContentMode = 95;
        static final int TRANSACTION_setShellRootAccessibilityWindow = 21;
        static final int TRANSACTION_setShouldShowSystemDecors = 99;
        static final int TRANSACTION_setShouldShowWithInsecureKeyguard = 97;
        static final int TRANSACTION_setStrictModeVisualIndicatorPreference = 42;
        static final int TRANSACTION_setSwitchingUser = 33;
        static final int TRANSACTION_setWindowingMode = 93;
        static final int TRANSACTION_shouldShowSystemDecors = 98;
        static final int TRANSACTION_shouldShowWithInsecureKeyguard = 96;
        static final int TRANSACTION_showGlobalActions = 110;
        static final int TRANSACTION_showStrictModeViolation = 41;
        static final int TRANSACTION_startFreezingScreen = 25;
        static final int TRANSACTION_startViewServer = 1;
        static final int TRANSACTION_startWindowTrace = 88;
        static final int TRANSACTION_stopFreezingScreen = 26;
        static final int TRANSACTION_stopViewServer = 2;
        static final int TRANSACTION_stopWindowTrace = 89;
        static final int TRANSACTION_syncInputTransactions = 103;
        static final int TRANSACTION_thawDisplayRotation = 53;
        static final int TRANSACTION_thawRotation = 50;
        static final int TRANSACTION_unregisterCrossWindowBlurEnabledListener = 121;
        static final int TRANSACTION_unregisterDisplayFoldListener = 85;
        static final int TRANSACTION_unregisterDisplayWindowListener = 87;
        static final int TRANSACTION_unregisterSystemGestureExclusionListener = 61;
        static final int TRANSACTION_unregisterWallpaperVisibilityListener = 59;
        static final int TRANSACTION_updateRotation = 44;
        static final int TRANSACTION_updateStaticPrivacyIndicatorBounds = 65;
        static final int TRANSACTION_useBLAST = 5;
        static final int TRANSACTION_verifyDisplayHash = 115;
        static final int TRANSACTION_watchRotation = 46;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWindowManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IWindowManager)) {
                return new Proxy(obj);
            }
            return (IWindowManager) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "startViewServer";
                case 2:
                    return "stopViewServer";
                case 3:
                    return "isViewServerRunning";
                case 4:
                    return "openSession";
                case 5:
                    return "useBLAST";
                case 6:
                    return "getInitialDisplaySize";
                case 7:
                    return "getBaseDisplaySize";
                case 8:
                    return "setForcedDisplaySize";
                case 9:
                    return "clearForcedDisplaySize";
                case 10:
                    return "getInitialDisplayDensity";
                case 11:
                    return "getBaseDisplayDensity";
                case 12:
                    return "setForcedDisplayDensityForUser";
                case 13:
                    return "clearForcedDisplayDensityForUser";
                case 14:
                    return "setForcedDisplayScalingMode";
                case 15:
                    return "setEventDispatching";
                case 16:
                    return "isWindowToken";
                case 17:
                    return "addWindowToken";
                case 18:
                    return "removeWindowToken";
                case 19:
                    return "setDisplayWindowRotationController";
                case 20:
                    return "addShellRoot";
                case 21:
                    return "setShellRootAccessibilityWindow";
                case 22:
                    return "overridePendingAppTransitionMultiThumbFuture";
                case 23:
                    return "overridePendingAppTransitionRemote";
                case 24:
                    return "endProlongedAnimations";
                case 25:
                    return "startFreezingScreen";
                case 26:
                    return "stopFreezingScreen";
                case 27:
                    return "disableKeyguard";
                case 28:
                    return "reenableKeyguard";
                case 29:
                    return "exitKeyguardSecurely";
                case 30:
                    return "isKeyguardLocked";
                case 31:
                    return "isKeyguardSecure";
                case 32:
                    return "dismissKeyguard";
                case 33:
                    return "setSwitchingUser";
                case 34:
                    return "closeSystemDialogs";
                case 35:
                    return "getAnimationScale";
                case 36:
                    return "getAnimationScales";
                case 37:
                    return "setAnimationScale";
                case 38:
                    return "setAnimationScales";
                case 39:
                    return "getCurrentAnimatorScale";
                case 40:
                    return "setInTouchMode";
                case 41:
                    return "showStrictModeViolation";
                case 42:
                    return "setStrictModeVisualIndicatorPreference";
                case 43:
                    return "refreshScreenCaptureDisabled";
                case 44:
                    return "updateRotation";
                case 45:
                    return "getDefaultDisplayRotation";
                case 46:
                    return "watchRotation";
                case 47:
                    return "removeRotationWatcher";
                case 48:
                    return "getPreferredOptionsPanelGravity";
                case 49:
                    return "freezeRotation";
                case 50:
                    return "thawRotation";
                case 51:
                    return "isRotationFrozen";
                case 52:
                    return "freezeDisplayRotation";
                case 53:
                    return "thawDisplayRotation";
                case 54:
                    return "isDisplayRotationFrozen";
                case 55:
                    return "setFixedToUserRotation";
                case 56:
                    return "setIgnoreOrientationRequest";
                case 57:
                    return "screenshotWallpaper";
                case 58:
                    return "registerWallpaperVisibilityListener";
                case 59:
                    return "unregisterWallpaperVisibilityListener";
                case 60:
                    return "registerSystemGestureExclusionListener";
                case 61:
                    return "unregisterSystemGestureExclusionListener";
                case 62:
                    return "requestAssistScreenshot";
                case 63:
                    return "hideTransientBars";
                case 64:
                    return "setRecentsVisibility";
                case 65:
                    return "updateStaticPrivacyIndicatorBounds";
                case 66:
                    return "setNavBarVirtualKeyHapticFeedbackEnabled";
                case 67:
                    return "hasNavigationBar";
                case 68:
                    return "getNavBarPosition";
                case 69:
                    return "lockNow";
                case 70:
                    return "isSafeModeEnabled";
                case 71:
                    return "enableScreenIfNeeded";
                case 72:
                    return "clearWindowContentFrameStats";
                case 73:
                    return "getWindowContentFrameStats";
                case 74:
                    return "getDockedStackSide";
                case 75:
                    return "setDockedTaskDividerTouchRegion";
                case 76:
                    return "registerPinnedTaskListener";
                case 77:
                    return "requestAppKeyboardShortcuts";
                case 78:
                    return "getStableInsets";
                case 79:
                    return "setForwardedInsets";
                case 80:
                    return "registerShortcutKey";
                case 81:
                    return "createInputConsumer";
                case 82:
                    return "destroyInputConsumer";
                case 83:
                    return "getCurrentImeTouchRegion";
                case 84:
                    return "registerDisplayFoldListener";
                case 85:
                    return "unregisterDisplayFoldListener";
                case 86:
                    return "registerDisplayWindowListener";
                case 87:
                    return "unregisterDisplayWindowListener";
                case 88:
                    return "startWindowTrace";
                case 89:
                    return "stopWindowTrace";
                case 90:
                    return "isWindowTraceEnabled";
                case 91:
                    return "dontOverrideDisplayInfo";
                case 92:
                    return "getWindowingMode";
                case 93:
                    return "setWindowingMode";
                case 94:
                    return "getRemoveContentMode";
                case 95:
                    return "setRemoveContentMode";
                case 96:
                    return "shouldShowWithInsecureKeyguard";
                case 97:
                    return "setShouldShowWithInsecureKeyguard";
                case 98:
                    return "shouldShowSystemDecors";
                case 99:
                    return "setShouldShowSystemDecors";
                case 100:
                    return "getDisplayImePolicy";
                case 101:
                    return "setDisplayImePolicy";
                case 102:
                    return "injectInputAfterTransactionsApplied";
                case 103:
                    return "syncInputTransactions";
                case 104:
                    return "isLayerTracing";
                case 105:
                    return "setLayerTracing";
                case 106:
                    return "mirrorDisplay";
                case 107:
                    return "setDisplayWindowInsetsController";
                case 108:
                    return "modifyDisplayWindowInsets";
                case 109:
                    return "getWindowInsets";
                case 110:
                    return "showGlobalActions";
                case 111:
                    return "setLayerTracingFlags";
                case 112:
                    return "requestScrollCapture";
                case 113:
                    return "holdLock";
                case 114:
                    return "getSupportedDisplayHashAlgorithms";
                case 115:
                    return "verifyDisplayHash";
                case 116:
                    return "setDisplayHashThrottlingEnabled";
                case 117:
                    return "attachWindowContextToDisplayArea";
                case 118:
                    return "attachWindowContextToWindowToken";
                case 119:
                    return "detachWindowContextFromWindowContainer";
                case 120:
                    return "registerCrossWindowBlurEnabledListener";
                case 121:
                    return "unregisterCrossWindowBlurEnabledListener";
                case 122:
                    return "isTaskSnapshotSupported";
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
            Bundle _arg3;
            RemoteAnimationAdapter _arg0;
            CharSequence _arg1;
            boolean _arg02;
            Bundle _arg03;
            Rect _arg04;
            Insets _arg12;
            InputEvent _arg05;
            InsetsState _arg13;
            WindowManager.LayoutParams _arg06;
            DisplayHash _arg07;
            Bundle _arg32;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    boolean _arg08 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            boolean startViewServer = startViewServer(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(startViewServer ? 1 : 0);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            boolean stopViewServer = stopViewServer();
                            reply.writeNoException();
                            reply.writeInt(stopViewServer ? 1 : 0);
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isViewServerRunning = isViewServerRunning();
                            reply.writeNoException();
                            reply.writeInt(isViewServerRunning ? 1 : 0);
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            IWindowSession _result = openSession(IWindowSessionCallback.Stub.asInterface(data.readStrongBinder()));
                            reply.writeNoException();
                            reply.writeStrongBinder(_result != null ? _result.asBinder() : null);
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            boolean useBLAST = useBLAST();
                            reply.writeNoException();
                            reply.writeInt(useBLAST ? 1 : 0);
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg09 = data.readInt();
                            Point _arg14 = new Point();
                            getInitialDisplaySize(_arg09, _arg14);
                            reply.writeNoException();
                            reply.writeInt(1);
                            _arg14.writeToParcel(reply, 1);
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg010 = data.readInt();
                            Point _arg15 = new Point();
                            getBaseDisplaySize(_arg010, _arg15);
                            reply.writeNoException();
                            reply.writeInt(1);
                            _arg15.writeToParcel(reply, 1);
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg011 = data.readInt();
                            int _arg16 = data.readInt();
                            int _arg2 = data.readInt();
                            setForcedDisplaySize(_arg011, _arg16, _arg2);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            clearForcedDisplaySize(data.readInt());
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            int _result2 = getInitialDisplayDensity(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            int _result3 = getBaseDisplayDensity(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result3);
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg012 = data.readInt();
                            int _arg17 = data.readInt();
                            int _arg22 = data.readInt();
                            setForcedDisplayDensityForUser(_arg012, _arg17, _arg22);
                            reply.writeNoException();
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            clearForcedDisplayDensityForUser(data.readInt(), data.readInt());
                            reply.writeNoException();
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            setForcedDisplayScalingMode(data.readInt(), data.readInt());
                            reply.writeNoException();
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg08 = true;
                            }
                            setEventDispatching(_arg08);
                            reply.writeNoException();
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isWindowToken = isWindowToken(data.readStrongBinder());
                            reply.writeNoException();
                            reply.writeInt(isWindowToken ? 1 : 0);
                            return true;
                        case 17:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg013 = data.readStrongBinder();
                            int _arg18 = data.readInt();
                            int _arg23 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg3 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            addWindowToken(_arg013, _arg18, _arg23, _arg3);
                            reply.writeNoException();
                            return true;
                        case 18:
                            data.enforceInterface(DESCRIPTOR);
                            removeWindowToken(data.readStrongBinder(), data.readInt());
                            reply.writeNoException();
                            return true;
                        case 19:
                            data.enforceInterface(DESCRIPTOR);
                            setDisplayWindowRotationController(IDisplayWindowRotationController.Stub.asInterface(data.readStrongBinder()));
                            reply.writeNoException();
                            return true;
                        case 20:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg014 = data.readInt();
                            IWindow _arg19 = IWindow.Stub.asInterface(data.readStrongBinder());
                            int _arg24 = data.readInt();
                            SurfaceControl _result4 = addShellRoot(_arg014, _arg19, _arg24);
                            reply.writeNoException();
                            if (_result4 != null) {
                                reply.writeInt(1);
                                _result4.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 21:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg015 = data.readInt();
                            int _arg110 = data.readInt();
                            IWindow _arg25 = IWindow.Stub.asInterface(data.readStrongBinder());
                            setShellRootAccessibilityWindow(_arg015, _arg110, _arg25);
                            reply.writeNoException();
                            return true;
                        case 22:
                            data.enforceInterface(DESCRIPTOR);
                            IAppTransitionAnimationSpecsFuture _arg016 = IAppTransitionAnimationSpecsFuture.Stub.asInterface(data.readStrongBinder());
                            IRemoteCallback _arg111 = IRemoteCallback.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg08 = true;
                            }
                            int _arg33 = data.readInt();
                            overridePendingAppTransitionMultiThumbFuture(_arg016, _arg111, _arg08, _arg33);
                            reply.writeNoException();
                            return true;
                        case 23:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = RemoteAnimationAdapter.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            overridePendingAppTransitionRemote(_arg0, data.readInt());
                            reply.writeNoException();
                            return true;
                        case 24:
                            data.enforceInterface(DESCRIPTOR);
                            endProlongedAnimations();
                            reply.writeNoException();
                            return true;
                        case 25:
                            data.enforceInterface(DESCRIPTOR);
                            startFreezingScreen(data.readInt(), data.readInt());
                            reply.writeNoException();
                            return true;
                        case 26:
                            data.enforceInterface(DESCRIPTOR);
                            stopFreezingScreen();
                            reply.writeNoException();
                            return true;
                        case 27:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg017 = data.readStrongBinder();
                            String _arg112 = data.readString();
                            int _arg26 = data.readInt();
                            disableKeyguard(_arg017, _arg112, _arg26);
                            reply.writeNoException();
                            return true;
                        case 28:
                            data.enforceInterface(DESCRIPTOR);
                            reenableKeyguard(data.readStrongBinder(), data.readInt());
                            reply.writeNoException();
                            return true;
                        case 29:
                            data.enforceInterface(DESCRIPTOR);
                            exitKeyguardSecurely(IOnKeyguardExitResult.Stub.asInterface(data.readStrongBinder()));
                            reply.writeNoException();
                            return true;
                        case 30:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isKeyguardLocked = isKeyguardLocked();
                            reply.writeNoException();
                            reply.writeInt(isKeyguardLocked ? 1 : 0);
                            return true;
                        case 31:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isKeyguardSecure = isKeyguardSecure(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(isKeyguardSecure ? 1 : 0);
                            return true;
                        case 32:
                            data.enforceInterface(DESCRIPTOR);
                            IKeyguardDismissCallback _arg018 = IKeyguardDismissCallback.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg1 = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            dismissKeyguard(_arg018, _arg1);
                            reply.writeNoException();
                            return true;
                        case 33:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg08 = true;
                            }
                            setSwitchingUser(_arg08);
                            reply.writeNoException();
                            return true;
                        case 34:
                            data.enforceInterface(DESCRIPTOR);
                            closeSystemDialogs(data.readString());
                            reply.writeNoException();
                            return true;
                        case 35:
                            data.enforceInterface(DESCRIPTOR);
                            float _result5 = getAnimationScale(data.readInt());
                            reply.writeNoException();
                            reply.writeFloat(_result5);
                            return true;
                        case 36:
                            data.enforceInterface(DESCRIPTOR);
                            float[] _result6 = getAnimationScales();
                            reply.writeNoException();
                            reply.writeFloatArray(_result6);
                            return true;
                        case 37:
                            data.enforceInterface(DESCRIPTOR);
                            setAnimationScale(data.readInt(), data.readFloat());
                            reply.writeNoException();
                            return true;
                        case 38:
                            data.enforceInterface(DESCRIPTOR);
                            setAnimationScales(data.createFloatArray());
                            reply.writeNoException();
                            return true;
                        case 39:
                            data.enforceInterface(DESCRIPTOR);
                            float _result7 = getCurrentAnimatorScale();
                            reply.writeNoException();
                            reply.writeFloat(_result7);
                            return true;
                        case 40:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg08 = true;
                            }
                            setInTouchMode(_arg08);
                            reply.writeNoException();
                            return true;
                        case 41:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg08 = true;
                            }
                            showStrictModeViolation(_arg08);
                            reply.writeNoException();
                            return true;
                        case 42:
                            data.enforceInterface(DESCRIPTOR);
                            setStrictModeVisualIndicatorPreference(data.readString());
                            reply.writeNoException();
                            return true;
                        case 43:
                            data.enforceInterface(DESCRIPTOR);
                            refreshScreenCaptureDisabled(data.readInt());
                            reply.writeNoException();
                            return true;
                        case 44:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = true;
                            } else {
                                _arg02 = false;
                            }
                            if (data.readInt() != 0) {
                                _arg08 = true;
                            }
                            updateRotation(_arg02, _arg08);
                            reply.writeNoException();
                            return true;
                        case 45:
                            data.enforceInterface(DESCRIPTOR);
                            int _result8 = getDefaultDisplayRotation();
                            reply.writeNoException();
                            reply.writeInt(_result8);
                            return true;
                        case 46:
                            data.enforceInterface(DESCRIPTOR);
                            int _result9 = watchRotation(IRotationWatcher.Stub.asInterface(data.readStrongBinder()), data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result9);
                            return true;
                        case 47:
                            data.enforceInterface(DESCRIPTOR);
                            removeRotationWatcher(IRotationWatcher.Stub.asInterface(data.readStrongBinder()));
                            reply.writeNoException();
                            return true;
                        case 48:
                            data.enforceInterface(DESCRIPTOR);
                            int _result10 = getPreferredOptionsPanelGravity(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result10);
                            return true;
                        case 49:
                            data.enforceInterface(DESCRIPTOR);
                            freezeRotation(data.readInt());
                            reply.writeNoException();
                            return true;
                        case 50:
                            data.enforceInterface(DESCRIPTOR);
                            thawRotation();
                            reply.writeNoException();
                            return true;
                        case 51:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isRotationFrozen = isRotationFrozen();
                            reply.writeNoException();
                            reply.writeInt(isRotationFrozen ? 1 : 0);
                            return true;
                        case 52:
                            data.enforceInterface(DESCRIPTOR);
                            freezeDisplayRotation(data.readInt(), data.readInt());
                            reply.writeNoException();
                            return true;
                        case 53:
                            data.enforceInterface(DESCRIPTOR);
                            thawDisplayRotation(data.readInt());
                            reply.writeNoException();
                            return true;
                        case 54:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isDisplayRotationFrozen = isDisplayRotationFrozen(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(isDisplayRotationFrozen ? 1 : 0);
                            return true;
                        case 55:
                            data.enforceInterface(DESCRIPTOR);
                            setFixedToUserRotation(data.readInt(), data.readInt());
                            reply.writeNoException();
                            return true;
                        case 56:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg019 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg08 = true;
                            }
                            setIgnoreOrientationRequest(_arg019, _arg08);
                            reply.writeNoException();
                            return true;
                        case 57:
                            data.enforceInterface(DESCRIPTOR);
                            Bitmap _result11 = screenshotWallpaper();
                            reply.writeNoException();
                            if (_result11 != null) {
                                reply.writeInt(1);
                                _result11.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 58:
                            data.enforceInterface(DESCRIPTOR);
                            boolean registerWallpaperVisibilityListener = registerWallpaperVisibilityListener(IWallpaperVisibilityListener.Stub.asInterface(data.readStrongBinder()), data.readInt());
                            reply.writeNoException();
                            reply.writeInt(registerWallpaperVisibilityListener ? 1 : 0);
                            return true;
                        case 59:
                            data.enforceInterface(DESCRIPTOR);
                            unregisterWallpaperVisibilityListener(IWallpaperVisibilityListener.Stub.asInterface(data.readStrongBinder()), data.readInt());
                            reply.writeNoException();
                            return true;
                        case 60:
                            data.enforceInterface(DESCRIPTOR);
                            registerSystemGestureExclusionListener(ISystemGestureExclusionListener.Stub.asInterface(data.readStrongBinder()), data.readInt());
                            reply.writeNoException();
                            return true;
                        case 61:
                            data.enforceInterface(DESCRIPTOR);
                            unregisterSystemGestureExclusionListener(ISystemGestureExclusionListener.Stub.asInterface(data.readStrongBinder()), data.readInt());
                            reply.writeNoException();
                            return true;
                        case 62:
                            data.enforceInterface(DESCRIPTOR);
                            boolean requestAssistScreenshot = requestAssistScreenshot(IAssistDataReceiver.Stub.asInterface(data.readStrongBinder()));
                            reply.writeNoException();
                            reply.writeInt(requestAssistScreenshot ? 1 : 0);
                            return true;
                        case 63:
                            data.enforceInterface(DESCRIPTOR);
                            hideTransientBars(data.readInt());
                            return true;
                        case 64:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg08 = true;
                            }
                            setRecentsVisibility(_arg08);
                            return true;
                        case 65:
                            data.enforceInterface(DESCRIPTOR);
                            updateStaticPrivacyIndicatorBounds(data.readInt(), (Rect[]) data.createTypedArray(Rect.CREATOR));
                            return true;
                        case 66:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg08 = true;
                            }
                            setNavBarVirtualKeyHapticFeedbackEnabled(_arg08);
                            reply.writeNoException();
                            return true;
                        case 67:
                            data.enforceInterface(DESCRIPTOR);
                            boolean hasNavigationBar = hasNavigationBar(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(hasNavigationBar ? 1 : 0);
                            return true;
                        case 68:
                            data.enforceInterface(DESCRIPTOR);
                            int _result12 = getNavBarPosition(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result12);
                            return true;
                        case 69:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            lockNow(_arg03);
                            reply.writeNoException();
                            return true;
                        case 70:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isSafeModeEnabled = isSafeModeEnabled();
                            reply.writeNoException();
                            reply.writeInt(isSafeModeEnabled ? 1 : 0);
                            return true;
                        case 71:
                            data.enforceInterface(DESCRIPTOR);
                            enableScreenIfNeeded();
                            reply.writeNoException();
                            return true;
                        case 72:
                            data.enforceInterface(DESCRIPTOR);
                            boolean clearWindowContentFrameStats = clearWindowContentFrameStats(data.readStrongBinder());
                            reply.writeNoException();
                            reply.writeInt(clearWindowContentFrameStats ? 1 : 0);
                            return true;
                        case 73:
                            data.enforceInterface(DESCRIPTOR);
                            WindowContentFrameStats _result13 = getWindowContentFrameStats(data.readStrongBinder());
                            reply.writeNoException();
                            if (_result13 != null) {
                                reply.writeInt(1);
                                _result13.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 74:
                            data.enforceInterface(DESCRIPTOR);
                            int _result14 = getDockedStackSide();
                            reply.writeNoException();
                            reply.writeInt(_result14);
                            return true;
                        case 75:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = Rect.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            setDockedTaskDividerTouchRegion(_arg04);
                            reply.writeNoException();
                            return true;
                        case 76:
                            data.enforceInterface(DESCRIPTOR);
                            registerPinnedTaskListener(data.readInt(), IPinnedTaskListener.Stub.asInterface(data.readStrongBinder()));
                            reply.writeNoException();
                            return true;
                        case 77:
                            data.enforceInterface(DESCRIPTOR);
                            requestAppKeyboardShortcuts(IResultReceiver.Stub.asInterface(data.readStrongBinder()), data.readInt());
                            reply.writeNoException();
                            return true;
                        case 78:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg020 = data.readInt();
                            Rect _arg113 = new Rect();
                            getStableInsets(_arg020, _arg113);
                            reply.writeNoException();
                            reply.writeInt(1);
                            _arg113.writeToParcel(reply, 1);
                            return true;
                        case 79:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg021 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg12 = Insets.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            setForwardedInsets(_arg021, _arg12);
                            reply.writeNoException();
                            return true;
                        case 80:
                            data.enforceInterface(DESCRIPTOR);
                            registerShortcutKey(data.readLong(), IShortcutService.Stub.asInterface(data.readStrongBinder()));
                            reply.writeNoException();
                            return true;
                        case 81:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg022 = data.readStrongBinder();
                            String _arg114 = data.readString();
                            int _arg27 = data.readInt();
                            InputChannel _arg34 = new InputChannel();
                            createInputConsumer(_arg022, _arg114, _arg27, _arg34);
                            reply.writeNoException();
                            reply.writeInt(1);
                            _arg34.writeToParcel(reply, 1);
                            return true;
                        case 82:
                            data.enforceInterface(DESCRIPTOR);
                            boolean destroyInputConsumer = destroyInputConsumer(data.readString(), data.readInt());
                            reply.writeNoException();
                            reply.writeInt(destroyInputConsumer ? 1 : 0);
                            return true;
                        case 83:
                            data.enforceInterface(DESCRIPTOR);
                            Region _result15 = getCurrentImeTouchRegion();
                            reply.writeNoException();
                            if (_result15 != null) {
                                reply.writeInt(1);
                                _result15.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 84:
                            data.enforceInterface(DESCRIPTOR);
                            registerDisplayFoldListener(IDisplayFoldListener.Stub.asInterface(data.readStrongBinder()));
                            reply.writeNoException();
                            return true;
                        case 85:
                            data.enforceInterface(DESCRIPTOR);
                            unregisterDisplayFoldListener(IDisplayFoldListener.Stub.asInterface(data.readStrongBinder()));
                            reply.writeNoException();
                            return true;
                        case 86:
                            data.enforceInterface(DESCRIPTOR);
                            registerDisplayWindowListener(IDisplayWindowListener.Stub.asInterface(data.readStrongBinder()));
                            reply.writeNoException();
                            return true;
                        case 87:
                            data.enforceInterface(DESCRIPTOR);
                            unregisterDisplayWindowListener(IDisplayWindowListener.Stub.asInterface(data.readStrongBinder()));
                            reply.writeNoException();
                            return true;
                        case 88:
                            data.enforceInterface(DESCRIPTOR);
                            startWindowTrace();
                            reply.writeNoException();
                            return true;
                        case 89:
                            data.enforceInterface(DESCRIPTOR);
                            stopWindowTrace();
                            reply.writeNoException();
                            return true;
                        case 90:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isWindowTraceEnabled = isWindowTraceEnabled();
                            reply.writeNoException();
                            reply.writeInt(isWindowTraceEnabled ? 1 : 0);
                            return true;
                        case 91:
                            data.enforceInterface(DESCRIPTOR);
                            dontOverrideDisplayInfo(data.readInt());
                            reply.writeNoException();
                            return true;
                        case 92:
                            data.enforceInterface(DESCRIPTOR);
                            int _result16 = getWindowingMode(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result16);
                            return true;
                        case 93:
                            data.enforceInterface(DESCRIPTOR);
                            setWindowingMode(data.readInt(), data.readInt());
                            reply.writeNoException();
                            return true;
                        case 94:
                            data.enforceInterface(DESCRIPTOR);
                            int _result17 = getRemoveContentMode(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result17);
                            return true;
                        case 95:
                            data.enforceInterface(DESCRIPTOR);
                            setRemoveContentMode(data.readInt(), data.readInt());
                            reply.writeNoException();
                            return true;
                        case 96:
                            data.enforceInterface(DESCRIPTOR);
                            boolean shouldShowWithInsecureKeyguard = shouldShowWithInsecureKeyguard(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(shouldShowWithInsecureKeyguard ? 1 : 0);
                            return true;
                        case 97:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg023 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg08 = true;
                            }
                            setShouldShowWithInsecureKeyguard(_arg023, _arg08);
                            reply.writeNoException();
                            return true;
                        case 98:
                            data.enforceInterface(DESCRIPTOR);
                            boolean shouldShowSystemDecors = shouldShowSystemDecors(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(shouldShowSystemDecors ? 1 : 0);
                            return true;
                        case 99:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg024 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg08 = true;
                            }
                            setShouldShowSystemDecors(_arg024, _arg08);
                            reply.writeNoException();
                            return true;
                        case 100:
                            data.enforceInterface(DESCRIPTOR);
                            int _result18 = getDisplayImePolicy(data.readInt());
                            reply.writeNoException();
                            reply.writeInt(_result18);
                            return true;
                        case 101:
                            data.enforceInterface(DESCRIPTOR);
                            setDisplayImePolicy(data.readInt(), data.readInt());
                            reply.writeNoException();
                            return true;
                        case 102:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg05 = InputEvent.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            int _arg115 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg08 = true;
                            }
                            boolean injectInputAfterTransactionsApplied = injectInputAfterTransactionsApplied(_arg05, _arg115, _arg08);
                            reply.writeNoException();
                            reply.writeInt(injectInputAfterTransactionsApplied ? 1 : 0);
                            return true;
                        case 103:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg08 = true;
                            }
                            syncInputTransactions(_arg08);
                            reply.writeNoException();
                            return true;
                        case 104:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isLayerTracing = isLayerTracing();
                            reply.writeNoException();
                            reply.writeInt(isLayerTracing ? 1 : 0);
                            return true;
                        case 105:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg08 = true;
                            }
                            setLayerTracing(_arg08);
                            reply.writeNoException();
                            return true;
                        case 106:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg025 = data.readInt();
                            SurfaceControl _arg116 = new SurfaceControl();
                            boolean mirrorDisplay = mirrorDisplay(_arg025, _arg116);
                            reply.writeNoException();
                            reply.writeInt(mirrorDisplay ? 1 : 0);
                            reply.writeInt(1);
                            _arg116.writeToParcel(reply, 1);
                            return true;
                        case 107:
                            data.enforceInterface(DESCRIPTOR);
                            setDisplayWindowInsetsController(data.readInt(), IDisplayWindowInsetsController.Stub.asInterface(data.readStrongBinder()));
                            reply.writeNoException();
                            return true;
                        case 108:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg026 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg13 = InsetsState.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            modifyDisplayWindowInsets(_arg026, _arg13);
                            reply.writeNoException();
                            return true;
                        case 109:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg06 = WindowManager.LayoutParams.CREATOR.createFromParcel(data);
                            } else {
                                _arg06 = null;
                            }
                            int _arg117 = data.readInt();
                            InsetsState _arg28 = new InsetsState();
                            boolean windowInsets = getWindowInsets(_arg06, _arg117, _arg28);
                            reply.writeNoException();
                            reply.writeInt(windowInsets ? 1 : 0);
                            reply.writeInt(1);
                            _arg28.writeToParcel(reply, 1);
                            return true;
                        case 110:
                            data.enforceInterface(DESCRIPTOR);
                            showGlobalActions();
                            reply.writeNoException();
                            return true;
                        case 111:
                            data.enforceInterface(DESCRIPTOR);
                            setLayerTracingFlags(data.readInt());
                            reply.writeNoException();
                            return true;
                        case 112:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg027 = data.readInt();
                            IBinder _arg118 = data.readStrongBinder();
                            int _arg29 = data.readInt();
                            IScrollCaptureResponseListener _arg35 = IScrollCaptureResponseListener.Stub.asInterface(data.readStrongBinder());
                            requestScrollCapture(_arg027, _arg118, _arg29, _arg35);
                            reply.writeNoException();
                            return true;
                        case 113:
                            data.enforceInterface(DESCRIPTOR);
                            holdLock(data.readStrongBinder(), data.readInt());
                            reply.writeNoException();
                            return true;
                        case 114:
                            data.enforceInterface(DESCRIPTOR);
                            String[] _result19 = getSupportedDisplayHashAlgorithms();
                            reply.writeNoException();
                            reply.writeStringArray(_result19);
                            return true;
                        case 115:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg07 = DisplayHash.CREATOR.createFromParcel(data);
                            } else {
                                _arg07 = null;
                            }
                            VerifiedDisplayHash _result20 = verifyDisplayHash(_arg07);
                            reply.writeNoException();
                            if (_result20 != null) {
                                reply.writeInt(1);
                                _result20.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 116:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg08 = true;
                            }
                            setDisplayHashThrottlingEnabled(_arg08);
                            reply.writeNoException();
                            return true;
                        case 117:
                            data.enforceInterface(DESCRIPTOR);
                            IBinder _arg028 = data.readStrongBinder();
                            int _arg119 = data.readInt();
                            int _arg210 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg32 = Bundle.CREATOR.createFromParcel(data);
                            } else {
                                _arg32 = null;
                            }
                            Configuration _result21 = attachWindowContextToDisplayArea(_arg028, _arg119, _arg210, _arg32);
                            reply.writeNoException();
                            if (_result21 != null) {
                                reply.writeInt(1);
                                _result21.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 118:
                            data.enforceInterface(DESCRIPTOR);
                            attachWindowContextToWindowToken(data.readStrongBinder(), data.readStrongBinder());
                            reply.writeNoException();
                            return true;
                        case 119:
                            data.enforceInterface(DESCRIPTOR);
                            detachWindowContextFromWindowContainer(data.readStrongBinder());
                            reply.writeNoException();
                            return true;
                        case 120:
                            data.enforceInterface(DESCRIPTOR);
                            boolean registerCrossWindowBlurEnabledListener = registerCrossWindowBlurEnabledListener(ICrossWindowBlurEnabledListener.Stub.asInterface(data.readStrongBinder()));
                            reply.writeNoException();
                            reply.writeInt(registerCrossWindowBlurEnabledListener ? 1 : 0);
                            return true;
                        case 121:
                            data.enforceInterface(DESCRIPTOR);
                            unregisterCrossWindowBlurEnabledListener(ICrossWindowBlurEnabledListener.Stub.asInterface(data.readStrongBinder()));
                            reply.writeNoException();
                            return true;
                        case 122:
                            data.enforceInterface(DESCRIPTOR);
                            boolean isTaskSnapshotSupported = isTaskSnapshotSupported();
                            reply.writeNoException();
                            reply.writeInt(isTaskSnapshotSupported ? 1 : 0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class Proxy implements IWindowManager {
            public static IWindowManager sDefaultImpl;
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

            @Override // android.view.IWindowManager
            public boolean startViewServer(int port) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(port);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startViewServer(port);
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

            @Override // android.view.IWindowManager
            public boolean stopViewServer() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopViewServer();
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

            @Override // android.view.IWindowManager
            public boolean isViewServerRunning() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isViewServerRunning();
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

            @Override // android.view.IWindowManager
            public IWindowSession openSession(IWindowSessionCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openSession(callback);
                    }
                    _reply.readException();
                    IWindowSession _result = IWindowSession.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean useBLAST() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().useBLAST();
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

            @Override // android.view.IWindowManager
            public void getInitialDisplaySize(int displayId, Point size) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        if (_reply.readInt() != 0) {
                            size.readFromParcel(_reply);
                        }
                        return;
                    }
                    Stub.getDefaultImpl().getInitialDisplaySize(displayId, size);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void getBaseDisplaySize(int displayId, Point size) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        if (_reply.readInt() != 0) {
                            size.readFromParcel(_reply);
                        }
                        return;
                    }
                    Stub.getDefaultImpl().getBaseDisplaySize(displayId, size);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setForcedDisplaySize(int displayId, int width, int height) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeInt(width);
                    _data.writeInt(height);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setForcedDisplaySize(displayId, width, height);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void clearForcedDisplaySize(int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().clearForcedDisplaySize(displayId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public int getInitialDisplayDensity(int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInitialDisplayDensity(displayId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public int getBaseDisplayDensity(int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBaseDisplayDensity(displayId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setForcedDisplayDensityForUser(int displayId, int density, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeInt(density);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setForcedDisplayDensityForUser(displayId, density, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void clearForcedDisplayDensityForUser(int displayId, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().clearForcedDisplayDensityForUser(displayId, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setForcedDisplayScalingMode(int displayId, int mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeInt(mode);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setForcedDisplayScalingMode(displayId, mode);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setEventDispatching(boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setEventDispatching(enabled);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean isWindowToken(IBinder binder) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(binder);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isWindowToken(binder);
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

            @Override // android.view.IWindowManager
            public void addWindowToken(IBinder token, int type, int displayId, Bundle options) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(type);
                    _data.writeInt(displayId);
                    if (options != null) {
                        _data.writeInt(1);
                        options.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addWindowToken(token, type, displayId, options);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void removeWindowToken(IBinder token, int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeWindowToken(token, displayId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setDisplayWindowRotationController(IDisplayWindowRotationController controller) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(controller != null ? controller.asBinder() : null);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setDisplayWindowRotationController(controller);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public SurfaceControl addShellRoot(int displayId, IWindow client, int shellRootLayer) throws RemoteException {
                SurfaceControl _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeStrongBinder(client != null ? client.asBinder() : null);
                    _data.writeInt(shellRootLayer);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addShellRoot(displayId, client, shellRootLayer);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = SurfaceControl.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setShellRootAccessibilityWindow(int displayId, int shellRootLayer, IWindow target) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeInt(shellRootLayer);
                    _data.writeStrongBinder(target != null ? target.asBinder() : null);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setShellRootAccessibilityWindow(displayId, shellRootLayer, target);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void overridePendingAppTransitionMultiThumbFuture(IAppTransitionAnimationSpecsFuture specsFuture, IRemoteCallback startedCallback, boolean scaleUp, int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    IBinder iBinder = null;
                    _data.writeStrongBinder(specsFuture != null ? specsFuture.asBinder() : null);
                    if (startedCallback != null) {
                        iBinder = startedCallback.asBinder();
                    }
                    _data.writeStrongBinder(iBinder);
                    _data.writeInt(scaleUp ? 1 : 0);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().overridePendingAppTransitionMultiThumbFuture(specsFuture, startedCallback, scaleUp, displayId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void overridePendingAppTransitionRemote(RemoteAnimationAdapter remoteAnimationAdapter, int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (remoteAnimationAdapter != null) {
                        _data.writeInt(1);
                        remoteAnimationAdapter.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().overridePendingAppTransitionRemote(remoteAnimationAdapter, displayId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void endProlongedAnimations() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().endProlongedAnimations();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void startFreezingScreen(int exitAnim, int enterAnim) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(exitAnim);
                    _data.writeInt(enterAnim);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().startFreezingScreen(exitAnim, enterAnim);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void stopFreezingScreen() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().stopFreezingScreen();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void disableKeyguard(IBinder token, String tag, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeString(tag);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().disableKeyguard(token, tag, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void reenableKeyguard(IBinder token, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(28, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().reenableKeyguard(token, userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void exitKeyguardSecurely(IOnKeyguardExitResult callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(29, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().exitKeyguardSecurely(callback);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean isKeyguardLocked() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(30, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isKeyguardLocked();
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

            @Override // android.view.IWindowManager
            public boolean isKeyguardSecure(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(31, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isKeyguardSecure(userId);
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

            @Override // android.view.IWindowManager
            public void dismissKeyguard(IKeyguardDismissCallback callback, CharSequence message) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (message != null) {
                        _data.writeInt(1);
                        TextUtils.writeToParcel(message, _data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(32, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().dismissKeyguard(callback, message);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setSwitchingUser(boolean switching) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(switching ? 1 : 0);
                    boolean _status = this.mRemote.transact(33, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setSwitchingUser(switching);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void closeSystemDialogs(String reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(reason);
                    boolean _status = this.mRemote.transact(34, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().closeSystemDialogs(reason);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public float getAnimationScale(int which) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(which);
                    boolean _status = this.mRemote.transact(35, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAnimationScale(which);
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public float[] getAnimationScales() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(36, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAnimationScales();
                    }
                    _reply.readException();
                    float[] _result = _reply.createFloatArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setAnimationScale(int which, float scale) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(which);
                    _data.writeFloat(scale);
                    boolean _status = this.mRemote.transact(37, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setAnimationScale(which, scale);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setAnimationScales(float[] scales) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloatArray(scales);
                    boolean _status = this.mRemote.transact(38, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setAnimationScales(scales);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public float getCurrentAnimatorScale() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(39, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentAnimatorScale();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setInTouchMode(boolean showFocus) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(showFocus ? 1 : 0);
                    boolean _status = this.mRemote.transact(40, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setInTouchMode(showFocus);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void showStrictModeViolation(boolean on) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(on ? 1 : 0);
                    boolean _status = this.mRemote.transact(41, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().showStrictModeViolation(on);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setStrictModeVisualIndicatorPreference(String enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(enabled);
                    boolean _status = this.mRemote.transact(42, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setStrictModeVisualIndicatorPreference(enabled);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void refreshScreenCaptureDisabled(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(userId);
                    boolean _status = this.mRemote.transact(43, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().refreshScreenCaptureDisabled(userId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void updateRotation(boolean alwaysSendConfiguration, boolean forceRelayout) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    _data.writeInt(alwaysSendConfiguration ? 1 : 0);
                    if (!forceRelayout) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    boolean _status = this.mRemote.transact(44, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().updateRotation(alwaysSendConfiguration, forceRelayout);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public int getDefaultDisplayRotation() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(45, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDefaultDisplayRotation();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public int watchRotation(IRotationWatcher watcher, int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(watcher != null ? watcher.asBinder() : null);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(46, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().watchRotation(watcher, displayId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void removeRotationWatcher(IRotationWatcher watcher) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(watcher != null ? watcher.asBinder() : null);
                    boolean _status = this.mRemote.transact(47, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeRotationWatcher(watcher);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public int getPreferredOptionsPanelGravity(int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(48, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPreferredOptionsPanelGravity(displayId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void freezeRotation(int rotation) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(rotation);
                    boolean _status = this.mRemote.transact(49, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().freezeRotation(rotation);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void thawRotation() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(50, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().thawRotation();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean isRotationFrozen() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(51, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isRotationFrozen();
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

            @Override // android.view.IWindowManager
            public void freezeDisplayRotation(int displayId, int rotation) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeInt(rotation);
                    boolean _status = this.mRemote.transact(52, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().freezeDisplayRotation(displayId, rotation);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void thawDisplayRotation(int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(53, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().thawDisplayRotation(displayId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean isDisplayRotationFrozen(int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(54, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDisplayRotationFrozen(displayId);
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

            @Override // android.view.IWindowManager
            public void setFixedToUserRotation(int displayId, int fixedToUserRotation) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeInt(fixedToUserRotation);
                    boolean _status = this.mRemote.transact(55, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setFixedToUserRotation(displayId, fixedToUserRotation);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setIgnoreOrientationRequest(int displayId, boolean ignoreOrientationRequest) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeInt(ignoreOrientationRequest ? 1 : 0);
                    boolean _status = this.mRemote.transact(56, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setIgnoreOrientationRequest(displayId, ignoreOrientationRequest);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public Bitmap screenshotWallpaper() throws RemoteException {
                Bitmap _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(57, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().screenshotWallpaper();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = Bitmap.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean registerWallpaperVisibilityListener(IWallpaperVisibilityListener listener, int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    _data.writeInt(displayId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(58, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerWallpaperVisibilityListener(listener, displayId);
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

            @Override // android.view.IWindowManager
            public void unregisterWallpaperVisibilityListener(IWallpaperVisibilityListener listener, int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(59, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterWallpaperVisibilityListener(listener, displayId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void registerSystemGestureExclusionListener(ISystemGestureExclusionListener listener, int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(60, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerSystemGestureExclusionListener(listener, displayId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void unregisterSystemGestureExclusionListener(ISystemGestureExclusionListener listener, int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(61, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterSystemGestureExclusionListener(listener, displayId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean requestAssistScreenshot(IAssistDataReceiver receiver) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(receiver != null ? receiver.asBinder() : null);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(62, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestAssistScreenshot(receiver);
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

            @Override // android.view.IWindowManager
            public void hideTransientBars(int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(63, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().hideTransientBars(displayId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setRecentsVisibility(boolean visible) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(visible ? 1 : 0);
                    boolean _status = this.mRemote.transact(64, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setRecentsVisibility(visible);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void updateStaticPrivacyIndicatorBounds(int displayId, Rect[] staticBounds) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeTypedArray(staticBounds, 0);
                    boolean _status = this.mRemote.transact(65, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateStaticPrivacyIndicatorBounds(displayId, staticBounds);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setNavBarVirtualKeyHapticFeedbackEnabled(boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(66, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setNavBarVirtualKeyHapticFeedbackEnabled(enabled);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean hasNavigationBar(int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(67, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasNavigationBar(displayId);
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

            @Override // android.view.IWindowManager
            public int getNavBarPosition(int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(68, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNavBarPosition(displayId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void lockNow(Bundle options) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (options != null) {
                        _data.writeInt(1);
                        options.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(69, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().lockNow(options);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean isSafeModeEnabled() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(70, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSafeModeEnabled();
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

            @Override // android.view.IWindowManager
            public void enableScreenIfNeeded() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(71, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().enableScreenIfNeeded();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean clearWindowContentFrameStats(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(72, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().clearWindowContentFrameStats(token);
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

            @Override // android.view.IWindowManager
            public WindowContentFrameStats getWindowContentFrameStats(IBinder token) throws RemoteException {
                WindowContentFrameStats _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(73, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWindowContentFrameStats(token);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = WindowContentFrameStats.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public int getDockedStackSide() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(74, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDockedStackSide();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setDockedTaskDividerTouchRegion(Rect touchableRegion) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (touchableRegion != null) {
                        _data.writeInt(1);
                        touchableRegion.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(75, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setDockedTaskDividerTouchRegion(touchableRegion);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void registerPinnedTaskListener(int displayId, IPinnedTaskListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(76, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerPinnedTaskListener(displayId, listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void requestAppKeyboardShortcuts(IResultReceiver receiver, int deviceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(receiver != null ? receiver.asBinder() : null);
                    _data.writeInt(deviceId);
                    boolean _status = this.mRemote.transact(77, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().requestAppKeyboardShortcuts(receiver, deviceId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void getStableInsets(int displayId, Rect outInsets) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(78, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        if (_reply.readInt() != 0) {
                            outInsets.readFromParcel(_reply);
                        }
                        return;
                    }
                    Stub.getDefaultImpl().getStableInsets(displayId, outInsets);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setForwardedInsets(int displayId, Insets insets) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    if (insets != null) {
                        _data.writeInt(1);
                        insets.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(79, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setForwardedInsets(displayId, insets);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void registerShortcutKey(long shortcutCode, IShortcutService keySubscriber) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(shortcutCode);
                    _data.writeStrongBinder(keySubscriber != null ? keySubscriber.asBinder() : null);
                    boolean _status = this.mRemote.transact(80, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerShortcutKey(shortcutCode, keySubscriber);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void createInputConsumer(IBinder token, String name, int displayId, InputChannel inputChannel) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeString(name);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(81, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        if (_reply.readInt() != 0) {
                            inputChannel.readFromParcel(_reply);
                        }
                        return;
                    }
                    Stub.getDefaultImpl().createInputConsumer(token, name, displayId, inputChannel);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean destroyInputConsumer(String name, int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    _data.writeInt(displayId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(82, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().destroyInputConsumer(name, displayId);
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

            @Override // android.view.IWindowManager
            public Region getCurrentImeTouchRegion() throws RemoteException {
                Region _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(83, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentImeTouchRegion();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = Region.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void registerDisplayFoldListener(IDisplayFoldListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(84, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerDisplayFoldListener(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void unregisterDisplayFoldListener(IDisplayFoldListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(85, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterDisplayFoldListener(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void registerDisplayWindowListener(IDisplayWindowListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(86, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerDisplayWindowListener(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void unregisterDisplayWindowListener(IDisplayWindowListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(87, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterDisplayWindowListener(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void startWindowTrace() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(88, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().startWindowTrace();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void stopWindowTrace() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(89, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().stopWindowTrace();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean isWindowTraceEnabled() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(90, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isWindowTraceEnabled();
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

            @Override // android.view.IWindowManager
            public void dontOverrideDisplayInfo(int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(91, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().dontOverrideDisplayInfo(displayId);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public int getWindowingMode(int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(92, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWindowingMode(displayId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setWindowingMode(int displayId, int mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeInt(mode);
                    boolean _status = this.mRemote.transact(93, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setWindowingMode(displayId, mode);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public int getRemoveContentMode(int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(94, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRemoveContentMode(displayId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setRemoveContentMode(int displayId, int mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeInt(mode);
                    boolean _status = this.mRemote.transact(95, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setRemoveContentMode(displayId, mode);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean shouldShowWithInsecureKeyguard(int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(96, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().shouldShowWithInsecureKeyguard(displayId);
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

            @Override // android.view.IWindowManager
            public void setShouldShowWithInsecureKeyguard(int displayId, boolean shouldShow) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeInt(shouldShow ? 1 : 0);
                    boolean _status = this.mRemote.transact(97, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setShouldShowWithInsecureKeyguard(displayId, shouldShow);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean shouldShowSystemDecors(int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(98, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().shouldShowSystemDecors(displayId);
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

            @Override // android.view.IWindowManager
            public void setShouldShowSystemDecors(int displayId, boolean shouldShow) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeInt(shouldShow ? 1 : 0);
                    boolean _status = this.mRemote.transact(99, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setShouldShowSystemDecors(displayId, shouldShow);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public int getDisplayImePolicy(int displayId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(100, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDisplayImePolicy(displayId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setDisplayImePolicy(int displayId, int imePolicy) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeInt(imePolicy);
                    boolean _status = this.mRemote.transact(101, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setDisplayImePolicy(displayId, imePolicy);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean injectInputAfterTransactionsApplied(InputEvent ev, int mode, boolean waitForAnimations) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = true;
                    if (ev != null) {
                        _data.writeInt(1);
                        ev.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(mode);
                    _data.writeInt(waitForAnimations ? 1 : 0);
                    boolean _status = this.mRemote.transact(102, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().injectInputAfterTransactionsApplied(ev, mode, waitForAnimations);
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

            @Override // android.view.IWindowManager
            public void syncInputTransactions(boolean waitForAnimations) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(waitForAnimations ? 1 : 0);
                    boolean _status = this.mRemote.transact(103, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().syncInputTransactions(waitForAnimations);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean isLayerTracing() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(104, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isLayerTracing();
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

            @Override // android.view.IWindowManager
            public void setLayerTracing(boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(105, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setLayerTracing(enabled);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean mirrorDisplay(int displayId, SurfaceControl outSurfaceControl) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(106, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().mirrorDisplay(displayId, outSurfaceControl);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    if (_reply.readInt() != 0) {
                        outSurfaceControl.readFromParcel(_reply);
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setDisplayWindowInsetsController(int displayId, IDisplayWindowInsetsController displayWindowInsetsController) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeStrongBinder(displayWindowInsetsController != null ? displayWindowInsetsController.asBinder() : null);
                    boolean _status = this.mRemote.transact(107, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setDisplayWindowInsetsController(displayId, displayWindowInsetsController);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void modifyDisplayWindowInsets(int displayId, InsetsState state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    if (state != null) {
                        _data.writeInt(1);
                        state.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(108, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().modifyDisplayWindowInsets(displayId, state);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean getWindowInsets(WindowManager.LayoutParams attrs, int displayId, InsetsState outInsetsState) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = true;
                    if (attrs != null) {
                        _data.writeInt(1);
                        attrs.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(displayId);
                    boolean _status = this.mRemote.transact(109, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWindowInsets(attrs, displayId, outInsetsState);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    if (_reply.readInt() != 0) {
                        outInsetsState.readFromParcel(_reply);
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void showGlobalActions() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(110, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().showGlobalActions();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setLayerTracingFlags(int flags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(flags);
                    boolean _status = this.mRemote.transact(111, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setLayerTracingFlags(flags);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void requestScrollCapture(int displayId, IBinder behindClient, int taskId, IScrollCaptureResponseListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(displayId);
                    _data.writeStrongBinder(behindClient);
                    _data.writeInt(taskId);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(112, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().requestScrollCapture(displayId, behindClient, taskId, listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void holdLock(IBinder token, int durationMs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    _data.writeInt(durationMs);
                    boolean _status = this.mRemote.transact(113, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().holdLock(token, durationMs);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public String[] getSupportedDisplayHashAlgorithms() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(114, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSupportedDisplayHashAlgorithms();
                    }
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public VerifiedDisplayHash verifyDisplayHash(DisplayHash displayHash) throws RemoteException {
                VerifiedDisplayHash _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (displayHash != null) {
                        _data.writeInt(1);
                        displayHash.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(115, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().verifyDisplayHash(displayHash);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = VerifiedDisplayHash.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setDisplayHashThrottlingEnabled(boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(116, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setDisplayHashThrottlingEnabled(enable);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public Configuration attachWindowContextToDisplayArea(IBinder clientToken, int type, int displayId, Bundle options) throws RemoteException {
                Configuration _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(clientToken);
                    _data.writeInt(type);
                    _data.writeInt(displayId);
                    if (options != null) {
                        _data.writeInt(1);
                        options.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(117, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().attachWindowContextToDisplayArea(clientToken, type, displayId, options);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = Configuration.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void attachWindowContextToWindowToken(IBinder clientToken, IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(clientToken);
                    _data.writeStrongBinder(token);
                    boolean _status = this.mRemote.transact(118, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().attachWindowContextToWindowToken(clientToken, token);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void detachWindowContextFromWindowContainer(IBinder clientToken) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(clientToken);
                    boolean _status = this.mRemote.transact(119, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().detachWindowContextFromWindowContainer(clientToken);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean registerCrossWindowBlurEnabledListener(ICrossWindowBlurEnabledListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(120, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerCrossWindowBlurEnabledListener(listener);
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

            @Override // android.view.IWindowManager
            public void unregisterCrossWindowBlurEnabledListener(ICrossWindowBlurEnabledListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(121, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterCrossWindowBlurEnabledListener(listener);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean isTaskSnapshotSupported() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(122, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isTaskSnapshotSupported();
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
        }

        public static boolean setDefaultImpl(IWindowManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IWindowManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
