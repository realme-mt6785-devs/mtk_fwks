package android.view;

import android.animation.AnimationHandler;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.res.CompatibilityInfo;
import android.graphics.Insets;
import android.graphics.Rect;
import android.os.CancellationSignal;
import android.os.Debug;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemProperties;
import android.os.Trace;
import android.util.ArraySet;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import android.util.imetracing.ImeTracing;
import android.util.proto.ProtoOutputStream;
import android.view.InsetsController;
import android.view.SyncRtSurfaceTransactionApplier;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.view.WindowInsetsController;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import android.view.inputmethod.InputMethodManager;
import com.android.internal.graphics.SfVsyncFrameCallbackProvider;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
/* loaded from: classes3.dex */
public class InsetsController implements WindowInsetsController, InsetsAnimationControlCallbacks {
    private static final int ANIMATION_DELAY_DIM_MS = 500;
    private static final int ANIMATION_DURATION_FADE_IN_MS = 500;
    private static final int ANIMATION_DURATION_FADE_OUT_MS = 1500;
    private static final int ANIMATION_DURATION_MOVE_IN_MS = 275;
    private static final int ANIMATION_DURATION_MOVE_OUT_MS = 340;
    private static final int ANIMATION_DURATION_SYNC_IME_MS = 285;
    private static final int ANIMATION_DURATION_UNSYNC_IME_MS = 200;
    public static final int ANIMATION_TYPE_HIDE = 1;
    public static final int ANIMATION_TYPE_NONE = -1;
    public static final int ANIMATION_TYPE_SHOW = 0;
    public static final int ANIMATION_TYPE_USER = 2;
    private static final int FLOATING_IME_BOTTOM_INSET_DP = -80;
    public static final int LAYOUT_INSETS_DURING_ANIMATION_HIDDEN = 1;
    public static final int LAYOUT_INSETS_DURING_ANIMATION_SHOWN = 0;
    private static final int PENDING_CONTROL_TIMEOUT_MS = 2000;
    private static final String TAG = "InsetsController";
    static final boolean WARN = false;
    private final Runnable mAnimCallback;
    private boolean mAnimCallbackScheduled;
    private boolean mAnimationsDisabled;
    private int mCaptionInsetsHeight;
    private final BiFunction<InsetsController, Integer, InsetsSourceConsumer> mConsumerCreator;
    private final ArrayList<WindowInsetsController.OnControllableInsetsChangedListener> mControllableInsetsChangedListeners;
    private int mDisabledUserAnimationInsetsTypes;
    private final Rect mFrame;
    private final Handler mHandler;
    private final Host mHost;
    private Runnable mInvokeControllableInsetsChangedListeners;
    private final InsetsState mLastDispatchedState;
    private WindowInsets mLastInsets;
    private int mLastLegacySoftInputMode;
    private int mLastLegacySystemUiFlags;
    private int mLastLegacyWindowFlags;
    private int mLastStartedAnimTypes;
    private int mLastWindowingMode;
    private Runnable mPendingControlTimeout;
    private PendingControlRequest mPendingImeControlRequest;
    private final InsetsState mRequestedState;
    private final ArraySet<InsetsSourceConsumer> mRequestedVisibilityChanged;
    private final ArrayList<RunningAnimation> mRunningAnimations;
    private final SparseArray<InsetsSourceConsumer> mSourceConsumers;
    private boolean mStartingAnimation;
    private final InsetsState mState;
    private final SparseArray<InsetsSourceControl> mTmpControlArray;
    private final ArrayList<InsetsAnimationControlImpl> mTmpFinishedControls;
    private int mTypesBeingCancelled;
    private int mWindowType;
    private static final Interpolator SYSTEM_BARS_INSETS_INTERPOLATOR = new PathInterpolator(0.4f, 0.0f, 0.2f, 1.0f);
    private static final Interpolator SYSTEM_BARS_ALPHA_INTERPOLATOR = new PathInterpolator(0.3f, 0.0f, 1.0f, 1.0f);
    private static final Interpolator SYSTEM_BARS_DIM_INTERPOLATOR = InsetsController$$ExternalSyntheticLambda3.INSTANCE;
    private static final Interpolator SYNC_IME_INTERPOLATOR = new PathInterpolator(0.2f, 0.0f, 0.0f, 1.0f);
    private static final Interpolator LINEAR_OUT_SLOW_IN_INTERPOLATOR = new PathInterpolator(0.0f, 0.0f, 0.2f, 1.0f);
    private static final Interpolator FAST_OUT_LINEAR_IN_INTERPOLATOR = new PathInterpolator(0.4f, 0.0f, 1.0f, 1.0f);
    static boolean DEBUG = false;
    static boolean DEBUG_PANIC = SystemProperties.getBoolean("persist.sys.assert.panic", false);
    static final int DEBUG_DEPTH = SystemProperties.getInt("debug.insets_controller.depth", 5);
    private static TypeEvaluator<Insets> sEvaluator = InsetsController$$ExternalSyntheticLambda0.INSTANCE;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    @interface AnimationType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    @interface LayoutInsetsDuringAnimation {
    }

    /* loaded from: classes3.dex */
    public interface Host {
        void addOnPreDrawRunnable(Runnable runnable);

        void applySurfaceParams(SyncRtSurfaceTransactionApplier.SurfaceParams... surfaceParamsArr);

        int dipToPx(int i);

        void dispatchWindowInsetsAnimationEnd(WindowInsetsAnimation windowInsetsAnimation);

        void dispatchWindowInsetsAnimationPrepare(WindowInsetsAnimation windowInsetsAnimation);

        WindowInsets dispatchWindowInsetsAnimationProgress(WindowInsets windowInsets, List<WindowInsetsAnimation> list);

        WindowInsetsAnimation.Bounds dispatchWindowInsetsAnimationStart(WindowInsetsAnimation windowInsetsAnimation, WindowInsetsAnimation.Bounds bounds);

        Handler getHandler();

        InputMethodManager getInputMethodManager();

        String getRootViewTitle();

        int getSystemBarsAppearance();

        int getSystemBarsBehavior();

        IBinder getWindowToken();

        boolean hasAnimationCallbacks();

        void notifyInsetsChanged();

        void onInsetsModified(InsetsState insetsState);

        void postInsetsAnimationCallback(Runnable runnable);

        void releaseSurfaceControlFromRt(SurfaceControl surfaceControl);

        void setSystemBarsAppearance(int i, int i2);

        void setSystemBarsBehavior(int i);

        void updateCompatSysUiVisibility(int i, boolean z, boolean z2);

        default boolean isSystemBarsAppearanceControlled() {
            return false;
        }

        default boolean isSystemBarsBehaviorControlled() {
            return false;
        }

