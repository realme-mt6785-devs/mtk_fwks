package android.view;

import android.app.ResourcesManager;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.android.internal.os.IResultReceiver;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
/* loaded from: classes3.dex */
public final class WindowManagerImpl implements WindowManager {
    public final Context mContext;
    private IBinder mDefaultToken;
    private final WindowManagerGlobal mGlobal;
    private final Window mParentWindow;
    private final IBinder mWindowContextToken;

    public WindowManagerImpl(Context context) {
        this(context, null, null);
    }

    private WindowManagerImpl(Context context, Window parentWindow, IBinder windowContextToken) {
        this.mGlobal = WindowManagerGlobal.getInstance();
        this.mContext = context;
        this.mParentWindow = parentWindow;
        this.mWindowContextToken = windowContextToken;
    }

    public WindowManagerImpl createLocalWindowManager(Window parentWindow) {
        return new WindowManagerImpl(this.mContext, parentWindow, this.mWindowContextToken);
    }

    public WindowManagerImpl createPresentationWindowManager(Context displayContext) {
        return new WindowManagerImpl(displayContext, this.mParentWindow, this.mWindowContextToken);
    }

    public static WindowManager createWindowContextWindowManager(Context context) {
        IBinder clientToken = context.getWindowContextToken();
        return new WindowManagerImpl(context, null, clientToken);
    }

    public void setDefaultToken(IBinder token) {
        this.mDefaultToken = token;
    }

