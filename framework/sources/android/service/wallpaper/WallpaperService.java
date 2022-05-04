package android.service.wallpaper;

import android.annotation.SystemApi;
import android.app.IWallpaperManagerExt;
import android.app.Service;
import android.app.WallpaperColors;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BLASTBufferQueue;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.Trace;
import android.provider.SettingsStringUtil;
import android.service.wallpaper.IWallpaperEngine;
import android.service.wallpaper.IWallpaperService;
import android.service.wallpaper.WallpaperService;
import android.util.ArraySet;
import android.util.Log;
import android.util.MergedConfiguration;
import android.view.Display;
import android.view.DisplayCutout;
import android.view.IWindowSession;
import android.view.InputChannel;
import android.view.InputEvent;
import android.view.InputEventReceiver;
import android.view.InsetsSourceControl;
import android.view.InsetsState;
import android.view.MotionEvent;
import android.view.PixelCopy;
import android.view.Surface;
import android.view.SurfaceControl;
import android.view.SurfaceHolder;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowManagerGlobal;
import android.window.ClientWindowFrames;
import com.android.internal.R;
import com.android.internal.os.HandlerCaller;
import com.android.internal.view.BaseIWindow;
import com.android.internal.view.BaseSurfaceHolder;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Supplier;
import system.ext.loader.core.ExtLoader;
/* loaded from: classes3.dex */
public abstract class WallpaperService extends Service {
    private static final long DEFAULT_UPDATE_SCREENSHOT_DURATION = 60000;
    private static final int DO_ATTACH = 10;
    private static final int DO_DETACH = 20;
    private static final int DO_IN_AMBIENT_MODE = 50;
    private static final int DO_SET_DESIRED_SIZE = 30;
    private static final int DO_SET_DISPLAY_PADDING = 40;
    private static final int MIN_BITMAP_SCREENSHOT_WIDTH = 64;
    static final float MIN_PAGE_ALLOWED_MARGIN = 0.05f;
    private static final int MSG_REQUEST_WALLPAPER_COLORS = 10050;
    private static final int MSG_SCALE_PREVIEW = 10110;
    private static final int MSG_TOUCH_EVENT = 10040;
    private static final int MSG_UPDATE_SURFACE = 10000;
    private static final int MSG_VISIBILITY_CHANGED = 10010;
    private static final int MSG_WALLPAPER_COMMAND = 10025;
    private static final int MSG_WALLPAPER_OFFSETS = 10020;
    private static final int MSG_WINDOW_MOVED = 10035;
    private static final int MSG_WINDOW_RESIZED = 10030;
    private static final int MSG_ZOOM = 10100;
    private static final int NOTIFY_COLORS_RATE_LIMIT_MS = 1000;
    public static final String SERVICE_INTERFACE = "android.service.wallpaper.WallpaperService";
    public static final String SERVICE_META_DATA = "android.service.wallpaper";
    static final String TAG = "WallpaperService";
    private final ArrayList<Engine> mActiveEngines = new ArrayList<>();
    public IWallpaperServiceExt mOplusWallpaperServiceExt = (IWallpaperServiceExt) ExtLoader.type(IWallpaperServiceExt.class).base(this).create();
    private IWallpaperManagerExt mWallpaperManagerExt = (IWallpaperManagerExt) ExtLoader.type(IWallpaperManagerExt.class).create();
    static final boolean DEBUG = SystemProperties.getBoolean("persist.sys.assert.panic", false);
    private static final RectF LOCAL_COLOR_BOUNDS = new RectF(0.0f, 0.0f, 1.0f, 1.0f);
    private static final List<Float> PROHIBITED_STEPS = Arrays.asList(Float.valueOf(0.0f), Float.valueOf(Float.POSITIVE_INFINITY), Float.valueOf(Float.NEGATIVE_INFINITY));
    private static final boolean ENABLE_WALLPAPER_DIMMING = SystemProperties.getBoolean("persist.debug.enable_wallpaper_dimming", true);

    public abstract Engine onCreateEngine();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class WallpaperCommand {
        String action;
        Bundle extras;
        boolean sync;
        int x;
        int y;
        int z;

        WallpaperCommand() {
        }
    }

    /* loaded from: classes3.dex */
    public class Engine {
        SurfaceControl mBbqSurfaceControl;
        BLASTBufferQueue mBlastBufferQueue;
        HandlerCaller mCaller;
        private final Supplier<Long> mClockFunction;
        IWallpaperConnection mConnection;
        boolean mCreated;
        int mCurHeight;
        int mCurWidth;
        int mCurWindowFlags;
        int mCurWindowPrivateFlags;
        boolean mDestroyed;
        final Rect mDispatchedContentInsets;
        DisplayCutout mDispatchedDisplayCutout;
        final Rect mDispatchedStableInsets;
        private Display mDisplay;
        private Context mDisplayContext;
        private final DisplayManager.DisplayListener mDisplayListener;
        private int mDisplayState;
        boolean mDrawingAllowed;
        final Rect mFinalStableInsets;
        final Rect mFinalSystemInsets;
        boolean mFixedSizeAllowed;
        int mFormat;
        private final Handler mHandler;
        int mHeight;
        IWallpaperEngineWrapper mIWallpaperEngine;
        boolean mInitializing;
        WallpaperInputEventReceiver mInputEventReceiver;
        final InsetsState mInsetsState;
        boolean mIsCreating;
        boolean mIsInAmbientMode;
        private long mLastColorInvalidation;
        float mLastPageOffset;
        Bitmap mLastScreenshot;
        private final Point mLastSurfaceSize;
        int mLastWindowPage;
        final WindowManager.LayoutParams mLayout;
        final ArraySet<RectF> mLocalColorAreas;
        final ArraySet<RectF> mLocalColorsToAdd;
        final Object mLock;
        final MergedConfiguration mMergedConfiguration;
        private final Runnable mNotifyColorsChanged;
        boolean mOffsetMessageEnqueued;
        boolean mOffsetsChanged;
        MotionEvent mPendingMove;
        boolean mPendingSync;
        float mPendingXOffset;
        float mPendingXOffsetStep;
        float mPendingYOffset;
        float mPendingYOffsetStep;
        Rect mPreviewSurfacePosition;
        boolean mReportedVisible;
        IWindowSession mSession;
        boolean mShouldDim;
        SurfaceControl mSurfaceControl;
        boolean mSurfaceCreated;
        final BaseSurfaceHolder mSurfaceHolder;
        private final Point mSurfaceSize;
        final InsetsSourceControl[] mTempControls;
        private final Matrix mTmpMatrix;
        private final float[] mTmpValues;
        int mType;
        boolean mVisible;
        private float mWallpaperDimAmount;
        int mWidth;
        final ClientWindowFrames mWinFrames;
        final BaseIWindow mWindow;
        int mWindowFlags;
        EngineWindowPage[] mWindowPages;
        int mWindowPrivateFlags;
        IBinder mWindowToken;
        float mZoom;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes3.dex */
        public final class WallpaperInputEventReceiver extends InputEventReceiver {
            public WallpaperInputEventReceiver(InputChannel inputChannel, Looper looper) {
                super(inputChannel, looper);
            }

            @Override // android.view.InputEventReceiver
            public void onInputEvent(InputEvent event) {
                boolean handled = false;
                try {
                    if ((event instanceof MotionEvent) && (event.getSource() & 2) != 0) {
                        MotionEvent dup = MotionEvent.obtainNoHistory((MotionEvent) event);
                        Engine.this.dispatchPointer(dup);
                        handled = true;
                    }
                } finally {
                    finishInputEvent(event, false);
                }
            }
        }

        public Engine(WallpaperService this$0) {
            this(WallpaperService$Engine$$ExternalSyntheticLambda7.INSTANCE, Handler.getMain());
        }