        default CompatibilityInfo.Translator getTranslator() {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ float lambda$static$0(float alphaFraction) {
        float fraction = 1.0f - alphaFraction;
        if (fraction <= 0.33333334f) {
            return 1.0f;
        }
        float innerFraction = (fraction - 0.33333334f) / 0.6666666f;
        return 1.0f - SYSTEM_BARS_ALPHA_INTERPOLATOR.getInterpolation(innerFraction);
    }

    /* loaded from: classes3.dex */
    public static class InternalAnimationControlListener implements WindowInsetsAnimationControlListener {
        private ValueAnimator mAnimator;
        private final int mBehavior;
        private WindowInsetsAnimationController mController;
        private final boolean mDisable;
        private final int mFloatingImeBottomInset;
        private final boolean mHasAnimationCallbacks;
        private final int mRequestedTypes;
        private final boolean mShow;
        public IInsetsControllerExt mInsetsControllerExt = InsetsControllerExtPlugin.constructor.newInstance();
        private ThreadLocal<AnimationHandler> mSfAnimationHandlerThreadLocal = new ThreadLocal<AnimationHandler>() { // from class: android.view.InsetsController.InternalAnimationControlListener.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.lang.ThreadLocal
            public AnimationHandler initialValue() {
                AnimationHandler handler = new AnimationHandler();
                handler.setProvider(new SfVsyncFrameCallbackProvider());
                return handler;
            }
        };
        private final long mDurationMs = calculateDurationMs();

        public InternalAnimationControlListener(boolean show, boolean hasAnimationCallbacks, int requestedTypes, int behavior, boolean disable, int floatingImeBottomInset) {
            this.mShow = show;
            this.mHasAnimationCallbacks = hasAnimationCallbacks;
            this.mRequestedTypes = requestedTypes;
            this.mBehavior = behavior;
            this.mDisable = disable;
            this.mFloatingImeBottomInset = floatingImeBottomInset;
        }

        @Override // android.view.WindowInsetsAnimationControlListener
        public void onReady(final WindowInsetsAnimationController controller, int types) {
            Insets hiddenInsets;
            final Insets start;
            final Insets end;
            this.mController = controller;
            if (InsetsController.DEBUG) {
                Log.d(InsetsController.TAG, "default animation onReady types: " + types);
            }
            if (this.mDisable) {
                onAnimationFinish();
                return;
            }
            this.mInsetsControllerExt.setInsetAnimationTid(types);
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            this.mAnimator = ofFloat;
            ofFloat.setDuration(this.mDurationMs);
            this.mAnimator.setInterpolator(new LinearInterpolator());
            Insets hiddenInsets2 = controller.getHiddenStateInsets();
            if (controller.hasZeroInsetsIme()) {
                hiddenInsets = Insets.of(hiddenInsets2.left, hiddenInsets2.top, hiddenInsets2.right, this.mFloatingImeBottomInset);
            } else {
                hiddenInsets = hiddenInsets2;
            }
            if (this.mShow) {
                start = hiddenInsets;
            } else {
                start = controller.getShownStateInsets();
            }
            if (this.mShow) {
                end = controller.getShownStateInsets();
            } else {
                end = hiddenInsets;
            }
            final Interpolator insetsInterpolator = getInsetsInterpolator();
            final Interpolator alphaInterpolator = getAlphaInterpolator();
            this.mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: android.view.InsetsController$InternalAnimationControlListener$$ExternalSyntheticLambda0
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    InsetsController.InternalAnimationControlListener.this.lambda$onReady$0$InsetsController$InternalAnimationControlListener(insetsInterpolator, controller, start, end, alphaInterpolator, valueAnimator);
                }
            });
            this.mAnimator.addListener(new AnimatorListenerAdapter() { // from class: android.view.InsetsController.InternalAnimationControlListener.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    InternalAnimationControlListener.this.onAnimationFinish();
                }
            });
            if (!this.mHasAnimationCallbacks && (WindowInsets.Type.ime() & types) == 0) {
                this.mAnimator.setAnimationHandler(this.mSfAnimationHandlerThreadLocal.get());
            }
            this.mAnimator.start();
        }

        public /* synthetic */ void lambda$onReady$0$InsetsController$InternalAnimationControlListener(Interpolator insetsInterpolator, WindowInsetsAnimationController controller, Insets start, Insets end, Interpolator alphaInterpolator, ValueAnimator animation) {
            float alphaFraction;
            float rawFraction = animation.getAnimatedFraction();
            if (this.mShow) {
                alphaFraction = rawFraction;
            } else {
                alphaFraction = 1.0f - rawFraction;
            }
            float insetsFraction = insetsInterpolator.getInterpolation(rawFraction);
            controller.setInsetsAndAlpha((Insets) InsetsController.sEvaluator.evaluate(insetsFraction, start, end), alphaInterpolator.getInterpolation(alphaFraction), rawFraction);
            if (InsetsController.DEBUG) {
                Log.d(InsetsController.TAG, "Default animation setInsetsAndAlpha fraction: " + insetsFraction);
            }
        }

        @Override // android.view.WindowInsetsAnimationControlListener
        public void onFinished(WindowInsetsAnimationController controller) {
            this.mInsetsControllerExt.setInsetAnimationTid(0);
            if (InsetsController.DEBUG) {
                Log.d(InsetsController.TAG, "InternalAnimationControlListener onFinished types:" + WindowInsets.Type.toString(this.mRequestedTypes));
            }
        }

        @Override // android.view.WindowInsetsAnimationControlListener
        public void onCancelled(WindowInsetsAnimationController controller) {
            ValueAnimator valueAnimator = this.mAnimator;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            if (InsetsController.DEBUG) {
                Log.d(InsetsController.TAG, "InternalAnimationControlListener onCancelled types:" + this.mRequestedTypes);
            }
        }

        protected Interpolator getInsetsInterpolator() {
            if ((this.mRequestedTypes & WindowInsets.Type.ime()) != 0) {
                if (this.mHasAnimationCallbacks) {
                    return InsetsController.SYNC_IME_INTERPOLATOR;
                }
                return this.mShow ? this.mInsetsControllerExt.replaceIMEInterpolator(InsetsController.LINEAR_OUT_SLOW_IN_INTERPOLATOR) : InsetsController.FAST_OUT_LINEAR_IN_INTERPOLATOR;
            } else if (this.mBehavior == 2) {
                return InsetsController.SYSTEM_BARS_INSETS_INTERPOLATOR;
            } else {
                return new Interpolator() { // from class: android.view.InsetsController$InternalAnimationControlListener$$ExternalSyntheticLambda1
                    @Override // android.animation.TimeInterpolator
                    public final float getInterpolation(float f) {
                        return InsetsController.InternalAnimationControlListener.this.lambda$getInsetsInterpolator$1$InsetsController$InternalAnimationControlListener(f);
                    }
                };
            }
        }

        public /* synthetic */ float lambda$getInsetsInterpolator$1$InsetsController$InternalAnimationControlListener(float input) {
            return this.mShow ? 1.0f : 0.0f;
        }

        Interpolator getAlphaInterpolator() {
            if ((this.mRequestedTypes & WindowInsets.Type.ime()) != 0) {
                if (this.mHasAnimationCallbacks) {
                    return InsetsController$InternalAnimationControlListener$$ExternalSyntheticLambda2.INSTANCE;
                }
                if (this.mShow) {
                    return InsetsController$InternalAnimationControlListener$$ExternalSyntheticLambda3.INSTANCE;
                }
                return InsetsController.FAST_OUT_LINEAR_IN_INTERPOLATOR;
            } else if (this.mBehavior == 2) {
                return InsetsController$InternalAnimationControlListener$$ExternalSyntheticLambda4.INSTANCE;
            } else {
                return this.mShow ? InsetsController.SYSTEM_BARS_ALPHA_INTERPOLATOR : InsetsController.SYSTEM_BARS_DIM_INTERPOLATOR;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ float lambda$getAlphaInterpolator$2(float input) {
            return 1.0f;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ float lambda$getAlphaInterpolator$4(float input) {
            return 1.0f;
        }

        protected void onAnimationFinish() {
            this.mController.finish(this.mShow);
            if (InsetsController.DEBUG) {
                Log.d(InsetsController.TAG, "onAnimationFinish showOnFinish: " + this.mShow);
            }
        }

        public long getDurationMs() {
            return this.mDurationMs;
        }

        private long calculateDurationMs() {
            if ((this.mRequestedTypes & WindowInsets.Type.ime()) == 0) {
                return this.mBehavior == 2 ? this.mShow ? 275L : 340L : this.mShow ? 500L : 1500L;
            }
            if (this.mHasAnimationCallbacks) {
                return 285L;
            }
            return this.mInsetsControllerExt.replaceIMEDurationMs(this.mShow, 200);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class RunningAnimation {
        final InsetsAnimationControlRunner runner;
        boolean startDispatched;
        final int type;

        RunningAnimation(InsetsAnimationControlRunner runner, int type) {
            this.runner = runner;
            this.type = type;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class PendingControlRequest {
        final int animationType;
        final CancellationSignal cancellationSignal;
        final long durationMs;
        final Interpolator interpolator;
        final int layoutInsetsDuringAnimation;
        final WindowInsetsAnimationControlListener listener;
        final int types;
        final boolean useInsetsAnimationThread;

        PendingControlRequest(int types, WindowInsetsAnimationControlListener listener, long durationMs, Interpolator interpolator, int animationType, int layoutInsetsDuringAnimation, CancellationSignal cancellationSignal, boolean useInsetsAnimationThread) {
            this.types = types;
            this.listener = listener;
            this.durationMs = durationMs;
            this.interpolator = interpolator;
            this.animationType = animationType;
            this.layoutInsetsDuringAnimation = layoutInsetsDuringAnimation;
            this.cancellationSignal = cancellationSignal;
            this.useInsetsAnimationThread = useInsetsAnimationThread;
        }
    }

    public InsetsController(Host host) {
        this(host, InsetsController$$ExternalSyntheticLambda9.INSTANCE, host.getHandler());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ InsetsSourceConsumer lambda$new$2(InsetsController controller, Integer type) {
        if (type.intValue() == 19) {
            return new ImeInsetsSourceConsumer(controller.mState, InsetsController$$ExternalSyntheticLambda10.INSTANCE, controller);
        }
        return new InsetsSourceConsumer(type.intValue(), controller.mState, InsetsController$$ExternalSyntheticLambda10.INSTANCE, controller);
    }

    public InsetsController(Host host, BiFunction<InsetsController, Integer, InsetsSourceConsumer> consumerCreator, Handler handler) {
        this.mState = new InsetsState();
        this.mLastDispatchedState = new InsetsState();
        this.mRequestedState = new InsetsState();
        this.mFrame = new Rect();
        this.mSourceConsumers = new SparseArray<>();
        this.mTmpControlArray = new SparseArray<>();
        this.mRunningAnimations = new ArrayList<>();
        this.mTmpFinishedControls = new ArrayList<>();
        this.mRequestedVisibilityChanged = new ArraySet<>();
        this.mCaptionInsetsHeight = 0;
        this.mPendingControlTimeout = new Runnable() { // from class: android.view.InsetsController$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                InsetsController.this.abortPendingImeControlRequest();
            }
        };
        this.mControllableInsetsChangedListeners = new ArrayList<>();
        this.mInvokeControllableInsetsChangedListeners = new Runnable() { // from class: android.view.InsetsController$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                InsetsController.this.invokeControllableInsetsChangedListeners();
            }
        };
        this.mHost = host;
        this.mConsumerCreator = consumerCreator;
        this.mHandler = handler;
        this.mAnimCallback = new Runnable() { // from class: android.view.InsetsController$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                InsetsController.this.lambda$new$3$InsetsController();
            }
        };
    }

    public /* synthetic */ void lambda$new$3$InsetsController() {
        this.mAnimCallbackScheduled = false;
        if (!this.mRunningAnimations.isEmpty()) {
            List<WindowInsetsAnimation> runningAnimations = new ArrayList<>();
            InsetsState state = new InsetsState(this.mState, true);
            for (int i = this.mRunningAnimations.size() - 1; i >= 0; i--) {
                RunningAnimation runningAnimation = this.mRunningAnimations.get(i);
                if (DEBUG) {
                    Log.d(TAG, "Running animation type: " + runningAnimation.type);
                }
                InsetsAnimationControlRunner runner = runningAnimation.runner;
                if (runner instanceof InsetsAnimationControlImpl) {
                    InsetsAnimationControlImpl control = (InsetsAnimationControlImpl) runner;
                    if (runningAnimation.startDispatched) {
                        runningAnimations.add(control.getAnimation());
                    }
                    if (control.applyChangeInsets(state)) {
                        this.mTmpFinishedControls.add(control);
                    }
                }
            }
            WindowInsets insets = state.calculateInsets(this.mFrame, this.mState, this.mLastInsets.isRound(), this.mLastInsets.shouldAlwaysConsumeSystemBars(), this.mLastLegacySoftInputMode, this.mLastLegacyWindowFlags, this.mLastLegacySystemUiFlags, this.mWindowType, this.mLastWindowingMode, null);
            this.mHost.dispatchWindowInsetsAnimationProgress(insets, Collections.unmodifiableList(runningAnimations));
            if (DEBUG) {
                for (WindowInsetsAnimation anim : runningAnimations) {
                    Log.d(TAG, String.format("Running animation type: %d, progress: %f", Integer.valueOf(anim.getTypeMask()), Float.valueOf(anim.getInterpolatedFraction())));
                }
            }
            for (int i2 = this.mTmpFinishedControls.size() - 1; i2 >= 0; i2--) {
                dispatchAnimationEnd(this.mTmpFinishedControls.get(i2).getAnimation());
            }
            this.mTmpFinishedControls.clear();
        }
    }

    public void onFrameChanged(Rect frame) {
        if (!this.mFrame.equals(frame)) {
            this.mHost.notifyInsetsChanged();
            this.mFrame.set(frame);
        }
    }

    @Override // android.view.WindowInsetsController
    public InsetsState getState() {
        return this.mState;
    }

    @Override // android.view.WindowInsetsController
    public boolean isRequestedVisible(int type) {
        return getSourceConsumer(type).isRequestedVisible();
    }

    public InsetsState getLastDispatchedState() {
        return this.mLastDispatchedState;
    }

    public boolean onStateChanged(InsetsState state) {
        boolean stateChanged = !this.mState.equals(state, true, false) || !captionInsetsUnchanged();
        if (!stateChanged && this.mLastDispatchedState.equals(state)) {
            return false;
        }
        if (DEBUG || DEBUG_PANIC) {
            Log.d(TAG, "onStateChanged: " + state + " callers=" + Debug.getCallers(DEBUG_DEPTH));
        }
        this.mLastDispatchedState.set(state, true);
        InsetsState lastState = new InsetsState(this.mState, true);
        updateState(state);
        applyLocalVisibilityOverride();
        if (!this.mState.equals(lastState, false, true)) {
            if (DEBUG) {
                Log.d(TAG, "onStateChanged, notifyInsetsChanged");
            }
            this.mHost.notifyInsetsChanged();
        }
        return true;
    }

    private void updateState(InsetsState newState) {
        this.mState.setDisplayFrame(newState.getDisplayFrame());
        this.mState.setDisplayCutout(newState.getDisplayCutout());
        this.mState.setRoundedCorners(newState.getRoundedCorners());
        this.mState.setPrivacyIndicatorBounds(newState.getPrivacyIndicatorBounds());
        int disabledUserAnimationTypes = 0;
        final int[] cancelledUserAnimationTypes = {0};
        for (int type = 0; type < 22; type++) {
            InsetsSource source = newState.peekSource(type);
            if (source != null) {
                int animationType = getAnimationType(type);
                if (!source.isUserControllable()) {
                    int insetsType = InsetsState.toPublicType(type);
                    disabledUserAnimationTypes |= insetsType;
                    if (animationType == 2) {
                        animationType = -1;
                        cancelledUserAnimationTypes[0] = cancelledUserAnimationTypes[0] | insetsType;
                    }
                }
                getSourceConsumer(type).updateSource(source, animationType);
            }
        }
        for (int type2 = 0; type2 < 22; type2++) {
            if (!(type2 == 2 || this.mState.peekSource(type2) == null || newState.peekSource(type2) != null)) {
                this.mState.removeSource(type2);
            }
        }
        updateDisabledUserAnimationTypes(disabledUserAnimationTypes);
        if (cancelledUserAnimationTypes[0] != 0) {
            this.mHandler.post(new Runnable() { // from class: android.view.InsetsController$$ExternalSyntheticLambda8
                @Override // java.lang.Runnable
                public final void run() {
                    InsetsController.this.lambda$updateState$4$InsetsController(cancelledUserAnimationTypes);
                }
            });
        }
    }

    public /* synthetic */ void lambda$updateState$4$InsetsController(int[] cancelledUserAnimationTypes) {
        show(cancelledUserAnimationTypes[0]);
    }

    private void updateDisabledUserAnimationTypes(int disabledUserAnimationTypes) {
        int diff = this.mDisabledUserAnimationInsetsTypes ^ disabledUserAnimationTypes;
        if (diff != 0) {
            int i = this.mSourceConsumers.size() - 1;
            while (true) {
                if (i < 0) {
                    break;
                }
                InsetsSourceConsumer consumer = this.mSourceConsumers.valueAt(i);
                if (consumer.getControl() != null && (InsetsState.toPublicType(consumer.getType()) & diff) != 0) {
                    this.mHandler.removeCallbacks(this.mInvokeControllableInsetsChangedListeners);
                    this.mHandler.post(this.mInvokeControllableInsetsChangedListeners);
                    break;
                }
                i--;
            }
            this.mDisabledUserAnimationInsetsTypes = disabledUserAnimationTypes;
        }
    }

    private boolean captionInsetsUnchanged() {
        if (this.mState.peekSource(2) == null && this.mCaptionInsetsHeight == 0) {
            return true;
        }
        return this.mState.peekSource(2) != null && this.mCaptionInsetsHeight == this.mState.peekSource(2).getFrame().height();
    }

    public WindowInsets calculateInsets(boolean isScreenRound, boolean alwaysConsumeSystemBars, int windowType, int windowingMode, int legacySoftInputMode, int legacyWindowFlags, int legacySystemUiFlags) {
        this.mWindowType = windowType;
        this.mLastWindowingMode = windowingMode;
        this.mLastLegacySoftInputMode = legacySoftInputMode;
        this.mLastLegacyWindowFlags = legacyWindowFlags;
        this.mLastLegacySystemUiFlags = legacySystemUiFlags;
        WindowInsets calculateInsets = this.mState.calculateInsets(this.mFrame, null, isScreenRound, alwaysConsumeSystemBars, legacySoftInputMode, legacyWindowFlags, legacySystemUiFlags, windowType, windowingMode, null);
        this.mLastInsets = calculateInsets;
        return calculateInsets;
    }

    public Rect calculateVisibleInsets(int softInputMode) {
        return this.mState.calculateVisibleInsets(this.mFrame, softInputMode);
    }

    public void onControlsChanged(InsetsSourceControl[] activeControls) {
        boolean requestedVisibilityChanged;
        boolean imeRequestedVisible;
        if (activeControls != null) {
            for (InsetsSourceControl activeControl : activeControls) {
                if (activeControl != null) {
                    this.mTmpControlArray.put(activeControl.getType(), activeControl);
                }
            }
        }
        boolean requestedStateStale = false;
        int[] showTypes = new int[1];
        int[] hideTypes = new int[1];
        for (int i = this.mSourceConsumers.size() - 1; i >= 0; i--) {
            InsetsSourceConsumer consumer = this.mSourceConsumers.valueAt(i);
            consumer.setControl(this.mTmpControlArray.get(consumer.getType()), showTypes, hideTypes);
        }
        for (int i2 = this.mTmpControlArray.size() - 1; i2 >= 0; i2--) {
            InsetsSourceControl control = this.mTmpControlArray.valueAt(i2);
            int type = control.getType();
            InsetsSourceConsumer consumer2 = getSourceConsumer(type);
            consumer2.setControl(control, showTypes, hideTypes);
            if (!requestedStateStale) {
                boolean requestedVisible = consumer2.isRequestedVisible();
                if (requestedVisible != this.mRequestedState.getSourceOrDefaultVisibility(type)) {
                    requestedVisibilityChanged = true;
                } else {
                    requestedVisibilityChanged = false;
                }
                if (type != 19 || !requestedVisible) {
                    imeRequestedVisible = false;
                } else {
                    imeRequestedVisible = true;
                }
                if (requestedVisibilityChanged || imeRequestedVisible) {
                    requestedStateStale = true;
                } else {
                    requestedStateStale = false;
                }
            }
        }
        if (this.mTmpControlArray.size() > 0) {
            for (int i3 = this.mRunningAnimations.size() - 1; i3 >= 0; i3--) {
                this.mRunningAnimations.get(i3).runner.updateSurfacePosition(this.mTmpControlArray);
            }
        }
        this.mTmpControlArray.clear();
        int animatingTypes = invokeControllableInsetsChangedListeners();
        showTypes[0] = showTypes[0] & (~animatingTypes);
        hideTypes[0] = hideTypes[0] & (~animatingTypes);
        if (showTypes[0] != 0) {
            applyAnimation(showTypes[0], true, false);
        }
        if (hideTypes[0] != 0) {
            applyAnimation(hideTypes[0], false, false);
        }
        updateRequestedVisibility();
    }

    @Override // android.view.WindowInsetsController
    public void show(int types) {
        show(types, false);
    }

    public void show(int types, boolean fromIme) {
        if (DEBUG || DEBUG_PANIC) {
            Log.d(TAG, String.format("show: types=%d fromIme=%b callers=%s", Integer.valueOf(types), Boolean.valueOf(fromIme), Debug.getCallers(DEBUG_DEPTH)));
        }
        if ((types & WindowInsets.Type.ime()) != 0) {
            Log.d(TAG, "show(ime(), fromIme=" + fromIme + ")");
        }
        if (fromIme) {
            ImeTracing.getInstance().triggerClientDump("InsetsController#show", this.mHost.getInputMethodManager(), null);
            Trace.asyncTraceEnd(8L, "IC.showRequestFromApiToImeReady", 0);
            Trace.asyncTraceBegin(8L, "IC.showRequestFromIme", 0);
        } else {
            Trace.asyncTraceBegin(8L, "IC.showRequestFromApi", 0);
            Trace.asyncTraceBegin(8L, "IC.showRequestFromApiToImeReady", 0);
        }
        if (!fromIme || this.mPendingImeControlRequest == null) {
            int typesReady = 0;
            ArraySet<Integer> internalTypes = InsetsState.toInternalType(types);
            for (int i = internalTypes.size() - 1; i >= 0; i--) {
                int internalType = internalTypes.valueAt(i).intValue();
                int animationType = getAnimationType(internalType);
                InsetsSourceConsumer consumer = getSourceConsumer(internalType);
                if ((!consumer.isRequestedVisible() || animationType != -1) && animationType != 0) {
                    if (!fromIme || animationType != 2) {
                        typesReady |= InsetsState.toPublicType(consumer.getType());
                    }
                } else if (DEBUG) {
                    Log.d(TAG, String.format("show ignored for type: %d animType: %d requestedVisible: %s", Integer.valueOf(consumer.getType()), Integer.valueOf(animationType), Boolean.valueOf(consumer.isRequestedVisible())));
                }
            }
            if (DEBUG) {
                Log.d(TAG, "show typesReady: " + typesReady);
            }
            applyAnimation(typesReady, true, fromIme);
            return;
        }
        PendingControlRequest pendingRequest = this.mPendingImeControlRequest;
        this.mPendingImeControlRequest = null;
        this.mHandler.removeCallbacks(this.mPendingControlTimeout);
        controlAnimationUnchecked(pendingRequest.types, pendingRequest.cancellationSignal, pendingRequest.listener, null, true, pendingRequest.durationMs, pendingRequest.interpolator, pendingRequest.animationType, pendingRequest.layoutInsetsDuringAnimation, pendingRequest.useInsetsAnimationThread);
    }

    @Override // android.view.WindowInsetsController
    public void hide(int types) {
        hide(types, false);
    }

    public void hide(int types, boolean fromIme) {
        if (DEBUG || DEBUG_PANIC) {
            Log.d(TAG, String.format("hide: types=%d fromIme=%b callers=%s", Integer.valueOf(types), Boolean.valueOf(fromIme), Debug.getCallers(DEBUG_DEPTH)));
        }
        if (fromIme) {
            ImeTracing.getInstance().triggerClientDump("InsetsController#hide", this.mHost.getInputMethodManager(), null);
            Trace.asyncTraceBegin(8L, "IC.hideRequestFromIme", 0);
        } else {
            Trace.asyncTraceBegin(8L, "IC.hideRequestFromApi", 0);
        }
        int typesReady = 0;
        ArraySet<Integer> internalTypes = InsetsState.toInternalType(types);
        for (int i = internalTypes.size() - 1; i >= 0; i--) {
            int internalType = internalTypes.valueAt(i).intValue();
            int animationType = getAnimationType(internalType);
            InsetsSourceConsumer consumer = getSourceConsumer(internalType);
            if ((consumer.isRequestedVisible() || animationType != -1) && animationType != 1) {
                typesReady |= InsetsState.toPublicType(consumer.getType());
            }
        }
        applyAnimation(typesReady, false, fromIme);
    }

    @Override // android.view.WindowInsetsController
    public void controlWindowInsetsAnimation(int types, long durationMillis, Interpolator interpolator, CancellationSignal cancellationSignal, WindowInsetsAnimationControlListener listener) {
        controlWindowInsetsAnimation(types, cancellationSignal, listener, false, durationMillis, interpolator, 2);
    }

    private void controlWindowInsetsAnimation(int types, CancellationSignal cancellationSignal, WindowInsetsAnimationControlListener listener, boolean fromIme, long durationMs, Interpolator interpolator, int animationType) {
        if ((this.mState.calculateUncontrollableInsetsFromFrame(this.mFrame) & types) != 0) {
            listener.onCancelled(null);
            return;
        }
        if (fromIme) {
            ImeTracing.getInstance().triggerClientDump("InsetsController#controlWindowInsetsAnimation", this.mHost.getInputMethodManager(), null);
        }
        controlAnimationUnchecked(types, cancellationSignal, listener, this.mFrame, fromIme, durationMs, interpolator, animationType, getLayoutInsetsDuringAnimationMode(types), false);
    }

    private void controlAnimationUnchecked(int types, CancellationSignal cancellationSignal, WindowInsetsAnimationControlListener listener, Rect frame, boolean fromIme, long durationMs, Interpolator interpolator, int animationType, int layoutInsetsDuringAnimation, boolean useInsetsAnimationThread) {
        int types2;
        int types3;
        String str;
        final InsetsAnimationControlRunner runner;
        ProtoOutputStream protoOutputStream;
        boolean z;
        if ((types & this.mTypesBeingCancelled) == 0) {
            if (animationType == 2) {
                int disabledTypes = types & this.mDisabledUserAnimationInsetsTypes;
                if (DEBUG) {
                    Log.d(TAG, "user animation disabled types: " + disabledTypes);
                }
                int types4 = types & (~this.mDisabledUserAnimationInsetsTypes);
                if (fromIme && (WindowInsets.Type.ime() & disabledTypes) != 0 && !this.mState.getSource(19).isVisible()) {
                    getSourceConsumer(19).hide(true, animationType);
                }
                types2 = types4;
            } else {
                types2 = types;
            }
            if (types2 == 0) {
                listener.onCancelled(null);
                updateRequestedVisibility();
                if (DEBUG) {
                    Log.d(TAG, "no types to animate in controlAnimationUnchecked");
                    return;
                }
                return;
            }
            cancelExistingControllers(types2);
            if (DEBUG) {
                Log.d(TAG, "controlAnimation types: " + types2);
            }
            this.mLastStartedAnimTypes |= types2;
            ArraySet<Integer> internalTypes = InsetsState.toInternalType(types2);
            SparseArray<InsetsSourceControl> controls = new SparseArray<>();
            Pair<Integer, Boolean> typesReadyPair = collectSourceControls(fromIme, internalTypes, controls, animationType);
            int typesReady = typesReadyPair.first.intValue();
            boolean imeReady = typesReadyPair.second.booleanValue();
            if (DEBUG) {
                Log.d(TAG, String.format("controlAnimationUnchecked, typesReady: %s imeReady: %s", Integer.valueOf(typesReady), Boolean.valueOf(imeReady)));
            }
            if (!imeReady) {
                abortPendingImeControlRequest();
                final PendingControlRequest request = new PendingControlRequest(types2, listener, durationMs, interpolator, animationType, layoutInsetsDuringAnimation, cancellationSignal, useInsetsAnimationThread);
                this.mPendingImeControlRequest = request;
                this.mHandler.postDelayed(this.mPendingControlTimeout, 2000L);
                if (DEBUG) {
                    Log.d(TAG, "Ime not ready. Create pending request");
                }
                if (cancellationSignal != null) {
                    cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: android.view.InsetsController$$ExternalSyntheticLambda2
                        @Override // android.os.CancellationSignal.OnCancelListener
                        public final void onCancel() {
                            InsetsController.this.lambda$controlAnimationUnchecked$5$InsetsController(request);
                        }
                    });
                }
                updateRequestedVisibility();
                Trace.asyncTraceEnd(8L, "IC.showRequestFromApi", 0);
            } else if (typesReady == 0) {
                if (DEBUG) {
                    Log.d(TAG, "No types ready. onCancelled()");
                }
                listener.onCancelled(null);
                updateRequestedVisibility();
            } else {
                if (useInsetsAnimationThread) {
                    InsetsState insetsState = this.mState;
                    CompatibilityInfo.Translator translator = this.mHost.getTranslator();
                    Handler handler = this.mHost.getHandler();
                    protoOutputStream = null;
                    str = TAG;
                    types3 = types2;
                    runner = new InsetsAnimationThreadControlRunner(controls, frame, insetsState, listener, typesReady, this, durationMs, interpolator, animationType, layoutInsetsDuringAnimation, translator, handler);
                } else {
                    protoOutputStream = null;
                    str = TAG;
                    types3 = types2;
                    runner = new InsetsAnimationControlImpl(controls, frame, this.mState, listener, typesReady, this, durationMs, interpolator, animationType, layoutInsetsDuringAnimation, this.mHost.getTranslator());
                }
                if ((typesReady & WindowInsets.Type.ime()) != 0) {
                    ImeTracing.getInstance().triggerClientDump("InsetsAnimationControlImpl", this.mHost.getInputMethodManager(), protoOutputStream);
                }
                this.mRunningAnimations.add(new RunningAnimation(runner, animationType));
                if (DEBUG) {
                    Log.d(str, "Animation added to runner. useInsetsAnimationThread: " + useInsetsAnimationThread);
                }
                if (cancellationSignal != null) {
                    cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: android.view.InsetsController$$ExternalSyntheticLambda1
                        @Override // android.os.CancellationSignal.OnCancelListener
                        public final void onCancel() {
                            InsetsController.this.lambda$controlAnimationUnchecked$6$InsetsController(runner);
                        }
                    });
                    z = false;
                } else {
                    z = false;
                    Trace.asyncTraceBegin(8L, "IC.pendingAnim", 0);
                }
                if (layoutInsetsDuringAnimation == 0) {
                    showDirectly(types3, fromIme);
                } else {
                    hideDirectly(types3, z, animationType, fromIme);
                }
                updateRequestedVisibility();
            }
        } else {
            throw new IllegalStateException("Cannot start a new insets animation of " + WindowInsets.Type.toString(types) + " while an existing " + WindowInsets.Type.toString(this.mTypesBeingCancelled) + " is being cancelled.");
        }
    }

    public /* synthetic */ void lambda$controlAnimationUnchecked$5$InsetsController(PendingControlRequest request) {
        if (this.mPendingImeControlRequest == request) {
            if (DEBUG) {
                Log.d(TAG, "Cancellation signal abortPendingImeControlRequest");
            }
            abortPendingImeControlRequest();
        }
    }

    public /* synthetic */ void lambda$controlAnimationUnchecked$6$InsetsController(InsetsAnimationControlRunner runner) {
        cancelAnimation(runner, true);
    }

    private Pair<Integer, Boolean> collectSourceControls(boolean fromIme, ArraySet<Integer> internalTypes, SparseArray<InsetsSourceControl> controls, int animationType) {
        int typesReady = 0;
        boolean imeReady = true;
        boolean show = true;
        int i = internalTypes.size() - 1;
        while (i >= 0) {
            InsetsSourceConsumer consumer = getSourceConsumer(internalTypes.valueAt(i).intValue());
            if (animationType != 0 && animationType != 2) {
                show = false;
            }
            boolean canRun = false;
            if (show) {
                if (fromIme) {
                    ImeTracing.getInstance().triggerClientDump("ImeInsetsSourceConsumer#requestShow", this.mHost.getInputMethodManager(), null);
                }
                switch (consumer.requestShow(fromIme)) {
                    case 0:
                        canRun = true;
                        break;
                    case 1:
                        imeReady = false;
                        if (DEBUG) {
                            Log.d(TAG, "requestShow IME_SHOW_DELAYED");
                            break;
                        }
                        break;
                }
            } else {
                if (!fromIme) {
                    consumer.notifyHidden();
                }
                canRun = true;
            }
            if (canRun) {
                InsetsSourceControl control = consumer.getControl();
                if (control != null && control.getLeash() != null) {
                    controls.put(consumer.getType(), new InsetsSourceControl(control));
                    typesReady |= InsetsState.toPublicType(consumer.getType());
                } else if (animationType == 0) {
                    if (DEBUG) {
                        Log.d(TAG, "collectSourceControls no control for show(). fromIme: " + fromIme);
                    }
                    if (fromIme) {
                        ImeTracing.getInstance().triggerClientDump("InsetsSourceConsumer#show", this.mHost.getInputMethodManager(), null);
                    }
                    consumer.show(fromIme);
                } else if (animationType == 1) {
                    if (fromIme) {
                        ImeTracing.getInstance().triggerClientDump("InsetsSourceConsumer#hide", this.mHost.getInputMethodManager(), null);
                    }
                    consumer.hide();
                }
            }
            i--;
            show = true;
        }
        return new Pair<>(Integer.valueOf(typesReady), Boolean.valueOf(imeReady));
    }

    private int getLayoutInsetsDuringAnimationMode(int types) {
        ArraySet<Integer> internalTypes = InsetsState.toInternalType(types);
        for (int i = internalTypes.size() - 1; i >= 0; i--) {
            InsetsSourceConsumer consumer = this.mSourceConsumers.get(internalTypes.valueAt(i).intValue());
            if (consumer != null && !consumer.isRequestedVisible()) {
                return 0;
            }
        }
        return 1;
    }

    private void cancelExistingControllers(int types) {
        int originalmTypesBeingCancelled = this.mTypesBeingCancelled;
        this.mTypesBeingCancelled |= types;
        try {
            for (int i = this.mRunningAnimations.size() - 1; i >= 0; i--) {
                InsetsAnimationControlRunner control = this.mRunningAnimations.get(i).runner;
                if ((control.getTypes() & types) != 0) {
                    cancelAnimation(control, true);
                }
            }
            int i2 = WindowInsets.Type.ime();
            if ((i2 & types) != 0) {
                abortPendingImeControlRequest();
            }
        } finally {
            this.mTypesBeingCancelled = originalmTypesBeingCancelled;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void abortPendingImeControlRequest() {
        PendingControlRequest pendingControlRequest = this.mPendingImeControlRequest;
        if (pendingControlRequest != null) {
            pendingControlRequest.listener.onCancelled(null);
            this.mPendingImeControlRequest = null;
            this.mHandler.removeCallbacks(this.mPendingControlTimeout);
            if (DEBUG) {
                Log.d(TAG, "abortPendingImeControlRequest");
            }
        }
    }

    @Override // android.view.InsetsAnimationControlCallbacks
    public void notifyFinished(InsetsAnimationControlRunner runner, boolean shown) {
        boolean isRunning = false;
        int i = this.mRunningAnimations.size() - 1;
        while (true) {
            if (i < 0) {
                break;
            } else if (this.mRunningAnimations.get(i).runner == runner) {
                isRunning = true;
                break;
            } else {
                i--;
            }
        }
        if (isRunning || (runner.getTypes() & WindowInsets.Type.ime()) != 0) {
            cancelAnimation(runner, false);
            if (DEBUG) {
                Log.d(TAG, "notifyFinished. shown: " + shown);
            }
            if (shown) {
                showDirectly(runner.getTypes(), true);
            } else {
                hideDirectly(runner.getTypes(), true, runner.getAnimationType(), true);
            }
        } else {
            Log.d(TAG, "notifyFinished. No animation running, is it cancelled in controlAnimationUnchecked?");
        }
    }

    @Override // android.view.InsetsAnimationControlCallbacks
    public void applySurfaceParams(SyncRtSurfaceTransactionApplier.SurfaceParams... params) {
        this.mHost.applySurfaceParams(params);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyControlRevoked(InsetsSourceConsumer consumer) {
        int types = InsetsState.toPublicType(consumer.getType());
        for (int i = this.mRunningAnimations.size() - 1; i >= 0; i--) {
            InsetsAnimationControlRunner control = this.mRunningAnimations.get(i).runner;
            control.notifyControlRevoked(types);
            if (control.getControllingTypes() == 0) {
                cancelAnimation(control, true);
            }
        }
        int i2 = consumer.getType();
        if (i2 == 19) {
            abortPendingImeControlRequest();
        }
    }

    private void cancelAnimation(InsetsAnimationControlRunner control, boolean invokeCallback) {
        if (DEBUG) {
            Log.d(TAG, String.format("cancelAnimation of types: %d, animType: %d", Integer.valueOf(control.getTypes()), Integer.valueOf(control.getAnimationType())));
        }
        if (invokeCallback) {
            control.cancel();
        }
        boolean stateChanged = false;
        int i = this.mRunningAnimations.size() - 1;
        while (true) {
            if (i < 0) {
                break;
            }
            RunningAnimation runningAnimation = this.mRunningAnimations.get(i);
            if (runningAnimation.runner == control) {
                this.mRunningAnimations.remove(i);
                ArraySet<Integer> types = InsetsState.toInternalType(control.getTypes());
                for (int j = types.size() - 1; j >= 0; j--) {
                    if (types.valueAt(j).intValue() == 19) {
                        ImeTracing.getInstance().triggerClientDump("InsetsSourceConsumer#notifyAnimationFinished", this.mHost.getInputMethodManager(), null);
                    }
                    stateChanged |= getSourceConsumer(types.valueAt(j).intValue()).notifyAnimationFinished();
                }
                if (invokeCallback) {
                    dispatchAnimationEnd(runningAnimation.runner.getAnimation());
                }
            } else {
                i--;
            }
        }
        if (stateChanged) {
            this.mHost.notifyInsetsChanged();
        }
    }

    private void applyLocalVisibilityOverride() {
        for (int i = this.mSourceConsumers.size() - 1; i >= 0; i--) {
            InsetsSourceConsumer consumer = this.mSourceConsumers.valueAt(i);
            consumer.applyLocalVisibilityOverride();
        }
    }

    public InsetsSourceConsumer getSourceConsumer(int type) {
        InsetsSourceConsumer controller = this.mSourceConsumers.get(type);
        if (controller != null) {
            return controller;
        }
        InsetsSourceConsumer controller2 = this.mConsumerCreator.apply(this, Integer.valueOf(type));
        this.mSourceConsumers.put(type, controller2);
        return controller2;
    }

    public void notifyVisibilityChanged() {
        this.mHost.notifyInsetsChanged();
    }

    public void updateCompatSysUiVisibility(int type, boolean visible, boolean hasControl) {
        this.mHost.updateCompatSysUiVisibility(type, visible, hasControl);
    }

    public void onWindowFocusGained(boolean hasViewFocused) {
        getSourceConsumer(19).onWindowFocusGained(hasViewFocused);
    }

    public void onWindowFocusLost() {
        getSourceConsumer(19).onWindowFocusLost();
    }

    public int getAnimationType(int type) {
        for (int i = this.mRunningAnimations.size() - 1; i >= 0; i--) {
            InsetsAnimationControlRunner control = this.mRunningAnimations.get(i).runner;
            if (control.controlsInternalType(type)) {
                return this.mRunningAnimations.get(i).type;
            }
        }
        return -1;
    }

    public void onRequestedVisibilityChanged(InsetsSourceConsumer consumer) {
        this.mRequestedVisibilityChanged.add(consumer);
    }

    private void updateRequestedVisibility() {
        boolean requestedVisible;
        boolean changed = false;
        for (int i = this.mRequestedVisibilityChanged.size() - 1; i >= 0; i--) {
            InsetsSourceConsumer consumer = this.mRequestedVisibilityChanged.valueAt(i);
            int type = consumer.getType();
            if (!(type == 2 || (requestedVisible = consumer.isRequestedVisible()) == this.mRequestedState.getSourceOrDefaultVisibility(type))) {
                this.mRequestedState.getSource(type).setVisible(requestedVisible);
                changed = true;
            }
        }
        this.mRequestedVisibilityChanged.clear();
        if (changed) {
            this.mHost.onInsetsModified(this.mRequestedState);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InsetsState getRequestedVisibility() {
        return this.mRequestedState;
    }

    public void applyAnimation(int types, boolean show, boolean fromIme) {
        boolean skipAnim = false;
        if ((WindowInsets.Type.ime() & types) != 0) {
            InsetsSourceConsumer consumer = this.mSourceConsumers.get(19);
            InsetsSourceControl imeControl = consumer != null ? consumer.getControl() : null;
            if (imeControl != null) {
                skipAnim = imeControl.getAndClearSkipAnimationOnce() && show && consumer.hasViewFocusWhenWindowFocusGain();
            }
        }
        applyAnimation(types, show, fromIme, skipAnim);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void applyAnimation(int types, boolean show, boolean fromIme, boolean skipAnim) {
        if (types != 0) {
            boolean hasAnimationCallbacks = this.mHost.hasAnimationCallbacks();
            InternalAnimationControlListener listener = new InternalAnimationControlListener(show, hasAnimationCallbacks, types, this.mHost.getSystemBarsBehavior(), skipAnim || this.mAnimationsDisabled, this.mHost.dipToPx(-80));
            controlAnimationUnchecked(types, null, listener, this.mState.getDisplayFrame(), fromIme, listener.getDurationMs(), listener.getInsetsInterpolator(), !show ? 1 : 0, !show, !hasAnimationCallbacks);
        } else if (DEBUG) {
            Log.d(TAG, "applyAnimation, nothing to animate");
        }
    }

    private void hideDirectly(int types, boolean animationFinished, int animationType, boolean fromIme) {
        if ((WindowInsets.Type.ime() & types) != 0) {
            ImeTracing.getInstance().triggerClientDump("InsetsController#hideDirectly", this.mHost.getInputMethodManager(), null);
        }
        ArraySet<Integer> internalTypes = InsetsState.toInternalType(types);
        for (int i = internalTypes.size() - 1; i >= 0; i--) {
            getSourceConsumer(internalTypes.valueAt(i).intValue()).hide(animationFinished, animationType);
        }
        updateRequestedVisibility();
        if (fromIme) {
            Trace.asyncTraceEnd(8L, "IC.hideRequestFromIme", 0);
        }
    }

    private void showDirectly(int types, boolean fromIme) {
        if ((WindowInsets.Type.ime() & types) != 0) {
            ImeTracing.getInstance().triggerClientDump("InsetsController#showDirectly", this.mHost.getInputMethodManager(), null);
        }
        ArraySet<Integer> internalTypes = InsetsState.toInternalType(types);
        int i = internalTypes.size();
        while (true) {
            i--;
            if (i < 0) {
                break;
            }
            getSourceConsumer(internalTypes.valueAt(i).intValue()).show(false);
        }
        updateRequestedVisibility();
        if (fromIme) {
            Trace.asyncTraceEnd(8L, "IC.showRequestFromIme", 0);
        }
    }

    public void cancelExistingAnimations() {
        cancelExistingControllers(WindowInsets.Type.all());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dump(String prefix, PrintWriter pw) {
        pw.print(prefix);
        pw.println("InsetsController:");
        InsetsState insetsState = this.mState;
        insetsState.dump(prefix + "  ", pw);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dumpDebug(ProtoOutputStream proto, long fieldId) {
        long token = proto.start(fieldId);
        this.mState.dumpDebug(proto, 1146756268033L);
        for (int i = this.mRunningAnimations.size() - 1; i >= 0; i--) {
            InsetsAnimationControlRunner runner = this.mRunningAnimations.get(i).runner;
            runner.dumpDebug(proto, 2246267895810L);
        }
        proto.end(token);
    }

    @Override // android.view.InsetsAnimationControlCallbacks
    public void startAnimation(final InsetsAnimationControlImpl controller, final WindowInsetsAnimationControlListener listener, final int types, final WindowInsetsAnimation animation, final WindowInsetsAnimation.Bounds bounds) {
        this.mHost.dispatchWindowInsetsAnimationPrepare(animation);
        this.mHost.addOnPreDrawRunnable(new Runnable() { // from class: android.view.InsetsController$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                InsetsController.this.lambda$startAnimation$7$InsetsController(controller, types, animation, bounds, listener);
            }
        });
    }

    public /* synthetic */ void lambda$startAnimation$7$InsetsController(InsetsAnimationControlImpl controller, int types, WindowInsetsAnimation animation, WindowInsetsAnimation.Bounds bounds, WindowInsetsAnimationControlListener listener) {
        if (!controller.isCancelled()) {
            Trace.asyncTraceBegin(8L, "InsetsAnimation: " + WindowInsets.Type.toString(types), types);
            for (int i = this.mRunningAnimations.size() - 1; i >= 0; i--) {
                RunningAnimation runningAnimation = this.mRunningAnimations.get(i);
                if (runningAnimation.runner == controller) {
                    runningAnimation.startDispatched = true;
                }
            }
            Trace.asyncTraceEnd(8L, "IC.pendingAnim", 0);
            this.mHost.dispatchWindowInsetsAnimationStart(animation, bounds);
            this.mStartingAnimation = true;
            controller.mReadyDispatched = true;
            listener.onReady(controller, types);
            this.mStartingAnimation = false;
        }
    }

    public void dispatchAnimationEnd(WindowInsetsAnimation animation) {
        Trace.asyncTraceEnd(8L, "InsetsAnimation: " + WindowInsets.Type.toString(animation.getTypeMask()), animation.getTypeMask());
        this.mHost.dispatchWindowInsetsAnimationEnd(animation);
    }

    @Override // android.view.InsetsAnimationControlCallbacks
    public void scheduleApplyChangeInsets(InsetsAnimationControlRunner runner) {
        if (this.mStartingAnimation || runner.getAnimationType() == 2) {
            this.mAnimCallback.run();
            this.mAnimCallbackScheduled = false;
        } else if (!this.mAnimCallbackScheduled) {
            this.mHost.postInsetsAnimationCallback(this.mAnimCallback);
            this.mAnimCallbackScheduled = true;
        }
    }

    @Override // android.view.WindowInsetsController
    public void setSystemBarsAppearance(int appearance, int mask) {
        this.mHost.setSystemBarsAppearance(appearance, mask);
    }

    @Override // android.view.WindowInsetsController
    public int getSystemBarsAppearance() {
        if (!this.mHost.isSystemBarsAppearanceControlled()) {
            return 0;
        }
        return this.mHost.getSystemBarsAppearance();
    }

    @Override // android.view.WindowInsetsController
    public void setCaptionInsetsHeight(int height) {
        if (this.mCaptionInsetsHeight != height) {
            this.mCaptionInsetsHeight = height;
            if (height != 0) {
                this.mState.getSource(2).setFrame(new Rect(this.mFrame.left, this.mFrame.top, this.mFrame.right, this.mFrame.top + this.mCaptionInsetsHeight));
            } else {
                this.mState.removeSource(2);
            }
            this.mHost.notifyInsetsChanged();
        }
    }

    @Override // android.view.WindowInsetsController
    public void setSystemBarsBehavior(int behavior) {
        this.mHost.setSystemBarsBehavior(behavior);
    }

    @Override // android.view.WindowInsetsController
    public int getSystemBarsBehavior() {
        if (!this.mHost.isSystemBarsBehaviorControlled()) {
            return 0;
        }
        return this.mHost.getSystemBarsBehavior();
    }

    @Override // android.view.WindowInsetsController
    public void setAnimationsDisabled(boolean disable) {
        this.mAnimationsDisabled = disable;
    }

    private int calculateControllableTypes() {
        int result = 0;
        for (int i = this.mSourceConsumers.size() - 1; i >= 0; i--) {
            InsetsSourceConsumer consumer = this.mSourceConsumers.valueAt(i);
            InsetsSource source = this.mState.peekSource(consumer.mType);
            if (!(consumer.getControl() == null || source == null || !source.isUserControllable())) {
                result |= InsetsState.toPublicType(consumer.mType);
            }
        }
        return (~this.mState.calculateUncontrollableInsetsFromFrame(this.mFrame)) & result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int invokeControllableInsetsChangedListeners() {
        this.mHandler.removeCallbacks(this.mInvokeControllableInsetsChangedListeners);
        this.mLastStartedAnimTypes = 0;
        int types = calculateControllableTypes();
        int size = this.mControllableInsetsChangedListeners.size();
        for (int i = 0; i < size; i++) {
            this.mControllableInsetsChangedListeners.get(i).onControllableInsetsChanged(this, types);
        }
        int i2 = this.mLastStartedAnimTypes;
        return i2;
    }

    @Override // android.view.WindowInsetsController
    public void addOnControllableInsetsChangedListener(WindowInsetsController.OnControllableInsetsChangedListener listener) {
        Objects.requireNonNull(listener);
        this.mControllableInsetsChangedListeners.add(listener);
        listener.onControllableInsetsChanged(this, calculateControllableTypes());
    }

    @Override // android.view.WindowInsetsController
    public void removeOnControllableInsetsChangedListener(WindowInsetsController.OnControllableInsetsChangedListener listener) {
        Objects.requireNonNull(listener);
        this.mControllableInsetsChangedListeners.remove(listener);
    }

    @Override // android.view.InsetsAnimationControlCallbacks
    public void releaseSurfaceControlFromRt(SurfaceControl sc) {
        this.mHost.releaseSurfaceControlFromRt(sc);
    }

    @Override // android.view.InsetsAnimationControlCallbacks
    public void reportPerceptible(int types, boolean perceptible) {
        ArraySet<Integer> internalTypes = InsetsState.toInternalType(types);
        int size = this.mSourceConsumers.size();
        for (int i = 0; i < size; i++) {
            InsetsSourceConsumer consumer = this.mSourceConsumers.valueAt(i);
            if (internalTypes.contains(Integer.valueOf(consumer.getType()))) {
                consumer.onPerceptible(perceptible);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Host getHost() {
        return this.mHost;
    }
}
