package android.inputmethodservice;

import android.graphics.Rect;
import android.inputmethodservice.MultiClientInputMethodServiceDelegate;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.view.InputChannel;
import android.view.InputEvent;
import android.view.InputEventReceiver;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CursorAnchorInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.ExtractedText;
import com.android.internal.inputmethod.CancellationGroup;
import com.android.internal.inputmethod.IMultiClientInputMethodSession;
import com.android.internal.os.SomeArgs;
import com.android.internal.util.function.pooled.PooledLambda;
import com.android.internal.view.IInputContext;
import com.android.internal.view.IInputMethodSession;
import com.android.internal.view.InputConnectionWrapper;
import java.lang.ref.WeakReference;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class MultiClientInputMethodClientCallbackAdaptor {
    static final boolean DEBUG = false;
    static final String TAG = MultiClientInputMethodClientCallbackAdaptor.class.getSimpleName();
    CallbackImpl mCallbackImpl;
    private final CancellationGroup mCancellationGroup;
    KeyEvent.DispatcherState mDispatcherState;
    Handler mHandler;
    InputEventReceiver mInputEventReceiver;
    InputChannel mReadChannel;
    private final Object mSessionLock;

    /* JADX INFO: Access modifiers changed from: package-private */
    public IInputMethodSession.Stub createIInputMethodSession() {
        InputMethodSessionImpl inputMethodSessionImpl;
        synchronized (this.mSessionLock) {
            inputMethodSessionImpl = new InputMethodSessionImpl(this.mSessionLock, this.mCallbackImpl, this.mHandler, this.mCancellationGroup);
        }
        return inputMethodSessionImpl;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IMultiClientInputMethodSession.Stub createIMultiClientInputMethodSession() {
        MultiClientInputMethodSessionImpl multiClientInputMethodSessionImpl;
        synchronized (this.mSessionLock) {
            multiClientInputMethodSessionImpl = new MultiClientInputMethodSessionImpl(this.mSessionLock, this.mCallbackImpl, this.mHandler, this.mCancellationGroup);
        }
        return multiClientInputMethodSessionImpl;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MultiClientInputMethodClientCallbackAdaptor(MultiClientInputMethodServiceDelegate.ClientCallback clientCallback, Looper looper, KeyEvent.DispatcherState dispatcherState, InputChannel readChannel) {
        Object obj = new Object();
        this.mSessionLock = obj;
        CancellationGroup cancellationGroup = new CancellationGroup();
        this.mCancellationGroup = cancellationGroup;
        synchronized (obj) {
            this.mCallbackImpl = new CallbackImpl(this, clientCallback);
            this.mDispatcherState = dispatcherState;
            Handler handler = new Handler(looper, null, true);
            this.mHandler = handler;
            this.mReadChannel = readChannel;
            this.mInputEventReceiver = new ImeInputEventReceiver(readChannel, handler.getLooper(), cancellationGroup, this.mDispatcherState, this.mCallbackImpl.mOriginalCallback);
        }
    }

    /* loaded from: classes2.dex */
    private static final class KeyEventCallbackAdaptor implements KeyEvent.Callback {
        private final MultiClientInputMethodServiceDelegate.ClientCallback mLocalCallback;

        KeyEventCallbackAdaptor(MultiClientInputMethodServiceDelegate.ClientCallback callback) {
            this.mLocalCallback = callback;
        }

        @Override // android.view.KeyEvent.Callback
        public boolean onKeyDown(int keyCode, KeyEvent event) {
            return this.mLocalCallback.onKeyDown(keyCode, event);
        }

        @Override // android.view.KeyEvent.Callback
        public boolean onKeyLongPress(int keyCode, KeyEvent event) {
            return this.mLocalCallback.onKeyLongPress(keyCode, event);
        }

        @Override // android.view.KeyEvent.Callback
        public boolean onKeyUp(int keyCode, KeyEvent event) {
            return this.mLocalCallback.onKeyUp(keyCode, event);
        }

        @Override // android.view.KeyEvent.Callback
        public boolean onKeyMultiple(int keyCode, int count, KeyEvent event) {
            return this.mLocalCallback.onKeyMultiple(keyCode, event);
        }
    }

    /* loaded from: classes2.dex */
    private static final class ImeInputEventReceiver extends InputEventReceiver {
        private final CancellationGroup mCancellationGroupOnFinishSession;
        private final MultiClientInputMethodServiceDelegate.ClientCallback mClientCallback;
        private final KeyEvent.DispatcherState mDispatcherState;
        private final KeyEventCallbackAdaptor mKeyEventCallbackAdaptor;

        ImeInputEventReceiver(InputChannel readChannel, Looper looper, CancellationGroup cancellationGroupOnFinishSession, KeyEvent.DispatcherState dispatcherState, MultiClientInputMethodServiceDelegate.ClientCallback callback) {
            super(readChannel, looper);
            this.mCancellationGroupOnFinishSession = cancellationGroupOnFinishSession;
            this.mDispatcherState = dispatcherState;
            this.mClientCallback = callback;
            this.mKeyEventCallbackAdaptor = new KeyEventCallbackAdaptor(callback);
        }

        @Override // android.view.InputEventReceiver
        public void onInputEvent(InputEvent event) {
            boolean handled;
            boolean handled2;
            if (this.mCancellationGroupOnFinishSession.isCanceled()) {
                handled = false;
                return;
            }
            handled = false;
            try {
                if (event instanceof KeyEvent) {
                    KeyEvent keyEvent = (KeyEvent) event;
                    KeyEventCallbackAdaptor keyEventCallbackAdaptor = this.mKeyEventCallbackAdaptor;
                    handled2 = keyEvent.dispatch(keyEventCallbackAdaptor, this.mDispatcherState, keyEventCallbackAdaptor);
                } else {
                    MotionEvent motionEvent = (MotionEvent) event;
                    if (motionEvent.isFromSource(4)) {
                        handled2 = this.mClientCallback.onTrackballEvent(motionEvent);
                    } else {
                        handled2 = this.mClientCallback.onGenericMotionEvent(motionEvent);
                    }
                }
            } finally {
                finishInputEvent(event, handled);
            }
        }
    }

    /* loaded from: classes2.dex */
    private static final class InputMethodSessionImpl extends IInputMethodSession.Stub {
        private CallbackImpl mCallbackImpl;
        private final CancellationGroup mCancellationGroupOnFinishSession;
        private Handler mHandler;
        private final Object mSessionLock;

        InputMethodSessionImpl(Object lock, CallbackImpl callback, Handler handler, CancellationGroup cancellationGroupOnFinishSession) {
            this.mSessionLock = lock;
            this.mCallbackImpl = callback;
            this.mHandler = handler;
            this.mCancellationGroupOnFinishSession = cancellationGroupOnFinishSession;
        }

        @Override // com.android.internal.view.IInputMethodSession
        public void updateExtractedText(int token, ExtractedText text) {
            MultiClientInputMethodClientCallbackAdaptor.reportNotSupported();
        }

        @Override // com.android.internal.view.IInputMethodSession
        public void updateSelection(int oldSelStart, int oldSelEnd, int newSelStart, int newSelEnd, int candidatesStart, int candidatesEnd) {
            synchronized (this.mSessionLock) {
                if (!(this.mCallbackImpl == null || this.mHandler == null)) {
                    SomeArgs args = SomeArgs.obtain();
                    args.argi1 = oldSelStart;
                    args.argi2 = oldSelEnd;
                    args.argi3 = newSelStart;
                    args.argi4 = newSelEnd;
                    args.argi5 = candidatesStart;
                    args.argi6 = candidatesEnd;
                    this.mHandler.sendMessage(PooledLambda.obtainMessage(MultiClientInputMethodClientCallbackAdaptor$InputMethodSessionImpl$$ExternalSyntheticLambda2.INSTANCE, this.mCallbackImpl, args));
                }
            }
        }

        @Override // com.android.internal.view.IInputMethodSession
        public void viewClicked(boolean focusChanged) {
            MultiClientInputMethodClientCallbackAdaptor.reportNotSupported();
        }

        @Override // com.android.internal.view.IInputMethodSession
        public void updateCursor(Rect newCursor) {
            MultiClientInputMethodClientCallbackAdaptor.reportNotSupported();
        }

        @Override // com.android.internal.view.IInputMethodSession
        public void displayCompletions(CompletionInfo[] completions) {
            Handler handler;
            synchronized (this.mSessionLock) {
                if (!(this.mCallbackImpl == null || (handler = this.mHandler) == null)) {
                    handler.sendMessage(PooledLambda.obtainMessage(MultiClientInputMethodClientCallbackAdaptor$InputMethodSessionImpl$$ExternalSyntheticLambda3.INSTANCE, this.mCallbackImpl, completions));
                }
            }
        }

        @Override // com.android.internal.view.IInputMethodSession
        public void appPrivateCommand(String action, Bundle data) {
            Handler handler;
            synchronized (this.mSessionLock) {
                if (!(this.mCallbackImpl == null || (handler = this.mHandler) == null)) {
                    handler.sendMessage(PooledLambda.obtainMessage(MultiClientInputMethodClientCallbackAdaptor$InputMethodSessionImpl$$ExternalSyntheticLambda0.INSTANCE, this.mCallbackImpl, action, data));
                }
            }
        }

        @Override // com.android.internal.view.IInputMethodSession
        public void finishSession() {
            synchronized (this.mSessionLock) {
                if (!(this.mCallbackImpl == null || this.mHandler == null)) {
                    this.mCancellationGroupOnFinishSession.cancelAll();
                    this.mHandler.sendMessage(PooledLambda.obtainMessage(MultiClientInputMethodClientCallbackAdaptor$InputMethodSessionImpl$$ExternalSyntheticLambda4.INSTANCE, this.mCallbackImpl));
                    this.mCallbackImpl = null;
                    this.mHandler = null;
                }
            }
        }

        @Override // com.android.internal.view.IInputMethodSession
        public void updateCursorAnchorInfo(CursorAnchorInfo info) {
            Handler handler;
            synchronized (this.mSessionLock) {
                if (!(this.mCallbackImpl == null || (handler = this.mHandler) == null)) {
                    handler.sendMessage(PooledLambda.obtainMessage(MultiClientInputMethodClientCallbackAdaptor$InputMethodSessionImpl$$ExternalSyntheticLambda1.INSTANCE, this.mCallbackImpl, info));
                }
            }
        }

        @Override // com.android.internal.view.IInputMethodSession
        public final void notifyImeHidden() {
            MultiClientInputMethodClientCallbackAdaptor.reportNotSupported();
        }

        @Override // com.android.internal.view.IInputMethodSession
        public void removeImeSurface() {
            MultiClientInputMethodClientCallbackAdaptor.reportNotSupported();
        }

        @Override // com.android.internal.view.IInputMethodSession
        public void finishInput() throws RemoteException {
            MultiClientInputMethodClientCallbackAdaptor.reportNotSupported();
        }
    }

    /* loaded from: classes2.dex */
    private static final class MultiClientInputMethodSessionImpl extends IMultiClientInputMethodSession.Stub {
        private CallbackImpl mCallbackImpl;
        private final CancellationGroup mCancellationGroupOnFinishSession;
        private Handler mHandler;
        private final Object mSessionLock;

        MultiClientInputMethodSessionImpl(Object lock, CallbackImpl callback, Handler handler, CancellationGroup cancellationGroupOnFinishSession) {
            this.mSessionLock = lock;
            this.mCallbackImpl = callback;
            this.mHandler = handler;
            this.mCancellationGroupOnFinishSession = cancellationGroupOnFinishSession;
        }

        @Override // com.android.internal.inputmethod.IMultiClientInputMethodSession
        public void startInputOrWindowGainedFocus(IInputContext inputContext, int missingMethods, EditorInfo editorInfo, int controlFlags, int softInputMode, int windowHandle) {
            synchronized (this.mSessionLock) {
                if (!(this.mCallbackImpl == null || this.mHandler == null)) {
                    SomeArgs args = SomeArgs.obtain();
                    InputConnectionWrapper inputConnectionWrapper = null;
                    WeakReference<AbstractInputMethodService> fakeIMS = new WeakReference<>(null);
                    if (inputContext != null) {
                        inputConnectionWrapper = new InputConnectionWrapper(fakeIMS, inputContext, missingMethods, this.mCancellationGroupOnFinishSession);
                    }
                    args.arg1 = inputConnectionWrapper;
                    args.arg2 = editorInfo;
                    args.argi1 = controlFlags;
                    args.argi2 = softInputMode;
                    args.argi3 = windowHandle;
                    this.mHandler.sendMessage(PooledLambda.obtainMessage(MultiClientInputMethodClientCallbackAdaptor$MultiClientInputMethodSessionImpl$$ExternalSyntheticLambda2.INSTANCE, this.mCallbackImpl, args));
                }
            }
        }

        @Override // com.android.internal.inputmethod.IMultiClientInputMethodSession
        public void showSoftInput(int flags, ResultReceiver resultReceiver) {
            Handler handler;
            synchronized (this.mSessionLock) {
                if (!(this.mCallbackImpl == null || (handler = this.mHandler) == null)) {
                    handler.sendMessage(PooledLambda.obtainMessage(MultiClientInputMethodClientCallbackAdaptor$MultiClientInputMethodSessionImpl$$ExternalSyntheticLambda1.INSTANCE, this.mCallbackImpl, Integer.valueOf(flags), resultReceiver));
                }
            }
        }

        @Override // com.android.internal.inputmethod.IMultiClientInputMethodSession
        public void hideSoftInput(int flags, ResultReceiver resultReceiver) {
            Handler handler;
            synchronized (this.mSessionLock) {
                if (!(this.mCallbackImpl == null || (handler = this.mHandler) == null)) {
                    handler.sendMessage(PooledLambda.obtainMessage(MultiClientInputMethodClientCallbackAdaptor$MultiClientInputMethodSessionImpl$$ExternalSyntheticLambda0.INSTANCE, this.mCallbackImpl, Integer.valueOf(flags), resultReceiver));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class CallbackImpl {
        private final MultiClientInputMethodClientCallbackAdaptor mCallbackAdaptor;
        private boolean mFinished = false;
        private final MultiClientInputMethodServiceDelegate.ClientCallback mOriginalCallback;

        CallbackImpl(MultiClientInputMethodClientCallbackAdaptor callbackAdaptor, MultiClientInputMethodServiceDelegate.ClientCallback callback) {
            this.mCallbackAdaptor = callbackAdaptor;
            this.mOriginalCallback = callback;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void updateSelection(SomeArgs args) {
            try {
                if (!this.mFinished) {
                    this.mOriginalCallback.onUpdateSelection(args.argi1, args.argi2, args.argi3, args.argi4, args.argi5, args.argi6);
                }
            } finally {
                args.recycle();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void displayCompletions(CompletionInfo[] completions) {
            if (!this.mFinished) {
                this.mOriginalCallback.onDisplayCompletions(completions);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void appPrivateCommand(String action, Bundle data) {
            if (!this.mFinished) {
                this.mOriginalCallback.onAppPrivateCommand(action, data);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void finishSession() {
            if (!this.mFinished) {
                this.mFinished = true;
                this.mOriginalCallback.onFinishSession();
                synchronized (this.mCallbackAdaptor.mSessionLock) {
                    this.mCallbackAdaptor.mDispatcherState = null;
                    if (this.mCallbackAdaptor.mReadChannel != null) {
                        this.mCallbackAdaptor.mReadChannel.dispose();
                        this.mCallbackAdaptor.mReadChannel = null;
                    }
                    this.mCallbackAdaptor.mInputEventReceiver = null;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void updateCursorAnchorInfo(CursorAnchorInfo info) {
            if (!this.mFinished) {
                this.mOriginalCallback.onUpdateCursorAnchorInfo(info);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void startInputOrWindowGainedFocus(SomeArgs args) {
            try {
                if (!this.mFinished) {
                    InputConnectionWrapper inputConnection = (InputConnectionWrapper) args.arg1;
                    EditorInfo editorInfo = (EditorInfo) args.arg2;
                    int startInputFlags = args.argi1;
                    int softInputMode = args.argi2;
                    int windowHandle = args.argi3;
                    this.mOriginalCallback.onStartInputOrWindowGainedFocus(inputConnection, editorInfo, startInputFlags, softInputMode, windowHandle);
                }
            } finally {
                args.recycle();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void showSoftInput(int flags, ResultReceiver resultReceiver) {
            if (!this.mFinished) {
                this.mOriginalCallback.onShowSoftInput(flags, resultReceiver);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void hideSoftInput(int flags, ResultReceiver resultReceiver) {
            if (!this.mFinished) {
                this.mOriginalCallback.onHideSoftInput(flags, resultReceiver);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void reportNotSupported() {
    }
}
