package android.view;

import android.Manifest;
import android.animation.LayoutTransition;
import android.app.ActivityManager;
import android.app.ResourcesManager;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.res.CompatibilityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.BLASTBufferQueue;
import android.graphics.Canvas;
import android.graphics.FrameInfo;
import android.graphics.HardwareRenderer;
import android.graphics.HardwareRendererObserver;
import android.graphics.Insets;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RenderNode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.hardware.display.DisplayManager;
import android.hardware.input.InputManager;
import android.media.AudioManager;
import android.media.TtmlUtils;
import android.media.audio.common.AudioFormat;
import android.net.TrafficStats;
import android.os.Binder;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.OplusJankMonitorExtPlugin;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.Trace;
import android.os.UserHandle;
import android.sysprop.DisplayProperties;
import android.util.AndroidRuntimeException;
import android.util.ArraySet;
import android.util.DisplayMetrics;
import android.util.EventLog;
import android.util.IndentingPrintWriter;
import android.util.Log;
import android.util.LongArray;
import android.util.MergedConfiguration;
import android.util.Slog;
import android.util.SparseArray;
import android.util.TypedValue;
import android.util.imetracing.ImeTracing;
import android.util.proto.ProtoOutputStream;
import android.view.ActionMode;
import android.view.Choreographer;
import android.view.IWindow;
import android.view.InputDevice;
import android.view.InputQueue;
import android.view.KeyCharacterMap;
import android.view.ScrollCaptureResponse;
import android.view.Surface;
import android.view.SurfaceControl;
import android.view.SurfaceHolder;
import android.view.ThreadedRenderer;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeIdManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.accessibility.IAccessibilityEmbeddedConnection;
import android.view.accessibility.IAccessibilityInteractionConnection;
import android.view.accessibility.IAccessibilityInteractionConnectionCallback;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillManager;
import android.view.contentcapture.ContentCaptureManager;
import android.view.contentcapture.ContentCaptureSession;
import android.view.contentcapture.MainContentCaptureSession;
import android.view.inputmethod.InputMethodManager;
import android.widget.Scroller;
import android.window.ClientWindowFrames;
import com.android.internal.R;
import com.android.internal.graphics.drawable.BackgroundBlurDrawable;
import com.android.internal.inputmethod.InputMethodDebug;
import com.android.internal.os.IResultReceiver;
import com.android.internal.os.SomeArgs;
import com.android.internal.policy.DecorView;
import com.android.internal.policy.PhoneFallbackEventHandler;
import com.android.internal.util.Preconditions;
import com.android.internal.view.BaseSurfaceHolder;
import com.android.internal.view.RootViewSurfaceTaker;
import com.android.internal.view.SurfaceCallbackHelper;
import com.mediatek.boostfwk.BoostFwkFactory;
import com.mediatek.boostfwk.scenario.scroll.ScrollScenario;
import com.mediatek.view.MsyncExt;
import com.mediatek.view.MsyncFactory;
import com.mediatek.view.SurfaceExt;
import com.mediatek.view.SurfaceFactory;
import com.mediatek.view.ViewDebugManager;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;
import system.ext.loader.core.ExtLoader;
/* loaded from: classes3.dex */
public final class ViewRootImpl implements ViewParent, View.AttachInfo.Callbacks, ThreadedRenderer.DrawCallbacks, AttachedSurfaceControl {
    private static final int CONTENT_CAPTURE_ENABLED_FALSE = 2;
    private static final int CONTENT_CAPTURE_ENABLED_NOT_CHECKED = 0;
    private static final int CONTENT_CAPTURE_ENABLED_TRUE = 1;
    private static final boolean ENABLE_INPUT_LATENCY_TRACKING = false;
    private static final int MAX_QUEUED_INPUT_EVENT_POOL_SIZE = 10;
    static final int MAX_TRACKBALL_DELAY = 250;
    private static final int MSG_CHECK_FOCUS = 13;
    private static final int MSG_CLEAR_ACCESSIBILITY_FOCUS_HOST = 21;
    private static final int MSG_CLOSE_SYSTEM_DIALOGS = 14;
    private static final int MSG_DIE = 3;
    private static final int MSG_DISPATCH_APP_VISIBILITY = 8;
    private static final int MSG_DISPATCH_DRAG_EVENT = 15;
    private static final int MSG_DISPATCH_DRAG_LOCATION_EVENT = 16;
    private static final int MSG_DISPATCH_GET_NEW_SURFACE = 9;
    private static final int MSG_DISPATCH_INPUT_EVENT = 7;
    private static final int MSG_DISPATCH_KEY_FROM_AUTOFILL = 12;
    private static final int MSG_DISPATCH_KEY_FROM_IME = 11;
    private static final int MSG_DISPATCH_SYSTEM_UI_VISIBILITY = 17;
    private static final int MSG_DISPATCH_WINDOW_SHOWN = 25;
    private static final int MSG_DRAW_FINISHED = 29;
    private static final int MSG_HIDE_INSETS = 35;
    private static final int MSG_INSETS_CHANGED = 30;
    private static final int MSG_INSETS_CONTROL_CHANGED = 31;
    private static final int MSG_INVALIDATE = 1;
    private static final int MSG_INVALIDATE_RECT = 2;
    private static final int MSG_INVALIDATE_WORLD = 22;
    private static final int MSG_LOCATION_IN_PARENT_DISPLAY_CHANGED = 33;
    private static final int MSG_POINTER_CAPTURE_CHANGED = 28;
    private static final int MSG_PROCESS_INPUT_EVENTS = 19;
    private static final int MSG_REQUEST_KEYBOARD_SHORTCUTS = 26;
    private static final int MSG_REQUEST_SCROLL_CAPTURE = 36;
    private static final int MSG_RESIZED = 4;
    private static final int MSG_RESIZED_REPORT = 5;
    private static final int MSG_SHOW_INSETS = 34;
    private static final int MSG_SYNTHESIZE_INPUT_EVENT = 24;
    private static final int MSG_SYSTEM_GESTURE_EXCLUSION_CHANGED = 32;
    private static final int MSG_UPDATE_CONFIGURATION = 18;
    private static final int MSG_UPDATE_POINTER_ICON = 27;
    private static final int MSG_WINDOW_FOCUS_CHANGED = 6;
    private static final int MSG_WINDOW_MOVED = 23;
    private static final boolean MT_RENDERER_AVAILABLE = true;
    private static final String PROPERTY_PROFILE_RENDERING = "viewroot.profile_rendering";
    private static final int SCROLL_CAPTURE_REQUEST_TIMEOUT_MILLIS = 2500;
    private static final String TAG = "ViewRootImpl";
    private static boolean sAlwaysAssignFocus;
    private boolean isFirstMoveEvent;
    private IAccessibilityEmbeddedConnection mAccessibilityEmbeddedConnection;
    View mAccessibilityFocusedHost;
    AccessibilityNodeInfo mAccessibilityFocusedVirtualView;
    final AccessibilityInteractionConnectionManager mAccessibilityInteractionConnectionManager;
    AccessibilityInteractionController mAccessibilityInteractionController;
    final AccessibilityManager mAccessibilityManager;
    private ActivityConfigCallback mActivityConfigCallback;
    private boolean mActivityRelaunched;
    boolean mAdded;
    boolean mAddedTouchMode;
    private boolean mAppVisibilityChanged;
    boolean mAppVisible;
    boolean mApplyInsetsRequested;
    final View.AttachInfo mAttachInfo;
    AudioManager mAudioManager;
    final String mBasePackageName;
    private BLASTBufferQueue mBlastBufferQueue;
    private final BackgroundBlurDrawable.Aggregator mBlurRegionAggregator;
    private SurfaceControl mBoundsLayer;
    private int mCanvasOffsetX;
    private int mCanvasOffsetY;
    final Choreographer mChoreographer;
    int mClientWindowLayoutFlags;
    final SystemUiVisibilityInfo mCompatibleVisibilityInfo;
    final ConsumeBatchedInputImmediatelyRunnable mConsumeBatchedInputImmediatelyRunnable;
    boolean mConsumeBatchedInputImmediatelyScheduled;
    boolean mConsumeBatchedInputScheduled;
    final ConsumeBatchedInputRunnable mConsumedBatchedInputRunnable;
    int mContentCaptureEnabled;
    public final Context mContext;
    int mCurScrollY;
    View mCurrentDragView;
    private PointerIcon mCustomPointerIcon;
    private final int mDensity;
    private Rect mDirty;
    int mDispatchedSystemBarAppearance;
    int mDispatchedSystemUiVisibility;
    Display mDisplay;
    private final DisplayManager.DisplayListener mDisplayListener;
    final DisplayManager mDisplayManager;
    ClipDescription mDragDescription;
    final PointF mDragPoint;
    private boolean mDragResizing;
    boolean mDrawingAllowed;
    int mDrawsNeededToReport;
    FallbackEventHandler mFallbackEventHandler;
    private boolean mFastScrollSoundEffectsEnabled;
    boolean mFirst;
    InputStage mFirstInputStage;
    InputStage mFirstPostImeInputStage;
    private boolean mForceDecorViewVisibility;
    private boolean mForceDisableBLAST;
    private boolean mForceNextConfigUpdate;
    boolean mForceNextWindowRelayout;
    private int mFpsNumFrames;
    private long mFpsPrevTime;
    private long mFpsStartTime;
    public int mFrame;
    boolean mFullRedrawNeeded;
    private final GestureExclusionTracker mGestureExclusionTracker;
    boolean mHadWindowFocus;
    final ViewRootHandler mHandler;
    boolean mHandlingLayoutInLayoutRequest;
    HardwareRendererObserver mHardwareRendererObserver;
    int mHardwareXOffset;
    int mHardwareYOffset;
    int mHeight;
    final HighContrastTextManager mHighContrastTextManager;
    public long mIdent;
    private final ImeFocusController mImeFocusController;
    private boolean mInLayout;
    private final InputEventCompatProcessor mInputCompatProcessor;
    private final InputEventAssigner mInputEventAssigner;
    protected final InputEventConsistencyVerifier mInputEventConsistencyVerifier;
    WindowInputEventReceiver mInputEventReceiver;
    IInputEventReceiverExt mInputEventReceiverExt;
    InputQueue mInputQueue;
    InputQueue.Callback mInputQueueCallback;
    private final InsetsController mInsetsController;
    final InvalidateOnAnimationRunnable mInvalidateOnAnimationRunnable;
    private boolean mInvalidateRootRequested;
    boolean mIsAmbientMode;
    public boolean mIsAnimating;
    boolean mIsCreating;
    boolean mIsDrawing;
    private boolean mIsForWebviewOverlay;
    boolean mIsInTraversal;
    private boolean mIsSurfaceOpaque;
    private boolean mIsSurfaceViewCreated;
    private final Configuration mLastConfigurationFromResources;
    final ViewTreeObserver.InternalInsetsInfo mLastGivenInsets;
    boolean mLastInCompatMode;
    private final MergedConfiguration mLastReportedMergedConfiguration;
    WeakReference<View> mLastScrolledFocus;
    private final Point mLastSurfaceSize;
    int mLastSystemUiVisibility;
    final PointF mLastTouchPoint;
    int mLastTouchSource;
    private WindowInsets mLastWindowInsets;
    boolean mLayoutRequested;
    ArrayList<View> mLayoutRequesters;
    final IBinder mLeashToken;
    volatile Object mLocalDragState;
    final WindowLeaked mLocation;
    boolean mLostWindowFocus;
    private MsyncExt mMsyncExt;
    private boolean mNeedsRendererSetup;
    boolean mNewSurfaceNeeded;
    private boolean mNextDrawUseBlastSync;
    private final int mNoncompatDensity;
    int mOrigWindowType;
    boolean mPausedForTransition;
    boolean mPendingAlwaysConsumeSystemBars;
    final Rect mPendingBackDropFrame;
    int mPendingInputEventCount;
    QueuedInputEvent mPendingInputEventHead;
    String mPendingInputEventQueueLengthCounterName;
    QueuedInputEvent mPendingInputEventTail;
    private final MergedConfiguration mPendingMergedConfiguration;
    private ArrayList<LayoutTransition> mPendingTransitions;
    boolean mPerformContentCapture;
    boolean mPointerCapture;
    private int mPointerIconType;
    final Region mPreviousTransparentRegion;
    boolean mProcessInputEventsScheduled;
    private boolean mProfile;
    private boolean mProfileRendering;
    private QueuedInputEvent mQueuedInputEventPool;
    private int mQueuedInputEventPoolSize;
    private boolean mRemoved;
    private Choreographer.FrameCallback mRenderProfiler;
    private boolean mRenderProfilingEnabled;
    boolean mReportNextDraw;
    private boolean mRequestedTraverseWhilePaused;
    private int mResizeMode;
    private HashSet<ScrollCaptureCallback> mRootScrollCaptureCallbacks;
    private final SurfaceControl.Transaction mRtBLASTSyncTransaction;
    private long mRtLastAttemptedDrawFrameNum;
    private long mScrollCaptureRequestTimeout;
    boolean mScrollMayChange;
    int mScrollY;
    Scroller mScroller;
    SendWindowContentChangedAccessibilityEvent mSendWindowContentChangedAccessibilityEvent;
    int mSoftInputMode;
    boolean mStopped;
    public final Surface mSurface;
    private final ArrayList<SurfaceChangedCallback> mSurfaceChangedCallbacks;
    private final SurfaceControl.Transaction mSurfaceChangedTransaction;
    private final SurfaceControl mSurfaceControl;
    private SurfaceExt mSurfaceExt;
    BaseSurfaceHolder mSurfaceHolder;
    SurfaceHolder.Callback2 mSurfaceHolderCallback;
    private int mSurfaceSequenceId;
    private final SurfaceSession mSurfaceSession;
    private final Point mSurfaceSize;
    InputStage mSyntheticInputStage;
    private String mTag;
    final int mTargetSdkVersion;
    private final Rect mTempBoundsRect;
    private final InsetsSourceControl[] mTempControls;
    HashSet<View> mTempHashSet;
    private final InsetsState mTempInsets;
    final Rect mTempRect;
    final Thread mThread;
    private final ClientWindowFrames mTmpFrames;
    final int[] mTmpLocation;
    final TypedValue mTmpValue;
    private final SurfaceControl.Transaction mTransaction;
    CompatibilityInfo.Translator mTranslator;
    final Region mTransparentRegion;
    int mTraversalBarrier;
    final TraversalRunnable mTraversalRunnable;
    public boolean mTraversalScheduled;
    private int mTypesHiddenByFlags;
    boolean mUnbufferedInputDispatch;
    int mUnbufferedInputSource;
    private final UnhandledKeyManager mUnhandledKeyManager;
    boolean mUpcomingInTouchMode;
    boolean mUpcomingWindowFocus;
    private boolean mUseBLASTAdapter;
    private boolean mUseMTRenderer;
    View mView;
    final ViewConfiguration mViewConfiguration;
    protected final ViewFrameInfo mViewFrameInfo;
    private int mViewLayoutDirectionInitial;
    public IViewRootImplExt mViewRootImplExt;
    int mViewVisibility;
    final Rect mVisRect;
    private boolean mWaitForBlastSyncComplete;
    int mWidth;
    boolean mWillDrawSoon;
    private boolean mWillMove;
    private boolean mWillResize;
    final Rect mWinFrame;
    final W mWindow;
    public final WindowManager.LayoutParams mWindowAttributes;
    boolean mWindowAttributesChanged;
    final ArrayList<WindowCallbacks> mWindowCallbacks;
    CountDownLatch mWindowDrawCountDown;
    boolean mWindowFocusChanged;
    final IWindowSession mWindowSession;
    private boolean sUseMsync;
    public static boolean DBG = false;
    public static boolean LOCAL_LOGV = false;
    public static boolean DEBUG_DRAW = false;
    public static boolean DEBUG_LAYOUT = false;
    public static boolean DEBUG_DIALOG = false;
    public static boolean DEBUG_INPUT_RESIZE = false;
    public static boolean DEBUG_ORIENTATION = false;
    public static boolean DEBUG_TRACKBALL = false;
    public static boolean DEBUG_IMF = false;
    public static boolean DEBUG_CONFIGURATION = false;
    public static boolean DEBUG_FPS = false;
    public static boolean DEBUG_INPUT_STAGES = false;
    public static boolean DEBUG_KEEP_SCREEN_ON = false;
    private static final boolean DEBUG_CONTENT_CAPTURE = false;
    private static final boolean DEBUG_SCROLL_CAPTURE = false;
    private static final boolean DEBUG_BLAST = false;
    private static final boolean sOsieNeedNoCompress = SystemProperties.getBoolean("debug.sys.osie.neednocompress", false);
    static final ThreadLocal<HandlerActionQueue> sRunQueues = new ThreadLocal<>();
    static final ArrayList<Runnable> sFirstDrawHandlers = new ArrayList<>();
    static boolean sFirstDrawComplete = false;
    private static final ArrayList<ConfigChangedCallback> sConfigCallbacks = new ArrayList<>();
    private static boolean sCompatibilityDone = false;
    static final Interpolator mResizeInterpolator = new AccelerateDecelerateInterpolator();

    /* loaded from: classes3.dex */
    public interface ActivityConfigCallback {
        void onConfigurationChanged(Configuration configuration, int i);
    }

    /* loaded from: classes3.dex */
    public interface ConfigChangedCallback {
        void onConfigurationChanged(Configuration configuration);
    }

    /* loaded from: classes3.dex */
    public interface SurfaceChangedCallback {
        void surfaceCreated(SurfaceControl.Transaction transaction);

        void surfaceDestroyed();

        void surfaceReplaced(SurfaceControl.Transaction transaction);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setSurfaceViewCreated(boolean created) {
        this.mIsSurfaceViewCreated = created;
        if (created && this.mSurfaceExt == null) {
            this.mSurfaceExt = SurfaceFactory.getInstance().getSurfaceExt();
        }
        Log.d(TAG, "setSurfaceViewCreated, created:" + created);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FrameInfo getUpdatedFrameInfo() {
        FrameInfo frameInfo = this.mChoreographer.mFrameInfo;
        long flag = this.mChoreographer.mChoreographerExt.populateAndResetFrameInfo(frameInfo.opFrameInfo);
        if (flag != 0) {
            this.mViewFrameInfo.addFlags(flag);
        }
        this.mViewFrameInfo.populateFrameInfo(frameInfo);
        this.mViewFrameInfo.reset();
        this.mInputEventAssigner.notifyFrameProcessed();
        return frameInfo;
    }

    public ImeFocusController getImeFocusController() {
        return this.mImeFocusController;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class SystemUiVisibilityInfo {
        int globalVisibility;
        int localChanges;
        int localValue;

        SystemUiVisibilityInfo() {
        }
    }

    public ViewRootImpl(Context context, Display display) {
        this(context, display, WindowManagerGlobal.getWindowSession(), false);
    }

    public ViewRootImpl(Context context, Display display, IWindowSession session) {
        this(context, display, session, false);
    }

    public ViewRootImpl(Context context, Display display, IWindowSession session, boolean useSfChoreographer) {
        InputEventConsistencyVerifier inputEventConsistencyVerifier;
        InputEventCompatProcessor compatProcessor;
        this.mSurfaceExt = null;
        boolean z = false;
        this.mIsSurfaceViewCreated = false;
        this.mWindowCallbacks = new ArrayList<>();
        this.mTmpLocation = new int[2];
        this.mTmpValue = new TypedValue();
        this.mWindowAttributes = new WindowManager.LayoutParams();
        this.mAppVisible = true;
        this.mForceDecorViewVisibility = false;
        this.mOrigWindowType = -1;
        this.mStopped = false;
        this.mIsAmbientMode = false;
        this.mPausedForTransition = false;
        this.mLastInCompatMode = false;
        this.mViewFrameInfo = new ViewFrameInfo();
        this.mInputEventAssigner = new InputEventAssigner();
        this.mSurfaceSize = new Point();
        this.mLastSurfaceSize = new Point();
        this.mTempBoundsRect = new Rect();
        this.mContentCaptureEnabled = 0;
        this.mUnbufferedInputSource = 0;
        this.mPendingInputEventQueueLengthCounterName = "pq";
        this.mUnhandledKeyManager = new UnhandledKeyManager();
        this.mWindowAttributesChanged = false;
        this.mSurface = new Surface();
        this.mSurfaceControl = new SurfaceControl();
        this.mSurfaceChangedTransaction = new SurfaceControl.Transaction();
        this.mSurfaceSession = new SurfaceSession();
        this.mTransaction = new SurfaceControl.Transaction();
        this.mTmpFrames = new ClientWindowFrames();
        this.mPendingBackDropFrame = new Rect();
        this.mTempInsets = new InsetsState();
        this.mTempControls = new InsetsSourceControl[22];
        this.mLastGivenInsets = new ViewTreeObserver.InternalInsetsInfo();
        this.mTypesHiddenByFlags = 0;
        this.mLastConfigurationFromResources = new Configuration();
        this.mLastReportedMergedConfiguration = new MergedConfiguration();
        this.mPendingMergedConfiguration = new MergedConfiguration();
        this.mDragPoint = new PointF();
        this.mLastTouchPoint = new PointF();
        this.mFpsStartTime = -1L;
        this.mFpsPrevTime = -1L;
        this.mPointerIconType = 1;
        this.mCustomPointerIcon = null;
        AccessibilityInteractionConnectionManager accessibilityInteractionConnectionManager = new AccessibilityInteractionConnectionManager();
        this.mAccessibilityInteractionConnectionManager = accessibilityInteractionConnectionManager;
        this.mInLayout = false;
        this.mLayoutRequesters = new ArrayList<>();
        this.mHandlingLayoutInLayoutRequest = false;
        this.mViewRootImplExt = (IViewRootImplExt) ExtLoader.type(IViewRootImplExt.class).base(this).create();
        this.mMsyncExt = MsyncFactory.getInstance().getMsyncExt();
        if (InputEventConsistencyVerifier.isInstrumentationEnabled()) {
            inputEventConsistencyVerifier = new InputEventConsistencyVerifier(this, 0);
        } else {
            inputEventConsistencyVerifier = null;
        }
        this.mInputEventConsistencyVerifier = inputEventConsistencyVerifier;
        this.mBlurRegionAggregator = new BackgroundBlurDrawable.Aggregator(this);
        this.sUseMsync = false;
        this.isFirstMoveEvent = false;
        this.mGestureExclusionTracker = new GestureExclusionTracker();
        this.mRtBLASTSyncTransaction = new SurfaceControl.Transaction();
        this.mNextDrawUseBlastSync = false;
        this.mWaitForBlastSyncComplete = false;
        this.mRtLastAttemptedDrawFrameNum = 0L;
        this.mRequestedTraverseWhilePaused = false;
        this.mScrollCaptureRequestTimeout = 2500L;
        this.mSurfaceSequenceId = 0;
        this.mTag = TAG;
        this.mProfile = false;
        this.mDisplayListener = new DisplayManager.DisplayListener() { // from class: android.view.ViewRootImpl.1
            @Override // android.hardware.display.DisplayManager.DisplayListener
            public void onDisplayChanged(int displayId) {
                int oldDisplayState;
                int newDisplayState;
                if (ViewRootImpl.this.mView != null && ViewRootImpl.this.mDisplay.getDisplayId() == displayId && (oldDisplayState = ViewRootImpl.this.mAttachInfo.mDisplayState) != (newDisplayState = ViewRootImpl.this.mDisplay.getState())) {
                    ViewRootImpl.this.mAttachInfo.mDisplayState = newDisplayState;
                    ViewRootImpl.this.pokeDrawLockIfNeeded();
                    if (oldDisplayState != 0) {
                        int oldScreenState = toViewScreenState(oldDisplayState);
                        int newScreenState = toViewScreenState(newDisplayState);
                        if (oldScreenState != newScreenState) {
                            ViewRootImpl.this.mView.dispatchScreenStateChanged(newScreenState);
                        }
                        if (oldDisplayState == 1) {
                            ViewRootImpl.this.mFullRedrawNeeded = true;
                            ViewRootImpl.this.scheduleTraversals();
                        }
                    }
                }
            }

            @Override // android.hardware.display.DisplayManager.DisplayListener
            public void onDisplayRemoved(int displayId) {
            }

            @Override // android.hardware.display.DisplayManager.DisplayListener
            public void onDisplayAdded(int displayId) {
            }

            private int toViewScreenState(int displayState) {
                if (displayState == 1) {
                    return 0;
                }
                return 1;
            }
        };
        this.mSurfaceChangedCallbacks = new ArrayList<>();
        this.mDrawsNeededToReport = 0;
        ViewRootHandler viewRootHandler = new ViewRootHandler();
        this.mHandler = viewRootHandler;
        this.mTraversalRunnable = new TraversalRunnable();
        this.mConsumedBatchedInputRunnable = new ConsumeBatchedInputRunnable();
        this.mConsumeBatchedInputImmediatelyRunnable = new ConsumeBatchedInputImmediatelyRunnable();
        this.mInvalidateOnAnimationRunnable = new InvalidateOnAnimationRunnable();
        this.mFrame = 0;
        this.mContext = context;
        this.mWindowSession = session;
        this.mViewRootImplExt.init(this, context);
        this.mDisplay = display;
        this.mBasePackageName = context.getBasePackageName();
        this.mThread = Thread.currentThread();
        WindowLeaked windowLeaked = new WindowLeaked(null);
        this.mLocation = windowLeaked;
        windowLeaked.fillInStackTrace();
        this.mWidth = -1;
        this.mHeight = -1;
        this.mDirty = new Rect();
        this.mTempRect = new Rect();
        this.mVisRect = new Rect();
        this.mWinFrame = new Rect();
        W createWindowClient = this.mViewRootImplExt.createWindowClient();
        this.mWindow = createWindowClient;
        this.mLeashToken = new Binder();
        this.mTargetSdkVersion = context.getApplicationInfo().targetSdkVersion;
        this.mViewVisibility = 8;
        this.mTransparentRegion = new Region();
        this.mPreviousTransparentRegion = new Region();
        this.mFirst = true;
        this.mPerformContentCapture = true;
        this.mAdded = false;
        this.mAttachInfo = new View.AttachInfo(session, createWindowClient, display, this, viewRootHandler, this, context);
        this.mCompatibleVisibilityInfo = new SystemUiVisibilityInfo();
        AccessibilityManager instance = AccessibilityManager.getInstance(context);
        this.mAccessibilityManager = instance;
        instance.addAccessibilityStateChangeListener(accessibilityInteractionConnectionManager, viewRootHandler);
        HighContrastTextManager highContrastTextManager = new HighContrastTextManager();
        this.mHighContrastTextManager = highContrastTextManager;
        instance.addHighTextContrastStateChangeListener(highContrastTextManager, viewRootHandler);
        this.mViewConfiguration = ViewConfiguration.get(context);
        this.mDensity = context.getResources().getDisplayMetrics().densityDpi;
        this.mNoncompatDensity = context.getResources().getDisplayMetrics().noncompatDensityDpi;
        this.mFallbackEventHandler = new PhoneFallbackEventHandler(context);
        this.mChoreographer = useSfChoreographer ? Choreographer.getSfInstance() : Choreographer.getInstance();
        this.mViewRootImplExt.initScrollOpt(context);
        this.mDisplayManager = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
        this.mInsetsController = new InsetsController(new ViewRootInsetsControllerHost(this));
        String processorOverrideName = context.getResources().getString(R.string.config_inputEventCompatProcessorOverrideClassName);
        if (processorOverrideName.isEmpty()) {
            compatProcessor = new InputEventCompatProcessor(context);
        } else {
            compatProcessor = null;
            try {
                compatProcessor = (InputEventCompatProcessor) Class.forName(processorOverrideName).getConstructor(Context.class).newInstance(context);
            } catch (Exception e) {
                Log.e(TAG, "Unable to create the InputEventCompatProcessor. ", e);
            } finally {
                this.mInputCompatProcessor = compatProcessor;
            }
        }
        if (!sCompatibilityDone) {
            sAlwaysAssignFocus = this.mTargetSdkVersion < 28 ? true : z;
            sCompatibilityDone = true;
        }
        loadSystemProperties();
        this.mImeFocusController = new ImeFocusController(this);
        this.mViewRootImplExt.initSystemUINavigationGesture(this, context);
        this.mViewRootImplExt.hookNewInstance(this.mContext);
        AudioManager audioManager = (AudioManager) this.mContext.getSystemService(AudioManager.class);
        this.mFastScrollSoundEffectsEnabled = audioManager.areNavigationRepeatSoundEffectsEnabled();
        this.mScrollCaptureRequestTimeout = 2500L;
        ViewDebugManager.getInstance().debugViewRootConstruct(this.mTag, context, this.mThread, this.mChoreographer, this.mTraversalRunnable, this);
        String packageName = this.mContext.getPackageName();
        String msyncPackageName = ViewDebugManager.msyncPackageName;
        if (ViewDebugManager.DEBUG_INPUT) {
            Log.d(TAG, "msyncPackageName = " + msyncPackageName + " packageName = " + packageName + " sUseMsync=" + msyncPackageName.equals(packageName));
        }
        if (msyncPackageName.equals(packageName) || this.mMsyncExt.isOpenMsyncPackage(packageName)) {
            this.mChoreographer.postUseMsync(true);
            this.sUseMsync = true;
        }
    }

    public static void addFirstDrawHandler(Runnable callback) {
        ArrayList<Runnable> arrayList = sFirstDrawHandlers;
        synchronized (arrayList) {
            if (!sFirstDrawComplete) {
                arrayList.add(callback);
            }
        }
    }

    public static void addConfigCallback(ConfigChangedCallback callback) {
        ArrayList<ConfigChangedCallback> arrayList = sConfigCallbacks;
        synchronized (arrayList) {
            arrayList.add(callback);
        }
    }

    public void setActivityConfigCallback(ActivityConfigCallback callback) {
        this.mActivityConfigCallback = callback;
    }

    public void setOnContentApplyWindowInsetsListener(Window.OnContentApplyWindowInsetsListener listener) {
        this.mAttachInfo.mContentOnApplyWindowInsetsListener = listener;
        if (!this.mFirst) {
            requestFitSystemWindows();
        }
    }

    public void addWindowCallbacks(WindowCallbacks callback) {
        synchronized (this.mWindowCallbacks) {
            this.mWindowCallbacks.add(callback);
        }
    }

    public void removeWindowCallbacks(WindowCallbacks callback) {
        synchronized (this.mWindowCallbacks) {
            this.mWindowCallbacks.remove(callback);
        }
    }

    public void reportDrawFinish() {
        CountDownLatch countDownLatch = this.mWindowDrawCountDown;
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    public void profile() {
        this.mProfile = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isInTouchMode() {
        IWindowSession windowSession = WindowManagerGlobal.peekWindowSession();
        if (windowSession == null) {
            return false;
        }
        try {
            return windowSession.getInTouchMode();
        } catch (RemoteException e) {
            return false;
        }
    }

    public void notifyChildRebuilt() {
        if (this.mView instanceof RootViewSurfaceTaker) {
            SurfaceHolder.Callback2 callback2 = this.mSurfaceHolderCallback;
            if (callback2 != null) {
                this.mSurfaceHolder.removeCallback(callback2);
            }
            SurfaceHolder.Callback2 willYouTakeTheSurface = ((RootViewSurfaceTaker) this.mView).willYouTakeTheSurface();
            this.mSurfaceHolderCallback = willYouTakeTheSurface;
            if (willYouTakeTheSurface != null) {
                TakenSurfaceHolder takenSurfaceHolder = new TakenSurfaceHolder();
                this.mSurfaceHolder = takenSurfaceHolder;
                takenSurfaceHolder.setFormat(0);
                this.mSurfaceHolder.addCallback(this.mSurfaceHolderCallback);
            } else {
                this.mSurfaceHolder = null;
            }
            InputQueue.Callback willYouTakeTheInputQueue = ((RootViewSurfaceTaker) this.mView).willYouTakeTheInputQueue();
            this.mInputQueueCallback = willYouTakeTheInputQueue;
            if (willYouTakeTheInputQueue != null) {
                willYouTakeTheInputQueue.onInputQueueCreated(this.mInputQueue);
            }
        }
    }

    public static void computeWindowBounds(WindowManager.LayoutParams attrs, InsetsState state, Rect displayFrame, Rect outBounds) {
        int typesToFit = attrs.getFitInsetsTypes();
        int sidesToFit = attrs.getFitInsetsSides();
        ArraySet<Integer> types = InsetsState.toInternalType(typesToFit);
        int bottom = 0;
        Insets insets = Insets.of(0, 0, 0, 0);
        for (int i = types.size() - 1; i >= 0; i--) {
            InsetsSource source = state.peekSource(types.valueAt(i).intValue());
            if (source != null) {
                insets = Insets.max(insets, source.calculateInsets(displayFrame, attrs.isFitInsetsIgnoringVisibility()));
            }
        }
        int i2 = sidesToFit & 1;
        int left = i2 != 0 ? insets.left : 0;
        int top = (sidesToFit & 2) != 0 ? insets.top : 0;
        int right = (sidesToFit & 4) != 0 ? insets.right : 0;
        if ((sidesToFit & 8) != 0) {
            bottom = insets.bottom;
        }
        outBounds.set(displayFrame.left + left, displayFrame.top + top, displayFrame.right - right, displayFrame.bottom - bottom);
    }

    private Configuration getConfiguration() {
        return this.mContext.getResources().getConfiguration();
    }

    public void setView(View view, WindowManager.LayoutParams attrs, View panelParentView) {
        setView(view, attrs, panelParentView, UserHandle.myUserId());
    }

    /* JADX WARN: Removed duplicated region for block: B:158:0x050a A[Catch: all -> 0x051a, TRY_ENTER, TryCatch #6 {all -> 0x051a, blocks: (B:13:0x0051, B:15:0x005f, B:17:0x0065, B:19:0x006b, B:20:0x0073, B:22:0x0080, B:24:0x008b, B:25:0x009c, B:27:0x00a1, B:28:0x00a4, B:30:0x00b9, B:34:0x00c5, B:36:0x00c9, B:37:0x00ce, B:39:0x00d3, B:41:0x00e4, B:43:0x00e8, B:45:0x00ec, B:46:0x011c, B:48:0x0122, B:49:0x012a, B:53:0x013d, B:56:0x0148, B:57:0x014a, B:59:0x014e, B:60:0x0156, B:62:0x0164, B:64:0x016d, B:68:0x0178, B:70:0x0180, B:72:0x0188, B:83:0x01ea, B:84:0x01ed, B:88:0x01f6, B:90:0x0230, B:91:0x0248, B:93:0x024c, B:95:0x0266, B:96:0x0294, B:97:0x0297, B:98:0x029b, B:99:0x02b8, B:100:0x02b9, B:101:0x02d6, B:102:0x02d7, B:103:0x02f4, B:104:0x02f5, B:105:0x0312, B:106:0x0313, B:108:0x0315, B:109:0x033e, B:110:0x033f, B:111:0x0363, B:112:0x0364, B:113:0x0381, B:114:0x0382, B:115:0x03ab, B:116:0x03ac, B:117:0x03c9, B:118:0x03ca, B:119:0x03de, B:120:0x03df, B:122:0x03e3, B:123:0x03e6, B:125:0x03ea, B:128:0x03f7, B:130:0x03fb, B:131:0x0407, B:132:0x0423, B:136:0x042d, B:139:0x0434, B:141:0x043e, B:142:0x0443, B:144:0x0449, B:145:0x044d, B:158:0x050a, B:159:0x050e, B:163:0x0513, B:167:0x0518), top: B:174:0x0005 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setView(android.view.View r21, android.view.WindowManager.LayoutParams r22, android.view.View r23, int r24) {
        /*
            Method dump skipped, instructions count: 1334
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.ViewRootImpl.setView(android.view.View, android.view.WindowManager$LayoutParams, android.view.View, int):void");
    }

    private void setTag() {
        String[] split = this.mWindowAttributes.getTitle().toString().split("\\.");
        if (split.length > 0) {
            this.mTag = "ViewRootImpl[" + split[split.length - 1] + "]";
        }
    }

    public int getWindowFlags() {
        return this.mWindowAttributes.flags;
    }

    public int getDisplayId() {
        return this.mDisplay.getDisplayId();
    }

    public CharSequence getTitle() {
        return this.mWindowAttributes.getTitle();
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void destroyHardwareResources() {
        ThreadedRenderer renderer = this.mAttachInfo.mThreadedRenderer;
        if (renderer == null) {
            return;
        }
        if (Looper.myLooper() != this.mAttachInfo.mHandler.getLooper()) {
            this.mAttachInfo.mHandler.postAtFrontOfQueue(new Runnable() { // from class: android.view.ViewRootImpl$$ExternalSyntheticLambda10
                @Override // java.lang.Runnable
                public final void run() {
                    ViewRootImpl.this.destroyHardwareResources();
                }
            });
            return;
        }
        renderer.destroyHardwareResources(this.mView);
        renderer.destroy();
    }

    public void detachFunctor(long functor) {
    }

    public static void invokeFunctor(long functor, boolean waitForCompletion) {
    }

    public void registerAnimatingRenderNode(RenderNode animator) {
        if (this.mAttachInfo.mThreadedRenderer != null) {
            this.mAttachInfo.mThreadedRenderer.registerAnimatingRenderNode(animator);
            return;
        }
        if (this.mAttachInfo.mPendingAnimatingRenderNodes == null) {
            this.mAttachInfo.mPendingAnimatingRenderNodes = new ArrayList();
        }
        this.mAttachInfo.mPendingAnimatingRenderNodes.add(animator);
    }

    public void registerVectorDrawableAnimator(NativeVectorDrawableAnimator animator) {
        if (this.mAttachInfo.mThreadedRenderer != null) {
            this.mAttachInfo.mThreadedRenderer.registerVectorDrawableAnimator(animator);
        }
    }

    public void registerRtFrameCallback(final HardwareRenderer.FrameDrawingCallback callback) {
        if (this.mAttachInfo.mThreadedRenderer != null) {
            this.mAttachInfo.mThreadedRenderer.registerRtFrameCallback(new HardwareRenderer.FrameDrawingCallback() { // from class: android.view.ViewRootImpl$$ExternalSyntheticLambda4
                @Override // android.graphics.HardwareRenderer.FrameDrawingCallback
                public final void onFrameDraw(long j) {
                    ViewRootImpl.lambda$registerRtFrameCallback$0(HardwareRenderer.FrameDrawingCallback.this, j);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$registerRtFrameCallback$0(HardwareRenderer.FrameDrawingCallback callback, long frame) {
        try {
            callback.onFrameDraw(frame);
        } catch (Exception e) {
            Log.e(TAG, "Exception while executing onFrameDraw", e);
        }
    }

    private void addASurfaceTransactionCallback() {
        HardwareRenderer.ASurfaceTransactionCallback callback = new HardwareRenderer.ASurfaceTransactionCallback() { // from class: android.view.ViewRootImpl$$ExternalSyntheticLambda1
            @Override // android.graphics.HardwareRenderer.ASurfaceTransactionCallback
            public final boolean onMergeTransaction(long j, long j2, long j3) {
                return ViewRootImpl.this.lambda$addASurfaceTransactionCallback$1$ViewRootImpl(j, j2, j3);
            }
        };
        this.mAttachInfo.mThreadedRenderer.setASurfaceTransactionCallback(callback);
    }

    public /* synthetic */ boolean lambda$addASurfaceTransactionCallback$1$ViewRootImpl(long nativeTransactionObj, long nativeSurfaceControlObj, long frameNr) {
        BLASTBufferQueue bLASTBufferQueue = this.mBlastBufferQueue;
        if (bLASTBufferQueue == null) {
            return false;
        }
        bLASTBufferQueue.mergeWithNextTransaction(nativeTransactionObj, frameNr);
        return true;
    }

    private void addPrepareSurfaceControlForWebviewCallback() {
        HardwareRenderer.PrepareSurfaceControlForWebviewCallback callback = new HardwareRenderer.PrepareSurfaceControlForWebviewCallback() { // from class: android.view.ViewRootImpl$$ExternalSyntheticLambda7
            @Override // android.graphics.HardwareRenderer.PrepareSurfaceControlForWebviewCallback
            public final void prepare() {
                ViewRootImpl.this.lambda$addPrepareSurfaceControlForWebviewCallback$2$ViewRootImpl();
            }
        };
        this.mAttachInfo.mThreadedRenderer.setPrepareSurfaceControlForWebviewCallback(callback);
    }

    public /* synthetic */ void lambda$addPrepareSurfaceControlForWebviewCallback$2$ViewRootImpl() {
        if (!this.mIsForWebviewOverlay) {
            synchronized (this) {
                this.mIsForWebviewOverlay = true;
            }
            this.mTransaction.setOpaque(this.mSurfaceControl, false).apply();
        }
    }

    private void enableHardwareAcceleration(WindowManager.LayoutParams attrs) {
        boolean translucent = false;
        this.mAttachInfo.mHardwareAccelerated = false;
        this.mAttachInfo.mHardwareAccelerationRequested = false;
        if (this.mTranslator == null) {
            boolean hardwareAccelerated = (attrs.flags & 16777216) != 0;
            if (ViewDebugManager.getInstance().debugForceHWDraw(hardwareAccelerated)) {
                boolean forceHwAccelerated = (attrs.privateFlags & 2) != 0;
                boolean fakeHwAccelerated = (attrs.privateFlags & 1) != 0;
                if (fakeHwAccelerated) {
                    this.mAttachInfo.mHardwareAccelerationRequested = true;
                } else if (ThreadedRenderer.sRendererEnabled || forceHwAccelerated) {
                    if (this.mAttachInfo.mThreadedRenderer != null) {
                        this.mAttachInfo.mThreadedRenderer.destroy();
                    }
                    Rect insets = attrs.surfaceInsets;
                    boolean hasSurfaceInsets = (insets.left == 0 && insets.right == 0 && insets.top == 0 && insets.bottom == 0) ? false : true;
                    if (attrs.format != -1 || hasSurfaceInsets) {
                        translucent = true;
                    }
                    this.mAttachInfo.mThreadedRenderer = ThreadedRenderer.create(this.mContext, translucent, attrs.getTitle().toString());
                    updateColorModeIfNeeded(attrs.getColorMode());
                    updateForceDarkMode();
                    if (this.mAttachInfo.mThreadedRenderer != null) {
                        View.AttachInfo attachInfo = this.mAttachInfo;
                        attachInfo.mHardwareAccelerationRequested = true;
                        attachInfo.mHardwareAccelerated = true;
                        if (this.mHardwareRendererObserver != null) {
                            this.mAttachInfo.mThreadedRenderer.addObserver(this.mHardwareRendererObserver);
                        }
                        if (HardwareRenderer.isWebViewOverlaysEnabled()) {
                            addPrepareSurfaceControlForWebviewCallback();
                            addASurfaceTransactionCallback();
                        }
                        this.mAttachInfo.mThreadedRenderer.setSurfaceControl(this.mSurfaceControl);
                    }
                }
                boolean translucent2 = ViewDebugManager.DEBUG_USER;
                if (translucent2) {
                    Log.d(this.mTag, "hardware acceleration = " + this.mAttachInfo.mHardwareAccelerated + ", sRendererEnabled = " + ThreadedRenderer.sRendererEnabled + ", forceHwAccelerated = " + forceHwAccelerated);
                }
            }
        }
    }

    private int getNightMode() {
        return getConfiguration().uiMode & 48;
    }

    private void updateForceDarkMode() {
        if (this.mAttachInfo.mThreadedRenderer != null) {
            boolean useAutoDark = true;
            useAutoDark = getNightMode() == 32;
            this.mViewRootImplExt.logConfigurationNightError(this.mContext, useAutoDark);
            if (useAutoDark) {
                boolean forceDarkAllowedDefault = SystemProperties.getBoolean(ThreadedRenderer.DEBUG_FORCE_DARK, false);
                TypedArray a = this.mContext.obtainStyledAttributes(R.styleable.Theme);
                if (!a.getBoolean(279, true) || !a.getBoolean(278, forceDarkAllowedDefault)) {
                    useAutoDark = false;
                }
                a.recycle();
                this.mViewRootImplExt.logForceDarkAllowedStatus(this.mContext, forceDarkAllowedDefault);
            }
            this.mViewRootImplExt.forceDarkWithoutTheme(this.mContext, this.mView, useAutoDark);
            if (this.mAttachInfo.mThreadedRenderer.setForceDark(useAutoDark)) {
                invalidateWorld(this.mView);
            }
        }
    }

    public View getView() {
        return this.mView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final WindowLeaked getLocation() {
        return this.mLocation;
    }

    public void setLayoutParams(WindowManager.LayoutParams attrs, boolean newView) {
        int oldInsetLeft;
        synchronized (this) {
            int oldInsetLeft2 = this.mWindowAttributes.surfaceInsets.left;
            int oldInsetTop = this.mWindowAttributes.surfaceInsets.top;
            int oldInsetRight = this.mWindowAttributes.surfaceInsets.right;
            int oldInsetBottom = this.mWindowAttributes.surfaceInsets.bottom;
            int oldSoftInputMode = this.mWindowAttributes.softInputMode;
            boolean oldHasManualSurfaceInsets = this.mWindowAttributes.hasManualSurfaceInsets;
            if (DEBUG_KEEP_SCREEN_ON && (this.mClientWindowLayoutFlags & 128) != 0 && (attrs.flags & 128) == 0) {
                Slog.d(this.mTag, "setLayoutParams: FLAG_KEEP_SCREEN_ON from true to false!");
            }
            this.mClientWindowLayoutFlags = attrs.flags;
            int compatibleWindowFlag = this.mWindowAttributes.privateFlags & 128;
            int systemUiVisibility = this.mWindowAttributes.systemUiVisibility;
            int subtreeSystemUiVisibility = this.mWindowAttributes.subtreeSystemUiVisibility;
            int appearance = this.mWindowAttributes.insetsFlags.appearance;
            int behavior = this.mWindowAttributes.insetsFlags.behavior;
            int appearanceAndBehaviorPrivateFlags = this.mWindowAttributes.privateFlags & AudioFormat.DTS_HD;
            int changes = this.mWindowAttributes.copyFrom(attrs);
            if ((524288 & changes) != 0) {
                this.mAttachInfo.mRecomputeGlobalAttributes = true;
            }
            if ((changes & 1) != 0) {
                this.mAttachInfo.mNeedsUpdateLightCenter = true;
            }
            if (this.mWindowAttributes.packageName == null) {
                this.mWindowAttributes.packageName = this.mBasePackageName;
            }
            this.mWindowAttributes.systemUiVisibility = systemUiVisibility;
            this.mWindowAttributes.subtreeSystemUiVisibility = subtreeSystemUiVisibility;
            this.mWindowAttributes.insetsFlags.appearance = appearance;
            this.mWindowAttributes.insetsFlags.behavior = behavior;
            this.mWindowAttributes.privateFlags |= compatibleWindowFlag | appearanceAndBehaviorPrivateFlags | 33554432;
            if (this.mWindowAttributes.preservePreviousSurfaceInsets) {
                this.mWindowAttributes.surfaceInsets.set(oldInsetLeft2, oldInsetTop, oldInsetRight, oldInsetBottom);
                this.mWindowAttributes.hasManualSurfaceInsets = oldHasManualSurfaceInsets;
            } else if (!(this.mWindowAttributes.surfaceInsets.left == oldInsetLeft2 && this.mWindowAttributes.surfaceInsets.top == oldInsetTop && this.mWindowAttributes.surfaceInsets.right == oldInsetRight && this.mWindowAttributes.surfaceInsets.bottom == oldInsetBottom)) {
                this.mNeedsRendererSetup = true;
            }
            applyKeepScreenOnFlag(this.mWindowAttributes);
            if (newView) {
                this.mSoftInputMode = attrs.softInputMode;
                requestLayout();
            }
            if ((attrs.softInputMode & 240) == 0) {
                WindowManager.LayoutParams layoutParams = this.mWindowAttributes;
                oldInsetLeft = oldSoftInputMode;
                layoutParams.softInputMode = (oldInsetLeft & 240) | (layoutParams.softInputMode & TrafficStats.TAG_SYSTEM_IMPERSONATION_RANGE_END);
            } else {
                oldInsetLeft = oldSoftInputMode;
            }
            if (this.mWindowAttributes.softInputMode != oldInsetLeft) {
                requestFitSystemWindows();
            }
            this.mWindowAttributesChanged = true;
            this.mViewRootImplExt.setScrollToTopRootView(this.mView, this.mWindowAttributes);
            scheduleTraversals();
        }
        if (DEBUG_IMF) {
            Log.d(this.mTag, "setLayoutParams: attrs = " + attrs + ", mSoftInputMode = " + this.mSoftInputMode + ", mWindowAttributes = " + this.mWindowAttributes + ", this = " + this);
        }
    }

    void handleAppVisibility(boolean visible) {
        if (DEBUG_LAYOUT) {
            String str = this.mTag;
            Log.d(str, "handleAppVisibility: visible=" + visible + ", mAppVisible=" + this.mAppVisible + ", this = " + this);
        }
        if (this.mAppVisible != visible) {
            this.mAppVisible = visible;
            this.mAppVisibilityChanged = true;
            scheduleTraversals();
            if (!this.mAppVisible) {
                WindowManagerGlobal.trimForeground();
            }
        }
    }

    void handleGetNewSurface() {
        this.mNewSurfaceNeeded = true;
        this.mFullRedrawNeeded = true;
        scheduleTraversals();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleResized(int msg, SomeArgs args) {
        if (this.mAdded) {
            ClientWindowFrames frames = (ClientWindowFrames) args.arg1;
            MergedConfiguration mergedConfiguration = (MergedConfiguration) args.arg2;
            boolean z = false;
            boolean forceNextWindowRelayout = args.argi1 != 0;
            int displayId = args.argi3;
            Rect backdropFrame = frames.backdropFrame;
            boolean frameChanged = !this.mWinFrame.equals(frames.frame);
            boolean backdropFrameChanged = !this.mPendingBackDropFrame.equals(backdropFrame);
            boolean configChanged = !this.mLastReportedMergedConfiguration.equals(mergedConfiguration);
            boolean displayChanged = this.mDisplay.getDisplayId() != displayId;
            if (msg != 4 || frameChanged || backdropFrameChanged || configChanged || displayChanged || forceNextWindowRelayout) {
                if (configChanged) {
                    performConfigurationChange(mergedConfiguration, false, displayChanged ? displayId : -1);
                } else if (displayChanged) {
                    onMovedToDisplay(displayId, this.mLastConfigurationFromResources);
                }
                setFrame(frames.frame);
                this.mTmpFrames.displayFrame.set(frames.displayFrame);
                this.mPendingBackDropFrame.set(backdropFrame);
                this.mForceNextWindowRelayout = forceNextWindowRelayout;
                if (args.argi2 != 0) {
                    z = true;
                }
                this.mPendingAlwaysConsumeSystemBars = z;
                if (msg == 5 && !this.mNextDrawUseBlastSync) {
                    reportNextDraw();
                }
                View view = this.mView;
                if (view != null && (frameChanged || configChanged)) {
                    forceLayout(view);
                }
                requestLayout();
            }
        }
    }

    public void onMovedToDisplay(int displayId, Configuration config) {
        if (this.mDisplay.getDisplayId() != displayId) {
            updateInternalDisplay(displayId, this.mView.getResources());
            this.mImeFocusController.onMovedToDisplay();
            this.mAttachInfo.mDisplayState = this.mDisplay.getState();
            this.mView.dispatchMovedToDisplay(this.mDisplay, config);
        }
    }

    private void updateInternalDisplay(int displayId, Resources resources) {
        Display preferredDisplay = ResourcesManager.getInstance().getAdjustedDisplay(displayId, resources);
        if (preferredDisplay == null) {
            Slog.w(TAG, "Cannot get desired display with Id: " + displayId);
            this.mDisplay = ResourcesManager.getInstance().getAdjustedDisplay(0, resources);
        } else {
            this.mDisplay = preferredDisplay;
        }
        this.mContext.updateDisplay(this.mDisplay.getDisplayId());
    }

    void pokeDrawLockIfNeeded() {
        int displayState = this.mAttachInfo.mDisplayState;
        if (this.mView != null && this.mAdded && this.mTraversalScheduled) {
            if (displayState == 3 || displayState == 4) {
                try {
                    this.mWindowSession.pokeDrawLock(this.mWindow);
                } catch (RemoteException e) {
                }
            }
        }
    }

    @Override // android.view.ViewParent
    public void requestFitSystemWindows() {
        checkThread();
        this.mApplyInsetsRequested = true;
        scheduleTraversals();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyInsetsChanged() {
        this.mApplyInsetsRequested = true;
        if (!this.mWillMove && !this.mWillResize) {
            requestLayout();
            if (View.sForceLayoutWhenInsetsChanged && this.mView != null && (this.mWindowAttributes.softInputMode & 240) == 16 && !this.mViewRootImplExt.disableRelayout()) {
                forceLayout(this.mView);
            }
            if (!this.mIsInTraversal) {
                scheduleTraversals();
            }
        }
    }

    @Override // android.view.ViewParent
    public void requestLayout() {
        if (!this.mHandlingLayoutInLayoutRequest) {
            if (ViewDebugManager.DEBUG_REQUESTLAYOUT) {
                String str = this.mTag;
                Log.d(str, "requestLayout: mView = " + this.mView + ", this = " + this, new Throwable("requestLayout"));
            }
            checkThread();
            this.mLayoutRequested = true;
            scheduleTraversals();
        }
    }

    @Override // android.view.ViewParent
    public boolean isLayoutRequested() {
        return this.mLayoutRequested;
    }

    @Override // android.view.ViewParent
    public void onDescendantInvalidated(View child, View descendant) {
        if ((descendant.mPrivateFlags & 64) != 0) {
            this.mIsAnimating = true;
        }
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void invalidate() {
        this.mDirty.set(0, 0, this.mWidth, this.mHeight);
        if (!this.mWillDrawSoon) {
            scheduleTraversals();
        }
    }

    void invalidateWorld(View view) {
        view.invalidate();
        if (view instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) view;
            for (int i = 0; i < parent.getChildCount(); i++) {
                invalidateWorld(parent.getChildAt(i));
            }
        }
    }

    @Override // android.view.ViewParent
    public void invalidateChild(View child, Rect dirty) {
        invalidateChildInParent(null, dirty);
    }

    @Override // android.view.ViewParent
    public ViewParent invalidateChildInParent(int[] location, Rect dirty) {
        checkThread();
        if (DEBUG_DRAW) {
            String str = this.mTag;
            Log.v(str, "Invalidate child: " + dirty);
        }
        if (dirty == null) {
            invalidate();
            return null;
        } else if (dirty.isEmpty() && !this.mIsAnimating) {
            return null;
        } else {
            if (!(this.mCurScrollY == 0 && this.mTranslator == null)) {
                this.mTempRect.set(dirty);
                dirty = this.mTempRect;
                int i = this.mCurScrollY;
                if (i != 0) {
                    dirty.offset(0, -i);
                }
                CompatibilityInfo.Translator translator = this.mTranslator;
                if (translator != null) {
                    translator.translateRectInAppWindowToScreen(dirty);
                }
                if (this.mAttachInfo.mScalingRequired) {
                    dirty.inset(-1, -1);
                }
            }
            invalidateRectOnScreen(dirty);
            return null;
        }
    }

    private void invalidateRectOnScreen(Rect dirty) {
        Rect localDirty = this.mDirty;
        localDirty.union(dirty.left, dirty.top, dirty.right, dirty.bottom);
        float appScale = this.mAttachInfo.mApplicationScale;
        boolean intersected = localDirty.intersect(0, 0, (int) ((this.mWidth * appScale) + 0.5f), (int) ((this.mHeight * appScale) + 0.5f));
        if (!intersected) {
            localDirty.setEmpty();
        }
        if (!this.mWillDrawSoon && (intersected || this.mIsAnimating)) {
            scheduleTraversals();
        } else if (DEBUG_DRAW) {
            String str = this.mTag;
            Log.v(str, "Invalidate child: Do not scheduleTraversals, mWillDrawSoon =" + this.mWillDrawSoon + ", intersected =" + intersected + ", mIsAnimating =" + this.mIsAnimating);
        }
    }

    public void setIsAmbientMode(boolean ambient) {
        this.mIsAmbientMode = ambient;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setWindowStopped(boolean stopped) {
        checkThread();
        if (this.mStopped != stopped) {
            this.mStopped = stopped;
            ThreadedRenderer renderer = this.mAttachInfo.mThreadedRenderer;
            if (renderer != null) {
                if (DEBUG_DRAW) {
                    String str = this.mTag;
                    Log.d(str, "WindowStopped on " + ((Object) getTitle()) + " set to " + this.mStopped);
                }
                renderer.setStopped(this.mStopped);
            }
            if (!this.mStopped) {
                this.mNewSurfaceNeeded = true;
                scheduleTraversals();
            } else {
                if (renderer != null) {
                    renderer.destroyHardwareResources(this.mView);
                }
                if (this.mSurface.isValid()) {
                    if (this.mSurfaceHolder != null) {
                        notifyHolderSurfaceDestroyed();
                    }
                    notifySurfaceDestroyed();
                }
                destroySurface();
            }
            if (this.mWaitForBlastSyncComplete || this.mDrawsNeededToReport != 0) {
                String str2 = this.mTag;
                Log.e(str2, "setWindowStopped mStopped=" + this.mStopped + ",wait blast:" + this.mWaitForBlastSyncComplete + "," + this.mDrawsNeededToReport);
            }
        }
    }

    public void addSurfaceChangedCallback(SurfaceChangedCallback c) {
        this.mSurfaceChangedCallbacks.add(c);
    }

    public void removeSurfaceChangedCallback(SurfaceChangedCallback c) {
        this.mSurfaceChangedCallbacks.remove(c);
    }

    private void notifySurfaceCreated() {
        this.mViewRootImplExt.updateLogLevel();
        for (int i = 0; i < this.mSurfaceChangedCallbacks.size(); i++) {
            this.mSurfaceChangedCallbacks.get(i).surfaceCreated(this.mSurfaceChangedTransaction);
        }
    }

    private void notifySurfaceReplaced() {
        for (int i = 0; i < this.mSurfaceChangedCallbacks.size(); i++) {
            this.mSurfaceChangedCallbacks.get(i).surfaceReplaced(this.mSurfaceChangedTransaction);
        }
    }

    private void notifySurfaceDestroyed() {
        for (int i = 0; i < this.mSurfaceChangedCallbacks.size(); i++) {
            this.mSurfaceChangedCallbacks.get(i).surfaceDestroyed();
        }
    }

    public SurfaceControl getBoundsLayer() {
        if (this.mBoundsLayer == null) {
            SurfaceControl.Builder containerLayer = new SurfaceControl.Builder(this.mSurfaceSession).setContainerLayer();
            this.mBoundsLayer = containerLayer.setName("Bounds for - " + getTitle().toString()).setParent(getSurfaceControl()).setCallsite("ViewRootImpl.getBoundsLayer").build();
            setBoundsLayerCrop(this.mTransaction);
            this.mTransaction.show(this.mBoundsLayer).apply();
        }
        return this.mBoundsLayer;
    }

    Surface getOrCreateBLASTSurface() {
        if (!this.mSurfaceControl.isValid()) {
            return null;
        }
        BLASTBufferQueue bLASTBufferQueue = this.mBlastBufferQueue;
        if (bLASTBufferQueue == null) {
            String layer_name = this.mTag;
            if (sOsieNeedNoCompress) {
                layer_name = "ViewRootImpl[" + this.mWindowAttributes.getTitle().toString() + "]";
            }
            BLASTBufferQueue bLASTBufferQueue2 = new BLASTBufferQueue(layer_name, this.mSurfaceControl, this.mSurfaceSize.x, this.mSurfaceSize.y, this.mWindowAttributes.format);
            this.mBlastBufferQueue = bLASTBufferQueue2;
            Surface ret = bLASTBufferQueue2.createSurface();
            return ret;
        }
        bLASTBufferQueue.update(this.mSurfaceControl, this.mSurfaceSize.x, this.mSurfaceSize.y, this.mWindowAttributes.format);
        return null;
    }

    private void setBoundsLayerCrop(SurfaceControl.Transaction t) {
        this.mTempBoundsRect.set(0, 0, this.mSurfaceSize.x, this.mSurfaceSize.y);
        this.mTempBoundsRect.inset(this.mWindowAttributes.surfaceInsets.left, this.mWindowAttributes.surfaceInsets.top, this.mWindowAttributes.surfaceInsets.right, this.mWindowAttributes.surfaceInsets.bottom);
        t.setWindowCrop(this.mBoundsLayer, this.mTempBoundsRect);
    }

    private boolean updateBoundsLayer(SurfaceControl.Transaction t) {
        if (this.mBoundsLayer == null) {
            return false;
        }
        setBoundsLayerCrop(t);
        return true;
    }

    private void prepareSurfaces() {
        SurfaceControl.Transaction t = this.mTransaction;
        SurfaceControl sc = getSurfaceControl();
        if (sc.isValid() && updateBoundsLayer(t)) {
            lambda$applyTransactionOnDraw$10$ViewRootImpl(t, this.mSurface.getNextFrameNumber());
        }
    }

    private void destroySurface() {
        SurfaceControl surfaceControl = this.mBoundsLayer;
        if (surfaceControl != null) {
            surfaceControl.release();
            this.mBoundsLayer = null;
        }
        this.mSurface.release();
        this.mSurfaceControl.release();
        BLASTBufferQueue bLASTBufferQueue = this.mBlastBufferQueue;
        if (bLASTBufferQueue != null) {
            bLASTBufferQueue.destroy();
            this.mBlastBufferQueue = null;
        }
        if (this.mAttachInfo.mThreadedRenderer != null) {
            this.mAttachInfo.mThreadedRenderer.setSurfaceControl(null);
        }
    }

    public void setPausedForTransition(boolean paused) {
        this.mPausedForTransition = paused;
    }

    @Override // android.view.ViewParent
    public ViewParent getParent() {
        return null;
    }

    @Override // android.view.ViewParent
    public boolean getChildVisibleRect(View child, Rect r, Point offset) {
        if (child == this.mView) {
            return r.intersect(0, 0, this.mWidth, this.mHeight);
        }
        throw new RuntimeException("child is not mine, honest!");
    }

    @Override // android.view.ViewParent
    public void bringChildToFront(View child) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getHostVisibility() {
        View view;
        if ((this.mAppVisible || this.mForceDecorViewVisibility) && (view = this.mView) != null) {
            return view.getVisibility();
        }
        return 8;
    }

    public void requestTransitionStart(LayoutTransition transition) {
        ArrayList<LayoutTransition> arrayList = this.mPendingTransitions;
        if (arrayList == null || !arrayList.contains(transition)) {
            if (this.mPendingTransitions == null) {
                this.mPendingTransitions = new ArrayList<>();
            }
            this.mPendingTransitions.add(transition);
        }
    }

    void notifyRendererOfFramePending() {
        if (this.mAttachInfo.mThreadedRenderer != null) {
            this.mAttachInfo.mThreadedRenderer.notifyFramePending();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void scheduleTraversals() {
        if (ViewDebugManager.DEBUG_SCHEDULETRAVERSALS) {
            Trace.traceBegin(8L, "scheduleTraversals In");
        }
        if (this.mViewRootImplExt.checkTraversalsImmediatelyProssible(this.mFirst)) {
            Slog.d(TAG, "checkTraversalsImmediatelyProssible");
            return;
        }
        if (!this.mTraversalScheduled) {
            Trace.traceBegin(8L, "scheduleTraversals");
            this.mTraversalScheduled = true;
            if (ViewDebugManager.DEBUG_SCHEDULETRAVERSALS) {
                Trace.traceBegin(8L, "scheduleTraversals occurred");
            }
            this.mTraversalBarrier = this.mHandler.getLooper().getQueue().postSyncBarrier();
            if (ViewDebugManager.DEBUG_SCHEDULETRAVERSALS) {
                String str = this.mTag;
                Log.v(str, "scheduleTraversals: mTraversalBarrier = " + this.mTraversalBarrier + ",this = " + this, new Throwable("scheduleTraversals"));
            }
            this.mChoreographer.postCallback(3, this.mTraversalRunnable, null);
            notifyRendererOfFramePending();
            if (ViewDebugManager.DEBUG_SCHEDULETRAVERSALS) {
                Trace.traceEnd(8L);
            }
            pokeDrawLockIfNeeded();
            Trace.traceEnd(8L);
        }
        if (ViewDebugManager.DEBUG_SCHEDULETRAVERSALS) {
            Trace.traceEnd(8L);
        }
    }

    void unscheduleTraversals() {
        if (this.mTraversalScheduled) {
            this.mTraversalScheduled = false;
            try {
                this.mHandler.getLooper().getQueue().removeSyncBarrier(this.mTraversalBarrier);
            } catch (IllegalStateException e) {
                Log.v(TAG, "The specified message queue synchronization  barrier token has not been posted or has already been removed");
            }
            this.mChoreographer.removeCallbacks(3, this.mTraversalRunnable, null);
        }
    }

    void doTraversal() {
        if (ViewDebugManager.DEBUG_LIFECYCLE || ViewDebugManager.DEBUG_ENG) {
            String str = this.mTag;
            Log.v(str, "doTraversal: mTraversalScheduled = " + this.mTraversalScheduled + " mFisrt = " + this.mFirst + ",mTraversalBarrier = " + this.mTraversalBarrier + ",this = " + this);
        }
        if (this.mTraversalScheduled) {
            this.mTraversalScheduled = false;
            try {
                this.mHandler.getLooper().getQueue().removeSyncBarrier(this.mTraversalBarrier);
            } catch (IllegalStateException e) {
                Log.v(TAG, "The specified message queue synchronization  barrier token has not been posted or has already been removed");
            }
            if (this.mProfile) {
                Debug.startMethodTracing("ViewAncestor");
            }
            if (ViewDebugManager.DEBUG_SCHEDULETRAVERSALS) {
                Trace.traceBegin(8L, "doTraversal");
            }
            performTraversals();
            if (ViewDebugManager.DEBUG_SCHEDULETRAVERSALS) {
                Trace.traceEnd(8L);
            }
            if (this.mProfile) {
                Debug.stopMethodTracing();
                this.mProfile = false;
            }
        }
    }

    private void applyKeepScreenOnFlag(WindowManager.LayoutParams params) {
        if (this.mAttachInfo.mKeepScreenOn) {
            params.flags |= 128;
        } else {
            params.flags = (params.flags & (-129)) | (this.mClientWindowLayoutFlags & 128);
        }
    }

    private boolean collectViewAttributes() {
        if (this.mAttachInfo.mRecomputeGlobalAttributes) {
            this.mAttachInfo.mRecomputeGlobalAttributes = false;
            boolean oldScreenOn = this.mAttachInfo.mKeepScreenOn;
            this.mAttachInfo.mKeepScreenOn = false;
            this.mAttachInfo.mSystemUiVisibility = 0;
            this.mAttachInfo.mHasSystemUiListeners = false;
            this.mView.dispatchCollectViewAttributes(this.mAttachInfo, 0);
            View.AttachInfo attachInfo = this.mAttachInfo;
            attachInfo.mSystemUiVisibility = this.mViewRootImplExt.changeSystemUiVisibility(attachInfo.mSystemUiVisibility);
            this.mAttachInfo.mSystemUiVisibility &= ~this.mAttachInfo.mDisabledSystemUiVisibility;
            WindowManager.LayoutParams params = this.mWindowAttributes;
            this.mAttachInfo.mSystemUiVisibility |= getImpliedSystemUiVisibility(params);
            SystemUiVisibilityInfo systemUiVisibilityInfo = this.mCompatibleVisibilityInfo;
            systemUiVisibilityInfo.globalVisibility = (systemUiVisibilityInfo.globalVisibility & (-2)) | (this.mAttachInfo.mSystemUiVisibility & 1);
            dispatchDispatchSystemUiVisibilityChanged(this.mCompatibleVisibilityInfo);
            if (!(this.mAttachInfo.mKeepScreenOn == oldScreenOn && this.mAttachInfo.mSystemUiVisibility == params.subtreeSystemUiVisibility && this.mAttachInfo.mHasSystemUiListeners == params.hasSystemUiListeners)) {
                applyKeepScreenOnFlag(params);
                params.subtreeSystemUiVisibility = this.mAttachInfo.mSystemUiVisibility;
                params.hasSystemUiListeners = this.mAttachInfo.mHasSystemUiListeners;
                this.mView.dispatchWindowSystemUiVisiblityChanged(this.mAttachInfo.mSystemUiVisibility);
                return true;
            }
        }
        return false;
    }

    private int getImpliedSystemUiVisibility(WindowManager.LayoutParams params) {
        int vis = 0;
        if ((params.flags & 67108864) != 0) {
            vis = 0 | 1280;
        }
        if ((params.flags & 134217728) != 0) {
            return vis | 768;
        }
        return vis;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateCompatSysUiVisibility(int type, boolean visible, boolean hasControl) {
        int systemUiFlag;
        boolean wasVisible = true;
        if (type == 0 || type == 1) {
            SystemUiVisibilityInfo info = this.mCompatibleVisibilityInfo;
            if (type == 0) {
                systemUiFlag = 4;
            } else {
                systemUiFlag = 2;
            }
            if ((info.globalVisibility & systemUiFlag) != 0) {
                wasVisible = false;
            }
            if (visible) {
                info.globalVisibility &= ~systemUiFlag;
                if (!wasVisible && hasControl) {
                    info.localChanges |= systemUiFlag;
                }
            } else {
                info.globalVisibility |= systemUiFlag;
                info.localChanges &= ~systemUiFlag;
            }
            dispatchDispatchSystemUiVisibilityChanged(info);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLowProfileModeIfNeeded(int showTypes, boolean fromIme) {
        SystemUiVisibilityInfo info = this.mCompatibleVisibilityInfo;
        if ((WindowInsets.Type.systemBars() & showTypes) != 0 && !fromIme && (info.globalVisibility & 1) != 0) {
            info.globalVisibility &= -2;
            info.localChanges |= 1;
            dispatchDispatchSystemUiVisibilityChanged(info);
        }
    }

    private void dispatchDispatchSystemUiVisibilityChanged(SystemUiVisibilityInfo args) {
        if (this.mDispatchedSystemUiVisibility != args.globalVisibility) {
            this.mHandler.removeMessages(17);
            ViewRootHandler viewRootHandler = this.mHandler;
            viewRootHandler.sendMessage(viewRootHandler.obtainMessage(17, args));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDispatchSystemUiVisibilityChanged(SystemUiVisibilityInfo args) {
        if (this.mView != null) {
            if (args.localChanges != 0) {
                this.mView.updateLocalSystemUiVisibility(args.localValue, args.localChanges);
                args.localChanges = 0;
            }
            int visibility = args.globalVisibility & 7;
            if (this.mDispatchedSystemUiVisibility != visibility) {
                this.mDispatchedSystemUiVisibility = visibility;
                this.mView.dispatchSystemUiVisibilityChanged(visibility);
            }
        }
    }

    public static void adjustLayoutParamsForCompatibility(WindowManager.LayoutParams inOutParams) {
        int sysUiVis = inOutParams.systemUiVisibility | inOutParams.subtreeSystemUiVisibility;
        int flags = inOutParams.flags;
        int type = inOutParams.type;
        int adjust = inOutParams.softInputMode & 240;
        if ((inOutParams.privateFlags & 67108864) == 0) {
            inOutParams.insetsFlags.appearance = 0;
            if ((sysUiVis & 1) != 0) {
                inOutParams.insetsFlags.appearance |= 4;
            }
            if ((sysUiVis & 8192) != 0) {
                inOutParams.insetsFlags.appearance |= 8;
            }
            if ((sysUiVis & 16) != 0) {
                inOutParams.insetsFlags.appearance |= 16;
            }
        }
        if ((inOutParams.privateFlags & 134217728) == 0) {
            if ((sysUiVis & 4096) == 0 && (flags & 1024) == 0) {
                inOutParams.insetsFlags.behavior = 1;
            } else {
                inOutParams.insetsFlags.behavior = 2;
            }
        }
        inOutParams.privateFlags &= -1073741825;
        if ((inOutParams.privateFlags & 268435456) == 0) {
            int types = inOutParams.getFitInsetsTypes();
            boolean ignoreVis = inOutParams.isFitInsetsIgnoringVisibility();
            if (!((sysUiVis & 1024) == 0 && (flags & 256) == 0 && (67108864 & flags) == 0)) {
                types &= ~WindowInsets.Type.statusBars();
            }
            if (!((sysUiVis & 512) == 0 && (flags & 134217728) == 0)) {
                types &= ~WindowInsets.Type.systemBars();
            }
            if (type == 2005 || type == 2003) {
                ignoreVis = true;
            } else if ((WindowInsets.Type.systemBars() & types) == WindowInsets.Type.systemBars()) {
                if (adjust == 16) {
                    types |= WindowInsets.Type.ime();
                } else {
                    inOutParams.privateFlags |= 1073741824;
                }
            }
            inOutParams.setFitInsetsTypes(types);
            inOutParams.setFitInsetsIgnoringVisibility(ignoreVis);
            inOutParams.privateFlags &= -268435457;
        }
    }

    private void controlInsetsForCompatibility(WindowManager.LayoutParams params) {
        int sysUiVis = params.systemUiVisibility | params.subtreeSystemUiVisibility;
        int flags = params.flags;
        boolean navIsHiddenByFlags = false;
        boolean matchParent = params.width == -1 && params.height == -1;
        boolean nonAttachedAppWindow = params.type >= 1 && params.type <= 99;
        boolean statusWasHiddenByFlags = (this.mTypesHiddenByFlags & WindowInsets.Type.statusBars()) != 0;
        boolean statusIsHiddenByFlags = (sysUiVis & 4) != 0 || ((flags & 1024) != 0 && matchParent && nonAttachedAppWindow);
        boolean navWasHiddenByFlags = (this.mTypesHiddenByFlags & WindowInsets.Type.navigationBars()) != 0;
        if ((sysUiVis & 2) != 0) {
            navIsHiddenByFlags = true;
        }
        int typesToHide = 0;
        int typesToShow = 0;
        if (statusIsHiddenByFlags && !statusWasHiddenByFlags) {
            typesToHide = 0 | WindowInsets.Type.statusBars();
        } else if (!statusIsHiddenByFlags && statusWasHiddenByFlags) {
            typesToShow = 0 | WindowInsets.Type.statusBars();
        }
        if (navIsHiddenByFlags && !navWasHiddenByFlags) {
            typesToHide |= WindowInsets.Type.navigationBars();
        } else if (!navIsHiddenByFlags && navWasHiddenByFlags) {
            typesToShow |= WindowInsets.Type.navigationBars();
        }
        if (typesToHide != 0) {
            getInsetsController().hide(typesToHide);
        }
        if (typesToShow != 0) {
            getInsetsController().show(typesToShow);
        }
        int i = this.mTypesHiddenByFlags | typesToHide;
        this.mTypesHiddenByFlags = i;
        this.mTypesHiddenByFlags = i & (~typesToShow);
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01ca  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean measureHierarchy(android.view.View r19, android.view.WindowManager.LayoutParams r20, android.content.res.Resources r21, int r22, int r23) {
        /*
            Method dump skipped, instructions count: 515
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.ViewRootImpl.measureHierarchy(android.view.View, android.view.WindowManager$LayoutParams, android.content.res.Resources, int, int):boolean");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void transformMatrixToGlobal(Matrix m) {
        m.preTranslate(this.mAttachInfo.mWindowLeft, this.mAttachInfo.mWindowTop);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void transformMatrixToLocal(Matrix m) {
        m.postTranslate(-this.mAttachInfo.mWindowLeft, -this.mAttachInfo.mWindowTop);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WindowInsets getWindowInsets(boolean forceConstruct) {
        if (this.mLastWindowInsets == null || forceConstruct) {
            Configuration config = getConfiguration();
            this.mLastWindowInsets = this.mInsetsController.calculateInsets(config.isScreenRound(), this.mAttachInfo.mAlwaysConsumeSystemBars, this.mWindowAttributes.type, config.windowConfiguration.getWindowingMode(), this.mWindowAttributes.softInputMode, this.mWindowAttributes.flags, this.mWindowAttributes.systemUiVisibility | this.mWindowAttributes.subtreeSystemUiVisibility);
            this.mAttachInfo.mContentInsets.set(this.mLastWindowInsets.getSystemWindowInsets().toRect());
            this.mAttachInfo.mStableInsets.set(this.mLastWindowInsets.getStableInsets().toRect());
            this.mAttachInfo.mVisibleInsets.set(this.mInsetsController.calculateVisibleInsets(this.mWindowAttributes.softInputMode));
        }
        return this.mLastWindowInsets;
    }

    public void dispatchApplyInsets(View host) {
        Trace.traceBegin(8L, "dispatchApplyInsets");
        this.mApplyInsetsRequested = false;
        WindowInsets insets = getWindowInsets(true);
        if (!shouldDispatchCutout()) {
            insets = insets.consumeDisplayCutout();
        }
        host.dispatchApplyWindowInsets(insets);
        this.mAttachInfo.delayNotifyContentCaptureInsetsEvent(insets.getInsets(WindowInsets.Type.all()));
        Trace.traceEnd(8L);
    }

    private boolean updateCaptionInsets() {
        View view = this.mView;
        if (!(view instanceof DecorView)) {
            return false;
        }
        int captionInsetsHeight = ((DecorView) view).getCaptionInsetsHeight();
        Rect captionFrame = new Rect();
        if (captionInsetsHeight != 0) {
            captionFrame.set(this.mWinFrame.left, this.mWinFrame.top, this.mWinFrame.right, this.mWinFrame.top + captionInsetsHeight);
        }
        if (this.mAttachInfo.mCaptionInsets.equals(captionFrame)) {
            return false;
        }
        this.mAttachInfo.mCaptionInsets.set(captionFrame);
        return true;
    }

    private boolean shouldDispatchCutout() {
        return this.mWindowAttributes.layoutInDisplayCutoutMode == 3 || this.mWindowAttributes.layoutInDisplayCutoutMode == 1;
    }

    public InsetsController getInsetsController() {
        return this.mInsetsController;
    }

    private static boolean shouldUseDisplaySize(WindowManager.LayoutParams lp) {
        return lp.type == 2041 || lp.type == 2011 || lp.type == 2020;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int dipToPx(int dip) {
        DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        return (int) ((displayMetrics.density * dip) + 0.5f);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(184:21|(1:30)(1:29)|31|(4:33|(1:35)(1:36)|(1:38)(1:39)|(176:41|43|(3:45|(1:47)(1:48)|49)(1:50)|51|(5:53|(1:55)(2:56|(1:61)(1:60))|62|(1:64)|65)(2:66|(3:70|(1:72)|73))|(3:75|(2:(1:78)(1:79)|80)|(1:84))|85|(1:87)|88|(1:95)(1:94)|96|(4:98|(1:100)(2:101|(3:106|(1:108)(1:109)|110))|105|110)(1:111)|112|(1:114)|115|(1:117)|118|(2:122|(6:124|(3:126|(2:128|748)(1:749)|129)|747|(1:131)|132|(1:134)))|135|(1:145)(2:141|(155:143|(1:148)|(1:172)(145:151|(1:170)(4:155|(2:157|(1:161))(1:162)|163|(2:165|(1:167))(1:169))|171|174|(1:179)(1:178)|180|(1:185)(1:184)|186|(1:188)(1:189)|190|(1:192)(1:193)|(4:195|(1:199)|200|(1:202))(1:203)|204|(1:213)(2:209|(1:211)(29:212|(1:543)|(1:549)(1:548)|550|(1:555)(1:554)|556|(1:558)(1:559)|560|(4:562|(4:564|(1:566)|567|(2:569|(1:573)))(1:574)|575|(1:577))(1:578)|(1:580)(1:(1:582)(1:(1:584)))|(1:586)|(2:588|(7:593|(1:595)(1:596)|742|597|598|740|599)(1:592))(1:604)|605|(3:607|(4:616|(1:618)|619|(2:621|(2:623|(1:625))(2:626|(1:628))))(2:611|(1:615))|629)|(1:634)(1:635)|636|(1:640)(1:639)|(1:645)(1:644)|(1:647)(1:(1:651))|(3:654|(1:656)(1:657)|(1:659))|660|(1:662)|663|(1:667)(1:666)|(3:669|(4:673|(2:676|674)|752|677)|678)(2:679|(2:681|(1:683))(3:684|(4:688|(2:691|689)|753|692)|(1:696)))|697|(1:699)|700|701))|214|(1:216)(1:217)|218|(1:220)|221|727|222|223|(8:732|225|226|715|227|228|719|229)(1:236)|237|745|238|(2:713|240)|243|(1:247)|248|(1:251)|252|253|(1:255)(1:256)|257|(1:259)(1:260)|261|(1:265)(1:264)|266|(5:736|268|(1:270)|271|(1:273))|709|276|(1:278)|279|(5:717|281|282|725|283)(1:286)|287|729|288|(1:290)|293|(6:295|(1:297)|298|(1:300)(1:301)|302|303)|304|305|(2:307|308)|309|(1:311)(1:312)|313|(1:318)(1:317)|319|(1:324)(1:323)|325|326|(1:332)(1:333)|334|(1:336)|(2:338|339)|340|(1:342)|(2:346|(59:348|708|(4:354|355|731|(5:706|357|358|(3:734|360|(1:364))|366))(1:(8:376|(1:378)|379|(1:381)|382|(1:384)|385|(1:387))(1:(3:397|738|398)))|402|744|(1:(11:(1:406)(1:407)|408|(1:413)(1:412)|414|(1:416)(1:417)|418|419|723|420|421|422)(1:425))(1:426)|427|(1:(1:430)(1:431))|432|446|(1:450)|451|(1:455)|456|(1:491)(6:458|(1:460)|461|(2:463|(3:465|(1:467)|750))|(3:475|(4:477|(1:479)|751|480)(1:481)|482)|(4:485|721|486|487))|492|(1:503)|504|(4:507|(1:509)(1:510)|511|(47:520|(1:522)(1:523)|524|(1:526)|527|(1:529)|(1:536)(3:531|(1:533)(1:534)|535)|537|538|(0)|543|(1:545)|549|550|(1:552)|555|556|(0)(0)|560|(0)(0)|(0)(0)|(0)|(0)(0)|605|(0)|(1:631)|634|636|(0)|640|(1:642)|645|(0)(0)|(0)|654|(0)(0)|(0)|660|(0)|663|(0)|667|(0)(0)|697|(0)|700|701))|519|538|(0)|543|(0)|549|550|(0)|555|556|(0)(0)|560|(0)(0)|(0)(0)|(0)|(0)(0)|605|(0)|(0)|634|636|(0)|640|(0)|645|(0)(0)|(0)|654|(0)(0)|(0)|660|(0)|663|(0)|667|(0)(0)|697|(0)|700|701))|349|350|711|351|352|708|(0)(0)|402|744|(0)(0)|427|(0)|432|446|(1:448)|450|451|(1:453)|455|456|(0)(0)|492|(2:494|503)|504|(0)|507|(0)(0)|511|(1:513)|520|(0)(0)|524|(0)|527|(0)|(0)(0)|537|538|(0)|543|(0)|549|550|(0)|555|556|(0)(0)|560|(0)(0)|(0)(0)|(0)|(0)(0)|605|(0)|(0)|634|636|(0)|640|(0)|645|(0)(0)|(0)|654|(0)(0)|(0)|660|(0)|663|(0)|667|(0)(0)|697|(0)|700|701)|173|174|(1:176)|179|180|(1:182)|185|186|(0)(0)|190|(0)(0)|(0)(0)|204|(0)|213|214|(0)(0)|218|(0)|221|727|222|223|(0)(0)|237|745|238|(0)|243|(2:245|247)|248|(0)|251|252|253|(0)(0)|257|(0)(0)|261|(0)|265|266|(0)|709|276|(0)|279|(0)(0)|287|729|288|(0)|293|(0)|304|305|(0)|309|(0)(0)|313|(1:315)|318|319|(1:321)|324|325|326|(1:328)|330|332|334|(0)|(0)|340|(0)|(3:344|346|(0))|349|350|711|351|352|708|(0)(0)|402|744|(0)(0)|427|(0)|432|446|(0)|450|451|(0)|455|456|(0)(0)|492|(0)|504|(0)|507|(0)(0)|511|(0)|520|(0)(0)|524|(0)|527|(0)|(0)(0)|537|538|(0)|543|(0)|549|550|(0)|555|556|(0)(0)|560|(0)(0)|(0)(0)|(0)|(0)(0)|605|(0)|(0)|634|636|(0)|640|(0)|645|(0)(0)|(0)|654|(0)(0)|(0)|660|(0)|663|(0)|667|(0)(0)|697|(0)|700|701)(1:144))|146|(0)|(0)|172|173|174|(0)|179|180|(0)|185|186|(0)(0)|190|(0)(0)|(0)(0)|204|(0)|213|214|(0)(0)|218|(0)|221|727|222|223|(0)(0)|237|745|238|(0)|243|(0)|248|(0)|251|252|253|(0)(0)|257|(0)(0)|261|(0)|265|266|(0)|709|276|(0)|279|(0)(0)|287|729|288|(0)|293|(0)|304|305|(0)|309|(0)(0)|313|(0)|318|319|(0)|324|325|326|(0)|330|332|334|(0)|(0)|340|(0)|(0)|349|350|711|351|352|708|(0)(0)|402|744|(0)(0)|427|(0)|432|446|(0)|450|451|(0)|455|456|(0)(0)|492|(0)|504|(0)|507|(0)(0)|511|(0)|520|(0)(0)|524|(0)|527|(0)|(0)(0)|537|538|(0)|543|(0)|549|550|(0)|555|556|(0)(0)|560|(0)(0)|(0)(0)|(0)|(0)(0)|605|(0)|(0)|634|636|(0)|640|(0)|645|(0)(0)|(0)|654|(0)(0)|(0)|660|(0)|663|(0)|667|(0)(0)|697|(0)|700|701))|42|43|(0)(0)|51|(0)(0)|(0)|85|(0)|88|(2:90|92)|95|96|(0)(0)|112|(0)|115|(0)|118|(1:120)|122|(0)|135|(1:137)|145|146|(0)|(0)|172|173|174|(0)|179|180|(0)|185|186|(0)(0)|190|(0)(0)|(0)(0)|204|(0)|213|214|(0)(0)|218|(0)|221|727|222|223|(0)(0)|237|745|238|(0)|243|(0)|248|(0)|251|252|253|(0)(0)|257|(0)(0)|261|(0)|265|266|(0)|709|276|(0)|279|(0)(0)|287|729|288|(0)|293|(0)|304|305|(0)|309|(0)(0)|313|(0)|318|319|(0)|324|325|326|(0)|330|332|334|(0)|(0)|340|(0)|(0)|349|350|711|351|352|708|(0)(0)|402|744|(0)(0)|427|(0)|432|446|(0)|450|451|(0)|455|456|(0)(0)|492|(0)|504|(0)|507|(0)(0)|511|(0)|520|(0)(0)|524|(0)|527|(0)|(0)(0)|537|538|(0)|543|(0)|549|550|(0)|555|556|(0)(0)|560|(0)(0)|(0)(0)|(0)|(0)(0)|605|(0)|(0)|634|636|(0)|640|(0)|645|(0)(0)|(0)|654|(0)(0)|(0)|660|(0)|663|(0)|667|(0)(0)|697|(0)|700|701) */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x02e4, code lost:
        if (r24.height() != r50.mHeight) goto L_0x02ee;
     */
    /* JADX WARN: Code restructure failed: missing block: B:373:0x0703, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:374:0x0704, code lost:
        r45 = r3;
        r43 = r0;
        r1 = r8;
        r2 = r31;
        r8 = r36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:433:0x0817, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:434:0x0818, code lost:
        r2 = r31;
        r1 = r36;
        r8 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:435:0x081e, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:436:0x081f, code lost:
        r45 = r3;
        r43 = r0;
        r8 = r36;
        r2 = r31;
        r1 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:437:0x0830, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:438:0x0831, code lost:
        r43 = r0;
        r45 = r8;
        r8 = r36;
        r2 = r31;
        r1 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:439:0x0842, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:440:0x0843, code lost:
        r43 = r0;
        r45 = r8;
        r8 = r36;
        r2 = r31;
        r1 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:441:0x0856, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:442:0x0857, code lost:
        r43 = r0;
        r8 = r36;
        r45 = r8;
        r1 = r30;
        r2 = r31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:443:0x0866, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:444:0x0867, code lost:
        r35 = false;
        r43 = r0;
        r45 = r8;
        r8 = r3;
        r1 = r30;
        r2 = r31;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x02a6 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:176:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x030f  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0328  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0339  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0343  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0349  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0378  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0382 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:216:0x03b9  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0400  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x040d  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x04a7  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x04d5 A[Catch: RemoteException -> 0x04be, TRY_ENTER, TryCatch #4 {RemoteException -> 0x04be, blocks: (B:240:0x04b7, B:245:0x04d5, B:247:0x04df), top: B:713:0x04b7 }] */
    /* JADX WARN: Removed duplicated region for block: B:250:0x04ed A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:255:0x0509  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x050b  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0511  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x0513  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x0518 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0553 A[Catch: RemoteException -> 0x053b, TRY_ENTER, TRY_LEAVE, TryCatch #16 {RemoteException -> 0x053b, blocks: (B:268:0x0523, B:270:0x0527, B:271:0x052e, B:273:0x0537, B:278:0x0553), top: B:736:0x0523 }] */
    /* JADX WARN: Removed duplicated region for block: B:286:0x05b8  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x05c2 A[Catch: RemoteException -> 0x05c6, TRY_ENTER, TRY_LEAVE, TryCatch #10 {RemoteException -> 0x05c6, blocks: (B:283:0x0565, B:290:0x05c2, B:295:0x05de, B:297:0x05e2, B:298:0x05fe, B:302:0x060c, B:308:0x0621, B:315:0x0644, B:321:0x0653, B:336:0x067c, B:338:0x0684, B:344:0x0697, B:346:0x069f), top: B:725:0x0565 }] */
    /* JADX WARN: Removed duplicated region for block: B:295:0x05de A[Catch: RemoteException -> 0x05c6, TRY_ENTER, TryCatch #10 {RemoteException -> 0x05c6, blocks: (B:283:0x0565, B:290:0x05c2, B:295:0x05de, B:297:0x05e2, B:298:0x05fe, B:302:0x060c, B:308:0x0621, B:315:0x0644, B:321:0x0653, B:336:0x067c, B:338:0x0684, B:344:0x0697, B:346:0x069f), top: B:725:0x0565 }] */
    /* JADX WARN: Removed duplicated region for block: B:307:0x061f  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x0636  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x0638  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x0644 A[Catch: RemoteException -> 0x05c6, TRY_ENTER, TryCatch #10 {RemoteException -> 0x05c6, blocks: (B:283:0x0565, B:290:0x05c2, B:295:0x05de, B:297:0x05e2, B:298:0x05fe, B:302:0x060c, B:308:0x0621, B:315:0x0644, B:321:0x0653, B:336:0x067c, B:338:0x0684, B:344:0x0697, B:346:0x069f), top: B:725:0x0565 }] */
    /* JADX WARN: Removed duplicated region for block: B:321:0x0653 A[Catch: RemoteException -> 0x05c6, TRY_LEAVE, TryCatch #10 {RemoteException -> 0x05c6, blocks: (B:283:0x0565, B:290:0x05c2, B:295:0x05de, B:297:0x05e2, B:298:0x05fe, B:302:0x060c, B:308:0x0621, B:315:0x0644, B:321:0x0653, B:336:0x067c, B:338:0x0684, B:344:0x0697, B:346:0x069f), top: B:725:0x0565 }] */
    /* JADX WARN: Removed duplicated region for block: B:328:0x0668 A[Catch: RemoteException -> 0x0830, TryCatch #12 {RemoteException -> 0x0830, blocks: (B:288:0x05bc, B:293:0x05d4, B:305:0x0615, B:309:0x062e, B:313:0x0639, B:326:0x0660, B:328:0x0668, B:330:0x066d, B:340:0x068c, B:349:0x06a7), top: B:729:0x05bc }] */
    /* JADX WARN: Removed duplicated region for block: B:336:0x067c A[Catch: RemoteException -> 0x05c6, TRY_ENTER, TryCatch #10 {RemoteException -> 0x05c6, blocks: (B:283:0x0565, B:290:0x05c2, B:295:0x05de, B:297:0x05e2, B:298:0x05fe, B:302:0x060c, B:308:0x0621, B:315:0x0644, B:321:0x0653, B:336:0x067c, B:338:0x0684, B:344:0x0697, B:346:0x069f), top: B:725:0x0565 }] */
    /* JADX WARN: Removed duplicated region for block: B:338:0x0684 A[Catch: RemoteException -> 0x05c6, TRY_LEAVE, TryCatch #10 {RemoteException -> 0x05c6, blocks: (B:283:0x0565, B:290:0x05c2, B:295:0x05de, B:297:0x05e2, B:298:0x05fe, B:302:0x060c, B:308:0x0621, B:315:0x0644, B:321:0x0653, B:336:0x067c, B:338:0x0684, B:344:0x0697, B:346:0x069f), top: B:725:0x0565 }] */
    /* JADX WARN: Removed duplicated region for block: B:342:0x0692  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x0697 A[Catch: RemoteException -> 0x05c6, TRY_ENTER, TryCatch #10 {RemoteException -> 0x05c6, blocks: (B:283:0x0565, B:290:0x05c2, B:295:0x05de, B:297:0x05e2, B:298:0x05fe, B:302:0x060c, B:308:0x0621, B:315:0x0644, B:321:0x0653, B:336:0x067c, B:338:0x0684, B:344:0x0697, B:346:0x069f), top: B:725:0x0565 }] */
    /* JADX WARN: Removed duplicated region for block: B:348:0x06a4  */
    /* JADX WARN: Removed duplicated region for block: B:354:0x06b7  */
    /* JADX WARN: Removed duplicated region for block: B:375:0x0711  */
    /* JADX WARN: Removed duplicated region for block: B:404:0x076e  */
    /* JADX WARN: Removed duplicated region for block: B:426:0x07e9  */
    /* JADX WARN: Removed duplicated region for block: B:429:0x07fb  */
    /* JADX WARN: Removed duplicated region for block: B:448:0x0896  */
    /* JADX WARN: Removed duplicated region for block: B:453:0x08ce  */
    /* JADX WARN: Removed duplicated region for block: B:458:0x08e6  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:491:0x0995  */
    /* JADX WARN: Removed duplicated region for block: B:494:0x099d  */
    /* JADX WARN: Removed duplicated region for block: B:506:0x09cd A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:509:0x09d3  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:510:0x09d5  */
    /* JADX WARN: Removed duplicated region for block: B:513:0x09dc  */
    /* JADX WARN: Removed duplicated region for block: B:522:0x0a0b  */
    /* JADX WARN: Removed duplicated region for block: B:523:0x0a50  */
    /* JADX WARN: Removed duplicated region for block: B:526:0x0a6a  */
    /* JADX WARN: Removed duplicated region for block: B:529:0x0a80  */
    /* JADX WARN: Removed duplicated region for block: B:531:0x0a92  */
    /* JADX WARN: Removed duplicated region for block: B:536:0x0abd  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:540:0x0acc A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:545:0x0ad7  */
    /* JADX WARN: Removed duplicated region for block: B:552:0x0ae3  */
    /* JADX WARN: Removed duplicated region for block: B:558:0x0af2  */
    /* JADX WARN: Removed duplicated region for block: B:559:0x0b3d  */
    /* JADX WARN: Removed duplicated region for block: B:562:0x0b42  */
    /* JADX WARN: Removed duplicated region for block: B:578:0x0c03  */
    /* JADX WARN: Removed duplicated region for block: B:580:0x0c0b  */
    /* JADX WARN: Removed duplicated region for block: B:581:0x0c0f  */
    /* JADX WARN: Removed duplicated region for block: B:586:0x0c1c  */
    /* JADX WARN: Removed duplicated region for block: B:588:0x0c2a  */
    /* JADX WARN: Removed duplicated region for block: B:604:0x0cd5  */
    /* JADX WARN: Removed duplicated region for block: B:607:0x0cdb  */
    /* JADX WARN: Removed duplicated region for block: B:631:0x0d76  */
    /* JADX WARN: Removed duplicated region for block: B:638:0x0d85 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:642:0x0d8c  */
    /* JADX WARN: Removed duplicated region for block: B:647:0x0d95  */
    /* JADX WARN: Removed duplicated region for block: B:648:0x0d99  */
    /* JADX WARN: Removed duplicated region for block: B:653:0x0da4 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:656:0x0dae  */
    /* JADX WARN: Removed duplicated region for block: B:657:0x0db0  */
    /* JADX WARN: Removed duplicated region for block: B:659:0x0db3  */
    /* JADX WARN: Removed duplicated region for block: B:662:0x0dce  */
    /* JADX WARN: Removed duplicated region for block: B:665:0x0ddb A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:669:0x0de3  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:679:0x0e30  */
    /* JADX WARN: Removed duplicated region for block: B:699:0x0e7c  */
    /* JADX WARN: Removed duplicated region for block: B:713:0x04b7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:717:0x055c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:732:0x0428 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:736:0x0523 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0197  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void performTraversals() {
        /*
            Method dump skipped, instructions count: 3728
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.ViewRootImpl.performTraversals():void");
    }

    /* JADX WARN: Finally extract failed */
    private void notifyContentCatpureEvents() {
        Trace.traceBegin(8L, "notifyContentCaptureEvents");
        try {
            MainContentCaptureSession mainSession = this.mAttachInfo.mContentCaptureManager.getMainContentCaptureSession();
            for (int i = 0; i < this.mAttachInfo.mContentCaptureEvents.size(); i++) {
                int sessionId = this.mAttachInfo.mContentCaptureEvents.keyAt(i);
                mainSession.notifyViewTreeEvent(sessionId, true);
                ArrayList<Object> events = this.mAttachInfo.mContentCaptureEvents.valueAt(i);
                for (int j = 0; j < events.size(); j++) {
                    Object event = events.get(j);
                    if (event instanceof AutofillId) {
                        mainSession.notifyViewDisappeared(sessionId, (AutofillId) event);
                    } else if (event instanceof View) {
                        View view = (View) event;
                        ContentCaptureSession session = view.getContentCaptureSession();
                        if (session == null) {
                            String str = this.mTag;
                            Log.w(str, "no content capture session on view: " + view);
                        } else {
                            int actualId = session.getId();
                            if (actualId != sessionId) {
                                String str2 = this.mTag;
                                Log.w(str2, "content capture session mismatch for view (" + view + "): was " + sessionId + " before, it's " + actualId + " now");
                            } else {
                                ViewStructure structure = session.newViewStructure(view);
                                view.onProvideContentCaptureStructure(structure, 0);
                                session.notifyViewAppeared(structure);
                            }
                        }
                    } else if (event instanceof Insets) {
                        mainSession.notifyViewInsetsChanged(sessionId, (Insets) event);
                    } else {
                        String str3 = this.mTag;
                        Log.w(str3, "invalid content capture event: " + event);
                    }
                }
                mainSession.notifyViewTreeEvent(sessionId, false);
            }
            this.mAttachInfo.mContentCaptureEvents = null;
            Trace.traceEnd(8L);
            if (DEBUG_DRAW || ViewDebugManager.DEBUG_LIFECYCLE || ViewDebugManager.DEBUG_ENG) {
                boolean isViewVisible = getHostVisibility() == 0;
                boolean cancelDraw = this.mAttachInfo.mTreeObserver.dispatchOnPreDraw() || !isViewVisible;
                ViewDebugManager instance = ViewDebugManager.getInstance();
                View.AttachInfo attachInfo = this.mAttachInfo;
                instance.debugTraveralDone(attachInfo, attachInfo.mThreadedRenderer, this.mAttachInfo.mThreadedRenderer == null ? false : this.mAttachInfo.mThreadedRenderer.isEnabled(), this, isViewVisible, cancelDraw, this.mTag);
            }
            this.mIsInTraversal = false;
        } catch (Throwable th) {
            Trace.traceEnd(8L);
            throw th;
        }
    }

    private void notifyHolderSurfaceDestroyed() {
        this.mSurfaceHolder.ungetCallbacks();
        SurfaceHolder.Callback[] callbacks = this.mSurfaceHolder.getCallbacks();
        if (callbacks != null) {
            for (SurfaceHolder.Callback c : callbacks) {
                c.surfaceDestroyed(this.mSurfaceHolder);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeHandleWindowMove(Rect frame) {
        boolean windowMoved = (this.mAttachInfo.mWindowLeft == frame.left && this.mAttachInfo.mWindowTop == frame.top) ? false : true;
        if (windowMoved) {
            this.mAttachInfo.mWindowLeft = frame.left;
            this.mAttachInfo.mWindowTop = frame.top;
        }
        if (windowMoved || this.mAttachInfo.mNeedsUpdateLightCenter) {
            if (this.mAttachInfo.mThreadedRenderer != null) {
                this.mAttachInfo.mThreadedRenderer.setLightCenter(this.mAttachInfo);
            }
            this.mAttachInfo.mNeedsUpdateLightCenter = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleWindowFocusChanged() {
        boolean z;
        synchronized (this) {
            if (this.mWindowFocusChanged) {
                this.mWindowFocusChanged = false;
                boolean hasWindowFocus = this.mUpcomingWindowFocus;
                boolean inTouchMode = this.mUpcomingInTouchMode;
                this.mViewRootImplExt.handleWindowFocusChanged(this.mContext, hasWindowFocus);
                if (hasWindowFocus) {
                    InsetsController insetsController = this.mInsetsController;
                    if (getFocusedViewOrNull() != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    insetsController.onWindowFocusGained(z);
                } else {
                    this.mInsetsController.onWindowFocusLost();
                }
                if (this.mAdded) {
                    profileRendering(hasWindowFocus);
                    if (hasWindowFocus) {
                        ensureTouchModeLocally(inTouchMode);
                        if (this.mAttachInfo.mThreadedRenderer != null && this.mSurface.isValid()) {
                            this.mFullRedrawNeeded = true;
                            try {
                                Rect surfaceInsets = this.mWindowAttributes.surfaceInsets;
                                this.mAttachInfo.mThreadedRenderer.initializeIfNeeded(this.mWidth, this.mHeight, this.mAttachInfo, this.mSurface, surfaceInsets);
                            } catch (Surface.OutOfResourcesException e) {
                                Log.e(this.mTag, "OutOfResourcesException locking surface", e);
                                try {
                                    if (!this.mWindowSession.outOfMemory(this.mWindow)) {
                                        Slog.w(this.mTag, "No processes killed for memory; killing self");
                                        Process.killProcess(Process.myPid());
                                    }
                                } catch (RemoteException e2) {
                                }
                                ViewRootHandler viewRootHandler = this.mHandler;
                                viewRootHandler.sendMessageDelayed(viewRootHandler.obtainMessage(6), 500L);
                                return;
                            }
                        }
                    }
                    this.mAttachInfo.mHasWindowFocus = hasWindowFocus;
                    this.mImeFocusController.updateImeFocusable(this.mWindowAttributes, true);
                    this.mImeFocusController.onPreWindowFocus(hasWindowFocus, this.mWindowAttributes);
                    if (this.mView != null) {
                        this.mAttachInfo.mKeyDispatchState.reset();
                        this.mView.dispatchWindowFocusChanged(hasWindowFocus);
                        this.mAttachInfo.mTreeObserver.dispatchOnWindowFocusChange(hasWindowFocus);
                        if (this.mAttachInfo.mTooltipHost != null) {
                            this.mAttachInfo.mTooltipHost.hideTooltip();
                        }
                    }
                    this.mImeFocusController.onPostWindowFocus(getFocusedViewOrNull(), hasWindowFocus, this.mWindowAttributes);
                    if (hasWindowFocus) {
                        this.mWindowAttributes.softInputMode &= TrafficStats.TAG_NETWORK_STACK_RANGE_END;
                        ((WindowManager.LayoutParams) this.mView.getLayoutParams()).softInputMode &= TrafficStats.TAG_NETWORK_STACK_RANGE_END;
                        fireAccessibilityFocusEventIfHasFocusedNode();
                    } else if (this.mPointerCapture) {
                        handlePointerCaptureChanged(false);
                    }
                }
                this.mFirstInputStage.onWindowFocusChanged(hasWindowFocus);
                if (hasWindowFocus) {
                    handleContentCaptureFlush();
                }
            }
        }
    }

    private void fireAccessibilityFocusEventIfHasFocusedNode() {
        View focusedView;
        if (AccessibilityManager.getInstance(this.mContext).isEnabled() && (focusedView = this.mView.findFocus()) != null) {
            AccessibilityNodeProvider provider = focusedView.getAccessibilityNodeProvider();
            if (provider == null) {
                focusedView.sendAccessibilityEvent(8);
                return;
            }
            AccessibilityNodeInfo focusedNode = findFocusedVirtualNode(provider);
            if (focusedNode != null) {
                int virtualId = AccessibilityNodeInfo.getVirtualDescendantId(focusedNode.getSourceNodeId());
                AccessibilityEvent event = AccessibilityEvent.obtain(8);
                event.setSource(focusedView, virtualId);
                event.setPackageName(focusedNode.getPackageName());
                event.setChecked(focusedNode.isChecked());
                event.setContentDescription(focusedNode.getContentDescription());
                event.setPassword(focusedNode.isPassword());
                event.getText().add(focusedNode.getText());
                event.setEnabled(focusedNode.isEnabled());
                focusedView.getParent().requestSendAccessibilityEvent(focusedView, event);
                focusedNode.recycle();
            }
        }
    }

    private AccessibilityNodeInfo findFocusedVirtualNode(AccessibilityNodeProvider provider) {
        AccessibilityNodeInfo focusedNode = provider.findFocus(1);
        if (focusedNode != null) {
            return focusedNode;
        }
        if (!this.mContext.isAutofillCompatibilityEnabled()) {
            return null;
        }
        AccessibilityNodeInfo current = provider.createAccessibilityNodeInfo(-1);
        if (current.isFocused()) {
            return current;
        }
        Queue<AccessibilityNodeInfo> fringe = new LinkedList<>();
        fringe.offer(current);
        while (!fringe.isEmpty()) {
            AccessibilityNodeInfo current2 = fringe.poll();
            LongArray childNodeIds = current2.getChildNodeIds();
            if (childNodeIds != null && childNodeIds.size() > 0) {
                int childCount = childNodeIds.size();
                for (int i = 0; i < childCount; i++) {
                    int virtualId = AccessibilityNodeInfo.getVirtualDescendantId(childNodeIds.get(i));
                    AccessibilityNodeInfo child = provider.createAccessibilityNodeInfo(virtualId);
                    if (child != null) {
                        if (child.isFocused()) {
                            return child;
                        }
                        fringe.offer(child);
                    }
                }
                current2.recycle();
            }
        }
        return null;
    }

    private void handleOutOfResourcesException(Surface.OutOfResourcesException e) {
        Log.e(this.mTag, "OutOfResourcesException initializing HW surface", e);
        try {
            if (!this.mWindowSession.outOfMemory(this.mWindow) && Process.myUid() != 1000) {
                Slog.w(this.mTag, "No processes killed for memory; killing self");
                Process.killProcess(Process.myPid());
            }
        } catch (RemoteException e2) {
        }
        this.mLayoutRequested = true;
    }

    private void performMeasure(int childWidthMeasureSpec, int childHeightMeasureSpec) {
        if (this.mView != null) {
            Trace.traceBegin(8L, "measure");
            long measureStartTime = System.nanoTime();
            try {
                this.mView.measure(childWidthMeasureSpec, childHeightMeasureSpec);
                this.mChoreographer.mChoreographerExt.populateMeasureCost(System.nanoTime() - measureStartTime);
                OplusJankMonitorExtPlugin.setLaunchStageTime.call(this.mBasePackageName, "performMeasure", Long.valueOf(measureStartTime));
                Trace.traceEnd(8L);
            } catch (Throwable th) {
                this.mChoreographer.mChoreographerExt.populateMeasureCost(System.nanoTime() - measureStartTime);
                OplusJankMonitorExtPlugin.setLaunchStageTime.call(this.mBasePackageName, "performMeasure", Long.valueOf(measureStartTime));
                Trace.traceEnd(8L);
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isInLayout() {
        return this.mInLayout;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean requestLayoutDuringLayout(View view) {
        if (view.mParent == null || view.mAttachInfo == null) {
            return true;
        }
        if (!this.mLayoutRequesters.contains(view)) {
            this.mLayoutRequesters.add(view);
        }
        return !this.mHandlingLayoutInLayoutRequest;
    }

    /* JADX WARN: Finally extract failed */
    private void performLayout(WindowManager.LayoutParams lp, int desiredWindowWidth, int desiredWindowHeight) {
        ArrayList<View> validLayoutRequesters;
        this.mScrollMayChange = true;
        this.mInLayout = true;
        View host = this.mView;
        if (host != null) {
            if (DEBUG_ORIENTATION || DEBUG_LAYOUT) {
                String str = this.mTag;
                Log.v(str, "Laying out " + host + " in " + this + " to (" + host.getMeasuredWidth() + ", " + host.getMeasuredHeight() + ")");
            }
            Trace.traceBegin(8L, TtmlUtils.TAG_LAYOUT);
            long layoutStartTime = System.nanoTime();
            try {
                host.layout(0, 0, host.getMeasuredWidth(), host.getMeasuredHeight());
                this.mInLayout = false;
                int numViewsRequestingLayout = this.mLayoutRequesters.size();
                if (numViewsRequestingLayout > 0 && (validLayoutRequesters = getValidLayoutRequesters(this.mLayoutRequesters, false)) != null) {
                    this.mHandlingLayoutInLayoutRequest = true;
                    int numValidRequests = validLayoutRequesters.size();
                    for (int i = 0; i < numValidRequests; i++) {
                        View view = validLayoutRequesters.get(i);
                        Log.w("View", "requestLayout() improperly called by " + view + " during layout: running second layout pass");
                        view.requestLayout();
                    }
                    measureHierarchy(host, lp, this.mView.getContext().getResources(), desiredWindowWidth, desiredWindowHeight);
                    this.mInLayout = true;
                    host.layout(0, 0, host.getMeasuredWidth(), host.getMeasuredHeight());
                    this.mHandlingLayoutInLayoutRequest = false;
                    final ArrayList<View> validLayoutRequesters2 = getValidLayoutRequesters(this.mLayoutRequesters, true);
                    if (validLayoutRequesters2 != null) {
                        getRunQueue().post(new Runnable() { // from class: android.view.ViewRootImpl.2
                            @Override // java.lang.Runnable
                            public void run() {
                                int numValidRequests2 = validLayoutRequesters2.size();
                                for (int i2 = 0; i2 < numValidRequests2; i2++) {
                                    View view2 = (View) validLayoutRequesters2.get(i2);
                                    Log.w("View", "requestLayout() improperly called by " + view2 + " during second layout pass: posting in next frame");
                                    view2.requestLayout();
                                }
                            }
                        });
                    }
                }
                this.mChoreographer.mChoreographerExt.populateLayoutCost(System.nanoTime() - layoutStartTime);
                Trace.traceEnd(8L);
                this.mViewRootImplExt.postShowGuidePopupRunnable(this.mView);
                this.mInLayout = false;
            } catch (Throwable th) {
                this.mChoreographer.mChoreographerExt.populateLayoutCost(System.nanoTime() - layoutStartTime);
                Trace.traceEnd(8L);
                throw th;
            }
        }
    }

    private ArrayList<View> getValidLayoutRequesters(ArrayList<View> layoutRequesters, boolean secondLayoutRequests) {
        int numViewsRequestingLayout = layoutRequesters.size();
        ArrayList<View> validLayoutRequesters = null;
        for (int i = 0; i < numViewsRequestingLayout; i++) {
            View view = layoutRequesters.get(i);
            if (!(view == null || view.mAttachInfo == null || view.mParent == null || !(secondLayoutRequests || (view.mPrivateFlags & 4096) == 4096))) {
                boolean gone = false;
                View parent = view;
                while (true) {
                    if (parent == null) {
                        break;
                    } else if ((parent.mViewFlags & 12) == 8) {
                        gone = true;
                        break;
                    } else if (parent.mParent instanceof View) {
                        parent = (View) parent.mParent;
                    } else {
                        parent = null;
                    }
                }
                if (!gone) {
                    if (validLayoutRequesters == null) {
                        validLayoutRequesters = new ArrayList<>();
                    }
                    validLayoutRequesters.add(view);
                }
            }
        }
        if (!secondLayoutRequests) {
            for (int i2 = 0; i2 < numViewsRequestingLayout; i2++) {
                View view2 = layoutRequesters.get(i2);
                while (view2 != null && (view2.mPrivateFlags & 4096) != 0) {
                    view2.mPrivateFlags &= -4097;
                    if (view2.mParent instanceof View) {
                        view2 = (View) view2.mParent;
                    } else {
                        view2 = null;
                    }
                }
            }
        }
        layoutRequesters.clear();
        return validLayoutRequesters;
    }

    @Override // android.view.ViewParent
    public void requestTransparentRegion(View child) {
        checkThread();
        View view = this.mView;
        if (view == child) {
            if ((view.mPrivateFlags & 512) == 0) {
                this.mView.mPrivateFlags |= 512;
                this.mWindowAttributesChanged = true;
            }
            requestLayout();
        }
    }

    private static int getRootMeasureSpec(int windowSize, int rootDimension) {
        switch (rootDimension) {
            case -2:
                int measureSpec = View.MeasureSpec.makeMeasureSpec(windowSize, Integer.MIN_VALUE);
                return measureSpec;
            case -1:
                int measureSpec2 = View.MeasureSpec.makeMeasureSpec(windowSize, 1073741824);
                return measureSpec2;
            default:
                int measureSpec3 = View.MeasureSpec.makeMeasureSpec(rootDimension, 1073741824);
                return measureSpec3;
        }
    }

    @Override // android.view.ThreadedRenderer.DrawCallbacks
    public void onPreDraw(RecordingCanvas canvas) {
        if (!(this.mCurScrollY == 0 || this.mHardwareYOffset == 0 || !this.mAttachInfo.mThreadedRenderer.isOpaque())) {
            canvas.drawColor(-16777216);
        }
        canvas.translate(-this.mHardwareXOffset, -this.mHardwareYOffset);
    }

    @Override // android.view.ThreadedRenderer.DrawCallbacks
    public void onPostDraw(RecordingCanvas canvas) {
        drawAccessibilityFocusedDrawableIfNeeded(canvas);
        if (this.mUseMTRenderer) {
            for (int i = this.mWindowCallbacks.size() - 1; i >= 0; i--) {
                this.mWindowCallbacks.get(i).onPostDraw(canvas);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void outputDisplayList(View view) {
        view.mRenderNode.output();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void profileRendering(boolean enabled) {
        if (this.mProfileRendering) {
            this.mRenderProfilingEnabled = enabled;
            Choreographer.FrameCallback frameCallback = this.mRenderProfiler;
            if (frameCallback != null) {
                this.mChoreographer.removeFrameCallback(frameCallback);
            }
            if (this.mRenderProfilingEnabled) {
                if (this.mRenderProfiler == null) {
                    this.mRenderProfiler = new Choreographer.FrameCallback() { // from class: android.view.ViewRootImpl.3
                        @Override // android.view.Choreographer.FrameCallback
                        public void doFrame(long frameTimeNanos) {
                            ViewRootImpl.this.mDirty.set(0, 0, ViewRootImpl.this.mWidth, ViewRootImpl.this.mHeight);
                            ViewRootImpl.this.scheduleTraversals();
                            if (ViewRootImpl.this.mRenderProfilingEnabled) {
                                ViewRootImpl.this.mChoreographer.postFrameCallback(ViewRootImpl.this.mRenderProfiler);
                            }
                        }
                    };
                }
                this.mChoreographer.postFrameCallback(this.mRenderProfiler);
                return;
            }
            this.mRenderProfiler = null;
        }
    }

    private void trackFPS() {
        long nowTime = System.currentTimeMillis();
        if (this.mFpsStartTime < 0) {
            this.mFpsPrevTime = nowTime;
            this.mFpsStartTime = nowTime;
            this.mFpsNumFrames = 0;
            return;
        }
        this.mFpsNumFrames++;
        String thisHash = Integer.toHexString(System.identityHashCode(this));
        long frameTime = nowTime - this.mFpsPrevTime;
        long totalTime = nowTime - this.mFpsStartTime;
        Log.v(this.mTag, "0x" + thisHash + "\tFrame time:\t" + frameTime);
        this.mFpsPrevTime = nowTime;
        if (totalTime > 1000) {
            float fps = (this.mFpsNumFrames * 1000.0f) / ((float) totalTime);
            Log.v(this.mTag, "0x" + thisHash + "\tFPS:\t" + fps);
            this.mFpsStartTime = nowTime;
            this.mFpsNumFrames = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void drawPending() {
        this.mDrawsNeededToReport++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void pendingDrawFinished() {
        int i = this.mDrawsNeededToReport;
        if (i != 0) {
            int i2 = i - 1;
            this.mDrawsNeededToReport = i2;
            if (i2 == 0) {
                reportDrawFinished();
            } else if (DEBUG_BLAST) {
                Log.d(this.mTag, "pendingDrawFinished. Waiting on draw reported mDrawsNeededToReport=" + this.mDrawsNeededToReport);
            }
        } else {
            throw new RuntimeException("Unbalanced drawPending/pendingDrawFinished calls");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postDrawFinished() {
        this.mHandler.sendEmptyMessage(29);
    }

    private void reportDrawFinished() {
        if (Trace.isTagEnabled(8L)) {
            Trace.traceBegin(8L, "finish draw");
        }
        try {
            try {
                if (DEBUG_BLAST) {
                    Log.d(this.mTag, "reportDrawFinished");
                }
                this.mDrawsNeededToReport = 0;
                this.mWindowSession.finishDrawing(this.mWindow, this.mSurfaceChangedTransaction);
            } catch (RemoteException e) {
                if (ViewDebugManager.DEBUG_DRAW) {
                    String str = this.mTag;
                    Log.d(str, "Exception when finish draw window " + this.mWindow + " in " + this, e);
                }
                Log.e(this.mTag, "Unable to report draw finished", e);
                this.mSurfaceChangedTransaction.apply();
            }
            if (Trace.isTagEnabled(8L)) {
                Trace.traceEnd(8L);
            }
        } finally {
            this.mSurfaceChangedTransaction.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearBlastSync() {
        this.mNextDrawUseBlastSync = false;
        this.mWaitForBlastSyncComplete = false;
        if (DEBUG_BLAST) {
            String str = this.mTag;
            Log.d(str, "Scheduling a traversal=" + this.mRequestedTraverseWhilePaused + " due to a previous skipped traversal.");
        }
        if (this.mRequestedTraverseWhilePaused) {
            this.mRequestedTraverseWhilePaused = false;
            scheduleTraversals();
        }
    }

    public boolean isHardwareEnabled() {
        return this.mAttachInfo.mThreadedRenderer != null && this.mAttachInfo.mThreadedRenderer.isEnabled();
    }

    private boolean addFrameCompleteCallbackIfNeeded(final boolean reportNextDraw) {
        if (!isHardwareEnabled()) {
            return false;
        }
        if (!this.mNextDrawUseBlastSync && !reportNextDraw) {
            return false;
        }
        if (DEBUG_BLAST) {
            Log.d(this.mTag, "Creating frameCompleteCallback");
        }
        this.mAttachInfo.mThreadedRenderer.setFrameCompleteCallback(new HardwareRenderer.FrameCompleteCallback() { // from class: android.view.ViewRootImpl$$ExternalSyntheticLambda3
            @Override // android.graphics.HardwareRenderer.FrameCompleteCallback
            public final void onFrameComplete() {
                ViewRootImpl.this.lambda$addFrameCompleteCallbackIfNeeded$4$ViewRootImpl(reportNextDraw);
            }
        });
        return true;
    }

    public /* synthetic */ void lambda$addFrameCompleteCallbackIfNeeded$4$ViewRootImpl(final boolean reportNextDraw) {
        long frameNr = this.mBlastBufferQueue.getLastAcquiredFrameNum();
        if (DEBUG_BLAST) {
            String str = this.mTag;
            Log.d(str, "Received frameCompleteCallback  lastAcquiredFrameNum=" + frameNr + " lastAttemptedDrawFrameNum=" + this.mRtLastAttemptedDrawFrameNum);
        }
        final boolean frameWasNotDrawn = frameNr != this.mRtLastAttemptedDrawFrameNum;
        if (frameWasNotDrawn) {
            this.mBlastBufferQueue.setNextTransaction(null);
            this.mBlastBufferQueue.setTransactionCompleteCallback(this.mRtLastAttemptedDrawFrameNum, null);
        }
        this.mHandler.postAtFrontOfQueue(new Runnable() { // from class: android.view.ViewRootImpl$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                ViewRootImpl.this.lambda$addFrameCompleteCallbackIfNeeded$3$ViewRootImpl(reportNextDraw, frameWasNotDrawn);
            }
        });
    }

    public /* synthetic */ void lambda$addFrameCompleteCallbackIfNeeded$3$ViewRootImpl(boolean reportNextDraw, boolean frameWasNotDrawn) {
        if (this.mNextDrawUseBlastSync) {
            this.mSurfaceChangedTransaction.merge(this.mRtBLASTSyncTransaction);
        }
        if (reportNextDraw) {
            pendingDrawFinished();
        }
        if (frameWasNotDrawn) {
            clearBlastSync();
        }
    }

    private void addFrameCommitCallbackIfNeeded() {
        if (isHardwareEnabled()) {
            final ArrayList<Runnable> commitCallbacks = this.mAttachInfo.mTreeObserver.captureFrameCommitCallbacks();
            boolean needFrameCommitCallback = commitCallbacks != null && commitCallbacks.size() > 0;
            if (needFrameCommitCallback) {
                if (DEBUG_DRAW) {
                    String str = this.mTag;
                    Log.d(str, "Creating frameCommitCallback commitCallbacks size=" + commitCallbacks.size());
                }
                this.mAttachInfo.mThreadedRenderer.setFrameCommitCallback(new HardwareRenderer.FrameCommitCallback() { // from class: android.view.ViewRootImpl$$ExternalSyntheticLambda2
                    @Override // android.graphics.HardwareRenderer.FrameCommitCallback
                    public final void onFrameCommit(boolean z) {
                        ViewRootImpl.this.lambda$addFrameCommitCallbackIfNeeded$6$ViewRootImpl(commitCallbacks, z);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$addFrameCommitCallbackIfNeeded$6$ViewRootImpl(final ArrayList commitCallbacks, boolean didProduceBuffer) {
        if (DEBUG_DRAW) {
            String str = this.mTag;
            Log.d(str, "Received frameCommitCallback didProduceBuffer=" + didProduceBuffer);
        }
        this.mHandler.postAtFrontOfQueue(new Runnable() { // from class: android.view.ViewRootImpl$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                ViewRootImpl.lambda$addFrameCommitCallbackIfNeeded$5(commitCallbacks);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$addFrameCommitCallbackIfNeeded$5(ArrayList commitCallbacks) {
        for (int i = 0; i < commitCallbacks.size(); i++) {
            ((Runnable) commitCallbacks.get(i)).run();
        }
    }

    private void addFrameCallbackIfNeeded() {
        final boolean nextDrawUseBlastSync = this.mNextDrawUseBlastSync;
        final boolean reportNextDraw = this.mReportNextDraw;
        final boolean hasBlurUpdates = this.mBlurRegionAggregator.hasUpdates();
        final boolean needsCallbackForBlur = hasBlurUpdates || this.mBlurRegionAggregator.hasRegions();
        if (nextDrawUseBlastSync || reportNextDraw || needsCallbackForBlur) {
            if (DEBUG_BLAST) {
                String str = this.mTag;
                Log.d(str, "Creating frameDrawingCallback nextDrawUseBlastSync=" + nextDrawUseBlastSync + " reportNextDraw=" + reportNextDraw + " hasBlurUpdates=" + hasBlurUpdates);
            }
            this.mWaitForBlastSyncComplete = nextDrawUseBlastSync;
            final BackgroundBlurDrawable.BlurRegion[] blurRegionsForFrame = needsCallbackForBlur ? this.mBlurRegionAggregator.getBlurRegionsCopyForRT() : null;
            HardwareRenderer.FrameDrawingCallback frameDrawingCallback = new HardwareRenderer.FrameDrawingCallback() { // from class: android.view.ViewRootImpl$$ExternalSyntheticLambda6
                @Override // android.graphics.HardwareRenderer.FrameDrawingCallback
                public final void onFrameDraw(long j) {
                    ViewRootImpl.this.lambda$addFrameCallbackIfNeeded$8$ViewRootImpl(nextDrawUseBlastSync, needsCallbackForBlur, blurRegionsForFrame, hasBlurUpdates, reportNextDraw, j);
                }
            };
            registerRtFrameCallback(frameDrawingCallback);
        }
    }

    public /* synthetic */ void lambda$addFrameCallbackIfNeeded$8$ViewRootImpl(boolean nextDrawUseBlastSync, boolean needsCallbackForBlur, BackgroundBlurDrawable.BlurRegion[] blurRegionsForFrame, boolean hasBlurUpdates, boolean reportNextDraw, final long frame) {
        if (DEBUG_BLAST) {
            String str = this.mTag;
            Log.d(str, "Received frameDrawingCallback frameNum=" + frame + ". Creating transactionCompleteCallback=" + nextDrawUseBlastSync);
        }
        this.mRtLastAttemptedDrawFrameNum = frame;
        if (needsCallbackForBlur) {
            this.mBlurRegionAggregator.dispatchBlurTransactionIfNeeded(frame, blurRegionsForFrame, hasBlurUpdates);
        }
        BLASTBufferQueue bLASTBufferQueue = this.mBlastBufferQueue;
        if (bLASTBufferQueue != null) {
            if (nextDrawUseBlastSync) {
                bLASTBufferQueue.setNextTransaction(this.mRtBLASTSyncTransaction);
                this.mBlastBufferQueue.setTransactionCompleteCallback(frame, new BLASTBufferQueue.TransactionCompleteCallback() { // from class: android.view.ViewRootImpl$$ExternalSyntheticLambda0
                    @Override // android.graphics.BLASTBufferQueue.TransactionCompleteCallback
                    public final void onTransactionComplete(long j) {
                        ViewRootImpl.this.lambda$addFrameCallbackIfNeeded$7$ViewRootImpl(frame, j);
                    }
                });
            } else if (reportNextDraw) {
                bLASTBufferQueue.flushShadowQueue();
            }
        }
    }

    public /* synthetic */ void lambda$addFrameCallbackIfNeeded$7$ViewRootImpl(long frame, long frameNumber) {
        if (DEBUG_BLAST) {
            String str = this.mTag;
            Log.d(str, "Received transactionCompleteCallback frameNum=" + frame);
        }
        this.mHandler.postAtFrontOfQueue(new Runnable() { // from class: android.view.ViewRootImpl$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                ViewRootImpl.this.clearBlastSync();
            }
        });
    }

    /* JADX WARN: Finally extract failed */
    private void performDraw() {
        if ((this.mAttachInfo.mDisplayState != 1 || this.mReportNextDraw) && this.mView != null) {
            boolean fullRedrawNeeded = this.mFullRedrawNeeded || this.mReportNextDraw || this.mNextDrawUseBlastSync;
            this.mFullRedrawNeeded = false;
            this.mIsDrawing = true;
            Trace.traceBegin(8L, "draw");
            addFrameCallbackIfNeeded();
            addFrameCommitCallbackIfNeeded();
            boolean usingAsyncReport = addFrameCompleteCallbackIfNeeded(this.mReportNextDraw);
            try {
                boolean canUseAsync = draw(fullRedrawNeeded);
                if (usingAsyncReport && !canUseAsync) {
                    if (this.mAttachInfo.mThreadedRenderer != null) {
                        this.mAttachInfo.mThreadedRenderer.setFrameCompleteCallback(null);
                    }
                    usingAsyncReport = false;
                }
                this.mIsDrawing = false;
                Trace.traceEnd(8L);
                if (this.mAttachInfo.mPendingAnimatingRenderNodes != null) {
                    int count = this.mAttachInfo.mPendingAnimatingRenderNodes.size();
                    for (int i = 0; i < count; i++) {
                        this.mAttachInfo.mPendingAnimatingRenderNodes.get(i).endAllAnimators();
                    }
                    this.mAttachInfo.mPendingAnimatingRenderNodes.clear();
                }
                if (this.mReportNextDraw) {
                    this.mReportNextDraw = false;
                    CountDownLatch countDownLatch = this.mWindowDrawCountDown;
                    if (countDownLatch != null) {
                        try {
                            countDownLatch.await();
                        } catch (InterruptedException e) {
                            Log.e(this.mTag, "Window redraw count down interrupted!");
                        }
                        this.mWindowDrawCountDown = null;
                    }
                    if (this.mAttachInfo.mThreadedRenderer != null) {
                        this.mAttachInfo.mThreadedRenderer.setStopped(this.mStopped);
                    }
                    if (LOCAL_LOGV || DEBUG_DRAW) {
                        String str = this.mTag;
                        Log.v(str, "FINISHED DRAWING: " + ((Object) this.mWindowAttributes.getTitle()));
                    }
                    if (this.mSurfaceHolder != null && this.mSurface.isValid()) {
                        SurfaceCallbackHelper sch = new SurfaceCallbackHelper(new Runnable() { // from class: android.view.ViewRootImpl$$ExternalSyntheticLambda11
                            @Override // java.lang.Runnable
                            public final void run() {
                                ViewRootImpl.this.postDrawFinished();
                            }
                        });
                        SurfaceHolder.Callback[] callbacks = this.mSurfaceHolder.getCallbacks();
                        sch.dispatchSurfaceRedrawNeededAsync(this.mSurfaceHolder, callbacks);
                    } else if (!usingAsyncReport) {
                        if (this.mAttachInfo.mThreadedRenderer != null) {
                            this.mAttachInfo.mThreadedRenderer.fence();
                        }
                        pendingDrawFinished();
                    }
                }
                if (this.mPerformContentCapture) {
                    performContentCaptureInitialReport();
                }
            } catch (Throwable th) {
                this.mIsDrawing = false;
                Trace.traceEnd(8L);
                throw th;
            }
        }
    }

    private boolean isContentCaptureEnabled() {
        int i = 1;
        switch (this.mContentCaptureEnabled) {
            case 0:
                boolean reallyEnabled = isContentCaptureReallyEnabled();
                if (!reallyEnabled) {
                    i = 2;
                }
                this.mContentCaptureEnabled = i;
                return reallyEnabled;
            case 1:
                return true;
            case 2:
                return false;
            default:
                Log.w(TAG, "isContentCaptureEnabled(): invalid state " + this.mContentCaptureEnabled);
                return false;
        }
    }

    private boolean isContentCaptureReallyEnabled() {
        ContentCaptureManager ccm;
        return (this.mContext.getContentCaptureOptions() == null || (ccm = this.mAttachInfo.getContentCaptureManager(this.mContext)) == null || !ccm.isContentCaptureEnabled()) ? false : true;
    }

    private void performContentCaptureInitialReport() {
        this.mPerformContentCapture = false;
        View rootView = this.mView;
        if (DEBUG_CONTENT_CAPTURE) {
            String str = this.mTag;
            Log.v(str, "performContentCaptureInitialReport() on " + rootView);
        }
        if (Trace.isTagEnabled(8L)) {
            Trace.traceBegin(8L, "dispatchContentCapture() for " + getClass().getSimpleName());
        }
        try {
            if (isContentCaptureEnabled()) {
                rootView.dispatchInitialProvideContentCaptureStructure();
            }
        } finally {
            Trace.traceEnd(8L);
        }
    }

    private void handleContentCaptureFlush() {
        if (DEBUG_CONTENT_CAPTURE) {
            Log.v(this.mTag, "handleContentCaptureFlush()");
        }
        if (Trace.isTagEnabled(8L)) {
            Trace.traceBegin(8L, "flushContentCapture for " + getClass().getSimpleName());
        }
        try {
            if (isContentCaptureEnabled()) {
                ContentCaptureManager ccm = this.mAttachInfo.mContentCaptureManager;
                if (ccm == null) {
                    Log.w(TAG, "No ContentCapture on AttachInfo");
                } else {
                    ccm.flush(2);
                }
            }
        } finally {
            Trace.traceEnd(8L);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x033e  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0237  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean draw(boolean r26) {
        /*
            Method dump skipped, instructions count: 837
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.ViewRootImpl.draw(boolean):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00c2 A[Catch: all -> 0x0193, TryCatch #1 {all -> 0x0193, blocks: (B:16:0x006a, B:18:0x006e, B:20:0x0072, B:21:0x009e, B:27:0x00ab, B:28:0x00b1, B:30:0x00c2, B:31:0x0102, B:33:0x010d, B:35:0x0112, B:37:0x0116, B:39:0x0125), top: B:73:0x006a }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x010d A[Catch: all -> 0x0193, TryCatch #1 {all -> 0x0193, blocks: (B:16:0x006a, B:18:0x006e, B:20:0x0072, B:21:0x009e, B:27:0x00ab, B:28:0x00b1, B:30:0x00c2, B:31:0x0102, B:33:0x010d, B:35:0x0112, B:37:0x0116, B:39:0x0125), top: B:73:0x006a }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0112 A[Catch: all -> 0x0193, TryCatch #1 {all -> 0x0193, blocks: (B:16:0x006a, B:18:0x006e, B:20:0x0072, B:21:0x009e, B:27:0x00ab, B:28:0x00b1, B:30:0x00c2, B:31:0x0102, B:33:0x010d, B:35:0x0112, B:37:0x0116, B:39:0x0125), top: B:73:0x006a }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0125 A[Catch: all -> 0x0193, TRY_LEAVE, TryCatch #1 {all -> 0x0193, blocks: (B:16:0x006a, B:18:0x006e, B:20:0x0072, B:21:0x009e, B:27:0x00ab, B:28:0x00b1, B:30:0x00c2, B:31:0x0102, B:33:0x010d, B:35:0x0112, B:37:0x0116, B:39:0x0125), top: B:73:0x006a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean drawSoftware(android.view.Surface r23, android.view.View.AttachInfo r24, int r25, int r26, boolean r27, android.graphics.Rect r28, android.graphics.Rect r29) {
        /*
            Method dump skipped, instructions count: 493
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.ViewRootImpl.drawSoftware(android.view.Surface, android.view.View$AttachInfo, int, int, boolean, android.graphics.Rect, android.graphics.Rect):boolean");
    }

    private void drawAccessibilityFocusedDrawableIfNeeded(Canvas canvas) {
        Rect bounds = this.mAttachInfo.mTmpInvalRect;
        if (getAccessibilityFocusedRect(bounds)) {
            Drawable drawable = getAccessibilityFocusedDrawable();
            if (drawable != null) {
                drawable.setBounds(bounds);
                drawable.draw(canvas);
            }
        } else if (this.mAttachInfo.mAccessibilityFocusDrawable != null) {
            this.mAttachInfo.mAccessibilityFocusDrawable.setBounds(0, 0, 0, 0);
        }
    }

    private boolean getAccessibilityFocusedRect(Rect bounds) {
        View host;
        AccessibilityManager manager = AccessibilityManager.getInstance(this.mView.mContext);
        if (!manager.isEnabled() || !manager.isTouchExplorationEnabled() || (host = this.mAccessibilityFocusedHost) == null || host.mAttachInfo == null) {
            return false;
        }
        AccessibilityNodeProvider provider = host.getAccessibilityNodeProvider();
        if (provider == null) {
            host.getBoundsOnScreen(bounds, true);
        } else {
            AccessibilityNodeInfo accessibilityNodeInfo = this.mAccessibilityFocusedVirtualView;
            if (accessibilityNodeInfo == null) {
                return false;
            }
            accessibilityNodeInfo.getBoundsInScreen(bounds);
        }
        View.AttachInfo attachInfo = this.mAttachInfo;
        bounds.offset(0, attachInfo.mViewRootImpl.mScrollY);
        bounds.offset(-attachInfo.mWindowLeft, -attachInfo.mWindowTop);
        if (!bounds.intersect(0, 0, attachInfo.mViewRootImpl.mWidth, attachInfo.mViewRootImpl.mHeight)) {
            bounds.setEmpty();
        }
        return !bounds.isEmpty();
    }

    private Drawable getAccessibilityFocusedDrawable() {
        if (this.mAttachInfo.mAccessibilityFocusDrawable == null) {
            TypedValue value = new TypedValue();
            boolean resolved = this.mView.mContext.getTheme().resolveAttribute(R.attr.accessibilityFocusedDrawable, value, true);
            if (resolved) {
                this.mAttachInfo.mAccessibilityFocusDrawable = this.mView.mContext.getDrawable(value.resourceId);
            }
        }
        if (this.mAttachInfo.mAccessibilityFocusDrawable instanceof GradientDrawable) {
            GradientDrawable drawable = (GradientDrawable) this.mAttachInfo.mAccessibilityFocusDrawable;
            drawable.setStroke(this.mAccessibilityManager.getAccessibilityFocusStrokeWidth(), this.mAccessibilityManager.getAccessibilityFocusColor());
        }
        return this.mAttachInfo.mAccessibilityFocusDrawable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateSystemGestureExclusionRectsForView(View view) {
        this.mGestureExclusionTracker.updateRectsForView(view);
        this.mHandler.sendEmptyMessage(32);
    }

    void systemGestureExclusionChanged() {
        List<Rect> rectsForWindowManager = this.mGestureExclusionTracker.computeChangedRects();
        if (rectsForWindowManager != null && this.mView != null) {
            try {
                this.mWindowSession.reportSystemGestureExclusionChanged(this.mWindow, rectsForWindowManager);
                this.mAttachInfo.mTreeObserver.dispatchOnSystemGestureExclusionRectsChanged(rectsForWindowManager);
                this.mViewRootImplExt.setSystemGestureExclusionRegion(rectsForWindowManager);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    void updateLocationInParentDisplay(int x, int y) {
        View.AttachInfo attachInfo = this.mAttachInfo;
        if (attachInfo != null && !attachInfo.mLocationInParentDisplay.equals(x, y)) {
            this.mAttachInfo.mLocationInParentDisplay.set(x, y);
        }
    }

    public void setRootSystemGestureExclusionRects(List<Rect> rects) {
        this.mGestureExclusionTracker.setRootSystemGestureExclusionRects(rects);
        this.mHandler.sendEmptyMessage(32);
    }

    public List<Rect> getRootSystemGestureExclusionRects() {
        return this.mGestureExclusionTracker.getRootSystemGestureExclusionRects();
    }

    public void requestInvalidateRootRenderNode() {
        this.mInvalidateRootRequested = true;
    }

    boolean scrollToRectOrFocus(Rect rectangle, boolean immediate) {
        int scrollY;
        Rect ci = this.mAttachInfo.mContentInsets;
        Rect vi = this.mAttachInfo.mVisibleInsets;
        int scrollY2 = 0;
        boolean handled = false;
        if (vi.left > ci.left || vi.top > ci.top || vi.right > ci.right || vi.bottom > ci.bottom) {
            scrollY2 = this.mScrollY;
            View focus = this.mView.findFocus();
            if (focus == null) {
                return false;
            }
            WeakReference<View> weakReference = this.mLastScrolledFocus;
            View lastScrolledFocus = weakReference != null ? weakReference.get() : null;
            if (focus != lastScrolledFocus) {
                rectangle = null;
            }
            if (DEBUG_INPUT_RESIZE) {
                String str = this.mTag;
                Log.v(str, "Eval scroll: focus=" + focus + " rectangle=" + rectangle + " ci=" + ci + " vi=" + vi);
            }
            if (focus != lastScrolledFocus || this.mScrollMayChange || rectangle != null) {
                this.mLastScrolledFocus = new WeakReference<>(focus);
                this.mScrollMayChange = false;
                if (DEBUG_INPUT_RESIZE) {
                    Log.v(this.mTag, "Need to scroll?");
                }
                if (focus.getGlobalVisibleRect(this.mVisRect, null)) {
                    if (DEBUG_INPUT_RESIZE) {
                        String str2 = this.mTag;
                        Log.v(str2, "Root w=" + this.mView.getWidth() + " h=" + this.mView.getHeight() + " ci=" + ci.toShortString() + " vi=" + vi.toShortString());
                    }
                    if (rectangle == null) {
                        focus.getFocusedRect(this.mTempRect);
                        if (DEBUG_INPUT_RESIZE) {
                            String str3 = this.mTag;
                            Log.v(str3, "Focus " + focus + ": focusRect=" + this.mTempRect.toShortString());
                        }
                        View view = this.mView;
                        if (view instanceof ViewGroup) {
                            ((ViewGroup) view).offsetDescendantRectToMyCoords(focus, this.mTempRect);
                        }
                        if (DEBUG_INPUT_RESIZE) {
                            String str4 = this.mTag;
                            Log.v(str4, "Focus in window: focusRect=" + this.mTempRect.toShortString() + " visRect=" + this.mVisRect.toShortString());
                        }
                    } else {
                        this.mTempRect.set(rectangle);
                        if (DEBUG_INPUT_RESIZE) {
                            String str5 = this.mTag;
                            Log.v(str5, "Request scroll to rect: " + this.mTempRect.toShortString() + " visRect=" + this.mVisRect.toShortString());
                        }
                    }
                    if (this.mTempRect.intersect(this.mVisRect)) {
                        if (DEBUG_INPUT_RESIZE) {
                            String str6 = this.mTag;
                            Log.v(str6, "Focus window visible rect: " + this.mTempRect.toShortString());
                        }
                        if (this.mTempRect.height() <= (this.mView.getHeight() - vi.top) - vi.bottom) {
                            if (this.mTempRect.top < vi.top) {
                                scrollY = this.mTempRect.top - vi.top;
                                if (DEBUG_INPUT_RESIZE) {
                                    String str7 = this.mTag;
                                    Log.v(str7, "Top covered; scrollY=" + scrollY);
                                }
                            } else if (this.mTempRect.bottom > this.mView.getHeight() - vi.bottom) {
                                scrollY = this.mTempRect.bottom - (this.mView.getHeight() - vi.bottom);
                                if (DEBUG_INPUT_RESIZE) {
                                    String str8 = this.mTag;
                                    Log.v(str8, "Bottom covered; scrollY=" + scrollY);
                                }
                            } else {
                                scrollY2 = 0;
                            }
                            scrollY2 = scrollY;
                        } else if (DEBUG_INPUT_RESIZE) {
                            String str9 = this.mTag;
                            Log.v(str9, "Too tall; leaving scrollY=" + scrollY2);
                        }
                        handled = true;
                    }
                }
            } else if (DEBUG_INPUT_RESIZE) {
                String str10 = this.mTag;
                Log.v(str10, "Keeping scroll y=" + this.mScrollY + " vi=" + vi.toShortString());
            }
        }
        if (scrollY2 != this.mScrollY) {
            if (DEBUG_INPUT_RESIZE) {
                String str11 = this.mTag;
                Log.v(str11, "Pan scroll changed: old=" + this.mScrollY + " , new=" + scrollY2);
            }
            if (!immediate) {
                if (this.mScroller == null) {
                    this.mScroller = new Scroller(this.mView.getContext());
                }
                Scroller scroller = this.mScroller;
                int i = this.mScrollY;
                scroller.startScroll(0, i, 0, scrollY2 - i);
            } else {
                Scroller scroller2 = this.mScroller;
                if (scroller2 != null) {
                    scroller2.abortAnimation();
                }
            }
            this.mScrollY = scrollY2;
        }
        return handled;
    }

    public View getAccessibilityFocusedHost() {
        return this.mAccessibilityFocusedHost;
    }

    public AccessibilityNodeInfo getAccessibilityFocusedVirtualView() {
        return this.mAccessibilityFocusedVirtualView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAccessibilityFocus(View view, AccessibilityNodeInfo node) {
        if (this.mAccessibilityFocusedVirtualView != null) {
            AccessibilityNodeInfo focusNode = this.mAccessibilityFocusedVirtualView;
            View focusHost = this.mAccessibilityFocusedHost;
            this.mAccessibilityFocusedHost = null;
            this.mAccessibilityFocusedVirtualView = null;
            focusHost.clearAccessibilityFocusNoCallbacks(64);
            AccessibilityNodeProvider provider = focusHost.getAccessibilityNodeProvider();
            if (provider != null) {
                focusNode.getBoundsInParent(this.mTempRect);
                focusHost.invalidate(this.mTempRect);
                int virtualNodeId = AccessibilityNodeInfo.getVirtualDescendantId(focusNode.getSourceNodeId());
                provider.performAction(virtualNodeId, 128, null);
            }
            focusNode.recycle();
        }
        View view2 = this.mAccessibilityFocusedHost;
        if (!(view2 == null || view2 == view)) {
            view2.clearAccessibilityFocusNoCallbacks(64);
        }
        this.mAccessibilityFocusedHost = view;
        this.mAccessibilityFocusedVirtualView = node;
        if (this.mAttachInfo.mThreadedRenderer != null) {
            this.mAttachInfo.mThreadedRenderer.invalidateRoot();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasPointerCapture() {
        return this.mPointerCapture;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void requestPointerCapture(boolean enabled) {
        if (this.mPointerCapture != enabled) {
            IBinder inputToken = getInputToken();
            if (inputToken == null) {
                Log.e(this.mTag, "No input channel to request Pointer Capture.");
            } else {
                InputManager.getInstance().requestPointerCapture(inputToken, enabled);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlePointerCaptureChanged(boolean hasCapture) {
        if (this.mPointerCapture != hasCapture) {
            this.mPointerCapture = hasCapture;
            View view = this.mView;
            if (view != null) {
                view.dispatchPointerCaptureChanged(hasCapture);
            }
        }
    }

    private void updateColorModeIfNeeded(int colorMode) {
        if (this.mAttachInfo.mThreadedRenderer != null) {
            if (!getConfiguration().isScreenWideColorGamut()) {
                colorMode = 0;
            }
            this.mAttachInfo.mThreadedRenderer.setColorMode(this.mViewRootImplExt.getColorMode(colorMode));
        }
    }

    @Override // android.view.ViewParent
    public void requestChildFocus(View child, View focused) {
        if (DEBUG_INPUT_RESIZE) {
            String str = this.mTag;
            Log.v(str, "Request child " + child + " focus: focus now " + focused + " in " + this);
        }
        checkThread();
        scheduleTraversals();
    }

    @Override // android.view.ViewParent
    public void clearChildFocus(View child) {
        if (DEBUG_INPUT_RESIZE) {
            Log.v(this.mTag, "Clearing child focus");
        }
        checkThread();
        scheduleTraversals();
    }

    @Override // android.view.ViewParent
    public ViewParent getParentForAccessibility() {
        return null;
    }

    @Override // android.view.ViewParent
    public void focusableViewAvailable(View v) {
        checkThread();
        View view = this.mView;
        if (view == null) {
            return;
        }
        if (view.hasFocus()) {
            View focused = this.mView.findFocus();
            if (focused instanceof ViewGroup) {
                ViewGroup group = (ViewGroup) focused;
                if (group.getDescendantFocusability() == 262144 && isViewDescendantOf(v, focused)) {
                    v.requestFocus();
                }
            }
        } else if (sAlwaysAssignFocus || !this.mAttachInfo.mInTouchMode) {
            v.requestFocus();
        }
    }

    @Override // android.view.ViewParent
    public void recomputeViewAttributes(View child) {
        checkThread();
        if (this.mView == child) {
            this.mAttachInfo.mRecomputeGlobalAttributes = true;
            if (!this.mWillDrawSoon) {
                scheduleTraversals();
            }
        }
    }

    void dispatchDetachedFromWindow() {
        InputQueue inputQueue;
        this.mInsetsController.onWindowFocusLost();
        InputStage inputStage = this.mFirstInputStage;
        if (inputStage != null) {
            inputStage.onDetachedFromWindow();
        }
        View view = this.mView;
        if (!(view == null || view.mAttachInfo == null)) {
            this.mAttachInfo.mTreeObserver.dispatchOnWindowAttachedChange(false);
            this.mView.dispatchDetachedFromWindow();
            this.mViewRootImplExt.dispatchDetachedFromWindow(this.mView);
        }
        this.mAccessibilityInteractionConnectionManager.ensureNoConnection();
        this.mAccessibilityManager.removeAccessibilityStateChangeListener(this.mAccessibilityInteractionConnectionManager);
        this.mAccessibilityManager.removeHighTextContrastStateChangeListener(this.mHighContrastTextManager);
        removeSendWindowContentChangedCallback();
        destroyHardwareRenderer();
        setAccessibilityFocus(null, null);
        this.mInsetsController.cancelExistingAnimations();
        this.mView.assignParent(null);
        this.mView = null;
        this.mAttachInfo.mRootView = null;
        destroySurface();
        InputQueue.Callback callback = this.mInputQueueCallback;
        if (!(callback == null || (inputQueue = this.mInputQueue) == null)) {
            callback.onInputQueueDestroyed(inputQueue);
            this.mInputQueue.dispose();
            this.mInputQueueCallback = null;
            this.mInputQueue = null;
        }
        try {
            this.mWindowSession.remove(this.mWindow);
        } catch (RemoteException e) {
            String str = this.mTag;
            Log.e(str, "RemoteException remove window " + this.mWindow + " in " + this, e);
        }
        WindowInputEventReceiver windowInputEventReceiver = this.mInputEventReceiver;
        if (windowInputEventReceiver != null) {
            windowInputEventReceiver.dispose();
            this.mInputEventReceiver = null;
            this.mInputEventReceiverExt = null;
        }
        this.mDisplayManager.unregisterDisplayListener(this.mDisplayListener);
        unscheduleTraversals();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performConfigurationChange(MergedConfiguration mergedConfiguration, boolean force, int newDisplayId) {
        if (mergedConfiguration != null) {
            Configuration globalConfig = mergedConfiguration.getGlobalConfiguration();
            Configuration overrideConfig = mergedConfiguration.getOverrideConfiguration();
            if (DEBUG_CONFIGURATION) {
                Log.v(this.mTag, "Applying new config to window " + ((Object) this.mWindowAttributes.getTitle()) + ", globalConfig: " + globalConfig + ", overrideConfig: " + overrideConfig + ", force = " + force + ", this = " + this);
            }
            CompatibilityInfo ci = this.mDisplay.getDisplayAdjustments().getCompatibilityInfo();
            if (!ci.equals(CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO)) {
                globalConfig = new Configuration(globalConfig);
                ci.applyToConfiguration(this.mNoncompatDensity, globalConfig);
            }
            ArrayList<ConfigChangedCallback> arrayList = sConfigCallbacks;
            synchronized (arrayList) {
                for (int i = arrayList.size() - 1; i >= 0; i--) {
                    sConfigCallbacks.get(i).onConfigurationChanged(globalConfig);
                }
            }
            this.mViewRootImplExt.handleGestureConfigCheck();
            this.mViewRootImplExt.setDarkModeProgress(this.mView, globalConfig);
            this.mLastReportedMergedConfiguration.setConfiguration(globalConfig, overrideConfig);
            this.mViewRootImplExt.setLastReportedMergedConfiguration(this.mView, this.mLastReportedMergedConfiguration.getMergedConfiguration(), this.mContext);
            this.mForceNextConfigUpdate = force;
            ActivityConfigCallback activityConfigCallback = this.mActivityConfigCallback;
            if (activityConfigCallback != null) {
                activityConfigCallback.onConfigurationChanged(overrideConfig, newDisplayId);
            } else {
                updateConfiguration(newDisplayId);
            }
            this.mForceNextConfigUpdate = false;
            return;
        }
        throw new IllegalArgumentException("No merged config provided.");
    }

    public void updateConfiguration(int newDisplayId) {
        View view = this.mView;
        if (view != null) {
            Resources localResources = view.getResources();
            Configuration config = localResources.getConfiguration();
            if (newDisplayId != -1) {
                onMovedToDisplay(newDisplayId, config);
            }
            if (this.mForceNextConfigUpdate || this.mLastConfigurationFromResources.diff(config) != 0) {
                updateInternalDisplay(this.mDisplay.getDisplayId(), localResources);
                int lastLayoutDirection = this.mLastConfigurationFromResources.getLayoutDirection();
                int currentLayoutDirection = config.getLayoutDirection();
                this.mLastConfigurationFromResources.setTo(config);
                if (lastLayoutDirection != currentLayoutDirection && this.mViewLayoutDirectionInitial == 2) {
                    this.mView.setLayoutDirection(currentLayoutDirection);
                }
                this.mView.dispatchConfigurationChanged(config);
                this.mForceNextWindowRelayout = true;
                requestLayout();
            }
            updateForceDarkMode();
        }
    }

    public static boolean isViewDescendantOf(View child, View parent) {
        if (child == parent) {
            return true;
        }
        ViewParent theParent = child.getParent();
        return (theParent instanceof ViewGroup) && isViewDescendantOf((View) theParent, parent);
    }

    private static void forceLayout(View view) {
        view.forceLayout();
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            int count = group.getChildCount();
            for (int i = 0; i < count; i++) {
                if (group.getChildAt(i) != null) {
                    forceLayout(group.getChildAt(i));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public final class ViewRootHandler extends Handler {
        ViewRootHandler() {
        }

        @Override // android.os.Handler
        public String getMessageName(Message message) {
            switch (message.what) {
                case 1:
                    return "MSG_INVALIDATE";
                case 2:
                    return "MSG_INVALIDATE_RECT";
                case 3:
                    return "MSG_DIE";
                case 4:
                    return "MSG_RESIZED";
                case 5:
                    return "MSG_RESIZED_REPORT";
                case 6:
                    return "MSG_WINDOW_FOCUS_CHANGED";
                case 7:
                    return "MSG_DISPATCH_INPUT_EVENT";
                case 8:
                    return "MSG_DISPATCH_APP_VISIBILITY";
                case 9:
                    return "MSG_DISPATCH_GET_NEW_SURFACE";
                case 10:
                case 20:
                case 22:
                case 26:
                default:
                    return super.getMessageName(message);
                case 11:
                    return "MSG_DISPATCH_KEY_FROM_IME";
                case 12:
                    return "MSG_DISPATCH_KEY_FROM_AUTOFILL";
                case 13:
                    return "MSG_CHECK_FOCUS";
                case 14:
                    return "MSG_CLOSE_SYSTEM_DIALOGS";
                case 15:
                    return "MSG_DISPATCH_DRAG_EVENT";
                case 16:
                    return "MSG_DISPATCH_DRAG_LOCATION_EVENT";
                case 17:
                    return "MSG_DISPATCH_SYSTEM_UI_VISIBILITY";
                case 18:
                    return "MSG_UPDATE_CONFIGURATION";
                case 19:
                    return "MSG_PROCESS_INPUT_EVENTS";
                case 21:
                    return "MSG_CLEAR_ACCESSIBILITY_FOCUS_HOST";
                case 23:
                    return "MSG_WINDOW_MOVED";
                case 24:
                    return "MSG_SYNTHESIZE_INPUT_EVENT";
                case 25:
                    return "MSG_DISPATCH_WINDOW_SHOWN";
                case 27:
                    return "MSG_UPDATE_POINTER_ICON";
                case 28:
                    return "MSG_POINTER_CAPTURE_CHANGED";
                case 29:
                    return "MSG_DRAW_FINISHED";
                case 30:
                    return "MSG_INSETS_CHANGED";
                case 31:
                    return "MSG_INSETS_CONTROL_CHANGED";
                case 32:
                    return "MSG_SYSTEM_GESTURE_EXCLUSION_CHANGED";
                case 33:
                    return "MSG_LOCATION_IN_PARENT_DISPLAY_CHANGED";
                case 34:
                    return "MSG_SHOW_INSETS";
                case 35:
                    return "MSG_HIDE_INSETS";
            }
        }

        @Override // android.os.Handler
        public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
            if (msg.what != 26 || msg.obj != null) {
                return super.sendMessageAtTime(msg, uptimeMillis);
            }
            throw new NullPointerException("Attempted to call MSG_REQUEST_KEYBOARD_SHORTCUTS with null receiver:");
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (Trace.isTagEnabled(8L)) {
                Trace.traceBegin(8L, getMessageName(msg));
            }
            try {
                handleMessageImpl(msg);
            } finally {
                Trace.traceEnd(8L);
            }
        }

        private void handleMessageImpl(Message msg) {
            boolean z = false;
            boolean z2 = false;
            boolean z3 = false;
            boolean z4 = false;
            boolean hasCapture = false;
            boolean z5 = true;
            switch (msg.what) {
                case 1:
                    ((View) msg.obj).invalidate();
                    return;
                case 2:
                    View.AttachInfo.InvalidateInfo info = (View.AttachInfo.InvalidateInfo) msg.obj;
                    info.target.invalidate(info.left, info.top, info.right, info.bottom);
                    info.recycle();
                    return;
                case 3:
                    ViewRootImpl.this.doDie();
                    return;
                case 4:
                case 5:
                    ViewRootImpl.this.mWillMove = false;
                    ViewRootImpl.this.mWillResize = false;
                    SomeArgs args = (SomeArgs) msg.obj;
                    ViewRootImpl.this.handleResized(msg.what, args);
                    if (ViewRootImpl.DEBUG_LAYOUT) {
                        Log.d(ViewRootImpl.this.mTag, "Handle RESIZE: message = " + msg.what + " ,this = " + ViewRootImpl.this);
                    }
                    args.recycle();
                    return;
                case 6:
                    ViewRootImpl.this.handleWindowFocusChanged();
                    return;
                case 7:
                    SomeArgs args2 = (SomeArgs) msg.obj;
                    InputEventReceiver receiver = (InputEventReceiver) args2.arg2;
                    ViewRootImpl.this.enqueueInputEvent((InputEvent) args2.arg1, receiver, 0, true);
                    args2.recycle();
                    return;
                case 8:
                    ViewRootImpl viewRootImpl = ViewRootImpl.this;
                    if (msg.arg1 != 0) {
                        z = true;
                    }
                    viewRootImpl.handleAppVisibility(z);
                    return;
                case 9:
                    ViewRootImpl.this.handleGetNewSurface();
                    return;
                case 10:
                case 20:
                default:
                    return;
                case 11:
                    if (ViewRootImpl.LOCAL_LOGV || ViewDebugManager.DEBUG_KEY) {
                        Log.v(ViewRootImpl.this.mTag, "Dispatching key " + msg.obj + " from IME to " + ViewRootImpl.this.mView + " in " + this);
                    }
                    KeyEvent event = (KeyEvent) msg.obj;
                    if ((event.getFlags() & 8) != 0) {
                        event = KeyEvent.changeFlags(event, event.getFlags() & (-9));
                    }
                    ViewRootImpl.this.enqueueInputEvent(event, null, 1, true);
                    return;
                case 12:
                    if (ViewRootImpl.LOCAL_LOGV) {
                        Log.v(ViewRootImpl.TAG, "Dispatching key " + msg.obj + " from Autofill to " + ViewRootImpl.this.mView);
                    }
                    ViewRootImpl.this.enqueueInputEvent((KeyEvent) msg.obj, null, 0, true);
                    return;
                case 13:
                    ViewRootImpl.this.getImeFocusController().checkFocus(false, true);
                    return;
                case 14:
                    if (ViewRootImpl.this.mView != null) {
                        ViewRootImpl.this.mView.onCloseSystemDialogs((String) msg.obj);
                        return;
                    }
                    return;
                case 15:
                case 16:
                    DragEvent event2 = (DragEvent) msg.obj;
                    event2.mLocalState = ViewRootImpl.this.mLocalDragState;
                    ViewRootImpl.this.handleDragEvent(event2);
                    return;
                case 17:
                    ViewRootImpl.this.handleDispatchSystemUiVisibilityChanged((SystemUiVisibilityInfo) msg.obj);
                    return;
                case 18:
                    Configuration config = (Configuration) msg.obj;
                    if (config.isOtherSeqNewer(ViewRootImpl.this.mLastReportedMergedConfiguration.getMergedConfiguration())) {
                        config = ViewRootImpl.this.mLastReportedMergedConfiguration.getGlobalConfiguration();
                    }
                    ViewRootImpl.this.mPendingMergedConfiguration.setConfiguration(config, ViewRootImpl.this.mLastReportedMergedConfiguration.getOverrideConfiguration());
                    ViewRootImpl.this.performConfigurationChange(new MergedConfiguration(ViewRootImpl.this.mPendingMergedConfiguration), false, -1);
                    return;
                case 19:
                    ViewRootImpl.this.mProcessInputEventsScheduled = false;
                    ViewRootImpl.this.doProcessInputEvents();
                    return;
                case 21:
                    ViewRootImpl.this.setAccessibilityFocus(null, null);
                    return;
                case 22:
                    if (ViewRootImpl.this.mView != null) {
                        ViewRootImpl viewRootImpl2 = ViewRootImpl.this;
                        viewRootImpl2.invalidateWorld(viewRootImpl2.mView);
                        return;
                    }
                    return;
                case 23:
                    ViewRootImpl.this.mWillMove = false;
                    if (ViewRootImpl.this.mAdded) {
                        int w = ViewRootImpl.this.mWinFrame.width();
                        int h = ViewRootImpl.this.mWinFrame.height();
                        int l = msg.arg1;
                        int t = msg.arg2;
                        ViewRootImpl.this.mTmpFrames.frame.left = l;
                        ViewRootImpl.this.mTmpFrames.frame.right = l + w;
                        ViewRootImpl.this.mTmpFrames.frame.top = t;
                        ViewRootImpl.this.mTmpFrames.frame.bottom = t + h;
                        ViewRootImpl viewRootImpl3 = ViewRootImpl.this;
                        viewRootImpl3.setFrame(viewRootImpl3.mTmpFrames.frame);
                        ViewRootImpl.this.mPendingBackDropFrame.set(ViewRootImpl.this.mWinFrame);
                        ViewRootImpl viewRootImpl4 = ViewRootImpl.this;
                        viewRootImpl4.maybeHandleWindowMove(viewRootImpl4.mWinFrame);
                        return;
                    }
                    return;
                case 24:
                    ViewRootImpl.this.enqueueInputEvent((InputEvent) msg.obj, null, 32, true);
                    return;
                case 25:
                    ViewRootImpl.this.handleDispatchWindowShown();
                    ViewRootImpl.this.mViewRootImplExt.checkKeyguardAndConfig(ViewRootImpl.this.mTag);
                    return;
                case 26:
                    IResultReceiver receiver2 = (IResultReceiver) msg.obj;
                    int deviceId = msg.arg1;
                    ViewRootImpl.this.handleRequestKeyboardShortcuts(receiver2, deviceId);
                    return;
                case 27:
                    ViewRootImpl.this.resetPointerIcon((MotionEvent) msg.obj);
                    return;
                case 28:
                    if (msg.arg1 != 0) {
                        hasCapture = true;
                    }
                    ViewRootImpl.this.handlePointerCaptureChanged(hasCapture);
                    return;
                case 29:
                    ViewRootImpl.this.pendingDrawFinished();
                    return;
                case 30:
                    SomeArgs args3 = (SomeArgs) msg.obj;
                    ViewRootImpl.this.mWillMove = args3.argi1 == 1;
                    ViewRootImpl viewRootImpl5 = ViewRootImpl.this;
                    if (args3.argi2 == 1) {
                        z4 = true;
                    }
                    viewRootImpl5.mWillResize = z4;
                    ViewRootImpl.this.mInsetsController.onStateChanged((InsetsState) args3.arg1);
                    args3.recycle();
                    return;
                case 31:
                    SomeArgs args4 = (SomeArgs) msg.obj;
                    ViewRootImpl.this.mWillMove = args4.argi1 == 1;
                    ViewRootImpl viewRootImpl6 = ViewRootImpl.this;
                    if (args4.argi2 != 1) {
                        z5 = false;
                    }
                    viewRootImpl6.mWillResize = z5;
                    ViewRootImpl.this.mInsetsController.onStateChanged((InsetsState) args4.arg1);
                    InsetsSourceControl[] controls = (InsetsSourceControl[]) args4.arg2;
                    if (ViewRootImpl.this.mAdded) {
                        ViewRootImpl.this.mInsetsController.onControlsChanged(controls);
                    } else if (controls != null) {
                        for (InsetsSourceControl control : controls) {
                            if (control != null) {
                                control.release(InsetsAnimationThreadControlRunner$$ExternalSyntheticLambda2.INSTANCE);
                            }
                        }
                    }
                    args4.recycle();
                    return;
                case 32:
                    ViewRootImpl.this.systemGestureExclusionChanged();
                    return;
                case 33:
                    ViewRootImpl.this.updateLocationInParentDisplay(msg.arg1, msg.arg2);
                    return;
                case 34:
                    if (ViewRootImpl.this.mView == null) {
                        Object[] objArr = new Object[2];
                        objArr[0] = Integer.valueOf(msg.arg1);
                        objArr[1] = Boolean.valueOf(msg.arg2 == 1);
                        Log.e(ViewRootImpl.TAG, String.format("Calling showInsets(%d,%b) on window that no longer has views.", objArr));
                    }
                    ViewRootImpl.this.clearLowProfileModeIfNeeded(msg.arg1, msg.arg2 == 1);
                    InsetsController insetsController = ViewRootImpl.this.mInsetsController;
                    int i = msg.arg1;
                    if (msg.arg2 == 1) {
                        z3 = true;
                    }
                    insetsController.show(i, z3);
                    return;
                case 35:
                    InsetsController insetsController2 = ViewRootImpl.this.mInsetsController;
                    int i2 = msg.arg1;
                    if (msg.arg2 == 1) {
                        z2 = true;
                    }
                    insetsController2.hide(i2, z2);
                    return;
                case 36:
                    ViewRootImpl.this.handleScrollCaptureRequest((IScrollCaptureResponseListener) msg.obj);
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean ensureTouchMode(boolean inTouchMode) {
        if (DBG) {
            Log.d("touchmode", "ensureTouchMode(" + inTouchMode + "), current touch mode is " + this.mAttachInfo.mInTouchMode);
        }
        if (this.mAttachInfo.mInTouchMode == inTouchMode) {
            return false;
        }
        try {
            this.mWindowSession.setInTouchMode(inTouchMode);
            return ensureTouchModeLocally(inTouchMode);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean ensureTouchModeLocally(boolean inTouchMode) {
        if (DBG) {
            Log.d("touchmode", "ensureTouchModeLocally(" + inTouchMode + "), current touch mode is " + this.mAttachInfo.mInTouchMode);
        }
        if (this.mAttachInfo.mInTouchMode == inTouchMode) {
            return false;
        }
        this.mAttachInfo.mInTouchMode = inTouchMode;
        this.mAttachInfo.mTreeObserver.dispatchOnTouchModeChanged(inTouchMode);
        return inTouchMode ? enterTouchMode() : leaveTouchMode();
    }

    private boolean enterTouchMode() {
        View focused;
        View view = this.mView;
        if (view == null || !view.hasFocus() || (focused = this.mView.findFocus()) == null || focused.isFocusableInTouchMode()) {
            return false;
        }
        ViewGroup ancestorToTakeFocus = findAncestorToTakeFocusInTouchMode(focused);
        if (ancestorToTakeFocus != null) {
            return ancestorToTakeFocus.requestFocus();
        }
        focused.clearFocusInternal(null, true, false);
        return true;
    }

    private static ViewGroup findAncestorToTakeFocusInTouchMode(View focused) {
        ViewParent parent = focused.getParent();
        while (parent instanceof ViewGroup) {
            ViewGroup vgParent = (ViewGroup) parent;
            if (vgParent.getDescendantFocusability() == 262144 && vgParent.isFocusableInTouchMode()) {
                return vgParent;
            }
            if (vgParent.isRootNamespace()) {
                return null;
            }
            parent = vgParent.getParent();
        }
        return null;
    }

    private boolean leaveTouchMode() {
        View view = this.mView;
        if (view == null) {
            return false;
        }
        if (view.hasFocus()) {
            View focusedView = this.mView.findFocus();
            if (!(focusedView instanceof ViewGroup) || ((ViewGroup) focusedView).getDescendantFocusability() != 262144) {
                return false;
            }
        }
        return this.mView.restoreDefaultFocus();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public abstract class InputStage {
        protected static final int FINISH_HANDLED = 1;
        protected static final int FINISH_NOT_HANDLED = 2;
        protected static final int FORWARD = 0;
        private final InputStage mNext;
        private String mTracePrefix;

        public InputStage(InputStage next) {
            this.mNext = next;
        }

        /* JADX WARN: Finally extract failed */
        public final void deliver(QueuedInputEvent q) {
            ViewDebugManager.getInstance().debugInputStageDeliverd(this, System.currentTimeMillis());
            if ((q.mFlags & 4) != 0) {
                forward(q);
            } else if (shouldDropInputEvent(q)) {
                finish(q, false);
            } else {
                traceEvent(q, 8L);
                try {
                    int result = onProcess(q);
                    Trace.traceEnd(8L);
                    apply(q, result);
                } catch (Throwable th) {
                    Trace.traceEnd(8L);
                    throw th;
                }
            }
        }

        protected void finish(QueuedInputEvent q, boolean handled) {
            q.mFlags |= 4;
            if (handled) {
                q.mFlags |= 8;
            }
            forward(q);
        }

        protected void forward(QueuedInputEvent q) {
            onDeliverToNext(q);
        }

        protected void apply(QueuedInputEvent q, int result) {
            if (result == 0) {
                forward(q);
            } else if (result == 1) {
                finish(q, true);
            } else if (result == 2) {
                finish(q, false);
            } else {
                throw new IllegalArgumentException("Invalid result: " + result);
            }
        }

        protected int onProcess(QueuedInputEvent q) {
            return 0;
        }

        protected void onDeliverToNext(QueuedInputEvent q) {
            ViewRootImpl.this.mViewRootImplExt.debugInputStageDeliverd(ViewRootImpl.this.mTag, q.mFlags, q.mEvent, getClass().getSimpleName(), (ViewRootImpl.DEBUG_INPUT_STAGES || ViewRootImpl.this.mViewRootImplExt.isLevelDebug()) ? q.toString() : null);
            if (ViewRootImpl.DEBUG_INPUT_STAGES) {
                String str = ViewRootImpl.this.mTag;
                Log.v(str, "Done with " + getClass().getSimpleName() + ". " + q);
            }
            InputStage inputStage = this.mNext;
            if (inputStage != null) {
                inputStage.deliver(q);
            } else {
                ViewRootImpl.this.finishInputEvent(q);
            }
        }

        protected void onWindowFocusChanged(boolean hasWindowFocus) {
            ViewRootImpl.this.mViewRootImplExt.onWindowFocusChangedByRoot(hasWindowFocus, ViewRootImpl.this.mView, ViewRootImpl.this.mLastReportedMergedConfiguration);
            InputStage inputStage = this.mNext;
            if (inputStage != null) {
                inputStage.onWindowFocusChanged(hasWindowFocus);
            }
        }

        protected void onDetachedFromWindow() {
            InputStage inputStage = this.mNext;
            if (inputStage != null) {
                inputStage.onDetachedFromWindow();
            }
        }

        protected boolean shouldDropInputEvent(QueuedInputEvent q) {
            String reason;
            if (ViewRootImpl.this.mView == null || !ViewRootImpl.this.mAdded) {
                String str = ViewRootImpl.this.mTag;
                Slog.w(str, "Dropping event due to root view being removed: " + q.mEvent);
                return true;
            }
            if (!ViewRootImpl.this.mAttachInfo.mHasWindowFocus && !q.mEvent.isFromSource(2) && !ViewRootImpl.this.isAutofillUiShowing()) {
                reason = "no window focus";
            } else if (ViewRootImpl.this.mStopped) {
                reason = "window is stopped";
            } else if (ViewRootImpl.this.mIsAmbientMode && !q.mEvent.isFromSource(1)) {
                reason = "non-button event in ambient mode";
            } else if (!ViewRootImpl.this.mPausedForTransition || isBack(q.mEvent)) {
                return false;
            } else {
                reason = "paused for transition";
            }
            if (ViewRootImpl.isTerminalInputEvent(q.mEvent)) {
                q.mEvent.cancel();
                String str2 = ViewRootImpl.this.mTag;
                Slog.w(str2, "Cancelling event (" + reason + "):" + q.mEvent);
                return false;
            }
            String str3 = ViewRootImpl.this.mTag;
            Slog.w(str3, "Dropping event (" + reason + "):" + q.mEvent);
            return true;
        }

        void dump(String prefix, PrintWriter writer) {
            InputStage inputStage = this.mNext;
            if (inputStage != null) {
                inputStage.dump(prefix, writer);
            }
        }

        private boolean isBack(InputEvent event) {
            return (event instanceof KeyEvent) && ((KeyEvent) event).getKeyCode() == 4;
        }

        private void traceEvent(QueuedInputEvent q, long traceTag) {
            if (Trace.isTagEnabled(traceTag)) {
                if (this.mTracePrefix == null) {
                    this.mTracePrefix = getClass().getSimpleName();
                }
                Trace.traceBegin(traceTag, this.mTracePrefix + " id=0x" + Integer.toHexString(q.mEvent.getId()));
            }
        }
    }

    /* loaded from: classes3.dex */
    abstract class AsyncInputStage extends InputStage {
        protected static final int DEFER = 3;
        private QueuedInputEvent mQueueHead;
        private int mQueueLength;
        private QueuedInputEvent mQueueTail;
        private final String mTraceCounter;

        public AsyncInputStage(InputStage next, String traceCounter) {
            super(next);
            this.mTraceCounter = traceCounter;
        }

        protected void defer(QueuedInputEvent q) {
            q.mFlags |= 2;
            enqueue(q);
        }

        @Override // android.view.ViewRootImpl.InputStage
        protected void forward(QueuedInputEvent q) {
            q.mFlags &= -3;
            QueuedInputEvent curr = this.mQueueHead;
            if (curr == null) {
                super.forward(q);
                return;
            }
            int deviceId = q.mEvent.getDeviceId();
            QueuedInputEvent prev = null;
            boolean blocked = false;
            while (curr != null && curr != q) {
                if (!blocked && deviceId == curr.mEvent.getDeviceId()) {
                    blocked = true;
                }
                prev = curr;
                curr = curr.mNext;
            }
            if (!blocked) {
                if (curr != null) {
                    curr = curr.mNext;
                    dequeue(q, prev);
                }
                super.forward(q);
                while (curr != null) {
                    if (deviceId != curr.mEvent.getDeviceId()) {
                        prev = curr;
                        curr = curr.mNext;
                    } else if ((curr.mFlags & 2) == 0) {
                        QueuedInputEvent next = curr.mNext;
                        dequeue(curr, prev);
                        super.forward(curr);
                        curr = next;
                    } else {
                        return;
                    }
                }
            } else if (curr == null) {
                enqueue(q);
            }
        }

        @Override // android.view.ViewRootImpl.InputStage
        protected void apply(QueuedInputEvent q, int result) {
            if (result == 3) {
                defer(q);
            } else {
                super.apply(q, result);
            }
        }

        private void enqueue(QueuedInputEvent q) {
            QueuedInputEvent queuedInputEvent = this.mQueueTail;
            if (queuedInputEvent == null) {
                this.mQueueHead = q;
                this.mQueueTail = q;
            } else {
                queuedInputEvent.mNext = q;
                this.mQueueTail = q;
            }
            int i = this.mQueueLength + 1;
            this.mQueueLength = i;
            Trace.traceCounter(4L, this.mTraceCounter, i);
        }

        private void dequeue(QueuedInputEvent q, QueuedInputEvent prev) {
            if (prev == null) {
                this.mQueueHead = q.mNext;
            } else {
                prev.mNext = q.mNext;
            }
            if (this.mQueueTail == q) {
                this.mQueueTail = prev;
            }
            q.mNext = null;
            int i = this.mQueueLength - 1;
            this.mQueueLength = i;
            Trace.traceCounter(4L, this.mTraceCounter, i);
        }

        @Override // android.view.ViewRootImpl.InputStage
        void dump(String prefix, PrintWriter writer) {
            writer.print(prefix);
            writer.print(getClass().getName());
            writer.print(": mQueueLength=");
            writer.println(this.mQueueLength);
            super.dump(prefix, writer);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public final class NativePreImeInputStage extends AsyncInputStage implements InputQueue.FinishedInputEventCallback {
        public NativePreImeInputStage(InputStage next, String traceCounter) {
            super(next, traceCounter);
        }

        @Override // android.view.ViewRootImpl.InputStage
        protected int onProcess(QueuedInputEvent q) {
            if (ViewRootImpl.this.mInputQueue == null || !(q.mEvent instanceof KeyEvent)) {
                return 0;
            }
            ViewRootImpl.this.mInputQueue.sendInputEvent(q.mEvent, q, true, this);
            return 3;
        }

        @Override // android.view.InputQueue.FinishedInputEventCallback
        public void onFinishedInputEvent(Object token, boolean handled) {
            QueuedInputEvent q = (QueuedInputEvent) token;
            if (handled) {
                finish(q, true);
            } else {
                forward(q);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public final class ViewPreImeInputStage extends InputStage {
        public ViewPreImeInputStage(InputStage next) {
            super(next);
        }

        @Override // android.view.ViewRootImpl.InputStage
        protected int onProcess(QueuedInputEvent q) {
            if (q.mEvent instanceof KeyEvent) {
                return processKeyEvent(q);
            }
            return 0;
        }

        private int processKeyEvent(QueuedInputEvent q) {
            KeyEvent event = (KeyEvent) q.mEvent;
            if (ViewRootImpl.this.mView.dispatchKeyEventPreIme(event)) {
                return 1;
            }
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public final class ImeInputStage extends AsyncInputStage implements InputMethodManager.FinishedInputEventCallback {
        public ImeInputStage(InputStage next, String traceCounter) {
            super(next, traceCounter);
        }

        @Override // android.view.ViewRootImpl.InputStage
        protected int onProcess(QueuedInputEvent q) {
            int result = ViewRootImpl.this.mImeFocusController.onProcessImeInputStage(q, q.mEvent, ViewRootImpl.this.mWindowAttributes, this);
            switch (result) {
                case -1:
                    return 3;
                case 0:
                    return 0;
                case 1:
                    return 1;
                default:
                    throw new IllegalStateException("Unexpected result=" + result);
            }
        }

        @Override // android.view.inputmethod.InputMethodManager.FinishedInputEventCallback
        public void onFinishedInputEvent(Object token, boolean handled) {
            QueuedInputEvent q = (QueuedInputEvent) token;
            if (ViewRootImpl.DEBUG_IMF || ViewDebugManager.DEBUG_INPUT || ViewDebugManager.DEBUG_KEY) {
                String str = ViewRootImpl.this.mTag;
                Log.d(str, "IME finishedEvent: handled = " + handled + ", event = " + q + ", viewAncestor = " + this);
            }
            if (handled) {
                finish(q, true);
            } else {
                forward(q);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public final class EarlyPostImeInputStage extends InputStage {
        public EarlyPostImeInputStage(InputStage next) {
            super(next);
        }

        @Override // android.view.ViewRootImpl.InputStage
        protected int onProcess(QueuedInputEvent q) {
            if (q.mEvent instanceof KeyEvent) {
                return processKeyEvent(q);
            }
            if (q.mEvent instanceof MotionEvent) {
                return processMotionEvent(q);
            }
            return 0;
        }

        private int processKeyEvent(QueuedInputEvent q) {
            KeyEvent event = (KeyEvent) q.mEvent;
            if (ViewRootImpl.this.mAttachInfo.mTooltipHost != null) {
                ViewRootImpl.this.mAttachInfo.mTooltipHost.handleTooltipKey(event);
            }
            if (ViewRootImpl.this.checkForLeavingTouchModeAndConsume(event)) {
                return 1;
            }
            ViewRootImpl.this.mFallbackEventHandler.preDispatchKeyEvent(event);
            return 0;
        }

        private int processMotionEvent(QueuedInputEvent q) {
            MotionEvent event = (MotionEvent) q.mEvent;
            if (event.isFromSource(2)) {
                return processPointerEvent(q);
            }
            int action = event.getActionMasked();
            if ((action == 0 || action == 8) && event.isFromSource(8)) {
                ViewRootImpl.this.ensureTouchMode(false);
            }
            return 0;
        }

        private int processPointerEvent(QueuedInputEvent q) {
            AutofillManager afm;
            MotionEvent event = (MotionEvent) q.mEvent;
            if (ViewRootImpl.this.mTranslator != null) {
                ViewRootImpl.this.mTranslator.translateEventInScreenToAppWindow(event);
            }
            int action = event.getAction();
            if (action == 0 || action == 8) {
                ViewRootImpl.this.ensureTouchMode(true);
            }
            if (action == 0 && (afm = ViewRootImpl.this.getAutofillManager()) != null) {
                afm.requestHideFillUi();
            }
            if (action == 0 && ViewRootImpl.this.mAttachInfo.mTooltipHost != null) {
                ViewRootImpl.this.mAttachInfo.mTooltipHost.hideTooltip();
            }
            if (ViewRootImpl.this.mCurScrollY != 0) {
                event.offsetLocation(0.0f, ViewRootImpl.this.mCurScrollY);
            }
            if (!event.isTouchEvent()) {
                return 0;
            }
            ViewRootImpl.this.mLastTouchPoint.x = event.getRawX();
            ViewRootImpl.this.mLastTouchPoint.y = event.getRawY();
            ViewRootImpl.this.mLastTouchSource = event.getSource();
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public final class NativePostImeInputStage extends AsyncInputStage implements InputQueue.FinishedInputEventCallback {
        public NativePostImeInputStage(InputStage next, String traceCounter) {
            super(next, traceCounter);
        }

        @Override // android.view.ViewRootImpl.InputStage
        protected int onProcess(QueuedInputEvent q) {
            if (ViewRootImpl.this.mInputQueue == null) {
                return 0;
            }
            ViewRootImpl.this.mInputQueue.sendInputEvent(q.mEvent, q, false, this);
            return 3;
        }

        @Override // android.view.InputQueue.FinishedInputEventCallback
        public void onFinishedInputEvent(Object token, boolean handled) {
            QueuedInputEvent q = (QueuedInputEvent) token;
            if (handled) {
                finish(q, true);
            } else {
                forward(q);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public final class ViewPostImeInputStage extends InputStage {
        public ViewPostImeInputStage(InputStage next) {
            super(next);
        }

        @Override // android.view.ViewRootImpl.InputStage
        protected int onProcess(QueuedInputEvent q) {
            if (q.mEvent instanceof KeyEvent) {
                return processKeyEvent(q);
            }
            int source = q.mEvent.getSource();
            if ((source & 2) != 0) {
                return processPointerEvent(q);
            }
            if ((source & 4) != 0) {
                return processTrackballEvent(q);
            }
            return processGenericMotionEvent(q);
        }

        @Override // android.view.ViewRootImpl.InputStage
        protected void onDeliverToNext(QueuedInputEvent q) {
            if (ViewRootImpl.this.mUnbufferedInputDispatch && (q.mEvent instanceof MotionEvent) && ((MotionEvent) q.mEvent).isTouchEvent() && ViewRootImpl.isTerminalInputEvent(q.mEvent)) {
                ViewRootImpl.this.mUnbufferedInputDispatch = false;
                ViewRootImpl.this.scheduleConsumeBatchedInput();
            }
            super.onDeliverToNext(q);
        }

        private boolean performFocusNavigation(KeyEvent event) {
            int direction = 0;
            switch (event.getKeyCode()) {
                case 19:
                    if (event.hasNoModifiers()) {
                        direction = 33;
                        break;
                    }
                    break;
                case 20:
                    if (event.hasNoModifiers()) {
                        direction = 130;
                        break;
                    }
                    break;
                case 21:
                    if (event.hasNoModifiers()) {
                        direction = 17;
                        break;
                    }
                    break;
                case 22:
                    if (event.hasNoModifiers()) {
                        direction = 66;
                        break;
                    }
                    break;
                case 61:
                    if (!event.hasNoModifiers()) {
                        if (event.hasModifiers(1)) {
                            direction = 1;
                            break;
                        }
                    } else {
                        direction = 2;
                        break;
                    }
                    break;
            }
            boolean isFastScrolling = false;
            if (direction != 0) {
                View focused = ViewRootImpl.this.mView.findFocus();
                if (focused != null) {
                    View v = focused.focusSearch(direction);
                    if (!(v == null || v == focused)) {
                        focused.getFocusedRect(ViewRootImpl.this.mTempRect);
                        if (ViewRootImpl.this.mView instanceof ViewGroup) {
                            ((ViewGroup) ViewRootImpl.this.mView).offsetDescendantRectToMyCoords(focused, ViewRootImpl.this.mTempRect);
                            ((ViewGroup) ViewRootImpl.this.mView).offsetRectIntoDescendantCoords(v, ViewRootImpl.this.mTempRect);
                        }
                        if (v.requestFocus(direction, ViewRootImpl.this.mTempRect)) {
                            if (event.getRepeatCount() > 0) {
                                isFastScrolling = true;
                            }
                            ViewRootImpl.this.playSoundEffect(SoundEffectConstants.getConstantForFocusDirection(direction, isFastScrolling));
                            return true;
                        }
                    }
                    if (ViewRootImpl.this.mView.dispatchUnhandledMove(focused, direction)) {
                        return true;
                    }
                } else if (ViewRootImpl.this.mView.restoreDefaultFocus()) {
                    return true;
                }
            }
            return false;
        }

        private boolean performKeyboardGroupNavigation(int direction) {
            View cluster;
            View focused = ViewRootImpl.this.mView.findFocus();
            if (focused == null && ViewRootImpl.this.mView.restoreDefaultFocus()) {
                return true;
            }
            if (focused == null) {
                cluster = ViewRootImpl.this.keyboardNavigationClusterSearch(null, direction);
            } else {
                cluster = focused.keyboardNavigationClusterSearch(null, direction);
            }
            int realDirection = direction;
            if (direction == 2 || direction == 1) {
                realDirection = 130;
            }
            if (cluster != null && cluster.isRootNamespace()) {
                if (cluster.restoreFocusNotInCluster()) {
                    ViewRootImpl.this.playSoundEffect(SoundEffectConstants.getContantForFocusDirection(direction));
                    return true;
                }
                cluster = ViewRootImpl.this.keyboardNavigationClusterSearch(null, direction);
            }
            if (cluster == null || !cluster.restoreFocusInCluster(realDirection)) {
                return false;
            }
            ViewRootImpl.this.playSoundEffect(SoundEffectConstants.getContantForFocusDirection(direction));
            return true;
        }

        private int processKeyEvent(QueuedInputEvent q) {
            KeyEvent event = (KeyEvent) q.mEvent;
            if (ViewRootImpl.this.mUnhandledKeyManager.preViewDispatch(event)) {
                IViewRootImplExt iViewRootImplExt = ViewRootImpl.this.mViewRootImplExt;
                String str = ViewRootImpl.this.mTag;
                iViewRootImplExt.v(str, "App handle dispatchUnique event = " + event + ", mView = " + ViewRootImpl.this.mView + ", this = " + this);
                return 1;
            }
            boolean handled = ViewRootImpl.this.mView.dispatchKeyEvent(event);
            IViewRootImplExt iViewRootImplExt2 = ViewRootImpl.this.mViewRootImplExt;
            String str2 = ViewRootImpl.this.mTag;
            iViewRootImplExt2.debugEventHandled(str2, event, "mView handled=" + handled);
            if (handled) {
                return 1;
            }
            if (shouldDropInputEvent(q)) {
                return 2;
            }
            if (ViewRootImpl.this.mUnhandledKeyManager.dispatch(ViewRootImpl.this.mView, event)) {
                return 1;
            }
            int groupNavigationDirection = 0;
            if (event.getAction() == 0 && event.getKeyCode() == 61) {
                if (KeyEvent.metaStateHasModifiers(event.getMetaState(), 65536)) {
                    groupNavigationDirection = 2;
                } else if (KeyEvent.metaStateHasModifiers(event.getMetaState(), 65537)) {
                    groupNavigationDirection = 1;
                }
            }
            if (event.getAction() == 0 && !KeyEvent.metaStateHasNoModifiers(event.getMetaState()) && event.getRepeatCount() == 0 && !KeyEvent.isModifierKey(event.getKeyCode()) && groupNavigationDirection == 0) {
                if (ViewRootImpl.this.mView.dispatchKeyShortcutEvent(event)) {
                    return 1;
                }
                if (shouldDropInputEvent(q)) {
                    return 2;
                }
            }
            if (ViewRootImpl.this.mFallbackEventHandler.dispatchKeyEvent(event)) {
                return 1;
            }
            if (shouldDropInputEvent(q)) {
                return 2;
            }
            if (event.getAction() == 0) {
                return groupNavigationDirection != 0 ? performKeyboardGroupNavigation(groupNavigationDirection) ? 1 : 0 : performFocusNavigation(event) ? 1 : 0;
            }
            return 0;
        }

        private int processPointerEvent(QueuedInputEvent q) {
            MotionEvent event = (MotionEvent) q.mEvent;
            boolean isHandled = ViewRootImpl.this.mViewRootImplExt.processGestureEvent(event);
            if (isHandled) {
                IViewRootImplExt iViewRootImplExt = ViewRootImpl.this.mViewRootImplExt;
                String str = ViewRootImpl.this.mTag;
                iViewRootImplExt.debugEventHandled(str, event, "Navigation Gesture handled=" + isHandled);
                return 1;
            }
            ViewRootImpl.this.mAttachInfo.mUnbufferedDispatchRequested = false;
            ViewRootImpl.this.mAttachInfo.mHandlingPointerEvent = true;
            if (ViewRootImpl.this.mView == null) {
                return 1;
            }
            long appDispatchTime = System.nanoTime();
            boolean handled = ViewRootImpl.this.mView.dispatchPointerEvent(event);
            BoostFwkFactory.getInstance().makeBoostFwkManager().perfHint(new ScrollScenario(1, 0, event, ViewRootImpl.this.mContext));
            if (handled && ViewDebugManager.DEBUG_ENG) {
                String str2 = ViewRootImpl.this.mTag;
                Log.v(str2, "App handle pointer event: event = " + event + ", mView = " + ViewRootImpl.this.mView + ", this = " + this);
            }
            ViewRootImpl.this.mViewRootImplExt.processPointerEvent(event, ViewRootImpl.this.mContext);
            maybeUpdatePointerIcon(event);
            ViewRootImpl.this.maybeUpdateTooltip(event);
            ViewRootImpl.this.mAttachInfo.mHandlingPointerEvent = false;
            if (ViewRootImpl.this.mAttachInfo.mUnbufferedDispatchRequested && !ViewRootImpl.this.mUnbufferedInputDispatch) {
                ViewRootImpl.this.mUnbufferedInputDispatch = true;
                if (ViewRootImpl.this.mConsumeBatchedInputScheduled) {
                    ViewRootImpl.this.scheduleConsumeBatchedInputImmediately();
                }
            }
            long appHandleTime = System.nanoTime();
            IViewRootImplExt iViewRootImplExt2 = ViewRootImpl.this.mViewRootImplExt;
            String str3 = ViewRootImpl.this.mTag;
            iViewRootImplExt2.debugEventHandled(str3, event, "mView handled=" + handled);
            try {
                if (ViewRootImpl.this.mInputEventReceiverExt != null) {
                    ViewRootImpl.this.mInputEventReceiverExt.reportAppInputTimeline(q.mEvent, appDispatchTime, appHandleTime);
                }
            } catch (Exception e) {
                Log.e(ViewRootImpl.TAG, "Unable to report Null Event ", e);
            }
            return handled ? 1 : 0;
        }

        private void maybeUpdatePointerIcon(MotionEvent event) {
            if (event.getPointerCount() == 1 && event.isFromSource(8194)) {
                if (event.getActionMasked() == 9 || event.getActionMasked() == 10) {
                    ViewRootImpl.this.mPointerIconType = 1;
                }
                if (event.getActionMasked() != 10 && !ViewRootImpl.this.updatePointerIcon(event) && event.getActionMasked() == 7) {
                    ViewRootImpl.this.mPointerIconType = 1;
                }
            }
        }

        private int processTrackballEvent(QueuedInputEvent q) {
            MotionEvent event = (MotionEvent) q.mEvent;
            return ((!event.isFromSource(InputDevice.SOURCE_MOUSE_RELATIVE) || (ViewRootImpl.this.hasPointerCapture() && !ViewRootImpl.this.mView.dispatchCapturedPointerEvent(event))) && !ViewRootImpl.this.mView.dispatchTrackballEvent(event)) ? 0 : 1;
        }

        private int processGenericMotionEvent(QueuedInputEvent q) {
            MotionEvent event = (MotionEvent) q.mEvent;
            return ((!event.isFromSource(InputDevice.SOURCE_TOUCHPAD) || !ViewRootImpl.this.hasPointerCapture() || !ViewRootImpl.this.mView.dispatchCapturedPointerEvent(event)) && !ViewRootImpl.this.mView.dispatchGenericMotionEvent(event)) ? 0 : 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetPointerIcon(MotionEvent event) {
        this.mPointerIconType = 1;
        updatePointerIcon(event);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean updatePointerIcon(MotionEvent event) {
        float x = event.getX(0);
        float y = event.getY(0);
        View view = this.mView;
        if (view == null) {
            Slog.d(this.mTag, "updatePointerIcon called after view was removed");
            return false;
        } else if (x < 0.0f || x >= view.getWidth() || y < 0.0f || y >= this.mView.getHeight()) {
            Slog.d(this.mTag, "updatePointerIcon called with position out of bounds");
            return false;
        } else {
            PointerIcon pointerIcon = this.mView.onResolvePointerIcon(event, 0);
            int pointerType = pointerIcon != null ? pointerIcon.getType() : 1000;
            if (this.mPointerIconType != pointerType) {
                this.mPointerIconType = pointerType;
                this.mCustomPointerIcon = null;
                if (pointerType != -1) {
                    InputManager.getInstance().setPointerIconType(pointerType);
                    return true;
                }
            }
            if (this.mPointerIconType == -1 && !pointerIcon.equals(this.mCustomPointerIcon)) {
                this.mCustomPointerIcon = pointerIcon;
                InputManager.getInstance().setCustomPointerIcon(this.mCustomPointerIcon);
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeUpdateTooltip(MotionEvent event) {
        if (event.getPointerCount() == 1) {
            int action = event.getActionMasked();
            if (action == 9 || action == 7 || action == 10) {
                AccessibilityManager manager = AccessibilityManager.getInstance(this.mContext);
                if (!manager.isEnabled() || !manager.isTouchExplorationEnabled()) {
                    View view = this.mView;
                    if (view == null) {
                        Slog.d(this.mTag, "maybeUpdateTooltip called after view was removed");
                    } else {
                        view.dispatchTooltipHoverEvent(event);
                    }
                }
            }
        }
    }

    private View getFocusedViewOrNull() {
        View view = this.mView;
        if (view != null) {
            return view.findFocus();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public final class SyntheticInputStage extends InputStage {
        private final SyntheticJoystickHandler mJoystick;
        private final SyntheticKeyboardHandler mKeyboard;
        private final SyntheticTouchNavigationHandler mTouchNavigation;
        private final SyntheticTrackballHandler mTrackball;

        public SyntheticInputStage() {
            super(null);
            this.mTrackball = new SyntheticTrackballHandler();
            this.mJoystick = new SyntheticJoystickHandler();
            this.mTouchNavigation = new SyntheticTouchNavigationHandler();
            this.mKeyboard = new SyntheticKeyboardHandler();
        }

        @Override // android.view.ViewRootImpl.InputStage
        protected int onProcess(QueuedInputEvent q) {
            q.mFlags |= 16;
            if (q.mEvent instanceof MotionEvent) {
                MotionEvent event = (MotionEvent) q.mEvent;
                int source = event.getSource();
                if ((source & 4) != 0) {
                    this.mTrackball.process(event);
                    return 1;
                } else if ((source & 16) != 0) {
                    this.mJoystick.process(event);
                    return 1;
                } else if ((source & 2097152) != 2097152) {
                    return 0;
                } else {
                    this.mTouchNavigation.process(event);
                    return 1;
                }
            } else if ((q.mFlags & 32) == 0) {
                return 0;
            } else {
                this.mKeyboard.process((KeyEvent) q.mEvent);
                return 1;
            }
        }

        @Override // android.view.ViewRootImpl.InputStage
        protected void onDeliverToNext(QueuedInputEvent q) {
            if ((q.mFlags & 16) == 0 && (q.mEvent instanceof MotionEvent)) {
                MotionEvent event = (MotionEvent) q.mEvent;
                int source = event.getSource();
                if ((source & 4) != 0) {
                    this.mTrackball.cancel();
                } else if ((source & 16) != 0) {
                    this.mJoystick.cancel();
                } else if ((source & 2097152) == 2097152) {
                    this.mTouchNavigation.cancel(event);
                }
            }
            super.onDeliverToNext(q);
        }

        @Override // android.view.ViewRootImpl.InputStage
        protected void onWindowFocusChanged(boolean hasWindowFocus) {
            if (!hasWindowFocus) {
                this.mJoystick.cancel();
            }
        }

        @Override // android.view.ViewRootImpl.InputStage
        protected void onDetachedFromWindow() {
            this.mJoystick.cancel();
        }
    }

    /* loaded from: classes3.dex */
    final class SyntheticTrackballHandler {
        private long mLastTime;
        private final TrackballAxis mX = new TrackballAxis();
        private final TrackballAxis mY = new TrackballAxis();

        SyntheticTrackballHandler() {
        }

        public void process(MotionEvent event) {
            long curTime;
            int keycode;
            float accel;
            String str;
            int keycode2;
            long curTime2;
            long curTime3 = SystemClock.uptimeMillis();
            if (this.mLastTime + 250 < curTime3) {
                this.mX.reset(0);
                this.mY.reset(0);
                this.mLastTime = curTime3;
            }
            int action = event.getAction();
            int metaState = event.getMetaState();
            switch (action) {
                case 0:
                    curTime = curTime3;
                    this.mX.reset(2);
                    this.mY.reset(2);
                    ViewRootImpl.this.enqueueInputEvent(new KeyEvent(curTime, curTime, 0, 23, 0, metaState, -1, 0, 1024, 257));
                    break;
                case 1:
                    this.mX.reset(2);
                    this.mY.reset(2);
                    curTime = curTime3;
                    ViewRootImpl.this.enqueueInputEvent(new KeyEvent(curTime3, curTime3, 1, 23, 0, metaState, -1, 0, 1024, 257));
                    break;
                default:
                    curTime = curTime3;
                    break;
            }
            if (ViewRootImpl.DEBUG_TRACKBALL) {
                Log.v(ViewRootImpl.this.mTag, "TB X=" + this.mX.position + " step=" + this.mX.step + " dir=" + this.mX.dir + " acc=" + this.mX.acceleration + " move=" + event.getX() + " / Y=" + this.mY.position + " step=" + this.mY.step + " dir=" + this.mY.dir + " acc=" + this.mY.acceleration + " move=" + event.getY());
            }
            float xOff = this.mX.collect(event.getX(), event.getEventTime(), "X");
            float yOff = this.mY.collect(event.getY(), event.getEventTime(), "Y");
            int movement = 0;
            if (xOff > yOff) {
                movement = this.mX.generate();
                if (movement != 0) {
                    int keycode3 = movement > 0 ? 22 : 21;
                    float accel2 = this.mX.acceleration;
                    this.mY.reset(2);
                    keycode = keycode3;
                    accel = accel2;
                } else {
                    keycode = 0;
                    accel = 1.0f;
                }
            } else if (yOff > 0.0f) {
                movement = this.mY.generate();
                if (movement != 0) {
                    int keycode4 = movement > 0 ? 20 : 19;
                    float accel3 = this.mY.acceleration;
                    this.mX.reset(2);
                    keycode = keycode4;
                    accel = accel3;
                } else {
                    keycode = 0;
                    accel = 1.0f;
                }
            } else {
                keycode = 0;
                accel = 1.0f;
            }
            if (keycode != 0) {
                if (movement < 0) {
                    movement = -movement;
                }
                int accelMovement = (int) (movement * accel);
                if (ViewRootImpl.DEBUG_TRACKBALL) {
                    Log.v(ViewRootImpl.this.mTag, "Move: movement=" + movement + " accelMovement=" + accelMovement + " accel=" + accel);
                }
                if (accelMovement > movement) {
                    if (ViewRootImpl.DEBUG_TRACKBALL) {
                        Log.v(ViewRootImpl.this.mTag, "Delivering fake DPAD: " + keycode);
                    }
                    int movement2 = movement - 1;
                    int repeatCount = accelMovement - movement2;
                    str = "Delivering fake DPAD: ";
                    keycode2 = keycode;
                    ViewRootImpl.this.enqueueInputEvent(new KeyEvent(curTime, curTime, 2, keycode, repeatCount, metaState, -1, 0, 1024, 257));
                    curTime2 = curTime;
                    movement = movement2;
                } else {
                    str = "Delivering fake DPAD: ";
                    keycode2 = keycode;
                    curTime2 = curTime;
                }
                while (movement > 0) {
                    if (ViewRootImpl.DEBUG_TRACKBALL) {
                        String str2 = ViewRootImpl.this.mTag;
                        StringBuilder sb = new StringBuilder();
                        str = str;
                        sb.append(str);
                        sb.append(keycode2);
                        Log.v(str2, sb.toString());
                    } else {
                        str = str;
                    }
                    long curTime4 = SystemClock.uptimeMillis();
                    ViewRootImpl.this.enqueueInputEvent(new KeyEvent(curTime4, curTime4, 0, keycode2, 0, metaState, -1, 0, 1024, 257));
                    ViewRootImpl.this.enqueueInputEvent(new KeyEvent(curTime4, curTime4, 1, keycode2, 0, metaState, -1, 0, 1024, 257));
                    movement--;
                    curTime2 = curTime4;
                    keycode2 = keycode2;
                }
                this.mLastTime = curTime2;
            }
        }

        public void cancel() {
            this.mLastTime = -2147483648L;
            if (ViewRootImpl.this.mView != null && ViewRootImpl.this.mAdded) {
                ViewRootImpl.this.ensureTouchMode(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class TrackballAxis {
        static final float ACCEL_MOVE_SCALING_FACTOR = 0.025f;
        static final long FAST_MOVE_TIME = 150;
        static final float FIRST_MOVEMENT_THRESHOLD = 0.5f;
        static final float MAX_ACCELERATION = 20.0f;
        static final float SECOND_CUMULATIVE_MOVEMENT_THRESHOLD = 2.0f;
        static final float SUBSEQUENT_INCREMENTAL_MOVEMENT_THRESHOLD = 1.0f;
        int dir;
        int nonAccelMovement;
        float position;
        int step;
        float acceleration = 1.0f;
        long lastMoveTime = 0;

        TrackballAxis() {
        }

        void reset(int _step) {
            this.position = 0.0f;
            this.acceleration = 1.0f;
            this.lastMoveTime = 0L;
            this.step = _step;
            this.dir = 0;
        }

        float collect(float off, long time, String axis) {
            long normTime;
            if (off > 0.0f) {
                normTime = off * 150.0f;
                if (this.dir < 0) {
                    if (ViewRootImpl.DEBUG_TRACKBALL) {
                        Log.v(ViewRootImpl.TAG, axis + " reversed to positive!");
                    }
                    this.position = 0.0f;
                    this.step = 0;
                    this.acceleration = 1.0f;
                    this.lastMoveTime = 0L;
                }
                this.dir = 1;
            } else if (off < 0.0f) {
                normTime = (-off) * 150.0f;
                if (this.dir > 0) {
                    if (ViewRootImpl.DEBUG_TRACKBALL) {
                        Log.v(ViewRootImpl.TAG, axis + " reversed to negative!");
                    }
                    this.position = 0.0f;
                    this.step = 0;
                    this.acceleration = 1.0f;
                    this.lastMoveTime = 0L;
                }
                this.dir = -1;
            } else {
                normTime = 0;
            }
            if (normTime > 0) {
                long delta = time - this.lastMoveTime;
                this.lastMoveTime = time;
                float acc = this.acceleration;
                if (delta < normTime) {
                    float scale = ((float) (normTime - delta)) * ACCEL_MOVE_SCALING_FACTOR;
                    if (scale > 1.0f) {
                        acc *= scale;
                    }
                    if (ViewRootImpl.DEBUG_TRACKBALL) {
                        Log.v(ViewRootImpl.TAG, axis + " accelerate: off=" + off + " normTime=" + normTime + " delta=" + delta + " scale=" + scale + " acc=" + acc);
                    }
                    float f = MAX_ACCELERATION;
                    if (acc < MAX_ACCELERATION) {
                        f = acc;
                    }
                    this.acceleration = f;
                } else {
                    float scale2 = ((float) (delta - normTime)) * ACCEL_MOVE_SCALING_FACTOR;
                    if (scale2 > 1.0f) {
                        acc /= scale2;
                    }
                    if (ViewRootImpl.DEBUG_TRACKBALL) {
                        Log.v(ViewRootImpl.TAG, axis + " deccelerate: off=" + off + " normTime=" + normTime + " delta=" + delta + " scale=" + scale2 + " acc=" + acc);
                    }
                    this.acceleration = acc > 1.0f ? acc : 1.0f;
                }
            }
            float f2 = this.position + off;
            this.position = f2;
            return Math.abs(f2);
        }

        int generate() {
            int movement = 0;
            this.nonAccelMovement = 0;
            while (true) {
                float f = this.position;
                int dir = f >= 0.0f ? 1 : -1;
                switch (this.step) {
                    case 0:
                        if (Math.abs(f) >= 0.5f) {
                            movement += dir;
                            this.nonAccelMovement += dir;
                            this.step = 1;
                            break;
                        } else {
                            return movement;
                        }
                    case 1:
                        if (Math.abs(f) >= SECOND_CUMULATIVE_MOVEMENT_THRESHOLD) {
                            movement += dir;
                            this.nonAccelMovement += dir;
                            this.position -= dir * SECOND_CUMULATIVE_MOVEMENT_THRESHOLD;
                            this.step = 2;
                            break;
                        } else {
                            return movement;
                        }
                    default:
                        if (Math.abs(f) >= 1.0f) {
                            movement += dir;
                            this.position -= dir * 1.0f;
                            float acc = this.acceleration * 1.1f;
                            this.acceleration = acc < MAX_ACCELERATION ? acc : this.acceleration;
                            break;
                        } else {
                            return movement;
                        }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public final class SyntheticJoystickHandler extends Handler {
        private static final int MSG_ENQUEUE_X_AXIS_KEY_REPEAT = 1;
        private static final int MSG_ENQUEUE_Y_AXIS_KEY_REPEAT = 2;
        private final JoystickAxesState mJoystickAxesState = new JoystickAxesState();
        private final SparseArray<KeyEvent> mDeviceKeyEvents = new SparseArray<>();

        public SyntheticJoystickHandler() {
            super(true);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                case 2:
                    if (ViewRootImpl.this.mAttachInfo.mHasWindowFocus) {
                        KeyEvent oldEvent = (KeyEvent) msg.obj;
                        KeyEvent e = KeyEvent.changeTimeRepeat(oldEvent, SystemClock.uptimeMillis(), oldEvent.getRepeatCount() + 1);
                        ViewRootImpl.this.enqueueInputEvent(e);
                        Message m = obtainMessage(msg.what, e);
                        m.setAsynchronous(true);
                        sendMessageDelayed(m, ViewConfiguration.getKeyRepeatDelay());
                        return;
                    }
                    return;
                default:
                    return;
            }
        }

        public void process(MotionEvent event) {
            switch (event.getActionMasked()) {
                case 2:
                    update(event);
                    return;
                case 3:
                    cancel();
                    return;
                default:
                    String str = ViewRootImpl.this.mTag;
                    Log.w(str, "Unexpected action: " + event.getActionMasked());
                    return;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void cancel() {
            removeMessages(1);
            removeMessages(2);
            for (int i = 0; i < this.mDeviceKeyEvents.size(); i++) {
                KeyEvent keyEvent = this.mDeviceKeyEvents.valueAt(i);
                if (keyEvent != null) {
                    ViewRootImpl.this.enqueueInputEvent(KeyEvent.changeTimeRepeat(keyEvent, SystemClock.uptimeMillis(), 0));
                }
            }
            this.mDeviceKeyEvents.clear();
            this.mJoystickAxesState.resetState();
        }

        private void update(MotionEvent event) {
            int historySize = event.getHistorySize();
            for (int h = 0; h < historySize; h++) {
                long time = event.getHistoricalEventTime(h);
                this.mJoystickAxesState.updateStateForAxis(event, time, 0, event.getHistoricalAxisValue(0, 0, h));
                this.mJoystickAxesState.updateStateForAxis(event, time, 1, event.getHistoricalAxisValue(1, 0, h));
                this.mJoystickAxesState.updateStateForAxis(event, time, 15, event.getHistoricalAxisValue(15, 0, h));
                this.mJoystickAxesState.updateStateForAxis(event, time, 16, event.getHistoricalAxisValue(16, 0, h));
            }
            long time2 = event.getEventTime();
            this.mJoystickAxesState.updateStateForAxis(event, time2, 0, event.getAxisValue(0));
            this.mJoystickAxesState.updateStateForAxis(event, time2, 1, event.getAxisValue(1));
            this.mJoystickAxesState.updateStateForAxis(event, time2, 15, event.getAxisValue(15));
            this.mJoystickAxesState.updateStateForAxis(event, time2, 16, event.getAxisValue(16));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes3.dex */
        public final class JoystickAxesState {
            private static final int STATE_DOWN_OR_RIGHT = 1;
            private static final int STATE_NEUTRAL = 0;
            private static final int STATE_UP_OR_LEFT = -1;
            final int[] mAxisStatesHat = {0, 0};
            final int[] mAxisStatesStick = {0, 0};

            JoystickAxesState() {
            }

            void resetState() {
                int[] iArr = this.mAxisStatesHat;
                iArr[0] = 0;
                iArr[1] = 0;
                int[] iArr2 = this.mAxisStatesStick;
                iArr2[0] = 0;
                iArr2[1] = 0;
            }

            void updateStateForAxis(MotionEvent event, long time, int axis, float value) {
                int repeatMessage;
                int axisStateIndex;
                int currentState;
                int keyCode;
                if (isXAxis(axis)) {
                    axisStateIndex = 0;
                    repeatMessage = 1;
                } else if (isYAxis(axis)) {
                    axisStateIndex = 1;
                    repeatMessage = 2;
                } else {
                    String str = ViewRootImpl.this.mTag;
                    Log.e(str, "Unexpected axis " + axis + " in updateStateForAxis!");
                    return;
                }
                int newState = joystickAxisValueToState(value);
                if (axis == 0 || axis == 1) {
                    currentState = this.mAxisStatesStick[axisStateIndex];
                } else {
                    currentState = this.mAxisStatesHat[axisStateIndex];
                }
                if (currentState != newState) {
                    int metaState = event.getMetaState();
                    int deviceId = event.getDeviceId();
                    int source = event.getSource();
                    if (currentState == 1 || currentState == -1) {
                        int keyCode2 = joystickAxisAndStateToKeycode(axis, currentState);
                        if (keyCode2 != 0) {
                            ViewRootImpl.this.enqueueInputEvent(new KeyEvent(time, time, 1, keyCode2, 0, metaState, deviceId, 0, 1024, source));
                            deviceId = deviceId;
                            SyntheticJoystickHandler.this.mDeviceKeyEvents.put(deviceId, null);
                        }
                        SyntheticJoystickHandler.this.removeMessages(repeatMessage);
                    }
                    if ((newState == 1 || newState == -1) && (keyCode = joystickAxisAndStateToKeycode(axis, newState)) != 0) {
                        KeyEvent keyEvent = new KeyEvent(time, time, 0, keyCode, 0, metaState, deviceId, 0, 1024, source);
                        ViewRootImpl.this.enqueueInputEvent(keyEvent);
                        Message m = SyntheticJoystickHandler.this.obtainMessage(repeatMessage, keyEvent);
                        m.setAsynchronous(true);
                        SyntheticJoystickHandler.this.sendMessageDelayed(m, ViewConfiguration.getKeyRepeatTimeout());
                        SyntheticJoystickHandler.this.mDeviceKeyEvents.put(deviceId, new KeyEvent(time, time, 1, keyCode, 0, metaState, deviceId, 0, 1056, source));
                    }
                    if (axis == 0 || axis == 1) {
                        this.mAxisStatesStick[axisStateIndex] = newState;
                    } else {
                        this.mAxisStatesHat[axisStateIndex] = newState;
                    }
                }
            }

            private boolean isXAxis(int axis) {
                return axis == 0 || axis == 15;
            }

            private boolean isYAxis(int axis) {
                return axis == 1 || axis == 16;
            }

            private int joystickAxisAndStateToKeycode(int axis, int state) {
                if (isXAxis(axis) && state == -1) {
                    return 21;
                }
                if (isXAxis(axis) && state == 1) {
                    return 22;
                }
                if (isYAxis(axis) && state == -1) {
                    return 19;
                }
                if (isYAxis(axis) && state == 1) {
                    return 20;
                }
                String str = ViewRootImpl.this.mTag;
                Log.e(str, "Unknown axis " + axis + " or direction " + state);
                return 0;
            }

            private int joystickAxisValueToState(float value) {
                if (value >= 0.5f) {
                    return 1;
                }
                if (value <= -0.5f) {
                    return -1;
                }
                return 0;
            }
        }
    }

    /* loaded from: classes3.dex */
    final class SyntheticTouchNavigationHandler extends Handler {
        private static final float DEFAULT_HEIGHT_MILLIMETERS = 48.0f;
        private static final float DEFAULT_WIDTH_MILLIMETERS = 48.0f;
        private static final float FLING_TICK_DECAY = 0.8f;
        private static final boolean LOCAL_DEBUG = false;
        private static final String LOCAL_TAG = "SyntheticTouchNavigationHandler";
        private static final float MAX_FLING_VELOCITY_TICKS_PER_SECOND = 20.0f;
        private static final float MIN_FLING_VELOCITY_TICKS_PER_SECOND = 6.0f;
        private static final int TICK_DISTANCE_MILLIMETERS = 12;
        private float mAccumulatedX;
        private float mAccumulatedY;
        private float mConfigMaxFlingVelocity;
        private float mConfigMinFlingVelocity;
        private float mConfigTickDistance;
        private boolean mConsumedMovement;
        private boolean mCurrentDeviceSupported;
        private int mCurrentSource;
        private float mFlingVelocity;
        private boolean mFlinging;
        private float mLastX;
        private float mLastY;
        private long mPendingKeyDownTime;
        private int mPendingKeyMetaState;
        private int mPendingKeyRepeatCount;
        private float mStartX;
        private float mStartY;
        private VelocityTracker mVelocityTracker;
        private int mCurrentDeviceId = -1;
        private int mActivePointerId = -1;
        private int mPendingKeyCode = 0;
        private final Runnable mFlingRunnable = new Runnable() { // from class: android.view.ViewRootImpl.SyntheticTouchNavigationHandler.1
            @Override // java.lang.Runnable
            public void run() {
                long time = SystemClock.uptimeMillis();
                SyntheticTouchNavigationHandler syntheticTouchNavigationHandler = SyntheticTouchNavigationHandler.this;
                syntheticTouchNavigationHandler.sendKeyDownOrRepeat(time, syntheticTouchNavigationHandler.mPendingKeyCode, SyntheticTouchNavigationHandler.this.mPendingKeyMetaState);
                SyntheticTouchNavigationHandler.access$3532(SyntheticTouchNavigationHandler.this, 0.8f);
                if (!SyntheticTouchNavigationHandler.this.postFling(time)) {
                    SyntheticTouchNavigationHandler.this.mFlinging = false;
                    SyntheticTouchNavigationHandler.this.finishKeys(time);
                }
            }
        };

        static /* synthetic */ float access$3532(SyntheticTouchNavigationHandler x0, float x1) {
            float f = x0.mFlingVelocity * x1;
            x0.mFlingVelocity = f;
            return f;
        }

        public SyntheticTouchNavigationHandler() {
            super(true);
        }

        public void process(MotionEvent event) {
            long time = event.getEventTime();
            int deviceId = event.getDeviceId();
            int source = event.getSource();
            if (!(this.mCurrentDeviceId == deviceId && this.mCurrentSource == source)) {
                finishKeys(time);
                finishTracking(time);
                this.mCurrentDeviceId = deviceId;
                this.mCurrentSource = source;
                this.mCurrentDeviceSupported = false;
                InputDevice device = event.getDevice();
                if (device != null) {
                    InputDevice.MotionRange xRange = device.getMotionRange(0);
                    InputDevice.MotionRange yRange = device.getMotionRange(1);
                    if (!(xRange == null || yRange == null)) {
                        this.mCurrentDeviceSupported = true;
                        float xRes = xRange.getResolution();
                        if (xRes <= 0.0f) {
                            xRes = xRange.getRange() / 48.0f;
                        }
                        float yRes = yRange.getResolution();
                        if (yRes <= 0.0f) {
                            yRes = yRange.getRange() / 48.0f;
                        }
                        float nominalRes = (xRes + yRes) * 0.5f;
                        float f = 12.0f * nominalRes;
                        this.mConfigTickDistance = f;
                        this.mConfigMinFlingVelocity = f * MIN_FLING_VELOCITY_TICKS_PER_SECOND;
                        this.mConfigMaxFlingVelocity = f * MAX_FLING_VELOCITY_TICKS_PER_SECOND;
                    }
                }
            }
            if (this.mCurrentDeviceSupported) {
                int action = event.getActionMasked();
                switch (action) {
                    case 0:
                        boolean caughtFling = this.mFlinging;
                        finishKeys(time);
                        finishTracking(time);
                        this.mActivePointerId = event.getPointerId(0);
                        VelocityTracker obtain = VelocityTracker.obtain();
                        this.mVelocityTracker = obtain;
                        obtain.addMovement(event);
                        this.mStartX = event.getX();
                        float y = event.getY();
                        this.mStartY = y;
                        this.mLastX = this.mStartX;
                        this.mLastY = y;
                        this.mAccumulatedX = 0.0f;
                        this.mAccumulatedY = 0.0f;
                        this.mConsumedMovement = caughtFling;
                        return;
                    case 1:
                    case 2:
                        int i = this.mActivePointerId;
                        if (i >= 0) {
                            int index = event.findPointerIndex(i);
                            if (index < 0) {
                                finishKeys(time);
                                finishTracking(time);
                                return;
                            }
                            this.mVelocityTracker.addMovement(event);
                            float x = event.getX(index);
                            float y2 = event.getY(index);
                            this.mAccumulatedX += x - this.mLastX;
                            this.mAccumulatedY += y2 - this.mLastY;
                            this.mLastX = x;
                            this.mLastY = y2;
                            int metaState = event.getMetaState();
                            consumeAccumulatedMovement(time, metaState);
                            if (action == 1) {
                                if (this.mConsumedMovement && this.mPendingKeyCode != 0) {
                                    this.mVelocityTracker.computeCurrentVelocity(1000, this.mConfigMaxFlingVelocity);
                                    float vx = this.mVelocityTracker.getXVelocity(this.mActivePointerId);
                                    float vy = this.mVelocityTracker.getYVelocity(this.mActivePointerId);
                                    if (!startFling(time, vx, vy)) {
                                        finishKeys(time);
                                    }
                                }
                                finishTracking(time);
                                return;
                            }
                            return;
                        }
                        return;
                    case 3:
                        finishKeys(time);
                        finishTracking(time);
                        return;
                    default:
                        return;
                }
            }
        }

        public void cancel(MotionEvent event) {
            if (this.mCurrentDeviceId == event.getDeviceId() && this.mCurrentSource == event.getSource()) {
                long time = event.getEventTime();
                finishKeys(time);
                finishTracking(time);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void finishKeys(long time) {
            cancelFling();
            sendKeyUp(time);
        }

        private void finishTracking(long time) {
            if (this.mActivePointerId >= 0) {
                this.mActivePointerId = -1;
                this.mVelocityTracker.recycle();
                this.mVelocityTracker = null;
            }
        }

        private void consumeAccumulatedMovement(long time, int metaState) {
            float absX = Math.abs(this.mAccumulatedX);
            float absY = Math.abs(this.mAccumulatedY);
            if (absX >= absY) {
                if (absX >= this.mConfigTickDistance) {
                    this.mAccumulatedX = consumeAccumulatedMovement(time, metaState, this.mAccumulatedX, 21, 22);
                    this.mAccumulatedY = 0.0f;
                    this.mConsumedMovement = true;
                }
            } else if (absY >= this.mConfigTickDistance) {
                this.mAccumulatedY = consumeAccumulatedMovement(time, metaState, this.mAccumulatedY, 19, 20);
                this.mAccumulatedX = 0.0f;
                this.mConsumedMovement = true;
            }
        }

        private float consumeAccumulatedMovement(long time, int metaState, float accumulator, int negativeKeyCode, int positiveKeyCode) {
            while (accumulator <= (-this.mConfigTickDistance)) {
                sendKeyDownOrRepeat(time, negativeKeyCode, metaState);
                accumulator += this.mConfigTickDistance;
            }
            while (accumulator >= this.mConfigTickDistance) {
                sendKeyDownOrRepeat(time, positiveKeyCode, metaState);
                accumulator -= this.mConfigTickDistance;
            }
            return accumulator;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void sendKeyDownOrRepeat(long time, int keyCode, int metaState) {
            if (this.mPendingKeyCode != keyCode) {
                sendKeyUp(time);
                this.mPendingKeyDownTime = time;
                this.mPendingKeyCode = keyCode;
                this.mPendingKeyRepeatCount = 0;
            } else {
                this.mPendingKeyRepeatCount++;
            }
            this.mPendingKeyMetaState = metaState;
            ViewRootImpl.this.enqueueInputEvent(new KeyEvent(this.mPendingKeyDownTime, time, 0, this.mPendingKeyCode, this.mPendingKeyRepeatCount, this.mPendingKeyMetaState, this.mCurrentDeviceId, 1024, this.mCurrentSource));
        }

        private void sendKeyUp(long time) {
            if (this.mPendingKeyCode != 0) {
                ViewRootImpl.this.enqueueInputEvent(new KeyEvent(this.mPendingKeyDownTime, time, 1, this.mPendingKeyCode, 0, this.mPendingKeyMetaState, this.mCurrentDeviceId, 0, 1024, this.mCurrentSource));
                this.mPendingKeyCode = 0;
            }
        }

        private boolean startFling(long time, float vx, float vy) {
            switch (this.mPendingKeyCode) {
                case 19:
                    if ((-vy) >= this.mConfigMinFlingVelocity && Math.abs(vx) < this.mConfigMinFlingVelocity) {
                        this.mFlingVelocity = -vy;
                        break;
                    } else {
                        return false;
                    }
                case 20:
                    if (vy >= this.mConfigMinFlingVelocity && Math.abs(vx) < this.mConfigMinFlingVelocity) {
                        this.mFlingVelocity = vy;
                        break;
                    } else {
                        return false;
                    }
                case 21:
                    if ((-vx) >= this.mConfigMinFlingVelocity && Math.abs(vy) < this.mConfigMinFlingVelocity) {
                        this.mFlingVelocity = -vx;
                        break;
                    } else {
                        return false;
                    }
                    break;
                case 22:
                    if (vx >= this.mConfigMinFlingVelocity && Math.abs(vy) < this.mConfigMinFlingVelocity) {
                        this.mFlingVelocity = vx;
                        break;
                    } else {
                        return false;
                    }
            }
            boolean postFling = postFling(time);
            this.mFlinging = postFling;
            return postFling;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean postFling(long time) {
            float f = this.mFlingVelocity;
            if (f < this.mConfigMinFlingVelocity) {
                return false;
            }
            long delay = (this.mConfigTickDistance / f) * 1000.0f;
            postAtTime(this.mFlingRunnable, time + delay);
            return true;
        }

        private void cancelFling() {
            if (this.mFlinging) {
                removeCallbacks(this.mFlingRunnable);
                this.mFlinging = false;
            }
        }
    }

    /* loaded from: classes3.dex */
    final class SyntheticKeyboardHandler {
        SyntheticKeyboardHandler() {
        }

        public void process(KeyEvent event) {
            if ((event.getFlags() & 1024) == 0) {
                KeyCharacterMap kcm = event.getKeyCharacterMap();
                int keyCode = event.getKeyCode();
                int metaState = event.getMetaState();
                KeyCharacterMap.FallbackAction fallbackAction = kcm.getFallbackAction(keyCode, metaState);
                if (fallbackAction != null) {
                    int flags = event.getFlags() | 1024;
                    KeyEvent fallbackEvent = KeyEvent.obtain(event.getDownTime(), event.getEventTime(), event.getAction(), fallbackAction.keyCode, event.getRepeatCount(), fallbackAction.metaState, event.getDeviceId(), event.getScanCode(), flags, event.getSource(), null);
                    fallbackAction.recycle();
                    ViewRootImpl.this.enqueueInputEvent(fallbackEvent);
                }
            }
        }
    }

    private static boolean isNavigationKey(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 61:
            case 62:
            case 66:
            case 92:
            case 93:
            case 122:
            case 123:
                return true;
            default:
                return false;
        }
    }

    private static boolean isTypingKey(KeyEvent keyEvent) {
        return keyEvent.getUnicodeChar() > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkForLeavingTouchModeAndConsume(KeyEvent event) {
        if (!this.mAttachInfo.mInTouchMode) {
            return false;
        }
        int action = event.getAction();
        if ((action != 0 && action != 2) || (event.getFlags() & 4) != 0) {
            return false;
        }
        if (isNavigationKey(event)) {
            return ensureTouchMode(false);
        }
        if (!isTypingKey(event)) {
            return false;
        }
        ensureTouchMode(false);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setLocalDragState(Object obj) {
        this.mLocalDragState = obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDragEvent(DragEvent event) {
        if (this.mView != null && this.mAdded) {
            int what = event.mAction;
            if (what == 1) {
                this.mCurrentDragView = null;
                this.mDragDescription = event.mClipDescription;
            } else {
                if (what == 4) {
                    this.mDragDescription = null;
                }
                event.mClipDescription = this.mDragDescription;
            }
            if (what == 6) {
                if (View.sCascadedDragDrop) {
                    this.mView.dispatchDragEnterExitInPreN(event);
                }
                setDragFocus(null, event);
            } else {
                if (what == 2 || what == 3) {
                    this.mDragPoint.set(event.mX, event.mY);
                    CompatibilityInfo.Translator translator = this.mTranslator;
                    if (translator != null) {
                        translator.translatePointInScreenToAppWindow(this.mDragPoint);
                    }
                    int i = this.mCurScrollY;
                    if (i != 0) {
                        this.mDragPoint.offset(0.0f, i);
                    }
                    event.mX = this.mDragPoint.x;
                    event.mY = this.mDragPoint.y;
                }
                View prevDragView = this.mCurrentDragView;
                if (what == 3 && event.mClipData != null) {
                    event.mClipData.prepareToEnterProcess(this.mView.getContext().getAttributionSource());
                }
                boolean result = this.mView.dispatchDragEvent(event);
                if (what == 2 && !event.mEventHandlerWasCalled) {
                    setDragFocus(null, event);
                }
                if (prevDragView != this.mCurrentDragView) {
                    if (prevDragView != null) {
                        try {
                            this.mWindowSession.dragRecipientExited(this.mWindow);
                        } catch (RemoteException e) {
                            Slog.e(this.mTag, "Unable to note drag target change");
                        }
                    }
                    if (this.mCurrentDragView != null) {
                        this.mWindowSession.dragRecipientEntered(this.mWindow);
                    }
                }
                if (what == 3) {
                    try {
                        String str = this.mTag;
                        Log.i(str, "Reporting drop result: " + result);
                        this.mWindowSession.reportDropResult(this.mWindow, result);
                    } catch (RemoteException e2) {
                        Log.e(this.mTag, "Unable to report drop result");
                    }
                }
                if (what == 4) {
                    this.mCurrentDragView = null;
                    setLocalDragState(null);
                    this.mAttachInfo.mDragToken = null;
                    if (this.mAttachInfo.mDragSurface != null) {
                        this.mAttachInfo.mDragSurface.release();
                        this.mAttachInfo.mDragSurface = null;
                    }
                }
            }
        }
        event.recycle();
    }

    public void onWindowTitleChanged() {
        this.mAttachInfo.mForceReportNewAttributes = true;
    }

    public void handleDispatchWindowShown() {
        this.mAttachInfo.mTreeObserver.dispatchOnWindowShown();
    }

    public void handleRequestKeyboardShortcuts(IResultReceiver receiver, int deviceId) {
        Bundle data = new Bundle();
        ArrayList<KeyboardShortcutGroup> list = new ArrayList<>();
        View view = this.mView;
        if (view != null) {
            view.requestKeyboardShortcuts(list, deviceId);
        }
        data.putParcelableArrayList(WindowManager.PARCEL_KEY_SHORTCUTS_ARRAY, list);
        try {
            receiver.send(0, data);
        } catch (RemoteException e) {
        }
    }

    public void getLastTouchPoint(Point outLocation) {
        outLocation.x = (int) this.mLastTouchPoint.x;
        outLocation.y = (int) this.mLastTouchPoint.y;
    }

    public int getLastTouchSource() {
        return this.mLastTouchSource;
    }

    public void setDragFocus(View newDragTarget, DragEvent event) {
        if (this.mCurrentDragView != newDragTarget && !View.sCascadedDragDrop) {
            float tx = event.mX;
            float ty = event.mY;
            int action = event.mAction;
            ClipData td = event.mClipData;
            event.mX = 0.0f;
            event.mY = 0.0f;
            event.mClipData = null;
            if (this.mCurrentDragView != null) {
                event.mAction = 6;
                this.mCurrentDragView.callDragEventHandler(event);
            }
            if (newDragTarget != null) {
                event.mAction = 5;
                newDragTarget.callDragEventHandler(event);
            }
            event.mAction = action;
            event.mX = tx;
            event.mY = ty;
            event.mClipData = td;
        }
        this.mCurrentDragView = newDragTarget;
    }

    private AudioManager getAudioManager() {
        View view = this.mView;
        if (view != null) {
            if (this.mAudioManager == null) {
                this.mAudioManager = (AudioManager) view.getContext().getSystemService("audio");
            }
            return this.mAudioManager;
        }
        throw new IllegalStateException("getAudioManager called when there is no mView");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AutofillManager getAutofillManager() {
        View view = this.mView;
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup decorView = (ViewGroup) view;
        if (decorView.getChildCount() > 0) {
            return (AutofillManager) decorView.getChildAt(0).getContext().getSystemService(AutofillManager.class);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAutofillUiShowing() {
        AutofillManager afm = getAutofillManager();
        if (afm == null) {
            return false;
        }
        return afm.isAutofillUiShowing();
    }

    public AccessibilityInteractionController getAccessibilityInteractionController() {
        if (this.mView != null) {
            if (this.mAccessibilityInteractionController == null) {
                this.mAccessibilityInteractionController = new AccessibilityInteractionController(this);
            }
            return this.mAccessibilityInteractionController;
        }
        throw new IllegalStateException("getAccessibilityInteractionController called when there is no mView");
    }

    private int relayoutWindow(WindowManager.LayoutParams params, int viewVisibility, boolean insetsPending) throws RemoteException {
        boolean restore;
        long frameNumber;
        float appScale = this.mAttachInfo.mApplicationScale;
        if (params == null || this.mTranslator == null) {
            restore = false;
        } else {
            params.backup();
            this.mTranslator.translateWindowLayout(params);
            restore = true;
        }
        if (params != null) {
            if (DBG) {
                Log.d(this.mTag, "WindowLayout in layoutWindow:" + params);
            }
            if (DEBUG_LAYOUT) {
                Log.d(this.mTag, ">>>>>> CALLING relayoutW+ " + this.mWindow + ", params = " + params + ",viewVisibility = " + viewVisibility + ", insetsPending = " + insetsPending + ", appScale = " + appScale + ", mWinFrame = " + this.mWinFrame + ", mPendingMergedConfiguration = " + this.mPendingMergedConfiguration + ", mSurface = " + this.mSurface + ",valid = " + this.mSurface.isValid() + ", mOrigWindowType = " + this.mOrigWindowType + ",this = " + this);
            }
            if (this.mOrigWindowType != params.type && this.mTargetSdkVersion < 14) {
                Slog.w(this.mTag, "Window type can not be changed after the window is added; ignoring change of " + this.mView);
                params.type = this.mOrigWindowType;
            }
        }
        if (this.mSurface.isValid()) {
            long frameNumber2 = this.mSurface.getNextFrameNumber();
            frameNumber = frameNumber2;
        } else {
            frameNumber = -1;
        }
        int relayoutResult = this.mWindowSession.relayout(this.mWindow, params, (int) ((this.mView.getMeasuredWidth() * appScale) + 0.5f), (int) ((this.mView.getMeasuredHeight() * appScale) + 0.5f), viewVisibility, insetsPending ? 1 : 0, frameNumber, this.mTmpFrames, this.mPendingMergedConfiguration, this.mSurfaceControl, this.mTempInsets, this.mTempControls, this.mSurfaceSize);
        this.mPendingBackDropFrame.set(this.mTmpFrames.backdropFrame);
        this.mViewRootImplExt.refreshForceDark(this.mView, this.mPendingMergedConfiguration.mOplusDarkModeData);
        if (this.mSurfaceControl.isValid()) {
            if (!useBLAST()) {
                this.mSurface.copyFrom(this.mSurfaceControl);
            } else {
                Surface blastSurface = getOrCreateBLASTSurface();
                if (blastSurface != null) {
                    this.mSurface.transferFrom(blastSurface);
                }
            }
            if (this.mAttachInfo.mThreadedRenderer != null) {
                if (HardwareRenderer.isWebViewOverlaysEnabled()) {
                    addPrepareSurfaceControlForWebviewCallback();
                    addASurfaceTransactionCallback();
                }
                this.mAttachInfo.mThreadedRenderer.setSurfaceControl(this.mSurfaceControl);
            }
        } else {
            destroySurface();
        }
        this.mPendingAlwaysConsumeSystemBars = (relayoutResult & 64) != 0;
        if (DEBUG_LAYOUT) {
            Log.d(this.mTag, "<<<<<< BACK FROM relayoutW- : res = " + relayoutResult + ", mWinFrame = " + this.mWinFrame + ", mPendingMergedConfiguration = " + this.mPendingMergedConfiguration + ", mSurface = " + this.mSurface + ",valid = " + this.mSurface.isValid() + ",params = " + params + ", this = " + this);
        }
        if (restore) {
            params.restore();
        }
        CompatibilityInfo.Translator translator = this.mTranslator;
        if (translator != null) {
            translator.translateRectInScreenToAppWindow(this.mTmpFrames.frame);
            this.mTranslator.translateInsetsStateInScreenToAppWindow(this.mTempInsets);
            this.mTranslator.translateSourceControlsInScreenToAppWindow(this.mTempControls);
        }
        setFrame(this.mTmpFrames.frame);
        this.mWillMove = false;
        this.mWillResize = false;
        this.mInsetsController.onStateChanged(this.mTempInsets);
        this.mInsetsController.onControlsChanged(this.mTempControls);
        return relayoutResult;
    }

    private void updateOpacity(WindowManager.LayoutParams params, boolean dragResizing) {
        boolean opaque = false;
        if (!PixelFormat.formatHasAlpha(params.format) && params.surfaceInsets.left == 0 && params.surfaceInsets.top == 0 && params.surfaceInsets.right == 0 && params.surfaceInsets.bottom == 0 && !dragResizing) {
            opaque = true;
        }
        if (this.mIsSurfaceOpaque != opaque) {
            synchronized (this) {
                if (this.mIsForWebviewOverlay) {
                    this.mIsSurfaceOpaque = false;
                    return;
                }
                this.mTransaction.setOpaque(this.mSurfaceControl, opaque).apply();
                this.mIsSurfaceOpaque = opaque;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFrame(Rect frame) {
        this.mWinFrame.set(frame);
        this.mInsetsController.onFrameChanged(frame);
        this.mViewRootImplExt.setScrollToTopWinFrame(frame);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void getDisplayFrame(Rect outFrame) {
        outFrame.set(this.mTmpFrames.displayFrame);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void getWindowVisibleDisplayFrame(Rect outFrame) {
        outFrame.set(this.mTmpFrames.displayFrame);
        Rect insets = this.mAttachInfo.mVisibleInsets;
        outFrame.left += insets.left;
        outFrame.top += insets.top;
        outFrame.right -= insets.right;
        outFrame.bottom -= insets.bottom;
    }

    @Override // android.view.View.AttachInfo.Callbacks
    public void playSoundEffect(int effectId) {
        checkThread();
        try {
            AudioManager audioManager = getAudioManager();
            if (!this.mFastScrollSoundEffectsEnabled || !SoundEffectConstants.isNavigationRepeat(effectId)) {
                switch (effectId) {
                    case 0:
                        audioManager.playSoundEffect(0);
                        return;
                    case 1:
                    case 5:
                        audioManager.playSoundEffect(3);
                        return;
                    case 2:
                    case 6:
                        audioManager.playSoundEffect(1);
                        return;
                    case 3:
                    case 7:
                        audioManager.playSoundEffect(4);
                        return;
                    case 4:
                    case 8:
                        audioManager.playSoundEffect(2);
                        return;
                    default:
                        throw new IllegalArgumentException("unknown effect id " + effectId + " not defined in " + SoundEffectConstants.class.getCanonicalName());
                }
            } else {
                audioManager.playSoundEffect(SoundEffectConstants.nextNavigationRepeatSoundEffectId());
            }
        } catch (IllegalStateException e) {
            String str = this.mTag;
            Log.e(str, "FATAL EXCEPTION when attempting to play sound effect: " + e);
            e.printStackTrace();
        }
    }

    @Override // android.view.View.AttachInfo.Callbacks
    public boolean performHapticFeedback(int effectId, boolean always) {
        try {
            return this.mWindowSession.performHapticFeedback(effectId, always);
        } catch (RemoteException e) {
            String str = this.mTag;
            Log.e(str, "performHapticFeedback RemoteException happens in " + this, e);
            return false;
        }
    }

    @Override // android.view.ViewParent
    public View focusSearch(View focused, int direction) {
        checkThread();
        if (!(this.mView instanceof ViewGroup)) {
            return null;
        }
        return FocusFinder.getInstance().findNextFocus((ViewGroup) this.mView, focused, direction);
    }

    @Override // android.view.ViewParent
    public View keyboardNavigationClusterSearch(View currentCluster, int direction) {
        checkThread();
        return FocusFinder.getInstance().findNextKeyboardNavigationCluster(this.mView, currentCluster, direction);
    }

    public void debug() {
        this.mView.debug();
    }

    public void dumpDebug(ProtoOutputStream proto, long fieldId) {
        long token = proto.start(fieldId);
        proto.write(1138166333441L, Objects.toString(this.mView));
        proto.write(1120986464258L, this.mDisplay.getDisplayId());
        proto.write(1133871366147L, this.mAppVisible);
        proto.write(1120986464261L, this.mHeight);
        proto.write(1120986464260L, this.mWidth);
        proto.write(1133871366150L, this.mIsAnimating);
        this.mVisRect.dumpDebug(proto, 1146756268039L);
        proto.write(1133871366152L, this.mIsDrawing);
        proto.write(1133871366153L, this.mAdded);
        this.mWinFrame.dumpDebug(proto, 1146756268042L);
        proto.write(1138166333452L, Objects.toString(this.mLastWindowInsets));
        proto.write(1138166333453L, InputMethodDebug.softInputModeToString(this.mSoftInputMode));
        proto.write(1120986464270L, this.mScrollY);
        proto.write(1120986464271L, this.mCurScrollY);
        proto.write(1133871366160L, this.mRemoved);
        this.mWindowAttributes.dumpDebug(proto, 1146756268049L);
        proto.end(token);
        this.mInsetsController.dumpDebug(proto, 1146756268036L);
        this.mImeFocusController.dumpDebug(proto, 1146756268039L);
    }

    public void dump(String prefix, PrintWriter writer) {
        String innerPrefix = prefix + "  ";
        writer.println(prefix + "ViewRoot:");
        writer.println(innerPrefix + "mAdded=" + this.mAdded);
        writer.println(innerPrefix + "mRemoved=" + this.mRemoved);
        writer.println(innerPrefix + "mStopped=" + this.mStopped);
        writer.println(innerPrefix + "mPausedForTransition=" + this.mPausedForTransition);
        writer.println(innerPrefix + "mConsumeBatchedInputScheduled=" + this.mConsumeBatchedInputScheduled);
        writer.println(innerPrefix + "mConsumeBatchedInputImmediatelyScheduled=" + this.mConsumeBatchedInputImmediatelyScheduled);
        writer.println(innerPrefix + "mPendingInputEventCount=" + this.mPendingInputEventCount);
        writer.println(innerPrefix + "mProcessInputEventsScheduled=" + this.mProcessInputEventsScheduled);
        writer.println(innerPrefix + "mTraversalScheduled=" + this.mTraversalScheduled);
        if (this.mTraversalScheduled) {
            writer.println(innerPrefix + " (barrier=" + this.mTraversalBarrier + ")");
        }
        writer.println(innerPrefix + "mWaitForBlastSyncComplete=" + this.mWaitForBlastSyncComplete);
        writer.println(innerPrefix + "mDrawsNeededToReport=" + this.mDrawsNeededToReport);
        writer.println(innerPrefix + "mIsAmbientMode=" + this.mIsAmbientMode);
        writer.println(innerPrefix + "mUnbufferedInputSource=" + Integer.toHexString(this.mUnbufferedInputSource));
        if (this.mAttachInfo != null) {
            writer.print(innerPrefix + "mAttachInfo= ");
            this.mAttachInfo.dump(innerPrefix, writer);
        } else {
            writer.println(innerPrefix + "mAttachInfo=<null>");
        }
        this.mFirstInputStage.dump(innerPrefix, writer);
        WindowInputEventReceiver windowInputEventReceiver = this.mInputEventReceiver;
        if (windowInputEventReceiver != null) {
            windowInputEventReceiver.dump(innerPrefix, writer);
        }
        this.mChoreographer.dump(prefix, writer);
        this.mInsetsController.dump(prefix, writer);
        writer.println(prefix + "View Hierarchy:");
        dumpViewHierarchy(innerPrefix, writer, this.mView);
    }

    private void dumpViewHierarchy(String prefix, PrintWriter writer, View view) {
        ViewGroup grp;
        int N;
        writer.print(prefix);
        if (view == null) {
            writer.println("null");
            return;
        }
        writer.println(view.toString());
        if ((view instanceof ViewGroup) && (N = (grp = (ViewGroup) view).getChildCount()) > 0) {
            String prefix2 = prefix + "  ";
            for (int i = 0; i < N; i++) {
                dumpViewHierarchy(prefix2, writer, grp.getChildAt(i));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class GfxInfo {
        public long renderNodeMemoryAllocated;
        public long renderNodeMemoryUsage;
        public int viewCount;

        /* JADX INFO: Access modifiers changed from: package-private */
        public void add(GfxInfo other) {
            this.viewCount += other.viewCount;
            this.renderNodeMemoryUsage += other.renderNodeMemoryUsage;
            this.renderNodeMemoryAllocated += other.renderNodeMemoryAllocated;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GfxInfo getGfxInfo() {
        GfxInfo info = new GfxInfo();
        View view = this.mView;
        if (view != null) {
            appendGfxInfo(view, info);
        }
        return info;
    }

    private static void computeRenderNodeUsage(RenderNode node, GfxInfo info) {
        if (node != null) {
            info.renderNodeMemoryUsage += node.computeApproximateMemoryUsage();
            info.renderNodeMemoryAllocated += node.computeApproximateMemoryAllocated();
        }
    }

    private static void appendGfxInfo(View view, GfxInfo info) {
        info.viewCount++;
        computeRenderNodeUsage(view.mRenderNode, info);
        computeRenderNodeUsage(view.mBackgroundRenderNode, info);
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            int count = group.getChildCount();
            for (int i = 0; i < count; i++) {
                appendGfxInfo(group.getChildAt(i), info);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean die(boolean immediate) {
        if (ViewDebugManager.DEBUG_LIFECYCLE) {
            String str = this.mTag;
            Log.v(str, "die: immediate = " + immediate + ", mIsInTraversal = " + this.mIsInTraversal + ",mIsDrawing = " + this.mIsDrawing + ",this = " + this, new Throwable());
        }
        if (!immediate || this.mIsInTraversal) {
            if (!this.mIsDrawing) {
                destroyHardwareRenderer();
            } else {
                String str2 = this.mTag;
                Log.e(str2, "Attempting to destroy the window while drawing!\n  window=" + this + ", title=" + ((Object) this.mWindowAttributes.getTitle()));
            }
            this.mHandler.sendEmptyMessage(3);
            return true;
        }
        doDie();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void doDie() {
        checkThread();
        if (LOCAL_LOGV) {
            Log.v(this.mTag, "DIE in " + this + " of " + this.mSurface);
        }
        if (ViewDebugManager.DEBUG_LIFECYCLE) {
            Log.v(this.mTag, "DIE in " + this + " of " + this.mSurface + ", mAdded = " + this.mAdded + ", mFirst = " + this.mFirst);
        }
        synchronized (this) {
            if (!this.mRemoved) {
                this.mViewRootImplExt.onWindowDying();
                boolean viewVisibilityChanged = true;
                this.mRemoved = true;
                if (this.mAdded) {
                    dispatchDetachedFromWindow();
                }
                if (this.mAdded && !this.mFirst) {
                    destroyHardwareRenderer();
                    View view = this.mView;
                    if (view != null) {
                        int viewVisibility = view.getVisibility();
                        if (this.mViewVisibility == viewVisibility) {
                            viewVisibilityChanged = false;
                        }
                        if (this.mWindowAttributesChanged || viewVisibilityChanged) {
                            try {
                                if ((relayoutWindow(this.mWindowAttributes, viewVisibility, false) & 2) != 0) {
                                    this.mWindowSession.finishDrawing(this.mWindow, null);
                                }
                            } catch (RemoteException e) {
                                Log.e(this.mTag, "RemoteException when finish draw window " + this.mWindow + " in " + this, e);
                            }
                        }
                        destroySurface();
                    }
                }
                this.mInsetsController.onControlsChanged(null);
                this.mAdded = false;
                WindowManagerGlobal.getInstance().doRemoveView(this);
            }
        }
    }

    public void requestUpdateConfiguration(Configuration config) {
        Message msg = this.mHandler.obtainMessage(18, config);
        this.mHandler.sendMessage(msg);
    }

    public void loadSystemProperties() {
        this.mHandler.post(new Runnable() { // from class: android.view.ViewRootImpl.4
            @Override // java.lang.Runnable
            public void run() {
                if (ViewRootImpl.this.sUseMsync) {
                    int reportRate = SystemProperties.getInt("ro.vendor.input.touch_report_rate", 100);
                    if (ViewDebugManager.DEBUG_INPUT) {
                        Log.d(ViewRootImpl.TAG, "reportRate = " + reportRate);
                    }
                    if (reportRate != 0) {
                        ViewRootImpl.this.mChoreographer.setMsyncFrameDelayTime(500 / reportRate);
                    }
                }
                ViewRootImpl.this.mProfileRendering = SystemProperties.getBoolean(ViewRootImpl.PROPERTY_PROFILE_RENDERING, false);
                ViewRootImpl viewRootImpl = ViewRootImpl.this;
                viewRootImpl.profileRendering(viewRootImpl.mAttachInfo.mHasWindowFocus);
                if (ViewRootImpl.this.mAttachInfo.mThreadedRenderer != null && ViewRootImpl.this.mAttachInfo.mThreadedRenderer.loadSystemProperties()) {
                    ViewRootImpl.this.invalidate();
                }
                boolean layout = DisplayProperties.debug_layout().orElse(false).booleanValue();
                if (layout != ViewRootImpl.this.mAttachInfo.mDebugLayout) {
                    ViewRootImpl.this.mAttachInfo.mDebugLayout = layout;
                    if (!ViewRootImpl.this.mHandler.hasMessages(22)) {
                        ViewRootImpl.this.mHandler.sendEmptyMessageDelayed(22, 200L);
                    }
                }
            }
        });
    }

    private void destroyHardwareRenderer() {
        ThreadedRenderer hardwareRenderer = this.mAttachInfo.mThreadedRenderer;
        if (hardwareRenderer != null) {
            HardwareRendererObserver hardwareRendererObserver = this.mHardwareRendererObserver;
            if (hardwareRendererObserver != null) {
                hardwareRenderer.removeObserver(hardwareRendererObserver);
            }
            View view = this.mView;
            if (view != null) {
                hardwareRenderer.destroyHardwareResources(view);
            }
            hardwareRenderer.destroy();
            hardwareRenderer.setRequested(false);
            this.mAttachInfo.mThreadedRenderer = null;
            this.mAttachInfo.mHardwareAccelerated = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchResized(ClientWindowFrames frames, boolean reportDraw, MergedConfiguration mergedConfiguration, boolean forceLayout, boolean alwaysConsumeSystemBars, int displayId) {
        Rect frame = frames.frame;
        Rect backDropFrame = frames.backdropFrame;
        if (DEBUG_LAYOUT) {
            Log.v(this.mTag, "Resizing " + this + ": frame=" + frame.toShortString() + " reportDraw=" + reportDraw + " backDropFrame=" + backDropFrame);
        }
        boolean sameProcessCall = true;
        if (this.mDragResizing && this.mUseMTRenderer) {
            boolean fullscreen = frame.equals(backDropFrame);
            synchronized (this.mWindowCallbacks) {
                for (int i = this.mWindowCallbacks.size() - 1; i >= 0; i--) {
                    this.mWindowCallbacks.get(i).onWindowSizeIsChanging(backDropFrame, fullscreen, this.mAttachInfo.mVisibleInsets, this.mAttachInfo.mStableInsets);
                }
            }
        }
        Message msg = this.mHandler.obtainMessage(reportDraw ? 5 : 4);
        CompatibilityInfo.Translator translator = this.mTranslator;
        if (translator != null) {
            translator.translateRectInScreenToAppWindow(frame);
        }
        SomeArgs args = SomeArgs.obtain();
        if (Binder.getCallingPid() != Process.myPid()) {
            sameProcessCall = false;
        }
        args.arg1 = sameProcessCall ? new ClientWindowFrames(frames) : frames;
        args.arg2 = (!sameProcessCall || mergedConfiguration == null) ? mergedConfiguration : new MergedConfiguration(mergedConfiguration);
        args.argi1 = forceLayout ? 1 : 0;
        args.argi2 = alwaysConsumeSystemBars ? 1 : 0;
        args.argi3 = displayId;
        msg.obj = args;
        this.mHandler.sendMessage(msg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchInsetsChanged(InsetsState insetsState, boolean willMove, boolean willResize) {
        if (Binder.getCallingPid() == Process.myPid()) {
            insetsState = new InsetsState(insetsState, true);
        }
        CompatibilityInfo.Translator translator = this.mTranslator;
        if (translator != null) {
            translator.translateInsetsStateInScreenToAppWindow(insetsState);
        }
        if (insetsState != null && insetsState.getSource(19).isVisible()) {
            ImeTracing.getInstance().triggerClientDump("ViewRootImpl#dispatchInsetsChanged", getInsetsController().getHost().getInputMethodManager(), null);
        }
        SomeArgs args = SomeArgs.obtain();
        args.arg1 = insetsState;
        args.argi1 = willMove ? 1 : 0;
        args.argi2 = willResize ? 1 : 0;
        this.mHandler.obtainMessage(30, args).sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchInsetsControlChanged(InsetsState insetsState, InsetsSourceControl[] activeControls, boolean willMove, boolean willResize) {
        if (Binder.getCallingPid() == Process.myPid()) {
            insetsState = new InsetsState(insetsState, true);
            if (activeControls != null) {
                for (int i = activeControls.length - 1; i >= 0; i--) {
                    activeControls[i] = new InsetsSourceControl(activeControls[i]);
                }
            }
        }
        CompatibilityInfo.Translator translator = this.mTranslator;
        if (translator != null) {
            translator.translateInsetsStateInScreenToAppWindow(insetsState);
            this.mTranslator.translateSourceControlsInScreenToAppWindow(activeControls);
        }
        if (insetsState != null && insetsState.getSource(19).isVisible()) {
            ImeTracing.getInstance().triggerClientDump("ViewRootImpl#dispatchInsetsControlChanged", getInsetsController().getHost().getInputMethodManager(), null);
        }
        SomeArgs args = SomeArgs.obtain();
        args.arg1 = insetsState;
        args.arg2 = activeControls;
        args.argi1 = willMove ? 1 : 0;
        args.argi2 = willResize ? 1 : 0;
        this.mHandler.obtainMessage(31, args).sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showInsets(int types, boolean fromIme) {
        this.mHandler.obtainMessage(34, types, fromIme ? 1 : 0).sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideInsets(int types, boolean fromIme) {
        this.mHandler.obtainMessage(35, types, fromIme ? 1 : 0).sendToTarget();
    }

    public void dispatchMoved(int newX, int newY) {
        if (DEBUG_LAYOUT) {
            String str = this.mTag;
            Log.v(str, "Window moved " + this + ": newX=" + newX + " newY=" + newY);
        }
        if (this.mTranslator != null) {
            PointF point = new PointF(newX, newY);
            this.mTranslator.translatePointInScreenToAppWindow(point);
            newX = (int) (point.x + 0.5d);
            newY = (int) (point.y + 0.5d);
        }
        Message msg = this.mHandler.obtainMessage(23, newX, newY);
        this.mHandler.sendMessage(msg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class QueuedInputEvent {
        public static final int FLAG_DEFERRED = 2;
        public static final int FLAG_DELIVER_POST_IME = 1;
        public static final int FLAG_FINISHED = 4;
        public static final int FLAG_FINISHED_HANDLED = 8;
        public static final int FLAG_MODIFIED_FOR_COMPATIBILITY = 64;
        public static final int FLAG_RESYNTHESIZED = 16;
        public static final int FLAG_UNHANDLED = 32;
        public InputEvent mEvent;
        public int mFlags;
        public QueuedInputEvent mNext;
        public InputEventReceiver mReceiver;

        private QueuedInputEvent() {
        }

        public boolean shouldSkipIme() {
            if ((this.mFlags & 1) != 0) {
                return true;
            }
            InputEvent inputEvent = this.mEvent;
            return (inputEvent instanceof MotionEvent) && (inputEvent.isFromSource(2) || this.mEvent.isFromSource(4194304));
        }

        public boolean shouldSendToSynthesizer() {
            if ((this.mFlags & 32) != 0) {
                return true;
            }
            return false;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("QueuedInputEvent{flags=");
            boolean hasPrevious = flagToString("DELIVER_POST_IME", 1, false, sb);
            if (!flagToString("UNHANDLED", 32, flagToString("RESYNTHESIZED", 16, flagToString("FINISHED_HANDLED", 8, flagToString("FINISHED", 4, flagToString("DEFERRED", 2, hasPrevious, sb), sb), sb), sb), sb)) {
                sb.append("0");
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(", hasNextQueuedEvent=");
            String str = "true";
            sb2.append(this.mEvent != null ? str : "false");
            sb.append(sb2.toString());
            StringBuilder sb3 = new StringBuilder();
            sb3.append(", hasInputEventReceiver=");
            if (this.mReceiver == null) {
                str = "false";
            }
            sb3.append(str);
            sb.append(sb3.toString());
            sb.append(", mEvent=" + this.mEvent + "}");
            return sb.toString();
        }

        private boolean flagToString(String name, int flag, boolean hasPrevious, StringBuilder sb) {
            if ((this.mFlags & flag) == 0) {
                return hasPrevious;
            }
            if (hasPrevious) {
                sb.append("|");
            }
            sb.append(name);
            return true;
        }
    }

    private QueuedInputEvent obtainQueuedInputEvent(InputEvent event, InputEventReceiver receiver, int flags) {
        QueuedInputEvent q = this.mQueuedInputEventPool;
        if (q != null) {
            this.mQueuedInputEventPoolSize--;
            this.mQueuedInputEventPool = q.mNext;
            q.mNext = null;
        } else {
            q = new QueuedInputEvent();
        }
        q.mEvent = event;
        q.mReceiver = receiver;
        q.mFlags = flags;
        return q;
    }

    private void recycleQueuedInputEvent(QueuedInputEvent q) {
        q.mEvent = null;
        q.mReceiver = null;
        int i = this.mQueuedInputEventPoolSize;
        if (i < 10) {
            this.mQueuedInputEventPoolSize = i + 1;
            q.mNext = this.mQueuedInputEventPool;
            this.mQueuedInputEventPool = q;
        }
    }

    void enqueueInputEvent(InputEvent event) {
        enqueueInputEvent(event, null, 0, false);
    }

    void enqueueInputEvent(InputEvent event, InputEventReceiver receiver, int flags, boolean processImmediately) {
        QueuedInputEvent q = obtainQueuedInputEvent(event, receiver, flags);
        if (event instanceof MotionEvent) {
            MotionEvent me = (MotionEvent) event;
            if (me.getAction() == 3) {
                EventLog.writeEvent((int) EventLogTags.VIEW_ENQUEUE_INPUT_EVENT, "Motion - Cancel", getTitle().toString());
            }
        } else if (event instanceof KeyEvent) {
            KeyEvent ke = (KeyEvent) event;
            if (ke.isCanceled()) {
                EventLog.writeEvent((int) EventLogTags.VIEW_ENQUEUE_INPUT_EVENT, "Key - Cancel", getTitle().toString());
            }
        }
        QueuedInputEvent last = this.mPendingInputEventTail;
        if (last == null) {
            this.mPendingInputEventHead = q;
            this.mPendingInputEventTail = q;
        } else {
            last.mNext = q;
            this.mPendingInputEventTail = q;
        }
        int i = this.mPendingInputEventCount + 1;
        this.mPendingInputEventCount = i;
        Trace.traceCounter(4L, this.mPendingInputEventQueueLengthCounterName, i);
        this.mViewRootImplExt.debugInputEventEnqueue(this.mTag, event, processImmediately, this.mProcessInputEventsScheduled);
        if (ViewDebugManager.DEBUG_MET_TRACE && (event instanceof MotionEvent)) {
            MotionEvent ev = (MotionEvent) event;
            Trace.traceBegin(4L, "MET_enqueueInputEvent_name: " + ((Object) this.mWindowAttributes.getTitle()));
            Trace.traceEnd(4L);
            Trace.traceBegin(4L, "MET_enqueueInputEvent: " + ev.getEventTime() + "," + ev.getAction() + "," + ev.getX(0) + "," + ev.getY(0));
            Trace.traceEnd(4L);
        }
        if (ViewDebugManager.DEBUG_INPUT || ViewDebugManager.DEBUG_KEY || ViewDebugManager.DEBUG_MOTION || ViewDebugManager.DEBUG_ENG || ViewDebugManager.DEBUG_USERDEBUG) {
            String str = this.mTag;
            Log.v(str, "enqueueInputEvent: event = " + event + ",processImmediately = " + processImmediately + ",mProcessInputEventsScheduled = " + this.mProcessInputEventsScheduled + ", this = " + this);
        }
        if (processImmediately) {
            doProcessInputEvents();
        } else {
            scheduleProcessInputEvents();
        }
    }

    private void scheduleProcessInputEvents() {
        if (!this.mProcessInputEventsScheduled) {
            this.mProcessInputEventsScheduled = true;
            Message msg = this.mHandler.obtainMessage(19);
            msg.setAsynchronous(true);
            this.mHandler.sendMessage(msg);
        }
    }

    void doProcessInputEvents() {
        while (this.mPendingInputEventHead != null) {
            QueuedInputEvent q = this.mPendingInputEventHead;
            QueuedInputEvent queuedInputEvent = q.mNext;
            this.mPendingInputEventHead = queuedInputEvent;
            if (queuedInputEvent == null) {
                this.mPendingInputEventTail = null;
            }
            q.mNext = null;
            int i = this.mPendingInputEventCount - 1;
            this.mPendingInputEventCount = i;
            Trace.traceCounter(4L, this.mPendingInputEventQueueLengthCounterName, i);
            if (q.mEvent instanceof MotionEvent) {
                MotionEvent me = (MotionEvent) q.mEvent;
                this.mViewRootImplExt.updateScrollerState(me);
                if (this.mIsSurfaceViewCreated && this.mSurfaceExt.isResolutionTuningPackage()) {
                    Matrix scale = new Matrix();
                    scale.setScale(1.0f / this.mSurfaceExt.getXScale(), 1.0f / this.mSurfaceExt.getYScale());
                    me.transform(scale);
                }
            }
            this.mViewFrameInfo.setInputEvent(this.mInputEventAssigner.processEvent(q.mEvent));
            long flag = this.mChoreographer.mChoreographerExt.getInputEventFlag(q.mEvent, this.mViewConfiguration.getScaledMaximumFlingVelocity(), this.mViewConfiguration.getScaledMinimumFlingVelocity());
            if (flag != 0) {
                this.mViewFrameInfo.addFlags(flag);
            }
            if (ViewDebugManager.DEBUG_INPUT || ViewDebugManager.DEBUG_KEY || ViewDebugManager.DEBUG_MOTION) {
                Log.v(this.mTag, "doProcessInputEvents: mCurrentInputEvent = " + q + ", this = " + this);
            }
            deliverInputEvent(q);
        }
        if (this.mProcessInputEventsScheduled) {
            this.mProcessInputEventsScheduled = false;
            this.mHandler.removeMessages(19);
        }
    }

    private void deliverInputEvent(QueuedInputEvent q) {
        InputStage stage;
        Trace.asyncTraceBegin(8L, "deliverInputEvent", q.mEvent.getId());
        if (Trace.isTagEnabled(8L)) {
            Trace.traceBegin(8L, "deliverInputEvent src=0x" + Integer.toHexString(q.mEvent.getSource()) + " eventTimeNano=" + q.mEvent.getEventTimeNano() + " id=0x" + Integer.toHexString(q.mEvent.getId()));
        }
        try {
            if (this.mInputEventConsistencyVerifier != null) {
                Trace.traceBegin(8L, "verifyEventConsistency");
                this.mInputEventConsistencyVerifier.onInputEvent(q.mEvent, 0);
                Trace.traceEnd(8L);
            }
            if (q.shouldSendToSynthesizer()) {
                stage = this.mSyntheticInputStage;
            } else {
                stage = q.shouldSkipIme() ? this.mFirstPostImeInputStage : this.mFirstInputStage;
            }
            if (q.mEvent instanceof KeyEvent) {
                Trace.traceBegin(8L, "preDispatchToUnhandledKeyManager");
                this.mUnhandledKeyManager.preDispatch((KeyEvent) q.mEvent);
                Trace.traceEnd(8L);
            }
            if (q.mEvent instanceof MotionEvent) {
                MotionEvent event = (MotionEvent) q.mEvent;
                if (event.getAction() == 0) {
                    this.isFirstMoveEvent = true;
                } else if (event.getAction() == 1 || event.getAction() == 3) {
                    this.isFirstMoveEvent = false;
                }
            }
            if (stage != null) {
                handleWindowFocusChanged();
                stage.deliver(q);
                this.mViewRootImplExt.resetOptState(this.mSurface);
            } else {
                finishInputEvent(q);
            }
        } finally {
            Trace.traceEnd(8L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishInputEvent(QueuedInputEvent q) {
        Trace.asyncTraceEnd(8L, "deliverInputEvent", q.mEvent.getId());
        boolean modified = true;
        boolean handled = (q.mFlags & 8) != 0;
        this.mViewRootImplExt.debugInputEventFinished(this.mTag, q.mFlags, q.mEvent);
        ViewDebugManager.getInstance().debugInputEventFinished(this.mTag, handled, q.mEvent, this);
        if (q.mReceiver != null) {
            if ((q.mFlags & 64) == 0) {
                modified = false;
            }
            if (modified) {
                Trace.traceBegin(8L, "processInputEventBeforeFinish");
                try {
                    InputEvent processedEvent = this.mInputCompatProcessor.processInputEventBeforeFinish(q.mEvent);
                    if (processedEvent != null) {
                        q.mReceiver.finishInputEvent(processedEvent, handled);
                    }
                } finally {
                    Trace.traceEnd(8L);
                }
            } else {
                q.mReceiver.finishInputEvent(q.mEvent, handled);
            }
        } else {
            q.mEvent.recycleIfNeededAfterDispatch();
        }
        recycleQueuedInputEvent(q);
    }

    static boolean isTerminalInputEvent(InputEvent event) {
        if (event instanceof KeyEvent) {
            KeyEvent keyEvent = (KeyEvent) event;
            return keyEvent.getAction() == 1;
        }
        MotionEvent motionEvent = (MotionEvent) event;
        int action = motionEvent.getAction();
        return action == 1 || action == 3 || action == 10;
    }

    void scheduleConsumeBatchedInput() {
        if (!this.mConsumeBatchedInputScheduled && !this.mConsumeBatchedInputImmediatelyScheduled) {
            this.mConsumeBatchedInputScheduled = true;
            this.mChoreographer.postCallback(0, this.mConsumedBatchedInputRunnable, null);
        }
    }

    void unscheduleConsumeBatchedInput() {
        if (this.mConsumeBatchedInputScheduled) {
            this.mConsumeBatchedInputScheduled = false;
            this.mChoreographer.removeCallbacks(0, this.mConsumedBatchedInputRunnable, null);
        }
    }

    void scheduleConsumeBatchedInputImmediately() {
        if (!this.mConsumeBatchedInputImmediatelyScheduled) {
            unscheduleConsumeBatchedInput();
            this.mConsumeBatchedInputImmediatelyScheduled = true;
            this.mHandler.post(this.mConsumeBatchedInputImmediatelyRunnable);
        }
    }

    boolean doConsumeBatchedInput(long frameTimeNanos) {
        boolean consumedBatches;
        WindowInputEventReceiver windowInputEventReceiver = this.mInputEventReceiver;
        if (windowInputEventReceiver != null) {
            consumedBatches = windowInputEventReceiver.consumeBatchedInputEvents(frameTimeNanos);
        } else {
            consumedBatches = false;
        }
        doProcessInputEvents();
        return consumedBatches;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public final class TraversalRunnable implements Runnable {
        TraversalRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewRootImpl.this.doTraversal();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public final class WindowInputEventReceiver extends InputEventReceiver {
        public WindowInputEventReceiver(InputChannel inputChannel, Looper looper) {
            super(inputChannel, looper);
        }

        @Override // android.view.InputEventReceiver
        public void onInputEvent(InputEvent event) {
            ViewDebugManager.getInstance().debugInputEventStart(event);
            Trace.traceBegin(8L, "processInputEventForCompatibility");
            ViewRootImpl.this.mViewRootImplExt.debugInputEventStart(ViewRootImpl.this.mTag, event);
            try {
                ViewRootImpl.this.mViewRootImplExt.updateInputEventToInputMethod(event);
                if (!ViewRootImpl.this.mViewRootImplExt.dispatchTouchEventForZoomWinow(this, event, ViewRootImpl.this.mView, ViewRootImpl.this.mLastReportedMergedConfiguration)) {
                    List<InputEvent> processedEvents = ViewRootImpl.this.mInputCompatProcessor.processInputEventForCompatibility(event);
                    Trace.traceEnd(8L);
                    if (processedEvents == null) {
                        ViewRootImpl.this.enqueueInputEvent(event, this, 0, true);
                    } else if (processedEvents.isEmpty()) {
                        finishInputEvent(event, true);
                    } else {
                        for (int i = 0; i < processedEvents.size(); i++) {
                            ViewRootImpl.this.enqueueInputEvent(processedEvents.get(i), this, 64, true);
                        }
                    }
                }
            } finally {
                Trace.traceEnd(8L);
            }
        }

        @Override // android.view.InputEventReceiver
        public void onBatchedInputEventPending(int source) {
            boolean unbuffered = ViewRootImpl.this.mUnbufferedInputDispatch || (ViewRootImpl.this.mUnbufferedInputSource & source) != 0;
            if (unbuffered) {
                if (ViewRootImpl.this.mConsumeBatchedInputScheduled) {
                    ViewRootImpl.this.unscheduleConsumeBatchedInput();
                }
                consumeBatchedInputEvents(-1L);
            } else if (!ViewRootImpl.this.sUseMsync || !ViewRootImpl.this.isFirstMoveEvent) {
                ViewRootImpl.this.scheduleConsumeBatchedInput();
            } else {
                ViewRootImpl.this.doConsumeBatchedInput(-1L);
                ViewRootImpl.this.isFirstMoveEvent = false;
            }
        }

        @Override // android.view.InputEventReceiver
        public void onFocusEvent(boolean hasFocus, boolean inTouchMode) {
            ViewRootImpl.this.windowFocusChanged(hasFocus, inTouchMode);
        }

        @Override // android.view.InputEventReceiver
        public void onPointerCaptureEvent(boolean pointerCaptureEnabled) {
            ViewRootImpl.this.dispatchPointerCaptureChanged(pointerCaptureEnabled);
        }

        @Override // android.view.InputEventReceiver
        public void onDragEvent(boolean isExiting, float x, float y) {
            DragEvent event = DragEvent.obtain(isExiting ? 6 : 2, x, y, 0.0f, 0.0f, null, null, null, null, null, false);
            ViewRootImpl.this.dispatchDragEvent(event);
        }

        @Override // android.view.InputEventReceiver
        public void dispose() {
            ViewRootImpl.this.unscheduleConsumeBatchedInput();
            super.dispose();
        }
    }

    /* loaded from: classes3.dex */
    final class InputMetricsListener implements HardwareRendererObserver.OnFrameMetricsAvailableListener {
        public long[] data = new long[22];

        InputMetricsListener() {
        }

        @Override // android.graphics.HardwareRendererObserver.OnFrameMetricsAvailableListener
        public void onFrameMetricsAvailable(int dropCountSinceLastInvocation) {
            long[] jArr = this.data;
            int inputEventId = (int) jArr[4];
            if (inputEventId != 0) {
                long presentTime = jArr[21];
                if (presentTime > 0) {
                    long gpuCompletedTime = jArr[19];
                    if (ViewRootImpl.this.mInputEventReceiver != null) {
                        if (gpuCompletedTime >= presentTime) {
                            double discrepancyMs = (gpuCompletedTime - presentTime) * 1.0E-6d;
                            long vsyncId = this.data[1];
                            Log.w(ViewRootImpl.TAG, "Not reporting timeline because gpuCompletedTime is " + discrepancyMs + "ms ahead of presentTime. FRAME_TIMELINE_VSYNC_ID=" + vsyncId + ", INPUT_EVENT_ID=" + inputEventId);
                            return;
                        }
                        ViewRootImpl.this.mInputEventReceiver.reportTimeline(inputEventId, gpuCompletedTime, presentTime);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public final class ConsumeBatchedInputRunnable implements Runnable {
        ConsumeBatchedInputRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewRootImpl.this.mConsumeBatchedInputScheduled = false;
            ViewRootImpl viewRootImpl = ViewRootImpl.this;
            if (viewRootImpl.doConsumeBatchedInput(viewRootImpl.mChoreographer.getFrameTimeNanos())) {
                ViewRootImpl.this.scheduleConsumeBatchedInput();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public final class ConsumeBatchedInputImmediatelyRunnable implements Runnable {
        ConsumeBatchedInputImmediatelyRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewRootImpl.this.mConsumeBatchedInputImmediatelyScheduled = false;
            ViewRootImpl.this.doConsumeBatchedInput(-1L);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public final class InvalidateOnAnimationRunnable implements Runnable {
        private boolean mPosted;
        private View.AttachInfo.InvalidateInfo[] mTempViewRects;
        private View[] mTempViews;
        private final ArrayList<View> mViews = new ArrayList<>();
        private final ArrayList<View.AttachInfo.InvalidateInfo> mViewRects = new ArrayList<>();

        InvalidateOnAnimationRunnable() {
        }

        public void addView(View view) {
            synchronized (this) {
                this.mViews.add(view);
                postIfNeededLocked();
            }
        }

        public void addViewRect(View.AttachInfo.InvalidateInfo info) {
            synchronized (this) {
                this.mViewRects.add(info);
                postIfNeededLocked();
            }
        }

        public void removeView(View view) {
            synchronized (this) {
                this.mViews.remove(view);
                int i = this.mViewRects.size();
                while (true) {
                    int i2 = i - 1;
                    if (i <= 0) {
                        break;
                    }
                    View.AttachInfo.InvalidateInfo info = this.mViewRects.get(i2);
                    if (info.target == view) {
                        this.mViewRects.remove(i2);
                        info.recycle();
                    }
                    i = i2;
                }
                if (this.mPosted && this.mViews.isEmpty() && this.mViewRects.isEmpty()) {
                    ViewRootImpl.this.mChoreographer.removeCallbacks(1, this, null);
                    this.mPosted = false;
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            int viewCount;
            int viewRectCount;
            synchronized (this) {
                this.mPosted = false;
                viewCount = this.mViews.size();
                if (viewCount != 0) {
                    ArrayList<View> arrayList = this.mViews;
                    View[] viewArr = this.mTempViews;
                    if (viewArr == null) {
                        viewArr = new View[viewCount];
                    }
                    this.mTempViews = (View[]) arrayList.toArray(viewArr);
                    this.mViews.clear();
                }
                viewRectCount = this.mViewRects.size();
                if (viewRectCount != 0) {
                    ArrayList<View.AttachInfo.InvalidateInfo> arrayList2 = this.mViewRects;
                    View.AttachInfo.InvalidateInfo[] invalidateInfoArr = this.mTempViewRects;
                    if (invalidateInfoArr == null) {
                        invalidateInfoArr = new View.AttachInfo.InvalidateInfo[viewRectCount];
                    }
                    this.mTempViewRects = (View.AttachInfo.InvalidateInfo[]) arrayList2.toArray(invalidateInfoArr);
                    this.mViewRects.clear();
                }
            }
            for (int i = 0; i < viewCount; i++) {
                this.mTempViews[i].invalidate();
                this.mTempViews[i] = null;
            }
            for (int i2 = 0; i2 < viewRectCount; i2++) {
                View.AttachInfo.InvalidateInfo info = this.mTempViewRects[i2];
                info.target.invalidate(info.left, info.top, info.right, info.bottom);
                info.recycle();
            }
        }

        private void postIfNeededLocked() {
            if (!this.mPosted) {
                ViewRootImpl.this.mChoreographer.postCallback(1, this, null);
                this.mPosted = true;
            }
        }
    }

    public void dispatchInvalidateDelayed(View view, long delayMilliseconds) {
        Message msg = this.mHandler.obtainMessage(1, view);
        this.mHandler.sendMessageDelayed(msg, delayMilliseconds);
    }

    public void dispatchInvalidateRectDelayed(View.AttachInfo.InvalidateInfo info, long delayMilliseconds) {
        Message msg = this.mHandler.obtainMessage(2, info);
        this.mHandler.sendMessageDelayed(msg, delayMilliseconds);
    }

    public void dispatchInvalidateOnAnimation(View view) {
        this.mInvalidateOnAnimationRunnable.addView(view);
    }

    public void dispatchInvalidateRectOnAnimation(View.AttachInfo.InvalidateInfo info) {
        this.mInvalidateOnAnimationRunnable.addViewRect(info);
    }

    public void cancelInvalidate(View view) {
        this.mHandler.removeMessages(1, view);
        this.mHandler.removeMessages(2, view);
        this.mInvalidateOnAnimationRunnable.removeView(view);
    }

    public void dispatchInputEvent(InputEvent event) {
        dispatchInputEvent(event, null);
    }

    public void dispatchInputEvent(InputEvent event, InputEventReceiver receiver) {
        SomeArgs args = SomeArgs.obtain();
        args.arg1 = event;
        args.arg2 = receiver;
        Message msg = this.mHandler.obtainMessage(7, args);
        msg.setAsynchronous(true);
        this.mHandler.sendMessage(msg);
    }

    public void synthesizeInputEvent(InputEvent event) {
        Message msg = this.mHandler.obtainMessage(24, event);
        msg.setAsynchronous(true);
        this.mHandler.sendMessage(msg);
    }

    public void dispatchKeyFromIme(KeyEvent event) {
        Message msg = this.mHandler.obtainMessage(11, event);
        msg.setAsynchronous(true);
        this.mHandler.sendMessage(msg);
    }

    public void dispatchKeyFromAutofill(KeyEvent event) {
        Message msg = this.mHandler.obtainMessage(12, event);
        msg.setAsynchronous(true);
        this.mHandler.sendMessage(msg);
    }

    public void dispatchUnhandledInputEvent(InputEvent event) {
        if (event instanceof MotionEvent) {
            event = MotionEvent.obtain((MotionEvent) event);
        }
        synthesizeInputEvent(event);
    }

    public void dispatchAppVisibility(boolean visible) {
        Message msg = this.mHandler.obtainMessage(8);
        msg.arg1 = visible ? 1 : 0;
        this.mHandler.sendMessage(msg);
    }

    public void dispatchGetNewSurface() {
        Message msg = this.mHandler.obtainMessage(9);
        this.mHandler.sendMessage(msg);
    }

    public void dispatchLocationInParentDisplayChanged(Point offset) {
        Message msg = this.mHandler.obtainMessage(33, offset.x, offset.y);
        this.mHandler.sendMessage(msg);
    }

    public void windowFocusChanged(boolean hasFocus, boolean inTouchMode) {
        this.mViewRootImplExt.checkKeyguardAndConfig(this.mTag);
        synchronized (this) {
            this.mWindowFocusChanged = true;
            this.mUpcomingWindowFocus = hasFocus;
            this.mUpcomingInTouchMode = inTouchMode;
        }
        Message msg = Message.obtain();
        msg.what = 6;
        this.mHandler.sendMessage(msg);
    }

    public void dispatchWindowShown() {
        this.mHandler.sendEmptyMessage(25);
    }

    public void dispatchCloseSystemDialogs(String reason) {
        Message msg = Message.obtain();
        msg.what = 14;
        msg.obj = reason;
        this.mHandler.sendMessage(msg);
    }

    public void dispatchDragEvent(DragEvent event) {
        int what;
        if (event.getAction() == 2) {
            what = 16;
            this.mHandler.removeMessages(16);
        } else {
            what = 15;
        }
        Message msg = this.mHandler.obtainMessage(what, event);
        this.mHandler.sendMessage(msg);
    }

    public void updatePointerIcon(float x, float y) {
        this.mHandler.removeMessages(27);
        long now = SystemClock.uptimeMillis();
        MotionEvent event = MotionEvent.obtain(0L, now, 7, x, y, 0);
        Message msg = this.mHandler.obtainMessage(27, event);
        this.mHandler.sendMessage(msg);
    }

    public void dispatchCheckFocus() {
        if (!this.mHandler.hasMessages(13)) {
            this.mHandler.sendEmptyMessage(13);
        }
    }

    public void dispatchRequestKeyboardShortcuts(IResultReceiver receiver, int deviceId) {
        this.mHandler.obtainMessage(26, deviceId, 0, receiver).sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchPointerCaptureChanged(boolean on) {
        this.mHandler.removeMessages(28);
        Message msg = this.mHandler.obtainMessage(28);
        msg.arg1 = on ? 1 : 0;
        this.mHandler.sendMessage(msg);
    }

    private void postSendWindowContentChangedCallback(View source, int changeType) {
        if (this.mSendWindowContentChangedAccessibilityEvent == null) {
            this.mSendWindowContentChangedAccessibilityEvent = new SendWindowContentChangedAccessibilityEvent();
        }
        this.mSendWindowContentChangedAccessibilityEvent.runOrPost(source, changeType);
    }

    private void removeSendWindowContentChangedCallback() {
        SendWindowContentChangedAccessibilityEvent sendWindowContentChangedAccessibilityEvent = this.mSendWindowContentChangedAccessibilityEvent;
        if (sendWindowContentChangedAccessibilityEvent != null) {
            this.mHandler.removeCallbacks(sendWindowContentChangedAccessibilityEvent);
        }
    }

    @Override // android.view.ViewParent
    public boolean showContextMenuForChild(View originalView) {
        return false;
    }

    @Override // android.view.ViewParent
    public boolean showContextMenuForChild(View originalView, float x, float y) {
        return false;
    }

    @Override // android.view.ViewParent
    public ActionMode startActionModeForChild(View originalView, ActionMode.Callback callback) {
        return null;
    }

    @Override // android.view.ViewParent
    public ActionMode startActionModeForChild(View originalView, ActionMode.Callback callback, int type) {
        return null;
    }

    @Override // android.view.ViewParent
    public void createContextMenu(ContextMenu menu) {
    }

    @Override // android.view.ViewParent
    public void childDrawableStateChanged(View child) {
    }

    @Override // android.view.ViewParent
    public boolean requestSendAccessibilityEvent(View child, AccessibilityEvent event) {
        AccessibilityNodeProvider provider;
        SendWindowContentChangedAccessibilityEvent sendWindowContentChangedAccessibilityEvent;
        if (this.mView == null || this.mStopped || this.mPausedForTransition) {
            return false;
        }
        if (!(event.getEventType() == 2048 || (sendWindowContentChangedAccessibilityEvent = this.mSendWindowContentChangedAccessibilityEvent) == null || sendWindowContentChangedAccessibilityEvent.mSource == null)) {
            this.mSendWindowContentChangedAccessibilityEvent.removeCallbacksAndRun();
        }
        int eventType = event.getEventType();
        View source = getSourceForAccessibilityEvent(event);
        switch (eventType) {
            case 2048:
                handleWindowContentChangedEvent(event);
                break;
            case 32768:
                if (!(source == null || (provider = source.getAccessibilityNodeProvider()) == null)) {
                    int virtualNodeId = AccessibilityNodeInfo.getVirtualDescendantId(event.getSourceNodeId());
                    AccessibilityNodeInfo node = provider.createAccessibilityNodeInfo(virtualNodeId);
                    setAccessibilityFocus(source, node);
                    break;
                }
                break;
            case 65536:
                if (!(source == null || source.getAccessibilityNodeProvider() == null)) {
                    setAccessibilityFocus(null, null);
                    break;
                }
                break;
        }
        this.mAccessibilityManager.sendAccessibilityEvent(event);
        return true;
    }

    private View getSourceForAccessibilityEvent(AccessibilityEvent event) {
        long sourceNodeId = event.getSourceNodeId();
        int accessibilityViewId = AccessibilityNodeInfo.getAccessibilityViewId(sourceNodeId);
        return AccessibilityNodeIdManager.getInstance().findView(accessibilityViewId);
    }

    private void handleWindowContentChangedEvent(AccessibilityEvent event) {
        View focusedHost = this.mAccessibilityFocusedHost;
        if (focusedHost != null && this.mAccessibilityFocusedVirtualView != null) {
            AccessibilityNodeProvider provider = focusedHost.getAccessibilityNodeProvider();
            if (provider == null) {
                this.mAccessibilityFocusedHost = null;
                this.mAccessibilityFocusedVirtualView = null;
                focusedHost.clearAccessibilityFocusNoCallbacks(0);
                return;
            }
            int changes = event.getContentChangeTypes();
            if ((changes & 1) != 0 || changes == 0) {
                long eventSourceNodeId = event.getSourceNodeId();
                int changedViewId = AccessibilityNodeInfo.getAccessibilityViewId(eventSourceNodeId);
                boolean hostInSubtree = false;
                View root = this.mAccessibilityFocusedHost;
                while (root != null && !hostInSubtree) {
                    if (changedViewId == root.getAccessibilityViewId()) {
                        hostInSubtree = true;
                    } else {
                        ViewParent parent = root.getParent();
                        if (parent instanceof View) {
                            root = (View) parent;
                        } else {
                            root = null;
                        }
                    }
                }
                if (hostInSubtree) {
                    long focusedSourceNodeId = this.mAccessibilityFocusedVirtualView.getSourceNodeId();
                    int focusedChildId = AccessibilityNodeInfo.getVirtualDescendantId(focusedSourceNodeId);
                    Rect oldBounds = this.mTempRect;
                    this.mAccessibilityFocusedVirtualView.getBoundsInScreen(oldBounds);
                    AccessibilityNodeInfo createAccessibilityNodeInfo = provider.createAccessibilityNodeInfo(focusedChildId);
                    this.mAccessibilityFocusedVirtualView = createAccessibilityNodeInfo;
                    if (createAccessibilityNodeInfo == null) {
                        this.mAccessibilityFocusedHost = null;
                        focusedHost.clearAccessibilityFocusNoCallbacks(0);
                        provider.performAction(focusedChildId, AccessibilityNodeInfo.AccessibilityAction.ACTION_CLEAR_ACCESSIBILITY_FOCUS.getId(), null);
                        invalidateRectOnScreen(oldBounds);
                        return;
                    }
                    Rect newBounds = createAccessibilityNodeInfo.getBoundsInScreen();
                    if (!oldBounds.equals(newBounds)) {
                        oldBounds.union(newBounds);
                        invalidateRectOnScreen(oldBounds);
                    }
                }
            }
        }
    }

    @Override // android.view.ViewParent
    public void notifySubtreeAccessibilityStateChanged(View child, View source, int changeType) {
        postSendWindowContentChangedCallback((View) Preconditions.checkNotNull(source), changeType);
    }

    @Override // android.view.ViewParent
    public boolean canResolveLayoutDirection() {
        return true;
    }

    @Override // android.view.ViewParent
    public boolean isLayoutDirectionResolved() {
        return true;
    }

    @Override // android.view.ViewParent
    public int getLayoutDirection() {
        return 0;
    }

    @Override // android.view.ViewParent
    public boolean canResolveTextDirection() {
        return true;
    }

    @Override // android.view.ViewParent
    public boolean isTextDirectionResolved() {
        return true;
    }

    @Override // android.view.ViewParent
    public int getTextDirection() {
        return 1;
    }

    @Override // android.view.ViewParent
    public boolean canResolveTextAlignment() {
        return true;
    }

    @Override // android.view.ViewParent
    public boolean isTextAlignmentResolved() {
        return true;
    }

    @Override // android.view.ViewParent
    public int getTextAlignment() {
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View getCommonPredecessor(View first, View second) {
        if (this.mTempHashSet == null) {
            this.mTempHashSet = new HashSet<>();
        }
        HashSet<View> seen = this.mTempHashSet;
        seen.clear();
        View firstCurrent = first;
        while (firstCurrent != null) {
            seen.add(firstCurrent);
            ViewParent firstCurrentParent = firstCurrent.mParent;
            if (firstCurrentParent instanceof View) {
                firstCurrent = (View) firstCurrentParent;
            } else {
                firstCurrent = null;
            }
        }
        View secondCurrent = second;
        while (secondCurrent != null) {
            if (seen.contains(secondCurrent)) {
                seen.clear();
                return secondCurrent;
            }
            ViewParent secondCurrentParent = secondCurrent.mParent;
            if (secondCurrentParent instanceof View) {
                secondCurrent = (View) secondCurrentParent;
            } else {
                secondCurrent = null;
            }
        }
        seen.clear();
        return null;
    }

    void checkThread() {
        if (this.mThread != Thread.currentThread()) {
            throw new CalledFromWrongThreadException("Only the original thread that created a view hierarchy can touch its views.");
        }
    }

    @Override // android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

    @Override // android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View child, Rect rectangle, boolean immediate) {
        if (rectangle == null) {
            return scrollToRectOrFocus(null, immediate);
        }
        rectangle.offset(child.getLeft() - child.getScrollX(), child.getTop() - child.getScrollY());
        boolean scrolled = scrollToRectOrFocus(rectangle, immediate);
        this.mTempRect.set(rectangle);
        this.mTempRect.offset(0, -this.mCurScrollY);
        this.mTempRect.offset(this.mAttachInfo.mWindowLeft, this.mAttachInfo.mWindowTop);
        try {
            this.mWindowSession.onRectangleOnScreenRequested(this.mWindow, this.mTempRect);
        } catch (RemoteException e) {
        }
        return scrolled;
    }

    @Override // android.view.ViewParent
    public void childHasTransientStateChanged(View child, boolean hasTransientState) {
    }

    @Override // android.view.ViewParent
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return false;
    }

    @Override // android.view.ViewParent
    public void onStopNestedScroll(View target) {
    }

    @Override // android.view.ViewParent
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
    }

    @Override // android.view.ViewParent
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
    }

    @Override // android.view.ViewParent
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
    }

    @Override // android.view.ViewParent
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override // android.view.ViewParent
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return false;
    }

    @Override // android.view.ViewParent
    public boolean onNestedPrePerformAccessibilityAction(View target, int action, Bundle args) {
        return false;
    }

    public void addScrollCaptureCallback(ScrollCaptureCallback callback) {
        if (this.mRootScrollCaptureCallbacks == null) {
            this.mRootScrollCaptureCallbacks = new HashSet<>();
        }
        this.mRootScrollCaptureCallbacks.add(callback);
    }

    public void removeScrollCaptureCallback(ScrollCaptureCallback callback) {
        HashSet<ScrollCaptureCallback> hashSet = this.mRootScrollCaptureCallbacks;
        if (hashSet != null) {
            hashSet.remove(callback);
            if (this.mRootScrollCaptureCallbacks.isEmpty()) {
                this.mRootScrollCaptureCallbacks = null;
            }
        }
    }

    public void dispatchScrollCaptureRequest(IScrollCaptureResponseListener listener) {
        this.mHandler.obtainMessage(36, listener).sendToTarget();
    }

    private void collectRootScrollCaptureTargets(ScrollCaptureSearchResults results) {
        HashSet<ScrollCaptureCallback> hashSet = this.mRootScrollCaptureCallbacks;
        if (hashSet != null) {
            Iterator<ScrollCaptureCallback> it = hashSet.iterator();
            while (it.hasNext()) {
                ScrollCaptureCallback cb = it.next();
                Point offset = new Point(this.mView.getLeft(), this.mView.getTop());
                Rect rect = new Rect(0, 0, this.mView.getWidth(), this.mView.getHeight());
                results.addTarget(new ScrollCaptureTarget(this.mView, rect, offset, cb));
            }
        }
    }

    public void setScrollCaptureRequestTimeout(int timeMillis) {
        this.mScrollCaptureRequestTimeout = timeMillis;
    }

    public long getScrollCaptureRequestTimeout() {
        return this.mScrollCaptureRequestTimeout;
    }

    public void handleScrollCaptureRequest(final IScrollCaptureResponseListener listener) {
        final ScrollCaptureSearchResults results = new ScrollCaptureSearchResults(this.mContext.getMainExecutor());
        collectRootScrollCaptureTargets(results);
        View rootView = getView();
        if (rootView != null) {
            Point point = new Point();
            Rect rect = new Rect(0, 0, rootView.getWidth(), rootView.getHeight());
            getChildVisibleRect(rootView, rect, point);
            Objects.requireNonNull(results);
            rootView.dispatchScrollCaptureSearch(rect, point, new Consumer() { // from class: android.view.ViewRootImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ScrollCaptureSearchResults.this.addTarget((ScrollCaptureTarget) obj);
                }
            });
        }
        Runnable onComplete = new Runnable() { // from class: android.view.ViewRootImpl$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                ViewRootImpl.this.lambda$handleScrollCaptureRequest$9$ViewRootImpl(listener, results);
            }
        };
        results.setOnCompleteListener(onComplete);
        if (!results.isComplete()) {
            ViewRootHandler viewRootHandler = this.mHandler;
            Objects.requireNonNull(results);
            viewRootHandler.postDelayed(new Runnable() { // from class: android.view.ViewRootImpl$$ExternalSyntheticLambda8
                @Override // java.lang.Runnable
                public final void run() {
                    ScrollCaptureSearchResults.this.finish();
                }
            }, getScrollCaptureRequestTimeout());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: dispatchScrollCaptureSearchResponse */
    public void lambda$handleScrollCaptureRequest$9$ViewRootImpl(IScrollCaptureResponseListener listener, ScrollCaptureSearchResults results) {
        ScrollCaptureTarget selectedTarget = results.getTopResult();
        ScrollCaptureResponse.Builder response = new ScrollCaptureResponse.Builder();
        response.setWindowTitle(getTitle().toString());
        StringWriter writer = new StringWriter();
        IndentingPrintWriter pw = new IndentingPrintWriter(writer);
        results.dump(pw);
        pw.flush();
        response.addMessage(writer.toString());
        if (selectedTarget == null) {
            response.setDescription("No scrollable targets found in window");
            try {
                listener.onScrollCaptureResponse(response.build());
            } catch (RemoteException e) {
                Log.e(TAG, "Failed to send scroll capture search result", e);
            }
        } else {
            response.setDescription("Connected");
            Rect boundsInWindow = new Rect();
            View containingView = selectedTarget.getContainingView();
            containingView.getLocationInWindow(this.mAttachInfo.mTmpLocation);
            boundsInWindow.set(selectedTarget.getScrollBounds());
            boundsInWindow.offset(this.mAttachInfo.mTmpLocation[0], this.mAttachInfo.mTmpLocation[1]);
            response.setBoundsInWindow(boundsInWindow);
            Rect boundsOnScreen = new Rect();
            this.mView.getLocationOnScreen(this.mAttachInfo.mTmpLocation);
            boundsOnScreen.set(0, 0, this.mView.getWidth(), this.mView.getHeight());
            boundsOnScreen.offset(this.mAttachInfo.mTmpLocation[0], this.mAttachInfo.mTmpLocation[1]);
            response.setWindowBounds(boundsOnScreen);
            ScrollCaptureConnection connection = new ScrollCaptureConnection(this.mView.getContext().getMainExecutor(), selectedTarget);
            response.setConnection(connection);
            try {
                listener.onScrollCaptureResponse(response.build());
            } catch (RemoteException e2) {
                if (DEBUG_SCROLL_CAPTURE) {
                    Log.w(TAG, "Failed to send scroll capture search response.", e2);
                }
                connection.close();
            }
        }
    }

    private void reportNextDraw() {
        if (!this.mReportNextDraw) {
            drawPending();
        }
        this.mReportNextDraw = true;
    }

    public void setReportNextDraw() {
        reportNextDraw();
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void changeCanvasOpacity(boolean opaque) {
        String str = this.mTag;
        Log.d(str, "changeCanvasOpacity: opaque=" + opaque);
        boolean opaque2 = opaque & ((this.mView.mPrivateFlags & 512) == 0);
        if (this.mAttachInfo.mThreadedRenderer != null) {
            this.mAttachInfo.mThreadedRenderer.setOpaque(opaque2);
        }
    }

    public boolean dispatchUnhandledKeyEvent(KeyEvent event) {
        return this.mUnhandledKeyManager.dispatch(this.mView, event);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class TakenSurfaceHolder extends BaseSurfaceHolder {
        TakenSurfaceHolder() {
        }

        @Override // com.android.internal.view.BaseSurfaceHolder
        public boolean onAllowLockCanvas() {
            return ViewRootImpl.this.mDrawingAllowed;
        }

        @Override // com.android.internal.view.BaseSurfaceHolder
        public void onRelayoutContainer() {
        }

        @Override // com.android.internal.view.BaseSurfaceHolder, android.view.SurfaceHolder
        public void setFormat(int format) {
            ((RootViewSurfaceTaker) ViewRootImpl.this.mView).setSurfaceFormat(format);
        }

        @Override // com.android.internal.view.BaseSurfaceHolder, android.view.SurfaceHolder
        public void setType(int type) {
            ((RootViewSurfaceTaker) ViewRootImpl.this.mView).setSurfaceType(type);
        }

        @Override // com.android.internal.view.BaseSurfaceHolder
        public void onUpdateSurface() {
            throw new IllegalStateException("Shouldn't be here");
        }

        @Override // android.view.SurfaceHolder
        public boolean isCreating() {
            return ViewRootImpl.this.mIsCreating;
        }

        @Override // com.android.internal.view.BaseSurfaceHolder, android.view.SurfaceHolder
        public void setFixedSize(int width, int height) {
            throw new UnsupportedOperationException("Currently only support sizing from layout");
        }

        @Override // android.view.SurfaceHolder
        public void setKeepScreenOn(boolean screenOn) {
            ((RootViewSurfaceTaker) ViewRootImpl.this.mView).setSurfaceKeepScreenOn(screenOn);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class W extends IWindow.Stub {
        final WeakReference<ViewRootImpl> mViewAncestor;
        private final IWindowSession mWindowSession;

        W(ViewRootImpl viewAncestor) {
            this.mViewAncestor = new WeakReference<>(viewAncestor);
            this.mWindowSession = viewAncestor.mWindowSession;
        }

        @Override // android.view.IWindow
        public void resized(ClientWindowFrames frames, boolean reportDraw, MergedConfiguration mergedConfiguration, boolean forceLayout, boolean alwaysConsumeSystemBars, int displayId) {
            ViewRootImpl viewAncestor = this.mViewAncestor.get();
            if (viewAncestor != null) {
                viewAncestor.dispatchResized(frames, reportDraw, mergedConfiguration, forceLayout, alwaysConsumeSystemBars, displayId);
            }
        }

        @Override // android.view.IWindow
        public void locationInParentDisplayChanged(Point offset) {
            ViewRootImpl viewAncestor = this.mViewAncestor.get();
            if (viewAncestor != null) {
                viewAncestor.dispatchLocationInParentDisplayChanged(offset);
            }
        }

        @Override // android.view.IWindow
        public void insetsChanged(InsetsState insetsState, boolean willMove, boolean willResize) {
            ViewRootImpl viewAncestor = this.mViewAncestor.get();
            if (viewAncestor != null) {
                viewAncestor.dispatchInsetsChanged(insetsState, willMove, willResize);
            }
        }

        @Override // android.view.IWindow
        public void insetsControlChanged(InsetsState insetsState, InsetsSourceControl[] activeControls, boolean willMove, boolean willResize) {
            ViewRootImpl viewAncestor = this.mViewAncestor.get();
            if (viewAncestor != null) {
                viewAncestor.dispatchInsetsControlChanged(insetsState, activeControls, willMove, willResize);
            }
        }

        @Override // android.view.IWindow
        public void showInsets(int types, boolean fromIme) {
            ViewRootImpl viewAncestor = this.mViewAncestor.get();
            if (fromIme) {
                ImeTracing.getInstance().triggerClientDump("ViewRootImpl.W#showInsets", viewAncestor.getInsetsController().getHost().getInputMethodManager(), null);
            }
            if (viewAncestor != null) {
                viewAncestor.showInsets(types, fromIme);
            }
        }

        @Override // android.view.IWindow
        public void hideInsets(int types, boolean fromIme) {
            ViewRootImpl viewAncestor = this.mViewAncestor.get();
            if (fromIme) {
                ImeTracing.getInstance().triggerClientDump("ViewRootImpl.W#hideInsets", viewAncestor.getInsetsController().getHost().getInputMethodManager(), null);
            }
            if (viewAncestor != null) {
                viewAncestor.hideInsets(types, fromIme);
            }
        }

        @Override // android.view.IWindow
        public void moved(int newX, int newY) {
            ViewRootImpl viewAncestor = this.mViewAncestor.get();
            if (viewAncestor != null) {
                viewAncestor.dispatchMoved(newX, newY);
            }
        }

        @Override // android.view.IWindow
        public void dispatchAppVisibility(boolean visible) {
            ViewRootImpl viewAncestor = this.mViewAncestor.get();
            if (ViewDebugManager.DEBUG_LIFECYCLE) {
                Log.v(ViewRootImpl.TAG, "dispatchAppVisibility: visible = " + visible + ", viewAncestor = " + viewAncestor);
            }
            if (viewAncestor != null) {
                viewAncestor.dispatchAppVisibility(visible);
            }
        }

        @Override // android.view.IWindow
        public void dispatchGetNewSurface() {
            ViewRootImpl viewAncestor = this.mViewAncestor.get();
            if (viewAncestor != null) {
                viewAncestor.dispatchGetNewSurface();
            }
        }

        @Override // android.view.IWindow
        public void windowFocusChanged(boolean hasFocus, boolean inTouchMode) {
            ViewRootImpl viewAncestor = this.mViewAncestor.get();
            if (ViewRootImpl.DEBUG_IMF) {
                Log.v(ViewRootImpl.TAG, "W windowFocusChanged: hasFocus = " + hasFocus + ", inTouchMode = " + inTouchMode + ", viewAncestor = " + viewAncestor + ", this = " + this);
            }
            if (viewAncestor != null) {
                viewAncestor.windowFocusChanged(hasFocus, inTouchMode);
            }
        }

        private static int checkCallingPermission(String permission) {
            try {
                return ActivityManager.getService().checkPermission(permission, Binder.getCallingPid(), Binder.getCallingUid());
            } catch (RemoteException e) {
                return -1;
            }
        }

        @Override // android.view.IWindow
        public void executeCommand(String command, String parameters, ParcelFileDescriptor out) {
            View view;
            ViewRootImpl viewAncestor = this.mViewAncestor.get();
            if (viewAncestor != null && (view = viewAncestor.mView) != null) {
                if (checkCallingPermission(Manifest.permission.DUMP) == 0) {
                    OutputStream clientStream = null;
                    try {
                        try {
                            try {
                                clientStream = new ParcelFileDescriptor.AutoCloseOutputStream(out);
                                ViewDebug.dispatchCommand(view, command, parameters, clientStream);
                                clientStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                                if (clientStream != null) {
                                    clientStream.close();
                                }
                            }
                        } catch (Throwable th) {
                            if (clientStream != null) {
                                try {
                                    clientStream.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                } else {
                    throw new SecurityException("Insufficient permissions to invoke executeCommand() from pid=" + Binder.getCallingPid() + ", uid=" + Binder.getCallingUid());
                }
            }
        }

        @Override // android.view.IWindow
        public void closeSystemDialogs(String reason) {
            ViewRootImpl viewAncestor = this.mViewAncestor.get();
            if (ViewRootImpl.LOCAL_LOGV) {
                Log.v(ViewRootImpl.TAG, "Close system dialogs in " + viewAncestor + " for " + reason);
            }
            if (viewAncestor != null) {
                viewAncestor.dispatchCloseSystemDialogs(reason);
            }
        }

        @Override // android.view.IWindow
        public void dispatchWallpaperOffsets(float x, float y, float xStep, float yStep, float zoom, boolean sync) {
            if (sync) {
                try {
                    this.mWindowSession.wallpaperOffsetsComplete(asBinder());
                } catch (RemoteException e) {
                    Log.e(ViewRootImpl.TAG, "RemoteException happens when dispatchWallpaperOffsets.", e);
                }
            }
        }

        @Override // android.view.IWindow
        public void dispatchWallpaperCommand(String action, int x, int y, int z, Bundle extras, boolean sync) {
            if (sync) {
                try {
                    this.mWindowSession.wallpaperCommandComplete(asBinder(), null);
                } catch (RemoteException e) {
                    Log.e(ViewRootImpl.TAG, "RemoteException happens when dispatchWallpaperCommand.", e);
                }
            }
        }

        @Override // android.view.IWindow
        public void dispatchDragEvent(DragEvent event) {
            ViewRootImpl viewAncestor = this.mViewAncestor.get();
            if (ViewRootImpl.LOCAL_LOGV || ViewDebugManager.DEBUG_INPUT) {
                Log.v(ViewRootImpl.TAG, "Dispatch drag event " + event + " in " + viewAncestor);
            }
            if (viewAncestor != null) {
                viewAncestor.dispatchDragEvent(event);
            }
        }

        @Override // android.view.IWindow
        public void updatePointerIcon(float x, float y) {
            ViewRootImpl viewAncestor = this.mViewAncestor.get();
            if (viewAncestor != null) {
                viewAncestor.updatePointerIcon(x, y);
            }
        }

        @Override // android.view.IWindow
        public void dispatchWindowShown() {
            ViewRootImpl viewAncestor = this.mViewAncestor.get();
            if (ViewRootImpl.DEBUG_DRAW) {
                Log.v(ViewRootImpl.TAG, "doneAnimating: viewAncestor" + viewAncestor);
            }
            if (viewAncestor != null) {
                viewAncestor.dispatchWindowShown();
            }
        }

        @Override // android.view.IWindow
        public void requestAppKeyboardShortcuts(IResultReceiver receiver, int deviceId) {
            ViewRootImpl viewAncestor = this.mViewAncestor.get();
            if (viewAncestor != null) {
                viewAncestor.dispatchRequestKeyboardShortcuts(receiver, deviceId);
            }
        }

        @Override // android.view.IWindow
        public void requestScrollCapture(IScrollCaptureResponseListener listener) {
            ViewRootImpl viewAncestor = this.mViewAncestor.get();
            if (viewAncestor != null) {
                viewAncestor.dispatchScrollCaptureRequest(listener);
            }
        }
    }

    /* loaded from: classes3.dex */
    public static final class CalledFromWrongThreadException extends AndroidRuntimeException {
        public CalledFromWrongThreadException(String msg) {
            super(msg);
        }
    }

    static HandlerActionQueue getRunQueue() {
        ThreadLocal<HandlerActionQueue> threadLocal = sRunQueues;
        HandlerActionQueue rq = threadLocal.get();
        if (rq != null) {
            return rq;
        }
        HandlerActionQueue rq2 = new HandlerActionQueue();
        threadLocal.set(rq2);
        return rq2;
    }

    private void startDragResizing(Rect initialBounds, boolean fullscreen, Rect systemInsets, Rect stableInsets, int resizeMode) {
        if (!this.mDragResizing) {
            this.mDragResizing = true;
            if (this.mUseMTRenderer) {
                for (int i = this.mWindowCallbacks.size() - 1; i >= 0; i--) {
                    this.mWindowCallbacks.get(i).onWindowDragResizeStart(initialBounds, fullscreen, systemInsets, stableInsets, resizeMode);
                }
            }
            this.mFullRedrawNeeded = true;
        }
    }

    private void endDragResizing() {
        if (this.mDragResizing) {
            this.mDragResizing = false;
            if (this.mUseMTRenderer) {
                for (int i = this.mWindowCallbacks.size() - 1; i >= 0; i--) {
                    this.mWindowCallbacks.get(i).onWindowDragResizeEnd();
                }
            }
            this.mFullRedrawNeeded = true;
        }
    }

    private boolean updateContentDrawBounds() {
        boolean updated = false;
        boolean z = true;
        if (this.mUseMTRenderer) {
            for (int i = this.mWindowCallbacks.size() - 1; i >= 0; i--) {
                updated |= this.mWindowCallbacks.get(i).onContentDrawn(this.mWindowAttributes.surfaceInsets.left, this.mWindowAttributes.surfaceInsets.top, this.mWidth, this.mHeight);
            }
        }
        if (!this.mDragResizing || !this.mReportNextDraw) {
            z = false;
        }
        return updated | z;
    }

    private void requestDrawWindow() {
        if (this.mUseMTRenderer) {
            this.mWindowDrawCountDown = new CountDownLatch(this.mWindowCallbacks.size());
            for (int i = this.mWindowCallbacks.size() - 1; i >= 0; i--) {
                this.mWindowCallbacks.get(i).onRequestDraw(this.mReportNextDraw || (this.mResizeMode == 1 && this.mDragResizing));
            }
        }
    }

    public void reportActivityRelaunched() {
        this.mActivityRelaunched = true;
    }

    public SurfaceControl getSurfaceControl() {
        return this.mSurfaceControl;
    }

    public IBinder getInputToken() {
        WindowInputEventReceiver windowInputEventReceiver = this.mInputEventReceiver;
        if (windowInputEventReceiver == null) {
            return null;
        }
        return windowInputEventReceiver.getToken();
    }

    public IBinder getWindowToken() {
        return this.mAttachInfo.mWindowToken;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public final class AccessibilityInteractionConnectionManager implements AccessibilityManager.AccessibilityStateChangeListener {
        AccessibilityInteractionConnectionManager() {
        }

        @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
        public void onAccessibilityStateChanged(boolean enabled) {
            if (enabled) {
                ensureConnection();
                try {
                    if (ViewRootImpl.this.mAttachInfo.mHasWindowFocus && ViewRootImpl.this.mView != null) {
                        ViewRootImpl.this.mView.sendAccessibilityEvent(32);
                        View focusedView = ViewRootImpl.this.mView.findFocus();
                        if (!(focusedView == null || focusedView == ViewRootImpl.this.mView)) {
                            focusedView.sendAccessibilityEvent(8);
                        }
                    }
                    if (ViewRootImpl.this.mAttachInfo.mLeashedParentToken != null) {
                        ViewRootImpl.this.mAccessibilityManager.associateEmbeddedHierarchy(ViewRootImpl.this.mAttachInfo.mLeashedParentToken, ViewRootImpl.this.mLeashToken);
                    }
                } catch (NullPointerException e) {
                    Slog.w(ViewRootImpl.TAG, "onAccessibilityStateChanged catch NullPointerException " + e);
                }
            } else {
                ensureNoConnection();
                ViewRootImpl.this.mHandler.obtainMessage(21).sendToTarget();
            }
        }

        public void ensureConnection() {
            boolean registered = ViewRootImpl.this.mAttachInfo.mAccessibilityWindowId != -1;
            if (!registered) {
                ViewRootImpl.this.mAttachInfo.mAccessibilityWindowId = ViewRootImpl.this.mAccessibilityManager.addAccessibilityInteractionConnection(ViewRootImpl.this.mWindow, ViewRootImpl.this.mLeashToken, ViewRootImpl.this.mContext.getPackageName(), new AccessibilityInteractionConnection(ViewRootImpl.this));
            }
        }

        public void ensureNoConnection() {
            boolean registered = ViewRootImpl.this.mAttachInfo.mAccessibilityWindowId != -1;
            if (registered) {
                ViewRootImpl.this.mAttachInfo.mAccessibilityWindowId = -1;
                ViewRootImpl.this.mAccessibilityManager.removeAccessibilityInteractionConnection(ViewRootImpl.this.mWindow);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public final class HighContrastTextManager implements AccessibilityManager.HighTextContrastChangeListener {
        HighContrastTextManager() {
            ThreadedRenderer.setHighContrastText(ViewRootImpl.this.mAccessibilityManager.isHighTextContrastEnabled());
        }

        @Override // android.view.accessibility.AccessibilityManager.HighTextContrastChangeListener
        public void onHighTextContrastStateChanged(boolean enabled) {
            ThreadedRenderer.setHighContrastText(enabled);
            ViewRootImpl.this.destroyHardwareResources();
            ViewRootImpl.this.invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class AccessibilityInteractionConnection extends IAccessibilityInteractionConnection.Stub {
        private final WeakReference<ViewRootImpl> mViewRootImpl;

        AccessibilityInteractionConnection(ViewRootImpl viewRootImpl) {
            this.mViewRootImpl = new WeakReference<>(viewRootImpl);
        }

        @Override // android.view.accessibility.IAccessibilityInteractionConnection
        public void findAccessibilityNodeInfoByAccessibilityId(long accessibilityNodeId, Region interactiveRegion, int interactionId, IAccessibilityInteractionConnectionCallback callback, int flags, int interrogatingPid, long interrogatingTid, MagnificationSpec spec, Bundle args) {
            ViewRootImpl viewRootImpl = this.mViewRootImpl.get();
            if (viewRootImpl == null || viewRootImpl.mView == null) {
                try {
                    callback.setFindAccessibilityNodeInfosResult(null, interactionId);
                } catch (RemoteException e) {
                }
            } else {
                viewRootImpl.getAccessibilityInteractionController().findAccessibilityNodeInfoByAccessibilityIdClientThread(accessibilityNodeId, interactiveRegion, interactionId, callback, flags, interrogatingPid, interrogatingTid, spec, args);
            }
        }

        @Override // android.view.accessibility.IAccessibilityInteractionConnection
        public void performAccessibilityAction(long accessibilityNodeId, int action, Bundle arguments, int interactionId, IAccessibilityInteractionConnectionCallback callback, int flags, int interrogatingPid, long interrogatingTid) {
            ViewRootImpl viewRootImpl = this.mViewRootImpl.get();
            if (viewRootImpl == null || viewRootImpl.mView == null) {
                try {
                    callback.setPerformAccessibilityActionResult(false, interactionId);
                } catch (RemoteException e) {
                }
            } else {
                viewRootImpl.getAccessibilityInteractionController().performAccessibilityActionClientThread(accessibilityNodeId, action, arguments, interactionId, callback, flags, interrogatingPid, interrogatingTid);
            }
        }

        @Override // android.view.accessibility.IAccessibilityInteractionConnection
        public void findAccessibilityNodeInfosByViewId(long accessibilityNodeId, String viewId, Region interactiveRegion, int interactionId, IAccessibilityInteractionConnectionCallback callback, int flags, int interrogatingPid, long interrogatingTid, MagnificationSpec spec) {
            ViewRootImpl viewRootImpl = this.mViewRootImpl.get();
            if (viewRootImpl == null || viewRootImpl.mView == null) {
                try {
                    callback.setFindAccessibilityNodeInfoResult(null, interactionId);
                } catch (RemoteException e) {
                }
            } else {
                viewRootImpl.getAccessibilityInteractionController().findAccessibilityNodeInfosByViewIdClientThread(accessibilityNodeId, viewId, interactiveRegion, interactionId, callback, flags, interrogatingPid, interrogatingTid, spec);
            }
        }

        @Override // android.view.accessibility.IAccessibilityInteractionConnection
        public void findAccessibilityNodeInfosByText(long accessibilityNodeId, String text, Region interactiveRegion, int interactionId, IAccessibilityInteractionConnectionCallback callback, int flags, int interrogatingPid, long interrogatingTid, MagnificationSpec spec) {
            ViewRootImpl viewRootImpl = this.mViewRootImpl.get();
            if (viewRootImpl == null || viewRootImpl.mView == null) {
                try {
                    callback.setFindAccessibilityNodeInfosResult(null, interactionId);
                } catch (RemoteException e) {
                }
            } else {
                viewRootImpl.getAccessibilityInteractionController().findAccessibilityNodeInfosByTextClientThread(accessibilityNodeId, text, interactiveRegion, interactionId, callback, flags, interrogatingPid, interrogatingTid, spec);
            }
        }

        @Override // android.view.accessibility.IAccessibilityInteractionConnection
        public void findFocus(long accessibilityNodeId, int focusType, Region interactiveRegion, int interactionId, IAccessibilityInteractionConnectionCallback callback, int flags, int interrogatingPid, long interrogatingTid, MagnificationSpec spec) {
            ViewRootImpl viewRootImpl = this.mViewRootImpl.get();
            if (viewRootImpl == null || viewRootImpl.mView == null) {
                try {
                    callback.setFindAccessibilityNodeInfoResult(null, interactionId);
                } catch (RemoteException e) {
                }
            } else {
                viewRootImpl.getAccessibilityInteractionController().findFocusClientThread(accessibilityNodeId, focusType, interactiveRegion, interactionId, callback, flags, interrogatingPid, interrogatingTid, spec);
            }
        }

        @Override // android.view.accessibility.IAccessibilityInteractionConnection
        public void focusSearch(long accessibilityNodeId, int direction, Region interactiveRegion, int interactionId, IAccessibilityInteractionConnectionCallback callback, int flags, int interrogatingPid, long interrogatingTid, MagnificationSpec spec) {
            ViewRootImpl viewRootImpl = this.mViewRootImpl.get();
            if (viewRootImpl == null || viewRootImpl.mView == null) {
                try {
                    callback.setFindAccessibilityNodeInfoResult(null, interactionId);
                } catch (RemoteException e) {
                }
            } else {
                viewRootImpl.getAccessibilityInteractionController().focusSearchClientThread(accessibilityNodeId, direction, interactiveRegion, interactionId, callback, flags, interrogatingPid, interrogatingTid, spec);
            }
        }

        @Override // android.view.accessibility.IAccessibilityInteractionConnection
        public void clearAccessibilityFocus() {
            ViewRootImpl viewRootImpl = this.mViewRootImpl.get();
            if (viewRootImpl != null && viewRootImpl.mView != null) {
                viewRootImpl.getAccessibilityInteractionController().clearAccessibilityFocusClientThread();
            }
        }

        @Override // android.view.accessibility.IAccessibilityInteractionConnection
        public void notifyOutsideTouch() {
            ViewRootImpl viewRootImpl = this.mViewRootImpl.get();
            if (viewRootImpl != null && viewRootImpl.mView != null) {
                viewRootImpl.getAccessibilityInteractionController().notifyOutsideTouchClientThread();
            }
        }
    }

    public IAccessibilityEmbeddedConnection getAccessibilityEmbeddedConnection() {
        if (this.mAccessibilityEmbeddedConnection == null) {
            this.mAccessibilityEmbeddedConnection = new AccessibilityEmbeddedConnection(this);
        }
        return this.mAccessibilityEmbeddedConnection;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class SendWindowContentChangedAccessibilityEvent implements Runnable {
        private int mChangeTypes;
        public long mLastEventTimeMillis;
        public StackTraceElement[] mOrigin;
        public View mSource;

        private SendWindowContentChangedAccessibilityEvent() {
            this.mChangeTypes = 0;
        }

        @Override // java.lang.Runnable
        public void run() {
            View source = this.mSource;
            this.mSource = null;
            if (source == null) {
                Log.e(ViewRootImpl.TAG, "Accessibility content change has no source");
                return;
            }
            if (AccessibilityManager.getInstance(ViewRootImpl.this.mContext).isEnabled()) {
                this.mLastEventTimeMillis = SystemClock.uptimeMillis();
                AccessibilityEvent event = AccessibilityEvent.obtain();
                event.setEventType(2048);
                event.setContentChangeTypes(this.mChangeTypes);
                source.sendAccessibilityEventUnchecked(event);
            } else {
                this.mLastEventTimeMillis = 0L;
            }
            source.resetSubtreeAccessibilityStateChanged();
            this.mChangeTypes = 0;
        }

        public void runOrPost(View source, int changeType) {
            if (ViewRootImpl.this.mHandler.getLooper() != Looper.myLooper()) {
                CalledFromWrongThreadException e = new CalledFromWrongThreadException("Only the original thread that created a view hierarchy can touch its views.");
                Log.e(ViewRootImpl.TAG, "Accessibility content change on non-UI thread. Future Android versions will throw an exception.", e);
                ViewRootImpl.this.mHandler.removeCallbacks(this);
                if (this.mSource != null) {
                    run();
                }
            }
            View view = this.mSource;
            if (view != null) {
                View predecessor = ViewRootImpl.this.getCommonPredecessor(view, source);
                if (predecessor != null) {
                    predecessor = predecessor.getSelfOrParentImportantForA11y();
                }
                this.mSource = predecessor != null ? predecessor : source;
                this.mChangeTypes |= changeType;
                return;
            }
            this.mSource = source;
            this.mChangeTypes = changeType;
            long timeSinceLastMillis = SystemClock.uptimeMillis() - this.mLastEventTimeMillis;
            long minEventIntevalMillis = ViewConfiguration.getSendRecurringAccessibilityEventsInterval();
            if (timeSinceLastMillis >= minEventIntevalMillis) {
                removeCallbacksAndRun();
            } else {
                ViewRootImpl.this.mHandler.postDelayed(this, minEventIntevalMillis - timeSinceLastMillis);
            }
        }

        public void removeCallbacksAndRun() {
            ViewRootImpl.this.mHandler.removeCallbacks(this);
            run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class UnhandledKeyManager {
        private final SparseArray<WeakReference<View>> mCapturedKeys;
        private WeakReference<View> mCurrentReceiver;
        private boolean mDispatched;

        private UnhandledKeyManager() {
            this.mDispatched = true;
            this.mCapturedKeys = new SparseArray<>();
            this.mCurrentReceiver = null;
        }

        boolean dispatch(View root, KeyEvent event) {
            if (this.mDispatched) {
                return false;
            }
            try {
                Trace.traceBegin(8L, "UnhandledKeyEvent dispatch");
                this.mDispatched = true;
                View consumer = root.dispatchUnhandledKeyEvent(event);
                if (event.getAction() == 0) {
                    int keycode = event.getKeyCode();
                    if (consumer != null && !KeyEvent.isModifierKey(keycode)) {
                        this.mCapturedKeys.put(keycode, new WeakReference<>(consumer));
                    }
                }
                return consumer != null;
            } finally {
                Trace.traceEnd(8L);
            }
        }

        void preDispatch(KeyEvent event) {
            int idx;
            this.mCurrentReceiver = null;
            if (event.getAction() == 1 && (idx = this.mCapturedKeys.indexOfKey(event.getKeyCode())) >= 0) {
                this.mCurrentReceiver = this.mCapturedKeys.valueAt(idx);
                this.mCapturedKeys.removeAt(idx);
            }
        }

        boolean preViewDispatch(KeyEvent event) {
            this.mDispatched = false;
            if (this.mCurrentReceiver == null) {
                this.mCurrentReceiver = this.mCapturedKeys.get(event.getKeyCode());
            }
            WeakReference<View> weakReference = this.mCurrentReceiver;
            if (weakReference == null) {
                return false;
            }
            View target = weakReference.get();
            if (event.getAction() == 1) {
                this.mCurrentReceiver = null;
            }
            if (target != null && target.isAttachedToWindow()) {
                target.onUnhandledKeyEvent(event);
            }
            return true;
        }
    }

    public void dispatchBlurRegions(float[][] regionCopy, long frameNumber) {
        BLASTBufferQueue bLASTBufferQueue;
        SurfaceControl surfaceControl = getSurfaceControl();
        if (surfaceControl.isValid()) {
            SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
            transaction.setBlurRegions(surfaceControl, regionCopy);
            if (useBLAST() && (bLASTBufferQueue = this.mBlastBufferQueue) != null) {
                bLASTBufferQueue.mergeWithNextTransaction(transaction, frameNumber);
            }
        }
    }

    public BackgroundBlurDrawable createBackgroundBlurDrawable() {
        return this.mBlurRegionAggregator.createBackgroundBlurDrawable(this.mContext);
    }

    @Override // android.view.ViewParent
    public void onDescendantUnbufferedRequested() {
        this.mUnbufferedInputSource = this.mView.mUnbufferedInputSource;
    }

    void forceDisableBLAST() {
        this.mForceDisableBLAST = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean useBLAST() {
        return this.mUseBLASTAdapter && !this.mForceDisableBLAST;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getSurfaceSequenceId() {
        return this.mSurfaceSequenceId;
    }

    /* renamed from: mergeWithNextTransaction */
    public void lambda$applyTransactionOnDraw$10$ViewRootImpl(SurfaceControl.Transaction t, long frameNumber) {
        BLASTBufferQueue bLASTBufferQueue = this.mBlastBufferQueue;
        if (bLASTBufferQueue != null) {
            bLASTBufferQueue.mergeWithNextTransaction(t, frameNumber);
        } else {
            t.apply();
        }
    }

    @Override // android.view.AttachedSurfaceControl
    public SurfaceControl.Transaction buildReparentTransaction(SurfaceControl child) {
        if (this.mSurfaceControl.isValid()) {
            return new SurfaceControl.Transaction().reparent(child, this.mSurfaceControl);
        }
        return null;
    }

    @Override // android.view.AttachedSurfaceControl
    public boolean applyTransactionOnDraw(final SurfaceControl.Transaction t) {
        registerRtFrameCallback(new HardwareRenderer.FrameDrawingCallback() { // from class: android.view.ViewRootImpl$$ExternalSyntheticLambda5
            @Override // android.graphics.HardwareRenderer.FrameDrawingCallback
            public final void onFrameDraw(long j) {
                ViewRootImpl.this.lambda$applyTransactionOnDraw$10$ViewRootImpl(t, j);
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getSurfaceTransformHint() {
        return this.mSurfaceControl.getTransformHint();
    }
}