        public Engine(Supplier<Long> clockFunction, Handler handler) {
            this.mLocalColorAreas = new ArraySet<>(4);
            this.mLocalColorsToAdd = new ArraySet<>(4);
            this.mWindowPages = new EngineWindowPage[1];
            this.mLastWindowPage = -1;
            this.mLastPageOffset = 0.0f;
            this.mInitializing = true;
            this.mZoom = 0.0f;
            this.mWindowFlags = 16;
            this.mWindowPrivateFlags = 33554436;
            this.mCurWindowFlags = 16;
            this.mCurWindowPrivateFlags = 33554436;
            this.mWinFrames = new ClientWindowFrames();
            this.mDispatchedContentInsets = new Rect();
            this.mDispatchedStableInsets = new Rect();
            this.mFinalSystemInsets = new Rect();
            this.mFinalStableInsets = new Rect();
            this.mDispatchedDisplayCutout = DisplayCutout.NO_CUTOUT;
            this.mInsetsState = new InsetsState();
            this.mTempControls = new InsetsSourceControl[0];
            this.mMergedConfiguration = new MergedConfiguration();
            this.mSurfaceSize = new Point();
            this.mLastSurfaceSize = new Point();
            this.mTmpMatrix = new Matrix();
            this.mTmpValues = new float[9];
            this.mLayout = new WindowManager.LayoutParams();
            this.mLock = new Object();
            this.mNotifyColorsChanged = new Runnable() { // from class: android.service.wallpaper.WallpaperService$Engine$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    WallpaperService.Engine.this.notifyColorsChanged();
                }
            };
            this.mWallpaperDimAmount = WallpaperService.MIN_PAGE_ALLOWED_MARGIN;
            this.mSurfaceControl = new SurfaceControl();
            this.mSurfaceHolder = new BaseSurfaceHolder() { // from class: android.service.wallpaper.WallpaperService.Engine.1
                {
                    this.mRequestedFormat = 2;
                }

                @Override // com.android.internal.view.BaseSurfaceHolder
                public boolean onAllowLockCanvas() {
                    return Engine.this.mDrawingAllowed;
                }

                @Override // com.android.internal.view.BaseSurfaceHolder
                public void onRelayoutContainer() {
                    Message msg = Engine.this.mCaller.obtainMessage(10000);
                    Engine.this.mCaller.sendMessage(msg);
                }

                @Override // com.android.internal.view.BaseSurfaceHolder
                public void onUpdateSurface() {
                    Message msg = Engine.this.mCaller.obtainMessage(10000);
                    Engine.this.mCaller.sendMessage(msg);
                }

                @Override // android.view.SurfaceHolder
                public boolean isCreating() {
                    return Engine.this.mIsCreating;
                }

                @Override // com.android.internal.view.BaseSurfaceHolder, android.view.SurfaceHolder
                public void setFixedSize(int width, int height) {
                    if (Engine.this.mFixedSizeAllowed) {
                        super.setFixedSize(width, height);
                        return;
                    }
                    throw new UnsupportedOperationException("Wallpapers currently only support sizing from layout");
                }

                @Override // android.view.SurfaceHolder
                public void setKeepScreenOn(boolean screenOn) {
                    throw new UnsupportedOperationException("Wallpapers do not support keep screen on");
                }

                private void prepareToDraw() {
                    if (Engine.this.mDisplayState == 3 || Engine.this.mDisplayState == 4) {
                        try {
                            Engine.this.mSession.pokeDrawLock(Engine.this.mWindow);
                        } catch (RemoteException e) {
                        }
                    }
                }

                @Override // com.android.internal.view.BaseSurfaceHolder, android.view.SurfaceHolder
                public Canvas lockCanvas() {
                    prepareToDraw();
                    return super.lockCanvas();
                }

                @Override // com.android.internal.view.BaseSurfaceHolder, android.view.SurfaceHolder
                public Canvas lockCanvas(Rect dirty) {
                    prepareToDraw();
                    return super.lockCanvas(dirty);
                }

                @Override // com.android.internal.view.BaseSurfaceHolder, android.view.SurfaceHolder
                public Canvas lockHardwareCanvas() {
                    prepareToDraw();
                    return super.lockHardwareCanvas();
                }
            };
            this.mWindow = new BaseIWindow() { // from class: android.service.wallpaper.WallpaperService.Engine.2
                @Override // com.android.internal.view.BaseIWindow, android.view.IWindow
                public void resized(ClientWindowFrames frames, boolean reportDraw, MergedConfiguration mergedConfiguration, boolean forceLayout, boolean alwaysConsumeSystemBars, int displayId) {
                    Message msg = Engine.this.mCaller.obtainMessageI(10030, reportDraw ? 1 : 0);
                    Engine.this.mCaller.sendMessage(msg);
                }

                @Override // com.android.internal.view.BaseIWindow, android.view.IWindow
                public void moved(int newX, int newY) {
                    Message msg = Engine.this.mCaller.obtainMessageII(10035, newX, newY);
                    Engine.this.mCaller.sendMessage(msg);
                }

                @Override // com.android.internal.view.BaseIWindow, android.view.IWindow
                public void dispatchAppVisibility(boolean visible) {
                    if (!Engine.this.mIWallpaperEngine.mIsPreview) {
                        Message msg = Engine.this.mCaller.obtainMessageI(10010, visible ? 1 : 0);
                        Engine.this.mCaller.sendMessage(msg);
                    }
                }

                @Override // com.android.internal.view.BaseIWindow, android.view.IWindow
                public void dispatchWallpaperOffsets(float x, float y, float xStep, float yStep, float zoom, boolean sync) {
                    synchronized (Engine.this.mLock) {
                        if (WallpaperService.DEBUG) {
                            Log.v(WallpaperService.TAG, "Dispatch wallpaper offsets: " + x + ", " + y);
                        }
                        Engine.this.mPendingXOffset = x;
                        Engine.this.mPendingYOffset = y;
                        Engine.this.mPendingXOffsetStep = xStep;
                        Engine.this.mPendingYOffsetStep = yStep;
                        if (sync) {
                            Engine.this.mPendingSync = true;
                        }
                        if (!Engine.this.mOffsetMessageEnqueued) {
                            Engine.this.mOffsetMessageEnqueued = true;
                            Message msg = Engine.this.mCaller.obtainMessage(10020);
                            Engine.this.mCaller.sendMessage(msg);
                        }
                        Message msg2 = Engine.this.mCaller.obtainMessageI(10100, Float.floatToIntBits(zoom));
                        Engine.this.mCaller.sendMessage(msg2);
                    }
                }

                @Override // com.android.internal.view.BaseIWindow, android.view.IWindow
                public void dispatchWallpaperCommand(String action, int x, int y, int z, Bundle extras, boolean sync) {
                    synchronized (Engine.this.mLock) {
                        if (WallpaperService.DEBUG) {
                            Log.v(WallpaperService.TAG, "Dispatch wallpaper command: " + x + ", " + y);
                        }
                        WallpaperCommand cmd = new WallpaperCommand();
                        cmd.action = action;
                        cmd.x = x;
                        cmd.y = y;
                        cmd.z = z;
                        cmd.extras = extras;
                        cmd.sync = sync;
                        Message msg = Engine.this.mCaller.obtainMessage(10025);
                        msg.obj = cmd;
                        Engine.this.mCaller.sendMessage(msg);
                    }
                }
            };
            this.mDisplayListener = new DisplayManager.DisplayListener() { // from class: android.service.wallpaper.WallpaperService.Engine.3
                @Override // android.hardware.display.DisplayManager.DisplayListener
                public void onDisplayChanged(int displayId) {
                    if (Engine.this.mDisplay.getDisplayId() == displayId) {
                        Engine.this.reportVisibility();
                    }
                }

                @Override // android.hardware.display.DisplayManager.DisplayListener
                public void onDisplayRemoved(int displayId) {
                }

                @Override // android.hardware.display.DisplayManager.DisplayListener
                public void onDisplayAdded(int displayId) {
                }
            };
            this.mClockFunction = clockFunction;
            this.mHandler = handler;
            WallpaperService.this.mOplusWallpaperServiceExt.setWallPaperEngine(this);
        }

        public SurfaceHolder getSurfaceHolder() {
            return this.mSurfaceHolder;
        }

        public int getDesiredMinimumWidth() {
            return this.mIWallpaperEngine.mReqWidth;
        }

        public int getDesiredMinimumHeight() {
            return this.mIWallpaperEngine.mReqHeight;
        }

        public boolean isVisible() {
            return this.mReportedVisible;
        }

        public boolean supportsLocalColorExtraction() {
            return false;
        }

        public boolean isPreview() {
            return this.mIWallpaperEngine.mIsPreview;
        }

        @SystemApi
        public boolean isInAmbientMode() {
            return this.mIsInAmbientMode;
        }

        public boolean shouldZoomOutWallpaper() {
            return false;
        }

        public void setTouchEventsEnabled(boolean enabled) {
            int i;
            if (enabled) {
                i = this.mWindowFlags & (-17);
            } else {
                i = this.mWindowFlags | 16;
            }
            this.mWindowFlags = i;
            if (this.mCreated) {
                updateSurface(false, false, false);
            }
        }

        public void setOffsetNotificationsEnabled(boolean enabled) {
            int i;
            if (enabled) {
                i = this.mWindowPrivateFlags | 4;
            } else {
                i = this.mWindowPrivateFlags & (-5);
            }
            this.mWindowPrivateFlags = i;
            if (this.mCreated) {
                updateSurface(false, false, false);
            }
        }

        public void setFixedSizeAllowed(boolean allowed) {
            this.mFixedSizeAllowed = allowed;
        }

