package android.service.dreams;

import android.app.Activity;
import android.app.ActivityTaskManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.IRemoteCallback;
import android.os.Looper;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.service.dreams.DreamService;
import android.service.dreams.IDreamManager;
import android.service.dreams.IDreamService;
import android.util.Log;
import android.util.MathUtils;
import android.util.Slog;
import android.view.ActionMode;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import com.android.internal.util.DumpUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;
/* loaded from: classes3.dex */
public class DreamService extends Service implements Window.Callback {
    public static final String DREAM_META_DATA = "android.service.dream";
    public static final String DREAM_SERVICE = "dreams";
    public static final String SERVICE_INTERFACE = "android.service.dreams.DreamService";
    private Activity mActivity;
    private boolean mCanDoze;
    private Runnable mDispatchAfterOnAttachedToWindow;
    private boolean mDozing;
    private DreamServiceWrapper mDreamServiceWrapper;
    private IBinder mDreamToken;
    private boolean mFinished;
    private boolean mFullscreen;
    private boolean mInteractive;
    private boolean mStarted;
    private boolean mWaking;
    private Window mWindow;
    private boolean mWindowless;
    private final String TAG = DreamService.class.getSimpleName() + "[" + getClass().getSimpleName() + "]";
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private boolean mScreenBright = true;
    private int mDozeScreenState = 0;
    private int mDozeScreenBrightness = -1;
    private boolean mDebug = false;
    private final IDreamManager mDreamManager = IDreamManager.Stub.asInterface(ServiceManager.getService(DREAM_SERVICE));

    public void setDebug(boolean dbg) {
        this.mDebug = dbg;
    }

