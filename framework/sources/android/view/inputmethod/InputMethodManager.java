package android.view.inputmethod;

import android.Manifest;
import android.app.ActivityThread;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Rect;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.ServiceManager;
import android.os.Trace;
import android.os.UserHandle;
import android.provider.Settings;
import android.provider.SettingsStringUtil;
import android.text.style.SuggestionSpan;
import android.util.Log;
import android.util.Pools;
import android.util.PrintWriterPrinter;
import android.util.Printer;
import android.util.SparseArray;
import android.util.imetracing.ImeTracing;
import android.util.proto.ProtoOutputStream;
import android.view.ImeFocusController;
import android.view.ImeInsetsSourceConsumer;
import android.view.InputChannel;
import android.view.InputEvent;
import android.view.InputEventSender;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewRootImpl;
import android.view.autofill.AutofillManager;
import com.android.internal.inputmethod.InputMethodDebug;
import com.android.internal.inputmethod.InputMethodPrivilegedOperationsRegistry;
import com.android.internal.os.SomeArgs;
import com.android.internal.view.IInputConnectionWrapper;
import com.android.internal.view.IInputContext;
import com.android.internal.view.IInputMethodClient;
import com.android.internal.view.IInputMethodManager;
import com.android.internal.view.IInputMethodSession;
import com.android.internal.view.InputBindResult;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
/* loaded from: classes3.dex */
public final class InputMethodManager {
    public static final int DISPATCH_HANDLED = 1;
    public static final int DISPATCH_IN_PROGRESS = -1;
    public static final int DISPATCH_NOT_HANDLED = 0;
    public static final int HIDE_IMPLICIT_ONLY = 1;
    public static final int HIDE_NOT_ALWAYS = 2;
    static final long INPUT_METHOD_NOT_RESPONDING_TIMEOUT = 2500;
    static final int MSG_BIND = 2;
    static final int MSG_DUMP = 1;
    static final int MSG_FLUSH_INPUT_EVENT = 7;
    static final int MSG_REPORT_FULLSCREEN_MODE = 10;
    static final int MSG_SEND_INPUT_EVENT = 5;
    static final int MSG_SET_ACTIVE = 4;
    static final int MSG_TIMEOUT_INPUT_EVENT = 6;
    static final int MSG_UNBIND = 3;
    private static final int NOT_A_SUBTYPE_ID = -1;
    static final String PENDING_EVENT_COUNTER = "aq:imm";
    private static final int REQUEST_UPDATE_CURSOR_ANCHOR_INFO_NONE = 0;
    public static final int RESULT_HIDDEN = 3;
    public static final int RESULT_SHOWN = 2;
    public static final int RESULT_UNCHANGED_HIDDEN = 1;
    public static final int RESULT_UNCHANGED_SHOWN = 0;
    public static final int SHOW_FORCED = 2;
    public static final int SHOW_IMPLICIT = 1;
    public static final int SHOW_IM_PICKER_MODE_AUTO = 0;
    public static final int SHOW_IM_PICKER_MODE_EXCLUDE_AUXILIARY_SUBTYPES = 2;
    public static final int SHOW_IM_PICKER_MODE_INCLUDE_AUXILIARY_SUBTYPES = 1;
    private static final String SUBTYPE_MODE_VOICE = "voice";
    static final String TAG = "InputMethodManager";
    @Deprecated
    static InputMethodManager sInstance;
    CompletionInfo[] mCompletions;
    InputChannel mCurChannel;
    String mCurId;
    @Deprecated
    IInputMethodSession mCurMethod;
    ViewRootImpl mCurRootView;
    ImeInputEventSender mCurSender;
    EditorInfo mCurrentTextBoxAttribute;
    int mCursorCandEnd;
    int mCursorCandStart;
    int mCursorSelEnd;
    int mCursorSelStart;
    private final int mDisplayId;
    final InputConnection mDummyInputConnection;
    boolean mFullscreenMode;
    final H mH;
    final IInputContext mIInputContext;
    private ImeInsetsSourceConsumer mImeInsetsConsumer;
    public IInputMethodManagerExt mInputMethodManagerExt;
    final Looper mMainLooper;
    boolean mServedConnecting;
    IInputConnectionWrapper mServedInputConnectionWrapper;
    final IInputMethodManager mService;
    static boolean DEBUG = false;
    private static final Object sLock = new Object();
    private static final SparseArray<InputMethodManager> sInstanceMap = new SparseArray<>();
    boolean mActive = false;
    private boolean mRestartOnNextWindowFocus = true;
    Rect mTmpCursorRect = new Rect();
    Rect mCursorRect = new Rect();
    private CursorAnchorInfo mCursorAnchorInfo = null;
    private boolean mIsInputMethodSuppressingSpellChecker = false;
    int mBindSequence = -1;
    private InputMethodSessionWrapper mCurrentInputMethodSession = null;
    private int mRequestUpdateCursorAnchorInfoMonitorMode = 0;
    final Pools.Pool<PendingEvent> mPendingEventPool = new Pools.SimplePool(20);
    final SparseArray<PendingEvent> mPendingEvents = new SparseArray<>(20);
    final DelegateImpl mDelegate = new DelegateImpl();
    final IInputMethodClient.Stub mClient = new IInputMethodClient.Stub() { // from class: android.view.inputmethod.InputMethodManager.1
        @Override // android.os.Binder
        protected void dump(FileDescriptor fd, PrintWriter fout, String[] args) {
            CountDownLatch latch = new CountDownLatch(1);
            SomeArgs sargs = SomeArgs.obtain();
            sargs.arg1 = fd;
            sargs.arg2 = fout;
            sargs.arg3 = args;
            sargs.arg4 = latch;
            InputMethodManager.this.mH.sendMessage(InputMethodManager.this.mH.obtainMessage(1, sargs));
            try {
                if (!latch.await(5L, TimeUnit.SECONDS)) {
                    fout.println("Timeout waiting for dump");
                }
            } catch (InterruptedException e) {
                fout.println("Interrupted waiting for dump");
            }
        }

        @Override // com.android.internal.view.IInputMethodClient
        public void onBindMethod(InputBindResult res) {
            InputMethodManager.this.mH.obtainMessage(2, res).sendToTarget();
        }

        @Override // com.android.internal.view.IInputMethodClient
        public void onUnbindMethod(int sequence, int unbindReason) {
            InputMethodManager.this.mH.obtainMessage(3, sequence, unbindReason).sendToTarget();
        }

        @Override // com.android.internal.view.IInputMethodClient
        public void setActive(boolean active, boolean fullscreen, boolean reportToImeController) {
            InputMethodManager.this.mH.obtainMessage(4, active ? 1 : 0, fullscreen ? 1 : 0, Boolean.valueOf(reportToImeController)).sendToTarget();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.android.internal.view.IInputMethodClient
        public void scheduleStartInputIfNecessary(boolean fullscreen) {
            InputMethodManager.this.mH.obtainMessage(4, 0, fullscreen ? 1 : 0).sendToTarget();
            InputMethodManager.this.mH.obtainMessage(4, 1, fullscreen).sendToTarget();
        }

        @Override // com.android.internal.view.IInputMethodClient
        public void reportFullscreenMode(boolean fullscreen) {
            InputMethodManager.this.mH.obtainMessage(10, fullscreen ? 1 : 0, 0).sendToTarget();
        }

        @Override // com.android.internal.view.IInputMethodClient
        public void setImeTraceEnabled(boolean enabled) {
            ImeTracing.getInstance().setEnabled(enabled);
        }

        @Override // com.android.internal.view.IInputMethodClient
        public void throwExceptionFromSystem(String message) {
            throw new RuntimeException(message);
        }
    };

    /* loaded from: classes3.dex */
    public interface FinishedInputEventCallback {
        void onFinishedInputEvent(Object obj, boolean z);
    }

    public static void ensureDefaultInstanceForDefaultDisplayIfNecessary() {
        forContextInternal(0, Looper.getMainLooper());
    }

    private static boolean isAutofillUIShowing(View servedView) {
        AutofillManager afm = (AutofillManager) servedView.getContext().getSystemService(AutofillManager.class);
        return afm != null && afm.isAutofillUiShowing();
    }

    private InputMethodManager getFallbackInputMethodManagerIfNecessary(View view) {
        ViewRootImpl viewRootImpl;
        int viewRootDisplayId;
        if (view == null || (viewRootImpl = view.getViewRootImpl()) == null || (viewRootDisplayId = viewRootImpl.getDisplayId()) == this.mDisplayId) {
            return null;
        }
        InputMethodManager fallbackImm = (InputMethodManager) viewRootImpl.mContext.getSystemService(InputMethodManager.class);
        if (fallbackImm == null) {
            Log.v(TAG, "b/117267690: Failed to get non-null fallback IMM. view=" + view);
            return null;
        } else if (fallbackImm.mDisplayId != viewRootDisplayId) {
            Log.v(TAG, "b/117267690: Failed to get fallback IMM with expected displayId=" + viewRootDisplayId + " actual IMM#displayId=" + fallbackImm.mDisplayId + " view=" + view);
            return null;
        } else {
            Log.v(TAG, "b/117267690: Display ID mismatch found. ViewRootImpl displayId=" + viewRootDisplayId + " InputMethodManager displayId=" + this.mDisplayId + ". Use the right InputMethodManager instance to avoid performance overhead.", new Throwable());
            return fallbackImm;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean canStartInput(View servedView) {
        return servedView.hasWindowFocus() || isAutofillUIShowing(servedView);
    }

    public void reportPerceptible(IBinder windowToken, boolean perceptible) {
        try {
            this.mService.reportPerceptibleAsync(windowToken, perceptible);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public final class DelegateImpl implements ImeFocusController.InputMethodManagerDelegate {
        private DelegateImpl() {
        }

        @Override // android.view.ImeFocusController.InputMethodManagerDelegate
        public boolean startInput(int startInputReason, View focusedView, int startInputFlags, int softInputMode, int windowFlags) {
            IBinder iBinder = null;
            ImeTracing.getInstance().triggerClientDump("InputMethodManager.DelegateImpl#startInput", InputMethodManager.this, null);
            synchronized (InputMethodManager.this.mH) {
                InputMethodManager.this.mCurrentTextBoxAttribute = null;
                InputMethodManager.this.mCompletions = null;
                InputMethodManager.this.mServedConnecting = true;
                InputMethodManager.this.getServedViewLocked();
            }
            InputMethodManager inputMethodManager = InputMethodManager.this;
            if (focusedView != null) {
                iBinder = focusedView.getWindowToken();
            }
            return inputMethodManager.startInputInner(startInputReason, iBinder, startInputFlags, softInputMode, windowFlags);
        }

        @Override // android.view.ImeFocusController.InputMethodManagerDelegate
        public void finishInput() {
            ImeTracing.getInstance().triggerClientDump("InputMethodManager.DelegateImpl#finishInput", InputMethodManager.this, null);
            synchronized (InputMethodManager.this.mH) {
                InputMethodManager.this.finishInputLocked();
            }
        }

        @Override // android.view.ImeFocusController.InputMethodManagerDelegate
        public void finishInputAndReportToIme() {
            synchronized (InputMethodManager.this.mH) {
                InputMethodManager.this.finishInputLocked();
                if (InputMethodManager.this.mCurrentInputMethodSession != null) {
                    InputMethodManager.this.mCurrentInputMethodSession.finishInput();
                }
            }
        }

        @Override // android.view.ImeFocusController.InputMethodManagerDelegate
        public void closeCurrentIme() {
            InputMethodManager.this.closeCurrentInput();
        }

        @Override // android.view.ImeFocusController.InputMethodManagerDelegate
        public void startInputAsyncOnWindowFocusGain(View focusedView, int softInputMode, int windowFlags, boolean forceNewFocus) {
            RemoteException e;
            RemoteException e2;
            int startInputFlags;
            int startInputReason;
            boolean nextFocusHasConnection = false;
            int startInputFlags2 = InputMethodManager.this.getStartInputFlags(focusedView, 0) | 8;
            ImeTracing.getInstance().triggerClientDump("InputMethodManager.DelegateImpl#startInputAsyncOnWindowFocusGain", InputMethodManager.this, null);
            ImeFocusController controller = InputMethodManager.this.getFocusController();
            if (controller != null) {
                if (!controller.checkFocus(forceNewFocus, false) || !startInput(1, focusedView, startInputFlags2, softInputMode, windowFlags)) {
                    synchronized (InputMethodManager.this.mH) {
                        try {
                            try {
                                View servedView = controller.getServedView();
                                if (servedView != null && servedView == focusedView && hasActiveConnection(focusedView)) {
                                    nextFocusHasConnection = true;
                                }
                                if (InputMethodManager.DEBUG) {
                                    Log.v(InputMethodManager.TAG, "Reporting focus gain, without startInput, nextFocusIsServedView=" + nextFocusHasConnection);
                                }
                                startInputFlags = InputMethodManager.this.mInputMethodManagerExt.adjustFlagForWindowingMode(focusedView, startInputFlags2);
                                if (nextFocusHasConnection) {
                                    startInputReason = 2;
                                } else {
                                    startInputReason = 3;
                                }
                            } catch (RemoteException e3) {
                                e2 = e3;
                            }
                            try {
                                try {
                                    InputMethodManager.this.mService.startInputOrWindowGainedFocus(startInputReason, InputMethodManager.this.mClient, focusedView.getWindowToken(), startInputFlags, softInputMode, windowFlags, null, null, 0, InputMethodManager.this.mCurRootView.mContext.getApplicationInfo().targetSdkVersion);
                                } catch (Throwable th) {
                                    e = th;
                                    throw e;
                                }
                            } catch (RemoteException e4) {
                                e2 = e4;
                                throw e2.rethrowFromSystemServer();
                            }
                        } catch (Throwable th2) {
                            e = th2;
                        }
                    }
                }
            }
        }

        @Override // android.view.ImeFocusController.InputMethodManagerDelegate
        public void finishComposingText() {
            if (InputMethodManager.this.mServedInputConnectionWrapper != null) {
                InputMethodManager.this.mServedInputConnectionWrapper.finishComposingText();
            }
        }

        @Override // android.view.ImeFocusController.InputMethodManagerDelegate
        public void setCurrentRootView(ViewRootImpl rootView) {
            synchronized (InputMethodManager.this.mH) {
                InputMethodManager.this.mCurRootView = rootView;
                if (InputMethodManager.DEBUG) {
                    Log.v(InputMethodManager.TAG, "setCurrentRootView mCurRootView = " + InputMethodManager.this.mCurRootView);
                }
            }
        }

        @Override // android.view.ImeFocusController.InputMethodManagerDelegate
        public boolean isCurrentRootView(ViewRootImpl rootView) {
            boolean z;
            synchronized (InputMethodManager.this.mH) {
                z = InputMethodManager.this.mCurRootView == rootView;
            }
            return z;
        }

        @Override // android.view.ImeFocusController.InputMethodManagerDelegate
        public boolean isRestartOnNextWindowFocus(boolean reset) {
            boolean result = InputMethodManager.this.mRestartOnNextWindowFocus;
            if (reset) {
                InputMethodManager.this.mRestartOnNextWindowFocus = false;
            }
            return result;
        }

        @Override // android.view.ImeFocusController.InputMethodManagerDelegate
        public boolean hasActiveConnection(View view) {
            synchronized (InputMethodManager.this.mH) {
                boolean z = false;
                if (InputMethodManager.this.hasServedByInputMethodLocked(view) && InputMethodManager.this.mCurrentInputMethodSession != null) {
                    if (InputMethodManager.this.mServedInputConnectionWrapper != null && InputMethodManager.this.mServedInputConnectionWrapper.isActive() && InputMethodManager.this.mServedInputConnectionWrapper.getServedView() == view) {
                        z = true;
                    }
                    return z;
                }
                return false;
            }
        }
    }

    public DelegateImpl getDelegate() {
        return this.mDelegate;
    }

    public boolean hasActiveInputConnection(View view) {
        return this.mDelegate.hasActiveConnection(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View getServedViewLocked() {
        ViewRootImpl viewRootImpl = this.mCurRootView;
        if (viewRootImpl != null) {
            return viewRootImpl.getImeFocusController().getServedView();
        }
        return null;
    }

    private View getNextServedViewLocked() {
        ViewRootImpl viewRootImpl = this.mCurRootView;
        if (viewRootImpl != null) {
            return viewRootImpl.getImeFocusController().getNextServedView();
        }
        return null;
    }

    private void setServedViewLocked(View view) {
        ViewRootImpl viewRootImpl = this.mCurRootView;
        if (viewRootImpl != null) {
            viewRootImpl.getImeFocusController().setServedView(view);
        }
    }

    private void setNextServedViewLocked(View view) {
        ViewRootImpl viewRootImpl = this.mCurRootView;
        if (viewRootImpl != null) {
            viewRootImpl.getImeFocusController().setNextServedView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ImeFocusController getFocusController() {
        synchronized (this.mH) {
            ViewRootImpl viewRootImpl = this.mCurRootView;
            if (viewRootImpl == null) {
                return null;
            }
            return viewRootImpl.getImeFocusController();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasServedByInputMethodLocked(View view) {
        View servedView = getServedViewLocked();
        return servedView == view || (servedView != null && servedView.checkInputConnectionProxy(view));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class H extends Handler {
        H(Looper looper) {
            super(looper, null, true);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            boolean fullscreen = true;
            switch (msg.what) {
                case 1:
                    SomeArgs args = (SomeArgs) msg.obj;
                    try {
                        InputMethodManager.this.doDump((FileDescriptor) args.arg1, (PrintWriter) args.arg2, (String[]) args.arg3);
                    } catch (RuntimeException e) {
                        ((PrintWriter) args.arg2).println("Exception: " + e);
                    }
                    synchronized (args.arg4) {
                        ((CountDownLatch) args.arg4).countDown();
                    }
                    args.recycle();
                    return;
                case 2:
                    InputBindResult res = (InputBindResult) msg.obj;
                    if (InputMethodManager.DEBUG) {
                        Log.i(InputMethodManager.TAG, "handleMessage: MSG_BIND " + res.sequence + "," + res.id);
                    }
                    synchronized (InputMethodManager.this.mH) {
                        if (InputMethodManager.this.mBindSequence >= 0 && InputMethodManager.this.mBindSequence == res.sequence) {
                            InputMethodManager.this.mRequestUpdateCursorAnchorInfoMonitorMode = 0;
                            InputMethodManager.this.setInputChannelLocked(res.channel);
                            InputMethodManager.this.mCurMethod = res.method;
                            InputMethodManager.this.mCurrentInputMethodSession = InputMethodSessionWrapper.createOrNull(res.method);
                            InputMethodManager.this.mCurId = res.id;
                            InputMethodManager.this.mBindSequence = res.sequence;
                            InputMethodManager.this.mIsInputMethodSuppressingSpellChecker = res.isInputMethodSuppressingSpellChecker;
                            InputMethodManager.this.startInputInner(6, null, 0, 0, 0);
                            return;
                        }
                        Log.w(InputMethodManager.TAG, "Ignoring onBind: cur seq=" + InputMethodManager.this.mBindSequence + ", given seq=" + res.sequence);
                        if (!(res.channel == null || res.channel == InputMethodManager.this.mCurChannel)) {
                            res.channel.dispose();
                        }
                        return;
                    }
                case 3:
                    int sequence = msg.arg1;
                    int reason = msg.arg2;
                    if (InputMethodManager.DEBUG) {
                        Log.i(InputMethodManager.TAG, "handleMessage: MSG_UNBIND " + sequence + " reason=" + InputMethodDebug.unbindReasonToString(reason));
                    }
                    synchronized (InputMethodManager.this.mH) {
                        if (InputMethodManager.this.mBindSequence == sequence) {
                            InputMethodManager.this.clearBindingLocked();
                            View servedView = InputMethodManager.this.getServedViewLocked();
                            if (servedView != null && servedView.isFocused()) {
                                InputMethodManager.this.mServedConnecting = true;
                            }
                            boolean startInput = InputMethodManager.this.mActive;
                            if (startInput) {
                                InputMethodManager.this.startInputInner(7, null, 0, 0, 0);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                case 4:
                    final boolean active = msg.arg1 != 0;
                    boolean fullscreen2 = msg.arg2 != 0;
                    boolean reportToImeController = msg.obj != null && ((Boolean) msg.obj).booleanValue();
                    if (InputMethodManager.DEBUG) {
                        Log.i(InputMethodManager.TAG, "handleMessage: MSG_SET_ACTIVE " + active + ", was " + InputMethodManager.this.mActive);
                    }
                    synchronized (InputMethodManager.this.mH) {
                        InputMethodManager.this.mActive = active;
                        InputMethodManager.this.mFullscreenMode = fullscreen2;
                        final ImeFocusController controller = InputMethodManager.this.getFocusController();
                        View rootView = InputMethodManager.this.mCurRootView != null ? InputMethodManager.this.mCurRootView.getView() : null;
                        if (controller == null || rootView == null || !reportToImeController) {
                            if (!active) {
                                InputMethodManager.this.mRestartOnNextWindowFocus = true;
                                try {
                                    InputMethodManager.this.mIInputContext.finishComposingText();
                                } catch (RemoteException e2) {
                                }
                            }
                            View servedView2 = InputMethodManager.this.getServedViewLocked();
                            if (servedView2 != null && InputMethodManager.canStartInput(servedView2) && InputMethodManager.this.mCurRootView != null && InputMethodManager.this.mCurRootView.getImeFocusController().checkFocus(InputMethodManager.this.mRestartOnNextWindowFocus, false)) {
                                int reason2 = active ? 8 : 9;
                                InputMethodManager.this.mDelegate.startInput(reason2, null, 0, 0, 0);
                            }
                            return;
                        }
                        rootView.post(new Runnable() { // from class: android.view.inputmethod.InputMethodManager$H$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                ImeFocusController.this.onInteractiveChanged(active);
                            }
                        });
                        return;
                    }
                case 5:
                    InputMethodManager.this.sendInputEventAndReportResultOnMainLooper((PendingEvent) msg.obj);
                    return;
                case 6:
                    InputMethodManager.this.finishedInputEvent(msg.arg1, false, true);
                    return;
                case 7:
                    InputMethodManager.this.finishedInputEvent(msg.arg1, false, false);
                    return;
                case 8:
                case 9:
                default:
                    return;
                case 10:
                    if (msg.arg1 == 0) {
                        fullscreen = false;
                    }
                    InputConnection ic = null;
                    synchronized (InputMethodManager.this.mH) {
                        InputMethodManager.this.mFullscreenMode = fullscreen;
                        if (InputMethodManager.this.mServedInputConnectionWrapper != null) {
                            ic = InputMethodManager.this.mServedInputConnectionWrapper.getInputConnection();
                        }
                    }
                    if (ic != null) {
                        ic.reportFullscreenMode(fullscreen);
                        return;
                    }
                    return;
            }
        }
    }

    static void tearDownEditMode() {
        if (isInEditMode()) {
            synchronized (sLock) {
                sInstance = null;
            }
            return;
        }
        throw new UnsupportedOperationException("This method must be called only from layoutlib");
    }

    private static boolean isInEditMode() {
        return false;
    }

    private static InputMethodManager createInstance(int displayId, Looper looper) {
        return isInEditMode() ? createStubInstance(displayId, looper) : createRealInstance(displayId, looper);
    }

    private static InputMethodManager createRealInstance(int displayId, Looper looper) {
        try {
            IInputMethodManager service = IInputMethodManager.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.INPUT_METHOD_SERVICE));
            InputMethodManager imm = new InputMethodManager(service, displayId, looper);
            long identity = Binder.clearCallingIdentity();
            try {
                try {
                    service.addClient(imm.mClient, imm.mIInputContext, displayId);
                } catch (RemoteException e) {
                    e.rethrowFromSystemServer();
                }
                return imm;
            } finally {
                Binder.restoreCallingIdentity(identity);
            }
        } catch (ServiceManager.ServiceNotFoundException e2) {
            throw new IllegalStateException(e2);
        }
    }

    private static InputMethodManager createStubInstance(int displayId, Looper looper) {
        IInputMethodManager stubInterface = (IInputMethodManager) Proxy.newProxyInstance(IInputMethodManager.class.getClassLoader(), new Class[]{IInputMethodManager.class}, InputMethodManager$$ExternalSyntheticLambda1.INSTANCE);
        return new InputMethodManager(stubInterface, displayId, looper);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$createStubInstance$0(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> returnType = method.getReturnType();
        if (returnType == Boolean.TYPE) {
            return false;
        }
        if (returnType == Integer.TYPE) {
            return 0;
        }
        if (returnType == Long.TYPE) {
            return 0L;
        }
        if (returnType == Short.TYPE || returnType == Character.TYPE || returnType == Byte.TYPE) {
            return 0;
        }
        if (returnType == Float.TYPE) {
            return Float.valueOf(0.0f);
        }
        if (returnType == Double.TYPE) {
            return Double.valueOf(0.0d);
        }
        return null;
    }

    private InputMethodManager(IInputMethodManager service, int displayId, Looper looper) {
        BaseInputConnection baseInputConnection = new BaseInputConnection(this, false);
        this.mDummyInputConnection = baseInputConnection;
        IInputMethodManagerExt newInstance = InputMethodManagerExtPlugin.constructor.newInstance();
        this.mInputMethodManagerExt = newInstance;
        DEBUG = newInstance.hookDebug();
        this.mService = service;
        this.mMainLooper = looper;
        this.mH = new H(looper);
        this.mDisplayId = displayId;
        this.mIInputContext = new IInputConnectionWrapper(looper, baseInputConnection, this, null);
    }

    public static InputMethodManager forContext(Context context) {
        int displayId = context.getDisplayId();
        Looper looper = displayId == 0 ? Looper.getMainLooper() : context.getMainLooper();
        return forContextInternal(displayId, looper);
    }

    private static InputMethodManager forContextInternal(int displayId, Looper looper) {
        boolean isDefaultDisplay = displayId == 0;
        synchronized (sLock) {
            SparseArray<InputMethodManager> sparseArray = sInstanceMap;
            InputMethodManager instance = sparseArray.get(displayId);
            if (instance != null) {
                return instance;
            }
            InputMethodManager instance2 = createInstance(displayId, looper);
            if (sInstance == null && isDefaultDisplay) {
                sInstance = instance2;
            }
            sparseArray.put(displayId, instance2);
            return instance2;
        }
    }

    @Deprecated
    public static InputMethodManager getInstance() {
        Log.w(TAG, "InputMethodManager.getInstance() is deprecated because it cannot be compatible with multi-display. Use context.getSystemService(InputMethodManager.class) instead.", new Throwable());
        ensureDefaultInstanceForDefaultDisplayIfNecessary();
        return peekInstance();
    }

    @Deprecated
    public static InputMethodManager peekInstance() {
        InputMethodManager inputMethodManager;
        Log.w(TAG, "InputMethodManager.peekInstance() is deprecated because it cannot be compatible with multi-display. Use context.getSystemService(InputMethodManager.class) instead.", new Throwable());
        synchronized (sLock) {
            inputMethodManager = sInstance;
        }
        return inputMethodManager;
    }

    public IInputMethodClient getClient() {
        return this.mClient;
    }

    public IInputContext getInputContext() {
        return this.mIInputContext;
    }

    public List<InputMethodInfo> getInputMethodList() {
        try {
            return this.mService.getInputMethodList(UserHandle.myUserId());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<InputMethodInfo> getInputMethodListAsUser(int userId) {
        try {
            return this.mService.getInputMethodList(userId);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<InputMethodInfo> getEnabledInputMethodList() {
        try {
            return this.mService.getEnabledInputMethodList(UserHandle.myUserId());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<InputMethodInfo> getEnabledInputMethodListAsUser(int userId) {
        try {
            return this.mService.getEnabledInputMethodList(userId);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<InputMethodSubtype> getEnabledInputMethodSubtypeList(InputMethodInfo imi, boolean allowsImplicitlySelectedSubtypes) {
        try {
            return this.mService.getEnabledInputMethodSubtypeList(imi == null ? null : imi.getId(), allowsImplicitlySelectedSubtypes);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public void showStatusIcon(IBinder imeToken, String packageName, int iconId) {
        InputMethodPrivilegedOperationsRegistry.get(imeToken).updateStatusIconAsync(packageName, iconId);
    }

    @Deprecated
    public void hideStatusIcon(IBinder imeToken) {
        InputMethodPrivilegedOperationsRegistry.get(imeToken).updateStatusIconAsync(null, 0);
    }

    @Deprecated
    public void registerSuggestionSpansForNotification(SuggestionSpan[] spans) {
        Log.w(TAG, "registerSuggestionSpansForNotification() is deprecated.  Does nothing.");
    }

    @Deprecated
    public void notifySuggestionPicked(SuggestionSpan span, String originalString, int index) {
        Log.w(TAG, "notifySuggestionPicked() is deprecated.  Does nothing.");
    }

    public boolean isFullscreenMode() {
        boolean z;
        synchronized (this.mH) {
            z = this.mFullscreenMode;
        }
        return z;
    }

    public boolean isActive(View view) {
        boolean z;
        InputMethodManager fallbackImm = getFallbackInputMethodManagerIfNecessary(view);
        if (fallbackImm != null) {
            return fallbackImm.isActive(view);
        }
        checkFocus();
        synchronized (this.mH) {
            z = hasServedByInputMethodLocked(view) && this.mCurrentTextBoxAttribute != null;
        }
        return z;
    }

    public boolean isActive() {
        boolean z;
        checkFocus();
        synchronized (this.mH) {
            z = (getServedViewLocked() == null || this.mCurrentTextBoxAttribute == null) ? false : true;
        }
        return z;
    }

    public boolean isAcceptingText() {
        boolean z;
        checkFocus();
        synchronized (this.mH) {
            IInputConnectionWrapper iInputConnectionWrapper = this.mServedInputConnectionWrapper;
            z = (iInputConnectionWrapper == null || iInputConnectionWrapper.getInputConnection() == null) ? false : true;
        }
        return z;
    }

    public boolean isInputMethodSuppressingSpellChecker() {
        boolean z;
        synchronized (this.mH) {
            z = this.mIsInputMethodSuppressingSpellChecker;
        }
        return z;
    }

    void clearBindingLocked() {
        if (DEBUG) {
            Log.v(TAG, "Clearing binding!");
        }
        clearConnectionLocked();
        setInputChannelLocked(null);
        this.mBindSequence = -1;
        this.mCurId = null;
        this.mCurMethod = null;
        this.mCurrentInputMethodSession = null;
    }

    void setInputChannelLocked(InputChannel channel) {
        InputChannel inputChannel = this.mCurChannel;
        if (inputChannel != channel) {
            if (inputChannel == null || channel == null || inputChannel.getToken() != channel.getToken()) {
                if (this.mCurSender != null) {
                    flushPendingEventsLocked();
                    this.mCurSender.dispose();
                    this.mCurSender = null;
                }
                InputChannel inputChannel2 = this.mCurChannel;
                if (inputChannel2 != null) {
                    inputChannel2.dispose();
                }
                this.mCurChannel = channel;
            }
        }
    }

    void clearConnectionLocked() {
        this.mCurrentTextBoxAttribute = null;
        IInputConnectionWrapper iInputConnectionWrapper = this.mServedInputConnectionWrapper;
        if (iInputConnectionWrapper != null) {
            iInputConnectionWrapper.deactivate();
            this.mServedInputConnectionWrapper = null;
        }
    }

    void finishInputLocked() {
        this.mIsInputMethodSuppressingSpellChecker = false;
        setNextServedViewLocked(null);
        if (getServedViewLocked() != null) {
            if (DEBUG) {
                Log.v(TAG, "FINISH INPUT: mServedView=" + dumpViewInfo(getServedViewLocked()));
            }
            setServedViewLocked(null);
            this.mCompletions = null;
            this.mServedConnecting = false;
            clearConnectionLocked();
        }
    }

    public void displayCompletions(View view, CompletionInfo[] completions) {
        InputMethodManager fallbackImm = getFallbackInputMethodManagerIfNecessary(view);
        if (fallbackImm != null) {
            fallbackImm.displayCompletions(view, completions);
            return;
        }
        checkFocus();
        synchronized (this.mH) {
            if (hasServedByInputMethodLocked(view)) {
                this.mCompletions = completions;
                InputMethodSessionWrapper inputMethodSessionWrapper = this.mCurrentInputMethodSession;
                if (inputMethodSessionWrapper != null) {
                    inputMethodSessionWrapper.displayCompletions(completions);
                }
            }
        }
    }

    public void updateExtractedText(View view, int token, ExtractedText text) {
        InputMethodManager fallbackImm = getFallbackInputMethodManagerIfNecessary(view);
        if (fallbackImm != null) {
            fallbackImm.updateExtractedText(view, token, text);
            return;
        }
        checkFocus();
        synchronized (this.mH) {
            if (hasServedByInputMethodLocked(view)) {
                InputMethodSessionWrapper inputMethodSessionWrapper = this.mCurrentInputMethodSession;
                if (inputMethodSessionWrapper != null) {
                    inputMethodSessionWrapper.updateExtractedText(token, text);
                }
            }
        }
    }

    public boolean showSoftInput(View view, int flags) {
        InputMethodManager fallbackImm = getFallbackInputMethodManagerIfNecessary(view);
        if (fallbackImm != null) {
            return fallbackImm.showSoftInput(view, flags);
        }
        return showSoftInput(view, flags, null);
    }

    public boolean showSoftInput(View view, int flags, ResultReceiver resultReceiver) {
        return showSoftInput(view, flags, resultReceiver, 0);
    }

    private boolean showSoftInput(View view, int flags, ResultReceiver resultReceiver, int reason) {
        ImeTracing.getInstance().triggerClientDump("InputMethodManager#showSoftInput", this, null);
        InputMethodManager fallbackImm = getFallbackInputMethodManagerIfNecessary(view);
        if (fallbackImm != null) {
            return fallbackImm.showSoftInput(view, flags, resultReceiver);
        }
        if (view != null) {
            this.mInputMethodManagerExt.onCallShowBeforeCheckFocus(view.getContext());
        }
        checkFocus();
        synchronized (this.mH) {
            this.mInputMethodManagerExt.printCallPidAndUid("showSoftInput");
            if (!hasServedByInputMethodLocked(view)) {
                Log.w(TAG, "Ignoring showSoftInput() as view=" + view + " is not served.");
                return false;
            }
            int flags2 = this.mInputMethodManagerExt.adjustForceFlag(flags);
            this.mInputMethodManagerExt.updateOrmsAction();
            if (view != null) {
                try {
                    if (!(view.getViewRootImpl() == null || view.getViewRootImpl().mViewRootImplExt == null || view.getContext() == null)) {
                        view.getViewRootImpl().mViewRootImplExt.showSoftInput(view.getContext().toString());
                    }
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            }
            Log.d(TAG, "showSoftInput() view=" + view + " flags=" + flags2 + " reason=" + InputMethodDebug.softInputDisplayReasonToString(reason));
            return this.mService.showSoftInput(this.mClient, view.getWindowToken(), flags2, resultReceiver, reason);
        }
    }

    @Deprecated
    public void showSoftInputUnchecked(int flags, ResultReceiver resultReceiver) {
        synchronized (this.mH) {
            try {
                try {
                    this.mInputMethodManagerExt.printCallPidAndUid("showSoftInputUncheck");
                    Log.w(TAG, "showSoftInputUnchecked() is a hidden method, which will be removed soon. If you are using android.support.v7.widget.SearchView, please update to version 26.0 or newer version.");
                    ViewRootImpl viewRootImpl = this.mCurRootView;
                    if (!(viewRootImpl == null || viewRootImpl.getView() == null)) {
                        this.mService.showSoftInput(this.mClient, this.mCurRootView.getView().getWindowToken(), flags, resultReceiver, 0);
                        return;
                    }
                    Log.w(TAG, "No current root view, ignoring showSoftInputUnchecked()");
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean hideSoftInputFromWindow(IBinder windowToken, int flags) {
        return hideSoftInputFromWindow(windowToken, flags, null);
    }

    public boolean hideSoftInputFromWindow(IBinder windowToken, int flags, ResultReceiver resultReceiver) {
        return hideSoftInputFromWindow(windowToken, flags, resultReceiver, 3);
    }

    private boolean hideSoftInputFromWindow(IBinder windowToken, int flags, ResultReceiver resultReceiver, int reason) {
        ImeTracing.getInstance().triggerClientDump("InputMethodManager#hideSoftInputFromWindow", this, null);
        checkFocus();
        synchronized (this.mH) {
            View servedView = getServedViewLocked();
            if (servedView == null || servedView.getWindowToken() != windowToken) {
                return false;
            }
            try {
                this.mInputMethodManagerExt.printCallPidAndUid("hideSoftInputFromWindow");
                if (!(servedView.getViewRootImpl() == null || servedView.getViewRootImpl().mViewRootImplExt == null || servedView.getContext() == null)) {
                    servedView.getViewRootImpl().mViewRootImplExt.hideSoftInputFromWindow(servedView.getContext().toString());
                }
                return this.mService.hideSoftInput(this.mClient, windowToken, flags, resultReceiver, reason);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    @Deprecated
    public void toggleSoftInputFromWindow(IBinder windowToken, int showFlags, int hideFlags) {
        ImeTracing.getInstance().triggerClientDump("InputMethodManager#toggleSoftInputFromWindow", this, null);
        synchronized (this.mH) {
            View servedView = getServedViewLocked();
            if (servedView != null && servedView.getWindowToken() == windowToken) {
                this.mInputMethodManagerExt.printCallPidAndUid("toggleSoftInputFromWindow");
                toggleSoftInput(showFlags, hideFlags);
            }
        }
    }

    @Deprecated
    public void toggleSoftInput(int showFlags, int hideFlags) {
        ImeTracing.getInstance().triggerClientDump("InputMethodManager#toggleSoftInput", this, null);
        this.mInputMethodManagerExt.printCallPidAndUid("toggleSoftInput");
        synchronized (this.mH) {
            View view = getServedViewLocked();
            ImeInsetsSourceConsumer imeInsetsSourceConsumer = this.mImeInsetsConsumer;
            if (!(imeInsetsSourceConsumer == null || view == null)) {
                if (imeInsetsSourceConsumer.isRequestedVisible()) {
                    hideSoftInputFromWindow(view.getWindowToken(), hideFlags, null, 24);
                } else {
                    showSoftInput(view, showFlags, null, 23);
                }
            }
        }
    }

    public void restartInput(View view) {
        InputMethodManager fallbackImm = getFallbackInputMethodManagerIfNecessary(view);
        if (fallbackImm != null) {
            fallbackImm.restartInput(view);
            return;
        }
        checkFocus();
        synchronized (this.mH) {
            if (hasServedByInputMethodLocked(view)) {
                this.mServedConnecting = true;
                startInputInner(4, null, 0, 0, 0);
            }
        }
    }

    boolean startInputInner(final int startInputReason, IBinder windowGainingFocus, int startInputFlags, int softInputMode, int windowFlags) {
        int windowFlags2;
        int softInputMode2;
        IBinder windowGainingFocus2;
        int startInputFlags2;
        H h;
        H h2;
        View servedView;
        View view;
        int missingMethodFlags;
        Handler icHandler;
        IInputConnectionWrapper servedContext;
        int startInputFlags3;
        RemoteException e;
        Handler icHandler2;
        View view2;
        CompletionInfo[] completionInfoArr;
        Handler icHandler3;
        synchronized (this.mH) {
            try {
                View view3 = getServedViewLocked();
                if (DEBUG) {
                    Log.v(TAG, "Starting input: view=" + dumpViewInfo(view3) + " reason=" + InputMethodDebug.startInputReasonToString(startInputReason));
                }
                if (view3 == null) {
                    if (DEBUG) {
                        Log.v(TAG, "ABORT input: no served view!");
                    }
                    return false;
                }
                if (windowGainingFocus == null) {
                    IBinder windowGainingFocus3 = view3.getWindowToken();
                    if (windowGainingFocus3 == null) {
                        Log.e(TAG, "ABORT input: ServedView must be attached to a Window");
                        return false;
                    }
                    startInputFlags2 = getStartInputFlags(view3, startInputFlags);
                    int softInputMode3 = view3.getViewRootImpl().mWindowAttributes.softInputMode;
                    windowFlags2 = view3.getViewRootImpl().mWindowAttributes.flags;
                    windowGainingFocus2 = windowGainingFocus3;
                    softInputMode2 = softInputMode3;
                } else {
                    startInputFlags2 = startInputFlags;
                    windowGainingFocus2 = windowGainingFocus;
                    softInputMode2 = softInputMode;
                    windowFlags2 = windowFlags;
                }
                Handler vh = view3.getHandler();
                if (vh == null) {
                    if (DEBUG) {
                        Log.v(TAG, "ABORT input: no handler for view! Close current input.");
                    }
                    closeCurrentInput();
                    return false;
                } else if (vh.getLooper() != Looper.myLooper()) {
                    if (DEBUG) {
                        Log.v(TAG, "Starting input: reschedule to view thread");
                    }
                    vh.post(new Runnable() { // from class: android.view.inputmethod.InputMethodManager$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            InputMethodManager.this.lambda$startInputInner$1$InputMethodManager(startInputReason);
                        }
                    });
                    return false;
                } else {
                    EditorInfo tba = new EditorInfo();
                    tba.packageName = view3.getContext().getOpPackageName();
                    tba.autofillId = view3.getAutofillId();
                    tba.fieldId = view3.getId();
                    InputConnection ic = view3.onCreateInputConnection(tba);
                    if (DEBUG) {
                        Log.v(TAG, "Starting input: tba=" + tba + " ic=" + ic);
                    }
                    H h3 = this.mH;
                    synchronized (h3) {
                        try {
                            try {
                                View servedView2 = getServedViewLocked();
                                if (servedView2 != view3) {
                                    view = view3;
                                    servedView = servedView2;
                                    h2 = h3;
                                } else if (!this.mServedConnecting) {
                                    view = view3;
                                    servedView = servedView2;
                                    h2 = h3;
                                } else {
                                    if (this.mCurrentTextBoxAttribute == null) {
                                        startInputFlags2 |= 4;
                                    }
                                    this.mCurrentTextBoxAttribute = tba;
                                    this.mServedConnecting = false;
                                    IInputConnectionWrapper iInputConnectionWrapper = this.mServedInputConnectionWrapper;
                                    if (iInputConnectionWrapper != null) {
                                        try {
                                            iInputConnectionWrapper.deactivate();
                                            this.mServedInputConnectionWrapper = null;
                                        } catch (Throwable th) {
                                            e = th;
                                            h = h3;
                                            throw e;
                                        }
                                    }
                                    if (ic != null) {
                                        this.mCursorSelStart = tba.initialSelStart;
                                        this.mCursorSelEnd = tba.initialSelEnd;
                                        this.mCursorCandStart = -1;
                                        this.mCursorCandEnd = -1;
                                        this.mCursorRect.setEmpty();
                                        this.mCursorAnchorInfo = null;
                                        int missingMethodFlags2 = InputConnectionInspector.getMissingMethodFlags(ic);
                                        if ((missingMethodFlags2 & 32) != 0) {
                                            icHandler3 = null;
                                        } else {
                                            icHandler3 = ic.getHandler();
                                        }
                                        missingMethodFlags = missingMethodFlags2;
                                        icHandler = icHandler3;
                                        servedContext = new IInputConnectionWrapper(icHandler3 != null ? icHandler3.getLooper() : vh.getLooper(), ic, this, view3);
                                    } else {
                                        missingMethodFlags = 0;
                                        icHandler = null;
                                        servedContext = null;
                                    }
                                    this.mServedInputConnectionWrapper = servedContext;
                                    if (DEBUG) {
                                        Log.v(TAG, "START INPUT: view=" + dumpViewInfo(view3) + " ic=" + ic + " tba=" + tba + " startInputFlags=" + InputMethodDebug.startInputFlagsToString(startInputFlags2));
                                    }
                                    try {
                                        h = h3;
                                    } catch (RemoteException e2) {
                                        e = e2;
                                        h = h3;
                                    } catch (Throwable th2) {
                                        e = th2;
                                        h = h3;
                                    }
                                    try {
                                        InputBindResult res = this.mService.startInputOrWindowGainedFocus(startInputReason, this.mClient, windowGainingFocus2, this.mInputMethodManagerExt.adjustFlagForWindowingMode(view3, startInputFlags2), softInputMode2, windowFlags2, tba, servedContext, missingMethodFlags, view3.getContext().getApplicationInfo().targetSdkVersion);
                                        try {
                                            if (DEBUG) {
                                                try {
                                                    Log.v(TAG, "Starting input: Bind result=" + res);
                                                } catch (Throwable th3) {
                                                    e = th3;
                                                    throw e;
                                                }
                                            }
                                            if (res == null) {
                                                Log.wtf(TAG, "startInputOrWindowGainedFocus must not return null. startInputReason=" + InputMethodDebug.startInputReasonToString(startInputReason) + " editorInfo=" + tba + " startInputFlags=" + InputMethodDebug.startInputFlagsToString(startInputFlags3));
                                                return false;
                                            }
                                            this.mIsInputMethodSuppressingSpellChecker = res.isInputMethodSuppressingSpellChecker;
                                            if (res.id != null) {
                                                setInputChannelLocked(res.channel);
                                                this.mBindSequence = res.sequence;
                                                this.mCurMethod = res.method;
                                                this.mCurrentInputMethodSession = InputMethodSessionWrapper.createOrNull(res.method);
                                                this.mCurId = res.id;
                                            } else if (!(res.channel == null || res.channel == this.mCurChannel)) {
                                                res.channel.dispose();
                                            }
                                            switch (res.result) {
                                                case 12:
                                                    this.mRestartOnNextWindowFocus = true;
                                                    break;
                                            }
                                            InputMethodSessionWrapper inputMethodSessionWrapper = this.mCurrentInputMethodSession;
                                            if (!(inputMethodSessionWrapper == null || (completionInfoArr = this.mCompletions) == null)) {
                                                inputMethodSessionWrapper.displayCompletions(completionInfoArr);
                                            }
                                            if (ic != null && res != null && res.method != null) {
                                                if (DEBUG) {
                                                    StringBuilder sb = new StringBuilder();
                                                    sb.append("Calling View.onInputConnectionOpened: view= ");
                                                    view2 = view3;
                                                    sb.append(view2);
                                                    sb.append(", ic=");
                                                    sb.append(ic);
                                                    sb.append(", tba=");
                                                    sb.append(tba);
                                                    sb.append(", handler=");
                                                    icHandler2 = icHandler;
                                                    sb.append(icHandler2);
                                                    Log.v(TAG, sb.toString());
                                                } else {
                                                    icHandler2 = icHandler;
                                                    view2 = view3;
                                                }
                                                view2.onInputConnectionOpenedInternal(ic, tba, icHandler2);
                                            }
                                            return true;
                                        } catch (Throwable th4) {
                                            e = th4;
                                        }
                                    } catch (RemoteException e3) {
                                        e = e3;
                                        try {
                                            throw e.rethrowFromSystemServer();
                                        } catch (Throwable th5) {
                                            e = th5;
                                            throw e;
                                        }
                                    } catch (Throwable th6) {
                                        e = th6;
                                    }
                                }
                                if (DEBUG) {
                                    Log.v(TAG, "Starting input: finished by someone else. view=" + dumpViewInfo(view) + " servedView=" + dumpViewInfo(servedView) + " mServedConnecting=" + this.mServedConnecting);
                                }
                                return false;
                            } catch (Throwable th7) {
                                e = th7;
                            }
                        } catch (Throwable th8) {
                            e = th8;
                            h = h3;
                        }
                    }
                }
            } catch (Throwable th9) {
                th = th9;
                while (true) {
                    try {
                        break;
                    } catch (Throwable th10) {
                        th = th10;
                    }
                }
                throw th;
            }
        }
    }

    public /* synthetic */ void lambda$startInputInner$1$InputMethodManager(int startInputReason) {
        this.mDelegate.startInput(startInputReason, null, 0, 0, 0);
    }

    @Deprecated
    public void windowDismissed(IBinder appWindowToken) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getStartInputFlags(View focusedView, int startInputFlags) {
        int startInputFlags2 = startInputFlags | 1;
        if (focusedView.onCheckIsTextEditor()) {
            startInputFlags2 |= 2;
        }
        return this.mInputMethodManagerExt.adjustStartInputFlags(startInputFlags2);
    }

    public void checkFocus() {
        ImeFocusController controller = getFocusController();
        if (controller == null) {
            return;
        }
        if (this.mInputMethodManagerExt.needForceNewFocus()) {
            controller.checkFocus(true, true);
        } else {
            controller.checkFocus(false, true);
        }
    }

    void closeCurrentInput() {
        synchronized (this.mH) {
            this.mInputMethodManagerExt.printCallPidAndUid("closeCurrentInput");
            ViewRootImpl viewRootImpl = this.mCurRootView;
            if (viewRootImpl == null || viewRootImpl.getView() == null) {
                Log.w(TAG, "No current root view, ignoring closeCurrentInput()");
                return;
            }
            try {
                this.mService.hideSoftInput(this.mClient, this.mCurRootView.getView().getWindowToken(), 2, null, 3);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void registerImeConsumer(ImeInsetsSourceConsumer imeInsetsConsumer) {
        if (imeInsetsConsumer != null) {
            synchronized (this.mH) {
                this.mImeInsetsConsumer = imeInsetsConsumer;
            }
            return;
        }
        throw new IllegalStateException("ImeInsetsSourceConsumer cannot be null.");
    }

    public void unregisterImeConsumer(ImeInsetsSourceConsumer imeInsetsConsumer) {
        if (imeInsetsConsumer != null) {
            synchronized (this.mH) {
                if (this.mImeInsetsConsumer == imeInsetsConsumer) {
                    this.mImeInsetsConsumer = null;
                }
            }
            return;
        }
        throw new IllegalStateException("ImeInsetsSourceConsumer cannot be null.");
    }

    public boolean requestImeShow(IBinder windowToken) {
        synchronized (this.mH) {
            View servedView = getServedViewLocked();
            if (servedView != null && servedView.getWindowToken() == windowToken) {
                showSoftInput(servedView, 0, null, 25);
                return true;
            }
            return false;
        }
    }

    public void notifyImeHidden(IBinder windowToken) {
        ViewRootImpl viewRootImpl;
        ImeTracing.getInstance().triggerClientDump("InputMethodManager#notifyImeHidden", this, null);
        synchronized (this.mH) {
            if (!(this.mCurrentInputMethodSession == null || (viewRootImpl = this.mCurRootView) == null || viewRootImpl.getWindowToken() != windowToken)) {
                this.mInputMethodManagerExt.printCallPidAndUid("notifyImeHidden");
                this.mCurrentInputMethodSession.notifyImeHidden();
            }
        }
    }

    public void removeImeSurface(IBinder windowToken) {
        synchronized (this.mH) {
            try {
                try {
                    this.mInputMethodManagerExt.printCallPidAndUid("removeImeSurface");
                    this.mService.removeImeSurfaceFromWindowAsync(windowToken);
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void updateSelection(View view, int selStart, int selEnd, int candidatesStart, int candidatesEnd) {
        InputMethodManager fallbackImm = getFallbackInputMethodManagerIfNecessary(view);
        if (fallbackImm != null) {
            fallbackImm.updateSelection(view, selStart, selEnd, candidatesStart, candidatesEnd);
            return;
        }
        checkFocus();
        synchronized (this.mH) {
            if (!(!hasServedByInputMethodLocked(view) || this.mCurrentTextBoxAttribute == null || this.mCurrentInputMethodSession == null)) {
                if (!(this.mCursorSelStart == selStart && this.mCursorSelEnd == selEnd && this.mCursorCandStart == candidatesStart && this.mCursorCandEnd == candidatesEnd)) {
                    if (DEBUG) {
                        Log.d(TAG, "updateSelection");
                    }
                    if (DEBUG) {
                        Log.v(TAG, "SELECTION CHANGE: " + this.mCurrentInputMethodSession);
                    }
                    int oldSelStart = this.mCursorSelStart;
                    int oldSelEnd = this.mCursorSelEnd;
                    this.mCursorSelStart = selStart;
                    this.mCursorSelEnd = selEnd;
                    this.mCursorCandStart = candidatesStart;
                    this.mCursorCandEnd = candidatesEnd;
                    this.mCurrentInputMethodSession.updateSelection(oldSelStart, oldSelEnd, selStart, selEnd, candidatesStart, candidatesEnd);
                }
            }
        }
    }

    @Deprecated
    public void viewClicked(View view) {
        View servedView;
        View nextServedView;
        InputMethodManager fallbackImm = getFallbackInputMethodManagerIfNecessary(view);
        if (fallbackImm != null) {
            fallbackImm.viewClicked(view);
            return;
        }
        synchronized (this.mH) {
            servedView = getServedViewLocked();
            nextServedView = getNextServedViewLocked();
        }
        boolean focusChanged = servedView != nextServedView;
        if (view != null) {
            this.mInputMethodManagerExt.onCallClickBeforeCheckFocus(view.getContext());
        }
        checkFocus();
        synchronized (this.mH) {
            if (!(!hasServedByInputMethodLocked(view) || this.mCurrentTextBoxAttribute == null || this.mCurrentInputMethodSession == null)) {
                if (DEBUG) {
                    Log.v(TAG, "onViewClicked: " + focusChanged);
                }
                this.mCurrentInputMethodSession.viewClicked(focusChanged);
            }
        }
    }

    @Deprecated
    public boolean isWatchingCursor(View view) {
        return false;
    }

    public boolean isCursorAnchorInfoEnabled() {
        boolean z;
        synchronized (this.mH) {
            int i = this.mRequestUpdateCursorAnchorInfoMonitorMode;
            z = false;
            boolean isImmediate = (i & 1) != 0;
            boolean isMonitoring = (i & 2) != 0;
            if (isImmediate || isMonitoring) {
                z = true;
            }
        }
        return z;
    }

    public void setUpdateCursorAnchorInfoMode(int flags) {
        synchronized (this.mH) {
            this.mRequestUpdateCursorAnchorInfoMonitorMode = flags;
        }
    }

    @Deprecated
    public void updateCursor(View view, int left, int top, int right, int bottom) {
        InputMethodManager fallbackImm = getFallbackInputMethodManagerIfNecessary(view);
        if (fallbackImm != null) {
            fallbackImm.updateCursor(view, left, top, right, bottom);
            return;
        }
        checkFocus();
        synchronized (this.mH) {
            if (!(!hasServedByInputMethodLocked(view) || this.mCurrentTextBoxAttribute == null || this.mCurrentInputMethodSession == null)) {
                this.mTmpCursorRect.set(left, top, right, bottom);
                if (!this.mCursorRect.equals(this.mTmpCursorRect)) {
                    if (DEBUG) {
                        Log.d(TAG, "updateCursor: " + this.mCurrentInputMethodSession);
                    }
                    this.mCurrentInputMethodSession.updateCursor(this.mTmpCursorRect);
                    this.mCursorRect.set(this.mTmpCursorRect);
                }
            }
        }
    }

    public void updateCursorAnchorInfo(View view, CursorAnchorInfo cursorAnchorInfo) {
        if (view != null && cursorAnchorInfo != null) {
            InputMethodManager fallbackImm = getFallbackInputMethodManagerIfNecessary(view);
            if (fallbackImm != null) {
                fallbackImm.updateCursorAnchorInfo(view, cursorAnchorInfo);
                return;
            }
            checkFocus();
            synchronized (this.mH) {
                if (!(!hasServedByInputMethodLocked(view) || this.mCurrentTextBoxAttribute == null || this.mCurrentInputMethodSession == null)) {
                    boolean isImmediate = true;
                    if ((this.mRequestUpdateCursorAnchorInfoMonitorMode & 1) == 0) {
                        isImmediate = false;
                    }
                    if (isImmediate || !Objects.equals(this.mCursorAnchorInfo, cursorAnchorInfo)) {
                        if (DEBUG) {
                            Log.v(TAG, "updateCursorAnchorInfo: " + cursorAnchorInfo);
                        }
                        this.mCurrentInputMethodSession.updateCursorAnchorInfo(cursorAnchorInfo);
                        this.mInputMethodManagerExt.updateCursorAnchorInfoToSynergy(cursorAnchorInfo);
                        this.mCursorAnchorInfo = cursorAnchorInfo;
                        this.mRequestUpdateCursorAnchorInfoMonitorMode &= -2;
                        return;
                    }
                    if (DEBUG) {
                        Log.w(TAG, "Ignoring redundant updateCursorAnchorInfo: info=" + cursorAnchorInfo);
                    }
                }
            }
        }
    }

    public void sendAppPrivateCommand(View view, String action, Bundle data) {
        InputMethodManager fallbackImm = getFallbackInputMethodManagerIfNecessary(view);
        if (fallbackImm != null) {
            fallbackImm.sendAppPrivateCommand(view, action, data);
            return;
        }
        checkFocus();
        synchronized (this.mH) {
            if (!(!hasServedByInputMethodLocked(view) || this.mCurrentTextBoxAttribute == null || this.mCurrentInputMethodSession == null)) {
                if (DEBUG) {
                    Log.v(TAG, "APP PRIVATE COMMAND " + action + ": " + data);
                }
                this.mCurrentInputMethodSession.appPrivateCommand(action, data);
            }
        }
    }

    @Deprecated
    public void setInputMethod(IBinder token, String id) {
        if (token != null) {
            InputMethodPrivilegedOperationsRegistry.get(token).setInputMethod(id);
        } else if (id != null) {
            if (Process.myUid() == 1000) {
                Log.w(TAG, "System process should not be calling setInputMethod() because almost always it is a bug under multi-user / multi-profile environment. Consider interacting with InputMethodManagerService directly via LocalServices.");
                return;
            }
            Context fallbackContext = ActivityThread.currentApplication();
            if (fallbackContext != null && fallbackContext.checkSelfPermission(Manifest.permission.WRITE_SECURE_SETTINGS) == 0) {
                List<InputMethodInfo> imis = getEnabledInputMethodList();
                int numImis = imis.size();
                boolean found = false;
                int i = 0;
                while (true) {
                    if (i >= numImis) {
                        break;
                    }
                    InputMethodInfo imi = imis.get(i);
                    if (id.equals(imi.getId())) {
                        found = true;
                        break;
                    }
                    i++;
                }
                if (!found) {
                    Log.e(TAG, "Ignoring setInputMethod(null, " + id + ") because the specified id not found in enabled IMEs.");
                    return;
                }
                Log.w(TAG, "The undocumented behavior that setInputMethod() accepts null token when the caller has WRITE_SECURE_SETTINGS is deprecated. This behavior may be completely removed in a future version.  Update secure settings directly instead.");
                ContentResolver resolver = fallbackContext.getContentResolver();
                Settings.Secure.putInt(resolver, Settings.Secure.SELECTED_INPUT_METHOD_SUBTYPE, -1);
                Settings.Secure.putString(resolver, Settings.Secure.DEFAULT_INPUT_METHOD, id);
            }
        }
    }

    @Deprecated
    public void setInputMethodAndSubtype(IBinder token, String id, InputMethodSubtype subtype) {
        if (token == null) {
            Log.e(TAG, "setInputMethodAndSubtype() does not accept null token on Android Q and later.");
        } else {
            InputMethodPrivilegedOperationsRegistry.get(token).setInputMethodAndSubtype(id, subtype);
        }
    }

    @Deprecated
    public void hideSoftInputFromInputMethod(IBinder token, int flags) {
        this.mInputMethodManagerExt.printCallPidAndUid("hideSoftInputFromInputMethod");
        InputMethodPrivilegedOperationsRegistry.get(token).hideMySoftInput(flags);
    }

    @Deprecated
    public void showSoftInputFromInputMethod(IBinder token, int flags) {
        this.mInputMethodManagerExt.printCallPidAndUid("showSoftInputFromInputMethod");
        InputMethodPrivilegedOperationsRegistry.get(token).showMySoftInput(flags);
    }

    public int dispatchInputEvent(InputEvent event, Object token, FinishedInputEventCallback callback, Handler handler) {
        synchronized (this.mH) {
            if (this.mCurrentInputMethodSession == null) {
                return 0;
            }
            if (event instanceof KeyEvent) {
                KeyEvent keyEvent = (KeyEvent) event;
                if (keyEvent.getAction() == 0 && keyEvent.getKeyCode() == 63 && keyEvent.getRepeatCount() == 0) {
                    showInputMethodPickerLocked();
                    return 1;
                }
            }
            if (DEBUG) {
                Log.v(TAG, "DISPATCH INPUT EVENT: " + this.mCurrentInputMethodSession);
            }
            PendingEvent p = obtainPendingEventLocked(event, token, this.mCurId, callback, handler);
            if (this.mMainLooper.isCurrentThread()) {
                return sendInputEventOnMainLooperLocked(p);
            }
            Message msg = this.mH.obtainMessage(5, p);
            msg.setAsynchronous(true);
            this.mH.sendMessage(msg);
            return -1;
        }
    }

    public void dispatchKeyEventFromInputMethod(View targetView, KeyEvent event) {
        ViewRootImpl viewRootImpl;
        View servedView;
        InputMethodManager fallbackImm = getFallbackInputMethodManagerIfNecessary(targetView);
        if (fallbackImm != null) {
            fallbackImm.dispatchKeyEventFromInputMethod(targetView, event);
            return;
        }
        synchronized (this.mH) {
            if (targetView != null) {
                try {
                    viewRootImpl = targetView.getViewRootImpl();
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                viewRootImpl = null;
            }
            if (viewRootImpl == null && (servedView = getServedViewLocked()) != null) {
                viewRootImpl = servedView.getViewRootImpl();
            }
            if (viewRootImpl != null) {
                viewRootImpl.dispatchKeyFromIme(event);
            }
        }
    }

    void sendInputEventAndReportResultOnMainLooper(PendingEvent p) {
        synchronized (this.mH) {
            int result = sendInputEventOnMainLooperLocked(p);
            if (result != -1) {
                boolean handled = true;
                if (result != 1) {
                    handled = false;
                }
                invokeFinishedInputEventCallback(p, handled);
            }
        }
    }

    int sendInputEventOnMainLooperLocked(PendingEvent p) {
        InputChannel inputChannel = this.mCurChannel;
        if (inputChannel != null) {
            if (this.mCurSender == null) {
                this.mCurSender = new ImeInputEventSender(inputChannel, this.mH.getLooper());
            }
            InputEvent event = p.mEvent;
            int seq = event.getSequenceNumber();
            if (this.mCurSender.sendInputEvent(seq, event)) {
                this.mPendingEvents.put(seq, p);
                Trace.traceCounter(4L, PENDING_EVENT_COUNTER, this.mPendingEvents.size());
                Message msg = this.mH.obtainMessage(6, seq, 0, p);
                msg.setAsynchronous(true);
                this.mH.sendMessageDelayed(msg, INPUT_METHOD_NOT_RESPONDING_TIMEOUT);
                return -1;
            }
            Log.w(TAG, "Unable to send input event to IME: " + this.mCurId + " dropping: " + event);
        }
        return 0;
    }

    void finishedInputEvent(int seq, boolean handled, boolean timeout) {
        synchronized (this.mH) {
            int index = this.mPendingEvents.indexOfKey(seq);
            if (index >= 0) {
                PendingEvent p = this.mPendingEvents.valueAt(index);
                this.mPendingEvents.removeAt(index);
                Trace.traceCounter(4L, PENDING_EVENT_COUNTER, this.mPendingEvents.size());
                if (timeout) {
                    Log.w(TAG, "Timeout waiting for IME to handle input event after 2500 ms: " + p.mInputMethodId);
                } else {
                    this.mH.removeMessages(6, p);
                }
                invokeFinishedInputEventCallback(p, handled);
            }
        }
    }

    void invokeFinishedInputEventCallback(PendingEvent p, boolean handled) {
        p.mHandled = handled;
        if (p.mHandler.getLooper().isCurrentThread()) {
            p.run();
            return;
        }
        Message msg = Message.obtain(p.mHandler, p);
        msg.setAsynchronous(true);
        msg.sendToTarget();
    }

    private void flushPendingEventsLocked() {
        this.mH.removeMessages(7);
        int count = this.mPendingEvents.size();
        for (int i = 0; i < count; i++) {
            int seq = this.mPendingEvents.keyAt(i);
            Message msg = this.mH.obtainMessage(7, seq, 0);
            msg.setAsynchronous(true);
            msg.sendToTarget();
        }
    }

    private PendingEvent obtainPendingEventLocked(InputEvent event, Object token, String inputMethodId, FinishedInputEventCallback callback, Handler handler) {
        PendingEvent p = this.mPendingEventPool.acquire();
        if (p == null) {
            p = new PendingEvent();
        }
        p.mEvent = event;
        p.mToken = token;
        p.mInputMethodId = inputMethodId;
        p.mCallback = callback;
        p.mHandler = handler;
        return p;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recyclePendingEventLocked(PendingEvent p) {
        p.recycle();
        this.mPendingEventPool.release(p);
    }

    public void showInputMethodPicker() {
        synchronized (this.mH) {
            showInputMethodPickerLocked();
        }
    }

    public void showInputMethodPickerFromSystem(boolean showAuxiliarySubtypes, int displayId) {
        int mode;
        if (showAuxiliarySubtypes) {
            mode = 1;
        } else {
            mode = 2;
        }
        try {
            this.mService.showInputMethodPickerFromSystem(this.mClient, mode, displayId);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private void showInputMethodPickerLocked() {
        try {
            this.mService.showInputMethodPickerFromClient(this.mClient, 0);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isInputMethodPickerShown() {
        try {
            return this.mService.isInputMethodPickerShownForTest();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void showInputMethodAndSubtypeEnabler(String imiId) {
        try {
            this.mService.showInputMethodAndSubtypeEnablerFromClient(this.mClient, imiId);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public InputMethodSubtype getCurrentInputMethodSubtype() {
        try {
            return this.mService.getCurrentInputMethodSubtype();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public boolean setCurrentInputMethodSubtype(InputMethodSubtype subtype) {
        Context fallbackContext;
        if (Process.myUid() == 1000) {
            Log.w(TAG, "System process should not call setCurrentInputMethodSubtype() because almost always it is a bug under multi-user / multi-profile environment. Consider directly interacting with InputMethodManagerService via LocalServices.");
            return false;
        } else if (subtype == null || (fallbackContext = ActivityThread.currentApplication()) == null || fallbackContext.checkSelfPermission(Manifest.permission.WRITE_SECURE_SETTINGS) != 0) {
            return false;
        } else {
            ContentResolver contentResolver = fallbackContext.getContentResolver();
            String imeId = Settings.Secure.getString(contentResolver, Settings.Secure.DEFAULT_INPUT_METHOD);
            if (ComponentName.unflattenFromString(imeId) == null) {
                return false;
            }
            try {
                List<InputMethodSubtype> enabledSubtypes = this.mService.getEnabledInputMethodSubtypeList(imeId, true);
                int numSubtypes = enabledSubtypes.size();
                for (int i = 0; i < numSubtypes; i++) {
                    InputMethodSubtype enabledSubtype = enabledSubtypes.get(i);
                    if (enabledSubtype.equals(subtype)) {
                        Settings.Secure.putInt(contentResolver, Settings.Secure.SELECTED_INPUT_METHOD_SUBTYPE, enabledSubtype.hashCode());
                        return true;
                    }
                }
                return false;
            } catch (RemoteException e) {
                return false;
            }
        }
    }

    @Deprecated
    public void notifyUserAction() {
        Log.w(TAG, "notifyUserAction() is a hidden method, which is now just a stub method that does nothing.  Leave comments in b.android.com/114740982 if your  application still depends on the previous behavior of this method.");
    }

    public Map<InputMethodInfo, List<InputMethodSubtype>> getShortcutInputMethodsAndSubtypes() {
        List<InputMethodInfo> enabledImes = getEnabledInputMethodList();
        enabledImes.sort(Comparator.comparingInt(InputMethodManager$$ExternalSyntheticLambda2.INSTANCE));
        int numEnabledImes = enabledImes.size();
        for (int imiIndex = 0; imiIndex < numEnabledImes; imiIndex++) {
            InputMethodInfo imi = enabledImes.get(imiIndex);
            List<InputMethodSubtype> subtypes = getEnabledInputMethodSubtypeList(imi, true);
            int subtypeCount = subtypes.size();
            for (int subtypeIndex = 0; subtypeIndex < subtypeCount; subtypeIndex++) {
                InputMethodSubtype subtype = imi.getSubtypeAt(subtypeIndex);
                if (SUBTYPE_MODE_VOICE.equals(subtype.getMode())) {
                    return Collections.singletonMap(imi, Collections.singletonList(subtype));
                }
            }
        }
        return Collections.emptyMap();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$getShortcutInputMethodsAndSubtypes$2(InputMethodInfo imi) {
        return !imi.isSystem();
    }

    public int getInputMethodWindowVisibleHeight() {
        try {
            return this.mService.getInputMethodWindowVisibleHeight();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public boolean switchToLastInputMethod(IBinder imeToken) {
        return InputMethodPrivilegedOperationsRegistry.get(imeToken).switchToPreviousInputMethod();
    }

    @Deprecated
    public boolean switchToNextInputMethod(IBinder imeToken, boolean onlyCurrentIme) {
        return InputMethodPrivilegedOperationsRegistry.get(imeToken).switchToNextInputMethod(onlyCurrentIme);
    }

    @Deprecated
    public boolean shouldOfferSwitchingToNextInputMethod(IBinder imeToken) {
        return InputMethodPrivilegedOperationsRegistry.get(imeToken).shouldOfferSwitchingToNextInputMethod();
    }

    @Deprecated
    public void setAdditionalInputMethodSubtypes(String imiId, InputMethodSubtype[] subtypes) {
        try {
            this.mService.setAdditionalInputMethodSubtypes(imiId, subtypes);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public InputMethodSubtype getLastInputMethodSubtype() {
        try {
            return this.mService.getLastInputMethodSubtype();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int getDisplayId() {
        return this.mDisplayId;
    }

    void doDump(FileDescriptor fd, PrintWriter fout, String[] args) {
        if (!processDump(fd, args)) {
            if (this.mInputMethodManagerExt.dynamicallyConfigDebug(args, "imm")) {
                DEBUG = this.mInputMethodManagerExt.hookDebug();
                if (this.mCurId == null) {
                    return;
                }
            }
            Printer p = new PrintWriterPrinter(fout);
            p.println("Input method client state for " + this + SettingsStringUtil.DELIMITER);
            StringBuilder sb = new StringBuilder();
            sb.append("  mService=");
            sb.append(this.mService);
            p.println(sb.toString());
            p.println("  mMainLooper=" + this.mMainLooper);
            p.println("  mIInputContext=" + this.mIInputContext);
            p.println("  mActive=" + this.mActive + " mRestartOnNextWindowFocus=" + this.mRestartOnNextWindowFocus + " mBindSequence=" + this.mBindSequence + " mCurId=" + this.mCurId);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("  mFullscreenMode=");
            sb2.append(this.mFullscreenMode);
            p.println(sb2.toString());
            if (this.mCurrentInputMethodSession != null) {
                p.println("  mCurMethod=" + this.mCurrentInputMethodSession);
            } else {
                p.println("  mCurMethod= null");
            }
            p.println("  mCurRootView=" + this.mCurRootView);
            p.println("  mServedView=" + getServedViewLocked());
            p.println("  mNextServedView=" + getNextServedViewLocked());
            p.println("  mServedConnecting=" + this.mServedConnecting);
            if (this.mCurrentTextBoxAttribute != null) {
                p.println("  mCurrentTextBoxAttribute:");
                this.mCurrentTextBoxAttribute.dump(p, "    ");
            } else {
                p.println("  mCurrentTextBoxAttribute: null");
            }
            p.println("  mServedInputConnectionWrapper=" + this.mServedInputConnectionWrapper);
            p.println("  mCompletions=" + Arrays.toString(this.mCompletions));
            p.println("  mCursorRect=" + this.mCursorRect);
            p.println("  mCursorSelStart=" + this.mCursorSelStart + " mCursorSelEnd=" + this.mCursorSelEnd + " mCursorCandStart=" + this.mCursorCandStart + " mCursorCandEnd=" + this.mCursorCandEnd);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public final class ImeInputEventSender extends InputEventSender {
        public ImeInputEventSender(InputChannel inputChannel, Looper looper) {
            super(inputChannel, looper);
        }

        @Override // android.view.InputEventSender
        public void onInputEventFinished(int seq, boolean handled) {
            InputMethodManager.this.finishedInputEvent(seq, handled, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public final class PendingEvent implements Runnable {
        public FinishedInputEventCallback mCallback;
        public InputEvent mEvent;
        public boolean mHandled;
        public Handler mHandler;
        public String mInputMethodId;
        public Object mToken;

        private PendingEvent() {
        }

        public void recycle() {
            this.mEvent = null;
            this.mToken = null;
            this.mInputMethodId = null;
            this.mCallback = null;
            this.mHandler = null;
            this.mHandled = false;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.mCallback.onFinishedInputEvent(this.mToken, this.mHandled);
            synchronized (InputMethodManager.this.mH) {
                InputMethodManager.this.recyclePendingEventLocked(this);
            }
        }
    }

    private static String dumpViewInfo(View view) {
        if (view == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(view);
        sb.append(",focus=" + view.hasFocus());
        sb.append(",windowFocus=" + view.hasWindowFocus());
        sb.append(",autofillUiShowing=" + isAutofillUIShowing(view));
        sb.append(",window=" + view.getWindowToken());
        sb.append(",displayId=" + view.getContext().getDisplayId());
        sb.append(",temporaryDetach=" + view.isTemporarilyDetached());
        sb.append(",hasImeFocus=" + view.hasImeFocus());
        return sb.toString();
    }

    private boolean processDump(FileDescriptor fd, String[] args) {
        if (args == null) {
            return false;
        }
        for (String arg : args) {
            if (arg.equals(ImeTracing.PROTO_ARG)) {
                ProtoOutputStream proto = new ProtoOutputStream(fd);
                dumpDebug(proto, null);
                proto.flush();
                return true;
            }
        }
        return false;
    }

    public void dumpDebug(ProtoOutputStream proto, ProtoOutputStream icProto) {
        if (this.mCurrentInputMethodSession != null) {
            proto.write(1120986464257L, this.mDisplayId);
            long token = proto.start(1146756268034L);
            synchronized (this.mH) {
                proto.write(1138166333441L, this.mCurId);
                proto.write(1133871366146L, this.mFullscreenMode);
                proto.write(1133871366148L, this.mActive);
                proto.write(1133871366149L, this.mServedConnecting);
                proto.end(token);
                ViewRootImpl viewRootImpl = this.mCurRootView;
                if (viewRootImpl != null) {
                    viewRootImpl.dumpDebug(proto, 1146756268035L);
                }
                EditorInfo editorInfo = this.mCurrentTextBoxAttribute;
                if (editorInfo != null) {
                    editorInfo.dumpDebug(proto, 1146756268038L);
                }
                ImeInsetsSourceConsumer imeInsetsSourceConsumer = this.mImeInsetsConsumer;
                if (imeInsetsSourceConsumer != null) {
                    imeInsetsSourceConsumer.dumpDebug(proto, 1146756268037L);
                }
                IInputConnectionWrapper iInputConnectionWrapper = this.mServedInputConnectionWrapper;
                if (iInputConnectionWrapper != null) {
                    iInputConnectionWrapper.dumpDebug(proto, 1146756268040L);
                }
                if (icProto != null) {
                    proto.write(1146756268041L, icProto.getBytes());
                }
            }
        }
    }
}