    @Override // android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams params) {
        applyTokens(params);
        this.mGlobal.addView(view, params, this.mContext.getDisplayNoVerify(), this.mParentWindow, this.mContext.getUserId());
    }

    @Override // android.view.ViewManager
    public void updateViewLayout(View view, ViewGroup.LayoutParams params) {
        applyTokens(params);
        this.mGlobal.updateViewLayout(view, params);
    }

    private void applyTokens(ViewGroup.LayoutParams params) {
        if (params instanceof WindowManager.LayoutParams) {
            WindowManager.LayoutParams wparams = (WindowManager.LayoutParams) params;
            if (this.mDefaultToken != null && this.mParentWindow == null && wparams.token == null) {
                wparams.token = this.mDefaultToken;
            }
            wparams.mWindowContextToken = this.mWindowContextToken;
            return;
        }
        throw new IllegalArgumentException("Params must be WindowManager.LayoutParams");
    }

    @Override // android.view.ViewManager
    public void removeView(View view) {
        this.mGlobal.removeView(view, false);
    }

    @Override // android.view.WindowManager
    public void removeViewImmediate(View view) {
        this.mGlobal.removeView(view, true);
    }

    @Override // android.view.WindowManager
    public void requestAppKeyboardShortcuts(final WindowManager.KeyboardShortcutsReceiver receiver, int deviceId) {
        IResultReceiver resultReceiver = new IResultReceiver.Stub() { // from class: android.view.WindowManagerImpl.1
            @Override // com.android.internal.os.IResultReceiver
            public void send(int resultCode, Bundle resultData) throws RemoteException {
                List<KeyboardShortcutGroup> result = resultData.getParcelableArrayList(WindowManager.PARCEL_KEY_SHORTCUTS_ARRAY);
                receiver.onKeyboardShortcutsReceived(result);
            }
        };
        try {
            WindowManagerGlobal.getWindowManagerService().requestAppKeyboardShortcuts(resultReceiver, deviceId);
        } catch (RemoteException e) {
        }
    }

    @Override // android.view.WindowManager
    public Display getDefaultDisplay() {
        return this.mContext.getDisplayNoVerify();
    }

    @Override // android.view.WindowManager
    public Region getCurrentImeTouchRegion() {
        try {
            return WindowManagerGlobal.getWindowManagerService().getCurrentImeTouchRegion();
        } catch (RemoteException e) {
            return null;
        }
    }

    @Override // android.view.WindowManager
    public void setShouldShowWithInsecureKeyguard(int displayId, boolean shouldShow) {
        try {
            WindowManagerGlobal.getWindowManagerService().setShouldShowWithInsecureKeyguard(displayId, shouldShow);
        } catch (RemoteException e) {
        }
    }

    @Override // android.view.WindowManager
    public void setShouldShowSystemDecors(int displayId, boolean shouldShow) {
        try {
            WindowManagerGlobal.getWindowManagerService().setShouldShowSystemDecors(displayId, shouldShow);
        } catch (RemoteException e) {
        }
    }

    @Override // android.view.WindowManager
    public boolean shouldShowSystemDecors(int displayId) {
        try {
            return WindowManagerGlobal.getWindowManagerService().shouldShowSystemDecors(displayId);
        } catch (RemoteException e) {
            return false;
        }
    }

    @Override // android.view.WindowManager
    public void setDisplayImePolicy(int displayId, int imePolicy) {
        try {
            WindowManagerGlobal.getWindowManagerService().setDisplayImePolicy(displayId, imePolicy);
        } catch (RemoteException e) {
        }
    }

    @Override // android.view.WindowManager
    public int getDisplayImePolicy(int displayId) {
        try {
            return WindowManagerGlobal.getWindowManagerService().getDisplayImePolicy(displayId);
        } catch (RemoteException e) {
            return 1;
        }
    }

    @Override // android.view.WindowManager
    public WindowMetrics getCurrentWindowMetrics() {
        Window window = this.mParentWindow;
        Context context = window != null ? window.getContext() : this.mContext;
        Rect bounds = getCurrentBounds(context);
        return new WindowMetrics(bounds, computeWindowInsets(bounds));
    }

    private static Rect getCurrentBounds(Context context) {
        Rect bounds;
        synchronized (ResourcesManager.getInstance()) {
            bounds = context.getResources().getConfiguration().windowConfiguration.getBounds();
        }
        return bounds;
    }

    @Override // android.view.WindowManager
    public WindowMetrics getMaximumWindowMetrics() {
        Window window = this.mParentWindow;
        Context context = window != null ? window.getContext() : this.mContext;
        Rect maxBounds = getMaximumBounds(context);
        return new WindowMetrics(maxBounds, computeWindowInsets(maxBounds));
    }

    private static Rect getMaximumBounds(Context context) {
        Rect maxBounds;
        synchronized (ResourcesManager.getInstance()) {
            maxBounds = context.getResources().getConfiguration().windowConfiguration.getMaxBounds();
        }
        return maxBounds;
    }

    private WindowInsets computeWindowInsets(Rect bounds) {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.flags = 65792;
        Window window = this.mParentWindow;
        Context context = window != null ? window.getContext() : this.mContext;
        params.token = Context.getToken(context);
        params.systemUiVisibility = 1536;
        params.setFitInsetsTypes(0);
        params.setFitInsetsSides(0);
        return getWindowInsetsFromServer(params, bounds);
    }

    private WindowInsets getWindowInsetsFromServer(WindowManager.LayoutParams attrs, Rect bounds) {
        try {
            InsetsState insetsState = new InsetsState();
            boolean alwaysConsumeSystemBars = WindowManagerGlobal.getWindowManagerService().getWindowInsets(attrs, this.mContext.getDisplayId(), insetsState);
            Configuration config = this.mContext.getResources().getConfiguration();
            boolean isScreenRound = config.isScreenRound();
            int windowingMode = config.windowConfiguration.getWindowingMode();
            return insetsState.calculateInsets(bounds, null, isScreenRound, alwaysConsumeSystemBars, 48, attrs.flags, 0, attrs.type, windowingMode, null);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Override // android.view.WindowManager
    public void holdLock(IBinder token, int durationMs) {
        try {
            WindowManagerGlobal.getWindowManagerService().holdLock(token, durationMs);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Override // android.view.WindowManager
    public boolean isCrossWindowBlurEnabled() {
        return CrossWindowBlurListeners.getInstance().isCrossWindowBlurEnabled();
    }

    @Override // android.view.WindowManager
    public void addCrossWindowBlurEnabledListener(Consumer<Boolean> listener) {
        addCrossWindowBlurEnabledListener(this.mContext.getMainExecutor(), listener);
    }

    @Override // android.view.WindowManager
    public void addCrossWindowBlurEnabledListener(Executor executor, Consumer<Boolean> listener) {
        CrossWindowBlurListeners.getInstance().addListener(executor, listener);
    }

    @Override // android.view.WindowManager
    public void removeCrossWindowBlurEnabledListener(Consumer<Boolean> listener) {
        CrossWindowBlurListeners.getInstance().removeListener(listener);
    }

    @Override // android.view.WindowManager
    public boolean isTaskSnapshotSupported() {
        try {
            return WindowManagerGlobal.getWindowManagerService().isTaskSnapshotSupported();
        } catch (RemoteException e) {
            return false;
        }
    }
}