        public float getZoom() {
            return this.mZoom;
        }

        public void onCreate(SurfaceHolder surfaceHolder) {
        }

        public void onDestroy() {
        }

        public void onVisibilityChanged(boolean visible) {
        }

        public void onApplyWindowInsets(WindowInsets insets) {
        }

        public void onTouchEvent(MotionEvent event) {
        }

        public void onOffsetsChanged(float xOffset, float yOffset, float xOffsetStep, float yOffsetStep, int xPixelOffset, int yPixelOffset) {
        }

        public Bundle onCommand(String action, int x, int y, int z, Bundle extras, boolean resultRequested) {
            WallpaperService.this.mOplusWallpaperServiceExt.onCommand(action, x, y, z, extras, resultRequested);
            return null;
        }

        @SystemApi
        public void onAmbientModeChanged(boolean inAmbientMode, long animationDuration) {
        }

        public void onDesiredSizeChanged(int desiredWidth, int desiredHeight) {
        }

        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        }

        public void onSurfaceRedrawNeeded(SurfaceHolder holder) {
        }

        public void onSurfaceCreated(SurfaceHolder holder) {
        }

        public void onSurfaceDestroyed(SurfaceHolder holder) {
        }

        public void onZoomChanged(float zoom) {
        }

        public void notifyColorsChanged() {
            long now = this.mClockFunction.get().longValue();
            if (now - this.mLastColorInvalidation < 1000) {
                Log.w(WallpaperService.TAG, "This call has been deferred. You should only call notifyColorsChanged() once every 1.0 seconds.");
                if (!this.mHandler.hasCallbacks(this.mNotifyColorsChanged)) {
                    this.mHandler.postDelayed(this.mNotifyColorsChanged, 1000L);
                    return;
                }
                return;
            }
            this.mLastColorInvalidation = now;
            this.mHandler.removeCallbacks(this.mNotifyColorsChanged);
            try {
                WallpaperColors newColors = onComputeColors();
                IWallpaperConnection iWallpaperConnection = this.mConnection;
                if (iWallpaperConnection != null) {
                    iWallpaperConnection.onWallpaperColorsChanged(newColors, this.mDisplay.getDisplayId());
                } else {
                    Log.w(WallpaperService.TAG, "Can't notify system because wallpaper connection was not established.");
                }
                resetWindowPages();
                processLocalColors(this.mPendingXOffset, this.mPendingXOffsetStep);
            } catch (RemoteException e) {
                Log.w(WallpaperService.TAG, "Can't notify system because wallpaper connection was lost.", e);
            }
        }

        public WallpaperColors onComputeColors() {
            return null;
        }

        public void notifyLocalColorsChanged(List<RectF> regions, List<WallpaperColors> colors) throws RuntimeException {
            for (int i = 0; i < regions.size() && i < colors.size(); i++) {
                WallpaperColors color = colors.get(i);
                RectF area = regions.get(i);
                if (color != null && area != null) {
                    try {
                        this.mConnection.onLocalWallpaperColorsChanged(area, color, this.mDisplayContext.getDisplayId());
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                } else if (WallpaperService.DEBUG) {
                    Log.e(WallpaperService.TAG, "notifyLocalColorsChanged null values. color: " + color + " area " + area);
                }
            }
            WallpaperColors primaryColors = this.mIWallpaperEngine.mWallpaperManager.getWallpaperColors(1);
            setPrimaryWallpaperColors(primaryColors);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPrimaryWallpaperColors(WallpaperColors colors) {
            if (colors != null) {
                int colorHints = colors.getColorHints();
                boolean shouldDim = (colorHints & 1) == 0 && (colorHints & 2) == 0;
                if (shouldDim != this.mShouldDim) {
                    this.mShouldDim = shouldDim;
                    updateSurfaceDimming();
                    updateSurface(false, false, true);
                }
            }
        }

        private void updateSurfaceDimming() {
            if (WallpaperService.ENABLE_WALLPAPER_DIMMING && this.mBbqSurfaceControl != null) {
                if (isPreview() || !this.mShouldDim) {
                    Log.v(WallpaperService.TAG, "Setting wallpaper dimming: 0");
                    new SurfaceControl.Transaction().setAlpha(this.mBbqSurfaceControl, 1.0f).apply();
                    return;
                }
                Log.v(WallpaperService.TAG, "Setting wallpaper dimming: " + this.mWallpaperDimAmount);
                new SurfaceControl.Transaction().setAlpha(this.mBbqSurfaceControl, 1.0f - this.mWallpaperDimAmount).apply();
            }
        }

        public void setCreated(boolean created) {
            this.mCreated = created;
        }

        protected void dump(String prefix, FileDescriptor fd, PrintWriter out, String[] args) {
            out.print(prefix);
            out.print("mInitializing=");
            out.print(this.mInitializing);
            out.print(" mDestroyed=");
            out.println(this.mDestroyed);
            out.print(prefix);
            out.print("mVisible=");
            out.print(this.mVisible);
            out.print(" mReportedVisible=");
            out.println(this.mReportedVisible);
            out.print(prefix);
            out.print("mDisplay=");
            out.println(this.mDisplay);
            out.print(prefix);
            out.print("mCreated=");
            out.print(this.mCreated);
            out.print(" mSurfaceCreated=");
            out.print(this.mSurfaceCreated);
            out.print(" mIsCreating=");
            out.print(this.mIsCreating);
            out.print(" mDrawingAllowed=");
            out.println(this.mDrawingAllowed);
            out.print(prefix);
            out.print("mWidth=");
            out.print(this.mWidth);
            out.print(" mCurWidth=");
            out.print(this.mCurWidth);
            out.print(" mHeight=");
            out.print(this.mHeight);
            out.print(" mCurHeight=");
            out.println(this.mCurHeight);
            out.print(prefix);
            out.print("mType=");
            out.print(this.mType);
            out.print(" mWindowFlags=");
            out.print(this.mWindowFlags);
            out.print(" mCurWindowFlags=");
            out.println(this.mCurWindowFlags);
            out.print(prefix);
            out.print("mWindowPrivateFlags=");
            out.print(this.mWindowPrivateFlags);
            out.print(" mCurWindowPrivateFlags=");
            out.println(this.mCurWindowPrivateFlags);
            out.print(prefix);
            out.println("mWinFrames=");
            out.println(this.mWinFrames);
            out.print(prefix);
            out.print("mConfiguration=");
            out.println(this.mMergedConfiguration.getMergedConfiguration());
            out.print(prefix);
            out.print("mLayout=");
            out.println(this.mLayout);
            out.print(prefix);
            out.print("mZoom=");
            out.println(this.mZoom);
            out.print(prefix);
            out.print("mPreviewSurfacePosition=");
            out.println(this.mPreviewSurfacePosition);
            synchronized (this.mLock) {
                out.print(prefix);
                out.print("mPendingXOffset=");
                out.print(this.mPendingXOffset);
                out.print(" mPendingXOffset=");
                out.println(this.mPendingXOffset);
                out.print(prefix);
                out.print("mPendingXOffsetStep=");
                out.print(this.mPendingXOffsetStep);
                out.print(" mPendingXOffsetStep=");
                out.println(this.mPendingXOffsetStep);
                out.print(prefix);
                out.print("mOffsetMessageEnqueued=");
                out.print(this.mOffsetMessageEnqueued);
                out.print(" mPendingSync=");
                out.println(this.mPendingSync);
                if (this.mPendingMove != null) {
                    out.print(prefix);
                    out.print("mPendingMove=");
                    out.println(this.mPendingMove);
                }
            }
        }

        public void setZoom(float zoom) {
            if (WallpaperService.DEBUG) {
                Log.v(WallpaperService.TAG, "set zoom received: " + zoom);
            }
            boolean updated = false;
            synchronized (this.mLock) {
                if (WallpaperService.DEBUG) {
                    Log.v(WallpaperService.TAG, "mZoom: " + this.mZoom + " updated: " + zoom);
                }
                if (this.mIsInAmbientMode) {
                    this.mZoom = 0.0f;
                }
                if (Float.compare(zoom, this.mZoom) != 0) {
                    this.mZoom = zoom;
                    updated = true;
                }
            }
            if (WallpaperService.DEBUG) {
                Log.v(WallpaperService.TAG, "setZoom updated? " + updated);
            }
            if (updated && !this.mDestroyed) {
                onZoomChanged(this.mZoom);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void dispatchPointer(MotionEvent event) {
            if (event.isTouchEvent()) {
                synchronized (this.mLock) {
                    if (event.getAction() == 2) {
                        this.mPendingMove = event;
                    } else {
                        this.mPendingMove = null;
                    }
                }
                Message msg = this.mCaller.obtainMessageO(10040, event);
                this.mCaller.sendMessage(msg);
                return;
            }
            event.recycle();
        }

        /* JADX WARN: Removed duplicated region for block: B:277:0x076b A[Catch: RemoteException -> 0x078c, TryCatch #28 {RemoteException -> 0x078c, blocks: (B:265:0x071c, B:267:0x0723, B:268:0x073a, B:275:0x0763, B:277:0x076b, B:278:0x0782, B:279:0x078b), top: B:363:0x04d3 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        void updateSurface(boolean r46, boolean r47, boolean r48) {
            /*
                Method dump skipped, instructions count: 2197
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: android.service.wallpaper.WallpaperService.Engine.updateSurface(boolean, boolean, boolean):void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void scalePreview(Rect position) {
            Rect rect;
            if ((isPreview() && this.mPreviewSurfacePosition == null && position != null) || ((rect = this.mPreviewSurfacePosition) != null && !rect.equals(position))) {
                this.mPreviewSurfacePosition = position;
                if (this.mSurfaceControl.isValid()) {
                    reposition();
                } else {
                    updateSurface(false, false, false);
                }
            }
        }

        private void reposition() {
            if (this.mPreviewSurfacePosition != null) {
                if (WallpaperService.DEBUG) {
                    Log.i(WallpaperService.TAG, "reposition: rect: " + this.mPreviewSurfacePosition);
                }
                this.mTmpMatrix.setTranslate(this.mPreviewSurfacePosition.left, this.mPreviewSurfacePosition.top);
                this.mTmpMatrix.postScale(this.mPreviewSurfacePosition.width() / this.mCurWidth, this.mPreviewSurfacePosition.height() / this.mCurHeight);
                this.mTmpMatrix.getValues(this.mTmpValues);
                SurfaceControl.Transaction t = new SurfaceControl.Transaction();
                t.setPosition(this.mSurfaceControl, this.mPreviewSurfacePosition.left, this.mPreviewSurfacePosition.top);
                SurfaceControl surfaceControl = this.mSurfaceControl;
                float[] fArr = this.mTmpValues;
                t.setMatrix(surfaceControl, fArr[0], fArr[3], fArr[1], fArr[4]);
                t.apply();
            }
        }

        void attach(IWallpaperEngineWrapper wrapper) {
            if (WallpaperService.DEBUG) {
                Log.v(WallpaperService.TAG, "attach: " + this + " wrapper=" + wrapper);
            }
            if (!this.mDestroyed) {
                this.mIWallpaperEngine = wrapper;
                this.mCaller = wrapper.mCaller;
                this.mConnection = wrapper.mConnection;
                this.mWindowToken = wrapper.mWindowToken;
                this.mSurfaceHolder.setSizeFromLayout();
                this.mInitializing = true;
                IWindowSession windowSession = WindowManagerGlobal.getWindowSession();
                this.mSession = windowSession;
                this.mWindow.setSession(windowSession);
                this.mLayout.packageName = WallpaperService.this.getPackageName();
                this.mIWallpaperEngine.mDisplayManager.registerDisplayListener(this.mDisplayListener, this.mCaller.getHandler());
                Display display = this.mIWallpaperEngine.mDisplay;
                this.mDisplay = display;
                Context createWindowContext = WallpaperService.this.createDisplayContext(display).createWindowContext(2013, null);
                this.mDisplayContext = createWindowContext;
                this.mWallpaperDimAmount = createWindowContext.getResources().getFloat(R.dimen.config_wallpaperDimAmount);
                this.mDisplayState = this.mDisplay.getState();
                if (WallpaperService.DEBUG) {
                    Log.v(WallpaperService.TAG, "onCreate(): " + this);
                }
                onCreate(this.mSurfaceHolder);
                this.mIWallpaperEngine.mWallpaperServiceHelper.registerSetingsContentObserver(this.mDisplayContext);
                this.mInitializing = false;
                this.mReportedVisible = false;
                Trace.traceBegin(8L, "WallpaperService.Engine.updateSurface");
                updateSurface(false, false, false);
                Trace.traceEnd(8L);
            }
        }

        public Context getDisplayContext() {
            return this.mDisplayContext;
        }

        public void doAmbientModeChanged(boolean inAmbientMode, long animationDuration) {
            if (!this.mDestroyed) {
                if (WallpaperService.DEBUG) {
                    Log.v(WallpaperService.TAG, "onAmbientModeChanged(" + inAmbientMode + ", " + animationDuration + "): " + this);
                }
                this.mIsInAmbientMode = inAmbientMode;
                if (this.mCreated) {
                    onAmbientModeChanged(inAmbientMode, animationDuration);
                }
            }
        }

        void doDesiredSizeChanged(int desiredWidth, int desiredHeight) {
            if (!this.mDestroyed) {
                if (WallpaperService.DEBUG) {
                    Log.v(WallpaperService.TAG, "onDesiredSizeChanged(" + desiredWidth + "," + desiredHeight + "): " + this);
                }
                this.mIWallpaperEngine.mReqWidth = desiredWidth;
                this.mIWallpaperEngine.mReqHeight = desiredHeight;
                onDesiredSizeChanged(desiredWidth, desiredHeight);
                doOffsetsChanged(true);
            }
        }

        void doDisplayPaddingChanged(Rect padding) {
            if (!this.mDestroyed) {
                if (WallpaperService.DEBUG) {
                    Log.v(WallpaperService.TAG, "onDisplayPaddingChanged(" + padding + "): " + this);
                }
                if (!this.mIWallpaperEngine.mDisplayPadding.equals(padding)) {
                    this.mIWallpaperEngine.mDisplayPadding.set(padding);
                    updateSurface(true, false, false);
                }
            }
        }

        void doVisibilityChanged(boolean visible) {
            if (!this.mDestroyed) {
                this.mVisible = visible;
                reportVisibility();
                if (visible) {
                    processLocalColors(this.mPendingXOffset, this.mPendingXOffsetStep);
                }
            }
        }

        void reportVisibility() {
            if (!this.mDestroyed) {
                Display display = this.mDisplay;
                int state = display == null ? 0 : display.getState();
                this.mDisplayState = state;
                boolean visible = this.mVisible && state != 1;
                if (this.mReportedVisible != visible) {
                    this.mReportedVisible = visible;
                    if (WallpaperService.DEBUG) {
                        Log.v(WallpaperService.TAG, "onVisibilityChanged(" + visible + "): " + this);
                    }
                    if (visible) {
                        doOffsetsChanged(false);
                        updateSurface(true, false, false);
                    }
                    onVisibilityChanged(visible);
                }
            }
        }

        void doOffsetsChanged(boolean always) {
            float xOffset;
            float yOffset;
            float xOffsetStep;
            float yOffsetStep;
            boolean sync;
            int yPixels;
            int xPixels;
            if (!this.mDestroyed) {
                if (always || this.mOffsetsChanged) {
                    synchronized (this.mLock) {
                        xOffset = this.mPendingXOffset;
                        yOffset = this.mPendingYOffset;
                        xOffsetStep = this.mPendingXOffsetStep;
                        yOffsetStep = this.mPendingYOffsetStep;
                        sync = this.mPendingSync;
                        yPixels = 0;
                        this.mPendingSync = false;
                        this.mOffsetMessageEnqueued = false;
                    }
                    if (this.mSurfaceCreated) {
                        if (this.mReportedVisible) {
                            if (WallpaperService.DEBUG) {
                                Log.v(WallpaperService.TAG, "Offsets change in " + this + ": " + xOffset + "," + yOffset);
                            }
                            int availw = this.mIWallpaperEngine.mReqWidth - this.mCurWidth;
                            if (availw > 0) {
                                xPixels = -((int) ((availw * xOffset) + 0.5f));
                            } else {
                                xPixels = 0;
                            }
                            int availh = this.mIWallpaperEngine.mReqHeight - this.mCurHeight;
                            if (availh > 0) {
                                yPixels = -((int) ((availh * yOffset) + 0.5f));
                            }
                            onOffsetsChanged(xOffset, yOffset, xOffsetStep, yOffsetStep, xPixels, yPixels);
                        } else {
                            this.mOffsetsChanged = true;
                        }
                    }
                    if (sync) {
                        try {
                            if (WallpaperService.DEBUG) {
                                Log.v(WallpaperService.TAG, "Reporting offsets change complete");
                            }
                            this.mSession.wallpaperOffsetsComplete(this.mWindow.asBinder());
                        } catch (RemoteException e) {
                        }
                    }
                    processLocalColors(xOffset, xOffsetStep);
                }
            }
        }

        private void processLocalColors(float xOffset, float xOffsetStep) {
            int xPages;
            int xPage;
            float xOffsetStep2;
            float shrink;
            EngineWindowPage current;
            if (!supportsLocalColorExtraction()) {
                if (WallpaperService.DEBUG) {
                    Log.d(WallpaperService.TAG, "processLocalColors " + xOffset + " of step " + xOffsetStep);
                }
                if (xOffset % xOffsetStep <= WallpaperService.MIN_PAGE_ALLOWED_MARGIN && this.mSurfaceHolder.getSurface().isValid()) {
                    if (!validStep(xOffsetStep)) {
                        if (WallpaperService.DEBUG) {
                            Log.w(WallpaperService.TAG, "invalid offset step " + xOffsetStep);
                        }
                        xPage = 0;
                        xPages = 1;
                        shrink = 0.0f;
                        xOffsetStep2 = 1.0f;
                    } else {
                        int xPages2 = Math.round(1.0f / xOffsetStep) + 1;
                        float xOffsetStep3 = 1.0f / xPages2;
                        float xOffset2 = xOffset * ((xPages2 - 1) / xPages2);
                        xPage = Math.round(xOffset2 / xOffsetStep3);
                        shrink = xOffset2;
                        xPages = xPages2;
                        xOffsetStep2 = xOffsetStep3;
                    }
                    if (WallpaperService.DEBUG) {
                        Log.d(WallpaperService.TAG, "xPages " + xPages + " xPage " + xPage);
                        Log.d(WallpaperService.TAG, "xOffsetStep " + xOffsetStep2 + " xOffset " + shrink);
                    }
                    synchronized (this.mLock) {
                        EngineWindowPage[] engineWindowPageArr = this.mWindowPages;
                        if (engineWindowPageArr.length == 0 || engineWindowPageArr.length != xPages) {
                            EngineWindowPage[] engineWindowPageArr2 = new EngineWindowPage[xPages];
                            this.mWindowPages = engineWindowPageArr2;
                            initWindowPages(engineWindowPageArr2, xOffsetStep2);
                        }
                        if (this.mLocalColorsToAdd.size() != 0) {
                            Iterator<RectF> it = this.mLocalColorsToAdd.iterator();
                            while (it.hasNext()) {
                                RectF colorArea = it.next();
                                if (WallpaperService.this.isValid(colorArea)) {
                                    this.mLocalColorAreas.add(colorArea);
                                    int colorPage = getRectFPage(colorArea, xOffsetStep2);
                                    EngineWindowPage currentPage = this.mWindowPages[colorPage];
                                    if (currentPage == null) {
                                        EngineWindowPage currentPage2 = new EngineWindowPage();
                                        currentPage2.addArea(colorArea);
                                        this.mWindowPages[colorPage] = currentPage2;
                                    } else {
                                        currentPage.setLastUpdateTime(0L);
                                        currentPage.removeColor(colorArea);
                                    }
                                }
                            }
                            this.mLocalColorsToAdd.clear();
                        }
                        if (xPage >= this.mWindowPages.length) {
                            if (WallpaperService.DEBUG) {
                                Log.e(WallpaperService.TAG, "error xPage >= mWindowPages.length page: " + xPage);
                                Log.e(WallpaperService.TAG, "error on page " + xPage + " out of " + xPages);
                                Log.e(WallpaperService.TAG, "error on xOffsetStep " + xOffsetStep2 + " xOffset " + shrink);
                            }
                            xPage = this.mWindowPages.length - 1;
                        }
                        current = this.mWindowPages[xPage];
                        if (current == null) {
                            if (WallpaperService.DEBUG) {
                                Log.d(WallpaperService.TAG, "making page " + xPage + " out of " + xPages);
                            }
                            if (WallpaperService.DEBUG) {
                                Log.d(WallpaperService.TAG, "xOffsetStep " + xOffsetStep2 + " xOffset " + shrink);
                            }
                            current = new EngineWindowPage();
                            this.mWindowPages[xPage] = current;
                        }
                    }
                    updatePage(current, xPage, xPages, xOffsetStep2);
                }
            }
        }

        private void initWindowPages(EngineWindowPage[] windowPages, float step) {
            for (int i = 0; i < windowPages.length; i++) {
                windowPages[i] = new EngineWindowPage();
            }
            this.mLocalColorAreas.addAll((ArraySet<? extends RectF>) this.mLocalColorsToAdd);
            this.mLocalColorsToAdd.clear();
            Iterator<RectF> it = this.mLocalColorAreas.iterator();
            while (it.hasNext()) {
                RectF area = it.next();
                if (!WallpaperService.this.isValid(area)) {
                    this.mLocalColorAreas.remove(area);
                } else {
                    int pageNum = getRectFPage(area, step);
                    windowPages[pageNum].addArea(area);
                }
            }
        }

        void updatePage(final EngineWindowPage currentPage, final int pageIndx, final int numPages, final float xOffsetStep) {
            int smaller;
            int height;
            int width;
            final long current = SystemClock.elapsedRealtime();
            long lapsed = current - currentPage.getLastUpdateTime();
            if (lapsed >= 60000 || currentPage.getLastUpdateTime() <= 0) {
                Surface surface = this.mSurfaceHolder.getSurface();
                boolean widthIsLarger = this.mSurfaceSize.x > this.mSurfaceSize.y;
                if (widthIsLarger) {
                    smaller = this.mSurfaceSize.x;
                } else {
                    smaller = this.mSurfaceSize.y;
                }
                float ratio = 64.0f / smaller;
                int width2 = (int) (this.mSurfaceSize.x * ratio);
                int height2 = (int) (this.mSurfaceSize.y * ratio);
                if (width2 <= 0) {
                    height = height2;
                    width = width2;
                } else if (height2 <= 0) {
                    height = height2;
                    width = width2;
                } else {
                    final Bitmap screenShot = Bitmap.createBitmap(width2, height2, Bitmap.Config.ARGB_8888);
                    Trace.beginSection("WallpaperService#pixelCopy");
                    PixelCopy.request(surface, screenShot, new PixelCopy.OnPixelCopyFinishedListener() { // from class: android.service.wallpaper.WallpaperService$Engine$$ExternalSyntheticLambda0
                        @Override // android.view.PixelCopy.OnPixelCopyFinishedListener
                        public final void onPixelCopyFinished(int i) {
                            WallpaperService.Engine.this.lambda$updatePage$2$WallpaperService$Engine(currentPage, pageIndx, numPages, xOffsetStep, screenShot, current, i);
                        }
                    }, this.mHandler);
                    return;
                }
                Log.e(WallpaperService.TAG, "wrong width and height values of bitmap " + width + " " + height);
            }
        }

        public /* synthetic */ void lambda$updatePage$2$WallpaperService$Engine(EngineWindowPage currentPage, int pageIndx, int numPages, float xOffsetStep, final Bitmap finalScreenShot, final long current, int res) {
            Trace.endSection();
            if (WallpaperService.DEBUG) {
                Log.d(WallpaperService.TAG, "result of pixel copy is " + res);
            }
            if (res != 0) {
                Bitmap lastBitmap = currentPage.getBitmap();
                currentPage.execSync(new Consumer() { // from class: android.service.wallpaper.WallpaperService$Engine$$ExternalSyntheticLambda5
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        WallpaperService.Engine.this.lambda$updatePage$0$WallpaperService$Engine((EngineWindowPage) obj);
                    }
                });
                Bitmap lastScreenshot = this.mLastScreenshot;
                if (lastScreenshot != null && !lastScreenshot.isRecycled() && !Objects.equals(lastBitmap, lastScreenshot)) {
                    updatePageColors(currentPage, pageIndx, numPages, xOffsetStep);
                    return;
                }
                return;
            }
            this.mLastScreenshot = finalScreenShot;
            currentPage.execSync(new Consumer() { // from class: android.service.wallpaper.WallpaperService$Engine$$ExternalSyntheticLambda2
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    WallpaperService.Engine.lambda$updatePage$1(Bitmap.this, current, (EngineWindowPage) obj);
                }
            });
            updatePageColors(currentPage, pageIndx, numPages, xOffsetStep);
        }

        public /* synthetic */ void lambda$updatePage$0$WallpaperService$Engine(EngineWindowPage p) {
            p.setBitmap(this.mLastScreenshot);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$updatePage$1(Bitmap finalScreenShot, long current, EngineWindowPage p) {
            p.setBitmap(finalScreenShot);
            p.setLastUpdateTime(current);
        }

        private void updatePageColors(EngineWindowPage page, int pageIndx, int numPages, float xOffsetStep) {
            if (page.getBitmap() != null) {
                if (WallpaperService.DEBUG) {
                    Log.d(WallpaperService.TAG, "updatePageColorsLocked for page " + pageIndx + " with areas " + page.getAreas().size() + " and bitmap size of " + page.getBitmap().getWidth() + " x " + page.getBitmap().getHeight());
                }
                Iterator<RectF> it = page.getAreas().iterator();
                while (it.hasNext()) {
                    RectF area = it.next();
                    if (area != null) {
                        RectF subArea = generateSubRect(area, pageIndx, numPages);
                        Bitmap b = page.getBitmap();
                        int x = Math.round(b.getWidth() * subArea.left);
                        int y = Math.round(b.getHeight() * subArea.top);
                        int width = Math.round(b.getWidth() * subArea.width());
                        int height = Math.round(b.getHeight() * subArea.height());
                        try {
                            Bitmap target = Bitmap.createBitmap(page.getBitmap(), x, y, width, height);
                            WallpaperColors color = WallpaperColors.fromBitmap(target);
                            target.recycle();
                            WallpaperColors currentColor = page.getColors(area);
                            if (WallpaperService.DEBUG) {
                                Log.d(WallpaperService.TAG, "getting local bitmap area x " + x + " y " + y + " width " + width + " height " + height + " for sub area " + subArea + " and with page " + pageIndx + " of " + numPages);
                            }
                            if (currentColor == null || !color.equals(currentColor)) {
                                page.addWallpaperColors(area, color);
                                if (WallpaperService.DEBUG) {
                                    Log.d(WallpaperService.TAG, "onLocalWallpaperColorsChanged local color callback for area" + area + " for page " + pageIndx + " of " + numPages);
                                }
                                try {
                                    this.mConnection.onLocalWallpaperColorsChanged(area, color, this.mDisplayContext.getDisplayId());
                                } catch (RemoteException e) {
                                    Log.e(WallpaperService.TAG, "Error calling Connection.onLocalWallpaperColorsChanged", e);
                                }
                            }
                            it = it;
                        } catch (Exception e2) {
                            Log.e(WallpaperService.TAG, "Error creating page local color bitmap", e2);
                            it = it;
                        }
                    }
                }
            }
        }

        private RectF generateSubRect(RectF in, int pageInx, int numPages) {
            float minLeft = pageInx / numPages;
            float maxRight = (pageInx + 1) / numPages;
            float left = in.left;
            float right = in.right;
            if (left < minLeft) {
                left = minLeft;
            }
            if (right > maxRight) {
                right = maxRight;
            }
            float left2 = (numPages * left) % 1.0f;
            float right2 = (numPages * right) % 1.0f;
            if (right2 == 0.0f) {
                right2 = 1.0f;
            }
            return new RectF(left2, in.top, right2, in.bottom);
        }

        private void resetWindowPages() {
            if (!supportsLocalColorExtraction()) {
                this.mLastWindowPage = -1;
                synchronized (this.mLock) {
                    int i = 0;
                    while (true) {
                        EngineWindowPage[] engineWindowPageArr = this.mWindowPages;
                        if (i < engineWindowPageArr.length) {
                            EngineWindowPage page = engineWindowPageArr[i];
                            if (page != null) {
                                page.execSync(WallpaperService$Engine$$ExternalSyntheticLambda6.INSTANCE);
                            }
                            i++;
                        }
                    }
                }
            }
        }

        private int getRectFPage(RectF area, float step) {
            if (!WallpaperService.this.isValid(area) || !validStep(step)) {
                return 0;
            }
            int pages = Math.round(1.0f / step);
            int page = Math.round(area.centerX() * pages);
            if (page == pages) {
                return pages - 1;
            }
            EngineWindowPage[] engineWindowPageArr = this.mWindowPages;
            return page == engineWindowPageArr.length ? engineWindowPageArr.length - 1 : page;
        }

        public void addLocalColorsAreas(List<RectF> regions) {
            if (!supportsLocalColorExtraction()) {
                if (WallpaperService.DEBUG) {
                    Log.d(WallpaperService.TAG, "addLocalColorsAreas adding local color areas " + regions);
                }
                float step = this.mPendingXOffsetStep;
                List<WallpaperColors> colors = getLocalWallpaperColors(regions);
                synchronized (this.mLock) {
                    if (!validStep(step)) {
                        step = 0.0f;
                    }
                    for (int i = 0; i < regions.size(); i++) {
                        final RectF area = regions.get(i);
                        if (WallpaperService.this.isValid(area)) {
                            int pageInx = getRectFPage(area, step);
                            EngineWindowPage page = this.mWindowPages[pageInx];
                            if (page != null) {
                                this.mLocalColorAreas.add(area);
                                page.addArea(area);
                                final WallpaperColors color = colors.get(i);
                                if (color != null && !color.equals(page.getColors(area))) {
                                    page.execSync(new Consumer() { // from class: android.service.wallpaper.WallpaperService$Engine$$ExternalSyntheticLambda4
                                        @Override // java.util.function.Consumer
                                        public final void accept(Object obj) {
                                            ((EngineWindowPage) obj).addWallpaperColors(RectF.this, color);
                                        }
                                    });
                                }
                            } else {
                                this.mLocalColorsToAdd.add(area);
                            }
                        }
                    }
                }
                for (int i2 = 0; i2 < colors.size() && colors.get(i2) != null; i2++) {
                    try {
                        this.mConnection.onLocalWallpaperColorsChanged(regions.get(i2), colors.get(i2), this.mDisplayContext.getDisplayId());
                    } catch (RemoteException e) {
                        Log.e(WallpaperService.TAG, "Error calling Connection.onLocalWallpaperColorsChanged", e);
                        return;
                    }
                }
            }
        }

        public void removeLocalColorsAreas(List<RectF> regions) {
            if (!supportsLocalColorExtraction()) {
                synchronized (this.mLock) {
                    float step = this.mPendingXOffsetStep;
                    this.mLocalColorsToAdd.removeAll(regions);
                    this.mLocalColorAreas.removeAll(regions);
                    if (validStep(step)) {
                        for (int i = 0; i < regions.size(); i++) {
                            final RectF area = regions.get(i);
                            if (WallpaperService.this.isValid(area)) {
                                int pageInx = getRectFPage(area, step);
                                EngineWindowPage page = this.mWindowPages[pageInx];
                                if (page != null) {
                                    page.execSync(new Consumer() { // from class: android.service.wallpaper.WallpaperService$Engine$$ExternalSyntheticLambda3
                                        @Override // java.util.function.Consumer
                                        public final void accept(Object obj) {
                                            ((EngineWindowPage) obj).removeArea(RectF.this);
                                        }
                                    });
                                }
                            }
                        }
                    }
                }
            }
        }

        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:64:? -> B:58:0x01a1). Please submit an issue!!! */
        private List<WallpaperColors> getLocalWallpaperColors(List<RectF> areas) {
            float step;
            float step2;
            int pageIndx;
            RectF area;
            EngineWindowPage page;
            ArrayList<WallpaperColors> colors = new ArrayList<>(areas.size());
            float step3 = this.mPendingXOffsetStep;
            if (!validStep(step3)) {
                if (WallpaperService.DEBUG) {
                    Log.d(WallpaperService.TAG, "invalid step size " + step3);
                }
                step = 1.0f;
            } else {
                step = step3;
            }
            int i = 0;
            while (i < areas.size()) {
                RectF currentArea = areas.get(i);
                if (currentArea == null) {
                    step2 = step;
                } else if (!WallpaperService.this.isValid(currentArea)) {
                    step2 = step;
                } else {
                    synchronized (this.mLock) {
                        try {
                            pageIndx = getRectFPage(currentArea, step);
                            EngineWindowPage[] engineWindowPageArr = this.mWindowPages;
                            if (engineWindowPageArr.length == 0 || pageIndx < 0 || pageIndx > engineWindowPageArr.length) {
                                step2 = step;
                            } else if (!WallpaperService.this.isValid(currentArea)) {
                                step2 = step;
                            } else {
                                area = generateSubRect(currentArea, pageIndx, this.mWindowPages.length);
                                page = this.mWindowPages[pageIndx];
                            }
                            try {
                                colors.add(null);
                            } catch (Throwable th) {
                                th = th;
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            throw th;
                        }
                    }
                    if (page == null) {
                        colors.add(null);
                        step2 = step;
                    } else {
                        Bitmap screenShot = page.getBitmap();
                        if (screenShot == null) {
                            screenShot = this.mLastScreenshot;
                        }
                        if (screenShot == null) {
                            step2 = step;
                        } else if (screenShot.isRecycled()) {
                            step2 = step;
                        } else {
                            step2 = step;
                            Rect subImage = fixRect(screenShot, new Rect(Math.round((area.left * screenShot.getWidth()) / step), Math.round(area.top * screenShot.getHeight()), Math.round((area.right * screenShot.getWidth()) / step), Math.round(area.bottom * screenShot.getHeight())));
                            if (WallpaperService.DEBUG) {
                                Log.d(WallpaperService.TAG, "getting subbitmap of " + subImage.toString() + " for RectF " + area.toString() + " screenshot width " + screenShot.getWidth() + " height " + screenShot.getHeight());
                            }
                            Bitmap colorImg = Bitmap.createBitmap(screenShot, subImage.left, subImage.top, subImage.width(), subImage.height());
                            if (WallpaperService.DEBUG) {
                                Log.d(WallpaperService.TAG, "created bitmap " + colorImg.getWidth() + ", " + colorImg.getHeight());
                            }
                            WallpaperColors color = WallpaperColors.fromBitmap(colorImg);
                            colors.add(color);
                        }
                        if (WallpaperService.DEBUG) {
                            Log.d(WallpaperService.TAG, "invalid bitmap " + screenShot + " for page " + pageIndx);
                        }
                        page.setLastUpdateTime(0L);
                        colors.add(null);
                    }
                    i++;
                    step = step2;
                }
                Log.wtf(WallpaperService.TAG, "invalid local area " + currentArea);
                i++;
                step = step2;
            }
            return colors;
        }

        private Rect fixRect(Bitmap b, Rect r) {
            int i;
            int i2;
            if (r.left >= r.right || r.left >= b.getWidth() || r.left > 0) {
                i = 0;
            } else {
                i = r.left;
            }
            r.left = i;
            if (r.left >= r.right || r.right > b.getWidth()) {
                i2 = b.getWidth();
            } else {
                i2 = r.right;
            }
            r.right = i2;
            return r;
        }

        private boolean validStep(float step) {
            return !WallpaperService.PROHIBITED_STEPS.contains(Float.valueOf(step)) && ((double) step) > 0.0d && ((double) step) <= 1.0d;
        }

        void doCommand(WallpaperCommand cmd) {
            Bundle result;
            if (!this.mDestroyed) {
                result = onCommand(cmd.action, cmd.x, cmd.y, cmd.z, cmd.extras, cmd.sync);
            } else {
                result = null;
            }
            if (cmd.sync) {
                try {
                    if (WallpaperService.DEBUG) {
                        Log.v(WallpaperService.TAG, "Reporting command complete");
                    }
                    this.mSession.wallpaperCommandComplete(this.mWindow.asBinder(), result);
                } catch (RemoteException e) {
                }
            }
        }

        void reportSurfaceDestroyed() {
            if (this.mSurfaceCreated) {
                this.mSurfaceCreated = false;
                this.mSurfaceHolder.ungetCallbacks();
                SurfaceHolder.Callback[] callbacks = this.mSurfaceHolder.getCallbacks();
                if (callbacks != null) {
                    for (SurfaceHolder.Callback c : callbacks) {
                        c.surfaceDestroyed(this.mSurfaceHolder);
                    }
                }
                if (WallpaperService.DEBUG) {
                    Log.v(WallpaperService.TAG, "onSurfaceDestroyed(" + this.mSurfaceHolder + "): " + this);
                }
                onSurfaceDestroyed(this.mSurfaceHolder);
            }
        }

        void detach() {
            if (!this.mDestroyed) {
                this.mDestroyed = true;
                if (this.mIWallpaperEngine.mDisplayManager != null) {
                    this.mIWallpaperEngine.mDisplayManager.unregisterDisplayListener(this.mDisplayListener);
                }
                if (this.mVisible) {
                    this.mVisible = false;
                    if (WallpaperService.DEBUG) {
                        Log.v(WallpaperService.TAG, "onVisibilityChanged(false): " + this);
                    }
                    onVisibilityChanged(false);
                }
                reportSurfaceDestroyed();
                if (WallpaperService.DEBUG) {
                    Log.v(WallpaperService.TAG, "onDestroy(): " + this);
                }
                onDestroy();
                this.mIWallpaperEngine.mWallpaperServiceHelper.unregisterSettingsContentObserver(this.mDisplayContext);
                if (this.mCreated) {
                    try {
                        if (WallpaperService.DEBUG) {
                            Log.v(WallpaperService.TAG, "Removing window and destroying surface " + this.mSurfaceHolder.getSurface() + " of: " + this);
                        }
                        WallpaperInputEventReceiver wallpaperInputEventReceiver = this.mInputEventReceiver;
                        if (wallpaperInputEventReceiver != null) {
                            wallpaperInputEventReceiver.dispose();
                            this.mInputEventReceiver = null;
                        }
                        this.mSession.remove(this.mWindow);
                    } catch (RemoteException e) {
                    }
                    this.mSurfaceHolder.mSurface.release();
                    BLASTBufferQueue bLASTBufferQueue = this.mBlastBufferQueue;
                    if (bLASTBufferQueue != null) {
                        bLASTBufferQueue.destroy();
                        this.mBlastBufferQueue = null;
                    }
                    if (this.mBbqSurfaceControl != null) {
                        new SurfaceControl.Transaction().remove(this.mBbqSurfaceControl).apply();
                        this.mBbqSurfaceControl = null;
                    }
                    this.mCreated = false;
                }
            }
        }

        private Surface getOrCreateBLASTSurface(int width, int height, int format) {
            BLASTBufferQueue bLASTBufferQueue = this.mBlastBufferQueue;
            if (bLASTBufferQueue == null) {
                BLASTBufferQueue bLASTBufferQueue2 = new BLASTBufferQueue("Wallpaper", this.mBbqSurfaceControl, width, height, format);
                this.mBlastBufferQueue = bLASTBufferQueue2;
                Surface ret = bLASTBufferQueue2.createSurface();
                return ret;
            }
            bLASTBufferQueue.update(this.mBbqSurfaceControl, width, height, format);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isValid(RectF area) {
        return area != null && area.bottom > area.top && area.left < area.right && LOCAL_COLOR_BOUNDS.contains(area);
    }

    private boolean inRectFRange(float number) {
        return number >= 0.0f && number <= 1.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class IWallpaperEngineWrapper extends IWallpaperEngine.Stub implements HandlerCaller.Callback {
        private final HandlerCaller mCaller;
        final IWallpaperConnection mConnection;
        private final AtomicBoolean mDetached = new AtomicBoolean();
        final Display mDisplay;
        final int mDisplayId;
        final DisplayManager mDisplayManager;
        final Rect mDisplayPadding;
        Engine mEngine;
        final boolean mIsPreview;
        int mReqHeight;
        int mReqWidth;
        boolean mShownReported;
        final WallpaperManager mWallpaperManager;
        IWallpaperServiceExt mWallpaperServiceHelper;
        final IBinder mWindowToken;
        final int mWindowType;

        IWallpaperEngineWrapper(WallpaperService context, IWallpaperConnection conn, IBinder windowToken, int windowType, boolean isPreview, int reqWidth, int reqHeight, Rect padding, int displayId) {
            Rect rect = new Rect();
            this.mDisplayPadding = rect;
            this.mWallpaperServiceHelper = WallpaperService.this.mOplusWallpaperServiceExt;
            this.mWallpaperManager = (WallpaperManager) WallpaperService.this.getSystemService(WallpaperManager.class);
            HandlerCaller handlerCaller = new HandlerCaller(context, context.getMainLooper(), this, true);
            this.mCaller = handlerCaller;
            this.mConnection = conn;
            this.mWindowToken = windowToken;
            this.mWindowType = windowType;
            this.mIsPreview = isPreview;
            this.mReqWidth = reqWidth;
            this.mReqHeight = reqHeight;
            rect.set(padding);
            this.mDisplayId = displayId;
            DisplayManager displayManager = (DisplayManager) WallpaperService.this.getSystemService(DisplayManager.class);
            this.mDisplayManager = displayManager;
            Display display = displayManager.getDisplay(displayId);
            this.mDisplay = display;
            if (display != null) {
                Message msg = handlerCaller.obtainMessage(10);
                WallpaperService.this.mOplusWallpaperServiceExt.adjustMessageQueue(handlerCaller.getHandler(), msg);
                return;
            }
            throw new IllegalArgumentException("Cannot find display with id" + displayId);
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void setDesiredSize(int width, int height) {
            Message msg = this.mCaller.obtainMessageII(30, width, height);
            this.mCaller.sendMessage(msg);
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void setDisplayPadding(Rect padding) {
            Message msg = this.mCaller.obtainMessageO(40, padding);
            this.mCaller.sendMessage(msg);
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void setVisibility(boolean visible) {
            Message msg = this.mCaller.obtainMessageI(10010, visible ? 1 : 0);
            this.mCaller.sendMessage(msg);
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void setInAmbientMode(boolean inAmbientDisplay, long animationDuration) throws RemoteException {
            Message msg = this.mCaller.obtainMessageIO(50, inAmbientDisplay ? 1 : 0, Long.valueOf(animationDuration));
            this.mCaller.sendMessage(msg);
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void dispatchPointer(MotionEvent event) {
            Engine engine = this.mEngine;
            if (engine != null) {
                engine.dispatchPointer(event);
            } else {
                event.recycle();
            }
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void dispatchWallpaperCommand(String action, int x, int y, int z, Bundle extras) {
            Engine engine = this.mEngine;
            if (engine != null) {
                engine.mWindow.dispatchWallpaperCommand(action, x, y, z, extras, false);
            }
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void setZoomOut(float scale) {
            Message msg = this.mCaller.obtainMessageI(10100, Float.floatToIntBits(scale));
            this.mCaller.sendMessage(msg);
        }

        public void reportShown() {
            if (!this.mShownReported) {
                this.mShownReported = true;
                try {
                    this.mConnection.engineShown(this);
                } catch (RemoteException e) {
                    Log.w(WallpaperService.TAG, "Wallpaper host disappeared", e);
                }
            }
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void requestWallpaperColors() {
            Message msg = this.mCaller.obtainMessage(10050);
            this.mCaller.sendMessage(msg);
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void addLocalColorsAreas(List<RectF> regions) {
            this.mEngine.addLocalColorsAreas(regions);
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void removeLocalColorsAreas(List<RectF> regions) {
            this.mEngine.removeLocalColorsAreas(regions);
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void destroy() {
            Message msg = this.mCaller.obtainMessage(20);
            this.mCaller.sendMessage(msg);
        }

        public void detach() {
            this.mDetached.set(true);
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public void scalePreview(Rect position) {
            Message msg = this.mCaller.obtainMessageO(10110, position);
            this.mCaller.sendMessage(msg);
        }

        @Override // android.service.wallpaper.IWallpaperEngine
        public SurfaceControl mirrorSurfaceControl() {
            Engine engine = this.mEngine;
            if (engine == null) {
                return null;
            }
            return SurfaceControl.mirrorSurface(engine.mSurfaceControl);
        }

        private void doDetachEngine() {
            WallpaperService.this.mActiveEngines.remove(this.mEngine);
            this.mEngine.detach();
            this.mWallpaperServiceHelper.removeEngine(this.mEngine);
            if (!this.mDetached.get()) {
                Iterator it = WallpaperService.this.mActiveEngines.iterator();
                while (it.hasNext()) {
                    Engine eng = (Engine) it.next();
                    if (eng.mVisible) {
                        eng.doVisibilityChanged(false);
                        eng.doVisibilityChanged(true);
                    }
                }
            }
        }

        @Override // com.android.internal.os.HandlerCaller.Callback
        public void executeMessage(Message message) {
            if (!this.mDetached.get()) {
                boolean z = false;
                switch (message.what) {
                    case 10:
                        Engine engine = WallpaperService.this.onCreateEngine();
                        this.mEngine = engine;
                        Trace.traceBegin(8L, "IWallpaperEngineWrapper.DO_ATTACH");
                        try {
                            this.mConnection.attachEngine(this, this.mDisplayId);
                            WallpaperService.this.mActiveEngines.add(engine);
                            this.mWallpaperServiceHelper.addEngine(engine);
                            Trace.traceBegin(8L, "WallpaperService.Engine.attach");
                            engine.attach(this);
                            Trace.traceEnd(8L);
                            return;
                        } catch (RemoteException e) {
                            engine.detach();
                            Log.w(WallpaperService.TAG, "Wallpaper host disappeared", e);
                            return;
                        }
                    case 20:
                        doDetachEngine();
                        return;
                    case 30:
                        this.mEngine.doDesiredSizeChanged(message.arg1, message.arg2);
                        return;
                    case 40:
                        this.mEngine.doDisplayPaddingChanged((Rect) message.obj);
                        return;
                    case 50:
                        Engine engine2 = this.mEngine;
                        if (message.arg1 != 0) {
                            z = true;
                        }
                        engine2.doAmbientModeChanged(z, ((Long) message.obj).longValue());
                        return;
                    case 10000:
                        this.mEngine.updateSurface(true, false, false);
                        return;
                    case 10010:
                        if (WallpaperService.DEBUG) {
                            Log.v(WallpaperService.TAG, "Visibility change in " + this.mEngine + ": " + message.arg1);
                        }
                        Engine engine3 = this.mEngine;
                        if (message.arg1 != 0) {
                            z = true;
                        }
                        engine3.doVisibilityChanged(z);
                        return;
                    case 10020:
                        this.mEngine.doOffsetsChanged(true);
                        return;
                    case 10025:
                        WallpaperCommand cmd = (WallpaperCommand) message.obj;
                        this.mEngine.doCommand(cmd);
                        return;
                    case 10030:
                        boolean reportDraw = message.arg1 != 0;
                        this.mEngine.updateSurface(true, false, reportDraw);
                        this.mEngine.doOffsetsChanged(true);
                        return;
                    case 10035:
                        return;
                    case 10040:
                        boolean skip = false;
                        MotionEvent ev = (MotionEvent) message.obj;
                        if (ev.getAction() == 2) {
                            synchronized (this.mEngine.mLock) {
                                if (this.mEngine.mPendingMove == ev) {
                                    this.mEngine.mPendingMove = null;
                                } else {
                                    skip = true;
                                }
                            }
                        }
                        if (!skip) {
                            if (WallpaperService.DEBUG) {
                                Log.v(WallpaperService.TAG, "Delivering touch event: " + ev);
                            }
                            this.mEngine.onTouchEvent(ev);
                        }
                        ev.recycle();
                        return;
                    case 10050:
                        if (this.mConnection != null) {
                            try {
                                WallpaperColors colors = this.mEngine.onComputeColors();
                                this.mEngine.setPrimaryWallpaperColors(colors);
                                this.mConnection.onWallpaperColorsChanged(colors, this.mDisplayId);
                                return;
                            } catch (RemoteException e2) {
                                return;
                            }
                        } else {
                            return;
                        }
                    case 10100:
                        this.mEngine.setZoom(Float.intBitsToFloat(message.arg1));
                        return;
                    case 10110:
                        this.mEngine.scalePreview((Rect) message.obj);
                        return;
                    default:
                        Log.w(WallpaperService.TAG, "Unknown message type " + message.what);
                        return;
                }
            } else if (WallpaperService.this.mActiveEngines.contains(this.mEngine)) {
                doDetachEngine();
            }
        }
    }

    /* loaded from: classes3.dex */
    class IWallpaperServiceWrapper extends IWallpaperService.Stub {
        private IWallpaperEngineWrapper mEngineWrapper;
        private IWallpaperEngineWrapper mLastEngineWrapper;
        private final WallpaperService mTarget;

        public IWallpaperServiceWrapper(WallpaperService context) {
            this.mTarget = context;
        }

        @Override // android.service.wallpaper.IWallpaperService
        public void attach(IWallpaperConnection conn, IBinder windowToken, int windowType, boolean isPreview, int reqWidth, int reqHeight, Rect padding, int displayId) {
            this.mLastEngineWrapper = this.mEngineWrapper;
            this.mEngineWrapper = new IWallpaperEngineWrapper(this.mTarget, conn, windowToken, windowType, isPreview, reqWidth, reqHeight, padding, displayId);
        }

        @Override // android.service.wallpaper.IWallpaperService
        public void detach() {
            this.mEngineWrapper.detach();
            IWallpaperEngineWrapper iWallpaperEngineWrapper = this.mLastEngineWrapper;
            if (iWallpaperEngineWrapper != null) {
                this.mEngineWrapper = iWallpaperEngineWrapper;
                this.mLastEngineWrapper = null;
                WallpaperService.this.mOplusWallpaperServiceExt.setWallPaperEngine(this.mEngineWrapper.mEngine);
            }
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mOplusWallpaperServiceExt.setWallpaperService(this);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        for (int i = 0; i < this.mActiveEngines.size(); i++) {
            this.mActiveEngines.get(i).detach();
        }
        this.mActiveEngines.clear();
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        this.mOplusWallpaperServiceExt.onBind(intent);
        this.mOplusWallpaperServiceExt.setWallpaperService(this);
        return new IWallpaperServiceWrapper(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Service
    public void dump(FileDescriptor fd, PrintWriter out, String[] args) {
        out.print("State of wallpaper ");
        out.print(this);
        out.println(SettingsStringUtil.DELIMITER);
        for (int i = 0; i < this.mActiveEngines.size(); i++) {
            Engine engine = this.mActiveEngines.get(i);
            out.print("  Engine ");
            out.print(engine);
            out.println(SettingsStringUtil.DELIMITER);
            engine.dump("    ", fd, out, args);
        }
    }
}
