package android.inputmethodservice;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.ContentObserver;
import android.graphics.Rect;
import android.graphics.Region;
import android.inputmethodservice.AbstractInputMethodService;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.os.Trace;
import android.provider.Settings;
import android.provider.SettingsStringUtil;
import android.text.Layout;
import android.text.Spannable;
import android.text.method.MovementMethod;
import android.util.Log;
import android.util.PrintWriterPrinter;
import android.util.Printer;
import android.util.imetracing.ImeTracing;
import android.util.proto.ProtoOutputStream;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewRootImpl;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.autofill.AutofillId;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CursorAnchorInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InlineSuggestionsRequest;
import android.view.inputmethod.InlineSuggestionsResponse;
import android.view.inputmethod.InputBinding;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputContentInfo;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.window.WindowMetricsHelper;
import com.android.internal.R;
import com.android.internal.inputmethod.IInputContentUriToken;
import com.android.internal.inputmethod.IInputMethodPrivilegedOperations;
import com.android.internal.inputmethod.InputMethodPrivilegedOperations;
import com.android.internal.inputmethod.InputMethodPrivilegedOperationsRegistry;
import com.android.internal.view.IInlineSuggestionsRequestCallback;
import com.android.internal.view.InlineSuggestionsRequestInfo;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
/* loaded from: classes2.dex */
public class InputMethodService extends AbstractInputMethodService {
    public static final int BACK_DISPOSITION_ADJUST_NOTHING = 3;
    public static final int BACK_DISPOSITION_DEFAULT = 0;
    private static final int BACK_DISPOSITION_MAX = 3;
    private static final int BACK_DISPOSITION_MIN = 0;
    @Deprecated
    public static final int BACK_DISPOSITION_WILL_DISMISS = 2;
    @Deprecated
    public static final int BACK_DISPOSITION_WILL_NOT_DISMISS = 1;
    static boolean DEBUG = false;
    public static final long FINISH_INPUT_NO_FALLBACK_CONNECTION = 156215187;
    public static final int IME_ACTIVE = 1;
    public static final int IME_INVISIBLE = 4;
    public static final int IME_VISIBLE = 2;
    static final int MOVEMENT_DOWN = -1;
    static final int MOVEMENT_UP = -2;
    static final String TAG = "InputMethodService";
    private static final long TIMEOUT_SURFACE_REMOVAL_MILLIS = 5000;
    public static IInputMethodServiceExt mInputMethodServiceExt = null;
    private boolean mAutomotiveHideNavBarForKeyboard;
    int mBackDisposition;
    FrameLayout mCandidatesFrame;
    boolean mCandidatesViewStarted;
    int mCandidatesVisibility;
    CompletionInfo[] mCurCompletions;
    private IBinder mCurHideInputToken;
    private IBinder mCurShowInputToken;
    boolean mDecorViewVisible;
    boolean mDecorViewWasVisible;
    ViewGroup mExtractAccessories;
    View mExtractAction;
    ExtractEditText mExtractEditText;
    FrameLayout mExtractFrame;
    View mExtractView;
    boolean mExtractViewHidden;
    ExtractedText mExtractedText;
    int mExtractedToken;
    boolean mFullscreenApplied;
    ViewGroup mFullscreenArea;
    private Handler mHandler;
    private boolean mImeSurfaceScheduledForRemoval;
    InputMethodManager mImm;
    boolean mInShowWindow;
    LayoutInflater mInflater;
    boolean mInitialized;
    private InlineSuggestionSessionController mInlineSuggestionSessionController;
    InputBinding mInputBinding;
    InputConnection mInputConnection;
    EditorInfo mInputEditorInfo;
    FrameLayout mInputFrame;
    boolean mInputStarted;
    View mInputView;
    boolean mInputViewStarted;
    private boolean mIsAutomotive;
    boolean mIsFullscreen;
    boolean mIsInputViewShown;
    boolean mLastShowInputRequested;
    private boolean mLastWasInFullscreenMode;
    private boolean mNotifyUserActionSent;
    View mRootView;
    private SettingsObserver mSettingsObserver;
    int mShowInputFlags;
    boolean mShowInputRequested;
    InputConnection mStartedInputConnection;
    int mStatusIcon;
    TypedArray mThemeAttrs;
    IBinder mToken;
    boolean mViewsCreated;
    SoftInputWindow mWindow;
    boolean mWindowVisible;
    private InputMethodPrivilegedOperations mPrivOps = new InputMethodPrivilegedOperations();
    int mTheme = 0;
    private Object mLock = new Object();
    final Insets mTmpInsets = new Insets();
    final int[] mTmpLocation = new int[2];
    private ImsConfigurationTracker mConfigTracker = new ImsConfigurationTracker();
    final ViewTreeObserver.OnComputeInternalInsetsListener mInsetsComputer = new ViewTreeObserver.OnComputeInternalInsetsListener() { // from class: android.inputmethodservice.InputMethodService$$ExternalSyntheticLambda1
        @Override // android.view.ViewTreeObserver.OnComputeInternalInsetsListener
        public final void onComputeInternalInsets(ViewTreeObserver.InternalInsetsInfo internalInsetsInfo) {
            InputMethodService.this.lambda$new$0$InputMethodService(internalInsetsInfo);
        }
    };
    final View.OnClickListener mActionClickListener = new View.OnClickListener() { // from class: android.inputmethodservice.InputMethodService$$ExternalSyntheticLambda0
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            InputMethodService.this.lambda$new$1$InputMethodService(view);
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface BackDispositionMode {
    }

    public /* synthetic */ void lambda$new$0$InputMethodService(ViewTreeObserver.InternalInsetsInfo info) {
        onComputeInsets(this.mTmpInsets);
        if (!this.mViewsCreated) {
            this.mTmpInsets.visibleTopInsets = 0;
        }
        if (isExtractViewShown()) {
            View decor = getWindow().getWindow().getDecorView();
            Rect rect = info.contentInsets;
            Rect rect2 = info.visibleInsets;
            int height = decor.getHeight();
            rect2.top = height;
            rect.top = height;
            info.touchableRegion.setEmpty();
            info.setTouchableInsets(0);
        } else {
            mInputMethodServiceExt.hookOnComputeRaise(this.mTmpInsets, getWindow());
            info.contentInsets.top = this.mTmpInsets.contentTopInsets;
            info.visibleInsets.top = this.mTmpInsets.visibleTopInsets;
            info.touchableRegion.set(this.mTmpInsets.touchableRegion);
            info.setTouchableInsets(this.mTmpInsets.touchableInsets);
        }
        if (this.mInputFrame != null) {
            setImeExclusionRect(this.mTmpInsets.visibleTopInsets);
        }
    }

    public /* synthetic */ void lambda$new$1$InputMethodService(View v) {
        EditorInfo ei = getCurrentInputEditorInfo();
        InputConnection ic = getCurrentInputConnection();
        if (ei != null && ic != null) {
            if (ei.actionId != 0) {
                ic.performEditorAction(ei.actionId);
            } else if ((ei.imeOptions & 255) != 1) {
                ic.performEditorAction(ei.imeOptions & 255);
            }
        }
    }

    /* loaded from: classes2.dex */
    public class InputMethodImpl extends AbstractInputMethodService.AbstractInputMethodImpl {
        private boolean mSystemCallingHideSoftInput;
        private boolean mSystemCallingShowSoftInput;

        public InputMethodImpl() {
            super();
        }

        @Override // android.view.inputmethod.InputMethod
        public final void initializeInternal(IBinder token, int displayId, IInputMethodPrivilegedOperations privilegedOperations, int configChanges) {
            if (InputMethodPrivilegedOperationsRegistry.isRegistered(token)) {
                Log.w(InputMethod.TAG, "The token has already registered, ignore this initialization.");
                return;
            }
            Trace.traceBegin(32L, "IMS.initializeInternal");
            InputMethodService.this.mConfigTracker.onInitialize(configChanges);
            InputMethodService.this.mPrivOps.set(privilegedOperations);
            InputMethodPrivilegedOperationsRegistry.put(token, InputMethodService.this.mPrivOps);
            updateInputMethodDisplay(displayId);
            attachToken(token);
            Trace.traceEnd(32L);
        }

        @Override // android.view.inputmethod.InputMethod
        public void onCreateInlineSuggestionsRequest(InlineSuggestionsRequestInfo requestInfo, IInlineSuggestionsRequestCallback cb) {
            if (InputMethodService.DEBUG) {
                Log.d(InputMethod.TAG, "InputMethodService received onCreateInlineSuggestionsRequest()");
            }
            InputMethodService.this.mInlineSuggestionSessionController.onMakeInlineSuggestionsRequest(requestInfo, cb);
        }

        @Override // android.view.inputmethod.InputMethod
        public void attachToken(IBinder token) {
            if (InputMethodService.this.mToken == null) {
                InputMethodService.this.mToken = token;
                InputMethodService.this.mWindow.setToken(token);
                return;
            }
            throw new IllegalStateException("attachToken() must be called at most once. token=" + token);
        }

        @Override // android.view.inputmethod.InputMethod
        public void updateInputMethodDisplay(int displayId) {
            if (InputMethodService.this.getDisplayId() != displayId) {
                InputMethodService.this.updateDisplay(displayId);
            }
        }

        @Override // android.view.inputmethod.InputMethod
        public void bindInput(InputBinding binding) {
            if (InputMethodService.this.mConfigTracker.isInitialized()) {
                Trace.traceBegin(32L, "IMS.bindInput");
                InputMethodService.this.mInputBinding = binding;
                InputMethodService.this.mInputConnection = binding.getConnection();
                if (InputMethodService.DEBUG) {
                    Log.v(InputMethod.TAG, "bindInput(): binding=" + binding + " ic=" + InputMethodService.this.mInputConnection);
                }
                InputMethodService.this.reportFullscreenMode();
                InputMethodService.this.initialize();
                InputMethodService.this.onBindInput();
                InputMethodService.this.mConfigTracker.onBindInput(InputMethodService.this.getResources());
                Trace.traceEnd(32L);
            }
        }

        @Override // android.view.inputmethod.InputMethod
        public void unbindInput() {
            if (InputMethodService.DEBUG) {
                Log.v(InputMethod.TAG, "unbindInput(): binding=" + InputMethodService.this.mInputBinding + " ic=" + InputMethodService.this.mInputConnection);
            }
            InputMethodService.this.onUnbindInput();
            InputMethodService.this.mInputBinding = null;
            InputMethodService.this.mInputConnection = null;
        }

        @Override // android.view.inputmethod.InputMethod
        public void startInput(InputConnection ic, EditorInfo attribute) {
            if (InputMethodService.DEBUG) {
                Log.v(InputMethod.TAG, "startInput(): editor=" + attribute);
            }
            Trace.traceBegin(32L, "IMS.startInput");
            InputMethodService.this.doStartInput(ic, attribute, false);
            Trace.traceEnd(32L);
        }

        @Override // android.view.inputmethod.InputMethod
        public void restartInput(InputConnection ic, EditorInfo attribute) {
            if (InputMethodService.DEBUG) {
                Log.v(InputMethod.TAG, "restartInput(): editor=" + attribute);
            }
            Trace.traceBegin(32L, "IMS.restartInput");
            InputMethodService.this.doStartInput(ic, attribute, true);
            Trace.traceEnd(32L);
        }

        @Override // android.view.inputmethod.InputMethod
        public final void dispatchStartInputWithToken(InputConnection inputConnection, EditorInfo editorInfo, boolean restarting, IBinder startInputToken) {
            InputMethodService.this.mPrivOps.reportStartInputAsync(startInputToken);
            if (restarting) {
                restartInput(inputConnection, editorInfo);
            } else {
                startInput(inputConnection, editorInfo);
            }
        }

        @Override // android.view.inputmethod.InputMethod
        public void hideSoftInputWithToken(int flags, ResultReceiver resultReceiver, IBinder hideInputToken) {
            this.mSystemCallingHideSoftInput = true;
            InputMethodService.this.mCurHideInputToken = hideInputToken;
            hideSoftInput(flags, resultReceiver);
            InputMethodService.this.mCurHideInputToken = null;
            this.mSystemCallingHideSoftInput = false;
        }

        @Override // android.view.inputmethod.InputMethod
        public void hideSoftInput(int flags, ResultReceiver resultReceiver) {
            if (InputMethodService.DEBUG) {
                Log.v(InputMethod.TAG, "hideSoftInput()");
            }
            if (!InputMethodService.mInputMethodServiceExt.hookHideImmediately(flags, InputMethodService.this.mWindow)) {
                if (InputMethodService.this.getApplicationInfo().targetSdkVersion < 30 || this.mSystemCallingHideSoftInput) {
                    ImeTracing.getInstance().triggerServiceDump("InputMethodService.InputMethodImpl#hideSoftInput", InputMethodService.this, null);
                    boolean wasVisible = InputMethodService.this.isInputViewShown();
                    Trace.traceBegin(32L, "IMS.hideSoftInput");
                    InputMethodService.mInputMethodServiceExt.hookSkipInsetChangeMaybe(flags);
                    int i = 0;
                    InputMethodService.this.mShowInputFlags = 0;
                    InputMethodService.this.mShowInputRequested = false;
                    InputMethodService.this.doHideWindow();
                    boolean isVisible = InputMethodService.this.isInputViewShown();
                    boolean visibilityChanged = isVisible != wasVisible;
                    if (resultReceiver != null) {
                        if (visibilityChanged) {
                            i = 3;
                        } else if (!wasVisible) {
                            i = 1;
                        }
                        resultReceiver.send(i, null);
                    }
                    Trace.traceEnd(32L);
                    return;
                }
                Log.e(InputMethod.TAG, "IME shouldn't call hideSoftInput on itself. Use requestHideSelf(int) itself");
            }
        }

        @Override // android.view.inputmethod.InputMethod
        public void showSoftInputWithToken(int flags, ResultReceiver resultReceiver, IBinder showInputToken) {
            this.mSystemCallingShowSoftInput = true;
            InputMethodService.this.mCurShowInputToken = showInputToken;
            showSoftInput(flags, resultReceiver);
            InputMethodService.this.mCurShowInputToken = null;
            this.mSystemCallingShowSoftInput = false;
        }

        @Override // android.view.inputmethod.InputMethod
        public void showSoftInput(int flags, ResultReceiver resultReceiver) {
            if (InputMethodService.DEBUG) {
                Log.v(InputMethod.TAG, "showSoftInput()");
            }
            if (InputMethodService.this.getApplicationInfo().targetSdkVersion < 30 || this.mSystemCallingShowSoftInput) {
                if (Trace.isEnabled()) {
                    Binder.enableTracing();
                } else {
                    Binder.disableTracing();
                }
                Trace.traceBegin(32L, "IMS.showSoftInput");
                ImeTracing.getInstance().triggerServiceDump("InputMethodService.InputMethodImpl#showSoftInput", InputMethodService.this, null);
                boolean wasVisible = InputMethodService.this.isInputViewShown();
                int i = 0;
                if (InputMethodService.this.dispatchOnShowInputRequested(flags, false)) {
                    InputMethodService.this.showWindow(true);
                    InputMethodService.this.applyVisibilityInInsetsConsumerIfNecessary(true);
                }
                InputMethodService inputMethodService = InputMethodService.this;
                inputMethodService.setImeWindowStatus(inputMethodService.mapToImeWindowStatus(), InputMethodService.this.mBackDisposition);
                boolean isVisible = InputMethodService.this.isInputViewShown();
                boolean visibilityChanged = isVisible != wasVisible;
                if (resultReceiver != null) {
                    if (visibilityChanged) {
                        i = 2;
                    } else if (!wasVisible) {
                        i = 1;
                    }
                    resultReceiver.send(i, null);
                }
                Trace.traceEnd(32L);
                return;
            }
            Log.e(InputMethod.TAG, " IME shouldn't call showSoftInput on itself. Use requestShowSelf(int) itself");
        }

        @Override // android.view.inputmethod.InputMethod
        public void changeInputMethodSubtype(InputMethodSubtype subtype) {
            InputMethodService.this.dispatchOnCurrentInputMethodSubtypeChanged(subtype);
        }

        @Override // android.view.inputmethod.InputMethod
        public void setCurrentShowInputToken(IBinder showInputToken) {
            InputMethodService.this.mCurShowInputToken = showInputToken;
        }

        @Override // android.view.inputmethod.InputMethod
        public void setCurrentHideInputToken(IBinder hideInputToken) {
            InputMethodService.this.mCurHideInputToken = hideInputToken;
        }
    }

    public InlineSuggestionsRequest onCreateInlineSuggestionsRequest(Bundle uiExtras) {
        return null;
    }

    public boolean onInlineSuggestionsResponse(InlineSuggestionsResponse response) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IBinder getHostInputToken() {
        ViewRootImpl viewRoot = null;
        View view = this.mRootView;
        if (view != null) {
            viewRoot = view.getViewRootImpl();
        }
        if (viewRoot == null) {
            return null;
        }
        return viewRoot.getInputToken();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyImeHidden() {
        int flag = mInputMethodServiceExt.hookUpdatedFlag();
        requestHideSelf(flag);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleImeSurfaceRemoval() {
        if (!this.mShowInputRequested && !this.mWindowVisible && this.mWindow != null && !this.mImeSurfaceScheduledForRemoval) {
            if (this.mHandler == null) {
                this.mHandler = new Handler(getMainLooper());
            }
            if (this.mLastWasInFullscreenMode) {
                lambda$scheduleImeSurfaceRemoval$2$InputMethodService();
                return;
            }
            this.mImeSurfaceScheduledForRemoval = true;
            this.mHandler.postDelayed(new Runnable() { // from class: android.inputmethodservice.InputMethodService$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    InputMethodService.this.lambda$scheduleImeSurfaceRemoval$2$InputMethodService();
                }
            }, 5000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: removeImeSurface */
    public void lambda$scheduleImeSurfaceRemoval$2$InputMethodService() {
        SoftInputWindow softInputWindow = this.mWindow;
        if (softInputWindow != null) {
            softInputWindow.hide();
            if (DEBUG) {
                Log.v(TAG, "hide window by removeImeSurface");
            }
        }
        this.mImeSurfaceScheduledForRemoval = false;
    }

    private void cancelImeSurfaceRemoval() {
        Handler handler = this.mHandler;
        if (handler != null && this.mImeSurfaceScheduledForRemoval) {
            handler.removeCallbacksAndMessages(null);
            this.mImeSurfaceScheduledForRemoval = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setImeWindowStatus(int visibilityFlags, int backDisposition) {
        this.mPrivOps.setImeWindowStatusAsync(visibilityFlags, backDisposition);
    }

    private void setImeExclusionRect(int visibleTopInsets) {
        View rootView = this.mInputFrame.getRootView();
        if (mInputMethodServiceExt.hookShouldPreventTouch(this.mWindow)) {
            mInputMethodServiceExt.hookRootViewUpdated(rootView);
            return;
        }
        android.graphics.Insets systemGesture = rootView.getRootWindowInsets().getInsets(WindowInsets.Type.systemGestures());
        ArrayList<Rect> exclusionRects = new ArrayList<>();
        exclusionRects.add(new Rect(0, visibleTopInsets, systemGesture.left, rootView.getHeight()));
        exclusionRects.add(new Rect(rootView.getWidth() - systemGesture.right, visibleTopInsets, rootView.getWidth(), rootView.getHeight()));
        rootView.setSystemGestureExclusionRects(exclusionRects);
    }

    /* loaded from: classes2.dex */
    public class InputMethodSessionImpl extends AbstractInputMethodService.AbstractInputMethodSessionImpl {
        public InputMethodSessionImpl() {
            super();
        }

        @Override // android.view.inputmethod.InputMethodSession
        public void finishInput() {
            if (isEnabled()) {
                if (InputMethodService.DEBUG) {
                    Log.v(InputMethodService.TAG, "finishInput() in " + this);
                }
                InputMethodService.this.doFinishInput();
            }
        }

        @Override // android.view.inputmethod.InputMethodSession
        public void displayCompletions(CompletionInfo[] completions) {
            if (isEnabled()) {
                InputMethodService.this.mCurCompletions = completions;
                InputMethodService.this.onDisplayCompletions(completions);
            }
        }

        @Override // android.view.inputmethod.InputMethodSession
        public void updateExtractedText(int token, ExtractedText text) {
            if (isEnabled()) {
                InputMethodService.this.onUpdateExtractedText(token, text);
            }
        }

        @Override // android.view.inputmethod.InputMethodSession
        public void updateSelection(int oldSelStart, int oldSelEnd, int newSelStart, int newSelEnd, int candidatesStart, int candidatesEnd) {
            if (isEnabled()) {
                InputMethodService.this.onUpdateSelection(oldSelStart, oldSelEnd, newSelStart, newSelEnd, candidatesStart, candidatesEnd);
            }
        }

        @Override // android.view.inputmethod.InputMethodSession
        public void viewClicked(boolean focusChanged) {
            if (isEnabled()) {
                InputMethodService.this.onViewClicked(focusChanged);
            }
        }

        @Override // android.view.inputmethod.InputMethodSession
        public void updateCursor(Rect newCursor) {
            if (isEnabled()) {
                InputMethodService.this.onUpdateCursor(newCursor);
            }
        }

        @Override // android.view.inputmethod.InputMethodSession
        public void appPrivateCommand(String action, Bundle data) {
            if (isEnabled()) {
                InputMethodService.this.onAppPrivateCommand(action, data);
            }
        }

        @Override // android.view.inputmethod.InputMethodSession
        @Deprecated
        public void toggleSoftInput(int showFlags, int hideFlags) {
            InputMethodService.this.onToggleSoftInput(showFlags, hideFlags);
        }

        @Override // android.view.inputmethod.InputMethodSession
        public void updateCursorAnchorInfo(CursorAnchorInfo info) {
            if (isEnabled()) {
                InputMethodService.this.onUpdateCursorAnchorInfo(info);
            }
        }

        @Override // android.view.inputmethod.InputMethodSession
        public final void notifyImeHidden() {
            InputMethodService.this.notifyImeHidden();
        }

        @Override // android.view.inputmethod.InputMethodSession
        public final void removeImeSurface() {
            InputMethodService.this.scheduleImeSurfaceRemoval();
        }
    }

    /* loaded from: classes2.dex */
    public static final class Insets {
        public static final int TOUCHABLE_INSETS_CONTENT = 1;
        public static final int TOUCHABLE_INSETS_FRAME = 0;
        public static final int TOUCHABLE_INSETS_REGION = 3;
        public static final int TOUCHABLE_INSETS_VISIBLE = 2;
        public int contentTopInsets;
        public int touchableInsets;
        public final Region touchableRegion = new Region();
        public int visibleTopInsets;

        /* JADX INFO: Access modifiers changed from: private */
        public void dumpDebug(ProtoOutputStream proto, long fieldId) {
            long token = proto.start(fieldId);
            proto.write(1120986464257L, this.contentTopInsets);
            proto.write(1120986464258L, this.visibleTopInsets);
            proto.write(1120986464259L, this.touchableInsets);
            proto.write(1138166333444L, this.touchableRegion.toString());
            proto.end(token);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class SettingsObserver extends ContentObserver {
        private final InputMethodService mService;
        private int mShowImeWithHardKeyboard = 0;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes2.dex */
        private @interface ShowImeWithHardKeyboardType {
            public static final int FALSE = 1;
            public static final int TRUE = 2;
            public static final int UNKNOWN = 0;
        }

        private SettingsObserver(InputMethodService service) {
            super(new Handler(service.getMainLooper()));
            this.mService = service;
        }

        public static SettingsObserver createAndRegister(InputMethodService service) {
            SettingsObserver observer = new SettingsObserver(service);
            service.getContentResolver().registerContentObserver(Settings.Secure.getUriFor(Settings.Secure.SHOW_IME_WITH_HARD_KEYBOARD), false, observer);
            return observer;
        }

        void unregister() {
            this.mService.getContentResolver().unregisterContentObserver(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean shouldShowImeWithHardKeyboard() {
            if (this.mShowImeWithHardKeyboard == 0) {
                this.mShowImeWithHardKeyboard = Settings.Secure.getInt(this.mService.getContentResolver(), Settings.Secure.SHOW_IME_WITH_HARD_KEYBOARD, 0) != 0 ? 2 : 1;
            }
            switch (this.mShowImeWithHardKeyboard) {
                case 1:
                    return false;
                case 2:
                    return true;
                default:
                    Log.e(InputMethodService.TAG, "Unexpected mShowImeWithHardKeyboard=" + this.mShowImeWithHardKeyboard);
                    return false;
            }
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange, Uri uri) {
            Uri showImeWithHardKeyboardUri = Settings.Secure.getUriFor(Settings.Secure.SHOW_IME_WITH_HARD_KEYBOARD);
            if (showImeWithHardKeyboardUri.equals(uri)) {
                this.mShowImeWithHardKeyboard = Settings.Secure.getInt(this.mService.getContentResolver(), Settings.Secure.SHOW_IME_WITH_HARD_KEYBOARD, 0) != 0 ? 2 : 1;
                this.mService.resetStateForNewConfiguration();
            }
            InputMethodService.mInputMethodServiceExt.hookOnColorChange(uri);
        }

        public String toString() {
            return "SettingsObserver{mShowImeWithHardKeyboard=" + this.mShowImeWithHardKeyboard + "}";
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int theme) {
        if (this.mWindow == null) {
            this.mTheme = theme;
            return;
        }
        throw new IllegalStateException("Must be called before onCreate()");
    }

    @Deprecated
    public boolean enableHardwareAcceleration() {
        if (this.mWindow == null) {
            return ActivityManager.isHighEndGfx();
        }
        throw new IllegalStateException("Must be called before onCreate()");
    }

    @Override // android.app.Service
    public void onCreate() {
        Trace.traceBegin(32L, "IMS.onCreate");
        IInputMethodServiceExt newInstance = InputMethodServiceExtPlugin.constructor.newInstance(this);
        mInputMethodServiceExt = newInstance;
        DEBUG = newInstance.hookDebugSwitch();
        int selectSystemTheme = Resources.selectSystemTheme(this.mTheme, getApplicationInfo().targetSdkVersion, 16973908, 16973951, 16974142, 16974142);
        this.mTheme = selectSystemTheme;
        super.setTheme(selectSystemTheme);
        super.onCreate();
        this.mImm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        SettingsObserver createAndRegister = SettingsObserver.createAndRegister(this);
        this.mSettingsObserver = createAndRegister;
        createAndRegister.shouldShowImeWithHardKeyboard();
        this.mIsAutomotive = isAutomotive();
        this.mAutomotiveHideNavBarForKeyboard = getApplicationContext().getResources().getBoolean(R.bool.config_automotiveHideNavBarForKeyboard);
        this.mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Trace.traceBegin(32L, "IMS.initSoftInputWindow");
        SoftInputWindow softInputWindow = new SoftInputWindow(this, InputMethod.TAG, this.mTheme, null, null, this.mDispatcherState, 2011, 80, false);
        this.mWindow = softInputWindow;
        softInputWindow.getWindow().getAttributes().setFitInsetsTypes(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());
        this.mWindow.getWindow().getAttributes().setFitInsetsSides(WindowInsets.Side.all() & (-9));
        this.mWindow.getWindow().getAttributes().receiveInsetsIgnoringZOrder = true;
        if (this.mIsAutomotive && this.mAutomotiveHideNavBarForKeyboard) {
            this.mWindow.getWindow().setDecorFitsSystemWindows(false);
        }
        this.mWindow.getWindow().setFlags(Integer.MIN_VALUE, Integer.MIN_VALUE);
        initViews();
        this.mWindow.getWindow().setLayout(-1, -2);
        Trace.traceEnd(32L);
        mInputMethodServiceExt.hookOnCreate(this.mSettingsObserver, this);
        this.mInlineSuggestionSessionController = new InlineSuggestionSessionController(new Function() { // from class: android.inputmethodservice.InputMethodService$$ExternalSyntheticLambda5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return InputMethodService.this.onCreateInlineSuggestionsRequest((Bundle) obj);
            }
        }, new Supplier() { // from class: android.inputmethodservice.InputMethodService$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                IBinder hostInputToken;
                hostInputToken = InputMethodService.this.getHostInputToken();
                return hostInputToken;
            }
        }, new Consumer() { // from class: android.inputmethodservice.InputMethodService$$ExternalSyntheticLambda4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                InputMethodService.this.onInlineSuggestionsResponse((InlineSuggestionsResponse) obj);
            }
        });
        Trace.traceEnd(32L);
    }

    public void onInitializeInterface() {
    }

    void initialize() {
        if (!this.mInitialized) {
            this.mInitialized = true;
            onInitializeInterface();
        }
    }

    void initViews() {
        Trace.traceBegin(32L, "IMS.initViews");
        this.mInitialized = false;
        this.mViewsCreated = false;
        this.mShowInputRequested = false;
        this.mShowInputFlags = 0;
        this.mThemeAttrs = obtainStyledAttributes(android.R.styleable.InputMethodService);
        View inflate = this.mInflater.inflate(R.layout.input_method, (ViewGroup) null);
        this.mRootView = inflate;
        this.mWindow.setContentView(inflate);
        this.mRootView.getViewTreeObserver().addOnComputeInternalInsetsListener(this.mInsetsComputer);
        this.mFullscreenArea = (ViewGroup) this.mRootView.findViewById(R.id.fullscreenArea);
        this.mExtractViewHidden = false;
        this.mExtractFrame = (FrameLayout) this.mRootView.findViewById(16908316);
        this.mExtractView = null;
        this.mExtractEditText = null;
        this.mExtractAccessories = null;
        this.mExtractAction = null;
        this.mFullscreenApplied = false;
        this.mCandidatesFrame = (FrameLayout) this.mRootView.findViewById(16908317);
        this.mInputFrame = (FrameLayout) this.mRootView.findViewById(16908318);
        this.mInputView = null;
        this.mIsInputViewShown = false;
        this.mExtractFrame.setVisibility(8);
        int candidatesHiddenVisibility = getCandidatesHiddenVisibility();
        this.mCandidatesVisibility = candidatesHiddenVisibility;
        this.mCandidatesFrame.setVisibility(candidatesHiddenVisibility);
        this.mInputFrame.setVisibility(8);
        Trace.traceEnd(32L);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.mRootView.getViewTreeObserver().removeOnComputeInternalInsetsListener(this.mInsetsComputer);
        doFinishInput();
        if (DEBUG) {
            Log.v(TAG, "onDestroy: " + this.mWindow.isShowing());
        }
        this.mWindow.dismissForDestroyIfNecessary();
        SettingsObserver settingsObserver = this.mSettingsObserver;
        if (settingsObserver != null) {
            settingsObserver.unregister();
            this.mSettingsObserver = null;
        }
        IBinder iBinder = this.mToken;
        if (iBinder != null) {
            InputMethodPrivilegedOperationsRegistry.remove(iBinder);
        }
        mInputMethodServiceExt.hookOnDestroy();
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.mConfigTracker.onConfigurationChanged(newConfig, new Runnable() { // from class: android.inputmethodservice.InputMethodService$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                InputMethodService.this.resetStateForNewConfiguration();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetStateForNewConfiguration() {
        Trace.traceBegin(32L, "IMS.resetStateForNewConfiguration");
        boolean visible = this.mDecorViewVisible;
        int showFlags = this.mShowInputFlags;
        boolean showingInput = this.mShowInputRequested;
        CompletionInfo[] completions = this.mCurCompletions;
        this.mRootView.getViewTreeObserver().removeOnComputeInternalInsetsListener(this.mInsetsComputer);
        initViews();
        int i = 0;
        this.mInputViewStarted = false;
        this.mCandidatesViewStarted = false;
        if (this.mInputStarted) {
            doStartInput(getCurrentInputConnection(), getCurrentInputEditorInfo(), true);
        }
        if (visible) {
            if (showingInput) {
                if (dispatchOnShowInputRequested(showFlags, true)) {
                    showWindow(true);
                    if (completions != null) {
                        this.mCurCompletions = completions;
                        onDisplayCompletions(completions);
                    }
                } else {
                    doHideWindow();
                }
            } else if (this.mCandidatesVisibility == 0) {
                showWindow(false);
            } else {
                doHideWindow();
            }
            boolean showing = onEvaluateInputViewShown();
            if (showing) {
                i = 2;
            }
            setImeWindowStatus(i | 1, this.mBackDisposition);
        }
        Trace.traceEnd(32L);
        mInputMethodServiceExt.hookUpdateNavigationGuardColorDelay(this.mWindow);
    }

    @Override // android.inputmethodservice.AbstractInputMethodService
    public AbstractInputMethodService.AbstractInputMethodImpl onCreateInputMethodInterface() {
        return new InputMethodImpl();
    }

    @Override // android.inputmethodservice.AbstractInputMethodService
    public AbstractInputMethodService.AbstractInputMethodSessionImpl onCreateInputMethodSessionInterface() {
        return new InputMethodSessionImpl();
    }

    public LayoutInflater getLayoutInflater() {
        return this.mInflater;
    }

    public Dialog getWindow() {
        return this.mWindow;
    }

    public void setBackDisposition(int disposition) {
        if (disposition != this.mBackDisposition) {
            if (disposition > 3 || disposition < 0) {
                Log.e(TAG, "Invalid back disposition value (" + disposition + ") specified.");
                return;
            }
            this.mBackDisposition = disposition;
            setImeWindowStatus(mapToImeWindowStatus(), this.mBackDisposition);
        }
    }

    public int getBackDisposition() {
        return this.mBackDisposition;
    }

    public int getMaxWidth() {
        WindowManager windowManager = (WindowManager) getSystemService(WindowManager.class);
        return WindowMetricsHelper.getBoundsExcludingNavigationBarAndCutout(windowManager.getCurrentWindowMetrics()).width();
    }

    public InputBinding getCurrentInputBinding() {
        return this.mInputBinding;
    }

    public InputConnection getCurrentInputConnection() {
        InputConnection ic = this.mStartedInputConnection;
        if (ic != null) {
            return ic;
        }
        return this.mInputConnection;
    }

    public final boolean switchToPreviousInputMethod() {
        return this.mPrivOps.switchToPreviousInputMethod();
    }

    public final boolean switchToNextInputMethod(boolean onlyCurrentIme) {
        return this.mPrivOps.switchToNextInputMethod(onlyCurrentIme);
    }

    public final boolean shouldOfferSwitchingToNextInputMethod() {
        return this.mPrivOps.shouldOfferSwitchingToNextInputMethod();
    }

    public boolean getCurrentInputStarted() {
        return this.mInputStarted;
    }

    public EditorInfo getCurrentInputEditorInfo() {
        return this.mInputEditorInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportFullscreenMode() {
        this.mPrivOps.reportFullscreenModeAsync(this.mIsFullscreen);
    }

    public void updateFullscreenMode() {
        View v;
        Trace.traceBegin(32L, "IMS.updateFullscreenMode");
        boolean isFullscreen = this.mShowInputRequested && onEvaluateFullscreenMode();
        boolean changed = this.mLastShowInputRequested != this.mShowInputRequested;
        if (this.mIsFullscreen != isFullscreen || !this.mFullscreenApplied) {
            changed = true;
            this.mIsFullscreen = isFullscreen;
            reportFullscreenMode();
            this.mFullscreenApplied = true;
            initialize();
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) this.mFullscreenArea.getLayoutParams();
            if (isFullscreen) {
                this.mFullscreenArea.setBackgroundDrawable(this.mThemeAttrs.getDrawable(0));
                lp.height = 0;
                lp.weight = 1.0f;
            } else {
                this.mFullscreenArea.setBackgroundDrawable(null);
                lp.height = -2;
                lp.weight = 0.0f;
            }
            ((ViewGroup) this.mFullscreenArea.getParent()).updateViewLayout(this.mFullscreenArea, lp);
            if (isFullscreen) {
                if (this.mExtractView == null && (v = onCreateExtractTextView()) != null) {
                    setExtractView(v);
                }
                startExtractingText(false);
            }
            updateExtractFrameVisibility();
        }
        if (changed) {
            onConfigureWindow(this.mWindow.getWindow(), isFullscreen, true ^ this.mShowInputRequested);
            this.mLastShowInputRequested = this.mShowInputRequested;
        }
        Trace.traceEnd(32L);
    }

    public void onConfigureWindow(Window win, boolean isFullscreen, boolean isCandidatesOnly) {
        int currentHeight = this.mWindow.getWindow().getAttributes().height;
        int newHeight = isFullscreen ? -1 : -2;
        if (this.mIsInputViewShown && currentHeight != newHeight && DEBUG) {
            Log.w(TAG, "Window size has been changed. This may cause jankiness of resizing window: " + currentHeight + " -> " + newHeight);
        }
        this.mWindow.getWindow().setLayout(-1, newHeight);
    }

    public boolean isFullscreenMode() {
        return this.mIsFullscreen;
    }

    public boolean onEvaluateFullscreenMode() {
        if (mInputMethodServiceExt.isFoldDisplayOpen()) {
            return false;
        }
        Configuration config = getResources().getConfiguration();
        if (config.orientation != 2) {
            return false;
        }
        EditorInfo editorInfo = this.mInputEditorInfo;
        return editorInfo == null || ((editorInfo.imeOptions & 33554432) == 0 && (this.mInputEditorInfo.internalImeOptions & 1) == 0);
    }

    public void setExtractViewShown(boolean shown) {
        if (this.mExtractViewHidden == shown) {
            this.mExtractViewHidden = !shown;
            updateExtractFrameVisibility();
        }
    }

    public boolean isExtractViewShown() {
        return this.mIsFullscreen && !this.mExtractViewHidden;
    }

    void updateExtractFrameVisibility() {
        int vis;
        if (isFullscreenMode()) {
            vis = this.mExtractViewHidden ? 4 : 0;
            this.mExtractFrame.setVisibility(vis);
        } else {
            vis = 0;
            this.mExtractFrame.setVisibility(8);
        }
        int i = 1;
        updateCandidatesVisibility(this.mCandidatesVisibility == 0);
        if (this.mDecorViewWasVisible && this.mFullscreenArea.getVisibility() != vis) {
            TypedArray typedArray = this.mThemeAttrs;
            if (vis != 0) {
                i = 2;
            }
            int animRes = typedArray.getResourceId(i, 0);
            if (animRes != 0) {
                this.mFullscreenArea.startAnimation(AnimationUtils.loadAnimation(this, animRes));
            }
        }
        this.mFullscreenArea.setVisibility(vis);
    }

    public void onComputeInsets(Insets outInsets) {
        Trace.traceBegin(32L, "IMS.onComputeInsets");
        int[] loc = this.mTmpLocation;
        if (this.mInputFrame.getVisibility() == 0) {
            this.mInputFrame.getLocationInWindow(loc);
        } else {
            View decor = getWindow().getWindow().getDecorView();
            loc[1] = decor.getHeight();
        }
        if (isFullscreenMode()) {
            View decor2 = getWindow().getWindow().getDecorView();
            outInsets.contentTopInsets = decor2.getHeight();
        } else {
            outInsets.contentTopInsets = loc[1];
        }
        if (this.mCandidatesFrame.getVisibility() == 0) {
            this.mCandidatesFrame.getLocationInWindow(loc);
        }
        outInsets.visibleTopInsets = loc[1];
        outInsets.touchableInsets = 2;
        outInsets.touchableRegion.setEmpty();
        Trace.traceEnd(32L);
    }

    public void updateInputViewShown() {
        int i = 0;
        boolean isShown = this.mShowInputRequested && onEvaluateInputViewShown();
        if (this.mIsInputViewShown != isShown && this.mDecorViewVisible) {
            this.mIsInputViewShown = isShown;
            FrameLayout frameLayout = this.mInputFrame;
            if (!isShown) {
                i = 8;
            }
            frameLayout.setVisibility(i);
            if (this.mInputView == null) {
                initialize();
                View v = onCreateInputView();
                if (v != null) {
                    setInputView(v);
                }
            }
        }
    }

    public boolean isShowInputRequested() {
        mInputMethodServiceExt.hookUpdateNavigationGuardColor(this.mWindow);
        return this.mShowInputRequested;
    }

    public boolean isInputViewShown() {
        return this.mDecorViewVisible;
    }

    public boolean onEvaluateInputViewShown() {
        SettingsObserver settingsObserver = this.mSettingsObserver;
        if (settingsObserver == null) {
            Log.w(TAG, "onEvaluateInputViewShown: mSettingsObserver must not be null here.");
            return false;
        } else if (settingsObserver.shouldShowImeWithHardKeyboard()) {
            return true;
        } else {
            Configuration config = getResources().getConfiguration();
            return config.keyboard == 1 || config.hardKeyboardHidden == 2;
        }
    }

    public void setCandidatesViewShown(boolean shown) {
        updateCandidatesVisibility(shown);
        if (!this.mShowInputRequested && this.mDecorViewVisible != shown) {
            if (shown) {
                showWindow(false);
            } else {
                doHideWindow();
            }
        }
    }

    void updateCandidatesVisibility(boolean shown) {
        int vis = shown ? 0 : getCandidatesHiddenVisibility();
        if (this.mCandidatesVisibility != vis) {
            this.mCandidatesFrame.setVisibility(vis);
            this.mCandidatesVisibility = vis;
        }
    }

    public int getCandidatesHiddenVisibility() {
        return isExtractViewShown() ? 8 : 4;
    }

    public void showStatusIcon(int iconResId) {
        this.mStatusIcon = iconResId;
        this.mPrivOps.updateStatusIconAsync(getPackageName(), iconResId);
    }

    public void hideStatusIcon() {
        this.mStatusIcon = 0;
        this.mPrivOps.updateStatusIconAsync(null, 0);
    }

    public void switchInputMethod(String id) {
        this.mPrivOps.setInputMethod(id);
    }

    public final void switchInputMethod(String id, InputMethodSubtype subtype) {
        this.mPrivOps.setInputMethodAndSubtype(id, subtype);
    }

    public void setExtractView(View view) {
        this.mExtractFrame.removeAllViews();
        this.mExtractFrame.addView(view, new FrameLayout.LayoutParams(-1, -1));
        this.mExtractView = view;
        if (view != null) {
            ExtractEditText extractEditText = (ExtractEditText) view.findViewById(16908325);
            this.mExtractEditText = extractEditText;
            extractEditText.setIME(this);
            View findViewById = view.findViewById(R.id.inputExtractAction);
            this.mExtractAction = findViewById;
            if (findViewById != null) {
                this.mExtractAccessories = (ViewGroup) view.findViewById(R.id.inputExtractAccessories);
            }
            startExtractingText(false);
            return;
        }
        this.mExtractEditText = null;
        this.mExtractAccessories = null;
        this.mExtractAction = null;
    }

    public void setCandidatesView(View view) {
        this.mCandidatesFrame.removeAllViews();
        this.mCandidatesFrame.addView(view, new FrameLayout.LayoutParams(-1, -2));
    }

    public void setInputView(View view) {
        this.mInputFrame.removeAllViews();
        this.mInputFrame.addView(view, new FrameLayout.LayoutParams(-1, -2));
        this.mInputView = view;
        mInputMethodServiceExt.hookUpdateNavigationGuardColorForDialog(this.mWindow);
    }

    public View onCreateExtractTextView() {
        return this.mInflater.inflate(R.layout.input_method_extract_view, (ViewGroup) null);
    }

    public View onCreateCandidatesView() {
        return null;
    }

    public View onCreateInputView() {
        return null;
    }

    public void onStartInputView(EditorInfo info, boolean restarting) {
    }

    public void onFinishInputView(boolean finishingInput) {
        InputConnection ic;
        if (!finishingInput && (ic = getCurrentInputConnection()) != null) {
            ic.finishComposingText();
        }
    }

    public void onStartCandidatesView(EditorInfo info, boolean restarting) {
    }

    public void onFinishCandidatesView(boolean finishingInput) {
        InputConnection ic;
        if (!finishingInput && (ic = getCurrentInputConnection()) != null) {
            ic.finishComposingText();
        }
    }

    public boolean onShowInputRequested(int flags, boolean configChange) {
        if (!onEvaluateInputViewShown()) {
            return false;
        }
        if ((flags & 1) == 0) {
            if (!configChange && onEvaluateFullscreenMode()) {
                return false;
            }
            if (!this.mSettingsObserver.shouldShowImeWithHardKeyboard() && getResources().getConfiguration().keyboard != 1) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean dispatchOnShowInputRequested(int flags, boolean configChange) {
        boolean result = onShowInputRequested(flags, configChange);
        this.mInlineSuggestionSessionController.notifyOnShowInputRequested(result);
        if (result) {
            this.mShowInputFlags = flags;
        } else {
            this.mShowInputFlags = 0;
        }
        return result;
    }

    public void showWindow(boolean showInput) {
        int i;
        if (DEBUG) {
            Log.v(TAG, "Showing window: showInput=" + showInput + " mShowInputRequested=" + this.mShowInputRequested + " mViewsCreated=" + this.mViewsCreated + " mDecorViewVisible=" + this.mDecorViewVisible + " mWindowVisible=" + this.mWindowVisible + " mInputStarted=" + this.mInputStarted + " mShowInputFlags=" + this.mShowInputFlags);
        }
        if (this.mInShowWindow) {
            Log.w(TAG, "Re-entrance in to showWindow");
            return;
        }
        ImeTracing.getInstance().triggerServiceDump("InputMethodService#showWindow", this, null);
        Trace.traceBegin(32L, "IMS.showWindow");
        boolean z = this.mDecorViewVisible;
        this.mDecorViewWasVisible = z;
        this.mInShowWindow = true;
        if (isInputViewShown()) {
            i = !this.mWindowVisible ? 4 : 2;
        } else {
            i = 0;
        }
        int previousImeWindowStatus = (z ? 1 : 0) | i;
        startViews(prepareWindow(showInput));
        int nextImeWindowStatus = mapToImeWindowStatus();
        if (previousImeWindowStatus != nextImeWindowStatus) {
            setImeWindowStatus(nextImeWindowStatus, this.mBackDisposition);
        }
        onWindowShown();
        this.mWindowVisible = true;
        if ((previousImeWindowStatus & 1) == 0) {
            if (DEBUG) {
                Log.v(TAG, "showWindow: draw decorView!");
            }
            this.mWindow.show();
            mInputMethodServiceExt.hookUpdateNavigationGuardColor(this.mWindow);
            mInputMethodServiceExt.hookUpdateStartTime();
        }
        this.mDecorViewWasVisible = true;
        this.mInShowWindow = false;
        Trace.traceEnd(32L);
    }

    private boolean prepareWindow(boolean showInput) {
        boolean doShowInput = false;
        this.mDecorViewVisible = true;
        if (!this.mShowInputRequested && this.mInputStarted && showInput) {
            doShowInput = true;
            this.mShowInputRequested = true;
        }
        if (DEBUG) {
            Log.v(TAG, "showWindow: updating UI");
        }
        initialize();
        updateFullscreenMode();
        updateInputViewShown();
        if (!this.mViewsCreated) {
            this.mViewsCreated = true;
            initialize();
            if (DEBUG) {
                Log.v(TAG, "CALL: onCreateCandidatesView");
            }
            View v = onCreateCandidatesView();
            if (DEBUG) {
                Log.v(TAG, "showWindow: candidates=" + v);
            }
            if (v != null) {
                setCandidatesView(v);
            }
        }
        return doShowInput;
    }

    private void startViews(boolean doShowInput) {
        if (this.mShowInputRequested) {
            if (!this.mInputViewStarted) {
                if (DEBUG) {
                    Log.v(TAG, "CALL: onStartInputView");
                }
                this.mInputViewStarted = true;
                this.mInlineSuggestionSessionController.notifyOnStartInputView();
                onStartInputView(this.mInputEditorInfo, false);
            }
        } else if (!this.mCandidatesViewStarted) {
            if (DEBUG) {
                Log.v(TAG, "CALL: onStartCandidatesView");
            }
            this.mCandidatesViewStarted = true;
            onStartCandidatesView(this.mInputEditorInfo, false);
        }
        if (doShowInput) {
            startExtractingText(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void applyVisibilityInInsetsConsumerIfNecessary(boolean setVisible) {
        ImeTracing.getInstance().triggerServiceDump("InputMethodService#applyVisibilityInInsetsConsumerIfNecessary", this, null);
        if (setVisible) {
            cancelImeSurfaceRemoval();
        }
        this.mPrivOps.applyImeVisibilityAsync(setVisible ? this.mCurShowInputToken : this.mCurHideInputToken, setVisible);
    }

    private void finishViews(boolean finishingInput) {
        if (this.mInputViewStarted) {
            if (DEBUG) {
                Log.v(TAG, "CALL: onFinishInputView");
            }
            this.mInlineSuggestionSessionController.notifyOnFinishInputView();
            onFinishInputView(finishingInput);
        } else if (this.mCandidatesViewStarted) {
            if (DEBUG) {
                Log.v(TAG, "CALL: onFinishCandidatesView");
            }
            onFinishCandidatesView(finishingInput);
        }
        this.mInputViewStarted = false;
        this.mCandidatesViewStarted = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doHideWindow() {
        setImeWindowStatus(0, this.mBackDisposition);
        hideWindow();
    }

    public void hideWindow() {
        if (DEBUG) {
            Log.v(TAG, "CALL: hideWindow");
        }
        ImeTracing.getInstance().triggerServiceDump("InputMethodService#hideWindow", this, null);
        this.mWindowVisible = false;
        finishViews(false);
        if (this.mDecorViewVisible) {
            View view = this.mInputView;
            if (view != null) {
                view.dispatchWindowVisibilityChanged(8);
            }
            this.mDecorViewVisible = false;
            onWindowHidden();
            this.mDecorViewWasVisible = false;
            mInputMethodServiceExt.hookUploadData();
        }
        this.mLastWasInFullscreenMode = this.mIsFullscreen;
        updateFullscreenMode();
    }

    public void onWindowShown() {
    }

    public void onWindowHidden() {
    }

    public void onBindInput() {
    }

    public void onUnbindInput() {
    }

    public void onStartInput(EditorInfo attribute, boolean restarting) {
    }

    void doFinishInput() {
        if (DEBUG) {
            Log.v(TAG, "CALL: doFinishInput");
        }
        ImeTracing.getInstance().triggerServiceDump("InputMethodService#doFinishInput", this, null);
        finishViews(true);
        if (this.mInputStarted) {
            this.mInlineSuggestionSessionController.notifyOnFinishInput();
            if (DEBUG) {
                Log.v(TAG, "CALL: onFinishInput");
            }
            onFinishInput();
        }
        this.mInputStarted = false;
        this.mStartedInputConnection = null;
        this.mCurCompletions = null;
    }

    void doStartInput(InputConnection ic, EditorInfo attribute, boolean restarting) {
        if (!restarting && this.mInputStarted) {
            doFinishInput();
        }
        AutofillId autofillId = null;
        ImeTracing.getInstance().triggerServiceDump("InputMethodService#doStartInput", this, null);
        this.mInputStarted = true;
        this.mStartedInputConnection = ic;
        this.mInputEditorInfo = attribute;
        initialize();
        InlineSuggestionSessionController inlineSuggestionSessionController = this.mInlineSuggestionSessionController;
        String str = attribute == null ? null : attribute.packageName;
        if (attribute != null) {
            autofillId = attribute.autofillId;
        }
        inlineSuggestionSessionController.notifyOnStartInput(str, autofillId);
        if (DEBUG) {
            Log.v(TAG, "CALL: onStartInput");
        }
        onStartInput(attribute, restarting);
        if (this.mDecorViewVisible) {
            if (this.mShowInputRequested) {
                if (DEBUG) {
                    Log.v(TAG, "CALL: onStartInputView");
                }
                this.mInputViewStarted = true;
                this.mInlineSuggestionSessionController.notifyOnStartInputView();
                onStartInputView(this.mInputEditorInfo, restarting);
                startExtractingText(true);
            } else if (this.mCandidatesVisibility == 0) {
                if (DEBUG) {
                    Log.v(TAG, "CALL: onStartCandidatesView");
                }
                this.mCandidatesViewStarted = true;
                onStartCandidatesView(this.mInputEditorInfo, restarting);
            }
            mInputMethodServiceExt.hookUpdateNavigationGuardColor(this.mWindow);
        }
    }

    public void onFinishInput() {
        InputConnection ic = getCurrentInputConnection();
        if (ic != null) {
            ic.finishComposingText();
        }
    }

    public void onDisplayCompletions(CompletionInfo[] completions) {
    }

    public void onUpdateExtractedText(int token, ExtractedText text) {
        ExtractEditText extractEditText;
        if (this.mExtractedToken == token && text != null && (extractEditText = this.mExtractEditText) != null) {
            this.mExtractedText = text;
            extractEditText.setExtractedText(text);
        }
    }

    public void onUpdateSelection(int oldSelStart, int oldSelEnd, int newSelStart, int newSelEnd, int candidatesStart, int candidatesEnd) {
        ExtractedText extractedText;
        ExtractEditText eet = this.mExtractEditText;
        if (eet != null && isFullscreenMode() && (extractedText = this.mExtractedText) != null) {
            int off = extractedText.startOffset;
            eet.startInternalChanges();
            int newSelStart2 = newSelStart - off;
            int newSelEnd2 = newSelEnd - off;
            int len = eet.getText().length();
            if (newSelStart2 < 0) {
                newSelStart2 = 0;
            } else if (newSelStart2 > len) {
                newSelStart2 = len;
            }
            if (newSelEnd2 < 0) {
                newSelEnd2 = 0;
            } else if (newSelEnd2 > len) {
                newSelEnd2 = len;
            }
            eet.setSelection(newSelStart2, newSelEnd2);
            eet.finishInternalChanges();
        }
    }

    @Deprecated
    public void onViewClicked(boolean focusChanged) {
    }

    @Deprecated
    public void onUpdateCursor(Rect newCursor) {
    }

    public void onUpdateCursorAnchorInfo(CursorAnchorInfo cursorAnchorInfo) {
    }

    public void requestHideSelf(int flags) {
        ImeTracing.getInstance().triggerServiceDump("InputMethodService#requestHideSelf", this, null);
        this.mPrivOps.hideMySoftInput(flags);
    }

    public final void requestShowSelf(int flags) {
        ImeTracing.getInstance().triggerServiceDump("InputMethodService#requestShowSelf", this, null);
        this.mPrivOps.showMySoftInput(flags);
    }

    private boolean handleBack(boolean doIt) {
        if (this.mShowInputRequested) {
            if (doIt) {
                requestHideSelf(0);
            }
            return true;
        } else if (!this.mDecorViewVisible) {
            return false;
        } else {
            if (this.mCandidatesVisibility == 0) {
                if (doIt) {
                    setCandidatesViewShown(false);
                }
            } else if (doIt) {
                doHideWindow();
            }
            return true;
        }
    }

    private ExtractEditText getExtractEditTextIfVisible() {
        if (!isExtractViewShown() || !isInputViewShown()) {
            return null;
        }
        return this.mExtractEditText;
    }

    @Override // android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() != 4) {
            return doMovementKey(keyCode, event, -1);
        }
        ExtractEditText eet = getExtractEditTextIfVisible();
        if (eet != null && eet.handleBackInTextActionModeIfNeeded(event)) {
            return true;
        }
        if (!handleBack(false)) {
            return false;
        }
        event.startTracking();
        return true;
    }

    @Override // android.view.KeyEvent.Callback
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        return false;
    }

    @Override // android.view.KeyEvent.Callback
    public boolean onKeyMultiple(int keyCode, int count, KeyEvent event) {
        return doMovementKey(keyCode, event, count);
    }

    @Override // android.view.KeyEvent.Callback
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == 4) {
            ExtractEditText eet = getExtractEditTextIfVisible();
            if (eet != null && eet.handleBackInTextActionModeIfNeeded(event)) {
                return true;
            }
            if (event.isTracking() && !event.isCanceled()) {
                return handleBack(true);
            }
        }
        return doMovementKey(keyCode, event, -2);
    }

    @Override // android.inputmethodservice.AbstractInputMethodService
    public boolean onTrackballEvent(MotionEvent event) {
        if (!DEBUG) {
            return false;
        }
        Log.v(TAG, "onTrackballEvent: " + event);
        return false;
    }

    @Override // android.inputmethodservice.AbstractInputMethodService
    public boolean onGenericMotionEvent(MotionEvent event) {
        if (!DEBUG) {
            return false;
        }
        Log.v(TAG, "onGenericMotionEvent(): event " + event);
        return false;
    }

    public void onAppPrivateCommand(String action, Bundle data) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onToggleSoftInput(int showFlags, int hideFlags) {
        if (DEBUG) {
            Log.v(TAG, "toggleSoftInput()");
        }
        if (isInputViewShown()) {
            requestHideSelf(hideFlags);
        } else {
            requestShowSelf(showFlags);
        }
    }

    void reportExtractedMovement(int keyCode, int count) {
        int dx = 0;
        int dy = 0;
        switch (keyCode) {
            case 19:
                dy = -count;
                break;
            case 20:
                dy = count;
                break;
            case 21:
                dx = -count;
                break;
            case 22:
                dx = count;
                break;
        }
        onExtractedCursorMovement(dx, dy);
    }

    boolean doMovementKey(int keyCode, KeyEvent event, int count) {
        ExtractEditText eet = getExtractEditTextIfVisible();
        if (eet != null) {
            MovementMethod movement = eet.getMovementMethod();
            Layout layout = eet.getLayout();
            if (!(movement == null || layout == null)) {
                if (count == -1) {
                    if (movement.onKeyDown(eet, eet.getText(), keyCode, event)) {
                        reportExtractedMovement(keyCode, 1);
                        return true;
                    }
                } else if (count == -2) {
                    if (movement.onKeyUp(eet, eet.getText(), keyCode, event)) {
                        return true;
                    }
                } else if (movement.onKeyOther(eet, eet.getText(), event)) {
                    reportExtractedMovement(keyCode, count);
                } else {
                    KeyEvent down = KeyEvent.changeAction(event, 0);
                    if (movement.onKeyDown(eet, eet.getText(), keyCode, down)) {
                        KeyEvent up = KeyEvent.changeAction(event, 1);
                        movement.onKeyUp(eet, eet.getText(), keyCode, up);
                        while (true) {
                            count--;
                            if (count <= 0) {
                                break;
                            }
                            movement.onKeyDown(eet, eet.getText(), keyCode, down);
                            movement.onKeyUp(eet, eet.getText(), keyCode, up);
                        }
                        reportExtractedMovement(keyCode, count);
                    }
                }
            }
            switch (keyCode) {
                case 19:
                case 20:
                case 21:
                case 22:
                    return true;
            }
        }
        return false;
    }

    public void sendDownUpKeyEvents(int keyEventCode) {
        InputConnection ic = getCurrentInputConnection();
        if (ic != null) {
            long eventTime = SystemClock.uptimeMillis();
            ic.sendKeyEvent(new KeyEvent(eventTime, eventTime, 0, keyEventCode, 0, 0, -1, 0, 6));
            ic.sendKeyEvent(new KeyEvent(eventTime, SystemClock.uptimeMillis(), 1, keyEventCode, 0, 0, -1, 0, 6));
        }
    }

    public boolean sendDefaultEditorAction(boolean fromEnterKey) {
        EditorInfo ei = getCurrentInputEditorInfo();
        if (ei == null) {
            return false;
        }
        if ((fromEnterKey && (ei.imeOptions & 1073741824) != 0) || (ei.imeOptions & 255) == 1) {
            return false;
        }
        InputConnection ic = getCurrentInputConnection();
        if (ic != null) {
            ic.performEditorAction(ei.imeOptions & 255);
        }
        return true;
    }

    public void sendKeyChar(char charCode) {
        switch (charCode) {
            case '\n':
                if (!sendDefaultEditorAction(true)) {
                    sendDownUpKeyEvents(66);
                    return;
                }
                return;
            default:
                if (charCode < '0' || charCode > '9') {
                    InputConnection ic = getCurrentInputConnection();
                    if (ic != null) {
                        ic.commitText(String.valueOf(charCode), 1);
                        return;
                    }
                    return;
                }
                sendDownUpKeyEvents((charCode - '0') + 7);
                return;
        }
    }

    public void onExtractedSelectionChanged(int start, int end) {
        InputConnection conn = getCurrentInputConnection();
        if (conn != null) {
            conn.setSelection(start, end);
        }
    }

    public void onExtractedDeleteText(int start, int end) {
        InputConnection conn = getCurrentInputConnection();
        if (conn != null) {
            conn.finishComposingText();
            conn.setSelection(start, start);
            conn.deleteSurroundingText(0, end - start);
        }
    }

    public void onExtractedReplaceText(int start, int end, CharSequence text) {
        InputConnection conn = getCurrentInputConnection();
        if (conn != null) {
            conn.setComposingRegion(start, end);
            conn.commitText(text, 1);
        }
    }

    public void onExtractedSetSpan(Object span, int start, int end, int flags) {
        InputConnection conn = getCurrentInputConnection();
        if (conn != null && conn.setSelection(start, end)) {
            CharSequence text = conn.getSelectedText(1);
            if (text instanceof Spannable) {
                ((Spannable) text).setSpan(span, 0, text.length(), flags);
                conn.setComposingRegion(start, end);
                conn.commitText(text, 1);
            }
        }
    }

    public void onExtractedTextClicked() {
        ExtractEditText extractEditText = this.mExtractEditText;
        if (extractEditText != null && extractEditText.hasVerticalScrollBar()) {
            setCandidatesViewShown(false);
        }
    }

    public void onExtractedCursorMovement(int dx, int dy) {
        ExtractEditText extractEditText = this.mExtractEditText;
        if (extractEditText != null && dy != 0 && extractEditText.hasVerticalScrollBar()) {
            setCandidatesViewShown(false);
        }
    }

    public boolean onExtractTextContextMenuItem(int id) {
        InputConnection ic = getCurrentInputConnection();
        if (ic == null) {
            return true;
        }
        ic.performContextMenuAction(id);
        return true;
    }

    public CharSequence getTextForImeAction(int imeOptions) {
        switch (imeOptions & 255) {
            case 1:
                return null;
            case 2:
                return getText(R.string.ime_action_go);
            case 3:
                return getText(R.string.ime_action_search);
            case 4:
                return getText(R.string.ime_action_send);
            case 5:
                return getText(R.string.ime_action_next);
            case 6:
                return getText(R.string.ime_action_done);
            case 7:
                return getText(R.string.ime_action_previous);
            default:
                return getText(R.string.ime_action_default);
        }
    }

    private int getIconForImeAction(int imeOptions) {
        switch (imeOptions & 255) {
            case 2:
                return R.drawable.ic_input_extract_action_go;
            case 3:
                return R.drawable.ic_input_extract_action_search;
            case 4:
                return R.drawable.ic_input_extract_action_send;
            case 5:
                return R.drawable.ic_input_extract_action_next;
            case 6:
                return R.drawable.ic_input_extract_action_done;
            case 7:
                return R.drawable.ic_input_extract_action_previous;
            default:
                return R.drawable.ic_input_extract_action_return;
        }
    }

    public void onUpdateExtractingVisibility(EditorInfo ei) {
        if (ei.inputType == 0 || (ei.imeOptions & 268435456) != 0) {
            setExtractViewShown(false);
        } else {
            setExtractViewShown(true);
        }
    }

    public void onUpdateExtractingViews(EditorInfo ei) {
        if (isExtractViewShown() && this.mExtractAccessories != null) {
            boolean hasAction = true;
            if (ei.actionLabel == null && ((ei.imeOptions & 255) == 1 || (ei.imeOptions & 536870912) != 0 || ei.inputType == 0)) {
                hasAction = false;
            }
            if (hasAction) {
                this.mExtractAccessories.setVisibility(0);
                View view = this.mExtractAction;
                if (view != null) {
                    if (view instanceof ImageButton) {
                        ((ImageButton) view).setImageResource(getIconForImeAction(ei.imeOptions));
                        if (ei.actionLabel != null) {
                            this.mExtractAction.setContentDescription(ei.actionLabel);
                        } else {
                            this.mExtractAction.setContentDescription(getTextForImeAction(ei.imeOptions));
                        }
                    } else if (ei.actionLabel != null) {
                        ((TextView) this.mExtractAction).setText(ei.actionLabel);
                    } else {
                        ((TextView) this.mExtractAction).setText(getTextForImeAction(ei.imeOptions));
                    }
                    this.mExtractAction.setOnClickListener(this.mActionClickListener);
                    return;
                }
                return;
            }
            this.mExtractAccessories.setVisibility(8);
            View view2 = this.mExtractAction;
            if (view2 != null) {
                view2.setOnClickListener(null);
            }
        }
    }

    public void onExtractingInputChanged(EditorInfo ei) {
        if (ei.inputType == 0) {
            requestHideSelf(2);
        }
    }

    void startExtractingText(boolean inputChanged) {
        ExtractEditText eet = this.mExtractEditText;
        if (eet != null && getCurrentInputStarted() && isFullscreenMode()) {
            this.mExtractedToken++;
            ExtractedTextRequest req = new ExtractedTextRequest();
            req.token = this.mExtractedToken;
            req.flags = 1;
            req.hintMaxLines = 10;
            req.hintMaxChars = 10000;
            InputConnection ic = getCurrentInputConnection();
            ExtractedText extractedText = ic == null ? null : ic.getExtractedText(req, 1);
            this.mExtractedText = extractedText;
            if (extractedText == null || ic == null) {
                Log.e(TAG, "Unexpected null in startExtractingText : mExtractedText = " + this.mExtractedText + ", input connection = " + ic);
            }
            EditorInfo ei = getCurrentInputEditorInfo();
            try {
                eet.startInternalChanges();
                onUpdateExtractingVisibility(ei);
                onUpdateExtractingViews(ei);
                int inputType = ei.inputType;
                if ((inputType & 15) == 1 && (262144 & inputType) != 0) {
                    inputType |= 131072;
                }
                eet.setInputType(inputType);
                eet.setHint(ei.hintText);
                if (this.mExtractedText != null) {
                    eet.setEnabled(true);
                    eet.setExtractedText(this.mExtractedText);
                } else {
                    eet.setEnabled(false);
                    eet.setText("");
                }
                if (inputChanged) {
                    onExtractingInputChanged(ei);
                }
            } finally {
                eet.finishInternalChanges();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchOnCurrentInputMethodSubtypeChanged(InputMethodSubtype newSubtype) {
        synchronized (this.mLock) {
            this.mNotifyUserActionSent = false;
        }
        onCurrentInputMethodSubtypeChanged(newSubtype);
    }

    protected void onCurrentInputMethodSubtypeChanged(InputMethodSubtype newSubtype) {
        if (DEBUG) {
            int nameResId = newSubtype.getNameResId();
            String mode = newSubtype.getMode();
            StringBuilder sb = new StringBuilder();
            sb.append("changeInputMethodSubtype:");
            sb.append(nameResId == 0 ? "<none>" : getString(nameResId));
            sb.append(",");
            sb.append(mode);
            sb.append(",");
            sb.append(newSubtype.getLocale());
            sb.append(",");
            sb.append(newSubtype.getExtraValue());
            String output = sb.toString();
            Log.v(TAG, "--- " + output);
        }
    }

    @Deprecated
    public int getInputMethodWindowRecommendedHeight() {
        Log.w(TAG, "getInputMethodWindowRecommendedHeight() is deprecated and now always returns 0. Do not use this method.");
        return 0;
    }

    @Override // android.inputmethodservice.AbstractInputMethodService
    public final void exposeContent(InputContentInfo inputContentInfo, InputConnection inputConnection) {
        if (inputConnection != null && getCurrentInputConnection() == inputConnection) {
            exposeContentInternal(inputContentInfo, getCurrentInputEditorInfo());
        }
    }

    @Override // android.inputmethodservice.AbstractInputMethodService
    public final void notifyUserActionIfNecessary() {
        synchronized (this.mLock) {
            if (!this.mNotifyUserActionSent) {
                this.mPrivOps.notifyUserActionAsync();
                this.mNotifyUserActionSent = true;
            }
        }
    }

    private void exposeContentInternal(InputContentInfo inputContentInfo, EditorInfo editorInfo) {
        Uri contentUri = inputContentInfo.getContentUri();
        IInputContentUriToken uriToken = this.mPrivOps.createInputContentUriToken(contentUri, editorInfo.packageName);
        if (uriToken == null) {
            Log.e(TAG, "createInputContentAccessToken failed. contentUri=" + contentUri.toString() + " packageName=" + editorInfo.packageName);
            return;
        }
        inputContentInfo.setUriToken(uriToken);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int mapToImeWindowStatus() {
        return (isInputViewShown() ? 2 : 0) | 1;
    }

    private boolean isAutomotive() {
        return getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_AUTOMOTIVE);
    }

    @Override // android.inputmethodservice.AbstractInputMethodService, android.app.Service
    protected void dump(FileDescriptor fd, PrintWriter fout, String[] args) {
        DEBUG = mInputMethodServiceExt.hookDebugSwitchUpdated(args, DEBUG);
        Printer p = new PrintWriterPrinter(fout);
        p.println("Input method service state for " + this + SettingsStringUtil.DELIMITER);
        StringBuilder sb = new StringBuilder();
        sb.append("  mViewsCreated=");
        sb.append(this.mViewsCreated);
        p.println(sb.toString());
        p.println("  mDecorViewVisible=" + this.mDecorViewVisible + " mDecorViewWasVisible=" + this.mDecorViewWasVisible + " mWindowVisible=" + this.mWindowVisible + " mInShowWindow=" + this.mInShowWindow);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("  Configuration=");
        sb2.append(getResources().getConfiguration());
        p.println(sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append("  mToken=");
        sb3.append(this.mToken);
        p.println(sb3.toString());
        p.println("  mInputBinding=" + this.mInputBinding);
        p.println("  mInputConnection=" + this.mInputConnection);
        p.println("  mStartedInputConnection=" + this.mStartedInputConnection);
        p.println("  mInputStarted=" + this.mInputStarted + " mInputViewStarted=" + this.mInputViewStarted + " mCandidatesViewStarted=" + this.mCandidatesViewStarted);
        if (this.mInputEditorInfo != null) {
            p.println("  mInputEditorInfo:");
            this.mInputEditorInfo.dump(p, "    ");
        } else {
            p.println("  mInputEditorInfo: null");
        }
        p.println("  mShowInputRequested=" + this.mShowInputRequested + " mLastShowInputRequested=" + this.mLastShowInputRequested + " mShowInputFlags=0x" + Integer.toHexString(this.mShowInputFlags));
        p.println("  mCandidatesVisibility=" + this.mCandidatesVisibility + " mFullscreenApplied=" + this.mFullscreenApplied + " mIsFullscreen=" + this.mIsFullscreen + " mExtractViewHidden=" + this.mExtractViewHidden);
        if (this.mExtractedText != null) {
            p.println("  mExtractedText:");
            p.println("    text=" + this.mExtractedText.text.length() + " chars startOffset=" + this.mExtractedText.startOffset);
            p.println("    selectionStart=" + this.mExtractedText.selectionStart + " selectionEnd=" + this.mExtractedText.selectionEnd + " flags=0x" + Integer.toHexString(this.mExtractedText.flags));
        } else {
            p.println("  mExtractedText: null");
        }
        p.println("  mExtractedToken=" + this.mExtractedToken);
        p.println("  mIsInputViewShown=" + this.mIsInputViewShown + " mStatusIcon=" + this.mStatusIcon);
        p.println("Last computed insets:");
        p.println("  contentTopInsets=" + this.mTmpInsets.contentTopInsets + " visibleTopInsets=" + this.mTmpInsets.visibleTopInsets + " touchableInsets=" + this.mTmpInsets.touchableInsets + " touchableRegion=" + this.mTmpInsets.touchableRegion);
        StringBuilder sb4 = new StringBuilder();
        sb4.append(" mSettingsObserver=");
        sb4.append(this.mSettingsObserver);
        p.println(sb4.toString());
    }

    @Override // android.inputmethodservice.AbstractInputMethodService
    public final void dumpProtoInternal(ProtoOutputStream proto, ProtoOutputStream icProto) {
        long token = proto.start(1146756268035L);
        this.mWindow.dumpDebug(proto, 1146756268033L);
        proto.write(1133871366146L, this.mViewsCreated);
        proto.write(1133871366147L, this.mDecorViewVisible);
        proto.write(1133871366148L, this.mDecorViewWasVisible);
        proto.write(1133871366149L, this.mWindowVisible);
        proto.write(1133871366150L, this.mInShowWindow);
        proto.write(1138166333447L, getResources().getConfiguration().toString());
        proto.write(1138166333448L, Objects.toString(this.mToken));
        proto.write(1138166333449L, Objects.toString(this.mInputBinding));
        proto.write(1133871366154L, this.mInputStarted);
        proto.write(1133871366155L, this.mInputViewStarted);
        proto.write(1133871366156L, this.mCandidatesViewStarted);
        EditorInfo editorInfo = this.mInputEditorInfo;
        if (editorInfo != null) {
            editorInfo.dumpDebug(proto, 1146756268045L);
        }
        proto.write(1133871366158L, this.mShowInputRequested);
        proto.write(1133871366159L, this.mLastShowInputRequested);
        proto.write(1120986464274L, this.mShowInputFlags);
        proto.write(1120986464275L, this.mCandidatesVisibility);
        proto.write(1133871366164L, this.mFullscreenApplied);
        proto.write(1133871366165L, this.mIsFullscreen);
        proto.write(1133871366166L, this.mExtractViewHidden);
        proto.write(1120986464279L, this.mExtractedToken);
        proto.write(1133871366168L, this.mIsInputViewShown);
        proto.write(1120986464281L, this.mStatusIcon);
        this.mTmpInsets.dumpDebug(proto, 1146756268058L);
        proto.write(1138166333467L, Objects.toString(this.mSettingsObserver));
        if (icProto != null) {
            proto.write(1146756268060L, icProto.getBytes());
        }
        proto.end(token);
    }
}