    @Override // android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (!this.mInteractive) {
            if (this.mDebug) {
                Slog.v(this.TAG, "Waking up on keyEvent");
            }
            wakeUp();
            return true;
        } else if (event.getKeyCode() != 4) {
            return this.mWindow.superDispatchKeyEvent(event);
        } else {
            if (this.mDebug) {
                Slog.v(this.TAG, "Waking up on back key");
            }
            wakeUp();
            return true;
        }
    }

    @Override // android.view.Window.Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        if (this.mInteractive) {
            return this.mWindow.superDispatchKeyShortcutEvent(event);
        }
        if (this.mDebug) {
            Slog.v(this.TAG, "Waking up on keyShortcutEvent");
        }
        wakeUp();
        return true;
    }

    @Override // android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (this.mInteractive) {
            return this.mWindow.superDispatchTouchEvent(event);
        }
        if (this.mDebug) {
            Slog.v(this.TAG, "Waking up on touchEvent");
        }
        wakeUp();
        return true;
    }

    @Override // android.view.Window.Callback
    public boolean dispatchTrackballEvent(MotionEvent event) {
        if (this.mInteractive) {
            return this.mWindow.superDispatchTrackballEvent(event);
        }
        if (this.mDebug) {
            Slog.v(this.TAG, "Waking up on trackballEvent");
        }
        wakeUp();
        return true;
    }

    @Override // android.view.Window.Callback
    public boolean dispatchGenericMotionEvent(MotionEvent event) {
        if (this.mInteractive) {
            return this.mWindow.superDispatchGenericMotionEvent(event);
        }
        if (this.mDebug) {
            Slog.v(this.TAG, "Waking up on genericMotionEvent");
        }
        wakeUp();
        return true;
    }

    @Override // android.view.Window.Callback
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        return false;
    }

    @Override // android.view.Window.Callback
    public View onCreatePanelView(int featureId) {
        return null;
    }

    @Override // android.view.Window.Callback
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        return false;
    }

    @Override // android.view.Window.Callback
    public boolean onPreparePanel(int featureId, View view, Menu menu) {
        return false;
    }

    @Override // android.view.Window.Callback
    public boolean onMenuOpened(int featureId, Menu menu) {
        return false;
    }

    @Override // android.view.Window.Callback
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        return false;
    }

    @Override // android.view.Window.Callback
    public void onWindowAttributesChanged(WindowManager.LayoutParams attrs) {
    }

    @Override // android.view.Window.Callback
    public void onContentChanged() {
    }

    @Override // android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
    }

    @Override // android.view.Window.Callback
    public void onAttachedToWindow() {
    }

    @Override // android.view.Window.Callback
    public void onDetachedFromWindow() {
    }

    @Override // android.view.Window.Callback
    public void onPanelClosed(int featureId, Menu menu) {
    }

    @Override // android.view.Window.Callback
    public boolean onSearchRequested(SearchEvent event) {
        return onSearchRequested();
    }

    @Override // android.view.Window.Callback
    public boolean onSearchRequested() {
        return false;
    }

    @Override // android.view.Window.Callback
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return null;
    }

    @Override // android.view.Window.Callback
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int type) {
        return null;
    }

    @Override // android.view.Window.Callback
    public void onActionModeStarted(ActionMode mode) {
    }

    @Override // android.view.Window.Callback
    public void onActionModeFinished(ActionMode mode) {
    }

    public WindowManager getWindowManager() {
        Window window = this.mWindow;
        if (window != null) {
            return window.getWindowManager();
        }
        return null;
    }

    public Window getWindow() {
        return this.mWindow;
    }

    public void setContentView(int layoutResID) {
        getWindow().setContentView(layoutResID);
    }

    public void setContentView(View view) {
        getWindow().setContentView(view);
    }

    public void setContentView(View view, ViewGroup.LayoutParams params) {
        getWindow().setContentView(view, params);
    }

    public void addContentView(View view, ViewGroup.LayoutParams params) {
        getWindow().addContentView(view, params);
    }

    public <T extends View> T findViewById(int id) {
        return (T) getWindow().findViewById(id);
    }

    public final <T extends View> T requireViewById(int id) {
        T view = (T) findViewById(id);
        if (view != null) {
            return view;
        }
        throw new IllegalArgumentException("ID does not reference a View inside this DreamService");
    }

    public void setInteractive(boolean interactive) {
        this.mInteractive = interactive;
    }

    public boolean isInteractive() {
        return this.mInteractive;
    }

    public void setFullscreen(boolean fullscreen) {
        if (this.mFullscreen != fullscreen) {
            this.mFullscreen = fullscreen;
            applyWindowFlags(fullscreen ? 1024 : 0, 1024);
        }
    }

    public boolean isFullscreen() {
        return this.mFullscreen;
    }

    public void setScreenBright(boolean screenBright) {
        if (this.mScreenBright != screenBright) {
            this.mScreenBright = screenBright;
            applyWindowFlags(screenBright ? 128 : 0, 128);
        }
    }

    public boolean isScreenBright() {
        return getWindowFlagValue(128, this.mScreenBright);
    }

    public void setWindowless(boolean windowless) {
        this.mWindowless = windowless;
    }

    public boolean isWindowless() {
        return this.mWindowless;
    }

    public boolean canDoze() {
        return this.mCanDoze;
    }

    public void startDozing() {
        if (this.mCanDoze && !this.mDozing) {
            this.mDozing = true;
            updateDoze();
        }
    }

    private void updateDoze() {
        IBinder iBinder = this.mDreamToken;
        if (iBinder == null) {
            Slog.w(this.TAG, "Updating doze without a dream token.");
        } else if (this.mDozing) {
            try {
                this.mDreamManager.startDozing(iBinder, this.mDozeScreenState, this.mDozeScreenBrightness);
            } catch (RemoteException e) {
            }
        }
    }

    public void stopDozing() {
        if (this.mDozing) {
            this.mDozing = false;
            try {
                this.mDreamManager.stopDozing(this.mDreamToken);
            } catch (RemoteException e) {
            }
        }
    }

    public boolean isDozing() {
        return this.mDozing;
    }

    public int getDozeScreenState() {
        return this.mDozeScreenState;
    }

    public void setDozeScreenState(int state) {
        if (this.mDozeScreenState != state) {
            this.mDozeScreenState = state;
            updateDoze();
        }
    }

    public int getDozeScreenBrightness() {
        return this.mDozeScreenBrightness;
    }

    public void setDozeScreenBrightness(int brightness) {
        if (brightness != -1) {
            brightness = clampAbsoluteBrightness(brightness);
        }
        if (this.mDozeScreenBrightness != brightness) {
            this.mDozeScreenBrightness = brightness;
            updateDoze();
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        if (this.mDebug) {
            Slog.v(this.TAG, "onCreate()");
        }
        super.onCreate();
    }

    public void onDreamingStarted() {
        if (this.mDebug) {
            Slog.v(this.TAG, "onDreamingStarted()");
        }
    }

    public void onDreamingStopped() {
        if (this.mDebug) {
            Slog.v(this.TAG, "onDreamingStopped()");
        }
    }

    public void onWakeUp() {
        finish();
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        if (this.mDebug) {
            String str = this.TAG;
            Slog.v(str, "onBind() intent = " + intent);
        }
        DreamServiceWrapper dreamServiceWrapper = new DreamServiceWrapper();
        this.mDreamServiceWrapper = dreamServiceWrapper;
        return dreamServiceWrapper;
    }

    public final void finish() {
        if (this.mDebug) {
            String str = this.TAG;
            Slog.v(str, "finish(): mFinished=" + this.mFinished);
        }
        Activity activity = this.mActivity;
        if (activity != null) {
            if (!activity.isFinishing()) {
                activity.finishAndRemoveTask();
            }
        } else if (!this.mFinished) {
            this.mFinished = true;
            IBinder iBinder = this.mDreamToken;
            if (iBinder == null) {
                Slog.w(this.TAG, "Finish was called before the dream was attached.");
                stopSelf();
                return;
            }
            try {
                this.mDreamManager.finishSelf(iBinder, true);
            } catch (RemoteException e) {
            }
        }
    }

    public final void wakeUp() {
        wakeUp(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void wakeUp(boolean fromSystem) {
        if (this.mDebug) {
            String str = this.TAG;
            Slog.v(str, "wakeUp(): fromSystem=" + fromSystem + ", mWaking=" + this.mWaking + ", mFinished=" + this.mFinished);
        }
        if (!this.mWaking && !this.mFinished) {
            this.mWaking = true;
            onWakeUp();
            if (!fromSystem && !this.mFinished) {
                if (this.mActivity == null) {
                    Slog.w(this.TAG, "WakeUp was called before the dream was attached.");
                    return;
                }
                try {
                    this.mDreamManager.finishSelf(this.mDreamToken, false);
                } catch (RemoteException e) {
                }
            }
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        if (this.mDebug) {
            Slog.v(this.TAG, "onDestroy()");
        }
        detach();
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void detach() {
        if (this.mStarted) {
            if (this.mDebug) {
                Slog.v(this.TAG, "detach(): Calling onDreamingStopped()");
            }
            this.mStarted = false;
            onDreamingStopped();
        }
        Activity activity = this.mActivity;
        if (activity == null || activity.isFinishing()) {
            finish();
        } else {
            this.mActivity.finishAndRemoveTask();
        }
        this.mDreamToken = null;
        this.mCanDoze = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void attach(IBinder dreamToken, boolean canDoze, final IRemoteCallback started) {
        if (this.mDreamToken != null) {
            String str = this.TAG;
            Slog.e(str, "attach() called when dream with token=" + this.mDreamToken + " already attached");
        } else if (this.mFinished || this.mWaking) {
            Slog.w(this.TAG, "attach() called after dream already finished");
            try {
                this.mDreamManager.finishSelf(dreamToken, true);
            } catch (RemoteException e) {
            }
        } else {
            this.mDreamToken = dreamToken;
            this.mCanDoze = canDoze;
            if (!this.mWindowless || canDoze) {
                Runnable dreamService$$ExternalSyntheticLambda0 = new Runnable() { // from class: android.service.dreams.DreamService$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        DreamService.this.lambda$attach$0$DreamService(started);
                    }
                };
                this.mDispatchAfterOnAttachedToWindow = dreamService$$ExternalSyntheticLambda0;
                if (!this.mWindowless) {
                    Intent i = new Intent(this, DreamActivity.class);
                    i.setPackage(getApplicationContext().getPackageName());
                    i.setFlags(268435456);
                    i.putExtra("binder", this.mDreamServiceWrapper);
                    try {
                        if (!ActivityTaskManager.getService().startDreamActivity(i)) {
                            detach();
                        }
                    } catch (RemoteException e2) {
                        Log.w(this.TAG, "Could not connect to activity task manager to start dream activity");
                        e2.rethrowFromSystemServer();
                    }
                } else {
                    dreamService$$ExternalSyntheticLambda0.run();
                }
            } else {
                throw new IllegalStateException("Only doze dreams can be windowless");
            }
        }
    }

    public /* synthetic */ void lambda$attach$0$DreamService(IRemoteCallback started) {
        if (this.mWindow != null || this.mWindowless) {
            this.mStarted = true;
            try {
                onDreamingStarted();
                try {
                    started.sendResult(null);
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            } catch (Throwable th) {
                try {
                    started.sendResult(null);
                    throw th;
                } catch (RemoteException e2) {
                    throw e2.rethrowFromSystemServer();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWindowCreated(Window w) {
        this.mWindow = w;
        w.setCallback(this);
        this.mWindow.requestFeature(1);
        WindowManager.LayoutParams lp = this.mWindow.getAttributes();
        lp.flags |= (this.mFullscreen ? 1024 : 0) | 21561601 | (this.mScreenBright ? 128 : 0);
        lp.layoutInDisplayCutoutMode = 3;
        this.mWindow.setAttributes(lp);
        this.mWindow.clearFlags(Integer.MIN_VALUE);
        this.mWindow.getDecorView().getWindowInsetsController().hide(WindowInsets.Type.systemBars());
        this.mWindow.setDecorFitsSystemWindows(false);
        this.mWindow.getDecorView().addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: android.service.dreams.DreamService.1
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View v) {
                DreamService.this.mDispatchAfterOnAttachedToWindow.run();
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View v) {
                if (DreamService.this.mActivity == null || !DreamService.this.mActivity.isChangingConfigurations()) {
                    DreamService.this.mActivity = null;
                    DreamService.this.finish();
                }
            }
        });
    }

    private boolean getWindowFlagValue(int flag, boolean defaultValue) {
        Window window = this.mWindow;
        return window == null ? defaultValue : (window.getAttributes().flags & flag) != 0;
    }

    private void applyWindowFlags(int flags, int mask) {
        Window window = this.mWindow;
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.flags = applyFlags(lp.flags, flags, mask);
            this.mWindow.setAttributes(lp);
            this.mWindow.getWindowManager().updateViewLayout(this.mWindow.getDecorView(), lp);
        }
    }

    private int applyFlags(int oldFlags, int flags, int mask) {
        return ((~mask) & oldFlags) | (flags & mask);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Service
    public void dump(final FileDescriptor fd, PrintWriter pw, final String[] args) {
        DumpUtils.dumpAsync(this.mHandler, new DumpUtils.Dump() { // from class: android.service.dreams.DreamService.2
            @Override // com.android.internal.util.DumpUtils.Dump
            public void dump(PrintWriter pw2, String prefix) {
                DreamService.this.dumpOnHandler(fd, pw2, args);
            }
        }, pw, "", 1000L);
    }

    protected void dumpOnHandler(FileDescriptor fd, PrintWriter pw, String[] args) {
        pw.print(this.TAG + ": ");
        if (this.mFinished) {
            pw.println("stopped");
        } else {
            pw.println("running (dreamToken=" + this.mDreamToken + ")");
        }
        pw.println("  window: " + this.mWindow);
        pw.print("  flags:");
        if (isInteractive()) {
            pw.print(" interactive");
        }
        if (isFullscreen()) {
            pw.print(" fullscreen");
        }
        if (isScreenBright()) {
            pw.print(" bright");
        }
        if (isWindowless()) {
            pw.print(" windowless");
        }
        if (isDozing()) {
            pw.print(" dozing");
        } else if (canDoze()) {
            pw.print(" candoze");
        }
        pw.println();
        if (canDoze()) {
            pw.println("  doze screen state: " + Display.stateToString(this.mDozeScreenState));
            pw.println("  doze screen brightness: " + this.mDozeScreenBrightness);
        }
    }

    private static int clampAbsoluteBrightness(int value) {
        return MathUtils.constrain(value, 0, 255);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public final class DreamServiceWrapper extends IDreamService.Stub {
        DreamServiceWrapper() {
        }

        @Override // android.service.dreams.IDreamService
        public void attach(final IBinder dreamToken, final boolean canDoze, final IRemoteCallback started) {
            DreamService.this.mHandler.post(new Runnable() { // from class: android.service.dreams.DreamService$DreamServiceWrapper$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    DreamService.DreamServiceWrapper.this.lambda$attach$0$DreamService$DreamServiceWrapper(dreamToken, canDoze, started);
                }
            });
        }

        public /* synthetic */ void lambda$attach$0$DreamService$DreamServiceWrapper(IBinder dreamToken, boolean canDoze, IRemoteCallback started) {
            DreamService.this.attach(dreamToken, canDoze, started);
        }

        @Override // android.service.dreams.IDreamService
        public void detach() {
            Handler handler = DreamService.this.mHandler;
            final DreamService dreamService = DreamService.this;
            handler.post(new Runnable() { // from class: android.service.dreams.DreamService$DreamServiceWrapper$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    DreamService.this.detach();
                }
            });
        }

        public /* synthetic */ void lambda$wakeUp$2$DreamService$DreamServiceWrapper() {
            DreamService.this.wakeUp(true);
        }

        @Override // android.service.dreams.IDreamService
        public void wakeUp() {
            DreamService.this.mHandler.post(new Runnable() { // from class: android.service.dreams.DreamService$DreamServiceWrapper$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DreamService.DreamServiceWrapper.this.lambda$wakeUp$2$DreamService$DreamServiceWrapper();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void onActivityCreated(DreamActivity a) {
            DreamService.this.mActivity = a;
            DreamService.this.onWindowCreated(a.getWindow());
        }
    }
}
